/*******************************************************************************
 * LiteralNode.java
 * 
 * A module implementing the AST node for literals.
 * ****************************************************************************/

package cs444.group9.AST.Node;

import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

public class LiteralNode extends ASTNode{
    String _literalType;
    String _integerLiteral; // might be 2147483648
    boolean _booleanLiteral;
    char _charliteral;
    String _stringliteral;

    public static final String Literal_BOOL="boolean";
    public static final String Literal_INT="int";
    public static final String Literal_CHAR="char";
    public static final String Literal_STR="String";
    public static final String Literal_NULL="null";

    /*******************************************************************************
     * LiteralNode constructor for a node
     * time : O(1)
     * *****************************************************************************/   
    public LiteralNode(Node parseTreeNode) {
        super(parseTreeNode);
    } // LiteralNode(Node)

    /*******************************************************************************
     * LiteralNode constructor for null
     * time : O(1)
     * *****************************************************************************/    
    public LiteralNode(){
        super(null);
    } // LiteralNode()

    @Override
    /*******************************************************************************
     * Printer for ASTNode
     * time : O(1)
     * *****************************************************************************/ 
    public void print() {
        if (_literalType.equals(Literal_INT)) {
            System.out.println("Integer literal: " + _integerLiteral);
        } else if (_literalType.equals(Literal_CHAR)) {
            System.out.println("Character literal: " + _charliteral);
        } else if (_literalType.equals(Literal_STR)) {
            System.out.println("String literal: " + _stringliteral);
        } else if (_literalType.equals(Literal_BOOL)) {
            System.out.println("Boolean literal: " + _booleanLiteral);
        } else if (_literalType.equals(Literal_NULL)){
            System.out.println("NULL literal");
        } else {
            System.out.println("!!BAD literal");
            throw new Error();
        } // else
    } // print()

    @Override
    /*******************************************************************************
     * Visitor acceptor
     * time : O(1)
     * *****************************************************************************/ 
    public void accept(iVisitor visitor)throws iVisitor.ASTException {
        visitor.visit(this);
    } // accept()

    /*******************************************************************************
     * Getters for different literal types
     * time : O(1)
     * *****************************************************************************/ 
    public String LiteralType() {
        return _literalType;
    } // LiteralType()

    public String IntegerLiteral() {
        return _integerLiteral;
    } // IntegerLiteral()

    public char Charliteral() {
        return _charliteral;
    } // Charliteral()

    public String Stringliteral() {
        return _stringliteral;
    } // Stringliteral()

    public void IntegerLiteral(String str){
        _integerLiteral= str;
        _literalType=Literal_INT;
    } // IntegerLiteral()

    public boolean BooleanLiteral(){
        return _booleanLiteral;
    } // BooleanLiteral()

    /*******************************************************************************
     * Setters for different literal types
     * time : O(1)
     * *****************************************************************************/ 
    public void LiteralType(String literalType) {
        _literalType = literalType;
    } // LiteralType(String)

    public void Charliteral(char charliteral) {
        _charliteral = charliteral;
        _literalType=Literal_CHAR;
    } // Charliteral(char)

    public void Charliteral(String str){
        _charliteral=str.charAt(0);
        _literalType=Literal_CHAR;
    } // Charliteral(String)

    public void BooleanLiteral(boolean booleanLiteral) {
        _booleanLiteral = booleanLiteral;
        _literalType=Literal_BOOL;
    } // BooleanLiteral(boolean)

    public void BooleanLiteral(String str){
        _booleanLiteral=Boolean.parseBoolean(str);
        _literalType=Literal_BOOL;
    } // BooleanLiteral(String)

    public void Stringliteral(String strliteral) {
        _stringliteral = strliteral;
        _literalType=Literal_STR;
    } // Stringliteral(String)

    public String NullLiteral(){
        return null;
    } // NullLiteral()

    /*******************************************************************************
     * Comparison of two different literalNodes
     * time : O(1)
     * *****************************************************************************/ 
    public boolean isSameValue(LiteralNode literalNode){
        if(!_literalType.equals(literalNode.LiteralType())){
            return false;
        } // if
        switch(_literalType){
            case Literal_BOOL:
                return _booleanLiteral == literalNode.BooleanLiteral();
            case Literal_CHAR:
                return _charliteral == literalNode._charliteral;
            case Literal_INT:
                return _integerLiteral.equals(literalNode._integerLiteral);
            case Literal_NULL:
                return true;
            case Literal_STR:
                return _stringliteral.equals(literalNode._stringliteral);
            default:
                break;
        } // switch
        return false;
    } // isSameValue()
} // class LiteralNode
