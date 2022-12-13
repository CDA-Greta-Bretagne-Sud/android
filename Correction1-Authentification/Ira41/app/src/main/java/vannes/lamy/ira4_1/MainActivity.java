package vannes.lamy.ira4_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
 EditText login;
 EditText pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=findViewById(R.id.login);
        pwd=findViewById(R.id.mdp);
    }

    public void Valider(View v) throws NoSuchAlgorithmException {
        Toast toast=Toast.makeText(getApplicationContext(),"Coucou, comment ça va?",Toast.LENGTH_SHORT);
        toast.show();
        //controle au niveau login et pwd
        //TODO crypté le mot de passe saisi dans le champ

        //TODO crypté le mot de passe dans le fichier strings xml
        // Comparer le login saisi et le mot de passe saisi
        if(login.getText().toString().equals(getResources().getString(R.string.auth_login))&&
                pwd.getText().toString().equals(getResources().getString(R.string.auth_passwd))) {
            Toast t = Toast.makeText(getApplicationContext(), "Authentification OK", Toast.LENGTH_SHORT);
            t.show();
            //déclaration de l'intent pour lancer l'activity Accueil
            Intent i=new Intent(getApplicationContext(),Accueil.class);
            //insertion du message pour récuperer le login dans la seconde activity
            i.putExtra("log",login.getText().toString());
            startActivity(i);
        }
        else {
            Toast tnok = Toast.makeText(getApplicationContext(), "Erreur Authentification ", Toast.LENGTH_SHORT);
            tnok.show();
            login.setText("");
            pwd.setText("");
        }





    }
    public void Effacer(View v){

        //effacer le contenu de login et password
        login.setText("");
        pwd.setText("");
    }

}