package ast.definitions.subclasses;

import ast.definitions.AbstractDefinition;
import ast.statement.Statement;
import ast.type.Type;
import ast.utils.varDefinition.VarDefinitionStatement;

public class VarDefinition extends AbstractDefinition implements Statement{

	private VarDefinitionStatement varDefinitionStatement;
	
	public VarDefinition(int line, int column, String name, Type type) {
		super(line, column, name, type);
		varDefinitionStatement = new VarDefinitionStatement(line, column);
	}

	@Override
	public String toString() {
		return getType() + " " + getName();
	}
}
