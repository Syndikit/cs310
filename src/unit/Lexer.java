package unit;

import model.AbstractLexer;
import java.lang.*;

import static model.AbstractLexer.Tokens.*;

public class Lexer extends model.AbstractLexer {
	public char[] sent = new char[80];

	//symbols = {'(', ')', ',', '?', '1', '0', '\'', '^', 'v', '='};
	int[] int_symbols = {40, 41, 44, 63, 49, 48, 39, 94, 118, 61};
	Tokens[] tokens = {OPEN_PAREN, CLOSE_PAREN, END_BOOL, END_TEST, TRUE_LITERAL, FALSE_LITERAL, NEGATION, CONJUNCTION, DISJUNCTION, ASSIGNMENT};

	/*
	Checks whether character is within the acceptable range of decimal values for letters (upper and lowercase)
	 */
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

	/**
	 * Assumed to be called once and only once before the
	 * first call to the {@link AbstractLexer#lex()} method in
	 * order to initialize the lexer's input source.
	 *
	 * @param sentence A sentence to use as lexer input
	 */
	@Override
	public void initialize(char[] sentence) {

		for(int i=0; i<sentence.length;i++){
			sent[i] = sentence[i];
		}

		throw new UnsupportedOperationException();
	}

	/**
	 * Advances to the next lexeme in the input and updates
	 * the values of the {@link AbstractLexer#LEXEME} and
	 * {@link AbstractLexer#TOKEN} fields with the next
	 * lexeme and its corresponding token, respectively.
	 *
	 * @throws Exception on an unexpected symbol
	 */
	@Override
	public void lex() {

		for (int i = 0; i < sent.length; i++) {

			for (int j = 0; j < int_symbols.length; j++) {
				if (int_symbols[j] == sent[i]) {
					//System.out.println(tokens[j]);
					TOKEN = tokens[j];
				}
			}


			if (((i + 3) < sent.length) && (isBool(sent[i], sent[i + 1], sent[i + 2], sent[i + 3]))) {
				//System.out.println(String.format("%c%c%c%c = BEGIN_BOOL", sent[i], sent[i + 1], sent[i + 2], sent[i + 3]));
				TOKEN = BEGIN_BOOL;
				i += 3;

			} else if (((i + 3) < sent.length) && isTest((int) sent[i], (int) sent[i + 1], (int) sent[i + 2], (int) sent[i + 3])) {
				//System.out.println(String.format("%c%c%c%c = BEGIN_TEST", sent[i], sent[i + 1], sent[i + 2], sent[i + 3]));
				TOKEN = BEGIN_TEST;
				i += 3;

			} else if (((i + 2) < sent.length) && (sent[i] == 60) && (sent[i + 1] == 45) && (sent[i + 2] == 62)) {
				//System.out.println(String.format("%c%c%c = EQUIVALENCE", sent[i], sent[i + 1], sent[i+2]));
				TOKEN = EQUIVALENCE;
				i += 2;

			} else if (((i + 1) < sent.length) && (sent[i] == 45) && (sent[i + 1] == 62)) {
				//System.out.println(String.format("%c%c = IMPLICATION", sent[i], sent[i+1]));
				TOKEN = IMPLICATION;
				i += 1;

			}

			// Tests for whitespace (32 in unicode)
			else if (sent[i] == 32) {
				//System.out.print("");
				continue;
			}
			// 65 - 90 are capital letters; 97 - 122 are lowercase letters
			else if (isAlpha(sent[i])) {
				System.out.println(String.format("%c = VARIABLE", sent[i]));
				TOKEN = VARIABLE_NAME;
			}


		}

		// TODO: implement this method stub
		throw new UnsupportedOperationException();
	}
}
