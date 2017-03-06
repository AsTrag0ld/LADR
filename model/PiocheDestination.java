package com.example.doria.ladrandr.model;

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
	 * Retire les 3 premi�res cartes de la pioche puis retourne celles que le joueur a choisi de conserver (au moins 1)
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
					conservees.add(c);													//Si oui, on l'ajoute � la liste des cartes gard�es
					System.out.println(">> Carte conserv�e");
				} else {
					jetees.add(c);														//Si non, on l'ajoute � la liste des cartes jet�es
				}
			}
			if (conservees.size() < 1) {												//La liste des cartes gard�es doit au moins contenir 2 cartes
				System.err.println("Vous devez conserver au moins 1 carte !");			//Si le nombre de cartes gard�es n'est pas suffisant
				for (int i = piochees.size()-1; i >= 0; i--) {
					this.carteDestination.addFirst(piochees.get(i));					//On remet chaque carte dans du paquet pour recommencer la m�thode de pioche
				}
				conservees = piocher();												//Appel r�cursif
			} else {																	//Si le nombre de cartes gard�es est > 1
				for (CarteDestination c : jetees) {
					this.carteDestination.addLast(c);									//On ajoute chaque carte jet�e � la fin du paquet
				}
			}
		} else {
			throw new OutOfCardsException();
		}
		return conservees;
	}
	
	/*
	 * Demande au joueur pour chaque carte pioch�e s'il souhaite la conserver
	 */
	public boolean conserverCarte(CarteDestination carte) {
		System.out.println("Voulez-vous conserver cette carte ? (0 : non / 1 : oui)");
		System.out.println(carte);
		Scanner sc = new Scanner(System.in);
		int s = sc.nextInt();
		return (s == 1);
	}
	
	/*
	 * M�lange le paquet de CarteDestination
	 */
	public void melanger() {
		Collections.shuffle(this.carteDestination);
    }
	
	/*
	 * Retire les 3 premi�res cartes de la pioche puis retourne celles que le joueur a choisi de conserver (au moins 2)
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
				conservees.add(c);													//Si oui, on l'ajoute � la liste des cartes gard�es
				System.out.println(">> Carte conserv�e");
			} else {
				jetees.add(c);														//Si non, on l'ajoute � la liste des cartes jet�es
			}
		}
		if (conservees.size() < 2) {												//La liste des cartes gard�es doit au moins contenir 2 cartes
			System.err.println("Vous devez conserver au moins 2 cartes !");			//Si le nombre de cartes gard�es n'est pas suffisant
			conservees.removeFirst();
			for (int i = piochees.size()-1; i >= 0; i--) {
				this.carteDestination.addFirst(piochees.get(i));					//On remet chaque carte dans du paquet pour recommencer la m�thode de pioche
			}
			conservees = distribuer();												//Appel r�cursif
		} else {																	//Si le nombre de cartes gard�es est > 1
			for (CarteDestination c : jetees) {
				this.carteDestination.addLast(c);									//On ajoute chaque carte jet�e � la fin du paquet
			}
		}
		return conservees;	
	}

}
