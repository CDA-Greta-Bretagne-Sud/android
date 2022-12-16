package vannes.lamy.fragmentnavcontroller;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class FragmentC extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_c, container, false);
        //Délcaration du tableau de de satéllites pour nore arrayadapter
        String[] satellites = {"Mnémé", "Euanthé", "Orthosie", "Harpalycé", "Praxidiké", "Thyoné", "Telxinoé", "Cordélia", "Ophélie", "Bianca", "Cressida", "Desdémone", "Juliette", "Portia", "Rosalinde"};
        // On affecte le tableau  au constructeur de l'arrayAdapter pour concevoir sa liste
        ListView lv = (ListView) v.findViewById(R.id.listc);
        lv.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.label, satellites));

        //declaration du listener pour réagir au clic sur l item de  liste
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // on recupere l' item de liste
                String itemselect = ((TextView) view).getText().toString();
                // Lancement de l'intent pour la seconde activity
                Intent i = new Intent(getActivity(), SingleListItem.class);
                // envoi de l'intent avec sitrng de l'item en parametre
                i.putExtra("infoitem", itemselect);
                startActivity(i);
            }
        });

        return v;
    }


}