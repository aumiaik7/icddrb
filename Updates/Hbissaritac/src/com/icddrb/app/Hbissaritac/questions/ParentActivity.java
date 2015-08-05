package com.icddrb.app.Hbissaritac.questions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
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
import android.text.InputFilter;
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
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.AutoCompleteTextView;
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

import com.icddrb.app.Hbissaritac.BaseActivity;
import com.icddrb.app.Hbissaritac.CommonStaticClass;
import com.icddrb.app.Hbissaritac.Options;
import com.icddrb.app.Hbissaritac.R;
import com.icddrb.app.Hbissaritac.adapters.SpinAdapter;

//import com.icddrb.app.Nidataextraction.questions.ParentActivity.DataIDSpinnerListener;

public class ParentActivity extends BaseActivity implements FormListener {

	// f
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
	private EditText pickDate, dischargeDate;
	static final int DATE_DIALOG = 1, DOB_DIALOG = 4;
	private int dateYear;
	private int dateMonth;
	private int dateDay;

	private int dobYear;
	private int dobMonth;
	private int dobDay;

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
	static final int DATE_DIALOG_ID = 3, HBIS_DATE_DIALOG_ID = 5;
	private int mYear;
	private int mMonth;
	private int mDay;
	private String hospital = "", ward = "";

	private ArrayAdapter adapterHospital, adapterWard;

	ArrayList<String> wardList = new ArrayList<String>();
	ArrayList<String> hospitalList = new ArrayList<String>();

	EditText etyearmonth, etpid;

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
	
	
	AutoCompleteTextView text1,text2,text3,text4,med1,med2,med3,ant1,ant2,ant3;

	// this activity part
	private ViewFlipper formFlipper;
	private Context con;
	private ViewGroup frmdataid, frmcombobox, frmdate, frmfamilymember,
			frmhhid, frmmessage, frmmultiplecheckcombo, frmmultiplechoice,
			frmnotes, frmnumeric, frmnumericoption, frmreasoning,
			frmsinglechoice, frmtext, frmtime, frmyeartomin, gpsdatacollection,
			frmnumerictwo, frmnumericwithunknowndecline,
			frmcomboswitheditspiner, frmmultiplecheckcombotwo,
			frmmultiplechoiceradio, frmmultiple, frmq124,
			frmmultiplechecknumeric, frmmultiplecheckdate, frmpatientdetail,
			frmweight, frmdiarrhea, frmvomiting, frmors, frmchildren,
			frmmultiplechoicetext, frmaddress, frmtravelinfo,
			frmpersonrelation, frmsymptoms, frmsymptomsone,
			frmmultiplesinglechoice, frmmultiplesinglechoiceone,
			frmmultiplesinglechoicetwo;

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
		super.onCreate(savedInstanceState);
		setContentView(R.layout.parentlayout);
		thisactivity = this;
		con = this;
		dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		font = Typeface.createFromAsset(getAssets(), "Siyam Rupali ANSI.ttf");

		loadParentUI();
		this.gotoForm(CommonStaticClass.questionMap.get(
				CommonStaticClass.currentSLNo).getFormname());
		// motion();
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
				if (line == null || !(line.length() > 1)) {

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

			/*
			 * case R.id.search: CommonStaticClass.mode = ""; Intent int_b = new
			 * Intent(getApplicationContext(), SearchLayout.class);
			 * startActivity(int_b); return true;
			 */

		case R.id.GotoHomeItem:
			CommonStaticClass.mode = "";
			finish();
			return true;

			/*
			 * case R.id.viewall:
			 * 
			 * CommonStaticClass.mode = ""; int_b = new
			 * Intent(getApplicationContext(), Viewall.class);
			 * startActivity(int_b); return true;
			 */

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
		// (ViewGroup) findViewById(R.id.frmmultiplecombo);

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
		frmpatientdetail = (ViewGroup) findViewById(R.id.frmpatientdetail);
		frmweight = (ViewGroup) findViewById(R.id.frmweight);
		frmdiarrhea = (ViewGroup) findViewById(R.id.frmdiarrhea);
		frmvomiting = (ViewGroup) findViewById(R.id.frmvomiting);
		frmors = (ViewGroup) findViewById(R.id.frmors);
		frmchildren = (ViewGroup) findViewById(R.id.frmchildren);
		frmmultiplechoicetext = (ViewGroup) findViewById(R.id.frmmultiplechoicetext);
		frmaddress = (ViewGroup) findViewById(R.id.frmaddress);
		frmtravelinfo = (ViewGroup) findViewById(R.id.frmtravelinfo);
		frmpersonrelation = (ViewGroup) findViewById(R.id.frmpersonrelation);
		frmsymptoms = (ViewGroup) findViewById(R.id.frmsymptoms);
		frmsymptomsone = (ViewGroup) findViewById(R.id.frmsymptomsone);
		frmmultiplesinglechoice = (ViewGroup) findViewById(R.id.frmmultiplesinglechoice);
		frmmultiplesinglechoiceone = (ViewGroup) findViewById(R.id.frmmultiplesinglechoiceone);
		frmmultiplesinglechoicetwo = (ViewGroup) findViewById(R.id.frmmultiplesinglechoicetwo);
	}

	protected void FraNotes() {
		// TODO Auto-generated method stub
		this.gotoForm("FrmNotes");
	}

	private void setfonttobangla(ViewGroup v) {
		((CheckBox) findViewById(R.id.chk1))
				.setText("wkï Lv`¨ †hgb j¨vK‡Uv‡Rb A_ev bvb A_ev ev‡qvwgj,gvBeq Ab¨vb¨?");
		((CheckBox) findViewById(R.id.chk1)).setTypeface(font);

		((CheckBox) findViewById(R.id.chk2))
				.setText("cvwb‡Z ev `y‡a wm× Kiv km¨ RvZxq Lvevi †hgb: mywR A_ev Ab¨vb¨ Lvevi hv f~Æv&i ˆZix, †h¸‡jv †`vKv‡b wKb‡Z cvIqv hvq?");
		((CheckBox) findViewById(R.id.chk2)).setTypeface(font);

		((CheckBox) findViewById(R.id.chk3))
				.setText("A_ev wkï‡`i Lv`¨ km¨ †hgb †m‡ijvK?");
		((CheckBox) findViewById(R.id.chk3)).setTypeface(font);

		((CheckBox) findViewById(R.id.chk4))
				.setText("cywóKYv, gwbwg• wgwkªZ Lvevi [¸ov ev gvB‡µvwbDwUª‡qÛ `vbv hv evRv‡i cvIqv hvq]?");
		((CheckBox) findViewById(R.id.chk4)).setTypeface(font);

		((CheckBox) findViewById(R.id.chk5))
				.setText("‡mvbvgwb hv Avgv‡`i ‡_‡K †c‡q‡Qb? hw` Iqvk-‡ewbwdU G Aš�?f©~³ nIqvi úi úi nq Zvn‡j “00�?  †KvW Kiæb | hw` wkïwUi eqm 6 gv‡mi †ekx  nq Ges †m Iqvk-‡ewbwdU †_‡K †Kvb LNS bv ‡c‡q _v‡K  Zvn‡j “88�? †KvW Kiæb |");
		((CheckBox) findViewById(R.id.chk4)).setTypeface(font);

		((CheckBox) findViewById(R.id.chk6))
				.setText("Ab¨ ‡h †Kvb (Gj Gb Gm) ev m¤ú~iK cywó/cywó c¨v‡KU?");
		((CheckBox) findViewById(R.id.chk6)).setTypeface(font);

	}

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
						.equalsIgnoreCase("q9")

				|| CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar()
						.equalsIgnoreCase("q10a")

				|| CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar()
						.equalsIgnoreCase("upa")

				|| CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar()
						.equalsIgnoreCase("q1_5")

				|| CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar()
						.equalsIgnoreCase("c1_8")) {
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
						.equalsIgnoreCase("q9"))
					sql = String.format("Select * from tblDisease");
				else if (CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar()
						.equalsIgnoreCase("q10a"))
					sql = String.format("Select * from tblAntibiotics");

				else if (CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar()
						.equalsIgnoreCase("q10"))

					/*
					 * sql = String.format(
					 * "select * from tblCluster where Clusterid = (select clusterid from tblMainQues where dataid =  '%s')"
					 * ,CommonStaticClass.dataId);
					 */
					sql = String
							.format("select * from tblDistrict Order By DistName");

				else if (CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar()
						.equalsIgnoreCase("upa"))

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
						.equalsIgnoreCase("c1_8"))
					// sql = "Select * from tblCluster";
					sql = String.format("select * from tblOccupation",
							CommonStaticClass.dataId);

				mCursor = dbHelper.getQueryCursor(sql);
				if (mCursor.moveToFirst()) {
					do {
						if (CommonStaticClass.questionMap
								.get(CommonStaticClass.currentSLNo).getQvar()
								.equalsIgnoreCase("q9"))

						{
							users.add(mCursor.getString(mCursor
									.getColumnIndex("ID"))
									+ " : "
									+ mCursor.getString(mCursor
											.getColumnIndex("Name")));
							userIDs.add(mCursor.getString(mCursor
									.getColumnIndex("ID")));

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

						}

						// for question 1_2 to 1_6

						else if (CommonStaticClass.questionMap
								.get(CommonStaticClass.currentSLNo).getQvar()
								.equalsIgnoreCase("q1_3")) {
							users.add(mCursor.getString(mCursor
									.getColumnIndex("Clusterid"))
									+ " : "
									+ mCursor.getString(mCursor
											.getColumnIndex("Clusterid")));
							userIDs.add(mCursor.getString(mCursor
									.getColumnIndex("Clusterid")));

						} else if (CommonStaticClass.questionMap
								.get(CommonStaticClass.currentSLNo).getQvar()
								.equalsIgnoreCase("q10a")
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
								.equalsIgnoreCase("upa")) {
							users.add(mCursor.getString(mCursor
									.getColumnIndex("UpazilaCode"))
									+ " : "
									+ mCursor.getString(mCursor
											.getColumnIndex("UpazilaName")));
							userIDs.add(mCursor.getString(mCursor
									.getColumnIndex("UpazilaCode")));

						} else if (CommonStaticClass.questionMap
								.get(CommonStaticClass.currentSLNo).getQvar()
								.equalsIgnoreCase("c1_8")) {

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

		} else // applicable when Spinner is loaded from options table
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
									.getQvar().equalsIgnoreCase("q9")

							|| CommonStaticClass.questionMap
									.get(CommonStaticClass.currentSLNo)
									.getQvar().equalsIgnoreCase("q10a")
							|| CommonStaticClass.questionMap
									.get(CommonStaticClass.currentSLNo)
									.getQvar().equalsIgnoreCase("upa")
							|| CommonStaticClass.questionMap
									.get(CommonStaticClass.currentSLNo)
									.getQvar().equalsIgnoreCase("q1_5")
							|| CommonStaticClass.questionMap
									.get(CommonStaticClass.currentSLNo)
									.getQvar().equalsIgnoreCase("c1_8")
							|| CommonStaticClass.questionMap
									.get(CommonStaticClass.currentSLNo)
									.getQvar().equalsIgnoreCase("c1_10"))
						sResCode = parent
								.getItemAtPosition(pos)
								.toString()
								.substring(
										0,
										(parent.getItemAtPosition(pos)
												.toString().lastIndexOf(":") - 1));
					else
						sResCode = op.codeList.get(pos).toString();
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

						if (mCursor1.getColumnIndex(column.toLowerCase()) != -1) {
							String a = mCursor1.getString(mCursor1
									.getColumnIndex(column.toLowerCase())) + "";

							if (CommonStaticClass.questionMap
									.get(CommonStaticClass.currentSLNo)
									.getQvar().equalsIgnoreCase("userid")
									|| CommonStaticClass.questionMap
											.get(CommonStaticClass.currentSLNo)
											.getQvar().equalsIgnoreCase("q9")

									|| CommonStaticClass.questionMap
											.get(CommonStaticClass.currentSLNo)
											.getQvar().equalsIgnoreCase("q10a")

									|| CommonStaticClass.questionMap
											.get(CommonStaticClass.currentSLNo)
											.getQvar().equalsIgnoreCase("upa")

									|| CommonStaticClass.questionMap
											.get(CommonStaticClass.currentSLNo)
											.getQvar().equalsIgnoreCase("q1_5")

									|| CommonStaticClass.questionMap
											.get(CommonStaticClass.currentSLNo)
											.getQvar().equalsIgnoreCase("c1_8")

									|| CommonStaticClass.questionMap
											.get(CommonStaticClass.currentSLNo)
											.getQvar()
											.equalsIgnoreCase("c1_10"))

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
		try {
			String sql = "";
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

			dobYear = year;
			dobMonth = monthOfYear;
			dobDay = dayOfMonth;

			updateDisplay("date");
		}
	};

	private DatePickerDialog.OnDateSetListener HBISdateListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			dateYear = year;
			dateMonth = monthOfYear;
			dateDay = dayOfMonth;
			EditText et = (EditText) getWindow().getCurrentFocus();

			updateDisplayfrmSymptom("date", et);
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

					if (mCursor1.getColumnIndex(column.toLowerCase()) != -1) {
						String val = mCursor1.getString(mCursor1
								.getColumnIndex(column.toLowerCase())) + "";
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
		if (!(pickDate.getText().toString().length() > 0))
			updateDisplay("date");

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

	private void updateDisplayfrmSymptom(String dt, EditText et) {

		if (dt.contains("date")) {
			String date = new StringBuilder()
					// Month is 0 based so add 1
			.append(dateDay).append("/")
			.append(String.format("%02d", dateMonth + 1)).append("/")
			.append(dateYear).toString();

			if (et.isFocused())
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
			if (ettime != null) {

				ettime.setText(time);
				return;

			}
			if (pickTime != null)
				pickTime.setText(time);
		}

		if (dt.contains("date")) {
			String date = new StringBuilder()
					// Month is 0 based so add 1

					.append(dateDay).append("/").append(dateMonth + 1)
					.append("/").append(dateYear).toString();

			// .append(GetMonth(dateMonth + 1)).append("-")
			if (pickDate != null) {
				// if (pickDate.isFocused()) {

				if (pickDate != null) {
					if (pickDate.isFocused())
						pickDate.setText(date);
				}

				// }
			}

			if (dischargeDate != null) {
				// if (pickDate.isFocused()) {

				if (dischargeDate != null) {
					if (dischargeDate.isFocused())
						dischargeDate.setText(date);
				}

				// }
			}

			// Log.d("Date: ",date);
			/*
			 * if (qName.equalsIgnoreCase("PatientID")) { {
			 * 
			 * if (touchedBox != null) { touchedBox.setText(date);
			 * //listvalues.set(touchedBox.getId(), date); return; }
			 * 
			 * } }
			 */

			if (CommonStaticClass.currentSLNo == 1) {

				int localMonth = (dateMonth + 1);

				String monthString = localMonth < 10 ? "0" + localMonth
						: Integer.toString(localMonth);
				String localYear = Integer.toString(dateYear).substring(2);

				etyearmonth.setText(new StringBuilder()
						// Month is 0 based so add 1
						.append(localYear).append("/").append(monthString)
						.append("/").append(dateDay).append(" "));
				showDialog(DATE_DIALOG_ID);
				return;

			}

			/*
			 * if (CommonStaticClass.currentSLNo == 2 ||
			 * CommonStaticClass.currentSLNo == 6) {
			 * 
			 * if (etyearmonth.isFocused()) { int localMonth = (dateMonth + 1);
			 * 
			 * String monthString = localMonth < 10 ? "0" + localMonth :
			 * Integer.toString(localMonth);
			 * 
			 * String localYear = Integer.toString(dateYear);
			 * 
			 * etyearmonth.setText(new StringBuilder().append(dateDay)
			 * .append("/").append(monthString).append("/") .append(localYear));
			 * // showDialog(DATE_DIALOG_ID); }
			 */
			/*
			 * if (pickDate != null) { if (pickDate.isFocused()) { int
			 * localMonth = (dobMonth + 1);
			 * 
			 * String monthString = localMonth < 10 ? "0" + localMonth :
			 * Integer.toString(localMonth);
			 * 
			 * String localYear = Integer.toString(dobYear);
			 * 
			 * if (pickDate != null) { pickDate.setText(new
			 * StringBuilder().append(dobDay) .append("/").append(monthString)
			 * .append("/").append(localYear)); } } }
			 */
			return;

			// }

			/*
			 * if (pickDate != null) { pickDate.setText(date); }
			 */
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
				.setText("wLPzox( wLPzox wK wK w`‡q ivbœv n‡q‡Q †R‡b wb‡q dzW MÖc Abyhvqx bx‡P †KvW Kiyb)");
		/*
		 * ((TextView) v.findViewById(R.id.textView3)) .setText(
		 * "cvwb‡Z ev `y‡a wm× Kiv km¨ RvZxq Lvevi †hgb: mywR,fvZ, iywU, byWzjm,  Ab¨vb¨  km¨ RvZxq `vbv`vi Lv`¨"
		 * );
		 */

		((CheckBox) v.findViewById(R.id.chk2_1)).setText("mywR");
		((CheckBox) v.findViewById(R.id.chk2_2)).setText("fvZ");
		((CheckBox) v.findViewById(R.id.chk2_3)).setText("iywU");
		((CheckBox) v.findViewById(R.id.chk2_4)).setText("byWzjm");
		((CheckBox) v.findViewById(R.id.chk2_5))
				.setText("Ab¨vb¨  km¨ RvZxq `vbv`vi Lv`¨");

		((CheckBox) v.findViewById(R.id.chk3_1)).setText("wgwó Kzgov");

		((CheckBox) v.findViewById(R.id.chk3_2)).setText("MvRi");

		((CheckBox) v.findViewById(R.id.chk3_3)).setText("Ab¨vb¨ njy` meRx");

		((CheckBox) v.findViewById(R.id.chk4_1)).setText("Avjy");

		((CheckBox) v.findViewById(R.id.chk4_2)).setText("mv`v wgwó Avjy");

		((CheckBox) v.findViewById(R.id.chk5_1)).setText("wgwó Kzgov kvK");

		((CheckBox) v.findViewById(R.id.chk5_2)).setText("mwilv kvK");

		((CheckBox) v.findViewById(R.id.chk5_3)).setText("gUi ïwU kvK");

		((CheckBox) v.findViewById(R.id.chk5_4))
				.setText("gUi ïwU kvK,  cyBu kvK");

		((CheckBox) v.findViewById(R.id.chk5_5)).setText("Ab¨vb¨ Mvp meyR kvK");

		((CheckBox) v.findViewById(R.id.chk6_1)).setText("cvKv Avg");

		((CheckBox) v.findViewById(R.id.chk6_2)).setText("cvKv †cu‡cu");

		((CheckBox) v.findViewById(R.id.chk7_1)).setText("Kjv");

		((CheckBox) v.findViewById(R.id.chk7_2)).setText("Avbvim");

		((CheckBox) v.findViewById(R.id.chk7_3)).setText("‡cqviv");

		((CheckBox) v.findViewById(R.id.chk7_4)).setText("Av‡cj");

		((CheckBox) v.findViewById(R.id.chk7_5)).setText("Av½yi");

		((CheckBox) v.findViewById(R.id.chk7_7)).setText("Kgjv");

		((CheckBox) v.findViewById(R.id.chk7_6)).setText("Ab¨vb¨ dj");

		((CheckBox) v.findViewById(R.id.chk8_1)).setText("U‡g‡Uv");

		((CheckBox) v.findViewById(R.id.chk8_2)).setText("wcuqvR");

		((CheckBox) v.findViewById(R.id.chk8_3)).setText("gvkiyg");

		((CheckBox) v.findViewById(R.id.chk8_4)).setText("‡pom");

		((CheckBox) v.findViewById(R.id.chk8_5)).setText("ZvRv mxg/gUi ïwU");

		((CheckBox) v.findViewById(R.id.chk8_6)).setText("Ab¨vb¨ mewR");

		((CheckBox) v.findViewById(R.id.chk9_1)).setText(" KwjRv");

		((CheckBox) v.findViewById(R.id.chk9_2)).setText("wMjv");

		((CheckBox) v.findViewById(R.id.chk9_3)).setText("Heart");

		((CheckBox) v.findViewById(R.id.chk10_1))
				.setText("†h †Kvb gvsm ,cï cvwLmn");

		((CheckBox) v.findViewById(R.id.chk11_1)).setText("†h †Kvb ai‡Yi wWg");

		((CheckBox) v.findViewById(R.id.chk12_1)).setText("ZvRv  gvQ");

		((CheckBox) v.findViewById(R.id.chk12_2)).setText("ïUwK gvQ");

		((CheckBox) v.findViewById(R.id.chk12_3))
				.setText("Ab¨vb¨ gvQ / mvgyw`ªK Lvevi");

		((CheckBox) v.findViewById(R.id.chk13_1)).setText("mxg");

		((CheckBox) v.findViewById(R.id.chk13_2)).setText("Wvj");

		((CheckBox) v.findViewById(R.id.chk13_3)).setText("mqv");

		((CheckBox) v.findViewById(R.id.chk13_4)).setText("Pxbvev`vg");

		((CheckBox) v.findViewById(R.id.chk13_5)).setText("†Kki");

		((CheckBox) v.findViewById(R.id.chk13_6)).setText("fvix Pxbvev`vg");

		((CheckBox) v.findViewById(R.id.chk13_7))
				.setText("Ab¨vb¨ †h †Kvb Wvj ev ev`vg RvZxq");

		((CheckBox) v.findViewById(R.id.chk14_1)).setText("cwbi");

		((CheckBox) v.findViewById(R.id.chk14_2)).setText("`B");

		((CheckBox) v.findViewById(R.id.chk14_3))
				.setText("Ab¨vb¨ `ya RvZxq Lv`¨");

		((CheckBox) v.findViewById(R.id.chk15_1)).setText("Dw™¢¾ ‡Zj (WvjWv)");

		((CheckBox) v.findViewById(R.id.chk15_2)).setText("cïi Pwe");

		((CheckBox) v.findViewById(R.id.chk15_3)).setText("GK ai‡bi gvLb");

		((CheckBox) v.findViewById(R.id.chk16_1)).setText("PK‡jU");

		((CheckBox) v.findViewById(R.id.chk16_2)).setText("wgwó/ K¨vwÛ");

		((CheckBox) v.findViewById(R.id.chk16_3)).setText("wcVv");

		((CheckBox) v.findViewById(R.id.chk16_4)).setText("wgwó we¯‹zU");

		((CheckBox) v.findViewById(R.id.chk16_5)).setText("wPwb");

		((CheckBox) v.findViewById(R.id.chk17_1)).setText("¯^v` e„w× KviK");

		((CheckBox) v.findViewById(R.id.chk17_2)).setText("imyb");

		((CheckBox) v.findViewById(R.id.chk17_3)).setText("gmjv");
		((CheckBox) v.findViewById(R.id.chk17_4)).setText("jeb");

		((CheckBox) v.findViewById(R.id.chk18_1)).setText("wPswo");

		((CheckBox) v.findViewById(R.id.chk18_2)).setText("KvKov");

		((TextView) v.findViewById(R.id.textView2))
				.setText("hw` Lv`¨ ZvwjKvq bv _v‡K Zvn‡j wb‡P Lvev‡ii bvg wjLyb|");

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
							"You've been inactive for a long while. Please exit and re-login.")
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

	/*
	 * class DataIDSpinnerListener implements OnItemSelectedListener {
	 * 
	 * public void onItemSelected(AdapterView<?> parent, View arg1, int pos,
	 * long arg3) { // TODO Auto-generated method stub if (parent ==
	 * spinnerhospital) { if (parent.getItemAtPosition(pos).toString().length()
	 * > 0) { hospital = "5";// = String.valueOf(pos + 1);
	 * 
	 * } } else if (parent == spinnerward) { if
	 * (parent.getItemAtPosition(pos).toString().length() > 0) { ward =
	 * String.valueOf(pos);
	 * 
	 * } }
	 * 
	 * }
	 * 
	 * public void onNothingSelected(AdapterView<?> arg0) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 * 
	 * }
	 */
	private void FillAllDataidCombo(ViewGroup v) {

		// Data id spinners
		ArrayList<String> compids = new ArrayList<String>();
		compids.add("");
		compids.add("1");
		compids.add("2");
		compids.add("3");

		adapterSl = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item, compids);
		adapterSl
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		((Spinner) v.findViewById(R.id.sp1)).setAdapter(adapterSl);
		((Spinner) v.findViewById(R.id.spl1)).setAdapter(adapterSl);

		ArrayList<String> yearids = new ArrayList<String>();
		yearids.add("13");
		yearids.add("14");
		yearids.add("15");
		yearids.add("16");
		yearids.add("17");
		yearids.add("18");
		adapterSl = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item, yearids);
		adapterSl
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		((Spinner) v.findViewById(R.id.sp2)).setAdapter(adapterSl);
		((Spinner) v.findViewById(R.id.spl2)).setAdapter(adapterSl);

		ArrayList<String> monthids = new ArrayList<String>();
		monthids.add("01");
		monthids.add("02");
		monthids.add("03");
		monthids.add("04");
		monthids.add("05");
		monthids.add("06");
		monthids.add("07");
		monthids.add("08");
		monthids.add("09");
		monthids.add("10");
		monthids.add("11");
		monthids.add("12");

		adapterSl = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item, monthids);
		adapterSl
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		((Spinner) v.findViewById(R.id.sp3)).setAdapter(adapterSl);
		((Spinner) v.findViewById(R.id.spl3)).setAdapter(adapterSl);

		ArrayList<String> hosids = new ArrayList<String>();
		hosids.add("01");
		hosids.add("02");
		hosids.add("03");
		hosids.add("04");
		hosids.add("05");
		hosids.add("06");
		hosids.add("07");
		hosids.add("08");
		hosids.add("09");
		hosids.add("10");
		hosids.add("11");
		hosids.add("12");
		hosids.add("13");
		hosids.add("14");
		adapterSl = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item, hosids);
		adapterSl
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		((Spinner) v.findViewById(R.id.sp4)).setAdapter(adapterSl);
		((Spinner) v.findViewById(R.id.spl4)).setAdapter(adapterSl);

		ArrayList<String> patientids = new ArrayList<String>();
		patientids.add("");
		for (int i = 1; i <= 100; i++) {
			patientids.add(String.format("%03d", (i)));
		}

		adapterSl = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item, patientids);
		adapterSl
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		((Spinner) v.findViewById(R.id.sp5)).setAdapter(adapterSl);
		((Spinner) v.findViewById(R.id.spl5)).setAdapter(adapterSl);

		// strYear = System.DateTime.Today.Date.Year.ToString();
		// /strYear = strYear.Substring(2, 2);
		// intMonth = System.DateTime.Today.Date.Month;

		CommonStaticClass.SetSpinnerValueFrmString(thisactivity,
				((Spinner) v.findViewById(R.id.sp2)),
				String.valueOf(CommonStaticClass.GetYear(true)));
		CommonStaticClass.SetSpinnerValueFrmString(thisactivity,
				((Spinner) v.findViewById(R.id.spl2)),
				String.valueOf(CommonStaticClass.GetYear(true)));

		CommonStaticClass.SetSpinnerValueFrmString(
				thisactivity,
				((Spinner) v.findViewById(R.id.sp3)),
				CommonStaticClass.padLeft(
						String.valueOf(CommonStaticClass.GetMonth()), 2, "0"));
		CommonStaticClass.SetSpinnerValueFrmString(
				thisactivity,
				((Spinner) v.findViewById(R.id.spl3)),
				CommonStaticClass.padLeft(
						String.valueOf(CommonStaticClass.GetMonth()), 2, "0"));

		if (CommonStaticClass.TryParse(CommonStaticClass.userSpecificId) != null) {
			CommonStaticClass.SetSpinnerValueFrmString(thisactivity,
					((Spinner) v.findViewById(R.id.sp4)),
					String.valueOf(CommonStaticClass.userSpecificId));
			CommonStaticClass.SetSpinnerValueFrmString(thisactivity,
					((Spinner) v.findViewById(R.id.spl4)),
					String.valueOf(CommonStaticClass.userSpecificId));
		} else {

		}

		/*
		 * cboHospitalID.SelectedItem = strHosID.Trim();
		 * cboHospitalIDRe.SelectedItem = strHosID.Trim();
		 */

	}

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

		FillAllDataidCombo(v);

		confButton = (Button) v.findViewById(R.id.confButton);
		confButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				// updateTableData();
				if (IsValidDataIDUserInput(vg) == false) {
					CommonStaticClass.showMyAlert(con, "Message",
							"Please fill all fields correctly");
					return;
				}

				if (CommonStaticClass.dataId.length() > 0) {
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
				} else {
					CommonStaticClass
							.showFinalAlert(con,
									"Please confirm data id is generated by clicking GENERATE");
				}
			}

		});
	}

	private String GetPatientID() {
		if (FileRead()) {

			String s = String.format(
					"SELECT * from tblSetup WHERE AssetID = '%s'",
					CommonStaticClass.AssetID);

			Cursor mCursor = null;

			try {

				mCursor = dbHelper.getQueryCursor(s);

				if (mCursor.getCount() > 0) {

					if (mCursor.moveToFirst()) {

						do {

							CommonStaticClass.HosCode = mCursor
									.getString(mCursor
											.getColumnIndex("HosCode"));
							CommonStaticClass.LastPatientID = mCursor
									.getString(mCursor
											.getColumnIndex("LastPatientID"));

							CommonStaticClass.LastPatientID = String
									.valueOf(Integer
											.parseInt(CommonStaticClass.LastPatientID) + 1);

							CommonStaticClass.LastPatientID = String
									.format("%06d",
											Integer.parseInt(CommonStaticClass.LastPatientID));

						} while (mCursor.moveToNext());

					} else
						return null;

				} else
					return null;

			} catch (Exception e) {

			}

		}
		return null;
	}

	private boolean IsValidDataIDUserInput(ViewGroup v) {

		CommonStaticClass.dataId = ((Spinner) v.findViewById(R.id.sp1))
				.getSelectedItem().toString()
				+ ((Spinner) v.findViewById(R.id.sp2)).getSelectedItem()
						.toString()
				+ ((Spinner) v.findViewById(R.id.sp3)).getSelectedItem()
						.toString()
				+ ((Spinner) v.findViewById(R.id.sp4)).getSelectedItem()
						.toString()
				+ ((Spinner) v.findViewById(R.id.sp5)).getSelectedItem()
						.toString();
		String ReDataid = ((Spinner) v.findViewById(R.id.spl1))
				.getSelectedItem().toString()
				+ ((Spinner) v.findViewById(R.id.spl2)).getSelectedItem()
						.toString()
				+ ((Spinner) v.findViewById(R.id.spl3)).getSelectedItem()
						.toString()
				+ ((Spinner) v.findViewById(R.id.spl4)).getSelectedItem()
						.toString()
				+ ((Spinner) v.findViewById(R.id.spl5)).getSelectedItem()
						.toString();

		if (CommonStaticClass.dataId.equalsIgnoreCase(ReDataid)) {
			return true;
		}

		return false;
	}

	private void updateTableDataFrmHHID(ViewGroup v) {
		// TODO Auto-generated method stub
		Cursor cursor = null;
		try {

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

				/*
				 * CommonStaticClass.mode = CommonStaticClass.EDITMODE;
				 * CommonStaticClass.findOutNextSLNo(
				 * CommonStaticClass.questionMap.get(
				 * CommonStaticClass.currentSLNo).getQvar(),
				 * CommonStaticClass.questionMap.get(
				 * CommonStaticClass.currentSLNo).getQnext1());
				 * CommonStaticClass.nextQuestion(ParentActivity.this);
				 */

			} else {

				if (CommonStaticClass.dataId.length() < 10) {
					CommonStaticClass.showMyAlert(con, "Error",
							"Select all fields");
					return;
				}
				String entryDate = CommonStaticClass.GetDate().toString();

				sql = String
						.format("Insert into %s (dataid,VersionNo,assetid,Comp,YearID,MonthID,HosID,PatID,EntryBy,EntryDate, EntryTime) VALUES('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
								CommonStaticClass
										.GetTableName(CommonStaticClass.questionMap
												.get(CommonStaticClass.currentSLNo)
												.getQvar()),
								CommonStaticClass.dataId,
								CommonStaticClass.VersionNo,
								CommonStaticClass.AssetID, ((Spinner) v
										.findViewById(R.id.sp1))
										.getSelectedItem().toString(),
								((Spinner) v.findViewById(R.id.sp2))
										.getSelectedItem().toString(),
								((Spinner) v.findViewById(R.id.sp3))
										.getSelectedItem().toString(),
								((Spinner) v.findViewById(R.id.sp4))
										.getSelectedItem().toString(),
								((Spinner) v.findViewById(R.id.sp5))
										.getSelectedItem().toString(),
								CommonStaticClass.userSpecificId, entryDate,
								CommonStaticClass.GetTime());

				String s = String
						.format("Insert into %s (dataid,VersionNo,assetid,EntryBy,EntryDate) VALUES('%s','%s','%s','%s','%s')",
								"tblMainQuesEPT", CommonStaticClass.dataId,
								CommonStaticClass.VersionNo,
								CommonStaticClass.AssetID,
								CommonStaticClass.userSpecificId, entryDate);

				if (dbHelper.executeDMLQuery(sql)
						&& dbHelper.executeDMLQuery(s)) {

					CommonStaticClass.HosCode = ((Spinner) v
							.findViewById(R.id.sp3)).getSelectedItem()
							.toString();
					CommonStaticClass.findOutNextSLNo(
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQvar(),
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQnext1());
					CommonStaticClass.nextQuestion(ParentActivity.this);
					CommonStaticClass.addCycleStarted = true;

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cursor != null)
				cursor.close();
		}
	}

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

		// Checking
		/*
		 * if (CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo)
		 * .getQvar().equalsIgnoreCase("sec3")) {
		 * 
		 * String sqlString = "Select q2_1 from tblMainQuesEPT where dataid = '"
		 * + CommonStaticClass.dataId + "'";
		 * 
		 * String nextToGo = ""; Cursor c = dbHelper.getQueryCursor(sqlString);
		 * if (c != null) { if (c.getCount() > 0) { if (c.moveToFirst()) { do {
		 * 
		 * if (c.getString(c.getColumnIndex("q2_1"))
		 * .toString().equalsIgnoreCase("1")) { nextToGo = "sec4";
		 * 
		 * } else nextToGo = "END";
		 * 
		 * } while (c.moveToNext());
		 * 
		 * } } } }
		 * 
		 * if (CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo)
		 * .getQvar().equalsIgnoreCase("sec4")) {
		 * 
		 * String sqlString = "Select q2_2 from tblMainQuesEPT where dataid = '"
		 * + CommonStaticClass.dataId + "'";
		 * 
		 * String nextToGo = ""; Cursor c = dbHelper.getQueryCursor(sqlString);
		 * if (c != null) { if (c.getCount() > 0) { if (c.moveToFirst()) { do {
		 * 
		 * if (c.getString(c.getColumnIndex("q2_2"))
		 * .toString().equalsIgnoreCase("1")) { nextToGo = "sec5"; }
		 * 
		 * } while (c.moveToNext());
		 * 
		 * } } } }
		 * 
		 * if (CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo)
		 * .getQvar().equalsIgnoreCase("sec5")) {
		 * 
		 * String sqlString = "Select q2_3 from tblMainQuesEPT where dataid = '"
		 * + CommonStaticClass.dataId + "'";
		 * 
		 * String nextToGo = ""; Cursor c = dbHelper.getQueryCursor(sqlString);
		 * if (c != null) { if (c.getCount() > 0) { if (c.moveToFirst()) { do {
		 * 
		 * if (c.getString(c.getColumnIndex("q2_3"))
		 * .toString().equalsIgnoreCase("1")) { nextToGo = "sec6"; }
		 * 
		 * } while (c.moveToNext());
		 * 
		 * } } } }
		 * 
		 * if (CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo)
		 * .getQvar().equalsIgnoreCase("sec6")) {
		 * 
		 * String sqlString = "Select q2_4 from tblMainQuesEPT where dataid = '"
		 * + CommonStaticClass.dataId + "'";
		 * 
		 * String nextToGo = ""; Cursor c = dbHelper.getQueryCursor(sqlString);
		 * if (c != null) { if (c.getCount() > 0) { if (c.moveToFirst()) { do {
		 * 
		 * if (c.getString(c.getColumnIndex("q2_4"))
		 * .toString().equalsIgnoreCase("1")) { nextToGo = "sec7"; } else
		 * nextToGo = "END";
		 * 
		 * } while (c.moveToNext());
		 * 
		 * } } } }
		 * 
		 * if (CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo)
		 * .getQvar().equalsIgnoreCase("sec7")) {
		 * 
		 * String sqlString = "Select q2_5 from tblMainQuesEPT where dataid = '"
		 * + CommonStaticClass.dataId + "'";
		 * 
		 * String nextToGo = ""; Cursor c = dbHelper.getQueryCursor(sqlString);
		 * if (c != null) { if (c.getCount() > 0) { if (c.moveToFirst()) { do {
		 * 
		 * if (c.getString(c.getColumnIndex("q2_5"))
		 * .toString().equalsIgnoreCase("1")) {
		 * 
		 * nextToGo = "END"; }
		 * 
		 * } while (c.moveToNext());
		 * 
		 * } } } }
		 */

		/*
		 * if (nextToGo != null && nextToGo != "") {
		 * 
		 * CommonStaticClass.currentSLNo = CommonStaticClass
		 * .giveTheSLNo(currentSLNo) - 1;
		 * 
		 * CommonStaticClass.findOutNextSLNo(qName, nextToGo);
		 * CommonStaticClass.nextQuestion(ParentActivity.this); return;
		 * 
		 * }
		 */

		// checking finish

		if (CommonStaticClass.langBng) {
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

		if (qName.equalsIgnoreCase("DS23"))
			op = CommonStaticClass.findOptionsForScvb(
					dbHelper,
					CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar());
		else
			op = CommonStaticClass.findOptionsForThisQuestion(
					dbHelper,
					CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar());

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

			if (qName.equalsIgnoreCase("DS23"))
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
			@Override
			public void onClick(View v) {
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
		if (listvalues != null && listvalues.size() > 0) {
			listvalues.clear();
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
		int maxLength = 0;

		if (qName.equalsIgnoreCase("AgeYr")) {
			op = CommonStaticClass.findOptionsForScvb(
					dbHelper,
					CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar());
			maxLength = 2;
		} else if (qName.equalsIgnoreCase("WT")) {
			op = CommonStaticClass.findOptionsForScvb(
					dbHelper,
					CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar());
			maxLength = 6;
		} else if (qName.equalsIgnoreCase("HH")) {
			op = CommonStaticClass.findOptionsForScvb(
					dbHelper,
					CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar());
			maxLength = 11;
		} else if (qName.equalsIgnoreCase("Ward")) {
			op = CommonStaticClass.findOptionsForScvb(
					dbHelper,
					CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar());
			maxLength = 100;
		}

		else if (qName.equalsIgnoreCase("DS36")) {
			op = CommonStaticClass.findOptionsForScvb(
					dbHelper,
					CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar());
			maxLength = 5;
		}

		else {
			op = CommonStaticClass.findOptionsForThisQuestion(
					dbHelper,
					CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar());
			maxLength = 3;
		}

		LinearLayout.LayoutParams lnlParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams layoutParamForcheck = new LinearLayout.LayoutParams(
				((dm.widthPixels - 65) / 3) * 2, (dm.widthPixels - 65) / 3);
		LinearLayout.LayoutParams layoutParamForSpin = new LinearLayout.LayoutParams(
				((dm.widthPixels + 65) / 3),
				LinearLayout.LayoutParams.WRAP_CONTENT);

		for (int i = 0; i < op.codeList.size(); i++) {

			listvalues.add("-1");
		}

		for (int i = 0; i < op.codeList.size(); i++) {

			Log.e("op.qidList.get(i)", op.qidList.get(i));

			LinearLayout ln = new LinearLayout(this);
			ln.setOrientation(LinearLayout.HORIZONTAL);

			ln.setPadding(0, 0, 0, 40);

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

			if (qName.equalsIgnoreCase("AgeYr"))
				et.setInputType(InputType.TYPE_CLASS_NUMBER);
			else if (qName.equalsIgnoreCase("WT")
					|| qName.equalsIgnoreCase("DS36"))
				et.setInputType(InputType.TYPE_CLASS_NUMBER
						| InputType.TYPE_NUMBER_FLAG_DECIMAL);

			et.setFilters(new InputFilter[] { new InputFilter.LengthFilter(
					maxLength) });

			layoutParamForSpin.weight = 1;
			ln.addView(et, 0, layoutParamForSpin);
			et.setVisibility(View.INVISIBLE);
			ln.addView(checkButton, 0, layoutParamForcheck);

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

							listvalues.set(checkButton.getId(), s.toString());

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
								listvalues.set(checkButton.getId(), "-1");
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
								 * ).toLowerCase() .contains("Ab¨vb¨: wjLyb")) {
								 * 
								 * 
								 * //listvalues.set(checkButton.getId(), 1);
								 * et.setVisibility(View.INVISIBLE); return; } }
								 * else { //listvalues.set(checkButton.getId(),
								 * 1); et.setVisibility(View.VISIBLE); }
								 */
								et.setVisibility(View.VISIBLE);

							} else {
								listvalues.set(checkButton.getId(), "-1");
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
						checkButton.setChecked(true);
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
					checkButton.setChecked(true);
					checkButton.setEnabled(false);
					checkButton.setTextColor(Color.BLACK);
					Log.e("column", "exist column:" + inColumn);
					try {
						String a = mCursor1.getString(mCursor1
								.getColumnIndex(inColumn));
						Log.e("a", a + "   lklklk");

						if (a.length() > 0 && !a.equalsIgnoreCase("-1")) {

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
					// if (!gotoskip()) {

					CommonStaticClass.findOutNextSLNo(
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQvar(),
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQnext1());
					CommonStaticClass.nextQuestion(ParentActivity.this);
					// }
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

		if (checkIfSingleOptionIsCheckedFrmMultipleCheckNumeric()) {

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
		return false;
	}

	private boolean checkIfSingleOptionIsCheckedFrmMultipleCheckNumeric() {
		for (int i = 0; i < listvalues.size(); i++) {
			if (!(listvalues.get(i) == "-1")) {
				return true;
			}

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

								if (qName.equalsIgnoreCase("q6_1")
										|| qName.equalsIgnoreCase("q6_2")) {
									if (checkButton.getText().toString()
											.equalsIgnoreCase("Don’t need")
											|| checkButton
													.getText()
													.toString()
													.equalsIgnoreCase(
															"15 cÖ‡qvRb g‡b Kwibv")) {
										String t = CommonStaticClass.langBng ? "15 cÖ‡qvRb g‡b Kwibv"
												: "Don’t need";
										uncheckAllFrmMultipleChoice(
												(ViewGroup) v, t);
									} else {
										String t = CommonStaticClass.langBng ? "15 cÖ‡qvRb g‡b Kwibv"
												: "Don’t need";
										uncheckExceptALLFrmMultipleChoice(
												(ViewGroup) v, t);
									}
								}

								if (qName.equalsIgnoreCase("q6_1")
										|| qName.equalsIgnoreCase("q6_2")) {
									if (checkButton
											.getText()
											.toString()
											.equalsIgnoreCase(
													"Don’t wash hands")
											|| checkButton
													.getText()
													.toString()
													.equalsIgnoreCase(
															"16 nvZ ayB bv")) {
										String t = CommonStaticClass.langBng ? "16 nvZ ayB bv"
												: "Don’t wash hands";
										uncheckAllFrmMultipleChoice(
												(ViewGroup) v, t);
									} else {
										String t = CommonStaticClass.langBng ? "16 nvZ ayB bv"
												: "Don’t wash hands";
										uncheckExceptALLFrmMultipleChoice(
												(ViewGroup) v, t);
									}
								}

								if (qName.equalsIgnoreCase("q3_4")
										|| qName.equalsIgnoreCase("q3_10")
										|| qName.equalsIgnoreCase("q3_16")) {
									if (checkButton
											.getText()
											.toString()
											.equalsIgnoreCase(
													"555. Can’t remember")
											|| checkButton
													.getText()
													.toString()
													.equalsIgnoreCase(
															"555. g‡b Ki‡Z cv‡i bv")) {
										String t = CommonStaticClass.langBng ? "555. g‡b Ki‡Z cv‡i bv"
												: "555. Can’t remember";
										uncheckAllFrmMultipleChoice(
												(ViewGroup) v, t);
									} else {
										String t = CommonStaticClass.langBng ? "555. g‡b Ki‡Z cv‡i bv"
												: "555. Can’t remember";
										uncheckExceptALLFrmMultipleChoice(
												(ViewGroup) v, t);
									}
								}

								if (qName.equalsIgnoreCase("q3_8")) {

									if (checkButton.getText().toString()
											.equalsIgnoreCase("888. N/A")
											|| checkButton
													.getText()
													.toString()
													.equalsIgnoreCase(
															"888. cÖ‡hvR¨ bq")) {
										String t = CommonStaticClass.langBng ? "888. cÖ‡hvR¨ b"
												: "888. N/A";
										uncheckAllFrmMultipleChoice(
												(ViewGroup) v, t);
									} else {
										String t = CommonStaticClass.langBng ? "888. cÖ‡hvR¨ b"
												: "888. N/A";
										uncheckExceptALLFrmMultipleChoice(
												(ViewGroup) v, t);
									}

									if (checkButton
											.getText()
											.toString()
											.equalsIgnoreCase(
													"555. Can’t remember")
											|| checkButton
													.getText()
													.toString()
													.equalsIgnoreCase(
															"555. g‡b Ki‡Z cv‡i bv")) {
										String t = CommonStaticClass.langBng ? "555. g‡b Ki‡Z cv‡i bv"
												: "555. Can’t remember";
										uncheckAllFrmMultipleChoice(
												(ViewGroup) v, t);
									} else {
										String t = CommonStaticClass.langBng ? "555. g‡b Ki‡Z cv‡i bv"
												: "555. Can’t remember";
										uncheckExceptALLFrmMultipleChoice(
												(ViewGroup) v, t);
									}
								}

								// 6_4
								if (qName.equalsIgnoreCase("q6_4")) {

									if (checkButton.getText().toString()
											.equalsIgnoreCase("N/A")
											|| checkButton
													.getText()
													.toString()
													.equalsIgnoreCase(
															"888 cÖ‡hvR¨ bq")) {
										String t = CommonStaticClass.langBng ? "888 cÖ‡hvR¨ bq"
												: "N/A";
										uncheckAllFrmMultipleChoice(
												(ViewGroup) v, t);
									} else {
										String t = CommonStaticClass.langBng ? "888 cÖ‡hvR¨ bq"
												: "N/A";
										uncheckExceptALLFrmMultipleChoice(
												(ViewGroup) v, t);
									}

									if (checkButton
											.getText()
											.toString()
											.equalsIgnoreCase(
													"555. Can’t remember")
											|| checkButton
													.getText()
													.toString()
													.equalsIgnoreCase(
															"555. g‡b Ki‡Z cv‡i bv")) {
										String t = CommonStaticClass.langBng ? "555. g‡b Ki‡Z cv‡i bv"
												: "555. Can’t remember";
										uncheckAllFrmMultipleChoice(
												(ViewGroup) v, t);
									} else {
										String t = CommonStaticClass.langBng ? "555. g‡b Ki‡Z cv‡i bv"
												: "555. Can’t remember";
										uncheckExceptALLFrmMultipleChoice(
												(ViewGroup) v, t);
									}
								}

								// 9_6
								if (qName.equalsIgnoreCase("q9_6")) {

									if (checkButton
											.getText()
											.toString()
											.equalsIgnoreCase(
													"None of the above")
											|| checkButton
													.getText()
													.toString()
													.equalsIgnoreCase(
															"9 Dc‡ii †KvbwUB bv")) {
										String t = CommonStaticClass.langBng ? "9 Dc‡ii †KvbwUB bv"
												: "None of the above";
										uncheckAllFrmMultipleChoice(
												(ViewGroup) v, t);
									} else {
										String t = CommonStaticClass.langBng ? "9 Dc‡ii †KvbwUB bv"
												: "None of the above";
										uncheckExceptALLFrmMultipleChoice(
												(ViewGroup) v, t);
									}

									if (checkButton
											.getText()
											.toString()
											.equalsIgnoreCase(
													"No available water")
											|| checkButton
													.getText()
													.toString()
													.equalsIgnoreCase(
															"10 cvwb wQj bv")) {
										String t = CommonStaticClass.langBng ? "10 cvwb wQj bv"
												: "No available water";
										uncheckAllFrmMultipleChoice(
												(ViewGroup) v, t);
									} else {
										String t = CommonStaticClass.langBng ? "10 cvwb wQj bv"
												: "No available water";
										uncheckExceptALLFrmMultipleChoice(
												(ViewGroup) v, t);
									}

								}
								// 10_4
								if (qName.equalsIgnoreCase("q10_4")) {

									if (checkButton.getText().toString()
											.equalsIgnoreCase("Found clean")
											|| checkButton
													.getText()
													.toString()
													.equalsIgnoreCase(
															"K. gqjv bvB")) {
										String t = CommonStaticClass.langBng ? "K. gqjv bvB"
												: "Found clean";
										uncheckAllFrmMultipleChoice(
												(ViewGroup) v, t);
									} else {
										String t = CommonStaticClass.langBng ? "K. gqjv bvB"
												: "Found clean";
										uncheckExceptALLFrmMultipleChoice(
												(ViewGroup) v, t);
									}

								}

								// Zoonotic
								// q40

								if (qName.equalsIgnoreCase("q40")) {

									if (checkButton
											.getText()
											.toString()
											.equalsIgnoreCase(
													"9. None of the above")
											|| checkButton
													.getText()
													.toString()
													.equalsIgnoreCase(
															"9. cÖ‡hvR¨ bq (hw` †KD ¯ck© K‡i bv _v‡K)")) {
										String t = CommonStaticClass.langBng ? "9. cÖ‡hvR¨ bq (hw` †KD ¯ck© K‡i bv _v‡K)"
												: "9. None of the above";
										uncheckAllFrmMultipleChoice(
												(ViewGroup) v, t);
									} else {
										String t = CommonStaticClass.langBng ? "9. cÖ‡hvR¨ bq (hw` †KD ¯ck© K‡i bv _v‡K)"
												: "9. None of the above";
										uncheckExceptALLFrmMultipleChoice(
												(ViewGroup) v, t);
									}

								}
								// q45
								if (qName.equalsIgnoreCase("q45")) {

									if (checkButton
											.getText()
											.toString()
											.equalsIgnoreCase(
													"None of the above")
											|| checkButton
													.getText()
													.toString()
													.equalsIgnoreCase(
															"8. cÖ‡hvR¨ bq (hw` †KD ¯ck© K‡i bv _v‡K)")) {
										String t = CommonStaticClass.langBng ? "8. cÖ‡hvR¨ bq (hw` †KD ¯ck© K‡i bv _v‡K)"
												: "None of the above";
										uncheckAllFrmMultipleChoice(
												(ViewGroup) v, t);
									} else {
										String t = CommonStaticClass.langBng ? "8. cÖ‡hvR¨ bq (hw` †KD ¯ck© K‡i bv _v‡K)"
												: "None of the above";
										uncheckExceptALLFrmMultipleChoice(
												(ViewGroup) v, t);
									}
								}

								// q49
								if (qName.equalsIgnoreCase("q49")) {

									if (checkButton.getText().toString()
											.equalsIgnoreCase("3.Dont know")
											|| checkButton
													.getText()
													.toString()
													.equalsIgnoreCase(
															"৩. জানিনা")) {
										String t = CommonStaticClass.langBng ? "৩. জানিনা"
												: "3.Dont know";
										uncheckAllFrmMultipleChoice(
												(ViewGroup) v, t);
									} else {
										String t = CommonStaticClass.langBng ? "৩. জানিনা"
												: "3.Dont know";
										uncheckExceptALLFrmMultipleChoice(
												(ViewGroup) v, t);
									}
								}

								if (qName.equalsIgnoreCase("q67")) {

									if (checkButton
											.getText()
											.toString()
											.equalsIgnoreCase(
													"5. Didn’t clean the processing place")
											|| checkButton
													.getText()
													.toString()
													.equalsIgnoreCase(
															"5. cwi®‹vi Kiv nqwb")) {
										String t = CommonStaticClass.langBng ? "5. cwi®‹vi Kiv nqwb"
												: "5. Didn’t clean the processing place";
										uncheckAllFrmMultipleChoice(
												(ViewGroup) v, t);
									} else {
										String t = CommonStaticClass.langBng ? "5. cwi®‹vi Kiv nqwb"
												: "5. Didn’t clean the processing place";
										uncheckExceptALLFrmMultipleChoice(
												(ViewGroup) v, t);
									}
								}

								if (qName.equalsIgnoreCase("q79")) {

									if (checkButton.getText().toString()
											.equalsIgnoreCase("8. Don’t know")
											|| checkButton
													.getText()
													.toString()
													.equalsIgnoreCase(
															"8. Rvwbbv")) {
										String t = CommonStaticClass.langBng ? "8. Rvwbbv"
												: "8. Don’t know";
										uncheckAllFrmMultipleChoice(
												(ViewGroup) v, t);
									} else {
										String t = CommonStaticClass.langBng ? "8. Rvwbbv"
												: "8. Don’t know";
										uncheckExceptALLFrmMultipleChoice(
												(ViewGroup) v, t);
									}
								}

								// q103a

								if (qName.equalsIgnoreCase("q103a")
										|| qName.equalsIgnoreCase("q103b")) {

									if (checkButton.getText().toString()
											.equalsIgnoreCase("Never Seen")
											|| checkButton
													.getText()
													.toString()
													.equalsIgnoreCase(
															"KL‡bv †`wLbvB")) {
										String t = CommonStaticClass.langBng ? "KL‡bv †`wLbvB"
												: "Never Seen";
										uncheckAllFrmMultipleChoice(
												(ViewGroup) v, t);
									} else {
										String t = CommonStaticClass.langBng ? "KL‡bv †`wLbvB"
												: "Never Seen";
										uncheckExceptALLFrmMultipleChoice(
												(ViewGroup) v, t);
									}
								}
								// q111
								if (qName.equalsIgnoreCase("q111")) {

									if (checkButton
											.getText()
											.toString()
											.equalsIgnoreCase("12.  Don’t know")
											|| checkButton
													.getText()
													.toString()
													.equalsIgnoreCase(
															"12. Rvwbbv")) {
										String t = CommonStaticClass.langBng ? "12. Rvwbbv"
												: "12.  Don’t know";
										uncheckAllFrmMultipleChoice(
												(ViewGroup) v, t);
									} else {
										String t = CommonStaticClass.langBng ? "12. Rvwbbv"
												: "12.  Don’t know";
										uncheckExceptALLFrmMultipleChoice(
												(ViewGroup) v, t);
									}
								}

								// q118_R1_C3
								if (qName.equalsIgnoreCase("q118_R1_C3")
										|| qName.equalsIgnoreCase("q118_R2_C3")
										|| qName.equalsIgnoreCase("q118_R3_C3")
										|| qName.equalsIgnoreCase("q118_R4_C3")
										|| qName.equalsIgnoreCase("q118_R5_C3")
										|| qName.equalsIgnoreCase("q118_R6_C3")) {

									if (checkButton
											.getText()
											.toString()
											.equalsIgnoreCase("11.  Don’t know")
											|| checkButton
													.getText()
													.toString()
													.equalsIgnoreCase(
															"11. Rvwbbv")) {
										String t = CommonStaticClass.langBng ? "11. Rvwbbv"
												: "11.  Don’t know";
										uncheckAllFrmMultipleChoice(
												(ViewGroup) v, t);
									} else {
										String t = CommonStaticClass.langBng ? "11. Rvwbbv"
												: "11.  Don’t know";
										uncheckExceptALLFrmMultipleChoice(
												(ViewGroup) v, t);
									}
								}

								// q121
								if (qName.equalsIgnoreCase("q121A")) {

									if (checkButton.getText().toString()
											.equalsIgnoreCase("6. Don’t know")
											|| checkButton
													.getText()
													.toString()
													.equalsIgnoreCase(
															"6. Rvwbbv")) {
										String t = CommonStaticClass.langBng ? "6. Rvwbbv"
												: "6. Don’t know";
										uncheckAllFrmMultipleChoice(
												(ViewGroup) v, t);
									} else {
										String t = CommonStaticClass.langBng ? "6. Rvwbbv"
												: "6. Don’t know";
										uncheckExceptALLFrmMultipleChoice(
												(ViewGroup) v, t);
									}
								}

								// q126
								if (qName.equalsIgnoreCase("q126")) {

									if (checkButton
											.getText()
											.toString()
											.equalsIgnoreCase("11.  Don’t know")
											|| checkButton
													.getText()
													.toString()
													.equalsIgnoreCase(
															"11. Rvwbbv")) {
										String t = CommonStaticClass.langBng ? "11. Rvwbbv"
												: "11.  Don’t know";
										uncheckAllFrmMultipleChoice(
												(ViewGroup) v, t);
									} else {
										String t = CommonStaticClass.langBng ? "11. Rvwbbv"
												: "11.  Don’t know";
										uncheckExceptALLFrmMultipleChoice(
												(ViewGroup) v, t);
									}
								}

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

			/*
			 * if (CommonStaticClass.mode
			 * .equalsIgnoreCase(CommonStaticClass.EDITMODE)) {
			 */
			checkIfChckButtonShouldBeCheckedFrmMultipleChoice(checkButton,
					op.qidList.get(i));
			// }
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

		if (checkIfSingleOptionIsCheckedFrmMultipleChoice())

		{

			Iterator it = edList.entrySet().iterator();
			while (it.hasNext()) {
				LinkedHashMap.Entry<Integer, EditText> pairs = (LinkedHashMap.Entry<Integer, EditText>) it
						.next();
				if (pairs.getValue().getVisibility() == View.VISIBLE) {
					if (pairs.getValue().getText().toString().length() > 0) {
						String sq = "";
						if (!CommonStaticClass.isMember)
							sq = "UPDATE "
									+ CommonStaticClass.questionMap.get(
											CommonStaticClass.currentSLNo)
											.getTablename() + " SET "
									+ op.qnList.get(pairs.getKey()) + " = '"
									+ pairs.getValue().getText().toString()
									+ "' where dataid='"
									+ CommonStaticClass.dataId + "'";
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

				if (!gotoskip()) {

					CommonStaticClass.findOutNextSLNo(
							qName,
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQnext1());
					CommonStaticClass.nextQuestion(ParentActivity.this);

				}

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
						.setText("	Ab¨vb¨ (wbw`©ó K‡i wjLyb)");
				((RadioButton) findViewById(R.id.radio2))
						.setText("g‡b Ki‡Z cv‡i bv");
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

				/*
				 * if (qName.equalsIgnoreCase("q5")) { String lineNumber =
				 * s.toString(); if (lineNumber.length() > 2) {
				 * CommonStaticClass.showMyAlert(con, "Message",
				 * "Number should be in two digit"); infoText.setText("");
				 * return; } }
				 */

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
				// String messageforlength =
				// CommonStaticClass.IsValidLength(qName,
				// infoText.getText().toString());
				// if (messageforlength.length() == 0)
				updateTableDataFrmNumeric();
				// else {
				// CommonStaticClass.showMyAlert(con, "Invalid Length",
				// messageforlength);
				// }
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

		if (qAns.length() > 0 || qName.equalsIgnoreCase("HH")
				|| qName.equalsIgnoreCase("SL")
				|| qName.equalsIgnoreCase("PID")) {

			if (qName.equalsIgnoreCase("c1_6")) {

				if (qAns.length() > 2) {

					CommonStaticClass.showMyAlert(con, "Message",
							"Number should be in two digit");
					return;
				}

				if (Integer.parseInt(qAns) >= 12
						&& Integer.parseInt(qAns) <= 99) {

				} else {
					CommonStaticClass.showMyAlert(con, "Message",
							"Number should be between 12 to 99");
					return;
				}

			}
			if (qName.equalsIgnoreCase("c613_days")) {

				if (qAns.length() > 1) {

					CommonStaticClass.showMyAlert(con, "Message",
							"Number should be in two digit");
					return;
				}

				if (Integer.parseInt(qAns) >= 1 && Integer.parseInt(qAns) <= 7) {

				} else {
					CommonStaticClass.showMyAlert(con, "Message",
							"Number should be between 1 to 7");
					return;
				}

			}

			// for two digit check only
			if (qName.equalsIgnoreCase("c607")
					|| qName.equalsIgnoreCase("c610a")
					|| qName.equalsIgnoreCase("q1_5")
					|| qName.equalsIgnoreCase("q1_6")) {
				String lineNumber = qAns.toString();
				if (lineNumber.length() > 2) {

					CommonStaticClass.showMyAlert(con, "Message",
							"Number should be in two digit");
					return;

				}
			}

			if (qName.equalsIgnoreCase("c607c_days")) {

				String lineNumber = qAns.toString();

				if (Integer.parseInt(lineNumber) <= 0
						|| Integer.parseInt(lineNumber) > 7) {

					CommonStaticClass.showMyAlert(con, "Message",
							"Number should be between 0 to 7");
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

				if (qName.equalsIgnoreCase("DS27")) {
					if (Integer.parseInt(qAns) == 0) {

						CommonStaticClass.qskipList.add("DS28D");
						CommonStaticClass.qskipList.add("DS28H");

					} else {
						CommonStaticClass.qskipList.remove("DS28D");
						CommonStaticClass.qskipList.remove("DS28H");
					}
				}

				if (qName.equalsIgnoreCase("AgeYr")) {
					if (Integer.parseInt(qAns) > 5) {
						CommonStaticClass.qskipList.add("DS60");
						CommonStaticClass.qskipList.add("DS61");
						CommonStaticClass.qskipList.add("DS62");
						CommonStaticClass.qskipList.add("DS63");
						CommonStaticClass.qskipList.add("DS64");
						CommonStaticClass.qskipList.add("DS65");
						CommonStaticClass.qskipList.add("DS66");
						CommonStaticClass.qskipList.add("DS67");
						CommonStaticClass.qskipList.add("DS68");
						CommonStaticClass.qskipList.add("DS69");

					} else {
						CommonStaticClass.qskipList.remove("DS60");
						CommonStaticClass.qskipList.remove("DS61");
						CommonStaticClass.qskipList.remove("DS62");
						CommonStaticClass.qskipList.remove("DS63");
						CommonStaticClass.qskipList.remove("DS64");
						CommonStaticClass.qskipList.remove("DS65");
						CommonStaticClass.qskipList.remove("DS66");
						CommonStaticClass.qskipList.remove("DS67");
						CommonStaticClass.qskipList.remove("DS68");
						CommonStaticClass.qskipList.remove("DS69");

					}
				}

				else if (qName.equalsIgnoreCase("c613_days")) {

					if (qAns.equalsIgnoreCase("00")) {

						CommonStaticClass.findOutNextSLNo(
								CommonStaticClass.questionMap.get(
										CommonStaticClass.currentSLNo)
										.getQvar(), "c615");
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
					.equalsIgnoreCase("HH")) {
				if (Integer.parseInt(infoText.getText().toString()) == 0) {

					return true;
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
				num1.setText("wbw`©ó Lvbv ");
				num2.setText("Ab¨vb¨ Lvbv ");
			} else if (qName.equalsIgnoreCase("q310")) {
				num1.setText("cÖwZw`b Lvevi cvwb msMÖn Kivi msL¨v ");
				num2.setText("cÖwZw`b GKzqvU¨ve e¨envi Kivi msL¨v ");
			} else if (qName.equalsIgnoreCase("q621")
					|| qName.equalsIgnoreCase("q622")) {
				num1.setText("wbw`©ó Lvbv");
				num2.setText("Ab¨vb¨ Lvbv ");
			} else if (qName.equalsIgnoreCase("q615")) {
				num1.setText("cÖwZw`b Lvevi cvwb msMÖn Kivi msL¨v ");
				num2.setText("cÖwZw`b GKzqvU¨ve e¨envi Kivi msL¨v ");
			}
			if (qName.equalsIgnoreCase("q1_12_1")
					|| qName.equalsIgnoreCase("q1_12_2")) {
				num1.setText("QvÎ");
				num2.setText("QvÎx");
			}
			if (qName.equalsIgnoreCase("DS18H")) {
				num1.setText("wk¶K");
				num2.setText("wkw¶Kv");
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
			}

			else if (qName.equalsIgnoreCase("DS28H")
					|| qName.equalsIgnoreCase("DS18H")
					|| qName.equalsIgnoreCase("DS32H")
					|| qName.equalsIgnoreCase("DS35H")) {
				num1.setText("Hours");
				num2.setText("Days");
			}

			else {
				if (qName.equalsIgnoreCase("q1_12_1")
						|| qName.equalsIgnoreCase("q1_12_2")) {
					num1.setText("Boy");
					num2.setText("Girls");
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
		infoText1.setText("");
		infoText2.setText("");
		if (mCursor1.moveToFirst()) {
			do {
				if (mCursor1.getColumnIndex(qName1) != -1) {
					String a = mCursor1.getString(mCursor1
							.getColumnIndex(qName1)) + "";
					infoText1.setText((a.length() > 0
							&& (!a.equalsIgnoreCase("-1")) && (!a
							.equalsIgnoreCase("null"))) ? a : "");
				}
				if (mCursor1.getColumnIndex(qName2) != -1) {
					String a = mCursor1.getString(mCursor1
							.getColumnIndex(qName2)) + "";
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

							/*
							 * Toast.makeText(getApplicationContext(),
							 * String.valueOf(code).toString(),
							 * Toast.LENGTH_SHORT).show();
							 */

						}
					}
				});

		/*
		 * for (int i = 0; i < op.codeList.size(); i++) {
		 * 
		 * RadioButton newRadioButton = new RadioButton(this); if
		 * (CommonStaticClass.langBng) { if (op.capBngList.get(i).length() > 0)
		 * { Typeface font = Typeface.createFromAsset(getAssets(),
		 * "Siyam Rupali ANSI.ttf"); newRadioButton.setTypeface(font); } ;
		 * newRadioButton .setText(op.capBngList.get(i).length() > 0 ?
		 * op.capBngList .get(i) : op.capEngList.get(i));
		 * 
		 * } else { newRadioButton.setTypeface(null);
		 * newRadioButton.setText(op.capEngList.get(i));
		 * 
		 * } newRadioButton.setId(op.codeList.get(i)); //
		 * if(CommonStaticClass.mode
		 * .equalsIgnoreCase(CommonStaticClass.EDITMODE)){ if (idIfEdit ==
		 * op.codeList.get(i)) { newRadioButton.setChecked(true); code =
		 * idIfEdit; } // } LinearLayout.LayoutParams layoutParams = new
		 * RadioGroup.LayoutParams( RadioGroup.LayoutParams.WRAP_CONTENT,
		 * RadioGroup.LayoutParams.WRAP_CONTENT);
		 * mRadioGroup.addView(newRadioButton, i, layoutParams); }
		 */

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

					if (CommonStaticClass.questionMap
							.get(CommonStaticClass.currentSLNo).getQvar()
							.equalsIgnoreCase("q2_1")

							|| CommonStaticClass.questionMap
									.get(CommonStaticClass.currentSLNo)
									.getQvar().equalsIgnoreCase("q2_2")

							|| CommonStaticClass.questionMap
									.get(CommonStaticClass.currentSLNo)
									.getQvar().equalsIgnoreCase("q2_3")

							|| CommonStaticClass.questionMap
									.get(CommonStaticClass.currentSLNo)
									.getQvar().equalsIgnoreCase("q2_4")

							|| CommonStaticClass.questionMap
									.get(CommonStaticClass.currentSLNo)
									.getQvar().equalsIgnoreCase("q2_5")

					) {

						CommonStaticClass.qskipList.remove("sec3");
						CommonStaticClass.qskipList.remove("q3_1");
						CommonStaticClass.qskipList.remove("sec4");
						CommonStaticClass.qskipList.remove("q4_1");
						CommonStaticClass.qskipList.remove("q4_2");
						CommonStaticClass.qskipList.remove("q4_3");
						CommonStaticClass.qskipList.remove("q4_4");
						CommonStaticClass.qskipList.remove("sec5");
						CommonStaticClass.qskipList.remove("q5_1");
						CommonStaticClass.qskipList.remove("q5_1b");
						CommonStaticClass.qskipList.remove("q5_1bOther");
						CommonStaticClass.qskipList.remove("q5_2");
						CommonStaticClass.qskipList.remove("q5_2b");
						CommonStaticClass.qskipList.remove("q5_2bOther");
						CommonStaticClass.qskipList.remove("q5_3");
						CommonStaticClass.qskipList.remove("q5_3b");
						CommonStaticClass.qskipList.remove("q5_3bOther");
						CommonStaticClass.qskipList.remove("q5_4");
						CommonStaticClass.qskipList.remove("q5_4b");
						CommonStaticClass.qskipList.remove("q5_4bOther");
						CommonStaticClass.qskipList.remove("q5_5");
						CommonStaticClass.qskipList.remove("q5_5b");
						CommonStaticClass.qskipList.remove("q5_5bOther");
						CommonStaticClass.qskipList.remove("q5_6");
						CommonStaticClass.qskipList.remove("q5_6b");
						CommonStaticClass.qskipList.remove("q5_6bOther");
						CommonStaticClass.qskipList.remove("q5_7");
						CommonStaticClass.qskipList.remove("q5_7b");
						CommonStaticClass.qskipList.remove("q5_7bOther");
						CommonStaticClass.qskipList.remove("q5_8");
						CommonStaticClass.qskipList.remove("q5_8b");
						CommonStaticClass.qskipList.remove("q5_8bOther");
						CommonStaticClass.qskipList.remove("q5_9");
						CommonStaticClass.qskipList.remove("q5_9b");
						CommonStaticClass.qskipList.remove("q5_9bOther");
						CommonStaticClass.qskipList.remove("q5_10");
						CommonStaticClass.qskipList.remove("q5_10Other");
						CommonStaticClass.qskipList.remove("q5_10b");
						CommonStaticClass.qskipList.remove("q5_10bOther");

						CommonStaticClass.qskipList.remove("sec6");
						CommonStaticClass.qskipList.remove("q6_1");
						CommonStaticClass.qskipList.remove("q6_2");
						CommonStaticClass.qskipList.remove("q6_3");
						CommonStaticClass.qskipList.remove("q6_4");
						CommonStaticClass.qskipList.remove("q6_5");
						CommonStaticClass.qskipList.remove("q6_6");
						CommonStaticClass.qskipList.remove("q6_7");
						CommonStaticClass.qskipList.remove("q6_8");

						CommonStaticClass.qskipList.remove("sec7");
						CommonStaticClass.qskipList.remove("q7_1");
						CommonStaticClass.qskipList.remove("q7_1b");
						CommonStaticClass.qskipList.remove("q7_1bOther");
						CommonStaticClass.qskipList.remove("q7_2");
						CommonStaticClass.qskipList.remove("q7_2b");
						CommonStaticClass.qskipList.remove("q7_2bOther");
						CommonStaticClass.qskipList.remove("q7_3");
						CommonStaticClass.qskipList.remove("q7_3b");
						CommonStaticClass.qskipList.remove("q7_3bOther");
						CommonStaticClass.qskipList.remove("q7_4");
						CommonStaticClass.qskipList.remove("q7_4b");
						CommonStaticClass.qskipList.remove("q7_4bOther");
						CommonStaticClass.qskipList.remove("q7_5");
						CommonStaticClass.qskipList.remove("q7_5b");
						CommonStaticClass.qskipList.remove("q7_5bOther");
						CommonStaticClass.qskipList.remove("q7_6");
						CommonStaticClass.qskipList.remove("q7_6b");
						CommonStaticClass.qskipList.remove("q7_6bOther");
						CommonStaticClass.qskipList.remove("q7_7");
						CommonStaticClass.qskipList.remove("q7_7b");
						CommonStaticClass.qskipList.remove("q7_7bOther");
						CommonStaticClass.qskipList.remove("q7_8");
						CommonStaticClass.qskipList.remove("q7_8Other");
						CommonStaticClass.qskipList.remove("q7_8b");
						CommonStaticClass.qskipList.remove("q7_8bOther");

						String sqlString = "Select q2_1,q2_2,q2_3,q2_4,q2_5 from tblMainQuesEPT where dataid = '"
								+ CommonStaticClass.dataId + "'";

						// String nextToGo = "";
						Cursor c = dbHelper.getQueryCursor(sqlString);
						if (c != null) {
							if (c.getCount() > 0) {
								if (c.moveToFirst()) {
									do {

										/*
										 * if (CommonStaticClass.questionMap
										 * .get(CommonStaticClass.currentSLNo)
										 * .getQvar() .equalsIgnoreCase("q2_1"))
										 * {
										 */
										if (c.getString(c
												.getColumnIndex("q2_1")) != null) {
											if (c.getString(
													c.getColumnIndex("q2_1"))
													.toString()
													.equalsIgnoreCase("2")) {
												CommonStaticClass.qskipList
														.add("sec3");
												CommonStaticClass.qskipList
														.add("q3_1");

												// nextToGo = "q2_2";

											} else {

											}
										}
										// }

										/*
										 * if (CommonStaticClass.questionMap
										 * .get(CommonStaticClass.currentSLNo)
										 * .getQvar() .equalsIgnoreCase("q2_2"))
										 * {
										 */
										if (c.getString(c
												.getColumnIndex("q2_2")) != null) {
											if (c.getString(
													c.getColumnIndex("q2_2"))
													.toString()
													.equalsIgnoreCase("2")) {
												CommonStaticClass.qskipList
														.add("sec4");
												CommonStaticClass.qskipList
														.add("q4_1");
												CommonStaticClass.qskipList
														.add("q4_2");
												CommonStaticClass.qskipList
														.add("q4_3");
												CommonStaticClass.qskipList
														.add("q4_4");
												// nextToGo = "q2_3";
											}
										}
										// }

										/*
										 * if (CommonStaticClass.questionMap
										 * .get(CommonStaticClass.currentSLNo)
										 * .getQvar() .equalsIgnoreCase("q2_3"))
										 * {
										 */
										if (c.getString(c
												.getColumnIndex("q2_3")) != null) {
											if (c.getString(
													c.getColumnIndex("q2_3"))
													.toString()
													.equalsIgnoreCase("2")) {
												CommonStaticClass.qskipList
														.add("sec5");
												CommonStaticClass.qskipList
														.add("q5_1");
												CommonStaticClass.qskipList
														.add("q5_1b");
												CommonStaticClass.qskipList
														.add("q5_1bOther");
												CommonStaticClass.qskipList
														.add("q5_2");
												CommonStaticClass.qskipList
														.add("q5_2b");
												CommonStaticClass.qskipList
														.add("q5_2bOther");
												CommonStaticClass.qskipList
														.add("q5_3");
												CommonStaticClass.qskipList
														.add("q5_3b");
												CommonStaticClass.qskipList
														.add("q5_3bOther");
												CommonStaticClass.qskipList
														.add("q5_4");
												CommonStaticClass.qskipList
														.add("q5_4b");
												CommonStaticClass.qskipList
														.add("q5_4bOther");
												CommonStaticClass.qskipList
														.add("q5_5");
												CommonStaticClass.qskipList
														.add("q5_5b");
												CommonStaticClass.qskipList
														.add("q5_5bOther");
												CommonStaticClass.qskipList
														.add("q5_6");
												CommonStaticClass.qskipList
														.add("q5_6b");
												CommonStaticClass.qskipList
														.add("q5_6bOther");
												CommonStaticClass.qskipList
														.add("q5_7");
												CommonStaticClass.qskipList
														.add("q5_7b");
												CommonStaticClass.qskipList
														.add("q5_7bOther");
												CommonStaticClass.qskipList
														.add("q5_8");
												CommonStaticClass.qskipList
														.add("q5_8b");
												CommonStaticClass.qskipList
														.add("q5_8bOther");
												CommonStaticClass.qskipList
														.add("q5_9");
												CommonStaticClass.qskipList
														.add("q5_9b");
												CommonStaticClass.qskipList
														.add("q5_9bOther");
												CommonStaticClass.qskipList
														.add("q5_10");
												CommonStaticClass.qskipList
														.add("q5_10Other");
												CommonStaticClass.qskipList
														.add("q5_10b");
												CommonStaticClass.qskipList
														.add("q5_10bOther");
												// nextToGo = "q2_4";
											}
										}
										// }

										/*
										 * if (CommonStaticClass.questionMap
										 * .get(CommonStaticClass.currentSLNo)
										 * .getQvar() .equalsIgnoreCase("q2_4"))
										 * {
										 */
										if (c.getString(c
												.getColumnIndex("q2_4")) != null) {
											if (c.getString(
													c.getColumnIndex("q2_4"))
													.toString()
													.equalsIgnoreCase("2")) {

												CommonStaticClass.qskipList
														.add("sec6");
												CommonStaticClass.qskipList
														.add("q6_1");
												CommonStaticClass.qskipList
														.add("q6_2");
												CommonStaticClass.qskipList
														.add("q6_3");
												CommonStaticClass.qskipList
														.add("q6_4");
												CommonStaticClass.qskipList
														.add("q6_5");
												CommonStaticClass.qskipList
														.add("q6_6");
												CommonStaticClass.qskipList
														.add("q6_7");
												CommonStaticClass.qskipList
														.add("q6_8");
												// nextToGo = "q2_5";
											}
										}
										// }

										/*
										 * if (CommonStaticClass.questionMap
										 * .get(CommonStaticClass.currentSLNo)
										 * .getQvar() .equalsIgnoreCase("q2_5"))
										 * {
										 */
										if (c.getString(c
												.getColumnIndex("q2_5")) != null) {
											if (c.getString(
													c.getColumnIndex("q2_5"))
													.toString()
													.equalsIgnoreCase("2")) {

												CommonStaticClass.qskipList
														.add("sec7");
												CommonStaticClass.qskipList
														.add("q7_1");
												CommonStaticClass.qskipList
														.add("q7_1b");
												CommonStaticClass.qskipList
														.add("q7_1bOther");
												CommonStaticClass.qskipList
														.add("q7_2");
												CommonStaticClass.qskipList
														.add("q7_2b");
												CommonStaticClass.qskipList
														.add("q7_2bOther");
												CommonStaticClass.qskipList
														.add("q7_3");
												CommonStaticClass.qskipList
														.add("q7_3b");
												CommonStaticClass.qskipList
														.add("q7_3bOther");
												CommonStaticClass.qskipList
														.add("q7_4");
												CommonStaticClass.qskipList
														.add("q7_4b");
												CommonStaticClass.qskipList
														.add("q7_4bOther");
												CommonStaticClass.qskipList
														.add("q7_5");
												CommonStaticClass.qskipList
														.add("q7_5b");
												CommonStaticClass.qskipList
														.add("q7_5bOther");
												CommonStaticClass.qskipList
														.add("q7_6");
												CommonStaticClass.qskipList
														.add("q7_6b");
												CommonStaticClass.qskipList
														.add("q7_6bOther");
												CommonStaticClass.qskipList
														.add("q7_7");
												CommonStaticClass.qskipList
														.add("q7_7b");
												CommonStaticClass.qskipList
														.add("q7_7bOther");
												CommonStaticClass.qskipList
														.add("q7_8");
												CommonStaticClass.qskipList
														.add("q7_8Other");
												CommonStaticClass.qskipList
														.add("q7_8b");
												CommonStaticClass.qskipList
														.add("q7_8bOther");
												// nextToGo = "END";
											}
										}
										// }

									} while (c.moveToNext());

								}
							}
						}
					}

					/*
					 * if (nextToGo != null && nextToGo != "") {
					 * CommonStaticClass.currentSLNo = CommonStaticClass
					 * .giveTheSLNo(qtoGo) - 1;
					 * CommonStaticClass.findOutNextSLNo(qName, nextToGo);
					 * CommonStaticClass.nextQuestion(ParentActivity.this);
					 * return;
					 * 
					 * } else {
					 * 
					 * CommonStaticClass.findOutNextSLNo(qName, nextToGo);
					 * CommonStaticClass.nextQuestion(ParentActivity.this); //
					 * return;
					 * 
					 * CommonStaticClass.currentSLNo = CommonStaticClass
					 * .giveTheSLNo(qtoGo) - 1;
					 * CommonStaticClass.findOutNextSLNo( qName,
					 * CommonStaticClass.questionMap.get(
					 * CommonStaticClass.currentSLNo) .getQnext1());
					 * CommonStaticClass.nextQuestion(ParentActivity.this);
					 * 
					 * return;
					 * 
					 * }
					 * 
					 * }
					 * 
					 * // End Angsuman if (CommonStaticClass.questionMap
					 * .get(CommonStaticClass.currentSLNo).getQnext1()
					 * .equalsIgnoreCase("SecP3")) { if
					 * (CommonStaticClass.qskipList.contains("SecP3")) { if
					 * (IfCompletedAllMembersFrmSingleChoice())
					 * showUserFinishDialogFrmSingleChoice(); else {
					 * CommonStaticClass.currentSLNo = 31; CommonStaticClass
					 * .nextQuestion(ParentActivity.this); } } else {
					 * CommonStaticClass.findOutNextSLNo( qName,
					 * CommonStaticClass.questionMap.get(
					 * CommonStaticClass.currentSLNo) .getQnext1());
					 * CommonStaticClass.nextQuestion(ParentActivity.this);
					 * 
					 * } } else if (nextToGo.equalsIgnoreCase("END")) { Message
					 * msg = new Message(); msg.what = UPDATEDONE;
					 * handler.sendMessage(msg); if
					 * (IfCompletedAllMembersFrmSingleChoice())
					 * showUserFinishDialogFrmSingleChoice(); else {
					 * CommonStaticClass.currentSLNo = 31;
					 * CommonStaticClass.nextQuestion(ParentActivity.this); } }
					 * else {
					 * 
					 * if (CommonStaticClass.questionMap
					 * .get(CommonStaticClass.currentSLNo).getQvar()
					 * .equalsIgnoreCase("q13") && code == 1) { if
					 * (dbHelper.GetSingleColumnData("q4")
					 * .equalsIgnoreCase("3")) {
					 * CommonStaticClass.qskipList.add("q13a");
					 * CommonStaticClass.qskipList.add("q14");
					 * nullifyWithInRange("q13", "q15"); nextToGo = "q16"; } }
					 * 
					 * if (CommonStaticClass.questionMap
					 * .get(CommonStaticClass.currentSLNo).getQvar()
					 * .equalsIgnoreCase("q8_3a")) {
					 * 
					 * if (dbHelper .IsDataExistsAndNotNull(String .format(
					 * "SELECT COUNT(*) FROM tblMainQuesSc WHERE (q8_3='0' OR q8_3='888') AND q8_3a ='0'  AND dataid = '%s'"
					 * , CommonStaticClass.dataId))) { //
					 * CommonStaticClass.qskipList.add("q14");
					 * nullifyWithInRange("q8_4_1", "q8_4_8"); nextToGo =
					 * "q9_Message"; } }
					 * 
					 * if (CommonStaticClass.questionMap
					 * .get(CommonStaticClass.currentSLNo).getQvar()
					 * .equalsIgnoreCase("q25")) { sql =
					 * "UPDATE tblMainQuesMc SET q26_b=NULL,q26_c=NULL,q26_i=NULL,q26_d=NULL,q26_p=NULL,q26_o=NULL, q26Other=NULL,q29years=NULL,q29months=NULL,q29days=NULL,q30years=NULL,q30months=NULL,q30days=NULL,q31_tp=NULL,q31_fp=NULL,q31_cf=NULL,q31_cw=NULL,q31_sp=NULL,q31_dp=NULL,q31_ep=NULL,q31_ctf=NULL,q31_cfk=NULL,q32_pa=NULL,q32_g=NULL,q32_dc=NULL,q32_m=NULL,q32_b=NULL "
					 * + CommonStaticClass.questionMap.get(
					 * CommonStaticClass.currentSLNo) .getQvar() + "='" + code +
					 * "' where dataid='" + CommonStaticClass.dataId + "'"; if
					 * (dbHelper.executeDMLQuery(sql)) { nextToGo = "END"; } }
					 * CommonStaticClass.findOutNextSLNo(qName, nextToGo);
					 * CommonStaticClass.nextQuestion(ParentActivity.this); } }
					 * } } catch (Exception ex) {
					 * CommonStaticClass.showMyAlert(con, "Exception", "Error");
					 * }
					 * 
					 * }
					 */

					if (CommonStaticClass.questionMap
							.get(CommonStaticClass.currentSLNo).getQvar()
							.equalsIgnoreCase("q2_5")) {

						if (dbHelper
								.IsDataExistsAndNotNull(String
										.format("SELECT COUNT(*) FROM tblMainQuesEPT WHERE (q2_1='2' AND q2_2='2' AND q2_3='2' AND q2_4='2' AND q2_5='2')  AND dataid = '%s'",
												CommonStaticClass.dataId))) {

							nextToGo = "END";
						}
					}

					if (CommonStaticClass.questionMap
							.get(CommonStaticClass.currentSLNo).getQvar()
							.equalsIgnoreCase("q3_1")) {

						if (dbHelper
								.IsDataExistsAndNotNull(String
										.format("SELECT COUNT(*) FROM tblMainQuesEPT WHERE (q2_2='2' AND q2_3='2' AND q2_4='2' AND q2_5='2')  AND dataid = '%s'",
												CommonStaticClass.dataId))) {

							nextToGo = "END";
						}
					}

					if (CommonStaticClass.questionMap
							.get(CommonStaticClass.currentSLNo).getQvar()
							.equalsIgnoreCase("q4_4")) {

						if (dbHelper
								.IsDataExistsAndNotNull(String
										.format("SELECT COUNT(*) FROM tblMainQuesEPT WHERE (q2_3='2' AND q2_4='2' AND q2_5='2')  AND dataid = '%s'",
												CommonStaticClass.dataId))) {

							nextToGo = "END";
						}
					}

					if (qtoGo != null && qtoGo != ""
							&& !nextToGo.equalsIgnoreCase("END")) {
						CommonStaticClass.currentSLNo = CommonStaticClass
								.giveTheSLNo(qtoGo) - 1;
						CommonStaticClass.findOutNextSLNo(
								qName,
								CommonStaticClass.questionMap.get(
										CommonStaticClass.currentSLNo)
										.getQnext1());
						CommonStaticClass.nextQuestion(ParentActivity.this);

						return;

					}
					// End Angsuman
					if (CommonStaticClass.questionMap
							.get(CommonStaticClass.currentSLNo).getQnext1()
							.equalsIgnoreCase("SecP3")) {
						if (CommonStaticClass.qskipList.contains("SecP3")) {
							if (IfCompletedAllMembersFrmSingleChoice())
								showUserFinishDialogFrmSingleChoice();
							else {
								CommonStaticClass.currentSLNo = 31;
								CommonStaticClass
										.nextQuestion(ParentActivity.this);
							}
						} else {
							CommonStaticClass.findOutNextSLNo(
									qName,
									CommonStaticClass.questionMap.get(
											CommonStaticClass.currentSLNo)
											.getQnext1());
							CommonStaticClass.nextQuestion(ParentActivity.this);

						}
					} else if (nextToGo.equalsIgnoreCase("END")) {
						Message msg = new Message();
						msg.what = UPDATEDONE;
						handler.sendMessage(msg);
						if (IfCompletedAllMembersFrmSingleChoice())
							showUserFinishDialogFrmSingleChoice();
						else {
							CommonStaticClass.currentSLNo = 31;
							CommonStaticClass.nextQuestion(ParentActivity.this);
						}
					} else {
						if (CommonStaticClass.questionMap
								.get(CommonStaticClass.currentSLNo).getQvar()
								.equalsIgnoreCase("q13")
								&& code == 1) {
							if (dbHelper.GetSingleColumnData("q4")
									.equalsIgnoreCase("3")) {
								CommonStaticClass.qskipList.add("q13a");
								CommonStaticClass.qskipList.add("q14");
								nullifyWithInRange("q13", "q15");
								nextToGo = "q16";
							}
						}
						if (CommonStaticClass.questionMap
								.get(CommonStaticClass.currentSLNo).getQvar()
								.equalsIgnoreCase("q8_3a")) {

							if (dbHelper
									.IsDataExistsAndNotNull(String
											.format("SELECT COUNT(*) FROM tblMainQuesSc WHERE (q8_3='0' OR q8_3='888') AND q8_3a ='0'  AND dataid = '%s'",
													CommonStaticClass.dataId))) {
								// CommonStaticClass.qskipList.add("q14");
								nullifyWithInRange("q8_4_1", "q8_4_8");
								nextToGo = "q9_Message";
							}
						}

						if (CommonStaticClass.questionMap
								.get(CommonStaticClass.currentSLNo).getQvar()
								.equalsIgnoreCase("q25")
								&& CommonStaticClass.dataId.substring(5, 7)
										.equalsIgnoreCase("00")) {
							sql = "UPDATE tblMainQuesMc SET q26_b=NULL,q26_c=NULL,q26_i=NULL,q26_d=NULL,q26_p=NULL,q26_o=NULL, q26Other=NULL,q29years=NULL,q29months=NULL,q29days=NULL,q30years=NULL,q30months=NULL,q30days=NULL,q31_tp=NULL,q31_fp=NULL,q31_cf=NULL,q31_cw=NULL,q31_sp=NULL,q31_dp=NULL,q31_ep=NULL,q31_ctf=NULL,q31_cfk=NULL,q32_pa=NULL,q32_g=NULL,q32_dc=NULL,q32_m=NULL,q32_b=NULL "
									+ CommonStaticClass.questionMap.get(
											CommonStaticClass.currentSLNo)
											.getQvar()
									+ "='"
									+ code
									+ "' where dataid='"
									+ CommonStaticClass.dataId + "'";
							if (dbHelper.executeDMLQuery(sql)) {
								nextToGo = "END";
							}
						}
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

				// String messageforlength = CommonStaticClass.IsValidLength(
				// qName, infoText.getText().toString());
				// if (messageforlength.length() == 0)
				updateTableDataFrmText();
				// else {
				// CommonStaticClass.showMyAlert(con, "Invalid Length",
				// messageforlength);
				// }

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
						.equalsIgnoreCase("q5_10Other")) {

					if (dbHelper
							.IsDataExistsAndNotNull(String
									.format("SELECT COUNT(*) FROM tblMainQuesEPT WHERE (q2_4='2' AND q2_5='2')  AND dataid = '%s'",
											CommonStaticClass.dataId))) {

						CommonStaticClass.findOutNextSLNo(
								CommonStaticClass.questionMap.get(
										CommonStaticClass.currentSLNo)
										.getQvar(), "END");
						CommonStaticClass.nextQuestion(ParentActivity.this);

						return;
					}
				}

				if (CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar()
						.equalsIgnoreCase("GISID")) {

					showUserFinishDialogFrmText();

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
		} else {
			CommonStaticClass.showMyAlert(con, "Message", "Please input data.");
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

		case DOB_DIALOG:
			return new DatePickerDialog(this, dateSetListener, dobYear,
					dobMonth, dobDay);

		case DATE_DIALOG_ID:

			DatePickerDialog datePickerDialog = this.customDatePicker();
			return datePickerDialog;

		case HBIS_DATE_DIALOG_ID:
			return new DatePickerDialog(this, HBISdateListener, dobYear,
					dobMonth, dobDay);
			/*
			 * DatePickerDialog HBISdatePickerDialog = this.HBISDatePicker();
			 * return HBISdatePickerDialog;
			 */

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

	private DatePickerDialog HBISDatePicker() {
		DatePickerDialog dpd = new DatePickerDialog(this, HBISdateListener,
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

		case HBIS_DATE_DIALOG_ID:
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
				CommonStaticClass.findOutNextSLNo(
						CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQvar(),
						CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQnext1());
				CommonStaticClass.nextQuestion(ParentActivity.this);
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

				weekText.setText("mßvn");
				yearText.setText("eQi");
				monthText.setText("gvm");
				dayText.setText("w`b");
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
						int y = mCursor1.getInt(mCursor1
								.getColumnIndex(CommonStaticClass.questionMap
										.get(CommonStaticClass.currentSLNo)
										.getQvar()
										+ "weeks"));
						if (y > 0) {
							weekBox.setText(y + "");
						} else {
							weekBox.setText("0");
						}
					}

					if (mCursor1.getColumnIndex(yearColumn) != -1) {
						yearHolder.setVisibility(View.VISIBLE);
						int y = mCursor1.getInt(mCursor1
								.getColumnIndex(CommonStaticClass.questionMap
										.get(CommonStaticClass.currentSLNo)
										.getQvar()
										+ "years"));
						if (y > 0) {
							yearBox.setText(y + "");
						} else {
							yearBox.setText("");
						}
					}
					if (mCursor1.getColumnIndex(monthColumn) != -1) {
						monthHolder.setVisibility(View.VISIBLE);
						int m = mCursor1.getInt(mCursor1
								.getColumnIndex(CommonStaticClass.questionMap
										.get(CommonStaticClass.currentSLNo)
										.getQvar()
										+ "months"));
						if (m > 0) {
							monthBox.setText(m + "");
						} else {
							monthBox.setText("");
						}
					}
					if (mCursor1.getColumnIndex(dayColumn) != -1) {
						int d = mCursor1.getInt(mCursor1
								.getColumnIndex(CommonStaticClass.questionMap
										.get(CommonStaticClass.currentSLNo)
										.getQvar()
										+ "days"));
						if (d > 0) {
							dayBox.setText(d + "");
						} else {
							dayBox.setText("");
						}
					}
					if (mCursor1.getColumnIndex(hourColumn) != -1) {
						int h = mCursor1.getInt(mCursor1
								.getColumnIndex(CommonStaticClass.questionMap
										.get(CommonStaticClass.currentSLNo)
										.getQvar()
										+ "hours"));
						if (h > 0) {
							hourBox.setText(h + "");
						} else {
							hourBox.setText("");
						}
					}
					if (mCursor1.getColumnIndex(minColumn) != -1) {
						int m = mCursor1.getInt(mCursor1
								.getColumnIndex(CommonStaticClass.questionMap
										.get(CommonStaticClass.currentSLNo)
										.getQvar()
										+ "mins"));
						if (m > 0) {
							minBox.setText(m + "");
						} else {
							minBox.setText("");
						}
					}

					/*
					 * if (!CommonStaticClass.questionMap
					 * .get(CommonStaticClass.currentSLNo).getQvar()
					 * .equalsIgnoreCase("c1_3")) {
					 * 
					 * monthHolder.setVisibility(View.VISIBLE);
					 * dayHolder.setVisibility(View.VISIBLE); }
					 * 
					 * if (CommonStaticClass.questionMap
					 * .get(CommonStaticClass.currentSLNo).getQvar()
					 * .equalsIgnoreCase("c1_3")) {
					 * monthHolder.setVisibility(View.VISIBLE);
					 * dayHolder.setVisibility(View.VISIBLE);
					 * 
					 * }
					 */
					// yearHolder.setVisibility(View.VISIBLE);
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
		/*
		 * if (yearHolder.getVisibility() == View.VISIBLE) { if
		 * (!(yearBox.getText().toString().length() > 0)) {
		 * CommonStaticClass.showMyAlert(con, "Error",
		 * "You must provide correct data to proceed"); return; } }
		 */

		if ((yearBox.getText().toString().length() <= 0)
				&& (monthBox.getText().toString().length() <= 0)) {
			CommonStaticClass.showMyAlert(con, "Error",
					"You must provide correct data to proceed");
			return;
		}

		if (CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo)
				.getQvar().equalsIgnoreCase("c1_3")) {

			if (monthBox.getText().toString().length() == 0
					&& dayBox.getText().toString().length() == 0) {
				CommonStaticClass.showMyAlert(con, "Message",
						"You must provide correct data to proceed");
				monthBox.requestFocus();
				return;
			}
			/*
			 * if (monthHolder.getVisibility() == View.VISIBLE) { if
			 * ((monthBox.getText().toString().length() > 0)) {
			 * 
			 * CommonStaticClass.showMyAlert(con, "Error",
			 * "You must provide correct data to proceed"); return; }
			 * 
			 * 
			 * if (Integer.parseInt(monthBox.getText().toString()) >= 1 &&
			 * Integer.parseInt(monthBox.getText().toString()) <= 36) {
			 * 
			 * } else { CommonStaticClass.showMyAlert(con, "Message",
			 * "Month should be between 1 to 36"); monthBox.setText("");
			 * monthBox.requestFocus(); return; } }
			 * 
			 * }
			 */

			if (dayHolder.getVisibility() == View.VISIBLE) {
				if ((dayBox.getText().toString().length() > 0)) {
					/*
					 * CommonStaticClass.showMyAlert(con, "Error",
					 * "You must provide correct data to proceed"); return; }
					 */

					if (Integer.parseInt(dayBox.getText().toString()) >= 1
							&& Integer.parseInt(dayBox.getText().toString()) <= 30) {

					} else {
						CommonStaticClass.showMyAlert(con, "Message",
								"Day should be between 1 to 30");
						dayBox.setText("");
						dayBox.requestFocus();
						return;
					}
				}
			}

		}

		if (CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo)
				.getQvar().equalsIgnoreCase("c607b")) {

			if (monthHolder.getVisibility() == View.VISIBLE
					|| dayHolder.getVisibility() == View.VISIBLE) {
				if (!(monthBox.getText().toString().length() > 0)
						&& !(dayBox.getText().toString().length() > 0)) {
					CommonStaticClass.showMyAlert(con, "Error",
							"You must provide correct data to proceed");
					return;
				}

				if (monthHolder.getVisibility() == View.VISIBLE) {
					if ((monthBox.getText().toString().length() > 0)) {
						/*
						 * CommonStaticClass.showMyAlert(con, "Error",
						 * "You must provide correct data to proceed"); return;
						 * }
						 */

						if ((Integer.parseInt(monthBox.getText().toString()) >= 1 && Integer
								.parseInt(monthBox.getText().toString()) <= 36)
								|| Integer.parseInt(monthBox.getText()
										.toString()) == 99) {

						} else {
							CommonStaticClass.showMyAlert(con, "Message",
									"Month should be between 1 to 36");
							monthBox.setText("");
							monthBox.requestFocus();
							return;
						}
					}

				}

				if (dayHolder.getVisibility() == View.VISIBLE) {
					if ((dayBox.getText().toString().length() > 0)) {
						/*
						 * CommonStaticClass.showMyAlert(con, "Error",
						 * "You must provide correct data to proceed"); return;
						 * }
						 */

						if ((Integer.parseInt(dayBox.getText().toString()) >= 1 && Integer
								.parseInt(dayBox.getText().toString()) <= 30)
								|| Integer
										.parseInt(dayBox.getText().toString()) == 99) {

						} else {
							CommonStaticClass.showMyAlert(con, "Message",
									"Day should be between 1 to 30");
							dayBox.setText("");
							dayBox.requestFocus();
							return;
						}
					}
				}

			}

		}

		if (monthHolder.getVisibility() == View.VISIBLE) {
			if ((monthBox.getText().toString().length() > 0)) {

				if ((Integer.parseInt(monthBox.getText().toString()) >= 0 && Integer
						.parseInt(monthBox.getText().toString()) <= 48)) {

				} else {
					CommonStaticClass.showMyAlert(con, "Message",
							"Month should be between 1 to 48");
					monthBox.setText("");
					monthBox.requestFocus();
					return;
				}
			}

		}

		if (yearHolder.getVisibility() == View.VISIBLE) {
			if ((yearBox.getText().toString().length() > 0)) {

				if ((Integer.parseInt(yearBox.getText().toString()) >= 5 && Integer
						.parseInt(yearBox.getText().toString()) <= 99)) {

				} else {
					CommonStaticClass.showMyAlert(con, "Message",
							"Year should be between 5 to 99");
					yearBox.setText("");
					yearBox.requestFocus();
					return;
				}
			}

		}

		// c606
		if (CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo)
				.getQvar().equalsIgnoreCase("c606")) {

			if (monthHolder.getVisibility() == View.VISIBLE
					|| dayHolder.getVisibility() == View.VISIBLE) {
				if (!(monthBox.getText().toString().length() > 0)
						&& !(dayBox.getText().toString().length() > 0)) {
					CommonStaticClass.showMyAlert(con, "Error",
							"You must provide correct data to proceed");
					return;
				}

				if (monthHolder.getVisibility() == View.VISIBLE) {
					if ((monthBox.getText().toString().length() > 0)) {

						if ((Integer.parseInt(monthBox.getText().toString()) >= 1 && Integer
								.parseInt(monthBox.getText().toString()) <= 36)
								|| Integer.parseInt(monthBox.getText()
										.toString()) == 99) {

						} else {
							CommonStaticClass.showMyAlert(con, "Message",
									"Month should be between 1 to 36");
							monthBox.setText("");
							monthBox.requestFocus();
							return;
						}
					}

				}

				if (dayHolder.getVisibility() == View.VISIBLE) {
					if ((dayBox.getText().toString().length() > 0)) {

						if ((Integer.parseInt(dayBox.getText().toString()) >= 0 && Integer
								.parseInt(dayBox.getText().toString()) <= 29)
								|| Integer
										.parseInt(dayBox.getText().toString()) == 99) {

						} else {
							CommonStaticClass.showMyAlert(con, "Message",
									"Day should be between 1 to 30");
							dayBox.setText("");
							dayBox.requestFocus();
							return;
						}
					}
				}

			}

		}

		if (weekHolder.getVisibility() == View.VISIBLE) {
			if (!(weekBox.getText().toString().length() > 0)) {
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
		String qName = CommonStaticClass.questionMap.get(
				CommonStaticClass.currentSLNo).getQvar();
		if (qName.equalsIgnoreCase("q201")) {
			if (!(yearBox.getText().toString().length() > 0)
					&& !(monthBox.getText().toString().length() > 0)) {
				CommonStaticClass.showMyAlert(con, "Error",
						"You must give data to proceed");
				return;
			}
		}
		if (qName.equalsIgnoreCase("q204")) {
			if (!(dayBox.getText().toString().length() > 0)
					&& !(yearBox.getText().toString().length() > 0)
					&& !(monthBox.getText().toString().length() > 0)) {
				CommonStaticClass.showMyAlert(con, "Error",
						"You must give data to proceed");
				return;
			}
		}
		String sql = "";

		sql = "UPDATE "
				+ CommonStaticClass.questionMap.get(
						CommonStaticClass.currentSLNo).getTablename() + " SET ";
		int i = 0;
		// +CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo).getQvar()+"='"+code+"' where dataid='"+CommonStaticClass.dataId+"'";
		if (yearHolder.getVisibility() == View.VISIBLE) {
			year = yearBox.getText().toString();
			sql += CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQvar()
					+ "years = '" + year + "'";
			i++;
		}
		if (monthHolder.getVisibility() == View.VISIBLE) {
			month = monthBox.getText().toString();
			if (month.equalsIgnoreCase("88") && qName.equalsIgnoreCase("q221")) {
				CommonStaticClass.qskipList.add("q1003");
			}
			sql += (i > 0 ? "," : "")
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar()
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

		if (qName.equalsIgnoreCase("q30")) {
			mCursor = dbHelper.getQueryCursor(sql);
			if (mCursor.moveToFirst()) {
				if (Integer.parseInt(year) > Integer.parseInt(mCursor
						.getString(mCursor.getColumnIndex("q29years")))) {
					CommonStaticClass.showMyAlert(con, "Error",
							"Wrong year, must be less than qustion 29");
				} else if (Integer.parseInt(year) == Integer.parseInt(mCursor
						.getString(mCursor.getColumnIndex("q29years")))
						&& Integer.parseInt(month) > Integer
								.parseInt(mCursor.getString(mCursor
										.getColumnIndex("q29months")))) {
					CommonStaticClass.showMyAlert(con, "Error",
							"Wrong year, must be less than qustion 29");
				} else if (Integer.parseInt(year) == Integer.parseInt(mCursor
						.getString(mCursor.getColumnIndex("q29years")))
						&& Integer.parseInt(month) == Integer
								.parseInt(mCursor.getString(mCursor
										.getColumnIndex("q29months")))
						&& Integer.parseInt(day) > Integer.parseInt(mCursor
								.getString(mCursor.getColumnIndex("q29days")))) {
					CommonStaticClass.showMyAlert(con, "Error",
							"Wrong year, must be less than qustion 29");
				}
			}
		}

		if (dbHelper.executeDMLQuery(sql)) {

			monthText.setText("");
			dayText.setText("");
			CommonStaticClass.findOutNextSLNo(
					CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar(),
					CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQnext1());
			CommonStaticClass.nextQuestion(ParentActivity.this);
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
		if (view instanceof RadioGroup) {
			((RadioGroup) view).clearCheck();
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
						.setText("c«wµqvRvZKib");
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

		// q37
		if (qName.equalsIgnoreCase("q37a")) {
			CommonStaticClass.qskipList.remove("q37c1");
			CommonStaticClass.qskipList.remove("q37c1_other");
			CommonStaticClass.qskipList.add("q37c1_other_num");
			CommonStaticClass.qskipList.remove("q37c2");
			CommonStaticClass.qskipList.remove("q37c2_other");
			CommonStaticClass.qskipList.add("q37c2_other_num");
			CommonStaticClass.qskipList.remove("q37c3");
			CommonStaticClass.qskipList.remove("q37c3_other");
			CommonStaticClass.qskipList.add("q37c3_other_num");
			CommonStaticClass.qskipList.remove("q37c4");
			CommonStaticClass.qskipList.remove("q37c4_other");
			CommonStaticClass.qskipList.add("q37c4_other_num");
			CommonStaticClass.qskipList.remove("q37c5");
			CommonStaticClass.qskipList.remove("q37c5_other");
			CommonStaticClass.qskipList.add("q37c5_other_num");
			CommonStaticClass.qskipList.remove("q37c6");
			CommonStaticClass.qskipList.remove("q37c6_other");
			CommonStaticClass.qskipList.add("q37c6_other_num");
			CommonStaticClass.qskipList.remove("q37c7");
			CommonStaticClass.qskipList.remove("q37c7_other");
			CommonStaticClass.qskipList.add("q37c7_other_num");

			if (!getSkip("q37a_1", "tblMainQues").equalsIgnoreCase("1")) {
				CommonStaticClass.qskipList.add("q37c1");
				CommonStaticClass.qskipList.add("q37c1_other");
				CommonStaticClass.qskipList.add("q37c1_other_num");

			}

			if (!getSkip("q37a_2", "tblMainQues").equalsIgnoreCase("1")) {
				CommonStaticClass.qskipList.add("q37c2");
				CommonStaticClass.qskipList.add("q37c2_other");
				CommonStaticClass.qskipList.add("q37c2_other_num");

			}
			if (!getSkip("q37a_3", "tblMainQues").equalsIgnoreCase("1")) {
				CommonStaticClass.qskipList.add("q37c3");
				CommonStaticClass.qskipList.add("q37c3_other");
				CommonStaticClass.qskipList.add("q37c3_other_num");

			}

			if (!getSkip("q37a_4", "tblMainQues").equalsIgnoreCase("1")) {
				CommonStaticClass.qskipList.add("q37c4");
				CommonStaticClass.qskipList.add("q37c4_other");
				CommonStaticClass.qskipList.add("q37c4_other_num");

			}

			if (!getSkip("q37a_5", "tblMainQues").equalsIgnoreCase("1")) {
				CommonStaticClass.qskipList.add("q37c5");
				CommonStaticClass.qskipList.add("q37c5_other");
				CommonStaticClass.qskipList.add("q37c5_other_num");

			}

			if (!getSkip("q37a_6", "tblMainQues").equalsIgnoreCase("1")) {
				CommonStaticClass.qskipList.add("q37c6");
				CommonStaticClass.qskipList.add("q37c6_other");
				CommonStaticClass.qskipList.add("q37c6_other_num");

			}

			if (!getSkip("q37a_7", "tblMainQues").equalsIgnoreCase("1")) {
				CommonStaticClass.qskipList.add("q37c7");
				CommonStaticClass.qskipList.add("q37c7_other");
				CommonStaticClass.qskipList.add("q37c7_other_num");

			}
		}

		if (qName.equalsIgnoreCase("q42a")) {

			CommonStaticClass.qskipList.remove("q42c1");
			CommonStaticClass.qskipList.remove("q42c1_other");
			CommonStaticClass.qskipList.remove("q42c1_other_num");
			CommonStaticClass.qskipList.remove("q42c2");
			CommonStaticClass.qskipList.remove("q42c2_other");
			CommonStaticClass.qskipList.remove("q42c2_other_num");
			CommonStaticClass.qskipList.remove("q42c3");
			CommonStaticClass.qskipList.remove("q42c3_other");
			CommonStaticClass.qskipList.remove("q42c3_other_num");
			CommonStaticClass.qskipList.remove("q42c4");
			CommonStaticClass.qskipList.remove("q42c4_other");
			CommonStaticClass.qskipList.remove("q42c4_other_num");
			CommonStaticClass.qskipList.remove("q42c5");
			CommonStaticClass.qskipList.remove("q42c5_other");
			CommonStaticClass.qskipList.remove("q42c5_other_num");
			CommonStaticClass.qskipList.remove("q42c6");
			CommonStaticClass.qskipList.remove("q42c6_other");
			CommonStaticClass.qskipList.remove("q42c6_other_num");
			CommonStaticClass.qskipList.remove("q42c7");
			CommonStaticClass.qskipList.remove("q42c7_other");
			CommonStaticClass.qskipList.remove("q42c7_other_num");
			CommonStaticClass.qskipList.remove("q42c8");
			CommonStaticClass.qskipList.remove("q42c8_other");
			CommonStaticClass.qskipList.remove("q42c8_other_num");

			if (!getSkip("q42a_1", "tblMainQues").equalsIgnoreCase("1")) {
				CommonStaticClass.qskipList.add("q42c1");
				CommonStaticClass.qskipList.add("q42c1_other");
				CommonStaticClass.qskipList.add("q42c1_other_num");

			}

			if (!getSkip("q42a_2", "tblMainQues").equalsIgnoreCase("1")) {
				CommonStaticClass.qskipList.add("q42c2");
				CommonStaticClass.qskipList.add("q42c2_other");
				CommonStaticClass.qskipList.add("q42c2_other_num");

			}
			if (!getSkip("q42a_3", "tblMainQues").equalsIgnoreCase("1")) {
				CommonStaticClass.qskipList.add("q42c3");
				CommonStaticClass.qskipList.add("q42c3_other");
				CommonStaticClass.qskipList.add("q42c3_other_num");

			}

			if (!getSkip("q42a_4", "tblMainQues").equalsIgnoreCase("1")) {
				CommonStaticClass.qskipList.add("q42c4");
				CommonStaticClass.qskipList.add("q42c4_other");
				CommonStaticClass.qskipList.add("q42c4_other_num");

			}

			if (!getSkip("q42a_5", "tblMainQues").equalsIgnoreCase("1")) {
				CommonStaticClass.qskipList.add("q42c5");
				CommonStaticClass.qskipList.add("q42c5_other");
				CommonStaticClass.qskipList.add("q42c5_other_num");

			}

			if (!getSkip("q42a_6", "tblMainQues").equalsIgnoreCase("1")) {
				CommonStaticClass.qskipList.add("q42c6");
				CommonStaticClass.qskipList.add("q42c6_other");
				CommonStaticClass.qskipList.add("q42c6_other_num");

			}

			if (!getSkip("q42a_7", "tblMainQues").equalsIgnoreCase("1")) {
				CommonStaticClass.qskipList.add("q42c7");
				CommonStaticClass.qskipList.add("q42c7_other");
				CommonStaticClass.qskipList.add("q42c7_other_num");

			}
			if (!getSkip("q42a_8", "tblMainQues").equalsIgnoreCase("1")) {
				CommonStaticClass.qskipList.add("q42c8");
				CommonStaticClass.qskipList.add("q42c8_other");
				CommonStaticClass.qskipList.add("q42c8_other_num");

			}
		}

		if (qName.equalsIgnoreCase("p118")) {

			CommonStaticClass.qskipList.remove("q118R1C2");
			CommonStaticClass.qskipList.remove("q118R1C3");
			CommonStaticClass.qskipList.remove("q118R2C2");
			CommonStaticClass.qskipList.remove("q118R2C3");
			CommonStaticClass.qskipList.remove("q118R3C2");
			CommonStaticClass.qskipList.remove("q118R3C3");
			CommonStaticClass.qskipList.remove("q118R4C2");
			CommonStaticClass.qskipList.remove("q118R4C3");
			CommonStaticClass.qskipList.remove("q118R5C2");
			CommonStaticClass.qskipList.remove("q118R5C3");
			CommonStaticClass.qskipList.remove("q118R6C2");
			CommonStaticClass.qskipList.remove("q118R6C3");
			CommonStaticClass.qskipList.remove("q118R7C2");
			CommonStaticClass.qskipList.remove("q118R7C3");

			if (!getSkip("p118_1", "tblMainQuesMc").equalsIgnoreCase("1")) {
				CommonStaticClass.qskipList.add("q118R1C2");
				CommonStaticClass.qskipList.add("q118R1C3");
			}

			if (!getSkip("p118_2", "tblMainQuesMc").equalsIgnoreCase("1")) {
				CommonStaticClass.qskipList.add("q118R2C2");
				CommonStaticClass.qskipList.add("q118R2C3");
			}

			if (!getSkip("p118_3", "tblMainQuesMc").equalsIgnoreCase("1")) {
				CommonStaticClass.qskipList.add("q118R3C2");
				CommonStaticClass.qskipList.add("q118R3C3");
			}

			if (!getSkip("p118_4", "tblMainQuesMc").equalsIgnoreCase("1")) {
				CommonStaticClass.qskipList.add("q118R4C2");
				CommonStaticClass.qskipList.add("q118R4C3");
			}

			if (!getSkip("p118_5", "tblMainQuesMc").equalsIgnoreCase("1")) {
				CommonStaticClass.qskipList.add("q118R5C2");
				CommonStaticClass.qskipList.add("q118R5C3");
			}

			if (!getSkip("p118_6", "tblMainQuesMc").equalsIgnoreCase("1")) {
				CommonStaticClass.qskipList.add("q118R6C2");
				CommonStaticClass.qskipList.add("q118R6C3");
			}

			if (!getSkip("p118_7", "tblMainQuesMc").equalsIgnoreCase("1")) {
				CommonStaticClass.qskipList.add("q118R7C2");
				CommonStaticClass.qskipList.add("q118R7C3");
			}

		}

		if (qName.equalsIgnoreCase("p114")) {
			CommonStaticClass.qskipList.remove("p114_other");
			CommonStaticClass.qskipList.remove("q114R0C2");
			CommonStaticClass.qskipList.remove("q114R0C3");
			CommonStaticClass.qskipList.remove("q114R1C2");
			CommonStaticClass.qskipList.remove("q114R1C3");
			CommonStaticClass.qskipList.remove("q114R2C2");
			CommonStaticClass.qskipList.remove("q114R2C3");
			CommonStaticClass.qskipList.remove("q114R3C2");
			CommonStaticClass.qskipList.remove("q114R3C3");
			CommonStaticClass.qskipList.remove("q114R4C2");
			CommonStaticClass.qskipList.remove("q114R4C3");
			CommonStaticClass.qskipList.remove("q114R5C2");
			CommonStaticClass.qskipList.remove("q114R5C3");
			CommonStaticClass.qskipList.remove("q114R6C2");
			CommonStaticClass.qskipList.remove("q114R6C3");
			CommonStaticClass.qskipList.remove("q114R7C2");
			CommonStaticClass.qskipList.remove("q114R7C3");
			CommonStaticClass.qskipList.remove("q114R8C2");
			CommonStaticClass.qskipList.remove("q114R8C3");
			CommonStaticClass.qskipList.remove("q114R9C2");
			CommonStaticClass.qskipList.remove("q114R9C3");
			CommonStaticClass.qskipList.remove("q114R10C2");
			CommonStaticClass.qskipList.remove("q114R10C3");
			CommonStaticClass.qskipList.remove("q114R11C2");
			CommonStaticClass.qskipList.remove("q114R11C3");
			CommonStaticClass.qskipList.remove("q114R12C2");
			CommonStaticClass.qskipList.remove("q114R12C3");
			CommonStaticClass.qskipList.remove("q114R13C2");
			CommonStaticClass.qskipList.remove("q114R13C3");
			CommonStaticClass.qskipList.remove("q114R14C2");
			CommonStaticClass.qskipList.remove("q114R14C3");

			CommonStaticClass.qskipList.remove("p114_other");
			CommonStaticClass.qskipList.remove("q114R0C2");
			CommonStaticClass.qskipList.remove("q114R0C3");

			if (!getSkip("p114_15", "tblMainQuesMc").equalsIgnoreCase("1")) {
				// if (Integer.parseInt(getSkip("q114R0C2", "tblMainQuesMc")) <=
				// 0) {
				CommonStaticClass.qskipList.add("q114R0C2");
				CommonStaticClass.qskipList.add("q114R0C3");
				CommonStaticClass.qskipList.add("p114_other");
				// }

			}

			if (!getSkip("p114_1", "tblMainQuesMc").equalsIgnoreCase("1")) {
				// if (Integer.parseInt(getSkip("q114R1C2", "tblMainQuesMc")) <=
				// 0) {
				CommonStaticClass.qskipList.add("q114R1C2");
				CommonStaticClass.qskipList.add("q114R1C3");
				// }
			}
			if (!getSkip("p114_2", "tblMainQuesMc").equalsIgnoreCase("1")) {
				// if (Integer.parseInt(getSkip("q114R2C2", "tblMainQuesMc")) <=
				// 0) {
				CommonStaticClass.qskipList.add("q114R2C2");
				CommonStaticClass.qskipList.add("q114R2C3");
				// }
			}
			if (!getSkip("p114_3", "tblMainQuesMc").equalsIgnoreCase("1")) {
				// if (Integer.parseInt(getSkip("q114R3C2", "tblMainQuesMc")) <=
				// 0) {
				CommonStaticClass.qskipList.add("q114R3C2");
				CommonStaticClass.qskipList.add("q114R3C3");
				// }
			}
			if (!getSkip("p114_4", "tblMainQuesMc").equalsIgnoreCase("1")) {
				// if (Integer.parseInt(getSkip("q114R4C2", "tblMainQuesMc")) <=
				// 0) {
				CommonStaticClass.qskipList.add("q114R4C2");
				CommonStaticClass.qskipList.add("q114R4C3");
				// }
			}

			if (!getSkip("p114_5", "tblMainQuesMc").equalsIgnoreCase("1")) {
				// if (Integer.parseInt(getSkip("q114R5C2", "tblMainQuesMc")) <=
				// 0) {
				CommonStaticClass.qskipList.add("q114R5C2");
				CommonStaticClass.qskipList.add("q114R5C3");
				// }
			}

			if (!getSkip("p114_6", "tblMainQuesMc").equalsIgnoreCase("1")) {
				// if (Integer.parseInt(getSkip("q114R6C2", "tblMainQuesMc")) <=
				// 0) {
				CommonStaticClass.qskipList.add("q114R6C2");
				CommonStaticClass.qskipList.add("q114R6C3");
				// }
			}

			if (!getSkip("p114_7", "tblMainQuesMc").equalsIgnoreCase("1")) {
				// if (Integer.parseInt(getSkip("q114R7C2", "tblMainQuesMc")) <=
				// 0) {
				CommonStaticClass.qskipList.add("q114R7C2");
				CommonStaticClass.qskipList.add("q114R7C3");
				// }
			}

			if (!getSkip("p114_8", "tblMainQuesMc").equalsIgnoreCase("1")) {
				// if (Integer.parseInt(getSkip("q114R8C2", "tblMainQuesMc")) <=
				// 0) {
				CommonStaticClass.qskipList.add("q114R8C2");
				CommonStaticClass.qskipList.add("q114R8C3");
				// }
			}

			if (!getSkip("p114_9", "tblMainQuesMc").equalsIgnoreCase("1")) {
				// if (Integer.parseInt(getSkip("q114R9C2", "tblMainQuesMc")) <=
				// 0) {
				CommonStaticClass.qskipList.add("q114R9C2");
				CommonStaticClass.qskipList.add("q114R9C3");
				// }
			}

			if (!getSkip("p114_10", "tblMainQuesMc").equalsIgnoreCase("1")) {
				// if (Integer.parseInt(getSkip("q114R10C2", "tblMainQuesMc"))
				// <= 0) {
				CommonStaticClass.qskipList.add("q114R10C2");
				CommonStaticClass.qskipList.add("q114R10C3");
				// }
			}

			if (!getSkip("p114_11", "tblMainQuesMc").equalsIgnoreCase("1")) {
				// if (Integer.parseInt(getSkip("q114R11C2", "tblMainQuesMc"))
				// <= 0) {
				CommonStaticClass.qskipList.add("q114R11C2");
				CommonStaticClass.qskipList.add("q114R11C3");
				// }
			}

			if (!getSkip("p114_12", "tblMainQuesMc").equalsIgnoreCase("1")) {
				// if (Integer.parseInt(getSkip("q114R12C2", "tblMainQuesMc"))
				// <= 0) {
				CommonStaticClass.qskipList.add("q114R12C2");
				CommonStaticClass.qskipList.add("q114R12C3");
				// }
			}

			if (!getSkip("p114_13", "tblMainQuesMc").equalsIgnoreCase("1")) {
				// if (Integer.parseInt(getSkip("q114R13C2", "tblMainQuesMc"))
				// <= 0) {
				CommonStaticClass.qskipList.add("q114R13C2");
				CommonStaticClass.qskipList.add("q114R13C3");
				// }
			}

			if (!getSkip("p114_14", "tblMainQuesMc").equalsIgnoreCase("1")) {
				// if (Integer.parseInt(getSkip("q114R14C2", "tblMainQuesMc"))
				// <= 0) {
				CommonStaticClass.qskipList.add("q114R14C2");
				CommonStaticClass.qskipList.add("q114R14C3");
				// }

			}
		}
		if (qName.equalsIgnoreCase("q89a")) {
			CommonStaticClass.qskipList.remove("q89a_other");
			CommonStaticClass.qskipList.remove("q89o");
			CommonStaticClass.qskipList.remove("q89o_other");
			CommonStaticClass.qskipList.remove("q89oCount");

			CommonStaticClass.qskipList.remove("q89R1");
			CommonStaticClass.qskipList.remove("q89R1_other");
			CommonStaticClass.qskipList.remove("q89R1Count");
			CommonStaticClass.qskipList.remove("q89R2");
			CommonStaticClass.qskipList.remove("q89R2_other");
			CommonStaticClass.qskipList.remove("q89R2Count");
			CommonStaticClass.qskipList.remove("q89R3");
			CommonStaticClass.qskipList.remove("q89R3_other");
			CommonStaticClass.qskipList.remove("q89R3Count");
			CommonStaticClass.qskipList.remove("q89R4");
			CommonStaticClass.qskipList.remove("q89R4_other");
			CommonStaticClass.qskipList.remove("q89R4Count");
			CommonStaticClass.qskipList.remove("q89R5");
			CommonStaticClass.qskipList.remove("q89R5_other");
			CommonStaticClass.qskipList.remove("q89R5Count");
			CommonStaticClass.qskipList.remove("q89R6");
			CommonStaticClass.qskipList.remove("q89R6_other");
			CommonStaticClass.qskipList.remove("q89R6Count");
			CommonStaticClass.qskipList.remove("q89R7");
			CommonStaticClass.qskipList.remove("q89R7_other");
			CommonStaticClass.qskipList.remove("q89R7a");
			CommonStaticClass.qskipList.remove("q89R7aCount");
			CommonStaticClass.qskipList.remove("q89R8");

			CommonStaticClass.qskipList.remove("q89R8_other");
			CommonStaticClass.qskipList.remove("q89R8Count");

			CommonStaticClass.qskipList.remove("q89R9");
			CommonStaticClass.qskipList.remove("q89R9_other");
			CommonStaticClass.qskipList.remove("q89R9Count");

			if (!getSkip("q89a_1", "tblMainQuesMc").equalsIgnoreCase("1")) {
				CommonStaticClass.qskipList.add("q89R1");
				CommonStaticClass.qskipList.add("q89R1Count");
				CommonStaticClass.qskipList.add("q89R1_other");

			}
			if (!getSkip("q89a_2", "tblMainQuesMc").equalsIgnoreCase("1")) {
				CommonStaticClass.qskipList.add("q89R2");
				CommonStaticClass.qskipList.add("q89R2Count");
				CommonStaticClass.qskipList.add("q89R2_other");

			}
			if (!getSkip("q89a_3", "tblMainQuesMc").equalsIgnoreCase("1")) {
				CommonStaticClass.qskipList.add("q89R3");
				CommonStaticClass.qskipList.add("q89R3Count");
				CommonStaticClass.qskipList.add("q89R3_other");

			}
			if (!getSkip("q89a_4", "tblMainQuesMc").equalsIgnoreCase("1")) {
				CommonStaticClass.qskipList.add("q89R4");
				CommonStaticClass.qskipList.add("q89R4Count");
				CommonStaticClass.qskipList.add("q89R4_other");

			}
			if (!getSkip("q89a_5", "tblMainQuesMc").equalsIgnoreCase("1")) {
				CommonStaticClass.qskipList.add("q89R5");
				CommonStaticClass.qskipList.add("q89R5Count");
				CommonStaticClass.qskipList.add("q89R5_other");

			}
			if (!getSkip("q89a_6", "tblMainQuesMc").equalsIgnoreCase("1")) {
				CommonStaticClass.qskipList.add("q89R6");
				CommonStaticClass.qskipList.add("q89R6Count");
				CommonStaticClass.qskipList.add("q89R6_other");

			}
			if (!getSkip("q89a_7", "tblMainQuesMc").equalsIgnoreCase("1")) {
				CommonStaticClass.qskipList.add("q89R7");
				CommonStaticClass.qskipList.add("q89R7Count");
				CommonStaticClass.qskipList.add("q89R7_other");
				CommonStaticClass.qskipList.add("q89R7a");
				CommonStaticClass.qskipList.add("q89R7aCount");

			}

			if (!getSkip("q89a_8", "tblMainQuesMc").equalsIgnoreCase("1")) {
				/*
				 * CommonStaticClass.qskipList.add("q89R8");
				 * CommonStaticClass.qskipList.add("q89R8Count");
				 * CommonStaticClass.qskipList.add("q89R8_other");
				 */

				CommonStaticClass.qskipList.add("q89R8");
				CommonStaticClass.qskipList.add("q89R8_other");
				CommonStaticClass.qskipList.add("q89R8Count");

			}

			if (!getSkip("q89a_9", "tblMainQuesMc").equalsIgnoreCase("1")) {

				CommonStaticClass.qskipList.add("q89R9");
				CommonStaticClass.qskipList.add("q89R9Count");
				CommonStaticClass.qskipList.add("q89R9_other");

			}

		}

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

		if (qName.equalsIgnoreCase("q6_2")) {
			String s = "SELECT * from tblMainQuesMc WHERE (q6_2_15 ='1' OR q6_2_16='1') AND dataid='"
					+ CommonStaticClass.dataId + "'";
			if (dbHelper.IsDataExists(s)) {
				CommonStaticClass.findOutNextSLNo(qName, "q6_3");
				CommonStaticClass.nextQuestion(ParentActivity.this);
				return true;
			}
		}

		if (qName.equalsIgnoreCase("q5_2")) {
			String s = "SELECT * from tblMainQuesMc WHERE (q5_2_1 ='1' OR q5_2_4='1') AND dataid='"
					+ CommonStaticClass.dataId + "'";
			if (dbHelper.IsDataExists(s)) {
				CommonStaticClass.findOutNextSLNo(qName, "q5_5");
				CommonStaticClass.nextQuestion(ParentActivity.this);
				return true;
			}
		}

		if (qName.equalsIgnoreCase("q54")) {

			String s = "SELECT * from tblMainQuesMc WHERE (q54_6 ='1') AND dataid='"
					+ CommonStaticClass.dataId + "'";
			if (dbHelper.IsDataExists(s)) {
				CommonStaticClass.findOutNextSLNo(qName, "q55");
				CommonStaticClass.nextQuestion(ParentActivity.this);
				return true;
			}

			s = "SELECT * from tblMainQuesMc WHERE (q54_1 ='1' OR q54_2 ='1' OR q54_3 ='1' OR q54_4 ='1' OR q54_5 ='1' OR q54_7 ='1') AND dataid='"
					+ CommonStaticClass.dataId + "'";
			if (dbHelper.IsDataExists(s)) {
				CommonStaticClass.findOutNextSLNo(qName, "q56");
				CommonStaticClass.nextQuestion(ParentActivity.this);
				return true;
			}
		}

		/*
		 * if (qName.equalsIgnoreCase("q87_R1_C1")) { String s =
		 * "SELECT * from tblMainQuesMc WHERE (q87_R1_C1_11 ='1') AND dataid='"
		 * + CommonStaticClass.dataId + "'"; if (dbHelper.IsDataExists(s)) {
		 * CommonStaticClass.findOutNextSLNo(qName, "q87_R2_C1");
		 * CommonStaticClass.nextQuestion(ParentActivity.this); return true; } }
		 * 
		 * if (qName.equalsIgnoreCase("q87_R2_C1")) { String s =
		 * "SELECT * from tblMainQuesMc WHERE (q87_R2_C1_11 ='1') AND dataid='"
		 * + CommonStaticClass.dataId + "'"; if (dbHelper.IsDataExists(s)) {
		 * CommonStaticClass.findOutNextSLNo(qName, "q87_R3_C1");
		 * CommonStaticClass.nextQuestion(ParentActivity.this); return true; } }
		 * 
		 * if (qName.equalsIgnoreCase("q87_R3_C1")) { String s =
		 * "SELECT * from tblMainQuesMc WHERE (q87_R3_C1_11 ='1') AND dataid='"
		 * + CommonStaticClass.dataId + "'"; if (dbHelper.IsDataExists(s)) {
		 * CommonStaticClass.findOutNextSLNo(qName, "q87_R4_C1");
		 * CommonStaticClass.nextQuestion(ParentActivity.this); return true; } }
		 * 
		 * if (qName.equalsIgnoreCase("q87_R4_C1")) { String s =
		 * "SELECT * from tblMainQuesMc WHERE (q87_R4_C1_11 ='1') AND dataid='"
		 * + CommonStaticClass.dataId + "'"; if (dbHelper.IsDataExists(s)) {
		 * CommonStaticClass.findOutNextSLNo(qName, "q87_R5_C1");
		 * CommonStaticClass.nextQuestion(ParentActivity.this); return true; } }
		 * 
		 * if (qName.equalsIgnoreCase("q87_R5_C1")) { String s =
		 * "SELECT * from tblMainQuesMc WHERE (q87_R5_C1_11 ='1') AND dataid='"
		 * + CommonStaticClass.dataId + "'"; if (dbHelper.IsDataExists(s)) {
		 * CommonStaticClass.findOutNextSLNo(qName, "q88");
		 * CommonStaticClass.nextQuestion(ParentActivity.this); return true; } }
		 */

		// q89
		/*
		 * if (qName.equalsIgnoreCase("q89_R1_C1")) { String s =
		 * "SELECT * from tblMainQuesMc WHERE (q89_R1_C1_11 ='1') AND dataid='"
		 * + CommonStaticClass.dataId + "'"; if (dbHelper.IsDataExists(s)) {
		 * CommonStaticClass.findOutNextSLNo(qName, "q89_R2_C1");
		 * CommonStaticClass.nextQuestion(ParentActivity.this); return true; } }
		 * 
		 * if (qName.equalsIgnoreCase("q89_R2_C1")) { String s =
		 * "SELECT * from tblMainQuesMc WHERE (q89_R2_C1_11 ='1') AND dataid='"
		 * + CommonStaticClass.dataId + "'"; if (dbHelper.IsDataExists(s)) {
		 * CommonStaticClass.findOutNextSLNo(qName, "q89_R3_C1");
		 * CommonStaticClass.nextQuestion(ParentActivity.this); return true; } }
		 * 
		 * if (qName.equalsIgnoreCase("q89_R3_C1")) { String s =
		 * "SELECT * from tblMainQuesMc WHERE (q89_R3_C1_11 ='1') AND dataid='"
		 * + CommonStaticClass.dataId + "'"; if (dbHelper.IsDataExists(s)) {
		 * CommonStaticClass.findOutNextSLNo(qName, "q89_R4_C1");
		 * CommonStaticClass.nextQuestion(ParentActivity.this); return true; } }
		 * 
		 * if (qName.equalsIgnoreCase("q89_R4_C1")) { String s =
		 * "SELECT * from tblMainQuesMc WHERE (q89_R4_C1_11 ='1') AND dataid='"
		 * + CommonStaticClass.dataId + "'"; if (dbHelper.IsDataExists(s)) {
		 * CommonStaticClass.findOutNextSLNo(qName, "q89_R5_C1");
		 * CommonStaticClass.nextQuestion(ParentActivity.this); return true; } }
		 * 
		 * if (qName.equalsIgnoreCase("q89_R5_C1")) { String s =
		 * "SELECT * from tblMainQuesMc WHERE (q89_R5_C1_11 ='1') AND dataid='"
		 * + CommonStaticClass.dataId + "'"; if (dbHelper.IsDataExists(s)) {
		 * CommonStaticClass.findOutNextSLNo(qName, "q89_R6_C1");
		 * CommonStaticClass.nextQuestion(ParentActivity.this); return true; } }
		 * 
		 * if (qName.equalsIgnoreCase("q89_R6_C1")) { String s =
		 * "SELECT * from tblMainQuesMc WHERE (q89_R6_C1_11 ='1') AND dataid='"
		 * + CommonStaticClass.dataId + "'"; if (dbHelper.IsDataExists(s)) {
		 * CommonStaticClass.findOutNextSLNo(qName, "q90");
		 * CommonStaticClass.nextQuestion(ParentActivity.this); return true; } }
		 */
		//
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
		if (formname.equalsIgnoreCase("frmpatientdetail")) {
			index = 28;
		}

		if (formname.equalsIgnoreCase("frmweight")) {
			index = 29;
		}
		if (formname.equalsIgnoreCase("frmdiarrhea")) {
			index = 30;
		}
		if (formname.equalsIgnoreCase("frmvomiting")) {
			index = 31;
		}
		if (formname.equalsIgnoreCase("frmors")) {
			index = 32;
		}

		if (formname.equalsIgnoreCase("frmchildren")) {
			index = 33;
		}

		if (formname.equalsIgnoreCase("frmmultiplechoicetext")) {
			index = 34;
		}
		if (formname.equalsIgnoreCase("frmaddress")) {
			index = 35;
		}

		if (formname.equalsIgnoreCase("frmtravelinfo")) {
			index = 36;
		}
		if (formname.equalsIgnoreCase("frmpersonrelation")) {
			index = 37;
		}

		if (formname.equalsIgnoreCase("frmsymptoms")) {
			index = 38;
		}

		if (formname.equalsIgnoreCase("frmsymptomsone")) {
			index = 39;
		}

		if (formname.equalsIgnoreCase("frmmultiplesinglechoice")) {
			index = 40;
		}
		if (formname.equalsIgnoreCase("frmmultiplesinglechoiceone")) {
			index = 41;
		}
		if (formname.equalsIgnoreCase("frmmultiplesinglechoicetwo")) {
			index = 42;
		}
		return index;
	}

	public void gotoForm(String formname) {
		// TODO Auto-generated method stub
		if (CommonStaticClass.dataId.length() > 0) {
			dataidViewer.setText("Patient ID: " + CommonStaticClass.dataId);
		}

		/*
		 * final String fName = formname; progressDialog =
		 * ProgressDialog.show(con, "Wait", "Please wait while processing");
		 */

		lastIndexBeforeFraNotes = formFlipper.getDisplayedChild();
		formFlipper.setDisplayedChild(getFormIndex(formname));
		formFlipper.setInAnimation(this, android.R.anim.slide_in_left);
		formFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
		/*
		 * new Thread() {
		 * 
		 * public void run() {
		 * 
		 * 
		 * 
		 * try {
		 * 
		 * Looper.prepare();
		 */

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
			// loadGuiFrmCombosWithEditSpinner(20,
			// frmcomboswitheditspiner);
			break;

		case 22:
			loadGuiMultipleCheckCombotwo(frmmultiplecheckcombotwo);
			break;

		case 23:
			// loadGuiFrmMultipleChoiceRadio(frmmultiplechoiceradio);
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
			loadguifrmpatientdetail(frmpatientdetail);
			break;

		case 29:
			loadguifrmweight(frmweight);
			break;

		case 30:
			loadguifrmdiarrhea(frmdiarrhea);
			break;

		case 31:
			loadguifrmvomiting(frmvomiting);
			break;

		case 32:
			loadguifrmors(frmors);
			break;

		case 33:
			loadguifrmchildren(frmchildren);
			break;

		case 34:
			loadguifrmmultiplechoicetext(frmmultiplechoicetext);
			break;

		case 35:
			loadguifrmaddress(frmaddress);
			break;

		case 36:
			loadguifrmtravelinfo(frmtravelinfo);
			break;

		case 37:
			loadguifrmpersonrelation(frmpersonrelation);
			break;

		case 38:

			loadguifrmsymptoms(frmsymptoms);
			break;

		case 39:

			loadguifrmsymptomsone(frmsymptomsone);

			break;

		case 40:
			loadguifrmmultiplesinglechoice(frmmultiplesinglechoice);
			break;

		case 41:
			loadguifrmmultiplesinglechoiceone(frmmultiplesinglechoiceone);
			break;

		case 42:
			loadguifrmmultiplesinglechoicetwo(frmmultiplesinglechoicetwo);
			break;

		default:

			break;
		}
		/*
		 * } Message msg = new Message(); msg.what = UPDATEDONE;
		 * handlerFrmHHID.sendMessage(msg); } catch (Exception ex) { Message msg
		 * = new Message(); msg.what = UPDATEDONE;
		 * handlerFrmHHID.sendMessage(msg); } } }.start();
		 */

	}

	private void ControlInEnglishFrmmultiplesinglechoiceone(ViewGroup v) {
		try {

			((CheckBox) v.findViewById(R.id.chk1)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk2)).setTypeface(null);

			((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroup1))
					.findViewById(R.id.Rb1)).setTypeface(null);
			((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroup1))
					.findViewById(R.id.Rb2)).setTypeface(null);

			((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroup2))
					.findViewById(R.id.Rb3)).setTypeface(null);

			((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroup2))
					.findViewById(R.id.Rb4)).setTypeface(null);

			((TextView) v.findViewById(R.id.lbldeep)).setTypeface(null);
			((TextView) v.findViewById(R.id.lblSubQuestion)).setTypeface(null);
			((TextView) v.findViewById(R.id.lblBleeding)).setTypeface(null);

		} catch (Exception ex) {
			CommonStaticClass.showMyAlert(thisactivity, "Error",
					ex.getMessage());
		} finally {
		}
	}

	private void ControlInBanglafrmmultiplesinglechoiceone(ViewGroup v) {

		try {
			Typeface font = Typeface.createFromAsset(getAssets(),
					"Siyam Rupali ANSI.ttf");

			((CheckBox) v.findViewById(R.id.chk1)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk2)).setTypeface(null);
			((TextView) v.findViewById(R.id.lbldeep)).setTypeface(null);
			((TextView) v.findViewById(R.id.lblBleeding)).setTypeface(null);
			((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroup1))
					.findViewById(R.id.Rb1)).setTypeface(null);
			((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroup1))
					.findViewById(R.id.Rb2)).setTypeface(null);

			((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroup2))
					.findViewById(R.id.Rb3)).setTypeface(null);

			((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroup2))
					.findViewById(R.id.Rb4)).setTypeface(null);
			((TextView) v.findViewById(R.id.lbldeep)).setTypeface(null);
			((TextView) v.findViewById(R.id.lblSubQuestion)).setTypeface(null);

			((CheckBox) v.findViewById(R.id.chk1)).setTypeface(font);
			((CheckBox) v.findViewById(R.id.chk2)).setTypeface(font);
			((RadioButton) v.findViewById(R.id.Rb1)).setTypeface(font);
			((RadioButton) v.findViewById(R.id.Rb2)).setTypeface(font);

			((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroup2))
					.findViewById(R.id.Rb3)).setTypeface(font);

			((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroup2))
					.findViewById(R.id.Rb4)).setTypeface(font);
			((TextView) v.findViewById(R.id.lbldeep)).setTypeface(font);
			((TextView) v.findViewById(R.id.lblSubQuestion)).setTypeface(font);
			((TextView) v.findViewById(R.id.lblBleeding)).setTypeface(font);

		} catch (Exception ex) {
			CommonStaticClass.showMyAlert(thisactivity, "Error",
					ex.getMessage());
		} finally {
		}

	}

	private void loadguifrmmultiplesinglechoiceone(ViewGroup v) {

		qqq = (TextView) v.findViewById(R.id.qqq);

		resetViewGroup(v);
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

		qName = CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar();
		if (!CommonStaticClass.langBng) {
			ControlInEnglishFrmmultiplesinglechoiceone(v);
		} else if (CommonStaticClass.langBng) {
			ControlInBanglafrmmultiplesinglechoiceone(v);
		}

		LoadQuesTypefrmmultiplesingchoiceone(qName, v);

		final ViewGroup vg = v;

		((RadioGroup) v.findViewById(R.id.radioGroup1))
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {

						if (((RadioButton) group.findViewById(R.id.Rb1))
								.isChecked() == true) {

							((CheckBox) vg.findViewById(R.id.chk1))
									.setEnabled(true);
							((CheckBox) vg.findViewById(R.id.chk2))
									.setEnabled(true);
							((TextView) vg.findViewById(R.id.lbldeep))
									.setEnabled(true);
							((EditText) vg.findViewById(R.id.txtNumber))
									.setVisibility(View.VISIBLE);
							((EditText) vg.findViewById(R.id.txtNumber))
									.setEnabled(true);
							((TextView) vg.findViewById(R.id.lblBleeding))
									.setEnabled(true);
							((RadioButton) ((RadioGroup) vg
									.findViewById(R.id.radioGroup2))
									.findViewById(R.id.Rb3)).setEnabled(true);
							((RadioButton) ((RadioGroup) vg
									.findViewById(R.id.radioGroup2))
									.findViewById(R.id.Rb4)).setEnabled(true);
							// Rb4.Enabled = true;
							if (qName.equalsIgnoreCase("q6_8")
									&& ((RadioButton) ((RadioGroup) vg
											.findViewById(R.id.radioGroup1))
											.findViewById(R.id.Rb1))
											.isChecked() == true) {

								((TextView) vg.findViewById(R.id.lblother))
										.setVisibility(View.VISIBLE);
								((EditText) vg.findViewById(R.id.txtOther))
										.setVisibility(View.VISIBLE);
							} else {
								((TextView) vg.findViewById(R.id.lblother))
										.setVisibility(View.GONE);
								((EditText) vg.findViewById(R.id.txtOther))
										.setVisibility(View.GONE);

							}
						} else if (((RadioButton) group.findViewById(R.id.Rb2))
								.isChecked() == true) {

							((CheckBox) vg.findViewById(R.id.chk1))
									.setEnabled(false);
							((CheckBox) vg.findViewById(R.id.chk2))
									.setEnabled(false);
							((TextView) vg.findViewById(R.id.lbldeep))
									.setEnabled(false);
							((EditText) vg.findViewById(R.id.txtNumber))
									.setVisibility(View.GONE);
							((EditText) vg.findViewById(R.id.txtNumber))
									.setEnabled(false);
							((TextView) vg.findViewById(R.id.lblBleeding))
									.setEnabled(false);
							((RadioGroup) vg.findViewById(R.id.radioGroup2))
									.clearCheck();
							((RadioButton) ((RadioGroup) vg
									.findViewById(R.id.radioGroup2))
									.findViewById(R.id.Rb3)).setEnabled(false);
							((RadioButton) ((RadioGroup) vg
									.findViewById(R.id.radioGroup2))
									.findViewById(R.id.Rb4)).setEnabled(false);

						}

					}
				});
		LoadDatafrmmultiplesingchoiceone(qName, v);

		try {
			saveNxtButton = (Button) v.findViewById(R.id.saveNxtButton);

			saveNxtButton.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (UpdateDataFrmMultipleSingleChoiceOne(vg)) {

					} else {
						CommonStaticClass.showMyAlert(thisactivity, "Message",
								"Please fill all fields correctly");
					}

					// preserveState();
				}

			});
			clButton = (Button) v.findViewById(R.id.clButton);
			clButton.setOnClickListener(new View.OnClickListener() {

				public void onClick(View vv) {
					// TODO Auto-generated method stub
					// resetViewGroup((ViewGroup) v);
				}

			});

			/*
			 * * notesButton = (Button) v.findViewById(R.id.btnNote);
			 * notesButton.setOnClickListener(new OnClickListener() {
			 * 
			 * public void onClick(View v) { // TODO Auto-generated method stub
			 * FraNotes();
			 * 
			 * }
			 * 
			 * });
			 */

			prevButton = (Button) v.findViewById(R.id.prevButton);
			prevButton.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					userPressedPrevious(ParentActivity.this);
				}

			});

		} catch (Exception ex) {
			CommonStaticClass.showMyAlert(thisactivity, "Error",
					"Error Form Load.");
		}

		finally {

		}

	}

	private boolean CheckValidationfrmmultiplesinglechoice(ViewGroup v) {
		if (((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroupRb))
				.findViewById(R.id.Rb1)).isChecked() == false
				&& (((RadioButton) ((RadioGroup) v
						.findViewById(R.id.radioGroupRb))
						.findViewById(R.id.Rb2)).isChecked() == false)) {
			CommonStaticClass.showMyAlert(thisactivity, "Message",
					"Please select any option from YES or No choice.");
			return false;
		}

		if (((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroupRb))
				.findViewById(R.id.Rb1)).isChecked() == true) {
			if (((CheckBox) v.findViewById(R.id.chk1)).isChecked() == false
					&& ((CheckBox) v.findViewById(R.id.chk2)).isChecked() == false
					&& ((CheckBox) v.findViewById(R.id.chk3)).isChecked() == false
					&& ((CheckBox) v.findViewById(R.id.chk4)).isChecked() == false) {
				CommonStaticClass.showMyAlert(thisactivity, "Message",
						"Please select any option");

				return false;
			}

		}

		return true;
	}

	private boolean CheckValidationfrmmultiplesinglechoiceone(ViewGroup v) {
		if (((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroup1))
				.findViewById(R.id.Rb1)).isChecked() == false
				&& (((RadioButton) ((RadioGroup) v
						.findViewById(R.id.radioGroup1)).findViewById(R.id.Rb2))
						.isChecked() == false)) {
			CommonStaticClass.showMyAlert(thisactivity, "Message",
					"Please select any option from YES or No choice.");
			return false;
		}

		if (((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroup1))
				.findViewById(R.id.Rb1)).isChecked() == true) {
			if (((CheckBox) v.findViewById(R.id.chk1)).isChecked() == false
					&& ((CheckBox) v.findViewById(R.id.chk2)).isChecked() == false) {
				CommonStaticClass.showMyAlert(thisactivity, "Message",
						"Please select any option");

				return false;
			}
			if (((EditText) v.findViewById(R.id.txtNumber)).getText()
					.toString().length() <= 0) {
				CommonStaticClass.showMyAlert(thisactivity, "Message",
						"Please type How deep was the wound?(cm).");
				return false;
			}

			if (((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroup2))
					.findViewById(R.id.Rb3)).isChecked() == false
					&& (((RadioButton) ((RadioGroup) v
							.findViewById(R.id.radioGroup2))
							.findViewById(R.id.Rb4)).isChecked() == false)) {
				CommonStaticClass
						.showMyAlert(thisactivity, "Message",
								"Please select Did you have trouble stopping the bleeding?");
				return false;
			}

		}

		return true;
	}

	private StringBuilder UpdateQryBuilderfrmmultiplesinglechoiceone(ViewGroup v) {

		StringBuilder SB = new StringBuilder();

		if (((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroup1))
				.findViewById(R.id.Rb1)).isChecked() == true) {
			SB.append(" set "
					+ qName
					+ " = "
					+ CommonStaticClass
							.CodeCheck(((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroup1))
									.findViewById(R.id.Rb1)).getText()
									.toString().trim()
									+ ""));

			if (((CheckBox) v.findViewById(R.id.chk1)).isChecked() == true) {
				SB.append(", " + qName + "Bitten = 1");
			} else if (((CheckBox) v.findViewById(R.id.chk1)).isChecked() == false) {
				SB.append(", " + qName + "Bitten = 2");
			}
			if (((CheckBox) v.findViewById(R.id.chk2)).isChecked() == true) {
				SB.append(", " + qName + "Scratched = 1");
			} else if (((CheckBox) v.findViewById(R.id.chk2)).isChecked() == false) {
				SB.append(", " + qName + "Scratched = 2");
			}

			SB.append(", "
					+ qName
					+ "Wound = '"
					+ ((EditText) v.findViewById(R.id.txtNumber)).getText()
							.toString() + "'");
			if (((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroup2))
					.findViewById(R.id.Rb3)).isChecked() == true) {
				SB.append(", " + qName + "Bleeding = 1");
			} else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroup2)).findViewById(R.id.Rb4))
					.isChecked() == true) {
				SB.append(", " + qName + "Bleeding = 2");
			}

			if (qName.equalsIgnoreCase("q6_8")
					&& ((RadioButton) ((RadioGroup) v
							.findViewById(R.id.radioGroup1))
							.findViewById(R.id.Rb1)).isChecked() == true) {
				SB.append(", "
						+ qName
						+ "Name = '"
						+ ((EditText) v.findViewById(R.id.txtOther)).getText()
								.toString() + "'");

			}

		} else if (((RadioButton) ((RadioGroup) v
				.findViewById(R.id.radioGroup1)).findViewById(R.id.Rb2))
				.isChecked() == true) {
			SB.append(" set "
					+ qName
					+ " = "
					+ CommonStaticClass
							.CodeCheck(((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroup1))
									.findViewById(R.id.Rb2)).getText()
									.toString().trim()) + "");
			SB.append(", " + qName + "Bitten = null");
			SB.append(", " + qName + "Scratched = null");
			SB.append(", " + qName + "Wound =null");
			SB.append(", " + qName + "Bleeding =null");
			if (qName == "q6_8"
					&& ((RadioButton) ((RadioGroup) v
							.findViewById(R.id.radioGroup1))
							.findViewById(R.id.Rb2)).isChecked() == true) {
				SB.append(", " + qName + "Name =null");
			}

		}

		// We choose those question variable who's form name is
		// 'frmSoapPurchaseFreq' in the Database
		// ~~~~~Start~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		SB.append(" , EditBy='" + CommonStaticClass.userSpecificId + "'");
		SB.append(" , EditDate='" + CommonStaticClass.GetDate() + "'");
		// ~~~~~~~~End~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		return SB;
	}

	private boolean UpdateDataFrmMultipleSingleChoiceOne(ViewGroup v) {
		String strSQL = "";

		try {
			if (CheckValidationfrmmultiplesinglechoiceone(v) == false) {
				return false;
			}

			strSQL = "UPDATE "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename() + " "
					+ UpdateQryBuilderfrmmultiplesinglechoiceone(v).toString()
					+ " where dataid = '" + CommonStaticClass.dataId + "' ";

			if (dbHelper.executeDMLQuery(strSQL)) {

				if (CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar()
						.equalsIgnoreCase("q6_8")) {

					if (dbHelper
							.IsDataExistsAndNotNull(String
									.format("SELECT COUNT(*) FROM tblMainQuesEPT WHERE (q2_5='2')  AND dataid = '%s'",
											CommonStaticClass.dataId))) {

						CommonStaticClass.findOutNextSLNo(
								CommonStaticClass.questionMap.get(
										CommonStaticClass.currentSLNo)
										.getQvar(), "END");
						CommonStaticClass.nextQuestion(ParentActivity.this);

						return true;
					}
				}

				CommonStaticClass.findOutNextSLNo(
						CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQvar(),
						CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQnext1());
				CommonStaticClass.nextQuestion(ParentActivity.this);
				return true;
			} /*
			 * else { return false; }
			 */

		}

		catch (Exception ex) {
			CommonStaticClass.showMyAlert(thisactivity, "Error",
					ex.getMessage());
		} finally {

		}

		return false;
	}

	private void ControlInEnglish(ViewGroup v) {
		try {
			/*
			 * lblSubQuestion.Font = new System.Drawing.Font("Tahoma", 7F,
			 * System.Drawing.FontStyle.Regular); lblQuestion.Font = new
			 * System.Drawing.Font("Tahoma", 7F,
			 * System.Drawing.FontStyle.Regular);
			 */

			((CheckBox) v.findViewById(R.id.chk1)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk2)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk3)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk4)).setTypeface(null);
			((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroupRb))
					.findViewById(R.id.Rb1)).setTypeface(null);
			((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroupRb))
					.findViewById(R.id.Rb2)).setTypeface(null);

		} catch (Exception ex) {
			CommonStaticClass.showMyAlert(thisactivity, "Error",
					ex.getMessage());
		} finally {
		}
	}

	private void ControlInBangla(ViewGroup v) {
		try {
			Typeface font = Typeface.createFromAsset(getAssets(),
					"Siyam Rupali ANSI.ttf");

			((CheckBox) v.findViewById(R.id.chk1)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk2)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk3)).setTypeface(null);
			((CheckBox) v.findViewById(R.id.chk4)).setTypeface(null);
			((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroupRb))
					.findViewById(R.id.Rb1)).setTypeface(null);
			((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroupRb))
					.findViewById(R.id.Rb2)).setTypeface(null);

			((CheckBox) v.findViewById(R.id.chk1)).setTypeface(font);
			((CheckBox) v.findViewById(R.id.chk2)).setTypeface(font);
			((CheckBox) v.findViewById(R.id.chk3)).setTypeface(font);
			((CheckBox) v.findViewById(R.id.chk4)).setTypeface(font);
			((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroupRb))
					.findViewById(R.id.Rb1)).setTypeface(font);
			((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroupRb))
					.findViewById(R.id.Rb2)).setTypeface(font);

		} catch (Exception ex) {
			CommonStaticClass.showMyAlert(thisactivity, "Error",
					ex.getMessage());
		} finally {
		}
	}

	private void loadguifrmmultiplesinglechoice(final ViewGroup v) {

		try {
			qqq = (TextView) v.findViewById(R.id.qqq);

			resetViewGroup(v);
			if (CommonStaticClass.langBng) {
				if (CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQdescbng()
						.length() > 0) {
					Typeface font = Typeface.createFromAsset(getAssets(),
							"Siyam Rupali ANSI.ttf");
					qqq.setTypeface(font);
				}
				;
				qqq.setText(CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQdescbng()
						.length() > 0 ? CommonStaticClass.questionMap.get(
						CommonStaticClass.currentSLNo).getQdescbng()
						: CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQdesceng());
			} else {
				qqq.setTypeface(null);
				qqq.setText(CommonStaticClass.questionMap.get(
						CommonStaticClass.currentSLNo).getQdesceng());
			}

			qName = CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQvar();

			((TextView) v.findViewById(R.id.lbl1)).setText("");// .Text =
																// ConManager.ReadQuestion(lblSubQuestion,
																// _strQVar,
																// _intLang);
			if (!CommonStaticClass.langBng) {
				ControlInEnglish(v);
			} else if (CommonStaticClass.langBng) {
				ControlInBangla(v);
			}

			((RadioGroup) v.findViewById(R.id.radioGroupRb))
					.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(RadioGroup group,
								int checkedId) {
							if (((RadioButton) group.findViewById(R.id.Rb1))
									.isChecked() == true) {
								if (qName.equalsIgnoreCase("q5_1")
										|| qName.equalsIgnoreCase("q5_2")
										|| qName.equalsIgnoreCase("q5_3")
										|| qName.equalsIgnoreCase("q5_4")
										|| qName.equalsIgnoreCase("q5_5")
										|| qName.equalsIgnoreCase("q5_6")
										|| qName.equalsIgnoreCase("q5_7")
										|| qName.equalsIgnoreCase("q5_8")
										|| qName.equalsIgnoreCase("q5_9")
										|| qName.equalsIgnoreCase("q5_10")) {
									((CheckBox) v.findViewById(R.id.chk1))
											.setVisibility(View.VISIBLE);
									((CheckBox) v.findViewById(R.id.chk2))
											.setVisibility(View.VISIBLE);
									((CheckBox) v.findViewById(R.id.chk3))
											.setVisibility(View.VISIBLE);
								}

							} else if (((RadioButton) group
									.findViewById(R.id.Rb2)).isChecked() == true) {
								if (qName.equalsIgnoreCase("q5_1")
										|| qName.equalsIgnoreCase("q5_2")
										|| qName.equalsIgnoreCase("q5_3")
										|| qName.equalsIgnoreCase("q5_4")
										|| qName.equalsIgnoreCase("q5_5")
										|| qName.equalsIgnoreCase("q5_6")
										|| qName.equalsIgnoreCase("q5_7")
										|| qName.equalsIgnoreCase("q5_8")
										|| qName.equalsIgnoreCase("q5_9")
										|| qName.equalsIgnoreCase("q5_10")) {
									((CheckBox) v.findViewById(R.id.chk1))
											.setVisibility(View.GONE);
									((CheckBox) v.findViewById(R.id.chk2))
											.setVisibility(View.GONE);
									((CheckBox) v.findViewById(R.id.chk3))
											.setVisibility(View.GONE);
								}
							}

						}
					});

			LoadQuesType(qName, v);
			LoadData(qName, v);

			final ViewGroup vg = v;

			saveNxtButton = (Button) v.findViewById(R.id.saveNxtButton);

			saveNxtButton.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (CheckValidationfrmmultiplesinglechoice(vg))
						UpdateDataFrmMultipleSingleChoice(vg);
					// else

					/*
					 * CommonStaticClass.showMyAlert(thisactivity, "Message",
					 * "Please fill all fields correctly");
					 */

					// preserveState();
				}

			});
			clButton = (Button) v.findViewById(R.id.clButton);
			clButton.setOnClickListener(new View.OnClickListener() {

				public void onClick(View vv) {
					// TODO Auto-generated method stub
					// resetViewGroup((ViewGroup) v);
				}

			});

			/*
			 * * notesButton = (Button) v.findViewById(R.id.btnNote);
			 * notesButton.setOnClickListener(new OnClickListener() {
			 * 
			 * public void onClick(View v) { // TODO Auto-generated method stub
			 * FraNotes();
			 * 
			 * }
			 * 
			 * });
			 */

			prevButton = (Button) v.findViewById(R.id.prevButton);
			prevButton.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					userPressedPrevious(ParentActivity.this);
				}

			});

		} catch (Exception ex) {
			CommonStaticClass.showMyAlert(thisactivity, "Error",
					"Error Form Load.");
		}

		finally {

		}

	}

	private void loadguifrmmultiplesinglechoicetwo(final ViewGroup v) {

		try {
			qqq = (TextView) v.findViewById(R.id.qqq);

			resetViewGroup(v);
			if (CommonStaticClass.langBng) {
				if (CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQdescbng()
						.length() > 0) {
					Typeface font = Typeface.createFromAsset(getAssets(),
							"Siyam Rupali ANSI.ttf");
					qqq.setTypeface(font);
				}
				;
				qqq.setText(CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQdescbng()
						.length() > 0 ? CommonStaticClass.questionMap.get(
						CommonStaticClass.currentSLNo).getQdescbng()
						: CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQdesceng());
			} else {
				qqq.setTypeface(null);
				qqq.setText(CommonStaticClass.questionMap.get(
						CommonStaticClass.currentSLNo).getQdesceng());
			}

			qName = CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQvar();

			((TextView) v.findViewById(R.id.lbl1)).setText("");// .Text =
																// ConManager.ReadQuestion(lblSubQuestion,
																// _strQVar,
																// _intLang);
			if (!CommonStaticClass.langBng) {
				ControlInEnglish(v);
			} else if (CommonStaticClass.langBng) {
				ControlInBangla(v);
			}

			((RadioGroup) v.findViewById(R.id.radioGroupRb))
					.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(RadioGroup group,
								int checkedId) {
							if (((RadioButton) group.findViewById(R.id.Rb1))
									.isChecked() == true) {
								if (qName.equalsIgnoreCase("q7_1")
										|| qName.equalsIgnoreCase("q7_2")
										|| qName.equalsIgnoreCase("q7_3")
										|| qName.equalsIgnoreCase("q7_4")
										|| qName.equalsIgnoreCase("q7_5")
										|| qName.equalsIgnoreCase("q7_6")
										|| qName.equalsIgnoreCase("q7_7")
										|| qName.equalsIgnoreCase("q7_8")
										|| qName.equalsIgnoreCase("q7_9")
										|| qName.equalsIgnoreCase("q7_10")) {
									((CheckBox) v.findViewById(R.id.chk1))
											.setVisibility(View.VISIBLE);
									((CheckBox) v.findViewById(R.id.chk2))
											.setVisibility(View.VISIBLE);
									((CheckBox) v.findViewById(R.id.chk3))
											.setVisibility(View.VISIBLE);
									((CheckBox) v.findViewById(R.id.chk4))
											.setVisibility(View.VISIBLE);
								}

							} else if (((RadioButton) group
									.findViewById(R.id.Rb2)).isChecked() == true) {
								if (qName.equalsIgnoreCase("q7_1")
										|| qName.equalsIgnoreCase("q7_2")
										|| qName.equalsIgnoreCase("q7_3")
										|| qName.equalsIgnoreCase("q7_4")
										|| qName.equalsIgnoreCase("q7_5")
										|| qName.equalsIgnoreCase("q7_6")
										|| qName.equalsIgnoreCase("q7_7")
										|| qName.equalsIgnoreCase("q7_8")
										|| qName.equalsIgnoreCase("q7_9")
										|| qName.equalsIgnoreCase("q7_10")) {
									((CheckBox) v.findViewById(R.id.chk1))
											.setVisibility(View.GONE);
									((CheckBox) v.findViewById(R.id.chk2))
											.setVisibility(View.GONE);
									((CheckBox) v.findViewById(R.id.chk3))
											.setVisibility(View.GONE);

									((CheckBox) v.findViewById(R.id.chk4))
											.setVisibility(View.GONE);

								}
							}

						}
					});

			LoadQuesTypefrmmultiplesinglechoicetwo(qName, v);
			LoadDataFrmmultiplesinglechoicetwo(qName, v);

			final ViewGroup vg = v;

			saveNxtButton = (Button) v.findViewById(R.id.saveNxtButton);

			saveNxtButton.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (CheckValidationfrmmultiplesinglechoice(vg))
						UpdateDataFrmMultipleSingleChoicetwo(vg);
					// else

					/*
					 * CommonStaticClass.showMyAlert(thisactivity, "Message",
					 * "Please fill all fields correctly");
					 */

					// preserveState();
				}

			});
			clButton = (Button) v.findViewById(R.id.clButton);
			clButton.setOnClickListener(new View.OnClickListener() {

				public void onClick(View vv) {
					// TODO Auto-generated method stub
					// resetViewGroup((ViewGroup) v);
				}

			});

			/*
			 * * notesButton = (Button) v.findViewById(R.id.btnNote);
			 * notesButton.setOnClickListener(new OnClickListener() {
			 * 
			 * public void onClick(View v) { // TODO Auto-generated method stub
			 * FraNotes();
			 * 
			 * }
			 * 
			 * });
			 */

			prevButton = (Button) v.findViewById(R.id.prevButton);
			prevButton.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					userPressedPrevious(ParentActivity.this);
				}

			});

		} catch (Exception ex) {
			CommonStaticClass.showMyAlert(thisactivity, "Error",
					"Error Form Load.");
		}

		finally {

		}

	}

	private StringBuilder UpdateQryBuilderFrmMultipleSingleChocie(ViewGroup v) {

		StringBuilder SB = new StringBuilder();
		if (((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroupRb))
				.findViewById(R.id.Rb1)).isChecked() == true) {
			SB.append(" set "
					+ qName
					+ " = "
					+ CommonStaticClass
							.CodeCheck(((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupRb))
									.findViewById(R.id.Rb1)).getText()
									.toString()) + "");
			if (((CheckBox) v.findViewById(R.id.chk1)).isChecked() == true) {
				SB.append(", " + qName + "a1 = 1");
			} else if (((CheckBox) v.findViewById(R.id.chk1)).isChecked() == false) {
				SB.append(", " + qName + "a1 = 2");
			}
			if (((CheckBox) v.findViewById(R.id.chk2)).isChecked() == true) {
				SB.append(", " + qName + "a2 = 1");
			} else if (((CheckBox) v.findViewById(R.id.chk2)).isChecked() == false) {
				SB.append(", " + qName + "a2 = 2");
			}
			if (((CheckBox) v.findViewById(R.id.chk3)).isChecked() == true) {
				SB.append(", " + qName + "a3 = 1");
			} else if (((CheckBox) v.findViewById(R.id.chk3)).isChecked() == false) {
				SB.append(", " + qName + "a3 = 2");
			}

		} else if (((RadioButton) ((RadioGroup) v
				.findViewById(R.id.radioGroupRb)).findViewById(R.id.Rb2))
				.isChecked() == true) {
			SB.append(" set "
					+ qName
					+ " = "
					+ CommonStaticClass
							.CodeCheck(((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupRb))
									.findViewById(R.id.Rb2)).getText()
									.toString()) + "");
			SB.append(", " + qName + "a1 = null");
			SB.append(", " + qName + "a2 = null");
			SB.append(", " + qName + "a3 = null");
			SB.append(", " + qName + "b_1 = null");
			SB.append(", " + qName + "b_2 = null");
			SB.append(", " + qName + "b_3 = null");
			SB.append(", " + qName + "b_4 = null");
			SB.append(", " + qName + "b_5 = null");
			SB.append(", " + qName + "b_6 = null");
			SB.append(", " + qName + "b_7 = null");
			SB.append(", " + qName + "b_8 = null");
			SB.append(", " + qName + "b_9 = null");
			SB.append(", " + qName + "b_777 = null");
			SB.append(", " + qName + "bOther = null");

		}

		// We choose those question variable who's form name is
		// 'frmSoapPurchaseFreq' in the Database
		// ~~~~~Start~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		SB.append(" , EditBy='" + CommonStaticClass.userSpecificId + "'");
		SB.append(" , EditDate=" + CommonStaticClass.GetDate());
		// ~~~~~~~~End~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		return SB;
	}

	private StringBuilder UpdateQryBuilderFrmMultipleSingleChocietwo(ViewGroup v) {

		StringBuilder SB = new StringBuilder();
		if (((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroupRb))
				.findViewById(R.id.Rb1)).isChecked() == true) {
			SB.append(" set "
					+ qName
					+ " = "
					+ CommonStaticClass
							.CodeCheck(((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupRb))
									.findViewById(R.id.Rb1)).getText()
									.toString()) + "");
			if (((CheckBox) v.findViewById(R.id.chk1)).isChecked() == true) {
				SB.append(", " + qName + "a1 = 1");
			} else if (((CheckBox) v.findViewById(R.id.chk1)).isChecked() == false) {
				SB.append(", " + qName + "a1 = 2");
			}
			if (((CheckBox) v.findViewById(R.id.chk2)).isChecked() == true) {
				SB.append(", " + qName + "a2 = 1");
			} else if (((CheckBox) v.findViewById(R.id.chk2)).isChecked() == false) {
				SB.append(", " + qName + "a2 = 2");
			}
			if (((CheckBox) v.findViewById(R.id.chk3)).isChecked() == true) {
				SB.append(", " + qName + "a3 = 1");
			} else if (((CheckBox) v.findViewById(R.id.chk3)).isChecked() == false) {
				SB.append(", " + qName + "a3 = 2");
			}

			if (((CheckBox) v.findViewById(R.id.chk4)).isChecked() == true) {
				SB.append(", " + qName + "a4 = 1");
			} else if (((CheckBox) v.findViewById(R.id.chk4)).isChecked() == false) {
				SB.append(", " + qName + "a4 = 2");
			}

		} else if (((RadioButton) ((RadioGroup) v
				.findViewById(R.id.radioGroupRb)).findViewById(R.id.Rb2))
				.isChecked() == true) {
			SB.append(" set "
					+ qName
					+ " = "
					+ CommonStaticClass
							.CodeCheck(((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupRb))
									.findViewById(R.id.Rb2)).getText()
									.toString()) + "");
			SB.append(", " + qName + "a1 = null");
			SB.append(", " + qName + "a2 = null");
			SB.append(", " + qName + "a3 = null");
			SB.append(", " + qName + "a4 = null");
			SB.append(", " + qName + "b_1 = null");
			SB.append(", " + qName + "b_2 = null");
			SB.append(", " + qName + "b_3 = null");
			SB.append(", " + qName + "b_777 = null");
			SB.append(", " + qName + "bOther = null");

		}

		// We choose those question variable who's form name is
		// 'frmSoapPurchaseFreq' in the Database
		// ~~~~~Start~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		SB.append(" , EditBy='" + CommonStaticClass.userSpecificId + "'");
		SB.append(" , EditDate=" + CommonStaticClass.GetDate());
		// ~~~~~~~~End~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		return SB;
	}

	private boolean UpdateDataFrmMultipleSingleChoice(ViewGroup v) {
		String strSQL = "";

		try {
			/*
			 * if (CheckValidationfrmmultiplesinglechoice(v)== false) { return
			 * false; }
			 */
			if (nextToGo == null) {
				nextToGo = "";
			}
			nextToGo = nextToGo.length() > 0 ? nextToGo
					: CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQnext1();

			strSQL = "";
			StringBuilder SB = UpdateQryBuilderFrmMultipleSingleChocie(v);
			strSQL = "UPDATE "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename() + " "
					+ SB.toString() + " where dataid = '"
					+ CommonStaticClass.dataId + "' ";

			if (dbHelper.executeDMLQuery(strSQL)) {

				if (CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar()
						.equalsIgnoreCase("q5_1")

						|| CommonStaticClass.questionMap
								.get(CommonStaticClass.currentSLNo).getQvar()
								.equalsIgnoreCase("q5_2")

						|| CommonStaticClass.questionMap
								.get(CommonStaticClass.currentSLNo).getQvar()
								.equalsIgnoreCase("q5_3")

						|| CommonStaticClass.questionMap
								.get(CommonStaticClass.currentSLNo).getQvar()
								.equalsIgnoreCase("q5_4")

						|| CommonStaticClass.questionMap
								.get(CommonStaticClass.currentSLNo).getQvar()
								.equalsIgnoreCase("q5_5")

						|| CommonStaticClass.questionMap
								.get(CommonStaticClass.currentSLNo).getQvar()
								.equalsIgnoreCase("q5_6")

						|| CommonStaticClass.questionMap
								.get(CommonStaticClass.currentSLNo).getQvar()
								.equalsIgnoreCase("q5_7")

						|| CommonStaticClass.questionMap
								.get(CommonStaticClass.currentSLNo).getQvar()
								.equalsIgnoreCase("q5_8")

						|| CommonStaticClass.questionMap
								.get(CommonStaticClass.currentSLNo).getQvar()
								.equalsIgnoreCase("q5_9")

						|| CommonStaticClass.questionMap
								.get(CommonStaticClass.currentSLNo).getQvar()
								.equalsIgnoreCase("q5_10")

				) {
					if (CommonStaticClass.questionMap
							.get(CommonStaticClass.currentSLNo).getQvar()
							.equalsIgnoreCase("q5_1")) {
						if (((RadioButton) ((RadioGroup) v
								.findViewById(R.id.radioGroupRb))
								.findViewById(R.id.Rb2)).isChecked() == true) {
							CommonStaticClass.qskipList.add("q5_1b");
						} else {
							CommonStaticClass.qskipList.remove("q5_1b");
						}
					}

					if (CommonStaticClass.questionMap
							.get(CommonStaticClass.currentSLNo).getQvar()
							.equalsIgnoreCase("q5_2")) {
						if (((RadioButton) ((RadioGroup) v
								.findViewById(R.id.radioGroupRb))
								.findViewById(R.id.Rb2)).isChecked() == true) {
							CommonStaticClass.qskipList.add("q5_2b");
						} else {
							CommonStaticClass.qskipList.remove("q5_2b");
						}
					}

					if (CommonStaticClass.questionMap
							.get(CommonStaticClass.currentSLNo).getQvar()
							.equalsIgnoreCase("q5_3")) {
						if (((RadioButton) ((RadioGroup) v
								.findViewById(R.id.radioGroupRb))
								.findViewById(R.id.Rb2)).isChecked() == true) {
							CommonStaticClass.qskipList.add("q5_3b");
						} else {
							CommonStaticClass.qskipList.remove("q5_3b");
						}
					}

					if (CommonStaticClass.questionMap
							.get(CommonStaticClass.currentSLNo).getQvar()
							.equalsIgnoreCase("q5_4")) {
						if (((RadioButton) ((RadioGroup) v
								.findViewById(R.id.radioGroupRb))
								.findViewById(R.id.Rb2)).isChecked() == true) {
							CommonStaticClass.qskipList.add("q5_4b");
						} else {
							CommonStaticClass.qskipList.remove("q5_4b");
						}
					}

					if (CommonStaticClass.questionMap
							.get(CommonStaticClass.currentSLNo).getQvar()
							.equalsIgnoreCase("q5_5")) {
						if (((RadioButton) ((RadioGroup) v
								.findViewById(R.id.radioGroupRb))
								.findViewById(R.id.Rb2)).isChecked() == true) {
							CommonStaticClass.qskipList.add("q5_5b");
						} else {
							CommonStaticClass.qskipList.remove("q5_5b");
						}
					}

					if (CommonStaticClass.questionMap
							.get(CommonStaticClass.currentSLNo).getQvar()
							.equalsIgnoreCase("q5_6")) {
						if (((RadioButton) ((RadioGroup) v
								.findViewById(R.id.radioGroupRb))
								.findViewById(R.id.Rb2)).isChecked() == true) {
							CommonStaticClass.qskipList.add("q5_6b");
						} else {
							CommonStaticClass.qskipList.remove("q5_6b");
						}
					}

					if (CommonStaticClass.questionMap
							.get(CommonStaticClass.currentSLNo).getQvar()
							.equalsIgnoreCase("q5_7")) {
						if (((RadioButton) ((RadioGroup) v
								.findViewById(R.id.radioGroupRb))
								.findViewById(R.id.Rb2)).isChecked() == true) {
							CommonStaticClass.qskipList.add("q5_7b");
						} else {
							CommonStaticClass.qskipList.remove("q5_7b");
						}
					}

					if (CommonStaticClass.questionMap
							.get(CommonStaticClass.currentSLNo).getQvar()
							.equalsIgnoreCase("q5_8")) {
						if (((RadioButton) ((RadioGroup) v
								.findViewById(R.id.radioGroupRb))
								.findViewById(R.id.Rb2)).isChecked() == true) {
							CommonStaticClass.qskipList.add("q5_8b");
						} else {
							CommonStaticClass.qskipList.remove("q5_8b");
						}
					}

					if (CommonStaticClass.questionMap
							.get(CommonStaticClass.currentSLNo).getQvar()
							.equalsIgnoreCase("q5_9")) {
						if (((RadioButton) ((RadioGroup) v
								.findViewById(R.id.radioGroupRb))
								.findViewById(R.id.Rb2)).isChecked() == true) {
							CommonStaticClass.qskipList.add("q5_9b");
						} else {
							CommonStaticClass.qskipList.remove("q5_9b");
						}
					}

					CommonStaticClass.qskipList.remove("q5_10b");
					CommonStaticClass.qskipList.remove("q5_10bOther");
					CommonStaticClass.qskipList.remove("q5_10Other");

					if (CommonStaticClass.questionMap
							.get(CommonStaticClass.currentSLNo).getQvar()
							.equalsIgnoreCase("q5_10")) {
						if (((RadioButton) ((RadioGroup) v
								.findViewById(R.id.radioGroupRb))
								.findViewById(R.id.Rb2)).isChecked() == true) {
							CommonStaticClass.qskipList.add("q5_10b");
							CommonStaticClass.qskipList.add("q5_10bOther");
							CommonStaticClass.qskipList.add("q5_10Other");
							// q5_10b

						} else {
							CommonStaticClass.qskipList.remove("q5_10b");
							CommonStaticClass.qskipList.remove("q5_10bOther");
							CommonStaticClass.qskipList.remove("q5_10Other");
						}
					}

					if (CommonStaticClass.questionMap
							.get(CommonStaticClass.currentSLNo).getQvar()
							.equalsIgnoreCase("q5_6")) {
						if (((RadioButton) ((RadioGroup) v
								.findViewById(R.id.radioGroupRb))
								.findViewById(R.id.Rb2)).isChecked() == true) {
							CommonStaticClass.qskipList.add("q5_6b");
						} else {
							CommonStaticClass.qskipList.remove("q5_6b");
						}
					}

				} else {

				}

				if (CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar()
						.equalsIgnoreCase("q5_10")) {

					if (((RadioButton) ((RadioGroup) v
							.findViewById(R.id.radioGroupRb))
							.findViewById(R.id.Rb1)).isChecked() == true) {

					}
					if (dbHelper
							.IsDataExistsAndNotNull(String
									.format("SELECT COUNT(*) FROM tblMainQuesEPT WHERE (q2_4='2' AND q2_5='2')  AND dataid = '%s'",
											CommonStaticClass.dataId))) {

						CommonStaticClass.findOutNextSLNo(
								CommonStaticClass.questionMap.get(
										CommonStaticClass.currentSLNo)
										.getQvar(), "END");
						CommonStaticClass.nextQuestion(ParentActivity.this);
						return true;
					} else {
						CommonStaticClass.findOutNextSLNo(
								CommonStaticClass.questionMap.get(
										CommonStaticClass.currentSLNo)
										.getQvar(),
								CommonStaticClass.questionMap.get(
										CommonStaticClass.currentSLNo)
										.getQnext1());
						CommonStaticClass.nextQuestion(ParentActivity.this);
						return true;
					}
				}

				else {

					CommonStaticClass.findOutNextSLNo(
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQvar(),
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQnext1());
					CommonStaticClass.nextQuestion(ParentActivity.this);
					return true;
				}
			}

		} catch (Exception ex) {
			CommonStaticClass.showMyAlert(thisactivity, "Error",
					ex.getMessage());
			return false;
		} finally {

		}
		return false;

	}

	private boolean UpdateDataFrmMultipleSingleChoicetwo(ViewGroup v) {
		String strSQL = "";

		try {
			/*
			 * if (CheckValidationfrmmultiplesinglechoice(v)== false) { return
			 * false; }
			 */

			strSQL = "";
			StringBuilder SB = UpdateQryBuilderFrmMultipleSingleChocietwo(v);
			strSQL = "UPDATE "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename() + " "
					+ SB.toString() + " where dataid = '"
					+ CommonStaticClass.dataId + "' ";

			// if (dbHelper.executeDMLQuery(strSQL)) {

			if (dbHelper.executeDMLQuery(strSQL)) {

				if (CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar()
						.equalsIgnoreCase("q7_1")

						|| CommonStaticClass.questionMap
								.get(CommonStaticClass.currentSLNo).getQvar()
								.equalsIgnoreCase("q7_2")

						|| CommonStaticClass.questionMap
								.get(CommonStaticClass.currentSLNo).getQvar()
								.equalsIgnoreCase("q7_3")

						|| CommonStaticClass.questionMap
								.get(CommonStaticClass.currentSLNo).getQvar()
								.equalsIgnoreCase("q7_4")

						|| CommonStaticClass.questionMap
								.get(CommonStaticClass.currentSLNo).getQvar()
								.equalsIgnoreCase("q7_5")

						|| CommonStaticClass.questionMap
								.get(CommonStaticClass.currentSLNo).getQvar()
								.equalsIgnoreCase("q7_6")

						|| CommonStaticClass.questionMap
								.get(CommonStaticClass.currentSLNo).getQvar()
								.equalsIgnoreCase("q7_7")

						|| CommonStaticClass.questionMap
								.get(CommonStaticClass.currentSLNo).getQvar()
								.equalsIgnoreCase("q7_8")

				/*
				 * || CommonStaticClass.questionMap
				 * .get(CommonStaticClass.currentSLNo)
				 * .getQvar().equalsIgnoreCase("q5_9")
				 * 
				 * || CommonStaticClass.questionMap
				 * .get(CommonStaticClass.currentSLNo)
				 * .getQvar().equalsIgnoreCase("q5_10")
				 */

				) {
					if (CommonStaticClass.questionMap
							.get(CommonStaticClass.currentSLNo).getQvar()
							.equalsIgnoreCase("q7_1")) {
						if (((RadioButton) ((RadioGroup) v
								.findViewById(R.id.radioGroupRb))
								.findViewById(R.id.Rb2)).isChecked() == true) {
							CommonStaticClass.qskipList.add("q7_1b");
						} else {
							CommonStaticClass.qskipList.remove("q7_1b");
						}
					}

					if (CommonStaticClass.questionMap
							.get(CommonStaticClass.currentSLNo).getQvar()
							.equalsIgnoreCase("q7_2")) {
						if (((RadioButton) ((RadioGroup) v
								.findViewById(R.id.radioGroupRb))
								.findViewById(R.id.Rb2)).isChecked() == true) {
							CommonStaticClass.qskipList.add("q7_2b");
						} else {
							CommonStaticClass.qskipList.remove("q7_2b");
						}
					}

					if (CommonStaticClass.questionMap
							.get(CommonStaticClass.currentSLNo).getQvar()
							.equalsIgnoreCase("q7_3")) {
						if (((RadioButton) ((RadioGroup) v
								.findViewById(R.id.radioGroupRb))
								.findViewById(R.id.Rb2)).isChecked() == true) {
							CommonStaticClass.qskipList.add("q7_3b");
						} else {
							CommonStaticClass.qskipList.remove("q7_3b");
						}
					}

					if (CommonStaticClass.questionMap
							.get(CommonStaticClass.currentSLNo).getQvar()
							.equalsIgnoreCase("q7_4")) {
						if (((RadioButton) ((RadioGroup) v
								.findViewById(R.id.radioGroupRb))
								.findViewById(R.id.Rb2)).isChecked() == true) {
							CommonStaticClass.qskipList.add("q7_4b");
						} else {
							CommonStaticClass.qskipList.remove("q7_4b");
						}
					}

					if (CommonStaticClass.questionMap
							.get(CommonStaticClass.currentSLNo).getQvar()
							.equalsIgnoreCase("q7_5")) {
						if (((RadioButton) ((RadioGroup) v
								.findViewById(R.id.radioGroupRb))
								.findViewById(R.id.Rb2)).isChecked() == true) {
							CommonStaticClass.qskipList.add("q7_5b");
						} else {
							CommonStaticClass.qskipList.remove("q7_5b");
						}
					}

					if (CommonStaticClass.questionMap
							.get(CommonStaticClass.currentSLNo).getQvar()
							.equalsIgnoreCase("q7_6")) {
						if (((RadioButton) ((RadioGroup) v
								.findViewById(R.id.radioGroupRb))
								.findViewById(R.id.Rb2)).isChecked() == true) {
							CommonStaticClass.qskipList.add("q7_6b");
						} else {
							CommonStaticClass.qskipList.remove("q7_6b");
						}
					}

					if (CommonStaticClass.questionMap
							.get(CommonStaticClass.currentSLNo).getQvar()
							.equalsIgnoreCase("q7_7")) {
						if (((RadioButton) ((RadioGroup) v
								.findViewById(R.id.radioGroupRb))
								.findViewById(R.id.Rb2)).isChecked() == true) {
							CommonStaticClass.qskipList.add("q7_7b");
						} else {
							CommonStaticClass.qskipList.remove("q7_7b");
						}
					}

					if (CommonStaticClass.questionMap
							.get(CommonStaticClass.currentSLNo).getQvar()
							.equalsIgnoreCase("q7_8")) {
						if (((RadioButton) ((RadioGroup) v
								.findViewById(R.id.radioGroupRb))
								.findViewById(R.id.Rb2)).isChecked() == true) {
							CommonStaticClass.qskipList.add("q7_8b");
							CommonStaticClass.qskipList.add("q7_8Other");
							CommonStaticClass.qskipList.add("q7_8bOther");
						} else {
							CommonStaticClass.qskipList.remove("q5_10b");
							CommonStaticClass.qskipList.remove("q5_10bOther");
							CommonStaticClass.qskipList.remove("q5_10Other");
						}
					}

				} else {

				}

				CommonStaticClass.findOutNextSLNo(
						CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQvar(),
						CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQnext1());
				CommonStaticClass.nextQuestion(ParentActivity.this);
				return true;
			}
			// }
			/*
			 * CommonStaticClass.findOutNextSLNo(
			 * CommonStaticClass.questionMap.get(
			 * CommonStaticClass.currentSLNo).getQvar(),
			 * CommonStaticClass.questionMap.get(
			 * CommonStaticClass.currentSLNo).getQnext1());
			 * CommonStaticClass.nextQuestion(ParentActivity.this); return true;
			 * }
			 */

		} catch (Exception ex) {
			CommonStaticClass.showMyAlert(thisactivity, "Error",
					ex.getMessage());
			return false;
		} finally {

		}
		return false;

	}

	String strFld1 = "";
	String strFld2 = "";
	String strFld3 = "";
	String strFld4 = "";
	String strFld5 = "";
	String strFld6 = "";
	String strFld7 = "";
	String strFld8 = "";

	private void SelectQueryBuilder() {
		strFld1 = "";
		strFld2 = "";
		strFld3 = "";
		strFld4 = "";
		strFld5 = "";
		strFld6 = "";
		strFld7 = "";
		strFld8 = "";

		// We choose those question variable who's form name is
		// 'frmSoapPurchaseFreq' in the Database

		// ~~~~~Start~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// For Q516
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		strFld1 = "" + qName + "";
		strFld2 = "" + qName + "a1";
		strFld3 = "" + qName + "a2";
		strFld4 = "" + qName + "a3";

	}

	private void LoadData(String strField, ViewGroup v) {
		/*
		 * strFld1 = "" + qName + ""; strFld2 = "" + qName + "Bitten"; strFld3 =
		 * "" + qName + "Scratched"; strFld4 = "" + qName + "Wound"; strFld5 =
		 * "" + qName + "Bleeding"; strFld6 = "" + qName + "Name";
		 */

		/*
		 * if (strField.equalsIgnoreCase("q5_1") ||
		 * strField.equalsIgnoreCase("q5_2") ||
		 * strField.equalsIgnoreCase("q5_3") ||
		 * strField.equalsIgnoreCase("q5_4") ||
		 * strField.equalsIgnoreCase("q5_5") ||
		 * strField.equalsIgnoreCase("q5_6")
		 * ||strField.equalsIgnoreCase("q5_7")) {
		 */

		strFld1 = "" + qName + "";
		strFld2 = "" + qName + "a1";
		strFld3 = "" + qName + "a2";
		strFld4 = "" + qName + "a3";
		// }
		String strSQL = "Select * FROM "
				+ CommonStaticClass.questionMap.get(
						CommonStaticClass.currentSLNo).getTablename()
				+ " where dataid = '" + CommonStaticClass.dataId + "' ";
		Cursor c = null;

		try {

			c = dbHelper.getQueryCursor(strSQL);

			if (c.getCount() > 0) {

				if (c.moveToFirst()) {

					do {

						/*
						 * if (strFld1.equalsIgnoreCase(CommonStaticClass
						 * .GetTableName(CommonStaticClass.questionMap
						 * .get(CommonStaticClass.currentSLNo) .getQvar()))) {
						 * ((RadioGroup) v.findViewById(R.id.radioGroupRb))
						 * .clearCheck();
						 * 
						 * }
						 */

						if (c.getString(c.getColumnIndex(strFld1)) == null
								|| c.getString(c.getColumnIndex(strFld1))
										.equalsIgnoreCase("")) {
							((RadioGroup) v.findViewById(R.id.radioGroupRb))
									.clearCheck();

						}

						else if (c.getString(c.getColumnIndex(strFld1))
								.toString().equalsIgnoreCase("1")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupRb))
									.findViewById(R.id.Rb1)).setChecked(true);

							if (CommonStaticClass.CheckCursorValue(c, strFld2)
									.equalsIgnoreCase("1")) {
								((CheckBox) v.findViewById(R.id.chk1))
										.setChecked(true);
							} else if (CommonStaticClass.CheckCursorValue(c,
									strFld2).equalsIgnoreCase("2")) {
								((CheckBox) v.findViewById(R.id.chk1))
										.setChecked(false);
							}
							if (CommonStaticClass.CheckCursorValue(c, strFld3)
									.equalsIgnoreCase("1")) {
								((CheckBox) v.findViewById(R.id.chk2))
										.setChecked(true);
							} else if (CommonStaticClass.CheckCursorValue(c,
									strFld3).equalsIgnoreCase("2")) {
								((CheckBox) v.findViewById(R.id.chk2))
										.setChecked(false);
							}
							if (CommonStaticClass.CheckCursorValue(c, strFld4)
									.equalsIgnoreCase("1")) {
								((CheckBox) v.findViewById(R.id.chk3))
										.setChecked(true);
							} else if (CommonStaticClass.CheckCursorValue(c,
									strFld4).equalsIgnoreCase("2")) {
								((CheckBox) v.findViewById(R.id.chk3))
										.setChecked(false);
							}

						} else if (CommonStaticClass.CheckCursorValue(c,
								strFld1).equalsIgnoreCase("0")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupRb))
									.findViewById(R.id.Rb2)).setChecked(true);

						}

						/*
						 * String val = mCursor.getString(0);
						 * if(val.equalsIgnoreCase("OccurrenceQuestions")) {
						 * LoadOccurrenceQuestions(v); }
						 */

					} while (c.moveToNext());

				}

			}

		} catch (Exception e) {

			CommonStaticClass.showMyAlert(thisactivity, "Error On Data Load", e
					.getMessage().toString());

		}

	}

	private void LoadDataFrmmultiplesinglechoicetwo(String strField, ViewGroup v) {
		strFld1 = "" + qName + "";
		strFld2 = "" + qName + "a1";
		strFld3 = "" + qName + "a2";
		strFld4 = "" + qName + "a3";
		strFld5 = "" + qName + "a4";

		String strSQL = "Select * FROM "
				+ CommonStaticClass.questionMap.get(
						CommonStaticClass.currentSLNo).getTablename()
				+ " where dataid = '" + CommonStaticClass.dataId + "' ";
		Cursor c = null;

		try {

			c = dbHelper.getQueryCursor(strSQL);

			if (c.getCount() > 0) {

				if (c.moveToFirst()) {

					do {

						if (c.getString(c.getColumnIndex(strFld1)) == null
								|| c.getString(c.getColumnIndex(strFld1))
										.equalsIgnoreCase("")) {
							((RadioGroup) v.findViewById(R.id.radioGroupRb))
									.clearCheck();

						}

						else if (c.getString(c.getColumnIndex(strFld1))
								.toString().equalsIgnoreCase("1")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupRb))
									.findViewById(R.id.Rb1)).setChecked(true);

							if (CommonStaticClass.CheckCursorValue(c, strFld2)
									.equalsIgnoreCase("1")) {
								((CheckBox) v.findViewById(R.id.chk1))
										.setChecked(true);
							} else if (CommonStaticClass.CheckCursorValue(c,
									strFld2).equalsIgnoreCase("2")) {
								((CheckBox) v.findViewById(R.id.chk1))
										.setChecked(false);
							}
							if (CommonStaticClass.CheckCursorValue(c, strFld3)
									.equalsIgnoreCase("1")) {
								((CheckBox) v.findViewById(R.id.chk2))
										.setChecked(true);
							} else if (CommonStaticClass.CheckCursorValue(c,
									strFld3).equalsIgnoreCase("2")) {
								((CheckBox) v.findViewById(R.id.chk2))
										.setChecked(false);
							}
							if (CommonStaticClass.CheckCursorValue(c, strFld4)
									.equalsIgnoreCase("1")) {
								((CheckBox) v.findViewById(R.id.chk3))
										.setChecked(true);
							} else if (CommonStaticClass.CheckCursorValue(c,
									strFld4).equalsIgnoreCase("2")) {
								((CheckBox) v.findViewById(R.id.chk3))
										.setChecked(false);
							}

							if (CommonStaticClass.CheckCursorValue(c, strFld4)
									.equalsIgnoreCase("1")) {
								((CheckBox) v.findViewById(R.id.chk4))
										.setChecked(true);
							} else if (CommonStaticClass.CheckCursorValue(c,
									strFld4).equalsIgnoreCase("2")) {
								((CheckBox) v.findViewById(R.id.chk4))
										.setChecked(false);
							}

						} else if (CommonStaticClass.CheckCursorValue(c,
								strFld1).equalsIgnoreCase("2")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupRb))
									.findViewById(R.id.Rb2)).setChecked(true);

						}

						/*
						 * String val = mCursor.getString(0);
						 * if(val.equalsIgnoreCase("OccurrenceQuestions")) {
						 * LoadOccurrenceQuestions(v); }
						 */

					} while (c.moveToNext());

				}

			}

		} catch (Exception e) {

			// TODO: handle exception

		}

	}

	private void LoadDatafrmmultiplesingchoiceone(String strField, ViewGroup v) {
		// We choose those question variable who's form name is
		// 'frmSoapPurchaseFreq' in the Database

		// ~~~~~Start~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// For Q516
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		strFld1 = "" + qName + "";
		strFld2 = "" + qName + "a1";
		strFld3 = "" + qName + "a2";
		strFld4 = "" + qName + "a3";

		strFld1 = "" + qName + "";
		strFld2 = "" + qName + "Bitten";
		strFld3 = "" + qName + "Scratched";
		strFld4 = "" + qName + "Wound";
		strFld5 = "" + qName + "Bleeding";
		strFld6 = "" + qName + "Name";
		String strSQL = "";
		if (qName.equalsIgnoreCase("q6_8")) {
			strSQL = "Select "
					+ strFld1
					+ ", "
					+ strFld2
					+ ", "
					+ strFld3
					+ ", "
					+ strFld4
					+ ", "
					+ strFld5
					+ " , "
					+ strFld6
					+ "   from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " where dataid = '" + CommonStaticClass.dataId + "' ";

		} else
			strSQL = "Select "
					+ strFld1
					+ ", "
					+ strFld2
					+ ", "
					+ strFld3
					+ ", "
					+ strFld4
					+ ", "
					+ strFld5
					+ "    from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " where dataid = '" + CommonStaticClass.dataId + "' ";

		Cursor mCursor = null;

		try {

			mCursor = dbHelper.getQueryCursor(strSQL);

			if (mCursor.getCount() > 0) {

				if (mCursor.moveToFirst()) {

					do {

						if (mCursor.getString(mCursor.getColumnIndex(strFld1)) == null
								|| mCursor.getString(
										mCursor.getColumnIndex(strFld1))
										.equalsIgnoreCase("")) {
							((RadioGroup) v.findViewById(R.id.radioGroup1))
									.clearCheck();

						}

						else if (mCursor
								.getString(mCursor.getColumnIndex(strFld1))
								.toString().equalsIgnoreCase("1")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroup1))
									.findViewById(R.id.Rb1)).setChecked(true);

							if (mCursor.getString(
									mCursor.getColumnIndex(strFld2))
									.equalsIgnoreCase("1")) {
								((CheckBox) v.findViewById(R.id.chk1))
										.setChecked(true);
							} else if (mCursor.getString(
									mCursor.getColumnIndex(strFld2))
									.equalsIgnoreCase("2")) {
								((CheckBox) v.findViewById(R.id.chk1))
										.setChecked(false);
							}
							if (mCursor.getString(
									mCursor.getColumnIndex(strFld3))
									.equalsIgnoreCase("1")) {
								((CheckBox) v.findViewById(R.id.chk2))
										.setChecked(true);
							} else if (mCursor.getString(
									mCursor.getColumnIndex(strFld3))
									.equalsIgnoreCase("2")) {
								((CheckBox) v.findViewById(R.id.chk2))
										.setChecked(false);
							}
							((EditText) v.findViewById(R.id.txtNumber))
									.setText(mCursor.getString(mCursor
											.getColumnIndex(strFld4)));

							if (mCursor.getString(
									mCursor.getColumnIndex(strFld5))
									.equalsIgnoreCase("1")) {
								((RadioButton) ((RadioGroup) v
										.findViewById(R.id.radioGroup2))
										.findViewById(R.id.Rb3))
										.setChecked(true);

								// Rb3.Checked = true;
							} else if (mCursor.getString(
									mCursor.getColumnIndex(strFld5))
									.equalsIgnoreCase("2")) {
								((RadioButton) ((RadioGroup) v
										.findViewById(R.id.radioGroup2))
										.findViewById(R.id.Rb4))
										.setChecked(true);
							} else {
								((RadioGroup) v.findViewById(R.id.radioGroup2))
										.clearCheck();
							}
							if (qName.equalsIgnoreCase("q6_8")) {
								((EditText) v.findViewById(R.id.txtOther))
										.setText(mCursor.getString(mCursor
												.getColumnIndex(strFld6)));
								/*
								 * txtOther.Text = DR[strFld6].ToString();
								 * 
								 * txtOther.Text = DR[strFld6].ToString();
								 */
							}

						} else if (mCursor.getString(
								mCursor.getColumnIndex(strFld1))
								.equalsIgnoreCase("2")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroup1))
									.findViewById(R.id.Rb2)).setChecked(true);

						}

						/*
						 * String val = mCursor.getString(0);
						 * if(val.equalsIgnoreCase("OccurrenceQuestions")) {
						 * LoadOccurrenceQuestions(v); }
						 */

					} while (mCursor.moveToNext());

				}

			}

		} catch (Exception e) {
			CommonStaticClass
					.showMyAlert(thisactivity, "Error", e.getMessage());
			// TODO: handle exception

		}

	}

	private void LoadQuesType(String strQvar, ViewGroup v) {

		String strSQL = "";
		try {
			strSQL = "Select QType from tblQuestion where Qvar = '"
					+ strQvar.trim() + "'";

			Cursor mCursor = null;

			try {

				mCursor = dbHelper.getQueryCursor(strSQL);

				if (mCursor.getCount() > 0) {

					if (mCursor.moveToFirst()) {

						do {

							String val = mCursor.getString(0);
							if (val.equalsIgnoreCase("OccurrenceQuestions")) {

								LoadOccurrenceQuestions(v);
							}

						} while (mCursor.moveToNext());

					}

				}

			} catch (Exception e) {

				// TODO: handle exception

			}
		} catch (Exception e) {

			// TODO: handle exception

		}

	}

	private void LoadQuesTypefrmmultiplesinglechoicetwo(String strQvar,
			ViewGroup v) {

		String strSQL = "";
		try {
			strSQL = "Select QType from tblQuestion where Qvar = '"
					+ strQvar.trim() + "'";

			Cursor mCursor = null;

			try {

				mCursor = dbHelper.getQueryCursor(strSQL);

				if (mCursor.getCount() > 0) {

					if (mCursor.moveToFirst()) {

						do {

							String val = mCursor.getString(0);
							if (val.equalsIgnoreCase("OccurrenceQuestions")) {

								LoadOccurrenceQuestionsfrmmultiplechoicetwo(v);
							}

						} while (mCursor.moveToNext());

					}

				}

			} catch (Exception e) {

				// TODO: handle exception

			}
		} catch (Exception e) {

			// TODO: handle exception

		}

	}

	private void LoadQuesTypefrmmultiplesingchoiceone(String strQvar,
			ViewGroup v) {

		String strSQL = "";
		try {
			strSQL = "Select QType from tblQuestion where Qvar = '"
					+ strQvar.trim() + "'";

			Cursor mCursor = null;

			try {

				mCursor = dbHelper.getQueryCursor(strSQL);

				if (mCursor.getCount() > 0) {

					if (mCursor.moveToFirst()) {

						do {

							String val = mCursor.getString(0);
							if (val.equalsIgnoreCase("OccurrenceQuestions")) {

								LoadOccurrenceQuestionsfrmmultiplesinglechoiceone(v);
							}

						} while (mCursor.moveToNext());

					}

				}

			} catch (Exception e) {

				// TODO: handle exception

			}
		} catch (Exception e) {

			// TODO: handle exception

		}

	}

	private void LoadOccurrenceQuestionsfrmmultiplechoicetwo(ViewGroup v) {
		if (CommonStaticClass.langBng) // Banglah VERSION
		{
			qqq.setText(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQdescbng());
			((TextView) v.findViewById(R.id.lbl1)).setText("");

			((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroupRb))
					.findViewById(R.id.Rb1)).setText("1 : n¨uv");
			((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroupRb))
					.findViewById(R.id.Rb2)).setText("2 : bv");

		} else if (!CommonStaticClass.langBng) // ENGLISH VERSION
		{
			qqq.setText(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQdesceng());
			((TextView) v.findViewById(R.id.lbl1)).setText("");// lblSubQuestion.Text
																// =
																// ConManager.ReadQuestion(lblQuestion,
																// _strQVar,
																// _intLang);
			((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroupRb))
					.findViewById(R.id.Rb1)).setText("1:Yes");
			((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroupRb))
					.findViewById(R.id.Rb2)).setText("2: No");

		}
		LoadExposurefrmmultiplesinglechoicetwo(v);
		// LoadComboWithData(cboOne);

	}

	private void LoadOccurrenceQuestions(ViewGroup v) {
		if (CommonStaticClass.langBng) // Banglah VERSION
		{
			// qqq.setText("Amy¯’ M…ncvwjZ cïi mv‡_ ms¯ú‡ki« weeiY I Zv‡`i ‡iv‡Mi g~j j¶Yt");
			((TextView) v.findViewById(R.id.lbl1)).setText("");

			((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroupRb))
					.findViewById(R.id.Rb1)).setText("1 : n¨uv");
			((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroupRb))
					.findViewById(R.id.Rb2)).setText("0 : bv");

		} else if (!CommonStaticClass.langBng) // ENGLISH VERSION
		{
			// qqq.setText("Sick Domestic Animal Exposure");
			((TextView) v.findViewById(R.id.lbl1)).setText("");// lblSubQuestion.Text
																// =
																// ConManager.ReadQuestion(lblQuestion,
																// _strQVar,
																// _intLang);
			((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroupRb))
					.findViewById(R.id.Rb1)).setText("1:Yes");
			((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroupRb))
					.findViewById(R.id.Rb2)).setText("0: No");

		}
		LoadExposure(v);
		// LoadComboWithData(cboOne);

	}

	private void LoadOccurrenceQuestionsfrmmultiplesinglechoiceone(ViewGroup v) {
		if (CommonStaticClass.langBng) // Banglah VERSION
		{
			// qqq.setText("Amy¯’ M…ncvwjZ cïi mv‡_ ms¯ú‡ki« weeiY I Zv‡`i ‡iv‡Mi g~j j¶Yt");
			// ((TextView) v.findViewById(R.id.lbl1)).setText("");

			((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroup1))
					.findViewById(R.id.Rb1)).setText("1 : n¨uv");
			((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroup1))
					.findViewById(R.id.Rb2)).setText("2 : bv");

		} else if (!CommonStaticClass.langBng) // ENGLISH VERSION
		{
			// qqq.setText("Sick Domestic Animal Exposure");
			// ((TextView) v.findViewById(R.id.lbl1)).setText("");//
			// lblSubQuestion.Text
			// =
			// ConManager.ReadQuestion(lblQuestion,
			// _strQVar,
			// _intLang);
			((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroup1))
					.findViewById(R.id.Rb1)).setText("1:Yes");
			((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroup1))
					.findViewById(R.id.Rb2)).setText("2: No");

		}
		LoadExposurefrmmultiplesinglechoiceone(v);
		// LoadComboWithData(cboOne);

	}

	private void LoadExposurefrmmultiplesinglechoiceone(ViewGroup v) {
		if (CommonStaticClass.langBng) // Banglah VERSION
		{
			((TextView) v.findViewById(R.id.lblSubQuestion))
					.setText("AvNvZ  wQj?");
			((CheckBox) v.findViewById(R.id.chk1)).setText("Kvgo");
			((CheckBox) v.findViewById(R.id.chk2)).setText("AvuPo");
			((TextView) v.findViewById(R.id.lblSubQuestion))
					.setText("AvNvZ  wQj?");

			((TextView) v.findViewById(R.id.lblBleeding))
					.setText("i³ eÜ n‡Z wK mgm¨v n‡qwQ‡jv?");
			((TextView) v.findViewById(R.id.lbldeep))
					.setText("¶Z KZUv Mfxi wQj?");
			((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroup2))
					.findViewById(R.id.Rb3)).setText("1:n¨vu");
			((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroup2))
					.findViewById(R.id.Rb4)).setText("2: bv");

			((TextView) v.findViewById(R.id.lbldeep))
					.setText("¶Z KZUv Mfxi wQj?");

		} else if (!CommonStaticClass.langBng) // ENGLISH VERSION
		{

			((CheckBox) v.findViewById(R.id.chk1)).setText("Bitten");
			((CheckBox) v.findViewById(R.id.chk2)).setText("Scratched");
			((TextView) v.findViewById(R.id.lblSubQuestion))
					.setText("Exposure?");

			((TextView) v.findViewById(R.id.lblBleeding))
					.setText("Did you have trouble stopping the Bleeding?");
			// ((TextView)v.findViewById(R.id.lbldeep)).setText("¶Z KZUv Mfxi wQj?");
			((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroup2))
					.findViewById(R.id.Rb3)).setText("1:Yes");
			((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroup2))
					.findViewById(R.id.Rb4)).setText("2: No");
			((TextView) v.findViewById(R.id.lbldeep))
					.setText("How deep was the Wound?(cm)");

		}
	}

	private void LoadExposure(ViewGroup v) {
		if (CommonStaticClass.langBng) // Banglah VERSION
		{
			((CheckBox) v.findViewById(R.id.chk1)).setText("Pvgov Qvov‡bv");
			((CheckBox) v.findViewById(R.id.chk2)).setText("gvsk c«¯‘Z Kiv");
			((CheckBox) v.findViewById(R.id.chk3)).setText("LvIqv");
			((CheckBox) v.findViewById(R.id.chk4)).setText("");

			((CheckBox) v.findViewById(R.id.chk4)).setVisibility(View.GONE);// chk4.Visible
																			// =
																			// false;
		} else if (!CommonStaticClass.langBng) // ENGLISH VERSION
		{
			((CheckBox) v.findViewById(R.id.chk1)).setText("Slaughtered");
			((CheckBox) v.findViewById(R.id.chk2)).setText("Prepared");
			((CheckBox) v.findViewById(R.id.chk3)).setText("Consumed");
			((CheckBox) v.findViewById(R.id.chk4)).setText("");
			((CheckBox) v.findViewById(R.id.chk4)).setVisibility(View.GONE);
			// chk4.Visible = false;

		}
	}

	private void LoadExposurefrmmultiplesinglechoicetwo(ViewGroup v) {
		if (CommonStaticClass.langBng) // Banglah VERSION
		{
			((CheckBox) v.findViewById(R.id.chk1)).setText("aiv");
			((CheckBox) v.findViewById(R.id.chk2)).setText("gviv");
			((CheckBox) v.findViewById(R.id.chk3)).setText("gvsk c«¯‘Z Kiv");
			((CheckBox) v.findViewById(R.id.chk4)).setText("LvIqv");

			((CheckBox) v.findViewById(R.id.chk4)).setVisibility(View.GONE);// chk4.Visible
																			// =
																			// false;
		} else if (!CommonStaticClass.langBng) // ENGLISH VERSION
		{
			((CheckBox) v.findViewById(R.id.chk1)).setText("Caught");
			((CheckBox) v.findViewById(R.id.chk2)).setText("Killed");
			((CheckBox) v.findViewById(R.id.chk3)).setText("Prepared");
			((CheckBox) v.findViewById(R.id.chk4)).setText("Consumed");
		}
	}

	private void loadguifrmsymptomsone(ViewGroup v) {

		qqq = (TextView) v.findViewById(R.id.qqq);

		qName = CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar();

		qqq.setText("");
		loadDataFrmSymptomone(v);
		final ViewGroup vg = v;

		saveNxtButton = (Button) v.findViewById(R.id.saveNxtButton);

		saveNxtButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				progressDialog = ProgressDialog.show(con, "Wait",
						"Please wait while processing next question");

				new Thread() {

					public void run() {
						try {
							Looper.prepare();

							if (CheckValidationForFrmSymptomsOne(vg)) {

								UpdateDataFrmSymptomsOne(vg);
								progressDialog.dismiss();
							} else {
								progressDialog.dismiss();
							}

							// else
							/*
							 * CommonStaticClass.showMyAlert(thisactivity,
							 * "Message", "Please fill all fields correctly");
							 */

							// preserveState();

						} catch (Exception lg) {
							progressDialog.dismiss();
							CommonStaticClass
									.showFinalAlert(con,
											"Error occured on save. Please fill all fields");

						} finally {

						}
						Looper.loop();
					}

				}.start();
			}

		});
		clButton = (Button) v.findViewById(R.id.clButton);
		clButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				// resetViewGroup((ViewGroup) v);
			}

		});

		/*
		 * notesButton = (Button) v.findViewById(R.id.btnNote);
		 * notesButton.setOnClickListener(new OnClickListener() {
		 * 
		 * public void onClick(View v) { // TODO Auto-generated method stub
		 * FraNotes();
		 * 
		 * }
		 * 
		 * });
		 */
		prevButton = (Button) v.findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				userPressedPrevious(ParentActivity.this);
			}

		});

	}

	private boolean CheckValidationForFrmSymptomsOne(ViewGroup v) {

		intAge = Integer.parseInt(CommonStaticClass.getSkip("AgeY",
				"tblMainQues", dbHelper));
		intSex = Integer.parseInt(CommonStaticClass.getSkip("Sex",
				"tblMainQues", dbHelper));

		intComp = Integer.parseInt(CommonStaticClass.getSkip("Comp",
				"tblMainQues", dbHelper));

		// Newly Addedd

		if (((RadioGroup) v.findViewById(R.id.chkLabFindings)).getVisibility() == View.VISIBLE) {

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.chkLabFindings))
					.findViewById(R.id.radio0)).isChecked() == true) {

				if (((EditText) v.findViewById(R.id.dtSpecimencol)).getText()
						.toString().length() == 0
						|| ((EditText) v.findViewById(R.id.dtSpecimentime))
								.getText().toString().length() == 0
						|| ((EditText) v.findViewById(R.id.txtWBCcount))
								.getText().toString().length() == 0
						|| ((EditText) v.findViewById(R.id.txtPlateletCount))
								.getText().toString().length() == 0
						|| ((EditText) v.findViewById(R.id.txtHemoglobin))
								.getText().toString().length() == 0) {
					CommonStaticClass.showMyAlert(con, "Message",
							"Fill fields related to Blood specimen collected");
					return false;
				}

			}
		}
		try {
			if (((RadioGroup) v.findViewById(R.id.radioGroupBreath))
					.getVisibility() == View.VISIBLE) {

				if (((RadioButton) ((RadioGroup) v
						.findViewById(R.id.radioGroupBreath))
						.findViewById(R.id.radio0)).isChecked() == true) {

					if (((RadioButton) ((RadioGroup) v
							.findViewById(R.id.radioGroupDBreathSound))
							.findViewById(R.id.radio1)).isChecked() == true

							&& ((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupRhonchi))
									.findViewById(R.id.radio1)).isChecked() == true
							&&

							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupCrepitation))
									.findViewById(R.id.radio1)).isChecked() == true

							&&

							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupRhales))
									.findViewById(R.id.radio1)).isChecked() == true
							&&

							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupStridor))
									.findViewById(R.id.radio1)).isChecked() == true) {
						CommonStaticClass
								.showMyAlert(thisactivity, "Message",
										"Please select any option for Abnormal breath sound");

						// progressDialog.dismiss();
						return false;
					}
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (((RadioGroup) v.findViewById(R.id.radioGroupPneumonia))
				.getVisibility() == View.VISIBLE) {
			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupPneumonia))
					.findViewById(R.id.radio0)).isChecked() == false
					&& (((RadioButton) ((RadioGroup) v
							.findViewById(R.id.radioGroupPneumonia))
							.findViewById(R.id.radio1)).isChecked() == false)
					&& (((RadioButton) ((RadioGroup) v
							.findViewById(R.id.radioGroupPneumonia))
							.findViewById(R.id.radio2)).isChecked() == false)) {
				CommonStaticClass.showMyAlert(thisactivity, "Message",
						"Please select the Pneumonia");
				return false;
			}
		}

		if ((intComp == 1 || intComp == 2) && intAge >= 5) {
			if (((Spinner) v.findViewById(R.id.cboPulseRate)).getVisibility() == View.VISIBLE) {
				if (((Spinner) v.findViewById(R.id.cboPulseRate))
						.getSelectedItem().toString().length() <= 0) {
					CommonStaticClass.showMyAlert(thisactivity, "Message",
							"Please select the pulse rate");
					return false;
				}
			}
		}
		if (intComp == 1 || intComp == 3) {
			if (((RadioGroup) v.findViewById(R.id.radioGroupMedic))
					.getVisibility() == View.VISIBLE) {
				if (((RadioButton) ((RadioGroup) v
						.findViewById(R.id.radioGroupMedic))
						.findViewById(R.id.radio0)).isChecked() == false
						&& (((RadioButton) ((RadioGroup) v
								.findViewById(R.id.radioGroupMedic))
								.findViewById(R.id.radio1)).isChecked() == false)
						&& (((RadioButton) ((RadioGroup) v
								.findViewById(R.id.radioGroupMedic))
								.findViewById(R.id.radio2)).isChecked() == false)) {
					CommonStaticClass.showMyAlert(thisactivity, "Message",
							"Please select Medicine");
					return false;
				}

			}
		}
		// Checking for temperature
		if (((EditText) v.findViewById(R.id.txtTemperature)).getVisibility() == View.VISIBLE) {
			if (((EditText) v.findViewById(R.id.txtTemperature)).getText()
					.toString().length() == 0) {
				CommonStaticClass.showMyAlert(thisactivity, "Message",
						"Please type the temperature information");
				return false;
			}
		}
		// Checking for Respiratory rates
		if (((Spinner) v.findViewById(R.id.cboRespiratoryRate)).getVisibility() == View.VISIBLE) {
			if (((Spinner) v.findViewById(R.id.cboRespiratoryRate))
					.getSelectedItem().toString().length() <= 0) {
				CommonStaticClass.showMyAlert(thisactivity, "Message",
						"Please select the Respiratory rates");
				return false;
			}
		}

		if (((RadioGroup) v.findViewById(R.id.radioGroupStridor))
				.getVisibility() == View.VISIBLE) {
			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupStridor))
					.findViewById(R.id.radio0)).isChecked() == false
					&& (((RadioButton) ((RadioGroup) v
							.findViewById(R.id.radioGroupStridor))
							.findViewById(R.id.radio1)).isChecked() == false)
					&& (((RadioButton) ((RadioGroup) v
							.findViewById(R.id.radioGroupStridor))
							.findViewById(R.id.radio2)).isChecked() == false)) {
				CommonStaticClass.showMyAlert(thisactivity, "Message",
						"Please select the Stroid information.");

				return false;
			}
		}
		/*
		 * if (((RadioGroup)
		 * v.findViewById(R.id.radioGroupStridor)).isSelected() == false) {
		 * CommonStaticClass.showMyAlert(thisactivity, "Message",
		 * "Please select the Stroid information.");
		 * 
		 * return false; }
		 */

		/*
		 * if (((RadioGroup) v.findViewById(R.id.radioGroupCrepitation))
		 * .isSelected() == false) { CommonStaticClass.showMyAlert(thisactivity,
		 * "Message", "Please select the Crepitation information.");
		 * 
		 * return false; }
		 */

		if (((RadioGroup) v.findViewById(R.id.radioGroupCrepitation))
				.getVisibility() == View.VISIBLE) {
			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupCrepitation))
					.findViewById(R.id.radio0)).isChecked() == false
					&& (((RadioButton) ((RadioGroup) v
							.findViewById(R.id.radioGroupCrepitation))
							.findViewById(R.id.radio1)).isChecked() == false)
					&& (((RadioButton) ((RadioGroup) v
							.findViewById(R.id.radioGroupCrepitation))
							.findViewById(R.id.radio2)).isChecked() == false)) {
				CommonStaticClass.showMyAlert(thisactivity, "Message",
						"Please select the Crepitation information.");

				return false;
			}
		}
		if (((Spinner) v.findViewById(R.id.cboMentalStatus)).getVisibility() == View.VISIBLE) {
			if (((Spinner) v.findViewById(R.id.cboMentalStatus))
					.getSelectedItem().toString().length() <= 0) {
				CommonStaticClass.showMyAlert(thisactivity, "Message",
						"Please select Mental Status.");

				return false;

			}
		}
		if (((RadioGroup) v.findViewById(R.id.radioGroupOPD)).getVisibility() == View.VISIBLE) {
			if (((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroupOPD))
					.findViewById(R.id.radio0)).isChecked() == true) {

				/*if (((Spinner) v.findViewById(R.id.cbolistantiBio1))
						.getSelectedItem().toString().length() <= 0) {

					CommonStaticClass.showMyAlert(thisactivity, "Message",
							"Please select Antibiotic-1 information.");
					return false;
				}

				if (((Spinner) v.findViewById(R.id.cbolistantiBio2))
						.getVisibility() == View.VISIBLE) {
					if (((Spinner) v.findViewById(R.id.cbolistantiBio2))
							.getSelectedItem().toString().length() <= 0) {

						CommonStaticClass.showMyAlert(thisactivity, "Message",
								"Please select Antibiotic-2 information.");
						return false;
					}
				}

				if (((Spinner) v.findViewById(R.id.cbolistantiBio3))
						.getVisibility() == View.VISIBLE) {
					if (((Spinner) v.findViewById(R.id.cbolistantiBio3))
							.getSelectedItem().toString().length() <= 0) {

						CommonStaticClass.showMyAlert(thisactivity, "Message",
								"Please select Antibiotic-2 information.");
						return false;
					}
				}*/
				
				if (ant1.getText().toString().length() <= 0) {

					CommonStaticClass.showMyAlert(thisactivity, "Message",
							"Please select Antibiotic-1 information.");
					return false;
				}

				if (ant2.getVisibility() == View.VISIBLE) {
					if (ant2.getText().toString().length() <= 0) {

						CommonStaticClass.showMyAlert(thisactivity, "Message",
								"Please select Antibiotic-2 information.");
						return false;
					}
				}

				if (ant3.getVisibility() == View.VISIBLE) {
					if (ant3.getText().toString().length() <= 0) {

						CommonStaticClass.showMyAlert(thisactivity, "Message",
								"Please select Antibiotic-3 information.");
						return false;
					}
				}
			}
		}

		if (((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroupMedic))
				.findViewById(R.id.radio0)).isChecked() == true) {

		/*	if (((Spinner) v.findViewById(R.id.cboMedicineOne)).getVisibility() == View.VISIBLE) {
				if (((Spinner) v.findViewById(R.id.cboMedicineOne))
						.getSelectedItem().toString().length() <= 0) {

					CommonStaticClass.showMyAlert(thisactivity, "Message",
							"Please select Medicine-1 information.");
					return false;
				}
			}
			if (((Spinner) v.findViewById(R.id.cboMedicineTwo)).getVisibility() == View.VISIBLE) {
				if (((Spinner) v.findViewById(R.id.cboMedicineTwo))
						.getSelectedItem().toString().length() <= 0) {

					CommonStaticClass.showMyAlert(thisactivity, "Message",
							"Please select Medicine-2 information.");
					return false;
				}
			}
			if (((Spinner) v.findViewById(R.id.cboMedicineThree))
					.getVisibility() == View.VISIBLE) {
				if (((Spinner) v.findViewById(R.id.cboMedicineThree))
						.getSelectedItem().toString().length() <= 0) {

					CommonStaticClass.showMyAlert(thisactivity, "Message",
							"Please select Medicine-3 information.");
					return false;
				}
			}*/
			
			if (med1.getVisibility() == View.VISIBLE) {
				if (med1.getText().toString().length() <= 0) {

					CommonStaticClass.showMyAlert(thisactivity, "Message",
							"Please select Medicine-1 information.");
					return false;
				}
			}
			if (med2.getVisibility() == View.VISIBLE) {
				if (med2.getText().toString().length() <= 0) {

					CommonStaticClass.showMyAlert(thisactivity, "Message",
							"Please select Medicine-2 information.");
					return false;
				}
			}
			if (med3.getVisibility() == View.VISIBLE) {
				if (med3.getText().toString().length() <= 0) {

					CommonStaticClass.showMyAlert(thisactivity, "Message",
							"Please select Medicine-3 information.");
					return false;
				}
			}

		}
		/*if (((Spinner) v.findViewById(R.id.cboTreatmentOne)).getVisibility() == View.VISIBLE) {
			if (((Spinner) v.findViewById(R.id.cboTreatmentOne))
					.getSelectedItem().toString().length() <= 0) {

				CommonStaticClass.showMyAlert(thisactivity, "Message",
						"Please select Treatment-1 recieved.");
				return false;
			}
		}
		if (((Spinner) v.findViewById(R.id.cboTreatmentTwo)).getVisibility() == View.VISIBLE) {
			if (((Spinner) v.findViewById(R.id.cboTreatmentTwo))
					.getSelectedItem().toString().length() <= 0) {

				CommonStaticClass.showMyAlert(thisactivity, "Message",
						"Please select Treatment-2 recieved.");
				return false;
			}
		}

		if (((Spinner) v.findViewById(R.id.cboTreatmentThree)).getVisibility() == View.VISIBLE) {
			if (((Spinner) v.findViewById(R.id.cboTreatmentThree))
					.getSelectedItem().toString().length() <= 0) {

				CommonStaticClass.showMyAlert(thisactivity, "Message",
						"Please select Treatment-3 recieved.");
				return false;
			}
		}
		if (((Spinner) v.findViewById(R.id.cboTreatmentFour)).getVisibility() == View.VISIBLE) {
			if (((Spinner) v.findViewById(R.id.cboTreatmentFour))
					.getSelectedItem().toString().length() <= 0) {

				CommonStaticClass.showMyAlert(thisactivity, "Message",
						"Please select Treatment-4 recieved.");
				return false;
			}
		}*/
		
		if (((AutoCompleteTextView) v.findViewById(R.id.autoCompleteTextView1)).getVisibility() == View.VISIBLE) {
			if (((AutoCompleteTextView) v.findViewById(R.id.autoCompleteTextView1))
					.getText().toString().length() <= 0) {

				CommonStaticClass.showMyAlert(thisactivity, "Message",
						"Please select Treatment-1 recieved.");
				return false;
			}
		}
		

		if (((AutoCompleteTextView) v.findViewById(R.id.autoCompleteTextView2)).getVisibility() == View.VISIBLE) {
			if (((AutoCompleteTextView) v.findViewById(R.id.autoCompleteTextView2))
					.getText().toString().length() <= 0) {

				CommonStaticClass.showMyAlert(thisactivity, "Message",
						"Please select Treatment-2 recieved.");
				return false;
			}
		}

		if (((AutoCompleteTextView) v.findViewById(R.id.autoCompleteTextView3)).getVisibility() == View.VISIBLE) {
			if (((AutoCompleteTextView) v.findViewById(R.id.autoCompleteTextView3))
					.getText().toString().length() <= 0) {

				CommonStaticClass.showMyAlert(thisactivity, "Message",
						"Please select Treatment-3 recieved.");
				return false;
			}
		}

		if (((AutoCompleteTextView) v.findViewById(R.id.autoCompleteTextView4)).getVisibility() == View.VISIBLE) {
			if (((AutoCompleteTextView) v.findViewById(R.id.autoCompleteTextView4))
					.getText().toString().length() <= 0) {

				CommonStaticClass.showMyAlert(thisactivity, "Message",
						"Please select Treatment-1 recieved.");
				return false;
			}
		}
		return true;
	}

	private boolean UpdateDataFrmSymptomsOne(ViewGroup v) {
		String strSQL = "";

		try {
			strSQL = "";
			StringBuilder SB = UpdateQryBuilderFrmSymptomsOne(v);

			strSQL = "UPDATE tblMainQues " + SB.toString()
					+ " Where dataid = '" + CommonStaticClass.dataId + "'";
			if (dbHelper.executeDMLQuery(strSQL)) {

				intComp = Integer.parseInt(CommonStaticClass.getSkip("Comp",
						"tblMainQues", dbHelper));
				if (intComp == 1) {
					CommonStaticClass.showMyAlert(con, "Message",
							"You successfully completed HBIS Part!");
					CommonStaticClass.findOutNextSLNo(
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQvar(),
							"sec2");
					CommonStaticClass.nextQuestion(ParentActivity.this);
					return true;
				} else {

					CommonStaticClass.findOutNextSLNo(
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQvar(),
							"END");
					CommonStaticClass.nextQuestion(ParentActivity.this);
				}
			}
			return false;
		}

		catch (Exception e) {
			// MessageBox.Show(ex.ToString(), "Error");
			// throw ex;
			CommonStaticClass.showMyAlert(thisactivity, "Error On Save", e
					.getMessage().toString());

			return false;
		} finally {

		}

	}

	private StringBuilder UpdateQryBuilderFrmSymptomsOne(ViewGroup v) {
		StringBuilder SB = new StringBuilder();
		try {
			// if (SB.Length > 0)
			// {
			// SB.Remove(0, SB.Length);
			// }

			if ((intComp == 1 || intComp == 2) && intAge >= 5) {
				SB.append("set Pulse = "
						+ ((Spinner) v.findViewById(R.id.cboPulseRate))
								.getSelectedItem().toString() + " ");
			} else {
				SB.append("set Pulse  = null ");
			}
			SB.append(",Temp = "
					+ ((EditText) v.findViewById(R.id.txtTemperature))
							.getText().toString().trim() + " ");

			if ((intComp == 3 || intComp == 2) && intAge < 5) {
				SB.append(",heartrate = "
						+ ((Spinner) v.findViewById(R.id.cboHeartRate))
								.getSelectedItem().toString() + " ");
			} else {
				SB.append(",heartrate = null");
			}

			SB.append(",temp_loc =  "
					+ CommonStaticClass.GetSpinnerValue((Spinner) (v
							.findViewById(R.id.cboTempLoc))) + " ");

			SB.append(",time_temp = "
					+ CommonStaticClass.GetSpinnerValue((Spinner) (v
							.findViewById(R.id.cboTempTime))) + " ");

			if ((intComp == 1 || intComp == 2) && intAge >= 5) {
				if (((Spinner) v.findViewById(R.id.cboSystolic))
						.getSelectedItem().toString().length() <= 0) {
					SB.append(", bp_sys = null ");
				} else // if
						// (((Spinner)v.findViewById(R.id.cboSystolic.SelectedIndex
						// > -1)
				{
					SB.append(", bp_sys = "
							+ ((Spinner) v.findViewById(R.id.cboSystolic))
									.getSelectedItem().toString() + " ");
				}
				if (((Spinner) v.findViewById(R.id.cboDiastolic))
						.getSelectedItem().toString().length() <= 0) {
					SB.append(", bp_dias = null ");
				} else // if (cboDiastolic.SelectedIndex > -1)
				{
					SB.append(", bp_dias = "
							+ ((Spinner) v.findViewById(R.id.cboDiastolic))
									.getSelectedItem().toString() + " ");
				}
			} else {
				SB.append(", bp_sys = null ");
				SB.append(", bp_dias = null ");
			}

			SB.append(", res_rate = "
					+ ((Spinner) v.findViewById(R.id.cboRespiratoryRate))
							.getSelectedItem().toString() + " ");

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupCyanosis))
					.findViewById(R.id.radio0)).isChecked() == true) {
				SB.append(", cyanosis = 1 ");
			} else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupCyanosis))
					.findViewById(R.id.radio1)).isChecked() == true) {
				SB.append(", cyanosis = 2 ");
			} else {
				SB.append(", Cyanosis = 0 ");
			}

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupBreath))
					.findViewById(R.id.radio0)).isChecked() == true) {
				SB.append(", breath_sound = 1 ");
			} else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupBreath))
					.findViewById(R.id.radio1)).isChecked() == true) {
				SB.append(", breath_sound = 2 ");
			} else {
				SB.append(", breath_sound = 0 ");
			}

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupRhonchi))
					.findViewById(R.id.radio0)).isChecked() == true) {
				SB.append(", rhonchi = 1 ");
			} else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupRhonchi))
					.findViewById(R.id.radio1)).isChecked() == true) {
				SB.append(", rhonchi = 2 ");
			} else {
				SB.append(", rhonchi = 0 ");
			}

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupCrepitation))
					.findViewById(R.id.radio0)).isChecked() == true) {
				SB.append(", creps = 1 ");
			} else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupCrepitation))
					.findViewById(R.id.radio1)).isChecked() == true) {
				SB.append(", creps = 2 ");
			} else {
				SB.append(", creps = 0 ");
			}

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupRhales))
					.findViewById(R.id.radio0)).isChecked() == true) {
				SB.append(", rhales = 1 ");
			} else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupRhales))
					.findViewById(R.id.radio1)).isChecked() == true) {
				SB.append(", rhales = 2 ");
			} else {
				SB.append(", rhales = 0 ");
			}

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupStridor))
					.findViewById(R.id.radio0)).isChecked() == true) {
				SB.append(", Stridor = 1 ");
			} else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupStridor))
					.findViewById(R.id.radio1)).isChecked() == true) {
				SB.append(", Stridor = 2 ");
			} else {
				SB.append(", Stridor = 0 ");
			}

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDBreathSound))
					.findViewById(R.id.radio0)).isChecked() == true) {
				SB.append(", decrease_breath = 1 ");
			} else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupDBreathSound))
					.findViewById(R.id.radio1)).isChecked() == true) {
				SB.append(", decrease_breath = 2 ");
			} else {
				SB.append(", decrease_breath = 0 ");
			}
			SB.append(", mental = "
					+ CommonStaticClass.GetSpinnerValue(((Spinner) v
							.findViewById(R.id.cboMentalStatus))) + " ");

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.chkLabFindings))
					.findViewById(R.id.radio0)).isChecked() == true) {

				// if (((CheckBox)
				// v.findViewById(R.id.chkLabFindings)).isChecked() == true) {
				SB.append(", SpecimenCollected = 1");

				if (((EditText) v.findViewById(R.id.txtWBCcount)).getText()
						.toString().length() != 0) {
					SB.append(", WBC = "
							+ ((EditText) v.findViewById(R.id.txtWBCcount))
									.getText().toString() + "");
				}
				if (((Spinner) v.findViewById(R.id.cboNeutrophils))
						.getSelectedItem().toString().length() > 0) {
					SB.append(", neutro = "
							+ ((Spinner) v.findViewById(R.id.cboNeutrophils))
									.getSelectedItem().toString() + " ");
				}
				if (((Spinner) v.findViewById(R.id.cboLymphocytes))
						.getSelectedItem().toString().length() > 0) {
					SB.append(", lympho = "
							+ ((Spinner) v.findViewById(R.id.cboLymphocytes))
									.getSelectedItem().toString() + " ");
				}
				if (((EditText) v.findViewById(R.id.txtPlateletCount))
						.getText().toString().length() != 0) {
					SB.append(", Platelet = "
							+ ((EditText) v.findViewById(R.id.txtPlateletCount))
									.getText().toString() + "");
				}
				if (((EditText) v.findViewById(R.id.txtHemoglobin)).getText()
						.toString().length() != 0) {
					SB.append(", hemog = "
							+ ((EditText) v.findViewById(R.id.txtHemoglobin))
									.getText().toString() + "");
				}
				if (((Spinner) v.findViewById(R.id.cboESR)).getSelectedItem()
						.toString().length() > 0) {
					SB.append(", ESR = "
							+ ((Spinner) v.findViewById(R.id.cboESR))
									.getSelectedItem().toString() + " ");
				}
				SB.append(", Date_SpecimenCollected = '"
						+ ((EditText) v.findViewById(R.id.dtSpecimencol))
								.getText().toString() + "'");
				SB.append(", time_SpecimenCollected = '"
						+ ((EditText) v.findViewById(R.id.dtSpecimentime))
								.getText().toString() + "'");
				// SB.append(", time_SpecimenCollected = '" +
				// dtSpecimentime.Value.Date + "' ");

			} else {

				SB.append(", SpecimenCollected = 2");
				SB.append(", WBC = 0");
				SB.append(", neutro = 0 ");
				SB.append(", lympho = 0 ");
				SB.append(", Platelet = 0");
				SB.append(", hemog = 0");
				SB.append(", ESR = 0 ");
				SB.append(", Date_SpecimenCollected = null ");
				SB.append(", time_SpecimenCollected = null ");
			}

			SB.append(", chest_cxr = '"
					+ CommonStaticClass.GetSpinnerValue(((Spinner) v
							.findViewById(R.id.cboChestRadiograph))) + "' ");
			if (CommonStaticClass.GetSpinnerValue(
					((Spinner) v.findViewById(R.id.cboChestRadiograph)))
					.equalsIgnoreCase("99")) {
				SB.append(", cxr_others = '"
						+ ((EditText) v.findViewById(R.id.txtRadiographOther))
								.getText().toString() + "' ");
			} else {
				SB.append(", cxr_others = null ");
			}

			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupPneumonia))
					.findViewById(R.id.radio0)).isChecked() == true) {
				SB.append(", dx_pneumonia = 1");
			} else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupPneumonia))
					.findViewById(R.id.radio1)).isChecked() == true) {
				SB.append(", dx_pneumonia = 2");
			} else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupPneumonia))
					.findViewById(R.id.radio2)).isChecked() == true) {
				SB.append(", dx_pneumonia = 9");
			} else {
				SB.append(", dx_pneumonia = 0");
			}
			
			if (((AutoCompleteTextView) v.findViewById(R.id.autoCompleteTextView1))
					.getVisibility() == View.VISIBLE) {

				if (((AutoCompleteTextView) v.findViewById(R.id.autoCompleteTextView1))
						.getText().toString().length() > 0) {
					SB.append(", treat1 = "
							+ CommonStaticClass.Getcode(text1.getText().toString(),"DrugCodeTreatment",dbHelper));
				} else {
					SB.append(", treat1 = null ");
				}

			}

			else {
				SB.append(", treat1 = null ");
			}
			
			if (((AutoCompleteTextView) v.findViewById(R.id.autoCompleteTextView2))
					.getVisibility() == View.VISIBLE) {

				if (((AutoCompleteTextView) v.findViewById(R.id.autoCompleteTextView2))
						.getText().toString().length() > 0) {
					SB.append(", treat2 = "
							+ CommonStaticClass.Getcode(text2.getText().toString(),"DrugCodeTreatment",dbHelper));
				} else {
					SB.append(", treat2 = null ");
				}

			}

			else {
				SB.append(", treat2 = null ");
			}
			
			if (((AutoCompleteTextView) v.findViewById(R.id.autoCompleteTextView3))
					.getVisibility() == View.VISIBLE) {

				if (((AutoCompleteTextView) v.findViewById(R.id.autoCompleteTextView3))
						.getText().toString().length() > 0) {
					SB.append(", treat3 = "
							+ CommonStaticClass.Getcode(text3.getText().toString(),"DrugCodeTreatment",dbHelper));
				} else {
					SB.append(", treat3 = null ");
				}

			}

			else {
				SB.append(", treat3 = null ");
			}
			
			if (((AutoCompleteTextView) v.findViewById(R.id.autoCompleteTextView4))
					.getVisibility() == View.VISIBLE) {

				if (((AutoCompleteTextView) v.findViewById(R.id.autoCompleteTextView4))
						.getText().toString().length() > 0) {
					SB.append(", treat4 = "
							+ CommonStaticClass.Getcode(text4.getText().toString(),"DrugCodeTreatment",dbHelper));
				} else {
					SB.append(", treat4 = null ");
				}

			}

			else {
				SB.append(", treat4 = null ");
			}

			/*if (((Spinner) v.findViewById(R.id.cboTreatmentTwo))
					.getVisibility() == View.VISIBLE) {

				if (((Spinner) v.findViewById(R.id.cboTreatmentOne))
						.getSelectedItem().toString().length() > 0) {
					SB.append(", treat1 = "
							+ CommonStaticClass.GetSpinnerValue(((Spinner) v
									.findViewById(R.id.cboTreatmentOne))) + " ");
				} else {
					SB.append(", treat1 = null ");
				}

			}

			else {
				SB.append(", treat1 = null ");
			}*/

			/*if (((Spinner) v.findViewById(R.id.cboTreatmentTwo))
					.getVisibility() == View.VISIBLE) {

				if (((Spinner) v.findViewById(R.id.cboTreatmentTwo))
						.getSelectedItem().toString().length() > 0) {
					SB.append(", treat2 = "
							+ CommonStaticClass.GetSpinnerValue(((Spinner) v
									.findViewById(R.id.cboTreatmentTwo))) + " ");
				} else {
					SB.append(", treat2 = null ");
				}
			} else {
				SB.append(", treat2 = null ");
			}

			if (((Spinner) v.findViewById(R.id.cboTreatmentThree))
					.getVisibility() == View.VISIBLE) {

				if (((Spinner) v.findViewById(R.id.cboTreatmentThree))
						.getSelectedItem().toString().length() > 0)

				{
					SB.append(", treat3 = "
							+ CommonStaticClass.GetSpinnerValue(((Spinner) v
									.findViewById(R.id.cboTreatmentThree)))
							+ " ");
				} else {
					SB.append(", treat3 = null ");
				}
			}

			else {
				SB.append(", treat3 = null ");
			}

			if (((Spinner) v.findViewById(R.id.cboTreatmentFour))
					.getVisibility() == View.VISIBLE) {
				if (((Spinner) v.findViewById(R.id.cboTreatmentFour))
						.getSelectedItem().toString().length() > 0) {
					SB.append(", treat4 = "
							+ CommonStaticClass.GetSpinnerValue(((Spinner) v
									.findViewById(R.id.cboTreatmentFour)))
							+ " ");

				} else {
					SB.append(", treat4 = null ");
				}

			} else {
				SB.append(", treat4 = null ");
			}*/

			// if (((CheckBox) v.findViewById(R.id.chkOthersTreatment))
			// .isChecked() == true) {
			SB.append(", treatOthers = '"
					+ ((EditText) v.findViewById(R.id.txtTreatmentOthers))
							.getText().toString() + "'");
			// else {
			// SB.append(", treatOthers = null ");
			// }
			if (intComp == 2) {
				SB.append(", medicine_24hrs = null ");
				// SB.append(", name_med24hrs = null ");
				SB.append(", antibiot_med24hrs = null ");
				SB.append(", ps_abx = null ");
				// SB.append(", list_antibiotics =null ");
				SB.append(", oxygen_received = null ");
				SB.append(", icu = null ");

			} else {
				if (((RadioButton) ((RadioGroup) v
						.findViewById(R.id.radioGroupMedic))
						.findViewById(R.id.radio0)).isChecked() == true) {
					SB.append(", medicine_24hrs = '1' ");
					/*if (((Spinner) v.findViewById(R.id.cboMedicineOne))
							.getSelectedItem().toString().length() > 0) {
						SB.append(", med24hrs1 = '"
								+ CommonStaticClass.GetSpinnerValue((Spinner) v
										.findViewById(R.id.cboMedicineOne))
								+ "' ");
					} else {
						SB.append(", med24hrs1 = '' ");
					}
					if (((Spinner) v.findViewById(R.id.cboMedicineTwo))
							.getSelectedItem().toString().length() > 0) {
						SB.append(", med24hrs2 = '"
								+ CommonStaticClass.GetSpinnerValue((Spinner) v
										.findViewById(R.id.cboMedicineTwo))
								+ "' ");
					} else {
						SB.append(", med24hrs2 = null ");
					}
					if (((Spinner) v.findViewById(R.id.cboMedicineThree))
							.getSelectedItem().toString().length() > 0) {
						SB.append(", med24hrs3 = '"
								+ CommonStaticClass.GetSpinnerValue((Spinner) v
										.findViewById(R.id.cboMedicineThree))
								+ "' ");
					} else {
						SB.append(", med24hrs3 = null ");
					}*/
					if (med1.getText().toString().length() > 0) {
						SB.append(", med24hrs1 = '"
								+ CommonStaticClass.Getcode(med1.getText().toString(),"DrugCode",dbHelper)
								+ "' ");
					} else {
						SB.append(", med24hrs1 = '' ");
					}
					if (med2.getText().toString().length() > 0) {
						SB.append(", med24hrs2 = '"
								+CommonStaticClass.Getcode(med2.getText().toString(),"DrugCode",dbHelper)
								+ "' ");
					} else {
						SB.append(", med24hrs2 = null ");
					}
					if (med3.getText().toString().length() > 0) {
						SB.append(", med24hrs3 = '"
								+ CommonStaticClass.Getcode(med3.getText().toString(),"DrugCode",dbHelper)
								+ "' ");
					} else {
						SB.append(", med24hrs3 = null ");
					}
					
					if (((EditText) v.findViewById(R.id.txtMedicineOthers))
							.getText().toString().length() > 0) {
						SB.append(", med24hrs4 = '"
								+ ((EditText) v
										.findViewById(R.id.txtMedicineOthers))
										.getText().toString() + "' ");
					} else {
						SB.append(", med24hrs4 = null ");

					}

				} else if (((RadioButton) ((RadioGroup) v
						.findViewById(R.id.radioGroupMedic))
						.findViewById(R.id.radio1)).isChecked() == true) {
					SB.append(", medicine_24hrs = 2 ");
					SB.append(", med24hrs1 = null ");
					SB.append(", med24hrs2 = null ");
					SB.append(", med24hrs3 = null ");
					SB.append(", med24hrs4 = null ");
				} else if (((RadioButton) ((RadioGroup) v
						.findViewById(R.id.radioGroupMedic))
						.findViewById(R.id.radio2)).isChecked() == true) {
					SB.append(", medicine_24hrs = 9 ");
					SB.append(", med24hrs1 = null ");
					SB.append(", med24hrs2 = null ");
					SB.append(", med24hrs3 = null ");
					SB.append(", med24hrs4 = null ");
				}

				if (((RadioButton) ((RadioGroup) v
						.findViewById(R.id.radioGroupIsAntiobiotic))
						.findViewById(R.id.radio0)).isChecked() == true) {
					SB.append(", antibiot_med24hrs = '1' ");
				} else if (((RadioButton) ((RadioGroup) v
						.findViewById(R.id.radioGroupIsAntiobiotic))
						.findViewById(R.id.radio1)).isChecked() == true) {
					SB.append(", antibiot_med24hrs = '2' ");
				} else {
					SB.append(", antibiot_med24hrs = null ");
				}

				if (((RadioButton) ((RadioGroup) v
						.findViewById(R.id.radioGroupOPD))
						.findViewById(R.id.radio0)).isChecked() == true) {
					SB.append(", ps_abx = '1' ");

					/*if (((Spinner) v.findViewById(R.id.cbolistantiBio1))
							.getSelectedItem().toString().length() <= 0) {
						SB.append(", list_antibiotics1 =null ");
					} else {
						SB.append(", list_antibiotics1 = '"
								+ CommonStaticClass.GetSpinnerValue((Spinner) v
										.findViewById(R.id.cbolistantiBio1))
								+ "' ");
					}

					if (((Spinner) v.findViewById(R.id.cbolistantiBio2))
							.getSelectedItem().toString().length() <= 0) {

						SB.append(", list_antibiotics2 =null ");
					} else {
						SB.append(", list_antibiotics2 = '"
								+ CommonStaticClass.GetSpinnerValue((Spinner) v
										.findViewById(R.id.cbolistantiBio2))
								+ "' ");
					}
					if (((Spinner) v.findViewById(R.id.cbolistantiBio3))
							.getSelectedItem().toString().length() <= 0)
					// if (cbolistantiBio3.SelectedIndex == -1)
					{
						SB.append(", list_antibiotics3 =null ");
					} else {
						SB.append(", list_antibiotics3 = '"
								+ CommonStaticClass.GetSpinnerValue((Spinner) v
										.findViewById(R.id.cbolistantiBio3))
								+ "' ");
					}*/
					
					if (ant1.getText().toString().length() <= 0) {
						SB.append(", list_antibiotics1 =null ");
					} else {
						SB.append(", list_antibiotics1 = '"
								+ CommonStaticClass.Getcode(ant1.getText().toString(),"tblAntioBioticDrug",dbHelper)
								+ "' ");
					}

					if (ant2.getText().toString().length() <= 0) {

						SB.append(", list_antibiotics2 =null ");
					} else {
						SB.append(", list_antibiotics2 = '"
								+CommonStaticClass.Getcode(ant2.getText().toString(),"tblAntioBioticDrug",dbHelper)
								+ "' ");
					}
					if (ant3.getText().toString().length() <= 0)
					// if (cbolistantiBio3.SelectedIndex == -1)
					{
						SB.append(", list_antibiotics3 =null ");
					} else {
						SB.append(", list_antibiotics3 = '"
								+ CommonStaticClass.Getcode(ant3.getText().toString(),"tblAntioBioticDrug",dbHelper)
								+ "' ");
					}

					SB.append(", Date_administration = '"
							+ ((EditText) v.findViewById(R.id.dtpAdmin))
									.getText().toString() + "' ");
					SB.append(", Time_administration = '"
							+ ((EditText) v.findViewById(R.id.dtpAdminTime))
									.getText().toString() + "' ");
					// SB.append(", Time_administration = '" +
					// dtpAdminTime.Value.Date + "' ");

				} else if (((RadioButton) ((RadioGroup) v
						.findViewById(R.id.radioGroupOPD))
						.findViewById(R.id.radio1)).isChecked() == true) {
					SB.append(", ps_abx = 2 ");
				} else {
					SB.append(", ps_abx = null ");
				}

				if (((RadioButton) ((RadioGroup) v
						.findViewById(R.id.radioGroupMechVenti))
						.findViewById(R.id.radio0)).isChecked() == true)

				// if (optY.Checked == true)
				{
					SB.append(", Isventilation = 1 ");
					SB.append(", NoVentilatorDays = "
							+ ((EditText) v
									.findViewById(R.id.txtNoofVentilator))
									.getText().toString() + "");

				} else if (((RadioButton) ((RadioGroup) v
						.findViewById(R.id.radioGroupMechVenti))
						.findViewById(R.id.radio1)).isChecked() == true) {
					SB.append(", Isventilation = 2 ");
					SB.append(", NoVentilatorDays =null");
				} else {
					SB.append(", Isventilation = null ");
				}

				if (((RadioButton) ((RadioGroup) v
						.findViewById(R.id.radioGroupOxyRecv))
						.findViewById(R.id.radio0)).isChecked() == true)

				// if (optYes.Checked == true)
				{
					SB.append(", oxygen_received = 1 ");
				} else if (((RadioButton) ((RadioGroup) v
						.findViewById(R.id.radioGroupOxyRecv))
						.findViewById(R.id.radio1)).isChecked() == true)
				// if (optOxyRecvNo.Checked == true)
				{
					SB.append(", oxygen_received = 2 ");
				} else if (((RadioButton) ((RadioGroup) v
						.findViewById(R.id.radioGroupOxyRecv))
						.findViewById(R.id.radio2)).isChecked() == true)

				// if (optOxyRecvDk.Checked == true)
				{
					SB.append(", oxygen_received = 9 ");
				} else {
					SB.append(", oxygen_received = null ");
				}

				if (((RadioButton) ((RadioGroup) v
						.findViewById(R.id.radioGroupICU))
						.findViewById(R.id.radio0)).isChecked() == true)

				// if (optICUYes.Checked == true)
				{
					SB.append(", icu = 1 ");
				} else if (((RadioButton) ((RadioGroup) v
						.findViewById(R.id.radioGroupICU))
						.findViewById(R.id.radio1)).isChecked() == true)// (optICUNo.Checked
																		// ==
																		// true)
				{
					SB.append(", icu = 2 ");
				} else if (((RadioButton) ((RadioGroup) v
						.findViewById(R.id.radioGroupICU))
						.findViewById(R.id.radio2)).isChecked() == true) {
					SB.append(", icu = 9 ");
				} else {
					SB.append(", icu = null ");
				}

			}

			SB.append(", Comments = '"
					+ ((EditText) v.findViewById(R.id.txtComments)).getText()
							.toString() + "' ");
			SB.append(", EditBy = '" + CommonStaticClass.userSpecificId + "' ");
			SB.append(", EditDate = '" + CommonStaticClass.GetDate() + "' ");

		} catch (Exception exp) {
			CommonStaticClass.showMyAlert(thisactivity, "Error",
					"Error in Update String Building");
		} finally {
		}
		return SB;

	}

	private void loadDataFrmSymptomone(final ViewGroup v) {
		
		

		try {
			pickDate = ((EditText) v.findViewById(R.id.dtSpecimencol));

			pickTime = ((EditText) v.findViewById(R.id.dtSpecimentime));

			pickDate.setOnTouchListener(new View.OnTouchListener() {

				public boolean onTouch(View v, MotionEvent event) {
					final Calendar c = Calendar.getInstance();
					dateYear = c.get(Calendar.YEAR);
					dateMonth = c.get(Calendar.MONTH);
					dateDay = c.get(Calendar.DAY_OF_MONTH);

					showDialog(DATE_DIALOG);
					return false;
				}
			});

			pickTime.setOnTouchListener(new View.OnTouchListener() {

				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					showDialog(TIME_DIALOG);
					return false;
				}
			});
			 

			CommonStaticClass.ComboAddRangeNumber(thisactivity,
					(((Spinner) v.findViewById(R.id.cboPulseRate))), 30, 160);

			ArrayList<String> ids = new ArrayList<String>();
			CommonStaticClass.ComboAddRangeNumber(thisactivity,
					((Spinner) v.findViewById(R.id.cboHeartRate)), 30, 160);

			CommonStaticClass.ComboAddRangeNumber(thisactivity,
					((Spinner) v.findViewById(R.id.cboDiastolic)), 0, 120, 5);

			CommonStaticClass.ComboAddRangeNumber(thisactivity,
					((Spinner) v.findViewById(R.id.cboSystolic)), 60, 220, 5);

			CommonStaticClass.ComboAddNumber(thisactivity,
					((Spinner) v.findViewById(R.id.cboRespiratoryRate)), 99);

			CommonStaticClass.ComboAddRangeNumber(thisactivity,
					((Spinner) v.findViewById(R.id.cboNeutrophils)), 10, 95);

			CommonStaticClass.ComboAddNumber(thisactivity,
					((Spinner) v.findViewById(R.id.cboLymphocytes)), 80);

			CommonStaticClass.ComboAddNumber(thisactivity,
					((Spinner) v.findViewById(R.id.cboESR)), 199);

			String sql = "SELECT (" + "" + "XCode" + "" + "|| " + "" + "' : '"
					+ " || " + "Name" + ") AS " + "D"
					+ " from XRayCode ORDER BY XCode" + "";

			CommonStaticClass.FillCombo(thisactivity, dbHelper, sql,
					((Spinner) v.findViewById(R.id.cboChestRadiograph)));

			sql = "SELECT '' AS Name UNION SELECT (" + "" + "DrCode" + ""
					+ "|| " + "" + "' : '" + " || " + "Name" + ") AS " + "D"
					+ " from DrugCodeTreatment ORDER BY Name" + "";

			/*CommonStaticClass.FillCombo(thisactivity, dbHelper, sql,
					((Spinner) v.findViewById(R.id.cboTreatmentOne)));

			CommonStaticClass.FillCombo(thisactivity, dbHelper, sql,
					((Spinner) v.findViewById(R.id.cboTreatmentTwo)));

			CommonStaticClass.FillCombo(thisactivity, dbHelper, sql,
					((Spinner) v.findViewById(R.id.cboTreatmentThree)));

			CommonStaticClass.FillCombo(thisactivity, dbHelper, sql,
					((Spinner) v.findViewById(R.id.cboTreatmentFour)));*/
			
			
			
			
			
			
			String [] medList = null;
			Cursor mCursor = null;
			String sql1 = "Select name from DrugCodeTreatment order by name collate nocase asc";	
			try {

				mCursor = dbHelper.getQueryCursor(sql1);

				if (mCursor.getCount() > 0) {
					medList = new String[mCursor.getCount()];
					int i = 0;
					if (mCursor.moveToFirst()) {

						do {
							medList[i] = mCursor.getString(0);
							i++;
//							ids.add(mCursor.getString(0));

						} while (mCursor.moveToNext());

						/*ArrayAdapter adapterSl = new ArrayAdapter(act,
								android.R.layout.simple_spinner_dropdown_item, ids);
						adapterSl
								.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						spnr.setAdapter(adapterSl);*/

					}

				}

			} catch (Exception e) {

				// TODO: handle exception

			}
			finally{
				if(mCursor != null)
					mCursor.close();
			}
			
			String [] medList2 = null;
			sql1 = "Select name from DrugCode order by name collate nocase asc";	
			try {

				mCursor = dbHelper.getQueryCursor(sql1);

				if (mCursor.getCount() > 0) {
					medList2 = new String[mCursor.getCount()];
					int i = 0;
					if (mCursor.moveToFirst()) {

						do {
							medList2[i] = mCursor.getString(0);
							i++;
//							ids.add(mCursor.getString(0));

						} while (mCursor.moveToNext());

						/*ArrayAdapter adapterSl = new ArrayAdapter(act,
								android.R.layout.simple_spinner_dropdown_item, ids);
						adapterSl
								.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						spnr.setAdapter(adapterSl);*/

					}

				}

			} catch (Exception e) {

				// TODO: handle exception

			}
			finally{
				if(mCursor != null)
					mCursor.close();
			}
			
			String [] medList3 = null;
			sql1 = "Select name from tblAntioBioticDrug order by name collate nocase asc";	
			try {

				mCursor = dbHelper.getQueryCursor(sql1);

				if (mCursor.getCount() > 0) {
					medList3 = new String[mCursor.getCount()];
					int i = 0;
					if (mCursor.moveToFirst()) {

						do {
							medList3[i] = mCursor.getString(0);
							i++;
//							ids.add(mCursor.getString(0));

						} while (mCursor.moveToNext());

						/*ArrayAdapter adapterSl = new ArrayAdapter(act,
								android.R.layout.simple_spinner_dropdown_item, ids);
						adapterSl
								.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						spnr.setAdapter(adapterSl);*/

					}

				}

			} catch (Exception e) {

				// TODO: handle exception

			}
			finally{
				if(mCursor != null)
					mCursor.close();
			}
			
			text1=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
			text2=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView2);
			text3=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView3);
			text4=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView4);
			med1=(AutoCompleteTextView)findViewById(R.id.autoCompleteMed1);
			med2=(AutoCompleteTextView)findViewById(R.id.autoCompleteMed2);
			med3=(AutoCompleteTextView)findViewById(R.id.autoCompleteMed3);
			ant1 = (AutoCompleteTextView)findViewById(R.id.autoCompleteAnt1);
			ant2 = (AutoCompleteTextView)findViewById(R.id.autoCompleteAnt2);
			ant3 = (AutoCompleteTextView)findViewById(R.id.autoCompleteAnt3);
			
			ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,medList);
		      
		    text1.setAdapter(adapter);
		    text1.setThreshold(1);
		    text2.setAdapter(adapter);
		    text2.setThreshold(1);
		    text3.setAdapter(adapter);
		    text3.setThreshold(1);
		    text4.setAdapter(adapter);
		    text4.setThreshold(1);
		    
		    adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,medList2);
		    med1.setAdapter(adapter);
		    med1.setThreshold(1);
		    med2.setAdapter(adapter);
		    med2.setThreshold(1);
		    med3.setAdapter(adapter);
		    med3.setThreshold(1);
		    
		    adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,medList3);
		    ant1.setAdapter(adapter);
		    ant1.setThreshold(1);
		    ant2.setAdapter(adapter);
		    ant2.setThreshold(1);
		    ant3.setAdapter(adapter);
		    ant3.setThreshold(1);
		    
		   
		    text1.setOnItemClickListener(new OnItemClickListener() {

		        @Override
		        public void onItemClick(AdapterView<?> parent, View arg1, int pos,
		                long id) {
		        	String val = text1.getText().toString();
					if (val.length() > 0) {
						if (val.contains("Others")) {

							((EditText) v
									.findViewById(R.id.txtTreatmentOthers))
									.setVisibility(View.VISIBLE);
							((TextView) v
									.findViewById(R.id.lblOthersTreatment))
									.setVisibility(View.VISIBLE);

						}

						else if (val.contains("Treatment not give")) {

							text2.setText("Treatment not give");
							text3.setText("Treatment not give");
							text4.setText("Treatment not give");
							/*CommonStaticClass.SetSpinnerValue(
									thisactivity,
									((Spinner) v
											.findViewById(R.id.cboTreatmentTwo)),
									"99");
							CommonStaticClass.SetSpinnerValue(
									thisactivity,
									((Spinner) v
											.findViewById(R.id.cboTreatmentThree)),
									"99");
							CommonStaticClass.SetSpinnerValue(
									thisactivity,
									((Spinner) v
											.findViewById(R.id.cboTreatmentFour)),
									"99");*/
						}

						else {
							((TextView) v
									.findViewById(R.id.lbltreatment2))
									.setVisibility(View.VISIBLE);
							/*((Spinner) v
									.findViewById(R.id.cboTreatmentTwo))
									.setVisibility(View.VISIBLE);*/
							text2.setVisibility(View.VISIBLE);

							((EditText) v
									.findViewById(R.id.txtTreatmentOthers))
									.setVisibility(View.GONE);
							((TextView) v
									.findViewById(R.id.lblOthersTreatment))
									.setVisibility(View.GONE);

						}

					} else {
						((TextView) v.findViewById(R.id.lbltreatment2))
								.setVisibility(View.GONE);
						/*((Spinner) v.findViewById(R.id.cboTreatmentTwo))
								.setVisibility(View.GONE);*/
						text2.setVisibility(View.GONE);

						((EditText) v
								.findViewById(R.id.txtTreatmentOthers))
								.setVisibility(View.GONE);
						((TextView) v
								.findViewById(R.id.lblOthersTreatment))
								.setVisibility(View.GONE);

					}
		        	
		              
		        }
		    });
		    
		    
		    text2.setOnItemClickListener(new OnItemClickListener() {

		        @Override
		        public void onItemClick(AdapterView<?> parent, View arg1, int pos,
		                long id) {
		        	String val = text2.getText().toString();
					if (val.length() > 0) {
						if (val.contains("Others")) {

							((EditText) v
									.findViewById(R.id.txtTreatmentOthers))
									.setVisibility(View.VISIBLE);
							((TextView) v
									.findViewById(R.id.lblOthersTreatment))
									.setVisibility(View.VISIBLE);

						}

						else if (val.contains("Treatment not give")) {

//							text2.setText("Treatment not give");
							text3.setText("Treatment not give");
							text4.setText("Treatment not give");
							/*CommonStaticClass.SetSpinnerValue(
									thisactivity,
									((Spinner) v
											.findViewById(R.id.cboTreatmentTwo)),
									"99");
							CommonStaticClass.SetSpinnerValue(
									thisactivity,
									((Spinner) v
											.findViewById(R.id.cboTreatmentThree)),
									"99");
							CommonStaticClass.SetSpinnerValue(
									thisactivity,
									((Spinner) v
											.findViewById(R.id.cboTreatmentFour)),
									"99");*/
						}

						else {
							((TextView) v
									.findViewById(R.id.lbltreatment3))
									.setVisibility(View.VISIBLE);
							/*((Spinner) v
									.findViewById(R.id.cboTreatmentTwo))
									.setVisibility(View.VISIBLE);*/
							text3.setVisibility(View.VISIBLE);

							((EditText) v
									.findViewById(R.id.txtTreatmentOthers))
									.setVisibility(View.GONE);
							((TextView) v
									.findViewById(R.id.lblOthersTreatment))
									.setVisibility(View.GONE);

						}

					} else {
						((TextView) v.findViewById(R.id.lbltreatment3))
								.setVisibility(View.GONE);
						/*((Spinner) v.findViewById(R.id.cboTreatmentTwo))
								.setVisibility(View.GONE);*/
						text3.setVisibility(View.GONE);

						((EditText) v
								.findViewById(R.id.txtTreatmentOthers))
								.setVisibility(View.GONE);
						((TextView) v
								.findViewById(R.id.lblOthersTreatment))
								.setVisibility(View.GONE);

					}
		        	
		              
		        }
		    });
		    
		    text3.setOnItemClickListener(new OnItemClickListener() {

		        @Override
		        public void onItemClick(AdapterView<?> parent, View arg1, int pos,
		                long id) {
		        	String val = text3.getText().toString();
					if (val.length() > 0) {
						if (val.contains("Others")) {

							((EditText) v
									.findViewById(R.id.txtTreatmentOthers))
									.setVisibility(View.VISIBLE);
							((TextView) v
									.findViewById(R.id.lblOthersTreatment))
									.setVisibility(View.VISIBLE);

						}

						else if (val.contains("Treatment not give")) {

//							text2.setText("Treatment not give");
//							text3.setText("Treatment not give");
							text4.setText("Treatment not give");
							/*CommonStaticClass.SetSpinnerValue(
									thisactivity,
									((Spinner) v
											.findViewById(R.id.cboTreatmentTwo)),
									"99");
							CommonStaticClass.SetSpinnerValue(
									thisactivity,
									((Spinner) v
											.findViewById(R.id.cboTreatmentThree)),
									"99");
							CommonStaticClass.SetSpinnerValue(
									thisactivity,
									((Spinner) v
											.findViewById(R.id.cboTreatmentFour)),
									"99");*/
						}

						else {
							((TextView) v
									.findViewById(R.id.lbltreatment4))
									.setVisibility(View.VISIBLE);
							/*((Spinner) v
									.findViewById(R.id.cboTreatmentTwo))
									.setVisibility(View.VISIBLE);*/
							text4.setVisibility(View.VISIBLE);

							((EditText) v
									.findViewById(R.id.txtTreatmentOthers))
									.setVisibility(View.GONE);
							((TextView) v
									.findViewById(R.id.lblOthersTreatment))
									.setVisibility(View.GONE);

						}

					} else {
						((TextView) v.findViewById(R.id.lbltreatment4))
								.setVisibility(View.GONE);
						/*((Spinner) v.findViewById(R.id.cboTreatmentTwo))
								.setVisibility(View.GONE);*/
						text4.setVisibility(View.GONE);

						((EditText) v
								.findViewById(R.id.txtTreatmentOthers))
								.setVisibility(View.GONE);
						((TextView) v
								.findViewById(R.id.lblOthersTreatment))
								.setVisibility(View.GONE);

					}
		        	
		              
		        }
		    });
		    
		    
		    text4.setOnItemClickListener(new OnItemClickListener() {

		        @Override
		        public void onItemClick(AdapterView<?> parent, View arg1, int pos,
		                long id) {
		        	String val = text4.getText().toString();
					if (val.length() > 0) {
						if (val.contains("Others")) {

							((EditText) v
									.findViewById(R.id.txtTreatmentOthers))
									.setVisibility(View.VISIBLE);
							((TextView) v
									.findViewById(R.id.lblOthersTreatment))
									.setVisibility(View.VISIBLE);

						}

						

						else {
							((TextView) v
									.findViewById(R.id.lbltreatment4))
									.setVisibility(View.VISIBLE);
							/*((Spinner) v
									.findViewById(R.id.cboTreatmentTwo))
									.setVisibility(View.VISIBLE);*/
							text4.setVisibility(View.VISIBLE);

							((EditText) v
									.findViewById(R.id.txtTreatmentOthers))
									.setVisibility(View.GONE);
							((TextView) v
									.findViewById(R.id.lblOthersTreatment))
									.setVisibility(View.GONE);

						}

					} else {
						

						((EditText) v
								.findViewById(R.id.txtTreatmentOthers))
								.setVisibility(View.GONE);
						((TextView) v
								.findViewById(R.id.lblOthersTreatment))
								.setVisibility(View.GONE);

					}
		        	
		              
		        }
		    });
		    
		    
		    

			/*((Spinner) v.findViewById(R.id.cboTreatmentOne))
					.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> parent,
								View arg1, int arg2, long arg3) {

							// String val =
							// CommonStaticClass.GetSpinnerValue(((Spinner)
							// v.findViewById(R.id.cboTreatmentOne)))
							String val = ((Spinner) v
									.findViewById(R.id.cboTreatmentOne))
									.getSelectedItem().toString().trim();
							if (val.length() > 0) {
								if (val.contains("95")) {

									((EditText) v
											.findViewById(R.id.txtTreatmentOthers))
											.setVisibility(View.VISIBLE);
									((TextView) v
											.findViewById(R.id.lblOthersTreatment))
											.setVisibility(View.VISIBLE);

								}

								else if (val.contains("99")) {

									CommonStaticClass.SetSpinnerValue(
											thisactivity,
											((Spinner) v
													.findViewById(R.id.cboTreatmentTwo)),
											"99");
									CommonStaticClass.SetSpinnerValue(
											thisactivity,
											((Spinner) v
													.findViewById(R.id.cboTreatmentThree)),
											"99");
									CommonStaticClass.SetSpinnerValue(
											thisactivity,
											((Spinner) v
													.findViewById(R.id.cboTreatmentFour)),
											"99");
								}

								else {
									((TextView) v
											.findViewById(R.id.lbltreatment2))
											.setVisibility(View.VISIBLE);
									((Spinner) v
											.findViewById(R.id.cboTreatmentTwo))
											.setVisibility(View.VISIBLE);

									((EditText) v
											.findViewById(R.id.txtTreatmentOthers))
											.setVisibility(View.GONE);
									((TextView) v
											.findViewById(R.id.lblOthersTreatment))
											.setVisibility(View.GONE);

								}

							} else {
								((TextView) v.findViewById(R.id.lbltreatment2))
										.setVisibility(View.GONE);
								((Spinner) v.findViewById(R.id.cboTreatmentTwo))
										.setVisibility(View.GONE);

								((EditText) v
										.findViewById(R.id.txtTreatmentOthers))
										.setVisibility(View.GONE);
								((TextView) v
										.findViewById(R.id.lblOthersTreatment))
										.setVisibility(View.GONE);

							}

						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
							// TODO Auto-generated method stub

						}
					});

			((Spinner) v.findViewById(R.id.cboTreatmentTwo))
					.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> parent,
								View arg1, int arg2, long arg3) {

							String val = ((Spinner) v
									.findViewById(R.id.cboTreatmentTwo))
									.getSelectedItem().toString().trim();
							if (val.length() > 0) {
								if (val.contains("95")) {

									((EditText) v
											.findViewById(R.id.txtTreatmentOthers))
											.setVisibility(View.VISIBLE);
									((TextView) v
											.findViewById(R.id.lblOthersTreatment))
											.setVisibility(View.VISIBLE);

								}

								else if (val.contains("99")) {

									CommonStaticClass.SetSpinnerValue(
											thisactivity,
											((Spinner) v
													.findViewById(R.id.cboTreatmentThree)),
											"99");
									CommonStaticClass.SetSpinnerValue(
											thisactivity,
											((Spinner) v
													.findViewById(R.id.cboTreatmentFour)),
											"99");
								}

								else {
									((TextView) v
											.findViewById(R.id.lbltreatment3))
											.setVisibility(View.VISIBLE);
									((Spinner) v
											.findViewById(R.id.cboTreatmentThree))
											.setVisibility(View.VISIBLE);

									((EditText) v
											.findViewById(R.id.txtTreatmentOthers))
											.setVisibility(View.GONE);
									((TextView) v
											.findViewById(R.id.lblOthersTreatment))
											.setVisibility(View.GONE);

								}
							} else {
								((TextView) v.findViewById(R.id.lbltreatment3))
										.setVisibility(View.GONE);
								((Spinner) v
										.findViewById(R.id.cboTreatmentThree))
										.setVisibility(View.GONE);

								((EditText) v
										.findViewById(R.id.txtTreatmentOthers))
										.setVisibility(View.GONE);
								((TextView) v
										.findViewById(R.id.lblOthersTreatment))
										.setVisibility(View.GONE);

							}

						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
							// TODO Auto-generated method stub

						}
					});

			((Spinner) v.findViewById(R.id.cboTreatmentThree))
					.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> parent,
								View arg1, int arg2, long arg3) {

							String val = ((Spinner) v
									.findViewById(R.id.cboTreatmentThree))
									.getSelectedItem().toString().trim();
							if (val.length() > 0) {
								if (val.contains("95")) {

									((EditText) v
											.findViewById(R.id.txtTreatmentOthers))
											.setVisibility(View.VISIBLE);
									((TextView) v
											.findViewById(R.id.lblOthersTreatment))
											.setVisibility(View.VISIBLE);

								} else if (val.contains("99")) {
									CommonStaticClass.SetSpinnerValue(
											thisactivity,
											((Spinner) v
													.findViewById(R.id.cboTreatmentFour)),
											"99");
								}

								else {
									((TextView) v
											.findViewById(R.id.lbltreatment4))
											.setVisibility(View.VISIBLE);
									((Spinner) v
											.findViewById(R.id.cboTreatmentFour))
											.setVisibility(View.VISIBLE);

									((EditText) v
											.findViewById(R.id.txtTreatmentOthers))
											.setVisibility(View.GONE);
									((TextView) v
											.findViewById(R.id.lblOthersTreatment))
											.setVisibility(View.GONE);

								}
							} else {
								((TextView) v.findViewById(R.id.lbltreatment4))
										.setVisibility(View.GONE);
								((Spinner) v
										.findViewById(R.id.cboTreatmentFour))
										.setVisibility(View.GONE);

								((EditText) v
										.findViewById(R.id.txtTreatmentOthers))
										.setVisibility(View.GONE);
								((TextView) v
										.findViewById(R.id.lblOthersTreatment))
										.setVisibility(View.GONE);

							}

						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
							// TODO Auto-generated method stub

						}
					});

			((Spinner) v.findViewById(R.id.cboTreatmentFour))
					.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> parent,
								View arg1, int arg2, long arg3) {

							String val = ((Spinner) v
									.findViewById(R.id.cboTreatmentFour))
									.getSelectedItem().toString().trim();
							if (val.length() > 0) {
								if (val.contains("95")) {

									((EditText) v
											.findViewById(R.id.txtTreatmentOthers))
											.setVisibility(View.VISIBLE);
									((TextView) v
											.findViewById(R.id.lblOthersTreatment))
											.setVisibility(View.VISIBLE);

								} else {

									((EditText) v
											.findViewById(R.id.txtTreatmentOthers))
											.setVisibility(View.GONE);
									((TextView) v
											.findViewById(R.id.lblOthersTreatment))
											.setVisibility(View.GONE);

								}
							} else {

								((EditText) v
										.findViewById(R.id.txtTreatmentOthers))
										.setVisibility(View.GONE);
								((TextView) v
										.findViewById(R.id.lblOthersTreatment))
										.setVisibility(View.GONE);

							}

						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
							// TODO Auto-generated method stub

						}
					});

			((Spinner) v.findViewById(R.id.cboTreatmentFour))
					.setVisibility(View.GONE);
			((Spinner) v.findViewById(R.id.cboTreatmentThree))
					.setVisibility(View.GONE);
			((Spinner) v.findViewById(R.id.cboTreatmentTwo))
					.setVisibility(View.GONE);*/
		    text2.setVisibility(View.GONE);
		    text3.setVisibility(View.GONE);
		    text4.setVisibility(View.GONE);
		    ((EditText) v
					.findViewById(R.id.txtTreatmentOthers))
					.setVisibility(View.GONE);
			((TextView) v
					.findViewById(R.id.lblOthersTreatment))
					.setVisibility(View.GONE);
		    
			((TextView) v.findViewById(R.id.lbltreatment2))
					.setVisibility(View.GONE);
			((TextView) v.findViewById(R.id.lbltreatment3))
					.setVisibility(View.GONE);
			((TextView) v.findViewById(R.id.lbltreatment4))
					.setVisibility(View.GONE);

			sql = "SELECT '' AS D from DrugCode UNION SELECT (" + "" + "DrCode"
					+ "" + "|| " + "" + "' : '" + " || " + "Name" + ") AS "
					+ "D" + " from DrugCode ORDER BY D" + "";

			/*
			 * CommonStaticClass.FillAutoCompleteTextView(thisactivity,
			 * dbHelper, sql, ((AutoCompleteTextView)
			 * v.findViewById(R.id.txtsearchmedicineone)));
			 */
			
			med1.setOnItemClickListener(new OnItemClickListener() {

		        @Override
		        public void onItemClick(AdapterView<?> parent, View arg1, int pos,
		                long id) {
		        	String val = med1.getText().toString();
		        	if (val.length() > 0) {
						med2.setVisibility(View.VISIBLE);
						CheckMedicineIsAnAntibiotic(v);

					} else {
						med2.setVisibility(View.GONE);
					}

					if (val.equalsIgnoreCase("Treatment not give")
							|| val.equalsIgnoreCase("Unknow")) {

						med2.setVisibility(View.GONE);

					}

					if (val.equalsIgnoreCase("Others")) {

						((EditText) v
								.findViewById(R.id.txtMedicineOthers))
								.setVisibility(View.VISIBLE);
						((TextView) v
								.findViewById(R.id.lblOtherMedicien))
								.setVisibility(View.VISIBLE);

						((TextView) v.findViewById(R.id.lblmedicine2))
								.setVisibility(View.GONE);
						med2.setVisibility(View.GONE);

					} else {
						/*((TextView) v.findViewById(R.id.lblmedicine2))
								.setVisibility(View.VISIBLE);
						
						 * ((Spinner)
						 * v.findViewById(R.id.cboMedicineTwo))
						 * .setVisibility(View.VISIBLE);*/
						 

						((EditText) v
								.findViewById(R.id.txtMedicineOthers))
								.setVisibility(View.GONE);
						((TextView) v
								.findViewById(R.id.lblOtherMedicien))
								.setVisibility(View.GONE);

					}
		        	
		              
		        }
		    });
			
			med2.setOnItemClickListener(new OnItemClickListener() {

		        @Override
		        public void onItemClick(AdapterView<?> parent, View arg1, int pos,
		                long id) {
		        	String val = med2.getText().toString();
		        	if (val.length() > 0) {
						med3.setVisibility(View.VISIBLE);
						CheckMedicineIsAnAntibiotic(v);

					} else {
						med3.setVisibility(View.GONE);
					}

					if (val.equalsIgnoreCase("Treatment not give")
							|| val.equalsIgnoreCase("Unknow")) {

						med3.setVisibility(View.GONE);

					}

					if (val.equalsIgnoreCase("Others")) {

						((EditText) v
								.findViewById(R.id.txtMedicineOthers))
								.setVisibility(View.VISIBLE);
						((TextView) v
								.findViewById(R.id.lblOtherMedicien))
								.setVisibility(View.VISIBLE);

						((TextView) v.findViewById(R.id.lblmedicine2))
								.setVisibility(View.GONE);
						med3.setVisibility(View.GONE);

					} else {
						/*((TextView) v.findViewById(R.id.lblmedicine2))
								.setVisibility(View.VISIBLE);
						
						 * ((Spinner)
						 * v.findViewById(R.id.cboMedicineTwo))
						 * .setVisibility(View.VISIBLE);*/
						 

						((EditText) v
								.findViewById(R.id.txtMedicineOthers))
								.setVisibility(View.GONE);
						((TextView) v
								.findViewById(R.id.lblOtherMedicien))
								.setVisibility(View.GONE);

					}
		        	
		              
		        }
		    });
			
			med3.setOnItemClickListener(new OnItemClickListener() {

		        @Override
		        public void onItemClick(AdapterView<?> parent, View arg1, int pos,
		                long id) {
		        	String val = med3.getText().toString();
		        	
					if (val.equalsIgnoreCase("Others")) {

						((EditText) v
								.findViewById(R.id.txtMedicineOthers))
								.setVisibility(View.VISIBLE);
						((TextView) v
								.findViewById(R.id.lblOtherMedicien))
								.setVisibility(View.VISIBLE);

						((TextView) v.findViewById(R.id.lblmedicine2))
								.setVisibility(View.GONE);
					

					} else {
						/*((TextView) v.findViewById(R.id.lblmedicine2))
								.setVisibility(View.VISIBLE);
						
						 * ((Spinner)
						 * v.findViewById(R.id.cboMedicineTwo))
						 * .setVisibility(View.VISIBLE);*/
						 

						((EditText) v
								.findViewById(R.id.txtMedicineOthers))
								.setVisibility(View.GONE);
						((TextView) v
								.findViewById(R.id.lblOtherMedicien))
								.setVisibility(View.GONE);

					}
		        	
		              
		        }
		    });

			/*CommonStaticClass.FillCombo(thisactivity, dbHelper, sql,
					((Spinner) v.findViewById(R.id.cboMedicineOne)));

			((Spinner) v.findViewById(R.id.cboMedicineOne))
					.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> parent,
								View arg1, int arg2, long arg3) {

							if (((Spinner) v.findViewById(R.id.cboMedicineOne))
									.getSelectedItem().toString().length() == 0)
								return;

							String val = CommonStaticClass
									.GetSpinnerValue(
											((Spinner) v
													.findViewById(R.id.cboMedicineOne)))
									.trim();

							if (val.length() > 0) {
								((Spinner) v.findViewById(R.id.cboMedicineTwo))
										.setVisibility(View.VISIBLE);
								CheckMedicineIsAnAntibiotic(v);

							} else {
								((Spinner) v.findViewById(R.id.cboMedicineTwo))
										.setVisibility(View.GONE);
							}

							if (val.equalsIgnoreCase("99")
									|| val.equalsIgnoreCase("888")) {

								((Spinner) v.findViewById(R.id.cboMedicineTwo))
										.setVisibility(View.GONE);

							}

							if (val.equalsIgnoreCase("95")) {

								((EditText) v
										.findViewById(R.id.txtMedicineOthers))
										.setVisibility(View.VISIBLE);
								((TextView) v
										.findViewById(R.id.lblOtherMedicien))
										.setVisibility(View.VISIBLE);

								((TextView) v.findViewById(R.id.lblmedicine2))
										.setVisibility(View.GONE);
								((Spinner) v.findViewById(R.id.cboMedicineTwo))
										.setVisibility(View.GONE);

							} else {
								((TextView) v.findViewById(R.id.lblmedicine2))
										.setVisibility(View.VISIBLE);
								
								 * ((Spinner)
								 * v.findViewById(R.id.cboMedicineTwo))
								 * .setVisibility(View.VISIBLE);
								 

								((EditText) v
										.findViewById(R.id.txtMedicineOthers))
										.setVisibility(View.GONE);
								((TextView) v
										.findViewById(R.id.lblOtherMedicien))
										.setVisibility(View.GONE);

							}

						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
							// TODO Auto-generated method stub

						}
					});

			CommonStaticClass.FillCombo(thisactivity, dbHelper, sql,
					((Spinner) v.findViewById(R.id.cboMedicineTwo)));
			CommonStaticClass.FillCombo(thisactivity, dbHelper, sql,
					((Spinner) v.findViewById(R.id.cboMedicineThree)));

			((Spinner) v.findViewById(R.id.cboMedicineTwo))
					.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> parent,
								View arg1, int arg2, long arg3) {

							if (((Spinner) v.findViewById(R.id.cboMedicineTwo))
									.getSelectedItem().toString().length() == 0)
								return;

							String val = CommonStaticClass
									.GetSpinnerValue(
											((Spinner) v
													.findViewById(R.id.cboMedicineTwo)))
									.trim();

							if (val.length() > 0) {
								((Spinner) v
										.findViewById(R.id.cboMedicineThree))
										.setVisibility(View.VISIBLE);
								CheckMedicineIsAnAntibiotic(v);

								
								 * if (Integer.parseInt(val) < 59) {
								 * ((RadioButton) ((RadioGroup) v
								 * .findViewById(R.id.radioGroupIsAntiobiotic))
								 * .findViewById(R.id.radio0))
								 * .setChecked(true); } else { ((RadioButton)
								 * ((RadioGroup) v
								 * .findViewById(R.id.radioGroupIsAntiobiotic))
								 * .findViewById(R.id.radio1))
								 * .setChecked(true); }
								 
							} else {
								((Spinner) v
										.findViewById(R.id.cboMedicineThree))
										.setVisibility(View.GONE);
							}

							if (val.equalsIgnoreCase("99")
									|| val.equalsIgnoreCase("888")) {

								((Spinner) v
										.findViewById(R.id.cboMedicineThree))
										.setVisibility(View.GONE);

							}

							if (val.equalsIgnoreCase("95")) {

								((TextView) v.findViewById(R.id.lblmedicine3))
										.setVisibility(View.GONE);
								((Spinner) v
										.findViewById(R.id.cboMedicineThree))
										.setVisibility(View.GONE);

								((EditText) v
										.findViewById(R.id.txtMedicineOthers))
										.setVisibility(View.VISIBLE);
								((TextView) v
										.findViewById(R.id.lblOtherMedicien))
										.setVisibility(View.VISIBLE);

							} else {
								((TextView) v.findViewById(R.id.lblmedicine3))
										.setVisibility(View.VISIBLE);
								
								 * ((Spinner) v
								 * .findViewById(R.id.cboMedicineThree))
								 * .setVisibility(View.VISIBLE);
								 

								((EditText) v
										.findViewById(R.id.txtMedicineOthers))
										.setVisibility(View.GONE);
								((TextView) v
										.findViewById(R.id.lblOtherMedicien))
										.setVisibility(View.GONE);

							}

						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
							// TODO Auto-generated method stub

						}
					});

			((Spinner) v.findViewById(R.id.cboMedicineThree))
					.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> parent,
								View arg1, int arg2, long arg3) {

							if (((Spinner) v
									.findViewById(R.id.cboMedicineThree))
									.getSelectedItem().toString().length() == 0)
								return;

							String val = CommonStaticClass
									.GetSpinnerValue(
											((Spinner) v
													.findViewById(R.id.cboMedicineThree)))
									.trim();

							if (Integer.parseInt(val) < 59) {
								CheckMedicineIsAnAntibiotic(v);
								
								 * ((RadioButton) ((RadioGroup) v
								 * .findViewById(R.id.radioGroupIsAntiobiotic))
								 * .findViewById(R.id.radio0))
								 * .setChecked(true); } else { ((RadioButton)
								 * ((RadioGroup) v
								 * .findViewById(R.id.radioGroupIsAntiobiotic))
								 * .findViewById(R.id.radio1))
								 * .setChecked(true); }
								 

								
								 * if (val.equalsIgnoreCase("99") ||
								 * val.equalsIgnoreCase("888")) {
								 * 
								 * ((Spinner)
								 * v.findViewById(R.id.cboMedicineThree))
								 * .setVisibility(View.GONE);
								 * 
								 * }
								 
							}
							if (val.equalsIgnoreCase("95")) {

								((EditText) v
										.findViewById(R.id.txtMedicineOthers))
										.setVisibility(View.VISIBLE);
								((TextView) v
										.findViewById(R.id.lblOtherMedicien))
										.setVisibility(View.VISIBLE);

							} else {

								((EditText) v
										.findViewById(R.id.txtMedicineOthers))
										.setVisibility(View.GONE);
								((TextView) v
										.findViewById(R.id.lblOtherMedicien))
										.setVisibility(View.GONE);

							}

						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
							// TODO Auto-generated method stub

						}
					});*/

			/*((Spinner) v.findViewById(R.id.cboMedicineOne))
					.setVisibility(View.GONE);
			((Spinner) v.findViewById(R.id.cboMedicineTwo))
					.setVisibility(View.GONE);
			((Spinner) v.findViewById(R.id.cboMedicineThree))
					.setVisibility(View.GONE);*/
			med1.setVisibility(View.GONE);
			med2.setVisibility(View.GONE);
			med3.setVisibility(View.GONE);
			((TextView) v.findViewById(R.id.lblOtherMedicien))
			.setVisibility(View.VISIBLE);

			sql = "SELECT '' AS Name UNION SELECT (" + "" + "DrCode" + ""
					+ "|| " + "" + "' : '" + " || " + "Name" + ") AS " + "D"
					+ " from tblAntioBioticDrug ORDER BY Name" + "";

			/*CommonStaticClass.FillCombo(thisactivity, dbHelper, sql,
					((Spinner) v.findViewById(R.id.cbolistantiBio1)));
			CommonStaticClass.FillCombo(thisactivity, dbHelper, sql,
					((Spinner) v.findViewById(R.id.cbolistantiBio2)));
			CommonStaticClass.FillCombo(thisactivity, dbHelper, sql,
					((Spinner) v.findViewById(R.id.cbolistantiBio3)));*/

			ids = new ArrayList<String>();
			ids.add("");
			ids.add("1 : on admission");
			ids.add("2 : on enrollment");

			CommonStaticClass.FillCombo(thisactivity, ids,
					(Spinner) v.findViewById(R.id.cboTempTime));
			ids = new ArrayList<String>();
			ids.add("");
			ids.add("1 : Oral");
			ids.add("2 : Tympanic/earbased");
			ids.add("3 : Axillary");
			ids.add("4 : Unknown");
			CommonStaticClass.FillCombo(thisactivity, ids,
					(Spinner) v.findViewById(R.id.cboTempLoc));

			ids = new ArrayList<String>();
			ids.add("");
			ids.add("1 : Normal");
			ids.add("2 : Irritable/Less active");
			ids.add("3 : Lethargic");
			ids.add("4 : Obtunded/Comatose");
			CommonStaticClass.FillCombo(thisactivity, ids,
					(Spinner) v.findViewById(R.id.cboMentalStatus));

			ids = new ArrayList<String>();
			ids.add("0 : Not done");
			ids.add("1 : Normal");
			ids.add("2 : Alveolar infiltrate");
			ids.add("3 : Interstitial infiltrate");
			ids.add("4 : Lobar consolidation");
			ids.add("5 : Nodular shadow");
			ids.add("6 : Cavitation");
			ids.add("7 : Pleural effusion");
			ids.add("8 : Pneumothorax");
			ids.add("9 : Hyperinflation");
			ids.add("10 : Patchy opacities");
			ids.add("99 : Others");

			/*
			 * CommonStaticClass.FillCombo(thisactivity, ids, (Spinner)
			 * v.findViewById(R.id.cbolistantiBio1));
			 * CommonStaticClass.FillCombo(thisactivity, ids, (Spinner)
			 * v.findViewById(R.id.cbolistantiBio2));
			 * CommonStaticClass.FillCombo(thisactivity, ids, (Spinner)
			 * v.findViewById(R.id.cbolistantiBio3));
			 */
			/*((Spinner) v.findViewById(R.id.cbolistantiBio1))
					.setVisibility(View.GONE);
			((Spinner) v.findViewById(R.id.cbolistantiBio2))
					.setVisibility(View.GONE);
			((Spinner) v.findViewById(R.id.cbolistantiBio3))
					.setVisibility(View.GONE);*/
			ant1.setVisibility(View.GONE);
			ant2.setVisibility(View.GONE);
			ant3.setVisibility(View.GONE);
			final ViewGroup vg = v;

			ant1.setOnItemClickListener(new OnItemClickListener() {

		        @Override
		        public void onItemClick(AdapterView<?> parent, View arg1, int pos,
		                long id) {
		        	String val = ant1.getText().toString();
		        	if (val.length() > 0) {
						ant2.setVisibility(View.VISIBLE);
						

					} else {
						ant2.setVisibility(View.GONE);
					}

					if (val.equalsIgnoreCase("Treatment not give")
							) {

						ant2.setVisibility(View.GONE);

					}

						        	
		              
		        }
		    });
			
			ant2.setOnItemClickListener(new OnItemClickListener() {

		        @Override
		        public void onItemClick(AdapterView<?> parent, View arg1, int pos,
		                long id) {
		        	String val = ant2.getText().toString();
		        	if (val.length() > 0) {
						ant3.setVisibility(View.VISIBLE);
						

					} else {
						ant3.setVisibility(View.GONE);
					}

					if (val.equalsIgnoreCase("Treatment not give")
							) {

						ant3.setVisibility(View.GONE);

					}

						        	
		              
		        }
		    });
			/*((Spinner) v.findViewById(R.id.cbolistantiBio1))
					.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> arg0,
								View arg1, int arg2, long arg3) {
							if (((Spinner) v.findViewById(R.id.cbolistantiBio1))
									.getSelectedItem().toString().length() > 0) {
								((Spinner) v.findViewById(R.id.cbolistantiBio2))
										.setVisibility(View.VISIBLE);

								if (CommonStaticClass
										.GetSpinnerValue(
												((Spinner) v
														.findViewById(R.id.cbolistantiBio1)))
										.equalsIgnoreCase("99")) {
									((Spinner) v
											.findViewById(R.id.cbolistantiBio2))
											.setVisibility(View.GONE);
								}
							} else {
								((Spinner) v.findViewById(R.id.cbolistantiBio2))
										.setVisibility(View.GONE);
							}

						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
							// TODO Auto-generated method stub

						}

					});*/

			/*((Spinner) v.findViewById(R.id.cbolistantiBio2))
					.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> arg0,
								View arg1, int arg2, long arg3) {
							if (((Spinner) v.findViewById(R.id.cbolistantiBio2))
									.getSelectedItem().toString().length() > 0) {
								((Spinner) v.findViewById(R.id.cbolistantiBio3))
										.setVisibility(View.VISIBLE);
								if (CommonStaticClass
										.GetSpinnerValue(
												((Spinner) v
														.findViewById(R.id.cbolistantiBio2)))
										.equalsIgnoreCase("99")) {
									((Spinner) v
											.findViewById(R.id.cbolistantiBio3))
											.setVisibility(View.GONE);
								}
							} else {
								((Spinner) v.findViewById(R.id.cbolistantiBio3))
										.setVisibility(View.GONE);
							}

						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
							// TODO Auto-generated method stub

						}

					});*/
			((RadioGroup) v.findViewById(R.id.radioGroupOPD))
					.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(RadioGroup group,
								int checkedId) {
							/*if (((RadioButton) (group.findViewById(R.id.radio0)))
									.isChecked()) {
								((Spinner) v.findViewById(R.id.cbolistantiBio1))
										.setVisibility(View.VISIBLE);

							} else {
								((Spinner) v.findViewById(R.id.cbolistantiBio1))
										.setVisibility(View.GONE);
								((Spinner) v.findViewById(R.id.cbolistantiBio2))
										.setVisibility(View.GONE);
								((Spinner) v.findViewById(R.id.cbolistantiBio3))
										.setVisibility(View.GONE);
							}*/
							
							if (((RadioButton) (group.findViewById(R.id.radio0)))
									.isChecked()) {
								ant1.setVisibility(View.VISIBLE);

							} else {
								ant1.setVisibility(View.GONE);
								ant2.setVisibility(View.GONE);
								ant3.setVisibility(View.GONE);
							}

						}
					});

			((RadioGroup) v.findViewById(R.id.chkLabFindings))
					.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(RadioGroup group,
								int checkedId) {
							// TODO Auto-generated method stub

							if (((RadioButton) (group.findViewById(R.id.radio0)))
									.isChecked()) {

								/*
								 * ((Spinner)v.findViewById(R.id.cbolistantiBio1)
								 * ).setVisibility(View.VISIBLE);
								 * ((Spinner)v.findViewById
								 * (R.id.cbolistantiBio2)
								 * ).setVisibility(View.GONE);
								 * ((Spinner)v.findViewById
								 * (R.id.cbolistantiBio3)
								 * ).setVisibility(View.GONE);
								 */
								((EditText) vg.findViewById(R.id.dtSpecimencol))
										.setVisibility(View.VISIBLE);
								((EditText) vg
										.findViewById(R.id.dtSpecimentime))
										.setVisibility(View.VISIBLE);

								((EditText) vg.findViewById(R.id.dtSpecimencol))
										.setVisibility(View.VISIBLE);
								((EditText) vg
										.findViewById(R.id.dtSpecimentime))
										.setVisibility(View.VISIBLE);
								((EditText) vg.findViewById(R.id.txtWBCcount))
										.setVisibility(View.VISIBLE);
								((TextView) vg.findViewById(R.id.lblWbcCount))
										.setVisibility(View.VISIBLE);
								((Spinner) vg.findViewById(R.id.cboNeutrophils))
										.setVisibility(View.VISIBLE);
								((Spinner) vg.findViewById(R.id.cboLymphocytes))
										.setVisibility(View.VISIBLE);
								((EditText) vg
										.findViewById(R.id.txtPlateletCount))
										.setVisibility(View.VISIBLE);
								((EditText) vg.findViewById(R.id.txtHemoglobin))
										.setVisibility(View.VISIBLE);
								((Spinner) vg.findViewById(R.id.cboESR))
										.setVisibility(View.VISIBLE);

								((TextView) vg.findViewById(R.id.lblnutrophils))
										.setVisibility(View.VISIBLE);
								((TextView) vg
										.findViewById(R.id.lblLymphocytes))
										.setVisibility(View.VISIBLE);
								((TextView) vg.findViewById(R.id.lblPlatelet))
										.setVisibility(View.VISIBLE);
								((TextView) vg.findViewById(R.id.lblHemoglobin))
										.setVisibility(View.VISIBLE);
								((TextView) vg.findViewById(R.id.lblesr))
										.setVisibility(View.VISIBLE);

							} else {
								/*
								 * ((Spinner)v.findViewById(R.id.cbolistantiBio1)
								 * ).setVisibility(View.GONE);
								 * ((Spinner)v.findViewById
								 * (R.id.cbolistantiBio2)
								 * ).setVisibility(View.GONE);
								 * ((Spinner)v.findViewById
								 * (R.id.cbolistantiBio3)
								 * ).setVisibility(View.GONE);
								 */
								((EditText) vg.findViewById(R.id.dtSpecimencol))
										.setVisibility(View.GONE);
								((EditText) vg
										.findViewById(R.id.dtSpecimentime))
										.setVisibility(View.GONE);

								((EditText) vg.findViewById(R.id.dtSpecimencol))
										.setVisibility(View.GONE);
								((EditText) vg
										.findViewById(R.id.dtSpecimentime))
										.setVisibility(View.GONE);
								((EditText) vg.findViewById(R.id.txtWBCcount))
										.setVisibility(View.GONE);
								((TextView) vg.findViewById(R.id.lblWbcCount))
										.setVisibility(View.GONE);
								((Spinner) vg.findViewById(R.id.cboNeutrophils))
										.setVisibility(View.GONE);
								((Spinner) vg.findViewById(R.id.cboLymphocytes))
										.setVisibility(View.GONE);
								((EditText) vg
										.findViewById(R.id.txtPlateletCount))
										.setVisibility(View.GONE);
								((EditText) vg.findViewById(R.id.txtHemoglobin))
										.setVisibility(View.GONE);
								((Spinner) vg.findViewById(R.id.cboESR))
										.setVisibility(View.GONE);

								((TextView) vg.findViewById(R.id.lblnutrophils))
										.setVisibility(View.GONE);
								((TextView) vg
										.findViewById(R.id.lblLymphocytes))
										.setVisibility(View.GONE);
								((TextView) vg.findViewById(R.id.lblPlatelet))
										.setVisibility(View.GONE);
								((TextView) vg.findViewById(R.id.lblHemoglobin))
										.setVisibility(View.GONE);
								((TextView) vg.findViewById(R.id.lblesr))
										.setVisibility(View.GONE);

							}
						}

					});

			((EditText) v.findViewById(R.id.txtWBCcount))
					.addTextChangedListener(new TextWatcher() {

						@Override
						public void onTextChanged(CharSequence s, int start,
								int before, int count) {
							// TODO Auto-generated method stub

						}

						@Override
						public void beforeTextChanged(CharSequence s,
								int start, int count, int after) {
							// TODO Auto-generated method stub

						}

						@Override
						public void afterTextChanged(Editable s) {

							Integer intWBCCount = 0;
							try {
								if (s.toString().length() <= 0) {
									return;
								}

								intWBCCount = Integer.parseInt(s.toString());
								if (intWBCCount < 1000 || intWBCCount > 1000000) {
									DisplayToast(
											thisactivity,
											"WBC count should be between 1000 to 1000000",
											1);
									// ((EditText)vg.findViewById(R.id.txtWBCcount)).
									return;
								}
								// MyInput.Enabled = false;
							} catch (Exception ex) {
								DisplayToast(thisactivity,
										"Error in WBC Count Lost Focus.",
										SHORT_TOAST);
							} finally {
							}

						}
					});

			((EditText) v.findViewById(R.id.txtPlateletCount))
					.addTextChangedListener(new TextWatcher() {

						@Override
						public void onTextChanged(CharSequence s, int start,
								int before, int count) {
							// TODO Auto-generated method stub

						}

						@Override
						public void beforeTextChanged(CharSequence s,
								int start, int count, int after) {
							// TODO Auto-generated method stub

						}

						@Override
						public void afterTextChanged(Editable s) {

							Integer intPlateletCount = 0;
							try {
								if (s.toString().length() <= 0) {
									return;
								}
								intPlateletCount = Integer.parseInt(s
										.toString());
								if (intPlateletCount < 15000
										|| intPlateletCount > 1000000) {
									DisplayToast(
											thisactivity,
											"Platelet count should be between 15000 to 1000000",
											1);
									// txtPlateletCount.Focus();
									return;
								}
								// MyInput.Enabled = false;
							}

							catch (Exception ex) {
								DisplayToast(thisactivity,
										"Error in Platelet Count Lost Focus.",
										SHORT_TOAST);
							} finally {
							}

						}
					});

			((EditText) v.findViewById(R.id.txtHemoglobin))
					.addTextChangedListener(new TextWatcher() {

						@Override
						public void onTextChanged(CharSequence s, int start,
								int before, int count) {
							// TODO Auto-generated method stub

						}

						@Override
						public void beforeTextChanged(CharSequence s,
								int start, int count, int after) {
							// TODO Auto-generated method stub

						}

						@Override
						public void afterTextChanged(Editable s) {

							double dblHemoglobin = 0;
							try {
								if (s.length() <= 0)
									return;

								dblHemoglobin = Double.parseDouble(s.toString());
								if (dblHemoglobin < 3.0 || dblHemoglobin > 19.9) {
									DisplayToast(
											thisactivity,
											"Hemoglobin should be between 3.0 and 19.9",
											SHORT_TOAST);

									return;
								}
								// MyInput.Enabled = false;
							} catch (Exception ex) {
								DisplayToast(thisactivity,
										"Error in Hemoglobin Lost Focus.",
										SHORT_TOAST);

							} finally {
							}
						}
					});

			((RadioGroup) v.findViewById(R.id.radioGroupBreath))
					.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(RadioGroup group,
								int checkedId) {

							if (((RadioButton) (group.findViewById(R.id.radio1)))
									.isChecked()) {

								((RadioGroup) vg
										.findViewById(R.id.radioGroupRhonchi))
										.clearCheck();
								((RadioGroup) vg
										.findViewById(R.id.radioGroupCrepitation))
										.clearCheck();
								((RadioGroup) vg
										.findViewById(R.id.radioGroupRhales))
										.clearCheck();
								((RadioGroup) vg
										.findViewById(R.id.radioGroupDBreathSound))
										.clearCheck();
								((RadioGroup) vg
										.findViewById(R.id.radioGroupStridor))
										.clearCheck();

								((RadioGroup) vg
										.findViewById(R.id.radioGroupRhonchi))
										.setVisibility(View.GONE);
								((RadioGroup) vg
										.findViewById(R.id.radioGroupCrepitation))
										.setVisibility(View.GONE);
								((RadioGroup) vg
										.findViewById(R.id.radioGroupRhales))
										.setVisibility(View.GONE);
								((RadioGroup) vg
										.findViewById(R.id.radioGroupDBreathSound))
										.setVisibility(View.GONE);
								((RadioGroup) vg
										.findViewById(R.id.radioGroupStridor))
										.setVisibility(View.GONE);

								((TextView) vg.findViewById(R.id.lbldecreased))
										.setVisibility(View.GONE);
								((TextView) vg.findViewById(R.id.lblrhonchi))
										.setVisibility(View.GONE);
								((TextView) vg
										.findViewById(R.id.lblcrepitation))
										.setVisibility(View.GONE);
								((TextView) vg.findViewById(R.id.lblrhales))
										.setVisibility(View.GONE);
								((TextView) vg.findViewById(R.id.lblstridor))
										.setVisibility(View.GONE);

							} else {

								((TextView) vg.findViewById(R.id.lbldecreased))
										.setVisibility(View.VISIBLE);
								((TextView) vg.findViewById(R.id.lblrhonchi))
										.setVisibility(View.VISIBLE);
								((TextView) vg
										.findViewById(R.id.lblcrepitation))
										.setVisibility(View.VISIBLE);
								((TextView) vg.findViewById(R.id.lblrhales))
										.setVisibility(View.VISIBLE);
								((TextView) vg.findViewById(R.id.lblstridor))
										.setVisibility(View.VISIBLE);

								((RadioGroup) vg
										.findViewById(R.id.radioGroupRhonchi))
										.setVisibility(View.VISIBLE);
								((RadioGroup) vg
										.findViewById(R.id.radioGroupCrepitation))
										.setVisibility(View.VISIBLE);
								((RadioGroup) vg
										.findViewById(R.id.radioGroupRhales))
										.setVisibility(View.VISIBLE);
								((RadioGroup) vg
										.findViewById(R.id.radioGroupDBreathSound))
										.setVisibility(View.VISIBLE);
								((RadioGroup) vg
										.findViewById(R.id.radioGroupStridor))
										.setVisibility(View.VISIBLE);

								((RadioGroup) vg
										.findViewById(R.id.radioGroupRhonchi))
										.clearCheck();
								((RadioGroup) vg
										.findViewById(R.id.radioGroupCrepitation))
										.clearCheck();
								((RadioGroup) vg
										.findViewById(R.id.radioGroupRhales))
										.clearCheck();
								((RadioGroup) vg
										.findViewById(R.id.radioGroupDBreathSound))
										.clearCheck();
								((RadioGroup) vg
										.findViewById(R.id.radioGroupStridor))
										.clearCheck();

							}
						}

					});

			((Spinner) v.findViewById(R.id.cboChestRadiograph))
					.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> arg0,
								View arg1, int arg2, long arg3) {

							String val = CommonStaticClass
									.GetSpinnerValue(
											((Spinner) vg
													.findViewById(R.id.cboChestRadiograph)))
									.trim();
							if (val.equalsIgnoreCase("0")) {
								((RadioGroup) vg
										.findViewById(R.id.radioGroupPneumonia))
										.clearCheck();
								((RadioGroup) vg
										.findViewById(R.id.radioGroupPneumonia))
										.setVisibility(View.GONE);

								((EditText) vg
										.findViewById(R.id.txtRadiographOther))
										.setVisibility(View.GONE);

								((TextView) vg.findViewById(R.id.lblxrayother))
										.setVisibility(View.GONE);
								((TextView) vg.findViewById(R.id.lblisfindings))
										.setVisibility(View.GONE);

								// (Te)LblXrayOther.Visible = false;

							} else if (val.equalsIgnoreCase("99")) {

								((RadioGroup) vg
										.findViewById(R.id.radioGroupPneumonia))
										.clearCheck();
								((RadioGroup) vg
										.findViewById(R.id.radioGroupPneumonia))
										.setVisibility(View.VISIBLE);

								((EditText) vg
										.findViewById(R.id.txtRadiographOther))
										.setVisibility(View.VISIBLE);
								((TextView) vg.findViewById(R.id.lblxrayother))
										.setVisibility(View.VISIBLE);
								((TextView) vg.findViewById(R.id.lblisfindings))
										.setVisibility(View.VISIBLE);

								// txtRadiographOther.Visible = true;
								// LblXrayOther.Visible = true;
								// optPneumoniaYes.Checked = false;
								// optPneumoniaNo.Checked = false;
								// optPneumoniaDk.Checked = false;
								// panel8.Enabled = true;

							} else {
								/*
								 * ((RadioGroup) vg
								 * .findViewById(R.id.radioGroupPneumonia))
								 * .clearCheck();
								 */
								((RadioGroup) vg
										.findViewById(R.id.radioGroupPneumonia))
										.setVisibility(View.VISIBLE);
								((EditText) vg
										.findViewById(R.id.txtRadiographOther))
										.setVisibility(View.GONE);

								((TextView) vg.findViewById(R.id.lblxrayother))
										.setVisibility(View.GONE);
								((TextView) vg.findViewById(R.id.lblisfindings))
										.setVisibility(View.VISIBLE);
								// ((TextView)vg.findViewById(R.id.lblisfindings)).setVisibility(View.GONE);

								/*
								 * panel8.Enabled = true;
								 * txtRadiographOther.Visible = false;
								 * LblXrayOther.Visible = false;
								 */
							}

						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
							// TODO Auto-generated method stub

						}
					});

			((RadioGroup) v.findViewById(R.id.radioGroupMedic))
					.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(RadioGroup group,
								int checkedId) {
							if (((RadioButton) group.findViewById(R.id.radio0))
									.isChecked()) {
								/*((Spinner) vg.findViewById(R.id.cboMedicineOne))
										.setVisibility(View.VISIBLE);*/
								med1.setVisibility(View.VISIBLE);
								/*
								 * ((Spinner)
								 * vg.findViewById(R.id.cboMedicineOne))
								 * .setVisibility(View.VISIBLE); ((Spinner)
								 * vg.findViewById(R.id.cboMedicineTwo))
								 * .setVisibility(View.VISIBLE); ((Spinner) vg
								 * .findViewById(R.id.cboMedicineThree))
								 * .setVisibility(View.VISIBLE);
								 */
								/*
								 * ((CheckBox) vg
								 * .findViewById(R.id.chkOtherMedicien))
								 * .setVisibility(View.VISIBLE);
								 */
								((RadioGroup) vg
										.findViewById(R.id.radioGroupIsAntiobiotic))
										.setVisibility(View.VISIBLE);

								((TextView) vg.findViewById(R.id.lblmedicine1))
										.setVisibility(View.VISIBLE);
								((TextView) vg.findViewById(R.id.lblmedicine2))
										.setVisibility(View.VISIBLE);
								((TextView) vg.findViewById(R.id.lblmedicine3))
										.setVisibility(View.VISIBLE);
								((TextView) vg
										.findViewById(R.id.lblisantibiotic))
										.setVisibility(View.VISIBLE);

							} else {
								/*((Spinner) vg.findViewById(R.id.cboMedicineOne))
										.setVisibility(View.GONE);
								((Spinner) vg.findViewById(R.id.cboMedicineTwo))
										.setVisibility(View.GONE);
								((Spinner) vg
										.findViewById(R.id.cboMedicineThree))
										.setVisibility(View.GONE);*/
								
								med1.setVisibility(View.GONE);
								med2.setVisibility(View.GONE);
								med3.setVisibility(View.GONE);
								((EditText) v.findViewById(R.id.txtMedicineOthers)).setVisibility(View.GONE);
								/*
								 * ((CheckBox) vg
								 * .findViewById(R.id.chkOtherMedicien))
								 * .setVisibility(View.GONE);
								 */
								((RadioGroup) vg
										.findViewById(R.id.radioGroupIsAntiobiotic))
										.setVisibility(View.GONE);
								((TextView) vg.findViewById(R.id.lblmedicine1))
										.setVisibility(View.GONE);
								((TextView) vg.findViewById(R.id.lblmedicine2))
										.setVisibility(View.GONE);
								((TextView) vg.findViewById(R.id.lblmedicine3))
										.setVisibility(View.GONE);
								((TextView) vg
										.findViewById(R.id.lblisantibiotic))
										.setVisibility(View.GONE);

								/*
								 * cboMedicineOne.SelectedIndex = -1;
								 * cboMedicineTwo.SelectedIndex = -1;
								 * cboMedicineThree.SelectedIndex = -1;
								 * //cboMedicineFour.SelectedIndex = -1;
								 * panel9.Enabled = false; panantibiotic.Enabled
								 * = false; optIsAntiobioticYes.Checked = false;
								 * optIsAntiobioticNo.Checked = false;
								 */
							}
						}

					});

			/*
			 * SetCheckBox((CheckBox) v.findViewById(R.id.chkOthersTreatment),
			 * (EditText) v.findViewById(R.id.txtTreatmentOthers), v);
			 */

			/*
			 * SetCheckBox((CheckBox) v.findViewById(R.id.chkOtherMedicien),
			 * (EditText) v.findViewById(R.id.txtMedicineOthers), v);
			 */

			((EditText) v.findViewById(R.id.txtMedicineOthers))
					.setVisibility(View.GONE);

			((EditText) v.findViewById(R.id.dtSpecimencol))
					.setVisibility(View.GONE);
			((EditText) v.findViewById(R.id.dtSpecimentime))
					.setVisibility(View.GONE);

			intAge = Integer.parseInt(CommonStaticClass.getSkip("AgeY",
					"tblMainQues", dbHelper));

			intComp = Integer.parseInt(CommonStaticClass.getSkip("Comp",
					"tblMainQues", dbHelper));

			if (intComp == 2) {
				/*
				 * ((RadioButton) ((RadioGroup) v
				 * .findViewById(R.id.radioGroupMedic))
				 * .findViewById(R.id.radio1)).setChecked(true);
				 */
				((RadioGroup) v.findViewById(R.id.radioGroupMedic))
						.setVisibility(View.GONE);
				((TextView) v.findViewById(R.id.lblforthisillness))
						.setVisibility(View.GONE);
				((TextView) v.findViewById(R.id.lblOtherMedicien))
						.setVisibility(View.GONE);
				((TextView) v.findViewById(R.id.lblisantibiotic))
						.setVisibility(View.GONE);
				((RadioGroup) v.findViewById(R.id.radioGroupIsAntiobiotic))
						.setVisibility(View.GONE);
				((TextView) v.findViewById(R.id.lblhospitalization))
						.setVisibility(View.GONE);
				((RadioGroup) v.findViewById(R.id.radioGroupOPD))
						.setVisibility(View.GONE);
				((TextView) v.findViewById(R.id.lblanti1))
						.setVisibility(View.GONE);
				/*((Spinner) v.findViewById(R.id.cbolistantiBio1))
						.setVisibility(View.GONE);*/
				ant1.setVisibility(View.GONE);
				((TextView) v.findViewById(R.id.lblanti2))
						.setVisibility(View.GONE);
				/*((Spinner) v.findViewById(R.id.cbolistantiBio2))
						.setVisibility(View.GONE);*/
				ant2.setVisibility(View.GONE);
				((TextView) v.findViewById(R.id.lblanti3))
						.setVisibility(View.GONE);
				/*((Spinner) v.findViewById(R.id.cbolistantiBio3))
						.setVisibility(View.GONE);*/
				ant3.setVisibility(View.GONE);
				((TextView) v.findViewById(R.id.lbld)).setVisibility(View.GONE);
				((EditText) v.findViewById(R.id.dtpAdmin))
						.setVisibility(View.GONE);
				((TextView) v.findViewById(R.id.lblt)).setVisibility(View.GONE);
				((EditText) v.findViewById(R.id.dtpAdminTime))
						.setVisibility(View.GONE);
				((TextView) v.findViewById(R.id.lbloxygen))
						.setVisibility(View.GONE);
				((RadioGroup) v.findViewById(R.id.radioGroupOxyRecv))
						.setVisibility(View.GONE);
				((TextView) v.findViewById(R.id.lblicu))
						.setVisibility(View.GONE);
				((RadioGroup) v.findViewById(R.id.radioGroupICU))
						.setVisibility(View.GONE);
				((TextView) v.findViewById(R.id.lblmechanical))
						.setVisibility(View.GONE);
				((RadioGroup) v.findViewById(R.id.radioGroupMechVenti))
						.setVisibility(View.GONE);
				// ((TextView)v.findViewById(R.id.lblTemperature)).setVisibility(View.GONE);
				((EditText) v.findViewById(R.id.txtNoofVentilator))
						.setVisibility(View.GONE);
				((TextView) v.findViewById(R.id.lblnodaysventilator))
						.setVisibility(View.GONE);

				((RadioGroup) v.findViewById(R.id.radioGroupOPD))
						.setVisibility(View.GONE);
				((RadioGroup) v.findViewById(R.id.radioGroupIsAntiobiotic))
						.setVisibility(View.GONE);
				/*
				 * optIsAntiobioticYes.Checked = false;
				 * optIsAntiobioticNo.Checked = false;
				 */

				// panVisitedOPD.Enabled = false;
				/*
				 * optOPDYes.Checked = false; optOPDNo.Checked = false;
				 */
				/*((Spinner) v.findViewById(R.id.cbolistantiBio1))
						.setVisibility(View.GONE);
				// cbolistantiBio1.SelectedIndex = -1;
				((Spinner) v.findViewById(R.id.cbolistantiBio2))
						.setVisibility(View.GONE);
				// cbolistantiBio2.SelectedIndex = -1;
				((Spinner) v.findViewById(R.id.cbolistantiBio3))
						.setVisibility(View.GONE);
				// cbolistantiBio3.SelectedIndex = -1;

				// cbolistantiBio1.SelectedIndex = -1;
				((Spinner) v.findViewById(R.id.cbolistantiBio1))
						.setVisibility(View.GONE);
				// cbolistantiBio2.SelectedIndex = -1;
				((Spinner) v.findViewById(R.id.cbolistantiBio2))
						.setVisibility(View.GONE);
				// cbolistantiBio3.SelectedIndex = -1;
				((Spinner) v.findViewById(R.id.cbolistantiBio3))
						.setVisibility(View.GONE);*/

				ant1.setVisibility(View.GONE);
				// cbolistantiBio1.SelectedIndex = -1;
				ant2.setVisibility(View.GONE);
				// cbolistantiBio2.SelectedIndex = -1;
				ant3.setVisibility(View.GONE);
				// cbolistantiBio3.SelectedIndex = -1;
		
				
				
				((EditText) v.findViewById(R.id.dtpAdmin)).setEnabled(false);
				((EditText) v.findViewById(R.id.dtpAdminTime))
						.setEnabled(false);

				// panel1.Enabled = false;
				((RadioGroup) v.findViewById(R.id.radioGroupOxyRecv))
						.setVisibility(View.GONE);
				((RadioGroup) v.findViewById(R.id.radioGroupICU))
						.setVisibility(View.GONE);
				/*
				 * optOxyRecvNo.Checked = false; optOxyRecvDk.Checked = false;
				 */

				// panel2.Enabled = false;
				/*
				 * optICUYes.Checked = false; optICUNo.Checked = false;
				 * optICUDK.Checked = false;
				 */

				/*
				 * ((Spinner) v.findViewById(R.id.cboTempTime))
				 * .setVisibility(View.GONE);
				 */

				/*
				 * cboMedicineOne.SelectedIndex = -1;
				 * cboMedicineTwo.SelectedIndex = -1;
				 * cboMedicineThree.SelectedIndex = -1;
				 */

				((RadioGroup) v.findViewById(R.id.radioGroupOPD))
						.setVisibility(View.GONE);
				// panVisitedOPD.Enabled = false;
				// optOPDYes.Checked = false;
				// optOPDNo.Checked = false;
				// panel9.Enabled = false;
				// panMedicine.Enabled = false;

				((TextView) v.findViewById(R.id.lblnodaysventilator))
						.setVisibility(View.GONE);
				((EditText) v.findViewById(R.id.txtNoofVentilator))
						.setVisibility(View.GONE);

			} else {

				((TextView) v.findViewById(R.id.lblnodaysventilator))
						.setVisibility(View.VISIBLE);
				((EditText) v.findViewById(R.id.txtNoofVentilator))
						.setVisibility(View.VISIBLE);

				((RadioGroup) v.findViewById(R.id.radioGroupMedic))
						.setVisibility(View.VISIBLE);
				((TextView) v.findViewById(R.id.lblforthisillness))
						.setVisibility(View.VISIBLE);
				((TextView) v.findViewById(R.id.lblOtherMedicien))
						.setVisibility(View.VISIBLE);
				/*
				 * ((RadioButton) ((RadioGroup) v
				 * .findViewById(R.id.radioGroupMedic))
				 * .findViewById(R.id.radio0)).setChecked(true);
				 */
				((RadioGroup) v.findViewById(R.id.radioGroupOPD))
						.setVisibility(View.VISIBLE);
				((RadioGroup) v.findViewById(R.id.radioGroupIsAntiobiotic))
						.setVisibility(View.VISIBLE);

				/*
				 * panMedicine.Enabled = true; optMedicYes.Checked = true;
				 */
				((RadioGroup) v.findViewById(R.id.radioGroupOPD))
						.setVisibility(View.VISIBLE);
				((RadioGroup) v.findViewById(R.id.radioGroupIsAntiobiotic))
						.setVisibility(View.VISIBLE);

				/*
				 * panantibiotic.Enabled = true; panVisitedOPD.Enabled = true;
				 */

				/*((Spinner) v.findViewById(R.id.cbolistantiBio1))
						.setVisibility(View.VISIBLE);
				((Spinner) v.findViewById(R.id.cbolistantiBio2))
						.setVisibility(View.VISIBLE);
				((Spinner) v.findViewById(R.id.cbolistantiBio3))
						.setVisibility(View.VISIBLE);*/
				ant1.setVisibility(View.VISIBLE);
				ant2.setVisibility(View.VISIBLE);
				ant3.setVisibility(View.VISIBLE);

				// cbolistantiBio1.Enabled = true;
				/*
				 * dtpAdmin.Enabled = true; dtpAdminTime.Enabled = true;
				 */

				((EditText) v.findViewById(R.id.dtpAdmin)).setEnabled(true);
				((EditText) v.findViewById(R.id.dtpAdminTime)).setEnabled(true);

				((RadioGroup) v.findViewById(R.id.radioGroupOxyRecv))
						.setVisibility(View.VISIBLE);
				((RadioGroup) v.findViewById(R.id.radioGroupICU))
						.setVisibility(View.VISIBLE);
				/*
				 * panel1.Enabled = true; panel2.Enabled = true;
				 */
				/* cboTempTime.SelectedIndex = -1; */

				((RadioGroup) v.findViewById(R.id.radioGroupMedic))
						.setVisibility(View.VISIBLE);
				((TextView) v.findViewById(R.id.lblforthisillness))
						.setVisibility(View.VISIBLE);
				((TextView) v.findViewById(R.id.lblOtherMedicien))
						.setVisibility(View.VISIBLE);
				((TextView) v.findViewById(R.id.lblisantibiotic))
						.setVisibility(View.VISIBLE);
				((RadioGroup) v.findViewById(R.id.radioGroupIsAntiobiotic))
						.setVisibility(View.VISIBLE);
				((TextView) v.findViewById(R.id.lblhospitalization))
						.setVisibility(View.VISIBLE);
				((RadioGroup) v.findViewById(R.id.radioGroupOPD))
						.setVisibility(View.VISIBLE);
				((TextView) v.findViewById(R.id.lblanti1))
						.setVisibility(View.VISIBLE);
				/*((Spinner) v.findViewById(R.id.cbolistantiBio1))
						.setVisibility(View.VISIBLE);*/
				ant1.setVisibility(View.VISIBLE);
				((TextView) v.findViewById(R.id.lblanti2))
						.setVisibility(View.VISIBLE);
				/*((Spinner) v.findViewById(R.id.cbolistantiBio2))
						.setVisibility(View.VISIBLE);*/
				ant2.setVisibility(View.VISIBLE);
				((TextView) v.findViewById(R.id.lblanti3))
						.setVisibility(View.VISIBLE);
				/*((Spinner) v.findViewById(R.id.cbolistantiBio3))
						.setVisibility(View.VISIBLE);*/
				ant3.setVisibility(View.VISIBLE);
				((TextView) v.findViewById(R.id.lbld))
						.setVisibility(View.VISIBLE);
				((EditText) v.findViewById(R.id.dtpAdmin))
						.setVisibility(View.VISIBLE);
				((TextView) v.findViewById(R.id.lblt))
						.setVisibility(View.VISIBLE);
				((EditText) v.findViewById(R.id.dtpAdminTime))
						.setVisibility(View.VISIBLE);
				((TextView) v.findViewById(R.id.lbloxygen))
						.setVisibility(View.VISIBLE);
				((RadioGroup) v.findViewById(R.id.radioGroupOxyRecv))
						.setVisibility(View.VISIBLE);
				((TextView) v.findViewById(R.id.lblicu))
						.setVisibility(View.VISIBLE);
				((RadioGroup) v.findViewById(R.id.radioGroupICU))
						.setVisibility(View.VISIBLE);
				((TextView) v.findViewById(R.id.lblmechanical))
						.setVisibility(View.VISIBLE);
				((RadioGroup) v.findViewById(R.id.radioGroupMechVenti))
						.setVisibility(View.VISIBLE);
				// ((TextView)v.findViewById(R.id.lblTemperature)).setVisibility(View.VISIBLE);
				((EditText) v.findViewById(R.id.txtNoofVentilator))
						.setVisibility(View.VISIBLE);
				((TextView) v.findViewById(R.id.lblnodaysventilator))
						.setVisibility(View.VISIBLE);

				((RadioGroup) v.findViewById(R.id.radioGroupOPD))
						.setVisibility(View.VISIBLE);
				((RadioGroup) v.findViewById(R.id.radioGroupIsAntiobiotic))
						.setVisibility(View.VISIBLE);

			}

			if (intAge < 5) {
				((Spinner) v.findViewById(R.id.cboMentalStatus))
						.setVisibility(View.VISIBLE);
				((RadioGroup) v.findViewById(R.id.chkLabFindings))
						.setVisibility(View.VISIBLE);
				((EditText) v.findViewById(R.id.dtSpecimencol))
						.setVisibility(View.VISIBLE);
				((EditText) v.findViewById(R.id.dtSpecimentime))
						.setVisibility(View.VISIBLE);
				((EditText) v.findViewById(R.id.txtWBCcount))
						.setVisibility(View.VISIBLE);
				((TextView) v.findViewById(R.id.lblWbcCount))
						.setVisibility(View.VISIBLE);
				((Spinner) v.findViewById(R.id.cboNeutrophils))
						.setVisibility(View.VISIBLE);
				((Spinner) v.findViewById(R.id.cboLymphocytes))
						.setVisibility(View.VISIBLE);
				((EditText) v.findViewById(R.id.txtPlateletCount))
						.setVisibility(View.VISIBLE);
				((EditText) v.findViewById(R.id.txtHemoglobin))
						.setVisibility(View.VISIBLE);
				((Spinner) v.findViewById(R.id.cboESR))
						.setVisibility(View.VISIBLE);
			} else {

				/*
				 * ((Spinner) v.findViewById(R.id.cboMentalStatus))
				 * .setVisibility(View.GONE);
				 */
				/*
				 * ((RadioGroup) v.findViewById(R.id.chkLabFindings))
				 * .setVisibility(View.GONE);
				 */
				((EditText) v.findViewById(R.id.dtSpecimencol))
						.setVisibility(View.GONE);
				((EditText) v.findViewById(R.id.dtSpecimentime))
						.setVisibility(View.GONE);
				((EditText) v.findViewById(R.id.txtWBCcount))
						.setVisibility(View.GONE);
				((TextView) v.findViewById(R.id.lblWbcCount))
						.setVisibility(View.GONE);
				((Spinner) v.findViewById(R.id.cboNeutrophils))
						.setVisibility(View.GONE);
				((Spinner) v.findViewById(R.id.cboLymphocytes))
						.setVisibility(View.GONE);
				((EditText) v.findViewById(R.id.txtPlateletCount))
						.setVisibility(View.GONE);
				((EditText) v.findViewById(R.id.txtHemoglobin))
						.setVisibility(View.GONE);
				((Spinner) v.findViewById(R.id.cboESR))
						.setVisibility(View.GONE);

				/*
				 * ((Spinner) v.findViewById(R.id.cboMentalStatus))
				 * .setVisibility(View.GONE);
				 */
				/*
				 * ((CheckBox) v.findViewById(R.id.chkLabFindings))
				 * .setVisibility(View.GONE);
				 */
				/*
				 * ((EditText) v.findViewById(R.id.dtSpecimencol))
				 * .setVisibility(View.GONE); ((EditText)
				 * v.findViewById(R.id.dtSpecimentime))
				 * .setVisibility(View.GONE); ((EditText)
				 * v.findViewById(R.id.txtWBCcount)) .setVisibility(View.GONE);
				 * ((Spinner) v.findViewById(R.id.cboNeutrophils))
				 * .setVisibility(View.GONE); ((Spinner)
				 * v.findViewById(R.id.cboLymphocytes))
				 * .setVisibility(View.GONE); ((EditText)
				 * v.findViewById(R.id.txtPlateletCount))
				 * .setVisibility(View.GONE); ((EditText)
				 * v.findViewById(R.id.txtHemoglobin))
				 * .setVisibility(View.GONE); ((Spinner)
				 * v.findViewById(R.id.cboESR)) .setVisibility(View.GONE);
				 */
			}

			((RadioGroup) v.findViewById(R.id.radioGroupMechVenti))
					.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

						public void onCheckedChanged(RadioGroup group,
								int checkedId) {

							if (((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupMechVenti))
									.findViewById(R.id.radio0)).isChecked()) {
								((TextView) v
										.findViewById(R.id.lblnodaysventilator))
										.setVisibility(View.VISIBLE);
								((EditText) v
										.findViewById(R.id.txtNoofVentilator))
										.setVisibility(View.VISIBLE);
							} else {
								((TextView) v
										.findViewById(R.id.lblnodaysventilator))
										.setVisibility(View.GONE);
								((EditText) v
										.findViewById(R.id.txtNoofVentilator))
										.setVisibility(View.GONE);
							}

						}
					});

			LoadDataFrmSymptomsOne(v);
		} catch (Exception ex) {
			CommonStaticClass.showMyAlert(thisactivity, "Error",
					"Error in Combo Loading");
		} finally {
		}

	}

	private void CheckMedicineIsAnAntibiotic(ViewGroup v) {
		/*if (((Spinner) v.findViewById(R.id.cboMedicineOne)).getVisibility() == View.VISIBLE) {
			if (((Spinner) v.findViewById(R.id.cboMedicineOne))
					.getSelectedItem().toString().length() > 0) {
				String val = CommonStaticClass.GetSpinnerValue(
						((Spinner) v.findViewById(R.id.cboMedicineOne))).trim();
				if (Integer.parseInt(val) < 59) {
					((RadioButton) ((RadioGroup) v
							.findViewById(R.id.radioGroupIsAntiobiotic))
							.findViewById(R.id.radio0)).setChecked(true);

				}

			}
		}
		if (((Spinner) v.findViewById(R.id.cboMedicineTwo)).getVisibility() == View.VISIBLE) {
			if (((Spinner) v.findViewById(R.id.cboMedicineTwo))
					.getSelectedItem().toString().length() > 0) {
				String val = CommonStaticClass.GetSpinnerValue(
						((Spinner) v.findViewById(R.id.cboMedicineTwo))).trim();
				if (Integer.parseInt(val) < 59) {
					((RadioButton) ((RadioGroup) v
							.findViewById(R.id.radioGroupIsAntiobiotic))
							.findViewById(R.id.radio0)).setChecked(true);

				}
			}
		}
		if (((Spinner) v.findViewById(R.id.cboMedicineThree)).getVisibility() == View.VISIBLE) {
			if (((Spinner) v.findViewById(R.id.cboMedicineThree))
					.getSelectedItem().toString().length() > 0) {
				String val = CommonStaticClass.GetSpinnerValue(
						((Spinner) v.findViewById(R.id.cboMedicineThree)))
						.trim();
				if (Integer.parseInt(val) < 59) {
					((RadioButton) ((RadioGroup) v
							.findViewById(R.id.radioGroupIsAntiobiotic))
							.findViewById(R.id.radio0)).setChecked(true);
				}
			}
		}*/
		
		
		if (med1.getVisibility() == View.VISIBLE) {
			if (med1.getText().toString().length() > 0) {
				String val = CommonStaticClass.Getcode(med1.getText().toString().trim(), "DrugCode", dbHelper);
				if (Integer.parseInt(val.trim()) < 59) {
					((RadioButton) ((RadioGroup) v
							.findViewById(R.id.radioGroupIsAntiobiotic))
							.findViewById(R.id.radio0)).setChecked(true);

				}

			}
		}
		if (med2.getVisibility() == View.VISIBLE) {
			if (med2.getText().toString().length() > 0) {
				String val = CommonStaticClass.Getcode(med2.getText().toString().trim(), "DrugCode", dbHelper);
				if (Integer.parseInt(val.trim()) < 59) {
					((RadioButton) ((RadioGroup) v
							.findViewById(R.id.radioGroupIsAntiobiotic))
							.findViewById(R.id.radio0)).setChecked(true);

				}

			}
		}
		if (med3.getVisibility() == View.VISIBLE) {
			if (med3.getText().toString().length() > 0) {
				String val = CommonStaticClass.Getcode(med3.getText().toString().trim(), "DrugCode", dbHelper);
				if (Integer.parseInt(val.trim()) < 59) {
					((RadioButton) ((RadioGroup) v
							.findViewById(R.id.radioGroupIsAntiobiotic))
							.findViewById(R.id.radio0)).setChecked(true);

				}

			}
		}

	}

	private void LoadDataFrmSymptomsOne(ViewGroup v) {
		/*
		 * SqlCeCommand cmdData; SqlCeDataReader DR; CultureInfo ci =
		 * CultureInfo.InvariantCulture; DateTime dtSpecimenTime =
		 * System.DateTime.Today; DateTime dtAdm = System.DateTime.Today;
		 */

		// ConManager.DBConnection("pass@2008");

		intAge = Integer.parseInt(CommonStaticClass.getSkip("AgeY",
				"tblMainQues", dbHelper));
		// intSex = Integer.parseInt(CommonStaticClass.getSkip("Sex",
		// "tblMainQues", dbHelper));
		intComp = Integer.parseInt(CommonStaticClass.getSkip("Comp",
				"tblMainQues", dbHelper));

		// GetAge();
		// intComp = Convert.ToInt16(txtHHID.Text.Substring(0, 1).Trim());
		String strSQL = "";
		Cursor mCursor = null;
		strSQL = "Select * from tblMainQues where dataid = '"
				+ CommonStaticClass.dataId + "'";
		try {

			mCursor = dbHelper.getQueryCursor(strSQL);

			if (mCursor.getCount() > 0) {

				if (mCursor.moveToFirst()) {

					do {

						CommonStaticClass.SetSpinnerValueFrmString(
								thisactivity, ((Spinner) v
										.findViewById(R.id.cboSystolic)),
								CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "bp_sys"));

						CommonStaticClass.SetSpinnerValueFrmString(
								thisactivity, ((Spinner) v
										.findViewById(R.id.cboDiastolic)),
								CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "bp_dias"));

						if (intAge >= 5) {
							((Spinner) v.findViewById(R.id.cboPulseRate))
									.setVisibility(View.VISIBLE);
							((TextView) v.findViewById(R.id.lblPulseRate))
									.setVisibility(View.VISIBLE);
							CommonStaticClass
									.SetSpinnerValueFrmString(
											thisactivity,
											(Spinner) v
													.findViewById(R.id.cboPulseRate),
											CommonStaticClass
													.CheckCursorValueWithNullHandler(
															mCursor, "pulse")
													.trim());

						} else {
							((Spinner) v.findViewById(R.id.cboPulseRate))
									.setVisibility(View.GONE);
							((TextView) v.findViewById(R.id.lblPulseRate))
									.setVisibility(View.GONE);

						}
						if (intAge < 5) {
							((Spinner) v.findViewById(R.id.cboHeartRate))
									.setVisibility(View.VISIBLE);
							CommonStaticClass.SetSpinnerValueFrmString(
									thisactivity,
									((Spinner) v
											.findViewById(R.id.cboHeartRate)),
									CommonStaticClass
											.CheckCursorValueWithNullHandler(
													mCursor, "heartrate")
											.trim());
							((TextView) v.findViewById(R.id.lblHeartRate))
									.setVisibility(View.VISIBLE);

						} else {
							((Spinner) v.findViewById(R.id.cboHeartRate))
									.setVisibility(View.GONE);
							((TextView) v.findViewById(R.id.lblHeartRate))
									.setVisibility(View.GONE);
						}

						if (CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "SpecimenCollected").equalsIgnoreCase(
								"1")) {

							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.chkLabFindings))
									.findViewById(R.id.radio0))
									.setChecked(true);

						} else {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.chkLabFindings))
									.findViewById(R.id.radio1))
									.setChecked(true);
						}

						((EditText) v.findViewById(R.id.txtTemperature))
								.setText(CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "temp"));
						if (CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "time_temp").equalsIgnoreCase("1")) {
							((Spinner) v.findViewById(R.id.cboTempTime))
									.setSelection(1);
						} else if (CommonStaticClass
								.CheckCursorValueWithNullHandler(mCursor,
										"time_temp").equalsIgnoreCase("2")) {

							((Spinner) v.findViewById(R.id.cboTempTime))
									.setSelection(2);
						} else if (CommonStaticClass
								.CheckCursorValueWithNullHandler(mCursor,
										"time_temp").equalsIgnoreCase(""))

						{
							((Spinner) v.findViewById(R.id.cboTempTime))
									.setSelection(0);
						}

						if (CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "temp_loc").equalsIgnoreCase("1")) {
							((Spinner) v.findViewById(R.id.cboTempLoc))
									.setSelection(1);
						} else if (CommonStaticClass
								.CheckCursorValueWithNullHandler(mCursor,
										"temp_loc").equalsIgnoreCase("2")) {
							((Spinner) v.findViewById(R.id.cboTempLoc))
									.setSelection(2);
						} else if (CommonStaticClass
								.CheckCursorValueWithNullHandler(mCursor,
										"temp_loc").equalsIgnoreCase("3")) {
							((Spinner) v.findViewById(R.id.cboTempLoc))
									.setSelection(3);
						} else if (CommonStaticClass
								.CheckCursorValueWithNullHandler(mCursor,
										"temp_loc").equalsIgnoreCase("4")) {
							((Spinner) v.findViewById(R.id.cboTempLoc))
									.setSelection(4);
						} else if (CommonStaticClass
								.CheckCursorValueWithNullHandler(mCursor,
										"temp_loc").equalsIgnoreCase("")) {
							((Spinner) v.findViewById(R.id.cboTempLoc))
									.setSelection(0);
						}

						if (intAge < 5) {
							((Spinner) v.findViewById(R.id.cboSystolic))
									.setSelection(-1);
							((Spinner) v.findViewById(R.id.cboDiastolic))
									.setSelection(-1);
							((Spinner) v.findViewById(R.id.cboSystolic))
									.setVisibility(View.GONE);
							((Spinner) v.findViewById(R.id.cboDiastolic))
									.setVisibility(View.GONE);
							((TextView) v.findViewById(R.id.lblDiastolic))
									.setVisibility(View.GONE);
							((TextView) v.findViewById(R.id.lblSystolic))
									.setVisibility(View.GONE);
						} else {

							((TextView) v.findViewById(R.id.lblDiastolic))
									.setVisibility(View.VISIBLE);
							((TextView) v.findViewById(R.id.lblSystolic))
									.setVisibility(View.VISIBLE);

							((Spinner) v.findViewById(R.id.cboSystolic))
									.setVisibility(View.VISIBLE);
							((Spinner) v.findViewById(R.id.cboDiastolic))
									.setVisibility(View.VISIBLE);

							CommonStaticClass
									.SetSpinnerValue(
											thisactivity,
											((Spinner) v
													.findViewById(R.id.cboSystolic)),
											CommonStaticClass
													.CheckCursorValueWithNullHandler(
															mCursor, "bp_sys"));

							CommonStaticClass.SetSpinnerValue(thisactivity,
									((Spinner) v
											.findViewById(R.id.cboDiastolic)),
									CommonStaticClass
											.CheckCursorValueWithNullHandler(
													mCursor, "bp_dias"));

						}

						if (CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "res_rate").equalsIgnoreCase("")
								|| CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "res_rate")
										.equalsIgnoreCase(null)) {
							((Spinner) v.findViewById(R.id.cboRespiratoryRate))
									.setSelection(-1);
						} else {
							CommonStaticClass
									.SetSpinnerValueFrmString(
											thisactivity,
											((Spinner) v
													.findViewById(R.id.cboRespiratoryRate)),
											CommonStaticClass
													.CheckCursorValueWithNullHandler(
															mCursor, "res_rate"));
						}

						if (CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "cyanosis").equalsIgnoreCase("1")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupCyanosis))
									.findViewById(R.id.radio0))
									.setChecked(true);
						} else if (CommonStaticClass
								.CheckCursorValueWithNullHandler(mCursor,
										"cyanosis").equalsIgnoreCase("2")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupCyanosis))
									.findViewById(R.id.radio1))
									.setChecked(true);
						} else {
							((RadioGroup) v
									.findViewById(R.id.radioGroupCyanosis))
									.clearCheck();
						}

						if (CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "breath_sound").equalsIgnoreCase("1")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupBreath))
									.findViewById(R.id.radio0))
									.setChecked(true);
						} else if (CommonStaticClass
								.CheckCursorValueWithNullHandler(mCursor,
										"breath_sound").equalsIgnoreCase("2")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupBreath))
									.findViewById(R.id.radio1))
									.setChecked(true);

						} else {
							((RadioGroup) v.findViewById(R.id.radioGroupBreath))
									.clearCheck();
						}

						if (CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "rhonchi").equalsIgnoreCase("1")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupRhonchi))
									.findViewById(R.id.radio0))
									.setChecked(true);

						} else if (CommonStaticClass
								.CheckCursorValueWithNullHandler(mCursor,
										"rhonchi").equalsIgnoreCase("2")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupRhonchi))
									.findViewById(R.id.radio1))
									.setChecked(true);

						} else {
							((RadioGroup) v
									.findViewById(R.id.radioGroupRhonchi))
									.clearCheck();
						}

						if (CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "Stridor").equalsIgnoreCase("1")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupStridor))
									.findViewById(R.id.radio0))
									.setChecked(true);

						} else if (CommonStaticClass
								.CheckCursorValueWithNullHandler(mCursor,
										"Stridor").equalsIgnoreCase("2")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupStridor))
									.findViewById(R.id.radio1))
									.setChecked(true);

						} else {
							((RadioGroup) v
									.findViewById(R.id.radioGroupStridor))
									.clearCheck();
						}

						if (CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "creps").equalsIgnoreCase("1")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupCrepitation))
									.findViewById(R.id.radio0))
									.setChecked(true);

						} else if (CommonStaticClass
								.CheckCursorValueWithNullHandler(mCursor,
										"creps").equalsIgnoreCase("2")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupCrepitation))
									.findViewById(R.id.radio1))
									.setChecked(true);

						} else {
							((RadioGroup) v
									.findViewById(R.id.radioGroupCrepitation))
									.clearCheck();
						}

						if (CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "rhales").equalsIgnoreCase("1")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupRhales))
									.findViewById(R.id.radio0))
									.setChecked(true);

						} else if (CommonStaticClass
								.CheckCursorValueWithNullHandler(mCursor,
										"rhales").equalsIgnoreCase("2")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupRhales))
									.findViewById(R.id.radio1))
									.setChecked(true);

						} else {
							((RadioGroup) v.findViewById(R.id.radioGroupRhales))
									.clearCheck();
						}

						if (CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "decrease_breath").equalsIgnoreCase(
								"1")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupDBreathSound))
									.findViewById(R.id.radio0))
									.setChecked(true);

						} else if (CommonStaticClass
								.CheckCursorValueWithNullHandler(mCursor,
										"decrease_breath")
								.equalsIgnoreCase("2")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupDBreathSound))
									.findViewById(R.id.radio1))
									.setChecked(true);

						} else {
							((RadioGroup) v
									.findViewById(R.id.radioGroupDBreathSound))
									.clearCheck();
						}

						if (CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "mental").equalsIgnoreCase(null)) {
							// CommonStaticClass.SetSpinnerValue(thisactivity,
							// (Spinner)v.findViewById(R.id.cboMentalStatus),
							// Value)
							((Spinner) v.findViewById(R.id.cboMentalStatus))
									.setSelection(0);

						} else {
							CommonStaticClass
									.SetSpinnerValue(
											thisactivity,
											(Spinner) v
													.findViewById(R.id.cboMentalStatus),
											CommonStaticClass
													.CheckCursorValueWithNullHandler(
															mCursor, "mental"));

						}

						if (CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "Date_SpecimenCollected")
								.equalsIgnoreCase("")) {
							((EditText) v.findViewById(R.id.dtSpecimencol))
									.setText("");
						} else {
							((EditText) v.findViewById(R.id.dtSpecimencol))
									.setText(CommonStaticClass
											.CheckCursorValueWithNullHandler(
													mCursor,
													"Date_SpecimenCollected"));
						}

						if (CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "time_SpecimenCollected").equals(null)
								|| (CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor,
												"time_SpecimenCollected")
										.equalsIgnoreCase(""))) {
							((EditText) v.findViewById(R.id.dtpAdmin))
									.setText(CommonStaticClass.GetDate());
						} else {
							((EditText) v.findViewById(R.id.dtSpecimentime))
									.setText(CommonStaticClass
											.CheckCursorValueWithNullHandler(
													mCursor,
													"time_SpecimenCollected"));

						}

						if (!CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "wbc").equalsIgnoreCase("0")
								&& !CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "wbc")
										.equalsIgnoreCase("")) {
							((EditText) v.findViewById(R.id.txtWBCcount))
									.setText(CommonStaticClass
											.CheckCursorValueWithNullHandler(
													mCursor, "wbc"));
							/*
							 * if (((RadioButton) ((RadioGroup) v
							 * .findViewById(R.id.chkLabFindings))
							 * .findViewById(R.id.radio0)).isChecked() == false)
							 * { ((RadioButton) ((RadioGroup) v
							 * .findViewById(R.id.chkLabFindings))
							 * .findViewById(R.id.radio0)) .setChecked(true);
							 * 
							 * }
							 */
						} else {
							((EditText) v.findViewById(R.id.txtWBCcount))
									.setText("");
						}

						CommonStaticClass.SetSpinnerValueFrmString(
								thisactivity, (Spinner) v
										.findViewById(R.id.cboNeutrophils),
								CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "neutro"));
						CommonStaticClass.SetSpinnerValueFrmString(
								thisactivity, (Spinner) v
										.findViewById(R.id.cboLymphocytes),
								CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "lympho"));

						if (((Spinner) v.findViewById(R.id.cboNeutrophils))
								.getSelectedItemPosition() != -1// .selecboNeutrophils.SelectedIndex
																// != -1
								|| ((Spinner) v
										.findViewById(R.id.cboLymphocytes))
										.getSelectedItemPosition() != -1) {
							/*
							 * if (((CheckBox)
							 * v.findViewById(R.id.chkLabFindings)) .isChecked()
							 * == false) { ((CheckBox)
							 * v.findViewById(R.id.chkLabFindings))
							 * .setChecked(true);
							 * 
							 * }
							 */
						}
						if (!CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "platelet").equalsIgnoreCase("0")
								&& !CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "platelet")
										.equalsIgnoreCase("")) {
							((EditText) v.findViewById(R.id.txtPlateletCount))
									.setText(CommonStaticClass
											.CheckCursorValueWithNullHandler(
													mCursor, "platelet"));
							/*
							 * if (((RadioButton) ((RadioGroup) v
							 * .findViewById(R.id.chkLabFindings))
							 * .findViewById(R.id.radio0)).isChecked() == false)
							 * { ((RadioButton) ((RadioGroup) v
							 * .findViewById(R.id.chkLabFindings))
							 * .findViewById(R.id.radio0)) .setChecked(true);
							 * ((EditText) v.findViewById(R.id.txtWBCcount))
							 * .setText(CommonStaticClass
							 * .CheckCursorValueWithNullHandler( mCursor,
							 * "wbc"));
							 * 
							 * }
							 */
						} else {
							((EditText) v.findViewById(R.id.txtPlateletCount))
									.setText("");
						}
						if (!CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "hemog").equalsIgnoreCase("0")
								&& !CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "hemog")
										.equalsIgnoreCase("")) {
							((EditText) v.findViewById(R.id.txtHemoglobin))
									.setText(CommonStaticClass
											.CheckCursorValueWithNullHandler(
													mCursor, "hemog"));

							/*
							 * if (((RadioButton) ((RadioGroup) v
							 * .findViewById(R.id.chkLabFindings))
							 * .findViewById(R.id.radio0)).isChecked() == false)
							 * { ((RadioButton) ((RadioGroup) v
							 * .findViewById(R.id.chkLabFindings))
							 * .findViewById(R.id.radio0)) .setChecked(true);
							 * ((EditText) v.findViewById(R.id.txtWBCcount))
							 * .setText(CommonStaticClass
							 * .CheckCursorValueWithNullHandler( mCursor,
							 * "wbc"));
							 * 
							 * }
							 */
						} else {

							((EditText) v.findViewById(R.id.txtHemoglobin))
									.setText("");
						}
						CommonStaticClass.SetSpinnerValueFrmString(
								thisactivity, ((Spinner) v
										.findViewById(R.id.cboESR)),
								CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "esr"));
						if (((Spinner) v.findViewById(R.id.cboESR))
								.getSelectedItemPosition() != -1) {
							/*
							 * if (((RadioButton) ((RadioGroup) v
							 * .findViewById(R.id.chkLabFindings))
							 * .findViewById(R.id.radio0)).isChecked() == false)
							 * { ((RadioButton) ((RadioGroup) v
							 * .findViewById(R.id.chkLabFindings))
							 * .findViewById(R.id.radio0)) .setChecked(true);
							 * 
							 * ((EditText) v.findViewById(R.id.txtWBCcount))
							 * .setText(CommonStaticClass
							 * .CheckCursorValueWithNullHandler( mCursor,
							 * "wbc"));
							 * 
							 * }
							 */
						}
						if (!CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "chest_cxr").equalsIgnoreCase("")) {
							if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"chest_cxr").equalsIgnoreCase("99")) {
								// need to check index 11
								CommonStaticClass
										.SetSpinnerValue(
												thisactivity,
												((Spinner) v
														.findViewById(R.id.cboChestRadiograph)),
												"99");

								((EditText) v
										.findViewById(R.id.txtRadiographOther))
										.setVisibility(View.VISIBLE);
								((TextView) v.findViewById(R.id.lblisfindings))
										.setVisibility(View.VISIBLE);
								((TextView) v.findViewById(R.id.lblxrayother))
										.setVisibility(View.VISIBLE);
								((EditText) v
										.findViewById(R.id.txtRadiographOther))
										.setText(CommonStaticClass
												.CheckCursorValueWithNullHandler(
														mCursor, "cxr_others"));
							} else {
								CommonStaticClass
										.SetSpinnerValue(
												thisactivity,
												((Spinner) v
														.findViewById(R.id.cboChestRadiograph)),
												CommonStaticClass
														.CheckCursorValueWithNullHandler(
																mCursor,
																"chest_cxr"));

								((EditText) v
										.findViewById(R.id.txtRadiographOther))
										.setText("");
								((EditText) v
										.findViewById(R.id.txtRadiographOther))
										.setVisibility(View.GONE);
								((TextView) v.findViewById(R.id.lblisfindings))
										.setVisibility(View.VISIBLE);
								((TextView) v.findViewById(R.id.lblxrayother))
										.setVisibility(View.GONE);
								((RadioGroup) v
										.findViewById(R.id.radioGroupPneumonia))
										.setVisibility(View.VISIBLE);
							}
						} else {
							((Spinner) v.findViewById(R.id.cboChestRadiograph))
									.setSelection(0);
							((EditText) v.findViewById(R.id.txtRadiographOther))
									.setText("");
							((EditText) v.findViewById(R.id.txtRadiographOther))
									.setVisibility(View.GONE);
							((TextView) v.findViewById(R.id.lblisfindings))
									.setVisibility(View.GONE);
							((TextView) v.findViewById(R.id.lblxrayother))
									.setVisibility(View.GONE);
							((RadioGroup) v
									.findViewById(R.id.radioGroupPneumonia))
									.setVisibility(View.GONE);
						}

						if (CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "dx_pneumonia").equalsIgnoreCase("1")) {

							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupPneumonia))
									.findViewById(R.id.radio0))
									.setChecked(true);
						} else if (CommonStaticClass
								.CheckCursorValueWithNullHandler(mCursor,
										"dx_pneumonia").equalsIgnoreCase("2")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupPneumonia))
									.findViewById(R.id.radio1))
									.setChecked(true);
						} else if (CommonStaticClass
								.CheckCursorValueWithNullHandler(mCursor,
										"dx_pneumonia").equalsIgnoreCase("9")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupPneumonia))
									.findViewById(R.id.radio2))
									.setChecked(true);
						} else {
							((RadioGroup) v
									.findViewById(R.id.radioGroupPneumonia))
									.clearCheck();

						}
					/*	if(Integer.parseInt(CommonStaticClass
								.CheckCursorValueWithNullHandler(
										mCursor, "med24hrs1").trim()) <59
							|| Integer.parseInt(CommonStaticClass
									.CheckCursorValueWithNullHandler(
											mCursor, "med24hrs2").trim()) <59
							|| Integer.parseInt(CommonStaticClass
									.CheckCursorValueWithNullHandler(
											mCursor, "med24hrs3").trim()) <59)
							((RadioGroup) v
									.findViewById(R.id.radioGroupIsAntiobiotic))
									.setVisibility(View.VISIBLE);
						else 
							((RadioGroup) v
									.findViewById(R.id.radioGroupIsAntiobiotic))
									.setVisibility(View.GONE);*/
						
						if (CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "medicine_24hrs")
								.equalsIgnoreCase("1")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupMedic))
									.findViewById(R.id.radio0))
									.setChecked(true);
						/*}
						 * else if (CommonStaticClass
						 * .CheckCursorValueWithNullHandler(mCursor,
						 * "med24hrs1").equalsIgnoreCase("") ||
						 * CommonStaticClass .CheckCursorValueWithNullHandler(
						 * mCursor, "med24hrs1").equals( null)) { ((Spinner)
						 * v.findViewById(R.id.cboMedicineOne))
						 * .setSelection(0);
						 * 
						 * }
						 */// 
//						else {

						/*CommonStaticClass
								.SetSpinnerValue(
										thisactivity,
										((Spinner) v
												.findViewById(R.id.cboMedicineOne)),
										CommonStaticClass
												.CheckCursorValueWithNullHandler(
														mCursor, "med24hrs1"));*/
							if(Integer.parseInt(CommonStaticClass
									.CheckCursorValueWithNullHandler(
											mCursor, "med24hrs1").trim()) >59)
								((RadioGroup) v
										.findViewById(R.id.radioGroupIsAntiobiotic))
										.setVisibility(View.VISIBLE);

							
							med1.setText(CommonStaticClass.GetName(CommonStaticClass
									.CheckCursorValueWithNullHandler(
											mCursor, "med24hrs1"),"DrugCode",dbHelper));
							med1.setVisibility(View.VISIBLE);
							

						 }
						if (CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "med24hrs2").equalsIgnoreCase("")
								|| CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "med24hrs2").equals(
												null)) {
							/*
							 * ((Spinner) v.findViewById(R.id.cboMedicineTwo))
							 * .setSelection(0);
							 */

						} else {
							/*CommonStaticClass
									.SetSpinnerValue(
											thisactivity,
											((Spinner) v
													.findViewById(R.id.cboMedicineTwo)),
											CommonStaticClass
													.CheckCursorValueWithNullHandler(
															mCursor,
															"med24hrs2"));*/
							med2.setText(CommonStaticClass.GetName(CommonStaticClass
									.CheckCursorValueWithNullHandler(
											mCursor, "med24hrs2"),"DrugCode",dbHelper));
							med2.setVisibility(View.VISIBLE);

						}

						if (CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "med24hrs3").equalsIgnoreCase("")
								|| CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "med24hrs3").equals(
												null))

						{
							/*
							 * ((Spinner) v.findViewById(R.id.cboMedicineThree))
							 * .setSelection(0);
							 */

						} else {
							/*CommonStaticClass
									.SetSpinnerValue(
											thisactivity,
											((Spinner) v
													.findViewById(R.id.cboMedicineThree)),
											CommonStaticClass
													.CheckCursorValueWithNullHandler(
															mCursor,
															"med24hrs3"));*/
							med3.setVisibility(View.VISIBLE);
							med3.setText(CommonStaticClass.GetName(CommonStaticClass
									.CheckCursorValueWithNullHandler(
											mCursor, "med24hrs3"),"DrugCode",dbHelper));

						}
						
						if (CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "med24hrs4").equalsIgnoreCase("")
								|| CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "med24hrs4").equals(
												null))

						{
							/*
							 * ((Spinner) v.findViewById(R.id.cboMedicineThree))
							 * .setSelection(0);
							 */

						} else {
							/*CommonStaticClass
									.SetSpinnerValue(
											thisactivity,
											((Spinner) v
													.findViewById(R.id.cboMedicineThree)),
											CommonStaticClass
													.CheckCursorValueWithNullHandler(
															mCursor,
															"med24hrs3"));*/
							((EditText) v.findViewById(R.id.txtMedicineOthers)).setVisibility(View.VISIBLE);
							((EditText) v.findViewById(R.id.txtMedicineOthers))
							.setText(CommonStaticClass
									.CheckCursorValueWithNullHandler(
											mCursor, "med24hrs4"));

						}
						

						if (CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "medicine_24hrs")
								.equalsIgnoreCase("2")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupMedic))
									.findViewById(R.id.radio1))
									.setChecked(true);

						} else if (CommonStaticClass
								.CheckCursorValueWithNullHandler(mCursor,
										"medicine_24hrs").equalsIgnoreCase("9")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupMedic))
									.findViewById(R.id.radio2))
									.setChecked(true);

							// optMedicDk.Checked = true;
						} else {
							/*
							 * optMedicYes.Checked = false; optMedicNo.Checked =
							 * false; optMedicDk.Checked = false;
							 */
							/*
							 * ((RadioGroup) v
							 * .findViewById(R.id.radioGroupPneumonia))
							 * .clearCheck();
							 */
						}

						if (CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "ps_abx").equalsIgnoreCase("1")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupOPD))
									.findViewById(R.id.radio0))
									.setChecked(true);

						} else if (CommonStaticClass
								.CheckCursorValueWithNullHandler(mCursor,
										"ps_abx").equalsIgnoreCase("2")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupOPD))
									.findViewById(R.id.radio1))
									.setChecked(true);
						} else {
							((RadioGroup) v.findViewById(R.id.radioGroupOPD))
									.clearCheck();
							/*
							 * optOPDYes.Checked = false; optOPDNo.Checked =
							 * false;
							 */
							/*
							 * ((RadioGroup) v
							 * .findViewById(R.id.radioGroupPneumonia))
							 * .clearCheck();
							 */
						}

						if (CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "ps_abx").equalsIgnoreCase("1")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupOPD))
									.findViewById(R.id.radio0))
									.setChecked(true);

							if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"list_antibiotics1")
									.equalsIgnoreCase("")
									|| CommonStaticClass
											.CheckCursorValueWithNullHandler(
													mCursor,
													"list_antibiotics1")
											.equals(null)) {

								/*((Spinner) v.findViewById(R.id.cbolistantiBio1))
										.setSelection(0);*/

							} else { //

								/*CommonStaticClass
										.SetSpinnerValue(
												thisactivity,
												((Spinner) v
														.findViewById(R.id.cbolistantiBio1)),
												CommonStaticClass
														.CheckCursorValueWithNullHandler(
																mCursor,
																"list_antibiotics1"));*/
								
								ant1.setText(CommonStaticClass.GetName(CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "list_antibiotics1"),"tblAntioBioticDrug",dbHelper));
								ant1.setVisibility(View.VISIBLE);

							}

							if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"list_antibiotics2")
									.equalsIgnoreCase("")
									|| CommonStaticClass
											.CheckCursorValueWithNullHandler(
													mCursor,
													"list_antibiotics2")
											.equals(null)) {
								// cbolistantiBio2.SelectedIndex = -1;
								/*((Spinner) v.findViewById(R.id.cbolistantiBio2))
										.setSelection(0);*/
							} else {
								/*CommonStaticClass
										.SetSpinnerValue(
												thisactivity,
												((Spinner) v
														.findViewById(R.id.cbolistantiBio2)),
												CommonStaticClass
														.CheckCursorValueWithNullHandler(
																mCursor,
																"list_antibiotics2"));*/
								ant2.setText(CommonStaticClass.GetName(CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "list_antibiotics2"),"tblAntioBioticDrug",dbHelper));
								ant2.setVisibility(View.VISIBLE);

							}

							if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"list_antibiotics3")
									.equalsIgnoreCase("")
									|| CommonStaticClass
											.CheckCursorValueWithNullHandler(
													mCursor,
													"list_antibiotics3")
											.equals(null)) {

								/*((Spinner) v.findViewById(R.id.cbolistantiBio3))
										.setSelection(0);*/

							} else {

								/*CommonStaticClass
										.SetSpinnerValue(
												thisactivity,
												((Spinner) v
														.findViewById(R.id.cbolistantiBio3)),
												CommonStaticClass
														.CheckCursorValueWithNullHandler(
																mCursor,
																"list_antibiotics3"));*/
								
								ant3.setText(CommonStaticClass.GetName(CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "list_antibiotics3"),"tblAntioBioticDrug",dbHelper));
								ant3.setVisibility(View.VISIBLE);

							}

						} else if (CommonStaticClass
								.CheckCursorValueWithNullHandler(mCursor,
										"ps_abx").equalsIgnoreCase("2")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupOPD))
									.findViewById(R.id.radio1))
									.setChecked(true);

							// optOPDNo.Checked = true;
						} else {

							((RadioGroup) v.findViewById(R.id.radioGroupOPD))
									.clearCheck();

							/*
							 * optOPDYes.Checked = false; optOPDNo.Checked =
							 * false;
							 */
						}

						if (CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "Date_administration")
								.equalsIgnoreCase("")) {
							((EditText) v.findViewById(R.id.dtpAdmin))
									.setText(CommonStaticClass.GetDate());
						} else {
							((EditText) v.findViewById(R.id.dtpAdmin))
									.setText(CommonStaticClass
											.CheckCursorValueWithNullHandler(
													mCursor,
													"Date_administration"));

						}

						if (CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "Time_administration")
								.equalsIgnoreCase("")
								|| CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "Time_administration")
										.equals(null)) {
							((EditText) v.findViewById(R.id.dtpAdminTime))
									.setText(CommonStaticClass.GetTime());
						} else {
							((EditText) v.findViewById(R.id.dtpAdminTime))
									.setText(CommonStaticClass
											.CheckCursorValueWithNullHandler(
													mCursor,
													"Time_administration"));

						}

						if (CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "oxygen_received").equalsIgnoreCase(
								"1")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupOxyRecv))
									.findViewById(R.id.radio0))
									.setChecked(true);

							// optOxyRecvYes.Checked = true;

						} else if (CommonStaticClass
								.CheckCursorValueWithNullHandler(mCursor,
										"oxygen_received")
								.equalsIgnoreCase("2")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupOxyRecv))
									.findViewById(R.id.radio1))
									.setChecked(true);

						} else if (CommonStaticClass
								.CheckCursorValueWithNullHandler(mCursor,
										"oxygen_received")
								.equalsIgnoreCase("9")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupOxyRecv))
									.findViewById(R.id.radio2))
									.setChecked(true);

						} else {
							((RadioGroup) v
									.findViewById(R.id.radioGroupOxyRecv))
									.clearCheck();
						}

						if (CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "treat1").equalsIgnoreCase("")
								|| CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "treat1").equals(null)) {

							/*((Spinner) v.findViewById(R.id.cboTreatmentOne))
									.setSelection(0);*/
							text1.setText("");
						} else {

						/*	CommonStaticClass
									.SetSpinnerValue(
											thisactivity,
											((Spinner) v
													.findViewById(R.id.cboTreatmentOne)),
											CommonStaticClass
													.CheckCursorValueWithNullHandler(
															mCursor, "treat1"));*/
							text1.setText(CommonStaticClass.GetName(CommonStaticClass
													.CheckCursorValueWithNullHandler(
															mCursor, "treat1"),"DrugCodeTreatment",dbHelper));

						}

						if (CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "treat2").equalsIgnoreCase("")
								|| CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "treat2").equals(null)) {

							/*((Spinner) v.findViewById(R.id.cboTreatmentTwo))
									.setSelection(0);*/
							text2.setText("");
						} else {

							/*CommonStaticClass
									.SetSpinnerValue(
											thisactivity,
											((Spinner) v
													.findViewById(R.id.cboTreatmentTwo)),
											CommonStaticClass
													.CheckCursorValueWithNullHandler(
															mCursor, "treat2"));*/
							((TextView) v
									.findViewById(R.id.lbltreatment2))
									.setVisibility(View.VISIBLE);
							text2.setVisibility(View.VISIBLE);
							text2.setText(CommonStaticClass.GetName(CommonStaticClass
									.CheckCursorValueWithNullHandler(
											mCursor, "treat2"),"DrugCodeTreatment",dbHelper));

						}

						if (CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "treat3").equalsIgnoreCase("")
								|| CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "treat3").equals(null)) {

							/*((Spinner) v.findViewById(R.id.cboTreatmentThree))
									.setSelection(-1);*/
							
							text3.setText("");
						} else {
							/*CommonStaticClass
									.SetSpinnerValue(
											thisactivity,
											((Spinner) v
													.findViewById(R.id.cboTreatmentThree)),
											CommonStaticClass
													.CheckCursorValueWithNullHandler(
															mCursor, "treat3"));*/
							((TextView) v
									.findViewById(R.id.lbltreatment3))
									.setVisibility(View.VISIBLE);
							text3.setVisibility(View.VISIBLE);
							text3.setText(CommonStaticClass.GetName(CommonStaticClass
									.CheckCursorValueWithNullHandler(
											mCursor, "treat3"),"DrugCodeTreatment",dbHelper));

						}

						if (CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "treat4").equalsIgnoreCase("")
								|| CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "treat4").equals(null)) {

							/*((Spinner) v.findViewById(R.id.cboTreatmentFour))
									.setSelection(-1);*/
							
							text4.setText("");
						} else {
							/*CommonStaticClass
									.SetSpinnerValue(
											thisactivity,
											((Spinner) v
													.findViewById(R.id.cboTreatmentFour)),
											CommonStaticClass
													.CheckCursorValueWithNullHandler(
															mCursor, "treat4"));*/
							((TextView) v
									.findViewById(R.id.lbltreatment4))
									.setVisibility(View.VISIBLE);
							text4.setVisibility(View.VISIBLE);
							text4.setText(CommonStaticClass.GetName(CommonStaticClass
									.CheckCursorValueWithNullHandler(
											mCursor, "treat4"),"DrugCodeTreatment",dbHelper));

						}

						if (!CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "treatOthers").equalsIgnoreCase("")) {

							// ((CheckBox)
							// v.findViewById(R.id.chkOthersTreatment)).setChecked(true);

							((TextView) v
									.findViewById(R.id.lblOthersTreatment))
									.setVisibility(View.VISIBLE);
							((EditText) v.findViewById(R.id.txtTreatmentOthers))
							.setVisibility(View.VISIBLE);
							((EditText) v.findViewById(R.id.txtTreatmentOthers))
									.setText(CommonStaticClass
											.CheckCursorValueWithNullHandler(
													mCursor, "treatOthers"));

						} else {
							((EditText) v.findViewById(R.id.txtTreatmentOthers))
									.setText("");
						}
						if (CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "icu").equalsIgnoreCase("1")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupICU))
									.findViewById(R.id.radio0))
									.setChecked(true);

						} else if (CommonStaticClass
								.CheckCursorValueWithNullHandler(mCursor, "icu")
								.equalsIgnoreCase("2")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupICU))
									.findViewById(R.id.radio1))
									.setChecked(true);
						} else if (CommonStaticClass
								.CheckCursorValueWithNullHandler(mCursor, "icu")
								.equalsIgnoreCase("9")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupICU))
									.findViewById(R.id.radio2))
									.setChecked(true);
						} else {
							((RadioGroup) v.findViewById(R.id.radioGroupICU))
									.clearCheck();
						}

						if (CommonStaticClass.CheckCursorValueWithNullHandler(
								mCursor, "Isventilation").equalsIgnoreCase("1")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupMechVenti))
									.findViewById(R.id.radio0))
									.setChecked(true);

							// optMechVentiY.Checked = true;
							((EditText) v.findViewById(R.id.txtNoofVentilator))
									.setText(CommonStaticClass
											.CheckCursorValueWithNullHandler(
													mCursor, "NoVentilatorDays"));

						} else if (CommonStaticClass
								.CheckCursorValueWithNullHandler(mCursor,
										"Isventilation").equalsIgnoreCase("2")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroupMechVenti))
									.findViewById(R.id.radio1))
									.setChecked(true);

						} else {
							((RadioGroup) v
									.findViewById(R.id.radioGroupMechVenti))
									.clearCheck();
						}

						((EditText) v.findViewById(R.id.txtComments))
								.setText(CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "comments"));

					} while (mCursor.moveToNext());
				}
			}

		} catch (Exception e) {
			CommonStaticClass.showMyAlert(thisactivity, "Error",
					"Error on Load");
		}

		/*
				

				*/

	}

	private void loadguifrmsymptoms(ViewGroup v) {

		resetViewGroup(v);
		qqq = (TextView) v.findViewById(R.id.qqq);

		qName = CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar();

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

		loadDataFrmSymptom(v);
		final ViewGroup vg = v;

		saveNxtButton = (Button) v.findViewById(R.id.saveNxtButton);

		saveNxtButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (CheckValidationfrmSymptom(vg))
					updateTableDataFrmSymptom(vg);
				// else
				/*
				 * CommonStaticClass.showMyAlert(thisactivity, "Message",
				 * "Please fill all fields correctly");
				 */

				// preserveState();
			}

		});
		clButton = (Button) v.findViewById(R.id.clButton);
		clButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				// resetViewGroup((ViewGroup) v);
			}

		});

		/*
		 * notesButton = (Button) v.findViewById(R.id.btnNote);
		 * notesButton.setOnClickListener(new OnClickListener() {
		 * 
		 * public void onClick(View v) { // TODO Auto-generated method stub
		 * FraNotes();
		 * 
		 * }
		 * 
		 * });
		 */
		prevButton = (Button) v.findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				userPressedPrevious(ParentActivity.this);
			}

		});

	}

	private Integer intAge, intSex, intComp;

	private boolean CheckValidationfrmSymptom(ViewGroup v) {
		// Checking Fever is checked or not
		intAge = Integer.parseInt(CommonStaticClass.getSkip("AgeY",
				"tblMainQues", dbHelper));
		intSex = Integer.parseInt(CommonStaticClass.getSkip("Sex",
				"tblMainQues", dbHelper));
		intComp = Integer.parseInt(CommonStaticClass.getSkip("Comp",
				"tblMainQues", dbHelper));

		if (((RadioGroup) v.findViewById(R.id.radioGroupchkSputum))
				.getVisibility() == View.VISIBLE) {
			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupchkSputum))
					.findViewById(R.id.radio0)).isChecked()) {

			} else if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupchkSputum))
					.findViewById(R.id.radio1)).isChecked())

			{

			} else {
				CommonStaticClass.showMyAlert(thisactivity, "Error",
						"Please input Sputum");
				return false;
			}

		}

		if (((Spinner) v.findViewById(R.id.cboIllness1)).getVisibility() == View.VISIBLE) {
			if (((Spinner) v.findViewById(R.id.cboIllness1)).getSelectedItem()
					.toString().trim().length() == 0) {
				CommonStaticClass.showMyAlert(thisactivity, "Error",
						"Please input History of Underlying chronic Illness.");
				return false;
			}
		}

		String edate = CommonStaticClass.getSkip("EntryDate", "tblMainQues",
				dbHelper);

		String dateInStringdtpCough = "";
		Date datedtpCough = null;
		SimpleDateFormat formatterdtpCough = new SimpleDateFormat("dd/MM/yyyy");
		if (((CheckBox) v.findViewById(R.id.chkCough)).isChecked()) {
			
			
			dateInStringdtpCough = ((EditText) v.findViewById(R.id.dtpCough))
					.getText().toString();

			

			
			try {
				datedtpCough = formatterdtpCough.parse(dateInStringdtpCough);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			if (CommonStaticClass.IsfutureDate(datedtpCough)) {
				CommonStaticClass.showMyAlert(thisactivity, "Error",
						"Please input correct cough date");
				return false;
			}
		}

		if (((CheckBox) v.findViewById(R.id.chkDiffbreathing)).isChecked()) {
			
			dateInStringdtpCough = ((EditText) v.findViewById(R.id.dtpDiffBreath)).getText().toString();
			datedtpCough = null;
					try {
						datedtpCough = formatterdtpCough.parse(dateInStringdtpCough);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					if (CommonStaticClass.IsfutureDate(datedtpCough)) {
						CommonStaticClass.showMyAlert(thisactivity, "Error",
								"Please input correct difficulty breathing date");
						return false;
					}
			
		}
		
		
		
	

		if (((CheckBox) v.findViewById(R.id.chkSorethroat)).isChecked()) {
			if (CommonStaticClass.IsValidHBISDate(((EditText) v.findViewById(R.id.dtpSoreThroat)))) {
				CommonStaticClass.showMyAlert(thisactivity, "Error",
						"Please input correct sore throat date");
				return false;
			}
		}

		if (((CheckBox) v.findViewById(R.id.chkRunningnose)).isChecked()) {
			
			if (CommonStaticClass.IsValidHBISDate(((EditText) v.findViewById(R.id.dtpRunningnose)))) {
				CommonStaticClass.showMyAlert(thisactivity, "Error",
						"Please input correct running nose date");
				return false;
			}
		}

		if (((CheckBox) v.findViewById(R.id.chkHeadache)).isChecked()) {
			
			if (CommonStaticClass.IsValidHBISDate(((EditText) v.findViewById(R.id.dtpHeadache)))) {
				CommonStaticClass.showMyAlert(thisactivity, "Error",
						"Please input correct headache date");
				return false;
			}
		}

		if (((CheckBox) v.findViewById(R.id.chkDiarrhea)).isChecked()) {
			
			if (CommonStaticClass.IsValidHBISDate(((EditText) v.findViewById(R.id.dtpDiarrhea)))) {
				CommonStaticClass.showMyAlert(thisactivity, "Error",
						"Please input correct diarrhea date");
				return false;
			}
		}

		if (((CheckBox) v.findViewById(R.id.chkChills)).isChecked()) {
			
			if (CommonStaticClass.IsValidHBISDate(((EditText) v.findViewById(R.id.dtpChills)))) {
			
				CommonStaticClass.showMyAlert(thisactivity, "Error",
						"Please input correct chills date");
				return false;
			}
		}

		if (((CheckBox) v.findViewById(R.id.chkBodyache)).isChecked()) {
			
			if (CommonStaticClass.IsValidHBISDate(((EditText) v.findViewById(R.id.dtpBodyache)))) {
				CommonStaticClass.showMyAlert(thisactivity, "Error",
						"Please input correct Body ache date");
				return false;
			}
		}

		if (((CheckBox) v.findViewById(R.id.chkhemo)).isChecked()) {
			
			if (CommonStaticClass.IsValidHBISDate(((EditText) v.findViewById(R.id.dtpHemo)))) {
								
				CommonStaticClass.showMyAlert(thisactivity, "Error",
						"Please input correct Hemoptysis date");
				return false;
			}
		}

		if (((CheckBox) v.findViewById(R.id.chkpleur)).isChecked()) {
			
			if (CommonStaticClass.IsValidHBISDate(((EditText) v.findViewById(R.id.dtppleur)))) {
			
				CommonStaticClass.showMyAlert(thisactivity, "Error",
						"Please input correct Pleuritic Chest pain date");
				return false;
			}
		}

		if (((CheckBox) v.findViewById(R.id.chkOtherOne)).isChecked()) {
			
			if (CommonStaticClass.IsValidHBISDate(((EditText) v.findViewById(R.id.dtpOtherOne)))) {
			
				CommonStaticClass.showMyAlert(thisactivity, "Error",
						"Please input correct Other date");
				return false;
			}
		}

		if (((CheckBox) v.findViewById(R.id.chkOtherTwo)).isChecked()) {
			if (CommonStaticClass.IsValidHBISDate(((EditText) v.findViewById(R.id.dtpOtherTwo)))) {
			CommonStaticClass.showMyAlert(thisactivity, "Error",
						"Please input correct Other date");
				return false;
			}
		}

		if (((CheckBox) v.findViewById(R.id.chkOtherThree)).isChecked()) {
			
			if (CommonStaticClass.IsValidHBISDate(((EditText) v.findViewById(R.id.dtpOtherThree)))) {
			CommonStaticClass.showMyAlert(thisactivity, "Error",
						"Please input correct Other date");
				return false;
			}
		}

		if (((CheckBox) v.findViewById(R.id.chkChestindraw)).isChecked()) {
			
			if (CommonStaticClass.IsValidHBISDate(((EditText) v.findViewById(R.id.dtpChestIndraw)))) {
			CommonStaticClass.showMyAlert(thisactivity, "Error",
						"Please input correct Chest Indraw date");
				return false;
			}
		}

		if (((CheckBox) v.findViewById(R.id.chkStridor)).isChecked()) {
			
			if (CommonStaticClass.IsValidHBISDate(((EditText) v.findViewById(R.id.dtpStridor)))) {
				CommonStaticClass.showMyAlert(thisactivity, "Error",
						"Please input correct Stridor date");
				return false;
			}
		}

		if (((CheckBox) v.findViewById(R.id.chkConvulsion)).isChecked()) {
			if (CommonStaticClass.IsValidHBISDate(((EditText) v.findViewById(R.id.dtpConvulsion)))) {
			CommonStaticClass.showMyAlert(thisactivity, "Error",
						"Please input correct Convulsion date");
				return false;
			}
		}

		if (((CheckBox) v.findViewById(R.id.chkUnableToDrink)).isChecked()) {
			if (CommonStaticClass.IsValidHBISDate(((EditText) v.findViewById(R.id.dtpUnableDrink)))) {
			
				CommonStaticClass.showMyAlert(thisactivity, "Error",
						"Please input correct Unable to Drink date");
				return false;
			}
		}

		if (((CheckBox) v.findViewById(R.id.chkUnconsciousness)).isChecked()) {
			if (CommonStaticClass.IsValidHBISDate(((EditText) v.findViewById(R.id.dtpUnconsciousness)))) {
			
				CommonStaticClass.showMyAlert(thisactivity, "Error",
						"Please input correct Unconsciousness date");
				return false;
			}
		}

		if (((CheckBox) v.findViewById(R.id.chkVomit)).isChecked()) {
			if (CommonStaticClass.IsValidHBISDate(((EditText) v.findViewById(R.id.dtpVomit)))) {
			
				CommonStaticClass.showMyAlert(thisactivity, "Error",
						"Please input correct Vomit date");
				return false;
			}
		}

		if (((CheckBox) v.findViewById(R.id.chkFever)).isChecked() == true) {
			if (CommonStaticClass.IsValidHBISDate(((EditText) v.findViewById(R.id.dtpFever)))) {
			
				CommonStaticClass.showMyAlert(thisactivity, "Error",
						"Please input correct Fever date");
				return false;
			}

		}
		if (intComp != 3) {
			if (((CheckBox) v.findViewById(R.id.chkCough)).isChecked() == false) {
				CommonStaticClass.showMyAlert(thisactivity, "Message",
						"Cough must be checked.");
				((CheckBox) v.findViewById(R.id.chkCough))
						.setBackgroundResource(R.drawable.border);
				// tabDiseaseInfo.SelectedIndex = 0;
				return false;
			}
		}
		// Checking Fever is checked or not
		if (intComp != 3) {
			if (((CheckBox) v.findViewById(R.id.chkFever)).isChecked() == false) {
				CommonStaticClass.showMyAlert(thisactivity, "Message",
						"Fever must be checked");
				((CheckBox) v.findViewById(R.id.chkFever))
						.setBackgroundResource(R.drawable.border);
				// tabDiseaseInfo.SelectedIndex = 0;
				return false;
			} else if (((CheckBox) v.findViewById(R.id.chkFever)).isChecked() == true) {
				try {

					
					if (((CheckBox) v.findViewById(R.id.chkFever)).isChecked() == true) {
						if (CommonStaticClass.IsValidHBISDate(((EditText) v.findViewById(R.id.dtpFever)))) {
						
							CommonStaticClass.showMyAlert(thisactivity, "Error",
									"Please input correct Fever date");
							return false;
						}

					}
					
					
				/*	if (CommonStaticClass.IsSeconDateGraterThanFirstDate(
							((EditText) v.findViewById(R.id.dtpFever))
									.getText().toString(), edate)) {
						CommonStaticClass.showMyAlert(thisactivity, "Error",
								"Please input correct Fever date");
						return false;
					}*/

					SimpleDateFormat formatter = new SimpleDateFormat(
							"yyyy-MM-dd");

					String[] d = (((EditText) v.findViewById(R.id.dtpFever))
							.getText().toString()).split("/");

					String dateInString = d[2] + "-" + d[1] + "-" + d[0];

					Date date, denddate;
					if (dateInString.length() <= 0) {
						CommonStaticClass.showMyAlert(thisactivity, "Error",
								"Please input date");
						return false;
					}
					date = formatter.parse(dateInString);

					Calendar startdate = Calendar.getInstance();
					Calendar enddate = Calendar.getInstance();

					startdate.setTime(date);

					String senddate = enddate.get(Calendar.YEAR)
							+ "-"
							+ CommonStaticClass.SetpadLeft(String
									.valueOf(enddate.get(Calendar.MONTH) + 1),
									enddate.get(Calendar.MONTH)) + "-"
							+ enddate.get(Calendar.DAY_OF_MONTH);

					denddate = formatter.parse(senddate);
					enddate.setTime(denddate);

					int x = CommonStaticClass.DayDifferenceBackwardWithMonth(
							startdate, enddate);

					if (x > 7) {
						CommonStaticClass.showMyAlert(thisactivity, "Error",
								"Fever Date can not be more than 7");
						return false;

					}

					else if (x == 1) {

						// return false;

					}

					else if (x == 2) {

						// return false;

					} else if (x == 3) {

						// return false;

					} else if (x == 4) {

						// return false;

					} else if (x == 5) {

						// return false;

					} else if (x == 6) {

						// return false;

					}

					else if (x == 7) {

						// return false;

					} else if (x == 0) {

						// return false;

					} else {

						CommonStaticClass.showMyAlert(thisactivity, "Error",
								"Fever Date can not be more than 7");
						return false;

					}

					/*
					 * else if (CommonStaticClass .DayDifference(enddate,
					 * startdate) < 7) {
					 * CommonStaticClass.showMyAlert(thisactivity, "Error",
					 * "Fever Date can not be less than 7"); return false;
					 * 
					 * }
					 */

					/*
					 * if(!CommonStaticClass.IsSeconDateGraterThanFirstDate(((
					 * EditText) v
					 * .findViewById(R.id.dtpFever)).getText().toString(),
					 * CommonStaticClass.getSkip("EntryDate", "tblMainQues",
					 * dbHelper))) { CommonStaticClass.showMyAlert(thisactivity,
					 * "Error", "Please input correct fever date"); return
					 * false; }
					 */

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/*
				 * ((EditText) v.findViewById(R.id.dtpFever))
				 * .setText(CommonStaticClass .CheckCursorValueWithNullHandler(
				 * mCursor, "dt_fever") .toString());
				 */
			}
			if (((CheckBox) v.findViewById(R.id.chkMeasureFever)).isChecked() == false) {
				CommonStaticClass.showMyAlert(thisactivity, "Message",
						"Measure Fever must be checked");
				((CheckBox) v.findViewById(R.id.chkMeasureFever))
						.setBackgroundResource(R.drawable.border);
				// tabDiseaseInfo.SelectedIndex = 0;
				return false;
			}
		}
		if (intComp == 3) {
			if (((CheckBox) v.findViewById(R.id.chkDiffbreathing)).isChecked() == false
					&& ((CheckBox) v.findViewById(R.id.chkCough)).isChecked() == false) {
				CommonStaticClass.showMyAlert(thisactivity, "Message",
						"Diffbreathing or cough must be checked.");
				((CheckBox) v.findViewById(R.id.chkDiffbreathing))
						.setBackgroundResource(R.drawable.border);
				// tabDiseaseInfo.SelectedIndex = 0;
				return false;
			}
		}
		if (intSex == 2 && intAge > 11 && intAge < 51) {
			if (((RadioButton) ((RadioGroup) v
					.findViewById(R.id.radioGroupPregnant))
					.findViewById(R.id.radioButton1)).isChecked() == false
					&& ((RadioButton) ((RadioGroup) v
							.findViewById(R.id.radioGroupPregnant))
							.findViewById(R.id.radioButton2)).isChecked() == false)

			// if (optPregnantYes.Checked == false && optPregnantNo.Checked ==
			// false)
			{
				CommonStaticClass.showMyAlert(thisactivity, "Message",
						"Please select any option for pregnancy.");
				// tabDiseaseInfo.SelectedIndex = 3;
				((RadioGroup) v.findViewById(R.id.radioGroupPregnant))
						.setBackgroundResource(R.drawable.border);
				return false;
			}
		}

		if (intComp == 3 && intAge < 5) {
			if (((CheckBox) v.findViewById(R.id.chkChestindraw)).isChecked() == false
					&& ((CheckBox) v.findViewById(R.id.chkStridor)).isChecked() == false
					&& ((CheckBox) v.findViewById(R.id.chkConvulsion))
							.isChecked() == false
					&& ((CheckBox) v.findViewById(R.id.chkUnableToDrink))
							.isChecked() == false
					&& ((CheckBox) v.findViewById(R.id.chkUnconsciousness))
							.isChecked() == false
					&& ((CheckBox) v.findViewById(R.id.chkVomit)).isChecked() == false) {

				((CheckBox) v.findViewById(R.id.chkChestindraw))
						.setBackgroundResource(R.drawable.border);
				((CheckBox) v.findViewById(R.id.chkStridor))
						.setBackgroundResource(R.drawable.border);
				((CheckBox) v.findViewById(R.id.chkConvulsion))
						.setBackgroundResource(R.drawable.border);
				((CheckBox) v.findViewById(R.id.chkUnableToDrink))
						.setBackgroundResource(R.drawable.border);
				((CheckBox) v.findViewById(R.id.chkUnconsciousness))
						.setBackgroundResource(R.drawable.border);
				((CheckBox) v.findViewById(R.id.chkVomit))
						.setBackgroundResource(R.drawable.border);

				CommonStaticClass
						.showMyAlert(thisactivity, "Message",
								"Please check atleast one option from Coloured Check boxes");

				// tabDiseaseInfo.SelectedIndex = 2;
				return false;
			}
		}
		if (intAge > 11) {
			/*
			 * if (optSmoke1.Checked == false && optSmoke2.Checked == false &&
			 * optSmoke3.Checked == false && optSmoke4.Checked == false) {
			 */

			if (((RadioGroup) v.findViewById(R.id.radioGroupsmoke))
					.getCheckedRadioButtonId() == -1) {
				CommonStaticClass.showMyAlert(thisactivity, "Message",
						"Please select smoking information.");
				((RadioGroup) v.findViewById(R.id.radioGroupsmoke))
						.setBackgroundResource(R.drawable.border);
				// tabDiseaseInfo.SelectedIndex = 3;
				// cboSmoke.Focus();
				return false;
			}
		}

		if (((RadioGroup) v.findViewById(R.id.radioGroupLungDisease))
				.getCheckedRadioButtonId() == -1) {

			// Checking lung disease
			// if (optLungYes.Checked == false && optLungNo.Checked == false) {
			((RadioGroup) v.findViewById(R.id.radioGroupLungDisease))
					.setBackgroundResource(R.drawable.border);
			CommonStaticClass.showMyAlert(thisactivity, "Message",
					"Please select any option for lung disease.");
			// tabDiseaseInfo.SelectedIndex = 3;
			return false;
		}

		if (((RadioGroup) v.findViewById(R.id.radioGroupHeartDis))
				.getCheckedRadioButtonId() == -1) {
			((RadioGroup) v.findViewById(R.id.radioGroupHeartDis))
					.setBackgroundResource(R.drawable.border);
			CommonStaticClass.showMyAlert(thisactivity, "Message",
					"Please select any option for Heart disease.");

			return false;
		}
		if (intComp != 2) {
			if (intSex == 2 && intAge > 11 && intAge < 51) {
				if (((RadioButton) ((RadioGroup) v
						.findViewById(R.id.radioGroupPregnant))
						.findViewById(R.id.radioButton1)).isChecked() == false
						&& ((RadioButton) ((RadioGroup) v
								.findViewById(R.id.radioGroupPregnant))
								.findViewById(R.id.radioButton2)).isChecked() == false) {

					((RadioGroup) v.findViewById(R.id.radioGroupPregnant))
							.setBackgroundResource(R.drawable.border);

					CommonStaticClass.showMyAlert(thisactivity, "Message",
							"Please select any option for pregnancy.");
					// tabDiseaseInfo.SelectedIndex = 3;
					return false;
				}
			}
		}

		// intComp = Convert.ToInt16(txtHHID.Text.Substring(0, 1).Trim());

		if (intComp == 1) {
			if (((RadioGroup) v.findViewById(R.id.radioGroupVisitOPD))
					.getCheckedRadioButtonId() == -1) {

				/*
				 * } if (optOPDYes.Checked == false && optOPDNo.Checked ==
				 * false) {
				 */
				((RadioGroup) v.findViewById(R.id.radioGroupVisitOPD))
						.setBackgroundResource(R.drawable.border);
				CommonStaticClass.showMyAlert(thisactivity, "Message",
						"Please select any option for OPD.");
				/*
				 * MessageBox.Show("Please select any option for OPD.",
				 * "Need Data");
				 */
				// tabDiseaseInfo.SelectedIndex = 3;
				return false;
			}
		}
		if (((CheckBox) v.findViewById(R.id.chkOtherOne)).isChecked() == true
				&& ((EditText) v.findViewById(R.id.txtOtherOne)).getText()
						.toString().length() <= 0) {
			((EditText) v.findViewById(R.id.txtOtherOne))
					.setBackgroundResource(R.drawable.border);
			CommonStaticClass.showMyAlert(thisactivity, "Message",
					"Please write something in other.");

			// tabDiseaseInfo.SelectedIndex = 2;
			return false;
		}
		if (((CheckBox) v.findViewById(R.id.chkOtherTwo)).isChecked() == true
				&& ((EditText) v.findViewById(R.id.txtOtherTwo)).getText()
						.toString().length() <= 0) {
			((EditText) v.findViewById(R.id.txtOtherTwo))
					.setBackgroundResource(R.drawable.border);
			CommonStaticClass.showMyAlert(thisactivity, "Message",
					"Please write something in other.");
			// tabDiseaseInfo.SelectedIndex = 2;
			return false;
		}
		if (((CheckBox) v.findViewById(R.id.chkOtherThree)).isChecked() == true
				&& ((EditText) v.findViewById(R.id.txtOtherThree)).getText()
						.toString().length() <= 0) {
			((EditText) v.findViewById(R.id.txtOtherThree))
					.setBackgroundResource(R.drawable.border);
			CommonStaticClass.showMyAlert(thisactivity, "Message",
					"Please write something in other.");
			// tabDiseaseInfo.SelectedIndex = 2;
			return false;
		}

		if (((Spinner) v.findViewById(R.id.cboIllness1)).getVisibility() == View.VISIBLE) {

			if (((Spinner) v.findViewById(R.id.cboIllness1))
					.getSelectedItemPosition() != 11) {
				if (((RadioGroup) v.findViewById(R.id.radioGroupTacMed))
						.getCheckedRadioButtonId() == -1) {
					CommonStaticClass.showMyAlert(thisactivity, "Message",
							"Please select chronic Drug List.");
					return false;
				}
			}
		}
		if (((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroupTacMed))
				.findViewById(R.id.radioButton1)).isChecked() == true) {
			// if (optTacMedY.Checked == true) {

			if (((Spinner) v.findViewById(R.id.cboTacMedicine1))
					.getVisibility() == View.VISIBLE) {

				if (((Spinner) v.findViewById(R.id.cboTacMedicine1))
						.getSelectedItem().toString().length() <= 0) {

					CommonStaticClass.showMyAlert(thisactivity, "Message",
							"please selct drug list1");
					return false;
				}
			}
			if (((Spinner) v.findViewById(R.id.cboTacMedicine1))
					.getVisibility() == View.VISIBLE) {
				if (((Spinner) v.findViewById(R.id.cboTacMedicine2))
						.getSelectedItem().toString().length() <= 0) {

					// if (cboTacMedicine2.SelectedIndex == -1) {
					CommonStaticClass.showMyAlert(thisactivity, "Message",
							"please selct drug list2");
					return false;
				}
			}
			if (((Spinner) v.findViewById(R.id.cboTacMedicine3))
					.getVisibility() == View.VISIBLE) {
				if (((Spinner) v.findViewById(R.id.cboTacMedicine3))
						.getSelectedItem().toString().length() <= 0) {

					CommonStaticClass.showMyAlert(thisactivity, "Message",
							"please selct drug list3");
					return false;
				}
			}
		}
		return true;
	}

	private void updateTableDataFrmSymptom(ViewGroup vg) {
		try {
			// if (SB.Length > 0)
			// {
			// SB.Remove(0, SB.Length);
			// }
			StringBuilder SB = new StringBuilder();

			intAge = Integer.parseInt(CommonStaticClass.getSkip("AgeY",
					"tblMainQues", dbHelper));
			intSex = Integer.parseInt(CommonStaticClass.getSkip("Sex",
					"tblMainQues", dbHelper));
			intComp = Integer.parseInt(CommonStaticClass.getSkip("Comp",
					"tblMainQues", dbHelper));

			SB.append("UPDATE tblMainQues ");
			if (((CheckBox) vg.findViewById(R.id.chkFever)).isChecked() == true) {
				SB.append("Set Fever = 1");
				SB.append(", dt_fever = '"
						+ ((EditText) vg.findViewById(R.id.dtpFever)).getText()
								.toString() + "' ");
			} else {
				SB.append("Set Fever = 2 ");
				SB.append(", dt_fever = null ");
			}
			if (((CheckBox) vg.findViewById(R.id.chkMeasureFever)).isChecked()) {
				// int x =
				// (((Spinner)vg.findViewById(R.id.cboSfever)).getSelectedItem().toString().lastIndexOf(":")
				// - 1);

				SB.append(", fever_meas_subj = "
						+ CommonStaticClass.GetSpinnerValue(((Spinner) vg
								.findViewById(R.id.cboSfever))));

				if ((((Spinner) vg.findViewById(R.id.cboSfever))
						.getSelectedItem().toString().lastIndexOf(":") - 1) == 1) {
					SB.append(", fever_temp = '"
							+ ((EditText) vg.findViewById(R.id.txtFeverTemp))
									.getText().toString() + "'");
				} else {
					SB.append(", fever_temp = null ");
				}

			} else {
				SB.append(", fever_meas_subj = null ");
				SB.append(", fever_temp = null ");
			}
			if (((CheckBox) vg.findViewById(R.id.chkCough)).isChecked()) {
				SB.append(", cough = 1");
				SB.append(", dt_cough = '"
						+ ((EditText) vg.findViewById(R.id.dtpCough)).getText()
								.toString() + "' ");
			} else {
				SB.append(", dt_cough = Null ");
				SB.append(", Cough = 2 ");
			}

			if (((RadioButton) ((RadioGroup) vg
					.findViewById(R.id.radioGroupchkSputum))
					.findViewById(R.id.radio0)).isChecked())
			// if (((RadioGroup)
			// vg.findViewById(R.id.radioGroupchkSputum)).isChecked())
			{
				// SB.append(", sputum = '" + txtSputum.Text.ToString() + "' ");
				SB.append(", sputum = 1 ");
				// if (optSputumY.Checked == true)
				// {
				// SB.append(", sputum = 1 ");
				// }
				// else if (optSputumN.Checked == true)
				// {
				// SB.append(", sputum = 2 ");
				// }
			} else if (((RadioButton) ((RadioGroup) vg
					.findViewById(R.id.radioGroupchkSputum))
					.findViewById(R.id.radio1)).isChecked()) {
				SB.append(", sputum = 2 ");
				// SB.append(", sputum = Null ");
			}

			if (((CheckBox) vg.findViewById(R.id.chkDiffbreathing)).isChecked()) {
				SB.append(", dif_brea = 1");
				SB.append(", dt_brea = '"
						+ ((EditText) vg.findViewById(R.id.dtpDiffBreath))
								.getText().toString() + "' ");
			} else {
				SB.append(", dt_brea = Null ");
				SB.append(", dif_brea = 2 ");
			}
			if (intComp != 3 && intAge >= 5) {
				if (((CheckBox) vg.findViewById(R.id.chkSorethroat))
						.isChecked()) {
					SB.append(", s_throat = 1");
					SB.append(", dt_throa = '"
							+ ((EditText) vg.findViewById(R.id.dtpSoreThroat))
									.getText().toString() + "' ");
				} else {
					SB.append(", dt_throa = Null ");
					SB.append(", s_throat = 2 ");
				}
			} else {
				SB.append(", dt_throa = Null ");
				SB.append(", s_throat = 2 ");

			}

			if (((CheckBox) vg.findViewById(R.id.chkRunningnose)).isChecked()) {
				SB.append(", r_nose = 1");
				SB.append(", dt_nose= '"
						+ ((EditText) vg.findViewById(R.id.dtpRunningnose))
								.getText().toString() + "' ");
			} else {
				SB.append(", r_nose = 2");
				SB.append(", dt_nose= Null ");
			}

			if (intAge >= 5) {
				if (((CheckBox) vg.findViewById(R.id.chkHeadache)).isChecked()) {
					SB.append(", headache = 1");
					SB.append(", dt_head= '"
							+ ((EditText) vg.findViewById(R.id.dtpHeadache))
									.getText().toString() + "' ");
				} else {
					SB.append(", headache = 2");
					SB.append(", dt_head= Null ");
				}
			} else {
				SB.append(", headache = null");
				SB.append(", dt_head= Null ");
			}

			if (((CheckBox) vg.findViewById(R.id.chkDiarrhea)).isChecked()) {
				SB.append(", diarrhea = 1");
				SB.append(", dt_diarr= '"
						+ ((EditText) vg.findViewById(R.id.dtpDiarrhea))
								.getText().toString() + "' ");
			} else {
				SB.append(", diarrhea = 2");
				SB.append(", dt_diarr= Null ");
			}

			if (((CheckBox) vg.findViewById(R.id.chkChills)).isChecked()) {
				SB.append(", chills = 1");
				SB.append(", dt_chills= '"
						+ ((EditText) vg.findViewById(R.id.dtpChills))
								.getText().toString() + "' ");
			} else {
				SB.append(", chills = 2");
				SB.append(", dt_chills= Null ");
			}
			if (intAge >= 5) {
				if (((CheckBox) vg.findViewById(R.id.chkBodyache)).isChecked()) {
					SB.append(", bodyache = 1");
					SB.append(", dt_body= '"
							+ ((EditText) vg.findViewById(R.id.dtpBodyache))
									.getText().toString() + "' ");
				} else {
					SB.append(", bodyache = 2");
					SB.append(", dt_body= Null ");
				}
			} else {
				SB.append(", bodyache = null");
				SB.append(", dt_body= Null ");
			}
			if (((CheckBox) vg.findViewById(R.id.chkhemo)).isChecked()) {
				SB.append(", hemo = 1");
				SB.append(", dt_hemo='"
						+ ((EditText) vg.findViewById(R.id.dtpHemo)).getText()
								.toString() + "'");
			} else {
				SB.append(", hemo = 2");
				SB.append(", dt_hemo= Null ");
			}

			if (((CheckBox) vg.findViewById(R.id.chkpleur)).isChecked()) {
				SB.append(", pleur = 1");
				SB.append(", dt_peur='"
						+ ((EditText) vg.findViewById(R.id.dtppleur)).getText()
								.toString() + "'");
			} else {
				SB.append(", pleur = 2");
				SB.append(", dt_peur=Null ");
			}

			if (((CheckBox) vg.findViewById(R.id.chkOtherOne)).isChecked()) {
				SB.append(", other1 = 1");
				SB.append(", SOther1N = '"
						+ ((EditText) vg.findViewById(R.id.txtOtherOne))
								.getText().toString() + "' ");
				SB.append(", SOther1DT = '"
						+ ((EditText) vg.findViewById(R.id.dtpOtherOne))
								.getText().toString() + "' ");
			} else {
				SB.append(", other1 = 2");
				SB.append(", SOther1DT = Null ");
				SB.append(", SOther1N = null");
			}

			if (((CheckBox) vg.findViewById(R.id.chkOtherTwo)).isChecked()) {
				SB.append(", other2 = 1");
				SB.append(", SOther2N = '"
						+ ((EditText) vg.findViewById(R.id.txtOtherTwo))
								.getText().toString() + "' ");
				SB.append(", SOther2DT = '"
						+ ((EditText) vg.findViewById(R.id.dtpOtherTwo))
								.getText().toString() + "' ");
			} else {
				SB.append(", other2 = 2");
				SB.append(", SOther2DT = Null ");
				SB.append(", SOther2N = null");
			}

			if (((CheckBox) vg.findViewById(R.id.chkOtherThree)).isChecked()) {
				SB.append(", other3 = 1");
				SB.append(", SOther3DT = '"
						+ ((EditText) vg.findViewById(R.id.dtpOtherThree))
								.getText().toString() + "' ");
				SB.append(", SOther3N = '"
						+ ((EditText) vg.findViewById(R.id.txtOtherThree))
								.getText().toString() + "' ");
			} else {
				SB.append(", other3 = 2");
				SB.append(", SOther3DT = Null ");
				SB.append(", SOther3N = null");
			}

			if (intComp == 3) {
				if (((CheckBox) vg.findViewById(R.id.chkChestindraw))
						.isChecked()) {
					SB.append(", S5ChestIND = '"
							+ ((EditText) vg.findViewById(R.id.dtpChestIndraw))
									.getText().toString() + "' ");
				} else {
					SB.append(", S5ChestIND = Null ");
				}
				if (((CheckBox) vg.findViewById(R.id.chkStridor)).isChecked()) {
					SB.append(", S5Stridor = '"
							+ ((EditText) vg.findViewById(R.id.dtpStridor))
									.getText().toString() + "' ");
				} else {
					SB.append(", S5Stridor = Null ");
				}
				if (((CheckBox) vg.findViewById(R.id.chkConvulsion))
						.isChecked()) {
					SB.append(", S5Convulsion = '"
							+ ((EditText) vg.findViewById(R.id.dtpConvulsion))
									.getText().toString() + "' ");
				} else {
					SB.append(", S5Convulsion = Null ");
				}
				if (((CheckBox) vg.findViewById(R.id.chkUnableToDrink))
						.isChecked()) {
					SB.append(", S5UnDrink = '"
							+ ((EditText) vg.findViewById(R.id.dtpUnableDrink))
									.getText().toString() + "' ");
				} else {
					SB.append(", S5UnDrink = Null ");
				}
				if (((CheckBox) vg.findViewById(R.id.chkUnconsciousness))
						.isChecked()) {
					SB.append(", S5UnCons = '"
							+ ((EditText) vg
									.findViewById(R.id.dtpUnconsciousness))
									.getText().toString() + "' ");
				} else {
					SB.append(", S5UnCons = Null ");
				}
				if (((CheckBox) vg.findViewById(R.id.chkVomit)).isChecked()) {
					SB.append(", S5Vomit = '"
							+ ((EditText) vg.findViewById(R.id.dtpVomit))
									.getText().toString() + "' ");
				} else {
					SB.append(", S5Vomit = Null ");
				}

			} else {
				SB.append(", S5ChestIND = Null ");
				SB.append(", S5Stridor = Null ");
				SB.append(", S5Convulsion = Null ");
				SB.append(", S5UnDrink = Null ");
				SB.append(", S5UnCons = Null ");
				SB.append(", S5Vomit = Null ");
			}

			if (((RadioButton) ((RadioGroup) vg
					.findViewById(R.id.radioGroupsmoke))
					.findViewById(R.id.radioButton1)).isChecked())
			// if
			// ((((RadioButton)vg.findViewById(R.id.radioGroupsmoke)).findViewById(R.id.radioButton1)).isSelected())
			{
				SB.append(", Smoking = 1 ");
			} else if (((RadioButton) ((RadioGroup) vg
					.findViewById(R.id.radioGroupsmoke))
					.findViewById(R.id.radioButton2)).isChecked())
			// else if
			// //((((RadioButton)vg.findViewById(R.id.radioGroupsmoke)).findViewById(R.id.radioButton2)).isSelected())
			{
				SB.append(", Smoking = 2 ");
			} else if (((RadioButton) ((RadioGroup) vg
					.findViewById(R.id.radioGroupsmoke))
					.findViewById(R.id.radioButton3)).isChecked())// ((((RadioButton)vg.findViewById(R.id.radioGroupsmoke)).findViewById(R.id.radioButton3)).isSelected())
			{
				SB.append(", Smoking = 3 ");
			} else if (((RadioButton) ((RadioGroup) vg
					.findViewById(R.id.radioGroupsmoke))
					.findViewById(R.id.radioButton4)).isChecked())// if
																	// ((((RadioButton)vg.findViewById(R.id.radioGroupsmoke)).findViewById(R.id.radioButton4)).isSelected())
			{
				SB.append(", Smoking = 4 ");
			} else {
				SB.append(", Smoking = null ");
			}

			if (((RadioButton) ((RadioGroup) vg
					.findViewById(R.id.radioGroupLungDisease))
					.findViewById(R.id.radioButton1)).isChecked()) {
				SB.append(", LungDis = 1 ");
			} else if (((RadioButton) ((RadioGroup) vg
					.findViewById(R.id.radioGroupLungDisease))
					.findViewById(R.id.radioButton2)).isChecked()) {
				SB.append(", LungDis = 2 ");
			}

			if (((RadioButton) ((RadioGroup) vg
					.findViewById(R.id.radioGroupHeartDis))
					.findViewById(R.id.radioButton1)).isChecked()) {
				SB.append(", HeartDis = 1 ");
			} else if (((RadioButton) ((RadioGroup) vg
					.findViewById(R.id.radioGroupHeartDis))
					.findViewById(R.id.radioButton2)).isChecked()) {
				SB.append(", HeartDis = 2 ");
			}

			if (intAge > 11 && intAge < 51 && intSex == 2) {
				if (((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupPregnant))
						.findViewById(R.id.radioButton1)).isChecked()) {
					SB.append(", Pregnant = 1 ");
				} else if (((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupPregnant))
						.findViewById(R.id.radioButton2)).isChecked()) {
					SB.append(", Pregnant = 2 ");
				}

			} else {
				SB.append(", Pregnant = null ");
			}

			if (intComp == 1 || intComp == 3) {
				if (((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupVisitOPD))
						.findViewById(R.id.radioButton1)).isChecked())

				// if (optOPDYes.Checked == true)
				{
					SB.append(", VisitOPD = 1 ");
				} else if (((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupVisitOPD))
						.findViewById(R.id.radioButton2)).isChecked()) {
					SB.append(", VisitOPD = 2 ");
				}
			} else {
				SB.append(", VisitOPD = null ");
			}

			if (((Spinner) vg.findViewById(R.id.cboIllness1)).getSelectedItem()
					.toString().length() > 0) {/*
												 * .getSelectedItem().toString().
												 * substring(0, ((Spinner)
												 * vg.findViewById
												 * (R.id.cboIllness1))
												 * .getSelectedItem
												 * ().toString().
												 * lastIndexOf(":")))
												 * .trim().length() > 0)
												 */

				// if (cboIllness1.SelectedIndex != -1)

				SB.append(", chron_illness1 = '"
						+ (((Spinner) vg.findViewById(R.id.cboIllness1))
								.getSelectedItem().toString().substring(0,
								((Spinner) vg.findViewById(R.id.cboIllness1))
										.getSelectedItem().toString()
										.lastIndexOf(":"))).trim() + "' ");
			} else {
				SB.append(", chron_illness1 = null ");
			}

			if (((Spinner) vg.findViewById(R.id.cboIllness2)).getSelectedItem()
					.toString().length() > 0) {
				/*
				 * if ((((Spinner) vg.findViewById(R.id.cboIllness2))
				 * .getSelectedItem().toString().substring(0, ((Spinner)
				 * vg.findViewById(R.id.cboIllness2))
				 * .getSelectedItem().toString().lastIndexOf(":")))
				 * .trim().length() > 0)
				 */
				SB.append(", chron_illness2 = '"
						+ (((Spinner) vg.findViewById(R.id.cboIllness2))
								.getSelectedItem().toString().substring(0,
								((Spinner) vg.findViewById(R.id.cboIllness2))
										.getSelectedItem().toString()
										.lastIndexOf(":"))).trim() + "' ");
			} else {
				SB.append(", chron_illness2 = null ");
			}

			if (((Spinner) vg.findViewById(R.id.cboIllness3)).getSelectedItem()
					.toString().length() > 0) {
				/*
				 * if ((((Spinner) vg.findViewById(R.id.cboIllness3))
				 * .getSelectedItem().toString().substring(0, ((Spinner)
				 * vg.findViewById(R.id.cboIllness3))
				 * .getSelectedItem().toString().lastIndexOf(":")))
				 * .trim().length() > 0) {
				 */
				SB.append(", chron_illness3 = '"
						+ (((Spinner) vg.findViewById(R.id.cboIllness3))
								.getSelectedItem().toString().substring(0,
								((Spinner) vg.findViewById(R.id.cboIllness3))
										.getSelectedItem().toString()
										.lastIndexOf(":"))).trim() + "' ");
			} else {
				SB.append(", chron_illness3 = null ");
			}
			if (((Spinner) vg.findViewById(R.id.cboIllness1)).getSelectedItem()
					.toString().contains("99")
					|| (((Spinner) vg.findViewById(R.id.cboIllness2))
							.getSelectedItem().toString().contains("99"))
					|| ((Spinner) vg.findViewById(R.id.cboIllness3))
							.getSelectedItem().toString().contains("99")) {
				SB.append(", chron_illnessOther = '"
						+ ((EditText) vg.findViewById(R.id.txtChronicILLOther))
								.getText().toString() + "' ");
			} else {
				SB.append(", chron_illnessOther = null ");
			}

			if (((RadioButton) ((RadioGroup) vg
					.findViewById(R.id.radioGroupTacMed))
					.findViewById(R.id.radioButton1)).isChecked() == true) {
				SB.append(" , IsDrug_chronillness = 1 ");

				if (((Spinner) vg.findViewById(R.id.cboTacMedicine1))
						.getSelectedItem().toString().length() > 0) {
					SB.append(", Drug1_chronillness1 = '"
							+ CommonStaticClass.GetSpinnerValue((((Spinner) vg
									.findViewById(R.id.cboTacMedicine1))))
							+ "'");
				}
				if (((Spinner) vg.findViewById(R.id.cboTacMedicine2))
						.getSelectedItem().toString().length() > 0) {
					SB.append(", Drug2_chronillness2 = '"
							+ CommonStaticClass.GetSpinnerValue((((Spinner) vg
									.findViewById(R.id.cboTacMedicine2))))
							+ "'");

				}
				if (((Spinner) vg.findViewById(R.id.cboTacMedicine3))
						.getSelectedItem().toString().length() > 0) {
					SB.append(", Drug3_chronillness3 = '"
							+ CommonStaticClass.GetSpinnerValue((((Spinner) vg
									.findViewById(R.id.cboTacMedicine3))))
							+ "'");
				}
			} else if (((RadioButton) ((RadioGroup) vg
					.findViewById(R.id.radioGroupTacMed))
					.findViewById(R.id.radioButton2)).isChecked() == true) {
				SB.append(" , IsDrug_chronillness = 0");
				SB.append(", Drug1_chronillness1 = null");
				SB.append(", Drug2_chronillness2 = null");
				SB.append(", Drug3_chronillness3 = null");

			}

			if (((RadioButton) ((RadioGroup) vg
					.findViewById(R.id.radioGroupPneumonia))
					.findViewById(R.id.radioButton1)).isChecked())
			// if (rbPneumoniaYes.Checked == true)
			{
				SB.append(", hx_pneumo = '1'");
			} else if (((RadioButton) ((RadioGroup) vg
					.findViewById(R.id.radioGroupPneumonia))
					.findViewById(R.id.radioButton2)).isChecked()) {
				SB.append(", hx_pneumo = '2'");
			} else if (((RadioButton) ((RadioGroup) vg
					.findViewById(R.id.radioGroupPneumonia))
					.findViewById(R.id.radioButton3)).isChecked()) {
				SB.append(", hx_pneumo = '9'");
			} else {
				SB.append(", hx_pneumo = null");

			}

			if (((RadioButton) ((RadioGroup) vg
					.findViewById(R.id.radioGrouppriorHosp))
					.findViewById(R.id.radioButton1)).isChecked())
			// if (optpriorHospY.Checked == true)
			{
				SB.append(", prior_hosp = 1 ");
			} else if (((RadioButton) ((RadioGroup) vg
					.findViewById(R.id.radioGrouppriorHosp))
					.findViewById(R.id.radioButton2)).isChecked()) {
				SB.append(", prior_hosp = 2 ");
			} else if (((RadioButton) ((RadioGroup) vg
					.findViewById(R.id.radioGrouppriorHosp))
					.findViewById(R.id.radioButton3)).isChecked()) {
				SB.append(", prior_hosp = 99");
			} else {
				SB.append(", prior_hosp = null");
			}

			if ((((RadioButton) ((RadioGroup) vg
					.findViewById(R.id.radioGrouppriorHosp))
					.findViewById(R.id.radioButton2)).isChecked())
					&& ((RadioButton) ((RadioGroup) vg
							.findViewById(R.id.radioGroupPneumonia))
							.findViewById(R.id.radioButton2)).isChecked()) {
				if (((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupTac))
						.findViewById(R.id.radioButton1)).isChecked())
				// if (OptTacY.Checked == true)
				{
					SB.append(", IsTac = '1'");
				} else if (((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupTac))
						.findViewById(R.id.radioButton2)).isChecked()) {
					SB.append(", IsTac = '0'");
				}

			} else {
				SB.append(", IsTac = null ");
			}

			SB.append(", EditBy = '" + CommonStaticClass.userSpecificId + "' ");
			SB.append(", EditDate = '" + CommonStaticClass.GetDate() + "' ");

			SB.append(" where dataid = '" + CommonStaticClass.dataId + "'");

			if (dbHelper.executeDMLQuery(SB.toString())) {

				CommonStaticClass.findOutNextSLNo(
						CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQvar(),
						CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQnext1());
				CommonStaticClass.nextQuestion(ParentActivity.this);
			}

		} catch (Exception ex) {
			CommonStaticClass.showMyAlert(thisactivity, "Error",
					"Error in Update String Building");
			// MessageBox.Show(ex.Message, "Error in Update String Building",
			// MessageBoxButtons.OK, MessageBoxIcon.Asterisk,
			// MessageBoxDefaultButton.Button1);
		} finally {
		}

	}

	private void loadDataFrmSymptom(ViewGroup v) {

		final ViewGroup vg = v;
		HideViews(v);
		String sql = "SELECT '' AS DrCode UNION SELECT (" + "" + "DrCode" + ""
				+ "|| " + "" + "' : '" + " || " + "Name" + ") AS " + "D"
				+ " from DrugChronicList WHERE DrCode <> '0' ORDER BY DrCode"
				+ "";

		CommonStaticClass.FillCombo(thisactivity, dbHelper, sql,
				((Spinner) v.findViewById(R.id.cboTacMedicine1)));

		sql = "SELECT '' AS DrCode UNION SELECT (" + "" + "DrCode" + "" + "|| "
				+ "" + "' : '" + " || " + "Name" + ") AS " + "D"
				+ " from DrugChronicList ORDER BY DrCode" + "";

		CommonStaticClass.FillCombo(thisactivity, dbHelper, sql,
				((Spinner) v.findViewById(R.id.cboTacMedicine2)));

		CommonStaticClass.FillCombo(thisactivity, dbHelper, sql,
				((Spinner) v.findViewById(R.id.cboTacMedicine3)));

		ArrayList<String> ids = new ArrayList<String>();
		ids.add("");
		ids.add("1 : Subjective");
		ids.add("2 : Measured");

		CommonStaticClass.FillCombo(thisactivity, ids,
				((Spinner) v.findViewById(R.id.cboSfever)));
		ids = new ArrayList<String>();
		ids.add("");
		ids.add("1 : Asthma");
		ids.add("2 : COPD/Chronic bronchitis/Emphysema");
		ids.add("3 : Diabetes");
		ids.add("4 : Hypertension");
		ids.add("5 : Ishchaemic Heart Diseases");
		ids.add("6 : Cancer");
		ids.add("7 : Malaria");
		ids.add("8 : Kideney disease");
		ids.add("9 : liver disease");
		ids.add("10 : HIV/AIDS");
		ids.add("99 : Others");
		ids.add("0 : No");

		CommonStaticClass.FillCombo(thisactivity, ids,
				((Spinner) v.findViewById(R.id.cboIllness1)));
		CommonStaticClass.FillCombo(thisactivity, ids,
				((Spinner) v.findViewById(R.id.cboIllness2)));
		CommonStaticClass.FillCombo(thisactivity, ids,
				((Spinner) v.findViewById(R.id.cboIllness3)));

		SetCheckBox((CheckBox) v.findViewById(R.id.chkFever),
				(EditText) v.findViewById(R.id.dtpFever), v);

		((CheckBox) v.findViewById(R.id.chkMeasureFever))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {

							((Spinner) vg.findViewById(R.id.cboSfever))
									.setVisibility(View.VISIBLE);

							((TextView) vg.findViewById(R.id.lbltemp))
									.setVisibility(View.VISIBLE);
							if (((CheckBox) vg.findViewById(R.id.chkFever))
									.isChecked()) {

							} else {
								((CheckBox) vg.findViewById(R.id.chkFever))
										.setChecked(true);
							}
						}

						else {
							((Spinner) vg.findViewById(R.id.cboSfever))
									.setVisibility(View.GONE);
							((TextView) vg.findViewById(R.id.lbltemp))
									.setVisibility(View.GONE);

							/*
							 * if (((CheckBox) vg.findViewById(R.id.chkFever))
							 * .isChecked()) { ((CheckBox)
							 * vg.findViewById(R.id.chkFever))
							 * .setChecked(true); }
							 */
							if (((CheckBox) vg.findViewById(R.id.chkFever))
									.isChecked()) {
								((CheckBox) vg
										.findViewById(R.id.chkMeasureFever))
										.setChecked(true);
								((Spinner) vg.findViewById(R.id.cboSfever))
										.setVisibility(View.VISIBLE);
								((TextView) vg.findViewById(R.id.lbltemp))
										.setVisibility(View.VISIBLE);
							}
						}
					}
				});
		((RadioGroup) v.findViewById(R.id.radioGroupPneumonia))
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {

						if (((RadioButton) group
								.findViewById(R.id.radioButton2)).isChecked() == true) {
							if (((RadioButton) ((RadioGroup) vg
									.findViewById(R.id.radioGrouppriorHosp))
									.findViewById(R.id.radioButton2))
									.isChecked()) {
								((TextView) vg.findViewById(R.id.lbltac))
										.setVisibility(View.VISIBLE);
								((RadioGroup) vg
										.findViewById(R.id.radioGroupTac))
										.setVisibility(View.VISIBLE);
							} else {
								((TextView) vg.findViewById(R.id.lbltac))
										.setVisibility(View.GONE);
								((RadioGroup) vg
										.findViewById(R.id.radioGroupTac))
										.setVisibility(View.GONE);
							}
						} else {
							((TextView) vg.findViewById(R.id.lbltac))
									.setVisibility(View.GONE);
							((RadioGroup) vg.findViewById(R.id.radioGroupTac))
									.setVisibility(View.GONE);
						}
					}
				});

		((RadioGroup) v.findViewById(R.id.radioGrouppriorHosp))
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {

						if (((RadioButton) group
								.findViewById(R.id.radioButton2)).isChecked() == true) {
							if (((RadioButton) ((RadioGroup) vg
									.findViewById(R.id.radioGroupPneumonia))
									.findViewById(R.id.radioButton2))
									.isChecked()) {
								((TextView) vg.findViewById(R.id.lbltac))
										.setVisibility(View.VISIBLE);
								((RadioGroup) vg
										.findViewById(R.id.radioGroupTac))
										.setVisibility(View.VISIBLE);
							} else {
								((TextView) vg.findViewById(R.id.lbltac))
										.setVisibility(View.GONE);
								((RadioGroup) vg
										.findViewById(R.id.radioGroupTac))
										.setVisibility(View.GONE);
							}
						} else {
							((TextView) vg.findViewById(R.id.lbltac))
									.setVisibility(View.GONE);
							((RadioGroup) vg.findViewById(R.id.radioGroupTac))
									.setVisibility(View.GONE);
						}
					}
				});

		SetCheckBox((CheckBox) v.findViewById(R.id.chkCough),
				(EditText) v.findViewById(R.id.dtpCough), v);
		SetCheckBox((CheckBox) v.findViewById(R.id.chkDiffbreathing),
				(EditText) v.findViewById(R.id.dtpDiffBreath), v);
		SetCheckBox((CheckBox) v.findViewById(R.id.chkSorethroat),
				(EditText) v.findViewById(R.id.dtpSoreThroat), v);

		SetCheckBox((CheckBox) v.findViewById(R.id.chkRunningnose),
				(EditText) v.findViewById(R.id.dtpRunningnose), v);
		SetCheckBox((CheckBox) v.findViewById(R.id.chkHeadache),
				(EditText) v.findViewById(R.id.dtpHeadache), v);
		SetCheckBox((CheckBox) v.findViewById(R.id.chkDiarrhea),
				(EditText) v.findViewById(R.id.dtpDiarrhea), v);
		SetCheckBox((CheckBox) v.findViewById(R.id.chkChills),
				(EditText) v.findViewById(R.id.dtpChills), v);
		SetCheckBox((CheckBox) v.findViewById(R.id.chkBodyache),
				(EditText) v.findViewById(R.id.dtpBodyache), v);
		SetCheckBox((CheckBox) v.findViewById(R.id.chkhemo),
				(EditText) v.findViewById(R.id.dtpHemo), v);
		SetCheckBox((CheckBox) v.findViewById(R.id.chkpleur),
				(EditText) v.findViewById(R.id.dtppleur), v);

		SetCheckBox((CheckBox) v.findViewById(R.id.chkOtherOne),
				(EditText) v.findViewById(R.id.txtOtherOne), v);

		SetCheckBox((CheckBox) v.findViewById(R.id.chkOtherTwo),
				(EditText) v.findViewById(R.id.txtOtherTwo), v);
		SetCheckBox((CheckBox) v.findViewById(R.id.chkOtherThree),
				(EditText) v.findViewById(R.id.txtOtherThree), v);

		SetCheckBox((CheckBox) v.findViewById(R.id.chkChestindraw),
				(EditText) v.findViewById(R.id.dtpChestIndraw), v);
		SetCheckBox((CheckBox) v.findViewById(R.id.chkStridor),
				(EditText) v.findViewById(R.id.dtpStridor), v);
		SetCheckBox((CheckBox) v.findViewById(R.id.chkConvulsion),
				(EditText) v.findViewById(R.id.dtpConvulsion), v);
		SetCheckBox((CheckBox) v.findViewById(R.id.chkUnableToDrink),
				(EditText) v.findViewById(R.id.dtpUnableDrink), v);
		SetCheckBox((CheckBox) v.findViewById(R.id.chkUnconsciousness),
				(EditText) v.findViewById(R.id.dtpUnconsciousness), v);
		SetCheckBox((CheckBox) v.findViewById(R.id.chkVomit),
				(EditText) v.findViewById(R.id.dtpVomit), v);

		SetDate((EditText) v.findViewById(R.id.dtpFever));

		SetDate((EditText) v.findViewById(R.id.dtpCough));
		SetDate((EditText) v.findViewById(R.id.dtpDiffBreath));
		SetDate((EditText) v.findViewById(R.id.dtpSoreThroat));
		SetDate((EditText) v.findViewById(R.id.dtpRunningnose));
		SetDate((EditText) v.findViewById(R.id.dtpHeadache));
		SetDate((EditText) v.findViewById(R.id.dtpDiarrhea));
		SetDate((EditText) v.findViewById(R.id.dtpChills));
		SetDate((EditText) v.findViewById(R.id.dtpBodyache));
		SetDate((EditText) v.findViewById(R.id.dtpHemo));
		SetDate((EditText) v.findViewById(R.id.dtppleur));

		SetDate((EditText) v.findViewById(R.id.dtpOtherOne));
		SetDate((EditText) v.findViewById(R.id.dtpOtherTwo));
		SetDate((EditText) v.findViewById(R.id.dtpOtherThree));

		SetDate((EditText) v.findViewById(R.id.dtpChestIndraw));
		SetDate((EditText) v.findViewById(R.id.dtpStridor));
		SetDate((EditText) v.findViewById(R.id.dtpConvulsion));
		SetDate((EditText) v.findViewById(R.id.dtpUnableDrink));
		SetDate((EditText) v.findViewById(R.id.dtpUnconsciousness));
		SetDate((EditText) v.findViewById(R.id.dtpVomit));

		((RadioGroup) v.findViewById(R.id.radioGroupTacMed))
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {

						if (((RadioButton) group
								.findViewById(R.id.radioButton1)).isChecked() == true) {
							((Spinner) vg.findViewById(R.id.cboTacMedicine1))
									.setVisibility(View.VISIBLE);

							((TextView) vg.findViewById(R.id.lbldrug1))
									.setVisibility(View.VISIBLE);

							/*
							 * ((Spinner) vg.findViewById(R.id.cboTacMedicine2))
							 * .setVisibility(View.VISIBLE); ((Spinner)
							 * vg.findViewById(R.id.cboTacMedicine3))
							 * .setVisibility(View.VISIBLE);
							 */

						} else {
							((Spinner) vg.findViewById(R.id.cboTacMedicine1))
									.setVisibility(View.GONE);
							((Spinner) vg.findViewById(R.id.cboTacMedicine2))
									.setVisibility(View.GONE);
							((Spinner) vg.findViewById(R.id.cboTacMedicine3))
									.setVisibility(View.GONE);
							((TextView) vg.findViewById(R.id.lbldrug1))
									.setVisibility(View.GONE);
						}

					}
				});

		((Spinner) vg.findViewById(R.id.cboSfever))
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
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
													.lastIndexOf(":"))).trim();
							if (sResCode.length() > 0) {
								if (sResCode.equalsIgnoreCase("2")) {
									((EditText) vg
											.findViewById(R.id.txtFeverTemp))
											.setVisibility(View.VISIBLE);
									((TextView) vg.findViewById(R.id.lbltemp))
											.setVisibility(View.VISIBLE);

								} else {
									((EditText) vg
											.findViewById(R.id.txtFeverTemp))
											.setVisibility(View.GONE);
									((TextView) vg.findViewById(R.id.lbltemp))
											.setVisibility(View.GONE);
								}
							}

						}

					}

					public void onNothingSelected(AdapterView<?> arg0) {

					}
				});

		((Spinner) vg.findViewById(R.id.cboIllness1))
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
													.lastIndexOf(":"))).trim();
							if (sResCode.length() > 0) {
								((EditText) vg
										.findViewById(R.id.txtChronicILLOther))
										.setVisibility(View.GONE);
								/*
								 * (((RadioButton) ((RadioGroup) vg
								 * .findViewById(R.id.radioGroupTacMed))
								 * .findViewById(R.id.radioButton1)))
								 * .setChecked(true);
								 */

								if (sResCode.equalsIgnoreCase("99")) {
									((EditText) vg
											.findViewById(R.id.txtChronicILLOther))
											.setVisibility(View.VISIBLE);
									((Spinner) vg
											.findViewById(R.id.cboIllness2))
											.setVisibility(View.GONE);
									((Spinner) vg
											.findViewById(R.id.cboIllness3))
											.setVisibility(View.GONE);
									((TextView) vg
											.findViewById(R.id.lblillness2))
											.setVisibility(View.GONE);
									((TextView) vg
											.findViewById(R.id.lblillness3))
											.setVisibility(View.GONE);
									((TextView) vg
											.findViewById(R.id.lblillnessother))
											.setVisibility(View.VISIBLE);

									/*
									 * for (int i = 0; i < ((Spinner) vg
									 * .findViewById(R.id.cboIllness2))
									 * .getCount(); i++) { if (parent
									 * .getItemAtPosition(i) .toString()
									 * .substring( 0, (parent.getItemAtPosition(
									 * i).toString() .lastIndexOf(":")))
									 * .trim().equalsIgnoreCase("99")) {
									 * 
									 * ((Spinner) vg
									 * .findViewById(R.id.cboIllness2))
									 * .setSelection(i); } }
									 * 
									 * for (int i = 0; i < ((Spinner) vg
									 * .findViewById(R.id.cboIllness2))
									 * .getCount(); i++) { if (parent
									 * .getItemAtPosition(i) .toString()
									 * .substring( 0, (parent.getItemAtPosition(
									 * i).toString() .lastIndexOf(":")))
									 * .trim().equalsIgnoreCase("99")) {
									 * 
									 * ((Spinner) vg
									 * .findViewById(R.id.cboIllness2))
									 * .setSelection(i); } }
									 */

								}

								else if (sResCode.equalsIgnoreCase("0"))

								{
									((EditText) vg
											.findViewById(R.id.txtChronicILLOther))
											.setVisibility(View.GONE);
									((Spinner) vg
											.findViewById(R.id.cboIllness2))
											.setVisibility(View.GONE);
									((Spinner) vg
											.findViewById(R.id.cboIllness3))
											.setVisibility(View.GONE);
									((TextView) vg
											.findViewById(R.id.lblillness2))
											.setVisibility(View.GONE);
									((TextView) vg
											.findViewById(R.id.lblillness3))
											.setVisibility(View.GONE);
									((TextView) vg
											.findViewById(R.id.lblillnessother))
											.setVisibility(View.GONE);

								} else if (!sResCode.equalsIgnoreCase("0")
										|| !sResCode.equalsIgnoreCase("99")) {
									((Spinner) vg
											.findViewById(R.id.cboIllness2))
											.setVisibility(View.VISIBLE);

									((TextView) vg
											.findViewById(R.id.lblillness2))
											.setVisibility(View.VISIBLE);

								} else {
									((EditText) vg
											.findViewById(R.id.txtChronicILLOther))
											.setVisibility(View.GONE);
									((Spinner) vg
											.findViewById(R.id.cboIllness2))
											.setVisibility(View.GONE);
									((Spinner) vg
											.findViewById(R.id.cboIllness3))
											.setVisibility(View.GONE);
									((TextView) vg
											.findViewById(R.id.lblillness2))
											.setVisibility(View.GONE);
									((TextView) vg
											.findViewById(R.id.lblillness3))
											.setVisibility(View.GONE);
									((TextView) vg
											.findViewById(R.id.lblillnessother))
											.setVisibility(View.GONE);
								}
							}

							/*
							 * else if (!parent .getItemAtPosition(pos)
							 * .toString() .substring( 0,
							 * (parent.getItemAtPosition(pos) .toString()
							 * .lastIndexOf(":"))).trim()
							 * .equalsIgnoreCase("10") && ((Spinner)
							 * findViewById(R.id.cboIllness2))
							 * .getSelectedItem() .toString() .substring( 0,
							 * (((Spinner) findViewById(R.id.cboIllness2))
							 * .toString() .lastIndexOf(":")))
							 * .trim().equalsIgnoreCase("10")
							 * 
							 * && ((Spinner) vg .findViewById(R.id.cboIllness3))
							 * .getSelectedItem() .toString() .substring( 0,
							 * (((Spinner) vg .findViewById(R.id.cboIllness3))
							 * .toString() .lastIndexOf(":")))
							 * .trim().equalsIgnoreCase("10")) {
							 * 
							 * ((EditText) vg
							 * .findViewById(R.id.txtChronicILLOther))
							 * .setVisibility(View.GONE); }
							 */
						}
					}

					public void onNothingSelected(AdapterView<?> arg0) {

					}
				});

		((Spinner) vg.findViewById(R.id.cboIllness2))
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
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
													.lastIndexOf(":"))).trim();
							if (sResCode.length() > 0) {
								/*
								 * ((EditText) vg
								 * .findViewById(R.id.txtChronicILLOther))
								 * .setVisibility(View.GONE);
								 */
								if (sResCode.equalsIgnoreCase("99")) {
									((EditText) vg
											.findViewById(R.id.txtChronicILLOther))
											.setVisibility(View.VISIBLE);

									((TextView) vg
											.findViewById(R.id.lblillnessother))
											.setVisibility(View.VISIBLE);

									/*
									 * for (int i = 0; i < ((Spinner) vg
									 * .findViewById(R.id.cboIllness1))
									 * .getCount(); i++) { if (parent
									 * .getItemAtPosition(i) .toString()
									 * .substring( 0, (parent.getItemAtPosition(
									 * i).toString() .lastIndexOf(":")))
									 * .trim().equalsIgnoreCase("99")) {
									 * 
									 * ((Spinner) vg
									 * .findViewById(R.id.cboIllness1))
									 * .setSelection(i); } }
									 * 
									 * for (int i = 0; i < ((Spinner) vg
									 * .findViewById(R.id.cboIllness3))
									 * .getCount(); i++) { if (parent
									 * .getItemAtPosition(i) .toString()
									 * .substring( 0, (parent.getItemAtPosition(
									 * i).toString() .lastIndexOf(":")))
									 * .trim().equalsIgnoreCase("99")) {
									 * 
									 * ((Spinner) vg
									 * .findViewById(R.id.cboIllness3))
									 * .setSelection(i); } }
									 */

								}

								else if (sResCode.equalsIgnoreCase("0"))

								{
									((Spinner) vg
											.findViewById(R.id.cboIllness3))
											.setVisibility(View.GONE);

									((EditText) vg
											.findViewById(R.id.txtChronicILLOther))
											.setVisibility(View.GONE);

									((TextView) vg
											.findViewById(R.id.lblillness3))
											.setVisibility(View.GONE);
									((TextView) vg
											.findViewById(R.id.lblillnessother))
											.setVisibility(View.GONE);

								}

								else if (!sResCode.equalsIgnoreCase("0")
										|| !sResCode.equalsIgnoreCase("99")) {
									((Spinner) vg
											.findViewById(R.id.cboIllness3))
											.setVisibility(View.VISIBLE);

									((TextView) vg
											.findViewById(R.id.lblillness3))
											.setVisibility(View.VISIBLE);

									/*
									 * ((EditText) vg
									 * .findViewById(R.id.txtChronicILLOther))
									 * .setVisibility(View.GONE);
									 */
								}

							} /*
							 * else if (!parent .getItemAtPosition(pos)
							 * .toString() .substring( 0,
							 * (parent.getItemAtPosition(pos) .toString()
							 * .lastIndexOf(":"))).trim()
							 * .equalsIgnoreCase("10")
							 * 
							 * && ((Spinner) findViewById(R.id.cboIllness1))
							 * .getSelectedItem() .toString() .substring( 0,
							 * (((Spinner) findViewById(R.id.cboIllness1))
							 * .toString() .lastIndexOf(":")))
							 * .trim().equalsIgnoreCase("10")
							 * 
							 * && ((Spinner) findViewById(R.id.cboIllness3))
							 * .getSelectedItem() .toString() .substring( 0,
							 * (((Spinner) findViewById(R.id.cboIllness3))
							 * .toString() .lastIndexOf(":")))
							 * .trim().equalsIgnoreCase("10")) {
							 * 
							 * ((EditText) vg
							 * .findViewById(R.id.txtChronicILLOther))
							 * .setVisibility(View.GONE); }
							 */
						}

					}

					public void onNothingSelected(AdapterView<?> arg0) {

					}
				});

		((Spinner) vg.findViewById(R.id.cboIllness3))
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
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
													.lastIndexOf(":"))).trim();
							if (sResCode.length() > 0) {
								/*
								 * ((EditText) vg
								 * .findViewById(R.id.txtChronicILLOther))
								 * .setVisibility(View.GONE);
								 */
								if (sResCode.equalsIgnoreCase("99")) {
									((EditText) vg
											.findViewById(R.id.txtChronicILLOther))
											.setVisibility(View.VISIBLE);

									((TextView) vg
											.findViewById(R.id.lblillnessother))
											.setVisibility(View.VISIBLE);

									/*
									 * for (int i = 0; i < ((Spinner) vg
									 * .findViewById(R.id.cboIllness1))
									 * .getCount(); i++) { if (parent
									 * .getItemAtPosition(i) .toString()
									 * .substring( 0, (parent.getItemAtPosition(
									 * i).toString() .lastIndexOf(":")))
									 * .trim().equalsIgnoreCase("99")) {
									 * 
									 * ((Spinner) vg
									 * .findViewById(R.id.cboIllness1))
									 * .setSelection(i); } }
									 * 
									 * for (int i = 0; i < ((Spinner) vg
									 * .findViewById(R.id.cboIllness2))
									 * .getCount(); i++) { if (parent
									 * .getItemAtPosition(i) .toString()
									 * .substring( 0, (parent.getItemAtPosition(
									 * i).toString() .lastIndexOf(":")))
									 * .trim().equalsIgnoreCase("99")) {
									 * 
									 * ((Spinner) vg
									 * .findViewById(R.id.cboIllness2))
									 * .setSelection(i); } }
									 */

								}

								else if (sResCode.equalsIgnoreCase("0"))

								{
									((EditText) vg
											.findViewById(R.id.txtChronicILLOther))
											.setVisibility(View.GONE);

									((TextView) vg
											.findViewById(R.id.lblillnessother))
											.setVisibility(View.GONE);

								}

								/*
								 * else { ((EditText) vg
								 * .findViewById(R.id.txtChronicILLOther))
								 * .setVisibility(View.GONE); }
								 */

							}

							/*
							 * else if (!parent .getItemAtPosition(pos)
							 * .toString() .substring( 0,
							 * (parent.getItemAtPosition(pos) .toString()
							 * .lastIndexOf(":"))).trim()
							 * .equalsIgnoreCase("10")
							 * 
							 * && ((Spinner) findViewById(R.id.cboIllness1))
							 * .getSelectedItem() .toString() .substring( 0,
							 * (((Spinner) findViewById(R.id.cboIllness1))
							 * .toString() .lastIndexOf(":")))
							 * .trim().equalsIgnoreCase("10")
							 * 
							 * && ((Spinner) findViewById(R.id.cboIllness2))
							 * .getSelectedItem() .toString() .substring( 0,
							 * (((Spinner) findViewById(R.id.cboIllness2))
							 * .toString() .lastIndexOf(":")))
							 * .trim().equalsIgnoreCase("10")) {
							 * 
							 * ((EditText) vg
							 * .findViewById(R.id.txtChronicILLOther))
							 * .setVisibility(View.GONE); }
							 */
						}

					}

					public void onNothingSelected(AdapterView<?> arg0) {

					}
				});

		((Spinner) vg.findViewById(R.id.cboTacMedicine1))
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int pos, long id) {

						if (parent.getItemAtPosition(pos).toString().length() > 0) {

							sResCode = CommonStaticClass.GetSpinnerValue(((Spinner) vg
									.findViewById(R.id.cboTacMedicine1)));/*
																		 * parent
																		 * .
																		 * getItemAtPosition
																		 * (pos)
																		 * .
																		 * toString
																		 * () .
																		 * substring
																		 * ( 0,
																		 * (
																		 * parent
																		 * .
																		 * getItemAtPosition
																		 * (pos)
																		 * .
																		 * toString
																		 * () .
																		 * lastIndexOf
																		 * (
																		 * ":"))
																		 * )
																		 * .trim
																		 * ();
																		 */
							if (sResCode.length() > 0) {
								if (sResCode.equalsIgnoreCase("888")) {

									((TextView) vg.findViewById(R.id.lbldrug1))
											.setVisibility(View.VISIBLE);

									/*
									 * ((TextView)
									 * vg.findViewById(R.id.lbldrug1))
									 * .setVisibility(View.GONE);
									 */
									((Spinner) vg
											.findViewById(R.id.cboTacMedicine2))
											.setVisibility(View.GONE);

									((TextView) vg.findViewById(R.id.lbldrug3))
											.setVisibility(View.GONE);
									((Spinner) vg
											.findViewById(R.id.cboTacMedicine3))
											.setVisibility(View.GONE);
								}

								if (sResCode.equalsIgnoreCase("0")) {
									CommonStaticClass.showMyAlert(thisactivity,
											"Selection Error",
											"You can not select no");
									((Spinner) vg
											.findViewById(R.id.cboTacMedicine1))
											.setSelection(0);
									((RadioButton) ((RadioGroup) vg
											.findViewById(R.id.radioGroupTacMed))
											.findViewById(R.id.radioButton2))
											.setChecked(true);

									((TextView) vg.findViewById(R.id.lbldrug1))
											.setVisibility(View.VISIBLE);

									((TextView) vg.findViewById(R.id.lbldrug2))
											.setVisibility(View.GONE);
									((Spinner) vg
											.findViewById(R.id.cboTacMedicine2))
											.setVisibility(View.GONE);

									((TextView) vg.findViewById(R.id.lbldrug3))
											.setVisibility(View.GONE);
									((Spinner) vg
											.findViewById(R.id.cboTacMedicine3))
											.setVisibility(View.GONE);
								}

								else {

									((TextView) vg.findViewById(R.id.lbldrug1))
											.setVisibility(View.VISIBLE);

									((TextView) vg.findViewById(R.id.lbldrug2))
											.setVisibility(View.VISIBLE);
									((Spinner) vg
											.findViewById(R.id.cboTacMedicine2))
											.setVisibility(View.VISIBLE);
								}

							}
						}

					}

					public void onNothingSelected(AdapterView<?> arg0) {

					}
				});

		((Spinner) vg.findViewById(R.id.cboTacMedicine2))
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
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
													.lastIndexOf(":"))).trim();
							if (sResCode.length() > 0) {
								if (sResCode.equalsIgnoreCase("888")) {

									((TextView) vg.findViewById(R.id.lbldrug3))
											.setVisibility(View.GONE);
									((Spinner) vg
											.findViewById(R.id.cboTacMedicine3))
											.setVisibility(View.GONE);
								}
								if (sResCode.equalsIgnoreCase("0")) {
									/*
									 * ((TextView)vg.findViewById(R.id.lbldrug2))
									 * .setVisibility(View.GONE); ((Spinner)
									 * vg.findViewById
									 * (R.id.cboTacMedicine2)).setVisibility
									 * (View.GONE);
									 */

									((TextView) vg.findViewById(R.id.lbldrug3))
											.setVisibility(View.GONE);
									((Spinner) vg
											.findViewById(R.id.cboTacMedicine3))
											.setVisibility(View.GONE);
								} else {
									((TextView) vg.findViewById(R.id.lbldrug3))
											.setVisibility(View.VISIBLE);
									((Spinner) vg
											.findViewById(R.id.cboTacMedicine3))
											.setVisibility(View.VISIBLE);
								}

							}
						}

					}

					public void onNothingSelected(AdapterView<?> arg0) {

					}
				});

		intAge = Integer.parseInt(CommonStaticClass.getSkip("AgeY",
				"tblMainQues", dbHelper));
		intSex = Integer.parseInt(CommonStaticClass.getSkip("Sex",
				"tblMainQues", dbHelper));
		intComp = Integer.parseInt(CommonStaticClass.getSkip("Comp",
				"tblMainQues", dbHelper));

		GetCathmanArea();

		try {
			String strSQL = "Select * from tblMainQues where dataid = '"
					+ CommonStaticClass.dataId + "'";
			Cursor mCursor = null;

			try {

				mCursor = dbHelper.getQueryCursor(strSQL);

				if (mCursor.getCount() > 0) {

					if (mCursor.moveToFirst()) {

						do {

							if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"Fever").toString()
									.equalsIgnoreCase("2")) {
								((CheckBox) v.findViewById(R.id.chkFever))
										.setChecked(false);
							}

							if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"Fever").equalsIgnoreCase("1")) {
								((CheckBox) v.findViewById(R.id.chkFever))
										.setChecked(true);
								((EditText) v.findViewById(R.id.dtpFever))
										.setText(CommonStaticClass
												.CheckCursorValueWithNullHandler(
														mCursor, "dt_fever")
												.toString());

								CommonStaticClass
										.SetSpinnerValue(
												thisactivity,
												((Spinner) v
														.findViewById(R.id.cboSfever)),
												CommonStaticClass
														.CheckCursorValueWithNullHandler(
																mCursor,
																"fever_meas_subj")
														.toString().trim());

								/*
								 * ((EditText)
								 * v.findViewById(R.id.dtpFever)).setVisibility
								 * (View.VISIBLE);
								 */
							} else {
								((CheckBox) v.findViewById(R.id.chkFever))
										.setChecked(false);
							}

							if (!CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"fever_meas_subj")
									.equalsIgnoreCase("")) {
								((CheckBox) v
										.findViewById(R.id.chkMeasureFever))
										.setChecked(false);

							}

							if (!CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"fever_meas_subj")
									.equalsIgnoreCase("")) {
								CommonStaticClass
										.SetSpinnerValue(
												thisactivity,
												((Spinner) v
														.findViewById(R.id.cboSfever)),
												CommonStaticClass
														.CheckCursorValueWithNullHandler(
																mCursor,
																"fever_meas_subj")
														.toString().trim());
								((CheckBox) v
										.findViewById(R.id.chkMeasureFever))
										.setChecked(true);
								((EditText) v.findViewById(R.id.txtFeverTemp))
										.setText(CommonStaticClass
												.CheckCursorValueWithNullHandler(
														mCursor, "fever_temp"));

								/*
								 * for (int i = 0; i < ((Spinner) v
								 * .findViewById(R.id.cboSfever)) .getCount();
								 * i++) {
								 * 
								 * if (CommonStaticClass
								 * .CheckCursorValueWithNullHandler( mCursor,
								 * "fever_meas_subj") .equalsIgnoreCase(
								 * ((Spinner) v .findViewById(R.id.cboSfever))
								 * .getItemAtPosition( i) .toString()
								 * .substring( 0, (((Spinner) v
								 * .findViewById(R.id.cboSfever))
								 * .getItemAtPosition( i) .toString()
								 * .lastIndexOf(":"))) .trim().toString()))
								 * 
								 * {
								 * 
								 * ((Spinner) v .findViewById(R.id.cboSfever))
								 * .setSelection(i); // cboSfever.SelectedIndex
								 * = //
								 * Convert.ToInt16(DR["fever_meas_subj"].ToString
								 * ()) // - 1; ((CheckBox) v
								 * .findViewById(R.id.chkMeasureFever))
								 * .setChecked(true); ((EditText) v
								 * .findViewById(R.id.txtFeverTemp))
								 * .setText(CommonStaticClass
								 * .CheckCursorValueWithNullHandler( mCursor,
								 * "fever_temp")); } }
								 */
							}

							else {
								((CheckBox) v
										.findViewById(R.id.chkMeasureFever))
										.setChecked(false);
							}

							if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"Cough").trim()
									.equalsIgnoreCase("2")) {
								((CheckBox) v.findViewById(R.id.chkCough))
										.setChecked(false);
							} else if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"Cough").trim()
									.equalsIgnoreCase("1")) {
								((CheckBox) v.findViewById(R.id.chkCough))
										.setChecked(true);
								((EditText) v.findViewById(R.id.dtpCough))
										.setText(CommonStaticClass
												.CheckCursorValueWithNullHandler(
														mCursor, "dt_cough")
												.toString().trim());
							} else {
								((CheckBox) v.findViewById(R.id.chkCough))
										.setChecked(false);
							}

							if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"sputum").trim()
									.equalsIgnoreCase("1")) {
								((RadioButton) ((RadioGroup) vg
										.findViewById(R.id.radioGroupchkSputum))
										.findViewById(R.id.radio0))
										.setChecked(true);
								// ((CheckBox) v.findViewById(R.id.chkSputum))
								// .setChecked(false);
							} else if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"sputum").trim()
									.equalsIgnoreCase("2")) {
								((RadioButton) ((RadioGroup) vg
										.findViewById(R.id.radioGroupchkSputum))
										.findViewById(R.id.radio1))
										.setChecked(true);
								// ((CheckBox) v.findViewById(R.id.chkSputum))
								// .setChecked(true);

							}
							/*
							 * else { ((CheckBox)
							 * v.findViewById(R.id.chkSputum))
							 * .setChecked(false); }
							 */
							if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"dif_brea").trim()
									.equalsIgnoreCase("2")) {
								((CheckBox) v
										.findViewById(R.id.chkDiffbreathing))
										.setChecked(false);
							} else if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"dif_brea").trim()
									.equalsIgnoreCase("1")) {
								((CheckBox) v
										.findViewById(R.id.chkDiffbreathing))
										.setChecked(true);
								((EditText) v.findViewById(R.id.dtpDiffBreath))
										.setText(CommonStaticClass
												.CheckCursorValueWithNullHandler(
														mCursor, "dt_brea")
												.toString().trim());
							} else {
								((CheckBox) v
										.findViewById(R.id.chkDiffbreathing))
										.setChecked(false);
							}

							if ((intComp == 1 || intComp == 2) && intAge >= 5) {
								((CheckBox) v.findViewById(R.id.chkSorethroat))
										.setChecked(true);
								if (CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "s_throat").trim()
										.equalsIgnoreCase("2")) {
									((CheckBox) v
											.findViewById(R.id.chkSorethroat))
											.setChecked(false);
								} else if (CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "s_throat").trim()
										.equalsIgnoreCase("1")) {
									((CheckBox) v
											.findViewById(R.id.chkSorethroat))
											.setChecked(true);
									((EditText) v
											.findViewById(R.id.dtpSoreThroat))
											.setText(CommonStaticClass
													.CheckCursorValueWithNullHandler(
															mCursor, "dt_throa")
													.toString().trim());
								} else {
									((CheckBox) v
											.findViewById(R.id.chkSorethroat))
											.setChecked(false);
								}
							} else {
								((CheckBox) v.findViewById(R.id.chkSorethroat))
										.setChecked(false);
								((CheckBox) v.findViewById(R.id.chkSorethroat))
										.setEnabled(false);
							}

							if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"r_nose").trim()
									.equalsIgnoreCase("2")) {
								((CheckBox) v.findViewById(R.id.chkRunningnose))
										.setChecked(false);
							} else if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"r_nose").trim()
									.equalsIgnoreCase("1")) {
								((CheckBox) v.findViewById(R.id.chkRunningnose))
										.setChecked(true);
								((EditText) v.findViewById(R.id.dtpRunningnose))
										.setText(CommonStaticClass
												.CheckCursorValueWithNullHandler(
														mCursor, "dt_nose")
												.toString().trim());
							} else {
								((CheckBox) v.findViewById(R.id.chkRunningnose))
										.setChecked(false);
							}

							if (intAge < 5) {
								((CheckBox) v.findViewById(R.id.chkHeadache))
										.setChecked(false);
								/*
								 * ((CheckBox) v.findViewById(R.id.chkspoon))
								 * .setChecked(false);
								 */
							} else {
								((CheckBox) v.findViewById(R.id.chkHeadache))
										.setEnabled(true);
								if (CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "headache").trim()
										.equalsIgnoreCase("2")) {
									((CheckBox) v
											.findViewById(R.id.chkHeadache))
											.setChecked(false);
								} else if (CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "headache").trim()
										.equalsIgnoreCase("1")) {
									((CheckBox) v
											.findViewById(R.id.chkHeadache))
											.setChecked(true);
									((EditText) v
											.findViewById(R.id.dtpHeadache))
											.setText(CommonStaticClass
													.CheckCursorValueWithNullHandler(
															mCursor, "dt_head")
													.toString().trim());
								} else {
									((CheckBox) v
											.findViewById(R.id.chkHeadache))
											.setChecked(false);
								}
							}

							if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"diarrhea").trim()
									.equalsIgnoreCase("2")) {
								((CheckBox) v.findViewById(R.id.chkDiarrhea))
										.setChecked(false);
							} else if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"diarrhea").trim()
									.equalsIgnoreCase("1")) {
								((CheckBox) v.findViewById(R.id.chkDiarrhea))
										.setChecked(true);
								((EditText) v.findViewById(R.id.dtpDiarrhea))
										.setText(CommonStaticClass
												.CheckCursorValueWithNullHandler(
														mCursor, "dt_diarr")
												.toString().trim());
							} else {
								((CheckBox) v.findViewById(R.id.chkDiarrhea))
										.setChecked(false);
							}

							if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"chills").trim()
									.equalsIgnoreCase("2")) {
								((CheckBox) v.findViewById(R.id.chkChills))
										.setChecked(false);
							} else if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"chills").trim()
									.equalsIgnoreCase("1")) {
								((CheckBox) v.findViewById(R.id.chkChills))
										.setChecked(true);
								((EditText) v.findViewById(R.id.dtpChills))
										.setText(CommonStaticClass
												.CheckCursorValueWithNullHandler(
														mCursor, "dt_chills")
												.toString().trim());
							} else {
								((CheckBox) v.findViewById(R.id.chkChills))
										.setChecked(false);
							}

							if (intAge >= 5) {
								((CheckBox) v.findViewById(R.id.chkBodyache))
										.setEnabled(true);
								if (CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "bodyache").trim()
										.equalsIgnoreCase("2")) {
									((CheckBox) v
											.findViewById(R.id.chkBodyache))
											.setChecked(false);
								} else if (CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "bodyache").trim()
										.equalsIgnoreCase("1")) {
									((CheckBox) v
											.findViewById(R.id.chkBodyache))
											.setChecked(true);
									((EditText) v
											.findViewById(R.id.dtpBodyache))
											.setText(CommonStaticClass
													.CheckCursorValueWithNullHandler(
															mCursor, "dt_body")
													.toString().trim());
								} else {
									((CheckBox) v
											.findViewById(R.id.chkBodyache))
											.setChecked(false);
								}
							} else {
								((CheckBox) v.findViewById(R.id.chkBodyache))
										.setEnabled(false);
								((CheckBox) v.findViewById(R.id.chkBodyache))
										.setChecked(false);
							}

							if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"hemo").trim()
									.equalsIgnoreCase("2")) {
								((CheckBox) v.findViewById(R.id.chkhemo))
										.setChecked(false);
							} else if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"hemo").trim()
									.equalsIgnoreCase("1")) {
								((CheckBox) v.findViewById(R.id.chkhemo))
										.setChecked(true);
								((EditText) v.findViewById(R.id.dtpHemo))
										.setText(CommonStaticClass
												.CheckCursorValueWithNullHandler(
														mCursor, "dt_hemo")
												.toString().trim());
							} else {
								((CheckBox) v.findViewById(R.id.chkhemo))
										.setChecked(false);
							}

							if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"pleur").trim()
									.equalsIgnoreCase("2")) {
								((CheckBox) v.findViewById(R.id.chkpleur))
										.setChecked(false);
							} else if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"pleur").trim()
									.equalsIgnoreCase("1")) {
								((CheckBox) v.findViewById(R.id.chkpleur))
										.setChecked(true);
								((EditText) v.findViewById(R.id.dtppleur))
										.setText(CommonStaticClass
												.CheckCursorValueWithNullHandler(
														mCursor, "dt_peur")
												.toString().trim());
							} else {
								((CheckBox) v.findViewById(R.id.chkpleur))
										.setChecked(false);
							}

							if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"SOther1DT").trim()
									.equalsIgnoreCase("")) {
								((CheckBox) v.findViewById(R.id.chkOtherOne))
										.setChecked(false);
								((EditText) v.findViewById(R.id.txtOtherOne))
										.setText("");
							} else {
								((CheckBox) v.findViewById(R.id.chkOtherOne))
										.setChecked(true);
								((EditText) v.findViewById(R.id.dtpOtherOne))
										.setText(CommonStaticClass
												.CheckCursorValueWithNullHandler(
														mCursor, "SOther1DT")
												.toString().trim());
								((EditText) v.findViewById(R.id.txtOtherOne))
										.setText(CommonStaticClass
												.CheckCursorValueWithNullHandler(
														mCursor, "SOther1N")
												.toString().trim());
							}
							if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"SOther2DT").trim()
									.equalsIgnoreCase("")) {
								((CheckBox) v.findViewById(R.id.chkOtherTwo))
										.setChecked(false);
								((EditText) v.findViewById(R.id.txtOtherTwo))
										.setText("");
							} else {
								((CheckBox) v.findViewById(R.id.chkOtherTwo))
										.setChecked(true);
								((EditText) v.findViewById(R.id.dtpOtherTwo))
										.setText(CommonStaticClass
												.CheckCursorValueWithNullHandler(
														mCursor, "SOther2DT"));

								((EditText) v.findViewById(R.id.txtOtherTwo))
										.setText(CommonStaticClass
												.CheckCursorValueWithNullHandler(
														mCursor, "SOther2N")
												.toString().trim());
							}
							if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"SOther3DT").trim()
									.equalsIgnoreCase("")) {
								((CheckBox) v.findViewById(R.id.chkOtherThree))
										.setChecked(false);
								((EditText) v.findViewById(R.id.txtOtherThree))
										.setText("");
							} else {
								((CheckBox) v.findViewById(R.id.chkOtherThree))
										.setChecked(true);
								((EditText) v.findViewById(R.id.dtpOtherThree))
										.setText(CommonStaticClass
												.CheckCursorValueWithNullHandler(
														mCursor, "SOther3DT")
												.toString().trim());
								((EditText) v.findViewById(R.id.txtOtherThree))
										.setText(CommonStaticClass
												.CheckCursorValueWithNullHandler(
														mCursor, "SOther3N")
												.toString().trim());
							}

							if (intComp == 3) {
								((CheckBox) v.findViewById(R.id.chkChestindraw))
										.setChecked(true);
								((CheckBox) v.findViewById(R.id.chkStridor))
										.setChecked(true);
								((CheckBox) v.findViewById(R.id.chkConvulsion))
										.setChecked(true);
								((CheckBox) v
										.findViewById(R.id.chkUnableToDrink))
										.setChecked(true);
								((CheckBox) v
										.findViewById(R.id.chkUnconsciousness))
										.setChecked(true);
								((CheckBox) v.findViewById(R.id.chkVomit))
										.setChecked(true);
								// panP4.Enabled = true;
								LoadCheckedDate(
										CommonStaticClass
												.CheckCursorValueWithNullHandler(
														mCursor, "S5ChestIND")
												.toString().trim(),
										((CheckBox) v
												.findViewById(R.id.chkChestindraw)),
										((EditText) v
												.findViewById(R.id.dtpChestIndraw)));
								LoadCheckedDate(
										CommonStaticClass
												.CheckCursorValueWithNullHandler(
														mCursor, "S5Stridor")
												.toString().trim(),
										((CheckBox) v
												.findViewById(R.id.chkStridor)),
										((EditText) v
												.findViewById(R.id.dtpStridor)));
								LoadCheckedDate(
										CommonStaticClass
												.CheckCursorValueWithNullHandler(
														mCursor, "S5Convulsion")
												.toString().trim(),
										((CheckBox) v
												.findViewById(R.id.chkConvulsion)),
										((EditText) v
												.findViewById(R.id.dtpConvulsion)));
								LoadCheckedDate(
										CommonStaticClass
												.CheckCursorValueWithNullHandler(
														mCursor, "S5UnDrink")
												.toString().trim(),
										((CheckBox) v
												.findViewById(R.id.chkUnableToDrink)),
										((EditText) v
												.findViewById(R.id.dtpUnableDrink)));
								LoadCheckedDate(
										CommonStaticClass
												.CheckCursorValueWithNullHandler(
														mCursor, "S5UnCons")
												.toString().trim(),
										((CheckBox) v
												.findViewById(R.id.chkUnconsciousness)),
										((EditText) v
												.findViewById(R.id.dtpUnconsciousness)));
								LoadCheckedDate(
										CommonStaticClass
												.CheckCursorValueWithNullHandler(
														mCursor, "S5Vomit")
												.toString().trim(),
										((CheckBox) v
												.findViewById(R.id.chkVomit)),
										((EditText) v
												.findViewById(R.id.dtpVomit)));
							} else {
								((CheckBox) v.findViewById(R.id.chkChestindraw))
										.setChecked(false);
								((CheckBox) v.findViewById(R.id.chkStridor))
										.setChecked(false);
								((CheckBox) v.findViewById(R.id.chkConvulsion))
										.setChecked(false);
								((CheckBox) v
										.findViewById(R.id.chkUnableToDrink))
										.setChecked(false);
								((CheckBox) v
										.findViewById(R.id.chkUnconsciousness))
										.setChecked(false);
								((CheckBox) v.findViewById(R.id.chkVomit))
										.setChecked(false);

							}
							if (intAge >= 9)
							// if (intComp != 3)
							{
								((RadioGroup) v
										.findViewById(R.id.radioGroupsmoke))
										.setVisibility(View.VISIBLE);
								((RadioGroup) v
										.findViewById(R.id.radioGroupsmoke))
										.setEnabled(true);
								// panSmoke.Enabled = true;
								if (CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "Smoking").toString()
										.trim().equalsIgnoreCase("1")) {
									((RadioButton) ((RadioGroup) v
											.findViewById(R.id.radioGroupsmoke))
											.findViewById(R.id.radioButton1))
											.setChecked(true);
									// optSmoke1.Checked = true;
								} else if (CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "Smoking").toString()
										.trim().equalsIgnoreCase("2")) {
									((RadioButton) ((RadioGroup) v
											.findViewById(R.id.radioGroupsmoke))
											.findViewById(R.id.radioButton2))
											.setChecked(true);
									// optSmoke2.Checked = true;
								} else if (CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "Smoking").toString()
										.trim().equalsIgnoreCase("3")) {
									((RadioButton) ((RadioGroup) v
											.findViewById(R.id.radioGroupsmoke))
											.findViewById(R.id.radioButton3))
											.setChecked(true);
									// optSmoke3.Checked = true;
								} else if (CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "Smoking").toString()
										.trim().equalsIgnoreCase("4")) {
									((RadioButton) ((RadioGroup) v
											.findViewById(R.id.radioGroupsmoke))
											.findViewById(R.id.radioButton4))
											.setChecked(true);
									// optSmoke4.Checked = true;
								}
							} else {
								/*
								 * ((CheckBox)
								 * v.findViewById(R.id.chkChestindraw))
								 * .setChecked(false); ((CheckBox)
								 * v.findViewById(R.id.chkStridor))
								 * .setChecked(false); ((CheckBox)
								 * v.findViewById(R.id.chkConvulsion))
								 * .setChecked(false); ((CheckBox) v
								 * .findViewById(R.id.chkUnableToDrink))
								 * .setChecked(false); ((CheckBox) v
								 * .findViewById(R.id.chkUnconsciousness))
								 * .setChecked(false); ((CheckBox)
								 * v.findViewById(R.id.chkVomit))
								 * .setChecked(false);
								 */

								((RadioGroup) v
										.findViewById(R.id.radioGroupsmoke))
										.clearCheck();
								((RadioGroup) v
										.findViewById(R.id.radioGroupsmoke))
										.setVisibility(View.GONE);
								((RadioGroup) v
										.findViewById(R.id.radioGroupsmoke))
										.setEnabled(false);
								((TextView) v.findViewById(R.id.lblsmoke))
										.setVisibility(View.GONE);

								/*
								 * panSmoke.Enabled = false; optSmoke1.Checked =
								 * false; optSmoke2.Checked = false;
								 * optSmoke3.Checked = false; optSmoke4.Checked
								 * = false;
								 */
							}

							if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"LungDis").toString().trim()
									.equalsIgnoreCase("1")) {
								((RadioButton) ((RadioGroup) v
										.findViewById(R.id.radioGroupLungDisease))
										.findViewById(R.id.radioButton1))
										.setChecked(true);
							} else if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"LungDis").toString().trim()
									.equalsIgnoreCase("2")) {
								((RadioButton) ((RadioGroup) v
										.findViewById(R.id.radioGroupLungDisease))
										.findViewById(R.id.radioButton2))
										.setChecked(true);
							}
							if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"HeartDis").toString().trim()
									.equalsIgnoreCase("1")) {
								((RadioButton) ((RadioGroup) v
										.findViewById(R.id.radioGroupHeartDis))
										.findViewById(R.id.radioButton1))
										.setChecked(true);
								// ((CheckBox)v.findViewById(R.id.optHeartYes)).setChecked(true);

							} else if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"HeartDis").toString().trim()
									.equalsIgnoreCase("2")) {
								((RadioButton) ((RadioGroup) v
										.findViewById(R.id.radioGroupHeartDis))
										.findViewById(R.id.radioButton2))
										.setChecked(true);
								// ((CheckBox)v.findViewById(R.id.optHeartNo)).setChecked(true);
							}
							if (intAge > 11 && intAge < 51 && intSex == 2) {
								((RadioGroup) v
										.findViewById(R.id.radioGroupPregnant))
										.setVisibility(View.VISIBLE);
								((RadioGroup) v
										.findViewById(R.id.radioGroupPregnant))
										.setEnabled(true);

								((TextView) v.findViewById(R.id.lblpregnant))
										.setVisibility(View.VISIBLE);

								// panPregnant.Enabled = true;
								if (CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "Pregnant").toString()
										.trim().equalsIgnoreCase("1")) {
									((RadioButton) ((RadioGroup) v
											.findViewById(R.id.radioGroupPregnant))
											.findViewById(R.id.radioButton1))
											.setChecked(true);

									// optPregnantYes.Checked = true;
								} else if (CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "Pregnant").toString()
										.trim().equalsIgnoreCase("2")) {
									((RadioButton) ((RadioGroup) v
											.findViewById(R.id.radioGroupPregnant))
											.findViewById(R.id.radioButton2))
											.setChecked(true);
									// optPregnantNo.Checked = true;
								}
							} else {
								((RadioGroup) v
										.findViewById(R.id.radioGroupPregnant))
										.clearCheck();
								((RadioGroup) v
										.findViewById(R.id.radioGroupPregnant))
										.setVisibility(View.GONE);
								((RadioGroup) v
										.findViewById(R.id.radioGroupPregnant))
										.setEnabled(false);
								((TextView) v.findViewById(R.id.lblpregnant))
										.setVisibility(View.GONE);

							}

							if (intComp == 2) {
								((RadioGroup) v
										.findViewById(R.id.radioGroupVisitOPD))
										.setVisibility(View.GONE);
								((TextView) v.findViewById(R.id.lblvisitOPD))
										.setVisibility(View.GONE);

							} else {
								// panVisitedOPD.Enabled = true;
								((RadioGroup) v
										.findViewById(R.id.radioGroupVisitOPD))
										.setVisibility(View.VISIBLE);
								((TextView) v.findViewById(R.id.lblvisitOPD))
										.setVisibility(View.VISIBLE);
								if (CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "VisitOPD").toString()
										.trim().equalsIgnoreCase("1")) {
									((RadioButton) ((RadioGroup) v
											.findViewById(R.id.radioGroupVisitOPD))
											.findViewById(R.id.radioButton1))
											.setChecked(true);
									// optOPDYes.Checked = true;
								} else if (CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "VisitOPD").toString()
										.trim().equalsIgnoreCase("2")) {
									((RadioButton) ((RadioGroup) v
											.findViewById(R.id.radioGroupVisitOPD))
											.findViewById(R.id.radioButton2))
											.setChecked(true);
									// optOPDNo.Checked = true;
								}
							}

							if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"Smoking").toString().trim()
									.equalsIgnoreCase("1")) {
								((RadioButton) ((RadioGroup) v
										.findViewById(R.id.radioGroupsmoke))
										.findViewById(R.id.radioButton1))
										.setChecked(true);

							} else if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"Smoking").toString().trim()
									.equalsIgnoreCase("2")) {
								((RadioButton) ((RadioGroup) v
										.findViewById(R.id.radioGroupsmoke))
										.findViewById(R.id.radioButton2))
										.setChecked(true);

							} else if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"Smoking").toString().trim()
									.equalsIgnoreCase("3")) {
								((RadioButton) ((RadioGroup) v
										.findViewById(R.id.radioGroupsmoke))
										.findViewById(R.id.radioButton3))
										.setChecked(true);

							} else if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"Smoking").toString().trim()
									.equalsIgnoreCase("4")) {
								((RadioButton) ((RadioGroup) v
										.findViewById(R.id.radioGroupsmoke))
										.findViewById(R.id.radioButton4))
										.setChecked(true);

							}

							if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"LungDis").toString().trim()
									.equalsIgnoreCase("1")) {
								((RadioButton) ((RadioGroup) v
										.findViewById(R.id.radioGroupLungDisease))
										.findViewById(R.id.radioButton1))
										.setChecked(true);
							} else if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"LungDis").toString().trim()
									.equalsIgnoreCase("2")) {
								((RadioButton) ((RadioGroup) v
										.findViewById(R.id.radioGroupLungDisease))
										.findViewById(R.id.radioButton2))
										.setChecked(true);
							}
							if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"HeartDis").toString().trim()
									.equalsIgnoreCase("1")) {
								((RadioButton) ((RadioGroup) v
										.findViewById(R.id.radioGroupHeartDis))
										.findViewById(R.id.radioButton1))
										.setChecked(true);

							} else if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"HeartDis").toString().trim()
									.equalsIgnoreCase("2")) {
								((RadioButton) ((RadioGroup) v
										.findViewById(R.id.radioGroupHeartDis))
										.findViewById(R.id.radioButton2))
										.setChecked(true);

							}

							if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"Pregnant").toString().trim()
									.equalsIgnoreCase("1")) {
								((RadioButton) ((RadioGroup) v
										.findViewById(R.id.radioGroupPregnant))
										.findViewById(R.id.radioButton1))
										.setChecked(true);

							} else if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"Pregnant").toString().trim()
									.equalsIgnoreCase("2")) {
								((RadioButton) ((RadioGroup) v
										.findViewById(R.id.radioGroupPregnant))
										.findViewById(R.id.radioButton2))
										.setChecked(true);

							}

							if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"VisitOPD").toString().trim()
									.equalsIgnoreCase("1")) {
								((RadioButton) ((RadioGroup) v
										.findViewById(R.id.radioGroupVisitOPD))
										.findViewById(R.id.radioButton1))
										.setChecked(true);

							} else if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"VisitOPD").toString().trim()
									.equalsIgnoreCase("2")) {
								((RadioButton) ((RadioGroup) v
										.findViewById(R.id.radioGroupVisitOPD))
										.findViewById(R.id.radioButton2))
										.setChecked(true);
								// optOPDNo.Checked = true;
							}
							if (!CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"chron_illness1").toString().trim()
									.equalsIgnoreCase("")) {
								if (CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "chron_illness1")
										.toString().trim()
										.equalsIgnoreCase("0")) {

									CommonStaticClass
											.SetSpinnerValue(
													thisactivity,
													((Spinner) v
															.findViewById(R.id.cboIllness1)),
													"0");

									/*
									 * for (int i = 0; i < ((Spinner) v
									 * .findViewById(R.id.cboIllness1))
									 * .getCount(); i++) {
									 * 
									 * if (((Spinner) v
									 * .findViewById(R.id.cboIllness1))
									 * .getItemAtPosition(i) .toString()
									 * .substring( 0, (((Spinner) v
									 * .findViewById(R.id.cboIllness1))
									 * .getItemAtPosition( i) .toString()
									 * .lastIndexOf(":")))
									 * .trim().equalsIgnoreCase("0"))
									 * 
									 * { ((Spinner) v
									 * .findViewById(R.id.cboIllness1))
									 * .setSelection(10); }
									 * 
									 * }
									 */

								} else {
									CommonStaticClass
											.SetSpinnerValue(
													thisactivity,
													((Spinner) v
															.findViewById(R.id.cboIllness1)),
													CommonStaticClass
															.CheckCursorValueWithNullHandler(
																	mCursor,
																	"chron_illness1")
															.toString().trim());

									/*
									 * for (int i = 0; i < ((Spinner) v
									 * .findViewById(R.id.cboIllness1))
									 * .getCount(); i++) { if (((Spinner) v
									 * .findViewById(R.id.cboIllness1))
									 * .getItemAtPosition(i) .toString()
									 * .substring( 0, (((Spinner) v
									 * .findViewById(R.id.cboIllness1))
									 * .getItemAtPosition( i) .toString()
									 * .lastIndexOf(":"))) .trim()
									 * .equalsIgnoreCase( CommonStaticClass
									 * .CheckCursorValueWithNullHandler(
									 * mCursor, "chron_illness1") .toString()
									 * .trim()))
									 * 
									 * { ((Spinner) v
									 * .findViewById(R.id.cboIllness1))
									 * .setSelection(i); } }
									 */
								}
								// cboIllness1.SelectedIndex =
								// Convert.ToInt16(DR["chron_illness1"].ToString())
								// - 1;
							}

							else {

								// cboIllness1.SelectedIndex = -1;
							}

							if (!CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"chron_illness2").toString().trim()
									.equalsIgnoreCase("")) {
								if (CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "chron_illness2")
										.toString().trim()
										.equalsIgnoreCase("0")) {
									CommonStaticClass
											.SetSpinnerValue(
													thisactivity,
													((Spinner) v
															.findViewById(R.id.cboIllness2)),
													CommonStaticClass
															.CheckCursorValueWithNullHandler(
																	mCursor,
																	"chron_illness2")
															.toString().trim());
									/*
									 * for (int i = 0; i < ((Spinner) v
									 * .findViewById(R.id.cboIllness2))
									 * .getCount(); i++) {
									 * 
									 * if (((Spinner) v
									 * .findViewById(R.id.cboIllness2))
									 * .getItemAtPosition(i) .toString()
									 * .substring( 0, (((Spinner) v
									 * .findViewById(R.id.cboIllness2))
									 * .getItemAtPosition( i) .toString()
									 * .lastIndexOf(":")))
									 * .trim().equalsIgnoreCase("0"))
									 * 
									 * { ((Spinner) v
									 * .findViewById(R.id.cboIllness2))
									 * .setSelection(10); }
									 * 
									 * }
									 */

								} else {
									CommonStaticClass
											.SetSpinnerValue(
													thisactivity,
													((Spinner) v
															.findViewById(R.id.cboIllness2)),
													CommonStaticClass
															.CheckCursorValueWithNullHandler(
																	mCursor,
																	"chron_illness2")
															.toString().trim());

									/*
									 * for (int i = 0; i < ((Spinner) v
									 * .findViewById(R.id.cboIllness2))
									 * .getCount(); i++) { if (((Spinner) v
									 * .findViewById(R.id.cboIllness2))
									 * .getItemAtPosition(i) .toString()
									 * .substring( 0, (((Spinner) v
									 * .findViewById(R.id.cboIllness2))
									 * .getItemAtPosition( i) .toString()
									 * .lastIndexOf(":"))) .trim()
									 * .equalsIgnoreCase( CommonStaticClass
									 * .CheckCursorValueWithNullHandler(
									 * mCursor, "chron_illness2") .toString()
									 * .trim()))
									 * 
									 * { ((Spinner) v
									 * .findViewById(R.id.cboIllness2))
									 * .setSelection(i); } }
									 */
								}
								// cboIllness1.SelectedIndex =
								// Convert.ToInt16(DR["chron_illness1"].ToString())
								// - 1;
							}

							else {
								// cboIllness1.SelectedIndex = -1;
							}

							/*
							 * if (DR["chron_illness2"].ToString() != "") { if
							 * (Convert.ToInt16(DR["chron_illness2"].ToString())
							 * == 0) { cboIllness2.SelectedIndex = 11; } else
							 * cboIllness2.SelectedIndex =
							 * Convert.ToInt16(DR["chron_illness2"].ToString())
							 * - 1; } else { cboIllness2.SelectedIndex = -1; }
							 */

							if (!CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"chron_illness3").toString().trim()
									.equalsIgnoreCase("")) {
								if (CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "chron_illness3")
										.toString().trim()
										.equalsIgnoreCase("0")) {

									CommonStaticClass
											.SetSpinnerValue(
													thisactivity,
													((Spinner) v
															.findViewById(R.id.cboIllness3)),
													CommonStaticClass
															.CheckCursorValueWithNullHandler(
																	mCursor,
																	"chron_illness3")
															.toString().trim());

									/*
									 * for (int i = 0; i < ((Spinner) v
									 * .findViewById(R.id.cboIllness3))
									 * .getCount(); i++) {
									 * 
									 * if (((Spinner) v
									 * .findViewById(R.id.cboIllness3))
									 * .getItemAtPosition(i) .toString()
									 * .substring( 0, (((Spinner) v
									 * .findViewById(R.id.cboIllness3))
									 * .getItemAtPosition( i) .toString()
									 * .lastIndexOf(":")))
									 * .trim().equalsIgnoreCase("0"))
									 * 
									 * { ((Spinner) v
									 * .findViewById(R.id.cboIllness3))
									 * .setSelection(10); }
									 * 
									 * }
									 */

								} else {
									CommonStaticClass
											.SetSpinnerValue(
													thisactivity,
													((Spinner) v
															.findViewById(R.id.cboIllness3)),
													CommonStaticClass
															.CheckCursorValueWithNullHandler(
																	mCursor,
																	"chron_illness3")
															.toString().trim());

									/*
									 * for (int i = 0; i < ((Spinner) v
									 * .findViewById(R.id.cboIllness3))
									 * .getCount(); i++) { if (((Spinner) v
									 * .findViewById(R.id.cboIllness3))
									 * .getItemAtPosition(i) .toString()
									 * .substring( 0, (((Spinner) v
									 * .findViewById(R.id.cboIllness3))
									 * .getItemAtPosition( i) .toString()
									 * .lastIndexOf(":"))) .trim()
									 * .equalsIgnoreCase( CommonStaticClass
									 * .CheckCursorValueWithNullHandler(
									 * mCursor, "chron_illness3") .toString()
									 * .trim()))
									 * 
									 * { ((Spinner) v
									 * .findViewById(R.id.cboIllness1))
									 * .setSelection(i); } }
									 */
								}

							}

							else {
								// cboIllness1.SelectedIndex = -1;
							}

							if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"chron_illness1").toString().trim() != "") {
								if (CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "chron_illness1")
										.toString().trim()
										.equalsIgnoreCase("10")) {
									((EditText) v
											.findViewById(R.id.txtChronicILLOther))
											.setText(CommonStaticClass
													.CheckCursorValueWithNullHandler(
															mCursor,
															"chron_illnessOther")
													.toString().trim());
								}
							}

							if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"chron_illness2").toString().trim() != "") {
								if (CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "chron_illness2")
										.toString().trim()
										.equalsIgnoreCase("10")) {
									((EditText) v
											.findViewById(R.id.txtChronicILLOther))
											.setText(CommonStaticClass
													.CheckCursorValueWithNullHandler(
															mCursor,
															"chron_illnessOther")
													.toString().trim());
								}
							}

							if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"chron_illness3").toString().trim() != "") {
								if (CommonStaticClass
										.CheckCursorValueWithNullHandler(
												mCursor, "chron_illness3")
										.toString().trim()
										.equalsIgnoreCase("10")) {
									((EditText) v
											.findViewById(R.id.txtChronicILLOther))
											.setText(CommonStaticClass
													.CheckCursorValueWithNullHandler(
															mCursor,
															"chron_illnessOther")
													.toString().trim());
								}
							}

							if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"IsDrug_chronillness").toString()
									.trim().equalsIgnoreCase("1")) {
								((RadioButton) ((RadioGroup) v
										.findViewById(R.id.radioGroupTacMed))
										.findViewById(R.id.radioButton1))
										.setChecked(true);

							} else if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"IsDrug_chronillness").toString()
									.trim().equalsIgnoreCase("0")) {
								((RadioButton) ((RadioGroup) v
										.findViewById(R.id.radioGroupTacMed))
										.findViewById(R.id.radioButton2))
										.setChecked(true);
							}

							if (!CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"Drug1_chronillness1").toString()
									.trim().equalsIgnoreCase("")) {
								CommonStaticClass
										.SetSpinnerValue(
												thisactivity,
												((Spinner) v
														.findViewById(R.id.cboIllness3)),
												CommonStaticClass
														.CheckCursorValueWithNullHandler(
																mCursor,
																"Drug1_chronillness1")
														.toString().trim());

								/*
								 * for (int j = 0; j < ((Spinner) v
								 * .findViewById(R.id.cboIllness3)) .getCount();
								 * j++) {
								 * 
								 * if (((Spinner) v
								 * .findViewById(R.id.cboIllness3))
								 * .getItemAtPosition(j) .toString() .substring(
								 * 0, (((Spinner) v
								 * .findViewById(R.id.cboIllness3))
								 * .getItemAtPosition( j) .toString()
								 * .lastIndexOf(":"))) .trim()
								 * .equalsIgnoreCase( CommonStaticClass
								 * .CheckCursorValueWithNullHandler( mCursor,
								 * "Drug1_chronillness1") .toString().trim()))
								 * 
								 * { ((Spinner) v
								 * .findViewById(R.id.cboTacMedicine1))
								 * .setSelection(j); }
								 * 
								 * }
								 */

								// cboTacMedicine1.SelectedItem =
								// FindDrugList(DR["Drug1_chronillness1"].ToString(),
								// ConManager.IDisconnection);
							}

							if (!CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"Drug1_chronillness1").toString()
									.trim().equalsIgnoreCase("")) {

								CommonStaticClass
										.SetSpinnerValue(
												thisactivity,
												((Spinner) v
														.findViewById(R.id.cboTacMedicine1)),
												CommonStaticClass
														.CheckCursorValueWithNullHandler(
																mCursor,
																"Drug1_chronillness1")
														.toString().trim());

								/*
								 * for (int j = 0; j < ((Spinner) v
								 * .findViewById(R.id.cboTacMedicine1))
								 * .getCount(); j++) {
								 * 
								 * if (((Spinner) v
								 * .findViewById(R.id.cboTacMedicine1))
								 * .getItemAtPosition(j) .toString() .substring(
								 * 0, (((Spinner) v
								 * .findViewById(R.id.cboTacMedicine1))
								 * .getItemAtPosition( j) .toString()
								 * .lastIndexOf(":"))) .trim()
								 * .equalsIgnoreCase( CommonStaticClass
								 * .CheckCursorValueWithNullHandler( mCursor,
								 * "Drug1_chronillness1") .toString().trim()))
								 * 
								 * { ((Spinner) v
								 * .findViewById(R.id.cboTacMedicine1))
								 * .setSelection(j); }
								 * 
								 * }
								 */

								// cboTacMedicine1.SelectedItem =
								// DR["Drug1_chronillness1"].ToString();
								// cboTacMedicine1.SelectedItem =
								// FindDrugList(DR["Drug1_chronillness1"].ToString(),
								// ConManager.IDisconnection);
							} else {
								// cboTacMedicine1.SelectedIndex = -1;
							}

							if (!CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"Drug2_chronillness2").toString()
									.trim().equalsIgnoreCase("")) {

								CommonStaticClass
										.SetSpinnerValue(
												thisactivity,
												((Spinner) v
														.findViewById(R.id.cboTacMedicine2)),
												CommonStaticClass
														.CheckCursorValueWithNullHandler(
																mCursor,
																"Drug2_chronillness2")
														.toString().trim());

								/*
								 * for (int j = 0; j < ((Spinner) v
								 * .findViewById(R.id.cboTacMedicine2))
								 * .getCount(); j++) {
								 * 
								 * if (((Spinner) v
								 * .findViewById(R.id.cboTacMedicine2))
								 * .getItemAtPosition(j) .toString() .substring(
								 * 0, (((Spinner) v
								 * .findViewById(R.id.cboTacMedicine2))
								 * .getItemAtPosition( j) .toString()
								 * .lastIndexOf(":"))) .trim()
								 * .equalsIgnoreCase( CommonStaticClass
								 * .CheckCursorValueWithNullHandler( mCursor,
								 * "Drug2_chronillness2") .toString().trim()))
								 * 
								 * { ((Spinner) v
								 * .findViewById(R.id.cboTacMedicine1))
								 * .setSelection(j); }
								 * 
								 * }
								 */

								// cboTacMedicine2.SelectedItem =
								// DR["Drug2_chronillness2"].ToString();
								// cboTacMedicine2.SelectedItem =
								// FindDrugList(DR["Drug2_chronillness2"].ToString(),
								// ConManager.IDisconnection);
							} else {
								// cboTacMedicine2.SelectedIndex = -1;
							}

							if (!CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"Drug3_chronillness3").toString()
									.trim().equalsIgnoreCase("")) {

								CommonStaticClass
										.SetSpinnerValue(
												thisactivity,
												((Spinner) v
														.findViewById(R.id.cboTacMedicine3)),
												CommonStaticClass
														.CheckCursorValueWithNullHandler(
																mCursor,
																"Drug3_chronillness3")
														.toString().trim());

								/*
								 * for (int j = 0; j < ((Spinner) v
								 * .findViewById(R.id.cboTacMedicine3))
								 * .getCount(); j++) {
								 * 
								 * if (((Spinner) v
								 * .findViewById(R.id.cboTacMedicine3))
								 * .getItemAtPosition(j) .toString() .substring(
								 * 0, (((Spinner) v
								 * .findViewById(R.id.cboTacMedicine3))
								 * .getItemAtPosition( j) .toString()
								 * .lastIndexOf(":"))) .trim()
								 * .equalsIgnoreCase( CommonStaticClass
								 * .CheckCursorValueWithNullHandler( mCursor,
								 * "Drug3_chronillness3") .toString().trim()))
								 * 
								 * { ((Spinner) v
								 * .findViewById(R.id.cboTacMedicine1))
								 * .setSelection(j); }
								 * 
								 * }
								 */

								// cboTacMedicine3.SelectedItem =
								// DR["Drug3_chronillness3"].ToString();
								// cboTacMedicine3.SelectedItem =
								// FindDrugList(DR["Drug3_chronillness3"].ToString(),
								// ConManager.IDisconnection);
							} else {
								// cboTacMedicine3.SelectedIndex = -1;
							}

							// cboIllness2.SelectedIndex =
							// Convert.ToInt16(DR["chron_illness2"].ToString())
							// - 1;
							// cboIllness3.SelectedIndex =
							// Convert.ToInt16(DR["chron_illness3"].ToString())
							// - 1;

							String strHosID = CommonStaticClass.getSkip(
									"HosID", "tblMainQues", dbHelper);

							if (intComp == 1 && intAge >= 18) {

								if (catchmanarea != null && strHosID != null) {
									if (catchmanarea.equalsIgnoreCase(strHosID)) {
										((RadioGroup) v
												.findViewById(R.id.radioGroupPneumonia))
												.setVisibility(View.VISIBLE);
										((RadioGroup) v
												.findViewById(R.id.radioGrouppriorHosp))
												.setVisibility(View.VISIBLE);

										((RadioGroup) v
												.findViewById(R.id.radioGroupPneumonia))
												.setEnabled(true);
										((RadioGroup) v
												.findViewById(R.id.radioGrouppriorHosp))
												.setEnabled(true);

										/*
										 * panel1.Enabled = true; panel2.Enabled
										 * = true;
										 */

										if (CommonStaticClass
												.CheckCursorValueWithNullHandler(
														mCursor, "IsTac")
												.toString().trim()
												.equalsIgnoreCase("1")) {
											((TextView) v
													.findViewById(R.id.lbltac))
													.setVisibility(View.VISIBLE);
											((RadioGroup) v
													.findViewById(R.id.radioGroupTac))
													.setVisibility(View.VISIBLE);
											((RadioButton) ((RadioGroup) v
													.findViewById(R.id.radioGroupTac))
													.findViewById(R.id.radioButton1))
													.setChecked(true);
											// OptTacY.Checked = true;
										} else if (CommonStaticClass
												.CheckCursorValueWithNullHandler(
														mCursor, "IsTac")
												.toString().trim()
												.equalsIgnoreCase("0")) {
											((TextView) v
													.findViewById(R.id.lbltac))
													.setVisibility(View.VISIBLE);
											((RadioGroup) v
													.findViewById(R.id.radioGroupTac))
													.setVisibility(View.VISIBLE);

											((RadioButton) ((RadioGroup) v
													.findViewById(R.id.radioGroupTac))
													.findViewById(R.id.radioButton2))
													.setChecked(true);

											// OptTacY.Checked = true;
										} else {
											((RadioGroup) v
													.findViewById(R.id.radioGroupTac))
													.clearCheck();
											((TextView) v
													.findViewById(R.id.lbltac))
													.setVisibility(View.GONE);
											((RadioGroup) v
													.findViewById(R.id.radioGroupTac))
													.setVisibility(View.GONE);
											// OptTacY.Checked = false;
											// OptTacNo.Checked = false;
										}

										if (CommonStaticClass
												.CheckCursorValueWithNullHandler(
														mCursor, "prior_hosp")
												.toString().trim()
												.equalsIgnoreCase("1")) {
											((RadioButton) ((RadioGroup) v
													.findViewById(R.id.radioGrouppriorHosp))
													.findViewById(R.id.radioButton1))
													.setChecked(true);
											// optpriorHospY.Checked = true;
										} else if (CommonStaticClass
												.CheckCursorValueWithNullHandler(
														mCursor, "prior_hosp")
												.toString().trim()
												.equalsIgnoreCase("2")) {
											((RadioButton) ((RadioGroup) v
													.findViewById(R.id.radioGrouppriorHosp))
													.findViewById(R.id.radioButton2))
													.setChecked(true);
											// optpriorHospN.Checked = true;
										} else if (CommonStaticClass
												.CheckCursorValueWithNullHandler(
														mCursor, "prior_hosp")
												.toString().trim()
												.equalsIgnoreCase("99")) {
											((RadioButton) ((RadioGroup) v
													.findViewById(R.id.radioGrouppriorHosp))
													.findViewById(R.id.radioButton3))
													.setChecked(true);
											// optpriorHospDK.Checked = true;
										}

										if (CommonStaticClass
												.CheckCursorValueWithNullHandler(
														mCursor, "hx_pneumo")
												.toString().trim()
												.equalsIgnoreCase("1")) {

											((RadioButton) ((RadioGroup) v
													.findViewById(R.id.radioGroupPneumonia))
													.findViewById(R.id.radioButton1))
													.setChecked(true);

											// rbPneumoniaYes.Checked = true;
										} else if (CommonStaticClass
												.CheckCursorValueWithNullHandler(
														mCursor, "hx_pneumo")
												.toString().trim()
												.equalsIgnoreCase("2")) {
											((RadioButton) ((RadioGroup) v
													.findViewById(R.id.radioGroupPneumonia))
													.findViewById(R.id.radioButton2))
													.setChecked(true);
											// rbPneumoniaNo.Checked = true;
										} else if (CommonStaticClass
												.CheckCursorValueWithNullHandler(
														mCursor, "hx_pneumo")
												.toString().trim()
												.equalsIgnoreCase("9")) {
											((RadioButton) ((RadioGroup) v
													.findViewById(R.id.radioGroupPneumonia))
													.findViewById(R.id.radioButton3))
													.setChecked(true);
											// rbPneumoniaDk.Checked = true;
										}

										/*
										 * if (CommonStaticClass
										 * .CheckCursorValueWithNullHandler(
										 * mCursor, "IsTac") .toString().trim()
										 * .equalsIgnoreCase("1")) { ((TextView)
										 * v .findViewById(R.id.lbltac))
										 * .setVisibility(View.VISIBLE);
										 * ((RadioGroup) v
										 * .findViewById(R.id.radioGroupTac))
										 * .setVisibility(View.VISIBLE);
										 * ((RadioButton) ((RadioGroup) v
										 * .findViewById(R.id.radioGroupTac))
										 * .findViewById(R.id.radioButton1))
										 * .setChecked(true); // OptTacY.Checked
										 * = true; } else if (CommonStaticClass
										 * .CheckCursorValueWithNullHandler(
										 * mCursor, "IsTac") .toString().trim()
										 * .equalsIgnoreCase("0")) { ((TextView)
										 * v .findViewById(R.id.lbltac))
										 * .setVisibility(View.VISIBLE);
										 * ((RadioGroup) v
										 * .findViewById(R.id.radioGroupTac))
										 * .setVisibility(View.VISIBLE);
										 * 
										 * ((RadioButton) ((RadioGroup) v
										 * .findViewById(R.id.radioGroupTac))
										 * .findViewById(R.id.radioButton2))
										 * .setChecked(true);
										 * 
										 * // OptTacY.Checked = true; } else {
										 * ((RadioGroup) v
										 * .findViewById(R.id.radioGroupTac))
										 * .clearCheck(); ((TextView) v
										 * .findViewById(R.id.lbltac))
										 * .setVisibility(View.GONE);
										 * ((RadioGroup) v
										 * .findViewById(R.id.radioGroupTac))
										 * .setVisibility(View.GONE); //
										 * OptTacY.Checked = false; //
										 * OptTacNo.Checked = false; }
										 */
									}
								} else {

									((TextView) v
											.findViewById(R.id.lblpneumonia))
											.setVisibility(View.GONE);
									((TextView) v
											.findViewById(R.id.lbladmitted))
											.setVisibility(View.GONE);

									((RadioGroup) v
											.findViewById(R.id.radioGroupPneumonia))
											.setVisibility(View.GONE);
									((RadioGroup) v
											.findViewById(R.id.radioGrouppriorHosp))
											.setVisibility(View.GONE);
									((RadioGroup) v
											.findViewById(R.id.radioGroupPneumonia))
											.setEnabled(false);
									((RadioGroup) v
											.findViewById(R.id.radioGrouppriorHosp))
											.setEnabled(false);
									((TextView) vg.findViewById(R.id.lbltac))
											.setVisibility(View.GONE);
									((RadioGroup) vg
											.findViewById(R.id.radioGroupTac))
											.setVisibility(View.GONE);

								}
							} else {

								((TextView) v.findViewById(R.id.lblpneumonia))
										.setVisibility(View.GONE);
								((TextView) v.findViewById(R.id.lbladmitted))
										.setVisibility(View.GONE);

								((RadioGroup) v
										.findViewById(R.id.radioGroupPneumonia))
										.setVisibility(View.GONE);
								((RadioGroup) v
										.findViewById(R.id.radioGrouppriorHosp))
										.setVisibility(View.GONE);
								((RadioGroup) v
										.findViewById(R.id.radioGroupPneumonia))
										.setEnabled(false);
								((RadioGroup) v
										.findViewById(R.id.radioGrouppriorHosp))
										.setEnabled(false);
								((TextView) vg.findViewById(R.id.lbltac))
										.setVisibility(View.GONE);
								((RadioGroup) vg
										.findViewById(R.id.radioGroupTac))
										.setVisibility(View.GONE);

							}

						} while (mCursor.moveToNext());

					}

				}

			} catch (Exception e) {
				CommonStaticClass.showMyAlert(thisactivity, "Error",
						"Error On Load");
			} finally {

			}
		} finally {

		}

	}

	private void LoadCheckedDate(String strData, CheckBox myChk, EditText myDtp) {
		try {
			if (strData.length() <= 0) {
				myChk.setChecked(false);
			} else {
				myChk.setChecked(true);

				myDtp.setText(strData);
			}
		} catch (Exception ex) {
			CommonStaticClass.showMyAlert(thisactivity, "Error",
					"Error in Load Check Date.");
		} finally {
		}
	}

	String catchmanarea = "";

	private void GetCathmanArea() {
		String strSQL = "";

		strSQL = "select p.hosid,p.pscode from PoliceStation p,tblMainQues m where p.PSCode  =m.pscode and m.dataid='"
				+ CommonStaticClass.dataId + "' ";

		Cursor mCursor = null;

		try {

			mCursor = dbHelper.getQueryCursor(strSQL);

			if (mCursor.getCount() > 0) {

				if (mCursor.moveToFirst()) {

					do {

						catchmanarea = mCursor.getString(0);

					} while (mCursor.moveToNext());

				}

			}

		} catch (Exception e) {

		}

	}

	private void HideViews(ViewGroup v) {

		((RadioGroup) v.findViewById(R.id.radioGroupchkSputum))
				.setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.dtpFever)).setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.dtpCough)).setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.dtpDiffBreath))
				.setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.dtpSoreThroat))
				.setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.dtpRunningnose))
				.setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.dtpHeadache)).setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.dtpDiarrhea)).setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.dtpChills)).setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.dtpBodyache)).setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.dtpHemo)).setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.dtppleur)).setVisibility(View.GONE);

		((EditText) v.findViewById(R.id.dtpOtherOne)).setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.dtpOtherTwo)).setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.dtpOtherThree))
				.setVisibility(View.GONE);

		((EditText) v.findViewById(R.id.dtpChestIndraw))
				.setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.dtpStridor)).setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.dtpConvulsion))
				.setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.dtpUnableDrink))
				.setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.dtpUnconsciousness))
				.setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.dtpVomit)).setVisibility(View.GONE);

		((EditText) v.findViewById(R.id.dtpFever)).setVisibility(View.GONE);

		((EditText) v.findViewById(R.id.dtpCough)).setVisibility(View.GONE);

		((EditText) v.findViewById(R.id.dtpDiffBreath))
				.setVisibility(View.GONE);

		((EditText) v.findViewById(R.id.dtpSoreThroat))
				.setVisibility(View.GONE);

		((EditText) v.findViewById(R.id.dtpRunningnose))
				.setVisibility(View.GONE);

		((EditText) v.findViewById(R.id.dtpHeadache)).setVisibility(View.GONE);

		((EditText) v.findViewById(R.id.dtpDiarrhea)).setVisibility(View.GONE);

		((EditText) v.findViewById(R.id.dtpChills)).setVisibility(View.GONE);

		((EditText) v.findViewById(R.id.dtpBodyache)).setVisibility(View.GONE);

		((EditText) v.findViewById(R.id.dtpHemo)).setVisibility(View.GONE);

		((EditText) v.findViewById(R.id.dtppleur)).setVisibility(View.GONE);

		((EditText) v.findViewById(R.id.txtOtherOne)).setVisibility(View.GONE);

		((EditText) v.findViewById(R.id.txtOtherTwo)).setVisibility(View.GONE);

		((EditText) v.findViewById(R.id.txtOtherThree))
				.setVisibility(View.GONE);

		((EditText) v.findViewById(R.id.dtpChestIndraw))
				.setVisibility(View.GONE);

		((EditText) v.findViewById(R.id.dtpStridor)).setVisibility(View.GONE);

		((EditText) v.findViewById(R.id.dtpConvulsion))
				.setVisibility(View.GONE);

		((EditText) v.findViewById(R.id.dtpUnableDrink))
				.setVisibility(View.GONE);

		((EditText) v.findViewById(R.id.dtpUnconsciousness))
				.setVisibility(View.GONE);

		((EditText) v.findViewById(R.id.dtpVomit)).setVisibility(View.GONE);

		((EditText) v.findViewById(R.id.txtFeverTemp)).setVisibility(View.GONE);
		((Spinner) v.findViewById(R.id.cboTacMedicine1))
				.setVisibility(View.GONE);
		((Spinner) v.findViewById(R.id.cboTacMedicine2))
				.setVisibility(View.GONE);
		((Spinner) v.findViewById(R.id.cboTacMedicine3))
				.setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.dtpOtherOne)).setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.dtpOtherTwo)).setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.dtpOtherThree))
				.setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.txtChronicILLOther))
				.setVisibility(View.GONE);

		((Spinner) v.findViewById(R.id.cboSfever)).setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.txtFeverTemp)).setVisibility(View.GONE);
		((Spinner) v.findViewById(R.id.cboIllness2)).setVisibility(View.GONE);
		((Spinner) v.findViewById(R.id.cboIllness3)).setVisibility(View.GONE);

		((TextView) v.findViewById(R.id.lblillness2)).setVisibility(View.GONE);
		((TextView) v.findViewById(R.id.lblillness3)).setVisibility(View.GONE);
		((TextView) v.findViewById(R.id.lblillnessother))
				.setVisibility(View.GONE);

		((TextView) v.findViewById(R.id.lbldrug2)).setVisibility(View.GONE);
		((Spinner) v.findViewById(R.id.cboTacMedicine2))
				.setVisibility(View.GONE);

		((TextView) v.findViewById(R.id.lbldrug3)).setVisibility(View.GONE);
		((Spinner) v.findViewById(R.id.cboTacMedicine3))
				.setVisibility(View.GONE);

		intAge = Integer.parseInt(CommonStaticClass.getSkip("AgeY",
				"tblMainQues", dbHelper));

		if (intAge < 5)
			((LinearLayout) v.findViewById(R.id.pan4))
					.setVisibility(View.VISIBLE);
		else if (intAge >= 5)
			((LinearLayout) v.findViewById(R.id.pan4)).setVisibility(View.GONE);

		intSex = Integer.parseInt(CommonStaticClass.getSkip("Sex",
				"tblMainQues", dbHelper));
		/*
		 * intComp = Integer.parseInt(CommonStaticClass.getSkip("Comp",
		 * "tblMainQues", dbHelper));
		 */

		/*
		 * if(intSex == 2) { (TextView) }
		 */

	}

	private void SetDate(EditText et) {

		if (!(et.getText().toString().length() > 0))
			updateDisplayfrmSymptom("date", et);

		et.setHint("Date of Onset");
		et.setHintTextColor(Color.GRAY);
		et.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				final Calendar c = Calendar.getInstance();
				dateYear = c.get(Calendar.YEAR);
				dateMonth = c.get(Calendar.MONTH);
				dateDay = c.get(Calendar.DAY_OF_MONTH);

				showDialog(HBIS_DATE_DIALOG_ID);
				return false;

			}
		});

	}

	private void SetCheckBox(final CheckBox chk, final EditText et,
			final ViewGroup vg) {

		chk.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {

				if (chk.isChecked()) {
					et.setVisibility(View.VISIBLE);

					if (R.id.chkOtherOne == chk.getId()) {
						((EditText) vg.findViewById(R.id.dtpOtherOne))
								.setVisibility(View.VISIBLE);
					} else if (R.id.chkOtherTwo == chk.getId()) {
						((EditText) vg.findViewById(R.id.dtpOtherTwo))
								.setVisibility(View.VISIBLE);
					} else if (R.id.chkOtherThree == chk.getId()) {
						((EditText) vg.findViewById(R.id.dtpOtherThree))
								.setVisibility(View.VISIBLE);
					} else if (R.id.chkFever == chk.getId()) {
						((CheckBox) vg.findViewById(R.id.chkMeasureFever))
								.setChecked(true);
						((TextView) vg.findViewById(R.id.lbltemp))
								.setVisibility(View.VISIBLE);
						((EditText) vg.findViewById(R.id.txtFeverTemp))
								.setVisibility(View.VISIBLE);
					}

					else if ((R.id.chkCough) == chk.getId()) {
						if (intAge < 5) {

						} else {
							((TextView) vg.findViewById(R.id.lblSputum))
									.setVisibility(View.VISIBLE);
							((RadioGroup) vg
									.findViewById(R.id.radioGroupchkSputum))
									.setVisibility(View.VISIBLE);
						}
					}
				} else {
					et.setVisibility(View.GONE);
					if (R.id.chkOtherOne == chk.getId()) {
						((EditText) vg.findViewById(R.id.dtpOtherOne))
								.setVisibility(View.GONE);
					} else if (R.id.chkOtherTwo == chk.getId()) {
						((EditText) vg.findViewById(R.id.dtpOtherTwo))
								.setVisibility(View.GONE);
					} else if (R.id.chkOtherThree == chk.getId()) {
						((EditText) vg.findViewById(R.id.dtpOtherThree))
								.setVisibility(View.GONE);
					} else if (R.id.chkFever == chk.getId()) {
						((CheckBox) vg.findViewById(R.id.chkMeasureFever))
								.setChecked(false);
						((TextView) vg.findViewById(R.id.lbltemp))
								.setVisibility(View.GONE);
						((EditText) vg.findViewById(R.id.txtFeverTemp))
								.setVisibility(View.GONE);
					} else if ((R.id.chkCough) == chk.getId()) {
						((TextView) vg.findViewById(R.id.lblSputum))
								.setVisibility(View.GONE);
						((RadioGroup) vg.findViewById(R.id.radioGroupchkSputum))
								.clearCheck();
						((RadioGroup) vg.findViewById(R.id.radioGroupchkSputum))
								.setVisibility(View.GONE);
					}
				}

				/*
				 * if (((CheckBox) vg.findViewById(R.id.chkOtherOne)).getId() ==
				 * chk .getId()) { ((EditText)
				 * vg.findViewById(R.id.dtpOtherOne))
				 * .setVisibility(View.VISIBLE); } else if (((CheckBox)
				 * vg.findViewById(R.id.chkOtherTwo)) .getId() == chk.getId()) {
				 * ((EditText) vg.findViewById(R.id.dtpOtherTwo))
				 * .setVisibility(View.VISIBLE); } else if (((CheckBox)
				 * vg.findViewById(R.id.chkOtherThree)) .getId() == chk.getId())
				 * { ((EditText) vg.findViewById(R.id.dtpOtherThree))
				 * .setVisibility(View.VISIBLE); }
				 * 
				 * } else { et.setVisibility(View.GONE); if (((CheckBox)
				 * vg.findViewById(R.id.chkOtherOne)).getId() == chk .getId()) {
				 * ((EditText) vg.findViewById(R.id.dtpOtherOne))
				 * .setVisibility(View.GONE); } else if (((CheckBox)
				 * vg.findViewById(R.id.chkOtherTwo)) .getId() == chk.getId()) {
				 * ((EditText) vg.findViewById(R.id.dtpOtherTwo))
				 * .setVisibility(View.GONE); } else if (((CheckBox)
				 * vg.findViewById(R.id.chkOtherThree)) .getId() == chk.getId())
				 * { ((EditText) vg.findViewById(R.id.dtpOtherThree))
				 * .setVisibility(View.GONE); } }
				 */

			}
		});

	}

	private void loadguifrmpersonrelation(ViewGroup v) {

		if (CommonStaticClass.getSkip("Comp", "tblMainQues", dbHelper)
				.equalsIgnoreCase("2")) {
			CommonStaticClass.findOutNextSLNo(
					CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar(),
					CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQnext1());
			CommonStaticClass.nextQuestion(ParentActivity.this);
		}
		qqq = (TextView) v.findViewById(R.id.qqq);

		qName = CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar();

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

		loadDataPersonRelation(v);
		final ViewGroup vg = v;

		saveNxtButton = (Button) v.findViewById(R.id.saveNxtButton);

		saveNxtButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (validatePersonRelation(vg))
					updateTableDataFrmPersonRelation(vg);
				else
					CommonStaticClass.showMyAlert(thisactivity, "Message",
							"Please fill all fields correctly");

				// preserveState();
			}

		});
		clButton = (Button) v.findViewById(R.id.clButton);
		clButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				// resetViewGroup((ViewGroup) v);
			}

		});

		/*
		 * notesButton = (Button) v.findViewById(R.id.btnNote);
		 * notesButton.setOnClickListener(new OnClickListener() {
		 * 
		 * public void onClick(View v) { // TODO Auto-generated method stub
		 * FraNotes();
		 * 
		 * }
		 * 
		 * });
		 */
		prevButton = (Button) v.findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				userPressedPrevious(ParentActivity.this);
			}

		});

	}

	protected boolean validatePersonRelation(ViewGroup v) {

		boolean validated = true;

		if (!Validate.hasText(((EditText) v.findViewById(R.id.admissiondate))))
			validated = false;
		if (!Validate.hasText(((Spinner) v.findViewById(R.id.sp1))))
			validated = false;
		/*
		 * if (!Validate.hasText(editLastName)) validated = false; if
		 * (!Validate.hasText(editAddress)) validated = false; if
		 * (!Validate.hasText(editCity)) validated = false; if
		 * (!Validate.isPostalCode(editPostalCode, true)) validated = false; if
		 * (!Validate.isPhoneNumber(editDayPhone, true)) validated = false; if
		 * (!Validate.isPhoneNumber(editEveningPhone, false)) validated = false;
		 * if (!Validate.isPhoneNumber(editMobilePhone, false)) validated =
		 * false; if (!Validate.isEmailAddress(editEmailAddress, false))
		 * validated = false;
		 */

		String edate = CommonStaticClass.getSkip("EntryDate", "tblMainQues",
				dbHelper);
		// String[] splitentrydate = edate.split("/");

		String AdmDate = ((EditText) v.findViewById(R.id.admissiondate))
				.getText().toString();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		// String[] d = (((EditText)
		// v.findViewById(R.id.dtpFever)).getText().toString()).split("/");
		String[] splitentrydate = edate.split("/");

		// String dateInString = splitentrydate[0] +"-"+ splitentrydate[1] + "-"
		// + splitentrydate[2];

		/*
		 * Date date = null, denddate = null; try { date =
		 * formatter.parse(dateInString); } catch (ParseException e1) { // TODO
		 * Auto-generated catch block e1.printStackTrace(); }
		 */

		// /////////////////////////////////////////////////////////////////////////
		/*
		 * if (CommonStaticClass.IsSeconDateGraterThanFirstDate(((EditText)
		 * v.findViewById(R.id.admissiondate)) .getText().toString(), edate)) {
		 * CommonStaticClass.showMyAlert(thisactivity, "Error",
		 * "Please input correct Fever date"); return false; }
		 */

		String[] d = (((EditText) v.findViewById(R.id.admissiondate)).getText()
				.toString()).split("/");

		String dateInString = d[2] + "-" + d[1] + "-" + d[0];

		Date date = null, denddate = null;
		if (dateInString.length() <= 0) {
			CommonStaticClass.showMyAlert(thisactivity, "Error",
					"Please input date");
			return false;
		}
		try {
			date = formatter.parse(dateInString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Calendar startdate = Calendar.getInstance();
		Calendar enddate = Calendar.getInstance();

		startdate.setTime(date);

		String senddate = enddate.get(Calendar.YEAR)
				+ "-"
				+ CommonStaticClass.SetpadLeft(
						String.valueOf(enddate.get(Calendar.MONTH) + 1),
						enddate.get(Calendar.MONTH)) + "-"
				+ enddate.get(Calendar.DAY_OF_MONTH);

		try {
			denddate = formatter.parse(senddate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		enddate.setTime(denddate);

		int x = CommonStaticClass.DayDifferenceBackwardWithMonth(startdate,
				enddate);

		if (x > 8) {
			CommonStaticClass.showMyAlert(thisactivity, "Error",
					"Fever Date can not be more than 7");
			return false;

		}

		else if (x == 1) {

			// return false;

		}

		else if (x == 2) {

			// return false;

		} else if (x == 3) {

			// return false;

		} else if (x == 4) {

			// return false;

		} else if (x == 5) {

			// return false;

		} else if (x == 6) {

			// return false;

		}

		else if (x == 7) {

			// return false;

		} else if (x == 0) {

			// return false;

		} else {

			CommonStaticClass.showMyAlert(thisactivity, "Message",
					"Please fill admission date correctly");
			validated = false;

		}

		return validated;

		// ///////////////////////////////////////////////////////////////////////////

		/*
		 * Calendar startdate = Calendar.getInstance(); Calendar enddate =
		 * Calendar.getInstance();
		 * 
		 * 
		 * startdate.setTime(date);
		 * 
		 * String[] splitAdmDate = AdmDate.split("/"); //String AdmDateInString
		 * = splitAdmDate[0] +"-"+
		 * CommonStaticClass.SetpadLeft(splitAdmDate[1],Integer
		 * .parseInt(splitAdmDate[1])) + "-" + splitAdmDate[2]; String
		 * AdmDateInString = splitAdmDate[0] +"-"+
		 * CommonStaticClass.SetpadLeft(splitAdmDate
		 * [1],Integer.parseInt(splitAdmDate[1])) + "-" + splitAdmDate[2];
		 * 
		 * String senddate = AdmDateInString;//enddate.get(Calendar.YEAR) + "-"+
		 * CommonStaticClass
		 * .SetpadLeft(String.valueOf(enddate.get(Calendar.MONTH) + 1),
		 * enddate.get(Calendar.MONTH)) + "-"+
		 * enddate.get(Calendar.DAY_OF_MONTH);
		 * 
		 * try { denddate = formatter.parse(senddate); } catch (ParseException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 * enddate.setTime(denddate);
		 * 
		 * 
		 * CommonStaticClass.IsSeconDateGraterThanFirstDate(date1, date2)
		 */

		/*
		 * Date d1 = Date.parse(edate); Date d2 = new Date(AdmDate);
		 */

		/*
		 * if (enddate.after(startdate)) {
		 * CommonStaticClass.showMyAlert(thisactivity, "Message",
		 * "Please fill admission date correctly"); validated = false; } return
		 * validated;
		 */
	}

	private boolean CheckValidationfrmtravelinfo(ViewGroup v) {
		/*
		 * if (((RadioGroup)v.findViewById(R.id.radioGroup1)).is == false &&
		 * Rb2.Checked == false) {
		 * MessageBox.Show("Please select any option from YES or No choice.");
		 * return false; } if (Rb1.Checked == true) { if (chk1.Checked == false
		 * && chk2.Checked == false && chk3.Checked == false && chk4.Checked ==
		 * false) { MessageBox.Show("Please select any option"); return false; }
		 * 
		 * }
		 */
		return true;
	}

	private void loadguifrmtravelinfo(ViewGroup v) {

		resetViewGroup(v);
		qqq = (TextView) v.findViewById(R.id.qqq);

		qName = CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar();

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

		loadDataTravel(v);
		final ViewGroup vg = v;

		saveNxtButton = (Button) v.findViewById(R.id.saveNxtButton);

		saveNxtButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				updateTableDataFrmTravelInfo(vg);
				// preserveState();
			}

		});
		clButton = (Button) v.findViewById(R.id.clButton);
		clButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				// resetViewGroup((ViewGroup) v);
			}

		});

		notesButton = (Button) v.findViewById(R.id.btnNote);
		notesButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) { // TODO Auto-generated method stub
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

	private void loadguifrmaddress(ViewGroup v) {
		qqq = (TextView) v.findViewById(R.id.qqq);

		qName = CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar();

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

		final ViewGroup vg = v;

		/*
		 * progressDialog = ProgressDialog.show(con, "Loading...",
		 * "Please wait while loading data");
		 */

		/*
		 * new Thread() {
		 * 
		 * public void run() { try { Looper.prepare();
		 */

		loadDataAddress(vg);
		/*
		 * Message msg = new Message(); msg.what = UPDATEDONE;
		 * handlerFrmHHID.sendMessage(msg);
		 * 
		 * } catch (Exception lg) { progressDialog.dismiss();
		 * CommonStaticClass.showFinalAlert(con,
		 * "An Error occured in load method");
		 * 
		 * } finally { progressDialog.dismiss(); } Looper.loop(); }
		 * 
		 * }.start();
		 */

		saveNxtButton = (Button) v.findViewById(R.id.saveNxtButton);

		saveNxtButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				/*
				 * try { progressDialog = ProgressDialog.show(con, "Saving...",
				 * "Please wait while saving.");
				 * 
				 * Handler refresh = new Handler(Looper.getMainLooper());
				 * 
				 * refresh.post(new Runnable() {
				 * 
				 * public void run() {
				 * 
				 * updateTableDataFrmAddress(vg);
				 * 
				 * }
				 * 
				 * }); } catch (Exception lg) { // progressDialog.dismiss();
				 * CommonStaticClass.showFinalAlert(con,
				 * "An Error occured in Save method");
				 * 
				 * } finally { progressDialog.dismiss(); }
				 */

				/*
				 * progressDialog = ProgressDialog.show(con, "Saving...",
				 * "Please wait while saving.");
				 * 
				 * Thread thread=new Thread(new Runnable() {
				 * 
				 * @Override public void run() {
				 * 
				 * //do your parsing; //But do not update user interface here
				 * updateTableDataFrmAddress(vg); Message msg = new Message();
				 * msg.what = UPDATEDONE; handlerFrmHHID.sendMessage(msg); } });
				 * 
				 * thread.start();
				 */

				/*
				 * new Thread() {
				 * 
				 * public void run() { try { Looper.prepare();
				 */

				updateTableDataFrmAddress(vg);
				/*
				 * Message msg = new Message(); msg.what = UPDATEDONE;
				 * handlerFrmHHID.sendMessage(msg);
				 * 
				 * } catch (Exception lg) { // progressDialog.dismiss();
				 * CommonStaticClass.showFinalAlert(con,
				 * "An Error occured in Save method");
				 * 
				 * } finally { progressDialog.dismiss(); } Looper.loop(); }
				 * 
				 * }.start();
				 */

				// preserveState();
			}

		});
		clButton = (Button) v.findViewById(R.id.clButton);
		clButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				// resetViewGroup((ViewGroup) v);
			}

		});
		/*
		 * notesButton = (Button) v.findViewById(R.id.btnNote);
		 * notesButton.setOnClickListener(new OnClickListener() {
		 * 
		 * public void onClick(View v) { // TODO Auto-generated method stub
		 * FraNotes();
		 * 
		 * }
		 * 
		 * });
		 */
		prevButton = (Button) v.findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				userPressedPrevious(ParentActivity.this);
			}

		});

	}

	private void updateTableDataFrmAddress(ViewGroup vg) {
		/*
		 * if (!IsValidEntry(vg)) { DisplayToast(thisactivity,
		 * "Please fill all fields", 1); return; }
		 */
		if (CheckValidationFrmAddress(vg) == false) {
			return;
		}
		String sql = String.format("");

		try {

			String HCWorker = "", PlWorker = "", Plraising = "", occupation = "";
			if (((RadioButton) ((RadioGroup) vg
					.findViewById(R.id.radioGrouphcw))
					.findViewById(R.id.radio0)).isChecked()) {
				HCWorker = "1";
			} else if (((RadioButton) ((RadioGroup) vg
					.findViewById(R.id.radioGrouphcw))
					.findViewById(R.id.radio1)).isChecked()) {
				HCWorker = "2";
			}

			if (((RadioButton) ((RadioGroup) vg
					.findViewById(R.id.radioGrouppoultryworker))
					.findViewById(R.id.radio0)).isChecked()) {
				PlWorker = "1";
			} else if (((RadioButton) ((RadioGroup) vg
					.findViewById(R.id.radioGrouppoultryworker))
					.findViewById(R.id.radio1)).isChecked()) {
				PlWorker = "2";
			}

			if (((RadioButton) ((RadioGroup) vg
					.findViewById(R.id.radioGrouppoultryraising))
					.findViewById(R.id.radio0)).isChecked()) {
				Plraising = "1";
			} else if (((RadioButton) ((RadioGroup) vg
					.findViewById(R.id.radioGrouppoultryraising))
					.findViewById(R.id.radio1)).isChecked()) {
				Plraising = "2";
			}
			if (((Spinner) vg.findViewById(R.id.spyear)).getSelectedItem()
					.toString().length() > 0) {

				if (Integer.parseInt(((Spinner) vg.findViewById(R.id.spyear))
						.getSelectedItem().toString()) <= 10) {

					occupation = "14";
				} else {
					occupation = ((Spinner) vg.findViewById(R.id.spoccupation))
							.getSelectedItem()
							.toString()
							.substring(
									0,
									((Spinner) vg
											.findViewById(R.id.spoccupation))
											.getSelectedItem().toString()
											.lastIndexOf(":") - 1);
				} /*
				 * else {
				 * 
				 * if(Integer.parseInt(((Spinner) vg.findViewById(R.id.spyear))
				 * .getSelectedItem().toString())<=10) { occupation = "14";}
				 */

			} else {
				occupation = "";
			}

			sql = String
					.format("Update %s Set Name = '%s',  HHHead='%s', Vill= '%s', UnionName='%s', PSCode= '%s', DistCode='%s', Location='%s', Phone='%s', Phone1='%s', "
							+ "AgeY='%s', AgeM='%s', AgeD='%s', Sex ='%s', occupCode ='%s', HCWorker='%s', PlWorker='%s', Plraising='%s', EditBy='%s', EditDate='%s' "
							+ " WHERE dataid = '%s'",
							CommonStaticClass
									.GetTableName(CommonStaticClass.questionMap
											.get(CommonStaticClass.currentSLNo)
											.getQvar()),

							((EditText) vg.findViewById(R.id.txtName))
									.getText().toString(),
							((EditText) vg.findViewById(R.id.txtHHHead))
									.getText().toString(),
							((EditText) vg.findViewById(R.id.txtVillage))
									.getText().toString(),
							((EditText) vg.findViewById(R.id.txtUnion))
									.getText().toString(),

							CommonStaticClass.GetSpinnerValue(((Spinner) vg
									.findViewById(R.id.spthana))),
							/*
							 * ((Spinner) vg.findViewById(R.id.spthana))
							 * .getSelectedItem() .toString() .substring( 0,
							 * ((Spinner) vg .findViewById(R.id.spthana))
							 * .getSelectedItem() .toString() .lastIndexOf(":")
							 * - 1),
							 */

							CommonStaticClass.GetSpinnerValue(((Spinner) vg
									.findViewById(R.id.spdist))),
							/*
							 * ((Spinner) vg.findViewById(R.id.spdist))
							 * .getSelectedItem() .toString() .substring( 0,
							 * ((Spinner) vg .findViewById(R.id.spdist))
							 * .getSelectedItem() .toString() .lastIndexOf(":")
							 * - 1),
							 */

							/*
							 * ((Spinner) vg.findViewById(R.id.splocation))
							 * .getSelectedItem() .toString() .substring( 0,
							 * ((Spinner) vg .findViewById(R.id.splocation))
							 * .getSelectedItem() .toString() .lastIndexOf(":")
							 * - 1),
							 */

							CommonStaticClass.GetSpinnerValue(((Spinner) vg
									.findViewById(R.id.splocation))),

							((EditText) vg.findViewById(R.id.txtphone1))
									.getText().toString(),
							((EditText) vg.findViewById(R.id.txtphone2))
									.getText().toString(),

							((Spinner) vg.findViewById(R.id.spyear))
									.getSelectedItem().toString(),
							((Spinner) vg.findViewById(R.id.spmonth))
									.getSelectedItem().toString(),
							((Spinner) vg.findViewById(R.id.spday))
									.getSelectedItem().toString(),
							((Spinner) vg.findViewById(R.id.spsex))
									.getSelectedItem()
									.toString()
									.substring(
											0,
											((Spinner) vg
													.findViewById(R.id.spsex))
													.getSelectedItem()
													.toString()
													.lastIndexOf(":") - 1),
							/*
							 * ((Spinner) vg.findViewById(R.id.spoccupation))
							 * .getSelectedItem() .toString() .substring( 0,
							 * ((Spinner) vg .findViewById(R.id.spoccupation))
							 * .getSelectedItem() .toString() .lastIndexOf(":")
							 * - 1)
							 */
							occupation, HCWorker, PlWorker, Plraising,
							CommonStaticClass.userSpecificId, CommonStaticClass
									.GetCurrentDate(), CommonStaticClass.dataId);

			if (dbHelper.executeDMLQuery(sql)) {

				CommonStaticClass.findOutNextSLNo(
						CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQvar(),
						CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQnext1());
				CommonStaticClass.nextQuestion(ParentActivity.this);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private Boolean CheckValidationFrmAddress(ViewGroup v) {

		// Checking Name
		if (((EditText) v.findViewById(R.id.txtName)).getText().toString()
				.length() <= 0) {
			CommonStaticClass.showMyAlert(thisactivity, "Missing info",
					"Please type Name");

			((EditText) v.findViewById(R.id.txtName)).requestFocus();
			return false;
		}

		if (((Spinner) v.findViewById(R.id.spsex)).getSelectedItem().toString()
				.length() <= 0) {
			CommonStaticClass.showMyAlert(thisactivity, "Missing info",
					"Please select Sex information");
			return false;
		}

		// Checking wheather age related information selected properly or not.
		if (((Spinner) v.findViewById(R.id.spyear)).getSelectedItem()
				.toString().length() <= 0) {
			CommonStaticClass.showMyAlert(thisactivity, "Missing info",
					"Please select Year");
			return false;
		}

		// Checking the age year is less then 5, if less then 5 then month value
		// is must
		if (Integer.parseInt(((Spinner) v.findViewById(R.id.spyear))
				.getSelectedItem().toString()) < 5) {
			if (((Spinner) v.findViewById(R.id.spmonth)).getSelectedItem()
					.toString().length() <= 0) {
				CommonStaticClass.showMyAlert(thisactivity, "Missing info",
						"Please select Age Month information");
				return false;
			}
			if (((Spinner) v.findViewById(R.id.spday)).getSelectedItem()
					.toString().length() <= 0) {

				CommonStaticClass.showMyAlert(thisactivity, "Missing info",
						"Please select Age Day information");
				return false;
			}
		} else {
			((Spinner) v.findViewById(R.id.spmonth)).setSelection(0);

		}

		if (((EditText) v.findViewById(R.id.txtphone1)).getText().toString()
				.length() > 0) {
			if ((((EditText) v.findViewById(R.id.txtphone1)).getText()
					.toString().length() != 11)) {
				CommonStaticClass.showMyAlert(thisactivity, "Missing info",
						"Phone number Should be 11 digit");

				return false;
			}
		}
		// }
		if (((EditText) v.findViewById(R.id.txtphone2)).getText().toString()
				.length() > 0) {
			if ((((EditText) v.findViewById(R.id.txtphone2)).getText()
					.toString().length() != 11)) {
				CommonStaticClass.showMyAlert(thisactivity, "Missing info",
						"Phone number Should be 11 digit");

				return false;
			}
			if (((EditText) v.findViewById(R.id.txtphone1)).getText()
					.toString().length() <= 0) {
				CommonStaticClass.showMyAlert(thisactivity, "Missing info",
						"Please input Phone 1 first");
				return false;
			}
		}

		// Checking district information given or not
		if (((Spinner) v.findViewById(R.id.spdist)).getSelectedItem()
				.toString().length() <= 0) {
			CommonStaticClass.showMyAlert(thisactivity, "Missing info",
					"Please select district information");

			return false;
		}

		// Checking Police station information given or not
		if (((Spinner) v.findViewById(R.id.spthana)).getSelectedItem()
				.toString().length() <= 0) {

			CommonStaticClass.showMyAlert(thisactivity, "Missing info",
					"Please select police station information");

			return false;
		}

		if (((Spinner) v.findViewById(R.id.splocation)).getSelectedItem()
				.toString().length() <= 0) {

			CommonStaticClass.showMyAlert(thisactivity, "Missing info",
					"Please select any location");

			return false;
		}

		if (Integer.parseInt(((Spinner) v.findViewById(R.id.spyear))
				.getSelectedItem().toString()) < 12) {
			return true;

		} else {
			// Checking the health care option selected or not
			if (((RadioGroup) v.findViewById(R.id.radioGrouphcw))
					.getCheckedRadioButtonId() == -1) {
				CommonStaticClass.showMyAlert(thisactivity, "Missing info",
						"Please select any option for Health Care Worker");

				return false;
			}

			// Checking the Poultry Worker option selected or not
			if (((RadioGroup) v.findViewById(R.id.radioGrouppoultryworker))
					.getCheckedRadioButtonId() == -1)

			{
				CommonStaticClass.showMyAlert(thisactivity, "Missing info",
						"Please select any option for Poultry Worker");

				return false;
			}

			// Checking the Poultry Worker option selected or not

			if (((RadioGroup) v.findViewById(R.id.radioGrouppoultryraising))
					.getCheckedRadioButtonId() == -1) {

				CommonStaticClass.showMyAlert(thisactivity, "Missing info",
						"Please select any option for Poultry Raising");

				return false;
			}
		}

		if (((RadioGroup) v.findViewById(R.id.radioGrouppoultryraising))
				.getCheckedRadioButtonId() == -1) {

			CommonStaticClass.showMyAlert(thisactivity, "Missing info",
					"Please select any option for Poultry Raising");

			return false;
		}
		return true;

	}

	private boolean CheckValidationTravelinfo(ViewGroup v) {
		if (((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroup1))
				.findViewById(R.id.radio1)).isChecked() == false
				&& ((RadioButton) ((RadioGroup) v
						.findViewById(R.id.radioGroup1))
						.findViewById(R.id.radio0)).isChecked() == false) {
			// CommonStaticClass.showMyAlert(thisactivity, "Message",
			// "Please select any option from YES or No choice.");
			return false;
		}
		if (((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroup2))
				.findViewById(R.id.radio1)).isChecked() == false
				&& ((RadioButton) ((RadioGroup) v
						.findViewById(R.id.radioGroup2))
						.findViewById(R.id.radio0)).isChecked() == false) {
			// CommonStaticClass.showMyAlert(thisactivity, "Message",
			// "Please select any option from YES or No choice.");
			return false;
		}

		if (((RadioButton) ((RadioGroup) v.findViewById(R.id.radioGroup3))
				.findViewById(R.id.radio1)).isChecked() == false
				&& ((RadioButton) ((RadioGroup) v
						.findViewById(R.id.radioGroup3))
						.findViewById(R.id.radio0)).isChecked() == false) {
			// CommonStaticClass.showMyAlert(thisactivity, "Message",
			// "Please select any option from YES or No choice.");
			return false;
		}
		return true;
	}

	private void updateTableDataFrmTravelInfo(ViewGroup vg) {
		String sql = String.format("");

		try {

			if (CheckValidationTravelinfo(vg) == false) {
				CommonStaticClass.showMyAlert(thisactivity, "Message",
						"Please select any option from YES or No choice.");
				return;
			}

			String travel_7_local = "", trav_pla_local = "", travel_7_international = "", trav_pla_international = "", trav_Pla_others = "", travel_30_international = "", trav_pla30_international = "", trav_Pla30_others = "";

			if (((RadioButton) ((RadioGroup) vg.findViewById(R.id.radioGroup1))
					.findViewById(R.id.radio0)).isChecked()) {
				travel_7_local = "1";
				trav_pla_local = ((Spinner) vg.findViewById(R.id.sp1))
						.getSelectedItem()
						.toString()
						.substring(
								0,
								((Spinner) vg.findViewById(R.id.sp1))
										.getSelectedItem().toString()
										.lastIndexOf(":")).trim();
			} else if (((RadioButton) ((RadioGroup) vg
					.findViewById(R.id.radioGroup1)).findViewById(R.id.radio1))
					.isChecked()) {
				travel_7_local = "2";
				trav_pla_local = null;
			}

			// International 1

			if (((RadioButton) ((RadioGroup) vg.findViewById(R.id.radioGroup2))
					.findViewById(R.id.radio0)).isChecked()) {
				travel_7_international = "1";
				trav_pla_international = ((Spinner) vg.findViewById(R.id.sp2))
						.getSelectedItem()
						.toString()
						.substring(
								0,
								((Spinner) vg.findViewById(R.id.sp2))
										.getSelectedItem().toString()
										.lastIndexOf(":")).trim();
				if (trav_pla_international.equalsIgnoreCase("17")) {
					trav_Pla_others = ((EditText) vg.findViewById(R.id.et1))
							.getText().toString();
				} else {
					trav_Pla_others = null;
				}
			} else if (((RadioButton) ((RadioGroup) vg
					.findViewById(R.id.radioGroup2)).findViewById(R.id.radio1))
					.isChecked()) {
				travel_7_international = "2";
				trav_pla_international = null;

			}

			// International 2
			if (((RadioButton) ((RadioGroup) vg.findViewById(R.id.radioGroup3))
					.findViewById(R.id.radio0)).isChecked()) {
				travel_30_international = "1";
				trav_pla30_international = ((Spinner) vg.findViewById(R.id.sp3))
						.getSelectedItem()
						.toString()
						.substring(
								0,
								((Spinner) vg.findViewById(R.id.sp3))
										.getSelectedItem().toString()
										.lastIndexOf(":")).trim();
				if (trav_pla30_international.equalsIgnoreCase("17")) {
					trav_Pla30_others = ((EditText) vg.findViewById(R.id.et2))
							.getText().toString();
				} else {
					trav_Pla30_others = null;
				}
			} else if (((RadioButton) ((RadioGroup) vg
					.findViewById(R.id.radioGroup3)).findViewById(R.id.radio1))
					.isChecked()) {
				travel_30_international = "2";
				trav_pla30_international = null;

			}

			sql = String
					.format("UPDATE '%s' set travel_7_local = '%s', trav_pla_local='%s', travel_7_international='%s', trav_pla_international='%s',"
							+ " trav_Pla_others='%s', travel_30_international='%s', trav_pla30_international='%s', trav_Pla30_others='%s' , EditBy='%s', EditDate='%s'"
							+ " WHERE dataid='%s'",

					CommonStaticClass
							.GetTableName(CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQvar()),
							travel_7_local, trav_pla_local,
							travel_7_international, trav_pla_international,
							trav_Pla_others, travel_30_international,
							trav_pla30_international, trav_Pla30_others,
							CommonStaticClass.userSpecificId, CommonStaticClass
									.GetCurrentDate(), CommonStaticClass.dataId);

			if (dbHelper.executeDMLQuery(sql)) {

				CommonStaticClass.findOutNextSLNo(
						CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQvar(),
						CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQnext1());
				CommonStaticClass.nextQuestion(ParentActivity.this);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void updateTableDataFrmPersonRelation(ViewGroup vg) {

		try {

			String AdmDate = ((EditText) vg.findViewById(R.id.admissiondate))
					.getText().toString();

			String DisDate = "";
			String DgCode = "";
			String DgOthers = "";
			String OutCome = "";

			if (((CheckBox) vg.findViewById(R.id.checkBox1)).isChecked()) {
				DisDate = ((EditText) vg.findViewById(R.id.dischargedate))
						.getText().toString();

			} else {
				DisDate = "";
			}

			DgCode = ((Spinner) vg.findViewById(R.id.sp1))
					.getSelectedItem()
					.toString()
					.substring(
							0,
							((Spinner) vg.findViewById(R.id.sp1))
									.getSelectedItem().toString()
									.lastIndexOf(":")).trim();

			if (DgCode.equalsIgnoreCase("99")) {
				DgOthers = ((EditText) vg.findViewById(R.id.et1)).getText()
						.toString();
			} else {
				DgOthers = null;
			}

			OutCome = ((Spinner) vg.findViewById(R.id.sp2))
					.getSelectedItem()
					.toString()
					.substring(
							0,
							((Spinner) vg.findViewById(R.id.sp2))
									.getSelectedItem().toString()
									.lastIndexOf(":"));

			String sql = String.format(
					"UPDATE '%s' set AdmDate = '%s', DisDate='%s', DgCode='%s', "
							+ "DgOthers='%s',"
							+ " OutCome='%s', EditBy='%s', EditDate='%s'"
							+ " WHERE dataid='%s'",

					CommonStaticClass
							.GetTableName(CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQvar()),
					AdmDate, DisDate, DgCode, DgOthers, OutCome,
					CommonStaticClass.userSpecificId, CommonStaticClass
							.GetCurrentDate(), CommonStaticClass.dataId);

			if (dbHelper.executeDMLQuery(sql)) {

				CommonStaticClass.findOutNextSLNo(
						CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQvar(),
						CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQnext1());
				CommonStaticClass.nextQuestion(ParentActivity.this);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void loadDataTravel(final ViewGroup v) {

		String dist = "SELECT '' AS DistName UNION SELECT (" + "" + "DistCode"
				+ "" + "|| " + "" + "' : '" + " || " + "DistName" + ") AS "
				+ "D" + " from District ORDER BY DistName" + "";

		/*
		 * ArrayList<String> ids = new ArrayList<String>(); ids.add("");
		 * ids.add("1 : Kingdom of Saudi Arabia");
		 * ids.add("2 : United Arab Emirettes"); ids.add("3 : Malaysia");
		 * ids.add("4: Kuwait"); ids.add("5 : Oman"); ids.add("6 : Singapore");
		 * ids.add("7 : Bahrain"); ids.add("8 : Qatar"); ids.add("9 : Libya");
		 * ids.add("10 : Lebanon"); ids.add("11 : Jordan");
		 * ids.add("12 : North Korea"); ids.add("13 : Italy");
		 * ids.add("14 : Brunei"); ids.add("15 : Mauritius");
		 * ids.add("16 : China"); ids.add("17 : Others");
		 */

		CommonStaticClass.FillCombo(thisactivity, dbHelper, dist,
				((Spinner) v.findViewById(R.id.sp1)));

		((Spinner) v.findViewById(R.id.sp2))
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
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
													.lastIndexOf(":"))).trim();
							if (sResCode.length() > 0) {
								if (sResCode.equalsIgnoreCase("17")) {
									((EditText) v.findViewById(R.id.et1))
											.setEnabled(true);

								} else {
									((EditText) v.findViewById(R.id.et1))
											.setEnabled(false);
								}
							}

						}

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});

		((Spinner) v.findViewById(R.id.sp3))
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
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
													.lastIndexOf(":"))).trim();
							if (sResCode.length() > 0) {
								if (sResCode.equalsIgnoreCase("17")) {
									((EditText) v.findViewById(R.id.et2))
											.setEnabled(true);

								} else {
									((EditText) v.findViewById(R.id.et2))
											.setEnabled(false);
								}
							}

						}

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});

		ArrayList<String> ids = new ArrayList<String>();
		ids.add("");
		ids.add("1 : Kingdom of Saudi Arabia");
		ids.add("2 : United Arab Emirettes");
		ids.add("3 : Malaysia");
		ids.add("4: Kuwait");
		ids.add("5 : Oman");
		ids.add("6 : Singapore");
		ids.add("7 : Bahrain");
		ids.add("8 : Qatar");
		ids.add("9 : Libya");
		ids.add("10 : Lebanon");
		ids.add("11 : Jordan");
		ids.add("12 : North Korea");
		ids.add("13 : Italy");
		ids.add("14 : Brunei");
		ids.add("15 : Mauritius");
		ids.add("16 : China");
		ids.add("17 : Others");

		CommonStaticClass.FillCombo(thisactivity, ids,
				((Spinner) v.findViewById(R.id.sp2)));

		CommonStaticClass.FillCombo(thisactivity, ids,
				((Spinner) v.findViewById(R.id.sp3)));

		((Spinner) v.findViewById(R.id.sp1)).setVisibility(View.GONE);

		((RadioGroup) v.findViewById(R.id.radioGroup1))
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup group, int checkedId) {

						if (((RadioButton) group.findViewById(R.id.radio0))
								.isChecked() == true) {
							((Spinner) v.findViewById(R.id.sp1))
									.setVisibility(View.VISIBLE);
							((TextView) v.findViewById(R.id.lblsp1))
									.setVisibility(View.VISIBLE);
						} else {
							((Spinner) v.findViewById(R.id.sp1))
									.setVisibility(View.GONE);
							((TextView) v.findViewById(R.id.lblsp1))
									.setVisibility(View.GONE);
						}
					}
				});

		((Spinner) v.findViewById(R.id.sp2)).setVisibility(View.GONE);

		((RadioGroup) v.findViewById(R.id.radioGroup2))
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					public void onCheckedChanged(RadioGroup group, int checkedId) {

						if (((RadioButton) group.findViewById(R.id.radio0))
								.isChecked() == true) {
							((Spinner) v.findViewById(R.id.sp2))
									.setVisibility(View.VISIBLE);
							((TextView) v.findViewById(R.id.lblsp2))
									.setVisibility(View.VISIBLE);
						} else {
							((Spinner) v.findViewById(R.id.sp2))
									.setVisibility(View.GONE);
							((TextView) v.findViewById(R.id.lblsp2))
									.setVisibility(View.GONE);
						}
					}
				});

		((Spinner) v.findViewById(R.id.sp3)).setVisibility(View.GONE);

		((EditText) v.findViewById(R.id.et1)).setEnabled(false);
		((EditText) v.findViewById(R.id.et2)).setEnabled(false);
		((RadioGroup) v.findViewById(R.id.radioGroup3))
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					public void onCheckedChanged(RadioGroup group, int checkedId) {

						if (((RadioButton) group.findViewById(R.id.radio0))
								.isChecked() == true) {
							((Spinner) v.findViewById(R.id.sp3))
									.setVisibility(View.VISIBLE);
							((TextView) v.findViewById(R.id.lblsp3))
									.setVisibility(View.VISIBLE);
						} else {
							((Spinner) v.findViewById(R.id.sp3))
									.setVisibility(View.GONE);
							((TextView) v.findViewById(R.id.lblsp3))
									.setVisibility(View.GONE);
						}
					}
				});

		String sql = String
				.format("Select travel_7_local, trav_pla_local, travel_7_international, trav_pla_international, "
						+ "trav_Pla_others,travel_30_international, trav_pla30_international, trav_Pla30_others from '%s' where dataid = '%s' ",
						CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getTablename(),
						CommonStaticClass.dataId);

		Cursor cur = null;

		try {

			cur = dbHelper.getQueryCursor(sql);

			if (cur.getCount() > 0) {

				if (cur.moveToFirst()) {

					do {
						// District
						if (cur.getString(0).equals(null)
								|| cur.getString(0).equalsIgnoreCase("")) {
							((RadioGroup) v.findViewById(R.id.radioGroup1))
									.clearCheck();
						} else if (cur.getString(0).equalsIgnoreCase("1")) {

							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroup1))
									.findViewById(R.id.radio0))
									.setChecked(true);

							// for (int i = 0; i < ((Spinner) v
							// .findViewById(R.id.sp1)).getCount(); i++) {

							CommonStaticClass.SetSpinnerValue(thisactivity,
									((Spinner) v.findViewById(R.id.sp1)),
									cur.getString(1));

							/*
							 * if (((Spinner)
							 * v.findViewById(R.id.sp1)).getItemAtPosition
							 * (i).toString().contains(cur.getString(1))) {
							 * ((Spinner)
							 * v.findViewById(R.id.sp1)).setSelection(i); }
							 */
							// }

						} else if (cur.getString(0).equalsIgnoreCase("2")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroup1))
									.findViewById(R.id.radio1))
									.setChecked(true);
						}

						// international 1

						if (cur.getString(2).equals(null)
								|| cur.getString(2).equalsIgnoreCase("")) {
							((RadioGroup) v.findViewById(R.id.radioGroup2))
									.clearCheck();
						} else if (cur.getString(2).equalsIgnoreCase("1")) {

							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroup2))
									.findViewById(R.id.radio0))
									.setChecked(true);

							for (int i = 0; i < ((Spinner) v
									.findViewById(R.id.sp2)).getCount(); i++) {

								if (((Spinner) v.findViewById(R.id.sp2))
										.getItemAtPosition(i).toString()
										.contains(cur.getString(3))) {
									((Spinner) v.findViewById(R.id.sp2))
											.setSelection(i);
								}
								((EditText) v.findViewById(R.id.et1))
										.setText(cur.getString(4));
							}

						} else if (cur.getString(2).equalsIgnoreCase("2")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroup2))
									.findViewById(R.id.radio1))
									.setChecked(true);

						}

						// international 2

						if (cur.getString(5).equals(null)
								|| cur.getString(5).equalsIgnoreCase("")) {
							((RadioGroup) v.findViewById(R.id.radioGroup3))
									.clearCheck();
						} else if (cur.getString(5).equalsIgnoreCase("1")) {

							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroup3))
									.findViewById(R.id.radio0))
									.setChecked(true);

							for (int i = 0; i < ((Spinner) v
									.findViewById(R.id.sp3)).getCount(); i++) {

								if (((Spinner) v.findViewById(R.id.sp3))
										.getItemAtPosition(i).toString()
										.contains(cur.getString(6))) {
									((Spinner) v.findViewById(R.id.sp3))
											.setSelection(i);
								}
								if (!cur.getString(7).equalsIgnoreCase("null"))
									((EditText) v.findViewById(R.id.et2))
											.setText(cur.getString(7));
							}

						} else if (cur.getString(5).equalsIgnoreCase("2")) {

							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGroup3))
									.findViewById(R.id.radio1))
									.setChecked(true);

						}

					} while (cur.moveToNext());

				}

			}

		} catch (Exception e) {

			// TODO: handle exception

		}

	}

	private void loadDataPersonRelation(final ViewGroup v) {

		pickDate = ((EditText) v.findViewById(R.id.admissiondate));
		/*
		 * if (!(pickDate.getText().toString().length() > 0))
		 * updateDisplay("date");
		 */

		dischargeDate = ((EditText) v.findViewById(R.id.dischargedate));
		/*
		 * if (!(dischargeDate.getText().toString().length() > 0))
		 * updateDisplay("date");
		 */

		dischargeDate.setVisibility(View.INVISIBLE);

		pickDate.setOnTouchListener(new View.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub

				final Calendar c = Calendar.getInstance();
				dateYear = c.get(Calendar.YEAR);
				dateMonth = c.get(Calendar.MONTH);
				dateDay = c.get(Calendar.DAY_OF_MONTH);

				showDialog(DATE_DIALOG);
				return false;
			}
		});

		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		dischargeDate.setOnTouchListener(new View.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub

				final Calendar c = Calendar.getInstance();
				dateYear = c.get(Calendar.YEAR);
				dateMonth = c.get(Calendar.MONTH);
				dateDay = c.get(Calendar.DAY_OF_MONTH);

				showDialog(DATE_DIALOG);
				return false;
			}
		});

		String dist = "SELECT '' AS DgCode UNION SELECT (" + "" + "DgCode" + ""
				+ "|| " + "" + "' : '" + " || " + "Name" + ") AS " + "D"
				+ " from DiagnosisCode ORDER BY DgCode" + "";

		CommonStaticClass.FillCombo(thisactivity, dbHelper, dist,
				((Spinner) v.findViewById(R.id.sp1)));

		/*
		 * ((Spinner) v.findViewById(R.id.sp2)) .setOnItemSelectedListener(new
		 * OnItemSelectedListener() {
		 * 
		 * @Override public void onItemSelected(AdapterView<?> parent, View
		 * view, int pos, long id) {
		 * 
		 * if (parent.getItemAtPosition(pos).toString().length() > 0) {
		 * 
		 * sResCode = parent .getItemAtPosition(pos) .toString() .substring( 0,
		 * (parent.getItemAtPosition(pos) .toString()
		 * .lastIndexOf(":"))).trim(); if (sResCode.length() > 0) { if
		 * (sResCode.equalsIgnoreCase("99")) { ((EditText)
		 * v.findViewById(R.id.et1)) .setEnabled(true);
		 * 
		 * } else { ((EditText) v.findViewById(R.id.et1)) .setEnabled(false); }
		 * }
		 * 
		 * }
		 * 
		 * }
		 * 
		 * @Override public void onNothingSelected(AdapterView<?> arg0) { //
		 * TODO Auto-generated method stub
		 * 
		 * } });
		 */

		((Spinner) v.findViewById(R.id.sp1))
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
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
													.lastIndexOf(":"))).trim();
							if (sResCode.length() > 0) {
								if (sResCode.equalsIgnoreCase("99")) {
									((EditText) v.findViewById(R.id.et1))
											.setVisibility(view.VISIBLE);

								} else {
									((EditText) v.findViewById(R.id.et1))
											.setVisibility(view.INVISIBLE);
								}
							}

						}

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});

		((CheckBox) v.findViewById(R.id.checkBox1))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton arg0,
							boolean arg1) {
						if (arg1 == true) {
							((EditText) v.findViewById(R.id.dischargedate))
									.setVisibility(View.VISIBLE);
							((TextView) v.findViewById(R.id.textView2))
									.setVisibility(View.VISIBLE);

							((Spinner) v.findViewById(R.id.sp2))
									.setVisibility(View.VISIBLE);
							((TextView) v.findViewById(R.id.lblsp2))
									.setVisibility(View.VISIBLE);
						} else {
							((TextView) v.findViewById(R.id.textView2))
									.setVisibility(View.GONE);
							((EditText) v.findViewById(R.id.dischargedate))
									.setVisibility(View.INVISIBLE);
							((EditText) v.findViewById(R.id.dischargedate))
									.setText("");
							((Spinner) v.findViewById(R.id.sp2))
									.setVisibility(View.INVISIBLE);

							((TextView) v.findViewById(R.id.lblsp2))
									.setVisibility(View.INVISIBLE);
						}
					}
				});

		ArrayList<String> ids = new ArrayList<String>();
		ids.add("1 : Fully recovered");
		ids.add("2 : Partially recovered");
		ids.add("3 : Remains hospitalized");
		ids.add("4 : Transferred");
		ids.add("5 : Death");
		ids.add("9 : Unknown");

		CommonStaticClass.FillCombo(thisactivity, ids,
				((Spinner) v.findViewById(R.id.sp2)));

		((Spinner) v.findViewById(R.id.sp2)).setVisibility(View.INVISIBLE);
		((TextView) v.findViewById(R.id.lblsp2)).setVisibility(View.INVISIBLE);
		String sql = String
				.format("Select AdmDate, DisDate, DgCode, DgOthers, OutCome from tblMainQues where dataid = '%s'",
						CommonStaticClass.dataId);

		Cursor cur = null;

		try {

			cur = dbHelper.getQueryCursor(sql);

			if (cur.getCount() > 0) {

				if (cur.moveToFirst()) {

					do {
						// District
						String intComp = CommonStaticClass.getSkip("Comp",
								"tblMainQues", dbHelper);

						if (intComp.equalsIgnoreCase("1")
								|| intComp.equalsIgnoreCase("3")) {

							if (cur.getString(0) == null) // if
															// (DR["AdmDate"].ToString()
															// ==
															// "")
							{
								/*
								 * ((EditText)
								 * v.findViewById(R.id.admissiondate))
								 * .setText(CommonStaticClass.GetDate());
								 */
							} else {
								((EditText) v.findViewById(R.id.admissiondate))
										.setText(cur.getString(0));
							}
						}

						// if (DR["DisDate"].ToString() == "")
						// {
						// dtpDischDate.Value = System.DateTime.Today;
						// }
						// else
						// {
						// dtpDischDate.Value =
						// Convert.ToDateTime(DR["AdmDate"].ToString());
						// }

						if (cur.getString(1) == null) // if
														// (DR["AdmDate"].ToString()
														// == "")
						{
							((EditText) v.findViewById(R.id.dischargedate))
									.setText(CommonStaticClass.GetDate());
						} else {
							((CheckBox) v.findViewById(R.id.checkBox1))
									.setChecked(true);
							((EditText) v.findViewById(R.id.dischargedate))
									.setText(cur.getString(1));

						}

						if (cur.getString(2) == null) {
							/*
							 * ((RadioGroup) v.findViewById(R.id.radioGroup1))
							 * .clearCheck();
							 */
							((TextView) v.findViewById(R.id.lblother))
									.setVisibility(View.INVISIBLE);
							((EditText) v.findViewById(R.id.et1))
									.setVisibility(View.INVISIBLE);
						} else if (cur.getString(2).equalsIgnoreCase("99")) {

							/*
							 * ((RadioButton) ((RadioGroup) v
							 * .findViewById(R.id.radioGroup1))
							 * .findViewById(R.id.radio0)) .setChecked(true);
							 */

							for (int i = 0; i < ((Spinner) v
									.findViewById(R.id.sp1)).getCount(); i++) {

								if (((Spinner) v.findViewById(R.id.sp1))
										.getItemAtPosition(i).toString()
										.contains(cur.getString(2))) {

									((Spinner) v.findViewById(R.id.sp1))
											.setSelection(i);

									((EditText) v.findViewById(R.id.et1))
											.setVisibility(View.VISIBLE);
									((EditText) v.findViewById(R.id.et1))
											.setText(cur.getString(3));
									((TextView) v.findViewById(R.id.lblother))
											.setVisibility(View.VISIBLE);
								}
							}

						} else {
							for (int i = 0; i < ((Spinner) v
									.findViewById(R.id.sp1)).getCount(); i++) {

								if (((Spinner) v.findViewById(R.id.sp1))
										.getItemAtPosition(i).toString()
										.contains(cur.getString(2))) {
									((Spinner) v.findViewById(R.id.sp1))
											.setSelection(i);

									((EditText) v.findViewById(R.id.et1))
											.setVisibility(View.INVISIBLE);
									((EditText) v.findViewById(R.id.et1))
											.setText(cur.getString(3));
									((TextView) v.findViewById(R.id.lblother))
											.setVisibility(View.INVISIBLE);
								}
							}
						}

						// international 1

						if (cur.getString(3) == null) {

						} else {

							for (int i = 0; i < ((Spinner) v
									.findViewById(R.id.sp2)).getCount(); i++) {

								if (((Spinner) v.findViewById(R.id.sp2))
										.getItemAtPosition(i).toString()
										.contains(cur.getString(4))) {
									((Spinner) v.findViewById(R.id.sp2))
											.setSelection(i);
								}

							}
						}

					} while (cur.moveToNext());

				}

			}

		} catch (Exception e) {

			Log.e("Error", e.getMessage());
			// TODO: handle exception

		}

		if (CommonStaticClass.getSkip("Comp", "tblMainQues", dbHelper)
				.equalsIgnoreCase("1")) {
			CommonStaticClass.SetSpinnerValue(thisactivity,
					((Spinner) v.findViewById(R.id.sp2)), "3");
			((CheckBox) v.findViewById(R.id.checkBox1))
					.setVisibility(View.VISIBLE);
			((TextView) v.findViewById(R.id.textView2))
					.setVisibility(View.GONE);
			((EditText) v.findViewById(R.id.dischargedate))
					.setVisibility(View.GONE);

		} else if (CommonStaticClass.getSkip("Comp", "tblMainQues", dbHelper)
				.equalsIgnoreCase("2")) {
			CommonStaticClass.SetSpinnerValue(thisactivity,
					((Spinner) v.findViewById(R.id.sp1)), "01");

			((CheckBox) v.findViewById(R.id.checkBox1))
					.setVisibility(View.GONE);
			((TextView) v.findViewById(R.id.textView2))
					.setVisibility(View.GONE);
			((EditText) v.findViewById(R.id.dischargedate))
					.setVisibility(View.GONE);

		} else if (CommonStaticClass.getSkip("Comp", "tblMainQues", dbHelper)
				.equalsIgnoreCase("3")) {
			CommonStaticClass.SetSpinnerValue(thisactivity,
					((Spinner) v.findViewById(R.id.sp1)), "17");
			CommonStaticClass.SetSpinnerValue(thisactivity,
					((Spinner) v.findViewById(R.id.sp2)), "3");

			((CheckBox) v.findViewById(R.id.checkBox1))
					.setVisibility(View.GONE);
			((TextView) v.findViewById(R.id.textView2))
					.setVisibility(View.GONE);
			((EditText) v.findViewById(R.id.dischargedate))
					.setVisibility(View.GONE);
		}
	}

	private void loadDataAddress(final ViewGroup v) {

		String dist = "SELECT (" + "" + "DistCode" + "" + "|| " + "" + "' : '"
				+ " || " + "DistName" + ") AS " + "D"
				+ " from District ORDER BY DistName" + "";

		String occu = "SELECT '' AS occupCode UNION ALL SELECT (" + ""
				+ "occupCode" + "" + "|| " + "" + "' : '" + " || "
				+ "Occupation" + ") AS " + "O"
				+ " from tblOccupation ORDER BY occupCode" + "";

		ArrayList<String> ids = new ArrayList<String>();
		ids.add("");
		ids.add("1 : Urban");
		ids.add("2 : Rural");

		CommonStaticClass.FillCombo(thisactivity, dbHelper, dist,
				((Spinner) v.findViewById(R.id.spdist)));

		String s = String.format(
				"select DistCode from Hospital where HosID='%s'",
				CommonStaticClass.userSpecificId);
		Cursor c = dbHelper.getQueryCursor(s);

		((Spinner) v.findViewById(R.id.spdist))
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
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

							String strSQL = String
									.format("SELECT ("
											+ ""
											+ "PSCode"
											+ ""
											+ "|| "
											+ ""
											+ "' : '"
											+ " || "
											+ "PSName"
											+ ") AS "
											+ "O"
											+ " from PoliceStation where DistCode = '%s' ORDER BY PSName"
											+ "", sResCode);

							CommonStaticClass.FillCombo(thisactivity, dbHelper,
									strSQL,
									((Spinner) v.findViewById(R.id.spthana)));

							String sql = "Select PSCode from tblMainQues where dataid = '"
									+ CommonStaticClass.dataId + "'";
							Cursor cur = null;

							try {

								cur = dbHelper.getQueryCursor(sql);

								if (cur.getCount() > 0) {

									if (cur.moveToFirst()) {

										do {

											CommonStaticClass.SetSpinnerValue(
													thisactivity,
													((Spinner) v
															.findViewById(R.id.spthana)),
													cur.getString(0));

										} while (cur.moveToNext());

									}

								}

							} catch (Exception e) {

								// TODO: handle exception

							}
							// String strSQL =
							// "Select PSCode, PSName from PoliceStation where DistCode = '"
							// + sResCode + "' Order By PSName";

						}

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});

		if (c.getCount() > 0) {

			if (c.moveToFirst()) {

				do {

					CommonStaticClass.SetSpinnerValue(thisactivity,
							((Spinner) v.findViewById(R.id.spdist)),
							CommonStaticClass.CheckCursorValue(c, "DistCode"));

				} while (c.moveToNext());

			}
		}

		CommonStaticClass.FillCombo(thisactivity, dbHelper, occu,
				((Spinner) v.findViewById(R.id.spoccupation)));
		CommonStaticClass.FillCombo(thisactivity, ids,
				((Spinner) v.findViewById(R.id.splocation)));

		ids = new ArrayList<String>();
		ids.add("");
		ids.add("1 : Male");
		ids.add("2 : Female");

		CommonStaticClass.FillCombo(thisactivity, ids,
				((Spinner) v.findViewById(R.id.spsex)));

		if (CommonStaticClass.dataId.subSequence(0, 1).equals("3")) {
			CommonStaticClass.ComboAddRangeNumber(thisactivity,
					((Spinner) v.findViewById(R.id.spyear)), 0, 4);
			CommonStaticClass.ComboAddRangeNumber(thisactivity,
					((Spinner) v.findViewById(R.id.spmonth)), 0, 11);
			CommonStaticClass.ComboAddRangeNumber(thisactivity,
					((Spinner) v.findViewById(R.id.spday)), 0, 29);
		} else {
			CommonStaticClass.ComboAddRangeNumber(thisactivity,
					((Spinner) v.findViewById(R.id.spyear)), 0, 100);
			CommonStaticClass.ComboAddRangeNumber(thisactivity,
					((Spinner) v.findViewById(R.id.spmonth)), 0, 11);
			CommonStaticClass.ComboAddRangeNumber(thisactivity,
					((Spinner) v.findViewById(R.id.spday)), 0, 29);
		}

		/*
		 * ((Spinner) v.findViewById(R.id.spyear)).setOnItemSelectedListener(new
		 * OnItemSelectedListener() {
		 * 
		 * @Override public void onItemSelected(AdapterView<?> arg0, View arg1,
		 * int arg2, long arg3) {
		 * 
		 * if() String val = ((Spinner) v .findViewById(R.id.spsur))
		 * .getSelectedItem() .toString();
		 * 
		 * if (val.contains("3")) { ((EditText) v .findViewById(R.id.txtSEIBID))
		 * .setEnabled(true); ((EditText) v .findViewById(R.id.txtSEIBID))
		 * .setVisibility(View.VISIBLE);
		 * 
		 * }
		 * 
		 * }
		 * 
		 * @Override public void onNothingSelected(AdapterView<?> arg0) { //
		 * TODO Auto-generated method stub
		 * 
		 * } });
		 */

		String sql = "Select Name, HHHead, Vill, UnionName, PSCode, occupCode, DistCode, Location, Phone,Phone1, AgeY, AgeM, AgeD, Sex, HCWorker, PlWorker, Plraising, TrvWSevDay, TrvWhere from tblMainQues where dataid = '"
				+ CommonStaticClass.dataId + "'";
		Cursor cur = null;

		try {

			cur = dbHelper.getQueryCursor(sql);

			if (cur.getCount() > 0) {

				if (cur.moveToFirst()) {

					do {

						((EditText) v.findViewById(R.id.txtName)).setText(cur
								.getString(0));
						((EditText) v.findViewById(R.id.txtHHHead)).setText(cur
								.getString(1));
						((EditText) v.findViewById(R.id.txtVillage))
								.setText(cur.getString(2));
						((EditText) v.findViewById(R.id.txtUnion)).setText(cur
								.getString(3));

						CommonStaticClass.SetSpinnerValue(thisactivity,
								((Spinner) v.findViewById(R.id.spdist)),
								cur.getString(6));

						CommonStaticClass.SetSpinnerValue(thisactivity,
								((Spinner) v.findViewById(R.id.spthana)),
								cur.getString(4));

						CommonStaticClass.SetSpinnerValue(thisactivity,
								((Spinner) v.findViewById(R.id.spoccupation)),
								cur.getString(5));

						CommonStaticClass.SetSpinnerValue(thisactivity,
								((Spinner) v.findViewById(R.id.splocation)),
								cur.getString(7));

						((EditText) v.findViewById(R.id.txtphone1)).setText(cur
								.getString(8));
						((EditText) v.findViewById(R.id.txtphone2)).setText(cur
								.getString(9));

						CommonStaticClass.SetSpinnerValueFrmString(
								thisactivity,
								((Spinner) v.findViewById(R.id.spyear)),
								cur.getString(10));

						CommonStaticClass.SetSpinnerValueFrmString(
								thisactivity,
								((Spinner) v.findViewById(R.id.spmonth)),
								cur.getString(11));

						CommonStaticClass.SetSpinnerValueFrmString(
								thisactivity,
								((Spinner) v.findViewById(R.id.spday)),
								cur.getString(12));

						CommonStaticClass.SetSpinnerValue(thisactivity,
								((Spinner) v.findViewById(R.id.spsex)),
								cur.getString(13));

						if (cur.getString(14).equalsIgnoreCase("1")) {

							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGrouphcw))
									.findViewById(R.id.radio0))
									.setChecked(true);
						} else if (cur.getString(14).equalsIgnoreCase("2")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGrouphcw))
									.findViewById(R.id.radio1))
									.setChecked(true);
						}

						if (cur.getString(15).equalsIgnoreCase("1")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGrouppoultryworker))
									.findViewById(R.id.radio0))
									.setChecked(true);

						} else if (cur.getString(15).equalsIgnoreCase("2")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGrouppoultryworker))
									.findViewById(R.id.radio1))
									.setChecked(true);

						}

						if (cur.getString(16).equalsIgnoreCase("1")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGrouppoultryraising))
									.findViewById(R.id.radio0))
									.setChecked(true);

						} else if (cur.getString(16).equalsIgnoreCase("2")) {
							((RadioButton) ((RadioGroup) v
									.findViewById(R.id.radioGrouppoultryraising))
									.findViewById(R.id.radio1))
									.setChecked(true);

						}

					} while (cur.moveToNext());

				}

			}

		} catch (Exception e) {

			// TODO: handle exception

		}

		if (CommonStaticClass.getSkip("Comp", "tblMainQues", dbHelper)
				.equalsIgnoreCase("1")) {
			((Spinner) v.findViewById(R.id.spmonth)).setVisibility(View.GONE);
			((Spinner) v.findViewById(R.id.spday)).setVisibility(View.GONE);

			((TextView) v.findViewById(R.id.lblday)).setVisibility(View.GONE);
			((TextView) v.findViewById(R.id.lblmonth)).setVisibility(View.GONE);
		}

	}

	private void loadDataMultipleChoiceText(ViewGroup v) {
		final ViewGroup vg = v;
		Cursor cur = null;
		try {
			// SelectQueryBuilder();

			String strSQL = "Select HosName, Dept, Unit,SEIB_ID, Surveillance, EntryDate, EntryTime from tblMainQues where dataid = '"
					+ CommonStaticClass.dataId + "'";

			cur = dbHelper.getQueryCursor(strSQL);

			if (cur.moveToFirst()) {
				do {
					if (cur.getColumnIndexOrThrow("HosName") != -1) {

						try {
							String HosName = cur.getString(cur
									.getColumnIndex("HosName"));
							String Dept = cur.getString(cur
									.getColumnIndex("Dept"));
							String Unit = cur.getString(cur
									.getColumnIndex("Unit"));
							String seib_id = cur.getString(cur
									.getColumnIndex("SEIB_ID"));
							String Surveillance = cur.getString(cur
									.getColumnIndex("Surveillance"));
							String EntryDate = cur.getString(cur
									.getColumnIndex("EntryDate"));
							String EntryTime = cur.getString(cur
									.getColumnIndex("EntryTime"));

							if (HosName != null) {

								((EditText) v
										.findViewById(R.id.txtHospitalName))
										.setText(HosName);

							} else {
								((EditText) v
										.findViewById(R.id.txtHospitalName))
										.setText(CommonStaticClass
												.GetHospitalName(dbHelper));
							}

							ArrayList<String> ids = new ArrayList<String>();
							ids.add("");
							ids.add("1 : Medicine");
							ids.add("2 : Pediatrics");
							CommonStaticClass.FillCombo(this, ids,
									((Spinner) v.findViewById(R.id.spdept)));

							if (Dept != null) {
								if (!Dept.equalsIgnoreCase("-1")) {

									if (Dept.equalsIgnoreCase("1")) {
										((Spinner) v.findViewById(R.id.spdept))
												.setSelection(1);
									} else if (Dept.equalsIgnoreCase("2")) {
										((Spinner) v.findViewById(R.id.spdept))
												.setSelection(2);
									}
								}

							}

							ids = new ArrayList<String>();
							ids.add("1 : Inpatient");
							ids.add("2 : Outpatient");
							CommonStaticClass.FillCombo(this, ids,
									((Spinner) v.findViewById(R.id.spunit)));

							if (Unit != null) {
								if (!Unit.equalsIgnoreCase("-1")) {
									if (Unit.equalsIgnoreCase("1")) {
										((Spinner) v.findViewById(R.id.spunit))
												.setSelection(0);
									} else if (Unit.equalsIgnoreCase("2")) {
										((Spinner) v.findViewById(R.id.spunit))
												.setSelection(1);
									} /*
									 * else { String Comp = CommonStaticClass
									 * .getSkip("Comp", "tblMainques",
									 * dbHelper); if (Comp != null) { if
									 * (Comp.length() > 0) { if
									 * (Comp.equalsIgnoreCase("3") ||
									 * Comp.equalsIgnoreCase("1")) { ((Spinner)
									 * v .findViewById(R.id.spunit))
									 * .setSelection(0); } else if (Comp
									 * .equalsIgnoreCase("2")) { ((Spinner) v
									 * .findViewById(R.id.spunit))
									 * .setSelection(1); } else {
									 * 
									 * } } } }
									 */

								}
								/*
								 * else { String Comp = CommonStaticClass
								 * .getSkip("Comp", "tblMainques", dbHelper); if
								 * (Comp != null) { if (Comp.length() > 0) { if
								 * (Comp.equalsIgnoreCase("3") ||
								 * Comp.equalsIgnoreCase("1")) { ((Spinner) v
								 * .findViewById(R.id.spunit)) .setSelection(0);
								 * } else if (Comp .equalsIgnoreCase("2")) {
								 * ((Spinner) v .findViewById(R.id.spunit))
								 * .setSelection(1); } else {
								 * 
								 * } } } }
								 */
							}
							/*
							 * else { String Comp = CommonStaticClass
							 * .getSkip("Comp", "tblMainques", dbHelper); if
							 * (Comp != null) { if (Comp.length() > 0) { if
							 * (Comp.equalsIgnoreCase("3") ||
							 * Comp.equalsIgnoreCase("1")) { ((Spinner) v
							 * .findViewById(R.id.spunit)) .setSelection(0); }
							 * else if (Comp .equalsIgnoreCase("2")) {
							 * ((Spinner) v .findViewById(R.id.spunit))
							 * .setSelection(1); } else {
							 * 
							 * } } } }
							 */

							if (seib_id != null) {

								((EditText) v.findViewById(R.id.txtSEIBID))
										.setText(seib_id);

							}

							ids = new ArrayList<String>();

							ids.add("");
							ids.add("1 : HBIS");
							ids.add("3 : Both");
							CommonStaticClass.FillCombo(this, ids,
									((Spinner) v.findViewById(R.id.spsur)));

							if (Surveillance != null) {
								if (!Surveillance.equalsIgnoreCase("-1")) {
									if (Surveillance.equalsIgnoreCase("1")) {

										((Spinner) v.findViewById(R.id.spsur))
												.setSelection(1);
										((EditText) v
												.findViewById(R.id.txtSEIBID))
												.setVisibility(View.GONE);
									} else if (Surveillance
											.equalsIgnoreCase("3")) {

										((Spinner) v.findViewById(R.id.spsur))
												.setSelection(2);
										((EditText) v
												.findViewById(R.id.txtSEIBID))
												.setVisibility(View.VISIBLE);
									}

									/*
									 * else { String HosID = CommonStaticClass
									 * .getSkip("HosID", "tblMainques",
									 * dbHelper); if (HosID != null) { if
									 * (HosID.length() > 0) { if
									 * (!HosID.equalsIgnoreCase("03") &&
									 * HosID.equalsIgnoreCase("05") &&
									 * HosID.equalsIgnoreCase("08") &&
									 * HosID.equalsIgnoreCase("12")) {
									 * ((Spinner) v .findViewById(R.id.spsur))
									 * .setSelection(0); } else if (HosID
									 * .equalsIgnoreCase("2")) { ((Spinner) v
									 * .findViewById(R.id.spunit))
									 * .setSelection(1); } else {
									 * 
									 * } } } }
									 */

								}/*
								 * else { String HosID = CommonStaticClass
								 * .getSkip("HosID", "tblMainques", dbHelper);
								 * if (HosID != null) { if (HosID.length() > 0)
								 * { if (!HosID.equalsIgnoreCase("03") &&
								 * HosID.equalsIgnoreCase("05") &&
								 * HosID.equalsIgnoreCase("08") &&
								 * HosID.equalsIgnoreCase("12")) { ((Spinner) v
								 * .findViewById(R.id.spsur)) .setSelection(0);
								 * } else if (HosID .equalsIgnoreCase("2")) {
								 * ((Spinner) v .findViewById(R.id.spunit))
								 * .setSelection(1); } else {
								 * 
								 * } } } }
								 */
							}/*
							 * else { String HosID = CommonStaticClass
							 * .getSkip("HosID", "tblMainques", dbHelper); if
							 * (HosID != null) { if (HosID.length() > 0) { if
							 * (!HosID.equalsIgnoreCase("03") &&
							 * HosID.equalsIgnoreCase("05") &&
							 * HosID.equalsIgnoreCase("08") &&
							 * HosID.equalsIgnoreCase("12")) { ((Spinner) v
							 * .findViewById(R.id.spsur)) .setSelection(0); }
							 * else if (HosID .equalsIgnoreCase("2")) {
							 * ((Spinner) v .findViewById(R.id.spunit))
							 * .setSelection(1); } else {
							 * 
							 * } } } }
							 */

							((EditText) v.findViewById(R.id.etdate))
									.setText(EntryDate);
							((EditText) v.findViewById(R.id.ettime))
									.setText(EntryTime);

							if (CommonStaticClass.getSkip("Comp",
									"tblMainques", dbHelper).equalsIgnoreCase(
									"1")) {
								CommonStaticClass
										.SetSpinnerValue(
												thisactivity,
												((Spinner) v
														.findViewById(R.id.spdept)),
												"1");
								CommonStaticClass
										.SetSpinnerValue(
												thisactivity,
												((Spinner) v
														.findViewById(R.id.spunit)),
												"1");
							} else if (CommonStaticClass.getSkip("Comp",
									"tblMainques", dbHelper).equalsIgnoreCase(
									"2")) {
								// Department should be blank
								CommonStaticClass
										.SetSpinnerValue(
												thisactivity,
												((Spinner) v
														.findViewById(R.id.spdept)),
												"");
								CommonStaticClass
										.SetSpinnerValue(
												thisactivity,
												((Spinner) v
														.findViewById(R.id.spunit)),
												"2");
							} else if (CommonStaticClass.getSkip("Comp",
									"tblMainques", dbHelper).equalsIgnoreCase(
									"3")) {
								CommonStaticClass
										.SetSpinnerValue(
												thisactivity,
												((Spinner) v
														.findViewById(R.id.spdept)),
												"2");
								CommonStaticClass
										.SetSpinnerValue(
												thisactivity,
												((Spinner) v
														.findViewById(R.id.spunit)),
												"1");
							}

							if ((CommonStaticClass.userSpecificId
									.equalsIgnoreCase("03")
									|| CommonStaticClass.userSpecificId
											.equalsIgnoreCase("05")
									|| CommonStaticClass.userSpecificId
											.equalsIgnoreCase("08") || CommonStaticClass.userSpecificId
										.equalsIgnoreCase("12"))
									&& (CommonStaticClass.getSkip("Comp",
											"tblMainques", dbHelper)
											.equalsIgnoreCase("3"))) {

								/*
								 * ((Spinner) v.findViewById(R.id.spsur))
								 * .setSelection(0);
								 */

							}

							else {
								((Spinner) v.findViewById(R.id.spsur))
										.setEnabled(true);
								((Spinner) v.findViewById(R.id.spsur))
										.setSelection(1);
							}

							((Spinner) v.findViewById(R.id.spsur))
									.setOnItemSelectedListener(new OnItemSelectedListener() {

										@Override
										public void onItemSelected(
												AdapterView<?> arg0, View arg1,
												int arg2, long arg3) {

											String val = ((Spinner) vg
													.findViewById(R.id.spsur))
													.getSelectedItem()
													.toString();

											if (val.contains("3")) {
												((EditText) vg
														.findViewById(R.id.txtSEIBID))
														.setEnabled(true);
												((EditText) vg
														.findViewById(R.id.txtSEIBID))
														.setVisibility(View.VISIBLE);

											} else {
												((EditText) vg
														.findViewById(R.id.txtSEIBID))
														.setEnabled(false);
												((EditText) vg
														.findViewById(R.id.txtSEIBID))
														.setVisibility(View.GONE);
											}

										}

										@Override
										public void onNothingSelected(
												AdapterView<?> arg0) {
											// TODO Auto-generated method
											// stub

										}
									});

							// ((EditText)v.findViewById(R.id.txtSEIBID)).setEnabled(false);

						} catch (Exception e) {
							// TODO: handle exception
							Log.e("error", e.getMessage());
						}

					}

				} while (cur.moveToNext());
			}

			else {

			}
		}

		catch (Exception ex) {
			// MessageBox.Show(ex.ToString(), "Error");

		}
	}

	private void loadguifrmmultiplechoicetext(ViewGroup v) {

		qqq = (TextView) v.findViewById(R.id.qqq);

		qName = CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar();

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

		final ViewGroup vg = v;
		loadDataMultipleChoiceText(vg);

		/*
		 * progressDialog = ProgressDialog.show(con, "Loading...",
		 * "Please wait while loading data");
		 * 
		 * new Thread() {
		 * 
		 * public void run() { try { Looper.prepare();
		 * 
		 * loadDataMultipleChoiceText(vg); Message msg = new Message(); msg.what
		 * = UPDATEDONE; handlerFrmHHID.sendMessage(msg);
		 * 
		 * } catch (Exception lg) { progressDialog.dismiss();
		 * CommonStaticClass.showFinalAlert(con,
		 * "An Error occured in load method");
		 * 
		 * } finally { progressDialog.dismiss(); } Looper.loop(); }
		 * 
		 * }.start();
		 */

		saveNxtButton = (Button) v.findViewById(R.id.saveNxtButton);

		saveNxtButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				if (!IsValidEntry(vg)) {
					DisplayToast(thisactivity, "Please fill all fields", 1);
					return;
				}

				progressDialog = ProgressDialog.show(con, "Saving...",
						"Please wait while saving");

				new Thread() {

					public void run() {
						try {
							Looper.prepare();

							updateTableDataFrmMultipleChoiceText(vg);
							Message msg = new Message();
							msg.what = UPDATEDONE;
							handlerFrmHHID.sendMessage(msg);

						} catch (Exception lg) {
							progressDialog.dismiss();
							CommonStaticClass.showFinalAlert(con,
									"An Error occured in save method");

						} finally {
							progressDialog.dismiss();
						}
						Looper.loop();
					}

				}.start();

			}

		});
		clButton = (Button) v.findViewById(R.id.clButton);
		clButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				// resetViewGroup((ViewGroup) vv);
			}

		});
		/*
		 * notesButton = (Button) v.findViewById(R.id.btnNote);
		 * notesButton.setOnClickListener(new OnClickListener() {
		 * 
		 * public void onClick(View v) { // TODO Auto-generated method stub
		 * FraNotes();
		 * 
		 * }
		 * 
		 * });
		 */
		prevButton = (Button) v.findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				userPressedPrevious(ParentActivity.this);
			}

		});

	}

	private boolean IsValidEntry(ViewGroup v) {

		ViewGroup viewGroup = (LinearLayout) v.findViewById(R.id.ln);
		int nrOfChildren = viewGroup.getChildCount();
		boolean alltrue = true;
		for (int i = 0; i < nrOfChildren; i++) {
			View view = viewGroup.getChildAt(i);

			if (view instanceof EditText) {
				if (!(((EditText) view).getText().toString().length() > 0)) {

					if (view.getVisibility() == View.GONE) {
						continue;
					}

					/*
					 * else { if (ExcludeFromvalidation(view)) { continue; }
					 */
					else {
						view.setBackgroundResource(R.drawable.border);
						view.requestFocus();
						return false;
					}

					// }

				}
			}

			else if (view instanceof RadioGroup) {

				if (view.getVisibility() == View.GONE) {
					continue;
				} else if (((RadioGroup) view).getCheckedRadioButtonId() == -1) {
					((RadioGroup) view)
							.setBackgroundResource(R.drawable.border);
					return false;

				}

			}

		}
		return true;

	}

	private void updateTableDataFrmMultipleChoiceText(ViewGroup v) {

		/*
		 * if (!IsValidEntry(v)) { DisplayToast(thisactivity,
		 * "Please fill all fields", 1); return; }
		 */

		String sql = String.format("");

		try {

			((Spinner) v.findViewById(R.id.spdept))
					.getSelectedItem()
					.toString()
					.substring(
							0,
							((Spinner) v.findViewById(R.id.spdept))
									.getSelectedItem().toString()
									.lastIndexOf(":") - 1);

			// String dept = dept.length() > 0 ? dept.substring(0,
			// dept.lastIndexOf(":") - 1) : "";

			sql = String
					.format("Update %s Set Dept = '%s',  HosName='%s', Unit= '%s', Surveillance='%s', seib_id= '%s', EditBy='%s', EditDate='%s' WHERE dataid = '%s'",
							CommonStaticClass
									.GetTableName(CommonStaticClass.questionMap
											.get(CommonStaticClass.currentSLNo)
											.getQvar()),
							((Spinner) v.findViewById(R.id.spdept))
									.getSelectedItem()
									.toString()
									.substring(
											0,
											((Spinner) v
													.findViewById(R.id.spdept))
													.getSelectedItem()
													.toString()
													.lastIndexOf(":") - 1),
							((EditText) v.findViewById(R.id.txtHospitalName))
									.getText().toString(),
							((Spinner) v.findViewById(R.id.spunit))
									.getSelectedItem()
									.toString()
									.substring(
											0,
											((Spinner) v
													.findViewById(R.id.spunit))
													.getSelectedItem()
													.toString()
													.lastIndexOf(":") - 1),
							((Spinner) v.findViewById(R.id.spsur))
									.getSelectedItem()
									.toString()
									.substring(
											0,
											((Spinner) v
													.findViewById(R.id.spsur))
													.getSelectedItem()
													.toString()
													.lastIndexOf(":") - 1),
							((EditText) v.findViewById(R.id.txtSEIBID))
									.getText().toString(),
							CommonStaticClass.userSpecificId, CommonStaticClass
									.GetCurrentDate(), CommonStaticClass.dataId);

			if (dbHelper.executeDMLQuery(sql)) {

				CommonStaticClass.findOutNextSLNo(
						CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQvar(),
						CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQnext1());
				CommonStaticClass.nextQuestion(ParentActivity.this);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
			listvalues.add("-1");
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
								listvalues.set(checkButton.getId(), "-1");
								spinner.setVisibility(View.VISIBLE);

							} else {
								listvalues.set(checkButton.getId(), "-1");
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
						if (!a.equalsIgnoreCase("-1")
								&& !a.equalsIgnoreCase(null)) {

							// if(a!=null&&a.length()>0){
							checkButton.setChecked(true);
							spinner.setText(a);
							listvalues.set(checkButton.getId(), a);
							dataOk = true;
							// }
						} else {
							listvalues.set(checkButton.getId(), "-1");
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

			Slno.setText("µwgK bs");
			lblc2.setText("åg‡bi D‡�?k¨,(1=mvgvwRK cwi`k©b,2=‡ivMxi ïk«ylvKvix wn‡m‡e,3=  Ab¨vb¨ (wbw`©ó Kiæb)");
			lblc3.setText("Avcbvi Lvbv‡Z AwZevwnZ mgq (N›Uvq D‡jøL Kiæb hw` AwZevwnZ mgq 3 iv‡Zi Kg  nq)");
			lblc4.setText("Avcbvi Lvbv‡Z AwZevwnZ iv‡Zi msL¨v (hw` AwZevwnZ mgq 3 iv‡Zi †ekx  nq)");
			lblc5.setText("‡Kv_v n‡Z wZwb/Zviv G‡mwQ‡jb?");
			lblc5village.setText("MÖvg/cvov (gnjøv)");
			lblc5_2.setText("BDwbqb");
			lblc5_3.setText("Dc‡Rjv");
			tvc5_4.setText("‡Rjv");
			lblc6.setText("`~iZ¡ wKtwgt/gvBj (DËi`vZvi D‡jjLK„Z GKK wbw`©ó Kiæb)");

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

	private void loadguifrmpatientdetail(ViewGroup vg) {

		final ViewGroup group = vg;

		qName = CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar();

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

		PatientDetail pd = new PatientDetail();
		pd = pd.GetPatientdetail(dbHelper);

		((EditText) vg.findViewById(R.id.txtPatientID)).setText(pd
				.getPatientID());

		if (pd.getPatientTY() > 0) {
			if (pd.getPatientTY() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupPatientTY))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getPatientTY() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupPatientTY))
						.findViewById(R.id.radio1)).setChecked(true);

		}
		etyearmonth = ((EditText) vg.findViewById(R.id.txtRegDate));
		etyearmonth.setText(pd.getRegDate());

		etyearmonth.setOnTouchListener(new View.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub

				final Calendar c = Calendar.getInstance();
				dateYear = c.get(Calendar.YEAR);
				dateMonth = c.get(Calendar.MONTH);
				dateDay = c.get(Calendar.DAY_OF_MONTH);

				showDialog(DATE_DIALOG);
				return false;
			}
		});

		etyearmonth.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				final Calendar c = Calendar.getInstance();
				dateYear = c.get(Calendar.YEAR);
				dateMonth = c.get(Calendar.MONTH);
				dateDay = c.get(Calendar.DAY_OF_MONTH);

				showDialog(DATE_DIALOG);

			}
		});

		etyearmonth.setOnFocusChangeListener(new OnFocusChangeListener() {

			public void onFocusChange(View arg0, boolean arg1) {

				final Calendar c = Calendar.getInstance();
				dateYear = c.get(Calendar.YEAR);
				dateMonth = c.get(Calendar.MONTH);
				dateDay = c.get(Calendar.DAY_OF_MONTH);

				showDialog(DATE_DIALOG);

			}
		});

		ettime = ((EditText) vg.findViewById(R.id.txtRegTime));
		ettime.setText(pd.getRegTime());

		ettime.setOnTouchListener(new View.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				showDialog(TIME_DIALOG);
				return false;
			}
		});

		((EditText) vg.findViewById(R.id.txtName)).setText(pd.getName());

		pickDate = ((EditText) vg.findViewById(R.id.txtBDate));
		pickDate.setText(pd.getBDate());

		pickDate.setOnTouchListener(new View.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub

				final Calendar c = Calendar.getInstance();
				dobYear = c.get(Calendar.YEAR);
				dobMonth = c.get(Calendar.MONTH);
				dobDay = c.get(Calendar.DAY_OF_MONTH);

				showDialog(DATE_DIALOG);
				return false;
			}
		});

		/*
		 * pickDate.setOnFocusChangeListener(new OnFocusChangeListener() {
		 * 
		 * public void onFocusChange(View arg0, boolean arg1) {
		 * 
		 * final Calendar c = Calendar.getInstance(); dobYear =
		 * c.get(Calendar.YEAR); dobMonth = c.get(Calendar.MONTH); dobDay =
		 * c.get(Calendar.DAY_OF_MONTH);
		 * 
		 * showDialog(DATE_DIALOG);
		 * 
		 * } });
		 */

		// ((EditText) vg.findViewById(R.id.txtBDate)).setText(pd.getBDate());

		if (pd.getSex() > 0) {
			if (pd.getSex() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupSex))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getSex() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupSex))
						.findViewById(R.id.radio1)).setChecked(true);

		}

		((EditText) vg.findViewById(R.id.txtAgeYr)).setText(String.valueOf(pd
				.getAgeYr()));
		((EditText) vg.findViewById(R.id.txtAgeMo)).setText(String.valueOf(pd
				.getAgeMo()));
		((EditText) vg.findViewById(R.id.txtAgeDa)).setText(String.valueOf(pd
				.getAgeDa()));

		if (pd.getBDType() > 0) {
			if (pd.getBDType() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupBDType))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getBDType() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupBDType))
						.findViewById(R.id.radio1)).setChecked(true);

		}

		((EditText) vg.findViewById(R.id.txtFaSpName))
				.setText(pd.getFaSpName());
		((EditText) vg.findViewById(R.id.txtWard)).setText(pd.getWard());
		((EditText) vg.findViewById(R.id.txtArea)).setText(pd.getArea());
		((EditText) vg.findViewById(R.id.txtSecBlock))
				.setText(pd.getSecBlock());
		((EditText) vg.findViewById(R.id.txtRoad)).setText(pd.getRoad());
		((EditText) vg.findViewById(R.id.txtHouse)).setText(pd.getHouse());
		((EditText) vg.findViewById(R.id.txtHH)).setText(pd.getHH());
		((EditText) vg.findViewById(R.id.txtSL)).setText(pd.getSL());
		((EditText) vg.findViewById(R.id.txtPID)).setText(pd.getPID());

		((EditText) vg.findViewById(R.id.txtPhone)).setText(pd.getPhone());
		((EditText) vg.findViewById(R.id.txtPhone2)).setText(pd.getPhone2());

		((EditText) vg.findViewById(R.id.txtVill)).setText(pd.getVill());
		((EditText) vg.findViewById(R.id.txtUPZ)).setText(pd.getUPZ());
		((EditText) vg.findViewById(R.id.txtDist)).setText(pd.getDist());

		btnsaveNxt = (Button) vg.findViewById(R.id.saveNxtButton);
		btnsaveNxt.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				PatientDetail pd = new PatientDetail();
				if (pd.SavePatientDetail(dbHelper, group)) {
					CommonStaticClass.findOutNextSLNo(
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQvar(),
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQnext1());
					CommonStaticClass.nextQuestion(ParentActivity.this);
				} else {
					CommonStaticClass.showMyAlert(con, "Save Failed",
							"Save Failed. Please fill fields!");
				}
			}

		});
		btnClear = (Button) vg.findViewById(R.id.clButton);
		btnClear.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				resetViewGroup((ViewGroup) vv);
			}

		});
		((Button) vg.findViewById(R.id.clButton)).setVisibility(View.GONE);
		notesButton = (Button) vg.findViewById(R.id.btnNote);
		notesButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				FraNotes();

			}

		});
	}

	private void loadguifrmweight(ViewGroup vg) {
		final ViewGroup group = vg;

		qName = CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar();

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

		PatientDetail pd = new PatientDetail();
		pd = pd.GetPatientdetail(dbHelper);

		((EditText) vg.findViewById(R.id.txtweight)).setText(pd.getWT());

		((EditText) vg.findViewById(R.id.txtheight)).setText(pd.getHT());

		((EditText) vg.findViewById(R.id.txtmuac)).setText(pd.getMUAC());

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

				PatientDetail pd = new PatientDetail();
				if (pd.SavePatientWeight(dbHelper, group)) {
					CommonStaticClass.findOutNextSLNo(
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQvar(),
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQnext1());
					CommonStaticClass.nextQuestion(ParentActivity.this);
				} else {
					CommonStaticClass.showMyAlert(con, "Save Failed",
							"Save Failed. Please fill all fields!");
				}

			}

		});
		btnClear = (Button) vg.findViewById(R.id.clButton);
		btnClear.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				resetViewGroup((ViewGroup) vv);
			}

		});
		((Button) vg.findViewById(R.id.clButton)).setVisibility(View.GONE);
		notesButton = (Button) vg.findViewById(R.id.btnNote);
		notesButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				FraNotes();

			}

		});

	}

	private void loadguifrmdiarrhea(ViewGroup vg) {
		final ViewGroup group = vg;

		resetViewGroup(vg);

		qName = CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar();

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

		PatientDetail pd = new PatientDetail();
		pd = pd.GetPatientdetail(dbHelper);

		((RadioGroup) vg.findViewById(R.id.radioGroupDS17))
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup g, int checkedId) {
						if (((RadioButton) (g.findViewById(R.id.radioGroupDS17))
								.findViewById(R.id.radio0)).isChecked()) {

							((EditText) group.findViewById(R.id.DS18D))
									.setVisibility(View.GONE);
							((RadioGroup) group
									.findViewById(R.id.radioGroupDS18H))
									.setVisibility(View.GONE);
							((RadioGroup) group
									.findViewById(R.id.radioGroupDS19))
									.setVisibility(View.GONE);
							((EditText) group.findViewById(R.id.DS20))
									.setVisibility(View.GONE);
							((EditText) group.findViewById(R.id.DS21))
									.setVisibility(View.GONE);
							((RadioGroup) group
									.findViewById(R.id.radioGroupDS22))
									.setVisibility(View.GONE);
							((RadioGroup) group
									.findViewById(R.id.radioGroupDS23))
									.setVisibility(View.GONE);
							((RadioGroup) group
									.findViewById(R.id.radioGroupDS24))
									.setVisibility(View.GONE);
							((RadioGroup) group
									.findViewById(R.id.radioGroupDS25))
									.setVisibility(View.GONE);

							((TextView) group.findViewById(R.id.lbl2))
									.setVisibility(View.GONE);
							((TextView) group.findViewById(R.id.lbl3))
									.setVisibility(View.GONE);
							((TextView) group.findViewById(R.id.lbl5))
									.setVisibility(View.GONE);
							((TextView) group.findViewById(R.id.lbl8))
									.setVisibility(View.GONE);
							((TextView) group.findViewById(R.id.lbl9))
									.setVisibility(View.GONE);
							((TextView) group.findViewById(R.id.lbl11))
									.setVisibility(View.GONE);
							((TextView) group.findViewById(R.id.lbl13))
									.setVisibility(View.GONE);
							((TextView) group.findViewById(R.id.lbl15))
									.setVisibility(View.GONE);
							((TextView) group.findViewById(R.id.lbl16))
									.setVisibility(View.GONE);

						} else {
							((EditText) group.findViewById(R.id.DS18D))
									.setVisibility(View.VISIBLE);
							((RadioGroup) group
									.findViewById(R.id.radioGroupDS18H))
									.setVisibility(View.VISIBLE);
							((RadioGroup) group
									.findViewById(R.id.radioGroupDS19))
									.setVisibility(View.VISIBLE);
							((EditText) group.findViewById(R.id.DS20))
									.setVisibility(View.VISIBLE);
							((EditText) group.findViewById(R.id.DS21))
									.setVisibility(View.VISIBLE);
							((RadioGroup) group
									.findViewById(R.id.radioGroupDS22))
									.setVisibility(View.VISIBLE);
							((RadioGroup) group
									.findViewById(R.id.radioGroupDS23))
									.setVisibility(View.VISIBLE);
							((RadioGroup) group
									.findViewById(R.id.radioGroupDS24))
									.setVisibility(View.VISIBLE);
							((RadioGroup) group
									.findViewById(R.id.radioGroupDS25))
									.setVisibility(View.VISIBLE);

							((TextView) group.findViewById(R.id.lbl2))
									.setVisibility(View.VISIBLE);
							((TextView) group.findViewById(R.id.lbl3))
									.setVisibility(View.VISIBLE);
							((TextView) group.findViewById(R.id.lbl5))
									.setVisibility(View.VISIBLE);
							((TextView) group.findViewById(R.id.lbl8))
									.setVisibility(View.VISIBLE);
							((TextView) group.findViewById(R.id.lbl9))
									.setVisibility(View.VISIBLE);
							((TextView) group.findViewById(R.id.lbl11))
									.setVisibility(View.VISIBLE);
							((TextView) group.findViewById(R.id.lbl13))
									.setVisibility(View.VISIBLE);
							((TextView) group.findViewById(R.id.lbl15))
									.setVisibility(View.VISIBLE);
							((TextView) group.findViewById(R.id.lbl16))
									.setVisibility(View.VISIBLE);

						}

					}
				});

		if (pd.getDS17() > 0) {
			if (pd.getDS17() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS17))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS17() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS17))
						.findViewById(R.id.radio1)).setChecked(true);

		}
		((EditText) vg.findViewById(R.id.DS18D)).setText(String.valueOf(pd
				.getDS18D()));

		if (pd.getDS18H() > 0) {
			if (pd.getDS18H() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS18H))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS18H() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS18H))
						.findViewById(R.id.radio1)).setChecked(true);

		}

		if (pd.getDS19() > 0) {
			if (pd.getDS19() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS19))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS19() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS19))
						.findViewById(R.id.radio1)).setChecked(true);

			else if (pd.getDS19() == 3)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS19))
						.findViewById(R.id.radio2)).setChecked(true);

		}

		((EditText) vg.findViewById(R.id.DS20)).setText(String.valueOf(pd
				.getDS20()));

		((EditText) vg.findViewById(R.id.DS21)).setText(String.valueOf(pd
				.getDS20()));

		if (pd.getDS22() > 0) {
			if (pd.getDS22() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS22))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS22() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS22))
						.findViewById(R.id.radio1)).setChecked(true);

			else if (pd.getDS22() == 3)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS22))
						.findViewById(R.id.radio2)).setChecked(true);

		}

		if (pd.getDS23() > 0) {
			if (pd.getDS23() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS23))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS23() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS23))
						.findViewById(R.id.radio1)).setChecked(true);

		}

		if (pd.getDS24() > 0) {
			if (pd.getDS24() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS24))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS24() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS24))
						.findViewById(R.id.radio1)).setChecked(true);

		}

		if (pd.getDS25() > 0) {
			if (pd.getDS25() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS25))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS25() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS25))
						.findViewById(R.id.radio1)).setChecked(true);

		}

		if (pd.getDS26() > 0) {
			if (pd.getDS26() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS26))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS26() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS26))
						.findViewById(R.id.radio1)).setChecked(true);
			else if (pd.getDS26() == 3)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS26))
						.findViewById(R.id.radio2)).setChecked(true);

		}

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

				PatientDetail pd = new PatientDetail();
				if (pd.SavePatientDiarrhea(dbHelper, group)) {
					CommonStaticClass.findOutNextSLNo(
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQvar(),
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQnext1());
					CommonStaticClass.nextQuestion(ParentActivity.this);
				} else {
					CommonStaticClass.showMyAlert(con, "Save Failed",
							"Save Failed. Please fill all fields!");
				}

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

	private void loadguifrmvomiting(ViewGroup vg) {
		final ViewGroup group = vg;

		resetViewGroup(vg);
		qName = CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar();

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

		PatientDetail pd = new PatientDetail();
		pd = pd.GetPatientdetail(dbHelper);

		((EditText) vg.findViewById(R.id.DS27))
				.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						// TODO Auto-generated method stub

					}

					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
						// TODO Auto-generated method stub

					}

					@Override
					public void afterTextChanged(Editable s) {
						if (s.toString().length() > 0) {
							if (s.toString().equalsIgnoreCase("0")) {
								((TextView) group.findViewById(R.id.lblDS28D))
										.setVisibility(View.GONE);
								((TextView) group.findViewById(R.id.lblDS28H))
										.setVisibility(View.GONE);
								((TextView) group.findViewById(R.id.DS28D))
										.setVisibility(View.GONE);
								((RadioGroup) group
										.findViewById(R.id.radioGroupDS28H))
										.setVisibility(View.GONE);

							} else {
								((TextView) group.findViewById(R.id.lblDS28D))
										.setVisibility(View.VISIBLE);
								((TextView) group.findViewById(R.id.lblDS28H))
										.setVisibility(View.VISIBLE);
								((TextView) group.findViewById(R.id.DS28D))
										.setVisibility(View.VISIBLE);
								((RadioGroup) group
										.findViewById(R.id.radioGroupDS28H))
										.setVisibility(View.VISIBLE);
							}
						}

					}
				});

		((EditText) vg.findViewById(R.id.DS28D)).setText(String.valueOf(pd
				.getDS28D()));

		if (pd.getDS28H() > 0) {
			if (pd.getDS28H() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS28H))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS28H() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS28H))
						.findViewById(R.id.radio1)).setChecked(true);

			else if (pd.getDS28H() == 3)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS28H))
						.findViewById(R.id.radio2)).setChecked(true);

		}

		((EditText) vg.findViewById(R.id.DS27)).setText(String.valueOf(pd
				.getDS27()));

		if (pd.getDS29() > 0) {
			if (pd.getDS29() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS29))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS29() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS29))
						.findViewById(R.id.radio1)).setChecked(true);

		}

		if (pd.getDS30() > 0) {
			if (pd.getDS30() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS30))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS30() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS30))
						.findViewById(R.id.radio1)).setChecked(true);

		}

		((RadioGroup) vg.findViewById(R.id.radioGroupDS31))
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup g, int checkedId) {
						if (((RadioButton) (g.findViewById(R.id.radioGroupDS31))
								.findViewById(R.id.radio0)).isChecked()) {

							((EditText) group.findViewById(R.id.DS32D))
									.setVisibility(View.GONE);
							((RadioGroup) group
									.findViewById(R.id.radioGroupDS32H))
									.setVisibility(View.GONE);

							((TextView) group.findViewById(R.id.lblDS32D))
									.setVisibility(View.GONE);
							((TextView) group.findViewById(R.id.lblDS32H))
									.setVisibility(View.GONE);

						} else {
							((EditText) group.findViewById(R.id.DS32D))
									.setVisibility(View.VISIBLE);
							((RadioGroup) group
									.findViewById(R.id.radioGroupDS32H))
									.setVisibility(View.VISIBLE);

							((TextView) group.findViewById(R.id.lblDS32D))
									.setVisibility(View.VISIBLE);
							((TextView) group.findViewById(R.id.lblDS32H))
									.setVisibility(View.VISIBLE);

						}

					}
				});

		((EditText) vg.findViewById(R.id.DS32D)).setText(String.valueOf(pd
				.getDS32D()));

		if (pd.getDS32H() > 0) {
			if (pd.getDS32H() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS32H))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS32H() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS32H))
						.findViewById(R.id.radio1)).setChecked(true);

		}

		if (pd.getDS31() > 0) {
			if (pd.getDS31() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS31))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS31() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS31))
						.findViewById(R.id.radio1)).setChecked(true);

		}

		((EditText) vg.findViewById(R.id.DS33)).setText(String.valueOf(pd
				.getDS33()));
		if (pd.getDS34() > 0) {
			if (pd.getDS34() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS34))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS34() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS34))
						.findViewById(R.id.radio1)).setChecked(true);

		}

		((RadioGroup) vg.findViewById(R.id.radioGroupDS34))
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup g, int checkedId) {
						if (((RadioButton) (g.findViewById(R.id.radioGroupDS34))
								.findViewById(R.id.radio0)).isChecked()) {

							((EditText) group.findViewById(R.id.DS35D))
									.setVisibility(View.GONE);
							((RadioGroup) group
									.findViewById(R.id.radioGroupDS35H))
									.setVisibility(View.GONE);

							((TextView) group.findViewById(R.id.lblDS35D))
									.setVisibility(View.GONE);
							((TextView) group.findViewById(R.id.lblDS35H))
									.setVisibility(View.GONE);

						} else {
							((EditText) group.findViewById(R.id.DS35D))
									.setVisibility(View.VISIBLE);
							((RadioGroup) group
									.findViewById(R.id.radioGroupDS35H))
									.setVisibility(View.VISIBLE);

							((TextView) group.findViewById(R.id.lblDS35D))
									.setVisibility(View.VISIBLE);
							((TextView) group.findViewById(R.id.lblDS35H))
									.setVisibility(View.VISIBLE);

						}

					}
				});

		((EditText) vg.findViewById(R.id.DS35D)).setText(String.valueOf(pd
				.getDS35D()));

		if (pd.getDS35H() > 0) {
			if (pd.getDS35H() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS35H))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS35H() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS35H))
						.findViewById(R.id.radio1)).setChecked(true);

		}

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

				PatientDetail pd = new PatientDetail();
				if (pd.SavePatientVomiting(dbHelper, group)) {
					CommonStaticClass.findOutNextSLNo(
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQvar(),
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQnext1());
					CommonStaticClass.nextQuestion(ParentActivity.this);
				} else {
					CommonStaticClass.showMyAlert(con, "Save Failed",
							"Save Failed. Please fill all fields!");
				}

			}

		});
		btnClear = (Button) vg.findViewById(R.id.clButton);
		btnClear.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				resetViewGroup((ViewGroup) vv);
			}

		});
		((Button) vg.findViewById(R.id.clButton)).setVisibility(View.GONE);
		notesButton = (Button) vg.findViewById(R.id.btnNote);
		notesButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				FraNotes();

			}

		});

	}

	private void loadguifrmors(ViewGroup vg) {
		final ViewGroup group = vg;

		qName = CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar();

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

		PatientDetail pd = new PatientDetail();
		pd = pd.GetPatientdetail(dbHelper);

		((EditText) vg.findViewById(R.id.DS36)).setText(String.valueOf(pd
				.getDS36()));

		IfZeroThenClear(((EditText) vg.findViewById(R.id.DS36)));

		((EditText) vg.findViewById(R.id.DS37)).setText(String.valueOf(pd
				.getDS37()));
		IfZeroThenClear(((EditText) vg.findViewById(R.id.DS37)));

		((EditText) vg.findViewById(R.id.DS38)).setText(String.valueOf(pd
				.getDS36()));

		IfZeroThenClear(((EditText) vg.findViewById(R.id.DS38)));

		((EditText) vg.findViewById(R.id.DS39)).setText(String.valueOf(pd
				.getDS39()));

		IfZeroThenClear(((EditText) vg.findViewById(R.id.DS39)));

		if (pd.getDS40() > 0) {
			if (pd.getDS40() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS40))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS40() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS40))
						.findViewById(R.id.radio1)).setChecked(true);

			else if (pd.getDS40() == 3)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS40))
						.findViewById(R.id.radio2)).setChecked(true);

			else if (pd.getDS40() == 4)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS40))
						.findViewById(R.id.radio3)).setChecked(true);

			else if (pd.getDS40() == 5)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS40))
						.findViewById(R.id.radio4)).setChecked(true);
		}

		((EditText) vg.findViewById(R.id.DS42)).setText(String.valueOf(pd
				.getDS42()));

		((EditText) vg.findViewById(R.id.DS43)).setText(String.valueOf(pd
				.getDS43()));

		if (pd.getDS44() > 0) {
			if (pd.getDS44() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS44))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS44() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS44))
						.findViewById(R.id.radio1)).setChecked(true);

			else if (pd.getDS44() == 3)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS44))
						.findViewById(R.id.radio2)).setChecked(true);

			else if (pd.getDS44() == 4)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS44))
						.findViewById(R.id.radio3)).setChecked(true);

		}

		if (pd.getDS45() > 0) {
			if (pd.getDS45() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS45))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS45() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS45))
						.findViewById(R.id.radio1)).setChecked(true);
		}

		if (pd.getDS46() > 0) {
			if (pd.getDS46() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS46))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS46() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS46))
						.findViewById(R.id.radio1)).setChecked(true);

		}

		if (pd.getDS47() > 0) {
			if (pd.getDS47() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS47))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS47() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS47))
						.findViewById(R.id.radio1)).setChecked(true);

		}

		if (pd.getDS48() > 0) {
			if (pd.getDS48() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS48))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS48() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS48))
						.findViewById(R.id.radio1)).setChecked(true);

			else if (pd.getDS48() == 3)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS48))
						.findViewById(R.id.radio2)).setChecked(true);

			else if (pd.getDS48() == 4)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS48))
						.findViewById(R.id.radio3)).setChecked(true);

			else if (pd.getDS48() == 5)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS48))
						.findViewById(R.id.radio4)).setChecked(true);

		}

		if (pd.getDS49() > 0) {
			if (pd.getDS49() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS49))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS49() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS49))
						.findViewById(R.id.radio1)).setChecked(true);

		}
		if (pd.getDS50() > 0) {
			if (pd.getDS50() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS50))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS50() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS50))
						.findViewById(R.id.radio1)).setChecked(true);

		}

		if (pd.getDS51() > 0) {
			if (pd.getDS51() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS51))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS51() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS51))
						.findViewById(R.id.radio1)).setChecked(true);
			else if (pd.getDS51() == 3)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS51))
						.findViewById(R.id.radio2)).setChecked(true);
		}

		if (pd.getDS52() > 0) {
			if (pd.getDS52() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS52))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS52() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS52))
						.findViewById(R.id.radio1)).setChecked(true);

		}

		if (pd.getDS53() > 0) {
			if (pd.getDS53() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS53))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS53() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS53))
						.findViewById(R.id.radio1)).setChecked(true);

		}

		if (pd.getDS54() > 0) {
			if (pd.getDS54() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS54))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS54() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS54))
						.findViewById(R.id.radio1)).setChecked(true);
			else if (pd.getDS54() == 3)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS54))
						.findViewById(R.id.radio2)).setChecked(true);
		}

		((EditText) vg.findViewById(R.id.DS55)).setText(String.valueOf(pd
				.getDS55()));

		if (pd.getDS56() > 0) {
			if (pd.getDS56() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS56))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS56() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS56))
						.findViewById(R.id.radio1)).setChecked(true);
			else if (pd.getDS56() == 3)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS56))
						.findViewById(R.id.radio2)).setChecked(true);

			else if (pd.getDS56() == 4)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS56))
						.findViewById(R.id.radio3)).setChecked(true);

			else if (pd.getDS56() == 5)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS56))
						.findViewById(R.id.radio4)).setChecked(true);
		}

		etyearmonth = ((EditText) vg.findViewById(R.id.DS57));
		// etyearmonth = ((EditText) vg.findViewById(R.id.txtRegDate));
		etyearmonth.setText(pd.getDS57());

		etyearmonth.setOnTouchListener(new View.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub

				final Calendar c = Calendar.getInstance();
				dateYear = c.get(Calendar.YEAR);
				dateMonth = c.get(Calendar.MONTH);
				dateDay = c.get(Calendar.DAY_OF_MONTH);

				showDialog(DATE_DIALOG);
				return false;
			}
		});

		etyearmonth.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				final Calendar c = Calendar.getInstance();
				dateYear = c.get(Calendar.YEAR);
				dateMonth = c.get(Calendar.MONTH);
				dateDay = c.get(Calendar.DAY_OF_MONTH);

				showDialog(DATE_DIALOG);

			}
		});

		etyearmonth.setOnFocusChangeListener(new OnFocusChangeListener() {

			public void onFocusChange(View arg0, boolean arg1) {

				final Calendar c = Calendar.getInstance();
				dateYear = c.get(Calendar.YEAR);
				dateMonth = c.get(Calendar.MONTH);
				dateDay = c.get(Calendar.DAY_OF_MONTH);

				showDialog(DATE_DIALOG);

			}
		});

		/*
		 * pickDate.setOnTouchListener(new OnTouchListener() {
		 * 
		 * @Override public boolean onTouch(View v, MotionEvent event) { final
		 * Calendar c = Calendar.getInstance(); dateYear = c.get(Calendar.YEAR);
		 * dateMonth = c.get(Calendar.MONTH); dateDay =
		 * c.get(Calendar.DAY_OF_MONTH); showDialog(DATE_DIALOG); return false;
		 * } });
		 */

		ettime = ((EditText) vg.findViewById(R.id.DS58));
		ettime.setText(pd.getDS58());
		ettime.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				showDialog(TIME_DIALOG);
				return false;

			}
		});

		/*
		 * ((EditText) vg.findViewById(R.id.DS57)).setText(String.valueOf(pd
		 * .getDS57()));
		 */

		/*
		 * ((EditText) vg.findViewById(R.id.DS58)).setText(String.valueOf(pd
		 * .getDS58()));
		 */

		((EditText) vg.findViewById(R.id.DS59P)).setText(String.valueOf(pd
				.getDS59P()));

		((EditText) vg.findViewById(R.id.DS59C)).setText(String.valueOf(pd
				.getDS59C()));

		(((RadioGroup) vg.findViewById(R.id.radioGroupDS56)))
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup g, int checkedId) {
						if (((RadioButton) (g.findViewById(R.id.radioGroupDS56))
								.findViewById(R.id.radio4)).isChecked()) {

							((TextView) group.findViewById(R.id.textViewDS59P))
									.setVisibility(View.VISIBLE);
							((EditText) group.findViewById(R.id.DS59C))
									.setText("");
							((TextView) group.findViewById(R.id.textViewDS59C))
									.setVisibility(View.VISIBLE);
							((EditText) group.findViewById(R.id.DS59P))
									.setVisibility(View.VISIBLE);
							((EditText) group.findViewById(R.id.DS59C))
									.setVisibility(View.VISIBLE);

						} else {

							((TextView) group.findViewById(R.id.textViewDS59P))
									.setVisibility(View.GONE);
							((EditText) group.findViewById(R.id.DS59C))
									.setText("");
							((TextView) group.findViewById(R.id.textViewDS59C))
									.setVisibility(View.GONE);
							((EditText) group.findViewById(R.id.DS59P))
									.setVisibility(View.GONE);
							((EditText) group.findViewById(R.id.DS59C))
									.setVisibility(View.GONE);

						}

					}
				});

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

				PatientDetail pd = new PatientDetail();
				if (pd.SavePatientORS(dbHelper, group)) {
					CommonStaticClass.findOutNextSLNo(
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQvar(),
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQnext1());
					CommonStaticClass.nextQuestion(ParentActivity.this);
				} else {
					CommonStaticClass.showMyAlert(con, "Save Failed",
							"Save Failed. Please fill all fields!");
				}

			}

		});
		btnClear = (Button) vg.findViewById(R.id.clButton);
		btnClear.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				resetViewGroup((ViewGroup) vv);
			}

		});
		((Button) vg.findViewById(R.id.clButton)).setVisibility(View.GONE);
		notesButton = (Button) vg.findViewById(R.id.btnNote);
		notesButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				FraNotes();

			}

		});

		((RadioGroup) vg.findViewById(R.id.radioGroupDS41))
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup g, int checkedId) {
						if (((RadioButton) (g.findViewById(R.id.radioGroupDS41))
								.findViewById(R.id.radio0)).isChecked()) {

							((TextView) group.findViewById(R.id.lblDS42))
									.setVisibility(View.GONE);
							((EditText) group.findViewById(R.id.DS42))
									.setText("");
							((EditText) group.findViewById(R.id.DS42))
									.setVisibility(View.GONE);

						} else {
							((TextView) group.findViewById(R.id.lblDS42))
									.setVisibility(View.VISIBLE);
							((EditText) group.findViewById(R.id.DS42))
									.setVisibility(View.VISIBLE);

						}

					}
				});

		if (pd.getDS41() > 0) {
			if (pd.getDS41() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS41))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS41() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS41))
						.findViewById(R.id.radio1)).setChecked(true);

		}

	}

	private void loadguifrmchildren(ViewGroup vg) {
		final ViewGroup group = vg;

		String v = getSkip("AgeYr", "SCVBDS");
		if (v.length() > 0 && !v.equalsIgnoreCase("null")) {
			if (Integer.parseInt(v) > 5) {
				CommonStaticClass.findOutNextSLNo(CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar(), "END");
				CommonStaticClass.nextQuestion(ParentActivity.this);
				return;
			}
		}

		qName = CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar();

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

		((TextView) findViewById(R.id.lbl17)).setVisibility(View.GONE);
		((EditText) findViewById(R.id.GISID)).setVisibility(View.GONE);

		PatientDetail pd = new PatientDetail();
		pd = pd.GetPatientdetail(dbHelper);

		if (pd.getDS60() > 0) {
			if (pd.getDS60() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS60))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS60() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS60))
						.findViewById(R.id.radio1)).setChecked(true);

			else if (pd.getDS60() == 3)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS60))
						.findViewById(R.id.radio2)).setChecked(true);

			else if (pd.getDS60() == 4)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS60))
						.findViewById(R.id.radio3)).setChecked(true);

		}

		((EditText) group.findViewById(R.id.DS61)).setText(String.valueOf(pd
				.getDS61()));

		((RadioGroup) vg.findViewById(R.id.radioGroupDS60))
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup g, int checkedId) {
						if (((RadioButton) (g.findViewById(R.id.radioGroupDS60))
								.findViewById(R.id.radio1)).isChecked()) {

							((TextView) group.findViewById(R.id.lbl2))
									.setVisibility(View.VISIBLE);
							((EditText) group.findViewById(R.id.DS61))
									.setVisibility(View.VISIBLE);

						} else {

							((EditText) group.findViewById(R.id.DS61))
									.setText("");
							((TextView) group.findViewById(R.id.lbl2))
									.setVisibility(View.GONE);
							((EditText) group.findViewById(R.id.DS61))
									.setVisibility(View.GONE);

						}

					}
				});

		if (pd.getDS62() > 0) {
			if (pd.getDS62() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS62))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS62() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS62))
						.findViewById(R.id.radio1)).setChecked(true);

			else if (pd.getDS62() == 4)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS62))
						.findViewById(R.id.radio2)).setChecked(true);

		}

		if (pd.getDS63() > 0) {
			if (pd.getDS63() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS63))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS63() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS63))
						.findViewById(R.id.radio1)).setChecked(true);

			else if (pd.getDS63() == 3)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS63))
						.findViewById(R.id.radio1)).setChecked(true);

		}

		if (pd.getDS64() > 0) {
			if (pd.getDS64() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS64))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS64() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS64))
						.findViewById(R.id.radio1)).setChecked(true);
			else if (pd.getDS64() == 3)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS64))
						.findViewById(R.id.radio2)).setChecked(true);
			else if (pd.getDS64() == 4)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS64))
						.findViewById(R.id.radio3)).setChecked(true);
			else if (pd.getDS64() == 5)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS64))
						.findViewById(R.id.radio4)).setChecked(true);
			else if (pd.getDS64() == 6)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS64))
						.findViewById(R.id.radio5)).setChecked(true);

		}

		if (pd.getDS65() > 0) {
			if (pd.getDS65() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS65))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS65() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS65))
						.findViewById(R.id.radio1)).setChecked(true);
			else if (pd.getDS65() == 3)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS65))
						.findViewById(R.id.radio2)).setChecked(true);
			else if (pd.getDS65() == 4)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS65))
						.findViewById(R.id.radio3)).setChecked(true);
			else if (pd.getDS65() == 5)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS65))
						.findViewById(R.id.radio4)).setChecked(true);
			else if (pd.getDS65() == 6)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS65))
						.findViewById(R.id.radio5)).setChecked(true);

			else if (pd.getDS65() == 7)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS65))
						.findViewById(R.id.radio5)).setChecked(true);

		}

		if (pd.getDS66() > 0) {
			if (pd.getDS66() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS66))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS66() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS66))
						.findViewById(R.id.radio1)).setChecked(true);

			else if (pd.getDS66() == 3)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS66))
						.findViewById(R.id.radio2)).setChecked(true);

			else if (pd.getDS66() == 4)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS66))
						.findViewById(R.id.radio3)).setChecked(true);

		}

		if (pd.getDS67() > 0) {
			if (pd.getDS67() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS67))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS67() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS67))
						.findViewById(R.id.radio1)).setChecked(true);

			else if (pd.getDS67() == 3)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS67))
						.findViewById(R.id.radio2)).setChecked(true);

			else if (pd.getDS67() == 4)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS67))
						.findViewById(R.id.radio3)).setChecked(true);

			else if (pd.getDS67() == 5)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS67))
						.findViewById(R.id.radio4)).setChecked(true);

			else if (pd.getDS67() == 6)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS67))
						.findViewById(R.id.radio5)).setChecked(true);

		}

		if (pd.getDS68() > 0) {
			if (pd.getDS68() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS68))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS68() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS68))
						.findViewById(R.id.radio1)).setChecked(true);

			else if (pd.getDS68() == 3)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS68))
						.findViewById(R.id.radio2)).setChecked(true);

			else if (pd.getDS68() == 4)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS68))
						.findViewById(R.id.radio3)).setChecked(true);

			else if (pd.getDS68() == 5)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS68))
						.findViewById(R.id.radio4)).setChecked(true);

			else if (pd.getDS68() == 6)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS68))
						.findViewById(R.id.radio5)).setChecked(true);

		}

		if (pd.getDS69() > 0) {
			if (pd.getDS69() == 1)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS69))
						.findViewById(R.id.radio0)).setChecked(true);
			else if (pd.getDS69() == 2)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS69))
						.findViewById(R.id.radio1)).setChecked(true);
			else if (pd.getDS69() == 3)
				((RadioButton) ((RadioGroup) vg
						.findViewById(R.id.radioGroupDS69))
						.findViewById(R.id.radio2)).setChecked(true);

		}
		((EditText) vg.findViewById(R.id.GISID)).setText(String.valueOf(pd
				.getGISID()));

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

				PatientDetail pd = new PatientDetail();
				if (pd.SavePatientChildren(dbHelper, group)) {
					CommonStaticClass.findOutNextSLNo(
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQvar(),
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQnext1());
					CommonStaticClass.nextQuestion(ParentActivity.this);
				} else {
					CommonStaticClass.showMyAlert(con, "Save Failed",
							"Save Failed. Please fill all fields!");
				}

			}

		});
		btnClear = (Button) vg.findViewById(R.id.clButton);
		btnClear.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				resetViewGroup((ViewGroup) vv);
			}

		});
		((Button) vg.findViewById(R.id.clButton)).setVisibility(View.GONE);

		notesButton = (Button) vg.findViewById(R.id.btnNote);
		notesButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				FraNotes();

			}

		});

	}

	private void IfZeroThenClear(EditText editText) {
		if (editText.getText().toString().equalsIgnoreCase("0")) {
			editText.setText("");
		}

	}

	@Override
	public void onBackPressed() {
		// if(formFlipper.getDisplayedChild()!=5){
		userPressedPrevious(this);
		// }
	}
}
