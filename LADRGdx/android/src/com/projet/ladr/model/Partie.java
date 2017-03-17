package com.projet.ladr.model;

import android.content.Context;

import com.projet.ladr.controller.DatabaseHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Partie {
    private Joueur vainqueur;
    private List<Joueur> joueurs;
    private PiocheWagon piocheWagon;
    private Map map;
    private DefausseWagon defausseWagon;
    private PiocheDestination piocheDestination;
    private String[] couleurs = {"blanc", "bleu", "jaune", "vert", "rouge", "violet", "noir", "marron"};
    private boolean enCours;

	public Partie(ArrayList<Joueur> joueurs) {
		this.vainqueur = null;
		this.joueurs = joueurs;
		this.piocheWagon = null;
		this.piocheDestination = null;
		this.map = Map.getInstance();
		this.defausseWagon = new DefausseWagon();
		this.enCours = false;
	}
    
    public Partie(ArrayList<Joueur> joueurs, PiocheWagon piocheWagon, PiocheDestination piocheDestination, DefausseWagon defausseWagon) {
    	this.vainqueur = null;
    	this.joueurs = joueurs;
    	this.piocheWagon = piocheWagon;
    	this.piocheDestination = piocheDestination;
    	this.map = Map.getInstance();
    	this.defausseWagon = defausseWagon;
    	this.enCours = false;
    }


    public String[] getCouleurs() {
		return this.couleurs;
	}
      
    public Joueur getVainqueur() {
		return vainqueur;
	}

	public void setVainqueur(Joueur vainqueur) {
		this.vainqueur = vainqueur;
	}

	public List<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(List<Joueur> joueurs) {
		this.joueurs = joueurs;
	}

	public PiocheWagon getPiocheWagon() {
		return piocheWagon;
	}

	public void setPiocheWagon(PiocheWagon piocheWagon) {
		this.piocheWagon = piocheWagon;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public DefausseWagon getDefausseWagon() {
		return defausseWagon;
	}

	public void setDefausseWagon(DefausseWagon defausseWagon) {
		this.defausseWagon = defausseWagon;
	}

	public PiocheDestination getPiocheDestination() {
		return piocheDestination;
	}

	public void setPiocheDestination(PiocheDestination piocheDestination) {
		this.piocheDestination = piocheDestination;
	}

	public void setEnCours(boolean b) {
		this.enCours = b;
	}

	/*
     * Retourne le vainqueur de la partie i.e. celui qui a le plus grand score
     */
    public Joueur determinerVainqueur() {
    	Joueur vainqueur = this.joueurs.get(0);
    	for (Joueur j : joueurs) {
    		if (j.getScore() > vainqueur.getScore()) {
    			vainqueur = j;
    		}
    	}
    	return vainqueur;
    }

    public void selectionnerProfil() {
    }

    public void selectionnerNbJoueurs() {
    }
    
    public void nouvellePartieBDD() {

    }
    
    public void updateClassementPartie() {

    }
    

        


    
    /*
     * Permet � un joueur d'effectuer son tour de jeu
     */
    public boolean jouerTourDeJeu(Joueur j) {
    	System.out.println("C'est � " + j.getNom() + " de jouer !");
    	j.setTourDeJeu(true);
    	System.out.println("Votre main : ");
    	j.afficherMain();
    	System.out.println("Quelle action souhaitez-vous faire ? (0 : piocher carte wagon, 1 : piocher carte destination, 2 : prendre une route");
    	Scanner sc = new Scanner(System.in);
    	int s = sc.nextInt();
    	switch (s) {
    		case 0 : try {
				j.piocherCarteWagon(this.piocheWagon, this.defausseWagon);
			} catch (OutOfCardsException e) {
				e.printStackTrace();
			}
    			break;
    		case 1 : try {
				j.piocherCarteDestination(this.piocheDestination);
			} catch (OutOfCardsException e) {
				e.printStackTrace();
			}
    			break;
    		case 2 : 
    			System.out.println("Quelle route souhaitez-vous prendre ? (entrez l'indice de la route)");
    			this.map.afficherRoutesDisponibles();
    			int indice = sc.nextInt();
				try {
					j.prendreRoute(this.map.getRoutes().get(indice));
				} catch (OutOfCardsException e) {
					jouerTourDeJeu(j);
				}
    			break;
    		default : System.out.println("Veuillez choisir parmi '1', '2' ou '3'");
    			break;
    	}
    	j.finirTour();
    	return j.isTourDeJeu();
    }
    
    /*
     * Permet d'ajouter ou de retirer les points correspondants aux cartes destinations de chaque joueur
     */
    public void comptabiliserPointsCartesDesination() {
    	for (Joueur j : this.getJoueurs()) {
    		for (CarteDestination c : j.getCartesDestination()) {
    			boolean reussie = false;
    			Route r = j.getRoutesPrises().getFirst();
    			for (int i = 1; i < j.getRoutesPrises().size(); i++) {
    				
    			}
    			if (reussie) {
    				j.setScore(j.getScore() + c.getValeur());
    			}
    		}
    	}
    }
    
    /*
     * Permet de faire jouer les joueurs chacun leur tour tant que cela est possible
     */
    public void jouerPartie() {
    	while (this.enCours) {
    		for (Joueur j : this.joueurs) {
    			jouerTourDeJeu(j);
    			if (j.getNbWagons() < 3) {
        			this.enCours = false;
        		}
    		}
    	}
    	Joueur vainqueur = this.determinerVainqueur();
    	System.out.println("Le vainqueur est : " + vainqueur.getNom());
    }
    
    
    

}
