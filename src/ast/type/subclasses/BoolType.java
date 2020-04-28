package ast.type.subclasses;

import ast.ASTNode;
import ast.type.AbstractType;
import ast.type.Type;
import visitor.Visitor;

public class BoolType extends AbstractType {

    public BoolType(int line, int column) {
        super(line, column);
        super.setNumberOfBytes(2);
    }

    @Override
    public String toString() {
        return "bool";
    }

    @Override
    public boolean isBoolean(ASTNode lineAndColumn) {
        return true;
    }

    @Override
    public Type canBeAssigned(Type other, ASTNode lineAndColumn) {
        if (other instanceof BoolType) {
            return new BoolType(lineAndColumn.getLine(), lineAndColumn.getColumn());
        }

        return super.canBeAssigned(other, lineAndColumn);
    }

    @Override
    public String suffix() {
        return "i";
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> v, TP param) {
        return v.visit(this, param);
    }
}
