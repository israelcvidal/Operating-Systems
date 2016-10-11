package algorithms;
import tools.*; 
import java.util.ArrayList;

public class Algorithms {
	public static int fcfs(ArrayList<Process> processes){
		ArrayList<Process> orderedByArrival = Sort.order(processes, "arrivalTime");
		ArrayList<Process> ready = new ArrayList<Process>();
		ArrayList<Process> running = new ArrayList<Process>();
		Process process;
		
		int timer = 0;
		while(  !orderedByArrival.isEmpty() || !ready.isEmpty() || !running.isEmpty()){

			if(!orderedByArrival.isEmpty()){
				if(orderedByArrival.get(0).getArrivalTime() <= timer){
					ready.add(orderedByArrival.remove(0));
				}
			}
			
			if(!ready.isEmpty()){
				if(running.isEmpty()){
					running.add(ready.remove(0));
				}
				//increasing waitingTime for those processes in the ready queue
				for (Process processAux : ready) {
					processAux.increaseWaitingTime();
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
					if((process.getBurstTime() - process.getAlreadyExecuted()) > 0){
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
		for (Process aux : processes) {
			//System.out.println("id: " + process.getProcessId() + ", startTime: " + process.getStartTime() + ", finishTime: " + process.getFinishTime());
			System.out.println(aux);
		}
		
		return timer;
	}
}
