package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

public class PiocheWagon {
    private LinkedList<CarteWagon> pioche;
    private LinkedList<CarteWagon> cartesVisibles;

    public PiocheWagon() {
    	this.pioche = new LinkedList<CarteWagon>();
    	this.cartesVisibles = new LinkedList<CarteWagon>();
    }
    
    public PiocheWagon(LinkedList<CarteWagon> cartes) {
    	this.pioche = cartes;
    	melanger();
    	this.cartesVisibles = new LinkedList<CarteWagon>();
    }
           
    public int getNbCartes() {
		return this.pioche.size() + this.cartesVisibles.size();
	}


	public List<CarteWagon> getCarteWagon() {
		return pioche;
	}

	public void setCarteWagon(LinkedList<CarteWagon> carteWagon) {
		this.pioche = carteWagon;
	}

	/*
	 * Retourne et retire la première carte de la pioche en aveugle
	 */
	public CarteWagon piocher1Carte(DefausseWagon defausse) {
		if (!(this.pioche.isEmpty())) {
			return this.pioche.pollFirst();
		}
		else {
			LinkedList<CarteWagon> nouvellePioche = defausse.getCarteWagon();
			defausse.vider();
			this.pioche = nouvellePioche;
			this.melanger();
			return this.piocher1Carte(defausse);
		}
	}
	

	/*
	 * Mélange le paquet de CarteWagon
	 */
	public void melanger() {
		Collections.shuffle(this.pioche);
    }
	
	/*
	 * Distribue à l'initialisation d'une partie
	 */
	public LinkedList<CarteWagon> distribuer() {
		LinkedList<CarteWagon> cartes = new LinkedList<CarteWagon>();
		for (int i=0; i < 4; i++) {
			cartes.add(this.pioche.pollFirst());
		}
		return cartes;
	}
	
	/*
	 * Retire 5 cartes du paquets pour les mettre dans la pioche visible
	 */
	public void preparerPiocheVisible(DefausseWagon defausse) {
		LinkedList<CarteWagon> visibles = new LinkedList<CarteWagon>();
		int cmpLoco = 0;
		for (int i = 0; i < 5; i++) {
			CarteWagon tmp = this.piocher1Carte(defausse);
			visibles.add(tmp);
			if (tmp.getCouleur() == "Locomotive") {
				cmpLoco++;
			}
		}
		if (cmpLoco >= 3) {
			defausse.getCarteWagon().addAll(visibles);
			for (CarteWagon c : visibles) {
				visibles.remove();
			}
			preparerPiocheVisible(defausse);
		}
		this.cartesVisibles = visibles;
	}
	
	/*
	 * Retourne et retire une carte de la pioche visible
	 */
	public CarteWagon piocherVisible(DefausseWagon defausse) {
		int cmpLoco = 0;
		System.out.println("Choisissez une carte");
		int indiceCarteChoisie = 0;
		CarteWagon tmp = this.cartesVisibles.get(indiceCarteChoisie);
		this.cartesVisibles.remove(indiceCarteChoisie);
		this.cartesVisibles.add(indiceCarteChoisie, this.piocher1Carte(defausse));
		for (CarteWagon c : this.cartesVisibles) {
			if (c.getCouleur() == "Locomotive") {
				cmpLoco++;
			}
		}
		if (cmpLoco >= 3) {
			defausse.getCarteWagon().addAll(this.cartesVisibles);
			for (CarteWagon c : this.cartesVisibles) {
				this.cartesVisibles.remove();
			}
			preparerPiocheVisible(defausse);
		}
		return tmp;
	}
	
	/*
	 * Effectue le tour de pioche d'un joueur selon les règles du jeu
	 */
	public List<CarteWagon> piocher(DefausseWagon defausse) {
		LinkedList<CarteWagon> piochees = new LinkedList<CarteWagon>();
		int nbCartes = 0;
		while (nbCartes < 2) {
			System.out.println("Pioche visible ou pioche aveugle");
			boolean piocheVisible = true;
			if (piocheVisible) {
				CarteWagon r = piocherVisible(defausse);
				piochees.add(r);
				nbCartes++;
				if (r.getCouleur() == "Locomotive") {
					return piochees;
				}
			} else {
				CarteWagon c = piocher1Carte(defausse);
				piochees.add(c);
				nbCartes++;
			}
		}
		return piochees;
	}
}
