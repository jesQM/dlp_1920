package visitor.codeGeneration;

import ast.definitions.subclasses.VarDefinition;
import ast.expression.subclasses.FieldAccess;
import ast.expression.subclasses.Indexing;
import ast.expression.subclasses.Variable;
import ast.type.subclasses.RecordType;

public class AddressCGVisitor extends AbstractCGVisitor<Void, Void> {

    @Override
    public Void visit(Variable ast, Void param) {
        if (ast.getDefinition().getScope() == 0) {
            cg.writeCode("pusha\t" + ((VarDefinition) ast.getDefinition()).getOffset());
        } else {
            cg.writeCode("push\tbp");
            cg.writeCode("pushi\t" + ((VarDefinition) ast.getDefinition()).getOffset());
            cg.writeCode("addi");
        }
        return null;
    }

    @Override
    public Void visit(Indexing ast, Void param) {
        ast.getArray().accept(address, param);
        ast.getIndex().accept(value, param);
        cg.writeCode("pushi\t" + ast.getType().getNumberOfBytes());
        cg.writeCode("muli");
        cg.writeCode("addi");
        return null;
    }

    @Override
    public Void visit(FieldAccess ast, Void param) {
        ast.getExpression().accept(address, param);
        cg.writeCode("pushi\t" + ((RecordType) ast.getExpression().getType()).getField(ast.getField()).getOffset());
        cg.writeCode("addi");
        return null;
    }
}

/**

 address[[Variable: exp -> ID]] =
     if (exp.definition.scope==0)
        <pusha > exp.definition.offset
     else {
         <push bp>
         <pushi > exp.definition.offset
         <addi>
     }


 address[FieldAcess: expression1 -> expression2 ID] =
     address[[expression2]]
     <pushi > expression2.type.getField(ID).offset
     <addi >


 address[[Indexing: expression1 -> expression2 expression3]] =
     address[[expression2]]
     value[[expression3]]
     <pushi > expression1.type.numberOfBytes()
     <muli>
     <addi>

 */
