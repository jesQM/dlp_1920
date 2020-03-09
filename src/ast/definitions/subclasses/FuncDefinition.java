package ast.definitions.subclasses;

import java.util.ArrayList;
import java.util.List;

import ast.definitions.AbstractDefinition;
import ast.statement.Statement;
import ast.type.Type;
import ast.type.subclasses.FunctionType;

public class FuncDefinition extends AbstractDefinition {

	private List<Statement> statements;
	
	public FuncDefinition(int line, int column, String name, Type returnType, List<VarDefinition> params, List<Statement> statements) {
		super(line, column, name, new FunctionType(line, column, params, returnType));
		this.statements = new ArrayList<>(statements);
	}

	@Override
	public String toString() {
		return  getName() + " " + getType() + "{" + statements + '}';
	}
}
