package com.icddrb.app.wbendLineenvsamplingd2;

import com.icddrb.app.wbendLineenvsamplingd2.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuScreen extends BaseActivity {
	protected static final int ALLQUESTIONLOADED = 1;
	protected static final int QUESTIONCANTLODED = 2;
	protected static final int DBSTOREDONE = 11;
	private Button addButton, editButton;
	private ProgressDialog progressDialog;
	private Context con;
	private boolean dbStored = false;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menulayout);
		con = this;
		setTheme(R.style.AppTheme);
		loadGui();
	}

	private void loadGui() {
		// TODO Auto-generated method stub
		addButton = (Button) findViewById(R.id.addButton);
		addButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				CommonStaticClass.mode = CommonStaticClass.ADDMODE;
				clearEveryThing();
				// CommonStaticClass.currentSLNo = 1;
				// BaseActivity.isChecked = false;
				if (CommonStaticClass.questionMap.size() == 0) {
					progressDialog = ProgressDialog.show(con, "Questions",
							"Please wait while loading questioniare");
					new Thread() {

						public void run() {
							loadQuestions();
						}
					}.start();
				}
			}

		});
		editButton = (Button) findViewById(R.id.editButton);
		editButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				CommonStaticClass.mode = CommonStaticClass.EDITMODE;
				Intent i = new Intent();
				/*i.setClassName(CommonStaticClass.pName, CommonStaticClass.pName
						+ ".EditEntry");*/
				i.setClassName(CommonStaticClass.pName, CommonStaticClass.pName
						+ ".EditEntry");
				startActivity(i);
			}

		});
	}

	private void clearEveryThing() {
		// TODO Auto-generated method stub
		if (!CommonStaticClass.SLNOSTACK.isEmpty()) {
			CommonStaticClass.SLNOSTACK.clear();
		}

		if (!CommonStaticClass.truetracker.isEmpty()) {
			CommonStaticClass.truetracker.clear();
		}

		if (!CommonStaticClass.questionMap.isEmpty()) {
			CommonStaticClass.questionMap.clear();
		}
		if (!CommonStaticClass.secMap1.isEmpty()) {
			CommonStaticClass.secMap1.clear();
			CommonStaticClass.secMap2.clear();
		}
		if (!CommonStaticClass.qskipList.isEmpty()) {
			CommonStaticClass.qskipList.clear();
		}
		if (!CommonStaticClass.previousqlist.isEmpty()) {
			CommonStaticClass.previousqlist.clear();
		}
		CommonStaticClass.addCycleStarted = false;
		CommonStaticClass.dataId = "";
		CommonStaticClass.memberID = "";

		CommonStaticClass.isMember = false;
		CommonStaticClass.previousDataFound = false;
		CommonStaticClass.previoushouseHoldDatatId = "";
		CommonStaticClass.houseHoldToLook = "";

		CommonStaticClass.totalHHMember = 1;
		CommonStaticClass.checker = false;
		CommonStaticClass.currentSLNo = 1;
		CommonStaticClass.participantType = "";
		CommonStaticClass.isChecked = false;
	}

	/*********************************************************************************************
	 *** MENUS ***
	 *********************************************************************************************/

	public boolean onCreateOptionsMenu(final Menu menu) {
		final MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.home_menu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(final MenuItem item) {
		switch (item.getItemId()) {
		case R.id.BNGMenuItem:
			CommonStaticClass.langBng = true;
			return true;
		case R.id.ENGMenuItem:
			CommonStaticClass.langBng = false;
			return true;
			/*
			 * case R.id.StoreItem: progressDialog = ProgressDialog.show(con,
			 * "Storing", "Please wait while system store the database"); new
			 * Thread() {
			 * 
			 * public void run() { try { dbHelper.copyDataBaseToSdcard();
			 * dbStored = true; } catch (IOException e) { // TODO Auto-generated
			 * catch block dbStored = false; e.printStackTrace(); } Message msg
			 * = new Message(); msg.what = DBSTOREDONE;
			 * handler.sendMessage(msg); } }.start(); return true; case
			 * R.id.ExitItem: finish(); return true;
			 */

		case R.id.ExitItem:

			CommonStaticClass.mode = "";
			finish();

			/*
			 * Intent i1 = new Intent();
			 * i1.setClassName(CommonStaticClass.pName,
			 * CommonStaticClass.pName+".LoginActivity"); startActivity(i1);
			 */
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void startQuestion() {

		CommonStaticClass.SLNOSTACK.add(CommonStaticClass.currentSLNo);
		Intent i = new Intent();
		i.setClassName(CommonStaticClass.pName, CommonStaticClass.pName
				+ ".questions.ParentActivity");
		startActivity(i);
	}

	private void loadQuestions() {

		String sql = "Select * from tblQuestion order by SLNo asc";
		Cursor mCursor = null;
		try {
			mCursor = dbHelper.getQueryCursor(sql);
			if (mCursor.moveToFirst()) {
				do {
					QuestionData qd = new QuestionData(
							mCursor.getInt(mCursor.getColumnIndex("SLNo")),
							mCursor.getString((mCursor.getColumnIndex("Qvar"))),
							mCursor.getString((mCursor
									.getColumnIndex("Formname"))),
							mCursor.getString((mCursor
									.getColumnIndex("Qdescbng"))),
							mCursor.getString((mCursor
									.getColumnIndex("Qdesceng"))),
							mCursor.getString((mCursor.getColumnIndex("QType"))),
							mCursor.getString((mCursor.getColumnIndex("Qnext1"))),
							mCursor.getString((mCursor.getColumnIndex("Qnext2"))),
							mCursor.getString((mCursor.getColumnIndex("Qnext3"))),
							mCursor.getString((mCursor.getColumnIndex("Qnext4"))),
							mCursor.getString((mCursor
									.getColumnIndex("Qchoice1eng"))), mCursor
									.getString((mCursor
											.getColumnIndex("Qchoice2eng"))),
							mCursor.getString((mCursor
									.getColumnIndex("Qchoice3eng"))), mCursor
									.getString((mCursor
											.getColumnIndex("Qchoice1bng"))),
							mCursor.getString((mCursor
									.getColumnIndex("Qchoice2bng"))), mCursor
									.getString((mCursor
											.getColumnIndex("Qchoice3bng"))),
							mCursor.getString((mCursor
									.getColumnIndex("Qrange1"))), mCursor
									.getString((mCursor
											.getColumnIndex("Qrange2"))),
							mCursor.getString((mCursor
									.getColumnIndex("DataType"))), mCursor
									.getString((mCursor
											.getColumnIndex("Tablename"))));
					
					CommonStaticClass.questionMap.put(
							mCursor.getInt(mCursor.getColumnIndex("SLNo")), qd);
				} while (mCursor.moveToNext());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		} finally {
			if (mCursor != null)
				mCursor.close();

			String sqlForSec = "Select SLNo,Qvar from tblQuestion where Qvar like 'sec%' order by SLNo asc";

			Cursor mCursor1 = null;
			try {
				mCursor1 = dbHelper.getQueryCursor(sqlForSec);
				if (mCursor1.moveToFirst()) {
					do {
						Log.e("secMap1 ", mCursor1.getString((mCursor1
								.getColumnIndex("Qvar"))));
						CommonStaticClass.secMap1.add(mCursor1
								.getString((mCursor1.getColumnIndex("Qvar"))));
						Log.e("secMap2 ",
								mCursor1.getInt(mCursor1.getColumnIndex("SLNo"))
										+ "");
						CommonStaticClass.secMap2.add(mCursor1.getInt(mCursor1
								.getColumnIndex("SLNo")));

					} while (mCursor1.moveToNext());
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();

			} finally {
				if (mCursor1 != null)
					mCursor1.close();
			}

			Message msg = new Message();
			msg.what = ALLQUESTIONLOADED;
			handler.sendMessage(msg);
		}
		// String sql1 = "Select * from tblOptions";
		// Cursor mCursor1 = null;
		// try{
		// mCursor1 = dbHelper.getQueryCursor(sql1);
		// if(mCursor1.moveToFirst()){
		// do{
		// OptionData od = new
		// OptionData(mCursor1.getString((mCursor1.getColumnIndex("QID"))),
		// mCursor1.getString((mCursor1.getColumnIndex("CaptionEng"))),
		// mCursor1.getString((mCursor1.getColumnIndex("CaptionBang"))),
		// mCursor1.getString((mCursor1.getColumnIndex("Code"))),
		// mCursor1.getString((mCursor1.getColumnIndex("QNext"))));
		// CommonStaticClass.optionMap.add(od);
		// }while(mCursor1.moveToNext());
		// }
		// }catch (Exception e) {
		// // TODO: handle exception
		// e.printStackTrace();
		// Message msg = new Message();
		// msg.what = QUESTIONCANTLODED;
		// handler.sendMessage(msg);
		// }finally{
		// if(mCursor1!=null)
		// mCursor1.close();
		//
		//
		// }

	}

	private Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case ALLQUESTIONLOADED:
				startQuestion();
				progressDialog.dismiss();
				break;
			case QUESTIONCANTLODED:
				progressDialog.dismiss();
				CommonStaticClass.showFinalAlert(con,
						"A problem occured during validation please try again");
				break;
			case DBSTOREDONE:
				progressDialog.dismiss();
				if (dbStored) {
					CommonStaticClass.showMyAlert(con, "Successful",
							"Database stored succcessfully");
				} else {
					CommonStaticClass.showMyAlert(con, "Failed",
							"Database stored failed please try again later");
				}
				break;
			}

		}
	};

	public void onBackPressed() {

	};
}
