package visitor.codeGeneration;

import ast.expression.subclasses.*;
import ast.type.subclasses.RecordType;

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
        }
        return null;
    }

    @Override
    public Void visit(Logical ast, Void param) {
        ast.getLeftSide().accept(this, param);
        ast.getRightSide().accept(this, param);
        switch (ast.getOperator()) {
            case "&&":
                cg.writeCode("and" + ast.getType().suffix());
                break;
            case "||":
                cg.writeCode("or" + ast.getType().suffix());
                break;
        }
        return null;
    }

    @Override
    public Void visit(UnaryMinus ast, Void param) {
        ast.getExpression().accept(this, param);
        cg.writeCode("not");
        return null;
    }

    @Override
    public Void visit(CharLiteral ast, Void param) {
        cg.writeCode("pushb " + ast.getValue());
        return null;
    }

    @Override
    public Void visit(IntLiteral ast, Void param) {
        cg.writeCode("pushi " + ast.getValue());
        return null;
    }

    @Override
    public Void visit(DoubleLiteral ast, Void param) {
        cg.writeCode("pushf " + ast.getValue());
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
        ast.accept(address, param); // address.visit(ast, param); //TODO; Which version is correct?
        cg.writeCode("load" + ast.getType().suffix());
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

 */