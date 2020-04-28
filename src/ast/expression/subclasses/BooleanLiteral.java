package ast.expression.subclasses;

import ast.expression.AbstractExpression;
import visitor.Visitor;

public class BooleanLiteral extends AbstractExpression {

    boolean value;

    public BooleanLiteral(int line, int column, boolean value) {
        super(line, column);
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    public int getNumericValue(){
        if (value)
            return 1;
        else
            return 0;
    }

    @Override
    public String toString() {
        return ""+value;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> v, TP param) {
        return v.visit(this, param);
    }
}
