package com.projet.ladr.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PiocheWagon {
	private static PiocheWagon instance;
    private LinkedList<CarteWagon> pioche;
    private LinkedList<CarteWagon> cartesVisibles;
    
    public static PiocheWagon getInstance(LinkedList<CarteWagon> cartes) {
    	if (instance == null) {
    		instance = new PiocheWagon(cartes);
    	}
    	return instance;
    }
    
    public PiocheWagon(LinkedList<CarteWagon> cartes) {
    	this.pioche = cartes;
    	this.cartesVisibles = new LinkedList<CarteWagon>();
    }
           
    public int getNbCartes() {
		return this.pioche.size() + this.cartesVisibles.size();
	}

	public LinkedList<CarteWagon> getPioche() {
		return pioche;
	}

	public void setPioche(LinkedList<CarteWagon> pioche) {
		this.pioche = pioche;
	}

	public LinkedList<CarteWagon> getCartesVisibles() {
		return cartesVisibles;
	}

	public void setCartesVisibles(LinkedList<CarteWagon> cartesVisibles) {
		this.cartesVisibles = cartesVisibles;
	}

	/*
	 * Retourne et retire la premi�re carte de la pioche en aveugle
	 */
	public CarteWagon piocher1Carte(DefausseWagon defausse) {
		if (!(this.pioche.isEmpty())) {
			return this.pioche.pollFirst();										//retire la premi�re carte de la pioche et la retourne
		}
		else { 																	//Si la pioche est vide
			LinkedList<CarteWagon> nouvellePioche = defausse.getCarteWagon(); 	//On r�cup�re les cartes de la d�fausse
			//defausse.vider();													//On vide la d�fausse actuelle
			Collections.shuffle(nouvellePioche);
			this.pioche.addAll(nouvellePioche);								//La pioche est maintenant l'ancienne d�fausse m�lang�e
			return this.piocher1Carte(defausse);								//Appel r�cursif de la m�thode pour pouvoir piocher une carte
		}
	}
	

	/*
	 * M�lange le paquet de CarteWagon
	 */
	public void melanger() {
		Collections.shuffle(this.pioche);
    }
	
	/*
	 * Distribue � l'initialisation d'une partie
	 */
	public LinkedList<CarteWagon> distribuer() {
		LinkedList<CarteWagon> cartes = new LinkedList<CarteWagon>();
		for (int i=0; i < 4; i++) {
			cartes.add(this.pioche.pollFirst());
		}
		return cartes;															//Retourne 4 cartes prises sur le dessus du paquets
	}
	
	/*
	 * Retire 5 cartes du paquets pour les mettre dans la pioche visible
	 */
	public void preparerPiocheVisible(DefausseWagon defausse) {
		LinkedList<CarteWagon> visibles = new LinkedList<CarteWagon>();
		int cmpLoco = 0;
		for (int i = 0; i < 5; i++) {											//Pioche visible compos�e de 5 cartes
			CarteWagon tmp = this.piocher1Carte(defausse);						//On tire une carte du paquet principal
			visibles.add(tmp);													//On l'ajoute � la liste des cartes visibles
			if (tmp.getCouleur() == "Locomotive") {
				cmpLoco++;														//Si la carte tir�e est une locomotive, on incr�mente le compteur
			}
		}
		if (cmpLoco >= 3) {														//Si il y a plus de 3 cartes locomotive dans la pioche visible
			defausse.getCarteWagon().addAll(visibles);							//On d�fausse toute la pioche visible
			for (CarteWagon c : visibles) {
				visibles.remove();
			}
			preparerPiocheVisible(defausse);									//Et on retire 5 nouvelles cartes du paquets pour refaire la pioche visible
		}
		this.cartesVisibles = visibles;
	}
	
	/*
	 * Retourne et retire une carte de la pioche visible
	 */
	public CarteWagon piocherVisible(DefausseWagon defausse) {
		int cmpLoco = 0;
		int i = 0;
		System.out.println("-->Choisissez une carte");
		for (CarteWagon c : this.cartesVisibles) {
			System.out.println("---->" + i + " : " + c);
			i++;
		}
		Scanner sc = new Scanner(System.in);
		int indiceCarteChoisie = sc.nextInt();
		CarteWagon tmp = this.cartesVisibles.get(indiceCarteChoisie);				//On prend la carte que le joueur a choisi
		this.cartesVisibles.remove(indiceCarteChoisie);								//On la retire de la pioche visible
		this.cartesVisibles.add(indiceCarteChoisie, this.piocher1Carte(defausse));	//Et on la remplace par une autre carte que l'on pioche sur le dessus du paquet
		for (CarteWagon c : this.cartesVisibles) {
			if (c.getCouleur() == "Locomotive") {
				cmpLoco++;															//Si la carte tir�e est une locomotive, on incr�mente le compteur
			}
		}
		if (cmpLoco >= 3) {															//Si il y a plus de 3 cartes locomotive dans la pioche visible
			defausse.getCarteWagon().addAll(this.cartesVisibles);					//On d�fausse toute la pioche visible
			for (CarteWagon c : this.cartesVisibles) {
				this.cartesVisibles.remove();
			}
			preparerPiocheVisible(defausse);										//Et on retire 5 nouvelles cartes du paquets pour refaire la pioche visible
		}
		return tmp;
	}
	
	/*
	 * Effectue le tour de pioche d'un joueur selon les r�gles du jeu
	 */
	public List<CarteWagon> piocher(DefausseWagon defausse) {
		LinkedList<CarteWagon> piochees = new LinkedList<CarteWagon>();
		int nbCartes = 0;
		while (nbCartes < 2) {													//La m�thode s'arr�te quand le joueur a tir� 2 cartes (sauf cas loco)
			System.out.println("Pioche visible ou pioche aveugle ? (Visible = 0 / Aveugle = 1)");
			Scanner sc = new Scanner(System.in);
			int i = sc.nextInt();
			if (i == 0) {														//Si le joueur a choisi de tirer dans la pioche visible
				CarteWagon r = piocherVisible(defausse);						//Il prend une carte
				piochees.add(r);												//On l'ajoute aux cartes pioch�es
				nbCartes++;														//Et on incr�mente le compteur
				if (r.getCouleur() == "Locomotive") {							//Si la carte est une locomotive, la m�thode se termine
					return piochees;
				}
			} else {															//Si le joueur a choisi de tirer dans la pioche aveugle
				CarteWagon c = piocher1Carte(defausse);							//Il prend une carte
				piochees.add(c);												//On l'ajoute � ses cartes pioch�es
				nbCartes++;														//Et on incr�mente le compteur
			}
		}
		return piochees;
	}
}
