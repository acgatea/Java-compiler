/*******************************************************************************
 * NoTrailingStmtNode.java
 * 
 * A module implementing the NoTrailingStmt node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Statement;

import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;
import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.StatementExpression.StatementExprNode;

public class NoTrailingStmtNode extends ASTNode {
    BlockNode _block;
    EmptyStmtNode _emptyStatement;
    StatementExprNode _StmtExpression;
    ReturnStmtNode _ReturnStatement;

    /*******************************************************************************
     * NoTrailingStmtNode constructor
     * time : O(1)
     * *****************************************************************************/
    public NoTrailingStmtNode(Node parseTreeNode) {
        super(parseTreeNode);
    } // NoTrailingStmtNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/
    @Override
    public void print() {
        if (_block != null){
            _block.print();
        } else if (_emptyStatement != null){
            _emptyStatement.print();
        } else if (_StmtExpression != null){
            _StmtExpression.print();
        } else if (_ReturnStatement != null){
            _ReturnStatement.print();
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
    public BlockNode Block(){
        return _block;
    }

    public void Block(BlockNode block){
        _block=block;
    }

    public EmptyStmtNode EmptyStatement() {
        return _emptyStatement;
    }

    public void EmptyStatement(EmptyStmtNode emptyStmtNode){
        _emptyStatement=emptyStmtNode;
    }

    public StatementExprNode StatementExpr(){
        return _StmtExpression;
    }

    public void StatementExpr(StatementExprNode StmtExpression){
        _StmtExpression=StmtExpression;
    }

    public ReturnStmtNode ReturnStmt(){
        return _ReturnStatement;
    }

    public void ReturnStmt(ReturnStmtNode ReturnStatement){
        _ReturnStatement=ReturnStatement;
    }
} // class NoTrailingStmtNode