package com.icddrb.app.wbendLineenvsamplingd1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.media.ExifInterface;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.icddrb.app.wbendLineenvsamplingd1.db.DatabaseHelper;
import com.icddrb.app.wbendLineenvsamplingd1.questions.ParentActivity;

public class CommonStaticClass {

	public static String pName = "com.icddrb.app.wbendLineenvsamplingd1";
	public static final String ADDMODE = "add";
	public static final String EDITMODE = "edit";
	public static boolean addCycleStarted = false;
	public static String userSpecificId = "";
	public static String dataId = "";
	public static String HosCode = "";

	public static String LastPatientID = "";

	public static String sampleid = "";
	public static String randomid = "";
	public static boolean previousDataFound = false;
	public static String houseHoldToLook = "";
	public static String previoushouseHoldDatatId = "";
	public static int totalHHMember = 1;
	public static ArrayList<Integer> truetracker = new ArrayList<Integer>();
	public static boolean checker = false;
	public static ArrayList<Integer> SLNOSTACK = new ArrayList<Integer>();
	public static ArrayList<String> secMap1 = new ArrayList<String>();
	public static ArrayList<Integer> secMap2 = new ArrayList<Integer>();
	public static ArrayList<String> previousqlist = new ArrayList<String>();
	public static LinkedHashMap<Integer, QuestionData> questionMap = new LinkedHashMap<Integer, QuestionData>();
	public static String mode = "";
	public static ArrayList<String> qskipList = new ArrayList<String>();
	public static int currentSLNo = 1;
	public static boolean langBng = false;
	public static String participantType = "";
	public static boolean isChecked = false;
	public static boolean isMember = false;
	public static String memberID = "";
	public static String AssetID = "";
	public static String VersionNo = "";
	public static String ClusterId = "";
	public static String MotherID = "";
	public static int numberofchildren = 0;

	public static void nextQuestion(final ParentActivity activity) {
		// TODO Auto-generated method stub
		if (CommonStaticClass.currentSLNo == 1) {
			activity.runOnUiThread(new Runnable() {

				public void run() {
					// TODO Auto-generated method stub
					activity.finish();
				}
			});
		} else {
			CommonStaticClass.SLNOSTACK.add(CommonStaticClass.currentSLNo);
			activity.runOnUiThread(new Runnable() {

				public void run() {
					// TODO Auto-generated method stub
					Log.e("nextQuestion: CommonStaticClass.currentSLNo",""+CommonStaticClass.currentSLNo);
					activity.gotoForm(CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getFormname());
				}
			});
		}

	}
	
	
	/*public static void nextQuestion(final ParentActivity activity) {
		// TODO Auto-generated method stub
		if (CommonStaticClass.currentSLNo == 1) {
			activity.runOnUiThread(new Runnable() {

				public void run() {

					
					 * if(Integer.parseInt(CommonStaticClass.memberID)>
					 * CommonStaticClass.numberofchildren) {
					 

					if (CommonStaticClass.currentSLNo == 145) {
						Log.e("Second Child", " Second Child"
								+ CommonStaticClass.memberID);

						activity.gotoForm(CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getFormname());
					}

					// }
					else
						activity.finish();

				}
			});

		} else {
			CommonStaticClass.SLNOSTACK.add(CommonStaticClass.currentSLNo);
			activity.runOnUiThread(new Runnable() {

				public void run() {
					// TODO Auto-generated method stub
					Log.e("nextQuestion: CommonStaticClass.currentSLNo", ""
							+ CommonStaticClass.currentSLNo);
					if(CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo)==null)
					{
						//activity.finish();
					}
						
					else
						activity.gotoForm(CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getFormname());
				}
			});
		}

	}*/

