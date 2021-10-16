/*******************************************************************************
 * EnvironmentBuilder.java
 * 
 * A module implementing the environment building.
 * ****************************************************************************/
package cs444.group9.AST.Visitor;

//import com.sun.corba.se.impl.corba.EnvironmentImpl;
import cs444.group9.AST.Node.Body.*;
import cs444.group9.AST.Node.Declaration.*;
import cs444.group9.AST.Node.Environments.Environment;
import cs444.group9.AST.Node.Environments.EnvironmentPair;
import cs444.group9.AST.Node.Statement.*;
import cs444.group9.AST.Node.*;
import cs444.group9.AST.Node.Array.ArrayAccessNode;
import cs444.group9.AST.Node.Array.FieldAcccessNode;
import cs444.group9.AST.Node.Array.PrimaryNoNewArrayNode;
import cs444.group9.AST.Node.Array.PrimaryNode;
import cs444.group9.AST.Node.Expression.*;
import cs444.group9.AST.Node.StatementExpression.AssignmentNode;
import cs444.group9.AST.Node.StatementExpression.ClassInstanceCreationExprNode;
import cs444.group9.AST.Node.StatementExpression.MethodInvocationNode;
import cs444.group9.AST.Node.StatementExpression.StatementExprNode;
import cs444.group9.AST.Node.Type.ArrayTypeNode;
import cs444.group9.AST.Node.Type.BasicTypeNode;
import cs444.group9.AST.Node.Type.ClassOrInterfaceTypeNode;
import cs444.group9.AST.Node.Type.TypeNode;

import java.util.*;

// *********************** ENVIRONMENT DESCRIPTION
// Root Environment:    scopeName = "##GLOBAL##"
//                      pairs and children are the classes/interfaces
// Environment of Class/Interface: scopeName = class/interface name
//                                 a pair for each method/constructor/field
//                                 children are constructor/method environments

// Environment of Constructor/Method: scopeName = method name
//                                    pairs are the formal parameters
//                                    child is methodbody block environment
// Environment of Field: scopeName = field name
//                       no pairs, no children
// Environment of ForLoop: scopeName = "##FORLOOP##"
//                         0 or 1 pair for forInit
//
// Environment of Block: scopeName = "##BLOCK##"
//                       no pairs
//                       children are any subblocks or localVars
// Environment of LocalVar: scopeName = "##LOCALVAR##"
//                       pair is local variable
//                       no child


public class EnvironmentBuilder extends DefaultVisitor {
    /*******************************************************************************
     * VISIT METHODS
     * *****************************************************************************/ 
    public void visit(CompilationUnitNode node, Environment e)
            throws EnvironmentBuildingException {
        if(node.TypeDeclaration() != null){
            visit(node.TypeDeclaration(), e);
        } // if
        Environment classEnv;
        if(node.TypeDeclaration() != null) classEnv = e.getChildren().get(e.getChildren().size()-1);
        else {
            // create a new environment
            classEnv = new Environment(new EnvironmentPair("_NOCLASS_", node));
            classEnv.setParent(e);
            e.addChild(classEnv);
        } // else

        NameNode LibNode = new NameNode(node.get_parseTreeNode());
        LibNode.addName("java");
        LibNode.addName("lang");
        ImportDeclNode newImport = new ImportDeclNode(node.get_parseTreeNode());
        newImport.Name(LibNode);
        newImport.isSingleType(false);
        node.AddImportDeclaration(newImport);

        if(node.PackageDeclaration() != null){
            visit(node.PackageDeclaration(), classEnv);
        } else {
            // unnamed package
            NameNode packageNode = new NameNode(node.get_parseTreeNode());
            packageNode.addName("_DEFAULTPACKAGE_");
            packageNode.UpdateEnv(classEnv);
            classEnv.setPackage(packageNode);
        } // else
        for(ImportDeclNode child: node.ImportDeclarations()){
            visit(child, classEnv);
        } // for
        node.UpdateEnv(classEnv);
    } // visit(CompilationUnitNode)

