/*******************************************************************************
 * CodeGenHelper.java
 * 
 * A module implementing a code generator helper methods.
 * ****************************************************************************/
package cs444.group9.AST.CodeGenerator;

import cs444.group9.AST.Node.Body.FormalParameterNode;
import cs444.group9.AST.Node.Declaration.*;
import cs444.group9.AST.Node.Body.MethodHeaderNode;
import cs444.group9.AST.Node.Environments.MethodData;
import cs444.group9.AST.Node.StatementExpression.ClassInstanceCreationExprNode;
import cs444.group9.AST.ASTTree;
import cs444.group9.AST.Node.StatementExpression.MethodInvocationNode;


/* CONVENTIONS for LABELS
counter is a field incremented after each label, set to 0 at visit(CompilationUnit)
qualifiedFileName is packageName.fileName in current file, set at visit(CompilationUnit)

labels for methods: _qualifiedFileName_METHOD_methodName#type1#...#type n
labels for constructors: _qualifiedFileName_CONSTRUCTOR_constrcutorName#type1#...#type n
labels for arrays: _ARRAY$ + labelname/basictype
labels for fields: _qualifiedFileName_FIELD_fieldName
labels for classes: _qualifiedFileName_CLASS_className

other labels (e.g. for ifStmt): _qualifiedFileName_labelName_counter

 */

/* CONVENTIONS FOR EXPRESSIONS
    result of any subexpression is stored in eax
 */

/* CONVENTIONS FOR METHOD CALLS
    caller saves registers ebx-edx, ebp (NOT eax!!!) followed by arguments
    callee sets ebp
    ... (callee does stuff)
    callee restores esp
    callee returns (ret)
    upon return, caller discards arguments and restores registers and THIS
    
    (basically it's the CDECL ABI except arguments are stored left to right)
 */

/* ARRAYS
    an array of type C is represented as a class with

    vtable ptr
    length
    arr[0]
    arr[1] ...
 */

/* CLASSES
    vtable ptr
    static fields
 */
/* OBJECTS
    vtable ptr
    nonstatic fields
 */
/* VTABLE
    SI column address
    Subtype column address
    Subtype column index
    methods
 */

public class CodeGenHelper {
    int offset;
    int counter;
    String qualifiedFileName;
    ASTTree currRoot;

    /********************************** PRELIMINARY setup ************************************/

    /*******************************************************************************
     * Sets qualified name
     * notes: to be called at the start of visit(CompilationUnit)
     * time : O(1)
     * *****************************************************************************/
    public void setQualifiedFileName(String file, ASTTree t) {
        qualifiedFileName = file;
        counter = 0;
        currRoot = t;
    } // setQualifiedFileName()

    /*******************************************************************************
     * Sets offset 
     * time : O(1)
     * *****************************************************************************/
    public void setOffset(int off) {
        offset = off;
    } // setOffset()

    /*******************************************************************************
     * Gets offset 
     * time : O(1)
     * *****************************************************************************/
    public int getOffset() {
        return offset;
    } // getOffset()

    /*******************************************************************************
     * Converts offset to string
     * notes: if offset is nonnegative returns "+ offset"
     *        otherwise returns "- (-offset)"
     * time : O(1)
     * *****************************************************************************/
    public String offsetToString(int offset) {
        if (offset >= 0) return "+" + offset;
        else return "-" + (-offset);
    }  // offsetToString()

    /********************************** LABELS ************************************/

    /*******************************************************************************
     * Gets label for method from MethodHeaderNode
     * time : O(#params)
     * *****************************************************************************/
    public String methodLabel(MethodHeaderNode node) {
        ClassDeclNode classNode = node.getEnv().getRoot().getRoot().TypeDeclaration().ClassDeclaration();
        String className = classNode.getEnv().getPackage().toString() + "." + classNode.getEnv().getScopePair().getName();
        String label = "_" + className + "_METHOD_" + node.MethodDeclarator().Identifier() + "#";
        for (FormalParameterNode param : node.MethodDeclarator().FormalParameterList()) {
            label += param.Type().toString() + "#";
        } // for
        node.setLabel(label);
        return label;
    } // methodLabel(MethodHeaderNode)

    /*******************************************************************************
     * Gets label for method from methodLabel
     * time : O(1)
     * *****************************************************************************/
    public String methodLabel(MethodData m) {
        return methodLabel((MethodHeaderNode) m.getDecl());
    } // methodLabel(MethodData)

