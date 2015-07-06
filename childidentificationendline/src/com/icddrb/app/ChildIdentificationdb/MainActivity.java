package com.icddrb.app.ChildIdentificationdb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.icddrb.app.ChildIdentificationdb.CommonStaticClass;
import com.icddrb.app.ChildIdentificationdb.db.DatabaseHelper;
import com.icddrb.app.Childidentificationendline.R;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TimePicker;

import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends BaseActivity {
	private String sqlQuery = "";

	ArrayList<String> users;
	ArrayAdapter adapter1;
	private Spinner userId;

	public String userIdSelection = "";
	private ViewGroup viewGroup, radio6View;
	private ScrollView showhidescrollview;
	private Context con;
	private String EntryBy = "", EntryDate = "", EditBy = "", EditDate = "";

	private String dataid, clusterid, mid, FaId, SampleColDate, q5, q6, q7, q8,
			q9, q10, NumChildren, VersionNo, q6_other,q8_other;

	static final int DATE_DIALOG = 1, TIME_DIALOG = 2, UPDATEDONE = 999,
			UI_Loaded = 888;
	private int dateYear;
	private int dateMonth;
	private int dateDay;

	private int thisYear;
	private int thisMonth;
	private int thisDay;
	private int hour;
	private int min;
	private EditText pickDate, pickTime, q9et, q10et, q11et;
	private RadioGroup rdogroup5, rdogroup6, rdogroup7, rdogroup8;
	private RadioButton rdobtn7;
	private ProgressDialog progressDialog;
	private Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UPDATEDONE:
				progressDialog.dismiss();
				break;

			case UI_Loaded:
				progressDialog.dismiss();
				break;
			}

		}
	};

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
											startActivity(new Intent(con, LoginActivity.class));
											//android.os.Process.killProcess(pid);
											
										}
									}

									dialog.dismiss();

								}
							})

					.setCancelable(false).show();
			
		
		}
		
	}
	private void Savedata() {

		if (CommonStaticClass.userSpecificId.length() == 0
				|| CommonStaticClass.AssetID.length() == 0) 
		{
			return;
		}
		
		if (!ValidActivity()) {
			CommonStaticClass.showFinalAlert(con, "Fill All Fields");
			return;
		}
		q5 = String.valueOf(rdogroup5.indexOfChild(findViewById(rdogroup5
				.getCheckedRadioButtonId())) + 1);

		
		if(((EditText)findViewById(R.id.rdogroup6_other)).getVisibility()== View.VISIBLE)
		{
			q6_other = ((EditText)findViewById(R.id.rdogroup6_other)).getText().toString();
		}
		else
		{
			q6_other ="";
		}
		
		if(((EditText)findViewById(R.id.rdogroup8_other)).getVisibility()== View.VISIBLE)
		{
			q8_other = ((EditText)findViewById(R.id.rdogroup8_other)).getText().toString();
		}
		else
		{
			q8_other ="";
		}
		
		
		q7 = String.valueOf(rdogroup7.indexOfChild(findViewById(rdogroup7
				.getCheckedRadioButtonId())) + 1);

		// int nrOfChildren = viewGroup.getChildCount();
		// boolean alltrue = false;

		// if (alltrue) {

		if (Integer.parseInt(mid) > 8) {
			CommonStaticClass.showMyAlert(con, "Invalid Mother ID",
					"Mother ID can not be greater than 8.");
			return;
		}
		if (clusterid.length() < 3) {
			CommonStaticClass.showMyAlert(con, "Invalid Cluster ID",
					"Invalid cluster id.");
			return;
		}
		if (IsValidClusterID(clusterid)) {

		} else {
			CommonStaticClass.showMyAlert(con, "Invalid Cluster ID",
					"Invalid cluster id.");
			return;
		}
		dataid = clusterid + "0" + mid;
		CommonStaticClass.dataId = dataid;
		if (doesExist(dataid)) {
			CommonStaticClass.showMyAlert(con, "Data Id exist",
					"Please recheck\nThis data Id already exist.");

		} else {
			try {

				Date d = new Date(System.currentTimeMillis());
				String entryDate = "dd-mmm-yy";
				entryDate = d.toLocaleString();

				q7 = "-1";
				q8 = "-1";
				q9 = "-1";
				q10 = "-1";
				// NumChildren = null;

				q6 = String.valueOf(rdogroup6
						.indexOfChild(findViewById(rdogroup6
								.getCheckedRadioButtonId())) + 1);

				if (q6.equalsIgnoreCase("0")) {
					q6 = "-1";
				}

				if (q5.equalsIgnoreCase("1"))

				{
					q6 = "-1";
					q7 = String.valueOf(rdogroup7
							.indexOfChild(findViewById(rdogroup7
									.getCheckedRadioButtonId())) + 1);

					q8 = String.valueOf(rdogroup8
							.indexOfChild(findViewById(rdogroup8
									.getCheckedRadioButtonId())) + 1);

					q9et = (EditText) findViewById(R.id.et9);
					q10et = (EditText) findViewById(R.id.et10);
					q11et = (EditText) findViewById(R.id.et11);

					q9 = q9et.getText().toString();

					if (!q9.startsWith("01")) {
						if (!q9.equalsIgnoreCase("99")) {
							CommonStaticClass
									.showMyAlert(con, "Message",
											"Invalid Mobile Number. Number should start with 01");
							return;
						}
					}
					/*
					 * if (q9.length() < 11) { CommonStaticClass
					 * .showMyAlert(con, "Message",
					 * "Invalid Mobile Number. Number should be 11 digit long");
					 * return; }
					 */

					q10 = q10et.getText().toString();

					if(CommonStaticClass.futureDateValidator(dateYear,dateMonth,dateDay))
							{
						CommonStaticClass
						.showMyAlert(con, "Message",
								"Invalid Sample Collection Date");
								return;
							}

					if (!q10.startsWith("01")) {
						if (!q10.equalsIgnoreCase("99")) {
							CommonStaticClass
									.showMyAlert(con, "Message",
											"Invalid Mobile Number. Number should start with 01");
							return;
						}
					}
					/*
					 * if (q10.length() < 11) { CommonStaticClass
					 * .showMyAlert(con, "Message",
					 * "Invalid Mobile Number. Number should be 11 digit long");
					 * return; }
					 */

					NumChildren = q11et.getText().toString();
					CommonStaticClass.numberofchildren = Integer
							.parseInt(NumChildren);
				} else {
					if (DatabaseHelper
							.getInstance(con)
							.executeDMLQuery(
									"INSERT INTO tblMainQues ('dataid','clusterid','mid','FaId','SampleColDate', 'q5','q6','VersionNo','EntryBy','EntryDate','EditBy','EditDate', AssetID, q6_other) VALUES('"
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
											+ q5
											+ "','"
											+ q6
											+ "','"

											+ CommonStaticClass.VersionNo
											+ "','"
											+ CommonStaticClass.userSpecificId
											+ "','"
											+ entryDate
											+ "','"
											+ EditBy
											+ "','"
											+ EditDate
											+ "','"
											+ CommonStaticClass.AssetID + "','" + q6_other + "')"))
						;

				}

				if (q5.equalsIgnoreCase("2"))

				{
					finish();
					return;

				}
				
				
				if ((Integer.parseInt(NumChildren) > 2)) {
					CommonStaticClass.showMyAlert(con, "Message",
							"No. of child can not be greater than 2.");
					return;
				}

				if (DatabaseHelper
						.getInstance(con)
						.executeDMLQuery(
								"INSERT INTO tblMainQues ('dataid','clusterid','mid','FaId','SampleColDate', 'q5','q6','q7','q8','q9','q10','NumChildren','VersionNo','EntryBy','EntryDate','EditBy','EditDate', AssetID, q6_other, q8_other) VALUES('"
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
										+ q5
										+ "','"
										+ q6
										+ "','"
										+ q7
										+ "','"
										+ q8
										+ "','"
										+ q9
										+ "','"
										+ q10
										+ "','"
										+ CommonStaticClass.numberofchildren
										+ "','"
										+ CommonStaticClass.VersionNo
										+ "','"
										+ CommonStaticClass.userSpecificId
										+ "','"
										+ entryDate
										+ "','"
										+ EditBy
										+ "','"
										+ EditDate
										+ "','"
										+ CommonStaticClass.AssetID + "','" + q6_other + "','" + q8_other+ "')"))

				{

					for (int i = 1; i <= Integer.parseInt(NumChildren); i++) {
						String sql = String
								.format("INSERT INTO tblSamples (dataid, childNo, numberofchildren, EntryBy, EntryDate) VALUES('%s', '%s','%s','%s','%s')",
										dataid, i, NumChildren,
										CommonStaticClass.userSpecificId,
										entryDate);
						DatabaseHelper.getInstance(con).InsertToSample(sql);
					}

					CommonStaticClass.numberofchildren = Integer
							.parseInt(NumChildren);
					
					if (q7.equalsIgnoreCase("2"))

					{
						finish();
						return;

					}
					
					Intent intnt = new Intent(MainActivity.this,
							SampleCollector.class);
					startActivity(intnt);
				} else {
					CommonStaticClass.showMyAlert(con, "not inserted",
							"Some information is missing please rechcek.");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.infoholder);
		loadui();

		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				// progressDialog = ProgressDialog.show(con, "Wait",
				// "Please wait while Saving");

				// new Thread() {
				// public void run() {
				// Looper.prepare();

				Savedata();
				// Message ms = new Message();
				// ms.what = UPDATEDONE;
				// handler.sendMessage(ms);
				// Looper.loop();

			}
			// }.start();

			// }

			// else {

			// CommonStaticClass.showMyAlert(con, "Missing",
			// "Some information is missing please rechcek.");
			// }

			// }
		});

	}

	private boolean IsValidClusterID(String ClusterID) {
		String id = "";
		String sql = "Select * from tblSamplesinfo where clusterid='"
				+ Integer.parseInt(ClusterID) + "'";
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

	private void loadui() {
		rdobtn7 = (RadioButton) findViewById(R.id.radiogroup702);
		FaId = CommonStaticClass.userSpecificId;
		rdogroup5 = (RadioGroup) findViewById(R.id.rdogroup5);
		rdogroup6 = (RadioGroup) findViewById(R.id.rdogroup6);
		if (((EditText) (findViewById(R.id.rdogroup6_other))) != null) {
			((EditText) (findViewById(R.id.rdogroup6_other)))
					.setVisibility(View.GONE);
		}

		if (((EditText) (findViewById(R.id.rdogroup8_other))) != null) {
			((EditText) (findViewById(R.id.rdogroup8_other)))
					.setVisibility(View.GONE);
		}
		rdogroup6.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				RadioButton rb = (RadioButton) findViewById(group
						.getCheckedRadioButtonId());
				if (rb.getId() == R.id.rdogroup69) {
					{
						((EditText) (findViewById(R.id.rdogroup6_other)))
								.setVisibility(View.VISIBLE);
						((EditText) (findViewById(R.id.rdogroup6_other)))
								.requestFocus();
					}
				} else {
					((EditText) (findViewById(R.id.rdogroup6_other)))
							.setVisibility(View.GONE);
				}

			}
		});

		rdogroup7 = (RadioGroup) findViewById(R.id.rdogroup7);

		rdogroup7.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (group.getCheckedRadioButtonId() == rdobtn7.getId()) {
					showDialog("Message", "Team is in wrong Direction?",
							MainActivity.this);

				}
			}
		});

		rdogroup8 = (RadioGroup) findViewById(R.id.rdogroup8);

		rdogroup8.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				RadioButton rb = (RadioButton) findViewById(group
						.getCheckedRadioButtonId());
				if (rb.getId() == R.id.rdogroup86) {
					{
						((EditText) (findViewById(R.id.rdogroup8_other)))
								.setVisibility(View.VISIBLE);
						((EditText) (findViewById(R.id.rdogroup8_other)))
								.requestFocus();
					}
				} else {
					((EditText) (findViewById(R.id.rdogroup8_other)))
							.setVisibility(View.GONE);
				}

			}
		});

		showhidescrollview = (ScrollView) findViewById(R.id.showhidescrollview);
		radio6View = (LinearLayout) findViewById(R.id.radio6View);
		showhidescrollview.setVisibility(View.GONE);

		rdogroup5 = (RadioGroup) findViewById(R.id.rdogroup5);

		rdogroup5.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				RadioButton rb = (RadioButton) findViewById(group
						.getCheckedRadioButtonId());

				if (rb.getId() == R.id.rdogroup502) {
					showhidescrollview.setVisibility(View.GONE);
					if (radio6View != null)
						radio6View.setVisibility(View.VISIBLE);

				} else {
					showhidescrollview.setVisibility(View.VISIBLE);
					if (radio6View != null)
						radio6View.setVisibility(View.GONE);

				}

			}
		});

		final Calendar c = Calendar.getInstance();
		dateYear = c.get(Calendar.YEAR);
		dateMonth = c.get(Calendar.MONTH);
		dateDay = c.get(Calendar.DAY_OF_MONTH);

		thisYear = c.get(Calendar.YEAR);
		thisMonth = c.get(Calendar.MONTH);
		thisDay = c.get(Calendar.DAY_OF_MONTH);

		con = this;

		users = new ArrayList<String>();
		users.add("");

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

	}

	private boolean ValidActivity() {
		viewGroup = (LinearLayout) findViewById(R.id.rootView);
		int nrOfChildren = viewGroup.getChildCount();
		boolean alltrue = false;
		for (int i = 0; i < nrOfChildren; i++) {
			View view = viewGroup.getChildAt(i);

			if (view instanceof EditText) {
				if (!(((EditText) view).getText().toString().length() > 0)) {

					return false;
				} else {
					switch (view.getId()) {
					case R.id.editText2:
						clusterid = ((EditText) view).getText().toString();
						break;
					case R.id.editText1:
						mid = ((EditText) view).getText().toString();
						break;

					case R.id.editText4:
						SampleColDate = ((EditText) view).getText().toString();
						break;

					case R.id.et9:
						if (!((EditText) view).getText().toString()
								.startsWith("01")) {
							if (!((EditText) view).getText().toString()
									.equalsIgnoreCase("99")) {
								return false;
							}
						}
						break;

					case R.id.et11:
						NumChildren = ((EditText) view).getText().toString();
						CommonStaticClass.numberofchildren = Integer
								.parseInt(NumChildren);
						break;
					}
				}
			}

			if (view instanceof RadioGroup) {

				switch (view.getId()) {
				case R.id.rdogroup5:
					int index = rdogroup5.indexOfChild(findViewById(rdogroup5
							.getCheckedRadioButtonId())) + 1;
					if (index <= 0) {
						return false;
					}
					break;

				case R.id.rdogroup6:
					index = rdogroup6.indexOfChild(findViewById(rdogroup6
							.getCheckedRadioButtonId())) + 1;
					if (index <= 0) {
						return false;
					}
					break;

				case R.id.rdogroup7:
					index = rdogroup7.indexOfChild(findViewById(rdogroup7
							.getCheckedRadioButtonId())) + 1;
					if (index <= 0) {
						return false;
					}
					break;

				case R.id.rdogroup8:
					index = rdogroup8.indexOfChild(findViewById(rdogroup8
							.getCheckedRadioButtonId())) + 1;
					if (index <= 0) {
						return false;
					}
					break;

				}

			}

		}
		return true;
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
		String sql = "Select * from tblMainques where dataid='" + dataid2 + "'";
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

	public void finishactivity() {
		finish();
	}

	public Runnable aproc() {
		return new Runnable() {
			public void run() {
				CommonStaticClass.DialogResult = false;
			}
		};
	}

	public Runnable bproc() {
		return new Runnable() {
			public void run() {
				CommonStaticClass.DialogResult = true;
			}
		};
	}

	public void showDialog(String title, CharSequence message,
			final Activity mainActivity) {

		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case DialogInterface.BUTTON_POSITIVE:

					break;

				case DialogInterface.BUTTON_NEGATIVE:
					finishactivity();
					break;
				}
			}
		};
		DialogInterface.OnDismissListener dismiss = new OnDismissListener() {

			public void onDismiss(DialogInterface dialog) {

			}
		};

		AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
		builder.setTitle(title);
		builder.setMessage(message)
				.setPositiveButton("Yes", dialogClickListener)
				.setNegativeButton("No", dialogClickListener).show();
	}

}
