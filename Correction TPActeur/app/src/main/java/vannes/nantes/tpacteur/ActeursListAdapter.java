package vannes.nantes.tpacteur;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ActeursListAdapter extends ArrayAdapter<String> {
	 
	 private final Activity context;
	 public  static String[] itemname;
	 public static Integer[] imgid;
	 public static String[] desc;
	public static Integer[] sonid;
	 
	 public ActeursListAdapter(Activity context, String[] itemname, Integer[] imgid, Integer[] sonsid,String[] description) {
	 super(context, R.layout.maliste, itemname);

	 
	 this.context=context;
	 this.itemname=itemname;
	 this.imgid=imgid;
	 this.desc=description;
	 this.sonid=sonsid;
	 }
	 
	 public View getView(int position,View view,ViewGroup parent) {
	 LayoutInflater inflater=context.getLayoutInflater();
	 View rowView=inflater.inflate(R.layout.maliste, null,true);

	 TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
	 ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
	 TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);
		 ImageButton lecture=rowView.findViewById(R.id.play);
		 ImageButton stop=rowView.findViewById(R.id.pause);
//definition des tags pour listener
		 txtTitle.setTag(position);
		 imageView.setTag(position);
		 extratxt.setTag(position);
		 lecture.setTag(position);
		 stop.setTag(position);
	 
	 txtTitle.setText(itemname[position]);

	 imageView.setImageResource(imgid[position]);
	 extratxt.setText(desc[position]);

	 return rowView;
	 
	 };
	}
