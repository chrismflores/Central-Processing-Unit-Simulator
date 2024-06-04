package Processor;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Processor.Token.TokenType;

public class Parser {
	/** The tokens. */
	// chang back to private 
	Queue<Token> tokens;

	/** Constructor that accepts collection of tokens */
	public Parser(List<Token> tokens) {
		this.tokens = new LinkedList<Token>(tokens);
		System.out.println("tokens.size: " + tokens.size());
	}

	/**
	 * matchandRemove method accepts a token type if the passed token type does not
	 * match the next type or if there are no more tokens then return null
	 * 
	 * if the passed token type matches the next token type remove that token and
	 * return it
	 */
	private Token matchandRemove(Token.TokenType type) {
		if (type == tokens.peek().getType()) {
			return tokens.remove();
		} else if (tokens.peek().getType() != type || tokens.isEmpty()) {
			return null;
		}
		return null;
	}

	/**
	 * expectEndsOfLine method uses matchandRemove to match and discard one or more
	 * ENDOFLINE tokens
	 * 
	 * if no ENDOFLINE tokens are found then throws exception
	 */
	private void expectNewline() throws Exception {
		if (matchandRemove(TokenType.NEWLINE) == null) {

			while (matchandRemove(Token.TokenType.NEWLINE) != null) {
				if (tokens.isEmpty()) {
					throw new Exception("No ENDOFLINE token was found");
				}
			}
		}
	}

	/**
	 * Peek accepts an integer and looks ahead that many tokens and returns that
	 * token. if there are not enough tokens to fulfill request then return null
	 */
	private Token peek(int i) {
		if (tokens.size() < i) {
			return null;
		} else {
			return (Token) tokens.toArray()[i];
		}
	}

	public void parse() throws Exception {
		while (!tokens.isEmpty()) {
			// If the keyword is MATH, pop it off the list
			if (tokens.peek().getType() == Token.TokenType.MATH) {
				parseMath();
			} else if (tokens.peek().getType() == Token.TokenType.BRANCH) {
				parseBranch();
			} else if (tokens.peek().getType() == Token.TokenType.HALT) {
//				parseHalt();
			} else if (tokens.peek().getType() == Token.TokenType.COPY) {
		    parseCopy();
			} else if (tokens.peek().getType() == Token.TokenType.JUMP) {
		    parseJump();
			} else if (tokens.peek().getType() == Token.TokenType.CALL) {
		    parseCall();
			} else if (tokens.peek().getType() == Token.TokenType.PUSH) {
		    parsePush();
			} else if (tokens.peek().getType() == Token.TokenType.POP) {
		    parsePop();
			} else if (tokens.peek().getType() == Token.TokenType.LOAD) {
		    parseLoad();
			} else if (tokens.peek().getType() == Token.TokenType.STORE) {
		    parseStore();
			} else if (tokens.peek().getType() == Token.TokenType.RETURN) {
//		    parseReturn();
			} else if (tokens.peek().getType() == Token.TokenType.PEEK) {
		    parsePeek();
			} 
			expectNewline();
		}
	}

	private void parsePeek() {
		Token keyword = matchandRemove(TokenType.PEEK);
		 if(peek(0).getType() == Token.TokenType.REGISTER && peek(1).getType() == Token.TokenType.REGISTER) {
				Token R1 = matchandRemove(TokenType.REGISTER);
				Token R2 = matchandRemove(TokenType.REGISTER);
				Token Rd = matchandRemove(TokenType.destOnly);
		 } else if (peek(0).getType() == Token.TokenType.REGISTER && peek(1).getType() == Token.TokenType.NUMBER){
			 Token R1 = matchandRemove(TokenType.REGISTER);
			 Token R2 = matchandRemove(TokenType.NUMBER);
			 Token Rd = matchandRemove(TokenType.destOnly);
		 }
	}

	private void parsePop() {
		Token keyword = matchandRemove(TokenType.POP);
		Token Rd = matchandRemove(TokenType.destOnly);

	}

	private void parseJump() {
		Token keyword = matchandRemove(TokenType.JUMP);
		if(peek(0).getType() == Token.TokenType.NUMBER && peek(1).getType() == Token.TokenType.destOnly) {
			Token Rd = matchandRemove(TokenType.NUMBER);
			Token num = matchandRemove(TokenType.destOnly);
			
		} else if (peek(0).getType() == Token.TokenType.NUMBER) {
			Token num = matchandRemove(TokenType.NUMBER);
		}
	}

