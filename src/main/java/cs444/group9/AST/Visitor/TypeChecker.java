/*******************************************************************************
 * TypeChecker.java
 * 
 * A module implementing the type checker.
 * ****************************************************************************/

package cs444.group9.AST.Visitor;

import cs444.group9.AST.ASTTree;
import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.Array.ArrayAccessNode;
import cs444.group9.AST.Node.Array.FieldAcccessNode;
import cs444.group9.AST.Node.Array.PrimaryNoNewArrayNode;
import cs444.group9.AST.Node.Array.PrimaryNode;
import cs444.group9.AST.Node.Body.CompilationUnitNode;
import cs444.group9.AST.Node.Body.FormalParameterNode;
import cs444.group9.AST.Node.Body.MethodDeclarationNode;
import cs444.group9.AST.Node.Body.MethodHeaderNode;
import cs444.group9.AST.Node.Declaration.*;
import cs444.group9.AST.Node.Environments.Environment;
import cs444.group9.AST.Node.Environments.EnvironmentPair;
import cs444.group9.AST.Node.Environments.FieldData;
import cs444.group9.AST.Node.Environments.MethodData;
import cs444.group9.AST.Node.Expression.*;
import cs444.group9.AST.Node.LiteralNode;
import cs444.group9.AST.Node.ModifierNode;
import cs444.group9.AST.Node.NameNode;
import cs444.group9.AST.Node.Statement.*;
import cs444.group9.AST.Node.StatementExpression.AssignmentNode;
import cs444.group9.AST.Node.StatementExpression.ClassInstanceCreationExprNode;
import cs444.group9.AST.Node.StatementExpression.MethodInvocationNode;
import cs444.group9.AST.Node.Type.*;

import java.util.ArrayList;
import java.util.List;

public class TypeChecker extends DefaultVisitor {
    // type checker variables
    Environment _globalSymbolTable;
    // type linker (to be used in order to call PerformResolveName
    ClassTypeLinkerVisitor typeLinker;

    // name resolution variables
    private boolean isExprName;
    private boolean isLHS;
    private boolean occursInVarInit;
    String enclosingEntityName;
    // does the current name occur in the method/constructor body of a static method/constructor
    // or in the initializer of a static field?
    private boolean occursInStatic;
    // root of current tree
    private ASTTree _root;
    private ASTTree objectClass;

    // nameNode for Object, Cloneable, Serializable classes
    private NameNode _obj;
    private NameNode _cloneable;
    private NameNode _serializable;

    /*******************************************************************************
     * TypeChecker constuctor
     * time: O(1)
     * *****************************************************************************/ 
    public TypeChecker(){
        super();
        _globalSymbolTable=null;
        objectClass = null;
        typeLinker = new ClassTypeLinkerVisitor();
    } // TypeChecker()

    /*******************************************************************************
     * Setter for object class
     * time: O(1)
     * *****************************************************************************/ 
    public void setObjectClass(ASTTree obj){
        objectClass = obj;
    } // setObjectClass()

    /*******************************************************************************
     * Resolves all names
     * *****************************************************************************/ 
    public void resolveAllNames(List<ASTTree> trees) throws ASTException{
        for(ASTTree t: trees){
            enclosingEntityName = "";
            isExprName = false;
            isLHS = false;
            occursInVarInit = false;
            occursInStatic = false;
            _root = t;
            visit(t.getRoot());
        } // for
    } // resolveAllNames()

    /*******************************************************************************
     * OVER-RIDDEN VISIT METHODS
     * *****************************************************************************/ 
    @Override
    public void visit(CompilationUnitNode node) throws iVisitor.ASTException {
        if(_globalSymbolTable == null) {
            // set the global symboltable
            Environment current = node.getEnv();
            while (!current.getScopePair().getName().equals(Environment.GLOBAL_SCOPE_NAME)) {
                current = current.getParent();
            } // while
            _globalSymbolTable = current;
            // create name nodes for Object, Cloneable, Serializable and resolve the names
            _obj = new NameNode(null);
            _obj.addName("java"); _obj.addName("lang"); _obj.addName("Object");
            _obj.UpdateEnv(node.getEnv()); 
            typeLinker.findTypeLink(_obj);
            _cloneable = new NameNode(null);
            _cloneable.addName("java"); _cloneable.addName("lang"); _cloneable.addName("Cloneable");
            _cloneable.UpdateEnv(node.getEnv()); 
            typeLinker.findTypeLink(_cloneable);
            _serializable = new NameNode(null);
            _serializable.addName("java"); _serializable.addName("io"); _serializable.addName("Serializable");
            _serializable.UpdateEnv(node.getEnv()); 
            typeLinker.findTypeLink(_serializable);
        } // if

        //check the types
        //assume that imports and packages are already checked
        if (node.TypeDeclaration() != null) {
            visit(node.TypeDeclaration());
        } // if
    } // visit(CompilationUnitNode)

    @Override
    public void visit(IfStmtNode ifStmtNode) throws iVisitor.ASTException{
        visit(ifStmtNode.Expression());
        TypeNode ifExpr=(TypeNode)ifStmtNode.Expression().getType();
        if(!ifExpr.isBoolean()){
            System.err.println("Expression inside if statement must return a boolean");
            throw new NameResolutionException();
        } // if
        for(StatementNode stmt: ifStmtNode.Statements()){
            visit(stmt);
        } // for
    } // visit(IfStmtNode)

    @Override
    public void visit(WhileStmtNode whileStmtNode) throws ASTException{
        visit(whileStmtNode.Expression());
        TypeNode whileExpr=(TypeNode)whileStmtNode.Expression().getType();
        if(!whileExpr.isBoolean()){
            System.err.println("Expression inside while statement must return a boolean");
            throw new NameResolutionException();
        } // if
        if (whileStmtNode.Statement() != null){
            visit(whileStmtNode.Statement());
        } // if
    } // visit(WhileStmtNode)

    @Override
    public void visit(ForStmtNode forStmtNode) throws ASTException{
        if(forStmtNode.StatementExpression() != null){
            visit(forStmtNode.StatementExpression());
        } // if
        if (forStmtNode.LocalVariableDeclaration() != null){
            visit(forStmtNode.LocalVariableDeclaration());
        } // if
        if(forStmtNode.Expression() != null){
            visit(forStmtNode.Expression());
            TypeNode forExpr=(TypeNode)forStmtNode.Expression().getType();
            if(!forExpr.isBoolean()){
                System.err.println("Expression inside while statement must return a boolean");
                throw new NameResolutionException();
            }  // if
        } // if
        if(forStmtNode.Forupdate() != null){
            visit(forStmtNode.Forupdate());
        } // if
        visit(forStmtNode.Statement());
    } // visit(ForStmtNode)

    @Override
    public void visit(FormalParameterNode node) throws ASTException{
        node.setType(node.Type());
    } // visit(FormalParameterNode)

