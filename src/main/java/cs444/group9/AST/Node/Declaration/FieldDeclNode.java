/*******************************************************************************
 * FieldDeclNode.java
 * 
 * A module implementing the FieldDecl node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Declaration;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.ModifierNode;
import cs444.group9.AST.Node.Type.TypeNode;
import cs444.group9.AST.Node.Environments.FieldData;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

import java.util.ArrayList;
import java.util.List;

public class FieldDeclNode extends ASTNode {
    List<ModifierNode> _modifiers;
    TypeNode _type;
    VariableDeclNode _VariableDeclarator;
    FieldData _fieldData;
    String _label;
    int _offset;

    /*******************************************************************************
     * FieldDeclNode constructor
     * time : O(1)
     * *****************************************************************************/
    public FieldDeclNode(Node node) {
        super(node);
        _modifiers=new ArrayList<>();
        _offset = 0;
    } // FieldDeclNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/
    @Override
    public void print() {
        for(ModifierNode modifier: _modifiers){
            modifier.print();
        } // for
        System.out.println(" Print type");
        _type.print();
        _VariableDeclarator.print();
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

    public List<ModifierNode> Modifiers(){
        return _modifiers;
    }

    public void addModifier(ModifierNode node){
        _modifiers.add(node);
    }

    public TypeNode Type(){ return _type; }

    public void Type(TypeNode node){
        _type=node;
    }

    public VariableDeclNode VariableDeclarator(){
        return _VariableDeclarator;
    }

    public void VariableDeclarator(VariableDeclNode node){
        _VariableDeclarator=node;
    }

    public FieldData getFieldData(){ return _fieldData;}
    public void setFieldData(FieldData f){_fieldData = f;}

    public void setLabel(String label){_label = label;}
    public String getLabel(){return _label;}
    public void setOffset(int offset){_offset = offset;}
    public int getOffset(){return _offset ;}
} // class FieldDeclNode