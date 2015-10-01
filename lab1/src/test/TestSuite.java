package test;

import static org.junit.Assert.*;
import org.junit.*;
import expr.*;

public class TestSuite {
	
	private Variable p = new Variable("p");
	private Variable q = new Variable("q");
	private Variable r = new Variable("r");
	

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLawOfExcludedMiddle() {
		Expression e = new Disjunction(p, new Negation(p));
		assertTrue(e.isTautology());
	}

	@Test
	public void testLawOfContraposition() {
		Expression e = new Equivalence(
					new Implication(p, q),
					new Implication(
							new Negation(q),
							new Negation(p)));
		assertTrue(e.isTautology());
	}

	@Test
	public void testReductioAdAbsurdum() {
		Expression e = new Implication(
					new Conjunction(
							new Implication(
									new Negation(p),
									q),
							new Implication(
									new Negation(p),
									new Negation(q))),
					p);
		assertTrue(e.isTautology());
	}

	@Test
	public void testDeMorgansLaw() {
		Expression e = new Equivalence(
					new Negation(new Conjunction(p, q)),
					new Disjunction(new Negation(p),
									new Negation(q)));
		assertTrue(e.isTautology());
	}

	@Test
	public void testSyllogism() {
		Expression e = new Implication(
					new Conjunction(
							new Implication(p, q),
							new Implication(q, r)),
					new Implication(p, r));
		assertTrue(e.isTautology());
	}

	@Test
	public void testProofByCases() {
		Expression e = new Implication(
					new Conjunction(
							new Conjunction(
									new Disjunction(p, q),
									new Implication(p, r)),
							new Implication(q, r)),
					r);
		assertTrue(e.isTautology());
	}
}
