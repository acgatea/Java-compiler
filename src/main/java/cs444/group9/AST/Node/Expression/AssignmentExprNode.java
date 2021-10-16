/*******************************************************************************
 * AssignmentExprNode.java
 * 
 * A module implementing the AssignmentExpr node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Expression;

import cs444.group9.AST.Node.StatementExpression.AssignmentNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;
import cs444.group9.AST.Node.ASTNode;


public class AssignmentExprNode extends ExprNode {
    public BinOpExprNode _conditionalExpression;
    public AssignmentNode _assignment;

    /*******************************************************************************
     * AssignmentExprNode constructor
     * time : O(1)
     * *****************************************************************************/
    public AssignmentExprNode(Node parseTreeNode) {
        super(parseTreeNode);
    } // AssignmentExprNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/
    @Override
    public void print() {
        if(_conditionalExpression != null){
            _conditionalExpression.print();
        } else if (_assignment != null){
            _assignment.print();
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
    public AssignmentNode Assignment() {
        return _assignment;
    }

    public BinOpExprNode ConditionalExpression() {
        return _conditionalExpression;
    }

    public void Assignment(AssignmentNode assignment) {
        _assignment = assignment;
    }

    public void ConditionalExpression(BinOpExprNode conditionalExpression) {
        _conditionalExpression = conditionalExpression;
    }
} // class AssingmentExprNode