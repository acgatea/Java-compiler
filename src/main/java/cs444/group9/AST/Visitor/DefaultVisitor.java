/*******************************************************************************
 * DefaultVisitor.java
 * 
 * A module implementing the default AST Visitor.
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
import cs444.group9.AST.Node.Expression.*;
import cs444.group9.AST.Node.Statement.*;
import cs444.group9.AST.Node.StatementExpression.AssignmentNode;
import cs444.group9.AST.Node.StatementExpression.ClassInstanceCreationExprNode;
import cs444.group9.AST.Node.StatementExpression.MethodInvocationNode;
import cs444.group9.AST.Node.StatementExpression.StatementExprNode;
import cs444.group9.AST.Node.Type.ArrayTypeNode;
import cs444.group9.AST.Node.Type.BasicTypeNode;
import cs444.group9.AST.Node.Type.ClassOrInterfaceTypeNode;
import cs444.group9.AST.Node.Type.TypeNode;

public class DefaultVisitor implements iVisitor {

    /*******************************************************************************
     * Visits the root of tree
     * *****************************************************************************/ 
    public void findTypes(ASTTree tree) throws ASTException{
        visit((CompilationUnitNode)tree.getRoot());
    } // findTypes()

    /*******************************************************************************
     * OVER-RIDDEN VISIT METHODS
     * *****************************************************************************/ 
    @Override
    public void visit(CompilationUnitNode node) throws ASTException{
        for(ImportDeclNode child: node.ImportDeclarations()){
            visit(child);
        } // for
        if(node.PackageDeclaration() != null){
            visit(node.PackageDeclaration());
        } // if
        if(node.TypeDeclaration() != null) {
            visit(node.TypeDeclaration());
        } // if
    } // visit(CompilationUnitNode)

    @Override
    public void visit(ClassDeclNode node) throws iVisitor.ASTException{
        //visit the nodes
        for(ModifierNode element: node.Modifiers()){
            visit(element);
        } // for
        for(NameNode element: node.Interfaces()){
            visit(element);
        } // for
        if(node.ClassType() != null){
            visit(node.ClassType());
        } // if
        if (node.ClassBody() != null){
            visit(node.ClassBody());
        } // if
    } // visit(ClassDeclNode)

    @Override
    public void visit(TypeDeclNode node) throws iVisitor.ASTException{
        if(node.ClassDeclaration() != null) {
            visit(node.ClassDeclaration());
        } else if(node.InterfaceDeclaration() != null) {
            visit(node.InterfaceDeclaration());
        } // else if
    } // visit(TypeDeclNode)

    @Override
    public void visit(ClassBodyDeclNode node) throws iVisitor.ASTException{
        if (node.ClassMemberDeclaration() != null){
            visit(node.ClassMemberDeclaration());
        } else if (node.ConstructorDeclaration() != null){
            visit(node.ConstructorDeclaration());
        } // else if
    } // visit(ClassBodyDeclNode)

    @Override
    public void visit(ModifierNode node) throws iVisitor.ASTException{ }

    @Override
    public void visit(ConstructorDeclarationNode node) throws iVisitor.ASTException{
        for(ModifierNode element: node.Modifiers()){
            visit(element);
        } // for
        visit(node.ConstructorDeclarator());
        visit(node.ConstructorBody());
    } // visit(ConstructorDeclarationNode)

    @Override
    public void visit(ConstructorBodyNode constructorBodyNode) throws iVisitor.ASTException{
        for(BlockStatementNode stmt: constructorBodyNode.BlockStatements()){
            visit(stmt);
        } // for
    } // visit(ConstructorBodyNode)

    @Override
    public void visit(ClassMemberDeclNode node) throws iVisitor.ASTException{
        if(node.FieldDeclaration() != null){
            visit(node.FieldDeclaration());
        } else if(node.MethodDeclaration() != null){
            visit(node.MethodDeclaration());
        } // else if
    } // visit(ClassMemberDeclNode)

    @Override
    public void visit(MethodDeclarationNode node) throws iVisitor.ASTException{
        visit(node.MethodHeader());
        visit(node.MethodBody());
    } // visit(MethodDeclarationNode)

    @Override
    public void visit(FieldDeclNode node) throws iVisitor.ASTException{
        for(ModifierNode modifier: node.Modifiers()){
            visit(modifier);
        } // for
        if (node.Type() != null){
            visit(node.Type());
        } // if
        visit(node.VariableDeclarator());
    } // visit(FieldDeclNode)

    public void visit(ArrayTypeNode arrayTypeNode) throws iVisitor.ASTException{
        if(arrayTypeNode.BasicType() != null){
            visit(arrayTypeNode.BasicType());
        } else if(arrayTypeNode.Name() != null){
            visit(arrayTypeNode.Name());
        } // else if
    } // visit(ArrayTypeNode)

    public void visit(VariableDeclNode variableDeclNode) throws iVisitor.ASTException{
        if (variableDeclNode.VariableInitializer() != null){
            visit(variableDeclNode.VariableInitializer());
        } // if
    } // visit(VariableDeclNode)

    @Override
    public void visit(ClassOrInterfaceTypeNode classOrInterfaceTypeNode) throws iVisitor.ASTException{
        visit(classOrInterfaceTypeNode.Name());
    } // visit(ClassOrInterfaceTypeNode)

    @Override
    public void visit(AssignmentExprNode assignmentExprNode) throws iVisitor.ASTException{
        if(assignmentExprNode.ConditionalExpression() != null) {
            visit(assignmentExprNode.ConditionalExpression());
        } else if(assignmentExprNode.Assignment() != null) {
            visit(assignmentExprNode.Assignment());
        } // else if
    } // visit(AssignmentExprNode)

    @Override
    public void visit(MethodDeclNode methodDeclNode) throws iVisitor.ASTException{
        for(FormalParameterNode param: methodDeclNode.FormalParameterList()){
            visit(param);
        } // for
    } // visit(MethodDeclNode)

    @Override
    public void visit(MethodBodyNode methodBodyNode) throws iVisitor.ASTException{
        if(methodBodyNode.Block() != null) {
            visit(methodBodyNode.Block());
        } // if
    } // visit(MethodBodyNode)

    @Override
    public void visit(MethodHeaderNode methodHeaderNode) throws iVisitor.ASTException{
        for(ModifierNode node: methodHeaderNode.Modifiers()){
            visit(node);
        } // for
        if(methodHeaderNode.ReturnType() != null){
            visit(methodHeaderNode.ReturnType());
        } // if
        visit(methodHeaderNode.MethodDeclarator());
    } // visit(MethodHeaderNode)


    @Override
    public void visit(FormalParameterNode formalParameterNode) throws iVisitor.ASTException{
        if (formalParameterNode.Type() != null){
            visit(formalParameterNode.Type());
        } // if
    } // visit(FormalParameterNode)

    @Override
    public void visit(ConstructorDeclNode constructorDeclNode) throws iVisitor.ASTException{
        for(FormalParameterNode param: constructorDeclNode.FormalParameterList()){
            visit(param);
        } // for
    } // visit(ConstructorDeclNode)

    @Override
    public void visit(BlockStatementNode blockStatementNode) throws iVisitor.ASTException{
        if (blockStatementNode.Statement() != null){
                visit(blockStatementNode.Statement());
        } // if
        if (blockStatementNode.LocalVariableDeclaration() != null){
                visit(blockStatementNode.LocalVariableDeclaration());
        } // if
    } // visit(BlockStatementNode)

    @Override
    public void visit(InterfaceMemberDeclNode interfaceMemberDeclNode) throws iVisitor.ASTException{
        if (interfaceMemberDeclNode.AbstractMethodDeclaration() != null){
            visit(interfaceMemberDeclNode.AbstractMethodDeclaration());
        } // if
    } // visit(InterfaceMemberDeclNode)

    @Override
    public void visit(AbstractMethodDeclNode abstractMethodDeclNode) throws iVisitor.ASTException{
        if (abstractMethodDeclNode.MethodHeader() != null){
            visit(abstractMethodDeclNode.MethodHeader());
        } // if
    } // visit(AbstractMethodDeclNode)

    @Override
    public void visit(BlockNode blockNode) throws iVisitor.ASTException{
        for(BlockStatementNode node: blockNode.BlockStatements()){
            visit(node);
        } // for
    } // visit(BlockNode)

    @Override
    public void visit(LocalVariableDeclNode localVariableDeclNode) throws iVisitor.ASTException{
        if(localVariableDeclNode.Type() != null){
            visit(localVariableDeclNode.Type());
        } // if
        if(localVariableDeclNode.VariableInitializer() != null){
            visit(localVariableDeclNode.VariableInitializer());
        } // if
    } // visit(LocalVariableDeclNode)

    @Override
    public void visit(StatementNode statementNode) throws iVisitor.ASTException{
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
    public void visit(AssignmentNode assignmentNode) throws iVisitor.ASTException{
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
    public void visit(MethodInvocationNode methodInvocationNode) throws iVisitor.ASTException{
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
    public void visit(ClassInstanceCreationExprNode classInstanceCreationExprNode) throws iVisitor.ASTException{
        if(classInstanceCreationExprNode.ClassType() != null){
            visit(classInstanceCreationExprNode.ClassType());
        } // if
        for(AssignmentExprNode n: classInstanceCreationExprNode.ArgumentList()){
            visit(n);
        } // for
    } // visit(ClassInstanceCreationExprNode)

    @Override
    public void visit(PrimaryNode primaryNode) throws iVisitor.ASTException{
        if(primaryNode.PrimaryNoNewArray() != null){
            visit(primaryNode.PrimaryNoNewArray());
        } else if(primaryNode.ArrayCreationExpression() != null){
            visit(primaryNode.ArrayCreationExpression());
        } // else if
    } // visit(PrimaryNode)

    @Override
    public void visit(PrimaryNoNewArrayNode primaryNoNewArrayNode) throws iVisitor.ASTException{
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
    public void visit(ArrayCreationExprNode arrayCreationExprNode) throws iVisitor.ASTException{
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
    public void visit(FieldAcccessNode fieldAcccessNode) throws iVisitor.ASTException{
        visit(fieldAcccessNode.Primary());
    } // visit(FieldAcccessNode)

    @Override
    public void visit(ArrayAccessNode arrayAccessNode) throws iVisitor.ASTException{
        if(arrayAccessNode.Name() != null){
            visit(arrayAccessNode.Name());
        } // if
        visit(arrayAccessNode.Expression());
        if(arrayAccessNode.PrimaryNoNewArray() != null){
            visit(arrayAccessNode.PrimaryNoNewArray());
        } // if
    } // visit(ArrayAccessNode)

    @Override
    public void visit(PostFixExprNode postFixExprNode) throws iVisitor.ASTException{
        if(postFixExprNode.Primary() != null){
            visit(postFixExprNode.Primary());
        } else if(postFixExprNode.Name() != null){
            visit(postFixExprNode.Name());
        } // else if
    } // visit(PostFixExprNode)

    @Override
    public void visit(UnaryExprNode unaryExprNode) throws iVisitor.ASTException{
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
    public void visit(CastExprNode castExprNode) throws iVisitor.ASTException{
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
    public void visit(BinOpExprNode binOpExprNode) throws iVisitor.ASTException{
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
    public void visit(StatementExprNode statementExprNode) throws iVisitor.ASTException{
        if(statementExprNode.Assignment() != null){
            visit(statementExprNode.Assignment());
        } else if(statementExprNode.MethodInvocation() != null){
            visit(statementExprNode.MethodInvocation());
        } else if(statementExprNode.ClassInstanceCreationExpression() != null){
            visit(statementExprNode.ClassInstanceCreationExpression());
        } // else if
    } // visit(StatementExprNode)

    @Override
    public void visit(NoTrailingStmtNode noTrailingStmtNode) throws iVisitor.ASTException{
        if(noTrailingStmtNode.Block() != null){
            visit(noTrailingStmtNode.Block());
        } else if(noTrailingStmtNode.StatementExpr() != null){
            visit(noTrailingStmtNode.StatementExpr());
        } else if(noTrailingStmtNode.ReturnStmt() != null){
            visit(noTrailingStmtNode.ReturnStmt());
        } // else if
    } // visit(NoTrailingStmtNode)

    @Override
    public void visit(IfStmtNode ifStmtNode) throws iVisitor.ASTException{
        if(ifStmtNode.Expression() != null){
            visit(ifStmtNode.Expression());
        } // if
        for(StatementNode stmt: ifStmtNode.Statements()){
            visit(stmt);
        } // for
    } // visit(IfStmtNode)

    @Override
    public void visit(ReturnStmtNode returnStmtNode) throws iVisitor.ASTException{
        if(returnStmtNode.Expression() != null){
            visit(returnStmtNode.Expression());
        } // if
    } // visit(ReturnStmtNode)

    @Override
    public void visit(WhileStmtNode whileStmtNode) throws iVisitor.ASTException{
        if(whileStmtNode.Expression() != null){
            visit(whileStmtNode.Expression());
        } // if
        if (whileStmtNode.Statement() != null){
            visit(whileStmtNode.Statement());
        } // if
    } // visit(WhileStmtNode)

    @Override
    public void visit(ForStmtNode forStmtNode) throws iVisitor.ASTException{
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
    public void visit(LiteralNode literalNode) throws iVisitor.ASTException{}

    @Override
    public void visit(ImportDeclNode importDeclNode) throws iVisitor.ASTException{
        visit(importDeclNode.Name());
    } // visit(ImportDeclNode)

    @Override
    public void visit(NameNode nameNode) throws iVisitor.ASTException{ }

    @Override
    public void visit(InterfaceDeclNode interfaceDeclNode) throws iVisitor.ASTException{
        for(ModifierNode modifier: interfaceDeclNode.Modifiers()){
            visit(modifier);
        } // for
        for(NameNode node: interfaceDeclNode.ExtendsInterfaces()){
            visit(node);
        } // for
        visit(interfaceDeclNode.InterfaceBody());
    } // visit(InterfaceDeclNode)

    @Override
    public void visit(InterfaceBodyNode interfaceBodyNode) throws iVisitor.ASTException{
        for(InterfaceMemberDeclNode child: interfaceBodyNode.InterfaceMemberDeclarations()){
            visit(child);
        } // for
    } // visit(InterfaceBodyNode)

    @Override
    public void visit(PackageDeclNode packageDeclNode) throws iVisitor.ASTException{
        if(packageDeclNode.Name() != null){
            visit(packageDeclNode.Name());
        } // if
    } // visit(PackageDeclNode)

    @Override
    public void visit(ClassBodyNode classBodyNode) throws iVisitor.ASTException{
        for(ClassBodyDeclNode child: classBodyNode.ClassBodyDeclarations()){
            visit(child);
        } // for
    } // visit(ClassBodyNode)

    @Override
    public void visit(BasicTypeNode basicTypeNode) throws iVisitor.ASTException{ }

    @Override
    public void visit(TypeNode typeNode) throws iVisitor.ASTException{
        if(typeNode.ArrayType() != null){
            visit(typeNode.ArrayType());
        } else if(typeNode.BasicType() != null){
            visit(typeNode.BasicType());
        } else if(typeNode.ClassOrInterfaceType() != null){
            visit(typeNode.ClassOrInterfaceType());
        } // else if
    } // visit(TypeNode)
} // class DefaultVisitor
