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
	
	public String returnAtoms(ArrayList<String> argArrayString){
		Iterator<String> argArrayStringIterator = argArrayString.iterator();
		Iterator<String> mapIterator = mapKeys.iterator();
		String result = new String();
		String regex,currentEl,tempString;
		Matcher m;
		Pattern p = null;
		while (argArrayStringIterator.hasNext()) {
			currentEl=argArrayStringIterator.next();
			m=p.matcher(currentEl);
			while(currentEl!=""){
				while(mapIterator.hasNext()){
					regex="^"+mapIterator.next();
					p=Pattern.compile(regex);
					m=p.matcher(currentEl);
					if(m.find()){
						result +=" "+atomMap.getAtom(regex);
						tempString=currentEl.substring(0, m.end());
						currentEl = currentEl.substring(m.end());
						break;
					}
				}
			if(!mapIterator.hasNext()){
				result += " ID";
			}
		}
		}
		return result;
	}
}
