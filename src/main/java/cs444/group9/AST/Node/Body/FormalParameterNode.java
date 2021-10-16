/*******************************************************************************
 * FormalParameterNode.java
 * 
 * A module implementing the FormalParameter node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Body;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.Type.TypeNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

public class FormalParameterNode extends ASTNode {
    TypeNode _type;
    String _variableDeclaratorId;

    int _offset;

    /*******************************************************************************
     * FormalParameterNode constructor
     * time : O(1)
     * *****************************************************************************/
    public FormalParameterNode(Node parseTreeNode) {
        super(parseTreeNode);
    } // FormalParameterNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/    
    @Override
    public void print() {
        _type.print();
        System.out.println("Variable declarator ID: " + _variableDeclaratorId);
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

    public void VariableDeclaratorId(String str){
        _variableDeclaratorId=str;
    }

    public void setOffset(int offset){_offset = offset;}
    public int getOffset(){return _offset ;}
} // class FormalParameterNode