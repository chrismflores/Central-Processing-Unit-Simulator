package Processor;

public class Bit {

	boolean value;

	// Construtor that initializes a Bit object with a default value of false
	Bit() {
		this.value = false;
	}

	// Construtor that creates a copy of an existing Bit object (not needed but helpful)
	Bit(Bit bit) {
		this.value = bit.value;
	}
	
	// sets the value of the bit
	void set(Boolean value) {
		this.value = value;
	}

	// changes the value from true to false or false to true
	void toggle() {
		if (value == false) {
			value = true;
		} else if (value == true) {
			value = false;
		}

	}

	// sets the bit to true
	void set() {
		value = true;
	}

	// sets the bit to false
	void clear() {
		value = false;
	}

	// returns the current value
	Boolean getValue() {
		return value;
	}

	// performs and on two bits and returns a new bit set to the result
	Bit and(Bit other) {
		Bit result = new Bit();
		if (value == true) {
			if (other.value == true) {
				result.set();
			}
		}

		return result;
	}

	// performs or on two bits and returns a new bit set to the result
	Bit or(Bit other) {
		// 1 and 1
		Bit result = new Bit();
		if (value == true) {
			result.set();
		} else if (other.value == true) {
			result.set();
		}
		return result;
	}

	// performs xor on two bits and returns a new bit set to the result
	Bit xor(Bit other) {
		Bit result = new Bit();

		if (value == true) {
			if (other.value == false) {
				result.set();
			}
		} else if (other.value == true) {
			result.set();
		}

		return result;
	}

	// performs not on the existing bit, returning the result as a new bit
	Bit not() {
		// result by default is false
		Bit result = new Bit();
		if (value == false) {
			result.set();
		}

		return result;
	}

	// returns “t” or “f”
	public String toString() {
		if (value == true) {
			return "t";
		} else
			return "f";

	}
	

}
