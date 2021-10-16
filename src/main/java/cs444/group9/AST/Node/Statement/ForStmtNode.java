/*******************************************************************************
 * ForStmtNode.java
 * 
 * A module implementing the ForStmt node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Statement;

import cs444.group9.AST.Node.Declaration.LocalVariableDeclNode;
import cs444.group9.AST.Node.Expression.AssignmentExprNode;
import cs444.group9.AST.Node.StatementExpression.StatementExprNode;
import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

public class ForStmtNode extends ASTNode{
    StatementExprNode _statementExpression;
    LocalVariableDeclNode _localVariableDeclaration;
    StatementNode _statement;
    StatementExprNode _forupdate;
    AssignmentExprNode _expression;

    /*******************************************************************************
     * ForStmtNode constructor
     * time : O(1)
     * *****************************************************************************/
    public ForStmtNode(Node parseTreeNode) {
        super(parseTreeNode);
    } // ForStmtNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/
    @Override
    public void print() {
        if(_statementExpression != null){
            _statementExpression.print();
        } else if (_localVariableDeclaration != null){
            _localVariableDeclaration.print();
        } // else if
        if(_expression != null){
            _expression.print();
        } // if
        if(_forupdate != null){
            _forupdate.print();
        } // if
        if(_statement != null){
            _statement.print();
        } // if
    } // print()

    /*******************************************************************************
     * Visitor acceptor
     * time : O(1)
     * *****************************************************************************/ 
    @Override
    public void accept(iVisitor visitor) throws iVisitor.ASTException {
        visitor.visit(this);
    } // accept()

    /*******************************************************************************
     * Accessor methods
     * *****************************************************************************/ 
    public StatementNode Statement() {
        return _statement;
    }

    public AssignmentExprNode Expression() {
        return _expression;
    }

    public StatementExprNode StatementExpression() {
        return _statementExpression;
    }

    public StatementExprNode Forupdate() {
        return _forupdate;
    }

    public void Forupdate(StatementExprNode forupdate) {
        _forupdate = forupdate;
    }

    public LocalVariableDeclNode LocalVariableDeclaration() {
        return _localVariableDeclaration;
    }

    public void LocalVariableDeclaration(LocalVariableDeclNode localVariableDeclaration) {
        _localVariableDeclaration = localVariableDeclaration;
    }

    public void Statement(StatementNode statement) {
        _statement = statement;
    }

    public void Expression(AssignmentExprNode expression) {
        _expression = expression;
    }

    public void StatementExpression(StatementExprNode statementExpression) {
        _statementExpression = statementExpression;
    }
} // class ForStmtNode