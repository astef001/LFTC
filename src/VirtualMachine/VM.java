package VirtualMachine;

import java.nio.ByteBuffer;
import java.util.LinkedList;


public class VM {
	 private ByteBuffer Stack;
	 private LinkedList<Instr> instructions = null;
	 private final int STACK_SIZE = 3 * 1024;
	 int SP;
	 long IP;
	 int stackAfter; 
	 public VM(){
		 this.SP = 0;
		 this.IP = 0;
		 this.instructions = new LinkedList<Instr>();
	 }
	 
	 void pushd(double d){
		 if(SP + 8 > stackAfter) {
			 System.out.println("Not enought space in Stack");
			 return;
		 }
		 Stack.putDouble(d);
		 SP+=8;
	 }
	 
	 double popd(){
		 SP-=8;
		 if(SP < 0){
			 System.out.println("Not enough stack bytes for pop");
			 return 0.0;
		 }
		 return Stack.getDouble();
	 }
	
	 void pusha(int a){
		 if(SP + 4 > stackAfter) {
			 System.out.println("Not enought space in Stack");
			 return;
		 }
		 Stack.putInt(a);
		 SP+=4;
	 }
	 
	 int popa(){
		 SP-=4;
		 if(SP < 0){
			 System.out.println("Not enough stack bytes for pop");
			 return 0;
		 }
		 return Stack.getInt();
	 }
	 
	 void pushc(char c){
		 if (SP + 1 > this.stackAfter){
			 System.out.println("Not enought space in Stack");
			 return;
		 }
		 Stack.putChar(c);
		 SP += 1;
	 }
	 
	 char popc(){
		 SP -= 1;
		 if(SP < 0){
			 System.out.println("Not enough stack bytes for pop");
			 return 0;
		 }
		 return Stack.getChar();
	 }
	 
	 
	 Instr createInstr(int opcode){
		 return (new Instr(opcode, 0, 0, null, -1, 0, 0, null));
	 }
	 
	 void insertInstrAfter(Instr after, Instr i){
		 this.instructions.add((this.instructions.indexOf(after) + 1), i);
	 }
	 
	 void addInstr(int opcode){
		 Instr i = createInstr(opcode);
		 this.instructions.addLast(i);
	 }
	 
	 void addInstrAfter(Instr after, int opcode){
		 Instr i = createInstr(opcode);
		 insertInstrAfter(after, i);
	 }
	 
	 void addInstrA(int opcode, Instr addr ){
		 Instr i = new Instr(opcode, 0, 0, addr, 3, 0, 0, null);
		 this.instructions.addLast(i);
	 }
	 
	 void addInstrI(int opcode, int val){
		 Instr i = new Instr(opcode, val, 0, null, 1, 0, 0, null);
		 this.instructions.addLast(i);
	 }
	 
	 void addInstrII(int opcode, int val1, int val2){
		 Instr i = new Instr(opcode, val1, 0, null, 4, val2, 0, null);
		 this.instructions.addLast(i);
	 }
	 
	 void deleteInstructionsAfter(Instr start){
		 this.instructions.removeIf((Instr after) -> {return this.instructions.indexOf(after) > this.instructions.indexOf(start); } );
	 }
	 
	 void ADD_C(){
		 char a = this.popc(), b = this.popc();
		 pushc((char)(a+b));
	 }
	 
	 void ADD_D(){
		 double a = this.popd(), b = this.popd();
		 pushd(a + b);
	 }
	 
	 void ADD_I(){
		 int a = this.popa(), b = this.popa();
		 pusha(a + b);
	 }
	 
	 void AND_A(){
		 int a = this.popa(), b = this.popa();
		 pushc((a!=0) && (b!=0) == true ? (char)1 : (char)0);
	 }
	 
	 void AND_C(){
		 char a = this.popc(), b = this.popc();
		 pushc((a!=0) && (b!=0) == true ? (char)1 : (char)0);
	 }
	 
