package com.icddrb.app.wbendLineenvsamplingd1.questions;

import android.database.Cursor;

import com.icddrb.app.wbendLineenvsamplingd1.BaseActivity;
import com.icddrb.app.wbendLineenvsamplingd1.CommonStaticClass;
import com.icddrb.app.wbendLineenvsamplingd1.db.DatabaseHelper;

public class custom_class extends BaseActivity {

	private int q612_1;
	private int q612_2;
	private int q612_3;
	private int q612_4;
	private int q612_5;
	private int q612_6;

	private int q612_2_day;
	private int q612_3_day;
	private int q612_4_day;
	private int q612_5_day;
	private int q612_6_day;

	private String q612_1_other;
	private String q612_3_other;
	private String q612_4_other;
	private String q612_6_other;

	public custom_class() {
		q612_2_day = -1;
		q612_3_day = -1;
		q612_4_day = -1;
		q612_5 = -1;
		q612_6_day = -1;

		q612_1_other = "";
		q612_3_other = "";
		q612_4_other = "";
		q612_6_other = "";

	}

	private int q612_1_day;

	public int getQ612_1_day() {
		return q612_1_day;
	}

	public void setQ612_1_day(int q612_1_day) {
		this.q612_1_day = q612_1_day;
	}

	public int getQ612_2_day() {
		return q612_2_day;
	}

	public void setQ612_2_day(int q612_2_day) {
		this.q612_2_day = q612_2_day;
	}

	public int getQ612_3_day() {
		return q612_3_day;
	}

	public void setQ612_3_day(int q612_3_day) {
		this.q612_3_day = q612_3_day;
	}

	public int getQ612_4_day() {
		return q612_4_day;
	}

	public void setQ612_4_day(int q612_4_day) {
		this.q612_4_day = q612_4_day;
	}

	public int getQ612_6_day() {
		return q612_6_day;
	}

	public void setQ612_6_day(int q612_6_day) {
		this.q612_6_day = q612_6_day;
	}

	public String getq612_1_other() {
		return q612_1_other;
	}

	public void setq612_1_other(String q612_1_other) {
		this.q612_1_other = q612_1_other;
	}

	public String getq612_3_other() {
		return q612_3_other;
	}

	public void setq612_3_other(String q612_3_other) {
		this.q612_3_other = q612_3_other;
	}

	public String getC612_4_other() {
		return q612_4_other;
	}

	public void setq612_4_other(String q612_4_other) {
		this.q612_4_other = q612_4_other;
	}

	public String getq612_6_other() {
		return q612_6_other;
	}

	public void setq612_6_other(String q612_6_other) {
		this.q612_6_other = q612_6_other;
	}

	public Boolean save(custom_class cust) {
		String sql = String
				.format("UPDATE tblMainQues SET c612_1= '%s', c612_2='%s', c612_3='%s', c612_4='%s', c612_5='%s', c612_6='%s', " +
						"c612_1_day= '%s', c612_2_day='%s', c612_3_day='%s', c612_4_day='%s', c612_6_day='%s',"
						+ "c612_1_other='%s', c612_3_other='%s', c612_4_other='%s', c612_6_other='%s' WHERE dataid ='%s'",
						cust.q612_1, cust.q612_2, cust.q612_3,
						cust.q612_4, cust.q612_5, cust.q612_6,
						cust.q612_1_day, cust.q612_2_day, cust.q612_3_day,
						cust.q612_4_day,  cust.q612_6_day, cust.q612_1_other,
						cust.q612_3_other, cust.q612_4_other,
						cust.q612_6_other, CommonStaticClass.dataId);
		if (DatabaseHelper.dbHelper.executeDMLQuery(sql)) {
			return true;
		}
		return false;

	}

