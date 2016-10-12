package algorithms;
import tools.*; 
import java.util.ArrayList;

public class Algorithms {
	public static int fcfs(ArrayList<Process> processes){

		ArrayList<Process> sortedByArrival = Sort.sort(processes, "arrivalTime");
		ArrayList<Process> ready = new ArrayList<Process>();
		ArrayList<Process> running = new ArrayList<Process>();
		Process process;
		
		int timer = 0;
		while(  !sortedByArrival.isEmpty() || !ready.isEmpty() || !running.isEmpty()){

			if(!sortedByArrival.isEmpty()){
				if(sortedByArrival.get(0).getArrivalTime() <= timer){
					ready.add(sortedByArrival.remove(0));
				}
			}
			
			if(!ready.isEmpty()){
				if(running.isEmpty()){
					running.add(ready.remove(0));
				}
			}
			
			if(!running.isEmpty()){
				process = running.get(0);
				if(!process.isExecuting()){
					process.setExecuting(true);
					process.setStartTime(timer);
					process.calculateResponseTime();
					process.increaseAlreadyExecuted();
				}
				else{
					if(process.getCurrentBurstTime() > 0){
						process.increaseAlreadyExecuted();
					}
					else{
						process.setFinishTime(timer);
						process.calculateWaitingTime();
						running.remove(0);
						//decrease timer so the next process can start at the same time the last one finished
						timer--;			
					}
				}
			}
			
			timer++;
			 
		}
//		for (Process aux : processes) {
//			System.out.println(aux);
//		}
		
		return timer;
	}

	public static int fcfs(ArrayList<Process> processes, FileManager writer){

		ArrayList<Process> sortedByArrival = Sort.sort(processes, "arrivalTime");
		ArrayList<Process> ready = new ArrayList<Process>();
		ArrayList<Process> running = new ArrayList<Process>();
		Process process;
		
		int timer = 0;
		while(  !sortedByArrival.isEmpty() || !ready.isEmpty() || !running.isEmpty()){

			if(!sortedByArrival.isEmpty()){
				if(sortedByArrival.get(0).getArrivalTime() <= timer){
					ready.add(sortedByArrival.remove(0));
				}
			}
			
			if(!ready.isEmpty()){
				if(running.isEmpty()){
					running.add(ready.remove(0));
				}
			}
			
			if(!running.isEmpty()){
				process = running.get(0);
				if(!process.isExecuting()){
					process.setExecuting(true);
					process.setStartTime(timer);
					process.calculateResponseTime();
					process.increaseAlreadyExecuted();
				}
				else{
					if(process.getCurrentBurstTime() > 0){
						process.increaseAlreadyExecuted();
					}
					else{
						writer.write(process, process.getBurstTime());
						process.setFinishTime(timer);
						process.calculateWaitingTime();
						running.remove(0);
						//decrease timer so the next process can start at the same time the last one finished
						timer--;
						
						
					}
				}
			}
			
			timer++;
			 
		}
//		for (Process aux : processes) {
//			//System.out.println("id: " + process.getProcessId() + ", startTime: " + process.getStartTime() + ", finishTime: " + process.getFinishTime());
//			System.out.println(aux);
//		}
		
		return timer;
	}

	public static int sjf(ArrayList<Process> processes){
		ArrayList<Process> sortedByArrival = Sort.sort(processes, "arrivalTime");
		ArrayList<Process> ready = new ArrayList<Process>();
		ArrayList<Process> running = new ArrayList<Process>();
		Process process;
		
		int timer = 0;
		while(  !sortedByArrival.isEmpty() || !ready.isEmpty() || !running.isEmpty()){
			
			while(!sortedByArrival.isEmpty() && sortedByArrival.get(0).getArrivalTime() <= timer){
					ready.add(sortedByArrival.remove(0));
			}
			
			if(!ready.isEmpty()){
				if(running.isEmpty()){
					int index = 0;
					//getting the process with minimum burstTime. 
					for (int i = 0; i < ready.size(); i++) {
						if(ready.get(index).getBurstTime() > ready.get(i).getBurstTime()   )
							index = i;
					}
					running.add(ready.remove(index));
				}
			}
			
			if(!running.isEmpty()){
				process = running.get(0);
				if(!process.isExecuting()){
					process.setExecuting(true);
					process.setStartTime(timer);
					process.calculateResponseTime();
					process.increaseAlreadyExecuted();
				}
				else{
					if(process.getCurrentBurstTime() > 0){
						process.increaseAlreadyExecuted();
					}
					else{
						process.setFinishTime(timer);
						process.calculateWaitingTime();
						running.remove(0);
						//decrease timer so the next process can start at the same time the last one finished
						timer--;
					}
				}
			}
			timer++;	 
		}
//		for (Process aux : processes) {
//			System.out.println(aux);
//		}
		
		return timer;
	}
		