	public static void findOutNextSLNo(String curNext, String qNext) {
		// TODO Auto-generated method stub
		if (qNext.equalsIgnoreCase("END")) {
			CommonStaticClass.currentSLNo = 1;
		} else {
			Iterator it = CommonStaticClass.questionMap.entrySet().iterator();
			while (it.hasNext()) {
				LinkedHashMap.Entry<Integer, QuestionData> pairs = (LinkedHashMap.Entry<Integer, QuestionData>) it
						.next();
				if (pairs.getValue().getQvar().equalsIgnoreCase(qNext)) {
					if (CommonStaticClass.qskipList.contains(qNext)) {
						if (qNext.toLowerCase().contains("sec")) {
							gotoNextViableSection(qNext);
						} else {
							CommonStaticClass.currentSLNo = pairs.getKey();
							findOutNextSLNo(
									CommonStaticClass.questionMap.get(
											CommonStaticClass.currentSLNo)
											.getQvar(),
									CommonStaticClass.questionMap.get(
											CommonStaticClass.currentSLNo)
											.getQnext1());
						}
					} else {
						CommonStaticClass.currentSLNo = pairs.getKey();
					}
					Log.e("findOutNextSLNo: CommonStaticClass.qn", qNext);
					Log.e("findOutNextSLNo: CommonStaticClass.currentSLNo",
							CommonStaticClass.currentSLNo + "");
					return;
				}
			}
		}
	}

	/*public static void findOutNextSLNo(String curNext, String qNext) {
		// TODO Auto-generated method stub
		if (qNext.equalsIgnoreCase("END")) {
			CommonStaticClass.currentSLNo = 1;
		} else {
			Iterator it = CommonStaticClass.questionMap.entrySet().iterator();
			while (it.hasNext()) {
				LinkedHashMap.Entry<Integer, QuestionData> pairs = (LinkedHashMap.Entry<Integer, QuestionData>) it
						.next();
				if (pairs.getValue().getQvar().equalsIgnoreCase(qNext)) {
					if (CommonStaticClass.qskipList.contains(qNext)) {
						if (qNext.toLowerCase().contains("sec")) {
							gotoNextViableSection(qNext);
						} else {
							CommonStaticClass.currentSLNo = pairs.getKey();
							findOutNextSLNo(
									CommonStaticClass.questionMap.get(
											CommonStaticClass.currentSLNo)
											.getQvar(),
									CommonStaticClass.questionMap.get(
											CommonStaticClass.currentSLNo)
											.getQnext1());
						}
					} else {
						CommonStaticClass.currentSLNo = pairs.getKey();
					}
					Log.e("findOutNextSLNo: CommonStaticClass.qn", qNext);
					Log.e("findOutNextSLNo: CommonStaticClass.currentSLNo",
							CommonStaticClass.currentSLNo + "");
					return;
				}
			}
		}
	}*/
	public static int giveTheSLNo(String qn) {
		// TODO Auto-generated method stub
		Iterator it = CommonStaticClass.questionMap.entrySet().iterator();
		while (it.hasNext()) {
			LinkedHashMap.Entry<Integer, QuestionData> pairs = (LinkedHashMap.Entry<Integer, QuestionData>) it
					.next();
			if (pairs.getValue().getQvar().equalsIgnoreCase(qn)) {
				Log.e("pairs.getKey()", pairs.getKey() + "");
				return pairs.getKey();
			}
		}
		return 0;
	}

	public static String GetTableName(String qn) {
		// TODO Auto-generated method stub
		Iterator it = CommonStaticClass.questionMap.entrySet().iterator();
		while (it.hasNext()) {
			LinkedHashMap.Entry<Integer, QuestionData> pairs = (LinkedHashMap.Entry<Integer, QuestionData>) it
					.next();
			if (pairs.getValue().getQvar().equalsIgnoreCase(qn)) {
				Log.e("pairs.getValue().getTablename()", pairs.getValue()
						.getTablename() + "");
				return pairs.getValue().getTablename();

			}
		}
		return "";
	}

	public static ArrayList<Integer> serialNoWithinRange(int slNo1, int slNo2) {
		ArrayList<Integer> myList = new ArrayList<Integer>();
		boolean doAdd = false;
		Iterator it = CommonStaticClass.questionMap.entrySet().iterator();
		while (it.hasNext()) {
			LinkedHashMap.Entry<Integer, QuestionData> pairs = (LinkedHashMap.Entry<Integer, QuestionData>) it
					.next();
			if (pairs.getKey() == slNo2) {
				return myList;
			}
			if (doAdd) {
				Log.e("adding slno", pairs.getKey() + "");
				myList.add(pairs.getKey());
			}
			if (pairs.getKey() == slNo1) {
				doAdd = true;
			}
		}
		return myList;
	}

