package parser;

import junit.framework.TestCase;
import expr.*;

public class ParserTest extends TestCase {
    private Parser parser = new Parser();
    private Variable p = new Variable("p");
    private Variable q = new Variable("q");
    private Conjunction andpq = new Conjunction(p, q);
    private Disjunction orpq = new Disjunction(p, q);
    private Implication impliespq = new Implication(p, q);

    protected void assertEqualsByToString(Object expected, Object actual) {
        assertEquals(expected.toString(), actual.toString());
    }

    public void test_p() { // p
        assertEqualsByToString(p, parser.build("p"));
    }

    public void testParenteses_p() { // !p
        assertEqualsByToString(p, parser.build("(p)"));
    }

    public void testNot_p() { // !p
        assertEqualsByToString(new Negation(p), parser.build("!p"));
    }

    public void testAnd_p_q() { // p&q
        assertEqualsByToString(andpq, parser.build("p&q"));
    }

    public void testAnd_p_q_p() { // p&q&p
        assertEqualsByToString(new Conjunction(andpq, p), parser.build("p&q&p"));
    }

    public void testAnd_p_q_p1() { // p&(p&q)
        assertEqualsByToString(new Conjunction(p, andpq), parser.build("p&(p&q)"));
    }

    public void testOr_p_q() { // p|q
        assertEqualsByToString(orpq, parser.build("p|q"));
    }

    public void testOr_p_q_p() { // p|q|p
        assertEqualsByToString(new Disjunction(orpq, p), parser.build("p|q|p"));
    }

    public void testOr_p_q_p1() { // p|(p|q)
        assertEqualsByToString(new Disjunction(p, orpq), parser.build("p|(p|q)"));
    }

    public void testAndOr_p_q_p() { // p&q|p
        assertEqualsByToString(new Disjunction(andpq, p), parser.build("p&q|p"));
    }

    public void testOrAnd_p_q_p() { // p&q|p
        assertEqualsByToString(new Disjunction(p, andpq), parser.build("p|p&q"));
    }

    public void testImplies_p_q() { // p->q
        assertEqualsByToString(impliespq, parser.build("p->q"));
    }

    private void shouldFail(String string, String message) {
        try {
            parser.build(string);
            fail("Should throw ParserExption: ");
        } catch (ParserException e) {
            if (e.getMessage().equals(message)) {
                // Successful test
            } else {
                fail("Should throw ParserExption: " + message);
            }
        }
    }

    public void testImplies_p_q_p() { // p->q->p
        shouldFail("p->q->p", "Trailing garbage after ->");
    }

    public void testMissingParenthesis() { // (p->q
        shouldFail("(p->q", "Expecting \")\", found: EOF");
    }

    public void testUnexpectedToken() { // #p
        shouldFail("#p", "Unexpected #");
    }
}
