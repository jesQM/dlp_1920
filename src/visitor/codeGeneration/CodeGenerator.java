package visitor.codeGeneration;

import java.io.PrintStream;

public class CodeGenerator {
    private int indentationNumber = 0;
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

    public void writeIndentation(){
        for (int i = 0; i < indentationNumber; i++)
            code.append('\t');
    }

    public void showCode(PrintStream out){
        out.print(code);
    }
}
