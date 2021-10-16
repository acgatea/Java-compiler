/*******************************************************************************
 * ReturnStmtNode.java
 * 
 * A module implementing the ReturnStmt node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Statement;

import cs444.group9.AST.Node.Expression.AssignmentExprNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

public class ReturnStmtNode extends NoTrailingStmtNode {
    AssignmentExprNode _expression;

    /*******************************************************************************
     * ReturnStmtNode constructor
     * time : O(1)
     * *****************************************************************************/
    public ReturnStmtNode(Node parseTreeNode) {
        super(parseTreeNode);
    } // ReturnStmtNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/
    @Override
    public void print(){
        if(_expression != null){
            _expression.print();
        } else throw new Error();
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
    public AssignmentExprNode Expression() {
        return _expression;
    }

    public void Expression(AssignmentExprNode assignmentNode){
        _expression=assignmentNode;
    }
} // class ReturnStmtNode
