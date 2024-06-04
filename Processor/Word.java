package Processor;
import java.util.ArrayList;
import java.util.List;

public class Word {

	/** The bits. **/
	// Creates an array of Bits
	Bit[] bits;
	
	
	// instantiates a Word object
	// a Word is an array of 32 bits 
	Word() {
		bits = new Bit[32];
		for (int i = 0; i < 32; i++) {
			bits[i] = new Bit();
		}
	}


	// set bit i's value
	void setBit(int i, Bit value) {
		bits[i].set(value.getValue());
	}


	// Get a new Bit that has the same value as bit i
	Bit getBit(int i) {
		return new Bit(bits[i]);
	}
	
	// and two words, returning a new Word
	Word and(Word other) {
		Word result = new Word();
		for (int i = 0; i < bits.length; i++) {
			result.setBit(i, bits[i].and(other.bits[i]));
		}

		return result;
	}


	// or two words, returning a new Word
	Word or(Word other) {
		Word result = new Word();
		for (int i = 0; i < bits.length; i++) {
			result.setBit(i, bits[i].or(other.bits[i]));
		}
		return result;
	}


	// xor two words, returning a new Word
	Word xor(Word other) {
		Word result = new Word();
		for (int i = 0; i < bits.length; i++) {
			result.setBit(i, bits[i].xor(other.bits[i]));
		}
		return result;
	}


	// negate this word, creating a new Word
	Word not() {
		Word result = new Word();
		for (int i = 0; i < bits.length; i++) {
			result.setBit(i, bits[i].not());
		}
		return result;
	}


	// left shift this word by amount bits, creating a new Word
	Word leftShift(int amount) {
		Word result = new Word();

		for (int i = 0; i < bits.length - amount; i++) {
			result.bits[i + amount] = bits[i];
		}

		for (int i = 0; i < amount; i++) {
			result.setBit(i, new Bit());
		}

		return result;
	}


	// left right this word by amount bits, creating a new Word
	Word rightShift(int amount) {
		Word result = new Word();

		for (int i = 0; i < bits.length - amount; i++) {
			result.bits[i] = bits[i + amount];
		}

		for (int i = amount; i > 0; i--) {
			result.setBit(bits.length - i, new Bit());
		}

		return result;
	}


	// returns a comma separated string t’s and f’s
	public String toString() {
		String result;
		result = bits[31].toString();

		for (int i = 30; i >= 9; i--) {
			result += (", " + bits[i].toString());
		}
		for (int i = 8; i >= 0; i--) {
			result += ("," + bits[i].toString());
		}

		return result;
	}
	
	// Used for testing
	// returns a String of 0s and 1s 
	public String toStringInt() {
	    StringBuilder result = new StringBuilder();
	    
	    for (int i = 31; i >= 0; i--) {
	        if (bits[i].value) {
	            result.append("1");
	        } else {
	            result.append("0");
	        }
	    }

	    return result.toString();
	}


	// returns the value of this word as a long
	long getUnsigned() {
		long result = 0;
		double count = 0;
		for (int i = 0; i < bits.length; i++) {
			if (bits[i].value == true) {
				result += (long) (1 * (Math.pow(2, count)));
			}
			count++;
		}
		return result;

	}


	// returns the value of this word as an int
	int getSigned() {
		int result = 0;
		int count = 0;
		int sign = 1;
		Word copy = new Word();
		
		for(int i = 0; i < bits.length; i++) {
			copy.bits[i] = bits[i];
		}
		// the highest order bit is ON
		if (copy.bits[31].value == true) {
			copy = copy.not();
			copy.increment();
			sign = -1;
		}
		
		// calculate the binary to decimal
		for (int i = 0; i < copy.bits.length; i++) {
			if (copy.bits[i].value == true) {
				result += (int) (1 * (Math.pow(2, count)));
			}
			count++;
		}
		// return positive int
		if(sign == -1) {
			result = result * sign;
		}
		
		return result;

	}

	// copies the values of the bits from another Word into this one
	void copy(Word other) {
		for (int i = 0; i < bits.length; i++) {
			bits[i] = other.bits[i];
		}
	}

	// set the value of the bits of this Word (used for tests)
	void set(int value) {
		int one = 0;

		if (value < 0) {
			value = value * -1;
			// calculates the binary of the given int
			for (int i = 0; i < bits.length; i++) {
				one = value % 2;
				value = (value / 2);
				if (one == 1) {
					bits[i].set(true);
				} else if (one == 0) {
					bits[i].set(false);
				}
			}
			// toggle all the bits
			for (int i = 0; i < bits.length; i++) {
				bits[i].toggle();
			}
			// add one (base 2)
			for (int i = 0; i < bits.length - 1; i++) {
				if (bits[i].value == false) {
					bits[i].set(true);
					break;
				} else if (bits[i].value == true) {
					bits[i].set(false);
				}
			}
			
		} else {
			// calculates the binary of the given positive int
			for (int i = 0; i < bits.length; i++) {
				one = value % 2;
				value = (value / 2);
				if (one == 1) {
					bits[i].set(true);
				} else if (one == 0) {
					bits[i].set(false);
				}
			}
		}

	}
	
