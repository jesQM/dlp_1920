package ast.expression;

import ast.ASTNode;

public interface Expression extends ASTNode {
    boolean getLvalue();
    void setLvalue(boolean lvalue);
}
