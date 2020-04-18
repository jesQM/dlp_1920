package visitor.codeGeneration;

import ast.Program;
import ast.definitions.subclasses.FuncDefinition;
import ast.definitions.subclasses.VarDefinition;
import ast.statement.subclasses.Assignment;
import ast.statement.subclasses.Read;
import ast.statement.subclasses.Write;
import ast.type.subclasses.FunctionType;

public class ExecuteCGVisitor extends AbstractCGVisitor<Void, Void>  {

    @Override
    public Void visit(Write ast, Void param) {
        cg.writeComment("* Write");
        ast.getExpression().accept(value, param);
        cg.writeCode("out" + ast.getExpression().getType().suffix());
        return null;
    }

    @Override
    public Void visit(Read ast, Void param) {
        cg.writeComment("* Read");
        ast.getExpression().accept(address, param);
        cg.writeCode("in" + ast.getExpression().getType().suffix());
        cg.writeCode("store" + ast.getExpression().getType().suffix());
        return null;
    }

    @Override
    public Void visit(Assignment ast, Void param) {
        ast.getExpression1().accept(address, param);
        ast.getExpression2().accept(value, param);
        cg.writeCode("store" + ast.getExpression1().getType().suffix());
        return null;
    }

    @Override
    public Void visit(FunctionType ast, Void param) {
        cg.writeComment("* Parameters");
        ast.getParameters().forEach(p -> p.accept(execute, param));
        return null;
    }

    @Override
    public Void visit(VarDefinition ast, Void param) {
        cg.writeComment(String.format("* %s %s offset(%s)", ast.getType(), ast.getName(), ast.getOffset()));
        return null;
    }

    @Override
    public Void visit(FuncDefinition ast, Void param) {
        cg.writeCode(ast.getName() + ":");
        cg.incrementIndentation();

        ast.getType().accept(execute, param);

        cg.writeComment("* Local variables");
        int bytesLocals = 0;
        for (int i = 0; i < ast.getStatements().size(); i++) {
            if (!(ast.getStatements().get(i) instanceof VarDefinition)) {
                if (i > 0)
                    bytesLocals = -((VarDefinition) ast.getStatements().get(i-1)).getOffset();
                break;
            }
        }

        cg.writeCode("enter " + bytesLocals);

        // statements
        int bytesReturn = ((FunctionType) ast.getType()).getReturnType().getNumberOfBytes();
        int bytesParams = ((FunctionType) ast.getType())
                .getParameters()
                .stream()
                .mapToInt( p -> p.getType().getNumberOfBytes() )
                .sum();
        ast.getStatements().forEach( stm -> stm.accept(execute, param) );

        // add a return if void
        if (bytesReturn == 0 )
            cg.writeCode("ret " + bytesReturn + ", " + bytesLocals + ", " + bytesParams);
        return null;
    }

    @Override
    public Void visit(Program ast, Void param) {
        cg.writeCode("# source\t\"input.txt\"");

        cg.incrementIndentation();
        ast.getDefinitions().forEach( def -> def.accept(execute, param) );
        cg.decrementIndentation();

        cg.writeComment("Invocation to the main function");
        cg.writeCode("call main");
        cg.writeCode("halt");

        return null;
    }
}

/**

 execute[[Write: stm -> exp]] =
     <' * Write>
     value[[exp]]
     <out> exp.type.suffix()

 execute[[Read: stm -> exp]] =
     <' * Read>
     address[[exp]]
     <in> exp.type.suffix()
     <store> exp.type.suffix()

 execute[[FunctionDef: def -> type ID statement*]] =
     ID <:>
     execute[[type]]

     <' * Local variables>
     int bytesLocals = 0;
     for (i = 0; i < statement*.size(); i++) {
     if (!statement*.get(i) instanceof VarDefinition) {
     if (i > 0)
     bytesLocals = -statement*.get(i-1).offset
     break;
     }
     }
     <enter > bytesLocals

     // statements
     int bytesReturn = type.returnType.numberOfBytes()
     int bytesParams = type.parameters.stream().mapToInt( param -> param.type.numberOfBytes() ).sum()
     statement*.forEach( stm -> execute[[stm]](bytesReturn, bytesLocals, bytesParams) )

     // add a return if void
     if (bytesReturn == 0 )
     <ret > bytesReturn <, > bytesLocals <, > bytesParams

 execute[[Assignment: stm -> exp1 exp2]] =
     address[[exp1]]
     value[[exp2]]
     <store> exp1.type.suffix()

 execute[[Program: program -> definition*]] =
     <#source\t"input.txt">
     definition*.forEach( def -> execute[[def]] )

     <' Invocation to the main function>
     <call main>
     <halt>

 execute[[FunctionType: type1 -> varDefinition* type2]] =
     <' * Parameters>
     varDefinition*.forEach(p -> execute[[p]]);

 execute[[VarDefinition: definition -> type ID]] =
     <' *> type.toString() ID <offset(> definition.offset <)>

 */