    @Override
    public void visit(ReturnStmtNode returnStmtNode) throws iVisitor.ASTException{
        //get current method return type
        TypeNode methodReturnType=null;
        // CHECK returnExpr matches the current method's return type
        Environment ep = returnStmtNode.getEnv();
        while(true){ 
            // keep going to parent until methodheader is found
            EnvironmentPair current=ep.getScopePair();
            if(current.getDeclaration() instanceof MethodHeaderNode) {
                MethodData m = ((MethodHeaderNode) current.getDeclaration()).getMethodData();
                methodReturnType = m.getReturnType();
                //handle void case
                if (m.isVoid()) {
                    BasicTypeNode voidType = new BasicTypeNode(null);
                    voidType.setElement(BasicTypeNode.VOID_TYPE);
                    methodReturnType = new TypeNode(voidType);
                } // if
                break;
            } else if (current.getDeclaration() instanceof ConstructorDeclarationNode){
                BasicTypeNode voidType = new BasicTypeNode(null);
                voidType.setElement(BasicTypeNode.VOID_TYPE);
                methodReturnType = new TypeNode(voidType);
                break;
            } else {
                ep=ep.getParent();
            } // else
        } // while
        if(methodReturnType == null){
            //SHOULD NOT GET HERE
            System.err.println("Unable to determine method return type");
            throw new Error();
        } // if
        if(returnStmtNode.Expression() != null){
            if(methodReturnType.isVoid()){
                System.err.println("Error: method has a void return type so must not return an expression.");
                throw new NameResolutionException();
            } // if
            visit(returnStmtNode.Expression());
            TypeNode returnExprType=(TypeNode)returnStmtNode.Expression().getType();

            if(isTypeAssignableTo(methodReturnType, returnExprType)){
                returnStmtNode.setType(methodReturnType);
            } else {
                System.err.println("Type of return expression is not type assignable to return type.");
                throw new NameResolutionException();
            } // else
        } else if(!methodReturnType.isVoid()) { 
            // missing expression but not void return header
            System.err.println("Error: method must return type: " + methodReturnType.toString());
            throw new NameResolutionException();
        } // else
    } // visit(ReturnStmtNode)


    @Override
    public void visit(ClassInstanceCreationExprNode classInstanceCreationExprNode) throws iVisitor.ASTException{
        ASTNode classDecl = classInstanceCreationExprNode.ClassType().Name().getLinkedDeclaration();
        boolean isAbstract = false;
        if(classDecl instanceof ClassDeclNode){
            for(ModifierNode m: ((ClassDeclNode) classDecl).Modifiers()){
                if(m.Modifier().equals("ABSTRACT")) isAbstract = true;
            } // for
        } else isAbstract = true;
        if(isAbstract) {
            System.err.println("Cannot create instance of abstract class " + classDecl.getEnv().getScopePair().getName());
            throw new NameResolutionException();
        } // if
        super.visit(classInstanceCreationExprNode);

        // resolve constructor
        // list of argument types
        List<FormalParameterNode> argTypes = new ArrayList<>();
        for(int i = 0; i < classInstanceCreationExprNode.ArgumentList().size(); i++){
            AssignmentExprNode arg = classInstanceCreationExprNode.ArgumentList().get(i);
            FormalParameterNode param = new FormalParameterNode(null);
            param.Type((TypeNode) arg.getType());
            argTypes.add(param);
        } // for
        // tries to resolve the constructor name (and store a ptr to its decl)
        for(MethodData m: classDecl.getEnv().getRoot().getConstructorsDeclared()){
            if(m.isSameSignature(classDecl.getEnv().getScopePair().getName(), argTypes)){
                classInstanceCreationExprNode.ConstructDecl(m.getDecl());
                if(!m.isPublic()) {
                    // protected check
                    if (!classDecl.getEnv().getPackage().toString().equals(_root.getRoot().getEnv().getPackage().toString())) {
                        System.err.println("Cannot call protected constructor from outside the package.");
                        throw new NameResolutionException();
                    } // if
                } // if
                break;
            } // if
        } // for
        if(classInstanceCreationExprNode.ConstructDecl() == null) {
            System.err.println("Cannot resolve constructor of class " + classDecl.getEnv().getScopePair().getName());
            throw new NameResolutionException();
        } // if
        // sets the type to be the class type
        TypeNode wrapper = new TypeNode(classInstanceCreationExprNode.get_parseTreeNode());
        wrapper.ClassOrInterfaceType(classInstanceCreationExprNode.ClassType());
        classInstanceCreationExprNode.setType(wrapper);
        wrapper.UpdateEnv(classInstanceCreationExprNode.getEnv());
    } // visit(ClassInstanceCreationExprNode)

    @Override
    public void visit(ArrayCreationExprNode arrayCreationExprNode) throws iVisitor.ASTException{
        // get the type
        TypeNode t = new TypeNode(arrayCreationExprNode.get_parseTreeNode());
        ArrayTypeNode arrayType = new ArrayTypeNode(null);
        if (arrayCreationExprNode.Basictype() != null){
            arrayType.BasicType(arrayCreationExprNode.Basictype());
        } else if(arrayCreationExprNode.ClassOrInterfaceType() != null){
            arrayType.Name(arrayCreationExprNode.ClassOrInterfaceType().Name());
        } // else if
        t.ArrayType(arrayType);
        t.UpdateEnv(arrayCreationExprNode.getEnv());
        arrayCreationExprNode.setType(t);

        if(arrayCreationExprNode.DimExpr() != null){
            visit(arrayCreationExprNode.DimExpr());
            TypeNode type=(TypeNode)arrayCreationExprNode.DimExpr().getType();
            if(!type.isNum()){
                System.err.println("Array declaration: value inside bracket must be of numerical type");
                throw new NameResolutionException();
            } // if
        } // if
    } // visit(ArrayCreationExprNode)

    @Override
    public void visit(ArrayAccessNode arrayAccessNode) throws iVisitor.ASTException{
        TypeNode arrayType = null;
        if(arrayAccessNode.Name() != null){
            isExprName = true;
            visit(arrayAccessNode.Name());
            isExprName = false;
            arrayType = (TypeNode) arrayAccessNode.Name().getType();
        } else if(arrayAccessNode.PrimaryNoNewArray() != null){
            visit(arrayAccessNode.PrimaryNoNewArray());
            arrayType = (TypeNode) arrayAccessNode.PrimaryNoNewArray().getType();
        } // else if
        // visits expression
        boolean tempIsLHS = isLHS;
        isLHS = false;
        visit(arrayAccessNode.Expression());
        isLHS = tempIsLHS;

        TypeNode type=(TypeNode)arrayAccessNode.Expression().getType();
        if(!type.isNum()){
            System.err.println("Array declaration: value inside bracket must be of numerical type");
            throw new NameResolutionException();
        } else if(arrayType.ArrayType() == null){
            System.err.println("Array accessed is not an array.");
            throw new NameResolutionException();
        } // else if
        if(arrayType.ArrayType().BasicType() != null) {
            TypeNode t = new TypeNode(arrayAccessNode.get_parseTreeNode());
            t.BasicType(arrayType.ArrayType().BasicType());
            t.UpdateEnv(arrayAccessNode.getEnv());
            arrayAccessNode.setType(t);
        } else {
            ClassOrInterfaceTypeNode newClassType = new ClassOrInterfaceTypeNode(null);
            newClassType.Name(arrayType.ArrayType().Name());
            newClassType.UpdateEnv(arrayType.ArrayType().Name().getLinkedDeclaration().getEnv());
            TypeNode newTypeNode = new TypeNode(arrayAccessNode.get_parseTreeNode());
            newTypeNode.ClassOrInterfaceType(newClassType);
            newTypeNode.UpdateEnv(arrayAccessNode.getEnv());
            arrayAccessNode.setType(newTypeNode);
        } // else
    } // visit(ArrayAccessNode)

