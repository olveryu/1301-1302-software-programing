package practice;

public class Practice {
	public static int min(int[] b, int left,int right) {
		if(left == right) return b[left];
		if(b[left]>b[right]) return min(b,left+1,right);
		return min(b,left,right-1);
	}
	public static void main(String[] args) {
		int [] a= {100,3,4,2,6,7,10,20,14,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		System.out.println("min of a is: "+ Practice.min(a,0,a.length-1));
		
	}//main
}
