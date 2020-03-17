package ast.definitions;

import ast.AbstractASTNode;
import ast.type.Type;

public abstract class AbstractDefinition extends AbstractASTNode implements Definition {

	String name;
	Type type;
	int scope;
	
	public AbstractDefinition(int line, int column, String name, Type type) {
		super(line, column);
		this.name = name;
		this.type = type;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public int getScope() {
		return scope;
	}

	@Override
	public void setScope(int scope) {
		this.scope = scope;
	}
}
