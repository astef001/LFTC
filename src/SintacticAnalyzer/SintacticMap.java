package SintacticAnalyzer;

import java.util.ArrayList;
import java.util.Iterator;

import LexicalAnalyzer.Atom;

public class SintacticMap implements SintaticMapInterface{
	private ArrayList<Atom> atomList;
	private int currentPosition = 0;
	private ArrayList<Symbol> symbolList;
	
	private String symName = null;
	private String symType = null;
	private boolean symArray = false;
	private int depth = 0;
	private boolean declaratin = false;
	private int tmpIndex = -1;
	public SintacticMap(ArrayList<Atom> arg){
		atomList=arg;
		this.symbolList = new ArrayList<Symbol>();
	}
	
	@Override
	public boolean doUnit() {
		while(true == this.doDeclStruct() || true == this.doDeclFunc() || true == this.doDeclVar());
			if(this.consume("END")){
				System.out.println("\nCompilation Success\n");
				return true;
			}
			else{
				System.out.println("\nCompilation Fail\n");
				return false;
			}
}

	@Override
	public boolean doDeclStruct() {
		int tmp_position = this.currentPosition;
		symName = null;
		symType = null;
		symArray = false;
		if(true == this.consume("STRUCT")){
			if(true == this.consume("ID")){
				int tmpPosStruct = this.currentPosition - 1;
				if(true == this.consume("LACC")){
					while(this.doDeclVar() == true);
						if(true == this.consume("RACC")){
							if(true == this.consume("SEMICOLON"))
								symName = atomList.get(tmpPosStruct).getValue();
								symType = "TB_STRUCT";
								symArray = false;
								if(!this.addSymbol()){
									return false;
								}
								return true;
						}
					}
			}
		}
	this.currentPosition = tmp_position;	
	return false;
}

	@Override
	public boolean doDeclVar() {
		symName = null;
		symType = null;
		symArray = false;
		this.declaratin = true;
		int tmp_position = this.currentPosition;
		if(this.doTypeBase()){
			if(this.consume("ID")==true){
				symName = atomList.get(this.currentPosition - 1).getValue();
				this.doArrayDecl();
				if(!(this.addSymbol())){
					this.currentPosition = tmp_position;
					this.declaratin = false;
					return false;
				}
				while(this.consume("COMMA")){
					if(this.consume("ID")){
						symName = atomList.get(this.currentPosition - 1).getValue();
						this.doArrayDecl();
						if(!(this.addSymbol())){
							this.currentPosition = tmp_position;
							this.declaratin = false;
							return false;
						}
					}
				}
				if(this.consume("SEMICOLON")){
					this.declaratin = false;
					return true;
				}
			}
		}
		this.currentPosition = tmp_position;
		this.declaratin = false;
		return false;
	}

	@Override
	public boolean doTypeBase() {
		int tmp_position = this.currentPosition;
		if(this.consume("INT")){
			this.symType = "TB_INT";
			return true;
			}
		if(this.consume("DOUBLE")){
			this.symType = "TB_DOUBLE";
			return true;
		}
		if(this.consume("CHAR")){
			this.symType = "TB_CHAR";	
			return true;
		}
		if(this.consume("STRUCT")){
			if(this.consume("ID")){
				this.symType = "TB_STRUCT";
				return true;
			}
		}
		this.currentPosition = tmp_position;
		return false;
	}

	@Override
	public boolean doArrayDecl() {
		int tmp_position = this.currentPosition;
		if(true == this.consume("LBRACKET")){
			this.doExpr();
				if(true == this.consume("RBRACKET")){
					this.symArray = true;
					return true;
				}
		}
		this.currentPosition = tmp_position;
		return false;
	}

	@Override
	public boolean doTypeName() {
		int tmp_position = this.currentPosition;
		if(this.doTypeBase() == true){
			this.doArrayDecl();
				return true;
		}
		this.currentPosition = tmp_position;
		return false;
	}

