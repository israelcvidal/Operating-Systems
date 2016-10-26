package tools;

import java.util.ArrayList;

import algorithms.AlgorithmReturn;
import algorithms.Process;
public class Statistics {
	private float totalTime, throughput, turnArround, waitingTime, responseTime, contextSwap, processesNumber, cpuPercentage;
	private ArrayList<Process> processes;
	
	public Statistics(ArrayList<Process> processes, AlgorithmReturn algorithmReturn){
		this.processes = processes;
		this.totalTime = algorithmReturn.getTotalTime();
		this.contextSwap = algorithmReturn.getContextSwap()/totalTime;
		this.processesNumber = processes.size();
		this.cpuPercentage = algorithmReturn.getCpuUse()/totalTime;
	}
	public float getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(float totalTime) {
		this.totalTime = totalTime;
	}

	public float getThroughput() {
		return throughput;
	}

	public void setThroughput(float throughput) {
		this.throughput = throughput;
	}

	public float getTurnArround() {
		return turnArround;
	}

	public void setTurnArround(float turnArround) {
		this.turnArround = turnArround;
	}

	public float getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(float waitingTime) {
		this.waitingTime = waitingTime;
	}

	public float getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(float responseTime) {
		this.responseTime = responseTime;
	}

	public float getcontextSwap() {
		return contextSwap;
	}

	public void setcontextSwap(float contextSwap) {
		this.contextSwap = contextSwap;
	}
	
	public void calculate(){
		int sum = 0;
		
		//calculating the throughput mean
		throughput = processesNumber/totalTime;
		 
		//calculating the turnArround mean
		for (Process process : processes) {
			sum += process.getBurstTime() + process.getWaitingTime();
		}
		turnArround = sum/processesNumber;

		//calculating the waiting time mean
		sum = 0;
		for (Process process : processes) {
			sum += process.getWaitingTime();
		}
		waitingTime = sum/processesNumber;

		//calculating the response time mean
		sum = 0;
		for (Process process : processes) {
			sum += process.getResponseTime();
		}
		responseTime = sum/processesNumber;
		
	}
	
	public String toString(){
		String str;
		str = "----------STATISTICS----------";
		str = str.concat("\nTOTAL TIME: " + totalTime);
		str = str.concat("\nNUMBER OF PROCESSES: " + processesNumber);
		str = str.concat("\nAVERAGE THROUGHPUT TIME: " + throughput ); 
		str = str.concat("\nAVERAGE TURNAROUND TIME: " + turnArround);
		str = str.concat("\nAVERAGE WAITING TIME: " + waitingTime);
		str = str.concat("\nAVERAGE RESPONSE TIME: " + responseTime);
		str = str.concat("\nCPU PERCENTAGE OF USE: " + cpuPercentage*100 + "%");
		str = str.concat("\nAVERAGE CONTEXT SWAP: " + contextSwap*100 + "%");

//		str = str.concat("\n" + totalTime);
//		str = str.concat("\n" + processesNumber);
//		str = str.concat("\n" + throughput ); 
//		str = str.concat("\n" + turnArround);
//		str = str.concat("\n" + waitingTime);
//		str = str.concat("\n" + responseTime);
//		str = str.concat("\n" + cpuPercentage*100 + "%");
//		str = str.concat("\n" + contextSwap*100 + "%");
		
		return str;
	}
	
}
