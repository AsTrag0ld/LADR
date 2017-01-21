package model;

import model.Ville;

public class Route {
    private boolean doubleRoute;
    private int taille;
    private String couleur;
    private boolean disponible;
    public Ville villeA;
    public Ville villeB;

    public Route(boolean doubleRoute, int taille, String couleur, Ville villeA, Ville villeB) {
    	this.doubleRoute = doubleRoute;
    	this.taille = taille;
    	this.couleur = couleur;
    	this.disponible = true;
    	this.villeA = villeA;
    	this.villeB = villeB;
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
	
	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
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
	 * Prend la route pour un joueur c'est-�-dire change sa disponibilit�
	 */
	public void prendre() {
    	this.setDisponible(false);
    }

}
