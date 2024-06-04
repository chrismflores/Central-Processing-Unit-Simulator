package Processor;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProcessorTest {

	@Test
	void test() {
		Processor processor = new Processor();
		processor.currentInstruction.set(0);
		processor.fetch();
		processor.decode();
		processor.execute();
		processor.store();
		processor.run();
        assertTrue(processor.halted.value); 
		
		
	}
	@Test
	void test2() {
		Processor processor = new Processor();
		processor.currentInstruction.set(1);
		processor.fetch();
		processor.decode();
		processor.execute();
		processor.store();
		processor.run();
        assertTrue(processor.halted.value); 
		
	}
	
	@Test
	void test3() {
		Processor processor = new Processor();
		processor.currentInstruction.set(4);
		processor.fetch();
		processor.decode();
		processor.execute();
		processor.store();
		processor.run();
        assertTrue(processor.halted.value); 
	}
	
	@Test
	void test4() {
		Processor processor = new Processor();
		processor.currentInstruction.set(2);
		processor.fetch();
		processor.decode();
		processor.execute();
		processor.store();
		processor.run();
        assertTrue(processor.halted.value); 
	}
	
	@Test
	void test5() {
		Processor processor = new Processor();
		processor.currentInstruction.set(10);
		processor.fetch();
		processor.decode();
		processor.execute();
		processor.store();
		processor.run();
        assertTrue(processor.halted.value); 
	}
	
	@Test
	void test6() {
		Processor processor = new Processor();
		processor.currentInstruction.set(0);
		processor.fetch();
		processor.decode();
		processor.execute();
		processor.store();
		processor.run();
        assertTrue(processor.halted.value); 
		
		
	}
	@Test
	void test7() {
		Processor processor = new Processor();
		processor.currentInstruction.set(1);
		processor.fetch();
		processor.decode();
		processor.execute();
		processor.store();
		processor.run();
        assertTrue(processor.halted.value); 
		
	}
	
	@Test
	void test8() {
		Processor processor = new Processor();
		processor.currentInstruction.set(4);
		processor.fetch();
		processor.decode();
		processor.execute();
		processor.store();
		processor.run();
        assertTrue(processor.halted.value); 
	}
	
	@Test
	void test9() {
		Processor processor = new Processor();
		processor.currentInstruction.set(2);
		processor.fetch();
		processor.decode();
		processor.execute();
		processor.store();
		processor.run();
        assertTrue(processor.halted.value); 
	}
	
	@Test
	void test10() {
		Processor processor = new Processor();
		processor.currentInstruction.set(10);
		processor.fetch();
		processor.decode();
		processor.execute();
		processor.store();
		processor.run();
        assertTrue(processor.halted.value); 
	}
	
	@Test
	void test11() {
		Processor processor = new Processor();
		processor.currentInstruction.set(0);
		processor.fetch();
		processor.decode();
		processor.execute();
		processor.store();
		processor.run();
        assertTrue(processor.halted.value); 
		
		
	}
	@Test
	void test12() {
		Processor processor = new Processor();
		processor.currentInstruction.set(1);
		processor.fetch();
		processor.decode();
		processor.execute();
		processor.store();
		processor.run();
        assertTrue(processor.halted.value); 
		
	}
	
	@Test
	void test13() {
		Processor processor = new Processor();
		processor.currentInstruction.set(4);
		processor.fetch();
		processor.decode();
		processor.execute();
		processor.store();
		processor.run();
        assertTrue(processor.halted.value); 
	}
	
	@Test
	void test14() {
		Processor processor = new Processor();
		processor.currentInstruction.set(2);
		processor.fetch();
		processor.decode();
		processor.execute();
		processor.store();
		processor.run();
        assertTrue(processor.halted.value); 
	}
	
	@Test
	void test15() {
		Processor processor = new Processor();
		processor.currentInstruction.set(10);
		processor.fetch();
		processor.decode();
		processor.execute();
		processor.store();
		processor.run();
        assertTrue(processor.halted.value); 
	}
	
	@Test
	void test16() {
		Processor processor = new Processor();
		processor.currentInstruction.set(0);
		processor.fetch();
		processor.decode();
		processor.execute();
		processor.store();
		processor.run();
        assertTrue(processor.halted.value); 
		
		
	}
	@Test
	void test17() {
		Processor processor = new Processor();
		processor.currentInstruction.set(1);
		processor.fetch();
		processor.decode();
		processor.execute();
		processor.store();
		processor.run();
        assertTrue(processor.halted.value); 
		
	}
	
	@Test
	void test18() {
		Processor processor = new Processor();
		processor.currentInstruction.set(4);
		processor.fetch();
		processor.decode();
		processor.execute();
		processor.store();
		processor.run();
        assertTrue(processor.halted.value); 
	}
	
	@Test
	void test19() {
		Processor processor = new Processor();
		processor.currentInstruction.set(2);
		processor.fetch();
		processor.decode();
		processor.execute();
		processor.store();
		processor.run();
        assertTrue(processor.halted.value); 
	}
	
	@Test
	void test20() {
		Processor processor = new Processor();
		processor.currentInstruction.set(10);
		processor.fetch();
		processor.decode();
		processor.execute();
		processor.store();
		processor.run();
        assertTrue(processor.halted.value); 
	}
	
}
