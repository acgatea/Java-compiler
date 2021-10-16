/*******************************************************************************
 * CodeChunk.java
 * 
 * A module implementing a CodeChunk.
 * ****************************************************************************/
package cs444.group9.AST.CodeGenerator;

import cs444.group9.AST.Visitor.iVisitor;

public class CodeChunk {
    boolean PRINT_COMMENTS = true;

    CodePrinterVisitor _codePrinterVisitor;

    /*******************************************************************************
     * CodeChunk constructor
     * time : O(1)
     * *****************************************************************************/
    public CodeChunk(CodePrinterVisitor codePrinterVisitor){
        _codePrinterVisitor=codePrinterVisitor;
    } // CodeChunk()

    /*******************************************************************************
     * Prints line to file
     * time : O(|line|)
     * *****************************************************************************/
    public void printLine(String line) throws iVisitor.CodePrinterVisitorException{
        _codePrinterVisitor.printToFile(line);
        _codePrinterVisitor.printToFile("\n");
    } // printLine()

    /*******************************************************************************
     * Prints label to file
     * time : O(|line|)
     * *****************************************************************************/
    public void printLabel(String label) throws iVisitor.CodePrinterVisitorException{
        _codePrinterVisitor.printToFile(label);
        _codePrinterVisitor.printToFile(":\n");
    } // printLabel()

    /*******************************************************************************
     * Prints line to file without newline
     * time : O(|line|)
     * *****************************************************************************/
    public void printRawLine(String line) throws iVisitor.CodePrinterVisitorException{
        _codePrinterVisitor.printToFile(line);
    } // printRawLine()

    /*******************************************************************************
     * Prints line to file with comments around it
     * time : O(|line|)
     * *****************************************************************************/
    public void printCommentLine(String line) throws iVisitor.CodePrinterVisitorException{
        if(!PRINT_COMMENTS) return;
        _codePrinterVisitor.printToFile("; *****");
        _codePrinterVisitor.printToFile(line);
        _codePrinterVisitor.printToFile(" *****\n");
    } // printCommentLine()
} // class CodeChunk