	public static void addQuestionFromThisSection(String qNext,
			DatabaseHelper dbHelper) {
		String sql = "Select Qvar from tblQuestion where Qvar like '" + qNext
				+ "%'";
		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.getCount() > 0) {
				if (mCursor1.getCount() > 0) {
					if (mCursor1.moveToFirst()) {
						do {
							String qn = mCursor1.getString(mCursor1
									.getColumnIndex("Qvar"));
							Log.e("qnnnnn", qn);
							CommonStaticClass.previousqlist.add(qn);
						} while (mCursor1.moveToNext());
					}

				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private static void gotoNextViableSection(String qNext) {
		boolean a = false;

		for (int i = 0; i < CommonStaticClass.secMap1.size(); i++) {
			if (CommonStaticClass.secMap1.get(i).equalsIgnoreCase(qNext)) {
				Log.e("current section", CommonStaticClass.secMap1.get(i) + "");
				a = true;
			}
			if (a) {
				if (!CommonStaticClass.qskipList
						.contains(CommonStaticClass.secMap1.get(i))) {
					Log.e("next section", CommonStaticClass.secMap1.get(i) + "");
					qNext = CommonStaticClass.secMap1.get(i);
					CommonStaticClass.currentSLNo = CommonStaticClass.secMap2
							.get(CommonStaticClass.secMap1.indexOf(qNext));
					Log.e("from gotoNextViableSection CommonStaticClass.currentSLNo",
							CommonStaticClass.currentSLNo + "");
					Log.e("from gotoNextViableSection qNext", qNext + "");

					break;

				}
			}
		}

	}

	public static void showFinalAlert(Context con, CharSequence message) {
		new AlertDialog.Builder(con).setTitle("User Credential Incorrect!!!")
				.setMessage(message)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.dismiss();
					}
				}).setCancelable(false).show();
	}

	public static void showMyAlert(Context con, String title,
			CharSequence message) {
		new AlertDialog.Builder(con).setTitle(title).setMessage(message)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.dismiss();
					}
				}).setCancelable(false).show();
	}

	public static Options findOptionsForThisQuestion(DatabaseHelper dbHelper,
			String qvar) {
		// TODO Auto-generated method stub
		String sql1 = "", sql2 = "";

		sql1 = "Select * from tblOptions where QID ='" + qvar
				+ "' order by SLNo";
		sql2 = "Select * from tblOptions where QID like '" + qvar
				+ "%' order by SLNo ASC";

		Options op = new Options();
		op.q = qvar;
		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql1);
			try {
				if (mCursor1 != null && mCursor1.getCount() > 0) {

				} else {
					mCursor1 = dbHelper.getQueryCursor(sql2);
				}
			} catch (Exception e) {
				// TODO: handle exception
				mCursor1 = dbHelper.getQueryCursor(sql2);
			}
			if (mCursor1.moveToFirst()) {
				do {
					Log.e("",
							mCursor1.getString(mCursor1.getColumnIndex("QID"))
									+ "");
					op.qidList.add(mCursor1.getString((mCursor1
							.getColumnIndex("QID"))));
					op.capEngList.add(mCursor1.getString((mCursor1
							.getColumnIndex("CaptionEng"))));
					op.capBngList.add(mCursor1.getString((mCursor1
							.getColumnIndex("CaptionBang"))));
					Log.e("opD.getCode()",
							mCursor1.getString((mCursor1.getColumnIndex("Code")))
									+ "");
					op.codeList.add(Integer.parseInt(mCursor1
							.getString((mCursor1.getColumnIndex("Code")))));
					op.qnList.add(mCursor1.getString((mCursor1
							.getColumnIndex("QNext"))));
				} while (mCursor1.moveToNext());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}
		return op;
	}

	public static Options findOptionsForScvb(DatabaseHelper dbHelper,
			String qvar) {
		// TODO Auto-generated method stub
		String sql = "";

		if (qvar.equalsIgnoreCase("AgeYr")) {
			sql = "Select QID, CaptionEng, CaptionBang, Code, QNext from tblOptions WHERE (QID = 'AgeYr' OR QID = 'AgeMo' OR QID = 'AgeDa') order by SLNo";
		}

		else if (qvar.equalsIgnoreCase("WT")) {
			sql = "Select QID, CaptionEng, CaptionBang, Code, QNext from tblOptions WHERE (QID = 'WT' OR QID = 'HT' OR QID = 'MUAC') order by SLNo";
		}

		else if (qvar.equalsIgnoreCase("Ward")) {
			sql = "Select QID, CaptionEng, CaptionBang, Code, QNext from tblOptions WHERE (QID = 'Ward' OR QID = 'Area' OR QID = 'SecBlock' OR QID = 'Road' OR QID = 'House' OR QID = 'Phone' OR QID = 'Vill' OR QID = 'UPZ' OR QID = 'Dist' ) order by SLNo";
		}

		else if (qvar.equalsIgnoreCase("DS36")) {
			sql = "Select QID, CaptionEng, CaptionBang, Code, QNext from tblOptions WHERE (QID = 'DS36' OR QID = 'DS37' OR QID = 'DS38' OR QID = 'DS39') order by SLNo";
		}

		else if (qvar.equalsIgnoreCase("DS23")) {
			sql = "Select QID, CaptionEng, CaptionBang, Code, QNext from tblOptions WHERE (QID = 'DS23' OR QID = 'DS24' OR QID = 'DS25' OR QID = 'DS25' OR QID = 'DS23_Options') order by SLNo";
		}

		Options op = new Options();
		op.q = qvar;
		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			try {
				if (mCursor1 != null && mCursor1.getCount() > 0) {

				}
			} catch (Exception e) {
				// TODO: handle exception

			}
			if (mCursor1.moveToFirst()) {
				do {
					Log.e("",
							mCursor1.getString(mCursor1.getColumnIndex("QID"))
									+ "");
					op.qidList.add(mCursor1.getString((mCursor1
							.getColumnIndex("QID"))));
					op.capEngList.add(mCursor1.getString((mCursor1
							.getColumnIndex("CaptionEng"))));
					op.capBngList.add(mCursor1.getString((mCursor1
							.getColumnIndex("CaptionBang"))));
					Log.e("opD.getCode()",
							mCursor1.getString((mCursor1.getColumnIndex("Code")))
									+ "");
					op.codeList.add(Integer.parseInt(mCursor1
							.getString((mCursor1.getColumnIndex("Code")))));
					op.qnList.add(mCursor1.getString((mCursor1
							.getColumnIndex("QNext"))));
				} while (mCursor1.moveToNext());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}
		return op;
	}

	public static Options findOptionsForMedicineList(DatabaseHelper dbHelper,
			String qvar, ArrayList<String> opMedicine) {
		// TODO Auto-generated method stub
		String sql2 = "";

		sql2 = "Select * from tblMedicine  ORDER BY ID";

		Options op = new Options();
		op.q = qvar;
		Cursor mCursor1 = null;
		try {

			try {

				mCursor1 = dbHelper.getQueryCursor(sql2);

			} catch (Exception e) {
				// TODO: handle exception
				mCursor1 = dbHelper.getQueryCursor(sql2);
			}

			if (mCursor1.moveToFirst()) {
				do {
					Log.e("", mCursor1.getString(mCursor1.getColumnIndex("ID"))
							+ "");
					op.qidList
							.add(qvar
									+ "_"
									+ mCursor1.getString((mCursor1
											.getColumnIndex("ID"))));

					op.capEngList.add(mCursor1.getString((mCursor1
							.getColumnIndex("Name"))));
					op.capBngList.add(mCursor1.getString((mCursor1
							.getColumnIndex("Name"))));

					Log.e("opD.getCode()",
							mCursor1.getString((mCursor1.getColumnIndex("ID")))
									+ "");

					op.codeList.add(mCursor1.getInt((mCursor1
							.getColumnIndex("ID"))));
					// opMedicine.add(mCursor1.getString(mCursor1.getColumnIndex("ID")));

					/*
					 * op.codeList.add(Integer.parseInt(mCursor1
					 * .getString((mCursor1.getColumnIndex("ID")))));
					 */

					op.qnList.add("");
				} while (mCursor1.moveToNext());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}
		return op;
	}

	// /* Zaman*//
	// Find out index from collection based on sent value
	public static int GetIndexFromCollection(ArrayList list, String value) {
		Iterator Item = list.iterator();
		int index = -1;
		while (Item.hasNext()) {
			index++;
			if (Item.next().toString().equalsIgnoreCase(value))
				return index;

		}
		return -1;
	}

	public static String getSkip(String column, String tablename,
			DatabaseHelper dbHelper) {

		String GtSkip = "";
		String sql = "";

		if (!CommonStaticClass.isMember)

			sql = "Select " + column + " from " + tablename + " where dataid='"
					+ CommonStaticClass.dataId + "'";
		else {
			sql = "Select " + column + " from " + tablename + " where dataid='"
					+ CommonStaticClass.dataId + "'" + "AND memberid='"
					+ CommonStaticClass.memberID + "'";
		}
		// String data ="";

		Cursor mCursor = null;

		try {

			mCursor = dbHelper.getQueryCursor(sql);

			if (mCursor.getCount() > 0) {

				if (mCursor.moveToFirst()) {

					do {

						GtSkip = mCursor.getString(mCursor
								.getColumnIndex(column));

					} while (mCursor.moveToNext());

				}

			}

		} catch (Exception e) {

			// TODO: handle exception

		}

		return GtSkip;

	}

	public static String CodeCheck(String strValue) {
		String strCode = "";
		int found = 0;
		found = strValue.indexOf(":");
		if (found == -1) {
			strCode = strValue;
		} else {
			strCode = strValue.substring(0, found);
		}
		return strCode.trim();
	}

	public static String GetHospitalName(DatabaseHelper dbHelper) {

		String GtSkip = "";

		Cursor mCursor = null;

		try {

			mCursor = dbHelper.getQueryCursor(String.format(
					"Select HosName,DistCode from Hospital where Hosid = '%s'",
					CommonStaticClass.dataId.substring(5, 7)));

			if (mCursor.getCount() > 0) {

				if (mCursor.moveToFirst()) {

					do {

						GtSkip = mCursor.getString(0);

					} while (mCursor.moveToNext());

				}

			}

		} catch (Exception e) {

			// TODO: handle exception

		}

		return GtSkip;

	}

	public static String CheckCursorValue(Cursor c, String ColumnName) {
		if (c.getString(c.getColumnIndex(ColumnName)) != null) {
			if (c.getString(c.getColumnIndex(ColumnName)).length() > 0)

			{

				if (c.getString(c.getColumnIndex(ColumnName)).toString().trim()
						.length() > 0) {
					return c.getString(c.getColumnIndex(ColumnName)).toString();

				} else
					return "";
			}

			else
				return "";

		} else {
			return null;
		}

	}
	public static String CheckCursorValueWithNullHandler(Cursor c, String ColumnName) {
		if (c.getString(c.getColumnIndex(ColumnName)) != null) {
			if (c.getString(c.getColumnIndex(ColumnName)).length() > 0)

			{

				if (c.getString(c.getColumnIndex(ColumnName)).toString().trim()
						.length() > 0) {
					return c.getString(c.getColumnIndex(ColumnName)).toString().trim();

				} else
					return "";
			}

			else
				return "";

		} else {
			return "";
		}

	}
	public static void FillCombo(ParentActivity act, DatabaseHelper dbHelper,
			String sql, Spinner spnr) {

		ArrayList<String> ids = new ArrayList<String>();

		Cursor mCursor = null;

		try {

			mCursor = dbHelper.getQueryCursor(sql);

			if (mCursor.getCount() > 0) {

				if (mCursor.moveToFirst()) {

					do {

						ids.add(mCursor.getString(0));

					} while (mCursor.moveToNext());

					ArrayAdapter adapterSl = new ArrayAdapter(act,
							android.R.layout.simple_spinner_dropdown_item, ids);
					adapterSl
							.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					spnr.setAdapter(adapterSl);

				}

			}

		} catch (Exception e) {

			// TODO: handle exception

		}
	}

	public static void SetSpinnerValue(ParentActivity act, Spinner spnr,
			String Value) {

		try {

			for (int i = 0; i < spnr.getCount(); i++) {

				if (spnr.getItemAtPosition(i).toString().length() > 0) {
					if (spnr.getItemAtPosition(i)
							.toString()
							.substring(
									0,
									(spnr.getItemAtPosition(i).toString()
											.lastIndexOf(":"))).trim()
							.equalsIgnoreCase(Value)) {

						spnr.setSelection(i);
						return;
					}
				}
			}

		} catch (Exception e) {

			// TODO: handle exception

		}

	}

	public static void SetSpinnerValueFrmString(ParentActivity act,
			Spinner spnr, String Value) {

		try {

			for (int i = 0; i < spnr.getCount(); i++) {
				if (spnr.getItemAtPosition(i).toString()
						.equalsIgnoreCase(String.valueOf(Value.trim()))) {
					spnr.setSelection(i);
				}
			}

		} catch (Exception e) {

			// TODO: handle exception

		}

	}

	public static String padLeft(String s, int n) {
		if (s.length() == 1)
			return String.format("%0s" + n + "s", s);
		else
			return "";
	}

	public static Integer TryParse(Object obj) {
		Integer retVal;
		try {
			retVal = Integer.parseInt(obj.toString());
		} catch (NumberFormatException nfe) {
			retVal = null; // or null if that is your preference
		}
		return retVal;
	}

	public static String GetSpinnerValue(Spinner sp) {

		return ((sp).getSelectedItem().toString().substring(0, (sp)
				.getSelectedItem().toString().lastIndexOf(":"))).trim();

	}
	
	public static String GetSpinnerValueFromString(Spinner sp) {

		return ((sp).getSelectedItem().toString()).trim();

	}

	public static void ComboAddNumber(ParentActivity act, Spinner cboMyCombo,
			int intMax) {
		int i = 1;
		ArrayList<String> ids = new ArrayList<String>();
		while (i <= intMax) {
			
			if(i==1)
			{
				ids.add("");
			}
			ids.add(String.valueOf(i));
			i++;

		}
		ArrayAdapter adapterSl = new ArrayAdapter(act,
				android.R.layout.simple_spinner_item, ids);
		adapterSl
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cboMyCombo.setAdapter(adapterSl);
	}

	public static void ComboAddRangeNumber(ParentActivity act,
			Spinner cboMyCombo, int intStartNum, int intMax, int intDiffer) {
		int i = intStartNum;
		ArrayList<String> ids = new ArrayList<String>();
		while (i <= intMax) {
			
			if(ids.size()==0)
			{
				ids.add("");
			}
			
			ids.add(String.valueOf(i));
			i = i + intDiffer;
		}

		ArrayAdapter adapterSl = new ArrayAdapter(act,
				android.R.layout.simple_spinner_item, ids);
		adapterSl
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cboMyCombo.setAdapter(adapterSl);

		/*
		 * ArrayAdapter adapterSl = new
		 * ArrayAdapter(act.getApplicationContext(),
		 * android.R.layout.simple_spinner_item, ids); adapterSl
		 * .setDropDownViewResource
		 * (android.R.layout.simple_spinner_dropdown_item);
		 * cboMyCombo.setAdapter(adapterSl);
		 */

	}

	public static void ComboAddRangeNumber(ParentActivity act,
			Spinner cboMyCombo, int intStartNum, int intMax) {
		ComboAddRangeNumber(act, cboMyCombo, intStartNum, intMax, 1);
	}

	public static void FillCombo(ParentActivity act, ArrayList<String> ids,
			Spinner spnr) {

		ArrayAdapter adapterSl = new ArrayAdapter(act,
				android.R.layout.simple_spinner_item, ids);
		adapterSl.setDropDownViewResource(android.R.layout.simple_spinner_item);

		/*
		 * adapterSl
		 * .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item
		 * );
		 */
		spnr.setAdapter(adapterSl);

		// return spnr;
	}

	public static String GetCurrentDate()// returns date with time
	{
		Calendar calendar = Calendar.getInstance();

		// set Date portion to January 1, 1970
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		calendar.set(Calendar.DATE, calendar.get(Calendar.DAY_OF_MONTH));

		// normalize the object
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.get(Calendar.MILLISECOND));

		java.sql.Date javaSqlDate = new java.sql.Date(calendar.getTime()
				.getTime());

		return javaSqlDate.toString() + " " + calendar.getTime().getHours()
				+ ":" + calendar.getTime().getMinutes() + ":"
				+ calendar.getTime().getSeconds();
	}

	public static String GetDate() // returns date without time
	{
		Calendar calendar = Calendar.getInstance();

		// set Date portion to January 1, 1970
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		calendar.set(Calendar.DATE, calendar.get(Calendar.DAY_OF_MONTH));

		// normalize the object
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.get(Calendar.MILLISECOND));

		java.sql.Date javaSqlDate = new java.sql.Date(calendar.getTime()
				.getTime());

		return javaSqlDate.toString();// + " " + calendar.getTime().getHours()
										// +":" +
										// calendar.getTime().getMinutes() +":"
										// + calendar.getTime().getSeconds();
	}

	public static int GetYear(boolean lastTwoDigitOnly) {
		Calendar now = Calendar.getInstance();
		if (!lastTwoDigitOnly)
			return now.get(Calendar.YEAR);
		else
			return Integer.parseInt(String.valueOf(now.get(Calendar.YEAR))
					.substring(2, 4));
	}

	public static int GetMonth() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.MONTH)+1;

	}

	public static String GetTime() // returns date without time
	{
		Calendar calendar = Calendar.getInstance();

		// set Date portion to January 1, 1970
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		calendar.set(Calendar.DATE, calendar.get(Calendar.DAY_OF_MONTH));

		// normalize the object
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.get(Calendar.MILLISECOND));

		java.sql.Date javaSqlDate = new java.sql.Date(calendar.getTime()
				.getTime());

		return calendar.getTime().getHours() + ":"
				+ calendar.getTime().getMinutes();// +":"+
													// calendar.getTime().getSeconds();
	}

	public static String padLeft(String s, int length, String pad) {  
        StringBuffer buffer = new StringBuffer(s);  
        while (buffer.length() < length) {  
            buffer.insert(0, pad);  
        }  
        return buffer.toString();  
    } 
	
	public static int daysBetween(Calendar startDate, Calendar endDate) {
		
		Calendar date = (Calendar) startDate.clone();
		long daysBetween = 0;
		while (date.before(endDate)) {
			date.add(Calendar.DAY_OF_MONTH, 1);
			daysBetween++;
		}
		return (int) (daysBetween / 365.25);
	}
	
	public static boolean IsfutureDate(Date d) {

		//final Calendar c = Calendar.getInstance();
	/*	int dateYear = c.get(Calendar.YEAR);
		int dateMonth = c.get(Calendar.MONTH);
		int dateDay = c.get(Calendar.DAY_OF_MONTH);*/
		// c.
		Date now = new Date();
		
		if (now.before(d)) {
			return true;
		}

		return false;
	}
	
	public static int DayDifference(Calendar startDate, Calendar endDate) {
		Calendar date = (Calendar) startDate.clone();
		int daysBetween = 0;
		//while (date.before(endDate)) {
		while (date.before(endDate)) {
			date.add(Calendar.DAY_OF_MONTH, 1);
			daysBetween++;
		}
		return daysBetween;
		//return (int) (daysBetween / 365.25);
	}
	


	// End
}
