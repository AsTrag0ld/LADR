package model;

import java.util.ArrayList;

public class Main {
/*
	public static void main(String[] args) {
		Joueur j1 = new Joueur("Dorian", "Bleu");
		Joueur j2 = new Joueur("Anthony", "Rouge");
		ArrayList<Joueur> lesJoueurs = new ArrayList<Joueur>();
		lesJoueurs.add(j1);
		lesJoueurs.add(j2);
		
		Partie partie = new Partie(lesJoueurs);
		
		System.out.println("PD : " + j1.getPiocheDestination());
		System.out.println("PW : " + j1.getPiocheWagon());
		System.out.println("D : " + j1.getDefausseWagon());
		System.out.println("W : " + j1.getWagons());
		System.out.println("CW : " + j1.getCartesWagon());
		System.out.println("CD : " + j1.getCartesDestination());
		System.out.println("--------------------------------------------------------");
		System.out.println("PD : " + j2.getPiocheDestination());
		System.out.println("PW : " + j2.getPiocheWagon());
		System.out.println("D : " + j2.getDefausseWagon());
		System.out.println("W : " + j2.getWagons());
		System.out.println("CW : " + j2.getCartesWagon());
		System.out.println("CD : " + j2.getCartesDestination());
	}
*/

	
	public static void main(String[] args) {
		ArrayList<Joueur> lesJoueurs = new ArrayList<Joueur>();
		lesJoueurs.add(new Joueur("Dorian", "Bleu"));
		lesJoueurs.add(new Joueur("Valentin", "Rouge"));
		Partie partie = new Partie(lesJoueurs);
		partie.jouerPartie();
	}
	
	
}
