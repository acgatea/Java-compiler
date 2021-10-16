package Integration;

import cs444.group9.AST.CodeGenerator.CodeCleaner;
import cs444.group9.JoosCompiler;
import cs444.group9.Runner.LinkerRunner;
import cs444.group9.Runner.NasmRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayTest {
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

    public ArrayTest() {
        _joosCompiler=new JoosCompiler();
    }

    @Test
    public void J1_Array_Cast_To_Method(){
        int expected=0;
        String[] args=new String[]{
                "/arraytests/J1_Array_Cast_To_Method.java",
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
        try {
            nasmRunner.runNasmForAll();
            ret=0;
        } catch (NasmRunner.NasmRunnerException e){
            ret=42;
        }
        assertThat(ret).isEqualTo(expected);

        LinkerRunner linkerRunner= new LinkerRunner();
        try {
            linkerRunner.runLd();
            ret=0;
        } catch (LinkerRunner.LinkerRunnerException e){
            ret=42;
        }
        assertThat(ret).isEqualTo(expected);
    }

    @Ignore
    @Test
    public void J1_Serializable_Cloneable_Override(){
        int expected=0;
        String[] args=new String[]{
                "/arraytests/J1_Serializable_Cloneable_Override/Main.java",
                "/arraytests/J1_Serializable_Cloneable_Override/A.java"
        };
        //determine absolute path
        int ioargs=2;
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
        try {
            nasmRunner.runNasmForAll();
            ret=0;
        } catch (NasmRunner.NasmRunnerException e){
            ret=42;
        }
        assertThat(ret).isEqualTo(expected);

        LinkerRunner linkerRunner= new LinkerRunner();
        try {
            linkerRunner.runLd();
            ret=0;
        } catch (LinkerRunner.LinkerRunnerException e){
            ret=42;
        }
        assertThat(ret).isEqualTo(expected);
    }

    @Test
    public void Je_Field_Access_From_Class(){
        int expected=42;
        String[] args=new String[]{
                "/arraytests/Je_Field_Access_From_Class/Main.java",
                "/arraytests/Je_Field_Access_From_Class/A.java"
        };
        //determine absolute path
        int ioargs=2;
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
        try {
            nasmRunner.runNasmForAll();
            ret=0;
        } catch (NasmRunner.NasmRunnerException e){
            ret=42;
        }
        assertThat(ret).isEqualTo(expected);

        LinkerRunner linkerRunner= new LinkerRunner();
        try {
            linkerRunner.runLd();
            ret=0;
        } catch (LinkerRunner.LinkerRunnerException e){
            ret=42;
        }
        assertThat(ret).isEqualTo(expected);
    }

    @Test
    public void Je_Field_Access_Cloneable(){
        int expected=42;
        String[] args=new String[]{
                "/arraytests/Je_Field_Access_Cloneable/Main.java",
                "/arraytests/Je_Field_Access_Cloneable/A.java"
        };
        //determine absolute path
        int ioargs=2;
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
        try {
            nasmRunner.runNasmForAll();
            ret=0;
        } catch (NasmRunner.NasmRunnerException e){
            ret=42;
        }
        assertThat(ret).isEqualTo(expected);

        LinkerRunner linkerRunner= new LinkerRunner();
        try {
            linkerRunner.runLd();
            ret=0;
        } catch (LinkerRunner.LinkerRunnerException e){
            ret=42;
        }
        assertThat(ret).isEqualTo(expected);
    }

    @Test
    public void Je_Field_Access_Serializable(){
        int expected=42;
        String[] args=new String[]{
                "/arraytests/Je_Field_Access_Serializable/Main.java",
                "/arraytests/Je_Field_Access_Serializable/A.java"
        };
        //determine absolute path
        int ioargs=2;
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
        try {
            nasmRunner.runNasmForAll();
            ret=0;
        } catch (NasmRunner.NasmRunnerException e){
            ret=42;
        }
        assertThat(ret).isEqualTo(expected);

        LinkerRunner linkerRunner= new LinkerRunner();
        try {
            linkerRunner.runLd();
            ret=0;
        } catch (LinkerRunner.LinkerRunnerException e){
            ret=42;
        }
        assertThat(ret).isEqualTo(expected);
    }

    @Before
    public void beforeCleanup(){
        CodeCleaner.cleanCode(CodeCleaner.DEFAULT_DIR);
    }

    @After
    public void cleanup(){
        CodeCleaner.cleanCode(CodeCleaner.DEFAULT_DIR);
    }
}
