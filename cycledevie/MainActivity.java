package vannes.nantes.cyclevieactivity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


        //TODO definition du nom du tag pour loguer les evenements ->nom de la classe
        private final String TAG="ActivitePrincipale";
        @Override
        //Called when the activity is first created
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            //TODO à compléter
            Log.i(TAG, );
            if(savedInstanceState!= null){
                onRestoreInstanceState(savedInstanceState);
            }

        }
        //The final call you receive before your activity is destroyed.
        protected void onDestroy() {
            super.onDestroy();

            //TODO à compléter
            Log.i(TAG,);
        }
    protected void onStop() {
        super.onStop();

        //TODO à compléter
        Log.i(TAG,);
    }
    protected void onStart() {
        super.onStart();

        //TODO à compléter
        Log.i(TAG,);
    }
        //Called when the system is about to start resuming a previous activity
        protected void onPause(Bundle savedInstanceState) {
            super.onPause();

            //TODO à compléter
            Log.i(TAG,);
            onSaveInstanceState(savedInstanceState);
        }
        //Called when the activity will start interacting with the user
        protected void onResume() {
            super.onResume();

            //TODO à compléter
            Log.i(TAG,);
        }
        //This method is called before an activity may be killed so that when it comes back some time in the future it can restore its state.
        protected void onSaveInstanceState(Bundle savedInstanceState) {
            super.onSaveInstanceState(savedInstanceState);

            //TODO à compléter
            Log.i(TAG,"OnsaveinstanceState");
        }
        //This method is called after onStart() when the activity is being re-initialized from a previously saved state
        protected void onRestoreInstanceState(Bundle savedInstanceState) {
            super.onRestoreInstanceState(savedInstanceState);
            //TODO à compléter

            Log.i(TAG, "OnrestoreInstanceState");
        }
        //Called when the activity will start interacting with the user
        protected void onRestart() {
            super.onRestart();

            //TODO à compléter
            Log.i(TAG, );
        }
    }

