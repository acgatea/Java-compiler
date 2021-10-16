/*******************************************************************************
 * ConstructorDeclNode.java
 * 
 * A module implementing the ConstructorDecl node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Declaration;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.Body.FormalParameterNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

import java.util.ArrayList;
import java.util.List;

public class ConstructorDeclNode extends ASTNode {
    String _identifier;
    List<FormalParameterNode> _formalParameterList;

    /*******************************************************************************
     * ConstructorDeclNode constructor
     * time : O(1)
     * *****************************************************************************/
    public ConstructorDeclNode(Node parseTreeNode) {
        super(parseTreeNode);
        _formalParameterList=new ArrayList<>();
    } // ConstructorDeclNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/
    @Override
    public void print() {
        System.out.println("Constructor declarator: " + _identifier);
        for(FormalParameterNode formalParameter: _formalParameterList){
            formalParameter.print();
        } // for
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
    public String Identifier(){
        return _identifier;
    }

    public void Identifier(String identifier){
        _identifier=identifier;
    }

    public List<FormalParameterNode> FormalParameterList(){
        return _formalParameterList;
    }

    public void addFormalParameterNode(FormalParameterNode node){
        _formalParameterList.add(node);
    }
} // class ConstructorDeclNode