	// left shifts the first 5 bits of a word
	public Word leftShift5(int signed) {
		Word result = new Word();

		for (int i = 0; i < 5 - signed; i++) {
			result.bits[i + signed] = bits[i];
		}

		for (int i = 0; i < signed; i++) {
			result.setBit(i, new Bit());
		}

		return result;
	}

	// right shifts the first 5 bits of a word
	public Word rightShift5(int signed) {
		Word result = new Word();

		for (int i = 0; i < 5; i++) {
			result.bits[i] = bits[i + signed];
		}

		for (int i = signed; i > 0; i--) {
			result.setBit(5 - i, new Bit());
		}

		return result;
	}
	
	
	// Used for testing 
	// Makes it easier to see and count each Bit
	public static void numbers() {
		for (int i = 31; i >= 0; i--) {
			System.out.print(i + "|");
		}
		System.out.println();

	}

	// Add
	// adds two words and returns a new Word
	public Word add(Word op2) {
		Word result = new Word();
		Word op1 = new Word();
		for(int i = 0; i < bits.length; i++) {
			op1.bits[i] = bits[i];
		}
		result = result.add2(op1, op2);
		return result;
	}
	
	// Adds 2 words together
	public Word add2(Word op1, Word op2) {
		Word sum = new Word();
		Bit Cin = new Bit();
		for(int i = 0; i < bits.length; i++) {
			
			sum.setBit(i, (op1.bits[i]).xor(op2.bits[i]).xor(Cin));
			if((op1.bits[i].and(Cin)).getValue() == true) {
				Cin.set();
			} else if ((op2.bits[i].and(Cin)).getValue() == true) {
				Cin.set();
			} else if ((op1.bits[i].and(op2.bits[i])).getValue() == true) {
				Cin.set();
			} else {
				Cin.clear();
			}

		}
		
		return sum;
	}

	// Subtract
	// subtracts two words and returns a new Word
	public Word subtract(Word op2) {
		Word result = new Word();
		
		Word op1 = new Word();
		for(int i = 0; i < bits.length; i++) {
			op1.bits[i] = bits[i];
		}			
			// get the complement op2 
			// toggle all bits
			for (int i = 0; i < op2.bits.length; i++) {
				op2.bits[i].toggle();
			}
			// add one (Base 2)
			for (int i = 0; i < op2.bits.length; i++) {
				if (op2.bits[i].value == false) {
					op2.bits[i].set(true);
					break;
				} else if (op2.bits[i].value == true) {
					op2.bits[i].set(false);
				}
			}
		
		result = op1.add(op2);
		return result;
	}
	
	// Add4
	// adds 4 words together and returns a new Word
	public Word add4(Word op1, Word op2, Word op3, Word op4) {
		Word result = new Word();
		int carry = 0;
		int count = 0;
		Bit trueBit = new Bit();
		trueBit.set();
		Bit FalseBit = new Bit();
		
		for(int i = 0; i < bits.length; i++) {
		
						
			if(op1.bits[i].getValue() == true) {
				count++;
			}
			if(op2.bits[i].getValue() == true) {
				count++;
			}
			if(op3.bits[i].getValue() == true) {
				count++;
			}
			if(op4.bits[i].getValue() == true) {
				count++;
			}
			
			if(count % 2 == 0) {
				result.setBit(i, FalseBit);
			} else {
				result.setBit(i, trueBit);
			}
			
			carry = count/2;
			count = carry;

		}
		
		return result;
	}

	// Multiply
	// mutliplies 2 Words together and returns a new Word
	public Word multiply(Word op1, Word op2) {
		List<Word> words = new ArrayList<>();
		List<Word> round1 = new ArrayList<>();
		List<Word> round2 = new ArrayList<>();
		Word result = new Word();

		for(int i = 0; i < bits.length; i++) {
			if(op2.bits[i].value == true) {
				words.add(i, op1.leftShift(i));
			}
			
			if(op2.bits[i].value == false) {
				words.add(this);
			}
		}
		
		for(int i = 0; i < 8; i++) {
			int j = i * 4;
			round1.add(result.add4(words.get(j), words.get(j+1), words.get(j+2), words.get(j+3)));
		}
		
		for(int i = 0; i < 2; i++) {
			int j = i * 4;
			round2.add(result.add4(words.get(j), words.get(j+1), words.get(j+2), words.get(j+3)));
		}
		result = result.add2(round2.get(0), round2.get(1));
		
		return result;
	}
	
