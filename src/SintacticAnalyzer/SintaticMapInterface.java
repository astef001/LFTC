package SintacticAnalyzer;

public interface SintaticMapInterface {
	boolean doUnit();
	boolean doDeclStruct();
	boolean doDeclVar();
	boolean doTypeBase();
	boolean doArrayDecl();
	boolean doTypeName();
	boolean doDeclFunc();
	boolean doFuncArg();
	boolean doStm();
	boolean doStmCompound();
	boolean doExpr();
	boolean doExprAssign();
	boolean doExprOr();
	boolean doExprAnd();
	boolean doExprEq();
	boolean doExprRel();
	boolean doExprAdd();
	boolean doExprMul();
	boolean doExprCast();
	boolean doExprUnary();
	boolean doExprPostifx();
	boolean doExprPrimary();
	boolean consume();
	void errorPrint();
}
