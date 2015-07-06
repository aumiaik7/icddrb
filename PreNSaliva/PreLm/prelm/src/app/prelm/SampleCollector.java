package app.prelm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import app.prelm.db.DatabaseHelper;

import com.app.prelm.R;

public class SampleCollector extends BaseActivity {

	public interface AliqoutSelcetionListener {
		public void execute();
	}

	private Context con;

	static final int DATE_DIALOG = 1, TIME_DIALOG = 2;
	private int dateYear;
	private int dateMonth;
	private int dateDay;
	private int hour;
	private int min;

	private static final int UPDATEDONE = 900;
	private static final int UI_Loaded = 800;
	private static final int DATA_SAVED_HOUR_2 = 700;
	private ProgressDialog progressDialog;
	private boolean datasaved;
	private EditText pickDate, pickTime;
	private ViewGroup viewGroup;

	ArrayList<String> _episodes_hour2;
	ArrayAdapter adapter14h2;
	private Spinner q42_hour2_spinner;

	ArrayList<String> _episodes_hour5;
	ArrayAdapter adapter14h5;
	private Spinner q14_hour5_spinner;

	String sql = "";

	private String childNo = "", q6 = "", q7 = "", q8 = "",
			q8_other = "", q9 = "", q10 = "", q11 = "", q12 = "", q41 = "",

			q43_hour2 = "", q44_hour2 = "", q45_hour2 = "", q46_hour2 = "",

			q15_hour5 = "", q16_hour5 = "", q17_hour5 = "", q18_hour5 = "",
			q47 = "", q48 = "", q49 = "", q50 = "", q51 = "", q24 = "",
			q25 = "", q26 = "", q27 = "", q28 = "", q29 = "", q30 = "",
			q52s1hour2 = "", q52s2hour2 = "", q52s3hour2 = "", q52s4hour2 = "",
			q52s5hour2 = "", q52s6hour2 = "", q31s7hour5 = "", q31s8hour5 = "",
			q31s9hour5 = "", q31s10hour5 = "", q31s11hour5 = "",
			q31s12hour5 = "", q53s1hour2 = "", q53s2hour2, q53s3hour2,
			q53s4hour2, q53s5hour2, q53s6hour2, q32s7hour5, q32s8hour5,
			q32s9hour5, q32s10hour5, q32s11hour5, q32s12hour5, q54s1hour2,
			q54s2hour2, q54s3hour2, q54s4hour2, q54s5hour2, q54s6hour2,
			q33s7hour5, q33s8hour5, q33s9hour5, q33s10hour5, q33s11hour5,
			q33s12hour5, q55s1hour2 = "", q55s2hour2 = "", q55s3hour2 = "",
			q55s4hour2 = "", q55s5hour2 = "", q56hour2 ="", q56hour2_other ="", q57hour2="", q55s6hour2 = "", q34s7hour5 = "",
			q34s8hour5 = "", q34s9hour5 = "", q34s10hour5 = "",
			q34s11hour5 = "", q34s12hour5 = "", q35hour2 = "",
			q35hour2_other = "", q36hour2 = "", q35hour5 = "",
			q35hour5_other = "", q36hour5 = "", q37 = "", q38 = "",q37_other = "",

			q5_prev = "", q6_prev = "", q7_prev = "", q8_prev = "",
			q8_other_prev = "", q41_prev = "", q47_prev = "", q48_prev = "",
			q13_prev = "", q15_hour2_prev = "",
			q16_hour2_prev = "", q17_hour2_prev = "", q18_hour2_prev = "",
			q19_prev = "",
			q20_prev = "", q49_prev = "", q50_prev = "", q51_prev = "",
			q52s1hour2_prev = "",
			q52s2hour2_prev = "", q52s3hour2_prev = "", q52s4hour2_prev = "",
			q52s5hour2_prev = "", q52s6hour2_prev = "", q53s1hour2_prev = "",
			q53s2hour2_prev = "", q53s3hour2_prev = "", q53s4hour2_prev = "",
			q53s5hour2_prev = "", q53s6hour2_prev = "", q33s1hour2_prev = "",
			q33s2hour2_prev = "", q33s3hour2_prev = "", q33s4hour2_prev = "",
			q33s5hour2_prev = "", q33s6hour2_prev = "", q54s1hour2_prev = "",
			q54s2hour2_prev = "", q54s3hour2_prev = "", q54s4hour2_prev = "",
			q54s5hour2_prev = "", q54s6hour2_prev = "", q56hour2_prev = "",
			
					q55s1hour2_prev = "",
					q55s2hour2_prev = "", q55s3hour2_prev = "", q55s4hour2_prev = "",
					q55s5hour2_prev = "", q55s6hour2_prev = "",
					
			q57hour2_prev = "", q56hour2_other_prev = "", q57_prev = "",q37_other_prev = "";

	@Override
	public void onBackPressed() {
		// do nothing.
	}

	private void SetNextButtonToDoneButtonInKeyBoard(EditText edittext) {
		edittext.setImeOptions(EditorInfo.IME_ACTION_DONE);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_main, menu);
		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menu_save:
			progressDialog = ProgressDialog.show(con, "Wait",
					"Please wait while Saving");

			new Thread() {
				public void run() {
					Looper.prepare();
					datasaved = save();

					Message ms = new Message();
					ms.what = UPDATEDONE;
					handler.sendMessage(ms);
					Looper.loop();

				}
			}.start();

			return true;

		case R.id.menu_exit:
			new AlertDialog.Builder(con)
					.setTitle("Confirm")
					.setMessage(
							"Are you sure you want to exit? (Make sure you've saved)")
					.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									resetSomeData();
									finish();
									// dialog.dismiss();

								}
							})
					.setNegativeButton("No",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									dialog.dismiss();
								}
							})

					.setCancelable(false).show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}

		/* return super.onOptionsItemSelected(item); */
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		con = this;
		setContentView(R.layout.main_layout);

		setTitle("Pre-LM Urine Sample Collection :: Data ID: "
				+ CommonStaticClass.dataId.toString() + " Child No :"
				+ String.valueOf(CommonStaticClass.currentChildrenCount));

		loadtab();
		SetNextButtonToDoneButtonInKeyBoard(((EditText) findViewById(R.id.q6)));
		/*SetNextButtonToDoneButtonInKeyBoard(((EditText) findViewById(R.id.q10et)));
		SetNextButtonToDoneButtonInKeyBoard(((EditText) findViewById(R.id.q11et)));
		SetNextButtonToDoneButtonInKeyBoard(((EditText) findViewById(R.id.q12et)));*/

		loadAllUI();

		disableall();

		findViewById(R.id.textView35h2).setVisibility(View.GONE);
		findViewById(R.id.radioGroup56h2).setVisibility(View.GONE);
