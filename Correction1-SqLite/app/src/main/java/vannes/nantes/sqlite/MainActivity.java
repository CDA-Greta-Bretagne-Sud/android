package vannes.nantes.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText nom, prenom,email,rue,numero,codepostale,ville;
    Button add,sup;
    TextView res;

    Persistance reqsql;

    ProgressDialog PD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reqsql = new Persistance(this);

        nom = (EditText) findViewById(R.id.nom);
        prenom = (EditText) findViewById(R.id.prenom);
        email = (EditText) findViewById(R.id.email);

        add = (Button) findViewById(R.id.ajout);
        sup= (Button) findViewById(R.id.sup);
        res = (TextView) findViewById(R.id.result);
        // affichage des enreg. presents en bdd
        BuildTable();

        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // lancement de la tache d'ajout
               insertDatas();
            }
        });
        sup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // lancement de la tache de suppression
                deleteDatas();

            }
        });

    }

    private void BuildTable() {
        // ouverture de bdd
        // selection des enregs
        reqsql.open();
        Cursor c = reqsql.select();
        // placement du cursor au debut
        c.moveToFirst();
        StringBuffer sb = new StringBuffer();
        // Parcours des resultats
        while (!c.isAfterLast()) {
            sb.append(c.getString(0) + "-" + c.getString(1) + "-"
                    + c.getString(2)+ "\n");
            c.moveToNext();
        }
        // affectation des resultats dans le textView
        res.setText(sb.toString());
        reqsql.close();
    }

    // Tache pour insertion des datas
    private void insertDatas() {


            // progresbar
            PD = new ProgressDialog(MainActivity.this);
            PD.setTitle("SVP Attendez..");
            PD.setMessage("Chargement en cours...");
            PD.setCancelable(false);
            PD.show();

            // recuperation des value nom prenom pour insertion
            String valnom = nom.getText().toString();
            String valprenom = prenom.getText().toString();
            String valemail= email.getText().toString();


            // insertion data
            reqsql.open();
            // instanciation dansla classe membre
            Membre m = new Membre(valnom, valprenom,valemail);
            // insertion en bdd
            reqsql.insertData(m);
        reqsql.close();
        BuildTable();
        PD.dismiss();
        }


    // Tache pour supprimer des datas
    private void deleteDatas() {
            // progresbar
            PD = new ProgressDialog(MainActivity.this);
            PD.setTitle("SVP Attendez..");
            PD.setMessage("Chargement en cours...");
            PD.setCancelable(false);
            PD.show();


            // ouverture bdd
            reqsql.open();
            // recuperation du dernier id ins�r�
            int id=reqsql.lastIdInsert();
            // suppression en bdd
            reqsql.deleteMembre(id);
            reqsql.close();
        BuildTable();
        PD.dismiss();

        }

    }


