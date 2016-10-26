import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class DataSet {
	private ArrayList<Integer> data = new ArrayList<Integer>();
	private boolean writing = false;
	private boolean reading = false;
	private final Lock lock = new ReentrantLock();
	private int readersCount = 0;
	private int writersCount = 0;
	private final Condition notWriting = lock.newCondition();
	private final Condition notReading = lock.newCondition();

	public void incrementReadersCount(){		
		reading = true;
		readersCount++;
	}
	
	public  void decrementReadersCount(){
		lock.lock();
		readersCount--;
		if(readersCount == 0){
			reading = false;
			writing = false;
			notReading.signal();
		}
		lock.unlock();
	}
	
	public ArrayList<Integer> read() throws InterruptedException {

		lock.lock();
		while(writing || data.size() == 0){
			notWriting.await();
		}
		reading=true;
		lock.unlock();
		
		incrementReadersCount();
		
		System.out.println("============READER============");
		System.out.println("Quantidade de leitores: " + readersCount);
		System.out.println("Quantidade de escritores: " + writersCount);
		System.out.println("===============================");
		System.out.println();

		decrementReadersCount();
		
		return data;
	}
	
	public void write(int number) throws InterruptedException {
		lock.lock();
		
		while(reading){
			notReading.await();
		}
		
		
		writersCount++;
		writing = true;

		this.data.add(number);
		
		System.out.println("============WRITER============");
		System.out.println("Quantidade de leitores: " + readersCount);
		System.out.println("Quantidade de escritores: " + writersCount);
		System.out.println("===============================");
		System.out.println();
		writing = false;
		writersCount--;
		notReading.signal();
		notWriting.signalAll();
		
		lock.unlock();
	}
}	