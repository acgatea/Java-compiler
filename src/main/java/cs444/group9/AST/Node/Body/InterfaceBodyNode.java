/*******************************************************************************
 * InterfaceBodyNode.java
 * 
 * A module implementing the InterfaceBody node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Body;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.Declaration.InterfaceMemberDeclNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

import java.util.ArrayList;
import java.util.List;

public class InterfaceBodyNode extends ASTNode {
    List<InterfaceMemberDeclNode> _interfaceMemberDeclarations;

    /*******************************************************************************
     * InterfaceBodyNode constructor
     * time : O(1)
     * *****************************************************************************/
    public InterfaceBodyNode(Node parseTreeNode) {
        super(parseTreeNode);
        _interfaceMemberDeclarations=new ArrayList<>();
    } // InterfaceBodyNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/ 
    @Override
    public void print() {
        for(InterfaceMemberDeclNode node: _interfaceMemberDeclarations){
            node.print();
        } // for 
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
    public List<InterfaceMemberDeclNode> InterfaceMemberDeclarations() {
        return _interfaceMemberDeclarations;
    }

    public void addInterfaceMemberDecl(InterfaceMemberDeclNode node){
        _interfaceMemberDeclarations.add(node);
    }
} // class InterfaceBodyNode
