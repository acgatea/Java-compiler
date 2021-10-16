/*******************************************************************************
 * InputScanner.java
 * 
 * A module implementing the Scanner.
 * ****************************************************************************/

package cs444.group9.Scanner;
import java.util.*;


public class InputScanner{
    public class ScanningError extends Exception {}

    // DFA for the scanner
    DFA tokenDFA;

    /*******************************************************************************
     * Expands escape sequences in s and returns the result
     * time : O(|s|)
     * *****************************************************************************/
    public String expandEscape(String s){
        StringBuffer buf = new StringBuffer();
        int len = s.length();
        for(int i = 1; i < len-1; ++i){
            if(s.charAt(i) != '\\') buf.append(s.charAt(i));
            else {
                // the next character is an escape sequence
                ++i;
                char c = s.charAt(i);
                if(c == 'n') buf.append('\n');
                else if(c == 't') buf.append('\t');
                else if(c == 'r') buf.append('\r');
                else if(c == '\'') buf.append('\'');
                else if(c == '"') buf.append('"');
                else if(c == 'b') buf.append('\b');
                else if(c == 'f') buf.append('\f');
                else if(c == '\\') buf.append('\\');
                else {
                    // octal escape
                    // string consisting of number in the octal escape
                    String num = ""+ c;
                    if(i+1 < len && s.charAt(i+1) >= '0' && s.charAt(i+1) <= '7') {
                        // if 2nd character is octal digit, add it to num
                        num += s.charAt(i + 1);
                        i++;
                        if (i + 1 < len && s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '7' && c >= '0' && c <= '3') {
                            // if 3rd character is octal digit and c is 0-3, add it to num
                            num += s.charAt(i + 1);
                            i++;
                        } // if
                    }   // if
                    try {
                        int val= Integer.parseInt(num,8);
                        buf.append((char) val);
                    } catch (NumberFormatException e) {} // no error
                } // else
            } // else
        } // for
        return buf.toString();
    } // expandEscape()

