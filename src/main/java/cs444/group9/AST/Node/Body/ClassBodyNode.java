/*******************************************************************************
 * ClassBodyNode.java
 * 
 * A module implementing the ClassBodyNode for the AST. 
 * ****************************************************************************/

package cs444.group9.AST.Node.Body;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.Declaration.ClassBodyDeclNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ClassBodyNode extends ASTNode {
    List<ClassBodyDeclNode> _classBodyDeclarations;

    /*******************************************************************************
     * ClassBodyNode constructor
     * time : O(1)
     * *****************************************************************************/
    public ClassBodyNode(Node parseTreeNode) {
        super(parseTreeNode);
        _classBodyDeclarations=new ArrayList<>();
    } // ClassBodyNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/ 
    @Override
    public void print() {
        for (ClassBodyDeclNode node: _classBodyDeclarations) {
            node.print();
        } // for
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
     * Getter for _classBodyDeclarations
     * time : O(1)
     * *****************************************************************************/
    public List<ClassBodyDeclNode> ClassBodyDeclarations(){
        return _classBodyDeclarations;
    } // ClassBodyDeclarations()

    /*******************************************************************************
     * Adds node to _classBodyDeclarations
     * time : O(1)
     * *****************************************************************************/
    public void addClassBodyDecl(ClassBodyDeclNode node){
        _classBodyDeclarations.add(node);
    } // addClassBodyDecl()
} // class ClassBodyNode
