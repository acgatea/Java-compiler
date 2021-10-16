/*******************************************************************************
 * PackageDeclNode.java
 * 
 * A module implementing the PackageDecl node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Declaration;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.NameNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

public class PackageDeclNode extends ASTNode{
    NameNode _name;
    String _className;

    /*******************************************************************************
     * PackageDeclNode constructor
     * time : O(1)
     * *****************************************************************************/
    public PackageDeclNode(Node parseTreeNode) {
        super(parseTreeNode);
    } // PackageDeclNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/
    @Override
    public void print() {
        System.out.print("Package name: ");
        _name.print();
        System.out.print("\n");
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

    public String ClassName(){
        return _className;
    }

    public void ClassName(String name){
        _className = name;
    }

    public void Name(NameNode node){
        _name=node;
    }
} // class PackageDeclNode