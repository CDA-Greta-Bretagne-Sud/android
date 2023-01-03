package vannes.lamy.cdagooglemaps;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import vannes.lamy.cdagooglemaps.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera 47.648163797699475, -2.7751284622047256
        LatLng greta= new LatLng(47.648163797699475, -2.7751284622047256);
        mMap.addMarker(new MarkerOptions().position(greta).title("Greta de Vannes"));
        //zoom en 15x
        CameraPosition cameraPos= new CameraPosition.Builder().target(greta).zoom(15).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPos));
        //type de carte
        // mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        // mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN;
        // mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL;
        //affichage bousole
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true) ;

        // activer runtime permission pour gelocalisation dans le manifest:
        //  <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
        //<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED){
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION},1);

        }

    }
}