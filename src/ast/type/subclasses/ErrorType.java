package ast.type.subclasses;

import ast.type.AbstractType;
import errorHandler.ErrorHandler;

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
}
