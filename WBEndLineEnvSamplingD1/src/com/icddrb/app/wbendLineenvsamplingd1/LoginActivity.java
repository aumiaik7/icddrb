package com.icddrb.app.wbendLineenvsamplingd1;

import java.util.ArrayList;

import com.icddrb.app.wbendLineenvsamplingd1.R;

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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

				if (username.equalsIgnoreCase("aaaa")
						&& userpass.equalsIgnoreCase("aaaa")) {
					progressDialog = ProgressDialog.show(con, "Wait",
							"Updating options please wait");

					new Thread() {

						public void run() {
							updateTableEntry();
						}
					}.start();
				}
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

	private void updateTableEntry() {
		// TODO Auto-generated method stub

		ArrayList<String> insertQueryList = new ArrayList<String>();
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(1,'q11','Islam','','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(2,'q11','Hindu','','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(3,'q11','Buddhism','','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(4,'q11','Christian','','4','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(5,'q11','Others','','5','q11Other');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(6,'q12','Child aged 0-35 months','','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(7,'q12','Child age 6-35 months selected for hemoglobin measurement','','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(8,'q12','Pregnant','','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(9,'q12','Lactating','','4','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(10,'q13b','Self (pergnant/lactating women); ','','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(11,'q13b','Mother and primary caregiver (children); ','','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(12,'q13b','Grandmother and primary caregiver (children); ','','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(13,'q13b','Aunt and primary caregiver (children); ','','4','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(14,'q13b','Sister and primary caregiver (children); ','','5','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(15,'q13b','Other primary caregiver (children) specify','','6','q13bOther');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(16,'q15','Yes','','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(17,'q15','No','','2','END');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(18,'q18a','In home','N‡i e‡m','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(19,'q18a','Shelter in the road','iv¯Ívi cv‡k¦©i QvDwbi wb‡P','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(20,'q18a','In a shelter','QvDwbi wb‡P','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(21,'q18a','Other','†Kv_vq e‡m (wbw`©ó Kiæb) ----- ','4','q18Other');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(22,'q19','In home','N‡i e‡m','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(23,'q19','Shelter in the road','iv¯Ívi cv‡k¦©i QvDwbi wb‡P','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(24,'q19','In a shelter','QvDwbi wb‡P','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(25,'q19','Other (specify)………….','†Kv_vq e‡m (wbw`©ó Kiæb) ----- ','4','q19Other');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(26,'q102','HH head','Lvbv cÖavb (wb‡R)','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(27,'q102','Spouse','¯^vgx/¯¿x','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(28,'q102','Son/ daughter','cyÎ/Kb¨v','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(29,'q102','Brother/ sister','fvB/‡evb','4','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(30,'q102','Brother/ sister in law','k¨vjK/kvwjKv/fvex/ bb`/`yjvfvB/‡`ei','5','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(31,'q102','Niece/ nephew','fvwZwR/fvwZRv/fvMœx/fvwMbv','6','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(32,'q102','Father/ mother','evev/gv','7','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(33,'q102','Father/mother in law','k¦ïi/kvïox','8','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(34,'q102','Daughter/son in law','cyÎeay/RvgvB','9','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(35,'q102','Grandson/daughter','bvwZ/bvZwb','10','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(36,'q102','Other relative','Ab¨vb¨ AvZœxq (wbw`©ó Kviæb)','11','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(37,'q102','Other non relative','Ab¨vb¨ (AbvZœxq) (wbw`©ó Kiæb)','12','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(38,'q102','Servant','Kv‡Ri †jvK','13','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(39,'q103','Male','cyiæl','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(40,'q103','Female','gwnjv','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(41,'q105','Married','weevwnZ','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(42,'q105','Unmarried','AweevwnZ','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(43,'q105','Widow','weaev/wecZœxK','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(44,'q105','Divorced','ZvjvK cÖvß','4','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(45,'q105','Separated','c„_K _v‡K (wew”Qbœ)','5','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(46,'q105','<10 years    ','`k eQ‡ii Kg','9','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(47,'q106','No education/ no class passed','cov‡jLv K‡i bvB/‡Kv‡bv †kÖYx cvm K‡i bvB','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(48,'q106','01 class passed ','01 †kÖYx cvm K‡i‡Q','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(49,'q106','02 class passed ','02 †kÖYx cvm K‡i‡Q','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(50,'q106','03 class passed ','03 †kÖYx cvm K‡i‡Q','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(51,'q106','04 class passed ','04 †kÖYx cvm K‡i‡Q','4','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(52,'q106','05 class passed ','05 †kÖYx cvm K‡i‡Q','5','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(53,'q106','06 class passed ','06 †kÖYx cvm K‡i‡Q','6','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(54,'q106','07 class passed ','07 †kÖYx cvm K‡i‡Q','7','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(55,'q106','08 class passed ','08 †kÖYx cvm K‡i‡Q','8','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(56,'q106','09 class passed ','09 †kÖYx cvm K‡i‡Q','9','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(57,'q106','1SSC/Dakhil','Gm.Gm.wm/`vwLj','10','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(58,'q106','HSC/Aleem/Diploma','GBP.Gm.wm/Avwjg/wW‡c­vgv','12','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(59,'q106','Degree (pass)/Fajil','¯œvZK (cvm)/dvwRj','14','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(60,'q106','Degree (Honors)/Fajil(Honors)','¯œvZK (m¤§vb)/dvwRj (m¤§vb)','15','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(61,'q106','Masters/Kamil','gv÷vm©/Kvwgj','16','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(62,'q106','MBBS/Engineer ','GgweweGm/BwÄwbqvi','17','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(63,'q106','Functional education','Kg©g~Lx  wk¶v','18','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(64,'q106','Currently going to ','eZ©gv‡b ¯‹z‡j hvq ( wKš‘ GL‡bv cÖ_g †kªYx cvk K‡i bvB)','77','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(65,'q106',' Don’t know','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(66,'q107','Farmer (Paddy)','01 = K…wlKvR (avb))','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(67,'q107','02 = Farmer (Other than paddy)','02 = K…wlKvR (avb Qvov Ab¨vb¨)','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(68,'q107','03 = Agriculture Day laborer','03 = K…wl w`bgRyi','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(69,'q107','04 = Unskilled day laborer','04 = A`¶ w`bgRyi','4','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(70,'q107','05 = Skilled day laborer ','05 = `¶ w`bgRyi','5','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(71,'q107','06 = Rickshaw/van/cart puller /boatman','06 = wi·v/f¨vb/‡VjvMvox/‡bŠKvi gvwS ','6','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(72,'q107','07 = Fisherman ','07 = ‡R‡j ','7','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(73,'q107','08 = Salaried worker','08 = PvKzixRxwe','8','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(74,'q107','09 = Professional ','09 = ‡ckvRxwe ','9','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(75,'q107','10 = Businessman ','10 = e¨emvqx ','10','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(76,'q107','11 = Petty businessman','11 = ¶z‡` e¨emvqx','11','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(77,'q107','13 = Household help','13 = M„ncwiPvwiKv ','12','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(78,'q107','14 = Mountain farming  ','14 = RygPvlx','14','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(79,'q107','15= Poultry/ Livestock ','15= nuvm/gyiMx cvjb/cï cvjb','15','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(80,'q107','16= Handicrafts ','16= n¯Íwkí','16','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(81,'q107','17= Vegetable Cultivation','17= kvK-mewR Pvl','17','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(82,'q107','18 = Pisciculture ','18= gvQ Pvl ','18','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(83,'q107','19 =Factory/Mill Worker','19 = d¨v±ix/wgj kªwgK ','19','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(84,'q107','20 =House wife','20 = M„wnbx','20','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(85,'q107','21=Student','21 = QvÎ/QvÎx','21','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(86,'q107','22 = No income','22 = DcvR©b K‡i bv','22','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(87,'q107','23=Abroad','23 = we‡`k _v‡K ','23','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(88,'q107','24=Driver/baby taxi driver','24 = WªvBfvi/‡eex U¨vw· WªvBfvi','24','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(89,'q107','77 = Others (Specify………………)','77 = Ab¨vb¨ (wbw`©ó Kiæb…………)','77','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(90,'q107','88= Don’t know','88= Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(91,'q107','99=Not applicable  (age<5years ) ','99 = cÖ‡Rvh¨ bv (eqm<5)','99','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(92,'q108','General member','mvavib m`m¨ ','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(93,'q108','Guest','AwZw_','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(94,'q108','Flood effected','eb¨vq AvµvšÍ','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(95,'q109','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(96,'q109','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(97,'q109','N/A','cÖ‡hvR¨ bq','99','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(98,'q110','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(99,'q110','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(100,'q110','N/A','cÖ‡hvR¨ bq','99','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(101,'q111','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(102,'q111','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(103,'q111','DK','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(104,'q111','N/A','cÖ‡hvR¨ bq','99','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(105,'q112','Not eligible ','A‡hvM¨','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(106,'q112','Not able to be interviewed because not at home/sick ','Abycw¯’Z/Amy¯’','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(107,'q112','Not selected','Awbe©vwPZ','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(108,'q112','Selected but survey/measurement not completed','wbe©vwPZ wKš‘ mv¶vrKvi/cwigvc Am¤úbœ ','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(109,'q112','Interviewed as mother ','gv wnmv‡e mv¶vrKvi MÖnY','4','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(110,'q112','Selected, interviewed and measured','wbe©vwPZ, mv¶vrKvi I cwigvc m¤úbœ','5','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(111,'q113','No','bv','0','q115');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(112,'q113','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(113,'q113','DK','Rvwbbv','8','q115');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(114,'q114','Farmer (Paddy)','01 = K…wlKvR (avb))','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(115,'q114','02 = Farmer (Other than paddy)','02 = K…wlKvR (avb Qvov Ab¨vb¨)','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(116,'q114','03 = Agriculture Day laborer','03 = K…wl w`bgRyi','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(117,'q114','04 = Unskilled day laborer','04 = A`¶ w`bgRyi','4','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(118,'q114','05 = Skilled day laborer ','05 = `¶ w`bgRyi','5','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(119,'q114','06 = Rickshaw/van/cart puller /boatman','06 = wi·v/f¨vb/‡VjvMvox/‡bŠKvi gvwS ','6','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(120,'q114','07 = Fisherman ','07 = ‡R‡j ','7','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(121,'q114','08 = Salaried worker','08 = PvKzixRxwe','8','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(122,'q114','09 = Professional ','09 = ‡ckvRxwe ','9','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(123,'q114','10 = Businessman ','10 = e¨emvqx ','10','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(124,'q114','11 = Petty businessman','11 = ¶z‡` e¨emvqx','11','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(125,'q114','13 = Household help','13 = M„ncwiPvwiKv ','12','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(126,'q114','14 = Mountain farming  ','14 = RygPvlx','14','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(127,'q114','15= Poultry/ Livestock ','15= nuvm/gyiMx cvjb/cï cvjb','15','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(128,'q114','16= Handicrafts ','16= n¯Íwkí','16','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(129,'q114','17= Vegetable Cultivation','17= kvK-mewR Pvl','17','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(130,'q114','18 = Pisciculture ','18= gvQ Pvl ','18','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(131,'q114','19 =Factory/Mill Worker','19 = d¨v±ix/wgj kªwgK ','19','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(132,'q114','20 =House wife','20 = M„wnbx','20','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(133,'q114','21=Student','21 = QvÎ/QvÎx','21','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(134,'q114','22 = No income','22 = DcvR©b K‡i bv','22','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(135,'q114','23=Abroad','23 = we‡`k _v‡K','23','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(136,'q114','24=Driver/baby taxi driver','24 = WªvBfvi/‡eex U¨vw· WªvBfvi','24','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(137,'q114','77 = Others (Specify………………)','77 = Ab¨vb¨ (wbw`©ó Kiæb…………)','77','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(138,'q114','88= Don’t know','88= Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(139,'q114','99=Not applicable  (age<5years ) ','99 = cÖ‡Rvh¨ bv (eqm<5)','99','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(140,'q201_Months','Month','gvm','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(141,'q201_Years','Year','ermi','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(142,'q203','Cannot read at all','GKUyI co‡Z cv‡ib wb','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(143,'q203','Able to read only parts of sentence','evK¨wUi Askwe‡kl co‡Z †c‡i‡Qb','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(144,'q203','Able to read whole sentence','m¤ú~Y© evK¨wU co‡Z †c‡i‡Qb','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(145,'q203','No sentence in required language (Specify language…………)','evK¨wU Zvi Rvbv fvlvq †jLv wQjbv (fvlvwU wbw`©ó Ki“b) -----------------','4','q203Other');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(146,'q203','Blind / mute, visually/ speech impaired','AÜ/†evev/evK cÖwZeÜx ','5','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(147,'q204_Days','Days','w`b','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(148,'q204_Months','Months','gvm','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(149,'q204_Years','Year','ermi','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(150,'q205','No, weight not taken at birth','bv, IRb †bqv nqwb','0','q208');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(151,'q205','Yes, weight taken','n¨uv, IRb †bqv n‡q‡Q','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(152,'q205','Not applicable (age<3 days)','cÖ‡hvR¨ bq (3 w`b c~Y© nq bvB)','99','q208');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(153,'q205','Don’t know','Rvwbbv','88','q208');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(154,'','','','','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(155,'q206','Never had a card/document','KvW©/WKz‡g›U © KL‡bv wQjbv  ','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(156,'q206','Card/document lost/damaged','KvW©©/WKz‡g›U nvwi‡q‡Q/bó n‡q‡Q','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(157,'q206','Card/document could not be shown ','KvW©©/WKz‡g›U †`Lv‡Z cv‡iwb ','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(158,'q206','Card/document shown','KvW©©/WKz‡g›U †`wL‡q‡Q Ges IRb wjLv Av‡Q','4','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(159,'q206','Card/document shown but no weight recorded','KvW©/WKz‡g›U © †`wL‡q‡Q wKš‘ IRb wjLv ‡bB','5','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(160,'q208','No','bv','0','q210');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(161,'q208','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(162,'q208','Don’t know','Rvwb bv  ','88','q210');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(163,'q210','No','bv','0','q212');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(164,'q210','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(165,'q211_1','Immediately','mv‡_ mv‡_B','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(166,'q211_2','< 1 hour','GK N›Uvi Kg mg‡qi g‡a¨','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(167,'q211_3','< 24 hours (01-23h)','24 N›Uvq Kg mg‡qi g‡a¨','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(168,'q211_4','>1day','GKw`‡bi †ekx','4','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(169,'q211_88','Don’t know ','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(170,'','','','','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(171,'q212','No','bv','0','q218');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(172,'q212','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(173,'q213_a','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(174,'q213_a','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(175,'q213_a','Don’t know ','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(176,'q213_b','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(177,'q213_b','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(178,'q213_b','Don’t know ','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(179,'q213_c','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(180,'q213_c','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(181,'q213_c','Don’t know ','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(182,'q213_d','No','bv','0','q213_e');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(183,'q213_d','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(184,'q213_d','Don’t know ','Rvwbbv','88','q213_e');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(185,'q213_e','No','bv','0','q213_f');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(186,'q213_e','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(187,'q213_e','Don’t know ','Rvwbbv','88','q213_f');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(188,'q213_f','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(189,'q213_f','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(190,'q213_f','Don’t know ','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(191,'q213_g','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(192,'q213_g','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(193,'q213_g','Don’t know ','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(194,'q213_h','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(195,'q213_h','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(196,'q213_h','Don’t know ','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(197,'q213_i','No','bv','0','q213_j');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(198,'q213_i','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(199,'q213_i','Don’t know ','Rvwbbv','88','q213_j');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(200,'q213_j','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(201,'q213_j','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(202,'q213_j','Don’t know ','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(203,'q213_k','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(204,'q213_k','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(205,'q213_k','Don’t know ','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(206,'q213_l','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(207,'q213_l','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(208,'q213_l','Don’t know ','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(209,'q213_m','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(210,'q213_m','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(211,'q213_m','Don’t know ','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(212,'q213_n','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(213,'q213_n','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(214,'q213_n','Don’t know ','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(215,'q213_o','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(216,'q213_o','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(217,'q213_o','Don’t know ','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(218,'q213_p','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(219,'q213_p','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(220,'q213_p','Don’t know ','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(221,'q213_q','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(222,'q213_q','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(223,'q213_q','Don’t know ','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(224,'q213_r','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(225,'q213_r','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(226,'q213_r','Don’t know ','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(227,'q213_s','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(228,'q213_s','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(229,'q213_s','Don’t know ','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(230,'q213_t','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(231,'q213_t','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(232,'q213_t','Don’t know ','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(233,'q213_u','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(234,'q213_u','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(235,'q213_u','Don’t know ','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(236,'q213_v','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(237,'q213_v','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(238,'q213_v','Don’t know ','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(239,'q213_w','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(240,'q213_w','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(241,'q213_w','Don’t know ','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(242,'q213_x','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(243,'q213_x','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(244,'q213_x','Don’t know ','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(245,'q213_y','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(246,'q213_y','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(247,'q213_y','Don’t know ','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(248,'q213_z','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(249,'q213_z','Yes','n¨v','1','q213Other');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(250,'q213_z','Don’t know ','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(251,'q214','No','bv','0','q216');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(252,'q214','Yes','n¨v','1','q213_i');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(253,'q216','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(254,'q216','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(255,'q216','Don’t know','Rvwb bv  ','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(256,'q217','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(257,'q217','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(258,'q217','Don’t know','Rvwb bv  ','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(259,'q218','No','bv','0','q219');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(260,'q218','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(261,'q218','Don’t know','Rvwb bv  ','88','q219');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(262,'q219','No','bv','0','q220');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(263,'q219','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(264,'q220','No','bv','0','sec3');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(265,'q220','Yes','n¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(266,'q301','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(267,'q301','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(268,'q301','Don’t know','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(269,'q301','Not applicable (<12 months)','cÖ‡hvR¨ bq (12 gv‡mi Kg)','99','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(270,'q302','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(271,'q302','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(272,'q302','Don’t know','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(273,'q303','Used toilet facility','cvqLvbvq','1','sec4');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(274,'q303','Used potty','cwU‡Z','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(275,'q303','Floor','N‡ii †g‡S‡Z','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(276,'q303','Used washable diapers','Wvqvcvi (‡aŠZ‡hvM¨)','4','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(277,'q303','Used disposable diapers','Wvqcvi (GKevi e¨envi‡hvM¨)','5','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(278,'q303','Defecated in his cloths ','wkïi e¨eüZ Kvc‡o','6','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(279,'q303','Defecated on the premises/yard.','evoxi Avw½bv/DVv‡b','7','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(280,'q303','Went outside the house','evoxi evwn‡i','8','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(281,'q303','Other (specify……….)','Ab¨vb¨ (wbw`©ó Kiæb........)','77','q303Other');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(282,'q303','Don’t know','Rvwbbv','88','sec4');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(283,'q304','Thrown/dropped in the toilet','cvqLvbvq †djv n‡q‡Q','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(284,'q304','Discarded in the drain','†WªB‡b/bvjvq †djv n‡q‡Q','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(285,'q304','Discarded in the yard/premise','Avw½bv/DVv‡b †djv n‡q‡Q','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(286,'q304','Discarded outside premise','Avw½bvi evwn‡i †djv n‡q‡Q','4','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(287,'q304','Disposed into waste/trash bin','AveR©bv ivLvi ev‡· †djv n‡q‡Q','5','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(288,'q304','Buried','gvwU‡Z cy‡Z †djv n‡q‡Q','6','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(289,'q304','Put into fixed place','wbw`©ó ¯’v‡b †djv n‡q‡Q','7','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(290,'q304','Left where child defecated','†hLv‡b cvqLvbv K‡i‡Q †mLv‡bB i‡q‡Q','8','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(291,'q304','By water thrown/dropped in the toilet','cvwb w`‡q cvqLvbvq †djv n‡q‡Q','9','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(292,'q304','Other (specify……….)','Ab¨vb¨ (wbw`©ó Kiæb)...','77','q304Other');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(293,'q304','Don’t know','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(294,'q404','No','bv','0','q421');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(295,'q404','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(296,'q405','No','bv','0','q410');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(297,'q405','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(298,'q406','No','bv','0','q410');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(299,'q406','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(300,'q406','Don’t know','Rvwbbv','88','q410');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(301,'q406a_Days','Days ago','w`b Av‡M','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(302,'q406a_Weeks','Weeks ago','mßvn Av‡M ','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(303,'q406a_Months','Months ago','gvm Av‡M','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(304,'q407','Family Welfare Assistant (FWA)/field staff','cwievi Kj¨vb mnKvix (GdWwe­DG) /gvVKgx©','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(305,'q407','Health Facility (CC, FWC, UHC)','¯^v¯’¨‡K›`ª (wmwm, GdWwe­Dwm, BDGBPwm) ','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(306,'q407','Satelite clinic/EPI   centre','m¨v‡UjvBU wK¬wbK/BwcAvB †K›`ª ','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(307,'q407','Others (Specify)____________','Ab¨vb¨ (wjLyb) ..................','77','q407Other');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(308,'q407','Don’t know','Rvwbbv','88','q409');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(309,'q407','Purchased from a shop ','‡`vKvb †_‡K µq K‡iwQ','4','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(310,'q408_Minutes','Minutes','wgwbU','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(311,'q408_Hours','Hours','N›Uv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(312,'q410','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(313,'q410','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(314,'q410','Don’t know','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(315,'q411','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(316,'q411','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(317,'q411','Don’t know','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(318,'q412','No','bv','0','q414');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(319,'q412','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(320,'q412','Don’t know','Rvwbbv','88','q414');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(321,'q414','No','bv','0','q416');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(322,'q414','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(323,'q414','Don’t know','Rvwbbv','88','q416');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(324,'q416','No','bv','0','q417');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(325,'q416','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(326,'q416','Don’t know','Rvwbbv','77','q417');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(327,'q416a_1','No. of unopened sachets','†Lvjv nqwb GiKg m¨v‡P‡Ui msL¨v ','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(328,'q416a_2','Already consumed (used sachets)','LvIqv‡bv n‡q †M‡Q (e¨eüZ m¨v‡PUm)','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(329,'q416a_3','Damaged','bó n‡q †M‡Q','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(330,'q417','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(331,'q417','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(332,'q417','Don’t know','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(333,'q418','No','bv','0','q421');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(334,'q418','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(335,'q418','Don’t know','Rvwbbv','88','q421');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(336,'','','','','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(337,'q419','Yesterday','MZKvj','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(338,'q419','In the last week','MZmßv‡n','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(339,'q419','More than 1 week ago but less than one month ago','GK mßv‡ni †ekx wKšÍy GK gv‡mi Kg mg‡qi g‡a¨','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(340,'q419','More than one month ago','GK gv‡mi †ekx mg‡qi c~‡e©','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(341,'q419','Don’t know','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(342,'q420_a','MNP/pustikana/monimix helps  in brain development','wfUvwgb wgbv‡ij cvDWvi/cywó Kbv/gwbwg·  gw¯Í¯‹ weKv‡k mnvqZv K‡i','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(343,'q420_b','MNP/pustikana/monimix makes child active/strong','wfUvwgb wgbv‡ij cvDWvi/cywó Kbv/gwbwg·  wkï‡K Kg©¶g/mej K‡i','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(344,'q420_c','MNP/pustikana/monimix increases appetite','wfUvwgb wgbv‡ij cvDWvi/cywó Kbv/gwbwg· ¶zav e„w× K‡i','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(345,'q420_d','MNP/pustikana/monimix reduces anemia','wfUvwgb wgbv‡ij cvDWvi/cywó Kbv/gwbwg·  i³¯^íZv Kgvq','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(346,'q420_e','MNP/pustikana/monimix is available for free ','wfUvwgb wgbv‡ij cvDWvi/cywó Kbv/gwbwg· webvg~‡j¨ cvIqv hvq','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(347,'q420_f','MNP/pustikana/monimix applies for 6-23 months old child','wfUvwgb wgbv‡ij cvDWvi/cywó Kbv/gwbwg·  6-23 gvm eq‡mi wkïi Rb¨ cÖ‡Rvh¨','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(348,'q420_g','MNP/pustikana/monimix is available from Family Welfare Assistant (FWA)/field staff and health facility','cwievi Kj¨vb mnKvix (GdWwe­DG)//gvVKgx© I ¯^v¯’¨‡K›`ª †_‡K wfUvwgb wgbv‡ij cvDWvi/cywó Kbv/gwbwg· cvIqv hvq','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(349,'q420_h','MNP/pustikana/monimix contains vitamins and minerals','wfUvwgb wgbv‡ij cvDWvi/cywó Kbv/gwbwg· -G wfUvwgb I LwbR c`v_© _v‡K','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(350,'q420_i','Others (Specify)____________','Ab¨vb¨ (wjLyb)...........','1','q420Other');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(351,'q420_j','Don’t know','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(352,'q421','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(353,'q421','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(354,'q421','Don’t know','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(355,'q501','No','bv','0','q502');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(356,'q501','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(357,'q501a','Rarely (1–2 times)','K`vwPr (1-2evi)','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(358,'q501a','Sometimes (3–10 times)','gv‡S gv‡S (3-10evi)','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(359,'q501a','Often (more than 10 times)','cÖvqB (10ev‡ii †ekx)','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(360,'q502','No','bv','0','q503');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(361,'q502','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(362,'q502','Don`t know','Rvwbbv','88','q503');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(363,'q502a','Rarely (1–2 times)','K`vwPr (1-2evi)','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(364,'q502a','Sometimes (3–10 times)','gv‡S gv‡S (3-10evi)','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(365,'q502a','Often (more than 10 times)','cÖvqB (10ev‡ii †ekx)','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(366,'q503','No','bv','0','sec6');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(367,'q503','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(368,'q503','Don`t know','Rvwbbv','88','sec6');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(369,'q503a','Rarely (1–2 times)','K`vwPr (1-2evi)','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(370,'q503a','Sometimes (3–10 times)','gv‡S gv‡S (3-10evi)','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(371,'q503a','Often (more than 10 times)','cÖvqB (10ev‡ii †ekx)','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(372,'q602','No','bv','0','q604');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(373,'q602','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(374,'q602','Don`t know','Rvwbbv','88','q604');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(375,'q604','No','bv','0','q606');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(376,'q604','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(377,'q604','Don`t know','Rvwbbv','88','q606');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(378,'q607_a','Electricity','we`y¨r ','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(379,'q607_b','Solar Panel','†mŠiwe`y¨r','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(380,'q607_c','Radio/cassette player','†iwWI/K¨v‡mU †c­qvi','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(381,'q607_d','Television','†Uwjwfkb','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(382,'q607_e','Telephone/mobile phone','†Uwj‡dvb/‡gvevBj †dvb','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(383,'q607_f','Electric fan','‰e`y¨wZK cvLv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(384,'q607_g','Khat/chawki','LvU/‡PŠwK','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(385,'q607_h','Almirah/Showcase','Avjgvwi/‡kv‡Km','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(386,'q607_i','Refrigerator','†iwd«Rv‡iUi','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(387,'q607_j','Table/chair','†Uwej/‡Pqvi','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(388,'q607_k','Watch/Wall clock','Nwo/‡`qvj Nwo','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(389,'q607_l','Bicycle','mvB‡Kj','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(390,'q607_m','Motorcycle/scooter/tempo','†gvUimvB‡Kj/¯‹zUvi/‡U‡¤úv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(391,'q607_n','Animal drawn cart','cïPvwjZ Mvwo','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(392,'q607_o','Car/truck/Bus','Kvi/UªªvK/evm','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(393,'q607_p','Country boat','†`kxq ‡bŠKv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(394,'q607_q','Motor boat ','BwÄbPvwjZ †bŠKv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(395,'q607_r','Rickshaw/van','wi·v/f¨vb','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(396,'q607_s','Plough','jv½j','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(397,'q607_t','Power Tiller','cvIqvi wUjvi','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(398,'q607_u','Shallow machine','k¨v‡jv †gwkb','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(399,'q607_v','IPS/generator ','AvB.wc.Gm/ †Rbv‡iUi','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(400,'q607_w','Fishing net','gvQ aivi Rvj','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(401,'q607_x','All no','wKQzB bvB','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(402,'q608','No walls','†`qvj bvB','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(403,'q608','Cane leaf/ sugarcane leaf /palm leaf ','†eZ cvZv/B¶z RvZxq cvZv/Zvj cvZv ','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(404,'q608','Dirt','Kv`v gvwU','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(405,'q608','Bamboo with mud','euvk I gvwU','4','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(406,'q608','Stone with mud','cv_i I gvwU','5','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(407,'q608','Plywood','cøvBDW','6','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(408,'q608','Cardboard','KvW© †evW','7','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(409,'q608','Tin','wUb','8','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(410,'q608','Cement & Bricks','BU I wm‡g›U','9','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(411,'q608','Stone with lime/cement','cv_i I Pzb/wm‡g›U','10','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(412,'q608','Wood planks/shingles','Kv‡Vi Z³v/djK','11','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(413,'q608','Polythin','cwjw_b','12','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(414,'q608','Other (specify)……','Ab¨vb¨ (wbw`©ó Kiæb)...... ','13','q608Other');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(415,'q609','No roof','Qv` bvB','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(416,'q609','Straw/thatch /palm leaf','Lo/Qb/ZvjcvZv/‡Mvj cvZv ','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(417,'q609','Bamboo with mud','evuk I gvwU','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(418,'q609','Wood planks','Kv‡Vi Z³v','4','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(419,'q609','Cardboard','KvW© †evW','5','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(420,'q609','Tin','wUb','6','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(421,'q609','Wood','KvV','7','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(422,'q609','Tiles',' UvBjm','8','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(423,'q609','Cement & bricks','BU-wm‡g›U','9','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(424,'q609','Polythin','cwjw_b','10','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(425,'q609','Other (specify)……','Ab¨vb¨ (wbw`©ó Kiæb)........ ','11','q609Other');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(426,'q610','Earth/sand','gvwU/evwj','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(427,'q610','Wood planks','Kv‡Vi Z³v','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(428,'q610','Palm/Bamboo','Zvj MvQ/euvk','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(429,'q610','Ceramic tiles','wmivwgK UvBjm','4','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(430,'q610','Cement/Bricks','BU-wm‡g›U','5','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(431,'q610','Mosiac','‡gvRvBK','6','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(432,'q610','Other (specify)……','Ab¨vb¨ (wbw`©ó Kiæb)....','7','q610Other');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(433,'q612','Electricity','we`y¨r','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(434,'q612','LPG','GjwcwR','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(435,'q612','Piped Natural gas','cvBchy³ cÖvK…wZK M¨vm','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(436,'q612','Kerosene','†K‡ivwmb','4','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(437,'q612','Coal/Lignite ','Kqjv/wjMbvBU','5','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(438,'q612','Charcoal','Pvi‡Kvj Kqjv ','6','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(439,'q612','Wood','KvV','7','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(440,'q612','Straw/grass/agricultural waste','cvULwo/Lo/bvov/Nvm cvZv/K…wlcwiZ¨v³ wRwbm','8','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(441,'q612','Animal dung ','†Mvei','9','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(442,'q612','Other (specify)……','Ab¨vb¨ (wbw`©ó Kiæb)...... ','10','q612Other');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(443,'q613_a','Cow/buffalo','Miæ/gwnl','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(444,'q613_b','Sheep/Goat/Pig','†fov/QvMj/ïKi','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(445,'q613_c','Chicken/Duck/Geese','gyiMx/nuvm/ivRnuvm','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(446,'q613_d','Small game (Rabbits, Pigeons,etc)','¯§j †Mg (eb¨ cï/cvLx) †hgb: Li‡Mvk, KeyZi BZ¨vw`','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(447,'q613_e','Others (Specify)…..','Ab¨vb¨ (wbw`©ó Kiæb).....','1','q613Other');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(448,'q614','No','bv','0','q616');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(449,'q614','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(450,'q616','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(451,'q616','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(452,'q616a','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(453,'q616a','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(454,'q617','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(455,'q617','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(456,'q617','No male person','‡Kvb cyi“l m`m¨ bvB','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(457,'q618','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(458,'q618','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(459,'q619','No','bv','0','q621');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(460,'q619','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(461,'q619','Don`t know','Rvwbbv','88','q621');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(462,'q619a_1','Food for work (KABIKHA)','Kv‡Ri wewbg‡q Lv`¨ (KvweLv)','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(463,'q619a_2','Money for work (KABITA)','Kv‡Ri wewbg‡q UvKv (KvweUv)','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(464,'q619a_3','Vulnerable group feeding (VGF)','SzuwKc~Y© Rb‡Mvwôxi Rb¨ Lv`¨ Kg©m~Px ','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(465,'q619a_4','Vulnerable group development (VGD)','SzuwKc~Y© Rb‡Mvwôxi Rb¨ Dbœqb Kg©m~Px ','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(466,'q619a_5',' Assistance for destitute','`yt¯’ mvnvh¨ Kg©m~Px','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(467,'q619a_6',' Elderly allowance ','e„× fvZv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(468,'q619a_7','Conditional cash transfer from NGO','NGO cwiPvwjZ kZ©hy³ A_©`vb Kg©m~Px ','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(469,'q619a_8',' Other (specify)………….._','Ab¨vb¨ (wbw`©ó Kiæb) ... ','1','q619aOther');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(470,'q619a_9','Don`t know','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(471,'q621','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(472,'q621','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(473,'q621','Don`t know','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(474,'q622','No','bv','0','sec7');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(475,'q622','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(476,'q622','Don`t know','Rvwbbv','88','sec7');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(477,'','','','','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(478,'q623_a','Flood/water logging','eb¨v/Rjve×Zv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(479,'q623_b','Tidal surge','R‡jv”Qvm','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(480,'q623_c','Erosion of land','b`x fv½b','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(481,'q623_d','Death of income earner','DcvR©bKvixi g„Zz¨','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(482,'q623_e','Other death','Ab¨ Kv‡iv g„Zz¨','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(483,'q623_f','Major illness','¸iæZi Amy¯’Zv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(484,'q623_g','Loss of income source','DcvR©‡bi Drm nviv‡bv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(485,'q623_h','Other (specify)….','Ab¨vb¨ (wbw`©ó Kiæb) ... ','1','q623Other');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(486,'q701','Piped sewer system','my¨qv‡iR jvB‡bi mv‡_ hy³','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(487,'q701','Latrine with septic tank','m¨vwÞK U¨vsKmn cvqLvbv','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(488,'q701','Ring slab with water seal','IqvUvi wmjmn wis¯­¨ve','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(489,'q701','Ring slab without water seal','IqvUvi wmj Qvov wis¯­¨ve','4','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(490,'q701','Pit latrine with slab','¯­¨vemn wcU cvqLvbv ','5','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(491,'q701','Pit latrine without slab','¯­¨veQvov †LvjvwcU','6','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(492,'q701','Hanging latrine','SzjšÍ cvqLvbv','7','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(493,'q701','No facility','cvqLvbv bvB','8','q705');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(494,'q701','Other (specify)…..','Ab¨vb¨ (wbw`©ó Kiæb)......','77','q701Other');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(495,'q702','No','bv','0','q705');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(496,'q702','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(497,'q702','Don`t know','Rvwbbv','88','q705');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(498,'q703','Other households only (not public)','cwiwPZ Ab¨vb¨ Lvbv e¨envi K‡i (mvavi‡bi Rb¨ Db¥~³ bv)','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(499,'q703','Public facility','mvavi‡bi Rb¨ Db¥~³ ','2','q705');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(500,'q704','Number of households  (if less than 10)','10 wUi Kg Lvbv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(501,'q704','Ten or more than 10 households ','10 wU ev Zvi †ekx Lvbv','10','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(502,'','','','','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(503,'q704','Don’t know','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(504,'q705','Piped into dwelling','evmvi wfZ‡i mieivnK…Z b‡ji cvwb','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(505,'q705','Piped into compound, yard or plot','evmvi Avw½bvq mieivnK…Z b‡ji cvwb','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(506,'q705','Piped to neighbor','cÖwZ‡ekxi b‡ji cvwb','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(507,'q705','Public tap / standpipe','me©mvavi‡bi e¨eüZ cvwbi Kj /iv¯Ívi cv‡k¦©i cvwbi Kj','4','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(508,'q705','Tube Well, Borehole','bjK~‡ci cvwb/Mfxi bjK~c ','5','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(509,'q705','Protected well','msiw¶Z K~c ','6','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(510,'q705','unprotected well','Amsiw¶Z Db¥~³ K~c','7','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(511,'q705','Protected spring','msiw¶Z SY©v','8','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(512,'q705','Unprotected spring','Amsiw¶Z SY©v','9','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(513,'q705','Rainwater collection','msM„wnZ e„wói cvwb ','10','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(514,'q705','Tanker-truck','UªvKevwnZ cvwbi U¨vsK','11','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(515,'q705','Cart with small tank / drum','‡VjvMvox evwnZ †QvU cvwbi U¨vsK/Wªvg','12','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(516,'q705','Surface water (river, stream, dam, lake, pond, canal, irrigation channel)','f~-Dcwifv‡Mi cvvwb (b`x, n«`, cyKzi, Lvj, wej, ‡m‡Pi bvjv)','13','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(517,'q705','Tube Well or Non-borehole','Pvc Kj / AMfxi bjK~c ','14','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(518,'q705','Other (specify) --------------','Ab¨vb¨ (wbw`©ó Kiæb)...... ','88','q705Other');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(519,'q706','No','bv','0','q708');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(520,'q706','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(521,'q706','Don`t know','Rvwbbv','88','q708');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(522,'q707_a ','Boil','dzUv‡bv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(523,'q707_b','Add bleach / chlorine','we­wPs cvDWvi/†K¬vwib ','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(524,'q707_c','Strain it through a cloth','Kvco w`‡q QvKv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(525,'q707_d','Use water filter (ceramic, sand,      composite, etc.)','IqvUvi wdëvi (wmivwgK,  evjy, K‡¤úvwmU BZ¨vw`)','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(526,'q707_e','Solar disinfection','m~‡q©i Zv‡c','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(527,'q707_f','Let it stand and settle','w_Zv‡bv ','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(528,'q707_g','Other (specify…..)','Ab¨vb¨ (wbw`©ó Kiæb)......','1','q707Other');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(529,'q707_h','Don’t Know','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(530,'q708','Observed','ch©‡e¶Y Kiv n‡q‡Q','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(531,'q708','Not observed','ch©‡e¶Y Kiv nq bvB','0','q711');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(532,'q708','Not in dwelling / plot / yard','evmvi wfZi/Avw½bv/mxgvbvi g‡a¨ bv','2','q711');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(533,'q708','No permission to see','ch©‡e¶Y Ki‡Z AbygwZ †`q bvB','3','q711');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(534,'q708','Other reason (specify)……………','Ab¨vb¨ (wbw`©ó Kiæb)......','77','q708Other');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(535,'q709','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(536,'q709','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(537,'q710_a','Bar soap','mvevb ','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(538,'q710_b','Detergent (Powder / Liquid / Paste)','wWUv‡R©›U (¸ov/Zij/†có)','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(539,'q710_c','Liquid soap','Zij mvevb','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(540,'q710_d','Ash / Mud / Sand','QvB/gvwU/evjy','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(541,'q710_e','None','wKQyB bv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(542,'q711','No','bv','0','q713');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(543,'q711','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(544,'q712_a','Bar soap','mvevb ','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(545,'q712_b','Detergent (Powder / Liquid / Paste)','wWUv‡R©›U (¸ov/Zij/†có)','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(546,'q712_c','Liquid soap','Zij mvevb','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(547,'q712_d','Ash / Mud / Sand','QvB/gvwU/evjy','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(548,'q712_e','Not able/Does not want to show','‡`Lv‡Z cv‡ib wb / †`Lv‡Z Pvb wb','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(549,'q713','No','bv','0','q715');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(550,'q713','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(551,'q714','No','bv','0','sec8');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(552,'q714','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(553,'q715_a','Washing cloths','Kvco †avqvi mgq','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(554,'q715_b','Washing my body','wb‡Ri ‡Mvm‡ji Rb¨','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(555,'q715_c','Washing my Children','wkï‡K †Mvmj Kiv‡bvi mgq','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(556,'q714_d','Washing child’s bottoms','wkï‡K †kŠP Kiv‡bvi ci','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(557,'q715_e','Washing my children’s hands','wkïi nvZ †avqv‡bvi Rb¨','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(558,'q714_f','Washing hands after defecating','‡kŠP Kv‡Ri ci nvZ †avqvi Rb¨ ','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(559,'q715_g','Washing hands before feeding child','wkï‡K LvIqv‡bvi Av‡M nvZ †avqvi Rb¨','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(560,'q715_h','Washing hands before preparing food','Lv`¨ ˆZwii Av‡M nvZ †avqvi Rb¨','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(561,'q715_i','Washing hands before eating','LvIqvi Av‡M nvZ †avqv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(562,'q715_j','Other (specify)…. ','Ab¨vb¨ (wbw`©ó Kiæb) ...','1','q715Other');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(563,'q801','Living with husband ','¯^vgxi mv‡_ emevm  K‡i','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(564,'q801','Separated','Avjv`v _v‡K  ','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(565,'q801','Divorced ','ZvjvKcÖvßv','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(566,'q801','Husband is dead ','weaev (¯^vgx g„Z)','4','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(567,'q801','Didn’t marry ',' AweevwnZ','5','q802');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(568,'q801','Other (specify)…..','Ab¨vb¨ (wbw`ó Kiæb) ---','77','q801Other');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(569,'q802_a','Liver, kidney, heart, or other organ meats','KwjRv, e„°, ürwcÛ A_ev Ab¨vb¨ AM©vb ','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(570,'q802_b','Meat, such as beef, pork, lamb, goat, chicken, or duck','gvsm †hgbÑMiæ, †fov, QvMj, ïKi, gyiMx ev nuvm','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(571,'q802_c','Eggs','wWg','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(572,'q802_d','Fresh or dried fish, shellfish, or seafood','gvQ, myUKx gvQ, kvgyK, ev mvgyw`ªK Lvevi ','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(573,'q802_e','Milk, cheese, yogurt, or other milk products','`ya, cwbi, `B, ev `y»RvZ Lvevi ','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(574,'q802_f','Other foods that came from an animal.   (Write down other foods the respondent names that come from an animal) --------------','Ab¨vb¨ cÖvvbxR Lvevi (DËi`vZv Ab¨vb¨ †h me cÖvYxR Lvev‡ii D‡j­K Ki‡e Zv wjLyb) --------------','1','q802Other');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(575,'','','','','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(576,'q805','No','bv','0','q808');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(577,'q805','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(578,'q806','In the 7 days','mvZ w`‡bi g‡a¨','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(579,'q806','More than 7 days ago but less than 30 days ago','mvZ w`‡bi †ekx wKš‘ 30 w`‡bi Kg ','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(580,'q806','More than 30 days ago but less than 60 days ago','30 w`‡bi †ekx wKš‘ 60 w`‡bi Kg ','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(581,'q806','More than 2 months ago','2 gv‡miI †ekx Av‡M ','4','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(582,'q807','Once','GKevi','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(583,'q807','Twice','`yBevi','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(584,'q807','Three times','wZbevi','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(585,'q807','Four times','Pvievi','4','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(586,'q807','More than four times','Pviev‡ii †ekx','5','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(587,'q807','Don’t know','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(588,'q808','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(589,'q808','Yes','n¨vu','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(590,'q808','Received 5 dose earlier','c~‡e©B 5wU wUKv †c‡qwQ','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(591,'q809','No','bv','0','q815');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(592,'q809','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(593,'q809','Don`t know','Rvwbbv','88','q815');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(594,'q810_Days','Days ago','w`b Av‡M','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(595,'q810_Weeks','Weeks ago','mßvn Av‡M ','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(596,'q810_Months','Months ago','gvm Av‡M','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(597,'q811','No','bv','0','q815');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(598,'q811','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(599,'q811','Don`t know','Rvwbbv','88','q815');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(600,'q813','No','bv','0','q815');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(601,'q813','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(602,'q815','Less','Kg ','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(603,'q815','About the same','GKB cwigvY','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(604,'q815','More','¯^vfvwe‡Ki †P‡q †ekx','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(605,'q901','Living with husband ','¯^vgxi mv‡_ emevm  K‡i','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(606,'q901','Separated','Avjv`v _v‡K  ','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(607,'q901','Divorced ','ZvjvKcÖvßv','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(608,'q901','Husband is dead ','weaev (¯^vgx g„Z)','4','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(609,'q901','Didn’t marry ',' AweevwnZ','5','q902');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(610,'q901','Other (specify)…..','Ab¨vb¨ (wbw`ó Kiæb) ---','77','q901Other');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(611,'q902_a','Liver, kidney, heart, or other organ meats','KwjRv, e„°, ürwcÛ A_ev Ab¨vb¨ AM©vb ','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(612,'q902_b','Meat, such as beef, pork, lamb, goat, chicken, or duck','gvsm †hgbÑMiæ, †fov, QvMj, ïKi, gyiMx ev nuvm','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(613,'q902_c','Eggs','wWg','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(614,'q902_d','Fresh or dried fish, shellfish, or seafood','gvQ, myUKx gvQ, kvgyK, ev mvgyw`ªK Lvevi ','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(615,'q902_e','Milk, cheese, yogurt, or other milk products','`ya, cwbi, `B, ev `y»RvZ Lvevi ','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(616,'q902_f','Other foods that came from an animal.   (Write down other foods the respondent names that come from an animal) -------------','Ab¨vb¨ cÖvvbxR Lvevi (DËi`vZv Ab¨vb¨ †h me cÖvYxR Lvev‡ii D‡j­L Ki‡e Zv wjLyb) --------------','1','q902Other');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(617,'q904','No','bv','0','q906');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(618,'q904','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(619,'q905','Rarely (1-2 times a month)','K`vwPr (gv‡m 1-2evi)','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(620,'q905','Sometimes (1-2 times a week)','gv‡Sg‡a¨ (mßv‡n 1-2 evi)','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(621,'q905','Often (3-4 times a week)','cÖvqB (mßv‡n 3-4 evi)','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(622,'q905','Mostly (5 times/more than 5 times per week)','†ekxi fvM mgq (mßv‡n 5/5 ev‡ii AwaK)','4','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(623,'q905','Don’t know','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(624,'q906','No','bv','0','q908');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(625,'q906','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(626,'q908','Less','Kg ','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(627,'q908','About the same','GKB cwigvY','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(628,'q908','More','¯^vfvwe‡Ki †P‡q †ekx','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(629,'q909','Less','Kg ','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(630,'q909','About the same','cÖvq GKB iKg','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(631,'q909','More','†ekx','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(632,'q1004','Male','‡Q‡j','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(633,'q1004','Female','‡g‡q...','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(634,'q1005','Pregnant','Mf©eZx...','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(635,'q1005','Lactating','j¨vK‡UwUs','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(636,'q1005','N/A','cÖ‡hvR¨ bq','99','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(637,'q1009','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(638,'q1009','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(639,'q1009','Don`t know','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(640,'q1011','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(641,'q1011','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(642,'q1011','Don`t know','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(643,'q1013','Measured ','cwigvcK…Z','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(644,'q1013','Not measured Absent','cwigvcK…Z bq Abycw¯’Z…','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(645,'q1013','Sick/disable','Amy¯’¨/cÖwZeÜx…','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(646,'q1013','Refused...……..','Am¤§wZ...','4','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(647,'q1014','Cooperative.1','mn‡hvMx','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(648,'q1014','Non- cooperative..2','Amn‡hvMx','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(649,'q1014','Severely non cooperative.3','LyeB Amn‡hvMx','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(650,'q1014','N/A…………99','cÖ‡hvR¨ bq','99','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(651,'q1015','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(652,'q1015','Yes','n¨vu','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(653,'q1015','Don’t know','Rvwbbv','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(654,'q1015','N/A','cÖ‡hvR¨ bq','99','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(655,'q1101','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(656,'q1101','Yes','n¨uv','1','q1101b');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(657,'q1101a','No','bv','0','q1103');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(658,'q1101a','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(659,'q1101b_1',' In cash','bM`  A_© (UvKv)','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(660,'q1101b_2',' In kind','`ªe¨ mvgMÖx','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(661,'q1101b_3',' Both','DfqB ','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(662,'q1101b_4 ','Not paid','‡Kvb wKQy cvB bvB','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(663,'q1101b_5',' Other (specify…………..)','Ab¨vb¨ (wbw`ó Kiæb) -----','1','q1101bOther');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(664,'q1102',' Self/mainly wife','wb‡R/cÖavbZ ¯¿x','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(665,'q1102',' Mainly husband','cÖavbZ ¯^vgx','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(666,'q1102','. Wife and husband jointly','¯^vgx I ¯¿x †hŠ_fv‡e ','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(667,'q1102','Other (specify)-------------','Ab¨vb¨ (wbw`©ó Kiæb) -----','4','q1102Other');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(668,'q1103','No','bv','0','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(669,'q1103','Yes','n¨uv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(670,'q1104_a','Woman’s own healthcare  ','gwnjvi wbR¯^ ¯^v¯’¨‡mev','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(671,'q1104_b','Major household purchases (cow, DVD/cassette player, TV etc.)','Lvbvi †Kvb ¸iæZ¡c~Y© †KbvKvUv (Miæ, wWwfwW/K¨v‡mU ‡c­qvi, wUwf BZ¨vw`)','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(672,'q1104_c','Purchase of daily household needs (rice, oil, fuel etc.)','Lvbvi ˆ`bw›`b cÖ‡qvRbxq `ªe¨ mgMÖx (Pvj, †Zj,R¡vjvwb BZ¨vw`)','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(673,'q1104_d','Visits to her family or relatives','evevi evox A_ev Ab¨ †Kvb AvZ¥x‡qi evox †eov‡Z hvIqv','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(674,'q1104_e','Children’s healthcare','ev”Pv‡`i ¯^v¯’¨ †mev','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(675,'q1104_f','Child’s food/drink','ev”Pv‡`i Lv`¨/cvbxq ','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(676,'q1104_g','Child’s feeding problem','ev”Pv‡`i LvIqv‡bvi mgm¨v','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(677,'q1104_h','Choice of contraceptive methods ','cwievi cwiKíbvi c×wZ wbe©vPb','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(678,'q1104_i','Pregnancy','Mf©avib','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(679,'q1104_j','Mother can attend a peer support group','gv‡qi wcqvi mv‡cvU© MÖæ‡c AskMÖnY ','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(680,'q1104_Options','Respondent/Mainly wife','DËi`vZv/cÖavbZ ¯¿x','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(681,'q1104_Options','Mainly husband','cÖavbZ ¯^vgx','2','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(682,'q1104_Options','Wife and husband jointly','¯¿x I ¯^vgx †hŠ_fv‡e','3','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(683,'q1104_Options','Someone else','Ab¨ †KD ','4','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(684,'q1104_Options','NA','cÖ‡Rvh¨ bq','88','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(685,'q1105_a',' NGO',' †emiKvix ms¯’v (NGO)','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(686,'q1105_b','Local credit and savings group','¯’vbxq †µwWU Ges mÂqx msMVb','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(687,'q1105_c',' Training group (income generation activity, health)','cÖwk¶Y cÖ`vbKvix msMVb (DcvR©b mswk­ô Kvh©µg, ¯^v¯’¨)','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(688,'q1105_d',' Women’s association','gwnjv‡`i msMVb','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(689,'q1105_e',' School committee','¯‹zj KwgwU ','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(690,'q1105_f',' Association for the destitute (dustho)','`y¯’‡`i Rb¨ msMVb','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(691,'q1105_g',' Youth club','B‡qv_ K¬ve','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(692,'q1105_h','No association with above mentioned groups.','†Kvb  msMVb m`m¨ bq','1','');");
		insertQueryList
				.add("INSERT INTO \"tblOptions\" VALUES(693,'q1105_i','Others (specify____________________)','Ab¨vb¨ (wbw`©ó Kiæb)-----','1','q1105Other');");

		String emptyQuery = "DELETE FROM tblOptions";
		try {
			if (dbHelper.executeDMLQuery(emptyQuery)) {
				Log.e("tbloptions", "now empty");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		boolean a = true;
		for (String s : insertQueryList) {
			if (dbHelper.executeDMLQuery(s)) {
				// Log.e("tbloptions","update");
				// System.out.println(s);
			} else {
				a = false;
			}
		}
		if (a) {
			Message msg = new Message();
			msg.what = UPDATEDONE;
			searchHandler.sendMessage(msg);
		} else {
			Message msg = new Message();
			msg.what = UPDATEFAILED;
			searchHandler.sendMessage(msg);
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
		CommonStaticClass.totalHHMember = 1;
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