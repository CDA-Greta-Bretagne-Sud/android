package vannes.lamy.fragmentnavcontroller;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class FragmentAccueil extends Fragment implements Response.Listener<Bitmap>  {
    private ImageView img;
    private ProgressDialog progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_accueil, container, false);
        img= (ImageView) v.findViewById(R.id.imageView);
        this.progress = new ProgressDialog(getActivity());
        this.progress.setTitle("Veuillez patientez");
        this.progress.setMessage("Récupération de l'image en cours...");
        this.progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        this.progress.show();

        //TODO Instancie la file de message (cet objet doit être un singleton)


    RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url ="https://img.phonandroid.com/2018/12/top-10-android-applis.jpg";
        // TODO Requête d'une image à l'URL demandée
// Requête d'une image à l'URL demandée
        ImageRequest picRequest = new ImageRequest(    url, this, 0,
                0, ImageView.ScaleType.CENTER_CROP, Bitmap.Config.RGB_565,
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // Toast.makeText(getActivity(),"Erreur download:"+error,Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        // TODO Insère la requête dans la file
        queue.add(picRequest);
        return v;
    }

    @Override
    public void onResponse(Bitmap response) { //callback en cas de succès
        //TODO fermeture de la boite de dialogue
        if(this.progress.isShowing()) this.progress.dismiss();
        //Affectation de l'image dans l'imageview
        this.img.setImageBitmap(response);
    }
}