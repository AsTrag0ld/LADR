package model;

import java.sql.Connection;

import org.postgresql.ds.PGSimpleDataSource;

public abstract class Service {
	static String SERVER_NAME = "localhost";
	static String DATABASE_NAME = "ladr";
	static String LOGIN_BDD = "postgres";
	static String PASSWORD_BDD = "postgres";
	
	// Connexion à la base de données sans la fermer
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
}
