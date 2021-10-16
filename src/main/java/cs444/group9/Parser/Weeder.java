/*******************************************************************************
 * Weeder.java
 * 
 * A module implementing the Weeder.
 * ****************************************************************************/

package cs444.group9.Parser;
import cs444.group9.Filter.FilePathFilter;
import cs444.group9.Scanner.*;
import java.util.*;

public class Weeder {
    public class WeedingError extends Exception {}
    Node root;
    // modifiers of the root class/interface
    List<String> rootModifiers;
    // CLASS or INTERFACE (indicating the type of the root) or "" (if no class)
    String rootType;
    // name of class/interface
    String rootName;
    //name of file
    String _fileName;
    //explicit constructor flag
    boolean _hasExplicitConstructor;
    FilePathFilter _filePathFilter;

    /*******************************************************************************
     * Weeder Constructor
     * notes: applies path filter to fileName
     * time : O(|fileName|)
     * *****************************************************************************/ 
    public Weeder(Node Root,String fileName){
        root = Root;
        rootModifiers = new ArrayList<String>();
        rootType = "";
        rootName = "";
        _hasExplicitConstructor = false;
        _filePathFilter=new FilePathFilter();

        _fileName=_filePathFilter.filter(fileName);
    } // Weeder()

    /*******************************************************************************
     * Determines and sets the modifier list of the subtree of node
     * notes: if a duplicate is found or the set of modifiers does not contain 
     *            exactly one of {public, protected}, it throws a weeding error
     *        also updates rootType, rootModifiers appropriately
     * time : O(|parsetree| * #modifiers)
     * *****************************************************************************/ 
    void determineModifiers(Node node)throws WeedingError{
        List <Node> children = node.getChildren();

        // if not one of the specified nodes, recurse on the children
        if (!node.getSymbol().equals("Modifiers") ){
            ListIterator<Node> itr = children.listIterator();

            while(itr.hasNext()){
                determineModifiers(itr.next());
            } // while
            // if left child is "Modifiers" checks that its list of modifiers contains exactly
            // one of {public, protected}, and updates root info appropriately
            if (children.size() > 0 && children.get(0).getSymbol().equals("Modifiers")) {
                List <String> modifierList = children.get(0).getModifiers();
                boolean isPublic = modifierList.contains("PUBLIC");
                boolean isProtected = modifierList.contains("PROTECTED");
                if(!isPublic && !isProtected ) {
                    System.err.println("Private modifier not allowed.");
                    throw new WeedingError();
                } else if (isPublic && isProtected){
                    System.err.println("Cannot be both public and protected.");
                    throw new WeedingError();
                } // else if
                // if a class or interface declaration, updates root info
                if(node.getSymbol().equals("ClassDeclaration") || node.getSymbol().equals("InterfaceDeclaration")) {
                    if(node.getSymbol().equals("ClassDeclaration")) rootType = "CLASS";
                    else rootType = "INTERFACE";
                    rootModifiers = modifierList;
                    // node with the ID of the class
                    Node IDnode = children.get(2).getChildren().get(0);
                    rootName = IDnode.getLexeme();
                } // if
            } // if
        } else {
            // Modifiers -> Modifier Modifier ... Modifier
            // list of modifiers
            List<String> list = new ArrayList<String>();
            for (Node modifier : node.getChildren()) {
                // modifier keyword corresponding to modifier
                String modifierKeyword = modifier.getChildren().get(0).getSymbol();
                // if duplicate, throw error
                if (list.contains(modifierKeyword)) {
                    System.err.println("Duplicate modifiers: " + modifierKeyword);
                    throw new WeedingError();
                } // if
                list.add(modifierKeyword);
            } // for
            node.setModifiers(list);
        } // else
    } // determineModifiers()

    /*******************************************************************************
     * Checks that the rootModifiers are valid
     * notes: throws error if class is both abstract and final
     *        or if class is protected, static or native
     *        or if interface is protected, static, native or final
     * time : O(#rootModifiers)
     * *****************************************************************************/ 
    void rootModifiersCheck () throws WeedingError{
        if(rootType.equals("CLASS")){
            if(rootModifiers.contains("ABSTRACT") && rootModifiers.contains("FINAL")) {
                System.err.println("Class is both final and abstract.");
                throw new WeedingError();
            } // if
        } else { 
            // if interface
            if(rootModifiers.contains("FINAL")) {
                System.err.println("Interface is final.");
                throw new WeedingError();
            } // if
        } // else
        if (rootModifiers.contains("PROTECTED") || rootModifiers.contains("NATIVE")
                || rootModifiers.contains("STATIC")) {
            System.err.println("Class/interface cannot be protected, static or native.");
            throw new WeedingError();
        } // if
    } // rootModifiersCheck()

