package tools;

import java.util.ArrayList;
import algorithms.Process;
public class Statistics {
	private float totalTime, throughput, turnArround, waitingTime, responseTime, contextExchange, processesNumber;
	private ArrayList<Process> processes;
	
	public Statistics(ArrayList<Process> processes, int totalTime){
		this.processes = processes;
		this.totalTime = totalTime;
		this.processesNumber = processes.size();
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

	public float getContextExchange() {
		return contextExchange;
	}

	public void setContextExchange(float contextExchange) {
		this.contextExchange = contextExchange;
	}
	
	public void calculate(){
		int sum = 0;
		
		//calculating the throughput mean
		throughput = processesNumber/totalTime;
		 
		//calculating the turnArround mean
		for (Process process : processes) {
			sum += process.getProcessTime();
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
		return "processesNumber: " + processesNumber+ ", throughput: " + throughput + ", turnaround: " + turnArround + ", waitingTime: " + waitingTime + ", responseTime: "+ responseTime;
	}
	
}
