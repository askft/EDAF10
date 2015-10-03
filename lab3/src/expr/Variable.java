package expr;

import java.util.Map;
import java.util.Set;

public class Variable extends Expression {
	private String name;

	public Variable(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public boolean equals(Object object) {
		if (object instanceof Variable) {
			return this.name.equals(((Variable) object).name);
		}
		return false;
	}
	
	@Override
	public boolean value(Map<Variable, Boolean> map) {
		return map.get(this);
	}

	@Override
	protected void collectVariables(Set<Variable> set) {
		set.add(this);
	}
}
