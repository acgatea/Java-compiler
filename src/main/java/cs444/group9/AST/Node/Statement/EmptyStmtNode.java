/*******************************************************************************
 * EmptyStmtNode.java
 * 
 * A module implementing the EmptyStmt node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Statement;

import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

public class EmptyStmtNode extends NoTrailingStmtNode {
    /*******************************************************************************
     * EmptyStmtNode constructor
     * time : O(1)
     * *****************************************************************************/
    public EmptyStmtNode(Node parseTreeNode) {
        super(parseTreeNode);
    } // EmptyStmtNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(1)
     * *****************************************************************************/
    @Override
    public void print(){}

    /*******************************************************************************
     * Visitor acceptor
     * time : O(1)
     * *****************************************************************************/ 
    @Override
    public void accept(iVisitor visitor) throws iVisitor.ASTException{
        visitor.visit(this);
    } // accept()
} // class EmptyStmtNode