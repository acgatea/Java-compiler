/*******************************************************************************
 * ASTTree.java
 * 
 * A module implementing the AST Tree.
 * ****************************************************************************/

package cs444.group9.AST;

import cs444.group9.AST.Node.Body.CompilationUnitNode;
import cs444.group9.AST.Node.Body.FormalParameterNode;
import cs444.group9.AST.Node.Body.MethodHeaderNode;
import cs444.group9.AST.Node.Declaration.*;
import cs444.group9.AST.Node.Environments.EnvironmentPair;
import cs444.group9.AST.Node.Environments.FieldData;
import cs444.group9.AST.Node.Environments.MethodData;
import cs444.group9.AST.Node.ModifierNode;
import cs444.group9.AST.Node.NameNode;
import cs444.group9.AST.Node.Type.BasicTypeNode;
import cs444.group9.AST.Node.Type.ClassOrInterfaceTypeNode;
import cs444.group9.AST.Node.Type.TypeNode;
import cs444.group9.AST.Visitor.ASTVisitor;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.Parser.Node;
import cs444.group9.AST.Node.Environments.Environment;
import java.util.*;

public class ASTTree {
    private Node _parseTreeroot;
    private CompilationUnitNode _root;

    private List<FieldData> _fieldsDeclared;    // data for each field declared
    private List<MethodData> _methodsDeclared;  // data for each method declared
    private List<MethodData> _constructorsDeclared;  // data for each constructor declared
    private List<FieldData> _fieldsInherited;   // data for each field inherted
    private List<MethodData> _methodsInherited; // data for each method inherited
    private List< Vector<MethodData> > _replaceMethodSet;  // list of (m, m') pairs where m replaces m'
    private List<ASTTree> _super;               // set of direct super classes/interfaces
    private boolean _isClass;                   // is this a class?
    private boolean _isInterface;               // is this an interface?
    private boolean _isRootAbstract;            // is class/interface abstract?
    private boolean _isRootFinal;               // is class/interface final?
    private boolean _isEmpty;                   // is the file empty (no import, package or class/interface)
    private ASTTree _superClass;
    private List<ASTTree> _AllSuper;            // all super types

    // the implicit 0 arg constructor from the super class (not null for all classes except Object)
    private MethodData _implicitSuperConstructor;

    /*******************************************************************************
     * ASTTree constructor
     * time : O(1)
     * *****************************************************************************/
    public ASTTree(Node node){
        _super = new ArrayList<>();
        _AllSuper = new ArrayList<>();
        _fieldsDeclared = new ArrayList<>();
        _methodsDeclared = new ArrayList<>();
        _constructorsDeclared = new ArrayList<>();
        _fieldsInherited = new ArrayList<>();
        _methodsInherited = new ArrayList<>();
        _replaceMethodSet = new ArrayList<>();
        _parseTreeroot=node;
        _isEmpty = false;
        _superClass = null;
    } // ASTTree()

    /*******************************************************************************
     * Getter for root
     * time : O(1)
     * *****************************************************************************/
    public CompilationUnitNode getRoot(){
        return _root;
    } // getRoot()

    /*******************************************************************************
     * Getter for parse tree root
     * time : O(1)
     * *****************************************************************************/    
    public Node getNodeRoot(){
        return _parseTreeroot;
    } // getNodeRoot()

    /*******************************************************************************
     * Setter for parse tree root
     * time : O(1)
     * *****************************************************************************/    
    public void setParseTreeRoot(Node node){
        _parseTreeroot=node;
    } // setParseTreeRoot()


    /*******************************************************************************
     * Getter for super
     * time : O(1)
     * *****************************************************************************/ 
    public List<ASTTree> getSuper() {
        return _super;
    } // getSuper()


    /*******************************************************************************
     * Getter for list of supers
     * time : O(1)
     * *****************************************************************************/ 
    public List<ASTTree> getAllSuper() {
        return _AllSuper;
    } // getAllSuper()

