package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PiocheWagon {
    private int nbCartes;
    private LinkedList<CarteWagon> carteWagon;

    public PiocheWagon() {
    	this.carteWagon = new LinkedList<CarteWagon>();
    	this.nbCartes = this.carteWagon.size();    	
    }
    
    public PiocheWagon(LinkedList<CarteWagon> cartes) {
    	this.carteWagon = cartes;
    	this.nbCartes = this.carteWagon.size();   
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

	/*
	 * Retourne et retire la première carte de la pioche
	 */
	public CarteWagon piocher1Carte(DefausseWagon defausse) {
		if (!(this.carteWagon.isEmpty())) {
			this.nbCartes -= 1;
			return this.carteWagon.pollFirst();
		}
		else {
			LinkedList<CarteWagon> nouvellePioche = defausse.getCarteWagon();
			defausse.vider();
			Collections.shuffle(nouvellePioche);
			this.carteWagon = nouvellePioche;
			return this.piocher1Carte(defausse);
		}
	}
	
	/*
	 * Effectue le tour de pioche d'un joueur selon les règles du jeu
	 */
	public List<CarteWagon> piocher(DefausseWagon defausse) {
		List<CarteWagon> piochees = new ArrayList<CarteWagon>();
		CarteWagon c = piocher1Carte(defausse);
		piochees.add(c);
		if (c.getCouleur() == "Locomotive") {
			return piochees;
		}
		else {
			System.out.println("Voulez-vous pioche une autre carte ? (O/N)");
			Scanner sc = new Scanner(System.in);
			String s = sc.nextLine();
			if (s == "O") {
				c = piocher1Carte(defausse);
				piochees.add(c);
			}
			return piochees;
		}
	}

	/*
	 * Mélange le paquet de CarteWagon
	 */
	public void melanger() {
		Collections.shuffle(this.carteWagon);
    }
	
	/*
	 * Distribue à l'initialisation d'une partie
	 */
	public LinkedList<CarteWagon> distribuer() {
		LinkedList<CarteWagon> cartes = new LinkedList<CarteWagon>();
		for (int i=0; i < 4; i++) {
			cartes.add(this.carteWagon.pollFirst());
		}
		return cartes;
	}
}
