/*******************************************************************************
 * DFA.java
 * 
 * A module implementing the DFA for the scanner.
 * ****************************************************************************/

package cs444.group9.Scanner;
import java.util.*;

public class DFA {
    // list of accepting states
    List <State> AcceptStates; 

    // (state, (int) char) -> state transition function
    // initially each array element is set to -1 (no transition)
    // the second integer is the ASCII character converted to integer
    EnumMap <State, HashMap<Character, State> > delta;

    /*******************************************************************************
     * DFA destructor
     * effects: creates a DFA with states stipulated in States
     * time : O(# states)
     * *****************************************************************************/
    public DFA(){
        AcceptStates = new ArrayList<State>();

        delta = new EnumMap <State, HashMap<Character, State> > (State.class);

        for (State state : State.values()) {
            HashMap<Character, State> h = new HashMap <Character, State>();
            delta.put(state, h);
        } // for
    } // DFA()

    /*******************************************************************************
     * Adds an accepting state
     * effects: adds newAccept to list of accepting states
     * time : O(1)
     * *****************************************************************************/
    public void AddAcceptState (State newAccept) {
        AcceptStates.add(newAccept);
    } // AddAcceptState()

    /*******************************************************************************
     * Determines if a state is accepting
     * time : O(# accepting states)
     * *****************************************************************************/
    public boolean isAccepting(State state){
        ListIterator<State> itr = AcceptStates.listIterator();
        while(itr.hasNext()){
            if(itr.next() == state) return true;
        } // while

        return false;
    } // isAccepting

    /*******************************************************************************
     * Adds transition (state, symbol) -> resultState to the DFA
     * time : O(# states)
     * *****************************************************************************/
    public void AddTransition (State state, char symbol, State resultState){
        delta.get(state).put(symbol, resultState);
    } // AddTransition(State, Char, State)

    /*******************************************************************************
     * Adds transition (state, symbol) -> resultState to the DFA for every 
     *      character in string
     * time : O(# states + |string|)
     * *****************************************************************************/
    public void AddTransition (State state, String string, State resultState){
        for (int i = 0; i < string.length(); ++i) {
            AddTransition(state, string.charAt(i), resultState);
        } // for
    } // AddTransition(State, String, State)

    /*******************************************************************************
     * Applies DFA to character c starting at state
     * notes: returns a valid state if there is a transition
     *        otherwise returns ST_ERR
     * time : O(# states)
     * *****************************************************************************/
    public State ApplyDFA (State state, char c){
        if(delta.get(state).containsKey(c)) return delta.get(state).get(c);
        else return State.ST_ERR;
    } // ApplyDFA()

    /*******************************************************************************
     * Prints the DFA
     * notes: returns a valid state if there is a transition
     *        otherwise returns ST_ERR
     * time : O(# transitions + # accepting states)
     * *****************************************************************************/
    public void printDFA(){
        System.out.println("Accepting States: ");
        ListIterator<State> itr = AcceptStates.listIterator();

        while(itr.hasNext()){
            System.out.println(itr.next());
        } // while

        System.out.println("Delta: ");

        for (State state : State.values()) {
            char c = 'A' - 65;
            for (int i = 0; i < 128; ++i){
                if(delta.get(state).containsKey( (char) (c+i) ))
                    System.out.println(state + " , " + (char) (c+i) + " -> " + delta.get(state).get((char) (c+i)));
            } // for
        } // for
    } // printDFA()
} // class DFA

