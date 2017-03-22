package LexicalAnalyzer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InputClass {
	private String inputFile;
	private ArrayList<String> outputList;
	public InputClass(String inputFile) {
		this.inputFile = inputFile;
		this.outputList = new ArrayList<String>();
		
	}
	
	private void readFile() throws IOException{
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
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
			while((tmpString = bufferedReader.readLine()) != null){
				outputList.add(tmpString);
			}
		}	
		bufferedReader.close();
	}
	
	public ArrayList<String> getList(){
		try {
			this.readFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.outputList;
	}
}

