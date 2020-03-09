package ast.definitions;

import ast.ASTNode;
import ast.type.Type;

public interface Definition extends ASTNode {
	String getName();
	Type getType();
}
