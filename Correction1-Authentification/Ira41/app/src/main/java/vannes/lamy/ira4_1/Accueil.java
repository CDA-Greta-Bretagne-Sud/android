package vannes.lamy.ira4_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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
    //TODO action appel téléphonique

    //TODO action lancement navigteur vers https://esaip.org

    //TODO action de declenchement d'alarme

    //TODO action envoi EMAIL

    //TODO action fermeture fenêtre


}