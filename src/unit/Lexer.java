package unit;

import model.AbstractLexer;
import java.lang.*;

import static model.AbstractLexer.Tokens.*;

public class Lexer extends model.AbstractLexer {

	private int index = 0;

	private char[] S;

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


	/**
	 * Assumed to be called once and only once before the
	 * first call to the {@link AbstractLexer#lex()} method in
	 * order to initialize the lexer's input source.
	 *
	 * @param sentence A sentence to use as lexer input
	 */
	@Override
	public void initialize(char[] sentence) {
		S = sentence;

		index = 0;

		//throw new UnsupportedOperationException();
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
		int ind = index;

		Tokens TOKEN;
		if ((int) this.S[ind] == 40) {
			this.TOKEN = OPEN_PAREN;
			index++;
		}
		else if((int) this.S[ind] == 41){
			this.TOKEN = CLOSE_PAREN;
			index++;
		}
		else if((int) this.S[ind] == 44){
			this.TOKEN = END_BOOL;
			index+=4;
		}
		else if((int) this.S[ind] == 63){
			this.TOKEN = END_TEST;
			index+=4;
		}
		else if((int) this.S[ind] == 49){
			this.TOKEN = TRUE_LITERAL;
			index++;
		}
		else if((int) this.S[ind] == 48){
			this.TOKEN = FALSE_LITERAL;
			index++;
		}
		else if((int) this.S[ind] == 39){
			this.TOKEN = NEGATION;
			index++;
		}
		else if((int) this.S[ind] == 94){
			this.TOKEN = CONJUNCTION;
			index++;
		}
		else if(((int) this.S[ind] == 118) || ((int) this.S[ind] == 86)){
			this.TOKEN =  DISJUNCTION;
			index++;
		}
		else if((int) this.S[ind] == 61){
			this.TOKEN = ASSIGNMENT;
			index++;
		}
		else if(isBool(this.S[ind],this.S[ind+1],this.S[ind+2], this.S[ind+3])){
			this.TOKEN = BEGIN_BOOL;
			index++;
		}
		else if(isTest(this.S[ind],this.S[ind+1],this.S[ind+2],this.S[ind+3])){
			this.TOKEN = BEGIN_TEST;
			index++;
		}
		else if(isEquivalence(this.S[ind],this.S[ind+1],this.S[ind+2])){
			this.TOKEN = EQUIVALENCE;
			index++;
		}
		else if(isImplication(this.S[ind],this.S[ind+1])){
			this.TOKEN = IMPLICATION;
			index++;
		}
		else if(isAlpha(this.S[ind])){
			this.TOKEN = VARIABLE_NAME;
			index++;
		}

		//throw new UnsupportedOperationException("Lex method failed");
	}

}
