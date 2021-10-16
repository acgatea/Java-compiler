/*******************************************************************************
 * ArrayTypeNode.java
 * 
 * A module implementing the ArrayType node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Type;

import cs444.group9.AST.Node.NameNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;
import cs444.group9.AST.Node.ASTNode;


public class ArrayTypeNode extends ASTNode {
    BasicTypeNode _basictype;
    NameNode _name;

    /*******************************************************************************
     * ArrayTypeNode constructor
     * time : O(1)
     * *****************************************************************************/
    public ArrayTypeNode(Node parseTreeNode) {
        super(parseTreeNode);
    } // ArrayTypeNode()

    /*******************************************************************************
     * Visitor acceptor
     * time : O(1)
     * *****************************************************************************/ 
    @Override
    public void accept(iVisitor visitor) throws iVisitor.ASTException {
        visitor.visit(this);
    } // accept()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(1)
     * *****************************************************************************/
    @Override
    public void print() {
        System.out.println(" Print ArrayType");

        if (_basictype != null){
            _basictype.print();
        } else if (_name != null){
            _name.print();
        } else throw new Error();
    } // print()

    /*******************************************************************************    
     * Compares two array types
     * time : O(1)
     * *****************************************************************************/
    public boolean isSameArrayType(ArrayTypeNode t) {
        if(_name != null && t.Name() != null){
            return _name.isSameName(t.Name());
        } else if(_basictype != null && t.BasicType() != null){
            return _basictype.isSameBasicType(t.BasicType());
        } else return false;
    } // isSameArrayType()

    /*******************************************************************************
     * Converts array type to string
     * time: O(1)
     * *****************************************************************************/
    public String toString(){
        if(_basictype != null) return _basictype.toString() + "$$";
        else return _name.toString() + "$$";
    } // toString()

    /*******************************************************************************
     * Accessor methods
     * *****************************************************************************/ 
    public NameNode Name(){
        return _name;
    }

    public void Name(NameNode name) {
        _name = name;
    }

    public BasicTypeNode BasicType(){
        return _basictype;
    }

    public void BasicType(BasicTypeNode basictype) {
        _basictype = basictype;
    }
} // class ArrayTypeNode
