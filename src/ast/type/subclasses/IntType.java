package ast.type.subclasses;

import ast.type.AbstractType;
import visitor.Visitor;

public class IntType extends AbstractType{

	public IntType(int line, int column) {
		super(line, column);
	}

	@Override
	public String toString() {
		return "int";
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> v, TP param) {
		return v.visit(this, param);
	}
}
