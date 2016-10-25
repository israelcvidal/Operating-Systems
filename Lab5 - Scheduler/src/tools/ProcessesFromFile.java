package tools;

import java.util.StringTokenizer;

public class ProcessesFromFile {
	public static int getArrivalTime(String line){
		return Integer.valueOf(new StringTokenizer(line, ",").nextToken());
	}
	
	public static int getProcessId(String line){
		StringTokenizer stkn = new StringTokenizer(line, ",");
		stkn.nextToken();
		return Integer.valueOf(stkn.nextToken().replace(" ", ""));
	}
	
	public static int getBurstTime(String line){
		StringTokenizer stkn = new StringTokenizer(line, ",");
		stkn.nextToken();
		stkn.nextToken();
		return Integer.valueOf(stkn.nextToken().replace(" ", ""));
	}
	
	public static int getPriority(String line){
		StringTokenizer stkn = new StringTokenizer(line, ",");
		stkn.nextToken();
		stkn.nextToken();
		stkn.nextToken();
		return Integer.valueOf(stkn.nextToken().replace(" ", ""));
	}
}
