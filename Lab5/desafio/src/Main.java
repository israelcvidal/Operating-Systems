import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Main {
	public static int count = 0;
	public static int BUFFER_SIZE = 2;
	public static ArrayList<Integer> buffer = new ArrayList<>(BUFFER_SIZE);
	
	Semaphore semaphore = new Semaphore(BUFFER_SIZE);
	
	Thread produtor = new Thread(new Produtor(semaphore));
	Thread consumidor = new Thread(new Consumidor(semaphore));
	
	
	
}
