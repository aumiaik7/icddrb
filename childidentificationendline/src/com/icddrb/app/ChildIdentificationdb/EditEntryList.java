package com.icddrb.app.ChildIdentificationdb;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import com.icddrb.app.Childidentificationendline.R;
import com.icddrb.app.ChildIdentificationdb.db.DatabaseHelper;//.db.DatabaseHelper;

public class EditEntryList extends BaseActivity {

	private ArrayList<String> dataIDlist;
	private ArrayList<DataItem> datalist;
	private ListView editlist;
	private QlistAdapter myAdapter;
	private Context con;
	public static final int CONTEXTMENU_ADDNEW = 11;
	private ProgressDialog progressDialog;
	final int ALLQUESTIONLOADED = 111;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		con = this;
		setContentView(R.layout.editentrylist);
		loadGui();
		

	}


	private void loadData() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		if (!dataIDlist.isEmpty() || dataIDlist.size()<=0) {

			dataIDlist.clear();
			datalist.clear();
		}

		String sql = "Select * from tblSamples";
		Cursor mCursor = null;
		try {
			mCursor = DatabaseHelper.getInstance(con).getQueryCursor(sql);
			if (mCursor.moveToFirst()) {
				do {
					// if(mCursor.getString(mCursor.getColumnIndex("sampleid")).contains("H1")||mCursor.getString(mCursor.getColumnIndex("sampleid")).contains("B1")||mCursor.getString(mCursor.getColumnIndex("sampleid")).contains("X1")||mCursor.getString(mCursor.getColumnIndex("sampleid")).contains("P1")){
					dataIDlist.add(mCursor.getString(mCursor
							.getColumnIndex("dataid")));
					// String data =
					// "sampleid: "+mCursor.getString(mCursor.getColumnIndex("sampleid"))+
					// " randomid: "+mCursor.getString(mCursor.getColumnIndex("randomid"))+
					// " sampletype: "+mCursor.getString(mCursor.getColumnIndex("sampletype"))+
					// " q5: "+mCursor.getString(mCursor.getColumnIndex("q5"))+
					// " q6: "+mCursor.getString(mCursor.getColumnIndex("q6"))+
					// " q7: "+mCursor.getString(mCursor.getColumnIndex("q7"))+
					// " q8: "+mCursor.getString(mCursor.getColumnIndex("q8"))+
					// " q9: "+mCursor.getString(mCursor.getColumnIndex("q9"))+
					// " q10: "+mCursor.getString(mCursor.getColumnIndex("q10"))+
					// " q11: "+mCursor.getString(mCursor.getColumnIndex("q11"))+
					// " q12: "+mCursor.getString(mCursor.getColumnIndex("q12"))+
					// " q12Other: "+mCursor.getString(mCursor.getColumnIndex("q12Other"))+
					// " q13: "+mCursor.getString(mCursor.getColumnIndex("q13"))+
					// " q14: "+mCursor.getString(mCursor.getColumnIndex("q14"))+
					// " q15: "+mCursor.getString(mCursor.getColumnIndex("q15"))+
					// " q16: "+mCursor.getString(mCursor.getColumnIndex("q16"))+
					// " q17: "+mCursor.getString(mCursor.getColumnIndex("q17"))+
					// " q18: "+mCursor.getString(mCursor.getColumnIndex("q18"))+
					// " EntryBy: "+mCursor.getString(mCursor.getColumnIndex("EntryBy"))+
					// " EntryDate: "+mCursor.getString(mCursor.getColumnIndex("EntryDate"))+
					// " EditBy: "+mCursor.getString(mCursor.getColumnIndex("EditBy"))+
					// " EditDate: "+mCursor.getString(mCursor.getColumnIndex("EditDate"));
					datalist.add(new DataItem(
							mCursor.getString(mCursor.getColumnIndex("childNo")),

							mCursor.getString(mCursor
									.getColumnIndex("numberofchildren")),
							mCursor.getString(mCursor.getColumnIndex("q12")),
							mCursor.getString(mCursor.getColumnIndex("q13")),
							mCursor.getString(mCursor.getColumnIndex("q14")),
							mCursor.getString(mCursor.getColumnIndex("q15")),
							mCursor.getString(mCursor.getColumnIndex("q16")),
							mCursor.getString(mCursor.getColumnIndex("q17")),
							mCursor.getString(mCursor
									.getColumnIndex("q18_days")),
							mCursor.getString(mCursor
									.getColumnIndex("q18_months")),
							mCursor.getString(mCursor.getColumnIndex("q18")),
							mCursor.getString(mCursor
									.getColumnIndex("q19_no_of_time")),
							mCursor.getString(mCursor.getColumnIndex("q19")),
							mCursor.getString(mCursor
									.getColumnIndex("q20_time1")),
							mCursor.getString(mCursor
									.getColumnIndex("q20_other_time1")),
							mCursor.getString(mCursor
									.getColumnIndex("q21_days_time1")),
							mCursor.getString(mCursor
									.getColumnIndex("q21_dontknow_time1")),
							mCursor.getString(mCursor
									.getColumnIndex("q20_time2")),
							mCursor.getString(mCursor
									.getColumnIndex("q20_other_time2")),
							mCursor.getString(mCursor
									.getColumnIndex("q21_days_time2")),
							mCursor.getString(mCursor
									.getColumnIndex("q21_dontknow_time2")),
							mCursor.getString(mCursor
									.getColumnIndex("q20_time3")),
							mCursor.getString(mCursor
									.getColumnIndex("q20_other_time3")),
							mCursor.getString(mCursor
									.getColumnIndex("q21_days_time3")),
							mCursor.getString(mCursor
									.getColumnIndex("q21_dontknow_time3")),
							mCursor.getString(mCursor
									.getColumnIndex("q20_time4")),
							mCursor.getString(mCursor
									.getColumnIndex("q20_other_time4")),
							mCursor.getString(mCursor
									.getColumnIndex("q21_days_time4")),
							mCursor.getString(mCursor
									.getColumnIndex("q21_dontknow_time4")),
							mCursor.getString(mCursor
									.getColumnIndex("q20_time5")),
							mCursor.getString(mCursor
									.getColumnIndex("q20_other_time5")),
							mCursor.getString(mCursor
									.getColumnIndex("q21_days_time5")),
							mCursor.getString(mCursor
									.getColumnIndex("q21_dontknow_time5")),
							mCursor.getString(mCursor.getColumnIndex("q22")),
							mCursor.getString(mCursor.getColumnIndex("q23")),
							mCursor.getString(mCursor.getColumnIndex("q24")),
							mCursor.getString(mCursor.getColumnIndex("q25")),
							mCursor.getString(mCursor
									.getColumnIndex("q26_days")),
							mCursor.getString(mCursor
									.getColumnIndex("q26_months")),
							mCursor.getString(mCursor.getColumnIndex("q26")),
							mCursor.getString(mCursor.getColumnIndex("q27")),
							mCursor.getString(mCursor
									.getColumnIndex("q27_other")),
							mCursor.getString(mCursor.getColumnIndex("q28")),
							mCursor.getString(mCursor.getColumnIndex("q29")),

							mCursor.getString(mCursor.getColumnIndex("EntryBy")),
							mCursor.getString(mCursor
									.getColumnIndex("EntryDate")),
							mCursor.getString(mCursor.getColumnIndex("EditBy")),
							mCursor.getString(mCursor
									.getColumnIndex("EditDate")), mCursor
									.getString(mCursor.getColumnIndex("q30A")),
							mCursor.getString(mCursor.getColumnIndex("q30B")),
							mCursor.getString(mCursor.getColumnIndex("q30C")),
							mCursor.getString(mCursor.getColumnIndex("q30D")),
							mCursor.getString(mCursor.getColumnIndex("q30E")),

							mCursor.getString(mCursor.getColumnIndex("q31A")),
							mCursor.getString(mCursor.getColumnIndex("q31B")),
							mCursor.getString(mCursor.getColumnIndex("q31C")),
							mCursor.getString(mCursor.getColumnIndex("q31D")),
							mCursor.getString(mCursor.getColumnIndex("q31E")),

							mCursor.getString(mCursor.getColumnIndex("q32A")),
							mCursor.getString(mCursor.getColumnIndex("q32B")),
							mCursor.getString(mCursor.getColumnIndex("q32C")),
							mCursor.getString(mCursor.getColumnIndex("q32D")),
							mCursor.getString(mCursor.getColumnIndex("q32E")),

							mCursor.getString(mCursor.getColumnIndex("q33A")),
							mCursor.getString(mCursor.getColumnIndex("q33B")),
							mCursor.getString(mCursor.getColumnIndex("q33C")),
							/*mCursor.getString(mCursor.getColumnIndex("q33D")),
							mCursor.getString(mCursor.getColumnIndex("q33E")),*/

							mCursor.getString(mCursor.getColumnIndex("q34A")),
							mCursor.getString(mCursor.getColumnIndex("q34B")),
							mCursor.getString(mCursor.getColumnIndex("q34C")),
							mCursor.getString(mCursor.getColumnIndex("q34D")),
							mCursor.getString(mCursor.getColumnIndex("q34E")),

							mCursor.getString(mCursor.getColumnIndex("q35A")),
							mCursor.getString(mCursor.getColumnIndex("q35B")),
							mCursor.getString(mCursor.getColumnIndex("q35C")),
							mCursor.getString(mCursor.getColumnIndex("q35D")),
							mCursor.getString(mCursor.getColumnIndex("q35E")),

							mCursor.getString(mCursor.getColumnIndex("q36A")),
							mCursor.getString(mCursor.getColumnIndex("q36B")),
							mCursor.getString(mCursor.getColumnIndex("q36C")),
							mCursor.getString(mCursor.getColumnIndex("q36D")),
							mCursor.getString(mCursor.getColumnIndex("q36E")),

							mCursor.getString(mCursor.getColumnIndex("q37A")),
							mCursor.getString(mCursor.getColumnIndex("q37B")),
							mCursor.getString(mCursor.getColumnIndex("q37C")),
							mCursor.getString(mCursor.getColumnIndex("q37D")),
							mCursor.getString(mCursor.getColumnIndex("q37E")),

							mCursor.getString(mCursor.getColumnIndex("q38A")),
							mCursor.getString(mCursor.getColumnIndex("q38B")),
							mCursor.getString(mCursor.getColumnIndex("q38C")),
							mCursor.getString(mCursor.getColumnIndex("q38D")),
							mCursor.getString(mCursor.getColumnIndex("q38E")),

							mCursor.getString(mCursor.getColumnIndex("q39A")),
							mCursor.getString(mCursor.getColumnIndex("q39B")),
							mCursor.getString(mCursor.getColumnIndex("q39C")),
							mCursor.getString(mCursor.getColumnIndex("q39D")),
							mCursor.getString(mCursor.getColumnIndex("q39E")),

							mCursor.getString(mCursor.getColumnIndex("q40A")),
							mCursor.getString(mCursor.getColumnIndex("q40B")),
							mCursor.getString(mCursor.getColumnIndex("q40C")),
							mCursor.getString(mCursor.getColumnIndex("q40D")),
							mCursor.getString(mCursor.getColumnIndex("q40E")),

							mCursor.getString(mCursor.getColumnIndex("q41A")),
							mCursor.getString(mCursor.getColumnIndex("q41B")),
							mCursor.getString(mCursor.getColumnIndex("q41C")),
							mCursor.getString(mCursor.getColumnIndex("q41D")),
							mCursor.getString(mCursor.getColumnIndex("q41E")),

							mCursor.getString(mCursor.getColumnIndex("q42_Days")), 
									
						    mCursor.getString(mCursor.getColumnIndex("q43_Days")),
											
						    mCursor.getString(mCursor.getColumnIndex("q42_weeks")), 
						    mCursor.getString(mCursor.getColumnIndex("q43_weeks")
											
											
											)));
					// }
				} while (mCursor.moveToNext());

			
			}
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("cursor", "is null");
		} finally {
			if (mCursor != null)
				mCursor.close();
		}

	}

	private Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case ALLQUESTIONLOADED:
				myAdapter.notifyDataSetChanged();
				progressDialog.dismiss();
				break;
			}
		}
	};

	@Override
	protected void onResume() {
		super.onResume();

		//progressDialog = ProgressDialog.show(con, "Questions",
			//	"Please wait while loading ");
		//new Thread() {

		//	public void run() {
			//	Looper.prepare();

		progressDialog = ProgressDialog.show(con, "Loading","Please wait while loading ");
		new Thread() {

			public void run() {
				Looper.prepare();
				
				loadData();
				Message ms = new Message();
				ms.what = ALLQUESTIONLOADED;
				handler.sendMessage(ms);
				Looper.loop();
			}
		}.start();
			//	Message ms = new Message();
			//	ms.what = ALLQUESTIONLOADED;
			//	handler.sendMessage(ms);
			//	Looper.loop();
			//}
		//}.start();
	}

	private void loadGui() {
		// TODO Auto-generated method stub
		dataIDlist = new ArrayList<String>();
		datalist = new ArrayList<DataItem>();

		editlist = (ListView) findViewById(R.id.editlist);
		myAdapter = new QlistAdapter(this, dataIDlist, datalist);

		editlist.setAdapter(myAdapter);
		editlist.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v, int pos,
					long id) {
				// TODO Auto-generated method stub

				String dataid = dataIDlist.get(pos);
				CommonStaticClass.itemToEdite = datalist.get(pos);
				if (CommonStaticClass.itemToEdite != null) {
					// if(CommonStaticClass.itemToEdite.getSampleid().contains("H1")||CommonStaticClass.itemToEdite.getSampleid().contains("B1")||CommonStaticClass.itemToEdite.getSampleid().contains("X1")||CommonStaticClass.itemToEdite.getSampleid().contains("P1")){
					CommonStaticClass.dataId = dataid;
					CommonStaticClass.currentChildrenCount = Integer
							.parseInt(datalist.get(pos).getChildNo());
					CommonStaticClass.mode = CommonStaticClass.EDITMODE;
					Intent intnt = new Intent(EditEntryList.this,
							SampleCollector.class);
					startActivity(intnt);
					// }
				}
			}
		});
		editlist.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
			public void onCreateContextMenu(ContextMenu menu, View v,
					ContextMenuInfo menuInfo) {
				// TODO Auto-generated method stub
				menu.setHeaderTitle("Select");
				menu.add(0, CONTEXTMENU_ADDNEW, 0, "ADD NEW CHILD");
			}
		});

	}

	@Override
	public boolean onContextItemSelected(MenuItem aItem) {

		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) aItem
				.getMenuInfo();

		switch (aItem.getItemId()) {
		case CONTEXTMENU_ADDNEW:
			String dataid = dataIDlist.get(menuInfo.position);
			if (getChildCount(dataid) != -1
					&& !((getChildCount(dataid) + 1) > 3)) {
				CommonStaticClass.dataId = dataid;
				CommonStaticClass.currentChildrenCount = getChildCount(dataid) + 1;
				Log.e("getChildCount(dataid)", "" + getChildCount(dataid));
				Log.e("CommonStaticClass.currentChildrenCount", ""
						+ CommonStaticClass.currentChildrenCount);
				CommonStaticClass.numberofchildren = CommonStaticClass.currentChildrenCount;
				CommonStaticClass.mode = CommonStaticClass.SPECIALADD;
				Intent intnt = new Intent(EditEntryList.this,
						SampleCollector.class);
				startActivity(intnt);
			} else {
				CommonStaticClass.showMyAlert(con, "Error",
						"You can not enter new child for this record");
			}
			return true; /* true means: "we handled the event". */

		}

		return false;

	}

	private int getChildCount(String dataid) {
		String sql = "Select NumChildren from tblMainQues WHERE dataid = '"
				+ dataid + "'";
		String sql1 = "Select (childNo) from tblSamples WHERE dataid = '"
				+ dataid + "'";
		int childMaxIndex = 0;
		Cursor mCursor = null;
		int i = 0;
		try {
			mCursor = DatabaseHelper.getInstance(con).getQueryCursor(sql);
			if (mCursor.moveToFirst()) {
				do {
					i = Integer.parseInt(mCursor.getString(mCursor
							.getColumnIndex("NumChildren")));
					Log.e("iiii", "ooo " + i);
				} while (mCursor.moveToNext());
			}
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("cursor", "is null");
		} finally {
			if (mCursor != null)
				mCursor.close();

			if (i <= 3) {
				try {
					mCursor = DatabaseHelper.getInstance(con).getQueryCursor(
							sql1);
					if (mCursor.moveToFirst()) {
						do {
							if (childMaxIndex <= Integer.parseInt(mCursor
									.getString(mCursor
											.getColumnIndex("childNo")))) {
								childMaxIndex = Integer.parseInt(mCursor
										.getString(mCursor
												.getColumnIndex("childNo")));
							}
							Log.e("childMaxIndex", "childMaxIndex "
									+ childMaxIndex);
						} while (mCursor.moveToNext());
					}
				} catch (Exception e) {
					// TODO: handle exception
					Log.e("cursor", "is null");
				} finally {
					if (mCursor != null)
						mCursor.close();

					Log.e("childMaxIndex", "childMaxIndex " + childMaxIndex);
					if (childMaxIndex < i) {
						i = childMaxIndex;
						return i;
					}
				}
			}

		}
		return -1;
	}

}
