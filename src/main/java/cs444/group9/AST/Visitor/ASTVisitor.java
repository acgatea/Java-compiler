/*******************************************************************************
 * ASTVisitor.java
 * 
 * A module implementing generic AST Visitor.
 * ****************************************************************************/

package cs444.group9.AST.Visitor;

import cs444.group9.AST.ASTTree;
import cs444.group9.AST.Node.*;
import cs444.group9.AST.Node.Array.ArrayAccessNode;
import cs444.group9.AST.Node.Array.FieldAcccessNode;
import cs444.group9.AST.Node.Array.PrimaryNoNewArrayNode;
import cs444.group9.AST.Node.Array.PrimaryNode;
import cs444.group9.AST.Node.Body.*;
import cs444.group9.AST.Node.Declaration.*;
import cs444.group9.AST.Node.Environments.Environment;
import cs444.group9.AST.Node.Expression.*;
import cs444.group9.AST.Node.Statement.*;
import cs444.group9.AST.Node.StatementExpression.*;
import cs444.group9.AST.Node.Type.*;
import cs444.group9.Parser.Node;
import cs444.group9.Scanner.State;
import cs444.group9.Scanner.Token;
import java.util.*;

public class ASTVisitor implements iVisitor {
    // name of current package
    String currPackage;

    /*******************************************************************************
     * OVER-RIDDEN VISIT METHODS
     * *****************************************************************************/ 
    @Override
    public void visit(CompilationUnitNode node) throws ASTException{
        String className = "";
        for(Node child: node.get_parseTreeNode().getChildren()){
            if (child.getSymbol().equals("ImportDeclarations")){
                for(Node decl: child.getChildren()){
                    node.ImportDeclarations().add(new ImportDeclNode(decl));
                } // for
            } else if (child.getSymbol().equals("TypeDeclaration")){
                node.TypeDeclaration(new TypeDeclNode(child));
            } else if (child.getSymbol().equals("PackageDeclaration")){
                node.PackageDeclaration(new PackageDeclNode(child));
            } // else if
        } // for
        if(node.PackageDeclaration() != null){
            visit(node.PackageDeclaration());
            currPackage = node.PackageDeclaration().Name().toString();
        } else currPackage = "_DEFAULTPACKAGE_";

        if(node.TypeDeclaration() != null){
            visit(node.TypeDeclaration());
            if(node.TypeDeclaration().ClassDeclaration() != null){
                className = node.TypeDeclaration().ClassDeclaration().Identifier();
            } else {
                className = node.TypeDeclaration().InterfaceDeclaration().Identifier();
            } // else
        } // if
        for(ImportDeclNode child: node.ImportDeclarations()){
            visit(child);
            child.ClassName(className);
        } // for
        if(node.PackageDeclaration() != null){
            node.PackageDeclaration().ClassName(className);
        } // if

        // create environment
        EnvironmentBuilder envBuilder = new EnvironmentBuilder();
        envBuilder.visit(node, node.getEnv());
    } // visit(CompilationUnitNode)

    @Override
    public void visit(ClassDeclNode node) {
        for(Node child: node.get_parseTreeNode().getChildren()){
            if (child.getSymbol().equals("Modifiers")){
                for(Node modifier: child.getChildren()){
                    node.addModifier(new ModifierNode(modifier));
                } // for
            } else if (child.getSymbol().equals("Identifier")){
                node.Identifier(child.getChildren().get(0).getLexeme());
            } else if (child.getSymbol().equals("Interfaces")){
                Node interfaceList=child.getChildren().get(1);
                for (Node element: interfaceList.getChildren()){
                    if (element.getSymbol().equals("InterfaceType")){
                        node.addInterface(new NameNode(element.getChildren().get(0).getChildren().get(0)));
                    } // if
                } // for
            } else if (child.getSymbol().equals("ClassBody")){
                node.ClassBody(new ClassBodyNode(child));
            } else if (child.getSymbol().equals("ClassType")){
                node.ClassType(new NameNode(child.getChildren().get(0).getChildren().get(0)));
            } // else if
        } // for
        // visit the nodes
        for(ModifierNode element: node.Modifiers()){
            visit(element);
        } // for
        for(NameNode element: node.Interfaces()){
            visit(element);
        } // for
        if(node.ClassType() != null){
            visit(node.ClassType());
        } else if (!(node.Identifier().equals("Object")
                && currPackage.equals("java.lang"))){
            // if there is no class in extends and class is not Object, it extends Object
            Token t = new Token("Object", 0, 0);
            t.getKind(State.ST_ID, "Object");
            Node newIDNode = new Node(t);
            NameNode newNameNode = new NameNode(newIDNode);
            newNameNode.addName("java");
            newNameNode.addName("lang");
            newNameNode.addName("Object");
            node.ClassType(newNameNode);
        } // else if
        if (node.ClassBody() != null){
            visit(node.ClassBody());
        } // if
    } // visit(CompilationUnitNode)

    @Override
    public void visit(TypeDeclNode node) {
        Node child=node.get_parseTreeNode().getChildren().get(0);
        if(child.getSymbol().equals("ClassDeclaration")){
            node.ClassDeclaration(new ClassDeclNode(child));
        } else if (child.getSymbol().equals("InterfaceDeclaration")){
            node.InterfaceDeclaration(new InterfaceDeclNode(child));
        } // else if
        if(node.ClassDeclaration() != null) {
            visit(node.ClassDeclaration());
        } else if(node.InterfaceDeclaration() != null) {
            visit(node.InterfaceDeclaration());
        } // else if
    } // visit(TypeDeclNode)

