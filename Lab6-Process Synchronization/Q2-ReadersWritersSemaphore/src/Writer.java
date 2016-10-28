import java.util.Random;
//objects from this class can read and write data from data set

public class Writer implements Runnable {
	private DataSet dataSet;
	public Writer(DataSet dataSet) {
		this.dataSet = dataSet;
	}
	
	public void run() {
		Random random = new Random();
		while (true) {
				int number = random.nextInt(10);
				try {
					Thread.sleep(random.nextInt(100));
					dataSet.write(number);
				} catch (InterruptedException e) { }
		}
	}
}