package net.miladinov.holding;
import net.miladinov.typeinfo.pets.*;
import java.util.*;
import static net.miladinov.util.Print.*;

public class MapOfList {
	public static Map<Person, List<? extends Pet>> petPeople = new HashMap<Person, List<? extends Pet>>();

	static {
		petPeople.put(new Person("Dawn"), Arrays.asList(new Cymric("Molly"), new Mutt("Spot")));
		petPeople.put(new Person("Kate"), Arrays.asList(new Cat("Shackleton"), new Dog("Margrett")));
		petPeople.put(
			new Person("Marilyn"),
			Arrays.asList(
				new Pug("Louie aka Snorkelstein Dupree"),
				new Cat("Stanford aka Stinky el Negro"),
				new Cat("Pinkola")
			)
		);
		petPeople.put(new Person("Luke"), Arrays.asList(new Rat("Fuzzy"), new Rat("Fizzy")));
		petPeople.put(new Person("Isaac"), Arrays.asList(new Rat("Freckly")));
	}

	public static void main(String[] args) {
		print("People: " + petPeople.keySet());
		print("Pets: " + petPeople.values());

		for (Person person : petPeople.keySet()) {
			print(person + " has: ");

			for (Pet pet : petPeople.get(person)) {
				print("   " + pet);
			}
		}
	}
}