//		findViewById(R.id.textView35h5).setVisibility(View.GONE);
	//	findViewById(R.id.radioGroup35h5).setVisibility(View.GONE);

		final Calendar c = Calendar.getInstance();
		dateYear = c.get(Calendar.YEAR);
		dateMonth = c.get(Calendar.MONTH);
		dateDay = c.get(Calendar.DAY_OF_MONTH);

		c.get(Calendar.YEAR);
		c.get(Calendar.MONTH);
		c.get(Calendar.DAY_OF_MONTH);

		hour = c.get(Calendar.HOUR_OF_DAY);
		min = c.get(Calendar.MINUTE);

		((Button) findViewById(R.id.btnSaveHour2))
				.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {

						/*progressDialog = ProgressDialog.show(con, "Wait",
								"Please wait while Saving Hour 2");

						new Thread() {
							public void run() {
								Looper.prepare();*/
								datasaved = SaveHour2();
								/*Message ms = new Message();
								ms.what = DATA_SAVED_HOUR_2;
								handler.sendMessage(ms);
								Looper.loop();

							}
						}.start();*/
					}
				});
		
		

		((Button) findViewById(R.id.btnSaveObservationH2))
				.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {

						progressDialog = ProgressDialog.show(con, "Wait",
								"Please wait while Saving Hour 2");

						new Thread() {
							public void run() {
								Looper.prepare();
								datasaved = SaveHour2();
								Message ms = new Message();
								ms.what = DATA_SAVED_HOUR_2;
								handler.sendMessage(ms);
								Looper.loop();

							}
						}.start();
					}
				});

		findViewById(R.id.btnSaveEpisodeh2).setOnClickListener(
				new OnClickListener() {

					public void onClick(View v) {

						datasaved = saveEpisodeHour2();

					}
				});

		/*findViewById(R.id.btnSaveEpisodeh5).setOnClickListener(
				new OnClickListener() {

					public void onClick(View v) {

						datasaved = saveEpisodeHour5();

					}
				});*/

	}
	
	

	private void computeTotalUrineVolumeHour2() {
		String sql = String.format(
				"SELECT SUM(q44_hour2) AS Volume FROM tblEpisodes_Hour2 WHERE dataid='%s' AND "
						+ " childNo='%s'", CommonStaticClass.dataId,
				CommonStaticClass.currentChildrenCount);

		String data = null;
		Cursor mCursor = null;
		try {
			mCursor = DatabaseHelper.getInstance(con).getQueryCursor(sql);
			if (mCursor.getCount() > 0) {
				if (mCursor.moveToFirst()) {
					do {
						data = mCursor.getString(mCursor
								.getColumnIndex("Volume"));

					} while (mCursor.moveToNext());
				}

				Log.e("From volume hour 2", "From volume hour 2");
			}

			if (data != null) {

				((EditText) findViewById(R.id.q48et)).setText(data);
				findViewById(R.id.textView36h2).setVisibility(View.VISIBLE);
				findViewById(R.id.q57eth2).setVisibility(View.VISIBLE);
			} else {
				findViewById(R.id.textView36h2).setVisibility(View.GONE);
				findViewById(R.id.q57eth2).setVisibility(View.GONE);
				((EditText) findViewById(R.id.q48et)).setText("0.0");
			}

		} catch (Exception e) {

		}

	}

	private float GetTotalUrineVolumeHour2() {
		String sql = String.format(
				"SELECT SUM(q44_hour2) AS Volume FROM tblEpisodes_Hour2 WHERE dataid='%s' AND "
						+ " childNo='%s'", CommonStaticClass.dataId,
				CommonStaticClass.currentChildrenCount);

		String data = null;
		Cursor mCursor = null;
		try {
			mCursor = DatabaseHelper.getInstance(con).getQueryCursor(sql);
			if (mCursor.getCount() > 0) {
				if (mCursor.moveToFirst()) {
					do {
						data = mCursor.getString(mCursor
								.getColumnIndex("Volume"));

					} while (mCursor.moveToNext());
				}

				Log.e("From volume hour 2", "From volume hour 2");
			}

		} catch (Exception e) {

		}
		if (data != null) {
			if (data.length() > 0) {
				return Float.parseFloat(data);
			} else {
				return 0;
			}
		} else {
			return 0;
		}

	}

	private void computeTotalUrineVolumeHour5() {
		String sql = String.format(
				"SELECT SUM(q16_hour5) AS Volume FROM tblEpisodes_Hour5 WHERE dataid='%s' AND "
						+ " childNo='%s'", CommonStaticClass.dataId,
				CommonStaticClass.currentChildrenCount);

		String data = null;
		Cursor mCursor = null;
		try {
			mCursor = DatabaseHelper.getInstance(con).getQueryCursor(sql);
			if (mCursor.getCount() > 0) {
				if (mCursor.moveToFirst()) {
					do {
						data = mCursor.getString(mCursor
								.getColumnIndex("Volume"));

					} while (mCursor.moveToNext());
				}

				Log.e("From volume hour 5", "From volume hour 5");
			}

			if (data != null) {
				data = String
						.valueOf((Float.parseFloat(data) + GetTotalUrineVolumeHour2()));
				((EditText) findViewById(R.id.q25et)).setText(data);
				findViewById(R.id.textView36h5).setVisibility(View.VISIBLE);
				findViewById(R.id.q36eth5).setVisibility(View.VISIBLE);

				/*
				 * if(Float.parseFloat(data)>0)
				 * findViewById(R.id.lnq37).setVisibility(View.GONE);
				 */

			} else {
				findViewById(R.id.textView36h5).setVisibility(View.GONE);
				findViewById(R.id.q36eth5).setVisibility(View.GONE);
				((EditText) findViewById(R.id.q25et)).setText("0.0");
				// findViewById(R.id.lnq37).setVisibility(View.VISIBLE);
			}
		} catch (Exception e) {

		}

	}

	private Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UPDATEDONE:
				if (progressDialog != null) {
					progressDialog.dismiss();
				}
				if (CommonStaticClass.mode
						.equalsIgnoreCase(CommonStaticClass.EDITMODE)) {

					if (datasaved) {
						datasaved = false;
						finish();
					}
				}
				if (datasaved) {
					resetEveryValue((LinearLayout) findViewById(R.id.sample_end_2hourln));
					resetEveryValue((LinearLayout) findViewById(R.id.sample_end_5hourln));

					resetEveryValue((GridLayout) findViewById(R.id.sample_start_hour2gl));
					resetEveryValue((GridLayout) findViewById(R.id.sample_start_hour5gl));
					resetEveryValue((GridLayout) findViewById(R.id.observation_grid_hour2gl));
					resetEveryValue((GridLayout) findViewById(R.id.observation_grid_hour5gl));

					datasaved = false;
					setTitle("Stool Sample :: Data ID: "
							+ CommonStaticClass.dataId.toString()
							+ " Child No :"
							+ String.valueOf(CommonStaticClass.currentChildrenCount));

					resetEveryValue((LinearLayout) findViewById(R.id.main_view));
					resetEveryValue((LinearLayout) findViewById(R.id.extraqh2));
					resetEveryValue((LinearLayout) findViewById(R.id.extraqh5));
					resetEveryValue((LinearLayout) findViewById(R.id.lnq37));
					((EditText) findViewById(R.id.q6)).setText("");
					q6 = "";
					q7 = "";
					q8 = "";
					q8_other = "";
					q9 = "";
					q10 = "";
					q11 = "";
					q12 = "";
					q41 = "";

					q43_hour2 = "";
					q44_hour2 = "";
					q45_hour2 = "";
					q46_hour2 = "";

					q15_hour5 = "";
					q16_hour5 = "";
					q17_hour5 = "";
					q18_hour5 = "";
					q47 = "";
					q48 = "";
					q49 = "";
					q50 = "";
					q51 = "";
					q24 = "";
					q25 = "";
					q26 = "";
					q27 = "";
					q28 = "";
					q29 = "";
					q30 = "";
					q52s1hour2 = "";
					q52s2hour2 = "";
					q52s3hour2 = "";
					q52s4hour2 = "";
					q52s5hour2 = "";
					q52s6hour2 = "";
					
					
					q53s1hour2 = "";
					q53s2hour2 = "";
					q53s3hour2 = "";
					q53s4hour2 = "";
					q53s5hour2 = "";
					q53s6hour2 = "";
					
					q54s1hour2 = "";
					q54s2hour2 = "";
					q54s3hour2 = "";
					q54s4hour2 = "";
					q54s5hour2 = "";
					q54s6hour2 = "";
					
					q55s1hour2 = "";
					q55s2hour2 = "";
					q55s3hour2 = "";
					q55s4hour2 = "";
					q55s5hour2 = "";
					q55s6hour2 = "";
					
					
					q56hour2 = "";
					q56hour2_other = "";
					q57hour2 = "";
					
					
					
					findViewById(R.id.q8_otheret).setVisibility(View.GONE);
					findViewById(R.id.q56_othereth2).setVisibility(View.GONE);
					/*findViewById(R.id.q35_othereth5).setVisibility(View.GONE);
					findViewById(R.id.textView35h2).setVisibility(View.GONE);*/
					findViewById(R.id.radioGroup56h2).setVisibility(View.GONE);
					/*findViewById(R.id.textView35h5).setVisibility(View.GONE);
					findViewById(R.id.radioGroup35h5).setVisibility(View.GONE);*/

					// findViewById(R.id.textView35h2).setVisibility(View.GONE);
					// findViewById(R.id.radioGroup35h2).setVisibility(View.GONE);
					// findViewById(R.id.textView35h5).setVisibility(View.GONE);
					// findViewById(R.id.radioGroup35h5).setVisibility(View.GONE);

					q52s1hour2 = CommonStaticClass.dataId + "E"
							+ CommonStaticClass.currentChildrenCount + "U13";
					q52s2hour2 = CommonStaticClass.dataId + "E"
							+ CommonStaticClass.currentChildrenCount + "U14";
					q52s3hour2 = CommonStaticClass.dataId + "E"
							+ CommonStaticClass.currentChildrenCount + "U15";
					q52s4hour2 = CommonStaticClass.dataId + "E"
							+ CommonStaticClass.currentChildrenCount + "U16";
					q52s5hour2 = CommonStaticClass.dataId + "E"
							+ CommonStaticClass.currentChildrenCount + "U17";
					q52s6hour2 = CommonStaticClass.dataId + "E"
							+ CommonStaticClass.currentChildrenCount + "U18";
					
					
					q53s1hour2 = getRandomId(CommonStaticClass.dataId + "E"
							+ CommonStaticClass.currentChildrenCount + "U13");
					q53s2hour2 = getRandomId(CommonStaticClass.dataId + "E"
							+ CommonStaticClass.currentChildrenCount + "U14");
					q53s3hour2 = getRandomId(CommonStaticClass.dataId + "E"
							+ CommonStaticClass.currentChildrenCount + "U15");
					q53s4hour2 = getRandomId(CommonStaticClass.dataId + "E"
							+ CommonStaticClass.currentChildrenCount + "U16");
					q53s5hour2 = getRandomId(CommonStaticClass.dataId + "E"
							+ CommonStaticClass.currentChildrenCount + "U17");
					q53s6hour2 = getRandomId(CommonStaticClass.dataId + "E"
							+ CommonStaticClass.currentChildrenCount + "U18");
					
					
					
					/*q32s7hour5 = getRandomId(CommonStaticClass.dataId + "M"
							+ CommonStaticClass.currentChildrenCount + "U07");
					q32s8hour5 = getRandomId(CommonStaticClass.dataId + "M"
							+ CommonStaticClass.currentChildrenCount + "U08");
					q32s9hour5 = getRandomId(CommonStaticClass.dataId + "M"
							+ CommonStaticClass.currentChildrenCount + "U09");
					q32s10hour5 = getRandomId(CommonStaticClass.dataId + "M"
							+ CommonStaticClass.currentChildrenCount + "U10");
					q32s11hour5 = getRandomId(CommonStaticClass.dataId + "M"
							+ CommonStaticClass.currentChildrenCount + "U11");
					q32s12hour5 = getRandomId(CommonStaticClass.dataId + "M"
							+ CommonStaticClass.currentChildrenCount + "U12");*/

					/*
					 * ((LinearLayout) findViewById(R.id.lnq37))
					 * .setVisibility(View.VISIBLE);
					 * findViewById(R.id.q37_other).setVisibility(View.GONE);
					 */

					((TextView) findViewById(R.id.q52s1hour2))
							.setText(q52s1hour2);
					((TextView) findViewById(R.id.q52s2hour2))
							.setText(q52s2hour2);
					((TextView) findViewById(R.id.q52s3hour2))
							.setText(q52s3hour2);
					((TextView) findViewById(R.id.q52s4hour2))
							.setText(q52s4hour2);
					((TextView) findViewById(R.id.q52s5hour2))
							.setText(q52s5hour2);
					((TextView) findViewById(R.id.q52s6hour2))
							.setText(q52s6hour2);
					
					
					/*((TextView) findViewById(R.id.q31s7hour5))
							.setText(q31s7hour5);
					((TextView) findViewById(R.id.q31s8hour5))
							.setText(q31s8hour5);
					((TextView) findViewById(R.id.q31s9hour5))
							.setText(q31s9hour5);
					((TextView) findViewById(R.id.q31s10hour5))
							.setText(q31s10hour5);
					((TextView) findViewById(R.id.q31s11hour5))
							.setText(q31s11hour5);
					((TextView) findViewById(R.id.q31s12hour5))
							.setText(q31s12hour5);*/

					((TextView) findViewById(R.id.q53s1hour2))
							.setText(q53s1hour2);
					((TextView) findViewById(R.id.q53s2hour2))
							.setText(q53s2hour2);
					((TextView) findViewById(R.id.q53s3hour2))
							.setText(q53s3hour2);
					((TextView) findViewById(R.id.q53s4hour2))
							.setText(q53s4hour2);
					((TextView) findViewById(R.id.q53s5hour2))
							.setText(q53s5hour2);
					((TextView) findViewById(R.id.q53s6hour2))
							.setText(q53s6hour2);
					/*((TextView) findViewById(R.id.q32s7hour5))
							.setText(q32s7hour5);
					((TextView) findViewById(R.id.q32s8hour5))
							.setText(q32s8hour5);
					((TextView) findViewById(R.id.q32s9hour5))
							.setText(q32s9hour5);
					((TextView) findViewById(R.id.q32s10hour5))
							.setText(q32s10hour5);
					((TextView) findViewById(R.id.q32s11hour5))
							.setText(q32s11hour5);
					((TextView) findViewById(R.id.q32s12hour5))
							.setText(q32s12hour5);*/
					/*((EditText) findViewById(R.id.q9et)).setText("");
					((EditText) findViewById(R.id.q10et)).setText("");
					((EditText) findViewById(R.id.q11et)).setText("");
					((EditText) findViewById(R.id.q12et)).setText("");*/
					((EditText) findViewById(R.id.q41et)).setText("");
					((RadioButton)((RadioGroup)findViewById(R.id.radioGroup7)).findViewById(R.id.radio1)).setChecked(true);
					disableall();

				}

				break;

			case DATA_SAVED_HOUR_2:
				if (progressDialog != null) {
					progressDialog.dismiss();
				}
				if (CommonStaticClass.mode
						.equalsIgnoreCase(CommonStaticClass.EDITMODE)) {

					if (datasaved) {
						datasaved = false;

					}
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

	private boolean save() {

		if (!ValidActivity()) {
			CommonStaticClass.showFinalAlert(con, "Fill the required field");
			return false;
		}

		String entryDate = "dd-mmm-yyyy";
		Date d = new Date(System.currentTimeMillis());
		entryDate = "dd-mmm-yyyy";
		entryDate = d.toLocaleString();

		// insert data
		childNo = CommonStaticClass.currentChildrenCount + "";

		if (CommonStaticClass.mode.equalsIgnoreCase(CommonStaticClass.ADDMODE)
				|| CommonStaticClass.mode
						.equalsIgnoreCase(CommonStaticClass.SPECIALADD)) {

			/*
			 * String old_entryBy = DatabaseHelper.getInstance(con)
			 * .GetSingleColumnData("EntryBy", "tblSamples"); String
			 * old_entrydate = DatabaseHelper.getInstance(con)
			 * .GetSingleColumnData("EntryDate", "tblSamples"); int num =
			 * Integer.parseInt(DatabaseHelper.getInstance(con)
			 * .GetSingleColumnData("q5", "tblSamples"));
			 */

			sql = String
					.format("UPDATE tblSamples SET q6='%s',q7='%s', q8='%s',"
							+ "q41 ='%s', q47 ='%s',"
							+ "q48 ='%s', q49 ='%s', q50 ='%s', q51 ='%s', q52s1hour2 ='%s', q52s2hour2 ='%s', q52s3hour2 ='%s', q52s4hour2 ='%s', q52s5hour2 ='%s'," +
							"q52s6hour2 ='%s', q53s1hour2 ='%s', "
							+ "q53s2hour2='%s', q53s3hour2='%s', q53s4hour2='%s', q53s5hour2='%s', q53s6hour2='%s'," 
							+ "q54s1hour2='%s', q54s2hour2='%s', q54s3hour2='%s', q54s4hour2='%s', q54s5hour2='%s', q54s6hour2='%s', "
							+" q55s1hour2 ='%s', q55s2hour2 ='%s', q55s3hour2 ='%s', q55s4hour2 ='%s', q55s5hour2 ='%s',"
							+ "q55s6hour2 ='%s', q56hour2 ='%s', q56hour2_other ='%s', q57hour2='%s',EditBy='%s',EditDate='%s'"
							+ " WHERE dataid = '%s' AND childno = '%s'", q6,q7, q8,
							 q41 , q47 ,
							 q48 , q49 , q50 , q51 , q52s1hour2 , q52s2hour2 , q52s3hour2 , q52s4hour2 , q52s5hour2 , 
							q52s6hour2 , q53s1hour2 , 
							 q53s2hour2, q53s3hour2, q53s4hour2, q53s5hour2, q53s6hour2, 
							 q54s1hour2, q54s2hour2, q54s3hour2, q54s4hour2, q54s5hour2, q54s6hour2, 
							 q55s1hour2 , q55s2hour2 , q55s3hour2 , q55s4hour2 , q55s5hour2 ,
							 q55s6hour2 , q56hour2 , q56hour2_other , q57hour2,
							CommonStaticClass.userSpecificId, entryDate,
							CommonStaticClass.dataId, childNo);

		} else if (CommonStaticClass.mode
				.equalsIgnoreCase(CommonStaticClass.EDITMODE)) {

			sql = String
					.format("UPDATE tblSamples SET q6='%s',q7='%s', q8='%s',"
							+ "q41 ='%s', q47 ='%s',"
							+ "q48 ='%s', q49 ='%s', q50 ='%s', q51 ='%s', q52s1hour2 ='%s', q52s2hour2 ='%s', q52s3hour2 ='%s', q52s4hour2 ='%s', q52s5hour2 ='%s'," +
							"q52s6hour2 ='%s', q53s1hour2 ='%s', "
							+ "q53s2hour2='%s', q53s3hour2='%s', q53s4hour2='%s', q53s5hour2='%s', q53s6hour2='%s'," 
							+ "q54s1hour2='%s', q54s2hour2='%s', q54s3hour2='%s', q54s4hour2='%s', q54s5hour2='%s', q54s6hour2='%s', "
							+" q55s1hour2 ='%s', q55s2hour2 ='%s', q55s3hour2 ='%s', q55s4hour2 ='%s', q55s5hour2 ='%s',"
							+ "q55s6hour2 ='%s', q56hour2 ='%s', q56hour2_other ='%s', q57hour2='%s',EditBy='%s',EditDate='%s'"
							+ " WHERE dataid = '%s' AND childno = '%s'", q6,q7, q8,
							 q41 , q47 ,
							 q48 , q49 , q50 , q51 , q52s1hour2 , q52s2hour2 , q52s3hour2 , q52s4hour2 , q52s5hour2 , 
							q52s6hour2 , q53s1hour2 , 
							 q53s2hour2, q53s3hour2, q53s4hour2, q53s5hour2, q53s6hour2, 
							 q54s1hour2, q54s2hour2, q54s3hour2, q54s4hour2, q54s5hour2, q54s6hour2, 
							 q55s1hour2 , q55s2hour2 , q55s3hour2 , q55s4hour2 , q55s5hour2 ,
							 q55s6hour2 , q56hour2 , q56hour2_other , q57hour2,
							CommonStaticClass.userSpecificId, entryDate,
							CommonStaticClass.dataId, childNo);

			Log.e("From Update", sql);
			CommonStaticClass.currentChildrenCount = 0;
		}

		try {
			if (DatabaseHelper.getInstance(con).executeDMLQuery(sql)) {

				if (CommonStaticClass.mode
						.equalsIgnoreCase(CommonStaticClass.ADDMODE)) {

					Toast.makeText(con, "Saved and Ready for Next Sample",
							Toast.LENGTH_LONG).show();

					checkToFinish();

					return true;

				} else {
					Toast.makeText(con, "Data Updated", Toast.LENGTH_LONG)
							.show();
					// resetEveryValue();
					resetSomeData();
					Log.e("Return for Edit", "Return true");
					return true;
					// finish();
				}
			} else {
				Toast.makeText(con, "An Error Occured. Maximum Child Reached",
						Toast.LENGTH_LONG).show();

			}
		} catch

		(Exception e) {
			return false;
		}
		return true;
	}

	private boolean saveEpisodeHour2() {

		viewGroup = (GridLayout) findViewById(R.id.sample_start_hour2gl);
		int nrOfChildren = viewGroup.getChildCount();

		for (int i = 0; i < nrOfChildren; i++) {
			if (viewGroup.getVisibility() == View.GONE)
				break;

			View view = viewGroup.getChildAt(i);

			if (view.getVisibility() == View.GONE)
				continue;

			if (view instanceof EditText) {
				if (!(((EditText) view).getText().toString().length() > 0)) {

					Toast.makeText(con, "Fill the required fields",
							Toast.LENGTH_LONG).show();
					return false;
				}

			}

			else if (view instanceof RadioGroup) {

				if (view.getVisibility() == View.GONE)
					continue;

				if (((RadioGroup) view).getCheckedRadioButtonId() == -1) {

					Toast.makeText(con, "Fill the required fields",
							Toast.LENGTH_LONG).show();
					return false;
				}

			}

		}

		String entryDate = "dd-mmm-yyyy";
		Date d = new Date(System.currentTimeMillis());
		entryDate = "dd-mmm-yyyy";
		entryDate = d.toLocaleString();

		// insert data
		childNo = CommonStaticClass.currentChildrenCount + "";
		String EpisodeNo = q42_hour2_spinner.getSelectedItem().toString();

		if (CommonStaticClass.mode.equalsIgnoreCase(CommonStaticClass.ADDMODE)
				|| CommonStaticClass.mode
						.equalsIgnoreCase(CommonStaticClass.SPECIALADD)) {

			q43_hour2 = ((EditText) findViewById(R.id.q43_hour2et)).getText()
					.toString();
			q44_hour2 = ((EditText) findViewById(R.id.q44_hour2et)).getText()
					.toString();

			// Check before insert
			sql = "SELECT EpisodeNo "
					+ "FROM tblEpisodes_Hour2 WHERE dataid = '"
					+ CommonStaticClass.dataId + "'" + " AND " + " childNo = '"
					+ CommonStaticClass.currentChildrenCount + "'"
					+ " AND EpisodeNo = '" + EpisodeNo + "'";

			Log.e("sql", sql);

			Cursor mCursor = null;
			boolean foundh2 = false;
			try {
				mCursor = DatabaseHelper.getInstance(con).getQueryCursor(sql);
				if (mCursor.moveToFirst()) {
					do {
						foundh2 = true;

					} while (mCursor.moveToNext());
				}
			} catch (Exception e) {
				// TODO: handle exception
				Log.e("cursor", "is null");
			} finally {
				if (mCursor != null)
					mCursor.close();

			}

			if (foundh2 == true) {
				sql = String
						.format("UPDATE tblEpisodes_Hour2 SET q43_hour2='%s', q44_hour2='%s',"
								+ "q45_hour2='%s', q46_hour2='%s', EditBy='%s',EditDate='%s' "
								+ " WHERE dataid = '%s' AND childno = '%s' AND EpisodeNo = '%s'",
								q43_hour2, q44_hour2, q45_hour2, q46_hour2,
								CommonStaticClass.userSpecificId, entryDate,
								CommonStaticClass.dataId, childNo, EpisodeNo);
			} else {

				//

				sql = "INSERT INTO tblEpisodes_Hour2 (dataid,childNo,EpisodeNo,q43_hour2, q44_hour2,"
						+ "q45_hour2, q46_hour2, EntryBy,EntryDate) VALUES ('"
						+ CommonStaticClass.dataId
						+ "','"
						+ CommonStaticClass.currentChildrenCount
						+ "','"
						+ EpisodeNo
						+ "','"
						+ q43_hour2
						+ "','"
						+ q44_hour2
						+ "','"
						+ q45_hour2
						+ "','"
						+ q46_hour2
						+ "','"
						+ CommonStaticClass.userSpecificId
						+ "','"
						+ entryDate
						+ "')";
			}
		} else if (CommonStaticClass.mode
				.equalsIgnoreCase(CommonStaticClass.EDITMODE)) {

			q43_hour2 = ((EditText) findViewById(R.id.q43_hour2et)).getText()
					.toString();
			q44_hour2 = ((EditText) findViewById(R.id.q44_hour2et)).getText()
					.toString();

			sql = "SELECT EpisodeNo "
					+ "FROM tblEpisodes_Hour2 WHERE dataid = '"
					+ CommonStaticClass.dataId + "'" + " AND " + " childNo = '"
					+ CommonStaticClass.currentChildrenCount + "'"
					+ " AND EpisodeNo = '" + EpisodeNo + "'";

			Log.e("sql", sql);

			Cursor mCursor = null;
			boolean foundh2 = false;
			try {
				mCursor = DatabaseHelper.getInstance(con).getQueryCursor(sql);
				if (mCursor.moveToFirst()) {
					do {
						foundh2 = true;

					} while (mCursor.moveToNext());
				}
			} catch (Exception e) {
				// TODO: handle exception
				Log.e("cursor", "is null");
			} finally {
				if (mCursor != null)
					mCursor.close();

			}
			if (foundh2 == true) {
				sql = String
						.format("UPDATE tblEpisodes_Hour2 SET q43_hour2='%s', q44_hour2='%s',"
								+ "q45_hour2='%s', q46_hour2='%s', EditBy='%s',EditDate='%s' "
								+ " WHERE dataid = '%s' AND childno = '%s' AND EpisodeNo = '%s'",
								q43_hour2, q44_hour2, q45_hour2, q46_hour2,
								CommonStaticClass.userSpecificId, entryDate,
								CommonStaticClass.dataId,
								CommonStaticClass.currentChildrenCount,
								EpisodeNo);
			} else {
				sql = "INSERT INTO tblEpisodes_Hour2 (dataid,childNo,EpisodeNo,q43_hour2, q44_hour2,"
						+ "q45_hour2, q46_hour2, EntryBy,EntryDate) VALUES ('"
						+ CommonStaticClass.dataId
						+ "','"
						+ CommonStaticClass.currentChildrenCount
						+ "','"
						+ EpisodeNo
						+ "','"
						+ q43_hour2
						+ "','"
						+ q44_hour2
						+ "','"
						+ q45_hour2
						+ "','"
						+ q46_hour2
						+ "','"
						+ CommonStaticClass.userSpecificId
						+ "','"
						+ entryDate
						+ "')";
			}

			// CommonStaticClass.currentChildrenCount = 0;
		}

		try {
			if (DatabaseHelper.getInstance(con).executeDMLQuery(sql))

			{
				computeTotalUrineVolumeHour2();

				if (CommonStaticClass.mode
						.equalsIgnoreCase(CommonStaticClass.ADDMODE)) {

					Toast.makeText(con, "Episode Saved", Toast.LENGTH_LONG)
							.show();

					int v = _episodes_hour2.size() + 1;
					_episodes_hour2.add(String.valueOf(v));

					adapter14h2 = new ArrayAdapter(this,
							android.R.layout.simple_spinner_item,
							_episodes_hour2);
					adapter14h2.notifyDataSetChanged();
					ClearEpisodeHour2();
					return true;

				} else {
					Toast.makeText(con, "Data Updated for Episode",
							Toast.LENGTH_LONG).show();
					int v = _episodes_hour2.size() + 1;
					_episodes_hour2.add(String.valueOf(v));

					adapter14h2 = new ArrayAdapter(this,
							android.R.layout.simple_spinner_item,
							_episodes_hour2);
					adapter14h2.notifyDataSetChanged();
					// resetEveryValue();
					// resetSomeData();

					Log.e("Return for Edit", "Return true");
					ClearEpisodeHour2();
					return true;
					// finish();
				}
			} else {
				Toast.makeText(
						con,
						"An Error Occured. could not perform Episode insert / update",
						Toast.LENGTH_LONG).show();

			}
		} catch

		(Exception e) {
			return false;
		}
		return true;
	}

	private void ClearEpisodeHour2() {
		((EditText) findViewById(R.id.q43_hour2et)).setText("");
		((EditText) findViewById(R.id.q44_hour2et)).setText("");
		((RadioGroup) findViewById(R.id.radioGroup45h2)).clearCheck();
		((RadioGroup) findViewById(R.id.radioGroup46h2)).clearCheck();
	}

	private void ClearEpisodeHour5() {
		((EditText) findViewById(R.id.q15_hour5et)).setText("");
		((EditText) findViewById(R.id.q16_hour5et)).setText("");
		((RadioGroup) findViewById(R.id.radioGroup17h5)).clearCheck();
		((RadioGroup) findViewById(R.id.radioGroup18h5)).clearCheck();
	}

	private boolean saveEpisodeHour5() {

		viewGroup = (GridLayout) findViewById(R.id.sample_start_hour5gl);
		int nrOfChildren = viewGroup.getChildCount();

		for (int i = 0; i < nrOfChildren; i++) {
			if (viewGroup.getVisibility() == View.GONE)
				break;

			View view = viewGroup.getChildAt(i);

			if (view.getVisibility() == View.GONE)
				continue;

			if (view instanceof EditText) {
				if (!(((EditText) view).getText().toString().length() > 0)) {

					Toast.makeText(con, "Fill the required fields",
							Toast.LENGTH_LONG).show();
					return false;
				}

			}

			else if (view instanceof RadioGroup) {

				if (view.getVisibility() == View.GONE)
					continue;

				if (((RadioGroup) view).getCheckedRadioButtonId() == -1) {

					Toast.makeText(con, "Fill the required fields",
							Toast.LENGTH_LONG).show();
					return false;
				}

			}

		}

		String entryDate = "dd-mmm-yyyy";
		Date d = new Date(System.currentTimeMillis());
		entryDate = "dd-mmm-yyyy";
		entryDate = d.toLocaleString();

		// insert data
		childNo = CommonStaticClass.currentChildrenCount + "";
		String EpisodeNo = q14_hour5_spinner.getSelectedItem().toString();
		if (CommonStaticClass.mode.equalsIgnoreCase(CommonStaticClass.ADDMODE)
				|| CommonStaticClass.mode
						.equalsIgnoreCase(CommonStaticClass.SPECIALADD)) {

			q15_hour5 = ((EditText) findViewById(R.id.q15_hour5et)).getText()
					.toString();
			q16_hour5 = ((EditText) findViewById(R.id.q16_hour5et)).getText()
					.toString();

			// Check before insert
			sql = "SELECT EpisodeNo "
					+ "FROM tblEpisodes_Hour5 WHERE dataid = '"
					+ CommonStaticClass.dataId + "'" + " AND " + " childNo = '"
					+ CommonStaticClass.currentChildrenCount + "'"
					+ " AND EpisodeNo = '" + EpisodeNo + "'";

			Log.e("sql", sql);

			Cursor mCursor = null;

			boolean foundh5 = false;
			try {
				mCursor = DatabaseHelper.getInstance(con).getQueryCursor(sql);
				if (mCursor.moveToFirst()) {
					do {
						foundh5 = true;

					} while (mCursor.moveToNext());
				}
			} catch (Exception e) {
				// TODO: handle exception
				Log.e("cursor", "is null");
			} finally {
				if (mCursor != null)
					mCursor.close();

			}

			if (foundh5 == true) {
				sql = String
						.format("UPDATE tblEpisodes_Hour5 SET q15_hour5='%s', q16_hour5='%s',"
								+ "q17_hour5='%s', q18_hour5='%s', EditBy='%s',EditDate='%s' "
								+ " WHERE dataid = '%s' AND childno = '%s' AND EpisodeNo = '%s'",
								q15_hour5, q16_hour5, q17_hour5, q18_hour5,
								CommonStaticClass.userSpecificId, entryDate,
								CommonStaticClass.dataId,
								CommonStaticClass.currentChildrenCount,
								EpisodeNo);
			} else {

				sql = "INSERT INTO tblEpisodes_Hour5 (dataid,childNo,EpisodeNo,q15_hour5, q16_hour5,"
						+ "q17_hour5, q18_hour5, EntryBy,EntryDate) VALUES ('"
						+ CommonStaticClass.dataId
						+ "','"
						+ CommonStaticClass.currentChildrenCount
						+ "','"
						+ EpisodeNo
						+ "','"
						+ q15_hour5
						+ "','"
						+ q16_hour5
						+ "','"
						+ q17_hour5
						+ "','"
						+ q18_hour5
						+ "','"
						+ CommonStaticClass.userSpecificId
						+ "','"
						+ entryDate
						+ "')";
			}
		} else if (CommonStaticClass.mode
				.equalsIgnoreCase(CommonStaticClass.EDITMODE)) {

			q15_hour5 = ((EditText) findViewById(R.id.q15_hour5et)).getText()
					.toString();
			q16_hour5 = ((EditText) findViewById(R.id.q16_hour5et)).getText()
					.toString();

			sql = "SELECT EpisodeNo "
					+ "FROM tblEpisodes_Hour5 WHERE dataid = '"
					+ CommonStaticClass.dataId + "'" + " AND " + " childNo = '"
					+ CommonStaticClass.currentChildrenCount + "'"
					+ " AND EpisodeNo = '" + EpisodeNo + "'";

			Log.e("sql", sql);

			Cursor mCursor = null;

			boolean foundh5 = false;
			try {
				mCursor = DatabaseHelper.getInstance(con).getQueryCursor(sql);
				if (mCursor.moveToFirst()) {
					do {
						foundh5 = true;

					} while (mCursor.moveToNext());
				}
			} catch (Exception e) {
				// TODO: handle exception
				Log.e("cursor", "is null");
			} finally {
				if (mCursor != null)
					mCursor.close();

			}

			if (foundh5 == true) {
				sql = String
						.format("UPDATE tblEpisodes_Hour5 SET q15_hour5='%s', q16_hour5='%s',"
								+ "q17_hour5='%s', q18_hour5='%s', EditBy='%s',EditDate='%s' "
								+ " WHERE dataid = '%s' AND childno = '%s' AND EpisodeNo = '%s'",
								q15_hour5, q16_hour5, q17_hour5, q18_hour5,
								CommonStaticClass.userSpecificId, entryDate,
								CommonStaticClass.dataId,
								CommonStaticClass.currentChildrenCount,
								EpisodeNo);
			} else {
				sql = "INSERT INTO tblEpisodes_Hour5 (dataid,childNo,EpisodeNo,q15_hour5, q16_hour5,"
						+ "q17_hour5, q18_hour5, EntryBy,EntryDate) VALUES ('"
						+ CommonStaticClass.dataId
						+ "','"
						+ CommonStaticClass.currentChildrenCount
						+ "','"
						+ EpisodeNo
						+ "','"
						+ q15_hour5
						+ "','"
						+ q16_hour5
						+ "','"
						+ q17_hour5
						+ "','"
						+ q18_hour5
						+ "','"
						+ CommonStaticClass.userSpecificId
						+ "','"
						+ entryDate
						+ "')";
			}

			// CommonStaticClass.currentChildrenCount = 0;
		}

		try {
			if (DatabaseHelper.getInstance(con).executeDMLQuery(sql))

			{
				computeTotalUrineVolumeHour5();
				if (CommonStaticClass.mode
						.equalsIgnoreCase(CommonStaticClass.ADDMODE)) {

					Toast.makeText(con, "Episode Saved", Toast.LENGTH_LONG)
							.show();

					int v = _episodes_hour5.size() + 1;
					_episodes_hour5.add(String.valueOf(v));

					adapter14h5 = new ArrayAdapter(this,
							android.R.layout.simple_spinner_item,
							_episodes_hour5);
					adapter14h5.notifyDataSetChanged();
					ClearEpisodeHour5();
					return true;

				} else {
					Toast.makeText(con, "Data Updated for Episode",
							Toast.LENGTH_LONG).show();
					int v = _episodes_hour5.size() + 1;
					_episodes_hour5.add(String.valueOf(v));

					adapter14h5 = new ArrayAdapter(this,
							android.R.layout.simple_spinner_item,
							_episodes_hour5);
					adapter14h5.notifyDataSetChanged();

					// resetEveryValue();
					// resetSomeData();
					ClearEpisodeHour5();
					Log.e("Return for Edit", "Return true");
					return true;
					// finish();
				}
			} else {
				Toast.makeText(
						con,
						"An Error Occured. could not perform Episode insert / update",
						Toast.LENGTH_LONG).show();

			}
		} catch

		(Exception e) {
			return false;
		}
		return true;
	}

	protected void resetSomeData() {
		// TODO Auto-generated method stub
		CommonStaticClass.numberofchildren = 0;
		CommonStaticClass.currentChildrenCount = 1;
		CommonStaticClass.itemToEdite = null;
		CommonStaticClass.dataId = "";
	}

	protected void checkToFinish() {
		// TODO Auto-generated method stub
		Log.e("CommonStaticClass.numberofchildren", ""
				+ CommonStaticClass.numberofchildren);
		if (CommonStaticClass.numberofchildren == CommonStaticClass.currentChildrenCount) {

			if (CommonStaticClass.mode
					.equalsIgnoreCase(CommonStaticClass.SPECIALADD)) {
				sql = "Update tblMainQues  SET NumChildren='"
						+ CommonStaticClass.numberofchildren
						+ "' where dataid='" + CommonStaticClass.dataId + "'";

			}

			resetSomeData();
			Intent intnt = new Intent(SampleCollector.this, MenuScreen.class);
			startActivity(intnt);

			// finish();

		} else {
			CommonStaticClass.currentChildrenCount = CommonStaticClass.currentChildrenCount + 1;

		}
	}

	public void makeCopy() {
		try {

			DatabaseHelper.getInstance(con).copyDataBaseToSdcard();
			resetSomeData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void onResume() {
		super.onResume();
		if (CommonStaticClass.mode.equalsIgnoreCase(CommonStaticClass.EDITMODE)) {
			loadDataForEdit();
		}
	}

	private void loadDataForEdit() {

		if (CommonStaticClass.itemToEdite != null) {
			q5_prev = CommonStaticClass.itemToEdite.getQ5();

			q6_prev = CommonStaticClass.itemToEdite.getQ6();
			q7_prev = CommonStaticClass.itemToEdite.getQ7();
			q8_prev = CommonStaticClass.itemToEdite.getQ8();
			q8_other_prev = CommonStaticClass.itemToEdite.getQ8_other();
			q41_prev = CommonStaticClass.itemToEdite.getQ41();
			q47_prev = CommonStaticClass.itemToEdite.getQ47();
			q48_prev = CommonStaticClass.itemToEdite.getQ48();
			q49_prev = CommonStaticClass.itemToEdite.getQ49();
			q50_prev = CommonStaticClass.itemToEdite.getQ50();
			q51_prev = CommonStaticClass.itemToEdite.getQ51();
			q52s1hour2_prev = CommonStaticClass.itemToEdite.getQ52s1hour2();
			q52s2hour2_prev = CommonStaticClass.itemToEdite.getQ52s2hour2();
			q52s3hour2_prev = CommonStaticClass.itemToEdite.getQ52s3hour2();
			q52s4hour2_prev = CommonStaticClass.itemToEdite.getQ52s4hour2();
			q52s5hour2_prev = CommonStaticClass.itemToEdite.getQ52s5hour2();
			q52s6hour2_prev = CommonStaticClass.itemToEdite.getQ52s6hour2();
			
			q53s1hour2_prev = CommonStaticClass.itemToEdite.getQ53s1hour2();
			q53s2hour2_prev = CommonStaticClass.itemToEdite.getQ53s2hour2();
			q53s3hour2_prev = CommonStaticClass.itemToEdite.getQ53s3hour2();
			q53s4hour2_prev = CommonStaticClass.itemToEdite.getQ53s4hour2();
			q53s5hour2_prev = CommonStaticClass.itemToEdite.getQ53s5hour2();
			q53s6hour2_prev = CommonStaticClass.itemToEdite.getQ53s6hour2();
			/*q32s7hour5_prev = CommonStaticClass.itemToEdite.getQ32s7hour5();
			q32s8hour5_prev = CommonStaticClass.itemToEdite.getQ32s8hour5();
			q32s9hour5_prev = CommonStaticClass.itemToEdite.getQ32s9hour5();
			q32s10hour5_prev = CommonStaticClass.itemToEdite.getQ32s10hour5();
			q32s11hour5_prev = CommonStaticClass.itemToEdite.getQ32s11hour5();
			q32s12hour5_prev = CommonStaticClass.itemToEdite.getQ32s12hour5();*/

			q52s1hour2 = q52s1hour2_prev;
			q52s2hour2 = q52s2hour2_prev;
			q52s3hour2 = q52s3hour2_prev;
			q52s4hour2 = q52s4hour2_prev;
			q52s5hour2 = q52s5hour2_prev;
			q52s6hour2 = q52s6hour2_prev;
			/*q31s7hour5 = q31s7hour5_prev;
			q31s8hour5 = q31s8hour5_prev;
			q31s9hour5 = q31s9hour5_prev;
			q31s10hour5 = q31s10hour5_prev;
			q31s11hour5 = q31s11hour5_prev;
			q31s12hour5 = q31s12hour5_prev;*/
			q53s1hour2 = q53s1hour2_prev;
			q53s2hour2 = q53s2hour2_prev;
			q53s3hour2 = q53s3hour2_prev;
			q53s4hour2 = q53s4hour2_prev;
			q53s5hour2 = q53s5hour2_prev;
			q53s6hour2 = q53s6hour2_prev;
			/*q32s7hour5 = q32s7hour5_prev;
			q32s8hour5 = q32s8hour5_prev;
			q32s9hour5 = q32s9hour5_prev;
			q32s10hour5 = q32s10hour5_prev;
			q32s11hour5 = q32s11hour5_prev;
			q32s12hour5 = q32s12hour5_prev;*/

			((TextView) findViewById(R.id.q52s1hour2)).setText(q52s1hour2);
			((TextView) findViewById(R.id.q52s2hour2)).setText(q52s2hour2);
			((TextView) findViewById(R.id.q52s3hour2)).setText(q52s3hour2);
			((TextView) findViewById(R.id.q52s4hour2)).setText(q52s4hour2);
			((TextView) findViewById(R.id.q52s5hour2)).setText(q52s5hour2);
			((TextView) findViewById(R.id.q52s6hour2)).setText(q52s6hour2);
			/*((TextView) findViewById(R.id.q31s7hour5)).setText(q31s7hour5);
			((TextView) findViewById(R.id.q31s8hour5)).setText(q31s8hour5);
			((TextView) findViewById(R.id.q31s9hour5)).setText(q31s9hour5);
			((TextView) findViewById(R.id.q31s10hour5)).setText(q31s10hour5);
			((TextView) findViewById(R.id.q31s11hour5)).setText(q31s11hour5);
			((TextView) findViewById(R.id.q31s12hour5)).setText(q31s12hour5);
*/
			((TextView) findViewById(R.id.q53s1hour2)).setText(q53s1hour2);
			((TextView) findViewById(R.id.q53s2hour2)).setText(q53s2hour2);
			((TextView) findViewById(R.id.q53s3hour2)).setText(q53s3hour2);
			((TextView) findViewById(R.id.q53s4hour2)).setText(q53s4hour2);
			((TextView) findViewById(R.id.q53s5hour2)).setText(q53s5hour2);
			((TextView) findViewById(R.id.q53s6hour2)).setText(q53s6hour2);
			/*((TextView) findViewById(R.id.q32s7hour5)).setText(q32s7hour5);
			((TextView) findViewById(R.id.q32s8hour5)).setText(q32s8hour5);
			((TextView) findViewById(R.id.q32s9hour5)).setText(q32s9hour5);
			((TextView) findViewById(R.id.q32s10hour5)).setText(q32s10hour5);
			((TextView) findViewById(R.id.q32s11hour5)).setText(q32s11hour5);
			((TextView) findViewById(R.id.q32s12hour5)).setText(q32s12hour5);*/

			q54s1hour2_prev = CommonStaticClass.itemToEdite.getQ54s1hour2();
			q54s2hour2_prev = CommonStaticClass.itemToEdite.getQ54s2hour2();
			q54s3hour2_prev = CommonStaticClass.itemToEdite.getQ54s3hour2();
			q54s4hour2_prev = CommonStaticClass.itemToEdite.getQ54s4hour2();
			q54s5hour2_prev = CommonStaticClass.itemToEdite.getQ54s5hour2();
			q54s6hour2_prev = CommonStaticClass.itemToEdite.getQ54s6hour2();
			/*q33s7hour5_prev = CommonStaticClass.itemToEdite.getQ33s7hour5();
			q33s8hour5_prev = CommonStaticClass.itemToEdite.getQ33s8hour5();
			q33s9hour5_prev = CommonStaticClass.itemToEdite.getQ33s9hour5();
			q33s10hour5_prev = CommonStaticClass.itemToEdite.getQ33s10hour5();
			q33s11hour5_prev = CommonStaticClass.itemToEdite.getQ33s11hour5();
			q33s12hour5_prev = CommonStaticClass.itemToEdite.getQ33s12hour5();*/
			q55s1hour2_prev = CommonStaticClass.itemToEdite.getQ55s1hour2();
			q55s2hour2_prev = CommonStaticClass.itemToEdite.getQ55s2hour2();
			q55s3hour2_prev = CommonStaticClass.itemToEdite.getQ55s3hour2();
			q55s4hour2_prev = CommonStaticClass.itemToEdite.getQ55s4hour2();
			q55s5hour2_prev = CommonStaticClass.itemToEdite.getQ55s5hour2();
			q55s6hour2_prev = CommonStaticClass.itemToEdite.getQ55s6hour2();
			/*q34s7hour5_prev = CommonStaticClass.itemToEdite.getQ34s7hour5();
			q34s8hour5_prev = CommonStaticClass.itemToEdite.getQ34s8hour5();
			q34s9hour5_prev = CommonStaticClass.itemToEdite.getQ34s9hour5();
			q34s10hour5_prev = CommonStaticClass.itemToEdite.getQ34s10hour5();
			q34s11hour5_prev = CommonStaticClass.itemToEdite.getQ34s11hour5();
			q34s12hour5_prev = CommonStaticClass.itemToEdite.getQ34s12hour5();*/
			q56hour2_prev = CommonStaticClass.itemToEdite.getQ56hour2();
			q56hour2_other_prev = CommonStaticClass.itemToEdite
					.getQ56hour2_other();

			q57hour2_prev = CommonStaticClass.itemToEdite.getQ57hour2();
			/*q35hour5_prev = CommonStaticClass.itemToEdite.getQ35hour5();
			q35hour5_other_prev = CommonStaticClass.itemToEdite
					.getQ35hour5_other();*/

			//q36hour5_prev = CommonStaticClass.itemToEdite.getQ36hour5();

			// Load spinners
			_episodes_hour2 = new ArrayList<String>();
			//_episodes_hour5 = new ArrayList<String>();
			String sql = "SELECT EpisodeNo "
					+ "FROM tblEpisodes_Hour2 WHERE dataid = '"
					+ CommonStaticClass.dataId + "'" + " AND " + " childNo = '"
					+ CommonStaticClass.currentChildrenCount + "'";

			Cursor mCursor = null;

			try {
				mCursor = DatabaseHelper.getInstance(con).getQueryCursor(sql);
				if (mCursor.moveToFirst()) {
					do {
						_episodes_hour2.add(mCursor.getString(mCursor
								.getColumnIndex("EpisodeNo")));

					} while (mCursor.moveToNext());
				}
			} catch (Exception e) {
				// TODO: handle exception
				Log.e("cursor", "is null");
			} finally {
				if (mCursor != null)
					mCursor.close();

			}

			adapter14h2 = new ArrayAdapter(this,
					android.R.layout.simple_spinner_item, _episodes_hour2);
			adapter14h2
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			q42_hour2_spinner.setAdapter(adapter14h2);
			adapter14h2.notifyDataSetChanged();

			if (q42_hour2_spinner.getCount() == 0) {
				loadEpisodeHour2();
			}

			/*sql = "SELECT EpisodeNo "
					+ "FROM tblEpisodes_Hour5 WHERE dataid = '"
					+ CommonStaticClass.dataId + "'" + " AND " + " childNo = '"
					+ CommonStaticClass.currentChildrenCount + "'";

			mCursor = null;*/

			/*try {
				mCursor = DatabaseHelper.getInstance(con).getQueryCursor(sql);
				if (mCursor.moveToFirst()) {
					do {
						_episodes_hour5.add(mCursor.getString(mCursor
								.getColumnIndex("EpisodeNo")));

					} while (mCursor.moveToNext());
				}
			} catch (Exception e) {
				// TODO: handle exception
				Log.e("cursor", "is null");
			} finally {
				if (mCursor != null)
					mCursor.close();

			}*/

			/*adapter14h5 = new ArrayAdapter(this,
					android.R.layout.simple_spinner_item, _episodes_hour5);
			adapter14h5
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			q14_hour5_spinner.setAdapter(adapter14h5);
			adapter14h5.notifyDataSetChanged();

			if (q14_hour5_spinner.getCount() == 0) {
				loadEpisodeHour5();
			}*/

			// end Spinners

			((EditText) findViewById(R.id.q6)).setText(q6_prev);

			q7 = q7_prev;
			if (q7 != null) {
				if (q7.trim() != "" && q7.length() > 0) {

					switch (Integer.parseInt(q7)) {
					case 1:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup7))
								.findViewById(R.id.radio0)).toggle();
						//findViewById(R.id.lnq37).setVisibility(View.VISIBLE);
						break;
					case 2:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup7))
								.findViewById(R.id.radio1)).toggle();
						//findViewById(R.id.lnq37).setVisibility(View.GONE);
						break;
					}
				}
			}
			q8 = q8_prev;
			if (q8 != null) {
				if (!q8.toString().equalsIgnoreCase("null")) {
					if (q8.trim() != "" && q8.length() > 0) {

						switch (Integer.parseInt(q8)) {
						case 1:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup8))
									.findViewById(R.id.radio0)).toggle();
							break;
						case 2:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup8))
									.findViewById(R.id.radio1)).toggle();
							break;

						case 3:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup8))
									.findViewById(R.id.radio2)).toggle();
							break;
						case 4:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup8))
									.findViewById(R.id.radio3)).toggle();
							break;
						case 5:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup8))
									.findViewById(R.id.radio4)).toggle();

							q8_other = q8_other_prev;
							((EditText) findViewById(R.id.q8_otheret))
									.setText(q8_other);

							break;

						}
					}
				}
			}
			
			q41 = q41_prev;

		
			((EditText) findViewById(R.id.q41et)).setText(q41);

			q47 = q47_prev;
			((EditText) findViewById(R.id.q47et)).setText(q47);

			q48 = q48_prev;
			if (q48 != null) {
				((EditText) findViewById(R.id.q48et)).setText(q48);
			} else {
				((EditText) findViewById(R.id.q48et)).setText("0.0");
			}

			q49 = q49_prev;
			((EditText) findViewById(R.id.q49et)).setText(q49);
			q50 = q50_prev;
			((EditText) findViewById(R.id.q50et)).setText(q50);
			q51 = q51_prev;
			((EditText) findViewById(R.id.q51et)).setText(q51);
			

			q55s1hour2 = q55s1hour2_prev;

			if (q55s1hour2 != null) {
				if (q55s1hour2.trim() != "" && q55s1hour2.length() > 0) {

					switch (Integer.parseInt(q55s1hour2)) {
					case 1:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup55s1))
								.findViewById(R.id.radio0)).toggle();
						break;
					case 2:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup55s1))
								.findViewById(R.id.radio1)).toggle();
						break;

					case 3:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup55s1))
								.findViewById(R.id.radio2)).toggle();
						break;
					}
				}
			}
			q55s2hour2 = q55s2hour2_prev;

			if (q55s2hour2 != null) {
				if (q55s2hour2.trim() != "" && q55s2hour2.length() > 0) {

					switch (Integer.parseInt(q55s2hour2)) {
					case 1:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup55s2))
								.findViewById(R.id.radio0)).toggle();
						break;
					case 2:

						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup55s2))
								.findViewById(R.id.radio1)).toggle();
						break;

					case 3:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup55s2))
								.findViewById(R.id.radio2)).toggle();
						break;
					}
				}
			}
			q55s3hour2 = q55s3hour2_prev;

			if (q55s3hour2 != null) {
				if (q55s3hour2.trim() != "" && q55s3hour2.length() > 0) {

					switch (Integer.parseInt(q55s3hour2)) {
					case 1:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup55s3))
								.findViewById(R.id.radio0)).toggle();
						break;
					case 2:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup55s3))
								.findViewById(R.id.radio1)).toggle();
						break;

					case 3:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup55s3))
								.findViewById(R.id.radio2)).toggle();
						break;
					}
				}
			}
			q55s4hour2 = q55s4hour2_prev;

			if (q55s4hour2 != null) {
				if (q55s4hour2.trim() != "" && q55s4hour2.length() > 0) {

					switch (Integer.parseInt(q55s4hour2)) {
					case 1:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup55s4))
								.findViewById(R.id.radio0)).toggle();
						break;
					case 2:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup55s4))
								.findViewById(R.id.radio1)).toggle();
						break;

					case 3:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup55s4))
								.findViewById(R.id.radio2)).toggle();
						break;
					}
				}
			}
			q55s5hour2 = q55s5hour2_prev;

			if (q55s5hour2 != null) {
				if (q55s5hour2.trim() != "" && q55s5hour2.length() > 0) {

					switch (Integer.parseInt(q55s5hour2)) {
					case 1:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup55s5))
								.findViewById(R.id.radio0)).toggle();
						break;
					case 2:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup55s5))
								.findViewById(R.id.radio1)).toggle();
						break;

					case 3:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup55s5))
								.findViewById(R.id.radio2)).toggle();
						break;
					}
				}
			}
			q55s6hour2 = q55s6hour2_prev;

			if (q55s5hour2 != null) {
				if (q55s6hour2.trim() != "" && q55s6hour2.length() > 0) {

					switch (Integer.parseInt(q55s6hour2)) {
					case 1:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup55s6))
								.findViewById(R.id.radio0)).toggle();
						break;
					case 2:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup55s6))
								.findViewById(R.id.radio1)).toggle();
						break;

					case 3:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup55s6))
								.findViewById(R.id.radio2)).toggle();
						break;
					}
				}
			}
			/*q34s7hour5 = q34s7hour5_prev;

			if (q34s7hour5 != null) {
				if (q34s7hour5.trim() != "" && q34s7hour5.length() > 0) {

					switch (Integer.parseInt(q34s7hour5)) {
					case 1:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup34s7))
								.findViewById(R.id.radio0)).toggle();
						break;
					case 2:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup34s7))
								.findViewById(R.id.radio1)).toggle();
						break;

					case 3:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup34s7))
								.findViewById(R.id.radio2)).toggle();
						break;
					}
				}
			}
			q34s8hour5 = q34s8hour5_prev;

			if (q34s8hour5 != null) {
				if (q34s8hour5.trim() != "" && q34s8hour5.length() > 0) {

					switch (Integer.parseInt(q34s8hour5)) {
					case 1:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup34s8))
								.findViewById(R.id.radio0)).toggle();
						break;
					case 2:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup34s8))
								.findViewById(R.id.radio1)).toggle();
						break;

					case 3:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup34s8))
								.findViewById(R.id.radio2)).toggle();
						break;
					}
				}
			}
			q34s9hour5 = q34s9hour5_prev;

			if (q34s9hour5 != null) {
				if (q34s9hour5.trim() != "" && q34s9hour5.length() > 0) {

					switch (Integer.parseInt(q34s9hour5)) {
					case 1:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup34s9))
								.findViewById(R.id.radio0)).toggle();
						break;
					case 2:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup34s9))
								.findViewById(R.id.radio1)).toggle();
						break;

					case 3:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup34s9))
								.findViewById(R.id.radio2)).toggle();
						break;
					}
				}
			}
			q34s10hour5 = q34s10hour5_prev;

			if (q34s10hour5 != null) {
				if (q34s10hour5.trim() != "" && q34s10hour5.length() > 0) {

					switch (Integer.parseInt(q34s10hour5)) {
					case 1:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup34s10))
								.findViewById(R.id.radio0)).toggle();
						break;
					case 2:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup34s10))
								.findViewById(R.id.radio1)).toggle();
						break;

					case 3:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup34s10))
								.findViewById(R.id.radio2)).toggle();
						break;
					}
				}
			}
			q34s11hour5 = q34s11hour5_prev;

			if (q34s11hour5 != null) {
				if (q34s11hour5.trim() != "" && q34s11hour5.length() > 0) {

					switch (Integer.parseInt(q34s11hour5)) {
					case 1:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup34s11))
								.findViewById(R.id.radio0)).toggle();
						break;
					case 2:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup34s11))
								.findViewById(R.id.radio1)).toggle();
						break;

					case 3:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup34s11))
								.findViewById(R.id.radio2)).toggle();
						break;
					}
				}
			}
			q34s12hour5 = q34s12hour5_prev;

			if (q34s12hour5 != null) {
				if (q34s12hour5.trim() != "" && q34s12hour5.length() > 0) {

					switch (Integer.parseInt(q34s12hour5)) {
					case 1:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup34s12))
								.findViewById(R.id.radio0)).toggle();
						break;
					case 2:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup34s12))
								.findViewById(R.id.radio1)).toggle();
						break;

					case 3:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup34s12))
								.findViewById(R.id.radio2)).toggle();
						break;
					}
				}
			}*/
			q56hour2 = q56hour2_prev;
			// q35_othereth2
			if (q56hour2 != null) {
				if (q56hour2.trim() != "" && q56hour2.length() > 0) {

					switch (Integer.parseInt(q56hour2)) {
					case 1:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup56h2))
								.findViewById(R.id.radio0)).toggle();
						break;
					case 2:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup56h2))
								.findViewById(R.id.radio1)).toggle();
						break;

					case 3:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup56h2))
								.findViewById(R.id.radio2)).toggle();
						break;
					case 4:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup56h2))
								.findViewById(R.id.radio3)).toggle();
						break;
					case 5:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup56h2))
								.findViewById(R.id.radio4)).toggle();
						((EditText) findViewById(R.id.q56_othereth2))
								.setText(q56hour2_other_prev);
						break;
					}
				}
			}

			q57hour2 = q57hour2_prev;
			((EditText) findViewById(R.id.q57eth2)).setText(q57hour2);

			//q35hour5 = q35hour5_prev;

			/*if (q35hour5 != null) {
				if (q35hour5.trim() != "" && q35hour5.length() > 0) {

					switch (Integer.parseInt(q35hour5)) {
					case 1:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup35h5))
								.findViewById(R.id.radio0)).toggle();
						break;
					case 2:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup35h5))
								.findViewById(R.id.radio1)).toggle();
						break;

					case 3:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup35h5))
								.findViewById(R.id.radio2)).toggle();
						break;
					case 4:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup35h5))
								.findViewById(R.id.radio3)).toggle();
						break;
					case 5:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup35h5))
								.findViewById(R.id.radio4)).toggle();
						((EditText) findViewById(R.id.q35_othereth5))
								.setText(q35hour5_other_prev);
						break;
					}
				}
			}
			q36hour5 = q36hour5_prev;
			((EditText) findViewById(R.id.q36eth5)).setText(q36hour5);*/

			/*q37_prev = CommonStaticClass.itemToEdite.getQ37();
			q37_other_prev = CommonStaticClass.itemToEdite.getQ37_other();

			q37 = q37_prev;
			q37_other = q37_other_prev;

			if (q37 != null) {
				if (q37.trim() != "" && q37.length() > 0) {

					switch (Integer.parseInt(q37)) {
					case 1:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup37))
								.findViewById(R.id.radio0)).toggle();
						break;
					case 2:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup37))
								.findViewById(R.id.radio1)).toggle();
						break;

					case 3:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup37))
								.findViewById(R.id.radio2)).toggle();
						break;
					case 4:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup37))
								.findViewById(R.id.radio3)).toggle();
						break;
					case 5:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup37))
								.findViewById(R.id.radio4)).toggle();

						break;

					case 6:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup37))
								.findViewById(R.id.radio5)).toggle();
						((TextView) findViewById(R.id.q37_other))
								.setText(q37_other);
						break;

					case 77:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup37))
								.findViewById(R.id.radio6)).toggle();

						break;
					}
				}
			}
			*/
	/*		q38_prev = CommonStaticClass.itemToEdite.getQ38();
			q38 = q38_prev;
			((EditText) findViewById(R.id.q38eth5))
			.setText(q38);*/
			
			computeTotalUrineVolumeHour2();
			//computeTotalUrineVolumeHour5();

		}

	}

	private boolean CheckAliquotAndShowMessage(RadioGroup CurrentRadioGroup,
			RadioGroup PreviousRadioGroup) {

		if (((RadioButton) ((RadioGroup) CurrentRadioGroup)
				.findViewById(R.id.radio1)).isChecked()) {

			if (((RadioButton) ((RadioGroup) PreviousRadioGroup)
					.findViewById(R.id.radio0)).isChecked()) {
				CurrentRadioGroup.clearCheck();

				CommonStaticClass
						.showMyAlert(con, "Message",
								"Previous value is No Aliquot. Please select valid Aliquot for this option.");
				return false;
			}
		}

		if (((RadioButton) ((RadioGroup) CurrentRadioGroup)
				.findViewById(R.id.radio2)).isChecked()) {

			if (((RadioButton) ((RadioGroup) PreviousRadioGroup)
					.findViewById(R.id.radio0)).isChecked()) {

				CurrentRadioGroup.clearCheck();
				/*
				 * ((RadioButton) ((RadioGroup) CurrentRadioGroup)
				 * .findViewById(R.id.radio2)).setChecked(false);
				 */
				CommonStaticClass
						.showMyAlert(con, "Message",
								"Previous value is No Aliquot. Please select valid Aliquot for this option.");
				return false;
			}
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

	protected ArrayList<String> GetUser() {
		ArrayList<String> str = new ArrayList<String>();

		String sql = "Select * from tblUser";
		Cursor mCursor = null;
		try {
			mCursor = DatabaseHelper.getInstance(con).getQueryCursor(sql);
			if (mCursor.moveToFirst()) {
				do {
					str.add(mCursor.getString(mCursor.getColumnIndex("ID"))
							+ " : "
							+ mCursor.getString(mCursor.getColumnIndex("Name")));
				} while (mCursor.moveToNext());
			}
		} catch (Exception e) {

		} finally {
			if (mCursor != null)
				mCursor.close();
		}
		return str;
	}

	protected String getAliq(String sqlq) {
		String aliqq = "";
		Cursor mCursor = null;
		try {
			mCursor = DatabaseHelper.getInstance(con).getQueryCursor(sqlq);
			if (mCursor.moveToFirst()) {
				do {
					aliqq = mCursor.getString(mCursor.getColumnIndex("q17"));
				} while (mCursor.moveToNext());
			}
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("cursor", "is null");
		} finally {
			if (mCursor != null)
				mCursor.close();
		}
		return aliqq;
	}

	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG:
			return new DatePickerDialog(this, dateSetListener, dateYear,
					dateMonth, dateDay);
		case TIME_DIALOG:

			return new TimePickerDialog(this, timeSetListener, hour, min, true);
		}
		return null;
	}

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
			view.setIs24HourView(true);

			hour = hourOfDay;
			min = minute;
			updateDisplay("time");
		}
	};

	private void updateDisplay(String dt) {
		if (dt.contains("date")) {
			/*
			 * String date = new StringBuilder() // Month is 0 based so add 1
			 * .append(dateMonth + 1).append("-").append(dateDay)
			 * .append("-").append(dateYear).toString(); pickDate.setText(date);
			 */

			String date = new StringBuilder()
					// Month is 0 based so add 1

					.append(dateDay).append(" ")
					.append(GetMonth(dateMonth + 1)).append(" ")
					.append(dateYear).toString();
			pickDate.setText(date);

		}
		if (dt.contains("time")) {
			String date = new StringBuilder()

			.append(hour).append(":").append(String.format("%02d", min))
					.toString();
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

	public void enableQuestion(int i) {
		switch (i) {
		case 6:
			/*
			 * findViewById(R.id.textView17).setVisibility(View.VISIBLE);
			 * q6Ed.setVisibility(View.VISIBLE);
			 */

			break;
		case 7:
			/*
			 * findViewById(R.id.textView18).setVisibility(View.VISIBLE);
			 * q7Ed.setVisibility(View.VISIBLE);
			 */

			break;
		case 8:
			// findViewById(R.id.textView1).setVisibility(View.VISIBLE);
			// q8Ed.setVisibility(View.VISIBLE);

			break;
		case 9:
			// findViewById(R.id.textView2).setVisibility(View.VISIBLE);
			// randomIdHolder.setVisibility(View.VISIBLE);
			break;
		case 10:
			// findViewById(R.id.textView4).setVisibility(View.VISIBLE);
			// radioGroupq10.setVisibility(View.VISIBLE);

			break;
		/*
		 * case 11:
		 * 
		 * findViewById(R.id.textViewq11).setVisibility(View.VISIBLE);
		 * findViewById(R.id.radioGroup11).setVisibility(View.VISIBLE); break;
		 * 
		 * case 12: findViewById(R.id.TextView12).setVisibility(View.VISIBLE);
		 * findViewById(R.id.q12et).setVisibility(View.VISIBLE); break; case 13:
		 * findViewById(R.id.TextView02).setVisibility(View.VISIBLE);
		 * findViewById(R.id.q13et).setVisibility(View.VISIBLE);
		 * 
		 * break; case 14:
		 * findViewById(R.id.TextView03).setVisibility(View.VISIBLE);
		 * findViewById(R.id.radioGroup14).setVisibility(View.VISIBLE); break;
		 * case 15: findViewById(R.id.TextView04).setVisibility(View.VISIBLE);
		 * findViewById(R.id.q15et).setVisibility(View.VISIBLE); break; case 16:
		 * findViewById(R.id.TextView05).setVisibility(View.VISIBLE);
		 * findViewById(R.id.radioGroup16).setVisibility(View.VISIBLE); break;
		 * case 17: findViewById(R.id.TextView17).setVisibility(View.VISIBLE);
		 * findViewById(R.id.radioGroup17).setVisibility(View.VISIBLE); break;
		 * case 18: findViewById(R.id.TextView06).setVisibility(View.VISIBLE);
		 * findViewById(R.id.radioGroup18).setVisibility(View.VISIBLE); break;
		 * 
		 * case 19: findViewById(R.id.textView19).setVisibility(View.VISIBLE);
		 * findViewById(R.id.radioGroup19).setVisibility(View.VISIBLE); break;
		 * 
		 * case 20:
		 * 
		 * findViewById(R.id.textView20).setVisibility(View.VISIBLE);
		 * findViewById(R.id.q48et).setVisibility(View.VISIBLE); break;
		 * 
		 * case 21: // q18
		 * findViewById(R.id.textView21).setVisibility(View.VISIBLE);
		 * findViewById(R.id.q21et).setVisibility(View.VISIBLE); //
		 * q18Ed.setVisibility(View.GONE); break; case 22: // q18
		 * findViewById(R.id.textView22).setVisibility(View.VISIBLE);
		 * findViewById(R.id.q22et).setVisibility(View.VISIBLE); //
		 * q18Ed.setVisibility(View.GONE); break;
		 */

		}
	}

	public void disableQuestion(int i) {
		switch (i) {
		case 6:
			/*
			 * findViewById(R.id.textView17).setVisibility(View.GONE);
			 * q6Ed.setVisibility(View.GONE);
			 */
			break;
		case 7:
			/*
			 * findViewById(R.id.textView18).setVisibility(View.GONE);
			 * q7Ed.setVisibility(View.GONE);
			 */
			break;
		case 8:

			break;
		case 9:

			// randomIdHolder.setVisibility(View.GONE);
			break;
		case 10:

			break;
		case 11:
			/*
			 * findViewById(R.id.textViewq11).setVisibility(View.GONE);
			 * findViewById(R.id.radioGroup11).setVisibility(View.GONE); if
			 * (q10s1.equalsIgnoreCase("2")) {
			 * findViewById(R.id.textViewq11).setVisibility(View.VISIBLE);
			 * findViewById(R.id.radioGroup11).setVisibility(View.VISIBLE);
			 * 
			 * } if (q10s2.equalsIgnoreCase("2")) {
			 * findViewById(R.id.textViewq11).setVisibility(View.VISIBLE);
			 * findViewById(R.id.radioGroup11).setVisibility(View.VISIBLE);
			 * 
			 * } if (q10s3.equalsIgnoreCase("2")) {
			 * findViewById(R.id.textViewq11).setVisibility(View.VISIBLE);
			 * findViewById(R.id.radioGroup11).setVisibility(View.VISIBLE);
			 * 
			 * } if (q10s4.equalsIgnoreCase("2")) {
			 * findViewById(R.id.textViewq11).setVisibility(View.VISIBLE);
			 * findViewById(R.id.radioGroup11).setVisibility(View.VISIBLE);
			 * 
			 * } if (q10s5.equalsIgnoreCase("2")) {
			 * findViewById(R.id.textViewq11).setVisibility(View.VISIBLE);
			 * findViewById(R.id.radioGroup11).setVisibility(View.VISIBLE);
			 * 
			 * }
			 * 
			 * break; case 12:
			 * findViewById(R.id.TextView12).setVisibility(View.GONE);
			 * findViewById(R.id.q12et).setVisibility(View.GONE); break; case
			 * 13: findViewById(R.id.TextView02).setVisibility(View.GONE);
			 * findViewById(R.id.q13et).setVisibility(View.GONE);
			 * 
			 * break; case 14:
			 * findViewById(R.id.TextView03).setVisibility(View.GONE);
			 * findViewById(R.id.radioGroup14).setVisibility(View.GONE); break;
			 * case 15: findViewById(R.id.TextView04).setVisibility(View.GONE);
			 * findViewById(R.id.q15et).setVisibility(View.GONE); break; case
			 * 16: findViewById(R.id.TextView05).setVisibility(View.GONE);
			 * findViewById(R.id.radioGroup16).setVisibility(View.GONE); break;
			 * case 17: findViewById(R.id.TextView17).setVisibility(View.GONE);
			 * findViewById(R.id.radioGroup17).setVisibility(View.GONE); break;
			 * case 18: findViewById(R.id.TextView06).setVisibility(View.GONE);
			 * findViewById(R.id.radioGroup18).setVisibility(View.GONE); break;
			 * 
			 * case 19: findViewById(R.id.textView19).setVisibility(View.GONE);
			 * findViewById(R.id.radioGroup19).setVisibility(View.GONE); break;
			 * 
			 * case 20:
			 * 
			 * findViewById(R.id.textView20).setVisibility(View.GONE);
			 * findViewById(R.id.q48et).setVisibility(View.GONE); break;
			 * 
			 * case 21: // q18
			 * findViewById(R.id.textView21).setVisibility(View.GONE);
			 * findViewById(R.id.q21et).setVisibility(View.GONE); //
			 * q18Ed.setVisibility(View.GONE); break; case 22: // q18
			 * findViewById(R.id.textView22).setVisibility(View.GONE);
			 * findViewById(R.id.q22et).setVisibility(View.GONE); //
			 * q18Ed.setVisibility(View.GONE);
			 */break;
		}
	}

	public void disableall() {
		findViewById(R.id.sample_end_2hourln).setVisibility(View.GONE);
		//findViewById(R.id.sample_end_5hourln).setVisibility(View.GONE);
		findViewById(R.id.sample_start_hour2gl).setVisibility(View.GONE);
		//findViewById(R.id.sample_start_hour5gl).setVisibility(View.GONE);
		findViewById(R.id.observation_grid_hour2gl).setVisibility(View.GONE);
		//findViewById(R.id.observation_grid_hour5gl).setVisibility(View.GONE);

		findViewById(R.id.textView35h2).setVisibility(View.GONE);
		findViewById(R.id.q56_othereth2).setVisibility(View.GONE);
		findViewById(R.id.radioGroup56h2).setVisibility(View.GONE);

//		findViewById(R.id.textView35h5).setVisibility(View.GONE);
	//	findViewById(R.id.q35_othereth5).setVisibility(View.GONE);
		//findViewById(R.id.radioGroup35h5).setVisibility(View.GONE);

		findViewById(R.id.textView36h2).setVisibility(View.GONE);
		findViewById(R.id.q57eth2).setVisibility(View.GONE);

		//findViewById(R.id.textView36h5).setVisibility(View.GONE);
		//findViewById(R.id.q36eth5).setVisibility(View.GONE);

	}

	public void enableall() {
		findViewById(R.id.sample_end_2hourln).setVisibility(View.VISIBLE);
		//findViewById(R.id.sample_end_5hourln).setVisibility(View.VISIBLE);
		findViewById(R.id.sample_start_hour2gl).setVisibility(View.VISIBLE);
		//findViewById(R.id.sample_start_hour5gl).setVisibility(View.VISIBLE);
		findViewById(R.id.observation_grid_hour2gl).setVisibility(View.VISIBLE);
		//findViewById(R.id.observation_grid_hour5gl).setVisibility(View.VISIBLE);

		findViewById(R.id.textView35h2).setVisibility(View.VISIBLE);
		// findViewById(R.id.q35_othereth2).setVisibility(View.VISIBLE);
		findViewById(R.id.radioGroup56h2).setVisibility(View.VISIBLE);

//		findViewById(R.id.textView35h5).setVisibility(View.VISIBLE);
		// findViewById(R.id.q35_othereth5).setVisibility(View.VISIBLE);
	//	findViewById(R.id.radioGroup35h5).setVisibility(View.VISIBLE);

		// findViewById(R.id.textView36h2).setVisibility(View.VISIBLE);
		// findViewById(R.id.q36eth2).setVisibility(View.VISIBLE);

		// findViewById(R.id.textView36h5).setVisibility(View.VISIBLE);
		// findViewById(R.id.q36eth5).setVisibility(View.VISIBLE);
	}

	private void SetToInvisible() {
		findViewById(R.id.q8_otheret).setVisibility(View.GONE);
	}

	private void loadtab() {
		TabHost th = (TabHost) findViewById(R.id.tabhost);
		th.setup();
		TabSpec ts = th.newTabSpec("tab1");
		ts.setContent(R.id.tab1);
		ts.setIndicator("Start");
		th.addTab(ts);

		ts = th.newTabSpec("tab2");
		ts.setContent(R.id.tab2);
		ts.setIndicator("2 Hour");
		th.addTab(ts);

		ts = th.newTabSpec("tab3");
		ts.setContent(R.id.tab3);
		ts.setIndicator("2 Hour End");
		th.addTab(ts);

		ts = th.newTabSpec("tab6");
		ts.setContent(R.id.tab6);
		ts.setIndicator("Observation (Hour 2)");
		th.addTab(ts);

		/*ts = th.newTabSpec("tab4");
		ts.setContent(R.id.tab4);
		ts.setIndicator("5 Hour");
		th.addTab(ts);

		ts = th.newTabSpec("tab5");
		ts.setContent(R.id.tab5);
		ts.setIndicator("5 Hour End");
		th.addTab(ts);

		ts = th.newTabSpec("tab6");
		ts.setContent(R.id.tab7);
		ts.setIndicator("Observation (Hour 5)");
		th.addTab(ts);*/

	}

	private void loadEpisodeHour2() {
		q42_hour2_spinner = (Spinner) findViewById(R.id.q42_hour2);
		_episodes_hour2 = new ArrayList<String>();
		_episodes_hour2.add("1");

		adapter14h2 = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item, _episodes_hour2);
		adapter14h2
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		q42_hour2_spinner.setAdapter(adapter14h2);

		q42_hour2_spinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int position, long id) {

						if (arg0.getItemAtPosition(position).toString()
								.length() > 0) {
							String selectedValue = arg0.getItemAtPosition(
									position).toString();

							String sql = "SELECT q43_hour2,q44_hour2,q45_hour2,q46_hour2 "
									+ "FROM tblEpisodes_Hour2 WHERE dataid = '"
									+ CommonStaticClass.dataId
									+ "'"
									+ " AND "
									+ " childNo = '"
									+ CommonStaticClass.currentChildrenCount
									+ "'"
									+ " AND EpisodeNo = '"
									+ selectedValue + "'";

							Log.e("sql", sql);

							Cursor mCursor = null;

							try {
								mCursor = DatabaseHelper.getInstance(con)
										.getQueryCursor(sql);
								if (mCursor.moveToFirst()) {
									do {
										((EditText) findViewById(R.id.q43_hour2et)).setText(mCursor.getString(mCursor
												.getColumnIndex("q43_hour2")));
										((EditText) findViewById(R.id.q44_hour2et)).setText(mCursor.getString(mCursor
												.getColumnIndex("q44_hour2")));

										String q45_hour2 = mCursor.getString(mCursor
												.getColumnIndex("q45_hour2"));

										if (q45_hour2.trim() != ""
												&& q45_hour2.length() > 0) {

											switch (Integer.parseInt(q45_hour2)) {
											case 1:
												((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup45h2))
														.findViewById(R.id.radio0))
														.toggle();
												break;
											case 2:
												((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup45h2))
														.findViewById(R.id.radio1))
														.toggle();
												break;

											case 3:
												((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup45h2))
														.findViewById(R.id.radio2))
														.toggle();
												break;
											case 4:
												((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup45h2))
														.findViewById(R.id.radio3))
														.toggle();
												break;
											case 5:
												((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup45h2))
														.findViewById(R.id.radio4))
														.toggle();

												break;

											}
										}

										String q46_hour2 = mCursor.getString(mCursor
												.getColumnIndex("q46_hour2"));
										if (q46_hour2.trim() != ""
												&& q46_hour2.length() > 0) {

											switch (Integer.parseInt(q46_hour2)) {
											case 1:
												((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup46h2))
														.findViewById(R.id.radio0))
														.toggle();
												break;
											case 2:
												((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup46h2))
														.findViewById(R.id.radio1))
														.toggle();
												break;

											}
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

						}

					}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});

	}

	private void loadEpisodeHour5() {
		q14_hour5_spinner = (Spinner) findViewById(R.id.q14_hour5_spinner);
		_episodes_hour5 = new ArrayList<String>();
		_episodes_hour5.add("1");

		adapter14h5 = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item, _episodes_hour5);
		adapter14h5
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		q14_hour5_spinner.setAdapter(adapter14h5);
		q14_hour5_spinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int position, long id) {

						if (arg0.getItemAtPosition(position).toString()
								.length() > 0) {
							String selectedValue = arg0.getItemAtPosition(
									position).toString();

							String sql = "SELECT q15_hour5,q16_hour5,q17_hour5,q18_hour5 "
									+ "FROM tblEpisodes_Hour5 WHERE dataid = '"
									+ CommonStaticClass.dataId
									+ "'"
									+ " AND "
									+ " childNo = '"
									+ CommonStaticClass.currentChildrenCount
									+ "'"
									+ " AND EpisodeNo = '"
									+ selectedValue + "'";

							Log.e("sql", sql);

							Cursor mCursor = null;

							try {
								mCursor = DatabaseHelper.getInstance(con)
										.getQueryCursor(sql);
								if (mCursor.moveToFirst()) {
									do {
										((EditText) findViewById(R.id.q15_hour5et)).setText(mCursor.getString(mCursor
												.getColumnIndex("q15_hour5")));
										((EditText) findViewById(R.id.q16_hour5et)).setText(mCursor.getString(mCursor
												.getColumnIndex("q16_hour5")));

										String q17_hour5 = mCursor.getString(mCursor
												.getColumnIndex("q17_hour5"));

										if (q17_hour5.trim() != ""
												&& q17_hour5.length() > 0) {

											switch (Integer.parseInt(q17_hour5)) {
											case 1:
												((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup17h5))
														.findViewById(R.id.radio0))
														.toggle();
												break;
											case 2:
												((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup17h5))
														.findViewById(R.id.radio1))
														.toggle();
												break;

											case 3:
												((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup17h5))
														.findViewById(R.id.radio2))
														.toggle();
												break;
											case 4:
												((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup17h5))
														.findViewById(R.id.radio3))
														.toggle();
												break;
											case 5:
												((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup17h5))
														.findViewById(R.id.radio4))
														.toggle();

												break;

											}
										}

										String q18_hour5 = mCursor.getString(mCursor
												.getColumnIndex("q18_hour5"));
										if (q18_hour5.trim() != ""
												&& q18_hour5.length() > 0) {

											switch (Integer.parseInt(q18_hour5)) {
											case 1:
												((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup18h5))
														.findViewById(R.id.radio0))
														.toggle();
												break;
											case 2:
												((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup18h5))
														.findViewById(R.id.radio1))
														.toggle();
												break;

											}
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

						}

					}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});

		// q14_hour2.setOnItemSelectedListener(new spinItemSelectedListener());

	}

	public void getChars(Character x) {
		try {
			System.out.print("Enter a Char:  ");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			String str = in.readLine();
			char i = str.charAt(0);
			System.out.println("Ascii value is :" + (int) i);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void loadAllUI() {

		((EditText) findViewById(R.id.q6))
				.addTextChangedListener(new TextWatcher() {

					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						if (s.length() > 0) {
							if (s.toString() != "") {
								if (s.toString().contains("0")) {
									s = s.toString().replace("0", "");
									((EditText) findViewById(R.id.q6))
											.setText(s);
									((EditText) findViewById(R.id.q6))
											.setSelection(s.length());

								}
								if (s.toString().contains("1")) {
									s = s.toString().replace("1", "");
									((EditText) findViewById(R.id.q6))
											.setText(s);
									((EditText) findViewById(R.id.q6))
											.setSelection(s.length());

								}
								if (s.toString().contains("2")) {
									s = s.toString().replace("2", "");
									((EditText) findViewById(R.id.q6))
											.setText(s);
									((EditText) findViewById(R.id.q6))
											.setSelection(s.length());

								}
								if (s.toString().contains("3")) {
									s = s.toString().replace("3", "");
									((EditText) findViewById(R.id.q6))
											.setText(s);
									((EditText) findViewById(R.id.q6))
											.setSelection(s.length());

								}
								if (s.toString().contains("4")) {
									s = s.toString().replace("4", "");
									((EditText) findViewById(R.id.q6))
											.setText(s);
									((EditText) findViewById(R.id.q6))
											.setSelection(s.length());

								}
								if (s.toString().contains("5")) {
									s = s.toString().replace("5", "");
									((EditText) findViewById(R.id.q6))
											.setText(s);
									((EditText) findViewById(R.id.q6))
											.setSelection(s.length());

								}
								if (s.toString().contains("6")) {
									s = s.toString().replace("6", "");
									((EditText) findViewById(R.id.q6))
											.setText(s);
									((EditText) findViewById(R.id.q6))
											.setSelection(s.length());

								}
								if (s.toString().contains("7")) {
									s = s.toString().replace("7", "");
									((EditText) findViewById(R.id.q6))
											.setText(s);
									((EditText) findViewById(R.id.q6))
											.setSelection(s.length());

								}
								if (s.toString().contains("8")) {
									s = s.toString().replace("8", "");
									((EditText) findViewById(R.id.q6))
											.setText(s);
									((EditText) findViewById(R.id.q6))
											.setSelection(s.length());

								}
								if (s.toString().contains("9")) {
									s = s.toString().replace("9", "");
									((EditText) findViewById(R.id.q6))
											.setText(s);
									((EditText) findViewById(R.id.q6))
											.setSelection(s.length());

								}
							}

						}
					}

					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
						// TODO Auto-generated method stub

					}

					public void afterTextChanged(Editable s) {
						/*
						 * if(s.length()>0) { String oldChar = s.subSequence(0,
						 * s.length()-1).toString();
						 * 
						 * Character c = s.charAt(s.length()-1);
						 * if(!Character.isLetter(c)) {
						 * //CommonStaticClass.showMyAlert(con,"Message",
						 * "Name can not contain numbers");
						 * q6Ed.setText(oldChar);
						 * q6Ed.setSelection(oldChar.length()); }
						 * 
						 * }
						 */

					}
				});

		loadEpisodeHour2();
		//loadEpisodeHour5();
		findViewById(R.id.q8_otheret).setVisibility(View.GONE);
		findViewById(R.id.q56_othereth2).setVisibility(View.GONE);
//		findViewById(R.id.q35_othereth5).setVisibility(View.GONE);
		findViewById(R.id.textView35h2).setVisibility(View.GONE);
		findViewById(R.id.radioGroup56h2).setVisibility(View.GONE);
	//	findViewById(R.id.textView35h5).setVisibility(View.GONE);
		//findViewById(R.id.radioGroup35h5).setVisibility(View.GONE);

		((RadioGroup) findViewById(R.id.radioGroup7))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup group, int checkedId) {

						switch (checkedId) {
						case R.id.radio0:
							q7 = "1";
							findViewById(R.id.textView2).setVisibility(
									View.GONE);
							findViewById(R.id.radioGroup8).setVisibility(
									View.GONE);
							findViewById(R.id.q8_otheret).setVisibility(
									View.GONE);
							/*findViewById(R.id.textView3).setVisibility(
									View.VISIBLE);
							findViewById(R.id.textView4).setVisibility(
									View.VISIBLE);
							findViewById(R.id.textView5).setVisibility(
									View.VISIBLE);*/
							/*findViewById(R.id.textView6).setVisibility(
									View.VISIBLE);
							*/

							/*findViewById(R.id.q9et).setVisibility(View.VISIBLE);
							findViewById(R.id.q10et)
									.setVisibility(View.VISIBLE);
							findViewById(R.id.q11et)
									.setVisibility(View.VISIBLE);
							findViewById(R.id.q12et)
									.setVisibility(View.VISIBLE);*/
							findViewById(R.id.textView7).setVisibility(
									View.VISIBLE);
							findViewById(R.id.q41et)
									.setVisibility(View.VISIBLE);
							// loadtab();
							enableall();
							/*findViewById(R.id.lnq37)
									.setVisibility(View.VISIBLE);*/
							break;

						case R.id.radio1:
							q7 = "2";
							findViewById(R.id.textView2).setVisibility(
									View.VISIBLE);
							findViewById(R.id.radioGroup8).setVisibility(
									View.VISIBLE);

							/*findViewById(R.id.textView3).setVisibility(
									View.GONE);
							findViewById(R.id.textView4).setVisibility(
									View.GONE);
							findViewById(R.id.textView5).setVisibility(
									View.GONE);
							findViewById(R.id.textView6).setVisibility(
									View.GONE);
							*/

							/*findViewById(R.id.q9et).setVisibility(View.GONE);
							findViewById(R.id.q10et).setVisibility(View.GONE);
							findViewById(R.id.q11et).setVisibility(View.GONE);
							findViewById(R.id.q12et).setVisibility(View.GONE);*/
							findViewById(R.id.textView7).setVisibility(
									View.GONE);
							findViewById(R.id.q41et).setVisibility(View.GONE);
							//findViewById(R.id.lnq37).setVisibility(View.GONE);
							// loadtab();
							disableall();
							break;
						}
					}
				});

		((RadioGroup) findViewById(R.id.radioGroup8))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup group, int checkedId) {

						findViewById(R.id.q8_otheret).setVisibility(View.GONE);

						switch (checkedId) {
						case R.id.radio0:
							q8 = "1";
							break;

						case R.id.radio1:
							q8 = "2";
							break;

						case R.id.radio2:
							q8 = "3";
							break;

						case R.id.radio3:
							q8 = "4";
							break;

						case R.id.radio4:
							q8 = "5";
							findViewById(R.id.q8_otheret).setVisibility(
									View.VISIBLE);
							break;

						}

					}
				});

		/*findViewById(R.id.q11et).setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				pickTime = (EditText) v;
				showDialog(TIME_DIALOG);
				return false;

			}
		});

		findViewById(R.id.q12et).setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				pickTime = (EditText) v;
				showDialog(TIME_DIALOG);
				return false;

			}
		});*/

		findViewById(R.id.q41et).setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				pickTime = (EditText) v;
				showDialog(TIME_DIALOG);
				return false;

			}
		});

		((EditText) findViewById(R.id.q43_hour2et))
				.setOnTouchListener(new OnTouchListener() {

					public boolean onTouch(View v, MotionEvent event) {
						pickTime = (EditText) v;
						showDialog(TIME_DIALOG);
						return false;

					}
				});

		((RadioGroup) findViewById(R.id.radioGroup45h2))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup arg0, int arg1) {
						switch (arg1) {
						case R.id.radio0:
							q45_hour2 = "1";
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup46h2))
									.findViewById(R.id.radio1))
									.setChecked(true);
							break;

						case R.id.radio1:
							q45_hour2 = "2";
							break;

						case R.id.radio2:
							q45_hour2 = "3";
							break;

						case R.id.radio3:
							q45_hour2 = "4";
							break;

						case R.id.radio4:
							q45_hour2 = "5";

							break;

						}

					}
				});

		((RadioGroup) findViewById(R.id.radioGroup46h2))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup arg0, int arg1) {
						switch (arg1) {
						case R.id.radio0:
							q46_hour2 = "1";
							break;

						case R.id.radio1:
							q46_hour2 = "2";
							break;

						}

					}
				});

		// question for hour 5
		/*((EditText) findViewById(R.id.q15_hour5et))
				.setOnTouchListener(new OnTouchListener() {

					public boolean onTouch(View v, MotionEvent event) {
						pickTime = (EditText) v;
						showDialog(TIME_DIALOG);
						return false;

					}
				});

		((RadioGroup) findViewById(R.id.radioGroup17h5))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup arg0, int arg1) {
						switch (arg1) {
						case R.id.radio0:
							q17_hour5 = "1";
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup18h5))
									.findViewById(R.id.radio1))
									.setChecked(true);
							break;

						case R.id.radio1:
							q17_hour5 = "2";
							break;

						case R.id.radio2:
							q17_hour5 = "3";
							break;

						case R.id.radio3:
							q17_hour5 = "4";
							break;

						case R.id.radio4:
							q17_hour5 = "5";

							break;

						}

					}
				});

		((RadioGroup) findViewById(R.id.radioGroup18h5))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup arg0, int arg1) {
						switch (arg1) {
						case R.id.radio0:
							q18_hour5 = "1";
							break;

						case R.id.radio1:
							q18_hour5 = "2";
							break;

						}

					}
				});*/

		// end hour 5
		findViewById(R.id.q47et).setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				pickTime = (EditText) v;
				showDialog(TIME_DIALOG);
				return false;

			}
		});

		/*findViewById(R.id.q24et).setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				pickTime = (EditText) v;
				showDialog(TIME_DIALOG);
				return false;

			}
		});
		((EditText) findViewById(R.id.q24et))
				.addTextChangedListener(new TextWatcher() {

					public void onTextChanged(CharSequence arg0, int arg1,
							int arg2, int arg3) {
						// TODO Auto-generated method stub

					}

					public void beforeTextChanged(CharSequence arg0, int arg1,
							int arg2, int arg3) {
						// TODO Auto-generated method stub

					}

					public void afterTextChanged(Editable arg0) {

						String time1 = ((EditText) findViewById(R.id.q13et))
								.getText().toString();
						((EditText) findViewById(R.id.q24et))
								.getText().toString();

						if (IsTimeLessThanFiveHour() == true) {
							if (!(time1.equalsIgnoreCase("") && time1
									.equalsIgnoreCase(""))) {
								((LinearLayout) findViewById(R.id.lnq37))
										.setVisibility(View.VISIBLE);
							}

						} else {
							((LinearLayout) findViewById(R.id.lnq37))
									.setVisibility(View.GONE);
						}
					}
				});*/

		
		
		/*((RadioGroup) findViewById(R.id.radioGroup29))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup arg0, int arg1) {
						switch (arg1) {
						case R.id.radio0:
							q29 = "1";
							break;

						case R.id.radio1:
							q29 = "2";
							break;

						}

					}
				});

		((RadioGroup) findViewById(R.id.radioGroup30))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup arg0, int arg1) {
						switch (arg1) {
						case R.id.radio0:
							q30 = "1";
							break;

						case R.id.radio1:
							q30 = "2";
							break;

						}

					}
				});*/

		((RadioGroup) findViewById(R.id.radioGroup55s1))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup arg0, int arg1) {
						findViewById(R.id.textView35h2)
								.setVisibility(View.GONE);
						findViewById(R.id.radioGroup56h2).setVisibility(
								View.GONE);
						switch (arg1) {
						case R.id.radio0:
							q55s1hour2 = "1";

							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup55s2))
									.getChildAt(0)).setChecked(true);
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup55s3))
									.getChildAt(0)).setChecked(true);
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup55s4))
									.getChildAt(0)).setChecked(true);
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup55s5))
									.getChildAt(0)).setChecked(true);

							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup55s6))
									.getChildAt(0)).setChecked(true);

							findViewById(R.id.textView35h2).setVisibility(
									View.VISIBLE);
							findViewById(R.id.radioGroup56h2).setVisibility(
									View.VISIBLE);

							break;

						case R.id.radio1:

							q55s1hour2 = "2";
							findViewById(R.id.textView35h2).setVisibility(
									View.VISIBLE);
							findViewById(R.id.radioGroup56h2).setVisibility(
									View.VISIBLE);
							break;

						case R.id.radio2:
							q55s1hour2 = "3";

							break;

						default:
							q55s1hour2 = "";
							break;
						}

					}
				});

		((RadioGroup) findViewById(R.id.radioGroup55s2))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup arg0, int arg1) {
						switch (arg1) {
						case R.id.radio0:
							q55s2hour2 = "1";
							findViewById(R.id.textView35h2).setVisibility(
									View.VISIBLE);
							findViewById(R.id.radioGroup56h2).setVisibility(
									View.VISIBLE);
							break;

						case R.id.radio1:

							if (CheckAliquotAndShowMessage(
									((RadioGroup) findViewById(R.id.radioGroup55s2)),
									((RadioGroup) findViewById(R.id.radioGroup55s1)))) {
								q55s2hour2 = "2";
								findViewById(R.id.textView35h2).setVisibility(
										View.VISIBLE);
								findViewById(R.id.radioGroup56h2)
										.setVisibility(View.VISIBLE);
							}
							break;

						case R.id.radio2:
							if (CheckAliquotAndShowMessage(
									((RadioGroup) findViewById(R.id.radioGroup55s2)),
									((RadioGroup) findViewById(R.id.radioGroup55s1)))) {
								q55s2hour2 = "3";
							}
							break;

						default:
							q55s2hour2 = "";
							break;

						}

					}
				});

		((RadioGroup) findViewById(R.id.radioGroup55s3))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup arg0, int arg1) {
						switch (arg1) {
						case R.id.radio0:
							q55s3hour2 = "1";
							findViewById(R.id.textView35h2).setVisibility(
									View.VISIBLE);
							findViewById(R.id.radioGroup56h2).setVisibility(
									View.VISIBLE);
							break;

						case R.id.radio1:
							if (CheckAliquotAndShowMessage(
									((RadioGroup) findViewById(R.id.radioGroup55s3)),
									((RadioGroup) findViewById(R.id.radioGroup55s2)))) {
								q55s3hour2 = "2";
								findViewById(R.id.textView35h2).setVisibility(
										View.VISIBLE);
								findViewById(R.id.radioGroup56h2)
										.setVisibility(View.VISIBLE);
							}
							break;

						case R.id.radio2:
							if (CheckAliquotAndShowMessage(
									((RadioGroup) findViewById(R.id.radioGroup55s3)),
									((RadioGroup) findViewById(R.id.radioGroup55s2)))) {
								q55s3hour2 = "3";
							}
							break;

						}

					}
				});

		((RadioGroup) findViewById(R.id.radioGroup55s4))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup arg0, int arg1) {
						switch (arg1) {
						case R.id.radio0:
							q55s4hour2 = "1";
							findViewById(R.id.textView35h2).setVisibility(
									View.VISIBLE);
							findViewById(R.id.radioGroup56h2).setVisibility(
									View.VISIBLE);
							break;

						case R.id.radio1:
							if (CheckAliquotAndShowMessage(
									((RadioGroup) findViewById(R.id.radioGroup55s4)),
									((RadioGroup) findViewById(R.id.radioGroup55s3)))) {
								q55s4hour2 = "2";
								findViewById(R.id.textView35h2).setVisibility(
										View.VISIBLE);
								findViewById(R.id.radioGroup56h2)
										.setVisibility(View.VISIBLE);
							}
							break;

						case R.id.radio2:
							if (CheckAliquotAndShowMessage(
									((RadioGroup) findViewById(R.id.radioGroup55s4)),
									((RadioGroup) findViewById(R.id.radioGroup55s3)))) {
								q55s4hour2 = "3";
							}
							break;

						}

					}
				});

		((RadioGroup) findViewById(R.id.radioGroup55s5))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup arg0, int arg1) {
						switch (arg1) {
						case R.id.radio0:
							q55s5hour2 = "1";
							findViewById(R.id.textView35h2).setVisibility(
									View.VISIBLE);
							findViewById(R.id.radioGroup56h2).setVisibility(
									View.VISIBLE);
							break;

						case R.id.radio1:
							if (CheckAliquotAndShowMessage(
									((RadioGroup) findViewById(R.id.radioGroup55s5)),
									((RadioGroup) findViewById(R.id.radioGroup55s4)))) {
								q55s5hour2 = "2";
								findViewById(R.id.textView35h2).setVisibility(
										View.VISIBLE);
								findViewById(R.id.radioGroup56h2)
										.setVisibility(View.VISIBLE);
							}
							break;

						case R.id.radio2:
							if (CheckAliquotAndShowMessage(
									((RadioGroup) findViewById(R.id.radioGroup55s5)),
									((RadioGroup) findViewById(R.id.radioGroup55s4)))) {
								q55s5hour2 = "3";
							}
							break;

						}

					}
				});

		
		
		
		((RadioGroup) findViewById(R.id.radioGroup55s6))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup arg0, int arg1) {
						switch (arg1) {
						case R.id.radio0:
							q55s6hour2 = "1";
							findViewById(R.id.textView35h2).setVisibility(
									View.VISIBLE);
							findViewById(R.id.radioGroup56h2).setVisibility(
									View.VISIBLE);
							break;

						case R.id.radio1:
							if (CheckAliquotAndShowMessage(
									((RadioGroup) findViewById(R.id.radioGroup55s6)),
									((RadioGroup) findViewById(R.id.radioGroup55s5)))) {
								q55s6hour2 = "2";
								findViewById(R.id.textView35h2).setVisibility(
										View.VISIBLE);
								findViewById(R.id.radioGroup56h2)
										.setVisibility(View.VISIBLE);
							}
							break;

						case R.id.radio2:
							if (CheckAliquotAndShowMessage(
									((RadioGroup) findViewById(R.id.radioGroup55s6)),
									((RadioGroup) findViewById(R.id.radioGroup55s5)))) {
								q55s6hour2 = "3";
							}
							break;

						}

					}
				});

		
		
		/*((RadioGroup) findViewById(R.id.radioGroup34s7))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup arg0, int arg1) {
						findViewById(R.id.textView35h5)
								.setVisibility(View.GONE);
						findViewById(R.id.radioGroup35h5).setVisibility(
								View.GONE);
						switch (arg1) {

						case R.id.radio0:
							q34s7hour5 = "1";

							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup34s8))
									.getChildAt(0)).setChecked(true);
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup34s9))
									.getChildAt(0)).setChecked(true);
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup34s10))
									.getChildAt(0)).setChecked(true);
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup34s11))
									.getChildAt(0)).setChecked(true);
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup34s12))
									.getChildAt(0)).setChecked(true);
							findViewById(R.id.textView35h5).setVisibility(
									View.VISIBLE);
							findViewById(R.id.radioGroup35h5).setVisibility(
									View.VISIBLE);
							break;

						case R.id.radio1:
							
							 * if (CheckAliquotAndShowMessage( ((RadioGroup)
							 * findViewById(R.id.radioGroup34s7)), ((RadioGroup)
							 * findViewById(R.id.radioGroup34s6)))) {
							 
							q34s7hour5 = "2";
							findViewById(R.id.textView35h5).setVisibility(
									View.VISIBLE);
							findViewById(R.id.radioGroup35h5).setVisibility(
									View.VISIBLE);
							// }
							break;

						case R.id.radio2:
							
							 * if (CheckAliquotAndShowMessage( ((RadioGroup)
							 * findViewById(R.id.radioGroup34s7)), ((RadioGroup)
							 * findViewById(R.id.radioGroup34s6)))) {
							 
							q34s7hour5 = "3";
							// }
							break;

						default:
							q34s7hour5 = "";
							break;

						}

					}
				});

		((RadioGroup) findViewById(R.id.radioGroup34s8))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup arg0, int arg1) {
						switch (arg1) {
						case R.id.radio0:
							q34s8hour5 = "1";
							findViewById(R.id.textView35h5).setVisibility(
									View.VISIBLE);
							findViewById(R.id.radioGroup35h5).setVisibility(
									View.VISIBLE);
							break;

						case R.id.radio1:
							if (CheckAliquotAndShowMessage(
									((RadioGroup) findViewById(R.id.radioGroup34s8)),
									((RadioGroup) findViewById(R.id.radioGroup34s7)))) {
								q34s8hour5 = "2";
								findViewById(R.id.textView35h5).setVisibility(
										View.VISIBLE);
								findViewById(R.id.radioGroup35h5)
										.setVisibility(View.VISIBLE);
							}
							break;

						case R.id.radio2:
							if (CheckAliquotAndShowMessage(
									((RadioGroup) findViewById(R.id.radioGroup34s8)),
									((RadioGroup) findViewById(R.id.radioGroup34s7)))) {
								q34s8hour5 = "3";
							}
							break;

						default:
							q34s8hour5 = "";
							break;

						}

					}
				});

		((RadioGroup) findViewById(R.id.radioGroup34s9))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup arg0, int arg1) {
						switch (arg1) {
						case R.id.radio0:
							q34s9hour5 = "1";
							findViewById(R.id.textView35h5).setVisibility(
									View.VISIBLE);
							findViewById(R.id.radioGroup35h5).setVisibility(
									View.VISIBLE);
							break;

						case R.id.radio1:
							if (CheckAliquotAndShowMessage(
									((RadioGroup) findViewById(R.id.radioGroup34s9)),
									((RadioGroup) findViewById(R.id.radioGroup34s8)))) {

								q34s9hour5 = "2";
								findViewById(R.id.textView35h5).setVisibility(
										View.VISIBLE);
								findViewById(R.id.radioGroup35h5)
										.setVisibility(View.VISIBLE);
							}
							break;

						case R.id.radio2:
							if (CheckAliquotAndShowMessage(
									((RadioGroup) findViewById(R.id.radioGroup34s9)),
									((RadioGroup) findViewById(R.id.radioGroup34s8)))) {
								q34s9hour5 = "3";
							}
							break;
						}

					}
				});

		((RadioGroup) findViewById(R.id.radioGroup34s10))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup arg0, int arg1) {
						switch (arg1) {
						case R.id.radio0:
							q34s10hour5 = "1";
							findViewById(R.id.textView35h5).setVisibility(
									View.VISIBLE);
							findViewById(R.id.radioGroup35h5).setVisibility(
									View.VISIBLE);
							break;

						case R.id.radio1:
							if (CheckAliquotAndShowMessage(
									((RadioGroup) findViewById(R.id.radioGroup34s10)),
									((RadioGroup) findViewById(R.id.radioGroup34s9)))) {

								q34s10hour5 = "2";
								findViewById(R.id.textView35h5).setVisibility(
										View.VISIBLE);
								findViewById(R.id.radioGroup35h5)
										.setVisibility(View.VISIBLE);
							}
							break;

						case R.id.radio2:
							if (CheckAliquotAndShowMessage(
									((RadioGroup) findViewById(R.id.radioGroup34s10)),
									((RadioGroup) findViewById(R.id.radioGroup34s9)))) {

								q34s10hour5 = "3";
							}
							break;
						}

					}
				});

		((RadioGroup) findViewById(R.id.radioGroup34s11))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup arg0, int arg1) {
						switch (arg1) {
						case R.id.radio0:
							q34s11hour5 = "1";
							findViewById(R.id.textView35h5).setVisibility(
									View.VISIBLE);
							findViewById(R.id.radioGroup35h5).setVisibility(
									View.VISIBLE);
							break;

						case R.id.radio1:
							if (CheckAliquotAndShowMessage(
									((RadioGroup) findViewById(R.id.radioGroup34s11)),
									((RadioGroup) findViewById(R.id.radioGroup34s10)))) {
								q34s11hour5 = "2";
								findViewById(R.id.textView35h5).setVisibility(
										View.VISIBLE);
								findViewById(R.id.radioGroup35h5)
										.setVisibility(View.VISIBLE);
							}
							break;

						case R.id.radio2:
							if (CheckAliquotAndShowMessage(
									((RadioGroup) findViewById(R.id.radioGroup34s11)),
									((RadioGroup) findViewById(R.id.radioGroup34s10)))) {
								q34s11hour5 = "3";
							}
							break;
						}

					}
				});

		((RadioGroup) findViewById(R.id.radioGroup34s12))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup arg0, int arg1) {
						switch (arg1) {
						case R.id.radio0:
							q34s12hour5 = "1";
							findViewById(R.id.textView35h5).setVisibility(
									View.VISIBLE);
							findViewById(R.id.radioGroup35h5).setVisibility(
									View.VISIBLE);
							break;

						case R.id.radio1:
							if (CheckAliquotAndShowMessage(
									((RadioGroup) findViewById(R.id.radioGroup34s12)),
									((RadioGroup) findViewById(R.id.radioGroup34s11)))) {
								q34s12hour5 = "2";
								findViewById(R.id.textView35h5).setVisibility(
										View.VISIBLE);
								findViewById(R.id.radioGroup35h5)
										.setVisibility(View.VISIBLE);
							}
							break;

						case R.id.radio2:
							if (CheckAliquotAndShowMessage(
									((RadioGroup) findViewById(R.id.radioGroup34s12)),
									((RadioGroup) findViewById(R.id.radioGroup34s11)))) {
								q34s12hour5 = "3";
							}
							break;
						}

					}
				});

		findViewById(R.id.q36eth5).setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent arg1) {
				pickTime = (EditText) v;
				showDialog(TIME_DIALOG);
				return false;
			}
		});

		((RadioGroup) findViewById(R.id.radioGroup35h5))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup arg0, int arg1) {
						findViewById(R.id.q35_othereth5).setVisibility(
								View.GONE);
						switch (arg1) {
						case R.id.radio0:
							q35hour5 = "1";
							break;

						case R.id.radio1:
							q35hour5 = "2";
							break;

						case R.id.radio2:
							q35hour5 = "3";
							break;

						case R.id.radio3:
							q35hour5 = "4";
							break;

						case R.id.radio4:
							q35hour5 = "5";
							findViewById(R.id.q35_othereth5).setVisibility(
									View.VISIBLE);
							break;

						}

					}
				});
		((RadioGroup) findViewById(R.id.radioGroup35h2))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup arg0, int arg1) {
						findViewById(R.id.q35_othereth2).setVisibility(
								View.GONE);
						switch (arg1) {
						case R.id.radio0:
							q35hour2 = "1";
							break;

						case R.id.radio1:
							q35hour2 = "2";
							break;

						case R.id.radio2:
							q35hour2 = "3";
							break;

						case R.id.radio3:
							q35hour2 = "4";
							break;

						case R.id.radio4:
							q35hour2 = "5";
							findViewById(R.id.q35_othereth2).setVisibility(
									View.VISIBLE);
							break;

						}

					}
				});

		findViewById(R.id.q36eth2).setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent arg1) {
				pickTime = (EditText) v;
				showDialog(TIME_DIALOG);
				return false;
			}
		});*/

		findViewById(R.id.q57eth2).setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent arg1) {
				pickTime = (EditText) v;
				showDialog(TIME_DIALOG);
				return false;
			}
		});
		
		((RadioGroup) findViewById(R.id.radioGroup56h2))
		.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				findViewById(R.id.q56_othereth2).setVisibility(
						View.GONE);
				switch (arg1) {
				case R.id.radio0:
					q56hour2 = "1";
					break;

				case R.id.radio1:
					q56hour2 = "2";
					break;

				case R.id.radio2:
					q56hour2 = "3";
					break;

				case R.id.radio3:
					q56hour2 = "4";
					break;

				case R.id.radio4:
					q56hour2 = "5";
					findViewById(R.id.q56_othereth2).setVisibility(
							View.VISIBLE);
					break;

				}

			}
		});

