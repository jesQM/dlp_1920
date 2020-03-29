package ast.expression.subclasses;

import ast.definitions.Definition;
import ast.expression.AbstractExpression;
import ast.expression.Expression;
import visitor.Visitor;

public class Variable extends AbstractExpression {
	
	private String name;
	private Definition definition;
	
	public Variable(int line, int column, String name) {
		super(line, column);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Definition getDefinition() {
		return definition;
	}

	public void setDefinition(Definition definition) {
		this.definition = definition;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> v, TP param) {
		return v.visit(this, param);
	}
}