    @Override
    public void visit(LocalVariableDeclNode localVariableDeclNode) throws iVisitor.ASTException{
        if(localVariableDeclNode.Type() != null){
            visit(localVariableDeclNode.Type());
            localVariableDeclNode.setType((TypeNode) localVariableDeclNode.Type());
        } // if
        if(localVariableDeclNode.VariableInitializer() != null){
            visit(localVariableDeclNode.VariableInitializer());
            TypeNode varType=(TypeNode)localVariableDeclNode.VariableInitializer().getType();
            if(!isTypeAssignableTo(localVariableDeclNode.Type(),varType)){
                System.err.println(localVariableDeclNode.VariableDeclaratorId() + " is not type assignable by RHS");
                localVariableDeclNode.Type().print();
                varType.print();
                throw new NameResolutionException();
            } // if
        } // if
    } // visit(LocalVariableDeclNode)

    @Override
    public void visit(UnaryExprNode unaryExprNode) throws iVisitor.ASTException{
        if(unaryExprNode.UnaryExpression() != null){ //MINUS case
            visit(unaryExprNode.UnaryExpression());
            if(unaryExprNode.UnaryOperator().equals(UnaryExprNode.MINUS_OPER)){
                TypeNode exprType=(TypeNode)unaryExprNode.UnaryExpression().getType();
                if(!(exprType.isNum())){
                    System.err.println("Unary expression must be numeric following the minus operator.");
                    throw new NameResolutionException();
                } else {
                    TypeNode t = new TypeNode(unaryExprNode.get_parseTreeNode());
                    t.BasicType(exprType.BasicType());
                    t.UpdateEnv(unaryExprNode.getEnv());
                    unaryExprNode.setType(t);
                } // else
            } else if(unaryExprNode.UnaryOperator().equals(UnaryExprNode.NEG_OPER)){
                TypeNode exprType=(TypeNode)unaryExprNode.UnaryExpression().getType();
                if(!(exprType.isBoolean())){
                    System.err.println("Unary expression must return a boolean following the exclamation operator.");
                    throw new NameResolutionException();
                } else {
                    TypeNode t = new TypeNode(unaryExprNode.get_parseTreeNode());
                    t.BasicType(exprType.BasicType());
                    t.UpdateEnv(unaryExprNode.getEnv());
                    unaryExprNode.setType(t);
                } // else
            } // else if
        } // if
        if(unaryExprNode.PostfixExpression() != null){
            visit(unaryExprNode.PostfixExpression());
            unaryExprNode.setType(unaryExprNode.PostfixExpression().getType());
        } // if
        if(unaryExprNode.CastExpression() != null){
            visit(unaryExprNode.CastExpression());
            unaryExprNode.setType(unaryExprNode.CastExpression().getType());
        } // if
    } // visit(UnaryExprNode)

    @Override
    public void visit(CastExprNode castExprNode) throws iVisitor.ASTException{
        visit(castExprNode.UnaryExpression());
        TypeNode exprType=(TypeNode)castExprNode.UnaryExpression().getType();
        TypeNode typeNode = new TypeNode(castExprNode.get_parseTreeNode());

        if(castExprNode.BasicType() != null){
            if(!castExprNode.Dims()) typeNode.BasicType(castExprNode.BasicType());
            else {
                ArrayTypeNode newArray = new ArrayTypeNode(null);
                newArray.BasicType(castExprNode.BasicType());
                typeNode.ArrayType(newArray);
            } // else
        } else if(castExprNode.Name() != null){
            visit(castExprNode.Name());

            if(!castExprNode.Dims()) {
                ClassOrInterfaceTypeNode newClass = new ClassOrInterfaceTypeNode(null);
                newClass.Name(castExprNode.Name());
                newClass.UpdateEnv(castExprNode.getEnv());
                typeNode.ClassOrInterfaceType(newClass);
            } else {
                ArrayTypeNode newArray = new ArrayTypeNode(null);
                newArray.Name(castExprNode.Name());
                typeNode.ArrayType(newArray);
            } // else
        } else throw new Error();
        typeNode.UpdateEnv(castExprNode.getEnv());
        castExprNode.setType(typeNode);

        if(!(typeNode.isNum() && exprType.isNum()) &&
                !isTypeAssignableTo(typeNode, exprType) && !isTypeAssignableTo(exprType, typeNode)) {
            System.err.println("Cast does not satisfy assignability rules. Types:");
            typeNode.print();
            exprType.print();
            throw new NameResolutionException();
        } // if
    } // visit(CastExprNode)

