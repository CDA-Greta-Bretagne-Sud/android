package vannes.nantes.authentir4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.TextView;

public class Accueil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        Intent i=getIntent();
        String monlogin=i.getStringExtra("msg");
        //recuperer widget du message d'accueil pour afficher le login
       // TextView messageAccueil= findViewById(R.id.);
        //afficher le message d'accueil
    }
    public void actAlarme(View v){
        //definir dans l'Androidmanifest les permissions
        // <uses-permission android:name="com.android.alarm.permission.SET_ALARM"/>
        Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
        i.putExtra(AlarmClock.EXTRA_MESSAGE, "Alarme QuestionPour1champion");
        i.putExtra(AlarmClock.EXTRA_HOUR, 19);
        i.putExtra(AlarmClock.EXTRA_MINUTES, 00);
        startActivity(i);
    }
    public void actWeb(View v){
        //implémenter la méthode pour lancer le site web lequipe.fr
        //TODO ajouter les permissions Internet dans le manifest
        //<uses-permission android:name="android.permission.INTERNET"/>
        String url = "http://www.lequipe.fr/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

    }
   public void actAppel(View v){
       //implementer la méthode pour lancer un appel
       startActivity(new Intent(Intent.ACTION_CALL,
               Uri.parse("tel:0123123456")));
       //implémenter les permissions dans le manifest
       //<uses-permission android:name="android.permission.CALL_PHONE"/>
   }
    public void actEmail(View v){
        // implementer la méthode pour lancer un email
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL,
                new String[] { "test@free.com" });
        email.putExtra(Intent.EXTRA_SUBJECT, "teste du sujet");
        email.putExtra(Intent.EXTRA_TEXT, "texte du message");
        email.setType("message/rfc822");
        startActivity(email);
    }
    public void actClose(View v){
        //implementer la méthode pour fermer l'activity
        finish();
    }

    public void actVideo(View v){
    //lancement de l'intent pour déclencher l'activity video
        Intent i=new Intent(getApplicationContext(),Video.class);
        startActivity(i);
    }
    public void actFB(View v){
        sendIntent("fb");
    }
    public void actDJ(View v){
        sendIntent("dj");
    }
    public void actP4(View v){
        sendIntent("p4");
    }
    public void sendIntent(String mesg){
        Intent i= new Intent(Accueil.this,Jeux.class);
        i.putExtra("msg",mesg);
        startActivity(i);
    }
}