package Processor;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ALUTest {

	@Test
	void test() {
		// Creates 2 new Bits, One false, One true
		Bit bitFalse = new Bit();
		Bit bitTrue = new Bit();
		bitTrue.set();
		
		// Creates an Array of Bits
		Word test = new Word();
		
		// Creates Words
		Word op1 = new Word();
		Word op2 = new Word();
		
		// sets bits to 7 0000...0111
		test.set(15);

		// creates an ALU object
		ALU alu = new ALU();
		
		// 10 
		op1.set(10);
		System.out.println(op1.getSigned());
		System.out.println(op1);
		
		System.out.println();
		
		// 5
		op2.set(5);
		System.out.println(op2.getSigned());
		System.out.println(op2);
		
		System.out.println();
		
		
		alu.doOperation(test.bits);
		System.out.println(alu.result.getSigned());
		System.out.println(alu.result);
		
	}

}
