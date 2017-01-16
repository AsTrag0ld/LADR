package model;

import model.Ville;

public class CarteDestination {
    private int valeur;
    private Ville villeA;
    private Ville villeB;

    public CarteDestination(int valeur, Ville villeA, Ville villeB) {
    	this.valeur = valeur;
    	this.villeA = villeA;
    	this.villeB = villeB;
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

	@Override
	public String toString() {
		return "CarteDestination [valeur=" + valeur + ", villeA=" + villeA + ", villeB=" + villeB + "]";
	}  
    

}
