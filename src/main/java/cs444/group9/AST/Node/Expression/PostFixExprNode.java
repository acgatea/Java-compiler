/*******************************************************************************
 * PostFixExprNode.java
 * 
 * A module implementing the PostFixExpr node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Expression;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.Array.PrimaryNode;
import cs444.group9.AST.Node.NameNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

public class PostFixExprNode extends ExprNode {
    PrimaryNode _primary;
    NameNode _name;

    /*******************************************************************************
     * PostFixExprNode constructor
     * time : O(1)
     * *****************************************************************************/
    public PostFixExprNode(Node parseTreeNode) {
        super(parseTreeNode);
    } // PostFixExprNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/
    @Override
    public void print() {
        if(_primary != null){
            _primary.print();
        } else if (_name != null){
            _name.print();
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
    public PrimaryNode Primary() {
        return _primary;
    }

    public void Primary(PrimaryNode primary){
        _primary=primary;
    }

    public NameNode Name() {
        return _name;
    }

    public void Name(NameNode name){
        _name=name;
    }

} // class PostFixExprNode