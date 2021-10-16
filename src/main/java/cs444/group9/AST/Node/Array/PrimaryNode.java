/*******************************************************************************
 * PrimaryNode.java
 * 
 * A module implementing the Primsry node for the AST. 
 * ****************************************************************************/

package cs444.group9.AST.Node.Array;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.Expression.ArrayCreationExprNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

public class PrimaryNode extends ASTNode {
    PrimaryNoNewArrayNode _primaryNoNewArray;
    ArrayCreationExprNode _arrayCreationExpression;

    /*******************************************************************************
     * PrimaryNode constructor
     * time : O(1)
     * *****************************************************************************/
    public PrimaryNode(Node parseTreeNode) {
        super(parseTreeNode);
    } // PrimaryNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/ 
    @Override
    public void print() {
        if(_primaryNoNewArray != null) {
            _primaryNoNewArray.print();
        } else if(_arrayCreationExpression != null) {
            _arrayCreationExpression.print();
        } else throw new Error();
    } // print()

    /*******************************************************************************
     * Visitor accept
     * time : O(1)
     * *****************************************************************************/
    @Override
    public void accept(iVisitor visitor)throws iVisitor.ASTException {
        visitor.visit(this);
    } // accept()

   /*******************************************************************************
     * Getter for _primaryNoNewArray
     * time : O(1)
     * *****************************************************************************/
    public PrimaryNoNewArrayNode PrimaryNoNewArray(){
        return _primaryNoNewArray;
    } // PrimaryNoNewArray()

   /*******************************************************************************
     * Setter for _primaryNoNewArray
     * time : O(1)
     * *****************************************************************************/
    public void PrimaryNoNewArray(PrimaryNoNewArrayNode primaryNoNewArray){
        _primaryNoNewArray=primaryNoNewArray;
    } // PrimaryNoNewArray(PrimaryNoNewArrayNode)

   /*******************************************************************************
     * Getter for _arrayCreationExpression
     * time : O(1)
     * *****************************************************************************/
    public ArrayCreationExprNode ArrayCreationExpression(){
        return _arrayCreationExpression;
    } // ArrayCreationExpression()

   /*******************************************************************************
     * Setter for _arrayCreationExpression
     * time : O(1)
     * *****************************************************************************/
    public void ArrayCreationExpression(ArrayCreationExprNode arrayCreationExpression){
        _arrayCreationExpression=arrayCreationExpression;
    } // ArrayCreationExpression(ArrayCreationExprNode)
} // class PrimaryNode