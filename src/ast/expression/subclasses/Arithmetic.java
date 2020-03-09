package ast.expression.subclasses;

import ast.expression.AbstractExpression;
import ast.expression.Expression;

public class Arithmetic extends AbstractExpression {

	private String operator;
	private Expression leftSide;
	private Expression rightSide;
	
	public Arithmetic(int line, int column, String operator, Expression leftside, Expression rightside) {
		super(line, column);
		this.operator = operator;
		this.leftSide = leftside;
		this.rightSide = rightside;
	}

	@Override
	public String toString() {
		return "{ " +leftSide+ " " +operator+ " " +rightSide+ "}";
	}
}
