/*******************************************************************************
 * HierarchyChecker.java
 * 
 * A module implementing the hierarchy checker.
 * ****************************************************************************/


package cs444.group9.AST;

import cs444.group9.AST.Node.Declaration.ClassDeclNode;
import cs444.group9.AST.Node.Environments.MethodData;
import cs444.group9.AST.Node.Environments.FieldData;


import java.util.*;

public class HierarchyChecker {
    public class HierarchyCheckException extends Exception{}

    /*******************************************************************************
     * Helper recursive method for cycleCheck
     * *****************************************************************************/
    private void cycleVisit(ASTTree t, HashMap <ASTTree, Integer> colour)
        throws HierarchyCheckException{
        // t is currently being processed
        colour.replace(t, 0, 1);
        for(ASTTree S: t.getSuper()){
            if(colour.get(S) == 0){
                cycleVisit(S, colour);
            } // if
            if(colour.get(S) == 1) {
                // found cycle
                System.err.println("Hierarchy has a cycle.");
                throw new HierarchyCheckException();
            } // if
        } // for
        colour.replace(t, 1, 2);
    } // cycleVisit()

    /*******************************************************************************
     * Checks there are no cycles in the hierarchy
     * *****************************************************************************/
    public void cycleCheck(List<ASTTree> trees) throws HierarchyCheckException {
        // gives each tree a colour; 0 if not found yet, 1 if currently being
        // processed and 2 if already done
        HashMap <ASTTree, Integer> colour = new HashMap<ASTTree, Integer>();
        // all trees are initially 0
        for(ASTTree t: trees){
            colour.put(t, 0);
        } // for
        // visit all trees
        for(ASTTree t: trees){
            if(colour.get(t) == 0){
                cycleVisit(t, colour);
            } // if
        } // for
    } // cycleCheck()

    /*******************************************************************************
     * Replaces method m with m2
     * notes: rule is 1 or 2 (for rule 2, check that a concrete method replacing 
     *              an abstract method is not static)
     *        if m, m2 have the same signature and are not in Replace(t),
     *              then either throws exception if return-Types don't match or 
     *              adds (m, m2) to Replace(t) otherwise
     * *****************************************************************************/
    public void performReplaceMethod(MethodData m, MethodData m2, ASTTree t, int rule) throws HierarchyCheckException {
        if(m.isSameSignature(m2)) {
            // current REPLACE set
            List<Vector<MethodData> > currReplace = t.getReplaceMethods();
            for (Vector<MethodData> v : currReplace) {
                // if (m,m2) are already in Replace(t), there is nothing to do
                if (v.get(0) == m && v.get(1) == m2) {
                    return;
                } // if
            } // for
            // if return types don't match, error
            if ((m.isVoid() != m2.isVoid()) || (!m.isVoid() && !m.getReturnType().isSameType(m2.getReturnType()))) {
                System.err.println("Return types of overriding methods " + m.getMethodName() + " do not match.");
                throw new HierarchyCheckException();
            } // if
            // if static/non-static modifier does not match, error
            if (!m.isStatic() && m2.isStatic()) {
                System.err.println("A non-static method is overriding a static method.");
                throw new HierarchyCheckException();
            } // if
            if (m.isStatic() && !m2.isStatic()) {
                System.err.println("A static method is overriding/hiding a non-static method.");
                throw new HierarchyCheckException();
            } // if
            // if m2 is public but m is not, error
            if (m2.isPublic() && !m.isPublic()) {
                System.err.println("A non-public method is overriding a public method.");
                throw new HierarchyCheckException();
            } // if
            // if m2 is public but m is not, error
            if (m2.isFinal()) {
                System.err.println("A final method cannot be overriden.");
                throw new HierarchyCheckException();
            } // if
            if(rule == 2){
                if(m.isStatic()){
                    System.err.println("The concrete method replacing an abstract method is static.");
                    throw new HierarchyCheckException();
                } // if
            } // if
            // if not already in currReplace, adds it
            Vector<MethodData> vector = new Vector<>();
            vector.add(m);
            vector.add(m2);
            t.addReplaceMethod(vector);
        } // if
    } // performReplaceMethod

