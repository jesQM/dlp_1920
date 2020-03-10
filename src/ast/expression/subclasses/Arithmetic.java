package ast.expression.subclasses;

import ast.expression.AbstractExpression;
import ast.expression.Expression;
import visitor.Visitor;

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

	public Expression getLeftSide() {
		return leftSide;
	}

	public Expression getRightSide() {
		return rightSide;
	}

	@Override
	public String toString() {
		return "{ " +leftSide+ " " +operator+ " " +rightSide+ "}";
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> v, TP param) {
		return v.visit(this, param);
	}
}
