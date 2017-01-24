package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public class Map extends Observable {
    private static Map instance = null;
    private LinkedList<Ville> villes;
    private LinkedList<Route> routes;

    private Map() {
    	this.villes = new LinkedList<Ville>();
    	this.routes = new LinkedList<Route>();
    	initialiserVilles();
    	initialiserRoutes();
    }

    public static Map getInstance() {
    	if (null == instance) {
    		instance = new Map();
    	}
    	return instance;
    }

	public List<Ville> getVilles() {
		return villes;
	}

	public void setVilles(LinkedList<Ville> villes) {
		this.villes = villes;
	}

	public List<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(LinkedList<Route> routes) {
		this.routes = routes;
	}
	
	public Ville getVilleParNom(String nom) {
		for (Ville v : this.villes) {
			if (v.getNom() == nom) {
				return v;
			}
		}
		return null;
	}
	
	/*
	 * Crée toutes les villes du plateau de jeu
	 */
	public void initialiserVilles() {
		LinkedList<Ville> lesVilles = new LinkedList<Ville>();
		lesVilles.add(new Ville("Whitehorse"));
		lesVilles.add(new Ville("Fort Good Hope"));
		lesVilles.add(new Ville("Tungstene"));
		lesVilles.add(new Ville("Fort Saint-Jean"));
		lesVilles.add(new Ville("Prince George"));
		lesVilles.add(new Ville("Echo Bay"));
		lesVilles.add(new Ville("Yellowknife"));
		lesVilles.add(new Ville("Fort Smith"));
		lesVilles.add(new Ville("Fort Vermilion"));
		lesVilles.add(new Ville("Edmonton"));
		lesVilles.add(new Ville("Vancouver"));
		lesVilles.add(new Ville("Victoria"));
		lesVilles.add(new Ville("Calgary"));
		lesVilles.add(new Ville("Uranium City"));
		lesVilles.add(new Ville("Saskatoon"));
		lesVilles.add(new Ville("Regina"));
		lesVilles.add(new Ville("Thompson"));
		lesVilles.add(new Ville("Churchill"));
		lesVilles.add(new Ville("Baker Lake"));
		lesVilles.add(new Ville("Resolute"));
		lesVilles.add(new Ville("Pond Inlet"));
		lesVilles.add(new Ville("Winnipeg"));
		lesVilles.add(new Ville("Thunder Bay"));
		lesVilles.add(new Ville("Ivujivik"));
		lesVilles.add(new Ville("Inukjuak"));
		lesVilles.add(new Ville("Eastmain"));
		lesVilles.add(new Ville("Hearst"));
		lesVilles.add(new Ville("Sudbury"));
		lesVilles.add(new Ville("Windsor"));
		lesVilles.add(new Ville("Oshawa"));
		lesVilles.add(new Ville("Ottawa"));
		lesVilles.add(new Ville("Amos"));
		lesVilles.add(new Ville("Chibougama"));
		lesVilles.add(new Ville("Quebec"));
		lesVilles.add(new Ville("Labrador City"));
		lesVilles.add(new Ville("Crand Sault"));
		lesVilles.add(new Ville("Halifax"));
		lesVilles.add(new Ville("Sydney"));
		lesVilles.add(new Ville("Millepertuis"));
		this.villes = lesVilles;
	}
	
	/*
	 * Crée toutes les routes du plateau de jeu
	 */
	public void initialiserRoutes() {
		
	}
    
}
