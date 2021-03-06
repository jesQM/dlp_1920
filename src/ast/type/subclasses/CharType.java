package ast.type.subclasses;

import ast.ASTNode;
import ast.type.AbstractType;
import ast.type.Type;
import visitor.Visitor;

public class CharType extends AbstractType{

	public CharType(int line, int column) {
		super(line, column);

		super.setNumberOfBytes(1);
	}

	@Override
	public String toString() {
		return "char";
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> v, TP param) {
		return v.visit(this, param);
	}

	@Override
	public Type arithmetic(Type other, ASTNode lineAndColumn) {
		if (other instanceof CharType) {
			return new IntType(lineAndColumn.getLine(), lineAndColumn.getColumn());
		}

		return super.arithmetic(other, lineAndColumn);
	}

	@Override
	public Type comparison(Type other, ASTNode lineAndColumn) {
		if (other instanceof CharType) {
			return new IntType(lineAndColumn.getLine(), lineAndColumn.getColumn());
		} else if (other instanceof IntType) {
			return new IntType(lineAndColumn.getLine(), lineAndColumn.getColumn());
		}

		return super.arithmetic(other, lineAndColumn);
	}

	@Override
	public Type canBeAssigned(Type other, ASTNode lineAndColumn) {
		if (other instanceof CharType) {
			return new CharType(lineAndColumn.getLine(), lineAndColumn.getColumn());
		}

		return super.canBeAssigned(other, lineAndColumn);
	}

	@Override
	public String suffix() {
		return "b";
	}

	@Override
	public String[] convertTo(Type to) {
		if (to instanceof CharType) {
			return new String[] {};
		} else if (to instanceof IntType) {
			return new String[] {"b2i"};
		} else if (to instanceof DoubleType) {
			return new String[] {"b2i", "i2f"};
		}
		return super.convertTo(to);
	}
}