    /*******************************************************************************
     * Checks that the methodModifiers are valid
     * notes: Checks that:
     *        -a method has a body if and only if it is neither abstract nor native
     *        -an abstract method cannot be static or final
     *        -a static method cannot be final
     *        -a native method must be static
     *        -an abstract method cannot be present in a non-abstract class
     *        throws error if any of these does not hold
     * time : O(|parse tree| * #modifiers)
     * *****************************************************************************/ 
    void methodModifierCheck (Node node) throws WeedingError{
        // if node is not MethodDeclaration, recurse on children
        if(!node.getSymbol().equals("MethodDeclaration")){
            List<Node> children = node.getChildren();
            ListIterator<Node> itr = children.listIterator();
            while(itr.hasNext()) {
                methodModifierCheck(itr.next());
            } // while
        } else {
            // first child of MethodHeader
            Node modifiersNode = node.getChildren().get(0).getChildren().get(0);

            // check that first child of MethodBody (the 2nd child of node) is Block
            boolean hasMethodBody = node.getChildren().get(1).getChildren().get(0).getSymbol().equals("Block");

            List <String> modifierList = modifiersNode.getModifiers();
            // is the method abstract, native, static, final?
            boolean isAbstract = modifierList.contains("ABSTRACT");
            boolean isNative = modifierList.contains("NATIVE");
            boolean isFinal = modifierList.contains("FINAL");
            boolean isStatic = modifierList.contains( "STATIC");

            // determines the result and prints error message if needed
            if(hasMethodBody && (isAbstract || isNative)) {
                System.err.println("Method has body but is abstract/native.");
                node.printNodeToken();
                throw new WeedingError();
            } else if(!hasMethodBody && !isAbstract && !isNative) {
                System.err.println("Method has no body but is neither abstract nor native.");
                node.printNodeToken();
                throw new WeedingError();
            }  else if(isAbstract && (isStatic || isFinal)) {
                System.err.println("Method is abstract and static/final.");
                node.printNodeToken();
                throw new WeedingError();
            } else if(isStatic && isFinal) {
                System.err.println("Method is both static and final.");
                node.printNodeToken();
                throw new WeedingError();
            } else if(isNative && !isStatic) {
                System.err.println("Method is native but not static.");
                node.printNodeToken();
                throw new WeedingError();
            } else if (isAbstract && rootType.equals("CLASS") && !rootModifiers.contains("ABSTRACT")){
                System.err.println("Abstract Method in non-abstract class.");
                node.printNodeToken();
                throw new WeedingError();
            } // else if
        } // else
    } // methodModifierCheck()

    /*******************************************************************************
     * Checks that no modifier in notAllowed is a modifier of declType -> Modifiers ...
     * notes: declType is ConstructorDeclaration, InterfaceDeclaration, FieldDeclaration
     * time : O(|parse tree| * #modifiers)
     * *****************************************************************************/ 
    void templateModifierCheck (Node node, String declType, List<String> notAllowed) throws WeedingError {
        // if node is not declType, recursively checks on children
        if(!node.getSymbol().equals(declType)){
            List<Node> children = node.getChildren();
            ListIterator<Node> itr = children.listIterator();
            while(itr.hasNext()) {
                templateModifierCheck(itr.next(), declType,notAllowed);
            } // while
        } else {
            // first child of objectType
            Node modifiersNode = node.getChildren().get(0);
            // list of modifiers
            List <String> modifierList = modifiersNode.getModifiers();

            ListIterator<String> it = notAllowed.listIterator();
            while(it.hasNext()){
                String next = it.next();
                if(modifierList.contains(next)) {
                    System.err.println(declType + " may not be " + next);
                    node.printNodeToken();
                    throw new WeedingError();
                } // if
            } // while
        } // else
    } // templateModifierCheck()

    /*******************************************************************************
     * Checks that no interface method is final/static/native/protected
     * time : O(|parse tree| * #modifiers)
     * *****************************************************************************/ 
    void interfaceMethodCheck (Node node) throws WeedingError {
        if(rootType.equals("INTERFACE")){
            List<String> notAllowed = new ArrayList<String>();
            notAllowed.add("STATIC");
            notAllowed.add("FINAL");
            notAllowed.add("NATIVE");
            notAllowed.add("PROTECTED");
            templateModifierCheck(node, "MethodHeader", notAllowed);
        } // if
    } // interfaceMethodCheck()

    /*******************************************************************************
     * Checks that no field is abstract, native or final
     * time : O(|parse tree| * #modifiers)
     * *****************************************************************************/ 
    void fieldCheck (Node node) throws WeedingError {
        List<String> notAllowed = new ArrayList<String>();
        notAllowed.add("ABSTRACT");
        notAllowed.add("FINAL");
        notAllowed.add("NATIVE");
        templateModifierCheck(node, "FieldDeclaration", notAllowed);
    } // fieldCheck()

    /*******************************************************************************
     * Checks that no constructor is abstract, native, static or final
     * time : O(|parse tree| * #modifiers)
     * *****************************************************************************/ 
    void constructorCheck (Node node) throws WeedingError {
        List<String> notAllowed = new ArrayList<String>();
        notAllowed.add("ABSTRACT");
        notAllowed.add("STATIC");
        notAllowed.add("FINAL");
        notAllowed.add("NATIVE");
        templateModifierCheck(node, "ConstructorDeclaration", notAllowed);
    } // constructorCheck()

