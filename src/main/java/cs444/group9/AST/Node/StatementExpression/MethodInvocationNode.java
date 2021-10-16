/*******************************************************************************
 * MethodInvocationNode.java
 * 
 * A module implementing the MethodInvocation node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.StatementExpression;

import cs444.group9.AST.Node.Array.PrimaryNode;
import cs444.group9.AST.Node.Expression.AssignmentExprNode;
import cs444.group9.AST.Node.NameNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;
import cs444.group9.AST.Node.ASTNode;


import java.util.ArrayList;
import java.util.List;

public class MethodInvocationNode extends ASTNode {
    NameNode _name;
    List<AssignmentExprNode> _argumentList;
    PrimaryNode _primary;
    String _identifier;
    ASTNode decl; // ptr to declaration (for Primary.Identifier)
    NameNode _fieldName;
    NameNode _typeName;

    /*******************************************************************************
     * MethodInvocationNode constructor
     * time : O(1)
     * *****************************************************************************/
    public MethodInvocationNode(Node parseTreeNode) {
        super(parseTreeNode);
        _argumentList=new ArrayList<AssignmentExprNode>();
        _fieldName = null;
    } // MethodInvocationNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/
    @Override
    public void print() {
        if(_name != null){
            _name.print();
        } else if(_primary != null && _identifier != null){
            _primary.print();
            System.out.println("MethodInvoked Identifier: " + _identifier);
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
    public NameNode Name() {
        return _name;
    }

    public void Name(NameNode name){
        _name=name;
    }

    public List<AssignmentExprNode> ArgumentList(){
        return _argumentList;
    }

    public void addArgument(AssignmentExprNode assignmentExprNode){
        _argumentList.add(assignmentExprNode);
    }

    public PrimaryNode Primary() {
        return _primary;
    }

    public void Primary(PrimaryNode primary){
        _primary=primary;
    }

    public String Identifier(){
        return _identifier;
    }

    public void Identifier(String identifier){
        _identifier=identifier;
    }

    public void setDecl(ASTNode n) {decl = n;}
    public ASTNode getMethodDecl() {return decl;}

    public void FieldName(NameNode n) {_fieldName = n;}
    public NameNode FieldName() {return _fieldName;}
    public void TypeName(NameNode n) {_typeName = n;}
    public NameNode TypeName() {return _typeName;}
} // class MethodInvocationNode