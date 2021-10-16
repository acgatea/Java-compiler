/*******************************************************************************
 * Action.java
 * 
 * A module implementing the Actions for the LR(1) parser.
 * ****************************************************************************/


package cs444.group9.Parser;

// each line of the LR(1) file is of the form: start symbol action end
public class Action {
    // LHS state
    int start;

    // symbol (for shift) or nonterminal (for reduce)
    String symbol;
    // "reduce" or "shift"
    String action;

    // RHS state (for shift) or rule (for reduce)
    int end;

    /*******************************************************************************
     * Action Constructor
     * time : O(1)
     * *****************************************************************************/        
    public Action(int s, String sym, String ac, int e){
        start = s;
        symbol = sym;
        action = ac;
        end = e;
    } // Action()

    /*******************************************************************************
     * Compares two actions
     * time : O(1)
     * *****************************************************************************/    
    public boolean compareAction(int s, String sym){
        return (start == s && symbol.equals(sym));
    } // compareAction()

    /*******************************************************************************
     * Getter for RHS
     * time : O(1)
     * *****************************************************************************/    
    public int getRHS(){
        return end;
    } // getRHS()

    /*******************************************************************************
     * Determines if action is a reduction
     * time : O(1)
     * *****************************************************************************/
    public boolean isReduction(){
        return action.equals("reduce");
    } // isReduction()

    /*******************************************************************************
     * Determines if action is an error
     * time : O(1)
     * *****************************************************************************/
    public boolean isError(){
        return action.equals("ERROR");
    } // isError()
} // class Action
