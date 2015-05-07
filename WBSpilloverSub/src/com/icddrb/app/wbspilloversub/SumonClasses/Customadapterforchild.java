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

public class Customadapterforchild extends ArrayAdapter<Showallchild> {
	Activity con;
	ArrayList<Showallchild> employeeList;
	private Context context;

	public Customadapterforchild(Context context,
		ArrayList<Showallchild> employees) {
	super(context, R.layout.list_childinformation, employees);
	this.context = context;
	this.employeeList = employees;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	LayoutInflater inflater = (LayoutInflater) context
	    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	View rowView = inflater.inflate(R.layout.list_childinformation, parent, false);
	TextView txtid = (TextView) rowView.findViewById(R.id.txtid);
	TextView txtname = (TextView) rowView.findViewById(R.id.txtname);
	TextView txtdob = (TextView) rowView.findViewById(R.id.txtdob);
	TextView txtage = (TextView) rowView.findViewById(R.id.txtage);
	TextView txtsex = (TextView) rowView.findViewById(R.id.txtsex);
	TextView txtsourcedob = (TextView) rowView.findViewById(R.id.txtsourcedob);
	TextView txtbirthorder = (TextView) rowView.findViewById(R.id.txtbirthorder);
	TextView txtspilloverchild = (TextView) rowView.findViewById(R.id.txtspilloverchild);
	TextView txtspillovermochild = (TextView) rowView.findViewById(R.id.txtspillovermochild);

	Showallchild e=employeeList.get(position);

	txtid.setText("ID: " + String.valueOf(e.getId()));
	txtname.setText("Name: "+String.valueOf(e.getName()));
	txtdob.setText("DoB: "+String.valueOf(e.getBdate()));
	txtage.setText("Age(Month): "+String.valueOf(e.getMage()));

	txtsex.setText("Sex: " + String.valueOf(e.getSex()));
	txtsourcedob.setText("Dob Source: "+String.valueOf(e.getSourcedob()));
	txtbirthorder.setText("Birth order: "+String.valueOf(e.getBirthorder()));
	txtspilloverchild.setText("Is spillover: "+String.valueOf(e.getSpilloverchild()));
	txtspillovermochild.setText("Spillover Mother's: "+String.valueOf(e.getSpillovermochild()));
	return rowView;
	}
	
	
	
	
}
