package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
    
    public Joueur(String nom, String couleur, int score, LinkedList<Wagon> wagons, LinkedList<CarteWagon> cartesWagon, LinkedList<CarteDestination> cartesDestination,
    		PiocheWagon piocheWagon, PiocheDestination piocheDestination, DefausseWagon defausseWagon) {
    	this.nom = nom;
    	this.couleur = couleur;
    	this.score = score;
    	this.wagons = wagons;
    	this.cartesWagon = cartesWagon;
    	this.cartesDestination = cartesDestination;
    	this.piocheWagon = piocheWagon;
    	this.piocheDestination = piocheDestination;
    	this.defausseWagon = defausseWagon;
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
	
	/* !!!!!!!!!!! PAS TERMINE !!!!!!!!!!!
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
    	this.cartesWagon.addAll(this.piocheWagon.piocher(this.defausseWagon));
    }
    
    /*
     * Ajoute aux CarteDestination du joueur les cartes qu'il a choisi de garder de la pioche de CarteDestination (les retire de la pioche)
     */
    public void piocherCarteDestination() throws OutOfCardsException {
    	this.cartesDestination.addAll(this.piocheDestination.piocher());
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

    /* !!!!!!!!!!! PAS TERMINE !!!!!!!!!!!
     * Appelle les différentes méthodes de consultation des statisques et affiche l'ensemble
     */
    public void consulterStatistiques() {
    	consulterNbVictoires();
    	consulterScoresTotaux();
    }
    
    /*
     * Récupère le nombre de victoires de chaque joueur présent dans la base de donnée
     */
    public void consulterNbVictoires() {
    	Connection con = Service.getConnection();
    	try (PreparedStatement stmt = con.prepareStatement("SELECT idJoueur, nbVictoires FROM Joueur GROUP BY idJoueur")) {
			stmt.execute();
		} catch (Exception e) {
			throw new RuntimeException("Impossible de récupérer le nombre de victoires des joueurs");
		}
    }
    
    /*
     * Récupère la somme de tous les points à chaque partie pour chaque joueur de la base de donnée
     */
    public void consulterScoresTotaux() {
    	Connection con = Service.getConnection();
    	try (PreparedStatement stmt = con.prepareStatement("SELECT idJoueur, sum(score) FROM Classement GROUP BY idJoueur")) {
			stmt.execute();
		} catch (Exception e) {
			throw new RuntimeException("Erreur dans la récupération des scores des joueurs");
		}
    }
    
    /* !!!!!!!!!!! PAS TERMINE !!!!!!!!!!!
     * Récupère les informations du joueur depuis la base de données
     */
    public void getJoueurBDD() {
    	Connection con = Service.getConnection();
    	try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM Joueur WHERE nom = ?")) {
    		stmt.setString(1, this.getNom());
			stmt.execute();
		} catch (Exception e) {
			throw new RuntimeException("Erreur dans la récupération du joueur" + this.getNom());
		}
    }
    
    /* !!!!!!!!!!! PAS TERMINE !!!!!!!!!!!
     * Ajoute un joueur dans la base de données
     */
    public void ajouterJoueurBDD() {
    	Connection con = Service.getConnection();
    	try (PreparedStatement stmt = con.prepareStatement("INSERT INTO Joueur(nom) VALUES (?)")) {
    		stmt.setString(1, this.getNom());
			stmt.execute();
		} catch (Exception e) {
			throw new RuntimeException("Erreur dans la récupération du joueur" + this.getNom());
		}
    }
    

}
