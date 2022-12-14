package vannes.nantes.authentir4;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class Video extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        //Acces à la vidéo Bigbunny
        Uri raw_uri=Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.bigbunny);
        //récupération de la videoview
        VideoView myVideoView=(VideoView)findViewById(R.id.videoView);

        // definition de l'URI à la videoview
        myVideoView.setVideoURI(raw_uri);
        //ajouter un controller
        myVideoView.setMediaController(new MediaController(this));
        //démarrer la video
        myVideoView.start();

    }
}