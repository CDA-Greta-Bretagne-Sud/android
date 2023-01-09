package vannes.nantes.gps;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FichierUtil {

	/*
	 * methode de lecture de fichier interne memory
	 */
	public static String lireFichierI(File dir, String nomFichier) {

		File newfile = new File(dir.getAbsolutePath() + File.separator
				+ nomFichier);

		String monText = "";

		BufferedReader input = null;

		try {

			input = new BufferedReader(new InputStreamReader(
					new FileInputStream(newfile)

			));

			String line;

			StringBuffer buffer = new StringBuffer();

			while ((line = input.readLine()) != null) {

				buffer.append(line);

			}

			monText = buffer.toString();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			if (input != null) {

				try {

					input.close();

				} catch (IOException e) {

					e.printStackTrace();

				}

			}

		}

		return monText;

	}

	/*
	 * m�thode pour ecriture fichier interne
	 */
	public static void ecrireFichierI(File dir, String nomFichier,
			String monText) {

		BufferedWriter writer = null;

		try {

			File newfile = new File(dir.getAbsolutePath() + File.separator
					+ nomFichier);

			newfile.createNewFile();

			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(newfile)));

			writer.write(monText);

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			if (writer != null) {

				try {

					writer.close();

				} catch (IOException e) {

					e.printStackTrace();

				}

			}

		}

	}

	/*
	 * m�thode pour ecriture fichier sur sdcard
	 */

	public static void ecrireFichierE(String repertoire, String nomFichier,
			String monText) {
		File monFichier;
		Boolean success = true;
		File monRep = new File(Environment.getExternalStorageDirectory()
				+ File.separator + repertoire);

		if (!monRep.exists()) {
			success = monRep.mkdir(); // On cr�e le r�pertoire (s'il n'existe
										// pas!!)
		}
		monFichier = new File(monRep, nomFichier);
		if (!success) {

			Log.i("repertInterne", monFichier.getAbsolutePath());
		}

		BufferedWriter writer = null;

		try {

			FileWriter out = new FileWriter(monFichier,true);

			writer = new BufferedWriter(out);

			writer.write(monText+"\n");

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			if (writer != null) {

				try {

					writer.close();

				} catch (IOException e) {

					e.printStackTrace();

				}

			}

		}

	}

	/*
	 * methode lecture de fichiers sur sdcard
	 */
	public static String lireFichierE(String repertoire, String nomFichier) {

		String monText = "";

		File sdLien = Environment.getExternalStorageDirectory();

		File monFichier = new File(sdLien + File.separator + repertoire
				+ File.separator + nomFichier);
		Log.d("Fichier SD", "path de fichier:" + monFichier);

		if (!monFichier.exists()) {

			throw new RuntimeException("Fichier inn�xistant dur la carte sd");

		}

		BufferedReader reader = null;

		try {

			reader = new BufferedReader(new FileReader(monFichier));

			StringBuilder builder = new StringBuilder();

			String line;

			while ((line = reader.readLine()) != null) {

				builder.append(line);

			}

			monText = builder.toString();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			if (reader != null) {

				try {

					reader.close();

				} catch (IOException e) {

					e.printStackTrace();

				}

			}

		}

		return monText;

	}

}
