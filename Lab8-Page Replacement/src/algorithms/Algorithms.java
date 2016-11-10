package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;


public class Algorithms {
	private ArrayList<Integer> pagesNeeded;
	private ArrayList<Integer> frames;
	private int numberOfFrames;
	private double accessTime;
	private double swapTime;
	
	public Algorithms(String pageString, int numberOfFrames){
		this.numberOfFrames = numberOfFrames;
		this.pagesNeeded = new ArrayList<>();
		this.accessTime = 0.0000002;
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
		this.frames = new ArrayList<Integer>();
		int pageFaults = 0;
		double totalTime = 0;
		
		System.out.println("FIFO:");
		System.out.println("Frames:");
		ArrayList<Integer> allocations = new ArrayList<Integer>();
		
		for (Integer page : pagesNeeded) {
			System.out.println(frames);
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
		System.out.println("Pages Needed: " + pagesNeeded);
		System.out.println("Allocations = " + allocations);
		System.out.println("Page Faults = " + pageFaults);
		System.out.println("SwapTime/TotalTime = " + ratio);
	}
	
	public void opt(){
		this.frames = new ArrayList<Integer>();
		int pageFaults = 0;
		double totalTime = 0;
				
		ArrayList<Integer> allocations = new ArrayList<Integer>();
		int i = -1;
		System.out.println("OPT:");
		System.out.println("Frames: ");
		for (Integer page : pagesNeeded) {
			System.out.println(frames);
			i++;
			totalTime+=accessTime;

			if(!frames.contains(page)){
				pageFaults++;
				allocations.add(page);
				totalTime+=swapTime;
				if(frames.size() < numberOfFrames){
					frames.add(page);
				}
				else{
					HashMap<Integer, Integer> pageDistance = new HashMap<>();
					for (Integer frame : frames) {
						for (int k = i; k < pagesNeeded.size(); k++){
							if(frame == pagesNeeded.get(k)){
								pageDistance.put(pagesNeeded.get(k), k);
								break;
							}
							else pageDistance.put(frame,1000);
						}
					}
					int optimalKey = frames.get(0);

					if(!pageDistance.isEmpty()){
						 int mostDistant = Collections.max(pageDistance.values());
						 ArrayList<Integer> keys = new ArrayList<Integer>(pageDistance.keySet());
							
							for (Integer key : keys) {
								if(pageDistance.get(key) == mostDistant){
									optimalKey = key;
									break;
								}
							}
					}
					frames.remove((Integer)optimalKey);
					frames.add(page);

				}
			}
		}
		
		double ratio = (swapTime*pageFaults)/totalTime;
		System.out.println("Pages Needed: " + pagesNeeded);
		System.out.println("Allocations = " + allocations);
		System.out.println("Page Faults = " + pageFaults);
		System.out.println("SwapTime/TotalTime = " + ratio);
		
	}
	
	public void lru(){
		this.frames = new ArrayList<Integer>();
		int time = 0;
		int pageFaults = 0;
		double totalTime = 0;
		HashMap<Integer, Integer> pageCounters = new HashMap<>();
				
		ArrayList<Integer> allocations = new ArrayList<Integer>();
		System.out.println("LRU:");
		System.out.println("Frames:");
		for (Integer page : pagesNeeded) {
			System.out.println(frames);
			totalTime+=accessTime;

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
		System.out.println("Pages Needed: " + pagesNeeded);
		System.out.println("Allocations = " + allocations);
		System.out.println("Page Faults = " + pageFaults);
		System.out.println("SwapTime/TotalTime = " + ratio);
		
	}
	
	public void lfu(){
		this.frames = new ArrayList<Integer>();
		int pageFaults = 0;
		double totalTime = 0;
		HashMap<Integer, Integer> pageCounters = new HashMap<>();
				
		ArrayList<Integer> allocations = new ArrayList<Integer>();
		System.out.println("LFU:");
		System.out.println("Frames:");
		for (Integer page : pagesNeeded) {
			System.out.println(frames);
			totalTime+=accessTime;
			if(!frames.contains(page)){
				pageFaults++;
				allocations.add(page);
				totalTime+=swapTime;
				if(frames.size() < numberOfFrames){
					frames.add(page);
					pageCounters.put(page, 1);
				}
				else{
					ArrayList<Integer> count = new ArrayList<Integer>();
					for (Integer frame : frames) {
						if(pageCounters.containsKey(frame)){
							count.add(pageCounters.get(frame));
						}
						else System.out.println("ERRO!!!!");
					}
				
					int minValue = Collections.min(count);
					int minKey = -1;
					for (int i = 0; i < count.size(); i++) {
						if(count.get(i) == minValue){
							minKey = frames.get(i);
							break;
						}
					}
					frames.remove((Integer)minKey);
					frames.add(page);
					if(pageCounters.containsKey(page)){
						int currentCount = pageCounters.get(page);
						pageCounters.put(page, currentCount+1);
					}
					else pageCounters.put(page, 1);
				}
			}
			else{
				int currentCount = pageCounters.get(page);
				pageCounters.put(page, currentCount+1);
			}
		}
		
		double ratio = (swapTime*pageFaults)/totalTime;
		System.out.println("Pages Needed: " + pagesNeeded);
		System.out.println("Allocations = " + allocations);
		System.out.println("Page Faults = " + pageFaults);
		System.out.println("SwapTime/TotalTime = " + ratio);
		
	}
	
	public void mfu(){
		this.frames = new ArrayList<Integer>();
		int pageFaults = 0;
		double totalTime = 0;
		HashMap<Integer, Integer> pageCounters = new HashMap<>();
				
		ArrayList<Integer> allocations = new ArrayList<Integer>();
		
		System.out.println("MFU:");
		System.out.println("Frames:");
		for (Integer page : pagesNeeded) {
			System.out.println(frames);
			totalTime+=accessTime;
			if(!frames.contains(page)){
				pageFaults++;
				allocations.add(page);
				totalTime+=swapTime;
				if(frames.size() < numberOfFrames){
					frames.add(page);
					pageCounters.put(page, 1);
				}
				else{
					ArrayList<Integer> count = new ArrayList<Integer>();
					for (Integer frame : frames) {
						if(pageCounters.containsKey(frame)){
							count.add(pageCounters.get(frame));
						}
						else System.out.println("ERRO!!!!");
					}
				
					int maxValue = Collections.max(count);
					int maxKey = -1;
					for (int i = 0; i < count.size(); i++) {
						if(count.get(i) == maxValue){
							maxKey = frames.get(i);
							break;
						}
					}
					frames.remove((Integer)maxKey);
					frames.add(page);
					if(pageCounters.containsKey(page)){
						int currentCount = pageCounters.get(page);
						pageCounters.put(page, currentCount+1);
					}
					else pageCounters.put(page, 1);
				}
			}
			else{
				int currentCount = pageCounters.get(page);
				pageCounters.put(page, currentCount+1);
			}
		}
		
		double ratio = (swapTime*pageFaults)/totalTime;
		System.out.println("Pages Needed: " + pagesNeeded);
		System.out.println("Allocations = " + allocations);
		System.out.println("Page Faults = " + pageFaults);
		System.out.println("SwapTime/TotalTime = " + ratio);
		
	}	
}
