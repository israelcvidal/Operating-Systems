import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class DataSet {
	private ArrayList<Integer> data = new ArrayList<Integer>();
	private boolean writing = false;
	private boolean reading = false;
//	private boolean newData = false;
	private final Lock lock = new ReentrantLock();
	private int readersCount = 0;
	private int writersCount = 0;
//	private final Condition isFull = lock.newCondition();
//	private final Condition notEmpty = lock.newCondition();
	private final Condition notWriting = lock.newCondition();
	private final Condition notReading = lock.newCondition();

	public void incrementReadersCount(){
		reading = true;
		readersCount++;
	}
	
	public void decrementReadersCount(){
		readersCount--;
		if(readersCount == 0){
			reading = false;
			writing = false;
//			newData = false;
			notReading.signalAll();
			notWriting.signalAll();
		}
	}
	public ArrayList<Integer> read() throws InterruptedException {
		Random random = new Random();

		lock.lock();
		
//		while(writing || !newData){
		while(writing || data.size() == 0){

//			System.out.println("reader waiting");
//			notReading.signal();
			notWriting.await();
		}
//		System.out.println("incrementou");
		incrementReadersCount();
		
		System.out.println("============READER============");
		System.out.println("Quantidade de leitores: " + readersCount);
		System.out.println("Quantidade de escritores: " + writersCount);
		System.out.println("===============================");
		System.out.println();
		
		for (Integer integer : data) {
//			Thread.sleep(random.nextInt(1000));
			Thread.sleep(10000);
		}
		
//		System.out.println("Leitor acabou a leitura!");
		

		decrementReadersCount();

		lock.unlock();
		return data;
	}
	
	public void write(int number) throws InterruptedException {
		Random random = new Random();
		lock.lock();
		
//		System.out.println("reading =" + reading + ", writing = "+ writing);
		while(reading){
			notReading.await();
			while(writing){
				notReading.signalAll();
				notWriting.await();
			}
		}
		writersCount++;
		writing = true;
//		if(data.size() >=3 )
//			this.data.remove(random.nextInt(data.size()));
		this.data.add(random.nextInt(10));
//		System.out.println("Writer escreve novo dado");
		
		System.out.println("============WRITER============");
		System.out.println("Quantidade de leitores: " + readersCount);
		System.out.println("Quantidade de escritores: " + writersCount);
		System.out.println("===============================");
		System.out.println();
//		newData = true;
		writing = false;
		writersCount--;
		notReading.signal();
		notWriting.signal();
		
		lock.unlock();
	}
}	