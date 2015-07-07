package com.icddrb.app.galvanic;

import java.util.ArrayList;

//import com.icddrb.app.galvanic.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import net.sqlcipher.Cursor;

public class LoginActivity extends BaseActivity {
	private Spinner userId;
	private EditText userName, userPass;
	private Button loginButton, exitButton;
	String sql = "Select * from tblUser where Active='Y' ORDER BY ID";
	ArrayList<String> users;
	ArrayAdapter adapter1;
	private String userIdSelection = "";
	private Context con;
	private ProgressDialog progressDialog;
	protected static final int UPDATEFAILED = 0, UPDATEDONE = 1;
	private TextView tvVersion;

	public boolean onCreateOptionsMenu(final Menu menu) {
		final MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.lang_login_menu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(final MenuItem item) {
		switch (item.getItemId()) {
		case R.id.BNGMenuItem:
			CommonStaticClass.langBng = true;
			return true;
		case R.id.ENGMenuItem:
			CommonStaticClass.langBng = false;
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private Boolean loadVersionData() {

		Cursor mCursor = null;

		try {
			tvVersion = (TextView) findViewById(R.id.tvVersion);
			String version = tvVersion.getText().toString();
			version = version.replace("Version No: ", "");

			mCursor = dbHelper.getQueryCursor(String.format(
					"SELECT VersionNo FROM tblVersion WHERE VersionNo = '%s'",
					version));

			if (mCursor.moveToFirst()) {
				do {
					if (version.equals((mCursor.getString(mCursor
							.getColumnIndex("VersionNo"))))) {
						CommonStaticClass.VersionNo = mCursor.getString(mCursor
								.getColumnIndex("VersionNo"));
						return true;
					}

				} while (mCursor.moveToNext());

			}
			return false;

		} catch (Exception e) {
			// TODO: handle exception
			Log.e("cursor", "is null");
			return false;
		} finally {
			if (mCursor != null)
				mCursor.close();

		}

	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.loginlayout);
		con = this;
		// if(DatabaseHelper.getInstance() == null){
		// dbHelper = new DatabaseHelper(con);
		// dbHelper.openDataBase();
		// }
		users = new ArrayList<String>();
		users.add("");
		if (loadVersionData()) {
			loadGui();
			loadData();
		}
	}

	private void loadGui() {
		// TODO Auto-generated method stub
		userId = (Spinner) findViewById(R.id.userId);
		userName = (EditText) findViewById(R.id.userName);
		userPass = (EditText) findViewById(R.id.userPass);

		loginButton = (Button) findViewById(R.id.loginButton);
		loginButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				String username = userName.getText().toString();
				String userpass = userPass.getText().toString();
				String userSpecificId = userIdSelection.length() > 0 ? userIdSelection
						.substring(0, userIdSelection.lastIndexOf(":") - 1)
						: "";

				/*if (username.equalsIgnoreCase("aaaa")
						&& userpass.equalsIgnoreCase("aaaa")) {
					progressDialog = ProgressDialog.show(con, "Wait",
							"Updating options please wait");

					new Thread() {

						public void run() {
							updateTableEntry();
						}
					}.start();
				}*/
				if (userIdSelection.length() > 0 && username.length() > 0
						&& userpass.length() > 0) {
					validateThisUser(userSpecificId, username, userpass);
				} else {
					CommonStaticClass.showFinalAlert(con,
							"Please enter valid user information");
				}
			}
		});

		exitButton = (Button) findViewById(R.id.exitButton);
		exitButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});

		adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item,
				users);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		userId.setAdapter(adapter1);
		userId.setOnItemSelectedListener(new spinItemSelectedListener());
	}

	private void loadData() {
		// TODO Auto-generated method stub
		Cursor mCursor = null;
		try {
			mCursor = dbHelper.getQueryCursor(sql);
			if (mCursor.moveToFirst()) {
				do {
					users.add(mCursor.getString(mCursor.getColumnIndex("ID"))
							+ " : "
							+ mCursor.getString(mCursor.getColumnIndex("Name")));
				} while (mCursor.moveToNext());
			}
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("cursor", "is null");
		} finally {
			if (mCursor != null)
				mCursor.close();
			adapter1.notifyDataSetChanged();
		}

	}

	public class spinItemSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			if (parent == userId) {
				if (parent.getItemAtPosition(pos).toString().length() > 0) {
					userIdSelection = parent.getItemAtPosition(pos).toString();
					userName.setText(userIdSelection.substring(
							userIdSelection.lastIndexOf(":") + 2,
							userIdSelection.length()));
					// Log.e("User Name",userIdSelection);
					// Log.e("User Name Sp",userIdSelection.substring(userIdSelection.lastIndexOf(":")+1,userIdSelection.length()));
				} else {
					userIdSelection = "";
					userName.setText("");
				}
			}
		}

		public void onNothingSelected(AdapterView parent) {
		}
	}

	private void validateThisUser(String userSpecificId, String username,
			String userpass) {
		// TODO Auto-generated method stub
		Log.e("userSpecificId", userSpecificId);
		String validationSql = "Select * from tblUser Where ID = '"
				+ userSpecificId + "' AND Name = '" + username
				+ "' AND Pwd = '" + userpass + "'";
		Cursor mCursor = null;
		try {
			mCursor = dbHelper.getQueryCursor(validationSql);
			if (mCursor.getCount() > 0) {
				CommonStaticClass.userSpecificId = userSpecificId;
				// if(CommonStaticClass.userSpecificId.equalsIgnoreCase("0")){
				// updateTableEntry();
				// }
				Intent i = new Intent();
				i.setClassName(CommonStaticClass.pName, CommonStaticClass.pName
						+ ".MenuScreen");
				startActivity(i);

			} else {
				CommonStaticClass
						.showFinalAlert(con,
								"Can not validate under this username and password please try again");
			}
		} catch (Exception e) {
			// TODO: handle exception
			CommonStaticClass.showFinalAlert(con,
					"A problem occured during validation please try again");
		} finally {
			if (mCursor != null)
				mCursor.close();
		}
	}


	public void onBackPressed() {
		super.onBackPressed();
		clearEveryThing();
	}

	private void clearEveryThing() {
		// TODO Auto-generated method stub
		CommonStaticClass.userSpecificId = "";
		CommonStaticClass.dataId = "";
		// CommonStaticClass.currentHHMemberLine = 1;
//		CommonStaticClass.totalHHMember = 1;
		CommonStaticClass.SLNOSTACK.clear();
		// CommonStaticClass.dataHashMap.clear();
		CommonStaticClass.questionMap.clear();
		CommonStaticClass.qskipList.clear();
		CommonStaticClass.currentSLNo = 1;
		CommonStaticClass.langBng = false;
		CommonStaticClass.mode = "";
		CommonStaticClass.dataId = "";
		CommonStaticClass.memberID = "";
		// finish();
	}

	private Handler searchHandler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UPDATEDONE:
				if (progressDialog != null && progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
				Toast.makeText(con, "Update is done", Toast.LENGTH_LONG).show();
				break;
			case UPDATEFAILED:
				if (progressDialog != null && progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
				Toast.makeText(con, "Update failed", Toast.LENGTH_LONG).show();
				break;
			}

		}
	};
}