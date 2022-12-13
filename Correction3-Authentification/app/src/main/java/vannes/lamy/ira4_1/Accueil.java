package vannes.lamy.ira4_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Accueil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        Intent i=getIntent();
        String login=i.getStringExtra("log");
        TextView bienvenu=findViewById(R.id.bienvenue);
        bienvenu.setText(getResources().getString(R.string.accueil_bienvenue) + " "+login);
    }
    public void btAlarme(View v){
        Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
        i.putExtra(AlarmClock.EXTRA_MESSAGE,"Alarme ESAIP");
        i.putExtra(AlarmClock.EXTRA_HOUR,11);
        i.putExtra(AlarmClock.EXTRA_MINUTES,45);
        startActivity(i);
    }
    public void btEsaip(View v){
        String url="http://esaip.org";
        Intent i= new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
    public void btAppel(View v){
        startActivity(new Intent(Intent.ACTION_CALL,Uri.parse("tel:0645342313")));
    }
   public void fermer(View v){
        finishAffinity();
   }

    public void sendSMS(View v){
        String telephone = "06010203004";
        String message = "test envoi sms";
//definir permission dans androidmanifest:  <uses-permission android:name="android.permission.SEND_SMS"/>
        try {
            // Instanciation du SmsManager
            SmsManager smsManager = SmsManager.getDefault();
            // Send Message
            smsManager.sendTextMessage(telephone, null, message, null, null);
            Log.i( "SMS","sms envoyé!");

        } catch (Exception ex) {
            Log.e( "SMS","erreur envoi SMS", ex);
            Toast.makeText(getApplicationContext(),"Le SMS n'a pas été envoyé " + ex.getMessage(),
                    Toast.LENGTH_LONG).show();
            //affichage exception
            ex.printStackTrace();
        }
    }
    public void sendEmail(View v){
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL,
                new String[] { "test@free.com" });
        email.putExtra(Intent.EXTRA_SUBJECT, "teste du sujet");
        email.putExtra(Intent.EXTRA_TEXT, "texte du message");
        email.setType("message/rfc822");
        startActivity(email);
    }
    public void fb(View v){
        sendIntent("fb");
    }
    public void dj(View v){
        sendIntent("dj");
    }
    public void p4(View v){
        sendIntent("p4");
    }

    public void sendIntent(String mesg){
        Intent i= new Intent(Accueil.this,Jeux.class);
        i.putExtra("msg",mesg);
        startActivity(i);
    }
    public void bb(View v){
        //TODO creer activity video.java
        Intent i= new Intent();
        startActivity(i);
    }

}