    @Override
    public void visit(ClassBodyDeclNode node) {
        Node child=node.get_parseTreeNode().getChildren().get(0);
        if(child.getSymbol().equals("ClassMemberDeclaration")){
            node.ClassMemberDeclaration(new ClassMemberDeclNode(child));
        } else if (child.getSymbol().equals("ConstructorDeclaration")){
            node.ConstructorDeclaration(new ConstructorDeclarationNode(child));
        } // else if
        if (node.ClassMemberDeclaration() != null){
            visit(node.ClassMemberDeclaration());
        } else if (node.ConstructorDeclaration() != null){
            visit(node.ConstructorDeclaration());
        } // else if
    } // visit(ClassBodyDeclNode)

    @Override
    public void visit(ModifierNode node) {
        node.Modifier(node.get_parseTreeNode().getChildren().get(0).getSymbol());
    } // visit(ModifierNode)

    @Override
    public void visit(ConstructorDeclarationNode node){
        for(Node child: node.get_parseTreeNode().getChildren()){
            if(child.getSymbol().equals("Modifiers")){
                for(Node modifier: child.getChildren()){
                    node.addModifier(new ModifierNode(modifier));
                } // for
            } else if (child.getSymbol().equals("ConstructorDeclarator")){
                node.ConstructorDeclarator(new ConstructorDeclNode(child));
            } else if (child.getSymbol().equals("ConstructorBody")){
                node.ConstructorBody(new ConstructorBodyNode(child));
            } // else if
        } // for
        for(ModifierNode element: node.Modifiers()){
            visit(element);
        } // for
        visit(node.ConstructorDeclarator());
        visit(node.ConstructorBody());
    } // visit(ConstructorDeclarationNode)

    @Override
    public void visit(ConstructorBodyNode constructorBodyNode) {
        for(Node child: constructorBodyNode.get_parseTreeNode().getChildren()){
            if(child.getSymbol().equals("BlockStatements")){
                for(Node stmt: child.getChildren()){
                    constructorBodyNode.addBlockStatement(new BlockStatementNode(stmt));
                } // for
            } // if
        } // for
        for(BlockStatementNode stmt: constructorBodyNode.BlockStatements()){
            visit(stmt);
        } // for
    } // visit(ConstructorBodyNode)

    @Override
    public void visit(ClassMemberDeclNode node) {
        Node childNode=node.get_parseTreeNode().getChildren().get(0);
        if(childNode.getSymbol().equals("FieldDeclaration")){
            node.FieldDeclaration(new FieldDeclNode(childNode));
        } else if (childNode.getSymbol().equals("MethodDeclaration")){
            node.MethodDeclaration(new MethodDeclarationNode(childNode));
        } // else if
        if(node.FieldDeclaration() != null){
            visit(node.FieldDeclaration());
        } else if(node.MethodDeclaration() != null){
            visit(node.MethodDeclaration());
        } // else if
    } // visit(ClassMemberDeclNode)

    @Override
    public void visit(MethodDeclarationNode node) {
        for(Node child: node.get_parseTreeNode().getChildren()){
            if (child.getSymbol().equals("MethodHeader")){
                node.MethodHeader(new MethodHeaderNode(child));
            } else if (child.getSymbol().equals("MethodBody")){
                node.MethodBody(new MethodBodyNode(child));
            } // else if
        } // for
        visit(node.MethodHeader());
        visit(node.MethodBody());
    } // visit(MethodDeclarationNode)

    @Override
    public void visit(FieldDeclNode node) {
        for(Node child: node.get_parseTreeNode().getChildren()){
            if (child.getSymbol().equals("Modifiers")){
                for(Node modifier: child.getChildren()){
                    node.addModifier(new ModifierNode(modifier));
                } // for
            } else if (child.getSymbol().equals("Type")){
                node.Type(new TypeNode(child));
            } else if (child.getSymbol().equals("VariableDeclarator")){
                node.VariableDeclarator(new VariableDeclNode(child));
            } // else if
        } // for
        for(ModifierNode modifier: node.Modifiers()){
            visit(modifier);
        } // for
        if (node.Type() != null){
            visit(node.Type());
        } // if
        visit(node.VariableDeclarator());
    } //  visit(FieldDeclNode)

    public void visit(ArrayTypeNode arrayTypeNode) {
        Node child=arrayTypeNode.get_parseTreeNode().getChildren().get(0);
        if(child.getSymbol().equals("BasicType")){
            arrayTypeNode.BasicType(new BasicTypeNode(child));
        } else if (child.getSymbol().equals("Name")){
            arrayTypeNode.Name(new NameNode(child));
        } // else if
        if(arrayTypeNode.BasicType() != null){
            visit(arrayTypeNode.BasicType());
        } else if(arrayTypeNode.Name() != null){
            visit(arrayTypeNode.Name());
        } // else if
    } // visit(ArrayTypeNode)

    public void visit(VariableDeclNode variableDeclNode) {
        for(Node child: variableDeclNode.get_parseTreeNode().getChildren()){
            if (child.getSymbol().equals("VariableDeclaratorId")){
                Node Identifier=child.getChildren().get(0);
                String id=Identifier.getChildren().get(0).getLexeme();
                variableDeclNode.VariableDeclarator(id);
            } else if (child.getSymbol().equals("VariableInitializer")){
                variableDeclNode.VariableInitializer(new AssignmentExprNode(child.getChildren().get(0).getChildren().get(0)));
            } // else if
        } // for
        if (variableDeclNode.VariableInitializer() != null){
            visit(variableDeclNode.VariableInitializer());
        } // if
    } // visit(VariableDeclNode)

    @Override
    public void visit(ClassOrInterfaceTypeNode classOrInterfaceTypeNode) {
        Node child=classOrInterfaceTypeNode.get_parseTreeNode().getChildren().get(0);
        classOrInterfaceTypeNode.Name(new NameNode(child));
        visit(classOrInterfaceTypeNode.Name());
    } // visit(ClassOrInterfaceTypeNode)