	 void AND_D(){
		 double a = this.popa(), b = this.popa();
		 pushc((a!=0) && (b!=0) == true ? (char)1 : (char)0);
	 }
	 
	 void AND_I(){
		 int a = this.popa(), b = this.popa();
		 pushc((a!=0) && (b!=0) == true ? (char)1 : (char)0);
	 }
	 
	 void CALL(Instr addr){
		 pusha(addr.opCode);
		 this.IP = this.instructions.indexOf(addr);
	 }
	 
	 void CAST_C_D(){
		 char a = popc();
		 pushd((double)a);
	 }
	 
	 void CAST_C_I(){
		 char a = popc();
		 pusha((int)a);
	 }
	 
	 void CAST_D_C(){
		 double a = popd();
		 pushc((char)a);
	 }
	 
	 void CAST_D_I(){
		 double a = popd();
		 pusha((int)a);
	 }
	 
	 void CAST_I_C(){
		 int a = popa();
		 pusha((char)a);
	 }
	 
	 void CAST_I_D(){
		 int a = popa();
		 pushd((double)a);
	 }
	 
	 void DIV_C(){
		 char a = popc(), b = popc();
		 pushc((char)(a/b));
	 }
	 
	 void DIV_D(){
		 double a = popd(), b = popd();
		 pushd(a/b);
	 }
	 
	 void DIV_I(){
		 int a = popa(), b = popa();
		 pusha(a/b);
	 }
	 
	 void DROP(long i){
		 while(i > 0){
			 popa();
			 i--;
		 }
	 }
	 
	 void EQ_A(){
		 int a = popa(), b = popa();
		 pushc((char)(a==b?1:0));
	 }
	 
	 void EQ_C(){
		 char a = popc(), b = popc();
		 pushc((char)(a==b?1:0));
	 }
	 
	 void EQ_D(){
		 double a = popd(), b = popd();
		 pushc((char)(a==b?1:0));
	 }
	 
	 void EQ_I(){
		 int a = popa(), b = popa();
		 pushc((char)(a==b?1:0));
	 }
	 
	 void GREATER_C(){
		 char a = popc(), b = popc();
		 pushc((char)(a>b?1:0));
	 }
	 
	 void GREATER_D(){
		 double a = popd(), b = popd();
		 pushc((char)(a>b?1:0));
	 }
	 
	 void GREATER_I(){
		 int a = popa(), b = popa();
		 pushc((char)(a>b?1:0));
	 }
	 
	 void GREATEREQ_C(){
		 char a = popc(), b = popc();
		 pushc((char)(a>=b?1:0));
	 }
	 
	 void GREATEREQ_D(){
		 double a = popd(), b = popd();
		 pushc((char)(a>=b?1:0));
	 }
	 
	 void GREATEREQ_I(){
		 int a = popa(), b = popa();
		 pushc((char)(a>=b?1:0));
	 }
	 
	 void HALT(){
		 System.out.println("EXECUTION FINISHED");
		 return;
	 }
	 
	 void JF_A(long i){
		 int a = popa();
		 if(a == 0)
			 IP = i;
	 }
	 
	 void JF_C(long i){
		 char a = popc();
		 if(a == 0){
			 IP = i;
		 }
		 else
		 this.IP++;
	 }
	 
	 void JF_D(long addr){
		 double a = popd();
		 if(a == 0){
			 IP = addr;
		 }
		 else
		 this.IP++;
	 }
	 
	 void JF_I(long i){
		 int a = popa();
		 if(a == 0){
			 IP = i;
		 }
		 else
		 this.IP++;
	 }
	 
	 void JMP(long addr){
		 IP = addr;
	 }
	 
	 void JT_A(long addr){
		 int a = popa();
		 if( a!= 0){
			 IP = addr;
		}
		 else
		 this.IP++;
	 }
	 
