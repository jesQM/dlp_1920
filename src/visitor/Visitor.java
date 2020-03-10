package visitor;

import ast.Program;
import ast.definitions.subclasses.*;
import ast.expression.subclasses.*;
import ast.statement.subclasses.*;
import ast.type.subclasses.*;

public interface Visitor<TP,TR> {

    TR visit(Program ast, TP param);

    // Type
    TR visit(ArrayType ast, TP param);
    TR visit(CharType ast, TP param);
    TR visit(DoubleType ast, TP param);
    TR visit(ErrorType ast, TP param);
    TR visit(FunctionType ast, TP param);
    TR visit(IntType ast, TP param);
    TR visit(RecordField ast, TP param);
    TR visit(RecordType ast, TP param);
    TR visit(VoidType ast, TP param);

    // Statement
    TR visit(Assignment ast, TP param);
    TR visit(FunctionInvoke ast, TP param);
    TR visit(IfElse ast, TP param);
    TR visit(Read ast, TP param);
    TR visit(Return ast, TP param);
    TR visit(WhileLoop ast, TP param);
    TR visit(Write ast, TP param);

    // Expression
    TR visit(Arithmetic ast, TP param);
    TR visit(Cast ast, TP param);
    TR visit(CharLiteral ast, TP param);
    TR visit(DoubleLiteral ast, TP param);
    TR visit(FieldAccess ast, TP param);
    TR visit(Indexing ast, TP param);
    TR visit(IntLiteral ast, TP param);
    TR visit(Relational ast, TP param);
    TR visit(UnaryMinus ast, TP param);
    TR visit(UnaryNegation ast, TP param);
    TR visit(Variable ast, TP param);

    // Definition
    TR visit(FuncDefinition ast, TP param);
    TR visit(VarDefinition ast, TP param);

}
