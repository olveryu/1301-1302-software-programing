/*
* Player.java
* Author: [Lin,Wenhao]
* Submission Date: [10/30/2017]
*
* Purpose: Models the data and actions associated with the person playing the game. DO NOT program any
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

import java.util.Scanner;
public class Player {
	private String name;
	private int fastestWin;
	private int gamesCompleted;
	private Scanner keyboard = new Scanner(System.in);

	public String askForGuess() {
		System.out.print("Enter your guess: ");
		String guess = keyboard.nextLine();
		return guess;
	}
	public String getName() {
		return name;
	}
	public int getFastestWin() {
		return fastestWin;
	}
	public int getGamesCompleted() {
		return gamesCompleted;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void updateStats(int numberOfMovesTakenToWin) {
		if(gamesCompleted == 0) {
			fastestWin = numberOfMovesTakenToWin;
		}else {
			fastestWin = (numberOfMovesTakenToWin < fastestWin) ? numberOfMovesTakenToWin:fastestWin;
		}
		gamesCompleted++;
	}



}
