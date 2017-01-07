package model;

import java.util.LinkedList;
import java.util.List;

public class PiocheDestination {
    private int nbCartes;
    private LinkedList<CarteDestination> carteDestination;

    public PiocheDestination() {
    	this.carteDestination = new LinkedList<CarteDestination>();
    	this.nbCartes = this.carteDestination.size();
    }
    
    public int getNbCartes() {
		return nbCartes;
	}

	public void setNbCartes(int nbCartes) {
		this.nbCartes = nbCartes;
	}

	public LinkedList<CarteDestination> getCarteDestination() {
		return carteDestination;
	}

	public void setCarteDestination(LinkedList<CarteDestination> carteDestination) {
		this.carteDestination = carteDestination;
	}

	/*
	 * Retourne et retire la première carte de la pioche
	 */
	public CarteDestination piocher() throws OutOfCardsException {
		if (!(this.carteDestination.isEmpty())) {
			return this.carteDestination.pollFirst();
		}
		else throw new OutOfCardsException();
	}
	
	public void melanger() {
    }

}
