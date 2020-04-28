package visitor;

import ast.definitions.subclasses.FuncDefinition;
import ast.definitions.subclasses.VarDefinition;
import ast.expression.subclasses.Variable;
import ast.statement.Statement;
import ast.type.subclasses.FunctionType;
import ast.type.subclasses.RecordField;
import ast.type.subclasses.RecordType;

import javax.swing.plaf.nimbus.State;

public class OffsetVisitor extends AbstractVisitor<Integer, Void> {

    private int globalVariablesTotalOffset;

    @Override
    public Void visit(FunctionType ast, Integer param) {
        ast.getReturnType().accept(this, param);

        int parameterTotalSize = 0; // parameters, in reverse order
        for (int i = ast.getParameters().size()-1; i >= 0; i--) {
            VarDefinition def = ast.getParameters().get(i);
            def.accept(this, parameterTotalSize + 4);
            parameterTotalSize += def.getType().getNumberOfBytes();
        }

        return null;
    }

    @Override
    public Void visit(FuncDefinition ast, Integer param) {
        ast.getType().accept( this, param );

        int localTotalSize = 0;
        for (Statement stm: ast.getStatements()) {
            if (stm instanceof VarDefinition)
                localTotalSize += ((VarDefinition) stm).getType().getNumberOfBytes();
            stm.accept(this, -localTotalSize);
        }

        return null;
    }

    @Override
    public Void visit(VarDefinition ast, Integer param) {
        super.visit(ast, param);

        if (ast.getScope() == 0) {
            // Global
            ast.setOffset(this.globalVariablesTotalOffset);
            this.globalVariablesTotalOffset += ast.getType().getNumberOfBytes();
        } else {
            // Local or param
            ast.setOffset(param);
        }

        return null;
    }

    @Override
    public Void visit(RecordField ast, Integer param) {
        super.visit(ast, param);
        ast.setOffset(param);
        return null;
    }

    @Override
    public Void visit(RecordType ast, Integer param) {
        int fieldsTotalSize = 0;
        for (RecordField f: ast.getFields()) {
            f.accept(this, fieldsTotalSize);
            fieldsTotalSize += f.getType().getNumberOfBytes();
        }

        return null;
    }
}