    /*******************************************************************************
     * Gets label for constructorLabel
     * time : O(#params)
     * *****************************************************************************/
    public String constructorLabel(ConstructorDeclarationNode node) {
        ClassDeclNode classNode = node.getEnv().getRoot().getRoot().TypeDeclaration().ClassDeclaration();
        String className = classNode.getEnv().getPackage().toString() + "." + classNode.getEnv().getScopePair().getName();
        String label = "_" + className + "_CONSTRUCTOR_" + node.ConstructorDeclarator().Identifier() + "#";
        for (FormalParameterNode param : node.ConstructorDeclarator().FormalParameterList()) {
            label += param.Type().toString() + "#";
        } // for
        node.setLabel(label);
        return label;
    } // constructorLabel()

    /*******************************************************************************
     * Gets label for field
     * time : O(1)
     * *****************************************************************************/
    public String fieldLabel(FieldDeclNode node) {
        ClassDeclNode classNode = node.getEnv().getRoot().getRoot().TypeDeclaration().ClassDeclaration();
        String className = classNode.getEnv().getPackage().toString() + "." + classNode.getEnv().getScopePair().getName();
        String label = "_" + className + "_FIELD_" + node.VariableDeclarator().VariableDeclarator();
        node.setLabel(label);
        return label;
    } // fieldLabel()

    /*******************************************************************************
     * Gets label for class
     * time : O(1)
     * *****************************************************************************/
    public String ClassLabel(ClassDeclNode node) {
        String className = node.getEnv().getPackage().toString() + "." + node.getEnv().getScopePair().getName();
        String label = "_" + className + "_CLASS_" + node.Identifier();
        node.setLabel(label);
        return label;
    } // ClassLabel()

    /*******************************************************************************
     * Gets label for interface
     * time : O(1)
     * *****************************************************************************/
    public String InterfaceLabel(InterfaceDeclNode node) {
        String className = node.getEnv().getPackage().toString() + "." + node.getEnv().getScopePair().getName();
        String label = "_" + className + "_INTERFACE_" + node.Identifier();
        node.setLabel(label);
        return label;
    } // InterfaceLabel()

    /*******************************************************************************
     * Gets label for vtable
     * time : O(1)
     * *****************************************************************************/
    public String VtableLabel(ClassDeclNode node) {
        String label = "_VTABLE_" + node.getEnv().getPackage() + "." + node.Identifier();
        node.setVtable(label);
        return label;
    } // VtableLabel()

    /*******************************************************************************
     * Gets label for array
     * time : O(1)
     * *****************************************************************************/
    public String ArrayLabel(String name) {
        String label = "_ARRAY$" + name;
        return label;
    } // ArrayLable()

    /*******************************************************************************
     * Gets array label for vtable
     * time : O(1)
     * *****************************************************************************/
    public String VtableLabelArray(String name) {
        String label = "_ARRAY$_VTABLE_" + name;
        return label;
    } // VtableLabelArray()

    /*******************************************************************************
     * Gets general label for name
     * time : O(1)
     * *****************************************************************************/
    public String generalLabel(String name) {
        counter++;
        return "_" + qualifiedFileName + "_" + name + "_" + counter;
    } /// generalLabel()

    /********************************** CODE GENERATION ************************************/

    /*******************************************************************************
     * Gets label for this
     * time : O(1)
     * *****************************************************************************/
    public String getThisLabel() {
        return "__THIS";
    } // getThisLabel()

    /*******************************************************************************
     * Gets null string label
     * time : O(1)
     * *****************************************************************************/
    public String nullStringLabel() {
        return "__createNullString";
    } // nullStringLabel()

    /*******************************************************************************
     * Gets standard externs
     * time : O(1)
     * *****************************************************************************/
    public String getStandardExterns() {
        return "extern __malloc\nextern __exception\nextern " + getThisLabel()+"\nextern " + nullStringLabel();
    } // getStandardExterns()

    /*******************************************************************************
     * Gets code for storing local variable (corresponding to node)
     * time : O(1)
     * *****************************************************************************/
    public String storeLocal(LocalVariableDeclNode node) {
        node.setOffset(offset);
        offset -= 4;
        return "push eax ; store local " + node.VariableDeclaratorId();
    } // storeLocal()

    /*******************************************************************************
     * Gets code for loading local variable (corresponding to node)
     * notes: return address of local
     * time : O(1)
     * *****************************************************************************/
    public String loadLocal(LocalVariableDeclNode node) {
        return "mov eax , ebp\nadd eax,  " + node.getOffset() + " ; get address of local " + node.VariableDeclaratorId();
    } // loadLocal()

