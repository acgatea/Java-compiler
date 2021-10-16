/*******************************************************************************
 * ConstructorBodyNode.java
 * 
 * A module implementing the ConstructorBody node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Body;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.Statement.BlockStatementNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

import java.util.ArrayList;
import java.util.List;

public class ConstructorBodyNode extends ASTNode {
    List<BlockStatementNode> _blockstatements;

    /*******************************************************************************
     * ConstructorBodyNode constructor
     * time : O(1)
     * *****************************************************************************/
    public ConstructorBodyNode(Node parseTreeNode) {
        super(parseTreeNode);
        _blockstatements=new ArrayList<>();
    } // ConstructorBodyNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/    
    @Override
    public void print() {
        for(BlockStatementNode stmt: _blockstatements){
            stmt.print();
        } // for
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
     * Getter for block statements
     * time : O(1)
     * *****************************************************************************/   
    public List<BlockStatementNode> BlockStatements(){
        return _blockstatements;
    } // BlockStatements()

    /*******************************************************************************
     * Add block statement
     * time : O(1)
     * *****************************************************************************/   
    public void addBlockStatement(BlockStatementNode node){
        _blockstatements.add(node);
    } // addBlockStatement()
} // class ConstructorBodyNode