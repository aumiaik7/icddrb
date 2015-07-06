package app.stoolsampleendline;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import app.stoolsampleendline.db.DatabaseHelper;


import com.app.stoolsampleendline.R;

public class MainActivity extends BaseActivity {
	private String sqlQuery = "";

	ArrayList<String> users;
	ArrayAdapter adapter1;
	private Spinner userId;

	public String userIdSelection = "";
	private ViewGroup viewGroup;
	private Context con;
	private String dataid = "", clusterid = "", mid = "", FaId = "",
			SampleColDate = "", NumChildren = "", VersionNo = "", EntryBy = "",
			EntryDate = "", EditBy = "", EditDate = "";
	static final int DATE_DIALOG = 1, TIME_DIALOG = 2;
	private int dateYear;
	private int dateMonth;
	private int dateDay;

	private int thisYear;
	private int thisMonth;
	private int thisDay;
	private int hour;
	private int min;
	private EditText pickDate, pickTime;
	private static final int UPDATEDONE = 900;
	private static final int UI_Loaded = 800;
	private ProgressDialog progressDialog;
	private boolean datasaved;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.infoholder);

		final Calendar c = Calendar.getInstance();
		dateYear = c.get(Calendar.YEAR);
		dateMonth = c.get(Calendar.MONTH);
		dateDay = c.get(Calendar.DAY_OF_MONTH);

		thisYear = c.get(Calendar.YEAR);
		thisMonth = c.get(Calendar.MONTH);
		thisDay = c.get(Calendar.DAY_OF_MONTH);

		con = this;
		// userId = (Spinner) findViewById(R.id.spinner1);

		users = new ArrayList<String>();
		users.add("");
		// loadData();
		// adapter1 = new ArrayAdapter(this,
		// android.R.layout.simple_spinner_item,
		// users);
		// adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// userId.setAdapter(adapter1);
		// userId.setOnItemSelectedListener(new spinItemSelectedListener());
		viewGroup = (ViewGroup) findViewById(R.id.rootView);

		findViewById(R.id.editText4).setOnTouchListener(
				new View.OnTouchListener() {

					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						pickDate = (EditText) v;

						showDialog(DATE_DIALOG);
						return false;
					}
				});

		((EditText) findViewById(R.id.editText4))
				.setOnFocusChangeListener(new OnFocusChangeListener() {

					public void onFocusChange(View arg0, boolean arg1) {

						pickDate = (EditText) arg0;
						showDialog(DATE_DIALOG);

					}
				});

		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				progressDialog = ProgressDialog.show(con, "Wait",
						"Please wait while Saving");

				new Thread() {
					public void run() {
						Looper.prepare();
						datasaved = Save();
						Message ms = new Message();
						ms.what = UPDATEDONE;
						handler.sendMessage(ms);
						Looper.loop();

					}
				}.start();
			}
		});
	}

	private Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UPDATEDONE:
				if (progressDialog != null) {
					progressDialog.dismiss();
				}

				if (datasaved) {

					datasaved = false;
					Intent intnt = new Intent(MainActivity.this,
							SampleCollector.class);
					startActivity(intnt);

				}

				break;

			case UI_Loaded:
				if (progressDialog != null) {
					progressDialog.dismiss();
				}
				break;
			}

		}
	};

	private boolean IsValidClusterID(String ClusterID)
	{
		String id = "";
		String sql = "Select * from tblSamplesinfo where clusterid='" + Integer.parseInt(ClusterID) + "'";
		Cursor mCursor = null;
		try {
			mCursor = DatabaseHelper.getInstance(con).getQueryCursor(sql);
			if (mCursor.moveToFirst()) {
				do {
					id = mCursor.getString(mCursor.getColumnIndex("clusterid"));
					if (id != null && id.length() > 0) {
						return true;
					}
				} while (mCursor.moveToNext());
			}
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("cursor", "is null");
		} finally {
			if (mCursor != null)
				mCursor.close();
		}
		return false;
	}
	@Override
	public void onStart() {
		super.onStart(); // Always call the superclass method first

		checksession();

	}
	
	private void checksession()
	{
		if (CommonStaticClass.userSpecificId.length() == 0
				|| CommonStaticClass.AssetID.length() == 0) {

			new AlertDialog.Builder(con)
					.setTitle("Session Expired")
					.setMessage(
							"You've been inactive for a long while. Please exit and re-login")
					.setPositiveButton("Ok",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {

									int pid = 0;
									ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
									List<ActivityManager.RunningAppProcessInfo> pids = am
											.getRunningAppProcesses();
									for (int i = 0; i < pids.size(); i++) {
										ActivityManager.RunningAppProcessInfo info = pids
												.get(i);
										String p = getApplicationContext()
												.getPackageName();
										if (info.processName
												.equalsIgnoreCase(p)) {
											pid = info.pid;
											//startActivity(new Intent(con, LoginActivity.class));
											android.os.Process.killProcess(pid);
											
										}
									}

									dialog.dismiss();

								}
							})

					.setCancelable(false).show();
			
		
		}
		
	}
	
	private boolean Save() {
		// TODO Auto-generated method stub
		
		if (CommonStaticClass.userSpecificId.length() == 0
				|| CommonStaticClass.AssetID.length() == 0) {
		
			return false;
		}
		
		int nrOfChildren = viewGroup.getChildCount();
		boolean alltrue = false;
		for (int i = 0; i < nrOfChildren; i++) {
			View view = viewGroup.getChildAt(i);

			if (view instanceof EditText) {
				if (!(((EditText) view).getText().toString().length() > 0)) {

					alltrue = false;
					break;
				}

				else {
					alltrue = true;
					switch (view.getId()) {
					case R.id.editText2:
						clusterid = ((EditText) view).getText().toString();
						break;
					case R.id.editText1:
						mid = "0" + ((EditText) view).getText().toString();
						break;

					case R.id.editText4:
						SampleColDate = ((EditText) view).getText().toString();
						break;
					case R.id.editText5:
						NumChildren = ((EditText) view).getText().toString();
						CommonStaticClass.numberofchildren = Integer
								.parseInt(NumChildren);
						break;
					}
				}
			}
		}

		if (alltrue) {

			if (Integer.parseInt(mid) > 8) {
				CommonStaticClass.showMyAlert(con, "Invalid Mother ID",
						"Mother ID can not be greater than 8.");
				return false;
			}

			if (clusterid.length() < 3) {
				CommonStaticClass.showMyAlert(con, "Invalid Cluster ID",
						"Invalid cluster id.");
				return false;
			}
			if(IsValidClusterID(clusterid))
			{
				
			}
			else
			{
				CommonStaticClass.showMyAlert(con, "Invalid Cluster ID",
						"Invalid cluster id.");
				return false;
			}

			dataid = clusterid + mid;
			CommonStaticClass.dataId = dataid;
			if (doesExist(dataid)) {
				CommonStaticClass.showMyAlert(con, "Data Id exist",
						"Please recheck\nThis data Id already exist.");
			}

			else {
				try {

					Date d = new Date(System.currentTimeMillis());
					String entryDate = "dd-mmm-yy";
					entryDate = d.toLocaleString();
					if (!(Integer.parseInt(NumChildren) > 2)) {
						if (DatabaseHelper
								.getInstance(con)
								.executeDMLQuery(
										"INSERT INTO tblMainQues ('dataid','clusterid','mid','FaId','SampleColDate','NumChildren','VersionNo','EntryBy','EntryDate','EditBy','EditDate', AssetID) VALUES('"
												+ dataid
												+ "','"
												+ clusterid
												+ "','"
												+ mid
												+ "','"
												+ CommonStaticClass.userSpecificId
												+ "','"
												+ SampleColDate
												+ "','"
												+ NumChildren
												+ "','"
												+ VersionNo
												+ "','"
												+ CommonStaticClass.userSpecificId
												+ "','"
												+ entryDate
												+ "','"
												+ EditBy
												+ "','"
												+ EditDate
												
												+ "','"
												+ CommonStaticClass.AssetID
												+ "')"))

						{
							for (int i = 1; i <= Integer.parseInt(NumChildren); i++) {
								String q7s1 = dataid + "E" + i + "S01";
								String q7s2 = dataid + "E" + i + "S02";
								String q7s3 = dataid + "E" + i + "S03";
								String q7s4 = dataid + "E" + i + "S04";
								String q7s5 = dataid + "E" + i + "S05";
								String q7s6 = dataid + "E" + i + "T01";
								
								
								String q8s1 = getRandomId(dataid + "E" + i
										+ "S01");
								
								String q8s2 = getRandomId(dataid + "E" + i
										+ "S02");
								String q8s3 = getRandomId(dataid + "E" + i
										+ "S03");
								String q8s4 = getRandomId(dataid + "E" + i
										+ "S04");
								String q8s5 = getRandomId(dataid + "E" + i
										+ "S05");
								String q8s6 = getRandomId(dataid + "E" + i
										+ "T01");

								String sql = String
										.format("INSERT INTO tblSamples (dataid, childNo, q5, EntryBy, EntryDate, q9s1,q9s2,q9s3,q9s4,q9s5, q9s6, q7s1, q7s2, q7s3, q7s4, q7s5, q7s6, q8s1, q8s2, q8s3, q8s4, q8s5, q8s6)"
												+ " VALUES('%s', '%s','%s','%s','%s','%s', '%s','%s','%s','%s','%s', '%s','%s','%s','%s','%s', '%s','%s','%s','%s','%s','%s','%s')",
												dataid,
												i,
												NumChildren,
												CommonStaticClass.userSpecificId,
												entryDate, "s", "s", "s", "s","s",
												"s", q7s1, q7s2, q7s3, q7s4,
												q7s5, q7s6,q8s1, q8s2, q8s3, q8s4,
												q8s5,q8s6);
								DatabaseHelper.getInstance(con).InsertToSample(
										sql);
							}

						} else {
							CommonStaticClass
									.showMyAlert(con, "not inserted",
											"Some information is missing please rechcek.");
							return false;
						}
					} else {
						CommonStaticClass.showMyAlert(con, "Error",
								"You can not enter more then 2 child!!!");
						return false;
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}

		else {

			CommonStaticClass.showMyAlert(con, "Missing",
					"Some information is missing please rechcek.");
			return false;
		}
		return true;
	}

	protected String getRandomId(String sampleid) {
		String randomID = "";
		String sql = "Select randomid from tblSamplesInfo where sampleid='"
				+ sampleid + "'";
		Cursor mCursor = null;
		try {
			mCursor = DatabaseHelper.getInstance(con).getQueryCursor(sql);
			if (mCursor.moveToFirst()) {
				do {
					randomID = mCursor.getString(mCursor
							.getColumnIndex("randomid"));
				} while (mCursor.moveToNext());
			}
		} catch (Exception e) { // TODO: handle exception
			Log.e("cursor", "is null");
		} finally {
			if (mCursor != null)
				mCursor.close();
		}
		return randomID;
	}

	// protected void onActivityResult(int requestCode, int resultCode, Intent
	// data) {
	// super.onActivityResult(requestCode, resultCode, data);
	// switch(requestCode){
	// case(101):{
	// if(resultCode==Activity.RESULT_OK)
	// {
	// finish();
	// }
	// break;
	// }
	// }
	// }

	protected boolean doesExist(String dataid2) {
		String id = "";
		String sql = "Select * from tblSamples where dataid='" + dataid2 + "'";
		Cursor mCursor = null;
		try {
			mCursor = DatabaseHelper.getInstance(con).getQueryCursor(sql);
			if (mCursor.moveToFirst()) {
				do {
					id = mCursor.getString(mCursor.getColumnIndex("dataid"));
					if (id != null && id.length() > 0) {
						return true;
					}
				} while (mCursor.moveToNext());
			}
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("cursor", "is null");
		} finally {
			if (mCursor != null)
				mCursor.close();
		}
		return false;
	}
	
	
	

	public class spinItemSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			if (parent == userId) {
				if (parent.getItemAtPosition(pos).toString().length() > 0) {
					userIdSelection = parent.getItemAtPosition(pos).toString();
					userIdSelection = userIdSelection.substring(
							userIdSelection.lastIndexOf(".") + 2,
							userIdSelection.length());
					Log.e("User Name", userIdSelection);
				} else {
					userIdSelection = "";
				}
			}
		}

		public void onNothingSelected(AdapterView parent) {
		}
	}

	private void loadData() {

		// Log.e("cursor","is null jonjnjknikjnkn");
		Cursor mCursor = null;
		try {
			mCursor = DatabaseHelper
					.getInstance(getApplicationContext())
					.getQueryCursor(
							"Select * from tblUser where Active='Y' ORDER BY ID");
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
			// adapter1.notifyDataSetChanged();
		}

		/*
		 * users.add("1. Balay Chand Sikder"); users.add("2. Mary Marget");
		 * users.add("3. Parimol Sarker"); users.add("14. Dipali Rani Das");
		 * users.add("5. Sania Akter Dolly");
		 * users.add("6. Syeda Fatema Khatun");
		 * users.add("7. Md. Altafur Rahman");
		 * users.add("12. Syeda Luthfa Famida"); users.add("13. Shajahan Ali");
		 * users.add("14. Md. Shariful Islam");
		 */

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG:
			return new DatePickerDialog(this, dateSetListener, dateYear,
					dateMonth, dateDay);
		case TIME_DIALOG:
			return new TimePickerDialog(this, timeSetListener, hour, min, false);
		}
		return null;
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		switch (id) {
		case DATE_DIALOG:
			((DatePickerDialog) dialog)
					.updateDate(dateYear, dateMonth, dateDay);
			break;
		case TIME_DIALOG:
			((TimePickerDialog) dialog).updateTime(hour, min);
			break;
		}
	}

	private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			dateYear = year;
			dateMonth = monthOfYear;
			dateDay = dayOfMonth;
			updateDisplay("date");
		}
	};

	private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// TODO Auto-generated method stub
			hour = hourOfDay;
			min = minute;
			updateDisplay("time");
		}
	};

	private void updateDisplay(String dt) {
		if (dt != null) {
			if (pickDate != null) {
				if (dt.contains("date")) {
					String date = new StringBuilder()
							// Month is 0 based so add 1

							.append(dateDay).append(" ")
							.append(GetMonth(dateMonth + 1)).append(" ")
							.append(dateYear).toString();
					pickDate.setText(date);
				}
				if (dt.contains("time")) {
					String date = new StringBuilder()
					// Month is 0 based so add 1
							.append(hour).append(":").append(min).toString();
					pickTime.setText(date);
				}
			}
		}
	}

	private String GetMonth(int month) {
		String sMonth = "";

		switch (month) {
		case 1:
			sMonth = "Jan";
			break;
		case 2:
			sMonth = "Feb";
			break;
		case 3:
			sMonth = "Mar";
			break;
		case 4:
			sMonth = "Apr";
			break;
		case 5:
			sMonth = "May";
			break;
		case 6:
			sMonth = "Jun";
			break;
		case 7:
			sMonth = "Jul";
			break;
		case 8:
			sMonth = "Aug";
			break;
		case 9:
			sMonth = "Sep";
			break;
		case 10:
			sMonth = "Oct";
			break;
		case 11:
			sMonth = "Nov";
			break;
		case 12:
			sMonth = "Dec";
			break;

		default:
			sMonth = " ";
			break;

		}

		return sMonth;

	}
}
