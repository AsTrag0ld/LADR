package com.projet.ladr.model;

import android.content.Context;

import com.projet.ladr.controler.DatabaseHandler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public class Map extends Observable {
    private static Map instance;
    private LinkedList<Ville> villes;
    private LinkedList<Route> routes;

    private Map() {
    	this.villes = new LinkedList<Ville>();
    	this.routes = new LinkedList<Route>();
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
	 * Cr�e toutes les villes du plateau de jeu
	 */
	public void initialiserVilles(Context context) {
		LinkedList<Ville> lesVilles = new LinkedList<Ville>();
		DatabaseHandler db = new DatabaseHandler(context);
		this.villes = db.getAllVilles();
	}
	
	/*
	 * Cr�e toutes les routes du plateau de jeu
	 */
	public void initialiserRoutes(Context context) {
		LinkedList<Route> lesRoutes = new LinkedList<Route>();
		DatabaseHandler db = new DatabaseHandler(context);
		this.routes = db.getAllRoutes();
	}
	
	/*
	 * Affiche les routes qui peuvent �tre prise par les joueurs
	 */
	public ArrayList<Route> getRoutesDisponibles() {
		ArrayList<Route> res = new ArrayList<>();
		for (int i = 0; i < this.routes.size(); i++) {
			if (this.routes.get(i).isDisponible()) {
				res.add(this.routes.get(i));
			}	
		}
		return res;
	}
    
}
