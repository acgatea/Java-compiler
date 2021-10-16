package Integration;

import cs444.group9.AST.CodeGenerator.CodeCleaner;
import cs444.group9.JoosCompiler;
import org.junit.After;
import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;


public class NameResolutionTest {

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

    public NameResolutionTest(){
        _joosCompiler=new JoosCompiler();
    }

    @Test
    public void duplicate(){
        int expected=42;
        String[] args=new String[]{
                "/other/duplicate.java"
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
    @Test
    public void test(){
        int expected=0;
        String[] args=new String[]{
                "/other/A.java",
                "/other/B.java",
                "/other/C.java",
                "/other/D.java",
        };
        //determine absolute path

        String [] absArgs=new String [15+4];
        for(int i=0;i<15;++i){
            File in = new File(this.getClass().getResource(javaLib[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=15;i<15+4;++i){
            File in = new File(this.getClass().getResource(args[i-15]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);
    }

    // marmoset test
    @Test
    public void accessToImplicitThis(){
        int expected=42;
        String[] args=new String[]{
                "/a3/Je_6_InstantiateAbstract.java",
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