    public void visit(PackageDeclNode packageDeclNode, Environment e) {
        if(packageDeclNode.Name() != null){
            visit(packageDeclNode.Name(), e);
            e.setPackage(packageDeclNode.Name());
        } else throw new Error();
        packageDeclNode.UpdateEnv(e);
    } // visit(PackageDeclNode)

    public void visit(ImportDeclNode importDeclNode, Environment e) throws EnvironmentBuildingException{
        if(importDeclNode.isSingleType()){
            // check that no two single-type-imports clash

            // is this import an exact duplicate of another?
            boolean isDuplicate = false;
            for(NameNode n: e.getSingleTypeImports()){
                if(n.getSimple().equals(importDeclNode.Name().getSimple())
                        && !n.toString().equals(importDeclNode.Name().toString())) {
                    System.err.println("Single-Type-Imports clash with each other");
                    throw new EnvironmentBuildingException();
                } else if(n.getSimple().equals(importDeclNode.Name().getSimple())){
                    isDuplicate = true;
                } // else if
            } // for
            if(importDeclNode.Name().getSimple().equals(e.getScopePair().getName())
                    && !importDeclNode.Name().getAllButLastName().equals(e.getPackage().toString())){
                System.err.println("Single-Type-Import clashes with current class");
                throw new EnvironmentBuildingException();
            } // if
            if(!isDuplicate) e.addSingleTypeImport(importDeclNode.Name());
        } // if
        visit(importDeclNode.Name(), e);
        importDeclNode.UpdateEnv(e);
    } // visit(ImportDeclNode)

    public void visit(ClassDeclNode node, Environment e) throws EnvironmentBuildingException{
        // create a pair for the class, add it to the global environement
        EnvironmentPair classPair = new EnvironmentPair(node.Identifier(), node);
        e.addPair(classPair);

        // create an environment for the class, and link it to e
        Environment classEnv = new Environment(classPair);
        classEnv.setParent(e);
        e.addChild(classEnv);

        // recurses on children
        for(NameNode element: node.Interfaces()){
            visit(element, classEnv);
        } // for
        if(node.ClassType() != null){
            visit(node.ClassType(), classEnv);
        } // if
        if (node.ClassBody() != null) {
            visit(node.ClassBody(), classEnv);
        } // if

        node.UpdateEnv(classEnv);
    } // visit(ClassDeclNode)

    public void visit(TypeDeclNode node, Environment e)
            throws EnvironmentBuildingException {
        if(node.ClassDeclaration() != null) {
            visit(node.ClassDeclaration(), e);
        } else if(node.InterfaceDeclaration() != null) {
            visit(node.InterfaceDeclaration(), e);
        } // else if
        node.UpdateEnv(e);
    } // visit(TypeDeclNode)

    public void visit(ClassBodyDeclNode node, Environment e) throws EnvironmentBuildingException {
        if (node.ClassMemberDeclaration() != null){
            visit(node.ClassMemberDeclaration(), e);
        } else if (node.ConstructorDeclaration() != null) {
            visit(node.ConstructorDeclaration(), e);
        } // else if
        node.UpdateEnv(e);
    } // visit(ClassBodyDeclNode)

    public void visit(ConstructorDeclarationNode node, Environment e)
            throws EnvironmentBuildingException{
        // add constructor name to class environment e, and add a new environment
        // with the method params as the last child
        visit(node.ConstructorDeclarator(), e, node);

        // get the method environment from e (the last child)
        Environment methodEnv = e.getChildren().get(e.getChildren().size()-1);

        // visit each child with (___, methodEnv)
        visit(node.ConstructorBody(), methodEnv);

        node.UpdateEnv(methodEnv);
    } // visit(ConstructorDeclarationNode)

    public void visit(ConstructorBodyNode constructorBodyNode, Environment e)
            throws EnvironmentBuildingException{
        for(BlockStatementNode stmt: constructorBodyNode.BlockStatements()){
            visit(stmt, e);
        } // for
        constructorBodyNode.UpdateEnv(e);
    } // visit(ConstructorBodyNode)

