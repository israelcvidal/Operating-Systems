package tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import algorithms.Process;

public class FileManager {
	private PrintWriter writer;
	private ByteArrayOutputStream fakeOutput = new ByteArrayOutputStream();
	
	public FileManager(String file){
		try {
			 writer = new PrintWriter(file + ".txt", "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}		
	}
	
	public FileManager(){
		writer = new PrintWriter(fakeOutput);
	}
	
	public void write(Process process, int time){
		writer.println("processId: " + process.getProcessId() + " executed: " + time);
	}
	
	public void close(){
		writer.close();
	}
	
	
}
