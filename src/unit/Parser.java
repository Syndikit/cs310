/*
* @author Michael Austin
*  Academic Integrity Statement: I have read the academic integrity policy and the code I am submitting is my own.
* I have not collaborated, or shared it with anyone else.
*/

package unit;

import model.AbstractLexer;
import unit.Lexer;
import model.AbstractLexer.Tokens;

public class Parser extends model.AbstractParser {
	boolean[] lookup_bool = new boolean[10];
	char[] lookup_char = new char[10];
	int lookup_index;
	int lexeme_index;
	Lexer Lex = new Lexer();


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
		boolean evaluation;
		Lex.initialize(sentence);
		//this.trace_start();
		Lex.lex();
		evaluation = program();
		expect(null);
		return evaluation;
	}

	/*
	* <program> → { <assignment> }* <evaluation>
	*
	* Initializes a global data structure called the lookup table whose data associates variable names with boolean values
	*
	* Returns the result of <evaluation> as a boolean, or throws an exception if the <program> is invalid for any reason
	*
	*/
	private boolean program() {
		//trace_open("Program");
		lookup_index = 0;
		lexeme_index = 0;
		boolean result = false;
		while(peek(Tokens.BEGIN_BOOL) || peek(Tokens.BEGIN_TEST)){
			if(accept(Tokens.BEGIN_BOOL))
				assignment();
			else if(accept(Tokens.BEGIN_TEST))
				result = evaluation();
		}

		return result;
	}

	private void assignment() {
		expect(Tokens.BEGIN_BOOL);
		expect(Tokens.VARIABLE_NAME);
		char var = variable();
		expect(Tokens.ASSIGNMENT);

		lookup_char[lookup_index] = var;
		lookup_bool[lookup_index] = equivalence();


	}

	private boolean evaluation() {
		boolean result = true;
		while(!accept(Tokens.END_TEST)){
			result = equivalence();
		}

		return result;
	}

	private boolean equivalence() {

		boolean result = implication();
		while(accept(Tokens.EQUIVALENCE)){
			return result == implication();
		}
		return result;
	}

	private boolean implication() {
		boolean result = disjunction();
		while(accept(Tokens.IMPLICATION)) {
			if(result && !disjunction()){
				result = false;
			}else if(!result && !disjunction())
				result = true;
			else {
				result = true;
			}

		}
		return result;
	}
	private boolean disjunction() {
		boolean result = conjunction();
		if(accept(Tokens.DISJUNCTION)) {
			result = (result || conjunction());
		}
		return result;
	}
	private boolean conjunction() {
		boolean result = negation();
		if(accept(Tokens.CONJUNCTION)){
			result = (result && negation());
		}
		return result;
	}
	private boolean negation() {

		boolean expression = expression();
		if(accept(Tokens.NEGATION)){
			expression = !expression;
		}
		return expression;
	}
	// Need to rework this one
	private boolean expression() {
		boolean result;
		// needs to peek instead
		if(peek(Tokens.OPEN_PAREN)){
			expect(Tokens.OPEN_PAREN);
			result = equivalence();
			expect(Tokens.CLOSE_PAREN);
			return result;
		}
		result = bool();
		return result;
	}
	private boolean bool() {
		boolean result = false;
		if(accept(Tokens.TRUE_LITERAL)){
			result = true;
		} else if(accept(Tokens.FALSE_LITERAL)){
			result = false;
		} else if (accept(Tokens.VARIABLE_NAME)){
			char target = variable();
			for(int i = 0; i < lookup_char.length; i++){
				if(lookup_char[i] == target){
					if(lookup_bool[i])
						result = true;
					else if(!lookup_bool[i])
						result = false;
				}
			}

		}

		return result;
	}

	private char variable() {
		return Lex.LEXEME[lexeme_index];
	}

	public boolean peek(Tokens token) {
		return Lex.TOKEN == token;
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
		boolean acceptable;

		if (Lex.TOKEN==token){

			acceptable = true;
			Lex.lex();
		}
		else{
			acceptable = false;
		}
		return acceptable;
	}

	// Looks good
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
