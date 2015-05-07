package com.icddrb.app.wbendLineenvsamplingd1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import com.icddrb.app.wbendLineenvsamplingd1.R;
import com.icddrb.app.wbendLineenvsamplingd1.db.DatabaseHelper;
import com.icddrb.app.wbendLineenvsamplingd1.questions.ParentActivity;


public class BaseActivity extends Activity {
	protected DatabaseHelper dbHelper;
	protected DatabaseHelper dbHelperBase;
	private Context contxt;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		contxt = this;

		if (DatabaseHelper.getInstance() == null) {
			dbHelper = new DatabaseHelper(contxt);
			dbHelper.openDataBase();
		} else {
			dbHelper = DatabaseHelper.getInstance();
		}
		
		
		setTheme(R.style.AppTheme);
	}

	public int getPreviousSLNumber() {
		if (CommonStaticClass.SLNOSTACK.size() > 0) {
			CommonStaticClass.SLNOSTACK.remove(CommonStaticClass.SLNOSTACK
					.size() - 1);
		}
		if (CommonStaticClass.SLNOSTACK.size() != 0) {
			CommonStaticClass.currentSLNo = CommonStaticClass.SLNOSTACK
					.get(CommonStaticClass.SLNOSTACK.size() - 1);
		}
		Log.e("CommonStaticClass.currentSLNo", ""
				+ CommonStaticClass.currentSLNo);
		if (CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo)
				.getQvar() == "memberid")
			CommonStaticClass.isMember = false;
		return CommonStaticClass.currentSLNo;
	}

	public void onBackPressed(boolean b) {
		if (b) {
			super.onBackPressed();
		}
	}

	public void userPressedPrevious(ParentActivity activity) {
		
		if (CommonStaticClass.mode.equalsIgnoreCase(CommonStaticClass.ADDMODE)) {
			if (CommonStaticClass.addCycleStarted
					&& CommonStaticClass.currentSLNo == 2) {
				CommonStaticClass
						.showMyAlert(contxt, "Warning!!!",
								"You can not go back, since id is already generated you can not edit that");
			} else {

				if (CommonStaticClass.SLNOSTACK.size() > 1) {
					activity.gotoForm(CommonStaticClass.questionMap.get(
							getPreviousSLNumber()).getFormname());
				}
			}
		} else if (CommonStaticClass.mode
				.equalsIgnoreCase(CommonStaticClass.EDITMODE)) {
			if (CommonStaticClass.SLNOSTACK.size() > 1) {
				activity.gotoForm(CommonStaticClass.questionMap.get(
						getPreviousSLNumber()).getFormname());
			} else {
				activity.finish();
			}
		}

	}

	protected void setEveryThingBackToNormalState(MyListTracker ml) {
		CommonStaticClass.pName = ml.pName;
		CommonStaticClass.addCycleStarted = ml.addCycleStarted;
		CommonStaticClass.userSpecificId = ml.userSpecificId;
		CommonStaticClass.dataId = ml.dataId;
		// CommonStaticClass.currentHHMemberLine = ml.currentHHMemberLine;
		CommonStaticClass.totalHHMember = ml.totalHHMember;
		CommonStaticClass.truetracker = ml.truetracker;
		CommonStaticClass.checker = ml.checker;
		CommonStaticClass.SLNOSTACK = ml.SLNOSTACK;
		CommonStaticClass.secMap1 = ml.secMap1;
		CommonStaticClass.secMap2 = ml.secMap2;
		CommonStaticClass.houseHoldToLook = ml.houseHoldToLook;
		CommonStaticClass.questionMap = ml.questionMap;
		CommonStaticClass.mode = ml.mode;
		CommonStaticClass.qskipList = ml.qskipList;
		CommonStaticClass.currentSLNo = ml.currentSLNo;
		CommonStaticClass.langBng = ml.langBng;
		Intent i = new Intent();
		i.setClassName(
				CommonStaticClass.pName,
				CommonStaticClass.pName
						+ ".questions."
						+ CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getFormname());
		startActivity(i);
	}

	// protected void preserveState() {
	// MyListTracker ml = new MyListTracker();
	// ReadWriteObject.writeListToFile(contxt, ml);
	// }
	// protected void retrieveState() {
	// setEveryThingBackToNormalState(ReadWriteObject.openFileAndRead(contxt));
	// }
	//
	protected boolean futureDateValidator(Date d) {

		final Calendar c = Calendar.getInstance();
		int dateYear = c.get(Calendar.YEAR);
		int dateMonth = c.get(Calendar.MONTH);
		int dateDay = c.get(Calendar.DAY_OF_MONTH);
		// c.
		if (d.getYear() > dateYear || d.getMonth() > dateMonth
				|| d.getDay() > dateDay) {
			return true;
		}

		return false;
	}

	protected boolean futureDateValidator(int year, int month, int day) {

		final Calendar c = Calendar.getInstance();
		int dateYear = c.get(Calendar.YEAR);
		int dateMonth = c.get(Calendar.MONTH);
		int dateDay = c.get(Calendar.DAY_OF_MONTH);

		if (year > dateYear) {
			return true;
		} else if (year <= dateYear) {
			if (year < dateYear) {
				return false;
			}
			if ((month + 1) > (dateMonth + 1)) {
				return true;
			} else if ((month + 1) <= (dateMonth + 1)) {
				if ((month + 1) < (dateMonth + 1)) {
					return false;
				}
				if (day > dateDay) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	protected void nullifyWithInRange(final String q1, final String q2) {
		// TODO Auto-generated method stub
		// progressDialog = ProgressDialog.show(contxt, "Wait",
		// "Please wait while processing next question");
		// new Thread()
		// {
		// @Override
		// public void run()
		// {
		int sLNo1 = CommonStaticClass.giveTheSLNo(q1);
		int sLNo2 = CommonStaticClass.giveTheSLNo(q2);
		ArrayList<Integer> slNos = CommonStaticClass.serialNoWithinRange(sLNo1,
				sLNo2);
		for (int i = 0; i < slNos.size(); i++) {
			String qN = CommonStaticClass.questionMap.get(slNos.get(i))
					.getQvar();
			
			String sql = "";
			if (!CommonStaticClass.isMember)
				sql = "UPDATE "
						+ CommonStaticClass.questionMap.get(slNos.get(i))
								.getTablename() + " SET " + qN + "= null where dataid='" + CommonStaticClass.dataId + "'";
			else
				sql = "UPDATE "
						+ CommonStaticClass.questionMap.get(slNos.get(i))
								.getTablename() + " SET " + qN + "= null and memberid=" + CommonStaticClass.memberID;
			Log.e("sqlllllllll", sql);
			if (dbHelper.executeDMLQuery(sql)) {
				Log.e("nullify", "done");
			} else {
				Options op1 = CommonStaticClass.findOptionsForThisQuestion(
						dbHelper,
						CommonStaticClass.questionMap.get(slNos.get(i))
								.getQvar());
				for (int i1 = 0; i1 < op1.qidList.size(); i1++) {
					String sql1 = "";
					if (!CommonStaticClass.isMember)
						sql1 = "UPDATE "
								+ CommonStaticClass.questionMap.get(
										slNos.get(i)).getTablename() + " SET "
								+ op1.qidList.get(i1) + "= null where dataid='" + CommonStaticClass.dataId
								+ "'";
					else
						sql1 = "UPDATE "
								+ CommonStaticClass.questionMap.get(
										slNos.get(i)).getTablename() + " SET "
								+ op1.qidList.get(i1) + "= null where dataid='" + CommonStaticClass.dataId
								+ "' and memberid="
								+ CommonStaticClass.memberID;
					if (dbHelper.executeDMLQuery(sql1)) {
						Log.e("nullify", "done");
					}
				}

			}
		}

	}

	private void Nullifyq15family() {
		String sql = "";
		if (!CommonStaticClass.isMember)
			sql = "DELETE FROM tblFamilyInfo where dataid='"
					+ CommonStaticClass.dataId + "'";

		dbHelper.executeDMLQuery(sql);

	}

	public void nullifyq4_3() {
		String sql = "UPDATE tblMainQues SET q4_3_1 = '-1', q4_3_2 = '-1', q4_3_3 = '-1', q4_3_4 = '-1', q4_3_5 = '-1', q4_3_6 = '-1', q4_3_7 = '-1', q4_3_8 = '-1', q4_3_9 ='-1', q4_3_10 = '-1', q4_3_11 = '-1', q4_3_12 = '-1', q4_3_13 = '-1', q4_3_14 = '-1', q4_3_15 = '-1', q4_3_16 = '-1', q4_3_17 = '-1', q4_3_18 = '-1', q4_3_19 = '-1', q4_3_20 = '-1', q4_3_21 = '-1', q4_3_22 = '-1', q4_3_23 = '-1', q4_3_24 = '-1', q4_3_25 = '-1', q4_3_26 = '-1', q4_3_27 ='-1', q4_3_28 = '-1', q4_3_29 = '-1'  where dataid='"
				+ CommonStaticClass.dataId + "'";

		dbHelper.executeDMLQuery(sql);

	}

	protected int valueFromDB(int slno) {
		int val = -1;
		String sql = "Select * from "
				+ CommonStaticClass.questionMap.get(slno).getTablename()
				+ " where dataid='" + CommonStaticClass.dataId + "'";
		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.moveToFirst()) {
				do {
					String column = CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar();

					if (mCursor1.getColumnIndex(column) != -1) {
						Log.e("frmSingle", mCursor1.getString(mCursor1
								.getColumnIndex(CommonStaticClass.questionMap
										.get(CommonStaticClass.currentSLNo)
										.getQvar())));
						String a = mCursor1.getString(mCursor1
								.getColumnIndex(CommonStaticClass.questionMap
										.get(CommonStaticClass.currentSLNo)
										.getQvar()));
						val = (a.length() > 0) ? Integer.parseInt(a) : -1;
					}
				} while (mCursor1.moveToNext());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			val = -1;
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}
		return val;
	}

	protected void checkDbHasPreviousDataForThisHouseHold() {
		if (!CommonStaticClass.isChecked) {
			CommonStaticClass.houseHoldToLook = CommonStaticClass.dataId
					.substring(1, CommonStaticClass.dataId.length() - 2);
			String curparticipantId = CommonStaticClass.dataId.substring(
					CommonStaticClass.dataId.length() - 2,
					CommonStaticClass.dataId.length());
			String curparticipantType = CommonStaticClass.dataId
					.substring(0, 1);
			String sqlCheck = "";

			Cursor mCursor1 = null, mCursor2 = null, mCursor3 = null, mCursor4 = null, mCursor5 = null;
			try {
				sqlCheck = "Select dataid from tblMainQues where dataid like '%"
						+ CommonStaticClass.houseHoldToLook + "%'";
				mCursor1 = dbHelper.getQueryCursor(sqlCheck);
				if (mCursor1.getCount() > 0) {
					sqlCheck = "Select dataid from tblMainQuesMC where dataid like '%"
							+ CommonStaticClass.houseHoldToLook + "%'";
					mCursor2 = dbHelper.getQueryCursor(sqlCheck);
					if (mCursor2.getCount() > 0) {
						sqlCheck = "Select dataid from tblMainQuesMCThree where dataid like '%"
								+ CommonStaticClass.houseHoldToLook + "%'";
						mCursor3 = dbHelper.getQueryCursor(sqlCheck);
						if (mCursor3.getCount() > 0) {
							sqlCheck = "Select dataid from tblMainQuesSC where dataid like '%"
									+ CommonStaticClass.houseHoldToLook + "%'";
							mCursor4 = dbHelper.getQueryCursor(sqlCheck);
							if (mCursor4.getCount() > 0) {
								sqlCheck = "Select dataid from tblAnthropometry where dataid like '%"
										+ CommonStaticClass.houseHoldToLook
										+ "%'";
								mCursor5 = dbHelper.getQueryCursor(sqlCheck);
								if (mCursor5.getCount() > 0) {
									if (mCursor5.moveToFirst()) {
										do {
											if (!(CommonStaticClass.previoushouseHoldDatatId
													.length() > 0)) {
												CommonStaticClass.previoushouseHoldDatatId = mCursor5
														.getString(mCursor5
																.getColumnIndex("dataid"));
												String preparticipantId = CommonStaticClass.previoushouseHoldDatatId
														.substring(
																CommonStaticClass.previoushouseHoldDatatId
																		.length() - 2,
																CommonStaticClass.previoushouseHoldDatatId
																		.length());
												boolean go = false;
												String preparticipantType = CommonStaticClass.previoushouseHoldDatatId
														.substring(0, 1);
												if (preparticipantId
														.equalsIgnoreCase(curparticipantId)) {
													if (!preparticipantType
															.equalsIgnoreCase(curparticipantType)) {
														go = true;
													} else {
														go = false;
													}
												}
												if (preparticipantType
														.equalsIgnoreCase(curparticipantType)) {
													if (!preparticipantId
															.equalsIgnoreCase(curparticipantId)) {
														go = true;
													} else {
														go = false;
													}
												}
												if (!preparticipantType
														.equalsIgnoreCase(curparticipantType)
														&& !preparticipantId
																.equalsIgnoreCase(curparticipantId)) {
													go = true;
												}
												if (go) {
													if (!CommonStaticClass.previousqlist
															.contains("q06")
															&& !CommonStaticClass.previousqlist
																	.contains("q07")) {
														CommonStaticClass.previousqlist
																.add("q06");
														CommonStaticClass.previousqlist
																.add("q07");
														CommonStaticClass.previousqlist
																.add("q11");
														CommonStaticClass.previousqlist
																.add("q113");
														CommonStaticClass.previousqlist
																.add("q114");
														CommonStaticClass.previousqlist
																.add("q115");
														CommonStaticClass.previousqlist
																.add("q115");
														CommonStaticClass
																.addQuestionFromThisSection(
																		"q5",
																		dbHelper);
														CommonStaticClass
																.addQuestionFromThisSection(
																		"q6",
																		dbHelper);
														CommonStaticClass
																.addQuestionFromThisSection(
																		"q7",
																		dbHelper);
														CommonStaticClass
																.addQuestionFromThisSection(
																		"q11",
																		dbHelper);
													}
													CommonStaticClass.previousDataFound = true;
													Log.e("returning ", "true");
												}
											}

										} while (mCursor5.moveToNext());
									}
								}

							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (mCursor1 != null)
					mCursor1.close();
				if (mCursor2 != null)
					mCursor2.close();
				if (mCursor3 != null)
					mCursor3.close();
				if (mCursor4 != null)
					mCursor4.close();
				if (mCursor5 != null)
					mCursor5.close();
			}

		}
	}
}
