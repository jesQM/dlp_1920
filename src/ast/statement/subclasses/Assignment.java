package ast.statement.subclasses;

import ast.expression.Expression;
import ast.statement.AbstractStatement;
import visitor.Visitor;

public class Assignment extends AbstractStatement{

	private Expression expression1;
	private Expression expression2;
	
	public Assignment(int line, int column, Expression expression1, Expression expression2) {
		super(line, column);
		this.expression1 = expression1;
		this.expression2 = expression2;
	}

	public Expression getExpression1() {
		return expression1;
	}

	public Expression getExpression2() {
		return expression2;
	}

	@Override
	public String toString() {
		return expression1 + " = " + expression2;
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> v, TP param) {
		return v.visit(this, param);
	}
}