    public void visit(ClassMemberDeclNode node, Environment e) throws EnvironmentBuildingException {
        if(node.FieldDeclaration() != null){
            visit(node.FieldDeclaration(), e);
        } else if(node.MethodDeclaration() != null){
            visit(node.MethodDeclaration(), e);
        } // else if
        node.UpdateEnv(e);
    } // visit(ClassMemberDeclNode)

    public void visit(MethodDeclarationNode node, Environment e)
            throws EnvironmentBuildingException {
        // add method name to class environment e, and add a new environment
        // with the method params as the last child
        visit(node.MethodHeader(), e);

        // get the method environment from e (the last child)
        Environment methodEnv = e.getChildren().get(e.getChildren().size()-1);

        // visit each child with (___, methodEnv)
        visit(node.MethodBody(), methodEnv);

        node.UpdateEnv(methodEnv);
    } // visit(MethodDeclarationNode)

    public void visit(FieldDeclNode node, Environment e) throws EnvironmentBuildingException {
        // creates pair for the field
        String fieldName = node.VariableDeclarator().VariableDeclarator();
        EnvironmentPair fieldPair = new EnvironmentPair(fieldName, node);

        // checks that  fieldName is not the name of any other field in this class
        List <EnvironmentPair> currPairs = e.getPairs();
        for( EnvironmentPair p: currPairs){
            String type = p.getDeclaration().get_parseTreeNode().getLexeme();
            if(type.equals("FieldDeclaration") && fieldName.equals(p.getName())) {
                System.err.println("The field " + fieldName + " is declared twice.");
                throw new EnvironmentBuildingException();
            } // if
        } // for
        // adds pair to the class environment
        e.addPair(fieldPair);

        // create environment for the field, link it to e
        Environment fieldEnv = new Environment(fieldPair);
        fieldEnv.setParent(e);
        e.addChild(fieldEnv);

        // recurse on children
        if (node.Type() != null){
            visit(node.Type(), fieldEnv);
            node.setType(node.Type());
        } // if
        visit(node.VariableDeclarator(), fieldEnv);
        node.UpdateEnv(fieldEnv);
    } // visit(FieldDeclNode)

    public void visit(ArrayTypeNode arrayTypeNode, Environment e) {
        if(arrayTypeNode.BasicType() != null){
            visit(arrayTypeNode.BasicType(), e);
        } else if(arrayTypeNode.Name() != null){
            visit(arrayTypeNode.Name(), e);
        } // else if
        arrayTypeNode.UpdateEnv(e);
    } // visit(ArrayTypeNode)

    public void visit(VariableDeclNode node, Environment e)
            throws EnvironmentBuildingException{
        if (node.VariableInitializer() != null){
            visit(node.VariableInitializer(), e);
        } // if
        node.UpdateEnv(e);
    } // visit(VariableDeclNode)

    public void visit(ClassOrInterfaceTypeNode classOrInterfaceTypeNode, Environment e) {
        visit(classOrInterfaceTypeNode.Name(), e);
        classOrInterfaceTypeNode.UpdateEnv(e);
    } // visit(ClassOrInterfaceTypeNode)

    public void visit(AssignmentExprNode assignmentExprNode, Environment e)
            throws EnvironmentBuildingException{
        if(assignmentExprNode.ConditionalExpression() != null) {
            visit(assignmentExprNode.ConditionalExpression(), e);
        } else if(assignmentExprNode.Assignment() != null) {
            visit(assignmentExprNode.Assignment(), e);
        } // else if
        assignmentExprNode.UpdateEnv(e);
    } // visit(AssignmentExprNode)

    public void visit(MethodDeclNode methodDeclNode, Environment e) {
        for(FormalParameterNode param: methodDeclNode.FormalParameterList()){
            visit(param, e);
        } // for
        methodDeclNode.UpdateEnv(e);
    } // visit(MethodDeclNode)

