/*******************************************************************************
 * IfStmtNode.java
 * 
 * A module implementing the IfStmt node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Statement;

import cs444.group9.AST.Node.Expression.AssignmentExprNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;
import cs444.group9.AST.Node.ASTNode;


import java.util.ArrayList;
import java.util.List;

public class IfStmtNode extends ASTNode {
    AssignmentExprNode _expression;
    List<StatementNode> _statements;

    /*******************************************************************************
     * IfStmtNode constructor
     * time : O(1)
     * *****************************************************************************/
    public IfStmtNode(Node parseTreeNode) {
        super(parseTreeNode);
        _statements=new ArrayList<>();
    } // IfStmtNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/
    @Override
    public void print() {
        _expression.print();
        for(StatementNode node: _statements){
            node.print();
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
    public AssignmentExprNode Expression(){
        return _expression;
    }

    public void Expression(AssignmentExprNode assignmentNode){
        _expression=assignmentNode;
    }

    public List<StatementNode> Statements(){
        return _statements;
    }

    public void addStatement(StatementNode statementNode){
        _statements.add(statementNode);
    }
} // class IfStmtNode