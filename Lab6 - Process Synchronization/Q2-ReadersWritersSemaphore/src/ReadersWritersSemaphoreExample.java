
public class ReadersWritersSemaphoreExample {

	public static void main(String[] args) {
		DataSet dataSet = new DataSet();
		
		(new Thread(new Writer(dataSet))).start();
		(new Thread(new Writer(dataSet))).start();
		(new Thread(new Reader(dataSet))).start();
		(new Thread(new Reader(dataSet))).start();
		(new Thread(new Reader(dataSet))).start();

	}
}