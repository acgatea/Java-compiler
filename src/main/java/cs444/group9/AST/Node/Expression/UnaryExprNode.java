/*******************************************************************************
 * UnaryExprNode.java
 * 
 * A module implementing the UnaryExpr node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Expression;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

public class UnaryExprNode extends ExprNode {
    UnaryExprNode _unaryExpression;
    PostFixExprNode _postfixExpression;
    CastExprNode _castExpression;
    String _unaryOp;

    public static final String MINUS_OPER="MINUS";
    public static final String NEG_OPER="EXCLAM";

    /*******************************************************************************
     * UnaryExprNode constructor
     * time : O(1)
     * *****************************************************************************/
    public UnaryExprNode(Node parseTreeNode) {
        super(parseTreeNode);
    } // UnaryExprNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/
    @Override
    public void print() {
        if (_unaryExpression != null){
            System.out.println("UnaryOperator: " + _unaryOp);
            _unaryExpression.print();
        } else if (_postfixExpression != null){
            _postfixExpression.print();
        } else if (_castExpression != null){
            _castExpression.print();
        } else throw new Error();
    } // print()

    /*******************************************************************************
     * Visitor acceptor
     * time : O(1)
     * *****************************************************************************/ 
    @Override
    public void accept(iVisitor visitor) throws iVisitor.ASTException{
        visitor.visit(this);
    } // accept()

    /*******************************************************************************
     * Accessor methods
     * *****************************************************************************/ 
    public CastExprNode CastExpression() {
        return _castExpression;
    }

    public void CastExpression(CastExprNode castExpression){
        _castExpression=castExpression;
    }

    public PostFixExprNode PostfixExpression() {
        return _postfixExpression;
    }

    public void PostfixExpression(PostFixExprNode postfixExpression){
        _postfixExpression=postfixExpression;
    }

    public UnaryExprNode UnaryExpression() {
        return _unaryExpression;
    }

    public void UnaryExpression(UnaryExprNode unaryExpression){
        _unaryExpression=unaryExpression;
    }

    public String UnaryOperator() {
        return _unaryOp;
    }

    public void UnaryOperator(String unaryOp){
        _unaryOp= unaryOp;
    }
} // class UnaryExprNode