    public void visit(MethodHeaderNode node, Environment e) {
        // add method name to class environment
        EnvironmentPair methodPair = new EnvironmentPair(node.MethodDeclarator().Identifier(), node);
        e.addPair(methodPair);

        // create an environment for the method, with the parameters in it
        Environment methodEnv = new Environment(methodPair);
        for (FormalParameterNode n : node.MethodDeclarator().FormalParameterList()) {
            EnvironmentPair paramPair = new EnvironmentPair(n.VariableDeclaratorId(), n);
            methodEnv.addPair(paramPair);
        } // for
        e.addChild(methodEnv);
        methodEnv.setParent(e);

        if(node.ReturnType() != null){
            visit(node.ReturnType(), methodEnv);
        } // if
        visit(node.MethodDeclarator(), methodEnv);

        node.UpdateEnv(methodEnv);
    } // visit(MethodHeaderNode)

    public void visit(MethodBodyNode methodBodyNode, Environment e)
            throws EnvironmentBuildingException{
        if(methodBodyNode.Block() != null) {
            visit(methodBodyNode.Block(), e);
        } // if
        methodBodyNode.UpdateEnv(e);
    } // visit(MethodBodyNode)

    public void visit(FormalParameterNode formalParameterNode, Environment e) {
        if (formalParameterNode.Type() != null){
            visit(formalParameterNode.Type(), e);
            formalParameterNode.setType(formalParameterNode.Type());
        } // if
        formalParameterNode.UpdateEnv(e);
    } // visit(FormalParameterNode)

    public void visit(ConstructorDeclNode node, Environment e, ConstructorDeclarationNode parent) {
        // add method name to class environment
        EnvironmentPair methodPair = new EnvironmentPair(node.Identifier(), parent);
        e.addPair(methodPair);

        // create an environment for the method, with the parameters in it
        Environment methodEnv = new Environment(methodPair);
        for (FormalParameterNode n : node.FormalParameterList()) {
            EnvironmentPair paramPair = new EnvironmentPair(n.VariableDeclaratorId(), n);
            methodEnv.addPair(paramPair);
        } // for
        e.addChild(methodEnv);
        methodEnv.setParent(e);

        for(FormalParameterNode param: node.FormalParameterList()){
            visit(param, methodEnv);
        } // for
        node.UpdateEnv(methodEnv);
    } // visit(ConstructorDeclNode)

    public void visit(BlockStatementNode blockStatementNode, Environment e)
            throws EnvironmentBuildingException{
        if (blockStatementNode.Statement() != null){
            visit(blockStatementNode.Statement(), e);
        } // if
        if (blockStatementNode.LocalVariableDeclaration() != null){
            visit(blockStatementNode.LocalVariableDeclaration(), e);
        } // if
        blockStatementNode.UpdateEnv(e);
    } // visit(BlockStatementNode)

    public void visit(InterfaceMemberDeclNode interfaceMemberDeclNode, Environment e) {
        if (interfaceMemberDeclNode.AbstractMethodDeclaration() != null){
            visit(interfaceMemberDeclNode.AbstractMethodDeclaration(), e);
        } // if
        interfaceMemberDeclNode.UpdateEnv(e);
    } // visit(InterfaceMemberDeclNode)

    public void visit(AbstractMethodDeclNode abstractMethodDeclNode, Environment e) {
        if (abstractMethodDeclNode.MethodHeader() != null){
            visit(abstractMethodDeclNode.MethodHeader(), e);
        } // if
        abstractMethodDeclNode.UpdateEnv(e);
    } // visit(AbstractMethodDeclNode)

