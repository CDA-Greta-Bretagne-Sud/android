package vannes.lamy.fragmentnavcontroller;

import static android.app.Activity.RESULT_OK;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class FragmentA extends Fragment implements Response.Listener<Bitmap> {
    private ImageView viewer;
    private TextView ville;
    private TextView description;
    private TextView temp;
    private TextView temp_max;
    private TextView temp_min;
    private TextView vit_vent;
    private TextView pression_atm;
    private TextView humidite;
    private TextView direction;
    private ProgressDialog progress;
    private Weather weather;
    private static String BASE_URL = "https://api.openweathermap.org/data/2.5/forecast?id=524901&units=metric&lang=FR&APPID=c0bbff4a824cce23670fa594dfa7e8b1";
    private static String IMG_URL = "https://openweathermap.org/img/w/";
    private SearchManager searchManager;
    private SearchView searchView=null;
    //String requeteVille;
    RequestQueue queue;
    public String fileName = "file";
    protected static final int RESULT_SPEECH = 1;
    public SharedPreferences pos;
    ImageButton btnSpeak;
    //public SharedPreferences villeSearch;
    String lat;
    String lon;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_a, container, false);
        //révupération latitude longitude
      // pos = getActivity().getSharedPreferences(fileName, 0);
        lat =  "47.46481";
        lon = "-0.49729";
// Instancie la file de message (cet objet doit être un singleton)

        queue = Volley.newRequestQueue(getActivity());
        MiseAJour("Vannes");
        this.viewer = (ImageView) v.findViewById(R.id.imageV);
        this.ville = (TextView) v.findViewById(R.id.ville);
        this.description = (TextView) v.findViewById(R.id.desc);
        this.temp = (TextView) v.findViewById(R.id.temp);
        this.temp_max = (TextView) v.findViewById(R.id.temp_max);
        this.temp_min = (TextView) v.findViewById(R.id.temp_min);
        this.vit_vent = (TextView) v.findViewById(R.id.vitvent);
        this.pression_atm = (TextView) v.findViewById(R.id.pression);
        this.humidite = (TextView) v.findViewById(R.id.humidite);
        this.direction = (TextView) v.findViewById(R.id.direction);

        Button btnRefresh = (Button) v.findViewById(R.id.refresh);

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View actuelView) {
               // MiseAJour();
            }
        });
        btnSpeak = (ImageButton) v.findViewById(R.id.buttonSpeak);

        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // lancement de l'intent de reconnaisance vocal
                Intent intent = new Intent(
                        RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                // on spécifie le langage
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "fr-FR");

                try {
                    // on demarre l'activity pour etre sur que l'api nous
                    // retourne le codde correctement
                    startActivityForResult(intent, RESULT_SPEECH);

                } catch (ActivityNotFoundException a) {// leve exception
                    Toast t = Toast.makeText(getActivity(),
                            "Oups! Vote appareil ne supporte pas cette API",
                            Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });
            return v;
    }
    public void MiseAJour(String texte){

        this.progress = new ProgressDialog(getActivity());
        this.progress.setTitle("Veuillez patientez");
        this.progress.setMessage("Récupération de des informations météos en cours...");
        this.progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        this.progress.show();
        String newUrl=BASE_URL;

            //newUrl+="&lat="+lat+"&lon="+lon;
        newUrl+="&q="+texte;
Log.e("openweathermap",newUrl);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, newUrl, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        weather = new Weather();
                        try {
                            Log.e("tab",response.toString());
                            weather = JSONWeatherParser.getWeather(response.toString());

                            ville.setText("Latitude:" +lat.toString()+ " Longitude:"+lon.toString());
                            temp_max.setText("température max: " + weather.temperature.getMaxTemp() + "Degrés Celcius");
                            temp_min.setText("température min: " +  weather.temperature.getMinTemp());
                            temp.setText("température: " + weather.temperature.getTemp());
                            description.setText("Description: " + weather.currentCondition.getDescr());
                            pression_atm.setText("pression Atmospherique: "+ weather.currentCondition.getPressure()+ "HP");
                            vit_vent.setText("Vitesse du vent:" + weather.wind.getSpeed()+"m/s");
                            humidite.setText("Humidité:" +  weather.currentCondition.getHumidity() +"%");
                            direction.setText("Direction du vent:" + weather.wind.getDeg() + " degrés par rapport au Nord");
                            if (progress.isShowing()) progress.dismiss();


                            downloadImage(weather.currentCondition.getIcon(), queue);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.e("error Volley", error.toString());

                    }
                });
        queue.add(jsObjRequest);



    }

    @Override
    public void onResponse(Bitmap response) { //callback en cas de succès
        //fermeture de la boite de dialogue
        if (this.progress.isShowing()) this.progress.dismiss();

        Bitmap bm = Bitmap.createScaledBitmap(response, 400, 400, true);
        //TODO Affectation de l'image dans l'imageview
        this.viewer.setImageBitmap(bm);


    }

    public void downloadImage(String pathImg, RequestQueue queue) {
        // Requête d'une image à l'URL demandée
        Log.i("Image down path:", pathImg);
        ImageRequest picRequest = new ImageRequest(IMG_URL + pathImg + ".png?APPID=c0bbff4a824cce23670fa594dfa7e8b1", this, 0, 0, null, null);
        // Insère la requête dans la file
        queue.add(picRequest);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // si resultat ok
        switch (requestCode) {
            case RESULT_SPEECH: {
                if (resultCode == RESULT_OK && null != data) {
                    // on recupere dans un arrayliste le texte
                    ArrayList<String> text = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    // on l'affiche dasn l'url
                    MiseAJour(text.get(0));
                }
                break;
            }

        }
    }
}