package net.miladinov.strings;
import net.miladinov.generics.coffee.*;

import java.util.*;

public class ArrayListDisplay {
	public static void main(String[] args) {
		ArrayList<Coffee> coffees = new ArrayList<Coffee>();
		for (Coffee c : new CoffeeGenerator(10)) {
			coffees.add(c);
		}
	}
}