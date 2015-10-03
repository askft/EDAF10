package parser;

import java.io.Reader;
import java.io.StringReader;

import expr.*;

/*
The grammar is given by:
	expr      ::= primary (’->’ primary)?
	primary   ::= term (’|’ term)*
	term      ::= factor (’&’ factor)*
	factor    ::= ID | ’!’ factor | ’(’ expr ’)’
*/

public class Parser {
    private Scanner scanner;
    private int token;

    public Expression build(Reader reader) {
        scanner = new Scanner(reader);
        token = scanner.nextToken();
        Expression expr = expr();
        if (token == Scanner.EOF) {
            return expr;
        } else {
            throw new ParserException("Trailing garbage after "
                    + scanner.token());
        }
    }

    public Expression build(String input) {
        return build(new StringReader(input));
    }

    private Expression expr() {
        Expression result, primary;
        result = primary();
        
        if (token == Scanner.IMPLIES) {
        	token = scanner.nextToken();
        	primary = primary();
        	result = new Implication(result, primary);
        }
        
        return result;
    }

    private Expression primary() {
        Expression result, term;
        result = term();
        
        while (token == '|') {
        	token = scanner.nextToken();
        	term = term();
        	result = new Disjunction(result, term);
        }
        
        return result;
     }

    private Expression term() {
        Expression result, factor;
        result = factor();
        
        while (token == '&') {
        	token = scanner.nextToken();
        	factor = factor();
        	result = new Conjunction(result, factor);
        }
        
        return result;
   }
    
    private Expression factor() {
    	Expression e;
    	
        switch (token) {
        
        case '(':
        	token = scanner.nextToken();
        	e = expr();
            if (token == ')') {
                token = scanner.nextToken();
            } else {
            	throw new ParserException("Expecting \")\", found: " + scanner.token());
            }
        	return e;
        	
        case Scanner.VARIABLE:
        	e = new Variable(scanner.token());
        	token = scanner.nextToken();
        	return e;
        	
        case '!':
        	token = scanner.nextToken();
        	return new Negation(factor());

        default:
            throw new ParserException("Unexpected " + scanner.token());
        }
    }
}