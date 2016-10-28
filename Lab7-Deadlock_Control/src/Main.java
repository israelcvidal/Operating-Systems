import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		ArrayList<Integer>available = new ArrayList<Integer>();	//available[j] = k, there are k instances of resource j
		ArrayList<ArrayList<Integer>> max = new ArrayList<ArrayList<Integer>>();	//max[i,j] = k, process i can use until k instances of resource j
		ArrayList<ArrayList<Integer>> allocation = new ArrayList<ArrayList<Integer>>(); //Allocation[i,j] = k, process i already allocated k instances of resource j
		ArrayList<ArrayList<Integer>> need = new ArrayList<ArrayList<Integer>>(); // Need[i,j] = k, process i still need k instances of j to finish
		
		//processes 0,1,2
		//resources 0,1,2
		
		ArrayList<Integer> max0 = new ArrayList<Integer>();
		max0.add(7);
		max0.add(5);
		max0.add(7);
		ArrayList<Integer> max1 = new ArrayList<Integer>();
		max1.add(7);
		max1.add(1);
		max1.add(1);
		ArrayList<Integer> max2 = new ArrayList<Integer>();
		max2.add(10);
		max2.add(4);
		max2.add(3);
		//adding to max
		max.add(max0);
		max.add(max1);
		max.add(max2);
		
		ArrayList<Integer> allocation0 = new ArrayList<Integer>();
		ArrayList<Integer> allocation1 = new ArrayList<Integer>();
		ArrayList<Integer> allocation2 = new ArrayList<Integer>();
		
		for (int i = 0; i < 3; i++) {
			allocation0.add(0);
			allocation1.add(0);
			allocation2.add(0);
			available.add(2);

		}
		//adding to allocation 
		allocation.add(allocation0);
		allocation.add(allocation1);
		allocation.add(allocation2);

		ArrayList<Integer> need0 = new ArrayList<Integer>();
		ArrayList<Integer> need1 = new ArrayList<Integer>();
		ArrayList<Integer> need2 = new ArrayList<Integer>();

		need0.add(3);
		need0.add(1);
		need0.add(0);

		need1.add(7);
		need1.add(0);
		need1.add(1);

		need2.add(0);
		need2.add(2);
		need2.add(2);
		
		need.add(need0);
		need.add(need1);
		need.add(need2);
		
		Deadlock_Control deadlock = new Deadlock_Control(available, max, allocation, need);
		System.out.println("The state is: ");
		if(deadlock.safety(available, max, allocation, need)){
			System.out.println("SAFE");
		}else System.out.println("UNSAFE");
		
		deadlock.detection();
		System.out.println();
		
		
		ArrayList<Integer> request = new ArrayList<Integer>();
		request.add(0);
		request.add(0);
		request.add(1);
		if(deadlock.avoid(1, request)){
			System.out.println("It is possible do allocate resources to process 1!");
		}else System.out.println("It is NOT possible do allocate resources to process 1!");

			
	}
}
