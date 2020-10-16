package unit;

import model.AbstractLexer;
import java.lang.*;

import static model.AbstractLexer.Tokens.*;

public class Lexer extends model.AbstractLexer {
	private int index;
	private char[] S;

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


	/**
	 * Assumed to be called once and only once before the
	 * first call to the {@link AbstractLexer#lex()} method in
	 * order to initialize the lexer input source.
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
		if(index >= S.length || S.length == 0)
			TOKEN = null;
		else{
			if(S[index]==32){
				index+=1;
			}
			// Tests for ( symbol
			if (S[index] == 40) {
				TOKEN = OPEN_PAREN;
				index+=1;
			}
			// Tests for ) symbol
			else if(S[index] == 41){
				TOKEN = CLOSE_PAREN;
				index+=1;
			}
			// Tests for , symbol
			else if(S[index] == 44){
				TOKEN = END_BOOL;
				index+=1;
			}
			// Tests for ? symbol
			else if(S[index] == 63){
				TOKEN = END_TEST;
				index+=1;
			}
			// Tests for 1 character
			else if(S[index] == 49){
				TOKEN = TRUE_LITERAL;
				index+=1;
			}
			// Tests for 0 character
			else if(S[index] == 48){
				TOKEN = FALSE_LITERAL;
				index++;
			}
			// Tests for ' symbol
			else if(S[index] == 39){
				TOKEN = NEGATION;
				index++;
			}
			// Tests for ^ symbol
			else if(S[index] == 94){
				TOKEN = CONJUNCTION;
				index++;
			}
			// Tests for v or V character
			else if((S[index] == 118) || (S[index] == 86)){
				TOKEN =  DISJUNCTION;
				index++;
			}
			// Tests for = symbol
			else if((int) this.S[index] == 61){
				TOKEN = ASSIGNMENT;
				index++;
			}
			// Specifically looks at whether the lexeme being evaluated is of the bool variety and ensures the index for the last char will be inbounds.
			else if(((index+4) < S.length) && (S[index] == 98 || S[index] == 66) && (S[index+1] == 111 || S[index+1] == 79) && (S[index+2] == 111 || S[index+2] == 79) && (S[index+3] == 108 || S[index+3] == 76)){
				TOKEN = BEGIN_BOOL;
				index+=4;
			}
			// Specifically looks at whether the lexeme being evaluated is of the test variety and ensures the index for the last char will be inbounds.
			else if(((index+4) < S.length) && (S[index] == 84 || S[index] == 116) && (S[index+1] == 69 || S[index+1] == 101) && (S[index+2] == 83 || S[index+2] == 115) && (S[index+3] == 84 || S[index+3] == 116)){
				TOKEN = BEGIN_TEST;
				index+=4;
			}
			// Looks for <-> and ensures the index for the last char will be inbounds.
			else if(((index+3) < S.length) && (S[index] == 60) && (S[index+1] == 45) && (S[index+2] == 62)){
				TOKEN = EQUIVALENCE;
				index+=3;
			}
			// Looks for -> and ensures the index for the last char will be inbounds.
			else if(((index+3) < S.length) && (S[index] == 45) && (S[index+1] == 62)){
				TOKEN = IMPLICATION;
				index+=2;
			}
			// Looks for alphabetical characters that don't fit the above criteria and are variables
			else if(isAlpha(S[index])){
				TOKEN = VARIABLE_NAME;
				index++;
			}
		}


		//throw new UnsupportedOperationException("Lex method failed");
	}

}
