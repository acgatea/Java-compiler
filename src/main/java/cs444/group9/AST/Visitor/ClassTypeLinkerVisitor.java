/*******************************************************************************
 * ClassTypeLinkerVisitor.java
 * 
 * A module implementing ClassTypeLinker Visitor.
 * ****************************************************************************/
package cs444.group9.AST.Visitor;

import cs444.group9.AST.Node.Body.CompilationUnitNode;
import cs444.group9.AST.Node.Declaration.*;
import cs444.group9.AST.Node.Environments.Environment;
import cs444.group9.AST.Node.Environments.EnvironmentPair;
import cs444.group9.AST.Node.Expression.CastExprNode;
import cs444.group9.AST.Node.NameNode;
import cs444.group9.AST.Node.Type.TypeNode;
import cs444.group9.AST.Node.ASTNode;
import cs444.group9.AST.Node.Type.ClassOrInterfaceTypeNode;

import java.util.*;

public class ClassTypeLinkerVisitor extends DefaultVisitor {
    // indicates if a namenode needs to be typelinked
    private boolean toCheck;

    /*******************************************************************************
     * Checks that no qualified prefix of node is a qualified type
     * notes: Offset indicates if prefixes checked are proper or not 
     *        (1 for proper, 0 for not proper)
     * *****************************************************************************/ 
    private void CheckPrefix(NameNode node, int offset) throws ASTException {
        List<String> currNameList = node.NameList();
        for (int i = currNameList.size()-offset; i > 1; --i) {
            NameNode prefix = new NameNode(null);
            for (int j = 0; j < i; ++j) {
                prefix.addName(currNameList.get(j));
            } // for

            if (PerformNameResolve(prefix, node.getEnv())) {
                System.err.println("Prefix "+prefix.toString()+" of "+node.toString()+" resolves to a qualified type.");
                throw new ASTException();
            } // if
        } // for
    } // CheckPrefix()

    /*******************************************************************************
     * Check if any other file (than the one for env) in the same package 
     *  has the same type name
     * notes: env must be a class environment
     * *****************************************************************************/ 
    private void checkDuplicateTypes(Environment env) throws ASTException{
        Environment global = env.getParent();
        for(Environment e: global.getChildren()){
            if(e.getPackage().toString().equals(env.getPackage().toString()) && e != env &&
                    e.getScopePair().getName().equals(env.getScopePair().getName())){
                System.err.println("The type " + e.getPackage().toString()+e.getScopePair().getName() + " is declared twice.");
                throw new ASTException();
            } // if
        } // for
    } // checkDuplicateTypes()

    /*******************************************************************************
     * OVER-RIDDEN VISIT METHODS
     * *****************************************************************************/ 
    @Override
    public void visit(CompilationUnitNode node)throws ASTException{
        super.visit(node);
        for(ImportDeclNode im: node.ImportDeclarations()){
            // checks that no proper prefix of an import name is a type
            CheckPrefix(im.Name(),1);
        } // for
        // checks that no package name or prefix of a package name is a type
        if(node.PackageDeclaration() != null) CheckPrefix(node.PackageDeclaration().Name(),0);
    } // visit(CompilationUnitNode)

    @Override
    public void visit(ImportDeclNode node)throws ASTException{
        toCheck = false;
        Environment global = node.getEnv().getParent();
        if(node.isSingleType()) {// single import
            for (Environment e : global.getChildren()) {
                if(e.getPackage().toString().equals(node.Name().getAllButLastName()) &&
                        e.getScopePair().getName().equals(node.Name().getSimple())){
                    // set declaration
                    node.Name().setLinkedDeclaration(e.getScopePair().getDeclaration());
                    return;
                } // if
            } // for
            System.err.println("Could not resolve SINGLE-IMPORT of " + node.Name().toString());
            throw new ASTException();
        } else{ 
            // import on-demand
            // has name been resolved yet?
            boolean resolved = false;
            for (Environment e : global.getChildren()) {
                if(node.Name().isLeftPrefix(e.getPackage()) ||
                        (node.Name().getAllButLastName().equals(e.getPackage().toString())
                        && node.Name().getSimple().equals(e.getScopePair().getName()))){
                    // resolves as a type name
                    resolved = true;
                    // find declaration
                    EnvironmentPair p = e.getScopePair();
                    // if this declaration already in the onDemandImportList, doesn't add it
                    boolean isDuplicate = false;
                    for(ASTNode decl: node.getEnv().getOnDemandTypeImports()){
                        if(decl == p.getDeclaration()) {
                            isDuplicate = true;
                            break;
                        } // if
                    } // for
                    if(!isDuplicate) node.getEnv().addOnDemandTypeImport(p.getDeclaration());
                } // if
            } // for
            if(!resolved) {
                System.err.println("Could not resolve ONDEMAND-IMPORT of " + node.Name().toString());
                throw new ASTException();
            } // if
        } // else
    } // visit(ImportDeclNode()

