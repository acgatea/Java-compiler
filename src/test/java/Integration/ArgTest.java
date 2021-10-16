package Integration;

//Test for supporting multiple files

import static org.assertj.core.api.Assertions.*;

import cs444.group9.AST.CodeGenerator.CodeCleaner;
import cs444.group9.JoosCompiler;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.io.File;

public class ArgTest {

    public JoosCompiler _joosCompiler;

    public ArgTest(){
        _joosCompiler=new JoosCompiler();
    }

    /*
    @Test
    public void SingleFilePassTest(){
        int expected=0;
        String[] args=new String[]{
                "/a1/J1_01.joos",
        };
        //determine absolute path
        String [] absArgs=new String [1];
        File in = new File(this.getClass().getResource(args[0]).getFile());
        absArgs[0]=in.getAbsolutePath();
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);
    }*/

    @Test
    public void SingleFileFailTest(){
        int expected=42;
        String[] args=new String[]{
                "/a1/Je_1_Identifiers_Goto.joos"
        };
        //determine absolute path
        String [] absArgs=new String [1];
        File in = new File(this.getClass().getResource(args[0]).getFile());
        absArgs[0]=in.getAbsolutePath();
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);
    }
    /*
    @Test
    public void DoubleFilePassTest(){
        int expected=0;
        String[] args=new String[]{
                "/a1/J1_01.joos",
                "/a1/J1_1_AmbiguousName_AccessResultFromMethod.joos"
        };
        //determine absolute path
        String [] absArgs=new String [2];
        File in1 = new File(this.getClass().getResource(args[0]).getFile());
        absArgs[0]=in1.getAbsolutePath();
        File in2=new File(this.getClass().getResource(args[1]).getFile());
        absArgs[1]=in2.getAbsolutePath();
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);
    }*/

    @Test
    public void DoubleFileFailTest(){
        int expected=42;
        String[] args=new String[]{
                "/a1/J1_01.joos",
                "/a1/Je_1_Identifiers_Goto.joos"
        };
        //determine absolute path
        String [] absArgs=new String [2];
        File in1 = new File(this.getClass().getResource(args[0]).getFile());
        absArgs[0]=in1.getAbsolutePath();
        File in2=new File(this.getClass().getResource(args[1]).getFile());
        absArgs[1]=in2.getAbsolutePath();
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);
    }

    /*
    @Test
    public void FourFilePassTest(){
        int expected=0;
        String[] args=new String[]{
                "/a1/J1_01.joos",
                "/a1/J1_1_AmbiguousName_AccessResultFromMethod.joos",
                "/a1/J1_1_Cast_Complement.joos",
                "/a1/J1_1_Cast_MultipleCastOfSameValue_1.joos"
        };
        //determine absolute path
        String [] absArgs=new String [4];
        for(int i=0;i<4;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);
    }
    */

    @Test
    public void FourFileFailTest(){
        int expected=42;
        String[] args=new String[]{
                "/a1/J1_01.joos",
                "/a1/J1_1_AmbiguousName_AccessResultFromMethod.joos",
                "/a1/Je_1_FinalField_NoInitializer.joos",
                "/a1/J1_1_Cast_MultipleCastOfSameValue_1.joos"
        };
        //determine absolute path
        String [] absArgs=new String [4];
        for(int i=0;i<4;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
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