	 void JT_C(long addr){
		 char a = popc();
		 if(a != 0){
			 IP = addr;
		 }
		 else
		 this.IP++;
	 }
	 
	 void JT_D(long addr){
		 double a = popd();
		 if(a != 0){
			 IP = addr;
		 }
		 else
		 this.IP++;
	 }
	 
	 void JT_I(long addr){
		 int a = popa();
		 if( a!= 0){
			 IP = addr;
		}
		 else
		 this.IP++;
	 }
	 
	 void LESS_C(){
		 char a = popc(), b = popc();
		 pushc((char)(a<b?1:0));
	 }
	 
	 void LESS_D(){
		 double a = popd(), b = popd();
		 pushc((char)(a<b?1:0));
	 }
	 
	 void LESS_I(){
		 int a = popa(), b = popa();
		 pushc((char)(a<b?1:0));
	 }
	 
	 void LESSEQ_C(){
		 char a = popc(), b = popc();
		 pushc((char)(a<=b?1:0));
	 }
	 
	 void LESSEQ_D(){
		 double a = popd(), b = popd();
		 pushc((char)(a<=b?1:0));
	 }
	 
	 void LESSEQ_I(){
		 int a = popa(), b = popa();
		 pushc((char)(a<=b?1:0));
	 }	 
	 
	 void MUL_C(){
		 char a = popc(), b = popc();
		 pushc((char)(a * b));
	 }
	 
	 void MUL_D(){
		 double a = popd(), b = popd();
		 pushd(a * b);
	 }
	 
	 void MUL_I(){
		 int a = popa(), b = popa();
		 pusha(a * b);
	 }
	 
	 void NOT_A(){
		 int a = popa();
		 pusha(a == 0?1:0);
	 }
	 
	 void NOT_C(){
		 char a = popc();
		 pushc((char) (a == 0?1:0));
	 }
	 
	 void NOT_D(){
		 double a = popd();
		 pushd(a == 0?1:0);
	 }
	 
	 void NOT_I(){
		 int a = popa();
		 pusha(a == 0?1:0);
	 }
	 
	 void NOP(){
		 return;
	 }
	 
	 void NEG_C(){
		 char a = popc();
		 pushc((char)((-1)*a));
	 }
	 
	 void NEG_D(){
		 double a = popd();
		 pushd((-1) * a);
	 }
	 
	 void NEG_I(){
		 int a = popa();
		 pusha((-1) * a);
	 }
	 
	 void NOTEQ_A(){
		 int a = popa(), b = popa();
		 pushc((char)(a != b?1:0));
	 }
	 
	 void NOTEQ_C(){
		 char a = popc(), b = popc();
		 pushc((char)(a != b?1:0));
	 }
	 
	 void NOTEQ_D(){
		 double a = popd(), b = popd();
		 pushc((char)(a != b?1:0));
	 }
	 
	 void NOTEQ_I(){
		 int a = popa(), b = popa();
		 pushc((char)(a != b?1:0));
	 }
	 
	 void OFFSET(){
		 int a = popa(), b = popa();
		 b += a;	
		 pusha(b);
	 }
	 
	 void OR_A(){
		 int a = popa(), b = popa();
		 pushc((a!=0) && (b!=0) == true ? (char)1 : (char)0);
	 }
	 
	 void OR_C(){
		 char a = popc(), b = popc();
		 pushc((a!=0) && (b!=0) == true ? (char)1 : (char)0);
	 }
	 
	 void OR_D(){
		 double a = popd(), b = popd();
		 pushc((a!=0) && (b!=0) == true ? (char)1 : (char)0);
	 }
	 
	 void OR_I(){
		 int a = popa(), b = popa();
		 pushc((a!=0) && (b!=0) == true ? (char)1 : (char)0);
	 }
	 
	 void PUSHCT_A(int a){
		 pusha(a);
	 }
	 
