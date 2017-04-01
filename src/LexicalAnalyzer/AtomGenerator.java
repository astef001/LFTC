package LexicalAnalyzer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AtomGenerator {
	
	private final AtomMap atomMap = new AtomMap();
	private Set<String> mapKeys=atomMap.getKeySet();
	private ArrayList<Atom> generatedAtoms;
	public AtomGenerator(){
	}
	
	public ArrayList<Atom> getAtoms(){
		return this.generatedAtoms;
	}
	
	public String getAtomsString(){
		
		Iterator<Atom> generatedAtomsIterator = generatedAtoms.iterator();
		String result=new String("");
		Atom currentAtom;
		int line=0;
		result+="@Line:" + (line + 1) + " "; //hardcoded to make the @Line work
		while(generatedAtomsIterator.hasNext()){
			currentAtom=generatedAtomsIterator.next();
			if (line!=currentAtom.getLine()){
				//removed result += "\n" (proful a zis sa fie doar un rand intreg).
				result+=" @Line:" + (line + 2) + " "; //hardcoded to make the @Line work
				line=currentAtom.getLine();
			}
			result += currentAtom.toString()+" ";
		}
		result=result.replaceAll("[ \t]+"," ");
		return result;
	}
	
	public void generateAtoms(ArrayList<String> argArrayString){
		Iterator<String> argArrayStringIterator = argArrayString.iterator();
		Iterator<String> mapIterator = mapKeys.iterator();
		ArrayList<Atom> generatedAtoms=new ArrayList<Atom>(); 
		String regex,currentEl,tempString;
		Matcher m;
		Pattern p = null;
		while (argArrayStringIterator.hasNext()) {
			currentEl=argArrayStringIterator.next();
			int line=argArrayString.indexOf(currentEl);
			while(currentEl.length()>0){
				currentEl=atomMap.removeIgnored(currentEl);
				mapIterator = mapKeys.iterator();
				while(mapIterator.hasNext()){
					regex=mapIterator.next();
					p=Pattern.compile("^"+regex);
					m=p.matcher(currentEl);
					if(m.find()){
						tempString=currentEl.substring(0, m.end());
						generatedAtoms.add(new Atom(tempString,atomMap.getAtomId(regex), line));	
						currentEl = currentEl.substring(m.end());
						break;
					}
				}
			}
//			generatedAtoms.add(new Atom("newline","\n",line));
 		}
		generatedAtoms.add(new Atom("END", "", argArrayString.size()));
		this.generatedAtoms=generatedAtoms;
	}
}
