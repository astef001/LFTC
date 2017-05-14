package LexicalAnalyzer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AtomMap {
	private LinkedHashMap<String, String> atoms = new LinkedHashMap<String, String>();
	private ArrayList<String> ignored = new ArrayList<String>();
	public AtomMap(){
		atoms.put("int[^a-zA-Z0-9_]", "INT");
		atoms.put("char[^a-zA-Z0-9_]", "CHAR");		
		atoms.put("break[^a-zA-Z0-9_]", "BREAK");
		atoms.put("double[^a-zA-Z0-9_]","DOUBLE");
		atoms.put("else[^a-zA-Z0-9_]", "ELSE");
		atoms.put("for[^a-zA-Z0-9_]", "FOR");
		atoms.put("if[^a-zA-Z0-9_]", "IF");
		atoms.put("return[^a-zA-Z0-9_]", "RETURN");
		atoms.put("struct[^a-zA-Z0-9_]", "STRUCT");
		atoms.put("void[^a-zA-Z0-9_]", "VOID");
		atoms.put("while[^a-zA-Z0-9_]", "WHILE");
		atoms.put(">=", "GREATEREQ");
		atoms.put("<=", "LESSEQ");
		atoms.put("!=","NOTEQ");
		atoms.put("==", "EQUAL");
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
		/* escaped the DOT (.) because it was considered any character before (0x2 was considered a real) */
		atoms.put("([0-9]+(\\.[0-9]+((e|E)(-|\\+)?[0-9]+)?|(\\.[0-9]+)?(e|E)(-|\\+)?[0-9]+))", "CT_REAL");
		atoms.put("((0x[0-9a-fA-F]+)|([1-9][0-9]*|0[0-7]*))", "CT_INT");
		atoms.put("[\"]([abfnrtv'?\\\\\"\\\\0]|[^\"\\\\])*[\"]", "CT_STRING");		
		atoms.put("['](\\\\[abfnrtv'?\\\\\"\\\\0]|[^'\\\\])[']", "CT_CHAR");
		atoms.put("[a-zA-Z_][a-zA-Z0-9_]*","ID");
		
		ignored.add("[ \n\r\t]+");
		ignored.add("//[^\n\r\0]*");
		ignored.add("\\/\\*(.)*\\*\\/");
	}
	public String removeIgnored(String el){
		Iterator<String> ignoredIterator= ignored.iterator();
		while(ignoredIterator.hasNext()){
			String currentEl=ignoredIterator.next();
			Pattern p=Pattern.compile("^"+currentEl);
			Matcher m=p.matcher(el);
			if(m.find()){
				el=el.substring(m.end());
				return el;
			}
		}
		return el;
	}
	public Set<String> getKeySet(){
		return atoms.keySet();
	}
	public String getAtomId(String arg){
		return atoms.get(arg);
	}
}