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
	public <TP, TR> TR accept(Visitor<TP, TR> v, TP param) {
		return v.visit(this, param);
	}
}
