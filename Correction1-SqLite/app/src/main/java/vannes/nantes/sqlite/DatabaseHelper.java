package vannes.nantes.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
 * Cr�ation de la bdd
 */
public class DatabaseHelper extends SQLiteOpenHelper {

	 // TABLE INFORMATTION
	 public static final String TABLE_MEMBRE = "membre";
	 public static final String MEMBRE_ID = "_id";
	 public static final String MEMBRE_PRENOM = "prenom";
	 public static final String MEMBRE_NOM = "nom";
	 public static final String MEMBRE_EMAIL = "email";

	 // DATABASE INFORMATION
	 static final String DB_NAME = "MEMBRE.DB";
	 static final int DB_VERSION = 3;

	 // TABLE CREATION STATEMENT

	 private static final String CREATE_TABLE = "create table " + TABLE_MEMBRE
	   + "(" + MEMBRE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
	   + MEMBRE_PRENOM + " TEXT NOT NULL ," + MEMBRE_NOM
	   + " TEXT NOT NULL ," + MEMBRE_EMAIL
	   + " TEXT NOT NULL);";



	 public DatabaseHelper(Context context) {
	  super(context, DB_NAME, null, DB_VERSION);

	 }

	 @Override
	 //creation table membre
	 public void onCreate(SQLiteDatabase db) {

	  db.execSQL(CREATE_TABLE);

	 }

	 @Override
	 //en cas de mis a jour li� au changement de version-drop et create
	 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	  db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBRE);

	  onCreate(db);

	 }

	}
