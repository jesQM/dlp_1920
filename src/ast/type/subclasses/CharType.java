package ast.type.subclasses;

import ast.type.AbstractType;
import visitor.Visitor;

public class CharType extends AbstractType{

	public CharType(int line, int column) {
		super(line, column);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "char";
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> v, TP param) {
		return v.visit(this, param);
	}
}
