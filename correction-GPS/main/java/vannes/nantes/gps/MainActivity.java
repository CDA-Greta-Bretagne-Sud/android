package vannes.nantes.gps;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Notification notification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnStart = (Button) findViewById(R.id.bt1);
        Button btnStop = (Button) findViewById(R.id.bt2);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        //final NotificationManager notificationManager = (NotificationManager)getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());

        //Définition de la redirection au moment du clic sur la notification. Dans notre cas la notification redirige vers notre application

        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);

        //Récupération du titre et description de la notification
        final String notificationTitle = "Service GPS démarré";
        final String notificationDesc = "Le service GPS est demarré pour alimenter les latitudes et longitudes du fichier texte";
//Création de la notification avec spécification de l'icône de la notification et le texte qui apparait à la création de la notification




        btnStart.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View actuelView) {
                startService(new Intent(MainActivity.this, ServiceGPS.class));
                //Notification

             NotificationChannel mchannel=new NotificationChannel("1","channel1",NotificationManager.IMPORTANCE_HIGH);
                notificationManager.createNotificationChannel(mchannel);
                notification  = new NotificationCompat.Builder(MainActivity.this,"channel1")
                        .setContentTitle(notificationTitle)
                        .setContentText(notificationDesc)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setPriority(Notification.PRIORITY_HIGH)
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .build();

                notificationManager.notify(1, notification);
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View actuelView) {

                Intent i = new Intent(MainActivity.this, ServiceGPS.class);
                MainActivity.this.stopService(i);
                notificationManager.cancel(1);
            }
        });

    }
    protected void onDestroy(){
        stopService(new Intent(MainActivity.this, ServiceGPS.class));
        super.onDestroy();
    }
    protected void onPause(){

        super.onPause();
    }

}
