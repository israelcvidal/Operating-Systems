import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Dropbox {
	private int number;
	private boolean evenNumber = false;
	private boolean exist = false;
	private final Lock lock = new ReentrantLock();
	
	private final Condition isFull = lock.newCondition();
	private final Condition notEmpty = lock.newCondition();
	private final Condition isEven = lock.newCondition();
	private final Condition isOdd = lock.newCondition();

	public int take(final boolean even) throws InterruptedException {
		lock.lock();
		
		while(!exist){
			notEmpty.await();
		}
		
		if(even){
			while(!evenNumber){
				isOdd.signal();
				isEven.await();
			}
			System.out.format("%s CONSUMIDOR obtem %d.%n", even ? "PAR" : "IMPAR", number);
		}
		else{
			while(evenNumber){
				isEven.signal();
				isOdd.await();
			}
			System.out.format("%s CONSUMIDOR obtem %d.%n", even ? "PAR" : "IMPAR", number);
		}
		exist = false;
		isFull.signal();

		lock.unlock();
		return number;
		
	}
	public void put(int number) throws InterruptedException {
		lock.lock();
		
		while(exist){
			notEmpty.signal();
			isFull.await();
		}
				
		this.number = number;
		evenNumber = number % 2 == 0;
		
		exist = true;
		notEmpty.signal();
		System.out.format("PRODUTOR gera %d.%n", number);
		
		lock.unlock();
	}
}	