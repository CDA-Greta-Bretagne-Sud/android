package vannes.lamy.fragmentnavcontroller;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class FragmentB extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_b, container, false);
        String[][] repertoire = new String[][]{
                {"Jean Valjean","jvaljean@free.fr" },
                {"Victor Hugo","vhugo@yahoo.fr"},
                {"Marcel proust","mproust@gmail.com"},
                {"Albert Camu","acamu@orange.fr"},
                {"Moliere","0moliere@ovh.com"},
                {"Honoré de Balzac","hb@free.fr"},
                {"Emile Zola","zola@yahoo.fr"},
                {"Alphonse Daudet","adaudet@alice.fr"},
                {"Denis Diderot","ddiderot@cegetel.fr"},
                {"Stendhal","stendhal@sfr.fr"},
                {"Jean Racine","racine@free.fr"},
                {"Arthur Rumbaud","arumbaud@sfr.fr"},
                {"Alfred de Musset","ademusset@free.Fr"}
        };

    //création de l'arrayList de hashmap

        List<HashMap<String, String>> liste = new ArrayList<HashMap<String, String>>();

        HashMap<String, String> element;

        for(int i = 0 ; i < repertoire.length ; i++) {

            element = new HashMap<String, String>();

            element.put("nom", repertoire[i][0]);

            element.put("email", repertoire[i][1]);
            liste.add(element);
        }
        ListAdapter adapter = new SimpleAdapter(getActivity(),
                //Valeurs à insérer
                liste,
                /*
                 * Layout de chaque élément (il s'agit d'un layout par défaut
                 * pour avoir deux textes l'un au-dessus de l'autre, c'est pourquoi on
                 * n'affiche que le nom et le numéro d'une personne)
                 */
                android.R.layout.simple_list_item_2,
                /*
                 * Les clés des informations à afficher pour chaque élément :
                 *  - la valeur associée à la clé  text1  sera la première information
                 *  - la valeur associée à la clé  text2  sera la seconde information
                 */
                new String[] {"nom", "email"},
                /*
                 * Enfin, les layouts à appliquer à chaque widget de notre élément
                 * (ce sont des layouts fournis par défaut) :
                 *  - la première information appliquera le layout  android.R.id.text1
                 *  - la seconde information appliquera le layout  android.R.id.text2
                 */
                new int[] {android.R.id.text1,android.R.id.text2 });
        //Pour finir, on donne à la ListView le SimpleAdapter
        ListView lv=(ListView) v.findViewById(R.id.list) ;
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // selected item
                HashMap <String,String> item= (HashMap) lv.getAdapter().getItem(position);
                //TODO declarer l'activity ecrivain dans l'intent
                Intent i = new Intent(getActivity(),Ecrivain.class);
                // sending data to new activity
                i.putExtra("nom", item.get("nom"));
                i.putExtra("email", item.get("email"));
                startActivity(i);

            }

        });



        return v;
    }
}