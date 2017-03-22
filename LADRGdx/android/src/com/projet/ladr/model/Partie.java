package com.projet.ladr.model;

import com.projet.ladr.controler.DatabaseHandler;

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

	public boolean isEnCours() { return this.enCours;}

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

	/*
     * Cr�e les 110 cartes wagons (12 par couleur + 14 locomotives)
     */
	private void initialiserCartesWagon(String[] couleurs) {
		LinkedList<CarteWagon> cartesWagon = new LinkedList<CarteWagon>();
		for (String s : couleurs) {											//Pour chaque couleur pr�sente dans le jeu
			for (int j = 0; j < 12; j++) {
				cartesWagon.add(new CarteWagon(s));							//On cr�e 12 cartes wagon de cette couleur
			}
		}
		for (int i = 0; i < 14; i++) {										//Ensuite on cr�e 14 cartes locomotives
			cartesWagon.add(new CarteWagon("Locomotive"));
		}
		this.setPiocheWagon(PiocheWagon.getInstance(cartesWagon));
		Collections.shuffle(this.getPiocheWagon().getPioche());
		this.getPiocheWagon().preparerPiocheVisible(this.getDefausseWagon());			//On sort 5 cartes du paquets pour en faire la pioche visible
	}

	/*
     * Cr�e 45 wagons pour la couleur pass�e en argument
     */
	LinkedList<Wagon> initialiserWagons(String couleur) {
		LinkedList<Wagon> lesWagons = new LinkedList<Wagon>();
		for (int j = 0; j < 45; j++) {
			lesWagons.add(new Wagon(couleur));
		}
		return lesWagons;
	}

	/*
     * Distribue 4 cartes wagon � chaque joueur apr�s avoir m�langer le paquet
     */
	public void distribuerCartesWagon() {
		initialiserCartesWagon(this.getCouleurs());
		this.getPiocheWagon().melanger();
		for (Joueur j : this.getJoueurs()) {
			j.setCartesWagon(this.getPiocheWagon().distribuer());
		}
	}

	/*
     * Distribue � chaque joueur les wagons correspondants � sa couleur
     */
	public void distribuerWagons() {
		for (Joueur j : this.getJoueurs()) {										//Pour chaque joueur
			j.setWagons(initialiserWagons(j.getCouleur()));					//On cr�e et on lui distibue 45 wagons de sa couleur
		}
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
}
