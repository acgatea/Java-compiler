/*******************************************************************************
 * BasicTypeNode.java
 * 
 * A module implementing the BasicType node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Type;

import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;
import cs444.group9.AST.Node.ASTNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BasicTypeNode extends ASTNode {
    String _element;
    public final static String BYTE_TYPE ="byte";
    public final static String SHORT_TYPE ="short";
    public final static String CHAR_TYPE ="char";
    public final static String INT_TYPE="int";
    public final static String BOOLEAN_TYPE="boolean";
    public final static String NULL_TYPE="null";
    public final static String VOID_TYPE="void";

    /*******************************************************************************
     * BasicTypeNode constructor
     * time : O(1)
     * *****************************************************************************/
    public BasicTypeNode(Node parseTreeNode) {
        super(parseTreeNode);
    } // BasicTypeNode()

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
        System.out.println("Basic type: " + _element);
    } // print()

    /*******************************************************************************
     * Accessor methods
     * *****************************************************************************/ 
    public String getElement() {
        return _element;
    }

    public void setElement(String element){
        _element=element;
    }

    public String toString(){
        return _element;
    }

    /*******************************************************************************
     * Predicate methods
     * *****************************************************************************/ 
    public boolean isSameBasicType(BasicTypeNode t) {
        return _element.equals(t.getElement());
    }

    public boolean isByte(){
        return _element.equals(BYTE_TYPE);
    }

    public boolean isShort(){
        return _element.equals(SHORT_TYPE);
    }

    public boolean isChar(){
        return _element.equals(CHAR_TYPE);
    }

    public boolean isInt(){
        return _element.equals(INT_TYPE);
    }

    public boolean isBoolean(){
        return _element.equals(BOOLEAN_TYPE);
    }

    public boolean isNull() { return _element.equals(NULL_TYPE); }

    public boolean isNum(){
        return isByte() || isChar() || isShort() || isInt();
    }

    public boolean isVoid(){
        return _element.equals(VOID_TYPE);
    }

    /*******************************************************************************
     * Gets list of basic types
     * time: O(1)
     * *****************************************************************************/ 
    public static List<String> basicTypeStrings(){
        ArrayList<String> list = new ArrayList<>();
        list.add(BasicTypeNode.BYTE_TYPE);
        list.add(BasicTypeNode.CHAR_TYPE);
        list.add(BasicTypeNode.BOOLEAN_TYPE);
        list.add(BasicTypeNode.INT_TYPE);
        list.add(BasicTypeNode.SHORT_TYPE);
        list.add(BasicTypeNode.NULL_TYPE);
        return list;
    } // basicTypeStrings()
} // class BasicTypeNode