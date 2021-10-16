/*******************************************************************************
 * StatementExprNode.java
 * 
 * A module implementing the StatementExpr node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.StatementExpression;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

public class StatementExprNode extends ASTNode {
    AssignmentNode _assignment;
    MethodInvocationNode _methodInvocation;
    ClassInstanceCreationExprNode _classInstanceCreationExpression;

    /*******************************************************************************
     * StatementExprNode constructor
     * time : O(1)
     * *****************************************************************************/
    public StatementExprNode(Node parseTreeNode) {
        super(parseTreeNode);
    } // StatementExprNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/
    @Override
    public void print(){
        if(_assignment != null){
            _assignment.print();
        } else if (_methodInvocation != null){
            _methodInvocation.print();
        } else if (_classInstanceCreationExpression != null){
            _classInstanceCreationExpression.print();
        } else throw new Error();
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
    public AssignmentNode Assignment(){
        return _assignment;
    }

    public void Assignment(AssignmentNode assignmentNode){
        _assignment=assignmentNode;
    }

    public MethodInvocationNode MethodInvocation(){
        return _methodInvocation;
    }

    public void MethodInvocation(MethodInvocationNode methodInvocationNode){
        _methodInvocation=methodInvocationNode;
    }

    public ClassInstanceCreationExprNode ClassInstanceCreationExpression(){
        return _classInstanceCreationExpression;
    }

    public void ClassInstanceCreationExpression(ClassInstanceCreationExprNode classInstanceCreationExprNode){
        _classInstanceCreationExpression=classInstanceCreationExprNode;
    }
} // class StatementExprNode