	 void PUSHCT_D(double a){
		 pushd(a);
	 }
	 
	 void PUSHCT_C(char a){
		 pushc(a);
	 }
	 
	 void PUSHCT_I(int a){
		 pusha(a);
	 }
	 
	 void SUB_C(){
		 char a = popc(), b = popc();
		 pushc((char)(a - b));
	 }
	 
	 void SUB_D(){
		 double a = popd(), b = popd();
		 pushd(a - b);
	 }
	 
	 void SUB_I(){
		 int a = popa(), b = popa();
		 pusha(a - b);
	 }
	 
	 
	 void run(){
		 SP = 0;
		 stackAfter = SP + STACK_SIZE;
		 while(true){
			 switch(instructions.get((int)IP).returnOpCode()){
			case O_ADD_C:
				this.ADD_C();
				this.IP++;
				break;
			case O_ADD_D:
				this.ADD_D();
				this.IP++;
				break;
			case O_ADD_I:
				this.ADD_I();
				this.IP++;
				break;
			case O_AND_A:
				this.AND_A();
				this.IP++;
				break;
			case O_AND_C:
				this.AND_C();
				this.IP++;
				break;
			case O_AND_D:
				this.AND_D();
				this.IP++;
				break;
			case O_AND_I:
				this.AND_D();
				this.IP++;
				break;
			case O_CALL:
				this.CALL(instructions.get((int)IP).addr[0]);
				this.IP++;
				break;
			case O_CALLEXT:
				int a = popa();
				System.out.println(a);
				this.IP++;
				break;
			case O_CAST_C_D:
				this.CAST_C_D();
				this.IP++;
				break;
			case O_CAST_C_I:
				this.CAST_C_I();
				this.IP++;
				break;
			case O_CAST_D_C:
				this.CAST_D_C();
				this.IP++;
				break;
			case O_CAST_D_I:
				this.CAST_D_I();
				this.IP++;
				break;
			case O_CAST_I_C:
				this.CAST_I_C();
				this.IP++;
				break;
			case O_CAST_I_D:
				this.CAST_I_D();
				this.IP++;
				break;
			case O_DIV_C:
				this.DIV_C();
				this.IP++;
				break;
			case O_DIV_D:
				this.DIV_D();
				this.IP++;
				break;
			case O_DIV_I:
				this.DIV_I();
				this.IP++;
				break;
			case O_DROP:
				this.DROP(instructions.get((int)IP).i[0]);
				this.IP++;
				break;
			case O_ENTER:
				//this.ENTER();
				this.IP++;
				break;
			case O_EQ_A:
				this.EQ_A();
				this.IP++;
				break;
			case O_EQ_C:
				this.EQ_C();
				this.IP++;
				break;
			case O_EQ_D:
				this.EQ_D();
				this.IP++;
				break;
			case O_EQ_I:
				this.EQ_I();
				this.IP++;
				break;
			case O_GREATER_C:
				this.GREATER_C();
				this.IP++;
				break;
			case O_GREATER_D:
				this.GREATER_D();
				this.IP++;
				break;
			case O_GREATER_I:
				this.GREATER_I();
				this.IP++;
				break;
			case O_GREATEREQ_C:
				this.GREATEREQ_C();
				this.IP++;
				break;
			case O_GREATEREQ_D:
				this.GREATEREQ_D();
				this.IP++;
				break;
			case O_GREATEREQ_I:
				this.GREATEREQ_I();
				this.IP++;
				break;
			case O_HALT:
				return;
			case O_INSERT:
				//
				this.IP++;
				break;
			case O_JF_A:
				this.JF_A(instructions.get((int)IP).i[0]);
				break;
			case O_JF_C:
				this.JF_C(instructions.get((int)IP).i[0]);
				break;
			case O_JF_D:
				this.JF_D(instructions.get((int)IP).i[0]);
				break;
			case O_JF_I:
				this.JF_I(instructions.get((int)IP).i[0]);
				break;
			case O_JMP:
				this.JMP(instructions.get((int)IP).i[0]);
				break;
			case O_JT_A:
				this.JT_A(instructions.get((int)IP).i[0]);
				break;
			case O_JT_C:
				this.JT_C(instructions.get((int)IP).i[0]);
				break;
			case O_JT_D:
				this.JT_D(instructions.get((int)IP).i[0]);
				break;
			case O_JT_I:
				this.JT_I(instructions.get((int)IP).i[0]);
				break;
			case O_LESS_C:
				this.LESS_C();
				this.IP++;
				break;
			case O_LESS_D:
				this.LESS_D();
				this.IP++;
				break;
			case O_LESS_I:
				this.LESS_I();
				this.IP++;
				break;
			case O_LESSEQ_C:
				this.LESSEQ_C();
				this.IP++;
				break;
			case O_LESSEQ_D:
				this.LESSEQ_D();
				this.IP++;
				break;
			case O_LESSEQ_I:
				this.LESSEQ_I();
				this.IP++;
				break;
			case O_LOAD:
				//
				this.IP++;
				break;
			case O_MUL_C:
				this.MUL_C();
				this.IP++;
				break;
			case O_MUL_D:
				this.MUL_D();
				this.IP++;
				break;
			case O_MUL_I:
				this.MUL_I();
				this.IP++;
				break;
			case O_NEG_C:
				this.NEG_C();
				this.IP++;
				break;
			case O_NEG_D:
				this.NEG_D();
				this.IP++;
				break;
			case O_NEG_I:
				this.NEG_I();
				this.IP++;
				break;
			case O_NOP:
				this.IP++;
				break;
			case O_NOT_A:
				this.NOT_A();
				this.IP++;
				break;
			case O_NOT_C:
				this.NOT_C();
				this.IP++;
				break;
			case O_NOT_D:
				this.NOT_D();
				this.IP++;
				break;
			case O_NOT_I:
				this.NOT_I();
				this.IP++;
				break;
			case O_NOTEQ_A:
				this.NOTEQ_A();
				this.IP++;
				break;
			case O_NOTEQ_C:
				this.NOTEQ_C();
				this.IP++;
				break;
			case O_NOTEQ_D:
				this.NOTEQ_D();
				this.IP++;
				break;
			case O_NOTEQ_I:
				this.NOTEQ_I();
				this.IP++;
				break;
			case O_OFFSET:
				this.OFFSET();
				this.IP++;
				break;
			case O_OR_A:
				this.OR_A();
				this.IP++;
				break;
			case O_OR_C:
				this.OR_C();
				this.IP++;
				break;
			case O_OR_D:
				this.OR_D();
				this.IP++;
				break;
			case O_OR_I:
				this.OR_I();
				this.IP++;
				break;
			case O_PUSHFPADDR:
				//
				this.IP++;
				break;
			case O_PUSHCT_A:
				this.PUSHCT_A((int)instructions.get((int)IP).i[0]);
				this.IP++;
				break;
			case O_PUSHCT_C:
				this.PUSHCT_C((char)instructions.get((int)IP).i[0]);
				this.IP++;
				break;
			case O_PUSHCT_D:
				this.PUSHCT_D(instructions.get((int)IP).d[0]);
				this.IP++;
				break;
			case O_PUSHCT_I:
				this.PUSHCT_I((int)instructions.get((int)IP).i[0]);
				this.IP++;
				break;
			case O_RET:
				//
				this.IP++;
				break;
			case O_STORE:
				//
				this.IP++;
				break;
			case O_SUB_C:
				this.SUB_C();
				this.IP++;
				break;
			case O_SUB_D:
				this.SUB_D();
				this.IP++;
				break;
			case O_SUB_I:
				this.SUB_I();
				this.IP++;
				break;
			 }
		 }
	 }
	 
}
