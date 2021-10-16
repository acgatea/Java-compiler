/*******************************************************************************
 * Environment.java
 * 
 * A module implementing Environment for compiler.
 * ****************************************************************************/
package cs444.group9.AST.Node.Environments;

import java.util.*;
import cs444.group9.AST.Node.NameNode;
import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.ASTTree;

public class Environment {
    // name of current scope (e.g. method name in method environment)
    // *** NOT TO BE USED FOR TYPE LINKING (use the pairs for this)
    private EnvironmentPair _scopePair;

    // pairs of (name, Decl ptr) for objects in immediate scope
    // Note: pair for the scopeName is found in _parent's pairs
    private List <EnvironmentPair> _pairs;

    // info for class/interface environment
    private List<NameNode> _singleTypeImports;
    private List<ASTNode> _onDemandTypeImports;
    private NameNode _package;
    private ASTTree _root;

    private Environment _parent;    // ptr to parent environment
    private List<Environment> _children; // ptr to children environments

    //constants
    public final static String GLOBAL_SCOPE_NAME="##GLOBAL##";

    /*******************************************************************************
     * Environment constructor
     * time : O(1)
     * *****************************************************************************/
    public Environment(EnvironmentPair scopePair){
        _scopePair = scopePair;
        _children = new ArrayList <Environment>();
        _pairs = new ArrayList<EnvironmentPair>();
        _singleTypeImports = new ArrayList<>();
        _onDemandTypeImports = new ArrayList<>();
    } // Environment()

    /*******************************************************************************
     * Accessor methods
     * *****************************************************************************/ 
    public void setParent(Environment e) { _parent = e;}
    public Environment getParent() { return _parent;}
    public void addChild(Environment child) { _children.add(child);}
    public List<Environment> getChildren() { return _children;}
    public EnvironmentPair getScopePair(){ return _scopePair;}

    public void addPair(EnvironmentPair e){
        _pairs.add(e);
    }
    public List<EnvironmentPair> getPairs(){
        return _pairs;
    }
    public void addSingleTypeImport(NameNode n){_singleTypeImports.add(n);}
    public void addOnDemandTypeImport(ASTNode n){_onDemandTypeImports.add(n);}
    public List<NameNode> getSingleTypeImports() {return _singleTypeImports;}
    public List<ASTNode> getOnDemandTypeImports() {return _onDemandTypeImports;}
    public void setPackage(NameNode n) {_package = n;}
    public NameNode getPackage(){return _package;}
    public void setRoot(ASTTree root) {_root = root;}

    /*******************************************************************************
     * Non-Accessor methods
     * *****************************************************************************/ 

     /*******************************************************************************
     * Finds root
     * time : O(strlen(package))
     * *****************************************************************************/
    public ASTTree getRoot(){
        Environment currEnv = this;
        while(currEnv.getPackage() == null) {
            currEnv = currEnv.getParent();
        } // while
        return currEnv._root;
    } // getRoot()

    /*******************************************************************************
     * Prints Environment
     * time: O(|environment|)
     * *****************************************************************************/
    public void printEnv(){
        System.out.println(" ENVIRONMENT of " + _scopePair);
        if(_package != null){
            System.out.println(" PACKAGE: " + _package.toString());
            for(NameNode n: _singleTypeImports) System.out.println("SINGLE-TYPE-IMPORT: " + n.toString());
            for(ASTNode n: _onDemandTypeImports)
                System.out.println("ON-DEMAND-TYPE-IMPORT: "+n.getEnv().getScopePair().getName()+" in package "+ n.getEnv().getPackage().toString());
        } // for
        for (EnvironmentPair p : _pairs) {
            System.out.println("envPair: " + p.getName() + " in node " + p.getDeclaration().get_parseTreeNode().getSymbol());
        } // for
        System.out.println("---------------------");
        for ( Environment e :_children){
            e.printEnv();
        } // for
    } // printEnv()
} // class Environment
