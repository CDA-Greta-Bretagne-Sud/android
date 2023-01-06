package vannes.nantes.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/*
 * Classe permettant d'�x�cuter les requetes sql sur la table membre
 */
public class Persistance {

	private DatabaseHelper dbhelper;
	private Context moncontext;
	private SQLiteDatabase database;

	// constructeur
	public Persistance(Context c) {
		moncontext = c;
	}

	// ouverture de la bdd
	public Persistance open() throws SQLException {
		dbhelper = new DatabaseHelper(moncontext);
		database = dbhelper.getWritableDatabase();
		return this;

	}

	// fermeture de la bdd
	public void close() {
		dbhelper.close();
	}

	// requete insertion
	public void insertData(Membre m) {
		String nom = m.getNom();
		String prenom = m.getPrenom();
		String email = m.getEmail();


		ContentValues cv = new ContentValues();


		cv.put(DatabaseHelper.MEMBRE_NOM, nom);
		cv.put(DatabaseHelper.MEMBRE_PRENOM, prenom);
		cv.put(DatabaseHelper.MEMBRE_EMAIL, email);
		database.insert(DatabaseHelper.TABLE_MEMBRE, null, cv);

	}

	// requete de suppression
	public void deleteMembre(int id) {

		database.delete(DatabaseHelper.TABLE_MEMBRE, DatabaseHelper.MEMBRE_ID
				+ " = " + id, null);

	}

	// recuperation du dernier id ins�r�
	public int lastIdInsert() {

		final String MY_QUERY = "SELECT MAX(_id) FROM "
				+ DatabaseHelper.TABLE_MEMBRE;
		Cursor cur = database.rawQuery(MY_QUERY, null);
		cur.moveToFirst();
		int ID = cur.getInt(0);

		return ID;

	}

	// requete de selection
	public Cursor select() {
		String selectQuery = "SELECT  * FROM " + DatabaseHelper.TABLE_MEMBRE ;
		Cursor c = database.rawQuery(selectQuery, null);


		if (c != null) {
			c.moveToFirst();
		}
		//c.close();
		return c;

	}

}