package vannes.nantes.tpacteur;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.FileProvider;

import static androidx.core.content.FileProvider.getUriForFile;

public class ImageSeule extends Activity{
	int position=0;
	TextView description;

	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.image_seule);
	        Button fermer=(Button)findViewById(R.id.button1);
	        Button partage=(Button)findViewById(R.id.partage);

	        Intent i = getIntent();

	        position = i.getExtras().getInt("id");
	        ActeursListAdapter acteurAdapter= new ActeursListAdapter(this, ActeursListAdapter.itemname, ActeursListAdapter.imgid,ActeursListAdapter.sonid,ActeursListAdapter.desc );
	 //TODO
	        ImageView imageView = (ImageView) findViewById(R.id.image_seule);
	    description=(TextView) findViewById(R.id.textView1);
	        imageView.setImageResource(acteurAdapter.imgid[position]);
	        description.setText(acteurAdapter.itemname[position]+" "+acteurAdapter.desc[position]);
	        
	        fermer.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// instanciation du generator de son
					final ToneGenerator tg3 = new ToneGenerator(
							AudioManager.STREAM_NOTIFICATION, 100);
					// affectation du type de son et de sa dur�e de 200ms
					tg3.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
					//TODO fermeture de la fenetre
					finish();

				}
			});
	    	partage.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// instanciation du generator de son
					final ToneGenerator tg5 = new ToneGenerator(
							AudioManager.STREAM_NOTIFICATION, 100);
					// affectation du type de son et de sa dur�e de 200ms
					tg5.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
					// declaration d ela bitmapfactory pour integrer l'image en
					// piece jointe au mail
					BitmapFactory.Options bitmapFatoryOptions = new BitmapFactory.Options();
					// encodage 256 couleurs
					bitmapFatoryOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
					Bitmap myBitmap = BitmapFactory.decodeResource(
							getResources(), acteurAdapter.imgid[position],
							bitmapFatoryOptions);

					// on retourne un fichier grace � la methode savebitmap afin
					// d'obtenir l'uri

					File mFile = savebitmap(myBitmap);

					Uri u = null;

					u = getUriForFile(getApplicationContext(),"vannes.nantes.tpacteur.fileprovider",mFile);


					Intent email = new Intent(Intent.ACTION_SEND);
					email.putExtra(Intent.EXTRA_EMAIL,
							new String[] { "test@free.com" });
					email.putExtra(Intent.EXTRA_SUBJECT, "info acteur");
					email.putExtra(Intent.EXTRA_TEXT, description.getText().toString());
					email.setType("message/rfc822");

					email.putExtra(Intent.EXTRA_STREAM, u);
					email.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
					startActivity(Intent.createChooser(email, "Envoi email"));

				}
			});

		} 
	    
	
	 private File savebitmap(Bitmap bmp) {

			OutputStream outStream,outStream2 = null;
		 File image,imageEx;

			try {
				//stockage memoire interne dans /data/data/vannes.nantes.tpacteur/files
				image= new File(getApplicationContext().getFilesDir(),"partage.png");
				outStream = new FileOutputStream(image);
				// compression du fichier
				bmp.compress(Bitmap.CompressFormat.PNG, 100, outStream);

				outStream.flush();

				outStream.close();

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			return image;
		}





}
