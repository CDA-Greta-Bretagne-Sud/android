package vannes.lamy.fragmentdyn;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {

private OnItemSelectedListener listener;
String message;
    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v=inflater.inflate(R.layout.fragment_1, container, false);
        Log.e("fragment1","OncreateView");

        Button button = v.findViewById(R.id.buttonf1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = "f2";
                listener.messageDuFragment(message);

            }
        });
        return v;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("fragment1","OnAttach");
        if (context instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " il faut implémenter l'interface OnItemSelectedListener");
        }
    }
    @Override
    public void onPause(){
        super.onPause();
    Log.e("fragment1","OnPause");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.e("fragment1","OnStop");
    }
    @Override
    public void onDestroyView(){
        super.onDestroyView();
        Log.e("fragment1","OnDestroyView");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.e("fragment1","onDestroy");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.e("fragment1","onResume");
    }
}