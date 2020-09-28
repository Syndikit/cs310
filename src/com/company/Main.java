package com.company;

import java.util.Arrays;

public class Main {

    static char[] open_paren = new char[]{'('};
    static char[] close_paren = new char[]{')'};
    static char[] end_bool = {','};
    static char[] begin_test = new char[]{'t', 'e', 's', 't'};
    static char[] end_test = new char[]{'?'};
    static char[] bool_type = new char[]{'b', 'o', 'o', 'l'};
    static char[] true_1 = new char[]{'1'};
    static char[] false_0 = new char[]{'0'};
    static char[] negation = new char[]{'\''};
    static char[] conjunction = new char[]{'^'};
    static char[] disjunction = new char[]{'v'};
    static char[] equivalence = new char[]{'<', '-', '>'};
    static char[] implication = new char[]{'-', '>'};

    public void initialize(char[] sentence) {

        throw new UnsupportedOperationException();
    }

    public static boolean isAlpha(char target) {
        char[] alpha = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        boolean in = false;
        for (char t : alpha) {
            if (t == target) {
                in = true;
                break;
            }
        }
        return in;
    }

    public static void main(String[] args) {
        //String sentence = "^vTEST bool ' P )";
        String sentence = "bool P = 1, bool Q = 0, test P -> Q <-> P' v Q?";
        String sentence2 = "bool P = 0, bool Q = 0, bool R = P' ^ Q', test R?";
        //char[][] test = {{'<','-','>'},{'v'}};
        char[] test = sentence.toCharArray();
        char[] test2 = sentence2.toCharArray();
        char[][] split_sent = new char[20][20];
        char[] arr = new char[20];
        int tok_num = 0;

        //char[][] symbols = {open_paren,close_paren,end_bool,end_test,true_1,false_0,negation,conjunction,disjunction, {'>'},{'<'},{'-'}};

        char[] symbols = {'(', ')', ',', '?', '1', '0', '\'', '^', 'v', '='};
        String[] tokens = {"OPEN_PAREN","CLOSE_PAREN","END_BOOL","END_TEST","TRUE_LITERAL","FALSE_LITERAL","NEGATION","CONJUNCTION","DISJUNCTION","ASSIGNMENT"};

        for (int i = 0; i < test.length; i++) {

            for (int j = 0; j < symbols.length; j++) {
                if (symbols[j]==test[i]) {
                    System.out.println(tokens[j]);
                }
            }

            if (test[i] == 'b' && test[i + 1] == 'o' && test[i + 2] == 'o' && test[i + 3] == 'l') {
                System.out.println("BEGIN_BOOL");
                i+=3;

            }
            else if(test[i] == '<' && test[i + 1] == '-' && test[i + 2] == '>'){
                System.out.println("EQUIVALENCE");
                i+=2;

            }
            else if (test[i] == '-' && test[i + 1] == '>'){
                System.out.println("IMPLICATION");
                i+=1;

            }
            else if(test[i] == 't' && test[i + 1] == 'e' && test[i + 2] == 's' && test[i + 3] == 't'){
                System.out.println("BEGIN_TEST");
                i+=2;

            }
            else if(Character.isWhitespace(test[i])){
                continue;
            }
            else if(Character.isLetter(test[i])){
                System.out.println("VARIABLE");
            }


        }

/*
Replace above methods with case-insensitivity and unicode range checking.
 */

    }
}
