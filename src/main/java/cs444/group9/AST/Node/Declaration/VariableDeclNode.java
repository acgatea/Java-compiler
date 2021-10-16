/*******************************************************************************
 * VariableDeclNode.java
 * 
 * A module implementing the VariableDecl node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Declaration;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.Expression.AssignmentExprNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

public class VariableDeclNode extends ASTNode {
    String _variableDeclarator;
    AssignmentExprNode _variableInitializer;

    /*******************************************************************************
     * VariableDeclNode constructor
     * time : O(1)
     * *****************************************************************************/
    public VariableDeclNode(Node parseTreeNode) {
        super(parseTreeNode);
    } // VariableDeclNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/
    @Override
    public void print() {
        System.out.println("VariableDeclarator: " + _variableDeclarator);
        if(_variableInitializer != null) {
            _variableInitializer.print();
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
    public String VariableDeclarator(){
        return _variableDeclarator;
    }

    public void VariableDeclarator(String str){
        _variableDeclarator=str;
    }

    public AssignmentExprNode VariableInitializer(){
        return _variableInitializer;
    }

    public void VariableInitializer(AssignmentExprNode node){
        _variableInitializer=node;
    }
} // class VariableDeclNode