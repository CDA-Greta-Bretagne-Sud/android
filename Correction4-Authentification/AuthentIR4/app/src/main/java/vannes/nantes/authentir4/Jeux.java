package vannes.nantes.authentir4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class Jeux extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeux);
        Intent i=getIntent();
        String games=i.getStringExtra("msg");
        //récupération de la webview
        WebView wbJeux= findViewById(R.id.webjeu);
        //activation du JavaScript
        WebSettings webSettings= wbJeux.getSettings();
        webSettings.setJavaScriptEnabled(true);
        if(games.equals("dj")){
            wbJeux.loadUrl("file:///android_asset/doodleJump/index.html");
        }
        else if(games.equals("fb")){
            wbJeux.loadUrl("file:///android_asset/flappyBird/index.html");
        }
        else if(games.equals("p4")){
            wbJeux.loadUrl("file:///android_asset/puissance4/index.html");
        }
        else {
            Toast.makeText(getApplicationContext(),
                    "Une erreur s'est produite!",Toast.LENGTH_LONG).show();
        }
    }
}