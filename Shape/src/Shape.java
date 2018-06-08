
public abstract class Shape {

    private String name;

    public Shape(String name) {
	this.name = name;
    } // Shape

    public String getName() {
	return name; 
    } // getName

    public abstract double area();
    
} // Shape

