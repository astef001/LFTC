package LexicalAnalyzer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringEscapeUtils;

public class Atom {
	private String value;
	private String id;
	private int line;
	private Object parser;
	
	public Atom(String value,String id,int line){
		this.value=value;
		this.id=id;
		this.line=line;
		this.parser = new Object();
		this.parser = value;
	}
	
	private double returnFloat(){
		this.parser = Double.parseDouble(this.value);
		return (double)this.parser;
	}
	
	private long returnInt(){
		this.parser = Long.decode(this.value);
		return (long)this.parser;
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
	
	//Had no solution but make a case
	@Override
	public String toString(){
		Pattern p = Pattern.compile("^CT_");
		Matcher m = p.matcher(id);
		if(this.id == "ID" || m.find()){
			switch(this.id){
			case "CT_INT": 
				return this.id+':' + this.returnInt();
			case "CT_REAL":
				return this.id+':' + this.returnFloat();
			case "CT_CHAR":
				p = Pattern.compile("^\\\'.*\\\'$");
				m = p.matcher(this.value);
				if(m.find())
					this.value = this.value.substring(m.start() + 1, m.end() - 1);
				return this.id+':' + StringEscapeUtils.unescapeJava(this.value);
			case "CT_STRING":
				p = Pattern.compile("^\\\".*\\\"$");
				m = p.matcher(this.value);
				if(m.find())
					this.value = this.value.substring(m.start() + 1, m.end() - 1);
				return this.id+':' + StringEscapeUtils.unescapeJava(this.value);
			default:
				return this.id + ":" + this.value;
			}
		}
		else{
			return this.id;
		}
	}
	
	/*public String toString(){
		Pattern p = Pattern.compile("^CT_");
		Matcher m = p.matcher(id);
		if(this.id=="ID" || m.find()){
			p = Pattern.compile("^\\\".*\\\"$");
			m = p.matcher(this.value);
			if(m.find())
				this.value = this.value.substring(m.start() + 1, m.end() - 1);
			return this.id+':' + StringEscapeUtils.unescapeJava(this.value);
		} else {
			return this.id;
		}
	}
	*/
	
}
