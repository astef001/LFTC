package VirtualMachine;

public class Instr {
	int opCode;
	long i[];
	double d[];
	Instr addr[] = null;
	
	public Instr(int argOpCode, long argI, double argD, Instr argAddr, int usage, long argI2, double argD2, Instr argAddr2){
		this.opCode = argOpCode;
		switch(usage){
		case 1: 
			this.i[0] = argI;
			break;
		case 2:
			this.d[0] = argD;
			break;
		case 3:
			this.addr[0] = argAddr;
			break;
		case 4:
			this.i[0] = argI;
			this.i[1] = argI2;
			break;
		case 5:
			this.d[0] = argD;
			this.d[1] = argD2;
			break;
		case 6:
			this.addr[0] = argAddr;
			this.addr[1] = argAddr2;
			break;
		case -1:
			break;
		default:
			System.out.println("Usage out of range");
			break;
		}
	}
	
	public OPCODES returnOpCode(){
		return OPCODES.valueOf(this.opCode);
	}
}
