package com.company;

import model.AbstractLexer.*;
import model.AbstractParser;
import unit.Lexer;
import unit.Parser;

/*
* <program> ::= {<assignment>}* <evaluation>
* <assignment> ::= bool <variable> = <equivalence> ,
* <evaluation> ::= test <equivalence> ?
* <equivalence> ::= <implication> { <-> <implication> }*
* <implication> ::= <disjunction> { -> <disjunction> }*
* <disjunction> ::= <conjunction> { v <conjunction>}*
* <conjunction> ::= <negation> { ^ <negation> }*
* <negation> ::= <expression> [ ' ]
* <expression> ::= ( <equivalence> ) | <boolean>
* <boolean> ::= 1 | 0 | <variable>
* <variable> ::= A | B | ... | Z
*/

public class Main {

    public boolean evaluate(Lexer Lex, char[] sentence) {
        Lex.initialize(sentence);
        Lex.lex();
        // TODO: implement this method stub
        throw new UnsupportedOperationException();
    }

    public boolean accept(Lexer Lex, Tokens token) {
        boolean acceptable = true;
        if (Lex.TOKEN==token){
            Lex.lex();
        }
        else{
            acceptable = false;
            throw new UnsupportedOperationException("Lexer next token unacceptable. Accept method failure.");
        }
        return acceptable;
    }

    public void  expect(Lexer Lex, Tokens token) {
        if (Lex.TOKEN==token){
            Lex.lex();
        }
        else{
            throw new UnsupportedOperationException("Lexer next token unacceptable. Expect method failure.");
        }
        return;
    }

    public static void main(String[] args) {
        /*
        String sentence = "bool P = 0, bool Q = 0, bool R = P' ^ Q', test R?";
        Tokens[] tokens;

        model.AbstractLexer LEXER;
        LEXER = new Lexer();

        LEXER.initialize(sentence.toCharArray());
        do{
            LEXER.lex();
            System.out.println(LEXER.TOKEN);
        }while(LEXER.TOKEN != null);
        */
        char[] alpha = new char[26];
        for(int i = 0; i < 26; i++){
            alpha[i] = (char)(65 + i);
        }
        for(int i =0; i<alpha.length; i++){
            System.out.print(alpha[i]+",");
        }



    }
}
