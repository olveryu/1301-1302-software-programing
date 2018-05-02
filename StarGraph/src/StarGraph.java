/*
* [StarGraph].java\
* Author: [Lin,Wenhao]
* Submission Date: [10-11-2017]
*
* Purpose: This program takes user input (#arraySize, minX and increment value) and out put and list of values
* and produce a graph based on the output values.
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
* of Computer Science at the University of Georgia.
*/

import java.text. DecimalFormat;
import java.util.Scanner;
public class StarGraph {
	public static void main(String[] args){
		Scanner keyboard  = new Scanner(System.in);
		//prompting user to imput values
		System.out.print("Please enter the number of x value to process: ");
		int arraySize = keyboard.nextInt();
		if(arraySize<=0) { // Checking for valid input
			System.out.println("The number of x values must be an integer greater"
					+ " than zero.");
			System.exit(0);
		}
		System.out.print("Enter a minimun value for x: ");
		double minX = keyboard.nextDouble();
		System.out.print("Enter the amount to increment x: ");
		double increment = keyboard.nextDouble();
		if(increment <= 0) {//checking for valid input
			System.out.println("The increment must be a decimal number greater than 0.");
			System.exit(0);
		}
		//declare array
		double [] y = new double[arraySize];
		double [] x = new double[arraySize];
		//to round to 3 decimal places
		DecimalFormat df = new DecimalFormat("0.000");
		
		System.out.println();
		System.out.println("Values");
			for(int count = 0; count < arraySize; count++) {
				x[count] = minX + increment * count;
				y[count] = 20.0*Math.abs(Math.sin(x[count])); 
				y[count] = Math.round(y[count]*1000.0)/1000.0;
				System.out.println("x: "+ df.format(x[count]) +", "+"y: " + df.format(y[count]));				
		}
		System.out.println();
		System.out.println("Graph");
		for(int i = 0; i < arraySize; i++) {
			System.out.print(":");
			for(int d = 1; d<y[i]; d++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
