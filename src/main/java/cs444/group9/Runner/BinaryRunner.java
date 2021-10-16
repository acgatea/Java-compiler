package cs444.group9.Runner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BinaryRunner {
    public class BinaryRunnerException extends Exception{}

    public final String DEFAULT_BINARY_EXEC="main";
    public final String DEFAULT_BINARY_EXEC_DIR=".";
    public final String DEFAULT_BINARY_WIN_EXEC="main.exe";
    public final int EXEC_TIMEOUT=10;

    private static final Logger logger = LogManager.getLogger(BinaryRunner.class);

    String _execName;
    String _execDir;

    public BinaryRunner(){
        _execDir=DEFAULT_BINARY_EXEC_DIR;
        _execName=DEFAULT_BINARY_EXEC;
    }

    public void ExecName(String execname){
        _execName=execname;
    }

    public String ExecName(){
        return _execName;
    }

    public String ExecDir(){
        return _execDir;
    }

    public void ExecDir(String execDir){
        _execDir=execDir;
    }

    public int runBin() throws BinaryRunnerException{
        String s;
        String cmdStr;
        Process p=null;

        //determine os
        String os =System.getProperty("os.name");
        if(os.toLowerCase().contains("windows")){
            _execName=DEFAULT_BINARY_WIN_EXEC;
            cmdStr=_execName;

        } else {
            cmdStr=_execDir + File.separator + _execName;
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
            logger.fatal("Assembled binary (main) exit code: " + exitval);
            return exitval;

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
            throw new BinaryRunnerException();
        } catch (InterruptedException e){
            logger.error("exception occured: ");
            logger.error(e.toString());
            if(p != null){
                p.destroy();
            }
            throw new BinaryRunnerException();
        }
    }
}
