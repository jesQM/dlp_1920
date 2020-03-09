package ast.type.subclasses;

import ast.type.AbstractType;

public class IntType extends AbstractType{

	public IntType(int line, int column) {
		super(line, column);
	}

	@Override
	public String toString() {
		return "int";
	}
}
