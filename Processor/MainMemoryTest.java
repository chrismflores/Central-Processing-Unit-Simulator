package Processor;
import org.junit.jupiter.api.Test;

class MainMemoryTest {

	@Test
	void test() {	
		// Creates an array of strings with length 5
		String[] arrayOfStrings = new String[5]; 
		arrayOfStrings[0] = "01101010101010101010010101010101";
		arrayOfStrings[1] = "01101010101010101010010101010101";
		arrayOfStrings[2] = "01101010101010101010010101010101";
		arrayOfStrings[3] = "01101010101010101010010101010101";
		arrayOfStrings[4] = "01101010101010101010010101010101";
		
		// test for load
		MainMemory.load(arrayOfStrings);
		
		Word test = new Word();
		Word test2 = new Word();
		
		System.out.println("Result for 0: ");
		test.set(0);
		System.out.println(test);
		System.out.println(test.getSigned());
		
		// test for read
		System.out.println("\nResult for read test");
		System.out.println(MainMemory.read(test));
		System.out.println(arrayOfStrings[0]);
		
		//test for write 
		// note test2 is only zeros
		System.out.println("\nResult for write test");
		System.out.println("test is set to 10");
		test.set(10);
		System.out.println(test);
		Word.numbers();
		
		System.out.println("\ntest 2: ");
		// test 2 is 0
		System.out.println(test2);
		Word.numbers();
		
		System.out.println();
		
		MainMemory.write(test2, test);
		Word read = MainMemory.read(test);
		
		System.out.println("\ntest after write with test2: ");
		System.out.println(read);
				
		
		
		System.out.println("\nIncrement Tests");
		//sets the test to 16 and uses other methods for testing purposes
		test.set(16);
		System.out.println(test);
		Word.numbers();
		System.out.println(test.getSigned());
		System.out.println();
		
		System.out.println("Result for increment: ");
		//increment test
		test.increment();
		System.out.println(test);
		Word.numbers();
		System.out.println(test.getSigned());
		System.out.println();
				
		System.out.println("\nIncrement Tests");
		//sets the test to 16 and uses other methods for testing purposes
		test.set(0);
		System.out.println(test);
		Word.numbers();
		System.out.println(test.getSigned());
		System.out.println();
		
		System.out.println("Result for increment: ");
		//increment test
		test.increment();
		System.out.println(test);
		Word.numbers();
		System.out.println(test.getSigned());
		System.out.println();
		
		System.out.println("\nIncrement Tests");
		//sets the test to 16 and uses other methods for testing purposes
		test.set(10);
		System.out.println(test);
		Word.numbers();
		System.out.println(test.getSigned());
		System.out.println();
		
		System.out.println("Result for increment: ");
		//increment test
		test.increment();
		System.out.println(test);
		Word.numbers();
		System.out.println(test.getSigned());
		System.out.println();
		
		Word mathTest1 = new Word();
		mathTest1.set(81953);
		
		Word mathTest2 = new Word();
		mathTest2.set(555075);
		
		Word mathTest3 = new Word();
		mathTest3.set(47170);
		
		Word mathTest4 = new Word();
		mathTest4.set(1079395);
		
		String[] array = new String[5]; 
		array[0] = mathTest1.toStringInt();
		
		System.out.println(mathTest1);
		System.out.println(mathTest1.toStringInt());
		
		Processor p = new Processor();
		
		p.run();
	}


}