	// increments a Word
	public void increment() {
		Bit Cin = new Bit();
		Bit savior = new Bit();
		Cin.set();
			for (int i = 0; i < bits.length; i++) {
				savior = bits[i];
				bits[i] = bits[i].xor(Cin);
				Cin = savior.and(Cin);
			}
	}
	
	// decrements a Word
	public void decrement() {
		 Bit Cin = new Bit();
		 Cin.set(); 
		    for (int i = 0; i < bits.length; i++) {
		        Bit complementedBit = bits[i].not(); 
		        bits[i] = bits[i].xor(Cin);
		        Cin = complementedBit.and(Cin);
		    }
	}

	// Used in Processor
	// copies bits from a Word to another, but only by the amount specified by user 
	void copyBits(Word other, int amount) {
		for (int i = 0; i < amount; i++) {
			bits[i] = other.bits[i];
		}
	}
	
	public boolean lessThan(Word op2) {
		Word result = new Word();
		Word op1 = new Word();
		for (int i = 0; i < bits.length; i++) {
			op1.bits[i] = bits[i];
		}
		System.out.println(op1.getSigned());
		System.out.println(op2.getSigned());
		result = op1.subtract(op2);
		System.out.println(result.getSigned());

		if(result.bits[31].getValue() == true) {
			return true;
		}
		return false;
	}
	
	public boolean greaterThan(Word op2) {
		Word result = new Word();
		Word op1 = new Word();
		for (int i = 0; i < bits.length; i++) {
			op1.bits[i] = bits[i];
		}
		result = op1.subtract(op2);
		if(result.bits[31].getValue() == true) {
			return false;
		}
		return true;
	}
	
	public boolean lessthanOrEqual(Word op2) {
		Word result = new Word();
		Word op1 = new Word();
		for (int i = 0; i < bits.length; i++) {
			op1.bits[i] = bits[i];
		}
		result = op1.subtract(op2);		
		if(result.bits[31].getValue() == true) {
			return true;
		}
		// Check if the result is equal to zero
	    for (int i = 0; i < result.bits.length; i++) {
	        if (result.bits[i].getValue() == true) {
	            return false; 
	        }
	    }

		return true;
	}
	
	public boolean greaterthanOrEqual(Word op2) {
		Word result = new Word();
		Word op1 = new Word();
		for (int i = 0; i < bits.length; i++) {
			op1.bits[i] = bits[i];
		}
		result = op1.subtract(op2);
	   
		for (int i = 0; i < result.bits.length; i++) {
	        if (result.bits[31].getValue() == true) {
	            return false; 
	        }
	        
	        if (result.bits[i].getValue() == true) {
	            return true; 
	        }
	    }
		return true;
	}
	
	public static void main(String[] args) {
		Word test = new Word();
		Word test2 = new Word(); 
		Word test3 = new Word(); 
		Word test4 = new Word(); 
		Word woww = new Word();
		Word sum = new Word();
		Word result = new Word();
		Word empty = new Word();

		
//		woww.set(25);
//		System.out.println(woww);
//		numbers();
//		System.out.println(woww.getSigned());
//		System.out.println();
		
		System.out.println();
		test.set(33);
		System.out.println(test.getSigned());
		System.out.println(test);
		numbers();
		
		System.out.println();
		test2.set(32);
		System.out.println(test2.getSigned());
		System.out.println(test2);
		numbers();
		
		System.out.println();
		System.out.println(test.greaterthanOrEqual(test2));
		
//		System.out.println(test.greaterThan(test2));
//		System.out.println(test.lessThan(test2));

//		test2.set(7);
//		System.out.println(test2);
//		numbers();
//		System.out.println(test2.getSigned());
//		System.out.println();
		
//		test3.set(13);
//		System.out.println(test3);
//		numbers();
//		System.out.println(test3.getSigned());
//		System.out.println();
//		
//		test4.set(13);
//		System.out.println(test4);
//		numbers();
//		System.out.println(test4.getSigned());
//		System.out.println();
//		
//		System.out.println("Result: ");
//		result = sum.add4(test, test2, test3, test4);
//		System.out.println(result);
//		numbers();
//		System.out.println(result.getSigned());
//		System.out.println();
		
//		System.out.println("Result for multiply: ");
//		result = sum.multiply(test, test2);
//		System.out.println(result);
//		numbers();
//		System.out.println(result.getSigned());
//		System.out.println();
		
//		System.out.println("Result for increment: ");
//		test.increment();
//		System.out.println(test);
//		numbers();
//		System.out.println(test.getSigned());

	}







	
	
}