    @Override
    public void visit(BinOpExprNode binOpExprNode) throws iVisitor.ASTException{
        if(binOpExprNode.UnaryExpr() != null) {
            visit(binOpExprNode.UnaryExpr());
            binOpExprNode.setType(binOpExprNode.UnaryExpr().getType());
        } else if (binOpExprNode.Operator().equals("INSTANCEOF")) {
            visit(binOpExprNode.LeftExpr());
            TypeNode leftType = (TypeNode) binOpExprNode.LeftExpr().getType();
            TypeNode rightType = new TypeNode(binOpExprNode.get_parseTreeNode());

            NameNode n = null;
            if(binOpExprNode.ClassOrInterfaceType() != null) {
                n = binOpExprNode.ClassOrInterfaceType().Name();
            } else if (binOpExprNode.ArrayType() != null){
                if(binOpExprNode.ArrayType().Name() != null) n = binOpExprNode.ArrayType().Name();
            } else {
                System.err.println("RHS of Instanceof cannot be NULL.");
                throw new NameResolutionException();
            } // else
            // if n is not null, resolves n
            if(n != null) {
                n.UpdateEnv(binOpExprNode.getEnv());
                if (!typeLinker.findTypeLink(n)){
                    System.err.println("Cannot resolve type of LHS of cast.");
                    throw new NameResolutionException();
                } // if
                if(binOpExprNode.ClassOrInterfaceType() != null)
                    rightType.ClassOrInterfaceType(binOpExprNode.ClassOrInterfaceType());
                else rightType.ArrayType(binOpExprNode.ArrayType());
            } else rightType.ArrayType(binOpExprNode.ArrayType());
            rightType.UpdateEnv(binOpExprNode.getEnv());
            binOpExprNode.setType(new TypeNode(BasicTypeNode.BOOLEAN_TYPE, null));
            if(!isTypeAssignableTo(leftType, rightType) && !isTypeAssignableTo(rightType,leftType)){
                System.err.println("Instanceof can only be applied to types satisfying assignability.");
                throw new NameResolutionException();
            } // if
        } else {
            visit(binOpExprNode.LeftExpr());
            if (binOpExprNode.ArrayType() != null) {
                visit(binOpExprNode.ArrayType());
            } else if (binOpExprNode.ClassOrInterfaceType() != null) {
                visit(binOpExprNode.ClassOrInterfaceType());
            } else if (binOpExprNode.RightExpr() != null) {
                visit(binOpExprNode.RightExpr());
            } else throw new Error();

            //expression check
            TypeNode leftType = (TypeNode) binOpExprNode.LeftExpr().getType();
            TypeNode rightType = (TypeNode) binOpExprNode.RightExpr().getType();

            String op = binOpExprNode.Operator();
            if (op.equals(BinOpExprNode.OPER_MULT) || op.equals(BinOpExprNode.OPER_DIV)
                    || op.equals(BinOpExprNode.OPER_PCT) || op.equals(BinOpExprNode.OPER_MINUS)) {
                if (!leftType.isNum()) {
                    System.err.println(binOpExprNode.LeftExpr().toString() + " must be a numeric type");
                    throw new NameResolutionException();
                } else if (!rightType.isNum()) {
                    System.err.println(binOpExprNode.RightExpr().toString() + " must be a numeric type");
                    throw new NameResolutionException();
                } else {
                    binOpExprNode.setType(new TypeNode(BasicTypeNode.INT_TYPE, null));
                } // else
            } else if (op.equals(BinOpExprNode.OPER_PLUS)) {
                if (!leftType.isNum()) {
                    if(leftType.isString()){
                        if (rightType != null && (!rightType.isVoid())){
                            binOpExprNode.setType(leftType);
                        } else {
                            System.err.println("cannot add type Void to type String");
                            throw new NameResolutionException();
                        } // else
                    } else if (!leftType.isString() && rightType.isString() && !leftType.isVoid()){
                        binOpExprNode.setType(rightType);
                    } else {
                        System.err.println(binOpExprNode.RightExpr().toString() + " must be a string type");
                        throw new NameResolutionException();
                    } // else
                } else if (!rightType.isNum()) {
                    if(rightType.isString() && (!leftType.isVoid())){
                        binOpExprNode.setType(rightType);
                    } else {
                        System.err.println(binOpExprNode.RightExpr().toString() + " must be a string type");
                        throw new NameResolutionException();
                    } // else
                } else { 
                    binOpExprNode.setType(new TypeNode(BasicTypeNode.INT_TYPE, null));
                } // else
            } else if (op.equals(BinOpExprNode.OPER_LT) || op.equals(BinOpExprNode.OPER_GT)
                    || op.equals(BinOpExprNode.OPER_LE) || op.equals(BinOpExprNode.OPER_GE)) {
                if (!leftType.isNum()) {
                    System.err.println(binOpExprNode.LeftExpr().toString() + " must be a numeric type");
                    throw new NameResolutionException();
                } else if (!rightType.isNum()) {
                    System.err.println(binOpExprNode.RightExpr().toString() + " must be a numeric type");
                    throw new NameResolutionException();
                } else {
                    binOpExprNode.setType(new TypeNode(BasicTypeNode.BOOLEAN_TYPE, null));
                } // else
            } else if (op.equals(BinOpExprNode.OPER_EQ) || op.equals(BinOpExprNode.OPER_NEQ)) {
                binOpExprNode.setType(new TypeNode(BasicTypeNode.BOOLEAN_TYPE, null));
                if(!isTypeAssignableTo(leftType, rightType) && !isTypeAssignableTo(rightType,leftType)){
                    System.err.println("==/!= can only be applied to types satisfying assignability.");
                    throw new NameResolutionException();
                } // if
            } else if (op.equals(BinOpExprNode.OPER_AND) || (op.equals(BinOpExprNode.OPER_OR))
                    || op.equals(BinOpExprNode.OPER_AMPAMP) || op.equals(BinOpExprNode.OPER_OROR)) {
                if (!leftType.isBoolean()) {
                    System.err.println(binOpExprNode.LeftExpr().toString() + " must be a boolean type");
                    throw new NameResolutionException();
                } else if (!rightType.isBoolean()) {
                    System.err.println(binOpExprNode.RightExpr().toString() + " must be a boolean type");
                    throw new NameResolutionException();
                } else {
                    binOpExprNode.setType(leftType);
                } // else
            } // else if
        } // else
    } // visit(BinOpExprNode)

    @Override
    public void visit(AssignmentExprNode node) throws iVisitor.ASTException{
        super.visit(node);
        if(node.Assignment() != null) node.setType(node.Assignment().getType());
        else if(node.ConditionalExpression() != null) node.setType(node.ConditionalExpression().getType());
        else throw new Error();
    } //  visit(AssignmentExprNode)

    /*******************************************************************************
     * Checks that left := right satisfies type assignability
     * *****************************************************************************/ 
    private boolean isTypeAssignableTo(TypeNode left, TypeNode right) throws ASTException{
        if(left == null || left.isNull()) {
            return false;
        } else if(right == null || right.isNull() &&
                ((left.ClassOrInterfaceType() != null) || (left.ArrayType() != null))) {
            return true;
        } else if(left.isSameType(right)){
            return true;
        } else if (left.isShort()){
            if(right.isByte()){
                return true;
            } else {
                return false;
            } // else
        } else if (left.isInt()) {
            if (right.isShort() || right.isChar() || right.isByte()) {
                return true;
            } else {
                return false;
            } // else
        } else if (right.ArrayType() != null){
            if(left.ClassOrInterfaceType() != null){
                if(left.ClassOrInterfaceType().Name().isSameName(_obj)
                        || left.ClassOrInterfaceType().Name().isSameName(_cloneable)
                        || left.ClassOrInterfaceType().Name().isSameName(_serializable)){
                    return true;
                } // if
            } // if
            if(left.ArrayType() != null && left.ArrayType().Name()!= null && right.ArrayType().Name() != null){
                ClassOrInterfaceTypeNode leftSubtype = new ClassOrInterfaceTypeNode(null);
                leftSubtype.Name(left.ArrayType().Name());
                leftSubtype.UpdateEnv(left.getEnv());
                ClassOrInterfaceTypeNode rightSubtype = new ClassOrInterfaceTypeNode(null);
                rightSubtype.Name(right.ArrayType().Name());
                rightSubtype.UpdateEnv(right.getEnv());
                return ASTTree.isSubTypeOf(rightSubtype.getDeclEnvironment().getRoot(),leftSubtype.getDeclEnvironment().getRoot());
            } // if
            return false;
        } else if(left.ClassOrInterfaceType() != null){ 
            // if left is class type, right is not array type
            if((right.ClassOrInterfaceType() != null) &&
                (ASTTree.isSubTypeOf(right.getDeclEnvironment().getRoot(),left.getDeclEnvironment().getRoot()))) {
                return true;
            } // if
            return false;
        } // else
        return false;
    } // isTypeAssignableTo()

