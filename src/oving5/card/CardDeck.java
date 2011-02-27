package oving5.card;

import java.util.ArrayList;

public class CardDeck {
	ArrayList<Card> theCardDeck;
	private ArrayList<Card> dealList;
	private int topCardIndex;
	public CardDeck() {
		topCardIndex = -1;
		theCardDeck = new ArrayList<Card>();
		String[] suites = {"S", "H", "D", "C"};
		for (String suit : suites) {
			for (int i = 1; i <= 13; i++) {
				theCardDeck.add(new Card(suit,i));
				topCardIndex++;
			}
		}
	}
	public int getCardCount() {
		//if(dealList != null) return theCardDeck.size() + dealList.size();
		return topCardIndex + 1;
	}
	public Card getCard(int index) {
		if(index >= 0 && index < 52){
			return theCardDeck.get(index); 
		}
		return null;
	}
	public ArrayList<Card> deal(int remove) {
		 dealList = new ArrayList<Card>();
		 if(topCardIndex + 1 >= remove) {
//			while(getCardCount() > remove){
				for (int i = 0; i < remove; i++) {
					dealList.add(getCard(topCardIndex));
					topCardIndex--;
				}
//			}
				System.out.println(topCardIndex);
				//printCardDeck(dealList);
				return dealList;
		 }
		 return null;
	}
	public void printCardDeck(ArrayList<Card> deal){
		System.out.println("Deallist:");
		for (Card card : deal) {
			System.out.println(card.toString());
		}
		System.out.println();
		System.out.println("CardDeck("+getCardCount()+"):");
		for (Card card : theCardDeck) {
			System.out.println(card.toString());
		}
		System.out.println();
	}
}
