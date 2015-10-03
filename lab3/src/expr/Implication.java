package expr;

public class Implication extends BinaryExpression {

	public Implication(Expression e1, Expression e2) {
		super(e1, e2);
	}

	public String operator() {
		return "→";
	}
	
	public boolean operate(boolean lhs, boolean rhs) {
		return !lhs || rhs;
	}
}
