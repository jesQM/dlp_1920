package visitor;

import ast.Program;
import ast.definitions.subclasses.FuncDefinition;
import ast.definitions.subclasses.VarDefinition;
import ast.expression.subclasses.*;
import ast.statement.subclasses.*;
import ast.type.subclasses.*;

public abstract class AbstractVisitor<TP, TR> implements Visitor<TP, TR> {

    @Override
    public TR visit(Program ast, TP param) {
        ast.getDefinitions().forEach( node -> node.accept(this, param));
        return null;
    }

    @Override
    public TR visit(ArrayType ast, TP param) {
        ast.getArrayOf().accept( this, param );
        return null;
    }

    @Override
    public TR visit(CharType ast, TP param) {
        return null;
    }

    @Override
    public TR visit(DoubleType ast, TP param) {
        return null;
    }

    @Override
    public TR visit(ErrorType ast, TP param) {
        return null;
    }

    @Override
    public TR visit(FunctionType ast, TP param) {
        ast.getReturnType().accept(this, param);
        ast.getParameters().forEach( node -> node.accept(this, param) );
        return null;
    }

    @Override
    public TR visit(IntType ast, TP param) {
        return null;
    }

    @Override
    public TR visit(RecordField ast, TP param) {
        ast.getType().accept(this, param);
        return null;
    }

    @Override
    public TR visit(RecordType ast, TP param) {
        ast.getFields().forEach( node -> node.accept(this, param) );
        return null;
    }

    @Override
    public TR visit(VoidType ast, TP param) {
        return null;
    }

    @Override
    public TR visit(Assignment ast, TP param) {
        ast.getExpression1().accept(this, param);
        ast.getExpression2().accept(this, param);
        return null;
    }

    @Override
    public TR visit(FunctionInvoke ast, TP param) {
        ast.getVariable().accept(this, param);
        ast.getParameters().forEach(node -> node.accept(this, param) );
        return null;
    }

    @Override
    public TR visit(IfElse ast, TP param) {
        ast.getCondition().accept(this, param);
        ast.getIfStatements().forEach( node -> node.accept(this, param) );
        ast.getElseStatements().forEach( node -> node.accept(this, param) );
        return null;
    }

    @Override
    public TR visit(Read ast, TP param) {
        ast.getExpression().accept(this, param);
        return null;
    }

    @Override
    public TR visit(Return ast, TP param) {
        ast.getExpression().accept(this, param);
        return null;
    }

    @Override
    public TR visit(WhileLoop ast, TP param) {
        ast.getCondition().accept(this, param);
        ast.getStatements().forEach( node -> node.accept(this, param) );
        return null;
    }

    @Override
    public TR visit(Write ast, TP param) {
        ast.getExpression().accept(this, param);
        return null;
    }

    @Override
    public TR visit(Arithmetic ast, TP param) {
        ast.getLeftSide().accept(this, param);
        ast.getRightSide().accept(this, param);
        return null;
    }

    @Override
    public TR visit(Cast ast, TP param) {
        ast.getExpression().accept(this, param);
        return null;
    }

    @Override
    public TR visit(CharLiteral ast, TP param) {
        return null;
    }

    @Override
    public TR visit(DoubleLiteral ast, TP param) {
        return null;
    }

    @Override
    public TR visit(FieldAccess ast, TP param) {
        ast.getExpression().accept(this, param);
        return null;
    }

    @Override
    public TR visit(Indexing ast, TP param) {
        ast.getArray().accept(this, param);
        ast.getIndex().accept(this, param);
        return null;
    }

    @Override
    public TR visit(IntLiteral ast, TP param) {
        return null;
    }

    @Override
    public TR visit(Logical ast, TP param) {
        ast.getLeftSide().accept(this, param);
        ast.getRightSide().accept(this, param);
        return null;
    }

    @Override
    public TR visit(BoolType ast, TP param) {
        return null;
    }

    @Override
    public TR visit(BooleanLiteral ast, TP param) {
        return null;
    }

    @Override
    public TR visit(Relational ast, TP param) {
        ast.getLeftSide().accept(this, param);
        ast.getRightSide().accept(this, param);
        return null;
    }

    @Override
    public TR visit(UnaryMinus ast, TP param) {
        ast.getExpression().accept(this, param);
        return null;
    }

    @Override
    public TR visit(UnaryNegation ast, TP param) {
        ast.getExpression().accept(this, param);
        return null;
    }

    @Override
    public TR visit(Variable ast, TP param) {
        return null;
    }

    @Override
    public TR visit(FuncDefinition ast, TP param) {
        ast.getType().accept( this, param );
        ast.getStatements().forEach( node -> node.accept(this, param) );
        return null;
    }

    @Override
    public TR visit(VarDefinition ast, TP param) {
        ast.getType().accept(this, param);
        return null;
    }
}
