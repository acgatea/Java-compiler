package Unit.ParserTests; //TODO: change the package name
import cs444.group9.Parser.*;
import cs444.group9.Scanner.*;
import java.util.*;


import static org.assertj.core.api.Assertions.*;
import org.junit.Before;
import org.junit.Test;

public class ActionTest {



    @Test
    public void getRHS_Test() {
        Action a1 = new Action(0, "term", "reduce", 2);
        assertThat(a1.getRHS()).isEqualTo(2);
        Action a2 = new Action(2, "expr", "shift", 1);
        assertThat(a2.getRHS()).isEqualTo(1);
    }

    @Test
    public void isError_Test() {
        Action a1 = new Action(0, "", "ERROR", 0);
        assertThat(a1.isError()).isTrue();
        Action a2 = new Action(2, "expr", "shift", 1);
        assertThat(a2.isError()).isFalse();
        Action a3 = new Action(0, "term", "reduce", 2);
        assertThat(a3.isError()).isFalse();
    }

    @Test
    public void isReduction_Test() {
        Action a1 = new Action(0, "term", "reduce", 2);
        assertThat(a1.isReduction()).isTrue();
        Action a2 = new Action(2, "expr", "shift", 1);
        assertThat(a2.isReduction()).isFalse();
        Action a3 = new Action(0, "", "ERROR", 0);
        assertThat(a3.isReduction()).isFalse();
    }

    @Test
    public void compareAction_Test(){
        Action a = new Action(0, "term", "reduce", 2);
        assertThat(a.compareAction(0, "term")).isTrue();
        assertThat(a.compareAction(3, "term")).isFalse();
        assertThat(a.compareAction(0, "expr")).isFalse();
        assertThat(a.compareAction(2, "reduction")).isFalse();
    }

}