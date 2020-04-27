package visitor.codeGeneration;

import ast.type.Type;

import java.io.PrintStream;

public class CodeGenerator {
    private int indentationNumber = 0;
    private int labelCount = 0;
    private StringBuilder code = new StringBuilder();

    public void writeCode(String code) {
        this.writeIndentation();
        this.code.append(code);
        this.code.append('\n');
    }

    public void writeComment(String comment){
        this.writeIndentation();
        this.code.append("\' ");
        this.code.append(comment);
        this.code.append('\n');
    }

    public void incrementIndentation(){
        this.indentationNumber++;
    }

    public void decrementIndentation(){
        if (this.indentationNumber > 0)
            this.indentationNumber--;
    }

    public int getLabels(int amount) {
        labelCount += amount;
        return labelCount-amount;
    }

    private void writeIndentation(){
        for (int i = 0; i < indentationNumber; i++)
            code.append('\t');
    }

    public void showCode(PrintStream out){
        out.print(code);
    }

    public void convert(Type from, Type to) {
        String[] lines = from.convertTo(to);
        for (String line: lines) {
            writeCode(line);
        }

    }

    public void writeLabel(String name) {
        this.code.append(" " + name + ":");
        this.code.append('\n');
    }
}
