package ast.expression.subclasses;

import ast.expression.AbstractExpression;
import ast.expression.Expression;
import ast.type.Type;

public class Cast extends AbstractExpression {
	
	private Type toType;
	private Expression expression;
	
	public Cast(int line, int column, Type toType, Expression expression) { //TODO; add Expression to constructor
		super(line, column);
		this.toType = toType;
		this.expression = expression;
	}

	@Override
	public String toString() {
		return "{ ("+toType+")" +expression+ " }";
	}
}
