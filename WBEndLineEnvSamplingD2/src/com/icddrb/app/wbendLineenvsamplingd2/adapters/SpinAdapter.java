package com.icddrb.app.wbendLineenvsamplingd2.adapters;

import java.util.List;

import android.app.Activity;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.icddrb.app.wbendLineenvsamplingd2.R;

public class SpinAdapter extends ArrayAdapter<String> {

	boolean bn = false;
	Typeface font ;
	Activity context;
	List<String> list;
	public SpinAdapter(Activity context,
			List<String> objects,boolean bn) {
		super(context, R.layout.spinitem, objects);
		font = Typeface.createFromAsset(context.getAssets(),
		"Siyam Rupali ANSI.ttf");
		this.bn = bn;
		this.context = context;
		this.list  = objects;
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater=context.getLayoutInflater();
		View row=inflater.inflate(R.layout.spinitem, null);
		TextView label=(TextView)row.findViewById(R.id.text1);
		
		if(bn){
			Log.e("pos:",position+"");
			label.setTypeface(font);
		}
		
		label.setText(list.get(position));
		return row;
	}

}