    public void visit(BlockNode node, Environment e)
            throws EnvironmentBuildingException {
        // create a new environment, link to e
        EnvironmentPair newBlockPair = new EnvironmentPair("##BLOCK##", null);
        Environment newEnv = new Environment(newBlockPair);
        newEnv.setParent(e);
        e.addChild(newEnv);

        // current environment
        Environment currEnv = newEnv;
        // recurse on children
        int numBlockStmts = node.BlockStatements().size();
        for(int index = 0; index < numBlockStmts; ++index){
            BlockStatementNode n = node.BlockStatements().get(index);
            if(n.LocalVariableDeclaration() != null) {
                EnvironmentPair newLocalPair = new EnvironmentPair("##LOCALVAR##", null);
                Environment localEnv = new Environment(newLocalPair);
                localEnv.setParent(currEnv);
                currEnv.addChild(localEnv);
                currEnv = localEnv;
            } // if
            visit(n, currEnv);
        } // for

        node.UpdateEnv(currEnv);
    } // visit(BlockNode()

    public void visit(LocalVariableDeclNode node, Environment e)
            throws EnvironmentBuildingException {
        String varID = node.VariableDeclaratorId();

        // creates pair for the variable declaration
        EnvironmentPair varDeclPair = new EnvironmentPair(varID, node);

        // checks that  varID is not the name of any other localvariable in this method

        // current environment searched
        Environment currEnv = e;
        // searches parents of e that are ##BLOCK##/##FORLOOP##/##LOCALVAR## environments
        while(currEnv.getScopePair().getName().equals("##BLOCK##") || currEnv.getScopePair().getName().equals("##FORLOOP##")
                || currEnv.getScopePair().getName().equals("##LOCALVAR##")) {
            for (EnvironmentPair p : currEnv.getPairs()) {
                String type = p.getDeclaration().get_parseTreeNode().getLexeme();
                if (varID.equals(p.getName())) {
                    System.err.println("Two local variables with overlapping scope have the same name (" + p.getName() + ")");
                    throw new EnvironmentBuildingException();
                } // if
            } // for
            currEnv = currEnv.getParent();
        } // while
        // looks at formal paramters of method
        for (EnvironmentPair p : currEnv.getPairs()) {
            String type = p.getDeclaration().get_parseTreeNode().getLexeme();
            if (varID.equals(p.getName())) {
                System.err.println("Two local variables (one a parameter) with overlapping scope have the same name (" + p.getName() + ")");
                throw new EnvironmentBuildingException();
            } // if
        } // for

        // adds pair to the class environment
        e.addPair(varDeclPair);

        if(node.Type() != null){
            visit(node.Type(), e);
            node.setType(node.Type());
        } // if
        if(node.VariableInitializer() != null){
            visit(node.VariableInitializer(), e);
        } // if
        node.UpdateEnv(e);
    } // visit(LocalVariableDeclNode)

    public void visit(StatementNode statementNode, Environment e) throws EnvironmentBuildingException {
        if(statementNode.NoTrailingStmt() != null){
            visit(statementNode.NoTrailingStmt(), e);
        } else if(statementNode.IfStmt() != null){
            visit(statementNode.IfStmt(), e);
        } else if(statementNode.WhileStmt() != null){
            visit(statementNode.WhileStmt(), e);
        } else if(statementNode.ForStmt() != null){
            visit(statementNode.ForStmt(), e);
        } // else if
        statementNode.UpdateEnv(e);
    } // visit(StatementNode)

    public void visit(AssignmentNode assignmentNode, Environment e)
            throws EnvironmentBuildingException {
        if(assignmentNode.Name() != null){
            visit(assignmentNode.Name(), e);
        } // if
        if(assignmentNode.FieldAccess() != null){
            visit(assignmentNode.FieldAccess(), e);
        } // if
        if(assignmentNode.ArrayAccess() != null){
            visit(assignmentNode.ArrayAccess(), e);
        } // if
        visit(assignmentNode.AssignmentExpression(), e);
        assignmentNode.UpdateEnv(e);
    } // visit(AssignmentNode)

