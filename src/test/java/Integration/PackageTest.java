package Integration;

import cs444.group9.AST.CodeGenerator.CodeCleaner;
import cs444.group9.JoosCompiler;
import org.junit.After;
import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class PackageTest {
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

    public PackageTest(){
        _joosCompiler=new JoosCompiler();
    }

    // other package tests
    @Test
    public void prefixTest5(){
        int expected=42;
        String[] args=new String[]{
                "/other/Prefix/PrefixOfType/A.java",
                "/other/Prefix/PrefixOfType/B/B.java",
                "/other/Prefix/PrefixOfType/B.java",
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
    public void prefixTest6(){
        int expected=0;
        String[] args=new String[]{
                "/other/Prefix/PrefixOfTypeNoError/A.java",
                "/other/Prefix/PrefixOfTypeNoError/B.java",
                "/other/Prefix/PrefixOfTypeNoError/C.java",
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
    public void prefixTest7(){
        int expected=0;
        String[] args=new String[]{
                "/other/Prefix/PrefixOfPackage/A.java",
                "/other/Prefix/PrefixOfPackage/B.java",
                "/other/Prefix/PrefixOfPackage/Dir/B.java",
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
    public void mosquitoTest(){ // from JLS
        int expected=0;
        String[] args=new String[]{
                "/other/MosquitoExample/Test.java",
                "/other/MosquitoExample/Mosquito.java",
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
    public void vectorTest(){ // from JLS
        int expected=0;
        String[] args=new String[]{
                "/other/VectorExample/Vector.java",
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
    public void unnamedPackageTest1(){
        int expected=42;
        String[] args=new String[]{
                "/other/UnnamedPackage/A.java",
                "/other/UnnamedPackage/B.java",
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
    public void unnamedPackageTest2(){
        int expected=42;
        String[] args=new String[]{
                "/other/UnnamedPackage2/A.java",
                "/other/UnnamedPackage2/B.java",
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
    public void nameResolutionPrecedence1(){
        int expected=0;
        String[] args=new String[]{
                "/other/Precedence1/A.java",
                "/other/Precedence1/Integer.java",
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
    public void nameResolutionPrecedence2(){
        int expected=0;
        String[] args=new String[]{
                "/other/Precedence2/pack/B.java",
                "/other/Precedence2/B.java",
                "/other/Precedence2/pack/A.java",
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
    public void nameResolutionPrecedence3(){
        int expected=0;
        String[] args=new String[]{
                "/other/Precedence3/pack/B.java",
                "/other/Precedence3/pack2/B.java",
                "/other/Precedence3/B.java",
                "/other/Precedence3/A.java",
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
    public void importOnDemandClash(){
        int expected=42;
        String[] args=new String[]{
                "/other/ImportOnDemandClash/pack/B.java",
                "/other/ImportOnDemandClash/pack2/B.java",
                "/other/ImportOnDemandClash/A.java",
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
    public void importOnDemandClash2(){
        int expected=42;
        String[] args=new String[]{
                "/other/ImportOnDemandClash2/pack/B.java",
                "/other/ImportOnDemandClash2/pack2/B.java",
                "/other/ImportOnDemandClash2/A.java",
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
    public void importOnDemandType(){
        int expected=0;
        String[] args=new String[]{
                "/other/ImportOnDemandType/B.java",
                "/other/ImportOnDemandType/A.java",
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
    public void subPackageTest(){
        int expected=42;
        String[] args=new String[]{
                "/other/SubPackage/A.java",
                "/other/SubPackage/B.java",
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
    public void subPackageTest2(){
        int expected=42;
        String[] args=new String[]{
                "/other/SubPackageofDefault/A.java",
                "/other/SubPackageofDefault/B.java",
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
    public void sameNamePackages(){
        int expected=0;
        String[] args=new String[]{
                "/other/SameName/A.java",
                "/other/SameName/B.java",
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
/*
    @Test
    public void noClass1(){
        int expected=42;
        String[] args=new String[]{
                "/other/noClass/noClassImportNonexistant/missingClass.java",
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
    public void noClass2(){
        int expected=42;
        String[] args=new String[]{
                "/other/noClass/noClassImportClash/missingClass.java",
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
    public void noClass3(){
        int expected=0;
        String[] args=new String[]{
                "/other/noClass/noClassNoError2/stuff.java",
                "/other/noClass/noClassNoError2/missingClass.java",
                "/other/noClass/noClassNoError2/justPack.java",
                "/other/noClass/noClassNoError2/justImport.java",
                "/other/noClass/noClassNoError2/justInterface.java",
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
*/
    // package marmoset tests
    @Test
    public void duplicate1(){
        int expected=42;
        String[] args=new String[]{
                "/a2/Je_4_DuplicateConstructor_ArrayArgs.java",
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
    public void importOnDemand3(){
        int expected=42;
        String[] args=new String[]{
                "/a2/Je_3_ImportOnDemand_ClassInMultiplePackages/java/sql/Date.java",
                "/a2/Je_3_ImportOnDemand_ClassInMultiplePackages/java/util/Date.java",
                "/a2/Je_3_ImportOnDemand_ClassInMultiplePackages/Main.java",
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
    public void importOnDemand2(){
        int expected=42;
        String[] args=new String[]{
                "/a2/Je_3_ImportOnDemand_ClashWithImplicitImport/Integer.java",
                "/a2/Je_3_ImportOnDemand_ClashWithImplicitImport/Main.java",
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
    public void resolveSamePackage(){
        int expected=42;
        String[] args=new String[]{
                "/a2/Je_3_Resolve_SamePackageAndClassName.java",
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
    public void duplicateType(){
        int expected=42;
        String[] args=new String[]{
                "/a2/Je_2_DuplicateType/Main.java",
                "/a2/Je_2_DuplicateType/Bar.java",
                "/a2/Je_2_DuplicateType/foo/Bar.java",
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
    public void singleType2(){
        int expected=0;
        String[] args=new String[]{
                "/a2/J2_3_SingleTypeImport_ImportSelf_Interface/Main.java",
                "/a2/J2_3_SingleTypeImport_ImportSelf_Interface/Test/Foo.java",
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
    public void defaultPackageTest1(){
        int expected=0;
        String[] args=new String[]{
                "/a2/J1_4_PackageNameIsClassName_DefaultPackage/Main.java",
                "/a2/J1_4_PackageNameIsClassName_DefaultPackage/foo.java",
                "/a2/J1_4_PackageNameIsClassName_DefaultPackage/foo/baz.java",
                "/a2/J1_4_PackageNameIsClassName_DefaultPackage/bar/bar.java",
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
    public void resolveNotDefault(){
        int expected=0;
        String[] args=new String[]{
                "/a2/J1_4_Resolve_NotDefaultPackage/Main.java",
                "/a2/J1_4_Resolve_NotDefaultPackage/foo.java",
                "/a2/J1_4_Resolve_NotDefaultPackage/foo/bar.java",
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
    public void singleImport2(){
        int expected=0;
        String[] args=new String[]{
                "/a2/J1_3_SingleTypeImport_ImportSelf/Main.java",
                "/a2/J1_3_SingleTypeImport_ImportSelf/Test/Foo.java",
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
    public void resolvePackage(){
        int expected=0;
        String[] args=new String[]{
                "/a2/J1_3_Resolve_PackagePrefixMatchClassName/Main.java",
                "/a2/J1_3_Resolve_PackagePrefixMatchClassName/Main/B/A.java",
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
    public void importOnDemand1(){
        int expected=0;
        String[] args=new String[]{
                "/a2/J1_3_ImportOnDemand_DefaultImportInPresenceOfOtherImport/Main.java",
                "/a2/J1_3_ImportOnDemand_DefaultImportInPresenceOfOtherImport/java/lang/ref/Foo.java",
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
    public void packageClash1(){
        int expected=0;
        String[] args=new String[]{
                "/a2/J1_3_PackageClashWithType_Linked_Mutated/Main.java",
                "/a2/J1_3_PackageClashWithType_Linked_Mutated/javax/swing/treejava.java",
                "/a2/J1_3_PackageClashWithType_Linked_Mutated/javax/swing/tree/TreeNode.java",
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
    public void packageClash2(){
        int expected=0;
        String[] args=new String[]{
                "/a2/J1_3_PackageDecl_SamePackageAndClassName/Main.java",
                "/a2/J1_3_PackageDecl_SamePackageAndClassName/A/A.java",
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
    public void prefixTest1(){
        int expected=0;
        String[] args=new String[]{
                "/a2/J1_3_PackageExists_AsPrefix_External/Main.java",
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
    public void prefixTest2(){
        int expected=0;
        String[] args=new String[]{
                "/a2/J1_3_PackageExists_AsPrefix_Internal/Main.java",
                "/a2/J1_3_PackageExists_AsPrefix_Internal/foo/bar/Baz.java",
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
    public void singleClashOnDemand(){
        int expected=0;
        String[] args=new String[]{
                "/a2/J1_3_SingleTypeImport_ClashWithOnDemand/Main.java",
                "/a2/J1_3_SingleTypeImport_ClashWithOnDemand/foo/List/Bar.java",
                "/a2/J1_3_SingleTypeImport_ClashWithOnDemand/java/util/Collection.java",
                "/a2/J1_3_SingleTypeImport_ClashWithOnDemand/java/util/List.java",
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
    public void singleClashPackage(){
        int expected=0;
        String[] args=new String[]{
                "/a2/J1_3_SingleTypeImport_ClashWithPackageName/Main.java",
                "/a2/J1_3_SingleTypeImport_ClashWithPackageName/List/A.java",
                "/a2/J1_3_SingleTypeImport_ClashWithPackageName/java/util/Collection.java",
                "/a2/J1_3_SingleTypeImport_ClashWithPackageName/java/util/List.java",
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
    public void multipleImports1(){
        int expected=0;
        String[] args=new String[]{
                "/a2/J1_3_SingleTypeImport_MultipleFromSamePackage/Main.java",
                "/a2/J1_3_SingleTypeImport_MultipleFromSamePackage/java/util/Collection.java",
                "/a2/J1_3_SingleTypeImport_MultipleFromSamePackage/java/util/ArrayList.java",
                "/a2/J1_3_SingleTypeImport_MultipleFromSamePackage/java/util/HashSet.java",
                "/a2/J1_3_SingleTypeImport_MultipleFromSamePackage/java/util/LinkedList.java",
                "/a2/J1_3_SingleTypeImport_MultipleFromSamePackage/java/util/List.java",
                "/a2/J1_3_SingleTypeImport_MultipleFromSamePackage/java/util/Set.java",

        };
        //determine absolute path
        String [] absArgs=new String [7+15];
        for(int i=0;i<15;++i){
            File in = new File(this.getClass().getResource(javaLib[i]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }
        for(int i=0;i<7;++i){
            File in = new File(this.getClass().getResource(args[i]).getFile());
            absArgs[i+15]=in.getAbsolutePath();
        }
        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(expected);
    }
    @Test
    public void multipleImports2(){
        int expected=0;
        String[] args=new String[]{
                "/a2/J1_3_SingleTypeImport_MultipleImportsOfSameType/Main.java",
                "/a2/J1_3_SingleTypeImport_MultipleImportsOfSameType/java/io/File.java",
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
    public void singleImport(){
        int expected=0;
        String[] args=new String[]{
                "/a2/J1_3_SingleTypeImport_NoClash/Main.java",
                "/a2/J1_3_SingleTypeImport_NoClash/a/bc.java",
                "/a2/J1_3_SingleTypeImport_NoClash/ab/c.java",
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
    public void classExtendsClass(){
        int expected=0;
        String[] args=new String[]{
                "/a2/J1_4_ClassExtendsClass_SameName/Main.java",
                "/a2/J1_4_ClassExtendsClass_SameName/foo/LinkedList.java",
                "/a2/J1_4_ClassExtendsClass_SameName/java/util/List.java",
                "/a2/J1_4_ClassExtendsClass_SameName/java/util/LinkedList.java",
                "/a2/J1_4_ClassExtendsClass_SameName/java/util/Collection.java",
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
    public void resolveDefaultPackageTest(){
        int expected=42;
        String[] args=new String[]{
                "/a2/Je_4_Resolve_DefaultPackage/foo.java",
                "/a2/Je_4_Resolve_DefaultPackage/foo/bar.java",
                "/a2/Je_4_Resolve_DefaultPackage/Main.java",
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
    public void resolveTypeTest(){
        int expected=0;
        String[] args=new String[]{
                "/a2/J1_resolvetype4/Main.java",
                "/a2/J1_resolvetype4/pack/Foo.java",
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
    public void packageNameIsClassNameTest(){
        int expected=42;
        String[] args=new String[]{
                "/a2/Je_3_PackageNameIsClassName/Main.java",
                "/a2/Je_3_PackageNameIsClassName/foo/bar.java",
                "/a2/Je_3_PackageNameIsClassName/foo/bar/baz.java",
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
    public void importLookupTest(){
        int expected=0;
        String[] args=new String[]{
                "/a2/J1_importNameLookup1/Main.java",
                "/a2/J1_importNameLookup1/bar/foo.java",
                "/a2/J1_importNameLookup1/baz/foo.java",
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
    public void importTest(){
        int expected=0;
        String[] args=new String[]{
                "/a2/J1_singleTypeImport/Main.java",
                "/a2/J1_singleTypeImport/java/awt/List.java",
                "/a2/J1_singleTypeImport/java/util/Collection.java",
                "/a2/J1_singleTypeImport/java/util/LinkedList.java",
                "/a2/J1_singleTypeImport/java/util/List.java",
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
    public void implicitSuper(){ // from JLS
        int expected=0;
        String[] args=new String[]{
                "/a2/J1_implicitsuper/Bar.java",
                "/a2/J1_implicitsuper/Foo.java",
                "/a2/J1_implicitsuper/Main.java",

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

    @After
    public void cleanup(){
        CodeCleaner.cleanCode(CodeCleaner.DEFAULT_DIR);
    }
}
