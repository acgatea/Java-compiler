/*******************************************************************************
 * ArrayAccessNode.java
 * 
 * A module implementing the Array Access node for the AST. 
 * ****************************************************************************/

package cs444.group9.AST.Node.Array;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.Expression.AssignmentExprNode;
import cs444.group9.AST.Node.NameNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

public class ArrayAccessNode extends ASTNode{
    AssignmentExprNode _expression;
    NameNode _name;
    PrimaryNoNewArrayNode _primaryNoNewArray;

    /*******************************************************************************
     * ArrayAccessNode constructor
     * time : O(1)
     * *****************************************************************************/
    public ArrayAccessNode(Node parseTreeNode) {
        super(parseTreeNode);
    } // ArrayAccessNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/ 
    @Override
    public void print() {
        if(_name != null){
            _name.print();
        } else if(_primaryNoNewArray != null){
            _primaryNoNewArray.print();
        } else throw new Error();
        _expression.print();
    } // print()

    /*******************************************************************************
     * Visitor accept
     * time : O(1)
     * *****************************************************************************/
    @Override
    public void accept(iVisitor visitor) throws iVisitor.ASTException {
        visitor.visit(this);
    } // accept()

   /*******************************************************************************
     * Getter for name
     * time : O(1)
     * *****************************************************************************/
    public NameNode Name() {
        return _name;
    } // Name()

   /*******************************************************************************
     * Getter for expression
     * time : O(1)
     * *****************************************************************************/
    public AssignmentExprNode Expression() {
        return _expression;
    } // Expression()

   /*******************************************************************************
     * Getter for _primaryNoNewArray
     * time : O(1)
     * *****************************************************************************/
    public PrimaryNoNewArrayNode PrimaryNoNewArray() {
        return _primaryNoNewArray;
    } // PrimaryNoNewArray()

   /*******************************************************************************
     * Setter for expression
     * time : O(1)
     * *****************************************************************************/
    public void Expression(AssignmentExprNode assignmentExprNode){
        _expression=assignmentExprNode;
    } // Expression(assignmentExprNode)

   /*******************************************************************************
     * Setter for name
     * time : O(1)
     * *****************************************************************************/
    public void Name(NameNode name){
        _name=name;
    } // Name(name)

   /*******************************************************************************
     * Setter for _primaryNoNewArray
     * time : O(1)
     * *****************************************************************************/
    public void PrimaryNoNewArray(PrimaryNoNewArrayNode primaryNoNewArray){
        _primaryNoNewArray=primaryNoNewArray;
    } // PrimaryNoNewArray(PrimaryNoNewArrayNode)
} // class ArrayAccessNode
