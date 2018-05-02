/*
* Player.java
* Author: Lin,Wenhao
* Submission Date: 11/17/2017
*
* Purpose: This class represents a player in the game. It is going
* to has a Hand object as its instance variable.
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
* written consent from the Department of Computer Science.
*/
/**


/* 
* A player in a blackJack game
 *
 */
public class Player {
	/**
	 * The players hand
	 */
	private Hand hand;
	
	/**
	 * Instantiates the hand instance variable.
	 */
	public Player() {
		hand = new Hand();
		
	}
	
	/**
	 * @return true if the player has bused
	 */
	public boolean busted() {
		if(BlackJack.getValueOfHand(hand) > 21) {
			return true;
		}
		else
		return false;
	}
	
	/**
	 * empty the player hand into the cards
	 * @param d deck that receives the cards
	 */
	public void returnCardstoDeck(Deck d) {
		int number1 = hand.size();
		for(int i = 0; i < number1; i++) {
			d.addToBottom(hand.getCards()[i]);
			}
			hand.emptyHand();
		
	}
	/**
	 * @return the player's hand
	 */
	public Hand getHand() {
		return hand;
	}
}
