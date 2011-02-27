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
		if(this.face == card.face && this.suit == card.suit) return 0;
		else {
			int faceDiff = getFaceValue() - card.getFaceValue();
			int suitDiff = 0;
/*			
			switch (suitToChar()) {
			case 'S':
				if(card.getSuit() == "H") suitDiff = 13;
				else if(card.getSuit() == "D") suitDiff = 2*13;
				else if(card.getSuit() == "C") suitDiff = 3*13;
				break;
			case 'H':
				if(card.getSuit() == "S") suitDiff = -13;
				else if(card.getSuit() == "D") suitDiff = 13;
				else if(card.getSuit() == "C") suitDiff = 3*13;
				break;
			case 'd':
				if(card.getSuit() == "S") suitDiff = -2*13;
				else if(card.getSuit() == "H") suitDiff = -13;
				else if(card.getSuit() == "C") suitDiff = 13;
				break;
			case 'C':
				if(card.getSuit() == "S") suitDiff = -3*13;
 				else if(card.getSuit() == "H") suitDiff = -2*13;
				else if(card.getSuit() == "D") suitDiff = -13;
				break;
			}
*/
			return faceDiff + suitDiff;
		}
	}
	private boolean isSuit(String suit){
		return (suit == "S" || suit == "H" || suit == "D" || suit == "C");
	}
	private char suitToChar(){
		return this.suit.charAt(0);
	}
}
