package com.example.edcryption;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import com.icddrb.app.edcryption.db.*;



public class BaseActivity extends Activity {
	
	protected DatabaseHelper dbHelper;
	protected DatabaseHelper dbHelperBase;
	private Context contxt;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		contxt = this;

		if (DatabaseHelper.getInstance() == null) {
			dbHelper = new DatabaseHelper(contxt);
			dbHelper.openDataBase();
		} else {
			dbHelper = DatabaseHelper.getInstance();
		}
		
		
		setTheme(R.style.AppTheme);
	}

	
}
