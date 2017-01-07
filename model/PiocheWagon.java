package model;

import java.util.LinkedList;
import java.util.List;

public class PiocheWagon {
    private int nbCartes;
    private LinkedList<CarteWagon> carteWagon;
    private LinkedList<CarteLocomotive> carteLocomotive;

    public PiocheWagon() {
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
	 * Retourne et retire la première carte de la pioche
	 */
	public CarteWagon piocher() throws OutOfCardsException {
		if (!(this.carteWagon.isEmpty())) {
			return this.carteWagon.pollFirst();
		}
		else throw new OutOfCardsException();
	}

	public void melanger() {
    }

}