/*findViewById(R.id.q56_othereth2).setOnTouchListener(new OnTouchListener() {

	public boolean onTouch(View v, MotionEvent arg1) {
		pickTime = (EditText) v;
		showDialog(TIME_DIALOG);
		return false;
	}
});*/

		computeTotalUrineVolumeHour2();
		//computeTotalUrineVolumeHour5();

		q52s1hour2 = CommonStaticClass.dataId + "E"
				+ CommonStaticClass.currentChildrenCount + "U13";
		q52s2hour2 = CommonStaticClass.dataId + "E"
				+ CommonStaticClass.currentChildrenCount + "U14";
		q52s3hour2 = CommonStaticClass.dataId + "E"
				+ CommonStaticClass.currentChildrenCount + "U15";
		q52s4hour2 = CommonStaticClass.dataId + "E"
				+ CommonStaticClass.currentChildrenCount + "U16";
		q52s5hour2 = CommonStaticClass.dataId + "E"
				+ CommonStaticClass.currentChildrenCount + "U17";
		q52s6hour2 = CommonStaticClass.dataId + "E"
				+ CommonStaticClass.currentChildrenCount + "U18";
		/*q31s7hour5 = CommonStaticClass.dataId + "M"
				+ CommonStaticClass.currentChildrenCount + "U07";
		q31s8hour5 = CommonStaticClass.dataId + "M"
				+ CommonStaticClass.currentChildrenCount + "U08";
		q31s9hour5 = CommonStaticClass.dataId + "M"
				+ CommonStaticClass.currentChildrenCount + "U09";
		q31s10hour5 = CommonStaticClass.dataId + "M"
				+ CommonStaticClass.currentChildrenCount + "U10";
		q31s11hour5 = CommonStaticClass.dataId + "M"
				+ CommonStaticClass.currentChildrenCount + "U11";
		q31s12hour5 = CommonStaticClass.dataId + "M"
				+ CommonStaticClass.currentChildrenCount + "U12";
		q32s1hour2 = getRandomId(CommonStaticClass.dataId + "M"
				+ CommonStaticClass.currentChildrenCount + "U01");
		
		
		q32s2hour2 = getRandomId(CommonStaticClass.dataId + "M"
				+ CommonStaticClass.currentChildrenCount + "U02");
		q32s3hour2 = getRandomId(CommonStaticClass.dataId + "M"
				+ CommonStaticClass.currentChildrenCount + "U03");
		q32s4hour2 = getRandomId(CommonStaticClass.dataId + "M"
				+ CommonStaticClass.currentChildrenCount + "U04");
		q32s5hour2 = getRandomId(CommonStaticClass.dataId + "M"
				+ CommonStaticClass.currentChildrenCount + "U05");
		q32s6hour2 = getRandomId(CommonStaticClass.dataId + "M"
				+ CommonStaticClass.currentChildrenCount + "U06");
		q32s7hour5 = getRandomId(CommonStaticClass.dataId + "M"
				+ CommonStaticClass.currentChildrenCount + "U07");
		q32s8hour5 = getRandomId(CommonStaticClass.dataId + "M"
				+ CommonStaticClass.currentChildrenCount + "U08");
		q32s9hour5 = getRandomId(CommonStaticClass.dataId + "M"
				+ CommonStaticClass.currentChildrenCount + "U09");
		q32s10hour5 = getRandomId(CommonStaticClass.dataId + "M"
				+ CommonStaticClass.currentChildrenCount + "U10");
		q32s11hour5 = getRandomId(CommonStaticClass.dataId + "M"
				+ CommonStaticClass.currentChildrenCount + "U11");
		q32s12hour5 = getRandomId(CommonStaticClass.dataId + "M"
				+ CommonStaticClass.currentChildrenCount + "U12");*/

		
		q53s1hour2 = getRandomId(CommonStaticClass.dataId + "E"
				+ CommonStaticClass.currentChildrenCount + "U13");
		q53s2hour2 = getRandomId(CommonStaticClass.dataId + "E"
				+ CommonStaticClass.currentChildrenCount + "U14");
		q53s3hour2 = getRandomId(CommonStaticClass.dataId + "E"
				+ CommonStaticClass.currentChildrenCount + "U15");
		q53s4hour2 = getRandomId(CommonStaticClass.dataId + "E"
				+ CommonStaticClass.currentChildrenCount + "U16");
		q53s5hour2 = getRandomId(CommonStaticClass.dataId + "E"
				+ CommonStaticClass.currentChildrenCount + "U17");
		q53s6hour2 = getRandomId(CommonStaticClass.dataId + "E"
				+ CommonStaticClass.currentChildrenCount + "U18");
		
		
		((TextView) findViewById(R.id.q52s1hour2)).setText(q52s1hour2);
		((TextView) findViewById(R.id.q52s2hour2)).setText(q52s2hour2);
		((TextView) findViewById(R.id.q52s3hour2)).setText(q52s3hour2);
		((TextView) findViewById(R.id.q52s4hour2)).setText(q52s4hour2);
		((TextView) findViewById(R.id.q52s5hour2)).setText(q52s5hour2);
		((TextView) findViewById(R.id.q52s6hour2)).setText(q52s6hour2);
		/*((TextView) findViewById(R.id.q31s7hour5)).setText(q31s7hour5);
		((TextView) findViewById(R.id.q31s8hour5)).setText(q31s8hour5);
		((TextView) findViewById(R.id.q31s9hour5)).setText(q31s9hour5);
		((TextView) findViewById(R.id.q31s10hour5)).setText(q31s10hour5);
		((TextView) findViewById(R.id.q31s11hour5)).setText(q31s11hour5);
		((TextView) findViewById(R.id.q31s12hour5)).setText(q31s12hour5);*/

		((TextView) findViewById(R.id.q53s1hour2)).setText(q53s1hour2);
		((TextView) findViewById(R.id.q53s2hour2)).setText(q53s2hour2);
		((TextView) findViewById(R.id.q53s3hour2)).setText(q53s3hour2);
		((TextView) findViewById(R.id.q53s4hour2)).setText(q53s4hour2);
		((TextView) findViewById(R.id.q53s5hour2)).setText(q53s5hour2);
		((TextView) findViewById(R.id.q53s6hour2)).setText(q53s6hour2);
		/*((TextView) findViewById(R.id.q32s7hour5)).setText(q32s7hour5);
		((TextView) findViewById(R.id.q32s8hour5)).setText(q32s8hour5);
		((TextView) findViewById(R.id.q32s9hour5)).setText(q32s9hour5);
		((TextView) findViewById(R.id.q32s10hour5)).setText(q32s10hour5);
		((TextView) findViewById(R.id.q32s11hour5)).setText(q32s11hour5);
		((TextView) findViewById(R.id.q32s12hour5)).setText(q32s12hour5);*/
