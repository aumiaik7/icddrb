package com.icddrb.app.ChildIdentificationdb;

import java.util.List;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.icddrb.app.Childidentificationendline.R;
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
					String data = "  numberofchildren  "  + dtItem.getNumberofchildren() +
							 "  q12  "  + dtItem.getQ12()+
							 "  q13  "  +dtItem.getQ13()+
							 "  q14  "  +dtItem.getQ14()+
							 "  q15  "  +dtItem.getQ15()+
							 "  q16  "  +dtItem.getQ16()+
							 "  q17  "  +dtItem.getQ17()+
							 "  q18_days  "  +dtItem.getQ18_days()+
							 "  q18_months  "  +dtItem.getQ18_months()+
							 "  q18  "  +dtItem.getQ18()+
							 "  q19_no_of_time  "  +dtItem.getQ19_no_of_time()+
							 "  q19  "  +dtItem.getQ19()+
							 "  q20_time1  "  +dtItem.getQ20_time1()+
							 "  q20_other_time1  "  +dtItem.getQ20_other_time1()+
							 "  q21_days_time1  "  +dtItem.getQ21_days_time1()+
							 "  q21_dontknow_time1  "  +dtItem.getQ21_dontknow_time1()+
							 "  q20_time2  "  +dtItem.getQ20_time2()+
							 "  q20_other_time2  "  +dtItem.getQ20_other_time2()+
							 "  q21_days_time2  "  +dtItem.getQ21_days_time2()+
							 "  q21_dontknow_time2  "  +dtItem.getQ21_dontknow_time2()+
							 "  q20_time3  "  +dtItem.getQ20_time3()+
							 "  q20_other_time3  "  +dtItem.getQ20_other_time3()+
							 "  q21_days_time3  "  +dtItem.getQ21_days_time3()+
							 "  q21_dontknow_time3  "  +dtItem.getQ21_dontknow_time3()+
							 "  q20_time4  "  +dtItem.getQ20_time4()+
							 "  q20_other_time4  "  +dtItem.getQ20_other_time4()+
							 "  q21_days_time4  "  +dtItem.getQ21_days_time4()+
							 "  q21_dontknow_time4  "  +dtItem.getQ21_dontknow_time4()+
							 "  q20_time5  "  +dtItem.getQ20_time5()+
							 "  q20_other_time5  "  +dtItem.getQ20_other_time5()+
							 "  q21_days_time5  "  +dtItem.getQ21_days_time5()+
							 "  q21_dontknow_time5  "  +dtItem.getQ21_dontknow_time5()+
							 "  q22  "  +dtItem.getQ22()+
							 "  q23  "  +dtItem.getQ23()+
							 "  q24  "  +dtItem.getQ24()+
							 "  q25  "  +dtItem.getQ25()+
							 "  q26_days  "  +dtItem.getQ26_days()+
							 "  q26_months  "  +dtItem.getQ26_months()+
							 "  q26  "  +dtItem.getQ26()+
							 "  q27  "  +dtItem.getQ27()+
							 "  q27_other  "  +dtItem.getQ27_other()+
							 "  q28  "  +dtItem.getQ28()+
							 "  q29  "  +dtItem.getQ29()+
														
							" EntryBy: "+dtItem.getEntryBy()+
							" EntryDate: "+dtItem.getEntryDate()+
							" EditBy: "+dtItem.getEditBy()+
							" EditDate: "+dtItem.getEditDate();	
                      bt.setText("Data Collected: "+ data);
                }

        return v;
	}

}
