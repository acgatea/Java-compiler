package Integration;

import cs444.group9.AST.CodeGenerator.CodeCleaner;
import cs444.group9.JoosCompiler;
import cs444.group9.Runner.BinaryRunner;
import cs444.group9.Runner.LinkerRunner;
import cs444.group9.Runner.NasmRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class RunTimeTest {
    public JoosCompiler _joosCompiler;
    public String[] javaLib = {
            "/JavaLibrary/io/OutputStream.java",
            "/JavaLibrary/io/PrintStream.java",
            "/JavaLibrary/io/Serializable.java",
            "/JavaLibrary/lang/Boolean.java",
            "/JavaLibrary/lang/Byte.java",
            "/JavaLibrary/lang/Character.java",
            "/JavaLibrary/lang/Class.java",
            "/JavaLibrary/lang/Cloneable.java",
            "/JavaLibrary/lang/Integer.java",
            "/JavaLibrary/lang/Number.java",
            "/JavaLibrary/lang/Object.java",
            "/JavaLibrary/lang/Short.java",
            "/JavaLibrary/lang/String.java",
            "/JavaLibrary/lang/System.java",
            "/JavaLibrary/util/Arrays.java"};

    public RunTimeTest() {
        _joosCompiler=new JoosCompiler();
    }

    @Before
    public void beforeCleanup(){
        CodeCleaner.cleanCode(CodeCleaner.DEFAULT_DIR);
    }

    @After
    public void cleanup(){
        CodeCleaner.cleanCode(CodeCleaner.DEFAULT_DIR);
    }

    @Test
    public void Je_InvalidArrayAccess(){
        int expected=0;
        int binRetExpected=13;
        String[] args=new String[]{
                "/runtime_exception/Je_InvalidArrayAccess.java",
        };
        //determine absolute path
        int ioargs=1;
        int libargs=15;
        int numargs=ioargs+libargs;
        String [] absArgs=new String [numargs];
        for(int i=0;i<ioargs;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=ioargs;i<numargs;++i){
            File in = new File(this.getClass().getResource(javaLib[i-ioargs]).getFile());
            absArgs[i]=in.getAbsolutePath(  );
        }
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);
        if(ret == 42){
            return;
        }

        NasmRunner nasmRunner=new NasmRunner();
        LinkerRunner linkerRunner= new LinkerRunner();
        BinaryRunner binaryRunner=new BinaryRunner();
        try {
            nasmRunner.runNasmForAll();
            linkerRunner.runLd();
            int binret=binaryRunner.runBin();
            assertThat(binret).isEqualTo(binRetExpected);
            ret=0;
        } catch (NasmRunner.NasmRunnerException e){
            ret=42;
        } catch (LinkerRunner.LinkerRunnerException e){
            ret=42;
        } catch (BinaryRunner.BinaryRunnerException e){
            ret=42;
        }
        assertThat(ret).isEqualTo(expected);
    }

    @Test
    public void Je_InvalidObjectAccess(){
        int expected=0;
        int binRetExpected=13;
        String[] args=new String[]{
                "/runtime_exception/Je_InvalidObjectAccess.java",
        };
        //determine absolute path
        int ioargs=1;
        int libargs=15;
        int numargs=ioargs+libargs;
        String [] absArgs=new String [numargs];
        for(int i=0;i<ioargs;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=ioargs;i<numargs;++i){
            File in = new File(this.getClass().getResource(javaLib[i-ioargs]).getFile());
            absArgs[i]=in.getAbsolutePath(  );
        }
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);
        if(ret == 42){
            return;
        }

        NasmRunner nasmRunner=new NasmRunner();
        LinkerRunner linkerRunner= new LinkerRunner();
        BinaryRunner binaryRunner=new BinaryRunner();
        try {
            nasmRunner.runNasmForAll();
            linkerRunner.runLd();
            int binret=binaryRunner.runBin();
            assertThat(binret).isEqualTo(binRetExpected);
            ret=0;
        } catch (NasmRunner.NasmRunnerException e){
            ret=42;
        } catch (LinkerRunner.LinkerRunnerException e){
            ret=42;
        } catch (BinaryRunner.BinaryRunnerException e){
            ret=42;
        }
        assertThat(ret).isEqualTo(expected);
    }

    @Test
    public void Je_InvalidDivision(){
        int expected=0;
        int binRetExpected=13;
        String[] args=new String[]{
                "/runtime_exception/Je_InvalidDivision.java",
        };
        //determine absolute path
        int ioargs=1;
        int libargs=15;
        int numargs=ioargs+libargs;
        String [] absArgs=new String [numargs];
        for(int i=0;i<ioargs;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=ioargs;i<numargs;++i){
            File in = new File(this.getClass().getResource(javaLib[i-ioargs]).getFile());
            absArgs[i]=in.getAbsolutePath(  );
        }
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);
        if(ret == 42){
            return;
        }

        NasmRunner nasmRunner=new NasmRunner();
        LinkerRunner linkerRunner= new LinkerRunner();
        BinaryRunner binaryRunner=new BinaryRunner();
        try {
            nasmRunner.runNasmForAll();
            linkerRunner.runLd();
            int binret=binaryRunner.runBin();
            assertThat(binret).isEqualTo(binRetExpected);
            ret=0;
        } catch (NasmRunner.NasmRunnerException e){
            ret=42;
        } catch (LinkerRunner.LinkerRunnerException e){
            ret=42;
        } catch (BinaryRunner.BinaryRunnerException e){
            ret=42;
        }
        assertThat(ret).isEqualTo(expected);
    }
}
