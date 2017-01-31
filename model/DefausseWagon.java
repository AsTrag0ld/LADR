package model;

import java.util.LinkedList;
import java.util.List;

public class DefausseWagon {
    private int nbCartes;
    private LinkedList<CarteWagon> carteWagon;

    public DefausseWagon() {
    	this.carteWagon = new LinkedList<CarteWagon>();
    	this.nbCartes = this.carteWagon.size();
    }
    
   	public int getNbCartes() {
		return nbCartes;
	}

	public void setNbCartes(int nbCartes) {
		this.nbCartes = nbCartes;
	}

	public LinkedList<CarteWagon> getCarteWagon() {
		return carteWagon;
	}

	public void setCarteWagon(LinkedList<CarteWagon> carteWagon) {
		this.carteWagon = carteWagon;
	}
	
	/*
     * Supprime tous les éléments contenus dans la défausse
     */
    public void vider() {
    	this.nbCartes = 0;
    	this.carteWagon.clear();
    }
    
    /*
     * Ajoute une carte à la défausse
     */
    public void ajouterCarte(CarteWagon c) {
    	this.carteWagon.add(c);
    }
    
    

}