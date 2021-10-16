/*******************************************************************************
 * BlockNode.java
 * 
 * A module implementing the Block node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Statement;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

import java.util.ArrayList;
import java.util.List;

public class BlockNode extends NoTrailingStmtNode {
    List<BlockStatementNode> _blockStatements;

    /*******************************************************************************
     * BlockNode constructor
     * time : O(1)
     * *****************************************************************************/
    public BlockNode(Node parseTreeNode) {
        super(parseTreeNode);
        _blockStatements=new ArrayList<>();
    } // BlockNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/
    @Override
    public void print(){
        for(BlockStatementNode blockstmt: _blockStatements){
            blockstmt.print();
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
    public List<BlockStatementNode> BlockStatements(){
        return _blockStatements;
    }

    public void addBlockStatement(BlockStatementNode blockStatementNode){
        _blockStatements.add(blockStatementNode);
    }
} // class BlockNode