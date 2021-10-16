/*******************************************************************************
 * ClassOrInterfaceTypeNode.java
 * 
 * A module implementing the ClassOrInterfaceType node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Type;

import cs444.group9.AST.Node.Environments.Environment;
import cs444.group9.AST.Node.NameNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;
import cs444.group9.AST.Node.ASTNode;

public class ClassOrInterfaceTypeNode extends ASTNode {
    NameNode _name;

    /*******************************************************************************
     * ClassOrInterfaceTypeNode constructor
     * time : O(1)
     * *****************************************************************************/
    public ClassOrInterfaceTypeNode(Node parseTreeNode) {
        super(parseTreeNode);
    } // ClassOrInterfaceTypeNode()

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
     * time : O(|AST|)
     * *****************************************************************************/
    @Override
    public void print() {
        System.out.println("  Class/Interface Name:");
        _name.print();
    } // print()

    /*******************************************************************************
     * Accessor methods
     * *****************************************************************************/ 
    public NameNode Name() {
        return _name;
    }

    public void Name(NameNode name) {
        _name = name;
    }

    public boolean isSameClassOrInterfType(ClassOrInterfaceTypeNode t) {
        return _name.isSameName(t.Name());
    }

    public Environment getDeclEnvironment() {
        return Name().getLinkedDeclaration().getEnv();
    }

    public String toString(){
        return _name.toString();
    }
} // class ClassOrInterfaceTypeNode
