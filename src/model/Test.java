package model;

import java.awt.List;
import java.util.LinkedList;

public class Test {
	public static void main(String args[]) throws OutOfCardsException{
		//Test 1 Classe : Joueur - Méthode : verificationCartesRoute() 
		//nbCartes < taille de la route	
			System.out.println("Classe : Joueur - Méthode : verificationCartesRoute() // nbCartes < taille de la route	");
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
		
		//Test 2 Classe : Joueur - Méthode : verificationCartesRoute() 
		//nbCartes = taille de la route
			System.out.println("Classe : Joueur - Méthode : verificationCartesRoute() // nbCartes = taille de la route");
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
	
		//Test 3 Classe : Joueur - Méthode : verificationCartesRoute() 
		//nbCartes > taille de la route
			System.out.println("Classe : Joueur - Méthode : verificationCartesRoute() // nbCartes > taille de la route");
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
		
	
		//Test 4 : Classe Joueur - Méthode : vérificationWagonRoute() 
		//nbWagon < taille route
			System.out.println("Classe Joueur - Méthode : vérificationWagonRoute() // nbWagon < taille route");
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
		
		
	
		//Test 5 : Classe Joueur - Méthode : vérificationWagonRoute() 
		//nbWagon = taille route
			System.out.println("Classe Joueur - Méthode : vérificationWagonRoute() // nbWagon = taille route");
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
				
		
		//Test 6 : Classe Joueur - Méthode : vérificationWagonRoute() 
		//nbWagon > taille route
			System.out.println("Classe Joueur - Méthode : vérificationWagonRoute() // nbWagon > taille route");		
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
				
	
		//Test 7 : Classe Partie - Méthode determinerVainqueur()
		//J1 = 20 et J2 = 40
			
			System.out.println("Classe Partie - Méthode determinerVainqueur() // J1 = 20 et J2 = 40");	
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
				
				
		//Test 8 : Classe Partie - Méthode determinerVainqueur()
		//J1 = 40 et J2 = 40
			
			System.out.println("Classe Partie - Méthode determinerVainqueur() // J1 = 40 et J2 = 40");
			j7.setScore(40);
			j8.setScore(40);
					
			System.out.println("Attendu : Georis");
			System.out.println("Obtenu : " + p.determinerVainqueur().getNom());
			System.out.println("----------------------------------------------------------");
	
	
		//Test 9 : Classe Partie - Méthode determinerVainqueur()
		//J1 = 60 et J2 = 40
			
			System.out.println("Classe Partie - Méthode determinerVainqueur() // J1 = 60 et J2 = 40");
			j1.setScore(60);
			j2.setScore(40);
						
			System.out.println("Attendu : Georis");
			System.out.println("Obtenu : " + p.determinerVainqueur().getNom());
			System.out.println("----------------------------------------------------------");
	
				
			
		//Test 10 : Classe Partie - Méthode initialiserWagons()
		//Couleur = rouge
			
			System.out.println("Classe Partie - Méthode initialiserWagons() // Couleur = rouge");
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
		
		
		//Test 11 : Classe Partie - Méthode initialiserWagons()
		//Couleur = bleu
			
			System.out.println("Classe Partie - Méthode initialiserWagons() // Couleur = bleu");
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
	
		
		//Test 14 : Classe PiocheDestination - Méthode conserverCarte()
		//réponse oui
		 	System.out.println("Classe PiocheDestination - Méthode conserverCarte() // Réponse : '1'");
			PiocheDestination p1 = new PiocheDestination();
			CarteDestination cd = new CarteDestination(15, new Ville("Bavay"), new Ville("Maubeuge"));
			System.out.println("Attendu : true");
			System.out.println("Obtenu : " + p1.conserverCarte(cd));
			System.out.println("----------------------------------------------------------");
	
		
		
		//Test 15 : Classe PiocheDestination - Méthode conserverCarte()
		//réponse non
		 	System.out.println("Classe PiocheDestination - Méthode conserverCarte() // Réponse : '0'");
			PiocheDestination p11 = new PiocheDestination();
			CarteDestination cd2 = new CarteDestination(15, new Ville("Bavay"), new Ville("Maubeuge"));
			System.out.println("Attendu : false");
			System.out.println("Obtenu : " + p11.conserverCarte(cd2));
			System.out.println("----------------------------------------------------------");
	
	
		//Test 16 : Classe PiocheDestination - Méthode distribuer()
		// le joueur conserve deux cartes
		 	System.out.println("Classe PiocheDestination - Méthode distribuer() // Réponse : '1' + '0' + '1'");
			PiocheDestination p3 = new PiocheDestination();
			LinkedList<CarteDestination> l1 = new LinkedList<CarteDestination>();
		
			l1.add(new CarteDestination(15, new Ville("Bavay"), new Ville("Maubeuge")));
			l1.add(new CarteDestination(25, new Ville("Bavay"), new Ville("Valenciennes")));
			l1.add(new CarteDestination(40, new Ville("Valenciennes"), new Ville("Maubeuge")));
			l1.add(new CarteDestination(3, new Ville("Bavay"), new Ville("Mecquignies")));
			p3.setCarteDestination(l1);
			
			LinkedList<CarteDestination> r = new LinkedList<CarteDestination>();
			r=p3.distribuer();
			
			System.out.println("Attendu : 'Bavay -> Maubeuge' + 'Valenciennes -> Maubeuge'" );
			System.out.println("Obtenu : ");
			for (int i = 0; i < r.size(); i++) {
				System.out.println(r.get(i));
			}
			System.out.println("----------------------------------------------------------");
	
	
		//Test 17 : Classe PiocheDestination - Méthode distribuer()
		// le joueur ne conserve pas de carte
			System.out.println("Classe PiocheDestination - Méthode distribuer() // Réponse : '0' + '0' + '1' + '1' + '0' + '1'");
			LinkedList<CarteDestination> l2 = new LinkedList<CarteDestination>();
		
			l2.add(new CarteDestination(15, new Ville("Bavay"), new Ville("Maubeuge")));
			l2.add(new CarteDestination(25, new Ville("Bavay"), new Ville("Valenciennes")));
			l2.add(new CarteDestination(40, new Ville("Valenciennes"), new Ville("Maubeuge")));
			l2.add(new CarteDestination(3, new Ville("Bavay"), new Ville("Mecquignies")));
			p3.setCarteDestination(l2);
			
			LinkedList<CarteDestination> r11 = new LinkedList<CarteDestination>();
			r11=p3.distribuer();
			
			System.out.println("Attendu : 'Bavay -> Maubeuge' + 'Valenciennes -> Maubeuge'" );
			System.out.println("Obtenu : " + r11.size());
			for (int i = 0; i < r11.size(); i++) {
				System.out.println(r11.get(i));
			}
			System.out.println("----------------------------------------------------------");
			
		//Test 18 : Classe PiocheDestination - Méthode distribuer()
		// le joueur conserve trois cartes
			System.out.println("Classe PiocheDestination - Méthode distribuer() // Réponse : '1' + '1' + '1'");
			LinkedList<CarteDestination> l3 = new LinkedList<CarteDestination>();
		
			l3.add(new CarteDestination(15, new Ville("Bavay"), new Ville("Maubeuge")));
			l3.add(new CarteDestination(25, new Ville("Bavay"), new Ville("Valenciennes")));
			l3.add(new CarteDestination(40, new Ville("Valenciennes"), new Ville("Maubeuge")));
			l3.add(new CarteDestination(3, new Ville("Bavay"), new Ville("Mecquignies")));
			p3.setCarteDestination(l3);
			
			LinkedList<CarteDestination> r111 = new LinkedList<CarteDestination>();
			r111=p3.distribuer();
			
			System.out.println("Attendu : 'Bavay -> Maubeuge' + 'Bavay -> Valenciennes' + 'Valenciennes -> Maubeuge'" );
			System.out.println("Obtenu : " + r111.size());
			for (CarteDestination c : r111) {
				System.out.println(c);
			}
			System.out.println("----------------------------------------------------------");
	
		//Test 19 : Classe PiocheWagon - Méthode piocher1Carte()
		// 
			System.out.println("Classe : PiocheWagon - Méthode : piocher1Carte()");
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
			
			
		//Test 20 : Classe : PiocheWagon - Méthode : piocher1Carte()
			System.out.println("Classe : PiocheWagon - Méthode : piocher1Carte()");
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
			
			
		//Test 21 : Classe : PiocheWagon - Méthode : piocher1Carte()
			System.out.println("Classe : PiocheWagon - Méthode : piocher1Carte()");
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
			
			
		//Test 22 : Classe : PiocheWagon - Méthode piocher()
			System.out.println("Classe : PiocheWagon - Méthode piocher() // Réponse aux questions : '0' + '1' + '1'");
			
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
			
		//Test 23 : Classe : PiocheWagon - Méthode piocher()
			System.out.println("Classe : PiocheWagon - Méthode piocher() // Réponse aux questions : '0' + '0'");
			
			pw2.setPioche(lw6);
			
			System.out.println("Attendu : Locomotive");
			System.out.println("Obtenu : " + pw2.piocher(d2));
			System.out.println("----------------------------------------------------------");
			
		//Test 24 : Classe : PiocheWagon - Méthode piocher()
			System.out.println("Classe : PiocheWagon - Méthode piocher() // Réponse à la question : '1' + '1'");
			
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
			
		//Test 25 : Classe : PiocheWagon - Méthode piocher()
			System.out.println("Classe : PiocheWagon - Méthode piocher() // Réponse à la question : '0' + '3' + '0' + '2'");
			
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
		
		//Test 26 : Classe PiocheDestination - Méthode distribuer()
		// le joueur conserve deux cartes
		 	System.out.println("Classe PiocheDestination - Méthode piocher() // Réponse : '1' + '0' + '1'");
			PiocheDestination p31 = new PiocheDestination();
			LinkedList<CarteDestination> l11 = new LinkedList<CarteDestination>();
		
			l11.add(new CarteDestination(15, new Ville("Bavay"), new Ville("Maubeuge")));
			l11.add(new CarteDestination(25, new Ville("Bavay"), new Ville("Valenciennes")));
			l11.add(new CarteDestination(40, new Ville("Valenciennes"), new Ville("Maubeuge")));
			l11.add(new CarteDestination(3, new Ville("Bavay"), new Ville("Mecquignies")));
			p31.setCarteDestination(l11);
			
			LinkedList<CarteDestination> r12 = new LinkedList<CarteDestination>();
			r12=p31.piocher();
			
			System.out.println("Attendu : 'Bavay -> Maubeuge' + 'Valenciennes -> Maubeuge'" );
			System.out.println("Obtenu : ");
			for (int i = 0; i < r12.size(); i++) {
				System.out.println(r12.get(i));
			}
			System.out.println("----------------------------------------------------------");
	
	
		//Test 27 : Classe PiocheDestination - Méthode distribuer()
		// le joueur ne conserve pas de carte
			System.out.println("Classe PiocheDestination - Méthode piocher() // Réponse : '0' + '0' + '1'");
			LinkedList<CarteDestination> l21 = new LinkedList<CarteDestination>();
		
			l21.add(new CarteDestination(15, new Ville("Bavay"), new Ville("Maubeuge")));
			l21.add(new CarteDestination(25, new Ville("Bavay"), new Ville("Valenciennes")));
			l21.add(new CarteDestination(40, new Ville("Valenciennes"), new Ville("Maubeuge")));
			l21.add(new CarteDestination(3, new Ville("Bavay"), new Ville("Mecquignies")));
			p31.setCarteDestination(l21);
			
			LinkedList<CarteDestination> r31 = new LinkedList<CarteDestination>();
			r31=p31.piocher();
			
			System.out.println("Attendu : 'Valenciennes -> Maubeuge'" );
			System.out.println("Obtenu : " + r31.size());
			for (int i = 0; i < r31.size(); i++) {
				System.out.println(r31.get(i));
			}
			System.out.println("----------------------------------------------------------");
			
		//Test 28 : Classe PiocheDestination - Méthode distribuer()
		// le joueur ne conserve pas de carte
			System.out.println("Classe PiocheDestination - Méthode piocher() // Réponse : '0' + '0' + '0' + '1' + '0' + '0'");
			LinkedList<CarteDestination> l22 = new LinkedList<CarteDestination>();
		
			l22.add(new CarteDestination(15, new Ville("Bavay"), new Ville("Maubeuge")));
			l22.add(new CarteDestination(25, new Ville("Bavay"), new Ville("Valenciennes")));
			l22.add(new CarteDestination(40, new Ville("Valenciennes"), new Ville("Maubeuge")));
			l22.add(new CarteDestination(3, new Ville("Bavay"), new Ville("Mecquignies")));
			p31.setCarteDestination(l22);
			
			LinkedList<CarteDestination> r33 = new LinkedList<CarteDestination>();
			r33=p31.piocher();
			
			System.out.println("Attendu : 'Bavay -> Maubeuge'" );
			System.out.println("Obtenu : " + r33.size());
			for (int i = 0; i < r33.size(); i++) {
				System.out.println(r33.get(i));
			}
			System.out.println("----------------------------------------------------------");
			
		//Test 29 : Classe PiocheDestination - Méthode distribuer()
		// le joueur conserve trois cartes
			System.out.println("Classe PiocheDestination - Méthode piocher() // Réponse : '1' + '1' + '1'");
			LinkedList<CarteDestination> l31 = new LinkedList<CarteDestination>();
		
			l31.add(new CarteDestination(15, new Ville("Bavay"), new Ville("Maubeuge")));
			l31.add(new CarteDestination(25, new Ville("Bavay"), new Ville("Valenciennes")));
			l31.add(new CarteDestination(40, new Ville("Valenciennes"), new Ville("Maubeuge")));
			l31.add(new CarteDestination(3, new Ville("Bavay"), new Ville("Mecquignies")));
			p31.setCarteDestination(l31);
			
			LinkedList<CarteDestination> r32 = new LinkedList<CarteDestination>();
			r32=p31.piocher();
			
			System.out.println("Attendu : 'Bavay -> Maubeuge' + 'Bavay -> Valenciennes' + 'Valenciennes -> Maubeuge'" );
			System.out.println("Obtenu : " + r32.size());
			for (CarteDestination c : r32) {
				System.out.println(c);
			}
			System.out.println("----------------------------------------------------------");
		
	}
}
