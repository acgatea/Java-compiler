/*******************************************************************************
 * MethodBodyNode.java
 * 
 * A module implementing the MethodBody node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Body;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.Environments.Environment;
import cs444.group9.AST.Node.Environments.EnvironmentPair;
import cs444.group9.AST.Node.Statement.BlockNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

public class MethodBodyNode extends ASTNode {
    BlockNode _block;

    /*******************************************************************************
     * MethodBodyNode constructor
     * time : O(1)
     * *****************************************************************************/
    public MethodBodyNode(Node parseTreeNode) {
        super(parseTreeNode);
    } // MethodBodyNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/    
    @Override
    public void print() {
        if(_block != null) {
            _block.print();
        } // if
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
    public BlockNode Block() {
        return _block;
    }

    public void Block(BlockNode block) {
        _block = block;
    }
} // class MethodBodyNode