package Integration;

import cs444.group9.AST.CodeGenerator.CodeCleaner;
import cs444.group9.JoosCompiler;
import org.junit.After;
import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class InitializationTest {
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

    public InitializationTest() {
        _joosCompiler=new JoosCompiler();
    }
    @Test
    public void forwardReferenceTest(){
        int expected=0;
        String[] args=new String[]{
                "/otherA4/ForwardReference/C.java",   // should be 0
                "/otherA4/ForwardReference/D.java",   // should be 0
                "/otherA4/ForwardReference/A.java",   // should be 42
                "/otherA4/ForwardReference/B.java", // returns 0, should be 42
        };
        //determine absolute path
        String [] absArgs=new String [15+1];
        for(int i=0;i<15;++i){
            File in = new File(this.getClass().getResource(javaLib[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=0;i<4;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[15]=in.getAbsolutePath();
            int ret=_joosCompiler.compile(absArgs);
            if(i == 2) expected = 42;
            assertThat(ret).isEqualTo(expected);
        }
    }
    @Test
    public void assignment3(){
        int expected=42;
        String[] args=new String[]{
                "/otherA4/J1_SelfAssign_BeforeExpr.java"
        };
        //determine absolute path
        String [] absArgs=new String [15+1];
        for(int i=0;i<15;++i){
            File in = new File(this.getClass().getResource(javaLib[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=15;i<15+1;++i){
            File in = new File(this.getClass().getResource(args[i-15]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);
    }

    @After
    public void cleanup(){
        CodeCleaner.cleanCode(CodeCleaner.DEFAULT_DIR);
    }
}
