package expr;

public class Disjunction extends BinaryExpression {

	public Disjunction(Expression e1, Expression e2) {
		super(e1, e2);
	}

	public String operator() {
		return "⋁";
	}
	
	public boolean operate(boolean lhs, boolean rhs) {
		return lhs || rhs;
	}
}
