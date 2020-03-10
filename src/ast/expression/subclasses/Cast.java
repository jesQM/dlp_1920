package ast.expression.subclasses;

import ast.expression.AbstractExpression;
import ast.expression.Expression;
import ast.type.Type;
import visitor.Visitor;

public class Cast extends AbstractExpression {
	
	private Type toType;
	private Expression expression;
	
	public Cast(int line, int column, Type toType, Expression expression) { //TODO; add Expression to constructor
		super(line, column);
		this.toType = toType;
		this.expression = expression;
	}

	public Expression getExpression() {
		return expression;
	}

	@Override
	public String toString() {
		return "{ ("+toType+")" +expression+ " }";
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> v, TP param) {
		return v.visit(this, param);
	}
}
