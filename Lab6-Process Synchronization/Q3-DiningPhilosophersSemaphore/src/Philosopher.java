import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Philosopher implements Runnable{
	private static int seq =-1;
	private int id;
	private ArrayList<Semaphore> forks;
	private Semaphore leftFork, rightFork;
	
	public Philosopher(ArrayList<Semaphore> forks){
		this.forks = forks;
		this.setId(++seq);
	}
	
	public void run() {
		while(true){
			try {
				this.eat();
				this.think();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void think() throws InterruptedException{
		Random rand = new Random();
		System.out.println("Philosopher " + this.id + " is thinking");
		Thread.sleep(rand.nextInt(100));
	}
	
	public void eat() throws InterruptedException{
		Random rand = new Random();
		int ind1 = id, ind2;

		leftFork = forks.get(ind1);
		if( (id-1) == -1){
			ind2 = forks.size()-1;
			rightFork = forks.get(ind2);
		}
		else{
			ind2 = id-1;
			rightFork = forks.get(ind2);
		}
		
		if (ind1 > ind2){
			Semaphore aux = leftFork;
			leftFork = rightFork;
			rightFork = aux;
		}
		
		leftFork.acquire();
		rightFork.acquire();
		
		System.out.println("Philosopher " + this.id + " is eating");
		Thread.sleep(rand.nextInt(100));
		
		leftFork.release();
		rightFork.release();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