	private void parseStore() {
		Token keyword = matchandRemove(TokenType.STORE);
		
		if (peek(0).getType() == Token.TokenType.NUMBER) {
			Token num = matchandRemove(peek(0).getType());
			Token Rd = matchandRemove(peek(0).getType());
			
		} else if(peek(0).getType() == Token.TokenType.REGISTER && peek(1).getType() == Token.TokenType.REGISTER) {
			Token R1 = matchandRemove(peek(0).getType());
			Token R2 = matchandRemove(peek(0).getType());
			Token Rd = matchandRemove(peek(0).getType());

		} else if(peek(0).getType() == Token.TokenType.REGISTER) {
			Token Rs = matchandRemove(peek(0).getType());
			Token num = matchandRemove(peek(0).getType());
			Token Rd = matchandRemove(peek(0).getType());
		}
	}

	private void parseLoad() {
		Token keyword = matchandRemove(TokenType.LOAD);
		
		if (peek(0).getType() == Token.TokenType.NUMBER) {
			Token num = matchandRemove(peek(0).getType());
			Token Rd = matchandRemove(peek(0).getType());
			
		} else if(peek(0).getType() == Token.TokenType.REGISTER && peek(1).getType() == Token.TokenType.REGISTER) {
			Token R1 = matchandRemove(peek(0).getType());
			Token R2 = matchandRemove(peek(0).getType());
			Token Rd = matchandRemove(peek(0).getType());

		} else if(peek(0).getType() == Token.TokenType.REGISTER) {
			Token Rs = matchandRemove(peek(0).getType());
			Token num = matchandRemove(peek(0).getType());
			Token Rd = matchandRemove(peek(0).getType());
		}
	}

	private void parsePush() {
		Token keyword = matchandRemove(TokenType.PUSH);
		
		if (peek(0).getType() == Token.TokenType.twoR || peek(0).getType() == Token.TokenType.threeR) {
			Token Register = matchandRemove(peek(0).getType());
			Token R1 = matchandRemove(TokenType.REGISTER);
		
			if (tokens.peek().getType() == Token.TokenType.ADD || tokens.peek().getType() == Token.TokenType.SUBTRACT
					|| tokens.peek().getType() == Token.TokenType.MULTIPLY || tokens.peek().getType() == Token.TokenType.AND
					|| tokens.peek().getType() == Token.TokenType.OR || tokens.peek().getType() == Token.TokenType.NOT
					|| tokens.peek().getType() == Token.TokenType.XOR || tokens.peek().getType() == Token.TokenType.LEFT
					|| tokens.peek().getType() == Token.TokenType.RIGHT) {
				Token MOP = matchandRemove(peek(0).getType());
			}			
			Token R2 = matchandRemove(TokenType.REGISTER);
			
			if(tokens.peek().getType() == Token.TokenType.REGISTER) {
				Token R3 = matchandRemove(TokenType.REGISTER);
			}
			
		} else if(peek(0).getType() == Token.TokenType.NUMBER && peek(2).getType() == Token.TokenType.destOnly) {
			Token Num = matchandRemove(peek(0).getType());
			if (tokens.peek().getType() == Token.TokenType.ADD || tokens.peek().getType() == Token.TokenType.SUBTRACT
					|| tokens.peek().getType() == Token.TokenType.MULTIPLY || tokens.peek().getType() == Token.TokenType.AND
					|| tokens.peek().getType() == Token.TokenType.OR || tokens.peek().getType() == Token.TokenType.NOT
					|| tokens.peek().getType() == Token.TokenType.XOR || tokens.peek().getType() == Token.TokenType.LEFT
					|| tokens.peek().getType() == Token.TokenType.RIGHT) {
				Token MOP = matchandRemove(peek(0).getType());
			}
			Token destOnly = matchandRemove(TokenType.destOnly);
			
		} else if(peek(0).getType() == Token.TokenType.REGISTER) {
			Token R1 = matchandRemove(peek(0).getType());
			Token Num = matchandRemove(peek(0).getType());
		} else if(peek(0).getType() == Token.TokenType.NUMBER) {
			Token Num = matchandRemove(peek(0).getType());
		}
		
	}

	private void parseCopy() {
		Token keyword = matchandRemove(TokenType.COPY);
		if (peek(0).getType() == Token.TokenType.NUMBER) {
			Token num = matchandRemove(TokenType.NUMBER);
			Token Rd = matchandRemove(TokenType.destOnly);
		}

	}

	private void parseHalt() {
		Token keyword = matchandRemove(TokenType.HALT);
	
	}

