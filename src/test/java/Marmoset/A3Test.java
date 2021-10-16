package Marmoset;

import cs444.group9.AST.CodeGenerator.CodeCleaner;
import cs444.group9.JoosCompiler;
import org.junit.After;
import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class A3Test {
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

    public A3Test() {
        _joosCompiler=new JoosCompiler();
    }

    @Test
    public void ambiguousName(){
        int expected=0;
        String[] args=new String[]{
                "/a3/J1_5_AmbiguousName_LocalVsType.java",
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
    public void superMethodOverride(){
        int expected=0;
        String[] args=new String[]{
                "/a3/J1_supermethod_override11/Main.java",
                "/a3/J1_supermethod_override11/CompA.java",
                "/a3/J1_supermethod_override11/CompB.java",
                "/a3/J1_supermethod_override11/CompC.java",
                "/a3/J1_supermethod_override11/java/lang/Comparable.java",

        };
        //determine absolute path
        String [] absArgs=new String [15+5];
        for(int i=0;i<15;++i){
            File in = new File(this.getClass().getResource(javaLib[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=15;i<15+5;++i){
            File in = new File(this.getClass().getResource(args[i-15]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);
    }

    @Test
    public void protectedAccess2(){
        int expected=0;
        String[] args=new String[]{
                "/a3/J1_ProtectedAccess2/Main.java",
                "/a3/J1_ProtectedAccess2/p/A.java",
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
    public void protectedAccess(){
        int expected=42;
        String[] args=new String[]{
                "/a3/Je_6_ProtectedAccess_InstanceField_SuperVar/A.java",
                "/a3/Je_6_ProtectedAccess_InstanceField_SuperVar/B.java",
                "/a3/Je_6_ProtectedAccess_InstanceField_SuperVar/C.java",
                "/a3/Je_6_ProtectedAccess_InstanceField_SuperVar/D.java",
                "/a3/Je_6_ProtectedAccess_InstanceField_SuperVar/Main.java",

        };
        //determine absolute path
        String [] absArgs=new String [15+5];
        for(int i=0;i<15;++i){
            File in = new File(this.getClass().getResource(javaLib[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=15;i<15+5;++i){
            File in = new File(this.getClass().getResource(args[i-15]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);
    }

    @Test
    public void arrayInstanceof1(){
        int expected=0;
        String[] args=new String[]{
                "/a3/J1_arrayinstanceof1.java",
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
    public void staticThis(){
        int expected=42;
        String[] args=new String[]{
                "/a3/Je_6_StaticThis_AfterStaticInvoke.java",
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
    public void staticThis2(){
        int expected=42;
        String[] args=new String[]{
                "/a3/Je_6_StaticThis_NonstaticField.java",
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
    public void staticThis3(){
        int expected=42;
        String[] args=new String[]{
                "/a3/Je_6_StaticThis_InvokeNonstatic_Implicit.java",
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
    public void equalityVoid(){
        int expected=42;
        String[] args=new String[]{
                "/a3/Je_6_Equality_Void.java",
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
    public void closestMatchArray(){
        int expected=42;
        String[] args=new String[]{
                "/a3/Je_16_ClosestMatch_Array.java",
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
    public void ambigInvoke(){
        int expected=42;
        String[] args=new String[]{
                "/a3/Je_5_AmbiguousName_FieldVsType_Initializer.java",
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
    public void exactMatch(){
        int expected=0;
        String[] args=new String[]{
                "/a3/J2_exactMatchConstructor3.java",
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
    public void assignableArray(){
        int expected=42;
        String[] args=new String[]{
                "/a3/Je_6_Assignable_byteArray_intArray.java",
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
    public void staticThisInvoke1(){
        int expected=42;
        String[] args=new String[]{
                "/a3/Je_6_Assignable_ToSubtype_FieldInit.java",
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
    public void returnTest(){
        int expected=0;
        String[] args=new String[]{
                "/a3/J1_typecheck_return.java",
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
    public void ambig_fieldtypename(){
        int expected=0;
        String[] args=new String[]{
                "/a3/J1_5_AmbiguousName_FieldVsType_Initializer.java",
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
    public void String_concatvoid(){
        int expected=42;
        String[] args=new String[]{
                "/a3/Je_6_Expression_StringConcat_Void.java",
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
    public void cast1(){
        int expected=0;
        String[] args=new String[]{
                "/a3/J1_ArrayCast1/Main.java",
                "/a3/J1_ArrayCast1/A.java"
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
    public void cast2(){
        int expected=0;
        String[] args=new String[]{
                "/a3/J1_ArrayCast2/Main.java",
                "/a3/J1_ArrayCast2/A.java"
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
    public void cast3(){
        int expected=0;
        String[] args=new String[]{
                "/a3/J1_ArrayCast3/Main.java",
                "/a3/J1_ArrayCast3/A.java"
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
    public void cast4(){
        int expected=0;
        String[] args=new String[]{
                "/a3/J1_ArrayCast4/Main.java",
                "/a3/J1_ArrayCast4/A.java"
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
    public void ObjectArray1(){
        int expected=0;
        String[] args=new String[]{
                "/a3/J1_6_Assignable_Object_ObjectArray.java"
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
    public void NonStaticAccessToStatic_MethodTest(){
        int expected=42;
        String[] args=new String[]{
                "/a3/Je_6_NonStaticAccessToStatic_Method.java"
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
    public void ArrayLength1(){
        int expected=0;
        String[] args=new String[]{
                "/a3/J1_6_AssignmentInArrayLength.java"
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
    public void Arrayinterfaces1(){
        int expected=0;
        String[] args=new String[]{
                "/a3/J1_ArrayInterfaces.java"
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
