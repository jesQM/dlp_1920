package ast.expression.subclasses;

import ast.expression.AbstractExpression;
import ast.expression.Expression;
import visitor.Visitor;

public class CharLiteral extends AbstractExpression {
	
	private char value;
	
	public CharLiteral(int line, int column, char value) {
		super(line, column);
		this.value = value;
	}

	public char getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "\'"+value+"\'";
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> v, TP param) {
		return v.visit(this, param);
	}
}
