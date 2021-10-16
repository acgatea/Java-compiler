package Integration;

import cs444.group9.AST.CodeGenerator.CodeCleaner;
import cs444.group9.JoosCompiler;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(value = Parameterized.class)
public class WeederTest {
    public JoosCompiler joosCompiler;

    private String _inputFile;
    private int _expectedReturnCode;

    // Inject via constructor
    public WeederTest(String inputFile, int expectedReturnCode) {
        this._inputFile = inputFile;
        this._expectedReturnCode = expectedReturnCode;
        joosCompiler=new JoosCompiler();
    }

    // name attribute is optional, provide an unique name for test
    // multiple parameters, uses Collection<Object[]>
    @Parameterized.Parameters(name = "File: {0}")
    public static Collection<Object[]> data() {
        Object[][] A1Tests=new Object[][]{
//                {"/integration/J1_LargeIntUnaryMinus.joos", 0},
                {"/integration/Je_ProtectedClass.joos", 42}, //
                {"/integration/Je_StaticClass.joos", 42},
                {"/integration/Je_NativeClass.joos", 42},
                {"/integration/Je_PrivateClass.joos", 42},
                {"/integration/Je_DuplicateModifier1.joos", 42},
                {"/integration/Je_DuplicateModifier2.joos", 42},
                {"/integration/Je_DuplicateModifier3.joos", 42},
                {"/integration/Je_PublicAndProtected1.joos", 42},
                {"/integration/Je_PublicAndProtected2.joos", 42},
                {"/integration/Je_NativeMethod.joos", 42},
                {"/integration/Je_AbstractMethod.joos", 42},
                {"/integration/Je_StaticInterface.joos", 42},
                {"/integration/Je_ProtectedInterface.joos", 42},
                {"/integration/Je_NativeInterface.joos", 42},
                {"/integration/Je_FinalInterface.joos", 42},
                {"/integration/Je_NativeInterfaceMethod.joos", 42},
                {"/integration/Je_ProtectedInterfaceMethod.joos", 42},
                {"/integration/Je_FinalField.joos", 42},
                {"/integration/Je_AbstractField.joos", 42},
                {"/integration/Je_NativeField.joos", 42},
                {"/integration/Je_AbstractConstructor.joos", 42},
                {"/integration/Je_StaticConstructor.joos", 42},
                {"/integration/Je_FinalConstructor.joos", 42},
                {"/integration/Je_NativeConstructor.joos", 42},
                {"/integration/Je_LargeIntFail.joos", 42},
                {"/integration/Je_NoConstructor1.joos", 42},
                {"/integration/Je_NoConstructor2.joos", 42},
                {"/integration/Je_CastIDPLUSID.joos", 42},
                {"/integration/Je_CastEmpty.joos", 42},
                {"/integration/Je_CastNum.joos", 42}
        };

        return Arrays.asList(A1Tests);
    }

    @Test
    public void runTests(){
        File in = new File(this.getClass().getResource(_inputFile).getFile());
        String [] list = {in.getAbsolutePath()};
        int returnCode=joosCompiler.compile(list);
        assertThat(returnCode).isEqualTo(_expectedReturnCode);
    }

    @After
    public void cleanup(){
        CodeCleaner.cleanCode(CodeCleaner.DEFAULT_DIR);
    }
}