	public custom_class GetRecords(DatabaseHelper dhelper) {
		custom_class c = new custom_class();
		String sql = "";
		sql = "Select c612_1, c612_2, c612_3,c612_4,c612_6," +
				"c612_1_day, c612_2_day, c612_3_day,c612_4_day, c612_5, c612_6_day," +
				"c612_1_other,c612_3_other,c612_4_other,c612_6_other from "
				+ CommonStaticClass.questionMap.get(
						CommonStaticClass.currentSLNo).getTablename()
				+ " where dataid='" + CommonStaticClass.dataId + "'";

		Cursor mCursor1 = null;
		try {
			
			mCursor1 = dhelper.getQueryCursor(sql);
			
			if (mCursor1.getCount() > 0) {

				if (mCursor1.moveToFirst()) {
					do 
					{
						c.setQ612_1(mCursor1.getInt(mCursor1.getColumnIndex("c612_1"))==-1 ? -1 : mCursor1.getInt(mCursor1.getColumnIndex("c612_1")));
						c.setQ612_2(mCursor1.getInt(mCursor1.getColumnIndex("c612_2"))==-1 ? -1 : mCursor1.getInt(mCursor1.getColumnIndex("c612_2")));
						c.setQ612_3(mCursor1.getInt(mCursor1.getColumnIndex("c612_3"))==-1 ? -1 : mCursor1.getInt(mCursor1.getColumnIndex("c612_3")));
						c.setQ612_4(mCursor1.getInt(mCursor1.getColumnIndex("c612_4"))==-1 ? -1 : mCursor1.getInt(mCursor1.getColumnIndex("c612_4")));
						c.setQ612_5(mCursor1.getInt(mCursor1.getColumnIndex("c612_5"))==-1 ? -1 : mCursor1.getInt(mCursor1.getColumnIndex("c612_5")));
						
						c.setQ612_6(mCursor1.getInt(mCursor1.getColumnIndex("c612_6"))==-1 ? -1 : mCursor1.getInt(mCursor1.getColumnIndex("c612_6")));
						
						c.setQ612_1_day(mCursor1.getInt(mCursor1.getColumnIndex("c612_1_day"))==-1 ? -1 : mCursor1.getInt(mCursor1.getColumnIndex("c612_1_day")));
						c.setQ612_2_day(mCursor1.getInt(mCursor1.getColumnIndex("c612_2_day"))==-1 ? -1 : mCursor1.getInt(mCursor1.getColumnIndex("c612_2_day")));
						c.setQ612_3_day(mCursor1.getInt(mCursor1.getColumnIndex("c612_3_day"))==-1 ? -1 : mCursor1.getInt(mCursor1.getColumnIndex("c612_3_day")));
						c.setQ612_4_day(mCursor1.getInt(mCursor1.getColumnIndex("c612_4_day"))==-1 ? -1 : mCursor1.getInt(mCursor1.getColumnIndex("c612_4_day")));
						c.setQ612_6_day(mCursor1.getInt(mCursor1.getColumnIndex("c612_6_day"))==-1 ? -1 : mCursor1.getInt(mCursor1.getColumnIndex("c612_6_day")));

						
						c.setq612_1_other(mCursor1.getString(mCursor1.getColumnIndex("c612_1_other")));
						c.setq612_3_other(mCursor1.getString(mCursor1.getColumnIndex("c612_3_other")));
						c.setq612_4_other(mCursor1.getString(mCursor1.getColumnIndex("c612_4_other")));
						c.setq612_6_other(mCursor1.getString(mCursor1.getColumnIndex("c612_6_other")));
						
					}while (mCursor1.moveToNext());
				//String y = (s > 120) ? "Slow Down" : "Safe";
				
				

				

			}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}
		return c;
	}

	public int getQ612_1() {
		return q612_1;
	}

	public void setQ612_1(int q612_1) {
		this.q612_1 = q612_1;
	}

	public int getQ612_2() {
		return q612_2;
	}

	public void setQ612_2(int q612_2) {
		this.q612_2 = q612_2;
	}

	public int getQ612_3() {
		return q612_3;
	}

	public void setQ612_3(int q612_3) {
		this.q612_3 = q612_3;
	}

	public int getQ612_4() {
		return q612_4;
	}

	public void setQ612_4(int q612_4) {
		this.q612_4 = q612_4;
	}

	public int getQ612_6() {
		return q612_6;
	}

	public void setQ612_6(int q612_6) {
		this.q612_6 = q612_6;
	}

	public int getQ612_5() {
		return q612_5;
	}

	public void setQ612_5(int q612_5) {
		this.q612_5 = q612_5;
	}

	public int getQ612_5_day() {
		return q612_5_day;
	}

	public void setQ612_5_day(int q612_5_day) {
		this.q612_5_day = q612_5_day;
	}

}
