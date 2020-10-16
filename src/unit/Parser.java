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
		// TODO: implement this method stub
		throw new UnsupportedOperationException();
	}

	/**
	 * If the lexer's next token matches the acceptable
	 * token, returns true and advances the lexer.
	 * Otherwise, returns false and does not advance.
	 *
	 * @param token The acceptable token
	 * @return Whether the next token is acceptable
	 */
	@Override
	public boolean accept(Tokens token) {
		// TODO: implement this method stub
		throw new UnsupportedOperationException();
	}

	/**
	 * If the lexer's next token matches the acceptable
	 * token, advances the lexer. Otherwise, throws an
	 * <code>IllegalState</code> exception.
	 *
	 * @param token The acceptable token
	 * @return Whether the next token is acceptable
	 */
	@Override
	public void expect(Tokens token) {
		// TODO: implement this method stub
		throw new UnsupportedOperationException();
	}
}
