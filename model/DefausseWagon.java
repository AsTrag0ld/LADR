package model;

import java.util.LinkedList;
import java.util.List;

public class DefausseWagon {
    private int nbCartes;
    private LinkedList<CarteWagon> carteWagon;
    private LinkedList<CarteLocomotive> carteLocomotive;

    public DefausseWagon() {
    	this.carteLocomotive = new LinkedList<CarteLocomotive>();
    	this.carteWagon = new LinkedList<CarteWagon>();
    	this.nbCartes = this.carteLocomotive.size() + this.carteWagon.size();
    }
    
   	public int getNbCartes() {
		return nbCartes;
	}

	public void setNbCartes(int nbCartes) {
		this.nbCartes = nbCartes;
	}

	public List<CarteWagon> getCarteWagon() {
		return carteWagon;
	}

	public void setCarteWagon(LinkedList<CarteWagon> carteWagon) {
		this.carteWagon = carteWagon;
	}

	public List<CarteLocomotive> getCarteLocomotive() {
		return carteLocomotive;
	}

	public void setCarteLocomotive(LinkedList<CarteLocomotive> carteLocomotive) {
		this.carteLocomotive = carteLocomotive;
	}
	
	/*
     * Supprime tous les éléments contenus dans la défausse
     */
    public void vider() {
    	this.nbCartes = 0;
    	this.carteLocomotive.clear();
    	this.carteWagon.clear();
    }
    
    

}