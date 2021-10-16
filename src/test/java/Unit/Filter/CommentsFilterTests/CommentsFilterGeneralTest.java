package Unit.Filter.CommentsFilterTests;

import static org.assertj.core.api.Assertions.*;

import cs444.group9.Filter.CommentsFilter;
import org.junit.Before;
import org.junit.Test;

public class CommentsFilterGeneralTest {

    public CommentsFilter commentsFilter;

    // Inject via constructor
    public CommentsFilterGeneralTest() {
        commentsFilter=new CommentsFilter();
    }

    @Before
    public void setup(){
        commentsFilter=new CommentsFilter();
    }

    @Test
    public void setMultiFlagTest(){
        commentsFilter.setMulti(false);
        assertThat(commentsFilter.isMulti()).isFalse();
        commentsFilter.setMulti(true);
        assertThat(commentsFilter.isMulti()).isTrue();
    }

    @Test
    public void toggleMultiFlagTest(){
        commentsFilter.setMulti(true);
        commentsFilter.toggleMulti();
        assertThat(commentsFilter.isMulti()).isFalse();
        commentsFilter.toggleMulti();
        assertThat(commentsFilter.isMulti()).isTrue();
    }
}
