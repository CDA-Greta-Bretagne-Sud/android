package vannes.lamy.pad2;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pad(View v){
        //tag du bouton
     String tag=  v.getTag().toString();
        switch(tag){
            case "1":
                mp = MediaPlayer.create(MainActivity.this, R.raw.star2);
                break;
            case "2":
                mp = MediaPlayer.create(MainActivity.this, R.raw.berreta);
                break;
            case "3":
                mp = MediaPlayer.create(MainActivity.this, R.raw.caisse);
                break;
            case "4":
                mp = MediaPlayer.create(MainActivity.this, R.raw.magnum);
                break;
            case "5":
                mp = MediaPlayer.create(MainActivity.this, R.raw.star3);
                break;
            case "6":
                mp = MediaPlayer.create(MainActivity.this, R.raw.star4);
                break;
            case "7":
                mp = MediaPlayer.create(MainActivity.this, R.raw.star5);
                break;
            case "8":
                mp = MediaPlayer.create(MainActivity.this, R.raw.corres);
                break;
        }
        mp.start();
        //compteur permettant de changer de couleur le bouton
        //durant la durée de la musique
        new CountDownTimer(mp.getDuration(), 1) {
            public void onTick(long millisUntilFinished) {
                v.setBackgroundColor(Color.RED);
            }
            @Override
            public void onFinish() {
                v.setBackgroundColor(Color.BLUE);
            }
        }.start();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
      switch(item.getItemId()){
            case R.id.fermer:
               finish();
                return true;
            case R.id.animaux:
            //TODO
                return true;
            case R.id.instruments:
                //TODO
                return true;
          case R.id.divers:
              //TODO
              return true;


        }
        return true;
    }
}