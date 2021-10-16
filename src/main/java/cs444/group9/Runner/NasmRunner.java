package cs444.group9.Runner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class NasmRunner {
    public class NasmRunnerException extends Exception {}

    public final String DEFAULT_NASM_DIR=".";
    public final String DEFAULT_NASM_EXEC="nasm";
    public final String DEFAULT_INPUT_DIR="output";
    public final String DEFAULT_WIN_RUNTIME="WinRuntime.s";
    public final int SUCCESS_CODE=0;
    public final int EXEC_TIMEOUT=10;

    String _execDir;
    String _execName;
    String _inputDir;

    private static final Logger logger = LogManager.getLogger(NasmRunner.class);

    public NasmRunner(){
        _execDir=DEFAULT_NASM_DIR;
        _execName=DEFAULT_NASM_EXEC;
        _inputDir=DEFAULT_INPUT_DIR;
    }

    public String ExecDir(){
        return _execDir;
    }

    public void Directory(String execDir){
        _execDir=execDir;
    }

    public void ExecName(String execname){
        _execName=execname;
    }

    public String ExecName(){
        return _execName;
    }

    public String InputDir(){
        return _inputDir;
    }

    public void InputDir(String inputDir){
        _inputDir=inputDir;
    }

    public void runNasmForAll() throws NasmRunnerException{
        File file = new File(_inputDir);
        String[] myFiles;
        if(file.isDirectory()){
            myFiles = file.list();
            for (int i=0; i<myFiles.length; i++) {
                runNasm(myFiles[i]);
            }

            //windows only
            if(System.getProperty("os.name").toLowerCase().contains("windows")) {
                runNasm("lib" + File.separator + DEFAULT_WIN_RUNTIME);
            }
        } else {
            logger.error("invalid input directory: " + _inputDir);
            throw new NasmRunnerException();
        }
    }

    public void runNasm(String filename) throws NasmRunnerException{
        String s;
        String cmdStr;
        Process p=null;

        //determine os
        String os =System.getProperty("os.name");
        if(os.toLowerCase().contains("windows")){
            _execName="nasm.exe";
            if(filename.endsWith(DEFAULT_WIN_RUNTIME)){
                cmdStr= _execName + " -O1 -f elf -g -F dwarf " + filename;
            } else {
                cmdStr= _execName + " -O1 -f elf -g -F dwarf " + _inputDir
                        + File.separator + filename;
            }
        } else {
            cmdStr=_execDir + File.separator +
                    _execName + " -O1 -f elf -g -F dwarf " + _inputDir
                    + File.separator + filename;
        }
        try {
            p = Runtime.getRuntime().exec(cmdStr);

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(p.getErrorStream()));

            // read the output from the command
            while ((s = stdInput.readLine()) != null) {
                logger.debug(s);
            }

            // read any errors from the attempted command
            while ((s = stdError.readLine()) != null) {
                logger.error(s);
            }

            p.destroy();
            p.waitFor(EXEC_TIMEOUT, TimeUnit.SECONDS);
            int exitval = p.exitValue();
            if(exitval != SUCCESS_CODE){
                logger.error("exit code: " + exitval);
                throw new NasmRunnerException();
            }
        } catch (IOException e) {
            logger.error("exception occured: ");
            logger.error(e.toString());
            if(p != null){
                p.destroy();
            }
            throw new NasmRunnerException();
        } catch (InterruptedException e){
            logger.error("exception occured: ");
            logger.error(e.toString());
            if(p != null){
                p.destroy();
            }
            throw new NasmRunnerException();
        }
    }
}
