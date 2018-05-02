package localVariableTest;

public class localVariableTest {

	public int value = 1;
	public int methodOne() {
		 int value = 2;
		 this.value = 3000;
		return value;
	}
	public int methodTwo() {
		value = 3;
		return value;
	}
}
