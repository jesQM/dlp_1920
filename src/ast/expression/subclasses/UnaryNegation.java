package ast.expression.subclasses;

import ast.expression.AbstractExpression;
import ast.expression.Expression;

public class UnaryNegation extends AbstractExpression {

	private Expression expression;
	
	public UnaryNegation(int line, int column, Expression expression) {
		super(line, column);
		this.expression = expression;
	}

	public Expression getExpression() {
		return expression;
	}

	@Override
	public String toString() {
		return "!"+expression;
	}
}