    /*******************************************************************************
     * Getter for super class
     * time : O(1)
     * *****************************************************************************/ 
    public ASTTree getSuperClass(){
        return _superClass;
    } // getSuperClass()

    /*******************************************************************************
     * Getter for declared fields
     * time : O(1)
     * *****************************************************************************/     
    public List<FieldData> getFieldsDeclared(){
        return _fieldsDeclared;
    } // getFieldsDeclared()

    /*******************************************************************************
     * Getter for inherited fields
     * time : O(1)
     * *****************************************************************************/ 
    public List<FieldData> getFieldsInherited(){
        return _fieldsInherited;
    } // getFieldsInherited()

    /*******************************************************************************
     * Getter for inherited methods
     * time : O(1)
     * *****************************************************************************/ 
    public List<MethodData> getMethodsInherited(){
        return _methodsInherited;
    } // getMethodsInherited()
    
    /*******************************************************************************
     * Getter for declared methods
     * time : O(1)
     * *****************************************************************************/ 
    public List<MethodData> getMethodsDeclared(){
        return _methodsDeclared;
    } // getMethodsDeclared()
    
    /*******************************************************************************
     * Getter for declared constructors
     * time : O(1)
     * *****************************************************************************/ 
    public List<MethodData> getConstructorsDeclared(){
        return _constructorsDeclared;
    } // getConstructorsDeclared()
    
    /*******************************************************************************
     * Getter for replaced methods
     * time : O(1)
     * *****************************************************************************/ 
    public List< Vector<MethodData> > getReplaceMethods(){
        return _replaceMethodSet;
    } // getReplaceMethods()
    
    /*******************************************************************************
     * Getter for implicit super constructor
     * time : O(1)
     * *****************************************************************************/ 
    public MethodData getImplicitSuperConstructor(){
        return _implicitSuperConstructor;
    } // getImplicitSuperConstructor()
    
    /*******************************************************************************
     * Setter for implicit super constructor
     * time : O(1)
     * *****************************************************************************/ 
    public void setImplicitSuperConstructor(MethodData m){
        _implicitSuperConstructor = m;
    } // setImplicitSuperConstructor()

    /*******************************************************************************
     * Add replace method
     * time : O(1)
     * *****************************************************************************/ 
    public void addReplaceMethod(Vector<MethodData> vector) {
        _replaceMethodSet.add(vector);
    } // addReplaceMethod()
    
    /*******************************************************************************
     * Add inherited field
     * time : O(1)
     * *****************************************************************************/ 
    public void addInheritedField(FieldData f) {
        _fieldsInherited.add(f);
    } // addInheritedField
    
    /*******************************************************************************
     * Add inherited method
     * time : O(1)
     * *****************************************************************************/ 
    public void addInheritedMethod(MethodData m) {
        _methodsInherited.add(m);
    } // addInheritedMethod()

    /*******************************************************************************
     * Predicate for class
     * time : O(1)
     * *****************************************************************************/ 
    public boolean isClass(){
        return _isClass;
    } // isClass()

    /*******************************************************************************
     * Predicate for interface
     * time : O(1)
     * *****************************************************************************/ 
    public boolean isInterface(){
        return _isInterface;
    } // isInterface()

    /*******************************************************************************
     * Predicate for abstract root
     * time : O(1)
     * *****************************************************************************/ 
    public boolean isRootAbstract(){
        return _isRootAbstract;
    } // isRootAbstract()

    /*******************************************************************************
     * Predicate for final root
     * time : O(1)
     * *****************************************************************************/ 
    public boolean isRootFinal(){
        return _isRootFinal;
    } // isRootFinal()

    /*******************************************************************************
     * Predicate for empty file
     * time : O(1)
     * *****************************************************************************/ 
    public boolean isFileEmpty(){
        return _isEmpty;
    } // isFileEmpty()

