import java.util.Random;
//objects from this class can read and write data from data set

public class Writer implements Runnable {
	private DataSet dataSet;
	private static int seq = -1;
	private int id;
	public Writer(DataSet dataSet) {
		this.dataSet = dataSet;
		this.id = ++seq;
	}
	
	public void run() {
		Random random = new Random();
		while (true) {
				int number = random.nextInt(10);
				try {
					Thread.sleep(random.nextInt(100));
					dataSet.write(number);
//					System.out.println("writerId="+id);
				} catch (InterruptedException e) { }
		}
	}
}