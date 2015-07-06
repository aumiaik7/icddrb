package app.stoolsampleendline;

import java.util.List;

import com.app.stoolsampleendline.R;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
					String data = " q5: "+dtItem.getQ5()+
							" q6: "+dtItem.getQ6()+
							" q7s1: "+dtItem.getQ7s1()+
							" q7s2: "+dtItem.getQ7s2()+
							" q7s3: "+dtItem.getQ7s3()+
							" q7s4: "+dtItem.getQ7s4()+
							" q7s5: "+dtItem.getQ7s5()+
							" q8s1: "+dtItem.getQ8s1()+
							" q8s2: "+dtItem.getQ8s2()+
							" q8s3: "+dtItem.getQ8s3()+
							" q8s4: "+dtItem.getQ8s4()+
							" q8s5: "+dtItem.getQ8s5()+
							
							" q10s1: "+dtItem.getQ10s1()+
							" q10s2: "+dtItem.getQ10s2()+
							" q10s3: "+dtItem.getQ10s3()+
							" q10s4: "+dtItem.getQ10s4()+
							" q10s5: "+dtItem.getQ10s5()+
						
							" q11: "+dtItem.getQ11()+
							" q11_Other: "+dtItem.getQ11_other()+
							" q12: "+dtItem.getQ12()+
							" q13: "+dtItem.getQ13()+
							" q14: "+dtItem.getQ14()+
							" q15: "+dtItem.getQ15()+
							" q16: "+dtItem.getQ16()+
							" q17: "+dtItem.getQ17()+
							" q17_other: "+dtItem.getQ17_other()+
							
							" q18: "+dtItem.getQ18()+
							" q18_other: "+dtItem.getQ18_other()+
							
							" q19: "+dtItem.getQ19()+
							" q20: "+dtItem.getQ20()+
							" q21: "+dtItem.getQ21()+
							" q22: "+dtItem.getQ22()+
							" q23: "+dtItem.getQ23()+
							" q23_other: "+dtItem.getQ23_other()+
							
							" EntryBy: "+dtItem.getEntryBy()+
							" EntryDate: "+dtItem.getEntryDate()+
							" EditBy: "+dtItem.getEditBy()+
							" EditDate: "+dtItem.getEditDate();	
                      bt.setText("Data Collected: "+ data);
                }

        return v;
	}

}
