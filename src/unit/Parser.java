/*
* @author Michael Austin
* Academic Integrity Statement: I have read the academic integrity policy and the code I am submitting is my own.
* I have not collaborated, or shared it with anyone else.
*/

package unit;
import model.AbstractLexer;
import model.AbstractLexer.Tokens;

public class Parser extends model.AbstractParser {
	boolean[] lookup_bool = new boolean[26];
	char[] lookup_char = new char[]{'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};


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
		lexer = new Lexer();
		boolean evaluation;
		lexer.initialize(sentence);
		lexer.lex();
		evaluation = program();
		expect(null);
		if(evaluation != true & evaluation != false){
			throw new IllegalStateException("Invalid sentence");
		}
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
		boolean result;
		while(peek(Tokens.BEGIN_BOOL)) {
			assignment();
		}

		result = evaluation();

		return result;
	}

	private void assignment() {
		expect(Tokens.BEGIN_BOOL);
		expect(Tokens.VARIABLE_NAME);
		char var = variable();
		expect(Tokens.ASSIGNMENT);
		boolean value = equivalence();
		for(int i =0; i<lookup_char.length;i++){
			if(lookup_char[i]==var){
				lookup_bool[i] = value;
			}
		}
		expect(Tokens.END_BOOL);
	}

	private boolean evaluation() {
		boolean result = true;
		while(accept(Tokens.BEGIN_TEST)){
			result = equivalence();
			expect(Tokens.END_TEST);
		}

		return result;
	}

	private boolean equivalence() {

		boolean result = implication();
		while(accept(Tokens.EQUIVALENCE)){
			result= (result == implication());
		}
		return result;
	}

	// If this, then that. ->
	/* Truth Table
	 * P | Q | P -> Q
	 * ------------
	 * T | T | T
	 * T | F | F
	 * F | T | T
	 * F | F | T
	 */
	private boolean implication() {
		boolean result = disjunction();
		while(accept(Tokens.IMPLICATION)) {
			if(result & !disjunction()){
				result = false;
			}else if(!result & !disjunction())
				result = true;
			else if(result & disjunction()){
				result = true;
			}else{
				result = true;
			}

		}
		return result;
	}
	// When v shows up; logical OR
	/* Truth Table
	* P | Q | P V Q
	* ------------
	* T | T | T
	* T | F | T
	* F | T | T
	* F | F | F
	*/
	private boolean disjunction() {
		boolean result = conjunction();
		while(accept(Tokens.DISJUNCTION)) {

			result = result | conjunction();
		}
		return result;
	}
	// When ^ shows up; logical AND
	/* Truth Table
	 * P | Q | P ^ Q
	 * ------------
	 * T | T | T
	 * T | F | F
	 * F | T | F
	 * F | F | F
	 */
	private boolean conjunction() {
		boolean result = negation();
		while(accept(Tokens.CONJUNCTION)){
			result = result & negation();
		}
		return result;
	}
	// ' symbol. Opposite
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
		if(accept(Tokens.OPEN_PAREN)){
			//accept(Tokens.OPEN_PAREN);
			result = equivalence();
			expect(Tokens.CLOSE_PAREN);
			return result;
		}else result = bool();

		return result;
	}
	private boolean bool() {
		boolean result = true;
		boolean found = false;
		if(accept(Tokens.TRUE_LITERAL)){
			result = true;
		}
		else if(accept(Tokens.FALSE_LITERAL)){
			result = false;
		}
		if(peek(Tokens.VARIABLE_NAME)){
			char target = variable();
			expect(Tokens.VARIABLE_NAME);
			int i = 0;
			while(i < lookup_char.length ){
				if(lookup_char[i] == target){
					found=true;
					result= lookup_bool[i];
				} else if(!found & (i==lookup_char.length-1)  ){
					System.out.println(target);
					throw new IllegalThreadStateException("Var not found. Expected: "+target+" Target Class: " );
				}
				i++;
			}

		}

		return result;
	}

	private char variable() {
		return lexer.LEXEME[0];
	}

	public boolean peek(Tokens token) {
		return lexer.TOKEN == token;
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

		if (lexer.TOKEN==token){

			acceptable = true;
			lexer.lex();
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
		if (lexer.TOKEN==token){
			lexer.lex();
		}
		else{
			throw new UnsupportedOperationException("Expect method failure. Expected: "+token+"; Received: "+lexer.TOKEN);
		}
	}
}
