package unit;

import model.AbstractLexer;
import java.lang.*;

import static model.AbstractLexer.Tokens.*;

public class Lexer extends model.AbstractLexer {

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


	/**
	 * Assumed to be called once and only once before the
	 * first call to the {@link AbstractLexer#lex()} method in
	 * order to initialize the lexer's input source.
	 *
	 * @param sentence A sentence to use as lexer input
	 */
	@Override
	public void initialize(char[] sentence) {

		for(int i=0;i<sentence.length;i++){
			int ind = getIndex();
			if(isBool(sentence[0],sentence[1],sentence[2],sentence[3])){
				LEXEME = new char[]{sentence[0], sentence[1], sentence[2], sentence[3]};
				this.LEXEMES[ind] = LEXEME;
				setIndex(index++);
				i += 3;
			}
			else if(isTest(sentence[0],sentence[1],sentence[2],sentence[3])){
				LEXEME = new char[]{sentence[0], sentence[1], sentence[2], sentence[3]};
				this.LEXEMES[ind] = LEXEME;
				setIndex(index++);
				i += 3;
			}
			else if(isAlpha(sentence[0])){
				LEXEME = new char[]{sentence[0]};
				this.LEXEMES[ind] = LEXEME;
				setIndex(index++);
			}
			else if(isSymbol(sentence[0])){
				LEXEME = new char[]{sentence[0]};
				this.LEXEMES[ind] = LEXEME;
				setIndex(index++);
			}
			else if(isEquivalence(sentence[0],sentence[1],sentence[2])){
				LEXEME = new char[]{sentence[0],sentence[1],sentence[2]};
				this.LEXEMES[ind] = LEXEME;
				setIndex(index++);
				i += 2;
			}
			else if(isImplication(sentence[0],sentence[1])){
				LEXEME = new char[]{sentence[0],sentence[1]};
				this.LEXEMES[ind] = LEXEME;
				setIndex(index++);
				i += 1;
			}
		}

		setIndex(0);

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

		throw new UnsupportedOperationException("Lex method failed");
	}

}