    /*******************************************************************************
     * Gets code for storing argument corresponding to argIndex in function call
     * time : O(1)
     * *****************************************************************************/
    public String storeArg(MethodInvocationNode node, int argIndex) {
        MethodHeaderNode method = (MethodHeaderNode) node.getMethodDecl();
        MethodDeclNode decl = method.MethodDeclarator();
        FormalParameterNode param = decl.FormalParameterList().get(argIndex);
        return "push eax ; store argument " + param.VariableDeclaratorId() + "\n";
    } // storeArg(MethodInvocationNode, int)

    /*******************************************************************************
     * Gets code for storing argument corresponding to argIndex in class creation call
     * time : O(1)
     * *****************************************************************************/
    public String storeArg(ClassInstanceCreationExprNode node, int argIndex) {
        ConstructorDeclarationNode constructor = (ConstructorDeclarationNode) node.ConstructDecl();
        ConstructorDeclNode decl = constructor.ConstructorDeclarator();
        FormalParameterNode param = decl.FormalParameterList().get(argIndex);
        return "push eax ; store argument " + param.VariableDeclaratorId() + "\n";
    } // storeArg(ClassInstanceCreationExprNode, int)

    /*******************************************************************************
     * Gets code for calling static method
     * time : O(1)
     * *****************************************************************************/
    public String callStaticMethod(MethodHeaderNode node) {
        if (node.getMethodData().isNative()) {
            ClassDeclNode classNode = node.getEnv().getRoot().getRoot().TypeDeclaration().ClassDeclaration();
            String className = classNode.getEnv().getPackage().toString() + "." + classNode.getEnv().getScopePair().getName();
            String methodLabel = "NATIVE" + className + "." + node.MethodDeclarator().Identifier();
            // always extern native method label
            return "extern " + methodLabel + "\ncall " + methodLabel + " ; call native method";
        } // if
        String methodLabel = methodLabel(node);
        String extern = "";
        if (node.getEnv().getRoot() != currRoot) extern = "extern " + methodLabel + "\n";
        return extern + "call " + methodLabel + " ; call static method";
    } // callStaticMethod()

    /*******************************************************************************
     * Gets code for calling non-static method
     * time : O(1)
     * *****************************************************************************/
    public String callNonStaticMethod(MethodHeaderNode node) {
        String loadSITable = "";
        int methodOffset = node.getOffset();
        if (node.getEnv().getRoot().isInterface()) {
            loadSITable = "mov eax, [eax] ; load SI table \n";
            methodOffset = node.getInterfaceMethodoffset();
        } // if
        return loadSITable + "mov eax, [eax] ; get vtable \nmov eax , [eax " + offsetToString(methodOffset) + "] ; load method \ncall eax ";
    } // callNonStaticMethod()

    /*******************************************************************************
     * Gets code for calling constructor
     * time : O(1)
     * *****************************************************************************/
    public String callConstructor(ConstructorDeclarationNode node) {
        String label = constructorLabel(node);
        String extern = "";
        if (node.getEnv().getRoot() != currRoot) extern = "extern " + label + "\n";
        return extern + "call " + label + " ; call constructor";
    } // callConstructor()

    /*******************************************************************************
     * Gets code for calling malloc
     * time : O(1)
     * *****************************************************************************/
    public String callMalloc() {
        return "push ebp ; save ebp\ncall __malloc\npop ebp ; load ebp";
    } // callMalloc()

    /*******************************************************************************
     * Gets code for loading argument (corresponding to node)
     * time : O(1)
     * *****************************************************************************/
    public String loadArgument(FormalParameterNode node) {
        return "mov eax , ebp\nadd eax, " + node.getOffset() + " ; get address of arg " + node.VariableDeclaratorId();
    } // loadArgument()

    /*******************************************************************************
     * Gets code for storing registers eax-edx, ebp for method call
     * time : O(1)
     * *****************************************************************************/
    public String storeRegs() {
        return "; save registers\npush esi\npush ebx\npush ecx\npush edx\npush ebp\n";
    } // storeRegs()

    /*******************************************************************************
     * Gets code for discarding arguments, loading registers eax-edx, ebp after 
     * return from method call
     * time : O(1)
     * *****************************************************************************/
    public String loadRegs(int numParams) {
        return "add esp, " + numParams * 4 + " ; pop arguments \n; load registers\npop ebp\npop edx\npop ecx\npop ebx\npop esi\n";
    } // loadRegs()

    /*******************************************************************************
     * Gets code for return from a method
     * time : O(1)
     * *****************************************************************************/
    public String returnCode() {
        return "mov esp, ebp\nret ; return\n";
    } // returnCode()

    /*******************************************************************************
     * Gets code at the start of each method
     * time : O(1)
     * *****************************************************************************/
    public String methodPrologue() {
        return "mov ebp, esp";
    } // methodPrologue()

