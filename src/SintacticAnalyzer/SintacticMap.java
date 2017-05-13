package SintacticAnalyzer;

import java.util.ArrayList;

import LexicalAnalyzer.Atom;

public class SintacticMap implements SintaticMapInterface{
	private ArrayList<Atom> atomList;
	public SintacticMap(ArrayList<Atom> arg){
		atomList=arg;
	}
	
	@Override
	public boolean doUnit() {
		while(true == this.doDeclStruct() || true == this.doDeclFunc() || true == this.doDeclVar());
			if(this.consume("END")){
				System.out.println("Compilation Succes\n");
				return true;
			}
			else{
				System.out.println("Compilation error\n");
				return false;
			}
}

	@Override
	public boolean doDeclStruct() {
		if(true == this.consume("STRUCT")){
			if(true == this.consume("ID")){
				if(true == this.consume("LACC")){
					while(this.doDeclVar() == true);
						if(true == this.consume("RACC")){
							if(true == this.consume("SEMICOLON"))
								return true;
						}
					}
			}
		}
	return false;
}

	@Override
	public boolean doDeclVar() {
<<<<<<< HEAD
		// Done by Alex
=======
		if(this.doTypeBase()){
			if(this.consume("ID")==true){
				this.doArrayDecl();
				while(this.consume("COMMA")){
					if(this.consume("ID")){
						this.doArrayDecl();
					}else err("missing id after ,");
				}
				if(this.consume("SEMICOLON")){
					return true;
				}else err
			}
		}
>>>>>>> fa76d946838cfbe084d728d2cc60b2148e67d0e9
		return false;
	}

	@Override
	public boolean doTypeBase() {
<<<<<<< HEAD
		// Done by Alex
=======
		if(this.consume("INT") || this.consume("DOUBLE") || this.consume("CHAR") || this.consume("STRUCT")){
			if(this.consume("ID")){
				return true;
			}
		}
>>>>>>> fa76d946838cfbe084d728d2cc60b2148e67d0e9
		return false;
	}

	@Override
	public boolean doArrayDecl() {
		if(true == this.consume("LBRACKET")){
			this.doExpr();
				if(true == this.consume("RBRACKET")){
					return true;
				}
		}
		return false;
	}

	@Override
	public boolean doTypeName() {
		if(this.doTypeBase() == true){
			this.doArrayDecl();
				return true;
		}
		return false;
	}

