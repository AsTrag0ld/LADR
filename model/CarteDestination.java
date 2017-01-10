package model;

import model.Ville;

public class CarteDestination {
    private int valeur;
    private Ville villeA;
    private Ville villeB;

    public CarteDestination() {
    	this.valeur = 0;
    	this.villeA = new Ville();
    	this.villeB = new Ville();
    }
    
    /*
     * Algorithme du plus court chemin entre la ville A et la ville B
     */
    public void calculerValeur() {
    	
    }

	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
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
    
    

}
