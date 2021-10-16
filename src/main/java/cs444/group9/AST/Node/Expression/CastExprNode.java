/*******************************************************************************
 * CastExprNode.java
 * 
 * A module implementing the CastExpr node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Expression;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.NameNode;
import cs444.group9.AST.Node.Type.BasicTypeNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

public class CastExprNode extends ExprNode {
    BasicTypeNode _basicType;
    UnaryExprNode _unaryExpression;
    NameNode _name;
    boolean _dims;

    /*******************************************************************************
     * CastExprNode constructor
     * time : O(1)
     * *****************************************************************************/
    public CastExprNode(Node parseTreeNode) {
        super(parseTreeNode);
        _dims = false;
    } // CastExprNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/
    @Override
    public void print() {
        if (_basicType != null){
            _basicType.print();
        } else if (_name != null){
            _name.print();
        } else throw new Error();
        _unaryExpression.print();
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
    public BasicTypeNode BasicType() {
        return _basicType;
    }

    public void BasicType(BasicTypeNode basicType) {
        _basicType = basicType;
    }

    public UnaryExprNode UnaryExpression() {
        return _unaryExpression;
    }

    public void UnaryExpression(UnaryExprNode unaryExpression){
        _unaryExpression=unaryExpression;
    }

    public NameNode Name() {
        return _name;
    }

    public void Name(NameNode name){
        _name=name;
    }

    public boolean Dims(){
        return _dims;
    }

    public void Dims(boolean dims){
        _dims=dims;
    }

} // class CastExprNode