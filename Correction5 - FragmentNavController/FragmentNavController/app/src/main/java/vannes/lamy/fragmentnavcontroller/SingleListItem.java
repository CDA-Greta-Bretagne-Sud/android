package vannes.lamy.fragmentnavcontroller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SingleListItem extends Activity {
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        this.setContentView(R.layout.single_list_item_view);
	         
	        TextView txtitemselect = (TextView) findViewById(R.id.item_label);
	         
	        Intent i = getIntent();
	        // On recupere l'intent et le nom du satellite
	        String itemSelect = i.getStringExtra("infoitem");
	        // on affiche le nom du satellite qu'on affecte à la textview.
	        txtitemselect.setText(itemSelect);
	         
	    }
}
