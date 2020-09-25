package com.company;
import java.util.Arrays;

public class Main {

    static char[] question_mark = new char[] {'?'};
    static char[] open_paren = new char[] {'('};
    static char[] close_paren = new char[] {')'};
    static char[] begin_test = new char[] {'t','e','s','t'};
    static char[] bool_type = new char[] {'b','o','o','l'};
    static char[] true_1 = new char[] {'1'};
    static char[] false_0 = new char[] {'0'};
   static char[] negation = new char[] {'\''};
   static char[] conjunction = new char[] {'^'};
    static char[] disjunction = new char[] {'v'};
    static char[] equivalence = new char[] {'<','-','>'};
    static char[] implication = new char[] {'-','>'};

    public static void main(String[] args) {
        char[] test = {'b','o','o','l'};
        for(int i=0;i<test.length; i++){
            System.out.println(test[i]==bool_type[i]);
        }

    }
}
