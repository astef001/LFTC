package LexicalAnalyzer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InputClass {
	private String inputFile;

	public InputClass(String inputFile) {
		this.inputFile = inputFile;
	}
	
	private String[] readFile() throws IOException{
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		String outputString = "";
		try {
			fileReader = new FileReader(this.inputFile);
		    bufferedReader = new BufferedReader(fileReader, 50000);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch(@SuppressWarnings("hiding") IOException ex){
			ex.printStackTrace();
		}
		
		if(bufferedReader.ready()){
			String tmpString = "";
			while((tmpString = bufferedReader.readLine()) != ""){
				outputString += " " + tmpString;
			}
		}
		bufferedReader.close();
		return outputString.split("\".+\" | \\s+");
	}
}

