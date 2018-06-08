
public class Triangle extends Shape {

    private double base;
    private double height;

    public Triangle(double base, double height) {
	super("Triangle("+base+", "+height+")");
	this.base = base;
	this.height = height;
    } // Triangle

    public double area() {
	return 0.5 * base * height;
    } // area

} // Triangle