    @Override
    public void visit(AssignmentExprNode assignmentExprNode) {
        Node child=assignmentExprNode.get_parseTreeNode().getChildren().get(0);
        if(child.getSymbol().equals("ConditionalOrExpression")){
            assignmentExprNode.ConditionalExpression(new BinOpExprNode(child));
        } else if (child.getSymbol().equals("Assignment")){
            assignmentExprNode.Assignment(new AssignmentNode(child));
        } // else if
        if(assignmentExprNode.ConditionalExpression() != null) {
            visit(assignmentExprNode.ConditionalExpression());
        } else if(assignmentExprNode.Assignment() != null) {
            visit(assignmentExprNode.Assignment());
        } // else if
    } // visit(AssignmentExprNode)

    @Override
    public void visit(MethodDeclNode methodDeclNode) {
        for(Node child: methodDeclNode.get_parseTreeNode().getChildren()){
            if (child.getSymbol().equals("Identifier")){
                methodDeclNode.Identifier(child.getChildren().get(0).getLexeme());
            } else if (child.getSymbol().equals("FormalParameterList")){
                for(Node param: child.getChildren()){
                    methodDeclNode.addFormalParameter(new FormalParameterNode(param));
                } // for
            } // else if
        } // for
        for(FormalParameterNode param: methodDeclNode.FormalParameterList()){
            visit(param);
        } // for
    } // visit(MethodDeclNode)

    @Override
    public void visit(MethodBodyNode methodBodyNode) {
        Node child=methodBodyNode.get_parseTreeNode().getChildren().get(0);
        if(child.getSymbol().equals("Block")){
            methodBodyNode.Block(new BlockNode(child));
        } // if
        if(methodBodyNode.Block() != null) {
            visit(methodBodyNode.Block());
        } // if
    } // visit(MethodBodyNode)

    @Override
    public void visit(MethodHeaderNode methodHeaderNode) {
        for(Node child: methodHeaderNode.get_parseTreeNode().getChildren()){
            if (child.getSymbol().equals("Modifiers")){
                for(Node modNode: child.getChildren()){
                    methodHeaderNode.addModifier(new ModifierNode(modNode));
                } // for
            } else if (child.getSymbol().equals("Type")){
                methodHeaderNode.ReturnType(new TypeNode(child));
            } else if (child.getSymbol().equals("MethodDeclarator")){
                methodHeaderNode.MethodDeclarator(new MethodDeclNode(child));
            } // else if 
        } // for
        for(ModifierNode node: methodHeaderNode.Modifiers()){
            visit(node);
        } // for
        if(methodHeaderNode.ReturnType() != null){
            visit(methodHeaderNode.ReturnType());
        } // if
        visit(methodHeaderNode.MethodDeclarator());
    } // visit(MethodHeaderNode)

    @Override
    public void visit(FormalParameterNode formalParameterNode) {
        for(Node child: formalParameterNode.get_parseTreeNode().getChildren()){
            if(child.getSymbol().equals("Type")){
                formalParameterNode.Type(new TypeNode(child));
            } else if (child.getSymbol().equals("VariableDeclaratorId")){
                formalParameterNode.VariableDeclaratorId(child.getChildren().get(0).getChildren().get(0).getLexeme());
            } // else if
        } // for
        if (formalParameterNode.Type() != null){
            visit(formalParameterNode.Type());
        } // if
    } // visit(FormalParameterNode)

    @Override
    public void visit(ConstructorDeclNode constructorDeclNode) {
        for(Node child: constructorDeclNode.get_parseTreeNode().getChildren()){
            if (child.getSymbol().equals("Identifier")){
                constructorDeclNode.Identifier(child.getChildren().get(0).getLexeme());
            } else if (child.getSymbol().equals("FormalParameterList")){
                for(Node param: child.getChildren()){
                    constructorDeclNode.addFormalParameterNode(new FormalParameterNode(param));
                } // for
            } // else if
        } // for
        for(FormalParameterNode param: constructorDeclNode.FormalParameterList()){
            visit(param);
        } // for 
    } // visit(ConstructorDeclNode)

    @Override
    public void visit(BlockStatementNode blockStatementNode) {
        Node child = blockStatementNode.get_parseTreeNode().getChildren().get(0);
        if(child.getSymbol().equals("Statement")){
            blockStatementNode.Statement(new StatementNode(child));
        } else if (child.getSymbol().equals("LocalVariableDeclarationStatement")){
            blockStatementNode.LocalVariableDeclaration(new LocalVariableDeclNode(child.getChildren().get(0)));
        } // else if

        if (blockStatementNode.Statement() != null){
            visit(blockStatementNode.Statement());
        } // if
        if (blockStatementNode.LocalVariableDeclaration() != null){
            visit(blockStatementNode.LocalVariableDeclaration());
        } // if
    } // visit(BlockStatementNode)

    @Override
    public void visit(InterfaceMemberDeclNode interfaceMemberDeclNode) {
        for(Node child: interfaceMemberDeclNode.get_parseTreeNode().getChildren()) {
            if(child.getSymbol().equals("AbstractMethodDeclaration")){
                interfaceMemberDeclNode.AbstractMethodDeclaration(new AbstractMethodDeclNode(child));
            } // if
        } // for
        if (interfaceMemberDeclNode.AbstractMethodDeclaration() != null){
            visit(interfaceMemberDeclNode.AbstractMethodDeclaration());
        } // if
    } // visit(InterfaceMemberDeclNode)

    @Override
    public void visit(AbstractMethodDeclNode abstractMethodDeclNode) {
        for(Node child: abstractMethodDeclNode.get_parseTreeNode().getChildren()) {
            if(child.getSymbol().equals("MethodHeader")){
                abstractMethodDeclNode.MethodHeader(new MethodHeaderNode(child));
            } // if
        } // for
        if (abstractMethodDeclNode.MethodHeader() != null){
            visit(abstractMethodDeclNode.MethodHeader());
        } // if
    } // visit(AbstractMethodDeclNode)

