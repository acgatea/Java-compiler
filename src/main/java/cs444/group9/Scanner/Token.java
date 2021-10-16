/*******************************************************************************
 * Token.java
 * 
 * A module implementing the Scanner tokens.
 * ****************************************************************************/

package cs444.group9.Scanner;
import java.util.Arrays;

public class Token {
    String kind;
    String lexeme;
    Integer lineNum;
    Integer colNum;
    static boolean _isLastTokenMinus=false;

    /*******************************************************************************
     * Setter for _isLastTokenMinus
     * time : O(1)
     * *****************************************************************************/        
    public void setLastTokenMinus(boolean flag){
        _isLastTokenMinus=flag;
    } // setLastTokenMinus()

    /*******************************************************************************
     * Getter for _isLastTokenMinus
     * time : O(1)
     * *****************************************************************************/ 
    public boolean isLastTokenMinus(){
        return _isLastTokenMinus;
    } // isLastTokenMinus()

    /*******************************************************************************
     * Getter for kind
     * time : O(1)
     * *****************************************************************************/
    public String returnKind(){ return kind; }

    /*******************************************************************************
     * Getter for lexeme
     * time : O(1)
     * *****************************************************************************/
    public String returnLexeme(){ return lexeme; }

    // list of reserved keywords
    private final String[] _RESERVED_KEYWORDS = {
            "abstract",
            "boolean",
            "break",
            "byte",
            "case",
            "catch",
            "char",
            "class",
            "const",
            "continue",
            "default",
            "do",
            "double",
            "else",
            "extends",
            "final",
            "finally",
            "float",
            "for",
            "goto",
            "if",
            "implements",
            "import",
            "instanceof",
            "int",
            "interface",
            "long",
            "native",
            "new",
            "package",
            "private",
            "protected",
            "public",
            "return",
            "short",
            "static",
            "strictfp",
            "super",
            "switch",
            "synchronized",
            "this",
            "throw",
            "throws",
            "transient",
            "try",
            "void",
            "volatile",
            "while"
    }; // _RESERVED_KEYWORDS