	public static int sjf(ArrayList<Process> processes, FileManager writer){
		ArrayList<Process> sortedByArrival = Sort.sort(processes, "arrivalTime");
		ArrayList<Process> ready = new ArrayList<Process>();
		ArrayList<Process> running = new ArrayList<Process>();
		Process process;
		
		int timer = 0;
		while(  !sortedByArrival.isEmpty() || !ready.isEmpty() || !running.isEmpty()){
			
			while(!sortedByArrival.isEmpty() && sortedByArrival.get(0).getArrivalTime() <= timer){
					ready.add(sortedByArrival.remove(0));
			}
			
			if(!ready.isEmpty()){
				if(running.isEmpty()){
					int index = 0;
					//getting the process with minimum burstTime. 
					for (int i = 0; i < ready.size(); i++) {
						if(ready.get(index).getBurstTime() > ready.get(i).getBurstTime()   )
							index = i;
					}
					running.add(ready.remove(index));
				}
			}
			
			if(!running.isEmpty()){
				process = running.get(0);
				if(!process.isExecuting()){
					process.setExecuting(true);
					process.setStartTime(timer);
					process.calculateResponseTime();
					process.increaseAlreadyExecuted();
				}
				else{
					if(process.getCurrentBurstTime() > 0){
						process.increaseAlreadyExecuted();
					}
					else{
						writer.write(process, process.getBurstTime());
						process.setFinishTime(timer);
						process.calculateWaitingTime();
						running.remove(0);
						//decrease timer so the next process can start at the same time the last one finished
						timer--;
					}
				}
			}
			timer++;	 
		}
//		for (Process aux : processes) {
//			System.out.println(aux);
//		}
		
		return timer;
	}

	public static int sjfp(ArrayList<Process> processes){
		ArrayList<Process> sortedByArrival = Sort.sort(processes, "arrivalTime");
		ArrayList<Process> ready = new ArrayList<Process>();
		ArrayList<Process> running = new ArrayList<Process>();
		Process process;
		
		int timer = 0;
		while(  !sortedByArrival.isEmpty() || !ready.isEmpty() || !running.isEmpty()){
			
			while(!sortedByArrival.isEmpty() && sortedByArrival.get(0).getArrivalTime() <= timer){
					ready.add(sortedByArrival.remove(0));
			}
			
			if(!ready.isEmpty()){
				
				int index = 0;
				//getting the process with minimum burstTime. 
				for (int i = 0; i < ready.size(); i++) {
					if(ready.get(index).getCurrentBurstTime() > ready.get(i).getCurrentBurstTime())
						index = i;
				}
				if(running.isEmpty()){
					running.add(ready.remove(index));
				}
				else{
					if(running.get(0).getCurrentBurstTime() != 0 && running.get(0).getCurrentBurstTime() > ready.get(index).getCurrentBurstTime()){
						running.add(ready.remove(index));
						ready.add(0,running.remove(0));
					}
				}
			}
			
			if(!running.isEmpty()){
				process = running.get(0);
				if(!process.isExecuting()){
					process.setExecuting(true);
					process.setStartTime(timer);
					process.calculateResponseTime();
					process.increaseAlreadyExecuted();
				}
				else{
					if(process.getCurrentBurstTime() > 0){
						process.increaseAlreadyExecuted();
					}
					else{
						process.setFinishTime(timer);
						process.calculateWaitingTime();
						running.remove(0);
						//decrease timer so the next process can start at the same time the last one finished
						timer--;
					}
				}
			}
			timer++;	 
		}
//		for (Process aux : processes) {
//			System.out.println(aux);
//		}
		
		return timer;
	}

