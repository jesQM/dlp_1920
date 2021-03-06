package ast.definitions.subclasses;

import ast.definitions.AbstractDefinition;
import ast.definitions.Definition;
import ast.statement.AbstractStatement;
import ast.statement.Statement;
import ast.type.Type;
import visitor.Visitor;

public class VarDefinition extends AbstractStatement implements Definition {

	String name;
	Type type;
	int offset;
	private int scope;

	public VarDefinition(int line, int column, String name, Type type) {
		super(line, column);
		this.name = name;
		this.type = type;
	}

	@Override
	public String toString() {
		return getType() + " " + getName();
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Type getType() {
		return this.type;
	}

	@Override
	public int getScope() {
		return this.scope;
	}

	@Override
	public void setScope(int scope) {
		this.scope = scope;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> v, TP param) {
		return v.visit(this, param);
	}
}
