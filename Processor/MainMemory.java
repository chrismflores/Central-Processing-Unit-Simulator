package Processor;

public class MainMemory {

	// static array to hold memory words
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

	// writes a word to memory
	public static void write(Word address, Word value) {
		int addy = (int) address.getUnsigned();

		// if memory location is empty initialize it
		if (memory[addy] == null) {
			memory[addy] = new Word();
		}

		// copies value to memory to result
		memory[addy].copy(value);

	}

	// loads data to memory
	public static void load(String[] data) {

		// Check if memory is constructed
		Bit bitTrue = new Bit();
		bitTrue.set();
		Bit bitFalse = new Bit();
		Word word = new Word();
		// Iterates through data
		for (int j = 0; j < data.length; j++) {
			// if memory location is empty initialize it
			if (memory[j] == null) {
				memory[j] = new Word();
			}
			// makes the word
			for (int i = 31; i >= 0; i--) {
				char bitChar = data[j].charAt(i);
				if (bitChar == '1') {
					word.setBit(31 - i, bitTrue);
				} else {
					word.setBit(31 - i, bitFalse);
				}

			}
			// adds the word to memory
			memory[j].copy(word);
		}

	}
	
	

}






