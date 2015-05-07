package com.icddrb.app.wbspilloversub.SumonClasses;


import java.util.ArrayList;

import com.icddrb.app.wbspilloversub.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Customadapterforhhcensus extends
ArrayAdapter<Showallhhcensus> {
Activity con;
ArrayList<Showallhhcensus> employeeList;
private Context context;

public Customadapterforhhcensus(Context context,
	ArrayList<Showallhhcensus> employees) {
super(context, R.layout.list_hhcensus, employees);
this.context = context;
this.employeeList = employees;
}
@Override
public View getView(int position, View convertView, ViewGroup parent) {
LayoutInflater inflater = (LayoutInflater) context
    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
View rowView = inflater.inflate(R.layout.list_hhcensus, parent, false);
TextView txtID = (TextView) rowView.findViewById(R.id.txtID);
TextView txta1 = (TextView) rowView.findViewById(R.id.txta1);
TextView txta2 = (TextView) rowView.findViewById(R.id.txta2);
TextView txta3 = (TextView) rowView.findViewById(R.id.txta3);

Showallhhcensus e=employeeList.get(position);

txtID.setText("ID: " + String.valueOf(e.getId()));
txta1.setText("A.1: "+String.valueOf(e.getA1()));
txta2.setText("A.2: "+String.valueOf(e.getA2()));
txta3.setText("A.3: "+String.valueOf(e.getA3()));

return rowView;
}
}
