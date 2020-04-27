package visitor.codeGeneration;

import ast.expression.subclasses.*;
import ast.statement.subclasses.FunctionInvoke;

public class ValueCGVisitor extends AbstractCGVisitor<Void, Void> {

    @Override
    public Void visit(Arithmetic ast, Void param) {
        ast.getLeftSide().accept(this, param);                  // value[[exp2]]
        cg.convert(ast.getLeftSide().getType(), ast.getType());    // exp2.type.convertTo(exp1.type)
        ast.getRightSide().accept(this, param);                 // value[[exp3]]
        cg.convert(ast.getRightSide().getType(), ast.getType());   // exp3.type.convertTo(exp1.type)

        switch (ast.getOperator()) {
            case "+":
                cg.writeCode( "add" + ast.getType().suffix() );
                break;
            case "-":
                cg.writeCode( "sub" + ast.getType().suffix() );
                break;
            case "*":
                cg.writeCode( "mul" + ast.getType().suffix() );
                break;
            case "/":
                cg.writeCode( "div" + ast.getType().suffix() );
                break;
            case "%":
                cg.writeCode( "mod" + ast.getType().suffix() );
                break;
            default:
                throw new RuntimeException("Operation not supported: " + ast.getOperator() );
        }
        return null;
    }

    @Override
    public Void visit(Relational ast, Void param) {
        ast.getLeftSide().accept(this, param);
        ast.getRightSide().accept(this, param);
        switch (ast.getOperator()) {
            case ">":
                cg.writeCode("gt" + ast.getType().suffix());
                break;
            case ">=":
                cg.writeCode("ge" + ast.getType().suffix());
                break;
            case "<":
                cg.writeCode("lt" + ast.getType().suffix());
                break;
            case "<=":
                cg.writeCode("le" + ast.getType().suffix());
                break;
            case "!=":
                cg.writeCode("ne" + ast.getType().suffix());
                break;
            case "==":
                cg.writeCode("eq" + ast.getType().suffix());
                break;
            default:
                throw new RuntimeException("Operation not supported: " + ast.getOperator() );
        }
        return null;
    }

    @Override
    public Void visit(Logical ast, Void param) {
        ast.getLeftSide().accept(this, param);
        ast.getRightSide().accept(this, param);
        switch (ast.getOperator()) {
            case "&&":
                cg.writeCode("and");
                break;
            case "||":
                cg.writeCode("or");
                break;
            default:
                throw new RuntimeException("Operation not supported: " + ast.getOperator() );
        }
        return null;
    }

    @Override
    public Void visit(UnaryNegation ast, Void param) {
        ast.getExpression().accept(this, param);
        cg.writeCode("not");
        return null;
    }

    @Override
    public Void visit(CharLiteral ast, Void param) {
        cg.writeCode("pushb\t" + ast.getAsciiValue());
        return null;
    }

    @Override
    public Void visit(IntLiteral ast, Void param) {
        cg.writeCode("pushi\t" + ast.getValue());
        return null;
    }

    @Override
    public Void visit(DoubleLiteral ast, Void param) {
        cg.writeCode("pushf\t" + ast.getValue());
        return null;
    }

    @Override
    public Void visit(Cast ast, Void param) {
        ast.getExpression().accept(this, param);
        cg.convert(ast.getExpression().getType(), ast.getToType());
        return null;
    }

    @Override
    public Void visit(Variable ast, Void param) {
        ast.accept(address, param);
        cg.writeCode("load" + ast.getType().suffix());
        return null;
    }

    @Override
    public Void visit(FieldAccess ast, Void param) {
        ast.accept(address, param);
        cg.writeCode("load" + ast.getType().suffix());
        return null;
    }

    @Override
    public Void visit(Indexing ast, Void param) {
        ast.accept(address, param);
        cg.writeCode("load" + ast.getType().suffix());
        return null;
    }

    @Override
    public Void visit(FunctionInvoke ast, Void param) {
        ast.getParameters().forEach( p -> p.accept(value, param) );
        cg.writeCode("call " + ast.getVariable().getName());
        return null;
    }
}

/**

 value[[Arithmetic: exp1 -> exp2 (+|-|*|/) exp3]] =
     value[[exp2]]
     exp2.type.convertTo(exp1.type)
     value[[exp3]]
     exp3.type.convertTo(exp1.type)
     switch (exp1.operator) {
         case "+": <add> exp1.type.suffix() break;
         case "-": <sub> exp1.type.suffix() break;
         case "*": <mul> exp1.type.suffix() break;
         case "/": <div> exp1.type.suffix() break;
    }


 value[[Relational: exp1 -> exp2 (>|>=|<|<=|!=|==) exp3]] =
     value[[exp2]]
     value[[exp3]]
     switch (exp1.operator) {
         case ">": <gt> exp1.type.suffix() break;
         case ">=": <ge> exp1.type.suffix() break;
         case "<": <lt> exp1.type.suffix() break;
         case "<=": <le> exp1.type.suffix() break;
         case "!=": <ne> exp1.type.suffix() break;
         case "==": <eq> exp1.type.suffix() break;
    }

 value[[Logical: exp1 -> exp2 (&& | ||) exp3]] =
     value[[exp2]]
     value[[exp3]]
     switch (exp1.operator) {
         case "&&": <and> break;
         case "||": <or> break;
    }

 value[[UnaryMinus: exp1 -> exp2]] =
     value[[exp2]]
     <not>

 value[[DoubleLiteral: exp -> REAL_CONSTANT]] =
     <pushf > exp.value

 value[[IntLiteral: exp -> INT_CONSTANT]] =
    <pushi > exp.value

 value[[CharLiteral: exp -> CHAR_CONSTANT]] =
    <pushb > exp.value

 value[[Cast: exp1 -> type exp2]] =
     value[[exp2]]
     exp2.type.convertTo(type)

 value[[Variable: exp -> ID]] =
     address[[exp]]
     <load> exp.type.suffix()

 value[[Indexing: expression1 -> expression2 expression3]] =
     address[[expression1]]
     <load> expression2.type.arrayOf.suffix()

 value[FieldAcess: expression1 -> expression2 ID] =
     address[[expression1]]
     <load> expression1.type.suffix()

 value[[FuncInvoke: expression1 -> expression2 expression3*]] =
     expression3*.forEach( exp -> value[[exp]] )
     <call > expression2.name

 */