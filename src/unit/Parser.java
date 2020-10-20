package unit;

import model.AbstractLexer;
import model.AbstractLexer.Tokens;

public class Parser extends model.AbstractParser {

	private Lexer Lex;
	private Tokens[] toks;

	/**
	 * Instantiates the lexer instance and initializes the lexer's
	 * input by calling {@link AbstractLexer#initialize(char[])}.
	 * <p>
	 * Then evaluates the input as a sentence in the
	 * language, returning the <code>true</code> or
	 * <code>false</code> evaluation of the sentence.
	 * <p>
	 * This method must throw any exceptions thrown by
	 * production methods in the recursive descent.
	 *
	 * @param sentence A sentence to use as lexer input
	 * @return The evaluation of the sentence
	 *
	 * @throws Exception if the sentence is invalid
	 */
	@Override
	public boolean evaluate(char[] sentence) {
		Lex.initialize(sentence);
		Lex.lex();
		boolean evaluation = false;

		if(sentence != null){
			do{
				Lex.lex();
				System.out.println(Lex.TOKEN);
			}while(Lex.TOKEN != null);
		}
		else{
			throw new UnsupportedOperationException();
		}
		// TODO: implement this method stub
		return evaluation;
	}

	/**
	 * If the lexer's next token matches the acceptable
	 * token, returns true and advances the lexer.
	 * Otherwise, returns false and does not advance.
	 * Difference between expect: only does not move forward if error.
	 *
	 * @param token The acceptable token
	 * @return Whether the next token is acceptable
	 */
	@Override
	public boolean accept(Tokens token) {
		boolean acceptable = true;
		if (Lex.TOKEN==token){
			Lex.lex();
		}
		else{
			acceptable = false;
			throw new UnsupportedOperationException("Lexer next token unacceptable. Accept method failure.");
		}
		return acceptable;
	}

	/**
	 * If the lexer's next token matches the acceptable
	 * token, advances the lexer. Otherwise, throws an
	 * <code>IllegalState</code> exception.
	 * Difference between accept: does not move forward AND throws error.
	 *
	 * @param token The acceptable token
	 * @return Whether the next token is acceptable
	 */
	@Override
	public void expect(Tokens token) {
		if (Lex.TOKEN==token){
			Lex.lex();
		}
		else{
			throw new UnsupportedOperationException("Lexer next token unacceptable. Expect method failure.");
		}
		return;
	}
}
