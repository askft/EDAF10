package predicate;

import term.Term;
import term.Variable;

public class Implies implements Expr {
	
	private Expr expr1, expr2;

	public Implies(Expr expr1, Expr expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}

	@Override
	public Expr substitute(Variable variable, Term term) {
		Expr s1 = expr1.substitute(variable, term);
		Expr s2 = expr2.substitute(variable, term);

		return new Implies(s1, s2);
	}
	
	@Override
	public String toString() {
		return expr1 + " → " + expr2;
	}
}
