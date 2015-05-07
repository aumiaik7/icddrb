package com.icddrb.app.maternalhealth;
import com.icddrb.app.maternalhealth.R;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class subedit extends BaseActivity {

	private Button btnEAdults, btnEAdultsDischarge, btnENeonates, btnENeonatesDischarge, btnHome;
	private ProgressDialog progressDialog;
	private Context con;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subedit);
		con = this;
		setTheme(R.style.AppTheme);
	}


	public void Clicksec1(View v) {
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
	}

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
