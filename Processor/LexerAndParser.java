package Processor;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class LexerAndParser {

	@Test
	void test() {

		/** Test the lexer by running Main, this will call the lexer method 
		 * and lex every keyword printing it in the console. As you can see 
		 * after every new line there is a new line. */
		
		/** Test the parser by running Main, this will call the lexer method 
		 * and lex every keyword printing it in the console. The Parser will
		 * then parse the all tokens as long as it is in the correct order 
		 * I print the list after each parse method is complete and As you can
		 * see the list is now method because every token has been parsed */

List<Token> allTokens = new ArrayList<>();
		Lexer lexer = new Lexer();

        String[] instructions = {
            "math add R1 R2 R3",
            "math mult R1 R2",
            "branch 2R R1 gt R2 12",
            "branch 3R R5 le R8 R9 10",
            "call 2R R1 gt R2 12",
            "call 3R R5 le R8 R9 10",
            "push 33",
            "push R1 16",
            "push 2R R1 sub R2",
            "push 3R R1 add R2 R3",
            "push 33 mult Rd",
            "load 100 Rd",
            "load R1 R2 Rd",
            "load R4 31 Rd",
            "store 12 Rd",
            "store R1 R2 Rd",
            "store R2 32 Rd",
            "copy 123 Rd",
            "jump 12",
            "jump 52 Rd",
            "peek R1 12 Rd",
            "peek R3 R4 Rd",
            "pop Rd"
        };

        for (String instruction : instructions) {
            try {
                lexer.lexer(instruction);
                List<Token> tokens = lexer.getTokens();
                allTokens.addAll(tokens);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
			
	        Parser parser = new Parser(allTokens);
			try {
				parser.parse();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
