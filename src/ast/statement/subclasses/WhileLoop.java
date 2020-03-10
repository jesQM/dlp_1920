package ast.statement.subclasses;

import java.util.ArrayList;
import java.util.List;

import ast.expression.Expression;
import ast.statement.AbstractStatement;
import ast.statement.Statement;
import visitor.Visitor;

public class WhileLoop extends AbstractStatement{

	private Expression condition;
	private List<Statement> statements;
	
	public WhileLoop(int line, int column, Expression condition, List<Statement> statements) {
		super(line, column);
		this.statements = new ArrayList<Statement>(statements);
		this.condition = condition;
	}

	public Expression getCondition() {
		return condition;
	}

	public List<Statement> getStatements() {
		return new ArrayList<>(this.statements);
	}

	@Override
	public String toString() {
		return "while(" +condition+ "){ " +statements+ " }";
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> v, TP param) {
		return v.visit(this, param);
	}
}