    @Override
    public void visit(BlockNode blockNode) {
        for(Node child: blockNode.get_parseTreeNode().getChildren()) {
            if(child.getSymbol().equals("BlockStatements")){
                for(Node stmt: child.getChildren()){
                    blockNode.addBlockStatement(new BlockStatementNode(stmt));
                } // for
            } // if
        } // for
        for(BlockStatementNode node: blockNode.BlockStatements()){
            visit(node);
        } // for
    } // visit(BlockNode)

    @Override
    public void visit(LocalVariableDeclNode localVariableDeclNode) {
        for(Node child: localVariableDeclNode.get_parseTreeNode().getChildren()) {
            if (child.getSymbol().equals("Type")){
                localVariableDeclNode.Type(new TypeNode(child));
            } else if (child.getSymbol().equals("VariableDeclaratorId")){
                localVariableDeclNode.VariableDeclaratorId(child.getChildren().get(0).getChildren().get(0).getLexeme());
            } else if (child.getSymbol().equals("VariableInitializer")){
                localVariableDeclNode.VariableInitializer(new AssignmentExprNode(child.getChildren().get(0).getChildren().get(0)));
            } // else if
        } // for
        if(localVariableDeclNode.Type() != null){
            visit(localVariableDeclNode.Type());
        } // if
        if(localVariableDeclNode.VariableInitializer() != null){
            visit(localVariableDeclNode.VariableInitializer());
        } // fi
    } // visit(LocalVariableDeclNode)

    @Override
    public void visit(StatementNode statementNode) {
        Node child = statementNode.get_parseTreeNode().getChildren().get(0);
        String nodeType=child.getSymbol();

        if(nodeType.equals("StatementWithoutTrailingSubstmt")){
            statementNode.NoTrailingStmt(new NoTrailingStmtNode(child));
        } else if (nodeType.equals("IfThenStatement") || nodeType.equals("IfThenElseStatement") || nodeType.equals("IfThenElseStatementNoShortIf")){
            statementNode.IfStmt(new IfStmtNode(child));
        } else if (nodeType.equals("WhileStatement") || nodeType.equals("WhileStatementNoShortIf")){
            statementNode.WhileStmt(new WhileStmtNode(child));
        } else if (nodeType.equals("ForStatement") || nodeType.equals("ForStatementNoShortIf")){
            statementNode.ForStmt(new ForStmtNode(child));
        } // else if

        if(statementNode.NoTrailingStmt() != null){
            visit(statementNode.NoTrailingStmt());
        } else if(statementNode.IfStmt() != null){
            visit(statementNode.IfStmt());
        } else if(statementNode.WhileStmt() != null){
            visit(statementNode.WhileStmt());
        } else if(statementNode.ForStmt() != null){
            visit(statementNode.ForStmt());
        } // else if
    } // visit(StatementNode)

    @Override
    public void visit(AssignmentNode assignmentNode) {
        for(Node child: assignmentNode.get_parseTreeNode().getChildren()){
            if(child.getSymbol().equals("LeftHandSide")){
                Node subchild=child.getChildren().get(0);
                if(subchild.getSymbol().equals("Name")){
                    assignmentNode.Name(new NameNode(subchild));
                } else if (subchild.getSymbol().equals("FieldAccess")){
                    assignmentNode.FieldAccess(new FieldAcccessNode(subchild));
                } else if (subchild.getSymbol().equals("ArrayAccess")){
                    assignmentNode.ArrayAccess(new ArrayAccessNode(subchild));
                } // else if 
            } else if (child.getSymbol().equals("AssignmentExpression")){
                assignmentNode.AssignmentExpression(new AssignmentExprNode(child));
            } // else if 
        } // for

        if(assignmentNode.Name() != null){
            visit(assignmentNode.Name());
        } // if
        if(assignmentNode.FieldAccess() != null){
            visit(assignmentNode.FieldAccess());
        } // if
        if(assignmentNode.ArrayAccess() != null){
            visit(assignmentNode.ArrayAccess());
        } // if
        visit(assignmentNode.AssignmentExpression());
    } // visit(AssignmentNode)

    @Override
    public void visit(MethodInvocationNode methodInvocationNode) {
        for(Node child: methodInvocationNode.get_parseTreeNode().getChildren()){
            if(child.getSymbol().equals("Name")){
                methodInvocationNode.Name(new NameNode(child));
            } else if (child.getSymbol().equals("ArgumentList")){
                for(Node arg: child.getChildren()){
                    methodInvocationNode.addArgument(new AssignmentExprNode(arg.getChildren().get(0)));
                } // for
            } else if (child.getSymbol().equals("Primary")){
                methodInvocationNode.Primary(new PrimaryNode(child));
            } else if (child.getSymbol().equals("Identifier")){
                methodInvocationNode.Identifier(child.getChildren().get(0).getLexeme());
            } // else if
        } // for
        if(methodInvocationNode.Name() != null){
            visit(methodInvocationNode.Name());
        } // if
        for(AssignmentExprNode node: methodInvocationNode.ArgumentList()){
            visit(node);
        } // for
        if(methodInvocationNode.Primary() != null){
            visit(methodInvocationNode.Primary());
        } // if
    } // visit(MethodInvocationNode)

    @Override
    public void visit(ClassInstanceCreationExprNode classInstanceCreationExprNode) {
        for(Node child: classInstanceCreationExprNode.get_parseTreeNode().getChildren()){
            if(child.getSymbol().equals("ClassType")){
                classInstanceCreationExprNode.ClassType(new ClassOrInterfaceTypeNode(child.getChildren().get(0)));
            } else if(child.getSymbol().equals("ArgumentList")){
                for(Node arg: child.getChildren()){
                    classInstanceCreationExprNode.addExpression(new AssignmentExprNode(arg.getChildren().get(0)));
                } // for
            } // else if
        } // for
        if(classInstanceCreationExprNode.ClassType() != null){
            visit(classInstanceCreationExprNode.ClassType());
        } // if
        for(AssignmentExprNode n: classInstanceCreationExprNode.ArgumentList()){
            visit(n);
        } // for
    } // visit(ClassInstanceCreationExprNode)

