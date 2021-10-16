/*******************************************************************************
 * TypeNode.java
 * 
 * A module implementing the Type node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Type;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.NameNode;
import cs444.group9.AST.Visitor.ClassTypeLinkerVisitor;
import cs444.group9.AST.Node.Environments.Environment;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

public class TypeNode extends ASTNode {
    BasicTypeNode _basicType;
    ArrayTypeNode _arrayType;
    ClassOrInterfaceTypeNode _classOrInterfType;

    /*******************************************************************************
     * TypeNode constructor
     * time : O(1)
     * *****************************************************************************/
    public TypeNode(Node parseTreeNode) {
        super(parseTreeNode);
    } // TypeNode(Node)

    /*******************************************************************************
     * TypeNode constructor based on ASTNode
     * time : O(1)
     * *****************************************************************************/
    public TypeNode(ASTNode type) {
        super(null);
        if(type instanceof ArrayTypeNode){
            ArrayType((ArrayTypeNode)type);
        } else if (type instanceof ClassOrInterfaceTypeNode){
            ClassOrInterfaceType((ClassOrInterfaceTypeNode)type);
        } else if (type instanceof BasicTypeNode){
            BasicType((BasicTypeNode)type);
        } // else if
        this.UpdateEnv(type.getEnv());
    } // TypeNode(ASTNode)

    /*******************************************************************************
     * TypeNode constructor based on type, environment
     * time : O(1)
     * *****************************************************************************/
    public TypeNode(String type, Environment e) throws iVisitor.ASTException{
        super(null);
        if(BasicTypeNode.basicTypeStrings().contains(type)) {
            BasicTypeNode t = new BasicTypeNode(null);
            t.setElement(type);
            BasicType(t);
        } else if(type.equals("String")){
            // creates a new type of String and links to the class String
            NameNode name = new NameNode(null);
            name.addName("java");
            name.addName("lang");
            name.addName("String");
            name.UpdateEnv(e);
            ClassTypeLinkerVisitor v = new ClassTypeLinkerVisitor();
            v.findTypeLink(name);
            ClassOrInterfaceTypeNode t = new ClassOrInterfaceTypeNode(null);
            t.Name(name);
            t.UpdateEnv(e);
            ClassOrInterfaceType(t);
        } // else if
        this.UpdateEnv(e);
    } // TypeNode(String, Environment)

    /*******************************************************************************
     * Visitor acceptor
     * time : O(1)
     * *****************************************************************************/ 
    @Override
    public void accept(iVisitor visitor) throws iVisitor.ASTException {
        visitor.visit(this);
    } // accept()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(1)
     * *****************************************************************************/
    @Override
    public void print() {
        if(_basicType != null) _basicType.print();
        else if(_arrayType != null) _arrayType.print();
        else if(_classOrInterfType != null) _classOrInterfType.print();
        else throw new Error();
    } // print()

    /*******************************************************************************
     * Accessor methods
     * *****************************************************************************/ 
    public BasicTypeNode BasicType(){ return _basicType; }

    public void BasicType(BasicTypeNode basicType){ _basicType = basicType; }

    public ArrayTypeNode ArrayType(){ return _arrayType; }

    public void ArrayType(ArrayTypeNode arrayType){ _arrayType = arrayType; }

    public ClassOrInterfaceTypeNode ClassOrInterfaceType() {
        return _classOrInterfType;
    }

    public void ClassOrInterfaceType(ClassOrInterfaceTypeNode classOrInterfType){ _classOrInterfType = classOrInterfType; }

    /*******************************************************************************
     * Compares two types
     * time: O(1)
     * *****************************************************************************/ 
    public boolean isSameType(TypeNode t){
        if(t == null) return false;
        if(_basicType != null && t.BasicType() != null){
            return _basicType.isSameBasicType(t.BasicType());
        } else if(_arrayType != null && t.ArrayType() != null){
            return _arrayType.isSameArrayType(t.ArrayType());
        } else if(_classOrInterfType != null && t.ClassOrInterfaceType() != null){
            return _classOrInterfType.isSameClassOrInterfType(t.ClassOrInterfaceType());
        }
        return false;
    } // isSameType()

    /*******************************************************************************
     * Predicate methods
     * *****************************************************************************/ 
    public boolean isByte(){
        return (_basicType != null) && (_basicType.isByte());
    }

    public boolean isShort(){
        return (_basicType != null) && (_basicType.isShort());
    }

    public boolean isChar(){
        return (_basicType != null) && (_basicType.isChar());
    }

    public boolean isInt(){
        return (_basicType != null) && (_basicType.isInt());
    }

    public boolean isBoolean(){
        return (_basicType != null) && (_basicType.isBoolean());
    }

    public boolean isNull() {
        return (_basicType != null) && (_basicType.isNull());
    }

    public boolean isNum(){
        return (_basicType != null) && (_basicType.isNum());
    }

    public boolean isString(){
        return (_classOrInterfType != null) && (_classOrInterfType.Name().getSimple().equals("String"))
                && (_classOrInterfType.Name().getLinkedDeclaration().getEnv().getPackage().toString().equals("java.lang"));
    }

    public boolean isVoid(){
        return (_basicType != null) && (_basicType.isVoid());
    }

    /*******************************************************************************
     * Returns the environment of the class where this is declared
     * notes: THIS must be a classOrInterface or classOrInterface array
     * time: O(1)
     * *****************************************************************************/ 
    public Environment getDeclEnvironment(){
        if(ClassOrInterfaceType() != null){
            return ClassOrInterfaceType().Name().getLinkedDeclaration().getEnv();
        } else if (ArrayType() != null && ArrayType().Name() != null) {
            return ArrayType().Name().getLinkedDeclaration().getEnv();
        } else throw new Error();
    } // getDeclEnvironment()

    /*******************************************************************************
     * Converts type to string
     * time: O(1)
     * *****************************************************************************/ 
    public String toString(){
        if(_basicType != null) return _basicType.toString();
        else if(_arrayType != null) return _arrayType.toString();
        else return _classOrInterfType.toString();
    } // toString()
} // class TypeNode
