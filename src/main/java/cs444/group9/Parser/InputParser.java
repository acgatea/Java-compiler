/*******************************************************************************
 * InputParser.java
 * 
 * A module implementing the LR(1) Parser.
 * ****************************************************************************/


package cs444.group9.Parser;
import cs444.group9.Scanner.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class InputParser {
    public class ParsingError extends Exception {}
    String startState;

    int numRules;
    // number of the start rule (start -> BOF ... EOF)
    int startRule;
    // rule # (determined by count, starts at 0) mapsto list of terminals/nonterminals that make up the rule
    // the first element of the list is the LHS, the rest of the list is the RHS
    Map <Integer, List<String> > Rules;

    // list of actions in the LR(1) machine
    List <Action> actions;

    /*******************************************************************************
     * Reads file and stores LR(1) machine in the fields above
     * notes: the number and names of terminals, nonterminals should not be stored (irrelevant)
     *        fileName should be "machine.cfg"
     * time : O(|file|)
     * *****************************************************************************/ 
    public InputParser(String fileName){
        Rules = new HashMap<Integer, List<String> >();
        try{
            BufferedReader buf = new BufferedReader(new FileReader(fileName));

            // get the # of terminals, and read the terminals
            String line = buf.readLine();
            String [] words = line.split(" ");
            int num = Integer.parseInt(words[0]);
            for(int i =0; i < num; ++i) buf.readLine();

            // get the # of nonterminals, and read the nonterminals
            line = buf.readLine();
            words = line.split(" ");
            num = Integer.parseInt(words[0]);
            for(int i =0; i < num; ++i) buf.readLine();

            // get the start state
            line = buf.readLine();
            words = line.split(" ");
            startState = words[0];

            // get the # of rules, and read the rules
            line = buf.readLine();
            words = line.split(" ");
            numRules = Integer.parseInt(words[0]);

            for(int i =0; i < numRules; ++i) {
                line = buf.readLine();
                words = line.split(" ");

                List<String>  rule = new ArrayList<String>();
                for(String w : words){
                    rule.add(w);
                } // for
                Rules.put(i, rule);
            } // for

            // read (and ignore) the # of states, read the # of actions
            buf.readLine();
            line = buf.readLine();
            words = line.split(" ");
            num = Integer.parseInt(words[0]);
            actions = new ArrayList<Action>();
            // read actions
            for(int i =0; i < num; ++i) {
                line = buf.readLine();
                words = line.split(" ");
                Action newAction = new Action(Integer.parseInt(words[0]), words[1], words[2], Integer.parseInt(words[3]));
                actions.add(newAction);
            } // for

            buf.close();
        } catch(FileNotFoundException e){
            System.err.println(fileName);
            System.err.println("File does not exist.");
            // throw exception
        } catch(IOException ie){
            System.err.println("File cannot be read.");
            // throw exception
        } // catch
    } // InputParser()

    /*******************************************************************************
     * Find a valid action from initialState on nextSymbol
     * notes: returns ERROR action if none found
     * time : O(#actions)
     * *****************************************************************************/     
    Action findAction(int initialState, String nextSymbol){
        ListIterator<Action> itr = actions.listIterator();

        // search actions for an action
        while(itr.hasNext()){
            Action next = itr.next();
            // if next is the desired action, return next
            if(next.compareAction(initialState, nextSymbol)) return next;
        } // while
        // if no valid action found, return ERROR action
        return new Action(-1, "", "ERROR", -1);
    } // findAction()

    /*******************************************************************************
     * Parses tokens using the LR(1) machine
     * notes: returns Node of the root of the Parse tree
     * time : O(#tokens * #actions)
     * *****************************************************************************/      
    public Node parse (List <Token> tokens) throws ParsingError {
        ListIterator<Token> itr = tokens.listIterator();

        // stack of the states
        List<Integer> stateStack = new ArrayList<Integer>();

        // adds start state
        stateStack.add(0,0);

        // stack of the nodes
        List<Node> nodeStack = new ArrayList<Node>();

        while (itr.hasNext()) {
            // next token
            Token nextTok = itr.next();
            String nextSymbol = nextTok.returnKind();
            while (true) {
                // gets the next action; if not reduction, exit out of inner while loop
                Action nextAction = findAction(stateStack.get(0), nextSymbol);
                if(!nextAction.isReduction()) break;

                // get the rule to be reduced
                List <String> rule = Rules.get(nextAction.getRHS());
                // size of the  RHS of rule
                int sizeRHS = rule.size() - 1;

                // a queue of the nodes that are popped off the stack
                List <Node> children = new ArrayList<Node>();
                // pop sizeRHS states and nodes off the stacks; make a list of the nodes popped
                for(int j = 1; j <= sizeRHS; ++j){
                    // if either stack is empty, error
                    if (stateStack.isEmpty() || nodeStack.isEmpty()) {
                        System.err.println("Parsing Error: stack does not contain enough items needed to be popped off.");
                        throw new ParsingError();
                    } // if

                    stateStack.remove(0);
                    // pop the top node off the stack, and add it to the front of the queue
                    children.add(0,nodeStack.get(0));
                    nodeStack.remove(0);
                } // for
                // push a new (non-leaf) node onto the stack, with the LHS of rule as the symbol
                nodeStack.add(0,new Node(rule.get(0), children));

                // get the next action, with the LHS of rule as the symbol
                Action a = findAction(stateStack.get(0), rule.get(0));
                if(nextAction.isError()) {
                    System.err.println("Parsing Error");
                    nextTok.printErrToken();
                    throw new ParsingError();
                } // if
                // push the new state on to the stack
                stateStack.add(0, a.getRHS());
            } // while
            // add a new leaf node
            nodeStack.add(0,new Node(nextTok));

            // get the next action (if not shift then error)
            Action nextAction = findAction(stateStack.get(0), nextSymbol);
            if(nextAction.isError()) {
                System.err.println("Parsing Error");
                nextTok.printErrToken();
                throw new ParsingError();
            } // if
            stateStack.add(0,nextAction.getRHS());
        } // while

        // if node stack does not contain 3 nodes (BOF, tree, EOF) error
        if(nodeStack.size() != 3 && !nodeStack.get(0).isEOF()) {
            System.err.println("Parsing Error: input is done but stack is not empty yet.");
            throw new ParsingError();
        } // if

        return nodeStack.get(1);
    } // parse()
} // class InputParser
