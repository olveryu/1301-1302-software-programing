/*
* Palindrome.java

* Author: Lin,Wenhao
* Submission Date: 09/26/2017
*
* Purpose: This program can be use for determined whether an user input number is a palindrome. 
*     The program can use output a summation of 1 to any palindrome number entered if its 
*     a palindrome.
* Statement of Academic Honesty:
*
* The following code represents my own work. I have neither
* received nor given inappropriate assistance. I have not copied
* or modified code from any source other than the course webpage
* CSCI 1301: Project 1 Page 3
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


import java.util.Scanner;
public class Palindrome {
	public static void main(String[] args) {
		//declare variable
		int r=0 ,reverse=0;
		Scanner myScanner=new Scanner(System.in);
		System.out.print("Please enter an integer > 0: ");
		int number = myScanner.nextInt();
		int numberEntered = number;
		if(number <= 0)
		{	System.out.println("Sorry, you must enter an integer greater than zero.");
			System.exit(0);
		}
		else
		{	while(number > 0)
			{	r = number % 10; //get the remainder
				number = number/10; //chop off the remainder in number
				reverse = reverse * 10 + r;  //obtaining reverse
			}	
		}	
		if(reverse != numberEntered)
		{	System.out.println("The integer " + numberEntered +" is not a palindrome.");
		}
		else
		{		int summation = (numberEntered*(numberEntered+1))/2;
			System.out.println("The integer " + numberEntered +" is a palindrome.");
			System.out.println("The sum of the numbers from 1 to " + numberEntered +" is "
			+ summation);
			
		}
		
		
	}
}
