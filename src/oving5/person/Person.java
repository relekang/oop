package oving5.person;

import java.util.ArrayList;

public class Person {
	String name; char gender; Person mother, father;
	ArrayList<Person> children;
	public Person(char gndr){
		if(gndr == 'm' || gndr == 'f'){ gender = gndr; }
		children = new ArrayList<Person>();
	}
	public boolean isMale(){ return (gender == 'm'); }
	public boolean isFemale(){ return (gender == 'f'); }
	public String getName(){ return name; }
	public void setName(String inputName){
		boolean isNameOk = false;
		if(inputName == null){ name = inputName; }
		else {
			for (char ch : inputName.toCharArray()) {
				if (Character.isLetter(ch) || Character.isSpaceChar(ch)){ isNameOk = true; }
				else { isNameOk = false; return;}
			}
			if(isNameOk){ name = inputName; }
		}
	}
	public int getChildCount() {
		return children.size();
	}
	public Person getMother() {
		return mother;
	}
	public void setMother(Person newMother) {
		if(newMother != null){
			if(newMother.isFemale()){ 
				if(mother != null){ mother.removeChild(this);}
				mother = newMother;mother.addChild(this); 
			}
		} else if(mother != null){ Person tmp = mother; mother = null; tmp.removeChild(this); }
	}
	public Person getFather() {
		return father;
	}
	public void setFather(Person newFather) {
		if(newFather != null){ 
			if(newFather.isMale()){ 
				if(father != null){ father.removeChild(this); }
				father = newFather; father.addChild(this); 
			
			} 
		} else if(father != null){ Person tmp = father; father = null; tmp.removeChild(this); }
	}
	public void addChild(Person newChild) {
		if(!(containsChild(newChild))){
			if(this.isFemale()){ 
				if(newChild.getMother() != null){ newChild.getMother().removeChild(newChild); } 
				children.add(newChild);
				newChild.setMother(this); 
			} else if(this.isMale()){  
				if(newChild.getFather() != null){ newChild.getFather().removeChild(newChild); }
				children.add(newChild);
				newChild.setFather(this); 
			} else{ System.out.println("No gender"); }
//			System.out.println("Child "+this.getChildCount() + ":  " + newChild);
		} else{ System.out.println("Can't add a kid that has already been added"); }
	}
	public void removeChild(Person child){
		try{
			if(child.getFather() == this){ child.setFather(null);System.out.println(child.getFather()); children.remove(child);}
			else if(child.getMother() == this){ child.setMother(null); System.out.println(child.getMother());children.remove(child);}
			else {children.remove(child);}
			} catch(IndexOutOfBoundsException e){ System.out.println(e); }
	}
	public Person getChild(int index) {
		try{
			Person child = children.get(index);
			return child;
		} catch(IndexOutOfBoundsException e){ System.out.println("Out of bound[children]:add child"); return null; }
	}
	public int indexOfChild(Person child) {
		return children.indexOf(child);
	}
	public boolean containsChild(Person child){
		return (children.contains(child));
	}
	public boolean isAncestorOf(Person child) {
		if(child.getFather() == this || child.getMother() == this) return true;
		if(child.getFather() != null && isAncestorOf(child.getFather())) return true;
		if(child.getMother() != null && isAncestorOf(child.getMother())) return true;
		return false;
	}
}
