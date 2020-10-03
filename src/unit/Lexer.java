package unit;

import model.AbstractLexer;
import java.lang.*;

import static model.AbstractLexer.Tokens.*;

public class Lexer extends model.AbstractLexer {



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
/*
Refactor to check for the different symbols
 */
	public static boolean isSymbol(char target) {

		int t = target;
		boolean in = false;

		if ((t > 64) && (t < 91) && !(t==86)) {
			in = true;
		} else if ((t > 96) && (t < 123)&& !(t==118)) {
			in = true;
		}
		return in;
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


		throw new UnsupportedOperationException("Lex method failed");
	}
}
