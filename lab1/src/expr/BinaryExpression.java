package expr;

import java.util.Map;
import java.util.Set;

public abstract class BinaryExpression extends Expression {
	
	protected Expression e1, e2;

	public BinaryExpression(Expression e1, Expression e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
	
	public abstract String operator();
	
	public abstract boolean operate(boolean lhs, boolean rhs);
	
	public String toString() {
		return "(" + e1.toString() + " " + operator() + " " + e2.toString() + ")";
	}
	
	public boolean value(Map<Variable, Boolean> map) {
		return operate(e1.value(map), e2.value(map));
	}

	protected void collectVariables(Set<Variable> set) {
		e1.collectVariables(set);
		e2.collectVariables(set);
	}
}
