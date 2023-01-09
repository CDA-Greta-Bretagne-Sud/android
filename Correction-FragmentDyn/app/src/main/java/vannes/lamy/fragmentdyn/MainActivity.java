package vannes.lamy.fragmentdyn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener{
FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Appel du manager pour effectuer des transactions
        ft=getSupportFragmentManager().beginTransaction();
        //load fragment1
        ft.replace(R.id.fragmentdyn,new Fragment1());
        ft.commit();
    }

    @Override
    public void messageDuFragment(String link) {
        ft = getSupportFragmentManager().beginTransaction();
        //message recu du fragment1
        if(link.equals("f2")){
            Toast.makeText(getApplicationContext(), "message de F1", Toast.LENGTH_SHORT).show();
            //chargement du fragment2
            ft.replace(R.id.fragmentdyn, new Fragment2());
            ft.commit();
        }
        //message recu du fragment2
        else  if(link.equals("f1")){
            Toast.makeText(getApplicationContext(), "message de F2", Toast.LENGTH_SHORT).show();
            //chargement du fragment1
            ft.replace(R.id.fragmentdyn, new Fragment1());
            ft.commit();
        }
    }
}