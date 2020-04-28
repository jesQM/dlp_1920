package visitor.codeGeneration;

import ast.Program;
import ast.definitions.Definition;
import ast.definitions.subclasses.FuncDefinition;
import ast.definitions.subclasses.VarDefinition;
import ast.statement.subclasses.*;
import ast.type.Type;
import ast.type.subclasses.FunctionType;
import ast.type.subclasses.VoidType;
import visitor.codeGeneration.utils.ReturnStatementDTO;

public class ExecuteCGVisitor extends AbstractCGVisitor<ReturnStatementDTO, Void>  {

    @Override
    public Void visit(Write ast, ReturnStatementDTO param) {
        cg.writeComment("* Write");
        ast.getExpression().accept(value, null);
        cg.writeCode("out" + ast.getExpression().getType().suffix());
        return null;
    }

    @Override
    public Void visit(Read ast, ReturnStatementDTO param) {
        cg.writeComment("* Read");
        ast.getExpression().accept(address, null);
        cg.writeCode("in" + ast.getExpression().getType().suffix());
        cg.writeCode("store" + ast.getExpression().getType().suffix());
        return null;
    }

    @Override
    public Void visit(Assignment ast, ReturnStatementDTO param) {
        ast.getExpression1().accept(address, null);
        ast.getExpression2().accept(value, null);
        cg.writeCode("store" + ast.getExpression1().getType().suffix());
        return null;
    }

    @Override
    public Void visit(FunctionType ast, ReturnStatementDTO param) {
        cg.writeComment("* Parameters");
        ast.getParameters().forEach(p -> p.accept(execute, param));
        return null;
    }

    @Override
    public Void visit(VarDefinition ast, ReturnStatementDTO param) {
        cg.writeComment(String.format("* %s %s (offset %s)", ast.getType(), ast.getName(), ast.getOffset()));
        return null;
    }

    @Override
    public Void visit(FuncDefinition ast, ReturnStatementDTO param) {
        cg.writeCode(" " + ast.getName() + ":");
        cg.incrementIndentation();

        ast.getType().accept(execute, param);

        cg.writeComment("* Local variables");
        int lastVarDefIndex = 0;
        int bytesLocals = 0;
        for (int i = 0; i < ast.getStatements().size(); i++) {
            if (!(ast.getStatements().get(i) instanceof VarDefinition)) { // Is !VarDef?
                lastVarDefIndex = i;
                if (i > 0)
                    bytesLocals = -((VarDefinition) ast.getStatements().get(i-1)).getOffset();
                break;
            } else {
                ast.getStatements().get(i).accept(this, param);
            }
        }

        cg.writeCode("enter\t" + bytesLocals);

        // statements
        int bytesReturn = ((FunctionType) ast.getType()).getReturnType().getNumberOfBytes();
        int bytesParams = ((FunctionType) ast.getType())
                .getParameters()
                .stream()
                .mapToInt( p -> p.getType().getNumberOfBytes() )
                .sum();

        ReturnStatementDTO returnInfo = new ReturnStatementDTO(bytesLocals, bytesParams, bytesReturn);
        for (int i = lastVarDefIndex; i < ast.getStatements().size(); i++) {
            ast.getStatements().get(i).accept(this, returnInfo);
        }

        // add a return if void
        if (bytesReturn == 0 )
            cg.writeCode("ret\t" + bytesReturn + ", " + bytesLocals + ", " + bytesParams);
        cg.decrementIndentation();
        return null;
    }

    @Override
    public Void visit(Program ast, ReturnStatementDTO param) {
        cg.writeCode("#source\t\"input.txt\"");
        cg.writeCode("");

        cg.incrementIndentation();
        int lastVarDefIndex = 0;
        for (int i = 0; i < ast.getDefinitions().size(); i++) {
            Definition def = ast.getDefinitions().get(i);
            if (ast.getDefinitions().get(i) instanceof VarDefinition) {
                def.accept(execute, param);
            } else {
                lastVarDefIndex = i;
                break;
            }
        }
        cg.decrementIndentation();

        cg.writeCode("");
        cg.writeComment("Invocation to the main function");
        cg.writeCode("call main");
        cg.writeCode("halt");

        for (int i = lastVarDefIndex; i < ast.getDefinitions().size(); i++) {
            ast.getDefinitions().get(i).accept(execute, param);
        }

        cg.showCode(System.out); // Show program output

        return null;
    }

