package oving5.card;


public class Card {
	String suit; int face;
	public Card(String theSuit, int theFace){
		if(theSuit == "S" || theSuit == "H" || theSuit == "D" || theSuit == "C"){ 
			suit = theSuit;
		} else { suit = null; }
		if(theFace >= 0 && theFace <= 13){ face = theFace; } else { face = -1; }
	}
	public String getSuit() {
		return suit;
	}
	public int getFace() {
		return face;
	}
	public String toString(){
		return suit+""+face;
	}
}
