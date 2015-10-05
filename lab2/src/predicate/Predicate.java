package predicate;

import term.Term;
import term.TermList;
import term.Variable;

public class Predicate implements Expression {
	
	String str;
	TermList terms;
	
	public Predicate(String str, TermList terms) {
		this.str = str;
		this.terms = terms;
	}

	@Override
	public String toString() {
		return str + terms;
	}

	@Override
	public Expression substitute(Variable variable, Term term) {
		return new Predicate(str, terms.substitute(variable, term));
	}
}
