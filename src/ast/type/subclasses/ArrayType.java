package ast.type.subclasses;

import ast.type.AbstractType;
import ast.type.Type;

public class ArrayType extends AbstractType{

	private int length;
	private Type arrayOf;
	
	public ArrayType(int line, int column, Type arrayOf, int length) {
		super(line, column);
		this.arrayOf = arrayOf;
		this.length = length;
	}

	public Type getArrayOf() {
		return arrayOf;
	}

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
			result =  ((ArrayType) arrayOf).recursiveCreation(line, column, this.arrayOf, length);
		} else {
			result = new ArrayType(line, column, this.arrayOf, length);
			this.arrayOf = result;
		}

		return this;
	}
}
