package com.icddrb.app.ccdtemplate.datatransfertool;

import java.io.File;
import java.io.FileFilter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.icddrb.app.ccdtemplate.CommonStaticClass;
import com.icddrb.app.ccdtemplate.MenuScreen;
import com.icddrb.app.ccdtemplate.db.DatabaseHelper;



public class FileRead extends Activity {

	private static String PATH = "/mnt/sdcard/";
	private ProgressDialog pdg;

	/*public List<String> GetListOfSqliteFiles() {

		File files = new File(PATH);

		FileFilter filter = new FileFilter() {

			private final List<String> exts = Arrays.asList("sqlite");

			public boolean accept(File pathname) {
				String ext;
				String path = pathname.getPath();
				ext = path.substring(path.lastIndexOf(".") + 1);
				return exts.contains(ext);
			}
		};

		final File[] filesFound = files.listFiles(filter);
		List<String> list = new ArrayList<String>();
		if (filesFound != null && filesFound.length > 0) {
			for (File file : filesFound) {
				list.add(file.getName());
			}
		}

		return list;
	}*/

	DatabaseHelper dbHelper = null;
	// JSONObject InsertDataStrings = new JSONObject();
	ArrayList<InsertStatement> list = new ArrayList<InsertStatement>();

	ArrayList<TransData> _trans = new ArrayList<TransData>();

	/*private String GetDataBaseID(String dbName, ArrayList<DatabaseID> dbList) {
		try {
			for (int i = 0; i < dbList.size(); i++) {
				if (dbList.get(i).getDatabaseName().equalsIgnoreCase(dbName)) {
					return dbList.get(i).getDBID();
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			Log.e("cursor", "is null");
		} finally {

		}
		return "";
	}

	private String GetDataBaseName(String dbid, ArrayList<DatabaseID> dbList) {
		try {
			for (int i = 0; i < dbList.size(); i++) {
				if (dbList.get(i).getDBID().equalsIgnoreCase(dbid)) {
					return dbList.get(i).getDatabaseName();
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			Log.e("cursor", "is null");
		} finally {

		}
		return "";
	}*/

	private ArrayList<DatabaseID> _DatabseList = null;
	Context contxt = null;

	public ArrayList<TransData> MakeInsertString(Context con,DatabaseHelper dbh,	Handler handler) {
	

		// List<String> dblist = GetListOfSqliteFiles();

		// InsertDataStrings = new JSONObject();
		list = new ArrayList<InsertStatement>();

//		for (String s : dblist) {

			Cursor mCursor = null;
			try {
				TransData _tran = new TransData();
//				DatabaseHelper.DB_NAME = s;

				contxt = con;
				dbHelper = dbh;
				//dbHelper = new DatabaseHelper(contxt);
				/*dbHelper.openDataTransferToolDataBasesFrmList();

				dbHelper = DatabaseHelper.getInstance();*/

				mCursor = dbHelper
						.getQueryCursor(String
								.format("Select * from sqlite_master where type='table'"));

				if (mCursor.moveToFirst()) {
					do {
						String table_name = mCursor.getString(mCursor
								.getColumnIndex("tbl_name"));

							
							if (table_name.startsWith("frmr")	
									|| table_name
											.equalsIgnoreCase("tblOptions")
									|| table_name
											.equalsIgnoreCase("tblQuestion")
									|| table_name.equalsIgnoreCase("tblStack")	
									|| table_name.equalsIgnoreCase("tblUser")
									|| table_name
											.equalsIgnoreCase("tblVersion")) {
								continue;
							}
						 
						

						_tran = new TransData();
						_tran.setDatabaseNm(CommonStaticClass.DB);
						_tran.setAssetId(CommonStaticClass.AssetID);
						_tran.setTableNm(table_name);

						Cursor table_cursor = null;
						Cursor primarykey_cursor = null;

						try {

							table_cursor = dbHelper.getQueryCursor(String
									.format("PRAGMA table_info('%s')",
											table_name));

							primarykey_cursor = dbHelper.getQueryCursor(String
									.format("PRAGMA table_info('%s')",
											table_name));

							primarykeycolumlist = GetPrimaryKeyColumnList(primarykey_cursor);
							if (primarykeycolumlist.size() <= 0) {
								continue;
							}

							if (table_cursor.moveToFirst()) {
							
								StringBuilder IStatement = new StringBuilder();
								IStatement.append(String.format(
										"Insert Into %s (", table_name));

								do {

									String column_name = table_cursor
											.getString(table_cursor
													.getColumnIndex("name"));

									IStatement.append(column_name + ",");

								} while (table_cursor.moveToNext());

								if (IStatement.length() > 0) {
									IStatement.replace(IStatement.length() - 1,
											IStatement.length(), "");
									IStatement.append(")");

									_tran.setIStatement(MakeValueString(
											table_name, con, IStatement,
											primarykeycolumlist));

									_trans.add(_tran);

									Message ms = new Message();
									ms.what = MenuScreen.INCREASE;
									handler.sendMessage(ms);

								}
								// table_cursor.close();
							}

						} catch (Exception e) {
							CommonStaticClass.showMyAlert(con, "Error", e
									.getMessage().toString());
							e.printStackTrace();
						}

					} while (mCursor.moveToNext());

				}

			} catch (Exception e) { // TODO: handle exception
				// Toast.makeText(con, e.getMessage().toString(), 1000).show();
				CommonStaticClass.showMyAlert(con, "Error", e.getMessage()
						.toString());
				Log.e("cursor", "is null");
			} finally {
				if (mCursor != null)
					mCursor.close();
			}
			

		//}

		return _trans;
	}

	

