/*
* Person.java
* Author: Lin, Wenhao	
* Submission Date: 10/31/2017
*
* Purpose:  This is a class representing a person in a real estate
market. Functionally, the Person class exists to encapsulate data 
about users of our program, such as whether or not they own a home,
 how much money they have etc
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
import java.text.DecimalFormat;


/**
 * Class representing a person (a human user) on a real estate market.
 * A person has a name, age, cash, and (potentially) a house.
 */
public class Person {

	/* Instance variables */

	private String name;
	private int age;
	private double cash;
	private House house;
	
	/* Constructors */

	/**
	 * The Default constructor creates a 20 year old John L. with a penny of cash and no home.
	 */
	public Person() {
		age = 20;
		house = null;
		name = "John L";
		cash = 0.01;
		// Your code here
		
	}
	/**
	 * A second constructor that enables the creation of a custom instance of the Person class. 
	 * The house instance variable is set to null.
	 * @param name : the person's name 
	 * @param age : the person's age
	 * @param cash : the amount of money the person has
	 */
	public Person(String name, int age, double cash) {
		house = null;
		this.name = name;
		this.age = age;
		this.cash = cash;
		
		// Your code here

	}

	/**
	 * A third constructor including a parameter for the persons house. The house is also updated
	 * because it is no longer for sale.
	 * @param name : the person's name 
	 * @param age : the person's age
	 * @param cash : the amount of money the person has
	 * @param house : the person's home
	 */
	public Person(String name, int age, double cash, House house) {
		this.name = name;
		this.age = age;
		this.cash = cash;
		this.house = house;
		
		// Your code here

	}
	
	/**
	 * Show the name and age of the person as well as their assets (cash and home if they have one).
	 * E.g.
	 * Name: John L.
	 * Age: 20 years old
	 * Cash: $ 0.01
	 */
	@Override
	public String toString() {
		DecimalFormat decimalFormatObj = (DecimalFormat) DecimalFormat.getInstance();
		decimalFormatObj.setDecimalSeparatorAlwaysShown(true);
		String s = "";
		s += "Name: " + getName() +"\n" + "Age: " + getAge() + " years old \n";
		decimalFormatObj.setMinimumFractionDigits(2);
        decimalFormatObj.setMaximumFractionDigits(2);
        s += "Cash: $ " + decimalFormatObj.format(getCash()) + "\n";
        if(house != null) {
        	s += "House info:  \n" +house.toString() + "\n";
        }
		
		// Your code here

		return s; // replace this line with your own code
	}
	
	/* Accessors / Getters */
	
	/**
	 * @return the person's name 
	 */
	public String getName() {
		
		

		return name; // replace this line with your own code
	}
	/**
	 * @return the person's age
	 */
	public int getAge() {
		
		// Your code here

		return age; // replace this line with your own code
	}
	
	/**
	 * @return the amount of cash this person has
	 */
	public double getCash() {
		
		// Your code here

		return cash; // replace this line with your own code
	}
	
	/**
	 * @return a reference the house object currently owned by this person
	 */
	public House getHouse() {
		
		// Your code here
		
		
		return house; // replace this line with your own code
	}
	

	/**
	 * @return true if this person has a home
	 */
	public boolean ownsAHouse() {
		
		if(house == null) return false;

		else return true; // replace this line with your own code
	}
	
	/* Mutators */
	
	/**
	 * @param amount : the amount of cash to give this person
	 */
	public void addCash(double amount) {
		
		cash += amount;

	}
	
	/**
	 * If this person owns home, put it up for sale and pay the person the price of the home.
	 */
	public void sellHome() {
		
		if(ownsAHouse() == true) {
			cash += house.getPrice();
			house.setForSale(true);
			this.house = null;
			System.out.println(getName() + " has sold their house!");
		}
		else System.out.println(getName() + " has no house to sell");
		
	}

	/**
	 * This method lets the person buy a home if they do not already have a home, have the cash and the home is for sale.
	 * If the person successfully buys a home, their cash is decreased by the cost of the home.
	 * @param h the house to be bought
	 */
	public void buyHouse(House h) {
		if(this.cash < h.getPrice()) {
			System.out.println(getName() + " cannot afford this home.");
		}
		else if(ownsAHouse()== true) {
			System.out.println(getName() + " is already a homeowner!.");
		}
		else if(h.isForSale()==false) {
			System.out.println("This house is not currently for sale.");
		}
		else {
			cash -= h.getPrice();
			house = h;
			h.setForSale(false);
			System.out.println(getName()+" is now a proud homeowner!");
		}
		// Your code here

	}
}
