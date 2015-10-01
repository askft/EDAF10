package predicate;

import java.util.HashSet;

import term.Term;
import term.Variable;

public class ForAll implements Expr {
	
	private Variable variable;
	private Expr expr;
	
	public ForAll(Variable var, Expr expr) {
		this.variable = var;
		this.expr = expr;
	}

	@Override
	public Expr substitute(Variable variable, Term term) {
		if (this.variable.equals(variable)) {
			return this;
        } else if (!term.collectVariables(new HashSet<Variable>()).contains(this.variable)) {
            return new ForAll(this.variable, expr.substitute(variable, term));
        } else {
            Variable v = new Variable();
            return new ForAll(v, expr.substitute(this.variable, v).substitute(variable, term));
        }
	}

	@Override
	public String toString() {
		return "∀" + variable + " . " + expr;
	}
}
