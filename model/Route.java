package model;

import model.Ville;

public class Route {
    private boolean doubleRoute;
    private int taille;
    private boolean disponible;
    public Ville villeA;
    public Ville villeB;

    public Route() {
    	
    }
    
    public boolean estDisponible() {
    	return this.disponible;
    }
    
    public boolean isDoubleRoute() {
		return doubleRoute;
	}

	public void setDoubleRoute(boolean doubleRoute) {
		this.doubleRoute = doubleRoute;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public Ville getVilleA() {
		return villeA;
	}

	public void setVilleA(Ville villeA) {
		this.villeA = villeA;
	}

	public Ville getVilleB() {
		return villeB;
	}

	public void setVilleB(Ville villeB) {
		this.villeB = villeB;
	}

	/*
	 * Prend la route pour un joueur c'est-à-dire change sa disponibilité
	 */
	public void prendre() {
    	this.setDisponible(false);
    }

}
