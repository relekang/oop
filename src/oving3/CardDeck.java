package oving3;

import java.util.ArrayList;

import acm.graphics.GImage;
import acm.program.ConsoleProgram;
import acm.program.GraphicsProgram;

/*
 * @startuml
 * class Card {
 * 	String suit
 * 	int face
 * }
 * class CardDeck {
 * 	+Card getCard(int i)
 * }
 * CardDeck --> "*" Card : cards
 * @enduml
 */

public class CardDeck extends GraphicsProgram {
	ArrayList<Card> cards;
	public void init(){
		cards = new ArrayList<Card>();
		for(int i = 0;i < 4; i++){
			for(int ii = 1; ii <= 13; ii++){
				int arraynr  = (13*i)+(ii-1);
				switch(i){
					case 0:
						Card s = new Card();
						s.suit = "S";
						s.face = ii;
						cards.add(arraynr, s); 
						break;
					case 1:
						Card h = new Card();
						h.suit = "H";
						h.face = ii;
						cards.add(arraynr, h); 
						break;
					case 2:
						Card d = new Card();
						d.suit = "D";
						d.face = ii;
						cards.add(arraynr, d); 
						break;
					case 3:
						Card c = new Card();
						c.suit = "C";
						c.face = ii;
						cards.add(arraynr, c); 
						break;
				}
				
			}
		}
/*		for(Card theCard : cards){
			try{
			println(cards.indexOf(theCard) + " " + theCard.toString());
			} catch(NullPointerException e){}
		}
*/
	}
	public void run() {
		setSize(800, 500);
		double x = 0; double y = 0;
		for(Card card : cards){
			if(card.suit == "S"){y = 50;}else if(card.suit == "H"){y = 140;}else if(card.suit == "D"){y = 230;}else if(card.suit == "C"){y = 320;}
			GImage img = createGImage(card.suit, card.face);
			x = card.face * 50;
			this.add(img,x,y); 
		}
	}
	public Card getCard(int position){ return cards.get(position); }
	GImage createGImage(String suit, int value) {
		String name="";
		switch(suit.charAt(0)){
		case 'H':
			name+="hearts";
			break;
		case 'D':
			name+="diamonds";
			break;
		case 'C':
			name+="clubs";
			break;
		case 'S':
			name+="spades";
			break;
		}
		name+="-";
		switch(value) {
		case 1:
			name+="a";
			break;
		case 11:
			name+="j";
			break;
		case 12:
			name+="q";
			break;
		case 13:
			name+="k";
			break;
		default:
			name+="" + value;
		}
		
		name+="-150.png";
		return new GImage("oving3/img/" + name);
	}
}
