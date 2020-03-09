package ast.type.subclasses;

import ast.type.AbstractType;

public class CharType extends AbstractType{

	public CharType(int line, int column) {
		super(line, column);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "char";
	}
}
