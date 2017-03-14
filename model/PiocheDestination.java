package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PiocheDestination {
	private static PiocheDestination instance;
    private int nbCartes;
    private LinkedList<CarteDestination> carteDestination;

    public static PiocheDestination getInstance(LinkedList<CarteDestination> cartes) {
    	if (instance == null) {
    		instance = new PiocheDestination(cartes);
    	}
    	return instance;
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
	public LinkedList<CarteDestination> piocher() throws OutOfCardsException {
		LinkedList<CarteDestination> piochees = new LinkedList<CarteDestination>();
		LinkedList<CarteDestination> conservees = new LinkedList<CarteDestination>();
		List<CarteDestination> jetees = new ArrayList<CarteDestination>();
		if (!(this.carteDestination.isEmpty())) {
			for (int i = 0; i < 3; i++) {
				piochees.add(this.carteDestination.pollFirst());						//On tire 3 cartes du dessus du paquet
			}
			for (CarteDestination c : piochees) {										//Pour chacune de ces 3 cartes
				if (conserverCarte(c)) {												//On demande au joueur s'il souhaite la conserver
					conservees.add(c);													//Si oui, on l'ajoute à la liste des cartes gardées
					System.out.println(">> Carte conservée");
				} else {
					jetees.add(c);														//Si non, on l'ajoute à la liste des cartes jetées
				}
			}
			if (conservees.size() < 1) {												//La liste des cartes gardées doit au moins contenir 2 cartes
				System.err.println("Vous devez conserver au moins 1 carte !");			//Si le nombre de cartes gardées n'est pas suffisant
				for (int i = piochees.size()-1; i >= 0; i--) {
					this.carteDestination.addFirst(piochees.get(i));					//On remet chaque carte dans du paquet pour recommencer la méthode de pioche
				}
				conservees = piocher();												//Appel récursif
			} else {																	//Si le nombre de cartes gardées est > 1
				for (CarteDestination c : jetees) {
					this.carteDestination.addLast(c);									//On ajoute chaque carte jetée à la fin du paquet
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
		System.out.println("Voulez-vous conserver cette carte ? (0 : non / 1 : oui)");
		System.out.println(carte);
		Scanner sc = new Scanner(System.in);
		int s = sc.nextInt();
		return (s == 1);
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
			piochees.add(this.carteDestination.pollFirst());						//On tire 3 cartes du dessus du paquet
		}
		for (CarteDestination c : piochees) {										//Pour chacune de ces 3 cartes
			if (conserverCarte(c)) {												//On demande au joueur s'il souhaite la conserver
				conservees.add(c);													//Si oui, on l'ajoute à la liste des cartes gardées
				System.out.println(">> Carte conservée");
			} else {
				jetees.add(c);														//Si non, on l'ajoute à la liste des cartes jetées
			}
		}
		if (conservees.size() < 2) {												//La liste des cartes gardées doit au moins contenir 2 cartes
			System.err.println("Vous devez conserver au moins 2 cartes !");			//Si le nombre de cartes gardées n'est pas suffisant
			if (conservees.size() != 0) {
				conservees.removeFirst();
			}
			for (int i = piochees.size()-1; i >= 0; i--) {
				this.carteDestination.addFirst(piochees.get(i));					//On remet chaque carte dans du paquet pour recommencer la méthode de pioche
			}
			conservees = distribuer();												//Appel récursif
		} else {																	//Si le nombre de cartes gardées est > 1
			for (CarteDestination c : jetees) {
				this.carteDestination.addLast(c);									//On ajoute chaque carte jetée à la fin du paquet
			}
		}
		return conservees;	
	}

}
