import java.util.ArrayList;
import java.util.Random;

public class Philosopher implements Runnable{
	private static int seq =-1;
	private int id;
	private ArrayList<Fork> forks;
	private Fork leftFork, rightFork;
	
	public Philosopher(ArrayList<Fork> forks){
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
		leftFork = forks.get(id);
		if( (id-1) == -1){
			rightFork = forks.get(forks.size()-1);
		}
		else{
			rightFork = forks.get(id-1);
		}
		
		if (rightFork.getId() > leftFork.getId()){
			Fork aux = leftFork;
			leftFork = rightFork;
			rightFork = aux;
		}
		
		synchronized (leftFork) {
			leftFork.use();
			synchronized(rightFork){
				rightFork.use();
				Random rand = new Random();
				System.out.println("Philosopher " + this.id + " is eating");
//				System.out.println("leftId = " + leftFork.getId() + "count  = " + leftFork.getCount() + "\nrighId = " + rightFork.getId() + "count  = " +rightFork.getCount());
				Thread.sleep(rand.nextInt(100));
				
				leftFork.release();
				rightFork.release();
			}
		};
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
