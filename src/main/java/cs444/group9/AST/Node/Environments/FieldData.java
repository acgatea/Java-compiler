/*******************************************************************************
 * FieldData.java
 * 
 * A module implementing class holding data of a field.
 * ****************************************************************************/
package cs444.group9.AST.Node.Environments;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.Declaration.FieldDeclNode;
import cs444.group9.AST.Node.ModifierNode;
import cs444.group9.AST.Node.Type.TypeNode;

public class FieldData {
    private String _name;
    private TypeNode _type;
    private boolean _isPublic; // true if public, false if protected
    private boolean _isStatic;
    private ASTNode _decl;

    /*******************************************************************************
     * FieldData constructor
     * time : O(#modifiers)
     * *****************************************************************************/
    public FieldData(FieldDeclNode node){
        _isPublic = false;
        _isStatic = false;
        for(ModifierNode m : node.Modifiers()){
            if(m.Modifier().equals("PUBLIC")) _isPublic = true;
            else if(m.Modifier().equals("STATIC")) _isStatic = true;
        } // for
        _type = node.Type();
        _name = node.VariableDeclarator().VariableDeclarator();
        _decl = node;
    } // FieldData()

    /*******************************************************************************
     * Accessor methods
     * *****************************************************************************/ 
    public String getFieldName(){ return _name;}
    public TypeNode getType(){ return _type;}
    public boolean isPublic() {return _isPublic;}
    public boolean isStatic() {return _isStatic;}
    public ASTNode getDecl() {return _decl;}
} // class FieldData