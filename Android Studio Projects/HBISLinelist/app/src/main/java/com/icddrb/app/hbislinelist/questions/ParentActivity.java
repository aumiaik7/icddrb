package com.icddrb.app.hbislinelist.questions;

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
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
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


import com.icddrb.app.hbislinelist.BaseActivity;
import com.icddrb.app.hbislinelist.CommonStaticClass;
import com.icddrb.app.hbislinelist.Options;
import com.icddrb.app.hbislinelist.R;
import com.icddrb.app.hbislinelist.adapters.SpinAdapter;
import com.icddrb.app.hbislinelist.db.DatabaseHelper;

import net.sqlcipher.Cursor;

public class ParentActivity extends BaseActivity implements FormListener {

	//
	static ParentActivity thisactivity;
	
	//For checking question 409

	// frmaddress part
	private EditText txtHoldingNo, txtPara, txtVillage, txtunion, txtupazila,txtclusterID,
					 txtclusterIDRe,txtmotherID,txtmotherIDRe,txtDistrict, txtPhone1, txtPhone2;
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
	static final int DATE_DIALOG = 1;
	private int dateYear;
	private int dateMonth;
	private int dateDay;

	// frmfamilymember part

	private boolean admOrdis = true;

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
	static final int DATE_DIALOG_ID = 3, HBIS_DATE_DIALOG_ID = 5;;
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

	private int dobYear;
	private int dobMonth;
	private int dobDay;

	private EditText txtschoolID, txtID, txtschoolIDRe, txtIDRe;
	private String schoolid = "", id = "", schoolidre = "", idre = "";
	private ProgressDialog progressDialog;
	private static final int UPDATEDONE = 900, FILEREADFAILED = 1000,
			FILECONTENTMISSING = 1100;
	private String line = "";
	private Button genButton, confButton;

	// frmmultiplecheckcombo
	private ArrayList<String> optionList1 = null, optionList2 = null,
			optionList3 = null;
	private ArrayList<Integer> optionCodeList1 = null, optionCodeList2 = null,
			optionCodeList3 = null;
	DisplayMetrics dm;
	private ArrayList<Integer> aaa = new ArrayList<Integer>();

	private ArrayList<String> listvalues = new ArrayList<String>();

	private boolean spinnerOK = true;
	private int[] checkValues;
	
	// frmmultiplechoice
	private LinkedHashMap<Integer, EditText> edList = new LinkedHashMap<Integer, EditText>();
	private static boolean datepickershow = false;

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
	int q_children = 1;


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
	private String other1 = "", other2 = "", other3 = "",
					cluster = "", mother = "";
	
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


	AutoCompleteTextView autoThana,autoDist;

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
			frmnumericwithrdbtn, frmfindsection, frmneonatelinfo,frmcamera,frmmultiplechoicetext,
			frmaddress, frmpersonrelation, frmsymptoms, frmdataid2;

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
	
