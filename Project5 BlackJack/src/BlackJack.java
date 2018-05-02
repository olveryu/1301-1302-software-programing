/*
* BlackJack.java
* Author: Lin,Wenhao
* Submission Date: 11/17/2017
*
* Purpose: This class represents a simple player blackjack game
* includes three instance variable deck, player and dealer.
* a important method in this class is getValueOfHand, it calcultes
* the total value of cards in a hand taking in consideration 
* whether Ace should be added as 1 or 11.
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
 * Class representing a single player blackjack game
 */
public class BlackJack {
	
	private Deck deck;
	private Dealer dealer;
	private Player player;


	/**
	 * Constructs and prepares for a new game of BlackJack.
	 * Creates player, dealer and deck objects then shuffles
	 * the deck and gives both the dealer and player two cards.
	 */
	public BlackJack() {
		player = new Player();
		dealer = new Dealer();
		deck = new Deck();
		deck.shuffle();
		player.getHand().addCard(deck.draw()); 
		player.getHand().addCard(deck.draw());
		dealer.getHand().addCard(deck.draw());
		dealer.getHand().addCard(deck.draw());
		
	}
	/**
	 * Restarts in a few steps
	 * 1. The Player and dealer return their cards to the deck.
	 * 2. The deck is shuffled.
	 * 3. Both the player and the dealer receive two cards drawn form the top of the deck.
	 */
	public void restart() {
		//empty player's hand and return cards to deck
		int number =player.getHand().size(); 
		for(int i = 0; i < number; i++) {
		deck.addToBottom(player.getHand().getCards()[i]);
		}
		player.getHand().emptyHand();
		//empty dealer's hand and return cards to deck
		int number1 = dealer.getHand().size();
		for(int i = 0; i < number1; i++) {
			deck.addToBottom(dealer.getHand().getCards()[i]);
			}
			dealer.getHand().emptyHand();
		//shuffle the deck
		deck.shuffle();
		//both dealer and player receive two cards
		player.getHand().addCard(deck.draw()); 
		player.getHand().addCard(deck.draw());
		dealer.getHand().addCard(deck.draw());
		dealer.getHand().addCard(deck.draw());
	}
	/**
	 * Returns the value of a card in a standard game of Blackjack based on the type of the card
	 * ex. An Ace would return 1, a 2 would return 2... 
	 * @param c card whose value is extracted
	 * @return value of the card
	 */
	public static int getValueOfCard(Card c) {
		switch(c.getType()) {
		case ACE :return 1;
		case TWO: return 2;
		case THREE: return 3;
		case FOUR: return 4;
		case FIVE: return 5;
		case SIX: return 6;
		case SEVEN: return 7;
		case EIGHT: return 8;
		case NINE: return 9;
		case TEN: return 10;
		case JACK: return 10;
		case QUEEN: return 10;
		case KING: return 10;
		}
		return 0;
		
	}
	/**
	 * Returns the maximum value of the hand that does not result in a bust
	 * @param h Hand whose value is returned
	 * @return value of h
	 */
	public static int getValueOfHand(Hand h) {
		int totalValue = 0;
		boolean contain = false;
		for(int i = 0; i < h.size(); i++) {
			if(getValueOfCard(h.getCards()[i]) == 1) {
				contain = true;
				continue;
			}
			totalValue += getValueOfCard(h.getCards()[i]);
		}
		if(contain == true) {
			if(totalValue + 11 <= 21 ) {
				totalValue += 11;
				return totalValue;
			}
			else {
				totalValue += 1;
				return totalValue;
			}
		}
		else {
			return totalValue;
		}
	}

	/**
	 * @return Deck used to play
	 */
	public Deck getDeck() {
		return deck;
	}
	
	/**
	 * @return Dealer of the game
	 */
	public Dealer getDealer() {
		return dealer;
	}
	
	/**
	 * @return Player playing the blackjack game
	 */
	public Player getPlayer() {
		return player;
	}

}
