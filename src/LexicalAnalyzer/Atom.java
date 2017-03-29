package LexicalAnalyzer;

public class Atom {
	private String value;
	private String id;
	private int line;
	public Atom(String value,String id,int line){
		this.value=value;
		this.id=id;
		this.line=line;
	}
	public String getValue(){
		return this.value;
	}
	public String getId(){
		return this.id;
	}
	public int getLine(){
		return this.line;
	}
	public String toString(){
		if(this.id=="ID"){
			return this.id+':'+this.value;
		} else {
			return this.id;
		}
	}
}
