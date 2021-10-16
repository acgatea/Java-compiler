/*******************************************************************************
 * ClassMemberDeclNode.java
 * 
 * A module implementing the ClassMemberDecl node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Declaration;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.Body.MethodDeclarationNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

public class ClassMemberDeclNode extends ASTNode {

    FieldDeclNode _fieldDeclaration;
    MethodDeclarationNode _methodDeclaration;

    /*******************************************************************************
     * ClassMemberDeclNode constructor
     * time : O(1)
     * *****************************************************************************/
    public ClassMemberDeclNode(Node parseTreeNode) {
        super(parseTreeNode);
    } // ClassMemberDeclNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/
    @Override
    public void print() {
        if(_fieldDeclaration != null){
            _fieldDeclaration.print();
        } else if(_methodDeclaration != null){
            _methodDeclaration.print();
        } // else if
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
    public FieldDeclNode FieldDeclaration(){
        return _fieldDeclaration;
    }

    public MethodDeclarationNode MethodDeclaration(){
        return _methodDeclaration;
    }

    public void FieldDeclaration(FieldDeclNode fieldDeclaration) {
        _fieldDeclaration = fieldDeclaration;
    }

    public void MethodDeclaration(MethodDeclarationNode methodDeclaration) {
        _methodDeclaration = methodDeclaration;
    }
} // class ClassMemberDeclNode