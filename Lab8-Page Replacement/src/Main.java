import algorithms.Algorithms;

public class Main {
	public static void main(String[] args) {
		Algorithms alg = new Algorithms("1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5", 4);
//		Algorithms alg = new Algorithms("7,0,1,2,0,3,0,4,2,3,0,3,2,1,2,0,1,7,0,1", 3);
		
		alg.fifo();
		System.out.println();
		alg.lru();
		System.out.println();
		alg.lfu();
		System.out.println();
		alg.mfu();
		System.out.println();
		alg.opt();
	}
}
