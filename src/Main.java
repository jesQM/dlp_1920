import ast.ASTNode;
import errorHandler.ErrorHandler;
import parser.*;
import introspector.model.IntrospectorModel;
import introspector.view.IntrospectorTree;
import org.antlr.v4.runtime.*;

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

		IntrospectorModel model = new IntrospectorModel("Program", ast);
		new IntrospectorTree("Program", model);

		ErrorHandler errorHandler = ErrorHandler.getInstance();
		if (errorHandler.anyError())
			errorHandler.showErrors(System.err);
	}
	

}