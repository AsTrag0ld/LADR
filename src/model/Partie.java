package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
    private String[] couleurs = {"Blanc", "Bleu", "Jaune", "Vert", "Rouge", "Violet", "Noir", "Marron"};
    private boolean enCours;
    
    public Partie() {
    	this.vainqueur = null;
    	this.joueurs = new ArrayList<Joueur>();
    	this.piocheWagon = null;
    	this.piocheDestination = null;
    	this.map.getInstance();
    	this.defausseWagon = new DefausseWagon();
    	this.enCours = false;
    }
    
    public Partie(ArrayList<Joueur> joueurs, PiocheWagon piocheWagon, PiocheDestination piocheDestination, DefausseWagon defausseWagon) {
    	this.vainqueur = null;
    	this.joueurs = joueurs;
    	this.piocheWagon = piocheWagon;
    	this.piocheDestination = piocheDestination;
    	this.map.getInstance();
    	this.defausseWagon = defausseWagon;
    	this.enCours = false;
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
//    	Connection con = Service.getConnection();
//    	try (PreparedStatement stmt = con.prepareStatement("INSERT INTO Partie(nbJoueurs) VALUES (?)")) {
//    		stmt.setInt(1, this.joueurs.size());
//			stmt.execute();
//		} catch (Exception e) {
//			throw new RuntimeException("Impossible de créer une nouvelle partie");
//		}
//    	try (PreparedStatement stmt = con.prepareStatement("SELECT idPartie FROM Partie GROUP BY idPartie DESC LIMIT 1")) {
//			ResultSet rs = stmt.executeQuery();
//			int idPartie = rs.getInt("idPartie");
//		} catch (Exception e) {
//			throw new RuntimeException("Impossible de créer une nouvelle partie");
//		}
//    	try (PreparedStatement stmt = con.prepareStatement("INSERT INTO Classement VALUES (?, ?, 0)")) {
//    		for (Joueur j : this.joueurs) {
//    			stmt.setInt(1, this.joueurs.size());
//    			stmt.execute();
//    		}
//    		
//		} catch (Exception e) {
//			throw new RuntimeException("Impossible de créer une nouvelle partie");
//		}
    }
    
    public void updateClassementPartie() {
//    	Connection con = Service.getConnection();
//    	try (PreparedStatement stmt = con.prepareStatement("INSERT INTO Classement(nbJoueurs) VALUES (?)")) {
//    		stmt.setInt(1, this.joueurs.size());
//			stmt.execute();
//		} catch (Exception e) {
//			throw new RuntimeException("Impossible de créer une nouvelle partie");
//		}
    }
    
    /* !!!!!!!! PAS TERMINE !!!!!!!!!
     * Initialise toutes les composantes d'une partie
     */
    public void initialiserPartie() {
    	distribuerCartesWagon();
    	distribuerWagons();
    	distribuerCartesDestination();
    	initialiserMap();
    	this.enCours = true;
    }
        
    /*
     * Crée les 110 cartes wagons (12 par couleur + 14 locomotives)
     */
    private void initialiserCartesWagon(String[] couleurs) {
    	LinkedList<CarteWagon> cartesWagon = new LinkedList<CarteWagon>();
    	for (String s : couleurs) {											//Pour chaque couleur présente dans le jeu
    		for (int j = 0; j < 12; j++) {
    			cartesWagon.add(new CarteWagon(s));							//On crée 12 cartes wagon de cette couleur
    		}
    	}
    	for (int i = 0; i < 14; i++) {										//Ensuite on crée 14 cartes locomotives
    		cartesWagon.add(new CarteWagon("Locomotive"));
    	}
    	this.piocheWagon = new PiocheWagon(cartesWagon);
    	this.piocheWagon.preparerPiocheVisible(this.defausseWagon);			//On sort 5 cartes du paquets pour en faire la pioche visible
    }
    
    /* !!!!!! PAS TERMINE !!!!! (Besoin de choisir quelle ville seront reliées par les cartes destinations)
     * Crée les 30 cartes destination
     */
    private void initialiserCartesDestination() {
    	LinkedList<CarteDestination> cartesDestination = new LinkedList<CarteDestination>();
    	
    	this.piocheDestination = new PiocheDestination(cartesDestination);
    }
    
    /*
     * Crée 45 wagons pour la couleur passée en argument
     */
    LinkedList<Wagon> initialiserWagons(String couleur) {
    	LinkedList<Wagon> lesWagons = new LinkedList<Wagon>();
    		for (int j = 0; j < 45; j++) {
    			lesWagons.add(new Wagon(couleur));
    		}
    	return lesWagons;    	
    }
    
    /*
     * Distribue 4 cartes wagon à chaque joueur après avoir mélanger le paquet
     */
    public void distribuerCartesWagon() {
    	initialiserCartesWagon(this.couleurs);
    	this.piocheWagon.melanger();
    	for (Joueur j : this.joueurs) {
    		j.setCartesWagon(this.piocheWagon.distribuer());
    	}
    }
    
    /*
     * Distribue à chaque joueur les wagons correspondants à sa couleur
     */
    public void distribuerWagons() {
    	for (Joueur j : this.joueurs) {										//Pour chaque joueur
    		j.setWagons(initialiserWagons(j.getCouleur()));					//On crée et on lui distibue 45 wagons de sa couleur
    	}
    }
    
    /*
     * Distribue à chaque joueur au moins 2 cartes destinations parmi 3
     */
    public void distribuerCartesDestination() {
    	initialiserCartesDestination();										//On crée les cartes Destination depuis la BDD
    	this.piocheDestination.melanger();									//On mélange le paquet
    	for (Joueur j : this.joueurs) {										//Et pour chaque joueur
    		j.setCartesDestination(this.piocheDestination.distribuer());	//On lance la procédure de distribution (avec question conservation...)
    	}
    }
    
    /*
     * Initialise le plateau de jeu
     */
    public void initialiserMap() {
    	this.map.initialiserRoutes();
    	this.map.initialiserVilles();
    }
    
    /*
     * Permet à un joueur d'effectuer son tour de jeu
     */
    public boolean jouerTourDeJeu(Joueur j) {
    	j.setTourDeJeu(true);
    	System.out.println("Quelle action souhaitez-vous faire ? (0 : piocher carte wagon, 1 : piocher carte destination, 2 : prendre une route");
    	Scanner sc = new Scanner(System.in);
    	int s = sc.nextInt();
    	switch (s) {
    		case 0 : 
    			break;
    		case 1 :
    			break;
    		case 2 : 
    			System.out.println("Quelle route souhaitez-vous prendre ? (entrez l'indice de la route)");
    			this.map.afficherRoutesDisponibles();
    			int indice = sc.nextInt();
    			for (int i = 0; i < this.map.getRoutes().size(); i++) {
    				if (i == indice) {
    					j.prendreRoute(this.map.getRoutes().get(i));
    				}	
    			}
    			break;
    		default : System.out.println("Veuillez choisir parmi '1', '2' ou '3'");
    			break;
    	}
    	j.finirTour();
    	return j.isTourDeJeu();
    }
    

}
