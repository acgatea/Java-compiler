/*******************************************************************************
 * ImportDeclNode.java
 * 
 * A module implementing the ImportDecl node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Declaration;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.NameNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

public class ImportDeclNode extends ASTNode {
    boolean _isSingleType;
    NameNode _name;
    String _className;

    /*******************************************************************************
     * ImportDeclNode constructor
     * time : O(1)
     * *****************************************************************************/
    public ImportDeclNode(Node parseTreeNode) {
        super(parseTreeNode);
    } // ImportDeclNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/
    @Override
    public void print() {
        if (_isSingleType){
            System.out.print("Single Type Import declaration: ");
            _name.print();
            System.out.print(".*\n");
        } else {
            System.out.print("On Demand Type Import declaration: ");
            _name.print();
            System.out.print("\n");
        } // else
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
    public NameNode Name(){
        return _name;
    }

    public boolean isSingleType(){
        return _isSingleType;
    }

    public void Name(NameNode node){
        _name=node;
    }

    public void isSingleType(boolean flag){
        _isSingleType=flag;
    }

    public void ClassName(String name){
        _className = name;
    }
} // class ImportDeclNode