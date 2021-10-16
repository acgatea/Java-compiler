/*******************************************************************************
 * LocalVariableDeclNode.java
 * 
 * A module implementing the LocalVariableDecl node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Declaration;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.Expression.AssignmentExprNode;
import cs444.group9.AST.Node.Type.TypeNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

public class LocalVariableDeclNode extends ASTNode {
    TypeNode _type;
    String _variableDeclaratorId;
    AssignmentExprNode _variableInitializer;

    int _offset;

    /*******************************************************************************
     * LocalVariableDeclNode constructor
     * time : O(1)
     * *****************************************************************************/
    public LocalVariableDeclNode(Node parseTreeNode) {
        super(parseTreeNode);
        _offset = 0;
    } // LocalVariableDeclNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/
    @Override
    public void print() {
        _type.print();
        System.out.println("VariableDeclaratorid: " + _variableDeclaratorId);
        _variableInitializer.print();
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
    public TypeNode Type(){
        return _type;
    }

    public void Type(TypeNode type){
        _type=type;
    }

    public String VariableDeclaratorId(){
        return _variableDeclaratorId;
    }

    public void VariableDeclaratorId(String variableDeclaratorId){
        _variableDeclaratorId=variableDeclaratorId;
    }

    public AssignmentExprNode VariableInitializer(){
        return _variableInitializer;
    }

    public void VariableInitializer(AssignmentExprNode variableInitializer){
        _variableInitializer=variableInitializer;
    }

    public void setOffset(int offset){_offset = offset;}
    public int getOffset(){return _offset ;}

} // class LocalVariableDeclNode