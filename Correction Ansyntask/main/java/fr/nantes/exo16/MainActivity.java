package fr.nantes.exo16;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;

import android.widget.Toast;
import android.widget.VideoView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;


public class MainActivity extends AppCompatActivity {

    private final static String TAG = "DownloadTaskActivity";
    private DownloadFileAsync d;
	private Button startBtn;
	private Button startVid;
	private ProgressBar progressBar;
	private VideoView videoV;
	final String url = "http://cda.greta-bretagne-sud.fr/bigbunny.mp4";
	//stockage du fichier  sur rep partagé
	File maVideo = new File(Environment.getExternalStorageDirectory()+File.separator+Environment.DIRECTORY_MOVIES + "/bigbunny.mp4");

	String stockageFichier = maVideo.getPath();



	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		startBtn = (Button) findViewById(R.id.startBtn);
		startVid = (Button) findViewById(R.id.startImg);
		progressBar = (ProgressBar) findViewById(R.id.progress);
		//Mise en place des runtimes permissions
		if (ContextCompat.checkSelfPermission
						(this ,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
		}
		startBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
			//lancement du telechargement
				startDownload();
			}
		});
		startVid.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//les intents ne connaissent que les uri
				//Cast de file en uri
				Uri uri = Uri.fromFile(maVideo);
				Log.i("Android_async",stockageFichier);
				//instanciation de l'intent pour visualiser la video
				Intent intent = new Intent(Intent.ACTION_GET_CONTENT,uri);
				// le MIME pour qu'android sache quel type de fichier à afficher
				intent.setType("video/mp4");
				startActivity(intent);
			}
			
			
		});
	}
	//methode permettant de controler le choix fait par l'utilisateur sur les permissions
	public void onRequestPermissionsResult(int requestCode,String permissions[],int [] grantResults) {
		switch
				(requestCode) {
			case 1: {
				if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED
						) {
					Toast.makeText(getApplicationContext(),"Enregistrement de fichier accepté" ,Toast.LENGTH_SHORT).show();
				}
				else
				{
					Toast.makeText(getApplicationContext(),"permission refusée pour ecrire le fichier",Toast.LENGTH_SHORT).show();
					finish();
				}
				return;
			}
		}
	}
	public void initProgress(){
		//mise a jour de la barre de progression à 0
		updateProgress(0);

	}
//methode lancant le telechargement
	private void startDownload() {
		//instanciation de la classe DownloadFileAsync
       d= new DownloadFileAsync();

        //execution du thread

		d.execute(url);

	}
	//Methode appelée par la classe downloadFileAsync pour mettre à jour la barre de progression
    private void updateProgress(Integer n) {
		progressBar.setProgress(n);
		Log.i("update-progress","update:"+n);
	}

	class DownloadFileAsync extends AsyncTask<String, Integer, String> {


		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			initProgress();
		}
	//traitement principal
		@Override
		protected String doInBackground(String... aurl) {
			int count;

			try {
				// instanciation de l'url
				URL url = new URL(aurl[0]);
				// ouverture de la connexion au servuer
				URLConnection connexion = url.openConnection();
				connexion.connect();
				//recuperation de la taille du fichier
				int lenghtOfFile = connexion.getContentLength();
				Log.d("ANDRO_ASYNC", "Taille du fichier: " + lenghtOfFile);
				//déclaration d'un inputstream pour récuperer le flux binaire de l'image
				InputStream input = new BufferedInputStream(url.openStream());
				//déclaration d'un outputstream pour ecrire  dans un fichier
				OutputStream output = new FileOutputStream(stockageFichier);
				//stockage temporaire du flux du fichier binaire
				byte data[] = new byte[1024];

				int total = 0;
				//parcours et recriture dans le fichier
				for(int ii=0; ii<lenghtOfFile;ii++){
					count = input.read(data);
					total += count;
					//publishprogress appelle OnProgressUpdate pour mettre a jour la barre de progression
					publishProgress((int) (( ((float) total) * 100)/lenghtOfFile));
					//ecriture du flux binaire dans le fichier
					output.write(data, 0, count);
					//afficage dans log debug
					Log.d("ANDRO_WRITEFILE", "count:"+(int) (( ((float) total) * 100)/lenghtOfFile));
					ii++;
				}
			//fermeture des objets et fichier
				output.flush();
				output.close();
				input.close();
			} catch (Exception e) {
			}
			return "telechargement ok";

		}

		protected void onProgressUpdate(Integer ... progress) {
			Log.d("ANDRO_ASYNC", "progression:" + progress[0]);
			updateProgress(progress[0]);
		}

		@Override
		protected void onPostExecute(String s) {
			Log.d("ANDRO_ASYNC", "valeur de retour de doinbackground:" + s);
			Uri Vuri = Uri.fromFile(maVideo);

				videoV=(VideoView)findViewById(R.id.videoView);

			videoV.setVideoURI(Vuri);
			videoV.setMediaController(new MediaController(MainActivity.this));
			videoV.start();
			

		}
	}
}