    /**************************************************************************************************
    ************************************ NON-ACCESS METHODS: ******************************************
    ***************************************************************************************************/

    /*******************************************************************************
     * Determines if t1 is a subtype of t2
     * time : O(#t1 supers)
     * *****************************************************************************/ 
    public static boolean isSubTypeOf(ASTTree t1, ASTTree t2){
        if(t1 == t2 ) {
            return true;
        } // if
        Environment t2Env = t2.getRoot().getEnv();
        if(t2Env.getScopePair().getName().equals("Object") && t2Env.getPackage().toString().equals("java.lang")){
            return true;
        } // if
        for(ASTTree superType: t1.getSuper()){
            if(isSubTypeOf(superType, t2)) return true;
        } // for
        return false;
    } // isSubTypeOf()

    /*******************************************************************************
     * Wrapper function call to isSubTypeOf(ASTTree t1, ASTTree t2)
     * time : O(#c1.root supers)
     * *****************************************************************************/ 
    public static boolean isSubTypeOf(ClassOrInterfaceTypeNode c1,ClassOrInterfaceTypeNode c2){
        return isSubTypeOf(c1.getEnv().getRoot(),c2.getEnv().getRoot());
    } // isSubTypeOf()

    /*******************************************************************************
     * Build the AST tree + environment (with an empty root globalEnv)
     * *****************************************************************************/ 
    public void buildtree(Environment globalEnv) throws iVisitor.ASTException {
        ASTVisitor astVisitor = new ASTVisitor();

        CompilationUnitNode compilationUnitNode = new CompilationUnitNode(_parseTreeroot);
        _root = compilationUnitNode;
        compilationUnitNode.UpdateEnv(globalEnv);
        compilationUnitNode.accept(astVisitor);
        if(compilationUnitNode.ImportDeclarations() == null && compilationUnitNode.TypeDeclaration() == null
                && compilationUnitNode.PackageDeclaration() == null) _isEmpty = true;
        else globalEnv.getChildren().get(globalEnv.getChildren().size()-1).setRoot(this);
    } // buildtree()

    /*******************************************************************************
     * Determines if m is a constructor or method declared
     * notes: forMethod = true if this checks signatures of methods
     *                    false for constructors
     * time : O(#methods/constructors declared)
     * *****************************************************************************/ 
    public boolean isSignatureInThisClass (MethodData m, boolean forMethod) {
        if (forMethod) {
            for (MethodData m2 : _methodsDeclared) {
                if (m2.isSameSignature(m)) return true;
            } // for
        } else {
            for (MethodData m2 : _constructorsDeclared) {
                if (m2.isSameSignature(m)) return true;
            } // for
        } // else
        return false;
    } // isSignatureInThisClass()

