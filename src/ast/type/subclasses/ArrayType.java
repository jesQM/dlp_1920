package ast.type.subclasses;

import ast.ASTNode;
import ast.type.AbstractType;
import ast.type.Type;
import visitor.Visitor;

public class ArrayType extends AbstractType{

	private int length;
	private Type arrayOf;
	
	public ArrayType(int line, int column, Type arrayOf, int length) {
		super(line, column);
		this.arrayOf = arrayOf;
		this.length = length;

		super.setNumberOfBytes( this.length * this.arrayOf.getNumberOfBytes() );
	}

	private void updateNumberOfBytes(){
		super.setNumberOfBytes( this.length * this.arrayOf.getNumberOfBytes() );
	}

	public Type getArrayOf() {
		return arrayOf;
	}

	public int getLength() {
		return length;
	}

	@Override
	public String toString() {
		return "[" + this.length +"," + this.arrayOf.toString() + "]";
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> v, TP param) {
		return v.visit(this, param);
	}

	@Override
	public Type squareBrackets(Type other, ASTNode lineAndColumn) {
		if (other instanceof IntType) {
			return arrayOf;
		}

		return super.squareBrackets(other, lineAndColumn);
	}

	@Override
	public Type canBeAssigned(Type other, ASTNode lineAndColumn) {
		if (other instanceof ArrayType) {
			return new ArrayType(lineAndColumn.getLine(), lineAndColumn.getColumn(), ((ArrayType)other).getArrayOf(), ((ArrayType)other).getLength());
		}

		return super.canBeAssigned(other, lineAndColumn);
	}

	// Create arrays correctly

	public static ArrayType createArray(int line, int column, Type arrayOf, int length){
		ArrayType result = null;
		if (arrayOf instanceof ArrayType) {
			result =  ((ArrayType) arrayOf).recursiveCreation(line, column, arrayOf, length);
		} else {
			result = new ArrayType(line, column, arrayOf, length);
		}

		return result;
	}

	private ArrayType recursiveCreation(int line, int column, Type arrayOf, int length) {
		ArrayType result = null;
		if (arrayOf instanceof ArrayType) {
			result = ((ArrayType) arrayOf).recursiveCreation(line, column, this.arrayOf, length);
			result.updateNumberOfBytes();
		} else {
			result = new ArrayType(line, column, this.arrayOf, length);
			this.arrayOf = result;
		}

		return this;
	}
}
