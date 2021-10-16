/*******************************************************************************
 * iVisitor.java
 * 
 * A module with visit method declarations
 * ****************************************************************************/
package cs444.group9.AST.Visitor;

import cs444.group9.AST.Node.*;
import cs444.group9.AST.Node.Array.ArrayAccessNode;
import cs444.group9.AST.Node.Array.FieldAcccessNode;
import cs444.group9.AST.Node.Array.PrimaryNode;
import cs444.group9.AST.Node.Array.PrimaryNoNewArrayNode;
import cs444.group9.AST.Node.Body.*;
import cs444.group9.AST.Node.Declaration.*;
import cs444.group9.AST.Node.Expression.*;
import cs444.group9.AST.Node.Statement.*;
import cs444.group9.AST.Node.Declaration.LocalVariableDeclNode;
import cs444.group9.AST.Node.StatementExpression.AssignmentNode;
import cs444.group9.AST.Node.StatementExpression.ClassInstanceCreationExprNode;
import cs444.group9.AST.Node.StatementExpression.MethodInvocationNode;
import cs444.group9.AST.Node.StatementExpression.StatementExprNode;
import cs444.group9.AST.Node.Type.ArrayTypeNode;
import cs444.group9.AST.Node.Type.BasicTypeNode;
import cs444.group9.AST.Node.Type.ClassOrInterfaceTypeNode;
import cs444.group9.AST.Node.Type.TypeNode;

public interface iVisitor {
    public class ASTException extends Exception {}
    public class EnvironmentBuildingException extends ASTException {}
    public class NameResolutionException extends ASTException {}
    public class InitializationException extends ASTException {}
    public class ReachabilityException extends ASTException {}
    public class ConstantComputeException extends ASTException {}
    public class CodePrinterVisitorException extends ASTException {}

    void visit(CompilationUnitNode node) throws ASTException;

    void visit(ClassDeclNode node) throws ASTException;
    void visit(TypeDeclNode node) throws ASTException;
    void visit(ClassBodyDeclNode node) throws ASTException;
    void visit(ConstructorDeclarationNode node) throws ASTException;
    void visit(ConstructorBodyNode node) throws ASTException;
    void visit(ClassMemberDeclNode node) throws ASTException;
    void visit(MethodDeclarationNode node) throws ASTException;
    void visit(FieldDeclNode node) throws ASTException;

    void visit(NoTrailingStmtNode noTrailingStmtNode) throws ASTException;

    void visit(IfStmtNode ifStmtNode) throws ASTException;

    void visit(ReturnStmtNode returnStmtNode) throws ASTException;

    void visit(WhileStmtNode whileStmtNode) throws ASTException;

    void visit(ForStmtNode forStmtNode) throws ASTException;

    void visit(LiteralNode literalNode) throws ASTException;

    void visit(ImportDeclNode importDeclNode) throws ASTException;

    void visit(NameNode nameNode) throws ASTException;

    void visit(InterfaceDeclNode interfaceDeclNode) throws ASTException;

    void visit(InterfaceBodyNode interfaceBodyNode) throws ASTException;

    void visit(ModifierNode modifierNode) throws ASTException;

    void visit(PackageDeclNode packageDeclNode) throws ASTException;

    void visit(ClassBodyNode classBodyNode) throws ASTException;

    void visit(VariableDeclNode variableDeclNode) throws ASTException;
    void visit(ClassOrInterfaceTypeNode classOrInterfaceTypeNode) throws ASTException;
    void visit(ArrayTypeNode arrayTypeNode) throws ASTException;
    void visit(BasicTypeNode basicTypeNode) throws ASTException;
    void visit(AssignmentExprNode assignmentExprNode) throws ASTException;

    void visit(MethodDeclNode methodDeclNode) throws ASTException;

    void visit(MethodBodyNode methodBodyNode) throws ASTException;

    void visit(MethodHeaderNode methodHeaderNode) throws ASTException;

    void visit(TypeNode type) throws ASTException;

    void visit(FormalParameterNode formalParameterNode) throws ASTException;

    void visit(ConstructorDeclNode constructorDeclNode) throws ASTException;

    void visit(BlockStatementNode blockStatementNode) throws ASTException;

    void visit(InterfaceMemberDeclNode interfaceMemberDeclNode) throws ASTException;

    void visit(AbstractMethodDeclNode abstractMethodDeclNode) throws ASTException;

    void visit(BlockNode blockNode) throws ASTException;

    void visit(LocalVariableDeclNode localVariableDeclNode) throws ASTException;

    void visit(StatementNode statementNode) throws ASTException;

    void visit(AssignmentNode assignmentNode) throws ASTException;

    void visit(MethodInvocationNode methodInvocationNode) throws ASTException;

    void visit(ClassInstanceCreationExprNode classInstanceCreationExprNode) throws ASTException;

    void visit(PrimaryNode primaryNode) throws ASTException;

    void visit(PrimaryNoNewArrayNode primaryNoNewArrayNode) throws ASTException;

    void visit(ArrayCreationExprNode arrayCreationExprNode) throws ASTException;

    void visit(FieldAcccessNode fieldAcccessNode) throws ASTException;

    void visit(ArrayAccessNode arrayAccessNode) throws ASTException;

    void visit(PostFixExprNode postFixExprNode) throws ASTException;

    void visit(UnaryExprNode unaryExprNode) throws ASTException;

    void visit(CastExprNode castExprNode) throws ASTException;

    void visit(BinOpExprNode binOpExprNode) throws ASTException;

    void visit(StatementExprNode statementExprNode) throws ASTException;
} // interface iVisitor