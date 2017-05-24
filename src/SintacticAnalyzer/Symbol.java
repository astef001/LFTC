package SintacticAnalyzer;

public class Symbol {
	private String name;
	private String type;
	private boolean array;
	private int depth;
	public Symbol(String nameArg, String typeArg, boolean arrayArg, int depthArg){
		this.setName(nameArg);
		this.setType(typeArg);
		this.setArray(arrayArg);
		this.setDepth(depthArg);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isArray() {
		return array;
	}

	public void setArray(boolean array) {
		this.array = array;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
}
