package com.icddrb.app.wbendLineenvsamplingd1;

import java.util.ArrayList;

import com.icddrb.app.wbendLineenvsamplingd1.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EditEntryMember extends BaseActivity {
	private ListView listentries;
	private Context con;
	private ArrayList<String> dID;
	private ArrayAdapter<String> myAdapter;
	protected static final int ALLQUESTIONLOADED = 1;
	protected static final int QUESTIONCANTLODED = 2;
	protected static final int DELETEDONE = 3;
	protected static final int DELETEFAILED = 4;
	private ProgressDialog progressDialog;
	private static final int CONTEXTMENU_DELETE = 11;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editlistmember);
		con = this;
		loadGui();
		loadDataToList();
	}
	

	protected void onStop() {
	    setResult(2);
	    super.onStop();
	}
	@Override
	protected void onDestroy() {
	    setResult(2);
	    super.onDestroy();
	}
	
	private void loadGui() {
		// TODO Auto-generated method stub
		dID = new ArrayList<String>();
		listentries = (ListView) findViewById(R.id.listentries);
		myAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_multiple_choice, dID);
		listentries.setAdapter(myAdapter);
		listentries.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v, int pos,
					long id) {
				// TODO Auto-generated method stub
				clearEveryThing();

				// Log.e("item","clicked");

				String memberid = dID.get(pos);
				CommonStaticClass.memberID = memberid;
				CommonStaticClass.isMember = true;
				// CommonStaticClass.participantType =
				// CommonStaticClass.dataId.substring(0, 1);
				CommonStaticClass.mode = CommonStaticClass.EDITMODE;

				if (CommonStaticClass.questionMap.size() == 0) {
					progressDialog = ProgressDialog.show(con, "Questions",
							"Please wait while loading questioniare");
					new Thread() {

						public void run() {
							loadQuestions();
						}
					}.start();
				}
				//AddSkipForMembers();
			}
		});
		listentries
				.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

					public void onCreateContextMenu(ContextMenu menu, View v,
							ContextMenuInfo menuInfo) {
						// TODO Auto-generated method stub
						if (CommonStaticClass.userSpecificId
								.equalsIgnoreCase("3")
								|| CommonStaticClass.userSpecificId
										.equalsIgnoreCase("5")) {
							menu.setHeaderTitle("Select");
							menu.add(0, CONTEXTMENU_DELETE, 0, "Delete this ID");
						}
					}
				});
	}

	public boolean onContextItemSelected(MenuItem aItem) {

		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) aItem
				.getMenuInfo();

		switch (aItem.getItemId()) {
		case CONTEXTMENU_DELETE:
			Log.e("menuInfo.position", "" + menuInfo.position);
			final String dataid = dID.get(menuInfo.position);

			progressDialog = ProgressDialog.show(con, "", "");

			new Thread() {

				public void run() {
					try {
						String sql1 = "Delete from tblMainQues where dataid='"
								+ dataid + "'";
						String sql2 = "Delete from tblAnthropometry where dataid='"
								+ dataid + "'";
						String sql3 = "Delete from tblHousehold where dataid='"
								+ dataid + "'";
						String sql4 = "Delete from tblMainQuesMC where dataid='"
								+ dataid + "'";
						String sql5 = "Delete from tblMainQuesMCThree where dataid='"
								+ dataid + "'";
						String sql6 = "Delete from tblMainQuesSC where dataid='"
								+ dataid + "'";
						if (dbHelper.executeDMLQuery(sql1)) {
							if (dbHelper.executeDMLQuery(sql2)) {
								if (dbHelper.executeDMLQuery(sql3)) {
									if (dbHelper.executeDMLQuery(sql4)) {
										if (dbHelper.executeDMLQuery(sql5)) {
											if (dbHelper.executeDMLQuery(sql6)) {
												Message msg = new Message();
												msg.what = DELETEDONE;
												handler.sendMessage(msg);
											}
										}
									}
								}
							}
						}
					} catch (Exception e) {
						// TODO: handle exception
						Message msg = new Message();
						msg.what = DELETEFAILED;
						handler.sendMessage(msg);
					}
				}
			}.start();

			return true; /* true means: "we handled the event". */

		}

		return false;

	}

	private void clearEveryThing() {
		// TODO Auto-generated method stub
		if (!CommonStaticClass.SLNOSTACK.isEmpty()) {
			CommonStaticClass.SLNOSTACK.clear();
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
		if (!CommonStaticClass.truetracker.isEmpty()) {
			CommonStaticClass.truetracker.clear();
		}
		CommonStaticClass.addCycleStarted = false;
		CommonStaticClass.memberID = "";
		CommonStaticClass.isMember = false;
		CommonStaticClass.previousDataFound = false;
		CommonStaticClass.totalHHMember = 1;
		CommonStaticClass.checker = false;
		CommonStaticClass.currentSLNo = 1;
		CommonStaticClass.participantType = "";
		CommonStaticClass.isChecked = false;
	}

	private void loadDataToList() {
		String sql = "Select memberid from tblDay1 Where dataid='"
				+ CommonStaticClass.dataId + "'";
		Cursor mCursor = null;
		try {
			mCursor = dbHelper.getQueryCursor(sql);
			dID.clear();
			if (mCursor.moveToFirst()) {
				do {
					dID.add(mCursor.getString((mCursor
							.getColumnIndex("memberid"))));
					
					/*CommonStaticClass.AssetID = mCursor.getString((mCursor
							.getColumnIndex("AssetId")));*/
				} while (mCursor.moveToNext());
			}
		} catch (Exception e) {
			// TODO: handle exception
			CommonStaticClass.showFinalAlert(con,
					"A problem occured please try later");
		} finally {
			if (mCursor != null)
				mCursor.close();

			myAdapter.notifyDataSetChanged();
		}
	}

	
	public boolean onCreateOptionsMenu(final Menu menu) {
		final MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.lang_menu, menu);
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
		case R.id.GotoHomeItem:
			CommonStaticClass.mode = "";
			setResult(2);
			finish();
			
			return true;
		case R.id.ExitItem:

			CommonStaticClass.mode = "";
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	@Override
	protected void onResume() {
	   // this.finish();
	    super.onResume();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	
	}

	private void AddSkipForMembers() {
		CommonStaticClass.qskipList.clear();

		// SKIP ADDITION
		String sql1 = "";
		sql1 = "Select * from tblFamilyMember where dataid='"
				+ CommonStaticClass.dataId + "' and memberid="
				+ CommonStaticClass.memberID;
		Cursor mCursor1 = null;
		int index = -1;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql1);
			if (mCursor1.moveToFirst()) {
				do {
					String column1 = "anysick";
					String column2 = "visitdoc";
					String column3 = "hospitalized";

					String a = mCursor1.getString(mCursor1
							.getColumnIndex(column1)) + "";
					String b = mCursor1.getString(mCursor1
							.getColumnIndex(column2)) + "";
					String c = mCursor1.getString(mCursor1
							.getColumnIndex(column3)) + "";

					if (a.equalsIgnoreCase("0") || a.equalsIgnoreCase("99")) {
						CommonStaticClass.qskipList.add("SecP1");
						nullifyWithInRange("SecP1", "SecP2");
					}
					if (b.equalsIgnoreCase("0") || b.equalsIgnoreCase("99")) {
						CommonStaticClass.qskipList.add("SecP2");
						nullifyWithInRange("SecP2", "SecP3");
					}
					if (c.equalsIgnoreCase("0") || c.equalsIgnoreCase("99")) {
						CommonStaticClass.qskipList.add("SecP3");
						nullifyWithInRange("SecP3", "p3_5");
					}

				} while (mCursor1.moveToNext());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}

	}

	
	/*public boolean onCreateOptionsMenu(final Menu menu) {
		final MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.lang_menu, menu);
		return true;
	}*/


	
	private void loadQuestions() {

		//String sql = "Select * from tblQuestion where SLNO>=3";
		String sql = "Select * from tblQuestion";
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

			String sqlForSec = "Select SLNo,Qvar from tblQuestion where  SLNO>=3 and Qvar  like 'sec%' order by SLNo";

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

	}

	private Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case ALLQUESTIONLOADED:
				progressDialog.dismiss();
				CommonStaticClass.isChecked = false;
				Intent i = new Intent(EditEntryMember.this,
						QListScreenForEdit.class);
				startActivity(i);
				break;
			case QUESTIONCANTLODED:
				progressDialog.dismiss();
				CommonStaticClass.showFinalAlert(con,
						"A problem occured question loading");
				break;
			case DELETEDONE:
				if (progressDialog != null && progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
				CommonStaticClass.showMyAlert(con, "Completed",
						"ID deleted successfully");

				if (dID != null & !dID.isEmpty()) {
					dID.clear();
					loadDataToList();
				}

				break;
			case DELETEFAILED:
				if (progressDialog != null && progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
				CommonStaticClass.showMyAlert(con, "InComplete",
						"ID can not be deleted");

				break;
			}

		}
	};
}
