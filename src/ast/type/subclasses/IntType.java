package ast.type.subclasses;

import ast.ASTNode;
import ast.type.AbstractType;
import ast.type.Type;
import visitor.Visitor;

public class IntType extends AbstractType{

	public IntType(int line, int column) {
		super(line, column);
		super.setNumberOfBytes(2);
	}

	@Override
	public String toString() {
		return "int";
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> v, TP param) {
		return v.visit(this, param);
	}

	@Override
	public Type arithmetic(Type other, ASTNode lineAndColumn) {
		if (other instanceof IntType) {
			return new IntType(lineAndColumn.getLine(), lineAndColumn.getColumn());
		}

		return super.arithmetic(other, lineAndColumn);
	}

	@Override
	public Type logical(Type other, ASTNode lineAndColumn) {
		if (other instanceof IntType) {
			return new IntType(lineAndColumn.getLine(), lineAndColumn.getColumn());
		}

		return super.logical(other, lineAndColumn);
	}

	@Override
	public Type comparison(Type other, ASTNode lineAndColumn) {
		if (other instanceof IntType) {
			return new IntType(lineAndColumn.getLine(), lineAndColumn.getColumn());
		}

		return super.comparison(other, lineAndColumn);
	}

	@Override
	public Type canBeAssigned(Type other, ASTNode lineAndColumn) {
		if (other instanceof IntType) {
			return new IntType(lineAndColumn.getLine(), lineAndColumn.getColumn());
		}

		return super.canBeAssigned(other, lineAndColumn);
	}

	@Override
	public Type canBeMinus(ASTNode lineAndColumn) {
		return new IntType(lineAndColumn.getLine(), lineAndColumn.getColumn());
	}

	@Override
	public Type not(ASTNode lineAndColumn) {
		return new IntType(lineAndColumn.getLine(), lineAndColumn.getColumn());
	}

	@Override
	public boolean isBoolean(ASTNode lineAndColumn) {
		return true;
	}
}