    /*******************************************************************************
     * Computes the REPLACE set of methods
     * *****************************************************************************/
    public void computeReplaceMethods (List<ASTTree> trees) throws HierarchyCheckException{int y = 0;
        for(ASTTree t: trees) {
            // replace rule #1
            for(MethodData m: t.getMethodsDeclared()){
                for(ASTTree S: t.getSuper()){
                    for(MethodData m2: S.getMethodsDeclared() ){
                        performReplaceMethod(m, m2, t, 1);
                    } // for
                    for(MethodData m2: S.getMethodsInherited() ){
                        performReplaceMethod(m, m2, t, 1);
                    } // for
                } // for
            } // for
            // replace rule #2 (concrete replaces all abstract)
            for(ASTTree S1: t.getSuper()){
                for(ASTTree S2: t.getSuper()){
                    for(MethodData m: S1.getMethodsDeclared()){
                        // if m1 is abstract or is declared in t, do nothing
                        if(m.isAbstract() || t.isSignatureInThisClass(m, true)) continue;
                        for(MethodData m2: S2.getMethodsDeclared()){
                            // if m2 is not abstract do nothing
                            if(! m2.isAbstract()) continue;
                            performReplaceMethod(m, m2, t,2);
                        } // for
                        for(MethodData m2: S2.getMethodsInherited()){
                            // if m2 is not abstract do nothing
                            if(! m2.isAbstract()) continue;
                            performReplaceMethod(m, m2, t,2);
                        } // for
                    } // for
                    for(MethodData m: S1.getMethodsInherited()){
                        // if m1 is abstract or is declared in t, do nothing
                        if(m.isAbstract() || t.isSignatureInThisClass(m, true)) continue;
                        for(MethodData m2: S2.getMethodsDeclared()){
                            // if m2 is not abstract do nothing
                            if(! m2.isAbstract()) continue;
                            performReplaceMethod(m, m2, t,2);
                        } // for
                        for(MethodData m2: S2.getMethodsInherited()){
                            // if m2 is not abstract do nothing
                            if(! m2.isAbstract()) continue;
                            performReplaceMethod(m, m2, t,2);
                        } // for
                    } // for
                } // for
            } // for
        } // for
    } // computeReplaceMethods()

    /*******************************************************************************
     * Inherits field f
     * notes: if f is not in CONTAINS(t) and f is inherited by t, 
     *              adds it to fieldsInherited and returns true
     *        else returns false
     * *****************************************************************************/
    public boolean performInheritField(FieldData f, ASTTree t) throws HierarchyCheckException {
        // checks if f is in t
        for(FieldData f2: t.getFieldsDeclared()){
            // if f.name == f2.name then f2 shadows f (do nothing)
            if(f.getFieldName().equals(f2.getFieldName())) return false;
        } // for
        // if f is not declared in t, checks if f is in fieldsInherited
        for(FieldData f2: t.getFieldsInherited()){
            // if already added to inherited fields, do nothing
            if(f == f2) return false;
        } // for
        // otherwise adds f to fieldsInherited
        t.addInheritedField(f);
        return true;
    } // performInheritField()


    /*******************************************************************************
     * Computes inherited fields
     * *****************************************************************************/
    public int computeInheritFields (List<ASTTree> trees) throws HierarchyCheckException{
        for(ASTTree t: trees) {
            // interfaces do not inherit fields
            if(!t.isClass()) continue;
            for(ASTTree S: t.getSuper()){
                // classes do not inherit fields from interfaces (they have no fields)
                if(!S.isClass()) continue;

                // if any new field is found, adds it and starts computing again
                for(FieldData f: S.getFieldsDeclared()){
                    if(performInheritField(f, t)) return 1;
                } // for
                for(FieldData f: S.getFieldsInherited()){
                    if(performInheritField(f, t)) return 1;
                } // for
            } // for
        } // for
        return 0;
    } // computeInheritFields()

