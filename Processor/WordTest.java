package Processor;
import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.Test;

class WordTest {
	
	Word test = new Word();
	Word exam = new Word();
	Word other = new Word();
	Bit mytrue = new Bit();
	Word andTest = new Word();
	Word help = new Word();

	@Test
	void test() {
		
		/************* getBit(int i) Test *****************/
		// gets a new bit that has the same value as i
		assertEquals(test.getBit(0).value, false);
		assertEquals(test.getBit(3).value, false);
		assertEquals(test.getBit(7).value, false);
		assertEquals(test.getBit(15).value, false);
		assertEquals(test.getBit(31).value, false);
		
		// creates a new bit thats true and stores in element 0
		mytrue.set();
		test.setBit(0, mytrue);
		
		// gets a new bit that has the same value as i
		assertEquals(test.getBit(0).value, true);
		
		/********* setBit(int i, Bit Value) Test ***********/
		// set bit i's value
		test.setBit(0, mytrue);
		test.setBit(3, mytrue);
		test.setBit(7, mytrue);
		test.setBit(15, mytrue);
		test.setBit(31, mytrue);

		assertEquals(test.getBit(0).value, true);
		assertEquals(test.getBit(3).value, true);
		assertEquals(test.getBit(7).value, true);
		assertEquals(test.getBit(15).value, true);
		assertEquals(test.getBit(31).value, true);
		
		/********* Word AND(Word other) Test ***********/
		
		other.setBit(0, mytrue);
		other.setBit(3, mytrue);
		other.setBit(7, mytrue);
		other.setBit(15, mytrue);
		other.setBit(31, mytrue);
		
		assertEquals(other.getBit(0).value, true);
		assertEquals(other.getBit(3).value, true);
		assertEquals(other.getBit(7).value, true);
		assertEquals(other.getBit(15).value, true);
		assertEquals(other.getBit(31).value, true);
		
		andTest.setBit(0, mytrue);
		andTest.setBit(3, mytrue);
		andTest.setBit(7, mytrue);
		andTest.setBit(15, mytrue);
		andTest.setBit(31, mytrue);
		
		assertEquals(andTest.getBit(0).value, true);
		assertEquals(andTest.getBit(3).value, true);
		assertEquals(andTest.getBit(7).value, true);
		assertEquals(andTest.getBit(15).value, true);
		assertEquals(andTest.getBit(31).value, true);
		
		help.setBit(0, mytrue);
		help.setBit(3, mytrue);
		help.setBit(7, mytrue);
		help.setBit(15, mytrue);
		help.setBit(31, mytrue);
		
		assertEquals(help.getBit(0).value, true);
		assertEquals(help.getBit(3).value, true);
		assertEquals(help.getBit(7).value, true);
		assertEquals(help.getBit(15).value, true);
		assertEquals(help.getBit(31).value, true);
		
		assertEquals(help.and(andTest).getBit(0).value, true);
		assertEquals(help.and(andTest).getBit(3).value, true);
		assertEquals(help.and(andTest).getBit(7).value, true);
		assertEquals(help.and(andTest).getBit(15).value, true);
		assertEquals(help.and(andTest).getBit(31).value, true);

		/********* Word OR(Word other) Test ***********/
		
		Bit myfalse = new Bit();
		help.setBit(3, myfalse);
		help.setBit(7, myfalse);

		assertEquals(help.or(other).getBit(0).value, true);
		assertEquals(help.or(other).getBit(3).value, true);
		assertEquals(help.or(other).getBit(7).value, true);
		assertEquals(help.or(other).getBit(15).value, true);
		assertEquals(help.or(other).getBit(31).value, true);
		
		/********* Word XOR(Word other) Test ***********/
		
		assertEquals(help.xor(other).getBit(0).value, false);
		assertEquals(help.xor(other).getBit(3).value, true);
		assertEquals(help.xor(other).getBit(7).value, true);
		assertEquals(help.xor(other).getBit(15).value, false);
		assertEquals(help.xor(other).getBit(31).value, false);
		
		
		/************ Word NOT() Test *************/
		
		Word notTest = new Word();
		notTest = test.not();
		
		assertEquals(notTest.getBit(0).value, false);
		assertEquals(notTest.getBit(1).value, true);
		assertEquals(notTest.getBit(2).value, true);
		assertEquals(notTest.getBit(3).value, false);
		assertEquals(notTest.getBit(4).value, true);
		assertEquals(notTest.getBit(5).value, true);
		assertEquals(notTest.getBit(6).value, true);
		assertEquals(notTest.getBit(7).value, false);
		assertEquals(notTest.getBit(20).value, true);
		assertEquals(notTest.getBit(25).value, true);
		assertEquals(notTest.getBit(30).value, true);
		assertEquals(notTest.getBit(31).value, false);
		
		
		/********* Word rightShift() Test ***********/
		
		Word test1 = new Word();
		test1 = test.rightShift(5);
		
		assertEquals(test1.getBit(0).value, false);
		assertEquals(test1.getBit(1).value, false);
		assertEquals(test1.getBit(2).value, true);
		assertEquals(test1.getBit(3).value, false);
		assertEquals(test1.getBit(4).value, false);
		assertEquals(test1.getBit(5).value, false);
		assertEquals(test1.getBit(6).value, false);
		assertEquals(test1.getBit(7).value, false);
		assertEquals(test1.getBit(20).value, false);
		assertEquals(test1.getBit(25).value, false);
		assertEquals(test1.getBit(30).value, false);
		assertEquals(test1.getBit(31).value, false);

		/********* Word leftShift() Test ***********/
		
		Word test2 = new Word();
		test2 = test.leftShift(5);
		
		assertEquals(test2.getBit(0).value, false);
		assertEquals(test2.getBit(1).value, false);
		assertEquals(test2.getBit(2).value, false);
		assertEquals(test2.getBit(3).value, false);
		assertEquals(test2.getBit(4).value, false);
		assertEquals(test2.getBit(5).value, true);
		assertEquals(test2.getBit(6).value, false);
		assertEquals(test2.getBit(7).value, false);
		assertEquals(test2.getBit(20).value, true);
		assertEquals(test2.getBit(25).value, false);
		assertEquals(test2.getBit(30).value, false);
		assertEquals(test2.getBit(31).value, false);
		
		
		/********* String toString() Test ***********/
		
		Word testString = new Word();
		
		testString.setBit(0, mytrue);
		testString.setBit(1, mytrue);
		testString.setBit(2, mytrue);
		testString.setBit(3, mytrue);
		testString.setBit(7, mytrue);
		testString.setBit(15, mytrue);

		assertEquals(testString.toString(), "f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, f, f, f, f, f,f,t,f,f,f,t,t,t,t");

		
		/********* long getUnsigned() Test ***********/
		
		Word testUnsigned = new Word();
		testUnsigned = testString;
		int ans = 32911;
		assertEquals(testUnsigned.getUnsigned(), ans);

		/********* int getSigned() Test ***********/
		
		Word testSigned = new Word();
		testSigned = testString;
		int ans1 = 32911;
		assertEquals(testSigned.getSigned(), ans1);
		
		/********* copy(Word other) Test ***********/
		
		Word done = new Word();
		Word done2 = new Word();
		testSigned.copy(done);
		assertEquals(done.getBit(0).value,done2.getBit(0).value);

		
		/********* set(int value) Test ***********/
		test.set(-1);
		System.out.println(test);
		System.out.println(test.getSigned());
		System.out.println();
		
		test.set(0);
		System.out.println(test);
		System.out.println(test.getSigned());
		System.out.println();
		
		test.set(1);
		System.out.println(test);
		System.out.println(test.getSigned());
		System.out.println();
		
		test.set(10);
		System.out.println(test);
		System.out.println(test.getSigned());
		System.out.println();
		
		test.set(-10);
		System.out.println(test);
		System.out.println(test.getSigned());
		System.out.println();
		
	}


}
