package unit;

public class Lexer extends model.AbstractLexer {

	static char[] question_mark = new char[] {'?'};
	static char[] open_paren = new char[] {'('};
	static char[] close_paren = new char[] {')'};
	static char[] begin_test = new char[] {'t','e','s','t'};
	static char[] bool_type = new char[] {'b','o','o','l'};
	static char[] true_1 = new char[] {'1'};
	static char[] false_0 = new char[] {'0'};
	static char[] negation = new char[] {'\''};
	static char[] conjunction = new char[] {'^'};
	static char[] disjunction = new char[] {'v'};
	static char[] equivalence = new char[] {'<','-','>'};
	static char[] implication = new char[] {'-','>'};

	@Override
	public void initialize(char[] sentence) {
		// TODO: implement this method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public void lex() {
		// TODO: implement this method stub
		throw new UnsupportedOperationException();
	}
}
