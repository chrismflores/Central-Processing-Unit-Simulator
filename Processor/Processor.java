package Processor;

public class Processor {

	private Word PC = new Word();
	private Word SP = new Word();
	public Word currentInstruction;
	Bit halted = new Bit();
	Word[] registers = new Word[32];
    private int clockCycle = 0;
	
	Word opCode = new Word();
	Word Rd = new Word();
	Word function = new Word();
	Word Rs2 = new Word();
	Word Rs1 = new Word();
	Word immediate = new Word();
	
	ALU alu = new ALU();
	Processor destination;

	public Processor() {
		   currentInstruction = new Word();
		   PC.set(0); // PC starts at address 0
	       SP.set(1024); // Stack Pointer starts at address 1024
	   }
	   
	   
	// run  
	void run() {
		while(halted.value == false) {
			fetch();
			decode();
			execute();
			store();
		}
	}
	
	// decode
	void decode() {
		// No R (00)
		if(currentInstruction.bits[0].value == false && currentInstruction.bits[1].value == false) {
			//gets the opcode (5)
			opCode.copyBits(currentInstruction, 5);
			currentInstruction = currentInstruction.rightShift(5);
			
			//gets the immediate (27)
			immediate.copyBits(currentInstruction, 27);
			currentInstruction = currentInstruction.rightShift(27);
			
		}
		// Dest Only (01)
		if(currentInstruction.bits[0].value == true && currentInstruction.bits[1].value == false) {
			//gets the opcode (5)
			opCode.copyBits(currentInstruction, 5);
			currentInstruction = currentInstruction.rightShift(5);
			
			//gets the Rd (5)
			Rd.copyBits(currentInstruction, 5);
			currentInstruction = currentInstruction.rightShift(5);
			
			//gets the function (4)
			function.copyBits(currentInstruction, 4);
			currentInstruction = currentInstruction.rightShift(4);
			
			//gets the Immediate (18)
			immediate.copyBits(currentInstruction, 18);
			currentInstruction = currentInstruction.rightShift(18);
			
		}
		// 2R (10)
		if(currentInstruction.bits[0].value == false && currentInstruction.bits[1].value == true) {
			//gets the opcode (5)
			opCode.copyBits(currentInstruction, 5);
			currentInstruction = currentInstruction.rightShift(5);
			
			//gets the Rd (5)
			Rd.copyBits(currentInstruction, 5);
			currentInstruction = currentInstruction.rightShift(5);
			
			//gets the function (4)
			function.copyBits(currentInstruction, 4);
			currentInstruction = currentInstruction.rightShift(4);
			
			//gets the Rs (5)
			Rs2.copyBits(currentInstruction, 5);
			currentInstruction = currentInstruction.rightShift(5);
			
			//gets the Immediate (13)
			immediate.copyBits(currentInstruction, 13);
			currentInstruction = currentInstruction.rightShift(13);
			
		}
		// 3R (11)
		if(currentInstruction.bits[0].value == true && currentInstruction.bits[1].value == true) {
			//gets the opcode (5)
			opCode.copyBits(currentInstruction, 5);
			currentInstruction = currentInstruction.rightShift(5);
			
			//gets the Rd (5)
			Rd.copyBits(currentInstruction, 5);
			currentInstruction = currentInstruction.rightShift(5);
			
			//gets the function (4)
			function.copyBits(currentInstruction, 4);
			currentInstruction = currentInstruction.rightShift(4);
			
			//gets the Rs2 (5)
			Rs2.copyBits(currentInstruction, 5);
			currentInstruction = currentInstruction.rightShift(5);
			
			//gets the Rs1 (5)
			Rs1.copyBits(currentInstruction, 5);
			currentInstruction = currentInstruction.rightShift(5);
			
			//gets the Immediate (8)
			immediate.copyBits(currentInstruction, 8);
			currentInstruction = currentInstruction.rightShift(8);
		}	
	}
	
