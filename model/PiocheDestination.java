package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

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
	 * Retourne et retire les 3 premières cartes de la pioche
	 */
	public List<CarteDestination> piocher() throws OutOfCardsException {
		List<CarteDestination> piochees = new ArrayList<CarteDestination>();
		if (!(this.carteDestination.isEmpty())) {
			for (int i = 0; i < 3; i++) {
				piochees.add(this.carteDestination.pollFirst());
			}
			for (CarteDestination c : piochees) {
				if (!conserverCarte(c)) {
					piochees.remove(c);
				}
			}
			return piochees;
		}
		else throw new OutOfCardsException();
	}
	
	/*
	 * Demande au joueur pour chaque carte piochée s'il souhaite la conserver
	 */
	public boolean conserverCarte(CarteDestination carte) {
		System.out.println("Voulez-vous conserver cette carte ? (O/N)");
		System.out.println(carte);
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		sc.close();
		return (s == "O");
	}
	
	/*
	 * Mélange le paquet de CarteDestination
	 */
	public void melanger() {
		Collections.shuffle(this.carteDestination);
    }

}
