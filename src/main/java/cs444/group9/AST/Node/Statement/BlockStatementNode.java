/*******************************************************************************
 * BlockStatementNode.java
 * 
 * A module implementing the BlockStatement node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Statement;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.Declaration.LocalVariableDeclNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

public class BlockStatementNode extends ASTNode {
    StatementNode _statement;
    LocalVariableDeclNode _localVariableDeclaration;

    /*******************************************************************************
     * BlockStatementNode constructor
     * time : O(1)
     * *****************************************************************************/
    public BlockStatementNode(Node parseTreeNode) {
        super(parseTreeNode);
    } // BlockStatementNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/
    @Override
    public void print() {
        if (_statement != null){
            _statement.print();
        } else if(_localVariableDeclaration != null){
            _localVariableDeclaration.print();
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
    public StatementNode Statement() {
        return _statement;
    }

    public void Statement(StatementNode statement){
        _statement=statement;
    }

    public LocalVariableDeclNode LocalVariableDeclaration(){
        return _localVariableDeclaration;
    }

    public void LocalVariableDeclaration(LocalVariableDeclNode localVariableDeclaration){
        _localVariableDeclaration=localVariableDeclaration;
    }
} // class BlockStatementNode