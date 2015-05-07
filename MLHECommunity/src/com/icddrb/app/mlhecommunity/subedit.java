package com.icddrb.app.mlhecommunity;
import com.icddrb.app.mlhecommunity.R;


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


	public void ClickbtnEAdults(View v) {
		CommonStaticClass.subEdit = "Adults";
		CommonStaticClass.mode = CommonStaticClass.EDITMODE;
		Intent i = new Intent();
		i.setClassName(CommonStaticClass.pName, CommonStaticClass.pName
				+ ".EditEntry");
		startActivity(i);
		//finish();
	}

	public void ClickbtnbtnEAdultsDischarge(View v) {
		CommonStaticClass.subEdit = "AdultsDischarge";
		CommonStaticClass.mode = CommonStaticClass.EDITMODE;
		Intent i = new Intent();
		i.setClassName(CommonStaticClass.pName, CommonStaticClass.pName
				+ ".EditEntry");
		startActivity(i);
		//finish();
	}

	public void ClickbtnENeonates(View v) {
		CommonStaticClass.subEdit = "Neonates";
		CommonStaticClass.mode = CommonStaticClass.EDITMODE;
		Intent i = new Intent();
		i.setClassName(CommonStaticClass.pName, CommonStaticClass.pName
				+ ".EditEntry");
		startActivity(i);
		//finish();
	}

	public void ClickbtnENeonatesDischarge(View v) {
		CommonStaticClass.subEdit = "NeonatesDischarge";
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
