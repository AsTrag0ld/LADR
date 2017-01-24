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
    
    public PiocheDestination(LinkedList<CarteDestination> lesCartes) {
    	this.carteDestination = lesCartes;
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
	 * Retire les 3 premières cartes de la pioche puis retourne celles que le joueur a choisi de conserver (au moins 1)
	 */
	public List<CarteDestination> piocher() throws OutOfCardsException {
		List<CarteDestination> piochees = new ArrayList<CarteDestination>();
		List<CarteDestination> conservees = new ArrayList<CarteDestination>();
		List<CarteDestination> jetees = new ArrayList<CarteDestination>();
		if (!(this.carteDestination.isEmpty())) {
			for (int i = 0; i < 3; i++) {
				piochees.add(this.carteDestination.pollFirst());
			}
			for (CarteDestination c : piochees) {
				if (conserverCarte(c)) {
					conservees.add(c);
					System.out.println("------------> Carte conservée");
				} else {
					jetees.add(c);
				}
			}
			if (conservees.size() < 1) {
				System.out.println("Vous devez conserver au moins 1 carte !");
				for (CarteDestination c : piochees) {
					this.carteDestination.addFirst(c);
				}
				piocher();
			} else {
				for (CarteDestination c : jetees) {
					this.carteDestination.addLast(c);
				}
			}
		} else {
			throw new OutOfCardsException();
		}
		return conservees;
	}
	
	/*
	 * Demande au joueur pour chaque carte piochée s'il souhaite la conserver
	 */
	public boolean conserverCarte(CarteDestination carte) {
		System.out.println("Voulez-vous conserver cette carte ? (O/N)");
		System.out.println(carte);
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		System.out.println(s);
		return (s == "O" || s == "o");
	}
	
	/*
	 * Mélange le paquet de CarteDestination
	 */
	public void melanger() {
		Collections.shuffle(this.carteDestination);
    }
	
	/*
	 * Retire les 3 premières cartes de la pioche puis retourne celles que le joueur a choisi de conserver (au moins 2)
	 */
	public LinkedList<CarteDestination> distribuer()  {
		LinkedList<CarteDestination> piochees = new LinkedList<CarteDestination>();
		LinkedList<CarteDestination> conservees = new LinkedList<CarteDestination>();
		List<CarteDestination> jetees = new ArrayList<CarteDestination>();
		for (int i = 0; i < 3; i++) {
			piochees.add(this.carteDestination.pollFirst());
		}
		for (CarteDestination c : piochees) {
			if (conserverCarte(c)) {
				conservees.add(c);
				System.out.println("------------> Carte conservée");
			} else {
				jetees.add(c);
			}
		}
		if (conservees.size() < 2) {
			System.out.println("Vous devez conserver au moins 2 carte !");
			for (CarteDestination c : piochees) {
				this.carteDestination.addFirst(c);
			}
			distribuer();
		} else {
			for (CarteDestination c : jetees) {
				this.carteDestination.addLast(c);
			}
		}
		return conservees;	
	}

}
