package Integration;

import cs444.group9.AST.CodeGenerator.CodeCleaner;
import cs444.group9.JoosCompiler;
import org.junit.After;
import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class ReachabilityTest {
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

    public ReachabilityTest() {
        _joosCompiler=new JoosCompiler();
    }

    @Test
    public void LoopTest(){
        int expected=42;
        String[] args=new String[]{
                "/otherA4/Loop/A.java",
                "/otherA4/Loop/B.java",
                "/otherA4/Loop/C.java",
                "/otherA4/Loop/D.java",
                "/otherA4/Loop/E.java",
                "/otherA4/Loop/G.java",
                "/otherA4/Loop/F.java",
                "/otherA4/Loop/I.java",
                "/otherA4/Loop/H.java",
                "/otherA4/Loop/J.java",
        };
        //determine absolute path
        String [] absArgs=new String [15+1];
        for(int i=0;i<15;++i){
            File in = new File(this.getClass().getResource(javaLib[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=0;i<10;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[15]=in.getAbsolutePath();
            int ret=_joosCompiler.compile(absArgs);
            if(i == 6) expected = 0;
            assertThat(ret).isEqualTo(expected);
        }
    }

    @Test
    public void reachabilityReturn(){
        int expected=0;
        String[] args=new String[]{
                "/a4/J1_reachability_return/Main.java",
                "/a4/J1_reachability_return/java/util/Random.java"

        };
        //determine absolute path
        String [] absArgs=new String [15+2];
        for(int i=0;i<15;++i){
            File in = new File(this.getClass().getResource(javaLib[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=15;i<15+2;++i){
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
