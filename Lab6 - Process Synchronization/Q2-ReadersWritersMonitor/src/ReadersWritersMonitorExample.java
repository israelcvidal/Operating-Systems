

public class ReadersWritersMonitorExample {
	private static boolean Even = true;
	private static boolean Odd = false;
	
	public static void main(String[] args) {
		DataSet dataSet = new DataSet();
		(new Thread(new Writer(dataSet))).start();
		(new Thread(new Writer(dataSet))).start();
		(new Thread(new Reader(dataSet))).start();
		(new Thread(new Reader(dataSet))).start();
		(new Thread(new Reader(dataSet))).start();

	}
}