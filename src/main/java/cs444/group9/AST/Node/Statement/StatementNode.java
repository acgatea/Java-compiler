/*******************************************************************************
 * StatementNode.java
 * 
 * A module implementing the Statement node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Statement;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;


public class StatementNode extends ASTNode {
    NoTrailingStmtNode _notrailingstmt;
    IfStmtNode _ifstmt;
    WhileStmtNode _whilestmt;
    ForStmtNode _forstmt;

    /*******************************************************************************
     * StatementNode constructor
     * time : O(1)
     * *****************************************************************************/
    public StatementNode(Node parseTreeNode) {
        super(parseTreeNode);
    } // StatementNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/
    @Override
    public void print() {
        if(_notrailingstmt != null) _notrailingstmt.print();
        else if(_ifstmt != null) _ifstmt.print();
        else if(_whilestmt != null) _whilestmt.print();
        else if(_forstmt != null) _forstmt.print();
        else throw new Error();
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
    public NoTrailingStmtNode NoTrailingStmt(){
        return _notrailingstmt;
    }
    public IfStmtNode IfStmt(){
        return _ifstmt;
    }
    public WhileStmtNode WhileStmt(){
        return _whilestmt;
    }
    public ForStmtNode ForStmt(){
        return _forstmt;
    }

    public void NoTrailingStmt(NoTrailingStmtNode notrailingstmt){ _notrailingstmt = notrailingstmt; }
    public void IfStmt(IfStmtNode ifstmt){ _ifstmt = ifstmt; }
    public void WhileStmt(WhileStmtNode whilestmt){ _whilestmt = whilestmt; }
    public void ForStmt(ForStmtNode forstmt){ _forstmt = forstmt; }

} // class StatementNode

