package ast;

import java.util.ArrayList;
import java.util.List;

import ast.definitions.Definition;
import visitor.Visitor;

public class Program extends AbstractASTNode{
	
	private List<Definition> definitions;
	
	public Program(int line, int column, List<Definition> definitions) {
		super(line, column);
		this.definitions = new ArrayList<>(definitions);
	}

	public List<Definition> getDefinitions() {
		return new ArrayList<>(this.definitions);
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> v, TP param) {
		return v.visit(this, param);
	}
}
