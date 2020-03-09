package ast.expression.subclasses;

import ast.expression.AbstractExpression;
import ast.expression.Expression;

public class Indexing extends AbstractExpression {

	private Expression array;
	private Expression index;
	
	public Indexing(int line, int column, Expression array, Expression index) {
		super(line, column);
		this.array = array;
		this.index = index;
	}

	@Override
	public String toString() {
		return array + "["+index+"]";
	}

}