	public static int sjfp(ArrayList<Process> processes, FileManager writer) {
		ArrayList<Process> sortedByArrival = Sort.sort(processes, "arrivalTime");
		ArrayList<Process> ready = new ArrayList<Process>();
		ArrayList<Process> running = new ArrayList<Process>();
		Process process;
		
		int timer = 0;
		while(  !sortedByArrival.isEmpty() || !ready.isEmpty() || !running.isEmpty()){
			
			while(!sortedByArrival.isEmpty() && sortedByArrival.get(0).getArrivalTime() <= timer){
					ready.add(sortedByArrival.remove(0));
			}
			
			if(!ready.isEmpty()){
				
				int index = 0;
				//getting the process with minimum burstTime. 
				for (int i = 0; i < ready.size(); i++) {
					if(ready.get(index).getCurrentBurstTime() > ready.get(i).getCurrentBurstTime())
						index = i;
				}
				if(running.isEmpty()){
					running.add(ready.remove(index));
				}
				else{
					if(running.get(0).getCurrentBurstTime() != 0 && running.get(0).getCurrentBurstTime() > ready.get(index).getCurrentBurstTime()){
						running.add(ready.remove(index));  
						writer.write( running.get(0), running.get(0).getExecutedInARoll());
						running.get(0).resetExecutedInARoll();
						ready.add(0,running.remove(0));	
					}
					
				}
			}
			
			if(!running.isEmpty()){
				process = running.get(0);
				if(!process.isExecuting()){
					process.setExecuting(true);
					process.setStartTime(timer);
					process.calculateResponseTime();
					process.increaseAlreadyExecuted();
					process.increaseExecutedInARoll();

				}
				else{
					if(process.getCurrentBurstTime() > 0){
						process.increaseAlreadyExecuted();
						process.increaseExecutedInARoll();
					}
					else{
						process.setFinishTime(timer);
						process.calculateWaitingTime();
						running.remove(0);
						writer.write(process, process.getExecutedInARoll());
						process.resetExecutedInARoll();
						//decrease timer so the next process can start at the same time the last one finished
						timer--;
					}
				}
			}
			timer++;	 
		}
//		for (Process aux : processes) {
//			System.out.println(aux);
//		}
		
		return timer;
	}

	public static int priority(ArrayList<Process> processes){
		ArrayList<Process> sortedByArrival = Sort.sort(processes, "arrivalTime");
		ArrayList<Process> ready = new ArrayList<Process>();
		ArrayList<Process> running = new ArrayList<Process>();
		Process process;
		
		int timer = 0;
		while(  !sortedByArrival.isEmpty() || !ready.isEmpty() || !running.isEmpty()){
			
			while(!sortedByArrival.isEmpty() && sortedByArrival.get(0).getArrivalTime() <= timer){
					ready.add(sortedByArrival.remove(0));
			}
			
			if(!ready.isEmpty()){
				if(running.isEmpty()){
					int index = 0;
					//getting the process with maximum priority. 
					for (int i = 0; i < ready.size(); i++) {
						if(ready.get(index).getPriority() > ready.get(i).getPriority() )
							index = i;
					}
					running.add(ready.remove(index));
				}
			}
			
			if(!running.isEmpty()){
				process = running.get(0);
				if(!process.isExecuting()){
					process.setExecuting(true);
					process.setStartTime(timer);
					process.calculateResponseTime();
					process.increaseAlreadyExecuted();
				}
				else{
					if(process.getCurrentBurstTime() > 0){
						process.increaseAlreadyExecuted();
					}
					else{
						process.setFinishTime(timer);
						process.calculateWaitingTime();
						running.remove(0);
						//decrease timer so the next process can start at the same time the last one finished
						timer--;
					}
				}
			}
			timer++;	 
		}
//		for (Process aux : processes) {
//			System.out.println(aux);
//		}
		
		return timer;
	}	

	public static int priority(ArrayList<Process> processes, FileManager writer){
		ArrayList<Process> sortedByArrival = Sort.sort(processes, "arrivalTime");
		ArrayList<Process> ready = new ArrayList<Process>();
		ArrayList<Process> running = new ArrayList<Process>();
		Process process;
		
		int timer = 0;
		while(  !sortedByArrival.isEmpty() || !ready.isEmpty() || !running.isEmpty()){
			
			while(!sortedByArrival.isEmpty() && sortedByArrival.get(0).getArrivalTime() <= timer){
					ready.add(sortedByArrival.remove(0));
			}
			
			if(!ready.isEmpty()){
				if(running.isEmpty()){
					int index = 0;
					//getting the process with maximum priority. 
					for (int i = 0; i < ready.size(); i++) {
						if(ready.get(index).getPriority() > ready.get(i).getPriority()   )
							index = i;
					}
					running.add(ready.remove(index));
				}
			}
			
			if(!running.isEmpty()){
				process = running.get(0);
				if(!process.isExecuting()){
					process.setExecuting(true);
					process.setStartTime(timer);
					process.calculateResponseTime();
					process.increaseAlreadyExecuted();
				}
				else{
					if(process.getCurrentBurstTime() > 0){
						process.increaseAlreadyExecuted();
					}
					else{
						writer.write(process, process.getBurstTime());
						process.setFinishTime(timer);
						process.calculateWaitingTime();
						running.remove(0);
						//decrease timer so the next process can start at the same time the last one finished
						timer--;
					}
				}
			}
			timer++;	 
		}
//		for (Process aux : processes) {
//			System.out.println(aux);
//		}
		
		return timer;
	}	

