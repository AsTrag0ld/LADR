package ladr.projet.application.lesaventuriersdurail.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Connection;




public class DatabaseHandler extends SQLiteOpenHelper {

	public static final String TABLE_JOUEUR_NAME = "Joueur";
	public static final String TABLE_PARTIE_NAME = "Partie";
	public static final String TABLE_CLASSEMENT_NAME = "Classement";

	public static final String JOUEUR_key = "idJoueur";
	public static final String JOUEUR_name = "nom";
	public static final String JOUEUR_nbVictory = "nbVictoires";
	public static final String PARTIE_key = "idPArtie";
	public static final String PARTIE_nbPlayer = "nbJoueurs";
	public static final String PARTIE_duree = "duree";
	public static final String CLASSEMENT_score="score";

	public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
		super(context, name, factory, version);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		SQLiteDatabase getWritableDatabase;
		onOpen(db);
		db.execSQL("CREATE TABLE"+ TABLE_JOUEUR_NAME + " ("+ JOUEUR_key + " SERIAL PRIMARY KEY, "+ JOUEUR_name + " TEXT NOT NULL, "+ JOUEUR_nbVictory + " INTEGER NOT NULL DEFAULT 0);");
		db.execSQL("CREATE TABLE"+ TABLE_PARTIE_NAME + " ("+ PARTIE_key + " SERIAL PRIMARY KEY, "+ PARTIE_nbPlayer + " INTEGER NOT NULL CHECK ("+PARTIE_nbPlayer+"<6 AND "+PARTIE_nbPlayer+">1),"+PARTIE_duree+"NUMERIC(4,2) NOT NULL DEFAULT 0);");
		db.execSQL("CREATE TABLE"+ TABLE_CLASSEMENT_NAME +" (" +JOUEUR_key + "INTEGER REFERENCES " + TABLE_JOUEUR_NAME + ","+ PARTIE_key+" INTEGER REFERENCES "+TABLE_PARTIE_NAME+", PRIMARY KEY("+JOUEUR_key+", "+PARTIE_key+"),"+CLASSEMENT_score+" INTEGER NOT NULL CHECK("+CLASSEMENT_score+" >=0));");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onCreate(db);
	}


}
