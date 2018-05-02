
/*
 * StringFun.java

 * Author: Lin,Wenhao
 * Submission Date: 09/26/2017
 *
 * Purpose: This program can process a number of different command entered by user to process the 
 * inputed strings. The different commands include quit,print reverse,replace all, replace 
 * single and remove which execute different functions.
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
public class StringFun {
	public static void main(String[] args) {



		//Declare variable;
		String result = "";

		System.out.println("Enter the string to be manipulated");
		Scanner myScanner = new Scanner(System.in);
		String inputString =myScanner.nextLine();
		while(true) {
			System.out.println("Enter your command "
					+ "(quit, print reverse, replace all, replace single, remove)");
			String command = myScanner.nextLine();

			if(command.equalsIgnoreCase("print reverse"))
			{	result = "";
			for(int i = inputString.length()-1; i >= 0; i--) {
				result= result+ inputString.charAt(i);
			}
			System.out.println(result);
			}
			else if (command.equalsIgnoreCase("quit"))
			{	System.exit(0);
			}
			else if (command.equalsIgnoreCase("remove"))
			{	System.out.println("Enter the character to remove");
			String remove = myScanner.nextLine();
			int index = inputString.indexOf(remove);
			if(index == -1 || (remove.length()>1)) {
				System.out.println("Error: the letter you are trying to remove does not exist");
			}
			else {
				while(index != -1) {	
					inputString = inputString.substring(0, index) + inputString.substring(index+1);
					index = inputString.indexOf(remove);
				}
				System.out.println("The new sentence is: " +inputString);
			}
			}	

			else if (command.equalsIgnoreCase("replace all"))
			{System.out.println("Enter the character to replace");
			String replace = myScanner.nextLine();
			if(replace.length()!= 1 || inputString.indexOf(replace)==-1)
				System.out.println("Error: the letter you are trying to remove does not exist");
			else
				System.out.println("Enter a new character");
			String newCharacter = myScanner.nextLine();
			int index1 = inputString.indexOf(replace);
			while(index1 != -1)
			{	inputString = inputString.substring(0,index1)+newCharacter + inputString.substring(index1+1);
			index1 = inputString.indexOf(replace);
			}
			System.out.println("The new string is: "+ inputString);
			}
			else if (command.equalsIgnoreCase("replace single"))
			{System.out.println("Enter the character to replace");
			char replace1 = myScanner.nextLine().charAt(0);
			if(inputString.indexOf(replace1) == -1 ) {
				System.out.println("Error: the letter you are trying to remove does not exist");
				break;
			}
			System.out.println("Enter the new character");
			char newChar = myScanner.nextLine().charAt(0);
			System.out.println("which " + replace1 + " would you like to replace?");
			char replaceIndexChar = myScanner.nextLine().charAt(0);
			boolean check;
			check=Character.isDigit(replaceIndexChar);
			if(check == false){	
				System.out.println("Sorry, you must enter a number");
				break;
			}else{
				int replaceIndex = Character.getNumericValue(replaceIndexChar);
				int counter = 0;
				for(int i = 0; i < inputString.length(); i++) {
					if(inputString.charAt(i) == replace1) {
						counter++;		
					}
					if(counter == replaceIndex) {
						inputString = inputString.substring(0,i) + newChar + inputString.substring(i+1);
						System.out.println("Your new sentence is: " + inputString);
						break;
					}
					if(i == inputString.length()-1) {
						System.out.println("Error: the letter you are trying to remove does not exist");
					}
				}
			}
			}
		}
	}
}




