package ast.type;

import ast.ASTNode;
import ast.AbstractASTNode;
import ast.expression.Expression;
import ast.type.subclasses.ErrorType;

import java.util.List;

public abstract class AbstractType extends AbstractASTNode implements Type{

	protected int numberOfBytes = 0;

	public AbstractType(int line, int column) {
		super(line, column);
	}

	protected void setNumberOfBytes(int amount){
		this.numberOfBytes = amount;
	};

	@Override
	public Type arithmetic(Type other, ASTNode lineAndColumn) {
		String msg = String.format("Cannot perform arithmetic operation between %s and %s", this.toString(), other.toString());
		return new ErrorType(lineAndColumn.getLine(), lineAndColumn.getColumn(), msg);
	}

	@Override
	public Type squareBrackets(Type other, ASTNode lineAndColumn) {
		String msg = String.format("Cannot perform indexing operation between %s and %s", this.toString(), other.toString());
		return new ErrorType(lineAndColumn.getLine(), lineAndColumn.getColumn(), msg);
	}

	@Override
	public Type comparison(Type other, ASTNode lineAndColumn) {
		String msg = String.format("Cannot perform comparison operation between %s and %s", this.toString(), other.toString());
		return new ErrorType(lineAndColumn.getLine(), lineAndColumn.getColumn(), msg);
	}

	@Override
	public Type logical(Type other, ASTNode lineAndColumn) {
		String msg = String.format("Cannot perform logic operation between %s and %s", this.toString(), other.toString());
		return new ErrorType(lineAndColumn.getLine(), lineAndColumn.getColumn(), msg);
	}

	@Override
	public boolean isBoolean(ASTNode lineAndColumn) {
		return false;
	}

	@Override
	public Type canBeAssigned(Type other, ASTNode lineAndColumn) {
		String msg = String.format("Cannot assign %s and %s", this.toString(), other.toString());
		return new ErrorType(lineAndColumn.getLine(), lineAndColumn.getColumn(), msg);
	}

	@Override
	public Type not(ASTNode lineAndColumn) {
		String msg = String.format("Cannot '!' %s", this.toString());
		return new ErrorType(lineAndColumn.getLine(), lineAndColumn.getColumn(), msg);
	}

	@Override
	public Type canBeMinus(ASTNode lineAndColumn) {
		String msg = String.format("Cannot '-' %s", this.toString());
		return new ErrorType(lineAndColumn.getLine(), lineAndColumn.getColumn(), msg);
	}

	@Override
	public Type canBeInvoked(List<Expression> parameters, ASTNode lineAndColumn) {
		String msg = String.format("Cannot invoke %s", this.toString());
		return new ErrorType(lineAndColumn.getLine(), lineAndColumn.getColumn(), msg);
	}

	@Override
	public Type dot(String ID, ASTNode lineAndColumn) {
		String msg = String.format("Cannot access the field %s of %s", ID, this.toString());
		return new ErrorType(lineAndColumn.getLine(), lineAndColumn.getColumn(), msg);
	}

	@Override
	public int getNumberOfBytes() {
		return this.numberOfBytes;
	}

	@Override
	public String suffix() {
		return null;
	}
}
