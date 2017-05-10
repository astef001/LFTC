package LexicalAnalyzer;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringEscapeUtils;

import SintacticAnalyzer.SintacticMap;

public class Main {
	
	private static String inputFile = "C:\\Users\\Alex\\Desktop\\tests\\8.c";
	private static AtomGenerator atoms=new AtomGenerator();
	private static SintacticMap asin;
	public static void main(String[] args) throws IOException {
		InputClass test = new InputClass(inputFile);
		ArrayList<String> tmpList = new ArrayList<String>();
		tmpList = test.getList();
		atoms.generateAtoms(tmpList);
		asin = new SintacticMap(atoms.getAtoms());
		//System.out.println(atoms.getAtomsString());
	}
}