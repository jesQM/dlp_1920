package ast.expression.subclasses;

import ast.expression.AbstractExpression;
import ast.expression.Expression;

public class Variable extends AbstractExpression {
	
	private String name;
	
	public Variable(int line, int column, String name) {
		super(line, column);
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