    @Override
    public void visit(PrimaryNode primaryNode) {
        Node child=primaryNode.get_parseTreeNode().getChildren().get(0);
        if (child.getSymbol().equals("PrimaryNoNewArray")){
            primaryNode.PrimaryNoNewArray(new PrimaryNoNewArrayNode(child));
        } else if (child.getSymbol().equals("ArrayCreationExpression")){
            primaryNode.ArrayCreationExpression(new ArrayCreationExprNode(child));
        } // else if
        if(primaryNode.PrimaryNoNewArray() != null){
            visit(primaryNode.PrimaryNoNewArray());
        } else if(primaryNode.ArrayCreationExpression() != null){
            visit(primaryNode.ArrayCreationExpression());
        } // else if
    } // visit(PrimaryNode)

    @Override
    public void visit(PrimaryNoNewArrayNode primaryNoNewArrayNode) {
        for(Node child: primaryNoNewArrayNode.get_parseTreeNode().getChildren()){
            if(child.getSymbol().equals("Literal")){
                primaryNoNewArrayNode.Literal(new LiteralNode(child));
            } else if (child.getSymbol().equals("Expression")){
                primaryNoNewArrayNode.Expression(new AssignmentExprNode(child.getChildren().get(0)));
            } else if (child.getSymbol().equals("ClassInstanceCreationExpression")){
                primaryNoNewArrayNode.ClassInstanceCreationExpression(new ClassInstanceCreationExprNode(child));
            } else if (child.getSymbol().equals("FieldAccess")){
                primaryNoNewArrayNode.FieldAccess(new FieldAcccessNode(child));
            } else if (child.getSymbol().equals("MethodInvocation")){
                primaryNoNewArrayNode.MethodInvocation(new MethodInvocationNode(child));
            } else if (child.getSymbol().equals("ArrayAccess")){
                primaryNoNewArrayNode.ArrayAccess(new ArrayAccessNode(child));
            } else if (child.getSymbol().equals("THIS")){
                primaryNoNewArrayNode.This(true);
            } // else if
        } // for
        if(primaryNoNewArrayNode.Literal()!= null){
            visit(primaryNoNewArrayNode.Literal());
        } // if
        if(primaryNoNewArrayNode.Expression() != null){
            visit(primaryNoNewArrayNode.Expression());
        } // if
        if(primaryNoNewArrayNode.ClassInstanceCreationExpression() != null){
            visit(primaryNoNewArrayNode.ClassInstanceCreationExpression());
        } // if
        if(primaryNoNewArrayNode.FieldAccess() != null){
            visit(primaryNoNewArrayNode.FieldAccess());
        } // if
        if(primaryNoNewArrayNode.MethodInvocation() != null){
            visit(primaryNoNewArrayNode.MethodInvocation());
        } // if
        if(primaryNoNewArrayNode.ArrayAccess() != null){
            visit(primaryNoNewArrayNode.ArrayAccess());
        } // if
    } // visit(PrimaryNoNewArrayNode)

    @Override
    public void visit(ArrayCreationExprNode arrayCreationExprNode) {
        for(Node child: arrayCreationExprNode.get_parseTreeNode().getChildren()){
            if(child.getSymbol().equals("BasicType")){
                arrayCreationExprNode.Basictype(new BasicTypeNode(child));
            } else if (child.getSymbol().equals("DimExpr")){
                arrayCreationExprNode.DimExpr(new AssignmentExprNode(child.getChildren().get(1).getChildren().get(0)));
            } else if (child.getSymbol().equals("ClassOrInterfaceType")){
                arrayCreationExprNode.ClassOrInterfaceType(new ClassOrInterfaceTypeNode(child));
            } // else if
        } // for
        if (arrayCreationExprNode.Basictype() != null){
            visit(arrayCreationExprNode.Basictype());
        } // if
        if(arrayCreationExprNode.DimExpr() != null){
            visit(arrayCreationExprNode.DimExpr());
        } // if
        if(arrayCreationExprNode.ClassOrInterfaceType() != null){
            visit(arrayCreationExprNode.ClassOrInterfaceType());
        } // if
    } // visit(ArrayCreationExprNode)

    @Override
    public void visit(FieldAcccessNode fieldAcccessNode) {
        for(Node child: fieldAcccessNode.get_parseTreeNode().getChildren()){
            if(child.getSymbol().equals("Primary")){
                fieldAcccessNode.Primary(new PrimaryNode(child));
            } else if (child.getSymbol().equals("Identifier")){
                fieldAcccessNode.Identifier(child.getChildren().get(0).getLexeme());
            } // else if
        } // for
        visit(fieldAcccessNode.Primary());
    } // visit(FieldAcccessNode)

    @Override
    public void visit(ArrayAccessNode arrayAccessNode) {
        for(Node child: arrayAccessNode.get_parseTreeNode().getChildren()){
            if(child.getSymbol().equals("Name")){
                arrayAccessNode.Name(new NameNode(child));
            } else if (child.getSymbol().equals("Expression")){
                arrayAccessNode.Expression(new AssignmentExprNode(child.getChildren().get(0)));
            } else if (child.getSymbol().equals("PrimaryNoNewArray")){
                arrayAccessNode.PrimaryNoNewArray(new PrimaryNoNewArrayNode(child));
            } // else if
        } // for
        if(arrayAccessNode.Name() != null){
            visit(arrayAccessNode.Name());
        } // if
        visit(arrayAccessNode.Expression());
        if(arrayAccessNode.PrimaryNoNewArray() != null){
            visit(arrayAccessNode.PrimaryNoNewArray());
        } // if
    } // visit(ArrayAccessNode)

