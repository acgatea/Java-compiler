/*******************************************************************************
 * ClassDeclNode.java
 * 
 * A module implementing the ClassDecl node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Declaration;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.Body.ClassBodyNode;
import cs444.group9.AST.Node.ModifierNode;
import cs444.group9.AST.Node.NameNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

import java.util.ArrayList;
import java.util.List;

public class ClassDeclNode extends ASTNode {

    List<ModifierNode> _modifiers;
    String _identifier;
    ClassBodyNode _classbody;
    List<NameNode> _interfaces;
    NameNode _classtype;
    String _label;
    String _vtable;
    int _offset; // subtype offset

    /*******************************************************************************
     * ClassDeclNode constructor
     * time : O(1)
     * *****************************************************************************/
    public ClassDeclNode(Node node){
        super(node);
        _modifiers=new ArrayList<>();
        _interfaces=new ArrayList<>();
    } // ClassDeclNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/
    @Override
    public void print() {
        for(ModifierNode modifier: _modifiers){
            modifier.print();
        } // for
        System.out.println("Class Declaration Identifier: " + _identifier);
        for(NameNode interfaceNode: _interfaces) {
            interfaceNode.print();
        } // for
        if (_classtype != null){
            _classtype.print();
        } // if
        _classbody.print();
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

    public List<ModifierNode> Modifiers(){
        return _modifiers;
    }

    public void addModifier(ModifierNode node){
        _modifiers.add(node);
    }

    public ClassBodyNode ClassBody(){
        return _classbody;
    }

    public void ClassBody(ClassBodyNode node){
        _classbody=node;
    }

    public List<NameNode> Interfaces() {
        return _interfaces;
    }

    public void addInterface(NameNode node){
        _interfaces.add(node);
    }

    public NameNode ClassType(){
        return _classtype;
    }

    public void ClassType(NameNode node){
        _classtype=node;
    }

    public void setLabel(String label){_label = label;}
    public String getLabel(){return _label;}
    public void setVtable(String label){_vtable = label;}
    public String getVtable(){return _vtable;}

    public void setOffset(int offset){_offset = offset;}
    public int getOffset(){return _offset;}
} // class ClassDeclNode