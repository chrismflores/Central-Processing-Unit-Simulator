package Processor;
import java.util.ArrayList;
import java.util.List;

public class Lexer {
	
	private List<Token> tokenType = new ArrayList<>();

	public Lexer() {
	
	}


	public void lexer(String input) throws Exception {
		StringBuilder buffer = new StringBuilder();
		/* Looking at one Line at a time */
		String[] words = input.split("\\s+");
		/* Iterate over each word */
		for (String word : words) {
			switch (word.toLowerCase()) {
            case "math":
                tokenType.add(new Token(Token.TokenType.MATH, "000"));
                break;
            case "add":
                tokenType.add(new Token(Token.TokenType.ADD, "1110"));
                break;
            case "sub":
                tokenType.add(new Token(Token.TokenType.SUBTRACT, "1111"));
                break;
            case "mult":
                tokenType.add(new Token(Token.TokenType.MULTIPLY, "0111"));
                break;
            case "and":
                tokenType.add(new Token(Token.TokenType.AND, "1000"));
                break;
            case "or":
                tokenType.add(new Token(Token.TokenType.OR, "1001"));
                break;
            case "not":
                tokenType.add(new Token(Token.TokenType.NOT, "1011"));
                break;
            case "xor":
                tokenType.add(new Token(Token.TokenType.XOR, "1010"));
                break;
            case "copy":
                tokenType.add(new Token(Token.TokenType.COPY, "00001"));
                break;
            case "halt":
                tokenType.add(new Token(Token.TokenType.HALT, "00000"));
                break;
            case "branch":
                tokenType.add(new Token(Token.TokenType.BRANCH, "001"));
                break;
            case "jump":
                tokenType.add(new Token(Token.TokenType.JUMP, "000"));
                break;
            case "call":
                tokenType.add(new Token(Token.TokenType.CALL, "010"));
                break;
            case "push":
                tokenType.add(new Token(Token.TokenType.PUSH, "011"));
                break;
            case "load":
                tokenType.add(new Token(Token.TokenType.LOAD, "100"));
                break;
            case "return":
                tokenType.add(new Token(Token.TokenType.RETURN, "10000"));
                break;
            case "store":
                tokenType.add(new Token(Token.TokenType.STORE, "101"));
                break;
            case "peek":
                tokenType.add(new Token(Token.TokenType.PEEK, "110"));
                break;
            case "pop":
                tokenType.add(new Token(Token.TokenType.POP, "110"));
                break;
            case "interrupt":
                tokenType.add(new Token(Token.TokenType.INTERRUPT, "110"));
                break;
            case "equal":
                tokenType.add(new Token(Token.TokenType.EQUAL, "0000"));
                break;
            case "unequal":
                tokenType.add(new Token(Token.TokenType.UNEQUAL, "0001"));
                break;
            case "gt":
                tokenType.add(new Token(Token.TokenType.GREATERTHAN, "0100"));
                break;
            case "lt":
                tokenType.add(new Token(Token.TokenType.LESSTHAN, "0010"));
                break;
            case "ge":
                tokenType.add(new Token(Token.TokenType.GREATEROREQUAL, "0011"));
                break;
            case "le":
                tokenType.add(new Token(Token.TokenType.LESSOREQUAL, "0101"));
                break;
            case "shift":
                tokenType.add(new Token(Token.TokenType.SHIFT, buffer.toString()));
                break;
            case "left":
                tokenType.add(new Token(Token.TokenType.LEFT, "1100"));
                break;
            case "right":
                tokenType.add(new Token(Token.TokenType.RIGHT, "1101"));
                break;
            case "3r":
                tokenType.add(new Token(Token.TokenType.threeR, "11"));
                break;
            case "2r":
                tokenType.add(new Token(Token.TokenType.twoR, "10"));
                break;
            case "rd":
                tokenType.add(new Token(Token.TokenType.destOnly, "01"));
                break;
            default:
            	if(word.toLowerCase().charAt(0) == 'r') {
                    tokenType.add(new Token(Token.TokenType.REGISTER, word));
            	} else {
            		Integer.parseInt(word);
                    tokenType.add(new Token(Token.TokenType.NUMBER, word));
            	}
                break; 
			}
			
			
		}
        tokenType.add(new Token(Token.TokenType.NEWLINE, buffer.toString()));

	}
	
	public List<Token> getTokens() {
		return tokenType;
	}
	
}
