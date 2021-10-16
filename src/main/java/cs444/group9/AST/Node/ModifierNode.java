/*******************************************************************************
 * ModifierNode.java
 * 
 * A module implementing the AST node for Modifiers.
 * ****************************************************************************/

package cs444.group9.AST.Node;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

public class ModifierNode extends ASTNode {

    String _modifier;

    /*******************************************************************************
     * ModifierNode constructor
     * time : O(1)
     * *****************************************************************************/
    public ModifierNode(Node parseTreeNode) {
        super(parseTreeNode);
    } // ModifierNode()

    /*******************************************************************************
     * ASTNode printer
     * time : O(1)
     * *****************************************************************************/    
    @Override
    public void print() {
        System.out.println("Modifier: " + _modifier);
    } // print

    /*******************************************************************************
     * Visitor acceptor
     * time : O(1)
     * *****************************************************************************/
    @Override
     public void accept(iVisitor visitor) throws iVisitor.ASTException {
        visitor.visit(this);
    } // accept()

    /*******************************************************************************
     * Getter for modifier
     * time : O(1)
     * *****************************************************************************/
    public String Modifier(){
        return _modifier;
    } // Modifier()

    /*******************************************************************************
     * Setter for modifier
     * time : O(1)
     * *****************************************************************************/
    public void Modifier(String modifier){
        _modifier=modifier;
    } // Modifier(String)
} // class ModifierNode
