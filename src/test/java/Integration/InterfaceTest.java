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

public class InterfaceTest {
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

    public InterfaceTest() {
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
    public void InterfaceTest1(){
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
        if(ret == 42){
            return;
        }

        NasmRunner nasmRunner=new NasmRunner();
        LinkerRunner linkerRunner= new LinkerRunner();
        BinaryRunner binaryRunner= new BinaryRunner();
        try {
            nasmRunner.runNasmForAll();
            linkerRunner.runLd();
            int binRet=binaryRunner.runBin();
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
    public void J1_Interface_Extends(){
        int expected=0;
        int binRetExpected=123;
        String[] args=new String[]{
                "/interfaces/J1_Interface_Extends/Main.java",
                "/interfaces/J1_Interface_Extends/abc/A.java",
                "/interfaces/J1_Interface_Extends/abc/B.java",
                "/interfaces/J1_Interface_Extends/abc/ImplClass.java",
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
        if(ret == 42){
            return;
        }

        NasmRunner nasmRunner=new NasmRunner();
        LinkerRunner linkerRunner= new LinkerRunner();
        BinaryRunner binaryRunner= new BinaryRunner();
        try {
            nasmRunner.runNasmForAll();
            linkerRunner.runLd();
            int binRet=binaryRunner.runBin();
            assertThat(binRet).isEqualTo(binRetExpected);
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
    public void Je_Interface_Self_Extends(){
        int expected=42;
        int binRetExpected=123;
        String[] args=new String[]{
                "/interfaces/Je_Interface_Self_Extends/Main.java",
                "/interfaces/Je_Interface_Self_Extends/A.java",
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
        LinkerRunner linkerRunner= new LinkerRunner();
        BinaryRunner binaryRunner= new BinaryRunner();
        try {
            nasmRunner.runNasmForAll();
            linkerRunner.runLd();
            int binRet=binaryRunner.runBin();
            assertThat(binRet).isEqualTo(binRetExpected);
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
    public void J1_Interface_Method_Override(){
        int expected=0;
        int binRetExpected=123;
        String[] args=new String[]{
                "/interfaces/J1_Interface_Method_Override/Main.java",
                "/interfaces/J1_Interface_Method_Override/A.java",
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
        LinkerRunner linkerRunner= new LinkerRunner();
        BinaryRunner binaryRunner= new BinaryRunner();
        try {
            nasmRunner.runNasmForAll();
            linkerRunner.runLd();
            int binRet=binaryRunner.runBin();
            assertThat(binRet).isEqualTo(binRetExpected);
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
    public void Je_Implements_Method_No_Override(){
        int expected=42;
        int binRetExpected=123;
        String[] args=new String[]{
                "/interfaces/Je_Implements_Method_No_Override/Main.java",
                "/interfaces/Je_Implements_Method_No_Override/A.java",
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
        LinkerRunner linkerRunner= new LinkerRunner();
        BinaryRunner binaryRunner= new BinaryRunner();
        try {
            nasmRunner.runNasmForAll();
            linkerRunner.runLd();
            int binRet=binaryRunner.runBin();
            assertThat(binRet).isEqualTo(binRetExpected);
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
    public void J1_method_overload(){
        int expected=0;
        int binRetExpected=123;
        String[] args=new String[]{
                "/interfaces/J1_method_overload/Main.java",
                "/interfaces/J1_method_overload/A.java",
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
        LinkerRunner linkerRunner= new LinkerRunner();
        BinaryRunner binaryRunner= new BinaryRunner();
        try {
            nasmRunner.runNasmForAll();
            linkerRunner.runLd();
            int binRet=binaryRunner.runBin();
            assertThat(binRet).isEqualTo(binRetExpected);
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
    public void J1_method_override_overload_extends(){
        int expected=0;
        int binRetExpected=123;
        String[] args=new String[]{
                "/interfaces/J1_method_override_overload_extends/Main.java",
                "/interfaces/J1_method_override_overload_extends/A.java",
                "/interfaces/J1_method_override_overload_extends/B.java",
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
        if(ret == 42){
            return;
        }

        NasmRunner nasmRunner=new NasmRunner();
        LinkerRunner linkerRunner= new LinkerRunner();
        BinaryRunner binaryRunner= new BinaryRunner();
        try {
            nasmRunner.runNasmForAll();
            linkerRunner.runLd();
            int binRet=binaryRunner.runBin();
            assertThat(binRet).isEqualTo(binRetExpected);
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
    public void J1_implements_multiple(){
        int expected=0;
        int binRetExpected=123;
        String[] args=new String[]{
                "/interfaces/J1_implements_multiple/Main.java",
                "/interfaces/J1_implements_multiple/A.java",
                "/interfaces/J1_implements_multiple/B.java",
                "/interfaces/J1_implements_multiple/C.java",
                "/interfaces/J1_implements_multiple/pack/F.java",
                "/interfaces/J1_implements_multiple/pkg/E.java",
                "/interfaces/J1_implements_multiple/pkg/D.java"
        };
        //determine absolute path
        int ioargs=7;
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
        BinaryRunner binaryRunner= new BinaryRunner();
        try {
            nasmRunner.runNasmForAll();
            linkerRunner.runLd();
            int binRet=binaryRunner.runBin();
            assertThat(binRet).isEqualTo(binRetExpected);
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
    public void J1_Arrayof_interface(){
        int expected=0;
        int binRetExpected=123;
        String[] args=new String[]{
                "/interfaces/J1_Arrayof_interface/Main.java",
                "/interfaces/J1_Arrayof_interface/A.java",
                "/interfaces/J1_Arrayof_interface/AImpl.java",
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
        if(ret == 42){
            return;
        }

        NasmRunner nasmRunner=new NasmRunner();
        LinkerRunner linkerRunner= new LinkerRunner();
        BinaryRunner binaryRunner= new BinaryRunner();
        try {
            nasmRunner.runNasmForAll();
            linkerRunner.runLd();
            int binRet=binaryRunner.runBin();
            assertThat(binRet).isEqualTo(binRetExpected);
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
    public void J1_instanceof_interface(){
        int expected=0;
        int binRetExpected=123;
        String[] args=new String[]{
                "/interfaces/J1_instanceof_interface/Main.java",
                "/interfaces/J1_instanceof_interface/A.java",
                "/interfaces/J1_instanceof_interface/B.java",
                "/interfaces/J1_instanceof_interface/BImpl.java",
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
        if(ret == 42){
            return;
        }

        NasmRunner nasmRunner=new NasmRunner();
        LinkerRunner linkerRunner= new LinkerRunner();
        BinaryRunner binaryRunner= new BinaryRunner();
        try {
            nasmRunner.runNasmForAll();
            linkerRunner.runLd();
            int binRet=binaryRunner.runBin();
            assertThat(binRet).isEqualTo(binRetExpected);
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
    public void Je_DidNotImplementAll(){
        int expected=42;
        int binRetExpected=123;
        String[] args=new String[]{
                "/interfaces/Je_DidNotImplementAll/Main.java",
                "/interfaces/Je_DidNotImplementAll/PointInterface.java",
                "/interfaces/Je_DidNotImplementAll/RealPointInterface.java",
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
        if(ret == 42){
            return;
        }

        NasmRunner nasmRunner=new NasmRunner();
        LinkerRunner linkerRunner= new LinkerRunner();
        BinaryRunner binaryRunner= new BinaryRunner();
        try {
            nasmRunner.runNasmForAll();
            linkerRunner.runLd();
            int binRet=binaryRunner.runBin();
            assertThat(binRet).isEqualTo(binRetExpected);
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
    public void J1_DidImplementAll(){
        int expected=0;
        int binRetExpected=123;
        String[] args=new String[]{
                "/interfaces/J1_DidImplementAll/Main.java",
                "/interfaces/J1_DidImplementAll/PointInterface.java",
                "/interfaces/J1_DidImplementAll/RealPointInterface.java",
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
        if(ret == 42){
            return;
        }

        NasmRunner nasmRunner=new NasmRunner();
        LinkerRunner linkerRunner= new LinkerRunner();
        BinaryRunner binaryRunner= new BinaryRunner();
        try {
            nasmRunner.runNasmForAll();
            linkerRunner.runLd();
            int binRet=binaryRunner.runBin();
            assertThat(binRet).isEqualTo(binRetExpected);
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
