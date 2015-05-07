package com.icddrb.app.SpecimenCollectionForm.Viewall;

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
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		// we need to tell the adapters, how each item will look
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View retView = inflater.inflate(R.layout.customviewallgrid, parent,
				false);
		return retView;
	}

	@Override
	public void bindView(View convertView, Context context, Cursor cursor) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		String dataid = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(0)));
		String HosCode = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(1)));
		/*String PatientID = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(2)));*/
		String HOSPID = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(2)));
		String PatientTY = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(3)));
		String RegDate = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(4)));
		String RegTime = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(5)));
		String Name = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(6)));
		String BDate = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(7)));
		String Sex = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(8)));
		String AgeYr = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(9)));
		String AgeMo = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(10)));
		String AgeDa = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(11)));
		String BDType = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(12)));
		String FaSpName = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(13)));
		String Ward = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(14)));
		String Area = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(15)));
		String SecBlock = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(16)));

		String Road = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(17)));
		String House = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(18)));
		String HH = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(19)));

		String SL = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(20)));
		String PID = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(21)));
		String Phone = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(22)));
		
		
		
		
		String  WT = cursor.getString(cursor.getColumnIndex("WT"));
		
		String  Vill = cursor.getString(cursor.getColumnIndex("Vill"));

		String   UPZ = cursor.getString(cursor.getColumnIndex("UPZ"));
		
		
		String  Dist  = cursor.getString(cursor.getColumnIndex("Dist"));
		String HT = cursor.getString(cursor.getColumnIndex("HT"));
		String MUAC = cursor.getString(cursor.getColumnIndex("MUAC"));

		
		
		
		
		String DS17 = cursor.getString(cursor.getColumnIndex("DS17"));
		String DS18D = cursor.getString(cursor.getColumnIndex("DS18D"));
		String DS18H = cursor.getString(cursor.getColumnIndex("DS18H"));
		String DS19 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(32)));
		String DS20 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(33)));
		String DS21 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(34)));
		String DS22 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(35)));
		String DS23 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(36)));
		String DS24 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(37)));
		String DS25 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(38)));
		String DS26 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(39)));
		
		String DS26a = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(40)));
		
		String DS26b = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(41)));
		
		String DS27 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(42)));
		String DS28D = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(43)));
		String DS28H = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(44)));
		String DS29 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(45)));
		String DS30 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(46)));
		String DS31 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(47)));
		String DS32D = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(48)));
		String DS32H = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(49)));
		String DS33 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(50)));
		String DS34 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(51)));
		String DS35D = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(52)));
		String DS35H = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(53)));
		String DS36 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(54)));
		String DS37 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(55)));
		String DS38 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(56)));
		String DS39 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(57)));
		String DS40 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(58)));
		String DS41 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(59)));
		String DS42 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(60)));
		String DS43 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(61)));
		String DS44 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(62)));
		String DS45 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(63)));
		String DS46 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(64)));
		String DS47 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(65)));
		String DS48 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(66)));
		String DS49 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(67)));
		String DS50 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(68)));
		String DS51 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(69)));
		String DS52 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(70)));
		String DS53 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(71)));
		String DS54 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(72)));
		String DS55 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(73)));
		String DS56 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(74)));
		String DS57 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(75)));
		String DS58 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(76)));
		String DS59C = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(77)));
		String DS59P = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(78)));
		String DS60 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(79)));
		String DS61 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(80)));
		String DS62 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(81)));
		String DS63 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(82)));
		String DS64 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(83)));
		String DS65 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(84)));
		String DS66 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(85)));
		String DS67 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(86)));
		String DS68 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(87)));
		String DS69 = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(88)));
		String ENTRYBY = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(89)));
		String EntryDate = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(90)));
		String EditBy = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(91)));
		String EditDate = cursor.getString(cursor.getColumnIndex(cursor
				.getColumnName(92)));

		/*
		 * String
		 * birthDate=cursor.getString(cursor.getColumnIndex(cursor.getColumnName
		 * (6))); if(birthDate != null && birthDate != "") { Date date; try {
		 * date = format.parse(birthDate); birthDate = format.format(date); }
		 * catch (ParseException e) { birthDate = "Error"; } }
		 */

		// int start, int top, int end, int bottom end, bottom
		int start = 5;
		int top = 0;
		int end = 5;
		int bottom = 0;

		((TextView) convertView.findViewById(R.id.dataid)).setText(dataid);
		((TextView) convertView.findViewById(R.id.dataid)).setPadding(start,
				top, end, bottom);

		((TextView) convertView.findViewById(R.id.HosCode)).setText(HosCode);
		((TextView) convertView.findViewById(R.id.HosCode)).setPadding(start,
				top, end, bottom);
		//start = 20;
		/*((TextView) convertView.findViewById(R.id.PatientID))
				.setText(PatientID);
		((TextView) convertView.findViewById(R.id.PatientID)).setPadding(start,
				top, end, bottom);*/

		((TextView) convertView.findViewById(R.id.HOSPID)).setText(HOSPID);
		((TextView) convertView.findViewById(R.id.HOSPID)).setPadding(start,
				top, end, bottom);

		((TextView) convertView.findViewById(R.id.PatientTY))
				.setText(PatientTY);
		((TextView) convertView.findViewById(R.id.PatientTY)).setPadding(start,
				top, end, bottom);

		((TextView) convertView.findViewById(R.id.RegDate)).setText(RegDate);
		((TextView) convertView.findViewById(R.id.RegDate)).setPadding(start,
				top, end, bottom);

		((TextView) convertView.findViewById(R.id.RegTime)).setText(RegTime);
		((TextView) convertView.findViewById(R.id.RegTime)).setPadding(start,
				top, end, bottom);

		((TextView) convertView.findViewById(R.id.Name)).setText(Name);
		((TextView) convertView.findViewById(R.id.Name)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.BDate)).setText(BDate);
		((TextView) convertView.findViewById(R.id.BDate)).setPadding(start,
				top, end, bottom);

		((TextView) convertView.findViewById(R.id.Sex)).setText(Sex);
		((TextView) convertView.findViewById(R.id.Sex)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.AgeYr)).setText(AgeYr);
		((TextView) convertView.findViewById(R.id.AgeYr)).setPadding(start,
				top, end, bottom);

		((TextView) convertView.findViewById(R.id.AgeMo)).setText(AgeMo);
		((TextView) convertView.findViewById(R.id.AgeMo)).setPadding(start,
				top, end, bottom);

		((TextView) convertView.findViewById(R.id.AgeDa)).setText(AgeDa);
		((TextView) convertView.findViewById(R.id.AgeDa)).setPadding(start,
				top, end, bottom);

		((TextView) convertView.findViewById(R.id.BDType)).setText(BDType);
		((TextView) convertView.findViewById(R.id.BDType)).setPadding(start,
				top, end, bottom);

		((TextView) convertView.findViewById(R.id.FaSpName)).setText(FaSpName);
		((TextView) convertView.findViewById(R.id.FaSpName)).setPadding(start,
				top, end, bottom);

		((TextView) convertView.findViewById(R.id.Ward)).setText(Ward);
		((TextView) convertView.findViewById(R.id.Ward)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.Area)).setText(Area);
		((TextView) convertView.findViewById(R.id.Area)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.SecBlock)).setText(SecBlock);
		((TextView) convertView.findViewById(R.id.SecBlock)).setPadding(start,
				top, end, bottom);

		((TextView) convertView.findViewById(R.id.Road)).setText(Road);
		((TextView) convertView.findViewById(R.id.Road)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.House)).setText(House);
		((TextView) convertView.findViewById(R.id.House)).setPadding(start,
				top, end, bottom);

		((TextView) convertView.findViewById(R.id.HH)).setText(HH);
		((TextView) convertView.findViewById(R.id.HH)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.SL)).setText(SL);
		((TextView) convertView.findViewById(R.id.SL)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.PID)).setText(PID);
		((TextView) convertView.findViewById(R.id.PID)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.Phone)).setText(Phone);
		((TextView) convertView.findViewById(R.id.Phone)).setPadding(start,
				top, end, bottom);

		((TextView) convertView.findViewById(R.id.Vill)).setText(Vill);
		((TextView) convertView.findViewById(R.id.Vill)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.UPZ)).setText(UPZ);
		((TextView) convertView.findViewById(R.id.UPZ)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.Dist)).setText(Dist);
		((TextView) convertView.findViewById(R.id.Dist)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.WT)).setText(WT);
		((TextView) convertView.findViewById(R.id.WT)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.HT)).setText(HT);
		((TextView) convertView.findViewById(R.id.HT)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.MUAC)).setText(MUAC);
		((TextView) convertView.findViewById(R.id.MUAC)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS17)).setText(DS17);
		((TextView) convertView.findViewById(R.id.DS17)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS18D)).setText(DS18D);
		((TextView) convertView.findViewById(R.id.DS18D)).setPadding(start,
				top, end, bottom);

		((TextView) convertView.findViewById(R.id.DS18H)).setText(DS18H);
		((TextView) convertView.findViewById(R.id.DS18H)).setPadding(start,
				top, end, bottom);

		((TextView) convertView.findViewById(R.id.DS19)).setText(DS19);
		((TextView) convertView.findViewById(R.id.DS19)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS20)).setText(DS20);
		((TextView) convertView.findViewById(R.id.DS20)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS21)).setText(DS21);
		((TextView) convertView.findViewById(R.id.DS21)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS22)).setText(DS22);
		((TextView) convertView.findViewById(R.id.DS22)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS23)).setText(DS23);
		((TextView) convertView.findViewById(R.id.DS23)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS24)).setText(DS24);
		((TextView) convertView.findViewById(R.id.DS24)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS25)).setText(DS25);
		((TextView) convertView.findViewById(R.id.DS25)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS26)).setText(DS26);
		((TextView) convertView.findViewById(R.id.DS26)).setPadding(start, top,
				end, bottom);
		
		
		((TextView) convertView.findViewById(R.id.DS26a)).setText(DS26a);
		((TextView) convertView.findViewById(R.id.DS26a)).setPadding(start, top,
				end, bottom);
		
		
		((TextView) convertView.findViewById(R.id.DS26b)).setText(DS26b);
		((TextView) convertView.findViewById(R.id.DS26b)).setPadding(start, top,
				end, bottom);
		
		((TextView) convertView.findViewById(R.id.DS27)).setText(DS27);
		((TextView) convertView.findViewById(R.id.DS27)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS28D)).setText(DS28D);
		((TextView) convertView.findViewById(R.id.DS28D)).setPadding(start,
				top, end, bottom);

		((TextView) convertView.findViewById(R.id.DS28H)).setText(DS28H);
		((TextView) convertView.findViewById(R.id.DS28H)).setPadding(start,
				top, end, bottom);

		((TextView) convertView.findViewById(R.id.DS29)).setText(DS29);
		((TextView) convertView.findViewById(R.id.DS29)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS30)).setText(DS30);
		((TextView) convertView.findViewById(R.id.DS30)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS31)).setText(DS31);
		((TextView) convertView.findViewById(R.id.DS31)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS32D)).setText(DS32D);
		((TextView) convertView.findViewById(R.id.DS32D)).setPadding(start,
				top, end, bottom);

		((TextView) convertView.findViewById(R.id.DS32H)).setText(DS32H);
		((TextView) convertView.findViewById(R.id.DS32H)).setPadding(start,
				top, end, bottom);

		((TextView) convertView.findViewById(R.id.DS33)).setText(DS33);
		((TextView) convertView.findViewById(R.id.DS33)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS34)).setText(DS34);
		((TextView) convertView.findViewById(R.id.DS34)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS35D)).setText(DS35D);
		((TextView) convertView.findViewById(R.id.DS35D)).setPadding(start,
				top, end, bottom);

		((TextView) convertView.findViewById(R.id.DS35H)).setText(DS35H);
		((TextView) convertView.findViewById(R.id.DS35H)).setPadding(start,
				top, end, bottom);

		((TextView) convertView.findViewById(R.id.DS36)).setText(DS36);
		((TextView) convertView.findViewById(R.id.DS36)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS37)).setText(DS37);
		((TextView) convertView.findViewById(R.id.DS37)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS38)).setText(DS38);
		((TextView) convertView.findViewById(R.id.DS38)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS39)).setText(DS39);
		((TextView) convertView.findViewById(R.id.DS39)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS40)).setText(DS40);
		((TextView) convertView.findViewById(R.id.DS40)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS41)).setText(DS41);
		((TextView) convertView.findViewById(R.id.DS41)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS42)).setText(DS42);
		((TextView) convertView.findViewById(R.id.DS42)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS43)).setText(DS43);
		((TextView) convertView.findViewById(R.id.DS43)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS44)).setText(DS44);
		((TextView) convertView.findViewById(R.id.DS44)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS45)).setText(DS45);
		((TextView) convertView.findViewById(R.id.DS45)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS46)).setText(DS46);
		((TextView) convertView.findViewById(R.id.DS46)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS47)).setText(DS47);
		((TextView) convertView.findViewById(R.id.DS47)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS48)).setText(DS48);
		((TextView) convertView.findViewById(R.id.DS48)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS49)).setText(DS49);
		((TextView) convertView.findViewById(R.id.DS49)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS50)).setText(DS50);
		((TextView) convertView.findViewById(R.id.DS50)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS51)).setText(DS51);
		((TextView) convertView.findViewById(R.id.DS51)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS52)).setText(DS52);
		((TextView) convertView.findViewById(R.id.DS52)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS53)).setText(DS53);
		((TextView) convertView.findViewById(R.id.DS53)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS54)).setText(DS54);
		((TextView) convertView.findViewById(R.id.DS54)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS55)).setText(DS55);
		((TextView) convertView.findViewById(R.id.DS55)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS56)).setText(DS56);
		((TextView) convertView.findViewById(R.id.DS56)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS57)).setText(DS57);
		((TextView) convertView.findViewById(R.id.DS57)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS58)).setText(DS58);
		((TextView) convertView.findViewById(R.id.DS58)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS59C)).setText(DS59C);
		((TextView) convertView.findViewById(R.id.DS59C)).setPadding(start,
				top, end, bottom);

		((TextView) convertView.findViewById(R.id.DS59P)).setText(DS59P);
		((TextView) convertView.findViewById(R.id.DS59P)).setPadding(start,
				top, end, bottom);

		((TextView) convertView.findViewById(R.id.DS60)).setText(DS60);
		((TextView) convertView.findViewById(R.id.DS60)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS61)).setText(DS61);
		((TextView) convertView.findViewById(R.id.DS61)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS62)).setText(DS62);
		((TextView) convertView.findViewById(R.id.DS62)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS63)).setText(DS63);
		((TextView) convertView.findViewById(R.id.DS63)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS64)).setText(DS64);
		((TextView) convertView.findViewById(R.id.DS64)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS65)).setText(DS65);
		((TextView) convertView.findViewById(R.id.DS65)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS66)).setText(DS66);
		((TextView) convertView.findViewById(R.id.DS66)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS67)).setText(DS67);
		((TextView) convertView.findViewById(R.id.DS67)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS68)).setText(DS68);
		((TextView) convertView.findViewById(R.id.DS68)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.DS69)).setText(DS69);
		((TextView) convertView.findViewById(R.id.DS69)).setPadding(start, top,
				end, bottom);

		((TextView) convertView.findViewById(R.id.ENTRYBY)).setText(ENTRYBY);
		((TextView) convertView.findViewById(R.id.ENTRYBY)).setPadding(start,
				top, end, bottom);

		((TextView) convertView.findViewById(R.id.EntryDate))
				.setText(EntryDate);
		((TextView) convertView.findViewById(R.id.EntryDate)).setPadding(start,
				top, end, bottom);

		/*((TextView) convertView.findViewById(R.id.EditBy)).setText(EditBy);
		((TextView) convertView.findViewById(R.id.EditBy)).setPadding(start,
				top, end, bottom);

		((TextView) convertView.findViewById(R.id.EditDate)).setText(EditDate);
		((TextView) convertView.findViewById(R.id.EditDate)).setPadding(start,
				top, end, bottom);*/

	}
}
