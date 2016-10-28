import java.util.ArrayList;
import java.util.Vector;

public class Deadlock_Control {
	
	private ArrayList<Integer>available;	//available[j] = k, there are k instances of resource j
	private ArrayList<ArrayList<Integer>> max;	//max[i,j] = k, process i can use until k instances of resource j
	private ArrayList<ArrayList<Integer>> allocation; //Allocation[i,j] = k, process i already allocated k instances of resource j
	private ArrayList<ArrayList<Integer>> need; // Need[i,j] = k, process i still need k instances of j to finish
//	private ArrayList<Integer> blocked;
	
	public Deadlock_Control(ArrayList<Integer> available, ArrayList<ArrayList<Integer>> max, ArrayList<ArrayList<Integer>> allocation, ArrayList<ArrayList<Integer>> need){
		this.available = available;
		this.max = max;
		this.allocation = allocation;
		this.need = need;
	}
	
	public boolean safety(ArrayList<Integer>available, ArrayList<ArrayList<Integer>> max, ArrayList<ArrayList<Integer>> allocation, ArrayList<ArrayList<Integer>> need ){
//	public boolean safety(){
		ArrayList<Integer> work = available;
		ArrayList<Boolean> finish = new ArrayList<Boolean>();

		//initializing finish with |processes| false.
		for (int i = 0; i <max.size(); i++) {
			finish.add(false);
		}
		int finished;
		for (int i = 0; i < finish.size(); i++) {
			finished = 0;
			for (int j = 0; j < need.get(i).size(); j++) {
				if(finish.get(i)== false && need.get(i).get(j) <= work.get(j)){
					work.set(j, work.get(j) + allocation.get(i).get(j));
					finished++;
				}
			}
			if(finished == need.get(i).size())
				finish.set(i, true);
		}
		
		for (Boolean bool : finish) {
			if(!bool)
				return false;
		}
		return true;
	}
	
	
//	public ArrayList<Integer> avoid(int pId, ArrayList<Integer> request,ArrayList<Integer>available, ArrayList<ArrayList<Integer>> max, ArrayList<ArrayList<Integer>> allocation, ArrayList<ArrayList<Integer>> need ){
	public Boolean avoid(int pId, ArrayList<Integer> request){
		ArrayList<Integer>blocked = new ArrayList<Integer>();
		ArrayList<Integer>newAvailable = available;
		ArrayList<ArrayList<Integer>> newAllocation = allocation;
		ArrayList<ArrayList<Integer>> newNeed = need;
		
		for (int i = 0; i < request.size(); i++) {
			if(request.get(i) > need.get(pId).get(i)){
				System.out.println("Process " + pId + " exceded the maximum of resource " + i);
				return false;
			}
			
			if(request.get(i) > available.get(i)){
				System.out.println("Process " + pId + " is waiting for resources");
				blocked.add(pId);
				return false;
			}	
			//Simulation:
			newAvailable.set(i, available.get(i) - request.get(i));
			newAllocation.get(pId).set(i, allocation.get(pId).get(i) + request.get(i));
			newNeed.get(pId).set(i, need.get(pId).get(i) - request.get(i));
		}
		return safety(newAvailable, max, newAllocation, newNeed);
		
	}
	
	public ArrayList<Integer> detection(){
		ArrayList<Integer> work = available;
		ArrayList<Boolean> finish = new ArrayList<Boolean>();

		//initializing finish with |processes| false.
		for (int i = 0; i <max.size(); i++) {
			finish.add(false);
		}
		int finished;
		for (int i = 0; i < finish.size(); i++) {
			finished = 0;
			for (int j = 0; j < need.get(i).size(); j++) {
				if(finish.get(i)== false && need.get(i).get(j) <= work.get(j)){
					work.set(j, work.get(j) + allocation.get(i).get(j));
					finished++;
				}
			}
			if(finished == need.get(i).size())
				finish.set(i, true);
		}
		ArrayList<Integer> inDeadlock = new ArrayList<Integer>();
		
		for (int i = 0; i < finish.size(); i++) {
			if(finish.get(i) == false)
				inDeadlock.add(i);
		}
		
		if(inDeadlock.size() > 0){
			System.out.println("Processes in deadlock: ");
			for (Integer integer : inDeadlock) {
				System.out.println(integer);
			}
		}
		return inDeadlock;
	}
	
	
}
