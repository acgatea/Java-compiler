package cs444.group9.Runner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LinkerRunner {
    public class LinkerRunnerException extends Exception {}

    public final String DEFAULT_LINKER_EXEC="ld";
    public final String DEFAULT_INPUT_DIR="output";
    public final int SUCCESS_CODE=0;
    public final int EXEC_TIMEOUT=10;

    String _execName;
    String _inputDir;

    private static final Logger logger = LogManager.getLogger(LinkerRunner.class);

    public LinkerRunner(){
        _execName=DEFAULT_LINKER_EXEC;
        _inputDir=DEFAULT_INPUT_DIR;
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

    public void runLd() throws LinkerRunnerException {
        String s;
        String cmdStr;
        Process p=null;

        //determine os
        String os =System.getProperty("os.name");
        if(os.toLowerCase().contains("windows")){
            _execName="ld.exe";
            cmdStr= _execName + " -mpei-i386 -o main.exe";

        } else {
            cmdStr= _execName + " -melf_i386 -o main";
        }
        List<String> inputs=getInputFiles();
        for(String input: inputs){
            cmdStr += " " + _inputDir + File.separator + input;
        }
        //add runtime libraries
        if(os.toLowerCase().equals("windows")){
            cmdStr += " " + "lib" + File.separator + "WinRuntime.o";
        } else {
            cmdStr += " " + "lib" + File.separator + "runtime.o";
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
                logger.error(cmdStr);
            }

            p.destroy();
            p.waitFor(EXEC_TIMEOUT, TimeUnit.SECONDS);
            int exitval = p.exitValue();
            if(exitval != SUCCESS_CODE){
                logger.error("exit code: " + exitval);
                throw new LinkerRunnerException();
            }

            //run objcopy - only for windows
            //ld.exe doesn't support elf_i386
            //no idea if this even needs to be done
            /*if(os.toLowerCase().contains("windows")){
                cmdStr="objcopy -O elf32-i386 " + _inputDir +
                        File.separator + "main main.elf";
                p = Runtime.getRuntime().exec(cmdStr);
                stdInput = new BufferedReader(new
                        InputStreamReader(p.getInputStream()));

                stdError = new BufferedReader(new
                        InputStreamReader(p.getErrorStream()));
                p.destroy();
                p.waitFor(EXEC_TIMEOUT, TimeUnit.SECONDS);
                exitval = p.exitValue();
                if(exitval != SUCCESS_CODE){
                    logger.error("exit code: " + exitval);
                    throw new LinkerRunnerException();
                }
            }*/
        } catch (IOException e) {
            logger.error("exception occured: ");
            logger.error(e.toString());
            if(p != null){
                p.destroy();
            }
            throw new LinkerRunnerException();
        } catch (InterruptedException e){
            logger.error("exception occured: ");
            logger.error(e.toString());
            if(p != null){
                p.destroy();
            }
            throw new LinkerRunnerException();
        }
    }

    //need this because java Runtime does not support wildcards
    public List<String> getInputFiles(){
        ArrayList<String> inputFiles=new ArrayList<>();

        File dir = new File(_inputDir);
        String[] myFiles;
        if(dir.isDirectory()){
            myFiles = dir.list();
            for (int i=0; i<myFiles.length; i++) {
                File myFile = new File(dir, myFiles[i]);
                if(myFile.getName().endsWith(".o")){
                    inputFiles.add(myFile.getName());
                }
            }
        }
        return inputFiles;
    }

}
