/*******************************************************************************
 * MethodData.java
 * 
 * A module implementing a class holding the data for a method/constructor.
 * ****************************************************************************/
package cs444.group9.AST.Node.Environments;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.Body.FormalParameterNode;
import cs444.group9.AST.Node.Body.MethodHeaderNode;
import cs444.group9.AST.Node.Declaration.ConstructorDeclarationNode;
import cs444.group9.AST.Node.ModifierNode;
import cs444.group9.AST.Node.Type.BasicTypeNode;
import cs444.group9.AST.Node.Type.TypeNode;
import cs444.group9.AST.Visitor.iVisitor;

import java.util.ArrayList;
import java.util.List;

// info for method, constructor
public class MethodData {
    private String _name;
    private TypeNode _returnType; // null if constructor or if type=void
    private List<FormalParameterNode> _formalParameterList;
    private boolean _isPublic;    // true if public, false if protected
    private boolean _isAbstract;
    private boolean _isFinal;
    private boolean _isStatic;
    private boolean _isNative;
    private boolean _isVoid;
    private boolean _isConstructor;
    private boolean _ImplementsInterfaceMethod;
    private ASTNode _decl;

    /*******************************************************************************
     * Sets the modifier flags based on modifiers
     * time : O(#modifiers)
     * *****************************************************************************/
    private void determineModifiers(List<ModifierNode> modifiers){
        _isPublic = false;
        _isAbstract = false;
        _isFinal = false;
        _isStatic = false;
        _isNative = false;
        _ImplementsInterfaceMethod = false;
        for(ModifierNode m : modifiers){
            if(m.Modifier().equals("PUBLIC")) _isPublic = true;
            else if(m.Modifier().equals("ABSTRACT")) _isAbstract = true;
            else if(m.Modifier().equals("FINAL")) _isFinal = true;
            else if(m.Modifier().equals("STATIC")) _isStatic = true;
            else if(m.Modifier().equals("NATIVE")) _isNative = true;
        } // for
    } // determineModifiers()

    /*******************************************************************************
     * MethodData constructor, based on method header
     * time : O(#modifiers)
     * *****************************************************************************/
    public MethodData(MethodHeaderNode node) throws iVisitor.ASTException{
        _formalParameterList = new ArrayList<FormalParameterNode>();
        _isVoid = false;
        _isConstructor = false;
        if (node.ReturnType() != null) _returnType = node.ReturnType();
        else _isVoid = true;
        determineModifiers(node.Modifiers());
        _name = node.MethodDeclarator().Identifier();

        // will throw an exception if two parameters have the same name
        setParams(node.MethodDeclarator().FormalParameterList());
        _decl = node;
    } // MethodData(MethodHeaderNode)

    /*******************************************************************************
     * MethodData constructor, based on constructor header
     * time : O(#modifiers)
     * *****************************************************************************/
    public MethodData(ConstructorDeclarationNode node) throws iVisitor.ASTException{
        _formalParameterList = new ArrayList<FormalParameterNode>();
        _isVoid = false;
        _isConstructor = true;
        determineModifiers(node.Modifiers());

        _name = node.ConstructorDeclarator().Identifier();

        // will throw an exception if two parameters have the same name
        setParams(node.ConstructorDeclarator().FormalParameterList());
        _decl = node;
    } // MethodData(ConstructorDeclarationNode)

    /*******************************************************************************
     * Checks that this is a native method not of the restricted type, 
     * otherwise throws error
     * time : O(1)
     * *****************************************************************************/
    public void GeneralNativeMethodCheck() throws iVisitor.ASTException{
        if(isNative()) {
            if(isStatic() && _formalParameterList.size() == 1 && _returnType.isInt()
                    && _formalParameterList.get(0).Type().isInt()) return;
            System.err.println("Invalid general native method.");
            throw new iVisitor.ASTException();
        } // if
    } // GeneralNativeMethodCheck()

    /*******************************************************************************
     * Checks that there are no duplicate parameters
     * time : O(#params^2)
     * *****************************************************************************/
    public void setParams(List<FormalParameterNode> formalParameterList)throws iVisitor.EnvironmentBuildingException{
        _formalParameterList = formalParameterList;
        int formalParameterListSize = formalParameterList.size();
        for(int i = 0; i < formalParameterListSize; ++i){
            for(int j = 0; j < i; ++j){
                FormalParameterNode p1 = formalParameterList.get(i);
                FormalParameterNode p2 = formalParameterList.get(j);
                if(p1.VariableDeclaratorId().equals(p2.VariableDeclaratorId())) {
                    System.out.println("Duplicate parameter name found in method.");
                    throw new iVisitor.EnvironmentBuildingException();
                } // if
            } // for
        } // for
    } // setParams()

    /*******************************************************************************
     * Returns true if n = name, types of params = types of _formalParameterList
     * time : O(#params)
     * *****************************************************************************/
    public boolean isSameSignature(String n, List<FormalParameterNode> params){
        int paramNum = params.size();
        if(!n.equals(_name) || paramNum != _formalParameterList.size()) return false;
        for(int i = 0; i < paramNum; ++i){
            FormalParameterNode p1 = params.get(i);
            FormalParameterNode p2 = _formalParameterList.get(i);
            if(! p1.Type().isSameType(p2.Type())) return false;
        } // for
        return true;
    } // isSameSignature(String, List<FormalParameterNode>)

    /*******************************************************************************
     * Wrapper for isSameSignature(String, List<FormalParameterNode>)
     * time : O(#params)
     * *****************************************************************************/    
    public boolean isSameSignature(MethodData m) {
        return isSameSignature(m._name, m._formalParameterList);
    } // isSameSignature(MethodData)

    /*******************************************************************************
     * Accessor methods
     * *****************************************************************************/ 
    public void setAbstract(boolean flag) {_isAbstract = flag;}

    public boolean isConstructor() { return _isConstructor;}
    public boolean isVoid() { return _isVoid;}

    public TypeNode getReturnType(){
        return _returnType;
    }

    public String getMethodName(){ return _name;}
    public boolean isPublic() {return _isPublic;}
    public boolean isAbstract() {return _isAbstract;}
    public boolean isFinal() {return _isFinal;}
    public boolean isStatic() {return _isStatic;}
    public boolean isNative() {return _isNative;}
    public boolean ImplementsInterfaceMethod(){return _ImplementsInterfaceMethod;}
    public void set_ImplementsInterfaceMethod(boolean flag) {_ImplementsInterfaceMethod = flag;}
    public boolean hasNoParams() {return (_formalParameterList.size() == 0);}
    public ASTNode getDecl() {return _decl;}
    public List<FormalParameterNode> getFormalParameterlist(){return _formalParameterList;}
} // class MethodData