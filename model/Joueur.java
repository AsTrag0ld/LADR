package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public class Joueur extends Observable {
    private String nom;
    private String couleur;
    private int score;
    private int nbWagons;
    private List<Wagon> wagons;
    private LinkedList<CarteWagon> cartesWagon;
    private LinkedList<CarteDestination> cartesDestination;
    private PiocheWagon piocheWagon;
    private PiocheDestination piocheDestination;
    private DefausseWagon defausseWagon;

    public Joueur() {
    	this.nom = "Nom";
    	this.couleur = "Couleur";
    	this.score = 0;
    	this.nbWagons = 40;
    }
    
    public Joueur(String nom, String couleur, int score, LinkedList<Wagon> wagons) {
    	this.nom = nom;
    	this.couleur = couleur;
    	this.score = score;
    	this.wagons = wagons;
    	/*
    	 * AJOUTER LA GESTION DES PIOCHES / DEFAUSSES
    	 */
    }
  
    public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getNbWagons() {
		return nbWagons;
	}

	public void setNbWagons(int nbWagons) {
		this.nbWagons = nbWagons;
	}
	
	public LinkedList<CarteWagon> getCartesWagon() {
		return cartesWagon;
	}

	public void setCartesWagon(LinkedList<CarteWagon> cartesWagon) {
		this.cartesWagon = cartesWagon;
	}
	
	public LinkedList<CarteDestination> getCartesDestination() {
		return cartesDestination;
	}

	public void setCartesDestination(LinkedList<CarteDestination> cartesDestination) {
		this.cartesDestination = cartesDestination;
	}

	public List<Wagon> getWagons() {
		return wagons;
	}

	public void setWagons(List<Wagon> wagon) {
		this.wagons = wagon;
	}

	public PiocheWagon getPiocheWagon() {
		return piocheWagon;
	}

	public void setPiocheWagon(PiocheWagon piocheWagon) {
		this.piocheWagon = piocheWagon;
	}

	public PiocheDestination getPiocheDestination() {
		return piocheDestination;
	}

	public void setPiocheDestination(PiocheDestination piocheDestination) {
		this.piocheDestination = piocheDestination;
	}

	public DefausseWagon getDefausseWagon() {
		return defausseWagon;
	}

	public void setDefausseWagon(DefausseWagon defausseWagon) {
		this.defausseWagon = defausseWagon;
	}
	
	/*
	 * Prend possession d'une route en plaçant des wagons sur le plateau et en décrémentant le nombre de wagons du joueur
	 */
	public void prendreRoute(Route r) {
		this.score += r.getTaille();
		this.nbWagons -= r.getTaille();
    }

    public void passerSonTour() {
    }

    public void modifierOptions() {
    }

    public void consulterRegles() {
    }

    /*
     * Ajoute dans la main la première carte de la pioche de CarteWagon (la retire de la pioche)
     */
    public void piocherCarteWagon() throws OutOfCardsException {
    	this.cartesWagon.add(this.piocheWagon.piocher());
    }
    
    /*
     * Ajoute aux CarteDestination du joueur la première carte de la pioche de CarteDestination (la retire de la pioche)
     */
    public void piocherCarteDestination() throws OutOfCardsException {
    	this.cartesDestination.add(this.piocheDestination.piocher());
    }

    public void consulterStatistiques() {
    }

}
