package visitor.semantic;

import ast.expression.subclasses.*;
import ast.statement.subclasses.Assignment;
import ast.statement.subclasses.FunctionInvoke;
import ast.statement.subclasses.Read;
import ast.statement.subclasses.Return;
import ast.type.subclasses.ErrorType;
import visitor.AbstractVisitor;

public class TypeCheckingVisitor extends AbstractVisitor<Void, Void> {

    @Override
    public Void visit(Arithmetic ast, Void param) {
        super.visit(ast, param);
        ast.setLvalue( false );
        return null;
    }

    @Override
    public Void visit(Cast ast, Void param) {
        super.visit(ast, param);
        ast.setLvalue( false ); // ast.getExpression().getLvalue()
        return null;
    }

    @Override
    public Void visit(CharLiteral ast, Void param) {
        super.visit(ast, param);
        ast.setLvalue(false);
        return null;
    }

    @Override
    public Void visit(DoubleLiteral ast, Void param) {
        super.visit(ast, param);
        ast.setLvalue(false);
        return null;
    }

    @Override
    public Void visit(FieldAccess ast, Void param) {
        super.visit(ast, param);
        ast.setLvalue(true);
        return null;
    }

    @Override
    public Void visit(Indexing ast, Void param) {
        super.visit(ast, param);
        ast.setLvalue( true ); // ast.getArray().getLvalue()
        return null;
    }

    @Override
    public Void visit(IntLiteral ast, Void param) {
        super.visit(ast, param);
        ast.setLvalue( false );
        return null;
    }

    @Override
    public Void visit(Relational ast, Void param) {
        super.visit(ast, param);
        ast.setLvalue( false );
        return null;
    }

    @Override
    public Void visit(UnaryMinus ast, Void param) {
        super.visit(ast, param);
        ast.setLvalue( false );
        return null;
    }

    @Override
    public Void visit(UnaryNegation ast, Void param) {
        super.visit(ast, param);
        ast.setLvalue( false );
        return null;
    }

    @Override
    public Void visit(Variable ast, Void param) {
        super.visit(ast, param);
        ast.setLvalue(true);
        return null;
    }

    @Override
    public Void visit(FunctionInvoke ast, Void param) {
        super.visit(ast, param);
        ast.setLvalue(false);
        return null;
    }

    // * Error Detection \\
    @Override
    public Void visit(Assignment ast, Void param) {
        super.visit(ast, param);
        if ( !ast.getExpression1().getLvalue() )
            new ErrorType(ast.getLine(), ast.getColumn(), "Lvalue expected");
        return null;
    }

    @Override
    public Void visit(Read ast, Void param) {
        super.visit(ast, param);
        if ( !ast.getExpression().getLvalue() )
            new ErrorType(ast.getLine(), ast.getColumn(), "Lvalue expected");
        return null;
    }

    @Override
    public Void visit(Return ast, Void param) {
        super.visit(ast, param);
        if ( ast.getExpression().getLvalue() )
            new ErrorType(ast.getLine(), ast.getColumn(), "Lvalue expected");
        return null;
    }
}
