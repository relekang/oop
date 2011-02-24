package oving3;

import java.util.ArrayList;
import java.util.EmptyStackException;

import acm.program.ConsoleProgram;


/*
 * @startuml
 * object Kongefamilien
 * 
 * object Harald
 * object Sonja
 * object Martha
 * object Ari
 * object Haakon
 * object "Mette-Marit" as MetteMarit
 * object "Ingrid Alexandra" as Ingrid
 * object "Sverre Magnus" as Sverre
 * object "Maud Angelica" as Maud
 * object "Leah Isadora" as Leah
 * object "Emma Tallulah" as Emma
 * 
 * Harald --> "children" Haakon
 * Harald --> "children" Martha
 * Sonja --> "children" Haakon
 * Sonja --> "children" Martha
 * 
 * Haakon --> "children" Ingrid
 * Haakon --> "children" Sverre
 * MetteMarit --> "children" Ingrid
 * MetteMarit --> "children" Sverre
 * 
 * Martha --> "children" Maud
 * Martha --> "children" Leah
 * Martha --> "children" Emma
 * Ari --> "children" Maud
 * Ari --> "children" Leah
 * Ari --> "children" Emma
 * 
 * Kongefamilien --> "harald" Harald
 * Kongefamilien --> "sonja" Sonja
 * Kongefamilien --> "martha" Martha
 * Kongefamilien --> "ari" Ari
 * Kongefamilien --> "haakon" Haakon
 * Kongefamilien --> "metteMarit" MetteMarit
 * Kongefamilien --> "ingrid" Ingrid
 * Kongefamilien --> "sverre" Sverre
 * Kongefamilien --> "maud" Maud
 * Kongefamilien --> "leah" Leah
 * Kongefamilien --> "emma" Emma
 * 
 * @enduml
 */
public class Kongefamilien extends ConsoleProgram{
	private ArrayList<Person> kongefamilien;
	public void init(){
		kongefamilien = new ArrayList<Person>();
	}
	public void run(){
		createFamily();
	}
	private void createFamily(){
		Person harald = new Person();
		Person sonja = new Person();
		Person haakon = new Person();
		Person martha = new Person();
		Person mettemarit = new Person();
		Person ari = new Person();
		Person ingridalexandra = new Person();
		Person sigurdmagnus = new Person();
		Person maudangelica = new Person();
		Person leahisadora = new Person();
		Person emmatalulah = new Person();
		
		harald.name = "Harald";
		harald.children.add(haakon);
		harald.children.add(martha);
		
		sonja.name = "Sonja";
		sonja.children.add(haakon);
		sonja.children.add(martha);
		
		haakon.name = "Haakon";
		haakon.father = harald;
		haakon.mother = sonja;
		haakon.children.add(ingridalexandra);
		haakon.children.add(sigurdmagnus);
		
		mettemarit.name = "Mette Marit";
		mettemarit.children.add(ingridalexandra);
		mettemarit.children.add(sigurdmagnus);
		
		ingridalexandra.name = "Ingrid Alexandra";
		ingridalexandra.father = haakon;
		ingridalexandra.mother = mettemarit;
		
		sigurdmagnus.name = "Sigurd Magnus";
		sigurdmagnus.father = haakon;
		sigurdmagnus.mother = mettemarit;
		
		martha.name = "Martha";
		martha.father = harald;
		martha.mother = sonja;
		martha.children.add(maudangelica);
		martha.children.add(leahisadora);
		martha.children.add(emmatalulah);
		
		ari.name = "Ari";
		ari.children.add(maudangelica);
		ari.children.add(leahisadora);
		ari.children.add(emmatalulah);
		
		maudangelica.name = "Maud Angelica";
		maudangelica.father = ari;
		maudangelica.mother = martha;
		
		leahisadora.name = "Leah Isadora";
		leahisadora.father = ari;
		leahisadora.mother = martha;
		
		emmatalulah.name = "Emma Talulah";
		emmatalulah.father = ari;
		emmatalulah.mother = martha;
		
		kongefamilien.add(harald);kongefamilien.add(sonja);
		kongefamilien.add(haakon);kongefamilien.add(mettemarit);
		kongefamilien.add(ingridalexandra);kongefamilien.add(sigurdmagnus);
		kongefamilien.add(martha);kongefamilien.add(ari);
		kongefamilien.add(maudangelica);kongefamilien.add(leahisadora);kongefamilien.add(emmatalulah);
		
		for(Person person : kongefamilien ){
			println(person.name);
			try{
				println(" - Far: " + person.father.name);
				println(" - Mor: " + person.mother.name);
			} catch(NullPointerException e){}
			try{
				if(person.children.size() > 0){
					println("  - Barn:");
					for(Person kid : person.children){
						println("    + " + kid.name);
					}	
				}
			} catch(EmptyStackException e){}
		}
	}
}