	// execute 
	void execute() {		
		// execute Math (000**)
		if(currentInstruction.bits[2].value == false && currentInstruction.bits[3].value == false 
				&& currentInstruction.bits[4].value == false) {
			
			// No R (00)
			if(currentInstruction.bits[0].value == false && currentInstruction.bits[1].value == false) {
				return;
			}
			
			// Dest Only (01)
			if(currentInstruction.bits[0].value == true && currentInstruction.bits[1].value == false) {
				/** Remember to change the index we cant use get Signed or unsigned **/
				registers[destination.bitsToNum(Rd)].copy(immediate);
			}
			
			// 2R (10)
			if(currentInstruction.bits[0].value == false && currentInstruction.bits[1].value == true) {
				alu.op1.copy(Rd);
				alu.op2.copy(Rs2);
				alu.doOperation(function.bits);
			}
			
			// 3R (11)
			if(currentInstruction.bits[0].value == true && currentInstruction.bits[1].value == true) {
				alu.op1.copy(Rs1);
				alu.op2.copy(Rs2);
				alu.doOperation(function.bits);			
			}
			
		}
		
		// execute Branch (001**)
		if(currentInstruction.bits[2].value == true && currentInstruction.bits[3].value == false 
						&& currentInstruction.bits[4].value == false) {
			
			// No R (00)
			if(currentInstruction.bits[0].value == false && currentInstruction.bits[1].value == false) {
				System.out.println("Executing Branch (00)");
				
			}
			// Dest Only (01)
			if(currentInstruction.bits[0].value == true && currentInstruction.bits[1].value == false) {
				System.out.println("Executing Branch (01)");
			}
			// 2R (10)
			if(currentInstruction.bits[0].value == false && currentInstruction.bits[1].value == true) {
				alu.op1.copy(Rs2);
				alu.op2.copy(Rd);
				alu.doOperation(function.bits);	
			}
			// 3R (11)
			if(currentInstruction.bits[0].value == true && currentInstruction.bits[1].value == true) {
				alu.op1.copy(Rs1);
				alu.op2.copy(Rs2);
				alu.doOperation(function.bits);
			}	
		}
		
		// execute Call (010**)
		if(currentInstruction.bits[2].value == false && currentInstruction.bits[3].value == true 
				&& currentInstruction.bits[4].value == false) {
			
			// No R (00)
			if(currentInstruction.bits[0].value == false && currentInstruction.bits[1].value == false) {
				System.out.println("Executing Call (00)");
			}
			// Dest Only (01)
			if(currentInstruction.bits[0].value == true && currentInstruction.bits[1].value == false) {
				System.out.println("Executing Call (01)");
			}
			// 2R (10)
			if(currentInstruction.bits[0].value == false && currentInstruction.bits[1].value == true) {
				alu.op1.copy(Rs2);
				alu.op2.copy(Rd);
				alu.doOperation(function.bits);	
			}
			// 3R (11)
			if(currentInstruction.bits[0].value == true && currentInstruction.bits[1].value == true) {
				alu.op1.copy(Rs1);
				alu.op2.copy(Rs2);
				alu.doOperation(function.bits);
			}	
		}
		
		// execute Push (011)
		if(currentInstruction.bits[2].value == true && currentInstruction.bits[3].value == true 
				&& currentInstruction.bits[4].value == false) {
			
			// No R (00)
			if(currentInstruction.bits[0].value == false && currentInstruction.bits[1].value == false) {
				System.out.println("Executing Push (00) UNUSED");
			}
			// Dest Only (01)
			if(currentInstruction.bits[0].value == true && currentInstruction.bits[1].value == false) {
				System.out.println("Executing Push (01)");
				alu.op1.copy(Rd);
				alu.op2.copy(immediate);
				alu.doOperation(function.bits);
			}
			// 2R (10)
			if(currentInstruction.bits[0].value == false && currentInstruction.bits[1].value == true) {
				alu.op1.copy(Rd);
				alu.op2.copy(Rs2);
				alu.doOperation(function.bits);	
			}
			// 3R (11)
			if(currentInstruction.bits[0].value == true && currentInstruction.bits[1].value == true) {
				alu.op1.copy(Rs1);
				alu.op2.copy(Rs2);
				alu.doOperation(function.bits);
			}			
		}
		
		// execute Load (100)
		if(currentInstruction.bits[2].value == false && currentInstruction.bits[3].value == false 
				&& currentInstruction.bits[4].value == true) {
			
			// No R (00)
			if(currentInstruction.bits[0].value == false && currentInstruction.bits[1].value == false) {
				System.out.println("Executing Load (00)");
			}
			// Dest Only (01)
			if(currentInstruction.bits[0].value == true && currentInstruction.bits[1].value == false) {
				
			}
			// 2R (10)
			if(currentInstruction.bits[0].value == false && currentInstruction.bits[1].value == true) {
			
			}
			// 3R (11)
			if(currentInstruction.bits[0].value == true && currentInstruction.bits[1].value == true) {
				
			}			
		
		}
		
		// execute Store (101)
		if(currentInstruction.bits[2].value == true && currentInstruction.bits[3].value == false 
				&& currentInstruction.bits[4].value == true) {
		
		}
		
		// execute Pop/interrupt (110)
		if(currentInstruction.bits[2].value == false && currentInstruction.bits[3].value == true 
				&& currentInstruction.bits[4].value == true) {
		
		}
		
				
	}
	
