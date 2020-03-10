package ast.definitions.subclasses;

import java.util.ArrayList;
import java.util.List;

import ast.definitions.AbstractDefinition;
import ast.statement.Statement;
import ast.type.Type;
import ast.type.subclasses.FunctionType;
import visitor.Visitor;

public class FuncDefinition extends AbstractDefinition {

	private List<Statement> statements;
	
	public FuncDefinition(int line, int column, String name, Type returnType, List<VarDefinition> params, List<Statement> statements) {
		super(line, column, name, new FunctionType(line, column, params, returnType));
		this.statements = new ArrayList<>(statements);
	}

	public List<Statement> getStatements() {
		return new ArrayList<>(this.statements);
	}

	@Override
	public String toString() {
		return  getName() + " " + getType() + "{" + statements + '}';
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> v, TP param) {
		return v.visit(this, param);
	}
}
