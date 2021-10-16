package Unit.ScannerTests; //TODO: change the package name
import cs444.group9.Scanner.*;

import static org.assertj.core.api.Assertions.*;
import org.junit.Before;
import org.junit.Test;

public class DFATest{

    public DFA dfa;

    @Before
    public void setup(){
        dfa=new DFA();
        dfa.AddAcceptState(State.ST_ID);
    }

    // tests that accepting states are accepting
    @Test
    public void IsAcceptingState_AcceptingState_Test(){
        State stateToTest= State.ST_ID;
        boolean result=dfa.isAccepting(stateToTest);
        assertThat(result).isTrue();
        dfa.AddAcceptState(State.ST_ZERO);
        dfa.AddAcceptState(State.ST_NUM);

        result=dfa.isAccepting(stateToTest);
        assertThat(result).isTrue();
        stateToTest = State.ST_ZERO;
        result=dfa.isAccepting(stateToTest);
        assertThat(result).isTrue();
        stateToTest = State.ST_NUM;
        result=dfa.isAccepting(stateToTest);
        assertThat(result).isTrue();
    }

    @Test
    public void IsAcceptingState_NotAcceptingState_Test(){
        State stateToTest=State.ST_Start;
        boolean result=dfa.isAccepting(stateToTest);
        assertThat(result).isFalse();
    }

    // does AddTransition with 1 character work?
    @Test
    public void IsAddTransitionCharWorking_Test(){
        dfa.AddTransition(State.ST_Start, '0', State.ST_ZERO);
        State expected=State.ST_ZERO;
        State result= dfa.ApplyDFA(State.ST_Start, '0');
        assertThat(result).isEqualTo(expected);

        dfa.AddTransition(State.ST_NUM, '1', State.ST_NUM);
        result= dfa.ApplyDFA(State.ST_Start, '0');
        assertThat(result).isEqualTo(expected);
        expected = State.ST_NUM;
        result= dfa.ApplyDFA(State.ST_NUM, '1');
        assertThat(result).isEqualTo(expected);
    }

    // does ApplyDFA return ST_ERR when no valid transition?
    @Test
    public void NoValidTransitions_Test(){
        State expected=State.ST_ERR;
        // ST_START has some transitions but not on .
        State result= dfa.ApplyDFA(State.ST_Start, '.');
        assertThat(result).isEqualTo(expected);

        // ST_DOT has no transitions
        result= dfa.ApplyDFA(State.ST_DOT, 'l');
        assertThat(result).isEqualTo(expected);
    }

    // does AddTransition with string work?
    @Test
    public void IsAddTransitionStringWorking_Test(){
        dfa.AddTransition(State.ST_Start, "123456789", State.ST_NUM);
        State expected=State.ST_NUM;
        State result= dfa.ApplyDFA(State.ST_Start, '1');
        assertThat(result).isEqualTo(expected);
        result= dfa.ApplyDFA(State.ST_Start, '2');
        assertThat(result).isEqualTo(expected);
        result= dfa.ApplyDFA(State.ST_Start, '3');
        assertThat(result).isEqualTo(expected);
        result= dfa.ApplyDFA(State.ST_Start, '4');
        assertThat(result).isEqualTo(expected);
        result= dfa.ApplyDFA(State.ST_Start, '5');
        assertThat(result).isEqualTo(expected);
        result= dfa.ApplyDFA(State.ST_Start, '6');
        assertThat(result).isEqualTo(expected);
        result= dfa.ApplyDFA(State.ST_Start, '7');
        assertThat(result).isEqualTo(expected);
        result= dfa.ApplyDFA(State.ST_Start, '8');
        assertThat(result).isEqualTo(expected);
        result= dfa.ApplyDFA(State.ST_Start, '9');
        assertThat(result).isEqualTo(expected);

        dfa.AddTransition(State.ST_NUM,  "2", State.ST_NUM);
        result= dfa.ApplyDFA(State.ST_NUM, '2');
        assertThat(result).isEqualTo(expected);
    }
}