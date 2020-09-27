package unit;

import model.AbstractLexer;
import java.lang.*;

public class Lexer extends model.AbstractLexer {
	public char[] sent = new char[80];

	static char[] open_paren = new char[] {'('};
	static char[] close_paren = new char[] {')'};
	static char[] end_bool = {','};
	static char[] begin_test = new char[] {'t','e','s','t'};
	static char[] end_test = new char[] {'?'};
	static char[] bool_type = new char[] {'b','o','o','l'};
	static char[] true_1 = new char[] {'1'};
	static char[] false_0 = new char[] {'0'};
	static char[] negation = new char[] {'\''};
	static char[] conjunction = new char[] {'^'};
	static char[] disjunction = new char[] {'v'};
	static char[] equivalence = new char[] {'<','-','>'};
	static char[] implication = new char[] {'-','>'};

	char[][] symbols = {open_paren,close_paren,end_bool,end_test};

	/*
	Looks through a character array and returns true if the specified character is in said char[].
	 */
	public static boolean contains(char[] list, char target){
		boolean in = false;
		for (char t : list){
			if (t == target){
				in = true;
				break;
			}
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

		for(int i=0; i<sentence.length;i++){
			sent[i] = Character.toLowerCase(sentence[i]);
		}

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

		// TODO: implement this method stub
		throw new UnsupportedOperationException();
	}
}
