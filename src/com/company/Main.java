package com.company;

import model.AbstractLexer;
import unit.Lexer;

import static model.AbstractLexer.Tokens.*;

public class Main {
    /**
     * The next lexeme in the input (or <code>null</code>
     * if the lexeme is the only instance of its token),
     * updated on each call to {@link AbstractLexer#lex()}.
     * <p>
     * Do not redefine this field in the subclass.
     */
    public char[] LEXEME;

    /**
     * The token type corresponding to {@link AbstractLexer#LEXEME}
     * (or <code>null</code> if the lexeme is end of input)
     * updated on each call to {@link AbstractLexer#lex()}.
     * <p>
     * Do not redefine this field in the subclass.
     */
    public AbstractLexer.Tokens TOKEN;
    public static enum Tokens {
        BEGIN_BOOL,
        ASSIGNMENT,
        END_BOOL,
        BEGIN_TEST,
        END_TEST,
        EQUIVALENCE,
        IMPLICATION,
        DISJUNCTION,
        CONJUNCTION,
        NEGATION,
        OPEN_PAREN,
        CLOSE_PAREN,
        VARIABLE_NAME,
        TRUE_LITERAL,
        FALSE_LITERAL
    }

    private char[][] LEXEMES = new char[30][10];
    private int index = 0;

    // Getter for current index
    public int getIndex() {
        return index;
    }

    // Getter for current index
    public char[] getLEXEME(int position) {
        return LEXEMES[position];
    }

    // Setter for index
    public void setIndex(int newIndex) {
        this.index = newIndex;
    }


    /*
    Checks whether character is within the acceptable range of decimal values for letters (upper and lowercase)
     */
    public static boolean isAlpha(char target) {

        boolean in = false;

        if ((target > 64) && (target < 91) && !(target==86)) {
            in = true;
        } else if ((target > 96) && (target < 123)&& !(target==118)) {
            in = true;
        }
        return in;
    }
    /*
    Specifically looks at whether the lexeme being evaluated is of the bool variety.
     */
    public static boolean isBool(int a, int b, int c, int d) {
        boolean res = false;
        if ((a == 98 || a == 66) && (b == 111 || b == 79) && (c == 111 || c == 79) && (d == 108 || d == 76)) {
            res = true;
        }

        return res;
    }
    /*
    Specifically looks at whether the lexeme being evaluated is of the begin test variety.
     */
    public static boolean isTest(int a, int b, int c, int d) {
        boolean res = false;
        if ((a == 84 || a == 116) && (b == 69 || b == 101) && (c == 83 || c == 115) && (d == 84 || d == 116)) {
            res = true;
        }

        return res;
    }
    /*
    Refactor to check for anything not alphanumeric in the latin system
     */
    public static boolean isSymbol(char target) {

        boolean in = false;

        if ((target > 32) && (target < 50) && (target != 45)) {
            in = true;
        } else if ((target > 57) && (target < 65) && (target != 60)) {
            in = true;
        }
        else if ((target > 90) && (target < 97)) {
            in = true;
        }
        return in;
    }

    public static boolean isEquivalence(char a, char b, char c) {

        boolean in = false;

        if ((a == 60) && (b == 45) && (c == 62)) {
            in = true;
        }
        return in;
    }

    public static boolean isImplication(char a, char b) {

        boolean in = false;

        if ((a == 45) && (b == 62)) {
            in = true;
        }
        return in;
    }
    public void lex() {
        int ind = getIndex();

        LEXEME = getLEXEME(ind);

        if (LEXEME[0] == 40) {
            TOKEN = OPEN_PAREN;
        }
        else if(LEXEME[0] == 41){
            TOKEN = CLOSE_PAREN;
        }
        else if(LEXEME[0] == 44){
            TOKEN = END_BOOL;
        }
        else if(LEXEME[0] == 63){
            TOKEN = END_TEST;
        }
        else if(LEXEME[0] == 49){
            TOKEN = TRUE_LITERAL;
        }
        else if(LEXEME[0] == 48){
            TOKEN = FALSE_LITERAL;
        }
        else if(LEXEME[0] == 39){
            TOKEN = NEGATION;
        }
        else if(LEXEME[0] == 94){
            TOKEN = CONJUNCTION;
        }
        else if((LEXEME[0] == 118) || (LEXEME[0] == 86)){
            TOKEN =  DISJUNCTION;
        }
        else if(LEXEME[0] == 61){
            TOKEN = ASSIGNMENT;
        }
        else if(isBool(LEXEME[0],LEXEME[1],LEXEME[2],LEXEME[3])){
            TOKEN = BEGIN_BOOL;
        }
        else if(isTest(LEXEME[0],LEXEME[1],LEXEME[2],LEXEME[3])){
            TOKEN = BEGIN_TEST;
        }
        else if(isEquivalence(LEXEME[0],LEXEME[1],LEXEME[2])){
            TOKEN = EQUIVALENCE;
        }
        else if(isImplication(LEXEME[0],LEXEME[1])){
            TOKEN = IMPLICATION;
        }
        else if(isAlpha(LEXEME[0])){
            TOKEN = VARIABLE_NAME;
        }
        System.out.println(TOKEN);

        throw new UnsupportedOperationException("Lex method failed");
    }

