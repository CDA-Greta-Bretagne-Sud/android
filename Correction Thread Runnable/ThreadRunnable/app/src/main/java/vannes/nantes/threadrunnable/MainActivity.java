package vannes.nantes.threadrunnable;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button download;
    ImageView MonImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        download = (Button) findViewById(R.id.button);
        MonImage = (ImageView) findViewById(R.id.MonImage);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable()
                {//TODO trouver un site pour télécharger une image
                    //execution du thread pour télécharger l'image
                    public void run() {
                         Bitmap bitmap = loadImageFromNetwork("https://images.frandroid.com/wp-content/uploads/2017/02/bugdroid.jpg");
                      //execution du thread pour insérer l'image dans l'imageview
                        MonImage.post(new Runnable() {
                            public void run() {
                                MonImage.setImageBitmap(bitmap);
                            }
                        });
                    }
                }).start();
            }
        });
    }
    private Bitmap loadImageFromNetwork(String url) {
        Bitmap bitmap = null;
        try {
            Log.e("activity","pre-telechargement");
            bitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
            Log.e("activity","post-telechargement");
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}


