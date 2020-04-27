package ast.type.subclasses;

import ast.ASTNode;
import ast.type.AbstractType;
import ast.type.Type;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class DoubleType extends AbstractType{

	public DoubleType(int line, int column) {
		super(line, column);

		super.setNumberOfBytes(4);
	}

	@Override
	public String toString() {
		return "real";
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> v, TP param) {
		return v.visit(this, param);
	}

	@Override
	public Type arithmetic(Type other, ASTNode lineAndColumn) {
		if (other instanceof DoubleType) {
			return new DoubleType(lineAndColumn.getLine(), lineAndColumn.getColumn());
		}/* else if (other instanceof IntType) {
			return new DoubleType(lineAndColumn.getLine(), lineAndColumn.getColumn());
		}*/

		return super.arithmetic(other, lineAndColumn);
	}

	@Override
	public Type comparison(Type other, ASTNode lineAndColumn) {
		if (other instanceof DoubleType) {
			return new IntType(lineAndColumn.getLine(), lineAndColumn.getColumn());
		}

		return super.arithmetic(other, lineAndColumn);
	}

	@Override
	public Type canBeAssigned(Type other, ASTNode lineAndColumn) {
		if (other instanceof DoubleType) {
			return new DoubleType(lineAndColumn.getLine(), lineAndColumn.getColumn());
		}

		return super.canBeAssigned(other, lineAndColumn);
	}

	@Override
	public Type canBeMinus(ASTNode lineAndColumn) {
		return new DoubleType(lineAndColumn.getLine(), lineAndColumn.getColumn());
	}

	@Override
	public String suffix() {
		return "f";
	}

	@Override
	public String[] convertTo(Type to) {
		if (to instanceof CharType) {
			return new String[] {"f2i", "i2b"};
		} else if (to instanceof IntType) {
			return new String[] {"f2i"};
		} else if (to instanceof DoubleType) {
			return new String[] {};
		}
		return super.convertTo(to);
	}
}