    /*******************************************************************************
     * Gets code at start of block
     * time : O(1)
     * *****************************************************************************/
    public String blockStart(){
        return "push esi; \nmov esi, esp ; block start";
    } // blockStart()

    /*******************************************************************************
     * Gets code at end of block
     * time : O(1)
     * *****************************************************************************/
    public String blockEnd(){
        return "mov esp, esi ; block end\npop esi";
    } // blockEnd()

    /*******************************************************************************
     * Gets code for storing non-static variable (corresponding to node)
     * time : O(1)
     * *****************************************************************************/
    public void storeField(FieldDeclNode node) {
        node.setOffset(offset);
        offset += 4;
    } // storeField()

    /*******************************************************************************
     * Gets code for loading non-static field variable (corresponding to node)
     * time : O(1)
     * *****************************************************************************/
    public String loadField(FieldDeclNode node) {
        if (node.getFieldData().isStatic()) {
            String label = node.getFieldData().getDecl().getEnv().getRoot()
                    .getRoot().TypeDeclaration().ClassDeclaration().getLabel();
            return "mov eax, [ " + label + " ]\n" +
                    "mov eax , [eax + " + node.getOffset() + "] ; load field "
                    + node.getFieldData().getFieldName() + " \n";
        } else { 
            //assume address of object is in eax
            return "mov eax, [eax + " + node.getOffset() + "] ; load field " +
                    node.getFieldData().getFieldName();
        } // else
    } // loadField()

    /*******************************************************************************
     * Gets code for storing method label
     * time : O(1)
     * *****************************************************************************/
    public String storeMethod(MethodData m) {
        String label = methodLabel(m);
        ((MethodHeaderNode) m.getDecl()).setOffset(offset);
        String result = "";
        if (m.isAbstract() || m.isNative()) result += "mov ebx, 0 ; method " + label + " has no body \n";
        else result += "extern " + label + "\nmov ebx, " + label + "\n";
        result += "mov [eax " + offsetToString(offset) + "], ebx";

        offset += 4;
        return result;
    } // storeMethod()

    /*******************************************************************************
     * Gets code for storing interface method label (if method is implemented)
     * time : O(1)
     * *****************************************************************************/
    public String storeInterfaceMethod(MethodData m, MethodData interfMeth) {
        String result = "";
        String interfaceLabel = ((MethodHeaderNode) interfMeth.getDecl()).getLabel();
        if (m != null) {
            String label = methodLabel(m);
            result += "extern " + label + "\n";
            result += "mov ebx, " + label + "\n";
            result += "mov [eax " + offsetToString(offset) + "], ebx";
            result += "; implements " + interfaceLabel;
        } else {
            result += "mov ecx, 0\n";
            result += "mov [eax" + offsetToString(offset) + "], ecx ; null (interface method " + interfMeth.getMethodName() + " not implemented)";
        } // else
        ((MethodHeaderNode) interfMeth.getDecl()).setInterfaceMethodOffset(offset);
        offset += 4;
        return result;
    } // storeInterfaceMethod()

    /*******************************************************************************
     * Gets code for storing subtype bit for classType and corresponding arrayType
     * time : O(1)
     * *****************************************************************************/
    public String storeSubtypeBit(ASTTree classType, boolean flag, boolean arrayFlag) {
        String result = flag ? "1" : "0";
        String className;
        if(classType.isClass()) className = classType.getRoot().TypeDeclaration().ClassDeclaration().Identifier();
        else className = classType.getRoot().TypeDeclaration().InterfaceDeclaration().Identifier();
        result = "mov ebx, " + result + "\nmov [eax " + offsetToString(offset) + "], ebx; class/interface Subtype of " + className + " \n";
        String arrayResult = arrayFlag ? "1" : "0";
        result = result+"mov ebx, "+arrayResult+"\nmov [eax "+offsetToString(offset + 4) + "], ebx ; class/interface array Subtype of "+className;
        if(classType.isClass()) classType.getRoot().TypeDeclaration().ClassDeclaration().setOffset(offset);
        else classType.getRoot().TypeDeclaration().InterfaceDeclaration().setOffset(offset);
        offset += 8;
        return result;
    } // storeSubtypeBit(ASTTree, boolean)

    /*******************************************************************************
     * Gets code for storing  subtype bit for simple array  types
     * time : O(1)
     * *****************************************************************************/
    public String storeSubtypeBit(boolean flag) {
        String result = flag ? "1" : "0";
        result = "mov ebx, " + result + "\nmov [eax " + offsetToString(offset) + "], ebx";
        offset += 4;
        return result;
    } // storeSubtypeBit(boolean)
} // class CodeGenHelper
