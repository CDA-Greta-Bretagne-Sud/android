package vannes.lamy.cdagooglemaps;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

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
        //carnac latitude et longitude
        double latCarnac=47.61500009982735;
        double longCarnac=-3.091131001089552;
        MarkerOptions marker =new MarkerOptions().position( new LatLng(latCarnac,longCarnac))
                .title("Carnac");
        marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        mMap.addMarker(marker);
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                String uri = "google.streetview:panoid=kcgjisxz_zb6s8SlWZF7rw&cbp=0,30,0,0,-15";

                Intent streetView=new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                streetView.setPackage("com.google.android.apps.maps");
                (MapsActivity.this).startActivity(streetView);
                return false;
            }
        });
        // definition geographique de smachines de l'iles
        double latitudeMI = 47.209893;
        double longitudeMI = -1.563318;
        MarkerOptions markerMI = new MarkerOptions().position(
                new LatLng(latitudeMI, longitudeMI)).title(
                "Les machines de l'Ile");
        marker.icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
        mMap.addMarker(markerMI);

        // zomm x15 de la zone du chu
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(latitudeMI, longitudeMI)).zoom(15)
                .build();

        googleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));
        // ajout d 'une polyline
        // definition du point latitude et longiture
        PolylineOptions polyOptions = new PolylineOptions()
                // coord. chateau nantes
                .add(new LatLng(latCarnac, longCarnac))
                // coord. Machines de l'ile
                .add(new LatLng(latitudeMI, longitudeMI));
        mMap.addPolyline(polyOptions);
        // ajout d'un cercle
        CircleOptions circleOptions = new CircleOptions().center(
                new LatLng(latCarnac, longCarnac)).radius(100); // en metre
        mMap.addCircle(circleOptions);
    }
}