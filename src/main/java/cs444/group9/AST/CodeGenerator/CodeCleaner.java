/*******************************************************************************
 * CodeCleaner.java
 * 
 * A module implementing the CodeCleaner.
 * ****************************************************************************/
package cs444.group9.AST.CodeGenerator;

import java.io.File;

public class CodeCleaner {
    public static final String DEFAULT_DIR="output";

    /*******************************************************************************
     * Cleans each file in directory
     * time : O(#files)
     * *****************************************************************************/
    public static void cleanCode(String directory){
        File file = new File(directory);
        String[] myFiles;
        if(file.isDirectory()){
            myFiles = file.list();
            int numFiles = myFiles.length;
            for (int i=0; i<numFiles; ++i) {
                File myFile = new File(file, myFiles[i]);
                myFile.delete();
            } // for
        } // if
    } // cleanCode()
} // class CodeCleaner
