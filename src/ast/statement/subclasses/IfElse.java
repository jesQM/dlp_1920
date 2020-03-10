package ast.statement.subclasses;

import java.util.ArrayList;
import java.util.List;

import ast.expression.Expression;
import ast.statement.AbstractStatement;
import ast.statement.Statement;

public class IfElse extends AbstractStatement{

	private Expression condition;
	private List<Statement> ifStatements;
	private List<Statement> elseStatements;
	
	public IfElse(int line, int column, Expression condition, List<Statement> ifStatements, List<Statement> elseStatements) {
		super(line, column);
		this.condition = condition;
		this.ifStatements = new ArrayList<Statement>(ifStatements);
		this.elseStatements = new ArrayList<Statement>(elseStatements);
	}

	public Expression getCondition() {
		return condition;
	}

	public List<Statement> getIfStatements() {
		return new ArrayList<>(this.ifStatements);
	}

	public List<Statement> getElseStatements() {
		return new ArrayList<>(this.elseStatements);
	}

	@Override
	public String toString() {
		if (elseStatements.size() == 0)
			return "if(" +condition+ "){ " +ifStatements+ " }";

		return "if(" +condition+ "){ " +ifStatements+ " } else { " +elseStatements+ " }";
	}
}