    /*******************************************************************************
     * InputScanner constructor
     * effects: creates the DFA, stores it in tokenDFA
     * time : O(|DFA|)
     * *****************************************************************************/
    public InputScanner(){
        // create DFA
        tokenDFA = new DFA();

        // add accepting states
        tokenDFA.AddAcceptState(State.ST_ID);
        tokenDFA.AddAcceptState(State.ST_ZERO);
        tokenDFA.AddAcceptState(State.ST_NUM);
        tokenDFA.AddAcceptState(State.ST_COMMA);
        tokenDFA.AddAcceptState(State.ST_SEMICOLON);
        tokenDFA.AddAcceptState(State.ST_COLON);
        tokenDFA.AddAcceptState(State.ST_LPAREN);
        tokenDFA.AddAcceptState(State.ST_RPAREN);
        tokenDFA.AddAcceptState(State.ST_LBRACK);
        tokenDFA.AddAcceptState(State.ST_RBRACK);
        tokenDFA.AddAcceptState(State.ST_LBRACE);
        tokenDFA.AddAcceptState(State.ST_RBRACE);
        tokenDFA.AddAcceptState(State.ST_DOT);
        tokenDFA.AddAcceptState(State.ST_MINUS);
        tokenDFA.AddAcceptState(State.ST_PLUS);
        tokenDFA.AddAcceptState(State.ST_STAR);
        tokenDFA.AddAcceptState(State.ST_PCT);
        tokenDFA.AddAcceptState(State.ST_SLASH);
        tokenDFA.AddAcceptState(State.ST_EXCLAM);
        tokenDFA.AddAcceptState(State.ST_NE);
        tokenDFA.AddAcceptState(State.ST_BECOMES);
        tokenDFA.AddAcceptState(State.ST_EQUALS);
        tokenDFA.AddAcceptState(State.ST_LT);
        tokenDFA.AddAcceptState(State.ST_LE);
        tokenDFA.AddAcceptState(State.ST_GT);
        tokenDFA.AddAcceptState(State.ST_GE);
        tokenDFA.AddAcceptState(State.ST_AMP);
        tokenDFA.AddAcceptState(State.ST_AMPAMP);
        tokenDFA.AddAcceptState(State.ST_OR);
        tokenDFA.AddAcceptState(State.ST_OROR);
        tokenDFA.AddAcceptState(State.ST_CHARLIT);
        tokenDFA.AddAcceptState(State.ST_STRINGLIT);

        // string of special characters
        String JavaLetters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ$_";
        String JavaDigits = "0123456789";
        // whitespace characters that are not line terminators
        String whitespace = " \t\f"; 

        // list of all ASCII characters (0-127) except ' " \ \n \r
        String asciiChar = "";
        // list of all ASCII characters (0-127) except ' " \ \n \r and 0-7
        String nonOctalASCII = "";
        for(int q = 0; q < 128; ++q){
            if (q >= 48 && q <= 55) continue; // ignore numbers
            char c = (char) q;
            if(c != '\'' && c != '\"' && c != '\\' && c != '\r' && c != '\n') nonOctalASCII += c;
        } // for
        asciiChar = nonOctalASCII + "01234567";

        // list of the second character in the escape sequences (after \)
        String escapeChar = "\'\"\\rfntb";
        // list of octal digits
        String octalDigits = "01234567";

        // add transitions
        tokenDFA.AddTransition(State.ST_Start, JavaLetters, State.ST_ID);
        tokenDFA.AddTransition(State.ST_ID, JavaLetters+JavaDigits, State.ST_ID);
        tokenDFA.AddTransition(State.ST_Start, '0', State.ST_ZERO);
        tokenDFA.AddTransition(State.ST_Start, "123456789", State.ST_NUM);
        tokenDFA.AddTransition(State.ST_NUM, JavaDigits, State.ST_NUM);
        tokenDFA.AddTransition(State.ST_Start, ',', State.ST_COMMA);
        tokenDFA.AddTransition(State.ST_Start, ';', State.ST_SEMICOLON);
        tokenDFA.AddTransition(State.ST_Start, ':', State.ST_COLON);
        tokenDFA.AddTransition(State.ST_Start, '(', State.ST_LPAREN);
        tokenDFA.AddTransition(State.ST_Start, ')', State.ST_RPAREN);
        tokenDFA.AddTransition(State.ST_Start, '[', State.ST_LBRACK);
        tokenDFA.AddTransition(State.ST_Start, ']', State.ST_RBRACK);
        tokenDFA.AddTransition(State.ST_Start, '{', State.ST_LBRACE);
        tokenDFA.AddTransition(State.ST_Start, '}', State.ST_RBRACE);
        tokenDFA.AddTransition(State.ST_Start, '.', State.ST_DOT);
        tokenDFA.AddTransition(State.ST_Start, '-', State.ST_MINUS);
        tokenDFA.AddTransition(State.ST_MINUS, '-', State.ST_DECR);
        tokenDFA.AddTransition(State.ST_Start, '+', State.ST_PLUS);
        tokenDFA.AddTransition(State.ST_PLUS, '+', State.ST_INCR);
        tokenDFA.AddTransition(State.ST_Start, '*', State.ST_STAR);
        tokenDFA.AddTransition(State.ST_Start, '%', State.ST_PCT);
        tokenDFA.AddTransition(State.ST_Start, '/', State.ST_SLASH);
        tokenDFA.AddTransition(State.ST_Start, '!', State.ST_EXCLAM);
        tokenDFA.AddTransition(State.ST_EXCLAM, '=', State.ST_NE);
        tokenDFA.AddTransition(State.ST_Start, '=', State.ST_BECOMES);
        tokenDFA.AddTransition(State.ST_BECOMES, '=', State.ST_EQUALS);
        tokenDFA.AddTransition(State.ST_Start, '<', State.ST_LT);
        tokenDFA.AddTransition(State.ST_LT, '=', State.ST_LE);
        tokenDFA.AddTransition(State.ST_Start, '>', State.ST_GT);
        tokenDFA.AddTransition(State.ST_GT, '=', State.ST_GE);
        tokenDFA.AddTransition(State.ST_Start, '&', State.ST_AMP);
        tokenDFA.AddTransition(State.ST_AMP, '&', State.ST_AMPAMP);
        tokenDFA.AddTransition(State.ST_Start, '|', State.ST_OR);
        tokenDFA.AddTransition(State.ST_OR, '|', State.ST_OROR);

        tokenDFA.AddTransition(State.ST_Start, "\'", State.ST_LSINGQ); // left '
        tokenDFA.AddTransition(State.ST_LSINGQ, asciiChar + "\"", State.ST_LSINGQCHAR); // '_ one character
        tokenDFA.AddTransition(State.ST_LSINGQ, '\\', State.ST_LSINGQESC); // '\ backslash
        tokenDFA.AddTransition(State.ST_LSINGQESC, escapeChar, State.ST_LSINGQCHAR); // '\_ character after backslash
        tokenDFA.AddTransition(State.ST_LSINGQESC, "0123", State.ST_LSINGQ_ZERO3_OCTAL1); // '\_ 0-3 after backslash
        tokenDFA.AddTransition(State.ST_LSINGQ_ZERO3_OCTAL1, octalDigits, State.ST_LSINGQ_ZERO3_OCTAL2); // '\__ 2nd octal digit after backslash
        tokenDFA.AddTransition(State.ST_LSINGQ_ZERO3_OCTAL2, octalDigits, State.ST_LSINGQCHAR); // '\___ 3rd octal digit after backslash
        tokenDFA.AddTransition(State.ST_LSINGQ_ZERO3_OCTAL1, "\'", State.ST_CHARLIT); // '_'
        tokenDFA.AddTransition(State.ST_LSINGQ_ZERO3_OCTAL2, "\'", State.ST_CHARLIT); // '_'
        tokenDFA.AddTransition(State.ST_LSINGQESC, "4567", State.ST_LSINGQ_OCTAL1); // '\_ 4-7 (octal) after backslash
        tokenDFA.AddTransition(State.ST_LSINGQ_OCTAL1, octalDigits, State.ST_LSINGQCHAR); // '\__ 2 octal digits after backslash
        tokenDFA.AddTransition(State.ST_LSINGQ_OCTAL1, "\'", State.ST_CHARLIT); // '_'
        tokenDFA.AddTransition(State.ST_LSINGQCHAR, "\'", State.ST_CHARLIT); // '_'

        tokenDFA.AddTransition(State.ST_Start, "\"", State.ST_LDOUBLEQSTRING);	// left "
        tokenDFA.AddTransition(State.ST_LDOUBLEQSTRING, "\"" , State.ST_STRINGLIT);	// "____"
        tokenDFA.AddTransition(State.ST_LDOUBLEQSTRING, asciiChar + "\'", State.ST_LDOUBLEQSTRING); // "__ 1 character
        tokenDFA.AddTransition(State.ST_LDOUBLEQSTRING, '\\', State.ST_LDOUBLEQSTRINGESC); // "__\  backslash
        tokenDFA.AddTransition(State.ST_LDOUBLEQSTRINGESC, escapeChar, State.ST_LDOUBLEQSTRING); // "__\_ character after backslash
        tokenDFA.AddTransition(State.ST_LDOUBLEQSTRINGESC, "0123", State.ST_LDOUBLEQ_ZERO3_OCTAL1); // "\_ 0-3 after backslash
        tokenDFA.AddTransition(State.ST_LDOUBLEQ_ZERO3_OCTAL1, octalDigits, State.ST_LDOUBLEQ_ZERO3_OCTAL2); // "\__ 2nd octal digit after backslash
        tokenDFA.AddTransition(State.ST_LDOUBLEQ_ZERO3_OCTAL2, octalDigits, State.ST_LDOUBLEQSTRING); // "\___ 3rd octal digit after backslash
        tokenDFA.AddTransition(State.ST_LDOUBLEQ_ZERO3_OCTAL1, nonOctalASCII + "\'", State.ST_LDOUBLEQSTRING); // end of octal
        tokenDFA.AddTransition(State.ST_LDOUBLEQ_ZERO3_OCTAL1, '\\', State.ST_LDOUBLEQSTRINGESC); // // "__\  backslash
        tokenDFA.AddTransition(State.ST_LDOUBLEQ_ZERO3_OCTAL1, "\"" , State.ST_STRINGLIT);	// "____"
        tokenDFA.AddTransition(State.ST_LDOUBLEQ_ZERO3_OCTAL2, nonOctalASCII + "\'", State.ST_LDOUBLEQSTRING); // end of octal
        tokenDFA.AddTransition(State.ST_LDOUBLEQ_ZERO3_OCTAL2, '\\', State.ST_LDOUBLEQSTRINGESC); // // "__\  backslash
        tokenDFA.AddTransition(State.ST_LDOUBLEQ_ZERO3_OCTAL2, "\"" , State.ST_STRINGLIT);	// "____"
        tokenDFA.AddTransition(State.ST_LDOUBLEQSTRINGESC, "4567", State.ST_LDOUBLEQ_OCTAL1); // '\_ 4-7 (octal) after backslash
        tokenDFA.AddTransition(State.ST_LDOUBLEQ_OCTAL1, octalDigits, State.ST_LDOUBLEQSTRING); // '\__ 2 octal digits after backslash
        tokenDFA.AddTransition(State.ST_LDOUBLEQ_OCTAL1, nonOctalASCII + "\'", State.ST_LDOUBLEQSTRING); // end of octal
        tokenDFA.AddTransition(State.ST_LDOUBLEQ_OCTAL1, '\\', State.ST_LDOUBLEQSTRINGESC); // // "__\  backslash
        tokenDFA.AddTransition(State.ST_LDOUBLEQ_OCTAL1, "\"" , State.ST_STRINGLIT);	// "____"

        tokenDFA.AddTransition(State.ST_Start, whitespace, State.ST_WHITESPACE) ;
        tokenDFA.AddTransition(State.ST_WHITESPACE, whitespace, State.ST_WHITESPACE) ;

    } // InputScanner()

