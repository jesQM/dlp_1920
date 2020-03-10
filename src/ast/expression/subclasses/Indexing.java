package ast.expression.subclasses;

import ast.expression.AbstractExpression;
import ast.expression.Expression;
import visitor.Visitor;

public class Indexing extends AbstractExpression {

	private Expression array;
	private Expression index;
	
	public Indexing(int line, int column, Expression array, Expression index) {
		super(line, column);
		this.array = array;
		this.index = index;
	}

	public Expression getArray() {
		return array;
	}

	public Expression getIndex() {
		return index;
	}

	@Override
	public String toString() {
		return array + "["+index+"]";
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> v, TP param) {
		return v.visit(this, param);
	}

}
