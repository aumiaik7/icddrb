package com.icddrb.datatransfertool;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.icddrb.datatransfertool.LoginScreen.spinItemSelectedListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class MainScreen extends BaseActivity {
	Context con;
	ArrayList<String> mFileLocation = new ArrayList<String>();
	ArrayList<String> mFileName = new ArrayList<String>();
	private String studyIdSelection = "";
	protected static final int Send_SUCCESS = 1;
	protected static final int INCREASE = 3;
	protected static final int COPY_FAILED = 2;
	private ProgressDialog progressDialog, progressDoalog;
	private SharedPreferences app_preferences;

	ArrayList<String> _list;
	ArrayList<String> _dblist;
	ArrayList<DatabaseID> _DatabaseIdlist;
	public static final String EXT_SDCARDLOC_ForTab = "/mnt/extSdCard/";

	public static final String EXT_SDCARDLOC = "/mnt/sdcard/";
	private long mil;
	ArrayAdapter<String> listadapter;
	ArrayAdapter<String> dbadapter;
	private BroadcastReceiver mNetworkStateIntentReceiver;
	private IntentFilter mNetworkStateChangedFilter;

	private String mTypeName = "Unknown";
	private String mSubtypeName = "Unknown";
	private boolean mAvailable = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//Checking internet availability
		mNetworkStateChangedFilter = new IntentFilter();
		mNetworkStateChangedFilter
				.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

		mNetworkStateIntentReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				if (intent.getAction().equals(
						ConnectivityManager.CONNECTIVITY_ACTION)) {
					NetworkInfo info = intent
							.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
					mTypeName = info.getTypeName();
					mSubtypeName = info.getSubtypeName();
					mAvailable = info.isAvailable();
					
					updateScreen();
				}
			}
		};

		setContentView(R.layout.activity_main);

		con = this;
		loadgui();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}

	public boolean onCreateOptionsMenu(final Menu menu) {
		final MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(final MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_settings:
			for (int x = 0; x < dbadapter.getCount(); x++) {

				((ListView) findViewById(R.id.dblist)).setItemChecked(x, true);
			}
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public class spinItemSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			if (parent == ((Spinner) findViewById(R.id.spinner1))) {
				if (parent.getItemAtPosition(pos).toString().length() > 0) {
					studyIdSelection = parent.getItemAtPosition(pos).toString();

					if (studyIdSelection.length() > 0) {
						String StudyId = studyIdSelection.length() > 0 ? studyIdSelection
								.substring(0,
										studyIdSelection.lastIndexOf(":") - 1)
								: "";

						try {

							GetListofDatabase(StudyId);

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						Log.e("Study ID: ", studyIdSelection.substring(
								studyIdSelection.lastIndexOf(":") + 2,
								studyIdSelection.length()));

					} else {
						studyIdSelection = "";

					}
				}
			}
		}

		public void onNothingSelected(AdapterView<?> parent) {
		}
	}

	public void ExecuteDataTransfer() {
		String last_backup = app_preferences.getString("last_backup", "");
		String message = "";
		if (last_backup.length() > 0) {
			final Calendar mCalendar = Calendar.getInstance();
			mCalendar.setTimeInMillis(Long.parseLong(last_backup));
			String backuptakenAt = mCalendar.getTime().toString();
			message = "Last Data was sent at " + backuptakenAt
					+ " , send again?";
		} else {
			message = "No Data was transfered yet do you want transfer data?";
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(message);
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

				progressDialog = new ProgressDialog(con);
				progressDialog.setMax(5);
				progressDialog
						.setMessage("Sending data to CCD Data Server. Please wait.");
				progressDialog.setTitle("Sending");
				progressDialog
						.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				progressDialog.setCancelable(false);
				progressDialog.setIcon(R.drawable.waiting);

				progressDialog.show();

				/*
				 * progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				 * progressDialog = ProgressDialog .show(con, "Sending...",
				 * "Please wait 5 to 10 minutes.", true, false);
				 */

				// progressDialog.setCancelable(true);

				new Thread() {

					public void run() {

						Looper.prepare();

						TransferFile();
						Looper.loop();
					}

				}.start();

				dialog.dismiss();
			}
		});
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		AlertDialog alert = builder.create();
		alert.show();

	}

	private void TransferFile() {

		/*
		 * progressDialog = ProgressDialog .show(con, "Sending...",
		 * "Please wait 5 to 10 minutes."); progressDialog.setCancelable(false);
		 */
		mil = System.currentTimeMillis();
		/*
		 * progressDialog = ProgressDialog.show(con, "Sending...",
		 * "Please wait 5 to 10 minutes."); progressDialog.setCancelable(false);
		 */
		/*
		 * new Thread() {
		 * 
		 * @Override public void run() {
		 */
		SharedPreferences.Editor editor = app_preferences.edit();
		editor.putString("last_backup", mil + "");
		editor.commit();

		SparseBooleanArray checked = ((ListView) findViewById(R.id.dblist))
				.getCheckedItemPositions();
		_list = new ArrayList<String>();

		// Looper.prepare();
		for (int x = 0; x < checked.size(); x++) {

			if (checked.valueAt(x) == true) {
				String selectedName = (String) ((ListView) findViewById(R.id.dblist))
						.getItemAtPosition(checked.keyAt(x));

				_list.add(selectedName);
			}
		}

		try {
			final FileRead file = new FileRead();
			ArrayList<TransData> trans = new ArrayList<TransData>();
			trans = file.MakeInsertString(con, _list, _DatabaseIdlist, handler);

			
			// file.SendRequest(trans);
			if (mAvailable == true) {
				if (file.CallWebService(trans)) {
					CommonStaticClass.showMyAlert(con,"Message",
							"Data Successfully Sent to CCD Data Server.");
				} else {
					CommonStaticClass.showMyAlert(con,"Message",
							"Could not connect to server. Please try again.");
					
					
				}
			} else {
				CommonStaticClass.showMyAlert(con,"Message","No Internet Connectivity");
			}
			// Toast.makeText(con, "Transfered", 1000).show();
			// progressDialog.dismiss();
		} catch (Exception e) {
			CommonStaticClass.showMyAlert(con,"Message",e.getMessage().toString());
			// progressDialog.dismiss();
			//Toast.makeText(con, e.getMessage(), 1000).show();
			/*
			 * Message msg = new Message(); msg.what = COPY_FAILED;
			 * handler.sendMessage(msg);
			 */
		} finally {
			Message ms = new Message();
			ms.what = Send_SUCCESS;
			handler.sendMessage(ms);

		}

		// Looper.loop();
	}

	// }.start();

	private void GetListofDatabase(String StudyId) {
		// TODO Auto-generated method stub
		_DatabaseIdlist = new ArrayList<DatabaseID>();
		// _dblist = new ArrayList<String>();
		dbadapter.clear();
		Cursor mCursor = null;

		try {
			String sql = "Select Id, StudyId, DatabaseName, DBID from tblStudyDatabase WHERE StudyId ="
					+ StudyId;

			mCursor = dbHelper.getQueryCursor(sql);

			dbadapter.notifyDataSetChanged();
			if (mCursor.moveToFirst()) {
				do {
					DatabaseID db = new DatabaseID();
					db.setId(mCursor.getString(mCursor.getColumnIndex("Id")));
					db.setStudyId(mCursor.getString(mCursor
							.getColumnIndex("StudyId")));
					db.setDatabaseName(mCursor.getString(mCursor
							.getColumnIndex("DatabaseName")));
					db.setDBID(mCursor.getString(mCursor.getColumnIndex("DBID")));
					_DatabaseIdlist.add(db);
					_dblist.add(mCursor.getString(mCursor
							.getColumnIndex("DatabaseName")));

					/*
					 * _DatabaseIdlist.add(mCursor.getString(mCursor.getColumnIndex
					 * ("DataBaseID")));
					 * _DatabaseIdlist.add(mCursor.getString(mCursor
					 * .getColumnIndex("DataBaseID")));
					 */

				} while (mCursor.moveToNext());
			}
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("cursor", "is null");
		} finally {
			if (mCursor != null)
				mCursor.close();
			dbadapter.notifyDataSetChanged();
			//To solve the re-login problem i have commected out the following line.
			//dbHelper.close();
			
		}

	}

	private void loadgui() {
		_list = new ArrayList<String>();
		_dblist = new ArrayList<String>();
		_list.add("");
		_dblist.add("");

		app_preferences = PreferenceManager.getDefaultSharedPreferences(this);

		listadapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, _list);
		listadapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		((Spinner) findViewById(R.id.spinner1)).setAdapter(listadapter);
		((Spinner) findViewById(R.id.spinner1))
				.setOnItemSelectedListener(new spinItemSelectedListener());

		loadData();

		((Button) findViewById(R.id.btnTransfer))
				.setOnClickListener(new View.OnClickListener() {

					public void onClick(View v) {

						/*
						 * progressDialog = ProgressDialog .show(con, "Wait",
						 * "Please wait 5 to 10 minutes while transfering data"
						 * );
						 */
						/*
						 * new Thread() { public void run() { Looper.prepare();
						 */
						if (mAvailable == true) {
							ExecuteDataTransfer();
						} else {
							CommonStaticClass.DisplayToast(con,
									"No Internet Connectivity", 1);
						}
						/*
						 * Message ms = new Message(); ms.what = Send_SUCCESS;
						 * handler.sendMessage(ms); Looper.loop();
						 * 
						 * } }.start();
						 */

					}
				});

		dbadapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_multiple_choice, _dblist);

		((ListView) findViewById(R.id.dblist)).setAdapter(dbadapter);

		/*
		 * ((ListView) findViewById(R.id.dblist))
		 * .setOnItemLongClickListener(new OnItemLongClickListener() {
		 * 
		 * public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int
		 * pos, long arg3) {
		 * 
		 * 
		 * 
		 * return false; }
		 * 
		 * });
		 */
	}

	private void loadData() {
		// TODO Auto-generated method stub
		Cursor mCursor = null;
		try {
			String sql = "Select DISTINCT ID, Name from tblStudy";
			mCursor = dbHelper.getQueryCursor(sql);
			if (mCursor.moveToFirst()) {
				do {
					_list.add(mCursor.getString(mCursor.getColumnIndex("Id"))
							+ " : "
							+ mCursor.getString(mCursor.getColumnIndex("Name")));
				} while (mCursor.moveToNext());
			}
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("cursor", "is null");
		} finally {
			if (mCursor != null)
				mCursor.close();
			listadapter.notifyDataSetChanged();
		}

	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Send_SUCCESS:
				if (progressDialog != null && progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
				break;
			case COPY_FAILED:
				if (progressDialog != null && progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
				CommonStaticClass.showFinalAlert(con, "Copy Failed");
				break;

			case INCREASE:
				if (progressDialog.getProgress() == progressDialog.getMax()) {
					progressDialog.setMax(progressDialog.getMax() * 2);
				}
				progressDialog.incrementProgressBy(1);
				break;

			}

		}
	};

	@Override
	protected void onResume() {
		// Log.d(LOGTAG, "onResume");
		super.onResume();
		registerReceiver(mNetworkStateIntentReceiver,
				mNetworkStateChangedFilter);
		unregisterReceiver(mNetworkStateIntentReceiver);
		registerReceiver(mNetworkStateIntentReceiver,
				mNetworkStateChangedFilter);
	}

	@Override
	protected void onPause() {
		// Log.d(LOGTAG, "onPause");
		super.onPause();
		unregisterReceiver(mNetworkStateIntentReceiver);
	}

	private void updateScreen() {
		String m = "Network state:" + "\n" + "Network Type: " + mTypeName
				+ "\n" + "Network subtype: " + mSubtypeName + "\n"
				+ "Available: " + mAvailable;
		CommonStaticClass.DisplayToast(con, m, 1);

		/*
		 * statusField = (TextView) findViewById(R.id.networkStatus);
		 * statusField.setText("Network Type: " + mTypeName + "\n" +
		 * "Network subtype: " + mSubtypeName + "\n" + "Available: " +
		 * mAvailable);
		 */

	}
}
