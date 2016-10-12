package tools;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import algorithms.Process;

public class FileManager {
	private PrintWriter writer;
	
	public FileManager(String file){
		try {
			 writer = new PrintWriter(file + ".txt", "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}		
	}
	
	public void write(Process process, int time){
		writer.println("processId: " + process.getProcessId() + " executed: " + time);
	}
	
	public void close(){
		writer.close();
	}
	
	
}
