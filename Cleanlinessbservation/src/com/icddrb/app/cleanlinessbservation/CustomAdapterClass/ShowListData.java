package com.icddrb.app.cleanlinessbservation.CustomAdapterClass;

import java.util.ArrayList;



import com.icddrb.app.cleanlinessbservation.BaseActivity;
import com.icddrb.app.cleanlinessbservation.CommonStaticClass;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ListView;

public class ShowListData {
	BaseActivity baseActivity = new BaseActivity();
	public void Listdata(Context con, final View v) {

		ListView lvshowAll;
		lvshowAll = (ListView) v;
		lvshowAll.setAdapter(null);
		ArrayList<ShowAllMedicine> allEmployees = new ArrayList<ShowAllMedicine>();

		String allSQL = "select slno,q2_12,q2_13,q2_14,q2_15 from tblToilet where dataid = '"
				+ CommonStaticClass.dataId + "'";
		Cursor allCursor1 = null;
		try {

			allCursor1 = baseActivity.dbHelper.getQueryCursor(allSQL);

			if (allCursor1 != null && allCursor1.getCount() > 0) {
				allCursor1.moveToFirst();
				for (int i = 0; i < allCursor1.getCount(); i++) {
					//
					int slno = allCursor1.getInt(allCursor1
							.getColumnIndex("slno"));
					String medicine = allCursor1.getString(allCursor1
							.getColumnIndex("q2_12"));
					String f_MedAmount = allCursor1.getString(allCursor1
							.getColumnIndex("q2_13"));
					String f_MedCost = allCursor1.getString(allCursor1
							.getColumnIndex("q2_14"));
					String h_MedAmount = allCursor1.getString(allCursor1
							.getColumnIndex("q2_15"));


					ShowAllMedicine e = new  ShowAllMedicine(slno,  medicine, f_MedAmount,
									 f_MedCost, h_MedAmount);
					allEmployees.add(e);
					allCursor1.moveToNext();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (allCursor1 != null)
				allCursor1.close();
		}

		CustomadapterforMedicine Showadapter;
		if (allEmployees != null && allEmployees.size() > 0) {

			Showadapter = new CustomadapterforMedicine(con, allEmployees);
			lvshowAll.setAdapter(Showadapter);
		}

	}


}
