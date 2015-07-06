package com.icddrb.app.ChildIdentificationdb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.R.integer;
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
import android.text.method.DateTimeKeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
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
import android.widget.Toast;

import com.icddrb.app.ChildIdentificationdb.db.DatabaseHelper;
import com.icddrb.app.Childidentificationendline.R;

public class SampleCollector extends BaseActivity {

	public interface AliqoutSelcetionListener {
		public void execute();
	}

	
	private static final int UPDATEDONE = 900;
	private static final int UI_Loaded = 800;

	private Context con;
	private DatePicker dpResult;
	private int year;
	private int month;
	private int day;

	static final int DATE_DIALOG_ID = 999;
	private String q12, q13, q14, q15, q16, q17, q18_days, q18_months, q18,
			q19_no_of_time, q19,

			q20_time1, q20_other_time1, q21_days_time1, q20_time2,
			q20_other_time2, q21_days_time2, q20_time3, q20_other_time3,
			q21_days_time3, q20_time4, q20_other_time4, q21_days_time4,
			q20_time5, q20_other_time5, q21_days_time5, q22, q23, q24, q25,
			q26_days, q26_months, q26, q27, q27_other, q28, q29,
			// New Variables
			q30A, q30B, q30C, q30D, q30E,

			q31A, q31B, q31C, q31D, q31E,

			q32A, q32B, q32C, q32D, q32E,

			q33A, q33B, q33C, q34A, q34B, q34C, q34D, q34E,

			q35A, q35B, q35C, q35D, q35E,

			q36A, q36B, q36C, q36D, q36E,

			q37A, q37B, q37C, q37D, q37E,

			q38A, q38B, q38C, q38D, q38E,

			q39A, q39B, q39C, q39D, q39E,

			q40A, q40B, q40C, q40D, q40E,

			q41A, q41B, q41C, q41D, q41E,

			q42_Days, q42_weeks, q43_Days, q43_weeks;;

	private EditText pickDate, pickTime, q12et, q13et, q15et, q17et,
			q18dayet,
			q19et,
			q18monthset,
			// time related variable
			q21ettime1et, q21ettime2et, q21ettime3et, q21ettime4et,
			q21ettime5et,

			q26dayset, q26monthset, q27_otheret, q20_other_time1et,
			q20_other_time2et, q20_other_time3et, q20_other_time4et,
			q20_other_time5et,

			q42_Dayset, q42_weekset, q43_Dayset, q43_weekset;;

	private RadioGroup rdogroup14,
			rdogroup16,
			radiogroup18,
			radioGroup19,
			radioGroup22,
			radioGroup23,
			radioGroup24,
			radioGroup25,
			radioGroup27,
			radioGroup28,
			radioGroup29,
			// time related variable
			radioG20time1, radioG20time2, radioG20time3, radioG20time4,
			radioG20time5,

			radioGroup30A, radioGroup30B, radioGroup30C, radioGroup30D,
			radioGroup30E,

			radioGroup31A, radioGroup31B, radioGroup31C, radioGroup31D,
			radioGroup31E,

			radioGroup32A, radioGroup32B, radioGroup32C, radioGroup32D,
			radioGroup32E,

			/*
			 * radioGroup33A, radioGroup33B, radioGroup33C, radioGroup33D,
			 * radioGroup33E,
			 */

			radioGroup34A, radioGroup34B, radioGroup34C, radioGroup34D,
			radioGroup34E,

			radioGroup35A, radioGroup35B, radioGroup35C, radioGroup35D,
			radioGroup35E,

			radioGroup36A, radioGroup36B, radioGroup36C, radioGroup36D,
			radioGroup36E,

			radioGroup37A, radioGroup37B, radioGroup37C, radioGroup37D,
			radioGroup37E,

			radioGroup38A, radioGroup38B, radioGroup38C, radioGroup38D,
			radioGroup38E,

			radioGroup39A, radioGroup39B, radioGroup39C, radioGroup39D,
			radioGroup39E,

			radioGroup40A, radioGroup40B, radioGroup40C, radioGroup40D,
			radioGroup40E,

			radioGroup41A, radioGroup41B, radioGroup41C, radioGroup41D,
			radioGroup41E;

	// dont know
	private RadioButton rdoq26Dontknow, radioGroup2101time1,
			radioGroup2101time2, radioGroup2101time3, radioGroup2101time4,
			radioGroup2101time5;

	static final int DATE_DIALOG = 1, TIME_DIALOG = 2;
	private int dateYear;
	private int dateMonth;
	private int dateDay;
	private int hour;
	private int min;

	private int thisYear;
	private int thisMonth;
	private int thisDay;

	private Button btnSave;

	private ViewGroup viewGroup, ln25to27, vg;
	String sql = "";
	private String childNo = "";

	protected String q21_Dontknow_time1;

	protected String q21_Dontknow_time2;

	protected String q21_Dontknow_time3;

	protected String q21_Dontknow_time4;

	protected String q21_Dontknow_time5;

	private ProgressDialog progressDialog;

	// Typeface font;
	@Override
	public void onBackPressed() {
		// do nothing.
	}

	private void SetNextButtonToDoneButtonInKeyBoard(EditText edittext) {
		edittext.setImeOptions(EditorInfo.IME_ACTION_DONE);

	}

