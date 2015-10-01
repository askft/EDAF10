package term;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

@SuppressWarnings("serial")
public class TermList extends ArrayList<Term> {

    public TermList(Term... terms) {
        super(Arrays.asList(terms));
    }

    public Set<Variable> collectVariables(Set<Variable> set) {
        for (Term term : this) {
            term.collectVariables(set);
        }
        return set;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('(');
        for (Term term : this) {
            builder.append(term).append(", ");
        }
        int length = builder.length();
        if (length > 2) {
            builder.setLength(length - 2);
        }
        builder.append(')');
        return builder.toString();
    }

    public TermList substitute(Variable variable, Term term) {
    	TermList ts = new TermList();
    	for (Term t : this) {
    		ts.add(t.substitute(variable, term));
    	}
    	return ts;
    }
}