	private void parseCall() {
		// Removes the Call Token from list
		Token keyword = matchandRemove(TokenType.CALL);
		
		//removes the 2R or 3R
		Token register = matchandRemove(peek(0).getType());
		
		// if the next Token is a Register then Remove it from the List
		if (peek(0).getType() == Token.TokenType.REGISTER) {
			Token R1 = matchandRemove(peek(0).getType());
		}
		
		/*
		 * We are in Call, Call needs a BOP keyword look if the keyword exist, if it does
		 * pop it off the list
		 */
		if (tokens.peek().getType() == Token.TokenType.EQUAL || // Equals
			    tokens.peek().getType() == Token.TokenType.UNEQUAL || // Unequal
			    tokens.peek().getType() == Token.TokenType.GREATERTHAN || // Greater than
			    tokens.peek().getType() == Token.TokenType.LESSTHAN || // Less than
			    tokens.peek().getType() == Token.TokenType.GREATEROREQUAL || // Greater than or equal
			    tokens.peek().getType() == Token.TokenType.LESSOREQUAL) { // Less than or equal
			// saves the OP as BOP token
			Token BOP = matchandRemove(peek(0).getType());
		}
		
		// CAll R1 BOP (now we need to see if its  R2 R3 number OR R2 Number
		if (peek(0).getType() == Token.TokenType.REGISTER && 
				peek(1).getType() == Token.TokenType.REGISTER) {
			Token R2 = matchandRemove(peek(0).getType());
			Token R3 = matchandRemove(peek(0).getType());
		} else if (peek(0).getType() == Token.TokenType.REGISTER) {
			Token R2 = matchandRemove(peek(0).getType());
		} else {
			System.out.println("parseCall() twoR or threeR is broken");
		}
		
		if (peek(0).getType() == Token.TokenType.NUMBER) {
			Token num = matchandRemove(peek(0).getType());
		}		
	}

	private void parseMath() throws Exception {
		// saves the MATH token as keyword
		Token keyword = matchandRemove(TokenType.MATH);
		/*
		 * We are in MATH, Math needs a keyword look if the keyword exist, if it does
		 * pop it off the list
		 */
		if (tokens.peek().getType() == Token.TokenType.ADD || tokens.peek().getType() == Token.TokenType.SUBTRACT
				|| tokens.peek().getType() == Token.TokenType.MULTIPLY || tokens.peek().getType() == Token.TokenType.AND
				|| tokens.peek().getType() == Token.TokenType.OR || tokens.peek().getType() == Token.TokenType.NOT
				|| tokens.peek().getType() == Token.TokenType.XOR || tokens.peek().getType() == Token.TokenType.LEFT
				|| tokens.peek().getType() == Token.TokenType.RIGHT) {
			// saves the OP as MOP token
			Token MOP = matchandRemove(peek(0).getType());
			// MATH and MOP popped off, Now we need either twoR or threeR,
			// pop them off the list
			if (peek(0).getType() == Token.TokenType.REGISTER && peek(1).getType() == Token.TokenType.REGISTER
					&& peek(2).getType() == Token.TokenType.REGISTER) {
				Token R1 = matchandRemove(peek(0).getType());
				Token R2 = matchandRemove(peek(0).getType());
				Token R3 = matchandRemove(peek(0).getType());

			} else if (peek(0).getType() == Token.TokenType.REGISTER && peek(1).getType() == Token.TokenType.REGISTER) {
				Token R1 = matchandRemove(peek(0).getType());
				Token R2 = matchandRemove(peek(0).getType());
			} else {
				System.out.println("parseMath() twoR or threeR is broken");
			}
		} else {
			System.out.println("parseMath() No Operator");
		}
		// Math is complete Newline token Expected!
		// removes the NEWLINE token
	}

	private void parseBranch() throws Exception {
		// saves Branch as keyword
		Token keyword = matchandRemove(TokenType.BRANCH);
			keyword.setValue("001");
			keyword.toString();
		//removes the 2R or 3R
		Token register = matchandRemove(peek(0).getType());

		
		if (peek(0).getType() == Token.TokenType.REGISTER) {
			Token R1 = matchandRemove(peek(0).getType());
		}
		/*
		 * We are in MATH, Math needs a keyword look if the keyword exist, if it does
		 * pop it off the list
		 */
		if (tokens.peek().getType() == Token.TokenType.EQUAL || // Equals
			    tokens.peek().getType() == Token.TokenType.UNEQUAL || // Unequal
			    tokens.peek().getType() == Token.TokenType.GREATERTHAN || // Greater than
			    tokens.peek().getType() == Token.TokenType.LESSTHAN || // Less than
			    tokens.peek().getType() == Token.TokenType.GREATEROREQUAL || // Greater than or equal
			    tokens.peek().getType() == Token.TokenType.LESSOREQUAL) { // Less than or equal
			// saves the OP as BOP token
			Token BOP = matchandRemove(peek(0).getType());
		}
	
		if (peek(0).getType() == Token.TokenType.REGISTER && 
				peek(1).getType() == Token.TokenType.REGISTER) {
			Token R2 = matchandRemove(peek(0).getType());
			Token R3 = matchandRemove(peek(0).getType());
		} else if (peek(0).getType() == Token.TokenType.REGISTER) {
			Token R2 = matchandRemove(peek(0).getType());
		} else {
			System.out.println("parseBranch() twoR or threeR is broken");
		}
		
		if (peek(0).getType() == Token.TokenType.NUMBER) {
			Token num = matchandRemove(peek(0).getType());
		}
		}

}
