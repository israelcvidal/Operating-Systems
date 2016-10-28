//Objects from this class can only read from data set, not write


public class Reader implements Runnable {
	private final DataSet dataSet;
	
	public Reader(DataSet dataSet) {
		this.dataSet = dataSet;
	}
	
	public void run() {
		while (true) {
			try {
				Thread.sleep(10);
				dataSet.read();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
		}
	}
}	