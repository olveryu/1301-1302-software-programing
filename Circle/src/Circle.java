//*******************************************************
// Circle.java
//
// 
//*******************************************************
/*
* Circle.java
* Author: Lin, Wenhao	
* Submission Date: 10/18/2017
*
* Purpose: This is a class Object for methods. Different methods like getter, setter,
* accessor and mutator are created to be called from other classes or within this class.
* 
* Statement of Academic Honesty:
*
* The following code represents my own work. I have neither
* received nor given inappropriate assistance. I have not copied
* or modified code from any source other than the course webpage
* or the course textbook. I recognize that any unauthorized
* assistance or plagiarism will be handled in accordance with
* the University of Georgia's Academic Honesty Policy and the
* policies of this course. I recognize that my work is based
* on an assignment created by the Department of Computer
* Science at the University of Georgia. Any publishing
* or posting of source code for this project is strictly
* prohibited unless you have written consent from the Department
* of 
*/
public class Circle {
	

	private double radius;      // declare the private double instance  radius
	private double x;       // declare the private double instance  x
	private double y;       // declare the private double instance  y
	
	
	//----------------------------------------------
	// getX - returns the value of x
	//----------------------------------------------
	public double getX() {
		return x;
	}
	

	//----------------------------------------------
	// getY - returns the value of y
	//----------------------------------------------
	public double getY() {
		return y;
	}
	
	//----------------------------------------------
	// getRadius - returns the value of radius
	//----------------------------------------------
	public double getRadius() {
		return radius;
	}

	//----------------------------------------------
	// setX - assigns a new value to x
	//----------------------------------------------
	public void setX(double newX) {
		x = newX;
	}
	

	//----------------------------------------------
	// setY - assigns a new value to y
	//----------------------------------------------
	public void setY(double newY) {
		y = newY;
	}	
	
	
	//----------------------------------------------
	// setRadius - assigns a new value to radius
	//----------------------------------------------
	public void setRadius(double newRadius) {
		if(newRadius > 0) {
			radius = newRadius;
		}
		
			
	}
	
	//--------------------------------------------------------
	// diameter - calculates the diameter of the circle
	//--------------------------------------------------------
	public double diameter() {
		double d = radius*2;
		return d;
	}
	

	//--------------------------------------------------------
	// area - returns the area of the circle
	//--------------------------------------------------------
	public double area() {
		double a = Math.PI*radius;
		return a;
			
	}

	//--------------------------------------------------------
	// perimeter - returns the perimeter of the circle
	//--------------------------------------------------------
	public double perimeter() {
		double p = 2 * Math.PI * radius;
		return p;
	}
	
	//--------------------------------------------------------
	// isUnitCircle - return true if the radius of this circle
	//                is 1 and its center is (0,0) and false
	//      	      otherwise.
	//--------------------------------------------------------
	public boolean isUnitCircle() {
		if(radius == 1 && x == 0 && y == 0)
			return true;
		else
			return false;
	}
	
	
	//--------------------------------------------------------
	// toString - return a String representation of
	//            this circle in the following format:
	//            center:(x,y)
	//            radius: r
	//--------------------------------------------------------
	public String toString() {
		String result = "center:(" + x +","+ y +")\n" +"radius: " + radius ;			
		return result;
			
	}
	//return true if both circles are equal in terms of same radius and center.
	public boolean equals(Circle anotherCircle) {
		if(this.radius == anotherCircle.getRadius() 
				&& this.x == anotherCircle.getX()
				&& this.y == anotherCircle.getY())
			return true;
		else
			return false;
	}
	
	//returns true if both circle has the same center and different radius
	public boolean isConcentric(Circle anotherCircle) {
		if(this.radius != anotherCircle.getRadius() 
				&& this.x == anotherCircle.getX()
				&& this.y == anotherCircle.getY())
			return true;
		else
			return false;
		
		}
	//return the distance between two circles
	public double distance(Circle anotherCircle) {
			double inside = Math.pow(this.x - anotherCircle.x, 2)
					+ Math.pow(this.y - anotherCircle.y, 2);
			double sqrt = Math.sqrt(inside);
			return sqrt;
		}
	
	public boolean intersects(Circle anotherCircle) {
		if(this.radius + anotherCircle.getRadius() > distance(anotherCircle))
			return true;
		else
			return false;	
	}
	
	

}