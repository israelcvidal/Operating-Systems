package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;


public class Algorithms {
	private ArrayList<Integer> pagesNeeded;
	private ArrayList<Integer> frames;
	private int numberOfFrames;
	private double accessTime;
	private double swapTime;
	
	public Algorithms(String pageString, int numberOfFrames){
		this.numberOfFrames = numberOfFrames;
		this.frames = new ArrayList<Integer>();
		this.pagesNeeded = new ArrayList<>();
		this.accessTime = (2*Math.pow(10, -9));
		this.swapTime = 0.002;
		StringTokenizer stkn = new StringTokenizer(pageString.replace(" ", ""), ",");
		while(stkn.hasMoreTokens()){
			pagesNeeded.add(Integer.valueOf(stkn.nextToken()));
		}
		
		
	}
	
	public ArrayList<Integer> getPagesNeeded() {
		return pagesNeeded;
	}

	public void setPagesNeeded(ArrayList<Integer> pagesNeeded) {
		this.pagesNeeded = pagesNeeded;
	}

	public ArrayList<Integer> getFrames() {
		return frames;
	}

	public void setFrames(ArrayList<Integer> frames) {
		this.frames = frames;
	}

	public ArrayList<Integer> getPages() {
		return pagesNeeded;
	}

	public void setPages(ArrayList<Integer> pages) {
		this.pagesNeeded = pages;
	}

	public int getNumberOfFrames() {
		return numberOfFrames;
	}

	public void setNumberOfFrames(int numberOfFrames) {
		this.numberOfFrames = numberOfFrames;
	}

	public void fifo(){
		int pageFaults = 0;
		double totalTime = 0;
		
		ArrayList<Integer> allocations = new ArrayList<Integer>();
		
		for (Integer page : pagesNeeded) {
			totalTime+=accessTime;
			if(!frames.contains(page)){
				pageFaults++;
				allocations.add(page);
				totalTime+=swapTime;
				if(frames.size() < numberOfFrames){
					frames.add(page);
				}
				else{
					frames.remove(0);
					frames.add(page);
				}
			}
		}
		
		double ratio = (swapTime*pageFaults)/totalTime;
		System.out.println("FIFO:");
		System.out.println("Allocations = " + allocations);
		System.out.println("Page Faults = " + pageFaults);
		System.out.println("SwapTime/TotalTime = " + ratio);
	}
	
	public void opt(){
		
	}
	
	public void lru(){
		int time = 0;
		int pageFaults = 0;
		double totalTime = 0;
		HashMap<Integer, Integer> pageCounters = new HashMap<>();
				
		ArrayList<Integer> allocations = new ArrayList<Integer>();
		
		for (Integer page : pagesNeeded) {
			totalTime+=accessTime;
//			System.out.println("FRAMES:\n" + frames);
			if(!frames.contains(page)){
				pageFaults++;
				allocations.add(page);
				totalTime+=swapTime;
				if(frames.size() < numberOfFrames){
					frames.add(page);
					pageCounters.put(page, time);
				}
				else{
					int minValue = Collections.min(pageCounters.values());
					ArrayList<Integer> keys = new ArrayList<Integer>(pageCounters.keySet());
					
					int minKey = -1;
					for (Integer key : keys) {
						if(pageCounters.get(key) == minValue){
							minKey = key;
							break;
						}
					}
//					System.out.println("remove " + minKey);
					frames.remove((Integer)minKey);
					pageCounters.remove(minKey);
					frames.add(page);
					pageCounters.put(page, time);
				}
			}
			else{
				pageCounters.put(page, time);
			}
			time++;
		}
		
		double ratio = (swapTime*pageFaults)/totalTime;
		System.out.println("LRU:");
		System.out.println("Allocations = " + allocations);
		System.out.println("Page Faults = " + pageFaults);
		System.out.println("SwapTime/TotalTime = " + ratio);
		
	}
	
	public void lfu(){
		
	}
	
	public void mfu(){
		
	}
	
}