    /*******************************************************************************
     * Computes the DECLARE set of current tree
     * *****************************************************************************/ 
    public void computeDeclare() throws iVisitor.ASTException {
        // determine the environment of this tree
        // and sets _isClass, _isInterface, _isRootAbstract, _isRootFinal
        Environment currEnv;
        _isRootFinal = false;
        _isRootAbstract = false;
        _isClass = false;
        _isInterface = false;
        if(_root.TypeDeclaration() == null) return; // no declarations
        if(_root.TypeDeclaration().InterfaceDeclaration() != null) {
            currEnv = _root.TypeDeclaration().InterfaceDeclaration().getEnv();
            _isInterface = true;
            // interfaces are abstract implicitly, and are never final
            _isRootAbstract = true;
        } else {
            currEnv = _root.TypeDeclaration().ClassDeclaration().getEnv();
            _isClass = true;
            for(ModifierNode m : _root.TypeDeclaration().ClassDeclaration().Modifiers()){
                if(m.Modifier().equals("ABSTRACT")) _isRootAbstract = true;
                else if(m.Modifier().equals("FINAL")) _isRootFinal = true;
            } // for
        } // else

        // check each pair in the class/interface environment
        for (EnvironmentPair p : currEnv.getPairs()) {
            // for every pair in the interface/class environment
            if (p.getDeclaration() instanceof MethodHeaderNode) {
                // add a method data for each method header found in this environment
                MethodData m = new MethodData((MethodHeaderNode) p.getDeclaration());

                // all interface methods are abstract
                if(_isInterface) m.setAbstract(true);
                // check that no two methods have the same signature
                if (isSignatureInThisClass(m, true)) {
                    System.out.println("Two methods have the same signature.");
                    throw new iVisitor.EnvironmentBuildingException();
                } // if
                m.GeneralNativeMethodCheck();
                //System.out.println(" Add to methodsDeclared:" + m.getMethodName());
                _methodsDeclared.add(m);
                ((MethodHeaderNode) p.getDeclaration()).setMethodData(m);
            } else if (p.getDeclaration() instanceof ConstructorDeclarationNode) {
                // add a method data for each constructor header found in this environment
                MethodData m = new MethodData((ConstructorDeclarationNode) p.getDeclaration());

                // check that no two methods have the same signature
                if (isSignatureInThisClass(m, false)) {
                    System.err.println("Two constructors have the same signature.");
                    throw new iVisitor.EnvironmentBuildingException();
                } else if(!m.getMethodName().equals(currEnv.getScopePair().getName())){
                    System.err.println("Constructor does not have the same name as the enclosing file.");
                    throw new iVisitor.EnvironmentBuildingException();
                } // else if

                _constructorsDeclared.add(m);
                ((ConstructorDeclarationNode) p.getDeclaration()).setMethodData(m);
            } else if (p.getDeclaration() instanceof FieldDeclNode) {
                // add a field data for each field found in this environment
                FieldData f = new FieldData((FieldDeclNode) p.getDeclaration());

                // if any two fields declared have the same name, error
                for (FieldData data : _fieldsDeclared) {
                    if (data.getFieldName().equals(f.getFieldName())) {
                        System.out.println("Field name used twice in the same class/interface.");
                        throw new iVisitor.EnvironmentBuildingException();
                    } // if
                } // for
                _fieldsDeclared.add(f);
                ((FieldDeclNode) p.getDeclaration()).setFieldData(f);
            } // else if
        } // for
    } // computeDeclare()

    /*******************************************************************************
     * Searches trees for ASTTree corresponding to name
     * notes: throws exception if not found
     * time: O(#trees)
     * *****************************************************************************/ 
    private ASTTree getSuperTree(NameNode name, List<ASTTree> trees) throws iVisitor.ASTException{
        // looks in every tree with nonempty root
        for(ASTTree t: trees){
            if(t.getRoot() != null) {
                if(t.getRoot().TypeDeclaration() != null) {
                    // different check based on if t is a class or an interface
                    TypeDeclNode typeNode = t.getRoot().TypeDeclaration();
                    if(typeNode.ClassDeclaration()!= null) {
                        if (name.getLinkedDeclaration() == typeNode.ClassDeclaration()){
                            return t;
                        } // if
                    } else if(typeNode.InterfaceDeclaration()!= null) {
                        if (name.getLinkedDeclaration() == typeNode.InterfaceDeclaration()){
                            return t;
                        } // if
                    } // else if
                } // if
            } // if
        } // for
        // should always find a corresponding tree
        throw new Error();
    } // getSuperTree()

    /*******************************************************************************
     * Checks that the list contains no duplicates
     * time: O(|list|^2)
     * *****************************************************************************/ 
    private void duplicateInterfaceCheck(List <NameNode> list)throws iVisitor.ASTException{
        int listSize = list.size();
        for (int i = 0; i < listSize; ++i) {
            NameNode interfaceType = list.get(i);
            for (int j = 0; j < i; ++j) {
                NameNode secondInterfaceType = list.get(j);
                if (interfaceType.getLinkedDeclaration() == secondInterfaceType.getLinkedDeclaration()) {
                    System.out.println("Duplicate interface in extends/implements list.");
                    throw new iVisitor.ASTException();
                } // if
            } // for
        } // for
    } // duplicateInterfaceCheck()