	@Override
	public boolean doDeclFunc() {
		symName = null;
		symType = null;
		symArray = false;
		this.declaratin = true;
		int tmp_position = this.currentPosition;
		if(true == this.doTypeBase()){
			this.consume("MUL");
		}
		else{
			if(true == this.consume("VOID")){
				this.symType = "TB_VOID";
			}
			else{
				this.currentPosition = tmp_position;
				this.declaratin = false;
				return false;
			}
		}
		
		if(true == this.consume("ID")){
			symName = atomList.get(this.currentPosition - 1).getValue();
			if(true == this.consume("LPAR")){
				this.doFuncArg();
				while(this.consume("COMMA")){
					if(this.doFuncArg()){
						
					} else {
						this.currentPosition = tmp_position;
						this.declaratin = false;
						return false;
					}
				}
				if(true == this.consume("RPAR")){
					if(!this.addSymbol()){
						this.declaratin = false;
						return false;
					}
					if(true == this.doStmCompound()){
						this.declaratin = false;
						return true;
					}
				}
			}
		}
		this.currentPosition = tmp_position;
		this.declaratin = false;
		return false;
	}

	@Override
	public boolean doFuncArg() {
		int tmp_position = this.currentPosition;
		if(true == this.doTypeBase()){
			if(true == this.consume("ID")){
				this.doArrayDecl();
				return true;
			}
		}
		this.currentPosition = tmp_position;
		return false;
	}

	@Override
	public boolean doStm() {
		int tmp_position = this.currentPosition;
		if(this.doStmCompound())
			return true;
		if(this.consume("IF")){
			if(this.consume("LPAR")){
				if(this.doExpr()){
					if(this.consume("RPAR")){
						if(this.doStm()){
							if(this.consume("ELSE")){
								if(!this.doStm()){
									return false;
								}
							}
							return true;
						}
					}
				}
			}
		}
		if(this.consume("WHILE")){
			if(this.consume("LPAR")){
				if(this.doExpr()){
					if(this.consume("RPAR")){
						if(this.doStm()){
							return true;
						}
					}
				}
			}
		}
		if(this.consume("FOR")){
			if(this.consume("LPAR")){
				this.doExpr();
				if(this.consume("SEMICOLON")){
					this.doExpr();
					if(this.consume("SEMICOLON")){
						this.doExpr();
						if(this.consume("RPAR")){
							if(this.doStm()){
								return true;
							}
						}
					}
				}
			}
		}
		if(this.consume("BREAK")){
			if(this.consume("SEMICOLON")){
				return true;
			}
		}
		if(this.consume("RETURN")){
			this.doExpr();
			if(this.consume("SEMICOLON")){
				return true;
			}
		}
		else{
			this.doExpr();
			if(this.consume("SEMICOLON")){
				return true;
			}
		}
		this.currentPosition = tmp_position;
		return false;
	}

	@Override
	public boolean doStmCompound() {
		int tmp_position = currentPosition;
		if(this.consume("LACC")){
			while(this.doDeclVar() || this.doStm());
			if(this.consume("RACC")){
				return true;
			}
		}
		this.currentPosition = tmp_position;
		return false;
		
	}

	@Override
	public boolean doExpr() {
		int tmp_position = currentPosition;
		if(this.doExprAssign()){
			return true;
		}
		this.currentPosition = tmp_position;
		return false;
		
	}

	@Override
	public boolean doExprAssign() {
		int tmp_position = currentPosition;
		if(this.doExprUnary()){
			if(this.consume("ASSIGN")){
				if(this.doExprAssign()){
					return true;
				} else {
					return false;
					} 
				}
			else{
				//to add
			}
		}
		this.currentPosition = tmp_position;
			if(this.doExprOr()){
				return true;
			}
			this.currentPosition = tmp_position;
		return false;
	}

