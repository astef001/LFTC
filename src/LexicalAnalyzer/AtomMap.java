package LexicalAnalyzer;

import java.util.LinkedHashMap;
import java.util.Set;

public class AtomMap {
	private LinkedHashMap<String, String> atoms = new LinkedHashMap<String, String>();
	public AtomMap(){
		atoms.put("int", "INT");
		atoms.put("char", "CHAR");
		atoms.put("break", "BREAK");
		atoms.put("double","DOUBLE");
		atoms.put("else", "ELSE");
		atoms.put("for", "FOR");
		atoms.put("if", "IF");
		atoms.put("return", "RETURN");
		atoms.put("struct", "STRUCT");
		atoms.put("void", "VOID");
		atoms.put("while", "WHILE");
		atoms.put(">=", "GREATEREQ");
		atoms.put("<=", "LESSEQ");
		atoms.put("!=","NOTEQ");
		atoms.put("==", "EQUALS");
		atoms.put("=", "ASSIGN");
		atoms.put(";", "SEMICOLON");
		atoms.put("\\(", "LPAR");
		atoms.put(",", "COMMA");
		atoms.put("\\)", "RPAR");
		atoms.put("\\{", "LACC");
		atoms.put("\\}", "RACC");
		atoms.put("\\[","LBRACKET");
		atoms.put("\\]","RBRACKET");
		atoms.put("\\+","ADD");
		atoms.put("-","SUB");
		atoms.put("\\*","MUL");
		atoms.put("/", "DIV");
		atoms.put("\\.","DOT");
		atoms.put("&&","AND");
		atoms.put("\\|\\|", "OR");
		atoms.put("!","NOT");
		atoms.put("<","LESS");
		atoms.put(">","GREATER") ;
		atoms.put("[ \t]", " ");
		atoms.put("[0-9]+('.'[0-9]+('e'|'E')('-'|'+')?[0-9]+?|('.'[0-9]+)?('e'|'E')('-'|'+')?[0-9]+)('e'|'E')('-'|'+')?[0-9]+", "CT_REAL");
		atoms.put("[1-9][0-9]*|0[0-7]*|0x[0-9a-fA-F]+", "CT_INT");
		atoms.put("[\"]([abfnrtv'?\\\\\"\\\\0]|[^\"\\\\])*[\"]", "CT_STRING");		
		atoms.put("[']([abfnrtv'?\\\\\"\\\\0]|[^'\\\\])[']", "CT_CHAR");
	}
	public Set<String> getKeySet(){
		return atoms.keySet();
	}
	public String getAtom(String arg){
		return atoms.get(arg);
	}
}