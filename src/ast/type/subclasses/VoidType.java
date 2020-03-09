package ast.type.subclasses;

import ast.type.AbstractType;

public class VoidType extends AbstractType{

	public VoidType(int line, int column) {
		super(line, column);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "void";
	}
}
