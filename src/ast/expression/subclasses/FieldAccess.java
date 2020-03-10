package ast.expression.subclasses;

import ast.expression.AbstractExpression;
import ast.expression.Expression;

public class FieldAccess extends AbstractExpression {

	private String field;
	private Expression expression;
	
	public FieldAccess(int line, int column, String field, Expression expression) {
		super(line, column);
		this.field = field;
		this.expression = expression;
	}

	public Expression getExpression() {
		return expression;
	}

	@Override
	public String toString() {
		return expression + "." + field;
	}
}