    @Override
    public void visit(AssignmentNode assignmentNode) throws iVisitor.ASTException{
        boolean tempIsLHS = isLHS;
        isLHS = true;
        TypeNode typeNode=null;
        if(assignmentNode.Name() != null){
            isExprName = true;
            visit(assignmentNode.Name());
            isExprName = false;
            typeNode=(TypeNode)assignmentNode.Name().getType();
        } else if(assignmentNode.FieldAccess() != null){
            visit(assignmentNode.FieldAccess());
            typeNode=(TypeNode)assignmentNode.FieldAccess().getType();
        } else if(assignmentNode.ArrayAccess() != null){
            visit(assignmentNode.ArrayAccess());
            typeNode=(TypeNode)assignmentNode.ArrayAccess().getType();
        } // else if
        isLHS = tempIsLHS;
        assignmentNode.setType(typeNode);
        visit(assignmentNode.AssignmentExpression());
        TypeNode exprType=(TypeNode)assignmentNode.AssignmentExpression().getType();

        if(!isTypeAssignableTo(typeNode, exprType)) {
            System.err.println("RHS not assignable to LHS");
            throw new NameResolutionException();
        } // if
    } // visit(AssignmentNode)

    /*******************************************************************************
     * Determine implicit super 0 arg constructor (if t is a class that is not Object)
     * *****************************************************************************/ 
    private void resolveImplicitConstructor() throws NameResolutionException{
        // searches super classes for the constructor
        for(ASTTree t: _root.getSuper()){
            if(!t.isClass()) continue;
            for(MethodData m: t.getConstructorsDeclared()){
                if(m.hasNoParams()) {
                    _root.setImplicitSuperConstructor(m);
                    return;
                } // if
            } // for
        } // for
        System.err.println("The class " + _root.getRoot().getEnv().getScopePair().getName() + " has no implicit 0 arg super constructor");
        throw new NameResolutionException();
    } // resolveImplicitConstructor()

    /*******************************************************************************
     * Checks that simpleName is declared before enclosingEntityName in classEnv
     * *****************************************************************************/
    private void checkDeclOrder(String simpleName) throws NameResolutionException{
        // go through all pairs looking for which of simpleName, enclosingEntityName comes first
        for(EnvironmentPair p: _root.getRoot().getEnv().getPairs()){
            if(p.getName().equals(enclosingEntityName)){
                System.err.println(simpleName+" is used before being declared.");
                throw new NameResolutionException();
            } else if(p.getName().equals(simpleName)) return;
        } // for
    } // checkDeclOrder()

    /*******************************************************************************
     * Resolves ambiguous name node by storing a ptr to the decl in node
     * notes: returns 0 for a PackageName, 1 for TypeName, 2 for ExprName
     * *****************************************************************************/
    private int resolveAmbiguousName(NameNode node) throws ASTException{
        if(node.isQualified()){
            // prefix consisting of all the names in node except the last
            NameNode prefix = new NameNode(null);
            int numNames = node.NameList().size();
            for(int i = 0; i < numNames -1; ++i){
                prefix.addName(node.NameList().get(i));
            } // for
            prefix.UpdateEnv(node.getEnv());
            int retCode = resolveAmbiguousName(prefix);
            node.setPrefixes(prefix.getPrefixes());
            node.addPrefix(node);

            if(retCode == 0){ 
                // if prefix is a packageName
                // get global environment
                Environment currEnv = _root.getRoot().getEnv().getParent();
                if(!currEnv.getScopePair().getName().equals("##GLOBAL##")) throw new Error();

                // determine if node is a type in a package
                for(Environment e: currEnv.getChildren()){
                    if(e.getPackage().toString().equals(node.getAllButLastName())
                            && e.getScopePair().getName().equals(node.getSimple())){
                        node.setLinkedDeclaration(e.getScopePair().getDeclaration());
                        return 1;
                    } // if
                } // for
                // it's a packageName
                return 0; 
            } else if(retCode == 1) { 
                // if prefix is a typeName
                // check if node is a STATIC field of the prefix
                Environment prefixEnv = prefix.getLinkedDeclaration().getEnv();
                if (searchFields(node,prefixEnv.getRoot(), true, false,false)) {
                    return 2;
                } else {
                    System.err.println(node.getSimple() + " cannot resolve to a static field.");
                    throw new NameResolutionException();
                } // else
            } else { 
                // if retcode == 2 (prefix is an ExpressionName)
                // get the type of the prefix (must be a classOrInterfacetype)
                TypeNode type = (TypeNode) prefix.getLinkedDeclaration().getType();
                if(type.ClassOrInterfaceType() == null) {
                    System.err.println(node.getSimple() + " has a non-ClassOrInterfaceType prefix.");
                    throw new NameResolutionException();
                } // if
                Environment prefixTypeEnv = type.getDeclEnvironment();
                // check if node is a NON-STATIC field of the prefix
                if (searchFields(node, prefixTypeEnv.getRoot(), false, true,false)) {
                    return 2;
                } else {
                    System.err.println(node.getSimple() + " cannot resolve to a non-static field.");
                    throw new NameResolutionException();
                } // else
            } // else
        } else { 
            // if node is simple name
            node.addPrefix(node);
            if(searchLocals(node) ||
                    searchFields(node, node.getEnv().getRoot(), false, true,true)) {
                return 2;
            } else {
                // if name resolves to a type it's a typeName
                if (typeLinker.findTypeLink(node)){
                    return 1;
                } // if
                // it's a packageName
                return 0; 
            } // else
        } // else
    } // resolveAmbiguousName()

    /*******************************************************************************
     * Determines if field is accessible in _root, if it was declared in declaredin
     * notes: prefix is the type of the prefix (if there is any)
     *        isSimple indicates if method invocation was a call or not (i.e. ___ . name())
     * *****************************************************************************/
    private boolean isFieldAccessible (FieldData field, ASTTree prefix, ASTTree declaredin, boolean isSimple){
        if(!field.isPublic()) { 
            // protected checks
            // if the field is declared in a different package than  where it's accessed,
            if (!declaredin.getRoot().getEnv().getPackage().toString().equals
                    (_root.getRoot().getEnv().getPackage().toString())) {
                // checks that the type containing the field access is a subtype of the type where it's declared
                if (!ASTTree.isSubTypeOf(_root, declaredin)) {
                    return false;
                } // if
                // if instance field and the field access is a qualified access, checks that the prefix type
                // is a subtype of the class where the access occurs
                if (!field.isStatic() && !isSimple && !ASTTree.isSubTypeOf(prefix, _root)) {
                    return false;
                } // if
            } // if
        } // if
        return true;
    } // isFieldAccessible()

