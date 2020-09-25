package model;

// Do not modify.
public abstract class AbstractLexer {
	/**
	 * The token types in the language (excluding the
	 * <code>null</code> token for end of input).
	 * <p>
	 * Do not call any methods on this enumeration type
	 * or on instances of this enumeration type.
	 */
	public static enum Tokens {
		BEGIN_BOOL,
		ASSIGNMENT,
		END_BOOL,
		BEGIN_TEST,
		END_TEST,
		EQUIVALENCE,
		IMPLICATION,
		DISJUNCTION,
		CONJUNCTION,
		NEGATION,
		OPEN_PAREN,
		CLOSE_PAREN,
		VARIABLE_NAME,
		TRUE_LITERAL,
		FALSE_LITERAL
	}

	/**
	 * Assumed to be called once and only once before the
	 * first call to the {@link AbstractLexer#lex()} method in
	 * order to initialize the lexer's input source.
	 *
	 * @param sentence A sentence to use as lexer input
	 */
	public abstract void initialize(char[] sentence);

	/**
	 * Advances to the next lexeme in the input and updates
	 * the values of the {@link AbstractLexer#LEXEME} and
	 * {@link AbstractLexer#TOKEN} fields with the next
	 * lexeme and its corresponding token, respectively.
	 *
	 * @throws Exception on an unexpected symbol
	 */
	public abstract void lex();

	/**
	 * The next lexeme in the input (or <code>null</code>
	 * if the lexeme is the only instance of its token),
	 * updated on each call to {@link AbstractLexer#lex()}.
	 * <p>
	 * Do not redefine this field in the subclass.
	 */
	public char[] LEXEME;

	/**
	 * The token type corresponding to {@link AbstractLexer#LEXEME}
	 * (or <code>null</code> if the lexeme is end of input)
	 * updated on each call to {@link AbstractLexer#lex()}.
	 * <p>
	 * Do not redefine this field in the subclass.
	 */
	public Tokens TOKEN;
}