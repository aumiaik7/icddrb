package com.icddrb.app.ccdtemplate;
import java.util.Calendar;

import com.icddrb.app.ccdtemplate.R;
import com.icddrb.app.ccdtemplate.questions.ParentActivity;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

import net.sqlcipher.Cursor;

public class subedit extends BaseActivity {

	private Button btnEAdults, btnEAdultsDischarge, btnENeonates, btnENeonatesDischarge, btnHome;
	private ProgressDialog progressDialog;
	private Context con;
	private LinearLayout subEditButtonHolder;
	DisplayMetrics dm;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subedit);
		con = this;
		dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		setTheme(R.style.AppTheme);
		
		subEditButtonHolder = (LinearLayout) this.findViewById(R.id.buttonHolder);
		subEditButtonHolder.removeAllViews();
		

	
		if(CommonStaticClass.secMap2.isEmpty())
			lookForSections();
		
		for(int i = 0; i < CommonStaticClass.secMap2.size() ; i++)
		{

			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
		            LinearLayout.LayoutParams.MATCH_PARENT,
		            LinearLayout.LayoutParams.WRAP_CONTENT);
		    Button btn = new Button(this);
		    btn.setId(i);
		    final int id_ = btn.getId();
		    btn.setText(CommonStaticClass.secMap1.get(i));
		    //btn.setBackgroundColor(Color.rgb(70, 80, 90));
		    btn.setPadding(5, 5, 5, 5);
		    subEditButtonHolder.addView(btn, params);
		    btn = ((Button) findViewById(id_));
		    btn.setOnClickListener(new View.OnClickListener() {
		        public void onClick(View view) {
		        	CommonStaticClass.mode = CommonStaticClass.EDITMODE;
		        	CommonStaticClass.sectionStart = CommonStaticClass.secMap2.get(id_);
		        	if(id_ == CommonStaticClass.secMap2.size() -1 )
		        	{
		        		CommonStaticClass.sectionEnd = getlastSerialNo();
		        	}
		        	else
		        		CommonStaticClass.sectionEnd = 	CommonStaticClass.secMap2.get(id_+1)-1;	        	
		    		Intent i = new Intent();
		    		i.setClassName(CommonStaticClass.pName, CommonStaticClass.pName
		    				+ ".EditEntry");
		    		startActivity(i);
		        }

				
		    });

		
			// }
		
		}
		
		
	}
	private void lookForSections() {
		// TODO Auto-generated method stub
		String sqlForSec = "Select SLNo,Qdesceng from tblQuestion where Qvar like 'sec%' order by SLNo";

		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sqlForSec);
			if (mCursor1.moveToFirst()) {
				do {
					Log.e("secMap1 ", mCursor1.getString((mCursor1
							.getColumnIndex("Qdesceng"))));
					CommonStaticClass.secMap1.add(mCursor1
							.getString((mCursor1.getColumnIndex("Qdesceng"))));
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
		
	}

	private int getlastSerialNo() {
		String sqlForSec = "Select max(SLNo) as SLNo from tblQuestion";
		int lastSl = 0;
		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sqlForSec);
			if (mCursor1.moveToFirst()) {
				do {
					
					
					lastSl = Integer.parseInt(mCursor1
							.getString((mCursor1.getColumnIndex("SLNo"))));

				} while (mCursor1.moveToNext());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}
		return lastSl;
	}
	/*public void Clicksec1(View v) {
		CommonStaticClass.subEdit = "sec01";
		CommonStaticClass.mode = CommonStaticClass.EDITMODE;
		Intent i = new Intent();
		i.setClassName(CommonStaticClass.pName, CommonStaticClass.pName
				+ ".EditEntry");
		startActivity(i);
		//finish();
	}

	public void Clicksec2(View v) {
		CommonStaticClass.subEdit = "sec02";
		CommonStaticClass.mode = CommonStaticClass.EDITMODE;
		Intent i = new Intent();
		i.setClassName(CommonStaticClass.pName, CommonStaticClass.pName
				+ ".EditEntry");
		startActivity(i);
		//finish();
	}

	public void Clicksec3(View v) {
		CommonStaticClass.subEdit = "sec03";
		CommonStaticClass.mode = CommonStaticClass.EDITMODE;
		Intent i = new Intent();
		i.setClassName(CommonStaticClass.pName, CommonStaticClass.pName
				+ ".EditEntry");
		startActivity(i);
		//finish();
	}

	public void Clicksec4(View v) {
		CommonStaticClass.subEdit = "sec04";
		CommonStaticClass.mode = CommonStaticClass.EDITMODE;
		Intent i = new Intent();
		i.setClassName(CommonStaticClass.pName, CommonStaticClass.pName
				+ ".EditEntry");
		startActivity(i);
		//finish();
	}
	
	public void Clicksec5(View v) {
		CommonStaticClass.subEdit = "sec05";
		CommonStaticClass.mode = CommonStaticClass.EDITMODE;
		Intent i = new Intent();
		i.setClassName(CommonStaticClass.pName, CommonStaticClass.pName
				+ ".EditEntry");
		startActivity(i);
		//finish();
	}
	
	public void Clicksec6(View v) {
		CommonStaticClass.subEdit = "sec06";
		CommonStaticClass.mode = CommonStaticClass.EDITMODE;
		Intent i = new Intent();
		i.setClassName(CommonStaticClass.pName, CommonStaticClass.pName
				+ ".EditEntry");
		startActivity(i);
		//finish();
	}
	
	public void Clicksec7(View v) {
		CommonStaticClass.subEdit = "sec07";
		CommonStaticClass.mode = CommonStaticClass.EDITMODE;
		Intent i = new Intent();
		i.setClassName(CommonStaticClass.pName, CommonStaticClass.pName
				+ ".EditEntry");
		startActivity(i);
		//finish();
	}
	
	public void Clicksec8(View v) {
		CommonStaticClass.subEdit = "sec08";
		CommonStaticClass.mode = CommonStaticClass.EDITMODE;
		Intent i = new Intent();
		i.setClassName(CommonStaticClass.pName, CommonStaticClass.pName
				+ ".EditEntry");
		startActivity(i);
		//finish();
	}
	
	public void Clicksec9(View v) {
		CommonStaticClass.subEdit = "sec09";
		CommonStaticClass.mode = CommonStaticClass.EDITMODE;
		Intent i = new Intent();
		i.setClassName(CommonStaticClass.pName, CommonStaticClass.pName
				+ ".EditEntry");
		startActivity(i);
		//finish();
	}
	
	public void Clicksec10(View v) {
		CommonStaticClass.subEdit = "sec10";
		CommonStaticClass.mode = CommonStaticClass.EDITMODE;
		Intent i = new Intent();
		i.setClassName(CommonStaticClass.pName, CommonStaticClass.pName
				+ ".EditEntry");
		startActivity(i);
		//finish();
	}
	
	public void Clicksec11(View v) {
		CommonStaticClass.subEdit = "sec11";
		CommonStaticClass.mode = CommonStaticClass.EDITMODE;
		Intent i = new Intent();
		i.setClassName(CommonStaticClass.pName, CommonStaticClass.pName
				+ ".EditEntry");
		startActivity(i);
		//finish();
	}
	
	public void Clicksec12(View v) {
		CommonStaticClass.subEdit = "sec12";
		CommonStaticClass.mode = CommonStaticClass.EDITMODE;
		Intent i = new Intent();
		i.setClassName(CommonStaticClass.pName, CommonStaticClass.pName
				+ ".EditEntry");
		startActivity(i);
		//finish();
	}
	
	public void Clicksec13(View v) {
		CommonStaticClass.subEdit = "sec13";
		CommonStaticClass.mode = CommonStaticClass.EDITMODE;
		Intent i = new Intent();
		i.setClassName(CommonStaticClass.pName, CommonStaticClass.pName
				+ ".EditEntry");
		startActivity(i);
		//finish();
	}*/

	public void ClickbtnHome(View v) {
		CommonStaticClass.subEdit = "";
		finish();
	}

	public boolean onCreateOptionsMenu(final Menu menu) {
		final MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.home_menu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(final MenuItem item) {
		switch (item.getItemId()) {

		case R.id.ExitItem:

			CommonStaticClass.mode = "";
			finish();

			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void onBackPressed() {

	};
}
