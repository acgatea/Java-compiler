/*******************************************************************************
 * AbstractMethodDeclNode.java
 * 
 * A module implementing the AbstractMethodDecl node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Declaration;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.Body.MethodHeaderNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

public class AbstractMethodDeclNode extends ASTNode {
    MethodHeaderNode _methodHeader;

    /*******************************************************************************
     * AbstractMethodDeclNode constructor
     * time : O(1)
     * *****************************************************************************/
    public AbstractMethodDeclNode(Node parseTreeNode) {
        super(parseTreeNode);
    } // AbstractMethodDeclNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/
    @Override
    public void print() {
        _methodHeader.print();
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
    public MethodHeaderNode MethodHeader(){
        return _methodHeader;
    }

    public void MethodHeader(MethodHeaderNode methodHeader){
        _methodHeader=methodHeader;
    }
} // class AbstractMethodDeclNode
