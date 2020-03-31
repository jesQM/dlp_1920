package ast.type.subclasses;

import ast.ASTNode;
import ast.type.AbstractType;
import ast.type.Type;
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

	@Override
	public Type arithmetic(Type other, ASTNode lineAndColumn) {
		if (other instanceof CharType) {
			return new CharType(lineAndColumn.getLine(), lineAndColumn.getColumn());
		} /*else if (other instanceof IntType) {
			return new IntType(lineAndColumn.getLine(), lineAndColumn.getColumn());
		}*/

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
}
