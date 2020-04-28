package visitor.codeGeneration;

import ast.Program;
import ast.definitions.subclasses.FuncDefinition;
import ast.definitions.subclasses.VarDefinition;
import ast.expression.subclasses.*;
import ast.statement.subclasses.*;
import ast.type.subclasses.*;
import visitor.AbstractVisitor;

public abstract class AbstractCGVisitor<TP, TR> extends AbstractVisitor<TP, TR> {
    public CodeGenerator cg = new CodeGenerator();
    public ValueCGVisitor value = null; //TODO; Repair params
    public AddressCGVisitor address = null;
    public ExecuteCGVisitor execute = null;

    public AbstractCGVisitor(){



    }

    private void throwError(String templateName) {
        String msg = String.format("Undefined template %s for the code function %s", templateName, this.getClass().getName());
        throw new RuntimeException(msg);
    }

    @Override
    public TR visit(Program ast, TP param) {
        throwError("visitProgram");
        return null;
    }

    @Override
    public TR visit(ArrayType ast, TP param) {
        throwError("visitArrayType");
        return null;
    }

    @Override
    public TR visit(CharType ast, TP param) {
        throwError("visitCharType");
        return null;
    }

    @Override
    public TR visit(DoubleType ast, TP param) {
        throwError("visitDoubleType");
        return null;
    }

    @Override
    public TR visit(ErrorType ast, TP param) {
        throwError("visitErrorType");
        return null;
    }

    @Override
    public TR visit(BoolType ast, TP param) {
        throwError("visitBoolType");
        return null;
    }

    @Override
    public TR visit(FunctionType ast, TP param) {
        throwError("visitFunctionType");
        return null;
    }

    @Override
    public TR visit(IntType ast, TP param) {
        throwError("visitIntType");
        return null;
    }

    @Override
    public TR visit(RecordField ast, TP param) {
        throwError("visitRecordField");
        return null;
    }

    @Override
    public TR visit(RecordType ast, TP param) {
        throwError("visitRecordType");
        return null;
    }

    @Override
    public TR visit(VoidType ast, TP param) {
        throwError("visitVoidType");
        return null;
    }

    @Override
    public TR visit(Assignment ast, TP param) {
        throwError("visitAssignment");
        return null;
    }

    @Override
    public TR visit(FunctionInvoke ast, TP param) {
        throwError("visitFunctionInvoke");
        return null;
    }

    @Override
    public TR visit(IfElse ast, TP param) {
        throwError("visitIfElse");
        return null;
    }

    @Override
    public TR visit(Read ast, TP param) {
        throwError("visitRead");
        return null;
    }

    @Override
    public TR visit(Return ast, TP param) {
        throwError("visitReturn");
        return null;
    }

    @Override
    public TR visit(WhileLoop ast, TP param) {
        throwError("visitWhileLoop");
        return null;
    }

    @Override
    public TR visit(Write ast, TP param) {
        throwError("visitWrite");
        return null;
    }

    @Override
    public TR visit(Arithmetic ast, TP param) {
        throwError("visitArithmetic");
        return null;
    }

    @Override
    public TR visit(Cast ast, TP param) {
        throwError("visitCast");
        return null;
    }

    @Override
    public TR visit(CharLiteral ast, TP param) {
        throwError("visitCharLiteral");
        return null;
    }

    @Override
    public TR visit(DoubleLiteral ast, TP param) {
        throwError("visitDoubleLiteral");
        return null;
    }

    @Override
    public TR visit(FieldAccess ast, TP param) {
        throwError("visitFieldAccess");
        return null;
    }

    @Override
    public TR visit(Indexing ast, TP param) {
        throwError("visitIndexing");
        return null;
    }

    @Override
    public TR visit(IntLiteral ast, TP param) {
        throwError("visitIntLiteral");
        return null;
    }

    @Override
    public TR visit(BooleanLiteral ast, TP param) {
        throwError("visitBooleanLiteral");
        return null;
    }

    @Override
    public TR visit(Logical ast, TP param) {
        throwError("visitLogical");
        return null;
    }

    @Override
    public TR visit(Relational ast, TP param) {
        throwError("visitRelational");
        return null;
    }

    @Override
    public TR visit(UnaryMinus ast, TP param) {
        throwError("visitUnaryMinus");
        return null;
    }

    @Override
    public TR visit(UnaryNegation ast, TP param) {
        throwError("visitUnaryNegation");
        return null;
    }

    @Override
    public TR visit(Variable ast, TP param) {
        throwError("visitVariable");
        return null;
    }

    @Override
    public TR visit(FuncDefinition ast, TP param) {
        throwError("visitFuncDefinition");
        return null;
    }

    @Override
    public TR visit(VarDefinition ast, TP param) {
        throwError("visitVarDefinition");
        return null;
    }
}
