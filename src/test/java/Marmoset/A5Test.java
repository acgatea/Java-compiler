package Marmoset;

import cs444.group9.AST.CodeGenerator.CodeCleaner;
import cs444.group9.Runner.LinkerRunner;
import cs444.group9.Runner.NasmRunner;
import cs444.group9.JoosCompiler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(value = Parameterized.class)
public class A5Test {
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

    public A5Test(String inputFile, int expectedReturnCode) {
        this._inputFile = inputFile;
        this._expectedReturnCode = expectedReturnCode;
        _joosCompiler=new JoosCompiler();
    }

    @Before
    public void CleanDir(){
        CodeCleaner.cleanCode(CodeCleaner.DEFAULT_DIR);
    }

    @Parameterized.Parameters(name = "Test: {0}")
    public static Collection<Object[]> data() {
        Object[][] A5Tests = new Object[][]{
                {"/a5/J1_01.java",0},
                {"/a5/J1_1_Instanceof_InLazyExp.java",0},
                {"/a5/J1_1_Instanceof_OfAdditiveExpression.java",0},
                {"/a5/J1_1_Instanceof_OfCastExpression.java",0},
                {"/a5/J1_6_Assignable_Object_ObjectArray.java",0},
                {"/a5/J1_6_AssignmentInArrayLength.java",0},
                {"/a5/J1_300locals.java",0},
                {"/a5/J1_A_AddressNotEqual.java",0},
                {"/a5/J1_A_ArrayBaseInAssignment.java",0},
                {"/a5/J1_A_ArrayStoreLoad.java",0},
                {"/a5/J1_A_AssignmentInLazyOr.java",0},
                {"/a5/J1_A_BooleanArray_External.java",0},
                {"/a5/J1_A_CloneWithArgs.java",0},
                {"/a5/J1_A_Complement_SideEffect.java",0},
                {"/a5/J1_A_ConcatInSimpleInvoke.java",0},
                {"/a5/J1_A_ConcatInStaticInvoke.java",0},
                {"/a5/J1_A_Conditionals_NoInstructionAfterIfElse.java",0},
                {"/a5/J1_A_FieldInitialization_Before.java",0},
                {"/a5/J1_A_FieldInitialization_NonConstant_Before.java",0},
                {"/a5/J1_A_GreaterOrEqual.java",0},
                {"/a5/J1_A_LazyEagerAndOr.java",0},
                {"/a5/J1_A_LazyEval.java",0},
                {"/a5/J1_arithmeticoperations.java",0},
                {"/a5/J1_array.java",0},
                {"/a5/J1_arrayAccess.java",0},
                {"/a5/J1_ArrayCreateAndIndex.java",0},
                {"/a5/J1_arrayinstanceof1.java",0},
                {"/a5/J1_arrayinstanceof2.java",0},
                {"/a5/J1_A_String_ByteShortCharInt.java",0},
                {"/a5/J1_A_StringConstAEQ_ANE.java",0},
                {"/a5/J1_backwardRef.java",0},
                {"/a5/J1_BigByteInit.java",0},
                {"/a5/J1_BigCharCharInit.java",0},
                {"/a5/J1_BigShortFromByteInit.java",0},
                {"/a5/J1_BigShortInit.java",0},
                {"/a5/J1_charadd.java",0},
                {"/a5/J1_concat_in_binop.java",0},
                {"/a5/J1_concatInMethods.java",0},
                {"/a5/J1_constructorbodycast.java",0},
                {"/a5/J1_divdiv.java",0},
                {"/a5/J1e_A_CastToArray.java",0},
                {"/a5/J1e_A_CastToString.java",0},
                {"/a5/J1e_divisionbyzero.java",0},
                {"/a5/J1_fieldinit.java",0},
                {"/a5/J1_fieldinit_forward_ref.java",0},
                {"/a5/J1_fieldinit_forward_ref2.java",0},
                {"/a5/J1_Hello.java",0},
                {"/a5/J1_implicitstringconcatenation.java",0},
                {"/a5/J1_instanceof_array.java",0},
                {"/a5/J1_instanceof_array2.java",0},
                {"/a5/J1_intstringadd.java",0},
                {"/a5/J1_minuschar.java",0},
                {"/a5/J1_minusminusminus.java",0},
                {"/a5/J1_NamedTypeArray.java",0},
                {"/a5/J1_NegativeByteCast.java",0},
                {"/a5/J1_NegativeCharCast.java",0},
                {"/a5/J1_negativeintcast3.java",0},
                {"/a5/J1_NegativeIntCast1.java",0},
                {"/a5/J1_NegativeIntCast2.java",0},
                {"/a5/J1_NegativeOneByteByteCast.java",0},
                {"/a5/J1_NegativeOneByteCharCast.java",0},
                {"/a5/J1_NegativeOneByteIntCast.java",0},
                {"/a5/J1_NegativeOneByteShortCast.java",0},
                {"/a5/J1_NegativeShortCast.java",0},
                {"/a5/J1_nestedcast.java",0},
                {"/a5/J1_random_arithmetic.java",0},
                {"/a5/J1_random_arithmetic_var.java",0},
                {"/a5/J1_sideeffect1.java",0},
                {"/a5/J1_sideeffect2.java",0},
                {"/a5/J1_sideeffect3.java",0},
                {"/a5/J1_sideeffect4.java",0},
                {"/a5/J1_sideeffect5.java",0},
                {"/a5/J1_sideeffect6.java",0},
                {"/a5/J1_sideeffect7.java",0},
                {"/a5/J1_sideeffect8.java",0},
                {"/a5/J1_sideeffects_array.java",0},
                {"/a5/J1_sideeffects_array2.java",0},
                {"/a5/J1_sideeffects_array3.java",0},
                {"/a5/J1_sideeffects_array4.java",0},
                {"/a5/J1_sideeffects_obj2.java",0},
                {"/a5/J1_sideeffects_obj3.java",0},
                {"/a5/J1_sim_and.java",0},
                {"/a5/J1_sim_or.java",0},
                {"/a5/J1_SimpleTypeArray.java",0},
                {"/a5/J1_sim_xor.java",0},
                {"/a5/J1_SmallInt.java",0},
                {"/a5/J1_StaticField_AccessFromClass.java",0},
                {"/a5/J1_staticMethodInvocation.java",0},
                {"/a5/J1_stringadd.java",0},
                {"/a5/J1_StringCast.java",0},
                {"/a5/J1_stringconcat.java",0},
                {"/a5/J1_toomuchinc.java",0},
                {"/a5/J1_typecheck_array.java",0},
                {"/a5/J1_typecheck_expstm.java",0},
                {"/a5/J1_typecheck_plus.java",0},
                {"/a5/J1_while1.java",0},
                {"/a5/J1_while2.java",0},
                {"/a5/J1_whiletrue1.java",0},
                {"/a5/J1_WildConcat.java",0},
                {"/a5/J2_A_FieldInitialization_Static_Before.java",0},
                {"/a5/J2_A_FieldInitialization_Static_NonConstant_Before.java",0},
                {"/a5/J2_fieldinit_forward_ref.java",0},
                {"/a5/J2_forwardRef.java",0}
        };

        return Arrays.asList(A5Tests);
    }

    @Test
    public void runTests(){
        String [] absArgs=new String [15+1];

        //first file will contain test()
        File in = new File(this.getClass().getResource(_inputFile).getFile());
        absArgs[0]=in.getAbsolutePath();

        for(int i=1;i<15+1;++i){
            in = new File(this.getClass().getResource(javaLib[i-1]).getFile());
            absArgs[i]=in.getAbsolutePath();
        }

        int ret=_joosCompiler.compile(absArgs);
        assertThat(ret).isEqualTo(_expectedReturnCode);

        NasmRunner nasmRunner=new NasmRunner();
        LinkerRunner linkerRunner= new LinkerRunner();
        try {
            nasmRunner.runNasmForAll();
            linkerRunner.runLd();
            ret=0;
        } catch (NasmRunner.NasmRunnerException e){
            ret=42;
        } catch (LinkerRunner.LinkerRunnerException e){
            ret=42;
        }
        assertThat(ret).isEqualTo(_expectedReturnCode);
    }

    @After
    public void cleanup(){
        CodeCleaner.cleanCode(CodeCleaner.DEFAULT_DIR);
    }
}
