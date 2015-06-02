package com.icddrb.datatransfertool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class LoginScreen extends BaseActivity {
	private Spinner userId;
	private EditText userName, userPass;
	private Button loginButton, exitButton;
	String sql = "Select * from tblUser where Active='Y' ORDER BY ID";
	ArrayList<String> users;
	ArrayAdapter<String> adapter1;
	private String userIdSelection = "";
	private Context con;
	private ProgressDialog progressDialog;
	protected static final int UPDATEFAILED = 0, UPDATEDONE = 1;
	private TextView tvVersion;
	private BroadcastReceiver mNetworkStateIntentReceiver;
	private IntentFilter mNetworkStateChangedFilter;

	private String mTypeName = "Unknown";
	private String mSubtypeName = "Unknown";
	private boolean mAvailable = false;
	
	
	
	 public void showNotification(){

		 Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

		 NotificationCompat.Builder mBuilder =
				    new NotificationCompat.Builder(this)
				    .setSmallIcon(R.drawable.ic_launcher)
				    .setContentTitle("Data Transfer Tool")
				    .setContentText("Touch to go to the transfer window")
				    .setSound(soundUri);

		 
		 
		 Intent resultIntent = new Intent(this, MainScreen.class);
		 
		 // Because clicking the notification opens a new ("special") activity, there's
		 // no need to create an artificial back stack.
		 PendingIntent resultPendingIntent =
		     PendingIntent.getActivity(
		     this,
		     0,
		     resultIntent,
		     PendingIntent.FLAG_UPDATE_CURRENT
		 );
		 
		 
		 
		 
		 mBuilder.setContentIntent(resultPendingIntent);
		 
		
		 int mNotificationId = 001;
		 // Gets an instance of the NotificationManager service
		 NotificationManager mNotifyMgr = 
		         (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		 // Builds the notification and issues it.
		 mNotifyMgr.notify(mNotificationId, mBuilder.build());
		 
		 
	    }

	
	
	

	/*
	 * public boolean onCreateOptionsMenu(final Menu menu) { final MenuInflater
	 * inflater = getMenuInflater(); inflater.inflate(R.menu.activity_main,
	 * menu); return true; }
	 * 
	 * public boolean onOptionsItemSelected(final MenuItem item) { switch
	 * (item.getItemId()) { case R.id.menu_settings: //
	 * CommonStaticClass.langBng = true; return true;
	 * 
	 * default: return super.onOptionsItemSelected(item); } }
	 */
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
		

	}

	private boolean ReadAssetID() {
		InputStream instream = null;

		try {
			String path = "/mnt/sdcard/Android/AssetID.txt";
			Log.e("path", "" + path);
			File f = new File(path);
			instream = new FileInputStream(f);// openFileInput("/mnt/sdcard/Android/AssetID.txt");

			if (instream != null) {

				InputStreamReader inputreader = new InputStreamReader(instream);
				BufferedReader buffreader = new BufferedReader(inputreader);

				String line = buffreader.readLine();
				Log.e("line", "" + line);
				if (line == null || !(line.length() > 1)) {
					return false;
				} else {
					CommonStaticClass.AssetID = line;
					return true;
				}

			}

			// close the file again

		} catch (Exception ex) {
			ex.printStackTrace();

			return false;
		} finally {
			if (instream != null) {
				try {
					instream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

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
					/*
					 * Log.i(LOGTAG, "Network Type: " + mTypeName +
					 * ", subtype: " + mSubtypeName + ", available: " +
					 * mAvailable);
					 */
					updateScreen();
				}
			}
		};

		setContentView(R.layout.loginlayout);
		con = this;

		// if(DatabaseHelper.getInstance() == null){
		// dbHelper = new DatabaseHelper(con);
		// dbHelper.openDataBase();
		// }
		users = new ArrayList<String>();
		users.add("");

		loadGui();
		loadData();
		
		/*if (mAvailable == false)
			CommonStaticClass.SetMobileDataEnabled(con, true);
		else {
			WifiManager wifiManager = (WifiManager) this
					.getSystemService(this.WIFI_SERVICE);
			wifiManager.setWifiEnabled(true);

			if (!wifiManager.isWifiEnabled()) {
				CommonStaticClass.showMyAlert(con, "Message",
						"Please Wait. Turning internet ON...");
				return;
			}
		}*/

	}

	
	private void loadGui() {
		// TODO Auto-generated method stub
		ReadAssetID();
		userId = (Spinner) findViewById(R.id.userId);
		userName = (EditText) findViewById(R.id.userName);
		userPass = (EditText) findViewById(R.id.userPass);

		loginButton = (Button) findViewById(R.id.loginButton);
		loginButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				String username = userName.getText().toString();
				String userpass = userPass.getText().toString();
				String userSpecificId = userIdSelection.length() > 0 ? userIdSelection
						.substring(0, userIdSelection.lastIndexOf(":") - 1)
						: "";

				if (userIdSelection.length() > 0 && username.length() > 0
						&& userpass.length() > 0) {
					validateThisUser(userSpecificId, username, userpass);
				} else {
					CommonStaticClass.showFinalAlert(con,
							"Please enter valid user information");
				}
			}
		});

		exitButton = (Button) findViewById(R.id.exitButton);
		exitButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.exit(0);
				//finish();
			}
		});

		adapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, users);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		userId.setAdapter(adapter1);
		userId.setOnItemSelectedListener(new spinItemSelectedListener());
	}

	private void loadData() {
		// TODO Auto-generated method stub
		/*users = new ArrayList<String>();
		users.add("");*/
		Cursor mCursor = null;
		try {
			mCursor = dbHelper.getQueryCursor(sql);
			if (mCursor.moveToFirst()) {
				do {
					users.add(mCursor.getString(mCursor.getColumnIndex("ID"))
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
			adapter1.notifyDataSetChanged();
		}

	}

	public class spinItemSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			if (parent == userId) {
				if (parent.getItemAtPosition(pos).toString().length() > 0) {
					userIdSelection = parent.getItemAtPosition(pos).toString();
					userName.setText(userIdSelection.substring(
							userIdSelection.lastIndexOf(":") + 2,
							userIdSelection.length()));
					// Log.e("User Name",userIdSelection);
					// Log.e("User Name Sp",userIdSelection.substring(userIdSelection.lastIndexOf(":")+1,userIdSelection.length()));
				} else {
					userIdSelection = "";
					userName.setText("");
				}
			}
		}

		public void onNothingSelected(AdapterView<?> parent) {
		}
	}

	private void validateThisUser(String userSpecificId, String username,
			String userpass) {
		// TODO Auto-generated method stub
		if (CommonStaticClass.AssetID.length() <= 0) {
			CommonStaticClass.showMyAlert(con, "Asset ID is Missing",
					"A problem occured during validation please try again");
			return;
		}
		Log.e("userSpecificId", userSpecificId);
		String validationSql = "Select * from tblUser Where ID = '"
				+ userSpecificId + "' AND Name = '" + username
				+ "' AND Pwd = '" + userpass + "'";
		Cursor mCursor = null;
		try {
			
			mCursor = dbHelper.getQueryCursor(validationSql);
			if (mCursor.getCount() > 0) {
				CommonStaticClass.userSpecificId = userSpecificId;
				// if(CommonStaticClass.userSpecificId.equalsIgnoreCase("0")){
				// updateTableEntry();
				// }
				//showNotification();
				
				Intent i = new Intent();
				i.setClassName(CommonStaticClass.pName, CommonStaticClass.pName
						+ ".MainScreen");
				startActivity(i);
				
				
			} else {
				CommonStaticClass
						.showFinalAlert(con,
								"Can not validate under this username and password please try again");
			}
		} catch (Exception e) {
			// TODO: handle exception
			CommonStaticClass.showFinalAlert(con,
					"A problem occured during validation please try again");
		} finally {
			
			if (mCursor != null)
				mCursor.close();
		}
	}

	public void onBackPressed() {
		super.onBackPressed();
		clearEveryThing();
	}

	private void clearEveryThing() {
		// TODO Auto-generated method stub
		CommonStaticClass.userSpecificId = "";

		// finish();
	}

	private Handler searchHandler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UPDATEDONE:
				if (progressDialog != null && progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
				Toast.makeText(con, "Update is done", Toast.LENGTH_LONG).show();
				break;
			case UPDATEFAILED:
				if (progressDialog != null && progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
				Toast.makeText(con, "Update failed", Toast.LENGTH_LONG).show();
				break;
			}

		}
	};
}