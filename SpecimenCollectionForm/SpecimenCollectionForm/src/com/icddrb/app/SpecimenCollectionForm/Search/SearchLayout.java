package com.icddrb.app.SpecimenCollectionForm.Search;

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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.icddrb.app.SpecimenCollectionForm.R;
import com.icddrb.app.SpecimenCollectionForm.*;
import com.icddrb.app.SpecimenCollectionForm.db.DatabaseHelper;
public class SearchLayout extends Activity {

	// Newly Added

	//
	private Context con;
	private ProgressDialog progressDialog;
	protected static final int Load_Failed = 0, Load_Done = 123, LoadDone_Search=321;

	protected static final int TIMER_RUNTIME = 10000; // in ms --> 10s
	private static final int DIALOG_CONTACTS = 0;

	protected boolean mbActive;
	protected ProgressBar mProgressBar;

	String spproject, spvillage, txtslno, txtfather, spunion, txtgisid,
			txtname, txthead, spkhana, txtagefrom, txtageto, txtupz, txthh,
			spdist, sparea, txtroad, spphone, txtphone;
	Spinner _spproject, _spkhana, _spunion, _spvillage, _spdist, _spSex, _spPhone;
	
	private int progressBarStatus = 0;
	ArrayList<String> _spprojectitems = new ArrayList<String>();
	ArrayList<String> _spkhanaitems = new ArrayList<String>();
	ArrayList<String> _spunionitems = new ArrayList<String>();
	ArrayList<String> _spvillageitems = new ArrayList<String>();
	ArrayList<String> _spdistitems = new ArrayList<String>();
	ArrayList<String> _spSexItems = new ArrayList<String>();
	ArrayList<String> _spPhoneItems = new ArrayList<String>();
	ArrayAdapter adapter = null;
	
