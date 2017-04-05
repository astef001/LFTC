package LexicalAnalyzer;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringEscapeUtils;

public class Main {
	
	private static String inputFile = "C:\\Users\\Cata\\Desktop\\8.c";
	private static AtomGenerator atoms=new AtomGenerator();
	public static void main(String[] args) throws IOException {
		InputClass test = new InputClass(inputFile);
		ArrayList<String> tmpList = new ArrayList<String>();
		tmpList = test.getList();
		atoms.generateAtoms(tmpList);
		System.out.println(atoms.getAtomsString());
	}
}