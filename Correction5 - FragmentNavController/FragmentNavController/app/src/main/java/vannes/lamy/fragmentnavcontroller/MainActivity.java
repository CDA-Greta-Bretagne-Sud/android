package vannes.lamy.fragmentnavcontroller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private final BottomNavigationView.OnItemSelectedListener navListener = item -> {
        Fragment selectedFragment = null;
        int itemId = item.getItemId();
        if (itemId == R.id.accueil) {
            selectedFragment = new FragmentAccueil();
        } else if (itemId == R.id.fragmentA) {
            selectedFragment = new FragmentA();
        } else if (itemId == R.id.fragmentB) {
            selectedFragment = new FragmentB();
        }else if (itemId == R.id.fragmentC) {
            selectedFragment = new FragmentC();
        }
        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.fragment_container, selectedFragment).commit();
        }
        return true;
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new FragmentAccueil()).commit();
    }
}