/*******************************************************************************
 * MethodDeclarationNode.java
 * 
 * A module implementing the MethodDeclaration node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Body;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

public class MethodDeclarationNode extends ASTNode {
    MethodHeaderNode _methodHeader;
    MethodBodyNode _methodBody;

    /*******************************************************************************
     * MethodDeclarationNode constructor
     * time : O(1)
     * *****************************************************************************/
    public MethodDeclarationNode(Node node) {
        super(node);
    } // MethodDeclarationNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/    
    @Override
    public void print() {
        _methodHeader.print();
        _methodBody.print();
    } // print()

    /*******************************************************************************
     * Visitor acceptor
     * time : O(1)
     * *****************************************************************************/ 
    @Override
    public void accept(iVisitor visitor)throws iVisitor.ASTException {
        visitor.visit(this);
    } // accept()

    /*******************************************************************************
     * Accessor methods
     * *****************************************************************************/ 
    public MethodHeaderNode MethodHeader(){
        return _methodHeader;
    }

    public void MethodHeader(MethodHeaderNode node){
        _methodHeader=node;
    }

    public MethodBodyNode MethodBody(){
        return _methodBody;
    }

    public void MethodBody(MethodBodyNode node){
        _methodBody=node;
    }
} // class MethodDeclarationNode
