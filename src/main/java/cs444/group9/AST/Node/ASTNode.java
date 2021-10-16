/*******************************************************************************
 * ASTNode.java
 * 
 * A module implementing an AST node.
 * ****************************************************************************/

package cs444.group9.AST.Node;

import cs444.group9.AST.Node.Environments.Environment;
import cs444.group9.AST.Node.Environments.EnvironmentPair;
import cs444.group9.AST.Visitor.iVisitable;
import cs444.group9.Parser.Node;
import java.util.*;

public abstract class ASTNode implements iVisitable {

    protected Node _parseTreeNode;
    protected Environment symbolTable;
    protected ASTNode _type;

    /*******************************************************************************
     * ASTNode constructor
     * time : O(1)
     * *****************************************************************************/
    public ASTNode(Node parseTreeNode){
        _parseTreeNode=parseTreeNode;
    } // ASTNode()

    /*******************************************************************************
     * Getter for _parseTreeNode
     * time : O(1)
     * *****************************************************************************/
    public Node get_parseTreeNode(){
        return _parseTreeNode;
    } // get_parseTreeNode()

    /*******************************************************************************
     * Setter for symbolTable
     * time : O(1)
     * *****************************************************************************/
    public void UpdateEnv( Environment e){
        symbolTable = e;
    } // UpdateEnv()

    /*******************************************************************************
     * Getter for symbolTable
     * time : O(1)
     * *****************************************************************************/
    public Environment getEnv(){
        return symbolTable;
    } // getEnv()

    /*******************************************************************************
     * Printer for symbolTable
     * time : O(|symbolTable|)
     * *****************************************************************************/
    public void printAllEnv(){
        symbolTable.printEnv();
    } // printAllEnv()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/ 
    public abstract void print();

    /*******************************************************************************
     * Getter for type
     * time : O(1)
     * *****************************************************************************/
    public ASTNode getType(){
        return _type;
    } // getType()

    /*******************************************************************************
     * Setter for type
     * time : O(1)
     * *****************************************************************************/
    public void setType(ASTNode type){
        _type=type;
    } // setType()
} // class ASTNode