	public static int priorityp(ArrayList<Process> processes){
		ArrayList<Process> sortedByArrival = Sort.sort(processes, "arrivalTime");
		ArrayList<Process> ready = new ArrayList<Process>();
		ArrayList<Process> running = new ArrayList<Process>();
		Process process;
		
		int timer = 0;
		while(  !sortedByArrival.isEmpty() || !ready.isEmpty() || !running.isEmpty()){
			
			while(!sortedByArrival.isEmpty() && sortedByArrival.get(0).getArrivalTime() <= timer){
					ready.add(sortedByArrival.remove(0));
			}
			
			if(!ready.isEmpty()){
				
				int index = 0;
				//getting the process with maximum priority. 
				for (int i = 0; i < ready.size(); i++) {
					if(ready.get(index).getPriority() > ready.get(i).getPriority())
						index = i;
				}
				if(running.isEmpty()){
					running.add(ready.remove(index));
				}
				else{
					if(running.get(0).getCurrentBurstTime() != 0  && running.get(0).getPriority() > ready.get(index).getPriority()){
						running.add(ready.remove(index));
						ready.add(0,running.remove(0));
					}
				}
			}
			
			if(!running.isEmpty()){
				process = running.get(0);
				if(!process.isExecuting()){
					process.setExecuting(true);
					process.setStartTime(timer);
					process.calculateResponseTime();
					process.increaseAlreadyExecuted();
				}
				else{
					if(process.getCurrentBurstTime() > 0){
						process.increaseAlreadyExecuted();
					}
					else{
						process.setFinishTime(timer);
						process.calculateWaitingTime();
						running.remove(0);
						//decrease timer so the next process can start at the same time the last one finished
						timer--;
					}
				}
			}
			timer++;	 
		}
//		for (Process aux : processes) {
//			System.out.println(aux);
//		}
		
		return timer;
	}

	public static int priorityp(ArrayList<Process> processes, FileManager writer) {
		ArrayList<Process> sortedByArrival = Sort.sort(processes, "arrivalTime");
		ArrayList<Process> ready = new ArrayList<Process>();
		ArrayList<Process> running = new ArrayList<Process>();
		Process process;
		
		int timer = 0;
		while(  !sortedByArrival.isEmpty() || !ready.isEmpty() || !running.isEmpty()){
			
			while(!sortedByArrival.isEmpty() && sortedByArrival.get(0).getArrivalTime() <= timer){
					ready.add(sortedByArrival.remove(0));
			}
			
			if(!ready.isEmpty()){
				
				int index = 0;
				//getting the process with maximum priority. 
				for (int i = 0; i < ready.size(); i++) {
					if(ready.get(index).getPriority() > ready.get(i).getPriority())
						index = i;
				}
				if(running.isEmpty()){
					running.add(ready.remove(index));
				}
				else{
					if(running.get(0).getCurrentBurstTime() != 0 && running.get(0).getPriority() > ready.get(index).getPriority()){
						running.add(ready.remove(index));
						writer.write( running.get(0), running.get(0).getExecutedInARoll());
						running.get(0).resetExecutedInARoll();
						ready.add(0,running.remove(0));	
					}
					
				}
			}
			
			if(!running.isEmpty()){
				process = running.get(0);
				if(!process.isExecuting()){
					process.setExecuting(true);
					process.setStartTime(timer);
					process.calculateResponseTime();
					process.increaseAlreadyExecuted();
					process.increaseExecutedInARoll();

				}
				else{
					if(process.getCurrentBurstTime() > 0){
						process.increaseAlreadyExecuted();
						process.increaseExecutedInARoll();
					}
					else{
						process.setFinishTime(timer);
						process.calculateWaitingTime();
						running.remove(0);
						writer.write(process, process.getExecutedInARoll());
						process.resetExecutedInARoll();
						//decrease timer so the next process can start at the same time the last one finished
						timer--;
					}
				}
			}
		
			timer++;	 
		}
//		for (Process aux : processes) {
//			System.out.println(aux);
//		}
		
		return timer;
	}