	private ArrayList<String> primarykeycolumlist;

	public ArrayList<InsertStatement> MakeValueString(String table_name,
			Context con, StringBuilder IStatement, ArrayList<String> Pklist) {

		primarykeycolumlist = Pklist;// GetPrimaryKeyColumnList(PrimaryKeyCursor);
		if (primarykeycolumlist.size() <= 0) {
			return null;
		}

		PrimaryClm column = null;
		// StringBuilder value = null;
		String value = "";
		Cursor value_cursor = null;
		ArrayList<PrimaryClm> _primarykeyColumn = null;
		InsertStatement insertStatement = null;
		ArrayList<InsertStatement> _trans = new ArrayList<InsertStatement>();
		try {
			// value = new StringBuilder();

			_primarykeyColumn = new ArrayList<PrimaryClm>();

			 value_cursor =
			 dbHelper.getQueryCursor(String.format("select * from '%s' where IsTransferred is not '1'",
			 table_name));
			//value_cursor = dbHelper.getQueryCursor(String.format(
			//		"select * from '%s'", table_name));

			if (value_cursor != null) {
				if (value_cursor.getCount() > 0) {
					if (value_cursor.moveToFirst()) {

						column = new PrimaryClm();
						// primarykeycolumlist =
						// GetPrimaryKeyColumnList(PrimaryKeyCursor);

						/*
						 * if (primarykeycolumlist.size() <= 0) { return null; }
						 */

						do {
							insertStatement = new InsertStatement();
							_primarykeyColumn = new ArrayList<PrimaryClm>();
							// value.append(IStatement + "VALUES(");
							value = value + IStatement + "VALUES(";
							// int colindex = 0;
							for (int i = 0; i < value_cursor.getColumnCount();) {
								// get column index takes too much time
								if (value_cursor.getString(value_cursor
										.getColumnIndex(value_cursor
												.getColumnName(i))) == null) {
									value = value
											+ ""
											+ value_cursor
													.getString(value_cursor
															.getColumnIndex(value_cursor
																	.getColumnName(i)))
											+ ",";

								} else if (value_cursor.getString(
										value_cursor
												.getColumnIndex(value_cursor
														.getColumnName(i)))
										.equalsIgnoreCase("-1"))

								{
									value = value
											+ ""
											+ value_cursor
													.getString(value_cursor
															.getColumnIndex(value_cursor
																	.getColumnName(i)))
											+ ",";

								}

								else if (value_cursor.getString(
										value_cursor
												.getColumnIndex(value_cursor
														.getColumnName(i)))
										.equalsIgnoreCase("null"))

								{
									value = value
											+ ""
											+ value_cursor
													.getString(value_cursor
															.getColumnIndex(value_cursor
																	.getColumnName(i)))
											+ ",";

								}

								else {

									value = value
											+ "'"
											+ value_cursor
													.getString(value_cursor
															.getColumnIndex(value_cursor
																	.getColumnName(i)))
											+ "',";

								}

								for (int j = 0; j < primarykeycolumlist.size(); j++) {
									if (String.valueOf(
											primarykeycolumlist.get(j))
											.equalsIgnoreCase(
													value_cursor
															.getColumnName(i))) {
										column = new PrimaryClm();
										column.set_ClumnMn(value_cursor
												.getColumnName(i));
										column.setClumnValue("'"
												+ value_cursor.getString(value_cursor
														.getColumnIndex(value_cursor
																.getColumnName(i)))
												+ "'");

										_primarykeyColumn.add(column);

									}
									if (_primarykeyColumn != null) {
										insertStatement
												.setPrimaryClmList(_primarykeyColumn);

									}
								}

								i++;
							}

							value = value.substring(0, value.length() - 1);

							value = value + ");";

							insertStatement.setStatement(value.toString());

							_trans.add(insertStatement);
							value = "";
							insertStatement = new InsertStatement();
							Log.e("InsertStatementWithValue", value.toString());
							CommonStaticClass.entryUdpated = CommonStaticClass.entryUdpated +1;

						} while (value_cursor.moveToNext());

					}
				}
				value_cursor.close();

			}

		} catch (Exception e) {
			e.printStackTrace();
			CommonStaticClass.showMyAlert(con, "Message", e.getMessage()
					.toString());

		} finally {
			value = null;
			_primarykeyColumn = null;
			column = null;
			value_cursor = null;
			// PrimaryKeyCursor = null;

		}

		return _trans;
	}

