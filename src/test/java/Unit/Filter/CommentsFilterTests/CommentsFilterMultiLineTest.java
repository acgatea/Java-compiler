package Unit.Filter.CommentsFilterTests;

import cs444.group9.Filter.CommentsFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(value = Parameterized.class)
public class CommentsFilterMultiLineTest {
    public CommentsFilter commentsFilter;

    private String[] unfiltered;
    private String[] expected;

    // Inject via constructor
    public CommentsFilterMultiLineTest(String[] unfiltered, String[] expected) {
        this.unfiltered = unfiltered;
        this.expected = expected;
    }

    @Before
    public void setup(){
        commentsFilter=new CommentsFilter();
    }

    // name attribute is optional, provide an unique name for test
    // multiple parameters, uses Collection<Object[]>
    @Parameterized.Parameters(name = "filterLines()")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new String[]{"int a=5;"}, new String[]{"int a=5;"}},
                {new String[]{"stuff here /*comments", "here */ and continued here"},
                        new String[]{"stuff here  ", "  and continued here"}},
                {new String[]{"/*all comments", "are here */"},
                        new String[]{" ", " "}},
                {new String[]{"three lines /*line 1", "line 2","line 3*/"},
                        new String[]{"three lines  ","", " "}}
        });
    }

    @Test
    public void multiLineComment_Test() {
        String[] filtered=commentsFilter.FilterLines(unfiltered);
        assertThat(filtered).isEqualTo(expected);
    }
}