	// store
	void store() {		
		// Math (000**)
		if(currentInstruction.bits[2].value == false && currentInstruction.bits[3].value == false 
				&& currentInstruction.bits[4].value == false) {
			
			// No R (00)
			if(currentInstruction.bits[0].value == false && currentInstruction.bits[1].value == false) {
				halted.value = true;
			}
			// Dest Only (01)
			if(currentInstruction.bits[0].value == true && currentInstruction.bits[1].value == false) {
				registers[destination.bitsToNum(Rd)].copy(immediate);
			}
			// 2R (10)
			if(currentInstruction.bits[0].value == false && currentInstruction.bits[1].value == true) {
				registers[destination.bitsToNum(Rd)].copy(alu.result);
			}
			// 3R (11)
			if(currentInstruction.bits[0].value == true && currentInstruction.bits[1].value == true) {
				registers[Rd.getSigned()].copy(alu.result);
			}				
			
		}
		
		// Branch (001**)
		if(currentInstruction.bits[2].value == true && currentInstruction.bits[3].value == false 
						&& currentInstruction.bits[4].value == false) {
			
			// No R (00)
			if(currentInstruction.bits[0].value == false && currentInstruction.bits[1].value == false) {
				PC.copy(immediate);
			}
			// Dest Only (01)
			if(currentInstruction.bits[0].value == true && currentInstruction.bits[1].value == false) {
				PC.copy(PC.add(immediate));
			}
			// 2R (10)
			if(currentInstruction.bits[0].value == false && currentInstruction.bits[1].value == true) {
				if(alu.bit.getValue() == true) {
					PC.copy(PC.add(immediate));
				} 
			}
			// 3R (11)
			if(currentInstruction.bits[0].value == true && currentInstruction.bits[1].value == true) {
				if(alu.bit.getValue() == true) {
					PC.copy(PC.add(immediate));
				} 
			}
		}
		
		// Call (010)
		if(currentInstruction.bits[2].value == false && currentInstruction.bits[3].value == true 
				&& currentInstruction.bits[4].value == false) {
			
			// No R (00)
			if(currentInstruction.bits[0].value == false && currentInstruction.bits[1].value == false) {
				SP.decrement();
				MainMemory.write(SP, PC);
				PC.copy(immediate);
			}
			// Dest Only (01)
			if(currentInstruction.bits[0].value == true && currentInstruction.bits[1].value == false) {
				SP.decrement();
				MainMemory.write(SP, PC);
				PC.copy(Rd.add(immediate));
			}
			// 2R (10)
			if(currentInstruction.bits[0].value == false && currentInstruction.bits[1].value == true) {
				if(alu.bit.getValue() == true) {
					SP.decrement();
					MainMemory.write(SP, PC);
					PC.copy(PC.add(immediate));
				} 
			}
			// 3R (11)
			if(currentInstruction.bits[0].value == true && currentInstruction.bits[1].value == true) {
				if(alu.bit.getValue() == true) {
					SP.decrement();
					MainMemory.write(SP, PC);
					PC.copy(Rd.add(immediate));
				} 
			}
		}
		
		// Push (011)
		if(currentInstruction.bits[2].value == true && currentInstruction.bits[3].value == true 
				&& currentInstruction.bits[4].value == false) {
			// No R (00)
			if(currentInstruction.bits[0].value == false && currentInstruction.bits[1].value == false) {
				System.out.println("Store Push (01) Unused");
			}
			// Dest Only (01)
			if(currentInstruction.bits[0].value == true && currentInstruction.bits[1].value == false) {
				SP.decrement();
				MainMemory.write(SP, alu.result);
			}
			// 2R (10)
			if(currentInstruction.bits[0].value == false && currentInstruction.bits[1].value == true) {
				SP.decrement();
				MainMemory.write(SP, alu.result);
			}
			// 3R (11)
			if(currentInstruction.bits[0].value == true && currentInstruction.bits[1].value == true) {
				SP.decrement();
				MainMemory.write(SP, alu.result);
			}		
		}
		
		// Load (100)
		if(currentInstruction.bits[2].value == false && currentInstruction.bits[3].value == false 
				&& currentInstruction.bits[4].value == true) {
			// No R (00)
			if(currentInstruction.bits[0].value == false && currentInstruction.bits[1].value == false) {
				PC.copy(MainMemory.read(SP));
				SP.increment();
			}
			// Dest Only (01)
			if(currentInstruction.bits[0].value == true && currentInstruction.bits[1].value == false) {
				Rd.copy(MainMemory.read(Rd.add(immediate)));
			}
			// 2R (10)
			if(currentInstruction.bits[0].value == false && currentInstruction.bits[1].value == true) {
				Rd.copy(MainMemory.read(Rs2).add(immediate));
			}
			// 3R (11)
			if(currentInstruction.bits[0].value == true && currentInstruction.bits[1].value == true) {
				Rd.copy(MainMemory.read(Rs1).add(Rs2));
			}		
		}
		
		// Store (101)
		if(currentInstruction.bits[2].value == true && currentInstruction.bits[3].value == false 
				&& currentInstruction.bits[4].value == true) {
			// No R (00)
			if(currentInstruction.bits[0].value == false && currentInstruction.bits[1].value == false) {
			System.out.println("UNUSED");
			}
			// Dest Only (01)
			if(currentInstruction.bits[0].value == true && currentInstruction.bits[1].value == false) {
				MainMemory.write(Rd, immediate);
			}
			// 2R (10)
			if(currentInstruction.bits[0].value == false && currentInstruction.bits[1].value == true) {
				MainMemory.write(Rd.add(immediate), Rs2);
			}
			// 3R (11)
			if(currentInstruction.bits[0].value == true && currentInstruction.bits[1].value == true) {
				MainMemory.write(Rd.add(Rs1), Rs2);
			}		
		}
		
		// Pop/interrupt (110)
		if(currentInstruction.bits[2].value == false && currentInstruction.bits[3].value == true 
				&& currentInstruction.bits[4].value == true) {	
			// No R (00)
			if(currentInstruction.bits[0].value == false && currentInstruction.bits[1].value == false) {
				System.out.println("Not implement yet");
			}
			// Dest Only (01)
			if(currentInstruction.bits[0].value == true && currentInstruction.bits[1].value == false) {
				Rd.copy(MainMemory.read(SP));
				SP.increment();
			}
			// 2R (10)
			if(currentInstruction.bits[0].value == false && currentInstruction.bits[1].value == true) {
				Rd.copy(MainMemory.read(SP.subtract(Rs2.add(immediate))));
			}
			
			// 3R (11)
			if(currentInstruction.bits[0].value == true && currentInstruction.bits[1].value == true) {
				Rd.copy(MainMemory.read(SP.subtract(Rs1.add(Rs2))));
			}		
		
		}		
		
	}
	
