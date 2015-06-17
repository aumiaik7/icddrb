package com.icddrb.app.ccdtemplate;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.icddrb.app.ccdtemplate.R;
import com.icddrb.app.ccdtemplate.adapters.QlistAdapter;

import net.sqlcipher.Cursor;


public class QListScreenForEdit extends BaseActivity {
	private ListView listentries;
	private Context con;
	private ArrayList<String> qs;
	private ArrayList<String> qdescbng;
	private ArrayList<String> qdesceng;
	private ArrayList<Integer> qslno;
	// private EditText filterText = null;
	private QlistAdapter myAdapter;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qeditlist);
		con = this;
		setTheme(R.style.AppTheme);
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
				if (CommonStaticClass.currentSLNo != 1) {
					// if(CommonStaticClass.currentSLNo==31)// condition added
					// by me
					// {
					// CommonStaticClass.mode = CommonStaticClass.EDITMODE;
					// Intent i = new Intent();
					// i.setClassName(CommonStaticClass.pName,
					// CommonStaticClass.pName+".EditEntryMember");
					// startActivity(i);
					// }
					// else if(CommonStaticClass.currentSLNo == 8){
					// startQuestion();
					// }else
					CommonStaticClass.mode = CommonStaticClass.EDITMODE;

					// if(addtoskip())
					// startQuestion();
				/*	if (checkSelectionHasDataOrNot(
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo).getQvar(),
							CommonStaticClass.questionMap.get(
									CommonStaticClass.currentSLNo)
									.getTablename())) {*/

						startQuestion();

				//	} 
				//else {
					//	CommonStaticClass.showMyAlert(con, "Warning!!!",
						//		"This question has no data associated with it");
					//}

					/*
					 * else{ CommonStaticClass.showMyAlert(con, "Warning!!!",
					 * "This question has no data associated with it"); }
					 */
				} else {
					CommonStaticClass.showMyAlert(con, "Warning!!!",
							"You can not edit this question");
				}
			}

		});

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
				String a = mCursor1.getString((mCursor1.getColumnIndex(qvar
						.toLowerCase()))) + "";
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
		//load sections 
		
		if(CommonStaticClass.subEditMode == 1)
		sql = "Select SLNo,Qvar,Qdescbng,Qdesceng from tblQuestion  where (SLNo >= '"+ CommonStaticClass.sectionStart+"' " +
					"and SLNo <=" +
					" '"+CommonStaticClass.sectionEnd+"') order by SLNo asc";
		else
		//load all
			sql = "Select SLNo,Qvar,Qdescbng,Qdesceng from tblQuestion  order by SLNo asc";
		
		/*if (CommonStaticClass.subEdit.equalsIgnoreCase("sec01")) {
		if (!CommonStaticClass.isMember)
			sql = "Select SLNo,Qvar,Qdescbng,Qdesceng from tblQuestion  where (SLNo >= 2 and SLNo <= 44) order by SLNo asc";
		else
			sql = "Select SLNo,Qvar,Qdescbng,Qdesceng from tblQuestion  where (SLNo >= 2 and SLNo <= 44) order by SLNo asc";
		}
		if (CommonStaticClass.subEdit.equalsIgnoreCase("sec02")) {
			if (!CommonStaticClass.isMember)
				sql = "Select SLNo,Qvar,Qdescbng,Qdesceng from tblQuestion where (SLNo >= 45 and SLNo <= 54) order by SLNo asc";
			else
				sql = "Select SLNo,Qvar,Qdescbng,Qdesceng from tblQuestion where (SLNo >= 45 and SLNo <= 54) order by SLNo asc";
			}
		if (CommonStaticClass.subEdit.equalsIgnoreCase("sec03")) {
			if (!CommonStaticClass.isMember)
				sql = "Select SLNo,Qvar,Qdescbng,Qdesceng from tblQuestion  where (SLNo >= 55 and SLNo <= 75) order by SLNo asc";
			else
				sql = "Select SLNo,Qvar,Qdescbng,Qdesceng from tblQuestion  where (SLNo >= 55 and SLNo <= 75) order by SLNo asc";
			}
		if (CommonStaticClass.subEdit.equalsIgnoreCase("sec04")) {
			if (!CommonStaticClass.isMember)
				sql = "Select SLNo,Qvar,Qdescbng,Qdesceng from tblQuestion  where (SLNo >= 76 and SLNo <= 96)  order by SLNo asc";
			else
				sql = "Select SLNo,Qvar,Qdescbng,Qdesceng from tblQuestion where (SLNo >= 76 and SLNo <= 96) order by SLNo asc";
			}
		
		if (CommonStaticClass.subEdit.equalsIgnoreCase("sec05")) {
			if (!CommonStaticClass.isMember)
				sql = "Select SLNo,Qvar,Qdescbng,Qdesceng from tblQuestion  where (SLNo >= 97 and SLNo <= 136)  order by SLNo asc";
			else
				sql = "Select SLNo,Qvar,Qdescbng,Qdesceng from tblQuestion where (SLNo >= 97 and SLNo <= 136) order by SLNo asc";
			}
		
		if (CommonStaticClass.subEdit.equalsIgnoreCase("sec06")) {
			if (!CommonStaticClass.isMember)
				sql = "Select SLNo,Qvar,Qdescbng,Qdesceng from tblQuestion  where (SLNo >= 137 and SLNo <= 178)  order by SLNo asc";
			else
				sql = "Select SLNo,Qvar,Qdescbng,Qdesceng from tblQuestion where (SLNo >= 137 and SLNo <= 178) order by SLNo asc";
			}
		
		if (CommonStaticClass.subEdit.equalsIgnoreCase("sec07")) {
			if (!CommonStaticClass.isMember)
				sql = "Select SLNo,Qvar,Qdescbng,Qdesceng from tblQuestion  where (SLNo >= 179 and SLNo <= 196)  order by SLNo asc";
			else
				sql = "Select SLNo,Qvar,Qdescbng,Qdesceng from tblQuestion where (SLNo >= 179 and SLNo <= 196) order by SLNo asc";
			}
		
		if (CommonStaticClass.subEdit.equalsIgnoreCase("sec08")) {
			if (!CommonStaticClass.isMember)
				sql = "Select SLNo,Qvar,Qdescbng,Qdesceng from tblQuestion  where (SLNo >= 197 and SLNo <= 258)  order by SLNo asc";
			else
				sql = "Select SLNo,Qvar,Qdescbng,Qdesceng from tblQuestion where (SLNo >= 197 and SLNo <= 258) order by SLNo asc";
			}
		
		if (CommonStaticClass.subEdit.equalsIgnoreCase("sec09")) {
			if (!CommonStaticClass.isMember)
				sql = "Select SLNo,Qvar,Qdescbng,Qdesceng from tblQuestion  where (SLNo >= 259 and SLNo <= 273)  order by SLNo asc";
			else
				sql = "Select SLNo,Qvar,Qdescbng,Qdesceng from tblQuestion where (SLNo >= 259 and SLNo <= 273) order by SLNo asc";
			}
		
		if (CommonStaticClass.subEdit.equalsIgnoreCase("sec10")) {
			if (!CommonStaticClass.isMember)
				sql = "Select SLNo,Qvar,Qdescbng,Qdesceng from tblQuestion  where (SLNo >= 274 and SLNo <= 328)  order by SLNo asc";
			else
				sql = "Select SLNo,Qvar,Qdescbng,Qdesceng from tblQuestion where (SLNo >= 274 and SLNo <= 328) order by SLNo asc";
			}
		
		if (CommonStaticClass.subEdit.equalsIgnoreCase("sec11")) {
			if (!CommonStaticClass.isMember)
				sql = "Select SLNo,Qvar,Qdescbng,Qdesceng from tblQuestion  where (SLNo >= 329 and SLNo <= 394)  order by SLNo asc";
			else
				sql = "Select SLNo,Qvar,Qdescbng,Qdesceng from tblQuestion where (SLNo >= 329 and SLNo <= 394) order by SLNo asc";
			}
		
		if (CommonStaticClass.subEdit.equalsIgnoreCase("sec12")) {
			if (!CommonStaticClass.isMember)
				sql = "Select SLNo,Qvar,Qdescbng,Qdesceng from tblQuestion  where (SLNo >= 395 and SLNo <= 422)  order by SLNo asc";
			else
				sql = "Select SLNo,Qvar,Qdescbng,Qdesceng from tblQuestion where (SLNo >= 395 and SLNo <= 422) order by SLNo asc";
			}
		
		if (CommonStaticClass.subEdit.equalsIgnoreCase("sec13")) {
			if (!CommonStaticClass.isMember)
				sql = "Select SLNo,Qvar,Qdescbng,Qdesceng from tblQuestion  where  SLNo >=423   order by SLNo asc";
			else
				sql = "Select SLNo,Qvar,Qdescbng,Qdesceng from tblQuestion where SLNo >=423 order by SLNo asc";
			}*/
		
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
			
			return true;

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
	
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		startActivity(i);
	}

	

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed(true);
	}
}
