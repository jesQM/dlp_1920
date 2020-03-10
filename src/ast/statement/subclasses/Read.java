package ast.statement.subclasses;

import ast.expression.Expression;
import ast.statement.AbstractStatement;
import visitor.Visitor;

public class Read extends AbstractStatement{

	private Expression expression;
	
	public Read(int line, int column, Expression expression) {
		super(line, column);
		this.expression = expression;
	}

	public Expression getExpression() {
		return expression;
	}

	@Override
	public String toString() {
		return "read " + expression;
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> v, TP param) {
		return v.visit(this, param);
	}
}
