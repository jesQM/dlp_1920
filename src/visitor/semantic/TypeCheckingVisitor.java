package visitor.semantic;

import ast.definitions.subclasses.FuncDefinition;
import ast.expression.Expression;
import ast.expression.subclasses.*;
import ast.statement.subclasses.*;
import ast.type.Type;
import ast.type.subclasses.*;
import visitor.AbstractVisitor;

public class TypeCheckingVisitor extends AbstractVisitor<Type, Type> {

    @Override
    public Type visit(Arithmetic ast, Type param) {
        super.visit(ast, param);
        ast.setLvalue( false );

        // Type of the expression
        Type leftSide = ast.getLeftSide().getType();
        Type rightSide = ast.getRightSide().getType();

        ast.setType(leftSide.arithmetic(rightSide, ast));

        return null;
    }

    @Override
    public Type visit(Cast ast, Type param) {
        super.visit(ast, param);
        ast.setLvalue( false ); // ast.getExpression().getLvalue()

        // Type of the expression
        ast.setType( ast.getToType() );
        return null;
    }

    @Override
    public Type visit(CharLiteral ast, Type param) {
        super.visit(ast, param);
        ast.setLvalue(false);

        // Type of the expression
        ast.setType( new CharType(ast.getLine(), ast.getColumn()) );
        return null;
    }

    @Override
    public Type visit(DoubleLiteral ast, Type param) {
        super.visit(ast, param);
        ast.setLvalue(false);

        // Type of the expression
        ast.setType( new DoubleType(ast.getLine(), ast.getColumn()) );
        return null;
    }

    @Override
    public Type visit(FieldAccess ast, Type param) {
        super.visit(ast, param);
        ast.setLvalue(true);

        // Type of the expression
        ast.setType( ast.getExpression().getType().dot(ast.getField(), ast) );
        return null;
    }

    @Override
    public Type visit(Indexing ast, Type param) {
        super.visit(ast, param);
        ast.setLvalue( true ); // ast.getArray().getLvalue()

        // Type of the expression
        ast.setType( ast.getArray().getType().squareBrackets(ast.getIndex().getType(), ast) );
        return null;
    }

    @Override
    public Type visit(IntLiteral ast, Type param) {
        super.visit(ast, param);
        ast.setLvalue( false );

        // Type of the expression
        ast.setType( new IntType(ast.getLine(), ast.getColumn()) );
        return null;
    }

    @Override
    public Type visit(Relational ast, Type param) {
        super.visit(ast, param);
        ast.setLvalue( false );

        // Type of the expression
        Type leftSide = ast.getLeftSide().getType();
        Type rightSide = ast.getRightSide().getType();

        ast.setType(leftSide.comparison(rightSide, ast));
        return null;
    }

    @Override
    public Type visit(UnaryMinus ast, Type param) {
        super.visit(ast, param);
        ast.setLvalue( false );

        ast.setType( ast.getExpression().getType().canBeMinus(ast) );
        return null;
    }

    @Override
    public Type visit(UnaryNegation ast, Type param) {
        super.visit(ast, param);
        ast.setLvalue( false );

        ast.setType( ast.getExpression().getType().not(ast) );
        return null;
    }

    @Override
    public Type visit(Variable ast, Type param) {
        super.visit(ast, param);
        ast.setLvalue(true);

        ast.setType( ast.getDefinition().getType() ); // TODO; Is this OK?
        return null;
    }

    @Override
    public Type visit(FunctionInvoke ast, Type param) {
        super.visit(ast, param);
        ast.setLvalue(false);

        ast.setType( ast.getVariable().getType().canBeInvoked(ast.getParameters(), ast) );
        return null;
    }

    // * Error Detection \\
    @Override
    public Type visit(Assignment ast, Type param) {
        super.visit(ast, param);
        if ( !ast.getExpression1().getLvalue() )
            new ErrorType(ast.getLine(), ast.getColumn(), "Lvalue expected");

        ast.getExpression1().getType().canBeAssigned(ast.getExpression2().getType(), ast);
        return null;
    }

    @Override
    public Type visit(Read ast, Type param) {
        super.visit(ast, param);
        if ( !ast.getExpression().getLvalue() )
            new ErrorType(ast.getLine(), ast.getColumn(), "Lvalue expected");
        return null;
    }

    // Required, to pass the return type to the return statement
    @Override
    public Type visit(FuncDefinition ast, Type param) {
        Type t = ast.getType().accept( this, param );
        ast.getStatements().forEach( node -> node.accept(this, t) );
        return null;
    }

    // Required to know the return type of the function
    @Override
    public Type visit(FunctionType ast, Type param) {
        ast.getReturnType().accept(this, param);
        ast.getParameters().forEach( node -> node.accept(this, param) );
        return ast.getReturnType();
    }

    @Override
    public Type visit(Return ast, Type param) {
        super.visit(ast, param);

        param.canBeAssigned(ast.getExpression().getType(), ast);
        return null;
    }

    @Override
    public Type visit(IfElse ast, Type param) {
        super.visit(ast, param);

        if (!ast.getCondition().getType().isBoolean(ast))
            new ErrorType(ast.getCondition().getLine(), ast.getCondition().getColumn(), "The condition is not a boolean");
        return null;
    }

    @Override
    public Type visit(WhileLoop ast, Type param) {
        super.visit(ast, param);

        if(!ast.getCondition().getType().isBoolean(ast))
            new ErrorType(ast.getCondition().getLine(), ast.getCondition().getColumn(), "The stop condition is not boolean");

        return null;
    }
}
