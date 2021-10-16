package Integration;

import cs444.group9.AST.CodeGenerator.CodeCleaner;
import cs444.group9.JoosCompiler;
import org.junit.After;
import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;


public class HierarchyTest {

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

    public HierarchyTest(){
        _joosCompiler=new JoosCompiler();
    }


    @Test
    public void publicReplace(){
        int expected=42;
        String[] args=new String[]{
                "/other/ReplaceMethodErrors/publicReplace/t/A.java",
                "/other/ReplaceMethodErrors/publicReplace/t/B.java",
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
    public void otherTest(){
        int expected=42;
        String[] args=new String[]{
                "/a2/Je_2_Locals_Overlapping_SameLine.java",
        };
        //determine absolute path
        String [] absArgs=new String [15 + 1];
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

    // tests with 1 file where there are 2 methods, constructors or fields with the
    // same signature
    @Test
    public void SameSignature(){
        int expected=42;
        String[] args=new String[]{
                "/other/SameSignature/sameSig1.java",
                "/other/SameSignature/sameSig2.java",
                "/other/SameSignature/sameSig3.java",
                "/other/SameSignature/sameSig4.java",
                "/other/SameSignature/sameSig5.java",
                "/other/SameSignature/sameSig6.java",
                "/other/SameSignature/sameSig7.java",
        };
        //determine absolute path
        String [] absArgs=new String [15 + 1];
        for(int i=0;i<15;++i){
            File in = new File(this.getClass().getResource(javaLib[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=0;i<7;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[15]=in.getAbsolutePath();
            int ret=_joosCompiler.compile(absArgs);
            if(i==6) expected = 0;// no error
            assertThat(ret).isEqualTo(expected);
        }
    }

    // tests with extends/implements lists that have names not of the correct type
    @Test
    public void BadSuperSet(){
        int expected=42;
        String[] args=new String[]{
                "/other/BadSuperSet/clas.java",
                "/other/BadSuperSet/interf.java",
                "/other/BadSuperSet/noclass.java",
                "/other/BadSuperSet/badSuperSet1.java",
                "/other/BadSuperSet/badSuperSet2.java",
                "/other/BadSuperSet/badSuperSet3.java",
                "/other/BadSuperSet/badSuperSet4.java",
                "/other/BadSuperSet/badSuperSet5.java",
                "/other/BadSuperSet/badSuperSet6.java",
                "/other/BadSuperSet/finalClass.java"

        };
        //determine absolute path
        String [] absArgs=new String [15 + 4];
        for(int i=0;i<15;++i){
            File in = new File(this.getClass().getResource(javaLib[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=0;i<3;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[i+15]=in.getAbsolutePath();
        }
        for(int i = 3; i < 8; i++) {
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[15+3] = in.getAbsolutePath();
            int ret = _joosCompiler.compile(absArgs);
            assertThat(ret).isEqualTo(expected);
        }
        // final class (test 6)
        String [] absArgs2=new String [15+2];
        for(int i=0;i<15;++i){
            File in = new File(this.getClass().getResource(javaLib[i]).getFile());
            absArgs2[i]=in.getAbsolutePath();
        }
        for(int i=8;i<10;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs2[i-8+15]=in.getAbsolutePath();
        }
        int ret = _joosCompiler.compile(absArgs2);
        assertThat(ret).isEqualTo(expected);
    }

    @Test
    public void HierarchyCycles1(){
        int expected=42;
        String[] args=new String[]{
                "/other/HierarchyCycles/Cycle1/A.java",
                "/other/HierarchyCycles/Cycle1/B.java",
                "/other/HierarchyCycles/Cycle1/C.java",
        };
        //determine absolute path
        String [] absArgs=new String [15+3];
        for(int i=0;i<15;++i){
            File in = new File(this.getClass().getResource(javaLib[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=0;i<3;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[i+15]=in.getAbsolutePath();
        }
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);
    }

    @Test
    public void HierarchyCycles2(){
        int expected=0;
        String[] args=new String[]{
                "/other/HierarchyCycles/Cycle2/A.java",
                "/other/HierarchyCycles/Cycle2/B.java",
                "/other/HierarchyCycles/Cycle2/C.java",
        };
        //determine absolute path
        String [] absArgs=new String [3+15];
        for(int i=0;i<15;++i){
            File in = new File(this.getClass().getResource(javaLib[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=0;i<3;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[i+15]=in.getAbsolutePath();
        }
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);
    }

    @Test
    public void HierarchyCycles3(){
        int expected=0;
        String[] args=new String[]{
                "/other/HierarchyCycles/Cycle3/A.java",
                "/other/HierarchyCycles/Cycle3/B.java",
                "/other/HierarchyCycles/Cycle3/C.java",
                "/other/HierarchyCycles/Cycle3/D.java",
                "/other/HierarchyCycles/Cycle3/E.java",
        };
        //determine absolute path
        String [] absArgs=new String [5+15];
        for(int i=0;i<15;++i){
            File in = new File(this.getClass().getResource(javaLib[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=0;i<5;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[i+15]=in.getAbsolutePath();
        }
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);
    }

    @Test
    public void HierarchyCycles4(){
        int expected=42;
        String[] args=new String[]{
                "/other/HierarchyCycles/Cycle4/A.java",
        };
        //determine absolute path
        String [] absArgs=new String [1+15];
        for(int i=0;i<15;++i){
            File in = new File(this.getClass().getResource(javaLib[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=0;i<1;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[i+15]=in.getAbsolutePath();
        }
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);
    }


    @Test
    public void HierarchyCycles5(){
        int expected=42;
        String[] args=new String[]{
                "/other/HierarchyCycles/Cycle5/A.java",
                "/other/HierarchyCycles/Cycle5/B.java",
                "/other/HierarchyCycles/Cycle5/C.java",
                "/other/HierarchyCycles/Cycle5/D.java",
                "/other/HierarchyCycles/Cycle5/E.java",
        };
        //determine absolute path
        String [] absArgs=new String [5+15];
        for(int i=0;i<15;++i){
            File in = new File(this.getClass().getResource(javaLib[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=0;i<5;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[15+i]=in.getAbsolutePath();
        }
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);
    }
    @Test
    public void HierarchyReplaceReturnType1(){
        int expected=42;
        String[] args=new String[]{
                "/other/ReplaceMethodErrors/returnType1/A.java",
                "/other/ReplaceMethodErrors/returnType1/B.java",
        };
        //determine absolute path
        String [] absArgs=new String [2+15];
        for(int i=0;i<15;++i){
            File in = new File(this.getClass().getResource(javaLib[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=0;i<2;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[i+15]=in.getAbsolutePath();
        }
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);
    }
    @Test
    public void HierarchyReplaceReturnType2(){
        int expected=42;
        String[] args=new String[]{
                "/other/ReplaceMethodErrors/returnType2/A.java",
                "/other/ReplaceMethodErrors/returnType2/B.java",
        };
        //determine absolute path
        String [] absArgs=new String [2+15];
        for(int i=0;i<15;++i){
            File in = new File(this.getClass().getResource(javaLib[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=0;i<2;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[i+15]=in.getAbsolutePath();
        }
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);
    }

    @Test
    public void StaticReplace1(){
        int expected=42;
        String[] args=new String[]{
                "/other/ReplaceMethodErrors/staticReplace1/A.java",
                "/other/ReplaceMethodErrors/staticReplace1/B.java",
        };
        //determine absolute path
        String [] absArgs=new String [2+15];
        for(int i=0;i<15;++i){
            File in = new File(this.getClass().getResource(javaLib[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=0;i<2;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[15+i]=in.getAbsolutePath();
        }
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);
    }
    @Test
    public void StaticReplace2(){
        int expected=42;
        String[] args=new String[]{
                "/other/ReplaceMethodErrors/staticReplace2/A.java",
                "/other/ReplaceMethodErrors/staticReplace2/B.java",
        };
        //determine absolute path
        String [] absArgs=new String [2+15];
        for(int i=0;i<15;++i){
            File in = new File(this.getClass().getResource(javaLib[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=0;i<2;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[i+15]=in.getAbsolutePath();
        }
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);
    }

    @Test
    public void finalReplace(){
        int expected=42;
        String[] args=new String[]{
                "/other/ReplaceMethodErrors/finalReplace/A.java",
                "/other/ReplaceMethodErrors/finalReplace/B.java",
        };
        //determine absolute path
        String [] absArgs=new String [2+15];
        for(int i=0;i<15;++i){
            File in = new File(this.getClass().getResource(javaLib[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=0;i<2;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[i+15]=in.getAbsolutePath();
        }
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);
    }
    @Test
    public void abstractInheritance1(){
        int expected=42;
        String[] args=new String[]{
                "/other/ReplaceMethodErrors/abstractInheritance1/A.java",
                "/other/ReplaceMethodErrors/abstractInheritance1/B.java",
                "/other/ReplaceMethodErrors/abstractInheritance1/C.java",
        };
        //determine absolute path
        String [] absArgs=new String [3+15];
        for(int i=0;i<15;++i){
            File in = new File(this.getClass().getResource(javaLib[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=0;i<3;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[i+15]=in.getAbsolutePath();
        }
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);
    }
    @Test
    public void abstractInheritance2(){
        int expected=42;
        String[] args=new String[]{
                "/other/ReplaceMethodErrors/abstractInheritance2/A.java",
                "/other/ReplaceMethodErrors/abstractInheritance2/B.java",
                "/other/ReplaceMethodErrors/abstractInheritance2/C.java",
        };
        //determine absolute path
        String [] absArgs=new String [3+15];
        for(int i=0;i<15;++i){
            File in = new File(this.getClass().getResource(javaLib[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=0;i<3;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[i+15]=in.getAbsolutePath();
        }
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);
    }
    @Test
    public void abstractInheritance3(){
        int expected=0; // no error
        String[] args=new String[]{
                "/other/ReplaceMethodErrors/abstractInheritance3/A.java",
                "/other/ReplaceMethodErrors/abstractInheritance3/B.java",
                "/other/ReplaceMethodErrors/abstractInheritance3/C.java",
        };
        //determine absolute path
        String [] absArgs=new String [3+15];
        for(int i=0;i<15;++i){
            File in = new File(this.getClass().getResource(javaLib[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=0;i<3;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[i+15]=in.getAbsolutePath();
        }
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);
    }
    @Test
    public void abstractInheritance4(){
        int expected=0;  // no error
        String[] args=new String[]{
                "/other/ReplaceMethodErrors/abstractInheritance4/C.java",
                "/other/ReplaceMethodErrors/abstractInheritance4/A.java",
                "/other/ReplaceMethodErrors/abstractInheritance4/B.java",
                "/other/ReplaceMethodErrors/abstractInheritance4/D.java",
                "/other/ReplaceMethodErrors/abstractInheritance4/E.java",
        };
        //determine absolute path
        String [] absArgs=new String [5+15];
        for(int i=0;i<15;++i){
            File in = new File(this.getClass().getResource(javaLib[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=0;i<5;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[i+15]=in.getAbsolutePath();
        }
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);
    }
    @Test
    public void abstractInheritance5(){
        int expected=42;
        String[] args=new String[]{
                "/other/ReplaceMethodErrors/abstractInheritance5/C.java",
                "/other/ReplaceMethodErrors/abstractInheritance5/A.java",
                "/other/ReplaceMethodErrors/abstractInheritance5/B.java",
                "/other/ReplaceMethodErrors/abstractInheritance5/D.java",
        };
        //determine absolute path
        String [] absArgs=new String [4+15];
        for(int i=0;i<15;++i){
            File in = new File(this.getClass().getResource(javaLib[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=0;i<4;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[i+15]=in.getAbsolutePath();
        }
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);
    }

/*
    @Test
    public void Hierarchy1(){
        int expected=0;
        String[] args=new String[]{
                "/other/noClass/noClassNoError/bar.java",
                "/other/noClass/noClassNoError/boz.java",
                "/other/noClass/noClassNoError/foo.java",
                "/other/noClass/noClassNoError/Main.java",
                "/other/noClass/noClassNoError/superClass.java",
                "/other/noClass/noClassNoError/subClass.java",
                "/other/noClass/noClassNoError/noClass.java",
                "/other/noClass/noClassNoError/Interf.java"
        };
        //determine absolute path
        String [] absArgs=new String [8+15];
        for(int i=0;i<15;++i){
            File in = new File(this.getClass().getResource(javaLib[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=0;i<8;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[i+15]=in.getAbsolutePath();
        }
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);
    }
*/
    // a couple of MARMOSET tests:
    @Test
    public void Hierarchy23(){
        int expected=0;
        String[] args=new String[]{
                "/a2/J2_hierachyCheck23/bar.java",
                "/a2/J2_hierachyCheck23/baz.java",
                "/a2/J2_hierachyCheck23/boz.java",
                "/a2/J2_hierachyCheck23/foo.java",
                "/a2/J2_hierachyCheck23/Main.java"
        };
        //determine absolute path
        String [] absArgs=new String [5+15];
        for(int i=0;i<15;++i){
            File in = new File(this.getClass().getResource(javaLib[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=0;i<5;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[i+15]=in.getAbsolutePath();
        }
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);
    }
    @Test
    public void Hierarchy24(){
        int expected=0;
        String[] args=new String[]{
                "/a2/J2_hierachyCheck24/bar.java",
                "/a2/J2_hierachyCheck24/baz.java",
                "/a2/J2_hierachyCheck24/boz.java",
                "/a2/J2_hierachyCheck24/foo.java",
                "/a2/J2_hierachyCheck24/Main.java"
        };
        //determine absolute path
        String [] absArgs=new String [5+15];
        for(int i=0;i<15;++i){
            File in = new File(this.getClass().getResource(javaLib[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=0;i<5;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[i+15]=in.getAbsolutePath();
        }
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);
    }
    @Test
    public void Hierarchy25(){
        int expected=0;
        String[] args=new String[]{
                "/a2/J2_hierachyCheck25/bar.java",
                "/a2/J2_hierachyCheck25/baz.java",
                "/a2/J2_hierachyCheck25/boz.java",
                "/a2/J2_hierachyCheck25/foo.java",
                "/a2/J2_hierachyCheck25/Main.java"
        };
        //determine absolute path
        String [] absArgs=new String [5+15];
        for(int i=0;i<15;++i){
            File in = new File(this.getClass().getResource(javaLib[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=0;i<5;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[i+15]=in.getAbsolutePath();
        }
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);
    }


    @Test
    public void HierarchyE1(){
        int expected=42;
        String[] args=new String[]{
                "/a2/Je_4_ClassExtendsCyclicClass/A.java",
                "/a2/Je_4_ClassExtendsCyclicClass/B.java",
                "/a2/Je_4_ClassExtendsCyclicClass/C.java",
                "/a2/Je_4_ClassExtendsCyclicClass/Main.java"
        };
        //determine absolute path
        String [] absArgs=new String [4+15];
        for(int i=0;i<15;++i){
            File in = new File(this.getClass().getResource(javaLib[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=0;i<4;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[i+15]=in.getAbsolutePath();
        }
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);
    }

    @Test
    public void implementTwiceTest(){
        int expected=42;
        String[] args=new String[]{
                "/a2/Je_4_ImplementTwice_SimpleName/Main.java",
                "/a2/Je_4_ImplementTwice_SimpleName/java/lang/Runnable.java",
        };
        //determine absolute path
        String [] absArgs=new String [2+15];
        for(int i=0;i<15;++i){
            File in = new File(this.getClass().getResource(javaLib[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=0;i<2;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[i+15]=in.getAbsolutePath();
        }
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);
    }

    @After
    public void cleanup(){
        CodeCleaner.cleanCode(CodeCleaner.DEFAULT_DIR);
    }
}
