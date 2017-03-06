package com.example.doria.ladrandr.model;
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
    private LinkedList<Route> routesPrises;
    private boolean tourDeJeu;

    public Joueur() {
    	this.nom = "Nom";
    	this.couleur = "Couleur";
    	this.score = 0;
    	this.nbWagons = 40;
    	this.tourDeJeu = false;
    	this.routesPrises = new LinkedList<Route>();
    }
    
    public Joueur(String nom, String couleur) {
    	this.nom = nom;
    	this.couleur = couleur;
    	this.score = 0;
    	this.nbWagons = 40;
    	this.tourDeJeu = false;
    	this.routesPrises = new LinkedList<Route>();
    }
    
    public Joueur(String nom, String couleur, int score, LinkedList<Wagon> wagons, LinkedList<CarteWagon> cartesWagon, LinkedList<CarteDestination> cartesDestination) {
    	this.nom = nom;
    	this.couleur = couleur;
    	this.score = score;
    	this.wagons = wagons;
    	this.cartesWagon = cartesWagon;
    	this.cartesDestination = cartesDestination;
    	this.routesPrises = new LinkedList<Route>();
    	this.tourDeJeu = false;
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
	
	public LinkedList<Route> getRoutesPrises() {
		return routesPrises;
	}

	public void setRoutesPrises(LinkedList<Route> routesPrises) {
		this.routesPrises = routesPrises;
	}

	public boolean isTourDeJeu() {
		return tourDeJeu;
	}

	public void setTourDeJeu(boolean tourDeJeu) {
		this.tourDeJeu = tourDeJeu;
	}

	/* 
	 * Prend possession d'une route en pla�ant des wagons sur le plateau et en d�cr�mentant le nombre de wagons du joueur
	 */
	public void prendreRoute(Route r) throws OutOfCardsException {
		if (verificationWagonsRoute(r)) {			//Si le joueur a assez de wagons pour prendre la route
			if (verificationCartesRoute(r)) { 		//Si il a assez de cartes
				int taille = r.getTaille();
				switch (taille) {					//On augmente son score en fonction de la taille de la route prise
					case 1 : this.score += 1;
						break;
					case 2 : this.score += 2;
						break;
					case 3 : this.score += 4;
						break;
					case 4 : this.score += 7;
						break;
					case 5 : this.score += 10;
						break;
					case 6 : this.score += 15;
						break;
					default : this.score += 0;
						break;
				}
				this.nbWagons -= r.getTaille();
				r.prendre();
				this.routesPrises.add(r);
			} else {
				System.out.println("Vous n'avez pas assez de cartes '" + r.getCouleur() + "' pour prendre possession de la route : " + r);
				throw new OutOfCardsException();
			}
		} else {
			System.out.println("Vous n'avez pas assez de wagons restants pour prendre possession de cette route");
		}
		
    }
	
	/*
	 * V�rifie si le joueur a suffisament de carte de la m�me couleur que la route en main
	 */
	boolean verificationCartesRoute(Route r) {
		int cmp = 0;
		for (CarteWagon c : this.cartesWagon) {
			if ((c.getCouleur().equals(r.getCouleur())) || (c.getCouleur() == "Locomotive")) {
				cmp++;					
			}
		}
		return (cmp >= r.getTaille());
	}
	
	/*
	 * V�rifie si le joueur a suffisament de wagons pour prendre possession de la route
	 */
	boolean verificationWagonsRoute(Route r) {
		return (this.getNbWagons() >= r.getTaille());
	}
	
    public void modifierOptions() {
    }

    public void consulterRegles() {
    }

    /*
     * Ajoute dans la main la premi�re carte de la pioche de CarteWagon (la retire de la pioche)
     */
    public void piocherCarteWagon(PiocheWagon pioche, DefausseWagon defausse) throws OutOfCardsException {
    	this.cartesWagon.addAll(pioche.piocher(defausse));
    }
    
    /*
     * Ajoute aux CarteDestination du joueur les cartes qu'il a choisi de garder de la pioche de CarteDestination (les retire de la pioche)
     */
    public void piocherCarteDestination(PiocheDestination pioche) throws OutOfCardsException {
    	this.cartesDestination.addAll(pioche.piocher());
    }
    
    /*
     * Affiche toutes les cartes wagon du joueur
     */
    public void afficherMain() {
    	for (CarteWagon c : this.cartesWagon) {
    		System.out.println(c);
    	}
    }
    
    /*
     * Affiche toutes les cartes destination du joueur
     */
    public void afficherCartesDestination() {
    	for (CarteDestination c : this.cartesDestination) {
    		System.out.println(c);
    	}
    }  
    
    /*
     * Commence le tour de jeu de ce joueur
     */
    public void commencerTour() {
    	this.tourDeJeu = true;
    }
    
    /*
     * Met fin au tour du joueur
     */
    public void finirTour() {
    	this.tourDeJeu = true;
    }

}
