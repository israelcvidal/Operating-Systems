import java.util.concurrent.Semaphore;

public class Consumidor implements Runnable{
	private Semaphore semaphore;
	
	public Consumidor(Semaphore semaphore){
		this.semaphore = semaphore;
	}
	public void run() {
		while(true){
			try {
				semaphore.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Main.buffer.remove(0));
			semaphore.release();
		}
	}
}
