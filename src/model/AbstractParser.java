package model;

import model.AbstractLexer.Tokens;

// Do not modify.
public abstract class AbstractParser {
	/**
	 * This parser's {@link AbstractLexer#Lexer()} instance.
	 * <p>
	 * Do not redefine this field in the subclass.
	 */
	public AbstractLexer lexer;

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
	public abstract boolean evaluate(char[] sentence);

	/**
	 * If the lexer's next token matches the acceptable
	 * token, returns true and advances the lexer.
	 * Otherwise, returns false and does not advance.
	 *
	 * @param token The acceptable token
	 * @return Whether the next token is acceptable
	 */
	public abstract boolean accept(Tokens token);

	/**
	 * If the lexer's next token matches the acceptable
	 * token, returns true. Otherwise, returns false.
	 * In either case, does not advance the lexer.
	 * <p>
	 * This method is optional, so a stub is given.
	 *
	 * @param token The acceptable token
	 * @return Whether the next token is acceptable
	 */
	public boolean peek(Tokens token) {
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
	public abstract void expect(Tokens token);

	// OPTIONAL TRACING FRAMEWORK FOLLOWS

	/**
	 * The current indentation level used by the
	 * trace framework methods.
	 */
	private int trace_indent;

	/**
	 * Assumed to be called once and only once
	 * at the start of {@link AbstractParser#evaluate(char[])}
	 * to initialize the trace framework.
	 * <p>
	 * This method initializes the indentation.
	 */
	protected final void trace_start() {
		trace_indent = 0;
	}

	/**
	 * Assumed to be called once and only once
	 * at the start of each production method.
	 * <p>
	 * This method increases the indentation.
	 *
	 * @param production The name of the production
	 */
	protected final void trace_open(String production) {
		trace_flush(production + " {");
		trace_indent++;
	}

	/**
	 * Assumed to be called once and only once
	 * at the end of each production method.
	 * <p>
	 * This method decreases the indentation.
	 *
	 * @param production The name of the production
	 */
	protected final void trace_close(String production) {
		trace_close(production, null);
	}

	/**
	 * Assumed to be called once and only once
	 * at the end of each production method.
	 * <p>
	 * This method decreases the indentation.
	 *
	 * @param production The name of the production
	 * @param result The evaluation of the production
	 */
	protected final void trace_close(String production, Object result) {
		trace_flush("Return: " + (result != null ? String.valueOf(result) : "void"));
		trace_flush("End of " + production);
		trace_indent--;
		trace_flush("}");
	}

	/**
	 * Assumed to be called once and only once
	 * after each time the lexer is advanced.
	 * <p>
	 * This method does not affect the indentation
	 */
	protected final void trace_lexed() {
		trace_flush("Lexed: " + lexer.TOKEN + (lexer.LEXEME != null ? "[" + String.valueOf(lexer.LEXEME) + "]" : ""));
	}

	/**
	 * Assumed to be called by other trace framework
	 * methods to flush their output to the console.
	 * <p>
	 * Can be called with a custom line to output.
	 * <p>
	 * This method does not affect the indentation.
	 *
	 * @param production The line to output
	 */
	protected final void trace_flush(String line) {
		for (int i = 1; i <= trace_indent; i++)
			System.out.print("  ");
		System.out.println(line);
	}
}