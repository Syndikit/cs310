package com.company;

public class Main {

    public void initialize(char[] sentence) {

        throw new UnsupportedOperationException();
    }

    public static boolean isAlpha(char target) {

        int t = target;
        boolean in = false;

        if ((t > 64) && (t < 91) && !(t==86)) {
            in = true;
        } else if ((t > 96) && (t < 123)&& !(t==118)) {
            in = true;
        }
        return in;
    }

    public static boolean isBool(int a, int b, int c, int d) {
        boolean res = false;
        if ((a == 98 || a == 66) && (b == 111 || b == 79) && (c == 111 || c == 79) && (d == 108 || d == 76)) {
            res = true;
        }

        return res;
    }

    public static boolean isTest(int a, int b, int c, int d) {
        boolean res = false;
        if ((a == 84 || a == 116) && (b == 69 || b == 101) && (c == 83 || c == 115) && (d == 84 || d == 116)) {
            res = true;
        }

        return res;
    }

    public static void main(String[] args) {
        //String sentence = "^vTEST bool ' P )";
        String sentence = "bool P = 1, bool Q = 0, test P -> Q <-> P' v Q?";
        String sentence2 = "bool P = 0, bool Q = 0, bool R = P' ^ Q', test R?";

        char[] test = sentence.toCharArray();
        char[] test2 = sentence2.toCharArray();

        //symbols = {'(', ')', ',', '?', '1', '0', '\'', '^', 'v', '='};
        int[] int_symbols = {40, 41, 44, 63, 49, 48, 39, 94, 118, 61};
        String[] tokens = {"OPEN_PAREN", "CLOSE_PAREN", "END_BOOL", "END_TEST", "TRUE_LITERAL", "FALSE_LITERAL", "NEGATION", "CONJUNCTION", "DISJUNCTION", "ASSIGNMENT"};


        for (int i = 0; i < test.length; i++) {

            for (int j = 0; j < int_symbols.length; j++) {
                if (int_symbols[j] == test[i]) {
                    System.out.println(tokens[j]);

                }
            }


            if (((i + 3) < test.length) && (isBool(test[i], test[i + 1], test[i + 2], test[i + 3]))) {
                System.out.println(String.format("%c%c%c%c = BEGIN_BOOL", test[i], test[i + 1], test[i + 2], test[i + 3]));
                i += 3;

            } else if (((i + 3) < test.length) && isTest((int) test[i], (int) test[i + 1], (int) test[i + 2], (int) test[i + 3])) {
                System.out.println(String.format("%c%c%c%c = BEGIN_TEST", test[i], test[i + 1], test[i + 2], test[i + 3]));
                i += 3;

            } else if (((i + 2) < test.length) && (test[i] == 60) && (test[i + 1] == 45) && (test[i + 2] == 62)) {
                System.out.println(String.format("%c%c%c = EQUIVALENCE", test[i], test[i + 1], test[i+2]));
                i += 2;

            } else if (((i + 1) < test.length) && (test[i] == 45) && (test[i + 1] == 62)) {
                System.out.println(String.format("%c%c = IMPLICATION", test[i], test[i+1]));
                i += 1;

            }

            // Tests for whitespace (32 in unicode)
            else if (test[i] == 32) {
                System.out.print("");
            }
            // 65 - 90 are capital letters; 97 - 122 are lowercase letters
            else if (isAlpha(test[i])) {
                System.out.println(String.format("%c = VARIABLE", test[i]));
            }


        }



/*
Replace above methods with case-insensitivity and unicode range checking.
List of unicode/dec/chars: https://en.wikipedia.org/wiki/List_of_Unicode_characters
 */

    }
}
