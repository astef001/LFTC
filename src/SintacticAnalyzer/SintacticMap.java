package SintacticAnalyzer;

import java.util.ArrayList;

import LexicalAnalyzer.Atom;

public class SintacticMap implements SintaticMapInterface{
	private ArrayList<Atom> atomList;
	public SintacticMap(ArrayList<Atom> arg){
		atomList=arg;
	}

	private ArrayList<Atom> atomList;
	
	public SintacticMap(ArrayList<Atom> arg){
		this.atomList = arg;
	}
	
	@Override
	public boolean doUnit() {
		while(true == this.doDeclStruct() || true == this.doDeclFunc() || true == this.doDeclVar());
			if(this.consume("END")){
				return false;
			}
		return false;
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
		return false;
	}

	@Override
	public boolean doTypeBase() {
		if(this.consume("INT") || this.consume("DOUBLE") || this.consume("CHAR") || this.consume("STRUCT")){
			if(this.consume("ID")){
				return true;
			}
		}
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doStmCompound() {
		if(this.consume("LACC")){
			while(this.doDeclVar() || this.doStm());
			if(this.consume("RACC")){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean doExpr() {
		if(this.doExprAssign()){
			return true;
		}
		return false;
	}

	@Override
	public boolean doExprAssign() {
		return false;
	}

	@Override
	public boolean doExprOr() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doExprAnd() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doExprEq() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doExprRel() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doExprAdd() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doExprMul() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doExprCast() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doExprUnary() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doExprPostifx() {
		// TODO Auto-generated method stub
		return false;
	}
	exprPrimary: ID ( LPAR ( expr ( COMMA expr )* )? RPAR )?
	           | CT_INT
	           | CT_REAL 
	           | CT_CHAR 
	           | CT_STRING 
	           | LPAR expr RPAR ;
	@Override
	public boolean doExprPrimary() {
		if(this.consume("ID")){
			if()
			this.consume("LPAR");
			this.doExpr();
			while(this.consume("COMMA") && this.doExpr());
			this.consume("RPAR");
		}
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
	
	
}
