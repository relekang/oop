package oving5.card;

import java.util.ArrayList;

public class CardDeck {
	ArrayList<Card> theCardDeck;
	public CardDeck() {
		theCardDeck = new ArrayList<Card>();
		String[] suites = {"S", "H", "D", "C"};
		for (String suit : suites) {
			for (int i = 1; i <= 13; i++) {
				System.out.println("CD: "+suit+" "+i);
				theCardDeck.add(new Card(suit,i));
			}
		}
	}
	public int getCardCount() {
		return theCardDeck.size();
	}
	public Card getCard(int index) {
		if(index > 0 && index <= 52 && index < getCardCount() && getCard(index) != null){
			return getCard(index); 
		}
		return null;
	}
	public ArrayList<Card> deal(int remove) {
		ArrayList<Card> dealList = new ArrayList<Card>();
		if(getCardCount() > remove){
			for (int i = 1; i <= remove; i++) {
				int cardnr = theCardDeck.size() - 1;
				dealList.add(theCardDeck.get(cardnr));
				theCardDeck.remove(cardnr);
			}
		} else { return null; }
		return dealList;
	}
}
