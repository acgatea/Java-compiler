/*******************************************************************************
 * BinOpExprNode.java
 * 
 * A module implementing the BinOpExpr node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Expression;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.LiteralNode;
import cs444.group9.AST.Node.Type.ArrayTypeNode;
import cs444.group9.AST.Node.Type.ClassOrInterfaceTypeNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

/*CONTAINS THE FOLLOWING
MultiplicativeExpression / MultiplicativeExprNode
AdditiveExpression / AdditiveExprNode
EqualityExpression / EqualityExprNode
AndExpression / AndExprNode
InclusiveOrExpression / InclusiveOrNode
ConditionalAndExpression / ConditionalAndNode
ConditionalOrExpression / ConditionalOrNode
RelationalExpression / RelationalExprNode
 */

public class BinOpExprNode extends ExprNode {
    BinOpExprNode _leftExpr;
    String _operator;
    BinOpExprNode _rightExpr;
    UnaryExprNode _unaryExpr;
    ArrayTypeNode _arrayType;
    ClassOrInterfaceTypeNode _classOrInterfType;

    //operator constants
    public final static String OPER_MULT="STAR";
    public final static String OPER_DIV="SLASH";
    public final static String OPER_PCT="PCT";
    public final static String OPER_PLUS="PLUS";
    public final static String OPER_MINUS="MINUS";
    public final static String OPER_LT="LT";
    public final static String OPER_GT="GT";
    public final static String OPER_LE="LE";
    public final static String OPER_GE="GE";
    public final static String OPER_EQ="EQUALS";
    public final static String OPER_NEQ="NE";
    public final static String OPER_AND="AMP";
    public final static String OPER_OR="OR";
    public final static String OPER_AMPAMP="AMPAMP";
    public final static String OPER_OROR="OROR";

    /*******************************************************************************
     * BinOpExprNode constructor
     * time : O(1)
     * *****************************************************************************/
    public BinOpExprNode(Node parseTreeNode) {
        super(parseTreeNode);
    } // BinOpExprNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/
    @Override
    public void print() {
        if(_unaryExpr != null) {
            _unaryExpr.print();
        } else if (_arrayType != null){
            _leftExpr.print();
            _arrayType.print();
        } else if (_classOrInterfType != null){
            _leftExpr.print();
            _classOrInterfType.print();
        } else if (_rightExpr != null){
            _leftExpr.print();
            System.out.println("Operator: " + _operator);
            _rightExpr.print();
        } else throw new Error();
    } // print()

    /*******************************************************************************
     * Visitor acceptor
     * time : O(1)
     * *****************************************************************************/ 
    @Override
    public void accept(iVisitor visitor)throws iVisitor.ASTException {
        visitor.visit(this);
    } // accept()

    /*******************************************************************************
     * Accessor methods
     * *****************************************************************************/ 
    public String Operator() {
        return _operator;
    }

    public BinOpExprNode LeftExpr() {
        return _leftExpr;
    }

    public BinOpExprNode RightExpr() {
        return _rightExpr;
    }

    public UnaryExprNode UnaryExpr() {
        return _unaryExpr;
    }

    public ArrayTypeNode ArrayType() {
        return _arrayType;
    }

    public ClassOrInterfaceTypeNode ClassOrInterfaceType() {
        return _classOrInterfType;
    }

    public void LeftExpr(BinOpExprNode leftExpr) {
        _leftExpr = leftExpr;
    }

    public void Operator(String operator) {
        _operator = operator;
    }

    public void RightExpr(BinOpExprNode rightExpr) {
        _rightExpr = rightExpr;
    }

    public void UnaryExpr(UnaryExprNode unaryExpr) {
        _unaryExpr = unaryExpr;
    }

    public void ArrayType(ArrayTypeNode arrayType){
        _arrayType=arrayType;
    }

    public void ClassOrInterfaceType(ClassOrInterfaceTypeNode classOrInterfType){
        _classOrInterfType=classOrInterfType;
    }

} // class BinOpExprNode