	// the fetch
	void fetch() {
		// get the current instruction from PC (memory)
		currentInstruction.copy(MainMemory.read(PC));
		//increment PC
		PC.increment();
	}
	
	int bitsToNum(Word rd) {
	    String result = rd.toStringInt();
	    // :(
	    // Check specific binary patterns and return integers
	    if (result.equals("00001")) {
	        return 1;
	   
	    } else if (result.equals("00010")) {
	        return 2;
	    
	    } else if (result.equals("00011")) {
	        return 3;
	   
	    } else if (result.equals("00100")) {
	        return 4;
	   
	    } else if (result.equals("00101")) {
	        return 5;
	   
	    } else if (result.equals("00110")) {
	        return 6;
	   
	    } else if (result.equals("00111")) {
	        return 7;
	   
	    } else if (result.equals("01000")) {
	        return 8;
	   
	    } else if (result.equals("01001")) {
	        return 9;
	  
	    } else if (result.equals("01010")) {
	        return 10;
	  
	    } else if (result.equals("01011")) {
	        return 11;
	   
	    } else if (result.equals("01100")) {
	        return 12;
	    
	    } else if (result.equals("01101")) {
	        return 13;
	    
	    } else if (result.equals("01110")) {
	        return 14;
	    
	    } else if (result.equals("01111")) {
	        return 15;
	    
	    } else if (result.equals("10000")) {
	        return 16;
	    
	    } else if (result.equals("10001")) {
	        return 17;
	    
	    } else if (result.equals("10010")) {
	        return 18;
	    
	    } else if (result.equals("10011")) {
	        return 19;
	    
	    } else if (result.equals("10100")) {
	        return 20;
	    
	    } else if (result.equals("10101")) {
	        return 21;
	    
	    } else if (result.equals("10110")) {
	        return 22;
	    
	    } else if (result.equals("10111")) {
	        return 23;
	    
	    } else if (result.equals("11000")) {
	        return 24;
	    
	    } else if (result.equals("11001")) {
	        return 25;
	    
	    } else if (result.equals("11010")) {
	        return 26;
	    
	    } else if (result.equals("11011")) {
	        return 27;
	    
	    } else if (result.equals("11100")) {
	        return 28;
	    
	    } else if (result.equals("11101")) {
	        return 29;
	    
	    } else if (result.equals("11110")) {
	        return 30;
	    
	    } else if (result.equals("11111")) {
	        return 31;
	    }

	    return -1; 
	}

	
	
	
	
	
	
	
	
	
	
}