    public void visit(MethodInvocationNode methodInvocationNode, Environment e)
            throws EnvironmentBuildingException {
        if(methodInvocationNode.Name() != null){
            visit(methodInvocationNode.Name(), e);
        } // if
        for(AssignmentExprNode node: methodInvocationNode.ArgumentList()){
            visit(node, e);
        } // for
        if(methodInvocationNode.Primary() != null){
            visit(methodInvocationNode.Primary(), e);
        } // if
        methodInvocationNode.UpdateEnv(e);
    } // visit(MethodInvocationNode)

    public void visit(ClassInstanceCreationExprNode classInstanceCreationExprNode, Environment e)
            throws EnvironmentBuildingException {
        if(classInstanceCreationExprNode.ClassType() != null){
            visit(classInstanceCreationExprNode.ClassType(), e);
        } // if 
        for(AssignmentExprNode n: classInstanceCreationExprNode.ArgumentList()){
            visit(n, e);
        } // for
        classInstanceCreationExprNode.UpdateEnv(e);
    } // visit(ClassInstanceCreationExprNode)

    public void visit(PrimaryNode primaryNode, Environment e)
            throws EnvironmentBuildingException {
        if(primaryNode.PrimaryNoNewArray() != null){
            visit(primaryNode.PrimaryNoNewArray(), e);
        } else if(primaryNode.ArrayCreationExpression() != null){
            visit(primaryNode.ArrayCreationExpression(), e);
        } // else if
        primaryNode.UpdateEnv(e);
    } // visit(PrimaryNode)

    public void visit(PrimaryNoNewArrayNode primaryNoNewArrayNode, Environment e)
            throws EnvironmentBuildingException{
        if(primaryNoNewArrayNode.Expression() != null){
            visit(primaryNoNewArrayNode.Expression(), e);
        } // if
        if(primaryNoNewArrayNode.ClassInstanceCreationExpression() != null){
            visit(primaryNoNewArrayNode.ClassInstanceCreationExpression(), e);
        } // if
        if(primaryNoNewArrayNode.FieldAccess() != null){
            visit(primaryNoNewArrayNode.FieldAccess(), e);
        } // if
        if(primaryNoNewArrayNode.MethodInvocation() != null){
            visit(primaryNoNewArrayNode.MethodInvocation(), e);
        } // if
        if(primaryNoNewArrayNode.ArrayAccess() != null){
            visit(primaryNoNewArrayNode.ArrayAccess(), e);
        } // if
        if(primaryNoNewArrayNode.Literal() != null){
            visit(primaryNoNewArrayNode.Literal(), e);
        } // if
        primaryNoNewArrayNode.UpdateEnv(e);
    } // visit(PrimaryNoNewArrayNode)

    public void visit(LiteralNode node, Environment e)
            throws EnvironmentBuildingException{
        node.UpdateEnv(e);
    } // visit(LiteralNode)

    public void visit(ArrayCreationExprNode arrayCreationExprNode, Environment e)
            throws EnvironmentBuildingException{
        if (arrayCreationExprNode.Basictype() != null){
            visit(arrayCreationExprNode.Basictype(), e);
        } // if
        if(arrayCreationExprNode.DimExpr() != null){
            visit(arrayCreationExprNode.DimExpr(), e);
        } // if
        if(arrayCreationExprNode.ClassOrInterfaceType() != null){
            visit(arrayCreationExprNode.ClassOrInterfaceType(), e);
        } // if
        arrayCreationExprNode.UpdateEnv(e);
    } // visit(ArrayCreationExprNode)

    public void visit(FieldAcccessNode fieldAcccessNode, Environment e)
            throws EnvironmentBuildingException {
        visit(fieldAcccessNode.Primary(), e);
        fieldAcccessNode.UpdateEnv(e);
    } // visit(FieldAcccessNode)

    public void visit(ArrayAccessNode arrayAccessNode, Environment e)
            throws EnvironmentBuildingException {
        if(arrayAccessNode.Name() != null){
            visit(arrayAccessNode.Name(), e);
        } // if
        visit(arrayAccessNode.Expression(), e);
        if(arrayAccessNode.PrimaryNoNewArray() != null){
            visit(arrayAccessNode.PrimaryNoNewArray(), e);
        } // if
        arrayAccessNode.UpdateEnv(e);
    } // visit(ArrayAccessNode)