    /*******************************************************************************
     * Tries to resolve name using currEnv 
     * notes: returns true if possible to resolve, false otherwise
     *        throws error if resolution is ambiguous
     * *****************************************************************************/ 
    private boolean PerformNameResolve(NameNode name, Environment e) throws ASTException{
        // gets the class environment
        Environment classEnv = e;
        while(classEnv.getPackage() == null) {
            classEnv = classEnv.getParent();
        } // while
        if (!name.isQualified() ) {
            // checks the single-type-imports
            for (NameNode importNode : classEnv.getSingleTypeImports()) {
                if (name.toString().equals(importNode.getSimple())) {
                    name.setLinkedDeclaration(importNode.getLinkedDeclaration());
                    return true;
                } // if
            } // for

            // checks files in current package
            // number of occurences found
            int count = 0;
            for (Environment env : classEnv.getParent().getChildren()) {
                if (classEnv.getPackage().toString().equals(env.getPackage().toString())) {
                    if(name.toString().equals(env.getScopePair().getName())) {
                        name.setLinkedDeclaration(env.getScopePair().getDeclaration());
                        count++;
                    } // if
                } // if
            } // for
            if (count == 1) {
                return true;
            } else if(count >= 1){
                System.err.println(name.toString() + " resolves to multiple types.");
                throw new ASTException();
            } // else if

            // the last decl found for this import
            ASTNode foundSoFar = null;
            // checks the ondemand-type-imports
            for(ASTNode decl: classEnv.getOnDemandTypeImports()){
                if(name.toString().equals(decl.getEnv().getScopePair().getName())){
                    if(foundSoFar != null && decl != foundSoFar){
                        throw new ASTException();
                    } // if
                    name.setLinkedDeclaration(decl);
                    foundSoFar = decl;
                } // if
            } // for

            if(foundSoFar != null) return true;
        } else { 
            // if name is qualified
            // checks files in all packages
            // number of occurences found
            int count = 0;
            for (Environment env : classEnv.getParent().getChildren()) {
                if (name.getAllButLastName().equals(env.getPackage().toString())) {
                    if(name.getSimple().equals(env.getScopePair().getName())) {
                        name.setLinkedDeclaration(env.getScopePair().getDeclaration());
                        count++;
                    } // if
                } // if
            } // for
            if (count == 1) {
                return true;
            } else if(count >= 1){
                System.err.println(name.toString() + " resolves to multiple types.");
                throw new ASTException();
            } // else if
        } // else
        return false;
    } // PerformNameResolve()

    /*******************************************************************************
     * Resolves the type of a name
     * notes: returns true/false depending on its success
     * *****************************************************************************/ 
    public boolean findTypeLink(NameNode name) throws ASTException{
        // tries to resolve name in the current environment
        if(!PerformNameResolve(name, name.getEnv())){
            return false;
        } // if
        if(name.isQualified()){
            // checks that no proper prefix can resolve to a type name
            List<String> currNameList = name.NameList();
            for(int i = currNameList.size()-1; i > 0; --i){
                NameNode prefix = new NameNode(null);
                for(int j = 0; j < i; ++j){
                    prefix.addName(currNameList.get(j));
                } // for
                if(PerformNameResolve(prefix, name.getEnv())) {
                    System.err.println("Prefix " + prefix.toString() + " of " + name.toString() + " resolves to a type.");
                    throw new ASTException();
                } // if
            } // for
        } // if
        return true;
    } // findTypeLink()

    @Override
    public void visit(ClassDeclNode node)throws ASTException{
        if(node.ClassType() != null){
            if(!findTypeLink(node.ClassType())){
                System.err.println(node.ClassType().toString() + " does not resolve to a type.");
                throw new ASTException();
            } // if
        } // if
        for(NameNode interfaceType: node.Interfaces()){
            if(!findTypeLink(interfaceType)) {
                System.err.println(interfaceType.toString() + " does not resolve to a type.");
                throw new ASTException();
            } // if
        } // for
        super.visit(node);
        checkDuplicateTypes(node.getEnv());
    } // visit(ClassDeclNode)

    @Override
    public void visit(InterfaceDeclNode node)throws ASTException{
        for(NameNode interfaceType: node.ExtendsInterfaces()){
            if(!findTypeLink(interfaceType)){
                System.err.println(interfaceType.toString() + " does not resolve to a type.");
                throw new ASTException();
            } // if
        } // for
        super.visit(node);
        checkDuplicateTypes(node.getEnv());
    } // visit(InterfaceDeclNode)

    @Override
    public void visit(TypeNode node)throws ASTException{
        toCheck = true;
        super.visit(node);
        toCheck = false;
    } // visit(TypeNode)

    @Override
    public void visit(ClassOrInterfaceTypeNode node)throws ASTException{
        toCheck = true;
        super.visit(node);
        node.UpdateEnv(node.Name().getLinkedDeclaration().getEnv());
        toCheck = false;
    } // visit(ClassOrInterfaceTypeNode)

    @Override
    public void visit(CastExprNode castExprNode)throws ASTException{
        if(castExprNode.Name() != null) {
            toCheck = true;
            visit(castExprNode.Name());
            toCheck = false;
        } // if
        if(castExprNode.BasicType() != null){
            visit(castExprNode.BasicType());
        } // if
        if(castExprNode.UnaryExpression() != null){
            visit(castExprNode.UnaryExpression());
        } // if
    } // visit(CastExprNode)

    @Override
    public void visit(NameNode node)throws ASTException{
        if(toCheck && ! findTypeLink(node)){
            System.err.println(node.toString() + " does not resolve to a type.");
            throw new ASTException();
        } // if
    } // visit(NameNode)
} // class ClassTypeLinkerVisitor
