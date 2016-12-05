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

}