	String _sql, _searchCriteria;
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		setTheme(R.style.AppTheme);
	}

	public boolean onCreateOptionsMenu(final Menu menu) {
		final MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.lang_menu, menu);
		
		menu.getItem(0).setVisible(false);
		
		return true;
	}

	public boolean onOptionsItemSelected(final MenuItem item) {
		switch (item.getItemId()) {
		
	
		/*case R.id.search:
			CommonStaticClass.mode = "";
			Intent int_b = new Intent(getApplicationContext(), SearchLayout.class);
			startActivity(int_b);
			return true;*/
			
		case R.id.GotoHomeItem:
			CommonStaticClass.mode = "";
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchlayout);
		setTheme(R.style.AppTheme);
		con = this;
		

		_spdist = (Spinner) findViewById(R.id.spdist);
		_spSex = (Spinner) findViewById(R.id.spsex);
		
		
			
		findViewById(R.id.btnSearch).setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				progressDialog = ProgressDialog.show(con, "Wait",
						"Searching...");
				new Thread() {

					@Override
					public void run() {
						Looper.prepare();

						search();
						
					}
				}.start();
			}
		});
		loadUI();
	}
	
	private void loadUI()
	{
		progressDialog = ProgressDialog.show(con, "Message",
				"Please wait...");
		new Thread() {

			@Override
			public void run() {
				Looper.prepare();
				
				String strSQL = "";

				/*strSQL = "SELECT  ' ' as Name UNION SELECT Name FROM MemSCVB ORDER BY Name ";
				_spvillageitems = LoadDataInCombo(_spvillage, strSQL);
*/
				strSQL = "SELECT  ' ' as Dist UNION SELECT DISTINCT TRIM(District) AS Dist FROM tblDistrict ORDER BY Dist ";
				_spdistitems = LoadDataInCombo(_spdist, strSQL);			
				
				//strSQL = "SELECT  ' ' AS Sex  UNION SELECT DISTINCT Sex FROM MemSCVB ORDER BY Sex";
				//_spSexItems = LoadDataInCombo(_spSex, strSQL);
				_spSexItems = new ArrayList<String>();
				_spSexItems.add("");
				_spSexItems.add("Male");
				_spSexItems.add("Female");
				
				/*strSQL = " SELECT  '  ' AS Name UNION ALL SELECT Name FROM MemSCVB  ";
				_spPhoneItems = LoadDataInCombo(_spPhone, strSQL);	*/
				
				Message msg = new Message();
				msg.what = Load_Done;
				handler.sendMessage(msg);
				Looper.loop();
			}
		}.start();
	}
	
	public void setData() {

		
		adapter = new ArrayAdapter(SearchLayout.this,
				android.R.layout.simple_spinner_item, _spdistitems);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		_spdist.setAdapter(adapter);		
		adapter.notifyDataSetChanged();
		
		adapter = new ArrayAdapter(SearchLayout.this,
				android.R.layout.simple_spinner_item, _spSexItems);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		_spSex.setAdapter(adapter);		
		
		
		adapter.notifyDataSetChanged();
	}

	private void search() {
		String whereSQL = " WHERE 1=1 ";	
		_searchCriteria = "";		
		

		String pid = ((EditText) findViewById(R.id.txtpid)).getText().toString();
		if (!pid.equalsIgnoreCase(""))
		{
			whereSQL = whereSQL + " AND [PID] LIKE '%" +pid + "%'";
			_searchCriteria += (_searchCriteria.length() == 0 ? " [PID] = ": " AND [PID] = " )+ pid;
		}
		
		String hh = ((EditText) findViewById(R.id.txtHHID)).getText().toString();
		if (!hh.equalsIgnoreCase(""))
		{
			whereSQL = whereSQL + " AND [Hh] LIKE '%" +hh + "%'";
			_searchCriteria += (_searchCriteria.length() == 0 ? " [Hh] = ": " AND [Hh] = " )+ hh;
		}
		
String phoneNo = ((EditText) findViewById(R.id.txtPhone)).getText().toString();
		
		if (!phoneNo.equalsIgnoreCase(""))
		{
			//String phoneType =((Spinner) findViewById(R.id.spphone)).getSelectedItem().toString();
			//if(phoneType.equalsIgnoreCase("First"))
			//{
				whereSQL = whereSQL + " AND [Phone1] LIKE '%" + phoneNo + "%' OR";
				_searchCriteria += (_searchCriteria.length() == 0 ? " [Phone1] = ": " OR [Phone1] = " )+ phoneNo;
			//}
			//else if(phoneType.equalsIgnoreCase("Second"))
			//{
				whereSQL = whereSQL + " [Phone2] LIKE '%" + phoneNo + "%' OR";
				_searchCriteria += (_searchCriteria.length() == 0 ? " [Phone2] = ": " OR [Phone2] = " )+ phoneNo;
			//}
			//else if(phoneType.equalsIgnoreCase("Third"))
			//{
				whereSQL = whereSQL + " [Phone3] LIKE '%" + phoneNo + "%' OR";
				_searchCriteria += (_searchCriteria.length() == 0 ? " [Phone3] = ": " OR [Phone3] = " )+ phoneNo;
			//}
			//else if(phoneType.equalsIgnoreCase("Fourth"))
			//{
				whereSQL = whereSQL + " [Phone4] LIKE '%" + phoneNo + "%' ";
				_searchCriteria += (_searchCriteria.length() == 0 ? " [Phone4] = ": " OR [Phone4] = " )+ phoneNo;
			//}		
		}
		
		String fatherName = ((EditText) findViewById(R.id.txtfather)).getText().toString();
		if (!fatherName.equalsIgnoreCase(""))
		{
			whereSQL = whereSQL + " AND [FatherName] LIKE '%" +fatherName + "%'";
			_searchCriteria += (_searchCriteria.length() == 0 ? " [FatherName] = ": " AND [FatherName] = " )+ fatherName;
		}
		

		String patName = ((EditText) findViewById(R.id.txtname)).getText().toString();
		if (!patName.equalsIgnoreCase(""))
		{
			whereSQL = whereSQL + " AND [Name] LIKE '%" +  patName + "%'";
			_searchCriteria += (_searchCriteria.length() == 0 ? " [Name] = ": " AND [Name] = " )+ patName;
		}
		
		
						
		String ageFrom  = ((EditText) findViewById(R.id.txtagefrom)).getText().toString();
		String ageTo  = ((EditText) findViewById(R.id.txtageto)).getText().toString();
		if(!ageFrom.equalsIgnoreCase("")  && !ageTo.equalsIgnoreCase(""))
		{
			whereSQL = whereSQL+ "  AND [Age] >="+ ageFrom + " AND [Age] <=" + ageTo;
			_searchCriteria += (_searchCriteria.length() == 0 ? "": " AND " )+ "[Age] BETWEEN " + ageFrom + " AND " +  ageTo;
		}
		else if(!ageFrom.equalsIgnoreCase("")  && ageTo.equalsIgnoreCase(""))
		{
			whereSQL = whereSQL + " AND [Age] >=" + ageFrom + " ";
			_searchCriteria += (_searchCriteria.length() == 0 ? " [Age] = ": " AND [Age] >= " )+ ageFrom;
		}
		else if(ageFrom.equalsIgnoreCase("")  && !ageTo.equalsIgnoreCase(""))
		{
			whereSQL = whereSQL + " AND [Age] <=" + ageTo + " ";
			_searchCriteria += (_searchCriteria.length() == 0 ? " [Age] = ": " AND [Age] <= " )+ ageTo;
		}
		
		//if (!upz.equalsIgnoreCase(""))
			//whereSQL = whereSQL+ " AND [UPZ] LIKE '%"+ upz + "%' ";
		
		String upz  = ((EditText) findViewById(R.id.txtupz)).getText().toString();
		if (!upz.equalsIgnoreCase(""))
		{
			whereSQL = whereSQL+ " AND [UPZ] LIKE '%"+ upz + "%' ";
			_searchCriteria += (_searchCriteria.length() == 0 ? " [UPZ] = ": " AND [UPZ] = " )+ upz;
		}
		
		
		if ((((Spinner) findViewById(R.id.spdist)).getSelectedItemPosition() > 0) && !(((Spinner) findViewById(R.id.spdist)).getSelectedItem().toString().equalsIgnoreCase("")))
		{
			String dist =((Spinner) findViewById(R.id.spdist)).getSelectedItem().toString();
			whereSQL = whereSQL + " AND [Dist] LIKE '%" + dist +"%'";
			_searchCriteria += (_searchCriteria.length() == 0 ? " [Dist] = ": " AND [Dist] = " )+ dist;
		}
		
		
				
		if ((((Spinner) findViewById(R.id.spsex)).getSelectedItemPosition() > 0) && !(((Spinner) findViewById(R.id.spsex)).getSelectedItem().toString().equalsIgnoreCase("")))
		{
			String sexText =((Spinner) findViewById(R.id.spsex)).getSelectedItem().toString();
			int sex =((Spinner) findViewById(R.id.spsex)).getSelectedItemPosition();
			whereSQL = whereSQL + " AND Sex= " + sex;
			_searchCriteria += (_searchCriteria.length() == 0 ? " [Sex] = ": " AND [Sex] = " )+ sexText;
		}		
		
 		DatabaseHelper dbhelper = new DatabaseHelper(con);
		Cursor mCursor = null;
		
		//_sql = " SELECT [PID],[Hh],[Slno],[Name], CASE [Sex] WHEN [Sex]=1 THEN 'Male' ELSE  'Female' END as [Sex], [Age], [BDate], [Rth], [MS], [Area], [SecBlock], [Road], [House], [Dist], [UPZ], [Vill], [Phone1], [Phone2], [Phone3], [Phone4], [GISID], [Ward], [Clust], [Block], [FaSize], [EnDate],[MotherName],[FatherName], [HeadName],[SpouseName], [Ucode], [UnionName], [VillK], [Vpart],[VillName], [Mohalla], [Bari],[Khana], [Project], [Location], rowid as _id from icvb" + whereSQL;
		_sql = whereSQL;
					
		Message msg = new Message();
		msg.what = LoadDone_Search;
		handler.sendMessage(msg);
		Looper.loop();
	}

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Load_Done:
				progressDialog.dismiss();
				setData();
				break;
				
			case LoadDone_Search:
				progressDialog.dismiss();
				Intent i = new Intent(con, Search_result.class);
				i.putExtra("sql", _sql);
				i.putExtra("SearchCriteria", _searchCriteria);
				con.startActivity(i);
				break;
			}

		}
	};

	private ArrayList<String> LoadDataInCombo(Spinner sp, String sql) {
		Cursor mCursor = null;
		ArrayList<String> items = new ArrayList<String>();
		ArrayAdapter adapter = null;
		//items.add(" ");

		try {
			DatabaseHelper dbhelper = new DatabaseHelper(con);
			mCursor = dbhelper.getQueryCursor(sql);
			if (mCursor.moveToFirst() && mCursor != null) {

				do {
					if (mCursor.getString(0) != null) {
						if (!mCursor.getString(0).equalsIgnoreCase("")) {
							items.add(mCursor.getString(0));
						}

					}
				} while (mCursor.moveToNext());
				if (items.size() > 0) {

					return items;

				}
			}

		} catch (Exception e) {
			Log.e("Error", e.getMessage());

		} finally {
			if (mCursor != null)
				mCursor.close();

		}
		return null;
	}

	
}