    /*******************************************************************************
     * Checks that 2147483648 occurs only if the previous minus was a unary minus
     * time : O(|parse tree|)
     * *****************************************************************************/ 
    void largeIntCheck (Node node) throws WeedingError {
        List<Node> children = node.getChildren();
        if(node.getSymbol().equals("AdditiveExpression") && children.size() == 3
                && children.get(1).getSymbol().equals("MINUS")){
            // if AdditiveExpression -> AdditiveExpression MINUS MultiplicativeExpression
            // checks that the next number is not 2147483648

            // traverses the subtree of MultiplicativeExpression until it finds the leftmost leaf
            Node curr = children.get(2);
            while(true){
                List <Node> currChildren = curr.getChildren();
                if(currChildren.size() == 0) break;
                curr = currChildren.get(0);
            } // while
            if(curr.getSymbol().equals("IntegerLiteral") && curr.getLexeme().equals("2147483648")) {
                System.err.println("Integer out of range");
                curr.printNodeToken();
                throw new WeedingError();
            } // if
        } // if
        // recursively checks on children
        ListIterator<Node> itr = children.listIterator();
        while(itr.hasNext()) {
            largeIntCheck (itr.next());
        } // while
    } // largeIntCheck()

    /*******************************************************************************
     * Checks that classes are in the same file name as the class name
     * time : O(|_fileName|)
     * *****************************************************************************/ 
    public void baseNameCheck() throws WeedingError{
        if(rootName.equals("")){
            return;
        } // if
        String fileNameNoSuffix=_filePathFilter.getFileNameWithoutSuffix(_fileName);
        if (!rootName.equals(fileNameNoSuffix)){
            System.err.println("Class: " + rootName + " must be in the same file as the class name");
            System.err.println("Instead found in file: " + _fileName);
            throw new WeedingError();
        } // if
    } // baseNameCheck()

    /*******************************************************************************
     * Checks that a class has an explicit constructor if not an interface
     * time : O(|parse tree|)
     * *****************************************************************************/ 
    public void explicitConstructorCheck(Node node, boolean istopNode) throws WeedingError{
        if(rootType.equals("INTERFACE") || _hasExplicitConstructor){
            return;
        } // if
        if (node.getSymbol().equals("ConstructorDeclaration")){
            _hasExplicitConstructor = true;
            return;
        } // if
        for(Node child: node.getChildren()){
            explicitConstructorCheck(child,false);
        } // for
        if (istopNode && !_hasExplicitConstructor){
            System.err.println("missing explicit constructor");
            throw new WeedingError();
        } // if
    } // explicitConstructCheck()

    // name node that will become the child of castCheck if current child is Expression
    private Node newNameChild;
    /*******************************************************************************
     * Checks that the expression inside each cast is a name node, and in this case
     *     replaces the expression node by the name node
     * time : O(|parse tree|)
     * *****************************************************************************/ 
    public void castCheck(Node node, boolean insideCheck) throws WeedingError{
        if (insideCheck){
            if (node.getChildren().size() != 1){
                System.err.println("Illegal cast to expression");
                throw new WeedingError();
            } // if
            if (node.getSymbol().equals("UnaryExpressionNotMinus")){
                Node firstChild=node.getChildren().get(0);
                if (firstChild.getSymbol().equals("PostfixExpression")){
                    Node firstGrandChild=firstChild.getChildren().get(0);
                    if (!firstGrandChild.getSymbol().equals("Name")){
                        System.err.println("Illegal cast to expression");
                        throw new WeedingError();
                    } else {
                        newNameChild = firstGrandChild;
                        insideCheck = false;
                    } // else
                } else {
                    System.err.println("Illegal cast to expression");
                    throw new WeedingError();
                } // else
            } // if
        } else if (node.getSymbol().equals("CastExpression")
                && node.getChildren().get(1).getSymbol().equals("Expression")) {
            castCheck(node.getChildren().get(1),true);
            if(newNameChild != null) {
                node.getChildren().remove(1);
                node.getChildren().add(1,newNameChild);
            } // if
        } // else if

        for(Node child: node.getChildren()){
            castCheck(child, insideCheck);
        } // for
    } // castCheck()

    /*******************************************************************************
     * Performs the weeding checks
     * time : O(|parse tree| * #modifiers)
     * *****************************************************************************/
    public void performWeeding() throws Weeder.WeedingError {
        root.flattenTree();
        determineModifiers(root);

        // if there is a class/interface, perform weeding
        if(!rootType.equals("")) {
            rootModifiersCheck();
            methodModifierCheck(root);
            interfaceMethodCheck(root);
            fieldCheck(root);
            constructorCheck(root);
            largeIntCheck(root);
            baseNameCheck();
            explicitConstructorCheck(root, true);
            castCheck(root, false);
        } // if
    } // performWeeding()
} // class Weeder