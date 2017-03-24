package model;

import model.Ville;

/*
 * Nombre de cartes par rapport à leurs points :
 * 4 : 1
 * 5 : 1
 * 6 : 1
 * 7 : 2
 * 8 : 3
 * 9 : 4
 * 10 : 2
 * 11 : 4
 * 12 : 2
 * 13 : 3
 * 16 : 1
 * 17 : 2
 * 20 : 2
 * 21 : 1
 * 22 : 1
 */
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
		return "CarteDestination [valeur=" + valeur + ", villeA=" + villeA.getNom() + ", villeB=" + villeB.getNom() + "]";
	}  
    

}
