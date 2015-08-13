package com.icddrb.app.Hbissaritac;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

import com.icddrb.app.Hbissaritac.CommonStaticClass;
import com.icddrb.app.Hbissaritac.R;
import com.icddrb.app.Hbissaritac.Search.SearchLayout;
import com.icddrb.app.Hbissaritac.Viewall.Viewall;
import com.icddrb.app.Hbissaritac.adapters.QlistAdapter;

public class QListScreenForEdit extends BaseActivity {
	protected static final int UPDATEDONE = 011;
	private ListView listentries;
	private Context con;
	private ArrayList<String> qs;
	private ArrayList<String> qdescbng;
	private ArrayList<String> qdesceng;
	private ArrayList<Integer> qslno;
	private ProgressDialog progressDialog;
	// private EditText filterText = null;
	private QlistAdapter myAdapter;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qeditlist);
		con = this;
		loadGui();
		loadDataToList();
		// setSkipper(); have to work on it
	}

	private void loadGui() {
		// TODO Auto-generated method stub
		qslno = new ArrayList<Integer>();
		qs = new ArrayList<String>();
		qdescbng = new ArrayList<String>();
		qdesceng = new ArrayList<String>();

		// filterText = (EditText) findViewById(R.id.search_box);
		// filterText.addTextChangedListener(filterTextWatcher);

		listentries = (ListView) findViewById(R.id.listentries);
		myAdapter = new QlistAdapter(this, qs, qdescbng, qdesceng);
		listentries.setAdapter(myAdapter);
		listentries.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v, int pos,
					long id) {
				// TODO Auto-generated method stub
				// addtoskip();
				CommonStaticClass.currentSLNo = qslno.get(pos);
				if ((CommonStaticClass.currentSLNo >=2 && CommonStaticClass.currentSLNo<=14)) {

					
					CommonStaticClass.mode = CommonStaticClass.EDITMODE;

					/*
					 * if (checkSelectionHasDataOrNot(
					 * CommonStaticClass.questionMap.get(
					 * CommonStaticClass.currentSLNo).getQvar(),
					 * CommonStaticClass.questionMap.get(
					 * CommonStaticClass.currentSLNo) .getTablename())) {
					 */
					// addtoskip();

					progressDialog = ProgressDialog.show(con, "Loading...","Please wait while loading");

					new Thread() {

						public void run() {
							try {
								Looper.prepare();
								startQuestion();
								Message msg = new Message();
								msg.what = UPDATEDONE;
								handlerFrmHHID.sendMessage(msg);

							} catch (Exception lg) {
								progressDialog.dismiss();
								CommonStaticClass.showFinalAlert(con,
										"An Error occured in load method");

							} finally {
								progressDialog.dismiss();
							}
							Looper.loop();
						}

					}.start();

					// }
					/*
					 * else { CommonStaticClass.showMyAlert(con, "Warning!!!",
					 * "This question has no data associated with it");
					 */
					// }

				} else {
					CommonStaticClass.showMyAlert(con, "Warning!!!",
							"You can not edit this question");
				}
			}

		});

	}

	private Handler handlerFrmHHID = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UPDATEDONE:
				progressDialog.dismiss();
				break;
			}

		}
	};

	private boolean addtoskip() {

		try {

			CommonStaticClass.qskipList.remove("sec3");
			CommonStaticClass.qskipList.remove("q3_1");
			CommonStaticClass.qskipList.remove("sec4");
			CommonStaticClass.qskipList.remove("q4_1");
			CommonStaticClass.qskipList.remove("q4_2");
			CommonStaticClass.qskipList.remove("q4_3");
			CommonStaticClass.qskipList.remove("q4_4");
			CommonStaticClass.qskipList.remove("sec5");
			CommonStaticClass.qskipList.remove("q5_1");
			CommonStaticClass.qskipList.remove("q5_1b");
			CommonStaticClass.qskipList.remove("q5_1bOther");
			CommonStaticClass.qskipList.remove("q5_2");
			CommonStaticClass.qskipList.remove("q5_2b");
			CommonStaticClass.qskipList.remove("q5_2bOther");
			CommonStaticClass.qskipList.remove("q5_3");
			CommonStaticClass.qskipList.remove("q5_3b");
			CommonStaticClass.qskipList.remove("q5_3bOther");
			CommonStaticClass.qskipList.remove("q5_4");
			CommonStaticClass.qskipList.remove("q5_4b");
			CommonStaticClass.qskipList.remove("q5_4bOther");
			CommonStaticClass.qskipList.remove("q5_5");
			CommonStaticClass.qskipList.remove("q5_5b");
			CommonStaticClass.qskipList.remove("q5_5bOther");
			CommonStaticClass.qskipList.remove("q5_6");
			CommonStaticClass.qskipList.remove("q5_6b");
			CommonStaticClass.qskipList.remove("q5_6bOther");
			CommonStaticClass.qskipList.remove("q5_7");
			CommonStaticClass.qskipList.remove("q5_7b");
			CommonStaticClass.qskipList.remove("q5_7bOther");
			CommonStaticClass.qskipList.remove("q5_8");
			CommonStaticClass.qskipList.remove("q5_8b");
			CommonStaticClass.qskipList.remove("q5_8bOther");
			CommonStaticClass.qskipList.remove("q5_9");
			CommonStaticClass.qskipList.remove("q5_9b");
			CommonStaticClass.qskipList.remove("q5_9bOther");
			CommonStaticClass.qskipList.remove("q5_10");
			CommonStaticClass.qskipList.remove("q5_10Other");
			CommonStaticClass.qskipList.remove("q5_10b");
			CommonStaticClass.qskipList.remove("q5_10bOther");

			CommonStaticClass.qskipList.remove("sec6");
			CommonStaticClass.qskipList.remove("q6_1");
			CommonStaticClass.qskipList.remove("q6_2");
			CommonStaticClass.qskipList.remove("q6_3");
			CommonStaticClass.qskipList.remove("q6_4");
			CommonStaticClass.qskipList.remove("q6_5");
			CommonStaticClass.qskipList.remove("q6_6");
			CommonStaticClass.qskipList.remove("q6_7");
			CommonStaticClass.qskipList.remove("q6_8");

			CommonStaticClass.qskipList.remove("sec7");
			CommonStaticClass.qskipList.remove("q7_1");
			CommonStaticClass.qskipList.remove("q7_1b");
			CommonStaticClass.qskipList.remove("q7_1bOther");
			CommonStaticClass.qskipList.remove("q7_2");
			CommonStaticClass.qskipList.remove("q7_2b");
			CommonStaticClass.qskipList.remove("q7_2bOther");
			CommonStaticClass.qskipList.remove("q7_3");
			CommonStaticClass.qskipList.remove("q7_3b");
			CommonStaticClass.qskipList.remove("q7_3bOther");
			CommonStaticClass.qskipList.remove("q7_4");
			CommonStaticClass.qskipList.remove("q7_4b");
			CommonStaticClass.qskipList.remove("q7_4bOther");
			CommonStaticClass.qskipList.remove("q7_5");
			CommonStaticClass.qskipList.remove("q7_5b");
			CommonStaticClass.qskipList.remove("q7_5bOther");
			CommonStaticClass.qskipList.remove("q7_6");
			CommonStaticClass.qskipList.remove("q7_6b");
			CommonStaticClass.qskipList.remove("q7_6bOther");
			CommonStaticClass.qskipList.remove("q7_7");
			CommonStaticClass.qskipList.remove("q7_7b");
			CommonStaticClass.qskipList.remove("q7_7bOther");
			CommonStaticClass.qskipList.remove("q7_8");
			CommonStaticClass.qskipList.remove("q7_8Other");
			CommonStaticClass.qskipList.remove("q7_8b");
			CommonStaticClass.qskipList.remove("q7_8bOther");

			String sqlString = "Select q2_1,q2_2,q2_3,q2_4,q2_5 from tblMainQuesEPT where dataid = '"
					+ CommonStaticClass.dataId + "'";

			Cursor c = dbHelper.getQueryCursor(sqlString);
			if (c != null) {
				if (c.getCount() > 0) {
					if (c.moveToFirst()) {
						do {

							if (c.getString(c.getColumnIndex("q2_1"))
									.toString().equalsIgnoreCase("2")) {
								CommonStaticClass.qskipList.add("sec3");
								CommonStaticClass.qskipList.add("q3_1");

							}
							if (c.getString(c.getColumnIndex("q2_2"))
									.toString().equalsIgnoreCase("2")) {
								CommonStaticClass.qskipList.add("sec4");
								CommonStaticClass.qskipList.add("q4_1");
								CommonStaticClass.qskipList.add("q4_2");
								CommonStaticClass.qskipList.add("q4_3");
								CommonStaticClass.qskipList.add("q4_4");
							}
							if (c.getString(c.getColumnIndex("q2_3"))
									.toString().equalsIgnoreCase("2")) {
								CommonStaticClass.qskipList.add("sec5");
								CommonStaticClass.qskipList.add("q5_1");
								CommonStaticClass.qskipList.add("q5_1b");
								CommonStaticClass.qskipList.add("q5_1bOther");
								CommonStaticClass.qskipList.add("q5_2");
								CommonStaticClass.qskipList.add("q5_2b");
								CommonStaticClass.qskipList.add("q5_2bOther");
								CommonStaticClass.qskipList.add("q5_3");
								CommonStaticClass.qskipList.add("q5_3b");
								CommonStaticClass.qskipList.add("q5_3bOther");
								CommonStaticClass.qskipList.add("q5_4");
								CommonStaticClass.qskipList.add("q5_4b");
								CommonStaticClass.qskipList.add("q5_4bOther");
								CommonStaticClass.qskipList.add("q5_5");
								CommonStaticClass.qskipList.add("q5_5b");
								CommonStaticClass.qskipList.add("q5_5bOther");
								CommonStaticClass.qskipList.add("q5_6");
								CommonStaticClass.qskipList.add("q5_6b");
								CommonStaticClass.qskipList.add("q5_6bOther");
								CommonStaticClass.qskipList.add("q5_7");
								CommonStaticClass.qskipList.add("q5_7b");
								CommonStaticClass.qskipList.add("q5_7bOther");
								CommonStaticClass.qskipList.add("q5_8");
								CommonStaticClass.qskipList.add("q5_8b");
								CommonStaticClass.qskipList.add("q5_8bOther");
								CommonStaticClass.qskipList.add("q5_9");
								CommonStaticClass.qskipList.add("q5_9b");
								CommonStaticClass.qskipList.add("q5_9bOther");
								CommonStaticClass.qskipList.add("q5_10");
								CommonStaticClass.qskipList.add("q5_10Other");
								CommonStaticClass.qskipList.add("q5_10b");
								CommonStaticClass.qskipList.add("q5_10bOther");
							}
							if (c.getString(c.getColumnIndex("q2_4"))
									.toString().equalsIgnoreCase("2")) {

								CommonStaticClass.qskipList.add("sec6");
								CommonStaticClass.qskipList.add("q6_1");
								CommonStaticClass.qskipList.add("q6_2");
								CommonStaticClass.qskipList.add("q6_3");
								CommonStaticClass.qskipList.add("q6_4");
								CommonStaticClass.qskipList.add("q6_5");
								CommonStaticClass.qskipList.add("q6_6");
								CommonStaticClass.qskipList.add("q6_7");
								CommonStaticClass.qskipList.add("q6_8");
							}
							if (c.getString(c.getColumnIndex("q2_5"))
									.toString().equalsIgnoreCase("2")) {

								CommonStaticClass.qskipList.add("sec7");
								CommonStaticClass.qskipList.add("q7_1");
								CommonStaticClass.qskipList.add("q7_1b");
								CommonStaticClass.qskipList.add("q7_1bOther");
								CommonStaticClass.qskipList.add("q7_2");
								CommonStaticClass.qskipList.add("q7_2b");
								CommonStaticClass.qskipList.add("q7_2bOther");
								CommonStaticClass.qskipList.add("q7_3");
								CommonStaticClass.qskipList.add("q7_3b");
								CommonStaticClass.qskipList.add("q7_3bOther");
								CommonStaticClass.qskipList.add("q7_4");
								CommonStaticClass.qskipList.add("q7_4b");
								CommonStaticClass.qskipList.add("q7_4bOther");
								CommonStaticClass.qskipList.add("q7_5");
								CommonStaticClass.qskipList.add("q7_5b");
								CommonStaticClass.qskipList.add("q7_5bOther");
								CommonStaticClass.qskipList.add("q7_6");
								CommonStaticClass.qskipList.add("q7_6b");
								CommonStaticClass.qskipList.add("q7_6bOther");
								CommonStaticClass.qskipList.add("q7_7");
								CommonStaticClass.qskipList.add("q7_7b");
								CommonStaticClass.qskipList.add("q7_7bOther");
								CommonStaticClass.qskipList.add("q7_8");
								CommonStaticClass.qskipList.add("q7_8Other");
								CommonStaticClass.qskipList.add("q7_8b");
								CommonStaticClass.qskipList.add("q7_8bOther");

							}

						} while (c.moveToNext());

					}
				}
			}

			return true;

		} catch (Exception e) {
			// CommonStaticClass.showMyAlert(con, "Empty",
			// "Can not edit this question because there was no data.");
			return false;
		}

	}

	private TextWatcher filterTextWatcher = new TextWatcher() {

		public void afterTextChanged(Editable s) {
		}

		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			myAdapter.getFilter().filter(s);
		}

	};

	//
	// protected void onDestroy() {
	// super.onDestroy();
	// filterText.removeTextChangedListener(filterTextWatcher);
	// }

	protected boolean checkSelectionHasDataOrNot(String qvar, String tablename) {
		if (qvar.contains("sec")) {
			return true;
		}
		String sqlCheck = "";
		if (!CommonStaticClass.isMember)
			sqlCheck = "Select " + qvar + " from " + tablename
					+ " where dataid='" + CommonStaticClass.dataId + "'";
		else
			sqlCheck = "Select " + qvar + " from " + tablename
					+ " where dataid='" + CommonStaticClass.dataId
					+ "' and memberid=" + CommonStaticClass.memberID;

		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sqlCheck);
			mCursor1.moveToFirst();
			if (mCursor1.getCount() > 0) {
				String a = mCursor1.getString((mCursor1.getColumnIndex(qvar)))
						+ "";
				Log.e("aaaa", "" + a);
				if (a.length() > 0 && !a.contains("-1")
						&& !a.equalsIgnoreCase("null")) {
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}
		return false;
	}

	private void loadDataToList() {
		String sql = "";
		if (!CommonStaticClass.isMember)
			sql = "Select SLNo,Qvar,Qdescbng,Qdesceng from tblQuestion order by SLNo asc";
		else
			sql = "Select SLNo,Qvar,Qdescbng,Qdesceng from tblQuestion order by SLNo asc";

		Cursor mCursor = null;
		try {
			mCursor = dbHelper.getQueryCursor(sql);
			if (mCursor.moveToFirst()) {
				do {
					qslno.add(mCursor.getInt((mCursor.getColumnIndex("SLNo"))));
					qs.add(mCursor.getString((mCursor.getColumnIndex("Qvar"))));
					qdescbng.add(mCursor.getString((mCursor
							.getColumnIndex("Qdescbng"))));
					qdesceng.add(mCursor.getString((mCursor
							.getColumnIndex("Qdesceng"))));
				} while (mCursor.moveToNext());
			}
		} catch (Exception e) {
			// TODO: handle exception
			CommonStaticClass.showFinalAlert(con,
					"A problem occured please try later");
		} finally {
			if (mCursor != null)
				mCursor.close();

			myAdapter.notifyDataSetChanged();
		}
	}

	// Added By Angsuman
	public boolean onCreateOptionsMenu(final Menu menu) {
		final MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.lang_menu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(final MenuItem item) {
		switch (item.getItemId()) {
		case R.id.BNGMenuItem:
			CommonStaticClass.langBng = true;
			loadGui();
			loadDataToList();
			return true;
		case R.id.ENGMenuItem:
			CommonStaticClass.langBng = false;
			loadGui();
			loadDataToList();
			return true;
		case R.id.GotoHomeItem:
			CommonStaticClass.mode = "";
			finish();
			// Intent i = new Intent();
			// i.setClassName(CommonStaticClass.pName,
			// CommonStaticClass.pName+".MenuScreen");
			// startActivity(i);

			return true;

		case R.id.ExitItem:

			CommonStaticClass.mode = "";
			finish();

			/*
			 * Intent i1 = new Intent();
			 * i1.setClassName(CommonStaticClass.pName,
			 * CommonStaticClass.pName+".LoginActivity"); startActivity(i1);
			 */
			return true;

			/*
			 * case R.id.search: CommonStaticClass.mode = ""; Intent int_b = new
			 * Intent(getApplicationContext(), SearchLayout.class);
			 * startActivity(int_b); return true;
			 * 
			 * case R.id.viewall:
			 * 
			 * CommonStaticClass.mode = ""; int_b = new
			 * Intent(getApplicationContext(), Viewall.class);
			 * startActivity(int_b); return true;
			 */

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	//
	private void startQuestion() {
		// TODO Auto-generated method stub
		CommonStaticClass.SLNOSTACK.add(CommonStaticClass.currentSLNo);
		Intent i = new Intent();
		i.setClassName(CommonStaticClass.pName, CommonStaticClass.pName
				+ ".questions.ParentActivity");
		startActivity(i);
	}

	private void setSkipper() {
		String sq = "Select q12 from tblMainQuesSC where dataid='"
				+ CommonStaticClass.dataId + "'";
		Cursor mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sq);
			if (mCursor1.moveToFirst()) {
				do {
					String val = mCursor1.getString((mCursor1
							.getColumnIndex("q12"))) + "";
					if (val.length() > 0 && !val.contains("-1")
							&& !val.equalsIgnoreCase("null")) {
						int a = Integer.parseInt(val);
						if (a == 1) {
							CommonStaticClass.qskipList.add("q1005");
							CommonStaticClass.qskipList.add("q1012");
							CommonStaticClass.qskipList.add("sec8");
							CommonStaticClass.qskipList.add("sec9");
						} else if (a == 2) {
							CommonStaticClass.qskipList.add("q1005");
							CommonStaticClass.qskipList.add("sec8");
							CommonStaticClass.qskipList.add("sec9");
						} else if (a == 3) {
							CommonStaticClass.qskipList.add("q1003");
							CommonStaticClass.qskipList.add("q1006");
							CommonStaticClass.qskipList.add("q1007");
							CommonStaticClass.qskipList.add("q1008");
							CommonStaticClass.qskipList.add("q1009");
							CommonStaticClass.qskipList.add("q1010");
							CommonStaticClass.qskipList.add("q1014");

							CommonStaticClass.qskipList.add("q1015");

							CommonStaticClass.qskipList.add("sec2");
							CommonStaticClass.qskipList.add("sec2_1");
							CommonStaticClass.qskipList.add("sec3");
							CommonStaticClass.qskipList.add("sec4");
							CommonStaticClass.qskipList.add("sec9");

						} else if (a == 4) {
							CommonStaticClass.qskipList.add("q1003");
							CommonStaticClass.qskipList.add("q1006");
							CommonStaticClass.qskipList.add("q1007");
							CommonStaticClass.qskipList.add("q1008");
							CommonStaticClass.qskipList.add("q1009");
							CommonStaticClass.qskipList.add("q1010");
							CommonStaticClass.qskipList.add("q1014");

							CommonStaticClass.qskipList.add("q1015");

							CommonStaticClass.qskipList.add("sec2");
							CommonStaticClass.qskipList.add("sec2_1");
							CommonStaticClass.qskipList.add("sec3");
							CommonStaticClass.qskipList.add("sec4");
							CommonStaticClass.qskipList.add("sec8");
						}
					}
				} while (mCursor1.moveToNext());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}
		String sql = "Select q204a from tblMainQues where dataid='"
				+ CommonStaticClass.dataId + "'";
		mCursor1 = null;
		try {
			mCursor1 = dbHelper.getQueryCursor(sql);
			if (mCursor1.moveToFirst()) {
				do {
					String val = mCursor1.getString((mCursor1
							.getColumnIndex("q204a"))) + "";
					if (val.length() > 0 && !val.contains("-1")
							&& !val.equalsIgnoreCase("null")) {
						int a = Integer.parseInt(val);
						if (a >= 6 && a < 12) {
							CommonStaticClass.qskipList.add("q301");
						}

						if (a < 6) {
							CommonStaticClass.qskipList.add("q301");
							CommonStaticClass.qskipList.add("q302");
							CommonStaticClass.qskipList.add("q406");
							CommonStaticClass.qskipList.add("q407");
							CommonStaticClass.qskipList.add("q408");
							CommonStaticClass.qskipList.add("q409");
							CommonStaticClass.qskipList.add("q412");
							CommonStaticClass.qskipList.add("q413");
							CommonStaticClass.qskipList.add("q414");
							CommonStaticClass.qskipList.add("q415");
							CommonStaticClass.qskipList.add("q416");
							CommonStaticClass.qskipList.add("q416a");
						}
					}
				} while (mCursor1.moveToNext());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		} finally {
			if (mCursor1 != null)
				mCursor1.close();
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed(true);
	}
}