	@Override
	public boolean doExprOr() {
		int tmp_position = currentPosition;
		if(this.doExprAnd()){
			if(this.doExprOr1()){
				return true;
			}
		}
		this.currentPosition = tmp_position;
		return false;
	}

	public boolean doExprOr1(){
		int tmp_position = currentPosition;
		if(this.consume("OR")){
			if(this.doExprAnd()){
				return true;
			}
		}
		this.currentPosition = tmp_position;
		if(this.doEpsilon()){
			return true;
		}
		return false;
	}
	@Override
	public boolean doExprAnd() {
		int tmp_position = currentPosition;
		if(this.doExprEq()){
			if(this.doExprAnd1()){
				return true;
			}
		}
		this.currentPosition = tmp_position;
		return false;
	}
	
	public boolean doExprAnd1(){
		int tmp_position = currentPosition;
		if(this.consume("AND")){
			if(this.doExprEq()){
				doExprAnd1();
				return true;
			}else {
				this.currentPosition = tmp_position;
				return false;
			}
		}
		return true;
	}
	@Override
	public boolean doExprEq() {
		int tmp_position = currentPosition;
		if(this.doExprRel()){
			if(this.doExprEq1()){
				return true;
			}
		}
		this.currentPosition = tmp_position;
		return false;
	}
	
	public boolean doExprEq1(){
		if(this.consume("EQUAL") || this.consume("NOTEQ")){
			if(this.doExprRel()){
				this.doExprEq1();
				return true;
			}
		} else {
			if(this.doExprRel()){
				this.doExprEq1();
				return true;
			}
		}
		
		return true;
	}

	@Override
	public boolean doExprRel() {
		int tmp_position = currentPosition;
		if(this.doExprAdd()){
			if(this.doExprRel1()){
				return true;
			}
		}
		this.currentPosition = tmp_position;
		return false;
	}
	
	public boolean doExprRel1(){
		int tmp_position = currentPosition;
		if(this.consume("LESS") || this.consume("LESSEQ") || this.consume("GREATER") || this.consume("GREATEREQ")){
			if(this.doExprAdd()){
				this.doExprRel1();
				return true;
			}
		}
		if(this.doEpsilon()){
			return true;
		}
		this.currentPosition = tmp_position;
		return false;
	}
	@Override
	public boolean doExprAdd() {
		int tmp_position = currentPosition;
		if(this.doExprMul()){
			if(this.doExprAdd1()){
				return true;
			}
		}
		this.currentPosition = tmp_position;
		return false;
	}

	public boolean doExprAdd1(){
		int tmp_position = currentPosition;
		if(this.consume("ADD") || this.consume("SUB")){
			if(this.doExprMul()){
				if(this.doExprAdd1())
				return true;
			}
		}
		if(this.doEpsilon()){
			return true;
		}
		this.currentPosition = tmp_position;
		return false;
	}
	@Override
	public boolean doExprMul() {
		int tmp_position = currentPosition;
		if(this.doExprCast()){
			if(this.doExprMul1()){
				return true;
			}
		}
		this.currentPosition = tmp_position;
		return false;
	}
	
	public boolean doExprMul1(){
		int tmp_position = currentPosition;
		if(this.consume("MUL") || this.consume("DIV")){
			if(this.doExprMul1()){
				return true;
			}
		}
		if(this.doEpsilon()){
			return true;
		}
		this.currentPosition = tmp_position;
		return false;
	}

	@Override
	public boolean doExprCast() {
		int tmp_position = currentPosition;
		if(this.consume("LPAR")){
			if(this.doTypeName()){
				if(this.consume("RPAR")){
					if(this.doExprCast()){
						return true;
					}
				}
			}
		}
		this.currentPosition = tmp_position;
		if(this.doExprUnary()){
			return true;
		}
		this.currentPosition = tmp_position;
		return false;
	}

