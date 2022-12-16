package vannes.lamy.fragmentnavcontroller;

import android.content.Intent;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Ecrivain extends AppCompatActivity {
String emailuser,infouser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecrivain);
        //recuperation des textview
        TextView txtUser= findViewById(R.id.user);
        TextView txtEmail= findViewById(R.id.email);
         //récupération valeurs de l'intent

       Intent i=getIntent();
        infouser=i.getStringExtra("nom");
        emailuser= i.getStringExtra("email");
        //affichage dans les textviews
        txtUser.setText(infouser);
        txtEmail.setText(emailuser);
    }
    public void envoiMail(View v){
        //instanciation genearateur de son + volume
        ToneGenerator tg=new ToneGenerator(AudioManager.STREAM_NOTIFICATION,100);
        //affectation type de son et durée

        tg.startTone(ToneGenerator.MAX_VOLUME,2000);
        //declaration intent pour envoi email
        Intent mail= new Intent(Intent.ACTION_SEND);
        mail.putExtra(Intent.EXTRA_EMAIL,new String[]{emailuser});
        //insertion contenu et sujet du mail
        mail.putExtra(Intent.EXTRA_SUBJECT,infouser);
        mail.putExtra(Intent.EXTRA_TEXT,"contenu du message de "+ infouser);
        //definition du type mime pour avoir uniquement les applis email
        mail.setType("message/rfc822");
        startActivity(Intent.createChooser(mail, "Envoi courriel"));
    }
    // implementer le bouton fermer
    public void close(View v){
        finish();
    }
}