    /*******************************************************************************
     * Tries to resolve node as a field
     * notes: returns true/false depending if it was successful
     *        mustBeStatic = true if field must be static ; 
     *        mustBeNonStatic = true if field must not be static (if field is not
     *        static/nonstatic as it needs to be, returns false)
     *        if both mustBeStatic, mustBeNonStatic are false then field can be either static or nonstatic
     *        isSimple = true if node is a simple field name (i.e. not ___.node)
     * *****************************************************************************/
    private boolean searchFields(NameNode node, ASTTree prefixRoot, boolean mustBeStatic,
                                 boolean mustBeNonStatic, boolean isSimple)
            throws NameResolutionException {
        String simpleName = node.getSimple();
        // searches the declared fields
        for(FieldData field: prefixRoot.getFieldsDeclared()){
            if(field.getFieldName().equals(simpleName)) {
                // if field is not static, checks where node occurs
                if (isSimple && occursInStatic && !field.isStatic()){
                    System.err.println("The field " + simpleName + " has an implicit this access.");
                    throw new NameResolutionException();
                } else if(!field.isStatic() && !occursInStatic && prefixRoot == _root) {
                    // check declaration before use conditions
                    if (!isLHS && occursInVarInit && isSimple) {
                        checkDeclOrder(simpleName);
                    } // if
                } // else if
                if (field.isStatic() && mustBeNonStatic) {
                    return false;
                } else if (!field.isStatic() && mustBeStatic) {
                    return false;
                } // else if
                if(!isFieldAccessible(field,prefixRoot,prefixRoot,isSimple)) return false;

                node.setLinkedDeclaration(field.getDecl());
                node.setType(field.getType());
                return true;
            } // if
        } // for
        if(mustBeStatic) return false;
        boolean isResolved = false;
        // searches the inherited fields
        for(FieldData field: prefixRoot.getFieldsInherited()){
            if(field.getFieldName().equals(simpleName)) {
                // if field is not static, checks where node occurs
                if (isSimple && occursInStatic && !field.isStatic()){
                    System.err.println("The field " + simpleName + " has an implicit this access.");
                    throw new NameResolutionException();
                } // if
                if (field.isStatic() && mustBeNonStatic) {
                    return false;
                } else if (!field.isStatic() && mustBeStatic) {
                    return false;
                } // else if
                // astTree where field is declared
                ASTTree fieldRoot = field.getDecl().getEnv().getRoot();
                if(!isFieldAccessible(field,prefixRoot,fieldRoot,isSimple)) return false;

                if(isResolved && node.getLinkedDeclaration() != field.getDecl()){
                    // should never happen
                    System.err.println(simpleName+" resolves to multiple inherited fields.");
                    throw new NameResolutionException();
                } // if
                node.setLinkedDeclaration(field.getDecl());
                node.setType(field.getType());
                isResolved = true;
            } // if
        } // for
        return isResolved;
    } // searchFields()

    /*******************************************************************************
     * Searches local scope of node; node must be a simple name
     * *****************************************************************************/
    private boolean searchLocals(NameNode node){
        String simpleName = node.getSimple();
        // current environment
        Environment currEnv = node.getEnv();
        // searches local variables/ parameters; stops at the method environment
        while(true) {
            // searches current environment's pairs
            for(EnvironmentPair p: currEnv.getPairs()){
                if(p.getName().equals(simpleName)){
                    node.setLinkedDeclaration(p.getDeclaration());
                    node.setType(node.getLinkedDeclaration().getType());
                    return true;
                } // if
            } // for
            if(currEnv.getParent().getPackage() != null) break;
            currEnv = currEnv.getParent();
        } // while
        return false;
    } // searchLocals()

    /*******************************************************************************
     * Tries to resolve the nameNode node (assuming it's an expression name),
     * stores the ptr to its decl
     * notes: throws exception if it cannot be resolved
     * *****************************************************************************/
    private void resolveExprName(NameNode node) throws ASTException {
        if(!node.isQualified()) {
            node.addPrefix(node);
            if(searchLocals(node)) {
                return;
            } // if
            if(searchFields(node, _root, false, false, true)) {
                return;
            } else {
                System.err.println("Cannot resolve the exprName "+node.toString());
                throw new NameResolutionException();
            } // else
        } else { 
            // if node is qualified
            // prefix consisting of all the names in node except the last
            NameNode prefix = new NameNode(null);
            int numNodes = node.NameList().size();
            for(int i = 0; i < numNodes-1; ++i){
                prefix.addName(node.NameList().get(i));
            }
            prefix.UpdateEnv(node.getEnv());
            int retCode = resolveAmbiguousName(prefix);
            node.setPrefixes(prefix.getPrefixes());
            node.addPrefix(node);
            if(retCode == 0) {
                System.err.println("ExprName "+node.toString() + " has prefix of a packageName.");
                throw new NameResolutionException();
            } else if (retCode == 1){
                Environment prefixEnv = prefix.getLinkedDeclaration().getEnv();
                if(prefixEnv.getRoot().isInterface()) { 
                    // type should be an interface type
                    System.err.println("Type of field is interface type.");
                    throw new NameResolutionException();
                } // if
                if (!searchFields(node,prefixEnv.getRoot(), true, false,false)) {
                    System.err.println(node.getSimple() + " cannot resolve to a static field.");
                    throw new NameResolutionException();
                } else node.setType(node.getLinkedDeclaration().getType());
            } else { 
                // if retcode == 2 (exprName)
                TypeNode prefixType = (TypeNode) prefix.getType();

                // check if node is a NON-STATIC field of the prefix
                if (node.getSimple().equals("length") && prefixType.ArrayType() != null) { 
                    // AND IS ARRAYTYPE
                    if(isLHS) {
                        System.err.println("length cannot occur on the LHS.");
                        throw new NameResolutionException();
                    } // if
                    node.setType(new TypeNode(BasicTypeNode.INT_TYPE, null));
                } else {
                    if(prefixType.ClassOrInterfaceType() == null){
                        System.err.println("Non-classOrInterfaceType has no fields.");
                        throw new NameResolutionException();
                    } // if
                    // get the environment of the prefix
                    Environment prefixEnv =  prefixType.getDeclEnvironment();
                    if (!searchFields(node, prefixEnv.getRoot(),false,true,false)){
                        System.err.println(node.getSimple() + " cannot resolve to a non-static field.");
                        throw new NameResolutionException();
                    } else node.setType(node.getLinkedDeclaration().getType());
                } // else
            } // else
        } // else 
    } // resolveExprName()

    @Override
    public void visit(PostFixExprNode node)throws ASTException{
        if(node.Name() != null) {
            isExprName = true;
            visit(node.Name());
            node.setType(node.Name().getType());
            isExprName = false;
        } else {
            visit(node.Primary());
            node.setType(node.Primary().getType());
        } // else
    } // visit(PostFixExprNode)

    @Override
    public void visit(NameNode node)throws ASTException{
        if(isExprName) {
            resolveExprName(node);
        } // if
    } // visit(NameNode)

    @Override
    public void visit(MethodDeclarationNode node)throws ASTException{
        occursInStatic = node.MethodHeader().getMethodData().isStatic();
        super.visit(node);
        occursInStatic = false;
    } // visit(MethodDeclarationNode)

    @Override
    public void visit(MethodHeaderNode node)throws ASTException{
        enclosingEntityName = node.MethodDeclarator().Identifier();
        super.visit(node);
    } // visit(MethodHeaderNode)
    
    @Override
    public void visit(ConstructorDeclarationNode node)throws ASTException{
        occursInStatic = node.getMethodData().isStatic();
        enclosingEntityName = node.ConstructorDeclarator().Identifier();
        super.visit(node);
        occursInStatic = false;
    } // visit(ConstructorDeclarationNode)

