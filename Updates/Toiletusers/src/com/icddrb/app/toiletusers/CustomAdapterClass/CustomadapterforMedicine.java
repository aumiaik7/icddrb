package com.icddrb.app.toiletusers.CustomAdapterClass;

import java.util.ArrayList;

import com.icddrb.app.toiletusers.R;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class CustomadapterforMedicine extends ArrayAdapter<ShowAllMedicine> {
	Activity con;
	ArrayList<ShowAllMedicine> employeeList;
	private Context context;

	public CustomadapterforMedicine(Context context,
			ArrayList<ShowAllMedicine> employees) {
		super(context, R.layout.list_frmmedicine, employees);
		this.context = context;
		this.employeeList = employees;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(R.layout.list_frmmedicine, parent,
				false);
		TextView txtansChamber = (TextView) rowView
				.findViewById(R.id.txtansChamber);
		TextView txtans2_12 = (TextView) rowView.findViewById(R.id.txtans2_12);
		TextView txtans2_13 = (TextView) rowView.findViewById(R.id.txtans2_13);
		TextView txtans2_14 = (TextView) rowView.findViewById(R.id.txtans2_14);
		TextView txtans2_15 = (TextView) rowView.findViewById(R.id.txtans2_15);
		AssetManager assetMgr = getContext().getAssets();
		Typeface font1 = Typeface.createFromAsset(this.context.getAssets(),
				"Siyam Rupali ANSI.ttf");

		txtansChamber.setTypeface(font1);
		txtans2_12.setTypeface(font1);
		txtans2_13.setTypeface(font1);
		txtans2_14.setTypeface(font1);
		txtans2_15.setTypeface(font1);

		ShowAllMedicine e = employeeList.get(position);
		txtansChamber.setText(String.valueOf(txtansChamber.getText().toString()
				+ e.getSlno()));
		if (e.getQ2_12() != null) {
			if (e.getQ2_12().equalsIgnoreCase("0")) {
				txtans2_12
						.setText(String
								.valueOf(txtans2_12.getText().toString()
										+ "†MvcbxqZv eRvq ivLvi †Kvb e¨e¯’v wQj bv (cvqLvbvi Af¨š�?‡ii RvqMvwU evB‡i †_‡K `„wó‡MvPi Kivi †Kvb e¨e¯’v wQj bv)"));
			} else if (e.getQ2_12().equalsIgnoreCase("1")) {
				txtans2_12
						.setText(String
								.valueOf(txtans2_12.getText().toString()
										+ "†MvcbxqZv eRvq ivLvi e¨e¯’vwU Kg cwigv‡b wQ‡jv/AvswkK wQj (`iRv wQj, wKš�?– wQUwKwb wQj bv)"));

			} else if (e.getQ2_12().equalsIgnoreCase("2")) {
				txtans2_12
						.setText(String
								.valueOf(txtans2_12.getText().toString()
										+ "†MvcbxqZv eRvq ivLvi e¨e¯’vwU wbwðZ wQj (`iRv wQj Ges wQUwKwb I Kvh©Kix wQj)"));

			} else if (e.getQ2_12().equalsIgnoreCase("3")) {
				txtans2_12
						.setText(String
								.valueOf(txtans2_12.getText().toString()
										+ "†MvcbxqZv eRvq ivLvi e¨e¯’vwU AZ¨vš�? my`„p wQj (`iRvq wQUwKwb wQj Ges evB‡i †_‡K †evSv hvw�?Q‡jv †h †fZ‡i GKRb e¨enviKvix Av‡Q)"));

			}
		}

		if (e.getQ2_13() != null) {
			if (e.getQ2_13().equalsIgnoreCase("0")) {
				txtans2_13.setText(String.valueOf(txtans2_13.getText()
						.toString() + "bv"));
			} else if (e.getQ2_13().equalsIgnoreCase("1")) {
				txtans2_13.setText(String.valueOf(txtans2_13.getText()
						.toString() + "nu¨v "));
			}
		}
		if (e.getQ2_14() != null) {
			if (e.getQ2_14().equalsIgnoreCase("0")) {
				txtans2_14.setText(String.valueOf(txtans2_14.getText()
						.toString() + "bv"));
			} else if (e.getQ2_14().equalsIgnoreCase("1")) {
				txtans2_14.setText(String.valueOf(txtans2_14.getText()
						.toString() + "nu¨v "));
			}
		}
		if (e.getQ2_15() != null) {
			if (e.getQ2_15().equalsIgnoreCase("0")) {
				txtans2_15.setText(String.valueOf(txtans2_15.getText()
						.toString() + "bv"));
			} else if (e.getQ2_15().equalsIgnoreCase("1")) {
				txtans2_15.setText(String.valueOf(txtans2_15.getText()
						.toString() + "nu¨v "));
			}
		}

		return rowView;
	}
}
