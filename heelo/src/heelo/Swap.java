package heelo;

public class Swap {
	public static void swap(int[] v) {
		int temp = v[0];
		v[0] = v[v.length - 1];
		v[v.length - 1] = temp;
	}
	
}