//		((LinearLayout) findViewById(R.id.lnq37)).setVisibility(View.VISIBLE);
		/*findViewById(R.id.q37_other).setVisibility(View.GONE);
		((RadioGroup) findViewById(R.id.radioGroup37))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup arg0, int arg1) {
						findViewById(R.id.q37_other).setVisibility(View.GONE);
						switch (arg1) {
						case R.id.radio0:
							q37 = "1";
							break;

						case R.id.radio1:
							q37 = "2";
							break;

						case R.id.radio2:
							q37 = "3";
							break;

						case R.id.radio3:
							q37 = "4";
							break;

						case R.id.radio4:
							q37 = "5";
							break;

						case R.id.radio5:
							q37 = "6";
							((EditText) findViewById(R.id.q37_other))
									.setVisibility(View.VISIBLE);
							break;

						case R.id.radio6:
							q37 = "77";
							break;

						}
					}
				});*/
	}

	public void resetEveryValue(ViewGroup v) {
		ViewGroup group = v;// (ViewGroup) findViewById(R.id.main_view);
		ViewGroupReferences(group);

	}

	protected void ViewGroupReferences(ViewGroup group) {
		try {

			// int nrOfChildren = viewGroup.getChildCount();
			// for (int i = 0; i < nrOfChildren; i++) {

			for (int i = 0, count = group.getChildCount(); i < count; ++i) {
				View view = group.getChildAt(i);
				if (view instanceof EditText) {
					((EditText) view).setText("");
					((EditText) findViewById(R.id.q6)).setText("");
				}

				if (view instanceof RadioGroup) {
					((RadioGroup) view).clearCheck();

				}
			}

			System.gc();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e("Error from View group", "Error from View group");
		}
	}

	protected void ViewReferences(View view) {
		// set all listeners to null (not every view and not every API level
		// supports the methods)
		try {
			if (view.getVisibility() == View.VISIBLE) {
				if (view instanceof EditText) {
					((EditText) view).setText("");
				}

				if (view instanceof RadioGroup) {
					((RadioGroup) view).clearCheck();
				}

			}
		} catch (Exception e) {

			Log.e("Error from View", "Error from view");
		}

	}

	private boolean ValidActivity() {

		boolean alltrue = true;
		if (((EditText) findViewById(R.id.q6)).length() > 0) {
			q6 = ((EditText) findViewById(R.id.q6)).getText().toString();
		} else {
			return alltrue = false;
		}

		viewGroup = (GridLayout) findViewById(R.id.observation_grid_hour2gl);
		int nrOfChildren = viewGroup.getChildCount();

		for (int i = 0; i < nrOfChildren; i++) {

			if (viewGroup.getVisibility() == View.GONE)
				break;

			View view = viewGroup.getChildAt(i);

			if (view.getVisibility() == View.GONE)
				continue;

			if (view instanceof EditText) {
				if (!(((EditText) view).getText().toString().length() > 0)) {

					return alltrue = false;
				}

			}

			else if (view instanceof RadioGroup) {

				if (view.getVisibility() == View.GONE)
					continue;

				if (((RadioGroup) view).getCheckedRadioButtonId() == -1) {
					Log.e("From Validation", String.valueOf(findViewById(
							view.getId()).toString()));
					return alltrue = false;

				}

			}

		}

/*		viewGroup = (GridLayout) findViewById(R.id.observation_grid_hour5gl);
		nrOfChildren = viewGroup.getChildCount();

		for (int i = 0; i < nrOfChildren; i++) {
			if (viewGroup.getVisibility() == View.GONE)
				break;
			View view = viewGroup.getChildAt(i);

			if (view.getVisibility() == View.GONE)
				continue;

			if (view instanceof EditText) {
				if (!(((EditText) view).getText().toString().length() > 0)) {

					return alltrue = false;
				}

			}

			else if (view instanceof RadioGroup) {

				if (view.getVisibility() == View.GONE)
					continue;

				if (((RadioGroup) view).getCheckedRadioButtonId() == -1) {
					Log.e("From Validation",
							String.valueOf(((RadioGroup) view).getId()));
					return alltrue = false;
				}

			}

		}*/

		viewGroup = (LinearLayout) findViewById(R.id.sample_end_2hourln);
		nrOfChildren = viewGroup.getChildCount();

		for (int i = 0; i < nrOfChildren; i++) {
			if (viewGroup.getVisibility() == View.GONE)
				break;
			View view = viewGroup.getChildAt(i);

			if (view.getVisibility() == View.GONE)
				continue;

			if (view instanceof EditText) {
				if (!(((EditText) view).getText().toString().length() > 0)) {

					return alltrue = false;
				}

			}

			else if (view instanceof RadioGroup) {

				if (view.getVisibility() == View.GONE)
					continue;

				if (((RadioGroup) view).getCheckedRadioButtonId() == -1) {
					return alltrue = false;
				}

			}

		}

		/*viewGroup = (LinearLayout) findViewById(R.id.sample_end_5hourln);
		nrOfChildren = viewGroup.getChildCount();

		for (int i = 0; i < nrOfChildren; i++) {
			if (viewGroup.getVisibility() == View.GONE)
				break;

			View view = viewGroup.getChildAt(i);

			if (view.getVisibility() == View.GONE)
				continue;

			if (view instanceof EditText) {
				if (!(((EditText) view).getText().toString().length() > 0)) {

					return alltrue = false;
				}

			}

			else if (view instanceof RadioGroup) {

				if (view.getVisibility() == View.GONE)
					continue;

				if (((RadioGroup) view).getCheckedRadioButtonId() == -1) {
					return alltrue = false;
				}

			}

		}*/

		viewGroup = (LinearLayout) findViewById(R.id.extraqh2);
		nrOfChildren = viewGroup.getChildCount();

		for (int i = 0; i < nrOfChildren; i++) {

			if (viewGroup.getVisibility() == View.GONE)
				break;

			View view = viewGroup.getChildAt(i);

			if (view.getVisibility() == View.GONE)
				continue;

			if (view instanceof EditText) {
				if (!(((EditText) view).getText().toString().length() > 0)) {

					return alltrue = false;
				}

			}

			else if (view instanceof RadioGroup) {

				if (view.getVisibility() == View.GONE)
					continue;

				if (((RadioGroup) view).getCheckedRadioButtonId() == -1) {
					return alltrue = false;
				}

			}

		}

		/*viewGroup = (LinearLayout) findViewById(R.id.extraqh5);
		nrOfChildren = viewGroup.getChildCount();

		for (int i = 0; i < nrOfChildren; i++) {

			if (viewGroup.getVisibility() == View.GONE)
				break;

			View view = viewGroup.getChildAt(i);

			if (view.getVisibility() == View.GONE)
				continue;

			if (view instanceof EditText) {
				if (!(((EditText) view).getText().toString().length() > 0)) {

					return alltrue = false;
				}

			}

			else if (view instanceof RadioGroup) {

				if (view.getVisibility() == View.GONE)
					continue;

				if (((RadioGroup) view).getCheckedRadioButtonId() == -1) {
					return alltrue = false;
				}

			}

		}*/

		// q37
	/*	viewGroup = (LinearLayout) findViewById(R.id.lnq37);
		nrOfChildren = viewGroup.getChildCount();

		for (int i = 0; i < nrOfChildren; i++) {

			if (viewGroup.getVisibility() == View.GONE)
				break;

			View view = viewGroup.getChildAt(i);

			if (view.getVisibility() == View.GONE)
				continue;

			if (view instanceof EditText) {
				if (!(((EditText) view).getText().toString().length() > 0)) {

					return alltrue = false;
				}

			}

			else if (view instanceof RadioGroup) {

				if (view.getVisibility() == View.GONE)
					continue;

				if (((RadioGroup) view).getCheckedRadioButtonId() == -1) {
					return alltrue = false;
				}

			}

		}*/

		if (IsViewVisible(R.id.q8_otheret))
			q8_other = ((EditText) findViewById(R.id.q8_otheret)).getText()
					.toString();

		/*if (IsViewVisible(R.id.q9et))
			q9 = ((EditText) findViewById(R.id.q9et)).getText().toString();

		if (IsViewVisible(R.id.q10et))
			q10 = ((EditText) findViewById(R.id.q10et)).getText().toString();

		if (IsViewVisible(R.id.q11et))
			q11 = ((EditText) findViewById(R.id.q11et)).getText().toString();

		if (IsViewVisible(R.id.q12et))
			q12 = ((EditText) findViewById(R.id.q12et)).getText().toString();
*/
		if (IsViewVisible(R.id.q41et))
			q41 = ((EditText) findViewById(R.id.q41et)).getText().toString();

		if (IsViewVisible(R.id.q47et))
			q47 = ((EditText) findViewById(R.id.q47et)).getText().toString();

		if (IsViewVisible(R.id.q48et))
			q48 = ((EditText) findViewById(R.id.q48et)).getText().toString();

		if (IsViewVisible(R.id.q49et))
			q49 = ((EditText) findViewById(R.id.q49et)).getText().toString();

		if (IsViewVisible(R.id.q50et))
			q50 = ((EditText) findViewById(R.id.q50et)).getText().toString();

		if (IsViewVisible(R.id.q51et))
			q51 = ((EditText) findViewById(R.id.q51et)).getText().toString();

		/*if (IsViewVisible(R.id.q24et))
			q24 = ((EditText) findViewById(R.id.q24et)).getText().toString();

		if (IsViewVisible(R.id.q25et))
			q25 = ((EditText) findViewById(R.id.q25et)).getText().toString();

		if (IsViewVisible(R.id.q26et))
			q26 = ((EditText) findViewById(R.id.q26et)).getText().toString();

		if (IsViewVisible(R.id.q27et))
			q27 = ((EditText) findViewById(R.id.q27et)).getText().toString();

		if (IsViewVisible(R.id.q28et))
			q28 = ((EditText) findViewById(R.id.q28et)).getText().toString();*/

		if (IsViewVisible(R.id.q52s1hour2))
			q52s1hour2 = ((TextView) findViewById(R.id.q52s1hour2)).getText()
					.toString();

		if (IsViewVisible(R.id.q52s2hour2))
			q52s2hour2 = ((TextView) findViewById(R.id.q52s2hour2)).getText()
					.toString();

		if (IsViewVisible(R.id.q52s3hour2))
			q52s3hour2 = ((TextView) findViewById(R.id.q52s3hour2)).getText()
					.toString();

		if (IsViewVisible(R.id.q52s4hour2))
			q52s4hour2 = ((TextView) findViewById(R.id.q52s4hour2)).getText()
					.toString();

		if (IsViewVisible(R.id.q52s5hour2))
			q52s5hour2 = ((TextView) findViewById(R.id.q52s5hour2)).getText()
					.toString();

		if (IsViewVisible(R.id.q52s6hour2))
			q52s6hour2 = ((TextView) findViewById(R.id.q52s6hour2)).getText()
					.toString();

		/*if (IsViewVisible(R.id.q31s7hour5))
			q31s7hour5 = ((TextView) findViewById(R.id.q31s7hour5)).getText()
					.toString();

		if (IsViewVisible(R.id.q31s8hour5))
			q31s8hour5 = ((TextView) findViewById(R.id.q31s8hour5)).getText()
					.toString();

		if (IsViewVisible(R.id.q31s9hour5))
			q31s9hour5 = ((TextView) findViewById(R.id.q31s9hour5)).getText()
					.toString();

		if (IsViewVisible(R.id.q31s10hour5))
			q31s10hour5 = ((TextView) findViewById(R.id.q31s10hour5)).getText()
					.toString();

		if (IsViewVisible(R.id.q31s11hour5))
			q31s11hour5 = ((TextView) findViewById(R.id.q31s11hour5)).getText()
					.toString();

		if (IsViewVisible(R.id.q31s12hour5))
			q31s12hour5 = ((TextView) findViewById(R.id.q31s12hour5)).getText()
					.toString();*/

		/*if (IsViewVisible(R.id.q52s1hour2))
			q52s1hour2 = ((TextView) findViewById(R.id.q52s1hour2)).getText()
					.toString();

		if (IsViewVisible(R.id.q52s2hour2))
			q52s2hour2 = ((TextView) findViewById(R.id.q52s2hour2)).getText()
					.toString();

		if (IsViewVisible(R.id.q52s3hour2))
			q52s3hour2 = ((TextView) findViewById(R.id.q52s3hour2)).getText()
					.toString();

		if (IsViewVisible(R.id.q52s4hour2))
			q52s4hour2 = ((TextView) findViewById(R.id.q52s4hour2)).getText()
					.toString();

		if (IsViewVisible(R.id.q52s5hour2))
			q52s5hour2 = ((TextView) findViewById(R.id.q52s5hour2)).getText()
					.toString();

		if (IsViewVisible(R.id.q52s6hour2))
			q52s6hour2 = ((TextView) findViewById(R.id.q52s6hour2)).getText()
					.toString();*/

		if (IsViewVisible(R.id.q53s1hour2))
			q53s1hour2 = ((TextView) findViewById(R.id.q53s1hour2)).getText()
					.toString();
		
		if (IsViewVisible(R.id.q53s2hour2))
			q53s2hour2 = ((TextView) findViewById(R.id.q53s2hour2)).getText()
					.toString();
		
		if (IsViewVisible(R.id.q53s3hour2))
			q53s3hour2 = ((TextView) findViewById(R.id.q53s3hour2)).getText()
					.toString();
		
		if (IsViewVisible(R.id.q53s4hour2))
			q53s4hour2 = ((TextView) findViewById(R.id.q53s4hour2)).getText()
					.toString();
		
		if (IsViewVisible(R.id.q53s5hour2))
			q53s5hour2 = ((TextView) findViewById(R.id.q53s5hour2)).getText()
					.toString();
		
		if (IsViewVisible(R.id.q53s6hour2))
			q53s6hour2 = ((TextView) findViewById(R.id.q53s6hour2)).getText()
					.toString();
		
		
		q54s1hour2 = "U";
		q54s2hour2 = "U";
		q54s3hour2 = "U";
		q54s4hour2 = "U";
		q54s5hour2 = "U";
		q54s6hour2 = "U";
		

		if (!IsViewVisible(R.id.radioGroup56h2))
			q56hour2 = "";

		
		if (IsViewVisible(R.id.q56_othereth2))
			q56hour2_other = ((EditText) findViewById(R.id.q56_othereth2)).getText()
					.toString();
		
		if (IsViewVisible(R.id.q57eth2))
			q57hour2 = ((EditText) findViewById(R.id.q57eth2)).getText()
					.toString();

		// ValidateQ15to18();
		return alltrue;
	}

	private boolean IsViewVisible(int viewID) {
		if (findViewById(viewID).getVisibility() == View.VISIBLE)
			return true;
		else
			return false;
	}

	private boolean SaveHour2() {
		if (!ValidateActivityHour2()) {
			CommonStaticClass.showFinalAlert(con,
					"Fill the required field for Hour 2");
			return false;
		}

		String entryDate = "dd-mmm-yyyy";
		Date d = new Date(System.currentTimeMillis());
		entryDate = "dd-mmm-yyyy";
		entryDate = d.toLocaleString();

		// insert data
		childNo = CommonStaticClass.currentChildrenCount + "";

		if (CommonStaticClass.mode.equalsIgnoreCase(CommonStaticClass.ADDMODE)
				|| CommonStaticClass.mode
						.equalsIgnoreCase(CommonStaticClass.SPECIALADD)) {

			sql = String
					.format("UPDATE tblSamples SET q6='%s',q7='%s', q8='%s',"
							+ "q41 ='%s', q47 ='%s',"
							+ "q48 ='%s', q49 ='%s', q50 ='%s', q51 ='%s', q52s1hour2 ='%s', q52s2hour2 ='%s', q52s3hour2 ='%s', q52s4hour2 ='%s', q52s5hour2 ='%s'," +
							"q52s6hour2 ='%s', q53s1hour2 ='%s', "
							+ "q53s2hour2='%s', q53s3hour2='%s', q53s4hour2='%s', q53s5hour2='%s', q53s6hour2='%s'," 
							+ "q54s1hour2='%s', q54s2hour2='%s', q54s3hour2='%s', q54s4hour2='%s', q54s5hour2='%s', q54s6hour2='%s', "
							+" q55s1hour2 ='%s', q55s2hour2 ='%s', q55s3hour2 ='%s', q55s4hour2 ='%s', q55s5hour2 ='%s',"
							+ "q55s6hour2 ='%s', q56hour2 ='%s', q56hour2_other ='%s', q57hour2='%s',EditBy='%s',EditDate='%s'"
							+ " WHERE dataid = '%s' AND childno = '%s'", q6,q7, q8,
							 q41 , q47 ,
							 q48 , q49 , q50 , q51 , q52s1hour2 , q52s2hour2 , q52s3hour2 , q52s4hour2 , q52s5hour2 , 
							q52s6hour2 , q53s1hour2 , 
							 q53s2hour2, q53s3hour2, q53s4hour2, q53s5hour2, q53s6hour2, 
							 q54s1hour2, q54s2hour2, q54s3hour2, q54s4hour2, q54s5hour2, q54s6hour2, 
							 q55s1hour2 , q55s2hour2 , q55s3hour2 , q55s4hour2 , q55s5hour2 ,
							 q55s6hour2 , q56hour2 , q56hour2_other , q57hour2,
							CommonStaticClass.userSpecificId, entryDate,
							CommonStaticClass.dataId, childNo);

		} else if (CommonStaticClass.mode
				.equalsIgnoreCase(CommonStaticClass.EDITMODE)) {

			sql = String
					.format("UPDATE tblSamples SET q6='%s',q7='%s', q8='%s',"
							+ "q41 ='%s', q47 ='%s',"
							+ "q48 ='%s', q49 ='%s', q50 ='%s', q51 ='%s', q52s1hour2 ='%s', q52s2hour2 ='%s', q52s3hour2 ='%s', q52s4hour2 ='%s', q52s5hour2 ='%s'," +
							"q52s6hour2 ='%s', q53s1hour2 ='%s', "
							+ "q53s2hour2='%s', q53s3hour2='%s', q53s4hour2='%s', q53s5hour2='%s', q53s6hour2='%s'," 
							+ "q54s1hour2='%s', q54s2hour2='%s', q54s3hour2='%s', q54s4hour2='%s', q54s5hour2='%s', q54s6hour2='%s', "
							+" q55s1hour2 ='%s', q55s2hour2 ='%s', q55s3hour2 ='%s', q55s4hour2 ='%s', q55s5hour2 ='%s',"
							+ "q55s6hour2 ='%s', q56hour2 ='%s', q56hour2_other ='%s', q57hour2='%s',EditBy='%s',EditDate='%s'"
							+ " WHERE dataid = '%s' AND childno = '%s'", q6,q7, q8,
							 q41 , q47 ,
							 q48 , q49 , q50 , q51 , q52s1hour2 , q52s2hour2 , q52s3hour2 , q52s4hour2 , q52s5hour2 , 
							q52s6hour2 , q53s1hour2 , 
							 q53s2hour2, q53s3hour2, q53s4hour2, q53s5hour2, q53s6hour2, 
							 q54s1hour2, q54s2hour2, q54s3hour2, q54s4hour2, q54s5hour2, q54s6hour2, 
							 q55s1hour2 , q55s2hour2 , q55s3hour2 , q55s4hour2 , q55s5hour2 ,
							 q55s6hour2 , q56hour2 , q56hour2_other , q57hour2,
							CommonStaticClass.userSpecificId, entryDate,
							CommonStaticClass.dataId, childNo);

			Log.e("From Update", sql);

			
		}

		
		
		try {
			if (DatabaseHelper.getInstance(con).executeDMLQuery(sql))

			{

				if (CommonStaticClass.mode
						.equalsIgnoreCase(CommonStaticClass.ADDMODE)) {

					CommonStaticClass.showMyAlert(con, "Saved",
							"Hour 2 Data Saved");
					return true;

				} else {
					CommonStaticClass.showMyAlert(con, "Saved",
							"Hour 2 Data Updated");

					return true;

				}
			} else {
				CommonStaticClass.showMyAlert(con, "Save Failed",
						"Hour 2 Data Save Failed");

			}
		} catch

		(Exception e) {
			return false;
		}
		return true;

	}

	private boolean ValidateActivityHour2() {

		boolean alltrue = true;
		if (((EditText) findViewById(R.id.q6)).length() > 0) {
			q6 = ((EditText) findViewById(R.id.q6)).getText().toString();
		} else {
			return alltrue = false;
		}

		viewGroup = (LinearLayout) findViewById(R.id.sample_end_2hourln);
		int nrOfChildren = viewGroup.getChildCount();

		for (int i = 0; i < nrOfChildren; i++) {
			if (viewGroup.getVisibility() == View.GONE)
				break;
			View view = viewGroup.getChildAt(i);

			if (view.getVisibility() == View.GONE)
				continue;

			if (view instanceof EditText) {
				if (!(((EditText) view).getText().toString().length() > 0)) {

					return alltrue = false;
				}

			}

			else if (view instanceof RadioGroup) {

				if (view.getVisibility() == View.GONE)
					continue;

				if (((RadioGroup) view).getCheckedRadioButtonId() == -1) {
					return alltrue = false;
				}

			}

		}

		if (IsViewVisible(R.id.q8_otheret))
			q8_other = ((EditText) findViewById(R.id.q8_otheret)).getText()
					.toString();

		/*if (IsViewVisible(R.id.q9et))
			q9 = ((EditText) findViewById(R.id.q9et)).getText().toString();

		if (IsViewVisible(R.id.q10et))
			q10 = ((EditText) findViewById(R.id.q10et)).getText().toString();

		if (IsViewVisible(R.id.q11et))
			q11 = ((EditText) findViewById(R.id.q11et)).getText().toString();

		if (IsViewVisible(R.id.q12et))
			q12 = ((EditText) findViewById(R.id.q12et)).getText().toString();*/

		if (IsViewVisible(R.id.q41et))
			q41 = ((EditText) findViewById(R.id.q41et)).getText().toString();

		if (IsViewVisible(R.id.q47et))
			q47 = ((EditText) findViewById(R.id.q47et)).getText().toString();

		if (IsViewVisible(R.id.q48et))
			q48 = ((EditText) findViewById(R.id.q48et)).getText().toString();

		if (IsViewVisible(R.id.q49et))
			q49 = ((EditText) findViewById(R.id.q49et)).getText().toString();

		if (IsViewVisible(R.id.q50et))
			q50 = ((EditText) findViewById(R.id.q50et)).getText().toString();

		if (IsViewVisible(R.id.q51et))
			q51 = ((EditText) findViewById(R.id.q51et)).getText().toString();

		q54s1hour2 = "U";
		q54s2hour2 = "U";
		q54s3hour2 = "U";
		q54s4hour2 = "U";
		q54s5hour2 = "U";
		q54s6hour2 = "U";
		
		/*q33s7hour5 = "U";
		q33s8hour5 = "U";
		q33s9hour5 = "U";
		q33s10hour5 = "U";
		q33s11hour5 = "U";
		q33s12hour5 = "U";*/

		return alltrue;
	}

	private boolean ValidActivityHour2WithObservation() {

		boolean alltrue = true;
		if (((EditText) findViewById(R.id.q6)).length() > 0) {
			q6 = ((EditText) findViewById(R.id.q6)).getText().toString();
		} else {
			return alltrue = false;
		}

		viewGroup = (GridLayout) findViewById(R.id.observation_grid_hour2gl);
		int nrOfChildren = viewGroup.getChildCount();

		for (int i = 0; i < nrOfChildren; i++) {

			if (viewGroup.getVisibility() == View.GONE)
				break;

			View view = viewGroup.getChildAt(i);

			if (view.getVisibility() == View.GONE)
				continue;

			if (view instanceof EditText) {
				if (!(((EditText) view).getText().toString().length() > 0)) {

					return alltrue = false;
				}

			}

			else if (view instanceof RadioGroup) {

				if (view.getVisibility() == View.GONE)
					continue;

				if (((RadioGroup) view).getCheckedRadioButtonId() == -1) {
					Log.e("From Validation", String.valueOf(findViewById(
							view.getId()).toString()));
					return alltrue = false;

				}

			}

		}

		viewGroup = (LinearLayout) findViewById(R.id.sample_end_2hourln);
		nrOfChildren = viewGroup.getChildCount();

		for (int i = 0; i < nrOfChildren; i++) {
			if (viewGroup.getVisibility() == View.GONE)
				break;
			View view = viewGroup.getChildAt(i);

			if (view.getVisibility() == View.GONE)
				continue;

			if (view instanceof EditText) {
				if (!(((EditText) view).getText().toString().length() > 0)) {

					return alltrue = false;
				}

			}

			else if (view instanceof RadioGroup) {

				if (view.getVisibility() == View.GONE)
					continue;

				if (((RadioGroup) view).getCheckedRadioButtonId() == -1) {
					return alltrue = false;
				}

			}

		}
		

		viewGroup = (LinearLayout) findViewById(R.id.extraqh2);
		nrOfChildren = viewGroup.getChildCount();

		for (int i = 0; i < nrOfChildren; i++) {

			if (viewGroup.getVisibility() == View.GONE)
				break;

			View view = viewGroup.getChildAt(i);

			if (view.getVisibility() == View.GONE)
				continue;

			if (view instanceof EditText) {
				if (!(((EditText) view).getText().toString().length() > 0)) {

					return alltrue = false;
				}

			}

			else if (view instanceof RadioGroup) {

				if (view.getVisibility() == View.GONE)
					continue;

				if (((RadioGroup) view).getCheckedRadioButtonId() == -1) {
					return alltrue = false;
				}

			}

		}

		if (IsViewVisible(R.id.q8_otheret))
			q8_other = ((EditText) findViewById(R.id.q8_otheret)).getText()
					.toString();

		/*if (IsViewVisible(R.id.q9et))
			q9 = ((EditText) findViewById(R.id.q9et)).getText().toString();

		if (IsViewVisible(R.id.q10et))
			q10 = ((EditText) findViewById(R.id.q10et)).getText().toString();

		if (IsViewVisible(R.id.q11et))
			q11 = ((EditText) findViewById(R.id.q11et)).getText().toString();

		if (IsViewVisible(R.id.q12et))
			q12 = ((EditText) findViewById(R.id.q12et)).getText().toString();*/

		if (IsViewVisible(R.id.q41et))
			q41 = ((EditText) findViewById(R.id.q41et)).getText().toString();

		if (IsViewVisible(R.id.q47et))
			q47 = ((EditText) findViewById(R.id.q47et)).getText().toString();

		if (IsViewVisible(R.id.q48et))
			q48 = ((EditText) findViewById(R.id.q48et)).getText().toString();

		if (IsViewVisible(R.id.q49et))
			q49 = ((EditText) findViewById(R.id.q49et)).getText().toString();

		if (IsViewVisible(R.id.q50et))
			q50 = ((EditText) findViewById(R.id.q50et)).getText().toString();

		if (IsViewVisible(R.id.q51et))
			q51 = ((EditText) findViewById(R.id.q51et)).getText().toString();

		if (IsViewVisible(R.id.q52s1hour2))
			q52s1hour2 = ((TextView) findViewById(R.id.q52s1hour2)).getText()
					.toString();

		if (IsViewVisible(R.id.q52s2hour2))
			q52s2hour2 = ((TextView) findViewById(R.id.q52s2hour2)).getText()
					.toString();

		if (IsViewVisible(R.id.q52s3hour2))
			q52s3hour2 = ((TextView) findViewById(R.id.q52s3hour2)).getText()
					.toString();

		if (IsViewVisible(R.id.q52s4hour2))
			q52s4hour2 = ((TextView) findViewById(R.id.q52s4hour2)).getText()
					.toString();

		if (IsViewVisible(R.id.q52s5hour2))
			q52s5hour2 = ((TextView) findViewById(R.id.q52s5hour2)).getText()
					.toString();

		if (IsViewVisible(R.id.q52s6hour2))
			q52s6hour2 = ((TextView) findViewById(R.id.q52s6hour2)).getText()
					.toString();

		if (IsViewVisible(R.id.q53s1hour2))
			q53s1hour2 = ((TextView) findViewById(R.id.q53s1hour2)).getText()
					.toString();

		if (IsViewVisible(R.id.q53s2hour2))
			q53s2hour2 = ((TextView) findViewById(R.id.q53s2hour2)).getText()
					.toString();

		if (IsViewVisible(R.id.q53s3hour2))
			q53s3hour2 = ((TextView) findViewById(R.id.q53s3hour2)).getText()
					.toString();

		if (IsViewVisible(R.id.q53s4hour2))
			q53s4hour2 = ((TextView) findViewById(R.id.q53s4hour2)).getText()
					.toString();

		if (IsViewVisible(R.id.q53s5hour2))
			q53s5hour2 = ((TextView) findViewById(R.id.q53s5hour2)).getText()
					.toString();

		if (IsViewVisible(R.id.q53s6hour2))
			q53s6hour2 = ((TextView) findViewById(R.id.q53s6hour2)).getText()
					.toString();

		if (IsViewVisible(R.id.q56_othereth2))
			q56hour2_other = ((TextView) findViewById(R.id.q56_othereth2))
					.getText().toString();

		q54s1hour2 = "U";
		q54s2hour2 = "U";
		q54s3hour2 = "U";
		q54s4hour2 = "U";
		q54s5hour2 = "U";
		q54s6hour2 = "U";
		
		

		if (IsViewVisible(R.id.q57eth2))
			q57hour2 = ((EditText) findViewById(R.id.q57eth2)).getText()
					.toString();

		return alltrue;
	}

	private boolean IsTimeLessThanFiveHour() {
		long timeinhour = 0;
		try {
			String time1 = ((EditText) findViewById(R.id.q41et)).getText()
					.toString();
			String time2 = ((EditText) findViewById(R.id.q24et)).getText()
					.toString();

			if (time1.equalsIgnoreCase("") || time1.equalsIgnoreCase("")) {
				return true;
			}
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date date1 = format.parse(time1);
			Date date2 = format.parse(time2);
			long difference = date2.getTime() - date1.getTime();
			timeinhour = difference / (60 * 60 * 1000);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		if (timeinhour < 5)
			return true;
		else
			return false;
	}
}