    /*******************************************************************************
     * Inherits method m
     * notes: if f is not in t and f is inherited by t, adds it to methodsInherited 
     *              and returns true
     *        else returns false
     *        isClassMethod = true if m is in class, false if m is in interface
     *        also checks that if m is abstract, it is abstract in all superclasses 
     *              (otherwise throws exception)
     * *****************************************************************************/
    public boolean performInheritMethod(MethodData m, ASTTree t) throws HierarchyCheckException {
        // if signature of m is in this class, do nothing (it is an override)
        if(t.isSignatureInThisClass(m, true)) {
            return false;
        } // if

        // if m is not declared in t, checks if m is in methodsInherited
        for(MethodData m2: t.getMethodsInherited()){
            // if already added to inherited methods, do nothing
            if(m == m2) return false;
        } // for
        // if m is abstract, checks if it is abstract in all superclasses (if not
        // then abstract method is not inherited)
        if(m.isAbstract()){
            for(ASTTree S: t.getSuper()){
                if(! S.isClass()) continue;
                for(MethodData m2: S.getMethodsDeclared()){
                    if(m.isSameSignature(m2)){
                        if(! m2.isAbstract()){
                            return false;
                        } // if
                    } // if
                } // for
                for(MethodData m2: S.getMethodsInherited()){
                    if(m.isSameSignature(m2)){
                        if(! m2.isAbstract()){
                            return false;
                        } // if
                    } // if
                } // for
            } // for
        } // if
        // otherwise adds f to methodInherited
        t.addInheritedMethod(m);
        return true;
    } // performInheritMethod()

    /*******************************************************************************
     * Computes the INHERIT set of all trees
     * *****************************************************************************/
    public int computeInheritMethods (List<ASTTree> trees) throws HierarchyCheckException{
        for(ASTTree t: trees) {
            for(ASTTree S: t.getSuper()){
                // if any new method is found, adds it and starts computing again
                for(MethodData m: S.getMethodsDeclared()){
                    if(performInheritMethod(m, t)) return 1;
                } // for
                for(MethodData m: S.getMethodsInherited()){
                    if(performInheritMethod(m, t)) return 1;
                } // for
            } // for
        } // for
        return 0;
    } // computeInheritMethods()

    /*******************************************************************************
     * Checks that any abstract method in CONTAINS is in an abstract class 
     * (otherwise throws exception)
     * *****************************************************************************/
    private void checkAbstractModifier(List<ASTTree> trees) throws HierarchyCheckException{
        for(ASTTree t: trees) {
            if(! t.isClass()) continue;
            for(MethodData m: t.getMethodsDeclared()){
                if(m.isAbstract() && !t.isRootAbstract()) {
                    System.err.println("Abstract method " + m.getMethodName() + " declared in non-abstract class.");
                    throw new HierarchyCheckException();
                } // if
            } // for
            for(MethodData m: t.getMethodsInherited()){
                if(m.isAbstract() && !t.isRootAbstract()) {
                    System.err.println("Abstract method " + m.getMethodName() + " inherited in non-abstract class.");
                    throw new HierarchyCheckException();
                } // if
            } // for
        } // for
    } // checkAbstractModifier()

    /*******************************************************************************
     * Checks that if all inherited methods of a certain signature are abstract 
     * then they have the same return type
     * *****************************************************************************/
    private void checkAbstractInheritance(List<ASTTree> trees) throws HierarchyCheckException{
        for(ASTTree t: trees) {
            // only consider abstract classes/interfaces
            if(! t.isRootAbstract()) continue;
            for (MethodData m : t.getMethodsInherited()) {
                if(!m.isAbstract() || t.isSignatureInThisClass(m, true)) continue;
                for (MethodData m2 : t.getMethodsInherited()) {
                    if(!m2.isAbstract() || t.isSignatureInThisClass(m2, true) || !m.isSameSignature(m2)) continue;
                    // if return types don't match, error
                    if ((m.isVoid() != m2.isVoid()) || (!m.isVoid() && !m.getReturnType().isSameType(m2.getReturnType()))) {
                        System.err.println("Return types of two abstract inherited methods " + m.getMethodName() +  " do not match.");
                        throw new HierarchyCheckException();
                    } // if
                } // for
            } // for
        } // for
    } // checkAbstractInheritance()

    /*******************************************************************************
     * Runs the hierarchy checks
     * *****************************************************************************/
    public void hierarchyCheck(List<ASTTree> trees) throws HierarchyCheckException{
        cycleCheck(trees);

        int returnCode = 1;
        // compute inherited fields
        while(returnCode == 1){
            returnCode = computeInheritFields(trees);
        } // while

        returnCode = 1;
        // compute inherited methods
        while(returnCode == 1){
            returnCode = computeInheritMethods(trees);
        } // while
        // compute REPLACE set
        computeReplaceMethods(trees);

        // check that whenever CONTAINS of a class has an abstract method,
        // class is abstract
        checkAbstractModifier(trees);
        checkAbstractInheritance(trees);
    } // hierarchyCheck()
} // class HierarchyChecker
