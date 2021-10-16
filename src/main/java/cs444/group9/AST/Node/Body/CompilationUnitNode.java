/*******************************************************************************
 * CompilationUnitNode.java
 * 
 * A module implementing the CompilationUnit node for the AST. 
 * ****************************************************************************/
package cs444.group9.AST.Node.Body;

import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.Declaration.ImportDeclNode;
import cs444.group9.AST.Node.Declaration.PackageDeclNode;
import cs444.group9.AST.Node.Declaration.TypeDeclNode;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;

import java.util.ArrayList;
import java.util.List;

public class CompilationUnitNode extends ASTNode {

    PackageDeclNode _packagedeclaration;
    List<ImportDeclNode> _importDeclarations;
    TypeDeclNode _typeDeclaration;

    /*******************************************************************************
     * CompilationUnitNode constructor
     * time : O(1)
     * *****************************************************************************/
    public CompilationUnitNode(Node node){
        super(node);
        _importDeclarations=new ArrayList<>();
    } // CompilationUnitNode()

    /*******************************************************************************
     * Printer for ASTNode
     * time : O(|AST|)
     * *****************************************************************************/    
    @Override
    public void print() {
        if (_packagedeclaration != null) {
            _packagedeclaration.print();
        } // if
        for(ImportDeclNode imp: _importDeclarations) {
            imp.print();
        } // for
        if(_typeDeclaration != null) {
            _typeDeclaration.print();
        } // if
        printAllEnv();
    } // print()

    /*******************************************************************************
     * Visitor acceptor
     * time : O(1)
     * *****************************************************************************/      
    @Override
    public void accept(iVisitor visitor) throws iVisitor.ASTException {
        visitor.visit(this);
    } // accept()

    /*******************************************************************************
     * Getter for type declaration
     * time : O(1)
     * *****************************************************************************/      
    public TypeDeclNode TypeDeclaration(){
        return _typeDeclaration;
    } // TypeDeclaration()

    /*******************************************************************************
     * Getter for package declaration
     * time : O(1)
     * *****************************************************************************/  
    public PackageDeclNode PackageDeclaration(){
        return _packagedeclaration;
    } // PackageDeclaration()

    /*******************************************************************************
     * Getter for import declaration
     * time : O(1)
     * *****************************************************************************/  
    public List<ImportDeclNode> ImportDeclarations(){
        return _importDeclarations;
    } // ImportDeclarations()

    /*******************************************************************************
     * Setter for type declaration
     * time : O(1)
     * *****************************************************************************/  
    public void TypeDeclaration(TypeDeclNode node){
        _typeDeclaration=node;
    } // TypeDeclaration(TypeDeclNode)

    /*******************************************************************************
     * Setter for package declaration
     * time : O(1)
     * *****************************************************************************/  
    public void PackageDeclaration(PackageDeclNode node){
        _packagedeclaration=node;
    } // PackageDeclaration(PackageDeclNode)

    /*******************************************************************************
     * Setter for import declaration
     * time : O(1)
     * *****************************************************************************/  
    public void AddImportDeclaration(ImportDeclNode node){
        _importDeclarations.add(node);
    } // ImportDeclarations(ImportDeclarations)
} // class CompilationUnitNode