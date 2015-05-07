package com.icddrb.app.SpecimenCollectionForm.Search;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.icddrb.app.SpecimenCollectionForm.R;
public class CustomCursorAdapter extends CursorAdapter {
	public CustomCursorAdapter(Context context, Cursor c) {
		super(context, c);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) { // when
																			// the
																			// view
																			// will
																			// be
																			// created
																			// for
																			// first
																			// time,
		// we need to tell the adapters, how each item will look
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View retView = inflater.inflate(R.layout.customgrid, parent, false);
		return retView;
	}

	
	@Override
	public void bindView(View convertView, Context context, Cursor cursor) 
	{ 	
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		String pid=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0)));
		String hh=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1)));
		String slno=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2)));
		String name=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3)));
		String sex=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(4)));
		String age=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(5)));
		
		
		
		String birthDate=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(6)));
		/*if(birthDate != null && birthDate != "")
		{
			Date date;
			try {
				date = format.parse(birthDate);
				birthDate = format.format(date);
			} catch (ParseException e) {
				birthDate = "Error";
			}  					
		}*/
		
		String rth=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(7)));
		String ms =cursor.getString(cursor.getColumnIndex(cursor.getColumnName(8)));
		String area=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(9)));
		String secBlock=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(10)));
		String road=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(11)));
		String house=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(12)));
		String dist=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(13)));
		String upz=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(14)));
		String vill=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(15)));
		String phone1=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(16)));
		String phone2=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(17)));
		String phone3=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(18)));
		String phone4=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(19)));
		/*if(phone1.length()>5)
			phone1 = "0"+phone1;
			
		if(phone2.length()>5)
			phone2 = "0"+phone2;
		
		if(phone3.length()>5)
			phone3 = "0"+phone3;
		
		if(phone4.length()>5)
			phone4 = "0"+phone4;*/
		
		String gsiId=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(20)));
		String ward=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(21)));
		String clust=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(22)));
		String block=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(23)));
		String faSize=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(24)));
		String enDate=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(25)));
		
		/*if(enDate != null && enDate != "")
		{
			Date date;
			try {
				date = format.parse(enDate);
				enDate = format.format(date);
			} catch (ParseException e) {
				enDate = "Error";
			}  					
		}*/
		
		String motherName=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(26)));
		String fatherName=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(27)));
		String headName=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(28)));
		String spouseName=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(29)));
		/*String ucode=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(30)));
		String unionName=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(31)));
		String villK=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(32)));
		String vpart=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(33)));
		String villName=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(34)));
		String mohalla=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(35)));
		String bari=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(36)));
		String khana=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(37)));
		String project=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(38)));
		String location=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(39)));*/

		
		//int start, int top, int end, int bottom end, bottom
		int start = 5;
		int top = 0;
		int end  = 5;
		int bottom = 0;
		
		((TextView) convertView.findViewById(R.id.PID)).setText(pid);
		((TextView) convertView.findViewById(R.id.PID)).setPadding(start, top, end, bottom);

		((TextView) convertView.findViewById(R.id.Hh)).setText(hh);
		((TextView) convertView.findViewById(R.id.Hh)).setPadding(start, top, end, bottom);

		((TextView) convertView.findViewById(R.id.Slno)).setText(slno);
		((TextView) convertView.findViewById(R.id.Slno)).setPadding(start, top, end, bottom);
		
		((TextView) convertView.findViewById(R.id.Name)).setText(name);
		((TextView) convertView.findViewById(R.id.Name)).setPadding(start, top, end, bottom);
		
		((TextView) convertView.findViewById(R.id.Sex)).setText(sex);
		((TextView) convertView.findViewById(R.id.Sex)).setPadding(start, top, end, bottom);

		((TextView) convertView.findViewById(R.id.Age)).setText(age);
		((TextView) convertView.findViewById(R.id.Age)).setPadding(start, top, end, bottom);

		((TextView) convertView.findViewById(R.id.BDate)).setText(birthDate);
		((TextView) convertView.findViewById(R.id.BDate)).setPadding(start, top, end, bottom);

		((TextView) convertView.findViewById(R.id.Rth)).setText(rth);
		((TextView) convertView.findViewById(R.id.Rth)).setPadding(start, top, end, bottom);

		((TextView) convertView.findViewById(R.id.MS)).setText(ms);
		((TextView) convertView.findViewById(R.id.MS)).setPadding(start, top, end, bottom);
		
		((TextView) convertView.findViewById(R.id.Area)).setText(area);		
		((TextView) convertView.findViewById(R.id.Area)).setPadding(start, top, end, bottom);
		
		((TextView) convertView.findViewById(R.id.SecBlock)).setText(secBlock);		
		((TextView) convertView.findViewById(R.id.SecBlock)).setPadding(start, top, end, bottom);
		
		((TextView) convertView.findViewById(R.id.Road)).setText(road);
		((TextView) convertView.findViewById(R.id.Road)).setPadding(start, top, end, bottom);
		
		((TextView) convertView.findViewById(R.id.House)).setText(house);
		((TextView) convertView.findViewById(R.id.House)).setPadding(start, top, end, bottom);
		
		((TextView) convertView.findViewById(R.id.Dist)).setText(dist);
		((TextView) convertView.findViewById(R.id.Dist)).setPadding(start, top, end, bottom);
		
		((TextView) convertView.findViewById(R.id.UPZ)).setText(upz);
		((TextView) convertView.findViewById(R.id.UPZ)).setPadding(start, top, end, bottom);
		
		((TextView) convertView.findViewById(R.id.Vill)).setText(vill);
		((TextView) convertView.findViewById(R.id.Vill)).setPadding(start, top, end, bottom);
		
		((TextView) convertView.findViewById(R.id.Phone1)).setText(phone1);
		((TextView) convertView.findViewById(R.id.Phone1)).setPadding(start, top, end, bottom);
		
		((TextView) convertView.findViewById(R.id.Phone2)).setText(phone2);
		((TextView) convertView.findViewById(R.id.Phone2)).setPadding(start, top, end, bottom);
		
		((TextView) convertView.findViewById(R.id.Phone3)).setText(phone3);
		((TextView) convertView.findViewById(R.id.Phone3)).setPadding(start, top, end, bottom);
		
		((TextView) convertView.findViewById(R.id.Phone4)).setText(phone4);
		((TextView) convertView.findViewById(R.id.Phone4)).setPadding(start, top, end, bottom);
		
		((TextView) convertView.findViewById(R.id.GISID)).setText(gsiId);
		((TextView) convertView.findViewById(R.id.GISID)).setPadding(start, top, end, bottom);
		
		((TextView) convertView.findViewById(R.id.Ward)).setText(ward);
		((TextView) convertView.findViewById(R.id.Ward)).setPadding(start, top, end, bottom);
		
		((TextView) convertView.findViewById(R.id.Clust)).setText(clust);
		((TextView) convertView.findViewById(R.id.Clust)).setPadding(start, top, end, bottom);
		
		((TextView) convertView.findViewById(R.id.Block)).setText(block);
		((TextView) convertView.findViewById(R.id.Block)).setPadding(start, top, end, bottom);
		
		((TextView) convertView.findViewById(R.id.FaSize)).setText(faSize);
		((TextView) convertView.findViewById(R.id.FaSize)).setPadding(start, top, end, bottom);
		
		((TextView) convertView.findViewById(R.id.EnDate)).setText(enDate);
		((TextView) convertView.findViewById(R.id.EnDate)).setPadding(start, top, end, bottom);
		
		((TextView) convertView.findViewById(R.id.MotherName)).setText(motherName);
		((TextView) convertView.findViewById(R.id.MotherName)).setPadding(start, top, end, bottom);
		
		((TextView) convertView.findViewById(R.id.FatherName)).setText(fatherName);
		((TextView) convertView.findViewById(R.id.FatherName)).setPadding(start, top, end, bottom);
		
		((TextView) convertView.findViewById(R.id.HeadName)).setText(headName);
		((TextView) convertView.findViewById(R.id.HeadName)).setPadding(start, top, end, bottom);
		((TextView) convertView.findViewById(R.id.SpouseName)).setText(spouseName);
		((TextView) convertView.findViewById(R.id.SpouseName)).setPadding(start, top, end, bottom);
		
		/*((TextView) convertView.findViewById(R.id.Ucode)).setText(ucode);
		((TextView) convertView.findViewById(R.id.Ucode)).setPadding(start, top, end, bottom);
		
		((TextView) convertView.findViewById(R.id.UnionName)).setText(unionName);
		((TextView) convertView.findViewById(R.id.UnionName)).setPadding(start, top, end, bottom);
		
		((TextView) convertView.findViewById(R.id.VillK)).setText(villK);
		((TextView) convertView.findViewById(R.id.VillK)).setPadding(start, top, end, bottom);
		
		((TextView) convertView.findViewById(R.id.Vpart)).setText(vpart);
		((TextView) convertView.findViewById(R.id.Vpart)).setPadding(start, top, end, bottom);
		
		((TextView) convertView.findViewById(R.id.VillName)).setText(villName);
		((TextView) convertView.findViewById(R.id.VillName)).setPadding(start, top, end, bottom);
		
		((TextView) convertView.findViewById(R.id.Mohalla)).setText(mohalla);
		((TextView) convertView.findViewById(R.id.Mohalla)).setPadding(start, top, end, bottom);
		
		((TextView) convertView.findViewById(R.id.Bari)).setText(bari);
		((TextView) convertView.findViewById(R.id.Bari)).setPadding(start, top, end, bottom);
		
		((TextView) convertView.findViewById(R.id.Khana)).setText(khana);
		((TextView) convertView.findViewById(R.id.Khana)).setPadding(start, top, end, bottom);
		
		((TextView) convertView.findViewById(R.id.Project)).setText(project);
		((TextView) convertView.findViewById(R.id.Project)).setPadding(start, top, end, bottom);
		
		((TextView) convertView.findViewById(R.id.Location)).setText(location);
		((TextView) convertView.findViewById(R.id.Location)).setPadding(start, top, end, bottom);*/
		
	}
}
