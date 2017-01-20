package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Partie {
    private Joueur vainqueur;
    private List<Joueur> joueurs;
    private PiocheWagon piocheWagon;
    private Map map;
    private DefausseWagon defausseWagon;
    private PiocheDestination piocheDestination;

    public Partie() {
    	this.vainqueur = null;
    	this.joueurs = new ArrayList<Joueur>();
    	this.piocheWagon = new PiocheWagon();
    	this.piocheDestination = new PiocheDestination();
    	this.map.getInstance();
    	this.defausseWagon = new DefausseWagon();
    }
    
    public Partie(ArrayList<Joueur> joueurs, PiocheWagon piocheWagon, PiocheDestination piocheDestination, DefausseWagon defausseWagon) {
    	this.vainqueur = null;
    	this.joueurs = joueurs;
    	this.piocheWagon = piocheWagon;
    	this.piocheDestination = piocheDestination;
    	this.map.getInstance();
    	this.defausseWagon = defausseWagon;
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
    	ArrayList<String> lesCouleurs = créerCouleurs();
    	initialiserCartesWagon(lesCouleurs);
    	initialiserCartesDestination();
    	distribuerCartesWagon();
    	distribuerWagons();
    }
    
    /*
     * Crée la liste des couleurs du jeu
     */
    public ArrayList<String> créerCouleurs() {
    	ArrayList<String> couleurs = new ArrayList<String>();
    	couleurs.add("Blanc");
    	couleurs.add("Bleu");
    	couleurs.add("Jaune");
    	couleurs.add("Vert");
    	couleurs.add("Rouge");
    	couleurs.add("Violet");
    	couleurs.add("Noir");
    	couleurs.add("Marron");
    	return couleurs;
    }
    
    /*
     * Crée les 110 cartes wagons (12 par couleur + 14 locomotives)
     */
    public void initialiserCartesWagon(ArrayList<String> couleurs) {
    	LinkedList<CarteWagon> cartesWagon = new LinkedList<CarteWagon>();
    	for (String s : couleurs) {
    		for (int j = 0; j < 12; j++) {
    			cartesWagon.add(new CarteWagon(s));
    		}
    	}
    	for (int i = 0; i < 14; i++) {
    		cartesWagon.add(new CarteWagon("Locomotive"));
    	}
    	this.piocheWagon.setCarteWagon(cartesWagon);
    }
    
    /*
     * Crée les 30 cartes destination
     */
    public void initialiserCartesDestination() {
    	
    }
    
    /*
     * Crée 45 wagons pour la couleur passée en argument
     */
    public LinkedList<Wagon> initialiserWagons(String couleur) {
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
    	this.piocheWagon.melanger();
    	for (Joueur j : this.joueurs) {
    		j.setCartesWagon(this.piocheWagon.distribuer());
    	}
    }
    
    /*
     * Distribue à chaque joueur les wagons correspondants à sa couleur
     */
    public void distribuerWagons() {
    	for (Joueur j : this.joueurs) {
    		j.setWagons(initialiserWagons(j.getCouleur()));
    	}
    }
    
    

}
