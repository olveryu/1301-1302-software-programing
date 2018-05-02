/*
* Engine.java
* Author: [Lin,Wenhao]
* Submission Date: [10/30/2017]
*
* Purpose: Encapsulates data and actions related to the actual game in progress. DO NOT program any
constructors for this class.
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
* on a programming project created by the Department of
* Computer Science at the University of Georgia. Any publishing
* of source code for this project is strictly prohibited without
* w
*/

import java.util.Random;
import static java.lang.Math.pow;
public class Engine {
	private int numDigits;
	private int[] secretNumber;
	private Random randomNumberGenerator = new Random();
	
	public int[] convertNumToDigitArray(String number) {
		int[] newNumber = new int[number.length()];
		for(int i = 0; i < number.length();i++) {
			String c = "";
			c += number.charAt(i);
			newNumber[i] = Integer.parseInt(c);
		}
		return newNumber;
	}
	public int getNumDigits() {
		return numDigits;
	}
	public int[] getSecretNumber() {
		return secretNumber;
	}
	public void generateNewSecret() {
		int lowerBound = (int)Math.pow(10, numDigits-1);
		int upperBound = (int)Math.pow(10, numDigits)-1;
		int randomNumber= randomNumberGenerator.nextInt((upperBound - lowerBound) + lowerBound);
		String randomNumberString = Integer.toString(randomNumber);
		int[] temp = convertNumToDigitArray(randomNumberString);
		secretNumber = new int[temp.length];
		for(int i = 0; i < temp.length; i ++) {
			secretNumber[i] = temp [i];
		}
		
	}
	public void setNumDigits(int newNumDigits) {
		numDigits = newNumDigits;
	}
	public void setSecretNumber(int[] newSecretNumber) {
		setNumDigits(newSecretNumber.length);
		for(int i = 0; i <newSecretNumber.length; i++) {
			secretNumber[i] = newSecretNumber[i];
		}
		
	}
	public static void main(String[] args) {
		System.out.println("Engine: setSecretNumber/getNumDigits test.");
		int[] testArray = {4, 2, 5, 1, 3, 9};
		Engine e = new Engine();
		e.setSecretNumber(testArray);
		boolean passed = true;
		if(e.getNumDigits() != testArray.length){
		passed = false;
		}
		System.out.println("Passed: " + passed);
	}
}
