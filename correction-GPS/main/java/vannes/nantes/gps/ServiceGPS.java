package vannes.nantes.gps;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ServiceGPS extends Service {

	private LocationManager locationMgr = null;
	private LocationListener onLocationChange = new LocationListener() {
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}

		@Override
		public void onProviderEnabled(String provider) {

		}

		@Override
		public void onProviderDisabled(String provider) {
		}

		@RequiresApi(api = Build.VERSION_CODES.KITKAT)
		@Override
		public void onLocationChanged(Location location) {

			Double latitude = location.getLatitude();
			Double longitude = location.getLongitude();
			Double altitude= location.getAltitude();
			Float vitesse= location.getSpeed();
			Long temps=location.getTime();
			//String provider=location.getProvider();


			

			Toast.makeText(
					getBaseContext(),
					"Voici les coordonnees de votre telephone : " + latitude
							+ " " + longitude +" altitude="+altitude +"vitesse:"+vitesse+" temps:"+temps , Toast.LENGTH_LONG).show();
			//sauvegarde sur la memoire interne
			FichierUtil.ecrireFichierI(getApplicationContext().getFilesDir(), "itineraire.txt", latitude + "," + longitude);
			//sauveagrder sur la mémoire externe
			FichierUtil.ecrireFichierE(Environment.DIRECTORY_DOCUMENTS,"itinerairee.txt",latitude + "," + longitude);

		}
	};

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {

		locationMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


		if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			// TODO: Consider calling
			//    ActivityCompat#requestPermissions
			// here to request the missing permissions, and then overriding
			//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
			//                                          int[] grantResults)
			// to handle the case where the user grants the permission. See the documentation
			// for ActivityCompat#requestPermissions for more details.
			return;
		}
//satellite
		locationMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000,
				0, onLocationChange);
//reseau wifi
		//locationMgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000,
			//	0, onLocationChange);

		//Toast.makeText(getApplicationContext(), "service GPS d�marr�",
				//Toast.LENGTH_SHORT).show();

	}
	 public int onStartCommand(Intent intent, int flags, int startId) {
	     
	      Toast.makeText(this, "Service GPS d�marr�", Toast.LENGTH_LONG).show();
	      //dans le cas ou service vient a etre stopp�, on le relance
	      return START_STICKY;
	   }

	@Override
	public void onDestroy() {
	   locationMgr.removeUpdates(onLocationChange);

	
		super.onDestroy();

	}

}
