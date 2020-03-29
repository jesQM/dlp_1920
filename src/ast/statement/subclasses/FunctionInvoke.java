package ast.statement.subclasses;

import java.util.ArrayList;
import java.util.List;

import ast.expression.Expression;
import ast.expression.subclasses.Variable;
import ast.statement.AbstractStatement;
import visitor.Visitor;

public class FunctionInvoke extends AbstractStatement implements Expression {
	
	private List<Expression> parameters;
	private Variable variable;
	private boolean lvalue;

	public FunctionInvoke(int line, int column, Variable name, List<Expression> parameters) {
		super(line, column);
		this.parameters = new ArrayList<Expression>(parameters);
		this.variable = name;
	}

	public List<Expression> getParameters() {
		return new ArrayList<>(this.parameters);
	}

	public Variable getVariable() {
		return variable;
	}

	@Override
	public String toString() {
		StringBuilder params = new StringBuilder();
		for (Expression param: parameters) {
			params.append(param + " ,");
		}
		if (params.length() > 0)
			params.subSequence(0, params.length()-2);

		return variable + "("+params+")";
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> v, TP param) {
		return v.visit(this, param);
	}

	@Override
	public boolean getLvalue() {
		return this.lvalue;
	}

	@Override
	public void setLvalue(boolean lvalue) {
		this.lvalue = lvalue;
	}
}
