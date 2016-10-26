
public class Fork {
	private static int seq = -1;
	private int id;
	private int count = 0;
	
	public Fork(){
		this.id = ++seq;
	}

	public int getId() {
		return id;
	}
	
	public void use(){
		count++;
	}
	
	public void release(){
		count--;
	}
	
	public int getCount(){
		return count;
	}

}
