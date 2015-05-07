package com.icddrb.app.wbendLineenvsamplingd2.questions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
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
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.icddrb.app.wbendLineenvsamplingd2.R;
import com.icddrb.app.wbendLineenvsamplingd2.BaseActivity;
import com.icddrb.app.wbendLineenvsamplingd2.CommonStaticClass;
import com.icddrb.app.wbendLineenvsamplingd2.Options;
import com.icddrb.app.wbendLineenvsamplingd2.adapters.SpinAdapter;
import com.icddrb.app.wbendLineenvsamplingd2.db.DatabaseHelper;

public class ParentActivity extends BaseActivity implements FormListener {

	//
	static ParentActivity thisactivity;

	// frmaddress part
	private EditText txtHoldingNo, txtPara, txtVillage, txtunion, txtupazila,
			txtDistrict, txtPhone1, txtPhone2;
	private TextView qqq, lblHoldingNo, lblPara, lblVillage, lblUnion,
			lblUpazilla, lblDistrict, lblPhone1, lblPhone2;
	private String resHHno, resPara, resVillage, resUnion, resUpazilla,
			resDistrict, resPhone1, resPhone2;
	private Button btnPrev, btnNext, btnClear, notesButton;
	private String qName = "";
	static Typeface font;

	// frmmultiplechecktext
	private EditText editText1, editText2, editText3, editText4;
	private CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5,
			checkBox6;
	// frmcombobox part
	private Button prevButton, saveNxtButton, clButton;
	private LinearLayout checkBoxHolder;
	private Options op;
	ArrayList<String> users = new ArrayList<String>();
	ArrayList<String> userIDs = new ArrayList<String>();
	ArrayAdapter adapterForCombo, adapterForCombo2, adapterForCombo3;
	private String sResCode = "";

	// frmdate part
	private EditText pickDate;
	static final int DATE_DIALOG = 1;
	private int dateYear;
	private int dateMonth;
	private int dateDay;

	// frmfamilymember part

	private TextView lblSL;

	private Button btnsaveNxt;

	private Spinner spinnerSL, spinnermedicine;
	private String sex = "";
	String mydata = "";
	private Options opSex;
	boolean insertMore = false;
	boolean IsFirstTime = true;
	private ArrayList<String> memberIDs, medicineIDs;
	private ArrayAdapter adapterSl, adapterSex;
	private String memberID;
	// frmgpsdatacollection part
	protected LocationManager locationManager;
	protected Button getGPSDataButton;
	protected EditText txtLongitute, txtLatitue;

	// frmhhid part
	static final int DATE_DIALOG_ID = 3;
	private int mYear;
	private int mMonth;
	private int mDay;
	private String hospital = "", ward = "";

	private ArrayAdapter adapterHospital, adapterWard;

	ArrayList<String> wardList = new ArrayList<String>();
	ArrayList<String> hospitalList = new ArrayList<String>();

	TextView tvformname;
	Spinner spinnerhospital;
	Spinner spinnerward;
	EditText etyearmonth;
	EditText etillness;

	private EditText txtschoolID, txtID, txtschoolIDRe, txtIDRe;
	private String schoolid = "", id = "", schoolidre = "", idre = "";
	private ProgressDialog progressDialog;
	private static final int UPDATEDONE = 900, FILEREADFAILED = 1000,
			FILECONTENTMISSING = 1100;
	private String line = "";
	private Button genButton, confButton;
	// frommessage part

	// frmmultiplecheckcombo
	private ArrayList<String> optionList1 = null, optionList2 = null,
			optionList3 = null;
	private ArrayList<Integer> optionCodeList1 = null, optionCodeList2 = null,
			optionCodeList3 = null;
	DisplayMetrics dm;
	private ArrayList<Integer> aaa = new ArrayList<Integer>();

	private ArrayList<String> listvalues = new ArrayList<String>();

	private boolean spinnerOK = true;
	// frmmultiplechoice
	private LinkedHashMap<Integer, EditText> edList = new LinkedHashMap<Integer, EditText>();

	// frmmultiplecombo
	private LinearLayout LinearLayout1, LinearLayout2, LinearLayout3;
	private TextView lbl1st, lbl2nd, lbl3rd;
	private Spinner spinner1st, spinner2nd, spinner3rd;
	private String res1st = "", res2nd = "", res3rd = "", res1stother = "",
			res2ndother = "", res3rdother = "";
	private Options op1st, op2nd, op3rd;
	private ArrayAdapter adapter1st, adapter2nd, adapter3rd;
	private boolean IsMismatch_1_1_8 = false;
	private boolean IsMismatch_1_1_9 = false;
	private boolean IsFirstTime1 = true;
	private boolean IsFirstTime2 = true;
	private boolean IsFirstTime3 = true;

	private boolean IsVisited1st = true;
	private boolean IsVisited2nd = true;
	private boolean IsVisited3rd = true;

	private boolean ShouldSkipfor1st = true;
	private boolean ShouldSkipfor2nd = true;
	private boolean ShouldSkipfor3rd = true;

	// frmnotes
	private EditText infoText;
	private Button btnCancel, btnSave;

	// frmnumeric

	// frmnumericTwo
	private TextView num1, num2;
	private EditText infoText1, infoText2;
	private String qName1, qName2;
	private Calendar dsDate = Calendar.getInstance();
	// frmreasoning
	private TextView lbla, lblb, lblc, lbld, lble, lblf, lblg, lblh, lbli,
			lblj;
	private CheckBox chka1, chka2, chka3, chkb1, chkb2, chkb3, chkc1, chkc2,
			chkc3, chkd1, chkd2, chkd3, chke1, chke2, chke3, chkf1, chkf2,
			chkf3, chkg1, chkg2, chkg3, chkh1, chkh2, chkh3, chki1, chki2,
			chki3, chkj1, chkj2, chkj3;
	private int a1 = 0, a2 = 0, a3 = 0, b1 = 0, b2 = 0, b3 = 0, c1 = 0, c2 = 0,
			c3 = 0, d1 = 0, d2 = 0, d3 = 0, e1 = 0, e2 = 0, e3 = 0, f1 = 0,
			f2 = 0, f3 = 0, g1 = 0, g2 = 0, g3 = 0, h1 = 0, h2 = 0, h3 = 0,
			i1 = 0, i2 = 0, i3 = 0, j1 = 0, j2 = 0, j3 = 0;
	private String other1 = "", other2 = "", other3 = "";
	// frmsinglechoice
	private RadioGroup mRadioGroup;
	int code, qWhen;
	String nextToGo;
	private int idIfEdit = -1;
	// frmtime
	private EditText pickTime;
	static final int TIME_DIALOG = 2;

	private int TimeHour;
	private int TimeMinute;

	// fromyeartomin
	private EditText yearBox, monthBox, weekBox, dayBox, hourBox, minBox;
	private TextView yearText, monthText, weekText, dayText, hourText, minText;
	private LinearLayout yearHolder, monthHolder, weekHolder, dayHolder,
			hourHolder, minHolder;
	private String year = "", month = "", week = "", day = "", hour = "",
			min = "";

	// frmNumeric_with_unknown_decline
	RadioGroup radioGroup;
	RadioButton radio1, radio2;

	// this activity part
	private ViewFlipper formFlipper;
	private Context con;
	private ViewGroup frmdataid, frmcombobox, frmdate, frmfamilymember,
			frmhhid, frmmessage, frmmultiplecheckcombo, frmmultiplechoice,
			frmmultiplecombo, frmnotes, frmnumeric, frmnumericoption,
			frmreasoning, frmsinglechoice, frmtext, frmtime, frmyeartomin,
			gpsdatacollection, frmnumerictwo, frmnumericwithunknowndecline,
			frmcomboswitheditspiner, frmmultiplecheckcombotwo,
			frmmultiplechoiceradio, frmmultiple, frmq124,
			frmmultiplechecknumeric, frmmultiplecheckdate, frmbarcode,
			frmnumericwithrdbtn;

	private int lastIndexBeforeFraNotes;
	private TextView dataidViewer;

	// Multiple check combo two
	private ArrayList<Integer> aaachecklist = new ArrayList<Integer>();
	private ArrayList<String> mEditStrings = new ArrayList<String>();
	private ArrayList<Integer> aaa1 = new ArrayList<Integer>();
	private ArrayList<Integer> aaa2 = new ArrayList<Integer>();
	private ArrayList<Integer> aaa3 = new ArrayList<Integer>();
	// frmMultiple
	private String chk1_1 = "2", chk2_1 = "2", chk2_2 = "2", chk2_3 = "2",
			chk2_4 = "2", chk2_5 = "2", et2_5_other = "", chk3_1 = "2",
			chk3_2 = "2", chk3_3 = "2", et3_3_other = "", chk4_1 = "2",
			chk4_2 = "2", chk5_1 = "2", chk5_2 = "2", chk5_3 = "2",
			chk5_4 = "2", chk5_5 = "2", et5_5_other = "", chk6_1 = "2",
			chk6_2 = "2", chk7_1 = "2", chk7_2 = "2", chk7_3 = "2",
			chk7_4 = "2", chk7_5 = "2", chk7_6 = "2", chk7_7 = "2",
			et7_6_other = "", chk8_1 = "2", chk8_2 = "2", chk8_3 = "2",
			chk8_4 = "2", chk8_5 = "2", chk8_6 = "2", et8_6_other = "",
			chk9_1 = "2", chk9_2 = "2", chk9_3 = "2", chk10_1 = "2",
			chk11_1 = "2", chk12_1 = "2", chk12_2 = "2", chk12_3 = "2",
			et12_3_other = "", chk13_1 = "2", chk13_2 = "2", chk13_3 = "2",
			chk13_4 = "2", chk13_5 = "2", chk13_6 = "2", chk13_7 = "2",
			et13_7_other = "", chk14_1 = "2", chk14_2 = "2", chk14_3 = "2",
			et14_3_other = "", chk15_1 = "2", chk15_2 = "2", chk15_3 = "2",
			chk16_1 = "2", chk16_2 = "2", chk16_3 = "2", chk16_4 = "2",
			chk16_5 = "2", chk17_1 = "2", chk17_2 = "2", chk17_3 = "2",
			chk17_4 = "2", chk18_1 = "2", chk18_2 = "2", et19_1 = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		/*super.onCreate(savedInstanceState);
		setContentView(R.layout.parentlayout);
		thisactivity = this;

		if (DatabaseHelper.getInstanceBase() == null) {
			dbHelperBase = new DatabaseHelper(thisactivity);
			//dbHelperBase.openDataBase_BASE();
		} else {
			dbHelperBase = DatabaseHelper.getInstance();
		}

		con = this;
		dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		font = Typeface.createFromAsset(getAssets(), "Siyam Rupali ANSI.ttf");

		loadParentUI();
		this.gotoForm(CommonStaticClass.questionMap.get(
				CommonStaticClass.currentSLNo).getFormname());
*/		// motion();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.parentlayout);
		thisactivity = this;
		con = this;
		dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		//font = Typeface.createFromAsset(getAssets(), "Siyam Rupali ANSI.ttf");

		loadParentUI();
		this.gotoForm(CommonStaticClass.questionMap.get(
				CommonStaticClass.currentSLNo).getFormname());
	}

	private String q4_a2 = "";
	private String q4_b2 = "";
	private String q4_c2 = "";
	private String q4_d2 = "";
	private String q4_e2 = "";
	private String q4_f2 = "";
	private String q4_g2 = "";
	private String q4_h2 = "";
	private String q4_i2 = "";
	private String q4_j2 = "";
	private String q4_k2 = "";
	private String q4_l2 = "";
	private String q4_m2 = "";
	private String q4_n2 = "";
	private String q4_o2 = "";
	private String q4_p2 = "";
	private String q4_q2 = "";
	private String q4_r2 = "";
	private String q4_s2 = "";
	private String q4_t2 = "";

	private void populateSpinnerListFrmCombosWithEditSpinner(Options op, int l,
			ArrayList<String> list, ArrayList<Integer> ll) {
		for (int i = 0; i < op.qidList.size(); i++) {
			if (l == 1 && op.qidList.get(i).contains("1_1")) {
				if (CommonStaticClass.langBng) {
					list.add(op.capBngList.get(i));
				} else {
					list.add(op.capEngList.get(i));
				}
				ll.add(op.codeList.get(i));
			} else if (l == 3 && op.qidList.get(i).contains("1_2")) {
				if (CommonStaticClass.langBng) {
					list.add(op.capBngList.get(i));
				} else {
					list.add(op.capEngList.get(i));
				}
				ll.add(op.codeList.get(i));
			}
		}
	}

	private boolean CheckColumnThreeForQuestionNo4_2() {

		if (!q4_a2.equalsIgnoreCase("-1") && q4_a2.length() > 0) {
			if (q4_a2.equalsIgnoreCase("1") || q4_a2.equalsIgnoreCase("2")) {
			} else {
				Toast.makeText(con, "Value on third Column Should be 1 OR 2",
						10000).show();
				return false;
			}
		}
		if (!q4_b2.equalsIgnoreCase("-1") && q4_b2.length() > 0) {
			if (q4_b2.equalsIgnoreCase("1") || q4_b2.equalsIgnoreCase("2")) {
			} else {
				Toast.makeText(con, "Value on third Column Should be 1 OR 2",
						10000).show();
				return false;
			}
		}
		if (!q4_c2.equalsIgnoreCase("-1") && q4_c2.length() > 0) {
			if (q4_c2.equalsIgnoreCase("1") || q4_c2.equalsIgnoreCase("2")) {
			} else {
				Toast.makeText(con, "Value on third Column Should be 1 OR 2",
						10000).show();
				return false;
			}
		}
		if (!q4_d2.equalsIgnoreCase("-1") && q4_d2.length() > 0) {
			if (q4_d2.equalsIgnoreCase("1") || q4_d2.equalsIgnoreCase("2")) {
			} else {
				Toast.makeText(con, "Value on third Column Should be 1 OR 2",
						10000).show();
				return false;
			}
		}
		if (!q4_e2.equalsIgnoreCase("-1") && q4_e2.length() > 0) {
			if (q4_e2.equalsIgnoreCase("1") || q4_e2.equalsIgnoreCase("2")) {
			} else {
				Toast.makeText(con, "Value on third Column Should be 1 OR 2",
						10000).show();
				return false;
			}
		}
		if (!q4_f2.equalsIgnoreCase("-1") && q4_f2.length() > 0) {
			if (q4_f2.equalsIgnoreCase("1") || q4_f2.equalsIgnoreCase("2")) {
			} else {
				Toast.makeText(con, "Value on third Column Should be 1 OR 2",
						10000).show();
				return false;
			}
		}
		if (!q4_g2.equalsIgnoreCase("-1") && q4_g2.length() > 0) {
			if (q4_g2.equalsIgnoreCase("1") || q4_g2.equalsIgnoreCase("2")) {
			} else {
				Toast.makeText(con, "Value on third Column Should be 1 OR 2",
						10000).show();
				return false;
			}
		}
		if (!q4_h2.equalsIgnoreCase("-1") && q4_h2.length() > 0) {
			if (q4_h2.equalsIgnoreCase("1") || q4_h2.equalsIgnoreCase("2")) {
			} else {
				Toast.makeText(con, "Value on third Column Should be 1 OR 2",
						10000).show();
				return false;
			}
		}
		if (!q4_i2.equalsIgnoreCase("-1") && q4_i2.length() > 0) {
			if (q4_i2.equalsIgnoreCase("1") || q4_i2.equalsIgnoreCase("2")) {
			} else {
				Toast.makeText(con, "Value on third Column Should be 1 OR 2",
						10000).show();
				return false;
			}
		}
		if (!q4_j2.equalsIgnoreCase("-1") && q4_j2.length() > 0) {
			if (q4_j2.equalsIgnoreCase("1") || q4_j2.equalsIgnoreCase("2")) {
			} else {
				Toast.makeText(con, "Value on third Column Should be 1 OR 2",
						10000).show();
				return false;
			}
		}
		if (!q4_k2.equalsIgnoreCase("-1") && q4_k2.length() > 0) {
			if (q4_k2.equalsIgnoreCase("1") || q4_k2.equalsIgnoreCase("2")) {
			} else {
				Toast.makeText(con, "Value on third Column Should be 1 OR 2",
						10000).show();
				return false;
			}
		}
		if (!q4_l2.equalsIgnoreCase("-1") && q4_l2.length() > 0) {
			if (q4_l2.equalsIgnoreCase("1") || q4_l2.equalsIgnoreCase("2")) {
			} else {
				Toast.makeText(con, "Value on third Column Should be 1 OR 2",
						10000).show();
				return false;
			}
		}
		if (!q4_m2.equalsIgnoreCase("-1") && q4_m2.length() > 0) {
			if (q4_m2.equalsIgnoreCase("1") || q4_m2.equalsIgnoreCase("2")) {
			} else {
				Toast.makeText(con, "Value on third Column Should be 1 OR 2",
						10000).show();
				return false;
			}
		}
		if (!q4_n2.equalsIgnoreCase("-1") && q4_n2.length() > 0) {
			if (q4_n2.equalsIgnoreCase("1") || q4_n2.equalsIgnoreCase("2")) {
			} else {
				Toast.makeText(con, "Value on third Column Should be 1 OR 2",
						10000).show();
				return false;
			}
		}
		if (!q4_o2.equalsIgnoreCase("-1") && q4_o2.length() > 0) {
			if (q4_o2.equalsIgnoreCase("1") || q4_o2.equalsIgnoreCase("2")) {
			} else {
				Toast.makeText(con, "Value on third Column Should be 1 OR 2",
						10000).show();
				return false;
			}
		}
		if (!q4_p2.equalsIgnoreCase("-1") && q4_p2.length() > 0) {
			if (q4_p2.equalsIgnoreCase("1") || q4_p2.equalsIgnoreCase("2")) {
			} else {
				Toast.makeText(con, "Value on third Column Should be 1 OR 2",
						10000).show();
				return false;
			}
		}
		if (!q4_q2.equalsIgnoreCase("-1") && q4_q2.length() > 0) {
			if (q4_q2.equalsIgnoreCase("1") || q4_q2.equalsIgnoreCase("2")) {
			} else {
				Toast.makeText(con, "Value on third Column Should be 1 OR 2",
						10000).show();
				return false;
			}
		}
		if (!q4_r2.equalsIgnoreCase("-1") && q4_r2.length() > 0) {
			if (q4_r2.equalsIgnoreCase("1") || q4_r2.equalsIgnoreCase("2")) {
			} else {
				Toast.makeText(con, "Value on third Column Should be 1 OR 2",
						10000).show();
				return false;
			}
		}
		if (!q4_s2.equalsIgnoreCase("-1") && q4_s2.length() > 0) {
			if (q4_s2.equalsIgnoreCase("1") || q4_s2.equalsIgnoreCase("2")) {
			} else {
				Toast.makeText(con, "Value on third Column Should be 1 OR 2",
						10000).show();
				return false;
			}

		}
		if (!q4_t2.equalsIgnoreCase("-1") && q4_t2.length() > 0) {
			if (q4_t2.equalsIgnoreCase("1") || q4_t2.equalsIgnoreCase("2")) {
			} else {
				Toast.makeText(con, "Value on third Column Should be 1 OR 2",
						10000).show();
				return false;
			}
		}

		/*
		 * , q4_b3, q4_c3, q4_d3, q4_e3, q4_f3, q4_g3, q4_h3, q4_i3, q4_j3,
		 * q4_k3, q4_l3, q4_m3, q4_n3, q4_o3, q4_p3, q4_q3, q4_r3, q4_s3, q4_t3)
		 */
		return true;
	}

	private boolean FileRead() {
		InputStream instream = null;

		try {
			String path = "/mnt/sdcard/Android/AssetID.txt";
			Log.e("path", "" + path);
			File f = new File(path);
			instream = new FileInputStream(f);// openFileInput("/mnt/sdcard/Android/AssetID.txt");

			// if file the available for reading
			if (instream != null) {
				// prepare the file for reading
				InputStreamReader inputreader = new InputStreamReader(instream);
				BufferedReader buffreader = new BufferedReader(inputreader);

				// read every line of the file into the line-variable, on line
				// at the time
				line = buffreader.readLine();
				Log.e("line", "" + line);
				if (line == null || !(line.length() > 3)) {

					Message msg = new Message();
					msg.what = FILECONTENTMISSING;
					handler.sendMessage(msg);
					return false;
				} else {
					CommonStaticClass.AssetID = line;
					return true;
				}
				// runOnUiThread(new Runnable() {
				//
				// public void run() {
				// // TODO Auto-generated method stub
				// Toast.makeText(con, line, 10000);
				// }
				// });

				// while (buffreader.readLine() != null) {
				// do something with the settings from the file
			}

			// close the file again

		} catch (Exception ex) {
			ex.printStackTrace();
			Message msg = new Message();
			msg.what = FILEREADFAILED;
			handler.sendMessage(msg);
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

	// End Angsuman
	public boolean onCreateOptionsMenu(final Menu menu) {
		final MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.lang_menu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(final MenuItem item) {
		switch (item.getItemId()) {
		case R.id.BNGMenuItem:
			CommonStaticClass.langBng = true;
			this.gotoForm(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getFormname());
			return true;
		case R.id.ENGMenuItem:
			CommonStaticClass.langBng = false;
			this.gotoForm(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getFormname());
			return true;
		case R.id.GotoHomeItem:
			CommonStaticClass.mode = "";
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void loadParentUI() {
		// TODO Auto-generated method stub
		dataidViewer = (TextView) findViewById(R.id.dataidViewer);
		formFlipper = (ViewFlipper) findViewById(R.id.formFlipper);

		frmdataid = (ViewGroup) findViewById(R.id.frmdataid);

		// (ViewGroup) findViewById(R.id.frmchoiceone);
		frmcombobox = (ViewGroup) findViewById(R.id.frmcombobox);

		frmdate = (ViewGroup) findViewById(R.id.frmdate);
		frmfamilymember = (ViewGroup) findViewById(R.id.frmfamilymember);
		frmhhid = (ViewGroup) findViewById(R.id.frmhhid);
		frmmessage = (ViewGroup) findViewById(R.id.frmmessage);

		frmmultiplecheckcombo = (ViewGroup) findViewById(R.id.frmmultiplecheckcombo);
		frmmultiplechoice = (ViewGroup) findViewById(R.id.frmmultiplechoice);
		frmmultiplecombo = (ViewGroup) findViewById(R.id.frmmultiplecombo);

		frmnotes = (ViewGroup) findViewById(R.id.frmnotes);
		frmnumeric = (ViewGroup) findViewById(R.id.frmnumeric);
		frmnumericoption = (ViewGroup) findViewById(R.id.frmnumericoption);

		frmreasoning = (ViewGroup) findViewById(R.id.frmreasoning);
		frmsinglechoice = (ViewGroup) findViewById(R.id.frmsinglechoice);
		frmtext = (ViewGroup) findViewById(R.id.frmtext);

		frmtime = (ViewGroup) findViewById(R.id.frmtime);
		frmyeartomin = (ViewGroup) findViewById(R.id.frmyeartomin);
		gpsdatacollection = (ViewGroup) findViewById(R.id.gpsdatacollection);
		frmnumerictwo = (ViewGroup) findViewById(R.id.frmnumerictwo);
		frmnumericwithunknowndecline = (ViewGroup) findViewById(R.id.frmnumericwithunknowndecline);
		// frmmultiplechecktext = (ViewGroup)
		// findViewById(R.id.frmmultiplechecktext);
		frmcomboswitheditspiner = (ViewGroup) findViewById(R.id.frmcomboswitheditspiner);
		frmmultiplecheckcombotwo = (ViewGroup) findViewById(R.id.frmmultiplecheckcombotwo);
		frmmultiplechoiceradio = (ViewGroup) findViewById(R.id.frmmultiplechoiceradio);
		frmmultiple = (ViewGroup) findViewById(R.id.frmmultiple);
		frmq124 = (ViewGroup) findViewById(R.id.frmq124);
		frmmultiplechecknumeric = (ViewGroup) findViewById(R.id.frmmultiplechecknumeric);
		frmmultiplecheckdate = (ViewGroup) findViewById(R.id.frmmultiplecheckdate);
		frmbarcode = (ViewGroup) findViewById(R.id.frmbarcode);
		frmnumericwithrdbtn = (ViewGroup) findViewById(R.id.frmnumericwithrdbtn);

	}

	protected void FraNotes() {
		// TODO Auto-generated method stub
		this.gotoForm("FrmNotes");
	}

	// FrmMultipleCheckText
	public custom_class cls = new custom_class();

	private void loadGuiFrmMultipleChoiceRadio(final ViewGroup v) {

		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		ArrayList<String> spinnervalues = new ArrayList<String>();
		ArrayList<String> spinnervalues2 = new ArrayList<String>();
		ArrayList<String> spinnervalues3 = new ArrayList<String>();

		spinnervalues.add("");
		spinnervalues.add("1:Yes Eaten");
		spinnervalues.add("0:No");
		spinnervalues.add("66:Eaten, donâ€™t know how many days ");
		spinnervalues.add("99:Donâ€™t know if eaten or not");
		// spinnervalues.add("77:If others, What type?");

		spinnervalues2.add("");
		spinnervalues2.add("1:Yes Eaten");
		spinnervalues2.add("0:No");
		spinnervalues2.add("66:Eaten, donâ€™t know how many days ");
		spinnervalues2.add("99:Donâ€™t know if eaten or not");

		spinnervalues3.add("");
		spinnervalues3.add("00:00");
		spinnervalues3.add("88:88");

		qName = CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar();
		qqq = (TextView) v.findViewById(R.id.qqq);

		((EditText) v.findViewById(R.id.day1)).setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.day2)).setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.day3)).setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.day4)).setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.day61)).setVisibility(View.GONE);

		((EditText) v.findViewById(R.id.et1)).setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.et3)).setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.et4)).setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.et6)).setVisibility(View.GONE);

		adapterForCombo = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item, spinnervalues);
		adapterForCombo
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		adapterForCombo2 = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item, spinnervalues2);
		adapterForCombo2
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		adapterForCombo3 = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item, spinnervalues3);
		adapterForCombo3
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		((Spinner) v.findViewById(R.id.spinner1)).setAdapter(adapterForCombo);
		((Spinner) v.findViewById(R.id.spinner2)).setAdapter(adapterForCombo2);
		((Spinner) v.findViewById(R.id.spinner3)).setAdapter(adapterForCombo);
		((Spinner) v.findViewById(R.id.spinner4)).setAdapter(adapterForCombo);
		((Spinner) v.findViewById(R.id.spinner5)).setAdapter(adapterForCombo3);
		((Spinner) v.findViewById(R.id.spinner6)).setAdapter(adapterForCombo2);

		((Spinner) v.findViewById(R.id.spinner1)).setVisibility(View.GONE);
		((Spinner) v.findViewById(R.id.spinner2)).setVisibility(View.GONE);
		((Spinner) v.findViewById(R.id.spinner3)).setVisibility(View.GONE);
		((Spinner) v.findViewById(R.id.spinner4)).setVisibility(View.GONE);
		((Spinner) v.findViewById(R.id.spinner5)).setVisibility(View.GONE);
		((Spinner) v.findViewById(R.id.spinner6)).setVisibility(View.GONE);

		((Spinner) v.findViewById(R.id.spinner1))
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> parent,
							View view, int pos, long id) {

						if (parent.getItemAtPosition(pos).toString().length() > 0) {

							sResCode = parent
									.getItemAtPosition(pos)
									.toString()
									.substring(
											0,
											(parent.getItemAtPosition(pos)
													.toString()
													.lastIndexOf(":")));

							cls.setQ612_1(Integer.parseInt(sResCode));

							if (sResCode.equalsIgnoreCase("1")) {
								((EditText) v.findViewById(R.id.day1))
										.setVisibility(View.VISIBLE);
							} else if (sResCode.equalsIgnoreCase("77")) {
								((EditText) v.findViewById(R.id.et1))
										.setVisibility(View.VISIBLE);
							} else {
								((EditText) v.findViewById(R.id.day1))
										.setText("");
								((EditText) v.findViewById(R.id.day1))
										.setVisibility(View.GONE);
								/*
								 * ((EditText) v.findViewById(R.id.et1))
								 * .setText(""); ((EditText)
								 * v.findViewById(R.id.et1))
								 * .setVisibility(View.GONE);
								 */
							}

						}

					}

					public void onNothingSelected(AdapterView<?> arg0) {

					}
				});

		((Spinner) v.findViewById(R.id.spinner2))
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> parent,
							View view, int pos, long id) {

						if (parent.getItemAtPosition(pos).toString().length() > 0) {

							/*
							 * ((EditText)
							 * v.findViewById(R.id.day2)).setText("");
							 * ((EditText) v.findViewById(R.id.day2))
							 * .setVisibility(View.GONE);
							 */

							sResCode = parent
									.getItemAtPosition(pos)
									.toString()
									.substring(
											0,
											(parent.getItemAtPosition(pos)
													.toString()
													.lastIndexOf(":")));

							cls.setQ612_2(Integer.parseInt(sResCode));

							if (sResCode.equalsIgnoreCase("1")) {
								((EditText) v.findViewById(R.id.day2))
										.setVisibility(View.VISIBLE);

							} else {
								((EditText) v.findViewById(R.id.day2))
										.setText("");
								((EditText) v.findViewById(R.id.day2))
										.setVisibility(View.GONE);
							}

						}

					}

					public void onNothingSelected(AdapterView<?> arg0) {

					}
				});

		((Spinner) v.findViewById(R.id.spinner3))
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> parent,
							View view, int pos, long id) {

						if (parent.getItemAtPosition(pos).toString().length() > 0) {

							sResCode = parent
									.getItemAtPosition(pos)
									.toString()
									.substring(
											0,
											(parent.getItemAtPosition(pos)
													.toString()
													.lastIndexOf(":")));

							cls.setQ612_3(Integer.parseInt(sResCode));

							if (sResCode.equalsIgnoreCase("1")) {
								((EditText) v.findViewById(R.id.day3))
										.setVisibility(View.VISIBLE);
							} else if (sResCode.equalsIgnoreCase("77")) {
								((EditText) v.findViewById(R.id.et3))
										.setVisibility(View.VISIBLE);
							} else {
								((EditText) v.findViewById(R.id.day3))
										.setText("");
								((EditText) v.findViewById(R.id.day3))
										.setVisibility(View.GONE);
								/*
								 * ((EditText) v.findViewById(R.id.et3))
								 * .setText(""); ((EditText)
								 * v.findViewById(R.id.et3))
								 * .setVisibility(View.GONE);
								 */
							}

						}

					}

					public void onNothingSelected(AdapterView<?> arg0) {

					}
				});

		((Spinner) v.findViewById(R.id.spinner4))
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> parent,
							View view, int pos, long id) {

						if (parent.getItemAtPosition(pos).toString().length() > 0) {

							sResCode = parent
									.getItemAtPosition(pos)
									.toString()
									.substring(
											0,
											(parent.getItemAtPosition(pos)
													.toString()
													.lastIndexOf(":")));

							cls.setQ612_4(Integer.parseInt(sResCode));

							if (sResCode.equalsIgnoreCase("1")) {
								((EditText) v.findViewById(R.id.day4))
										.setVisibility(View.VISIBLE);
							} else if (sResCode.equalsIgnoreCase("77")) {
								((EditText) v.findViewById(R.id.et4))
										.setVisibility(View.VISIBLE);
							} else {
								((EditText) v.findViewById(R.id.day4))
										.setText("");
								((EditText) v.findViewById(R.id.day4))
										.setVisibility(View.GONE);
								/*
								 * ((EditText) v.findViewById(R.id.et4))
								 * .setText(""); ((EditText)
								 * v.findViewById(R.id.et4))
								 * .setVisibility(View.GONE);
								 */
							}

						}

					}

					public void onNothingSelected(AdapterView<?> arg0) {

					}
				});

		((Spinner) v.findViewById(R.id.spinner5))
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> parent,
							View view, int pos, long id) {

						if (parent.getItemAtPosition(pos).toString().length() > 0) {

							sResCode = parent
									.getItemAtPosition(pos)
									.toString()
									.substring(
											0,
											(parent.getItemAtPosition(pos)
													.toString()
													.lastIndexOf(":")));

							cls.setQ612_5(Integer.parseInt(sResCode));

						}

					}

					public void onNothingSelected(AdapterView<?> arg0) {

					}
				});

		((Spinner) v.findViewById(R.id.spinner6))
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> parent,
							View view, int pos, long id) {

						if (parent.getItemAtPosition(pos).toString().length() > 0) {

							sResCode = parent
									.getItemAtPosition(pos)
									.toString()
									.substring(
											0,
											(parent.getItemAtPosition(pos)
													.toString()
													.lastIndexOf(":")));

							cls.setQ612_6(Integer.parseInt(sResCode));

							if (sResCode.equalsIgnoreCase("1")) {
								((EditText) v.findViewById(R.id.day61))
										.setVisibility(View.VISIBLE);
							} else if (sResCode.equalsIgnoreCase("77")) {
								((EditText) v.findViewById(R.id.et6))
										.setVisibility(View.VISIBLE);
							} else {
								((EditText) v.findViewById(R.id.day61))
										.setText("");
								((EditText) v.findViewById(R.id.day61))
										.setVisibility(View.GONE);
								// ((EditText) v.findViewById(R.id.et6))
								// .setText("");
								// ((EditText) v.findViewById(R.id.et6))
								// .setVisibility(View.GONE);
							}

						}

					}

					public void onNothingSelected(AdapterView<?> arg0) {

					}
				});

		((CheckBox) v.findViewById(R.id.chk1))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {

						if (isChecked) {
							((Spinner) v.findViewById(R.id.spinner1))
									.setVisibility(View.VISIBLE);

						} else {
							((Spinner) v.findViewById(R.id.spinner1))
									.setVisibility(View.GONE);
						}

					}
				});

		((CheckBox) v.findViewById(R.id.chk2))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {

						if (isChecked)
							((Spinner) v.findViewById(R.id.spinner2))
									.setVisibility(View.VISIBLE);
						else
							((Spinner) v.findViewById(R.id.spinner2))
									.setVisibility(View.GONE);

					}
				});

		((CheckBox) v.findViewById(R.id.chk3))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {

						if (isChecked)
							((Spinner) v.findViewById(R.id.spinner3))
									.setVisibility(View.VISIBLE);
						else
							((Spinner) v.findViewById(R.id.spinner3))
									.setVisibility(View.GONE);

					}
				});

		((CheckBox) v.findViewById(R.id.chk4))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {

						if (isChecked)
							((Spinner) v.findViewById(R.id.spinner4))
									.setVisibility(View.VISIBLE);
						else
							((Spinner) v.findViewById(R.id.spinner4))
									.setVisibility(View.GONE);

					}
				});

		((CheckBox) v.findViewById(R.id.chk5))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {

						if (isChecked)
							((Spinner) v.findViewById(R.id.spinner5))
									.setVisibility(View.VISIBLE);
						else
							((Spinner) v.findViewById(R.id.spinner5))
									.setVisibility(View.GONE);

					}
				});

		((CheckBox) v.findViewById(R.id.chk6))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {

						if (isChecked)
							((Spinner) v.findViewById(R.id.spinner6))
									.setVisibility(View.VISIBLE);
						else
							((Spinner) v.findViewById(R.id.spinner6))
									.setVisibility(View.GONE);

					}
				});

		((CheckBox) v.findViewById(R.id.checkBox1))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {

						if (isChecked)
							((EditText) v.findViewById(R.id.et1))
									.setVisibility(View.VISIBLE);
						else
							((EditText) v.findViewById(R.id.et1))
									.setVisibility(View.GONE);

					}
				});

		((CheckBox) v.findViewById(R.id.checkBox2))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {

						if (isChecked)
							((EditText) v.findViewById(R.id.et3))
									.setVisibility(View.VISIBLE);
						else
							((EditText) v.findViewById(R.id.et3))
									.setVisibility(View.GONE);

					}
				});
		((CheckBox) v.findViewById(R.id.checkBox3))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {

						if (isChecked)
							((EditText) v.findViewById(R.id.et4))
									.setVisibility(View.VISIBLE);
						else
							((EditText) v.findViewById(R.id.et4))
									.setVisibility(View.GONE);

					}
				});
		((CheckBox) v.findViewById(R.id.checkBox4))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {

						if (isChecked)
							((EditText) v.findViewById(R.id.et6))
									.setVisibility(View.VISIBLE);
						else
							((EditText) v.findViewById(R.id.et6))
									.setVisibility(View.GONE);

					}
				});
		btnPrev = (Button) v.findViewById(R.id.prevButton);
		btnPrev.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				userPressedPrevious(ParentActivity.this);
			}

		});
		btnNext = (Button) v.findViewById(R.id.saveNxtButton);

		btnNext.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				if (((EditText) findViewById(R.id.day1)).getVisibility() == View.VISIBLE) {
					if (((EditText) findViewById(R.id.day1)).getText()
							.toString().length() == 0) {
						CommonStaticClass.showMyAlert(con, "Message",
								"Number should be between 1 to 7");
						return;
					}

					if (!(Integer.parseInt(((EditText) findViewById(R.id.day1))
							.getText().toString()) >= 1)
							|| !(Integer
									.parseInt(((EditText) findViewById(R.id.day1))
											.getText().toString()) <= 7))

					{
						CommonStaticClass.showMyAlert(con, "Message",
								"Number should be between 1 to 7");
						return;
					}

					cls.setQ612_1_day(Integer
							.parseInt((((EditText) findViewById(R.id.day1))
									.getText().toString())));
				} else {
					cls.setQ612_1_day(-1);
				}

				if (((EditText) findViewById(R.id.day2)).getVisibility() == View.VISIBLE) {

					if (((EditText) findViewById(R.id.day2)).getText()
							.toString().length() == 0) {
						CommonStaticClass.showMyAlert(con, "Message",
								"Number should be between 1 to 7");
						return;
					}

					if (!(Integer.parseInt(((EditText) findViewById(R.id.day2))
							.getText().toString()) >= 1)
							|| !(Integer
									.parseInt(((EditText) findViewById(R.id.day2))
											.getText().toString()) <= 7))

					{
						CommonStaticClass.showMyAlert(con, "Message",
								"Number should be between 1 to 7");
						return;
					}

					cls.setQ612_2_day(Integer
							.parseInt((((EditText) findViewById(R.id.day2))
									.getText().toString())));
				} else {
					cls.setQ612_2_day(-1);
				}

				if (((EditText) findViewById(R.id.day3)).getVisibility() == View.VISIBLE) {

					if (((EditText) findViewById(R.id.day3)).getText()
							.toString().length() == 0) {
						CommonStaticClass.showMyAlert(con, "Message",
								"Number should be between 1 to 7");
						return;
					}

					if (!(Integer.parseInt(((EditText) findViewById(R.id.day3))
							.getText().toString()) >= 1)
							|| !(Integer
									.parseInt(((EditText) findViewById(R.id.day3))
											.getText().toString()) <= 7))

					{
						CommonStaticClass.showMyAlert(con, "Message",
								"Number should be between 1 to 7");
						return;
					}

					cls.setQ612_3_day(Integer
							.parseInt((((EditText) findViewById(R.id.day3))
									.getText().toString())));
				} else {
					cls.setQ612_3_day(-1);
				}
				if (((EditText) findViewById(R.id.day4)).getVisibility() == View.VISIBLE) {

					if (((EditText) findViewById(R.id.day4)).getText()
							.toString().length() == 0) {
						CommonStaticClass.showMyAlert(con, "Message",
								"Number should be between 1 to 7");
						return;
					}

					if (!(Integer.parseInt(((EditText) findViewById(R.id.day4))
							.getText().toString()) >= 1)
							|| !(Integer
									.parseInt(((EditText) findViewById(R.id.day4))
											.getText().toString()) <= 7))

					{
						CommonStaticClass.showMyAlert(con, "Message",
								"Number should be between 1 to 7");
						return;
					}

					cls.setQ612_4_day(Integer
							.parseInt((((EditText) findViewById(R.id.day4))
									.getText().toString())));
				} else {
					cls.setQ612_4_day(-1);
				}
				if (((EditText) findViewById(R.id.day61)).getVisibility() == View.VISIBLE) {
					if (((EditText) findViewById(R.id.day61)).getText()
							.toString().length() == 0) {
						CommonStaticClass.showMyAlert(con, "Message",
								"Number should be between 1 to 7");
						return;
					}

					if (!(Integer
							.parseInt(((EditText) findViewById(R.id.day61))
									.getText().toString()) >= 1)
							|| !(Integer
									.parseInt(((EditText) findViewById(R.id.day61))
											.getText().toString()) <= 7))

					{
						CommonStaticClass.showMyAlert(con, "Message",
								"Number should be between 1 to 7");
						return;
					}
					cls.setQ612_6_day(Integer
							.parseInt((((EditText) findViewById(R.id.day61))
									.getText().toString())));
				} else {
					cls.setQ612_6_day(-1);
				}
				if (((EditText) findViewById(R.id.et1)).getVisibility() == View.VISIBLE) {
					cls.setq612_1_other((((EditText) findViewById(R.id.et1))
							.getText().toString()));
				} else {
					cls.setq612_1_other("");
				}

				if (((EditText) findViewById(R.id.et3)).getVisibility() == View.VISIBLE) {
					cls.setq612_3_other((((EditText) findViewById(R.id.et3))
							.getText().toString()));
				} else {
					cls.setq612_3_other("");
				}

				if (((EditText) findViewById(R.id.et4)).getVisibility() == View.VISIBLE) {
					cls.setq612_4_other((((EditText) findViewById(R.id.et4))
							.getText().toString()));
				} else {
					cls.setq612_4_other("");
				}
				if (((EditText) findViewById(R.id.et6)).getVisibility() == View.VISIBLE) {
					cls.setq612_6_other((((EditText) findViewById(R.id.et6))
							.getText().toString()));
				} else {
					cls.setq612_6_other("");
				}
				updateTableDataFrmMultipleChoiceRadio(cls);
			}

		});
		btnClear = (Button) v.findViewById(R.id.clButton);
		btnClear.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				resetViewGroup((ViewGroup) v);
			}

		});

		cls = new custom_class();
		cls = cls.GetRecords(dbHelper);

		if (cls.getQ612_1() >= 0) {
			((CheckBox) v.findViewById(R.id.chk1)).setChecked(true);
			((Spinner) v.findViewById(R.id.spinner1))
					.setVisibility(View.VISIBLE);

			switch (cls.getQ612_1()) {
			case 1:
				((Spinner) v.findViewById(R.id.spinner1)).setSelection(1);
				((EditText) v.findViewById(R.id.day1))
						.setVisibility(View.VISIBLE);
				((EditText) v.findViewById(R.id.day1)).setText(String
						.valueOf(cls.getQ612_1_day()));
				break;

			case 0:
				((Spinner) v.findViewById(R.id.spinner1)).setSelection(2);
				break;

			case 66:
				((Spinner) v.findViewById(R.id.spinner1)).setSelection(3);
				break;

			case 99:
				((Spinner) v.findViewById(R.id.spinner1)).setSelection(4);
				break;

			case 77:
				((Spinner) v.findViewById(R.id.spinner1)).setSelection(5);

				break;

			default:
				break;
			}

		}
		if (cls.getq612_1_other() != null) {
			if (cls.getq612_1_other().toString().length() > 0) {

				((CheckBox) v.findViewById(R.id.checkBox1)).setChecked(true);
				((EditText) v.findViewById(R.id.et1))
						.setVisibility(View.VISIBLE);
				((EditText) v.findViewById(R.id.et1)).setText(cls
						.getq612_1_other());
			}
		}

		if (cls.getQ612_2() >= 0) {
			((CheckBox) v.findViewById(R.id.chk2)).setChecked(true);
			((Spinner) v.findViewById(R.id.spinner2))
					.setVisibility(View.VISIBLE);
			switch (cls.getQ612_2()) {
			case 1:
				((Spinner) v.findViewById(R.id.spinner2)).setSelection(1);
				((EditText) v.findViewById(R.id.day2)).setText(String
						.valueOf(cls.getQ612_2_day()));
				break;

			case 0:
				((Spinner) v.findViewById(R.id.spinner2)).setSelection(2);
				break;

			case 66:
				((Spinner) v.findViewById(R.id.spinner2)).setSelection(3);
				break;

			case 99:
				((Spinner) v.findViewById(R.id.spinner2)).setSelection(4);
				break;

			case 77:
				((Spinner) v.findViewById(R.id.spinner2)).setSelection(5);

				break;

			default:
				break;
			}

		}
		if (cls.getQ612_3() >= 0) {
			((CheckBox) v.findViewById(R.id.chk3)).setChecked(true);
			((Spinner) v.findViewById(R.id.spinner3))
					.setVisibility(View.VISIBLE);
			switch (cls.getQ612_3()) {
			case 1:
				((Spinner) v.findViewById(R.id.spinner3)).setSelection(1);
				((EditText) v.findViewById(R.id.day3)).setText(String
						.valueOf(cls.getQ612_3_day()));
				break;

			case 0:
				((Spinner) v.findViewById(R.id.spinner3)).setSelection(2);
				break;

			case 66:
				((Spinner) v.findViewById(R.id.spinner3)).setSelection(3);
				break;

			case 99:
				((Spinner) v.findViewById(R.id.spinner3)).setSelection(4);
				break;

			case 77:
				((Spinner) v.findViewById(R.id.spinner3)).setSelection(5);
				/*
				 * ((EditText) v.findViewById(R.id.et3)).setText(cls
				 * .getq612_3_other());
				 */
				break;

			default:
				break;
			}

		}

		if (cls.getq612_3_other() != null) {
			if (cls.getq612_3_other().toString().length() > 0) {

				((CheckBox) v.findViewById(R.id.checkBox2)).setChecked(true);
				((EditText) v.findViewById(R.id.et3))
						.setVisibility(View.VISIBLE);
				((EditText) v.findViewById(R.id.et3)).setText(cls
						.getq612_3_other());
			}
		}
		if (cls.getQ612_4() >= 0) {
			((CheckBox) v.findViewById(R.id.chk4)).setChecked(true);
			((Spinner) v.findViewById(R.id.spinner4))
					.setVisibility(View.VISIBLE);
			switch (cls.getQ612_4()) {
			case 1:
				((Spinner) v.findViewById(R.id.spinner4)).setSelection(1);
				((EditText) v.findViewById(R.id.day4)).setText(String
						.valueOf(cls.getQ612_4_day()));
				break;

			case 0:
				((Spinner) v.findViewById(R.id.spinner4)).setSelection(2);
				break;

			case 66:
				((Spinner) v.findViewById(R.id.spinner4)).setSelection(3);
				break;

			case 99:
				((Spinner) v.findViewById(R.id.spinner4)).setSelection(4);
				break;

			case 77:
				((Spinner) v.findViewById(R.id.spinner4)).setSelection(5);

				break;

			default:
				break;
			}

		}

		if (cls.getq612_3_other() != null) {
			if (cls.getq612_3_other().toString().length() > 0) {

				((CheckBox) v.findViewById(R.id.checkBox3)).setChecked(true);
				((EditText) v.findViewById(R.id.et4))
						.setVisibility(View.VISIBLE);
				((EditText) v.findViewById(R.id.et4)).setText(cls
						.getq612_3_other());

			}
		}
		if (cls.getQ612_5() >= 0) {
			((CheckBox) v.findViewById(R.id.chk5)).setChecked(true);
			((Spinner) v.findViewById(R.id.spinner5))
					.setVisibility(View.VISIBLE);
			switch (cls.getQ612_5()) {
			case 00:
				((Spinner) v.findViewById(R.id.spinner5)).setSelection(1);

				break;

			case 88:
				((Spinner) v.findViewById(R.id.spinner5)).setSelection(2);
				break;

			default:
				break;
			}

		}

		if (cls.getQ612_6() >= 0) {
			((CheckBox) v.findViewById(R.id.chk6)).setChecked(true);
			((Spinner) v.findViewById(R.id.spinner6))
					.setVisibility(View.VISIBLE);
			switch (cls.getQ612_6()) {
			case 1:
				((Spinner) v.findViewById(R.id.spinner6)).setSelection(1);
				((EditText) v.findViewById(R.id.day61)).setText(String
						.valueOf(cls.getQ612_6_day()));
				break;

			case 0:
				((Spinner) v.findViewById(R.id.spinner6)).setSelection(2);
				break;

			case 66:
				((Spinner) v.findViewById(R.id.spinner6)).setSelection(3);
				break;

			case 99:
				((Spinner) v.findViewById(R.id.spinner6)).setSelection(4);
				break;

			case 77:
				((Spinner) v.findViewById(R.id.spinner6)).setSelection(5);
				/*
				 * ((EditText) v.findViewById(R.id.et6)).setText(cls
				 * .getq612_6_other());
				 */
				break;

			default:
				break;
			}

		}

		if (cls.getq612_6_other() != null) {
			if (cls.getq612_6_other().toString().length() > 0) {

				((CheckBox) v.findViewById(R.id.checkBox4)).setChecked(true);
				((EditText) v.findViewById(R.id.et6))
						.setVisibility(View.VISIBLE);
				((EditText) v.findViewById(R.id.et6)).setText(cls
						.getq612_6_other());
			}
		}
		if (CommonStaticClass.langBng) {
			setfonttobangla(v);
		}

	}

	private void setfonttobangla(ViewGroup v) {
		((CheckBox) findViewById(R.id.chk1))
				.setText("wkÃ¯ Lv`Â¨ â€ hgb jÂ¨vKâ€¡Uvâ€¡Rb A_ev bvb A_ev evâ€¡qvwgj,gvBeq AbÂ¨vbÂ¨?");
		((CheckBox) findViewById(R.id.chk1)).setTypeface(font);

		((CheckBox) findViewById(R.id.chk2))
				.setText("cvwbâ€¡Z ev `yâ€¡a wmÃ— Kiv kmÂ¨ RvZxq Lvevi â€ hgb: mywR A_ev AbÂ¨vbÂ¨ Lvevi hv f~Ã†v&i Ë†Zix, â€ hÂ¸â€¡jv â€ `vKvâ€¡b wKbâ€¡Z cvIqv hvq?");
		((CheckBox) findViewById(R.id.chk2)).setTypeface(font);

		((CheckBox) findViewById(R.id.chk3))
				.setText("A_ev wkÃ¯â€¡`i Lv`Â¨ kmÂ¨ â€ hgb â€ mâ€¡ijvK?");
		((CheckBox) findViewById(R.id.chk3)).setTypeface(font);

		((CheckBox) findViewById(R.id.chk4))
				.setText("cywÃ³KYv, gwbwgâ€¢ wgwkÂªZ Lvevi [Â¸ov ev gvBâ€¡ÂµvwbDwUÂªâ€¡qÃ› `vbv hv evRvâ€¡i cvIqv hvq]?");
		((CheckBox) findViewById(R.id.chk4)).setTypeface(font);

		((CheckBox) findViewById(R.id.chk5))
				.setText("â€¡mvbvgwb hv Avgvâ€¡`i â€¡_â€¡K â€ câ€¡qâ€¡Qb? hw` Iqvk-â€¡ewbwdU G AÅ¡ï¿½?fÂ©~Â³ nIqvi Ãºi Ãºi nq Zvnâ€¡j â€œ00ï¿½?  â€ KvW KiÃ¦b | hw` wkÃ¯wUi eqm 6 gvâ€¡mi â€ ekx  nq Ges â€ m Iqvk-â€¡ewbwdU â€ _â€¡K â€ Kvb LNS bv â€¡câ€¡q _vâ€¡K  Zvnâ€¡j â€œ88ï¿½? â€ KvW KiÃ¦b |");
		((CheckBox) findViewById(R.id.chk4)).setTypeface(font);

		((CheckBox) findViewById(R.id.chk6))
				.setText("AbÂ¨ â€¡h â€ Kvb (Gj Gb Gm) ev mÂ¤Ãº~iK cywÃ³/cywÃ³ cÂ¨vâ€¡KU?");
		((CheckBox) findViewById(R.id.chk6)).setTypeface(font);

	}

	private void updateTableDataFrmMultipleChoiceRadio(custom_class c) {

		if (c.save(c)) {
			CommonStaticClass.findOutNextSLNo(
					CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar(),
					CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQnext1());
			CommonStaticClass.nextQuestion(ParentActivity.this);
		}

	}

	/*
	 * private boolean ValidActivity(View v) {
	 * 
	 * int nrOfChildren = ((GridView) v).getChildCount(); boolean alltrue =
	 * true; for (int i = 0; i < nrOfChildren; i++) { View view =
	 * v.getChildAt(i);
	 * 
	 * if (view instanceof EditText) { if (!(((EditText)
	 * view).getText().toString().length() > 0)) {
	 * 
	 * if (view.getVisibility() == View.GONE) { continue; } switch
	 * (view.getId()) { case R.id.q6:
	 * 
	 * if (((EditText) view).getText().toString().length() <= 0) { return
	 * alltrue = false;
	 * 
	 * } break; case R.id.q7:
	 * 
	 * if (((EditText) view).getText().toString().length() <= 0) { return
	 * alltrue = false;
	 * 
	 * } break;
	 * 
	 * case R.id.q8:
	 * 
	 * if (((EditText) view).getText().toString().length() <= 0) { return
	 * alltrue = false;
	 * 
	 * } break;
	 * 
	 * case R.id.q11:
	 * 
	 * if (((EditText) view).getText().toString().length() <= 0) { return
	 * alltrue = false;
	 * 
	 * } break;
	 * 
	 * case R.id.q13:
	 * 
	 * if (((EditText) view).getText().toString().length() <= 0) { return
	 * alltrue = false;
	 * 
	 * } break;
	 * 
	 * case R.id.q15:
	 * 
	 * if (((EditText) view).getText().toString().length() <= 0) { return
	 * alltrue = false;
	 * 
	 * } break; case R.id.q16:
	 * 
	 * if (((EditText) view).getText().toString().length() <= 0) { return
	 * alltrue = false;
	 * 
	 * } break; default: break; } } }
	 * 
	 * 
	 * else if (view instanceof RadioButton) {
	 * 
	 * switch (view.getId()) { case R.id.q9: if (rdoq26Dontknow.isChecked() ==
	 * false) { if (q26dayset.getText().toString().length() <= 0 ||
	 * q26monthset.getText().toString().length() <= 0)
	 * 
	 * { return alltrue = false; }
	 * 
	 * } break; } }
	 * 
	 * 
	 * else if (view instanceof RadioGroup) {
	 * 
	 * if (view.getVisibility() == View.GONE) { continue; }
	 * 
	 * switch (view.getId()) { case R.id.q9: if (((RadioGroup)
	 * findViewById(R.id.q9)) .getCheckedRadioButtonId() == -1) return alltrue =
	 * false; break;
	 * 
	 * case R.id.q10: if (((RadioGroup) findViewById(R.id.q10))
	 * .getCheckedRadioButtonId() == -1) return alltrue = false; break;
	 * 
	 * case R.id.q12: if (((RadioGroup) findViewById(R.id.q12))
	 * .getCheckedRadioButtonId() == -1) return alltrue = false; break;
	 * 
	 * case R.id.q14: if (((RadioGroup) findViewById(R.id.q14))
	 * .getCheckedRadioButtonId() == -1) { if (q10.equalsIgnoreCase("1") &&
	 * q12.equalsIgnoreCase("1")) {
	 * 
	 * } else { if (((RadioGroup) findViewById(R.id.q14)) .getVisibility() ==
	 * View.GONE) {
	 * 
	 * } else { if (((RadioGroup) findViewById(R.id.q14))
	 * .getCheckedRadioButtonId() == -1) { return alltrue = false; }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * break;
	 * 
	 * default: break; } }
	 * 
	 * } return alltrue; }
	 */

	private void updateTableDataFrmMultipleCheckText() {
		// TODO Auto-generated method stub
		if (!checkBox1.isChecked() && !checkBox2.isChecked()
				&& !checkBox3.isChecked() && !checkBox4.isChecked()
				&& !checkBox5.isChecked() && !checkBox6.isChecked()) {
			CommonStaticClass.showMyAlert(con, "Message",
					"Please fill at least one single box");
			return;
		} else {
			if (checkBox1.isChecked()
					&& !(editText1.getText().toString().length() > 0)) {
				CommonStaticClass.showMyAlert(con, "Message",
						"Please fill at least one single box");
				return;
			}
			if (checkBox2.isChecked()
					&& !(editText2.getText().toString().length() > 0)) {
				CommonStaticClass.showMyAlert(con, "Message",
						"Please fill at least one single box");
				return;
			}
			if (checkBox3.isChecked()
					&& !(editText3.getText().toString().length() > 0)) {
				CommonStaticClass.showMyAlert(con, "Message",
						"Please fill at least one single box");
				return;
			}

			if (checkBox4.isChecked()
					&& !(editText4.getText().toString().length() > 0)) {
				CommonStaticClass.showMyAlert(con, "Message",
						"Please fill at least one single box");
				return;
			}

		}

		String V5 = (checkBox5.isChecked()) ? "1" : "";
		String V6 = (checkBox6.isChecked()) ? "1" : "";
		String sql = String
				.format("Update tblMainQuesMc SET q4_5_1 ='%s', q4_5_2 ='%s' , q4_5_3 ='%s' , q4_5_4 ='%s' , q4_5_5 ='%s' , q4_5_6 ='%s' WHERE dataid='%s'",
						editText1.getText().toString(), editText2.getText()
								.toString(), editText3.getText().toString(),
						editText4.getText().toString(), V5, V6,
						CommonStaticClass.dataId);

		if (dbHelper.executeDMLQuery(sql)) {
			// preserveState();
			CommonStaticClass.findOutNextSLNo(
					CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar(),
					CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQnext1());
			CommonStaticClass.nextQuestion(ParentActivity.this);
		}

	}

	// method to load FrmAddress GUI and data
	private void Load_UIFrmAddress(final ViewGroup v) {
		// TODO Auto-generated method stub

		// checkDbHasPreviousDataForThisHouseHold();

		// Load Question
		qName = CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar();
		qqq = (TextView) v.findViewById(R.id.qqq);

		if (CommonStaticClass.langBng) {
			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0) {
				font = Typeface.createFromAsset(getAssets(),
						"Siyam Rupali ANSI.ttf");
				qqq.setTypeface(font);
			}
			;
			qqq.setText(CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0 ? CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng()
					: CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQdesceng());
		} else {
			qqq.setTypeface(null);
			qqq.setText(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQdesceng());
		}

		txtHoldingNo = (EditText) v.findViewById(R.id.txtholdingNo);
		txtPara = (EditText) v.findViewById(R.id.txtPara);
		txtVillage = (EditText) v.findViewById(R.id.txtVillage);
		txtunion = (EditText) v.findViewById(R.id.txtUnion);
		txtupazila = (EditText) v.findViewById(R.id.txtUpazila);
		txtDistrict = (EditText) v.findViewById(R.id.txtDistrict);
		txtPhone1 = (EditText) v.findViewById(R.id.txtPhone1);
		txtPhone2 = (EditText) v.findViewById(R.id.txtPhone2);

		// Define lebel of the answers
		Fill_labelFrmAddress(v);

		btnPrev = (Button) v.findViewById(R.id.prevButton);
		btnPrev.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				userPressedPrevious(ParentActivity.this);
			}

		});
		btnNext = (Button) v.findViewById(R.id.saveNxtButton);

		btnNext.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				updateTableDataFrmAddress();
			}

		});
		btnClear = (Button) v.findViewById(R.id.clButton);
		btnClear.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				resetViewGroup((ViewGroup) v);
			}

		});
		notesButton = (Button) v.findViewById(R.id.btnNote);
		notesButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				FraNotes();

			}

		});

	}

	// Populate the controls
	private void doFillFrmAddress(Cursor mCursor1) {
		// TODO Auto-generated method stub
		if (mCursor1.moveToFirst()) {
			do {
				String columnhhno = "holdnumber";
				String columnPara = "para";
				String columnVillage = "village";
				String columnUnion = "unionward";
				String columnUpazilla = "upazilla";
				String columnDistrict = "district";
				String columnphone1 = "phone1";
				String columnphone2 = "phone2";
				String a = "";

				if (mCursor1.getColumnIndex(columnhhno) != -1) {
					a = mCursor1.getString(mCursor1.getColumnIndex(columnhhno))
							+ "";
					txtHoldingNo.setText((a.length() > 0
							&& (!a.equalsIgnoreCase("-1")) && (!a
							.equalsIgnoreCase("null"))) ? a : "");
					a = "";
				}
				if (mCursor1.getColumnIndex(columnPara) != -1) {
					a = mCursor1.getString(mCursor1.getColumnIndex(columnPara))
							+ "";
					txtPara.setText((a.length() > 0
							&& (!a.equalsIgnoreCase("-1")) && (!a
							.equalsIgnoreCase("null"))) ? a : "");
					a = "";
				}
				if (mCursor1.getColumnIndex(columnVillage) != -1) {
					a = mCursor1.getString(mCursor1
							.getColumnIndex(columnVillage)) + "";
					txtVillage.setText((a.length() > 0
							&& (!a.equalsIgnoreCase("-1")) && (!a
							.equalsIgnoreCase("null"))) ? a : "");
					a = "";
				}
				if (mCursor1.getColumnIndex(columnUnion) != -1) {
					a = mCursor1
							.getString(mCursor1.getColumnIndex(columnUnion))
							+ "";
					txtunion.setText((a.length() > 0
							&& (!a.equalsIgnoreCase("-1")) && (!a
							.equalsIgnoreCase("null"))) ? a : "");
					a = "";
				}
				if (mCursor1.getColumnIndex(columnUpazilla) != -1) {
					a = mCursor1.getString(mCursor1
							.getColumnIndex(columnUpazilla)) + "";
					txtupazila.setText((a.length() > 0
							&& (!a.equalsIgnoreCase("-1")) && (!a
							.equalsIgnoreCase("null"))) ? a : "");
					a = "";
				}
				if (mCursor1.getColumnIndex(columnDistrict) != -1) {
					a = mCursor1.getString(mCursor1
							.getColumnIndex(columnDistrict)) + "";
					txtDistrict.setText((a.length() > 0
							&& (!a.equalsIgnoreCase("-1")) && (!a
							.equalsIgnoreCase("null"))) ? a : "");
					a = "";
				}
				if (mCursor1.getColumnIndex(columnphone1) != -1) {
					a = mCursor1.getString(mCursor1
							.getColumnIndex(columnphone1)) + "";
					txtPhone1.setText((a.length() > 0
							&& (!a.equalsIgnoreCase("-1")) && (!a
							.equalsIgnoreCase("null"))) ? a : "");
					a = "";
				}
				if (mCursor1.getColumnIndex(columnphone2) != -1) {
					a = mCursor1.getString(mCursor1
							.getColumnIndex(columnphone2)) + "";
					txtPhone2.setText((a.length() > 0
							&& (!a.equalsIgnoreCase("-1")) && (!a
							.equalsIgnoreCase("null"))) ? a : "");
					a = "";
				}

			} while (mCursor1.moveToNext());
		}
	}

	private void updateTableDataFrmAddress() {
		// TODO Auto-generated method stub
		resHHno = txtHoldingNo.getText().toString();
		resPara = txtPara.getText().toString();
		resVillage = txtVillage.getText().toString();
		resUnion = txtunion.getText().toString();
		resUpazilla = txtupazila.getText().toString();
		resDistrict = txtDistrict.getText().toString();
		resPhone1 = txtPhone1.getText().toString();
		resPhone2 = txtPhone2.getText().toString();

		// Check Validation
		if (!IsvalidFrmAddress())
			return;

		try {

			String sql = "UPDATE "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " SET  holdnumber='" + resHHno + "',para='" + resPara
					+ "', village='" + resVillage + "',unionward='" + resUnion
					+ "',upazilla='" + resUpazilla + "',district='"
					+ resDistrict + "',phone1='" + resPhone1 + "',phone2='"
					+ resPhone2 + "' where dataid='" + CommonStaticClass.dataId
					+ "'";
			// Update the table if success full go to the next question
			if (dbHelper.executeDMLQuery(sql)) {
				CommonStaticClass.findOutNextSLNo(
						qName,
						CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQnext1());
				CommonStaticClass.nextQuestion(ParentActivity.this);
			}
		} catch (Exception e) {
			CommonStaticClass.showMyAlert(con, "Exception", e.toString());
		}

	}

	private void showUserFinishDialogFrmAddress() {
		// get prompts.xml view
		LayoutInflater li = LayoutInflater.from(con);
		LinearLayout promptsView = (LinearLayout) li.inflate(R.layout.prompts,
				null);

		promptsView.removeView((EditText) promptsView
				.findViewById(R.id.editTextDialogUserInput));

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(con);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);

		final TextView textInfo = (TextView) promptsView
				.findViewById(R.id.textView1);
		textInfo.setText("Do u want to finish this cycle?");

		// set dialog message
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

						CommonStaticClass.findOutNextSLNo(
								qName,
								CommonStaticClass.questionMap.get(
										CommonStaticClass.currentSLNo)
										.getQnext1());
						CommonStaticClass.nextQuestion(ParentActivity.this);

					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
	}

	private boolean IsvalidFrmAddress() {
		boolean Isvalid = false;

		if (resPara.length() == 0) {
			CommonStaticClass.showMyAlert(con, "Error", "Para Name is Empty");
			return Isvalid;
		}
		if (resVillage.length() == 0) {
			CommonStaticClass
					.showMyAlert(con, "Error", "Village Name is Empty");
			return Isvalid;
		}
		if (resUnion.length() == 0) {
			CommonStaticClass.showMyAlert(con, "Error", "Union Name is Empty");
			return Isvalid;
		}
		if (resUpazilla.length() == 0) {
			CommonStaticClass.showMyAlert(con, "Error",
					"Upazilla Name is Empty");
			return Isvalid;
		}
		if (resDistrict.length() == 0) {
			CommonStaticClass.showMyAlert(con, "Error",
					"District Name is Empty");
			return Isvalid;
		}
		if (resPhone1.length() == 0) {
			CommonStaticClass.showMyAlert(con, "Error", "Phone Name is Empty");
			return Isvalid;
		} else {
			if (!resPhone1.startsWith("01")) {
				CommonStaticClass.showMyAlert(con, "Error",
						"Wrong combination of phone no.");
				return Isvalid;
			}
			if (resPhone1.length() > 11 || resPhone1.length() < 11) {
				CommonStaticClass.showMyAlert(con, "Error",
						"Please inser 11 digit phone no.");
				return Isvalid;
			}

		}

		return true;

	}

	private void Fill_labelFrmAddress(ViewGroup v) {
		lblHoldingNo = (TextView) v.findViewById(R.id.lblholdingNo);
		lblPara = (TextView) v.findViewById(R.id.lblPara);
		lblVillage = (TextView) v.findViewById(R.id.lblVillage);
		lblUnion = (TextView) v.findViewById(R.id.lblUnion);
		lblUpazilla = (TextView) v.findViewById(R.id.lblUpazila);
		lblDistrict = (TextView) v.findViewById(R.id.lblDistrict);
		lblPhone1 = (TextView) v.findViewById(R.id.lblPhone1);
		lblPhone2 = (TextView) v.findViewById(R.id.lblPhone2);

		if (CommonStaticClass.langBng) {

			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0) {
				qqq.setTypeface(font);
			}
			;
			lblHoldingNo.setTypeface(font);
			lblHoldingNo.setText(con.getResources().getString(
					R.string.lblHoldingNoBN));
			lblPara.setTypeface(font);
			lblPara.setText(con.getResources().getString(R.string.lblParaBN));
			lblVillage.setTypeface(font);
			lblVillage.setText(con.getResources().getString(
					R.string.lblVillageBN));
			lblUnion.setTypeface(font);
			lblUnion.setText(con.getResources().getString(R.string.lblUnionBN));
			lblUpazilla.setTypeface(font);
			lblUpazilla.setText(con.getResources().getString(
					R.string.lblUpazillaBN));
			lblDistrict.setTypeface(font);
			lblDistrict.setText(con.getResources().getString(
					R.string.lblDistrictBN));
			lblPhone1.setTypeface(font);
			lblPhone1
					.setText(con.getResources().getString(R.string.lblPhoneBN));
			lblPhone2.setTypeface(font);
			lblPhone2
					.setText(con.getResources().getString(R.string.lblPhoneBN));

			qqq.setText(CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0 ? CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng()
					: CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQdesceng());
		} else {
			lblHoldingNo.setTypeface(null);

			lblPara.setTypeface(null);

			lblVillage.setTypeface(null);

			lblUnion.setTypeface(null);

			lblUpazilla.setTypeface(null);

			lblDistrict.setTypeface(null);

			lblPhone1.setTypeface(null);

			lblPhone2.setTypeface(null);

			lblHoldingNo.setText("Holding Number (if any):  ");

			lblPara.setText("Para / mahalla");

			lblVillage.setText("Village");

			lblUnion.setText("Union / ward");

			lblUpazilla.setText("Upazilla / Municipality / CC");

			lblDistrict.setText("District");

			lblPhone1.setText("Phone number 1: ");

			lblPhone2.setText("Phone number 2: ");

			qqq.setTypeface(null);
			qqq.setText(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQdesceng());
		}

	}

	private void Load_DataFrmAddress() {
		String sql = "Select * from "
				+ CommonStaticClass.questionMap.get(
						CommonStaticClass.currentSLNo).getTablename()
				+ " where dataid='" + CommonStaticClass.dataId + "'";
		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.getCount() > 0) {
				doFillFrmAddress(mCursor1);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}
	}

	private boolean characterCheckingFrmAddress(String qAns) {
		boolean allch = true;
		String a = qAns.toLowerCase();
		char[] cArray = a.toCharArray();
		for (char c : cArray) {
			if (c == ' ' || c == '.') {
				continue;
			}
			if (c < 'a' || c > 'z') {
				allch = false;
				return allch;
			}
		}
		return allch;
	}

	// frmcombobox part
	private void Load_UIFrmComboBox(final ViewGroup v) {
		if (users != null && users.size() > 0) {
			users.clear();
		}
		if (userIDs != null && userIDs.size() > 0) {
			userIDs.clear();
		}
		// TODO Auto-generated method stub
		qqq = (TextView) v.findViewById(R.id.qqq);
		op = new Options();

		if (CommonStaticClass.langBng) {
			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0) {
				Typeface font = Typeface.createFromAsset(getAssets(),
						"Siyam Rupali ANSI.ttf");
				qqq.setTypeface(font);
			}
			;
			qqq.setText(CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0 ? CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng()
					: CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQdesceng());
		} else {
			qqq.setTypeface(null);
			qqq.setText(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQdesceng());
		}

		checkBoxHolder = (LinearLayout) v.findViewById(R.id.checkBoxHolder);
		checkBoxHolder.removeAllViews();
		LinearLayout.LayoutParams lnlParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams layoutParamForSpin = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);

		LinearLayout ln = new LinearLayout(this);
		ln.setOrientation(LinearLayout.HORIZONTAL);

		// Add spinner control
		final Spinner spinner = new Spinner(this);
		layoutParamForSpin.weight = 1;
		ln.addView(spinner, 0, layoutParamForSpin);
		// added by zaman
		if (CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo)
				.getQvar().equalsIgnoreCase("userid")
				|| CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar()
						.equalsIgnoreCase("memberid")
				|| CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar()
						.equalsIgnoreCase("Q17aa")

				|| CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar()
						.equalsIgnoreCase("Q19aa")

				|| CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar()
						.equalsIgnoreCase("Q21aa")

				|| CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar()
						.equalsIgnoreCase("q11_1")

				|| CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar()
						.equalsIgnoreCase("c1_8aa")) {
			// for Reading data from a specific table like user, member etc.
			Cursor mCursor = null;
			String sql = "";
			users = new ArrayList<String>();
			users.add("");
			userIDs = new ArrayList<String>();
			userIDs.add("");

			try {
				if (CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar()
						.equalsIgnoreCase("q9ss"))
					sql = String.format("Select * from tblDisease");
				else if (CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar()
						.equalsIgnoreCase("Q19aa")
						|| CommonStaticClass.questionMap
								.get(CommonStaticClass.currentSLNo).getQvar()
								.equalsIgnoreCase("Q21aa"))
					sql = String.format("Select * from tblCountry");

				else if (CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar()
						.equalsIgnoreCase("Q17aa"))

					/*
					 * sql = String.format(
					 * "select * from tblCluster where Clusterid = (select clusterid from tblMainQues where dataid =  '%s')"
					 * ,CommonStaticClass.dataId);
					 */
					sql = String
							.format("select * from District Order By DistName");
				else if (CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar()
						.equalsIgnoreCase("q11_1"))
					sql = String
							.format("select ID,Name from tblUser where Active = 'Y'");

				else if (CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar()
						.equalsIgnoreCase("upaaaa"))

					/*
					 * sql = String.format(
					 * "select * from tblCluster where Clusterid = (select clusterid from tblMainQues where dataid =  '%s')"
					 * ,CommonStaticClass.dataId);
					 */
					sql = String
							.format("select * from tblUpazila where Distcode = (select q10 from tblMainQuesMc where dataid =  '%s')",
									CommonStaticClass.dataId);

				else if (CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar()
						.equalsIgnoreCase("c1_8aaa"))
					// sql = "Select * from tblCluster";
					sql = String.format("select * from tblOccupation",
							CommonStaticClass.dataId);

				mCursor = dbHelper.getQueryCursor(sql);
				if (mCursor.moveToFirst()) {
					do {
						if (CommonStaticClass.questionMap
								.get(CommonStaticClass.currentSLNo).getQvar()
								.equalsIgnoreCase("Q17aa"))

						{
							users.add(mCursor.getString(mCursor
									.getColumnIndex("DistCode"))
									+ " : "
									+ mCursor.getString(mCursor
											.getColumnIndex("DistName")));
							userIDs.add(mCursor.getString(mCursor
									.getColumnIndex("DistCode")));

						} else if (CommonStaticClass.questionMap
								.get(CommonStaticClass.currentSLNo).getQvar()
								.equalsIgnoreCase("memberid")) {
							users.add(mCursor.getString(mCursor
									.getColumnIndex("memberid"))
									+ " : "
									+ mCursor.getString(mCursor
											.getColumnIndex("mname")));
							userIDs.add(mCursor.getString(mCursor
									.getColumnIndex("memberid")));

						} else if (CommonStaticClass.questionMap
								.get(CommonStaticClass.currentSLNo).getQvar()
								.equalsIgnoreCase("Q19aa")
								|| CommonStaticClass.questionMap
										.get(CommonStaticClass.currentSLNo)
										.getQvar().equalsIgnoreCase("Q21aa")) {
							users.add(mCursor.getString(mCursor
									.getColumnIndex("Countrycode"))
									+ " : "
									+ mCursor.getString(mCursor
											.getColumnIndex("CountryName")));
							userIDs.add(mCursor.getString(mCursor
									.getColumnIndex("Countrycode")));

						}
						// for question 1_2 to 1_6

						else if (CommonStaticClass.questionMap
								.get(CommonStaticClass.currentSLNo).getQvar()
								.equalsIgnoreCase("q1_3aaa")) {
							users.add(mCursor.getString(mCursor
									.getColumnIndex("Clusterid"))
									+ " : "
									+ mCursor.getString(mCursor
											.getColumnIndex("Clusterid")));
							userIDs.add(mCursor.getString(mCursor
									.getColumnIndex("Clusterid")));

						} else if (CommonStaticClass.questionMap
								.get(CommonStaticClass.currentSLNo).getQvar()
								.equalsIgnoreCase("q10aaaa")
								|| CommonStaticClass.questionMap
										.get(CommonStaticClass.currentSLNo)
										.getQvar().equalsIgnoreCase("q12a")) {
							users.add(mCursor.getString(mCursor
									.getColumnIndex("ID"))
									+ " : "
									+ mCursor.getString(mCursor
											.getColumnIndex("Name")));
							userIDs.add(mCursor.getString(mCursor
									.getColumnIndex("ID")));

						} else if (CommonStaticClass.questionMap
								.get(CommonStaticClass.currentSLNo).getQvar()
								.equalsIgnoreCase("q11_1")) {
							users.add(mCursor.getString(mCursor
									.getColumnIndex("ID"))
									+ " : "
									+ mCursor.getString(mCursor
											.getColumnIndex("Name")));
							userIDs.add(mCursor.getString(mCursor
									.getColumnIndex("ID")));

						} else if (CommonStaticClass.questionMap
								.get(CommonStaticClass.currentSLNo).getQvar()
								.equalsIgnoreCase("upaaaa")) {
							users.add(mCursor.getString(mCursor
									.getColumnIndex("UpazilaCode"))
									+ " : "
									+ mCursor.getString(mCursor
											.getColumnIndex("UpazilaName")));
							userIDs.add(mCursor.getString(mCursor
									.getColumnIndex("UpazilaCode")));

						} else if (CommonStaticClass.questionMap
								.get(CommonStaticClass.currentSLNo).getQvar()
								.equalsIgnoreCase("c1_8aaa")) {

							if (CommonStaticClass.langBng) {
								users.add(mCursor.getString(mCursor
										.getColumnIndex("Code"))
										+ " : "
										+ mCursor.getString(mCursor
												.getColumnIndex("OccupationBng")));

							} else {
								users.add(mCursor.getString(mCursor
										.getColumnIndex("Code"))
										+ " : "
										+ mCursor.getString(mCursor
												.getColumnIndex("Occupation")));
							}

							userIDs.add(mCursor.getString(mCursor
									.getColumnIndex("Code")));

						}
					} while (mCursor.moveToNext());
				}
				adapterForCombo = new ArrayAdapter(this,
						android.R.layout.simple_spinner_item, users);
				adapterForCombo
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				// adapterForCombo.setDropDownViewResource(R.layout.checkedspintextview);
				spinner.setAdapter(adapterForCombo);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (mCursor != null)
					mCursor.close();
			}

		} 
		//code by imtiaz khan
		else if(CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar()
				.equalsIgnoreCase("q11_2"))
		{
			users = new ArrayList<String>();
			users.add("");
			userIDs = new ArrayList<String>();
			userIDs.add("");
			for(int i = 1 ; i<= 31; i++)
			{
				users.add(i+"");
				userIDs.add(i+"");
			}
			
			adapterForCombo = new ArrayAdapter(this,
					android.R.layout.simple_spinner_item, users);
			adapterForCombo
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			// adapterForCombo.setDropDownViewResource(R.layout.checkedspintextview);
			spinner.setAdapter(adapterForCombo);
		}
		else // applicable when Spinner is loaded from options table
		{
			op = CommonStaticClass.findOptionsForThisQuestion(
					dbHelper,
					CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar());
			op.capBngList.add(0, "");
			op.capEngList.add(0, "");
			op.codeList.add(0, -1);

			if (CommonStaticClass.langBng) {
				adapterForCombo = new SpinAdapter(this, op.capBngList, true);

			} else {
				adapterForCombo = new ArrayAdapter(this,
						android.R.layout.simple_spinner_item, op.capEngList);

			}
			adapterForCombo
					.setDropDownViewResource(CommonStaticClass.langBng ? R.layout.checkedspintextview
							: android.R.layout.simple_spinner_dropdown_item);
			spinner.setAdapter(adapterForCombo);
			// spinner.setOnItemSelectedListener(new spinItemSelectListener());

		}
		// Slected Index change event of Spinner
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				// TODO Auto-generated method stub
				if (parent.getItemAtPosition(pos).toString().length() > 0) {
					if (CommonStaticClass.questionMap
							.get(CommonStaticClass.currentSLNo).getQvar()
							.equalsIgnoreCase("userid")
							|| CommonStaticClass.questionMap
									.get(CommonStaticClass.currentSLNo)
									.getQvar().equalsIgnoreCase("Q17aa")

							|| CommonStaticClass.questionMap
									.get(CommonStaticClass.currentSLNo)
									.getQvar().equalsIgnoreCase("Q19aa")
							|| CommonStaticClass.questionMap
									.get(CommonStaticClass.currentSLNo)
									.getQvar().equalsIgnoreCase("q11_1")
							|| CommonStaticClass.questionMap
									.get(CommonStaticClass.currentSLNo)
									.getQvar().equalsIgnoreCase("q1_5aa")
							|| CommonStaticClass.questionMap
									.get(CommonStaticClass.currentSLNo)
									.getQvar().equalsIgnoreCase("c1_8aa")
							|| CommonStaticClass.questionMap
									.get(CommonStaticClass.currentSLNo)
									.getQvar().equalsIgnoreCase("c1_10aa"))
						sResCode = parent
								.getItemAtPosition(pos)
								.toString()
								.substring(
										0,
										(parent.getItemAtPosition(pos)
												.toString().lastIndexOf(":") - 1));
					
					else if(CommonStaticClass.questionMap
							.get(CommonStaticClass.currentSLNo)
							.getQvar().equalsIgnoreCase("q11_2") )
					{
						sResCode = parent
								.getItemAtPosition(pos)
								.toString();
					}
					else
						sResCode = op.codeList.get(pos).toString();
				} else {
					sResCode = "";
				}

			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});

		// if(CommonStaticClass.mode.equalsIgnoreCase(CommonStaticClass.EDITMODE)){
		// String sql =
		// "Select "+CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo).getQvar()+" from "+CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo).getTablename()+" where dataid='"+CommonStaticClass.dataId+"'";

		// Data Load
		String sql = "";
		if (CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo)
				.getQvar().equalsIgnoreCase("memberid"))
			sql = "Select * from tblFamilyMember where dataid='"
					+ CommonStaticClass.dataId + "'";// As data should be loaded
														// from family member
														// table
		else {
			sql = "Select * from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " where dataid='" + CommonStaticClass.dataId + "'";
			Cursor mCursor1 = null;
			int index = -1;
			try {
				mCursor1 = dbHelper.getQueryCursor(sql);
				if (mCursor1.moveToFirst()) {
					do {
						String column = CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQvar();

						if (mCursor1.getColumnIndex(column) != -1) {
							String a = mCursor1.getString(mCursor1
									.getColumnIndex(column)) + "";

							if (CommonStaticClass.questionMap
									.get(CommonStaticClass.currentSLNo)
									.getQvar().equalsIgnoreCase("userid")
									|| CommonStaticClass.questionMap
											.get(CommonStaticClass.currentSLNo)
											.getQvar()
											.equalsIgnoreCase("Q17aa")

									|| CommonStaticClass.questionMap
											.get(CommonStaticClass.currentSLNo)
											.getQvar().equalsIgnoreCase("q11_1")
									|| CommonStaticClass.questionMap
										.get(CommonStaticClass.currentSLNo)
										.getQvar().equalsIgnoreCase("q11_2")

									|| CommonStaticClass.questionMap
											.get(CommonStaticClass.currentSLNo)
											.getQvar()
											.equalsIgnoreCase("Q21aa")

									|| CommonStaticClass.questionMap
											.get(CommonStaticClass.currentSLNo)
											.getQvar()
											.equalsIgnoreCase("q1_5aa")

									|| CommonStaticClass.questionMap
											.get(CommonStaticClass.currentSLNo)
											.getQvar()
											.equalsIgnoreCase("c1_8aa")

									|| CommonStaticClass.questionMap
											.get(CommonStaticClass.currentSLNo)
											.getQvar()
											.equalsIgnoreCase("c1_10aa"))

								index = CommonStaticClass
										.GetIndexFromCollection(userIDs, a);
							else
								index = CommonStaticClass
										.GetIndexFromCollection(op.codeList, a);

							if (index != -1)
								spinner.setSelection(index);

						}
					} while (mCursor1.moveToNext());
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				if (mCursor1 != null)
					mCursor1.close();
			}
		}
		// }
		// End of addition

		checkBoxHolder.addView(ln, 0, lnlParams);

		prevButton = (Button) v.findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				userPressedPrevious(ParentActivity.this);
			}

		});
		saveNxtButton = (Button) v.findViewById(R.id.saveNxtButton);
		saveNxtButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				updateTableDataFrmComboBox();
				// preserveState();
			}

		});

		clButton = (Button) v.findViewById(R.id.clButton);
		clButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				resetViewGroup((ViewGroup) v);
			}

		});
		notesButton = (Button) v.findViewById(R.id.btnNote);
		notesButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				FraNotes();

			}

		});

	}

	private void updateTableDataFrmComboBox() {
		// TODO Auto-generated method stub
		if(sResCode.equalsIgnoreCase(""))
		{
			CommonStaticClass.showMyAlert(con, "Alert", "Must selecet an item in order to proceed");
			return;
		}
		try {
			String sql = "";
			if ((CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQvar()
					.equalsIgnoreCase("Q17")
					|| CommonStaticClass.questionMap
							.get(CommonStaticClass.currentSLNo).getQvar()
							.equalsIgnoreCase("Q19") || CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQvar()
					.equalsIgnoreCase("Q21"))
					&& sResCode.length() == 0) {
				return;
			}
			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQvar()
					.equalsIgnoreCase("memberid")) {
				String entryBy = CommonStaticClass.userSpecificId;
				Date d = new Date(System.currentTimeMillis());
				String entryDate = "dd-mmm-yy";
				entryDate = d.toLocaleString();

				String sqlCheck = "Select * from "
						+ CommonStaticClass
								.GetTableName(CommonStaticClass.questionMap
										.get(CommonStaticClass.currentSLNo)
										.getQvar()) + " where dataid='"
						+ CommonStaticClass.dataId + "' and memberid="
						+ sResCode;
				Cursor mCursor1 = null;
				try {
					mCursor1 = dbHelper.getQueryCursor(sqlCheck);
					if (mCursor1.getCount() == 0)
						sql = "Insert into "
								+ CommonStaticClass
										.GetTableName(CommonStaticClass.questionMap
												.get(CommonStaticClass.currentSLNo)
												.getQvar())
								+ "(dataid,memberid,EntryBy,EntryDate) values('"
								+ CommonStaticClass.dataId + "'," + sResCode
								+ ",'" + entryBy + "','" + entryDate + "')";

				} catch (Exception e) {
				} finally {
					if (mCursor1 != null)
						mCursor1.close();
				}

				CommonStaticClass.isMember = false;
				// CommonStaticClass.memberID = sResCode;

				// SKIP ADDITION
				CommonStaticClass.qskipList.clear();
				String sql1 = "";
				sql1 = "Select * from tblFamilyMember where dataid='"
						+ CommonStaticClass.dataId + "' and memberid="
						+ CommonStaticClass.memberID;
				mCursor1 = null;
				try {
					mCursor1 = dbHelper.getQueryCursor(sql1);
					if (mCursor1.moveToFirst()) {
						do {
							String column1 = "anysick";
							String column2 = "visitdoc";
							String column3 = "hospitalized";

							String a = mCursor1.getString(mCursor1
									.getColumnIndex(column1)) + "";
							String b = mCursor1.getString(mCursor1
									.getColumnIndex(column2)) + "";
							String c = mCursor1.getString(mCursor1
									.getColumnIndex(column3)) + "";

							if (a.equalsIgnoreCase("0")
									|| a.equalsIgnoreCase("99")) {
								CommonStaticClass.qskipList.add("SecP1");
								nullifyWithInRange("SecP1", "SecP2");
							}
							if (b.equalsIgnoreCase("0")
									|| b.equalsIgnoreCase("99")) {
								CommonStaticClass.qskipList.add("SecP2");
								nullifyWithInRange("SecP2", "SecP3");
							}
							if (c.equalsIgnoreCase("0")
									|| c.equalsIgnoreCase("99")) {
								CommonStaticClass.qskipList.add("SecP3");
								nullifyWithInRange("SecP3", "p3_5");
							}

						} while (mCursor1.moveToNext());

						if (CommonStaticClass.qskipList.contains("SecP1")
								&& CommonStaticClass.qskipList
										.contains("SecP2")
								&& CommonStaticClass.qskipList
										.contains("SecP3")) {
							CommonStaticClass
									.showMyAlert(con, "Notification",
											"Nothing to proceed for the selected member, please select another memeber");
							return;
						}

					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				} finally {
					if (mCursor1 != null)
						mCursor1.close();
				}

			}

			else
				sql = "Update "
						+ CommonStaticClass
								.GetTableName(CommonStaticClass.questionMap
										.get(CommonStaticClass.currentSLNo)
										.getQvar())
						+ " set "
						+ CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQvar() + "='"
						+ sResCode + "' Where DataID='"
						+ CommonStaticClass.dataId + "'";

			dbHelper.executeDMLQuery(sql);

			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQvar()
					.equalsIgnoreCase("q9")) {
				String val = dbHelper.GetSingleColumnData("q9");
				if (val.equalsIgnoreCase("99")) {
					CommonStaticClass.findOutNextSLNo(
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQvar(),
							"q9_other");
					CommonStaticClass.nextQuestion(ParentActivity.this);
					return;
				}
			}
			CommonStaticClass.findOutNextSLNo(
					CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar(),
					CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQnext1());
			CommonStaticClass.nextQuestion(ParentActivity.this);
		} catch (Exception e) {
			e.printStackTrace();
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

	private void loadGuiFrmDate(final ViewGroup v) {
		// TODO Auto-generated method stub
		qqq = (TextView) v.findViewById(R.id.qqq);

		if (CommonStaticClass.langBng) {
			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0) {
				Typeface font = Typeface.createFromAsset(getAssets(),
						"Siyam Rupali ANSI.ttf");
				qqq.setTypeface(font);
			}
			;
			qqq.setText(CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0 ? CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng()
					: CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQdesceng());
		} else {
			qqq.setTypeface(null);
			qqq.setTypeface(null);
			qqq.setText(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQdesceng());
		}

		pickDate = (EditText) v.findViewById(R.id.pickDate);

		String sql = "Select * from "
				+ CommonStaticClass.questionMap.get(
						CommonStaticClass.currentSLNo).getTablename()
				+ " where dataid='" + CommonStaticClass.dataId + "'";
		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.moveToFirst()) {
				do {
					String column = CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar();

					if (mCursor1.getColumnIndex(column) != -1) {
						String val = mCursor1.getString(mCursor1
								.getColumnIndex(column)) + "";
						pickDate.setText((val.length() > 0 && !val
								.equalsIgnoreCase("null")) ? val : "");
					}
				} while (mCursor1.moveToNext());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}

		pickDate.setOnTouchListener(new View.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				showDialog(DATE_DIALOG);
				return false;
			}
		});

		final Calendar c = Calendar.getInstance();
		dateYear = c.get(Calendar.YEAR);
		dateMonth = c.get(Calendar.MONTH);
		dateDay = c.get(Calendar.DAY_OF_MONTH);
		if (!(pickDate.getText().toString().length() > 0)) {
			if (!CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQvar()
					.equalsIgnoreCase("onsetdt")) {
				updateDisplay("date");
			}
		}

		prevButton = (Button) v.findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				userPressedPrevious(ParentActivity.this);
			}
		});
		saveNxtButton = (Button) v.findViewById(R.id.saveNxtButton);

		saveNxtButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				updateTableDataFrmDate();
			}

		});
		clButton = (Button) v.findViewById(R.id.clButton);
		clButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				resetViewGroup((ViewGroup) v);
			}

		});
		notesButton = (Button) v.findViewById(R.id.btnNote);
		notesButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				FraNotes();

			}

		});

	}

	/*
	 * private void updateDisplay(String dt) {
	 * 
	 * if (dt.contains("time")) { String time = new StringBuilder() // Month is
	 * 0 based so add 1
	 * .append(TimeHour).append(":").append(TimeMinute).toString(); //
	 * Log.d("Date: ",date); pickTime.setText(time); }
	 * 
	 * if (dt.contains("date")) { String date = new StringBuilder() // Month is
	 * 0 based so add 1 .append(dateDay).append(" ") .append(GetMonth(dateMonth
	 * + 1)).append(" ") .append(dateYear).toString(); // Log.d("Date: ",date);
	 * pickDate.setText(date); }
	 * 
	 * }
	 */
	EditText etpickdate;

	private void updateDisplayfrmfamily(String dt, EditText et) {

		if (dt.contains("date")) {
			String date = new StringBuilder()
					// Month is 0 based so add 1
					.append(dateDay).append(" ")
					.append(GetMonth(dateMonth + 1)).append(" ")
					.append(dateYear).toString();
			// Log.d("Date: ",date);
			et.setText(date);
		}

	}

	private void updateDisplay(String dt) {

		String time = new StringBuilder()

		.append(TimeHour).append(":")
		// .append(TimeMinute).toString();
				.append(String.format("%02d", TimeMinute)).toString();
		if (dt.contains("time")) {
			// Log.d("Date: ",date);
			if (qName != "" && qName.length() > 0) {
				if (qName.equalsIgnoreCase("q17hmd1")
						|| qName.equalsIgnoreCase("q18md1")
						|| qName.equalsIgnoreCase("q17hmd2")
						|| qName.equalsIgnoreCase("q18md2")) {
					ettime.setText(time);

					return;
				}
			}

			pickTime.setText(time);
		}

		if (dt.contains("date")) {
			String date = new StringBuilder()
					// Month is 0 based so add 1
					.append(dateDay).append(" ")
					.append(GetMonth(dateMonth + 1)).append(" ")
					.append(dateYear).toString();
			// Log.d("Date: ",date);
			if (qName.equalsIgnoreCase("Q3_signs_dt")) {
				{

					if (touchedBox != null) {
						touchedBox.setText(date);
						listvalues.set(touchedBox.getId(), date);
						return;
					}

				}
			}

			if (CommonStaticClass.currentSLNo == 1) {

				int localMonth = (dateMonth + 1);
				String monthString = localMonth < 10 ? "0" + localMonth
						: Integer.toString(localMonth);
				String localYear = Integer.toString(dateYear).substring(2);

				etyearmonth.setText(new StringBuilder()
						// Month is 0 based so add 1
						.append(monthString).append("/").append(localYear)
						.append(" "));
				showDialog(DATE_DIALOG_ID);
				return;

			}

			if (pickDate != null) {
				pickDate.setText(date);
			}
		}

	}

	private void updateTableDataFrmDate() {
		// TODO Auto-generated method stub
		String qAns = pickDate.getText().toString();
		try {
			String currentQuestion = CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQvar();
			if (qAns.length() > 0) {

				if (futureDateValidator(dateYear, dateMonth, dateDay)) {
					CommonStaticClass
							.showMyAlert(con, "Not Correct",
									"You are putting future date which is not acceptable");
					return;
				}

				String sql = "UPDATE "
						+ CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getTablename()
						+ " SET " + currentQuestion + "='" + qAns
						+ "' where dataid='" + CommonStaticClass.dataId + "'";
				if (dbHelper.executeDMLQuery(sql)) {
					// preserveState();
					CommonStaticClass.findOutNextSLNo(
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQvar(),
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQnext1());
					CommonStaticClass.nextQuestion(ParentActivity.this);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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

	// frmfamilymember part
	private void loadGuiFrmFamilyMember(final ViewGroup v) {
		// TODO Auto-generated method stub
		qName = CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar();
		loadAllUiViewsFrmFamilyMember(v);

		memberIDs = new ArrayList<String>();

		// loading all options
		op = CommonStaticClass.findOptionsForMedicineList(dbHelper,
				CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar(),
				medicineIDs);

		filllAllSpinnerDataFrmFamilyMember();

		btnPrev = (Button) v.findViewById(R.id.prevButton);
		btnPrev.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				userPressedPrevious(ParentActivity.this);
			}

		});
		btnsaveNxt = (Button) v.findViewById(R.id.saveNxtButton);
		btnsaveNxt.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				updateTableDataFrmFamilyMember();
			}

		});
		btnClear = (Button) v.findViewById(R.id.clButton);
		btnClear.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				resetViewGroup((ViewGroup) v);
			}

		});
		notesButton = (Button) v.findViewById(R.id.btnNote);
		notesButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				FraNotes();

			}

		});
	}

	private void filllAllSpinnerDataFrmFamilyMember() {
		// TODO Auto-generated method stub
		if (CommonStaticClass.langBng) {
			adapterSex = new SpinAdapter(this, op.capBngList, true);

		} else {
			adapterSex = new ArrayAdapter(this,
					android.R.layout.simple_spinner_item, op.capEngList);
		}

		adapterSex
				.setDropDownViewResource(CommonStaticClass.langBng ? R.layout.checkedspintextview
						: android.R.layout.simple_spinner_dropdown_item);

		spinnermedicine.setAdapter(adapterSex);
		spinnermedicine
				.setOnItemSelectedListener(new spinItemSelectListenerFrmFamilyMember());

		adapterSl = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item, memberIDs);
		adapterSl
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerSL.setAdapter(adapterSl);
		spinnerSL
				.setOnItemSelectedListener(new spinItemSelectListenerFrmFamilyMember());

		checkDataBaseHasLinesOrNotFrmFamilyMember();

	}

	private void filllAllSpinnerDataq124() {
		// TODO Auto-generated method stub
		if (CommonStaticClass.langBng) {
			adapterSex = new SpinAdapter(this, opSex.capBngList, true);
			// adapterMonth = new SpinAdapter(this,opMonth.capBngList, true);
			// adapterYear = new SpinAdapter(this,opYear.capBngList, true);

		} else {
			adapterSex = new ArrayAdapter(this,
					android.R.layout.simple_spinner_item, opSex.capEngList);
		}

		adapterSex
				.setDropDownViewResource(CommonStaticClass.langBng ? R.layout.checkedspintextview
						: android.R.layout.simple_spinner_dropdown_item);
		spinnerc2.setAdapter(adapterSex);
		spinnerc2.setOnItemSelectedListener(new spinItemSelectListenerq124());

		adapterSl = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item, memberIDs);
		adapterSl
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerc1.setAdapter(adapterSl);
		spinnerc1.setOnItemSelectedListener(new spinItemSelectListenerq124());

		checkDataBaseHasLinesOrNotq124();

	}

	private void checkDataBaseHasLinesOrNotFrmFamilyMember() {
		// TODO Auto-generated method stub
		// String sql = "Select * from '%s' where dataid = '"
		// + CommonStaticClass.dataId + "'";

		String sql = String.format("Select * from '%s' where dataid = '%s'",
				CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getTablename(),
				CommonStaticClass.dataId);

		Cursor mCursor1 = null;
		String serialNo = "";
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.getCount() > 0) {
				if (!memberIDs.isEmpty()) {
					memberIDs.clear();
				}
				int i = 1;
				if (mCursor1.moveToFirst()) {
					do {

						serialNo = String.valueOf(i);
						memberIDs.add(serialNo);

						if (i != (mCursor1.getCount() + 1)) {
							i++;
						}

					} while (mCursor1.moveToNext());

					adapterSl.notifyDataSetChanged();
					spinnerSL.setSelection(0);
				}
			} else {
				memberIDs.add("1");
				adapterSl.notifyDataSetChanged();
				spinnerSL.setSelection(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}
	}

	private void checkDataBaseHasLinesOrNotq124() {
		// TODO Auto-generated method stub
		String sql = "Select * from tblTravel where dataid = '"
				+ CommonStaticClass.dataId + "'";
		Cursor mCursor1 = null;
		String serialNo = "";
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.getCount() > 0) {
				if (!memberIDs.isEmpty()) {
					memberIDs.clear();
				}
				int i = 1;
				if (mCursor1.moveToFirst()) {
					do {
						serialNo = String.valueOf(i);
						memberIDs.add(serialNo);

						/*
						 * memberIDs.add(CommonStaticClass.dataId.substring(0,
						 * 5) + serialNo.substring(serialNo.length() - 2,
						 * serialNo.length()));
						 */
						if (i != (mCursor1.getCount() + 1)) {
							i++;
						}

					} while (mCursor1.moveToNext());

					adapterSl.notifyDataSetChanged();
					spinnerc1.setSelection(0);
				}
			} else {
				memberIDs.add("1");
				adapterSl.notifyDataSetChanged();
				spinnerc1.setSelection(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}
	}

	class spinItemSelectListenerFrmFamilyMember implements
			OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View arg1, int pos,
				long arg3) {
			// TODO Auto-generated method stub
			if (parent == spinnerSL) {
				if (parent.getItemAtPosition(pos).toString().length() > 0) {
					memberID = parent.getItemAtPosition(pos).toString();
					findDataForThisSelectionFrmFamilyMember(memberID);
				}
			} else if (parent == spinnermedicine) {
				if (parent.getItemAtPosition(pos).toString().length() > 0) {
					sex = op.codeList.get(pos) + "";
					if (sex.equalsIgnoreCase("30")
							|| sex.equalsIgnoreCase("31")
							|| sex.equalsIgnoreCase("32")
							|| sex.equalsIgnoreCase("33")) {
						((EditText) findViewById(R.id.etMedicineOther))
								.setVisibility(View.VISIBLE);
					} else {
						((EditText) findViewById(R.id.etMedicineOther))
								.setVisibility(View.GONE);
					}
				}
			}

		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}

	}

	class spinItemSelectListenerq124 implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View arg1, int pos,
				long arg3) {
			// TODO Auto-generated method stub
			if (parent == spinnerc1) {
				if (parent.getItemAtPosition(pos).toString().length() > 0) {
					memberID = parent.getItemAtPosition(pos).toString();
					findDataForThisSelectionq124(memberID);
				}
			} else if (parent == spinnerc2) {
				if (parent.getItemAtPosition(pos).toString().length() > 0) {
					sex = opSex.codeList.get(pos) + "";
					if (pos == 2) {
						((EditText) findViewById(R.id.etother))
								.setVisibility(View.VISIBLE);
					} else {
						((EditText) findViewById(R.id.etother))
								.setVisibility(View.GONE);
					}
				}
			}

		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}

	}

	private void updateTableDataFrmFamilyMember() {
		if (!IsValidFrmFamilyMember())
			return;
		else {
			Date d = new Date(System.currentTimeMillis());
			String entryDate = "dd-mmm-yy";

			entryDate = d.toLocaleString();
			// CommonStaticClass.memberID=memberIDs.get(spinnerSL.getSelectedItemPosition());
			/*
			 * String v = getSkip("q1_6", "tblMainQues"); if (v != null) { if
			 * (v.length() > 0) { if (spinnerSL.getCount() >
			 * Integer.parseInt(v)) {
			 * 
			 * if(Integer.parseInt(v)==spinnerSL.getCount()-1) {
			 * 
			 * CommonStaticClass .showMyAlert(con, "Message",
			 * "You have reached the maximum number of family member.");
			 * CommonStaticClass.findOutNextSLNo(
			 * CommonStaticClass.questionMap.get( CommonStaticClass.currentSLNo)
			 * .getQvar(), CommonStaticClass.questionMap.get(
			 * CommonStaticClass.currentSLNo) .getQnext1());
			 * CommonStaticClass.nextQuestion(ParentActivity.this); return; } }
			 * }
			 */

			String s = String
					.format("INSERT INTO '%s' (dataid, QName, EpisodeNo, MedicineID, Medicine_other1, Medicine_other2, "
							+ "Medicine_other3, Medicine_other4, qWhen, qWhenT1, qWhenT2, qtab, qcap, qDrop, qSpoon, "
							+ "EntryBy, EntryDate, memid) VALUES "
							+ "('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo)
									.getTablename(),
							CommonStaticClass.dataId,
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQvar(),
							memberIDs.get(spinnerSL.getSelectedItemPosition()),

							op.codeList.get(spinnermedicine
									.getSelectedItemPosition()),

							Medicine_other1, Medicine_other2, Medicine_other3,
							Medicine_other4, qWhen, qWhenT1, qWhenT2, qtab,
							qcap, qDrop, qSpoon,
							CommonStaticClass.userSpecificId, entryDate,
							CommonStaticClass.memberID);

			if (dbHelper.executeDMLQuery(s)) {

			} else {

				String sqlUp = String
						.format("UPDATE '%s' SET MedicineID='%s', Medicine_other1='%s', Medicine_other2='%s', "
								+ "Medicine_other3='%s', Medicine_other4='%s', qWhen='%s', qWhenT1='%s', qWhenT2='%s', "
								+ "qtab='%s', qcap='%s', qDrop='%s', qSpoon='%s', EditBy='%s', EditDate='%s' WHERE dataid='%s' "
								+ "AND QName='%s' AND EpisodeNo='%s' AND memid ='%s'",
								CommonStaticClass.questionMap.get(
										CommonStaticClass.currentSLNo)
										.getTablename(),
								op.codeList.get(spinnermedicine
										.getSelectedItemPosition()),
								Medicine_other1,
								Medicine_other2,
								Medicine_other3,
								Medicine_other4,
								qWhen,
								qWhenT1,
								qWhenT2,
								qtab,
								qcap,
								qDrop,
								qSpoon,
								CommonStaticClass.userSpecificId,
								entryDate,
								CommonStaticClass.dataId,
								CommonStaticClass.questionMap.get(
										CommonStaticClass.currentSLNo)
										.getQvar(), memberIDs.get(spinnerSL
										.getSelectedItemPosition()),

								CommonStaticClass.memberID);

				dbHelper.executeDMLQuery(sqlUp);

				qtab = "";
				qcap = "";
				qDrop = "";
				qSpoon = "";
				((CheckBox) findViewById(R.id.chktablet)).setChecked(false);
				((CheckBox) findViewById(R.id.chkcapsule)).setChecked(false);
				((CheckBox) findViewById(R.id.chkdrops)).setChecked(false);
				((CheckBox) findViewById(R.id.chkspoon)).setChecked(false);

			}

			if (memberIDs.contains((Integer.parseInt(memberID) + 1) + "")) {

				insertMoreFrmFamilyMember(false);
				CommonStaticClass.totalHHMember = Integer.parseInt(memberID);
				// spinnerSL.setSelection(Integer.parseInt(memberID));

			} else {
				new AlertDialog.Builder(con)
						.setTitle("Data updated")
						.setMessage("Do you want to insert more Episode?")
						.setPositiveButton("Yes",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {
										dialog.dismiss();
										insertMoreFrmFamilyMember(true);
									}
								})
						.setNegativeButton("No",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {
										dialog.dismiss();
										insertMoreFrmFamilyMember(false);
										CommonStaticClass.totalHHMember = Integer
												.parseInt(memberID);
									}
								}).setCancelable(false).show();
			}

		}
	}

	// q124
	private void updateTableDataq124() {
		if (!IsValidq124())
			return;
		else {
			Date d = new Date(System.currentTimeMillis());
			String entryDate = "dd-mmm-yy";
			entryDate = d.toLocaleString();

			String v = getSkip("q123", "tblMainQues");
			if (v != null) {
				if (v.length() > 0) {
					if (spinnerc1.getCount() > Integer.parseInt(v)) {
						CommonStaticClass.showMyAlert(con, "Message",
								"You have reached the maximum number.");
						CommonStaticClass.findOutNextSLNo(
								CommonStaticClass.questionMap.get(
										CommonStaticClass.currentSLNo)
										.getQvar(),
								CommonStaticClass.questionMap.get(
										CommonStaticClass.currentSLNo)
										.getQnext1());
						CommonStaticClass.nextQuestion(ParentActivity.this);
						return;
					}
				}
			}

			String s1 = String
					.format("Insert into tblTravel (dataid, slno, c2, c2_other, c3,c4,c5,c5_2,c5_3,c5_4,c6,EntryBy,EntryDate) "
							+ "VALUES('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
							CommonStaticClass.dataId,
							memberIDs.get(spinnerc1.getSelectedItemPosition()),
							sex, etother.getText(), etc3.getText(),
							etc4.getText(), etc5.getText(), etc5_2.getText(),
							etc5_3.getText(), etc5_4.getText(), etc6.getText(),
							CommonStaticClass.userSpecificId, entryDate);

			/*
			 * + "    values('" + CommonStaticClass.dataId + "','" +
			 * memberIDs.get(spinnerSL.getSelectedItemPosition()) + "','" +
			 * AgeYear + "','" + sex + "','" + CommonStaticClass.userSpecificId
			 * + "','" + entryDate + "')";
			 */
			if (dbHelper.executeDMLQuery(s1)) {

			} else {

				/*
				 * c2, c2_other, c3, c4, c5, c5_2, c5_3, c5_4, c6, EntryBy,
				 * EntryDate
				 */

				String sqlUp = String
						.format("Update tblTravel SET c2='%s', c2_other='%s',c3='%s',c4='%s',c5='%s',c5_2='%s',c5_3='%s',c5_4='%s',c6='%s' WHERE dataid='%s' AND slno ='%s'",
								sex, etother.getText(), etc3.getText(), etc4
										.getText(), etc5.getText(), etc5_2
										.getText(), etc5_3.getText(), etc5_4
										.getText(), etc6.getText(),
								CommonStaticClass.dataId, memberIDs
										.get(spinnerc1
												.getSelectedItemPosition()));

				dbHelper.executeDMLQuery(sqlUp);
			}

			/*
			 * if (memberIDs.contains((Integer.parseInt(memberID) + 1) + "")) {
			 * 
			 * insertMoreFrmFamilyMember(false); CommonStaticClass.totalHHMember
			 * = Integer.parseInt(memberID); //
			 * spinnerSL.setSelection(Integer.parseInt(memberID));
			 * 
			 * }
			 */

			// memberID = String.valueOf(spinnerc1.getSelectedItemPosition());
			if (memberIDs.contains((Integer.parseInt(memberID) + 1) + "")) {

				insertMoreq124(false);
				CommonStaticClass.totalHHMember = Integer.parseInt(memberID);
				// spinnerSL.setSelection(Integer.parseInt(memberID));

			} else {
				new AlertDialog.Builder(con)
						.setTitle("Data updated")
						.setMessage("Do you want to insert more data?")
						.setPositiveButton("Yes",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {
										dialog.dismiss();
										insertMoreq124(true);
									}
								})
						.setNegativeButton("No",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {
										dialog.dismiss();
										insertMoreq124(false);
										CommonStaticClass.totalHHMember = Integer
												.parseInt(memberID);
									}
								}).setCancelable(false).show();
			}

		}
	}

	private void promptUserForInputFrmFamilyMember(final Spinner spinner) {
		// get prompts.xml view
		mydata = getOtherDataFrmFamilyMember();
		LayoutInflater li = LayoutInflater.from(con);
		View promptsView = li.inflate(R.layout.prompts, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(con);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);

		final EditText userInput = (EditText) promptsView
				.findViewById(R.id.editTextDialogUserInput);
		if (mydata != null && mydata.length() > 0) {
			userInput.setText(mydata);
		}
		// set dialog message
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// get user input and set it to result
						// edit text
						// insertDataToRelationOther(userInput.getText().toString());
						mydata = userInput.getText().toString();
					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
								spinner.setSelection(0);
							}
						});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
	}

	private String getOtherDataFrmFamilyMember() {
		String sql = "Select relationother from tblfamilyMember where dataid='"
				+ CommonStaticClass.dataId + "' and memberid="
				+ memberIDs.get(spinnerSL.getSelectedItemPosition());
		String data = "";
		Cursor mCursor = null;
		try {
			mCursor = dbHelper.getQueryCursor(sql);
			if (mCursor.getCount() > 0) {
				if (mCursor.moveToFirst()) {
					do {
						data = mCursor.getString(mCursor
								.getColumnIndex("relationother"));
					} while (mCursor.moveToNext());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (mCursor != null)
				mCursor.close();
		}
		return data;
	}

	public void insertDataToRelationOtherFrmFamilyMember(String data) {
		// TODO Auto-generated method stub
		if (data.length() > 0) {
			String sql = "UPDATE tblfamilyMember SET relationother ='" + data
					+ "' where dataid='" + CommonStaticClass.dataId
					+ "' and memberid="
					+ memberIDs.get(spinnerSL.getSelectedItemPosition());
			if (dbHelper.executeDMLQuery(sql)) {
				Log.e("relationother ", "Update");
			}
		}
	}

	public void findDataForThisSelectionFrmFamilyMember(String memberid) {
		// TODO Auto-generated method stub
		/*
		 * String sql = "Select * from tblMedicineInfo where dataid = '" +
		 * CommonStaticClass.dataId + "' AND memid='" + memberID + "'";
		 */

		String sql = String
				.format("Select * from '%s' where dataid = '%s' AND EpisodeNo='%s' AND QName='%s' AND memid = '%s'",
						CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getTablename(),
						CommonStaticClass.dataId,
						memberID,
						CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQvar(),
						CommonStaticClass.memberID);

		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.getCount() > 0) {

				doFillFrmFamilyMember(mCursor1);
			} else if (CommonStaticClass.previousDataFound) {

				/*
				 * sql = "Select * from tblFamilyInfo where dataid = '" +
				 * CommonStaticClass.previoushouseHoldDatatId + "' AND memid='"
				 * + memberID + "'";
				 */

				sql = String
						.format("Select * from '%s' where dataid = '%s' AND EpisodeNo='%s' AND QName='%s' AND memid = '%s'",
								CommonStaticClass.questionMap.get(
										CommonStaticClass.currentSLNo)
										.getTablename(),
								CommonStaticClass.dataId,
								memberID,
								CommonStaticClass.questionMap.get(
										CommonStaticClass.currentSLNo)
										.getQvar(), CommonStaticClass.memberID);

				mCursor1 = dbHelper.getQueryCursor(sql);
				if (mCursor1.getCount() > 0) {
					doFillFrmFamilyMember(mCursor1);
				}
			} else {
				resetViewsFrmFamilyMember();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}
	}

	public void findDataForThisSelectionq124(String memberid) {
		// TODO Auto-generated method stub
		String sql = "Select * from tblTravel where dataid = '"
				+ CommonStaticClass.dataId + "' AND slno='" + memberID + "'";
		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.getCount() > 0) {
				doFillq124(mCursor1);
			} else if (CommonStaticClass.previousDataFound) {
				sql = "Select * from tblTravel where dataid = '"
						+ CommonStaticClass.previoushouseHoldDatatId
						+ "' AND slno='" + memberID + "'";
				mCursor1 = dbHelper.getQueryCursor(sql);
				if (mCursor1.getCount() > 0) {
					doFillq124(mCursor1);
				}
			} else {
				resetViewsq124();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}
	}

	private void doFillFrmFamilyMember(Cursor mCursor1) {
		// TODO Auto-generated method stub
		if (mCursor1.moveToFirst()) {
			do {
				/*
				 * txtMname.setText(mCursor1.getString(mCursor1
				 * .getColumnIndex("Name")));
				 */

				((RadioGroup) findViewById(R.id.radioGroupwhen)).clearCheck();
				((EditText) findViewById(R.id.ettime)).setText("");

				((CheckBox) findViewById(R.id.chktablet)).setChecked(false);
				((CheckBox) findViewById(R.id.chkcapsule)).setChecked(false);
				((CheckBox) findViewById(R.id.chkdrops)).setChecked(false);
				((CheckBox) findViewById(R.id.chkspoon)).setChecked(false);

				((EditText) findViewById(R.id.ettablet)).setText("");
				((EditText) findViewById(R.id.etcapsule)).setText("");
				((EditText) findViewById(R.id.etdrops)).setText("");
				((EditText) findViewById(R.id.etspoon)).setText("");
				((EditText) findViewById(R.id.etMedicineOther)).setText("");

				((EditText) findViewById(R.id.etcapsule))
						.setVisibility(View.GONE);
				((EditText) findViewById(R.id.etdrops))
						.setVisibility(View.GONE);
				((EditText) findViewById(R.id.etspoon))
						.setVisibility(View.GONE);
				((EditText) findViewById(R.id.etMedicineOther))
						.setVisibility(View.GONE);
				((EditText) findViewById(R.id.ettablet))
						.setVisibility(View.GONE);

				spinnermedicine.setSelection(op.codeList.indexOf(Integer
						.parseInt(mCursor1.getString(mCursor1
								.getColumnIndex("MedicineID")))));

				String when = mCursor1.getString(mCursor1
						.getColumnIndex("qWhen"));
				switch (Integer.parseInt(when)) {
				case 1:
					((RadioButton) findViewById(R.id.radio0)).setChecked(true);
					((EditText) findViewById(R.id.ettime))
							.setVisibility(View.GONE);
					break;

				case 2:
					((RadioButton) findViewById(R.id.radio1)).setChecked(true);
					((EditText) findViewById(R.id.ettime))
							.setVisibility(View.VISIBLE);
					((EditText) findViewById(R.id.ettime)).setText(mCursor1
							.getString(mCursor1.getColumnIndex("qWhenT1")));
					break;

				case 3:
					((RadioButton) findViewById(R.id.radio2)).setChecked(true);
					((EditText) findViewById(R.id.ettime))
							.setVisibility(View.VISIBLE);
					((EditText) findViewById(R.id.ettime)).setText(mCursor1
							.getString(mCursor1.getColumnIndex("qWhenT2")));
					break;

				case 99:
					((RadioButton) findViewById(R.id.radio3)).setChecked(true);
					((EditText) findViewById(R.id.ettime))
							.setVisibility(View.GONE);
					break;

				default:
					break;
				}

				if (mCursor1.getString(mCursor1.getColumnIndex("MedicineID"))
						.equalsIgnoreCase("30"))

				{
					((EditText) findViewById(R.id.etMedicineOther))
							.setText(mCursor1.getString(mCursor1
									.getColumnIndex("Medicine_other1")));

				}

				if (mCursor1.getString(mCursor1.getColumnIndex("MedicineID"))
						.equalsIgnoreCase("31"))

				{
					((EditText) findViewById(R.id.etMedicineOther))
							.setText(mCursor1.getString(mCursor1
									.getColumnIndex("Medicine_other2")));
				}
				if (mCursor1.getString(mCursor1.getColumnIndex("MedicineID"))
						.equalsIgnoreCase("32"))

				{
					((EditText) findViewById(R.id.etMedicineOther))
							.setText(mCursor1.getString(mCursor1
									.getColumnIndex("Medicine_other3")));
				}
				if (mCursor1.getString(mCursor1.getColumnIndex("MedicineID"))
						.equalsIgnoreCase("33"))

				{
					((EditText) findViewById(R.id.etMedicineOther))
							.setText(mCursor1.getString(mCursor1
									.getColumnIndex("Medicine_other4")));
				}

				if (!mCursor1.getString(mCursor1.getColumnIndex("qtab"))
						.toString().equalsIgnoreCase("null")) {

					if (Integer.parseInt(mCursor1.getString(mCursor1
							.getColumnIndex("qtab"))) > 0) {

						((CheckBox) findViewById(R.id.chktablet))
								.setChecked(true);
						((EditText) findViewById(R.id.ettablet))
								.setVisibility(View.VISIBLE);
						((EditText) findViewById(R.id.ettablet))
								.setText(mCursor1.getString(mCursor1
										.getColumnIndex("qtab")));
					}
				}

				if (!mCursor1.getString(mCursor1.getColumnIndex("qcap"))
						.toString().equalsIgnoreCase("null")) {
					if (Integer.parseInt(mCursor1.getString(mCursor1
							.getColumnIndex("qcap"))) > 0) {

						((CheckBox) findViewById(R.id.chkcapsule))
								.setChecked(true);

						((EditText) findViewById(R.id.etcapsule))
								.setVisibility(View.VISIBLE);
						((EditText) findViewById(R.id.etcapsule))
								.setText(mCursor1.getString(mCursor1
										.getColumnIndex("qcap")));

					}
				}

				if (!mCursor1.getString(mCursor1.getColumnIndex("qDrop"))
						.toString().equalsIgnoreCase("null")) {
					if (Integer.parseInt(mCursor1.getString(mCursor1
							.getColumnIndex("qDrop"))) > 0) {

						((CheckBox) findViewById(R.id.chkdrops))
								.setChecked(true);
						((EditText) findViewById(R.id.etdrops))
								.setVisibility(View.VISIBLE);
						((EditText) findViewById(R.id.etdrops))
								.setText(mCursor1.getString(mCursor1
										.getColumnIndex("qDrop")));

					}
				}
				if (!mCursor1.getString(mCursor1.getColumnIndex("qSpoon"))
						.toString().equalsIgnoreCase("null")) {
					if (Integer.parseInt(mCursor1.getString(mCursor1
							.getColumnIndex("qSpoon"))) > 0) {

						((CheckBox) findViewById(R.id.chkspoon))
								.setChecked(true);

						((EditText) findViewById(R.id.etspoon))
								.setVisibility(View.VISIBLE);
						((EditText) findViewById(R.id.etspoon))
								.setText(mCursor1.getString(mCursor1
										.getColumnIndex("qSpoon")));

					}
				}

			} while (mCursor1.moveToNext());
		}
	}

	private void doFillq124(Cursor mCursor1) {
		// TODO Auto-generated method stub
		if (mCursor1.moveToFirst()) {
			do {
				/*
				 * txtMname.setText(mCursor1.getString(mCursor1
				 * .getColumnIndex("Name")));
				 */
				spinnerc2.setSelection(opSex.codeList.indexOf(Integer
						.parseInt(mCursor1.getString(mCursor1
								.getColumnIndex("c2")))));

				etc3.setText(mCursor1.getString(mCursor1.getColumnIndex("c3")));

				etc4.setText(mCursor1.getString(mCursor1.getColumnIndex("c4")));

				etc5.setText(mCursor1.getString(mCursor1.getColumnIndex("c5")));

				etc5_2.setText(mCursor1.getString(mCursor1
						.getColumnIndex("c5_2")));

				etc5_3.setText(mCursor1.getString(mCursor1
						.getColumnIndex("c5_3")));

				etc5_4.setText(mCursor1.getString(mCursor1
						.getColumnIndex("c5_4")));

				etc6.setText(mCursor1.getString(mCursor1.getColumnIndex("c6")));

				etother.setText(mCursor1.getString(mCursor1
						.getColumnIndex("c2_other")));

			} while (mCursor1.moveToNext());
		}
	}

	private void resetViewsq124() {
		spinnerc1.setSelection(0);
		spinnerc2.setSelection(0);
		adapterSl.notifyDataSetChanged();
		spinnerc1.setSelection(memberIDs.size() - 1);
		etc3.setText("");
		etc4.setText("");
		etc5.setText("");
		etc5_2.setText("");
		etc5_3.setText("");
		etc5_4.setText("");
		etc6.setText("");
		etother.setText("");

	}

	private void resetViewsFrmFamilyMember() {
		spinnerSL.setSelection(0);
		spinnermedicine.setSelection(0);
		adapterSl.notifyDataSetChanged();
		spinnerSL.setSelection(memberIDs.size() - 1);

		((RadioGroup) findViewById(R.id.radioGroupwhen)).clearCheck();
		((EditText) findViewById(R.id.ettime)).setText("");

		((CheckBox) findViewById(R.id.chktablet)).setChecked(false);
		((CheckBox) findViewById(R.id.chkcapsule)).setChecked(false);
		((CheckBox) findViewById(R.id.chkdrops)).setChecked(false);
		((CheckBox) findViewById(R.id.chkspoon)).setChecked(false);

		((EditText) findViewById(R.id.ettablet)).setText("");
		((EditText) findViewById(R.id.etcapsule)).setText("");
		((EditText) findViewById(R.id.etdrops)).setText("");
		((EditText) findViewById(R.id.etspoon)).setText("");
		((EditText) findViewById(R.id.etMedicineOther)).setText("");

		((EditText) findViewById(R.id.etcapsule)).setVisibility(View.GONE);
		((EditText) findViewById(R.id.etdrops)).setVisibility(View.GONE);
		((EditText) findViewById(R.id.etspoon)).setVisibility(View.GONE);
		((EditText) findViewById(R.id.etMedicineOther))
				.setVisibility(View.GONE);
		((EditText) findViewById(R.id.ettablet)).setVisibility(View.GONE);

		Medicine_other1 = "";
		Medicine_other2 = "";
		Medicine_other3 = "";
		Medicine_other4 = "";
		qWhenT1 = "";
		qWhenT2 = "";
		qtab = "";
		qcap = "";
		qDrop = "";
		qSpoon = "";
		MedicineID = "";

	}

	private void insertMoreFrmFamilyMember(boolean b) {
		// TODO Auto-generated method stub
		if (b) {

			memberIDs
					.add((Integer.parseInt(memberIDs.get(memberIDs.size() - 1)) + 1)
							+ "");

			resetViewsFrmFamilyMember();
		} else {
			// String sq =
			// "UPDATE tblMainQues SET q10='"+q101+"' where dataid='"+CommonStaticClass.dataId+"'";
			// if(dbHelper.executeDMLQuery(sq)){
			// preserveState();
			CommonStaticClass.findOutNextSLNo(
					CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar(),
					CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQnext1());
			CommonStaticClass.nextQuestion(ParentActivity.this);
			// }
		}
	}

	private void insertMoreq124(boolean b) {
		// TODO Auto-generated method stub
		if (b) {

			memberIDs
					.add((Integer.parseInt(memberIDs.get(memberIDs.size() - 1)) + 1)
							+ "");

			resetViewsq124();
		} else {
			// String sq =
			// "UPDATE tblMainQues SET q10='"+q101+"' where dataid='"+CommonStaticClass.dataId+"'";
			// if(dbHelper.executeDMLQuery(sq)){
			// preserveState();
			CommonStaticClass.findOutNextSLNo(
					CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar(),
					CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQnext1());
			CommonStaticClass.nextQuestion(ParentActivity.this);
			// }
		}
	}

	private boolean valueInFrmFamilyMember(String column1, String column2) {
		String sql = "Select " + column1 + "," + column2
				+ " from tblMainQues where dataid='" + CommonStaticClass.dataId
				+ "'";
		Cursor mCursor = null;
		try {
			mCursor = dbHelper.getQueryCursor(sql);
			if (mCursor.getCount() > 0) {
				if (mCursor.moveToFirst()) {
					do {
						String val1 = mCursor.getString(mCursor
								.getColumnIndex(column1));
						String val2 = mCursor.getString(mCursor
								.getColumnIndex(column2));
						if (val1.length() > 0 || val2.length() > 0) {
							return true;
						}
					} while (mCursor.moveToNext());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	private boolean valueInFrmFamilyMember(String column1) {
		String sql = "Select " + column1 + " from tblMainQues where dataid='"
				+ CommonStaticClass.dataId + "'";
		Cursor mCursor = null;
		try {
			mCursor = dbHelper.getQueryCursor(sql);
			if (mCursor.getCount() > 0) {
				if (mCursor.moveToFirst()) {
					do {
						String val1 = mCursor.getString(mCursor
								.getColumnIndex(column1));

						if (val1.length() > 0) {
							return true;
						}
					} while (mCursor.moveToNext());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	private boolean valueInFrmFamilyMember(String column1, String column2,
			String column3) {
		String sql = "Select " + column1 + "," + column2 + "," + column3
				+ " from tblMainQues where dataid='" + CommonStaticClass.dataId
				+ "'";
		Cursor mCursor = null;
		try {
			mCursor = dbHelper.getQueryCursor(sql);
			if (mCursor.getCount() > 0) {
				if (mCursor.moveToFirst()) {
					do {
						String val1 = mCursor.getString(mCursor
								.getColumnIndex(column1));
						String val2 = mCursor.getString(mCursor
								.getColumnIndex(column2));
						String val3 = mCursor.getString(mCursor
								.getColumnIndex(column3));
						if (val1.length() > 0 || val2.length() > 0
								|| val3.length() > 0) {
							return true;
						}
					} while (mCursor.moveToNext());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	String Medicine_other1 = "", Medicine_other2 = "", Medicine_other3 = "",
			Medicine_other4 = "", qWhenT1, qWhenT2, qtab, qcap, qDrop, qSpoon;
	String MedicineID = "";

	private boolean IsValidFrmFamilyMember() {
		boolean IsValid = false;

		MedicineID = op.codeList.get(spinnermedicine.getSelectedItemPosition())
				.toString();

		if (MedicineID.length() == 0) {
			CommonStaticClass.showMyAlert(con, "Error",
					"Please select Medicine.");
			return IsValid;
		}

		if (String.valueOf(qWhen).toString().length() < 0) {
			CommonStaticClass.showMyAlert(con, "Error", "Please select When.");
			return IsValid;
		}

		if (qWhen == 2) {
			qWhenT1 = ((EditText) findViewById(R.id.ettime)).getText()
					.toString();
		}
		if (qWhen == 3) {
			qWhenT2 = ((EditText) findViewById(R.id.ettime)).getText()
					.toString();
		}

		if (MedicineID.equalsIgnoreCase("30")) {
			Medicine_other1 = ((EditText) findViewById(R.id.etMedicineOther))
					.getText().toString();
			if (Medicine_other1.length() <= 0) {
				CommonStaticClass.showMyAlert(con, "Error",
						"Please input Medicine.");
				return IsValid;
			}

		}
		if (MedicineID.equalsIgnoreCase("31")) {
			Medicine_other2 = ((EditText) findViewById(R.id.etMedicineOther))
					.getText().toString();

			if (Medicine_other2.length() <= 0) {
				CommonStaticClass.showMyAlert(con, "Error",
						"Please input Medicine.");
				return IsValid;
			}

		}
		if (MedicineID.equalsIgnoreCase("32")) {
			Medicine_other3 = ((EditText) findViewById(R.id.etMedicineOther))
					.getText().toString();

			if (Medicine_other3.length() <= 0) {
				CommonStaticClass.showMyAlert(con, "Error",
						"Please input Medicine.");
				return IsValid;
			}

		}
		if (MedicineID.equalsIgnoreCase("33")) {
			Medicine_other4 = ((EditText) findViewById(R.id.etMedicineOther))
					.getText().toString();

			if (Medicine_other4.length() <= 0) {
				CommonStaticClass.showMyAlert(con, "Error",
						"Please input Medicine.");
				return IsValid;
			}

		}

		if (((CheckBox) findViewById(R.id.chktablet)).isChecked()) {
			qtab = ((EditText) findViewById(R.id.ettablet)).getText()
					.toString();
			if (qtab.length() <= 0) {
				CommonStaticClass.showMyAlert(con, "Error",
						"Please input quantity.");
				return IsValid;
			}
		}
		if (((CheckBox) findViewById(R.id.chkcapsule)).isChecked()) {
			qcap = ((EditText) findViewById(R.id.etcapsule)).getText()
					.toString();

			if (qcap.length() <= 0) {
				CommonStaticClass.showMyAlert(con, "Error",
						"Please input quantity.");
				return IsValid;
			}

		}

		if (((CheckBox) findViewById(R.id.chkdrops)).isChecked()) {
			qDrop = ((EditText) findViewById(R.id.etdrops)).getText()
					.toString();

			if (qDrop.length() <= 0) {
				CommonStaticClass.showMyAlert(con, "Error",
						"Please input quantity.");
				return IsValid;
			}

		}
		if (((CheckBox) findViewById(R.id.chkspoon)).isChecked()) {
			qSpoon = ((EditText) findViewById(R.id.etspoon)).getText()
					.toString();

			if (qSpoon.length() <= 0) {
				CommonStaticClass.showMyAlert(con, "Error",
						"Please input quantity.");
				return IsValid;
			}

		}// qtab, qcap, qDrop, qSpoon

		return true;
	}

	private boolean IsValidq124() {
		boolean IsValid = false;

		sex = opSex.codeList.get(spinnerc2.getSelectedItemPosition())
				.toString();
		if (((EditText) findViewById(R.id.c3)).getText().length() <= 0
				&& ((EditText) findViewById(R.id.c4)).getText().length() <= 0) {
			CommonStaticClass.showMyAlert(con, "Error",
					"Specify hour/day spent");
			return IsValid;
		}

		if (((EditText) findViewById(R.id.c6)).getText().length() <= 0) {
			CommonStaticClass.showMyAlert(con, "Error", "Specify Distance");
			return IsValid;
		}

		return true;
	}

	private void HideFamily(ViewGroup v) {
		((EditText) v.findViewById(R.id.ettime)).setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.ettablet)).setVisibility(View.GONE);
		/*
		 * ((CheckBox)
		 * v.findViewById(R.id.chkcapsule)).setVisibility(View.GONE);
		 * ((CheckBox) v.findViewById(R.id.chkdrops)).setVisibility(View.GONE);
		 * ((CheckBox) v.findViewById(R.id.chkspoon)).setVisibility(View.GONE);
		 */
		((EditText) v.findViewById(R.id.etMedicineOther))
				.setVisibility(View.GONE);

	}

	EditText ettime;

	private void loadAllUiViewsFrmFamilyMember(ViewGroup v) {

		final Calendar c = Calendar.getInstance();
		TimeHour = c.get(Calendar.HOUR_OF_DAY);
		TimeMinute = c.get(Calendar.MINUTE);
		ettime = (EditText) v.findViewById(R.id.ettime);
		// updateDisplayfrmfamily("time");

		HideFamily(v);

		qqq = (TextView) v.findViewById(R.id.qqq);

		if (CommonStaticClass.langBng) {
			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0) {
				Typeface font = Typeface.createFromAsset(getAssets(),
						"Siyam Rupali ANSI.ttf");
				qqq.setTypeface(font);
			}
			;
			qqq.setText(CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0 ? CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng()
					: CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQdesceng());
		} else {
			qqq.setTypeface(null);
			qqq.setText(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQdesceng());
		}

		lblSL = (TextView) v.findViewById(R.id.lblMemberID);

		spinnerSL = (Spinner) v.findViewById(R.id.spinnerMember);
		spinnermedicine = (Spinner) v.findViewById(R.id.spinnermedicine);
		spinnerSL.setFocusableInTouchMode(true);
		spinnerSL.requestFocus();

		if (CommonStaticClass.langBng) {

			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0) {
				qqq.setTypeface(font);
			}
			;
			lblSL.setTypeface(font);
			lblSL.setText(con.getResources().getString(R.string.lblMmberIDBN));

			qqq.setText(CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0 ? CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng()
					: CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQdesceng());
		} else {

			lblSL.setTypeface(null);

			lblSL.setText("Episode No.");

			qqq.setTypeface(null);
			qqq.setText(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQdesceng());

		}

		ettime.setOnTouchListener(new View.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				showDialog(TIME_DIALOG);
				return false;
			}
		});

		// ////
		((CheckBox) v.findViewById(R.id.chktablet))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							((EditText) findViewById(R.id.ettablet))
									.setVisibility(View.VISIBLE);
						} else {
							((EditText) findViewById(R.id.ettablet))
									.setVisibility(View.GONE);

						}

					}
				});

		((CheckBox) v.findViewById(R.id.chkcapsule))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							((EditText) findViewById(R.id.etcapsule))
									.setVisibility(View.VISIBLE);
						} else {
							((EditText) findViewById(R.id.etcapsule))
									.setVisibility(View.GONE);

						}

					}
				});

		((CheckBox) v.findViewById(R.id.chkdrops))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							((EditText) findViewById(R.id.etdrops))
									.setVisibility(View.VISIBLE);
						} else {
							((EditText) findViewById(R.id.etdrops))
									.setVisibility(View.GONE);

						}

					}
				});
		((CheckBox) v.findViewById(R.id.chkspoon))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {

						if (isChecked) {
							((EditText) findViewById(R.id.etspoon))
									.setVisibility(View.VISIBLE);
						} else {
							((EditText) findViewById(R.id.etspoon))
									.setVisibility(View.GONE);
						}

					}
				});

		((RadioGroup) findViewById(R.id.radioGroupwhen))
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					public void onCheckedChanged(final RadioGroup group,
							int checkedId) {
						// TODO Auto-generated method stub

						if (((RadioButton) findViewById(R.id.radio0))
								.isChecked()) {
							qWhen = 1;
							((EditText) findViewById(R.id.ettime))
									.setVisibility(View.GONE);

						}

						if (((RadioButton) findViewById(R.id.radio1))
								.isChecked()) {
							qWhen = 2;
							((EditText) findViewById(R.id.ettime))
									.setVisibility(View.VISIBLE);
						}

						if (((RadioButton) findViewById(R.id.radio2))
								.isChecked()) {
							qWhen = 3;
							((EditText) findViewById(R.id.ettime))
									.setVisibility(View.VISIBLE);
						}

						if (((RadioButton) findViewById(R.id.radio3))
								.isChecked()) {
							qWhen = 99;
							((EditText) findViewById(R.id.ettime))
									.setVisibility(View.GONE);
						}

						/*
						 * if (String.valueOf(qWhen) != null) { switch (qWhen) {
						 * case 0: qWhen = 0; break;
						 * 
						 * case 1: qWhen = 1; break;
						 * 
						 * case 2: qWhen = 88; break;
						 * 
						 * case 3: qWhen = 99; break; default: break; }
						 * 
						 * }
						 */

					}
				});

	}

	// FrmGPSDataCollection part
	private void Load_UIFrmGPSDataCollection(final ViewGroup v) {
		txtLongitute = (EditText) v.findViewById(R.id.txtLongitude);
		txtLatitue = (EditText) v.findViewById(R.id.txtLatitude);

		qName = CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar();
		qqq = (TextView) v.findViewById(R.id.qqq);

		if (CommonStaticClass.langBng) {
			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0) {
				font = Typeface.createFromAsset(getAssets(),
						"Siyam Rupali ANSI.ttf");
				qqq.setTypeface(font);
			}
			;
			qqq.setText(CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0 ? CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng()
					: CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQdesceng());
		} else {
			qqq.setTypeface(null);
			qqq.setText(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQdesceng());
		}

		getGPSDataButton = (Button) v.findViewById(R.id.btnGetGPS);
		getGPSDataButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				try {
					locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

					locationManager.requestLocationUpdates(
							LocationManager.GPS_PROVIDER, 100, 1,
							new MyLocationListener());

					/*
					 * locationManager = (LocationManager)
					 * getSystemService(Context.LOCATION_SERVICE);
					 * 
					 * locationManager.requestLocationUpdates(
					 * LocationManager.GPS_PROVIDER, 100, 1, new
					 * MyLocationListenerFrmGPSDataCollection());
					 */
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		prevButton = (Button) v.findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				userPressedPrevious(ParentActivity.this);
			}

		});
		saveNxtButton = (Button) v.findViewById(R.id.saveNxtButton);
		saveNxtButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				updateTableDataFrmGPSDataCollection();
				// preserveState();
			}

		});
		clButton = (Button) v.findViewById(R.id.clButton);
		clButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				resetViewGroup((ViewGroup) v);
			}

		});
		notesButton = (Button) v.findViewById(R.id.btnNote);
		notesButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				FraNotes();

			}

		});

	}

	private class MyLocationListener implements LocationListener {

		public void onLocationChanged(Location location) {
			txtLongitute.setText(String.valueOf(location.getLongitude()));
			txtLatitue.setText(String.valueOf(location.getLatitude()));
			/*
			 * Toast.makeText(con, "GPS Location Changed", Toast.LENGTH_SHORT)
			 * .show();
			 */
			// txtLongitute.setText(String.format("%1$s",location.getLongitude()));
			// txtLatitue.setText(String.format("%1$s",location.getLatitude()));
		}

		public void onStatusChanged(String s, int i, Bundle b) {

		}

		public void onProviderDisabled(String s) {

		}

		public void onProviderEnabled(String s) {

		}

	}

	private void updateTableDataFrmGPSDataCollection() {
		// TODO Auto-generated method stub
		String sLongitude = txtLongitute.getText().toString();
		String sLatitude = txtLatitue.getText().toString();

		if (sLongitude.length() > 0 && sLatitude.length() > 0) {
			String sql = "UPDATE "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " SET Longitude='" + sLongitude + "',Latitude='"
					+ sLatitude + "' where dataid='" + CommonStaticClass.dataId
					+ "'";

			if (dbHelper.executeDMLQuery(sql)) {

				CommonStaticClass.findOutNextSLNo(
						CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQvar(),
						CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQnext1());
				CommonStaticClass.nextQuestion(ParentActivity.this);
			}
		}
	}

	protected void showCurrentLocationFrmGPSDataCollection() {

		try {
			Location location = locationManager
					.getLastKnownLocation(LocationManager.GPS_PROVIDER);

			if (location != null) {

				txtLongitute.setText(String.format("%1$s",
						location.getLongitude()));
				txtLatitue
						.setText(String.format("%1$s", location.getLatitude()));

			}
		} catch (Exception e) {
			Toast.makeText(con, e.toString(), 0);
		}

	}

	private class MyLocationListenerFrmGPSDataCollection implements
			LocationListener {

		public void onLocationChanged(Location location) {
			txtLongitute
					.setText(String.format("%1$s", location.getLongitude()));
			txtLatitue.setText(String.format("%1$s", location.getLatitude()));
		}

		public void onStatusChanged(String s, int i, Bundle b) {

		}

		public void onProviderDisabled(String s) {

		}

		public void onProviderEnabled(String s) {

		}

	}

	private void Load_DataFrmGPSDataCollection() {
		String sql = "Select * from "
				+ CommonStaticClass.questionMap.get(
						CommonStaticClass.currentSLNo).getTablename()
				+ " where dataid='" + CommonStaticClass.dataId + "'";
		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.moveToFirst()) {
				do {
					String column1 = "Longitude";
					String column2 = "Latitude";

					if (mCursor1.getColumnIndex(column1) != -1) {
						String a = mCursor1.getString(mCursor1
								.getColumnIndex(column1)) + "";
						String b = mCursor1.getString(mCursor1
								.getColumnIndex(column2)) + "";

						txtLongitute.setText(a.toString());
						txtLatitue.setText(b.toString());

					}
				} while (mCursor1.moveToNext());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}

	}

	// FrmHHID part

	private void loadGuiFrmMultiple(ViewGroup v) {
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		// ((EditText) v.findViewById(R.id.et1_1)).setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.et2_5)).setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.et3_3)).setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.et5_5)).setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.et7_6)).setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.et8_6)).setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.et12_3)).setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.et13_7)).setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.et14_3)).setVisibility(View.GONE);

		((CheckBox) v.findViewById(R.id.chk1_1))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk1_1 = (ischecked == true) ? "1" : "2";
						/*
						 * ((EditText) findViewById(R.id.et1_1))
						 * .setVisibility(View.VISIBLE);
						 */
					}
				});

		/*
		 * ((EditText) v.findViewById(R.id.et1_1)) .addTextChangedListener(new
		 * TextWatcher() {
		 * 
		 * public void onTextChanged(CharSequence s, int start, int before, int
		 * count) {
		 * 
		 * }
		 * 
		 * public void beforeTextChanged(CharSequence s, int start, int count,
		 * int after) { // TODO Auto-generated method stub
		 * 
		 * }
		 * 
		 * public void afterTextChanged(Editable s) { if (s.toString().length()
		 * > 0) { et1_1_other = s.toString(); }
		 * 
		 * } });
		 */

		((CheckBox) v.findViewById(R.id.chk2_1))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk2_1 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk2_2))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk2_2 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk2_3))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk2_3 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk2_4))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk2_4 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk2_5))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk2_5 = (ischecked == true) ? "1" : "2";

					}
				});

		((EditText) v.findViewById(R.id.et2_5))
				.addTextChangedListener(new TextWatcher() {

					public void onTextChanged(CharSequence s, int start,
							int before, int count) {

					}

					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
						// TODO Auto-generated method stub

					}

					public void afterTextChanged(Editable s) {
						if (s.toString().length() > 0) {
							et2_5_other = s.toString();
						}

					}
				});

		((CheckBox) v.findViewById(R.id.chk3_1))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk3_1 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk3_2))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk3_2 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk3_3))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk3_3 = (ischecked == true) ? "1" : "2";
						((EditText) findViewById(R.id.et3_3))
								.setVisibility(View.VISIBLE);

					}
				});

		((EditText) v.findViewById(R.id.et3_3))
				.addTextChangedListener(new TextWatcher() {

					public void onTextChanged(CharSequence s, int start,
							int before, int count) {

					}

					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
						// TODO Auto-generated method stub

					}

					public void afterTextChanged(Editable s) {
						if (s.toString().length() > 0) {
							et3_3_other = s.toString();
						}

					}
				});

		((CheckBox) v.findViewById(R.id.chk4_1))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk4_1 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk4_2))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk4_2 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk5_1))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk5_1 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk5_2))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk5_2 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk5_3))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk5_3 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk5_4))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk5_4 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk5_5))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk5_5 = (ischecked == true) ? "1" : "2";
						((EditText) findViewById(R.id.et5_5))
								.setVisibility(View.VISIBLE);
					}
				});

		((EditText) v.findViewById(R.id.et5_5))
				.addTextChangedListener(new TextWatcher() {

					public void onTextChanged(CharSequence s, int start,
							int before, int count) {

					}

					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
						// TODO Auto-generated method stub

					}

					public void afterTextChanged(Editable s) {
						if (s.toString().length() > 0) {
							et5_5_other = s.toString();
						}

					}
				});

		((CheckBox) v.findViewById(R.id.chk6_1))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk6_1 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk6_2))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk6_2 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk7_1))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk7_1 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk7_2))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk7_2 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk7_3))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk7_3 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk7_4))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk7_4 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk7_5))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk7_5 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk7_7))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk7_7 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk7_6))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk7_6 = (ischecked == true) ? "1" : "2";
						((EditText) findViewById(R.id.et7_6))
								.setVisibility(View.VISIBLE);
					}
				});

		((EditText) v.findViewById(R.id.et7_6))
				.addTextChangedListener(new TextWatcher() {

					public void onTextChanged(CharSequence s, int start,
							int before, int count) {

					}

					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
						// TODO Auto-generated method stub

					}

					public void afterTextChanged(Editable s) {
						if (s.toString().length() > 0) {
							et7_6_other = s.toString();
						}

					}
				});

		((CheckBox) v.findViewById(R.id.chk8_1))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk8_1 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk8_2))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk8_2 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk8_3))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk8_3 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk8_4))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk8_4 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk8_5))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk8_5 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk8_6))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk8_6 = (ischecked == true) ? "1" : "2";
						((EditText) findViewById(R.id.et8_6))
								.setVisibility(View.VISIBLE);
					}
				});

		((EditText) v.findViewById(R.id.et8_6))
				.addTextChangedListener(new TextWatcher() {

					public void onTextChanged(CharSequence s, int start,
							int before, int count) {

					}

					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
						// TODO Auto-generated method stub

					}

					public void afterTextChanged(Editable s) {
						if (s.toString().length() > 0) {
							et8_6_other = s.toString();
						}

					}
				});

		((CheckBox) v.findViewById(R.id.chk9_1))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk9_1 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk9_2))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk9_2 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk9_3))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk9_3 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk10_1))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk10_1 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk11_1))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk11_1 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk12_1))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk12_1 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk12_2))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk12_2 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk12_3))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk12_3 = (ischecked == true) ? "1" : "2";
						((EditText) findViewById(R.id.et12_3))
								.setVisibility(View.VISIBLE);
					}
				});

		((EditText) v.findViewById(R.id.et12_3))
				.addTextChangedListener(new TextWatcher() {

					public void onTextChanged(CharSequence s, int start,
							int before, int count) {

					}

					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
						// TODO Auto-generated method stub

					}

					public void afterTextChanged(Editable s) {
						if (s.toString().length() > 0) {
							et12_3_other = s.toString();
						}

					}
				});

		((CheckBox) v.findViewById(R.id.chk13_1))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk13_1 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk13_2))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk13_2 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk13_3))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk13_3 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk13_4))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk13_4 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk13_5))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk13_5 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk13_6))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk13_6 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk13_7))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk13_7 = (ischecked == true) ? "1" : "2";
						((EditText) findViewById(R.id.et13_7))
								.setVisibility(View.VISIBLE);
					}
				});

		((EditText) v.findViewById(R.id.et13_7))
				.addTextChangedListener(new TextWatcher() {

					public void onTextChanged(CharSequence s, int start,
							int before, int count) {

					}

					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
						// TODO Auto-generated method stub

					}

					public void afterTextChanged(Editable s) {
						if (s.toString().length() > 0) {
							et13_7_other = s.toString();
						}

					}
				});

		((CheckBox) v.findViewById(R.id.chk14_1))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk14_1 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk14_2))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk14_2 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk14_3))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk14_3 = (ischecked == true) ? "1" : "2";
						((EditText) findViewById(R.id.et14_3))
								.setVisibility(View.VISIBLE);
					}
				});

		((EditText) v.findViewById(R.id.et14_3))
				.addTextChangedListener(new TextWatcher() {

					public void onTextChanged(CharSequence s, int start,
							int before, int count) {

					}

					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
						// TODO Auto-generated method stub

					}

					public void afterTextChanged(Editable s) {
						if (s.toString().length() > 0) {
							et14_3_other = s.toString();
						}

					}
				});

		((CheckBox) v.findViewById(R.id.chk15_1))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk15_1 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk15_2))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk15_2 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk15_3))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk15_3 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk16_1))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk16_1 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk16_2))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk16_2 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk16_3))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk16_3 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk16_4))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk16_4 = (ischecked == true) ? "1" : "2";

					}
				});
		((CheckBox) v.findViewById(R.id.chk16_5))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk16_5 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk17_1))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk17_1 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk17_2))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk17_2 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk17_3))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk17_3 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk17_4))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk17_4 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk18_1))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk18_1 = (ischecked == true) ? "1" : "2";

					}
				});

		((CheckBox) v.findViewById(R.id.chk18_2))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						chk18_2 = (ischecked == true) ? "1" : "2";

					}
				});
		((EditText) v.findViewById(R.id.et19_1))
				.addTextChangedListener(new TextWatcher() {

					public void onTextChanged(CharSequence s, int start,
							int before, int count) {

					}

					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
						// TODO Auto-generated method stub

					}

					public void afterTextChanged(Editable s) {
						if (s.toString().length() > 0) {
							et19_1 = s.toString();
						}

					}
				});

		String sql = "";

		sql = "Select c609_1_1,c609_2_1,c609_2_2,c609_2_3,c609_2_4,c609_2_5,c609_2_5_other,c609_3_1,c609_3_2,c609_3_3,c609_3_3_other,c609_4_1,c609_4_2,"
				+ "c609_5_1,c609_5_2,c609_5_3,c609_5_4,c609_5_5,c609_5_5_other,c609_6_1,c609_6_2,c609_7_1,c609_7_2,c609_7_3,c609_7_4,c609_7_5,c609_7_6, c609_7_7,c609_7_6_other,"
				+ "c609_8_1,c609_8_2,c609_8_3,c609_8_4,c609_8_5,c609_8_6,c609_8_6_other,c609_9_1,c609_9_2,c609_9_3,c609_10_1,c609_11_1,c609_12_1,c609_12_2,"
				+ "c609_12_3,c609_12_3_other ,c609_13_1,c609_13_2,c609_13_3,c609_13_4,c609_13_5,c609_13_6,c609_13_7,c609_13_7_other ,c609_14_1,c609_14_2,c609_14_3,"
				+ "c609_14_3_other ,c609_15_1,c609_15_2,c609_15_3,c609_16_1,c609_16_2,c609_16_3,c609_16_4, c609_16_5, c609_17_1,c609_17_2,c609_17_3, c609_17_4, c609_18_1,c609_18_2,c609_19_1 from "
				+ CommonStaticClass.questionMap.get(
						CommonStaticClass.currentSLNo).getTablename()
				+ " where dataid='" + CommonStaticClass.dataId + "'";

		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.getCount() > 0) {
				if (mCursor1.moveToFirst()) {
					do {

						chk1_1 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_1_1")) + "";
						if (chk1_1.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk1_1))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk1_1))
									.setChecked(false);
						}

						/*
						 * et1_1_other = mCursor1.getString(mCursor1
						 * .getColumnIndex("c609_1_1_other")) + "";
						 * 
						 * if (et1_1_other.length() > 0 &&
						 * !et1_1_other.equalsIgnoreCase("null")) { ((EditText)
						 * findViewById(R.id.et1_1))
						 * .setVisibility(View.VISIBLE); ((EditText)
						 * findViewById(R.id.et1_1)) .setText(et1_1_other);
						 * 
						 * }
						 */

						chk2_1 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_2_1")) + "";

						if (chk2_1.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk2_1))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk2_1))
									.setChecked(false);
						}

						chk2_2 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_2_2")) + "";

						if (chk2_2.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk2_2))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk2_2))
									.setChecked(false);
						}

						chk2_3 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_2_3")) + "";
						if (chk2_3.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk2_3))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk2_3))
									.setChecked(false);
						}

						chk2_4 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_2_4")) + "";
						if (chk2_4.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk2_4))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk2_4))
									.setChecked(false);
						}

						chk2_5 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_2_5")) + "";
						if (chk2_5.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk2_5))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk2_5))
									.setChecked(false);
						}

						et2_5_other = mCursor1.getString(mCursor1
								.getColumnIndex("c609_2_5_other")) + "";
						((EditText) findViewById(R.id.et2_5))
								.setText(et2_5_other);

						if (et2_5_other.length() > 0
								&& !et2_5_other.equalsIgnoreCase("null")) {
							((EditText) findViewById(R.id.et2_5))
									.setVisibility(View.VISIBLE);
						}

						chk3_1 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_3_1")) + "";
						if (chk3_1.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk3_1))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk3_1))
									.setChecked(false);
						}

						chk3_2 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_3_2")) + "";
						if (chk3_2.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk3_2))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk3_2))
									.setChecked(false);
						}
						chk3_3 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_3_3")) + "";
						if (chk3_3.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk3_3))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk3_3))
									.setChecked(false);
						}
						et3_3_other = mCursor1.getString(mCursor1
								.getColumnIndex("c609_3_3_other")) + "";
						((EditText) findViewById(R.id.et3_3))
								.setText(et3_3_other);

						if (et3_3_other.length() > 0
								&& !et3_3_other.equalsIgnoreCase("null")) {
							((EditText) findViewById(R.id.et3_3))
									.setVisibility(View.VISIBLE);
						}

						chk4_1 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_4_1")) + "";
						if (chk4_1.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk4_1))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk4_1))
									.setChecked(false);
						}
						chk4_2 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_4_2")) + "";
						if (chk4_2.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk4_2))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk4_2))
									.setChecked(false);
						}

						chk5_1 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_5_1")) + "";
						if (chk5_1.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk5_1))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk5_1))
									.setChecked(false);
						}

						chk5_2 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_5_2")) + "";
						if (chk5_2.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk5_2))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk5_2))
									.setChecked(false);
						}

						chk5_3 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_5_3")) + "";
						if (chk5_3.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk5_3))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk5_3))
									.setChecked(false);
						}

						chk5_4 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_5_4")) + "";
						if (chk5_4.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk5_4))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk5_4))
									.setChecked(false);
						}
						chk5_5 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_5_5")) + "";
						if (chk5_5.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk5_5))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk5_5))
									.setChecked(false);
						}

						et5_5_other = mCursor1.getString(mCursor1
								.getColumnIndex("c609_5_5_other")) + "";
						((EditText) findViewById(R.id.et5_5))
								.setText(et5_5_other);

						if (et5_5_other.length() > 0
								&& !et5_5_other.equalsIgnoreCase("null")) {
							((EditText) findViewById(R.id.et5_5))
									.setVisibility(View.VISIBLE);
						}

						chk6_1 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_6_1")) + "";
						if (chk6_1.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk6_1))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk6_1))
									.setChecked(false);
						}
						chk6_2 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_6_2")) + "";
						if (chk6_2.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk6_2))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk6_2))
									.setChecked(false);
						}
						chk7_1 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_7_1")) + "";
						if (chk7_1.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk7_1))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk7_1))
									.setChecked(false);
						}
						chk7_2 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_7_2")) + "";
						if (chk7_2.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk7_2))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk7_2))
									.setChecked(false);
						}
						chk7_3 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_7_3")) + "";
						if (chk7_3.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk7_3))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk7_3))
									.setChecked(false);
						}
						chk7_4 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_7_4")) + "";
						if (chk7_4.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk7_4))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk7_4))
									.setChecked(false);
						}
						chk7_5 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_7_5")) + "";
						if (chk7_5.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk7_5))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk7_5))
									.setChecked(false);
						}

						chk7_7 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_7_7")) + "";
						if (chk7_7.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk7_7))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk7_7))
									.setChecked(false);
						}

						chk7_6 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_7_6")) + "";
						if (chk7_6.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk7_6))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk7_6))
									.setChecked(false);
						}
						et7_6_other = mCursor1.getString(mCursor1
								.getColumnIndex("c609_7_6_other")) + "";
						((EditText) findViewById(R.id.et7_6))
								.setText(et7_6_other);

						if (et7_6_other.length() > 0
								&& !et7_6_other.equalsIgnoreCase("null")) {
							((EditText) findViewById(R.id.et7_6))
									.setVisibility(View.VISIBLE);
						}

						chk8_1 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_8_1")) + "";
						if (chk8_1.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk8_1))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk8_1))
									.setChecked(false);
						}
						chk8_2 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_8_2")) + "";
						if (chk8_2.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk8_2))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk8_2))
									.setChecked(false);
						}
						chk8_3 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_8_3")) + "";
						if (chk8_3.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk8_3))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk8_3))
									.setChecked(false);
						}
						chk8_4 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_8_4")) + "";
						if (chk8_4.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk8_4))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk8_4))
									.setChecked(false);
						}
						chk8_5 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_8_5")) + "";
						if (chk8_5.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk8_5))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk8_5))
									.setChecked(false);
						}
						chk8_6 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_8_6")) + "";
						if (chk8_6.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk8_6))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk8_6))
									.setChecked(false);
						}
						et8_6_other = mCursor1.getString(mCursor1
								.getColumnIndex("c609_8_6_other")) + "";
						((EditText) findViewById(R.id.et8_6))
								.setText(et8_6_other);

						if (et8_6_other.length() > 0
								&& !et8_6_other.equalsIgnoreCase("null")) {
							((EditText) findViewById(R.id.et8_6))
									.setVisibility(View.VISIBLE);
						}

						chk9_1 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_9_1")) + "";
						if (chk9_1.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk9_1))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk9_1))
									.setChecked(false);
						}
						chk9_2 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_9_2")) + "";
						if (chk9_2.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk9_2))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk9_2))
									.setChecked(false);
						}
						chk9_3 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_9_3")) + "";
						if (chk9_3.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk9_3))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk9_3))
									.setChecked(false);
						}
						chk10_1 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_10_1")) + "";
						if (chk10_1.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk10_1))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk10_1))
									.setChecked(false);
						}
						chk11_1 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_11_1")) + "";
						if (chk11_1.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk11_1))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk11_1))
									.setChecked(false);
						}
						chk12_1 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_12_1")) + "";
						if (chk12_1.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk12_1))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk12_1))
									.setChecked(false);
						}
						chk12_2 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_12_2")) + "";
						if (chk12_2.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk12_2))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk12_2))
									.setChecked(false);
						}
						chk12_3 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_12_3")) + "";
						if (chk12_3.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk12_3))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk12_3))
									.setChecked(false);
						}
						et12_3_other = mCursor1.getString(mCursor1
								.getColumnIndex("c609_12_3_other")) + "";
						((EditText) findViewById(R.id.et12_3))
								.setText(et12_3_other);

						if (et12_3_other.length() > 0
								&& !et12_3_other.equalsIgnoreCase("null")) {
							((EditText) findViewById(R.id.et12_3))
									.setVisibility(View.VISIBLE);
						}

						chk13_1 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_13_1")) + "";
						if (chk13_1.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk13_1))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk13_1))
									.setChecked(false);
						}
						chk13_2 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_13_2")) + "";
						if (chk13_2.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk13_2))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk13_2))
									.setChecked(false);
						}
						chk13_3 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_13_3")) + "";
						if (chk13_3.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk13_3))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk13_3))
									.setChecked(false);
						}
						chk13_4 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_13_4")) + "";
						if (chk13_4.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk13_4))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk2_1))
									.setChecked(false);
						}
						chk13_5 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_13_5")) + "";
						if (chk13_5.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk13_5))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk13_5))
									.setChecked(false);
						}
						chk13_6 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_13_6")) + "";
						if (chk13_6.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk13_6))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk13_6))
									.setChecked(false);
						}
						chk13_7 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_13_7")) + "";
						if (chk13_7.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk13_7))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk13_7))
									.setChecked(false);
						}
						et13_7_other = mCursor1.getString(mCursor1
								.getColumnIndex("c609_13_7_other")) + "";
						((EditText) findViewById(R.id.et13_7))
								.setText(et13_7_other);

						if (et13_7_other.length() > 0
								&& !et13_7_other.equalsIgnoreCase("null")) {
							((EditText) findViewById(R.id.et13_7))
									.setVisibility(View.VISIBLE);
						}

						chk14_1 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_14_1")) + "";
						if (chk14_1.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk14_1))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk14_1))
									.setChecked(false);
						}
						chk14_2 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_14_2")) + "";
						if (chk14_2.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk14_2))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk14_2))
									.setChecked(false);
						}
						chk14_3 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_14_3")) + "";
						if (chk14_3.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk14_3))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk14_3))
									.setChecked(false);
						}
						et14_3_other = mCursor1.getString(mCursor1
								.getColumnIndex("c609_14_3_other")) + "";
						((EditText) findViewById(R.id.et14_3))
								.setText(et14_3_other);

						if (et14_3_other.length() > 0
								&& !et14_3_other.equalsIgnoreCase("null")) {
							((EditText) findViewById(R.id.et14_3))
									.setVisibility(View.VISIBLE);
						}

						chk15_1 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_15_1")) + "";
						if (chk15_1.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk15_1))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk15_1))
									.setChecked(false);
						}
						chk15_2 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_15_2")) + "";
						if (chk15_2.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk15_2))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk15_2))
									.setChecked(false);
						}
						chk15_3 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_15_3")) + "";
						if (chk15_3.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk15_3))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk15_3))
									.setChecked(false);
						}
						chk16_1 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_16_1")) + "";
						if (chk16_1.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk16_1))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk16_1))
									.setChecked(false);
						}
						chk16_2 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_16_2")) + "";
						if (chk16_2.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk16_2))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk16_2))
									.setChecked(false);
						}
						chk16_3 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_16_3")) + "";
						if (chk16_3.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk16_3))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk16_3))
									.setChecked(false);
						}
						chk16_4 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_16_4")) + "";
						if (chk16_4.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk16_4))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk16_4))
									.setChecked(false);
						}

						chk16_5 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_16_5")) + "";
						if (chk16_5.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk16_5))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk16_5))
									.setChecked(false);
						}

						chk17_1 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_17_1")) + "";
						if (chk17_1.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk17_1))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk17_1))
									.setChecked(false);
						}
						chk17_2 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_17_2")) + "";
						if (chk17_2.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk17_2))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk17_2))
									.setChecked(false);
						}
						chk17_3 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_17_3")) + "";
						if (chk17_3.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk17_3))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk17_3))
									.setChecked(false);
						}

						chk17_4 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_17_4")) + "";
						if (chk17_4.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk17_4))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk17_4))
									.setChecked(false);
						}

						chk18_1 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_18_1")) + "";
						if (chk18_1.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk18_1))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk18_1))
									.setChecked(false);
						}
						chk18_2 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_18_2")) + "";
						if (chk18_2.equalsIgnoreCase("1")) {
							((CheckBox) v.findViewById(R.id.chk18_2))
									.setChecked(true);
						} else {
							((CheckBox) v.findViewById(R.id.chk18_2))
									.setChecked(false);
						}
						et19_1 = mCursor1.getString(mCursor1
								.getColumnIndex("c609_19_1")) + "";

						((EditText) findViewById(R.id.et19_1)).setText((et19_1
								.length() > 0
								&& (!et19_1.equalsIgnoreCase("-1")) && (!et19_1
								.equalsIgnoreCase("null"))) ? et19_1 : "");

						// seeting if null text
						if ((((EditText) findViewById(R.id.et2_5)).getText()
								.toString().equalsIgnoreCase("null"))) {
							((EditText) findViewById(R.id.et2_5)).setText("");
						}

						if ((((EditText) findViewById(R.id.et3_3)).getText()
								.toString().equalsIgnoreCase("null"))) {
							((EditText) findViewById(R.id.et3_3)).setText("");
						}

						if ((((EditText) findViewById(R.id.et5_5)).getText()
								.toString().equalsIgnoreCase("null"))) {
							((EditText) findViewById(R.id.et5_5)).setText("");
						}

						if ((((EditText) findViewById(R.id.et7_6)).getText()
								.toString().equalsIgnoreCase("null"))) {
							((EditText) findViewById(R.id.et7_6)).setText("");
						}

						if ((((EditText) findViewById(R.id.et8_6)).getText()
								.toString().equalsIgnoreCase("null"))) {
							((EditText) findViewById(R.id.et8_6)).setText("");
						}

						if ((((EditText) findViewById(R.id.et12_3)).getText()
								.toString().equalsIgnoreCase("null"))) {
							((EditText) findViewById(R.id.et12_3)).setText("");
						}

						if ((((EditText) findViewById(R.id.et13_7)).getText()
								.toString().equalsIgnoreCase("null"))) {
							((EditText) findViewById(R.id.et13_7)).setText("");
						}

						if ((((EditText) findViewById(R.id.et14_3)).getText()
								.toString().equalsIgnoreCase("null"))) {
							((EditText) findViewById(R.id.et14_3)).setText("");
						}
					} while (mCursor1.moveToNext());
				}
				// doFillFrmMultiple(mCursor1,v);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}

		saveNxtButton = (Button) v.findViewById(R.id.saveNxtButton);
		saveNxtButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				updatemultiple();
			}

		});
		btnPrev = (Button) v.findViewById(R.id.prevButton);
		btnPrev.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				userPressedPrevious(ParentActivity.this);
			}

		});
		btnClear = (Button) v.findViewById(R.id.clButton);
		btnClear.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				resetViewGroup((ViewGroup) vv);
			}

		});
		notesButton = (Button) v.findViewById(R.id.btnNote);
		notesButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				FraNotes();

			}

		});

		if (CommonStaticClass.langBng) {
			setfonttobanglamultiple(v);
		} else {
			((TextView) v.findViewById(R.id.textView2)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk1_1)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk2_1)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk2_2)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk2_3)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk2_4)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk2_5)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk3_1)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk3_2)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk3_3)).setTypeface(null);

			((CheckBox) v.findViewById(R.id.chk4_1)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk4_2)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk5_1)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk5_2)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk5_3)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk5_4)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk5_5)).setTypeface(null);

			((CheckBox) v.findViewById(R.id.chk6_1)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk6_2)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk7_1)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk7_2)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk7_3)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk7_4)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk7_5)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk7_7)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk7_6)).setTypeface(null);

			((CheckBox) v.findViewById(R.id.chk8_1)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk8_2)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk8_3)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk8_4)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk8_5)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk8_6)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk9_1)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk9_2)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk9_3)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk10_1)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk11_1)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk12_1)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk12_2)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk12_3)).setTypeface(null);

			((CheckBox) v.findViewById(R.id.chk13_1)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk13_2)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk13_3)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk13_4)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk13_5)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk13_6)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk13_7)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk14_1)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk14_2)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk14_3)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk15_1)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk15_2)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk15_3)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk16_1)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk16_2)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk16_3)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk16_4)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk16_5)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk17_1)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk17_2)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk17_3)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk17_4)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk18_1)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk18_2)).setTypeface(null);

			// setting text to english
			((CheckBox) v.findViewById(R.id.chk1_1)).setText("Khichuri");
			((CheckBox) v.findViewById(R.id.chk2_1)).setText("Porridge");
			((CheckBox) v.findViewById(R.id.chk2_2)).setText("Rice");
			((CheckBox) v.findViewById(R.id.chk2_3)).setText("Bread/Roti");
			((CheckBox) v.findViewById(R.id.chk2_4)).setText("Noodles");
			((CheckBox) v.findViewById(R.id.chk2_5))
					.setText("Other foods made by grain");
			((CheckBox) v.findViewById(R.id.chk3_1)).setText("Pumpkin");
			((CheckBox) v.findViewById(R.id.chk3_2)).setText("Carrots");
			((CheckBox) v.findViewById(R.id.chk3_3))
					.setText("Other yellow vegetable");

			((CheckBox) v.findViewById(R.id.chk4_1)).setText("Potato");
			((CheckBox) v.findViewById(R.id.chk4_2))
					.setText("White sweet potato");
			((CheckBox) v.findViewById(R.id.chk5_1)).setText("Pumpkin leaves");
			((CheckBox) v.findViewById(R.id.chk5_2)).setText("Mustard leaves");
			((CheckBox) v.findViewById(R.id.chk5_3)).setText("Bean leaves");
			((CheckBox) v.findViewById(R.id.chk5_4))
					.setText("Pigeon pea/Motoshuti leaves");
			((CheckBox) v.findViewById(R.id.chk5_5))
					.setText("other dark green leaves");

			((CheckBox) v.findViewById(R.id.chk6_1)).setText("Ripe mango");
			((CheckBox) v.findViewById(R.id.chk6_2)).setText("Ripe papaya");
			((CheckBox) v.findViewById(R.id.chk7_1)).setText("Banana");
			((CheckBox) v.findViewById(R.id.chk7_2)).setText("Pineapple");
			((CheckBox) v.findViewById(R.id.chk7_3)).setText("Guava");
			((CheckBox) v.findViewById(R.id.chk7_4)).setText("Apple");
			((CheckBox) v.findViewById(R.id.chk7_5)).setText("Grape");
			((CheckBox) v.findViewById(R.id.chk7_7)).setText("Orange");
			((CheckBox) v.findViewById(R.id.chk7_6)).setText("Other fruit");

			((CheckBox) v.findViewById(R.id.chk8_1)).setText("Tomato");
			((CheckBox) v.findViewById(R.id.chk8_2)).setText("Onion");
			((CheckBox) v.findViewById(R.id.chk8_3)).setText("Mushroom");
			((CheckBox) v.findViewById(R.id.chk8_4)).setText("Lady's finger");
			((CheckBox) v.findViewById(R.id.chk8_5))
					.setText("Fresh bean/motorshuti");
			((CheckBox) v.findViewById(R.id.chk8_6)).setText("Other vegetable");
			((CheckBox) v.findViewById(R.id.chk9_1)).setText("Liver");
			((CheckBox) v.findViewById(R.id.chk9_2)).setText("Kidney");
			((CheckBox) v.findViewById(R.id.chk9_3)).setText("Heart");
			((CheckBox) v.findViewById(R.id.chk10_1))
					.setText("Any type of meat/flesh,including from birds and animals");
			((CheckBox) v.findViewById(R.id.chk11_1))
					.setText("Any type of egg");
			((CheckBox) v.findViewById(R.id.chk12_1)).setText("Fresh fish");
			((CheckBox) v.findViewById(R.id.chk12_2)).setText("Dried fish");
			((CheckBox) v.findViewById(R.id.chk12_3))
					.setText("Other fish / seafood");

			((CheckBox) v.findViewById(R.id.chk13_1)).setText("Beans");
			((CheckBox) v.findViewById(R.id.chk13_2)).setText("Peas/Lentils");
			((CheckBox) v.findViewById(R.id.chk13_3)).setText("Soya");
			((CheckBox) v.findViewById(R.id.chk13_4)).setText("Groundnut");
			((CheckBox) v.findViewById(R.id.chk13_5)).setText("Cashew");
			((CheckBox) v.findViewById(R.id.chk13_6))
					.setText("Pounded groundnut");
			((CheckBox) v.findViewById(R.id.chk13_7))
					.setText("Anyother legume or nut");
			((CheckBox) v.findViewById(R.id.chk14_1)).setText("Cheese");
			((CheckBox) v.findViewById(R.id.chk14_2)).setText("Yogurt");
			((CheckBox) v.findViewById(R.id.chk14_3))
					.setText("Other milk products");
			((CheckBox) v.findViewById(R.id.chk15_1)).setText("Vegetable oil");
			((CheckBox) v.findViewById(R.id.chk15_2)).setText("Animal fat");
			((CheckBox) v.findViewById(R.id.chk15_3)).setText("Margarine");
			((CheckBox) v.findViewById(R.id.chk16_1)).setText("Chocolate");
			((CheckBox) v.findViewById(R.id.chk16_2)).setText("Sweets/candies");
			((CheckBox) v.findViewById(R.id.chk16_3)).setText("Cake");
			((CheckBox) v.findViewById(R.id.chk16_4))
					.setText("Cookies/sweet biscuits");
			((CheckBox) v.findViewById(R.id.chk16_5)).setText("Sugar");
			((CheckBox) v.findViewById(R.id.chk17_1)).setText("Seasonings");
			((CheckBox) v.findViewById(R.id.chk17_2)).setText("Garlic");
			((CheckBox) v.findViewById(R.id.chk17_3)).setText("Spices");
			((CheckBox) v.findViewById(R.id.chk17_4)).setText("Salt");
			((CheckBox) v.findViewById(R.id.chk18_1)).setText("Prawns");
			((CheckBox) v.findViewById(R.id.chk18_2)).setText("Crab");
			((TextView) v.findViewById(R.id.textView2))
					.setText("If not on list above, write food(s) here");
		}

	}

	private void setfonttobanglamultiple(ViewGroup v) {

		((CheckBox) v.findViewById(R.id.chk1_1))
				.setText("wLPzox( wLPzox wK wK w`â€¡q ivbÅ“v nâ€¡qâ€¡Q â€ Râ€¡b wbâ€¡q dzW MÃ–c Abyhvqx bxâ€¡P â€ KvW Kiyb)");
		/*
		 * ((TextView) v.findViewById(R.id.textView3)) .setText(
		 * "cvwbâ€¡Z ev `yâ€¡a wmÃ— Kiv kmÂ¨ RvZxq Lvevi â€ hgb: mywR,fvZ, iywU, byWzjm,  AbÂ¨vbÂ¨  kmÂ¨ RvZxq `vbv`vi Lv`Â¨"
		 * );
		 */

		((CheckBox) v.findViewById(R.id.chk2_1)).setText("mywR");
		((CheckBox) v.findViewById(R.id.chk2_2)).setText("fvZ");
		((CheckBox) v.findViewById(R.id.chk2_3)).setText("iywU");
		((CheckBox) v.findViewById(R.id.chk2_4)).setText("byWzjm");
		((CheckBox) v.findViewById(R.id.chk2_5))
				.setText("AbÂ¨vbÂ¨  kmÂ¨ RvZxq `vbv`vi Lv`Â¨");

		((CheckBox) v.findViewById(R.id.chk3_1)).setText("wgwÃ³ Kzgov");

		((CheckBox) v.findViewById(R.id.chk3_2)).setText("MvRi");

		((CheckBox) v.findViewById(R.id.chk3_3)).setText("AbÂ¨vbÂ¨ njy` meRx");

		((CheckBox) v.findViewById(R.id.chk4_1)).setText("Avjy");

		((CheckBox) v.findViewById(R.id.chk4_2)).setText("mv`v wgwÃ³ Avjy");

		((CheckBox) v.findViewById(R.id.chk5_1)).setText("wgwÃ³ Kzgov kvK");

		((CheckBox) v.findViewById(R.id.chk5_2)).setText("mwilv kvK");

		((CheckBox) v.findViewById(R.id.chk5_3)).setText("gUi Ã¯wU kvK");

		((CheckBox) v.findViewById(R.id.chk5_4))
				.setText("gUi Ã¯wU kvK,  cyBu kvK");

		((CheckBox) v.findViewById(R.id.chk5_5))
				.setText("AbÂ¨vbÂ¨ Mvp meyR kvK");

		((CheckBox) v.findViewById(R.id.chk6_1)).setText("cvKv Avg");

		((CheckBox) v.findViewById(R.id.chk6_2)).setText("cvKv â€ cuâ€¡cu");

		((CheckBox) v.findViewById(R.id.chk7_1)).setText("Kjv");

		((CheckBox) v.findViewById(R.id.chk7_2)).setText("Avbvim");

		((CheckBox) v.findViewById(R.id.chk7_3)).setText("â€¡cqviv");

		((CheckBox) v.findViewById(R.id.chk7_4)).setText("Avâ€¡cj");

		((CheckBox) v.findViewById(R.id.chk7_5)).setText("AvÂ½yi");

		((CheckBox) v.findViewById(R.id.chk7_7)).setText("Kgjv");

		((CheckBox) v.findViewById(R.id.chk7_6)).setText("AbÂ¨vbÂ¨ dj");

		((CheckBox) v.findViewById(R.id.chk8_1)).setText("Uâ€¡gâ€¡Uv");

		((CheckBox) v.findViewById(R.id.chk8_2)).setText("wcuqvR");

		((CheckBox) v.findViewById(R.id.chk8_3)).setText("gvkiyg");

		((CheckBox) v.findViewById(R.id.chk8_4)).setText("â€¡pom");

		((CheckBox) v.findViewById(R.id.chk8_5)).setText("ZvRv mxg/gUi Ã¯wU");

		((CheckBox) v.findViewById(R.id.chk8_6)).setText("AbÂ¨vbÂ¨ mewR");

		((CheckBox) v.findViewById(R.id.chk9_1)).setText(" KwjRv");

		((CheckBox) v.findViewById(R.id.chk9_2)).setText("wMjv");

		((CheckBox) v.findViewById(R.id.chk9_3)).setText("Heart");

		((CheckBox) v.findViewById(R.id.chk10_1))
				.setText("â€ h â€ Kvb gvsm ,cÃ¯ cvwLmn");

		((CheckBox) v.findViewById(R.id.chk11_1))
				.setText("â€ h â€ Kvb aiâ€¡Yi wWg");

		((CheckBox) v.findViewById(R.id.chk12_1)).setText("ZvRv  gvQ");

		((CheckBox) v.findViewById(R.id.chk12_2)).setText("Ã¯UwK gvQ");

		((CheckBox) v.findViewById(R.id.chk12_3))
				.setText("AbÂ¨vbÂ¨ gvQ / mvgyw`ÂªK Lvevi");

		((CheckBox) v.findViewById(R.id.chk13_1)).setText("mxg");

		((CheckBox) v.findViewById(R.id.chk13_2)).setText("Wvj");

		((CheckBox) v.findViewById(R.id.chk13_3)).setText("mqv");

		((CheckBox) v.findViewById(R.id.chk13_4)).setText("Pxbvev`vg");

		((CheckBox) v.findViewById(R.id.chk13_5)).setText("â€ Kki");

		((CheckBox) v.findViewById(R.id.chk13_6)).setText("fvix Pxbvev`vg");

		((CheckBox) v.findViewById(R.id.chk13_7))
				.setText("AbÂ¨vbÂ¨ â€ h â€ Kvb Wvj ev ev`vg RvZxq");

		((CheckBox) v.findViewById(R.id.chk14_1)).setText("cwbi");

		((CheckBox) v.findViewById(R.id.chk14_2)).setText("`B");

		((CheckBox) v.findViewById(R.id.chk14_3))
				.setText("AbÂ¨vbÂ¨ `ya RvZxq Lv`Â¨");

		((CheckBox) v.findViewById(R.id.chk15_1))
				.setText("Dwâ„¢Â¢Â¾ â€¡Zj (WvjWv)");

		((CheckBox) v.findViewById(R.id.chk15_2)).setText("cÃ¯i Pwe");

		((CheckBox) v.findViewById(R.id.chk15_3)).setText("GK aiâ€¡bi gvLb");

		((CheckBox) v.findViewById(R.id.chk16_1)).setText("PKâ€¡jU");

		((CheckBox) v.findViewById(R.id.chk16_2)).setText("wgwÃ³/ KÂ¨vwÃ›");

		((CheckBox) v.findViewById(R.id.chk16_3)).setText("wcVv");

		((CheckBox) v.findViewById(R.id.chk16_4)).setText("wgwÃ³ weÂ¯â€¹zU");

		((CheckBox) v.findViewById(R.id.chk16_5)).setText("wPwb");

		((CheckBox) v.findViewById(R.id.chk17_1)).setText("Â¯^v` eâ€žwÃ— KviK");

		((CheckBox) v.findViewById(R.id.chk17_2)).setText("imyb");

		((CheckBox) v.findViewById(R.id.chk17_3)).setText("gmjv");
		((CheckBox) v.findViewById(R.id.chk17_4)).setText("jeb");

		((CheckBox) v.findViewById(R.id.chk18_1)).setText("wPswo");

		((CheckBox) v.findViewById(R.id.chk18_2)).setText("KvKov");

		((TextView) v.findViewById(R.id.textView2))
				.setText("hw` Lv`Â¨ ZvwjKvq bv _vâ€¡K Zvnâ€¡j wbâ€¡P Lvevâ€¡ii bvg wjLyb|");

		((TextView) v.findViewById(R.id.textView2)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk1_1)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk2_1)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk2_2)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk2_3)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk2_4)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk2_5)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk3_1)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk3_2)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk3_3)).setTypeface(font);

		((CheckBox) v.findViewById(R.id.chk4_1)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk4_2)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk5_1)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk5_2)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk5_3)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk5_4)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk5_5)).setTypeface(font);

		((CheckBox) v.findViewById(R.id.chk6_1)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk6_2)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk7_1)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk7_2)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk7_3)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk7_4)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk7_5)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk7_7)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk7_6)).setTypeface(font);

		((CheckBox) v.findViewById(R.id.chk8_1)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk8_2)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk8_3)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk8_4)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk8_5)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk8_6)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk9_1)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk9_2)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk9_3)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk10_1)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk11_1)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk12_1)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk12_2)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk12_3)).setTypeface(font);

		((CheckBox) v.findViewById(R.id.chk13_1)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk13_2)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk13_3)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk13_4)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk13_5)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk13_6)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk13_7)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk14_1)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk14_2)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk14_3)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk15_1)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk15_2)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk15_3)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk16_1)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk16_2)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk16_3)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk16_4)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk16_5)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk17_1)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk17_2)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk17_3)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk17_4)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk18_1)).setTypeface(font);
		((CheckBox) v.findViewById(R.id.chk18_2)).setTypeface(font);

	}

	private static void setviewfonttobangla(View view) {

		if (view instanceof CheckBox) {
			((CheckBox) view).setTypeface(font);
		}

		if (view instanceof TextView) {
			((TextView) view).setTypeface(font);
		}
	}

	private void updatemultiple() {

		String sql = "";

		sql = String
				.format("UPDATE tblMainQues SET c609_1_1='%s', c609_2_1='%s', c609_2_2='%s', c609_2_3='%s', c609_2_4='%s', c609_2_5='%s', c609_2_5_other='%s',"
						+ "c609_3_1='%s', c609_3_2='%s', c609_3_3='%s', c609_3_3_other='%s', c609_4_1='%s', c609_4_2='%s', c609_5_1='%s', c609_5_2='%s', c609_5_3='%s',"
						+ "c609_5_4='%s', c609_5_5='%s', c609_5_5_other='%s', c609_6_1='%s', c609_6_2='%s', c609_7_1='%s', c609_7_2='%s', c609_7_3='%s', c609_7_4='%s',"
						+ "c609_7_5='%s', c609_7_6='%s', c609_7_6_other='%s', c609_8_1='%s', c609_8_2='%s', c609_8_3='%s', c609_8_4='%s', c609_8_5='%s', c609_8_6='%s',"
						+ "c609_8_6_other='%s', c609_9_1='%s', c609_9_2='%s', c609_9_3='%s', c609_10_1='%s', c609_11_1='%s', c609_12_1='%s', c609_12_2='%s', c609_12_3='%s',"
						+ "c609_12_3_other='%s', c609_13_1='%s', c609_13_2='%s', c609_13_3='%s', c609_13_4='%s', c609_13_5='%s', c609_13_6='%s', c609_13_7='%s', c609_13_7_other='%s',"
						+ "c609_14_1='%s', c609_14_2='%s', c609_14_3='%s', c609_14_3_other='%s', c609_15_1='%s', c609_15_2='%s', c609_15_3='%s', c609_16_1='%s', c609_16_2='%s', c609_16_3='%s',"
						+ "c609_16_4='%s', c609_17_1='%s', c609_17_2='%s', c609_17_3='%s', c609_18_1='%s', c609_18_2='%s', c609_19_1='%s', c609_7_7='%s', "
						+ "c609_17_4='%s', c609_16_5='%s' where dataid = '%s'",

				chk1_1, chk2_1, chk2_2, chk2_3, chk2_4, chk2_5, et2_5_other,
						chk3_1, chk3_2, chk3_3, et3_3_other, chk4_1, chk4_2,
						chk5_1, chk5_2, chk5_3, chk5_4, chk5_5, et5_5_other,
						chk6_1, chk6_2, chk7_1, chk7_2, chk7_3, chk7_4, chk7_5,
						chk7_6, et7_6_other, chk8_1, chk8_2, chk8_3, chk8_4,
						chk8_5, chk8_6, et8_6_other, chk9_1, chk9_2, chk9_3,
						chk10_1, chk11_1, chk12_1, chk12_2, chk12_3,
						et12_3_other, chk13_1, chk13_2, chk13_3, chk13_4,
						chk13_5, chk13_6, chk13_7, et13_7_other, chk14_1,
						chk14_2, chk14_3, et14_3_other, chk15_1, chk15_2,
						chk15_3, chk16_1, chk16_2, chk16_3, chk16_4, chk17_1,
						chk17_2, chk17_3, chk18_1, chk18_2, et19_1, chk7_7,
						chk17_4, chk16_5, CommonStaticClass.dataId);

		if (dbHelper.executeDMLQuery(sql)) {
			CommonStaticClass.findOutNextSLNo(
					qName,
					CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQnext1());
			CommonStaticClass.nextQuestion(ParentActivity.this);

		} else if (CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQnext1()
				.equalsIgnoreCase("END")) {
			showUserFinishDialogFrmText();
		}

	}

	boolean datasaved = false;

	@Override
	public void onStart() {
		super.onStart(); // Always call the superclass method first

		checksession();

	}

	private void checksession() {
		if (CommonStaticClass.userSpecificId.length() == 0) {

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
											android.os.Process.killProcess(pid);

										}
									}

									dialog.dismiss();

								}
							})

					.setCancelable(false).show();

		}

	}

	class DataIDSpinnerListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View arg1, int pos,
				long arg3) {
			// TODO Auto-generated method stub
			if (parent == spinnerhospital) {
				if (parent.getItemAtPosition(pos).toString().length() > 0) {
					hospital = "5";// = String.valueOf(pos + 1);

				}
			} else if (parent == spinnerward) {
				if (parent.getItemAtPosition(pos).toString().length() > 0) {
					ward = String.valueOf(pos);

				}
			}

		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}

	}

	private void FillAllDataidCombo(final ViewGroup v) {

		final ViewGroup vg = v;
		// Data id spinners

		String strSQL = "Select dataid from tblMainQues";
		ArrayList<String> ids = new ArrayList<String>();
		ids.add("");
		Cursor mCursor = null;
		try {
			mCursor = dbHelperBase.getQueryCursor_BASE(strSQL);
			if (mCursor.getCount() > 0) {
				if (mCursor.moveToFirst()) {
					do {
						ids.add(mCursor.getString(0));
					} while (mCursor.moveToNext());
					ArrayAdapter a = new ArrayAdapter(thisactivity,
							android.R.layout.simple_spinner_dropdown_item, ids);
					a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					((Spinner) v.findViewById(R.id.sp1)).setAdapter(a);
					((Spinner) v.findViewById(R.id.spl1)).setAdapter(a);
				}
			} else {
				ArrayAdapter a = new ArrayAdapter(thisactivity,
						android.R.layout.simple_spinner_dropdown_item, ids);
				a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				((Spinner) v.findViewById(R.id.sp1)).setAdapter(a);
				((Spinner) v.findViewById(R.id.spl1)).setAdapter(a);
			}
		} catch (Exception e) {

			e.printStackTrace();
			// TODO: handle exception

		} finally {
			mCursor.close();
		}

		// ////////////////////////////////////////////////////

		((Spinner) v.findViewById(R.id.sp1))
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> parent,
							View view, int pos, long id) {
						// TODO Auto-generated method stub
						if (pos == 0) {
							((TextView) v.findViewById(R.id.txtHosname))
									.setVisibility(View.GONE);
							((TextView) v.findViewById(R.id.txtPatientname))
									.setVisibility(View.GONE);
							((TextView) v.findViewById(R.id.txtPhoneno))
									.setVisibility(View.GONE);
							((TextView) v.findViewById(R.id.txtAgeyear))
									.setVisibility(View.GONE);
							((TextView) v.findViewById(R.id.txtAgemonth))
									.setVisibility(View.GONE);
							((TextView) v.findViewById(R.id.txtAgeday))
									.setVisibility(View.GONE);
							((TextView) v.findViewById(R.id.txtsex))
									.setVisibility(View.GONE);
							return;
						}
						if (((Spinner) v.findViewById(R.id.sp1))
								.getItemAtPosition(pos).toString().length() > 0) {

							String sql = "select Name,Comp,YearID,MonthID,HosID,HosName,AgeY,AgeM,AgeD,Sex,Phone,PatID from tblMainQues where dataid = "
									+ parent.getItemAtPosition(pos).toString()
									+ "";
							ArrayList<String> ids = new ArrayList<String>();
							ids.add("");
							Cursor mCursor = null;
							try {
								mCursor = dbHelperBase.getQueryCursor_BASE(sql);
								if (mCursor.getCount() > 0) {
									if (mCursor.moveToFirst()) {
										do {
											((TextView) v
													.findViewById(R.id.txtHosname))
													.setVisibility(View.VISIBLE);
											((TextView) v
													.findViewById(R.id.txtPatientname))
													.setVisibility(View.VISIBLE);
											((TextView) v
													.findViewById(R.id.txtPhoneno))
													.setVisibility(View.VISIBLE);
											((TextView) v
													.findViewById(R.id.txtAgeyear))
													.setVisibility(View.VISIBLE);
											((TextView) v
													.findViewById(R.id.txtAgemonth))
													.setVisibility(View.VISIBLE);
											((TextView) v
													.findViewById(R.id.txtAgeday))
													.setVisibility(View.VISIBLE);
											((TextView) v
													.findViewById(R.id.txtsex))
													.setVisibility(View.VISIBLE);

											Name = mCursor.getString(mCursor
													.getColumnIndex("Name"));
											Comp = mCursor.getString(mCursor
													.getColumnIndex("Comp"));
											YearID = mCursor.getString(mCursor
													.getColumnIndex("YearID"));
											MonthID = mCursor.getString(mCursor
													.getColumnIndex("MonthID"));
											HosID = mCursor.getString(mCursor
													.getColumnIndex("HosID"));
											HosName = mCursor.getString(mCursor
													.getColumnIndex("HosName"));
											AgeY = mCursor.getString(mCursor
													.getColumnIndex("AgeY"));
											AgeM = mCursor.getString(mCursor
													.getColumnIndex("AgeM"));
											AgeD = mCursor.getString(mCursor
													.getColumnIndex("AgeD"));
											Sex = mCursor.getString(mCursor
													.getColumnIndex("Sex"));
											Phone = mCursor.getString(mCursor
													.getColumnIndex("Phone"));
											PatID = mCursor.getString(mCursor
													.getColumnIndex("PatID"));
											((TextView) v
													.findViewById(R.id.txtHosname))
													.setText("Hospital Name: "
															+ HosName);
											((TextView) v
													.findViewById(R.id.txtPatientname))
													.setText("Patient Name:"
															+ Name);
											((TextView) v
													.findViewById(R.id.txtPhoneno))
													.setText("Phone No: "
															+ Phone);
											((TextView) v
													.findViewById(R.id.txtAgeyear))
													.setText("AGE:: Year: "
															+ AgeY);
											((TextView) v
													.findViewById(R.id.txtAgemonth))
													.setText("Month: " + AgeM);
											((TextView) v
													.findViewById(R.id.txtAgeday))
													.setText("Day: " + AgeD);
											if (Sex == null) {
												((TextView) v
														.findViewById(R.id.txtsex))
														.setText("Sex:");
											} else {
												if (Sex.equalsIgnoreCase("1")) {
													((TextView) v
															.findViewById(R.id.txtsex))
															.setText("Sex: Male");
												} else {
													((TextView) v
															.findViewById(R.id.txtsex))
															.setText("Sex:Female");
												}
											}

										} while (mCursor.moveToNext());
									}
								} else {
									((TextView) v.findViewById(R.id.txtHosname))
											.setVisibility(View.GONE);
									((TextView) v
											.findViewById(R.id.txtPatientname))
											.setVisibility(View.GONE);
									((TextView) v.findViewById(R.id.txtPhoneno))
											.setVisibility(View.GONE);
									((TextView) v.findViewById(R.id.txtAgeyear))
											.setVisibility(View.GONE);
									((TextView) v
											.findViewById(R.id.txtAgemonth))
											.setVisibility(View.GONE);
									((TextView) v.findViewById(R.id.txtAgeday))
											.setVisibility(View.GONE);
									((TextView) v.findViewById(R.id.txtsex))
											.setVisibility(View.GONE);
									return;
								}
							} catch (Exception e) {

								e.printStackTrace();
								// TODO: handle exception

							} finally {
								mCursor.close();
							}
						}
					}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
					}

				});
	}

	String Name = "", Comp = "", YearID = "", MonthID = "", HosID = "",
			HosName = "", AgeY = "", AgeM = "", AgeD = "", Sex = "",
			Phone = "", PatID = "";

	private void loadGuiFrmHHID(ViewGroup v) {
		// TODO Auto-generated method stub

		final ViewGroup vg = v;
		// textView3 = (TextView)v.findViewById(R.id.textView3);

		qqq = (TextView) v.findViewById(R.id.qqq);

		if (CommonStaticClass.langBng) {
			qqq.setText(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQdescbng());
		} else {
			qqq.setTypeface(null);
			qqq.setText(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQdesceng());
		}

		// FillAllDataidCombo(v);

		confButton = (Button) v.findViewById(R.id.confButton);
		confButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				// updateTableData();

				if (((EditText) vg.findViewById(R.id.txtid)).length() != 5) {
					CommonStaticClass.showMyAlert(con, "Message",
							"Id must be of 5 digits");
					return;
				}
				/*
				 * if (((Spinner) vg.findViewById(R.id.sp1))
				 * .getSelectedItemPosition() == 0) {
				 * CommonStaticClass.showMyAlert(con, "Message",
				 * "Please fill all fields correctly"); return; }
				 */
				if (!((EditText) vg.findViewById(R.id.txtid))
						.getText()
						.toString()
						.equalsIgnoreCase(
								((EditText) vg.findViewById(R.id.txtidre))
										.getText().toString())) {
					CommonStaticClass.showMyAlert(con, "Message",
							"Please fill all fields correctly");
					return;
				}

				CommonStaticClass.dataId = ((EditText) vg
						.findViewById(R.id.txtid)).getText().toString();
				String first3 = Character.toString(CommonStaticClass.dataId.charAt(0)) + Character.toString(CommonStaticClass.dataId.charAt(1))
								+ Character.toString(CommonStaticClass.dataId.charAt(2));
				String last2 = Character.toString(CommonStaticClass.dataId.charAt(3)) + Character.toString(CommonStaticClass.dataId.charAt(4));
				
				int first3Int = Integer.parseInt(first3);
				int last2Int = Integer.parseInt(last2);
				
				if (CommonStaticClass.dataId.length() > 0) {
					if((first3Int >=1 && first3Int <= 720) && (last2Int >=1 && last2Int <= 8))
					{
						progressDialog = ProgressDialog.show(con, "Wait",
								"Please wait while processing next question");
	
						new Thread() {
	
							public void run() {
								try {
									Looper.prepare();
									if (FileRead()) {
	
										if (CommonStaticClass.userSpecificId
												.length() == 0
												|| CommonStaticClass.AssetID
														.length() == 0) {
	
											return;
										}
										//
										updateTableDataFrmHHID(vg);
										// preserveState();
										Message msg = new Message();
										msg.what = UPDATEDONE;
										handlerFrmHHID.sendMessage(msg);
									} else {
										progressDialog.dismiss();
										CommonStaticClass.showFinalAlert(con,
												"Ensure your Asset ID");
	
									}
								} catch (Exception lg) {
									progressDialog.dismiss();
									CommonStaticClass.showFinalAlert(con,
											"Ensure your Asset ID");
	
								} finally {
									progressDialog.dismiss();
								}
								Looper.loop();
							}
	
						}.start();
					}
					else
					{
						CommonStaticClass.showMyAlert(con, "Alert", "First 3 digits should be within 001-720 and last 2 digits should be within 01-08");
						return;
					}
					
						
				} else {
					CommonStaticClass
							.showFinalAlert(con,
									"Please confirm data id is generated by clicking GENERATE");
				}
			}

		});
	}

	private boolean IsValidDataIDUserInput() {
		if (hospital.length() == 0) {
			return false;
		}

		if (Integer.parseInt(hospital) < 2) {
			return false;
		}

		if (ward.length() == 0) {
			return false;
		}
		if (etyearmonth.getText().toString().length() == 0) {
			return false;
		}
		if (etillness.getText().toString().length() < 2) {
			Toast.makeText(con, "Patient should be in two digits", 100).show();
			return false;
		}

		return true;
	}

	private void updateTableDataFrmHHID(ViewGroup v) {
		// TODO Auto-generated method stub
		Cursor cursor = null;
		try {
			/*
			 * if (Code_validation(v) == false) {
			 * CommonStaticClass.showMyAlert(thisactivity, "Error",
			 * "Participant code is not valid for this Upazilla!!!"); return; }
			 */
			String sql = "Select * from  "
					+ CommonStaticClass
							.GetTableName(CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQvar())
					+ " Where dataid='" + CommonStaticClass.dataId
					+ "' and AssetID = '" + CommonStaticClass.AssetID + "'";
			String sqlSc, sqlMc;

			cursor = dbHelper.getQueryCursor(sql);

			if (cursor.getCount() != 0) {
				CommonStaticClass.showMyAlert(con, "Id exist",
						"This id is already exist!");
				return;
			} else {

				/*
				 * if (CommonStaticClass.dataId.length() < 8) {
				 * CommonStaticClass.showMyAlert(con, "Error",
				 * "Select all fields"); return; }
				 */
				String entryDate = CommonStaticClass.GetDate().toString();

				sql = String
						.format("Insert into %s (dataid,VersionNo,assetid, EntryBy,EntryDate) VALUES('%s','%s','%s','%s','%s')",
								CommonStaticClass
										.GetTableName(CommonStaticClass.questionMap
												.get(CommonStaticClass.currentSLNo)
												.getQvar()),
								CommonStaticClass.dataId,
								CommonStaticClass.VersionNo,
								CommonStaticClass.AssetID,

								CommonStaticClass.userSpecificId, entryDate);

				if (dbHelper.executeDMLQuery(sql)) {

					// if (UpdateOtherField(v)) {
					CommonStaticClass.findOutNextSLNo(
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQvar(),
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQnext1());
					CommonStaticClass.nextQuestion(ParentActivity.this);
					CommonStaticClass.addCycleStarted = true;
					// }

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cursor != null)
				cursor.close();
		}
	}

	private boolean UpdateOtherField(ViewGroup v) {

		try {

			String strSQL = "update tblMainQues set PatName = '" + Name
					+ "',Comp = '" + Comp + "',YearID = '" + YearID
					+ "',MonthID  = '" + MonthID + "',HosID  = '" + HosID
					+ "',HosName  = '" + HosName + "',PatID = '" + PatID
					+ "',AgeY  = '" + AgeY + "',AgeM   = '" + AgeM
					+ "',AgeD   = '" + AgeD + "',Sex   = '" + Sex
					+ "',Phone   = '" + Phone + "' where dataid = '"
					+ CommonStaticClass.dataId + "'";
			dbHelper.executeDMLQuery(strSQL);
		} catch (Exception ex) {
			Log.e("Exception", ex.getMessage());
		}

		return true;

	}

	/*
	 * private boolean Code_validation(ViewGroup v) { if
	 * ((CommonStaticClass.GetSpinnerValueFromString( (Spinner)
	 * v.findViewById(R.id.sp4)).trim() == "1") && (Integer.parseInt(((EditText)
	 * v .findViewById(R.id.txtParticipant_list)).getText() .toString()) >= 1001
	 * && Integer.parseInt(((EditText) v
	 * .findViewById(R.id.txtParticipant_list)).getText() .toString()) <= 2000))
	 * { return true; }
	 * 
	 * else if ((CommonStaticClass.GetSpinnerValueFromString( (Spinner)
	 * v.findViewById(R.id.sp4)).trim() == "2") && (Integer.parseInt(((EditText)
	 * v .findViewById(R.id.txtParticipant_list)).getText() .toString()) >= 2001
	 * && Integer.parseInt(((EditText) v
	 * .findViewById(R.id.txtParticipant_list)).getText() .toString()) <= 3000))
	 * { return true; }
	 * 
	 * 
	 * else if ((cboHospitalID.Text.Trim() == "2") &&
	 * (Convert.ToInt16(txtParticipant_list.Text) >= 2001 && Convert
	 * .ToInt16(txtParticipant_list.Text) <= 3000)) { return true; }
	 * 
	 * else if ((CommonStaticClass.GetSpinnerValueFromString( (Spinner)
	 * v.findViewById(R.id.sp4)).trim() == "3") && (Integer.parseInt(((EditText)
	 * v .findViewById(R.id.txtParticipant_list)).getText() .toString()) >= 3001
	 * && Integer.parseInt(((EditText) v
	 * .findViewById(R.id.txtParticipant_list)).getText() .toString()) <= 4000))
	 * { return true; }
	 * 
	 * else if ((CommonStaticClass.GetSpinnerValueFromString( (Spinner)
	 * v.findViewById(R.id.sp4)).trim() == "4") && (Integer.parseInt(((EditText)
	 * v .findViewById(R.id.txtParticipant_list)).getText() .toString()) >= 4001
	 * && Integer.parseInt(((EditText) v
	 * .findViewById(R.id.txtParticipant_list)).getText() .toString()) <= 5000))
	 * { return true; }
	 * 
	 * else if ((CommonStaticClass.GetSpinnerValueFromString( (Spinner)
	 * v.findViewById(R.id.sp4)).trim() == "5") && (Integer.parseInt(((EditText)
	 * v .findViewById(R.id.txtParticipant_list)).getText() .toString()) >= 5001
	 * && Integer.parseInt(((EditText) v
	 * .findViewById(R.id.txtParticipant_list)).getText() .toString()) <= 6000))
	 * { return true; }
	 * 
	 * else if ((CommonStaticClass.GetSpinnerValueFromString( (Spinner)
	 * v.findViewById(R.id.sp4)).trim() == "6") && (Integer.parseInt(((EditText)
	 * v .findViewById(R.id.txtParticipant_list)).getText() .toString()) >= 6001
	 * && Integer.parseInt(((EditText) v
	 * .findViewById(R.id.txtParticipant_list)).getText() .toString()) <= 7000))
	 * { return true; }
	 * 
	 * else if ((CommonStaticClass.GetSpinnerValueFromString( (Spinner)
	 * v.findViewById(R.id.sp4)).trim() == "7") && (Integer.parseInt(((EditText)
	 * v .findViewById(R.id.txtParticipant_list)).getText() .toString()) >= 7001
	 * && Integer.parseInt(((EditText) v
	 * .findViewById(R.id.txtParticipant_list)).getText() .toString()) <= 8000))
	 * { return true; }
	 * 
	 * else if ((CommonStaticClass.GetSpinnerValueFromString( (Spinner)
	 * v.findViewById(R.id.sp4)).trim() == "8") && (Integer.parseInt(((EditText)
	 * v .findViewById(R.id.txtParticipant_list)).getText() .toString()) >= 8001
	 * && Integer.parseInt(((EditText) v
	 * .findViewById(R.id.txtParticipant_list)).getText() .toString()) <= 9000))
	 * { return true; }
	 * 
	 * else { return false; } }
	 */

	protected boolean doesExist(String dataid2) {
		String id = "";
		String sql = "Select * from tblMainQues where dataid='" + dataid2 + "'";
		Cursor mCursor = null;
		try {
			mCursor = dbHelper.getQueryCursor(sql);
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

	protected String getRandomId(String sampleid) {
		String randomID = "";
		String sql = "Select randomid from tblSamplesInfo where sampleid='"
				+ sampleid + "'";
		Cursor mCursor = null;
		try {
			mCursor = dbHelper.getQueryCursor(sql);
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

	private boolean IsValidClusterID(String ClusterID) {
		String id = "";
		String sql = "Select * from tblSamplesinfo where clusterid='"
				+ Integer.parseInt(ClusterID) + "'";
		Cursor mCursor = null;
		try {
			mCursor = dbHelper.getQueryCursor(sql);
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

	protected void checkThisIdIsAlreadyExistFrmHHID(String dataId) {
		// TODO Auto-generated method stub
		String sql = "Select dataid from "
				+ CommonStaticClass.GetTableName(CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar())
				+ " where dataid = '" + CommonStaticClass.dataId + "'";
		Cursor mCursor = null;
		try {
			mCursor = dbHelper.getQueryCursor(sql);
			if (mCursor.getCount() > 0) {
				CommonStaticClass.showMyAlert(con, "Id exist",
						"This id is already exist please GENERATE another");
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (mCursor != null)
				mCursor.close();
		}
	}

	private Handler handlerFrmHHID = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UPDATEDONE:
				progressDialog.dismiss();
				break;
			}

		}
	};

	// FrmMessage part
	private void loadGuiFrmMessage(final ViewGroup v) {
		// TODO Auto-generated method stub
		qqq = (TextView) v.findViewById(R.id.qqq);

		//code by imtiaz khan
				if(CommonStaticClass.questionMap.
						get(CommonStaticClass.currentSLNo).getQvar().equalsIgnoreCase("m213"))
				{
					Typeface font = Typeface.createFromAsset(getAssets(),
							"SolaimanLipi.ttf");
					qqq.setTypeface(font);
					String PID = "";
					String textBang = "দয়া করে হুইলপ্যাকে এই ক্রমানুসারে লেবেল বসান";
					String textEng = "PROMPT: Please label the whirlpak with the following label:";
					PID = "T."+CommonStaticClass.dataId;
				
					
					
						
						if(CommonStaticClass.langBng)			
							qqq.setText(textBang+
							"\n\n"+ PID+"."+getChoiceValue("q11_2")+"."+getChoiceValue("q11_3"));
						else 
							qqq.setText(textEng+
									"\n\n"+ PID+"."+getChoiceValue("q11_2")+"."+getChoiceValue("q11_3"));
						
					
					
				}
				else if (CommonStaticClass.langBng) {
			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0) {
				Typeface font = Typeface.createFromAsset(getAssets(),
						"Siyam Rupali ANSI.ttf");
				qqq.setTypeface(font);
			}

			qqq.setText(CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0 ? Html
					.fromHtml(CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQdescbng())
					: Html.fromHtml(CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQdesceng()));
			/*
			 * qqq.setText(CommonStaticClass.questionMap
			 * .get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0 ?
			 * CommonStaticClass.questionMap
			 * .get(CommonStaticClass.currentSLNo).getQdescbng() :
			 * CommonStaticClass.questionMap.get(
			 * CommonStaticClass.currentSLNo).getQdesceng());
			 */

		} else {
			qqq.setTypeface(null);
			qqq.setText(Html.fromHtml(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQdesceng()));
			/*
			 * qqq.setText(CommonStaticClass.questionMap.get(
			 * CommonStaticClass.currentSLNo).getQdesceng());
			 */

			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQvar()
					.equalsIgnoreCase("q9day1")) {

				String val = CommonStaticClass.getSkip("sampleid", "tblDay1",
						dbHelper);

				qqq.setText(qqq.getText() + val);
			}

			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQvar()
					.equalsIgnoreCase("q10day1")) {

				String val = CommonStaticClass.getSkip("randomid", "tblDay1",
						dbHelper);

				qqq.setText(qqq.getText() + val);
			}

			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQvar()
					.equalsIgnoreCase("q9day2")) {

				String val = CommonStaticClass.getSkip("sampleid", "tblDay2",
						dbHelper);

				qqq.setText(qqq.getText() + val);
			}

			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQvar()
					.equalsIgnoreCase("q10day2")) {

				String val = CommonStaticClass.getSkip("randomid", "tblDay2",
						dbHelper);

				qqq.setText(qqq.getText() + val);
			}

		}

		prevButton = (Button) v.findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				userPressedPrevious(ParentActivity.this);
			}

		});
		saveNxtButton = (Button) v.findViewById(R.id.saveNxtButton);

		saveNxtButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				updateTableDataFrmMessage();

			}

		});
		clButton = (Button) v.findViewById(R.id.clButton);
		clButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				resetViewGroup((ViewGroup) v);
			}

		});
	}

	private void updateTableDataFrmMessage() {
		// TODO Auto-generated method stub
		CommonStaticClass.findOutNextSLNo(
				CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar(),
				CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQnext1());
		CommonStaticClass.nextQuestion(ParentActivity.this);
	}

	// FrmMultipleCheckCombo part
	private void loadGuiFrmMultipleCheckCombo(final ViewGroup v) {
		// TODO Auto-generated method stub
		// checkDbHasPreviousDataForThisHouseHold();
		if (aaa != null && aaa.size() > 0) {
			aaa.clear();
		}
		optionList1 = new ArrayList<String>();
		optionCodeList1 = new ArrayList<Integer>();
		qqq = (TextView) v.findViewById(R.id.qqq);
		qName = CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar();

		if (CommonStaticClass.langBng) {
			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0) {
				qqq.setTypeface(font);
			}
			;
			qqq.setText(CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0 ? CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng()
					: CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQdesceng());
		} else {
			qqq.setTypeface(null);
			qqq.setText(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQdesceng());
		}

		checkBoxHolder = (LinearLayout) v.findViewById(R.id.checkBoxHolder);
		checkBoxHolder.removeAllViews();
		op = CommonStaticClass.findOptionsForThisQuestion(dbHelper,
				CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar());

		LinearLayout.LayoutParams lnlParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams layoutParamForcheck = new LinearLayout.LayoutParams(
				((dm.widthPixels - 65) / 3) * 2,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams layoutParamForSpin = new LinearLayout.LayoutParams(
				((dm.widthPixels + 65) / 3),
				LinearLayout.LayoutParams.WRAP_CONTENT);

		optionList1.add("");
		optionCodeList1.add(-1);
		for (int i = 0; i < op.codeList.size(); i++) {

			if (op.qidList.get(i).contains("Options")) {
				Log.e("op.qidList.get(i)", op.qidList.get(i));
				if (CommonStaticClass.langBng) {
					optionList1.add(op.capBngList.get(i));
				} else {
					optionList1.add(op.capEngList.get(i));
				}
				optionCodeList1.add(op.codeList.get(i));
				continue;
			}
			aaa.add(-1);
		}

		// op.codeList.toArray().
		for (int i = 0; i < op.codeList.size(); i++) {

			Log.e("op.qidList.get(i)", op.qidList.get(i));
			if (op.qidList.get(i).contains("Options")) {
				continue;
			}
			LinearLayout ln = new LinearLayout(this);
			ln.setOrientation(LinearLayout.HORIZONTAL);
			final CheckBox checkButton = new CheckBox(this);
			if (CommonStaticClass.langBng) {
				checkButton.setTypeface(font);
				checkButton
						.setText(op.capBngList.get(i).length() > 0 ? op.capBngList
								.get(i) : op.capEngList.get(i));
			} else {
				checkButton.setTypeface(null);
				checkButton.setText(op.capEngList.get(i));
			}
			checkButton.setId(i);
			final Spinner spinner = new Spinner(this);
			layoutParamForSpin.weight = 1;
			ln.addView(spinner, 0, layoutParamForSpin);
			spinner.setVisibility(View.INVISIBLE);
			ln.addView(checkButton, 0, layoutParamForcheck);

			ArrayAdapter adapter1;

			if (CommonStaticClass.langBng) {
				adapter1 = new SpinAdapter(this, optionList1, true);
			} else {
				adapter1 = new ArrayAdapter(this,
						android.R.layout.simple_spinner_item, optionList1);
			}

			adapter1.setDropDownViewResource(CommonStaticClass.langBng ? R.layout.checkedspintextview
					: android.R.layout.simple_spinner_dropdown_item);

			spinner.setAdapter(adapter1);
			spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

				public void onItemSelected(AdapterView<?> parent, View view,
						int pos, long id) {
					// TODO Auto-generated method stub

					aaa.set(checkButton.getId(), optionCodeList1.get(pos));
				}

				public void onNothingSelected(AdapterView<?> arg0) {
				}

			});
			checkBoxHolder.addView(ln, 0, lnlParams);

			checkButton
					.setOnCheckedChangeListener(new OnCheckedChangeListener() {

						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							// TODO Auto-generated method stub
							if (isChecked) {
								Log.e("id :", "" + checkButton.getId());
								spinner.setSelection(0);
								spinner.setVisibility(View.VISIBLE);
							} else {
								aaa.set(checkButton.getId(), -1);// added by me
								spinner.setVisibility(View.INVISIBLE);
							}
						}
					});
			selectCheckAndComboFrmMultipleCheckCombo(op.qidList.get(i),
					checkButton, spinner);

		}

		prevButton = (Button) v.findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vb) {
				// TODO Auto-generated method stub
				userPressedPrevious(ParentActivity.this);
			}

		});

		saveNxtButton = (Button) v.findViewById(R.id.saveNxtButton);

		saveNxtButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				updateTableDataFrmMultipleCheckCombo(v);

			}

		});
		clButton = (Button) v.findViewById(R.id.clButton);
		clButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				resetViewGroup((ViewGroup) v);
			}

		});
		notesButton = (Button) v.findViewById(R.id.btnNote);
		notesButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				FraNotes();

			}

		});
	}

	private void loadGuiFrmMultipleCheckNumeric(final ViewGroup v) {
		// TODO Auto-generated method stub
		// checkDbHasPreviousDataForThisHouseHold();
		if (aaa != null && aaa.size() > 0) {
			aaa.clear();
		}
		optionList1 = new ArrayList<String>();
		optionCodeList1 = new ArrayList<Integer>();
		qqq = (TextView) v.findViewById(R.id.qqq);
		qName = CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar();

		if (CommonStaticClass.langBng) {
			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0) {
				qqq.setTypeface(font);
			}
			;
			qqq.setText(CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0 ? CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng()
					: CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQdesceng());
		} else {
			qqq.setTypeface(null);
			qqq.setText(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQdesceng());
		}

		checkBoxHolder = (LinearLayout) v.findViewById(R.id.checkBoxHolder);
		checkBoxHolder.removeAllViews();
		op = CommonStaticClass.findOptionsForThisQuestion(dbHelper,
				CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar());

		LinearLayout.LayoutParams lnlParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams layoutParamForcheck = new LinearLayout.LayoutParams(
				((dm.widthPixels - 65) / 3) * 2,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams layoutParamForSpin = new LinearLayout.LayoutParams(
				((dm.widthPixels + 65) / 3),
				LinearLayout.LayoutParams.WRAP_CONTENT);

		for (int i = 0; i < op.codeList.size(); i++) {

			aaa.add(-1);
		}

		for (int i = 0; i < op.codeList.size(); i++) {

			Log.e("op.qidList.get(i)", op.qidList.get(i));

			LinearLayout ln = new LinearLayout(this);
			ln.setOrientation(LinearLayout.HORIZONTAL);
			final CheckBox checkButton = new CheckBox(this);
			if (CommonStaticClass.langBng) {
				checkButton.setTypeface(font);
				checkButton
						.setText(op.capBngList.get(i).length() > 0 ? op.capBngList
								.get(i) : op.capEngList.get(i));
			} else {
				checkButton.setTypeface(null);
				checkButton.setText(op.capEngList.get(i));
			}
			checkButton.setId(i);

			final EditText et = new EditText(this);

			/*
			 * if(qName.equalsIgnoreCase(string)
			 * if(checkButton.getText().toString
			 * ().toLowerCase().contains("other") ||
			 * checkButton.getText().toString
			 * ().toLowerCase().contains("AbÂ¨vbÂ¨")) {
			 * et.setInputType(InputType.TYPE_CLASS_TEXT); }
			 * 
			 * else
			 */
			et.setInputType(InputType.TYPE_CLASS_NUMBER);

			layoutParamForSpin.weight = 1;
			ln.addView(et, 0, layoutParamForSpin);
			et.setVisibility(View.INVISIBLE);
			ln.addView(checkButton, 0, layoutParamForcheck);

			/*
			 * if (checkButton.getText().toString().trim().toLowerCase()
			 * .contains("others") ||
			 * checkButton.getText().toString().trim().toLowerCase()
			 * .contains("AbÂ¨vbÂ¨: wjLyb")) { ln.removeView(et);
			 * et.setVisibility(View.GONE); }
			 */

			/*
			 * if (op.capEngList.get(i).toLowerCase().contains("others")) {
			 * ln.removeView(et); et.setVisibility(View.GONE); }
			 */

			et.addTextChangedListener(new TextWatcher() {

				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
					// TODO Auto-generated method stub

				}

				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
					// TODO Auto-generated method stub

				}

				public void afterTextChanged(Editable s) {

					if (checkButton.isChecked()) {
						if (s.toString().length() > 0) {
							aaa.set(checkButton.getId(),
									Integer.parseInt(s.toString()));
						}
					}
				}
			});

			checkBoxHolder.addView(ln, 0, lnlParams);

			checkButton
					.setOnCheckedChangeListener(new OnCheckedChangeListener() {

						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							// TODO Auto-generated method stub
							if (isChecked) {
								Log.e("id :", "" + checkButton.getId());
								aaa.set(checkButton.getId(), -1);
								/*
								 * if (qName.equalsIgnoreCase("q37c1") ||
								 * qName.equalsIgnoreCase("q37c2")
								 * ||qName.equalsIgnoreCase("q37c3") ||
								 * qName.equalsIgnoreCase("q37c4") ||
								 * qName.equalsIgnoreCase("q37c5") ||
								 * qName.equalsIgnoreCase("q37c6") ||
								 * qName.equalsIgnoreCase("q37c7")) {
								 * 
								 * if (checkButton.getId()==6) {
								 * 
								 * 
								 * if (checkButton.getText().toString()
								 * .toLowerCase().contains("others") ||
								 * checkButton .getText().toString().trim(
								 * ).toLowerCase() .contains("AbÂ¨vbÂ¨: wjLyb"))
								 * {
								 * 
								 * 
								 * //aaa.set(checkButton.getId(), 1);
								 * et.setVisibility(View.INVISIBLE); return; } }
								 * else { //aaa.set(checkButton.getId(), 1);
								 * et.setVisibility(View.VISIBLE); }
								 */
								et.setVisibility(View.VISIBLE);

							} else {
								aaa.set(checkButton.getId(), -1);
								et.setVisibility(View.INVISIBLE);
							}
						}
					});
			selectCheckAndComboFrmMultipleCheckNumeric(op.qidList.get(i),
					checkButton, et);

		}

		prevButton = (Button) v.findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vb) {
				// TODO Auto-generated method stub
				userPressedPrevious(ParentActivity.this);
			}

		});

		saveNxtButton = (Button) v.findViewById(R.id.saveNxtButton);

		saveNxtButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				updateTableDataFrmMultipleCheckNumeric(v);

			}

		});
		clButton = (Button) v.findViewById(R.id.clButton);
		clButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				resetViewGroup((ViewGroup) v);
			}

		});
		notesButton = (Button) v.findViewById(R.id.btnNote);
		notesButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				FraNotes();

			}

		});
	}

	private void selectCheckAndComboFrmMultipleCheckCombo(String inColumn,
			CheckBox checkButton, Spinner spinner) {
		// TODO Auto-generated method stub
		String sql = "";
		if (!CommonStaticClass.isMember)
			sql = "Select "
					+ inColumn
					+ " from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " where dataid='" + CommonStaticClass.dataId + "'";
		else
			sql = "Select "
					+ inColumn
					+ " from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " where dataid='" + CommonStaticClass.dataId
					+ "' and memberid=" + CommonStaticClass.memberID;

		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.getCount() > 0) {
				doFill1FrmMultipleCheckCombo(mCursor1, inColumn, checkButton,
						spinner);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}

	}

	private void selectCheckAndComboFrmMultipleCheckNumeric(String inColumn,
			CheckBox checkButton, EditText et) {
		// TODO Auto-generated method stub
		String sql = "";
		if (!CommonStaticClass.isMember)
			sql = "Select "
					+ inColumn
					+ " from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " where dataid='" + CommonStaticClass.dataId + "'";
		else
			sql = "Select "
					+ inColumn
					+ " from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " where dataid='" + CommonStaticClass.dataId
					+ "' and memberid=" + CommonStaticClass.memberID;

		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.getCount() > 0) {
				doFillFrmMultipleCheckNumeric(mCursor1, inColumn, checkButton,
						et);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}

	}

	private boolean doFill1FrmMultipleCheckCombo(Cursor mCursor1,
			String inColumn, CheckBox checkButton, Spinner spinner) {
		boolean dataOk = false;
		if (mCursor1.moveToFirst()) {
			do {
				if (mCursor1.getColumnIndexOrThrow(inColumn) != -1) {
					Log.e("column", "exist column:" + inColumn);
					try {
						String a = mCursor1.getString(mCursor1
								.getColumnIndex(inColumn));
						Log.e("a", a + "   lklklk");

						if (optionCodeList1.contains(Integer.parseInt(a))
								&& Integer.parseInt(a) != -1) {

							int pos = optionCodeList1.indexOf(Integer
									.parseInt(a));

							checkButton.setChecked(true);
							spinner.setSelection(pos);
							dataOk = true;

						}

					} catch (Exception e) {
						// TODO: handle exception
					} finally {
						if (qName.equalsIgnoreCase("Q5_severity_signs")) {
							String ava = "";
							String Q3Column = "Q3_signs_dt_"
									+ inColumn.charAt(inColumn.length() - 1);
							String sql = "";
							sql = "Select "
									+ Q3Column
									+ " from "
									+ CommonStaticClass.questionMap.get(
											CommonStaticClass.currentSLNo)
											.getTablename() + " where dataid='"
									+ CommonStaticClass.dataId + "'";

							Cursor mmCursor = null;
							try {
								mmCursor = dbHelper.getQueryCursor(sql);
								if (mmCursor.moveToFirst()) {
									do {
										ava = mmCursor.getString(mmCursor
												.getColumnIndex(Q3Column));
									} while (mmCursor.moveToNext());
								}
								if (ava == null) {
									spinner.setSelection(0);
									checkButton.setChecked(false);
									checkButton.setVisibility(View.GONE);
								} else {
									if (ava.trim().length() == 0) {
										spinner.setSelection(0);
										checkButton.setChecked(false);
										checkButton.setVisibility(View.GONE);
									} else {
										checkButton.setChecked(true);
										checkButton.setEnabled(false);
									}
								}

							} catch (Exception e) { // TODO: handle exception
								Log.e("cursor", "is null");
							} finally {
								if (mmCursor != null)
									mmCursor.close();
							}

						}
					}

				}

			} while (mCursor1.moveToNext());
		}

		return dataOk;
	}

	private boolean doFillFrmMultipleCheckNumeric(Cursor mCursor1,
			String inColumn, CheckBox checkButton, EditText et) {
		boolean dataOk = false;
		if (mCursor1.moveToFirst()) {
			do {
				if (mCursor1.getColumnIndexOrThrow(inColumn) != -1) {
					Log.e("column", "exist column:" + inColumn);
					try {
						String a = mCursor1.getString(mCursor1
								.getColumnIndex(inColumn));
						Log.e("a", a + "   lklklk");

						if (a.length() > 0 && !a.equalsIgnoreCase("-1")) {
							checkButton.setChecked(true);

							et.setText(a);
							dataOk = true;
						}

					} catch (Exception e) {

					}
				}

			} while (mCursor1.moveToNext());
		}

		return dataOk;
	}

	private String getSkip(String column, String tablename) {

		String GtSkip = "";
		String sql = "";

		if (!CommonStaticClass.isMember)

			sql = "Select " + column + " from " + tablename + " where dataid='"
					+ CommonStaticClass.dataId + "'";

		// String data ="";

		Cursor mCursor = null;

		try {

			mCursor = dbHelper.getQueryCursor(sql);

			if (mCursor.getCount() > 0) {

				if (mCursor.moveToFirst()) {

					do {

						GtSkip = mCursor.getString(mCursor
								.getColumnIndex(column));

					} while (mCursor.moveToNext());

				}

			}

		} catch (Exception e) {

			// TODO: handle exception

		}

		return GtSkip;

	}

	private void updateTableDataFrmMultipleCheckCombo(ViewGroup v) {
		// TODO Auto-generated method stub
		spinnerOK = true;
		CheckBoxNotSeletedFrmMultipleCheckCombo();
		if (spinnerOK) {
			spinnerVisibleButNotSeletedFrmMultipleCheckCombo((ViewGroup) v);
		}
		String sql = "";
		// spinnerOK = true;
		if (spinnerOK) {

			sql = "UPDATE "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " SET ";
			for (int i = 0; i < op.codeList.size(); i++) {
				if (op.qidList.get(i).contains("Options")) {
					continue;
				}
				if (op.qidList.get(i + 1).contains("Options")) {
					sql += op.qidList.get(i) + " = '" + aaa.get(i) + "'";
					break;
				}
				sql += op.qidList.get(i) + " = '" + aaa.get(i) + "',";
			}
			if (!CommonStaticClass.isMember)
				sql += "where dataid='" + CommonStaticClass.dataId + "'";
			else
				// added later 7 aug 2012
				sql += "where dataid='" + CommonStaticClass.dataId
						+ "' and memberid=" + CommonStaticClass.memberID;

			if (checkIfSingleOptionIsCheckedFrmMultipleCheckCombo()) {

				if (dbHelper.executeDMLQuery(sql)) {
					if (!gotoskip()) {

						CommonStaticClass.findOutNextSLNo(
								CommonStaticClass.questionMap.get(
										CommonStaticClass.currentSLNo)
										.getQvar(),
								CommonStaticClass.questionMap.get(
										CommonStaticClass.currentSLNo)
										.getQnext1());
						CommonStaticClass.nextQuestion(ParentActivity.this);
					}
				}
			}

			/*
			 * else { if (CommonStaticClass.questionMap
			 * .get(CommonStaticClass.currentSLNo).getQvar()
			 * .equalsIgnoreCase("p3_1")) { if (dbHelper.executeDMLQuery(sql)) {
			 * showUserFinishDialogFrmMultipleCheckCombo();
			 * 
			 * } else {
			 * 
			 * CommonStaticClass } .showMyAlert(con, "Please check one!!!",
			 * "You didn't checked any answer please select one to preceed"); }
			 * } }
			 */
		} else {
			CommonStaticClass.showMyAlert(con, "Please select item!!!",
					"Please select an item from combo.");
		}

	}

	private void updateTableDataFrmMultipleCheckNumeric(ViewGroup v) {
		// TODO Auto-generated method stub
		/*
		 * spinnerOK = true; CheckBoxNotSeletedFrmMultipleCheckCombo(); if
		 * (spinnerOK) {
		 * spinnerVisibleButNotSeletedFrmMultipleCheckCombo((ViewGroup) v); }
		 */
		String sql = "";
		// spinnerOK = true;
		// if (spinnerOK) {

		sql = "UPDATE "
				+ CommonStaticClass.questionMap.get(
						CommonStaticClass.currentSLNo).getTablename() + " SET ";
		for (int i = 0; i < op.codeList.size(); i++) {
			/*
			 * if (op.qidList.get(i).contains("Options")) { continue; } if
			 * (op.qidList.get(i + 1).contains("Options")) { sql +=
			 * op.qidList.get(i) + " = '" + aaa.get(i) + "'"; break; }
			 */
			sql += op.qidList.get(i) + " = '" + listvalues.get(i) + "',";
		}
		sql = (String) sql.subSequence(0, sql.length() - 1);

		if (!CommonStaticClass.isMember)
			sql += " where dataid='" + CommonStaticClass.dataId + "'";
		else
			// added later 7 aug 2012
			sql += " where dataid='" + CommonStaticClass.dataId
					+ "' and memberid=" + CommonStaticClass.memberID;

		if (checkIfSingleOptionIsCheckedFrmMultipleCheckCombo()) {

			if (dbHelper.executeDMLQuery(sql)) {

				// Newly Added
				CommonStaticClass.findOutNextSLNo(
						CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQvar(),
						CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQnext1());
				CommonStaticClass.nextQuestion(ParentActivity.this);
			}

		} else
			CommonStaticClass
					.showMyAlert(con, "Please check one!!!",
							"You didn't checked any answer please select one to preceed");

	}

	private void showUserFinishDialogFrmMultipleCheckCombo() {
		// get prompts.xml view
		LayoutInflater li = LayoutInflater.from(con);
		LinearLayout promptsView = (LinearLayout) li.inflate(R.layout.prompts,
				null);

		promptsView.removeView((EditText) promptsView
				.findViewById(R.id.editTextDialogUserInput));

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(con);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);

		final TextView textInfo = (TextView) promptsView
				.findViewById(R.id.textView1);
		textInfo.setText("Do u want to proceed without giving input?");

		// set dialog message
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {

								CommonStaticClass.findOutNextSLNo(
										CommonStaticClass.questionMap.get(
												CommonStaticClass.currentSLNo)
												.getQvar(),
										CommonStaticClass.questionMap.get(
												CommonStaticClass.currentSLNo)
												.getQnext1());
								CommonStaticClass
										.nextQuestion(ParentActivity.this);

							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
	}

	private void CheckBoxNotSeletedFrmMultipleCheckCombo() {
		if (qName.equalsIgnoreCase("q608") || qName.equalsIgnoreCase("q612")
				|| qName.equalsIgnoreCase("q611")) {
			for (int i = 0; i < aaa.size(); i++) {
				if (aaa.get(i) != -1) {
					spinnerOK = true;
					return;
				} else {

					spinnerOK = false;

					if (qName.equalsIgnoreCase("q61")) {
						spinnerOK = true;
					}
				}
			}
		} else if (aaa.contains(-1)) {
			spinnerOK = false;
			if (qName.equalsIgnoreCase("q61") || qName.equalsIgnoreCase("q47")
					|| qName.equalsIgnoreCase("Q5_severity_signs")) {
				spinnerOK = true;
			}
		}
	}

	private void spinnerVisibleButNotSeletedFrmMultipleCheckCombo(
			ViewGroup viewGroup) {
		int nrOfChildren = viewGroup.getChildCount();
		for (int i = 0; i < nrOfChildren; i++) {
			View view = viewGroup.getChildAt(i);

			if (view instanceof Spinner
					&& (view.getVisibility() == View.VISIBLE)) {
				if (((Spinner) view).getSelectedItemPosition() == 0) {
					spinnerOK = false;
				}
			}
			if (view instanceof ViewGroup) {
				spinnerVisibleButNotSeletedFrmMultipleCheckCombo((ViewGroup) view);
			}
		}
	}

	private boolean checkIfSingleOptionIsCheckedFrmMultipleCheckCombo() {
		for (int i = 0; i < aaa.size(); i++) {
			if (!(aaa.get(i) == -1)) {
				return true;
			}

		}
		if (qName.equalsIgnoreCase("Q5_severity_signs")) {
			return true;
		}
		return false;
	}

	// FrmMultipleChoice part
	private void loadGuiFrmMultipleChoice(final ViewGroup v) {
		// TODO Auto-generated method stub
		if (aaa != null && aaa.size() > 0) {
			aaa.clear();
		}

		qqq = (TextView) v.findViewById(R.id.qqq);
		qName = CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar();

		if (CommonStaticClass.langBng) {
			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0) {
				font = Typeface.createFromAsset(con.getAssets(),
						"Siyam Rupali ANSI.ttf");

				qqq.setTypeface(font);
			}
			;
			qqq.setText(CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0 ? CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng()
					: CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQdesceng());
		} else {
			qqq.setTypeface(null);
			qqq.setText(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQdesceng());
		}

		checkBoxHolder = (LinearLayout) v.findViewById(R.id.checkBoxHolder);
		checkBoxHolder.removeAllViews();

		if (qName.equalsIgnoreCase("q17hmd1")
				|| qName.equalsIgnoreCase("q18md1")
				|| qName.equalsIgnoreCase("q17hmd2")
				|| qName.equalsIgnoreCase("q18md2")) {
			/*
			 * op = CommonStaticClass.findOptionsForMedicineList( dbHelper,
			 * CommonStaticClass.questionMap.get(
			 * CommonStaticClass.currentSLNo).getQvar());
			 */
		} else {
			op = CommonStaticClass.findOptionsForThisQuestion(
					dbHelper,
					CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar());
		}

		Collections.reverse(op.capBngList);
		Collections.reverse(op.capEngList);
		Collections.reverse(op.codeList);
		Collections.reverse(op.qidList);
		Collections.reverse(op.qnList);

		LinearLayout.LayoutParams lnlParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams layoutParamForcheck = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);

		for (int i = 0; i < op.codeList.size(); i++) {
			aaa.add(-1);
		}

		LinearLayout.LayoutParams layoutParamForTextBox = new LinearLayout.LayoutParams(
				(dm.widthPixels / 3), LinearLayout.LayoutParams.WRAP_CONTENT);

		for (int i = 0; i < op.codeList.size(); i++) {
			final LinearLayout ln = new LinearLayout(this);
			ln.setOrientation(LinearLayout.HORIZONTAL);
			final CheckBox checkButton = new CheckBox(this);
			if (CommonStaticClass.langBng) {
				checkButton.setTypeface(font);
				checkButton
						.setText(op.capBngList.get(i).length() > 0 ? op.capBngList
								.get(i) : op.capEngList.get(i));
			} else {
				checkButton.setTypeface(null);
				checkButton.setText(op.capEngList.get(i));
			}

			checkButton.setId(op.codeList.get(i));

			if (op.qnList.get(i).length() > 1) {
				ln.setWeightSum((float) 2.0);
				final EditText edbox = new EditText(this);
				edbox.setId(i);
				
				//only for this project by imtiaz khan
				if(!qName.equalsIgnoreCase("q2_10"))
				edbox.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
				edList.put(i, edbox);
				layoutParamForcheck.weight = 1;
				layoutParamForTextBox.weight = 1;

				ln.addView(edbox, 0, layoutParamForTextBox);
				edbox.setVisibility(View.INVISIBLE);
				/*
				 * if (CommonStaticClass.mode
				 * .equalsIgnoreCase(CommonStaticClass.EDITMODE) ||
				 * CommonStaticClass.mode
				 * .equalsIgnoreCase(CommonStaticClass.ADDMODE) ||
				 * CommonStaticClass.mode.equalsIgnoreCase("")) {
				 */
				// checkEdboxHasDataFrmMultipleChoice(edbox, op.qnList.get(i));
				// }

				/*
				 * if (CommonStaticClass.mode
				 * .equalsIgnoreCase(CommonStaticClass.EDITMODE)) {
				 */
				checkEdboxHasDataFrmMultipleChoice(edbox, op.qnList.get(i));
				// }

			}

			ln.setId(i);
			ln.addView(checkButton, 0, layoutParamForcheck);

			checkBoxHolder.addView(ln, 0, lnlParams);

			checkButton
					.setOnCheckedChangeListener(new OnCheckedChangeListener() {

						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							// TODO Auto-generated method stub
							if (isChecked) {

								Log.e("id :", "" + checkButton.getId());
								if (edList.containsKey(ln.getId())) {
									edList.get(ln.getId()).setVisibility(
											View.VISIBLE);
								}
								aaa.set(ln.getId(), checkButton.getId());
							} else {
								aaa.set(ln.getId(), -1);
								if (edList.containsKey(ln.getId())) {
									edList.get(ln.getId()).setVisibility(
											View.INVISIBLE);
								}
							}
						}
					});

			checkIfChckButtonShouldBeCheckedFrmMultipleChoice(checkButton,
					op.qidList.get(i));
		}

		prevButton = (Button) v.findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				userPressedPrevious(ParentActivity.this);
			}

		});
		saveNxtButton = (Button) v.findViewById(R.id.saveNxtButton);

		saveNxtButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				updateTableDataFrmMultipleChoice();

			}

		});
		clButton = (Button) v.findViewById(R.id.clButton);
		clButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				resetViewGroup((ViewGroup) v);
			}

		});
		notesButton = (Button) v.findViewById(R.id.btnNote);
		notesButton.setOnClickListener(new OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				FraNotes();

			}

		});
	}

	protected void uncheckOtherCheckBoxFrmMultipleChoice(ViewGroup group,
			String t) {
		// TODO Auto-generated method stub
		int nrOfChildren = group.getChildCount();
		for (int i = 0; i < nrOfChildren; i++) {
			View view = group.getChildAt(i);
			if (view instanceof CheckBox) {
				if (((CheckBox) view).getText().toString().equalsIgnoreCase(t)) {
					uncheckThisCheckButtonFrmMultipleChoice(view);
				}
			}
			if (view instanceof ViewGroup) {
				uncheckOtherCheckBoxFrmMultipleChoice((ViewGroup) view, t);
			}
		}
	}

	protected void unccheckDontknowCheckBoxFrmMultipleChoice(ViewGroup group,
			String t) {
		// TODO Auto-generated method stub
		int nrOfChildren = group.getChildCount();
		for (int i = 0; i < nrOfChildren; i++) {
			View view = group.getChildAt(i);
			if (view instanceof CheckBox) {
				if (((CheckBox) view).getText().toString().equalsIgnoreCase(t)) {
					uncheckThisCheckButtonFrmMultipleChoice(view);
				}
			}
			if (view instanceof ViewGroup) {
				unccheckDontknowCheckBoxFrmMultipleChoice((ViewGroup) view, t);
			}
		}
	}

	protected void uncheckExceptALLFrmMultipleChoice(ViewGroup group, String t) {
		// TODO Auto-generated method stub
		int nrOfChildren = group.getChildCount();
		for (int i = 0; i < nrOfChildren; i++) {
			View view = group.getChildAt(i);
			if (view instanceof CheckBox) {
				if (((CheckBox) view).getText().toString().equalsIgnoreCase(t)) {
					uncheckThisCheckButtonFrmMultipleChoice(view);
				}
			}
			if (view instanceof ViewGroup) {
				uncheckExceptALLFrmMultipleChoice((ViewGroup) view, t);
			}
		}
	}

	protected void uncheckAllFrmMultipleChoice(ViewGroup group, String t) {
		// TODO Auto-generated method stub
		int nrOfChildren = group.getChildCount();
		for (int i = 0; i < nrOfChildren; i++) {
			View view = group.getChildAt(i);
			if (view instanceof CheckBox) {
				if (!((CheckBox) view).getText().toString().equalsIgnoreCase(t)) {
					uncheckThisCheckButtonFrmMultipleChoice(view);
				}
			}
			if (view instanceof ViewGroup) {
				uncheckAllFrmMultipleChoice((ViewGroup) view, t);
			}
		}
	}

	private void uncheckThisCheckButtonFrmMultipleChoice(View v) {
		((CheckBox) v).setChecked(false);
	}

	private void checkEdboxHasDataFrmMultipleChoice(EditText edbox,
			String inColumn) {
		String sql = "";
		if (!CommonStaticClass.isMember)
			sql = "Select "
					+ inColumn
					+ " from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " where dataid='" + CommonStaticClass.dataId + "'";
		else
			sql = "Select "
					+ inColumn
					+ " from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " where dataid='" + CommonStaticClass.dataId
					+ "' and memberid=" + CommonStaticClass.memberID;
		Cursor c = null;
		try {

			c = dbHelper.getQueryCursor(sql);
			if (c.getCount() > 0) {
				doFill1FrmMultipleChoice(c, edbox, inColumn);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (c != null)
				c.close();
		}
	}

	private boolean doFill1FrmMultipleChoice(Cursor c, EditText edbox,
			String inColumn) {
		// TODO Auto-generated method stub
		boolean dataOk = false;
		if (c.moveToFirst()) {
			do {
				if (c.getColumnIndex(inColumn) != -1) {
					String a = c.getString(c.getColumnIndex(inColumn));
					if (a != null && a.length() > 0) {
						edbox.setText(a);
						dataOk = true;
					}
				}
			} while (c.moveToNext());
		}
		return dataOk;
	}

	private void checkIfChckButtonShouldBeCheckedFrmMultipleChoice(
			CheckBox checkButton, String inColumn) {
		String sql = "";
		if (!CommonStaticClass.isMember)
			sql = "Select "
					+ inColumn
					+ " from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " where dataid='" + CommonStaticClass.dataId + "'";
		else
			sql = "Select "
					+ inColumn
					+ " from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " where dataid='" + CommonStaticClass.dataId
					+ "' and memberid=" + CommonStaticClass.memberID;
		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.getCount() > 0) {
				doFill2FrmMultipleChoice(mCursor1, checkButton, inColumn);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}
	}

	private boolean doFill2FrmMultipleChoice(Cursor mCursor1,
			CheckBox checkButton, String inColumn) {
		// TODO Auto-generated method stub
		boolean dataOk = false;
		if (mCursor1.moveToFirst()) {
			do {
				if (mCursor1.getColumnIndex(inColumn) != -1) {
					int a = mCursor1.getInt(mCursor1.getColumnIndex(inColumn));
					if (a == 1) {
						checkButton.setChecked(true);
						dataOk = true;
					}
				}
			} while (mCursor1.moveToNext());
		}
		return dataOk;
	}

	private void updateTableDataFrmMultipleChoice() {

		int totalCheck = 0;
		if (checkIfSingleOptionIsCheckedFrmMultipleChoice())

		{
			//code by imtiaz khan
			
			if (qName.equalsIgnoreCase("q2_16") &&  aaa.get(2) == 1 && aaa.get(3) == 1)
			{
				CommonStaticClass.showMyAlert(con, "ALERT",
						"Yes and No canot be checked together");
				return;
			}
		    else if ((qName.equalsIgnoreCase("q2_16") || qName.equalsIgnoreCase("q2_12"))
					&& (aaa.get(0) == 1 && (aaa.get(1) == 1 
					|| aaa.get(2) == 1 || aaa.get(3) == 1))) {
				
				CommonStaticClass.showMyAlert(con, "Alert", "If no. 4 is selected other options can not be selected");
				return;
				
			}
			
			

			Iterator it = edList.entrySet().iterator();
			while (it.hasNext()) {
				LinkedHashMap.Entry<Integer, EditText> pairs = (LinkedHashMap.Entry<Integer, EditText>) it
						.next();
				if (pairs.getValue().getVisibility() == View.VISIBLE) {
					if (pairs.getValue().getText().toString().length() > 0) {
						String sq = "";
						if (!CommonStaticClass.isMember)
						{
							sq = "UPDATE "
									+ CommonStaticClass.questionMap.get(
											CommonStaticClass.currentSLNo)
											.getTablename() + " SET "
									+ op.qnList.get(pairs.getKey()) + " = '"
									+ pairs.getValue().getText().toString()
									+ "' where dataid='"
									+ CommonStaticClass.dataId + "'";
							if(qName.equalsIgnoreCase("q3_7") || qName.equalsIgnoreCase("q3_14") )
							{
								try
								{
									totalCheck += Integer.parseInt(pairs.getValue().getText().toString());
								}
								catch(NumberFormatException e)
								{
									
								}
							}
						}
						else
							sq = "UPDATE "
									+ CommonStaticClass.questionMap.get(
											CommonStaticClass.currentSLNo)
											.getTablename() + " SET "
									+ op.qnList.get(pairs.getKey()) + " = '"
									+ pairs.getValue().getText().toString()
									+ "' where dataid='"
									+ CommonStaticClass.dataId
									+ "' and memberid="
									+ CommonStaticClass.memberID;
						dbHelper.executeDMLQuery(sq);
					} /*
					 * else { CommonStaticClass .showMyAlert(con,
					 * "Field is empty",
					 * "Please put correct information in field to proceed");
					 * return; }
					 */
				}
			}
			//code by imtiaz khan
			if(qName.equalsIgnoreCase("q3_7"))
			{
				if( getChoiceValue("q3_6") != totalCheck)
				{
					CommonStaticClass.showMyAlert(con, "Alert", "Check value of question 3.6 and total values of 3.7. They must match");
					return;
				}
					
			}
			else if(qName.equalsIgnoreCase("q3_14"))
			{
				if( getChoiceValue("q3_13") != totalCheck)
				{
					CommonStaticClass.showMyAlert(con, "Alert", "Check value of question 3.13 and total values of 3.14. They must match");
					return;
				}
					
			}

			String sql = "UPDATE "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " SET ";
			for (int i = 0; i < op.codeList.size(); i++) {
				if (i == (op.codeList.size() - 1)) {
					sql += op.qidList.get(i) + " = '" + aaa.get(i) + "'";
					break;
				}
				sql += op.qidList.get(i) + " = '" + aaa.get(i) + "',";
			}
			if (!CommonStaticClass.isMember)
				sql += " where dataid='" + CommonStaticClass.dataId + "'";
			else
				sql += " where dataid='" + CommonStaticClass.dataId
						+ "' and memberid=" + CommonStaticClass.memberID;

			if (dbHelper.executeDMLQuery(sql)) {

			/*	if (!gotoskip()) {
					if (qName.equalsIgnoreCase("q3_4")) {
						if (op.qidList.get(0).equalsIgnoreCase("q3_4_5") && aaa.get(0) == 1)
						{
							nullifyWithInRange(
									CommonStaticClass.questionMap.get(
											CommonStaticClass.currentSLNo).getQvar(),"q41m");
							CommonStaticClass.findOutNextSLNo(
									qName,"q41m");
							CommonStaticClass.nextQuestion(ParentActivity.this);
						}
						else
						{
							nullifyWithInRange(
									CommonStaticClass.questionMap.get(
											CommonStaticClass.currentSLNo).getQvar(),
									CommonStaticClass.questionMap.get(
											CommonStaticClass.currentSLNo).getQnext1());
							CommonStaticClass.findOutNextSLNo(
									qName,
									CommonStaticClass.questionMap.get(
											CommonStaticClass.currentSLNo).getQnext1());
							CommonStaticClass.nextQuestion(ParentActivity.this);
						}
					}
					else
					{
						
					nullifyWithInRange(
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQvar(),
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQnext1());*/
				
				//code by imtiaz khan
				if (qName.equalsIgnoreCase("q2_16")
						&& op.qidList.get(0).equalsIgnoreCase("q2_16_4") && aaa.get(0) == 1) {
					
						nullifyWithInRange(
								CommonStaticClass.questionMap.get(
										CommonStaticClass.currentSLNo).getQvar(),"sec03");
						CommonStaticClass.findOutNextSLNo(
								qName,"sec03");
						CommonStaticClass.nextQuestion(ParentActivity.this);
					
				}
				/*else if (qName.equalsIgnoreCase("q3_7")
						&& op.qidList.get(1).equalsIgnoreCase("q3_7_77") && aaa.get(1) == 1) {
					
						CommonStaticClass.findOutNextSLNo(
								qName,"q3_7Other");
						CommonStaticClass.nextQuestion(ParentActivity.this);
					
				}
				else if (qName.equalsIgnoreCase("q3_14")
						&& op.qidList.get(1).equalsIgnoreCase("q3_14_77") && aaa.get(1) == 1) {
					
						CommonStaticClass.findOutNextSLNo(
								qName,"q3_14Other");
						CommonStaticClass.nextQuestion(ParentActivity.this);
					
				}*/
				else {
					CommonStaticClass.findOutNextSLNo(
							qName,
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQnext1());
					CommonStaticClass.nextQuestion(ParentActivity.this);
				}
					//}
				//}
			}

			else {
				CommonStaticClass
						.showMyAlert(con, "Please check one!!!",
								"You didn't checked any answer please select one to preceed");
			}
		}

	}

	private boolean IfCompletedAllMembersFrmMultipleChoice() {
		boolean IsCompleted = true;
		String sql1 = "Select * from tblMainQues  where dataid='"
				+ CommonStaticClass.dataId + "'";
		String sql2 = "Select * from tblFamilyMember  where dataid='"
				+ CommonStaticClass.dataId
				+ "' and (anysick=1 or visitdoc=1 or hospitalized=1)";
		Cursor mCursor1 = null;
		Cursor mCursor2 = null;
		try {

			mCursor1 = dbHelper.getQueryCursor(sql1);
			mCursor2 = dbHelper.getQueryCursor(sql2);

			int a = mCursor1.getCount();
			int b = mCursor2.getCount();
			String x = Integer.toString(a);
			String y = Integer.toString(b);
			if (x.equalsIgnoreCase(y))
				IsCompleted = true;
			else
				IsCompleted = false;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}

		return IsCompleted;
	}

	private void showUserFinishDialogFrmMultipleChoice() {
		// get prompts.xml view
		LayoutInflater li = LayoutInflater.from(con);
		LinearLayout promptsView = (LinearLayout) li.inflate(R.layout.prompts,
				null);

		promptsView.removeView((EditText) promptsView
				.findViewById(R.id.editTextDialogUserInput));

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(con);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);

		final TextView textInfo = (TextView) promptsView
				.findViewById(R.id.textView1);
		textInfo.setText("Do u want to finish this cycle?");

		// set dialog message
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

						CommonStaticClass.findOutNextSLNo(qName, "END");
						CommonStaticClass.nextQuestion(ParentActivity.this);

					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
	}

	private boolean checkIfSingleOptionIsCheckedFrmMultipleChoice() {
		for (int i = 0; i < aaa.size(); i++) {
			if (!(aaa.get(i) == -1)) {
				return true;
			}
		}

		return false;
	}

	// FrmMultipleCombo part
	private void Load_UIFrmMultipleCombo(final ViewGroup v) {
		// TODO Auto-generated method stub
		loadAllUiViewsFrmMultipleCombo(v);

		// loading all options// need to give field name for every spinner
		if (CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo)
				.getQvar().equalsIgnoreCase("p1_10")) {
			op1st = CommonStaticClass.findOptionsForThisQuestion(dbHelper,
					"p1_10v1");
			op2nd = CommonStaticClass.findOptionsForThisQuestion(dbHelper,
					"p1_10v2");
			op3rd = CommonStaticClass.findOptionsForThisQuestion(dbHelper,
					"p1_10v3");

			op1st.qidList.add(0, "p1_10v1");
			op1st.codeList.add(0, -1);
			op1st.capBngList.add(0, "");
			op1st.capEngList.add(0, "");

			op2nd.qidList.add(0, "p1_10v2");
			op2nd.codeList.add(0, -1);
			op2nd.capBngList.add(0, "");
			op2nd.capEngList.add(0, "");

			op3rd.qidList.add(0, "p1_10v3");
			op3rd.codeList.add(0, -1);
			op3rd.capBngList.add(0, "");
			op3rd.capEngList.add(0, "");

		} else if (CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar()
				.equalsIgnoreCase("p2_8")) {
			op1st = CommonStaticClass.findOptionsForThisQuestion(dbHelper,
					"p2_8v1");
			op2nd = CommonStaticClass.findOptionsForThisQuestion(dbHelper,
					"p2_8v2");
			op3rd = CommonStaticClass.findOptionsForThisQuestion(dbHelper,
					"p2_8v3");

			op1st.qidList.add(0, "p2_8v1");
			op1st.codeList.add(0, -1);
			op1st.capBngList.add(0, "");
			op1st.capEngList.add(0, "");

			op2nd.qidList.add(0, "p2_8v2");
			op2nd.codeList.add(0, -1);
			op2nd.capBngList.add(0, "");
			op2nd.capEngList.add(0, "");

			op3rd.qidList.add(0, "p2_8v3");
			op3rd.codeList.add(0, -1);
			op3rd.capBngList.add(0, "");
			op3rd.capEngList.add(0, "");
		}

		filllAllSpinnerDataFrmMultipleCombo();

		btnPrev = (Button) v.findViewById(R.id.prevButton);
		btnPrev.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				userPressedPrevious(ParentActivity.this);
			}

		});
		btnsaveNxt = (Button) v.findViewById(R.id.saveNxtButton);
		btnsaveNxt.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				updateTableDataFrmMultipleCombo();

			}

		});
		btnClear = (Button) v.findViewById(R.id.clButton);
		btnClear.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				resetViewGroup((ViewGroup) v);
			}

		});
		notesButton = (Button) v.findViewById(R.id.btnNote);
		notesButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				FraNotes();

			}

		});
	}

	private void filllAllSpinnerDataFrmMultipleCombo() {
		// TODO Auto-generated method stub
		if (CommonStaticClass.langBng) {
			adapter1st = new SpinAdapter(this, op1st.capBngList, true);
			adapter2nd = new SpinAdapter(this, op2nd.capBngList, true);
			adapter3rd = new SpinAdapter(this, op3rd.capBngList, true);

		} else {
			adapter1st = new ArrayAdapter(this,
					android.R.layout.simple_spinner_item, op1st.capEngList);
			adapter2nd = new ArrayAdapter(this,
					android.R.layout.simple_spinner_item, op2nd.capEngList);
			adapter3rd = new ArrayAdapter(this,
					android.R.layout.simple_spinner_item, op3rd.capEngList);

		}
		adapter1st
				.setDropDownViewResource(CommonStaticClass.langBng ? R.layout.checkedspintextview
						: android.R.layout.simple_spinner_dropdown_item);
		spinner1st.setAdapter(adapter1st);
		spinner1st
				.setOnItemSelectedListener(new spinItemSelectListenerFrmMultipleCombo());

		adapter2nd
				.setDropDownViewResource(CommonStaticClass.langBng ? R.layout.checkedspintextview
						: android.R.layout.simple_spinner_dropdown_item);
		spinner2nd.setAdapter(adapter2nd);
		spinner2nd
				.setOnItemSelectedListener(new spinItemSelectListenerFrmMultipleCombo());

		adapter3rd
				.setDropDownViewResource(CommonStaticClass.langBng ? R.layout.checkedspintextview
						: android.R.layout.simple_spinner_dropdown_item);
		spinner3rd.setAdapter(adapter3rd);
		spinner3rd
				.setOnItemSelectedListener(new spinItemSelectListenerFrmMultipleCombo());

	}

	class spinItemSelectListenerFrmMultipleCombo implements
			OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View arg1, int pos,
				long arg3) {
			// TODO Auto-generated method stub

			if (parent == spinner1st) {
				if (parent.getItemAtPosition(pos).toString().length() > 0) {
					res1st = op1st.codeList.get(pos) + "";

					// Need to add condition here for all questions applied
					if (CommonStaticClass.questionMap
							.get(CommonStaticClass.currentSLNo).getQvar()
							.equalsIgnoreCase("p1_10")) {
						if (res1st.equalsIgnoreCase("77")) {
							if (!IsFirstTime1)
								promptUserForInputFrmMultipleCombo(spinner1st,
										"p1_10v1other");

						} else
							res1stother = "";
						IsFirstTime1 = false;
					} else if (CommonStaticClass.questionMap
							.get(CommonStaticClass.currentSLNo).getQvar()
							.equalsIgnoreCase("p2_8")) {
						if (res1st.equalsIgnoreCase("77")) {
							if (!IsFirstTime1)
								promptUserForInputFrmMultipleCombo(spinner1st,
										"p2_8v1other");
						} else
							res1stother = "";
						IsFirstTime1 = false;
					}

				}
			} else if (parent == spinner2nd) {
				if (parent.getItemAtPosition(pos).toString().length() > 0) {
					res2nd = op2nd.codeList.get(pos) + "";

					// Need to add condition here for all questions applied
					if (CommonStaticClass.questionMap
							.get(CommonStaticClass.currentSLNo).getQvar()
							.equalsIgnoreCase("p1_10")) {
						if (res2nd.equalsIgnoreCase("77")) {
							if (!IsFirstTime2)
								promptUserForInputFrmMultipleCombo(spinner2nd,
										"p1_10v2other");
						} else
							res2ndother = "";
						IsFirstTime2 = false;
					} else if (CommonStaticClass.questionMap
							.get(CommonStaticClass.currentSLNo).getQvar()
							.equalsIgnoreCase("p2_8")) {
						if (res2nd.equalsIgnoreCase("77")) {
							if (!IsFirstTime2)
								promptUserForInputFrmMultipleCombo(spinner2nd,
										"p2_8v2other");

						} else
							res2ndother = "";
						IsFirstTime2 = false;
					}

				}
			} else if (parent == spinner3rd) {
				if (parent.getItemAtPosition(pos).toString().length() > 0) {
					res3rd = op3rd.codeList.get(pos) + "";

					// Need to add condition here for all questions applied
					if (CommonStaticClass.questionMap
							.get(CommonStaticClass.currentSLNo).getQvar()
							.equalsIgnoreCase("p1_10")) {
						if (res3rd.equalsIgnoreCase("77")) {
							if (!IsFirstTime3)
								promptUserForInputFrmMultipleCombo(spinner3rd,
										"p1_10v3other");

						} else
							res3rdother = "";
						IsFirstTime3 = false;
					} else if (CommonStaticClass.questionMap
							.get(CommonStaticClass.currentSLNo).getQvar()
							.equalsIgnoreCase("p2_8")) {
						if (res3rd.equalsIgnoreCase("77")) {
							if (!IsFirstTime3)
								promptUserForInputFrmMultipleCombo(spinner3rd,
										"p2_8v3other");

						} else
							res3rdother = "";
						IsFirstTime3 = false;
					}

				}
			}
		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}

	}

	private void updateTableDataFrmMultipleCombo() {
		if (!IsValidFrmMultipleCombo())
			return;
		else {
			try {
				Date d = new Date(System.currentTimeMillis());
				d.toLocaleString();
				String sqlUp = "";
				// This section is for consistency check specilly for this
				// project
				if (CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar()
						.equalsIgnoreCase("p1_10")) {
					CheckNUpdateFrmMultipleCombo();
					// Skip Addition
					AddSkipFrmMultipleCombo();
				}

				sqlUp = "Update "
						+ CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getTablename()
						+ " Set " + op1st.qidList.get(0) + "=" + res1st + ","
						+ op2nd.qidList.get(0) + "=" + res2nd + ","
						+ op3rd.qidList.get(0) + "=" + res3rd;

				if (CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar()
						.equalsIgnoreCase("p1_10")) {
					sqlUp = sqlUp + ",p1_10v1other='" + res1stother + "'"
							+ ",p1_10v2other='" + res2ndother + "'"
							+ ",p1_10v3other='" + res3rdother + "'";
				}
				if (CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar()
						.equalsIgnoreCase("p2_8")) {
					sqlUp = sqlUp + ",p2_8v1other='" + res1stother + "'"
							+ ",p2_8v2other='" + res2ndother + "'"
							+ ",p2_8v3other='" + res3rdother + "'";
				}
				// for other questions have to add other conditions

				if (!CommonStaticClass.isMember)
					sqlUp = sqlUp + " Where dataid='"
							+ CommonStaticClass.dataId + "'";
				else
					sqlUp = sqlUp + " Where dataid='"
							+ CommonStaticClass.dataId + "' and memberid='"
							+ CommonStaticClass.memberID + "'";
				if (dbHelper.executeDMLQuery(sqlUp)) {
					// preserveState();
					CommonStaticClass.findOutNextSLNo(
							qName,
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQnext1());
					CommonStaticClass.nextQuestion(ParentActivity.this);
				}
			} catch (Exception e) {
			}
		}
	}

	private void CheckNUpdateFrmMultipleCombo() {
		IsInfomationMismatchingFrmMultipleCombo();

		if (IsMismatch_1_1_8) {

			CommonStaticClass.showMyAlert(con, "Updating",
					"memeber Visited doctor information is updating");
			UpdatePreviousDataFrmMultipleCombo("visitdoc");
			if (CommonStaticClass.qskipList.contains("SecP2"))
				;
			CommonStaticClass.qskipList.remove("SecP2");

		}
		if (IsMismatch_1_1_9) {
			CommonStaticClass.showMyAlert(con, "Updating",
					"Hospitalization information is updating");
			UpdatePreviousDataFrmMultipleCombo("hospitalized");
			if (CommonStaticClass.qskipList.contains("SecP3"))
				;
			CommonStaticClass.qskipList.remove("SecP3");

		}

	}

	private void AddSkipFrmMultipleCombo() {
		IsVisited1st = true;
		IsVisited2nd = true;
		IsVisited3rd = true;

		ShouldSkipfor1st = true;
		ShouldSkipfor2nd = true;
		ShouldSkipfor3rd = true;

		if (res1st.length() > 0
				&& (res1st.equalsIgnoreCase("-1")
						|| res1st.equalsIgnoreCase("2") || res1st
							.equalsIgnoreCase("99"))) {
			IsVisited1st = false;
		}
		if (res2nd.length() > 0
				&& (res2nd.equalsIgnoreCase("-1")
						|| res2nd.equalsIgnoreCase("2") || res2nd
							.equalsIgnoreCase("99"))) {
			IsVisited2nd = false;
		}
		if (res3rd.length() > 0
				&& (res3rd.equalsIgnoreCase("-1")
						|| res3rd.equalsIgnoreCase("2") || res3rd
							.equalsIgnoreCase("99"))) {
			IsVisited3rd = false;
		}

		if (res1st.length() > 0
				&& (res1st.equalsIgnoreCase("2") || res1st
						.equalsIgnoreCase("77"))) {
			ShouldSkipfor1st = false;
		}
		if (res2nd.length() > 0
				&& (res2nd.equalsIgnoreCase("2") || res2nd
						.equalsIgnoreCase("77"))) {
			ShouldSkipfor2nd = false;
		}

		if (res3rd.length() > 0
				&& (res3rd.equalsIgnoreCase("2") || res3rd
						.equalsIgnoreCase("77"))) {
			ShouldSkipfor3rd = false;
		}

		if (CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo)
				.getQvar().equalsIgnoreCase("p1_10")) {
			if (CommonStaticClass.qskipList.contains("p1_11"))
				CommonStaticClass.qskipList.remove("p1_11");
			if (CommonStaticClass.qskipList.contains("p1_12"))
				CommonStaticClass.qskipList.remove("p1_12");

			if (!IsVisited1st && !IsVisited2nd && !IsVisited3rd) {
				CommonStaticClass.qskipList.add("p1_11");
				nullifyWithInRange("p1_10", "p1_12");
			}
			String SQL = "";
			if (!IsVisited1st) {
				SQL = "Update tblMainQues set p1_11av1=-1,p1_11bv1=-1,p1_11cv1=-1,p1_11dv1=-1,p1_11ev1=-1,p1_11fv1=-1,p1_11gv1=-1,p1_11hv1=-1,p1_11iv1=-1,p1_11jv1=-1,p1_11v1other='' Where dataid='"
						+ CommonStaticClass.dataId
						+ "' and memberid="
						+ CommonStaticClass.memberID;
				dbHelper.executeDMLQuery(SQL);

			}
			if (!IsVisited2nd) {
				SQL = "Update tblMainQues set p1_11av2=-1,p1_11bv2=-1,p1_11cv2=-1,p1_11dv2=-1,p1_11ev2=-1,p1_11fv2=-1,p1_11gv2=-1,p1_11hv2=-1,p1_11iv2=-1,p1_11jv2=-1,p1_11v2other='' Where dataid='"
						+ CommonStaticClass.dataId
						+ "' and memberid="
						+ CommonStaticClass.memberID;
				dbHelper.executeDMLQuery(SQL);

			}
			if (!IsVisited3rd) {
				SQL = "Update tblMainQues set p1_11av3=-1,p1_11bv3=-1,p1_11cv3=-1,p1_11dv3=-1,p1_11ev3=-1,p1_11fv3=-1,p1_11gv3=-1,p1_11hv3=-1,p1_11iv3=-1,p1_11jv3=-1,p1_11v3other='' Where dataid='"
						+ CommonStaticClass.dataId
						+ "' and memberid="
						+ CommonStaticClass.memberID;
				dbHelper.executeDMLQuery(SQL);

			}

			if (res1st.equalsIgnoreCase("-1") && res2nd.equalsIgnoreCase("-1")
					&& res3rd.equalsIgnoreCase("-1")) {
				// No skip
			} else if (ShouldSkipfor1st && ShouldSkipfor2nd && ShouldSkipfor3rd) {
				CommonStaticClass.qskipList.add("p1_12");
				nullifyWithInRange("p1_11", "SecP2");
			}
		}

	}

	private void UpdatePreviousDataFrmMultipleCombo(String QVar) {
		try {
			String sql = "";

			if (QVar.equalsIgnoreCase("visitdoc"))
				sql = "Update tblFamilymember set visitdoc=1 Where dataid='"
						+ CommonStaticClass.dataId + "' and memberid='"
						+ CommonStaticClass.memberID + "'";
			else if (QVar.equalsIgnoreCase("hospitalized"))
				sql = "Update tblFamilymember set hospitalized=1 Where dataid='"
						+ CommonStaticClass.dataId
						+ "' and memberid='"
						+ CommonStaticClass.memberID + "'";
			dbHelper.executeDMLQuery(sql);
		} catch (Exception e) {

		}
	}

	private void promptUserForInputFrmMultipleCombo(final Spinner spinner,
			String ColumnName) {
		// get prompts.xml view

		LayoutInflater li = LayoutInflater.from(con);
		View promptsView = li.inflate(R.layout.prompts, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(con);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);

		final EditText userInput = (EditText) promptsView
				.findViewById(R.id.editTextDialogUserInput);

		if (spinner == spinner1st) {
			res1stother = getOtherDataFrmMultipleCombo(ColumnName);
			if (res1stother != null && res1stother.length() > 0) {
				userInput.setText(res1stother);
			}
		} else if (spinner == spinner2nd) {
			res2ndother = getOtherDataFrmMultipleCombo(ColumnName);
			if (res2ndother != null && res2ndother.length() > 0) {
				userInput.setText(res2ndother);
			}
		} else if (spinner == spinner3rd) {
			res3rdother = getOtherDataFrmMultipleCombo(ColumnName);
			if (res3rdother != null && res3rdother.length() > 0) {
				userInput.setText(res3rdother);
			}
		}

		// set dialog message
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// get user input and set it to result
						// edit text
						// insertDataToRelationOther(userInput.getText().toString());
						if (spinner == spinner1st)
							res1stother = userInput.getText().toString();
						else if (spinner == spinner2nd)
							res2ndother = userInput.getText().toString();
						else if (spinner == spinner3rd)
							res3rdother = userInput.getText().toString();

					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
								spinner.setSelection(0);
							}
						});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
	}

	private String getOtherDataFrmMultipleCombo(String column) {
		String sql = "";
		if (!CommonStaticClass.isMember)
			sql = "Select "
					+ column
					+ " from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " where dataid='" + CommonStaticClass.dataId + "'";
		else
			sql = "Select "
					+ column
					+ " from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " where dataid='" + CommonStaticClass.dataId
					+ "' and memberid='" + CommonStaticClass.memberID + "'";

		String data = "";
		Cursor mCursor = null;
		try {
			mCursor = dbHelper.getQueryCursor(sql);
			if (mCursor.getCount() > 0) {
				if (mCursor.moveToFirst()) {
					do {
						data = mCursor
								.getString(mCursor.getColumnIndex(column));
					} while (mCursor.moveToNext());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (mCursor != null)
				mCursor.close();
		}
		return data;
	}

	public void LoadDataFrmMultipleCombo() {
		// TODO Auto-generated method stub
		String sql = "";
		if (!CommonStaticClass.isMember)
			sql = "Select * from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ "  where dataid = '" + CommonStaticClass.dataId + "'";
		else
			sql = "Select * from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ "  where dataid = '" + CommonStaticClass.dataId
					+ "' AND memberid='" + CommonStaticClass.memberID + "'";
		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.getCount() > 0) {
				doFillFrmMultipleCombo(mCursor1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}
	}

	private void doFillFrmMultipleCombo(Cursor mCursor1) {
		// TODO Auto-generated method stub
		if (mCursor1.moveToFirst()) {
			do {
				spinner1st.setSelection(op1st.codeList.indexOf(Integer
						.parseInt(mCursor1.getString(mCursor1
								.getColumnIndex(op1st.qidList.get(0))))));
				spinner2nd.setSelection(op2nd.codeList.indexOf(Integer
						.parseInt(mCursor1.getString(mCursor1
								.getColumnIndex(op2nd.qidList.get(0))))));
				spinner3rd.setSelection(op3rd.codeList.indexOf(Integer
						.parseInt(mCursor1.getString(mCursor1
								.getColumnIndex(op3rd.qidList.get(0))))));

			} while (mCursor1.moveToNext());
		}
	}

	private void resetViewsFrmMultipleCombo() {
		spinner1st.setSelection(0);
		spinner2nd.setSelection(0);
		spinner3rd.setSelection(0);

	}

	private boolean IsValidFrmMultipleCombo() {
		boolean IsValid = false;

		res1st = op1st.codeList.get(spinner1st.getSelectedItemPosition())
				.toString();
		res2nd = op2nd.codeList.get(spinner2nd.getSelectedItemPosition())
				.toString();
		res3rd = op3rd.codeList.get(spinner3rd.getSelectedItemPosition())
				.toString();

		// Have to add question specific condition
		if (CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo)
				.getQvar().equalsIgnoreCase("p1_10")) {
			if (res1st.equalsIgnoreCase("77")
					&& res1stother.equalsIgnoreCase(""))
				res1stother = getOtherDataFrmMultipleCombo("p1_10v1other");
			if (res2nd.equalsIgnoreCase("77")
					&& res2ndother.equalsIgnoreCase(""))
				res2ndother = getOtherDataFrmMultipleCombo("p1_10v2other");
			if (res3rd.equalsIgnoreCase("77")
					&& res3rdother.equalsIgnoreCase(""))
				res3rdother = getOtherDataFrmMultipleCombo("p1_10v3other");
		}
		if (CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo)
				.getQvar().equalsIgnoreCase("p2_8")) {
			if (res1st.equalsIgnoreCase("77")
					&& res1stother.equalsIgnoreCase(""))
				res1stother = getOtherDataFrmMultipleCombo("p2_8v1other");
			if (res2nd.equalsIgnoreCase("77")
					&& res2ndother.equalsIgnoreCase(""))
				res2ndother = getOtherDataFrmMultipleCombo("p2_8v2other");
			if (res3rd.equalsIgnoreCase("77")
					&& res3rdother.equalsIgnoreCase(""))
				res3rdother = getOtherDataFrmMultipleCombo("p2_8v3other");
		}

		if (res1st.length() == 0) {
			CommonStaticClass.showMyAlert(con, "Error",
					"Please select 1st Visited");
			return IsValid;
		}
		if (res2nd.length() == 0) {
			CommonStaticClass.showMyAlert(con, "Error",
					"Please select 2nd Visited");
			return IsValid;
		}
		if (res3rd.length() == 0) {
			CommonStaticClass.showMyAlert(con, "Error",
					"Please select 3rd Visited");
			return IsValid;
		}
		return true;
	}

	private void IsInfomationMismatchingFrmMultipleCombo() {
		String sql = "";
		IsMismatch_1_1_8 = false;
		IsMismatch_1_1_9 = false;

		sql = "Select * from tblFamilyMember  where dataid = '"
				+ CommonStaticClass.dataId + "' AND memberid='"
				+ CommonStaticClass.memberID + "'";
		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.getCount() > 0) {
				if (mCursor1.moveToFirst()) {
					do {
						// String
						// q1_1_7=mCursor1.getString(mCursor1.getColumnIndex("anysick"));
						String q1_1_8 = mCursor1.getString(mCursor1
								.getColumnIndex("visitdoc"));
						String q1_1_9 = mCursor1.getString(mCursor1
								.getColumnIndex("hospitalized"));

						if (!res1st.equalsIgnoreCase("-1")
								&& !res1st.equalsIgnoreCase("2")
								&& !res1st.equalsIgnoreCase("77")
								&& !res1st.equalsIgnoreCase("99")
								&& !q1_1_8.equalsIgnoreCase("1"))
							IsMismatch_1_1_8 = true;
						if (!res2nd.equalsIgnoreCase("-1")
								&& !res2nd.equalsIgnoreCase("2")
								&& !res2nd.equalsIgnoreCase("77")
								&& !res2nd.equalsIgnoreCase("99")
								&& !q1_1_8.equalsIgnoreCase("1"))
							IsMismatch_1_1_8 = true;
						if (!res3rd.equalsIgnoreCase("-1")
								&& !res3rd.equalsIgnoreCase("2")
								&& !res3rd.equalsIgnoreCase("77")
								&& !res3rd.equalsIgnoreCase("99")
								&& !q1_1_8.equalsIgnoreCase("1"))
							IsMismatch_1_1_8 = true;

						if ((res1st.equalsIgnoreCase("1") || res1st
								.equalsIgnoreCase("3"))
								&& !q1_1_9.equalsIgnoreCase("1"))
							IsMismatch_1_1_9 = true;
						if ((res2nd.equalsIgnoreCase("1") || res2nd
								.equalsIgnoreCase("3"))
								&& !q1_1_9.equalsIgnoreCase("1"))
							IsMismatch_1_1_9 = true;
						if ((res3rd.equalsIgnoreCase("1") || res3rd
								.equalsIgnoreCase("3"))
								&& !q1_1_9.equalsIgnoreCase("1"))
							IsMismatch_1_1_9 = true;

					} while (mCursor1.moveToNext());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}

	}

	private void loadAllUiViewsFrmMultipleCombo(ViewGroup v) {
		// TODO Auto-generated method stub

		qName = CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar();

		qqq = (TextView) v.findViewById(R.id.qqq);

		lbl1st = (TextView) v.findViewById(R.id.lbl1st);
		lbl2nd = (TextView) v.findViewById(R.id.lbl2nd);
		lbl3rd = (TextView) v.findViewById(R.id.lbl3rd);

		spinner1st = (Spinner) v.findViewById(R.id.spinner1st);
		spinner2nd = (Spinner) v.findViewById(R.id.spinner2nd);
		spinner3rd = (Spinner) v.findViewById(R.id.spinner3rd);

		spinner1st.setFocusableInTouchMode(true);
		spinner1st.requestFocus();

		if (CommonStaticClass.langBng) {

			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0) {
				qqq.setTypeface(font);
			}
			;
			lbl1st.setTypeface(font);
			lbl1st.setText(con.getResources().getString(R.string.lbl1stVBN));
			lbl2nd.setTypeface(font);
			lbl2nd.setText(con.getResources().getString(R.string.lbl2VBN));
			lbl3rd.setTypeface(font);
			lbl3rd.setText(con.getResources().getString(R.string.lbl3VBN));

			qqq.setText(CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0 ? CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng()
					: CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQdesceng());
		} else {
			lbl1st.setTypeface(null);

			lbl2nd.setTypeface(null);

			lbl3rd.setTypeface(null);

			lbl1st.setText("1st Visited care provider");

			lbl2nd.setText("2nd Visited care provider");

			lbl3rd.setText("3rd Visited care provider");

			qqq.setTypeface(null);
			qqq.setText(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQdesceng());
		}

	}

	// FrmNotes part
	private void loadGuiFrmNotes(ViewGroup v) {
		// TODO Auto-generated method stub

		// checkDbHasPreviousDataForThisHouseHold();

		infoText = (EditText) v.findViewById(R.id.infoText);
		String sql = "";
		if (!CommonStaticClass.isMember)
			sql = "Select notes from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " where dataid='" + CommonStaticClass.dataId + "'";
		else
			sql = "Select notes from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " where dataid='" + CommonStaticClass.dataId
					+ "' and memberid=" + CommonStaticClass.memberID;
		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.getCount() > 0) {
				doFillFrmNotes(mCursor1, infoText);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}

		btnCancel = (Button) v.findViewById(R.id.btnCancel);
		btnCancel.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				// System.exit(0);
				ParentActivity.this.formFlipper
						.setDisplayedChild(lastIndexBeforeFraNotes);// gotoForm(CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo).getFormname());
			}

		});
		btnSave = (Button) v.findViewById(R.id.btnSave);
		btnSave.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				updateTableDataFrmNotes();
				ParentActivity.this.formFlipper
						.setDisplayedChild(lastIndexBeforeFraNotes);
				// System.exit(0);
				// ParentActivity.this.gotoForm(CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo).getFormname());
			}

		});

	}

	private void doFillFrmNotes(Cursor mCursor1, EditText infoText) {
		// TODO Auto-generated method stub
		if (mCursor1.moveToFirst()) {
			do {
				String column = "notes";

				if (mCursor1.getColumnIndex(column) != -1) {
					String a = mCursor1.getString(mCursor1
							.getColumnIndex(column)) + "";
					infoText.setText((a.length() > 0
							&& (!a.equalsIgnoreCase("-1")) && (!a
							.equalsIgnoreCase("null"))) ? a : "");
				}
			} while (mCursor1.moveToNext());
		}
	}

	private void updateTableDataFrmNotes() {
		// TODO Auto-generated method stub
		String qAns = infoText.getText().toString();
		if (qAns.length() > 0) {

			String sql = "";
			if (!CommonStaticClass.isMember)
				sql = "UPDATE "
						+ CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getTablename()
						+ " set notes='" + qAns + "' where dataid='"
						+ CommonStaticClass.dataId + "'";
			else
				sql = "UPDATE "
						+ CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getTablename()
						+ " set notes='" + qAns + "' where dataid='"
						+ CommonStaticClass.dataId + "' and memeberid="
						+ CommonStaticClass.memberID;
			dbHelper.executeDMLQuery(sql);

		}
	}

	private void doFill(Cursor mCursor1, EditText infoText2) {
		// TODO Auto-generated method stub
		if (mCursor1.moveToFirst()) {
			do {
				if (mCursor1.getColumnIndex(qName.toLowerCase()) != -1) {
					String a = mCursor1.getString(mCursor1.getColumnIndex(qName
							.toLowerCase())) + "";
					if (a.length() > 0 && (!a.equalsIgnoreCase("-1"))
							&& (!a.equalsIgnoreCase("null"))) {
						if (a.equalsIgnoreCase("555")) {
							((RadioButton) findViewById(R.id.radio2))
									.setChecked(true);

						} else if (a.equalsIgnoreCase("777")) {
							((RadioButton) findViewById(R.id.radio1))
									.setChecked(true);
						} else {
							infoText.setText(a);

						}
					}

				}
			} while (mCursor1.moveToNext());
		}
	}

	private void loadGuiFrmNumericWithUnknowDecline(final ViewGroup v) {

		qqq = (TextView) v.findViewById(R.id.qqq);

		if (CommonStaticClass.langBng) {
			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0) {
				Typeface font = Typeface.createFromAsset(getAssets(),
						"Siyam Rupali ANSI.ttf");
				qqq.setTypeface(font);
			}
			;
			qqq.setText(CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0 ? CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng()
					: CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQdesceng());
		} else {
			qqq.setTypeface(null);
			qqq.setTypeface(null);
			qqq.setText(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQdesceng());
		}

		// TODO Auto-generated method stub
		qName = CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar();

		// qqq = (TextView) findViewById(R.id.qqq);

		// checkDbHasPreviousDataForThisHouseHold();

		((RadioGroup) findViewById(R.id.radioGroup1)).clearCheck();

		if (CommonStaticClass.langBng) {
			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0) {
				Typeface font = Typeface.createFromAsset(getAssets(),
						"Siyam Rupali ANSI.ttf");
				qqq.setTypeface(font);

				((RadioButton) findViewById(R.id.radio1)).setTypeface(font);
				((RadioButton) findViewById(R.id.radio2)).setTypeface(font);
				((RadioButton) findViewById(R.id.radio1))
						.setText("	AbÂ¨vbÂ¨ (wbw`Â©Ã³ Kâ€¡i wjLyb)");
				((RadioButton) findViewById(R.id.radio2))
						.setText("gâ€¡b Kiâ€¡Z cvâ€¡i bv");
			}
		}

		infoText = (EditText) findViewById(R.id.frmnumericwithunknowndecline)
				.findViewById(R.id.infoText);

		String sql = "";

		sql = "Select "
				+ qName
				+ " from "
				+ CommonStaticClass.questionMap.get(
						CommonStaticClass.currentSLNo).getTablename()
				+ " where dataid='" + CommonStaticClass.dataId + "'";
		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.getCount() > 0) {
				doFill(mCursor1, infoText);
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}

		prevButton = (Button) v.findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				userPressedPrevious(ParentActivity.this);
			}

		});
		saveNxtButton = (Button) v.findViewById(R.id.saveNxtButton);

		saveNxtButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				updateTableDataFrmNumericwithunknowDecline();
			}

		});
		clButton = (Button) v.findViewById(R.id.clButton);
		clButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				resetViewGroup((ViewGroup) v);
			}

		});
		notesButton = (Button) v.findViewById(R.id.btnNote);
		notesButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				FraNotes();

			}

		});

		((RadioGroup) findViewById(R.id.radioGroup1))
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup group, int checkedId) {
						// ((EditText) findViewById(R.id.infoText)).setText("");
						infoText.setText("");
						InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
						imm.hideSoftInputFromWindow(infoText.getWindowToken(),
								0);

					}
				});

		infoText.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				((RadioButton) findViewById(R.id.radio1)).setChecked(false);
				((RadioButton) findViewById(R.id.radio2)).setChecked(false);

				if (((RadioGroup) findViewById(R.id.radioGroup1))
						.getCheckedRadioButtonId() != -1) {
					((RadioGroup) findViewById(R.id.radioGroup1)).clearCheck();
				}
				getWindow()
						.setSoftInputMode(
								WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
				return false;
			}
		});

		if (qName.equalsIgnoreCase("q5")) {
			((RadioButton) findViewById(R.id.radio2)).setVisibility(View.GONE);
		} else {
			((RadioButton) findViewById(R.id.radio2))
					.setVisibility(View.VISIBLE);
		}
	}

	private void updateTableDataFrmNumericwithunknowDecline() {
		// TODO Auto-generated method stub
		String qAns = ((EditText) findViewById(
				R.id.frmnumericwithunknowndecline).findViewById(R.id.infoText))
				.getText().toString();// infoText.getText().toString();
		String currentQuestion = qName;
		/*
		 * if (!IsValid()) { CommonStaticClass.showMyAlert(con, "Not Correct",
		 * "Cluster ID is mismatching"); return; }
		 */

		int id = ((RadioGroup) findViewById(R.id.radioGroup1))
				.getCheckedRadioButtonId();
		if (id != -1) {
			if (id == R.id.radio1) {
				qAns = "777";

			}
			if (id == R.id.radio2) {
				qAns = "555";

			}
		}

		if (qAns.length() > 0) {

			if (qName.equalsIgnoreCase("q19") && Integer.parseInt(qAns) > 999) {
				CommonStaticClass.showMyAlert(con, "Message", "Invalid");
				return;
			}

			if (qName.equalsIgnoreCase("q29")) {
				if (Integer.parseInt(qAns) > 0) {
					if (Integer.parseInt(qAns) < 14) {

					} else {
						if (((EditText) findViewById(R.id.infoText)).getText()
								.toString().length() > 0) {
							CommonStaticClass.showMyAlert(con, "Message",
									"Invalid Days(Valid value is 1 To 13)");
							return;
						}

					}
				} else {
					if (((EditText) findViewById(R.id.infoText)).getText()
							.toString().length() > 0) {
						CommonStaticClass.showMyAlert(con, "Message",
								"Invalid Days(Valid value is 1 To 13)");
						return;
					}
				}
			}

			// Validation & skip definition
			String sql = "";

			sql = "UPDATE "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " SET " + currentQuestion + "='" + qAns
					+ "' where dataid='" + CommonStaticClass.dataId + "'";

			if (dbHelper.executeDMLQuery(sql)) {

				CommonStaticClass.findOutNextSLNo(
						CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQvar(),
						CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQnext1());
				resetViewGroup((ViewGroup) (findViewById(R.id.linearLayout1)));

				if (qName.equalsIgnoreCase("q3_28")) {
					if (qAns.equalsIgnoreCase("777")) {
						CommonStaticClass.findOutNextSLNo(
								CommonStaticClass.questionMap.get(
										CommonStaticClass.currentSLNo)
										.getQvar(), "q3_28_other");
					}

				} else if (qName.equalsIgnoreCase("q3_29")) {
					if (qAns.equalsIgnoreCase("777")) {
						CommonStaticClass.findOutNextSLNo(
								CommonStaticClass.questionMap.get(
										CommonStaticClass.currentSLNo)
										.getQvar(), "q3_29_other");
					}
				}

				CommonStaticClass.nextQuestion(ParentActivity.this);

			}

		} else {
			CommonStaticClass.showMyAlert(con, "Not Correct",
					"Please put correct information in the field");
		}
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

	public Date getDate() {

		Date d = null;

		String sql = String.format(
				"SELECT q7 FROM tblFormB WHERE dataid='%s' ",
				CommonStaticClass.dataId);

		Cursor mCursor = null;
		try {
			mCursor = dbHelper.getQueryCursor(sql);
			if (mCursor.moveToFirst()) {
				do {
					String sDate = mCursor.getString(mCursor
							.getColumnIndex("q7"));

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

	// FrmNumeric part

	private void loadGuiFrmNumeric(final ViewGroup v) {
		// TODO Auto-generated method stub

		qName = CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar();

		qqq = (TextView) v.findViewById(R.id.qqq);

		if (CommonStaticClass.langBng) {
			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0) {
				Typeface font = Typeface.createFromAsset(getAssets(),
						"Siyam Rupali ANSI.ttf");
				qqq.setTypeface(font);
			}
			;
			qqq.setText(CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0 ? CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng()
					: CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQdesceng());
		} else {
			qqq.setTypeface(null);
			qqq.setText(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQdesceng());
		}

		infoText = (EditText) v.findViewById(R.id.infoText);

		// infoText.setText("");
		infoText.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {

				if (qName.equalsIgnoreCase("q2_15")) {
					String lineNumber = s.toString();
					if (lineNumber.length() > 5) {
						CommonStaticClass.showMyAlert(con, "Message",
								"Number should be in 5 digit");
						if (lineNumber.length() > 5) {
							infoText.setText(lineNumber.substring(0,
									lineNumber.length() - 1));
						}
						// infoText.setText("");
						return;
					}
				}

				/*
				 * if (qName.equalsIgnoreCase("c610a")) { String lineNumber =
				 * s.toString(); if (lineNumber.length() > 2) {
				 * 
				 * CommonStaticClass.showMyAlert(con, "Message",
				 * "Number should be in two digit"); return;
				 * 
				 * } }
				 */

			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}
		});

		String sql = "";
		if (!CommonStaticClass.isMember)
			sql = "Select "
					+ qName
					+ " from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " where dataid='" + CommonStaticClass.dataId + "'";
		else
			sql = "Select "
					+ qName
					+ " from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " where dataid='" + CommonStaticClass.dataId
					+ "' and memberid=" + CommonStaticClass.memberID;
		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.getCount() > 0) {
				doFillFrmNumeric(mCursor1, infoText);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}

		prevButton = (Button) v.findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				userPressedPrevious(ParentActivity.this);
			}

		});
		saveNxtButton = (Button) v.findViewById(R.id.saveNxtButton);

		saveNxtButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				updateTableDataFrmNumeric();
			}

		});
		clButton = (Button) v.findViewById(R.id.clButton);
		clButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				resetViewGroup((ViewGroup) v);
			}

		});
		notesButton = (Button) v.findViewById(R.id.btnNote);
		notesButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				FraNotes();

			}

		});

	}

	private void doFillFrmNumeric(Cursor mCursor1, EditText infoText2) {
		// TODO Auto-generated method stub
		/*
		 * if (mCursor1.moveToFirst()) { do { if
		 * (mCursor1.getColumnIndex(qName.toLowerCase()) != -1) { String a =
		 * mCursor1.getString(mCursor1.getColumnIndex(qName .toLowerCase())) +
		 * ""; infoText2.setText((a.length() > 0 && (!a.equalsIgnoreCase("-1"))
		 * && (!a .equalsIgnoreCase("null"))) ? a : ""); } } while
		 * (mCursor1.moveToNext()); }
		 */
		infoText.setText("");
		if (mCursor1.moveToFirst()) {
			do {
				if (mCursor1.getColumnIndex(qName) != -1) {
					String a = mCursor1.getString(mCursor1
							.getColumnIndex(qName)) + "";
					// infoText.setText((a.length() > 0 &&
					// (!a.equalsIgnoreCase("-1")) &&
					// (!a.equalsIgnoreCase("null"))) ? a : "");
					infoText.setText((a.length() > 0
							&& (!a.equalsIgnoreCase("-1")) && (!a
							.equalsIgnoreCase("null"))) ? a : "");

					if (qName.equalsIgnoreCase("q8")) {
						if ((a.length() > 0 && (!a.equalsIgnoreCase("-1")) && (!a
								.equalsIgnoreCase("null")))) {
							infoText.setText(a);
						} else {
							Calendar dobCalender = Calendar.getInstance();

							Date d = getDate();
							dobCalender.set(d.getYear(), d.getMonth(),
									d.getDate());
							infoText.setText(String.valueOf(daysBetween(dsDate,
									dobCalender)));

						}

					}

				}
			} while (mCursor1.moveToNext());
		}
	}

	private void doFillFrmMultiple(Cursor mCursor1, ViewGroup v) {

		if (mCursor1.moveToFirst()) {
			do {

				chk1_1 = mCursor1
						.getString(mCursor1.getColumnIndex("c609_1_1")) + "";
				((CheckBox) v.findViewById(R.id.chk1_1))
						.setChecked(chk1_1 == "1" ? true : false);

				chk2_1 = mCursor1
						.getString(mCursor1.getColumnIndex("c609_2_1")) + "";
				((CheckBox) v.findViewById(R.id.chk2_1))
						.setChecked(chk2_1 == "1" ? true : false);

				chk2_2 = mCursor1
						.getString(mCursor1.getColumnIndex("c609_2_2")) + "";
				((CheckBox) findViewById(R.id.chk2_2))
						.setChecked(chk2_2 == "1" ? true : false);

				chk2_3 = mCursor1
						.getString(mCursor1.getColumnIndex("c609_2_3")) + "";
				((CheckBox) findViewById(R.id.chk2_3))
						.setChecked(chk2_3 == "1" ? true : false);

				chk2_4 = mCursor1
						.getString(mCursor1.getColumnIndex("c609_2_4")) + "";
				((CheckBox) findViewById(R.id.chk2_4))
						.setChecked(chk2_4 == "1" ? true : false);

				chk2_5 = mCursor1
						.getString(mCursor1.getColumnIndex("c609_2_5")) + "";
				((CheckBox) findViewById(R.id.chk2_5))
						.setChecked(chk2_5 == "1" ? true : false);

				et2_5_other = mCursor1.getString(mCursor1
						.getColumnIndex("c609_2_5_other")) + "";
				((EditText) findViewById(R.id.et2_5)).setText(et2_5_other);

				chk3_1 = mCursor1
						.getString(mCursor1.getColumnIndex("c609_3_1")) + "";
				((CheckBox) findViewById(R.id.chk3_1))
						.setChecked(chk3_1 == "1" ? true : false);

				chk3_2 = mCursor1
						.getString(mCursor1.getColumnIndex("c609_3_2")) + "";
				((CheckBox) findViewById(R.id.chk3_2))
						.setChecked(chk3_2 == "1" ? true : false);

				chk3_3 = mCursor1
						.getString(mCursor1.getColumnIndex("c609_3_3")) + "";
				((CheckBox) findViewById(R.id.chk3_3))
						.setChecked(chk3_3 == "1" ? true : false);

				et3_3_other = mCursor1.getString(mCursor1
						.getColumnIndex("chk3_3_other")) + "";
				((EditText) findViewById(R.id.et3_3)).setText(et3_3_other);

				chk4_1 = mCursor1
						.getString(mCursor1.getColumnIndex("c609_4_1")) + "";
				((CheckBox) findViewById(R.id.chk4_1))
						.setChecked(chk4_1 == "1" ? true : false);

				chk4_2 = mCursor1
						.getString(mCursor1.getColumnIndex("c609_4_2")) + "";
				((CheckBox) findViewById(R.id.chk4_2))
						.setChecked(chk4_2 == "1" ? true : false);

				chk5_1 = mCursor1
						.getString(mCursor1.getColumnIndex("c609_5_1")) + "";
				((CheckBox) findViewById(R.id.chk5_1))
						.setChecked(chk5_1 == "1" ? true : false);

				chk5_2 = mCursor1
						.getString(mCursor1.getColumnIndex("c609_5_2")) + "";
				((CheckBox) findViewById(R.id.chk5_2))
						.setChecked(chk5_2 == "1" ? true : false);

				chk5_3 = mCursor1
						.getString(mCursor1.getColumnIndex("c609_5_3")) + "";
				((CheckBox) findViewById(R.id.chk5_3))
						.setChecked(chk5_3 == "1" ? true : false);

				chk5_4 = mCursor1
						.getString(mCursor1.getColumnIndex("c609_5_4")) + "";
				((CheckBox) findViewById(R.id.chk5_4))
						.setChecked(chk5_4 == "1" ? true : false);

				chk5_5 = mCursor1
						.getString(mCursor1.getColumnIndex("c609_5_5")) + "";
				((CheckBox) findViewById(R.id.chk5_5))
						.setChecked(chk5_5 == "1" ? true : false);

				et5_5_other = mCursor1.getString(mCursor1
						.getColumnIndex("et5_5_other")) + "";
				((EditText) findViewById(R.id.et5_5)).setText(et5_5_other);
				chk6_1 = mCursor1
						.getString(mCursor1.getColumnIndex("c609_6_1")) + "";
				((CheckBox) findViewById(R.id.chk6_1))
						.setChecked(chk6_1 == "1" ? true : false);
				chk6_2 = mCursor1
						.getString(mCursor1.getColumnIndex("c609_6_2")) + "";
				((CheckBox) findViewById(R.id.chk6_2))
						.setChecked(chk6_2 == "1" ? true : false);
				chk7_1 = mCursor1
						.getString(mCursor1.getColumnIndex("c609_7_1")) + "";
				((CheckBox) findViewById(R.id.chk7_1))
						.setChecked(chk7_1 == "1" ? true : false);
				chk7_2 = mCursor1
						.getString(mCursor1.getColumnIndex("c609_7_2")) + "";
				((CheckBox) findViewById(R.id.chk7_2))
						.setChecked(chk7_2 == "1" ? true : false);
				chk7_3 = mCursor1
						.getString(mCursor1.getColumnIndex("c609_7_3")) + "";
				((CheckBox) findViewById(R.id.chk7_3))
						.setChecked(chk7_3 == "1" ? true : false);
				chk7_4 = mCursor1
						.getString(mCursor1.getColumnIndex("c609_7_4")) + "";
				((CheckBox) findViewById(R.id.chk7_4))
						.setChecked(chk7_4 == "1" ? true : false);
				chk7_5 = mCursor1
						.getString(mCursor1.getColumnIndex("c609_7_5")) + "";
				((CheckBox) findViewById(R.id.chk7_5))
						.setChecked(chk7_5 == "1" ? true : false);

				chk7_7 = mCursor1
						.getString(mCursor1.getColumnIndex("c609_7_7")) + "";
				((CheckBox) findViewById(R.id.chk7_7))
						.setChecked(chk7_7 == "1" ? true : false);

				chk7_6 = mCursor1
						.getString(mCursor1.getColumnIndex("c609_7_6")) + "";
				((CheckBox) findViewById(R.id.chk7_6))
						.setChecked(chk7_6 == "1" ? true : false);
				et7_6_other = mCursor1.getString(mCursor1
						.getColumnIndex("c609_7_6_other")) + "";
				((EditText) findViewById(R.id.et7_6)).setText(et7_6_other);
				chk8_1 = mCursor1
						.getString(mCursor1.getColumnIndex("c609_8_1")) + "";
				((CheckBox) findViewById(R.id.chk8_1))
						.setChecked(chk8_1 == "1" ? true : false);
				chk8_2 = mCursor1
						.getString(mCursor1.getColumnIndex("c609_8_2")) + "";
				((CheckBox) findViewById(R.id.chk8_2))
						.setChecked(chk8_2 == "1" ? true : false);
				chk8_3 = mCursor1
						.getString(mCursor1.getColumnIndex("c609_8_3")) + "";
				((CheckBox) findViewById(R.id.chk8_3))
						.setChecked(chk8_3 == "1" ? true : false);
				chk8_4 = mCursor1
						.getString(mCursor1.getColumnIndex("c609_8_4")) + "";
				((CheckBox) findViewById(R.id.chk8_4))
						.setChecked(chk8_4 == "1" ? true : false);
				chk8_5 = mCursor1
						.getString(mCursor1.getColumnIndex("c609_8_5")) + "";
				((CheckBox) findViewById(R.id.chk8_5))
						.setChecked(chk8_5 == "1" ? true : false);
				chk8_6 = mCursor1
						.getString(mCursor1.getColumnIndex("c609_8_6")) + "";
				((CheckBox) findViewById(R.id.chk8_6))
						.setChecked(chk8_6 == "1" ? true : false);
				et8_6_other = mCursor1.getString(mCursor1
						.getColumnIndex("c609_8_6_other")) + "";
				((EditText) findViewById(R.id.et8_6)).setText(et8_6_other);
				chk9_1 = mCursor1
						.getString(mCursor1.getColumnIndex("c609_9_1")) + "";
				((CheckBox) findViewById(R.id.chk9_1))
						.setChecked(chk9_1 == "1" ? true : false);
				chk9_2 = mCursor1
						.getString(mCursor1.getColumnIndex("c609_9_2")) + "";
				((CheckBox) findViewById(R.id.chk9_2))
						.setChecked(chk9_2 == "1" ? true : false);
				chk9_3 = mCursor1
						.getString(mCursor1.getColumnIndex("c609_9_3")) + "";
				((CheckBox) findViewById(R.id.chk9_3))
						.setChecked(chk9_3 == "1" ? true : false);
				chk10_1 = mCursor1.getString(mCursor1
						.getColumnIndex("c609_10_1")) + "";
				((CheckBox) findViewById(R.id.chk10_1))
						.setChecked(chk10_1 == "1" ? true : false);
				chk11_1 = mCursor1.getString(mCursor1
						.getColumnIndex("c609_11_1")) + "";
				((CheckBox) findViewById(R.id.chk11_1))
						.setChecked(chk11_1 == "1" ? true : false);
				chk12_1 = mCursor1.getString(mCursor1
						.getColumnIndex("c609_12_1")) + "";
				((CheckBox) findViewById(R.id.chk12_1))
						.setChecked(chk12_1 == "1" ? true : false);
				chk12_2 = mCursor1.getString(mCursor1
						.getColumnIndex("c609_12_2")) + "";
				((CheckBox) findViewById(R.id.chk12_2))
						.setChecked(chk12_2 == "1" ? true : false);
				chk12_3 = mCursor1.getString(mCursor1
						.getColumnIndex("c609_12_3")) + "";
				((CheckBox) findViewById(R.id.chk12_3))
						.setChecked(chk12_3 == "1" ? true : false);
				et12_3_other = mCursor1.getString(mCursor1
						.getColumnIndex("c609_12_3_other")) + "";
				((EditText) findViewById(R.id.et12_3)).setText(et12_3_other);
				chk13_1 = mCursor1.getString(mCursor1
						.getColumnIndex("c609_13_1")) + "";
				((CheckBox) findViewById(R.id.chk13_1))
						.setChecked(chk13_1 == "1" ? true : false);
				chk13_2 = mCursor1.getString(mCursor1
						.getColumnIndex("c609_13_2")) + "";
				((CheckBox) findViewById(R.id.chk13_2))
						.setChecked(chk13_2 == "1" ? true : false);
				chk13_3 = mCursor1.getString(mCursor1
						.getColumnIndex("c609_13_3")) + "";
				((CheckBox) findViewById(R.id.chk13_3))
						.setChecked(chk13_3 == "1" ? true : false);
				chk13_4 = mCursor1.getString(mCursor1
						.getColumnIndex("c609_13_4")) + "";
				((CheckBox) findViewById(R.id.chk13_4))
						.setChecked(chk13_4 == "1" ? true : false);
				chk13_5 = mCursor1.getString(mCursor1
						.getColumnIndex("c609_13_5")) + "";
				((CheckBox) findViewById(R.id.chk13_5))
						.setChecked(chk13_5 == "1" ? true : false);
				chk13_6 = mCursor1.getString(mCursor1
						.getColumnIndex("c609_13_6")) + "";
				((CheckBox) findViewById(R.id.chk13_6))
						.setChecked(chk13_6 == "1" ? true : false);
				chk13_7 = mCursor1.getString(mCursor1
						.getColumnIndex("c609_13_7")) + "";
				((CheckBox) findViewById(R.id.chk13_7))
						.setChecked(chk13_7 == "1" ? true : false);
				et13_7_other = mCursor1.getString(mCursor1
						.getColumnIndex("c609_13_7_other")) + "";
				((EditText) findViewById(R.id.et13_7)).setText(et13_7_other);
				chk14_1 = mCursor1.getString(mCursor1
						.getColumnIndex("c609_14_1")) + "";
				((CheckBox) findViewById(R.id.chk14_1))
						.setChecked(chk14_1 == "1" ? true : false);
				chk14_2 = mCursor1.getString(mCursor1
						.getColumnIndex("c609_14_2")) + "";
				((CheckBox) findViewById(R.id.chk14_2))
						.setChecked(chk14_2 == "1" ? true : false);
				chk14_3 = mCursor1.getString(mCursor1
						.getColumnIndex("c609_14_3")) + "";
				((CheckBox) findViewById(R.id.chk14_3))
						.setChecked(chk14_3 == "1" ? true : false);
				et14_3_other = mCursor1.getString(mCursor1
						.getColumnIndex("c609_14_3_other")) + "";
				((EditText) findViewById(R.id.et14_3)).setText(et14_3_other);
				chk15_1 = mCursor1.getString(mCursor1
						.getColumnIndex("c609_15_1")) + "";
				((CheckBox) findViewById(R.id.chk15_1))
						.setChecked(chk15_1 == "1" ? true : false);
				chk15_2 = mCursor1.getString(mCursor1
						.getColumnIndex("c609_15_2")) + "";
				((CheckBox) findViewById(R.id.chk15_2))
						.setChecked(chk15_2 == "1" ? true : false);
				chk15_3 = mCursor1.getString(mCursor1
						.getColumnIndex("c609_15_3")) + "";
				((CheckBox) findViewById(R.id.chk15_3))
						.setChecked(chk15_3 == "1" ? true : false);
				chk16_1 = mCursor1.getString(mCursor1
						.getColumnIndex("c609_16_1")) + "";
				((CheckBox) findViewById(R.id.chk16_1))
						.setChecked(chk16_1 == "1" ? true : false);
				chk16_2 = mCursor1.getString(mCursor1
						.getColumnIndex("c609_16_2")) + "";
				((CheckBox) findViewById(R.id.chk16_2))
						.setChecked(chk16_2 == "1" ? true : false);
				chk16_3 = mCursor1.getString(mCursor1
						.getColumnIndex("c609_16_3")) + "";
				((CheckBox) findViewById(R.id.chk16_3))
						.setChecked(chk16_3 == "1" ? true : false);
				chk16_4 = mCursor1.getString(mCursor1
						.getColumnIndex("c609_16_4")) + "";
				((CheckBox) findViewById(R.id.chk16_4))
						.setChecked(chk16_4 == "1" ? true : false);
				chk17_1 = mCursor1.getString(mCursor1
						.getColumnIndex("c609_17_1")) + "";
				((CheckBox) findViewById(R.id.chk17_1))
						.setChecked(chk17_1 == "1" ? true : false);
				chk17_2 = mCursor1.getString(mCursor1
						.getColumnIndex("c609_17_2")) + "";
				((CheckBox) findViewById(R.id.chk17_2))
						.setChecked(chk17_2 == "1" ? true : false);
				chk17_3 = mCursor1.getString(mCursor1
						.getColumnIndex("c609_17_3")) + "";
				((CheckBox) findViewById(R.id.chk17_3))
						.setChecked(chk17_3 == "1" ? true : false);
				chk18_1 = mCursor1.getString(mCursor1
						.getColumnIndex("c609_18_1")) + "";
				((CheckBox) findViewById(R.id.chk18_1))
						.setChecked(chk18_1 == "1" ? true : false);
				chk18_2 = mCursor1.getString(mCursor1
						.getColumnIndex("c609_18_2")) + "";
				((CheckBox) findViewById(R.id.chk18_2))
						.setChecked(chk18_2 == "1" ? true : false);
				et19_1 = mCursor1.getString(mCursor1
						.getColumnIndex("c609_19_1")) + "";

				((EditText) findViewById(R.id.et19_1))
						.setText((et19_1.length() > 0
								&& (!et19_1.equalsIgnoreCase("-1")) && (!et19_1
								.equalsIgnoreCase("null"))) ? et19_1 : "");

			} while (mCursor1.moveToNext());
		}
	}

	private float dataFromFrmNumeric(String sql, String columnName) {
		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.moveToFirst()) {
				do {
					String column = columnName;

					if (mCursor1.getColumnIndex(column) != -1) {
						String val = mCursor1.getString(mCursor1
								.getColumnIndex(column));
						Log.e("val", val + "");
						if (val.length() > 0 && !val.equalsIgnoreCase("0")
								&& !val.equalsIgnoreCase("-1")) {
							float a = Float.parseFloat(val);
							return a;
						}
					}
				} while (mCursor1.moveToNext());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}
		return 0;
	}

	private void updateTableDataFrmNumeric() {
		// TODO Auto-generated method stub

		String qAns = infoText.getText().toString();
		String currentQuestion = qName;
		if (!IsValidFrmNumeric()) {
			CommonStaticClass.showMyAlert(con, "Not Correct",
					"Cluster ID is mismatching");
			return;
		}

		if (qAns.length() > 0) {
			
			// code by imtiaz khan
			if(qName.equalsIgnoreCase("q2_15"))
			{
				if(qAns.length() < 5)
				{	
					CommonStaticClass.showMyAlert(con, "Not Correct",
							"Length of ID must be 5 digit");
					return;
				}
					
			}
			// Validation & skip definition
			String sql = "";
			if (!CommonStaticClass.isMember)
				sql = "UPDATE "
						+ CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getTablename()
						+ " SET " + currentQuestion + "='" + qAns
						+ "' where dataid='" + CommonStaticClass.dataId + "'";
			else
				sql = "UPDATE "
						+ CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getTablename()
						+ " SET " + currentQuestion + "='" + qAns
						+ "' where dataid='" + CommonStaticClass.dataId
						+ "' and memberid=" + CommonStaticClass.memberID;

			if (dbHelper.executeDMLQuery(sql)) {
				
				//code by imtiaz khan
				if(qName.equalsIgnoreCase("q3_6") && Integer.parseInt(qAns) == 0)
				{
					nullifyWithInRange(qName, "q3_8");
					CommonStaticClass.findOutNextSLNo(
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQvar(),"q3_8");
					CommonStaticClass.nextQuestion(ParentActivity.this);
				}
				else if(qName.equalsIgnoreCase("q3_13") && Integer.parseInt(qAns) == 0)
				{
					nullifyWithInRange(qName, "q3_15");
					CommonStaticClass.findOutNextSLNo(
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQvar(),"q3_15");
					CommonStaticClass.nextQuestion(ParentActivity.this);
				}
				else
				{
					CommonStaticClass.findOutNextSLNo(
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQvar(),
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQnext1());
					CommonStaticClass.nextQuestion(ParentActivity.this);
				}
			}
		} else {
			CommonStaticClass.showMyAlert(con, "Not Correct",
					"Please put correct information in the field");
		}
	}

	private boolean IsValidFrmNumeric() {
		String sql = "";
		Cursor mCursor1 = null;
		if (CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo)
				.getQvar().equalsIgnoreCase("clusterid")) {
			String ClusterID = CommonStaticClass.dataId.substring(2, 5);
			if (!ClusterID.equalsIgnoreCase(infoText.getText().toString()))
				return false;
		}
		if (CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo)
				.getQvar().equalsIgnoreCase("q29")
				|| CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar()
						.equalsIgnoreCase("q30")) {
			sql = "select * from tblMainQues where dataid='"
					+ CommonStaticClass.dataId + "'";
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQvar()
					.equalsIgnoreCase("q30")) {
				if (Integer.parseInt(infoText.getText().toString()) >= Integer
						.parseInt(mCursor1.getString(mCursor1
								.getColumnIndex("q12")))) {
					CommonStaticClass.showMyAlert(con, "Not Correct",
							"Wrong year, must be less than worker/member age.");
					return false;
				}
			} else if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQvar()
					.equalsIgnoreCase("q29")) {
				if (Integer.parseInt(infoText.getText().toString()) >= Integer
						.parseInt(mCursor1.getString(mCursor1
								.getColumnIndex("q12")))) {
					CommonStaticClass.showMyAlert(con, "Not Correct",
							"Wrong year, must be less than qustion 29.");
					return false;
				}
			}
		}

		return true;
	}

	private boolean alliszeroFrmNumeric(String qAns) {
		try {
			if (Integer.parseInt(qAns) == 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	// start-FrmNumericTwo
	private void loadGuiFrmNumericTwo(final ViewGroup v) {
		// TODO Auto-generated method stub
		qName = CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar();
		qqq = (TextView) v.findViewById(R.id.qqq);
		num1 = (TextView) v.findViewById(R.id.lblNum1e);
		num2 = (TextView) v.findViewById(R.id.lblNum2e);

		// checkDbHasPreviousDataForThisHouseHold();

		if (CommonStaticClass.langBng) {
			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0) {
				Typeface font = Typeface.createFromAsset(getAssets(),
						"Siyam Rupali ANSI.ttf");

				qqq.setTypeface(font);
				num1.setTypeface(font);
				num2.setTypeface(font);
			}
			;
			qqq.setText(CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0 ? CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng()
					: CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQdesceng());
			if (qName.equalsIgnoreCase("q401")
					|| qName.equalsIgnoreCase("q402")) {
				num1.setText("wbw`Â©Ã³ Lvbv ");
				num2.setText("AbÂ¨vbÂ¨ Lvbv ");
			} else if (qName.equalsIgnoreCase("q310")) {
				num1.setText("cÃ–wZw`b Lvevi cvwb msMÃ–n Kivi msLÂ¨v ");
				num2.setText("cÃ–wZw`b GKzqvUÂ¨ve eÂ¨envi Kivi msLÂ¨v ");
			} else if (qName.equalsIgnoreCase("q621")
					|| qName.equalsIgnoreCase("q622")) {
				num1.setText("wbw`Â©Ã³ Lvbv");
				num2.setText("AbÂ¨vbÂ¨ Lvbv ");
			} else if (qName.equalsIgnoreCase("q615")) {
				num1.setText("cÃ–wZw`b Lvevi cvwb msMÃ–n Kivi msLÂ¨v ");
				num2.setText("cÃ–wZw`b GKzqvUÂ¨ve eÂ¨envi Kivi msLÂ¨v ");
			}
			if (qName.equalsIgnoreCase("q1_12_1")
					|| qName.equalsIgnoreCase("q1_12_2")) {
				num1.setText("QvÃŽ");
				num2.setText("QvÃŽx");
			}
			if (qName.equalsIgnoreCase("q1_12_3")) {
				num1.setText("wkÂ¶K");
				num2.setText("wkwÂ¶Kv");
			}

		} else {
			qqq.setText(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQdesceng());
			if (qName == "q401" || qName.equalsIgnoreCase("q622")
					|| qName.equalsIgnoreCase("q621")) {
				num1.setText("Index hh");
				num2.setText("Non Index hh");
			} else if (qName.equalsIgnoreCase("q310")
					|| qName.equalsIgnoreCase("q615")) {
				num1.setText("collect drinking water daily");
				num2.setText("Use aquatab daily");
			} else {
				if (qName.equalsIgnoreCase("q1_12_1")
						|| qName.equalsIgnoreCase("q1_12_2")) {
					num1.setText("Boy");
					num2.setText("Girls");
				}
				if (qName.equalsIgnoreCase("q1_12_3")) {
					num1.setText("Male");
					num2.setText("Female");
				}
			}
		}

		infoText1 = (EditText) v.findViewById(R.id.txtNum1);
		infoText2 = (EditText) v.findViewById(R.id.txtNum2);

		qName1 = qName + "_a";
		qName2 = qName + "_b";

		String sql = "Select "
				+ qName1
				+ ","
				+ qName2
				+ " from "
				+ CommonStaticClass.questionMap.get(
						CommonStaticClass.currentSLNo).getTablename()
				+ " where dataid='" + CommonStaticClass.dataId + "'";
		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.getCount() > 0) {
				doFillFrmNumericTwo(mCursor1, infoText1, infoText2);
			}
			if (!(infoText1.getText().toString().length() > 0)) {
				if (CommonStaticClass.previousDataFound) {
					if (CommonStaticClass.previousqlist.contains(qName)) {
						sql = "Select "
								+ qName1
								+ ","
								+ qName2
								+ " from "
								+ CommonStaticClass.questionMap.get(
										CommonStaticClass.currentSLNo)
										.getTablename() + " where dataid='"
								+ CommonStaticClass.previoushouseHoldDatatId
								+ "'";
						mCursor1 = dbHelper.getQueryCursor(sql);
						if (mCursor1.getCount() > 0) {
							doFillFrmNumericTwo(mCursor1, infoText1, infoText2);
						}
					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}

		prevButton = (Button) v.findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				userPressedPrevious(ParentActivity.this);
			}

		});
		saveNxtButton = (Button) v.findViewById(R.id.saveNxtButton);

		saveNxtButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				updateTableDataFrmNumericTwo();
			}

		});
		clButton = (Button) v.findViewById(R.id.clButton);
		clButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				resetViewGroup((ViewGroup) v);
			}

		});
		notesButton = (Button) v.findViewById(R.id.btnNote);
		notesButton.setOnClickListener(new OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub

				// TODO Auto-generated method stub
				FraNotes();

			}

		});

	}

	private void doFillFrmNumericTwo(Cursor mCursor1, EditText infoText1,
			EditText infoText2) {
		// TODO Auto-generated method stub
		if (mCursor1.moveToFirst()) {
			do {
				if (mCursor1.getColumnIndex(qName1.toLowerCase()) != -1) {
					String a = mCursor1.getString(mCursor1
							.getColumnIndex(qName1.toLowerCase())) + "";
					infoText1.setText((a.length() > 0
							&& (!a.equalsIgnoreCase("-1")) && (!a
							.equalsIgnoreCase("null"))) ? a : "");
				}
				if (mCursor1.getColumnIndex(qName2.toLowerCase()) != -1) {
					String a = mCursor1.getString(mCursor1
							.getColumnIndex(qName2.toLowerCase())) + "";
					infoText2.setText((a.length() > 0
							&& (!a.equalsIgnoreCase("-1")) && (!a
							.equalsIgnoreCase("null"))) ? a : "");
				}
			} while (mCursor1.moveToNext());
		}
	}

	protected boolean checkForNoneFrmNumericTwo(String lineNumber) {
		String sql = "Select q101,q101a from tblHousehold where dataid='"
				+ CommonStaticClass.dataId + "'";
		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.moveToFirst()) {
				do {
					String lNo = mCursor1.getString(mCursor1
							.getColumnIndex("q101"));
					String typeNo = mCursor1.getString(mCursor1
							.getColumnIndex("q101a"));
					if (lNo.equalsIgnoreCase(lineNumber)) {
						if (Integer.parseInt(typeNo) == 4) {
							return true;
						}
					}
				} while (mCursor1.moveToNext());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}
		return false;
	}

	private void setDataFromFrmNumericTwo(EditText infoText, String qq,
			String table) {
		// TODO Auto-generated method stub
		String sql = "Select " + qq + "_Years from " + table
				+ " where dataid='" + CommonStaticClass.dataId + "'";
		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.moveToFirst()) {
				do {
					String column = qq + "_Years";

					if (mCursor1.getColumnIndex(column) != -1) {
						int yr = mCursor1.getInt(mCursor1
								.getColumnIndex(column));
						final Calendar c = Calendar.getInstance();
						int dateYear = c.get(Calendar.YEAR);
						// int dateMonth = c.get(Calendar.MONTH);
						// int dateDay = c.get(Calendar.DAY_OF_MONTH);
						if (yr != 8888) {
							infoText.setText((dateYear - yr) + "");
						} else {
							infoText.setText(88 + "");
						}
					}
				} while (mCursor1.moveToNext());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}

	}

	private void setDataFromFrmNumericTwo(EditText infoText, String q1,
			String q2, String table) {
		// TODO Auto-generated method stub
		String sql1 = "Select " + q1 + " from " + table + " where dataid='"
				+ CommonStaticClass.dataId + "'";
		String sql2 = "Select " + q2 + " from " + table + " where dataid='"
				+ CommonStaticClass.dataId + "'";
		float value = dataFromFrmNumericTwo(sql1, q1)
				- dataFromFrmNumericTwo(sql2, q2);
		infoText.setText(value + "");

	}

	// Get specific column value corresponding to SQL Query

	private float dataFromFrmNumericTwo(String sql, String columnName) {
		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.moveToFirst()) {
				do {
					String column = columnName;

					if (mCursor1.getColumnIndex(column) != -1) {
						String val = mCursor1.getString(mCursor1
								.getColumnIndex(column));
						Log.e("val", val + "");
						if (val.length() > 0 && !val.equalsIgnoreCase("0")
								&& !val.equalsIgnoreCase("-1")) {
							float a = Float.parseFloat(val);
							return a;
						}
					}
				} while (mCursor1.moveToNext());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}
		return 0;
	}

	private void updateTableDataFrmNumericTwo() {
		// TODO Auto-generated method stub
		String qAns1 = infoText1.getText().toString(), qAns2 = infoText2
				.getText().toString();
		if (!IsValidFrmNumericTwo()) {
			CommonStaticClass.showMyAlert(con, "Not Correct",
					"Cluster ID is mismatching");
			return;
		}
		if (qAns1.length() > 0 && qAns2.length() > 0) {

			// Validation & skip definition

			String sql = "UPDATE "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " SET " + qName1 + "='" + qAns1 + "'," + qName2 + "='"
					+ qAns2 + "' where dataid='" + CommonStaticClass.dataId
					+ "'";
			if (dbHelper.executeDMLQuery(sql)) {

				CommonStaticClass.findOutNextSLNo(
						CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQvar(),
						CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQnext1());
				CommonStaticClass.nextQuestion(ParentActivity.this);
			}
		} else {
			CommonStaticClass.showMyAlert(con, "Not Correct",
					"Please put correct information in the field");
		}
	}

	private boolean IsValidFrmNumericTwo() {
		if (infoText1.getText().toString() == "") {
			return false;
		}

		else {
			return true;
		}
	}

	private boolean alliszeroFrmNumericTwo(String qAns) {
		try {
			if (Integer.parseInt(qAns) == 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	// end-frmNumericTwo

	// FrmReasoning part
	private void Load_UIFrmReasoning(final ViewGroup v) {
		// TODO Auto-generated method stub

		// Enabling & disabling the controls
		Load_ControlsFrmReasoning(v);

		chka1 = (CheckBox) v.findViewById(R.id.chka1);
		chkb1 = (CheckBox) v.findViewById(R.id.chkb1);
		chkc1 = (CheckBox) v.findViewById(R.id.chkc1);
		chkd1 = (CheckBox) v.findViewById(R.id.chkd1);
		chke1 = (CheckBox) v.findViewById(R.id.chke1);
		chkf1 = (CheckBox) v.findViewById(R.id.chkf1);
		chkg1 = (CheckBox) v.findViewById(R.id.chkg1);
		chkh1 = (CheckBox) v.findViewById(R.id.chkh1);
		chki1 = (CheckBox) v.findViewById(R.id.chki1);
		chkj1 = (CheckBox) v.findViewById(R.id.chkj1);

		chka2 = (CheckBox) v.findViewById(R.id.chka2);
		chkb2 = (CheckBox) v.findViewById(R.id.chkb2);
		chkc2 = (CheckBox) v.findViewById(R.id.chkc2);
		chkd2 = (CheckBox) v.findViewById(R.id.chkd2);
		chke2 = (CheckBox) v.findViewById(R.id.chke2);
		chkf2 = (CheckBox) v.findViewById(R.id.chkf2);
		chkg2 = (CheckBox) v.findViewById(R.id.chkg2);
		chkh2 = (CheckBox) v.findViewById(R.id.chkh2);
		chki2 = (CheckBox) v.findViewById(R.id.chki2);
		chkj2 = (CheckBox) v.findViewById(R.id.chkj2);

		chka3 = (CheckBox) v.findViewById(R.id.chka3);
		chkb3 = (CheckBox) v.findViewById(R.id.chkb3);
		chkc3 = (CheckBox) v.findViewById(R.id.chkc3);
		chkd3 = (CheckBox) v.findViewById(R.id.chkd3);
		chke3 = (CheckBox) v.findViewById(R.id.chke3);
		chkf3 = (CheckBox) v.findViewById(R.id.chkf3);
		chkg3 = (CheckBox) v.findViewById(R.id.chkg3);
		chkh3 = (CheckBox) v.findViewById(R.id.chkh3);
		chki3 = (CheckBox) v.findViewById(R.id.chki3);
		chkj3 = (CheckBox) v.findViewById(R.id.chkj3);

		chka1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked)
					a1 = 1;
				else
					a1 = 0;
			}
		});
		chkb1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked)
					b1 = 1;
				else
					b1 = 0;
			}
		});
		chkc1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked)
					c1 = 1;
				else
					c1 = 0;
			}
		});
		chkd1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked)
					d1 = 1;
				else
					d1 = 0;
			}
		});
		chke1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					e1 = 1;
					if (!IsFirstTime1)
						promptUserForInputFrmReasoning(chke1, "p1_11v1other");
				} else {
					e1 = 0;
					other1 = "";
				}
				IsFirstTime1 = false;
			}
		});
		chkf1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked)
					f1 = 1;
				else
					f1 = 0;
			}
		});
		chkg1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked)
					g1 = 1;
				else
					g1 = 0;
			}
		});
		chkh1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked)
					h1 = 1;
				else
					h1 = 0;
			}
		});
		chki1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked)
					i1 = 1;
				else
					i1 = 0;
			}
		});
		chkj1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked)
					j1 = 1;
				else
					j1 = 0;
			}
		});

		chka2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked)
					a2 = 1;
				else
					a2 = 0;
			}
		});
		chkb2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked)
					b2 = 1;
				else
					b2 = 0;
			}
		});
		chkc2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked)
					c2 = 1;
				else
					c2 = 0;
			}
		});
		chkd2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked)
					d2 = 1;
				else
					d2 = 0;
			}
		});
		chke2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					e2 = 1;
					if (!IsFirstTime2)
						promptUserForInputFrmReasoning(chke2, "p1_11v2other");
				} else {
					e2 = 0;
					other2 = "";

				}
				IsFirstTime2 = false;
			}
		});
		chkf2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked)
					f2 = 1;
				else
					f2 = 0;
			}
		});
		chkg2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked)
					g2 = 1;
				else
					g2 = 0;
			}
		});
		chkh2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked)
					h2 = 1;
				else
					h2 = 0;
			}
		});
		chki2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked)
					i2 = 1;
				else
					i2 = 0;
			}
		});
		chkj2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked)
					j2 = 1;
				else
					j2 = 0;
			}
		});
		chka3.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked)
					a3 = 1;
				else
					a3 = 0;
			}
		});
		chkb3.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked)
					b3 = 1;
				else
					b3 = 0;
			}
		});
		chkc3.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked)
					c3 = 1;
				else
					c3 = 0;
			}
		});
		chkd3.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked)
					d3 = 1;
				else
					d3 = 0;
			}
		});
		chke3.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					e3 = 1;
					if (!IsFirstTime3)
						promptUserForInputFrmReasoning(chke3, "p1_11v3other");
				} else {
					e3 = 0;
					other3 = "";
				}
				IsFirstTime3 = false;
			}
		});
		chkf3.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked)
					f3 = 1;
				else
					f3 = 0;
			}
		});
		chkg3.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked)
					g3 = 1;
				else
					g3 = 0;
			}
		});
		chkh3.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked)
					h3 = 1;
				else
					h3 = 0;
			}
		});
		chki3.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked)
					i3 = 1;
				else
					i3 = 0;
			}
		});
		chkj3.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked)
					j3 = 1;
				else
					j3 = 0;
			}
		});

		btnPrev = (Button) v.findViewById(R.id.prevButton);
		btnPrev.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				userPressedPrevious(ParentActivity.this);
			}

		});
		btnsaveNxt = (Button) v.findViewById(R.id.saveNxtButton);
		btnsaveNxt.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				updateTableDataFrmReasoning();
			}

		});
		btnClear = (Button) v.findViewById(R.id.clButton);
		btnClear.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				resetViewGroup((ViewGroup) v);
			}

		});
		notesButton = (Button) v.findViewById(R.id.btnNote);
		notesButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				FraNotes();

			}

		});
	}

	private void Load_ControlsFrmReasoning(ViewGroup v) {
		LinearLayout1 = (LinearLayout) v.findViewById(R.id.linearLayoutR1);
		LinearLayout2 = (LinearLayout) v.findViewById(R.id.linearLayoutR2);
		LinearLayout3 = (LinearLayout) v.findViewById(R.id.linearLayoutR3);

		qqq = (TextView) v.findViewById(R.id.qqq);

		lbla = (TextView) v.findViewById(R.id.lbla);
		lblb = (TextView) v.findViewById(R.id.lblb);
		lblc = (TextView) v.findViewById(R.id.lblc);
		lbld = (TextView) v.findViewById(R.id.lbld);
		lble = (TextView) v.findViewById(R.id.lble);
		lblf = (TextView) v.findViewById(R.id.lblf);
		lblg = (TextView) v.findViewById(R.id.lblg);
		lblh = (TextView) v.findViewById(R.id.lblh);
		lbli = (TextView) v.findViewById(R.id.lbli);
		lblj = (TextView) v.findViewById(R.id.lblj);

		if (CommonStaticClass.langBng) {

			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0) {
				qqq.setTypeface(font);
			}
			;
			lbla.setTypeface(font);
			lbla.setText(con.getResources().getString(R.string.lblaBN));
			lblb.setTypeface(font);
			lblb.setText(con.getResources().getString(R.string.lblbBN));
			lblc.setTypeface(font);
			lblc.setText(con.getResources().getString(R.string.lblcBN));
			lbld.setTypeface(font);
			lbld.setText(con.getResources().getString(R.string.lbldBN));
			lble.setTypeface(font);
			lble.setText(con.getResources().getString(R.string.lbleBN));
			lblf.setTypeface(font);
			lblf.setText(con.getResources().getString(R.string.lblfBN));
			lblg.setTypeface(font);
			lblg.setText(con.getResources().getString(R.string.lblgBN));
			lblh.setTypeface(font);
			lblh.setText(con.getResources().getString(R.string.lblhBN));
			lbli.setTypeface(font);
			lbli.setText(con.getResources().getString(R.string.lbliBN));
			lblj.setTypeface(font);
			lblj.setText(con.getResources().getString(R.string.lbljBN));

			qqq.setText(CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0 ? CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng()
					: CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQdesceng());
		} else {

			lbla.setTypeface(null);

			lblb.setTypeface(null);

			lblc.setTypeface(null);

			lbld.setTypeface(null);

			lble.setTypeface(null);

			lblf.setTypeface(null);

			lblg.setTypeface(null);

			lblh.setTypeface(null);

			lbli.setTypeface(null);

			lblj.setTypeface(null);

			lbla.setText("a.Good treatment");

			lblb.setText("b. Good behaviour of health provider");

			lblc.setText("c.See patients with attention & time");

			lbld.setText("d.Do not know");

			lble.setText("e.Others");

			lblf.setText("f.Close distance from house");

			lblg.setText("g.Low cost");

			lblh.setText("h.Doctors available");

			lbli.setText("i.Drugs available/");
			lblj.setText("j.Familiar doctor/treatment place");

			qqq.setTypeface(null);
			qqq.setText(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQdesceng());
		}

		String sql = "Select * from tblMainQues where dataid='"
				+ CommonStaticClass.dataId + "' and memberid='"
				+ CommonStaticClass.memberID + "'";
		Cursor mCursor = null;
		try {
			mCursor = dbHelper.getQueryCursor(sql);
			if (mCursor.getCount() > 0) {
				if (mCursor.moveToFirst()) {
					do {
						String val1 = mCursor.getString(mCursor
								.getColumnIndex("p1_10v1"));
						String val2 = mCursor.getString(mCursor
								.getColumnIndex("p1_10v2"));
						String val3 = mCursor.getString(mCursor
								.getColumnIndex("p1_10v3"));
						if (val1.length() > 0
								&& (val1.equalsIgnoreCase("-1")
										|| val1.equalsIgnoreCase("2") || val1
											.equalsIgnoreCase("99"))) {
							LinearLayout1.setVisibility(View.GONE);
							IsVisited1st = false;
						}
						if (val2.length() > 0
								&& (val2.equalsIgnoreCase("-1")
										|| val2.equalsIgnoreCase("2") || val2
											.equalsIgnoreCase("99"))) {
							LinearLayout2.setVisibility(View.GONE);
							IsVisited2nd = false;
						}
						if (val3.length() > 0
								&& (val3.equalsIgnoreCase("-1")
										|| val3.equalsIgnoreCase("2") || val3
											.equalsIgnoreCase("99"))) {
							LinearLayout3.setVisibility(View.GONE);
							IsVisited3rd = false;
						}
					} while (mCursor.moveToNext());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			mCursor.close();
			mCursor = null;
		}

	}

	private void updateTableDataFrmReasoning() {
		try {
			if (chke1.isChecked() && other1 == "") {
				other1 = dbHelper.GetSingleColumnData("p1_11v1other");
			}
			if (chke2.isChecked() && other2 == "") {
				other2 = dbHelper.GetSingleColumnData("p1_11v2other");
			}
			if (chke3.isChecked() && other3 == "") {
				other3 = dbHelper.GetSingleColumnData("p1_11v3other");
			}

			if (!IsValidFrmReasoning()) {
				CommonStaticClass.showMyAlert(con, "Error",
						"Please check the options");
				return;
			}

			String SQL = "Update tblMainQues set p1_11av1=" + a1 + ",p1_11bv1="
					+ b1 + ",p1_11cv1=" + c1 + ",p1_11dv1=" + d1 + ",p1_11ev1="
					+ e1 + ",p1_11fv1=" + f1 + ",p1_11gv1=" + g1 + ",p1_11hv1="
					+ h1 + ",p1_11iv1=" + i1 + ",p1_11jv1=" + j1 + ",p1_11av2="
					+ a2 + ",p1_11bv2=" + b2 + ",p1_11cv2=" + c2 + ",p1_11dv2="
					+ d2 + ",p1_11ev2=" + e2 + ",p1_11fv2=" + f2 + ",p1_11gv2="
					+ g2 + ",p1_11hv2=" + h2 + ",p1_11iv2=" + i2 + ",p1_11jv2="
					+ j2 + ",p1_11av3=" + a3 + ",p1_11bv3=" + b3 + ",p1_11cv3="
					+ c3 + ",p1_11dv3=" + d3 + ",p1_11ev3=" + e3 + ",p1_11fv3="
					+ f3 + ",p1_11gv3=" + g3 + ",p1_11hv3=" + h3 + ",p1_11iv3="
					+ i3 + ",p1_11jv3=" + j3 + ",p1_11v1other='" + other1
					+ "',p1_11v2other='" + other2 + "',p1_11v3other='" + other3
					+ "' Where dataid='" + CommonStaticClass.dataId
					+ "' and memberid=" + CommonStaticClass.memberID;
			if (dbHelper.executeDMLQuery(SQL)) {
				// preserveState();
				CommonStaticClass.findOutNextSLNo(
						CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQvar(),
						CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQnext1());
				CommonStaticClass.nextQuestion(ParentActivity.this);
			}
		} catch (Exception e) {

		} finally {

		}

	}

	private boolean IsValidFrmReasoning() {
		boolean vaild = false;
		if (IsVisited1st) {
			if (!chka1.isChecked() && !chkb1.isChecked() && !chkc1.isChecked()
					&& !chkd1.isChecked() && !chke1.isChecked()
					&& !chkf1.isChecked() && !chkg1.isChecked()
					&& !chkh1.isChecked() && !chki1.isChecked()
					&& !chkj1.isChecked())
				return vaild;
			if (chke1.isChecked() && other1 == "")
				return vaild;
		}
		if (IsVisited2nd) {
			if (!chka2.isChecked() && !chkb2.isChecked() && !chkc2.isChecked()
					&& !chkd2.isChecked() && !chke2.isChecked()
					&& !chkf2.isChecked() && !chkg2.isChecked()
					&& !chkh2.isChecked() && !chki2.isChecked()
					&& !chkj2.isChecked())
				return vaild;
			if (chke2.isChecked() && other2 == "")
				return vaild;

		}
		if (IsVisited3rd) {
			if (!chka3.isChecked() && !chkb3.isChecked() && !chkc3.isChecked()
					&& !chkd3.isChecked() && !chke3.isChecked()
					&& !chkf3.isChecked() && !chkg3.isChecked()
					&& !chkh3.isChecked() && !chki3.isChecked()
					&& !chkj3.isChecked())
				return vaild;
			if (chke3.isChecked() && other3 == "")
				return vaild;

		}

		return true;

	}

	private void Load_DataFrmReasoning() {

		String sql = "Select * from tblMainQues where dataid='"
				+ CommonStaticClass.dataId + "' and memberid='"
				+ CommonStaticClass.memberID + "'";
		Cursor mCursor = null;
		String inColumnFirst = "p1_11";
		String inColumnMiddle = "";
		String inColumnLast = "";
		try {
			mCursor = dbHelper.getQueryCursor(sql);
			if (mCursor.getCount() > 0) {
				if (mCursor.moveToFirst()) {
					do {
						for (int i = 1; i <= 3; i++) {
							if (i == 1)
								inColumnLast = "v1";
							else if (i == 2)
								inColumnLast = "v2";
							else if (i == 3)
								inColumnLast = "v3";
							for (int j = 1; j <= 10; j++) {
								if (j == 1)
									inColumnMiddle = "a";
								else if (j == 2)
									inColumnMiddle = "b";
								else if (j == 3)
									inColumnMiddle = "c";
								else if (j == 4)
									inColumnMiddle = "d";
								else if (j == 5)
									inColumnMiddle = "e";
								else if (j == 6)
									inColumnMiddle = "f";
								else if (j == 7)
									inColumnMiddle = "g";
								else if (j == 8)
									inColumnMiddle = "h";
								else if (j == 9)
									inColumnMiddle = "i";
								else if (j == 10)
									inColumnMiddle = "j";

								inColumnFirst = "p1_11";
								inColumnFirst = inColumnFirst + inColumnMiddle
										+ inColumnLast;

								if (mCursor.getColumnIndex(inColumnFirst) != -1) {
									int a = mCursor.getInt(mCursor
											.getColumnIndex(inColumnFirst));
									if (i == 1 && j == 1 && a == 1)
										chka1.setChecked(true);
									else if (i == 1 && j == 2 && a == 1)
										chkb1.setChecked(true);
									else if (i == 1 && j == 3 && a == 1)
										chkc1.setChecked(true);
									else if (i == 1 && j == 4 && a == 1)
										chkd1.setChecked(true);
									else if (i == 1 && j == 5 && a == 1)
										chke1.setChecked(true);
									else if (i == 1 && j == 6 && a == 1)
										chkf1.setChecked(true);
									else if (i == 1 && j == 7 && a == 1)
										chkg1.setChecked(true);
									else if (i == 1 && j == 8 && a == 1)
										chkh1.setChecked(true);
									else if (i == 1 && j == 9 && a == 1)
										chki1.setChecked(true);
									else if (i == 1 && j == 10 && a == 1)
										chkj1.setChecked(true);

									else if (i == 2 && j == 1 && a == 1)
										chka2.setChecked(true);
									else if (i == 2 && j == 2 && a == 1)
										chkb2.setChecked(true);
									else if (i == 2 && j == 3 && a == 1)
										chkc2.setChecked(true);
									else if (i == 2 && j == 4 && a == 1)
										chkd2.setChecked(true);
									else if (i == 2 && j == 5 && a == 1)
										chke2.setChecked(true);
									else if (i == 2 && j == 6 && a == 1)
										chkf2.setChecked(true);
									else if (i == 2 && j == 7 && a == 1)
										chkg2.setChecked(true);
									else if (i == 2 && j == 8 && a == 1)
										chkh2.setChecked(true);
									else if (i == 2 && j == 9 && a == 1)
										chki2.setChecked(true);
									else if (i == 2 && j == 10 && a == 1)
										chkj2.setChecked(true);

									else if (i == 3 && j == 1 && a == 1)
										chka3.setChecked(true);
									else if (i == 3 && j == 2 && a == 1)
										chkb3.setChecked(true);
									else if (i == 3 && j == 3 && a == 1)
										chkc3.setChecked(true);
									else if (i == 3 && j == 4 && a == 1)
										chkd3.setChecked(true);
									else if (i == 3 && j == 5 && a == 1)
										chke3.setChecked(true);
									else if (i == 3 && j == 6 && a == 1)
										chkf3.setChecked(true);
									else if (i == 3 && j == 7 && a == 1)
										chkg3.setChecked(true);
									else if (i == 3 && j == 8 && a == 1)
										chkh3.setChecked(true);
									else if (i == 3 && j == 9 && a == 1)
										chki3.setChecked(true);
									else if (i == 3 && j == 10 && a == 1)
										chkj3.setChecked(true);

								}
							}
						}
					} while (mCursor.moveToNext());
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void promptUserForInputFrmReasoning(final CheckBox checkbox,
			String ColumnName) {
		// get prompts.xml view

		LayoutInflater li = LayoutInflater.from(con);
		View promptsView = li.inflate(R.layout.prompts, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(con);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);

		final EditText userInput = (EditText) promptsView
				.findViewById(R.id.editTextDialogUserInput);

		if (checkbox == chke1) {
			other1 = dbHelper.GetSingleColumnData(ColumnName);
			if (other1 != null && other1.length() > 0) {
				userInput.setText(other1);
			}
		} else if (checkbox == chke2) {
			other2 = dbHelper.GetSingleColumnData(ColumnName);
			if (other2 != null && other2.length() > 0) {
				userInput.setText(other2);
			}
		} else if (checkbox == chke3) {
			other3 = dbHelper.GetSingleColumnData(ColumnName);
			if (other3 != null && other3.length() > 0) {
				userInput.setText(other3);
			}
		}

		// set dialog message
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// get user input and set it to result
						// edit text
						// insertDataToRelationOther(userInput.getText().toString());
						if (checkbox == chke1)
							other1 = userInput.getText().toString();
						else if (checkbox == chke2)
							other2 = userInput.getText().toString();
						else if (checkbox == chke3)
							other3 = userInput.getText().toString();

					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();

							}
						});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
	}

	// frmsinglechoice part
	private void Load_UIFrmSingleChoice(final ViewGroup v) {
		// TODO Auto-generated method stub
		code = -1;
		qqq = (TextView) v.findViewById(R.id.qqq);

		// checkDbHasPreviousDataForThisHouseHold();

		qName = CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar();

		op = CommonStaticClass.findOptionsForThisQuestion(dbHelper, qName);

		if (CommonStaticClass.langBng) {
			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0) {
				Typeface font = Typeface.createFromAsset(getAssets(),
						"Siyam Rupali ANSI.ttf");

				qqq.setTypeface(font);
			}
			;
			qqq.setText(CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0 ? CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng()
					: CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQdesceng());
		} else {
			qqq.setTypeface(null);
			qqq.setText(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQdesceng());
		}

		prevButton = (Button) v.findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				userPressedPrevious(ParentActivity.this);
			}

		});
		saveNxtButton = (Button) v.findViewById(R.id.saveNxtButton);

		saveNxtButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (code == -1) {
					CommonStaticClass.showMyAlert(con, "Error",
							"Please select one before going to next question");
				} else {
					progressDialog = ProgressDialog.show(con, "Wait",
							"Please wait while processing next question");
					new Thread() {

						public void run() {
							Looper.prepare();
							updateTableDataFrmSingleChoice();

							Message msg = new Message();
							msg.what = UPDATEDONE;
							handler.sendMessage(msg);
							Looper.loop();
						}
					}.start();
				}
			}

		});

		String sql = "";
		if (!CommonStaticClass.isMember)
			sql = "Select "
					+ qName
					+ " from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " where dataid='" + CommonStaticClass.dataId + "'";
		else
			sql = "Select "
					+ qName
					+ " from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " where dataid='" + CommonStaticClass.dataId
					+ "' and memberid='" + CommonStaticClass.memberID + "'";
		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.getCount() > 0) {
				doFill(mCursor1);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			idIfEdit = -1;
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}

		/*
		 * if(mRadioGroup!=null) { mRadioGroup. mRadioGroup.clearCheck(); }
		 */

		mRadioGroup = (RadioGroup) v.findViewById(R.id.sigleChoice);
		mRadioGroup.removeAllViews();
		Log.e("size", "" + op.codeList.size());
		mRadioGroup
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					public void onCheckedChanged(final RadioGroup group,
							int checkedId) {
						// TODO Auto-generated method stub

						code = group.getCheckedRadioButtonId();
						nextToGo = op.qnList.get(op.codeList.indexOf(code));

						Log.e("next to go", nextToGo);

						RadioButton checkedRadioButton = (RadioButton) group
								.findViewById(checkedId);

						if (checkedRadioButton != null) {

							if (String.valueOf(code) != null) {
								checkedRadioButton.setChecked(true);
								// group.check(checkedId);
							}

							Toast.makeText(getApplicationContext(),
									String.valueOf(code).toString(),
									Toast.LENGTH_SHORT).show();

						}
					}
				});

		for (int i = 0; i < op.codeList.size(); i++) {

			RadioButton newRadioButton = new RadioButton(this);
			if (CommonStaticClass.langBng) {
				if (op.capBngList.get(i).length() > 0) {
					Typeface font = Typeface.createFromAsset(getAssets(),
							"Siyam Rupali ANSI.ttf");
					newRadioButton.setTypeface(font);
				}
				;
				newRadioButton.setText(op.capBngList.get(i).length() > 0 ? Html
						.fromHtml(op.capBngList.get(i)) : Html
						.fromHtml(op.capEngList.get(i)));

			} else {
				newRadioButton.setText(Html.fromHtml(op.capEngList.get(i)));

			}
			newRadioButton.setId(op.codeList.get(i));
			// if(CommonStaticClass.mode.equalsIgnoreCase(CommonStaticClass.EDITMODE)){
			if (idIfEdit == op.codeList.get(i)) {
				newRadioButton.setChecked(true);
				code = idIfEdit;
			}
			// }
			LinearLayout.LayoutParams layoutParams = new RadioGroup.LayoutParams(
					RadioGroup.LayoutParams.WRAP_CONTENT,
					RadioGroup.LayoutParams.WRAP_CONTENT);

			mRadioGroup.addView(newRadioButton, i, layoutParams);
		}

		clButton = (Button) v.findViewById(R.id.clButton);
		clButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				code = -1;
				resetViewGroup((ViewGroup) v);
			}

		});
		notesButton = (Button) v.findViewById(R.id.btnNote);
		notesButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				FraNotes();

			}

		});
	}

	private boolean doFill(Cursor mCursor1) {
		// TODO Auto-generated method stub
		boolean dataOk = false;
		if (mCursor1.moveToFirst()) {
			do {
				if (mCursor1.getColumnIndex(qName) != -1) {
					// Log.e("frmSingle",mCursor1.getString(mCursor1.getColumnIndex(CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo).getQvar())));
					String a = mCursor1.getString(mCursor1
							.getColumnIndex(qName)) + "";
					Log.e("aaaa", a + "");
					idIfEdit = (a.length() > 0 && !a.equalsIgnoreCase("null")) ? Integer
							.parseInt(a) : -1;
					if (op.codeList.contains(idIfEdit)) {
						dataOk = true;
					}
				}
			} while (mCursor1.moveToNext());
		}
		return dataOk;
	}

	private void SaveFamilyInfoFrmSingleChoice() {
		String entryBy = CommonStaticClass.userSpecificId;
		Date d = new Date(System.currentTimeMillis());
		String entryDate = "dd-mmm-yy";
		entryDate = d.toString();

		String sql = "", vName = "", vAge = "", vSex = "";
		Cursor cursor = null;
		sql = "Select * from  tblFamilyInfo Where  memid='"
				+ CommonStaticClass.dataId + "'";
		cursor = dbHelper.getQueryCursor(sql);
		if (cursor.getCount() == 0) {
			sql = "Select tblMainQues.q10,tblMainQues.q12,tblMainQuesSc.q13 from  tblMainQues inner join tblMainQuesSc on tblMainQues.dataid=tblMainQuesSc.dataid Where  tblMainQues.dataid='"
					+ CommonStaticClass.dataId + "'";
			cursor = dbHelper.getQueryCursor(sql);
			if (cursor.moveToFirst()) {
				vName = cursor.getString(cursor.getColumnIndex("q10"));
				vAge = cursor.getString(cursor.getColumnIndex("q12"));
				vSex = cursor.getString(cursor.getColumnIndex("q13"));
			}
			sql = "insert into tblFamilyInfo (dataid, memid, Name, Age_Year,Sex,EntryBy,EntryDate) values ('"
					+ CommonStaticClass.dataId.substring(0, 5)
					+ "00', '"
					+ CommonStaticClass.dataId
					+ "','"
					+ vName
					+ "',"
					+ vAge
					+ "," + vSex + ",'" + entryBy + "','" + entryDate + "')";
			if (dbHelper.executeDMLQuery(sql)) {
				sql = "update  tblMainQues set q15=q15+1 Where dataid='"
						+ CommonStaticClass.dataId.substring(0, 5) + "00'";
				if (dbHelper.executeDMLQuery(sql)) {

				}
			}
		}
	}

	private void updateTableDataFrmSingleChoice() {
		// TODO Auto-generated method stub
		// CommonStaticClass.findskiplistfromDB("q2_1c", "q2_1c", dbHelper);
		String sql = "";
		String qtoGo = "";
		if (nextToGo == null) {
			nextToGo = "";
		}

		nextToGo = nextToGo.length() > 0 ? nextToGo
				: CommonStaticClass.questionMap.get(
						CommonStaticClass.currentSLNo).getQnext1();

		try {

			if (code != -1) {
				if (!CommonStaticClass.isMember)
					sql = "UPDATE "
							+ CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo)
									.getTablename()
							+ " SET "
							+ CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQvar()
							+ "='" + code + "' where dataid='"
							+ CommonStaticClass.dataId + "'";
				else
					sql = "UPDATE "
							+ CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo)
									.getTablename()
							+ " SET "
							+ CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQvar()
							+ "='" + code + "' where dataid='"
							+ CommonStaticClass.dataId + "' and memberid='"
							+ CommonStaticClass.memberID + "'";

				if (dbHelper.executeDMLQuery(sql)) {
					
					if (nextToGo.equalsIgnoreCase("END")) {
						Message msg = new Message();
						msg.what = UPDATEDONE;
						handler.sendMessage(msg);
						if (IfCompletedAllMembersFrmSingleChoice())
							showUserFinishDialogFrmSingleChoice();
						else {
							CommonStaticClass.currentSLNo = 31;
							CommonStaticClass.nextQuestion(ParentActivity.this);
						}
					}  
					else if(qName.equalsIgnoreCase("q4_6")
							&& getChoiceValue("q4_4") == 2)
					{
						
						CommonStaticClass.findOutNextSLNo(qName, "q4_7");
						CommonStaticClass.nextQuestion(ParentActivity.this);
						
					}
					else if(qName.equalsIgnoreCase("q5_18")
							&& getChoiceValue("q4_6") != 1)
					{
						
						CommonStaticClass.findOutNextSLNo(qName, "END");
						CommonStaticClass.nextQuestion(ParentActivity.this);
						
					}
					
					else {
						nullifyWithInRange(qName, nextToGo);
						CommonStaticClass.findOutNextSLNo(qName, nextToGo);
						CommonStaticClass.nextQuestion(ParentActivity.this);
					}
				}
			}
		} catch (Exception ex) {
			// CommonStaticClass.showMyAlert(con, "Exception",
			// ex.getMessage().toString());
		}

	}

	private void updateq7() {

		try {

			CommonStaticClass.qskipList.add("q7_1_b");
			CommonStaticClass.qskipList.add("q7_1_c");
			CommonStaticClass.qskipList.add("q7_1_d");
			CommonStaticClass.qskipList.add("q7_2_a");
			CommonStaticClass.qskipList.add("q7_2_b");
			CommonStaticClass.qskipList.add("q7_2_c");
			CommonStaticClass.qskipList.add("q7_2_d");
			CommonStaticClass.qskipList.add("q7_3_a");
			CommonStaticClass.qskipList.add("q7_3_b");
			CommonStaticClass.qskipList.add("q7_3_c");
			CommonStaticClass.qskipList.add("q7_3_d");
			CommonStaticClass.qskipList.add("q7_4_a");
			CommonStaticClass.qskipList.add("q7_4_b");
			CommonStaticClass.qskipList.add("q7_4_c");
			CommonStaticClass.qskipList.add("q7_4_d");

		} catch (Exception e) {
			Log.e("updateq7", e.toString());
		}

	}

	private void Load_DataFrmSingleChoice() {

	}

	private boolean IfCompletedAllMembersFrmSingleChoice() {
		boolean IsCompleted = true;
		String sql1 = "Select * from tblMainQues  where dataid='"
				+ CommonStaticClass.dataId + "'";
		String sql2 = "Select * from tblFamilyMember  where dataid='"
				+ CommonStaticClass.dataId
				+ "' and (anysick=1 or visitdoc=1 or hospitalized=1)";
		Cursor mCursor1 = null;
		Cursor mCursor2 = null;
		try {

			mCursor1 = dbHelper.getQueryCursor(sql1);
			mCursor2 = dbHelper.getQueryCursor(sql2);

			int a = mCursor1.getCount();
			int b = mCursor2.getCount();
			String x = Integer.toString(a);
			String y = Integer.toString(b);
			if (x.equalsIgnoreCase(y))
				IsCompleted = true;
			else
				IsCompleted = false;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}

		return IsCompleted;
	}

	private void showUserFinishDialogFrmSingleChoice() {
		// get prompts.xml view
		LayoutInflater li = LayoutInflater.from(con);
		LinearLayout promptsView = (LinearLayout) li.inflate(R.layout.prompts,
				null);

		promptsView.removeView((EditText) promptsView
				.findViewById(R.id.editTextDialogUserInput));

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(con);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);

		final TextView textInfo = (TextView) promptsView
				.findViewById(R.id.textView1);
		textInfo.setText("Do u want to finish this cycle?");

		// set dialog message
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						Log.e("qName: ", "qName: " + qName);
						if (qName.equalsIgnoreCase("q4_2")) {
							nullifyq4_3();
						}
						CommonStaticClass.findOutNextSLNo(qName, "END");
						CommonStaticClass.nextQuestion(ParentActivity.this);

					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
	}

	private Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UPDATEDONE:
				progressDialog.dismiss();
				break;
			}

		}
	};

	// FrmText part
	private void loadGuiFrmText(final ViewGroup v) {
		// TODO Auto-generated method stub

		// checkDbHasPreviousDataForThisHouseHold();

		qName = CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar();
		qqq = (TextView) v.findViewById(R.id.qqq);

		if (CommonStaticClass.langBng) {
			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0) {
				Typeface font = Typeface.createFromAsset(getAssets(),
						"Siyam Rupali ANSI.ttf");
				qqq.setTypeface(font);
			}
			;
			qqq.setText(CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0 ? CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng()
					: CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQdesceng());
		} else {
			qqq.setTypeface(null);
			qqq.setText(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQdesceng());
		}

		infoText = (EditText) v.findViewById(R.id.infoText);
		infoText.requestFocus();
		String sql = "";
		if (!CommonStaticClass.isMember)
			sql = "Select "
					+ qName
					+ " from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " where dataid='" + CommonStaticClass.dataId + "'";
		else
			sql = "Select "
					+ qName
					+ " from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " where dataid='" + CommonStaticClass.dataId
					+ "' and memberid=" + CommonStaticClass.memberID;
		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.getCount() > 0) {
				doFillFrmText(mCursor1, infoText);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}

		prevButton = (Button) v.findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				userPressedPrevious(ParentActivity.this);
			}

		});
		saveNxtButton = (Button) v.findViewById(R.id.saveNxtButton);

		saveNxtButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				updateTableDataFrmText();
			}

		});
		clButton = (Button) v.findViewById(R.id.clButton);
		clButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				resetViewGroup((ViewGroup) v);
			}

		});
		notesButton = (Button) v.findViewById(R.id.btnNote);
		notesButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				FraNotes();

			}

		});

	}

	// FrmText part
	private void loadGuiFrmBarcode(final ViewGroup v) {
		// TODO Auto-generated method stub

		// checkDbHasPreviousDataForThisHouseHold();

		qName = CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar();
		qqq = (TextView) v.findViewById(R.id.qqq);

		if (CommonStaticClass.langBng) {
			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0) {
				Typeface font = Typeface.createFromAsset(getAssets(),
						"Siyam Rupali ANSI.ttf");
				qqq.setTypeface(font);
			}
			;
			qqq.setText(CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0 ? CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng()
					: CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQdesceng());
		} else {
			qqq.setTypeface(null);
			qqq.setText(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQdesceng());
		}

		infoText = (EditText) v.findViewById(R.id.infoText);
		infoText.requestFocus();
		String sql = "";
		if (!CommonStaticClass.isMember)
			sql = "Select "
					+ qName
					+ " from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " where dataid='" + CommonStaticClass.dataId + "'";
		else
			sql = "Select "
					+ qName
					+ " from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " where dataid='" + CommonStaticClass.dataId
					+ "' and memberid=" + CommonStaticClass.memberID;
		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.getCount() > 0) {
				doFillFrmText(mCursor1, infoText);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}

		prevButton = (Button) v.findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				userPressedPrevious(ParentActivity.this);
			}

		});
		saveNxtButton = (Button) v.findViewById(R.id.saveNxtButton);

		saveNxtButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				updateTableDataFrmText();
			}

		});

		clButton = (Button) v.findViewById(R.id.clButton);
		clButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				resetViewGroup((ViewGroup) v);
			}

		});

		notesButton = (Button) v.findViewById(R.id.btnNote);
		notesButton.setText("Click to Scan");
		notesButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				// FraNotes();

				IntentIntegrator scanIntegrator = new IntentIntegrator(
						thisactivity);
				// start scanning
				scanIntegrator.initiateScan();
			}

		});

	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		// retrieve result of scanning - instantiate ZXing object
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(
				requestCode, resultCode, intent);
		// check we have a valid result
		if (scanningResult != null) {
			// get content from Intent Result
			String scanContent = scanningResult.getContents();
			// get format name of data scanned
			String scanFormat = scanningResult.getFormatName();
			// output to UI
			// formatTxt.setText("FORMAT: "+scanFormat);
			infoText.setText(scanContent);
		} else {
			// invalid scan data or scan canceled
			Toast toast = Toast.makeText(getApplicationContext(),
					"No scan data received!", Toast.LENGTH_SHORT);
			toast.show();
		}
	}

	private void doFillFrmText(Cursor mCursor1, EditText infoText) {
		// TODO Auto-generated method stub
		if (mCursor1.moveToFirst()) {
			do {
				String column = qName;

				if (mCursor1.getColumnIndex(column) != -1) {
					String a = mCursor1.getString(mCursor1
							.getColumnIndex(qName)) + "";

					/*
					 * if (qName.equalsIgnoreCase("q1_3")) { if
					 * (a.equalsIgnoreCase("null")) {
					 * infoText.setText(dbHelper.GetSingleColumnData("q3")); }
					 * else { infoText.setText(dbHelper
					 * .GetSingleColumnData("q1_3"));
					 * 
					 * }
					 * 
					 * } else
					 */
					infoText.setText((a.length() > 0
							&& (!a.equalsIgnoreCase("-1")) && (!a
							.equalsIgnoreCase("null"))) ? a : "");

				}
			} while (mCursor1.moveToNext());
		}
	}

	private void updateTableDataFrmText() {
		// TODO Auto-generated method stub
		String qAns = infoText.getText().toString().trim();
		if (qAns.length() > 0) {

			if (qName.equalsIgnoreCase("c1_2"))
				setTitle("Child Name :: " + qAns);

			String sql = "";
			if (!CommonStaticClass.isMember)
				sql = "UPDATE "
						+ CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getTablename()
						+ " SET " + qName + "='" + qAns + "' where dataid='"
						+ CommonStaticClass.dataId + "'";
			else
				sql = "UPDATE "
						+ CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getTablename()
						+ " SET " + qName + "='" + qAns + "' where dataid='"
						+ CommonStaticClass.dataId + "' and memberid="
						+ CommonStaticClass.memberID;
			if (dbHelper.executeDMLQuery(sql)) {
				if (CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar()
						.equalsIgnoreCase("q9")) {

					// nullifyWithInRange("q9", "q33");

					CommonStaticClass.findOutNextSLNo(
							qName,
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQnext1());
					CommonStaticClass.nextQuestion(ParentActivity.this);

				} else if (CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQnext1()
						.equalsIgnoreCase("END")) {
					showUserFinishDialogFrmText();
				} else {
					// preserveState();
					CommonStaticClass.findOutNextSLNo(
							qName,
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQnext1());
					CommonStaticClass.nextQuestion(ParentActivity.this);
				}
			}
		}
	}

	private boolean IfCompletedAllMembersFrmText() {
		boolean IsCompleted = true;
		String sql1 = "Select * from tblMainQues  where dataid='"
				+ CommonStaticClass.dataId + "'";
		String sql2 = "Select * from tblFamilyMember  where dataid='"
				+ CommonStaticClass.dataId
				+ "' and (anysick=1 or visitdoc=1 or hospitalized=1)";
		Cursor mCursor1 = null;
		Cursor mCursor2 = null;
		try {

			mCursor1 = dbHelper.getQueryCursor(sql1);
			mCursor2 = dbHelper.getQueryCursor(sql2);

			int a = mCursor1.getCount();
			int b = mCursor2.getCount();
			String x = Integer.toString(a);
			String y = Integer.toString(b);
			if (x.equalsIgnoreCase(y))
				IsCompleted = true;
			else
				IsCompleted = false;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}

		return IsCompleted;
	}

	private void showUserFinishDialogFrmText() {
		// get prompts.xml view
		LayoutInflater li = LayoutInflater.from(con);
		LinearLayout promptsView = (LinearLayout) li.inflate(R.layout.prompts,
				null);

		promptsView.removeView((EditText) promptsView
				.findViewById(R.id.editTextDialogUserInput));

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(con);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);

		final TextView textInfo = (TextView) promptsView
				.findViewById(R.id.textView1);
		textInfo.setText("Do u want to finish this cycle?");

		// set dialog message
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

						CommonStaticClass.findOutNextSLNo(
								qName,
								CommonStaticClass.questionMap.get(
										CommonStaticClass.currentSLNo)
										.getQnext1());
						CommonStaticClass.nextQuestion(ParentActivity.this);

					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
	}

	private boolean characterCheckingFrmText(String qAns) {
		boolean allch = true;
		String a = qAns.toLowerCase();
		char[] cArray = a.toCharArray();
		for (char c : cArray) {
			if (c == ' ' || c == '.') {
				continue;
			}
			if (c < 'a' || c > 'z') {
				allch = false;
				return allch;
			}
		}
		return allch;
	}

	// FrmTime part
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case TIME_DIALOG:
			return new TimePickerDialog(this, timeSetListener, TimeHour,
					TimeMinute, true);
		case DATE_DIALOG:
			return new DatePickerDialog(this, dateSetListener, dateYear,
					dateMonth, dateDay);

		case DATE_DIALOG_ID:

			DatePickerDialog datePickerDialog = this.customDatePicker();
			return datePickerDialog;

			/*
			 * return new DatePickerDialog(this, dateSetListener, dateYear,
			 * dateMonth, dateDay);
			 */

		}

		return null;
	}

	private DatePickerDialog customDatePicker() {
		DatePickerDialog dpd = new DatePickerDialog(this, dateSetListener,
				dateYear, dateMonth, dateDay);
		try {

			Field[] datePickerDialogFields = dpd.getClass().getDeclaredFields();
			for (Field datePickerDialogField : datePickerDialogFields) {
				if (datePickerDialogField.getName().equals("mDatePicker")) {
					datePickerDialogField.setAccessible(true);
					DatePicker datePicker = (DatePicker) datePickerDialogField
							.get(dpd);
					Field datePickerFields[] = datePickerDialogField.getType()
							.getDeclaredFields();
					for (Field datePickerField : datePickerFields) {
						if ("mDayPicker".equals(datePickerField.getName())
								|| "mDaySpinner".equals(datePickerField
										.getName())) {
							datePickerField.setAccessible(true);
							Object dayPicker = new Object();
							dayPicker = datePickerField.get(datePicker);
							((View) dayPicker).setVisibility(View.GONE);
						}
					}
				}

			}
		} catch (Exception ex) {
		}
		return dpd;
	}

	protected void onPrepareDialog(int id, Dialog dialog) {
		switch (id) {
		case TIME_DIALOG:
			((TimePickerDialog) dialog).updateTime(TimeHour, TimeMinute);
			break;
		case DATE_DIALOG:
			((DatePickerDialog) dialog)
					.updateDate(dateYear, dateMonth, dateDay);

		case DATE_DIALOG_ID:
			((DatePickerDialog) dialog)
					.updateDate(dateYear, dateMonth, dateDay);

			break;
		}
	}

	private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

		public void onTimeSet(TimePicker view, int hour, int minute) {
			TimeHour = hour;
			TimeMinute = minute;
			if (qName.length() > 0) {
				if (qName.equalsIgnoreCase("q17hmd1")
						|| qName.equalsIgnoreCase("q18md1")
						|| qName.equalsIgnoreCase("q17hmd2")
						|| qName.equalsIgnoreCase("q18md2")) {
					// updateDisplayfrmfamily("time");
					return;
				}
			}

			updateDisplay("time");
		}
	};

	private void loadGuiFrmTime(final ViewGroup v) {
		// TODO Auto-generated method stub
		qqq = (TextView) v.findViewById(R.id.qqq);

		// Load Question
		if (CommonStaticClass.langBng) {
			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0) {
				Typeface font = Typeface.createFromAsset(getAssets(),
						"Siyam Rupali ANSI.ttf");
				qqq.setTypeface(font);
			}
			;
			qqq.setText(CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0 ? CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng()
					: CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQdesceng());
		} else {
			qqq.setTypeface(null);
			qqq.setText(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQdesceng());
		}

		pickTime = (EditText) v.findViewById(R.id.pickTime);

		final Calendar c = Calendar.getInstance();
		TimeHour = c.get(Calendar.HOUR_OF_DAY);
		TimeMinute = c.get(Calendar.MINUTE);
		updateDisplay("time");

		prevButton = (Button) v.findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				userPressedPrevious(ParentActivity.this);
			}

		});

		// if(CommonStaticClass.mode.equalsIgnoreCase(CommonStaticClass.EDITMODE)){
		// String sql =
		// "Select "+CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo).getQvar()+" from "+CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo).getTablename()+" where dataid='"+CommonStaticClass.dataId+"'";
		String sql = "";
		if (!CommonStaticClass.isMember)
			sql = "Select * from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " where dataid='" + CommonStaticClass.dataId + "'";
		else
			sql = "Select * from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " where dataid='" + CommonStaticClass.dataId
					+ "' and memberid=" + CommonStaticClass.memberID;

		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.moveToFirst()) {
				do {
					String column = CommonStaticClass.questionMap
							.get(CommonStaticClass.currentSLNo).getQvar()
							.toLowerCase();
					if (mCursor1.getColumnIndex(column) != -1) {
						String res = mCursor1.getString(mCursor1
								.getColumnIndex(column));
						pickTime.setText((res.length() > 0 && !res
								.equalsIgnoreCase("null")) ? res : "");
					}
				} while (mCursor1.moveToNext());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}
		// }

		pickTime.setOnTouchListener(new View.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				showDialog(TIME_DIALOG);
				return false;
			}
		});

		saveNxtButton = (Button) v.findViewById(R.id.saveNxtButton);

		saveNxtButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				updateTableDataFrmTime();
				// preserveState();
			}
		});
		clButton = (Button) v.findViewById(R.id.clButton);
		clButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				resetViewGroup((ViewGroup) v);
			}
		});
		notesButton = (Button) v.findViewById(R.id.btnNote);
		notesButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				FraNotes();
			}
		});
	}

	private void updateTableDataFrmTime() {
		// TODO Auto-generated method stub
		String qAns = pickTime.getText().toString();
		String currentQuestion = CommonStaticClass.questionMap.get(
				CommonStaticClass.currentSLNo).getQvar();
		if (qAns.length() > 0) {

			// if(futureDateValidator(dateYear, dateMonth, dateDay)){
			// CommonStaticClass.showMyAlert(con, "Not Correct",
			// "You are putting future date which is not acceptable");
			// return;
			// }

			String sql = "";
			if (!CommonStaticClass.isMember)
				sql = "UPDATE "
						+ CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getTablename()
						+ " SET " + currentQuestion + "='" + qAns
						+ "' where dataid='" + CommonStaticClass.dataId + "'";
			else
				sql = "UPDATE "
						+ CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getTablename()
						+ " SET " + currentQuestion + "='" + qAns
						+ "' where dataid='" + CommonStaticClass.dataId
						+ "' and memberid=" + CommonStaticClass.memberID;

			if (dbHelper.executeDMLQuery(sql)) {
				if(CommonStaticClass.questionMap.get(
						CommonStaticClass.currentSLNo).getQvar().equalsIgnoreCase("q4_15")
						&& getChoiceValue("q4_11_1") != 1
						&& getChoiceValue("q4_11_2") != 1
						&& getChoiceValue("q4_11_11") != 1)
				{
					
					CommonStaticClass.findOutNextSLNo(qName, "q4_18");
					CommonStaticClass.nextQuestion(ParentActivity.this);
					
				} else {
					nullifyWithInRange(
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQvar(),
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQnext1());
					CommonStaticClass.findOutNextSLNo(
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQvar(),
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQnext1());

					CommonStaticClass.nextQuestion(ParentActivity.this);
				}
			}
		}
	}

	// FrmYearToMin part
	private void loadGuiFrmYearToMin(final ViewGroup v) {
		// TODO Auto-generated method stub
		qqq = (TextView) v.findViewById(R.id.qqq);
		yearText = (TextView) v.findViewById(R.id.yearText);
		monthText = (TextView) v.findViewById(R.id.monthText);
		weekText = (TextView) v.findViewById(R.id.weekText);
		dayText = (TextView) v.findViewById(R.id.dayText);
		hourText = (TextView) v.findViewById(R.id.hourText);
		minText = (TextView) v.findViewById(R.id.minText);

		if (CommonStaticClass.langBng) {
			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0) {
				Typeface font = Typeface.createFromAsset(getAssets(),
						"Siyam Rupali ANSI.ttf");
				qqq.setTypeface(font);
				yearText.setTypeface(font);
				monthText.setTypeface(font);
				weekText.setTypeface(font);
				dayText.setTypeface(font);
				hourText.setTypeface(font);
				minText.setTypeface(font);

				weekText.setText("mÃŸvn");
				yearText.setText("eQi");
				monthText.setText("gvm");
				dayText.setText("w`b");
				hourText.setText("N›Uv");
				minText.setText("wgwbU ");
			}
			;
			qqq.setText(CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0 ? CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng()
					: CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQdesceng());
		} else {

			yearText.setText("Year");
			monthText.setText("Months");
			dayText.setText("Days");
			weekText.setText("Weeks");
			hourText.setText("Hours");
			minText.setText("Minutes ");
			yearText.setTypeface(null);
			monthText.setTypeface(null);
			weekText.setTypeface(null);
			dayText.setTypeface(null);
			hourText.setTypeface(null);
			minText.setTypeface(null);
			qqq.setTypeface(null);
			qqq.setText(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQdesceng());
		}

		prevButton = (Button) v.findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				userPressedPrevious(ParentActivity.this);
			}

		});
		saveNxtButton = (Button) v.findViewById(R.id.saveNxtButton);

		saveNxtButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				// if(IsValid())
				updateTableDataFrmYearToMin();

			}

		});
		clButton = (Button) v.findViewById(R.id.clButton);

		clButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				resetViewGroup((ViewGroup) v);
			}

		});
		notesButton = (Button) v.findViewById(R.id.btnNote);
		notesButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				FraNotes();

			}

		});

		yearHolder = (LinearLayout) v.findViewById(R.id.yearHolder);
		monthHolder = (LinearLayout) v.findViewById(R.id.monthHolder);
		weekHolder = (LinearLayout) v.findViewById(R.id.weekHolder);
		dayHolder = (LinearLayout) v.findViewById(R.id.dayHolder);
		hourHolder = (LinearLayout) v.findViewById(R.id.hourHolder);
		minHolder = (LinearLayout) v.findViewById(R.id.minHolder);

		yearBox = (EditText) v.findViewById(R.id.yearBox);
		monthBox = (EditText) v.findViewById(R.id.monthBox);
		weekBox = (EditText) v.findViewById(R.id.weekBox);
		dayBox = (EditText) v.findViewById(R.id.dayBox);
		hourBox = (EditText) v.findViewById(R.id.hourBox);
		minBox = (EditText) v.findViewById(R.id.minBox);

		String sql = "Select * from "
				+ CommonStaticClass.questionMap.get(
						CommonStaticClass.currentSLNo).getTablename()
				+ " where dataid='" + CommonStaticClass.dataId + "'";
		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.moveToFirst()) {
				do {
					String weekColumn = CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar()
							+ "weeks";

					String yearColumn = CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar()
							+ "years";

					String monthColumn = CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar()
							+ "months";

					String dayColumn = CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar()
							+ "days";
					String hourColumn = CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar()
							+ "hours";
					String minColumn = CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar()
							+ "mins";

					if (mCursor1.getColumnIndex(weekColumn) != -1) {
						weekHolder.setVisibility(View.VISIBLE);
						weekBox.setText(mCursor1.getString(mCursor1
								.getColumnIndex(weekColumn)));
					} else {
						weekHolder.setVisibility(View.GONE);
					}
					if (mCursor1.getColumnIndex(yearColumn) != -1) {
						yearHolder.setVisibility(View.VISIBLE);
						yearBox.setText(mCursor1.getString(mCursor1
								.getColumnIndex(yearColumn)));
					} else {
						yearHolder.setVisibility(View.GONE);
					}
					if (mCursor1.getColumnIndex(monthColumn) != -1) {
						monthHolder.setVisibility(View.VISIBLE);
						monthBox.setText(mCursor1.getString(mCursor1
								.getColumnIndex(monthColumn)));
					} else {
						monthHolder.setVisibility(View.GONE);
					}
					if (mCursor1.getColumnIndex(dayColumn) != -1) {
						dayHolder.setVisibility(View.VISIBLE);
						dayBox.setText(mCursor1.getString(mCursor1
								.getColumnIndex(dayColumn)));
					} else {
						dayHolder.setVisibility(View.GONE);
					}
					if (mCursor1.getColumnIndex(hourColumn) != -1) {
						hourHolder.setVisibility(View.VISIBLE);
						hourBox.setText(mCursor1.getString(mCursor1
								.getColumnIndex(hourColumn)));
					} else {
						hourHolder.setVisibility(View.GONE);
					}
					if (mCursor1.getColumnIndex(minColumn) != -1) {
						minHolder.setVisibility(View.VISIBLE);
						minBox.setText(mCursor1.getString(mCursor1
								.getColumnIndex(minColumn)));
					} else {
						minHolder.setVisibility(View.GONE);
					}
				} while (mCursor1.moveToNext());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}

		op = CommonStaticClass.findOptionsForThisQuestion(dbHelper,
				CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar());

		for (int i = 0; i < op.capEngList.size(); i++) {
			if (op.qidList.get(i).contains("weeks")) {
				weekHolder.setVisibility(View.VISIBLE);
				if (CommonStaticClass.langBng) {
					weekText.setText(op.capBngList.get(i).length() > 0 ? op.capBngList
							.get(i) : op.capEngList.get(i));
				} else {
					weekText.setText(op.capEngList.get(i));
				}
			}

			if (op.qidList.get(i).contains("years")) {
				yearHolder.setVisibility(View.VISIBLE);
				if (CommonStaticClass.langBng) {
					yearText.setText(op.capBngList.get(i).length() > 0 ? op.capBngList
							.get(i) : op.capEngList.get(i));
				} else {
					yearText.setText(op.capEngList.get(i));
				}
			}
			if (op.qidList.get(i).contains("months")) {
				monthHolder.setVisibility(View.VISIBLE);
				if (CommonStaticClass.langBng) {
					monthText
							.setText(op.capBngList.get(i).length() > 0 ? op.capBngList
									.get(i) : op.capEngList.get(i));
				} else {
					monthText.setText(op.capEngList.get(i));
				}
			}
			if (op.qidList.get(i).contains("weeks")) {
				weekHolder.setVisibility(View.VISIBLE);
				if (CommonStaticClass.langBng) {
					weekText.setText(op.capBngList.get(i).length() > 0 ? op.capBngList
							.get(i) : op.capEngList.get(i));
				} else {
					weekText.setText(op.capEngList.get(i));
				}
			}
			if (op.qidList.get(i).contains("days")) {
				dayHolder.setVisibility(View.VISIBLE);
				if (CommonStaticClass.langBng) {
					dayText.setText(op.capBngList.get(i).length() > 0 ? op.capBngList
							.get(i) : op.capEngList.get(i));
				} else {
					dayText.setText(op.capEngList.get(i));
				}
			}
			if (op.qidList.get(i).contains("hours")) {
				hourHolder.setVisibility(View.VISIBLE);
				if (CommonStaticClass.langBng) {
					hourText.setText(op.capBngList.get(i).length() > 0 ? op.capBngList
							.get(i) : op.capEngList.get(i));
				} else {
					hourText.setText(op.capEngList.get(i));
				}
			}
			if (op.qidList.get(i).contains("mins")) {
				minHolder.setVisibility(View.VISIBLE);

				if (CommonStaticClass.langBng) {
					minText.setText(op.capBngList.get(i).length() > 0 ? op.capBngList
							.get(i) : op.capEngList.get(i));
				} else {
					minText.setText(op.capEngList.get(i));
				}
			}
		}

		// c606

		if (CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo)
				.getQvar().equalsIgnoreCase("q4")) {
			monthBox.addTextChangedListener(new TextWatcher() {

				public void onTextChanged(CharSequence s, int start,
						int before, int count) {

					if (s.length() > 0) {
						if (s.toString() != "") {
							yearBox.setText("");
						}
					}

				}

				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) { // TODO Auto-generated method
												// stub

				}

				public void afterTextChanged(Editable s) { // TODO
															// Auto-generated

				}
			});

			yearBox.addTextChangedListener(new TextWatcher() {

				public void onTextChanged(CharSequence s, int start,
						int before, int count) {

					if (s.length() > 0) {
						if (s.toString() != "") {
							monthBox.setText("");
						}
					}

				}

				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) { // TODO Auto-generated method
												// stub

				}

				public void afterTextChanged(Editable s) { // TODO
															// Auto-generated

				}
			});

		}

		//
		if (CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo)
				.getQvar().equalsIgnoreCase("c607b")) {
			monthBox.addTextChangedListener(new TextWatcher() {

				public void onTextChanged(CharSequence s, int start,
						int before, int count) {

					if (s.length() > 0) {
						if (s.toString() != "") {
							dayBox.setText("");
						}
					}

				}

				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
					// TODO Auto-generated method stub

				}

				public void afterTextChanged(Editable s) {
					// TODO Auto-generated method stub

				}
			});

			dayBox.addTextChangedListener(new TextWatcher() {

				public void onTextChanged(CharSequence s, int start,
						int before, int count) {

					if (s.length() > 0) {
						if (s.toString() != "") {
							monthBox.setText("");
						}
					}

				}

				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
					// TODO Auto-generated method stub

				}

				public void afterTextChanged(Editable s) {
					// TODO Auto-generated method stub

				}
			});

		}

		// q1_3
		if (CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo)
				.getQvar().equalsIgnoreCase("c1_3")) {
			monthBox.addTextChangedListener(new TextWatcher() {

				public void onTextChanged(CharSequence s, int start,
						int before, int count) {

					/*
					 * if (s.length() > 0) { if (s.toString() != "") {
					 * dayBox.setText(""); if(Integer.parseInt(s.toString())>=1
					 * && Integer.parseInt(s.toString())<=36) {
					 * 
					 * } else { if(s.toString().length()==2) {
					 * CommonStaticClass.showMyAlert(con, "Message",
					 * "Month should be between 1 to 36"); monthBox.setText("");
					 * monthBox.requestFocus(); return; } } } }
					 */

				}

				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
					// TODO Auto-generated method stub

				}

				public void afterTextChanged(Editable s) {

				}
			});

			dayBox.addTextChangedListener(new TextWatcher() {

				public void onTextChanged(CharSequence s, int start,
						int before, int count) {

					/*
					 * if (s.length() > 0) { if (s.toString() != "") {
					 * monthBox.setText("");
					 * 
					 * if(Integer.parseInt(s.toString())>=1 &&
					 * Integer.parseInt(s.toString())<=30) {
					 * 
					 * } else { if(s.toString().length()==2) {
					 * CommonStaticClass.showMyAlert(con, "Message",
					 * "Day should be between 1 to 30"); dayBox.setText("");
					 * dayBox.requestFocus(); return; } }
					 * 
					 * } }
					 */

				}

				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
					// TODO Auto-generated method stub

				}

				public void afterTextChanged(Editable s) {

				}
			});

		}
	}

	private boolean IsValidFrmYearToMin() {
		String sql = "";
		Cursor mCursor1 = null;

		if (CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo)
				.getQvar().equalsIgnoreCase("q29")
				|| CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar()
						.equalsIgnoreCase("q30")) {

			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQvar()
					.equalsIgnoreCase("q29")) {
				sql = "select * from tblMainQues where dataid='"
						+ CommonStaticClass.dataId + "'";
				mCursor1 = dbHelper.getQueryCursor(sql);
				CommonStaticClass
						.showMyAlert(
								con,
								"Not Correct",
								""
										+ mCursor1.getString(mCursor1
												.getColumnIndex("q12")));

			} else if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQvar()
					.equalsIgnoreCase("q30")) {
				sql = "select * from tblMainQuesMc where dataid='"
						+ CommonStaticClass.dataId + "'";
				mCursor1 = dbHelper.getQueryCursor(sql);
				// CommonStaticClass.showMyAlert(con, "Not Correct",
				// "Wrong year, must be less than qustion 29."+
				// yearBox.getText().toString());
				if (Integer.parseInt(yearBox.getText().toString()) >= Integer
						.parseInt(mCursor1.getString(mCursor1
								.getColumnIndex("q29years")))) {
					CommonStaticClass.showMyAlert(con, "Not Correct",
							"Wrong year, must be less than qustion 29.");
					return false;
				}
			}
		}

		return true;
	}

	private void updateTableDataFrmYearToMin() {
		Cursor mCursor = null;
		// TODO Auto-generated method stub
		if (yearHolder.getVisibility() == View.VISIBLE) {
			if (!(yearBox.getText().toString().length() > 0)) {
				CommonStaticClass.showMyAlert(con, "Error",
						"You must provide correct data to proceed");
				return;
			}
		}
		if (monthHolder.getVisibility() == View.VISIBLE) {
			if (!(monthBox.getText().toString().length() > 0)) {
				CommonStaticClass.showMyAlert(con, "Error",
						"You must provide correct data to proceed");
				return;
			}
		}
		if (weekHolder.getVisibility() == View.VISIBLE) {
			if (!(weekBox.getText().toString().length() > 0)) {
				CommonStaticClass.showMyAlert(con, "Error",
						"You must provide correct data to proceed");
				return;
			}
		}
		if (dayHolder.getVisibility() == View.VISIBLE) {
			if (!(dayBox.getText().toString().length() > 0)) {
				CommonStaticClass.showMyAlert(con, "Error",
						"You must provide correct data to proceed");
				return;
			}
		}

		if (hourHolder.getVisibility() == View.VISIBLE) {
			if (!(hourBox.getText().toString().length() > 0)) {
				CommonStaticClass.showMyAlert(con, "Error",
						"You must provide correct data to proceed");
				return;
			}
		}
		if (minHolder.getVisibility() == View.VISIBLE) {
			if (!(minBox.getText().toString().length() > 0)) {
				CommonStaticClass.showMyAlert(con, "Error",
						"You must provide correct data to proceed");
				return;
			}
		}
		
		//code by imtiaz khan
		
		if((CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo)
				.getQvar().equalsIgnoreCase("q4_9")
				|| CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo)
				.getQvar().equalsIgnoreCase("q4_12")
				) && 
				!((Integer.parseInt(hourBox.getText().toString()) < 24 
						||Integer.parseInt(hourBox.getText().toString()) == 99) 
					&& 
				(Integer.parseInt(minBox.getText().toString())<60 
						|| Integer.parseInt(minBox.getText().toString()) == 99)
					&& 
				(Integer.parseInt(dayBox.getText().toString())<30)
						|| Integer.parseInt(dayBox.getText().toString()) == 99)
				)
		{
			
					CommonStaticClass.showMyAlert(con, "Error",
						"Wrong Input.Give corretct input " +
						"(99 for DK)");
					return;
			
		}

		String sql = "";

		sql = "UPDATE "
				+ CommonStaticClass.questionMap.get(
						CommonStaticClass.currentSLNo).getTablename() + " SET ";
		int i = 0;
		// +CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo).getQvar()+"='"+code+"' where dataid='"+CommonStaticClass.dataId+"'";
		if (yearHolder.getVisibility() == View.VISIBLE) {
			year = yearBox.getText().toString();
			sql += "years = '" + year + "'";
			i++;
		}
		if (monthHolder.getVisibility() == View.VISIBLE) {
			month = monthBox.getText().toString();
			if (month.equalsIgnoreCase("88") && qName.equalsIgnoreCase("q221")) {
				CommonStaticClass.qskipList.add("q1003");
			}
			sql += (i > 0 ? "," : "")

			+ "months = '" + month + "'";
			i++;
		}
		if (weekHolder.getVisibility() == View.VISIBLE) {
			week = weekBox.getText().toString();
			sql += (i > 0 ? "," : "")
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar()
					+ "weeks = '" + week + "'";
			i++;
		}
		if (dayHolder.getVisibility() == View.VISIBLE) {
			day = dayBox.getText().toString();
			sql += (i > 0 ? "," : "")
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar()
					+ "days = '" + day + "'";
			i++;
		}
		if (hourHolder.getVisibility() == View.VISIBLE) {
			hour = hourBox.getText().toString();
			sql += (i > 0 ? "," : "")
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar()
					+ "hours = '" + hour + "'";
			i++;
		}
		if (minHolder.getVisibility() == View.VISIBLE) {
			min = minBox.getText().toString();
			sql += (i > 0 ? "," : "")
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar()
					+ "mins = '" + min + "'";
			i++;
		}
		sql += "where dataid='" + CommonStaticClass.dataId + "'";

		if (dbHelper.executeDMLQuery(sql)) {

			monthText.setText("");
			dayText.setText("");
			
			//code by imtiaz khan
			if(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQvar().equalsIgnoreCase("q4_12")
					&& getChoiceValue("q4_11_1") != 1
					&& getChoiceValue("q4_11_2") != 1
					&& getChoiceValue("q4_11_11") != 1)
			{
				
				CommonStaticClass.findOutNextSLNo(qName, "q4_14");
				CommonStaticClass.nextQuestion(ParentActivity.this);
				
			}
			else
			{
				CommonStaticClass.findOutNextSLNo(
						CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQvar(),
						CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQnext1());
				CommonStaticClass.nextQuestion(ParentActivity.this);
			}
		}

	}

	private static void resetViewGroup(ViewGroup viewGroup) {
		int nrOfChildren = viewGroup.getChildCount();
		for (int i = 0; i < nrOfChildren; i++) {
			View view = viewGroup.getChildAt(i);
			resetView(view);
			if (view instanceof ViewGroup) {
				resetViewGroup((ViewGroup) view);
			}
			if (view instanceof RadioGroup) {
				((RadioGroup) view).clearCheck();
			}
		}

	}

	private static void resetView(View view) {

		if (view instanceof Spinner) {
			((Spinner) view).setSelection(0);
		}

		if (view instanceof RadioButton) {
			((RadioButton) view).setChecked(false);
		}
		if (view instanceof CheckBox) {
			((CheckBox) view).setChecked(false);
		}
		if (view instanceof EditText) {
			((EditText) view).setText("");
		}
	}

	boolean adjustForEdit = false;
	boolean adjustForSpinner = false;
	int allchecked = 0;

	private void CreateMultipleCheckCombotwoHeader(final ViewGroup v) {
		// setting up the width for column header
		if (qName.equalsIgnoreCase("q65")) {
			((TextView) v.findViewById(R.id.txtcustom1))
					.setWidth((dm.widthPixels / 4) * 2);
			((TextView) v.findViewById(R.id.txtcustom2))
					.setWidth((dm.widthPixels / 4));
			((TextView) v.findViewById(R.id.txtcustom3))
					.setWidth((dm.widthPixels / 4));
			//
			if (CommonStaticClass.langBng) {
				((TextView) v.findViewById(R.id.txtcustom1))
						.setText("RvqMvi bvg");
				((TextView) v.findViewById(R.id.txtcustom1)).setTypeface(font);
				((TextView) v.findViewById(R.id.txtcustom2))
						.setText("RevB Kiv");
				((TextView) v.findViewById(R.id.txtcustom2)).setTypeface(font);
				((TextView) v.findViewById(R.id.txtcustom3))
						.setText("cÂ«wÂµqvRvZKib");
				((TextView) v.findViewById(R.id.txtcustom3)).setTypeface(font);
			} else {
				((TextView) v.findViewById(R.id.txtcustom1))
						.setText("Location");
				((TextView) v.findViewById(R.id.txtcustom1)).setTypeface(null);
				((TextView) v.findViewById(R.id.txtcustom2))
						.setText("Slaughtered");
				((TextView) v.findViewById(R.id.txtcustom2)).setTypeface(null);
				((TextView) v.findViewById(R.id.txtcustom3))
						.setText("Processed");
				((TextView) v.findViewById(R.id.txtcustom3)).setTypeface(null);
			}
		}
	}

	private void loadGuiMultipleCheckCombotwo(final ViewGroup v) {
		// TODO Auto-generated method stub
		// checkDbHasPreviousDataForThisHouseHold();
		adjustForSpinner = false;
		adjustForEdit = false;
		aaa1.clear();
		aaa2.clear();
		aaa3.clear();
		aaachecklist.clear();
		mEditStrings.clear();

		optionList1 = new ArrayList<String>();
		optionCodeList1 = new ArrayList<Integer>();

		optionList2 = new ArrayList<String>();
		optionCodeList2 = new ArrayList<Integer>();

		optionList3 = new ArrayList<String>();
		optionCodeList3 = new ArrayList<Integer>();

		qqq = (TextView) v.findViewById(R.id.qqq);

		qName = CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar();
		if (qName.equalsIgnoreCase("p87b") || qName.equalsIgnoreCase("p87c")
				|| qName.equalsIgnoreCase("p87d")
				|| qName.equalsIgnoreCase("p87e")) {// put the name of question
													// from
			// qvar
			adjustForEdit = true;
		}
		if (qName.equalsIgnoreCase("")) { // put the name of question from
											// qvar
			adjustForSpinner = true;
		} else {
			adjustForSpinner = false;
		}
		if (CommonStaticClass.langBng) {
			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0) {
				qqq.setTypeface(font);
			}

			qqq.setText(CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0 ? CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng()
					: CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQdesceng());

		} else {
			qqq.setText(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQdesceng());
			qqq.setTypeface(null);
		}

		checkBoxHolder = (LinearLayout) v.findViewById(R.id.checkBoxHolder);
		checkBoxHolder.removeAllViews();
		op = CommonStaticClass.findOptionsForThisQuestion(dbHelper,
				CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar());

		LinearLayout.LayoutParams lnlParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams layoutParamForcheck = new LinearLayout.LayoutParams(
				(dm.widthPixels / 4) * 2,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams layoutParamForSpin1 = new LinearLayout.LayoutParams(
				(dm.widthPixels / 4), LinearLayout.LayoutParams.WRAP_CONTENT);

		LinearLayout.LayoutParams layoutParamForSpin2 = new LinearLayout.LayoutParams(
				(dm.widthPixels / 4), LinearLayout.LayoutParams.WRAP_CONTENT);

		LinearLayout.LayoutParams layoutParamForEditOrSpinner = new LinearLayout.LayoutParams(
				(dm.widthPixels / 4), LinearLayout.LayoutParams.WRAP_CONTENT);

		CreateMultipleCheckCombotwoHeader(v);

		optionList1.add("");
		optionCodeList1.add(-1);

		optionList2.add("");
		optionCodeList2.add(-1);

		optionList3.add("");
		optionCodeList3.add(-1);

		for (int i = 0; i < op.codeList.size(); i++) {

			if (op.qidList.get(i).contains("_Options_1")) {
				Log.e("op.qidList.get(i)", op.qidList.get(i));
				if (CommonStaticClass.langBng) {
					optionList1.add(op.capBngList.get(i));
				} else {
					optionList1.add(op.capEngList.get(i));
				}
				optionCodeList1.add(op.codeList.get(i));
				continue;
			} else if (op.qidList.get(i).contains("_Options_2")) {

				if (CommonStaticClass.langBng) {
					optionList2.add(op.capBngList.get(i));
				} else {
					optionList2.add(op.capEngList.get(i));
				}
				optionCodeList2.add(op.codeList.get(i));
				continue;
			} else if (op.qidList.get(i).contains("_Options_3")) {

				if (CommonStaticClass.langBng) {
					optionList3.add(op.capBngList.get(i));
				} else {
					optionList3.add(op.capEngList.get(i));
				}
				optionCodeList3.add(op.codeList.get(i));
				continue;
			}

			aaa1.add(-1);
			aaa2.add(-1);
			aaa3.add(-1);
			aaachecklist.add(-1);
			if (adjustForEdit)
				mEditStrings.add("-1");
		}

		for (int i = 0; i < op.codeList.size(); i++) {

			Log.e("op.qidList.get(i)", op.qidList.get(i));
			if (op.qidList.get(i).contains("_Options_1")
					|| op.qidList.get(i).contains("_Options_2")
					|| op.qidList.get(i).contains("_Options_3")) {
				continue;
			}
			LinearLayout ln = new LinearLayout(this);
			ln.setOrientation(LinearLayout.HORIZONTAL);
			final CheckBox checkButton = new CheckBox(this);
			if (CommonStaticClass.langBng) {
				checkButton.setTypeface(font);
				checkButton
						.setText(op.capBngList.get(i).length() > 0 ? op.capBngList
								.get(i) : op.capEngList.get(i));
			} else {
				checkButton.setText(op.capEngList.get(i));
			}
			checkButton.setId(i);
			final Spinner spinner1 = new Spinner(this);
			final Spinner spinner2 = new Spinner(this);
			final Spinner spinner3 = new Spinner(this);
			final EditText editforwater = new EditText(this);
			editforwater.setTag(i);
			layoutParamForSpin1.weight = 1;
			if (adjustForEdit) {
				layoutParamForEditOrSpinner.weight = 1;
			}
			if (adjustForSpinner) {
				layoutParamForEditOrSpinner.weight = 1;
			}
			layoutParamForSpin2.weight = 1;
			if (adjustForSpinner) {
				ln.addView(spinner3, 0, layoutParamForEditOrSpinner);
			}

			if (!qName.equalsIgnoreCase("p87b"))
				ln.addView(spinner2, 0, layoutParamForSpin2);

			if (adjustForEdit) {
				editforwater.setRawInputType(InputType.TYPE_CLASS_TEXT);
				ln.addView(editforwater, 0, layoutParamForEditOrSpinner);

			}

			ln.addView(spinner1, 0, layoutParamForSpin1);

			ln.addView(checkButton, 0, layoutParamForcheck);

			ArrayAdapter adapter1, adapter2, adapter3 = null;

			if (CommonStaticClass.langBng) {
				adapter1 = new SpinAdapter(this, optionList1, true);
				adapter2 = new SpinAdapter(this, optionList2, true);
				if (adjustForSpinner) {
					adapter3 = new SpinAdapter(this, optionList3, true);
				}
			} else {
				adapter1 = new ArrayAdapter(this,
						android.R.layout.simple_spinner_item, optionList1);
				adapter2 = new ArrayAdapter(this,
						android.R.layout.simple_spinner_item, optionList2);
				if (adjustForSpinner) {
					adapter3 = new ArrayAdapter(this,
							android.R.layout.simple_spinner_item, optionList3);
				}
			}

			adapter1.setDropDownViewResource(CommonStaticClass.langBng ? R.layout.checkedspintextview
					: android.R.layout.simple_spinner_dropdown_item);

			adapter2.setDropDownViewResource(CommonStaticClass.langBng ? R.layout.checkedspintextview
					: android.R.layout.simple_spinner_dropdown_item);
			if (adjustForSpinner) {
				adapter3.setDropDownViewResource(CommonStaticClass.langBng ? R.layout.checkedspintextview
						: android.R.layout.simple_spinner_dropdown_item);
			}

			spinner1.setAdapter(adapter1);
			spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {

				public void onItemSelected(AdapterView<?> parent, View view,
						int pos, long id) {
					// TODO Auto-generated method stub
					if (optionCodeList1.size() > pos)
						aaa1.set(checkButton.getId(), optionCodeList1.get(pos));

				}

				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					// aaa.set(checkButton.getId(), -1);
				}

			});

			if (spinner2.getVisibility() == View.VISIBLE) {
				spinner2.setAdapter(adapter2);
				spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> parent,
							View view, int pos, long id) {
						// TODO Auto-generated method stub
						if (optionCodeList2.size() > pos)
							aaa2.set(checkButton.getId(),
									optionCodeList2.get(pos));

					}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						// aaa.set(checkButton.getId(), -1);
					}

				});
			}

			if (adjustForSpinner) {
				spinner3.setAdapter(adapter3);
				spinner3.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> parent,
							View view, int pos, long id) {
						// TODO Auto-generated method stub
						if (optionCodeList3.size() > pos)
							aaa3.set(checkButton.getId(),
									optionCodeList3.get(pos));

					}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						// aaa.set(checkButton.getId(), -1);
					}

				});
			}
			if (adjustForEdit) {
				editforwater.addTextChangedListener(new TextWatcher() {

					public void onTextChanged(CharSequence arg0, int arg1,
							int arg2, int arg3) {
						// TODO Auto-generated method stub

					}

					public void beforeTextChanged(CharSequence arg0, int arg1,
							int arg2, int arg3) {
						// TODO Auto-generated method stub

					}

					public void afterTextChanged(Editable arg0) {
						// TODO Auto-generated method stub
						mEditStrings.set((Integer) editforwater.getTag(),
								arg0.toString());
					}
				});
			}

			checkBoxHolder.addView(ln, 0, lnlParams);

			checkButton
					.setOnCheckedChangeListener(new OnCheckedChangeListener() {

						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							// TODO Auto-generated method stub
							if (isChecked) {
								Log.e("id :", "" + checkButton.getId());
								aaachecklist.set(checkButton.getId(), 1);
								// allchecked = allchecked+1;
								spinner1.setVisibility(View.VISIBLE);
								if (!qName.equalsIgnoreCase("p87b")) {
									spinner2.setVisibility(View.VISIBLE);
								}

								if (adjustForSpinner) {
									spinner3.setVisibility(View.VISIBLE);
								}
								if (adjustForEdit) {
									editforwater.setVisibility(View.VISIBLE);
									mEditStrings.set(
											(Integer) editforwater.getTag(),
											"::");
								}

							} else {
								aaachecklist.set(checkButton.getId(), -1);
								spinner1.setVisibility(View.INVISIBLE);
								if (!qName.equalsIgnoreCase("p87b")) {
									spinner2.setVisibility(View.INVISIBLE);
								}
								if (adjustForSpinner) {
									spinner3.setVisibility(View.INVISIBLE);
								}
								if (adjustForEdit) {
									editforwater.setVisibility(View.INVISIBLE);
									mEditStrings.set(
											(Integer) editforwater.getTag(),
											"-1");
								}
							}
						}
					});

			spinner1.setVisibility(View.INVISIBLE);
			if (!qName.equalsIgnoreCase("p87b")) {
				spinner2.setVisibility(View.INVISIBLE);
			}
			if (adjustForSpinner) {
				spinner3.setVisibility(View.INVISIBLE);
			}
			if (adjustForEdit) {
				editforwater.setVisibility(View.INVISIBLE);
			}
			if (adjustForEdit) {
				selectCheckAndCombo(op.qidList.get(i), checkButton, spinner1,
						editforwater, spinner2);
			} else {
				if (adjustForSpinner) {
					selectCheckAndCombo(op.qidList.get(i), checkButton,
							spinner1, spinner2, spinner3);
				} else {
					selectCheckAndCombo(op.qidList.get(i), checkButton,
							spinner1, spinner2);
				}
			}

		}

		prevButton = (Button) v.findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vvv) {
				// TODO Auto-generated method stub
				userPressedPrevious(ParentActivity.this);
			}

		});
		saveNxtButton = (Button) v.findViewById(R.id.saveNxtButton);

		saveNxtButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vvv) {
				// TODO Auto-generated method stub
				updateTableMultipleCheckComboTwo(checkBoxHolder, adjustForEdit,
						adjustForSpinner);

			}

		});
		clButton = (Button) v.findViewById(R.id.clButton);
		clButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vvv) {
				// TODO Auto-generated method stub
				resetViewGroup((ViewGroup) v.findViewById(R.id.rootView));
			}

		});
		notesButton = (Button) v.findViewById(R.id.btnNote);
		notesButton.setOnClickListener(new OnClickListener() {

			public void onClick(View vvv) {
				// TODO Auto-generated method stub
				FraNotes();

			}

		});
	}

	private void spinnerVisibleButNotSeleted(ViewGroup viewGroup) {
		int nrOfChildren = viewGroup.getChildCount();
		for (int i = 0; i < nrOfChildren; i++) {
			View view = viewGroup.getChildAt(i);

			if (view instanceof Spinner
					&& (view.getVisibility() == View.VISIBLE)) {
				if (((Spinner) view).getSelectedItemPosition() == 0) {

					spinnerOK = false;

				}
			}
			if (view instanceof ViewGroup) {
				spinnerVisibleButNotSeleted((ViewGroup) view);
			}
		}
	}

	// private boolean checkIfSingleOptionIsChecked() {
	// for (int i = 0; i < aaachecklist.size(); i++) {
	// if (!(aaachecklist.get(i) == -1)) {
	// return true;
	// }
	// }
	// return false;
	// }
	private boolean checkIfAllOptionIsChecked() {
		// for (int i = 0; i < aaachecklist.size(); i++) {
		// if ((aaachecklist.get(i) == -1)) {
		// Log.e("aaachecklist.get(i)","aaachecklist.get(i): "+aaachecklist.get(i));
		// }
		// }
		// return false;
		/*
		 * if (aaachecklist.contains(-1)) { return false; } else {
		 */
		return true;
		// }
	}

	// private Boolean CheckBoxNotSeletedFrmMultipleCheckCombotwo() {
	// if(aaachecklist.size()!=allchecked)
	// {
	// return spinnerOK = false;
	// }
	// return spinnerOK;
	// /*for (int i = 0; i < aaachecklist.size(); i++) {
	// if (aaachecklist.get(i) == -1) {
	// return spinnerOK = false;
	//
	// }
	// }
	// return spinnerOK;*/
	//
	// }

	private void updateTableMultipleCheckComboTwo(ViewGroup checkBoxHolder,
			boolean adjustEdit, boolean adjustSpin) {
		// TODO Auto-generated method stub
		spinnerOK = true;

		spinnerVisibleButNotSeleted(checkBoxHolder);
		if (adjustEdit) {
			if (checkIfSingleEditIsBlank()) {
				CommonStaticClass
						.showMyAlert(con, "Please enter the value",
								"Blank value is not accepted,please put some value to proceed");
				return;
			}
		}
		/*
		 * if (CheckBoxNotSeletedFrmMultipleCheckCombotwo() == false) {
		 * CommonStaticClass.showMyAlert(con, "Please Select",
		 * "Please select all checkbox to proceed"); return; }
		 */

		if (spinnerOK) {
			// if (checkIfSingleOptionIsChecked()) {
			if (checkIfAllOptionIsChecked()) {
				String sql = "UPDATE "
						+ CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getTablename()
						+ " SET ";
				for (int i = 0; i < op.codeList.size(); i++) {
					if (op.qidList.get(i).contains("_Options")) {
						continue;
					}
					if (op.qidList.get(i + 1).contains("_Options")) {
						if (adjustEdit) {
							sql += op.qidList.get(i) + "_1 = '"
									+ (aaachecklist.get(i) == -1 ? 0 : 1)
									+ "',";
							sql += op.qidList.get(i) + "_2 = '"
									+ mEditStrings.get(i) + "',";

							sql += op.qidList.get(i)
									+ "_3 = '"
									+ ((aaachecklist.get(i) == -1 || aaachecklist
											.get(i) == 0) ? "" : aaa1.get(i))
									+ "',";

							sql += op.qidList.get(i)
									+ "_4 = '"
									+ ((aaachecklist.get(i) == -1 || aaachecklist
											.get(i) == 0) ? "" : aaa2.get(i))
									+ "' ";
						} else {
							if (adjustSpin) {
								sql += op.qidList.get(i) + "_1 = '"
										+ (aaachecklist.get(i) == -1 ? 0 : 1)
										+ "',";
								sql += op.qidList.get(i)
										+ "_2 = '"
										+ ((aaachecklist.get(i) == -1 || aaachecklist
												.get(i) == 0) ? "" : aaa1
												.get(i)) + "',";
								sql += op.qidList.get(i)
										+ "_3 = '"
										+ ((aaachecklist.get(i) == -1 || aaachecklist
												.get(i) == 0) ? "" : aaa2
												.get(i)) + "',";
								sql += op.qidList.get(i)
										+ "_4 = '"
										+ ((aaachecklist.get(i) == -1 || aaachecklist
												.get(i) == 0) ? "" : aaa3
												.get(i)) + "' ";
							} else {
								sql += op.qidList.get(i) + "_1 = '"
										+ (aaachecklist.get(i) == -1 ? 0 : 1)
										+ "',";
								sql += op.qidList.get(i)
										+ "_2 = '"
										+ ((aaachecklist.get(i) == -1 || aaachecklist
												.get(i) == 0) ? "" : aaa1
												.get(i)) + "',";
								sql += op.qidList.get(i)
										+ "_3 = '"
										+ ((aaachecklist.get(i) == -1 || aaachecklist
												.get(i) == 0) ? "" : aaa2
												.get(i)) + "' ";
							}
						}

						break;
					}
					if (adjustEdit) {
						sql += op.qidList.get(i) + "_1 = '"
								+ (aaachecklist.get(i) == -1 ? 0 : 1) + "',";
						sql += op.qidList.get(i) + "_2 = '"
								+ mEditStrings.get(i) + "',";
						sql += op.qidList.get(i)
								+ "_3 = '"
								+ ((aaachecklist.get(i) == -1 || aaachecklist
										.get(i) == 0) ? "" : aaa1.get(i))
								+ "',";
						sql += op.qidList.get(i)
								+ "_4 = '"
								+ ((aaachecklist.get(i) == -1 || aaachecklist
										.get(i) == 0) ? "" : aaa2.get(i))
								+ "',";
					} else {
						if (adjustSpin) {
							sql += op.qidList.get(i) + "_1 = '"
									+ (aaachecklist.get(i) == -1 ? 0 : 1)
									+ "',";
							sql += op.qidList.get(i)
									+ "_2 = '"
									+ ((aaachecklist.get(i) == -1 || aaachecklist
											.get(i) == 0) ? "" : aaa1.get(i))
									+ "',";
							sql += op.qidList.get(i)
									+ "_3 = '"
									+ ((aaachecklist.get(i) == -1 || aaachecklist
											.get(i) == 0) ? "" : aaa2.get(i))
									+ "',";
							sql += op.qidList.get(i)
									+ "_4 = '"
									+ ((aaachecklist.get(i) == -1 || aaachecklist
											.get(i) == 0) ? "" : aaa3.get(i))
									+ "',";
						} else {
							sql += op.qidList.get(i) + "_1 = '"
									+ (aaachecklist.get(i) == -1 ? 0 : 1)
									+ "',";
							sql += op.qidList.get(i)
									+ "_2 = '"
									+ ((aaachecklist.get(i) == -1 || aaachecklist
											.get(i) == 0) ? "" : aaa1.get(i))
									+ "',";
							sql += op.qidList.get(i)
									+ "_3 = '"
									+ ((aaachecklist.get(i) == -1 || aaachecklist
											.get(i) == 0) ? "" : aaa2.get(i))
									+ "',";
						}

					}
				}
				sql += "where dataid='" + CommonStaticClass.dataId + "'";

				if (dbHelper.executeDMLQuery(sql)) {
					if (!gotoskip()) {
						CommonStaticClass.findOutNextSLNo(
								CommonStaticClass.questionMap.get(
										CommonStaticClass.currentSLNo)
										.getQvar(),
								CommonStaticClass.questionMap.get(
										CommonStaticClass.currentSLNo)
										.getQnext1());
						CommonStaticClass.nextQuestion(ParentActivity.this);
					}
				}
			} else {
				CommonStaticClass
						.showMyAlert(con, "Please check one!!!",
								"You didn't check any answer please select one to preceed");
			}
		} else {
			CommonStaticClass
					.showMyAlert(con, "Please select item!!!",
							"You didn't select any item from list, Please select one to proceed");
		}

	}

	private boolean gotoskip() {

		if (qName.equalsIgnoreCase("p87a")) {
			CommonStaticClass.qskipList.remove("p87b");
			CommonStaticClass.qskipList.remove("p87bcount");
			CommonStaticClass.qskipList.remove("p87b_other");

			CommonStaticClass.qskipList.remove("p87c");
			CommonStaticClass.qskipList.remove("p87ccount");
			CommonStaticClass.qskipList.remove("p87c_other");

			CommonStaticClass.qskipList.remove("p87d");
			CommonStaticClass.qskipList.remove("p87dcount");
			CommonStaticClass.qskipList.remove("p87d_other");

			CommonStaticClass.qskipList.remove("p87e");
			CommonStaticClass.qskipList.remove("p87ecount");
			CommonStaticClass.qskipList.remove("p87e_other");

			CommonStaticClass.qskipList.remove("p87f");
			CommonStaticClass.qskipList.remove("p87fcount");
			CommonStaticClass.qskipList.remove("p87f_other");

			if (!getSkip("p87a_1", "tblMainQuesMc").equalsIgnoreCase("1")) {
				CommonStaticClass.qskipList.add("p87b");
				CommonStaticClass.qskipList.add("p87bcount");
				CommonStaticClass.qskipList.add("p87b_other");

			}

			if (!getSkip("p87a_2", "tblMainQuesMc").equalsIgnoreCase("1")) {
				CommonStaticClass.qskipList.add("p87c");
				CommonStaticClass.qskipList.add("p87ccount");
				CommonStaticClass.qskipList.add("p87c_other");

			}

			if (!getSkip("p87a_3", "tblMainQuesMc").equalsIgnoreCase("1")) {
				CommonStaticClass.qskipList.add("p87d");
				CommonStaticClass.qskipList.add("p87dcount");
				CommonStaticClass.qskipList.add("p87d_other");

			}

			if (!getSkip("p87a_4", "tblMainQuesMc").equalsIgnoreCase("1")) {
				CommonStaticClass.qskipList.add("p87e");
				CommonStaticClass.qskipList.add("p87ecount");
				CommonStaticClass.qskipList.add("p87e_other");

			}

			if (!getSkip("p87a_5", "tblMainQuesMc").equalsIgnoreCase("1")) {
				CommonStaticClass.qskipList.add("p87f");
				CommonStaticClass.qskipList.add("p87fcount");
				CommonStaticClass.qskipList.add("p87f_other");

			}

			return false;

		}

		if (CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo)
				.getQnext1().equalsIgnoreCase("SecP2")) {
			if (CommonStaticClass.qskipList.contains("SecP2")
					&& CommonStaticClass.qskipList.contains("SecP3")) {

				if (IfCompletedAllMembersFrmMultipleChoice()) {
					showUserFinishDialogFrmMultipleChoice();
					return true;
				} else {
					CommonStaticClass.currentSLNo = 31;
					CommonStaticClass.nextQuestion(ParentActivity.this);
					return true;
				}

			}
		}
		if (CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo)
				.getQnext1().equalsIgnoreCase("END")) {

			showUserFinishDialogFrmMultipleChoice();
			return true;
		}

		/*
		 * else {
		 * 
		 * CommonStaticClass.findOutNextSLNo( qName,
		 * CommonStaticClass.questionMap.get(
		 * CommonStaticClass.currentSLNo).getQnext1());
		 * CommonStaticClass.nextQuestion(ParentActivity.this); }
		 */

		if (qName.equalsIgnoreCase("q61")) {
			String v1 = getSkip("q61_7", "tblMainQuesMc");
			if (v1 != null) {
				if (v1.length() > 0) {
					if (v1.equalsIgnoreCase("1") || v1.equalsIgnoreCase("2")
							|| v1.equalsIgnoreCase("3")
							|| v1.equalsIgnoreCase("5")
							|| v1.equalsIgnoreCase("6")) {
						CommonStaticClass.findOutNextSLNo(
								CommonStaticClass.questionMap.get(
										CommonStaticClass.currentSLNo)
										.getQvar(), "q61_other");
						CommonStaticClass.nextQuestion(ParentActivity.this);
						return false;
					}
				}
			}
		}
		if (qName.equalsIgnoreCase("q2_6")) {
			// String s =
			// "SELECT q2_6_12 FROM tblMainques WHERE dataid = '" +
			// CommonStaticClass.dataId +"'";
			String v1 = dbHelper.GetSingleColumnData("q2_6_12");

			if (v1.equalsIgnoreCase("2") || v1.equalsIgnoreCase("3")) {
				CommonStaticClass.findOutNextSLNo(CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar(),
						"q2_6_other");

				CommonStaticClass.nextQuestion(ParentActivity.this);
				return false;
			}

		}

		if (qName.equalsIgnoreCase("q61"))

		{

			String GtSkip = getSkip("q61_7", "tblMainQuesMc");

			if (GtSkip.equalsIgnoreCase("1"))

			{

				CommonStaticClass.findOutNextSLNo(qName, "q61_other");

				CommonStaticClass.nextQuestion(ParentActivity.this);
				return false;
			}

		}

		if (qName.equalsIgnoreCase("q7_18_1"))

		{

			String GtSkip = getSkip("q7_18_1_c", "tblMainQuesMc");

			if (GtSkip.equalsIgnoreCase("1"))

			{

				CommonStaticClass.findOutNextSLNo(qName, "q7_18_1_other");

				CommonStaticClass.nextQuestion(ParentActivity.this);
				return false;
			}

		}

		if (qName.equalsIgnoreCase("q7_18_2"))

		{

			String GtSkip = getSkip("q7_18_2_c", "tblMainQuesMc");

			if (GtSkip.equalsIgnoreCase("1"))

			{

				CommonStaticClass.findOutNextSLNo(qName, "q7_18_2_other");

				CommonStaticClass.nextQuestion(ParentActivity.this);
				return false;
			}

		}
		if (qName.equalsIgnoreCase("q7_18_3"))

		{

			String GtSkip = getSkip("q7_18_3_c", "tblMainQuesMc");

			if (GtSkip.equalsIgnoreCase("1"))

			{

				CommonStaticClass.findOutNextSLNo(qName, "q7_18_3_other");

				CommonStaticClass.nextQuestion(ParentActivity.this);
				return false;
			}

		}
		if (qName.equalsIgnoreCase("q7_18_4"))

		{

			String GtSkip = getSkip("q7_18_4_c", "tblMainQuesMc");

			if (GtSkip.equalsIgnoreCase("1"))

			{

				CommonStaticClass.findOutNextSLNo(qName, "q7_18_4_other");

				CommonStaticClass.nextQuestion(ParentActivity.this);
				return false;
			}

		}
		if (qName.equalsIgnoreCase("q7_18_5"))

		{

			String GtSkip = getSkip("q7_18_5_c", "tblMainQuesMc");

			if (GtSkip.equalsIgnoreCase("1"))

			{

				CommonStaticClass.findOutNextSLNo(qName, "q7_18_5_other");

				CommonStaticClass.nextQuestion(ParentActivity.this);

				return false;
			}

		}

		if (qName.equalsIgnoreCase("q7_18_6"))

		{

			String GtSkip = getSkip("q7_18_6_c", "tblMainQuesMc");

			if (GtSkip.equalsIgnoreCase("1"))

			{

				CommonStaticClass.findOutNextSLNo(qName, "q7_18_6_other");

				CommonStaticClass.nextQuestion(ParentActivity.this);

				return false;

			}
		}

		if (qName.equalsIgnoreCase("q65")) {
			String v1 = getSkip("q65_9_1", "tblMainQuesMc");
			if (v1 != null) {
				if (v1.length() > 0) {
					if (v1.equalsIgnoreCase("1")) {
						CommonStaticClass.findOutNextSLNo(
								CommonStaticClass.questionMap.get(
										CommonStaticClass.currentSLNo)
										.getQvar(), "q65_other");
						CommonStaticClass.nextQuestion(ParentActivity.this);
						return true;
					}
				}
			}
		}

		// q47
		if (qName.equalsIgnoreCase("q47")) {
			String v1 = getSkip("q47_23", "tblMainQuesMc");
			if (v1 != null) {
				if (v1.length() > 0) {
					if (v1.equalsIgnoreCase("1")) {
						CommonStaticClass.findOutNextSLNo(
								CommonStaticClass.questionMap.get(
										CommonStaticClass.currentSLNo)
										.getQvar(), "q47_other");
						CommonStaticClass.nextQuestion(ParentActivity.this);
						return true;
					}
				}
			}
		}

		return false;
	}

	private boolean checkIfSingleEditIsBlank() {
		if (mEditStrings.contains("::")) {
			return true;
		} else
			return false;
	}

	private boolean checkeditboxValueisvalid() {

		for (int i = 0; i < mEditStrings.size(); i++) {
			if (Integer.parseInt(mEditStrings.get(i).toString()) >= 1
					|| Integer.parseInt(mEditStrings.get(i).toString()) < 7) {

			} else {
				return false;
			}
		}

		return true;
	}

	private void selectCheckAndCombo(String inColumn, CheckBox checkButton,
			Spinner spinner1, EditText ed, Spinner spinner2) {
		// TODO Auto-generated method stub
		Log.e("inColumn: ", "" + inColumn);
		String sql = "";

		// if (!CommonStaticClass.isMember)
		// sql = "Select "
		// + inColumn
		// + ","
		// + inColumn
		// + " from "
		// + CommonStaticClass.questionMap.get(
		// CommonStaticClass.currentSLNo).getTablename()
		// + " where dataid='" + CommonStaticClass.dataId + "'";
		// else
		// sql = "Select "
		// + inColumn
		// + "_1,"
		// + inColumn
		// + "_2 from "
		// + CommonStaticClass.questionMap.get(
		// CommonStaticClass.currentSLNo).getTablename()
		// + " where dataid='" + CommonStaticClass.dataId
		// + "' and memberid=" + CommonStaticClass.memberID;
		sql = "Select * "
				+ " from "
				+ CommonStaticClass.questionMap.get(
						CommonStaticClass.currentSLNo).getTablename()
				+ " where dataid='" + CommonStaticClass.dataId + "'";

		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.getCount() > 0) {
				doFillpos(mCursor1, inColumn, checkButton, spinner1, ed,
						spinner2);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}

	}

	private void selectCheckAndCombo(String inColumn, CheckBox checkButton,
			Spinner spinner1, Spinner spinner2, Spinner spinner3) {
		// TODO Auto-generated method stub
		Log.e("inColumn: ", "" + inColumn);
		String sql = "";

		sql = "Select * "
				+ " from "
				+ CommonStaticClass.questionMap.get(
						CommonStaticClass.currentSLNo).getTablename()
				+ " where dataid='" + CommonStaticClass.dataId + "'";

		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.getCount() > 0) {
				doFillpos(mCursor1, inColumn, checkButton, spinner1, spinner2,
						spinner3);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}

	}

	private void selectCheckAndCombo(String inColumn, CheckBox checkButton,
			Spinner spinner1, Spinner spinner2) {
		// TODO Auto-generated method stub
		Log.e("inColumn: ", "" + inColumn);
		String sql = "";

		// if (!CommonStaticClass.isMember)
		// sql = "Select "
		// + inColumn
		// + ","
		// + inColumn
		// + " from "
		// + CommonStaticClass.questionMap.get(
		// CommonStaticClass.currentSLNo).getTablename()
		// + " where dataid='" + CommonStaticClass.dataId + "'";
		// else
		// sql = "Select "
		// + inColumn
		// + "_1,"
		// + inColumn
		// + "_2 from "
		// + CommonStaticClass.questionMap.get(
		// CommonStaticClass.currentSLNo).getTablename()
		// + " where dataid='" + CommonStaticClass.dataId
		// + "' and memberid=" + CommonStaticClass.memberID;
		sql = "Select * "
				+ " from "
				+ CommonStaticClass.questionMap.get(
						CommonStaticClass.currentSLNo).getTablename()
				+ " where dataid='" + CommonStaticClass.dataId + "'";

		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.getCount() > 0) {
				doFillpos(mCursor1, inColumn, checkButton, spinner1, spinner2);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}

	}

	private boolean doFillpos(Cursor mCursor1, String columnPrefix,
			CheckBox checkButton, Spinner spinner1, Spinner spinner2,
			Spinner spinner3) {
		boolean dataOk = false;
		String column1 = columnPrefix + "_1";
		String column2 = columnPrefix + "_2";
		String column3 = columnPrefix + "_3";
		String column4 = columnPrefix + "_4";
		if (mCursor1.moveToFirst()) {
			do {
				if (mCursor1.getColumnIndexOrThrow(column1) != -1
						&& mCursor1.getColumnIndexOrThrow(column2) != -1
						&& mCursor1.getColumnIndexOrThrow(column3) != -1
						&& mCursor1.getColumnIndexOrThrow(column4) != -1) {
					try {

						String a = mCursor1.getString(mCursor1
								.getColumnIndex(column1));
						String b = mCursor1.getString(mCursor1
								.getColumnIndex(column2));
						String c = mCursor1.getString(mCursor1
								.getColumnIndex(column3));
						String d = mCursor1.getString(mCursor1
								.getColumnIndex(column4));
						if (Integer.parseInt(a) == 1) {
							checkButton.setChecked(true);
						} else if (Integer.parseInt(a) == 0) {
							checkButton.setChecked(false);
						}
						if (optionCodeList1.contains(Integer.parseInt(b))) {
							int pos = optionCodeList1.indexOf(Integer
									.parseInt(b));

							spinner1.setSelection(pos);
							dataOk = true;
						}
						if (optionCodeList2.contains(Integer.parseInt(c))) {
							int pos = optionCodeList2.indexOf(Integer
									.parseInt(c));

							spinner2.setSelection(pos);
							dataOk = true;
						}
						if (optionCodeList3.contains(Integer.parseInt(d))) {
							int pos = optionCodeList3.indexOf(Integer
									.parseInt(d));

							spinner3.setSelection(pos);
							dataOk = true;
						}

					} catch (Exception e) {
						// TODO: handle exception
					}

				}

			} while (mCursor1.moveToNext());
		}

		return dataOk;
	}

	private boolean doFillpos(Cursor mCursor1, String columnPrefix,
			CheckBox checkButton, Spinner spinner1, EditText ed,
			Spinner spinner2) {
		boolean dataOk = false;
		String column1 = columnPrefix + "_1";
		String column2 = columnPrefix + "_2";
		String column3 = columnPrefix + "_3";
		String column4 = columnPrefix + "_4";
		if (mCursor1.moveToFirst()) {
			do {
				if (mCursor1.getColumnIndexOrThrow(column1) != -1
						&& mCursor1.getColumnIndexOrThrow(column2) != -1
						&& mCursor1.getColumnIndexOrThrow(column3) != -1
						&& mCursor1.getColumnIndexOrThrow(column4) != -1) {
					try {

						String a = mCursor1.getString(mCursor1
								.getColumnIndex(column1));
						String b = mCursor1.getString(mCursor1
								.getColumnIndex(column2));
						String c = mCursor1.getString(mCursor1
								.getColumnIndex(column3));
						String d = mCursor1.getString(mCursor1
								.getColumnIndex(column4));
						if (Integer.parseInt(a) == 1) {
							checkButton.setChecked(true);
						} else if (Integer.parseInt(a) == 0) {
							checkButton.setChecked(false);
						}
						if (b != null && b.length() > 0
								&& !b.equalsIgnoreCase("-1")) {
							ed.setText(b);
						}
						if (optionCodeList1.contains(Integer.parseInt(c))) {
							int pos = optionCodeList1.indexOf(Integer
									.parseInt(c));

							spinner1.setSelection(pos);
							dataOk = true;
						}
						if (optionCodeList2.contains(Integer.parseInt(d))) {
							int pos = optionCodeList2.indexOf(Integer
									.parseInt(d));

							spinner2.setSelection(pos);
							dataOk = true;
						}

					} catch (Exception e) {
						// TODO: handle exception
					}

				}

			} while (mCursor1.moveToNext());
		}

		return dataOk;
	}

	private boolean doFillpos(Cursor mCursor1, String columnPrefix,
			CheckBox checkButton, Spinner spinner1, Spinner spinner2) {
		boolean dataOk = false;
		String column1 = columnPrefix + "_1";
		String column2 = columnPrefix + "_2";
		String column3 = columnPrefix + "_3";
		if (mCursor1.moveToFirst()) {
			do {
				if (mCursor1.getColumnIndexOrThrow(column1) != -1
						&& mCursor1.getColumnIndexOrThrow(column2) != -1
						&& mCursor1.getColumnIndexOrThrow(column3) != -1) {
					try {

						String a = mCursor1.getString(mCursor1
								.getColumnIndex(column1));
						String b = mCursor1.getString(mCursor1
								.getColumnIndex(column2));
						String c = mCursor1.getString(mCursor1
								.getColumnIndex(column3));
						if (Integer.parseInt(a) == 1) {
							checkButton.setChecked(true);
						} else if (Integer.parseInt(a) == 0) {
							checkButton.setChecked(false);
						}
						if (optionCodeList1.contains(Integer.parseInt(b))) {
							int pos = optionCodeList1.indexOf(Integer
									.parseInt(b));

							spinner1.setSelection(pos);
							dataOk = true;
						}
						if (optionCodeList2.contains(Integer.parseInt(c))) {
							int pos = optionCodeList2.indexOf(Integer
									.parseInt(c));

							spinner2.setSelection(pos);
							dataOk = true;
						}

					} catch (Exception e) {
						// TODO: handle exception
					}

				}

			} while (mCursor1.moveToNext());
		}

		return dataOk;
	}

	private boolean doFillpos(Cursor mCursor1, String columnPrefix,
			CheckBox checkButton, Spinner spinner1) {
		boolean dataOk = false;
		String column1 = columnPrefix + "_1";
		String column2 = columnPrefix + "_2";
		String column3 = columnPrefix + "_3";
		if (mCursor1.moveToFirst()) {
			do {
				if (mCursor1.getColumnIndexOrThrow(column1) != -1
						&& mCursor1.getColumnIndexOrThrow(column2) != -1
				/*
				 * && mCursor1.getColumnIndexOrThrow(column3) != -1 &&
				 * mCursor1.getColumnIndexOrThrow(column4) != -1
				 */
				) {
					try {

						String a = mCursor1.getString(mCursor1
								.getColumnIndex(column1));
						String b = mCursor1.getString(mCursor1
								.getColumnIndex(column2));
						String c = mCursor1.getString(mCursor1
								.getColumnIndex(column3));
						/*
						 * String d = mCursor1.getString(mCursor1
						 * .getColumnIndex(column4));
						 */
						if (Integer.parseInt(a) == 1) {
							checkButton.setChecked(true);
						} else if (Integer.parseInt(a) == 0) {
							checkButton.setChecked(false);
						}
						if (optionCodeList1.contains(Integer.parseInt(b))) {
							int pos = optionCodeList1.indexOf(Integer
									.parseInt(b));

							spinner1.setSelection(pos);
							dataOk = true;
						}

						if (optionCodeList2.contains(Integer.parseInt(c))) {
							optionCodeList2.indexOf(Integer.parseInt(c));

							// spinner2.setSelection(pos); dataOk = true; }
							// if
							// (optionCodeList3.contains(Integer.parseInt(d))) {
							// int
							// pos = optionCodeList3.indexOf(Integer
							// .parseInt(d));

							// spinner3.setSelection(pos); dataOk = true;
						}

					} catch (Exception e) {
						// TODO: handle exception
					}

				}

			} while (mCursor1.moveToNext());
		}

		return dataOk;
	}

	public int getFormIndex(String formname) {
		int index = 0;
		if (formname.equalsIgnoreCase("FrmHHID")) {
			index = 0;
		}
		if (formname.equalsIgnoreCase("FrmComboBox")) {
			index = 2;
		}
		if (formname.equalsIgnoreCase("FrmDate")) {
			index = 3;
		}
		if (formname.equalsIgnoreCase("FrmFamilyMember")) {
			index = 4;
		}
		if (formname.equalsIgnoreCase("FrmMessage")) {
			index = 6;
		}
		if (formname.equalsIgnoreCase("FrmMultipleCheckCombo")) {
			index = 7;
		}
		if (formname.equalsIgnoreCase("FrmMultipleChoice")) {
			index = 8;
		}
		if (formname.equalsIgnoreCase("FrmMultipleCombo")) {
			index = 9;
		}
		if (formname.equalsIgnoreCase("FrmNotes")) {
			index = 10;
		}
		if (formname.equalsIgnoreCase("FrmNumeric")) {
			index = 11;
		}
		if (formname.equalsIgnoreCase("FrmNumericOption")) {
			index = 12;
		}
		if (formname.equalsIgnoreCase("FrmReasoning")) {
			index = 13;
		}
		if (formname.equalsIgnoreCase("FrmSingleChoice")) {
			index = 14;
		}
		if (formname.equalsIgnoreCase("FrmText")) {
			index = 15;
		}
		if (formname.equalsIgnoreCase("FrmTime")) {
			index = 16;
		}
		if (formname.equalsIgnoreCase("FrmYearToMin")) {
			index = 17;
		}
		if (formname.equalsIgnoreCase("gpsdatacollection")) {
			index = 18;
		}
		if (formname.equalsIgnoreCase("FrmNumericTwo")) {
			index = 19;
		}
		if (formname.equalsIgnoreCase("FrmNumericwithunknownDecline")) {
			index = 20;
		}

		if (formname.equalsIgnoreCase("frmcomboswitheditspiner")) {
			index = 21;
		}
		if (formname.equalsIgnoreCase("frmmultiplecheckcombotwo")) {
			index = 22;
		}
		if (formname.equalsIgnoreCase("frmmultiplechoiceradio")) {
			index = 23;
		}

		if (formname.equalsIgnoreCase("frmmultiple"))
		// || formname.equalsIgnoreCase("frmmultiplebangla"))
		{
			index = 24;
		}
		if (formname.equalsIgnoreCase("frmq124")) {
			index = 25;
		}

		if (formname.equalsIgnoreCase("frmmultiplechecknumeric")) {
			index = 26;
		}
		if (formname.equalsIgnoreCase("frmmultiplecheckdate")) {
			index = 27;
		}
		if (formname.equalsIgnoreCase("frmbarcode")) {
			index = 28;
		}
		if (formname.equalsIgnoreCase("frmnumericwithrdbtn")) {
			index = 29;
		}

		return index;
	}

	Animation animFlipInForeward;
	Animation animFlipOutForeward;
	Animation animFlipInBackward;
	Animation animFlipOutBackward;

	public void gotoForm(String formname) {
		// TODO Auto-generated method stub
		if (CommonStaticClass.dataId.length() > 0) {
			dataidViewer.setText("Data ID: " + CommonStaticClass.dataId);
		}
		lastIndexBeforeFraNotes = formFlipper.getDisplayedChild();
		formFlipper.setDisplayedChild(getFormIndex(formname));

		switch (getFormIndex(formname)) {
		case 0:

			loadGuiFrmHHID(frmdataid);
			break;
		case 1:

			break;
		case 2:
			Load_UIFrmComboBox(frmcombobox);
			break;
		case 3:
			loadGuiFrmDate(frmdate);
			break;
		case 4:
			loadGuiFrmFamilyMember(frmfamilymember);
			break;
		case 5:
			loadGuiFrmHHID(frmhhid);
			break;
		case 6:
			loadGuiFrmMessage(frmmessage);
			break;
		case 7:
			loadGuiFrmMultipleCheckCombo(frmmultiplecheckcombo);
			break;
		case 8:
			loadGuiFrmMultipleChoice(frmmultiplechoice);
			break;
		case 9:
			Load_UIFrmComboBox(frmcombobox);
			break;
		case 10:
			loadGuiFrmNotes(frmnotes);
			break;
		case 11:
			loadGuiFrmNumeric(frmnumeric);
			break;
		case 12:
			loadGuiFrmNumeric(frmnumeric);
			break;
		case 13:
			Load_UIFrmReasoning(frmreasoning);
			break;
		case 14:
			Load_UIFrmSingleChoice(frmsinglechoice);
			break;
		case 15:
			loadGuiFrmText(frmtext);
			break;
		case 16:
			loadGuiFrmTime(frmtime);
			break;
		case 17:
			loadGuiFrmYearToMin(frmyeartomin);
			break;
		case 18:
			Load_UIFrmGPSDataCollection(gpsdatacollection);
			break;
		case 19:
			loadGuiFrmNumericTwo(frmnumerictwo);
			break;
		case 20:
			loadGuiFrmNumericWithUnknowDecline(frmnumericwithunknowndecline);
			break;

		case 21:
			// loadGuiFrmCombosWithEditSpinner(20, frmcomboswitheditspiner);
			break;

		case 22:
			loadGuiMultipleCheckCombotwo(frmmultiplecheckcombotwo);
			break;

		case 23:
			loadGuiFrmMultipleChoiceRadio(frmmultiplechoiceradio);
			break;

		case 24:
			loadGuiFrmMultiple(frmmultiple);
			break;

		case 25:
			loadGuiFrmq124(frmq124);
			break;

		case 26:
			loadGuiFrmMultipleCheckNumeric(frmmultiplechecknumeric);
			break;

		case 27:
			loadguifrmmultiplecheckdate(frmmultiplecheckdate);
			break;

		case 28:
			loadGuiFrmBarcode(frmbarcode);
			break;
		case 29:
			loadGuifrmnumericwithrdbtn(frmnumericwithrdbtn);
			break;

		default:

			break;
		}
	}

	private ArrayList<String> optionList = null;
	private ArrayList<Integer> optionCodeList = null;
	private EditText touchedBox;

	private void loadguifrmmultiplecheckdate(final ViewGroup v) {
		// TODO Auto-generated method stub
		qqq = (TextView) v.findViewById(R.id.qqq);
		optionList = new ArrayList<String>();
		optionCodeList = new ArrayList<Integer>();
		listvalues = new ArrayList<String>();
		qName = CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar();

		op = new Options();
		if (listvalues != null && listvalues.size() > 0) {
			listvalues.clear();
		}
		if (CommonStaticClass.langBng) {
			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0) {
				Typeface font = Typeface.createFromAsset(getAssets(),
						"Siyam Rupali ANSI.ttf");
				qqq.setTypeface(font);
			}
			;
			qqq.setText(CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0 ? CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng()
					: CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQdesceng());
		} else {
			qqq.setTypeface(null);
			qqq.setText(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQdesceng());
		}

		checkBoxHolder = (LinearLayout) v.findViewById(R.id.checkBoxHolder);
		checkBoxHolder.removeAllViews();
		op = CommonStaticClass.findOptionsForThisQuestion(dbHelper,
				CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar());

		LinearLayout.LayoutParams lnlParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams layoutParamForcheck = new LinearLayout.LayoutParams(
				(dm.widthPixels / 3) * 2,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams layoutParamForSpin = new LinearLayout.LayoutParams(
				(dm.widthPixels / 3), LinearLayout.LayoutParams.WRAP_CONTENT);

		optionList.add("");
		optionCodeList.add(-1);

		for (int i = 0; i < op.codeList.size(); i++) {
			/*
			 * if (op.qidList.get(i).contains("Options")) {
			 * Log.e("op.qidList.get(i)", op.qidList.get(i)); if
			 * (CommonStaticClass.langBng) {
			 * optionList.add(op.capBngList.get(i)); } else {
			 * optionList.add(op.capEngList.get(i)); }
			 * optionCodeList.add(op.codeList.get(i)); continue; }
			 */
			listvalues.add("");
		}
		for (int i = 0; i < op.codeList.size(); i++) {

			Log.e("op.qidList.get(i)", op.qidList.get(i));
			/*
			 * if (op.qidList.get(i).contains("Options")) { continue; }
			 */
			LinearLayout ln = new LinearLayout(this);
			ln.setOrientation(LinearLayout.HORIZONTAL);
			final CheckBox checkButton = new CheckBox(this);
			if (CommonStaticClass.langBng) {
				checkButton.setTypeface(font);
				checkButton
						.setText(op.capBngList.get(i).length() > 0 ? op.capBngList
								.get(i) : op.capEngList.get(i));
			} else {
				checkButton.setTypeface(null);
				checkButton.setText(op.capEngList.get(i));
			}
			checkButton.setId(i);
			final EditText spinner = new EditText(this);
			layoutParamForSpin.weight = 1;
			ln.addView(spinner, 0, layoutParamForSpin);
			spinner.setId(i);
			spinner.setVisibility(View.INVISIBLE);
			ln.addView(checkButton, 0, layoutParamForcheck);

			// if (!(spinner.getText().toString().length() > 0))
			updateDisplay("date");

			spinner.setOnTouchListener(new View.OnTouchListener() {

				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					final Calendar c = Calendar.getInstance();
					dateYear = c.get(Calendar.YEAR);
					dateMonth = c.get(Calendar.MONTH);
					dateDay = c.get(Calendar.DAY_OF_MONTH);
					touchedBox = (EditText) v;
					showDialog(DATE_DIALOG);
					return false;
				}
			});

			/*
			 * if (listvalues != null && listvalues.size() > 0) {
			 * listvalues.clear(); } optionList1 = new ArrayList<String>();
			 * optionCodeList1 = new ArrayList<Integer>(); qqq = (TextView)
			 * v.findViewById(R.id.qqq); qName = CommonStaticClass.questionMap
			 * .get(CommonStaticClass.currentSLNo).getQvar();
			 * 
			 * if (CommonStaticClass.langBng) { if
			 * (CommonStaticClass.questionMap
			 * .get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0) {
			 * qqq.setTypeface(font); } ;
			 * qqq.setText(CommonStaticClass.questionMap
			 * .get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0 ?
			 * CommonStaticClass.questionMap
			 * .get(CommonStaticClass.currentSLNo).getQdescbng() :
			 * CommonStaticClass.questionMap.get(
			 * CommonStaticClass.currentSLNo).getQdesceng()); } else {
			 * qqq.setTypeface(null);
			 * qqq.setText(CommonStaticClass.questionMap.get(
			 * CommonStaticClass.currentSLNo).getQdesceng()); }
			 * 
			 * checkBoxHolder = (LinearLayout)
			 * v.findViewById(R.id.checkBoxHolder);
			 * checkBoxHolder.removeAllViews(); op =
			 * CommonStaticClass.findOptionsForThisQuestion(dbHelper,
			 * CommonStaticClass.questionMap
			 * .get(CommonStaticClass.currentSLNo).getQvar());
			 * 
			 * LinearLayout.LayoutParams lnlParams = new
			 * LinearLayout.LayoutParams( LinearLayout.LayoutParams.FILL_PARENT,
			 * LinearLayout.LayoutParams.WRAP_CONTENT);
			 * LinearLayout.LayoutParams layoutParamForcheck = new
			 * LinearLayout.LayoutParams( ((dm.widthPixels - 65) / 3) * 2,
			 * LinearLayout.LayoutParams.WRAP_CONTENT);
			 * LinearLayout.LayoutParams layoutParamForSpin = new
			 * LinearLayout.LayoutParams( ((dm.widthPixels + 65) / 3),
			 * LinearLayout.LayoutParams.WRAP_CONTENT);
			 * 
			 * for (int i = 0; i < op.codeList.size(); i++) {
			 * 
			 * listvalues.add("-1"); }
			 * 
			 * for (int i = 0; i < op.codeList.size(); i++) {
			 * 
			 * Log.e("op.qidList.get(i)", op.qidList.get(i));
			 * 
			 * LinearLayout ln = new LinearLayout(this);
			 * ln.setOrientation(LinearLayout.HORIZONTAL); final CheckBox
			 * checkButton = new CheckBox(this); if (CommonStaticClass.langBng)
			 * { checkButton.setTypeface(font); checkButton
			 * .setText(op.capBngList.get(i).length() > 0 ? op.capBngList
			 * .get(i) : op.capEngList.get(i)); } else {
			 * checkButton.setTypeface(null);
			 * checkButton.setText(op.capEngList.get(i)); }
			 * checkButton.setId(i);
			 * 
			 * final EditText spinner = new EditText(this); spinner.setId(i);
			 * 
			 * spinner.setInputType(InputType.TYPE_CLASS_DATETIME);
			 * 
			 * layoutParamForSpin.weight = 1; ln.addView(spinner, 0,
			 * layoutParamForSpin); spinner.setVisibility(View.INVISIBLE);
			 * ln.addView(checkButton, 0, layoutParamForcheck);
			 * 
			 * if (!(spinner.getText().toString().length() > 0))
			 * updateDisplay("date");
			 * 
			 * spinner.setOnTouchListener(new View.OnTouchListener() {
			 * 
			 * public boolean onTouch(View v, MotionEvent event) { // TODO
			 * Auto-generated method stub etpickdate = (EditText) v;
			 * showDialog(DATE_DIALOG); return false; } });
			 */

			/*
			 * spinner.setOnTouchListener(new OnTouchListener() {
			 * 
			 * public boolean onTouch(View arg0, MotionEvent arg1) { // TODO
			 * Auto-generated method stub if (checkButton.isChecked()) {
			 * showDialog(DATE_DIALOG); // if (s.toString().length() > 0) {
			 * 
			 * if (!(spinner.getText().toString().length() > 0)) { etpickdate =
			 * spinner; updateDisplay("date"); }
			 * 
			 * listvalues.set(checkButton.getId(), spinner.getText()
			 * .toString()); } return false; }
			 * 
			 * });
			 */

			/*
			 * et.addTextChangedListener(new TextWatcher() {
			 * 
			 * public void onTextChanged(CharSequence s, int start, int before,
			 * int count) { // TODO Auto-generated method stub
			 * 
			 * }
			 * 
			 * public void beforeTextChanged(CharSequence s, int start, int
			 * count, int after) { // TODO Auto-generated method stub
			 * 
			 * }
			 * 
			 * public void afterTextChanged(Editable s) {
			 * 
			 * if (checkButton.isChecked()) { if (s.toString().length() > 0) {
			 * aaa.set(checkButton.getId(), Integer.parseInt(s.toString())); } }
			 * } });
			 */

			checkBoxHolder.addView(ln, 0, lnlParams);

			checkButton
					.setOnCheckedChangeListener(new OnCheckedChangeListener() {

						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							// TODO Auto-generated method stub
							if (isChecked) {
								Log.e("id :", "" + checkButton.getId());
								listvalues.set(checkButton.getId(), "");
								spinner.setVisibility(View.VISIBLE);

							} else {
								listvalues.set(checkButton.getId(), "");
								spinner.setVisibility(View.INVISIBLE);
							}
						}
					});

			selectCheckAndDateFrmMultipleCheckDate(op.qidList.get(i),
					checkButton, spinner);

		}

		saveNxtButton = (Button) v.findViewById(R.id.saveNxtButton);

		saveNxtButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				updateTableDataFrmMultipleCheckDate();
				// preserveState();
			}

		});
		clButton = (Button) v.findViewById(R.id.clButton);
		clButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				resetViewGroup((ViewGroup) v);
			}

		});
		notesButton = (Button) v.findViewById(R.id.btnNote);
		notesButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				FraNotes();

			}

		});
		prevButton = (Button) v.findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				userPressedPrevious(ParentActivity.this);
			}

		});

	}

	private boolean doFillFrmMultipleCheckDate(Cursor mCursor1,
			String inColumn, CheckBox checkButton, EditText spinner) {
		boolean dataOk = false;
		if (mCursor1.moveToFirst()) {
			do {
				if (mCursor1.getColumnIndexOrThrow(inColumn) != -1) {
					Log.e("column", "exist column:" + inColumn);
					try {
						String a = mCursor1.getString(mCursor1
								.getColumnIndex(inColumn));
						Log.e("a", a + "   lklklk");
						if (!a.equalsIgnoreCase("")
								&& !a.equalsIgnoreCase(null)) {

							// if(a!=null&&a.length()>0){
							checkButton.setChecked(true);
							spinner.setText(a);
							listvalues.set(checkButton.getId(), a);
							dataOk = true;
							// }
						} else {
							listvalues.set(checkButton.getId(), "");
						}

					} catch (Exception e) {
						// TODO: handle exception
					}

				}

			} while (mCursor1.moveToNext());
		}

		return dataOk;
	}

	private void spinnerVisibleButNotSeletedFrmMultipleCheckDate(
			ViewGroup viewGroup) {
		int nrOfChildren = viewGroup.getChildCount();
		for (int i = 0; i < nrOfChildren; i++) {
			View view = viewGroup.getChildAt(i);

			if (view instanceof Spinner
					&& (view.getVisibility() == View.VISIBLE)) {
				if (((Spinner) view).getSelectedItemPosition() == 0) {
					spinnerOK = false;
				}
			}
			if (view instanceof ViewGroup) {
				spinnerVisibleButNotSeletedFrmMultipleCheckDate((ViewGroup) view);
			}
		}
	}

	private boolean checkIfSingleOptionIsCheckedFrmMultipleCheckDate() {
		for (int i = 0; i < listvalues.size(); i++) {
			if (!(listvalues.get(i).equalsIgnoreCase("-1"))) {
				return true;
			}

		}
		return false;
	}

	/*
	 * private void selectCheckAndDateFrmMultipleCheckDate(String inColumn,
	 * CheckBox checkButton, EditText spinner) { // TODO Auto-generated method
	 * stub String sql = String.format("Select " + inColumn +
	 * " from '%s' where dataid='%s'", CommonStaticClass.questionMap
	 * .get(CommonStaticClass.currentSLNo).getTablename(),
	 * CommonStaticClass.dataId); Cursor mCursor1 = null; try { mCursor1 =
	 * dbHelper.getQueryCursor(sql); boolean a = false; if (mCursor1.getCount()
	 * > 0) { a = doFillFrmMultipleCheckDate(mCursor1, inColumn, checkButton,
	 * spinner); } if (!a) { if (CommonStaticClass.previousDataFound) { if
	 * (CommonStaticClass.previousqlist.contains(qName)) {
	 * 
	 * sql = String.format( "Select " + inColumn +
	 * " from '%s' where dataid='%s'", CommonStaticClass.questionMap.get(
	 * CommonStaticClass.currentSLNo) .getTablename(),
	 * CommonStaticClass.dataId);
	 * 
	 * mCursor1 = dbHelper.getQueryCursor(sql); if (mCursor1.getCount() > 0) {
	 * doFillFrmMultipleCheckDate(mCursor1, inColumn, checkButton, spinner); } }
	 * } }
	 * 
	 * } catch (Exception e) { // TODO: handle exception e.printStackTrace(); }
	 * finally { if (mCursor1 != null) mCursor1.close(); }
	 * 
	 * }
	 */

	private void selectCheckAndDateFrmMultipleCheckDate(String inColumn,
			CheckBox checkButton, EditText et) {
		// TODO Auto-generated method stub
		String sql = "";
		if (!CommonStaticClass.isMember)
			sql = "Select "
					+ inColumn
					+ " from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " where dataid='" + CommonStaticClass.dataId + "'";
		else
			sql = "Select "
					+ inColumn
					+ " from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " where dataid='" + CommonStaticClass.dataId
					+ "' and memberid=" + CommonStaticClass.memberID;

		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			boolean a = false;
			if (mCursor1.getCount() > 0) {
				doFillFrmMultipleCheckDate(mCursor1, inColumn, checkButton, et);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}

	}

	private void updateTableDataFrmMultipleCheckDate() {
		// TODO Auto-generated method stub
		spinnerOK = true;
		spinnerVisibleButNotSeletedFrmMultipleCheckDate((ViewGroup) findViewById(R.id.rootView));
		if (spinnerOK) {
			if (checkIfSingleOptionIsCheckedFrmMultipleCheckDate()) {
				String sql = "UPDATE "
						+ CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getTablename()
						+ " SET ";
				for (int i = 0; i < op.codeList.size(); i++) {
					if (i == (op.codeList.size() - 1)) {
						sql += op.qidList.get(i) + " = '" + listvalues.get(i)
								+ "' ";
						break;
					}
					sql += op.qidList.get(i) + " = '" + listvalues.get(i)
							+ "',";
				}
				sql += "where dataid='" + CommonStaticClass.dataId + "'";

				if (dbHelper.executeDMLQuery(sql)) {
					listvalues.clear();
					CommonStaticClass.findOutNextSLNo(
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQvar(),
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQnext1());
					CommonStaticClass.nextQuestion(ParentActivity.this);

					/*
					 * preserveState();
					 * CommonStaticClass.findOutNextSLNo(CommonStaticClass
					 * .questionMap
					 * .get(CommonStaticClass.currentSLNo).getQvar(),
					 * CommonStaticClass
					 * .questionMap.get(CommonStaticClass.currentSLNo
					 * ).getQnext1()); CommonStaticClass.nextQuestion(con);
					 */
				}

			} else {
				CommonStaticClass
						.showMyAlert(con, "Please check one!!!",
								"You didn't checked any answer please select one to preceed");
			}
		} else {
			CommonStaticClass
					.showMyAlert(con, "Please select item!!!",
							"You didn't select any item from list, Please select one to proceed");
		}

	}

	TextView Slno;
	Spinner spinnerc1;
	TextView lblc2;
	Spinner spinnerc2;
	TextView lblc3;
	EditText etc3;
	TextView lblc4;
	EditText etc4;
	TextView lblc5;
	TextView lblc5village;
	EditText etc5;
	TextView lblc5_2;
	EditText etc5_2;
	TextView lblc5_3;
	EditText etc5_3;
	TextView tvc5_4;
	EditText etc5_4;
	TextView lblc6;
	EditText etc6;
	EditText etother;

	private void loadGuiFrmq124(ViewGroup vg) {

		qqq = (TextView) vg.findViewById(R.id.qqq);

		if (CommonStaticClass.langBng) {
			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0) {
				Typeface font = Typeface.createFromAsset(getAssets(),
						"Siyam Rupali ANSI.ttf");
				qqq.setTypeface(font);
			}
			;
			qqq.setText(CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0 ? CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng()
					: CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQdesceng());
		} else {
			qqq.setTypeface(null);
			qqq.setText(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQdesceng());
		}

		Slno = (TextView) vg.findViewById(R.id.Slno);
		spinnerc1 = (Spinner) vg.findViewById(R.id.c1);
		lblc2 = (TextView) vg.findViewById(R.id.lblc2);
		spinnerc2 = (Spinner) vg.findViewById(R.id.c2);
		lblc3 = (TextView) vg.findViewById(R.id.lblc3);
		etc3 = (EditText) vg.findViewById(R.id.c3);
		lblc4 = (TextView) vg.findViewById(R.id.lblc4);
		etc4 = (EditText) vg.findViewById(R.id.c4);
		lblc5 = (TextView) vg.findViewById(R.id.lblc5);
		lblc5village = (TextView) vg.findViewById(R.id.lblc5village);
		etc5 = (EditText) vg.findViewById(R.id.c5);
		lblc5_2 = (TextView) vg.findViewById(R.id.lblc5_2);
		etc5_2 = (EditText) vg.findViewById(R.id.c5_2);
		lblc5_3 = (TextView) vg.findViewById(R.id.lblc5_3);
		etc5_3 = (EditText) vg.findViewById(R.id.c5_3);
		tvc5_4 = (TextView) vg.findViewById(R.id.lblc5_4);
		etc5_4 = (EditText) vg.findViewById(R.id.c5_4);
		lblc6 = (TextView) vg.findViewById(R.id.lblc6);
		etc6 = (EditText) vg.findViewById(R.id.c6);
		etother = (EditText) vg.findViewById(R.id.etother);

		if (CommonStaticClass.langBng) {

			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0) {
				qqq.setTypeface(font);
			}

			Slno.setTypeface(font);
			lblc2.setTypeface(font);
			lblc3.setTypeface(font);
			lblc4.setTypeface(font);
			lblc5.setTypeface(font);
			lblc5village.setTypeface(font);
			lblc5_2.setTypeface(font);
			lblc5_3.setTypeface(font);
			tvc5_4.setTypeface(font);
			lblc6.setTypeface(font);

			Slno.setText("ÂµwgK bs");
			lblc2.setText("Ã¥gâ€¡bi Dâ€¡ï¿½?kÂ¨,(1=mvgvwRK cwi`kÂ©b,2=â€¡ivMxi Ã¯kÂ«ylvKvix wnâ€¡mâ€¡e,3=  AbÂ¨vbÂ¨ (wbw`Â©Ã³ KiÃ¦b)");
			lblc3.setText("Avcbvi Lvbvâ€¡Z AwZevwnZ mgq (Nâ€ºUvq Dâ€¡jÃ¸L KiÃ¦b hw` AwZevwnZ mgq 3 ivâ€¡Zi Kg  nq)");
			lblc4.setText("Avcbvi Lvbvâ€¡Z AwZevwnZ ivâ€¡Zi msLÂ¨v (hw` AwZevwnZ mgq 3 ivâ€¡Zi â€ ekx  nq)");
			lblc5.setText("â€¡Kv_v nâ€¡Z wZwb/Zviv Gâ€¡mwQâ€¡jb?");
			lblc5village.setText("MÃ–vg/cvov (gnjÃ¸v)");
			lblc5_2.setText("BDwbqb");
			lblc5_3.setText("Dcâ€¡Rjv");
			tvc5_4.setText("â€¡Rjv");
			lblc6.setText("`~iZÂ¡ wKtwgt/gvBj (DÃ‹i`vZvi Dâ€¡jjLKâ€žZ GKK wbw`Â©Ã³ KiÃ¦b)");

			qqq.setText(CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0 ? CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng()
					: CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQdesceng());
		} else {

			Slno.setTypeface(null);
			lblc2.setTypeface(null);
			lblc3.setTypeface(null);
			lblc4.setTypeface(null);
			lblc5.setTypeface(null);
			lblc5village.setTypeface(null);
			lblc5_2.setTypeface(null);
			lblc5_3.setTypeface(null);
			tvc5_4.setTypeface(null);
			lblc6.setTypeface(null);

			Slno.setText("Sl. No.");
			lblc2.setText("Purpose of visit (1=Social visit, 2=As a care giver of a patient 3=Others)");
			lblc3.setText("Time spent in your household (mention in hours if spent <3nights)");
			lblc4.setText("Number of nights spent in your household (if spent > 3 nights)");
			lblc5.setText("From where he/she/they came");
			lblc5village.setText("Village (rural)/ Para (Urban)");
			lblc5_2.setText("Union");
			lblc5_3.setText("Upazilla/ Thana");
			tvc5_4.setText("District");
			lblc6.setText("Distance in km or mile (specify the unit as the respondent mentions)");

			qqq.setTypeface(null);
			qqq.setText(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQdesceng());

		}

		memberIDs = new ArrayList<String>();

		// loading all options
		opSex = CommonStaticClass.findOptionsForThisQuestion(dbHelper, "q124");

		filllAllSpinnerDataq124();

		btnPrev = (Button) vg.findViewById(R.id.prevButton);
		btnPrev.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				userPressedPrevious(ParentActivity.this);
			}

		});
		btnsaveNxt = (Button) vg.findViewById(R.id.saveNxtButton);
		btnsaveNxt.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				updateTableDataq124();
			}

		});
		btnClear = (Button) vg.findViewById(R.id.clButton);
		btnClear.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				resetViewGroup((ViewGroup) vv);
			}

		});
		notesButton = (Button) vg.findViewById(R.id.btnNote);
		notesButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				FraNotes();

			}

		});

	}

	private void loadGuifrmnumericwithrdbtn(final ViewGroup v) {
		// TODO Auto-generated method stub
		RadioGroup rgButton = (RadioGroup) v.findViewById(R.id.radioGroup1);
		rgButton.clearCheck();
		qName = CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar();

		qqq = (TextView) v.findViewById(R.id.qqq);

		if (CommonStaticClass.langBng) {
			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0) {
				Typeface font = Typeface.createFromAsset(getAssets(),
						"Siyam Rupali ANSI.ttf");
				qqq.setTypeface(font);
			}
			;
			qqq.setText(CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0 ? CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng()
					: CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQdesceng());
		} else {
			qqq.setTypeface(null);
			qqq.setText(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQdesceng());
		}

		infoText = (EditText) v.findViewById(R.id.infoText);
		// infoText.setText("");
		infoText.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				if (qName.equalsIgnoreCase("q5")) {
					String lineNumber = s.toString();
					if (lineNumber.length() > 2) {
						CommonStaticClass.showMyAlert(con, "Message",
								"Number should be in two digit");
						infoText.setText("");
						return;
					}
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}
		});

		String sql = "";
		if (!CommonStaticClass.isMember)
			sql = "Select "
					+ qName
					+ ", Q3MorK from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " where dataid='" + CommonStaticClass.dataId + "'";
		else
			sql = "Select "
					+ qName
					+ ", Q3MorK from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " where dataid='" + CommonStaticClass.dataId
					+ "' and memberid=" + CommonStaticClass.memberID;
		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.getCount() > 0) {
				infoText.setText("");
				if (mCursor1.moveToFirst()) {
					do {
						if (mCursor1.getColumnIndex(qName) != -1) {
							String a = mCursor1.getString(mCursor1
									.getColumnIndex(qName)) + "";

							infoText.setText((a.length() > 0
									&& (!a.equalsIgnoreCase("-1")) && (!a
									.equalsIgnoreCase("null"))) ? a : "");

							String b = mCursor1.getString(mCursor1
									.getColumnIndex("Q3MorK")) + "";

							if (b != null) {
								if (b.equalsIgnoreCase("1")) {
									((RadioButton) v.findViewById(R.id.rbtnmin))
											.setChecked(true);
								} else if (b.equalsIgnoreCase("2")) {
									((RadioButton) v.findViewById(R.id.rbtnkm))
											.setChecked(true);
								}
							}

						}
					} while (mCursor1.moveToNext());
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}

		prevButton = (Button) v.findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				userPressedPrevious(ParentActivity.this);
			}

		});
		saveNxtButton = (Button) v.findViewById(R.id.saveNxtButton);

		saveNxtButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				// TODO Auto-generated method stub

				String qAns = infoText.getText().toString();
				String currentQuestion = qName;
				if (qAns.length() == 0) {
					CommonStaticClass.showMyAlert(con, "Not Correct",
							"Textbox can not be empty");
					return;
				}
				if (!IsValidFrmNumeric()) {
					CommonStaticClass.showMyAlert(con, "Not Correct",
							"Cluster ID is mismatching");
					return;
				}
				if (!((RadioButton) findViewById(R.id.rbtnmin)).isChecked()
						&& !((RadioButton) findViewById(R.id.rbtnkm))
								.isChecked()) {
					CommonStaticClass.showMyAlert(con, "Not Correct",
							"Check an option");
					return;
				}
				if (((RadioButton) findViewById(R.id.rbtnmin)).isChecked()
						&& Double.valueOf(qAns) > 30) {
					CommonStaticClass.showMyAlert(con, "Not Correct",
							"Distance in Minutes can not be greater than 30");
					return;
				}
				if (((RadioButton) findViewById(R.id.rbtnkm)).isChecked()
						&& Double.valueOf(qAns) > 3) {
					CommonStaticClass.showMyAlert(con, "Not Correct",
							"Distance in kilometers can not be greater than 3");
					return;
				}

				if (qAns.length() > 0) {

					String rbval = "";
					if (((RadioButton) findViewById(R.id.rbtnmin)).isChecked()) {
						rbval = "1";
					} else if (((RadioButton) findViewById(R.id.rbtnkm))
							.isChecked()) {
						rbval = "2";
					}
					String sql = "";
					sql = "UPDATE "
							+ CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo)
									.getTablename() + " SET " + currentQuestion
							+ "='" + qAns + "', Q3MorK = " + rbval
							+ " where dataid='" + CommonStaticClass.dataId
							+ "'";

					if (dbHelper.executeDMLQuery(sql)) {

						CommonStaticClass.findOutNextSLNo(
								CommonStaticClass.questionMap.get(
										CommonStaticClass.currentSLNo)
										.getQvar(),
								CommonStaticClass.questionMap.get(
										CommonStaticClass.currentSLNo)
										.getQnext1());
						CommonStaticClass.nextQuestion(ParentActivity.this);
					}
				} else {
					CommonStaticClass.showMyAlert(con, "Not Correct",
							"Please put correct information in the field");
				}

			}

		});
		clButton = (Button) v.findViewById(R.id.clButton);
		clButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				resetViewGroup((ViewGroup) v);
			}

		});
		notesButton = (Button) v.findViewById(R.id.btnNote);
		notesButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				FraNotes();

			}

		});

	}
	
	// code by imtiaz khan
	public int getChoiceValue(String quesName)
	{
		String sql1 = "";
		int choiceValue = 0;
		sql1 = "Select "+quesName+" from tblMainQues where dataid='" + CommonStaticClass.dataId + "'";	
		//sql1 = "Select q5_1,q5_2,q5_3,q5_4,q5_5,q5_6 from tblMainQues where dataid='" + CommonStaticClass.dataId + "'";

		Cursor mCursor1 = null;
		
		
		try {
			mCursor1 = dbHelper.getQueryCursor(sql1);

			if (mCursor1 != null && mCursor1.getCount() > 0) {
				
					mCursor1.moveToFirst();
					
					
						
							
						choiceValue = Integer.parseInt(mCursor1.getString(mCursor1.getColumnIndex(quesName)));
						
				
					
				}	
			} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
			
		}
		return choiceValue;
	}

	@Override
	public void onBackPressed() {
		// if(formFlipper.getDisplayedChild()!=5){
		userPressedPrevious(this);
		// }
	}

	public void FillSpinnerOther(String sql, Spinner spnr) {

	}

}
