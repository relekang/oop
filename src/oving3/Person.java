package oving3;

import java.util.ArrayList;

/*
 * @startuml
 * class Person {
 * 	String name
 * 	Person mother
 * 	Person father
 * }
 * Person --> "*" Person : children
 * @enduml
 */
public class Person {
	String name;
	Person mother;
	Person father;
	ArrayList<Person> children = new ArrayList<Person>();
	
	boolean isMotherOf(Person person){ return (person.mother == this && this.children.contains(person)); }
	boolean isFatherOf(Person person){ return (person.father == this && this.children.contains(person)); }
	boolean isSiblingOf(Person person){ return (person.father == this.father && person.mother == this.mother && !(person == this)); }
	
	
}