    @Override
    public void visit(PostFixExprNode postFixExprNode) {
        Node child=postFixExprNode.get_parseTreeNode().getChildren().get(0);
        if(child.getSymbol().equals("Primary")){
            postFixExprNode.Primary(new PrimaryNode(child));
        } else if (child.getSymbol().equals("Name")){
            postFixExprNode.Name(new NameNode(child));
        } // else if
        if(postFixExprNode.Primary() != null){
            visit(postFixExprNode.Primary());
        } else if(postFixExprNode.Name() != null){
            visit(postFixExprNode.Name());
        } // else if
    } // visit(PostFixExprNode)

    @Override
    public void visit(UnaryExprNode unaryExprNode) {
        if(unaryExprNode.get_parseTreeNode().getChildren().size() == 2){
            // UnaryExpression -> MINUS UnaryExpression
            unaryExprNode.UnaryOperator("MINUS");
            Node child = unaryExprNode.get_parseTreeNode().getChildren().get(1);
            unaryExprNode.UnaryExpression(new UnaryExprNode(child));
        } else {
            // UnaryExpression -> UnaryExpressionNotMinus
            Node child = unaryExprNode.get_parseTreeNode().getChildren().get(0);
            Node subchild = child.getChildren().get(0);
            if(subchild.getSymbol().equals("EXCLAM")){
                unaryExprNode.UnaryOperator("EXCLAM");
                unaryExprNode.UnaryExpression(new UnaryExprNode(child.getChildren().get(1)));
            } else if(subchild.getSymbol().equals("PostfixExpression")){
                unaryExprNode.PostfixExpression(new PostFixExprNode(subchild));
            } else if(subchild.getSymbol().equals("CastExpression")){
                unaryExprNode.CastExpression(new CastExprNode(subchild));
            } // else if
        } // else

        if(unaryExprNode.UnaryExpression() != null){
            visit(unaryExprNode.UnaryExpression());
        } // if
        if(unaryExprNode.PostfixExpression() != null){
            visit(unaryExprNode.PostfixExpression());
        } // if
        if(unaryExprNode.CastExpression() != null){
            visit(unaryExprNode.CastExpression());
        } // if
    } // visit(UnaryExprNode)

    @Override
    public void visit(CastExprNode castExprNode) {
        for(Node child: castExprNode.get_parseTreeNode().getChildren()){
            if (child.getSymbol().equals("BasicType")){
                castExprNode.BasicType(new BasicTypeNode(child));
            } else if (child.getSymbol().equals("UnaryExpression")){
                castExprNode.UnaryExpression(new UnaryExprNode(child));
            } else if (child.getSymbol().equals("UnaryExpressionNotMinus")){
                // create wrapper UnaryExpression for child
                List<Node> listChildren = new ArrayList<Node>();
                listChildren.add(child);
                Node unaryNode = new Node("UnaryExpression", listChildren);
                castExprNode.UnaryExpression(new UnaryExprNode(unaryNode));
            } else if (child.getSymbol().equals("Name")){
                castExprNode.Name(new NameNode(child));
            } else if (child.getSymbol().equals("Dims")){
                castExprNode.Dims(true);
            } // else if
        } // for
        if(castExprNode.BasicType() != null){
            visit(castExprNode.BasicType());
        } // if
        if(castExprNode.UnaryExpression() != null){
            visit(castExprNode.UnaryExpression());
        } // if
        if(castExprNode.Name() != null){
            visit(castExprNode.Name());
        } // if
    } // visit(CastExprNode)

    @Override
    public void visit(BinOpExprNode binOpExprNode) {
        // traverse tree until UnaryExpression or the first binary expression
        Node currNode = binOpExprNode.get_parseTreeNode();
        while(currNode.getChildren().size() == 1 && !currNode.getSymbol().equals("UnaryExpression")){
            currNode = currNode.getChildren().get(0);
        } // while

        if(currNode.getSymbol().equals("UnaryExpression")){
            // unary Expression
            binOpExprNode.UnaryExpr(new UnaryExprNode(currNode));
        } else {
            // binary expression
            Node child1= currNode.getChildren().get(0);
            Node child2= currNode.getChildren().get(2);
            Node op= currNode.getChildren().get(1);
            binOpExprNode.LeftExpr(new BinOpExprNode(child1));
            binOpExprNode.Operator(op.getSymbol());

            if(child2.getSymbol().equals("ReferenceType")){
                child2 = child2.getChildren().get(0);
                if(child2.getSymbol().equals("ArrayType")){
                    binOpExprNode.ArrayType(new ArrayTypeNode(child2));
                } else if(child2.getSymbol().equals("ClassOrInterfaceType")) {
                    binOpExprNode.ClassOrInterfaceType(new ClassOrInterfaceTypeNode(child2));
                } // else if
            } else {
                binOpExprNode.RightExpr(new BinOpExprNode(child2));
            } // else
        } // else
        if(binOpExprNode.UnaryExpr() != null) {
            visit(binOpExprNode.UnaryExpr());
        } else {
            visit(binOpExprNode.LeftExpr());
            if (binOpExprNode.ArrayType() != null) {
                visit(binOpExprNode.ArrayType());
            } else if (binOpExprNode.ClassOrInterfaceType() != null) {
                visit(binOpExprNode.ClassOrInterfaceType());
            } else if (binOpExprNode.RightExpr() != null) {
                visit(binOpExprNode.RightExpr());
            } else throw new Error();
        } // else
    } // visit(BinOpExprNode)

    @Override
    public void visit(StatementExprNode statementExprNode) {
        Node node=statementExprNode.get_parseTreeNode().getChildren().get(0);
        if(node.getSymbol().equals("Assignment")){
            statementExprNode.Assignment(new AssignmentNode(node));
        } else if (node.getSymbol().equals("MethodInvocation")){
            statementExprNode.MethodInvocation(new MethodInvocationNode(node));
        } else if (node.getSymbol().equals("ClassInstanceCreationExpression")){
            statementExprNode.ClassInstanceCreationExpression(new ClassInstanceCreationExprNode(node));
        } // else if
        if(statementExprNode.Assignment() != null){
            visit(statementExprNode.Assignment());
        } else if(statementExprNode.MethodInvocation() != null){
            visit(statementExprNode.MethodInvocation());
        } else if(statementExprNode.ClassInstanceCreationExpression() != null){
            visit(statementExprNode.ClassInstanceCreationExpression());
        } // else if
    } // visit(StatementExprNode)


