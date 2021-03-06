package ast.type;

import ast.ASTNode;
import ast.expression.Expression;

import java.util.List;

public interface Type extends ASTNode {
    Type arithmetic(Type other, ASTNode lineAndColumn);
    Type squareBrackets(Type other, ASTNode lineAndColumn);
    Type comparison(Type other, ASTNode lineAndColumn);
    Type logical(Type other, ASTNode lineAndColumn);
    boolean isBoolean(ASTNode lineAndColumn);

    Type canBeAssigned(Type other, ASTNode lineAndColumn);
    Type not(ASTNode lineAndColumn);
    Type canBeMinus(ASTNode lineAndColumn);
    Type canBeInvoked(List<Expression> parameters, ASTNode lineAndColumn);
    Type dot(String ID, ASTNode lineAndColumn);

    int getNumberOfBytes();

    String suffix();
    String[] convertTo(Type to);
}
