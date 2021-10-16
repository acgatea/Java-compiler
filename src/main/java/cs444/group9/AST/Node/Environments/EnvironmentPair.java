/*******************************************************************************
 * EnvironmentPair.java
 * 
 * A module implementing an EnvironmentPair.
 * ****************************************************************************/
package cs444.group9.AST.Node.Environments;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.Type.ClassOrInterfaceTypeNode;
import cs444.group9.AST.Node.Type.TypeNode;

public class EnvironmentPair {
    String name;
    ASTNode declaration;

    /*******************************************************************************
     * EnvironmentPair constructor
     * time : O(1)
     * *****************************************************************************/
    public EnvironmentPair(String n, ASTNode d){
        name = n;
        declaration = d;
    } // EnvironmentPair()

    /*******************************************************************************
     * Accessor methods
     * *****************************************************************************/ 
    public String getName(){
        return name;
    }
    public ASTNode getDeclaration(){
        return declaration;
    }
} // class EnvironmentPair