    public void visit(PostFixExprNode postFixExprNode, Environment e)
            throws EnvironmentBuildingException {
        if(postFixExprNode.Primary() != null){
            visit(postFixExprNode.Primary(), e);
        } else if(postFixExprNode.Name() != null){
            visit(postFixExprNode.Name(), e);
        } // else if
        postFixExprNode.UpdateEnv(e);
    } // visit(PostFixExprNode)

    public void visit(UnaryExprNode unaryExprNode, Environment e)
            throws EnvironmentBuildingException {
        if(unaryExprNode.UnaryExpression() != null){
            visit(unaryExprNode.UnaryExpression(), e);
        } // if
        if(unaryExprNode.PostfixExpression() != null){
            visit(unaryExprNode.PostfixExpression(), e);
        } // if
        if(unaryExprNode.CastExpression() != null){
            visit(unaryExprNode.CastExpression(), e);
        } // if
        unaryExprNode.UpdateEnv(e);
    } // visit(UnaryExprNode)

    public void visit(CastExprNode castExprNode, Environment e)
            throws EnvironmentBuildingException {
        if(castExprNode.UnaryExpression() != null){
            visit(castExprNode.UnaryExpression(), e);
        } // if
        if(castExprNode.Name() != null){
            visit(castExprNode.Name(), e);
        } // if
        castExprNode.UpdateEnv(e);
    } // visit(CastExprNode)

    public void visit(BinOpExprNode binOpExprNode, Environment e)
            throws EnvironmentBuildingException {
        if(binOpExprNode.UnaryExpr() != null) {
            visit(binOpExprNode.UnaryExpr(), e);
        } else {
            visit(binOpExprNode.LeftExpr(), e);
            if (binOpExprNode.ArrayType() != null) {
                visit(binOpExprNode.ArrayType(), e);
            } else if (binOpExprNode.ClassOrInterfaceType() != null) {
                visit(binOpExprNode.ClassOrInterfaceType(), e);
            } else if (binOpExprNode.RightExpr() != null) {
                visit(binOpExprNode.RightExpr(), e);
            } else throw new Error();
        }  // else
        binOpExprNode.UpdateEnv(e);
    } // visit(BinOpExprNode)

    public void visit(StatementExprNode statementExprNode, Environment e)
            throws EnvironmentBuildingException {
        if(statementExprNode.Assignment() != null){
            visit(statementExprNode.Assignment(), e);
        } else if(statementExprNode.MethodInvocation() != null){
            visit(statementExprNode.MethodInvocation(), e);
        } else if(statementExprNode.ClassInstanceCreationExpression() != null){
            visit(statementExprNode.ClassInstanceCreationExpression(), e);
        } // else if
        statementExprNode.UpdateEnv(e);
    } // visit(StatementExprNode)

    public void visit(NoTrailingStmtNode noTrailingStmtNode, Environment e)
            throws EnvironmentBuildingException{
        if(noTrailingStmtNode.Block() != null){
            visit(noTrailingStmtNode.Block(), e);
        } else if(noTrailingStmtNode.StatementExpr() != null){
            visit(noTrailingStmtNode.StatementExpr(), e);
        } else if(noTrailingStmtNode.ReturnStmt() != null){
            visit(noTrailingStmtNode.ReturnStmt(), e);
        } // else if
        noTrailingStmtNode.UpdateEnv(e);
    } // visit(NoTrailingStmtNode)

    public void visit(IfStmtNode ifStmtNode, Environment e) throws EnvironmentBuildingException{
        if(ifStmtNode.Expression() != null){
            visit(ifStmtNode.Expression(), e);
        } // if
        for(StatementNode stmt: ifStmtNode.Statements()){
            visit(stmt, e);
        } // for
        ifStmtNode.UpdateEnv(e);
    } // visit(IfStmtNode)

