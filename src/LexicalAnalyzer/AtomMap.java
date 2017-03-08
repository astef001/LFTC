package LexicalAnalyzer;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class AtomMap {
	private Map<String, String> atoms = new HashMap<String, String>();
	public AtomMap(){
		atoms.put("a", "INT");
		/*atoms.put(";", "SEMICOLON");
		atoms.put("=", "ASSIGN");
		atoms.put("(", "LPAR");
		atoms.put(",", "COMMA");
		atoms.put(")", "RPAR");*/
	}
	public String getAtom(String arg){
		return atoms.get(arg);
	}
}