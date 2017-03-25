package LexicalAnalyzer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AtomGenerator {
	
	private final AtomMap atomMap = new AtomMap();
	private Set<String> mapKeys=atomMap.getKeySet();
			
	public AtomGenerator(){
		
	}
	
	private String addSpace(String result){
		if(result.length()>0 && !result.substring(result.length()-1).matches("[ \n]+")){
			result +=" ";
		}
		return result;
	}
	
	public String returnAtoms(ArrayList<String> argArrayString){
		Iterator<String> argArrayStringIterator = argArrayString.iterator();
		Iterator<String> mapIterator = mapKeys.iterator();
		String result = new String("");
		String regex,currentEl,tempString;
		Matcher m;
		Pattern p = null;
		while (argArrayStringIterator.hasNext()) {
			currentEl=argArrayStringIterator.next();
			while(currentEl.length()>0){
				mapIterator = mapKeys.iterator();
				while(mapIterator.hasNext()){
					regex=mapIterator.next();
					p=Pattern.compile("^"+regex);
					m=p.matcher(currentEl);
					if(m.find()){
						result = addSpace(result);
						result +=atomMap.getAtom(regex);
						currentEl = currentEl.substring(m.end());
						break;
					}
				}
			if(!mapIterator.hasNext()){
				Pattern idRegex=Pattern.compile("^[a-zA-Z_][a-zA-Z0-9_]*");
				Matcher idMatcher=idRegex.matcher(currentEl);
				if(idMatcher.find()){
					tempString=currentEl.substring(0, idMatcher.end());
					result = addSpace(result);
					result += "ID:"+ tempString;
					currentEl = currentEl.substring(idMatcher.end());
				}
			}
			
		}
			result += "\n";
 		}
		result=result.replaceAll("[ \t]+"," ");
		result+="END";
		return result;
	}
}
