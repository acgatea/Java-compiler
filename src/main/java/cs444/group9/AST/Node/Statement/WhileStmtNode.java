/*******************************************************************************
 * WhileStmtNode.java
 * 
 * A module implementing the WhileStmt node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Statement;

import cs444.group9.AST.Node.Expression.AssignmentExprNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;
import cs444.group9.AST.Node.ASTNode;


public class WhileStmtNode extends ASTNode {
    AssignmentExprNode _expression;
    StatementNode _statement;

    /*******************************************************************************
     * WhileStmtNode constructor
     * time : O(1)
     * *****************************************************************************/
    public WhileStmtNode(Node parseTreeNode) {
        super(parseTreeNode);
    } // WhileStmtNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/
    @Override
    public void print() {
        _expression.print();
        _statement.print();
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
    public AssignmentExprNode Expression(){
        return _expression;
    }

    public void Expression(AssignmentExprNode assignmentNode){
        _expression=assignmentNode;
    }

    public StatementNode Statement(){
        return _statement;
    }

    public void Statement(StatementNode statementNode){
        _statement=statementNode;
    }
} // class WhileStmtNode