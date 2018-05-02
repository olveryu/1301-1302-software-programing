/*
* CreditCardPayOff.java
* Author: Lin,Wenhao
* Submission Date: 08/30/2017
*
* Purpose: This program can be a useful tool for computing desire credit card pay off 
* information.Just enter data for principal(account balance), annual percentage rate 
* and monthly payment,the program will output data for Month needed to pay off,
*  total amount paid total interest paid as well as the over payment.
*
* Statement of Academic Honesty:
*
* The following code represents my own work. I have neither
* received nor given inappropriate assistance. I have not copied
* or modified code from any source other than the course webpage
CSCI 1301: Project 1 Page 3
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

public class CreditCardPayOff {
	public static void main (String[] args) {
		
		// Declare Variable
		double principal, 
		annualInterestRate,
		monthlyPayment,
		monthsNeededToPayOff,
		totalAmountPaid, 
		totalInterestPaid,
		overPayment, 
		amountNeededToPayOff;
		
		int monthsNeededToPayOffRound;
		/*
		 a=ln(monthlyPayment)
		 b=ln(monthlyPayment-(annualInterestRate/1200.0)*principal)
		 c=(ln(annualInterestRate/1200.0+1.0))
		 */
		
		double d;
		
		//Declared Scanner
		System.out.print("Principal: \t\t\t");
		Scanner keyboard = new Scanner(System.in);
		principal=keyboard.nextDouble();

		System.out.print("Annual Interest Rate (%): \t");
		annualInterestRate = keyboard.nextDouble();
		
		System.out.print("Monthly Payment: \t\t");
		monthlyPayment = keyboard.nextDouble();
		System.out.println();
		
		//Declared Formula 
		
		monthsNeededToPayOff = (Math.log(monthlyPayment) -
				Math.log(monthlyPayment-((annualInterestRate/1200.0) * principal)))/
				Math.log((annualInterestRate/1200.0+1.0));
				
		amountNeededToPayOff= monthlyPayment * monthsNeededToPayOff;
		
				
				
		d = Math.ceil(monthsNeededToPayOff);
		monthsNeededToPayOffRound = (int)d;
		totalAmountPaid = monthlyPayment * monthsNeededToPayOffRound;
		totalInterestPaid = totalAmountPaid - principal;
		
		//over payment = total amount paid - amount needed to pay off
		overPayment = totalAmountPaid - amountNeededToPayOff;
		
		//print result
		System.out.println("Months Needed To Pay Off: \t" + monthsNeededToPayOffRound);
		System.out.printf("Total Amount Paid: \t\t$%.2f\n", totalAmountPaid);
		System.out.printf("Total Interest Paid: \t\t$%.2f\n", totalInterestPaid);
		System.out.printf("Overpayment: \t\t\t$%.2f", overPayment); 
		keyboard.close();
	}
}
