package predicate;

import java.util.HashSet;

import term.Term;
import term.Variable;

public class ForAll implements Expression {
	
	private Variable variable;
	private Expression expr;
	
	public ForAll(Variable var, Expression expr) {
		this.variable = var;
		this.expr = expr;
	}

	@Override
	public String toString() {
		return "∀" + variable + " . " + expr;
	}

	/**
	 * (∀v . P)[x\t] =
	 * 					∀v . P					if v == x
	 * 					∀v . P[x\t]				if v != x and v ∉ t
	 * 					∀z . (P [v\z])[x\t])  	otherwise (name collision)
	 */
	@Override
	public Expression substitute(Variable variable, Term term) {
		if (this.variable.equals(variable)) {
			return this;
        } else if (!term.collectVariables(new HashSet<Variable>()).contains(this.variable)) {
            return new ForAll(this.variable, expr.substitute(variable, term));
        } else {
            Variable v = new Variable();
            return new ForAll(v, expr
				            		.substitute(this.variable, v)
				            		.substitute(variable, term));
        }
	}
}
