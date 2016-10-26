import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class DiningTable {
	
	
	public static void main(String[] args) {
		ArrayList<Philosopher> philosophers = new ArrayList<Philosopher>();
		ArrayList<Semaphore> forks = new ArrayList<Semaphore>();

		for(int i = 0; i<5; i++){
			forks.add(new Semaphore(1));
		}
		
		for(int i = 0; i<5; i++){
			philosophers.add(new Philosopher(forks));
		}
		
		for (Philosopher philosopher : philosophers) {
			(new Thread(philosopher)).start();
		}
		
	}
	

}