	// frmcamera
	private TouchImageView mImageView;
	private static final int ACTION_TAKE_PHOTO_S = 2;
	private String mCurrentPhotoPath;
	private static final String JPEG_FILE_PREFIX = "IMG_";
	private static final String JPEG_FILE_SUFFIX = ".jpg";
	private Bitmap mImageBitmap;
	private static final String BITMAP_STORAGE_KEY = "viewbitmap";
	private static final String IMAGEVIEW_VISIBILITY_STORAGE_KEY = "imageviewvisibility";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.parentlayout);
		thisactivity = this;

		if (DatabaseHelper.getInstanceBase() == null) {
			dbHelperBase = new DatabaseHelper(thisactivity);
			// dbHelperBase.openDataBase_BASE();
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
		// motion();
	}

	

	private boolean FileRead() {
		InputStream instream = null;

		try {
			String path = "/mnt/sdcard/Android/AssetID.txt";
			Log.e("path", "" + path);
			File f = new File(path);
			instream = new FileInputStream(f);
			
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
		frmcomboswitheditspiner = (ViewGroup) findViewById(R.id.frmcomboswitheditspiner);
		frmmultiplecheckcombotwo = (ViewGroup) findViewById(R.id.frmmultiplecheckcombotwo);
		frmmultiplechoiceradio = (ViewGroup) findViewById(R.id.frmmultiplechoiceradio);
		frmmultiple = (ViewGroup) findViewById(R.id.frmmultiple);
		frmq124 = (ViewGroup) findViewById(R.id.frmq124);
		frmmultiplechecknumeric = (ViewGroup) findViewById(R.id.frmmultiplechecknumeric);
		frmmultiplecheckdate = (ViewGroup) findViewById(R.id.frmmultiplecheckdate);
		frmbarcode = (ViewGroup) findViewById(R.id.frmbarcode);
		frmnumericwithrdbtn = (ViewGroup) findViewById(R.id.frmnumericwithrdbtn);
		frmfindsection = (ViewGroup) findViewById(R.id.frmfindsection);
		frmneonatelinfo = (ViewGroup) findViewById(R.id.frmneonatelinfo);
		frmcamera = (ViewGroup) findViewById(R.id.frmcamera);
		frmmultiplechoicetext = (ViewGroup) findViewById(R.id.frmmultiplechoicetext);
		frmaddress = (ViewGroup) findViewById(R.id.frmaddress);
		frmpersonrelation = (ViewGroup) findViewById(R.id.frmpersonrelation);
		frmsymptoms = (ViewGroup) findViewById(R.id.frmsymptoms);
		frmdataid2 = (ViewGroup) findViewById(R.id.frmdataid2);
	}

	protected void FraNotes() {
		// TODO Auto-generated method stub
		this.gotoForm("FrmNotes");
	}

	// FrmMultipleCheckText

	//This is a custom form
	/*private void loadGuiFrmMultipleChoiceRadio(final ViewGroup v) {

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
								
								 * ((EditText) v.findViewById(R.id.et1))
								 * .setText(""); ((EditText)
								 * v.findViewById(R.id.et1))
								 * .setVisibility(View.GONE);
								 
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

							
							 * ((EditText)
							 * v.findViewById(R.id.day2)).setText("");
							 * ((EditText) v.findViewById(R.id.day2))
							 * .setVisibility(View.GONE);
							 

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
								
								 * ((EditText) v.findViewById(R.id.et3))
								 * .setText(""); ((EditText)
								 * v.findViewById(R.id.et3))
								 * .setVisibility(View.GONE);
								 
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
								
								 * ((EditText) v.findViewById(R.id.et4))
								 * .setText(""); ((EditText)
								 * v.findViewById(R.id.et4))
								 * .setVisibility(View.GONE);
								 
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
				
				 * ((EditText) v.findViewById(R.id.et3)).setText(cls
				 * .getq612_3_other());
				 
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
				
				 * ((EditText) v.findViewById(R.id.et6)).setText(cls
				 * .getq612_6_other());
				 
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

	}*/

	/*private void setfonttobangla(ViewGroup v) {
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

	}*/

	/*private void updateTableDataFrmMultipleChoiceRadio(custom_class c) {

		if (c.save(c)) {
			CommonStaticClass.findOutNextSLNo(
					CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar(),
					CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQnext1());
			CommonStaticClass.nextQuestion(ParentActivity.this);
		}

	}*/

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

	/*private void updateTableDataFrmMultipleCheckText() {
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

	}*/

	// method to load FrmAddress GUI and data
	/*private void Load_UIFrmAddress(final ViewGroup v) {
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

	}*/

	// Populate the controls
	/*private void doFillFrmAddress(Cursor mCursor1) {
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
	}*/



	/*private void showUserFinishDialogFrmAddress() {
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
	}*/



	/*private void Fill_labelFrmAddress(ViewGroup v) {
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

	}*/

	/*private void Load_DataFrmAddress() {
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
	}*/

	/*private boolean characterCheckingFrmAddress(String qAns) {
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
	}*/

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
			// added by imtiaz khan
			if (CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo)
					.getQvar().equalsIgnoreCase("q3_4")
					|| CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQvar()
					.equalsIgnoreCase("qUserid")) {

				// for Reading data from a specific table like user, member etc.
				Cursor mCursor = null;
				String sql = "";
				users = new ArrayList<String>();
				users.add("");
				userIDs = new ArrayList<String>();
				userIDs.add("");

				try {
					
					
						sql = String.format("select * from tblUser");
					
					
					
						mCursor = dbHelper.getQueryCursor(sql);
						if (mCursor.moveToFirst()) {
							do {
								
									users.add(mCursor.getString(mCursor
											.getColumnIndex("ID"))
											+ " : "
											+ mCursor.getString(mCursor
													.getColumnIndex("Name")));
									userIDs.add(mCursor.getString(mCursor
											.getColumnIndex("ID")));
	
																
							} while (mCursor.moveToNext());
						}
					
					

						adapterForCombo = new ArrayAdapter(this,
								android.R.layout.simple_spinner_item, users);
						adapterForCombo
								.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

						spinner.setAdapter(adapterForCombo);
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (mCursor != null)
						mCursor.close();
				}

			} 
			
			
			else // applicable when Spinner is loaded from options table or add number in loop
			{
				//it adds number to combox in loop
				if (CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar()
						.equalsIgnoreCase("qd")
						) {
					int iCount = 5;			
					
					for (int i = 1; i <= iCount; i++) {
						op.capBngList.add(Integer.toString(i));
						op.capEngList.add(Integer.toString(i));
						
						op.codeList.add(i);

					}
					op.capBngList.add(0, "");
					op.capEngList.add(0, "");
					op.codeList.add(0, -1);
					adapterForCombo = new ArrayAdapter(this,
							android.R.layout.simple_spinner_item, op.capEngList);
					adapterForCombo
							.setDropDownViewResource(CommonStaticClass.langBng ? R.layout.checkedspintextview
									: android.R.layout.simple_spinner_dropdown_item);
					spinner.setAdapter(adapterForCombo);

				}
				//it adds options from tblOptions to combobox for a question
				else 
				{
					op = CommonStaticClass.findOptionsForThisQuestion(
							dbHelper,
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQvar());
					op.capBngList.add(0, "");
					op.capEngList.add(0, "");
					op.codeList.add(0, -1);

					
					if (CommonStaticClass.langBng) {
						//code by imtiaz khan
						
							adapterForCombo = new SpinAdapter(this, op.capBngList, true);

					} else {
						adapterForCombo = new ArrayAdapter(this,
								android.R.layout.simple_spinner_item, op.capEngList);

					}
					
					
					adapterForCombo
							.setDropDownViewResource(CommonStaticClass.langBng ? R.layout.checkedspintextview
									: android.R.layout.simple_spinner_dropdown_item);
					
					spinner.setAdapter(adapterForCombo);
					// spinner.setOnItemSelectedListener(new
					// spinItemSelectListener());
				}
			}
			
			// Slected Index change event of Spinner
			spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

				public void onItemSelected(AdapterView<?> parent, View view,
						int pos, long id) {
					// TODO Auto-generated method stub
					
					if (parent.getItemAtPosition(pos).toString().length() > 0) {
						
						//if you want to store the whole text from selected combo index
						if (CommonStaticClass.questionMap
								.get(CommonStaticClass.currentSLNo).getQvar()
								.equalsIgnoreCase("q3_4") 
								)
							sResCode = parent
									.getItemAtPosition(pos)
									.toString()
									;
						//if you want to extract and store userid from selected combo index
						else if( CommonStaticClass.questionMap
								.get(CommonStaticClass.currentSLNo).getQvar()
								.equalsIgnoreCase("qUserid")
								)
						{
							sResCode = parent
									.getItemAtPosition(pos)
									.toString()
									.substring(
											0,
											(parent.getItemAtPosition(pos)
													.toString().lastIndexOf(":") - 1));
						}
						//Stores from codelist
						else
						{
							sResCode = op.codeList.get(pos).toString();
							//sResName = op.capEngList.get(pos).toString();
						}
					}
					else 
						sResCode = "";

				}

				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub

				}

			});

			
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
								//condition for loading data if the whole 
								//text of selected index is stored in db
								if ( CommonStaticClass.questionMap
												.get(CommonStaticClass.currentSLNo)
												.getQvar()
												.equalsIgnoreCase("q3_4")
										)

									index = CommonStaticClass
											.GetIndexFromCollection(users, a);
								//condition for loading data if only id of selected index is stored in db
								else if(CommonStaticClass.questionMap
										.get(CommonStaticClass.currentSLNo).getQvar()
										.equalsIgnoreCase("qUserid")
												)
								{
									index = CommonStaticClass
											.GetIndexFromCollection(userIDs, a);
								}
								
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
			CommonStaticClass
			.showMyAlert(con, "Notification",
					"Please Select an Item in order to proceed");
			return;
		}
		try 
		{
			String sql = "";
			
			
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

			
			if(dbHelper.executeDMLQuery(sql))
			{	
			
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
	

	private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			dateYear = year;
			dateMonth = monthOfYear;
			dateDay = dayOfMonth;

			dobYear = year;
			dobMonth = monthOfYear;
			dobDay = dayOfMonth;
			//updateDisplay("date");
			
			if (pickDate != null) {
				if (pickDate.isFocused())
				pickDate.setText((((dateDay < 10) ? "0" : "") + dateDay)+"/"+
						(((dateMonth+1 < 10) ? "0" : "") + (dateMonth+1))+"/"+dateYear);
			}



			if (dischargeDate != null) {
				if (dischargeDate.isFocused())
					dischargeDate.setText((((dateDay < 10) ? "0" : "") + dateDay)+"/"+
							(((dateMonth+1 < 10) ? "0" : "") + (dateMonth+1))+"/"+dateYear);
			}

			if (infoText2 != null) {
				if (infoText2.isFocused())
					infoText2.setText((((dateDay < 10) ? "0" : "") + dateDay)+"/"+
							(((dateMonth+1 < 10) ? "0" : "") + (dateMonth+1))+"/"+dateYear);
			}

				// }
			}
			

	};

	//laod gui for frmdate
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
		final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
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
				/*updateDisplay("date");*/
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


	EditText etpickdate;

	/*private void updateDisplayfrmfamily(String dt, EditText et) {

		if (dt.contains("date")) {
			String date = new StringBuilder()
					// Month is 0 based so add 1
					.append(dateDay).append(" ")
					.append(GetMonth(dateMonth + 1)).append(" ")
					.append(dateYear).toString();
			// Log.d("Date: ",date);
			et.setText(date);
		}

	}*/

	private void updateDisplay(String dt) {

		/*String time = new StringBuilder()

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
					.append(dateDay).append("/")
					.append(dateMonth).append("/")
					.append(dateYear).toString();
			

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
		}*/

	}

	private void updateTableDataFrmDate() {
		// TODO Auto-generated method stub
		String qAns = pickDate.getText().toString();
		try {
			String currentQuestion = CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQvar();
			if (qAns.length() > 0) {

				//if you want to restrict setting future date unquote the following portion
				/*if (futureDateValidator(dateYear, dateMonth, dateDay)) {
					CommonStaticClass
							.showMyAlert(con, "Not Correct",
									"You are putting future date which is not acceptable");
					return;
				}*/

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

	/*private String GetMonth(int month) {
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

	}*/

	// frmfamilymember part
	/*private void loadGuiFrmFamilyMember(final ViewGroup v) {
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
	}*/

	/*private void filllAllSpinnerDataFrmFamilyMember() {
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

	}*/

	/*private void filllAllSpinnerDataq124() {
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

	}*/

	/*private void checkDataBaseHasLinesOrNotFrmFamilyMember() {
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
	}*/

	/*private void checkDataBaseHasLinesOrNotq124() {
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

						
						 * memberIDs.add(CommonStaticClass.dataId.substring(0,
						 * 5) + serialNo.substring(serialNo.length() - 2,
						 * serialNo.length()));
						 
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
	}*/

	/*class spinItemSelectListenerFrmFamilyMember implements
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

	}*/

	/*class spinItemSelectListenerq124 implements OnItemSelectedListener {

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

	}*/

	/*private void updateTableDataFrmFamilyMember() {
		if (!IsValidFrmFamilyMember())
			return;
		else {
			Date d = new Date(System.currentTimeMillis());
			String entryDate = "dd-mmm-yy";

			entryDate = d.toLocaleString();
			// CommonStaticClass.memberID=memberIDs.get(spinnerSL.getSelectedItemPosition());
			
			 * String v = getSkip("q1_6", "tblLinelist"); if (v != null) { if
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
	}*/

	// q124
	/*private void updateTableDataq124() {
		if (!IsValidq124())
			return;
		else {
			Date d = new Date(System.currentTimeMillis());
			String entryDate = "dd-mmm-yy";
			entryDate = d.toLocaleString();

			String v = getSkip("q123", "tblLinelist");
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

			
			 * + "    values('" + CommonStaticClass.dataId + "','" +
			 * memberIDs.get(spinnerSL.getSelectedItemPosition()) + "','" +
			 * AgeYear + "','" + sex + "','" + CommonStaticClass.userSpecificId
			 * + "','" + entryDate + "')";
			 
			if (dbHelper.executeDMLQuery(s1)) {

			} else {

				
				 * c2, c2_other, c3, c4, c5, c5_2, c5_3, c5_4, c6, EntryBy,
				 * EntryDate
				 

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

			
			 * if (memberIDs.contains((Integer.parseInt(memberID) + 1) + "")) {
			 * 
			 * insertMoreFrmFamilyMember(false); CommonStaticClass.totalHHMember
			 * = Integer.parseInt(memberID); //
			 * spinnerSL.setSelection(Integer.parseInt(memberID));
			 * 
			 * }
			 

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
	}*/

	/*private void promptUserForInputFrmFamilyMember(final Spinner spinner) {
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
	}*/

	/*private String getOtherDataFrmFamilyMember() {
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
	}*/

	/*public void insertDataToRelationOtherFrmFamilyMember(String data) {
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
	}*/

	/*public void findDataForThisSelectionFrmFamilyMember(String memberid) {
		// TODO Auto-generated method stub
		
		 * String sql = "Select * from tblMedicineInfo where dataid = '" +
		 * CommonStaticClass.dataId + "' AND memid='" + memberID + "'";
		 

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

				
				 * sql = "Select * from tblFamilyInfo where dataid = '" +
				 * CommonStaticClass.previoushouseHoldDatatId + "' AND memid='"
				 * + memberID + "'";
				 

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
	}*/

	/*public void findDataForThisSelectionq124(String memberid) {
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
	}*/

	/*private void doFillFrmFamilyMember(Cursor mCursor1) {
		// TODO Auto-generated method stub
		if (mCursor1.moveToFirst()) {
			do {
				
				 * txtMname.setText(mCursor1.getString(mCursor1
				 * .getColumnIndex("Name")));
				 

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
	}*/

	/*private void doFillq124(Cursor mCursor1) {
		// TODO Auto-generated method stub
		if (mCursor1.moveToFirst()) {
			do {
				
				 * txtMname.setText(mCursor1.getString(mCursor1
				 * .getColumnIndex("Name")));
				 
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
	}*/

	/*private void resetViewsq124() {
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
*/
	/*private void resetViewsFrmFamilyMember() {
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

	}*/

	/*private void insertMoreFrmFamilyMember(boolean b) {
		// TODO Auto-generated method stub
		if (b) {

			memberIDs
					.add((Integer.parseInt(memberIDs.get(memberIDs.size() - 1)) + 1)
							+ "");

			resetViewsFrmFamilyMember();
		} else {
			// String sq =
			// "UPDATE tblLinelist SET q10='"+q101+"' where dataid='"+CommonStaticClass.dataId+"'";
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
	}*/

	/*private void insertMoreq124(boolean b) {
		// TODO Auto-generated method stub
		if (b) {

			memberIDs
					.add((Integer.parseInt(memberIDs.get(memberIDs.size() - 1)) + 1)
							+ "");

			resetViewsq124();
		} else {
			// String sq =
			// "UPDATE tblLinelist SET q10='"+q101+"' where dataid='"+CommonStaticClass.dataId+"'";
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
	}*/

	/*private boolean valueInFrmFamilyMember(String column1, String column2) {
		String sql = "Select " + column1 + "," + column2
				+ " from tblLinelist where dataid='" + CommonStaticClass.dataId
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
	}*/

	/*private boolean valueInFrmFamilyMember(String column1) {
		String sql = "Select " + column1 + " from tblLinelist where dataid='"
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
*/
	/*private boolean valueInFrmFamilyMember(String column1, String column2,
			String column3) {
		String sql = "Select " + column1 + "," + column2 + "," + column3
				+ " from tblLinelist where dataid='" + CommonStaticClass.dataId
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
	}*/

	/*String Medicine_other1 = "", Medicine_other2 = "", Medicine_other3 = "",
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
	}*/

	/*private boolean IsValidq124() {
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
	}*/

	/*private void HideFamily(ViewGroup v) {
		((EditText) v.findViewById(R.id.ettime)).setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.ettablet)).setVisibility(View.GONE);
		
		 * ((CheckBox)
		 * v.findViewById(R.id.chkcapsule)).setVisibility(View.GONE);
		 * ((CheckBox) v.findViewById(R.id.chkdrops)).setVisibility(View.GONE);
		 * ((CheckBox) v.findViewById(R.id.chkspoon)).setVisibility(View.GONE);
		 
		((EditText) v.findViewById(R.id.etMedicineOther))
				.setVisibility(View.GONE);

	}*/

	/*EditText ettime;

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
						 

					}
				});

	}*/

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

		Load_DataFrmGPSDataCollection();
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
					+ " SET " + qName + "Lon='" + sLongitude + "'," + qName
					+ "Lat='" + sLatitude + "' where dataid='"
					+ CommonStaticClass.dataId + "'";

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
			Toast.makeText(con, e.toString(), Toast.LENGTH_LONG);
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
					String column1 = qName+"Lon";
					String column2 = qName+"Lat";

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


	//Custom form
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
				.format("UPDATE tblLinelist SET c609_1_1='%s', c609_2_1='%s', c609_2_2='%s', c609_2_3='%s', c609_2_4='%s', c609_2_5='%s', c609_2_5_other='%s',"
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

		//checksession();

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

/*	private void FillAllDataidCombo(final ViewGroup v) {

		final ViewGroup vg = v;
		// Data id spinners

		ArrayList<String> ids = new ArrayList<String>();
		ids.add("");

		try {
			for (int i = 1; i <= 6; i++) {
				ids.add(String.valueOf(i));
			}
			ArrayAdapter a = new ArrayAdapter(thisactivity,
					android.R.layout.simple_spinner_dropdown_item, ids);
			a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			((Spinner) v.findViewById(R.id.sphosid)).setAdapter(a);
			((Spinner) v.findViewById(R.id.sphosidre)).setAdapter(a);

		} catch (Exception e) {

			e.printStackTrace();
			// TODO: handle exception

		} finally {

		}

		ArrayList<String> idpps = new ArrayList<String>();
		idpps.add("");

		try {
			for (int i = 1; i <= 2; i++) {
				idpps.add(String.valueOf(i));
			}
			ArrayAdapter b = new ArrayAdapter(thisactivity,
					android.R.layout.simple_spinner_dropdown_item, idpps);
			b.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			((Spinner) v.findViewById(R.id.sppid)).setAdapter(b);
			((Spinner) v.findViewById(R.id.sppidre)).setAdapter(b);

		} catch (Exception e) {

			e.printStackTrace();
			// TODO: handle exception

		} finally {

		}
		// ////////////////////////////////////////////////////

	}*/



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

	private void FillAllDataidCombo(ViewGroup v) {





		ArrayList<String> yearids = new ArrayList<String>();
		yearids.add("15");
		yearids.add("16");
		yearids.add("17");
		yearids.add("18");
		yearids.add("19");
		yearids.add("20");

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

	private boolean IsValidDataIDUserInput(ViewGroup v) {

		CommonStaticClass.dataId = ((Spinner) v.findViewById(R.id.sp2)).getSelectedItem()
				.toString()
				+ ((Spinner) v.findViewById(R.id.sp3)).getSelectedItem()
				.toString()
				+ ((Spinner) v.findViewById(R.id.sp4)).getSelectedItem()
				.toString()
				+ ((Spinner) v.findViewById(R.id.sp5)).getSelectedItem()
				.toString();
		String ReDataid = ((Spinner) v.findViewById(R.id.spl2)).getSelectedItem()
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

	/*private boolean IsValidDataIDUserInput() {
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
	}*/


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

				if (CommonStaticClass.dataId.length() < 9) {
					CommonStaticClass.showMyAlert(con, "Error",
							"Select all fields");
					return;
				}
				String entryDate = "dd/mm/yyyy";
				String entryTime = "hh:mm";
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date d = new Date(System.currentTimeMillis());
				entryDate = sdf.format(d);
				sdf = new SimpleDateFormat("HH:mm");
				entryTime = sdf.format(d);

				sql = String
						.format("Insert into %s (dataid,VersionNo,assetid,YearID,MonthID,HosID,PatID,EntryBy,EntryDate, EntryTime) VALUES('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
								CommonStaticClass
										.GetTableName(CommonStaticClass.questionMap
												.get(CommonStaticClass.currentSLNo)
												.getQvar()),
								CommonStaticClass.dataId,
								CommonStaticClass.VersionNo,
								CommonStaticClass.AssetID,
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

				/*String s = String
						.format("Insert into %s (dataid,VersionNo,assetid,EntryBy,EntryDate) VALUES('%s','%s','%s','%s','%s')",
								"tblMainQuesEPT", CommonStaticClass.dataId,
								CommonStaticClass.VersionNo,
								CommonStaticClass.AssetID,
								CommonStaticClass.userSpecificId, entryDate);*/

				if (dbHelper.executeDMLQuery(sql)
//						&& dbHelper.executeDMLQuery(s)
					) {

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

	/*private boolean UpdateOtherField(ViewGroup v) {

		
		 * try {
		 * 
		 * String strSQL = "update tblLinelist set PatName = '" + Name +
		 * "',Comp = '" + Comp + "',YearID = '" + YearID + "',MonthID  = '" +
		 * MonthID + "',HosID  = '" + HosID + "',HosName  = '" + HosName +
		 * "',PatID = '" + PatID + "',AgeY  = '" + AgeY + "',AgeM   = '" + AgeM
		 * + "',AgeD   = '" + AgeD + "',Sex   = '" + Sex + "',Phone   = '" +
		 * Phone + "' where dataid = '" + CommonStaticClass.dataId + "'";
		 * dbHelper.executeDMLQuery(strSQL); } catch (Exception ex) {
		 * Log.e("Exception", ex.getMessage()); }
		 

		return true;

	}*/

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
		String sql = "Select * from tblLinelist where dataid='" + dataid2 + "'";
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
		String sql = "Select randomid from frmrtblSamplesInfo where sampleid='"
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
		

		} else {
			qqq.setTypeface(null);
			qqq.setText(Html.fromHtml(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQdesceng()));
			

			
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

		qName = CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar();

		
		
			CommonStaticClass.findOutNextSLNo(
					CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar(),
					CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQnext1());
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
		optionCodeList1.add(0);
		for (int i = 0; i < op.codeList.size(); i++) {

			if (op.qidList.get(i).contains("Options")
					|| op.qidList.get(i).contains("Option")
					|| op.qidList.get(i).contains("options")
					|| op.qidList.get(i).contains("option")) {
				Log.e("op.qidList.get(i)", op.qidList.get(i));
				if (CommonStaticClass.langBng) {
					optionList1.add(op.capBngList.get(i));
				} else {
					optionList1.add(op.capEngList.get(i));
				}
				optionCodeList1.add(op.codeList.get(i));
				continue;
			}
			aaa.add(0);
		}

		checkValues = new int[op.codeList.size()];
		// op.codeList.toArray().
		for (int i = op.codeList.size() - 1 ; i >= 0; i--) {
			

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
								checkValues[checkButton.getId()] = 1;
							} else {
								aaa.set(checkButton.getId(), 0);// added by me
								spinner.setVisibility(View.INVISIBLE);
								checkValues[checkButton.getId()] = 0;
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

	//No need to use this for. frmmultiple choice can do this task by apllying simple condition
	/*private void loadGuiFrmMultipleCheckNumeric(final ViewGroup v) {
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

			
			 * if(qName.equalsIgnoreCase(string)
			 * if(checkButton.getText().toString
			 * ().toLowerCase().contains("other") ||
			 * checkButton.getText().toString
			 * ().toLowerCase().contains("AbÂ¨vbÂ¨")) {
			 * et.setInputType(InputType.TYPE_CLASS_TEXT); }
			 * 
			 * else
			 
			et.setInputType(InputType.TYPE_CLASS_NUMBER);

			layoutParamForSpin.weight = 1;
			ln.addView(et, 0, layoutParamForSpin);
			et.setVisibility(View.INVISIBLE);
			ln.addView(checkButton, 0, layoutParamForcheck);

			
			 * if (checkButton.getText().toString().trim().toLowerCase()
			 * .contains("others") ||
			 * checkButton.getText().toString().trim().toLowerCase()
			 * .contains("AbÂ¨vbÂ¨: wjLyb")) { ln.removeView(et);
			 * et.setVisibility(View.GONE); }
			 

			
			 * if (op.capEngList.get(i).toLowerCase().contains("others")) {
			 * ln.removeView(et); et.setVisibility(View.GONE); }
			 

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
	}*/

	private void selectCheckAndComboFrmMultipleCheckCombo(String inColumn,
			CheckBox checkButton, Spinner spinner) {
		// TODO Auto-generated method stub
		String sql = "";
		
			sql = "Select "
					+ inColumn
					+ " from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " where dataid='" + CommonStaticClass.dataId + "'";
		

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

	/*private void selectCheckAndComboFrmMultipleCheckNumeric(String inColumn,
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

	}*/

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
								&& Integer.parseInt(a) != 0
								) { // if you want all options to be checked 
									// comment Integer.parseInt(a) != 0 portion

							int pos = optionCodeList1.indexOf(Integer
									.parseInt(a));

							checkButton.setChecked(true);
							checkValues[checkButton.getId()] = 1;
							spinner.setSelection(pos);
							dataOk = true;

						}

					} catch (Exception e) {
						// TODO: handle exception
					} finally {
						
					}

				}

			} while (mCursor1.moveToNext());
		}

		return dataOk;
	}

	/*private boolean doFillFrmMultipleCheckNumeric(Cursor mCursor1,
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
	}*/

	
	/*private String getColumnValue(String column, String tablename) {

		String colValue = "";
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

						colValue = mCursor.getString(mCursor
								.getColumnIndex(column));

					} while (mCursor.moveToNext());

				}

			}

		} catch (Exception e) {

			// TODO: handle exception

		}finally{
			if(mCursor != null)
				mCursor.close();
		}

		return colValue;

	}*/

	private void updateTableDataFrmMultipleCheckCombo(ViewGroup v) {
		// TODO Auto-generated method stub
		boolean otherCheck = false;
		boolean noComboValue = false;
	
		// check whether every checked option has a slected option from combo
		spinnerVisibleButNotSeletedFrmMultipleCheckCombo((ViewGroup) v);
	
		String sql = "";
	
		
		//The following portion is an example if you want to block 
		//forwaring the question without selecting all options
		/*if(CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar().equalsIgnoreCase("q206"))
		{
			if(aaa.get(0) == 0 || aaa.get(1) == 0
					|| aaa.get(2) == 0
					
					)
			{
				CommonStaticClass.showMyAlert(con, "ALERT",
						"You need to select all items in order to proceed");
				return;
			}
		}*/
		
		
		for (int i = 0; i < op.codeList.size(); i++) {
			if(op.qidList.get(i).contains("Options"))
				break;
			if(checkValues[i] == 1 && aaa.get(i) == 0 )
			{
				CommonStaticClass.showMyAlert(con, "Alert", "Must choose option for every selected checkbox");
				return;
			}
			
		}

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
				Log.e("Option Name :", "" + op.qidList.get(i));
			}
			
				sql += "where dataid='" + CommonStaticClass.dataId + "'";
			

			if (checkIfSingleOptionIsCheckedFrmMultipleCheckCombo()) {

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
//		} else {
//			CommonStaticClass.showMyAlert(con, "Please select item!!!",
//					"Please select an item from combo.");
//		}

	}

	/*private void updateTableDataFrmMultipleCheckNumeric(ViewGroup v) {
		// TODO Auto-generated method stub
		
		 * spinnerOK = true; CheckBoxNotSeletedFrmMultipleCheckCombo(); if
		 * (spinnerOK) {
		 * spinnerVisibleButNotSeletedFrmMultipleCheckCombo((ViewGroup) v); }
		 
		String sql = "";
		// spinnerOK = true;
		// if (spinnerOK) {

		sql = "UPDATE "
				+ CommonStaticClass.questionMap.get(
						CommonStaticClass.currentSLNo).getTablename() + " SET ";
		for (int i = 0; i < op.codeList.size(); i++) {
			
			 * if (op.qidList.get(i).contains("Options")) { continue; } if
			 * (op.qidList.get(i + 1).contains("Options")) { sql +=
			 * op.qidList.get(i) + " = '" + aaa.get(i) + "'"; break; }
			 
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

	}*/

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

//	private void loadGuiFrmMultipleChoice(final ViewGroup v) {
//		// TODO Auto-generated method stub
//		if (aaa != null && aaa.size() > 0) {
//			aaa.clear();
//		}
//		qqq = (TextView) v.findViewById(R.id.qqq);
//		qName = CommonStaticClass.questionMap
//				.get(CommonStaticClass.currentSLNo).getQvar();
//
//		if (CommonStaticClass.langBng) {
//			if (CommonStaticClass.questionMap
//					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0) {
//				font = Typeface.createFromAsset(con.getAssets(),
//						"Siyam Rupali ANSI.ttf");
//
//				qqq.setTypeface(font);
//			}
//			;
//			qqq.setText(CommonStaticClass.questionMap
//					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0 ? CommonStaticClass.questionMap
//					.get(CommonStaticClass.currentSLNo).getQdescbng()
//					: CommonStaticClass.questionMap.get(
//							CommonStaticClass.currentSLNo).getQdesceng());
//		} else {
//			qqq.setTypeface(null);
//			qqq.setText(CommonStaticClass.questionMap.get(
//					CommonStaticClass.currentSLNo).getQdesceng());
//		}
//
//		checkBoxHolder = (LinearLayout) v.findViewById(R.id.checkBoxHolder);
//		checkBoxHolder.removeAllViews();
//
//		if (qName.equalsIgnoreCase("q17hmd1")
//				|| qName.equalsIgnoreCase("q18md1")
//				|| qName.equalsIgnoreCase("q17hmd2")
//				|| qName.equalsIgnoreCase("q18md2")) {
//			/*
//			 * op = CommonStaticClass.findOptionsForMedicineList( dbHelper,
//			 * CommonStaticClass.questionMap.get(
//			 * CommonStaticClass.currentSLNo).getQvar());
//			 */
//		} else {
//			op = CommonStaticClass.findOptionsForThisQuestion(
//					dbHelper,
//					CommonStaticClass.questionMap.get(
//							CommonStaticClass.currentSLNo).getQvar());
//		}
//
//		Collections.reverse(op.capBngList);
//		Collections.reverse(op.capEngList);
//		Collections.reverse(op.codeList);
//		Collections.reverse(op.qidList);
//		Collections.reverse(op.qnList);
//
//		LinearLayout.LayoutParams lnlParams = new LinearLayout.LayoutParams(
//				LinearLayout.LayoutParams.FILL_PARENT,
//				LinearLayout.LayoutParams.WRAP_CONTENT);
//		LinearLayout.LayoutParams layoutParamForcheck = new LinearLayout.LayoutParams(
//				LinearLayout.LayoutParams.WRAP_CONTENT,
//				LinearLayout.LayoutParams.WRAP_CONTENT);
//
//		for (int i = 0; i < op.codeList.size(); i++) {
//			aaa.add(-1);
//		}
//
//		LinearLayout.LayoutParams layoutParamForTextBox = new LinearLayout.LayoutParams(
//				(dm.widthPixels / 3), LinearLayout.LayoutParams.WRAP_CONTENT);
//
//		for (int i = 0; i < op.codeList.size(); i++) {
//			final LinearLayout ln = new LinearLayout(this);
//			ln.setOrientation(LinearLayout.HORIZONTAL);
//			final CheckBox checkButton = new CheckBox(this);
//			if (CommonStaticClass.langBng) {
//				checkButton.setTypeface(font);
//				checkButton
//						.setText(op.capBngList.get(i).length() > 0 ? op.capBngList
//								.get(i) : op.capEngList.get(i));
//			} else {
//				checkButton.setTypeface(null);
//				checkButton.setText(op.capEngList.get(i));
//			}
//
//			checkButton.setId(op.codeList.get(i));
//
//			if (op.qnList.get(i).length() > 1) {
//				ln.setWeightSum((float) 2.0);
//				final EditText edbox = new EditText(this);
//				edbox.setId(i);
//				edList.put(i, edbox);
//				layoutParamForcheck.weight = 1;
//				layoutParamForTextBox.weight = 1;
//
//				ln.addView(edbox, 0, layoutParamForTextBox);
//				edbox.setVisibility(View.INVISIBLE);
//				/*
//				 * if (CommonStaticClass.mode
//				 * .equalsIgnoreCase(CommonStaticClass.EDITMODE) ||
//				 * CommonStaticClass.mode
//				 * .equalsIgnoreCase(CommonStaticClass.ADDMODE) ||
//				 * CommonStaticClass.mode.equalsIgnoreCase("")) {
//				 */
//				// checkEdboxHasDataFrmMultipleChoice(edbox, op.qnList.get(i));
//				// }
//
//				/*
//				 * if (CommonStaticClass.mode
//				 * .equalsIgnoreCase(CommonStaticClass.EDITMODE)) {
//				 */
//				checkEdboxHasDataFrmMultipleChoice(edbox, op.qnList.get(i));
//				// }
//
//			}
//
//			ln.setId(i);
//			ln.addView(checkButton, 0, layoutParamForcheck);
//
//			checkBoxHolder.addView(ln, 0, lnlParams);
//
//			checkButton
//					.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//
//						public void onCheckedChanged(CompoundButton buttonView,
//								boolean isChecked) {
//							// TODO Auto-generated method stub
//							if (isChecked) {
//
//								Log.e("id :", "" + checkButton.getId());
//								if (edList.containsKey(ln.getId())) {
//									edList.get(ln.getId()).setVisibility(
//											View.VISIBLE);
//								}
//								aaa.set(ln.getId(), checkButton.getId());
//							} else {
//								aaa.set(ln.getId(), -1);
//								if (edList.containsKey(ln.getId())) {
//									edList.get(ln.getId()).setVisibility(
//											View.INVISIBLE);
//
//								}
//							}
//						}
//					});
//
//			/*
//			 * if (CommonStaticClass.mode
//			 * .equalsIgnoreCase(CommonStaticClass.EDITMODE)) {
//			 */
//			checkIfChckButtonShouldBeCheckedFrmMultipleChoice(checkButton,
//					op.qidList.get(i));
//			// }
//		}
//
//		prevButton = (Button) v.findViewById(R.id.prevButton);
//		prevButton.setOnClickListener(new View.OnClickListener() {
//
//			public void onClick(View vv) {
//				// TODO Auto-generated method stub
//				userPressedPrevious(ParentActivity.this);
//			}
//
//		});
//		saveNxtButton = (Button) v.findViewById(R.id.saveNxtButton);
//
//		saveNxtButton.setOnClickListener(new View.OnClickListener() {
//
//			public void onClick(View vv) {
//				// TODO Auto-generated method stub
//				updateTableDataFrmMultipleChoice();
//
//			}
//
//		});
//		clButton = (Button) v.findViewById(R.id.clButton);
//		clButton.setOnClickListener(new View.OnClickListener() {
//
//			public void onClick(View vv) {
//				// TODO Auto-generated method stub
//				resetViewGroup((ViewGroup) v);
//			}
//
//		});
//		notesButton = (Button) v.findViewById(R.id.btnNote);
//		notesButton.setOnClickListener(new OnClickListener() {
//
//			public void onClick(View vv) {
//				// TODO Auto-generated method stub
//				FraNotes();
//
//			}
//
//		});
//	}
	
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

		
			op = CommonStaticClass.findOptionsForThisQuestion(
					dbHelper,
					CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar());
		
		edList.clear();
		Collections.reverse(op.capBngList);
		Collections.reverse(op.capEngList);
		Collections.reverse(op.codeList);
		Collections.reverse(op.qidList);
		Collections.reverse(op.qnList);

		LinearLayout.LayoutParams lnlParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams layoutParamForcheck = new LinearLayout.LayoutParams(
				(dm.widthPixels / 5) * 3,
				LinearLayout.LayoutParams.WRAP_CONTENT);

		for (int i = 0; i < op.codeList.size(); i++) {
			aaa.add(0);
		}

		LinearLayout.LayoutParams layoutParamForTextBox = new LinearLayout.LayoutParams(
				(dm.widthPixels / 5) * 2, LinearLayout.LayoutParams.WRAP_CONTENT);

		for (int i = 0; i < op.codeList.size(); i++) {
			final LinearLayout ln = new LinearLayout(this);
			ln.setOrientation(LinearLayout.HORIZONTAL);
			final CheckBox checkButton = new CheckBox(this);
			checkButton.setPadding(30, 5, 0, 0);
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
				//after checking he box if the corresponding checkbox opens 
				//a text box you can make it numeric, date or time
				//numeric
				if(qName.equalsIgnoreCase(""))
					edbox.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
				//date
				else if(qName.equalsIgnoreCase(""))
				{
					edbox.setFocusable(false);
					
					edbox.setOnClickListener(new OnClickListener() {
						
						public void onClick(View v) {
							// TODO Auto-generated method stub
							final Calendar c = Calendar.getInstance();
				            mYear = c.get(Calendar.YEAR);
				            mMonth = c.get(Calendar.MONTH);
				            mDay = c.get(Calendar.DAY_OF_MONTH);

				            // Launch Date Picker Dialog
				            DatePickerDialog dpd = new DatePickerDialog(ParentActivity.this,
				                    new DatePickerDialog.OnDateSetListener() {

				                        public void onDateSet(DatePicker view, int year,
				                                int monthOfYear, int dayOfMonth) {
				                            // Display Selected date in textbox
				                        	edbox.setText((((dayOfMonth < 10) ? "0" : "") + dayOfMonth) + "/"
				                                    + ((((monthOfYear+1) < 10) ? "0" : "") + (monthOfYear+1)) + "/" + year);
				                        	

				                        }
				                        
				                    }, mYear, mMonth, mDay);
				            
				            	dpd.setTitle("Select Date");
				            	dpd.show();
				          
							
						}
					});
				}
				//time
				else if(qName.equalsIgnoreCase(""))
				{
					edbox.setFocusable(false);
					
					
					edbox.setOnClickListener(new OnClickListener() {
						
						public void onClick(View v) {
							// TODO Auto-generated method stub
							

				            // Launch time Picker Dialog
							Calendar c = Calendar.getInstance();
				            int hour = c.get(Calendar.HOUR_OF_DAY);
				            int minute = c.get(Calendar.MINUTE);
				            TimePickerDialog mTimePicker;
				            mTimePicker = new TimePickerDialog(ParentActivity.this, new TimePickerDialog.OnTimeSetListener() {
				                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
				                    edbox.setText( (((selectedHour<10)? "0" :"")+ selectedHour) + ":" + 
				                    		(((selectedMinute<10)? "0" : "") + selectedMinute));
				                }
				            }, hour, minute, true);//Yes 24 hour time
				            mTimePicker.setTitle("Select Time");
				            mTimePicker.show();
				        
				       
				          
							
						}
					});
				}
				edList.put(i, edbox);
				layoutParamForcheck.weight = 1;
				layoutParamForTextBox.weight = 1;

				ln.addView(edbox, 0, layoutParamForTextBox);
				edbox.setVisibility(View.INVISIBLE);
				
				checkEdboxHasDataFrmMultipleChoice(edbox, op.qnList.get(i));
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
								edList.get(ln.getId()).setFocusable(true);
								aaa.set(ln.getId(), checkButton.getId());
						} 
								else {
								aaa.set(ln.getId(), 0);
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
		
			sql = "Select "
					+ inColumn
					+ " from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " where dataid='" + CommonStaticClass.dataId + "'";
		
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
		
			sql = "Select "
					+ inColumn
					+ " from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " where dataid='" + CommonStaticClass.dataId + "'";
		
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
					if(pairs.getValue().getText().toString().isEmpty())
					{
						CommonStaticClass.showMyAlert(con, "Alert", "Please Specify Other");
						return;
					}
					else{
						if (pairs.getValue().getText().toString().length() > 0) {
							String sq = "";
							
							
								sq = "UPDATE "
										+ CommonStaticClass.questionMap.get(
												CommonStaticClass.currentSLNo)
												.getTablename() + " SET "
										+ op.qnList.get(pairs.getKey()) + " = '"
										+ pairs.getValue().getText().toString()
										+ "' where dataid='"
										+ CommonStaticClass.dataId + "'";
							
							dbHelper.executeDMLQuery(sq);
						}
					} 
				}
				else
				{
					String sq = "";
					
					
					sq = "UPDATE "
							+ CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo)
									.getTablename() + " SET "
							+ op.qnList.get(pairs.getKey()) + " = null where dataid='"
							+ CommonStaticClass.dataId + "'";
				
					dbHelper.executeDMLQuery(sq);
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
			
				sql += " where dataid='" + CommonStaticClass.dataId + "'";
			

			if (dbHelper.executeDMLQuery(sql)) {

				    CommonStaticClass.findOutNextSLNo(
							qName,
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo)
									.getQnext1());
					CommonStaticClass.nextQuestion(ParentActivity.this);
				
		
			}

		else {
			CommonStaticClass
					.showMyAlert(con, "Please check one!!!",
							"You didn't checked any answer please select one to preceed");
		}
		}

	}

	/*private boolean IfCompletedAllMembersFrmMultipleChoice() {
		boolean IsCompleted = true;
		String sql1 = "Select * from tblLinelist  where dataid='"
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
	}*/

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
			if (!(aaa.get(i) == 0)) {
				return true;
			}
		}

		return false;
	}

	/*// FrmMultipleCombo part
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
	}*/

	/*private void filllAllSpinnerDataFrmMultipleCombo() {
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

	}*/

	/*class spinItemSelectListenerFrmMultipleCombo implements
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

	}*/

	/*private void updateTableDataFrmMultipleCombo() {
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
	}*/

	/*private void CheckNUpdateFrmMultipleCombo() {
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

	}*/

	/*private void AddSkipFrmMultipleCombo() {
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
				SQL = "Update tblLinelist set p1_11av1=-1,p1_11bv1=-1,p1_11cv1=-1,p1_11dv1=-1,p1_11ev1=-1,p1_11fv1=-1,p1_11gv1=-1,p1_11hv1=-1,p1_11iv1=-1,p1_11jv1=-1,p1_11v1other='' Where dataid='"
						+ CommonStaticClass.dataId
						+ "' and memberid="
						+ CommonStaticClass.memberID;
				dbHelper.executeDMLQuery(SQL);

			}
			if (!IsVisited2nd) {
				SQL = "Update tblLinelist set p1_11av2=-1,p1_11bv2=-1,p1_11cv2=-1,p1_11dv2=-1,p1_11ev2=-1,p1_11fv2=-1,p1_11gv2=-1,p1_11hv2=-1,p1_11iv2=-1,p1_11jv2=-1,p1_11v2other='' Where dataid='"
						+ CommonStaticClass.dataId
						+ "' and memberid="
						+ CommonStaticClass.memberID;
				dbHelper.executeDMLQuery(SQL);

			}
			if (!IsVisited3rd) {
				SQL = "Update tblLinelist set p1_11av3=-1,p1_11bv3=-1,p1_11cv3=-1,p1_11dv3=-1,p1_11ev3=-1,p1_11fv3=-1,p1_11gv3=-1,p1_11hv3=-1,p1_11iv3=-1,p1_11jv3=-1,p1_11v3other='' Where dataid='"
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

	}*/

	/*private void UpdatePreviousDataFrmMultipleCombo(String QVar) {
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
	}*/

	/*private void promptUserForInputFrmMultipleCombo(final Spinner spinner,
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
	}*/

	/*private String getOtherDataFrmMultipleCombo(String column) {
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
*/
	/*public void LoadDataFrmMultipleCombo() {
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
	}*/

	/*private void doFillFrmMultipleCombo(Cursor mCursor1) {
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
	}*/

	/*private void resetViewsFrmMultipleCombo() {
		spinner1st.setSelection(0);
		spinner2nd.setSelection(0);
		spinner3rd.setSelection(0);

	}*/

	/*private boolean IsValidFrmMultipleCombo() {
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
	}*/

	/*private void IsInfomationMismatchingFrmMultipleCombo() {
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

	}*/

	/*private void loadAllUiViewsFrmMultipleCombo(ViewGroup v) {
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

	}*/

	// FrmNotes part
	private void loadGuiFrmNotes(ViewGroup v) {
		// TODO Auto-generated method stub

		// checkDbHasPreviousDataForThisHouseHold();

		infoText = (EditText) v.findViewById(R.id.infoText);
		String sql = "";
		
			sql = "Select notes from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " where dataid='" + CommonStaticClass.dataId + "'";
		
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
			
				sql = "UPDATE "
						+ CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getTablename()
						+ " set notes='" + qAns + "' where dataid='"
						+ CommonStaticClass.dataId + "'";
			
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

	
			((RadioButton) findViewById(R.id.radio2))
					.setVisibility(View.VISIBLE);
		
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

				//example code if other is checked
				/*if (qName.equalsIgnoreCase("q3_28")) {
					if (qAns.equalsIgnoreCase("777")) {
						CommonStaticClass.findOutNextSLNo(
								CommonStaticClass.questionMap.get(
										CommonStaticClass.currentSLNo)
										.getQvar(), "q3_28_other");
					}

				} */

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

	/*public Date getDate() {

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

	}*/

	/*private int GetMonth(String month) {
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

	}*/

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

			//check for numeric entries for app maternal health
			public void afterTextChanged(Editable s) {
				

			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}
		});

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
					
					infoText.setText((a.length() > 0
							&& (!a.equalsIgnoreCase("-1")) && (!a
							.equalsIgnoreCase("null"))) ? a : "");

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
		/*if (!IsValidFrmNumeric()) {
			CommonStaticClass.showMyAlert(con, "Not Correct",
					"Data Mismatching");
			return;
		}*/
		
		if (qAns.length() > 0)
		{
		    
			//if you want to validate something do it here
			
			
		}
	    
	    

		if (qAns.length() > 0) {
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
			//set bangla text to the labels of the text field
			if (qName.equalsIgnoreCase("q9")
					|| qName.equalsIgnoreCase("q402")) {
				num1.setText("wbw`Â©Ã³ Lvbv ");
				num2.setText("AbÂ¨vbÂ¨ Lvbv ");
			} 

		} else {
			qqq.setTypeface(null);
			num1.setTypeface(null);
			num2.setTypeface(null);
			qqq.setText(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQdesceng());
			//set english text to the labels of the text field
			if (qName.equalsIgnoreCase("sampleYes")) {
				num1.setText("Identity code of the patient");
				num2.setText("Date of  sample collection");
			} 
		}

		infoText1 = (EditText) v.findViewById(R.id.txtNum1);
		infoText2 = (EditText) v.findViewById(R.id.txtNum2);

		infoText2.setOnTouchListener(new View.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {

				final Calendar c = Calendar.getInstance();
				dateYear = c.get(Calendar.YEAR);
				dateMonth = c.get(Calendar.MONTH);
				dateDay = c.get(Calendar.DAY_OF_MONTH);
				// TODO Auto-generated method stub
				showDialog(DATE_DIALOG);
				return false;
			}
		});

		qName1 = qName + "_1";
		qName2 = qName + "_2";

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

	/*protected boolean checkForNoneFrmNumericTwo(String lineNumber) {
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
	}*/

	/*private void setDataFromFrmNumericTwo(EditText infoText, String qq,
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

	}*/

	/*private void setDataFromFrmNumericTwo(EditText infoText, String q3_4,
			String q2, String table) {
		// TODO Auto-generated method stub
		String sql1 = "Select " + q3_4 + " from " + table + " where dataid='"
				+ CommonStaticClass.dataId + "'";
		String sql2 = "Select " + q2 + " from " + table + " where dataid='"
				+ CommonStaticClass.dataId + "'";
		float value = dataFromFrmNumericTwo(sql1, q3_4)
				- dataFromFrmNumericTwo(sql2, q2);
		infoText.setText(value + "");

	}*/

	// Get specific column value corresponding to SQL Query

	/*private float dataFromFrmNumericTwo(String sql, String columnName) {
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
	}*/

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

	/*// FrmReasoning part
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
*/
	/*private void Load_ControlsFrmReasoning(ViewGroup v) {
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

		String sql = "Select * from tblLinelist where dataid='"
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

	}*/

	/*private void updateTableDataFrmReasoning() {
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

			String SQL = "Update tblLinelist set p1_11av1=" + a1 + ",p1_11bv1="
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

	}*/

	/*private boolean IsValidFrmReasoning() {
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

	}*/

	/*private void Load_DataFrmReasoning() {

		String sql = "Select * from tblLinelist where dataid='"
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

	}*/

	/*private void promptUserForInputFrmReasoning(final CheckBox checkbox,
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
	}*/

	// frmsinglechoice part
	private void Load_UIFrmSingleChoice(final ViewGroup v) {
		// TODO Auto-generated method stub
		code = 0;
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
				code = 0;
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
							.parseInt(a) : 0;
					if (op.codeList.contains(idIfEdit)) {
						dataOk = true;
					}
				}
			} while (mCursor1.moveToNext());
		}
		return dataOk;
	}

	/*private void SaveFamilyInfoFrmSingleChoice() {
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
			sql = "Select tblLinelist.q10,tblLinelist.q12,tblMainQuesSc.q13 from  tblLinelist inner join tblMainQuesSc on tblLinelist.dataid=tblMainQuesSc.dataid Where  tblLinelist.dataid='"
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
				sql = "update  tblLinelist set q15=q15+1 Where dataid='"
						+ CommonStaticClass.dataId.substring(0, 5) + "00'";
				if (dbHelper.executeDMLQuery(sql)) {

				}
			}
		}
	}*/

	private void updateTableDataFrmSingleChoice() {
		// TODO Auto-generated method stub
		// CommonStaticClass.findskiplistfromDB("q2_1c", "q2_1c", dbHelper);
		String sql = "";
		String qtoGo = "";
		qName = CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar();
		if (nextToGo == null) {
			nextToGo = "";
		}

		nextToGo = nextToGo.length() > 0 ? nextToGo
				: CommonStaticClass.questionMap.get(
						CommonStaticClass.currentSLNo).getQnext1();

		try {

			if (code != -1) {
				
					sql = "UPDATE "
							+ CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo)
									.getTablename()
							+ " SET "
							+ CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQvar()
							+ "='" + code + "' where dataid='"
							+ CommonStaticClass.dataId + "'";
				


				if (dbHelper.executeDMLQuery(sql)) {

					if (nextToGo.equalsIgnoreCase("END")) {
						Message msg = new Message();
						msg.what = UPDATEDONE;
						handler.sendMessage(msg);
					/*	if (IfCompletedAllMembersFrmSingleChoice())
							showUserFinishDialogFrmSingleChoice();
						else {*/
						CommonStaticClass.findOutNextSLNo(qName, "END");
						CommonStaticClass.nextQuestion(ParentActivity.this);
//						}
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

	/*private void updateq7() {

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

	}*/

/*	private void Load_DataFrmSingleChoice() {

	}*/

	/*private boolean IfCompletedAllMembersFrmSingleChoice() {
		boolean IsCompleted = true;
		String sql1 = "Select * from tblLinelist  where dataid='"
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
	}*/

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

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// retrieve result of scanning - instantiate ZXing object
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(
				requestCode, resultCode, data);
		if(requestCode == ACTION_TAKE_PHOTO_S)
		{
			if (resultCode == RESULT_OK) {
				handleSmallCameraPhoto(data);
			}
		}
		// check we have a valid result
		else if (scanningResult != null) {
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

			
			String sql = "";
			
				sql = "UPDATE "
						+ CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getTablename()
						+ " SET " + qName + "='" + qAns + "' where dataid='"
						+ CommonStaticClass.dataId + "'";
			
			if (dbHelper.executeDMLQuery(sql)) {
				
					
					CommonStaticClass.findOutNextSLNo(
							qName,
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQnext1());
					CommonStaticClass.nextQuestion(ParentActivity.this);
				
			}
		}
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

			case HBIS_DATE_DIALOG_ID:
				return new DatePickerDialog(this, HBISdateListener, dateYear,
						dateMonth, dateDay);


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
			pickTime.setText( (((TimeHour<10)? "0" :"")+ TimeHour) + ":" + 
            		(((minute<10)? "0" : "") + minute));
		
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

		
		String sql = "";
		
			sql = "Select * from "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " where dataid='" + CommonStaticClass.dataId + "'";
		
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

		
	}

	

	private void updateTableDataFrmYearToMin() {
		Cursor mCursor = null;
		// TODO Auto-generated method stub
		//little bit modified for maternal healt app
		if (yearHolder.getVisibility() == View.VISIBLE) {
			if (!(yearBox.getText().toString().length() > 0) 
					|| ((Integer.parseInt(yearBox.getText().toString())> 2015)
					&& Integer.parseInt(yearBox.getText().toString())!=9998)
					)
					 {
				CommonStaticClass.showMyAlert(con, "Error",
						"You must provide correct data to proceed");
				return;
			}
		}
		if (monthHolder.getVisibility() == View.VISIBLE) {
			if (!(monthBox.getText().toString().length() > 0)
					|| (Integer.parseInt(monthBox.getText().toString()) !=98
					&& Integer.parseInt(monthBox.getText().toString())>12)) {
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
			if (!(dayBox.getText().toString().length() > 0)
					|| (Integer.parseInt(dayBox.getText().toString())!=98
					&& Integer.parseInt(dayBox.getText().toString())>31)) {
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

		String sql = "";

		sql = "UPDATE "
				+ CommonStaticClass.questionMap.get(
						CommonStaticClass.currentSLNo).getTablename() + " SET ";

		String yearColumn = CommonStaticClass.questionMap.get(
				CommonStaticClass.currentSLNo).getQvar()
				+ "years";

		String monthColumn = CommonStaticClass.questionMap.get(
				CommonStaticClass.currentSLNo).getQvar()
				+ "months";

		String weekColumn = CommonStaticClass.questionMap.get(
				CommonStaticClass.currentSLNo).getQvar()
				+ "weeks";

		String dayColumn = CommonStaticClass.questionMap.get(
				CommonStaticClass.currentSLNo).getQvar()
				+ "days";
		
		String hourColumn = CommonStaticClass.questionMap.get(
				CommonStaticClass.currentSLNo).getQvar()
				+ "hours";
		String minColumn = CommonStaticClass.questionMap.get(
				CommonStaticClass.currentSLNo).getQvar()
				+ "mins";

		int i = 0;
		// +CommonStaticClass.questionMap.get(CommonStaticClass.currentSLNo).getQvar()+"='"+code+"' where dataid='"+CommonStaticClass.dataId+"'";
		if (yearHolder.getVisibility() == View.VISIBLE) {
			year = yearBox.getText().toString();
			sql += yearColumn + " = '" + year + "'";
			i++;
		}
		if (monthHolder.getVisibility() == View.VISIBLE) {
			month = monthBox.getText().toString();
			
			sql += (i > 0 ? "," : "")

			+ monthColumn + " = '" + month + "'";
			i++;
		}
		if (weekHolder.getVisibility() == View.VISIBLE) {
			week = weekBox.getText().toString();
			sql += (i > 0 ? "," : "")
					
					+ weekColumn + " = '" + week + "'";
			i++;
		}
		if (dayHolder.getVisibility() == View.VISIBLE) {
			day = dayBox.getText().toString();
			sql += (i > 0 ? "," : "")
					
					+ dayColumn + " = '" + day + "'";
			i++;
		}
		if (hourHolder.getVisibility() == View.VISIBLE) {
			hour = hourBox.getText().toString();
			sql += (i > 0 ? "," : "")
					
					+ hourColumn + " = '" + hour + "'";
			i++;
		}
		if (minHolder.getVisibility() == View.VISIBLE) {
			min = minBox.getText().toString();
			sql += (i > 0 ? "," : "")
					
					+ minColumn + " = '" + min + "'";
			i++;
		}
		sql += "where dataid='" + CommonStaticClass.dataId + "'";

		if (dbHelper.executeDMLQuery(sql)) {

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

	//frmmultiplecheckcombotwo
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
		if (qName.equalsIgnoreCase("q14")) {// put
																				// the
																				// name
																				// of
																				// question
																				// from
																				// qvar
			adjustForEdit = true;

		}
		if (qName.equalsIgnoreCase("q3_4")) { // put the name of question from
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
			;
			qqq.setText(CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0 ? CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng()
					: CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQdesceng());

		} else {
			qqq.setText(CommonStaticClass.questionMap.get(
					CommonStaticClass.currentSLNo).getQdesceng());
		}

		checkBoxHolder = (LinearLayout) v.findViewById(R.id.checkBoxHolder);
		checkBoxHolder.removeAllViews();
		op = CommonStaticClass.findOptionsForThisQuestion(dbHelper,
				CommonStaticClass.questionMap
						.get(CommonStaticClass.currentSLNo).getQvar());

		LinearLayout.LayoutParams lnlParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT, 50);

		/*
		 * LinearLayout.LayoutParams lnlParams = new LinearLayout.LayoutParams(
		 * LinearLayout.LayoutParams.MATCH_PARENT,
		 * LinearLayout.LayoutParams.MATCH_PARENT);
		 */

		LinearLayout.LayoutParams layoutParamForcheck = new LinearLayout.LayoutParams(
				(dm.widthPixels / 4) * 2,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams layoutParamForSpin1 = new LinearLayout.LayoutParams(
				(dm.widthPixels / 4), LinearLayout.LayoutParams.WRAP_CONTENT);

		/*
		 * LinearLayout.LayoutParams layoutParamForSpin2 = new
		 * LinearLayout.LayoutParams( (dm.widthPixels / 4),
		 * LinearLayout.LayoutParams.WRAP_CONTENT);
		 */

		LinearLayout.LayoutParams layoutParamForEditOrSpinner = new LinearLayout.LayoutParams(
				(dm.widthPixels / 4), LinearLayout.LayoutParams.WRAP_CONTENT);

		optionList1.add("");
		optionCodeList1.add(0);

		optionList2.add("");
		optionCodeList2.add(0);

		optionList3.add("");
		optionCodeList3.add(0);

		for (int i = 0; i < op.codeList.size(); i++) {

			if (op.qidList.get(i).contains("_Options")
					|| op.qidList.get(i).contains("_Option")
					|| op.qidList.get(i).contains("_options")
					|| op.qidList.get(i).contains("_option")
					) {
				Log.e("op.qidList.get(i)", op.qidList.get(i));
				if (CommonStaticClass.langBng) {
					optionList1.add(op.capBngList.get(i));
				} else {
					optionList1.add(op.capEngList.get(i));
				}
				optionCodeList1.add(op.codeList.get(i));
				continue;
			} 

			aaa1.add(0);
			aaa2.add(0);
			aaa3.add(0);
			aaachecklist.add(0);
			if (adjustForEdit)
				mEditStrings.add("");
		}

		for (int i = op.codeList.size() - 1; i >=0 ; i--) {

			Log.e("op.qidList.get(i)", op.qidList.get(i));
			if (op.qidList.get(i).contains("_Options")
					|| op.qidList.get(i).contains("_Option")
					|| op.qidList.get(i).contains("_options")
					|| op.qidList.get(i).contains("_option")
					) {
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
			
			final EditText editforwater = new EditText(this);
			editforwater.setTag(i);
			layoutParamForSpin1.weight = 1;
			if (adjustForEdit) {
				layoutParamForEditOrSpinner.weight = 1;
			}
			if (adjustForSpinner) {
				layoutParamForEditOrSpinner.weight = 1;
			}
			
			if (adjustForEdit) {
				//make the text box either date, time or numeric type
				//date
				if(qName.equalsIgnoreCase("q14"))
				{
					editforwater.setFocusable(false);
					
					editforwater.setOnClickListener(new OnClickListener() {
						
						public void onClick(View v) {
							// TODO Auto-generated method stub
							final Calendar c = Calendar.getInstance();
				            mYear = c.get(Calendar.YEAR);
				            mMonth = c.get(Calendar.MONTH);
				            mDay = c.get(Calendar.DAY_OF_MONTH);

				            // Launch Date Picker Dialog
				            DatePickerDialog dpd = new DatePickerDialog(ParentActivity.this,
				                    new DatePickerDialog.OnDateSetListener() {

				                        public void onDateSet(DatePicker view, int year,
				                                int monthOfYear, int dayOfMonth) {
				                            // Display Selected date in textbox
				                        	editforwater.setText((((dayOfMonth < 10) ? "0" : "") + dayOfMonth) + "/"
				                                    + ((((monthOfYear+1) < 10) ? "0" : "") + (monthOfYear+1)) + "/" + year);
				                        	

				                        }
				                        
				                    }, mYear, mMonth, mDay);
				            
				            	dpd.setTitle("Select Date");
				            	dpd.show();
				          
							
						}
					});
				}
				//time
				else if(qName.equalsIgnoreCase(""))
				{
					editforwater.setFocusable(false);
					
					
					editforwater.setOnClickListener(new OnClickListener() {
						
						public void onClick(View v) {
							// TODO Auto-generated method stub
							

				            // Launch time Picker Dialog
							Calendar c = Calendar.getInstance();
				            int hour = c.get(Calendar.HOUR_OF_DAY);
				            int minute = c.get(Calendar.MINUTE);
				            TimePickerDialog mTimePicker;
				            mTimePicker = new TimePickerDialog(ParentActivity.this, new TimePickerDialog.OnTimeSetListener() {
				                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
				                	editforwater.setText( (((selectedHour<10)? "0" :"")+ selectedHour) + ":" + 
				                    		(((selectedMinute<10)? "0" : "") + selectedMinute));
				                }
				            }, hour, minute, true);//Yes 24 hour time
				            mTimePicker.setTitle("Select Time");
				            mTimePicker.show();
				        
				       
				          
							
						}
					});
				}
				//number
				else 
				editforwater.setRawInputType(InputType.TYPE_CLASS_NUMBER);
				
				
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
			editforwater.setVisibility(View.GONE);
			spinner1.setAdapter(adapter1);
			spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {

				public void onItemSelected(AdapterView<?> parent, View view,
						int pos, long id) {
					// TODO Auto-generated method stub
					if (optionCodeList1.size() > pos)
						aaa1.set(checkButton.getId(), optionCodeList1.get(pos));

					if (pos == 1) {
						editforwater.setVisibility(View.VISIBLE);
					} else {
						editforwater.setText("");
						editforwater.setVisibility(View.GONE);
					}
				}

				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					// aaa.set(checkButton.getId(), -1);
				}

			});

			
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
			editforwater.setVisibility(View.GONE);
			checkButton
					.setOnCheckedChangeListener(new OnCheckedChangeListener() {

						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							// TODO Auto-generated method stub
							if (isChecked) {
								Log.e("id :", "" + checkButton.getId());
								aaachecklist.set(checkButton.getId(), 1);
								editforwater.setVisibility(View.GONE);
								// allchecked = allchecked+1;
								spinner1.setVisibility(View.VISIBLE);
								// spinner2.setVisibility(View.VISIBLE);
								if (adjustForSpinner) {
									// spinner3.setVisibility(View.VISIBLE);
								}
								if (adjustForEdit) {
									// editforwater.setVisibility(View.VISIBLE);
									mEditStrings.set(
											(Integer) editforwater.getTag(),
											"::");
								}

							} else {
								aaachecklist.set(checkButton.getId(), 0);
								spinner1.setVisibility(View.INVISIBLE);
								editforwater.setVisibility(View.INVISIBLE);
								// spinner2.setVisibility(View.INVISIBLE);
								if (adjustForSpinner) {
									// spinner3.setVisibility(View.INVISIBLE);
								}
								if (adjustForEdit) {
									// editforwater.setVisibility(View.INVISIBLE);
									mEditStrings.set(
											(Integer) editforwater.getTag(),
											"");
								}
							}
						}
					});

			spinner1.setVisibility(View.INVISIBLE);
			// spinner2.setVisibility(View.INVISIBLE);
			if (adjustForSpinner) {
				// spinner3.setVisibility(View.INVISIBLE);
			}
			if (adjustForEdit) {
				// editforwater.setVisibility(View.INVISIBLE);
			}
			if (adjustForEdit) {
				selectCheckAndCombo(op.qidList.get(i), checkButton, spinner1,
						editforwater);
			} else {
				if (adjustForSpinner) {
					selectCheckAndCombo(op.qidList.get(i), checkButton,
							spinner1);
				} else {
					selectCheckAndCombo(op.qidList.get(i), checkButton,
							spinner1);
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

		CheckBoxNotSeletedFrmMultipleCheckCombotwo();
		if (spinnerOK == false) {
			CommonStaticClass.showMyAlert(con, "Please Select",
					"Please select all checkbox to proceed");
			return;
		}

		if (spinnerOK) {
		

			String sql = "UPDATE "
					+ CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()
					+ " SET ";
			for (int i = 0; i < op.codeList.size(); i++) {
				if (op.qidList.get(i).contains("_Options")
						|| op.qidList.get(i).contains("_Option")
						|| op.qidList.get(i).contains("_options")
						|| op.qidList.get(i).contains("_option")) {
					continue;
				}
				if (op.qidList.get(i + 1).contains("_Options")
						|| op.qidList.get(i + 1).contains("_Option")
						|| op.qidList.get(i + 1).contains("_options")
						|| op.qidList.get(i + 1).contains("_option")) {
					if (adjustEdit) {
						sql += op.qidList.get(i) + " = '"
								+ (aaachecklist.get(i) == 0 ? 0 : 1) + "',";
						sql += op.qidList.get(i)
								+ "_1 = '"
								+ ((aaachecklist.get(i) == 0 || aaachecklist
										.get(i) == 0) ? 0 : aaa1.get(i))
								+ "',";
						sql += op.qidList.get(i) + "_2 = '"
								+ mEditStrings.get(i) + "',";
						
						
					} else {
						if (adjustSpin) {
							sql += op.qidList.get(i) + " = '"
									+ (aaachecklist.get(i) == 0 ? 0 : 1)
									+ "',";
							sql += op.qidList.get(i)
									+ "_1 = '"
									+ ((aaachecklist.get(i) == 0 || aaachecklist
											.get(i) == 0) ? 0 : aaa1.get(i))
									+ "'";
							sql += op.qidList.get(i)
									+ "_2 = '"
									+ ((aaachecklist.get(i) == 0 || aaachecklist
											.get(i) == 0) ? 0 : aaa2.get(i))
									+ "',";
							
							
							/*
							 * sql += op.qidList.get(i) + "_4 = '" +
							 * ((aaachecklist.get(i) == -1 || aaachecklist
							 * .get(i) == 0) ? "" : aaa3 .get(i)) + "' ";
							 */
						} else {
							sql += op.qidList.get(i) + " = '"
									+ (aaachecklist.get(i) == 0 ? 0 : 1)
									+ "',";
							sql += op.qidList.get(i)
									+ "_1 = '"
									+ ((aaachecklist.get(i) == 0 || aaachecklist
											.get(i) == 0) ? 0 : aaa2.get(i))
									+ "' ";
							sql += op.qidList.get(i)
									+ "_2 = '"
									+ ((aaachecklist.get(i) == 0 || aaachecklist
											.get(i) == 0) ? 0 : aaa1.get(i))
									+ "',";
							
						}
					}

					break;
				}
				if (adjustEdit) {
					sql += op.qidList.get(i) + " = '"
							+ (aaachecklist.get(i) == 0 ? 0 : 1) + "',";
					sql += op.qidList.get(i)
							+ "_1 = '"
							+ ((aaachecklist.get(i) == 0 || aaachecklist
									.get(i) == 0) ? 0 : aaa1.get(i)) + "',";
					sql += op.qidList.get(i) + "_2 = '" + mEditStrings.get(i)
							+ "',";
					
					/*
					 * sql += op.qidList.get(i) + "_4 = '" +
					 * ((aaachecklist.get(i) == -1 || aaachecklist .get(i) == 0)
					 * ? "" : aaa2.get(i)) + "',";
					 */
				} else {
					if (adjustSpin) {
						sql += op.qidList.get(i) + " = '"
								+ (aaachecklist.get(i) == 0 ? 0 : 1) + "',";
						sql += op.qidList.get(i)
								+ "_1 = '"
								+ ((aaachecklist.get(i) == 0 || aaachecklist
										.get(i) == 0) ? 0 : aaa2.get(i))
								+ "',";
						sql += op.qidList.get(i)
								+ "_2 = '"
								+ ((aaachecklist.get(i) == 0 || aaachecklist
										.get(i) == 0) ? 0 : aaa1.get(i))
								+ "',";
						
						/*
						 * sql += op.qidList.get(i) + "_4 = '" +
						 * ((aaachecklist.get(i) == -1 || aaachecklist .get(i)
						 * == 0) ? "" : aaa3.get(i)) + "',";
						 */
					} else {
						sql += op.qidList.get(i) + " = '"
								+ (aaachecklist.get(i) == 0 ? 0 : 1) + "',";
						sql += op.qidList.get(i)
								+ "_1 = '"
								+ ((aaachecklist.get(i) == 0 || aaachecklist
										.get(i) == 0) ? 0 : aaa2.get(i))
								+ "',";
						sql += op.qidList.get(i)
								+ "_2 = '"
								+ ((aaachecklist.get(i) == 0 || aaachecklist
										.get(i) == 0) ? 0 : aaa1.get(i))
								+ "',";
						
					}

				}
			}
			sql = sql.subSequence(0, sql.length() - 1).toString();

			sql += " where dataid='" + CommonStaticClass.dataId + "'";

			if (dbHelper.executeDMLQuery(sql)) {
				CommonStaticClass.findOutNextSLNo(
						CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQvar(),
						CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getQnext1());
				CommonStaticClass.nextQuestion(ParentActivity.this);
			}
		} else {
			CommonStaticClass.showMyAlert(con, "Please check one!!!",
					"You didn't check any answer please select one to preceed");
		}
		// }
		/*
		 * else { CommonStaticClass .showMyAlert(con, "Please select item!!!",
		 * "You didn't select any item from list, Please select one to proceed"
		 * ); }
		 */

	}
	
	private void selectCheckAndCombo(String inColumn, CheckBox checkButton,
			Spinner spinner1, EditText ed) {
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
			boolean a = false;
			if (mCursor1.getCount() > 0) {
				doFillpos(mCursor1, inColumn, checkButton, spinner1, ed);

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
			Spinner spinner1) {
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
			boolean a = false;
			if (mCursor1.getCount() > 0) {
				doFillpos(mCursor1, inColumn, checkButton, spinner1);

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
			CheckBox checkButton, Spinner spinner1, EditText ed) {
		boolean dataOk = false;
		String column1 = columnPrefix ;
		String column2 = columnPrefix + "_1";
		String column3 = columnPrefix + "_2";
		/* String column4 = columnPrefix + "_4"; */
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
						if (c != null && c.length() > 0
								&& !c.equalsIgnoreCase("")) {
							ed.setText(c);
						}
						
						/*
						 * if (optionCodeList2.contains(Integer.parseInt(d))) {
						 * int pos = optionCodeList2.indexOf(Integer
						 * .parseInt(d));
						 * 
						 * spinner2.setSelection(pos); dataOk = true; }
						 */

					} catch (Exception e) {
						// TODO: handle exception
					}

				}

			} while (mCursor1.moveToNext());
		}

		return dataOk;
	}
	
	private void CheckBoxNotSeletedFrmMultipleCheckCombotwo() {
		// if (qName.equalsIgnoreCase("q612") || qName.equalsIgnoreCase("q611"))
		// {
		for (int i = 0; i < aaa1.size(); i++) {
			if (aaa1.get(i) != 0) {
				spinnerOK = true;
				return;
			} else {
				spinnerOK = false;
			}
		}
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
		String column1 = columnPrefix ;
		String column2 = columnPrefix + "_1";
		String column3 = columnPrefix + "_2";
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


	//Frrmultiplechoicetext
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

	private void updateTableDataFrmMultipleChoiceText(ViewGroup v) {

		/*
		 * if (!IsValidEntry(v)) { DisplayToast(thisactivity,
		 * "Please fill all fields", 1); return; }
		 */

		String sql = String.format("");
		String DgOthers = "";
		String DgCode = ((Spinner) v.findViewById(R.id.sp1))
				.getSelectedItem()
				.toString()
				.substring(
						0,
						((Spinner) v.findViewById(R.id.sp1))
								.getSelectedItem().toString()
								.lastIndexOf(":")).trim();

		if (DgCode.equalsIgnoreCase("99")) {
			DgOthers = ((EditText) v.findViewById(R.id.et1)).getText()
					.toString();
		} else {
			DgOthers = null;
		}

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
					.format("Update %s Set Dept = '%s',  HosName='%s', RegNo= '%s', WardNo='%s', BedNo= '%s' , DgCode= '%s',DgOthers='%s', EditBy='%s', EditDate='%s' WHERE dataid = '%s'",
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
							((EditText) v.findViewById(R.id.txtRegno))
									.getText().toString(),
							((EditText) v.findViewById(R.id.txtWardno))
									.getText().toString(),
							((EditText) v.findViewById(R.id.txtBedno))
									.getText().toString()
							, DgCode, DgOthers,
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

	private void loadDataMultipleChoiceText(ViewGroup v) {
		final ViewGroup vg = v;
		Cursor cur = null;
		try {
			// SelectQueryBuilder();


			/*String dist = "SELECT '' AS DgCode UNION SELECT (" + "" + "DgCode" + ""
					+ "|| " + "" + "' : '" + " || " + "Name" + ") AS " + "D"
					+ " from frmrDiagnosisCode ORDER BY DgCode" + "";*/
			String dist = "SELECT '' AS DgCode UNION SELECT (" + "" + "DgCode" + ""
					+ "|| " + "" + "' : '" + " || " + "Name" + ") AS " + "D"
					+ " from DiagnosisCode ORDER BY DgCode" + "";

			CommonStaticClass.FillCombo(thisactivity, dbHelper, dist,
					((Spinner) v.findViewById(R.id.sp1)));

			((Spinner) v.findViewById(R.id.sp1))
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
									if (sResCode.equalsIgnoreCase("99")) {
										((EditText) vg.findViewById(R.id.et1))
												.setVisibility(view.VISIBLE);

									} else {
										((EditText) vg.findViewById(R.id.et1))
												.setVisibility(view.INVISIBLE);
									}
								}

							}

						}


						public void onNothingSelected(AdapterView<?> arg0) {
							// TODO Auto-generated method stub

						}
					});

			String strSQL = "Select HosName, Dept, RegNo,WardNo, BedNo,DgCode, DgOthers, EntryDate, EntryTime from "+
					CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getTablename()+" where dataid = '"
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
							String RegNo = cur.getString(cur
									.getColumnIndex("RegNo"));
							String WardNo = cur.getString(cur
									.getColumnIndex("WardNo"));
							String BedNo = cur.getString(cur
									.getColumnIndex("BedNo"));
							String ProvDiag = cur.getString(cur
									.getColumnIndex("DgCode"));
							String DiagOthers = cur.getString(cur
									.getColumnIndex("DgOthers"));
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

							/*ids = new ArrayList<String>();
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
									} *//*
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
									 *//*

								}
								*//*
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
								 *//*
							}*/
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

							if (RegNo != null) {

								((EditText) v.findViewById(R.id.txtRegno))
										.setText(RegNo);

							}

							if (WardNo != null) {

								((EditText) v.findViewById(R.id.txtWardno))
										.setText(WardNo);

							}
							if (BedNo != null) {

								((EditText) v.findViewById(R.id.txtBedno))
										.setText(BedNo);

							}
							if (ProvDiag == null) {

//								((EditText) v.findViewById(R.id.txtProDiag))
//										.setText(ProvDiag);
								((TextView) v.findViewById(R.id.lblother))
										.setVisibility(View.INVISIBLE);
								((EditText) v.findViewById(R.id.et1))
										.setVisibility(View.INVISIBLE);

							}else if (ProvDiag.equalsIgnoreCase("99")) {

							/*
							 * ((RadioButton) ((RadioGroup) v
							 * .findViewById(R.id.radioGroup1))
							 * .findViewById(R.id.radio0)) .setChecked(true);
							 */

								for (int i = 0; i < ((Spinner) v
										.findViewById(R.id.sp1)).getCount(); i++) {

									if (((Spinner) v.findViewById(R.id.sp1))
											.getItemAtPosition(i).toString()
											.contains(ProvDiag)) {

										((Spinner) v.findViewById(R.id.sp1))
												.setSelection(i);

										((EditText) v.findViewById(R.id.et1))
												.setVisibility(View.VISIBLE);
										((EditText) v.findViewById(R.id.et1))
												.setText(DiagOthers);
										((TextView) v.findViewById(R.id.lblother))
												.setVisibility(View.VISIBLE);
									}
								}

							} else {
								for (int i = 0; i < ((Spinner) v
										.findViewById(R.id.sp1)).getCount(); i++) {

									if (((Spinner) v.findViewById(R.id.sp1))
											.getItemAtPosition(i).toString()
											.contains(ProvDiag)) {
										((Spinner) v.findViewById(R.id.sp1))
												.setSelection(i);

										((EditText) v.findViewById(R.id.et1))
												.setVisibility(View.INVISIBLE);
										((EditText) v.findViewById(R.id.et1))
												.setText(DiagOthers);
										((TextView) v.findViewById(R.id.lblother))
												.setVisibility(View.INVISIBLE);
										break;
									}
								}
							}

							/*ids = new ArrayList<String>();

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

									*//*
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
									 *//*

								}*//*
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
								 *//*
							}*//*
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

							/*if (CommonStaticClass.getSkip("Comp",
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
							}*/

							/*if ((CommonStaticClass.userSpecificId
									.equalsIgnoreCase("03")
									|| CommonStaticClass.userSpecificId
									.equalsIgnoreCase("05")
									|| CommonStaticClass.userSpecificId
									.equalsIgnoreCase("08") || CommonStaticClass.userSpecificId
									.equalsIgnoreCase("12"))
									&& (CommonStaticClass.getSkip("Comp",
									"tblMainques", dbHelper)
									.equalsIgnoreCase("3"))) {

								*//*
								 * ((Spinner) v.findViewById(R.id.spsur))
								 * .setSelection(0);
								 *//*

							}*/


								/*((Spinner) v.findViewById(R.id.spsur))
										.setEnabled(true);
								((Spinner) v.findViewById(R.id.spsur))
										.setSelection(1);


							((Spinner) v.findViewById(R.id.spsur))
									.setOnItemSelectedListener(new OnItemSelectedListener() {


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


										public void onNothingSelected(
												AdapterView<?> arg0) {
											// TODO Auto-generated method
											// stub

										}
									});*/

							// ((EditText)v.findViewById(R.id.txtSEIBID)).setEnabled(false);

						} catch (Exception e) {
							// TODO: handle exception
							Log.e("error", e.getMessage());
						}
						finally {
							if(cur != null)
								cur.close();
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

	private boolean IsValidEntry(ViewGroup v) {

		ViewGroup viewGroup = (LinearLayout) v.findViewById(R.id.ln);
		int nrOfChildren = viewGroup.getChildCount();
		boolean alltrue = true;
		for (int i = 0; i < nrOfChildren; i++) {
			View view = viewGroup.getChildAt(i);

			if (view instanceof EditText) {
				if (!(((EditText) view).getText().toString().length() > 0)) {

					if (view.getVisibility() == View.GONE || view.getVisibility() == View.INVISIBLE) {
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
	// display customized Toast message
	public static int SHORT_TOAST = 0;
	public static int LONG_TOAST = 1;
	public static void DisplayToast(Context caller, String toastMsg, int toastType){

		try {// try-catch to avoid stupid app crashes
			LayoutInflater inflater = LayoutInflater.from(caller);

			View mainLayout = inflater.inflate(R.layout.toast_layout, null);
			View rootLayout = mainLayout.findViewById(R.id.toast_layout_root);

			ImageView image = (ImageView) mainLayout.findViewById(R.id.image);
			image.setImageResource(R.drawable.notification);
			TextView text = (TextView) mainLayout.findViewById(R.id.text);
			text.setText(toastMsg);

			Toast toast = new Toast(caller);
			//toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			toast.setGravity(Gravity.TOP, 0, 0);
			if (toastType==SHORT_TOAST)//(isShort)
				toast.setDuration(Toast.LENGTH_SHORT);
			else
				toast.setDuration(Toast.LENGTH_LONG);
			toast.setView(rootLayout);
			toast.show();
		}
		catch(Exception ex) {// to avoid stupid app crashes

		}
	}
	//frmpersonrelation form
	private void loadguifrmpersonrelation(ViewGroup v) {


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

	private void updateTableDataFrmPersonRelation(ViewGroup vg) {

		try {

			String AdmDate = ((EditText) vg.findViewById(R.id.admissiondate))
					.getText().toString();

			String DisDate = "";
			String DgCode = "";
			String DgOthers = "";
			String OutCome = "";

		/*	if (((CheckBox) vg.findViewById(R.id.checkBox1)).isChecked()) {*/
				DisDate = ((EditText) vg.findViewById(R.id.dischargedate))
						.getText().toString();

			/*} else {
				DisDate = "";
			}*/

			/*DgCode = ((Spinner) vg.findViewById(R.id.sp1))
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
			}*/

			OutCome = ((Spinner) vg.findViewById(R.id.sp2))
					.getSelectedItem()
					.toString()
					.substring(
							0,
							((Spinner) vg.findViewById(R.id.sp2))
									.getSelectedItem().toString()
									.lastIndexOf(":"));

			String sql = String.format(
					"UPDATE tblLinelist set AdmDate = '%s', DisDate='%s',"
							+ " OutCome='%s', EditBy='%s', EditDate='%s'"
							+ " WHERE dataid='%s'",

					/*CommonStaticClass
							.GetTableName(CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQvar()),*/
					AdmDate, DisDate, OutCome,
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

	protected boolean validatePersonRelation(ViewGroup v) {

		boolean validated = true;

		if (!Validate.hasText(((EditText) v.findViewById(R.id.admissiondate))))
			validated = false;
		/*if (!Validate.hasText(((Spinner) v.findViewById(R.id.sp1))))
			validated = false;*/
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

		String edate = CommonStaticClass.getSkip("EntryDate", "tblLinelist",
				dbHelper);
		// String[] splitentrydate = edate.split("/");

		String AdmDate = ((EditText) v.findViewById(R.id.admissiondate))
				.getText().toString();

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
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

		String dateInString =((EditText) v.findViewById(R.id.admissiondate)).getText().toString();
		String dateOutString =((EditText) v.findViewById(R.id.dischargedate)).getText().toString();
				//d[0] + "-" + d[1] + "-" + d[0];

		Date date = null, denddate = null;
		if (dateInString.length() <= 0) {
			CommonStaticClass.showMyAlert(thisactivity, "Error",
					"Please input date");
			return false;
		}

		if(dateOutString.length() > 0)
		{

			try {

				date = formatter.parse(dateInString);
				denddate = formatter.parse(dateOutString);


			Calendar startdate = Calendar.getInstance();
			Calendar enddate = Calendar.getInstance();

			startdate.setTime(date);
            enddate.setTime(denddate);


			int x = CommonStaticClass.DayDifferenceBackwardWithMonth(startdate,
					enddate);

			if(x<0)
			{
				CommonStaticClass.showMyAlert(thisactivity, "Error",
						"Discharge Date Should not be previous date of Admission Date");
				return false;
			}

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}


		/*if (x > 8) {
			CommonStaticClass.showMyAlert(thisactivity, "Error",
					"Fever Date can not be more than 7");
			return false;

		}*/

		/*if (x == 1) {

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
*/
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

		//dischargeDate.setVisibility(View.INVISIBLE);

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

		/*String dist = "SELECT '' AS DgCode UNION SELECT (" + "" + "DgCode" + ""
				+ "|| " + "" + "' : '" + " || " + "Name" + ") AS " + "D"
				+ " from DiagnosisCode ORDER BY DgCode" + "";

		CommonStaticClass.FillCombo(thisactivity, dbHelper, dist,
				((Spinner) v.findViewById(R.id.sp1)));*/

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

		/*((Spinner) v.findViewById(R.id.sp1))
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


					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});*/

		/*((CheckBox) v.findViewById(R.id.checkBox1))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {


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
				});*/

		ArrayList<String> ids = new ArrayList<String>();
		ids.add("1 : Fully recovered");
		ids.add("2 : Partially recovered");
		ids.add("3 : Remains hospitalized");
		ids.add("4 : Transferred");
		ids.add("5 : Death");
		ids.add("6 : DOR");
		ids.add("7 : DORB");
		ids.add("8 : Abscond");
		ids.add("9 : Unknown");

		CommonStaticClass.FillCombo(thisactivity, ids,
				((Spinner) v.findViewById(R.id.sp2)));

		((Spinner) v.findViewById(R.id.sp2)).setSelection(2);

		/*((Spinner) v.findViewById(R.id.sp2)).setVisibility(View.INVISIBLE);
		((TextView) v.findViewById(R.id.lblsp2)).setVisibility(View.INVISIBLE);*/
		String sql = String
				.format("Select AdmDate, DisDate, OutCome from tblLinelist where dataid = '%s'",
						CommonStaticClass.dataId);

		Cursor cur = null;

		try {

			cur = dbHelper.getQueryCursor(sql);

			if (cur.getCount() > 0) {

				if (cur.moveToFirst()) {

					do {
						// District
						/*String intComp = CommonStaticClass.getSkip("Comp",
								"tblLinelist", dbHelper);

						if (intComp.equalsIgnoreCase("1")
								|| intComp.equalsIgnoreCase("3")) {

							if (cur.getString(0) == null) // if
							// (DR["AdmDate"].ToString()
							// ==
							// "")
							{
								*//*
								 * ((EditText)
								 * v.findViewById(R.id.admissiondate))
								 * .setText(CommonStaticClass.GetDate());
								 *//*
							} else {*/
								((EditText) v.findViewById(R.id.admissiondate))
										.setText(cur.getString(0));
//							}
//						}

						// if (DR["DisDate"].ToString() == "")
						// {
						// dtpDischDate.Value = System.DateTime.Today;
						// }
						// else
						// {
						// dtpDischDate.Value =
						// Convert.ToDateTime(DR["AdmDate"].ToString());
						// }

						/*if (cur.getString(1) == null) // if
						// (DR["AdmDate"].ToString()
						// == "")
						{*/
							((EditText) v.findViewById(R.id.dischargedate))
									.setText(cur.getString(1));
						/*} else {
							((CheckBox) v.findViewById(R.id.checkBox1))
									.setChecked(true);
							((EditText) v.findViewById(R.id.dischargedate))
									.setText(cur.getString(1));

						}*/

						/*if (cur.getString(2) == null) {
							*//*
							 * ((RadioGroup) v.findViewById(R.id.radioGroup1))
							 * .clearCheck();
							 *//*
							((TextView) v.findViewById(R.id.lblother))
									.setVisibility(View.INVISIBLE);
							((EditText) v.findViewById(R.id.et1))
									.setVisibility(View.INVISIBLE);
						} else if (cur.getString(2).equalsIgnoreCase("99")) {

							*//*
							 * ((RadioButton) ((RadioGroup) v
							 * .findViewById(R.id.radioGroup1))
							 * .findViewById(R.id.radio0)) .setChecked(true);
							 *//*

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
						}*/

						// international 1



						if (cur.getString(2) == null) {

						}
						else {
							for (int i = 0; i < ((Spinner) v
									.findViewById(R.id.sp2)).getCount(); i++) {

								if (((Spinner) v.findViewById(R.id.sp2))
										.getItemAtPosition(i).toString()
										.contains(cur.getString(2))) {
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
		finally
		{
			if(cur != null)
				cur.close();
		}

		/*if (CommonStaticClass.getSkip("Comp", "tblLinelist", dbHelper)
				.equalsIgnoreCase("1")) {
			CommonStaticClass.SetSpinnerValue(thisactivity,
					((Spinner) v.findViewById(R.id.sp2)), "3");
			((CheckBox) v.findViewById(R.id.checkBox1))
					.setVisibility(View.VISIBLE);
			((TextView) v.findViewById(R.id.textView2))
					.setVisibility(View.GONE);
			((EditText) v.findViewById(R.id.dischargedate))
					.setVisibility(View.GONE);

		} else if (CommonStaticClass.getSkip("Comp", "tblLinelist", dbHelper)
				.equalsIgnoreCase("2")) {
			CommonStaticClass.SetSpinnerValue(thisactivity,
					((Spinner) v.findViewById(R.id.sp1)), "01");

			((CheckBox) v.findViewById(R.id.checkBox1))
					.setVisibility(View.GONE);
			((TextView) v.findViewById(R.id.textView2))
					.setVisibility(View.GONE);
			((EditText) v.findViewById(R.id.dischargedate))
					.setVisibility(View.GONE);

		} else if (CommonStaticClass.getSkip("Comp", "tblLinelist", dbHelper)
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
		}*/
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
		if (formname.equalsIgnoreCase("frmfindsection")) {
			index = 30;
		}
		if (formname.equalsIgnoreCase("frmneonatelinfo")) {
			index = 31;
		}
		
		if (formname.equalsIgnoreCase("frmcamera")) {
			index = 32;
		}
		if (formname.equalsIgnoreCase("frmmultiplechoicetext")) {
			index = 33;
		}
		if (formname.equalsIgnoreCase("frmaddress")) {
			index = 34;
		}
		if (formname.equalsIgnoreCase("frmpersonrelation")) {
			index = 35;
		}
		if (formname.equalsIgnoreCase("frmsymptoms")) {
			index = 36;
		}
		if (formname.equalsIgnoreCase("frmdataid2")) {
			index = 37;
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
			//loadGuiFrmFamilyMember(frmfamilymember);
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
			//Load_UIFrmReasoning(frmreasoning);
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
			//loadGuiFrmMultipleChoiceRadio(frmmultiplechoiceradio);
			break;

		case 24:
			loadGuiFrmMultiple(frmmultiple);
			break;

		case 25:
			//loadGuiFrmq124(frmq124);
			break;

		case 26:
			//loadGuiFrmMultipleCheckNumeric(frmmultiplechecknumeric);
			break;

		case 27:
			//loadguifrmmultiplecheckdate(frmmultiplecheckdate);
			break;

		case 28:
			loadGuiFrmBarcode(frmbarcode);
			break;
		case 29:
//			loadGuifrmnumericwithrdbtn(frmnumericwithrdbtn);
			break;
		case 30:
			//loadGuifrmfindsection(frmfindsection);
			break;
		case 31:
			//loadGuifrmneonatelinfo(frmneonatelinfo);
			break;
			
		case 32:
			loadGuifrmcamera(frmcamera);
			break;
		case 33:
			loadguifrmmultiplechoicetext(frmmultiplechoicetext);
			break;
		case 34:
			loadguifrmaddress(frmaddress);
			break;
		case 35:
			loadguifrmpersonrelation(frmpersonrelation);
			break;
		case 36:
			loadguifrmsymptoms(frmsymptoms);
			break;
		case 37:
			loadguifrmdataid2(frmdataid2);
			break;

		default:

			break;
		}
	}

	private void loadguifrmdataid2(ViewGroup v) {


		// TODO Auto-generated method stub

		final ViewGroup vg = v;
		// textView3 = (TextView)v.findViewById(R.id.textView3);

//		qqq = (TextView) v.findViewById(R.id.qqq);
//
//		if (CommonStaticClass.langBng) {
//			qqq.setText(CommonStaticClass.questionMap.get(
//					CommonStaticClass.currentSLNo).getQdescbng());
//		} else {
//			qqq.setTypeface(null);
//			qqq.setText(CommonStaticClass.questionMap.get(
//					CommonStaticClass.currentSLNo).getQdesceng());
//		}

		FillAllDataidCombo2(v);

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

					if (mCursor1.getColumnIndex(column+"_2") != -1) {
						String val = mCursor1.getString(mCursor1
								.getColumnIndex(column+"_2")) + "";
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
				/*updateDisplay("date");*/
			}
		}

		confButton = (Button) v.findViewById(R.id.confButton);
		confButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				// updateTableData();
				if (IsValidDataIDUserInput2(vg) == false) {
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
									updateTableDataFrmHHID2(vg);
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
		prevButton = (Button) v.findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				userPressedPrevious(ParentActivity.this);
			}

		});


	}

	private void updateTableDataFrmHHID2(ViewGroup vg) {

		if (CommonStaticClass.hbisdataId.length() < 10 || pickDate.getText().toString().isEmpty()) {
			CommonStaticClass.showMyAlert(con, "Error",
					"Select all fields");
			return;
		}
		String ques = CommonStaticClass.questionMap.get(
				CommonStaticClass.currentSLNo).getQvar();
		String sql = "Update "+ CommonStaticClass.questionMap.get(
				CommonStaticClass.currentSLNo).getTablename() +" set "+ques+"_1= '"+CommonStaticClass.hbisdataId+"', "+ques+"_2= '"+pickDate.getText()+"'  Where dataid='"
				+ CommonStaticClass.dataId+"'";

		if (dbHelper.executeDMLQuery(sql))
		{
			createRowForHbis();
			CommonStaticClass.findOutNextSLNo(
					CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar(),
					CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQnext1());
			CommonStaticClass.nextQuestion(ParentActivity.this);
		}
	}

	private void createRowForHbis() {

		String sql = "Select * from tblLinelist where dataid='" + CommonStaticClass.dataId + "'";
		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.moveToFirst()) {

				String entryDate = "dd/mm/yyyy";
				String entryTime = "hh:mm";
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date d = new Date(System.currentTimeMillis());
				entryDate = sdf.format(d);
				sdf = new SimpleDateFormat("HH:mm");
				entryTime = sdf.format(d);
				String sqlI = "insert into tblMainQues (dataid,Comp,YearID,MonthID,HosID,PatID" +
						/*",HosName,Dept," +
						"DgCode,DgOthers,Name,HHHead,AgeY,AgeM,AgeD,Sex,Vill,UnionName,PSCode,DistCode,Location" +
						",Phone,Phone1,AdmDate,DisDate,OutCome" +*/
						/*",Fever,dt_fever,fever_meas_subj,fever_temp,Cough,dt_cough," +
						"sputum,dif_brea,dt_brea,s_throat,dt_throa,r_nose,dt_nose,headache,dt_head,diarrhea,dt_diarr" +
						",chills,dt_chills,bodyache,dt_body,hemo,dt_hemo,pleur,dt_peur" +*/
						/*",other1,other2,other3,SOther1N," +
						"SOther1DT,SOther2N,SOther2DT,SOther3N,SOther3DT,S5ChestIND,S5Stridor,S5Convulsion,S5UnDrink,S5UnCons" +
						",S5Vomit" +*/
						",EntryBy,EntryDate, EntryTime,assetid) values ('"
						+ CommonStaticClass.hbisdataId
						+ "','"
						+ String.valueOf(CommonStaticClass.hbisdataId.charAt(0))
						+ "','"
						+ CommonStaticClass.hbisdataId.substring(1, 3)
						+ "','" + CommonStaticClass.hbisdataId.substring(3,5)
						+ "','" + CommonStaticClass.hbisdataId.substring(5, 7)
						+ "','" + CommonStaticClass.hbisdataId.substring(7)
						/*+ "','" + mCursor1.getString(mCursor1.getColumnIndex("HosName"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("Dept"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("DgCode"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("DgOthers"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("Name"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("HHHead"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("AgeY"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("AgeM"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("AgeD"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("Sex"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("Vill"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("UnionName"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("PSCode"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("DistCode"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("Location"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("Phone"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("Phone1"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("AdmDate"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("DisDate"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("OutCome"))*/
						/*+ "','" + mCursor1.getString(mCursor1.getColumnIndex("Fever"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("dt_fever"))
						+ "','" +mCursor1.getString(mCursor1.getColumnIndex("fever_meas_subj"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("fever_temp"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("Cough"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("dt_cough"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("sputum"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("dif_brea"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("dt_brea"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("s_throat"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("dt_throa"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("r_nose"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("dt_nose"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("headache"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("dt_head"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("diarrhea"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("dt_diarr"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("chills"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("dt_chills"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("bodyache"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("dt_body"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("hemo"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("dt_hemo"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("pleur"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("dt_peur"))*/
						/*+ "','" + mCursor1.getString(mCursor1.getColumnIndex("other1"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("other2"))
						+ "','" + mCursor1.getString(mCursor1.getColumnIndex("other3"))
						+ "'," + mCursor1.getString(mCursor1.getColumnIndex("SOther1N"))
						+ "," + mCursor1.getString(mCursor1.getColumnIndex("SOther1DT"))
						+ "," + mCursor1.getString(mCursor1.getColumnIndex("SOther2N"))
						+ "," + mCursor1.getString(mCursor1.getColumnIndex("SOther2DT"))
						+ "," + mCursor1.getString(mCursor1.getColumnIndex("SOther3N"))
						+ "," + mCursor1.getString(mCursor1.getColumnIndex("SOther3DT"))
						+ "," + mCursor1.getString(mCursor1.getColumnIndex("S5ChestIND"))
						+ "," + mCursor1.getString(mCursor1.getColumnIndex("S5Stridor"))
						+ "," + mCursor1.getString(mCursor1.getColumnIndex("S5Convulsion"))
						+ "," + mCursor1.getString(mCursor1.getColumnIndex("S5UnDrink"))
						+ "," + mCursor1.getString(mCursor1.getColumnIndex("S5UnCons"))
						+ "," + mCursor1.getString(mCursor1.getColumnIndex("S5Vomit"))*/
						+ "','" + CommonStaticClass.userSpecificId + "','" + entryDate + "','" + entryTime +  "','" + CommonStaticClass.AssetID + "' )";

				dbHelper.executeDMLQuery(sqlI);

				/*if(dbHelper.executeDMLQuery(sqlI))
				{*/
					StringBuilder SB = new StringBuilder();
					SB.append("UPDATE tblMainQues set " +

							/*"dataid = '"+CommonStaticClass.hbisdataId+"'" +
							",Comp = '"+String.valueOf(CommonStaticClass.hbisdataId.charAt(0))+"'" +
							",YearID = '"+CommonStaticClass.hbisdataId.substring(1,3)+"'" +
							",MonthID = '"+CommonStaticClass.hbisdataId.substring(3,5)+"'" +
							",HosID = '"+CommonStaticClass.hbisdataId.substring(5,7)+"' " +
							",PatID = '"+CommonStaticClass.hbisdataId.substring(7)+"'" +*/
							"HosName = '"+mCursor1.getString(mCursor1.getColumnIndex("HosName"))+"'" +
							", Dept = '"+mCursor1.getString(mCursor1.getColumnIndex("Dept"))+"'" +
							",DgCode = '"+mCursor1.getString(mCursor1.getColumnIndex("DgCode"))+"'" +
							",DgOthers = '"+mCursor1.getString(mCursor1.getColumnIndex("DgOthers"))+"'" +
							",Name = '"+mCursor1.getString(mCursor1.getColumnIndex("Name"))+"'" +
							",HHHead = '"+mCursor1.getString(mCursor1.getColumnIndex("HHHead"))+"' " +
							",AgeY = '"+mCursor1.getString(mCursor1.getColumnIndex("AgeY"))+"'" +
							",AgeM = '"+mCursor1.getString(mCursor1.getColumnIndex("AgeM"))+"'" +
							",AgeD = '"+mCursor1.getString(mCursor1.getColumnIndex("AgeD"))+"'" +
							",Sex = '"+mCursor1.getString(mCursor1.getColumnIndex("Sex"))+"'" +
							",Vill = '"+mCursor1.getString(mCursor1.getColumnIndex("Vill"))+"'" +
							",UnionName = '"+mCursor1.getString(mCursor1.getColumnIndex("UnionName"))+"'" +
							",PSCode = '"+mCursor1.getString(mCursor1.getColumnIndex("PSCode"))+"'" +
							",DistCode = '"+mCursor1.getString(mCursor1.getColumnIndex("DistCode"))+"'" +
//							",Location = '"+mCursor1.getString(mCursor1.getColumnIndex("Location"))+"'" +
							",Phone = '"+mCursor1.getString(mCursor1.getColumnIndex("Phone"))+"'" +
							",Phone1 = '"+mCursor1.getString(mCursor1.getColumnIndex("Phone1"))+"'" +
							",AdmDate = '"+mCursor1.getString(mCursor1.getColumnIndex("AdmDate"))+"'" +
							",DisDate = '"+mCursor1.getString(mCursor1.getColumnIndex("DisDate"))+"'" +
							",OutCome = '"+mCursor1.getString(mCursor1.getColumnIndex("OutCome"))+"'");


					if (mCursor1.getString(mCursor1.getColumnIndex("Fever")).equalsIgnoreCase("1")) {
						SB.append(",Fever = 1");
						SB.append(", dt_fever = '"
								+ mCursor1.getString(mCursor1.getColumnIndex("dt_fever")) + "' ");
					} else {
						SB.append(",Fever = 2 ");
						SB.append(", dt_fever = null ");
					}
					if (mCursor1.getString(mCursor1.getColumnIndex("fever_meas_subj")) != null) {
						// int x =
						// (((Spinner)vg.findViewById(R.id.cboSfever)).getSelectedItem().toString().lastIndexOf(":")
						// - 1);

						SB.append(", fever_meas_subj = "
								+mCursor1.getString(mCursor1.getColumnIndex("fever_meas_subj")));

						if (mCursor1.getString(mCursor1.getColumnIndex("fever_temp")) != null) {
							SB.append(", fever_temp = '"
									+ mCursor1.getString(mCursor1.getColumnIndex("fever_temp")) + "'");
						} else {
							SB.append(", fever_temp = null ");
						}

					} else {
						SB.append(", fever_meas_subj = null ");
						SB.append(", fever_temp = null ");
					}
					if (mCursor1.getString(mCursor1.getColumnIndex("Cough")).equalsIgnoreCase("1")) {
						SB.append(", cough = 1");
						SB.append(", dt_cough = '"
								+ mCursor1.getString(mCursor1.getColumnIndex("dt_cough")) + "' ");
					} else {
						SB.append(", dt_cough = Null ");
						SB.append(", Cough = 2 ");
					}

					if (mCursor1.getString(mCursor1.getColumnIndex("sputum"))!= null)
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
					} else if (mCursor1.getString(mCursor1.getColumnIndex("sputum")) != null) {
						SB.append(", sputum = 2 ");
						// SB.append(", sputum = Null ");
					}

					if (mCursor1.getString(mCursor1.getColumnIndex("dif_brea")).equalsIgnoreCase("1")) {
						SB.append(", dif_brea = 1");
						SB.append(", dt_brea = '"
								+ mCursor1.getString(mCursor1.getColumnIndex("dt_brea")) + "' ");
					} else {
						SB.append(", dt_brea = Null ");
						SB.append(", dif_brea = 2 ");
					}
//					if (intComp != 3 && intAge >= 5) {
						if (mCursor1.getString(mCursor1.getColumnIndex("s_throat")).equalsIgnoreCase("1")) {
							SB.append(", s_throat = 1");
							SB.append(", dt_throa = '"
									+ mCursor1.getString(mCursor1.getColumnIndex("dt_throa")) + "' ");
						} else {
							SB.append(", dt_throa = Null ");
							SB.append(", s_throat = 2 ");
						}
					/*} else {
						SB.append(", dt_throa = Null ");
						SB.append(", s_throat = 2 ");

					}*/

					if (mCursor1.getString(mCursor1.getColumnIndex("r_nose")).equalsIgnoreCase("1")) {
						SB.append(", r_nose = 1");
						SB.append(", dt_nose= '"
								+ mCursor1.getString(mCursor1.getColumnIndex("dt_nose")) + "' ");
					} else {
						SB.append(", r_nose = 2");
						SB.append(", dt_nose= Null ");
					}

					if (Integer.parseInt(mCursor1.getString(mCursor1.getColumnIndex("AgeY"))) >= 5) {
						if (mCursor1.getString(mCursor1.getColumnIndex("headache")).equalsIgnoreCase("1")) {
							SB.append(", headache = 1");
							SB.append(", dt_head= '"
									+ mCursor1.getString(mCursor1.getColumnIndex("dt_head")) + "' ");
						} else {
							SB.append(", headache = 2");
							SB.append(", dt_head= Null ");
						}
					} else {
						SB.append(", headache = null");
						SB.append(", dt_head= Null ");
					}

					if (mCursor1.getString(mCursor1.getColumnIndex("diarrhea")).equalsIgnoreCase("1")) {
						SB.append(", diarrhea = 1");
						SB.append(", dt_diarr= '"
								+ mCursor1.getString(mCursor1.getColumnIndex("dt_diarr")) + "' ");
					} else {
						SB.append(", diarrhea = 2");
						SB.append(", dt_diarr= Null ");
					}

					if (mCursor1.getString(mCursor1.getColumnIndex("chills")).equalsIgnoreCase("1")) {
						SB.append(", chills = 1");
						SB.append(", dt_chills= '"
								+ mCursor1.getString(mCursor1.getColumnIndex("dt_chills")) + "' ");
					} else {
						SB.append(", chills = 2");
						SB.append(", dt_chills= Null ");
					}
				if (Integer.parseInt(mCursor1.getString(mCursor1.getColumnIndex("AgeY"))) >= 5) {
						if (mCursor1.getString(mCursor1.getColumnIndex("bodyache")).equalsIgnoreCase("1")) {
							SB.append(", bodyache = 1");
							SB.append(", dt_body= '"
									+ mCursor1.getString(mCursor1.getColumnIndex("dt_body")) + "' ");
						} else {
							SB.append(", bodyache = 2");
							SB.append(", dt_body= Null ");
						}
					} else {
						SB.append(", bodyache = null");
						SB.append(", dt_body= Null ");
					}
					if (mCursor1.getString(mCursor1.getColumnIndex("hemo")).equalsIgnoreCase("1")) {
						SB.append(", hemo = 1");
						SB.append(", dt_hemo='"
								+ mCursor1.getString(mCursor1.getColumnIndex("dt_hemo")) + "'");
					} else {
						SB.append(", hemo = 2");
						SB.append(", dt_hemo= Null ");
					}

					if (mCursor1.getString(mCursor1.getColumnIndex("pleur")).equalsIgnoreCase("1")) {
						SB.append(", pleur = 1");
						SB.append(", dt_peur='"
								+ mCursor1.getString(mCursor1.getColumnIndex("dt_peur")) + "'");
					} else {
						SB.append(", pleur = 2");
						SB.append(", dt_peur=Null ");
					}



					if (mCursor1.getString(mCursor1.getColumnIndex("other1")).equalsIgnoreCase("1")) {
						SB.append(", other1 = 1");
						SB.append(", SOther1N = '"
								+ mCursor1.getString(mCursor1.getColumnIndex("SOther1N")) + "' ");
						SB.append(", SOther1DT = '"
								+ mCursor1.getString(mCursor1.getColumnIndex("SOther1DT")) + "' ");
					} else {
						SB.append(", other1 = 2");
						SB.append(", SOther1DT = Null ");
						SB.append(", SOther1N = null");
					}

					if (mCursor1.getString(mCursor1.getColumnIndex("other2")).equalsIgnoreCase("1")) {
						SB.append(", other2 = 1");
						SB.append(", SOther2N = '"
								+ mCursor1.getString(mCursor1.getColumnIndex("SOther2N")) + "' ");
						SB.append(", SOther2DT = '"
								+ mCursor1.getString(mCursor1.getColumnIndex("SOther2DT")) + "' ");
					} else {
						SB.append(", other2 = 2");
						SB.append(", SOther2DT = Null ");
						SB.append(", SOther2N = null");
					}

					if (mCursor1.getString(mCursor1.getColumnIndex("other3")).equalsIgnoreCase("1")) {
						SB.append(", other3 = 1");
						SB.append(", SOther3DT = '"
								+  mCursor1.getString(mCursor1.getColumnIndex("SOther3DT")) + "' ");
						SB.append(", SOther3N = '"
								+ mCursor1.getString(mCursor1.getColumnIndex("SOther3N")) + "' ");
					} else {
						SB.append(", other3 = 2");
						SB.append(", SOther3DT = Null ");
						SB.append(", SOther3N = null");
					}



//					if (Integer.parseInt(mCursor1.getString(mCursor1.getColumnIndex("AgeY")))<5) {
						if (mCursor1.getString(mCursor1.getColumnIndex("S5ChestIND")) != null) {
							SB.append(", S5ChestIND = '"
									+ mCursor1.getString(mCursor1.getColumnIndex("S5ChestIND")) + "' ");
						} else {
							SB.append(", S5ChestIND = Null ");
						}
						if (mCursor1.getString(mCursor1.getColumnIndex("S5Stridor"))!=null) {
							SB.append(", S5Stridor = '"
									+ mCursor1.getString(mCursor1.getColumnIndex("S5Stridor")) + "' ");
						} else {
							SB.append(", S5Stridor = Null ");
						}
						if (mCursor1.getString(mCursor1.getColumnIndex("S5Convulsion")) != null) {
							SB.append(", S5Convulsion = '"
									+ mCursor1.getString(mCursor1.getColumnIndex("S5Convulsion")) + "' ");
						} else {
							SB.append(", S5Convulsion = Null ");
						}
						if (mCursor1.getString(mCursor1.getColumnIndex("S5UnDrink"))!= null) {
							SB.append(", S5UnDrink = '"
									+ mCursor1.getString(mCursor1.getColumnIndex("S5UnDrink")) + "' ");
						} else {
							SB.append(", S5UnDrink = Null ");
						}
						if (mCursor1.getString(mCursor1.getColumnIndex("S5UnCons")) != null) {
							SB.append(", S5UnCons = '"
									+ mCursor1.getString(mCursor1.getColumnIndex("S5UnCons")) + "' ");
						} else {
							SB.append(", S5UnCons = Null ");
						}
						if (mCursor1.getString(mCursor1.getColumnIndex("S5Vomit")) != null) {
							SB.append(", S5Vomit = '"
									+ mCursor1.getString(mCursor1.getColumnIndex("S5Vomit")) + "' ");
						} else {
							SB.append(", S5Vomit = Null ");
						}
						SB.append(", isTransfered = 0 ");

					 /*}else {
						SB.append(", S5ChestIND = Null ");
						SB.append(", S5Stridor = Null ");
						SB.append(", S5Convulsion = Null ");
						SB.append(", S5UnDrink = Null ");
						SB.append(", S5UnCons = Null ");
						SB.append(", S5Vomit = Null ");
					}*/
					SB.append(" where dataid = '" + CommonStaticClass.hbisdataId + "'");
					dbHelper.executeDMLQuery(SB.toString());

				String s = String
						.format("Insert into %s (dataid,VersionNo,assetid,EntryBy,EntryDate,isTransfered) VALUES('%s','%s','%s','%s','%s','%s')",
								"tblMainQuesEPT", CommonStaticClass.hbisdataId,
								CommonStaticClass.VersionNo,
								CommonStaticClass.AssetID,
								CommonStaticClass.userSpecificId, entryDate, '0');
				dbHelper.executeDMLQuery(s);

				/*}
				else
				{

				}*/





			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}

	}


	private boolean IsValidDataIDUserInput2(ViewGroup v) {




		CommonStaticClass.hbisdataId = ((Spinner) v.findViewById(R.id.sp1))
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

		if (CommonStaticClass.hbisdataId.equalsIgnoreCase(ReDataid)) {
			return true;
		}

		return false;

	}

	private void FillAllDataidCombo2(ViewGroup v) {


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

		yearids.add("15");
		yearids.add("16");
		yearids.add("17");
		yearids.add("18");
		yearids.add("19");
		yearids.add("20");
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

		String sql = "Select * from "
				+ CommonStaticClass.questionMap.get(
				CommonStaticClass.currentSLNo).getTablename()
				+ " where dataid='" + CommonStaticClass.dataId + "'";
		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.moveToFirst()) {

					String column = CommonStaticClass.questionMap.get(
							CommonStaticClass.currentSLNo).getQvar();

					if (mCursor1.getColumnIndex(column+"_1") != -1) {
						String val = mCursor1.getString(mCursor1
								.getColumnIndex(column+"_1")) + "";

						if(!val.equalsIgnoreCase("null")) {
							CommonStaticClass.SetSpinnerValueFrmString(thisactivity,
									((Spinner) v.findViewById(R.id.sp1)),
									String.valueOf(val.charAt(0)));
							CommonStaticClass.SetSpinnerValueFrmString(thisactivity,
									((Spinner) v.findViewById(R.id.sp2)),
									val.substring(1, 3));
							CommonStaticClass.SetSpinnerValueFrmString(thisactivity,
									((Spinner) v.findViewById(R.id.sp3)),
									val.substring(3, 5));
							CommonStaticClass.SetSpinnerValueFrmString(thisactivity,
									((Spinner) v.findViewById(R.id.sp4)),
									val.substring(5, 7));
							CommonStaticClass.SetSpinnerValueFrmString(thisactivity,
									((Spinner) v.findViewById(R.id.sp5)),
									val.substring(7));

							CommonStaticClass.SetSpinnerValueFrmString(thisactivity,
									((Spinner) v.findViewById(R.id.spl1)),
									String.valueOf(val.charAt(0)));
							CommonStaticClass.SetSpinnerValueFrmString(thisactivity,
									((Spinner) v.findViewById(R.id.spl2)),
									val.substring(1, 3));
							CommonStaticClass.SetSpinnerValueFrmString(thisactivity,
									((Spinner) v.findViewById(R.id.spl3)),
									val.substring(3, 5));
							CommonStaticClass.SetSpinnerValueFrmString(thisactivity,
									((Spinner) v.findViewById(R.id.spl4)),
									val.substring(5, 7));
							CommonStaticClass.SetSpinnerValueFrmString(thisactivity,
									((Spinner) v.findViewById(R.id.spl5)),
									val.substring(7));
						}
						else
						{
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




		/*
		 * cboHospitalID.SelectedItem = strHosID.Trim();
		 * cboHospitalIDRe.SelectedItem = strHosID.Trim();
		 */


	}

	//frmsymptoms
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

	private Integer intAge, intSex;
	private void updateTableDataFrmSymptom(ViewGroup vg)
	{

		try {
			// if (SB.Length > 0)
			// {
			// SB.Remove(0, SB.Length);
			// }
			StringBuilder SB = new StringBuilder();

			intAge = Integer.parseInt(CommonStaticClass.getSkip("AgeY",
					"tblLinelist", dbHelper));
			intSex = Integer.parseInt(CommonStaticClass.getSkip("Sex",
					"tblLinelist", dbHelper));
			/*intComp = Integer.parseInt(CommonStaticClass.getSkip("Comp",
					"tblLinelist", dbHelper));*/

			SB.append("UPDATE tblLinelist ");
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
			if (intAge >= 5) {
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

			if (((CheckBox) vg.findViewById(R.id.chkOtherFour)).isChecked()) {
				SB.append(", other4 = 1");
				SB.append(", SOther4DT = '"
						+ ((EditText) vg.findViewById(R.id.dtpOtherFour))
						.getText().toString() + "' ");
				SB.append(", SOther4N = '"
						+ ((EditText) vg.findViewById(R.id.txtOtherFour))
						.getText().toString() + "' ");
			} else {
				SB.append(", other4 = 2");
				SB.append(", SOther4DT = Null ");
				SB.append(", SOther4N = null");
			}

			if (intAge < 5) {
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

	private boolean CheckValidationfrmSymptom(ViewGroup v)
	{

		// Checking Fever is checked or not
		intAge = Integer.parseInt(CommonStaticClass.getSkip("AgeY",
				"tblLinelist", dbHelper));
		intSex = Integer.parseInt(CommonStaticClass.getSkip("Sex",
				"tblLinelist", dbHelper));
		/*intComp = Integer.parseInt(CommonStaticClass.getSkip("Comp",
				"tblLinelist", dbHelper));*/

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



		String edate = CommonStaticClass.getSkip("EntryDate", "tblLinelist",
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
						"Please input correct Runny Nose date");
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

		if (((CheckBox) v.findViewById(R.id.chkOtherFour)).isChecked()) {

			if (CommonStaticClass.IsValidHBISDate(((EditText) v.findViewById(R.id.dtpOtherFour)))) {
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
			try {
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

				if (x > 21) {
					CommonStaticClass.showMyAlert(thisactivity, "Error",
							"Fever Date can not be more than 21");
					return false;

				}
			}
			catch(Exception e)
			{

			}

		}



		// Checking Fever is checked or not
		/*if (intComp != 3) {
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


				*//*	if (CommonStaticClass.IsSeconDateGraterThanFirstDate(
							((EditText) v.findViewById(R.id.dtpFever))
									.getText().toString(), edate)) {
						CommonStaticClass.showMyAlert(thisactivity, "Error",
								"Please input correct Fever date");
						return false;
					}*//*

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

					*//*
					 * else if (CommonStaticClass .DayDifference(enddate,
					 * startdate) < 7) {
					 * CommonStaticClass.showMyAlert(thisactivity, "Error",
					 * "Fever Date can not be less than 7"); return false;
					 *
					 * }
					 *//*

					*//*
					 * if(!CommonStaticClass.IsSeconDateGraterThanFirstDate(((
					 * EditText) v
					 * .findViewById(R.id.dtpFever)).getText().toString(),
					 * CommonStaticClass.getSkip("EntryDate", "tblLinelist",
					 * dbHelper))) { CommonStaticClass.showMyAlert(thisactivity,
					 * "Error", "Please input correct fever date"); return
					 * false; }
					 *//*

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*//*
				 * ((EditText) v.findViewById(R.id.dtpFever))
				 * .setText(CommonStaticClass .CheckCursorValueWithNullHandler(
				 * mCursor, "dt_fever") .toString());
				 *//*
			}
			if (((CheckBox) v.findViewById(R.id.chkMeasureFever)).isChecked() == false) {
				CommonStaticClass.showMyAlert(thisactivity, "Message",
						"Measure Fever must be checked");
				((CheckBox) v.findViewById(R.id.chkMeasureFever))
						.setBackgroundResource(R.drawable.border);
				// tabDiseaseInfo.SelectedIndex = 0;
				return false;
			}
		}*/
		/*if (intComp == 3) {
			if (((CheckBox) v.findViewById(R.id.chkDiffbreathing)).isChecked() == false
					&& ((CheckBox) v.findViewById(R.id.chkCough)).isChecked() == false) {
				CommonStaticClass.showMyAlert(thisactivity, "Message",
						"Diffbreathing or cough must be checked.");
				((CheckBox) v.findViewById(R.id.chkDiffbreathing))
						.setBackgroundResource(R.drawable.border);
				// tabDiseaseInfo.SelectedIndex = 0;
				return false;
			}
		}*/


		if ( intAge < 5) {
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







		// intComp = Convert.ToInt16(txtHHID.Text.Substring(0, 1).Trim());


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

		if (((CheckBox) v.findViewById(R.id.chkOtherFour)).isChecked() == true
				&& ((EditText) v.findViewById(R.id.txtOtherFour)).getText()
				.toString().length() <= 0) {
			((EditText) v.findViewById(R.id.txtOtherFour))
					.setBackgroundResource(R.drawable.border);
			CommonStaticClass.showMyAlert(thisactivity, "Message",
					"Please write something in other.");
			// tabDiseaseInfo.SelectedIndex = 2;
			return false;
		}



		return true;

	}

	private void loadDataFrmSymptom(ViewGroup v)
	{


		final ViewGroup vg = v;
		HideViews(v);
		/*String sql = "SELECT '' AS DrCode UNION SELECT (" + "" + "DrCode" + ""
				+ "|| " + "" + "' : '" + " || " + "Name" + ") AS " + "D"
				+ " from frmrDrugChronicList WHERE DrCode <> '0' ORDER BY DrCode"
				+ "";*/
		String sql = "SELECT '' AS DrCode UNION SELECT (" + "" + "DrCode" + ""
				+ "|| " + "" + "' : '" + " || " + "Name" + ") AS " + "D"
				+ " from DrugChronicList WHERE DrCode <> '0' ORDER BY DrCode"
				+ "";


		/*sql = "SELECT '' AS DrCode UNION SELECT (" + "" + "DrCode" + "" + "|| "
				+ "" + "' : '" + " || " + "Name" + ") AS " + "D"
				+ " from frmrDrugChronicList ORDER BY DrCode" + "";*/
		sql = "SELECT '' AS DrCode UNION SELECT (" + "" + "DrCode" + "" + "|| "
				+ "" + "' : '" + " || " + "Name" + ") AS " + "D"
				+ " from DrugChronicList ORDER BY DrCode" + "";


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



		SetCheckBox((CheckBox) v.findViewById(R.id.chkFever),
				(EditText) v.findViewById(R.id.dtpFever), v);

		((CheckBox) v.findViewById(R.id.chkMeasureFever))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {


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
						} else {
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
		SetCheckBox((CheckBox) v.findViewById(R.id.chkOtherFour),
				(EditText) v.findViewById(R.id.txtOtherFour), v);

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
		SetDate((EditText) v.findViewById(R.id.dtpOtherFour));

		SetDate((EditText) v.findViewById(R.id.dtpChestIndraw));
		SetDate((EditText) v.findViewById(R.id.dtpStridor));
		SetDate((EditText) v.findViewById(R.id.dtpConvulsion));
		SetDate((EditText) v.findViewById(R.id.dtpUnableDrink));
		SetDate((EditText) v.findViewById(R.id.dtpUnconsciousness));
		SetDate((EditText) v.findViewById(R.id.dtpVomit));


		((Spinner) vg.findViewById(R.id.cboSfever))
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




		intAge = Integer.parseInt(CommonStaticClass.getSkip("AgeY",
				"tblLinelist", dbHelper));
		intSex = Integer.parseInt(CommonStaticClass.getSkip("Sex",
				"tblLinelist", dbHelper));
		/*intComp = Integer.parseInt(CommonStaticClass.getSkip("Comp",
				"tblLinelist", dbHelper));*/

//		GetCathmanArea();
		Cursor mCursor = null;
		try {
			String strSQL = "Select * from tblLinelist where dataid = '"
					+ CommonStaticClass.dataId + "'";




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

							if (intAge >= 5) {
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

							if (CommonStaticClass
									.CheckCursorValueWithNullHandler(mCursor,
											"SOther4DT").trim()
									.equalsIgnoreCase("")) {
								((CheckBox) v.findViewById(R.id.chkOtherFour))
										.setChecked(false);
								((EditText) v.findViewById(R.id.txtOtherFour))
										.setText("");
							} else {
								((CheckBox) v.findViewById(R.id.chkOtherFour))
										.setChecked(true);
								((EditText) v.findViewById(R.id.dtpOtherFour))
										.setText(CommonStaticClass
												.CheckCursorValueWithNullHandler(
														mCursor, "SOther4DT")
												.toString().trim());
								((EditText) v.findViewById(R.id.txtOtherFour))
										.setText(CommonStaticClass
												.CheckCursorValueWithNullHandler(
														mCursor, "SOther4N")
												.toString().trim());
							}

							if (intAge < 5) {
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


						} while (mCursor.moveToNext());

					}

				}

			} catch (Exception e) {
				CommonStaticClass.showMyAlert(thisactivity, "Error",
						"Error On Load");
			}
		 finally {
			if(mCursor != null )
				mCursor.close();

		}


	}

	private void SetDate(EditText et) {

		if (!(et.getText().toString().length() > 0))
			updateDisplayfrmSymptom("date", et);

		et.setHint("Date of Onset");
		et.setHintTextColor(Color.GRAY);
		et.setOnTouchListener(new View.OnTouchListener() {


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
	private void updateDisplayfrmSymptom(String dt, EditText et) {

		if (dt.contains("date")) {
			String date = new StringBuilder()
					// Month is 0 based so add 1
					.append(String.format("%02d", dateDay)).append("/")
					.append(String.format("%02d", dateMonth + 1)).append("/")
					.append(dateYear).toString();

			if (et.isFocused())
				et.setText(date);
		}

	}
	private void SetCheckBox(final CheckBox chk, final EditText et,
							 final ViewGroup vg) {

		chk.setOnCheckedChangeListener(new OnCheckedChangeListener() {


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
					}else if (R.id.chkOtherFour == chk.getId()) {
						((EditText) vg.findViewById(R.id.dtpOtherFour))
								.setVisibility(View.VISIBLE);
					}else if (R.id.chkFever == chk.getId()) {
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
					} else if (R.id.chkOtherFour == chk.getId()) {
						((EditText) vg.findViewById(R.id.dtpOtherFour))
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

	private void HideViews(ViewGroup v)
	{


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
		((EditText) v.findViewById(R.id.dtpOtherFour))
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
		((EditText) v.findViewById(R.id.txtOtherFour))
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

		((EditText) v.findViewById(R.id.dtpOtherOne)).setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.dtpOtherTwo)).setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.dtpOtherThree))
				.setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.dtpOtherFour))
				.setVisibility(View.GONE);


		((Spinner) v.findViewById(R.id.cboSfever)).setVisibility(View.GONE);
		((EditText) v.findViewById(R.id.txtFeverTemp)).setVisibility(View.GONE);


		intAge = Integer.parseInt(CommonStaticClass.getSkip("AgeY",
				"tblLinelist", dbHelper));

		if (intAge < 5)
			((LinearLayout) v.findViewById(R.id.pan4))
					.setVisibility(View.VISIBLE);
		else if (intAge >= 5)
			((LinearLayout) v.findViewById(R.id.pan4)).setVisibility(View.GONE);

		intSex = Integer.parseInt(CommonStaticClass.getSkip("Sex",
				"tblLinelist", dbHelper));
		/*
		 * intComp = Integer.parseInt(CommonStaticClass.getSkip("Comp",
		 * "tblLinelist", dbHelper));
		 */

		/*
		 * if(intSex == 2) { (TextView) }
		 */


	}

	private void loadDataAddress(final ViewGroup v) {

		/*String dist = "SELECT (" + "" + "DistCode" + "" + "|| " + "" + "' : '"
				+ " || " + "DistName" + ") AS " + "D"
				+ " from frmrDistrict ORDER BY DistName" + "";*/
		String dist = "SELECT (" + "" + "DistCode" + "" + "|| " + "" + "' : '"
				+ " || " + "DistName" + ") AS " + "D"
				+ " from District ORDER BY DistName" + "";


		/*String occu = "SELECT '' AS occupCode UNION ALL SELECT (" + ""
				+ "occupCode" + "" + "|| " + "" + "' : '" + " || "
				+ "Occupation" + ") AS " + "O"
				+ " from tblOccupation ORDER BY occupCode" + "";*/

		autoDist=(AutoCompleteTextView)findViewById(R.id.autoCompleteDist);
		autoThana=(AutoCompleteTextView)findViewById(R.id.autoCompleteThana);

		String[] distList = loadAutoComplete(dist);
		final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,distList);


		String strSQL = String.format("SELECT ("
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
				+ " from PoliceStation  ORDER BY PSName"
				+ "");

					/*CommonStaticClass.FillCombo(thisactivity, dbHelper,
					strSQL,
							((Spinner) v.findViewById(R.id.spthana)));*/

		String[] thanaList = loadAutoComplete(strSQL);
		ArrayAdapter adapter2 = new ArrayAdapter(con,android.R.layout.simple_dropdown_item_1line,thanaList);

		autoDist.setAdapter(adapter);
		autoDist.setThreshold(1);


		autoThana.setAdapter(adapter2);
		autoThana.setThreshold(1);

		ArrayList<String> ids = new ArrayList<String>();
		/*ids.add("");
		ids.add("1 : Urban");
		ids.add("2 : Rural");*/

	/*	CommonStaticClass.FillCombo(thisactivity, dbHelper, dist,
				((Spinner) v.findViewById(R.id.spdist)));*/

		/*String s = String.format(
				"select DistCode from frmrHospital where HosID='%s'",
				CommonStaticClass.userSpecificId);*/
		String s = String.format(
				"select DistCode from Hospital where HosID='%s'",
				CommonStaticClass.userSpecificId);
		Cursor c = dbHelper.getQueryCursor(dist);



	/*	String sqld = "Select PSCode from tblLinelist where dataid = '"
				+ CommonStaticClass.dataId + "'";
		Cursor curd = null;

		try {

			curd = dbHelper.getQueryCursor(sqld);

			if (curd.getCount() > 0) {

				if (curd.moveToFirst()) {



						*//*CommonStaticClass.SetSpinnerValue(
								thisactivity,
								((Spinner) v
										.findViewById(R.id.spthana)),
								cur.getString(0));*//*

					autoThana.setText(curd.getString(0)+" : "+CommonStaticClass.GetName(curd.getString(0),"PoliceStation",dbHelper));


				}

			}

		} catch (Exception e) {

			// TODO: handle exception

		}
		finally {
			if(curd != null)
				curd.close();
		}*/


		autoDist.setOnItemClickListener(new AdapterView.OnItemClickListener() {


			public void onItemClick(AdapterView<?> parent, View arg1, int pos,
									long id) {
				String val = autoDist.getText().toString();
				if (val.length() > 0) {
					sResCode =autoDist.getText().toString().trim()
							.substring(
									0,
									(autoDist.getText().toString().trim()
											.lastIndexOf(":")));

					/*String strSQL = String
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
							+ " from frmrPoliceStation where DistCode = '%s' ORDER BY PSName"
							+ "", sResCode);*/

					/*String strSQL = String
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
									+ "", sResCode);*/

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
					/*CommonStaticClass.FillCombo(thisactivity, dbHelper,
					strSQL,
							((Spinner) v.findViewById(R.id.spthana)));*/

					String[] thanaList = loadAutoComplete(strSQL);
					ArrayAdapter adapter2 = new ArrayAdapter(con,android.R.layout.simple_dropdown_item_1line,thanaList);

					autoThana.setAdapter(adapter2);
					autoThana.setThreshold(1);


					/*String sql = "Select PSCode from tblLinelist where dataid = '"
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
					finally {
						if(cur != null)
							cur.close();
					}*/
					// String strSQL =
					// "Select PSCode, PSName from PoliceStation where DistCode = '"
					// + sResCode + "' Order By PSName";

				}

				else {

				}


			}
		});

		autoThana.setOnItemClickListener(new AdapterView.OnItemClickListener() {


			public void onItemClick(AdapterView<?> parent, View arg1, int pos,
									long id) {
				String val = autoThana.getText().toString();
				if (val.length() > 0) {
					sResCode =autoThana.getText().toString().trim()
							.substring(
									0,
									(autoThana.getText().toString().trim()
											.lastIndexOf(":")));

					/*String strSQL = String
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
							+ " from frmrPoliceStation where DistCode = '%s' ORDER BY PSName"
							+ "", sResCode);*/
					String code = CommonStaticClass.Getcode2("DistCode", "PoliceStation", "PSCode", sResCode, dbHelper).trim();


					String strSQL = String
									.format("SELECT ("
											+ ""
											+ "DistCode"
											+ ""
											+ "|| "
											+ ""
											+ "' : '"
									+ " || "
									+ "DistName"
									+ ") AS "
									+ "O"
									+ " from District where DistCode = '%s'", code);


					Cursor cur = null;

					try {

						cur = dbHelper.getQueryCursor(strSQL);

						if (cur.getCount() > 0) {

							if (cur.moveToFirst()) {


								autoDist.setText(cur.getString(0));



							}

						}

					} catch (Exception e) {

						// TODO: handle exception

					}
					finally {
						if(cur != null)
							cur.close();
					}
					// String strSQL =
					// "Select PSCode, PSName from PoliceStation where DistCode = '"
					// + sResCode + "' Order By PSName";

				}

				else {

				}


			}
		});



		/*((Spinner) v.findViewById(R.id.spdist))
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

							*//*String strSQL = String
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
											+ " from frmrPoliceStation where DistCode = '%s' ORDER BY PSName"
											+ "", sResCode);*//*

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

							*//*CommonStaticClass.FillCombo(thisactivity, dbHelper,
									strSQL,
									((Spinner) v.findViewById(R.id.spthana)));*//*

							String sql = "Select PSCode from tblLinelist where dataid = '"
									+ CommonStaticClass.dataId + "'";
							Cursor cur = null;

							try {

								cur = dbHelper.getQueryCursor(sql);

								if (cur.getCount() > 0) {

									if (cur.moveToFirst()) {

										do {

											*//*CommonStaticClass.SetSpinnerValue(
													thisactivity,
													((Spinner) v
															.findViewById(R.id.spthana)),
													cur.getString(0));*//*

										} while (cur.moveToNext());

									}

								}

							} catch (Exception e) {

								// TODO: handle exception

							}
							finally {
								if(cur != null)
								cur.close();
							}
							// String strSQL =
							// "Select PSCode, PSName from PoliceStation where DistCode = '"
							// + sResCode + "' Order By PSName";

						}

					}


					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});*/

		/*if (c.getCount() > 0) {

			if (c.moveToFirst()) {

				do {

					CommonStaticClass.SetSpinnerValue(thisactivity,
							((Spinner) v.findViewById(R.id.spdist)),
							CommonStaticClass.CheckCursorValue(c, "DistCode"));

				} while (c.moveToNext());

			}
		}*/

		/*CommonStaticClass.FillCombo(thisactivity, dbHelper, occu,
				((Spinner) v.findViewById(R.id.spoccupation)));
		CommonStaticClass.FillCombo(thisactivity, ids,
				((Spinner) v.findViewById(R.id.splocation)));*/

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


		((Spinner) v.findViewById(R.id.spyear))
				.setOnItemSelectedListener(new OnItemSelectedListener() {


					public void onItemSelected(AdapterView<?> parent,
											   View view, int pos, long id) {

						if (parent.getItemAtPosition(pos).toString().length() > 0) {

							sResCode = parent
									.getItemAtPosition(pos)
									.toString();

							if (sResCode.length() > 0) {
								if (Integer.parseInt(sResCode) < 5) {
									((Spinner) v.findViewById(R.id.spmonth))
											.setVisibility(view.VISIBLE);
									((Spinner) v.findViewById(R.id.spday))
											.setVisibility(view.VISIBLE);

								} else {
									((Spinner) v.findViewById(R.id.spmonth))
											.setSelection(0);
									((Spinner) v.findViewById(R.id.spday))
											.setSelection(0);
									((Spinner) v.findViewById(R.id.spday))
											.setVisibility(view.INVISIBLE);
									((Spinner) v.findViewById(R.id.spmonth))
											.setVisibility(view.INVISIBLE);
									((Spinner) v.findViewById(R.id.spday))
											.setVisibility(view.INVISIBLE);
								}
							}

						}

					}


					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});
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

		String sql = "Select Name, HHHead, AgeY, AgeM, AgeD, Sex, Vill, UnionName, PSCode, DistCode, Phone,Phone1 from tblLinelist where dataid = '"
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

						CommonStaticClass.SetSpinnerValueFrmString(
								thisactivity,
								((Spinner) v.findViewById(R.id.spyear)),
								cur.getString(2));

						CommonStaticClass.SetSpinnerValueFrmString(
								thisactivity,
								((Spinner) v.findViewById(R.id.spmonth)),
								cur.getString(3));

						CommonStaticClass.SetSpinnerValueFrmString(
								thisactivity,
								((Spinner) v.findViewById(R.id.spday)),
								cur.getString(4));

						CommonStaticClass.SetSpinnerValue(thisactivity,
								((Spinner) v.findViewById(R.id.spsex)),
								cur.getString(5));

						((EditText) v.findViewById(R.id.txtVillage))
								.setText(cur.getString(6));
						((EditText) v.findViewById(R.id.txtUnion)).setText(cur
								.getString(7));
						/*CommonStaticClass.SetSpinnerValue(thisactivity,
								((Spinner) v.findViewById(R.id.spthana)),
								cur.getString(8));

						CommonStaticClass.SetSpinnerValue(thisactivity,
								((Spinner) v.findViewById(R.id.spdist)),
								cur.getString(9));*/

						autoThana.setText(cur.getString(8)+ " : "+CommonStaticClass.GetName2("PSName","PoliceStation","PSCode",cur.getString(8).trim(),dbHelper));
						autoDist.setText(cur.getString(9)+ " : "+CommonStaticClass.GetName2("DistName", "District", "DistCode", cur.getString(9).trim(), dbHelper));





						((EditText) v.findViewById(R.id.txtphone1)).setText(cur
								.getString(10));
						((EditText) v.findViewById(R.id.txtphone2)).setText(cur
								.getString(11));





					} while (cur.moveToNext());

				}

			}

		} catch (Exception e) {

			// TODO: handle exception

		}
		finally {
			if(cur != null)
				cur.close();
			if(c != null)
				c.close();
		}

		/*if (CommonStaticClass.getSkip("Comp", "tblLinelist", dbHelper)
				.equalsIgnoreCase("1")) {
			((Spinner) v.findViewById(R.id.spmonth)).setVisibility(View.GONE);
			((Spinner) v.findViewById(R.id.spday)).setVisibility(View.GONE);

			((TextView) v.findViewById(R.id.lblday)).setVisibility(View.GONE);
			((TextView) v.findViewById(R.id.lblmonth)).setVisibility(View.GONE);
		}*/

	}

	private String[] loadAutoComplete(String s) {
		String[] list = null;
		Cursor mCursor = null;
		try {

			mCursor = dbHelper.getQueryCursor(s);

			if (mCursor.getCount() > 0) {
				list = new String[mCursor.getCount()];
				int i = 0;
				if (mCursor.moveToFirst()) {

					do {
						list[i] = mCursor.getString(0);
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

		} finally {
			if (mCursor != null)
				mCursor.close();

		}
		return list;
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

			/*String HCWorker = "", PlWorker = "", Plraising = "", occupation = "";
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
			}*/
			/*if (((Spinner) vg.findViewById(R.id.spyear)).getSelectedItem()
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
				} *//*
				 * else {
				 *
				 * if(Integer.parseInt(((Spinner) vg.findViewById(R.id.spyear))
				 * .getSelectedItem().toString())<=10) { occupation = "14";}
				 *//*

			} else {
				occupation = "";
			}*/

			sql = String
					.format("Update %s Set Name = '%s',  HHHead='%s', " +
									"AgeY='%s', AgeM='%s', AgeD='%s', Sex ='%s', Vill= '%s', UnionName='%s', " +
									"PSCode= '%s', DistCode='%s', Phone='%s', Phone1='%s' "
									+ " WHERE dataid = '%s'",
							CommonStaticClass
									.GetTableName(CommonStaticClass.questionMap
											.get(CommonStaticClass.currentSLNo)
											.getQvar()),

							((EditText) vg.findViewById(R.id.txtName))
									.getText().toString(),
							((EditText) vg.findViewById(R.id.txtHHHead))
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
							((EditText) vg.findViewById(R.id.txtVillage))
									.getText().toString(),
							((EditText) vg.findViewById(R.id.txtUnion))
									.getText().toString(),
							autoThana.getText().toString().substring( 0,
									autoThana.getText().toString() .lastIndexOf(":") - 1).trim()
							,
							autoDist.getText().toString().substring( 0,
									autoDist.getText().toString() .lastIndexOf(":") - 1).trim(),
							/*CommonStaticClass.GetSpinnerValue(((Spinner) vg
									.findViewById(R.id.spthana)))
							,*/
							/*
							 * ((Spinner) vg.findViewById(R.id.spthana))
							 * .getSelectedItem() .toString() .substring( 0,
							 * ((Spinner) vg .findViewById(R.id.spthana))
							 * .getSelectedItem() .toString() .lastIndexOf(":")
							 * - 1),
							 */

							/*CommonStaticClass.GetSpinnerValue(((Spinner) vg
									.findViewById(R.id.spdist))),*/
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



							((EditText) vg.findViewById(R.id.txtphone1))
									.getText().toString(),
							((EditText) vg.findViewById(R.id.txtphone2))
									.getText().toString(), CommonStaticClass.dataId);


							/*
							 * ((Spinner) vg.findViewById(R.id.spoccupation))
							 * .getSelectedItem() .toString() .substring( 0,
							 * ((Spinner) vg .findViewById(R.id.spoccupation))
							 * .getSelectedItem() .toString() .lastIndexOf(":")
							 * - 1)
							 */



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
		/*if (((Spinner) v.findViewById(R.id.spdist)).getSelectedItem()
				.toString().length() <= 0) {
			CommonStaticClass.showMyAlert(thisactivity, "Missing info",
					"Please select district information");

			return false;
		}*/

		// Checking Police station information given or not
		/*if (((Spinner) v.findViewById(R.id.spthana)).getSelectedItem()
				.toString().length() <= 0) {

			CommonStaticClass.showMyAlert(thisactivity, "Missing info",
					"Please select police station information");

			return false;
		}*/

		if (autoDist.getText().toString().length() <= 0) {
			CommonStaticClass.showMyAlert(thisactivity, "Missing info",
					"Please select district information");

			return false;
		}

		// Checking Police station information given or not
		if (autoThana.getText().toString().length() <= 0) {

			CommonStaticClass.showMyAlert(thisactivity, "Missing info",
					"Please select police station information");

			return false;
		}

		return true;

	}

	/*private ArrayList<String> optionList = null;
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
		optionCodeList.add(0);

		for (int i = 0; i < op.codeList.size(); i++) {
			
			listvalues.add("");
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
			final EditText spinner = new EditText(this);
			layoutParamForSpin.weight = 1;
			ln.addView(spinner, 0, layoutParamForSpin);
			spinner.setId(i);
			spinner.setVisibility(View.INVISIBLE);
			ln.addView(checkButton, 0, layoutParamForcheck);

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

	}*/

	/*private boolean doFillFrmMultipleCheckDate(Cursor mCursor1,
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
	}*/

	/*private void spinnerVisibleButNotSeletedFrmMultipleCheckDate(
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
	}*/

	/*private boolean checkIfSingleOptionIsCheckedFrmMultipleCheckDate() {
		for (int i = 0; i < listvalues.size(); i++) {
			if (!(listvalues.get(i).equalsIgnoreCase("-1"))) {
				return true;
			}

		}
		return false;
	}*/

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

	/*private void selectCheckAndDateFrmMultipleCheckDate(String inColumn,
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

	}*/

	/*private void updateTableDataFrmMultipleCheckDate() {
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

					
					 * preserveState();
					 * CommonStaticClass.findOutNextSLNo(CommonStaticClass
					 * .questionMap
					 * .get(CommonStaticClass.currentSLNo).getQvar(),
					 * CommonStaticClass
					 * .questionMap.get(CommonStaticClass.currentSLNo
					 * ).getQnext1()); CommonStaticClass.nextQuestion(con);
					 
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

	}*/

	/*TextView Slno;
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
	EditText etother;*/

	/*private void loadGuiFrmq124(ViewGroup vg) {

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

	}*/

	/*private void loadGuifrmnumericwithrdbtn(final ViewGroup v) {
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

	}*/
	

	//frmcamera
	private void loadGuifrmcamera(final ViewGroup v) {
		// TODO Auto-generated method stub
	
		qName = CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar();
		qqq = (TextView) v.findViewById(R.id.qqq);
		
		mCurrentPhotoPath = "";

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
		
		mImageView = (TouchImageView) findViewById(R.id.imageView1);
		Button picSBtn = (Button) findViewById(R.id.btnIntendS);
		
		
		setBtnListenerOrDisable( 
				picSBtn, 
				mTakePicSOnClickListener,
				MediaStore.ACTION_IMAGE_CAPTURE
		);
		
		
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
				mCursor1.moveToFirst();
				mCurrentPhotoPath = mCursor1.getString(mCursor1.getColumnIndex(qName));
				setPic();
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
	
				updateTableDataFrmCamera();
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
	private void updateTableDataFrmCamera() {
		// TODO Auto-generated method stub

		
		
			// Validation & skip definition
			
			String sql = "";
			
				sql = "UPDATE "
						+ CommonStaticClass.questionMap.get(
								CommonStaticClass.currentSLNo).getTablename()
						+ " SET " + qName + "='" + mCurrentPhotoPath
						+ "' where dataid='" + CommonStaticClass.dataId + "'";
			

							
			if (dbHelper.executeDMLQuery(sql)) {
				
				
					CommonStaticClass.findOutNextSLNo(
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQvar(),
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQnext1());
					CommonStaticClass.nextQuestion(ParentActivity.this);
				
			}
		 else {
			CommonStaticClass.showMyAlert(con, "Not Correct",
					"Please put correct information in the field");
		}
	}
	
		
		private void setBtnListenerOrDisable( 
				Button btn, 
				Button.OnClickListener onClickListener,
				String intentName
		) {
			if (isIntentAvailable(thisactivity, intentName)) {
				btn.setOnClickListener(onClickListener);        	
			} else {
				btn.setText( "Cannot " + btn.getText());
				btn.setClickable(false);
			}
		}
		
		public static boolean isIntentAvailable(Context context, String action) {
			final PackageManager packageManager = context.getPackageManager();
			final Intent intent = new Intent(action);
			List<ResolveInfo> list =
				packageManager.queryIntentActivities(intent,
						PackageManager.MATCH_DEFAULT_ONLY);
			return list.size() > 0;
		}
		
		Button.OnClickListener mTakePicSOnClickListener = 
				new Button.OnClickListener() {
				
				public void onClick(View v) {
					Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					
					File f = null;
					
					try {
						f = setUpPhotoFile();
						mCurrentPhotoPath = f.getAbsolutePath();
						takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
					} catch (IOException e) {
						e.printStackTrace();
						f = null;
						mCurrentPhotoPath = null;
					}

					startActivityForResult(takePictureIntent, ACTION_TAKE_PHOTO_S);

				}
			};
		
		
		private void handleSmallCameraPhoto(Intent intent) {
				if (mCurrentPhotoPath != null) {
					setPic();
//					galleryAddPic();
//					mCurrentPhotoPath = null;
				}
				
		}
			
		private void setPic() {

				/* There isn't enough memory to open up more than a couple camera photos */
				/* So pre-scale the target bitmap into which the file is decoded */

				/* Get the size of the ImageView */
				int targetW = mImageView.getWidth();
				int targetH = mImageView.getHeight();

				/* Get the size of the image */
				BitmapFactory.Options bmOptions = new BitmapFactory.Options();
				bmOptions.inJustDecodeBounds = true;
				BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
				int photoW = bmOptions.outWidth;
				int photoH = bmOptions.outHeight;
				
				/* Figure out which way needs to be reduced less */
				int scaleFactor = 1;
				if ((targetW > 0) || (targetH > 0)) {
					scaleFactor = Math.min(photoW/targetW, photoH/targetH);	
				}

				/* Set bitmap options to scale the image decode target */
				bmOptions.inJustDecodeBounds = false;
				bmOptions.inSampleSize = scaleFactor;
				bmOptions.inPurgeable = true;

				/* Decode the JPEG file into a Bitmap */
				Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
				
//				ExifInterface exif;
//				try {
//					Cursor cur=managedQuery(Media.EXTERNAL_CONTENT_URI, null, "_data=?", new String[]{mCurrentPhotoPath}, null);
//					cur.moveToPosition(0);
//					int i=cur.getInt(cur.getColumnIndex(MediaStore.Images.Media.ORIENTATION));
//					exif = new ExifInterface(mCurrentPhotoPath);
//					String exifOrientation = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
//					int orientation=exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,-1);
//					Log.i("Orientation", String.valueOf(orientation));
////					exif.saveAttributes(ExifInterface.TAG_ORIENTATION,"90");
//					Log.d("Photo", exifOrientation);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}     //Since API Level 5
			

				/* Associate the Bitmap to the ImageView */
				mImageView.setImageBitmap(bitmap);
				
				mImageView.setVisibility(View.VISIBLE);
				
				mImageView.setOnTouchImageViewListener(new TouchImageView.OnTouchImageViewListener() {
					
					public void onMove() {
						PointF point = mImageView.getScrollPosition();
						RectF rect = mImageView.getZoomedRect();
						float currentZoom = mImageView.getCurrentZoom();
						boolean isZoomed = mImageView.isZoomed();
						
					}
				});
				
				
			}	
		
		private File getAlbumDir() {
			File storageDir = null;
			String StroagePath = "";

			if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
				StroagePath = Environment.getExternalStorageDirectory().getAbsoluteFile().toString()+"/icddrbDB/"+CommonStaticClass.DB;
				storageDir=new File(StroagePath);
				if (storageDir != null) {
					if (! storageDir.mkdirs()) {
						if (! storageDir.exists()){
							Log.d("CameraSample", "failed to create directory");
							return null;
						}
					}
				}
				
			} else {
				Log.v(getString(R.string.app_name), "External storage is not mounted READ/WRITE.");
			}
			
			return storageDir;
		}
		
		private File createImageFile() throws IOException {
			// Create an image file name
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String imageFileName = CommonStaticClass.dataId+ "_" + CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQvar()+ "_"+ timeStamp;
			File albumF = getAlbumDir();
			//File imageF = File.createTempFile(imageFileName, JPEG_FILE_SUFFIX, albumF);
			File imageF = new File(albumF.getAbsolutePath()+"/"+imageFileName+JPEG_FILE_SUFFIX);
			
			return imageF;
		}
		private File setUpPhotoFile() throws IOException {
			
			File f = createImageFile();
			mCurrentPhotoPath = f.getAbsolutePath();
			
			return f;
		}

		
	
	


	Button btnAdults, btnAdultsDischarge, btnNeonates, btnNeonatesDischarge,
			btnHome;

	/*private void loadGuifrmfindsection(final ViewGroup v) {
		resetViewGroup(v);

		if (CommonStaticClass.langBng) {
			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0) {
				Typeface font = Typeface.createFromAsset(getAssets(),
						"Siyam Rupali ANSI.ttf");
				qqq.setTypeface(font);
			}

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

		if (CommonStaticClass.langBng) {
			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0) {
				Typeface font = Typeface.createFromAsset(getAssets(),
						"Siyam Rupali ANSI.ttf");
				qqq.setTypeface(font);
			}
		}
		int index = CommonStaticClass.SLNOSTACK.indexOf(2);
		int siz = CommonStaticClass.SLNOSTACK.size();
		if (siz > index + 1) {
			CommonStaticClass.SLNOSTACK.subList(index + 1, siz).clear();
		}

		
		 * String sqlD = "select comments from tblLinelist where dataid= '" +
		 * CommonStaticClass.dataId + "'"; String cmnts = ""; Cursor dCursor =
		 * dbHelper.getQueryCursor(sqlD); if (dCursor.moveToFirst()) { do {
		 * cmnts = dCursor.getString(dCursor.getColumnIndex("comments")); }
		 * while (dCursor.moveToNext()); }
		 * 
		 * if (!CommonStaticClass.isNullOrEmpty(cmnts)) { if
		 * (!cmnts.trim().equalsIgnoreCase("")) { txtcomnt.setText(cmnts); } }
		 
		btnAdults = (Button) v.findViewById(R.id.btnAdults);
		btnAdults.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				CommonStaticClass.currentSLNo = 3;
				
				 * CommonStaticClass.findOutNextSLNo(
				 * CommonStaticClass.questionMap.get(
				 * CommonStaticClass.currentSLNo).getQvar(),
				 * CommonStaticClass.questionMap.get(
				 * CommonStaticClass.currentSLNo).getQnext1());
				 
				CommonStaticClass.nextQuestion(ParentActivity.this);
			}
		});
		btnAdultsDischarge = (Button) v.findViewById(R.id.btnAdultsDischarge);
		btnAdultsDischarge.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				CommonStaticClass.currentSLNo = 137;
				CommonStaticClass.nextQuestion(ParentActivity.this);
			}
		});
		btnNeonates = (Button) v.findViewById(R.id.btnNeonates);
		btnNeonates.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				CommonStaticClass.currentSLNo = 155;
				CommonStaticClass.nextQuestion(ParentActivity.this);
			}
		});
		btnNeonatesDischarge = (Button) v
				.findViewById(R.id.btnNeonatesDischarge);
		btnNeonatesDischarge.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				CommonStaticClass.currentSLNo = 187;
				CommonStaticClass.nextQuestion(ParentActivity.this);
			}
		});

		btnHome = (Button) v.findViewById(R.id.btnHome);
		btnHome.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				CommonStaticClass.mode = "";
				finish();
			}
		});

		
		 * btncmnt = (Button) v.findViewById(R.id.btncmnt);
		 * btncmnt.setOnClickListener(new View.OnClickListener() {
		 * 
		 * public void onClick(View v) { if
		 * (!CommonStaticClass.isNullOrEmpty(txtcomnt.getText() .toString())) {
		 * if (!txtcomnt.getText().toString().trim() .equalsIgnoreCase("")) {
		 * String cmntSQL = "Update tblLinelist set comments = '" +
		 * txtcomnt.getText().toString() + "' where dataid= '" +
		 * CommonStaticClass.dataId + "'";
		 * 
		 * if (dbHelper.executeDMLQuery(cmntSQL)) {
		 * CommonStaticClass.showMyAlert(con, "Message",
		 * "Note saved successfully"); } } } } });
		 

	}
*/
	/*TextView txtname, txtSlno;
	Spinner cobslno;
	ArrayList<String> IDList;
	ArrayAdapter<String> LKadapter;

	private void loadGuifrmneonatelinfo(final ViewGroup v) {
		resetViewGroup(v);
		if (!CommonStaticClass.HouseholdCode.equalsIgnoreCase("")) {
			CommonStaticClass.dataId = CommonStaticClass.HouseholdCode;
			CommonStaticClass.HouseholdCode = "";
		}
		qqq = (TextView) v.findViewById(R.id.qqq);
		cobslno = (Spinner) v.findViewById(R.id.cobslno);
		txtname = (TextView) v.findViewById(R.id.txtname);
		txtSlno = (TextView) v.findViewById(R.id.txtSlno);

		if (CommonStaticClass.langBng) {
			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0) {
				Typeface font = Typeface.createFromAsset(getAssets(),
						"Siyam Rupali ANSI.ttf");
				qqq.setTypeface(font);
				txtSlno.setTypeface(font);
				txtSlno.setText("AskMÖnbKvixi AvBwW");

			}

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
			txtSlno.setTypeface(null);
			txtSlno.setText("Neonates ID");
		}

		// TODO Auto-generated method stub
		qName = CommonStaticClass.questionMap
				.get(CommonStaticClass.currentSLNo).getQvar();

		if (CommonStaticClass.langBng) {
			if (CommonStaticClass.questionMap
					.get(CommonStaticClass.currentSLNo).getQdescbng().length() > 0) {
				Typeface font = Typeface.createFromAsset(getAssets(),
						"Siyam Rupali ANSI.ttf");
				qqq.setTypeface(font);
			}
		}

		
		 * int index = CommonStaticClass.SLNOSTACK.indexOf(2); int siz =
		 * CommonStaticClass.SLNOSTACK.size(); if (siz > index + 1) {
		 * CommonStaticClass.SLNOSTACK.subList(index + 1, siz).clear(); }
		 

		cobslno.setAdapter(null);
		IDList = new ArrayList<String>();
		IDList.add(".....Select Neonates ID.....");
		
		 * String sqlD =
		 * "select ParticipantID from tblParticipantInfo where dataid= '" +
		 * CommonStaticClass.dataid_store + "'  and IndividualQ = 1";
		 

		String sqlD = "select count (NeonateID) as totalID from tblNeonate where MotherID = '"
				+ CommonStaticClass.dataid_store + "'";

		int cnt = 0;
		Cursor dCursor = dbHelper.getQueryCursor(sqlD);
		if (dCursor.moveToFirst()) {
			do {
				if (qName.equalsIgnoreCase("NeonateID")) {
					cnt = Integer.parseInt(dCursor.getString(dCursor
							.getColumnIndex("totalID"))) + 1;
				} else {
					cnt = Integer.parseInt(dCursor.getString(dCursor
							.getColumnIndex("totalID")));
				}
			} while (dCursor.moveToNext());
		}

		for (int i = 1; i <= cnt; i++) {
			String HHVal = "";
			if (String.valueOf(i).length() == 1) {
				HHVal = "0" + String.valueOf(i);
				IDList.add(HHVal);
			} else {
				IDList.add(String.valueOf(i));
			}
		}

		LKadapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, IDList);
		LKadapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cobslno.setAdapter(LKadapter);

		
		 * cobslno.setOnItemSelectedListener(new
		 * AdapterView.OnItemSelectedListener() { public void
		 * onItemSelected(AdapterView<?> parent, View view, int i, long l) { if
		 * (i != 0) { String itemY = parent.getItemAtPosition(i).toString();
		 * 
		 * String sqlN = "select Name from tblParticipantInfo where dataid= '" +
		 * CommonStaticClass.dataid_store + "'  and ParticipantID = " + itemY +
		 * "";
		 * 
		 * Cursor dCursor = dbHelper.getQueryCursor(sqlN); if
		 * (dCursor.moveToFirst()) { do { String NM = dCursor.getString(dCursor
		 * .getColumnIndex("Name")); txtname.setText("Name: " + NM); } while
		 * (dCursor.moveToNext()); }
		 * 
		 * else { txtname.setText(""); }
		 * 
		 * } else { txtname.setText(""); }
		 * 
		 * }
		 * 
		 * @Override public void onNothingSelected(AdapterView<?> parent) {
		 * 
		 * } })
		 ;
		//
		saveNxtButton = (Button) v.findViewById(R.id.saveNxtButton);

		saveNxtButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				if (cobslno.getSelectedItemPosition() == 0) {
					return;
				}
				// ///////////////////////////////////////////////////////////

				progressDialog = ProgressDialog.show(con, "Questions",
						"Please wait while loading questioniare");
				new Thread() {

					public void run() {
						String PID = cobslno.getSelectedItem().toString();
						String DataID = CommonStaticClass.dataId + "" + PID;

						int count = 0;
						String sqlD = "select count() as Total from tblNeonate where dataid= '"
								+ DataID + "'";

						Cursor dCursor = dbHelper.getQueryCursor(sqlD);
						if (dCursor.moveToFirst()) {
							do {
								count = Integer.valueOf(dCursor
										.getString(dCursor
												.getColumnIndex("Total")));
							} while (dCursor.moveToNext());
						}
						if (qName.equalsIgnoreCase("DNeonateID")) {
							String Randsql = "update tblNeonate set DNeonateID ="
									+ DataID
									+ " where dataid = '"
									+ DataID
									+ "'";

							if (dbHelper.executeDMLQuery(Randsql)) {
								Log.e("update sql", String.valueOf(Randsql));
							}
						}
						if (count == 0) {

							String HosID = DataID.substring(0, 1);
							String InssertSQL1 = "Insert into tblNeonate (dataid,hospitalID,MotherID,NeonateID,assetid,EntryBy,EntryDate)"
									+ "VALUES ('"
									+ DataID
									+ "','"
									+ HosID
									+ "','"
									+ CommonStaticClass.dataId
									+ "','"
									+ PID
									+ "','"
									+ CommonStaticClass.AssetID
									+ "','"
									+ CommonStaticClass.userSpecificId
									+ "','"
									+ CommonStaticClass.GetDate()
									+ "')";

							if (dbHelper.executeDMLQuery(InssertSQL1)) {
								Log.e("Save2", "Save");
								CommonStaticClass.HouseholdCode = CommonStaticClass.dataId;
								CommonStaticClass.dataId = DataID;
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
						} else {
							CommonStaticClass.HouseholdCode = CommonStaticClass.dataId;
							CommonStaticClass.dataId = DataID;
							CommonStaticClass.findOutNextSLNo(
									CommonStaticClass.questionMap.get(
											CommonStaticClass.currentSLNo)
											.getQvar(),
									CommonStaticClass.questionMap.get(
											CommonStaticClass.currentSLNo)
											.getQnext1());
							CommonStaticClass.nextQuestion(ParentActivity.this);
						}
						progressDialog.dismiss();
					}
				}.start();

				
				 * String PID = cobslno.getSelectedItem().toString(); String
				 * DataID = CommonStaticClass.dataId + "-" + PID;
				 * 
				 * int count = 0; String sqlD =
				 * "select count() as Total from tblIndividualInfo where dataid= '"
				 * + DataID + "'";
				 * 
				 * Cursor dCursor = dbHelper.getQueryCursor(sqlD); if
				 * (dCursor.moveToFirst()) { do { count =
				 * Integer.valueOf(dCursor.getString(dCursor
				 * .getColumnIndex("Total"))); } while (dCursor.moveToNext()); }
				 * if (count == 0) {
				 * 
				 * String InssertSQL1 =
				 * "Insert into tblIndividualInfo (dataid,HouseholdCode,ParticipantID,assetid,EntryBy,EntryDate)"
				 * + "VALUES ('" + DataID + "','" + CommonStaticClass.dataId +
				 * "','" + PID + "','" + CommonStaticClass.AssetID + "','" +
				 * CommonStaticClass.userSpecificId + "','" +
				 * CommonStaticClass.GetDate() + "')"; String InssertSQL2 =
				 * "Insert into tblpast4days (dataid,HouseholdCode,ParticipantID,assetid,EntryBy,EntryDate)"
				 * + "VALUES ('" + DataID + "','" + CommonStaticClass.dataId +
				 * "','" + PID + "','" + CommonStaticClass.AssetID + "','" +
				 * CommonStaticClass.userSpecificId + "','" +
				 * CommonStaticClass.GetDate() + "')"; if
				 * (dbHelper.executeDMLQuery(InssertSQL1)) { Log.e("Save1",
				 * "Save");
				 * 
				 * int Rand = 0; String Randsql =
				 * "SELECT ID FROM tblRandom ORDER BY RANDOM() LIMIT 1";
				 * 
				 * Cursor RanddCursor = dbHelper.getQueryCursor(Randsql); if
				 * (RanddCursor.moveToFirst()) { do { Rand =
				 * Integer.valueOf(RanddCursor
				 * .getString(RanddCursor.getColumnIndex("ID"))); } while
				 * (RanddCursor.moveToNext()); }
				 * 
				 * Randsql = "update tblIndividualInfo set randvalue =" + Rand +
				 * " where dataid = '" + DataID + "'";
				 * 
				 * if (dbHelper.executeDMLQuery(Randsql)) { Log.e("Rand",
				 * String.valueOf(Rand)); }
				 * 
				 * }
				 * 
				 * if (dbHelper.executeDMLQuery(InssertSQL2)) { Log.e("Save2",
				 * "Save"); CommonStaticClass.HouseholdCode =
				 * CommonStaticClass.dataId; CommonStaticClass.dataId = DataID;
				 * CommonStaticClass.findOutNextSLNo(
				 * CommonStaticClass.questionMap.get(
				 * CommonStaticClass.currentSLNo) .getQvar(),
				 * CommonStaticClass.questionMap.get(
				 * CommonStaticClass.currentSLNo) .getQnext1());
				 * CommonStaticClass.nextQuestion(ParentActivity.this); } } else
				 * { CommonStaticClass.HouseholdCode = CommonStaticClass.dataId;
				 * CommonStaticClass.dataId = DataID;
				 * CommonStaticClass.findOutNextSLNo(
				 * CommonStaticClass.questionMap.get(
				 * CommonStaticClass.currentSLNo).getQvar(),
				 * CommonStaticClass.questionMap.get(
				 * CommonStaticClass.currentSLNo).getQnext1());
				 * CommonStaticClass.nextQuestion(ParentActivity.this); }
				 }

		});

		prevButton = (Button) v.findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				
				 * CommonStaticClass.dataId = CommonStaticClass.HouseholdCode;
				 * CommonStaticClass.HouseholdCode = "";
				 

				userPressedPrevious(ParentActivity.this);
			}
		});

		clButton = (Button) v.findViewById(R.id.clButton);
		clButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View vv) {
				// TODO Auto-generated method stub
				resetViewGroup((ViewGroup) v);
			}
		});
	}*/

	@Override
	public void onBackPressed() {
		// if(formFlipper.getDisplayedChild()!=5){
		userPressedPrevious(this);
		// }
	}

	public void FillSpinnerOther(String sql, Spinner spnr) {

	}
	/**/

}
