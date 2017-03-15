package LexicalAnalyzer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class AtomGenerator {
	
	private final AtomMap atomMap = new AtomMap();
	private Set<String> mapKeys=atomMap.getKeySet();
			
	public AtomGenerator(){
		
	}
	
	public String returnAtoms(ArrayList<String> argArrayString){
		Iterator<String> argArrayStringIterator = argArrayString.iterator();
		Iterator<String> mapIterator = mapKeys.iterator();
		String result = new String();
		String regex,currentEl;
		while (argArrayStringIterator.hasNext()) {
			currentEl=argArrayStringIterator.next();
			while(mapIterator.hasNext()){
				regex=mapIterator.next();
				if(currentEl.matches(regex)){
					result +=" "+atomMap.getAtom(regex);
					break;
				}
			}
			if(!mapIterator.hasNext()){
				result += " ID";
			}
		}
		return result;
	}
}
