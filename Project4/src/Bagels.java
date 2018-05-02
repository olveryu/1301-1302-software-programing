/*
 * Bagels.java
 * Author: [Lin,Wenhao]
 * Submission Date: [10/30/2017]
 *
 * Purpose: The “driver” class that users will execute to play the game.
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
public class Bagels {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		char userInput = 'z';

		System.out.println("Welcome");

		boolean run = true;
		while(run) {
			System.out.print("Enter the number of digits to use: ");
			int numDigits = keyboard.nextInt();
			keyboard.nextLine();
			Engine engine1 = new Engine();
			engine1.setNumDigits(numDigits);
			int numDigit = engine1.getNumDigits();

			System.out.print("Enter the player's name: ");
			String playerName = keyboard.nextLine();
			Player player1 = new Player();
			player1.setName(playerName);
			System.out.println("");

			int round = 1;
			boolean playAgain=true;
			while(playAgain) {
				engine1.generateNewSecret();//generate new secret number
				/*int [] secret = new int[engine1.getSecretNumber().length];
			for(int i = 0; i <engine1.getSecretNumber().length; i ++) {
				secret[i] = engine1.getSecretNumber()[i];
			}
				 */

				System.out.println("Starting game # " + round + ".");// printout the current number of rounds

				boolean askAgain = true;

				int moves = 0;
				while(askAgain) {
					String guess = player1.askForGuess();//ask for guess
					Validator.validateGuess(engine1.getSecretNumber(), engine1.convertNumToDigitArray(guess), numDigit);
					boolean equal = true;//check if guess is equal to the secret/
					for(int i = 0; i <engine1.getSecretNumber().length; i++) {
						int x = engine1.getSecretNumber()[i];
						int y = engine1.convertNumToDigitArray(guess)[i];
						if(x != y) {
							equal=false;
							break;
						}	
					}
					moves++;
					if(equal == true) {
						askAgain = false;
						System.out.println("Congratulations!  You won in "+ moves +" moves.");
						System.out.println("");
					}
				}
				player1.updateStats(moves);
				System.out.println("Statistics for " + player1.getName() +":");
				System.out.println("Games completed: " + player1.getGamesCompleted());
				System.out.println("Number of digits: " + numDigits);
				System.out.println("Fastest win: " + player1.getFastestWin() +" guesses");
				System.out.println("p - Play again");
				System.out.println("r - Reset game");
				System.out.println("q - Quit");
				System.out.println("");

				//int [] guessArray = engine1.convertNumToDigitArray(guess);

				round++;
				System.out.print("What would you like to do?");
				userInput = keyboard.nextLine().charAt(0);
				System.out.println("");
				if(userInput =='q') {
					playAgain = false;
					System.exit(0);
				}
				if(userInput !='p') playAgain =false;
			}
			if(userInput == 'r') run = true;
		}	
	}
}
