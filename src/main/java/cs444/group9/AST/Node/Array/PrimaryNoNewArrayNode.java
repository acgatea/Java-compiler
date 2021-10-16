/*******************************************************************************
 * PrimaryNoNewArrayNode.java
 * 
 * A module implementing the PrimaryNoNewArrayNode for the AST. 
 * ****************************************************************************/

package cs444.group9.AST.Node.Array;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.Expression.AssignmentExprNode;
import cs444.group9.AST.Node.StatementExpression.ClassInstanceCreationExprNode;
import cs444.group9.AST.Node.LiteralNode;
import cs444.group9.AST.Node.StatementExpression.MethodInvocationNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

public class PrimaryNoNewArrayNode extends ASTNode {
    LiteralNode _literal;
    AssignmentExprNode _expression;
    ClassInstanceCreationExprNode _classInstanceCreationExpression;
    FieldAcccessNode _fieldAccess;
    MethodInvocationNode _methodInvocation;
    ArrayAccessNode _arrayAccess;
    boolean _this;

    /*******************************************************************************
     * PrimaryNoNewArrayNode constructor
     * time : O(1)
     * *****************************************************************************/
    public PrimaryNoNewArrayNode(Node parseTreeNode) {
        super(parseTreeNode);
        _this = false;
    } // PrimaryNoNewArrayNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/ 
    @Override
     public void print() {
            if(_literal != null){
                _literal.print();
            } else if(_expression != null){
                _expression.print();
            } else if(_classInstanceCreationExpression != null){
                _classInstanceCreationExpression.print();
            } else if(_fieldAccess != null){
                _fieldAccess.print();
            }else if(_methodInvocation != null){
                _methodInvocation.print();
            } else if(_arrayAccess != null){
                _arrayAccess.print();
            } else if(! _this) throw new Error();
    } // print()

    /*******************************************************************************
     * Visitor accept
     * time : O(1)
     * *****************************************************************************/
    @Override
    public void accept(iVisitor visitor) throws iVisitor.ASTException {
        visitor.visit(this);
    } // accept()

    /*******************************************************************************
     * Accessor methods
     * time : O(1)
     * *****************************************************************************/
    public LiteralNode Literal() {
        return _literal;
    }

    public void Literal(LiteralNode literal){
        _literal=literal;
    }

    public AssignmentExprNode Expression() {
        return _expression;
    }

    public void Expression(AssignmentExprNode expression){
        _expression=expression;
    }

    public ClassInstanceCreationExprNode ClassInstanceCreationExpression() {
        return _classInstanceCreationExpression;
    }

    public void ClassInstanceCreationExpression(ClassInstanceCreationExprNode classInstanceCreationExpression){
        _classInstanceCreationExpression=classInstanceCreationExpression;
    }

    public FieldAcccessNode FieldAccess() {
        return _fieldAccess;
    }

    public MethodInvocationNode MethodInvocation() {
        return _methodInvocation;
    }

    public void MethodInvocation(MethodInvocationNode methodInvocation){
        _methodInvocation=methodInvocation;
    }

    public void FieldAccess(FieldAcccessNode fieldAccess) {
        _fieldAccess = fieldAccess;
    }

    public ArrayAccessNode ArrayAccess() {
        return _arrayAccess;
    }

    public void ArrayAccess(ArrayAccessNode arrayAccess){
        _arrayAccess=arrayAccess;
    }

    public boolean This() {
        return _this;
    }

    public void This(boolean flag) {
        _this = flag;
    }

} // class PrimaryNoNewArrayNode