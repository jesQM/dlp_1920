package ast;

import java.util.ArrayList;
import java.util.List;

import ast.definitions.Definition;

public class Program extends AbstractASTNode{
	
	private List<Definition> definitions;
	
	public Program(int line, int column, List<Definition> definitions) {
		super(line, column);
		this.definitions = new ArrayList<>(definitions);
	}
}
