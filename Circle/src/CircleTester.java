//*******************************************************
// CircleTester.java
//
//
//  A client to test the functionality of objects
//  of the class Circle
// 
//*******************************************************
/*
* CircleTester.java
* Author: Lin, Wenhao	
* Submission Date: 10/18/2017
*
* Purpose: This is a tester for methods created in Circle.java file. This tester 
* can test if the methods can be correctly called and can do the designed function 
* of methods correctly when called.
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

public class CircleTester{
	
	public static void main(String[] args) {
		
		Circle circle1 = new Circle();
		Circle circle2 = new Circle();
		circle1.setX(0.0);
		circle1.setY(0.0);
		circle1.setRadius(2);
		circle2.setX(2.0);
		circle2.setY(1.0);
		circle2.setRadius(1);
		System.out.println("circle1="+circle1);
		System.out.println("circle2="+circle2);
		
		// If the method setRadius is implemented correctly,
		// a call to setRadius with a negative number
		// will not change the value of the circle's radius.
		//
		circle1.setRadius(-2.0); 
		
		//
		// Reset the center of circle1 (-3.0,4.0)
		//
		circle1.setX(-3.0);
		circle1.setY(4.0);
		
		
		// print circle1 characteristics (center and radius), use a statement similar 
		// to the previous println statements. Note that is not necessary to call
		//the method toString, why?
		System.out.println("circle1= " + circle1);
		
		// set the circle2 radius to 5.3
		circle2.setRadius(5.3);
		
		// print circle2 characteristics (center and radius), use a statement similar to the first and
		// second println statements
		System.out.println("circle2= " + circle2);
		
		// print circle1 diameter, area and perimeter
		System.out.println("circle1 diameter= " +circle1.diameter());
		System.out.println("circle1 area= " + circle1.area());
		System.out.println("circle1 perimeter= " + circle1.perimeter());
		
		// print circle2 diameter, area and perimeter
		System.out.println("circle2 diameter=" +circle2.diameter());
		System.out.println("circle2 area=" + circle2.area());
		System.out.println("circle2 perimeter=" + circle2.perimeter());
		
		// display whether circle1 is a unit circle
		if(circle1.isUnitCircle()==true)
			System.out.println("Yes,circle1 is a unit circle.");
		else
			System.out.println("No,circle1 is not a unit circle.");
		
		// display whether circle2 is a unit circle
		if(circle2.isUnitCircle()==true)
			System.out.println("Yes,circle2 is a unit circle.");
		else
			System.out.println("No,circle2 is not a unit circle.");
		
		// your additional tests should be placed below here
		
		//test if two circles are the same in terms of the radius and the centers
		//test1
		circle1.setRadius(1);
		circle1.setX(0);
		circle1.setY(0);
		circle2.setRadius(1);
		circle2.setX(0);
		circle2.setY(0);
		System.out.println("Test 1 to check if two circles are the same: ");
		if(circle1.equals(circle2)==true)
			System.out.println("circle1 and circle2 are the same.");
		else
			System.out.println("circle1 and circle2 are NOT the same.");
		//*test2
		circle1.setRadius(10);
		circle1.setX(0);
		circle1.setY(0);
		circle2.setRadius(1);
		circle2.setX(0);
		circle2.setY(0);
		System.out.println("Test 2 to check if two circles are the same: ");
		if(circle1.equals(circle2)==true)
			System.out.println("circle1 and circle2 are the same.");
		else
			System.out.println("circle1 and circle2 are NOT the same.");
		 
		//*test3
		circle1.setRadius(1);
		circle1.setX(10);
		circle1.setY(0);
		circle2.setRadius(1);
		circle2.setX(0);
		circle2.setY(10);
		System.out.println("Test 3 to check if two circles are the same: ");
		if(circle1.equals(circle2)==true)
			System.out.println("circle1 and circle2 are the same.");
		else
			System.out.println("circle1 and circle2 are NOT the same.");
		
		//test if two circle are concentric but not equal in radius.
		//*test1
		circle1.setRadius(1);
		circle1.setX(0);
		circle1.setY(0);
		circle2.setRadius(5.3);
		circle2.setX(0);
		circle2.setY(0);
		
		System.out.println("Test 1 to see if two circles are concentric: ");
		if(circle1.isConcentric(circle2)==true)
			System.out.println("circle1 and circle2 are concentric.");
		else
			System.out.println("circle1 and circle2 are NOT concentric.");
		
		//*test2
		circle1.setRadius(1);
		circle1.setX(0);
		circle1.setY(0);
		circle2.setRadius(1);
		circle2.setX(0);
		circle2.setY(0);
		System.out.println("Test 2 to see if two circles are concentric: ");
		if(circle1.isConcentric(circle2)==true)
			System.out.println("circle1 and circle2 are concentric.");
		else
			System.out.println("circle1 and circle2 are NOT concentric.");
		
		//*test3
		circle1.setRadius(1);
		circle1.setX(2);
		circle1.setY(5.6);
		circle2.setRadius(1);
		circle2.setX(0);
		circle2.setY(0);
		System.out.println("Test 3 to see if two circles are concentric: ");
		if(circle1.isConcentric(circle2)==true)
			System.out.println("circle1 and circle2 are concentric.");
		else
			System.out.println("circle1 and circle2 are NOT concentric.");
		
		//*test for the distance calculation
		//*test1
		circle1.setX(1);
		circle1.setY(1);
		circle2.setX(3);
		circle2.setY(3);
		System.out.println("Test 1 for distance calculation: ");
		System.out.println("The distance between two circles is:" + circle1.distance(circle2));
		
		//*test2
		circle1.setX(3);
		circle1.setY(1);
		circle2.setX(2);
		circle2.setY(3);
		System.out.println("Test 2 for distance calculation: ");
		System.out.println("The distance between two circles is:" + circle1.distance(circle2));
		//*test3
		circle1.setX(0);
		circle1.setY(0);
		circle2.setX(4);
		circle2.setY(4);
		
		System.out.println("Test 3 for distance calculation: ");
		System.out.println("The distance between two circles is:" + circle1.distance(circle2));
		
		//test to see if two circles intersects
		//*test1
		circle1.setX(0);
		circle1.setY(1);
		circle1.setRadius(3.2);
		circle2.setX(4);
		circle2.setY(4);
		circle2.setRadius(2.2);	 
		System.out.println("Test 1 to see if two circles are concentric: ");
		if(circle1.intersects(circle2)==true)
			System.out.println("Yes, circle1 intersects with circle2.");
		else
			System.out.println("No, circle1 does NOT intersect with circle2.");
		
		//*test2
		circle1.setX(12);
		circle1.setY(1);
		circle1.setRadius(1.9);
		circle2.setX(0);
		circle2.setY(0);
		circle2.setRadius(2.2);
		 
		System.out.println("Test 2 to see if two circles are concentric: ");
		if(circle1.intersects(circle2)==true)
			System.out.println("Yes, circle1 intersects with circle2.");
		else
			System.out.println("No, circle1 does NOT intersect with circle2.");
				
		//*test3
		circle1.setX(1);
		circle1.setY(1);
		circle1.setRadius(5.6);
		circle2.setX(0);
		circle2.setY(0);
		circle2.setRadius(2.2); 
		System.out.println("Test 3 to see if two circles are concentric: ");
		if(circle1.intersects(circle2)==true)
			System.out.println("Yes, circle1 intersects with circle2.");
		else
			System.out.println("No, circle1 does NOT intersect with circle2.");
				
	}
	
}