    public static void main(String[] args) {
        AbstractLexer.Tokens TOKEN = null;
        //String sentence = "^vTEST bool ' P )";
        String sentence = "bool P = 1, bool Q = 0, test P -> Q <-> P' v Q?";
        String sentence2 = "bool P = 0, bool Q = 0, bool R = P' ^ Q', test R?";

        char[] test = sentence.toCharArray();
        char[] test2 = sentence2.toCharArray();
        Lexer L = new Lexer();
        L.initialize(test);



        //symbols = {'(', ')', ',', '?', '1', '0', '\'', '^', 'v', '='};
        int[] int_symbols = {40, 41, 44, 63, 49, 48, 39, 94, 118, 61};
        //String[] tokens = {"OPEN_PAREN", "CLOSE_PAREN", "END_BOOL", "END_TEST", "TRUE_LITERAL", "FALSE_LITERAL", "NEGATION", "CONJUNCTION", "DISJUNCTION", "ASSIGNMENT"};
        AbstractLexer.Tokens[] tokens = {OPEN_PAREN, CLOSE_PAREN, END_BOOL, END_TEST, TRUE_LITERAL, FALSE_LITERAL, NEGATION, CONJUNCTION, DISJUNCTION, ASSIGNMENT};

        /*
        for (int i = 0; i < test.length; i++) {

            for (int j = 0; j < int_symbols.length; j++) {
                if (int_symbols[j] == test[i]) {
                    //System.out.println(tokens[j]);
                    TOKEN = tokens[j];
                    System.out.println(TOKEN);
                }
            }


            if (((i + 3) < test.length) && (isBool(test[i], test[i + 1], test[i + 2], test[i + 3]))) {
                //System.out.println(String.format("%c%c%c%c = BEGIN_BOOL", test[i], test[i + 1], test[i + 2], test[i + 3]));
                TOKEN = BEGIN_BOOL;
                System.out.println(TOKEN);
                i+=3;


            } else if (((i + 3) < test.length) && isTest((int) test[i], (int) test[i + 1], (int) test[i + 2], (int) test[i + 3])) {
                //System.out.println(String.format("%c%c%c%c = BEGIN_TEST", test[i], test[i + 1], test[i + 2], test[i + 3]));
                TOKEN = BEGIN_TEST;
                System.out.println(TOKEN);
                i+=3;


            } else if (((i + 2) < test.length) && (test[i] == 60) && (test[i + 1] == 45) && (test[i + 2] == 62)) {
                //System.out.println(String.format("%c%c%c = EQUIVALENCE", test[i], test[i + 1], test[i+2]));
                TOKEN = EQUIVALENCE;
                System.out.println(TOKEN);
                i+=2;


            } else if (((i + 1) < test.length) && (test[i] == 45) && (test[i + 1] == 62)) {
                //System.out.println(String.format("%c%c = IMPLICATION", test[i], test[i+1]));
                TOKEN = IMPLICATION;
                System.out.println(TOKEN);
                i+=1;

            }

            // Tests for whitespace (32 in unicode)
            else if (test[i] == 32) {
                System.out.print("");

            }
            // 65 - 90 are capital letters; 97 - 122 are lowercase letters
            else if (isAlpha(test[i])) {
                //System.out.println(String.format("%c = VARIABLE", test[i]));
                TOKEN = VARIABLE_NAME;
                System.out.println(TOKEN);

            }


        }

         */


/*
Replace above methods with case-insensitivity and unicode range checking.
List of unicode/dec/chars: https://en.wikipedia.org/wiki/List_of_Unicode_characters
 */

    }
}
