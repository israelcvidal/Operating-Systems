package algorithms;

public class AlgorithmReturn {
	private int contextSwap, cpuUse, totalTime;
	
	public AlgorithmReturn(){
		contextSwap = cpuUse = totalTime = 0;
	}
	
	public void increaseContextSwap(){
		contextSwap++;
	}
	
	public void increaseCpuUse(){
		cpuUse++;
	}
	
	public void decreaseCpuUse(){
		cpuUse--;
	}
	
	public void setTotalTime(int total){
		totalTime = total;
	}

	public int getContextSwap() {
		return contextSwap;
	}
	
	public int getCpuUse() {
		return cpuUse;
	}
	
	public int getTotalTime() {
		return totalTime;
	}
}
