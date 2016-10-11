import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import algorithms.Algorithms;
import algorithms.Process;
import tools.ProcessesFromFile;
import tools.Statistics;
public class Scheduler {

	public static void main(String[] args) {
		try {
			if(args.length < 3)
				throw new Exception("Quantidade de argumentos inválida");
			String path = args[0], algorithm = args[1], outputType = args[2], line;
			ArrayList<algorithms.Process> processes = new ArrayList<>();
			BufferedReader reader = new BufferedReader(new FileReader(path));
			int processId, arrivalTime, burstTime, priority;
			
			//skip first line from file
			reader.readLine();
			//creating an array with the processes found in the file
			while( (line = reader.readLine()) != null ){
				processId = ProcessesFromFile.getProcessId(line);
				arrivalTime = ProcessesFromFile.getArrivalTime(line);
				burstTime = ProcessesFromFile.getBurstTime(line);
				priority = ProcessesFromFile.getPriority(line);
				processes.add(new Process(processId, arrivalTime, burstTime, priority));
			}
			reader.close();
			
			switch (algorithm.toUpperCase()) {
			case "FCFS":
				if (Integer.valueOf(outputType) == 1){
					int totalTime = Algorithms.fcfs(processes);
					Statistics statistics = new Statistics(processes, totalTime);
					statistics.calculate();
					System.out.println(statistics);
				}else if(Integer.valueOf(outputType) == 2){
					
				}else throw new Exception("Tipo de saída inválida. Tente 1 ou 2!");
				break;

			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}	

}
