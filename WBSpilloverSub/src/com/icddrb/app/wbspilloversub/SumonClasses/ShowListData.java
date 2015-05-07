package com.icddrb.app.wbspilloversub.SumonClasses;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.icddrb.app.wbspilloversub.BaseActivity;
import com.icddrb.app.wbspilloversub.CommonStaticClass;
import com.icddrb.app.wbspilloversub.R;

public class ShowListData {

	BaseActivity baseActivity = new BaseActivity();

	public void ListdataforParticipant(Context con, final View v) {

		ListView lvshowAll;
		lvshowAll = (ListView) v;
		lvshowAll.setAdapter(null);
		ArrayList<Showallhhcensus> allEmployees = new ArrayList<Showallhhcensus>();
		// ArrayList<ShowallDiscUpozila> getAllEmployees;

		String allSQL = "select hhno,a1,a2,a3 from tblHHCensus where dataid = '"
				+ CommonStaticClass.dataId + "' ORDER BY CAST(hhno AS INTEGER)";
		Cursor allCursor1 = null;
		try {

			allCursor1 = baseActivity.dbHelper.getQueryCursor(allSQL);

			if (allCursor1 != null && allCursor1.getCount() > 0) {
				allCursor1.moveToFirst();
				for (int i = 0; i < allCursor1.getCount(); i++) {
					//
					String hhno = allCursor1.getString(allCursor1
							.getColumnIndex("hhno"));
					String a1 = allCursor1.getString(allCursor1
							.getColumnIndex("a1"));
					String a2 = allCursor1.getString(allCursor1
							.getColumnIndex("a2"));
					String a3 = allCursor1.getString(allCursor1
							.getColumnIndex("a3"));

					Showallhhcensus e = new Showallhhcensus(hhno, a1, a2, a3);
					allEmployees.add(e);
					allCursor1.moveToNext();
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (allCursor1 != null)
				allCursor1.close();
		}

		Customadapterforhhcensus Showadapter;
		// CustomadapterforDistUpzila Showadapter;
		if (allEmployees != null && allEmployees.size() > 0) {

			// Showadapter = new CustomadapterforDistUpzila(thisactivity,
			// dbHelper.getQueryCursor(allSQL));
			Showadapter = new Customadapterforhhcensus(con, allEmployees);
			lvshowAll.setAdapter(Showadapter);
		}

	}

	public void Listdataforchildinformation(Context con, final View v) {

		ListView lvshowAll;
		lvshowAll = (ListView) v;
		lvshowAll.setAdapter(null);
		ArrayList<Showallchild> allEmployees = new ArrayList<Showallchild>();
		// ArrayList<ShowallDiscUpozila> getAllEmployees;

		String allSQL = "select childno,Name,Sex,AgeMonth,dob,sourcedob,birthorder,spilloverchild,spillovermochild from tblChildInformation where dataid = '"
				+ CommonStaticClass.dataId + "' ORDER BY CAST(childno AS INTEGER)";
		Cursor allCursor1 = null;
		try {

			allCursor1 = baseActivity.dbHelper.getQueryCursor(allSQL);

			if (allCursor1 != null && allCursor1.getCount() > 0) {
				allCursor1.moveToFirst();
				for (int i = 0; i < allCursor1.getCount(); i++) {
					//
					String childno = allCursor1.getString(allCursor1
							.getColumnIndex("childno"));
					String Name = allCursor1.getString(allCursor1
							.getColumnIndex("Name"));
					String Sex = allCursor1.getString(allCursor1
							.getColumnIndex("Sex"));
					String AgeMonth = allCursor1.getString(allCursor1
							.getColumnIndex("AgeMonth"));
					String dob = allCursor1.getString(allCursor1
							.getColumnIndex("dob"));
					String sourcedob = allCursor1.getString(allCursor1
							.getColumnIndex("sourcedob"));
					String birthorder = allCursor1.getString(allCursor1
							.getColumnIndex("birthorder"));
					String spilloverchild = allCursor1.getString(allCursor1
							.getColumnIndex("spilloverchild"));
					String spillovermochild = allCursor1.getString(allCursor1
							.getColumnIndex("spillovermochild"));
				
					Showallchild e = new Showallchild(childno, Name, Sex, AgeMonth,dob,sourcedob,birthorder,spilloverchild,spillovermochild);
					allEmployees.add(e);
					allCursor1.moveToNext();
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (allCursor1 != null)
				allCursor1.close();
		}

		Customadapterforchild Showadapter;
		// CustomadapterforDistUpzila Showadapter;
		if (allEmployees != null && allEmployees.size() > 0) {

			// Showadapter = new CustomadapterforDistUpzila(thisactivity,
			// dbHelper.getQueryCursor(allSQL));
			Showadapter = new Customadapterforchild(con, allEmployees);
			lvshowAll.setAdapter(Showadapter);
		}

	}


}
