package ast.type.subclasses;

import ast.ASTNode;
import ast.expression.Expression;
import ast.type.AbstractType;
import ast.type.Type;
import errorHandler.ErrorHandler;
import visitor.Visitor;

import java.util.List;

public class ErrorType extends AbstractType {

    private String message;

    public ErrorType(int line, int column, String message) {
        super(line, column);
        this.message = message;
        ErrorHandler.getInstance().addError(this);
    }

    @Override
    public String toString() {
        return "Error at "+getLine()+":"+getColumn()+": " +message;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> v, TP param) {
        return v.visit(this, param);
    }

    @Override
    public Type arithmetic(Type other, ASTNode lineAndColumn) {
        return this;
    }

    @Override
    public Type squareBrackets(Type other, ASTNode lineAndColumn) {
        return this;
    }

    @Override
    public Type comparison(Type other, ASTNode lineAndColumn) {
        return this;
    }

    @Override
    public boolean isBoolean(ASTNode lineAndColumn) {
        return false;
    }

    @Override
    public Type canBeAssigned(Type other, ASTNode lineAndColumn) {
        return this;
    }

    @Override
    public Type not(ASTNode lineAndColumn) {
        return this;
    }

    @Override
    public Type canBeMinus(ASTNode lineAndColumn) {
        return this;
    }

    @Override
    public Type canBeInvoked(List<Expression> parameters, ASTNode lineAndColumn) {
        return this;
    }

    @Override
    public Type dot(String ID, ASTNode lineAndColumn) {
        return this;
    }
}
