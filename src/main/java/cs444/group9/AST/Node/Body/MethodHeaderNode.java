/*******************************************************************************
 * MethodHeaderNode.java
 * 
 * A module implementing the MethodHeader node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Body;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.Declaration.MethodDeclNode;
import cs444.group9.AST.Node.Environments.MethodData;
import cs444.group9.AST.Node.ModifierNode;
import cs444.group9.AST.Node.Type.TypeNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

import java.util.ArrayList;
import java.util.List;

public class MethodHeaderNode extends ASTNode {
    List<ModifierNode> _modifiers;
    TypeNode _returnType;
    MethodDeclNode _methodDeclarator;
    MethodData _methodData;

    String _label;
    int _offset;
    int _InterfaceMethodoffset;
    String _qualifiedFileName;

    /*******************************************************************************
     * MethodHeaderNode constructor
     * time : O(1)
     * *****************************************************************************/
    public MethodHeaderNode(Node parseTreeNode) {
        super(parseTreeNode);
        _modifiers=new ArrayList<>();
        _label = "";
        _offset = 0;
    } // MethodHeaderNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/    
    @Override
    public void print() {
        for(ModifierNode node: _modifiers){
            node.print();
        } // for
        if(_returnType != null){
            _returnType.print();
        } // if
        _methodDeclarator.print();
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

    public TypeNode ReturnType(){
        return _returnType;
    }

    public void ReturnType(TypeNode node){
        _returnType=node;
    }

    public MethodDeclNode MethodDeclarator(){
        return _methodDeclarator;
    }

    public void MethodDeclarator(MethodDeclNode node){
        _methodDeclarator=node;
    }

    public MethodData getMethodData(){ return _methodData;}
    public void setMethodData(MethodData m){_methodData = m;}

    public void setLabel(String label){_label = label;}
    public String getLabel(){return _label;}
    public void setOffset(int offset){_offset = offset;}
    public int getOffset(){return _offset ;}

    public void setInterfaceMethodOffset(int offset){_InterfaceMethodoffset = offset;}
    public int getInterfaceMethodoffset(){return _InterfaceMethodoffset ;}
} // class MethodHeaderNode