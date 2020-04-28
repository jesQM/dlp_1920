package ast.type.subclasses;

import ast.ASTNode;
import ast.AbstractASTNode;
import ast.type.Type;
import visitor.Visitor;

public class RecordField extends AbstractASTNode implements ASTNode {
	private String name;
	private Type type;
	private int offset;

	public RecordField(int line, int column, String name, Type type) {
		super(line, column);
		this.type = type;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	public String toString() {
		return "(" + name + " x " + type + ")";
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> v, TP param) {
		return v.visit(this, param);
	}
}
