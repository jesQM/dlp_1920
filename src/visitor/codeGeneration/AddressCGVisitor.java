package visitor.codeGeneration;

import ast.definitions.subclasses.VarDefinition;
import ast.expression.subclasses.Variable;

public class AddressCGVisitor extends AbstractCGVisitor<Void, Void> {

    @Override
    public Void visit(Variable ast, Void param) {
        if (ast.getDefinition().getScope() == 0) {
            cg.writeCode("pusha " + ((VarDefinition) ast.getDefinition()).getOffset());
        } else {
            cg.writeCode("push bp");
            cg.writeCode("pushi " + ((VarDefinition) ast.getDefinition()).getOffset());
            cg.writeCode("addi");
        }
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

 */
