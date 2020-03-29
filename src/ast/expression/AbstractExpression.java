package ast.expression;

import ast.AbstractASTNode;
import ast.type.Type;

public abstract class AbstractExpression extends AbstractASTNode implements Expression{

	boolean lvalue;
	Type type;

	public AbstractExpression(int line, int column) {
		super(line, column);
	}

	@Override
	public boolean getLvalue() {
		return lvalue;
	}

	@Override
	public void setLvalue(boolean lvalue) {
		this.lvalue = lvalue;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setType(Type type) {
		this.type = type;
	}
}
