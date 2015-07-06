package app.prelm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import app.prelm.db.DatabaseHelper;

import com.app.prelm.R;

public class LoginActivity extends BaseActivity {
	private Spinner userId;
	private EditText userName, userPass;
	private Button loginButton, exitButton;
	ArrayList<String> users;
	ArrayAdapter adapter1;
	private String userIdSelection = "";
	private Context con;
	private String line = "";
	protected static final int UPDATEFAILED = 0, UPDATEDONE = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.loginlayout);

		con = this;

		users = new ArrayList<String>();
		users.add("");
		loadGui();
		loadData();

	}

	private boolean FileRead() {
		InputStream instream = null;

		try {
			String path = "/mnt/sdcard/Android/AssetID.txt";
			Log.e("path", "" + path);
			File f = new File(path);
			instream = new FileInputStream(f);
			
			if (instream != null) {

				InputStreamReader inputreader = new InputStreamReader(instream);
				BufferedReader buffreader = new BufferedReader(inputreader);

				line = buffreader.readLine();
				Log.e("line", "" + line);

				if (line == null || !(line.length() > 3)) {

					return false;
				} else {
					CommonStaticClass.AssetID = line;
					return true;
				}

			}
			

		} catch (Exception ex) {
			ex.printStackTrace();

			
			
		}

		finally {
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

	private void loadGui() {
		// TODO Auto-generated method stub
		userId = (Spinner) findViewById(R.id.userId);
		userName = (EditText) findViewById(R.id.userName);
		userPass = (EditText) findViewById(R.id.userPass);

		loginButton = (Button) findViewById(R.id.loginButton);
		loginButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*
				 * String username = userName.getText().toString(); String
				 * userpass = userPass.getText().toString(); String
				 * userSpecificId =
				 * userIdSelection.length()>0?userIdSelection.substring
				 * (0,userIdSelection.lastIndexOf(".")-1):"";
				 * 
				 * if(userIdSelection.length()>0&&username.length()>0&&userpass.
				 * length()>0){
				 * validateThisUser(userSpecificId,username,userpass); }else{
				 * CommonStaticClass
				 * .showFinalAlert(con,"Please enter valid user information"); }
				 */
				// TODO Auto-generated method stub
				String username = userName.getText().toString();
				String userpass = userPass.getText().toString();
				String userSpecificId = userIdSelection.length() > 0 ? userIdSelection
						.substring(0, userIdSelection.lastIndexOf(":") - 1)
						: "";

				// Dialog progressDialog = ProgressDialog.show(con, "Wait",
				// "Updating options please wait");

				// new Thread() {

				// public void run() {
				// updateTableEntry();
				// }
				// }.start();

				if (userIdSelection.length() > 0 && username.length() > 0
						&& userpass.length() > 0) {
					if (FileRead() == true) {
						validateThisUser(userSpecificId, username, userpass);
					} 
					else
					{
						CommonStaticClass.showMyAlert(con, "Message", "Asset ID is Missing in Device");
					}
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

	protected void validateThisUser(String userSpecificId, String username2,
			String userpass2) {
		/*
		 * // TODO Auto-generated method stub
		 * Log.e("userSpecificId",""+userSpecificId);
		 * Log.e("username2",""+username2); Log.e("userpass2",""+userpass2);
		 * if(userIdSelection.contains(userpass2)){
		 * CommonStaticClass.userSpecificId = userIdSelection; Intent intn = new
		 * Intent(LoginActivity.this, MenuScreen.class); startActivity(intn); }
		 */

		Log.e("userSpecificId", userSpecificId);
		String validationSql = "Select * from tblUser Where ID = '"
				+ userSpecificId + "' AND Pwd = '" + userpass2 + "'";
		Cursor mCursor = null;
		try {
			mCursor = DatabaseHelper.getInstance(con).getQueryCursor(
					validationSql);
			if (mCursor.getCount() > 0) {
				CommonStaticClass.userSpecificId = userSpecificId;
				// if(CommonStaticClass.userSpecificId.equalsIgnoreCase("0")){
				// updateTableEntry();
				// }
				Intent i = new Intent(LoginActivity.this, MenuScreen.class);
				// i.setClassName(CommonStaticClass.pName,
				// CommonStaticClass.pName+".MenuScreen");
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

	// private void loadData() {
	/*
	 * users.add("1. Balay Chand Sikder"); users.add("2. Mary Marget");
	 * users.add("3. Parimol Sarker"); users.add("4. Dipali Rani Das");
	 * users.add("5. Sania Akter Dolly"); users.add("6. Syeda Fatema Khatun");
	 * users.add("7. Md. Altafur Rahman"); users.add("12. Syeda Luthfa Famida");
	 * users.add("13. Shajahan Ali"); users.add("14. Md. Shariful Islam");
	 */
	// for (String str : GetUser()) {

	// users.add(str);
	// }
	// }

	private void loadData() {
		// TODO Auto-generated method stub
		Log.e("cursor", "is null jonjnjknikjnkn");
		Cursor mCursor = null;
		try {
			mCursor = DatabaseHelper
					.getInstance(getApplicationContext())
					.getQueryCursor(
							"Select * from tblUser where Active='Y' ORDER BY ID");
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

	protected ArrayList<String> GetUser() {
		ArrayList<String> str = new ArrayList<String>();

		String sql = "Select * from tblUser";
		Cursor mCursor = null;
		try {
			mCursor = DatabaseHelper.getInstance(con).getQueryCursor(
					"Select * from tblUser where Active='Y' ORDER BY ID");
			if (mCursor.moveToFirst()) {
				do {
					str.add(mCursor.getString(mCursor.getColumnIndex("ID"))
							+ " : "
							+ mCursor.getString(mCursor.getColumnIndex("Name")));
				} while (mCursor.moveToNext());
			}
		} catch (Exception e) {

		} finally {
			if (mCursor != null)
				mCursor.close();
		}
		return str;
	}

	public class spinItemSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			if (parent == userId) {
				if (parent.getItemAtPosition(pos).toString().length() > 0) {
					userIdSelection = parent.getItemAtPosition(pos).toString();
					userName.setText(userIdSelection.substring(
							userIdSelection.lastIndexOf(".") + 2,
							userIdSelection.length()));
					Log.e("User Name", userIdSelection);
				} else {
					userIdSelection = "";

					userName.setText("");
				}
			}
		}

		public void onNothingSelected(AdapterView parent) {
		}
	}

}