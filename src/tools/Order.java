package tools;

import java.util.ArrayList;
import algorithms.Process;

public class Order {
	//Ordenate the file by the number in "index" in each line.
	
	public static ArrayList<Process> order(ArrayList<Process> processes, String field){
		@SuppressWarnings("unchecked")
		ArrayList<Process> processesCopy = (ArrayList<Process>)processes.clone(); 
		switch(field.toUpperCase()){
		//ordered by arrival time
		case("ARRIVALTIME"):
			
			for (int i = 1; i < processesCopy.size(); i++) {
	            for(int j = i ; j > 0 ; j--){
	                if(processesCopy.get(j).getArrivalTime() < processesCopy.get(j-1).getArrivalTime()){
	                	processesCopy.add(j, processesCopy.remove(j-1));	                	
	                }
	            }
	        }

			break;
		
		//ordered by process id
		case("PROCESSID"):

			for (int i = 1; i < processesCopy.size(); i++) {
	            for(int j = i ; j > 0 ; j--){
	                if(processesCopy.get(j).getProcessId() < processesCopy.get(j-1).getProcessId()){
	                	processesCopy.add(j, processesCopy.remove(j-1));	                	
	                }
	            }
	        }
	        

			break;
		
		//ordered by burst time
		case("BURSTTIME"):
			
			for (int i = 1; i < processesCopy.size(); i++) {
	            for(int j = i ; j > 0 ; j--){
	                if(processesCopy.get(j).getBurstTime() < processesCopy.get(j-1).getBurstTime()){
	                	processesCopy.add(j, processesCopy.remove(j-1));	                	
	                }
	            }
	        }

			break;
		
		default: 
			processesCopy = null;
			break;
		}
        
        return processesCopy;
	}

}
