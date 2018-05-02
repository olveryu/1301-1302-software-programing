package localVariable;

public class driver {
	public static void main(String[] args) {
		localVariableTest testObj = new localVariableTest();
		System.out.println(testObj.value);
		System.out.println(testObj.methodOne());
		System.out.println(testObj.methodTwo());
		System.out.println(testObj.methodOne());
		System.out.println(testObj.value);
	}

}
