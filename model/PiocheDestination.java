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
				piochees.add(this.carteDestination.pollFirst());						//Si la pioche n'est pas vide on tire 3 cartes du dessus du paquet
			}
			for (CarteDestination c : piochees) {										//Pour chacune de ces 3 cartes
				if (conserverCarte(c)) {												//On demande au joueur s'il souhaite la conserver
					conservees.add(c);													//Si oui, on l'ajoute à la liste des cartes gardées
					System.out.println("------------> Carte conservée");
				} else {
					jetees.add(c);														//Si non, on l'ajoute à la liste des cartes jetées
				}
			}
			if (conservees.size() < 1) {												//La liste des cartes gardées doit au moins contenir une carte
				System.out.println("Vous devez conserver au moins 1 carte !");			//Si le nombre de cartes gardées n'est pas suffisant
				for (CarteDestination c : piochees) {
					this.carteDestination.addFirst(c);									//On remet chaque carte sur le dessus du paquet pour recommencer la méthode de pioche
				}
				piocher();																//Appel récursif 
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
				System.out.println("------------> Carte conservée");
			} else {
				jetees.add(c);														//Si non, on l'ajoute à la liste des cartes jetées
			}
		}
		if (conservees.size() < 2) {												//La liste des cartes gardées doit au moins contenir 2 cartes
			System.out.println("Vous devez conserver au moins 2 carte !");			//Si le nombre de cartes gardées n'est pas suffisant
			for (CarteDestination c : piochees) {									
				this.carteDestination.addFirst(c);									//On remet chaque carte sur le dessus du paquet pour recommencer la méthode de pioche
			}
			distribuer();															//Appel récursif
		} else {																	//Si le nombre de cartes gardées est > 1
			for (CarteDestination c : jetees) {
				this.carteDestination.addLast(c);									//On ajoute chaque carte jetée à la fin du paquet
			}
		}
		return conservees;	
	}

}
