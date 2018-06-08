
public class Rectangle extends Shape {

    private double width;
    private double height;

    public Rectangle(double width, double height) {
	super("Rectangle("+width+", "+height+")");
	this.width = width;
	this.height = height;
    } // Rectangle

    public double area() {
	return width * height;
    } // area

} // Rectangle
