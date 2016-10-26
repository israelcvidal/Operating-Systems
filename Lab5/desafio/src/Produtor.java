import java.util.concurrent.Semaphore;

public class Produtor implements Runnable{
	private Semaphore semaphore;
	
	public Produtor(Semaphore semaphore){
		this.semaphore = semaphore;
	}
	
	public void run() {
		int item = 0;
		while(true){
			try {
				semaphore.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Main.buffer.add(item);
			item++;
			semaphore.release();
		}
	}
	
}