    public void visit(ReturnStmtNode returnStmtNode, Environment e)
            throws EnvironmentBuildingException{
        if(returnStmtNode.Expression() != null){
            visit(returnStmtNode.Expression(), e);
        } // if
        returnStmtNode.UpdateEnv(e);
    } // visit(ReturnStmtNode)

    public void visit(WhileStmtNode whileStmtNode, Environment e)
            throws EnvironmentBuildingException{
        if(whileStmtNode.Expression() != null){
            visit(whileStmtNode.Expression(), e);
        } // if
        if (whileStmtNode.Statement() != null){
            visit(whileStmtNode.Statement(), e);
        } // if
        whileStmtNode.UpdateEnv(e);
    } // visit(WhileStmtNode)

    public void visit(ForStmtNode forStmtNode, Environment e) throws EnvironmentBuildingException{
        EnvironmentPair newForPair = new EnvironmentPair("##FORLOOP##", null);
        Environment newEnv = new Environment(newForPair);
        newEnv.setParent(e);
        e.addChild(newEnv);

        if (forStmtNode.LocalVariableDeclaration() != null){
            visit(forStmtNode.LocalVariableDeclaration(), newEnv);
        } // if
        if(forStmtNode.StatementExpression() != null){
            visit(forStmtNode.StatementExpression(), newEnv);
        } // if
        if(forStmtNode.Expression() != null){
            visit(forStmtNode.Expression(), newEnv);
        } // if
        if(forStmtNode.Forupdate() != null){
            visit(forStmtNode.Forupdate(), newEnv);
        } // if

        visit(forStmtNode.Statement(), newEnv);
        forStmtNode.UpdateEnv(newEnv);
    } // visit(ForStmtNode)

    public void visit(NameNode nameNode, Environment e) {
        nameNode.UpdateEnv(e);
    } // visit(NameNode)

    public void visit(InterfaceDeclNode node, Environment e) {
        // creates pair for the interface, adds it to the global environment
        EnvironmentPair interfacePair = new EnvironmentPair(node.Identifier(), node);
        e.addPair(interfacePair);

        // creates an environment for the interface, links it to e
        Environment interfaceEnv = new Environment(interfacePair);
        e.addChild(interfaceEnv);
        interfaceEnv.setParent(e);

        // recurses on children
        for(NameNode n: node.ExtendsInterfaces()) {
            visit(n, interfaceEnv);
        } // for
        visit(node.InterfaceBody(), interfaceEnv);

        node.UpdateEnv(interfaceEnv);
    } // visit(InterfaceDeclNode)

    public void visit(InterfaceBodyNode interfaceBodyNode, Environment e) {
        for(InterfaceMemberDeclNode child: interfaceBodyNode.InterfaceMemberDeclarations()){
            visit(child, e);
        } // for
        interfaceBodyNode.UpdateEnv(e);
    } // visit(InterfaceBodyNode)

    public void visit(ClassBodyNode classBodyNode, Environment e) throws EnvironmentBuildingException {
        for(ClassBodyDeclNode child: classBodyNode.ClassBodyDeclarations()){
            visit(child, e);
        } // for
        classBodyNode.UpdateEnv(e);
    } // visit(ClassBodyNode)

    public void visit(BasicTypeNode basicTypeNode, Environment e){
        basicTypeNode.UpdateEnv(e);
    } // visit(BasicTypeNode)

    public void visit(TypeNode typeNode, Environment e){
        if(typeNode.ArrayType() != null){
            visit(typeNode.ArrayType(), e);
        } else if(typeNode.BasicType() != null){
            visit(typeNode.BasicType(), e);
        } else if(typeNode.ClassOrInterfaceType() != null){
            visit(typeNode.ClassOrInterfaceType(), e);
        } // else if
        typeNode.UpdateEnv(e);
    } // visit(TypeNode)
} // class EnvironmentBuilder