/*******************************************************************************
 * NameNode.java
 * 
 * A module implementing the AST node for Names.
 * ****************************************************************************/

package cs444.group9.AST.Node;

import cs444.group9.AST.Node.Declaration.ClassDeclNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

import java.util.ArrayList;
import java.util.List;

public class NameNode extends ASTNode {

    List<String> _nameList;
    ASTNode _linkedDeclaration;
    List<NameNode> _prefixes;

    /*******************************************************************************
     * NameNode constructor
     * time : O(1)
     * *****************************************************************************/
    public NameNode(Node parseTreeNode) {
        super(parseTreeNode);
        _nameList=new ArrayList<>();

        // TEMPORARY, to make Hierarchy Visitor run
        List <Node> children = new ArrayList<Node>();
        Node n= new Node("", children);
        _linkedDeclaration = new ClassDeclNode(n);
        _prefixes = new ArrayList<>();
    } // NameNode()


    @Override
    /*******************************************************************************
     * ASTNode printer
     * time : O(|_nameList|)
     * *****************************************************************************/
    public void print() {
        for(String name: _nameList){
            System.out.print("Name: " + name + " ");
        } // for
    } // print()

    @Override
    /*******************************************************************************
     * Visitor acceptor
     * time : O(1)
     * *****************************************************************************/
    public void accept(iVisitor visitor) throws iVisitor.ASTException {
        visitor.visit(this);
    } // accept()

    /*******************************************************************************
     * Getter for name list
     * time : O(1)
     * *****************************************************************************/
    public List<String> NameList() {
        return _nameList;
    } // NameList()

    /*******************************************************************************
     * Adds name to name list
     * time : O(1)
     * *****************************************************************************/
    public void addName(String name) {
        _nameList.add(name);
    } // addName()

    /*******************************************************************************
     * Setter for _linkedDeclarartion
     * time : O(1)
     * *****************************************************************************/
    public void setLinkedDeclaration(ASTNode n) {
        _linkedDeclaration = n;
    } // setLinkedDeclaration()

    /*******************************************************************************
     * Getter for _linkedDeclarartion
     * time : O(1)
     * *****************************************************************************/
    public ASTNode getLinkedDeclaration () {
        return _linkedDeclaration;
    } // getLinkedDeclaration()

    /*******************************************************************************
     * Getter for last name in _nameList
     * time : O(1)
     * *****************************************************************************/
    public String getSimple(){
        return _nameList.get(_nameList.size()-1);
    } // getSimple()

    /*******************************************************************************
     * Appends full name except for last name in _nameList
     * time : O(|full name|)
     * *****************************************************************************/
    public String getAllButLastName(){
        String singleName="";
        int size = _nameList.size();
        for(int i = 0; i < size-1 ;++i){
            singleName += _nameList.get(i);
            if(i < size - 2){
                singleName += ".";
            } // if
        } // for
        return singleName;
    } // getAllButLastName()

    /*******************************************************************************
     * Determines if THIS name is a left prefix of n
     *      eg java.util is a left prefix of java.util.lang but not of lib.java.util
     * time : O(|full name|)
     * *****************************************************************************/
    public boolean isLeftPrefix(NameNode n){
        List<String> nameList = n.NameList();
        int _nameListSize = _nameList.size();
        if(nameList.size() < _nameListSize) return false;
        for(int i = 0; i < _nameListSize; ++i){
            if(!nameList.get(i).equals(_nameList.get(i))) return false;
        } // for
        return true;
    } // isLeftPrefix()

    /*******************************************************************************
     * Compares THIS NameNode and n
     * time : O(1)
     * *****************************************************************************/
    public boolean isSameName(NameNode n){
        return _linkedDeclaration == n.getLinkedDeclaration();
    } // isSameName()

    /*******************************************************************************
     * Determines if name is qualified
     * time : O(1)
     * *****************************************************************************/
    public boolean isQualified(){
        return _nameList.size() > 1;
    } // isQualified()

    /*******************************************************************************
     * Setter for prefixes
     * time : O(1)
     * *****************************************************************************/
    public void setPrefixes(List<NameNode> prefixes) {
        _prefixes = prefixes;
    } // setPrefixes()

    /*******************************************************************************
     * Adds prefix
     * time : O(1)
     * *****************************************************************************/    
    public void addPrefix(NameNode prefix) {
        _prefixes.add(prefix);
    } // addPrefix()

    /*******************************************************************************
     * Getter for prefixes
     * time : O(1)
     * *****************************************************************************/    
    public List<NameNode> getPrefixes() {
        return _prefixes;
    } // getPrefixes()

    @Override
    /*******************************************************************************
     * Gets full name
     * time : O(|full name|)
     * *****************************************************************************/
    public String toString(){
        return getAllButLastName() + "." + getSimple();
    } // toString()
} // class NameNode
