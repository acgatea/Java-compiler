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
public class GeneralTest {
    public JoosCompiler joosCompiler;

    private String _inputFile;
    private int _expectedReturnCode;

    // Inject via constructor
    public GeneralTest(String inputFile, int expectedReturnCode) {
        this._inputFile = inputFile;
        this._expectedReturnCode = expectedReturnCode;
        joosCompiler=new JoosCompiler();
    }

    // name attribute is optional, provide an unique name for test
    // multiple parameters, uses Collection<Object[]>
    @Parameterized.Parameters(name = "File: {0}")
    public static Collection<Object[]> data() {
        Object[][] A1Tests=new Object[][]{
                {"/integration/J1_1_EmptyFile.joos", 0},
                {"/integration/J1_1_SpacesFile.joos", 0},
                //{"/integration/J1_1_Imports.joos", 0}, //TODO: move this to unit tests
                //{"/integration/J1_1_ImportsPackage.joos", 0}, //TODO: move this to unit tests
                //{"/integration/J1_1_ImportsPackageClass.joos", 0}, //TODO: move this to unit tests
                {"/integration/Je_BadOrderingPackageImports.joos", 42},
                {"/integration/Je_BadOrderingClassImports.joos", 42},
                {"/integration/Je_MultipleFieldsPerDecl.joos", 42},
                {"/integration/Je_MultipleVarsPerDecl.joos", 42},
                {"/integration/Je_MultipleClasses.joos", 42},
                {"/integration/Je_NestedClasses.joos", 42},
                {"/integration/Je_InterfaceConstant.joos", 42},
                {"/integration/Je_OmittedLocalInitializer.joos", 42},
                {"/integration/Je_GeneralForLoop.joos", 42},
                {"/integration/Je_ArraySuffix.joos", 42},
                {"/integration/Je_ArrayReturnSuffix.joos", 42},
                {"/integration/Je_ArrayData.joos", 42},
                {"/integration/Je_MultiArray.joos", 42},

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

