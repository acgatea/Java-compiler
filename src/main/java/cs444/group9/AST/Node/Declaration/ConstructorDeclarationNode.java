/*******************************************************************************
 * ConstructorDeclarationNode.java
 * 
 * A module implementing the ConstructorDeclaration node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Declaration;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.Body.ConstructorBodyNode;
import cs444.group9.AST.Node.Declaration.ConstructorDeclNode;
import cs444.group9.AST.Node.Environments.MethodData;
import cs444.group9.AST.Node.ModifierNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

import java.util.ArrayList;
import java.util.List;

public class ConstructorDeclarationNode extends ASTNode {
    List<ModifierNode> _modifiers;
    ConstructorDeclNode _constructorDeclarator;
    ConstructorBodyNode _constructorbody;
    MethodData _methodData;

    String _label;
    int _offset;

    /*******************************************************************************
     * ConstructorDeclarationNode constructor
     * time : O(1)
     * *****************************************************************************/
    public ConstructorDeclarationNode(Node parseTreeNode) {
        super(parseTreeNode);
        _modifiers=new ArrayList<>();
    } // ConstructorDeclarationNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/
    @Override
    public void print() {
        for(ModifierNode modifier: _modifiers){
            modifier.print();
        } // for
        _constructorDeclarator.print();
        _constructorbody.print();
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

    public List<ModifierNode> Modifiers(){
        return _modifiers;
    }

    public void addModifier(ModifierNode node){
        _modifiers.add(node);
    }

    public ConstructorBodyNode ConstructorBody(){
        return _constructorbody;
    }

    public void ConstructorBody(ConstructorBodyNode constructorbody){
        _constructorbody=constructorbody;
    }

    public ConstructorDeclNode ConstructorDeclarator(){
        return _constructorDeclarator;
    }

    public void ConstructorDeclarator(ConstructorDeclNode constructorDeclarator){
        _constructorDeclarator=constructorDeclarator;
    }

    public MethodData getMethodData(){ return _methodData;}
    public void setMethodData(MethodData m){_methodData = m;}

    public void setLabel(String label){_label = label;}
    public String getLabel(){return _label;}
    public void setOffset(int offset){_offset = offset;}
    public int getOffset(){return _offset ;}
} // class ConstructorDeclarationNode