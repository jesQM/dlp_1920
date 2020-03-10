package ast.statement.subclasses;

import java.util.ArrayList;
import java.util.List;

import ast.expression.Expression;
import ast.expression.subclasses.Variable;
import ast.statement.AbstractStatement;

public class FunctionInvoke extends AbstractStatement implements Expression {
	
	private List<Expression> expressions;
	private Variable variable;
	
	public FunctionInvoke(int line, int column, Variable name, List<Expression> parameters) {
		super(line, column);
		this.expressions = new ArrayList<Expression>(parameters);
		this.variable = name;
	}

	public List<Expression> getExpressions() {
		return new ArrayList<>(this.expressions);
	}

	public Variable getVariable() {
		return variable;
	}

	@Override
	public String toString() {
		StringBuilder params = new StringBuilder();
		for (Expression param: expressions) {
			params.append(param + " ,");
		}
		if (params.length() > 0)
			params.subSequence(0, params.length()-2);

		return variable + "("+params+")";
	}
}
