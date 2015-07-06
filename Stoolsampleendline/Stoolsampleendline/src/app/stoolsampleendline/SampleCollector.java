package app.stoolsampleendline;

import java.io.IOException;
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
import android.graphics.Typeface;
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
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
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
import app.stoolsampleendline.db.DatabaseHelper;

import com.app.stoolsampleendline.R;

public class SampleCollector extends BaseActivity {

	public interface AliqoutSelcetionListener {
		public void execute();
	}

	private Context con;

	private String q5 = "", q6 = "", q7s1 = "", q7s2 = "", q7s3 = "",
			q7s4 = "", q7s5 = "", q7s6 = "", q8s1 = "", q8s2 = "", q8s3 = "", q8s4 = "",
			q8s5 = "", q8s6 = "", q10s1 = "", q10s2 = "", q10s3 = "", q10s4 = "",
			q10s5 = "",q10s6 = "", q11 = "", q11_other = "", q12 = "", q13 = "", q14 = "",
			q15 = "", q16 = "", q17 = "", q17_other = "", q18 = "",
			q18_other = "", q19 = "", q20 = "", q21 = "", q22 = "", q23 = "",
			q23_other = "", q24="", q25="", q26="";
	
	int whichDateText = 0;

	// private TextView randomIdHolder;

	static final int DATE_DIALOG = 1, TIME_DIALOG = 2, CUST_DATE = 3;
	private int dateYear;
	private int dateMonth;
	private int dateDay;
	private int hour;
	private int min;
	private boolean datasaved;
	private int thisYear;
	private int thisMonth;
	private int thisDay;
	private static final int UPDATEDONE = 900;
	private static final int UI_Loaded = 800;
	private ProgressDialog progressDialog;
	private EditText pickDate, pickTime, pickd;
	private ViewGroup viewGroup;

	String sql = "";

	private String q5prev = "", q6prev = "", q7s1prev = "", q7s2prev = "",
			q7s3prev = "", q7s4prev = "", q7s5prev = "", q7s6prev = "", q8s1prev = "",
			q8s2prev = "", q8s3prev = "", q8s4prev = "", q8s5prev = "",q8s6prev = "",
			q10s1prev = "", q10s2prev = "", q10s3prev = "", q10s4prev = "",
			q10s5prev = "", q11prev = "", q11_otherprev = "", q12prev = "",
			q13prev = "", q14prev = "", q15prev = "", q16prev = "",
			q17prev = "", q17_otherprev = "", q18prev = "", q18_otherprev = "",
			q19prev = "", q20prev = "", q21prev = "", q22prev = "",
			q23prev = "", q23_otherprev = "", childNo = "", q24prev="", q25prev="", q26prev="";

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

		// return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menu_Bangla:
			CreateEnglishUI();
			SetFontToBangla();
			return true;

		case R.id.menu_English:

			CreateEnglishUI();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}

	}

	private void CreateEnglishUI() {
		setContentView(R.layout.main_layout);
		setTitle("Stool Sample :: Data ID: "
				+ CommonStaticClass.dataId.toString() + " Child No :"
				+ String.valueOf(CommonStaticClass.currentChildrenCount));

		loadAllUI();

		SetNextButtonToDoneButtonInKeyBoard(((EditText) findViewById(R.id.q6)));
		SetNextButtonToDoneButtonInKeyBoard(((EditText) findViewById(R.id.q11_otheret)));
		SetNextButtonToDoneButtonInKeyBoard(((EditText) findViewById(R.id.q12et)));
		SetNextButtonToDoneButtonInKeyBoard(((EditText) findViewById(R.id.q13et)));
		disableall();

		final Calendar c = Calendar.getInstance();
		dateYear = c.get(Calendar.YEAR);
		dateMonth = c.get(Calendar.MONTH);
		dateDay = c.get(Calendar.DAY_OF_MONTH);

		thisYear = c.get(Calendar.YEAR);
		thisMonth = c.get(Calendar.MONTH);
		thisDay = c.get(Calendar.DAY_OF_MONTH);

		hour = c.get(Calendar.HOUR_OF_DAY);
		min = c.get(Calendar.MINUTE);

		findViewById(R.id.btnSave).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

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
			}
		});

		((Button) findViewById(R.id.btnExit))
				.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						new AlertDialog.Builder(con)
								.setTitle("Confirm")
								.setMessage(
										"Are you sure you want to exit? (Make sure you've saved)")
								.setPositiveButton("Yes",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int whichButton) {
												resetSomeData();
												finish();
												// dialog.dismiss();

											}
										})
								.setNegativeButton("No",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int whichButton) {
												dialog.dismiss();
											}
										})

								.setCancelable(false).show();

					}
				});

	}

	private void SetFontToBangla() {
		// TODO Auto-generated method stub

		Typeface font = Typeface.createFromAsset(getAssets(),
				"Siyam Rupali ANSI.ttf");

		((TextView) findViewById(R.id.TextView05))
				.setText("16. jÿ¨ Kiæb: msMªn Kiv cvqLvbvi avivevwnKZv");
		((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup16))
				.findViewById(R.id.radio0)).setText("1.¯^vfvweK cvqLvbv");
		((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup16))
				.findViewById(R.id.radio1))
				.setText("2. cvZjv cvqLvbv (Wvqwiqv)");
		((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup16))
				.findViewById(R.id.radio2))
				.setText("3. k³ cvqLvbv (‡KŠôKvwV‡b¨i gZ)");

		((TextView) findViewById(R.id.TextView06))
				.setText("18. jÿ¨ Kiæb: msMªn Kiv cvqLvbvq †Kvb A¯^vfvweKZv Av‡Q?");
		((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup18))
				.findViewById(R.id.radio0))
				.setText("1.n¨uv, AvVv‡jv ev wcw”Qj");
		((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup18))
				.findViewById(R.id.radio1)).setText("2. n¨uv, i³");
		((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup18))
				.findViewById(R.id.radio2)).setText("3. n¨uv, K„wg");
		((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup18))
				.findViewById(R.id.radio3)).setText("4. n¨uv, Ab¨vb¨");
		((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup18))
				.findViewById(R.id.radio4)).setText("5. bv");

		((TextView) findViewById(R.id.textView19))
				.setText("19. cÖkœ Kiæb: Avcbvi ev”Pvi (ev”Pvi bvg) wK  GLb Wvqwiqv Av‡Q?");
		((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup19))
				.findViewById(R.id.radio0)).setText("1.n¨uv");
		((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup19))
				.findViewById(R.id.radio1)).setText("2.bv");
		((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup19))
				.findViewById(R.id.radio2)).setText("99.Rvwbbv");

		((TextView) findViewById(R.id.textView20))
				.setText("20. cÖkœ Kiæb: ev”Pvi (ev”Pvi bvg) GKvav‡i KZw`b Wvqwiqv wQj? (‡bvU: mwVK ev AvbygvwbK KZ w`b †mUv ‡`qv n‡q‡Q)");

		((TextView) findViewById(R.id.textView21))
				.setText("21. cÖkœ Kiæb: ev”Pvi (ev”Pvi bvg) KZw`b Av‡M Wvqwiqv n‡qwQj? 88 KL‡bv bv, 99. Rvwbbv");

		((TextView) findViewById(R.id.textView22))
				.setText("22. cÖkœ Kiæb: ‡m mgq GKvav‡i KZw`b Wvqwiqv wQj? 99. Rvwbbv (‡bvU: mwVK ev AvbygvwbK KZ w`b †mUv ‡`qv n‡q‡Q)");

		((TextView) findViewById(R.id.TextView05)).setTypeface(font);
		((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup16))
				.findViewById(R.id.radio0)).setTypeface(font);
		((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup16))
				.findViewById(R.id.radio1)).setTypeface(font);
		((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup16))
				.findViewById(R.id.radio2)).setTypeface(font);

		((TextView) findViewById(R.id.TextView06)).setTypeface(font);
		((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup18))
				.findViewById(R.id.radio0)).setTypeface(font);
		((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup18))
				.findViewById(R.id.radio1)).setTypeface(font);
		((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup18))
				.findViewById(R.id.radio2)).setTypeface(font);
		((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup18))
				.findViewById(R.id.radio3)).setTypeface(font);
		((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup18))
				.findViewById(R.id.radio4)).setTypeface(font);

		((TextView) findViewById(R.id.textView19)).setTypeface(font);
		((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup19))
				.findViewById(R.id.radio0)).setTypeface(font);
		((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup19))
				.findViewById(R.id.radio1)).setTypeface(font);
		((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup19))
				.findViewById(R.id.radio2)).setTypeface(font);

		((TextView) findViewById(R.id.textView20)).setTypeface(font);

		((TextView) findViewById(R.id.textView21)).setTypeface(font);

		((TextView) findViewById(R.id.textView22)).setTypeface(font);

	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		con = this;
		
		CreateEnglishUI();
		

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
					resetEveryValue((LinearLayout) findViewById(R.id.lnView));
					resetEveryValue((GridLayout) findViewById(R.id.grdView));
					datasaved = false;
					setTitle("Stool Sample :: Data ID: "
							+ CommonStaticClass.dataId.toString()
							+ " Child No :"
							+ String.valueOf(CommonStaticClass.currentChildrenCount));
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

			sql = String
					.format("UPDATE tblSamples SET q6 = '%s',  q7s1 = '%s',  q7s2 = '%s',  q7s3 = '%s',  q7s4 = '%s',"
							+ "q7s5 = '%s', q7s6 = '%s', q8s1 = '%s',  q8s2 = '%s',  q8s3 = '%s',  q8s4 = '%s',  q8s5 = '%s',  q8s6 = '%s',"
							+ "q10s1 = '%s',  q10s2 = '%s',  q10s3 = '%s',  q10s4 = '%s',  q10s5 = '%s', q10s6 = '%s',"
							+ "q11 = '%s',  q11_other = '%s',  q12 = '%s',  q13 = '%s',  q14 = '%s',  q15 = '%s',"
							+ "q16 = '%s',  q17 = '%s',  q17_other = '%s',  q18 = '%s',  q18_other = '%s',"
							+ "q19 = '%s',  q20 = '%s',  q21 = '%s', q22 = '%s', q23 = '%s', q23_other = '%s', q24 = '%s', q25='%s', q26='%s', EditBy='%s',EditDate='%s' "
							+ " WHERE dataid = '%s' AND childno = '%s'", q6,
							q7s1, q7s2, q7s3, q7s4, q7s5, q7s6, q8s1, q8s2, q8s3,
							q8s4, q8s5, q8s6, q10s1, q10s2, q10s3, q10s4, q10s5, q10s6, q11,
							q11_other, q12, q13, q14, q15, q16, q17, q17_other,
							q18, q18_other, q19, q20, q21, q22, q23, q23_other,q24, q25,q26,
							CommonStaticClass.userSpecificId, entryDate,
							CommonStaticClass.dataId, childNo);

		} else if (CommonStaticClass.mode
				.equalsIgnoreCase(CommonStaticClass.EDITMODE)) {

			/*
			 * String old_entryBy = DatabaseHelper.getInstance(con)
			 * .GetSingleColumnData("EntryBy", "tblSamples"); String
			 * old_entrydate = DatabaseHelper.getInstance(con)
			 * .GetSingleColumnData("EntryDate", "tblSamples"); int num =
			 * Integer.parseInt(DatabaseHelper.getInstance(con)
			 * .GetSingleColumnData("q5", "tblSamples"));
			 */

			sql = String
					.format("UPDATE tblSamples SET q6 = '%s',  q7s1 = '%s',  q7s2 = '%s',  q7s3 = '%s',  q7s4 = '%s',"
							+ "q7s5 = '%s', q7s6 = '%s', q8s1 = '%s',  q8s2 = '%s',  q8s3 = '%s',  q8s4 = '%s',  q8s5 = '%s',  q8s6 = '%s',"
							+ "q10s1 = '%s',  q10s2 = '%s',  q10s3 = '%s',  q10s4 = '%s',  q10s5 = '%s', q10s6 = '%s',"
							+ "q11 = '%s',  q11_other = '%s',  q12 = '%s',  q13 = '%s',  q14 = '%s',  q15 = '%s',"
							+ "q16 = '%s',  q17 = '%s',  q17_other = '%s',  q18 = '%s',  q18_other = '%s',"
							+ "q19 = '%s',  q20 = '%s',  q21 = '%s', q22 = '%s', q23 = '%s', q23_other = '%s', q24 = '%s', q25='%s',q26='%s', EditBy='%s',EditDate='%s' "
							+ " WHERE dataid = '%s' AND childno = '%s'", q6,
							q7s1, q7s2, q7s3, q7s4, q7s5, q7s6, q8s1, q8s2, q8s3,
							q8s4, q8s5, q8s6, q10s1, q10s2, q10s3, q10s4, q10s5, q10s6, q11,
							q11_other, q12, q13, q14, q15, q16, q17, q17_other,
							q18, q18_other, q19, q20, q21, q22, q23, q23_other,q24, q25,q26,
							CommonStaticClass.userSpecificId, entryDate,
							CommonStaticClass.dataId, childNo);

			CommonStaticClass.currentChildrenCount = 0;
		}

		try {
			if (DatabaseHelper.getInstance(con).executeDMLQuery(sql))

			{

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

			/*
			 * if (CommonStaticClass.mode
			 * .equalsIgnoreCase(CommonStaticClass.SPECIALADD)) { sql =
			 * "Update tblMainQues  SET NumChildren='" +
			 * CommonStaticClass.numberofchildren + "' where dataid='" +
			 * CommonStaticClass.dataId + "'";
			 * 
			 * }
			 */
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

			q5prev = CommonStaticClass.itemToEdite.getQ5();

			q6prev = CommonStaticClass.itemToEdite.getQ6();
			q6 = q6prev;
			((EditText) findViewById(R.id.q6)).setText(q6prev);

			q7s1prev = CommonStaticClass.itemToEdite.getQ7s1();
			q7s1 = q7s1prev;
			((TextView) findViewById(R.id.q7s1)).setText(q7s1prev);

			q7s2prev = CommonStaticClass.itemToEdite.getQ7s2();
			q7s2 = q7s2prev;
			((TextView) findViewById(R.id.q7s2)).setText(q7s2prev);

			q7s3prev = CommonStaticClass.itemToEdite.getQ7s3();
			q7s3 = q7s3prev;
			((TextView) findViewById(R.id.q7s3)).setText(q7s3prev);

			q7s4prev = CommonStaticClass.itemToEdite.getQ7s4();
			q7s4 = q7s4prev;
			((TextView) findViewById(R.id.q7s4)).setText(q7s4prev);

			q7s5prev = CommonStaticClass.itemToEdite.getQ7s5();
			q7s5 = q7s5prev;
			((TextView) findViewById(R.id.q7s5)).setText(q7s5prev);
			
			q7s6prev = CommonStaticClass.itemToEdite.getQ7s6();
			q7s6 = q7s6prev;
			((TextView) findViewById(R.id.q7s6)).setText(q7s6prev);
			

			q8s1prev = CommonStaticClass.itemToEdite.getQ8s1();
			q8s1 = q8s1prev;
			((TextView) findViewById(R.id.q8s1)).setText(q8s1prev);

			q8s2prev = CommonStaticClass.itemToEdite.getQ8s2();
			q8s2 = q8s2prev;
			((TextView) findViewById(R.id.q8s2)).setText(q8s2prev);

			q8s3prev = CommonStaticClass.itemToEdite.getQ8s3();
			q8s3 = q8s3prev;
			((TextView) findViewById(R.id.q8s3)).setText(q8s3prev);

			q8s4prev = CommonStaticClass.itemToEdite.getQ8s4();
			q8s4 = q8s4prev;
			((TextView) findViewById(R.id.q8s4)).setText(q8s4prev);

			q8s5prev = CommonStaticClass.itemToEdite.getQ8s5();
			q8s5 = q8s5prev;
			((TextView) findViewById(R.id.q8s5)).setText(q8s5prev);
			
			q8s6prev = CommonStaticClass.itemToEdite.getQ8s6();
			q8s6 = q8s6prev;
			((TextView) findViewById(R.id.q8s6)).setText(q8s6prev);
			

			q10s1 = CommonStaticClass.itemToEdite.getQ10s1();
			q10s2 = CommonStaticClass.itemToEdite.getQ10s2();
			q10s3 = CommonStaticClass.itemToEdite.getQ10s3();
			q10s4 = CommonStaticClass.itemToEdite.getQ10s4();
			q10s5 = CommonStaticClass.itemToEdite.getQ10s5();
			q10s6 = CommonStaticClass.itemToEdite.getQ10s6();
			q11 = CommonStaticClass.itemToEdite.getQ11();
			q11_otherprev = CommonStaticClass.itemToEdite.getQ11_other();
			q11_other = q11_otherprev;

			((EditText) findViewById(R.id.q11_otheret)).setText(q11_other);

			if (q10s1 != null) {
				switch (Integer.parseInt(q10s1)) {
				case 1:
					((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup10s1))
							.findViewById(R.id.radio0)).toggle();
					break;

				case 2:
					((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup10s1))
							.findViewById(R.id.radio1)).toggle();
					break;

				case 3:
					((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup10s1))
							.findViewById(R.id.radio2)).toggle();
					break;

				default:
					break;
				}
			}

			if (q10s2 != null) {
				switch (Integer.parseInt(q10s2)) {
				case 1:
					((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup10s2))
							.findViewById(R.id.radio0)).toggle();
					break;

				case 2:
					((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup10s2))
							.findViewById(R.id.radio1)).toggle();
					break;

				case 3:
					((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup10s2))
							.findViewById(R.id.radio2)).toggle();
					break;

				default:
					break;
				}
			}
			if (q10s3 != null) {

				switch (Integer.parseInt(q10s3)) {
				case 1:
					((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup10s3))
							.findViewById(R.id.radio0)).toggle();
					break;

				case 2:
					((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup10s3))
							.findViewById(R.id.radio1)).toggle();
					break;

				case 3:
					((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup10s3))
							.findViewById(R.id.radio2)).toggle();
					break;

				default:
					break;
				}
			}

			if (q10s4 != null) {
				switch (Integer.parseInt(q10s4)) {
				case 1:
					((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup10s4))
							.findViewById(R.id.radio0)).toggle();
					break;

				case 2:
					((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup10s4))
							.findViewById(R.id.radio1)).toggle();
					break;

				case 3:
					((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup10s4))
							.findViewById(R.id.radio2)).toggle();
					break;

				default:
					break;
				}
			}

			if (q10s5 != null) {

				switch (Integer.parseInt(q10s5)) {
				case 1:
					((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup10s5))
							.findViewById(R.id.radio0)).toggle();
					break;

				case 2:
					((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup10s5))
							.findViewById(R.id.radio1)).toggle();
					break;

				case 3:
					((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup10s5))
							.findViewById(R.id.radio2)).toggle();
					break;

				default:
					break;
				}
			}
			if (q10s6 != null) {

				switch (Integer.parseInt(q10s6)) {
				case 1:
					((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup10s6))
							.findViewById(R.id.radio0)).toggle();
					break;

				case 2:
					((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup10s6))
							.findViewById(R.id.radio1)).toggle();
					break;

				case 3:
					((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup10s6))
							.findViewById(R.id.radio2)).toggle();
					break;

				default:
					break;
				}
			}
			if (q11 != null) {

				if (q11.trim() != "" && q11.length() > 0) {
					switch (Integer.parseInt(q11)) {
					case 1:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup11))
								.findViewById(R.id.radio0)).toggle();
						break;

					case 2:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup11))
								.findViewById(R.id.radio1)).toggle();
						break;

					case 3:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup11))
								.findViewById(R.id.radio2)).toggle();
						break;

					case 4:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup11))
								.findViewById(R.id.radio3)).toggle();
						break;

					case 5:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup11))
								.findViewById(R.id.radio4)).toggle();
						break;

					case 6:
						((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup11))
								.findViewById(R.id.radio5)).toggle();
						break;

					default:
						break;
					}
				}
			}
			/*
			 * ((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup11))
			 * .getChildAt(Integer.parseInt(q11)-1)).toggle();
			 */
			q12prev = CommonStaticClass.itemToEdite.getQ12();
			q12 = q12prev;
			((EditText) findViewById(R.id.q12et)).setText(q12prev);

			q13prev = CommonStaticClass.itemToEdite.getQ13();
			q13 = q13prev;
			((EditText) findViewById(R.id.q13et)).setText(q13prev);

			/*
			 * ((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup14))
			 * .getChildAt(Integer.parseInt(CommonStaticClass.itemToEdite
			 * .getQ14()))).toggle();
			 */

			q14prev = CommonStaticClass.itemToEdite.getQ14();
			q14 = q14prev;

			if (q14 != null) {
				if (!q14.equalsIgnoreCase("null")) {
					if (q14.trim() != "" && q14.length() > 0) {

						switch (Integer.parseInt(q14)) {
						case 1:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup14))
									.findViewById(R.id.radio0)).toggle();
							break;

						case 2:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup14))
									.findViewById(R.id.radio1)).toggle();
							break;

						default:
							break;
						}
					}
				}
			}
			q15prev = CommonStaticClass.itemToEdite.getQ15();
			q15 = q15prev;
			((EditText) findViewById(R.id.q15et)).setText(q15prev);

			/*
			 * ((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup16))
			 * .getChildAt(Integer.parseInt(CommonStaticClass.itemToEdite
			 * .getQ16()))).toggle();
			 */
			q16prev = CommonStaticClass.itemToEdite.getQ16();
			q16 = q16prev;

			if (q16 != null) {

				if (!q16.equalsIgnoreCase("null")) {
					if (q16.trim() != "" && q16.length() > 0) {

						switch (Integer.parseInt(q16)) {
						case 1:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup16))
									.findViewById(R.id.radio0)).toggle();
							break;
						case 2:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup16))
									.findViewById(R.id.radio1)).toggle();
							break;
						case 3:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup16))
									.findViewById(R.id.radio2)).toggle();
							break;

						default:
							break;
						}
					}
				}
			}

			q17prev = CommonStaticClass.itemToEdite.getQ17();
			q17 = q17prev;

			q17_otherprev = CommonStaticClass.itemToEdite.getQ17_other();
			q17_other = q17_otherprev;
			((EditText) findViewById(R.id.q17_otheret)).setText(q17_other);

			if (q17 != null) {
				if (!q17.equalsIgnoreCase("null")) {
					if (q17.trim() != "" && q17.length() > 0) {

						switch (Integer.parseInt(q17)) {
						case 1:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup17))
									.findViewById(R.id.radio0)).toggle();
							break;

						case 2:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup17))
									.findViewById(R.id.radio1)).toggle();
							break;

						case 3:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup17))
									.findViewById(R.id.radio2)).toggle();
							break;

						case 4:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup17))
									.findViewById(R.id.radio3)).toggle();
							break;

						case 5:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup17))
									.findViewById(R.id.radio4)).toggle();
							break;

						case 6:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup17))
									.findViewById(R.id.radio5)).toggle();
							break;

						case 7:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup17))
									.findViewById(R.id.radio6)).toggle();
							break;

						default:
							break;
						}
					}
				}
			}

			q18prev = CommonStaticClass.itemToEdite.getQ18();
			q18 = q18prev;

			q18_otherprev = CommonStaticClass.itemToEdite.getQ18_other();
			q18_other = q18_otherprev;
			((EditText) findViewById(R.id.q18_otheret)).setText(q18_other);

			if (q18 != null) {
				if (!q18.equalsIgnoreCase("null")) {
					if (q18.trim() != "" && q18.length() > 0) {

						switch (Integer.parseInt(q18)) {
						case 1:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup18))
									.findViewById(R.id.radio0)).toggle();
							break;

						case 2:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup18))
									.findViewById(R.id.radio1)).toggle();
							break;

						case 3:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup18))
									.findViewById(R.id.radio2)).toggle();
							break;

						case 4:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup18))
									.findViewById(R.id.radio3)).toggle();
							break;

						case 5:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup18))
									.findViewById(R.id.radio4)).toggle();
							break;

						default:
							break;
						}
					}
				}
			}

			q19prev = CommonStaticClass.itemToEdite.getQ19();
			q19 = q19prev;

			if (q19 != null) {
				if (!q19.equalsIgnoreCase("null")) {
					if (q19.trim() != "" && q19.length() > 0) {

						switch (Integer.parseInt(q19)) {
						case 1:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup19))
									.findViewById(R.id.radio0)).toggle();
							break;

						case 2:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup19))
									.findViewById(R.id.radio1)).toggle();
							break;

						case 99:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup19))
									.findViewById(R.id.radio2)).toggle();
							break;

						default:
							break;
						}
					}
				}
			}
			q20prev = CommonStaticClass.itemToEdite.getQ20();
			q20 = q20prev;
			((EditText) findViewById(R.id.q20et)).setText(q20prev);

			q21prev = CommonStaticClass.itemToEdite.getQ21();
			q21 = q21prev;
			((EditText) findViewById(R.id.q21et)).setText(q21prev);

			q22prev = CommonStaticClass.itemToEdite.getQ22();
			q22 = q22prev;
			((EditText) findViewById(R.id.q22et)).setText(q22prev);

			q23prev = CommonStaticClass.itemToEdite.getQ23();
			q23_otherprev = CommonStaticClass.itemToEdite.getQ23_other();
			q23 = q23prev;

			if (q23 != null) {
				if (!q23.equalsIgnoreCase("null")) {
					if (q23.trim() != "" && q23.length() > 0) {

						switch (Integer.parseInt(q23)) {
						case 1:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup23))
									.findViewById(R.id.radio0)).toggle();
							break;

						case 2:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup23))
									.findViewById(R.id.radio1)).toggle();
							break;

						case 3:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup23))
									.findViewById(R.id.radio2)).toggle();
							break;

						case 4:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup23))
									.findViewById(R.id.radio3)).toggle();
							break;
						case 5:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup23))
									.findViewById(R.id.radio4)).toggle();
							break;
							
						case 6:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup23))
									.findViewById(R.id.radio5)).toggle();
							break;
							
						case 7:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup23))
									.findViewById(R.id.radio6)).toggle();

							if (q23_otherprev != null)
								((EditText) findViewById(R.id.q23_otheret))
										.setText(q23_otherprev);

							break;

						case 77:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup23))
									.findViewById(R.id.radio7)).toggle();
							break;

						default:
							break;
						}
					}
				}
			}
			
			
			q24prev = CommonStaticClass.itemToEdite.getQ24();
			
			q24 = q24prev;
			
			
			if (q24 != null) {
				if (!q24.equalsIgnoreCase("null")) {
					if (q24.trim() != "" && q24.length() > 0) {

						switch (Integer.parseInt(q24)) {
						case 1:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup24))
									.findViewById(R.id.radio0)).toggle();
							break;

						case 2:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup24))
									.findViewById(R.id.radio1)).toggle();
							break;
 
						case 99:
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup24))
									.findViewById(R.id.radio2)).toggle();
							break;

						

						default:
							break;
						}
					}
				}
			}
			
			q25prev = CommonStaticClass.itemToEdite.getQ25();
			
			q25 = q25prev;
			
			((EditText)findViewById(R.id.q25et)).setText(q25);
			
			
			q26prev = CommonStaticClass.itemToEdite.getQ26();
			
			q26 = q26prev;
			
			((EditText)findViewById(R.id.q26et)).setText(q26);
			

		}

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
		case CUST_DATE:
			return new DatePickerDialog(this, dateSetListenertwo, dateYear,
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
			
		case CUST_DATE:
			((DatePickerDialog) dialog)
					.updateDate(dateYear, dateMonth, dateDay);
			break;
			
		case TIME_DIALOG:
			((TimePickerDialog) dialog).updateTime(hour, min);
			break;
		}
	}

	private DatePickerDialog.OnDateSetListener dateSetListenertwo = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			dateYear = year;
			dateMonth = monthOfYear;
			dateDay = dayOfMonth;
			updateDisplay("ddate");
		}
	};
	
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
		
			String date = new StringBuilder()
					// Month is 0 based so add 1

					.append(dateDay).append(" ")
					.append(GetMonth(dateMonth + 1)).append(" ")
					.append(dateYear).toString();
			
			if(whichDateText == 1)
				pickDate.setText(date);
			else if(whichDateText == 2)
				pickd.setText(date);
		}
		if (dt.contains("ddate")) {
			
			String date = new StringBuilder()
					// Month is 0 based so add 1

					.append(dateDay).append(" ")
					.append(GetMonth(dateMonth + 1)).append(" ")
					.append(dateYear).toString();
			pickd.setText(date);

		}
		/*
		 * if (dt.contains("time")) { String date = new StringBuilder()
		 * 
		 * .append(hour).append(":").append(min).toString();
		 * pickTime.setText(date); }
		 */

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
		case 11:

			findViewById(R.id.textViewq11).setVisibility(View.VISIBLE);
			findViewById(R.id.radioGroup11).setVisibility(View.VISIBLE);
			break;

		case 12:
			findViewById(R.id.TextView12).setVisibility(View.VISIBLE);
			findViewById(R.id.q12et).setVisibility(View.VISIBLE);
			break;
		case 13:
			findViewById(R.id.TextView02).setVisibility(View.VISIBLE);
			findViewById(R.id.q13et).setVisibility(View.VISIBLE);

			break;
		case 14:
			findViewById(R.id.TextView03).setVisibility(View.VISIBLE);
			findViewById(R.id.radioGroup14).setVisibility(View.VISIBLE);
			break;
		case 15:
			findViewById(R.id.TextView04).setVisibility(View.VISIBLE);
			findViewById(R.id.q15et).setVisibility(View.VISIBLE);
			break;
		case 16:
			findViewById(R.id.TextView05).setVisibility(View.VISIBLE);
			findViewById(R.id.radioGroup16).setVisibility(View.VISIBLE);
			break;
		case 17:
			findViewById(R.id.TextView17).setVisibility(View.VISIBLE);
			findViewById(R.id.radioGroup17).setVisibility(View.VISIBLE);
			break;
		case 18:
			findViewById(R.id.TextView06).setVisibility(View.VISIBLE);
			findViewById(R.id.radioGroup18).setVisibility(View.VISIBLE);
			break;

		case 19:
			findViewById(R.id.textView19).setVisibility(View.VISIBLE);
			findViewById(R.id.radioGroup19).setVisibility(View.VISIBLE);
			break;

		case 20:

			findViewById(R.id.textView20).setVisibility(View.VISIBLE);
			findViewById(R.id.q20et).setVisibility(View.VISIBLE);
			break;

		case 21:
			// q18
			findViewById(R.id.textView21).setVisibility(View.VISIBLE);
			findViewById(R.id.q21et).setVisibility(View.VISIBLE);
			// q18Ed.setVisibility(View.GONE);
			break;
		case 22:
			// q18
			findViewById(R.id.textView22).setVisibility(View.VISIBLE);
			findViewById(R.id.q22et).setVisibility(View.VISIBLE);
			// q18Ed.setVisibility(View.GONE);
			break;
		case 23:

			findViewById(R.id.textView23).setVisibility(View.VISIBLE);
			findViewById(R.id.radioGroup23).setVisibility(View.VISIBLE);
			findViewById(R.id.q23_otheret).setVisibility(View.GONE);
			break;
			
		case 24:

			findViewById(R.id.textView24).setVisibility(View.VISIBLE);
			findViewById(R.id.radioGroup24).setVisibility(View.VISIBLE);
			
			break;
			
		case 25:
			findViewById(R.id.textView25).setVisibility(View.VISIBLE);
			findViewById(R.id.q25et).setVisibility(View.VISIBLE);
			break;

		case 26:
			findViewById(R.id.textView26).setVisibility(View.VISIBLE);
			findViewById(R.id.q26et).setVisibility(View.VISIBLE);
			break;
			
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
			findViewById(R.id.textViewq11).setVisibility(View.GONE);
			findViewById(R.id.radioGroup11).setVisibility(View.GONE);
			if (q10s1.equalsIgnoreCase("2")) {
				findViewById(R.id.textViewq11).setVisibility(View.VISIBLE);
				findViewById(R.id.radioGroup11).setVisibility(View.VISIBLE);

			}
			if (q10s2.equalsIgnoreCase("2")) {
				findViewById(R.id.textViewq11).setVisibility(View.VISIBLE);
				findViewById(R.id.radioGroup11).setVisibility(View.VISIBLE);

			}
			if (q10s3.equalsIgnoreCase("2")) {
				findViewById(R.id.textViewq11).setVisibility(View.VISIBLE);
				findViewById(R.id.radioGroup11).setVisibility(View.VISIBLE);

			}
			if (q10s4.equalsIgnoreCase("2")) {
				findViewById(R.id.textViewq11).setVisibility(View.VISIBLE);
				findViewById(R.id.radioGroup11).setVisibility(View.VISIBLE);

			}
			if (q10s5.equalsIgnoreCase("2")) {
				findViewById(R.id.textViewq11).setVisibility(View.VISIBLE);
				findViewById(R.id.radioGroup11).setVisibility(View.VISIBLE);

			}
			if (q10s6.equalsIgnoreCase("2")) {
				findViewById(R.id.textViewq11).setVisibility(View.VISIBLE);
				findViewById(R.id.radioGroup11).setVisibility(View.VISIBLE);

			}
			break;
		case 12:
			findViewById(R.id.TextView12).setVisibility(View.GONE);
			findViewById(R.id.q12et).setVisibility(View.GONE);
			break;
		case 13:
			findViewById(R.id.TextView02).setVisibility(View.GONE);
			findViewById(R.id.q13et).setVisibility(View.GONE);

			break;
		case 14:
			findViewById(R.id.TextView03).setVisibility(View.GONE);
			findViewById(R.id.radioGroup14).setVisibility(View.GONE);
			break;
		case 15:
			findViewById(R.id.TextView04).setVisibility(View.GONE);
			findViewById(R.id.q15et).setVisibility(View.GONE);
			break;
		case 16:
			findViewById(R.id.TextView05).setVisibility(View.GONE);
			findViewById(R.id.radioGroup16).setVisibility(View.GONE);
			break;
		case 17:
			findViewById(R.id.TextView17).setVisibility(View.GONE);
			findViewById(R.id.radioGroup17).setVisibility(View.GONE);
			break;
		case 18:
			findViewById(R.id.TextView06).setVisibility(View.GONE);
			findViewById(R.id.radioGroup18).setVisibility(View.GONE);
			break;

		case 19:
			findViewById(R.id.textView19).setVisibility(View.GONE);
			findViewById(R.id.radioGroup19).setVisibility(View.GONE);
			break;

		case 20:

			findViewById(R.id.textView20).setVisibility(View.GONE);
			findViewById(R.id.q20et).setVisibility(View.GONE);
			break;

		case 21:
			// q18
			findViewById(R.id.textView21).setVisibility(View.GONE);
			findViewById(R.id.q21et).setVisibility(View.GONE);
			// q18Ed.setVisibility(View.GONE);
			break;
		case 22:
			// q18
			findViewById(R.id.textView22).setVisibility(View.GONE);
			findViewById(R.id.q22et).setVisibility(View.GONE);
			// q18Ed.setVisibility(View.GONE);
			break;

		case 23:
			// q18
			findViewById(R.id.textView23).setVisibility(View.GONE);
			findViewById(R.id.radioGroup23).setVisibility(View.GONE);
			findViewById(R.id.q23_otheret).setVisibility(View.GONE);
			break;
			
		case 24:
			
			findViewById(R.id.textView24).setVisibility(View.GONE);
			findViewById(R.id.radioGroup24).setVisibility(View.GONE);
			
			break;
			
		case 25:
			
			findViewById(R.id.textView25).setVisibility(View.GONE);
			findViewById(R.id.q25et).setVisibility(View.GONE);
			break;
			
			
		case 26:
			
			findViewById(R.id.textView26).setVisibility(View.GONE);
			findViewById(R.id.q26et).setVisibility(View.GONE);
			break;
		}
	}

	public void disableall() {
		
	}

	private void loadAllUI() {

		TabHost th = new TabHost(con);
		th = (TabHost) findViewById(R.id.tabhost);
		th.setup();
		TabSpec ts = th.newTabSpec("tab1");
		ts.setContent(R.id.tab1);
		ts.setIndicator("Samples");
		th.addTab(ts);

		ts = th.newTabSpec("tab2");
		ts.setContent(R.id.tab2);
		ts.setIndicator("Questions");
		th.addTab(ts);

		((RadioGroup) findViewById(R.id.radioGroup10s1))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup group, int checkedId) {

						switch (checkedId) {
						case R.id.radio0:
							q10s1 = "1";
							enableQuestion(11);
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup10s2))
									.getChildAt(0)).setChecked(true);
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup10s3))
									.getChildAt(0)).setChecked(true);
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup10s4))
									.getChildAt(0)).setChecked(true);
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup10s5))
									.getChildAt(0)).setChecked(true);
							((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup10s6))
									.getChildAt(0)).setChecked(true);

							disableQuestion(12);
							disableQuestion(13);
							disableQuestion(14);
							disableQuestion(15);
							disableQuestion(16);
							disableQuestion(17);
							disableQuestion(18);
							disableQuestion(19);
							disableQuestion(20);
							disableQuestion(21);
							disableQuestion(22);
							disableQuestion(23);
							disableQuestion(24);
							disableQuestion(25);
							disableQuestion(26);
							break;

						case R.id.radio1:
							q10s1 = "2";
							enableQuestion(11);
							enableQuestion(12);
							enableQuestion(13);
							enableQuestion(14);
							enableQuestion(15);
							enableQuestion(16);
							enableQuestion(17);
							enableQuestion(18);
							enableQuestion(19);
							enableQuestion(20);
							enableQuestion(21);
							enableQuestion(22);
							enableQuestion(23);
							enableQuestion(24);
							enableQuestion(25);
							enableQuestion(26);
							break;

						case R.id.radio2:
							q10s1 = "3";
							disableQuestion(11);
							break;

						}

					}
				});

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
		((TextView) findViewById(R.id.q7s1)).setText(CommonStaticClass.dataId
				+ "E" + CommonStaticClass.currentChildrenCount + "S01");
		((TextView) findViewById(R.id.q8s1))
				.setText(getRandomId(CommonStaticClass.dataId + "E"
						+ CommonStaticClass.currentChildrenCount + "S01"));

		((TextView) findViewById(R.id.q7s2)).setText(CommonStaticClass.dataId
				+ "E" + CommonStaticClass.currentChildrenCount + "S02");
		((TextView) findViewById(R.id.q8s2))
				.setText(getRandomId(CommonStaticClass.dataId + "E"
						+ CommonStaticClass.currentChildrenCount + "S02"));

		((TextView) findViewById(R.id.q7s3)).setText(CommonStaticClass.dataId
				+ "E" + CommonStaticClass.currentChildrenCount + "S03");
		((TextView) findViewById(R.id.q8s3))
				.setText(getRandomId(CommonStaticClass.dataId + "E"
						+ CommonStaticClass.currentChildrenCount + "S03"));

		((TextView) findViewById(R.id.q7s4)).setText(CommonStaticClass.dataId
				+ "E" + CommonStaticClass.currentChildrenCount + "S04");
		((TextView) findViewById(R.id.q8s4))
				.setText(getRandomId(CommonStaticClass.dataId + "E"
						+ CommonStaticClass.currentChildrenCount + "S04"));

		((TextView) findViewById(R.id.q7s5)).setText(CommonStaticClass.dataId
				+ "E" + CommonStaticClass.currentChildrenCount + "S05");
		
		((TextView) findViewById(R.id.q7s6)).setText(CommonStaticClass.dataId
				+ "E" + CommonStaticClass.currentChildrenCount + "T01");
		
		((TextView) findViewById(R.id.q8s5))
				.setText(getRandomId(CommonStaticClass.dataId + "E"
						+ CommonStaticClass.currentChildrenCount + "S05"));
		
		((TextView) findViewById(R.id.q8s6))
		.setText(getRandomId(CommonStaticClass.dataId + "E"
				+ CommonStaticClass.currentChildrenCount + "T01"));

		((RadioGroup) findViewById(R.id.radioGroup10s2))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup group, int checkedId) {

						switch (checkedId) {
						case R.id.radio0:
							q10s2 = "1";
							enableQuestion(11);
							break;

						case R.id.radio1:
							q10s2 = "2";
							enableQuestion(11);
							enableQuestion(12);
							enableQuestion(13);
							enableQuestion(14);
							enableQuestion(15);
							enableQuestion(16);
							enableQuestion(17);
							enableQuestion(18);
							enableQuestion(19);
							enableQuestion(20);
							enableQuestion(21);
							enableQuestion(22);
							enableQuestion(23);
							enableQuestion(24);
							enableQuestion(25);
							enableQuestion(26);
							break;

						case R.id.radio2:
							q10s2 = "3";
							disableQuestion(11);
							break;

						}

					}
				});

		((RadioGroup) findViewById(R.id.radioGroup10s3))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup group, int checkedId) {

						switch (checkedId) {
						case R.id.radio0:
							q10s3 = "1";
							enableQuestion(11);
							break;

						case R.id.radio1:
							q10s3 = "2";
							enableQuestion(11);
							enableQuestion(12);
							enableQuestion(13);
							enableQuestion(14);
							enableQuestion(15);
							enableQuestion(16);
							enableQuestion(17);
							enableQuestion(18);
							enableQuestion(19);
							enableQuestion(20);
							enableQuestion(21);
							enableQuestion(22);
							enableQuestion(23);
							enableQuestion(24);
							enableQuestion(25);
							enableQuestion(26);
							break;

						case R.id.radio2:
							q10s3 = "3";
							disableQuestion(11);
							break;

						}

					}
				});
		((RadioGroup) findViewById(R.id.radioGroup10s4))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup group, int checkedId) {

						switch (checkedId) {
						case R.id.radio0:
							q10s4 = "1";
							enableQuestion(11);
							break;

						case R.id.radio1:
							q10s4 = "2";
							enableQuestion(11);
							enableQuestion(12);
							enableQuestion(13);
							enableQuestion(14);
							enableQuestion(15);
							enableQuestion(16);
							enableQuestion(17);
							enableQuestion(18);
							enableQuestion(19);
							enableQuestion(20);
							enableQuestion(21);
							enableQuestion(22);
							enableQuestion(23);
							enableQuestion(24);
							enableQuestion(25);
							enableQuestion(26);
							break;

						case R.id.radio2:
							q10s4 = "3";
							disableQuestion(11);
							break;

						}

					}
				});
		((RadioGroup) findViewById(R.id.radioGroup10s5))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup group, int checkedId) {

						switch (checkedId) {
						case R.id.radio0:
							q10s5 = "1";
							enableQuestion(11);
							break;

						case R.id.radio1:
							q10s5 = "2";
							enableQuestion(11);
							enableQuestion(12);
							enableQuestion(13);
							enableQuestion(14);
							enableQuestion(15);
							enableQuestion(16);
							enableQuestion(17);
							enableQuestion(18);
							enableQuestion(19);
							enableQuestion(20);
							enableQuestion(21);
							enableQuestion(22);
							enableQuestion(23);
							enableQuestion(24);
							enableQuestion(25);
							enableQuestion(26);
							break;

						case R.id.radio2:
							q10s5 = "3";
							disableQuestion(11);

							break;

						}

					}
				});

		
		((RadioGroup) findViewById(R.id.radioGroup10s6))
		.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {

				switch (checkedId) {
				case R.id.radio0:
					q10s6 = "1";
					enableQuestion(11);
					break;

				case R.id.radio1:
					q10s6 = "2";
					enableQuestion(11);
					enableQuestion(12);
					enableQuestion(13);
					enableQuestion(14);
					enableQuestion(15);
					enableQuestion(16);
					enableQuestion(17);
					enableQuestion(18);
					enableQuestion(19);
					enableQuestion(20);
					enableQuestion(21);
					enableQuestion(22);
					enableQuestion(23);
					enableQuestion(24);
					enableQuestion(25);
					enableQuestion(26);
					break;

				case R.id.radio2:
					q10s6 = "3";
					disableQuestion(11);

					break;

				}

			}
		});
		
		
		findViewById(R.id.q11_otheret).setVisibility(View.GONE);

		((RadioGroup) findViewById(R.id.radioGroup11))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup group, int checkedId) {

						switch (checkedId) {
						case R.id.radio0:
							q11 = "1";
							findViewById(R.id.q11_otheret).setVisibility(
									View.GONE);
							break;

						case R.id.radio1:
							q11 = "2";
							findViewById(R.id.q11_otheret).setVisibility(
									View.GONE);
							break;

						case R.id.radio2:
							q11 = "3";
							findViewById(R.id.q11_otheret).setVisibility(
									View.GONE);
							break;

						case R.id.radio3:
							q11 = "4";
							findViewById(R.id.q11_otheret).setVisibility(
									View.GONE);
							break;

						case R.id.radio4:
							q11 = "5";
							findViewById(R.id.q11_otheret).setVisibility(
									View.GONE);
							break;

						case R.id.radio5:
							q11 = "6";
							findViewById(R.id.q11_otheret).setVisibility(
									View.VISIBLE);
							break;

						}

					}
				});

		findViewById(R.id.q12et).setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				pickDate = (EditText) v;
				showDialog(DATE_DIALOG);
				whichDateText = 1;
				return false;
			}
		});

		findViewById(R.id.q26et).setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				pickd = (EditText) v;
				showDialog(CUST_DATE);
				whichDateText = 2;
				return false;
			}
		});
		
		findViewById(R.id.q13et).setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				pickTime = (EditText) v;
				showDialog(TIME_DIALOG);
				return false;
			}
		});
		findViewById(R.id.q15et).setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				pickTime = (EditText) v;
				showDialog(TIME_DIALOG);
				return false;
			}
		});
		((RadioGroup) findViewById(R.id.radioGroup14))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup group, int checkedId) {

						switch (checkedId) {
						case R.id.radio0:
							q14 = "1";

							break;

						case R.id.radio1:
							q14 = "2";

							break;

						}

					}
				});

		((RadioGroup) findViewById(R.id.radioGroup16))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup group, int checkedId) {

						switch (checkedId) {
						case R.id.radio0:
							q16 = "1";

							break;

						case R.id.radio1:
							q16 = "2";

							break;

						case R.id.radio2:
							q16 = "3";

							break;

						}

					}
				});

		findViewById(R.id.q17_otheret).setVisibility(View.GONE);

		((RadioGroup) findViewById(R.id.radioGroup17))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup group, int checkedId) {

						switch (checkedId) {
						case R.id.radio0:
							q17 = "1";
							findViewById(R.id.q17_otheret).setVisibility(
									View.GONE);
							break;

						case R.id.radio1:
							q17 = "2";
							findViewById(R.id.q17_otheret).setVisibility(
									View.GONE);
							break;

						case R.id.radio2:
							q17 = "3";
							findViewById(R.id.q17_otheret).setVisibility(
									View.GONE);
							break;

						case R.id.radio3:
							q17 = "4";
							findViewById(R.id.q17_otheret).setVisibility(
									View.GONE);
							break;

						case R.id.radio4:
							q17 = "5";
							findViewById(R.id.q17_otheret).setVisibility(
									View.GONE);
							break;

						case R.id.radio5:
							q17 = "6";
							findViewById(R.id.q17_otheret).setVisibility(
									View.GONE);
							break;

						case R.id.radio6:
							q17 = "7";
							findViewById(R.id.q17_otheret).setVisibility(
									View.VISIBLE);
							break;

						}

					}
				});

		findViewById(R.id.q18_otheret).setVisibility(View.GONE);

		((RadioGroup) findViewById(R.id.radioGroup18))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup group, int checkedId) {

						switch (checkedId) {
						case R.id.radio0:
							q18 = "1";
							findViewById(R.id.q18_otheret).setVisibility(
									View.GONE);
							break;

						case R.id.radio1:
							q18 = "2";
							findViewById(R.id.q18_otheret).setVisibility(
									View.GONE);
							break;

						case R.id.radio2:
							q18 = "3";
							findViewById(R.id.q18_otheret).setVisibility(
									View.GONE);
							break;

						case R.id.radio3:
							q18 = "4";
							findViewById(R.id.q18_otheret).setVisibility(
									View.VISIBLE);
							break;

						case R.id.radio4:
							q18 = "5";
							findViewById(R.id.q18_otheret).setVisibility(
									View.GONE);
							break;

						}

					}
				});

		((RadioGroup) findViewById(R.id.radioGroup19))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup group, int checkedId) {

						switch (checkedId) {
						case R.id.radio0:
							q19 = "1";
							enableQuestion(20);
							disableQuestion(21);
							disableQuestion(22);
							break;

						case R.id.radio1:
							q19 = "2";
							disableQuestion(20);
							enableQuestion(21);
							enableQuestion(22);
							break;

						case R.id.radio2:
							q19 = "99";
							disableQuestion(20);
							enableQuestion(21);
							enableQuestion(22);
							break;

						}

					}
				});

		((EditText) findViewById(R.id.q23_otheret)).setVisibility(View.GONE);

		((RadioGroup) findViewById(R.id.radioGroup23))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup group, int checkedId) {
						((EditText) findViewById(R.id.q23_otheret))
								.setVisibility(View.GONE);
						switch (checkedId) {
						case R.id.radio0:
							q23 = "1";
							break;

						case R.id.radio1:
							q23 = "2";
							break;

						case R.id.radio2:
							q23 = "3";
							break;

						case R.id.radio3:
							q23 = "4";
							break;
							
						case R.id.radio4:
							q23 = "5";
							break;
							
						case R.id.radio5:
							q23 = "6";
							break;
							
							

						case R.id.radio6:
							q23 = "7";
							((EditText) findViewById(R.id.q23_otheret))
									.setVisibility(View.VISIBLE);
							break;

						case R.id.radio7:
							q23 = "77";
							break;

						}

					}
				});

		
		
		
		((RadioGroup) findViewById(R.id.radioGroup24))
		.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {
				
				switch (checkedId) {
				case R.id.radio0:
					q24 = "1";
					break;

				case R.id.radio1:
					q24 = "2";
					break;

				case R.id.radio2:
					q24 = "99";
					break;

				

				}

			}
		});
		
		
		
		((EditText) findViewById(R.id.q21et))
				.addTextChangedListener(new TextWatcher() {

					public void onTextChanged(CharSequence arg0, int arg1,
							int arg2, int arg3) {

						if (arg0 != null) {
							if (arg0.length() > 0) {
								if (((EditText) findViewById(R.id.q21et))
										.getText().toString()
										.equalsIgnoreCase("88")

										|| ((EditText) findViewById(R.id.q21et))
												.getText().toString()
												.equalsIgnoreCase("99")) {

									((TextView) findViewById(R.id.textView22))
											.setVisibility(View.GONE);
									((TextView) findViewById(R.id.q22et))
											.setVisibility(View.GONE);

								} else {
									((TextView) findViewById(R.id.textView22))
											.setVisibility(View.VISIBLE);
									((TextView) findViewById(R.id.q22et))
											.setVisibility(View.VISIBLE);
								}

							}
						}

					}

					public void beforeTextChanged(CharSequence arg0, int arg1,
							int arg2, int arg3) {
						// TODO Auto-generated method stub

					}

					public void afterTextChanged(Editable arg0) {
						// TODO Auto-generated method stub

					}
				});

		

	}

	public void resetEveryValue(ViewGroup v) {
		ViewGroup group = v;// (ViewGroup) findViewById(R.id.main_view);
		ViewGroupReferences(group);

		q6 = "";
		q7s1 = "";
		q7s2 = "";
		q7s3 = "";
		q7s4 = "";
		q7s5 = "";
		q7s6 = "";
		q8s1 = "";
		q8s2 = "";
		q8s3 = "";
		q8s4 = "";
		q8s5 = "";
		q8s6 = "";
		q10s1 = "";
		q10s2 = "";
		q10s3 = "";
		q10s4 = "";
		q10s5 = "";
		q10s6 = "";
		q11 = "";
		q11_other = "";
		q12 = "";
		q13 = "";
		q14 = "";
		q15 = "";
		q16 = "";
		q17 = "";
		q17_other = "";
		q18 = "";
		q18_other = "";
		q19 = "";
		q20 = "";
		q21 = "";
		q22 = "";
		q23 = "";
		q24 = "";
		q25 = "";
		q26 = "";
		q23_other = "";

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
				/*
				 * if (view instanceof Spinner) { ((Spinner)
				 * view).setSelection(0); }
				 */
				/*
				 * if (view instanceof CheckBox) { ((CheckBox)
				 * view).setChecked(false); }
				 */
				if (view instanceof RadioGroup) {
					((RadioGroup) view).clearCheck();
					/*
					 * ViewGroup rgroup1 = ((RadioGroup) view); for (int r1 = 0,
					 * r1count = rgroup1.getChildCount(); r1 < r1count; ++r1) {
					 * if (view instanceof RadioButton) { ((RadioButton)
					 * view).setChecked(false); } }
					 */
				}
			}

			/*
			 * View view = viewGroup.getChildAt(i); ViewReferences(view);
			 * 
			 * if (view instanceof ViewGroup) { ViewGroupReferences((ViewGroup)
			 * view); }
			 */
			// }
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

		q7s1 = ((TextView) findViewById(R.id.q7s1)).getText().toString();
		q7s2 = ((TextView) findViewById(R.id.q7s2)).getText().toString();
		q7s3 = ((TextView) findViewById(R.id.q7s3)).getText().toString();
		q7s4 = ((TextView) findViewById(R.id.q7s4)).getText().toString();
		q7s5 = ((TextView) findViewById(R.id.q7s5)).getText().toString();
		q7s6 = ((TextView) findViewById(R.id.q7s6)).getText().toString();

		q8s1 = ((TextView) findViewById(R.id.q8s1)).getText().toString();
		q8s2 = ((TextView) findViewById(R.id.q8s2)).getText().toString();
		q8s3 = ((TextView) findViewById(R.id.q8s3)).getText().toString();
		q8s4 = ((TextView) findViewById(R.id.q8s4)).getText().toString();
		q8s5 = ((TextView) findViewById(R.id.q8s5)).getText().toString();
		q8s6 = ((TextView) findViewById(R.id.q8s6)).getText().toString();

		q11_other = ((EditText) findViewById(R.id.q11_otheret)).getText()
				.toString();
		q12 = ((EditText) findViewById(R.id.q12et)).getText().toString();
		q13 = ((EditText) findViewById(R.id.q13et)).getText().toString();
		q15 = ((EditText) findViewById(R.id.q15et)).getText().toString();
		q17_other = ((EditText) findViewById(R.id.q17_otheret)).getText()
				.toString();
		q18_other = ((EditText) findViewById(R.id.q18_otheret)).getText()
				.toString();

		q20 = ((EditText) findViewById(R.id.q20et)).getText().toString();
		q21 = ((EditText) findViewById(R.id.q21et)).getText().toString();
		q22 = ((EditText) findViewById(R.id.q22et)).getText().toString();
		q25 = ((EditText) findViewById(R.id.q25et)).getText().toString();
		q26 = ((EditText) findViewById(R.id.q26et)).getText().toString();

		q23_other = ((EditText) findViewById(R.id.q23_otheret)).getText()
				.toString();

		viewGroup = (LinearLayout) findViewById(R.id.lnView);
		int nrOfChildren = viewGroup.getChildCount();

		for (int i = 0; i < nrOfChildren; i++) {
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
		return alltrue;
	}
}
