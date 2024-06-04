package Processor;

public class ALU {

	// Memebers 
	public Word op1;
	public Word op2;
	public Word result;
	public Bit bit;
	// Construtor for the ALU
	ALU() {
		op1 = new Word();
		op2 = new Word();
		result = new Word();
		bit = new Bit();
	}

	// function
	public void doOperation(Bit[] operation) {
		// 0000 Equals (eq) (0)
		if(operation[0].value == false && operation[1].value == false
				&& operation[2].value == false && operation[3].value == false) {
					if(op1.equals(op2)) {
						bit.set();
					}
				
				}
		
		// 0001 Not Equals (neg) (1)
		if(operation[0].value == true && operation[1].value == false
				&& operation[2].value == false && operation[3].value == false) {
					if(!op1.equals(op2)) {
						bit.set();
					}
				
				}
		
		// 0010 Less than (lt) (2)
		if(operation[0].value == false && operation[1].value == true
				&& operation[2].value == false && operation[3].value == false) {
					if(op1.lessThan(op2) == true) {
						bit.set();
					}
				
				}
		
		// 0011 Greater than or Equal (ge) (3)
		if(operation[0].value == true && operation[1].value == true
				&& operation[2].value == false && operation[3].value == false) {
					if(op1.greaterthanOrEqual(op2) == true) {
						bit.set();
					}
				
				}	
		
		// 0100 Greater than (gt) (4)
		if(operation[0].value == false && operation[1].value == false
				&& operation[2].value == true && operation[3].value == false) {
					if(op1.greaterThan(op2) == true) {
						bit.set();
					}
				
				}
		
		// 0101 less than or Equal (le) (5)
		if(operation[0].value == true && operation[1].value == false
				&& operation[2].value == true && operation[3].value == false) {
					if(op1.lessthanOrEqual(op2) == true) {
						bit.set();
					}
				
				}
		
		// 0110 (6)
		if(operation[0].value == false && operation[1].value == true
				&& operation[2].value == true && operation[3].value == false) {
				
				}

		// 0111 multiply (7)
		if(operation[0].value == true && operation[1].value == true
				&& operation[2].value == true && operation[3].value == false) {
							
					result.copy(result.multiply(op1, op2));
				
				}
		//1000 and (8)
		if(operation[0].value == false && operation[1].value == false
				&& operation[2].value == false && operation[3].value == true) {
			
					result.copy(op1.and(op2));
					
				}
		// 1001 or  (9)
		if(operation[0].value == true && operation[1].value == false
				&& operation[2].value == false && operation[3].value == true) {
					
					result.copy(op1.or(op2));
					
				}
		// 1010 xor (10)
		if(operation[0].value == false && operation[1].value == true
				&& operation[2].value == false && operation[3].value == true) {
					
					result.copy(op1.xor(op2));
					
				}
		// 1011 not (11)
		if(operation[0].value == true && operation[1].value == true
				&& operation[2].value == false && operation[3].value == true) {
					
					result.copy(op1.not());
					
				}
		// 1100 left shift (12)
		if(operation[0].value == false && operation[1].value == false
				&& operation[2].value == true && operation[3].value == true) {
					
					result.copy(op1.leftShift5(op2.getSigned()));
					
				}
		// 1101 right shift (13)
		if(operation[0].value == true && operation[1].value == true
				&& operation[2].value == false && operation[3].value == true) {
					
					result.copy(op1.rightShift5(op2.getSigned()));

				}
		// 1110 add (14)
		if(operation[0].value == false && operation[1].value == true
				&& operation[2].value == true && operation[3].value == true) {
					
					result.copy((op1.add(op2)));
					
				}
		// 1111 subtract (15)
		if(operation[0].value == true && operation[1].value == true
				&& operation[2].value == true && operation[3].value == true) {		
			
					result.copy((op1.subtract(op2)));

				}
		
	}
	
	
}