    @Override
    public void visit(FieldDeclNode node)throws ASTException{
        occursInStatic = node.getFieldData().isStatic();
        enclosingEntityName = node.VariableDeclarator().VariableDeclarator();
        node.setType(node.Type());
        visit(node.VariableDeclarator());
        if(node.VariableDeclarator().VariableInitializer() != null) {
            if (!isTypeAssignableTo(node.Type(), (TypeNode) node.VariableDeclarator().VariableInitializer().getType())) {
                System.err.println("RHS not assignable to LHS (a field decl)");
                throw new NameResolutionException();
            } // if
        } // if
        occursInStatic = false;
    } // visit(FieldDeclNode)

    // check static vs non-static (where it occurs)
    @Override
    public void visit(FieldAcccessNode node)throws ASTException{
        // visits primary
        boolean tempIsLHS = isLHS;
        isLHS = false;
        super.visit(node);
        isLHS = tempIsLHS;
        TypeNode type = (TypeNode) node.Primary().getType();

        if (type.ClassOrInterfaceType() != null) {
            Environment prefixEnv = type.getDeclEnvironment();
            NameNode newName = new NameNode(null);
            newName.addName(node.Identifier());
            newName.UpdateEnv(node.getEnv());
            // check if node is a NON-STATIC field of the prefix
            if (!searchFields(newName, prefixEnv.getRoot(), false, true,false)){
                System.err.println(node.Identifier() + " cannot resolve to a non-static field.");
                throw new NameResolutionException();
            } else {
                node.setType(newName.getType());
                node.setDecl(newName.getLinkedDeclaration());
            } // else
        } else if(type.ArrayType() != null){
            if(node.Identifier().equals("length")){
                if(isLHS) {
                    System.err.println("length cannot occur on the LHS");
                    throw new NameResolutionException();
                }
                node.setType(new TypeNode(BasicTypeNode.INT_TYPE,null));
            } else {
                System.err.println("Invalid array property: " + node.Identifier());
                throw new NameResolutionException();
            } // else
        } else {
            System.err.println("Invalid attempt to access field.");
            throw new NameResolutionException();
        } // else
    } // visit(FieldAcccessNode)

    @Override
    public void visit(ClassDeclNode node)throws ASTException{
        if(node != _obj.getLinkedDeclaration()) resolveImplicitConstructor();
        super.visit(node);
    } // visit(ClassDeclNode)

    @Override
    public void visit(VariableDeclNode node)throws ASTException{
        if(node.VariableInitializer() != null){
            boolean oldOccurs = occursInVarInit;
            occursInVarInit = true;
            visit(node.VariableInitializer());
            occursInVarInit = oldOccurs;
            node.setType(node.VariableInitializer().getType());
        } // if
    } // visit(VariableDeclNode)

    @Override
    public void visit(LiteralNode node)throws ASTException{
        node.setType(new TypeNode(node.LiteralType(), node.getEnv()));
    } // visit(LiteralNode)

    @Override
    public void visit(PrimaryNode primaryNode)throws ASTException{
        if(primaryNode.PrimaryNoNewArray() != null){
            visit(primaryNode.PrimaryNoNewArray());
            primaryNode.setType(primaryNode.PrimaryNoNewArray().getType());
        } else if(primaryNode.ArrayCreationExpression() != null){
            visit(primaryNode.ArrayCreationExpression());
            primaryNode.setType(primaryNode.ArrayCreationExpression().getType());
        } // else if
    } // visit(PrimaryNode)

    @Override
    public void visit(PrimaryNoNewArrayNode primaryNoNewArrayNode)throws ASTException{
        if(occursInStatic && primaryNoNewArrayNode.This()) {
            System.err.println("Cannot use \"this\" in an abstract class/field initializer");
            throw new NameResolutionException();
        } // if

        if(primaryNoNewArrayNode.This()){
            NameNode thisName = new NameNode(null);
            thisName.addName(_root.getRoot().getEnv().getScopePair().getName());
            thisName.setLinkedDeclaration(_root.getRoot().getEnv().getScopePair().getDeclaration());
            ClassOrInterfaceTypeNode classType = new ClassOrInterfaceTypeNode(null);
            classType.Name(thisName);
            classType.UpdateEnv(primaryNoNewArrayNode.getEnv());
            TypeNode t = new TypeNode(primaryNoNewArrayNode.get_parseTreeNode());
            t.ClassOrInterfaceType(classType);
            t.UpdateEnv(primaryNoNewArrayNode.getEnv());
            primaryNoNewArrayNode.setType(t);
        } // if

        if(primaryNoNewArrayNode.Literal()!= null){
            visit(primaryNoNewArrayNode.Literal());
            primaryNoNewArrayNode.setType(primaryNoNewArrayNode.Literal().getType());
        } // if

        if(primaryNoNewArrayNode.Expression() != null){
            visit(primaryNoNewArrayNode.Expression());
            primaryNoNewArrayNode.setType(primaryNoNewArrayNode.Expression().getType());
        } // if
        if(primaryNoNewArrayNode.ClassInstanceCreationExpression() != null){
            visit(primaryNoNewArrayNode.ClassInstanceCreationExpression());
            primaryNoNewArrayNode.setType(primaryNoNewArrayNode.ClassInstanceCreationExpression().getType());
        } // if
        if(primaryNoNewArrayNode.FieldAccess() != null){
            visit(primaryNoNewArrayNode.FieldAccess());
            primaryNoNewArrayNode.setType(primaryNoNewArrayNode.FieldAccess().getType());
        } // if
        if(primaryNoNewArrayNode.MethodInvocation() != null){
            visit(primaryNoNewArrayNode.MethodInvocation());
            primaryNoNewArrayNode.setType(primaryNoNewArrayNode.MethodInvocation().getType());
        } // if
        if(primaryNoNewArrayNode.ArrayAccess() != null){
            visit(primaryNoNewArrayNode.ArrayAccess());
            primaryNoNewArrayNode.setType(primaryNoNewArrayNode.ArrayAccess().getType());
        } // if
    } // visit(PrimaryNoNewArrayNode)

