/*******************************************************************************
 * InterfaceDeclNode.java
 * 
 * A module implementing the InterfaceDecl node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Declaration;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.Body.InterfaceBodyNode;
import cs444.group9.AST.Node.ModifierNode;
import cs444.group9.AST.Node.NameNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

import java.util.ArrayList;
import java.util.List;

public class InterfaceDeclNode extends ASTNode {
    List<ModifierNode> _modifiers;
    String _identifier;
    List<NameNode> _extendsInterfaces;
    InterfaceBodyNode _interfaceBody;

    String _label;
    String _vtable;
    int _offset; // subtype offset

    /*******************************************************************************
     * InterfaceDeclNode constructor
     * time : O(1)
     * *****************************************************************************/
    public InterfaceDeclNode(Node node) {
        super(node);
        _modifiers=new ArrayList<ModifierNode>();
        _extendsInterfaces=new ArrayList<NameNode>();
    } // InterfaceDeclNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/
    @Override
    public void print() {
        for(ModifierNode modifier: _modifiers){
            modifier.print();
        } // for
        System.out.println("Identifier: " + _identifier + " Extends: ");
        for(NameNode extendsInterface: _extendsInterfaces){
            extendsInterface.print();
        } // for
        _interfaceBody.print();
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

    public String Identifier(){
        return _identifier;
    }

    public void Identifier(String identifier){
        _identifier=identifier;
    }

    public List<NameNode> ExtendsInterfaces(){
        return _extendsInterfaces;
    }

    public void addExtendsInterface(NameNode node){
        _extendsInterfaces.add(node);
    }

    public InterfaceBodyNode InterfaceBody(){
        return _interfaceBody;
    }

    public void InterfaceBody(InterfaceBodyNode interfaceBody){
        _interfaceBody=interfaceBody;
    }

    public void setLabel(String label){_label = label;}
    public String getLabel(){return _label;}
    public void setVtable(String label){_vtable = label;}
    public String getVtable(){return _vtable;}

    public void setOffset(int offset){_offset = offset;}
    public int getOffset(){return _offset;}

} // class InterfaceDeclNode