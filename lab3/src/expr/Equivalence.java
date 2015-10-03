package expr;

public class Equivalence extends BinaryExpression {
	
	public Equivalence(Expression e1, Expression e2) {
		super(e1, e2);
	}
	
	public String operator() {
		return "↔";
	}
	
	public boolean operate(boolean lhs, boolean rhs) {
		return lhs == rhs;
	}
}
