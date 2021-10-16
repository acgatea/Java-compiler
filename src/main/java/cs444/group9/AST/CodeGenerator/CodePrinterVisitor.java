/*******************************************************************************
 * CodePrinterVisitor.java
 * 
 * A module implementing code generation with the visitor pattern.
 * ****************************************************************************/

package cs444.group9.AST.CodeGenerator;

import cs444.group9.AST.Node.Array.ArrayAccessNode;
import cs444.group9.AST.Node.Array.FieldAcccessNode;
import cs444.group9.AST.Node.Array.PrimaryNoNewArrayNode;
import cs444.group9.AST.Node.Body.CompilationUnitNode;
import cs444.group9.AST.ASTTree;
import cs444.group9.AST.Node.Body.FormalParameterNode;
import cs444.group9.AST.Node.Body.MethodDeclarationNode;
import cs444.group9.AST.Node.Body.MethodHeaderNode;
import cs444.group9.AST.Node.Declaration.*;
import cs444.group9.AST.Node.Expression.*;
import cs444.group9.AST.Node.LiteralNode;
import cs444.group9.AST.Node.Environments.MethodData;
import cs444.group9.AST.Node.Environments.FieldData;
import cs444.group9.AST.Node.Statement.*;
import cs444.group9.AST.Node.StatementExpression.AssignmentNode;
import cs444.group9.AST.Node.StatementExpression.ClassInstanceCreationExprNode;
import cs444.group9.AST.Node.StatementExpression.MethodInvocationNode;
import cs444.group9.AST.Node.Type.ArrayTypeNode;
import cs444.group9.AST.Node.Type.BasicTypeNode;
import cs444.group9.AST.Node.Type.TypeNode;
import cs444.group9.AST.Node.NameNode;
import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Visitor.DefaultVisitor;
import cs444.group9.AST.Visitor.iVisitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class CodePrinterVisitor extends DefaultVisitor{
    static List<MethodData> _interfaceMethods;
    static List<ASTTree> _ClassTypes;
    static List<String> _SimpleArrayTypes;
    ASTTree objectClass;
    ASTTree cloneableInterface;
    ASTTree serializableInterface;

    String _outputFileName;
    String _outputDirectory;

    CodeGenHelper interf;
    CodeChunk _codeChunk;

    boolean directlyInsideIfForWhile;

    // is current node in the LHS of an assignment
    boolean isLHS;

    // string class pointer
    ClassDeclNode _stringClass; 
    // Boolean class pointer
    ClassDeclNode _BooleanClass; 
    // Character class pointer
    ClassDeclNode _CharacterClass; 
    // Integer class pointer
    ClassDeclNode _IntegerClass; 

    private static final Logger logger = LogManager.getLogger(CodePrinterVisitor.class);

    /*******************************************************************************
     * CodePrinterVisitor constructor
     * time : O(1)
     * *****************************************************************************/
    public CodePrinterVisitor(){
        super();
        _outputFileName="";
        _outputDirectory="output";
        interf = new CodeGenHelper();
        _codeChunk=new CodeChunk(this);
        _interfaceMethods = new ArrayList<>();
        _ClassTypes = new ArrayList<>();
        _SimpleArrayTypes = new ArrayList<>();
        directlyInsideIfForWhile = false;
    } // CodePrinterVisitor()

    /*******************************************************************************
     * Computes interface methods
     * time : O(|ASTTree| * #methods declared/inherited)
     * *****************************************************************************/
    public static void computeInterfaceMethods(List<ASTTree> trees){
        for(ASTTree t: trees){
            if(t.isInterface()) {
                for(MethodData m : t.getMethodsDeclared()){
                    _interfaceMethods.add(m);
                } // for
                for(MethodData m : t.getMethodsInherited()){
                    _interfaceMethods.add(m);
                } // for
            } // if
        } // for
    } // computeInterfaceMethods()

    /*******************************************************************************
     * Computes subtypes
     * time : O(|ASTTree|)
     * *****************************************************************************/
    public void computeSubtypes(List<ASTTree> trees){
        for(ASTTree t: trees){
            _ClassTypes.add(t);
        } // for
        String [] basicTypes = {"int", "char","byte", "short", "boolean"};
        for(String t: basicTypes){
            _SimpleArrayTypes.add(t);
        } // for
    } // computeSubtypes()

    /*******************************************************************************
     * Computes vtable, SI-column, subtype-column
     * notes: t is current tree, isArray indicates if this is for an array or not
     * *****************************************************************************/
    private void initializeTables(List<MethodData> methods, String vtable, String name, boolean isArray,
                                  boolean isSimpleType, ASTTree t) throws ASTException{
        int numMeth = methods.size();
        interf.setOffset(0);
        _codeChunk.printLine("mov eax, " + (numMeth+3)*4 + " ; allocate memory for vtable");
        _codeChunk.printLine(interf.callMalloc());
        _codeChunk.printLine("mov [" + vtable + "], eax\npush eax");
        interf.setOffset(12); // slot 0 is SItable address, slot 4 is SubtypeTable address, slot 8 is subtype index
        for(MethodData m : methods){
            _codeChunk.printLine(interf.storeMethod(m));
        } // for

        // compute selector indexed column
        _codeChunk.printLine("mov eax, " + _interfaceMethods.size()*4 + " ; allocate memory for SI-column");
        _codeChunk.printLine(interf.callMalloc());
        _codeChunk.printLine("pop ebx\nmov [ebx], eax ; put address of SI column at first slot in vtable\npush ebx");
        interf.setOffset(0);
        for(MethodData interfMeth: _interfaceMethods){
            boolean isImplemented = false;
            for(MethodData m: methods){
                if(!isImplemented && m.isSameSignature(interfMeth)){
                    _codeChunk.printLine(interf.storeInterfaceMethod(m, interfMeth));
                    isImplemented = true;
                } // if
            } // for
            if(!isImplemented) _codeChunk.printLine(interf.storeInterfaceMethod(null, interfMeth));
        } // for

        // compute subtype column
        _codeChunk.printLine("mov eax, " + (_ClassTypes.size()*2+_SimpleArrayTypes.size())*4
                + " ; allocate memory for Subtype-column");
        _codeChunk.printLine(interf.callMalloc());
        _codeChunk.printLine("pop ebx\nmov [ebx+4], eax ; put address of Subtype column at second slot in vtable\npush ebx");
        interf.setOffset(0);
        if(isArray && isSimpleType) {
            for(String type: _SimpleArrayTypes){
                // is name a subtype of type?
                boolean isSubtype = false;
                if(name.equals(type)) isSubtype = true;
                else if(type.equals("short")){
                    if(name.equals("byte")) isSubtype = true;
                } else if(type.equals("int")){
                    if(name.equals("short") || name.equals("char")) isSubtype = true;
                } // else if
                _codeChunk.printLine(interf.storeSubtypeBit(isSubtype));
            } // for
            for(ASTTree classType: _ClassTypes){
                // is type[] a subtype of classType ?
                boolean isSubtype = (classType == objectClass) || (classType == cloneableInterface)
                        || (classType == serializableInterface);
                _codeChunk.printLine(interf.storeSubtypeBit(classType, isSubtype, false));
            } // for
        } else if(isArray && !isSimpleType){
            for(String type: _SimpleArrayTypes){
                _codeChunk.printLine(interf.storeSubtypeBit(false));
            } // for
            for(ASTTree classType: _ClassTypes){
                // is type[] a subtype of classType ?
                boolean isSubtype = (classType == objectClass) || (classType == cloneableInterface)
                        || (classType == serializableInterface);
                // is type[] a subtype of classType[] ?
                boolean isArraySubtype = false;
                if(t == classType || t.getAllSuper().contains(classType)) isArraySubtype = true;
                _codeChunk.printLine(interf.storeSubtypeBit(classType, isSubtype, isArraySubtype));
            } // for
        } else { 
            // if class type
            for(String type: _SimpleArrayTypes){
                _codeChunk.printLine(interf.storeSubtypeBit(false));
            } // for
            for(ASTTree classType: _ClassTypes){
                // is t a subtype of classType ?
                boolean isSubtype = false;
                if(t == classType || t.getAllSuper().contains(classType)) isSubtype = true;
                _codeChunk.printLine(interf.storeSubtypeBit(classType, isSubtype, false));
            } // for
        } // else
        int index = 0;
        if(isSimpleType){
            int numSimpleArrayTypes = _SimpleArrayTypes.size();
            for(int i = 0; i < numSimpleArrayTypes; ++i){
                if(name.equals(_SimpleArrayTypes.get(i))) {
                    index = i;
                    break;
                } // if
            } // for
        } else {
            index = _SimpleArrayTypes.size();
            int numClassTypes = _ClassTypes.size();
            for(int i = 0; i < numClassTypes; ++i){
                if(t == _ClassTypes.get(i)) {
                    if(isArray) index += 2 * i + 1;
                    else index += 2 * i;
                    break;
                } // if
            } // for
        } // else
        _codeChunk.printLine("pop ebx\nmov edx, " + (4*index) + "\nmov [ebx+8], edx ; store index in Subtype column at third slot in vtable");
    } // initializeTables()

    /*******************************************************************************
     * Initializes class t if !isArray, initializes array of class t if isArray
     * notes: if isPreliminary then just creates address for class and sets offset
     *        if not isPreliminary then initializes static fields
     * *****************************************************************************/
    private void initializeClass(ASTTree t, boolean isArray, boolean isPreliminary) throws ASTException{
        ClassDeclNode node = t.getRoot().TypeDeclaration().ClassDeclaration();
        String classLabel = interf.ClassLabel(node);
        String vtable = interf.VtableLabel(node);
        if(isArray){
            String vtableArray = interf.VtableLabelArray(classLabel);
            String classArray = interf.ArrayLabel(classLabel);
            _codeChunk.printCommentLine("Start Class Array Initialization for " + classArray);

            // methods in the vtable for array
            List<MethodData> objmethods = objectClass.getMethodsDeclared();
            initializeTables(objmethods, vtableArray, classArray, true, false, t);
            _codeChunk.printCommentLine("END Class Array Initialization for " + classLabel);
        } else if(isPreliminary) {
            _codeChunk.printCommentLine("Start Class Preliminary Initialization for " + classLabel);
            int offset = 4; // accounts for vtable ptr
            for (FieldData fieldData : t.getFieldsDeclared()) {
                if (fieldData.isStatic()) {
                    FieldDeclNode fieldDeclNode = (FieldDeclNode) fieldData.getDecl();
                    fieldDeclNode.setOffset(offset);
                    offset += 4;
                } // if
            } // for
            _codeChunk.printLine("mov eax, " + offset + "\n" + interf.callMalloc());
            _codeChunk.printLine("mov [" + classLabel + "], eax ; store address of class");
            _codeChunk.printCommentLine("END Class Preliminary Initialization for " + classLabel);
            _codeChunk.printCommentLine("Start static methods for " + classLabel);

            // methods in the vtable
            List<MethodData> methods = new ArrayList<>();
            computeVtable(node.getEnv().getRoot(), methods);
            initializeTables(methods, vtable, classLabel, false, false, t);
            _codeChunk.printCommentLine("End static methods for " + classLabel);
        } else {
            _codeChunk.printCommentLine("Start initialize static fields for " + classLabel);
            for (FieldData fieldData : t.getFieldsDeclared()) {
                if (fieldData.isStatic()) {
                    FieldDeclNode fieldDeclNode = (FieldDeclNode) fieldData.getDecl();
                    visit(fieldDeclNode);
                } // if
            } // for
            _codeChunk.printCommentLine("END initialize static fields for " + classLabel);
        } // else
    } //// initializeClass()

    /*******************************************************************************
     * Initializes class t if !isArray, initializes array of class t if isArray
     * notes: if isPreliminary then just creates address for class and sets offset
     * if not isPreliminary then initializes static fields
     * *****************************************************************************/
    private void initializeInterfaceArray(ASTTree t) throws ASTException{
        InterfaceDeclNode node = t.getRoot().TypeDeclaration().InterfaceDeclaration();
        String classLabel = interf.InterfaceLabel(node);
        String vtableArray = interf.VtableLabelArray(classLabel);
        String classArray = interf.ArrayLabel(classLabel);
        _codeChunk.printCommentLine("Start Interface Array Initialization for " + classArray);

        // methods in the vtable for array
        List<MethodData> objmethods = objectClass.getMethodsDeclared();
        initializeTables(objmethods, vtableArray, classArray, true, false, t);
        _codeChunk.printCommentLine("END Interface Array Initialization for " + classLabel);
    } // initializeInterfaceArray()

    /*******************************************************************************
    * Starts the initialization process
    * *****************************************************************************/
    public void startInitialization(List <ASTTree> trees) throws  ASTException{
        // object class
        objectClass = null; 
        // cloneable interface
        cloneableInterface = null; 
        // serializable interface
        serializableInterface = null; 

        _outputFileName = "_Initialization";
        interf.setQualifiedFileName(_outputFileName, null);

        String startLabel = "_start";
        _codeChunk.printLine("global " + startLabel);
        _codeChunk.printLabel(startLabel);
        _codeChunk.printLine("extern __malloc\nextern __exception");

        _codeChunk.printLine("section .data");
        String thisLabel = interf.getThisLabel();
        _codeChunk.printLine("global " + thisLabel);
        _codeChunk.printLabel(thisLabel);
        _codeChunk.printLine("dd 0");
        for(ASTTree t: trees) {
            if (t.isClass()) {
                if(t.getRoot().getEnv().getPackage().toString().equals("java.lang") &&
                        t.getRoot().getEnv().getScopePair().getName().equals("Object")) objectClass = t;
                ClassDeclNode node = t.getRoot().TypeDeclaration().ClassDeclaration();
                if(node == null) throw new Error();
                //store ptrs to special classes
                if (node.getEnv().getPackage().toString().equals("java.lang")){
                    if(node.Identifier().equals("String")) _stringClass=node;
                    else if(node.Identifier().equals("Boolean")) _BooleanClass=node;
                    else if(node.Identifier().equals("Character")) _CharacterClass=node;
                    else if(node.Identifier().equals("Integer")) _IntegerClass=node;
                } // if

                String vtable = interf.VtableLabel(node);
                String classLabel = interf.ClassLabel(node);
                String vtableArray = interf.VtableLabelArray(classLabel);
                String classArray = interf.ArrayLabel(classLabel);
                _codeChunk.printCommentLine("CLASS global declaration");
                _codeChunk.printLine("global " + classLabel);
                _codeChunk.printLabel(classLabel);
                _codeChunk.printLine("dd 0");
                _codeChunk.printLine("global " + vtable);
                _codeChunk.printLabel(vtable);
                _codeChunk.printLine("dd 0");

                // create space for static fields
                for(FieldData fieldData: t.getFieldsDeclared()){
                    if(fieldData.isStatic()){
                        String fieldLabel=interf.fieldLabel((FieldDeclNode)fieldData.getDecl());
                        _codeChunk.printLine("global " + fieldLabel);
                        _codeChunk.printLabel(fieldLabel);
                        _codeChunk.printLine("dd 0; allocate space for field");
                    } // if
                } // for

                // set offsets for nonstatic fields
                int offset = 4; // accounts for vtable ptr
                for(FieldData f: node.getEnv().getRoot().getSuperFieldsDeclared()){
                    FieldDeclNode fieldDecl = (FieldDeclNode) f.getDecl();
                    fieldDecl.setOffset(offset);
                    offset += 4;
                } // for

                // set offsets for methods
                for(MethodData m: t.getMethodsDeclared()) {
                    List<FormalParameterNode> list = m.getFormalParameterlist();
                    offset = 4 * list.size(); // accounts for vtable ptr
                    // compute and store arguments on stack
                    for(int i =0; i < list.size(); i++){
                        list.get(i).setOffset(offset);
                        offset -= 4;
                    } // for
                } // for

                // set offsets for constructors
                for(MethodData m: t.getConstructorsDeclared()) {
                    List<FormalParameterNode> list = m.getFormalParameterlist();
                    offset = 4 * list.size(); // accounts for vtable ptr
                    // compute and store arguments on stack
                    for(int i =0; i < list.size(); i++){
                        list.get(i).setOffset(offset);
                        offset -= 4;
                    } // for
                } // for

                _codeChunk.printCommentLine("ARRAY global declaration");
                _codeChunk.printLine("global " + classArray);
                _codeChunk.printLabel(classArray);
                _codeChunk.printLine("dd 0");
                _codeChunk.printLine("global " + vtableArray);
                _codeChunk.printLabel(vtableArray);
                _codeChunk.printLine("dd 0");
            } else if (t.isInterface()){
                if(t.getRoot().getEnv().getPackage().toString().equals("java.lang") &&
                        t.getRoot().getEnv().getScopePair().getName().equals("Cloneable")) cloneableInterface = t;
                else if (t.getRoot().getEnv().getPackage().toString().equals("java.io") &&
                        t.getRoot().getEnv().getScopePair().getName().equals("Serializable")) serializableInterface = t;
                String classLabel = interf.InterfaceLabel(t.getRoot().TypeDeclaration().InterfaceDeclaration());
                String vtableArray = interf.VtableLabelArray(classLabel);
                String classArray = interf.ArrayLabel(classLabel);
                _codeChunk.printCommentLine("ARRAY (of Interfaces) global declaration");
                _codeChunk.printLine("global " + classArray);
                _codeChunk.printLabel(classArray);
                _codeChunk.printLine("dd 0");
                _codeChunk.printLine("global " + vtableArray);
                _codeChunk.printLabel(vtableArray);
                _codeChunk.printLine("dd 0");
            } // else if
        } // for

        for(String type: _SimpleArrayTypes) {
            String arraylabel = interf.ArrayLabel(type);
            String vtableArrayLabel = interf.VtableLabelArray(type);
            _codeChunk.printCommentLine("CLASS global declaration");
            _codeChunk.printLine("global " + arraylabel);
            _codeChunk.printLabel(arraylabel);
            _codeChunk.printLine("dd 0");
            _codeChunk.printLine("global " + vtableArrayLabel);
            _codeChunk.printLabel(vtableArrayLabel);
            _codeChunk.printLine("dd 0");
        } // for
        _codeChunk.printLine("section .text");
        _codeChunk.printLine("mov eax, 4\n" + interf.callMalloc() +"\nmov ["+ interf.getThisLabel()+"], eax ; allocate memory for THIS ptr");

        // preliminary class initialization
        for(ASTTree t: trees){
            if(t.isClass()) initializeClass(t,false,true);
        } // for

        // initialize arrays of simple type
        for(String type: _SimpleArrayTypes){
            String arraylabel = interf.ArrayLabel(type);
            String vtableArrayLabel = interf.VtableLabelArray(type);
            _codeChunk.printCommentLine("Start Class Array Initialization for " + arraylabel);

            // methods in the vtable for array
            List<MethodData> objmethods = objectClass.getMethodsDeclared();
            initializeTables(objmethods, vtableArrayLabel, type, true, true,null);
            _codeChunk.printCommentLine("END Class Array Initialization for " + arraylabel);
        } // for

        // initialize class arrays
        for(ASTTree t: trees){
            if(t.isClass()) {
                initializeClass(t, true, false);
            } else if(t.isInterface()){
                initializeInterfaceArray(t);
            } // else if
        } // for

        // initialize classes
        for(ASTTree t: trees){
            if(t.isClass()) {
                initializeClass(t, false, false);
            } // if
        } // for

        // call
        if(trees.size() > 0) {
            if (!ContainsTestMethod(trees.get(0))){
                logger.error("method: static int test() not found in first input file");
            } // if
            CompilationUnitNode firstFile = trees.get(0).getRoot();
            String className = firstFile.getEnv().getScopePair().getName();
            String qualifiedFileName = firstFile.getEnv().getPackage().toString() + "." + className;
            String label = "_" + qualifiedFileName + "_METHOD_test#";
            _codeChunk.printLine("extern " + label);
            _codeChunk.printLine(interf.storeRegs());
            _codeChunk.printLine("call " + label + " ; call starter-method test");
            _codeChunk.printLine(interf.loadRegs(0));
            _codeChunk.printLine("mov ebx, eax \nmov eax, 1 ; sys_exit\nint 0x80\n\n");
        } // if

        // code for converting null to string
        _codeChunk.printLine("global " + interf.nullStringLabel());
        _codeChunk.printLabel(interf.nullStringLabel());
        _codeChunk.printLine(interf.methodPrologue());
        createStringObjectFromNull(false);
        _codeChunk.printLine(interf.returnCode());
    } // startInitialization()

    /*******************************************************************************
     * Getter for output file name
     * time : O(1)
     * *****************************************************************************/    
    public String OutputFileName(){
        return _outputFileName;
    } // OutputFileName()

    /*******************************************************************************
     * Setter for output file name
     * time : O(1)
     * *****************************************************************************/   
    public void OutputFileName(String outputFileName){
        _outputFileName=outputFileName;
    } // OutputFileName(String)

    /*******************************************************************************
     * Getter for output directory
     * time : O(1)
     * *****************************************************************************/   
    public String OutputDirectory(){
        return _outputDirectory;
    } // OutputDirectory()

    /*******************************************************************************
     * Setter for output directory
     * time : O(1)
     * *****************************************************************************/   
    public void OutputDirectory(String outputDirectory){
        _outputDirectory=outputDirectory;
    } // OutputDirectory(String)

    /*******************************************************************************
     * Prints code to file
     * time : O(|code|)
     * *****************************************************************************/   
    public void printToFile(String code) throws CodePrinterVisitorException{
        String path =  _outputDirectory + File.separator + _outputFileName+".s";
        new File(_outputDirectory).mkdirs();
        File file = new File(_outputDirectory + File.separator +_outputFileName+".s");
        try {
            BufferedWriter output;
            if(file.isFile()){
                output = new BufferedWriter(new FileWriter(file, true));
            } else {
                output = new BufferedWriter(new FileWriter(file));
            } // else
            output.write(code);
            output.close();
        } catch (IOException e){
            logger.error("Unable to write to file: " + _outputDirectory + File.separator + _outputFileName +".s");
            logger.error(file.getAbsolutePath());
            throw new CodePrinterVisitorException();
        } // catch
    } // printToFile()

    /*******************************************************************************
     * Computes set of methodData for methods in the vtable for root
     * notes: stores the result in methods
     * *****************************************************************************/   
    private void computeVtable(ASTTree root, List<MethodData> methods){
        ASTTree superClass = root.getSuperClass();
        // computes vtable methods for superclass (if there is any superclass)
        if(superClass != null) computeVtable(superClass, methods);

        for(MethodData m: root.getMethodsDeclared()){
            // if there is a method m2 in the vtable for the superclass with (m,m2) in REPLACE
            // then replace m2 with m in methods
            boolean foundInReplace = false;
            int numReplaceMethods = root.getReplaceMethods().size();
            for(int i = 0; i < numReplaceMethods; ++i){
                if(m == root.getReplaceMethods().get(i).firstElement()){
                    int numMethods = methods.size();
                    for(int j = 0; j < numMethods; ++j){
                        if(root.getReplaceMethods().get(i).lastElement() == methods.get(j)){
                            // m replaces another method m2
                            foundInReplace = true;
                            methods.set(j, m);
                        } // if
                    } // for
                } // if
            } // for
            if(!foundInReplace) {
                // if m did not replace any method, add m to methods
                methods.add(m);
            } // if
        } // for
    } // computeVtable()

    /*******************************************************************************
     * Gets code for assignability
     * time: O(1)
     * *****************************************************************************/   
    private void assignabilityHelper(int offset, String name) throws ASTException{
        String noError = interf.generalLabel("endTypeCheck");
        _codeChunk.printLine("; type Assignability\ncmp eax, 0; is eax NULL?\nje " + noError);
        _codeChunk.printLine("mov eax, [eax] ; get address of vtable\nadd eax, 4");
        _codeChunk.printLine("mov eax, [eax] ; get address of subtype column");
        _codeChunk.printLine("mov eax, [eax + " + offset + "] ; get subtype bit for " + name);
        _codeChunk.printLine("cmp eax, 0\njne " + noError + "\ncall __exception ; not type assignable");
        _codeChunk.printLabel(noError);
    } // assignabilityHelper()

    /*******************************************************************************
     * Is object stored at eax type assignable to classType []_isarray?
     * *****************************************************************************/   
    private void isTypeAssignable(ClassDeclNode classType, boolean isArray) throws ASTException{
        int arrayOffset = isArray ? 4 : 0;
        assignabilityHelper(classType.getOffset() + arrayOffset, classType.Identifier());
    } // isTypeAssignable(ClassDeclNode.boolean)

    /*******************************************************************************
     * Is object stored at eax type assignable to InterfaceType []_isarray?
     * *****************************************************************************/  
    private void isTypeAssignable(InterfaceDeclNode interfType, boolean isArray) throws ASTException{
        int arrayOffset = isArray ? 4 : 0;
        assignabilityHelper(interfType.getOffset() + arrayOffset, interfType.Identifier());
    } // isTypeAssignable(InterfaceDeclNode.boolean)

    /*******************************************************************************
     * Is object stored at eax type assignable to IsimplyType?
     * *****************************************************************************/ 
    private void isTypeAssignable(String simpleType) throws ASTException{
        // get index of simpleType
        int index = 0;
        int numSimpleTypes = _SimpleArrayTypes.size();
        for(int i = 0; i < numSimpleTypes; ++i){
            if(_SimpleArrayTypes.get(i).equals(simpleType)) {
                index = i;
                break;
            } // if
        } // for
        assignabilityHelper(index*4, simpleType);
    } // isTypeAssignable(String)

    /*******************************************************************************
     * Is eax[] := ebx allowed? (where they are reference types 
     * and eax is address of array)
     * *****************************************************************************/ 
    private void checkTypeAssignability() throws ASTException{
        _codeChunk.printLine("push eax\npush ebx");
        String noError = interf.generalLabel("noTypeAssignabilityError");
        _codeChunk.printCommentLine("is LEFT:= RIGHT?"); ;
        _codeChunk.printLine("cmp ebx, 0\nje " + noError + "; if RIGHT is null, no error");
        _codeChunk.printLine("cmp eax, 0\nje " + noError + "; if LEFT is null, no error"); // ******** NOT SURE
        _codeChunk.printLine("mov eax, [eax] ; get vtable of LEFT");
        _codeChunk.printLine("mov eax, [eax+8] ; get index of array of type of LEFT");
        _codeChunk.printLine("sub eax, 4 ; get index of type of LEFT");
        _codeChunk.printLine("mov ebx, [ebx] ; get vtable of RIGHT");
        _codeChunk.printLine("mov ebx, [ebx+4] ; get Subtype column of RIGHT");
        _codeChunk.printLine("add ebx, eax ; get address of subtype bit");
        _codeChunk.printLine("mov eax, [ebx] ; get Subtype bit");

        _codeChunk.printLine("cmp eax, 0\njne " + noError + "\ncall __exception ; not type assignable");
        _codeChunk.printLabel(noError);
        _codeChunk.printLine("pop ebx\npop eax");
    } // checkTypeAssignability()

    /*******************************************************************************
     * OVER-RIDDEN VISIT METHODS
     * *****************************************************************************/ 
    @Override
    public void visit(CompilationUnitNode node) throws ASTException{
        // print no output for empty files or interfaces
        if(node.TypeDeclaration() == null || node.TypeDeclaration().InterfaceDeclaration() != null) return;
        String className = node.getEnv().getScopePair().getName();
        _outputFileName = className;
        _outputDirectory = "output";
        interf.setQualifiedFileName(node.getEnv().getPackage().toString() + "." + _outputFileName, node.getEnv().getRoot());
        _codeChunk.printLine(interf.getStandardExterns());
        logger.debug("START " + _outputFileName);
        super.visit(node);
        logger.debug("DONE " + _outputFileName);
    } // visit(CompilationUnitNode)

    @Override
    public void visit(IfStmtNode ifStmtNode) throws iVisitor.ASTException{
        _codeChunk.printCommentLine("Start Ifstmt node");
        int offsetBefore = interf.getOffset();
        visit(ifStmtNode.Expression());
        if(ifStmtNode.Statements().size() == 1){ //if then
            String endLabel = interf.generalLabel("IfThenStmt");
            _codeChunk.printLine("mov ebx, 0");
            _codeChunk.printLine("cmp eax, ebx");
            _codeChunk.printLine("je " + endLabel);
            interf.setOffset(offsetBefore-4);
            _codeChunk.printLine(interf.blockStart());
            directlyInsideIfForWhile = true;
            visit(ifStmtNode.Statements().get(0));
            directlyInsideIfForWhile = false;
            _codeChunk.printLine(interf.blockEnd());
            interf.setOffset(offsetBefore);
            _codeChunk.printLabel(endLabel);
        } else { 
            //if then else
            String elseLabel = interf.generalLabel("ElseStmt");
            String endLabel = interf.generalLabel("IfStmtEnd");
            _codeChunk.printLine("mov ebx, 0");
            _codeChunk.printLine("cmp eax, ebx");
            _codeChunk.printLine("je " + elseLabel);

            interf.setOffset(offsetBefore-4);
            _codeChunk.printLine(interf.blockStart());
            directlyInsideIfForWhile = true;
            visit(ifStmtNode.Statements().get(0));
            directlyInsideIfForWhile = false;
            _codeChunk.printLine(interf.blockEnd());
            interf.setOffset(offsetBefore);
            _codeChunk.printLine("jmp " + endLabel);

            _codeChunk.printLabel(elseLabel);
            interf.setOffset(offsetBefore-4);
            _codeChunk.printLine(interf.blockStart());
            directlyInsideIfForWhile = true;
            visit(ifStmtNode.Statements().get(1));
            directlyInsideIfForWhile = false;
            _codeChunk.printLine(interf.blockEnd());
            interf.setOffset(offsetBefore);

            _codeChunk.printLabel( endLabel);
        } // else
        _codeChunk.printCommentLine("END Ifstmt node");
    } // visit(IfStmtNode)

    @Override
    public void visit(StatementNode node) throws ASTException{
        if(directlyInsideIfForWhile){
            if(node.NoTrailingStmt() == null || node.NoTrailingStmt().Block() == null) directlyInsideIfForWhile = false;
        } // if
        super.visit(node);
    } // visit(StatementNode)

    @Override
    public void visit(BlockNode node) throws ASTException{
        int offsetBefore = interf.getOffset();
        boolean oldFlag = directlyInsideIfForWhile;
        directlyInsideIfForWhile = false;
        if(!oldFlag) {
            interf.setOffset(offsetBefore-4);
            _codeChunk.printLine(interf.blockStart());
        } // if
        super.visit(node);
        if(!oldFlag) {
            interf.setOffset(offsetBefore);
            _codeChunk.printLine(interf.blockEnd());
        } // if
    } // visit(BlockNode)

    @Override
    public void visit(WhileStmtNode node) throws ASTException{
        _codeChunk.printCommentLine("Start WhileStmt node");
        String labelWhile = interf.generalLabel("WhileLoop");
        String endLabel = interf.generalLabel("doneWhile");
        int offsetBefore = interf.getOffset();

        directlyInsideIfForWhile = true;
        _codeChunk.printLabel(labelWhile);
        visit(node.Expression());
        _codeChunk.printLine("mov ebx, 0");
        _codeChunk.printLine("cmp eax, ebx");
        _codeChunk.printLine("je " + endLabel);

        _codeChunk.printLine(interf.blockStart());
        interf.setOffset(offsetBefore-4);
        visit(node.Statement());
        _codeChunk.printLine(interf.blockEnd());
        interf.setOffset(offsetBefore);

        _codeChunk.printLine("jmp " + labelWhile);
        _codeChunk.printLabel(endLabel);

        directlyInsideIfForWhile = false;
        _codeChunk.printCommentLine("END WhileStmt node");
    } // visit(WhileStmtNode)

    @Override
    public void visit(ForStmtNode forStmtNode) throws iVisitor.ASTException{
        _codeChunk.printCommentLine("Start forstmtnode");
        
        String labelWhile = interf.generalLabel("ForLoop");
        String labelDone = interf.generalLabel("doneFor");

        if(forStmtNode.StatementExpression() != null){
            visit(forStmtNode.StatementExpression()); //initialization
        } // if
        if (forStmtNode.LocalVariableDeclaration() != null){
            visit(forStmtNode.LocalVariableDeclaration());
        } // if
        int offsetBefore = interf.getOffset();
        directlyInsideIfForWhile = true;

        _codeChunk.printLabel(labelWhile);
        visit(forStmtNode.Expression()); // condition
        _codeChunk.printLine("mov ebx, 0");
        _codeChunk.printLine("cmp eax, ebx");
        _codeChunk.printLine( "je " + labelDone);
        interf.setOffset(offsetBefore-4);
        _codeChunk.printLine(interf.blockStart());
        visit(forStmtNode.Statement()); // action
        if(forStmtNode.Forupdate() != null){
            visit(forStmtNode.Forupdate()); // increment
        } // if
        _codeChunk.printLine(interf.blockEnd());
        interf.setOffset(offsetBefore);
        _codeChunk.printLine( "jmp " + labelWhile);
        _codeChunk.printLabel(labelDone);
        _codeChunk.printCommentLine("END forstmtnode");
        directlyInsideIfForWhile = false;
    } // visit(ForStmtNode)

    @Override
    public void visit(NameNode node) throws iVisitor.ASTException{
        // no code to generate
        if(node.getPrefixes().size() == 0) return; 

        _codeChunk.printCommentLine("Start NameNode");

        // class of prefix (current class if no prefix)
        ClassDeclNode currentClass = node.getEnv().getRoot().getRoot().TypeDeclaration().ClassDeclaration();
        boolean isArray = false;
        // is this first prefix that might not be a type/package name?
        boolean firstPrefix = true;
        for(NameNode prefix: node.getPrefixes()){
            if(!firstPrefix) _codeChunk.printLine("mov eax, [eax]; load value of LHS");
            if(isArray) {
                if(!node.getSimple().equals("length")) throw new Error();
                _codeChunk.printLine("mov eax, [eax+4]; load length of array");
                return;
            } // if
            ASTNode decl = prefix.getLinkedDeclaration();
            TypeNode type = null;
            if(decl instanceof LocalVariableDeclNode){
                _codeChunk.printLine(interf.loadLocal((LocalVariableDeclNode) decl));
                type = ((LocalVariableDeclNode) decl).Type();
            } else if (decl instanceof FormalParameterNode){
                _codeChunk.printLine(interf.loadArgument((FormalParameterNode) decl));
                type = ((FormalParameterNode) decl).Type();
            } else if (decl instanceof  FieldDeclNode) {
                FieldDeclNode field = (FieldDeclNode) decl;
                if (field.getFieldData().isStatic()) {
                    String classLabel = currentClass.getLabel();
                    String extern = "";
                    if(!_outputFileName.equals("_Initialization")) extern = "extern "+ classLabel + "\n";
                    _codeChunk.printLine(extern + "mov eax, [" + classLabel + "] ; get class address\nadd eax, " + field.getOffset()
                            + " ; get address of static field " + field.getFieldData().getFieldName());
                } else {
                    // nonstatic field
                    String noError = interf.generalLabel("noError");
                    if (firstPrefix) {
                        _codeChunk.printLine("mov eax, [" + interf.getThisLabel() + "]\nmov eax, [eax] ; get THIS for nonstatic field access");
                    } // if

                    // eax contains address of object
                    _codeChunk.printLine("mov ebx, 0\ncmp eax, ebx\njne " + noError + "\ncall __exception ; nullpointerexception (field access)");
                    _codeChunk.printLabel(noError);
                    _codeChunk.printLine("add eax, " + field.getOffset() + " ; get address of nonstatic field "
                            + field.getFieldData().getFieldName());
                } // else
                type = field.getFieldData().getType();
            } else { 
                // if package or type
                currentClass = null;
                if(decl instanceof ClassDeclNode) { // if type
                    currentClass = (ClassDeclNode) decl;
                }
                isArray = false;
                firstPrefix = true;
                continue;
            } // else
            if(type.ArrayType() != null) {
                isArray = true;
                currentClass = null;
            } else if(type.ClassOrInterfaceType() != null){
                if(type.ClassOrInterfaceType().Name().getLinkedDeclaration() instanceof ClassDeclNode) {
                    currentClass = (ClassDeclNode) type.ClassOrInterfaceType().Name().getLinkedDeclaration();
                } else { 
                    // interface
                    currentClass = null;
                } // else
            } else { 
                // type is BasicType
                currentClass = null;
            } // else
            firstPrefix = false;
        } // for
        if(!isLHS) _codeChunk.printLine("mov eax, [eax] ; not LHS so get value");
        _codeChunk.printCommentLine("END NameNode");
    } // visit(NameNode)

    @Override
    public void visit(BinOpExprNode binOpExprNode) throws iVisitor.ASTException{
        _codeChunk.printCommentLine("Start BinOpExprNode");
        String endLabel=interf.generalLabel("BinOpEndLabel");
        if(binOpExprNode.UnaryExpr() !=  null){ 
            // unary expression
            visit(binOpExprNode.UnaryExpr());
        } else { 
            // binary expression
            visit(binOpExprNode.LeftExpr());
            if(binOpExprNode.RightExpr() != null){
                if (binOpExprNode.Operator() == BinOpExprNode.OPER_AMPAMP){
                    _codeChunk.printCommentLine("Start AMP AMP LEFT");
                    String notTrueLabel=interf.generalLabel("AMPAMPLeftNotTrue");
                    String trueLabel=interf.generalLabel("AMPAMPLeftTrue");
                    _codeChunk.printLine( "cmp eax, 1");
                    _codeChunk.printLine("jne " + notTrueLabel);
                    _codeChunk.printLine("jmp " + trueLabel);
                    _codeChunk.printLabel(notTrueLabel);
                    _codeChunk.printLine("mov eax,0");
                    _codeChunk.printLine("jmp " + endLabel);
                    _codeChunk.printLabel(trueLabel);
                    _codeChunk.printCommentLine("END AMP AMP LEFT");
                } else if (binOpExprNode.Operator() == BinOpExprNode.OPER_OROR){
                    _codeChunk.printCommentLine("Start OR OR LEFT");
                    String notTrueLabel=interf.generalLabel("ORORLeftNotTrue");
                    String trueLabel=interf.generalLabel("ORORLeftTrue");
                    _codeChunk.printLine( "cmp eax, 1");
                    _codeChunk.printLine("je " + trueLabel);
                    _codeChunk.printLine("jmp " + notTrueLabel);
                    _codeChunk.printLabel(trueLabel);
                    _codeChunk.printLine("mov eax,1");
                    _codeChunk.printLine("jmp " + endLabel);
                    _codeChunk.printLabel(notTrueLabel);
                    _codeChunk.printCommentLine("END OR OR LEFT");
                } // else if
                _codeChunk.printLine("push eax ;push leftexpr onto stack");
                visit(binOpExprNode.RightExpr());
                _codeChunk.printLine("mov ebx, eax ; move rightexpr to ebx");
                _codeChunk.printLine( "pop eax ; get leftexpr");
                // EAX = left expr; EBX = right expr
                if(binOpExprNode.Operator() == BinOpExprNode.OPER_PLUS){
                    String beginNonStringAddLabel=interf.generalLabel("beginNonStringAdd");
                    String endNonStringAddLabel=interf.generalLabel("endNonStringAdd");
                    TypeNode leftTypeNode=(TypeNode)binOpExprNode.LeftExpr().getType();
                    TypeNode rightTypeNode=(TypeNode)binOpExprNode.RightExpr().getType();

                    ClassDeclNode classDeclNode=binOpExprNode.getEnv().getRoot().getRoot().TypeDeclaration().ClassDeclaration();
                    if(leftTypeNode.isString() || rightTypeNode.isString()){
                        if(leftTypeNode.isString()){
                            _codeChunk.printLine("push eax ; save left string");
                            _codeChunk.printLine("mov eax, ebx");
                            if (rightTypeNode.isInt() || rightTypeNode.isShort() || rightTypeNode.isByte()){
                                createStringObject(_IntegerClass,"_java.lang.Integer_CONSTRUCTOR_Integer#int#",classDeclNode == _IntegerClass);
                            } else if (rightTypeNode.isBoolean()){
                                createStringObject(_BooleanClass,"_java.lang.Boolean_CONSTRUCTOR_Boolean#boolean#",classDeclNode == _BooleanClass);
                            } else if (rightTypeNode.isChar()) {
                                createStringObject(_CharacterClass,"_java.lang.Character_CONSTRUCTOR_Character#char#",classDeclNode == _CharacterClass);
                            } // else if
                            _codeChunk.printLine("mov ebx, eax");
                            _codeChunk.printLine("pop eax ; load left string");
                        } else {
                            _codeChunk.printLine("push ebx ; save right string");
                            if (leftTypeNode.isInt() || leftTypeNode.isShort() || leftTypeNode.isByte()){
                                createStringObject(_IntegerClass,"_java.lang.Integer_CONSTRUCTOR_Integer#int#",classDeclNode == _IntegerClass);
                            } else if (leftTypeNode.isBoolean()){
                                createStringObject(_BooleanClass,"_java.lang.Boolean_CONSTRUCTOR_Boolean#boolean#",classDeclNode == _BooleanClass);
                            } else if (rightTypeNode.isChar()) {
                                createStringObject(_CharacterClass,"_java.lang.Character_CONSTRUCTOR_Character#char#",classDeclNode == _CharacterClass);
                            } // else if
                            _codeChunk.printLine("pop ebx ; load right string");
                        } // else
                        // if either eax or ebx is 0 (null), convert it to string
                        String notNullleft = interf.generalLabel("leftNotNull");
                        String notNullright = interf.generalLabel("rightNotNull");
                        _codeChunk.printLine("cmp eax, 0\njne " + notNullleft);
                        _codeChunk.printLine(interf.storeRegs());
                        _codeChunk.printLine("call " + interf.nullStringLabel());
                        _codeChunk.printLine(interf.loadRegs(0));
                        _codeChunk.printLabel(notNullleft);
                        callToString();

                        _codeChunk.printLine("push eax\nmov eax, ebx ; get right string");

                        _codeChunk.printLine("cmp eax, 0\njne " + notNullright);
                        _codeChunk.printLine(interf.storeRegs());
                        _codeChunk.printLine("call " + interf.nullStringLabel());
                        _codeChunk.printLine(interf.loadRegs(0));
                        _codeChunk.printLabel(notNullright);
                        callToString();
                        _codeChunk.printLine("mov ebx, eax\npop eax ; get left string");

                        //string (eax) + string (ebx)
                        //call concat
                        concatenate(classDeclNode == _stringClass);
                        _codeChunk.printLine("jmp " + endNonStringAddLabel);
                    } // if
                    _codeChunk.printLabel(beginNonStringAddLabel);
                    _codeChunk.printLine( "add eax, ebx ; +");
                    _codeChunk.printLabel(endNonStringAddLabel);
                } else if (binOpExprNode.Operator() == BinOpExprNode.OPER_MINUS){
                    _codeChunk.printLine( "sub eax, ebx ; -");
                } else if (binOpExprNode.Operator() == BinOpExprNode.OPER_MULT){
                    _codeChunk.printLine( "imul eax, ebx ; *");
                } else if (binOpExprNode.Operator() == BinOpExprNode.OPER_DIV || binOpExprNode.Operator() == BinOpExprNode.OPER_PCT){
                    String noError = interf.generalLabel("noDivisionByZero");
                    _codeChunk.printLine("cmp ebx, 0\njne " + noError + " \ncall __exception ;  division by 0");
                    _codeChunk.printLabel(noError);
                    _codeChunk.printLine("cdq");
                    _codeChunk.printLine("idiv ebx");
                    if(binOpExprNode.Operator() == BinOpExprNode.OPER_PCT) _codeChunk.printLine("mov eax, edx");
                } else if (binOpExprNode.Operator() == BinOpExprNode.OPER_AMPAMP){
                    _codeChunk.printCommentLine("Start Right of AMPAMP");
                    String notEqualLabel=interf.generalLabel("notEqualLabel");
                    String equalLabel= interf.generalLabel("EqualLabel");
                    _codeChunk.printLine("cmp ebx, 1");
                    _codeChunk.printLine("jne " + notEqualLabel);
                    _codeChunk.printLabel(equalLabel);
                    _codeChunk.printLine("mov eax, 1");
                    _codeChunk.printLine("jmp " + endLabel);
                    _codeChunk.printLabel(notEqualLabel);
                    _codeChunk.printLine("mov eax, 0");
                    _codeChunk.printCommentLine("END Right of AMPAMP");
                } else if (binOpExprNode.Operator() == BinOpExprNode.OPER_OROR){
                    _codeChunk.printCommentLine("Start Right of OROR");
                    String notEqualLabel=interf.generalLabel("notEqualLabel");
                    String equalLabel= interf.generalLabel("EqualLabel");
                    _codeChunk.printLine("cmp ebx, 1");
                    _codeChunk.printLine("jne " + notEqualLabel);
                    _codeChunk.printLabel(equalLabel);
                    _codeChunk.printLine("mov eax, 1");
                    _codeChunk.printLine("jmp " + endLabel);
                    _codeChunk.printLabel(notEqualLabel);
                    _codeChunk.printLine("mov eax, 0");
                    _codeChunk.printCommentLine("END Right of OROR");
                } else if (binOpExprNode.Operator() == BinOpExprNode.OPER_AND){
                    _codeChunk.printLine("and eax, ebx");
                } else if (binOpExprNode.Operator() == BinOpExprNode.OPER_OR){
                    _codeChunk.printLine("or eax, ebx");
                } else if (binOpExprNode.Operator() == BinOpExprNode.OPER_EQ) {
                    String label=interf.generalLabel("IfEQ");
                    _codeChunk.printLine("cmp eax, ebx");
                    _codeChunk.printLine("je " + label);
                    _codeChunk.printLine("mov eax, 0");
                    _codeChunk.printLine("jmp " + endLabel);
                    _codeChunk.printLabel(label);
                    _codeChunk.printLine("mov eax, 1");
                } else if (binOpExprNode.Operator() == BinOpExprNode.OPER_NEQ){
                    String label=interf.generalLabel("IfNEQ");
                    _codeChunk.printLine("cmp eax, ebx");
                    _codeChunk.printLine("jne " + label);
                    _codeChunk.printLine("mov eax, 0");
                    _codeChunk.printLine("jmp " + endLabel);
                    _codeChunk.printLabel(label);
                    _codeChunk.printLine("mov eax, 1");
                } else if (binOpExprNode.Operator() == BinOpExprNode.OPER_GE) {
                    String label=interf.generalLabel("IfGE");
                    _codeChunk.printLine("cmp eax, ebx");
                    _codeChunk.printLine("jge " + label);
                    _codeChunk.printLine("mov eax, 0");
                    _codeChunk.printLine("jmp " + endLabel);
                    _codeChunk.printLabel(label);
                    _codeChunk.printLine("mov eax, 1");
                } else if (binOpExprNode.Operator() == BinOpExprNode.OPER_GT) {
                    String label=interf.generalLabel("IfGT");
                    _codeChunk.printLine("cmp eax, ebx");
                    _codeChunk.printLine("jg " + label);
                    _codeChunk.printLine("mov eax, 0");
                    _codeChunk.printLine("jmp " + endLabel);
                    _codeChunk.printLabel(label);
                    _codeChunk.printLine("mov eax, 1");
                } else if (binOpExprNode.Operator() == BinOpExprNode.OPER_LT) {
                    String label=interf.generalLabel("IfLT");
                    _codeChunk.printLine("cmp eax, ebx");
                    _codeChunk.printLine("jl " + label);
                    _codeChunk.printLine("mov eax, 0");
                    _codeChunk.printLine("jmp " + endLabel);
                    _codeChunk.printLabel(label);
                    _codeChunk.printLine("mov eax, 1");
                } else if (binOpExprNode.Operator() == BinOpExprNode.OPER_LE) {
                    String label=interf.generalLabel("IfLE");
                    _codeChunk.printLine("cmp eax, ebx");
                    _codeChunk.printLine("jle " + label);
                    _codeChunk.printLine("mov eax, 0");
                    _codeChunk.printLine("jmp " + endLabel);
                    _codeChunk.printLabel(label);
                    _codeChunk.printLine("mov eax, 1");
                } // else if
            } else { 
                // instanceof
                String label=interf.generalLabel("notNull");
                _codeChunk.printLine("cmp eax, 0 ; if object is null, result is false");
                _codeChunk.printLine("je " + endLabel);
                if(binOpExprNode.ClassOrInterfaceType() != null) {
                    ASTNode linkedDecl = binOpExprNode.ClassOrInterfaceType().Name().getLinkedDeclaration();
                    if(linkedDecl instanceof ClassDeclNode) {
                        isTypeAssignable((ClassDeclNode) linkedDecl, false);
                    } else if(linkedDecl instanceof InterfaceDeclNode) {
                        isTypeAssignable((InterfaceDeclNode) linkedDecl, false);
                    } // else if
                } else if(binOpExprNode.ArrayType() != null){
                    if(binOpExprNode.ArrayType().BasicType() != null){
                        isTypeAssignable(binOpExprNode.ArrayType().BasicType().getElement());
                    } else if(binOpExprNode.ArrayType().Name() != null){
                        ASTNode linkedDecl = binOpExprNode.ArrayType().Name().getLinkedDeclaration();
                        if(linkedDecl instanceof ClassDeclNode) {
                            isTypeAssignable((ClassDeclNode) linkedDecl, true);
                        } else if(linkedDecl instanceof InterfaceDeclNode) {
                            isTypeAssignable((InterfaceDeclNode) linkedDecl, true);
                        } // else if
                    } // else if
                } // else if
            } // else
        } // else
        _codeChunk.printLabel(endLabel);
        _codeChunk.printCommentLine("END BinOpExprNode");
    } // visit(BinOpExprNode)

    @Override
    public void visit(ArrayAccessNode arrayAccessNode) throws iVisitor.ASTException{
        boolean oldIsLHS = isLHS;
        isLHS = false; // for child expressions to be visited

        _codeChunk.printCommentLine("Start Array Access node");
        if(arrayAccessNode.Name() != null) visit(arrayAccessNode.Name());
        else visit(arrayAccessNode.PrimaryNoNewArray());

        _codeChunk.printLine("push eax ; store array reference");
        visit(arrayAccessNode.Expression());

        String noErrorLabel1 = interf.generalLabel("noError");
        String noErrorLabel2 = interf.generalLabel("noError");
        String noErrorLabel3 = interf.generalLabel("noError");

        _codeChunk.printLine("mov ecx, eax ; store array expression");
        _codeChunk.printLine("add eax, 2\nshl eax, 2 \nmov edx, eax ; store 4 * (array expression+2)");
        _codeChunk.printLine("pop eax ; get array reference\npush eax");
        _codeChunk.printLine("mov ebx, 0");
        // EAX = array reference, EBX = 0, ECX = array expression, EDX = 4 * (ECX+2)
        _codeChunk.printLine("cmp eax, ebx \njne " + noErrorLabel1 + " \ncall __exception ;  nullPointerException");
        _codeChunk.printLabel(noErrorLabel1);
        _codeChunk.printLine("cmp ecx, ebx \njge " + noErrorLabel2 + " \ncall __exception ; negative array index");
        _codeChunk.printLabel(noErrorLabel2);
        _codeChunk.printLine("mov ebx, [eax+4] ; get array length");
        _codeChunk.printLine("cmp ecx, ebx \njl " + noErrorLabel3 + " \ncall __exception ; ArrayIndexOutOfBoundsException");
        _codeChunk.printLabel(noErrorLabel3);
        _codeChunk.printLine("add eax, edx ; put address of array element in eax\npop edx; put address of array in edx");
        if(! oldIsLHS) {
            // if array access does not occur in the LHS of assignment, compute the value of the element
            _codeChunk.printLine("mov eax, [eax]");
        } // if 
        // otherwise return the address of the element
        _codeChunk.printCommentLine("END Array Access node");
    } // visit(ArrayAccessNode)

    @Override
    public void visit(FieldAcccessNode node) throws iVisitor.ASTException{
        _codeChunk.printCommentLine("Start FieldAcccessNode");
        boolean oldIsLHS = isLHS;
        isLHS = false;
        visit(node.Primary());

        TypeNode type = (TypeNode) node.Primary().getType();
        if(type.ArrayType() != null){
            // length field
            _codeChunk.printLine("mov eax, [eax+4] ; get array length (field access)");
        } else if(type.ClassOrInterfaceType() != null) {
            FieldDeclNode decl = (FieldDeclNode) node.getFieldDecl();
            if(decl.getFieldData().isStatic()){
                ClassDeclNode prefixClass = (ClassDeclNode) type.ClassOrInterfaceType().Name().getLinkedDeclaration();
                String classLabel = prefixClass.getLabel();
                _codeChunk.printLine("mov eax, ["+classLabel+"] ; get class address\nadd eax, " + decl.getOffset()
                        +" ; get address of static field " + decl.getFieldData().getFieldName());
            } else {
                // nonstatic field
                String noError = interf.generalLabel("noError");
                _codeChunk.printLine("mov ebx, 0\ncmp eax, ebx\njne "+noError+"\ncall __exception ; nullpointerexception (field access)");
                _codeChunk.printLabel(noError);
                _codeChunk.printLine("add eax, " + decl.getOffset() + " ; get address of nonstatic field "
                        + decl.getFieldData().getFieldName());
            } // else 
            if(!oldIsLHS) _codeChunk.printLine("mov eax, [eax] ; not LHS so get the value");
        } // else if
        _codeChunk.printCommentLine("END FieldAcccessNode");
    } // visit(FieldAcccessNode)

    @Override
    public void visit(PrimaryNoNewArrayNode primaryNoNewArrayNode) throws iVisitor.ASTException{
        _codeChunk.printCommentLine("Start PrimaryNoNewArrayNode");
        if(primaryNoNewArrayNode.This()) {
            _codeChunk.printLine("mov eax, [" + interf.getThisLabel() + "]\nmov eax, [eax] ; get this");
        } else super.visit(primaryNoNewArrayNode);
        _codeChunk.printCommentLine("END PrimaryNoNewArrayNode");
    } // visit(PrimaryNoNewArrayNode)

    @Override
    public void visit(MethodDeclarationNode node) throws iVisitor.ASTException{
        if(node.MethodBody() == null || node.MethodBody().Block() == null) { // abstract methods should not generate code
            _codeChunk.printCommentLine("method " + interf.methodLabel(node.MethodHeader()) + " has no body");
            return;
        } // if
        directlyInsideIfForWhile = true;
        _codeChunk.printCommentLine("Start MethodDeclaration");
        super.visit(node);
        // implicit return for void methods
        if(node.MethodHeader().getMethodData().isVoid()) {
            _codeChunk.printCommentLine("implicit return at end of void method's body");
            _codeChunk.printLine(interf.returnCode());
        } // if
        directlyInsideIfForWhile = false;
        _codeChunk.printCommentLine("END MethodDeclaration");
    } // visit(MethodDeclarationNode)

    @Override
    public void visit(MethodHeaderNode methodHeaderNode) throws iVisitor.ASTException{
        _codeChunk.printCommentLine("Start MethodHeader");
        // Prologue
        interf.setOffset(-4);
        String methodLabel = interf.methodLabel(methodHeaderNode);
        _codeChunk.printLine("global " + methodLabel);
        _codeChunk.printLabel(methodLabel);
        _codeChunk.printLine(interf.methodPrologue());

        // Code for method 
        super.visit(methodHeaderNode);
        _codeChunk.printCommentLine("END MethodHeader");
    } // visit(MethodHeaderNode)

    @Override
    public void visit(ConstructorDeclarationNode node) throws iVisitor.ASTException{
        _codeChunk.printCommentLine("Start ConstructorDeclarationNode");
        interf.setOffset(-4);
        String methodLabel = interf.constructorLabel(node);
        _codeChunk.printLine("global " + methodLabel);
        _codeChunk.printLabel(methodLabel);
        _codeChunk.printLine(interf.methodPrologue());

        // Code for method
        if(node.getEnv().getRoot().getImplicitSuperConstructor() != null){
            _codeChunk.printLine(interf.storeRegs());
            _codeChunk.printLine("; calls implicit constructor for " + node.getMethodData().getMethodName());
            _codeChunk.printLine(interf.callConstructor((ConstructorDeclarationNode) node.getEnv().getRoot().getImplicitSuperConstructor().getDecl()));
            _codeChunk.printLine(interf.loadRegs(0));
        } // if
        _codeChunk.printCommentLine("initialize nonstatic fields");
        //execute field initializers
        List<FieldData> fieldDataList = node.getEnv().getRoot().getSuperFieldsDeclared();
        for(FieldData fieldData: fieldDataList){
            FieldDeclNode fieldDecl = (FieldDeclNode) fieldData.getDecl();
            String fieldLabel = interf.fieldLabel(fieldDecl);
            _codeChunk.printLine(interf.storeRegs());

            String extern = "";
            if (fieldDecl.getEnv().getRoot() != node.getEnv().getRoot()) extern = "extern "+fieldLabel+"\n";
            _codeChunk.printLine(extern + "call " + fieldLabel + " ; calls nonstatic field initializer");
            _codeChunk.printLine(interf.loadRegs(0));
        } // for

        super.visit(node);
        _codeChunk.printCommentLine("implicit return at end of constructor");
        _codeChunk.printLine(interf.returnCode());
        _codeChunk.printCommentLine("END ConstructorDeclarationNode");
    } // visit(ConstructorDeclarationNode)

    @Override
    public void visit(ClassMemberDeclNode node) throws iVisitor.ASTException{
        // do not generate code for static fields in class.s
        if(node.FieldDeclaration() != null && node.FieldDeclaration().getFieldData().isStatic()) return;
        super.visit(node);
    } // visit(ClassMemberDeclNode)

    @Override
    public void visit(FieldDeclNode node) throws iVisitor.ASTException{
        _codeChunk.printCommentLine("Start FieldDeclNode");
        
        if(!node.getFieldData().isStatic()) { 
            // nonstatic field (this method is called by object)
            String fieldLabel = interf.fieldLabel(node);
            _codeChunk.printLine("global " + fieldLabel);
            _codeChunk.printLabel(fieldLabel);
            _codeChunk.printLine(interf.methodPrologue());
            // compute value of field
            if(node.VariableDeclarator().VariableInitializer() != null) {
                visit(node.VariableDeclarator().VariableInitializer());
            } else { 
                // default initialization
                _codeChunk.printLine("mov eax, 0 ; default initialization");
            } // else
            _codeChunk.printLine("mov ebx, [" + interf.getThisLabel() + "]\nmov ebx, [ebx]");
            _codeChunk.printLine("add ebx, "+ node.getOffset() + "\nmov [ebx], eax ; initialize field");
            _codeChunk.printLine(interf.returnCode() + " ; return from nonstatic field initializer");
        } else { 
            // static field
            String classLabel=node.getEnv().getRoot().getRoot().TypeDeclaration().ClassDeclaration().getLabel();
            
            if(node.VariableDeclarator().VariableInitializer() != null) {
                visit(node.VariableDeclarator().VariableInitializer());
            } else { 
                // default initialization
                _codeChunk.printLine("mov eax, 0 ; default initialization");
            } // else
            _codeChunk.printLine("mov ebx, [" + classLabel +"]\nadd ebx, "+ interf.offsetToString(node.getOffset()));
            _codeChunk.printLine("mov [ebx], eax ; store value of field");
        } // else
        _codeChunk.printCommentLine("END FieldDeclNode");
    } // visit(FieldDeclNode)

    @Override
    public void visit(LocalVariableDeclNode localVariableDeclNode) throws iVisitor.ASTException{
        _codeChunk.printCommentLine("Start LocalVariableDeclNode");
        if(localVariableDeclNode.VariableInitializer() != null){
            visit(localVariableDeclNode.VariableInitializer());
        } // if
        _codeChunk.printLine(interf.storeLocal(localVariableDeclNode));
        _codeChunk.printCommentLine("END LocalVariableDeclNode");
    } // visit(LocalVariableDeclNode)

    @Override
    public void visit(LiteralNode literalNode) throws iVisitor.ASTException{
        _codeChunk.printCommentLine("Start LiteralNode");
        if(literalNode.LiteralType().equals(LiteralNode.Literal_BOOL)){
            if(literalNode.BooleanLiteral()){
                _codeChunk.printLine("mov eax, 1");
            } else {
                _codeChunk.printLine("mov eax, 0");
            } // else
        } else if (literalNode.LiteralType().equals(LiteralNode.Literal_STR)){
            createStringLiteral(literalNode.Stringliteral(),
                    literalNode.getEnv().getRoot().getRoot().TypeDeclaration().ClassDeclaration() == _stringClass);
        } else if (literalNode.LiteralType().equals(LiteralNode.Literal_CHAR)){
            int c=literalNode.Charliteral();
            _codeChunk.printLine("mov eax, " + c);
        } else if (literalNode.LiteralType().equals(LiteralNode.Literal_INT)){
            _codeChunk.printLine("mov eax, " + literalNode.IntegerLiteral());
        } else if (literalNode.LiteralType().equals(LiteralNode.Literal_NULL)){
            _codeChunk.printLine("mov eax, 0");
        } // else if
        _codeChunk.printCommentLine("END LiteralNode");
    } // visit(LiteralNode)
   
    @Override
    public void visit(ArrayCreationExprNode arrayCreationExprNode) throws iVisitor.ASTException{
        _codeChunk.printCommentLine("Start Array Creation");
        visit(arrayCreationExprNode.DimExpr());
        boolean isBasic = (arrayCreationExprNode.Basictype() != null);
        String noErrorLabel = interf.generalLabel("noError");
        _codeChunk.printLine("mov ebx, 0 \ncmp eax, ebx \njge " + noErrorLabel + "\ncall __exception; negative array size");
        _codeChunk.printLabel(noErrorLabel);
        // allocate space for the class being created
        // create the pointer
        _codeChunk.printLine("mov ecx, eax \nmov edx, 2\nadd eax, edx\nshl eax, 2\npush ecx ; store array length");
        _codeChunk.printLine(interf.callMalloc());
        String vtable;
        if(isBasic) vtable = interf.VtableLabelArray(arrayCreationExprNode.Basictype().getElement());
        else {
            ASTNode decl = arrayCreationExprNode.ClassOrInterfaceType().Name().getLinkedDeclaration();
            if (decl instanceof ClassDeclNode) {
                ClassDeclNode node = (ClassDeclNode) decl;
                vtable = interf.VtableLabelArray(interf.ClassLabel(node));
            } else {
                InterfaceDeclNode node = (InterfaceDeclNode) decl;
                vtable = interf.VtableLabelArray(interf.InterfaceLabel(node));
            } // else
        } // else
        String extern = "";
        if(!_outputFileName.equals("_Initialization")) extern = "extern " + vtable+"\n";
        _codeChunk.printLine(extern +"mov ebx, [" + vtable + "]\nmov [eax], ebx ; store vtable label");
        _codeChunk.printLine("pop ecx\nmov [eax + 4], ecx ; store array length");

        _codeChunk.printLine("push eax ; store array location");
        String loopLabel = interf.generalLabel("arrayInitLoop");
        String endLabel = interf.generalLabel("endArrayInit");

        _codeChunk.printLine("add eax, 8 ; starting location of actual array elements ");
        _codeChunk.printLabel(loopLabel);
        _codeChunk.printLine("cmp ecx, 0 \nje "  + endLabel);
        _codeChunk.printLine("mov ebx, 0\nmov [eax], ebx ; initialize to 0");
        _codeChunk.printLine("add eax, 4 \nsub ecx, 1");
        _codeChunk.printLine("jmp " + loopLabel);
        _codeChunk.printLabel(endLabel);
        _codeChunk.printLine("pop eax ; return array location");
        _codeChunk.printCommentLine("END Array Creation");
    } // visit(ArrayCreationExprNode)

    @Override
    public void visit(CastExprNode castExprNode) throws iVisitor.ASTException{
        _codeChunk.printCommentLine("Start Cast Expr");
        visit(castExprNode.UnaryExpression());
        _codeChunk.printLine("push eax");
        if(castExprNode.Dims()){
            if(castExprNode.Name() != null && castExprNode.Name().getLinkedDeclaration() instanceof ClassDeclNode) {
                isTypeAssignable((ClassDeclNode) castExprNode.Name().getLinkedDeclaration(), true);
            } else if(castExprNode.Name() != null && castExprNode.Name().getLinkedDeclaration() instanceof InterfaceDeclNode){
                isTypeAssignable((InterfaceDeclNode) castExprNode.Name().getLinkedDeclaration(),true);
            } else if (castExprNode.BasicType() != null){
                isTypeAssignable(castExprNode.BasicType().getElement());
            } // else if
        } else if(castExprNode.Name() != null && castExprNode.Name().getLinkedDeclaration() instanceof ClassDeclNode){
            isTypeAssignable((ClassDeclNode) castExprNode.Name().getLinkedDeclaration(),false);
        } else if(castExprNode.Name() != null && castExprNode.Name().getLinkedDeclaration() instanceof InterfaceDeclNode){
            isTypeAssignable((InterfaceDeclNode) castExprNode.Name().getLinkedDeclaration(),false);
        } // else if
        _codeChunk.printLine("pop eax");
        _codeChunk.printCommentLine("END Cast Expr");
    } // visit(CastExprNode)

    @Override
    public void visit(UnaryExprNode unaryExprNode) throws iVisitor.ASTException{
        _codeChunk.printCommentLine("Start unaryexprnode");
        super.visit(unaryExprNode);
        if(unaryExprNode.UnaryOperator() != null){
            if(unaryExprNode.UnaryOperator().equals(UnaryExprNode.MINUS_OPER)){
                _codeChunk.printLine("neg eax");
            } else if (unaryExprNode.UnaryOperator().equals(UnaryExprNode.NEG_OPER)){
                _codeChunk.printLine("mov ecx, 1");
                _codeChunk.printLine("sub ecx, eax ; ecx <- 1-eax");
                _codeChunk.printLine("mov eax, ecx ; eax <- 1-eax");
            } // else if
        } // if
        _codeChunk.printCommentLine("END unaryexprnode");
    } // visit(UnaryExprNode)


    @Override
    public void visit(ReturnStmtNode returnStmtNode) throws iVisitor.ASTException{
        _codeChunk.printCommentLine("Start ReturnStmt node");
        if(returnStmtNode.Expression() != null) visit(returnStmtNode.Expression());
        _codeChunk.printLine(interf.returnCode());
        _codeChunk.printCommentLine("END ReturnStmt");
    } // visit(ReturnStmtNode)

    @Override
    public void visit(AssignmentExprNode node) throws iVisitor.ASTException{
        _codeChunk.printCommentLine("Start AssignmentExprNode");
        super.visit(node);
        _codeChunk.printCommentLine("END AssignmentExprNode");
    } // visit(AssignmentExprNode)

    @Override
    public void visit(AssignmentNode assignmentNode) throws iVisitor.ASTException{
        _codeChunk.printCommentLine("Start AssignmentNode");

        isLHS = true;
        if(assignmentNode.FieldAccess() != null) {
            visit(assignmentNode.FieldAccess());
        } else if(assignmentNode.ArrayAccess() != null) {
            visit(assignmentNode.ArrayAccess());
        } else if(assignmentNode.Name() != null) {
            visit(assignmentNode.Name());
        } // else if
        isLHS = false;
        _codeChunk.printLine("push eax ; store result of LHS");
        if(assignmentNode.ArrayAccess() != null
                && ((TypeNode) assignmentNode.AssignmentExpression().getType()).ClassOrInterfaceType() != null){
            // store address of array (to be used by checkTypeAssignability
            _codeChunk.printLine("push edx ; store address of array");
        } // if
        visit(assignmentNode.AssignmentExpression());
        _codeChunk.printLine("mov ebx, eax ; move value of RHS to ebx");
        if(assignmentNode.ArrayAccess() != null
                && ((TypeNode) assignmentNode.AssignmentExpression().getType()).ClassOrInterfaceType() != null){
            // assignment to class array element
            _codeChunk.printLine("pop eax ; get address of array");
            checkTypeAssignability();
        } // if
        _codeChunk.printLine("pop eax ; get result of LHS");
        _codeChunk.printLine("mov [eax], ebx ; store result of RHS at LHS");
        _codeChunk.printLine("mov eax, ebx ; final result");
        _codeChunk.printCommentLine("END AssignmentNode");
    } // visit(AssignmentNode)

    @Override
    public void visit(ClassInstanceCreationExprNode classInstanceCreationExprNode) throws iVisitor.ASTException{
        ClassDeclNode classDecl = (ClassDeclNode) classInstanceCreationExprNode.ClassType().Name().getLinkedDeclaration();
        List<FieldData> fieldDataList = classDecl.getEnv().getRoot().getSuperFieldsDeclared();

        //get fields from superclasses
        _codeChunk.printCommentLine("Start Object Initialization of type " + classDecl.Identifier());
        int bytesToAllocate=fieldDataList.size() + 1;
        //allocate space for the class being created
        //create the pointer
        _codeChunk.printLine("mov eax, " + bytesToAllocate*4);
        _codeChunk.printLine(interf.callMalloc());
        String vtable = interf.VtableLabel(classDecl);
        String extern = "";
        if(!_outputFileName.equals("_Initialization")) extern = "extern " + vtable+"\n";
        _codeChunk.printLine(extern +"mov ebx, [" + vtable + "]\nmov [eax], ebx ; store vtable label");

        _codeChunk.printLine("push eax ; push new THIS\nmov edx, [" + interf.getThisLabel() + "]\nmov edx, [edx]; old THIS\npush edx");
        _codeChunk.printLine("mov edx, eax; put address of object (for constructor call) in edx");

        _codeChunk.printLine(interf.storeRegs());

        //call the constructor
        ConstructorDeclarationNode decl = (ConstructorDeclarationNode) classInstanceCreationExprNode.ConstructDecl();

        // compute and store arguments on stack
        for(int i = 0; i < classInstanceCreationExprNode.ArgumentList().size(); ++i){
            visit(classInstanceCreationExprNode.ArgumentList().get(i));
            _codeChunk.printLine(interf.storeArg(classInstanceCreationExprNode, i));
        } // for

        _codeChunk.printLine("mov eax, [esp + "+ 4* (classInstanceCreationExprNode.ArgumentList().size()+ 1)+ " ] "
                + "; get address of object (for constructor call) from stack" );
        String noError = interf.generalLabel("noError");
        _codeChunk.printLine("mov edx, 0\ncmp eax, edx\njne " + noError + "\ncall __exception ; nullpointerexception (for constructorcall)");
        _codeChunk.printLabel(noError);
        _codeChunk.printLine("mov edx, [" + interf.getThisLabel() + "]\nmov [edx], eax ; update THIS to prefix object");

        _codeChunk.printLine("; calls " + classInstanceCreationExprNode.ConstructDecl().getEnv().getScopePair().getName().toString());
        _codeChunk.printLine(interf.callConstructor((ConstructorDeclarationNode) classInstanceCreationExprNode.ConstructDecl()));
        _codeChunk.printLine(interf.loadRegs(classInstanceCreationExprNode.ArgumentList().size()));

        _codeChunk.printLine("pop eax; load old THIS");
        _codeChunk.printLine("mov edx, [" + interf.getThisLabel() + "]\nmov [edx], eax ; update THIS to old value");
        _codeChunk.printLine("pop eax; load new THIS");
        _codeChunk.printCommentLine("END Object Initialization");
    } // visit(ClassInstanceCreationExprNode)

    @Override
    public void visit(MethodInvocationNode methodInvocationNode) throws iVisitor.ASTException{
        _codeChunk.printCommentLine("Start MethodInvocation node");

        if(methodInvocationNode.Primary() != null) {
            visit(methodInvocationNode.Primary());
        } else if(methodInvocationNode.FieldName() != null) {
            visit(methodInvocationNode.FieldName());
        } else { 
            // no prefix to method call
            _codeChunk.printLine("mov eax, [" + interf.getThisLabel() + "]\nmov eax, [eax]; get THIS (for prefixless-method call)");
        } // else
        // EAX has result (address of object)

        MethodHeaderNode decl = (MethodHeaderNode) methodInvocationNode.getMethodDecl();
        boolean isStatic = decl.getMethodData().isStatic();
        _codeChunk.printLine("mov edx, [" + interf.getThisLabel() + "]\nmov edx, [edx]; old THIS\npush edx");
        if(!isStatic) _codeChunk.printLine("mov edx, eax; put address of object (for nonstatic methodcalls) in edx");

        _codeChunk.printLine(interf.storeRegs());

        if(methodInvocationNode.ArgumentList().size() > 0) _codeChunk.printCommentLine("Compute arguments");
        // compute and store arguments on stack
        int numArgs = methodInvocationNode.ArgumentList().size();
        for(int i =0; i < numArgs; ++i){
            visit(methodInvocationNode.ArgumentList().get(i));
            _codeChunk.printLine(interf.storeArg(methodInvocationNode, i));
        } // for
        if(methodInvocationNode.ArgumentList().size() > 0) _codeChunk.printCommentLine("Done computing arguments");

        if(isStatic) {
            _codeChunk.printLine("mov ecx, 0\nmov edx, [" + interf.getThisLabel() + "]\nmov [edx], ecx ; update THIS to 0 (static method call)");
        } else { 
            // nonstatic
            _codeChunk.printLine("mov eax, [esp + "+ 4* (methodInvocationNode.ArgumentList().size()+ 1)+ " ] "
                + "; get address of _THIS_ (for nonstatic methodcalls) from stack" );
            String noError = interf.generalLabel("noError");
            _codeChunk.printLine("mov edx, 0\ncmp eax, edx\njne " + noError + "\ncall __exception ; nullpointerexception (for methodcall)");
            _codeChunk.printLabel(noError);
            _codeChunk.printLine("mov edx, [" + interf.getThisLabel() + "]\nmov [edx], eax ; update THIS to prefix object");
        } // else

        // code for calling the method using the vtable or SItable
        if(isStatic){
            _codeChunk.printLine(interf.callStaticMethod(decl));
        } else _codeChunk.printLine(interf.callNonStaticMethod(decl));
        _codeChunk.printLine(interf.loadRegs(methodInvocationNode.ArgumentList().size()));

        _codeChunk.printLine("pop edx; load old THIS");
        _codeChunk.printLine("mov ecx, [" + interf.getThisLabel() + "]\nmov [ecx], edx ; update THIS to old value");
        _codeChunk.printCommentLine("END MethodInvocation node");
    } // visit(MethodInvocationNode)

    /*******************************************************************************
     * Code for concatenation
     * notes: eax has left string, ebx has right string
     * *****************************************************************************/   
    public void concatenate(boolean inSameFile) throws ASTException{
        _codeChunk.printCommentLine("Start MethodInvocation node (concat)");

        _codeChunk.printLine("mov edx, [" + interf.getThisLabel() + "]\nmov edx, [edx]; old THIS\npush edx");
        _codeChunk.printLine("mov edx, eax; put address of object (for nonstatic methodcalls) in edx");

        _codeChunk.printLine(interf.storeRegs());
        _codeChunk.printLine("push ebx ; store argument (right string)");

        _codeChunk.printLine("mov eax, [esp + "+ 4* (1 + 1)+ " ] "
                + "; get address of _THIS_ (for nonstatic methodcalls) from stack" );
        String noError = interf.generalLabel("noError");
        _codeChunk.printLine("mov edx, 0\ncmp eax, edx\njne " + noError + "\ncall __exception ; nullpointerexception (for methodcall)");
        _codeChunk.printLabel(noError);
        _codeChunk.printLine("mov edx, [" + interf.getThisLabel() + "]\nmov [edx], eax ; update THIS to prefix object");

        // code for calling the method using the vtable
        if(!inSameFile) _codeChunk.printLine("extern _java.lang.String_METHOD_concat#String#");
        _codeChunk.printLine("call _java.lang.String_METHOD_concat#String#");

        _codeChunk.printLine(interf.loadRegs(1));

        _codeChunk.printLine("pop edx; load old THIS");
        _codeChunk.printLine("mov ecx, [" + interf.getThisLabel() + "]\nmov [ecx], edx ; update THIS to old value");
        _codeChunk.printCommentLine("END MethodInvocation node (concat)");
    } // concatenate()

    /*******************************************************************************
     * Call toString on reference type
     * notes: eax contains address of object
     * *****************************************************************************/   
    public void callToString() throws ASTException{
        _codeChunk.printCommentLine("Start MethodInvocation (toString) node");
        // EAX has result (address of object)

        _codeChunk.printLine("mov edx, [" + interf.getThisLabel() + "]\nmov edx, [edx]; old THIS\npush edx");
        _codeChunk.printLine("mov edx, eax; put address of object (for nonstatic methodcalls) in edx");

        _codeChunk.printLine(interf.storeRegs());

        _codeChunk.printLine("mov eax, [esp + "+ 4* (0 + 1)+ " ] "
                + "; get address of _THIS_ (for nonstatic methodcalls) from stack" );
        String noError = interf.generalLabel("noError");
        _codeChunk.printLine("mov edx, 0\ncmp eax, edx\njne " + noError + "\ncall __exception ; nullpointerexception (for methodcall)");
        _codeChunk.printLabel(noError);
        _codeChunk.printLine("mov edx, [" + interf.getThisLabel() + "]\nmov [edx], eax ; update THIS to prefix object");

        // code for calling the method using the vtable (with fixed offset 16)
        _codeChunk.printLine("mov eax, [eax] ; get vtable \nmov eax , [eax + 16] ; load toString method \ncall eax ");

        _codeChunk.printLine(interf.loadRegs(0));

        _codeChunk.printLine("pop edx; load old THIS");
        _codeChunk.printLine("mov ecx, [" + interf.getThisLabel() + "]\nmov [ecx], edx ; update THIS to old value");
        _codeChunk.printCommentLine("END MethodInvocation (toString) node");
    } // callToString()

    /*******************************************************************************
     * Code for creating a String object
     * *****************************************************************************/ 
    public void createStringObject(ClassDeclNode classDecl, String methodLabel, boolean inSameFile) throws iVisitor.CodePrinterVisitorException {
        _codeChunk.printCommentLine("Start String conversion");

        /*************************** Calls Class constructor *********************************/
        List<FieldData> fieldDataList = classDecl.getEnv().getRoot().getSuperFieldsDeclared();
        _codeChunk.printLine("push eax ; store value to be converted to Class");

        //get fields from superclasses
        _codeChunk.printCommentLine("Start Object Initialization of type " + classDecl.Identifier());
        int bytesToAllocate=fieldDataList.size() + 1;
        //allocate space for the class being created
        //create the pointer
        _codeChunk.printLine("mov eax, " + bytesToAllocate*4);
        _codeChunk.printLine(interf.callMalloc());
        String vtable = interf.VtableLabel(classDecl);
        String extern = "";
        if(!inSameFile) extern = "extern " + vtable+"\n";
        _codeChunk.printLine(extern +"mov ebx, [" + vtable + "]\nmov [eax], ebx ; store vtable label");

        _codeChunk.printLine("pop ecx ; store value to be converted to Class in ecx");

        _codeChunk.printLine("push eax ; push new THIS\nmov edx, [" + interf.getThisLabel() + "]\nmov edx, [edx]; old THIS\npush edx");
        _codeChunk.printLine("mov edx, eax; put address of object (for constructor call) in edx");
        _codeChunk.printLine(interf.storeRegs());

        // compute and store argument on stack
        _codeChunk.printLine("push ecx ; store arg");

        _codeChunk.printLine("mov eax, [esp + "+ 4* (1 + 1)+ " ] "
                + "; get address of object (for constructor call) from stack" );
        String noError = interf.generalLabel("noError");
        _codeChunk.printLine("mov edx, 0\ncmp eax, edx\njne " + noError + "\ncall __exception ; nullpointerexception (for constructorcall)");
        _codeChunk.printLabel(noError);
        _codeChunk.printLine("mov edx, [" + interf.getThisLabel() + "]\nmov [edx], eax ; update THIS to prefix object");

        _codeChunk.printLine("; calls " + methodLabel);
        extern = "";
        if (!inSameFile) extern = "extern " + methodLabel + "\n";
        _codeChunk.printLine(extern + "call " + methodLabel);
        _codeChunk.printLine(interf.loadRegs(1));

        _codeChunk.printLine("pop eax; load old THIS");
        _codeChunk.printLine("mov edx, [" + interf.getThisLabel() + "]\nmov [edx], eax ; update THIS to old value");
        _codeChunk.printLine("pop eax; load new THIS");
        _codeChunk.printCommentLine("END Object Initialization");
        _codeChunk.printCommentLine("END String conversion");
    } // createStringObject()

    /*******************************************************************************
     * Code for creating a String literal
     * *****************************************************************************/ 
    public void createStringLiteral(String stringLiteral, boolean inSameFile) throws iVisitor.CodePrinterVisitorException{
        _codeChunk.printCommentLine("Start String Object Creation method");
        ClassDeclNode classDecl = _stringClass;
        List<FieldData> fieldDataList = classDecl.getEnv().getRoot().getSuperFieldsDeclared();

        //get fields from superclasses
        int bytesToAllocate=fieldDataList.size() + 1;
        //allocate space for the class being created
        //create the pointer
        _codeChunk.printLine("mov eax, " + bytesToAllocate*4);
        _codeChunk.printLine(interf.callMalloc());
        String vtable = interf.VtableLabel(classDecl);
        String extern = "";
        if(!_outputFileName.equals("_Initialization")) extern = "extern " + vtable+"\n";
        _codeChunk.printLine(extern +"mov ebx, [" + vtable + "]\nmov [eax], ebx ; store vtable label");

        _codeChunk.printLine("push eax ; push new THIS\nmov edx, [" + interf.getThisLabel() + "]\nmov edx, [edx]; old THIS\npush edx");
        _codeChunk.printLine("mov edx, eax; put address of object (for constructor call) in edx");

        _codeChunk.printLine(interf.storeRegs());

        /*************************** compute and store the array of characters ***************************/
        _codeChunk.printCommentLine("Start Array Creation");
        boolean isBasic = true;
        String noErrorLabel = interf.generalLabel("noError");
        _codeChunk.printLine("mov eax, "+ stringLiteral.length() + " ; array size");
        //allocate space for the class being created
        //create the pointer
        _codeChunk.printLine("mov ecx, eax \nmov edx, 2\nadd eax, edx\nshl eax, 2\npush ecx ; store array length");
        _codeChunk.printLine(interf.callMalloc());
        vtable = interf.VtableLabelArray(BasicTypeNode.CHAR_TYPE);
        extern = "";
        if(!_outputFileName.equals("_Initialization")) extern = "extern " + vtable+"\n";
        _codeChunk.printLine(extern +"mov ebx, [" + vtable + "]\nmov [eax], ebx ; store vtable label");
        _codeChunk.printLine("pop ecx\nmov [eax + 4], ecx ; store array length");

        _codeChunk.printLine("push eax ; store array location");
        _codeChunk.printLine("add eax, 8 ; starting location of actual array elements ");
        int strLen = stringLiteral.length();
        for(int i = 0; i < strLen; ++i){
            char c = stringLiteral.charAt(i);
            int converted = c;
            _codeChunk.printLine("mov ebx, " + converted + "\nmov [eax], ebx ; initialize char ");
            _codeChunk.printLine("add eax, 4 \nsub ecx, 1");
        } // for
        _codeChunk.printLine("pop eax ; return array location");
        _codeChunk.printCommentLine("END Array Creation");
        /*************************** DONE ARRAY CREATION ***************************/
        // store argument
        _codeChunk.printLine("push eax ; store argument of String constructor");

        // call the constructor
        _codeChunk.printLine("mov eax, [esp + "+ 4* (1+1)+ " ] "
                + "; get address of object (for constructor call) from stack" );
        String noError = interf.generalLabel("noError");
        _codeChunk.printLine("mov edx, 0\ncmp eax, edx\njne " + noError + "\ncall __exception ; nullpointerexception (for constructorcall)");
        _codeChunk.printLabel(noError);
        _codeChunk.printLine("mov edx, [" + interf.getThisLabel() + "]\nmov [edx], eax ; update THIS to prefix object");

        String constructorLabel = "_java.lang.String_CONSTRUCTOR_String#char$$#";
        extern = "";
        if (!inSameFile) extern = "extern " + constructorLabel + "\n";
        _codeChunk.printLine(extern + "call " + constructorLabel + " ; call constructor");
        _codeChunk.printLine(interf.loadRegs(1));

        _codeChunk.printLine("pop eax; load old THIS");
        _codeChunk.printLine("mov edx, [" + interf.getThisLabel() + "]\nmov [edx], eax ; update THIS to old value");
        _codeChunk.printLine("pop eax; load new THIS");
    } // createStringLiteral()

    /*******************************************************************************
     * Code for creating a NULL String object
     * *****************************************************************************/ 
    public void createStringObjectFromNull(boolean inSameFile) throws CodePrinterVisitorException{
        createStringLiteral("null", inSameFile);
    } // createStringObjectFromNull()

    /*******************************************************************************
     * Determines if there is a "test" method
     * *****************************************************************************/ 
    public boolean ContainsTestMethod(ASTTree tree){
        for(MethodData methodData: tree.getMethodsDeclared()){
                if(methodData.getMethodName().equals("test") && methodData.isStatic() && methodData.getReturnType().isInt()){
                        return true;
                } // if
        } // for
        return false;
    } // ContainsTestMethod()
} // class CodePrinterVisitor()