    /*******************************************************************************
     * Computer SUPER set of current tree
     * notes: trees is the list of all trees
     *        checks that there are no duplicates in extends/implements lists and
     *          that class does not extend final class
     * *****************************************************************************/ 
    public void computeSuper(List<ASTTree> trees) throws iVisitor.ASTException {
        // determine root info
        if(_isClass) {
            ClassDeclNode node = _root.TypeDeclaration().ClassDeclaration() ;
            NameNode classType = node.ClassType();

            // check there are no duplicates in the interfaceList
            duplicateInterfaceCheck(node.Interfaces());

            // set super
            for (NameNode n : node.Interfaces()) {
                ASTTree superTree = getSuperTree(n, trees);
                if(! superTree._isInterface) {
                    System.out.println("Class cannot implement a class.");
                    throw new iVisitor.ASTException();
                } // if
                _super.add(superTree);
            } // for
            if (classType != null) {
                ASTTree superTree = getSuperTree(classType, trees);
                if(! superTree._isClass) {
                    System.out.println("Class cannot extend interface.");
                    throw new iVisitor.ASTException();
                } // if
                ClassDeclNode classNode = (ClassDeclNode) classType.getLinkedDeclaration();
                for (ModifierNode m : classNode.Modifiers()) {
                    if (m.Modifier().equals("FINAL")) {
                        System.out.println("Class's extends type must not be a final class.");
                        throw new iVisitor.ASTException();
                    } // if
                } // for
                _super.add(superTree);
                _superClass = superTree;
            } // if
        } else if(_isInterface){
            // if is interface
            InterfaceDeclNode node = _root.TypeDeclaration().InterfaceDeclaration();
            // check there are no duplicates in the extends list
            duplicateInterfaceCheck(node.ExtendsInterfaces());

              // set super
              for (NameNode n : node.ExtendsInterfaces()) {
                  ASTTree superTree = getSuperTree(n, trees);

                  if(! superTree._isInterface && !n.toString().equals("java.lang.Object")) {
                      System.out.println("Interface cannot extend a class.");
                      throw new iVisitor.ASTException();
                  } // if
                  _super.add(superTree);
              } // for
        } // else if
    } // computeSuper()

    /*******************************************************************************
     * Computes SUPER set of current and all super trees
     * *****************************************************************************/ 
    public void computeAllSuper(){
        if(!_AllSuper.isEmpty()) return;
        for(ASTTree t: _super){
            t.computeAllSuper();
            List<ASTTree> computedSuper = t.getAllSuper();
            for(ASTTree superTree: computedSuper){
                if(!_AllSuper.contains(superTree)) _AllSuper.add(superTree);
            } // for
        } // for
        _AllSuper.add(this);
    } // computeAllSuper()

    /*******************************************************************************
     * Computes fields declared in all supers
     * time: O(#fields declared or inherited)
     * *****************************************************************************/ 
    public List<FieldData> getSuperFieldsDeclared(){
        List<FieldData> accumulated = new ArrayList<>();
        if(_superClass != null) { 
            //add inherited fields
            accumulated.addAll(_superClass.getSuperFieldsDeclared());
        } // if
        //add declared fields
        List<FieldData> toAdd=new ArrayList<>();
        int numFieldsDeclared = _fieldsDeclared.size();
        for(int i = 0; i < numFieldsDeclared; ++i){
            if (_fieldsDeclared.get(i).isStatic()){
                continue;
            } // if
            toAdd.add(_fieldsDeclared.get(i));
        } // for
        accumulated.addAll(toAdd);
        return accumulated;
    } // getSuperFieldsDeclared()
} // class ASTTree
