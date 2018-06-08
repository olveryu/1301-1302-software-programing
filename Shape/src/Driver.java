
public class Driver {

    public static void main(String[] args) {
    	int[] array = new int[5];
    	array[4] = 4;
    	System.out.println(Integer.MAX_VALUE);
    	array = new int[2];
    	String s = "sdasd";
    	int h = s.indexOf(null);
    	
    	
//	Shape[] shapes = new Shape[] {
//	    new Triangle(1, 1),
//	    new Rectangle(1, 1),
//	    new Triangle(2, 2),
//	    new Rectangle(2, 2)
//	};
//
//	for (Shape shape: shapes) {
//	    System.out.println(shape.getName());
//	    System.out.println(shape.area());
//	} // for
//	
//	Shape square = new Rectangle(5,5);
//	System.out.println(square.getName());
//	System.out.println(shapes[3].getName());
	
    	System.out.println(count());
	
    } // main
    public static int count() {
    	for(int i = 0; i <10; i++) {
    		if(i+2==5) return i+2;
    	}
    	return 0;
    }
    public boolean hasNext() {
    	return (1>2);
    }
} // Driver

