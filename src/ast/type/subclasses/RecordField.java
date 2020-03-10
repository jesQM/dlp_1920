package ast.type.subclasses;

import ast.ASTNode;
import ast.AbstractASTNode;
import ast.type.Type;
import visitor.Visitor;

public class RecordField extends AbstractASTNode implements ASTNode {
	private String name;
	private Type type;

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

	@Override
	public String toString() {
		return type + " " + name;
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> v, TP param) {
		return v.visit(this, param);
	}
}
