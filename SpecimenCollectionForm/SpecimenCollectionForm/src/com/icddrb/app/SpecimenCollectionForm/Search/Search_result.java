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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.icddrb.app.SpecimenCollectionForm.R;
import com.icddrb.app.SpecimenCollectionForm.CommonStaticClass;
import com.icddrb.app.SpecimenCollectionForm.db.DatabaseHelper;
public class Search_result extends Activity {

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
		_intent = getIntent().getExtras();
		setContentView(R.layout.searchresult);
		/*progressDialog = ProgressDialog.show(con, "Wait",
				"Please wait while processing your request");
		
		progressDialog.show();*/
		//Display Search Criteria
		String searchCriteria = _intent.getString("SearchCriteria");		
		searchCriteria = "Search Criteria ::" + searchCriteria;				
		((TextView) findViewById(R.id.DisplayCriteria)).setText(searchCriteria);
		
				
		//Get Row Count
		String query = _intent.getString("sql");
		_sql = "SELECT COUNT(*) AS rowCount FROM MemSCVB" + query;		
		int rowCount = GetRowCount(_sql);
		//((TextView) findViewById(R.id.DisplayRowCount)).setText("Record(s) found:: " + rowCount);
		
		progressDialog = ProgressDialog.show(con, "Wait",
				"Searching...");
		new Thread() 
		{
			@Override
			public void run() 
			{
				Looper.prepare();
				
				String query2 = _intent.getString("sql");
				//_sql = " SELECT [PID],[Hh],[Slno],[Name], CASE [Sex] WHEN [Sex]=1 THEN 'Male' ELSE  'Female' END as [Sex], [Age], [BDate], [Rth], [MS], [Area], [SecBlock], [Road], [House], [Dist], [UPZ], [Vill], [Phone1], [Phone2], [Phone3], [Phone4], [GISID], [Ward], [Clust], [Block], [FaSize], [EnDate],[MotherName],[FatherName], [HeadName],[SpouseName], [Ucode], [UnionName], [VillK], [Vpart],[VillName], [Mohalla], [Bari],[Khana], [Project], [Location], rowid as _id from MemSCVB" + query2;
				_sql = " SELECT [PID],[Hh],[Slno],[Name], CASE [Sex] WHEN [Sex]=1 THEN 'Male' ELSE  'Female' END as [Sex], [Age], [BDate], [Rth], [MS], [Area], [SecBlock], [Road], [House], [Dist], [UPZ], [Vill], [Phone1], [Phone2], [Phone3], [Phone4], [GISID], [Ward], [Clust], [Block], [FaSize], [EnDate],[MotherName],[FatherName], [HeadName],[SpouseName], rowid as _id from MemSCVB" + query2;
				
				ListView gridview = (ListView) findViewById(R.id.gridview);							
				
				DatabaseHelper databaseHelper = new DatabaseHelper(con);
				customAdapter = new CustomCursorAdapter(Search_result.this, databaseHelper.getQueryCursor(_sql));

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
	private int GetRowCount(String sql) {
		int rowCount = 0;
		Cursor mCursor = null;
	    boolean found = false;
	    try 
	    {
	    	DatabaseHelper dbhelper = new DatabaseHelper(con);
	        mCursor = dbhelper.getQueryCursor(sql);
	        if (mCursor.moveToFirst()) 
	        {
	        	do 
	        	{
	        		rowCount  = Integer.parseInt(mCursor.getString(mCursor.getColumnIndex("rowCount")));

	            } while (mCursor.moveToNext());
	        }
	    } 
	    catch (Exception e) 
	    {
    		Log.e("cursor", "is null");
	    } 
	    finally 
	    {
	    	if (mCursor != null)
	    		mCursor.close();
	    }
	    return rowCount;
	}
}
