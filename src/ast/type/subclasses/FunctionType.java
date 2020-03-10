package ast.type.subclasses;

import java.util.ArrayList;
import java.util.List;

import ast.definitions.subclasses.VarDefinition;
import ast.type.AbstractType;
import ast.type.Type;

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
}
