package com.company;
import java.util.Arrays;

public class Main {

    static char[] open_paren = new char[] {'('};
    static char[] close_paren = new char[] {')'};
    static char[] end_bool = {','};
    static char[] begin_test = new char[] {'t','e','s','t'};
    static char[] end_test = new char[] {'?'};
    static char[] bool_type = new char[] {'b','o','o','l'};
    static char[] true_1 = new char[] {'1'};
    static char[] false_0 = new char[] {'0'};
    static char[] negation = new char[] {'\''};
    static char[] conjunction = new char[] {'^'};
    static char[] disjunction = new char[] {'v'};
    static char[] equivalence = new char[] {'<','-','>'};
    static char[] implication = new char[] {'-','>'};

    public void initialize(char[] sentence) {

        throw new UnsupportedOperationException();
    }

    public static boolean contains(char[] list, char target){
        boolean in = false;
        for (char t : list){
            if (t == target){
                in = true;
                break;
            }
        }
        return in;
    }

    public static void main(String[] args) {
        String sentence = "^vTEST bool ' P )";
        String sentence2 = "bool P = 0, bool Q = 0, bool R = P' ^ Q', test R?";
        //char[][] test = {{'<','-','>'},{'v'}};
        char[] test = sentence.toCharArray();
        char[] test2 = sentence2.toCharArray();
        char[][] split_sent = new char[20][20];
        char[] arr = new char[20];
        int tok_num = 0;

        char[][] symbols = {open_paren,close_paren,end_bool,end_test,true_1,false_0,negation,conjunction,disjunction, {'>'}};
        for (int i =0;i<test.length;i++){
            for(int j =0; j<symbols.length;j++){
                if(contains(symbols[j], test[i])){
                    System.out.println(symbols[j]);
                }
            }

        }

    }
}
