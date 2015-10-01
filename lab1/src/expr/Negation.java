package expr;

import java.util.Map;
import java.util.Set;

public class Negation extends Expression {

	private Expression e1;

	public Negation(Expression e1) {
		this.e1 = e1;
	}

	public String toString() {
		return "(" + "¬" + e1.toString() + ")";
	}

	@Override
	public boolean value(Map<Variable, Boolean> map) {
		return !e1.value(map);
	}

	@Override
	protected void collectVariables(Set<Variable> set) {
		e1.collectVariables(set);
	}
}
