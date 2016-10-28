import java.util.ArrayList;

public class DiningTable {
	
	
	public static void main(String[] args) {
		ArrayList<Philosopher> philosophers = new ArrayList<Philosopher>();
		ArrayList<Fork> forks = new ArrayList<Fork>();
		
		for(int i = 0; i<5; i++){
			forks.add(new Fork());
		}
		
		for(int i = 0; i<5; i++){
			philosophers.add(new Philosopher(forks));
		}
		
		for (Philosopher philosopher : philosophers) {
			(new Thread(philosopher)).start();
		}
		
	}
	

}
