/*
* Hand.java
* Author: Lin,Wenhao
* Submission Date: 11/17/2017
*
* Purpose: This class represents a hand of cards. both dealer and 
* player are going to have a hand object to represent the card they
* hold in hand.
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
/**
 * 
 * @author ghyzel
 *
 */
public class Hand {
	
	/**
	 * The cards in the hand
	 */
	private Card [] cards;
	
	/**
	 * Creates an empty hand
	 */
	public Hand() {
		//Initializing an empty array. 
		//Calling cards.length on this array would return 0
		cards = new Card[0];		
	}

	/**
	 * Adds Card c to the hand
	 * 
	 * @param c card to be added
	 */
	public void addCard(Card c) {
		/*creating a card array call tempCards to copy over the old cards and add the new
		card to the end*/
		Card[] tempCards = new Card[cards.length+1];
		for(int i = 0; i < cards.length; i ++) {
			tempCards[i] = cards[i];
		}
		tempCards[tempCards.length-1] = c;
		/*copy over all the elements from tempCard array and store to the instance array 
		variable*/
		cards = new Card[tempCards.length];
		for(int i = 0; i < tempCards.length; i++) {
			cards[i] = tempCards[i];
		}
	}
	
	/**
	 * @return number of cards in the hand
	 */
	public int size() {
		return cards.length;
	}
	
	/**
	 * Returns an array of all the cards in the hand
	 * 
	 * @return the cards in the hand
	 */
	public Card[] getCards() {
		Card[] tempCard = new Card[cards.length];
		for(int i = 0; i < cards.length; i ++) {
			tempCard[i] = cards[i];
		}
		return tempCard;
	}
	
	/**
	 * Empties the hand, and returns an array containing the discarded cards.
	 * 
	 * @return the discarded cards
	 */
	public Card[] emptyHand() {
		//copy over the cards to be discarded
		Card[] tempCard = new Card[cards.length];
		for(int i = 0; i < cards.length; i++) {
			tempCard[i] = cards[i];
		}
		//empty all the cards;
		cards = new Card[0];
		//return the discarded cards.
		return tempCard;
	}
	
	/**
	 * Returns a String representation of the hand
	 * 
	 * E.g.
	 * 
	 * "Empty Hand"
	 * "1. ACE OF SPADES\n2. QUEEN OF HEARTS"
	 * 
	 * @return a String representing the hand
	 */
	@Override
	public String toString() {
		// HINT: Use the toString() method of the card class
		String cardsOnHand = "";
		if(cards.length == 0) {
			cardsOnHand = "Empty Hand";
		}
		else {
			for(int i = 0, j = 1;i < cards.length; i++,j++) {
				cardsOnHand += j + "." + cards[i].toString()+ "\n";
			}
		}
		return cardsOnHand;
	}
	
	
	
}
