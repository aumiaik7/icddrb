package com.icddrb.app.WBMicrobiomeFFQ;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;

import com.icddrb.app.WBMicrobiomeFFQ.CommonStaticClass;
import com.icddrb.app.WBMicrobiomeFFQ.Options;
import com.icddrb.app.WBMicrobiomeFFQ.QuestionData;
import com.icddrb.app.WBMicrobiomeFFQ.R;
import com.icddrb.app.WBMicrobiomeFFQ.db.DatabaseHelper;
import com.icddrb.app.WBMicrobiomeFFQ.questions.FormListener;
import com.icddrb.app.WBMicrobiomeFFQ.questions.ParentActivity;

public class CommonStaticClass {

	public static String pName = "com.icddrb.app.WBMicrobiomeFFQ";	
	public static final String ADDMODE = "add";
	public static final String EDITMODE = "edit";
	public static boolean addCycleStarted = false;
	public static String userSpecificId = "";
	public static String dataId = "";
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
	
	public static boolean IsValidLength()
	{
		return false;
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

	// End
}
