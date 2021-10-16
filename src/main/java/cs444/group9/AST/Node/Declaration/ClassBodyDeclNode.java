/*******************************************************************************
 * ClassBodyDeclNode.java
 * 
 * A module implementing the ClassBodyDecl node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Declaration;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

public class ClassBodyDeclNode extends ASTNode {

    ClassMemberDeclNode _classMemberDeclaration;
    ConstructorDeclarationNode _constructorDeclaration;

    /*******************************************************************************
     * ClassBodyDeclNode constructor
     * time : O(1)
     * *****************************************************************************/
    public ClassBodyDeclNode(Node parseTreeNode) {
        super(parseTreeNode);
    } // ClassBodyDeclNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/
    @Override
    public void print() {
        if(_classMemberDeclaration != null){
            _classMemberDeclaration.print();
        } else if(_constructorDeclaration != null){
            _constructorDeclaration.print();
        } else throw new Error();
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
    public ClassMemberDeclNode ClassMemberDeclaration(){
        return _classMemberDeclaration;
    }

    public ConstructorDeclarationNode ConstructorDeclaration(){
        return _constructorDeclaration;
    }

    public void ClassMemberDeclaration(ClassMemberDeclNode node){
        _classMemberDeclaration=node;
    }

    public void ConstructorDeclaration(ConstructorDeclarationNode node){
        _constructorDeclaration=node;
    }
} // class ClassBodyDeclNode