package app.stoolsampleendline;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import app.stoolsampleendline.db.DatabaseHelper;

import com.app.stoolsampleendline.R;

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
		//loadData();

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
							mCursor.getString(mCursor.getColumnIndex("q5")),
							mCursor.getString(mCursor.getColumnIndex("q6")),
							mCursor.getString(mCursor.getColumnIndex("q7s1")),
							mCursor.getString(mCursor.getColumnIndex("q7s2")),
							mCursor.getString(mCursor.getColumnIndex("q7s3")),
							mCursor.getString(mCursor.getColumnIndex("q7s4")),
							mCursor.getString(mCursor.getColumnIndex("q7s5")),
							mCursor.getString(mCursor.getColumnIndex("q7s6")),
							mCursor.getString(mCursor.getColumnIndex("q8s1")),
							mCursor.getString(mCursor.getColumnIndex("q8s2")),
							mCursor.getString(mCursor.getColumnIndex("q8s3")),
							mCursor.getString(mCursor.getColumnIndex("q8s4")),
							mCursor.getString(mCursor.getColumnIndex("q8s5")),
							mCursor.getString(mCursor.getColumnIndex("q8s6")),
							mCursor.getString(mCursor.getColumnIndex("q10s1")),
							mCursor.getString(mCursor.getColumnIndex("q10s2")),
							mCursor.getString(mCursor.getColumnIndex("q10s3")),
							mCursor.getString(mCursor.getColumnIndex("q10s4")),
							mCursor.getString(mCursor.getColumnIndex("q10s5")),
							mCursor.getString(mCursor.getColumnIndex("q10s6")),
							mCursor.getString(mCursor.getColumnIndex("q11")),
							mCursor.getString(mCursor
									.getColumnIndex("q11_other")),
							mCursor.getString(mCursor.getColumnIndex("q12")),
							mCursor.getString(mCursor.getColumnIndex("q13")),
							mCursor.getString(mCursor.getColumnIndex("q14")),
							mCursor.getString(mCursor.getColumnIndex("q15")),
							mCursor.getString(mCursor.getColumnIndex("q16")),
							mCursor.getString(mCursor.getColumnIndex("q17")),
							mCursor.getString(mCursor
									.getColumnIndex("q17_other")),
							mCursor.getString(mCursor.getColumnIndex("q18")),
							mCursor.getString(mCursor
									.getColumnIndex("q18_other")),
							mCursor.getString(mCursor.getColumnIndex("q19")),
							mCursor.getString(mCursor.getColumnIndex("q20")),
							mCursor.getString(mCursor.getColumnIndex("q21")),
							mCursor.getString(mCursor.getColumnIndex("q22")),
							mCursor.getString(mCursor.getColumnIndex("q23")),
							mCursor.getString(mCursor.getColumnIndex("q23_other")),
							mCursor.getString(mCursor.getColumnIndex("q24")),
							mCursor.getString(mCursor.getColumnIndex("q25")),
							mCursor.getString(mCursor.getColumnIndex("q26")),
							mCursor.getString(mCursor.getColumnIndex("EntryBy")),
							mCursor.getString(mCursor
									.getColumnIndex("EntryDate")),
							mCursor.getString(mCursor.getColumnIndex("EditBy")),
							mCursor.getString(mCursor
									.getColumnIndex("EditDate"))));

				} while (mCursor.moveToNext());
			}
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("cursor", "is null");
		} finally {
			if (mCursor != null)
				mCursor.close();
		}
		//myAdapter.notifyDataSetChanged();
	}

	@Override
	protected void onResume() {
		super.onResume();
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
	}
	private Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case ALLQUESTIONLOADED:
				myAdapter.notifyDataSetChanged();
				if(progressDialog!=null)
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