    /*******************************************************************************
     * Scans input, returns list of tokens
     * notes: input must be comment-free input
     * time : O(|input| + |DFA|)
     * *****************************************************************************/
    public List<Token> scan(String input) throws ScanningError{
        // current line
        int lineNum = 1;

        // result (to be returned)
        List <Token> Result = new ArrayList<Token>();

        // add BOF token
        Token tok = new Token ("BOF", 0, 0);
        tok.getKind(State.ST_BOF, "BOF");
        Result.add(tok);

        Scanner s = new Scanner (input);

        // read the input line-by-line
        while (s.hasNextLine()){
            // the next line
            String line = s.nextLine();

            // if line is empty, go to the next line
            if(line.length() == 0) {
                lineNum++;
                continue;
            } // if

            // index where the current run of the DFA started
            int currStartIndex = 0;

            // the last accepting state, and the index where it occurs
            State lastAccepting = State.ST_ERR;
            int lastIndex = 0;

            // the current state
            State currState = State.ST_Start;

            // read line character-by-character, applying the DFA transitions
            int lineLength = line.length();
            for (int i = 0; i < lineLength; ++i){
                // get the next characater
                char c = line.charAt(i);
                if(currState == State.ST_WHITESPACE && c != ' ' && c != '\n'
                        && c  != '\r' && c != '\t' && c != '\f'){
                    // if the last state was WHITESPACE and the next char is not whitespace,
                    // restart the state variables (this is the start of a new word)
                    currState = State.ST_Start;
                    lastIndex = i;
                    currStartIndex = i;
                    lastAccepting = State.ST_ERR;
                } // if
                // apply the DFA to c and determine if the new state is accepting
                currState = tokenDFA.ApplyDFA(currState, c);
                boolean currStateIsAccepting = tokenDFA.isAccepting(currState);
                if(currStateIsAccepting) {
                    // if the new state is accepting, update the last accept state
                    lastIndex = i;
                    lastAccepting = currState;
                } // if

                if((i == lineLength-1 && currStateIsAccepting)
                        || (currState == State.ST_ERR && lastAccepting != State.ST_ERR)) {
                    // if either this is the last character in the line and currState is accepting
                    // or currState is ST_ERR and there was a lastAccepting state, then tokenize
                    String lexeme = line.substring(currStartIndex, lastIndex+1);

                    // expand escape sequences of string/char literal
                    if(lastAccepting == State.ST_STRINGLIT || lastAccepting == State.ST_CHARLIT)
                        lexeme = expandEscape(lexeme);

                    Token t = new Token (lexeme, lineNum, currStartIndex+1);

                    String k = t.getKind(lastAccepting, lexeme);

                    // if error is found by getKind, exit
                    if(k.equals("ERROR")) {
                        s.close();
                        throw new ScanningError();
                    } // if

                    // if this is the last character on the line, update _isLastTokenMinus
                    if(i == line.length()-1) t.setLastTokenMinus(false);

                    Result.add(t);

                    // restart the dfa at index lastIndex+1 of line
                    i = lastIndex;

                    // reset the other state variables for the next run through the dfa
                    currStartIndex = i+1;
                    lastIndex = i+1;
                    lastAccepting = State.ST_ERR;
                    currState = State.ST_Start;
                } else if ((currState != State.ST_WHITESPACE && i == lineLength-1 && ! currStateIsAccepting)
                        || (currState == State.ST_ERR && lastAccepting == State.ST_ERR)
                        || currState == State.ST_DECR || currState == State.ST_INCR){
                    // if either this is the last character and currState is not accepting and is not WHITESPACE
                    // or if currState is ERROR and there was no previous last accepting state
                    // or if currState is one of DECR, INCR then produce error
                    System.err.println("Tokenization Error at Line "  + lineNum + " , Col " + (currStartIndex+1));
                    System.err.println("Cannot tokenize \"" + line.substring(currStartIndex, i+1) + "\"");

                    s.close();
                    throw new ScanningError();
                } // else
                // otherwise, continue applying the dfa (to the next iteration of the for-loop)
            } // for
            // go to the next line
            lineNum++;
        } // while
        // add EOF token
        Token token = new Token ("EOF", 0, 0);
        token.getKind(State.ST_EOF, "EOF");
        Result.add(token);

        s.close();

        return Result;
    } // scan
}
