package model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;

public class TestHashtable {
	static Map m;
	static Hashtable table;
	
	public static boolean test(String villeA, String villeB, ArrayList<String> marquage) {
		ArrayList<String> voisins = getVoisins(villeA);
		System.out.println("Voisins de " + villeA + " : " + voisins);
		if (voisins.contains(villeB)) {
			return true;
		} else {
			marquage.add(villeA);
			for (String s : voisins) {
				if (!(marquage.contains(s)) && table.contains(s)) {
					test(s, villeB, marquage);
				}
			}
			return false;
		}
	}
	
	public static ArrayList<String> getVoisins(String ville) {
		return (ArrayList<String>) table.get(ville);
	}
	
	public static void main(String[] args) {
		m = Map.getInstance();
		m.initialiserVoisins();
		//System.out.println(m.getVoisins("Halifax"));
		
		CarteDestination c = new CarteDestination(5, new Ville("Halifax"), new Ville("Labrador City"));
		
		Route r1 = new Route(2, "rouge", new Ville("Halifax"), new Ville("Sydney"));
		Route r2 = new Route(5, "blanc", new Ville("Sydney"), new Ville("Labrador City"));
		Route r3 = new Route(5, "vert", new Ville("Grand Sault"), new Ville("Labrador City"));
		Route r4 = new Route(5, "orange", new Ville("Labrador City"), new Ville("Inukjuak"));
		Route r5 = new Route(4, "rouge", new Ville("Halifax"), new Ville("Quebec"));
		LinkedList<Route> lesRoutes = new LinkedList<Route>();
		lesRoutes.add(r1);
		lesRoutes.add(r2);
		lesRoutes.add(r3);
		
		table = new Hashtable();
		for (Route r : lesRoutes) {
			table.put(r.villeA.getNom(), m.getVoisins(r.villeA.getNom()));
			table.put(r.villeB.getNom(), m.getVoisins(r.villeB.getNom()));
		}
		
		ArrayList<String> marquage = new ArrayList<String>();
		
		String villeA = c.getVilleA().getNom();
		String villeB = c.getVilleB().getNom();
		
		
		System.out.println(test(villeA, villeB, marquage));
			
	}

}
