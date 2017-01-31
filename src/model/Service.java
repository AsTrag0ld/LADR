package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.postgresql.ds.PGSimpleDataSource;

public abstract class Service {
	static String SERVER_NAME = "localhost";
	static String DATABASE_NAME = "ladr";
	static String LOGIN_BDD = "postgres";
	static String PASSWORD_BDD = "postgres";
	
	// Connexion � la base de donn�es sans la fermer
		public static Connection getConnection() {
			PGSimpleDataSource ds = new PGSimpleDataSource();
			ds.setServerName(SERVER_NAME);
			ds.setDatabaseName(DATABASE_NAME);
			Connection con = null;
			try {
				con = ds.getConnection(LOGIN_BDD, PASSWORD_BDD);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return con;
		}
		
		 /* !!!!!!!!!!! PAS TERMINE !!!!!!!!!!!
	     * Appelle les diff�rentes m�thodes de consultation des statisques et affiche l'ensemble
	     */
	    public void consulterStatistiques() {
	    	consulterNbVictoires();
	    	consulterScoresTotaux();
	    }
	    
	    /*
	     * R�cup�re le nombre de victoires de chaque joueur pr�sent dans la base de donn�e
	     */
	    public void consulterNbVictoires() {
	    	Connection con = Service.getConnection();
	    	try (PreparedStatement stmt = con.prepareStatement("SELECT idJoueur, nbVictoires FROM Joueur GROUP BY idJoueur")) {
				stmt.execute();
			} catch (Exception e) {
				throw new RuntimeException("Impossible de r�cup�rer le nombre de victoires des joueurs");
			}
	    }
	    
	    /*
	     * R�cup�re la somme de tous les points � chaque partie pour chaque joueur de la base de donn�e
	     */
	    public void consulterScoresTotaux() {
	    	Connection con = Service.getConnection();
	    	try (PreparedStatement stmt = con.prepareStatement("SELECT idJoueur, sum(score) FROM Classement GROUP BY idJoueur")) {
				stmt.execute();
			} catch (Exception e) {
				throw new RuntimeException("Erreur dans la r�cup�ration des scores des joueurs");
			}
	    }
	    
	    /* !!!!!!!!!!! PAS TERMINE !!!!!!!!!!!
	     * R�cup�re les informations du joueur depuis la base de donn�es
	     */
	    public void getJoueurBDD(Joueur j) {
	    	Connection con = Service.getConnection();
	    	try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM Joueur WHERE nom = ?")) {
	    		stmt.setString(1, j.getNom());
				stmt.execute();
			} catch (Exception e) {
				throw new RuntimeException("Erreur dans la r�cup�ration du joueur" + j.getNom());
			}
	    }
	    
	    /* !!!!!!!!!!! PAS TERMINE !!!!!!!!!!!
	     * Ajoute un joueur dans la base de donn�es
	     */
	    public void ajouterJoueurBDD(Joueur j) {
	    	Connection con = Service.getConnection();
	    	try (PreparedStatement stmt = con.prepareStatement("INSERT INTO Joueur(nom) VALUES (?)")) {
	    		stmt.setString(1, j.getNom());
				stmt.execute();
			} catch (Exception e) {
				throw new RuntimeException("Erreur dans la r�cup�ration du joueur" + j.getNom());
			}
	    }
}
