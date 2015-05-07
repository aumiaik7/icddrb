package com.icddrb.app.SpecimenCollectionForm.Viewall;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.icddrb.app.SpecimenCollectionForm.R;
import com.icddrb.app.SpecimenCollectionForm.CommonStaticClass;
import com.icddrb.app.SpecimenCollectionForm.db.DatabaseHelper;

public class Viewall extends Activity {

	private CustomCursorAdapter customAdapter;
	private ListView listView;
	protected static final int Load_Failed = 0, Load_Done = 2123;
	private ProgressDialog progressDialog;

	Context con;
	String _sql = "";
	Bundle _intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		con = this;

		setContentView(R.layout.viewall);
		progressDialog = ProgressDialog.show(con, "Wait",
				"Please wait while processing your request");

		progressDialog.show();

		new Thread() {
			@Override
			public void run() {
				Looper.prepare();

				_sql = " SELECT dataid,HosCode,HOSPID,PatientTY,RegDate    ,RegTime    ,Name       ,BDate      ,CASE [Sex] WHEN [Sex]=1 THEN 'Male' ELSE  'Female' END as [Sex],"
						+ "AgeYr      ,AgeMo      ,AgeDa      ,BDType     ,FaSpName   ,Ward       ,Area       ,SecBlock   ,Road       ,"
						+ "House      ,HH         ,SL         ,PID        ,Phone      ,UPZ ,        Vill       ,Dist       ,WT         ,HT         ,"
						+ "MUAC       ,DS17       ,DS18D      ,DS18H      ,DS19       ,DS20       ,DS21       ,DS22       ,DS23       ,DS24       ,"
						+ "DS25       ,DS26       ,DS26a,      DS26b, 		DS27       ,DS28D      ,DS28H      ,DS29       ,DS30       ,DS31       ,DS32D      " +
						",DS32H      ,"
						+ "DS33       ,DS34       ,DS35D      ,DS35H      ,DS36       ,DS37       ,DS38       ,DS39       ,DS40       ,DS41       ,"
						+ "DS42       ,DS43       ,DS44       ,DS45       ,DS46       ,DS47       ,DS48       ,DS49       ,DS50       ,"
						+ "DS51       ,DS52       ,DS53       ,DS54       ,DS55       ,DS56       ,DS57       ,DS58       ,DS59C      ,DS59P"
						+ ",DS60       ,DS61       ,DS62       ,DS63       ,DS64       ,DS65       ,DS66       ,DS67       ,"
						+ "DS68       ,DS69       ,ENTRYBY    ,EntryDate  ,EditBy     , EditDate, dataid as _id from SCVBDS WHERE DelStatus=0";// +
				// query2;

				// ListView gridview = (ListView) findViewById(R.id.gridview);

				DatabaseHelper databaseHelper = new DatabaseHelper(con);
				customAdapter = new CustomCursorAdapter(Viewall.this,
						databaseHelper.getQueryCursor(_sql));

				Message msg = new Message();
				msg.what = Load_Done;
				handler.sendMessage(msg);
				Looper.loop();
			}
		}.start();
	}

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Load_Done:
				progressDialog.dismiss();
				listView = (ListView) findViewById(R.id.gridview);
				listView.setAdapter(customAdapter);
				break;
			}

		}
	};

	public boolean onCreateOptionsMenu(final Menu menu) {
		final MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.lang_menu, menu);
		menu.getItem(0).setVisible(false);
		return true;
	}

	public boolean onOptionsItemSelected(final MenuItem item) {
		switch (item.getItemId()) {
		case R.id.GotoHomeItem:
			CommonStaticClass.mode = "";
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}


}