    @Override
    public void visit(NoTrailingStmtNode noTrailingStmtNode){
        Node child=noTrailingStmtNode.get_parseTreeNode().getChildren().get(0);
        String nodeType=child.getSymbol();
        if (nodeType.equals("Block")){
            noTrailingStmtNode.Block(new BlockNode(child));
        } else if (nodeType.equals("EmptyStatement")){
            noTrailingStmtNode.EmptyStatement();
        } else if (nodeType.equals("ExpressionStatement")){
            noTrailingStmtNode.StatementExpr(new StatementExprNode(child.getChildren().get(0)));
        } else if (nodeType.equals("ReturnStatement")){
            noTrailingStmtNode.ReturnStmt(new ReturnStmtNode(child));
        } // else if

        if(noTrailingStmtNode.Block() != null){
            visit(noTrailingStmtNode.Block());
        } else if(noTrailingStmtNode.StatementExpr() != null){
            visit(noTrailingStmtNode.StatementExpr());
        } else if(noTrailingStmtNode.ReturnStmt() != null){
            visit(noTrailingStmtNode.ReturnStmt());
        } // else if
    } // visit(NoTrailingStmtNode)

    @Override
    public void visit(IfStmtNode ifStmtNode){
        for(Node child: ifStmtNode.get_parseTreeNode().getChildren()){
            if(child.getSymbol().equals("Expression")){ //we assign it the node below it
                ifStmtNode.Expression(new AssignmentExprNode(child.getChildren().get(0)));
            } else if (child.getSymbol().equals("Statement") || child.getSymbol().equals("StatementNoShortIf")){
                ifStmtNode.addStatement(new StatementNode(child));
            } // else if
        } // for
        if(ifStmtNode.Expression() != null){
            visit(ifStmtNode.Expression());
        } // if
        for(StatementNode stmt: ifStmtNode.Statements()){
            visit(stmt);
        } // for
    } // visit(IfStmtNode)

    @Override
    public void visit(ReturnStmtNode returnStmtNode){
        for(Node child: returnStmtNode.get_parseTreeNode().getChildren()){
            if (child.getSymbol().equals("Expression")){
                returnStmtNode.Expression(new AssignmentExprNode(child.getChildren().get(0)));
            } // if
        } // for
        if(returnStmtNode.Expression() != null){
            visit(returnStmtNode.Expression());
        } // if
    } // visit(ReturnStmtNode)

    @Override
    public void visit(WhileStmtNode whileStmtNode){
        for(Node child: whileStmtNode.get_parseTreeNode().getChildren()) {
            if(child.getSymbol().equals("Expression")){
                whileStmtNode.Expression(new AssignmentExprNode(child.getChildren().get(0)));
            } else if (child.getSymbol().equals("Statement") || child.getSymbol().equals("StatementNoShortIf")){
                whileStmtNode.Statement(new StatementNode(child));
            } // else if
        } // for
        if(whileStmtNode.Expression() != null){
            visit(whileStmtNode.Expression());
        } // if
        if (whileStmtNode.Statement() != null){
            visit(whileStmtNode.Statement());
        } // if
    } // visit(WhileStmtNode)

    @Override
    public void visit(ForStmtNode forStmtNode){
        Node forStart=forStmtNode.get_parseTreeNode().getChildren().get(0);
        for(Node child: forStart.getChildren()) {
            if (child.getSymbol().equals("ForInit")) {
                Node subchild = child.getChildren().get(0);
                if (subchild.getSymbol().equals("StatementExpression")) {
                    forStmtNode.StatementExpression(new StatementExprNode(subchild));
                } else if (subchild.getSymbol().equals("LocalVariableDeclaration")) {
                    forStmtNode.LocalVariableDeclaration(new LocalVariableDeclNode(subchild));
                } // else if
            } else if (child.getSymbol().equals("Expression")) {
                forStmtNode.Expression(new AssignmentExprNode(child.getChildren().get(0)));
            } else if (child.getSymbol().equals("ForUpdate")) {
                forStmtNode.Forupdate(new StatementExprNode(child.getChildren().get(0)));
            } // else if
        } // for
        forStmtNode.Statement(new StatementNode(forStmtNode.get_parseTreeNode().getChildren().get(1)));

        if(forStmtNode.StatementExpression() != null){
            visit(forStmtNode.StatementExpression());
        } // if
        if(forStmtNode.Expression() != null){
            visit(forStmtNode.Expression());
        } // if
        if(forStmtNode.Forupdate() != null){
            visit(forStmtNode.Forupdate());
        } // if
        if (forStmtNode.LocalVariableDeclaration() != null){
            visit(forStmtNode.LocalVariableDeclaration());
        } // if
        visit(forStmtNode.Statement());
    } // visit(ForStmtNode)

    @Override
    public void visit(LiteralNode literalNode) {
        Node typeNode=literalNode.get_parseTreeNode().getChildren().get(0);
        String type=typeNode.getSymbol();

        literalNode.LiteralType(type);
        if(type.equals("IntegerLiteral")){
            literalNode.IntegerLiteral(typeNode.getLexeme());
            literalNode.LiteralType(LiteralNode.Literal_INT);
        } else if (type.equals("CharacterLiteral")){
            literalNode.Charliteral(typeNode.getLexeme());
            literalNode.LiteralType(LiteralNode.Literal_CHAR);
        } else if (type.equals("StringLiteral")){
            literalNode.Stringliteral(typeNode.getLexeme());
            literalNode.LiteralType(LiteralNode.Literal_STR);
        } else if (type.equals("BooleanLiteral")){
            literalNode.BooleanLiteral(typeNode.getLexeme());
            literalNode.LiteralType(LiteralNode.Literal_BOOL);
        } else if(type.equals("NULL")){
            literalNode.LiteralType(LiteralNode.Literal_NULL);
        } // else if
    } // visit(LiteralNode)

