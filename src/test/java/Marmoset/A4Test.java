package Marmoset;

import cs444.group9.AST.CodeGenerator.CodeCleaner;
import cs444.group9.JoosCompiler;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.Ignore;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(value = Parameterized.class)
public class A4Test {
    public JoosCompiler _joosCompiler;

    private String _inputFile;
    private int _expectedReturnCode;

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

    public A4Test(String inputFile, int expectedReturnCode) {
        this._inputFile = inputFile;
        this._expectedReturnCode = expectedReturnCode;
        _joosCompiler=new JoosCompiler();
    }
@Ignore
    @Parameterized.Parameters(name = "Test: {0}")
    public static Collection<Object[]> data() {
        Object[][] A4Tests = new Object[][]{
                {"/a4/J1_7_Reachability_AfterIfWithWhileTrue.java",0},
                {"/a4/J1_7_Reachability_EmptyVoidMethod.java",0},
                {"/a4/J1_7_Reachability_IfAndWhile_Return.java",0},
                {"/a4/J1_7_Reachability_IfThenElse_InConstructor.java",0},
                {"/a4/J1_7_Reachability_IfThenElse_InValueMethod.java",0},
                {"/a4/J1_7_Reachability_IfThenElse_InVoidMethod.java",0},
                {"/a4/J1_7_Reachability_WhileTrue_ConstantFolding.java",0},
                {"/a4/J1_arbitraryreturn.java",0},
                {"/a4/J1_7_Unreachable_IfEqualsNot.java",0},
                {"/a4/J1_defasn_use_before_declare.java",0},
                {"/a4/J1_if_then.java",0},
                {"/a4/J1_ifThenElse.java",0},
                {"/a4/J1_multipleReturn.java",0},
                {"/a4/J1_omittedvoidreturn.java",0},
                {"/a4/J1_Reachable1.java",0},
                {"/a4/J1_Reachable2.java",0},
                {"/a4/J1_Reachable3.java",0},
                {"/a4/J1_Reachable4.java",0},
                {"/a4/J1_reachableIfBody.java",0},
                {"/a4/J1_Unreachable.java",0},
                {"/a4/J1_unreachableAutomation.java",0},
                {"/a4/J1_while1.java",0},
                {"/a4/J1_while2.java",0},
                {"/a4/J1_whiletrue1.java",0},
                {"/a4/Je_7_DefiniteAssignment_2LazyOr_Assignment.java",42},
                {"/a4/Je_7_DefiniteAssignment_3LazyOr_Assignment.java",42},
                {"/a4/Je_7_Reachability_AfterElseReturn.java",42},
                {"/a4/Je_7_Reachability_AfterIfReturn.java",42},
                {"/a4/Je_7_Reachability_AfterIfReturnElseReturn.java",42},
                {"/a4/Je_7_Reachability_AfterReturn_Constructor.java",42},
                {"/a4/Je_7_Reachability_AfterReturnEmptyBlock.java",42},
                {"/a4/Je_7_Reachability_AfterValueReturn.java",42},
                {"/a4/Je_7_Reachability_AfterVoidReturn.java",42},
                {"/a4/Je_7_Reachability_EmptyValueMethod.java",42},
                {"/a4/Je_7_Reachability_ForFalse_1.java",42},
                {"/a4/Je_7_Reachability_ForFalse_2.java",42},
                {"/a4/Je_7_Reachability_ReturnReturn.java",42},
                {"/a4/Je_7_Reachability_WhileFalse_ConstantFolding.java",42},
                {"/a4/Je_7_Reachability_WhileFalse_Empty.java",42},
                {"/a4/Je_7_Reachability_WhileFalse_IfThenElse.java",42},
                {"/a4/Je_7_Reachability_WhileTrue.java",42},
                {"/a4/Je_7_Reachability_WhileTrue_ConstantFolding.java",42},
                {"/a4/Je_7_Return_IfElseIf.java",42},
                {"/a4/Je_7_Return_IfIfNoElseElse.java",42},
                {"/a4/Je_7_Return_IfIfNot.java",42},
                {"/a4/Je_7_Return_MissingInElse.java",42},
                {"/a4/Je_8_DefiniteAssignment_ArrayAssign.java",42},
                {"/a4/Je_8_DefiniteAssignment_ArrayIndexAssign.java",42},
                {"/a4/Je_8_DefiniteAssignment_ComplexInitializer.java",42},
                {"/a4/Je_8_DefiniteAssignment_FalseAndAssignment.java",42},
                {"/a4/Je_8_DefiniteAssignment_FieldWithSameName.java",42},
                {"/a4/Je_8_DefiniteAssignment_IfIfNot.java",42},
                {"/a4/Je_8_DefiniteAssignment_InitToItself.java",42},
                {"/a4/Je_8_DefiniteAssignment_SomethingAndAssignment.java",42},
                {"/a4/Je_8_DefiniteAssignment_SomethingOrAssignment.java",42},
                {"/a4/Je_8_DefiniteAssignment_UninitializedExpInLvalue.java",42},
                {"/a4/Je_8_DefiniteAssignment_UninitializedInNewArray.java",42},
                {"/a4/Je_8_DefiniteAssignment_WhileFalse.java",42},
                {"/a4/Je_Widening.java",42}
        };

        return Arrays.asList(A4Tests);
    }

    @Test
    public void runTests(){
        String [] absArgs=new String [15+1];

        File in = new File(this.getClass().getResource(_inputFile).getFile());
        absArgs[0]=in.getAbsolutePath();

        for(int i=1;i<15+1;++i){
            in = new File(this.getClass().getResource(javaLib[i-1]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }

        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(_expectedReturnCode);
    }

    @After
    public void cleanup(){
        CodeCleaner.cleanCode(CodeCleaner.DEFAULT_DIR);
    }
}