	public static int rr(ArrayList<Process> processes, int timeQuantum){
		ArrayList<Process> sortedByArrival = Sort.sort(processes, "arrivalTime");
		ArrayList<Process> ready = new ArrayList<Process>();
		ArrayList<Process> running = new ArrayList<Process>();
		Process process;
		
		int timer = 0;
		while(  !sortedByArrival.isEmpty() || !ready.isEmpty() || !running.isEmpty()){
			
			while(!sortedByArrival.isEmpty() && sortedByArrival.get(0).getArrivalTime() <= timer){
					ready.add(sortedByArrival.remove(0));
			}
			
			if(!ready.isEmpty()){
				if(running.isEmpty()){
					running.add(ready.remove(0));
				}
				else{
					if(running.get(0).getCurrentBurstTime() != 0  && running.get(0).getExecutedInARoll() >= timeQuantum){
						running.get(0).resetExecutedInARoll();
						running.add(ready.remove(0));
						ready.add(running.remove(0));
					}
				}
			}
			
			if(!running.isEmpty()){
				process = running.get(0);
				if(!process.isExecuting()){
					process.setExecuting(true);
					process.setStartTime(timer);
					process.calculateResponseTime();
					process.increaseAlreadyExecuted();
					process.increaseExecutedInARoll();
				}
				else{
					if(process.getCurrentBurstTime() > 0){
						process.increaseAlreadyExecuted();
						process.increaseExecutedInARoll();
					}
					else{
						process.setFinishTime(timer);
						process.calculateWaitingTime();
						process.resetExecutedInARoll();
						running.remove(0);
						//decrease timer so the next process can start at the same time the last one finished
						timer--;
					}
				}
			}
//			if(!running.isEmpty()){
//				System.out.println("tempo " + timer + " executed " +  running.get(0).getProcessId());
//			}
			timer++;	 
		}
//		for (Process aux : processes) {
//			System.out.println(aux);
//		}
		
		return timer;
	}

	public static int rr(ArrayList<Process> processes, int timeQuantum, FileManager writer) {
		ArrayList<Process> sortedByArrival = Sort.sort(processes, "arrivalTime");
		ArrayList<Process> ready = new ArrayList<Process>();
		ArrayList<Process> running = new ArrayList<Process>();
		Process process;
		
		int timer = 0;
		while(  !sortedByArrival.isEmpty() || !ready.isEmpty() || !running.isEmpty()){
		
			while(!sortedByArrival.isEmpty() && sortedByArrival.get(0).getArrivalTime() <= timer){
					ready.add(sortedByArrival.remove(0));
			}
			
			if(!ready.isEmpty()){
				
				int index = 0;
				//getting the process with maximum priority. 
				for (int i = 0; i < ready.size(); i++) {
					if(ready.get(index).getPriority() > ready.get(i).getPriority())
						index = i;
				}
				if(running.isEmpty()){
					running.add(ready.remove(index));
				}
				else{
					if(running.get(0).getCurrentBurstTime() != 0  && running.get(0).getExecutedInARoll() >= timeQuantum){
						running.add(ready.remove(0));
						writer.write( running.get(0), running.get(0).getExecutedInARoll());
						running.get(0).resetExecutedInARoll();
						ready.add(running.remove(0));
					}
				}
			}
			
			if(!running.isEmpty()){
				process = running.get(0);
				if(!process.isExecuting()){
					process.setExecuting(true);
					process.setStartTime(timer);
					process.calculateResponseTime();
					process.increaseAlreadyExecuted();
					process.increaseExecutedInARoll();

				}
				else{
					if(process.getCurrentBurstTime() > 0){
						process.increaseAlreadyExecuted();
						process.increaseExecutedInARoll();
					}
					else{
						process.setFinishTime(timer);
						process.calculateWaitingTime();
						running.remove(0);
						writer.write(process, process.getExecutedInARoll());
						process.resetExecutedInARoll();
						//decrease timer so the next process can start at the same time the last one finished
						timer--;
					}
				}
			}
			timer++;	 
		}
//		for (Process aux : processes) {
//			System.out.println(aux);
//		}
		
		return timer;
	}

	
	
	
}