	private ArrayList<String> GetPrimaryKeyColumnList(Cursor primaryKeyCursor) {

		ArrayList<String> _pkList = new ArrayList<String>();
		String PrimaryKeyName = "";
		String isPrimaryKeyColumn = "";
		try {

			if (primaryKeyCursor.moveToFirst()) {

				do {

					PrimaryKeyName = primaryKeyCursor
							.getString(primaryKeyCursor.getColumnIndex("name"));
					isPrimaryKeyColumn = primaryKeyCursor
							.getString(primaryKeyCursor.getColumnIndex("pk"));

					if (!isPrimaryKeyColumn.equalsIgnoreCase("0")) {
						_pkList.add(PrimaryKeyName);

					}

				} while (primaryKeyCursor.moveToNext());
				return _pkList;
			}
		} catch (Exception e) {

		}

		return null;
	}

	private boolean IsPrimaryKey(String tablename, String ColumnName,
			Cursor PrimaryKeyCursor) {

		try {

			if (PrimaryKeyCursor.moveToFirst()) {

				do {

					String PrimaryKeyName = PrimaryKeyCursor
							.getString(PrimaryKeyCursor.getColumnIndex("name"));

					String isPrimaryKeyColumn = PrimaryKeyCursor.getString(5);

					if (PrimaryKeyName.equalsIgnoreCase(ColumnName)) {
						if (!isPrimaryKeyColumn.equalsIgnoreCase("0")) {
							// PrimaryKeyCursor.close();
							return true;
						}
					}

				} while (PrimaryKeyCursor.moveToNext());
			}
		} catch (Exception e) {

		}
		return false;

	}

	public static int SHORT_TOAST = 0;
	public static int LONG_TOAST = 1;

	/*public static void DisplayToast(Context caller, String toastMsg,
			int toastType) {

		try {// try-catch to avoid stupid app crashes
			LayoutInflater inflater = LayoutInflater.from(caller);

			View mainLayout = inflater.inflate(R.layout.toast_layout, null);
			View rootLayout = mainLayout.findViewById(R.id.toast_layout_root);

			ImageView image = (ImageView) mainLayout.findViewById(R.id.image);
			image.setImageResource(R.drawable.notification);
			TextView text = (TextView) mainLayout.findViewById(R.id.text);
			text.setText(toastMsg);

			Toast toast = new Toast(caller);
			// toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			toast.setGravity(Gravity.TOP, 0, 0);
			if (toastType == SHORT_TOAST)// (isShort)
				toast.setDuration(Toast.LENGTH_SHORT);
			else
				toast.setDuration(Toast.LENGTH_LONG);
			toast.setView(rootLayout);
			toast.show();
		} catch (Exception ex) {// to avoid stupid app crashes

		}
	}*/

	// for testing only
	public void SendRequest(List<TransData> trans) {

		/*try {

			// make web service connection
			
			 * HttpPost request = new HttpPost("http://172.16.10.28:8080" +
			 * "/Design_Time_Addresses/SampleEmpService.svc/Employees");
			 
			HttpPost request = new HttpPost("http://172.16.10.28:8732"
					+ "/Design_Time_Addresses/DataTransferApp.svc/CCDRDUpload");
			request.setHeader("Accept", "application/json");
			request.setHeader("Content-type", "application/json");

			Gson g = new Gson();
			String s = g.toJson(trans);
			StringEntity entity = new StringEntity(s);// MakeEmployees());

			// Log.d("****Parameter Input****", "Testing:" + TestApp);
			request.setEntity(entity);
			// Send request to WCF service
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(request);

			String _response = EntityUtils.toString(response.getEntity());

			// content will be consume only once this is for Object to Object
			// mapper
			Employee[] sectionArray = new Gson().fromJson(_response,
					Employee[].class);

			List<Employee> emplist = new ArrayList<Employee>();
			for (int i = 0; i < sectionArray.length; i++) {

				emplist.add(sectionArray[i]);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}*/

	}

