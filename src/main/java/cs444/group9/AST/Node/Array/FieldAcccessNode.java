/*******************************************************************************
 * FieldAccessNode.java
 * 
 * A module implementing the Field Access node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Array;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

public class FieldAcccessNode extends ASTNode {
    PrimaryNode _primary;
    String _identifier;
    ASTNode decl; // ptr to declaration (for Primary.Identifier)

    /*******************************************************************************
     * FieldAccessNode constructor
     * time : O(1)
     * *****************************************************************************/
    public FieldAcccessNode(Node parseTreeNode) {
        super(parseTreeNode);
    } // FieldAcccessNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/     
    @Override
    public void print(){
        _primary.print();
        System.out.println("FieldAccess Identifier: " + _identifier);
    } // print()

    /*******************************************************************************
     * Visitor accept
     * time : O(1)
     * *****************************************************************************/
    @Override
    public void accept(iVisitor visitor) throws iVisitor.ASTException {
        visitor.visit(this);
    } // accept()

   /*******************************************************************************
     * Getter for identifier
     * time : O(1)
     * *****************************************************************************/    
    public String Identifier() {
        return _identifier;
    } // Identifier()

   /*******************************************************************************
     * Getter for primary
     * time : O(1)
     * *****************************************************************************/
    public PrimaryNode Primary() {
        return _primary;
    } // Primary()

   /*******************************************************************************
     * Setter for identifier
     * time : O(1)
     * *****************************************************************************/
    public void Identifier(String identifier) {
        _identifier = identifier;
    } // Identifier(String)

   /*******************************************************************************
     * Setter for primary
     * time : O(1)
     * *****************************************************************************/
    public void Primary(PrimaryNode primary) {
        _primary = primary;
    } // Primary(PrimaryNode)

   /*******************************************************************************
     * Setter for field declaration
     * time : O(1)
     * *****************************************************************************/
    public void setDecl(ASTNode n) {decl = n;}

   /*******************************************************************************
     * Getter for field declaration
     * time : O(1)
     * *****************************************************************************/
    public ASTNode getFieldDecl() {return decl;}

} // class FieldAccessNode
