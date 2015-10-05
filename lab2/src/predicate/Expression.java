package predicate;

import term.Term;
import term.Variable;

public interface Expression {
    public String toString();
    public Expression substitute(Variable variable, Term term);
}
