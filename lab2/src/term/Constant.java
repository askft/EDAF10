package term;

import java.util.Set;

public class Constant implements Term {
    private String name;

    public Constant(String value) {
        this.name = value;
    }

    public Set<Variable> collectVariables(Set<Variable> set) {
        return set;
    }

    public String toString() {
        return name;
    }

    /**
     * A constant contains no substitutable variables and thus a
     * "substitution" may only return the constant itself.
     */
	@Override
	public Term substitute(Variable x, Term term) {
		return this;
	}
}
