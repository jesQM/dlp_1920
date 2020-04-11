package visitor.identification;

import ast.definitions.Definition;
import ast.definitions.subclasses.FuncDefinition;
import ast.definitions.subclasses.VarDefinition;
import ast.expression.subclasses.Variable;
import ast.statement.subclasses.FunctionInvoke;
import ast.type.subclasses.ErrorType;
import errorHandler.ErrorHandler;
import symbolTable.SymbolTable;
import visitor.AbstractVisitor;

public class IdentificationVisitor extends AbstractVisitor<Void, Void> {
    private SymbolTable st = new SymbolTable();

    @Override
    public Void visit(FuncDefinition ast, Void param) {
        st.insert(ast);

        // Add scope, traverse children, remove scope
        st.set();
        super.visit(ast, param);
        st.reset();
        return null;
    }

    @Override
    public Void visit(VarDefinition ast, Void param) {
        if (!st.insert(ast))
            new ErrorType(ast.getLine(), ast.getColumn(),String.format("The identifier \"%s\" has been already defined in this scope", ast.getName()) );

        return super.visit(ast, param);
    }

    @Override
    public Void visit(Variable ast, Void param) {
        Definition definition =  st.find(ast.getName());

        /*
        // Check done in the TypeCheckingVisitor
        if( definition == null )
            new ErrorType(ast.getLine(), ast.getColumn(),String.format("The identifier \"%s\" is not defined", ast.getName()) );
        */

        ast.setDefinition(definition);

        return super.visit(ast, param);
    }
}
