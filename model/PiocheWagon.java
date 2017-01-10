package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PiocheWagon {
    private int nbCartes;
    private LinkedList<CarteWagon> carteWagon;
    private Partie partie;

    public PiocheWagon() {
    	this.carteWagon = new LinkedList<CarteWagon>();
    	this.nbCartes = this.carteWagon.size();    	
    }
    
    public PiocheWagon(Partie p) {
    	this.carteWagon = new LinkedList<CarteWagon>();
    	this.nbCartes = this.carteWagon.size();   
    	this.partie = p;
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
	public CarteWagon piocher1Carte() {
		if (!(this.carteWagon.isEmpty())) {
			return this.carteWagon.pollFirst();
		}
		else {
			LinkedList<CarteWagon> nouvellePioche = this.partie.getDefausseWagon().getCarteWagon();
			Collections.shuffle(nouvellePioche);
			this.carteWagon = nouvellePioche;
			return this.piocher1Carte();
		}
	}
	
	/*
	 * Effectue le tour de pioche d'un joueur selon les règles du jeu
	 */
	public List<CarteWagon> piocher() {
		List<CarteWagon> piochees = new ArrayList<CarteWagon>();
		CarteWagon c = piocher1Carte();
		piochees.add(c);
		if (c.getCouleur() == "Locomotive") {
			return piochees;
		}
		else {
			System.out.println("Voulez-vous pioche une autre carte ? (O/N)");
			Scanner sc = new Scanner(System.in);
			String s = sc.nextLine();
			sc.close();
			if (s == "O") {
				c = piocher1Carte();
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

}
