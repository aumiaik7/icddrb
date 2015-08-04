package com.icddrb.app.hbislinelist;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

import com.icddrb.app.hbislinelist.R;

import com.icddrb.app.hbislinelist.datatransfertool.FileRead;
import com.icddrb.app.hbislinelist.datatransfertool.TransData;
import com.icddrb.app.hbislinelist.db.DatabaseHelper;
import com.icddrb.app.hbislinelist.schedulebackup.DBCopier;
import com.icddrb.app.hbislinelist.schedulebackup.ScheduleBackup;


import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import net.sqlcipher.Cursor;


public class MenuScreen extends BaseActivity {
	protected static final int ALLQUESTIONLOADED = 1;
	protected static final int QUESTIONCANTLODED = 2;
	protected static final int DBSTOREDONE = 11;
	private Button addButton, editButton;
	private ProgressDialog progressDialog;
	private Context con;
	private boolean dbStored = false;
	private SharedPreferences app_preferences;
	public static final int INCREASE = 3;
	protected static final int Send_SUCCESS = 4;
	protected static final int COPY_FAILED = 5;
	private PendingIntent pendingIntent;
	private AlarmManager manager;
	private boolean alarm = false;

	private boolean mAvailable = false;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menulayout);
		con = this;
		setTheme(R.style.AppTheme);
		loadGui();

		/*Intent alarmIntent = new Intent(this, ScheduleBackup.class);
		alarmIntent.putExtra("dbpath", DatabaseHelper.DB_PATH);
		alarmIntent.putExtra("dbname", CommonStaticClass.DB);
		alarmIntent.putExtra("dbpass", DatabaseHelper.getpw());
		pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);

		startAlarm();*/

	}

	public void startAlarm() {
		manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		long interval = (24*60*60)*1000; // 10 seconds

		manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
		Toast.makeText(this, "Auto Backup Started", Toast.LENGTH_SHORT).show();
	}
	public void cancelAlarm() {
		if (manager != null) {
			manager.cancel(pendingIntent);
			Toast.makeText(this, "Auto Backup Stoppped", Toast.LENGTH_SHORT).show();
		}

	}

	private void loadGui() {
		// TODO Auto-generated method stub
		app_preferences = PreferenceManager.getDefaultSharedPreferences(this);
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
				//CommonStaticClass.mode = CommonStaticClass.EDITMODE;
//				Intent i = new Intent();
//				i.setClassName(CommonStaticClass.pName, CommonStaticClass.pName
//						+ ".EditEntry");
//				i.setClassName(CommonStaticClass.pName, CommonStaticClass.pName
//						+ ".EditEntry");
//				startActivity(i);
				
				///Edit mode with all questions
				CommonStaticClass.mode = CommonStaticClass.EDITMODE;
				CommonStaticClass.subEditMode = 0;
				Intent i = new Intent();
				i.setClassName(CommonStaticClass.pName, CommonStaticClass.pName
						+ ".EditEntry");
				startActivity(i);
				
				
				
				//Edit mode with Sub section
				/*CommonStaticClass.subEditMode = 1;
				clearEveryThing();
				Intent i = new Intent();
				i.setClassName(CommonStaticClass.pName, CommonStaticClass.pName
						+ ".subedit");
				startActivity(i);*/
	
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
			
		case R.id.DTMenuItem:
			if (isNetworkAvailable())
			{
				/*Toast.makeText(this, "Network Has", Toast.LENGTH_LONG).show();*/
				
//				new hasInternet().execute("");
				showTransDialog();
				
			}
			else
			{
				/*new AlertDialog.Builder(con).setTitle("Network Error!!!")
				.setMessage("Network is not Available")
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.dismiss();
					}
				}).setCancelable(false).show();*/
				new Thread() {

					public void run() {



//						TransferFile();
						try {
							new DBCopier(con).copyDataBaseToSdcard(dbHelper.DB_PATH, CommonStaticClass.DB);
						} catch (IOException e) {
							e.printStackTrace();
						}

					}

				}.start();
			}
			return true;
			case R.id.Alarm:
				/*if (!alarm)
				{
				*//*Toast.makeText(this, "Network Has", Toast.LENGTH_LONG).show();*//*

//				new hasInternet().execute("");
					alarm = true;
					startAlarm();


				}
				else
				{
					alarm = false;*/
				cancelAlarm();
//				}
				return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	public void showTransDialog()
	{

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
		AlertDialog.Builder builder = new AlertDialog.Builder(con);
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
				//progressDialog.setIcon(R.drawable.waiting);

				progressDialog.show();
				mAvailable = true;
				//TransferFile();
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
	
	private class hasInternet extends AsyncTask<String, Void, String> {
	    @Override
	    protected String doInBackground(String... urls) {
	    	String response = "";
	    	try
		    {
		        HttpURLConnection urlc = (HttpURLConnection) (new URL("http://www.google.com").openConnection());
		        urlc.setRequestProperty("User-Agent", "Test");
		        urlc.setRequestProperty("Connection", "close");
		        urlc.setConnectTimeout(3000); //choose your own timeframe
		        urlc.setReadTimeout(4000); //choose your own timeframe
		        urlc.connect();
		        response = urlc.getResponseCode()+"";
		        //int networkcode2 = urlc.getResponseCode();
		        return response;
		    } catch (IOException e)
		    {
		        return "-1";  //connectivity exists, but no internet.
		    }
	}
	    	
	    
	    
	    @Override
	    protected void onPostExecute(String result) {
	    	if(!result.equalsIgnoreCase("-1"))
	    	{
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
	    		AlertDialog.Builder builder = new AlertDialog.Builder(con);
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
	    				//progressDialog.setIcon(R.drawable.waiting);

	    				progressDialog.show();
	    				mAvailable = true;
	    				//TransferFile();
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
	    	else
	    	{
	    		Toast.makeText(getApplicationContext(), "Internet No Has", Toast.LENGTH_LONG).show();
	    	}
	    }
	}
	
	private void TransferFile() {

		/*
		 * progressDialog = ProgressDialog .show(con, "Sending...",
		 * "Please wait 5 to 10 minutes."); progressDialog.setCancelable(false);
		 */
		
		/*
		 * progressDialog = ProgressDialog.show(con, "Sending...",
		 * "Please wait 5 to 10 minutes."); progressDialog.setCancelable(false);
		 */
		/*
		 * new Thread() {
		 * 
		 * @Override public void run() {
		 * 
		 */
		SharedPreferences.Editor editor = app_preferences.edit();
		editor.putString("last_backup", System.currentTimeMillis() + "");
		editor.commit();

		/*SparseBooleanArray checked = ((ListView) findViewById(R.id.dblist))
				.getCheckedItemPositions();
		_list = new ArrayList<String>();

		// Looper.prepare();
		for (int x = 0; x < checked.size(); x++) {

			if (checked.valueAt(x) == true) {
				String selectedName = (String) ((ListView) findViewById(R.id.dblist))
						.getItemAtPosition(checked.keyAt(x));

				_list.add(selectedName);
			}
		}*/

		try {
			CommonStaticClass.entryUdpated = 0;
			final FileRead file = new FileRead();
			//ArrayList<TransData> trans = new ArrayList<TransData>();
			ArrayList<TransData> trans = new ArrayList<TransData>();
			trans = file.MakeInsertString(con,dbHelper, handler);

			
			// file.SendRequest(trans);
			if (mAvailable == true) {
				if (file.CallWebService(trans)) {
					if(CommonStaticClass.entryUdpated==0)
					{
						CommonStaticClass.showMyAlert(con,"Message",
								"No New Data Avaible For CCD Data Server");
					}
					else
					CommonStaticClass.showMyAlert(con,"Message",
							"Data Successfully Sent To CCD Data Server. Total No. of Sent " +
							"Entries: "+CommonStaticClass.entryUdpated);
				} else {
					CommonStaticClass.showMyAlert(con,"Message",
							"Could not connect to server. Please try again.");
					new Thread() {

						public void run() {


							Looper.prepare();
//						TransferFile();
							try {
								new DBCopier(con).copyDataBaseToSdcard(dbHelper.DB_PATH, CommonStaticClass.DB);
							} catch (IOException e) {
								e.printStackTrace();
							}Looper.loop();

						}

					}.start();
					
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
	/*private boolean isInternetAvailable()
	{
	}*/  
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

	private void startQuestion() {

		CommonStaticClass.SLNOSTACK.add(CommonStaticClass.currentSLNo);
		Intent i = new Intent();
		i.setClassName(CommonStaticClass.pName, CommonStaticClass.pName
				+ ".questions.ParentActivity");
		startActivity(i);
	}

	private void loadQuestions() {

		String sql = "Select * from tblQuestionLList order by SLNo asc";
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

			String sqlForSec = "Select SLNo,Qvar from tblQuestionLList where Qvar like 'sec%' order by SLNo asc";

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
			case INCREASE:
				if (progressDialog.getProgress() == progressDialog.getMax()) {
					progressDialog.setMax(progressDialog.getMax() * 2);
				}
				progressDialog.incrementProgressBy(1);
				break;
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

			}

		}
	};

	public void onBackPressed() {

	};
}
