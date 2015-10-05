package predicate;

import term.Term;
import term.Variable;

public class Implication implements Expression {
	
	private Expression expr1, expr2;

	public Implication(Expression expr1, Expression expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}

	@Override
	public String toString() {
		return expr1 + " → " + expr2;
	}

	@Override
	public Expression substitute(Variable variable, Term term) {
		Expression s1 = expr1.substitute(variable, term);
		Expression s2 = expr2.substitute(variable, term);

		return new Implication(s1, s2);
	}
}
