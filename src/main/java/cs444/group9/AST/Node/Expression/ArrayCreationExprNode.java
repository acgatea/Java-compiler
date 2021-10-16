/*******************************************************************************
 * ArrayCreationExprNode.java
 * 
 * A module implementing the ArrayCreationExpr node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Expression;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.Type.BasicTypeNode;
import cs444.group9.AST.Node.Type.ClassOrInterfaceTypeNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

public class ArrayCreationExprNode extends ExprNode {
    BasicTypeNode _basictype;
    AssignmentExprNode _dimExpr;
    ClassOrInterfaceTypeNode _classOrInterfaceType;

    /*******************************************************************************
     * ArrayCreationExprNode constructor
     * time : O(1)
     * *****************************************************************************/
    public ArrayCreationExprNode(Node parseTreeNode) {
        super(parseTreeNode);
    } // ArrayCreationExprNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/
    @Override
    public void print() {
        if(_basictype != null){
            _basictype.print();
        } else if(_classOrInterfaceType != null){
            _classOrInterfaceType.print();
        } else throw new Error();
        if (_dimExpr != null){
            _dimExpr.print();
        } // if
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
    public BasicTypeNode Basictype() {
        return _basictype;
    }

    public void Basictype(BasicTypeNode basictype){
        _basictype=basictype;
    }

    public AssignmentExprNode DimExpr() {
        return _dimExpr;
    }

    public void DimExpr(AssignmentExprNode assignmentExprNode){
        _dimExpr=assignmentExprNode;
    }

    public ClassOrInterfaceTypeNode ClassOrInterfaceType() {
        return _classOrInterfaceType;
    }

    public void ClassOrInterfaceType(ClassOrInterfaceTypeNode classOrInterfaceType){
        _classOrInterfaceType=classOrInterfaceType;
    }
} // class ArrayCreationExprNode