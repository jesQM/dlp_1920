package ast.expression.subclasses;

import ast.expression.AbstractExpression;
import ast.expression.Expression;

public class UnaryMinus extends AbstractExpression {

	private Expression expression;
	
	public UnaryMinus(int line, int column, Expression expression) {
		super(line, column);
		this.expression = expression;
	}

	@Override
	public String toString() {
		return "-"+expression;
	}
}
