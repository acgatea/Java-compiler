package Integration;

import cs444.group9.AST.CodeGenerator.CodeCleaner;
import cs444.group9.Runner.BinaryRunner;
import cs444.group9.Runner.LinkerRunner;
import cs444.group9.Runner.NasmRunner;
import cs444.group9.JoosCompiler;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class CodeGenTest {
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

    public CodeGenTest() {
        _joosCompiler=new JoosCompiler();
    }

    @Before
    public void beforeCleanup(){
        CodeCleaner.cleanCode(CodeCleaner.DEFAULT_DIR);
    }

    @Test
    public void InterfaceTest(){
        int expected=0;
        String[] args=new String[]{
                "/codeGen/InterfaceTests/A.java",
                "/codeGen/InterfaceTests/B.java",
                "/codeGen/InterfaceTests/C.java",
                "/codeGen/InterfaceTests/D.java",
                "/codeGen/InterfaceTests/E.java",
                "/codeGen/InterfaceTests/F.java"
        };
        //determine absolute path
        String [] absArgs=new String [15+6];
        for(int i=0;i<6;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=6;i<15+6;++i){
            File in = new File(this.getClass().getResource(javaLib[i-6]).getFile());
            absArgs[i]=in.getAbsolutePath(  );
        }
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);

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
    public void abstractClassTest(){
        int expected=0;
        String[] args=new String[]{
                "/codeGen/abstractClass/A.java",
                "/codeGen/abstractClass/B.java",
                "/codeGen/abstractClass/C.java",
        };
        //determine absolute path
        String [] absArgs=new String [15+3];
        for(int i=0;i<3;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=3;i<15+3;++i){
            File in = new File(this.getClass().getResource(javaLib[i-3]).getFile());
            absArgs[i]=in.getAbsolutePath(  );
        }
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);

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
    public void BlockTest(){
        int expected=0;
        String[] args=new String[]{
                "/blocktests/Main.java",
        };
        //determine absolute path
        String [] absArgs=new String [15+1];
        for(int i=0;i<1;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=1;i<15+1;++i){
            File in = new File(this.getClass().getResource(javaLib[i-1]).getFile());
            absArgs[i]=in.getAbsolutePath(  );
        }
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);

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
    public void Test(){
        int expected=0;
        String[] args=new String[]{
            "/codeGen/A.java"
        };
        //determine absolute path
        String [] absArgs=new String [15+1];
        for(int i=0;i<1;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[0]=in.getAbsolutePath();
        }
        for(int i=1;i<16;++i){
            File in = new File(this.getClass().getResource(javaLib[i-1]).getFile());
            absArgs[i]=in.getAbsolutePath(  );
        }
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);

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
    public void J1_A_CloneOnInterface(){
        int expected=0;
        String[] args=new String[]{
                "/a5/J1_A_CloneOnInterface/Main.java",
                "/a5/J1_A_CloneOnInterface/pkg/Class.java",
                "/a5/J1_A_CloneOnInterface/pkg/Interface.java"
        };
        //determine absolute path
        int ioargs=3;
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

        NasmRunner nasmRunner=new NasmRunner();
        LinkerRunner linkerRunner= new LinkerRunner();
        try {
            nasmRunner.runNasmForAll();
            linkerRunner.runLd();
            ret=0;
        } catch (NasmRunner.NasmRunnerException e){
            ret=42;
        } catch (LinkerRunner.LinkerRunnerException e){
            ret=42;
        }
        assertThat(ret).isEqualTo(expected);

    }

    @Test
    public void J1_callbeforereturn(){
        int expected=0;
        String[] args=new String[]{
                "/a5/J1_callbeforereturn/Main.java",
                "/a5/J1_callbeforereturn/java/util/Collection.java",
                "/a5/J1_callbeforereturn/java/util/LinkedList.java",
                "/a5/J1_callbeforereturn/java/util/List.java",
        };
        //determine absolute path
        int ioargs=4;
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

        NasmRunner nasmRunner=new NasmRunner();
        LinkerRunner linkerRunner= new LinkerRunner();
        try {
            nasmRunner.runNasmForAll();
            linkerRunner.runLd();
            ret=0;
        } catch (NasmRunner.NasmRunnerException e){
            ret=42;
        } catch (LinkerRunner.LinkerRunnerException e){
            ret=42;
        }
        assertThat(ret).isEqualTo(expected);

    }

    @Test
    public void J1e_A_CastNewExp(){
        int expected=0;
        String[] args=new String[]{
                "/a5/J1e_A_CastNewExp/Main.java",
                "/a5/J1e_A_CastNewExp/javax/swing/JButton.java",
                "/a5/J1e_A_CastNewExp/javax/swing/JComponent.java",
        };
        //determine absolute path
        int ioargs=3;
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

        NasmRunner nasmRunner=new NasmRunner();
        LinkerRunner linkerRunner= new LinkerRunner();
        try {
            nasmRunner.runNasmForAll();
            linkerRunner.runLd();
            ret=0;
        } catch (NasmRunner.NasmRunnerException e){
            ret=42;
        } catch (LinkerRunner.LinkerRunnerException e){
            ret=42;
        }
        assertThat(ret).isEqualTo(expected);
    }

    @Test
    public void J1_sideeffects_obj() {
        int expected=0;
        String[] args=new String[]{
                "/a5/J1_sideeffects_obj/Main.java",
                "/a5/J1_sideeffects_obj/java/util/Collection.java",
                "/a5/J1_sideeffects_obj/java/util/LinkedList.java",
                "/a5/J1_sideeffects_obj/java/util/List.java",
        };
        //determine absolute path
        int ioargs=4;
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

        NasmRunner nasmRunner=new NasmRunner();
        LinkerRunner linkerRunner= new LinkerRunner();
        BinaryRunner binaryRunner= new BinaryRunner();
        try {
            nasmRunner.runNasmForAll();
            linkerRunner.runLd();
            binaryRunner.runBin();
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


    @After
    public void cleanup(){
       CodeCleaner.cleanCode(CodeCleaner.DEFAULT_DIR);
    }
}
