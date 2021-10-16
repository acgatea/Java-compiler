/*******************************************************************************
 * ClassInstanceCreationExprNode.java
 * 
 * A module implementing the ClassInstanceCreationExpr node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.StatementExpression;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.Expression.AssignmentExprNode;
import cs444.group9.AST.Node.Type.ClassOrInterfaceTypeNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

import java.util.ArrayList;
import java.util.List;

public class ClassInstanceCreationExprNode extends ASTNode {
    ClassOrInterfaceTypeNode _classType;
    List<AssignmentExprNode> _argumentList;
    ASTNode constrDeclaration;

    /*******************************************************************************
     * ClassInstanceCreationExprNode constructor
     * time : O(1)
     * *****************************************************************************/
    public ClassInstanceCreationExprNode(Node parseTreeNode) {
        super(parseTreeNode);
        _argumentList = new ArrayList<AssignmentExprNode>();
    } // ClassInstanceCreationExprNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/
    @Override
    public void print() {
        if (_classType != null){
            _classType.print();
        } else throw new Error();

        for(AssignmentExprNode arg: _argumentList){
            arg.print();
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
    public ClassOrInterfaceTypeNode ClassType(){
        return _classType;
    }

    public void ClassType(ClassOrInterfaceTypeNode classType){
        _classType=classType;
    }

    public List<AssignmentExprNode> ArgumentList(){
        return _argumentList;
    }

    public void addExpression(AssignmentExprNode assignmentExprNode){
        _argumentList.add(assignmentExprNode);
    }

    public void ConstructDecl(ASTNode n){ constrDeclaration = n;}

    public ASTNode ConstructDecl(){return constrDeclaration;}

} // class ClassInstanceCretionExprNode