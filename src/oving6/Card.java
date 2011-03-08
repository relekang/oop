package oving6;

public class Card implements Comparable<Card> {
	private String suit;
	private int face;
	public  Card(String suit, int face) {
		if(isSuit(suit)) this.suit = suit;
		else suit = null;
		if(face > 0 && face < 14) this.face = face;
		else face = 0;
	}
	public String getSuit() {
		return suit;
	}
	public int getFace() {
		return face;
	}
	public int getFaceValue() {
		if(face == 1) return 14;
		else return face;
	}
	public String  toString() {
		return getSuit()+""+getFace();
	}
	public int compareTo(Card card) {
		int diff = 2000;
		if(this.getFaceValue() == card.getFaceValue() && this.getSuit() == card.getSuit()) return 0;
		else if(this.getFaceValue() == card.getFaceValue()){
			System.out.println(suitToChar()+"  "+card.getSuit());
			switch (suitToChar()) {
			case 'S':
				if(card.getSuit() == "H") diff = 1;
				else if(card.getSuit() == "D") diff = 1;
				else if(card.getSuit() == "C") diff = 1;
				break;
			case 'H':
				if(card.getSuit() == "S") diff = -1;
				else if(card.getSuit() == "D") diff = 1;
				else if(card.getSuit() == "C") diff = 1;
				break;
			case 'D':
				if(card.getSuit() == "S") diff = -1;
				else if(card.getSuit() == "H") diff = -1;
				else if(card.getSuit() == "C") diff = 1;
				break;
			case 'C':
				if(card.getSuit() == "S") diff = -1;
 				else if(card.getSuit() == "H") diff = -1;
				else if(card.getSuit() == "D") diff = -1;
				break;
			}
		} else {
				diff = this.getFaceValue() - card.getFaceValue();
		}
		System.out.println(this.toString() + card.toString() + ":"+diff);
		return diff;
	}
	private boolean isSuit(String suit){
		return (suit == "S" || suit == "H" || suit == "D" || suit == "C");
	}
	private char suitToChar(){
		return this.suit.charAt(0);
	}
}
