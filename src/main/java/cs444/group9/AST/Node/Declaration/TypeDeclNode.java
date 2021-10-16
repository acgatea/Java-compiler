/*******************************************************************************
 * TypeDeclNode.java
 * 
 * A module implementing the TypeDecl node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Declaration;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

public class TypeDeclNode extends ASTNode {

    ClassDeclNode _classDeclaration;
    InterfaceDeclNode _interfaceDeclaration;

    /*******************************************************************************
     * TypeDeclNode constructor
     * time : O(1)
     * *****************************************************************************/
    public TypeDeclNode(Node parseTreeNode) {
        super(parseTreeNode);
    } // TypeDeclNode

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/
    @Override
    public void print() {
        if(_classDeclaration != null) _classDeclaration.print();
        else if(_interfaceDeclaration != null) _interfaceDeclaration.print();
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
    public ClassDeclNode ClassDeclaration(){
        return _classDeclaration;
    }

    public void ClassDeclaration(ClassDeclNode node){
        _classDeclaration=node;
    }

    public InterfaceDeclNode InterfaceDeclaration(){
        return _interfaceDeclaration;
    }

    public void InterfaceDeclaration(InterfaceDeclNode node){
        _interfaceDeclaration=node;
    }
} // class TypeDeclNode