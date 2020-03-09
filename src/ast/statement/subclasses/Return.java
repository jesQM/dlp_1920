package ast.statement.subclasses;

import ast.expression.Expression;
import ast.statement.AbstractStatement;

public class Return extends AbstractStatement{

	private Expression expression;
	
	public Return(int line, int column, Expression expression) {
		super(line, column);
		this.expression = expression;
	}

	@Override
	public String toString() {
		return "return " + expression;
	}
}