    /*******************************************************************************
     * Determines kind based on token info
     * effects: stores result in kind field
     *          if not a valid token, gives error message and stores K_ERR in kind
     * time : O(#states)
     * *****************************************************************************/ 
    public String getKind(State finalState, String lexeme) {
        switch (finalState) {
            case ST_ZERO:
                kind="IntegerLiteral";
                break;
            case ST_NUM:
                kind="IntegerLiteral";
                //check range for the special case
                if(_isLastTokenMinus){
                    try {
                        long val=Long.parseLong(lexeme);
                        if(val <= 2147483648l){
                            kind="IntegerLiteral";
                        } else {
                            kind="ERROR";
                            System.err.println("Number out of range.");
                        } // else
                    } catch (NumberFormatException e){
                        kind="ERROR";
                    } // catch
                } else {
                    try {
                        Integer.parseInt(lexeme);
                    } catch (NumberFormatException e){ 
                        // out of range
                        kind="ERROR";
                        System.err.println("Number out of range.");
                    } // catch
                } // else
                break;
            case ST_BOF:
                kind="BOF";
                break;
            case ST_EOF:
                kind="EOF";
                break;
            case ST_COMMA:
                kind="COMMA";
                break;
            case ST_SEMICOLON:
                kind="SEMICOLON";
                break;
            case ST_COLON:
                kind="COLON";
                break;
            case ST_LPAREN:
                kind="LPAREN";
                break;
            case ST_RPAREN:
                kind="RPAREN";
                break;
            case ST_LBRACK:
                kind="LBRACK";
                break;
            case ST_RBRACK:
                kind="RBRACK";
                break;
            case ST_LBRACE:
                kind="LBRACE";
                break;
            case ST_RBRACE:
                kind="RBRACE";
                break;
            case ST_DOT:
                kind="DOT";
                break;
            case ST_MINUS:
                kind="MINUS";
                break;
            case ST_PLUS:
                kind="PLUS";
                break;
            case ST_STAR:
                kind="STAR";
                break;
            case ST_PCT:
                kind="PCT";
                break;
            case ST_SLASH:
                kind="SLASH";
                break;
            case ST_EXCLAM:
                kind="EXCLAM";
                break;
            case ST_NE:
                kind="NE";
                break;
            case ST_BECOMES:
                kind="BECOMES";
                break;
            case ST_EQUALS:
                kind="EQUALS";
                break;
            case ST_LT:
                kind="LT";
                break;
            case ST_LE:
                kind="LE";
                break;
            case ST_GT:
                kind="GT";
                break;
            case ST_GE:
                kind="GE";
                break;
            case ST_AMP:
                kind="AMP";
                break;
            case ST_AMPAMP:
                kind="AMPAMP";
                break;
            case ST_OR:
                kind="OR";
                break;
            case ST_OROR:
                kind="OROR";
                break;
            case ST_CHARLIT:
                kind="CharacterLiteral";
                break;
            case ST_STRINGLIT:
                kind="StringLiteral";
                break;
            default:
                break;
        } // switch
        if(finalState == State.ST_ID) {
            if (lexeme.equals("extends")){
                kind="EXTENDS";
            } else if (lexeme.equals("implements")){
                kind="IMPLEMENTS";
            } else if (lexeme.equals("static")){
                kind="STATIC";
            } else if(lexeme.equals("import")){
                kind="IMPORT";
            } else if(lexeme.equals("package")){
                kind="PACKAGE";
            } else if(lexeme.equals("public")){
                kind="PUBLIC";
            } else if(lexeme.equals("interface")){
                kind="INTERFACE";
            } else if(lexeme.equals("protected")){
                kind="PROTECTED";
            } else if(lexeme.equals("abstract")){
                kind="ABSTRACT";
            } else if(lexeme.equals("native")){
                kind="NATIVE";
            } else if(lexeme.equals("while")){
                kind="WHILE";
            } else if(lexeme.equals("for")){
                kind="FOR";
            } else if(lexeme.equals("boolean")){
                kind="BOOLEAN";
            } else if(lexeme.equals("int")){
                kind="INT";
            } else if(lexeme.equals("char")){
                kind="CHAR";
            } else if(lexeme.equals("byte")){
                kind="BYTE";
            } else if(lexeme.equals("short")){
                kind="SHORT";
            } else if(lexeme.equals("null")){
                kind="NULL";
            } else if(lexeme.equals("this")){
                kind="THIS";
            } else if(lexeme.equals("true") || lexeme.equals("false")){
                kind="BooleanLiteral";
            } else if(lexeme.equals("instanceof")){
                kind="INSTANCEOF";
            } else if(lexeme.equals("class")){
                kind="CLASS";
            } else if(lexeme.equals("if")){
                kind="IF";
            } else if(lexeme.equals("else")){
                kind="ELSE";
            } else if(lexeme.equals("final")){
                kind="FINAL";
            } else if(lexeme.equals("new")){
                kind="NEW";
            } else if(lexeme.equals("return")){
                kind="RETURN";
            } else if(lexeme.equals("void")){
                kind="VOID";
            } else if(!Arrays.asList(_RESERVED_KEYWORDS).contains(lexeme)){
                kind="ID";
            } // else
        } // if
        if(kind.equals("ERROR")){
                printErrToken();
        } // if

        setLastTokenMinus(kind.equals("MINUS"));
        return kind;
    } // getKind()

    /*******************************************************************************
     * Token constructor
     * notes: default kind is K_ERR
     * time : O(1)
     * *****************************************************************************/ 
    public Token(String lexeme, int lineNum, int colNum){
            this.kind = "ERROR";
            this.lexeme = lexeme;
            this.lineNum = lineNum;
            this.colNum = colNum;
    } // Token()

    /*******************************************************************************
     * Prints info for token
     * time : O(1)
     * *****************************************************************************/         
    public void printToken(){
            System.out.println("Kind: " + kind + " , Lexeme: " + lexeme);
    } // printToken()

    /*******************************************************************************
     * Prints info for error token
     * time : O(1)
     * *****************************************************************************/ 
    public void printErrToken(){
        System.err.println("Invalid token at line: " + lineNum + " , column " + colNum);
    } // printErrToken()
} // class Token
