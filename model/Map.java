package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public class Map extends Observable {
    private static Map instance;
    private LinkedList<Ville> villes;
    private LinkedList<Route> routes;
    private Hashtable voisins;

    private Map() {
    	this.villes = new LinkedList<Ville>();
    	this.routes = new LinkedList<Route>();
    	this.voisins = new Hashtable();
    }

    public static Map getInstance() {
    	if (instance == null) {
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
		Connection con = Service.getConnection();
    	try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM ville")) {
    		ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				lesVilles.add(new Ville(rs.getString("nom")));
			}
		} catch (Exception e) {
			throw new RuntimeException("Impossible de récupérer les villes");
		}
		this.villes = lesVilles;
	}
	
	/*
	 * Crée toutes les routes du plateau de jeu
	 */
	public void initialiserRoutes() {
		LinkedList<Route> lesRoutes = new LinkedList<Route>();
		Connection con = Service.getConnection();
    	try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM route")) {
    		ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				lesRoutes.add(new Route(rs.getInt("taille"), rs.getString("couleur"), new Ville(rs.getString("villeA")), new Ville(rs.getString("villeB"))));
			}
		} catch (Exception e) {
			throw new RuntimeException("Impossible de récupérer les routes");
		}
		this.routes = lesRoutes;
	}
	
	/*
	 * Affiche les routes qui peuvent être prise par les joueurs
	 */
	public void afficherRoutesDisponibles() {
		for (int i = 0; i < this.routes.size(); i++) {
			if (this.routes.get(i).isDisponible()) {
				System.out.println("Route " + i + " : " + this.routes.get(i));
			}	
		}
	}
	
	public void initialiserVoisins() {
		ArrayList<String> tmp = new ArrayList<String>();
		tmp.add("Sydney");
		tmp.add("Quebec");
		this.voisins.put("Halifax", tmp);
		tmp = new ArrayList<String>();
		tmp.add("Halifax");
		tmp.add("Chibougama");
		tmp.add("Ottawa");
		tmp.add("Labrador City");
		this.voisins.put("Quebec", tmp);
		tmp = new ArrayList<String>();
		tmp.add("Quebec");
		tmp.add("Inukjuak");
		tmp.add("Sydney");
		tmp.add("Grand Sault");
		this.voisins.put("Labrador City", tmp);
		tmp = new ArrayList<String>();
		tmp.add("Labrador City");
		tmp.add("Halifax");
		tmp.add("Millepertui");
		tmp.add("Grand Sault");
		this.voisins.put("Sydney", tmp);
		tmp = new ArrayList<String>();
		tmp.add("Quebec");
		tmp.add("Ottawa");
		this.voisins.put("Chibougama", tmp);
		tmp = new ArrayList<String>();
		tmp.add("Quebec");
		tmp.add("Chibougama");
		this.voisins.put("Ottawa", tmp);
		tmp = new ArrayList<String>();
		tmp.add("Labrador City");
		this.voisins.put("Inukjuak", tmp);
		tmp = new ArrayList<String>();
		tmp.add("Millepertui");
		tmp.add("Sydney");
		this.voisins.put("Grand Sault", tmp);
		tmp = new ArrayList<String>();
		tmp.add("Grand Sault");
		tmp.add("Sydney");
		this.voisins.put("Millepertui", tmp);
	}
	
	public ArrayList<String> getVoisins(String ville) {
		return (ArrayList<String>) this.voisins.get(ville);
	}
    
}
