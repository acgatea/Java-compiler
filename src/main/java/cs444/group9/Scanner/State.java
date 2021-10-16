/*******************************************************************************
 * State.java
 * 
 * A module listing the states of the DFA.
 * ****************************************************************************/
package cs444.group9.Scanner;

// states of the DFA
public enum State{     
    ST_ERR,         // error state (indicating there was no valid transition)
    ST_Start,       // initial state (accepting)
    ST_BOF,         // BOF
    ST_EOF,         // EOF
    ST_ID,          // IDENTIFIER or KEYWORD
    ST_ZERO,        // zero
    ST_NUM,         // non-zero number
    ST_COMMA,       // ','
    ST_SEMICOLON,   // ';'
    ST_COLON,       // ':'
    ST_LPAREN,      // '('
    ST_RPAREN,      // ')'
    ST_LBRACK,      // '['
    ST_RBRACK,      // ']'
    ST_LBRACE,      // '{'
    ST_RBRACE,      // '}'
    ST_DOT,         // '.'
    ST_MINUS,       // '-'
    ST_DECR,        // '--' (an error state)
    ST_PLUS,        // '+'
    ST_INCR,        // '++' (an error state)
    ST_STAR,        // '*'
    ST_PCT,         // '%'
    ST_SLASH,       // '/'
    ST_EXCLAM,      // '!'
    ST_NE,          // '!='
    ST_BECOMES,     // '='
    ST_EQUALS,      // '=='
    ST_LT,          // '<'
    ST_LE,          // '<='
    ST_GT,          // '>'
    ST_GE,          // '>='
    ST_AMP,         // '&'
    ST_AMPAMP,      // '&&'
    ST_OR,          // '|'
    ST_OROR,        // '||'
    ST_LSINGQ,      // '
    ST_LSINGQCHAR,  // '_ or '\_
    ST_LSINGQESC,   // '\ (backslash)
    ST_LSINGQ_ZERO3_OCTAL1, // '\0-3
    ST_LSINGQ_ZERO3_OCTAL2, // '\0-3_
    ST_LSINGQ_OCTAL1,       // '\4-7
    ST_CHARLIT,     // '_' character literal
    ST_LDOUBLEQSTRING, // "___
    ST_LDOUBLEQSTRINGESC, // "___\
    ST_LDOUBLEQ_ZERO3_OCTAL1, // '\0-3
    ST_LDOUBLEQ_ZERO3_OCTAL2, // '\0-3_
    ST_LDOUBLEQ_OCTAL1,       // '\4-7
    ST_STRINGLIT,   // "____"
    ST_WHITESPACE   // for whitespace (non-accepting)
} // enum State