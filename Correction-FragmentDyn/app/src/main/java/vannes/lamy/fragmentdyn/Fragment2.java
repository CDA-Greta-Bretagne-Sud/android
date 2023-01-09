package vannes.lamy.fragmentdyn;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment2 extends Fragment implements LifecycleOwner {
    private OnItemSelectedListener listener;
    String message;

    public Fragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("fragment2","OncreateView");
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_2, container, false);

        Button button = v.findViewById(R.id.buttonf2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = "f1";
                listener.messageDuFragment(message);

            }
        });
        return v;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("fragment2","OnAttach");
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
        Log.e("fragment2","OnPause");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.e("fragment2","OnStop");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.e("fragment2","onResume");
    }
    @Override
    public void onDestroyView(){
        super.onDestroyView();
        Log.e("fragment2","OnDestroyView");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.e("fragment2","onDestroy");
    }
}