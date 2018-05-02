package lab;

/*
* NetPay.java
* Author: Lin,Wenhao
* Submission Date: 08/30/2017
*
* Purpose: 
* This is a simple and useful program for computing one's gross pay 
* and net pay after deduction. Users just need to enter the number of hours worked,
* this program can output values for gross pay and net pay. It also displays the
* values for each of the deductions.  
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

import java.util.Scanner;

public class NetPay {
	public static void main(String[] args) {
		
		double soft;
		double softer;
		double answer;
		soft=1;
		softer = 22.2;
		answer= soft *softer;
		
		
		//declared constant value
		double FEDERAL_TAX_PERCENT  =0.1;
		double STATE_TAX_PERCENT    = 0.045;
		double SS_PERCENT           = 0.062;
		double MEDICARE_PERCENT     = 0.0145;
		double PAY_PER_HOUR         = 7.25;	
		
		
		//declared variable
		double hoursPerWeek;
		double grossPay;
		double netPay;
		double federal;
		double state;
		double ss;
		double medicare;

		//declared scanner
		Scanner keyboard1 = new Scanner(System.in);
		System.out.print("hours Per Week:\t");
		hoursPerWeek = keyboard1.nextDouble();
		
		//Declare Formula
		grossPay = hoursPerWeek * PAY_PER_HOUR;
		federal = grossPay * FEDERAL_TAX_PERCENT;
		state = grossPay * STATE_TAX_PERCENT;
		ss = grossPay * SS_PERCENT;
		medicare = grossPay * MEDICARE_PERCENT;
		netPay= grossPay - federal - state - ss - medicare;
		
		
		
		//Round Decimal to fifth place
		
		//print out result
		System.out.println("Gross Pay:\t" + grossPay);
		System.out.println("Net Pay:\t\t" + netPay);
		System.out.println();
		System.out.println("Deductions");
		System.out.println("Federal:\t" + federal);
		System.out.println("State:\t\t" + state);
		System.out.println("SS:\t\t" + ss);
		System.out.println("Medicare:\t" + medicare);
		
		keyboard1.close();
	}
}
