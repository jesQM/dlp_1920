import ast.ASTNode;
import ast.Program;
import errorHandler.ErrorHandler;
import parser.*;
import introspector.model.IntrospectorModel;
import introspector.view.IntrospectorTree;
import org.antlr.v4.runtime.*;
import visitor.OffsetVisitor;
import visitor.codeGeneration.*;
import visitor.identification.IdentificationVisitor;
import visitor.semantic.TypeCheckingVisitor;

public class Main {
	
	public static void main(String... args) throws Exception {
		   if (args.length<1) {
		        System.err.println("Please, pass me the input file.");
		        return;
		    }
		   		 			
		 // create a lexer that feeds off of input CharStream
		CharStream input = CharStreams.fromFileName(args[0]);
		CmmLexer lexer = new CmmLexer(input);

		// create a parser that feeds off the tokens buffer
		CommonTokenStream tokens = new CommonTokenStream(lexer); 
		CmmParser parser = new CmmParser(tokens);
		//parser.program();

		ASTNode ast = parser.program().ast;
		new IdentificationVisitor().visit( (Program) ast, null);
		new TypeCheckingVisitor().visit( (Program) ast, null);
		new OffsetVisitor().visit( (Program) ast, null);

		IntrospectorModel model = new IntrospectorModel("Program", ast);
		new IntrospectorTree("Program", model);

		ErrorHandler errorHandler = ErrorHandler.getInstance();
		if (errorHandler.anyError()) {
			errorHandler.showErrors(System.err);
		} else {
			CodeGenerator cg = new CodeGenerator();
			ValueCGVisitor value = new ValueCGVisitor();
			AddressCGVisitor address = new AddressCGVisitor();
			ExecuteCGVisitor execute = new ExecuteCGVisitor();

			value.address = address;
			execute.address = address;
			address.address = address;

			value.execute = execute;
			address.execute = execute;
			execute.execute = execute;

			execute.value = value;
			address.value = value;
			value.value = value;

			execute.cg = cg;
			address.cg = cg;
			value.cg = cg;

			//AbstractCGVisitor<Void, Void> v = new ExecuteCGVisitor();
			execute.visit( (Program) ast, null);
		}
	}
	

}
