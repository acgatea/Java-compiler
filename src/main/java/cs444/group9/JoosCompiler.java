/*******************************************************************************
 * JoosCompiler.java
 * 
 * A module implementing the main compiler for Joos.
 * ****************************************************************************/


package cs444.group9;

import java.util.*;

import cs444.group9.AST.ASTTree;
import cs444.group9.AST.CodeGenerator.CodePrinterVisitor;
import cs444.group9.AST.HierarchyChecker;
import cs444.group9.AST.Node.Environments.Environment;
import cs444.group9.AST.Node.Environments.EnvironmentPair;
import cs444.group9.AST.Visitor.iVisitor;
import cs444.group9.AST.Visitor.*;
import cs444.group9.Filter.CommentsFilter;
import cs444.group9.Scanner.*;
import cs444.group9.Parser.*;

import java.io.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JoosCompiler{

    private static final Logger logger = LogManager.getLogger(JoosCompiler.class);

    public CommentsFilter commentsFilter;

    /*******************************************************************************
     * JoosCompiler Constructor
     * time : O(1)
     * *****************************************************************************/     
    public JoosCompiler(){
        commentsFilter=new CommentsFilter();
    } // JoosCompiler()

    /*******************************************************************************
     * Compiles with command-line arguments args
     * notes: returns appropriate exit code
     * *****************************************************************************/     
    public int compile(String [] args){
        logger.debug("Starting compilation");
        EnvironmentPair newGlobalPair = new EnvironmentPair("##GLOBAL##", null);
        Environment globalEnv = new Environment(newGlobalPair);
        List<ASTTree> trees = new ArrayList<>();
        for(String filename: args) {
            /* *************************** INPUT PROCESSING ******************************* */
            // try reading the file
            String content = "";
            try {
                BufferedReader buf = new BufferedReader(new FileReader(filename));

                // read the file content, line by line
                String line;
                while ((line = buf.readLine()) != null) {
                    content += line + "\n";
                } // while
                buf.close();
            } catch (FileNotFoundException e) {
                System.err.println("File does not exist.");
                return 1;
            } catch (IOException ie) {
                System.err.println("File cannot be read.");
                return 1;
            } // catch

            // apply comment filter
            content = commentsFilter.FilterLine(content);

            // get a sequence of tokens from scanner
            InputScanner i = new InputScanner();
            List<Token> result = new ArrayList<Token>();
            // try to scan
            try {
                result = i.scan(content);
            } catch (InputScanner.ScanningError e) {
                // if scanner finds error, exit
                return 42;
            } // catch

            // if BOF, EOF are the only tokens then we are done
            if (result.size() == 2) return 0;

            /* ******************************* PARSER ********************************** */
            InputParser p = new InputParser("res/machine.lr1");
            Node tree;
            try {
                tree = p.parse(result);
            } catch (InputParser.ParsingError e) {
                // if parser finds error, exit
                return 42;
            } // catch
            
            Weeder w = new Weeder(tree, filename);
            try {
                w.performWeeding();
            } catch (Weeder.WeedingError e) {
                // if parser finds error, exit
                return 42;
            } // catch

            //build the AST tree
            ASTTree astTree = new ASTTree(tree);
            try {
                astTree.buildtree(globalEnv);
                // ignore empty trees
                if(!astTree.isFileEmpty()) trees.add(astTree);
            } catch (iVisitor.ASTException e) {
                // if astbuilder finds error, exit
                return 42;
            } // catch
        } // for
        ClassTypeLinkerVisitor v = new ClassTypeLinkerVisitor();
        ASTTree objectClass = null;
        try {
            for(ASTTree t: trees){
                // compute type links
                t.getRoot().accept(v);
                // compute Declare set
                t.computeDeclare();

                if(t.getRoot().getEnv().getPackage().toString().equals("java.lang") &&
                        t.getRoot().getEnv().getScopePair().getName().equals("Object")) objectClass = t;
            } // for
        } catch (iVisitor.ASTException e) {
            return 42;
        } // catch
        if(objectClass == null) return 42;

        // compute SUPER
        try {
            for(ASTTree astTree: trees) {
                astTree.computeSuper(trees);
            } // for
        } catch (iVisitor.ASTException e) {
            return 42;
        } // catch

        // check hierarchy is well-formed
        HierarchyChecker h = new HierarchyChecker();
        try {
            h.hierarchyCheck(trees);
        } catch (HierarchyChecker.HierarchyCheckException e) {
            return 42;
        } // catch

        // compute AllSuper (all super interfaces/classes)
        for(ASTTree astTree: trees) {
            astTree.computeAllSuper();
        } // for

        // resolve names
        TypeChecker tc = new TypeChecker();
        tc.setObjectClass(objectClass);
        try {
            tc.resolveAllNames(trees);
        } catch (iVisitor.ASTException e) {
            return 42;
        } // catch

        ConstantComputeVisitor constantComputeVisitor=new ConstantComputeVisitor();
        ReachabilityChecker reachabilityChecker=new ReachabilityChecker();
        InitializationChecker initializationChecker=new InitializationChecker();

        //compute constants
        try {
            for(ASTTree astTree: trees) {
                constantComputeVisitor.visit(astTree.getRoot());
            } // for
        } catch (iVisitor.ASTException e) {
            return 42;
        } // catch

        //check reachability
        try {
            for(ASTTree astTree: trees) {
                reachabilityChecker.visit(astTree.getRoot());
            } // for
        } catch (iVisitor.ASTException e) {
            return 42;
        } // catch

        //check initializations
        try {
            for(ASTTree astTree: trees) {
                initializationChecker.visit(astTree.getRoot());
            } // for
        } catch (iVisitor.ASTException e) {
            return 42;
        } // catch

        // generate code
        CodePrinterVisitor p = new CodePrinterVisitor();
        p.computeSubtypes(trees); // compute and order types
        p.computeInterfaceMethods(trees); // compute and order interfaces
        try{
            p.startInitialization(trees); // code for initialization
            for(ASTTree astTree: trees) {
                p.visit(astTree.getRoot());
            } // for
        } catch (iVisitor.ASTException e) {
            return 42;
        } // catch

        return 0;
    } // compile()

    /*******************************************************************************
     * Compiles with command-line arguments args
     * notes: exits with appropriate exit code
     * *****************************************************************************/ 
    public static void main (String [] args){
        JoosCompiler compiler = new JoosCompiler();
        int returncode = compiler.compile(args);
        System.exit(returncode);
    } // main()
} // class JoosCompiler