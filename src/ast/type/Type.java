package ast.type;

import ast.ASTNode;
import ast.expression.Expression;

import java.util.List;

public interface Type extends ASTNode {
    Type arithmetic(Type other, ASTNode lineAndColumn);
    Type squareBrackets(Type other, ASTNode lineAndColumn);
    Type comparison(Type other, ASTNode lineAndColumn);
    boolean isBoolean(ASTNode lineAndColumn);

    void canBeAssigned(Type other, ASTNode lineAndColumn);
    Type not(ASTNode lineAndColumn);
    void canBeMinus(ASTNode lineAndColumn);
    void canBeinvoked(List<Expression> parameters, ASTNode lineAndColumn);
    Type dot(String ID, ASTNode lineAndColumn);
}
