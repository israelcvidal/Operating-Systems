package algorithms;

public class Process {
	private int startTime, finishTime, waitingTime, processId, arrivalTime, burstTime, priority, alreadyExecuted, responseTime ;
	private int executedInARoll;
	private boolean executing;
	public Process(int processId, int arrivalTime, int burstTime, int priority){
		executedInARoll = startTime = finishTime = waitingTime = alreadyExecuted = 0;
		this.processId = processId;
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
		this.priority = priority;
		this.executing = false;		
	
	}
	public String toString(){
		return 	"processId: " + processId + ", arrivalTime: "+ arrivalTime + ", startTime: " + startTime + ", finishTime: " + finishTime + ", burstTime: " + burstTime + ", priority: " + priority + ", waitingTime: "+ waitingTime + ", responseTime: " + responseTime;

	}
	
	public void calculateResponseTime(){
		this.responseTime = this.startTime - this.arrivalTime;
	}
	
	public int getResponseTime(){
		return responseTime;
	}
	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getBurstTime() {
		return burstTime;
	}

	public void setBurstTime(int burstTime) {
		this.burstTime = burstTime;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public void setProcessId(int processId) {
		this.processId = processId;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}

	public int getWaitingTime() {
		return waitingTime;
	}

	public void calculateWaitingTime() {
		this.waitingTime = (this.finishTime - this.arrivalTime - this.burstTime);
	}

	public int getProcessId() {
		return processId;
	}	
	
	public boolean isExecuting(){
		return executing;
	}
	
	public void setExecuting(boolean bool){
		this.executing = bool;
	}
	
	public void increaseAlreadyExecuted(){
		this.alreadyExecuted++;
	}
	
//	public int getAlreadyExecuted(){
//		return alreadyExecuted;
//	}
	
	public int getCurrentBurstTime(){
		return burstTime - alreadyExecuted;
	}

	public int getProcessTime(){
		return finishTime-startTime;
	}

	public int getExecutedInARoll(){
		return executedInARoll;
	}

	
	public void increaseExecutedInARoll(){
		executedInARoll++;
	}
	
	public void resetExecutedInARoll(){
		executedInARoll = 0;
	}
}
