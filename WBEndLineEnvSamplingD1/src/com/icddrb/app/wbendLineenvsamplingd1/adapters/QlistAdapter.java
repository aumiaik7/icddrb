package com.icddrb.app.wbendLineenvsamplingd1.adapters;

import java.util.List;

import android.app.Activity;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.icddrb.app.wbendLineenvsamplingd1.CommonStaticClass;
import com.icddrb.app.wbendLineenvsamplingd1.R;

public class QlistAdapter extends ArrayAdapter<String> {

	boolean bn = false;
	Typeface font ;
	Activity context;
	List<String> qlist;
	List<String> qdescbng;
	List<String> qdesceng;
	public QlistAdapter(Activity context,
			List<String> qobjects,List<String> objectsbng,List<String> objectseng) {
		super(context, R.layout.qitem, qobjects);
		font = Typeface.createFromAsset(context.getAssets(),
		"Siyam Rupali ANSI.ttf");
		this.bn = CommonStaticClass.langBng;
		this.context = context;
		this.qlist  = qobjects;
		this.qdescbng  = objectsbng;
		this.qdesceng  = objectseng;
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater=context.getLayoutInflater();
		View row=inflater.inflate(R.layout.qitem, null);
		TextView label=(TextView)row.findViewById(R.id.text1);
		TextView labeldesc=(TextView)row.findViewById(R.id.textdesc);
		
		if(bn && qdescbng.get(position).length()>0){
			Log.e("pos:",position+"");
			labeldesc.setTypeface(font);
			labeldesc.setText(qdescbng.get(position));
		}else{
			labeldesc.setText(qdesceng.get(position));
		}
		
		label.setText(qlist.get(position));
		
		return row;
	}

}