    @Override
    public void visit(ImportDeclNode importDeclNode) {
        Node child = importDeclNode.get_parseTreeNode().getChildren().get(0);

        if(child.getSymbol().equals("SingleTypeImportDeclaration")){
            importDeclNode.isSingleType(true);
        } else if (child.getSymbol().equals("TypeImportOnDemandDeclaration")){
            importDeclNode.isSingleType(false);
        } // else if

        importDeclNode.Name(new NameNode(child.getChildren().get(1)));

        visit(importDeclNode.Name());
    } // visit(ImportDeclNode)

    @Override
    public void visit(NameNode nameNode) {
        Node child = nameNode.get_parseTreeNode().getChildren().get(0);
        if(child.getSymbol().equals("Identifier")){
            nameNode.addName(child.getChildren().get(0).getLexeme());
        } else {
            for(Node subchild: child.getChildren()){
                if(subchild.getSymbol().equals("Identifier")){
                    nameNode.addName(subchild.getChildren().get(0).getLexeme());
                } // if
            } // for
        } // else
    } // visit(NameNode)

    @Override
    public void visit(InterfaceDeclNode interfaceDeclNode) {
        for(Node child: interfaceDeclNode.get_parseTreeNode().getChildren()){
            if (child.getSymbol().equals("Modifiers")){
                for(Node modNode: child.getChildren()){
                    interfaceDeclNode.addModifier(new ModifierNode(modNode));
                } // for
            } else if (child.getSymbol().equals("Identifier")){
                interfaceDeclNode.Identifier(child.getChildren().get(0).getLexeme());
            } else if (child.getSymbol().equals("InterfaceBody")){
                interfaceDeclNode.InterfaceBody(new InterfaceBodyNode(child));
            } else if (child.getSymbol().equals("ExtendsInterfaces")) {
                Node InterfaceTypeList = child.getChildren().get(1);
                for (Node element : InterfaceTypeList.getChildren()) {
                    Node subchild = element.getChildren().get(0).getChildren().get(0);
                    interfaceDeclNode.addExtendsInterface(new NameNode(subchild));
                } // for
            } // else if
        } // for
        // visits children
        for(ModifierNode modifier: interfaceDeclNode.Modifiers()){
            visit(modifier);
        } // for
        for(NameNode node: interfaceDeclNode.ExtendsInterfaces()){
            visit(node);
        } // for
        if(interfaceDeclNode.ExtendsInterfaces().isEmpty()) {
            // if no superinterfaces, adds Object as a "superinterface"
            NameNode objectName = new NameNode(null);
            objectName.addName("java");
            objectName.addName("lang");
            objectName.addName("Object");
            interfaceDeclNode.addExtendsInterface(objectName);
        } // if
        visit(interfaceDeclNode.InterfaceBody());
    } // visit(InterfaceDeclNode)

    @Override
    public void visit(InterfaceBodyNode interfaceBodyNode) {
        for(Node child: interfaceBodyNode.get_parseTreeNode().getChildren()){
            if (child.getSymbol().equals("InterfaceMemberDeclarations")){
                for(Node decl: child.getChildren()){
                    interfaceBodyNode.addInterfaceMemberDecl(new InterfaceMemberDeclNode(decl));
                } // for
            } // if
        } // for
        for(InterfaceMemberDeclNode child: interfaceBodyNode.InterfaceMemberDeclarations()){
            visit(child);
        } // for
    } // visit(InterfaceBodyNode)

    @Override
    public void visit(PackageDeclNode packageDeclNode) {
        for(Node child: packageDeclNode.get_parseTreeNode().getChildren()){
            if (child.getSymbol().equals("Name")){
                packageDeclNode.Name(new NameNode(child));
            } // if
        } // for
        if(packageDeclNode.Name() != null){
            visit(packageDeclNode.Name());
        } // if
    } // visit(PackageDeclNode)

    @Override
    public void visit(ClassBodyNode classBodyNode) {
        Node decls=classBodyNode.get_parseTreeNode().getChildren().get(1);
        for(Node child: decls.getChildren()){
            classBodyNode.addClassBodyDecl(new ClassBodyDeclNode(child));
        } // for
        for(ClassBodyDeclNode child: classBodyNode.ClassBodyDeclarations()){
            visit(child);
        } // for
    } // visit(ClassBodyNode)

    @Override
    public void visit(TypeNode typeNode){
        Node type= typeNode.get_parseTreeNode().getChildren().get(0);
        if(type.getSymbol().equals("BasicType")){
            typeNode.BasicType(new BasicTypeNode(type));
        } else {
            Node subtype = type.getChildren().get(0);
            if(subtype.getSymbol().equals("ArrayType")){
                typeNode.ArrayType(new ArrayTypeNode(subtype));
            } else if(subtype.getSymbol().equals("ClassOrInterfaceType")){
                typeNode.ClassOrInterfaceType(new ClassOrInterfaceTypeNode(subtype));
            } // else if
        } // else
        if(typeNode.BasicType() != null) visit(typeNode.BasicType());
        else if(typeNode.ArrayType() != null) visit(typeNode.ArrayType());
        else if(typeNode.ClassOrInterfaceType() != null) visit(typeNode.ClassOrInterfaceType());
    } // visit(TypeNode)

    @Override
    public void visit(BasicTypeNode basicTypeNode){
        basicTypeNode.setElement(basicTypeNode.get_parseTreeNode().getChildren().get(0).getLexeme());
    } // visit(BasicTypeNode)

} // class ASTVisitor