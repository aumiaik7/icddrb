package app.prelm;

import java.util.List;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.app.prelm.R;
public class QlistAdapter extends ArrayAdapter<String> {

	boolean bn = false;
	Typeface font ;
	Activity context;
	List<String> dIDlist;
	List<DataItem> datalist;
	public QlistAdapter(Activity context,
			List<String> dIDlist,List<DataItem> datalist) {
		super(context, R.layout.qitem, dIDlist);

		this.context = context;
		this.dIDlist  = dIDlist;
		this.datalist  = datalist;
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = context.getLayoutInflater();
            v = vi.inflate(R.layout.qitem, null);
        }
                TextView tt = (TextView) v.findViewById(R.id.text1);
                TextView bt = (TextView) v.findViewById(R.id.textdesc);
                DataItem dtItem = datalist.get(position);
                if (tt != null) {
                      tt.setText("Data ID: "+dIDlist.get(position)+" Children No: "+dtItem.getChildNo());                            }
                if(bt != null){
					String data = " q6: "+dtItem.getQ6()+
							
							
							" EntryBy: "+dtItem.getEntryBy()+
							" EntryDate: "+dtItem.getEntryDate()+
							" EditBy: "+dtItem.getEditBy()+
							" EditDate: "+dtItem.getEditDate();	
                      bt.setText("Data Collected: "+ data);
                }

        return v;
	}

}