    @Override
    public void visit(MethodInvocationNode node) throws ASTException{
        super.visit(node);

        List<FormalParameterNode> argTypes = new ArrayList<>();
        int numArgs = node.ArgumentList().size();
        for(int i = 0; i < numArgs; ++i){
            AssignmentExprNode arg = node.ArgumentList().get(i);
            FormalParameterNode param = new FormalParameterNode(null);
            param.Type((TypeNode) arg.getType());
            argTypes.add(param);
        } // for

        if(node.Name() != null) {
            NameNode nameNode = node.Name();
            if(!nameNode.isQualified()) {
                if(!searchMethods(nameNode, argTypes, _root, false, true,true, false)) {
                    System.err.println("Cannot resolve the simple MethodInvocation "+node.Name().toString());
                    throw new NameResolutionException();
                }
            } else { // if nameNode is qualified
                NameNode prefix = new NameNode(null);
                for(int i = 0; i < nameNode.NameList().size()-1; i++){
                    prefix.addName(nameNode.NameList().get(i));
                }
                prefix.UpdateEnv(node.getEnv());
                int retCode = resolveAmbiguousName(prefix);
                if(retCode == 0) {
                    System.err.println("MethodInvocation "+nameNode.toString() + " has prefix of a packageName.");
                    throw new NameResolutionException();
                } else if (retCode == 1) {
                    node.TypeName(prefix);
                    Environment prefixEnv = prefix.getLinkedDeclaration().getEnv();
                    if(prefixEnv.getRoot().isInterface()) {
                        System.err.println("MethodInvocation "+nameNode.toString() + " has an interface type as prefix.");
                        throw new NameResolutionException();
                    }
                    if (!searchMethods(node.Name(),argTypes,prefixEnv.getRoot(), true, false,false,false)) {
                        System.err.println(node.Name().getSimple() + " cannot resolve to a static method.");
                        throw new NameResolutionException();
                    }
                } else { // if prefix is fieldName
                    // get the environment of the prefix
                    node.FieldName(prefix);
                    TypeNode prefixType = (TypeNode) prefix.getType();

                    if(prefixType.ClassOrInterfaceType() == null && prefixType.ArrayType() == null){
                            System.err.println("Non-classOrInterfaceType has no methods.");
                            throw new NameResolutionException();

                    }
                    ASTTree prefixRoot = null;
                    boolean isArray = false;
                    if(prefixType.ClassOrInterfaceType() != null){
                        prefixRoot = prefixType.getDeclEnvironment().getRoot();
                    } else {
                        isArray = true;
                        prefixRoot = objectClass;
                    }
                        // check if node is a NON-STATIC method of the prefix
                        if (!searchMethods(node.Name(), argTypes, prefixRoot, false, true, false, isArray)) {
                            System.err.println(nameNode.toString() + " cannot resolve to a non-static method.");
                            throw new NameResolutionException();
                        }
                }
            }
            node.setDecl(nameNode.getLinkedDeclaration());
            node.setType(((MethodHeaderNode) nameNode.getLinkedDeclaration()).ReturnType());
        } else { 
            // if (node.Primary() != null) 
            // get the type of primary
            TypeNode type = (TypeNode) node.Primary().getType();

            if(type.ClassOrInterfaceType() == null && type.ArrayType() == null){
                System.err.println("Primary expression is not a class/interface type.");
                throw new NameResolutionException();
            } // if
            ASTTree prefixRoot = null;
            boolean isArray = false;
            if(type.ClassOrInterfaceType() != null){
                prefixRoot = type.getDeclEnvironment().getRoot();
            } else {
                isArray = true;
                prefixRoot = objectClass;
            } // else

            NameNode idName = new NameNode(null);
            idName.addName(node.Identifier());
            idName.UpdateEnv(node.getEnv());

            // check if node is a method of the prefix
            if (!searchMethods(idName,argTypes,prefixRoot,false,true,false, isArray)){
                System.err.println(idName.toString() + " cannot resolve to a non-static method.");
                throw new NameResolutionException();
            } // if
            node.setDecl(idName.getLinkedDeclaration());
            node.setType(((MethodHeaderNode) idName.getLinkedDeclaration()).ReturnType());
        } // else
    } // visit(MethodInvocationNode)

    /*******************************************************************************
     * Determines if meth is accessible in _root, if it was declared in declaredin
     * notes: prefix is the type of the prefix (if there is any)
     *        isSimple indicates if method invocation was a call or not (i.e. ___ . name())
     * *****************************************************************************/
    private boolean isMethodAccessible (MethodData meth, ASTTree prefix, ASTTree declaredin, boolean isSimple){
        if(!meth.isPublic()) {
            // if method is declared in different package than where it's used
            if (!declaredin.getRoot().getEnv().getPackage().toString().equals
                    (_root.getRoot().getEnv().getPackage().toString())) {
                // checks that type where method is invoked is a subtype of the class where it's declared
                if (!ASTTree.isSubTypeOf(_root, declaredin)) {
                    return false;
                } // if
                // if method is nonstatic and there is a prefix, checks that the prefix's root is a subtype
                // of the class where the method is invoked
                if (!meth.isStatic() && !isSimple && !ASTTree.isSubTypeOf(prefix, _root)) {
                    return false;
                } // if
            } // if
        } // if
        return true;
    } // isMethodAccessible()

    /*******************************************************************************
     * Searches for the method node with signature argTypes in root
     * notes: mustBeStatic/mustBeNonStatic indicate if the method must be static/nonstatic
     *        isSimple = true when node is a simple name (not ___ . node)
     *        prefixroot is the root of the prefix; this is null if there is no prefix
     *        isArray indicates if prefix is array
     * *****************************************************************************/
    private boolean searchMethods(NameNode node, List<FormalParameterNode> argTypes, ASTTree prefixRoot, boolean mustBeStatic,
                                  boolean mustBeNonStatic, boolean isSimple, boolean isArray) throws NameResolutionException{
        String simpleName = node.getSimple();
        // searches the declared methods
        for(MethodData meth: prefixRoot.getMethodsDeclared()){
            // tries to resolve the method name (and store a ptr to its decl)
            if(meth.isSameSignature(simpleName, argTypes)){
                // if method is not accessible, continue searching
                if(!isMethodAccessible(meth, prefixRoot, prefixRoot, isSimple)) {
                    if(!(isArray && prefixRoot == objectClass && simpleName.equals("clone"))){
                        continue;
                    } // if
                } // if

                // method checks that static matches mustBeStatic, mustBeNonStatic
                if (meth.isStatic() && mustBeNonStatic) {
                    return false;
                } else if (!meth.isStatic() && mustBeStatic) {
                    return false;
                } // else if
                node.setLinkedDeclaration(meth.getDecl());
                // if the method invocation occurs in static entity and node is simple and meth is nonstatic, error
                if(occursInStatic && !meth.isStatic() && isSimple) {
                    System.err.println("MethodInvocation occurs in static method but has implicit this.");
                    throw new NameResolutionException();
                } // if
                return true;
            } // if
        } // for
        // searches the inherited methods
        for(MethodData meth: prefixRoot.getMethodsInherited()){
            if(meth.isSameSignature(simpleName, argTypes)){
                // root where method is declared
                ASTTree methodRoot = meth.getDecl().getEnv().getRoot();
                // if meth is not accessible, continue searching
                if(!isMethodAccessible(meth, prefixRoot, methodRoot, isSimple)) continue;

                // method checks that static matches mustBeStatic, mustBeNonStatic
                if (meth.isStatic() && mustBeNonStatic) {
                    return false;
                } else if (!meth.isStatic() && mustBeStatic) {
                    return false;
                } // else if
                node.setLinkedDeclaration(meth.getDecl());

                // if the method invocation occurs in static entity and node is simple and meth is nonstatic, error
                if(occursInStatic && !meth.isStatic() && isSimple) {
                    System.err.println("MethodInvocation occurs in static method but has implicit this.");
                    throw new NameResolutionException();
                } // if
                // method invocation resolved to a method (possibly abstract)
                return true; 
            } // if
        } // for
        return false;
    } // searchMethods()
} // class TypeChecker