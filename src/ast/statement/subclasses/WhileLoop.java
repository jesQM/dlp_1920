package ast.statement.subclasses;

import java.util.ArrayList;
import java.util.List;

import ast.expression.Expression;
import ast.statement.AbstractStatement;
import ast.statement.Statement;

public class WhileLoop extends AbstractStatement{

	private Expression condition;
	private List<Statement> statements;
	
	public WhileLoop(int line, int column, Expression condition, List<Statement> statements) {
		super(line, column);
		this.statements = new ArrayList<Statement>(statements);
		this.condition = condition;
	}

	@Override
	public String toString() {
		return "while(" +condition+ "){ " +statements+ " }";
	}
}
