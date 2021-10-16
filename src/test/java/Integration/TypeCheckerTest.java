package Integration;

import cs444.group9.AST.CodeGenerator.CodeCleaner;
import cs444.group9.JoosCompiler;
import org.junit.After;
import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class TypeCheckerTest {
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

    public TypeCheckerTest() {
        _joosCompiler=new JoosCompiler();
    }

    //  general tests (non-marmoset)
    @Test
    public void forwardReferenceTest(){
        int expected=42;
        String[] args=new String[]{
                "/otherA3/ForwardReference/A.java",
                "/otherA3/ForwardReference/B.java",
                "/otherA3/ForwardReference/C.java",
        };
        //determine absolute path
        String [] absArgs=new String [15+1];
        for(int i=0;i<15;++i){
            File in = new File(this.getClass().getResource(javaLib[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=0;i<3;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[15]=in.getAbsolutePath();
            int ret=_joosCompiler.compile(absArgs);
            if(i==2) expected = 0;
            assertThat(ret).isEqualTo(expected);
        }
    }
    @Test
    public void narrowingConversion(){ // bad conversions
        int expected=42;
        String[] args=new String[]{
                "/otherA3/Conversions/narrowingConv1.java",
                "/otherA3/Conversions/narrowingConv2.java",
                "/otherA3/Conversions/narrowingConv3.java",
                "/otherA3/Conversions/narrowingConv4.java",
                "/otherA3/Conversions/narrowingConv5.java",
                "/otherA3/Conversions/narrowingConv6.java",
                "/otherA3/Conversions/narrowingConv7.java",
                "/otherA3/Conversions/narrowingConv8.java",
        };
        //determine absolute path
        String [] absArgs=new String [15+1];
        for(int i=0;i<15;++i){
            File in = new File(this.getClass().getResource(javaLib[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=0;i<8;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[15]=in.getAbsolutePath();
            int ret=_joosCompiler.compile(absArgs);
            assertThat(ret).isEqualTo(expected);
        }
    }
    @Test
    public void objectTest(){
        int expected=42;
        String[] args=new String[]{
                "/otherA3/Object/Object.java",
                "/otherA3/Object/String.java",
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
    @Test
    public void objectTest2(){
        int expected=42;
        String[] args=new String[]{
                "/otherA3/Object2/Object.java",
                "/otherA3/Object2/String.java",
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
    @Test
    public void badLHSTest(){
        int expected=42;
        String[] args=new String[]{
                "/otherA3/BadLHS/A.java",
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
    public void forwardReference(){
        int expected=42;
        String[] args=new String[]{
                "/a3/Je_5_ForwardReference_InAssignment.java",
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
    public void forwardReference2(){
        int expected=42;
        String[] args=new String[]{
                "/a3/Je_5_ForwardReference_InAssignment2.java",
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
    public void forwardReference3(){
        int expected=0;
        String[] args=new String[]{
                "/a3/J1_5_ForwardReference_ExplicitThis_InAssignment.java",
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
    public void wideiningConversion(){ // good conversions
        int expected=0;
        String[] args=new String[]{
                "/otherA3/Conversions/wideningConv.java",
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
    public void GoodLookup1(){
        int expected=0;
        String[] args=new String[]{
                "/otherA3/GoodLookup1/A.java",
                "/otherA3/GoodLookup1/B.java",
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
    @Test
    public void BadLookup1(){
        int expected=42;
        String[] args=new String[]{
                "/otherA3/BadLookup1/A.java",
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
    public void PoetExample(){
        int expected=0;
        String[] args=new String[]{
                "/otherA3/PoetExample/Gabriel.java",
                "/otherA3/PoetExample/Music.java",
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
    @Test
    public void protectedConstructor(){
        int expected=42;
        String[] args=new String[]{
                "/otherA3/protectedConstr/A.java",
                "/otherA3/protectedConstr/B.java",
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
    @Test
    public void defaultConstructor(){
        int expected=0;
        String[] args=new String[]{
                "/otherA3/defaultConstr/A.java",
                "/otherA3/defaultConstr/B.java",
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
    @Test
    public void badInit(){
        int expected=42;
        String[] args=new String[]{
                "/otherA3/BadInit/A.java",
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
    public void restrictedNative(){
        int expected=0;
        String[] args=new String[]{
                "/otherA3/NativeRestricted/A.java",
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
    public void restrictedGeneral(){
        int expected=42;
        String[] args=new String[]{
                "/otherA3/NativeGeneral/A.java",
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
    public void nonstatic(){
        int expected=42;
        String[] args=new String[]{
                "/otherA3/NonStaticAccess/A.java",
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
    public void interfaceTest(){
        int expected=0;
        String[] args=new String[]{
                "/otherA3/Classes/A.java",
                "/otherA3/Classes/B.java",
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
