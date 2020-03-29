package ast.type.subclasses;

import java.util.ArrayList;
import java.util.List;

import ast.ASTNode;
import ast.definitions.subclasses.VarDefinition;
import ast.expression.Expression;
import ast.type.AbstractType;
import ast.type.Type;
import visitor.Visitor;

public class FunctionType extends AbstractType{

	private Type returnType;
	private List<VarDefinition> parameters;
	
	public FunctionType(int line, int column, List<VarDefinition> parameters, Type returnType) {
		super(line, column);
		this.parameters = new ArrayList<VarDefinition>(parameters);
		this.returnType = returnType;
	}

	public Type getReturnType() {
		return returnType;
	}

	public List<VarDefinition> getParameters() {
		return new ArrayList<>(parameters);
	}

	@Override
	public String toString() {
		return "function("+parameters+" :"+returnType+")";
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> v, TP param) {
		return v.visit(this, param);
	}

	@Override
	public Type canBeInvoked(List<Expression> parameters, ASTNode lineAndColumn) {
		// TODO;
		String msg = String.format("Cannot invoke %s", this.toString());
		return new ErrorType(lineAndColumn.getLine(), lineAndColumn.getColumn(), msg);
	}
}
