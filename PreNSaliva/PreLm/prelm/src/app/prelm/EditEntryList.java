package app.prelm;

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
import app.prelm.db.DatabaseHelper;

import com.app.prelm.R;

public class EditEntryList extends BaseActivity {

	private ArrayList<String> dataIDlist;
	private ArrayList<DataItem> datalist;
	private ListView editlist;
	private QlistAdapter myAdapter;
	private Context con;
	public static final int CONTEXTMENU_ADDNEW = 11;
	private ProgressDialog progressDialog;
	final int ALLQUESTIONLOADED = 111;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		con = this;
		setContentView(R.layout.editentrylist);
		loadGui();
		// loadData();

	}

	private void loadData() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		if (!dataIDlist.isEmpty()) {
			dataIDlist.clear();
			datalist.clear();
		}

		String sql = "Select * from tblSamples";
		Cursor mCursor = null;
		try {
			mCursor = DatabaseHelper.getInstance(con).getQueryCursor(sql);
			if (mCursor.moveToFirst()) {
				do {

					dataIDlist.add(mCursor.getString(mCursor
							.getColumnIndex("dataid")));

					datalist.add(new DataItem(

							mCursor.getString(mCursor.getColumnIndex("childNo")),
							mCursor.getString(mCursor.getColumnIndex("q5")),
							mCursor.getString(mCursor.getColumnIndex("q6")),
							mCursor.getString(mCursor.getColumnIndex("q7")),
							mCursor.getString(mCursor.getColumnIndex("q8")),
							mCursor.getString(mCursor
									.getColumnIndex("q8_other")),
							mCursor.getString(mCursor.getColumnIndex("q41")),
							mCursor.getString(mCursor.getColumnIndex("q47")),
							mCursor.getString(mCursor.getColumnIndex("q48")),
							mCursor.getString(mCursor.getColumnIndex("q49")),
							mCursor.getString(mCursor.getColumnIndex("q50")),
							mCursor.getString(mCursor.getColumnIndex("q51")),
							mCursor.getString(mCursor
									.getColumnIndex("q52s1hour2")),
							mCursor.getString(mCursor
									.getColumnIndex("q52s2hour2")),
							mCursor.getString(mCursor
									.getColumnIndex("q52s3hour2")),
							mCursor.getString(mCursor
									.getColumnIndex("q52s4hour2")),
							mCursor.getString(mCursor
									.getColumnIndex("q52s5hour2")),
							mCursor.getString(mCursor
									.getColumnIndex("q52s6hour2")),
							mCursor.getString(mCursor
									.getColumnIndex("q53s1hour2")),
							mCursor.getString(mCursor
									.getColumnIndex("q53s2hour2")),
							mCursor.getString(mCursor
									.getColumnIndex("q53s3hour2")),
							mCursor.getString(mCursor
									.getColumnIndex("q53s4hour2")),
							mCursor.getString(mCursor
									.getColumnIndex("q53s5hour2")),
							mCursor.getString(mCursor
									.getColumnIndex("q53s6hour2")),
							mCursor.getString(mCursor
									.getColumnIndex("q54s1hour2")),
							mCursor.getString(mCursor
									.getColumnIndex("q54s2hour2")),
							mCursor.getString(mCursor
									.getColumnIndex("q54s3hour2")),
							mCursor.getString(mCursor
									.getColumnIndex("q54s4hour2")),
							mCursor.getString(mCursor
									.getColumnIndex("q54s5hour2")),
							mCursor.getString(mCursor
									.getColumnIndex("q54s6hour2")),
							mCursor.getString(mCursor
									.getColumnIndex("q55s1hour2")),
							mCursor.getString(mCursor
									.getColumnIndex("q55s2hour2")),
							mCursor.getString(mCursor
									.getColumnIndex("q55s3hour2")),
							mCursor.getString(mCursor
									.getColumnIndex("q55s4hour2")),
							mCursor.getString(mCursor
									.getColumnIndex("q55s5hour2")),
							mCursor.getString(mCursor
									.getColumnIndex("q55s6hour2")),
							mCursor.getString(mCursor
									.getColumnIndex("q56hour2")),
							mCursor.getString(mCursor
									.getColumnIndex("q56hour2_other")),
							mCursor.getString(mCursor
									.getColumnIndex("q57hour2")),
							mCursor.getString(mCursor.getColumnIndex("EntryBy")),
							mCursor.getString(mCursor
									.getColumnIndex("EntryDate")),
							mCursor.getString(mCursor.getColumnIndex("EditBy")),
							mCursor.getString(mCursor
									.getColumnIndex("EditDate"))));

					/*
					 * mCursor.getString(mCursor.getColumnIndex("childNo")),
					 * mCursor.getString(mCursor.getColumnIndex("q5")),
					 * mCursor.getString(mCursor.getColumnIndex("q6")),
					 * mCursor.getString(mCursor.getColumnIndex("q7")),
					 * mCursor.getString(mCursor.getColumnIndex("q8")),
					 * mCursor.getString(mCursor.getColumnIndex("q8_other")),
					 * mCursor.getString(mCursor.getColumnIndex("q9")),
					 * mCursor.getString(mCursor.getColumnIndex("q10")),
					 * mCursor.getString(mCursor.getColumnIndex("q11")),
					 * 
					 * mCursor.getString(mCursor.getColumnIndex("q12")),
					 * mCursor.getString(mCursor.getColumnIndex("q13")),
					 * mCursor.getString(mCursor.getColumnIndex("q14_hour5")),
					 * mCursor.getString(mCursor.getColumnIndex("q15_hour2")),
					 * mCursor.getString(mCursor.getColumnIndex("q15_hour5")),
					 * mCursor.getString(mCursor.getColumnIndex("q16_hour2")),
					 * mCursor.getString(mCursor.getColumnIndex("q16_hour5")),
					 * mCursor.getString(mCursor.getColumnIndex("q17_hour2")),
					 * mCursor.getString(mCursor.getColumnIndex("q17_hour5")),
					 * mCursor.getString(mCursor.getColumnIndex("q18_hour2")),
					 * mCursor.getString(mCursor.getColumnIndex("q18_hour5")),
					 * mCursor.getString(mCursor.getColumnIndex("q19")),
					 * mCursor.getString(mCursor.getColumnIndex("q20")),
					 * mCursor.getString(mCursor.getColumnIndex("q21")),
					 * mCursor.getString(mCursor.getColumnIndex("q22")),
					 * mCursor.getString(mCursor.getColumnIndex("q23")),
					 * mCursor.getString(mCursor.getColumnIndex("q24")),
					 * mCursor.getString(mCursor.getColumnIndex("q25")),
					 * mCursor.getString(mCursor.getColumnIndex("q26")),
					 * mCursor.getString(mCursor.getColumnIndex("q27")),
					 * mCursor.getString(mCursor.getColumnIndex("q28")),
					 * mCursor.getString(mCursor.getColumnIndex("q29")),
					 * mCursor.getString(mCursor.getColumnIndex("q30")),
					 * mCursor.getString(mCursor.getColumnIndex("q31s1hour2")),
					 * mCursor.getString(mCursor.getColumnIndex("q31s2hour2")),
					 * mCursor.getString(mCursor.getColumnIndex("q31s3hour2")),
					 * mCursor.getString(mCursor.getColumnIndex("q31s4hour2")),
					 * mCursor.getString(mCursor.getColumnIndex("q31s5hour2")),
					 * mCursor.getString(mCursor.getColumnIndex("q31s6hour2")),
					 * mCursor.getString(mCursor.getColumnIndex("q31s7hour5")),
					 * mCursor.getString(mCursor.getColumnIndex("q31s8hour5")),
					 * mCursor.getString(mCursor.getColumnIndex("q31s9hour5")),
					 * mCursor.getString(mCursor.getColumnIndex("q31s10hour5")),
					 * mCursor.getString(mCursor.getColumnIndex("q31s11hour5")),
					 * mCursor.getString(mCursor.getColumnIndex("q31s12hour5")),
					 * mCursor.getString(mCursor.getColumnIndex("q32s1hour2")),
					 * mCursor.getString(mCursor.getColumnIndex("q32s2hour2")),
					 * mCursor.getString(mCursor.getColumnIndex("q32s3hour2")),
					 * mCursor.getString(mCursor.getColumnIndex("q32s4hour2")),
					 * mCursor.getString(mCursor.getColumnIndex("q32s5hour2")),
					 * mCursor.getString(mCursor.getColumnIndex("q32s6hour2")),
					 * mCursor.getString(mCursor.getColumnIndex("q32s7hour5")),
					 * mCursor.getString(mCursor.getColumnIndex("q32s8hour5")),
					 * mCursor.getString(mCursor.getColumnIndex("q32s9hour5")),
					 * mCursor.getString(mCursor.getColumnIndex("q32s10hour5")),
					 * mCursor.getString(mCursor.getColumnIndex("q32s11hour5")),
					 * mCursor.getString(mCursor.getColumnIndex("q32s12hour5")),
					 * mCursor.getString(mCursor.getColumnIndex("q33s1hour2")),
					 * mCursor.getString(mCursor.getColumnIndex("q33s2hour2")),
					 * mCursor.getString(mCursor.getColumnIndex("q33s3hour2")),
					 * mCursor.getString(mCursor.getColumnIndex("q33s4hour2")),
					 * mCursor.getString(mCursor.getColumnIndex("q33s5hour2")),
					 * mCursor.getString(mCursor.getColumnIndex("q33s6hour2")),
					 * mCursor.getString(mCursor.getColumnIndex("q33s7hour5")),
					 * mCursor.getString(mCursor.getColumnIndex("q33s8hour5")),
					 * mCursor.getString(mCursor.getColumnIndex("q33s9hour5")),
					 * mCursor.getString(mCursor.getColumnIndex("q33s10hour5")),
					 * mCursor.getString(mCursor.getColumnIndex("q33s11hour5")),
					 * mCursor.getString(mCursor.getColumnIndex("q33s12hour5")),
					 * mCursor.getString(mCursor.getColumnIndex("q34s1hour2")),
					 * mCursor.getString(mCursor.getColumnIndex("q34s2hour2")),
					 * mCursor.getString(mCursor.getColumnIndex("q34s3hour2")),
					 * mCursor.getString(mCursor.getColumnIndex("q34s4hour2")),
					 * mCursor.getString(mCursor.getColumnIndex("q34s5hour2")),
					 * mCursor.getString(mCursor.getColumnIndex("q34s6hour2")),
					 * mCursor.getString(mCursor.getColumnIndex("q34s7hour5")),
					 * mCursor.getString(mCursor.getColumnIndex("q34s8hour5")),
					 * mCursor.getString(mCursor.getColumnIndex("q34s9hour5")),
					 * mCursor.getString(mCursor.getColumnIndex("q34s10hour5")),
					 * mCursor.getString(mCursor.getColumnIndex("q34s11hour5")),
					 * mCursor.getString(mCursor.getColumnIndex("q34s12hour5")),
					 * mCursor.getString(mCursor.getColumnIndex("q35hour2")),
					 * mCursor
					 * .getString(mCursor.getColumnIndex("q35hour2_other")),
					 * mCursor.getString(mCursor.getColumnIndex("q36hour2")),
					 * mCursor.getString(mCursor.getColumnIndex("q35hour5")),
					 * mCursor
					 * .getString(mCursor.getColumnIndex("q35hour5_other")),
					 * mCursor.getString(mCursor.getColumnIndex("q36hour5")),
					 * mCursor.getString(mCursor.getColumnIndex("q37")),
					 * mCursor.getString(mCursor.getColumnIndex("q37_other")),
					 * mCursor.getString(mCursor.getColumnIndex("q38")),
					 * mCursor.getString(mCursor.getColumnIndex("EntryBy")),
					 * mCursor.getString(mCursor.getColumnIndex("EntryDate")),
					 * mCursor.getString(mCursor.getColumnIndex("EditBy")),
					 * mCursor.getString(mCursor.getColumnIndex("EditDate"))));
					 */

				} while (mCursor.moveToNext());
			}
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("cursor", "is null");
		} finally {
			if (mCursor != null)
				mCursor.close();
		}
		// myAdapter.notifyDataSetChanged();
	}

	@Override
	protected void onResume() {
		super.onResume();
		progressDialog = ProgressDialog.show(con, "Loading",
				"Please wait while loading ");
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
	}

	private Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case ALLQUESTIONLOADED:
				myAdapter.notifyDataSetChanged();
				if (progressDialog != null)
					progressDialog.dismiss();
				break;
			}
		}
	};

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
				menu.setHeaderTitle("Menu to Add Child");
				menu.add(0, CONTEXTMENU_ADDNEW, 0, "ADD NEW CHILD");
			}
		});

	}

	//
	/*
	 * public boolean onContextItemSelected(MenuItem aItem) {
	 * 
	 * AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) aItem
	 * .getMenuInfo();
	 * 
	 * switch (aItem.getItemId()) { case CONTEXTMENU_ADDNEW: String dataid =
	 * dataIDlist.get(menuInfo.position); CommonStaticClass.dataId = dataid;
	 * CommonStaticClass.currentChildrenCount = getChildCount(dataid)+1;
	 * CommonStaticClass.numberofchildren =
	 * CommonStaticClass.currentChildrenCount; CommonStaticClass.mode =
	 * CommonStaticClass.SPECIALADD; Intent intnt = new
	 * Intent(EditEntryList.this,SampleCollector.class); startActivity(intnt);
	 * return true; true means: "we handled the event".
	 * 
	 * }
	 * 
	 * return false;
	 * 
	 * }
	 */

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

	/*
	 * private int getChildCount(String dataid) { String sql =
	 * "Select NumChildren from tblMainQues WHERE dataid = '" + dataid +"'";
	 * Cursor mCursor = null; int i = 0; try{ mCursor =
	 * DatabaseHelper.getInstance(con).getQueryCursor(sql);
	 * if(mCursor.moveToFirst()){ do{ i =
	 * Integer.parseInt(mCursor.getString(mCursor
	 * .getColumnIndex("NumChildren"))); Log.e("iiii","ooo "+i);
	 * }while(mCursor.moveToNext()); } }catch (Exception e) { // TODO: handle
	 * exception Log.e("cursor","is null"); }finally{ if(mCursor!=null)
	 * mCursor.close(); } return i; }
	 * 
	 * }
	 */
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
