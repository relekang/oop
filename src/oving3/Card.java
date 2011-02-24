package oving3;

/*
 * @startuml
 * class Card {
 * 	String suit
 * 	int face
 * }
 * @enduml
 */
public class Card {
	String suit;
	int face;
	public String toString(){ return suit+face; }
/*	String suit(String newSuit){
		//TODO String suit
		if(newSuit != null){ suit = newSuit;}
		return suit;
	}
	int face(){
		//TODO String suit
		if(newFace != null){ face = newFace;}
		return face;
	}
	*/
}