    @Override
    public Void visit(IfElse ast, ReturnStatementDTO param) {
        int labelNumber = cg.getLabels(2);

        // condition
        cg.writeComment("* If statement");
        ast.getCondition().accept(value, null);
        cg.writeCode("jz\tlabel" + labelNumber);


        cg.writeComment("* Body of the if branch");
        ast.getIfStatements().forEach( s -> s.accept(execute, param));
        cg.writeCode("jmp\tlabel" + (labelNumber+1));

        // else
        cg.writeLabel("label" + labelNumber);
        cg.writeComment("* Body of the else branch");
        ast.getElseStatements().forEach( s -> s.accept(execute, param));

        cg.writeLabel("label" + (labelNumber+1));

        return null;
    }

    @Override
    public Void visit(WhileLoop ast, ReturnStatementDTO param) {
        int labelNumber = cg.getLabels(2);

        // start:
        cg.writeComment("* While");
        cg.writeLabel("label" + labelNumber);

        // condition
        ast.getCondition().accept(value, null);
        cg.writeCode("jz\tlabel" + (labelNumber+1));

        // body
        cg.writeComment("* Body of the while statement");
        ast.getStatements().forEach(stm -> stm.accept(execute, param));

        // jmp start
        cg.writeCode("jmp\tlabel" + labelNumber);
        cg.writeLabel("label" + (labelNumber+1));

        return null;
    }

    @Override
    public Void visit(Return ast, ReturnStatementDTO param) {
        cg.writeComment("* Return");
        ast.getExpression().accept(value, null);
        cg.writeCode(String.format("ret\t%s, %s, %s", param.bytesReturn, param.bytesLocals, param.bytesParams));
        return null;
    }

    @Override
    public Void visit(FunctionInvoke ast, ReturnStatementDTO param) {
        ast.getParameters().forEach( p -> p.accept(value, null) );
        cg.writeCode("call\t" + ast.getVariable().getName());

        Type t = ast.getType();
        if ( !( t instanceof VoidType) )
            cg.writeCode("pop" + t.suffix());

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


 execute[[IfElse: statement1 -> expression statement2* statement3*]] =
     int labelNumber = cg.getLabels(2);

     // condition
     <' * If statement>
     value[[expression]]
     <jz > labelNumber

     <' * Body of the if branch>
     statement2*.forEach( stm -> execute[[stm]])
     <jmp > (labelNumber+1)

     // else
     <label> labelNumber <:>
     <' * Body of the else branch>
     statement3*.forEach( stm -> execute[[stm]])

     <label> (labelNumber+1) <:>



 execute[[WhileLoop: statement1 -> expression statement2*]] =
     int labelNumber = cg.getLabel(2);
     // start:
     <' * While>
     <label> labelNumber <:>

     // condition
     value[[expression]]
     <jz label> (labelNumber+1)

     // body
     <' * Body of the while statement>
     statement2*.forEach( stm -> execute[[stm]])

     // jmp start
     <jmp label> labelNumber
     <label> (labelNumber+1) <:>


 execute[[Return: statement -> expression]](int bytesLocals, int bytesParams, int bytesReturn) =
     value[[expression]]
     <ret > bytesLocals <, > bytesParams <, > bytesReturn


 execute[[FuncInvoke: statement -> expression1 expression2*]] =
     expression2*.forEach( exp -> value[[exp]] )
     <call > expression1.name
     if ( !expression1.type.returnType instanceof VoidType )
     <pop> expression1.type.returnType.suffix()
 */
