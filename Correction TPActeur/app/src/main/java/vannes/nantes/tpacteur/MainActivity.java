package vannes.nantes.tpacteur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView list;
    MediaPlayer mp;
    String[] itemname ={
            "Gerard Lanvin",
            "Fabrice Lucchini",
            "Francis Huster",
            "Philippe Torreton",
            "Francois Berléand",
            "Jacques Weber",
            "Micehl Galabru",
            "Pierre Richard",
            "Olivier Saladin",
            "Roland Giraud"
    };
    String[] descname ={
            "desc Gerard Lanvin",
            "desc Fabrice Lucchini",
            "desc Francis Huster",
            "Philippe Torreton",
            "Francois Berléand",
            "Jacques Weber",
            "Micehl Galabru",
            "Pierre Richard",
            "Olivier Saladin",
            "Roland Giraud"
    };
    Integer[] imgid={
            R.drawable.pic_1,
            R.drawable.pic_2,
            R.drawable.pic_3,
            R.drawable.pic_4,
            R.drawable.pic_5,
            R.drawable.pic_6,
            R.drawable.pic_7,
            R.drawable.pic_8,
            R.drawable.pic_9,
            R.drawable.pic_10
    };
    Integer[] sonsid={
            R.raw.berreta,
            R.raw.caisse,
            R.raw.corres,
            R.raw.magnum,
            R.raw.star2,
            R.raw.repondeur,
            R.raw.star3,
            R.raw.star4,
            R.raw.star5,
            R.raw.caisse
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //demande de permission
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }





        mp = MediaPlayer.create(MainActivity.this, R.raw.star2);
// mettre a jour les parametres du constructeurs
        ActeursListAdapter adapter=new ActeursListAdapter(this,itemname,imgid,sonsid,descname );
//recuperation de la listview
        list=findViewById(R.id.list);
        list.setAdapter(adapter);

    }

    public void onRequestPermissionsResult(int requestCode,String permission[], int [] grantResults){
        switch(requestCode){
            case 1:{
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getApplicationContext(),"permission Acceptée par l'utilisateur",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "permission Refusée par l'utilisateur",
                            Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
    public void stop(View v){
    mp.stop();
    }
    public void play(View v){
        //Recuperation de la position de l'item selectionné defini dans le tag (cf acteurlisteadapter)
       int position = (Integer)v.getTag();
        mp = MediaPlayer.create(MainActivity.this, sonsid[position]);
        mp.start();
    }
    public void sendActivity(View v){
        int position = (Integer)v.getTag();
// Sending image id to ImageSeule
        Intent i = new Intent(getApplicationContext(), ImageSeule.class);
//  passing array index
        i.putExtra("id", position);
// Lancement activity
        startActivity(i);
    }
}