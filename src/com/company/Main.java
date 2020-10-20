package com.company;

import model.AbstractLexer;
import model.AbstractParser;
import unit.Lexer;
import unit.Parser;

public class Main {

    public boolean evaluate(AbstractLexer Lex, char[] sentence) {
        Lex.initialize(sentence);
        Lex.lex();
        // TODO: implement this method stub
        throw new UnsupportedOperationException();
    }



    public static void main(String[] args) {
        String sentence = "bool P = 0, bool Q = 0, bool R = P' ^ Q', test R?";
        AbstractLexer.Tokens[] tokens;

        model.AbstractLexer LEXER;
        LEXER = new Lexer();

        LEXER.initialize(sentence.toCharArray());
        do{
            LEXER.lex();
            System.out.println(LEXER.TOKEN);
        }while(LEXER.TOKEN != null);


    }
}
