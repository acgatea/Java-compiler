/*******************************************************************************
 * iVisitable.java
 * 
 * A module with accept method declaration
 * ****************************************************************************/
package cs444.group9.AST.Visitor;

public interface iVisitable {
    void accept (iVisitor visitor) throws iVisitor.ASTException;
} // interface iVisitable
