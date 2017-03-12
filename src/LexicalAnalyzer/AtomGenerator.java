package LexicalAnalyzer;


public class AtomGenerator {
	
	private final AtomMap atomMap = new AtomMap();
	public AtomGenerator(){
		
	}
	
	private String returnAtoms(String[] argArrayString){
		long index;
		String returnString = "";
		for(String s : argArrayString){
			returnString += atomMap.getAtom(s);
		}
		return returnString;
	}
}
