/*******************************************************************************
 * AssignmentNode.java
 * 
 * A module implementing the Assignment node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.StatementExpression;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.Array.ArrayAccessNode;
import cs444.group9.AST.Node.Array.FieldAcccessNode;
import cs444.group9.AST.Node.Expression.AssignmentExprNode;
import cs444.group9.AST.Node.NameNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

public class AssignmentNode extends ASTNode {
    NameNode _name;
    FieldAcccessNode _fieldAccess;
    ArrayAccessNode _arrayAccess;
    AssignmentExprNode _assignmentExpression;

    /*******************************************************************************
     * AssignmentNode constructor
     * time : O(1)
     * *****************************************************************************/
    public AssignmentNode(Node parseTreeNode) {
        super(parseTreeNode);
    } // AssignmentNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/
    @Override
    public void print() {
        if (_name != null){
            _name.print();
        } else if (_fieldAccess != null){
            _fieldAccess.print();
        } else if (_arrayAccess != null){
            _arrayAccess.print();
        } else throw new Error();
        _assignmentExpression.print();
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
    public NameNode Name() {
        return _name;
    }

    public ArrayAccessNode ArrayAccess() {
        return _arrayAccess;
    }

    public FieldAcccessNode FieldAccess() {
        return _fieldAccess;
    }

    public void FieldAccess(FieldAcccessNode fieldAccess) {
        _fieldAccess = fieldAccess;
    }

    public void ArrayAccess(ArrayAccessNode arrayAccess) {
        _arrayAccess = arrayAccess;
    }

    public void Name(NameNode name) {
        _name = name;
    }

    public void AssignmentExpression(AssignmentExprNode assignmentExpression) {
        _assignmentExpression = assignmentExpression;
    }

    public AssignmentExprNode AssignmentExpression() {
        return _assignmentExpression;
    }
} // class AssignmentNode