package LexicalAnalyzer;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringEscapeUtils;

import SintacticAnalyzer.SintacticMap;

public class Main {
	
	private static String inputFile = "C:\\Users\\Cata\\Desktop\\tests\\";
	private static AtomGenerator atoms=new AtomGenerator();
	private static SintacticMap asin;
	public static void main(String[] args) throws IOException {
		for(int i = 1; i <=9; i++){
		InputClass test = new InputClass(inputFile+i+".c");
		ArrayList<String> tmpList = new ArrayList<String>();
		tmpList = test.getList();
		atoms.generateAtoms(tmpList);
		System.out.println("Ex:"+i+"\nALEX:");
		System.out.println(atoms.getAtomsString());
		asin = new SintacticMap(atoms.getAtoms());
		System.out.println("ASIN");
		asin.doUnit();//{
			//System.out.println("Compilation succesful");
		//} else
			//System.out.println("Something wrong with the compiler");
		System.out.println();
		}
	}
}