	public Boolean CallWebService(ArrayList<TransData> trans)
			throws UnsupportedEncodingException {
		// Boolean NoError = false;

		// The live one

		//HttpPost request = new HttpPost("http://ccd-mis.icddrb.org/ccdrdupload/datatransferapp.datatransservice.svc/CCDRDUpload");
		HttpPost request = new HttpPost("http://172.16.10.20/DTTrans/datatransferapp.datatransservice.svc/CCDRDUpload");

		// HttpPost request = new HttpPost("http://172.16.8.221:8732/Design_Time_Addresses/DataTransferApp.svc/CCDRDUpload");
		// Real IP

		// HttpPost request = new
		// HttpPost("http://203.190.254.64/ccdrdupload/datatransferapp.datatransservice.svc/CCDRDUpload");

		// HttpPost request = new
		// HttpPost("http://ccd-dataserver.icddrb.net/ccdrdupload/datatransferapp.datatransservice.svc/CCDRDUpload");

		// HttpPost request = new HttpPost("http://172.16.10.28:8732"+
		// "/Design_Time_Addresses/DataTransferApp.svc/CCDRDUpload");

		/*
		 * HttpPost request = new HttpPost(
		 * "http://ccd-rzaman.icddrb.net/Pub/DataTransferApp.DataTransService.svc/CCDRDUpload"
		 * );
		 */

		request.setHeader("Accept", "application/json");
		request.setHeader("Content-type", "application/json");

		Gson g = new Gson();
		String s = g.toJson(trans);
		StringEntity entity = new StringEntity(s, "UTF-8");// MakeEmployees());

		try {

			request.setEntity(entity);

			HttpParams httpParameters = new BasicHttpParams();

			int timeoutConnection = 0;// Timeout for establishing connection
			HttpConnectionParams.setConnectionTimeout(httpParameters,
					timeoutConnection);
			int timeoutSocket = 0;// Timeout for establishing data transfer work
			HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
			DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);

			HttpResponse response;
			try {
				response = httpClient.execute(request);
				String _response;
				_response = EntityUtils.toString(response.getEntity());
				TransData[] sectionArray = new Gson().fromJson(_response,
						TransData[].class);
				ArrayList<TransData> tdata = new ArrayList<TransData>();
				for (int i = 0; i < sectionArray.length; i++) {
					tdata.add(sectionArray[i]);
				}

				if (UpdateFlag(tdata) == true)
					return true;
				else
					return false;

				// return true;

				/*
				 * if(sectionArray==null) return false; else return true;
				 */

			} catch (Exception e) {
				CommonStaticClass.showMyAlert(contxt, "Message", e.getMessage()
						.toString());
				return false;

			}

		} catch (Exception e) {
			/*
			 * Log.e("", e.printStackTrace());
			 */
			// return false;
		}
		return false;

	}

	private boolean UpdateFlag(ArrayList<TransData> transactionData) {
		try {
			for (TransData transData : transactionData) {

				String DatabaseName = CommonStaticClass.DB+".sqlite";
						
						//this.GetDataBaseName(transData.getDatabaseNm(), _DatabseList);

				String sql = "";

				// String WhereClause = "";
				ArrayList<InsertStatement> iStatements = transData
						.getIStatement();
				// String TableName = transData.getTableNm();

				for (InsertStatement insertStatement : iStatements) {
					sql = "UPDATE " + transData.getTableNm()
							+ " SET IsTransferred = '1' WHERE ";

					List<PrimaryClm> primarykeycolumns = insertStatement
							.getPrimaryClmList();
					String WhereClause = "";
					for (PrimaryClm columns : primarykeycolumns) {

						WhereClause = WhereClause + columns.get_ClumnMn() + "="
								+ columns.getClumnValue() + " AND ";
					}
					if (WhereClause.length() > 0) {
						String filter = WhereClause.substring(0,
								WhereClause.length() - 5);
						sql = sql + filter + "";
						UpdatedataBase(DatabaseName, sql);
					}
				}

			}
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}

	}

	private void UpdatedataBase(String DBName, String SqlStatement) {
		// Context contxt = getApplicationContext();
		try {

			/*DatabaseHelper.DB_NAME = DBName;
			dbHelper = new DatabaseHelper(contxt);
			dbHelper.openDataTransferToolDataBasesFrmList();

			dbHelper = DatabaseHelper.getInstance();*/

			dbHelper.executeDMLQueryIsTransferred(SqlStatement);
		} catch (Exception e) {
			CommonStaticClass.showMyAlert(contxt, "Error",
					"Error Updating Databse");

		} 

	}

	/*
	 * public ArrayList<PrimaryClm> MakePrimaryKeyColumn(Context con, Cursor c )
	 * { ArrayList<PrimaryClm> columns = new ArrayList<PrimaryClm>(); return
	 * columns;
	 * 
	 * }
	 */

}
