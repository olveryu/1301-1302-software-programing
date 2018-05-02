package localVariableTest;

public class Tester {
public static void main(String args[]) {
	localVariableTest testObj = new localVariableTest();
	System.out.println(testObj.value);
	System.out.println(testObj.methodOne());
	System.out.println(testObj.methodTwo());
	System.out.println(testObj.methodOne());
	System.out.println(testObj.value);
}
}
//1,2,3 2 3000