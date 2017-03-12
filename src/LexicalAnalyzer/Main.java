package LexicalAnalyzer;

import java.io.IOException;

public class Main {
	
	private static String inputFile = "C:\\Users\\Cata\\Desktop\\test.c";
	public static void main(String[] args) {
		InputClass test = new InputClass(inputFile);
		try {
			System.out.println(test.readFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
