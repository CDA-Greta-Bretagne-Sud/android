package vannes.nantes.authentir4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText log,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        log= findViewById(R.id.login);
        pass=findViewById(R.id.password);
    }
    public void actQuitter(View v){
        finish();
    }
    public void actEffacer(View v){
        log.setText("");
        pass.setText("");
    }
    public void actValider(View v){
    if(log.getText().toString().equals(getResources().getString(R.string.login)) &&
    pass.getText().toString().equals(getResources().getString(R.string.mdp))){
        Toast.makeText(getApplicationContext(),getResources().getString(R.string.logpasscorrect),Toast.LENGTH_LONG).show();
       //Envoi de l'intent à l'activity accueil
        Intent i= new Intent(MainActivity.this,Accueil.class);
        i.putExtra("msg",log.getText().toString());
        //lancement de l'activity Accueil
        startActivity(i);
    }
    else
        Toast.makeText(getApplicationContext(),getResources().getString(R.string.logpassincorrect),Toast.LENGTH_LONG).show();
    }
}