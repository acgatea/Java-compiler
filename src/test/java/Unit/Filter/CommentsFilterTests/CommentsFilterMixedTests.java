package Unit.Filter.CommentsFilterTests;

import cs444.group9.Filter.CommentsFilter;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CommentsFilterMixedTests {
    public CommentsFilter commentsFilter;

    public CommentsFilterMixedTests(){
        commentsFilter=new CommentsFilter();
    }

    @Test
    public void mixed_SingleLineTest() {
        String unfiltered="/*what we got here*/stuff//nothing here";
        String expected=" stuff ";
        String filtered=commentsFilter.FilterLine(unfiltered);
        assertThat(filtered).isEqualTo(expected);
    }

    @Test
    public void mixed_multiLineTest(){
        String[] unfiltered= new String[]{"begin/*line 1","line 2 */ line 2//blahblah","line 3//line 3"};
        String[] expected = new String[]{"begin ","  line 2 ","line 3 "};
        String[] filtered=commentsFilter.FilterLines(unfiltered);
        assertThat(filtered).isEqualTo(expected);
    }
}