	@Override
	public boolean doDeclFunc() {
		if(true == this.doTypeBase()){
			this.consume("MUL");
		}
		else{
			if(true == this.consume("VOID")){
				
			}
			else return false;
		}
		
		if(true == this.consume("ID")){
			if(true == this.consume("LPAR")){
				this.doFuncArg();
				while(this.consume("COMMA")){
					if(this.doFuncArg()){
						
					} else {
						return false;
					}
				}
				if(true == this.consume("RPAR")){
					if(true == this.doStmCompound()){
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean doFuncArg() {
		if(true == this.doTypeBase()){
			if(true == this.consume("ID")){
				this.doArrayDecl();
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean doStm() {
		if(this.doStmCompound())
			return true;
		if(this.consume("IF")){
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
		return false;
	}

	@Override
	public boolean doStmCompound() {
<<<<<<< HEAD
		// Done by Alex
=======
		if(this.consume("LACC")){
			while(this.doDeclVar() || this.doStm());
			if(this.consume("RACC")){
				return true;
			}
		}
>>>>>>> fa76d946838cfbe084d728d2cc60b2148e67d0e9
		return false;
	}

	@Override
	public boolean doExpr() {
<<<<<<< HEAD
		// Done by Alex
=======
		if(this.doExprAssign()){
			return true;
		}
>>>>>>> fa76d946838cfbe084d728d2cc60b2148e67d0e9
		return false;
	}

	@Override
	public boolean doExprAssign() {
<<<<<<< HEAD
		if(this.doExprUnary()){
			if(this.consume("ASSIGN")){
				if(this.doExprOr() || this.doExprAssign()){
					return true;
				}
			}
		}
=======
>>>>>>> fa76d946838cfbe084d728d2cc60b2148e67d0e9
		return false;
	}

	@Override
	public boolean doExprOr() {
		if(this.doExprAnd()){
			if(this.doExprOr1()){
				return true;
			}
		}
		return false;
	}

	public boolean doExprOr1(){
		if(this.consume("OR")){
			if(this.doExprAnd()){
				return true;
			}
		}
		if(this.doEpsilon()){
			return true;
		}
		return false;
	}
	@Override
	public boolean doExprAnd() {
		if(this.doExprEq()){
			if(this.doExprAnd1()){
				return true;
			}
		}
		return false;
	}
	
	public boolean doExprAnd1(){
		if(this.consume("AND")){
			if(this.doExprEq()){
				return true;
			}
		}
		if(this.doEpsilon()){
			return true;
		}
		return false;
	}
	@Override
	public boolean doExprEq() {
		if(this.doExprRel()){
			if(this.doExprEq1()){
				return true;
			}
		}
		return false;
	}
	
	public boolean doExprEq1(){
		if(this.consume("EQUAL") || this.consume("NOTEQ")){
			if(this.doExprRel()){
				return true;
			}
		}
		if(this.doEpsilon()){
			return true;
		}
		return false;
	}

	@Override
	public boolean doExprRel() {
		if(this.doExprAdd()){
			if(this.doExprRel1()){
				return true;
			}
		}
		return false;
	}
	
	public boolean doExprRel1(){
		if(this.consume("LESS") || this.consume("LESSEQ") || this.consume("GREATER") || this.consume("GREATEREQ")){
			if(this.doExprAdd()){
				return true;
			}
		}
		if(this.doEpsilon()){
			return true;
		}
		return false;
	}
	@Override
	public boolean doExprAdd() {
		if(this.doExprMul()){
			if(this.doExprAdd1()){
				return true;
			}
		}
		return false;
	}

	public boolean doExprAdd1(){
		if(this.consume("ADD") || this.consume("SUB")){
			if(this.doExprMul()){
				return true;
			}
		}
		if(this.doEpsilon()){
			return true;
		}
		return false;
	}
	@Override
	public boolean doExprMul() {
		if(this.doExprCast()){
			if(this.doExprMul1()){
				return true;
			}
		}
		return false;
	}
	
	public boolean doExprMul1(){
		if(this.consume("MUL") || this.consume("DIV")){
			if(this.doExprMul1()){
				return true;
			}
		}
		if(this.doEpsilon()){
			return true;
		}
		return false;
	}

	@Override
	public boolean doExprCast() {
		if(this.consume("LPAR")){
			if(this.doTypeName()){
				if(this.consume("RPAR")){
					if(this.doExprUnary() || this.doExprCast()){
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean doExprUnary() {
		if(this.consume("SUB") || this.consume("NOT")){
			if(this.doExprPostifx() || this.doExprUnary())
				return true;
		}
		return false;
	}

	@Override
	public boolean doExprPostifx() {
		if(this.doExprPrimary()){
			if(this.doExprPostfix1()){
				return true;
			}
		}
		return false;
	}
	
	public boolean doExprPostfix1(){
		if(this.consume("LBRACKET")){
			if(this.doExpr()){
				if(this.consume("RBRACKET")){
					if(this.doExprPostfix1()){
						return true;
					}
				}
			}
		}
		if(this.consume("DOT")){
			if(this.consume("ID")){
				return true;
			}
		}
		if(this.doEpsilon()){
			return true;
		}
		return false;
	}

	@Override
	public boolean doExprPrimary() {
<<<<<<< HEAD
		// Done by Alex
=======
		if(this.consume("ID")){
			if()
			this.consume("LPAR");
			this.doExpr();
			while(this.consume("COMMA") && this.doExpr());
			this.consume("RPAR");
		}
>>>>>>> fa76d946838cfbe084d728d2cc60b2148e67d0e9
		return false;
	}

	@Override
	public void errorPrint() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean consume(String argToFind) {
		if(argToFind == atomList.get(0).getValue()){
			atomList.remove(0);
			return true;
		} else
		return false;
	}
	
	public boolean doEpsilon(){
		return true;
	}
}
