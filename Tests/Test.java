package model;

import java.awt.List;
import java.util.LinkedList;

public class Test {
	public static void main(String args[]){
		//Test 1 Classe : Joueur - M�thode : verificationCartesRoute() 
		//nbCartes < taille de la route	
			System.out.println("Classe : Joueur - M�thode : verificationCartesRoute() // nbCartes < taille de la route	");
		 	Joueur j1=new Joueur();
			LinkedList<CarteWagon> lc = new LinkedList<CarteWagon>();
			
			for(int i=0; i<2; i++){
				CarteWagon c = new CarteWagon("rouge");
				lc.add(c);
			}
			
			j1.setCartesWagon(lc);
			
			Route r1 = new Route(false, 5, "rouge", null, null);
			System.out.println("Attendu : false");
			System.out.println("Obtenu : " + j1.verificationCartesRoute(r1));
			
			System.out.println("----------------------------------------------------------");
		
		//Test 2 Classe : Joueur - M�thode : verificationCartesRoute() 
		//nbCartes = taille de la route
			System.out.println("Classe : Joueur - M�thode : verificationCartesRoute() // nbCartes = taille de la route");
			Joueur j2=new Joueur();
			LinkedList<CarteWagon> lc2 = new LinkedList<CarteWagon>();
			
			for(int i=0; i<5; i++){
				CarteWagon c2 = new CarteWagon("rouge");
				lc2.add(c2);
			}
			
			j2.setCartesWagon(lc2);
			
			Route r2 = new Route(false, 5, "rouge", null, null);
			
			System.out.println("Attendu : true");
			System.out.println("Obtenu : " + j2.verificationCartesRoute(r2));
			System.out.println("----------------------------------------------------------");
	
		//Test 3 Classe : Joueur - M�thode : verificationCartesRoute() 
		//nbCartes > taille de la route
			System.out.println("Classe : Joueur - M�thode : verificationCartesRoute() // nbCartes > taille de la route");
			Joueur j3=new Joueur();
			LinkedList<CarteWagon> lc3 = new LinkedList<CarteWagon>();
			
			for(int i=0; i<6; i++){
				CarteWagon c3 = new CarteWagon("rouge");
				lc3.add(c3);
			}
			
			j3.setCartesWagon(lc3);
			
			Route r3 = new Route(false, 5, "rouge", null, null);
			
			System.out.println("Attendu : true");
			System.out.println("Obtenu : " + j3.verificationCartesRoute(r3));
			System.out.println("----------------------------------------------------------");
		
	
		//Test 4 : Classe Joueur - M�thode : v�rificationWagonRoute() 
		//nbWagon < taille route
			System.out.println("Classe Joueur - M�thode : v�rificationWagonRoute() // nbWagon < taille route");
			Joueur j4=new Joueur();
			LinkedList<Wagon> lc4 = new LinkedList<Wagon>();
			
			for(int i=0; i<2; i++){
				Wagon w4 = new Wagon("rouge");
				lc4.add(w4);
			}
			
			j4.setWagons(lc4);
			j4.setNbWagons(lc4.size());
			
			Route r4 = new Route(false, 5, "rouge", null, null);
			
			System.out.println("Attendu : false");
			System.out.println("Obtenu : " + j4.verificationWagonsRoute(r4));
			System.out.println("----------------------------------------------------------");
		
		
	
		//Test 5 : Classe Joueur - M�thode : v�rificationWagonRoute() 
		//nbWagon = taille route
			System.out.println("Classe Joueur - M�thode : v�rificationWagonRoute() // nbWagon = taille route");
			Joueur j5=new Joueur();
			LinkedList<Wagon> lc5 = new LinkedList<Wagon>();
			
			for(int i=0; i<6; i++){
				Wagon w5 = new Wagon("rouge");
				lc5.add(w5);
			}
			
			j5.setWagons(lc5);
			j5.setNbWagons(lc5.size());
			
			Route r5 = new Route(false, 5, "rouge", null, null);
			
			System.out.println("Attendu : true");
			System.out.println("Obtenu : " + j5.verificationWagonsRoute(r5));
			System.out.println("----------------------------------------------------------");
				
		
		//Test 6 : Classe Joueur - M�thode : v�rificationWagonRoute() 
		//nbWagon > taille route
			System.out.println("Classe Joueur - M�thode : v�rificationWagonRoute() // nbWagon > taille route");		
			Joueur j6=new Joueur();
			LinkedList<Wagon> lc6 = new LinkedList<Wagon>();
			
			for(int i=0; i<6; i++){
				Wagon w6 = new Wagon("rouge");
				lc6.add(w6);
			}
			
			j6.setWagons(lc6);
			j6.setNbWagons(lc6.size());
			
			Route r6 = new Route(false, 4, "rouge", null, null);
			
			System.out.println("Attendu : true");
			System.out.println("Obtenu : " + j6.verificationWagonsRoute(r6));
			System.out.println("----------------------------------------------------------");
				
	
		//Test 7 : Classe Partie - M�thode determinerVainqueur()
		//J1 = 20 et J2 = 40
			
			System.out.println("Classe Partie - M�thode determinerVainqueur() // J1 = 20 et J2 = 40");	
			Partie p = new Partie();
			Joueur j7 = new Joueur();
			j7.setNom("Georis");
			Joueur j8 = new Joueur();
			j8.setNom("Jaqueline");
			
			j7.setScore(20);
			j8.setScore(40);
			LinkedList<Joueur> l = new LinkedList<Joueur>();
			l.add(j7);
			l.add(j8);
			p.setJoueurs(l);
			
			System.out.println("Attendu : Jacqueline");
			System.out.println("Obtenu : " + p.determinerVainqueur().getNom());
			System.out.println("----------------------------------------------------------");
				
				
		//Test 8 : Classe Partie - M�thode determinerVainqueur()
		//J1 = 40 et J2 = 40
			
			System.out.println("Classe Partie - M�thode determinerVainqueur() // J1 = 40 et J2 = 40");
			j7.setScore(40);
			j8.setScore(40);
					
			System.out.println("Attendu : Georis");
			System.out.println("Obtenu : " + p.determinerVainqueur().getNom());
			System.out.println("----------------------------------------------------------");
	
	
		//Test 9 : Classe Partie - M�thode determinerVainqueur()
		//J1 = 60 et J2 = 40
			
			System.out.println("Classe Partie - M�thode determinerVainqueur() // J1 = 60 et J2 = 40");
			j1.setScore(60);
			j2.setScore(40);
						
			System.out.println("Attendu : Georis");
			System.out.println("Obtenu : " + p.determinerVainqueur().getNom());
			System.out.println("----------------------------------------------------------");
	
				
			
		//Test 10 : Classe Partie - M�thode initialiserWagons()
		//Couleur = rouge
			
			System.out.println("Classe Partie - M�thode initialiserWagons() // Couleur = rouge");
			Partie p2 = new Partie();
			LinkedList<Wagon> lw = new LinkedList<Wagon>();
			lw=p2.initialiserWagons("rouge");
			
			int x=0;
			for(int i=0; i<lw.size(); i++){
				if(lw.get(i).getCouleur()=="rouge"){
					x++;
				}
			} 
			
			System.out.println("45 Wagons rouge / 45 Wagons");
			System.out.println(x + " Wagons rouge / 45 Wagons");
			System.out.println("----------------------------------------------------------");
		
		
		//Test 11 : Classe Partie - M�thode initialiserWagons()
		//Couleur = bleu
			
			System.out.println("Classe Partie - M�thode initialiserWagons() // Couleur = bleu");
			LinkedList<Wagon> lw2 = new LinkedList<Wagon>();
			lw2=p.initialiserWagons("bleu");
			
			int y=0;
			for(int i=0; i<lw2.size(); i++){
				if(lw2.get(i).getCouleur()=="bleu"){
					y++;
				}
			}
			
			System.out.println("45 Wagons bleu / 45 Wagons");
			System.out.println(y + " Wagons bleu / 45 Wagons");
			System.out.println("----------------------------------------------------------");
	
		
		//Test 14 : Classe PiocheDestination - M�thode conserverCarte()
		//r�ponse oui
		 	System.out.println("Classe PiocheDestination - M�thode conserverCarte() // R�ponse : '1'");
			PiocheDestination p1 = new PiocheDestination();
			CarteDestination cd = new CarteDestination(5, null, null);
			System.out.println("Attendu : true");
			System.out.println("Obtenu : " + p1.conserverCarte(cd));
			System.out.println("----------------------------------------------------------");
	
		
		
		//Test 15 : Classe PiocheDestination - M�thode conserverCarte()
		//r�ponse non
		 	System.out.println("Classe PiocheDestination - M�thode conserverCarte() // R�ponse : '0'");
			PiocheDestination p11 = new PiocheDestination();
			CarteDestination cd2 = new CarteDestination(5, null, null);
			System.out.println("Attendu : false");
			System.out.println("Obtenu : " + p11.conserverCarte(cd2));
			System.out.println("----------------------------------------------------------");
	
	
	/*
		//Test 16 : Classe PiocheDestination - M�thode distribuer()
		// le joueur conserve une carte
		 	System.out.println("Classe PiocheDestination - M�thode distribuer()");
			PiocheDestination p = new PiocheDestination();
			LinkedList<CarteDestination> l = new LinkedList<CarteDestination>();
		
			for(int i=0; i<5; i++){
				CarteDestination c = new CarteDestination(5, null, null);
				l.add(c);
			}
			p.setCarteDestination(l);
			try {
				p.piocher();
			} catch (OutOfCardsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			LinkedList<CarteDestination> r = new LinkedList<CarteDestination>();
			r=p.distribuer();
			System.out.println("----------------------------------------------------------");
	
		//Test 17 : Classe PiocheDestination - M�thode distribuer()
		// le joueur conserve deux cartes
		 	System.out.println("Classe PiocheDestination - M�thode distribuer()");
			PiocheDestination p = new PiocheDestination();
			LinkedList<CarteDestination> l = new LinkedList<CarteDestination>();
		
			for(int i=0; i<5; i++){
				CarteDestination c = new CarteDestination(5, null, null);
				l.add(c);
			}
			p.setCarteDestination(l);
			try {
				p.piocher();
			} catch (OutOfCardsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			LinkedList<CarteDestination> r = new LinkedList<CarteDestination>();
			r=p.distribuer();
			System.out.println("----------------------------------------------------------");
			
		//Test 18 : Classe PiocheDestination - M�thode distribuer()
		// le joueur conserve trois cartes
		 	System.out.println("Classe PiocheDestination - M�thode distribuer()");
			PiocheDestination p = new PiocheDestination();
			LinkedList<CarteDestination> l = new LinkedList<CarteDestination>();
		
			for(int i=0; i<5; i++){
				CarteDestination c = new CarteDestination(5, null, null);
				l.add(c);
			}
			p.setCarteDestination(l);
			try {
				p.piocher();
			} catch (OutOfCardsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			LinkedList<CarteDestination> r = new LinkedList<CarteDestination>();
			r=p.distribuer();	
			System.out.println("----------------------------------------------------------");
	*/
		//Test 19 : Classe PiocheWagon - M�thode piocher1Carte()
		// 
			System.out.println("Classe : PiocheWagon - M�thode : piocher1Carte()");
			PiocheWagon pw = new PiocheWagon();
			DefausseWagon d = new DefausseWagon();
			LinkedList<CarteWagon> lw3 = new LinkedList<CarteWagon>();
			
			CarteWagon c1 = new CarteWagon("bleu");
			CarteWagon c2 = new CarteWagon("rouge");
			CarteWagon c3 = new CarteWagon("jaune");
			CarteWagon c4 = new CarteWagon("orange");
			CarteWagon c5 = new CarteWagon("locomotive");
			CarteWagon c6 = new CarteWagon("noir");
			CarteWagon c7 = new CarteWagon("vert");
			
			lw3.add(c1);
			lw3.add(c2);
			lw3.add(c3);
			lw3.add(c4);
			lw3.add(c5);
			lw3.add(c6);
			lw3.add(c7);
			
			pw.setPioche(lw3);
			
			System.out.println("Attendu : bleu");
			System.out.println("Obtenu : " + pw.piocher1Carte(d));
			System.out.println("----------------------------------------------------------");
			
			
		//Test 20 : Classe : PiocheWagon - M�thode : piocher1Carte()
			System.out.println("Classe : PiocheWagon - M�thode : piocher1Carte()");
			LinkedList<CarteWagon> lw4 = new LinkedList<CarteWagon>();
			
			
			lw4.add(c6);
			lw4.add(c2);
			lw4.add(c3);
			lw4.add(c4);
			lw4.add(c5);
			lw4.add(c1);
			lw4.add(c7);
			
			pw.setPioche(lw4);
			
			System.out.println("Attendu : noir");
			System.out.println("Obtenu : " + pw.piocher1Carte(d));
			System.out.println("----------------------------------------------------------");
			
			
		//Test 21 : Classe : PiocheWagon - M�thode : piocher1Carte()
			System.out.println("Classe : PiocheWagon - M�thode : piocher1Carte()");
			LinkedList<CarteWagon> lw5 = new LinkedList<CarteWagon>();
			
			
			lw5.add(c5);
			lw5.add(c2);
			lw5.add(c3);
			lw5.add(c4);
			lw5.add(c6);
			lw5.add(c1);
			lw5.add(c7);
			
			pw.setPioche(lw5);
			
			System.out.println("Attendu : locomotive");
			System.out.println("Obtenu : " + pw.piocher1Carte(d));
			System.out.println("----------------------------------------------------------");
			
			
		//Test 22 : Classe : PiocheWagon - M�thode piocher()
			System.out.println("Classe : PiocheWagon - M�thode piocher() // R�ponse aux questions : '0' + '1' + '1'");
			
			LinkedList<CarteWagon> lw6 = new LinkedList<CarteWagon>();
			
			CarteWagon c11 = new CarteWagon("bleu");
			CarteWagon c21 = new CarteWagon("rouge");
			CarteWagon c31 = new CarteWagon("jaune");
			CarteWagon c41 = new CarteWagon("orange");
			CarteWagon c51 = new CarteWagon("Locomotive");
			CarteWagon c61 = new CarteWagon("noir");
			CarteWagon c71 = new CarteWagon("vert");
			CarteWagon c81 = new CarteWagon("blouge");
			
			lw6.add(c51);	//Locomotive
			lw6.add(c21);	//Rouge
			lw6.add(c31);	//Jaune
			lw6.add(c41);	//Orange
			lw6.add(c11);	//Bleu
			lw6.add(c61);	//Noir
			lw6.add(c71);	//Vert
			lw6.add(c81);	//Blouge
				
			PiocheWagon pw2 = new PiocheWagon(lw6);
			DefausseWagon d2 = new DefausseWagon();
			pw2.preparerPiocheVisible(d2);
			
			System.out.println("Attendu : Rouge + Vert");
			System.out.println("Obtenu : " + pw2.piocher(d2));
			System.out.println("----------------------------------------------------------");
			
		//Test 23 : Classe : PiocheWagon - M�thode piocher()
			System.out.println("Classe : PiocheWagon - M�thode piocher() // R�ponse aux questions : '0' + '0'");
			
			pw2.setPioche(lw6);
			
			System.out.println("Attendu : Locomotive");
			System.out.println("Obtenu : " + pw2.piocher(d2));
			System.out.println("----------------------------------------------------------");
			
		//Test 23 : Classe : PiocheWagon - M�thode piocher()
			System.out.println("Classe : PiocheWagon - M�thode piocher() // R�ponse � la question : '1' + '1'");
			
			LinkedList<CarteWagon> lw8 = new LinkedList<CarteWagon>();
			
			lw8.add(c21);	//Rouge
			lw8.add(c11);	//Bleu
			lw8.add(c31);	//Jaune
			lw8.add(c41);	//Orange
			lw8.add(c51);	//loco
			lw8.add(c61);	//Noir
			lw8.add(c71);	//vert
			lw8.add(c81);	//Blouge
			
			PiocheWagon pw3 = new PiocheWagon(lw8);
			DefausseWagon d3 = new DefausseWagon();
			pw3.preparerPiocheVisible(d3);
			
			System.out.println("Attendu : Noir + Vert");
			System.out.println("Obtenu : " + pw3.piocher(d3));
			System.out.println("----------------------------------------------------------");
			
		//Test 24 : Classe : PiocheWagon - M�thode piocher()
			System.out.println("Classe : PiocheWagon - M�thode piocher() // R�ponse � la question : '0' + '3' + '0' + '2'");
			
			LinkedList<CarteWagon> lw9 = new LinkedList<CarteWagon>();
			
			lw9.add(c21);	//Rouge
			lw9.add(c11);	//Bleu
			lw9.add(c31);	//Jaune
			lw9.add(c41);	//Orange
			lw9.add(c51);	//loco
			lw9.add(c61);	//Noir
			lw9.add(c71);	//vert
			lw9.add(c81);	//Blouge
			
			pw3.setPioche(lw9);
			pw3.preparerPiocheVisible(d3);
			
			System.out.println("Attendu : Orange + Jaune");
			System.out.println("Obtenu : " + pw3.piocher(d3));
			System.out.println("----------------------------------------------------------");
	}
}
