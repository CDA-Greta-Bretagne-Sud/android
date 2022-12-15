package vannes.lamy.fragmentnavcontroller;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

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
    public SharedPreferences pos;
    //public SharedPreferences villeSearch;
    String lat;
    String lon;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_a, container, false);
        //révupération latitude longitude
       pos = getActivity().getSharedPreferences(fileName, 0);
        lat =  "47.46481";
        lon = "-0.49729";
// Instancie la file de message (cet objet doit être un singleton)

        queue = Volley.newRequestQueue(getActivity());
        MiseAJour();
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
                MiseAJour();
            }
        });
            return v;
    }
    public void MiseAJour(){

        this.progress = new ProgressDialog(getActivity());
        this.progress.setTitle("Veuillez patientez");
        this.progress.setMessage("Récupération de des informations météos en cours...");
        this.progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        this.progress.show();
        String newUrl=BASE_URL;

            newUrl+="&lat="+lat+"&lon="+lon;
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
                            lat = pos.getString("lat", " 47.66653608490028");
                            lon = pos.getString("long", "-2.7529143456271323");

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
}