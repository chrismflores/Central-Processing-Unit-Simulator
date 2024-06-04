package Processor;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BitTest {

	Bit bit = new Bit();
	Bit other = new Bit();

	@Test
	public void test1() {
		/**************** Bit() Construcotr Tests *********************/
		
		// Creates a Bit object
		// NOTE: Value should be false because the constructor sets new bit objects values to false;
		 Bit testBit = new Bit(); 
	      
	    // Checks if 'testBit' has the correct value (false)
		 System.out.println("-------------------------------------------------------------"); 
		 System.out.println("TESTING if the constructor 'Bit()' creates a new bit object correctly"); 
		 System.out.println("The Value of testBit should be: false"); 
		 System.out.println("testBit Value: " + testBit.getValue()); 
			assertEquals(testBit.getValue(), false);
		 System.out.println("-------------------------------------------------------------"); 
		 
		// Creates a new Bit Object that is a copy of an existing Bit object
		//NOTE: the value of this Bit will be copy the value of the bit passed in
		Bit test2 = new Bit(testBit);
		
		 // Checks if 'testBit' has the correct value (false)
		 System.out.println("-------------------------------------------------------------"); 
		 System.out.println("TESTING if the constructor 'Bit(Bit bit)' creates a copy of a bit object correctly"); 
		 System.out.println("The Value of this should be: false"); 
		 System.out.println("test2 Value: " + test2.getValue()); 
		 	// Unit Test to make sure test2 is false
			assertEquals(test2.getValue(), false);
		 System.out.println("Setting the Bit(testBit) to true... ");
		 testBit.set();
		 Bit test3 = new Bit(testBit);
		 System.out.println("The Value of this should be: true"); 
		 System.out.println("test3 Value: " + test3.getValue()); 
		 	// Unit Test to make sure test3 is true
			assertEquals(test3.getValue(), true);
		 System.out.println("-------------------------------------------------------------"); 
	}
	
	@Test
	public void test2() {
		/**************** set(Boolean) Tests *********************/
		// sets the value of the bit to true
		bit.set(true);
		assertEquals(bit.getValue(), true);

		// sets the value of the bit to false
		bit.set(false);
		assertEquals(bit.getValue(), false);

		/**************** toggle Tests *********************/
		// toggles bit to true
		bit.toggle();
		assertEquals(bit.getValue(), true);

		// toggles bit to false
		bit.toggle();
		assertEquals(bit.getValue(), false);

		/**************** set() Tests *********************/
		// test sets the bit to true
		bit.set();
		assertEquals(bit.getValue(), true);

		/**************** clear() Tests *********************/
		// tests sets the bit to false
		bit.clear();
		assertEquals(bit.getValue(), false);

		/**************** getValue() Tests *********************/
		// tests returns the current value false
		bit.getValue();
		assertEquals(bit.getValue(), false);

		// tests returns the current value true
		bit.set();
		bit.getValue();
		assertEquals(bit.getValue(), true);

		/**************** AND Tests *********************/
		// tests AND with t and f
		bit.set();
		other.clear();
		assertEquals(bit.and(other).getValue(), false);

		// tests AND with f and t
		assertEquals(other.and(bit).getValue(), false);

		other.set();
		// tests AND with t and t
		assertEquals(bit.and(other).getValue(), true);

		other.clear();
		bit.clear();
		// tests AND with f and f
		assertEquals(bit.and(other).getValue(), false);

		/**************** OR Tests *********************/
		bit.set();
		// tests OR with t and f
		assertEquals(bit.or(other).getValue(), true);

		// tests OR with f and t
		assertEquals(other.or(bit).getValue(), true);

		other.set();
		// tests OR with t and t
		assertEquals(bit.or(other).getValue(), true);

		other.clear();
		bit.clear();
		// tests OR with f and f
		assertEquals(bit.or(other).getValue(), false);

		/**************** XOR Tests *********************/
		bit.set();
		other.clear();
		// tests XOR with t and f
		assertEquals(bit.xor(other).getValue(), true);

		// tests XOR with f and t
		assertEquals(other.xor(bit).getValue(), true);

		other.set();
		// tests XOR with t and t
		assertEquals(bit.xor(other).getValue(), false);

		other.clear();
		bit.clear();
		// tests XOR with f and f
		assertEquals(bit.xor(other).getValue(), false);

		/**************** NOT Tests *********************/
		// tests NOT with f
		assertEquals(bit.not().getValue(), true);

		// tests NOT with t
		bit.set();
		assertEquals(bit.not().getValue(), false);

		/*************** toString Tests ******************/
		bit.set(true);
		assertEquals(bit.toString(), "t");
		bit.set(false);
		assertEquals(bit.toString(), "f");

	}

}
