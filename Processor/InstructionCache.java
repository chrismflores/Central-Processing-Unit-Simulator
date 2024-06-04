package Processor;

public class InstructionCache {

	private static Word[] memory = new Word[1024];

	// read a word from memory
	public static Word read(Word address) {
		int addy = (int) address.getUnsigned();
		Word result = new Word();

		// if memory location is empty initialize it
		if (memory[addy] == null) {
			memory[addy] = new Word();
		}
		// copies memory to result
		result.copy(memory[addy]);

		return result;

	}
	
}