	@Override
	public boolean doExprUnary() {
		int tmp_position = currentPosition;
		if(this.consume("SUB") || this.consume("NOT") ){
			if(this.doExprUnary()){
				return true;
			}
		}
		this.currentPosition = tmp_position;
			if(this.doExprPostifx()){
			return true;
		}
		this.currentPosition = tmp_position;
		return false;
	}

	@Override
	public boolean doExprPostifx() {
		int tmp_position = currentPosition;
		if(this.doExprPrimary()){
				if(this.doExprPostfix1()){
					return true;
				}
		}
		this.currentPosition = tmp_position;
		return false;
	}
	
	public boolean doExprPostfix1(){
		int tmp_position = currentPosition;
		if(this.consume("LBRACKET")){
			if(this.doExpr()){
				if(this.consume("RBRACKET")){
					if(this.doExprPostfix1()){
						return true;
					}
				}
			}
		}
		this.currentPosition = tmp_position;
		if(this.consume("DOT")){
			if(this.consume("ID")){
				if(this.doExprPostfix1()){
					return true;					
				}
			}
		}
		if(this.doEpsilon()){
			return true;
		}
		return false;
	}

	@Override
	public boolean doExprPrimary() {
		int tmp_position = currentPosition;
		if(this.consume("ID")){
			if(this.consume("LPAR")){
			this.doExpr(); 
			while(this.consume("COMMA")){
				if(!this.doExpr()){
					this.currentPosition = tmp_position;
					return false;
				}
			}
				if(!this.consume("RPAR")){
					this.currentPosition = tmp_position;
					return false;
				}
			}
			return true;
		} else if(this.consume("CT_INT")){
			return true;
		} else if(this.consume("CT_REAL")){
			return true;
		} else if(this.consume("CT_STRING")){
			return true;
		} else if(this.consume("CT_CHAR")){
			return true;
		} else if(this.consume("LPAR")){
			if(this.doExpr()){
				if(this.consume("RPAR")){
					return true;
				}
			}
		}
		this.currentPosition = tmp_position;
		return false;
	}

	@Override
	public void errorPrint() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean consume(String argToFind) {
		String tmpString = atomList.get(this.currentPosition).getId();
		if(argToFind.equals(tmpString)){
			System.out.print(argToFind+ " ");
			this.currentPosition++;
			if(argToFind == "LACC"){
				this.depth++;
			}
			if(argToFind == "RACC"){
				this.removeSymbols(depth);
				this.depth--;
			}
			if(argToFind == "ID" && !this.declaratin && !this.isPresent(atomList.get(this.currentPosition - 1).getValue())){
				return false;
			}
			return true;
		} else
		{
			/*if(!argToFind.equals("END") && this.maxStep<this.currentPosition){
				this.maxStep=this.currentPosition;
				error = "Error at line " + atomList.get(this.currentPosition).getLine() + ": Expected "+argToFind+" Found: "+ tmpString;
				System.out.println(error);
			}*/
			return false;
		}
	}
	
	public boolean doEpsilon(){
		return true;
	}
	
	private boolean addSymbol(){
		Iterator<Symbol> symIterator = this.symbolList.iterator();
		while(symIterator.hasNext()){
			Symbol tmpSym = symIterator.next();
			if(tmpSym.getName().equals( this.symName ) && this.depth == tmpSym.getDepth()){
				System.out.println("\nDuplicate " + this.symName);
				return false;
			}
		}
		this.symbolList.add(new Symbol(this.symName, this.symType, this.symArray, this.depth));
		this.tmpIndex++;
		return true;
	}
	
	private void removeSymbols(int depthArg){
		this.symbolList.removeIf((Symbol arg) -> {return (arg.getDepth() == this.depth);});
	}
	
	private boolean isPresent(String argName){
		Iterator<Symbol> symIterator = this.symbolList.iterator();
		while(symIterator.hasNext()){
			if(symIterator.next().getName().equals( argName )){
				return true;
			}
		}
		System.out.println("\nSymbol " + argName + " not declared");
		return false;
	}
}
