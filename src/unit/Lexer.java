package unit;

import model.AbstractLexer;
import java.lang.*;

import static model.AbstractLexer.Tokens.*;

public class Lexer extends model.AbstractLexer {
	public int index;
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
		LEXEME = new char[100];
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
				LEXEME[index] = S[index];
				index+=1;
			}
			// Tests for ) symbol
			else if(S[index] == 41){
				TOKEN = CLOSE_PAREN;
				LEXEME[index] = S[index];
				index+=1;
			}
			// Tests for , symbol
			else if(S[index] == 44){
				TOKEN = END_BOOL;
				LEXEME[index] = S[index];
				index+=1;
			}
			// Tests for ? symbol
			else if(S[index] == 63){
				TOKEN = END_TEST;
				LEXEME[index] = S[index];
				index+=1;
			}
			// Tests for 1 character
			else if(S[index] == 49){
				TOKEN = TRUE_LITERAL;
				LEXEME[index] = S[index];
				index+=1;
			}
			// Tests for 0 character
			else if(S[index] == 48){
				TOKEN = FALSE_LITERAL;
				LEXEME[index] = S[index];
				index++;
			}
			// Tests for ' symbol
			else if(S[index] == 39){
				TOKEN = NEGATION;
				LEXEME[index] = S[index];
				index++;
			}
			// Tests for ^ symbol
			else if(S[index] == 94){
				TOKEN = CONJUNCTION;
				LEXEME[index] = S[index];
				index++;
			}
			// Tests for v or V character
			else if((S[index] == 118)){
				TOKEN =  DISJUNCTION;
				LEXEME[index] = S[index];
				index++;
			}
			// Tests for = symbol
			else if((int) this.S[index] == 61){
				TOKEN = ASSIGNMENT;
				LEXEME[index] = S[index];
				index++;
			}
			// Specifically looks at whether the lexeme being evaluated is of the bool variety and ensures the index for the last char will be inbounds.
			else if(((index+4) < S.length) && (S[index] == 98 || S[index] == 66) && (S[index+1] == 111 || S[index+1] == 79) && (S[index+2] == 111 || S[index+2] == 79) && (S[index+3] == 108 || S[index+3] == 76)){
				LEXEME[index] = S[index];
				LEXEME[index+1] = S[index+1];
				LEXEME[index+2] = S[index+2];
				LEXEME[index+3] = S[index+3];
				TOKEN = BEGIN_BOOL;
				index+=4;
			}
			// Specifically looks at whether the lexeme being evaluated is of the test variety and ensures the index for the last char will be inbounds.
			else if(((index+4) < S.length) && (S[index] == 84 || S[index] == 116) && (S[index+1] == 69 || S[index+1] == 101) && (S[index+2] == 83 || S[index+2] == 115) && (S[index+3] == 84 || S[index+3] == 116)){
				TOKEN = BEGIN_TEST;
				LEXEME[index] = S[index];
				LEXEME[index+1] = S[index+1];
				LEXEME[index+2] = S[index+2];
				LEXEME[index+3] = S[index+3];
				index+=4;
			}
			// Looks for <-> and ensures the index for the last char will be inbounds.
			else if(((index+3) < S.length) && (S[index] == 60) && (S[index+1] == 45) && (S[index+2] == 62)){
				TOKEN = EQUIVALENCE;
				LEXEME[index] = S[index];
				LEXEME[index+1] = S[index+1];
				LEXEME[index+2] = S[index+2];
				index+=3;
			}
			// Looks for -> and ensures the index for the last char will be inbounds.
			else if(((index+3) < S.length) && (S[index] == 45) && (S[index+1] == 62)){
				TOKEN = IMPLICATION;
				LEXEME[index] = S[index];
				LEXEME[index+1] = S[index+1];
				index+=2;
			}
			// Looks for alphabetical characters that don't fit the above criteria and are variables
			else if(isAlpha(S[index])){
				TOKEN = VARIABLE_NAME;
				LEXEME[index] = S[index];
				index++;
			}
		}


		//throw new UnsupportedOperationException("Lex method failed");
	}

}
