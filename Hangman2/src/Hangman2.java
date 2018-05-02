/*
* Hangman2.java
* Author: [Wenhao Lin]
* Submission Date: [10-10-2017]
*
* Purpose: This is a word guessing games. Players can select easy, intermediate and hard level. 
* Each levels allow different number of guesses and spaces to check. Players are failed if they used up all the guess.
* users can play a maximum of 20 times for the game.
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
public class Hangman2 {
	private static final boolean testingMode = true;
	public static void main(String[] args) {
		Scanner keyboard= new Scanner(System.in);
		boolean run = true;
		boolean unknownDifficulty = true;
		String difficulty = "";
		char difficultyChar='a';
		String playAgain;
		int spaces = 0;
		int guesses = 0;
		String dash = "-";
		String secretWord = RandomWord.newWord();
		
		int round = 0;
		while(run) {
			round++;
			secretWord = RandomWord.newWord();
			String update="-";
			while(unknownDifficulty) {//validating user input
				System.out.println("Enter your difficulty:Easy (e), Intermediate (i), or Hard(h)");
				difficulty = keyboard.nextLine().toLowerCase();
				difficultyChar = difficulty.charAt(0);
				if(difficultyChar == 'e' || difficultyChar == 'i' || difficultyChar == 'h' ) {
					unknownDifficulty = false;
				}else {
					System.out.println("Invalid difficulty.   Try Again...");
				}
			}
			//delaring variable for number of guesses and spaces
			if(difficultyChar == 'e') { guesses = 15; spaces = 4;}
			if(difficultyChar == 'i') {guesses = 12; spaces = 3;}
			if(difficultyChar == 'h') {guesses = 10; spaces = 2;}

			//statement to show the secret word
			if(testingMode == true) {
				System.out.println("The secret word is: "+ secretWord);}

			//declaring the dashes
			System.out.print("Your secret word is: ");
			int i;
			for(i=0; i<secretWord.length(); i++) {
				System.out.print(dash);
				}
			for(int f=1;f<secretWord.length();f++) {
				update=update + dash;
			}
			System.out.println();

			//game start
			boolean gameRun = true;
			String letterToGuess1 = null;//initial input
			char letterToGuess = 0;//taking first letter
			boolean check;
			String spacesEntered = null;

			
				boolean wrongInput = true;
				
			while(gameRun) {//game start
				while(wrongInput) {//validating user input
					System.out.println("Please enter the letter you want to guess: ");
					letterToGuess1 = keyboard.nextLine();
					letterToGuess = letterToGuess1.charAt(0);
					check = Character.isDigit(letterToGuess);
					boolean containLetter = false;

					//validating user input for letter
					if(check == true ) {
						System.out.println("Your input is not valid. Try Again.");
						System.out.println("Guesses remaining: " + guesses);
					}
					//for solve the answer part
					else if(letterToGuess1.equalsIgnoreCase("solve")) {
						System.out.println("Please solve the answer: ");
						String solve = keyboard.nextLine();
						if(solve.equalsIgnoreCase(secretWord)) {
							System.out.println("You win!");
							System.out.println("You have guessed the word! Congratulations!");
							wrongInput = false;
						}
						else {
							System.out.println("That's not the secret word");
							guesses--;
							System.out.println("Guesses ramaining: " +guesses);
						}
					}
					else if(check == false) {
						System.out.println("Please enter the spaces you want to check (separated by spaces)");
						spacesEntered = keyboard.nextLine();
						if(spacesEntered.length()==spaces*2 || spacesEntered.length()==spaces *2 - 1){
							for(int b = 0; b < spacesEntered.length(); b ++) {
								containLetter = Character.isLetter(spacesEntered.charAt(b));
								if(containLetter == true) {
									System.out.println("Your input is not valid. Try Again.");
									System.out.println("Guesses remaining: " + guesses);
									break;
								}
								int outOfBound = 0;
								if(containLetter==false) {
									for(int n = 0; n< spacesEntered.length();n++)
									{
										int aa = Character.getNumericValue(spacesEntered.charAt(n));
										if(aa==-1) continue;
										if(aa>secretWord.length()-1)
										{
											System.out.println("Your input is not valid. Try Again.");
											System.out.println("Guesses remaining: " + guesses);
											wrongInput = true;
											outOfBound++;
											break;
										}
									}
								}
								if(outOfBound!=0) break;
								if(outOfBound == 0)
									{
									wrongInput = false;
									break;
									}
							}
						}
						else {
							System.out.println("Your input is not valid. Try Again.");
							System.out.println("Guesses remaining: " + guesses);
							wrongInput = true;
						}
					}
						
				}
				wrongInput = true;
				if(letterToGuess1.equalsIgnoreCase("solve"))
				{
					gameRun = false;
					break;
				}
				 	
				//validating user guess
					int contain = 1;
					for(int n = 0; n< spacesEntered.length();n++)
					{
						int aa = Character.getNumericValue(spacesEntered.charAt(n));
						if(aa==-1)
							continue;
		
						if(secretWord.charAt(aa)== letterToGuess) 
						{
						update = update.substring(0,aa)+letterToGuess +update.substring(aa+1);
						contain++;
						}		
					}
					if(contain == 1)
					{
						System.out.println("Your letter was not found in the spaces you provided!");
						guesses--;
						System.out.println("Guesses remaining: " + guesses);
						if(guesses==0)
						{
							System.out.println("You have failed to guess the word...:( ");
							gameRun = false;
						}
					}
					else if (contain !=1 && update.equals(secretWord)==false)
					{
						System.out.println("Your guess is in the word!");
						System.out.println("The update word is: " + update);
						System.out.println("Guesses remaining: " + guesses);
					}	
					else {
						System.out.println("Your guess is in the word!");
						System.out.println("The update word is: " + update);
						System.out.println("Guesses remaining: " + guesses);
						System.out.println("You have guessed the word! Congratulations!");
					}
				
				//boolean wrongGuess = true;
				//while(wrongGuess)
					//gameRun = false;
	           // if(guesses==0) gameRun = false;
					if(update.equals(secretWord))
					{
						gameRun=false;
					}
				}	
			if(round == 20)
			{
				System.out.println("You've reached the maximum number of attempts");
				System.exit(0);
			}
			boolean true1=true;
			while(true1)
			{
			System.out.println("Would you like to play again? Yes(y) or No(n)");//ask user if wanted to play again
			playAgain = keyboard.nextLine().toLowerCase();
			unknownDifficulty = true;
			if(playAgain.equals("y")||playAgain.equals("yes")) 
				{run = true;
				true1 = false;
				}
			else if (playAgain.equals("n")||playAgain.equals("no")) 
				{run = false;
				System.exit(0);
				}
			else
			{
				System.out.println("Invalid input. Try again...");
			}
			}
		}
	}
	
	
}

