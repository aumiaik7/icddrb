package com.icddrb.datatransfertool;



import android.app.Activity;
import android.content.Context;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.text.AndroidCharacter;
import android.view.Window;
import android.view.WindowManager;

import com.icddrb.app.datatransfertool.db.DatabaseHelper;
public class BaseActivity extends Activity{
	protected DatabaseHelper dbHelper;
	private Context contxt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		contxt = this;
		/*getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
		
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		if (DatabaseHelper.getInstance() == null) {
			dbHelper = new DatabaseHelper(contxt);
			dbHelper.openDataBase();
		} else {
			dbHelper = DatabaseHelper.getInstance();
		}
	}

}
