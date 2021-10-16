/*******************************************************************************
 * Node.java
 * 
 * A module implementing the parse tree nodes.
 * ****************************************************************************/

package cs444.group9.Parser;
import java.util.*;
import cs444.group9.Scanner.*;


public class Node {
    // terminal or non-terminal corresponding to this node
    String symbol;

    // if symbol is a terminal, this is the corresponding token
    // if symbol is a nonterminal, this is the error token
    Token token;
    List<Node> children;

    // if symbol is Modifiers/Modifier or InterfaceTypes/InterfaceType, this contains
    // all modifiers/interface types in the subtree of node
    List<String> ModifierList;

    /*******************************************************************************
     * Constructor for leaf node with token t
     * time : O(1)
     * *****************************************************************************/ 
    public Node(Token t) {
        children = new ArrayList<Node>();
        ModifierList = new ArrayList<String>();
        token = t;
        symbol = t.returnKind();
    } // Node(Token)

    /*******************************************************************************
     * Constructor for non-leaf node with given symbol and list of children
     * time : O(1)
     * *****************************************************************************/ 
    public Node(String s, List<Node> q) {
        children = q;
        ModifierList = new ArrayList<String>();
        symbol = s;
        token = new Token("ERROR", -1, -1);
    } // Node(s, q)

    /*******************************************************************************
     * Getter for symbol
     * time : O(1)
     * *****************************************************************************/ 
    public String getSymbol() {
        return symbol;
    } // getSymbol()

    /*******************************************************************************
     * Getter for children
     * time : O(1)
     * *****************************************************************************/ 
    public List<Node> getChildren() {
        return children;
    } // getChildren()

    /*******************************************************************************
     * Getter for modifiers
     * time : O(1)
     * *****************************************************************************/ 
    public List<String> getModifiers() {
        return ModifierList;
    } // getModifiers()

    /*******************************************************************************
     * Setter for modifiers
     * time : O(1)
     * *****************************************************************************/ 
    public void setModifiers(List<String> list) {
        ModifierList = list;
    } // setModifiers()

    /*******************************************************************************
     * Predicate for EOF
     * time : O(1)
     * *****************************************************************************/ 
    public boolean isEOF() {
        return symbol == "EOF";
    } // isEOF()

    /*******************************************************************************
     * Getter for lexeme
     * time : O(1)
     * *****************************************************************************/ 
    public String getLexeme() {
        return token.returnLexeme();
    } // getLexeme()

    /*******************************************************************************
     * Prints node's token
     * time : O(1)
     * *****************************************************************************/ 
    public void printNodeToken() {
        token.printErrToken();
    } // printNodeToken()

    /*******************************************************************************
     * Node printer
     * time : O(#children)
     * *****************************************************************************/ 
    public void printNode() {
        if (children.size() == 0) {
            System.out.println(symbol + ", " + children.size() + " ; LEAF; Lexeme: " + token.returnLexeme());
            return;
        } // if

        System.out.println(symbol + ", " + children.size() + " ; CHILDREN:");
        ListIterator<Node> itr = children.listIterator();
        while (itr.hasNext()) {
            itr.next().printNode();
        } // while
    } // printNode()

    /*******************************************************************************
     * Flattens lists in tree
     * time : O(|parse tree|)
     * *****************************************************************************/ 
    public void flattenTree() {
        // list of delimitedList nonterminals
        List<String> delimitedList = new ArrayList<String>();
        delimitedList.add("InterfaceTypeList");
        delimitedList.add("FormalParameterList");
        delimitedList.add("VariableInitializers");
        delimitedList.add("ArgumentList");

        // list of non-delimitedList nonterminals
        List<String> nonDelimitedList = new ArrayList<String>();
        nonDelimitedList.add("Modifiers");
        nonDelimitedList.add("ImportDeclarations");
        nonDelimitedList.add("ClassBodyDeclarations");
        nonDelimitedList.add("InterfaceMemberDeclarations");
        nonDelimitedList.add("BlockStatements");

        // flattens nondelimited and delimited lists
        if (delimitedList.contains(symbol) || nonDelimitedList.contains(symbol)) {
            // index offset between list items due to delimiters
            int offset = delimitedList.contains(symbol) ? 1 : 0;

            // the new list of children of this node
            List<Node> newChildren = new ArrayList<Node>();
            // root of the current subtree
            Node currNode = this;
            while (true) {
                List<Node> currChildren = currNode.getChildren();
                if (currChildren.size() == 2 + offset) {
                    // add right node to the new list of children
                    newChildren.add(0, currChildren.get(1 + offset));
                    currNode = currChildren.get(0);
                } else {
                    // if 1 child, add the child to new list of children
                    newChildren.add(0, currChildren.get(0));
                    break;
                } // else
            } // while
            children = newChildren;
        } else if (symbol.equals("QualifiedIdentifier")){
            List<Node> newChildren = new ArrayList<Node>();
            // root of the current subtree
            Node currNode = this;
            while (true) {
                List<Node> currChildren = currNode.getChildren();
                if (currChildren.size() == 3) {
                    // add right node to the new list of children
                    newChildren.add(0, currChildren.get(2));
                    currNode = currChildren.get(0);
                } else if (currChildren.get(0).getSymbol().equals("Identifier")){
                    // if 1 child, add the child to new list of children
                    newChildren.add(0, currChildren.get(0));
                    break;
                } else { 
                    // QualifiedIdentifier
                    currNode = currChildren.get(0);
                } // else
            } // while
            children = newChildren;
        } // else if
        for (Node n : children) {
            n.flattenTree();
        } // for
    } // flattenTree()
} // class Node

