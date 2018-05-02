package coin;
import java.util.Random;
import java.util.Scanner;
public class FlippingCoin {
	public static void main(String[] args) {
		

	String s = "Fs2df";
	String d = "asdh";
	boolean equal = true;
	int x=10;
	
	/*for(int i = 0; i<s.length(); i++) {
		if(s.charAt(i)=='2') {
			break;
		}
		x++;
	}
	System.out.println(x);
	*/
	//Scanner keyboard = new Scanner(System.in);
	//System.out.println("please enter a string");
	//String ws = keyboard.nextLine();
	//do {
		//System.out.println("hello");
		//x--;
	//}while(x>5);
	for(int j = 1;j<=5;j++) {
		for(int y = 1;y <=5; y++) {
			System.out.print(j+"/" + y + " ");
			
		}
		System.out.println();
	}
	
}
}
