package LexicalAnalyzer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AtomMap {
	private Map<String, String> atoms = new HashMap<String, String>();
	public AtomMap(){
		atoms.put("int", "INT");
		atoms.put("char", "CHAR");
		atoms.put("==", "EQUALS");
		atoms.put("=", "ASSIGN");
		atoms.put(";", "SEMICOLON");
		atoms.put("(", "LPAR");
		atoms.put(",", "COMMA");
		atoms.put(")", "RPAR");
		atoms.put("{", "LACC");
		atoms.put("}", "RACC");
		atoms.put(" ", " ");
	}
	public Set<String> getKeySet(){
		return atoms.keySet();
	}
	public String getAtom(String arg){
		return atoms.get(arg);
	}
}