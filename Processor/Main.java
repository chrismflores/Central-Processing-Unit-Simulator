package Processor;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Main {

	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
            System.out.println("Missing an input or outputfile");
            System.exit(1);
        }
		
//        String inputFileName = args[0];
//        String outputFileName = args[1];

        if (args.length > 0) {
            System.out.println("args[0] exists.");
        } else {
            System.out.println("args[0] does not exist.");
        }
        
        // Reads input assembly file
        Path myPath = Paths.get(args[0]);
		List<String> lines = Files.readAllLines(myPath, StandardCharsets.UTF_8);
		List<Token> allTokens = new ArrayList<>();
		
		Lexer lexer = new Lexer();
		
		// inputs 1 line at a time to Lexer method
		for(String line : lines) {
			lexer.lexer(line);
			List<Token> tokens = lexer.getTokens();
			allTokens.addAll(tokens);
		}
		System.out.println(lexer.getTokens() + " ");
		Parser parser = new Parser(allTokens);
		parser.parse();
		System.out.println(parser.tokens);
		
        // Parse tokens and generate output
//        Parser parser = new Parser(tokens);
//        parser.program();
    

		}
	}


