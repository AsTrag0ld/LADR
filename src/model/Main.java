package model;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		Joueur j1 = new Joueur("Dorian", "Bleu");
		Joueur j2 = new Joueur("Anthony", "Rouge");
		Joueur j3 = new Joueur("Valentin", "Vert");
		ArrayList<Joueur> lesJoueurs = new ArrayList<Joueur>();
		lesJoueurs.add(j1);
		lesJoueurs.add(j2);
		lesJoueurs.add(j3);
		
		Partie partie = new Partie(lesJoueurs, new PiocheWagon(), new PiocheDestination(), new DefausseWagon());
		partie.initialiserPartie();
		
		System.out.println("Défausse : " + partie.getDefausseWagon().getNbCartes());
		System.out.println("Cartes Wagon : " + partie.getPiocheWagon().getNbCartes());
		System.out.println("Cartes Destination : " + partie.getPiocheDestination().getNbCartes());
		System.out.println("----------------------------------------------------------");
			
	}
}