	private void SetFontToBangla() {
		Typeface font = Typeface.createFromAsset(getAssets(),
				"Siyam Rupali ANSI.ttf");
		((RadioButton) findViewById(R.id.radiogroup1601))
				.setText("1.wUKv`vb KvW© †_‡K");
		((RadioButton) findViewById(R.id.radiogroup1602))
				.setText("2.gv/AvZ¥xq Rb¥ ZvwiL ¯§ib K‡i‡Q");
		((RadioButton) findViewById(R.id.radiogroup1603))
				.setText("3.1 Ges 2 `y‡UvB");
		((RadioButton) findViewById(R.id.radiogroup1604)).setText("99.Rvwb bv");
		((TextView) findViewById(R.id.textView16))
				.setText("18. cÖkœ Kiyb:KZ w`b Av‡M ev”Pv (ev”Pvi bvg) Gw›Uev‡qvwUK †L‡q‡Q? (BB m¨v¤új n¨vÛeyK Jla Gi ZvwjKv †_‡K D`vnib w`b)");
		((TextView) findViewById(R.id.textView15))
				.setText("19. cÖkœ Kiyb: Avcwb wK ej‡Z cv‡ib MZ wZb gv‡m Avcbvi ev”Pv KZevi Gw›Uev‡qvwUK ‡L‡q‡Q?");
		((RadioButton) findViewById(R.id.radioGroup1901))
				.setText("88 KL‡bv bv");
		((RadioButton) findViewById(R.id.radioGroup1902)).setText("99 Rvwb bv");

		((TextView) findViewById(R.id.textView4))
				.setText("20. cÖkœ Kiyb: Avcbvi ev”Pv MZ 3 gv‡m †h mKj Gw›Uev‡qvwUK †L‡q‡Q AbyMªn K‡i ‡m¸‡jvi ¯§iY Kiyb| (MZ 90 w`‡b)");
		((TextView) findViewById(R.id.textView12))
				.setText("21. cÖkœ Kiyb: KZw`b ch©šÍ Avcbvi ev”Pv GB Gw›Uev‡qvwUK †L‡q‡Q? (cÖwZ ev‡i)");
		((TextView) findViewById(R.id.textView11))
				.setText("24. cÖkœ Kiyb: (6gvm Ges Zvi Dc‡i ev”Pvi Rb¨)MZ 6gv‡m  Avcbvi ev”Pv (ev”Pvi bvg) wK K…wgi Rb¨ †Kvb ewo ev Jla †L‡q‡Q?(BB m¨v¤új n¨vÛeyK Jla Gi ZvwjKv †_‡K D`vnib w`b)");
		((TextView) findViewById(R.id.textView5))
				.setText("25. cÖkœ Kiæb: K„wgi mgm¨vi Rb¨  ev”Pv (ev”Pvi bvg) †Kv_vq wPwKrmv wb‡q‡Q?");
		((TextView) findViewById(R.id.textView10))
				.setText("26. cÖkœ Kiyb: AvbygvwbK  KZw`b Av‡M ev”Pv (ev”Pvi bvg)  K…wgi Rb¨ Jla †L‡q‡Q?");
		((TextView) findViewById(R.id.textView21)).setText("##w`b");
		((TextView) findViewById(R.id.textView22)).setText("##gvm");
		((RadioButton) findViewById(R.id.q26Dontknow)).setText("99 Rvwbbv");
		((TextView) findViewById(R.id.textView7))
				.setText("27. cÖkœ Kiyb: AbyMªn K‡i Avcbvi ev”Pv (ev”Pvi bvg) †h K…wgi Jla MÖnY K‡iwQj, Zvi bvg ¯§ib Kiyb");
		((RadioButton) findViewById(R.id.radioGroup2701))
				.setText("1.G¨vj‡ebWvRj");
		((RadioButton) findViewById(R.id.radioGroup2702))
				.setText("2.wg‡ebWvRj");
		
		((RadioButton) findViewById(R.id.radioGroup2704)).setText("99.Rvwbbv");
		((RadioButton) findViewById(R.id.radioGroup2703)).setText("3.Ab¨vb¨");
		
		

		((TextView) findViewById(R.id.textView7z))
				.setText("32. MZ 24 N›Uvq 3 evi ev Zvi †ekx cvqLvbv K‡i‡Q ?");

		((TextView) findViewById(R.id.textView8z))
				.setText("33.cÖwZw`b KZevi cvqLvbv K‡i‡Q ?");
		((TextView) findViewById(R.id.textView9z))
				.setText("34. cvwbi gZ ev big cvqLvbv");
		((TextView) findViewById(R.id.textView12z)).setText("37. AbeiZ Kvwk");
		((TextView) findViewById(R.id.textView13z))
				.setText("38. bvK eÜ nIqv / bvK w`‡q cvwb cov");
		((TextView) findViewById(R.id.textView14z))
				.setText("39. nuvcv‡bv, ey‡K †kv ‡kv kã nIqv, k¦vm wb‡Z Kó nIqv");
		((TextView) findViewById(R.id.textView15z))
				.setText("40. Kvjwkiv cov, Pvgov wQ‡j hvIqv, †K‡U hvIqv");
		((TextView) findViewById(R.id.textView19z))
				.setText("42. cÖkœ Kiyb : KZw`b Av‡M ev”Pvi bvg/ Avcbvi  wkïi Wvqwiqv  n‡qwQj?");

		((RadioButton) findViewById(R.id.radiogroup1601)).setTypeface(font);
		((RadioButton) findViewById(R.id.radiogroup1602)).setTypeface(font);
		((RadioButton) findViewById(R.id.radiogroup1603)).setTypeface(font);
		((RadioButton) findViewById(R.id.radiogroup1604)).setTypeface(font);
		((TextView) findViewById(R.id.textView16)).setTypeface(font);
		((TextView) findViewById(R.id.textView15)).setTypeface(font);
		((RadioButton) findViewById(R.id.radioGroup1901)).setTypeface(font);
		((RadioButton) findViewById(R.id.radioGroup1902)).setTypeface(font);
		((TextView) findViewById(R.id.textView4)).setTypeface(font);
		((TextView) findViewById(R.id.textView12)).setTypeface(font);
		((TextView) findViewById(R.id.textView11)).setTypeface(font);
		((TextView) findViewById(R.id.textView5)).setTypeface(font);
		((TextView) findViewById(R.id.textView10)).setTypeface(font);

		((TextView) findViewById(R.id.textView21)).setTypeface(font);
		((TextView) findViewById(R.id.textView22)).setTypeface(font);
		((RadioButton) findViewById(R.id.q26Dontknow)).setTypeface(font);

		((TextView) findViewById(R.id.textView7)).setTypeface(font);
		((RadioButton) findViewById(R.id.radioGroup2701)).setTypeface(font);
		((RadioButton) findViewById(R.id.radioGroup2702)).setTypeface(font);
		((RadioButton) findViewById(R.id.radioGroup2703)).setTypeface(font);
		((RadioButton) findViewById(R.id.radioGroup2704)).setTypeface(font);

		((TextView) findViewById(R.id.textView7z)).setTypeface(font);

		((TextView) findViewById(R.id.textView8z)).setTypeface(font);
		((TextView) findViewById(R.id.textView9z)).setTypeface(font);
		((TextView) findViewById(R.id.textView12z)).setTypeface(font);
		((TextView) findViewById(R.id.textView13z)).setTypeface(font);
		((TextView) findViewById(R.id.textView14z)).setTypeface(font);
		((TextView) findViewById(R.id.textView15z)).setTypeface(font);
		((TextView) findViewById(R.id.textView19z)).setTypeface(font);

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
			/*
			 * progressDialog = ProgressDialog.show(con, "Wait",
			 * "Please wait while Loading");
			 * 
			 * new Thread() { public void run() { Looper.prepare();
			 */
			// datasaved = save();
			/*
			 * Message ms = new Message(); ms.what = UPDATEDONE;
			 * handler.sendMessage(ms); Looper.loop();
			 * 
			 * } }.start();
			 */return true;

		case R.id.menu_English:
			/*
			 * new AlertDialog.Builder(con) .setTitle("Confirm") .setMessage(
			 * "Are you sure you want to exit? (Make sure you've saved)")
			 * .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			 * public void onClick(DialogInterface dialog, int whichButton) {
			 * resetSomeData(); finish();
			 * 
			 * 
			 * } }) .setNegativeButton("No", new
			 * DialogInterface.OnClickListener() { public void
			 * onClick(DialogInterface dialog, int whichButton) {
			 * dialog.dismiss(); } })
			 * 
			 * .setCancelable(false).show();
			 */
			CreateEnglishUI();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}

	}

	private void CreateEnglishUI() {
		setContentView(R.layout.tabwithsample);
		setTitle("Child Identification :: Data ID: "
				+ CommonStaticClass.dataId.toString() + " Child No :"
				+ String.valueOf(CommonStaticClass.currentChildrenCount));

		loadAllUI();
		loadSymptomView();
		SetNextButtonToDoneButtonInKeyBoard(((EditText) findViewById(R.id.q13)));
		// disableall();

		btnSave = (Button) findViewById(R.id.btnbDone);

		btnSave.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				progressDialog = ProgressDialog.show(con, "Wait",
						"Please wait while Saving");

				new Thread() {
					public void run() {
						Looper.prepare();
						datasaved = SaveData();
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		con = this;
		CreateEnglishUI();

	}

	private boolean datasaved = false;

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
					resetEveryValue();
					datasaved = false;
					setTitle("Child Identification :: Data ID: "
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

	private boolean SaveData() {
		
		
		if (!ValidActivity()) {
			CommonStaticClass.showMyAlert(con, "Message",
					"Fill the required field");
			return false;
		}
		if (!ValidateSymptom()) {
			CommonStaticClass.showMyAlert(con, "Message",
					"Fill the required field in symptom section");
			return false;
		}

		String entryDate = "dd-mmm-yy";
		Date d = new Date(System.currentTimeMillis());
		entryDate = "dd-mmm-yy";
		entryDate = d.toLocaleString();

		// insert data
		childNo = CommonStaticClass.currentChildrenCount + "";

		q12 = q12et.getText().toString();
		q13 = q13et.getText().toString();
		q15 = q15et.getText().toString();
		q17 = q17et.getText().toString();

		if (radiogroup18.indexOfChild(radiogroup18.findViewById(radiogroup18
				.getCheckedRadioButtonId())) != -1) {

			q18 = String.valueOf(radiogroup18.indexOfChild(radiogroup18
					.findViewById(radiogroup18.getCheckedRadioButtonId())));

			q18_days = "-1";
			q18_months = "-1";

			if (q18.equalsIgnoreCase("1")) {
				q18 = "99";

			}

			else {
				q18 = "88";
			}
		} else {
			q18_days = q18dayet.getText().toString();
			q18_months = q18monthset.getText().toString();
			q18 = "-1";
		}

		// radioGroup19 = (RadioGroup) findViewById(R.id.radioGroup19);
		if (radioGroup19 != null) {

			if (radioGroup19.indexOfChild(radioGroup19
					.findViewById(radioGroup19.getCheckedRadioButtonId())) != -1) {

				// if (radioGroup19.getCheckedRadioButtonId() != -1) {

				q19 = String.valueOf(radioGroup19.indexOfChild(radioGroup19
						.findViewById(radioGroup19.getCheckedRadioButtonId())));
				q19_no_of_time = "";

				if (q19.equalsIgnoreCase("1")) {
					q19 = "99";

				}

				else {
					q19 = "88";
				}
			} else {
				q19_no_of_time = q19et.getText().toString();
				q19 = "-1";
			}
		}

		/*
		 * q20_time1 = String.valueOf(radioGroup20time1
		 * .indexOfChild(findViewById(radioGroup20time1
		 * .getCheckedRadioButtonId())) + 1);
		 */

		if (radioG20time1 != null) {

			if (radioG20time1.indexOfChild(findViewById(radioG20time1
					.getCheckedRadioButtonId())) != -1) {

				q20_time1 = String.valueOf(radioG20time1
						.indexOfChild(findViewById(radioG20time1
								.getCheckedRadioButtonId())) + 1);

				if (radioG20time1.indexOfChild(findViewById(radioG20time1
						.getCheckedRadioButtonId())) == 13) {
					q20_time1 = "99";

				}

			} else {
				q20_time1 = "-1";
			}
		}

		/*
		 * q20_time2 = String.valueOf(radioGroup20time2
		 * .indexOfChild(findViewById(radioGroup20time2
		 * .getCheckedRadioButtonId())) + 1);
		 */

		if (radioG20time2 != null) {

			if (radioG20time2.indexOfChild(findViewById(radioG20time2
					.getCheckedRadioButtonId())) != -1) {

				q20_time2 = String.valueOf(radioG20time2
						.indexOfChild(findViewById(radioG20time2
								.getCheckedRadioButtonId())) + 1);

				if (radioG20time2.indexOfChild(findViewById(radioG20time2
						.getCheckedRadioButtonId())) == 13) {
					q20_time2 = "99";

				}

			} else {
				q20_time2 = "-1";
			}
		}

		/*
		 * q20_time3 = String.valueOf(radioGroup20time3
		 * .indexOfChild(findViewById(radioGroup20time3
		 * .getCheckedRadioButtonId())) + 1);
		 */

		if (radioG20time3 != null) {

			if (radioG20time3.indexOfChild(findViewById(radioG20time3
					.getCheckedRadioButtonId())) != -1) {

				q20_time3 = String.valueOf(radioG20time3
						.indexOfChild(findViewById(radioG20time3
								.getCheckedRadioButtonId())) + 1);

				if (radioG20time3.indexOfChild(findViewById(radioG20time3
						.getCheckedRadioButtonId())) == 13) {
					q20_time3 = "99";

				}

			} else {
				q20_time3 = "-1";
			}
		}

		/*
		 * q20_time4 = String.valueOf(radioGroup20time4
		 * .indexOfChild(findViewById(radioGroup20time4
		 * .getCheckedRadioButtonId())) + 1);
		 */

		if (radioG20time4 != null) {

			if (radioG20time4.indexOfChild(findViewById(radioG20time4
					.getCheckedRadioButtonId())) != -1) {

				q20_time4 = String.valueOf(radioG20time4
						.indexOfChild(findViewById(radioG20time4
								.getCheckedRadioButtonId())) + 1);

				if (radioG20time4.indexOfChild(findViewById(radioG20time4
						.getCheckedRadioButtonId())) == 13) {
					q20_time4 = "99";

				}

			} else {
				q20_time4 = "-1";
			}
		}

		/*
		 * q20_time5 = String.valueOf(radioGroup20time5
		 * .indexOfChild(findViewById(radioGroup20time5
		 * .getCheckedRadioButtonId())) + 1);
		 */

		if (radioG20time5 != null) {

			if (radioG20time5.indexOfChild(findViewById(radioG20time5
					.getCheckedRadioButtonId())) != -1) {

				q20_time5 = String.valueOf(radioG20time5
						.indexOfChild(findViewById(radioG20time5
								.getCheckedRadioButtonId())) + 1);

				if (radioG20time5.indexOfChild(findViewById(radioG20time5
						.getCheckedRadioButtonId())) == 13) {
					q20_time5 = "99";

				}

			} else {
				q20_time5 = "-1";
			}
		}

		q20_other_time1 = q20_other_time1et.getText().toString();
		q20_other_time2 = q20_other_time2et.getText().toString();
		q20_other_time3 = q20_other_time3et.getText().toString();
		q20_other_time4 = q20_other_time4et.getText().toString();
		q20_other_time5 = q20_other_time5et.getText().toString();

		// No. of Days for 21
		q21_days_time1 = q21ettime1et.getText().toString();
		q21_days_time2 = q21ettime2et.getText().toString();
		q21_days_time3 = q21ettime3et.getText().toString();
		q21_days_time4 = q21ettime4et.getText().toString();
		q21_days_time5 = q21ettime5et.getText().toString();

		q14 = String.valueOf(rdogroup14.indexOfChild(findViewById(rdogroup14
				.getCheckedRadioButtonId())) + 1);

		/*
		 * q16 = String.valueOf(rdogroup16 .indexOfChild(findViewById(rdogroup16
		 * .getCheckedRadioButtonId())) + 1);
		 */

		if (rdogroup16 != null) {

			if (rdogroup16.indexOfChild(findViewById(rdogroup16
					.getCheckedRadioButtonId())) != -1) {

				q16 = String.valueOf(rdogroup16
						.indexOfChild(findViewById(rdogroup16
								.getCheckedRadioButtonId())) + 1);

				if (rdogroup16.indexOfChild(findViewById(rdogroup16
						.getCheckedRadioButtonId())) == 3) {
					q16 = "99";

				}

			}
		}

		/*
		 * q22 = String.valueOf(radioGroup22
		 * .indexOfChild(findViewById(radioGroup22 .getCheckedRadioButtonId()))
		 * + 1);
		 */

		if (radioGroup22 != null) {

			if (radioGroup22.indexOfChild(findViewById(radioGroup22
					.getCheckedRadioButtonId())) != -1) {

				q22 = String.valueOf(radioGroup22
						.indexOfChild(findViewById(radioGroup22
								.getCheckedRadioButtonId())) + 1);

				if (radioGroup22.indexOfChild(findViewById(radioGroup22
						.getCheckedRadioButtonId())) == 4) {
					q22 = "99";

				}

			}
		}

		if (radioGroup23 != null) {

			if (radioGroup23.indexOfChild(findViewById(radioGroup23
					.getCheckedRadioButtonId())) != -1) {

				q23 = String.valueOf(radioGroup23
						.indexOfChild(findViewById(radioGroup23
								.getCheckedRadioButtonId())) + 1);

				if (radioGroup23.indexOfChild(findViewById(radioGroup23
						.getCheckedRadioButtonId())) == 4) {
					q23 = "99";

				}

			}
		}

		q24 = String.valueOf(radioGroup24
				.indexOfChild(findViewById(radioGroup24
						.getCheckedRadioButtonId())));

		if (radioGroup24 != null) {

			if (radioGroup24.indexOfChild(findViewById(radioGroup24
					.getCheckedRadioButtonId())) != -1) {

				q24 = String.valueOf(radioGroup24
						.indexOfChild(findViewById(radioGroup24
								.getCheckedRadioButtonId())) + 1);

				if (radioGroup24.indexOfChild(findViewById(radioGroup24
						.getCheckedRadioButtonId())) == 3) {
					q24 = "99";

				}

			}
		}

		// radioGroup25 = (RadioGroup) findViewById(R.id.radioGroup25);

		if (ln25to27.getVisibility() != View.GONE) {
			if (radioGroup25 != null) {

				if (radioGroup25.indexOfChild(findViewById(radioGroup25
						.getCheckedRadioButtonId())) != -1) {

					q25 = String.valueOf(radioGroup25
							.indexOfChild(findViewById(radioGroup25
									.getCheckedRadioButtonId())) + 1);

					if (radioGroup25.indexOfChild(findViewById(radioGroup25
							.getCheckedRadioButtonId())) == 4) {
						q25 = "99";

					}

				}
			}

			if (radioGroup27 != null) {

				if (radioGroup27.indexOfChild(findViewById(radioGroup27
						.getCheckedRadioButtonId())) != -1) {

					q27 = String.valueOf(radioGroup27
							.indexOfChild(findViewById(radioGroup27
									.getCheckedRadioButtonId())) + 1);
					q27_other = "";

					if (radioGroup27.indexOfChild(findViewById(radioGroup27
							.getCheckedRadioButtonId())) == 2) {
						q27 = "99";

					}
					else if (radioGroup27.indexOfChild(findViewById(radioGroup27
							.getCheckedRadioButtonId())) == 3) {
						q27 = "3";
						q27_other = q27_otheret.getText().toString();	
					}

				} else {
					q27_other = q27_otheret.getText().toString();
					q27 = "-1";
				}
			}

			if (rdoq26Dontknow != null) {
				if (!rdoq26Dontknow.isChecked()) {

					q26_days = q26dayset.getText().toString();
					q26_months = q26monthset.getText().toString();
					q26 = "-1";
				} else {
					q26_days = "";
					q26_months = "";
					q26 = "99";
				}
			}

		} else {
			q25 = "-1";
			q26_days = "";
			q26_months = "";
			q26 = "-1";
			q27_other = "";
			q27 = "-1";
		}

		// radioGroup28 = (RadioGroup) findViewById(R.id.radioGroup28);
		if (radioGroup28 != null) {

			if (radioGroup28.indexOfChild(findViewById(radioGroup28
					.getCheckedRadioButtonId())) != -1) {

				q28 = String.valueOf(radioGroup28
						.indexOfChild(findViewById(radioGroup28
								.getCheckedRadioButtonId())) + 1);

				if (radioGroup28.indexOfChild(findViewById(radioGroup28
						.getCheckedRadioButtonId())) == 2) {
					q28 = "99";

				}

			}
		}

		// radioGroup29 = (RadioGroup) findViewById(R.id.radioGroup29);
		if (radioGroup29 != null) {

			if (radioGroup29.indexOfChild(findViewById(radioGroup29
					.getCheckedRadioButtonId())) != -1) {

				q29 = String.valueOf(radioGroup29
						.indexOfChild(findViewById(radioGroup29
								.getCheckedRadioButtonId())) + 1);

				if (radioGroup29.indexOfChild(findViewById(radioGroup29
						.getCheckedRadioButtonId())) == 2) {
					q29 = "99";

				}

			}
		}

		// other for q21

		if ((RadioButton) findViewById(R.id.radioGroup2101Dontknowtime1) != null) {
			if (((RadioButton) findViewById(R.id.radioGroup2101Dontknowtime1))
					.isChecked()) {
				q21_Dontknow_time1 = "99";
			} else {
				q21_Dontknow_time1 = "-1";
			}
		}

		if ((RadioButton) findViewById(R.id.radioGroup2101Dontknowtime2) != null) {
			if (((RadioButton) findViewById(R.id.radioGroup2101Dontknowtime2))
					.isChecked()) {
				q21_Dontknow_time2 = "99";
			} else {
				q21_Dontknow_time2 = "-1";
			}
		}

		if ((RadioButton) findViewById(R.id.radioGroup2101Dontknowtime3) != null) {
			if (((RadioButton) findViewById(R.id.radioGroup2101Dontknowtime3))
					.isChecked()) {
				q21_Dontknow_time3 = "99";
			} else {
				q21_Dontknow_time3 = "-1";
			}
		}

		if ((RadioButton) findViewById(R.id.radioGroup2101Dontknowtime4) != null) {
			if (((RadioButton) findViewById(R.id.radioGroup2101Dontknowtime4))
					.isChecked()) {
				q21_Dontknow_time4 = "99";
			} else {
				q21_Dontknow_time4 = "-1";
			}
		}

		if ((RadioButton) findViewById(R.id.radioGroup2101Dontknowtime5) != null) {
			if (((RadioButton) findViewById(R.id.radioGroup2101Dontknowtime5))
					.isChecked()) {
				q21_Dontknow_time5 = "99";
			} else {
				q21_Dontknow_time5 = "-1";
			}
		}

		// Symptom
		// radioGroup30A = (RadioGroup) findViewById(R.id.radioGroup30A);
		if (radioGroup30A != null) {

			if (radioGroup30A.indexOfChild(radioGroup30A
					.findViewById(radioGroup30A.getCheckedRadioButtonId())) != -1) {

				q30A = String.valueOf(radioGroup30A
						.indexOfChild(findViewById(radioGroup30A
								.getCheckedRadioButtonId())) + 1);

				if (radioGroup30A.indexOfChild(findViewById(radioGroup30A
						.getCheckedRadioButtonId())) == 2) {
					q30A = "99";

				}

			} else {
				q30A = "-1";
			}

		}

		// radioGroup30B = (RadioGroup) findViewById(R.id.radioGroup30B);
		if (radioGroup30B != null) {

			if (radioGroup30B.indexOfChild(radioGroup30B
					.findViewById(radioGroup30B.getCheckedRadioButtonId())) != -1) {

				q30B = String
						.valueOf(radioGroup30B.indexOfChild(radioGroup30B
								.findViewById(radioGroup30B
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup30B.indexOfChild(radioGroup30B
						.findViewById(radioGroup30B.getCheckedRadioButtonId())) == 2) {
					q30B = "99";

				}

			}
		}

		// radioGroup30C = (RadioGroup) findViewById(R.id.radioGroup30C);
		if (radioGroup30C != null) {

			if (radioGroup30C.indexOfChild(radioGroup30C
					.findViewById(radioGroup30C.getCheckedRadioButtonId())) != -1) {

				q30C = String
						.valueOf(radioGroup30C.indexOfChild(radioGroup30C
								.findViewById(radioGroup30C
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup30C.indexOfChild(radioGroup30C
						.findViewById(radioGroup30C.getCheckedRadioButtonId())) == 2) {
					q30C = "99";

				}

			}
		}

		// radioGroup30D = (RadioGroup) findViewById(R.id.radioGroup30D);
		if (radioGroup30D != null) {

			if (radioGroup30D.indexOfChild(radioGroup30D
					.findViewById(radioGroup30D.getCheckedRadioButtonId())) != -1) {

				q30D = String
						.valueOf(radioGroup30D.indexOfChild(radioGroup30D
								.findViewById(radioGroup30D
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup30D.indexOfChild(radioGroup30D
						.findViewById(radioGroup30D.getCheckedRadioButtonId())) == 2) {
					q30D = "99";

				}

			}
		}
		if (radioGroup30E.getVisibility() != View.VISIBLE) {
			q30E = "-1";
		}

		else {
			if (radioGroup30E != null) {

				if (radioGroup30E.getVisibility() != View.VISIBLE) {
					q30E = "-1";
				}
				if (radioGroup30E.indexOfChild(radioGroup30E
						.findViewById(radioGroup30E.getCheckedRadioButtonId())) != -1) {

					q30E = String.valueOf(radioGroup30E
							.indexOfChild(radioGroup30E
									.findViewById(radioGroup30E
											.getCheckedRadioButtonId())) + 1);

					if (radioGroup30E.indexOfChild(radioGroup30E
							.findViewById(radioGroup30E
									.getCheckedRadioButtonId())) == 2) {
						q30E = "99";

					}

				}
			}
		}
		// 31

		if (radioGroup31A != null) {

			if (radioGroup31A.indexOfChild(radioGroup31A
					.findViewById(radioGroup31A.getCheckedRadioButtonId())) != -1) {

				q31A = String
						.valueOf(radioGroup31A.indexOfChild(radioGroup31A
								.findViewById(radioGroup31A
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup31A.indexOfChild(radioGroup31A
						.findViewById(radioGroup31A.getCheckedRadioButtonId())) == 2) {
					q31A = "99";

				}

			}
		}

		if (radioGroup31B != null) {

			if (radioGroup31B.indexOfChild(radioGroup31B
					.findViewById(radioGroup31B.getCheckedRadioButtonId())) != -1) {

				q31B = String
						.valueOf(radioGroup31B.indexOfChild(radioGroup31B
								.findViewById(radioGroup31B
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup31B.indexOfChild(radioGroup31B
						.findViewById(radioGroup31B.getCheckedRadioButtonId())) == 2) {
					q31B = "99";

				}

			}
		}

		// radioGroup31C = (RadioGroup) findViewById(R.id.radioGroup31C);
		if (radioGroup31C != null) {

			if (radioGroup31C.indexOfChild(radioGroup31C
					.findViewById(radioGroup31C.getCheckedRadioButtonId())) != -1) {

				q31C = String
						.valueOf(radioGroup31C.indexOfChild(radioGroup31C
								.findViewById(radioGroup31C
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup31C.indexOfChild(radioGroup31C
						.findViewById(radioGroup31C.getCheckedRadioButtonId())) == 2) {
					q31C = "99";

				}

			}
		}

		if (radioGroup31D != null) {

			if (radioGroup31D.indexOfChild(radioGroup31D
					.findViewById(radioGroup31D.getCheckedRadioButtonId())) != -1) {

				q31D = String
						.valueOf(radioGroup31D.indexOfChild(radioGroup31D
								.findViewById(radioGroup31D
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup31D.indexOfChild(radioGroup31D
						.findViewById(radioGroup31D.getCheckedRadioButtonId())) == 2) {
					q31D = "99";

				}

			}
		}

		if (radioGroup31E.getVisibility() != View.VISIBLE) {
			q31E = "-1";
		}

		else {

			if (radioGroup31E != null) {

				if (radioGroup31E.indexOfChild(radioGroup31E
						.findViewById(radioGroup31E.getCheckedRadioButtonId())) != -1) {

					q31E = String.valueOf(radioGroup31E
							.indexOfChild(radioGroup31E
									.findViewById(radioGroup31E
											.getCheckedRadioButtonId())) + 1);

					if (radioGroup31E.indexOfChild(radioGroup31E
							.findViewById(radioGroup31D
									.getCheckedRadioButtonId())) == 2) {
						q31E = "99";

					}

				}
			}
		}
		// 32
		if (radioGroup32A != null) {

			if (radioGroup32A.indexOfChild(radioGroup32A
					.findViewById(radioGroup32A.getCheckedRadioButtonId())) != -1) {

				q32A = String
						.valueOf(radioGroup32A.indexOfChild(radioGroup32A
								.findViewById(radioGroup32A
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup32A.indexOfChild(radioGroup32A
						.findViewById(radioGroup32A.getCheckedRadioButtonId())) == 2) {
					q32A = "99";

				}

			}
		}

		if (radioGroup32B != null) {

			if (radioGroup32B.indexOfChild(radioGroup32B
					.findViewById(radioGroup32B.getCheckedRadioButtonId())) != -1) {

				q32B = String
						.valueOf(radioGroup32B.indexOfChild(radioGroup32B
								.findViewById(radioGroup32B
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup32B.indexOfChild(radioGroup32B
						.findViewById(radioGroup32B.getCheckedRadioButtonId())) == 2) {
					q32B = "99";

				}

			}
		}

		// radioGroup32C = (RadioGroup) findViewById(R.id.radioGroup32C);
		if (radioGroup32C != null) {

			if (radioGroup32C.indexOfChild(radioGroup32C
					.findViewById(radioGroup32C.getCheckedRadioButtonId())) != -1) {

				q32C = String
						.valueOf(radioGroup32C.indexOfChild(radioGroup32C
								.findViewById(radioGroup32C
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup32C.indexOfChild(radioGroup32C
						.findViewById(radioGroup32C.getCheckedRadioButtonId())) == 2) {
					q32C = "99";

				}

			}
		}

		if (radioGroup32D != null) {

			if (radioGroup32D.indexOfChild(radioGroup32D
					.findViewById(radioGroup32D.getCheckedRadioButtonId())) != -1) {

				q32D = String
						.valueOf(radioGroup32D.indexOfChild(radioGroup32D
								.findViewById(radioGroup32D
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup32D.indexOfChild(radioGroup32D
						.findViewById(radioGroup32D.getCheckedRadioButtonId())) == 2) {
					q32D = "99";

				}

			}
		}
		if (radioGroup32E.getVisibility() != View.VISIBLE) {
			q32E = "-1";
		}

		else {

			if (radioGroup32E != null) {

				if (radioGroup32E.indexOfChild(radioGroup32E
						.findViewById(radioGroup32E.getCheckedRadioButtonId())) != -1) {

					q32E = String.valueOf(radioGroup32E
							.indexOfChild(radioGroup32E
									.findViewById(radioGroup32E
											.getCheckedRadioButtonId())) + 1);

					if (radioGroup32E.indexOfChild(radioGroup32E
							.findViewById(radioGroup32E
									.getCheckedRadioButtonId())) == 2) {
						q32E = "99";

					}

				}
			}
		}
		// 33
		

		if (findViewById(R.id.q33Aet).getVisibility() == View.VISIBLE) {

			q33A = ((EditText) findViewById(R.id.q33Aet)).getText().toString();

			if (q33A.length() > 0) {
				if (Integer.parseInt(q33A) > 50) {
					if (Integer.parseInt(q33A) == 99) {

					} else {
						CommonStaticClass.showMyAlert(con, "Message",
								"Invalid answer for question no. 33");
						return false;
					}
				}
			} else {
				CommonStaticClass.showMyAlert(con, "Message",
						"Put value for question no. 33");
				return false;
			}
		}

		if (findViewById(R.id.q33Bet).getVisibility() == View.VISIBLE) {

			q33B = ((EditText) findViewById(R.id.q33Bet)).getText().toString();

			if (q33B.length() > 0) {

				if (Integer.parseInt(q33B) > 50) {
					if (Integer.parseInt(q33A) == 99) {

					} else {
						CommonStaticClass.showMyAlert(con, "Message",
								"Invalid value for question no. 33");
						return false;
					}
				}
			} else {
				CommonStaticClass.showMyAlert(con, "Message",
						"Put value for question no. 33");
				return false;
			}
		}
		if (findViewById(R.id.q33Cet).getVisibility() == View.VISIBLE) {

			q33C = ((EditText) findViewById(R.id.q33Cet)).getText().toString();
			if (q33C.length() > 0) {

				if (Integer.parseInt(q33C) > 50) {
					if (Integer.parseInt(q33A) == 99) {

					} else {
						CommonStaticClass.showMyAlert(con, "Message",
								"Invalid value for question no. 33");
						return false;
					}
				}
			} else {
				CommonStaticClass.showMyAlert(con, "Message",
						"Put value for question no. 33");
				return false;
			}
		}
		

		// 34
		if (radioGroup34A != null) {

			if (radioGroup34A.indexOfChild(radioGroup34A
					.findViewById(radioGroup34A.getCheckedRadioButtonId())) != -1) {

				q34A = String
						.valueOf(radioGroup34A.indexOfChild(radioGroup34A
								.findViewById(radioGroup34A
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup34A.indexOfChild(radioGroup34A
						.findViewById(radioGroup34A.getCheckedRadioButtonId())) == 2) {
					q34A = "99";

				}

			}
		}

		if (radioGroup34B != null) {

			if (radioGroup34B.indexOfChild(radioGroup34B
					.findViewById(radioGroup34B.getCheckedRadioButtonId())) != -1) {

				q34B = String
						.valueOf(radioGroup34B.indexOfChild(radioGroup34B
								.findViewById(radioGroup34B
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup34B.indexOfChild(radioGroup34B
						.findViewById(radioGroup34B.getCheckedRadioButtonId())) == 2) {
					q34B = "99";

				}

			}
		}

		// radioGroup34C = (RadioGroup) findViewById(R.id.radioGroup34C);
		if (radioGroup34C != null) {

			if (radioGroup34C.indexOfChild(radioGroup34C
					.findViewById(radioGroup34C.getCheckedRadioButtonId())) != -1) {

				q34C = String
						.valueOf(radioGroup34C.indexOfChild(radioGroup34C
								.findViewById(radioGroup34C
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup34C.indexOfChild(radioGroup34C
						.findViewById(radioGroup34C.getCheckedRadioButtonId())) == 2) {
					q34C = "99";

				}

			}
		}

		if (radioGroup34D != null) {

			if (radioGroup34D.indexOfChild(radioGroup34D
					.findViewById(radioGroup34D.getCheckedRadioButtonId())) != -1) {

				q34D = String
						.valueOf(radioGroup34D.indexOfChild(radioGroup34D
								.findViewById(radioGroup34D
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup34D.indexOfChild(radioGroup34D
						.findViewById(radioGroup34D.getCheckedRadioButtonId())) == 2) {
					q34D = "99";

				}

			}
		}

		if (radioGroup34E.getVisibility() != View.VISIBLE) {
			q34E = "-1";
		}

		else {
			if (radioGroup34E != null) {

				if (radioGroup34E.indexOfChild(radioGroup34E
						.findViewById(radioGroup34E.getCheckedRadioButtonId())) != -1) {

					q34E = String.valueOf(radioGroup34E
							.indexOfChild(radioGroup34E
									.findViewById(radioGroup34E
											.getCheckedRadioButtonId())) + 1);

					if (radioGroup34E.indexOfChild(radioGroup34E
							.findViewById(radioGroup34E
									.getCheckedRadioButtonId())) == 2) {
						q34E = "99";

					}

				}
			}
		}
		// 35
		if (radioGroup35A != null) {

			if (radioGroup35A.indexOfChild(radioGroup35A
					.findViewById(radioGroup35A.getCheckedRadioButtonId())) != -1) {

				q35A = String
						.valueOf(radioGroup35A.indexOfChild(radioGroup35A
								.findViewById(radioGroup35A
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup35A.indexOfChild(radioGroup35A
						.findViewById(radioGroup35A.getCheckedRadioButtonId())) == 2) {
					q35A = "99";

				}

			}
		}

		if (radioGroup35B != null) {

			if (radioGroup35B.indexOfChild(radioGroup35B
					.findViewById(radioGroup35B.getCheckedRadioButtonId())) != -1) {

				q35B = String
						.valueOf(radioGroup35B.indexOfChild(radioGroup35B
								.findViewById(radioGroup35B
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup35B.indexOfChild(radioGroup35B
						.findViewById(radioGroup35B.getCheckedRadioButtonId())) == 2) {
					q35B = "99";

				}

			}
		}

		// radioGroup35C = (RadioGroup) findViewById(R.id.radioGroup35C);
		if (radioGroup35C != null) {

			if (radioGroup35C.indexOfChild(radioGroup35C
					.findViewById(radioGroup35C.getCheckedRadioButtonId())) != -1) {

				q35C = String
						.valueOf(radioGroup35C.indexOfChild(radioGroup35C
								.findViewById(radioGroup35C
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup35C.indexOfChild(radioGroup35C
						.findViewById(radioGroup35C.getCheckedRadioButtonId())) == 2) {
					q35C = "99";

				}

			}
		}

		if (radioGroup35D != null) {

			if (radioGroup35D.indexOfChild(radioGroup35D
					.findViewById(radioGroup35D.getCheckedRadioButtonId())) != -1) {

				q35D = String
						.valueOf(radioGroup35D.indexOfChild(radioGroup35D
								.findViewById(radioGroup35D
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup35D.indexOfChild(radioGroup35D
						.findViewById(radioGroup35D.getCheckedRadioButtonId())) == 2) {
					q35D = "99";

				}

			}
		}

		if (radioGroup35E.getVisibility() != View.VISIBLE) {
			q35E = "-1";
		}

		else {
			if (radioGroup35E != null) {

				if (radioGroup35E.indexOfChild(radioGroup35E
						.findViewById(radioGroup35E.getCheckedRadioButtonId())) != -1) {

					q35E = String.valueOf(radioGroup35E
							.indexOfChild(radioGroup35E
									.findViewById(radioGroup35E
											.getCheckedRadioButtonId())) + 1);

					if (radioGroup35E.indexOfChild(radioGroup35E
							.findViewById(radioGroup35E
									.getCheckedRadioButtonId())) == 2) {
						q35E = "99";

					}

				}
			}
		}
		// 36
		if (radioGroup36A != null) {

			if (radioGroup36A.indexOfChild(radioGroup36A
					.findViewById(radioGroup36A.getCheckedRadioButtonId())) != -1) {

				q36A = String
						.valueOf(radioGroup36A.indexOfChild(radioGroup36A
								.findViewById(radioGroup36A
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup36A.indexOfChild(radioGroup36A
						.findViewById(radioGroup36A.getCheckedRadioButtonId())) == 2) {
					q36A = "99";

				}

			}
		}

		if (radioGroup36B != null) {

			if (radioGroup36B.indexOfChild(radioGroup36B
					.findViewById(radioGroup36B.getCheckedRadioButtonId())) != -1) {

				q36B = String
						.valueOf(radioGroup36B.indexOfChild(radioGroup36B
								.findViewById(radioGroup36B
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup36B.indexOfChild(radioGroup36B
						.findViewById(radioGroup36B.getCheckedRadioButtonId())) == 2) {
					q36B = "99";

				}

			}
		}

		// radioGroup36C = (RadioGroup) findViewById(R.id.radioGroup36C);
		if (radioGroup36C != null) {

			if (radioGroup36C.indexOfChild(radioGroup36C
					.findViewById(radioGroup36C.getCheckedRadioButtonId())) != -1) {

				q36C = String
						.valueOf(radioGroup36C.indexOfChild(radioGroup36C
								.findViewById(radioGroup36C
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup36C.indexOfChild(radioGroup36C
						.findViewById(radioGroup36C.getCheckedRadioButtonId())) == 2) {
					q36C = "99";

				}

			}
		}

		if (radioGroup36D != null) {

			if (radioGroup36D.indexOfChild(radioGroup36D
					.findViewById(radioGroup36D.getCheckedRadioButtonId())) != -1) {

				q36D = String
						.valueOf(radioGroup36D.indexOfChild(radioGroup36D
								.findViewById(radioGroup36D
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup36D.indexOfChild(radioGroup36D
						.findViewById(radioGroup36D.getCheckedRadioButtonId())) == 2) {
					q36D = "99";

				}

			}
		}

		if (radioGroup36E.getVisibility() != View.VISIBLE) {
			q36E = "-1";
		}

		else {
			if (radioGroup36E != null) {

				if (radioGroup36E.indexOfChild(radioGroup36E
						.findViewById(radioGroup36E.getCheckedRadioButtonId())) != -1) {

					q36E = String.valueOf(radioGroup36E
							.indexOfChild(radioGroup36E
									.findViewById(radioGroup36E
											.getCheckedRadioButtonId())) + 1);

					if (radioGroup36E.indexOfChild(radioGroup36E
							.findViewById(radioGroup36E
									.getCheckedRadioButtonId())) == 2) {
						q36E = "99";

					}

				}
			}
		}
		// 37
		if (radioGroup37A != null) {

			if (radioGroup37A.indexOfChild(radioGroup37A
					.findViewById(radioGroup37A.getCheckedRadioButtonId())) != -1) {

				q37A = String
						.valueOf(radioGroup37A.indexOfChild(radioGroup37A
								.findViewById(radioGroup37A
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup37A.indexOfChild(radioGroup37A
						.findViewById(radioGroup37A.getCheckedRadioButtonId())) == 2) {
					q37A = "99";

				}

			}
		}

		if (radioGroup37B != null) {

			if (radioGroup37B.indexOfChild(radioGroup37B
					.findViewById(radioGroup37B.getCheckedRadioButtonId())) != -1) {

				q37B = String
						.valueOf(radioGroup37B.indexOfChild(radioGroup37B
								.findViewById(radioGroup37B
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup37B.indexOfChild(radioGroup37B
						.findViewById(radioGroup37B.getCheckedRadioButtonId())) == 2) {
					q37B = "99";

				}

			}
		}

		// radioGroup37C = (RadioGroup) findViewById(R.id.radioGroup37C);
		if (radioGroup37C != null) {

			if (radioGroup37C.indexOfChild(radioGroup37C
					.findViewById(radioGroup37C.getCheckedRadioButtonId())) != -1) {

				q37C = String
						.valueOf(radioGroup37C.indexOfChild(radioGroup37C
								.findViewById(radioGroup37C
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup37C.indexOfChild(radioGroup37C
						.findViewById(radioGroup37C.getCheckedRadioButtonId())) == 2) {
					q37C = "99";

				}

			}
		}

		if (radioGroup37D != null) {

			if (radioGroup37D.indexOfChild(radioGroup37D
					.findViewById(radioGroup37D.getCheckedRadioButtonId())) != -1) {

				q37D = String
						.valueOf(radioGroup37D.indexOfChild(radioGroup37D
								.findViewById(radioGroup37D
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup37D.indexOfChild(radioGroup37D
						.findViewById(radioGroup37D.getCheckedRadioButtonId())) == 2) {
					q37D = "99";

				}

			}
		}

		if (radioGroup37E.getVisibility() != View.VISIBLE) {
			q37E = "-1";
		}

		else {
			if (radioGroup37E != null) {

				if (radioGroup37E.indexOfChild(radioGroup37E
						.findViewById(radioGroup37E.getCheckedRadioButtonId())) != -1) {

					q37E = String.valueOf(radioGroup37E
							.indexOfChild(radioGroup37E
									.findViewById(radioGroup37E
											.getCheckedRadioButtonId())) + 1);

					if (radioGroup37E.indexOfChild(radioGroup37E
							.findViewById(radioGroup37E
									.getCheckedRadioButtonId())) == 2) {
						q37E = "99";

					}

				}
			}
		}
		// 38
		if (radioGroup38A != null) {

			if (radioGroup38A.indexOfChild(radioGroup38A
					.findViewById(radioGroup38A.getCheckedRadioButtonId())) != -1) {

				q38A = String
						.valueOf(radioGroup38A.indexOfChild(radioGroup38A
								.findViewById(radioGroup38A
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup38A.indexOfChild(radioGroup38A
						.findViewById(radioGroup38A.getCheckedRadioButtonId())) == 2) {
					q38A = "99";

				}

			}
		}

		if (radioGroup38B != null) {

			if (radioGroup38B.indexOfChild(radioGroup38B
					.findViewById(radioGroup38B.getCheckedRadioButtonId())) != -1) {

				q38B = String
						.valueOf(radioGroup38B.indexOfChild(radioGroup38B
								.findViewById(radioGroup38B
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup38B.indexOfChild(radioGroup38B
						.findViewById(radioGroup38B.getCheckedRadioButtonId())) == 2) {
					q38B = "99";

				}

			}
		}

		// radioGroup38C = (RadioGroup) findViewById(R.id.radioGroup38C);
		if (radioGroup38C != null) {

			if (radioGroup38C.indexOfChild(radioGroup38C
					.findViewById(radioGroup38C.getCheckedRadioButtonId())) != -1) {

				q38C = String
						.valueOf(radioGroup38C.indexOfChild(radioGroup38C
								.findViewById(radioGroup38C
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup38C.indexOfChild(radioGroup38C
						.findViewById(radioGroup38C.getCheckedRadioButtonId())) == 2) {
					q38C = "99";

				}

			}
		}

		if (radioGroup38D != null) {

			if (radioGroup38D.indexOfChild(radioGroup38D
					.findViewById(radioGroup38D.getCheckedRadioButtonId())) != -1) {

				q38D = String
						.valueOf(radioGroup38D.indexOfChild(radioGroup38D
								.findViewById(radioGroup38D
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup38D.indexOfChild(radioGroup38D
						.findViewById(radioGroup38D.getCheckedRadioButtonId())) == 2) {
					q38D = "99";

				}

			}
		}
		if (radioGroup38E.getVisibility() != View.VISIBLE) {
			q38E = "-1";
		}

		else {
			if (radioGroup38E != null) {

				if (radioGroup38E.indexOfChild(radioGroup38E
						.findViewById(radioGroup38E.getCheckedRadioButtonId())) != -1) {

					q38E = String.valueOf(radioGroup38E
							.indexOfChild(radioGroup38E
									.findViewById(radioGroup38E
											.getCheckedRadioButtonId())) + 1);

					if (radioGroup38E.indexOfChild(radioGroup38E
							.findViewById(radioGroup38E
									.getCheckedRadioButtonId())) == 2) {
						q38E = "99";

					}

				}
			}
		}
		// 39
		if (radioGroup39A != null) {

			if (radioGroup39A.indexOfChild(radioGroup39A
					.findViewById(radioGroup39A.getCheckedRadioButtonId())) != -1) {

				q39A = String
						.valueOf(radioGroup39A.indexOfChild(radioGroup39A
								.findViewById(radioGroup39A
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup39A.indexOfChild(radioGroup39A
						.findViewById(radioGroup39A.getCheckedRadioButtonId())) == 2) {
					q39A = "99";

				}

			}
		}

		if (radioGroup39B != null) {

			if (radioGroup39B.indexOfChild(radioGroup39B
					.findViewById(radioGroup39B.getCheckedRadioButtonId())) != -1) {

				q39B = String
						.valueOf(radioGroup39B.indexOfChild(radioGroup39B
								.findViewById(radioGroup39B
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup39B.indexOfChild(radioGroup39B
						.findViewById(radioGroup39B.getCheckedRadioButtonId())) == 2) {
					q39B = "99";

				}

			}
		}

		// radioGroup39C = (RadioGroup) findViewById(R.id.radioGroup39C);
		if (radioGroup39C != null) {

			if (radioGroup39C.indexOfChild(radioGroup39C
					.findViewById(radioGroup39C.getCheckedRadioButtonId())) != -1) {

				q39C = String
						.valueOf(radioGroup39C.indexOfChild(radioGroup39C
								.findViewById(radioGroup39C
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup39C.indexOfChild(radioGroup39C
						.findViewById(radioGroup39C.getCheckedRadioButtonId())) == 2) {
					q39C = "99";

				}

			}
		}

		if (radioGroup39D != null) {

			if (radioGroup39D.indexOfChild(radioGroup39D
					.findViewById(radioGroup39D.getCheckedRadioButtonId())) != -1) {

				q39D = String
						.valueOf(radioGroup39D.indexOfChild(radioGroup39D
								.findViewById(radioGroup39D
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup39D.indexOfChild(radioGroup39D
						.findViewById(radioGroup39D.getCheckedRadioButtonId())) == 2) {
					q39D = "99";

				}

			}
		}
		if (radioGroup39E.getVisibility() != View.VISIBLE) {
			q39E = "-1";
		}

		else {
			if (radioGroup39E != null) {

				if (radioGroup39E.indexOfChild(radioGroup39E
						.findViewById(radioGroup39E.getCheckedRadioButtonId())) != -1) {

					q39E = String.valueOf(radioGroup39E
							.indexOfChild(radioGroup39E
									.findViewById(radioGroup39E
											.getCheckedRadioButtonId())) + 1);

					if (radioGroup39E.indexOfChild(radioGroup39E
							.findViewById(radioGroup39E
									.getCheckedRadioButtonId())) == 2) {
						q39E = "99";

					}

				}
			}
		}
		// 40
		if (radioGroup40A != null) {

			if (radioGroup40A.indexOfChild(radioGroup40A
					.findViewById(radioGroup40A.getCheckedRadioButtonId())) != -1) {

				q40A = String
						.valueOf(radioGroup40A.indexOfChild(radioGroup40A
								.findViewById(radioGroup40A
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup40A.indexOfChild(radioGroup40A
						.findViewById(radioGroup40A.getCheckedRadioButtonId())) == 2) {
					q40A = "99";

				}

			}
		}

		if (radioGroup40B != null) {

			if (radioGroup40B.indexOfChild(radioGroup40B
					.findViewById(radioGroup40B.getCheckedRadioButtonId())) != -1) {

				q40B = String
						.valueOf(radioGroup40B.indexOfChild(radioGroup40B
								.findViewById(radioGroup40B
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup40B.indexOfChild(radioGroup40B
						.findViewById(radioGroup40B.getCheckedRadioButtonId())) == 2) {
					q40B = "99";

				}

			}
		}

		// radioGroup40C = (RadioGroup) findViewById(R.id.radioGroup40C);
		if (radioGroup40C != null) {

			if (radioGroup40C.indexOfChild(radioGroup40C
					.findViewById(radioGroup40C.getCheckedRadioButtonId())) != -1) {

				q40C = String
						.valueOf(radioGroup40C.indexOfChild(radioGroup40C
								.findViewById(radioGroup40C
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup40C.indexOfChild(radioGroup40C
						.findViewById(radioGroup40C.getCheckedRadioButtonId())) == 2) {
					q40C = "99";

				}

			}
		}

		if (radioGroup40D != null) {

			if (radioGroup40D.indexOfChild(radioGroup40D
					.findViewById(radioGroup40D.getCheckedRadioButtonId())) != -1) {

				q40D = String
						.valueOf(radioGroup40D.indexOfChild(radioGroup40D
								.findViewById(radioGroup40D
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup40D.indexOfChild(radioGroup40D
						.findViewById(radioGroup40D.getCheckedRadioButtonId())) == 2) {
					q40D = "99";

				}

			}
		}
		if (radioGroup40E.getVisibility() != View.VISIBLE) {
			q40E = "-1";
		}

		else {
			if (radioGroup40E != null) {

				if (radioGroup40E.indexOfChild(radioGroup40E
						.findViewById(radioGroup40E.getCheckedRadioButtonId())) != -1) {

					q40E = String.valueOf(radioGroup40E
							.indexOfChild(radioGroup40E
									.findViewById(radioGroup40E
											.getCheckedRadioButtonId())) + 1);

					if (radioGroup40E.indexOfChild(radioGroup40E
							.findViewById(radioGroup40E
									.getCheckedRadioButtonId())) == 2) {
						q40E = "99";

					}

				}
			}
		}
		// 41
		if (radioGroup41A != null) {

			if (radioGroup41A.indexOfChild(radioGroup41A
					.findViewById(radioGroup41A.getCheckedRadioButtonId())) != -1) {

				q41A = String
						.valueOf(radioGroup41A.indexOfChild(radioGroup41A
								.findViewById(radioGroup41A
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup41A.indexOfChild(radioGroup41A
						.findViewById(radioGroup41A.getCheckedRadioButtonId())) == 2) {
					q41A = "99";

				}

			}
		}

		if (radioGroup41B != null) {

			if (radioGroup41B.indexOfChild(radioGroup41B
					.findViewById(radioGroup41B.getCheckedRadioButtonId())) != -1) {

				q41B = String
						.valueOf(radioGroup41B.indexOfChild(radioGroup41B
								.findViewById(radioGroup41B
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup41B.indexOfChild(radioGroup41B
						.findViewById(radioGroup41B.getCheckedRadioButtonId())) == 2) {
					q41B = "99";

				}

			}
		}

		// radioGroup41C = (RadioGroup) findViewById(R.id.radioGroup41C);
		if (radioGroup41C != null) {

			if (radioGroup41C.indexOfChild(radioGroup41C
					.findViewById(radioGroup41C.getCheckedRadioButtonId())) != -1) {

				q41C = String
						.valueOf(radioGroup41C.indexOfChild(radioGroup41C
								.findViewById(radioGroup41C
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup41C.indexOfChild(radioGroup41C
						.findViewById(radioGroup41C.getCheckedRadioButtonId())) == 2) {
					q41C = "99";

				}

			}
		}

		if (radioGroup41D != null) {

			if (radioGroup41D.indexOfChild(radioGroup41D
					.findViewById(radioGroup41D.getCheckedRadioButtonId())) != -1) {

				q41D = String
						.valueOf(radioGroup41D.indexOfChild(radioGroup41D
								.findViewById(radioGroup41D
										.getCheckedRadioButtonId())) + 1);

				if (radioGroup41D.indexOfChild(radioGroup41D
						.findViewById(radioGroup41D.getCheckedRadioButtonId())) == 2) {
					q41D = "99";

				}

			}
		}
		if (radioGroup41E.getVisibility() != View.VISIBLE) {
			q41E = "-1";
		}

		else {
			if (radioGroup41E != null) {

				if (radioGroup41E.indexOfChild(radioGroup41E
						.findViewById(radioGroup41E.getCheckedRadioButtonId())) != -1) {

					q41E = String.valueOf(radioGroup41E
							.indexOfChild(radioGroup41E
									.findViewById(radioGroup41E
											.getCheckedRadioButtonId())) + 1);

					if (radioGroup41E.indexOfChild(radioGroup41E
							.findViewById(radioGroup41E
									.getCheckedRadioButtonId())) == 2) {
						q41E = "99";

					}

				}
			}
		}
		q42_Days = "";
		q43_Days = "";
		if (q31D.equals("1")) {
			if (q42_Dayset.getText().toString().length() <= 0
					|| q43_Dayset.getText().toString().length() <= 0) {
				CommonStaticClass.showMyAlert(con, "Message",
						"Question No. 42 and 43 Must have value");
				return false;
			} else {
				if (q42_Dayset.getText().toString().length() > 0) {
					q42_Days = q42_Dayset.getText().toString();
					q43_Days = q43_Dayset.getText().toString();

				} else {
					q42_Days = "";
					q43_Days = "";
				}

			}
		}

		q42_weeks = "";
		q43_weeks = "";

		if (q31E.equals("1")) {
			if (q42_weekset.getText().toString().length() <= 0
					|| q43_weekset.getText().toString().length() <= 0) {
				CommonStaticClass.showMyAlert(con, "Message",
						"Question No. 42 and 43 Must have value");
				return false;
			} else {
				if (q42_weekset.getText().toString().length() > 0) {
					q42_weeks = q42_weekset.getText().toString();
					q43_weeks = q43_weekset.getText().toString();

				} else {
					//q42_weeks = "";
					//q43_weeks = "";
				}

			}
		}

		if(CommonStaticClass.futureDateValidator(dateYear,dateMonth,dateDay))
		{
	CommonStaticClass
	.showMyAlert(con, "Message",
			"Invalid Date of Birth");
			return false;
		}
		// End symptom

		if (CommonStaticClass.mode.equalsIgnoreCase(CommonStaticClass.ADDMODE)
				|| CommonStaticClass.mode
						.equalsIgnoreCase(CommonStaticClass.SPECIALADD)) {

			sql = String
					.format("UPDATE tblSamples SET q12='%s', q13='%s', q14='%s', q15='%s', q16='%s', q17='%s', q18_days='%s', q18_months='%s', q18='%s', q19_no_of_time='%s', q19='%s',"
							+ " q20_time1='%s', q20_other_time1='%s', q21_days_time1='%s', q21_dontknow_time1='%s', q20_time2='%s', q20_other_time2='%s', q21_days_time2='%s', q21_dontknow_time2='%s', q20_time3='%s',"
							+ " q20_other_time3='%s', q21_days_time3='%s', q21_dontknow_time3='%s', q20_time4='%s', q20_other_time4='%s', q21_days_time4='%s', q21_dontknow_time4='%s', q20_time5='%s', q20_other_time5='%s',"
							+ " q21_days_time5='%s', q21_dontknow_time5='%s', q22='%s', q23='%s', q24='%s', q25='%s', q26_days='%s', q26_months='%s', q26='%s', q27='%s', q27_other='%s', q28='%s',q29='%s',"
							+ " q30A='%s', q30B='%s', q30C='%s', q30D='%s', q30E='%s', q31A='%s', q31B='%s', q31C='%s', q31D='%s', q31E='%s', q32A='%s', q32B='%s', q32C='%s', q32D='%s', q32E='%s', q33A='%s', q33B='%s', q33C='%s', q34A='%s', q34B='%s', q34C='%s', q34D='%s', q34E='%s',"
							+ " q35A='%s', q35B='%s', q35C='%s', q35D='%s', q35E='%s', q36A='%s', q36B='%s', q36C='%s', q36D='%s', q36E='%s', q37A='%s', q37B='%s', q37C='%s', q37D='%s', q37E='%s', q38A='%s', q38B='%s', q38C='%s', q38D='%s', q38E='%s',"
							+ " q39A='%s', q39B='%s', q39C='%s', q39D='%s', q39E='%s', q40A='%s', q40B='%s', q40C='%s', q40D='%s', q40E='%s', q41A='%s', q41B='%s', q41C='%s', q41D='%s', q41E='%s',"
							+ " q42_Days='%s', q43_Days='%s', q42_weeks='%s', q43_weeks='%s', EditBy='%s', EditDate='%s' WHERE dataid='%s' AND childNo='%s'",
							q12, q13, q14, q15, q16, q17, q18_days, q18_months,
							q18, q19_no_of_time, q19, q20_time1,
							q20_other_time1, q21_days_time1,
							q21_Dontknow_time1, q20_time2, q20_other_time2,
							q21_days_time2, q21_Dontknow_time2, q20_time3,
							q20_other_time3, q21_days_time3,
							q21_Dontknow_time3, q20_time4, q20_other_time4,
							q21_days_time4, q21_Dontknow_time4, q20_time5,
							q20_other_time5, q21_days_time5,
							q21_Dontknow_time5, q22, q23, q24, q25, q26_days,
							q26_months, q26, q27, q27_other, q28, q29, q30A,
							q30B, q30C, q30D, q30E, q31A, q31B, q31C, q31D,
							q31E, q32A, q32B, q32C, q32D, q32E, q33A, q33B,
							q33C, q34A, q34B, q34C, q34D, q34E, q35A, q35B,
							q35C, q35D, q35E, q36A, q36B, q36C, q36D, q36E,
							q37A, q37B, q37C, q37D, q37E, q38A, q38B, q38C,
							q38D, q38E, q39A, q39B, q39C, q39D, q39E, q40A,
							q40B, q40C, q40D, q40E, q41A, q41B, q41C, q41D,
							q41E, q42_Days, q43_Days, q42_weeks, q43_weeks,
							CommonStaticClass.userSpecificId, entryDate,
							CommonStaticClass.dataId, childNo);

			
		}

		else if (CommonStaticClass.mode
				.equalsIgnoreCase(CommonStaticClass.EDITMODE)) {

			

			sql = String
					.format("UPDATE tblSamples SET q12='%s', q13='%s', q14='%s', q15='%s', q16='%s', q17='%s', q18_days='%s', q18_months='%s', q18='%s', q19_no_of_time='%s', q19='%s',"
							+ " q20_time1='%s', q20_other_time1='%s', q21_days_time1='%s', q21_dontknow_time1='%s', q20_time2='%s', q20_other_time2='%s', q21_days_time2='%s', q21_dontknow_time2='%s', q20_time3='%s',"
							+ " q20_other_time3='%s', q21_days_time3='%s', q21_dontknow_time3='%s', q20_time4='%s', q20_other_time4='%s', q21_days_time4='%s', q21_dontknow_time4='%s', q20_time5='%s', q20_other_time5='%s',"
							+ " q21_days_time5='%s', q21_dontknow_time5='%s', q22='%s', q23='%s', q24='%s', q25='%s', q26_days='%s', q26_months='%s', q26='%s', q27='%s', q27_other='%s', q28='%s',q29='%s',"
							+ " q30A='%s', q30B='%s', q30C='%s', q30D='%s', q30E='%s', q31A='%s', q31B='%s', q31C='%s', q31D='%s', q31E='%s', q32A='%s', q32B='%s', q32C='%s', q32D='%s', q32E='%s', q33A='%s', q33B='%s', q33C='%s', q34A='%s', q34B='%s', q34C='%s', q34D='%s', q34E='%s',"
							+ " q35A='%s', q35B='%s', q35C='%s', q35D='%s', q35E='%s', q36A='%s', q36B='%s', q36C='%s', q36D='%s', q36E='%s', q37A='%s', q37B='%s', q37C='%s', q37D='%s', q37E='%s', q38A='%s', q38B='%s', q38C='%s', q38D='%s', q38E='%s',"
							+ " q39A='%s', q39B='%s', q39C='%s', q39D='%s', q39E='%s', q40A='%s', q40B='%s', q40C='%s', q40D='%s', q40E='%s', q41A='%s', q41B='%s', q41C='%s', q41D='%s', q41E='%s', "
							+ " q42_Days='%s', q43_Days='%s', q42_weeks='%s', q43_weeks='%s',  EditBy='%s',EditDate='%s' WHERE dataid='%s' AND childNo='%s'",
							q12, q13, q14, q15, q16, q17, q18_days, q18_months,
							q18, q19_no_of_time, q19, q20_time1,
							q20_other_time1, q21_days_time1,
							q21_Dontknow_time1, q20_time2, q20_other_time2,
							q21_days_time2, q21_Dontknow_time2, q20_time3,
							q20_other_time3, q21_days_time3,
							q21_Dontknow_time3, q20_time4, q20_other_time4,
							q21_days_time4, q21_Dontknow_time4, q20_time5,
							q20_other_time5, q21_days_time5,
							q21_Dontknow_time5, q22, q23, q24, q25, q26_days,
							q26_months, q26, q27, q27_other, q28, q29, q30A,
							q30B, q30C, q30D, q30E, q31A, q31B, q31C, q31D,
							q31E, q32A, q32B, q32C, q32D, q32E, q33A, q33B,
							q33C, q34A, q34B, q34C, q34D, q34E, q35A, q35B,
							q35C, q35D, q35E, q36A, q36B, q36C, q36D, q36E,
							q37A, q37B, q37C, q37D, q37E, q38A, q38B, q38C,
							q38D, q38E, q39A, q39B, q39C, q39D, q39E, q40A,
							q40B, q40C, q40D, q40E, q41A, q41B, q41C, q41D,
							q41E, q42_Days, q43_Days, q42_weeks, q43_weeks,
							CommonStaticClass.userSpecificId, entryDate,
							CommonStaticClass.dataId, childNo);

			CommonStaticClass.currentChildrenCount = 0;
			// }
		}
		try {
			if (DatabaseHelper.getInstance(con).executeDMLQuery(sql))
			// resetEveryValue();
			// makeCopy();
			{

				// resetEveryValue();
				if (CommonStaticClass.mode
						.equalsIgnoreCase(CommonStaticClass.ADDMODE)) {

					Toast.makeText(con, "Saved and Ready for Next Sample",
							Toast.LENGTH_LONG).show();
					// CommonStaticClass.showFinalAlert(con,"");

					checkToFinish();
					/*
					 * ((TextView) findViewById(R.id.title_Bar))
					 * .setText("Data ID: " +
					 * CommonStaticClass.dataId.toString() + "  Child No: " +
					 * String.valueOf(CommonStaticClass.currentChildrenCount));
					 */

					setTitle("Child Identification :: Data ID: "
							+ CommonStaticClass.dataId.toString()
							+ " Child No :"
							+ String.valueOf(CommonStaticClass.currentChildrenCount));

					return true;

				} else {
					Toast.makeText(con, "Data Updated", Toast.LENGTH_LONG)
							.show();
					// CommonStaticClass.showFinalAlert(con, "Data Updated");
					resetSomeData();
					Log.e("Return for Edit", "Return true");
					return true;
					// finish();
				}
			} else {
				Toast.makeText(con, "An Error Occured. Maximum Child Reached",
						Toast.LENGTH_LONG).show();
				// CommonStaticClass.showFinalAlert(con,
				// "An Error Occured. Maximum Child Reached");

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

	private boolean ValidActivity() {

		viewGroup = (LinearLayout) findViewById(R.id.rootView);
		boolean alltrue = true;

		alltrue = ViewGroupReferencesForvalidation(viewGroup, alltrue);
		// int nrOfChildren = viewGroup.getChildCount();

		/*
		 * try { for (int i = 0; i < nrOfChildren; i++) { View view =
		 * viewGroup.getChildAt(i);
		 * 
		 * 
		 * 
		 * } } catch (Exception e) { Log.e("validate", "Validation Exception");
		 * }
		 */
		return alltrue;
	}

	private boolean ValidateSymptom() {
		vg = (GridLayout) findViewById(R.id.grd);
		int nrOfChildren = vg.getChildCount();
		boolean alltrue = true;
		for (int i = 0; i < nrOfChildren; i++) {
			View view = vg.getChildAt(i);

			if (view.getVisibility() == View.GONE) {
				alltrue = true;
				continue;
			}

			if (view instanceof RadioGroup) {

				if (view.getVisibility() == View.GONE) {
					alltrue = true;
					continue;
				}

				if (((RadioGroup) view).getCheckedRadioButtonId() == -1) {
					return alltrue = false;
				}

				
			}

		}
		return alltrue;
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
			// makeCopy();
			Intent intnt = new Intent(SampleCollector.this, MenuScreen.class);
			startActivity(intnt);

			// finish();
		} else {
			CommonStaticClass.currentChildrenCount = CommonStaticClass.currentChildrenCount + 1;
		}
	}

	public void makeCopy() {
		// DatabaseHelper.getInstance(con).copyDataBasdeToSdcard();
		resetSomeData();
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (CommonStaticClass.mode.equalsIgnoreCase(CommonStaticClass.EDITMODE)) {

			// progressDialog = ProgressDialog.show(con, "Wait",
			// "Please wait while Loading");
			// new Thread() {

			// public void run() {
			// Looper.prepare();
			loadDataForEdit();

			// Message ms = new Message();
			// ms.what = UI_Loaded;
			// handler.sendMessage(ms);
			// Looper.loop();

			// }
			// }.start();

		}
	}

	private void loadDataForEdit() {

		if (CommonStaticClass.itemToEdite != null) {

			q12et.setText(CommonStaticClass.itemToEdite.getQ12());
			q13et.setText(CommonStaticClass.itemToEdite.getQ13());

			if (CommonStaticClass.itemToEdite.getQ14() != null) {
				((RadioButton) rdogroup14.getChildAt(Integer
						.parseInt(CommonStaticClass.itemToEdite.getQ14()) - 1))
						.toggle();
			}
			q15et.setText(CommonStaticClass.itemToEdite.getQ15());

			if (CommonStaticClass.itemToEdite.getQ16() != null) {
				if (CommonStaticClass.itemToEdite.getQ16().equalsIgnoreCase(
						"99")) {
					((RadioButton) rdogroup16.getChildAt(3)).toggle();
				} else {
					((RadioButton) rdogroup16
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ16()) - 1)).toggle();

				}
			}
			q17et.setText(CommonStaticClass.itemToEdite.getQ17());

			q18dayet.setText(CommonStaticClass.itemToEdite.getQ18_days());
			q18monthset.setText(CommonStaticClass.itemToEdite.getQ18_months());

			if (CommonStaticClass.itemToEdite.getQ18() != null) {
				if (!CommonStaticClass.itemToEdite.getQ18().equalsIgnoreCase(
						"-1")) {
					if (CommonStaticClass.itemToEdite.getQ18()
							.equalsIgnoreCase("88")) {
						((RadioButton) radiogroup18.getChildAt(0)).toggle();
					} else if (CommonStaticClass.itemToEdite.getQ18()
							.equalsIgnoreCase("99")) {
						((RadioButton) radiogroup18.getChildAt(1)).toggle();
					} else {
						((RadioButton) radiogroup18.getChildAt(Integer
								.parseInt(CommonStaticClass.itemToEdite
										.getQ18()) - 1)).toggle();
					}
				}
			}
			q19et.setText(CommonStaticClass.itemToEdite.getQ19_no_of_time());

			if (CommonStaticClass.itemToEdite.getQ19() != null) {
				if (CommonStaticClass.itemToEdite.getQ19().equalsIgnoreCase(
						"88")) {
					((RadioButton) radioGroup19.getChildAt(0)).toggle();
				} else if (CommonStaticClass.itemToEdite.getQ19()
						.equalsIgnoreCase("99")) {
					((RadioButton) radioGroup19.getChildAt(1)).toggle();
				}
			}
			if (CommonStaticClass.itemToEdite.getQ20_time1() != null) {
				if (Integer.parseInt(CommonStaticClass.itemToEdite
						.getQ20_time1()) != -1) {
					if (radioG20time1.getVisibility() == View.VISIBLE) {
						if (CommonStaticClass.itemToEdite.getQ20_time1()
								.equalsIgnoreCase("99")) {

							((RadioButton) radioG20time1.getChildAt(13))
									.setChecked(true);

						} else {
							if (Integer.parseInt(CommonStaticClass.itemToEdite
									.getQ20_time1()) != -1)
								((RadioButton) radioG20time1.getChildAt(Integer
										.parseInt(CommonStaticClass.itemToEdite
												.getQ20_time1()) - 1)).toggle();
						}
					}
				}
			}

			if (CommonStaticClass.itemToEdite.getQ20_time2() != null) {
				if (Integer.parseInt(CommonStaticClass.itemToEdite
						.getQ20_time2()) != -1) {
					if (radioG20time2.getVisibility() == View.VISIBLE) {
						if (CommonStaticClass.itemToEdite.getQ20_time2()
								.equalsIgnoreCase("99")) {

							((RadioButton) radioG20time2.getChildAt(13))
									.toggle();
						} else {

							if (Integer.parseInt(CommonStaticClass.itemToEdite
									.getQ20_time2()) != -1)
								((RadioButton) radioG20time2.getChildAt(Integer
										.parseInt(CommonStaticClass.itemToEdite
												.getQ20_time2()) - 1)).toggle();
						}
					}
				}
			}

			if (CommonStaticClass.itemToEdite.getQ20_time3() != null) {
				if (Integer.parseInt(CommonStaticClass.itemToEdite
						.getQ20_time3()) != -1) {
					if (radioG20time3.getVisibility() == View.VISIBLE) {
						if (CommonStaticClass.itemToEdite.getQ20_time3()
								.equalsIgnoreCase("99")) {
							((RadioButton) radioG20time3.getChildAt(13))
									.toggle();
						} else {
							if (Integer.parseInt(CommonStaticClass.itemToEdite
									.getQ20_time3()) != -1)
								((RadioButton) radioG20time3.getChildAt(Integer
										.parseInt(CommonStaticClass.itemToEdite
												.getQ20_time3()) - 1)).toggle();
						}
					}
				}
			}

			if (CommonStaticClass.itemToEdite.getQ20_time4() != null) {
				if (Integer.parseInt(CommonStaticClass.itemToEdite
						.getQ20_time4()) != -1) {
					if (radioG20time4.getVisibility() == View.VISIBLE) {
						if (CommonStaticClass.itemToEdite.getQ20_time4()

						.equalsIgnoreCase("99")) {
							((RadioButton) radioG20time4.getChildAt(13))
									.toggle();
						} else {
							if (Integer.parseInt(CommonStaticClass.itemToEdite
									.getQ20_time4()) != -1)
								((RadioButton) radioG20time4.getChildAt(Integer
										.parseInt(CommonStaticClass.itemToEdite
												.getQ20_time4()) - 1)).toggle();
						}
					}
				}
			}

			if (CommonStaticClass.itemToEdite.getQ20_time5() != null) {
				if (Integer.parseInt(CommonStaticClass.itemToEdite
						.getQ20_time5()) != -1) {
					if (radioG20time4.getVisibility() == View.VISIBLE) {
						if (CommonStaticClass.itemToEdite.getQ20_time5()
								.equalsIgnoreCase("99")) {
							((RadioButton) radioG20time5.getChildAt(13))
									.toggle();
						} else {
							if (Integer.parseInt(CommonStaticClass.itemToEdite
									.getQ20_time5()) != -1)
								((RadioButton) radioG20time5.getChildAt(Integer
										.parseInt(CommonStaticClass.itemToEdite
												.getQ20_time5()) - 1)).toggle();
						}
					}
				}
			}
			q20_other_time1et.setText(CommonStaticClass.itemToEdite
					.getQ20_other_time1());
			q20_other_time2et.setText(CommonStaticClass.itemToEdite
					.getQ20_other_time2());
			q20_other_time3et.setText(CommonStaticClass.itemToEdite
					.getQ20_other_time3());
			q20_other_time4et.setText(CommonStaticClass.itemToEdite
					.getQ20_other_time4());
			q20_other_time5et.setText(CommonStaticClass.itemToEdite
					.getQ20_other_time5());

			q21ettime1et.setText(CommonStaticClass.itemToEdite
					.getQ21_days_time1());
			q21ettime2et.setText(CommonStaticClass.itemToEdite
					.getQ21_days_time2());
			q21ettime3et.setText(CommonStaticClass.itemToEdite
					.getQ21_days_time3());
			q21ettime4et.setText(CommonStaticClass.itemToEdite
					.getQ21_days_time4());
			q21ettime5et.setText(CommonStaticClass.itemToEdite
					.getQ21_days_time5());

			if (CommonStaticClass.itemToEdite.getQ21_dontknow_time1() != null) {
				if (CommonStaticClass.itemToEdite.getQ21_dontknow_time1()
						.equalsIgnoreCase("99")) {
					radioGroup2101time1.toggle();
				}
			}

			if (CommonStaticClass.itemToEdite.getQ21_dontknow_time2() != null) {
				if (CommonStaticClass.itemToEdite.getQ21_dontknow_time2()
						.equalsIgnoreCase("99")) {
					radioGroup2101time2.toggle();
				}
			}

			if (CommonStaticClass.itemToEdite.getQ21_dontknow_time3() != null) {
				if (CommonStaticClass.itemToEdite.getQ21_dontknow_time3()
						.equalsIgnoreCase("99")) {
					radioGroup2101time3.toggle();
				}
			}

			if (CommonStaticClass.itemToEdite.getQ21_dontknow_time4() != null) {
				if (CommonStaticClass.itemToEdite.getQ21_dontknow_time4()
						.equalsIgnoreCase("99")) {
					radioGroup2101time4.toggle();
				}
			}

			if (CommonStaticClass.itemToEdite.getQ21_dontknow_time5() != null) {
				if (CommonStaticClass.itemToEdite.getQ21_dontknow_time5()
						.equalsIgnoreCase("99")) {
					radioGroup2101time5.toggle();
				}
			}

			q19et.setText(CommonStaticClass.itemToEdite.getQ19_no_of_time());

			if (CommonStaticClass.itemToEdite.getQ19() != null) {
				if (CommonStaticClass.itemToEdite.getQ19().equalsIgnoreCase(
						"88")) {
					((RadioButton) radioGroup19.getChildAt(0)).toggle();
				} else if (CommonStaticClass.itemToEdite.getQ19()
						.equalsIgnoreCase("99")) {
					((RadioButton) radioGroup19.getChildAt(1)).toggle();
				}
			}
			if (CommonStaticClass.itemToEdite.getQ22() != null) {
				if (CommonStaticClass.itemToEdite.getQ22().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup22.getChildAt(4)).toggle();
				} else {
					((RadioButton) radioGroup22
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ22()) - 1)).toggle();
				}
			}
			if (CommonStaticClass.itemToEdite.getQ23() != null) {
				if (CommonStaticClass.itemToEdite.getQ23().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup23.getChildAt(4)).toggle();
				} else {
					((RadioButton) radioGroup23
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ23()) - 1)).toggle();
				}
			}
			if (CommonStaticClass.itemToEdite.getQ24() != null) {
				if (CommonStaticClass.itemToEdite.getQ24().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup24.getChildAt(3)).toggle();
				} else {
					((RadioButton) radioGroup24
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ24()) - 1)).toggle();
				}
			}

			if (CommonStaticClass.itemToEdite.getQ25() != null) {
				if (!CommonStaticClass.itemToEdite.getQ25().equalsIgnoreCase(
						"null")) {
					if (CommonStaticClass.itemToEdite.getQ25()
							.equalsIgnoreCase("99")) {
						((RadioButton) radioGroup25.getChildAt(4)).toggle();
					} else {

						if (!CommonStaticClass.itemToEdite.getQ25()
								.equals("-1")) {
							((RadioButton) radioGroup25.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ25()) - 1)).toggle();
						}
					}
				}
			}

			if (CommonStaticClass.itemToEdite.getQ26_days() != null) {
				if (!CommonStaticClass.itemToEdite.getQ26_days()
						.equalsIgnoreCase("null")) {
					q26dayset.setText(CommonStaticClass.itemToEdite
							.getQ26_days());
				}
			}
			if (CommonStaticClass.itemToEdite.getQ26_months() != null) {
				if (!CommonStaticClass.itemToEdite.getQ26_months()
						.equalsIgnoreCase("null")) {
					q26monthset.setText(CommonStaticClass.itemToEdite
							.getQ26_months());
				}
			}
			if (CommonStaticClass.itemToEdite.getQ26() != null) {
				if (!CommonStaticClass.itemToEdite.getQ26().equalsIgnoreCase(
						"null")) {
					if (CommonStaticClass.itemToEdite.getQ26()
							.equalsIgnoreCase("99")) {
						rdoq26Dontknow.toggle();
					}
				}
			}
			if (CommonStaticClass.itemToEdite.getQ27() != null) {
				if (!CommonStaticClass.itemToEdite.getQ27().equalsIgnoreCase(
						"null")) {

					if (CommonStaticClass.itemToEdite.getQ27()
							.equalsIgnoreCase("99")) {
						((RadioButton) radioGroup27.getChildAt(2)).toggle();
					} 
					else if (CommonStaticClass.itemToEdite.getQ27()
							.equalsIgnoreCase("3")) {
						((RadioButton) radioGroup27.getChildAt(3)).toggle();
					} 
					else {
						if (!CommonStaticClass.itemToEdite.getQ27()
								.equals("-1")) {
							((RadioButton) radioGroup27.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ27()) - 1)).toggle();
						}
					}
				}
			}
			if (CommonStaticClass.itemToEdite.getQ27_other() != null) {
				if (!CommonStaticClass.itemToEdite.getQ27_other()
						.equalsIgnoreCase("null")) {

					q27_otheret.setText(CommonStaticClass.itemToEdite
							.getQ27_other());
				}
			}
			if (CommonStaticClass.itemToEdite.getQ28() != null) {
				if (!CommonStaticClass.itemToEdite.getQ28().equalsIgnoreCase(
						"null")) {

					if (CommonStaticClass.itemToEdite.getQ28()
							.equalsIgnoreCase("99")) {
						((RadioButton) radioGroup28.getChildAt(2)).toggle();
					} else {
						((RadioButton) radioGroup28.getChildAt(Integer
								.parseInt(CommonStaticClass.itemToEdite
										.getQ28()) - 1)).toggle();
					}
				}
			}

			if (CommonStaticClass.itemToEdite.getQ29() != null) {
				if (!CommonStaticClass.itemToEdite.getQ29().equalsIgnoreCase(
						"null")) {

					if (CommonStaticClass.itemToEdite.getQ29()
							.equalsIgnoreCase("99")) {
						((RadioButton) radioGroup29.getChildAt(2)).toggle();
					} else {

						((RadioButton) radioGroup29.getChildAt(Integer
								.parseInt(CommonStaticClass.itemToEdite
										.getQ29()) - 1)).toggle();
					}
				}
			}
			// symptom
			// 30

			if (CommonStaticClass.itemToEdite.getQ30A() != null) {
				if (CommonStaticClass.itemToEdite.getQ30A().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup30A.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup30A
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ30A()) - 1)).toggle();
				}
			}

			if (CommonStaticClass.itemToEdite.getQ30B() != null) {
				if (CommonStaticClass.itemToEdite.getQ30B().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup30B.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup30B
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ30B()) - 1)).toggle();
				}
			}
			if (CommonStaticClass.itemToEdite.getQ30C() != null) {
				if (CommonStaticClass.itemToEdite.getQ30C().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup30C.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup30C
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ30C()) - 1)).toggle();
				}
			}

			if (CommonStaticClass.itemToEdite.getQ30D() != null) {
				if (CommonStaticClass.itemToEdite.getQ30D().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup30D.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup30D
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ30D()) - 1)).toggle();
				}
			}

			if (CommonStaticClass.itemToEdite.getQ30E() != null) {
				if (CommonStaticClass.itemToEdite.getQ30E().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup30E.getChildAt(2)).toggle();
				} else {
					if (CommonStaticClass.itemToEdite.getQ30E()
							.equalsIgnoreCase("-1")) {

					} else
						((RadioButton) radioGroup30E.getChildAt(Integer
								.parseInt(CommonStaticClass.itemToEdite
										.getQ30E()) - 1)).toggle();
				}
			}
			// 31
			if (CommonStaticClass.itemToEdite.getQ31A() != null) {
				if (CommonStaticClass.itemToEdite.getQ31A().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup31A.getChildAt(2)).toggle();
				} else {
					((RadioButton) radioGroup31A
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ31A()) - 1)).toggle();
				}
			}
			if (CommonStaticClass.itemToEdite.getQ31B() != null) {
				if (CommonStaticClass.itemToEdite.getQ31B().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup31B.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup31B
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ31B()) - 1)).toggle();
				}
			}

			if (CommonStaticClass.itemToEdite.getQ31C() != null) {
				if (CommonStaticClass.itemToEdite.getQ31C().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup31C.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup31C
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ31C()) - 1)).toggle();
				}
			}

			if (CommonStaticClass.itemToEdite.getQ31D() != null) {
				if (CommonStaticClass.itemToEdite.getQ31D().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup31D.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup31D
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ31D()) - 1)).toggle();
				}
			}

			if (CommonStaticClass.itemToEdite.getQ31E() != null) {
				if (CommonStaticClass.itemToEdite.getQ31E().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup31E.getChildAt(2)).toggle();
				} else {

					if (CommonStaticClass.itemToEdite.getQ31E()
							.equalsIgnoreCase("-1")) {

					} else
						((RadioButton) radioGroup31E.getChildAt(Integer
								.parseInt(CommonStaticClass.itemToEdite
										.getQ31E()) - 1)).toggle();
				}
			}
			// 32
			if (CommonStaticClass.itemToEdite.getQ32A() != null) {
				if (CommonStaticClass.itemToEdite.getQ32A().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup32A.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup32A
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ32A()) - 1)).toggle();
				}
			}

			if (CommonStaticClass.itemToEdite.getQ32B() != null) {
				if (CommonStaticClass.itemToEdite.getQ32B().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup32B.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup32B
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ32B()) - 1)).toggle();
				}
			}
			if (CommonStaticClass.itemToEdite.getQ32C() != null) {
				if (CommonStaticClass.itemToEdite.getQ32C().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup32C.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup32C
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ32C()) - 1)).toggle();
				}
			}

			if (CommonStaticClass.itemToEdite.getQ32D() != null) {
				if (CommonStaticClass.itemToEdite.getQ32D().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup32D.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup32D
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ32D()) - 1)).toggle();
				}
			}
			if (CommonStaticClass.itemToEdite.getQ32E() != null) {

				if (CommonStaticClass.itemToEdite.getQ32E().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup32E.getChildAt(2)).toggle();
				} else {

					if (CommonStaticClass.itemToEdite.getQ32E()
							.equalsIgnoreCase("-1")) {

					} else
						((RadioButton) radioGroup32E.getChildAt(Integer
								.parseInt(CommonStaticClass.itemToEdite
										.getQ32E()) - 1)).toggle();
				}
			}

			if (CommonStaticClass.itemToEdite.getQ33A() != null) {
				if (!CommonStaticClass.itemToEdite.getQ33A().equalsIgnoreCase(
						"null")) {
					((EditText) findViewById(R.id.q33Aet))
							.setText(CommonStaticClass.itemToEdite.getQ33A());
				}
			}
			if (CommonStaticClass.itemToEdite.getQ33B() != null) {
				if (!CommonStaticClass.itemToEdite.getQ33B().equalsIgnoreCase(
						"null")) {
					((EditText) findViewById(R.id.q33Bet))
							.setText(CommonStaticClass.itemToEdite.getQ33B());
				}
			}
			if (CommonStaticClass.itemToEdite.getQ33C() != null) {
				if (!CommonStaticClass.itemToEdite.getQ33C().equalsIgnoreCase(
						"null")) {
					((EditText) findViewById(R.id.q33Cet))
							.setText(CommonStaticClass.itemToEdite.getQ33C());
				}
			}

			// 34
			if (CommonStaticClass.itemToEdite.getQ34A() != null) {
				if (CommonStaticClass.itemToEdite.getQ34A().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup34A.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup34A
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ34A()) - 1)).toggle();
				}

			}

			if (CommonStaticClass.itemToEdite.getQ34B() != null) {
				if (CommonStaticClass.itemToEdite.getQ34B().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup34B.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup34B
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ34B()) - 1)).toggle();
				}
			}

			if (CommonStaticClass.itemToEdite.getQ34C() != null) {
				if (CommonStaticClass.itemToEdite.getQ34C().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup34C.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup34C
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ34C()) - 1)).toggle();
				}
			}
			if (CommonStaticClass.itemToEdite.getQ34D() != null) {
				if (CommonStaticClass.itemToEdite.getQ34D().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup34D.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup34D
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ34D()) - 1)).toggle();
				}
			}
			if (CommonStaticClass.itemToEdite.getQ34E() != null) {
				if (CommonStaticClass.itemToEdite.getQ34E().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup34E.getChildAt(2)).toggle();
				} else {
					if (CommonStaticClass.itemToEdite.getQ34E()
							.equalsIgnoreCase("-1")) {

					} else
						((RadioButton) radioGroup34E.getChildAt(Integer
								.parseInt(CommonStaticClass.itemToEdite
										.getQ34E()) - 1)).toggle();
				}
			}
			// 35
			if (CommonStaticClass.itemToEdite.getQ35A() != null) {
				if (CommonStaticClass.itemToEdite.getQ35A().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup35A.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup35A
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ35A()) - 1)).toggle();
				}
			}
			if (CommonStaticClass.itemToEdite.getQ35B() != null) {
				if (CommonStaticClass.itemToEdite.getQ35B().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup35B.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup35B
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ35B()) - 1)).toggle();
				}
			}
			if (CommonStaticClass.itemToEdite.getQ35C() != null) {
				if (CommonStaticClass.itemToEdite.getQ35C().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup35C.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup35C
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ35C()) - 1)).toggle();
				}
			}
			if (CommonStaticClass.itemToEdite.getQ35D() != null) {
				if (CommonStaticClass.itemToEdite.getQ35D().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup35D.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup35D
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ35D()) - 1)).toggle();
				}
			}
			if (CommonStaticClass.itemToEdite.getQ35E() != null) {
				if (CommonStaticClass.itemToEdite.getQ35E().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup35E.getChildAt(2)).toggle();
				} else {
					if (CommonStaticClass.itemToEdite.getQ35E()
							.equalsIgnoreCase("-1")) {

					} else
						((RadioButton) radioGroup35E.getChildAt(Integer
								.parseInt(CommonStaticClass.itemToEdite
										.getQ35E()) - 1)).toggle();
				}
			}

			// 36
			if (CommonStaticClass.itemToEdite.getQ36A() != null) {
				if (CommonStaticClass.itemToEdite.getQ36A().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup36A.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup36A
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ36A()) - 1)).toggle();
				}
			}

			if (CommonStaticClass.itemToEdite.getQ36B() != null) {
				if (CommonStaticClass.itemToEdite.getQ36B().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup36B.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup36B
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ36B()) - 1)).toggle();
				}
			}
			if (CommonStaticClass.itemToEdite.getQ36C() != null) {
				if (CommonStaticClass.itemToEdite.getQ36C().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup36C.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup36C
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ36C()) - 1)).toggle();
				}
			}

			if (CommonStaticClass.itemToEdite.getQ36D() != null) {
				if (CommonStaticClass.itemToEdite.getQ36D().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup36D.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup36D
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ36D()) - 1)).toggle();
				}
			}
			if (CommonStaticClass.itemToEdite.getQ36E() != null) {
				if (CommonStaticClass.itemToEdite.getQ36E().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup36E.getChildAt(2)).toggle();
				} else {
					if (CommonStaticClass.itemToEdite.getQ36E()
							.equalsIgnoreCase("-1")) {

					} else
						((RadioButton) radioGroup36E.getChildAt(Integer
								.parseInt(CommonStaticClass.itemToEdite
										.getQ36E()) - 1)).toggle();
				}
			}

			// 37
			if (CommonStaticClass.itemToEdite.getQ37A() != null) {
				if (CommonStaticClass.itemToEdite.getQ37A().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup37A.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup37A
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ37A()) - 1)).toggle();
				}
			}
			if (CommonStaticClass.itemToEdite.getQ37B() != null) {
				if (CommonStaticClass.itemToEdite.getQ37B().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup37B.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup37B
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ37B()) - 1)).toggle();
				}
			}
			if (CommonStaticClass.itemToEdite.getQ37C() != null) {
				if (CommonStaticClass.itemToEdite.getQ37C().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup37C.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup37C
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ37C()) - 1)).toggle();
				}
			}

			if (CommonStaticClass.itemToEdite.getQ37D() != null) {
				if (CommonStaticClass.itemToEdite.getQ37D().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup37D.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup37D
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ37D()) - 1)).toggle();
				}
			}
			if (CommonStaticClass.itemToEdite.getQ37E() != null) {
				if (CommonStaticClass.itemToEdite.getQ37E().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup37E.getChildAt(2)).toggle();
				} else {
					if (CommonStaticClass.itemToEdite.getQ37E()
							.equalsIgnoreCase("-1")) {

					} else
						((RadioButton) radioGroup37E.getChildAt(Integer
								.parseInt(CommonStaticClass.itemToEdite
										.getQ37E()) - 1)).toggle();
				}
			}
			// 38
			if (CommonStaticClass.itemToEdite.getQ38A() != null) {
				if (CommonStaticClass.itemToEdite.getQ38A().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup38A.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup38A
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ38A()) - 1)).toggle();
				}
			}
			if (CommonStaticClass.itemToEdite.getQ38B() != null) {
				if (CommonStaticClass.itemToEdite.getQ38B().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup38B.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup38B
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ38B()) - 1)).toggle();
				}
			}
			if (CommonStaticClass.itemToEdite.getQ38C() != null) {
				if (CommonStaticClass.itemToEdite.getQ38C().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup38C.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup38C
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ38C()) - 1)).toggle();
				}
			}
			if (CommonStaticClass.itemToEdite.getQ38D() != null) {
				if (CommonStaticClass.itemToEdite.getQ38D().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup38D.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup38D
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ38D()) - 1)).toggle();
				}
			}
			if (CommonStaticClass.itemToEdite.getQ38E() != null) {
				if (CommonStaticClass.itemToEdite.getQ38E().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup38E.getChildAt(2)).toggle();
				} else {
					if (CommonStaticClass.itemToEdite.getQ38E()
							.equalsIgnoreCase("-1")) {

					} else
						((RadioButton) radioGroup38E.getChildAt(Integer
								.parseInt(CommonStaticClass.itemToEdite
										.getQ38E()) - 1)).toggle();
				}
			}
			// 39
			if (CommonStaticClass.itemToEdite.getQ39A() != null) {
				if (CommonStaticClass.itemToEdite.getQ39A().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup39A.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup39A
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ39A()) - 1)).toggle();
				}
			}
			if (CommonStaticClass.itemToEdite.getQ39B() != null) {
				if (CommonStaticClass.itemToEdite.getQ39B().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup39B.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup39B
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ39B()) - 1)).toggle();
				}
			}
			if (CommonStaticClass.itemToEdite.getQ39C() != null) {
				if (CommonStaticClass.itemToEdite.getQ39C().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup39C.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup39C
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ39C()) - 1)).toggle();
				}
			}

			if (CommonStaticClass.itemToEdite.getQ39D() != null) {
				if (CommonStaticClass.itemToEdite.getQ39D().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup39D.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup39D
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ39D()) - 1)).toggle();
				}
			}

			if (CommonStaticClass.itemToEdite.getQ39E() != null) {
				if (CommonStaticClass.itemToEdite.getQ39E().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup39E.getChildAt(2)).toggle();
				} else {
					if (CommonStaticClass.itemToEdite.getQ39E()
							.equalsIgnoreCase("-1")) {

					} else
						((RadioButton) radioGroup39E.getChildAt(Integer
								.parseInt(CommonStaticClass.itemToEdite
										.getQ39E()) - 1)).toggle();
				}
			}
			// 40
			if (CommonStaticClass.itemToEdite.getQ40A() != null) {
				if (CommonStaticClass.itemToEdite.getQ40A().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup40A.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup40A
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ40A()) - 1)).toggle();
				}
			}

			if (CommonStaticClass.itemToEdite.getQ40B() != null) {
				if (CommonStaticClass.itemToEdite.getQ40B().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup40B.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup40B
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ40B()) - 1)).toggle();
				}
			}

			if (CommonStaticClass.itemToEdite.getQ40C() != null) {
				if (CommonStaticClass.itemToEdite.getQ40C().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup40C.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup40C
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ40C()) - 1)).toggle();
				}
			}

			if (CommonStaticClass.itemToEdite.getQ40D() != null) {
				if (CommonStaticClass.itemToEdite.getQ40D().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup40D.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup40D
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ40D()) - 1)).toggle();
				}
			}

			if (CommonStaticClass.itemToEdite.getQ40E() != null) {
				if (CommonStaticClass.itemToEdite.getQ40E().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup40E.getChildAt(2)).toggle();
				} else {
					if (CommonStaticClass.itemToEdite.getQ40E()
							.equalsIgnoreCase("-1")) {

					} else
						((RadioButton) radioGroup40E.getChildAt(Integer
								.parseInt(CommonStaticClass.itemToEdite
										.getQ40E()) - 1)).toggle();
				}
			}
			// 41
			if (CommonStaticClass.itemToEdite.getQ41A() != null) {
				if (CommonStaticClass.itemToEdite.getQ41A().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup41A.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup41A
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ41A()) - 1)).toggle();
				}
			}

			if (CommonStaticClass.itemToEdite.getQ41B() != null) {
				if (CommonStaticClass.itemToEdite.getQ41B().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup41B.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup41B
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ41B()) - 1)).toggle();
				}
			}
			if (CommonStaticClass.itemToEdite.getQ41C() != null) {
				if (CommonStaticClass.itemToEdite.getQ41C().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup41C.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup41C
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ41C()) - 1)).toggle();
				}
			}
			if (CommonStaticClass.itemToEdite.getQ41D() != null) {
				if (CommonStaticClass.itemToEdite.getQ41D().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup41D.getChildAt(2)).toggle();
				} else {

					((RadioButton) radioGroup41D
							.getChildAt(Integer
									.parseInt(CommonStaticClass.itemToEdite
											.getQ41D()) - 1)).toggle();
				}
			}

			if (CommonStaticClass.itemToEdite.getQ41E() != null) {
				if (CommonStaticClass.itemToEdite.getQ41E().equalsIgnoreCase(
						"99")) {
					((RadioButton) radioGroup41E.getChildAt(2)).toggle();
				} else {
					if (CommonStaticClass.itemToEdite.getQ41E()
							.equalsIgnoreCase("-1")) {

					} else
						((RadioButton) radioGroup41E.getChildAt(Integer
								.parseInt(CommonStaticClass.itemToEdite
										.getQ41E()) - 1)).toggle();
				}
			}

			if (CommonStaticClass.itemToEdite.getQ42_Days() != null) {
				if (CommonStaticClass.itemToEdite.getQ42_Days().length() > 0
						&& !CommonStaticClass.itemToEdite.getQ42_Days()
								.toString().equalsIgnoreCase("null")) {
					q42_Dayset.setText(CommonStaticClass.itemToEdite
							.getQ42_Days());
				}
			}
			if (CommonStaticClass.itemToEdite.getQ42_Weeks() != null) {
				if (CommonStaticClass.itemToEdite.getQ42_Weeks().length() > 0
						&& !CommonStaticClass.itemToEdite.getQ42_Weeks()
								.equalsIgnoreCase("null")) {
					q42_weekset.setText(CommonStaticClass.itemToEdite
							.getQ42_Weeks());
				}
			}
			if (CommonStaticClass.itemToEdite.getQ43_Days() != null) {
				if (CommonStaticClass.itemToEdite.getQ43_Days().length() > 0
						&& !CommonStaticClass.itemToEdite.getQ43_Days()
								.toString().equalsIgnoreCase("null")) {
					q43_Dayset.setText(CommonStaticClass.itemToEdite
							.getQ43_Days());
				}
			}
			if (CommonStaticClass.itemToEdite.getQ43_Weeks() != null) {
				if (CommonStaticClass.itemToEdite.getQ43_Weeks().length() > 0
						&& !CommonStaticClass.itemToEdite.getQ43_Weeks()
								.equalsIgnoreCase("null")) {
					q43_weekset.setText(CommonStaticClass.itemToEdite
							.getQ42_Weeks());
				}
			}

		}

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

	/*
	 * private TimePickerDialog.OnTimeSetListener timeSetListener = new
	 * TimePickerDialog.OnTimeSetListener() {
	 * 
	 * @Override public void onTimeSet(TimePicker view, int hourOfDay, int
	 * minute) { // TODO Auto-generated method stub hour = hourOfDay; min =
	 * minute; updateDisplay("time"); } };
	 */

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
		/*
		 * if (dt.contains("time")) { String date = new StringBuilder() // Month
		 * is 0 based so add 1 .append(hour).append(":").append(min).toString();
		 * pickTime.setText(date);
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

	private int GetMonth(String month) {
		int sMonth = 0;

		if (month.equalsIgnoreCase("Jan")) {
			sMonth = 1;
		} else if (month.equalsIgnoreCase("Feb")) {
			sMonth = 2;
		} else if (month.equalsIgnoreCase("Mar")) {
			sMonth = 3;
		} else if (month.equalsIgnoreCase("Apr")) {
			sMonth = 4;
		} else if (month.equalsIgnoreCase("May")) {
			sMonth = 5;
		} else if (month.equalsIgnoreCase("Jun")) {
			sMonth = 6;
		} else if (month.equalsIgnoreCase("Jul")) {
			sMonth = 7;
		} else if (month.equalsIgnoreCase("Aug")) {
			sMonth = 8;
		} else if (month.equalsIgnoreCase("Sep")) {
			sMonth = 9;
		} else if (month.equalsIgnoreCase("Oct")) {
			sMonth = 10;
		} else if (month.equalsIgnoreCase("Nov")) {
			sMonth = 11;
		} else if (month.equalsIgnoreCase("Dec")) {
			sMonth = 12;
		}

		return sMonth;

	}

	private void loadAllUI() {

		TabHost th = (TabHost) findViewById(R.id.tabhost);
		th.setup();
		TabSpec ts = th.newTabSpec("tab1");
		ts.setContent(R.id.tab1);
		ts.setIndicator("Child Identification");
		th.addTab(ts);

		ts = th.newTabSpec("tab2");
		ts.setContent(R.id.tab2);
		ts.setIndicator("Antibiotic");
		th.addTab(ts);

		ts = th.newTabSpec("tab3");
		ts.setContent(R.id.tab3);
		ts.setIndicator("Medical History");
		th.addTab(ts);

		ts = th.newTabSpec("tab4");
		ts.setContent(R.id.tab4);
		ts.setIndicator("Symptom");
		th.addTab(ts);
		// load_bangla();
		((EditText) findViewById(R.id.q12))
				.addTextChangedListener(new TextWatcher() {

					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						if (s.length() > 0) {
							if (s.toString() != "") {
								if (s.toString().contains("0")) {
									s = s.toString().replace("0", "");
									((EditText) findViewById(R.id.q12))
											.setText(s);
									((EditText) findViewById(R.id.q12))
											.setSelection(s.length());

								}
								if (s.toString().contains("1")) {
									s = s.toString().replace("1", "");
									((EditText) findViewById(R.id.q12))
											.setText(s);
									((EditText) findViewById(R.id.q12))
											.setSelection(s.length());

								}
								if (s.toString().contains("2")) {
									s = s.toString().replace("2", "");
									((EditText) findViewById(R.id.q12))
											.setText(s);
									((EditText) findViewById(R.id.q12))
											.setSelection(s.length());

								}
								if (s.toString().contains("3")) {
									s = s.toString().replace("3", "");
									((EditText) findViewById(R.id.q12))
											.setText(s);
									((EditText) findViewById(R.id.q12))
											.setSelection(s.length());

								}
								if (s.toString().contains("4")) {
									s = s.toString().replace("4", "");
									((EditText) findViewById(R.id.q12))
											.setText(s);
									((EditText) findViewById(R.id.q12))
											.setSelection(s.length());

								}
								if (s.toString().contains("5")) {
									s = s.toString().replace("5", "");
									((EditText) findViewById(R.id.q12))
											.setText(s);
									((EditText) findViewById(R.id.q12))
											.setSelection(s.length());

								}
								if (s.toString().contains("6")) {
									s = s.toString().replace("6", "");
									((EditText) findViewById(R.id.q12))
											.setText(s);
									((EditText) findViewById(R.id.q12))
											.setSelection(s.length());

								}
								if (s.toString().contains("7")) {
									s = s.toString().replace("7", "");
									((EditText) findViewById(R.id.q12))
											.setText(s);
									((EditText) findViewById(R.id.q12))
											.setSelection(s.length());

								}
								if (s.toString().contains("8")) {
									s = s.toString().replace("8", "");
									((EditText) findViewById(R.id.q12))
											.setText(s);
									((EditText) findViewById(R.id.q12))
											.setSelection(s.length());

								}
								if (s.toString().contains("9")) {
									s = s.toString().replace("9", "");
									((EditText) findViewById(R.id.q12))
											.setText(s);
									((EditText) findViewById(R.id.q12))
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

		final Calendar c = Calendar.getInstance();
		dateYear = c.get(Calendar.YEAR);
		dateMonth = c.get(Calendar.MONTH);
		dateDay = c.get(Calendar.DAY_OF_MONTH);

		thisYear = c.get(Calendar.YEAR);
		thisMonth = c.get(Calendar.MONTH);
		thisDay = c.get(Calendar.DAY_OF_MONTH);

		hour = c.get(Calendar.HOUR_OF_DAY);
		min = c.get(Calendar.MINUTE);

		final ViewGroup time1 = (LinearLayout) findViewById(R.id.time1);
		final ViewGroup time2 = (LinearLayout) findViewById(R.id.time2);
		final ViewGroup time3 = (LinearLayout) findViewById(R.id.time3);
		final ViewGroup time4 = (LinearLayout) findViewById(R.id.time4);
		final ViewGroup time5 = (LinearLayout) findViewById(R.id.time5);
		ln25to27 = (LinearLayout) findViewById(R.id.ln25to27);

		/*
		 * time1.setVisibility(View.GONE); time2.setVisibility(View.GONE);
		 * time3.setVisibility(View.GONE); time4.setVisibility(View.GONE);
		 * time5.setVisibility(View.GONE);
		 */
		findViewById(R.id.q42_7Dayset).setVisibility(View.GONE);
		findViewById(R.id.q42_Weekset).setVisibility(View.GONE);
		findViewById(R.id.q43_7dayset).setVisibility(View.GONE);
		findViewById(R.id.q43_Weekset).setVisibility(View.GONE);

		q12et = (EditText) findViewById(R.id.q12);
		q13et = (EditText) findViewById(R.id.q13);
		q15et = (EditText) findViewById(R.id.q15);

		q15et.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				showDialog(DATE_DIALOG_ID);

				// set selected date into textview
				// q15et.setText(new StringBuilder().append(day).append(" ")
				// .append(GetMonth(month + 1)).append(" ").append(year)
				// .toString());

				// int daytodate = Calendar.DAY_OF_YEAR;

				return false;
			}
		});

		q15et.setOnKeyListener(new OnKeyListener() {

			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// showDialog(DATE_DIALOG_ID);
				return false;
			}
		});

		q17et = (EditText) findViewById(R.id.q17);

		q18dayet = (EditText) findViewById(R.id.q18days);
		q18monthset = (EditText) findViewById(R.id.q18months);

		q18dayet.setImeOptions(EditorInfo.IME_ACTION_DONE);
		q19et = (EditText) findViewById(R.id.q19);
		// q19et.setText("");
		q21ettime1et = (EditText) findViewById(R.id.q21time1);

		q21ettime2et = (EditText) findViewById(R.id.q21time2);
		q21ettime3et = (EditText) findViewById(R.id.q21time3);
		q21ettime4et = (EditText) findViewById(R.id.q21time4);
		q21ettime5et = (EditText) findViewById(R.id.q21time5);

		q26dayset = (EditText) findViewById(R.id.q26days);
		q26monthset = (EditText) findViewById(R.id.q26months);

		// q27_otheret

		radioGroup28 = (RadioGroup) findViewById(R.id.radioGroup28);

		radioGroup29 = (RadioGroup) findViewById(R.id.radioGroup29);
		rdogroup14 = (RadioGroup) findViewById(R.id.rdogroup14);
		rdogroup16 = (RadioGroup) findViewById(R.id.rdogroup16);
		radiogroup18 = (RadioGroup) findViewById(R.id.radiogroup18);
		radioGroup19 = (RadioGroup) findViewById(R.id.radioGroup19);

		radioG20time1 = (RadioGroup) findViewById(R.id.radioGroup20time1);

		radioG20time2 = (RadioGroup) findViewById(R.id.radioGroup20time2);
		radioG20time3 = (RadioGroup) findViewById(R.id.radioGroup20time3);
		radioG20time4 = (RadioGroup) findViewById(R.id.radioGroup20time4);
		radioG20time5 = (RadioGroup) findViewById(R.id.radioGroup20time5);

		q20_other_time1et = (EditText) findViewById(R.id.radioGroup2012time1_other);
		q20_other_time1et.setVisibility(View.GONE);
		// time 1
		radioG20time1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {

				RadioButton rb = (RadioButton) findViewById(group
						.getCheckedRadioButtonId());
				if (rb.getId() == R.id.radioGroup2012time1) {
					q20_other_time1et.setVisibility(View.VISIBLE);
				} else {
					q20_other_time1et.setVisibility(View.GONE);
				}

			}
		});

		q20_other_time2et = (EditText) findViewById(R.id.radioGroup2012time2_other);
		q20_other_time2et.setVisibility(View.GONE);
		radioG20time2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {

				RadioButton rb = (RadioButton) findViewById(group
						.getCheckedRadioButtonId());
				if (rb.getId() == R.id.radioGroup2012time2) {
					q20_other_time2et.setVisibility(View.VISIBLE);
				} else {
					q20_other_time2et.setVisibility(View.GONE);
				}

			}
		});

		q20_other_time3et = (EditText) findViewById(R.id.radioGroup2012time3_other);
		q20_other_time3et.setVisibility(View.GONE);
		radioG20time3.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {

				RadioButton rb = (RadioButton) findViewById(group
						.getCheckedRadioButtonId());
				if (rb.getId() == R.id.radioGroup2012time3) {
					q20_other_time3et.setVisibility(View.VISIBLE);
				} else {
					q20_other_time3et.setVisibility(View.GONE);
				}

			}
		});

		q20_other_time4et = (EditText) findViewById(R.id.radioGroup2012time4_other);
		q20_other_time4et.setVisibility(View.GONE);

		radioG20time4.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {

				RadioButton rb = (RadioButton) findViewById(group
						.getCheckedRadioButtonId());
				if (rb.getId() == R.id.radioGroup2012time4) {
					q20_other_time4et.setVisibility(View.VISIBLE);
				} else {
					q20_other_time4et.setVisibility(View.GONE);
				}

			}
		});

		q20_other_time5et = (EditText) findViewById(R.id.radioGroup2012time5_other);
		q20_other_time5et.setVisibility(View.GONE);
		radioG20time5.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {

				RadioButton rb = (RadioButton) findViewById(group
						.getCheckedRadioButtonId());
				if (rb.getId() == R.id.radioGroup2012time5) {
					q20_other_time5et.setVisibility(View.VISIBLE);
				} else {
					q20_other_time5et.setVisibility(View.GONE);
				}

			}
		});

		q27_otheret = (EditText) findViewById(R.id.q27_other);
		q27_otheret.setVisibility(View.GONE);

		radioGroup27 = (RadioGroup) findViewById(R.id.radioGroup27);

		radioGroup27.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {

				RadioButton rb = (RadioButton) findViewById(group
						.getCheckedRadioButtonId());
				if (rb.getId() == R.id.radioGroup2703) {
					q27_otheret.setVisibility(View.VISIBLE);
				} else {
					q27_otheret.setVisibility(View.GONE);
				}

			}
		});
		radioGroup29 = (RadioGroup) findViewById(R.id.radioGroup29);
		// Dont know for 21
		radioGroup2101time1 = (RadioButton) findViewById(R.id.radioGroup2101Dontknowtime1);
		radioGroup2101time2 = (RadioButton) findViewById(R.id.radioGroup2101Dontknowtime2);
		radioGroup2101time3 = (RadioButton) findViewById(R.id.radioGroup2101Dontknowtime3);
		radioGroup2101time4 = (RadioButton) findViewById(R.id.radioGroup2101Dontknowtime4);
		radioGroup2101time5 = (RadioButton) findViewById(R.id.radioGroup2101Dontknowtime5);

		radioGroup22 = (RadioGroup) findViewById(R.id.radioGroup22);
		((RadioGroup) findViewById(R.id.radioGroup22))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup arg0, int checkedId) {

						if (checkedId != -1) {
							switch (checkedId) {
							case R.id.radioGroup2201:
								((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup23))
										.findViewById(R.id.radioGroup2301))
										.setChecked(true);

								break;

							default:
								((RadioGroup) findViewById(R.id.radioGroup23))
										.clearCheck();
								break;
							}
						}

					}
				});

		radioGroup23 = (RadioGroup) findViewById(R.id.radioGroup23);
		((RadioGroup) findViewById(R.id.radioGroup23))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup arg0, int checkedId) {

						if (checkedId != -1) {
							switch (checkedId) {
							case R.id.radioGroup2303:
								if (((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup22))
										.findViewById(R.id.radioGroup2202))
										.isChecked()) {
									((RadioGroup) findViewById(R.id.radioGroup23))
											.clearCheck();
									CommonStaticClass.showMyAlert(con,
											"Message",
											"You can not select this");
								}

								break;

							case R.id.radioGroup2304:
								if (((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup22))
										.findViewById(R.id.radioGroup2202))
										.isChecked()) {
									((RadioGroup) findViewById(R.id.radioGroup23))
											.clearCheck();
									CommonStaticClass.showMyAlert(con,
											"Message",
											"You can not select this");
								}
								break;

							case R.id.radioGroup2305:
								if (((RadioButton) ((RadioGroup) findViewById(R.id.radioGroup22))
										.findViewById(R.id.radioGroup2202))
										.isChecked()) {
									((RadioGroup) findViewById(R.id.radioGroup23))
											.clearCheck();
									CommonStaticClass.showMyAlert(con,
											"Message",
											"You can not select this");
								}
								break;

							default:
								break;
							}
						}

					}
				});

		radioGroup24 = (RadioGroup) findViewById(R.id.radioGroup24);
		radioGroup25 = (RadioGroup) findViewById(R.id.radioGroup25);
		rdoq26Dontknow = (RadioButton) findViewById(R.id.q26Dontknow);

		radioGroup24.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if (checkedId != -1) {
					if (group.getCheckedRadioButtonId() == ((RadioButton) findViewById(R.id.radioGroup2402))
							.getId()
							|| group.getCheckedRadioButtonId() == ((RadioButton) findViewById(R.id.radioGroup2404))
									.getId()) {

						findViewById(R.id.textView5).setVisibility(View.GONE);
						radioGroup25.setVisibility(View.GONE);
						findViewById(R.id.textView10).setVisibility(View.GONE);
						findViewById(R.id.textView21).setVisibility(View.GONE);
						q26dayset.setVisibility(View.GONE);
						findViewById(R.id.textView22).setVisibility(View.GONE);
						q26monthset.setVisibility(View.GONE);
						rdoq26Dontknow.setVisibility(View.GONE);
						findViewById(R.id.textView7).setVisibility(View.GONE);
						radioGroup27.setVisibility(View.GONE);
						q27_otheret.setVisibility(View.GONE);

						findViewById(R.id.textView6)
								.setVisibility(View.VISIBLE);
						radioGroup28.setVisibility(View.VISIBLE);
						findViewById(R.id.textView77).setVisibility(
								View.VISIBLE);
						radioGroup29.setVisibility(View.VISIBLE);

						ln25to27.setVisibility(View.GONE);

					} else if (group.getCheckedRadioButtonId() == ((RadioButton) findViewById(R.id.radioGroup2403))
							.getId()) {

						findViewById(R.id.textView5).setVisibility(View.GONE);
						radioGroup25.setVisibility(View.GONE);
						findViewById(R.id.textView10).setVisibility(View.GONE);
						findViewById(R.id.textView21).setVisibility(View.GONE);
						q26dayset.setVisibility(View.GONE);
						findViewById(R.id.textView22).setVisibility(View.GONE);
						q26monthset.setVisibility(View.GONE);
						rdoq26Dontknow.setVisibility(View.GONE);
						findViewById(R.id.textView7).setVisibility(View.GONE);
						radioGroup27.setVisibility(View.GONE);
						q27_otheret.setVisibility(View.GONE);
						ln25to27.setVisibility(View.GONE);

						findViewById(R.id.textView6).setVisibility(View.GONE);
						radioGroup28.setVisibility(View.GONE);
						findViewById(R.id.textView77).setVisibility(View.GONE);
						radioGroup29.setVisibility(View.GONE);
					} else {
						findViewById(R.id.textView5)
								.setVisibility(View.VISIBLE);
						radioGroup25.setVisibility(View.VISIBLE);
						findViewById(R.id.textView10).setVisibility(
								View.VISIBLE);
						findViewById(R.id.textView21).setVisibility(
								View.VISIBLE);
						q26dayset.setVisibility(View.VISIBLE);
						findViewById(R.id.textView22).setVisibility(
								View.VISIBLE);
						q26monthset.setVisibility(View.VISIBLE);
						rdoq26Dontknow.setVisibility(View.VISIBLE);
						findViewById(R.id.textView7)
								.setVisibility(View.VISIBLE);
						radioGroup27.setVisibility(View.VISIBLE);
						q27_otheret.setVisibility(View.VISIBLE);

						findViewById(R.id.textView6)
								.setVisibility(View.VISIBLE);
						radioGroup28.setVisibility(View.VISIBLE);
						findViewById(R.id.textView77).setVisibility(
								View.VISIBLE);
						radioGroup29.setVisibility(View.VISIBLE);

						ln25to27.setVisibility(View.VISIBLE);
					}
				}
			}
		});

		setCurrentDateOnView();

		q17et.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			public void afterTextChanged(Editable s) {

				if (q17et.getText().toString() != null) {
					if (q17et.getText().toString().length() > 0) {
						if (Integer.parseInt(q17et.getText().toString()) > 1200) {
							CommonStaticClass.showMyAlert(con, "Message",
									"Age should be less than 1200 days");
						} else if (Integer.parseInt(q17et.getText().toString()) < 300) {
							radioGroup30E.setVisibility(View.GONE);
							radioGroup31E.setVisibility(View.GONE);
							radioGroup32E.setVisibility(View.GONE);
							radioGroup34E.setVisibility(View.GONE);
							radioGroup35E.setVisibility(View.GONE);
							radioGroup36E.setVisibility(View.GONE);
							radioGroup37E.setVisibility(View.GONE);
							radioGroup38E.setVisibility(View.GONE);
							radioGroup39E.setVisibility(View.GONE);
							radioGroup40E.setVisibility(View.GONE);
							radioGroup41E.setVisibility(View.GONE);
						} else {
							radioGroup30E.setVisibility(View.VISIBLE);
							radioGroup31E.setVisibility(View.VISIBLE);
							radioGroup32E.setVisibility(View.VISIBLE);
							radioGroup34E.setVisibility(View.VISIBLE);
							radioGroup35E.setVisibility(View.VISIBLE);
							radioGroup36E.setVisibility(View.VISIBLE);
							radioGroup37E.setVisibility(View.VISIBLE);
							radioGroup38E.setVisibility(View.VISIBLE);
							radioGroup39E.setVisibility(View.VISIBLE);
							radioGroup40E.setVisibility(View.VISIBLE);
							radioGroup41E.setVisibility(View.VISIBLE);
						}
					}

				}

			}
		});

		radiogroup18.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {

				q18dayet.setText("");
				q18monthset.setText("");

			}
		});

		q18dayet.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				q18monthset.setText("");
				((RadioButton) findViewById(R.id.radiogroup1801))
						.setChecked(false);
				((RadioButton) findViewById(R.id.radiogroup1802))
						.setChecked(false);

				if (radiogroup18.getCheckedRadioButtonId() != -1) {
					radiogroup18.clearCheck();
				}
				return false;
			}
		});

		q18monthset.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				q18dayet.setText("");
				((RadioButton) findViewById(R.id.radiogroup1801))
						.setChecked(false);
				((RadioButton) findViewById(R.id.radiogroup1802))
						.setChecked(false);

				// if (((RadioButton)
				// radiogroup18.findViewById(radiogroup18.getCheckedRadioButtonId())).isChecked())
				if (radiogroup18.getCheckedRadioButtonId() != -1) {
					radiogroup18.clearCheck();
				}
				return false;
			}
		});

		radioGroup19.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId != -1) {
					if (((RadioButton) radioGroup19.findViewById(group
							.getCheckedRadioButtonId())).isChecked()) {
						q19et.setText("");
						time1.setVisibility(View.GONE);
						time2.setVisibility(View.GONE);
						time3.setVisibility(View.GONE);
						time4.setVisibility(View.GONE);
						time5.setVisibility(View.GONE);
					}
				}
			}
		});

		/*
		 * radioGroup19.setOnTouchListener(new OnTouchListener() {
		 * 
		 * @Override public boolean onTouch(View v, MotionEvent event) {
		 * 
		 * q19et.setText(""); time1.setVisibility(View.GONE);
		 * time2.setVisibility(View.GONE); time3.setVisibility(View.GONE);
		 * time4.setVisibility(View.GONE); time5.setVisibility(View.GONE);
		 * return false; } });
		 */

		/*
		 * radioGroup19.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { q19et.setText("");
		 * time1.setVisibility(View.GONE); time2.setVisibility(View.GONE);
		 * time3.setVisibility(View.GONE); time4.setVisibility(View.GONE);
		 * time5.setVisibility(View.GONE);
		 * 
		 * } });
		 */
		q19et.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				if (s.length() > 0) {
					if (radioGroup19.getCheckedRadioButtonId() != -1) {
						radioGroup19.clearCheck();
					}
					time1.setVisibility(View.GONE);
					time2.setVisibility(View.GONE);
					time3.setVisibility(View.GONE);
					time4.setVisibility(View.GONE);
					time5.setVisibility(View.GONE);
				}
			}

			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

				q19et = (EditText) findViewById(R.id.q19);

				String xx = q19et.getText().toString();
				if (xx.length() > 0) {

					if (radioGroup19.getCheckedRadioButtonId() != -1) {
						radioGroup19.clearCheck();
					}
					if (Integer.parseInt(xx) == 1)
						time1.setVisibility(View.VISIBLE);
					else if (Integer.parseInt(xx) == 2) {
						time1.setVisibility(View.VISIBLE);
						time2.setVisibility(View.VISIBLE);
					} else if (Integer.parseInt(xx) == 3) {
						time1.setVisibility(View.VISIBLE);
						time2.setVisibility(View.VISIBLE);
						time3.setVisibility(View.VISIBLE);
					} else if (Integer.parseInt(xx) == 4) {
						time1.setVisibility(View.VISIBLE);
						time2.setVisibility(View.VISIBLE);
						time3.setVisibility(View.VISIBLE);
						time4.setVisibility(View.VISIBLE);
					} else if (Integer.parseInt(xx) == 5) {
						time1.setVisibility(View.VISIBLE);
						time2.setVisibility(View.VISIBLE);
						time3.setVisibility(View.VISIBLE);
						time4.setVisibility(View.VISIBLE);
						time5.setVisibility(View.VISIBLE);
					}
				}

				else {
					if (xx.length() > 0) {
						if (radioGroup19.getCheckedRadioButtonId() != -1) {
							radioGroup19.clearCheck();
						}
						time1.setVisibility(View.GONE);
						time2.setVisibility(View.GONE);
						time3.setVisibility(View.GONE);
						time4.setVisibility(View.GONE);
						time5.setVisibility(View.GONE);
					}
				}
			}
		});

		/*
		 * q19et.setOnKeyListener(new OnKeyListener() {
		 * 
		 * public boolean onKey(View arg0, int arg1, KeyEvent arg2) { // TODO
		 * Auto-generated method stub // TODO Auto-generated method stub
		 * radioGroup19.clearCheck(); time1.setVisibility(View.GONE);
		 * time2.setVisibility(View.GONE); time3.setVisibility(View.GONE);
		 * time4.setVisibility(View.GONE); time5.setVisibility(View.GONE); q19et
		 * = (EditText) findViewById(R.id.q19);
		 * 
		 * String xx = q19et.getText().toString(); if (xx.length() > 0) if
		 * (Integer.parseInt(xx) == 1) time1.setVisibility(View.VISIBLE); else
		 * if (Integer.parseInt(xx) == 2) { time1.setVisibility(View.VISIBLE);
		 * time2.setVisibility(View.VISIBLE); } else if (Integer.parseInt(xx) ==
		 * 3) { time1.setVisibility(View.VISIBLE);
		 * time2.setVisibility(View.VISIBLE); time3.setVisibility(View.VISIBLE);
		 * } else if (Integer.parseInt(xx) == 4) {
		 * time1.setVisibility(View.VISIBLE); time2.setVisibility(View.VISIBLE);
		 * time3.setVisibility(View.VISIBLE); time4.setVisibility(View.VISIBLE);
		 * } else if (Integer.parseInt(xx) == 5) {
		 * time1.setVisibility(View.VISIBLE); time2.setVisibility(View.VISIBLE);
		 * time3.setVisibility(View.VISIBLE); time4.setVisibility(View.VISIBLE);
		 * time5.setVisibility(View.VISIBLE); }
		 * 
		 * return false; } });
		 */

		q21ettime1et.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				if (radioGroup2101time1 != null)

				{

					radioGroup2101time1.setChecked(false);

				}
			}

			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (radioGroup2101time1 != null)
					radioGroup2101time1.setChecked(false);

			}
		});

		q21ettime2et.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				if (radioGroup2101time2 != null)
					radioGroup2101time2.setChecked(false);

			}

			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (radioGroup2101time2 != null)
					radioGroup2101time2.setChecked(false);

			}
		});

		q21ettime3et.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				if (radioGroup2101time3 != null)
					radioGroup2101time3.setChecked(false);
			}

			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (radioGroup2101time3 != null)
					radioGroup2101time3.setChecked(false);
			}
		});
		q21ettime4et.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				if (radioGroup2101time4 != null)
					radioGroup2101time4.setChecked(false);
			}

			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (radioGroup2101time4 != null)
					radioGroup2101time4.setChecked(false);
			}
		});

		q21ettime5et.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				if (radioGroup2101time5 != null)
					radioGroup2101time5.setChecked(false);
			}

			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (radioGroup2101time5 != null)
					radioGroup2101time5.setChecked(false);
			}
		});

		radioGroup2101time1.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				q21ettime1et.setText("");
				return false;
			}
		});

		radioGroup2101time2.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				q21ettime2et.setText("");
				return false;
			}
		});
		radioGroup2101time3.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				q21ettime3et.setText("");
				return false;
			}
		});
		radioGroup2101time4.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				q21ettime4et.setText("");
				return false;
			}
		});
		radioGroup2101time5.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				q21ettime5et.setText("");
				return false;
			}
		});

		rdoq26Dontknow.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				q26dayset.setText("");
				q26monthset.setText("");

			}
		});
		q26dayset.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {

				rdoq26Dontknow.setChecked(false);
				return false;
			}
		});

		q26monthset.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				rdoq26Dontknow.setChecked(false);
				return false;
			}
		});

		btnSave = (Button) findViewById(R.id.btnbDone);
		time1.setVisibility(View.GONE);
		time2.setVisibility(View.GONE);
		time3.setVisibility(View.GONE);
		time4.setVisibility(View.GONE);
		time5.setVisibility(View.GONE);

	}

	private void loadSymptomView() {

		/*
		 * findViewById(R.id.q33Aet).setVisibility(View.GONE);
		 * findViewById(R.id.q33Bet).setVisibility(View.GONE);
		 * findViewById(R.id.q33Cet).setVisibility(View.GONE);
		 * findViewById(R.id.q33Det).setVisibility(View.GONE);
		 * findViewById(R.id.q33Eet).setVisibility(View.GONE);
		 */

		radioGroup30A = (RadioGroup) findViewById(R.id.radioGroup30A);
		radioGroup30B = (RadioGroup) findViewById(R.id.radioGroup30B);
		radioGroup30C = (RadioGroup) findViewById(R.id.radioGroup30C);

		radioGroup30D = (RadioGroup) findViewById(R.id.radioGroup30D);
		radioGroup30E = (RadioGroup) findViewById(R.id.radioGroup30E);

		radioGroup31A = (RadioGroup) findViewById(R.id.radioGroup31A);
		radioGroup31B = (RadioGroup) findViewById(R.id.radioGroup31B);
		radioGroup31C = (RadioGroup) findViewById(R.id.radioGroup31C);
		radioGroup31D = (RadioGroup) findViewById(R.id.radioGroup31D);
		radioGroup31E = (RadioGroup) findViewById(R.id.radioGroup31E);

		radioGroup32A = (RadioGroup) findViewById(R.id.radioGroup32A);
		radioGroup32B = (RadioGroup) findViewById(R.id.radioGroup32B);
		radioGroup32C = (RadioGroup) findViewById(R.id.radioGroup32C);
		radioGroup32D = (RadioGroup) findViewById(R.id.radioGroup32D);
		radioGroup32E = (RadioGroup) findViewById(R.id.radioGroup32E);

		setRadioButtonCheckstatetoYes(radioGroup30C, radioGroup30B,
				radioGroup30A, radioGroup30D, radioGroup30E);

		// q31end
		setRadioButtonCheckstatetoYes(radioGroup31C, radioGroup31B,
				radioGroup31A, radioGroup31D, radioGroup31E);

		radioGroup31D.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup arg0, int arg1) {

				int v = arg0.getCheckedRadioButtonId();
				switch (v) {
				case R.id.radio0:
					findViewById(R.id.textView19).setVisibility(View.VISIBLE);
					findViewById(R.id.q42_7Dayset).setVisibility(View.VISIBLE);

					findViewById(R.id.textView21).setVisibility(View.VISIBLE);
					findViewById(R.id.q43_7dayset).setVisibility(View.VISIBLE);
					// findViewById(R.id.q43_2Weekset).setVisibility(View.VISIBLE);

					break;
				case R.id.radio1:
					findViewById(R.id.textView19).setVisibility(View.GONE);
					findViewById(R.id.q42_7Dayset).setVisibility(View.GONE);

					findViewById(R.id.textView21).setVisibility(View.GONE);
					findViewById(R.id.q43_7dayset).setVisibility(View.GONE);
					// findViewById(R.id.q43_2Weekset).setVisibility(View.GONE);
					break;

				case R.id.radio2:
					findViewById(R.id.textView19).setVisibility(View.GONE);
					findViewById(R.id.q42_7Dayset).setVisibility(View.GONE);

					findViewById(R.id.textView21).setVisibility(View.GONE);
					findViewById(R.id.q43_7dayset).setVisibility(View.GONE);
					// findViewById(R.id.q43_2Weekset).setVisibility(View.GONE);
					break;

				default:
					break;
				}

			}
		});

		radioGroup31E.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup arg0, int arg1) {

				int v = arg0.getCheckedRadioButtonId();
				switch (v) {
				case R.id.radio0:
					findViewById(R.id.textView19).setVisibility(View.VISIBLE);
					findViewById(R.id.q42_Weekset).setVisibility(View.VISIBLE);
					findViewById(R.id.q43_Weekset).setVisibility(View.VISIBLE);

					break;
				case R.id.radio1:
					findViewById(R.id.textView19).setVisibility(View.GONE);
					findViewById(R.id.q42_Weekset).setVisibility(View.GONE);
					findViewById(R.id.q43_Weekset).setVisibility(View.GONE);

					break;

				case R.id.radio2:
					findViewById(R.id.textView19).setVisibility(View.GONE);
					findViewById(R.id.q42_Weekset).setVisibility(View.GONE);
					findViewById(R.id.q43_Weekset).setVisibility(View.GONE);
					break;

				default:
					break;
				}

			}
		});

		setRadioButtonCheckstatetoYesq32(radioGroup32C, radioGroup32B,
				radioGroup32A, radioGroup32D, radioGroup32E);

		/*
		 * radioGroup32D.setOnCheckedChangeListener(new
		 * OnCheckedChangeListener() { public void onCheckedChanged(RadioGroup
		 * group, int checkedId) {
		 * 
		 * switch (checkedId) { case R.id.radio0:
		 * findViewById(R.id.q33Det).setVisibility(View.VISIBLE); break;
		 * 
		 * case R.id.radio1: findViewById(R.id.q33Det).setVisibility(View.GONE);
		 * break;
		 * 
		 * case R.id.radio2: findViewById(R.id.q33Det).setVisibility(View.GONE);
		 * break;
		 * 
		 * default: break; }
		 * 
		 * } });
		 * 
		 * radioGroup32E.setOnCheckedChangeListener(new
		 * OnCheckedChangeListener() { public void onCheckedChanged(RadioGroup
		 * group, int checkedId) {
		 * 
		 * switch (checkedId) { case R.id.radio0:
		 * findViewById(R.id.q33Eet).setVisibility(View.VISIBLE); break;
		 * 
		 * case R.id.radio1: findViewById(R.id.q33Eet).setVisibility(View.GONE);
		 * break;
		 * 
		 * case R.id.radio2: findViewById(R.id.q33Eet).setVisibility(View.GONE);
		 * break;
		 * 
		 * default: break; }
		 * 
		 * } });
		 */

		radioGroup34A = (RadioGroup) findViewById(R.id.radioGroup34A);
		radioGroup34B = (RadioGroup) findViewById(R.id.radioGroup34B);
		radioGroup34C = (RadioGroup) findViewById(R.id.radioGroup34C);
		radioGroup34D = (RadioGroup) findViewById(R.id.radioGroup34D);
		radioGroup34E = (RadioGroup) findViewById(R.id.radioGroup34E);

		setRadioButtonCheckstatetoYes(radioGroup34C, radioGroup34B,
				radioGroup34A, radioGroup34D, radioGroup34E);

		radioGroup35A = (RadioGroup) findViewById(R.id.radioGroup35A);
		radioGroup35B = (RadioGroup) findViewById(R.id.radioGroup35B);
		radioGroup35C = (RadioGroup) findViewById(R.id.radioGroup35C);
		radioGroup35D = (RadioGroup) findViewById(R.id.radioGroup35D);
		radioGroup35E = (RadioGroup) findViewById(R.id.radioGroup35E);

		setRadioButtonCheckstatetoYes(radioGroup35C, radioGroup35B,
				radioGroup35A, radioGroup35D, radioGroup35E);

		radioGroup36A = (RadioGroup) findViewById(R.id.radioGroup36A);
		radioGroup36B = (RadioGroup) findViewById(R.id.radioGroup36B);
		radioGroup36C = (RadioGroup) findViewById(R.id.radioGroup36C);
		radioGroup36D = (RadioGroup) findViewById(R.id.radioGroup36D);
		radioGroup36E = (RadioGroup) findViewById(R.id.radioGroup36E);

		setRadioButtonCheckstatetoYes(radioGroup36C, radioGroup36B,
				radioGroup36A, radioGroup36D, radioGroup36E);

		radioGroup37A = (RadioGroup) findViewById(R.id.radioGroup37A);
		radioGroup37B = (RadioGroup) findViewById(R.id.radioGroup37B);
		radioGroup37C = (RadioGroup) findViewById(R.id.radioGroup37C);
		radioGroup37D = (RadioGroup) findViewById(R.id.radioGroup37D);
		radioGroup37E = (RadioGroup) findViewById(R.id.radioGroup37E);

		setRadioButtonCheckstatetoYes(radioGroup37C, radioGroup37B,
				radioGroup37A, radioGroup37D, radioGroup37E);

		radioGroup38A = (RadioGroup) findViewById(R.id.radioGroup38A);
		radioGroup38B = (RadioGroup) findViewById(R.id.radioGroup38B);
		radioGroup38C = (RadioGroup) findViewById(R.id.radioGroup38C);
		radioGroup38D = (RadioGroup) findViewById(R.id.radioGroup38D);
		radioGroup38E = (RadioGroup) findViewById(R.id.radioGroup38E);

		setRadioButtonCheckstatetoYes(radioGroup38C, radioGroup38B,
				radioGroup38A, radioGroup38D, radioGroup38E);

		radioGroup39A = (RadioGroup) findViewById(R.id.radioGroup39A);
		radioGroup39B = (RadioGroup) findViewById(R.id.radioGroup39B);
		radioGroup39C = (RadioGroup) findViewById(R.id.radioGroup39C);
		radioGroup39D = (RadioGroup) findViewById(R.id.radioGroup39D);
		radioGroup39E = (RadioGroup) findViewById(R.id.radioGroup39E);

		setRadioButtonCheckstatetoYes(radioGroup39C, radioGroup39B,
				radioGroup39A, radioGroup39D, radioGroup39E);

		radioGroup40A = (RadioGroup) findViewById(R.id.radioGroup40A);
		radioGroup40B = (RadioGroup) findViewById(R.id.radioGroup40B);
		radioGroup40C = (RadioGroup) findViewById(R.id.radioGroup40C);
		radioGroup40D = (RadioGroup) findViewById(R.id.radioGroup40D);
		radioGroup40E = (RadioGroup) findViewById(R.id.radioGroup40E);

		setRadioButtonCheckstatetoYes(radioGroup40C, radioGroup40B,
				radioGroup40A, radioGroup40D, radioGroup40E);

		radioGroup41A = (RadioGroup) findViewById(R.id.radioGroup41A);
		radioGroup41B = (RadioGroup) findViewById(R.id.radioGroup41B);
		radioGroup41C = (RadioGroup) findViewById(R.id.radioGroup41C);
		radioGroup41D = (RadioGroup) findViewById(R.id.radioGroup41D);
		radioGroup41E = (RadioGroup) findViewById(R.id.radioGroup41E);

		setRadioButtonCheckstatetoYes(radioGroup41C, radioGroup41B,
				radioGroup41A, radioGroup41D, radioGroup41E);

		q42_Dayset = (EditText) findViewById(R.id.q42_7Dayset);
		q42_weekset = (EditText) findViewById(R.id.q42_Weekset);

		q43_Dayset = (EditText) findViewById(R.id.q43_7dayset);
		q43_weekset = (EditText) findViewById(R.id.q43_Weekset);
	}

	/*
	 * private void setRadioButtonCheckstatetoYes(final RadioGroup C, final
	 * RadioGroup B, final RadioGroup A, final RadioGroup D, final RadioGroup E)
	 * { C.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	 * 
	 * public void onCheckedChanged(RadioGroup group, int checkedId) {
	 * 
	 * if (((RadioGroup) C).getCheckedRadioButtonId() == C
	 * .findViewById(R.id.radio0).getId()) { if (((RadioGroup)
	 * B).getCheckedRadioButtonId() == B .findViewById(R.id.radio0).getId()) {
	 * if (((RadioGroup) A).getCheckedRadioButtonId() == A
	 * .findViewById(R.id.radio0).getId()) { ((RadioButton)
	 * D.getChildAt(0)).setChecked(true); ((RadioButton)
	 * E.getChildAt(0)).setChecked(true); } }
	 * 
	 * }
	 * 
	 * } });
	 * 
	 * B.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	 * 
	 * public void onCheckedChanged(RadioGroup group, int checkedId) {
	 * 
	 * if (((RadioGroup) B).getCheckedRadioButtonId() == B
	 * .findViewById(R.id.radio0).getId()) { if (((RadioGroup)
	 * C).getCheckedRadioButtonId() == C .findViewById(R.id.radio0).getId()) {
	 * if (((RadioGroup) A).getCheckedRadioButtonId() == A
	 * .findViewById(R.id.radio0).getId()) { ((RadioButton)
	 * D.getChildAt(0)).setChecked(true); ((RadioButton)
	 * E.getChildAt(0)).setChecked(true); } }
	 * 
	 * }
	 * 
	 * } }); A.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	 * 
	 * public void onCheckedChanged(RadioGroup group, int checkedId) {
	 * 
	 * if (((RadioGroup) A).getCheckedRadioButtonId() == A
	 * .findViewById(R.id.radio0).getId()) { if (((RadioGroup)
	 * B).getCheckedRadioButtonId() == B .findViewById(R.id.radio0).getId()) {
	 * if (((RadioGroup) C).getCheckedRadioButtonId() == C
	 * .findViewById(R.id.radio0).getId()) { ((RadioButton)
	 * D.getChildAt(0)).setChecked(true); ((RadioButton)
	 * E.getChildAt(0)).setChecked(true); } }
	 * 
	 * }
	 * 
	 * } }); }
	 */
	private void setRadioButtonCheckstatetoYes(final RadioGroup C,
			final RadioGroup B, final RadioGroup A, final RadioGroup D,
			final RadioGroup E) {
		C.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {

				if (((RadioGroup) C).getCheckedRadioButtonId() == C
						.findViewById(R.id.radio0).getId()
				// {
						|| (((RadioGroup) B).getCheckedRadioButtonId() == B
								.findViewById(R.id.radio0).getId())
						// {
						|| ((RadioGroup) A).getCheckedRadioButtonId() == A
								.findViewById(R.id.radio0).getId()) {
					((RadioButton) D.getChildAt(0)).setChecked(true);
					((RadioButton) E.getChildAt(0)).setChecked(true);

					if (q17et.getText().toString() != null) {
						if (q17et.getText().toString().length() > 0) {
							if ((Integer.parseInt(q17et.getText().toString()) < 14)) {
								E.clearCheck();
							}
						}
					}

				}
				// }

				// }

			}
		});

		B.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {

				if (((RadioGroup) B).getCheckedRadioButtonId() == B
						.findViewById(R.id.radio0).getId()
						|| (((RadioGroup) C).getCheckedRadioButtonId() == C
								.findViewById(R.id.radio0).getId())
						|| ((RadioGroup) A).getCheckedRadioButtonId() == A
								.findViewById(R.id.radio0).getId()) {
					((RadioButton) D.getChildAt(0)).setChecked(true);
					((RadioButton) E.getChildAt(0)).setChecked(true);

					if (q17et.getText().toString() != null) {
						if (q17et.getText().toString().length() > 0) {
							if ((Integer.parseInt(q17et.getText().toString()) < 14)) {
								E.clearCheck();
							}
						}
					}
				}

			}
		});
		A.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {

				if (((RadioGroup) A).getCheckedRadioButtonId() == A
						.findViewById(R.id.radio0).getId()
						|| (((RadioGroup) B).getCheckedRadioButtonId() == B
								.findViewById(R.id.radio0).getId())
						|| ((RadioGroup) C).getCheckedRadioButtonId() == C
								.findViewById(R.id.radio0).getId()) {
					((RadioButton) D.getChildAt(0)).setChecked(true);
					((RadioButton) E.getChildAt(0)).setChecked(true);

					if (q17et.getText().toString() != null) {
						if (q17et.getText().toString().length() > 0) {
							if ((Integer.parseInt(q17et.getText().toString()) < 14)) {
								E.clearCheck();
							}
						}
					}
				}
			}
		});

		D.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {

				if (((RadioGroup) D).getCheckedRadioButtonId() == D
						.findViewById(R.id.radio0).getId()) {
					((RadioButton) E.getChildAt(0)).setChecked(true);

					if (q17et.getText().toString() != null) {
						if (q17et.getText().toString().length() > 0) {
							if ((Integer.parseInt(q17et.getText().toString()) < 14)) {
								E.clearCheck();
							}
						}
					}
				}
			}
		});

	}

	private void setRadioButtonCheckstatetoYesq32(final RadioGroup C,
			final RadioGroup B, final RadioGroup A, final RadioGroup D,
			final RadioGroup E) {
		C.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {

				if (((RadioGroup) C).getCheckedRadioButtonId() == C
						.findViewById(R.id.radio0).getId()
						|| (((RadioGroup) B).getCheckedRadioButtonId() == B
								.findViewById(R.id.radio0).getId())
						|| ((RadioGroup) A).getCheckedRadioButtonId() == A
								.findViewById(R.id.radio0).getId()) {

					((RadioButton) D.getChildAt(0)).setChecked(true);
					((RadioButton) E.getChildAt(0)).setChecked(true);

					if (q17et.getText().toString() != null) {
						if (q17et.getText().toString().length() > 0) {
							if ((Integer.parseInt(q17et.getText().toString()) < 14)) {
								E.clearCheck();
							}
						}
					}
				}
			}
		});

		B.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {

				if (((RadioGroup) B).getCheckedRadioButtonId() == B
						.findViewById(R.id.radio0).getId()

						|| ((RadioGroup) C).getCheckedRadioButtonId() == C
								.findViewById(R.id.radio0).getId()
						|| ((RadioGroup) A).getCheckedRadioButtonId() == A
								.findViewById(R.id.radio0).getId()) {
					((RadioButton) D.getChildAt(0)).setChecked(true);
					((RadioButton) E.getChildAt(0)).setChecked(true);

					if (q17et.getText().toString() != null) {
						if (q17et.getText().toString().length() > 0) {
							if ((Integer.parseInt(q17et.getText().toString()) < 14)) {
								E.clearCheck();
							}
						}
					}
				}

			}

		});
		A.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {

				if (((RadioGroup) A).getCheckedRadioButtonId() == A
						.findViewById(R.id.radio0).getId()
						|| ((RadioGroup) B).getCheckedRadioButtonId() == B
								.findViewById(R.id.radio0).getId()
						|| ((RadioGroup) C).getCheckedRadioButtonId() == C
								.findViewById(R.id.radio0).getId()) {
					((RadioButton) D.getChildAt(0)).setChecked(true);
					((RadioButton) E.getChildAt(0)).setChecked(true);

					if (q17et.getText().toString() != null) {
						if (q17et.getText().toString().length() > 0) {
							if ((Integer.parseInt(q17et.getText().toString()) < 14)) {
								E.clearCheck();
							}
						}
					}
				}

			}
		});

	}

	public void resetEveryValue() {

		ViewGroup group = (LinearLayout) findViewById(R.id.rootView);
		ViewGroupReferences(group);

		q12 = "";
		q13 = "";
		q14 = "";
		q15 = "";
		q16 = "";
		q17 = "";
		q18_days = "";
		q18_months = "";
		q18 = "";
		q19_no_of_time = "";
		q19 = "";

		q20_time1 = "";
		q20_other_time1 = "";
		q21_days_time1 = "";
		q20_time2 = "";
		q20_other_time2 = "";
		q21_days_time2 = "";
		q20_time3 = "";
		q20_other_time3 = "";
		q21_days_time3 = "";
		q20_time4 = "";
		q20_other_time4 = "";
		q21_days_time4 = "";
		q20_time5 = "";
		q20_other_time5 = "";
		q21_days_time5 = "";
		q22 = "";
		q23 = "";
		q24 = "";
		q25 = "";
		q26_days = "";
		q26_months = "";
		q26 = "";
		q27 = "";
		q27_other = "";
		q28 = "";
		q29 = "";

	}

	protected void ViewGroupReferences(ViewGroup viewGroup) {
		int nrOfChildren = viewGroup.getChildCount();
		for (int i = 0; i < nrOfChildren; i++) {
			View view = viewGroup.getChildAt(i);
			ViewReferences(view);
			if (view instanceof ViewGroup) {
				ViewGroupReferences((ViewGroup) view);
			}
		}
		System.gc();
	}

	protected void ViewReferences(View view) {
		// set all listeners to null (not every view and not every API level
		// supports the methods)

		if (view instanceof EditText) {
			((EditText) view).setText("");
		}
		if (view instanceof Spinner) {
			((Spinner) view).setSelection(0);
		}

		if (view instanceof RadioButton) {
			((RadioButton) view).setChecked(false);
		}
	}

	protected boolean ViewGroupReferencesForvalidation(ViewGroup viewGroup,
			boolean alltrue) {

		if (alltrue == false) {
			return alltrue = false;
		}

		/*
		 * if(viewGroup.getVisibility() == View.GONE) {
		 * 
		 * return alltrue = true;
		 * 
		 * }
		 */

		int nrOfChildren = viewGroup.getChildCount();
		for (int i = 0; i < nrOfChildren; i++) {
			View view = viewGroup.getChildAt(i);
			if (view instanceof ViewGroup) {

				if (view.getVisibility() == View.GONE) {
					alltrue = true;
					continue;
				}

				/*
				 * if (view.getVisibility() == View.GONE) alltrue = true; else
				 */
				alltrue = ViewGroupReferencesForvalidation((ViewGroup) view,
						alltrue);

				if (alltrue == false) {
					return alltrue = false;
				}
			} else {
				alltrue = ViewGroupReferencesForvalidation(view, alltrue);

				if (alltrue == false) {
					return alltrue = false;

				}
			}

		}
		System.gc();
		// Log.e("All true", String.valueOf(alltrue));
		return alltrue;

	}

	protected boolean ViewGroupReferencesForvalidation(View view,
			boolean alltrue) {

		if (view.getVisibility() == View.GONE) {

			return alltrue = true;

		}

		if (view instanceof EditText) {

			if (!(((EditText) view).getText().toString().length() > 0)) {

				switch (view.getId()) {
				case R.id.q12:

					if (((EditText) view).getText().toString().length() <= 0) {
						Log.e("q12", String.valueOf(alltrue));
						return alltrue = false;

					}
					break;
				case R.id.q13:

					if (((EditText) view).getText().toString().length() <= 0) {
						return alltrue = false;

					}
					break;

				case R.id.q15:

					if (((EditText) view).getText().toString().length() <= 0) {
						return alltrue = false;

					}
					break;

				case R.id.q17:

					if (((EditText) view).getText().toString().length() <= 0) {
						return alltrue = false;

					}
					break;

				case R.id.q18days:

					if (((EditText) view).getText().toString().length() <= 0) {
						if (((EditText) findViewById(R.id.q18months)).getText()
								.toString().length() <= 0)
							return alltrue = false;

					}
				case R.id.q18months:

					if (((EditText) view).getText().toString().length() <= 0) {
						if (((EditText) findViewById(R.id.q18days)).getText()
								.toString().length() <= 0)
							return alltrue = false;

					}
					break;

				default:
					break;
				}
			}
		}

		else if (view instanceof RadioButton) {

			switch (view.getId()) {
			case R.id.q26Dontknow:
				if (rdoq26Dontknow.isChecked() == false) {
					if (q26dayset.getText().toString().length() <= 0
							|| q26monthset.getText().toString().length() <= 0)

					{
						return alltrue = false;
					}

				}
				break;
			}
		}

		else if (view instanceof RadioGroup) {

			switch (view.getId()) {
			case R.id.radioGroup29:
				if (radioGroup29.indexOfChild(findViewById(radioGroup29
						.getCheckedRadioButtonId())) == -1)
					return alltrue = false;
				break;

			case R.id.radioGroup28:
				if (radioGroup28.indexOfChild(findViewById(radioGroup28
						.getCheckedRadioButtonId())) == -1)
					return alltrue = false;
				break;

			case R.id.radioGroup27:
				if (radioGroup27.indexOfChild(findViewById(radioGroup27
						.getCheckedRadioButtonId())) == -1)
					return alltrue = false;
				break;

			case R.id.radioGroup25:
				if (radioGroup25.indexOfChild(findViewById(radioGroup25
						.getCheckedRadioButtonId())) == -1)
					return alltrue = false;
				break;

			case R.id.radioGroup24:
				if (radioGroup24.indexOfChild(findViewById(radioGroup24
						.getCheckedRadioButtonId())) == -1)
					return alltrue = false;
				break;

			case R.id.radioGroup23:
				if (radioGroup24.indexOfChild(findViewById(radioGroup24
						.getCheckedRadioButtonId())) == -1)
					return alltrue = false;
				break;

			case R.id.radioGroup22:
				if (radioGroup24.indexOfChild(findViewById(radioGroup24
						.getCheckedRadioButtonId())) == -1)
					return alltrue = false;
				break;

			case R.id.radioGroup19:
				if (radioGroup19.indexOfChild(findViewById(radioGroup19
						.getCheckedRadioButtonId())) == -1) {
					// check text field value
					if (q19et.getText().toString().length() <= 0) {
						return alltrue = false;
					}
				}

				break;

			case R.id.radiogroup18:
				if (radiogroup18.indexOfChild(findViewById(radiogroup18
						.getCheckedRadioButtonId())) == -1) {

					// check text field value
					if (q18dayet.getText().toString().length() <= 0
							|| q18monthset.getText().toString().length() <= 0) {
						return alltrue = false;
					}
				}

				break;

			case R.id.rdogroup16:
				if (rdogroup16.indexOfChild(findViewById(rdogroup16
						.getCheckedRadioButtonId())) == -1)
					return alltrue = false;
				break;

			case R.id.rdogroup14:
				if (rdogroup14.indexOfChild(findViewById(rdogroup14
						.getCheckedRadioButtonId())) == -1)
					return alltrue = false;
				break;

			// for q30 to 41
			/*
			 * case R.id.radioGroup30A: if
			 * (radioGroup30A.indexOfChild(radioGroup30A
			 * .findViewById(radioGroup30A.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup30B: if
			 * (radioGroup30A.indexOfChild(radioGroup30B
			 * .findViewById(radioGroup30B.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup30C: if
			 * (radioGroup30C.indexOfChild(radioGroup30C
			 * .findViewById(radioGroup30C.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup30D: if
			 * (radioGroup30D.indexOfChild(radioGroup30D
			 * .findViewById(radioGroup30D.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup31A: if
			 * (radioGroup31A.indexOfChild(radioGroup31A
			 * .findViewById(radioGroup31A.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup31B: if
			 * (radioGroup31A.indexOfChild(radioGroup31B
			 * .findViewById(radioGroup31B.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup31C: if
			 * (radioGroup31C.indexOfChild(radioGroup31C
			 * .findViewById(radioGroup31C.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup31D: if
			 * (radioGroup31D.indexOfChild(radioGroup31D
			 * .findViewById(radioGroup31D.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup32A: if
			 * (radioGroup32A.indexOfChild(radioGroup32A
			 * .findViewById(radioGroup32A.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup32B: if
			 * (radioGroup32A.indexOfChild(radioGroup32B
			 * .findViewById(radioGroup32B.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup32C: if
			 * (radioGroup32C.indexOfChild(radioGroup32C
			 * .findViewById(radioGroup32C.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup32D: if
			 * (radioGroup32D.indexOfChild(radioGroup32D
			 * .findViewById(radioGroup32D.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup33A: if
			 * (radioGroup33A.indexOfChild(radioGroup33A
			 * .findViewById(radioGroup33A.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup33B: if
			 * (radioGroup33A.indexOfChild(radioGroup33B
			 * .findViewById(radioGroup33B.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup33C: if
			 * (radioGroup33C.indexOfChild(radioGroup33C
			 * .findViewById(radioGroup33C.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup33D: if
			 * (radioGroup33D.indexOfChild(radioGroup33D
			 * .findViewById(radioGroup33D.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup34A: if
			 * (radioGroup34A.indexOfChild(radioGroup34A
			 * .findViewById(radioGroup34A.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup34B: if
			 * (radioGroup34A.indexOfChild(radioGroup34B
			 * .findViewById(radioGroup34B.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup34C: if
			 * (radioGroup34C.indexOfChild(radioGroup34C
			 * .findViewById(radioGroup34C.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup34D: if
			 * (radioGroup34D.indexOfChild(radioGroup34D
			 * .findViewById(radioGroup34D.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false;
			 * 
			 * case R.id.radioGroup35A: if
			 * (radioGroup35A.indexOfChild(radioGroup35A
			 * .findViewById(radioGroup35A.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup35B: if
			 * (radioGroup35A.indexOfChild(radioGroup35B
			 * .findViewById(radioGroup35B.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup35C: if
			 * (radioGroup35C.indexOfChild(radioGroup35C
			 * .findViewById(radioGroup35C.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup35D: if
			 * (radioGroup35D.indexOfChild(radioGroup35D
			 * .findViewById(radioGroup35D.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup36A: if
			 * (radioGroup36A.indexOfChild(radioGroup36A
			 * .findViewById(radioGroup36A.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup36B: if
			 * (radioGroup36A.indexOfChild(radioGroup36B
			 * .findViewById(radioGroup36B.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup36C: if
			 * (radioGroup36C.indexOfChild(radioGroup36C
			 * .findViewById(radioGroup36C.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup36D: if
			 * (radioGroup36D.indexOfChild(radioGroup36D
			 * .findViewById(radioGroup36D.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup37A: if
			 * (radioGroup37A.indexOfChild(radioGroup37A
			 * .findViewById(radioGroup37A.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup37B: if
			 * (radioGroup37A.indexOfChild(radioGroup37B
			 * .findViewById(radioGroup37B.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup37C: if
			 * (radioGroup37C.indexOfChild(radioGroup37C
			 * .findViewById(radioGroup37C.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup37D: if
			 * (radioGroup37D.indexOfChild(radioGroup37D
			 * .findViewById(radioGroup37D.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup38A: if
			 * (radioGroup38A.indexOfChild(radioGroup38A
			 * .findViewById(radioGroup38A.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup38B: if
			 * (radioGroup38A.indexOfChild(radioGroup38B
			 * .findViewById(radioGroup38B.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup38C: if
			 * (radioGroup38C.indexOfChild(radioGroup38C
			 * .findViewById(radioGroup38C.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup38D: if
			 * (radioGroup38D.indexOfChild(radioGroup38D
			 * .findViewById(radioGroup38D.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup39A: if
			 * (radioGroup39A.indexOfChild(radioGroup39A
			 * .findViewById(radioGroup39A.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup39B: if
			 * (radioGroup39A.indexOfChild(radioGroup39B
			 * .findViewById(radioGroup39B.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup39C: if
			 * (radioGroup39C.indexOfChild(radioGroup39C
			 * .findViewById(radioGroup39C.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup39D: if
			 * (radioGroup39D.indexOfChild(radioGroup39D
			 * .findViewById(radioGroup39D.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup40A: if
			 * (radioGroup40A.indexOfChild(radioGroup40A
			 * .findViewById(radioGroup40A.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup40B: if
			 * (radioGroup40A.indexOfChild(radioGroup40B
			 * .findViewById(radioGroup40B.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup40C: if
			 * (radioGroup40C.indexOfChild(radioGroup40C
			 * .findViewById(radioGroup40C.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup40D: if
			 * (radioGroup40D.indexOfChild(radioGroup40D
			 * .findViewById(radioGroup40D.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup41A: if
			 * (radioGroup41A.indexOfChild(radioGroup41A
			 * .findViewById(radioGroup41A.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup41B: if
			 * (radioGroup41A.indexOfChild(radioGroup41B
			 * .findViewById(radioGroup41B.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup41C: if
			 * (radioGroup41C.indexOfChild(radioGroup41C
			 * .findViewById(radioGroup41C.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 * 
			 * case R.id.radioGroup41D: if
			 * (radioGroup41D.indexOfChild(radioGroup41D
			 * .findViewById(radioGroup41D.getCheckedRadioButtonId())) == -1)
			 * return alltrue = false; break;
			 */

			default:
				break;
			}
		}

		/*
		 * if (view instanceof EditText) { ((EditText) view).setText(""); } if
		 * (view instanceof Spinner) { ((Spinner) view).setSelection(0); }
		 * 
		 * if (view instanceof RadioButton) { ((RadioButton)
		 * view).setChecked(false); }
		 */
		return alltrue;
	}

	// display current date
	public void setCurrentDateOnView() {

		// q15 = (EditText) findViewById(R.id.q15et);
		dpResult = new DatePicker(con);

		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		// set current date into textview
		/*
		 * q15et.setText(new StringBuilder() // Month is 0 based, just add 1
		 * .append(month + 1).append("-").append(day).append("-")
		 * .append(year).append(" "));
		 * 
		 * .append(day).append(" ").append(GetMonth(month + 1))
		 * .append(" ").append(year).toString());
		 */

		// set current date into datepicker

		/*
		 * String entryDate = "dd-mmm-yy"; Date d = new
		 * Date(System.currentTimeMillis()); entryDate = "dd-mmm-yy"; entryDate
		 * = d.toLocaleString();
		 */

		// Date d = new Date();
		// d = Date.parse(q15et.getText().toString());

		// q17et.setText(String.valueOf(calculateNumberOfDaysBetween(DATE, d)));

		dpResult.init(year, month, day, null);

		// q17et.setText(text)

	}

	private int calculateNumberOfDaysBetween(Date startDate, Date endDate) {
		if (startDate.after(endDate)) {
			throw new IllegalArgumentException(
					"End date should be grater or equals to start date");
		}

		long startDateTime = startDate.getTime();
		long endDateTime = endDate.getTime();
		long milPerDay = 1000 * 60 * 60 * 24;

		int numOfDays = (int) ((endDateTime - startDateTime) / milPerDay); // calculate
																			// vacation
																			// duration
																			// in
																			// days

		return (numOfDays + 1); // add one day to include start date in interval
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			// set date picker as current date
			return new DatePickerDialog(this, datePickerListener, year, month,
					day);
		}
		return null;
	}

	public static long daysBetween(Calendar startDate, Calendar endDate) {
		Calendar date = (Calendar) startDate.clone();
		long daysBetween = 0;
		while (date.before(endDate)) {
			date.add(Calendar.DAY_OF_MONTH, 1);
			daysBetween++;
		}
		return daysBetween;
	}

	public Date getBirthDays() {

		Date d = null;

		String sql = String.format(
				"SELECT SampleColDate FROM tblMainQues WHERE dataid='%s' ",
				CommonStaticClass.dataId,
				CommonStaticClass.currentChildrenCount);

		Cursor mCursor = null;
		try {
			mCursor = DatabaseHelper.getInstance(con).getQueryCursor(sql);
			if (mCursor.moveToFirst()) {
				do {
					String sDate = mCursor.getString(mCursor
							.getColumnIndex("SampleColDate"));

					String sday = sDate.substring(0, 2).trim();
					String smonth = sDate.substring(2, 6).trim();
					String sYear = sDate.substring(6, sDate.length()).trim();

					d = new Date(Integer.parseInt(sYear), GetMonth(smonth) - 1,
							Integer.parseInt(sday));

				} while (mCursor.moveToNext());
			}
		} catch (Exception e) {

		} finally {
			if (mCursor != null)
				mCursor.close();
		}
		return d;

	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			// set selected date into textview
			q15et.setText(new StringBuilder().append(day).append(" ")
					.append(GetMonth(month + 1)).append(" ").append(year)
					.toString());

			final Calendar c = Calendar.getInstance();
			dateYear = c.get(Calendar.YEAR);
			dateMonth = c.get(Calendar.MONTH);
			dateDay = c.get(Calendar.DAY_OF_MONTH);

			thisYear = c.get(Calendar.YEAR);
			thisMonth = c.get(Calendar.MONTH);
			thisDay = c.get(Calendar.DAY_OF_MONTH);

			Calendar dsDate = Calendar.getInstance();
			dsDate.set(selectedYear, selectedMonth, selectedDay);

			if (q15et.getText().length() > 0) {

				Calendar dobCalender = Calendar.getInstance();

				Date d = getBirthDays();
				dobCalender.set(d.getYear(), d.getMonth(), d.getDate());

				// q17et.setText(String.valueOf(daysBetween(dsDate, c)));
				q17et.setText(String.valueOf(daysBetween(dsDate, dobCalender)));
			}
			// set selected date into datepicker also
			dpResult.init(year, month, day, null);

		}
	};

}
