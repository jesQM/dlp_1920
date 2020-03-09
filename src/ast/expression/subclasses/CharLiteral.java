package ast.expression.subclasses;

import ast.expression.AbstractExpression;
import ast.expression.Expression;

public class CharLiteral extends AbstractExpression {
	
	private char value;
	
	public CharLiteral(int line, int column, char value) {
		super(line, column);
		this.value = value;
	}

	@Override
	public String toString() {
		return "\'"+value+"\'";
	}
}
