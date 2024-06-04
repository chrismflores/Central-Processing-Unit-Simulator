package Processor;

public class Token {
	/**
	 * The Enum TokenType.
	 */
	public static enum TokenType {
		MATH,  
		ADD,  
		SUBTRACT,  
		MULTIPLY,  
		AND,  
		OR,  
		NOT,  
		XOR,  
		COPY,  
		HALT,  
		BRANCH,  
		JUMP,  
		CALL,  
		PUSH,  
		LOAD,  
		RETURN,  
		STORE,  
		PEEK,  
		POP,  
		INTERRUPT,  
		EQUAL,  
		UNEQUAL,  
		GREATERTHAN,  
		LESSTHAN,  
		GREATEROREQUAL,  
		LESSOREQUAL,  
		SHIFT,  
		LEFT,  
		RIGHT,
		NEWLINE,
		NUMBER,
		REGISTER, 
		twoR, 
		threeR, 
		destOnly
	}

	/** The type. */
	private TokenType type;

	/** The value. */
	private String value;

	/**
	 * Instantiates a new token.
	 *
	 * @param type  the type
	 * @param value the value
	 */
	public Token(TokenType type, String value) {
		this.type = type;
		this.value = value;
	}

	public Token(TokenType register, char charAt) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public TokenType getType() {
		return type;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 * @param string 
	 *
	 * @return the value
	 */
	public String setValue(String value) {
		this.value = value;
		return value;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return type + "(" + value + ")";
	}

}

