/*******************************************************************************
 * ExprNode.java
 * 
 * A module implementing the Expr node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Expression;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.LiteralNode;
import cs444.group9.Parser.Node;

public abstract class ExprNode extends ASTNode {
    //constants
    boolean _isConstant;
    LiteralNode _constantValue;

    /*******************************************************************************
     * ExprNode constructor
     * time : O(1)
     * *****************************************************************************/
    public ExprNode(Node parseTreeNode) {
        super(parseTreeNode);
        _isConstant=false;
    } // ExprNode()

    /*******************************************************************************
     * Accessor methods
     * *****************************************************************************/ 
    public boolean isConstant(){
        return _isConstant;
    }

    public void setIsConstant(boolean constant){
        _isConstant=constant;
    }

    public LiteralNode getConstant(){
        return _constantValue;
    }

    public void setConstantValue(LiteralNode literalNode){
        _constantValue=literalNode;
    }
} // class ExprNode