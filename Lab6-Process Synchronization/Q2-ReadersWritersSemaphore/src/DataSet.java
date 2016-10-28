import java.util.ArrayList;
import java.util.concurrent.Semaphore;


public class DataSet {
	private ArrayList<Integer> data = new ArrayList<Integer>();
	private Semaphore access = new Semaphore(1);
	private Semaphore mutex = new Semaphore(1);
	private int readersCount = 0;
	private int writersCount = 0;

	
	public void incrementReadersCount() throws InterruptedException{
		mutex.acquire();
		readersCount++;
		mutex.release();
	}
	
	public void decrementReadersCount() throws InterruptedException{
		mutex.acquire();
		readersCount--;
		if(readersCount == 0){
			access.release();
		}
		mutex.release();
	}
	
	public ArrayList<Integer> read() throws InterruptedException {
		
		if (readersCount == 0) 
			access.acquire();
		
		
		incrementReadersCount();

		if(readersCount >0 || writersCount > 0 ){
			System.out.println("============READER============\n" + "Quantidade de leitores: " + readersCount + "\nQuantidade de escritores: " + writersCount + "\n===============================\n");
		}
		
		decrementReadersCount();
		
		return data;
	}
	
	public void write(int number) throws InterruptedException {
		
		access.acquire();
		
		writersCount++;

		this.data.add(number);
		
		System.out.println("============WRITER============\n" + "Quantidade de leitores: " + readersCount + "\nQuantidade de escritores: " + writersCount + "\n===============================\n");

		writersCount--;
		access.release();
		
	}
}	