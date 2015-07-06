package app.prelm.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import app.prelm.CommonStaticClass;

public class DatabaseHelper extends SQLiteOpenHelper
{
	//private static String DB_PATH = "/data/data/"+CommonStaticClass.pName+"/databases/";	 
	private static String DB_PATH = "/mnt/sdcard/";
	 
    private static String DB_NAME = "PreLE.sqlite";
 
    private static SQLiteDatabase myDataBase = null; 
 
    private final Context myContext;
    
    public static DatabaseHelper dbHelper;
    
    
    private DatabaseHelper(Context context) {
    	 
    	super(context, DB_NAME, null, 1);
    	
        this.myContext = context;
        try {
			createDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	public static DatabaseHelper getInstance(Context context){
		if(dbHelper==null){
			dbHelper = new DatabaseHelper(context);
			dbHelper.openDataBase();
			Log.e("dbhelper"," called once");
		}	
		Log.e("dbhelper"," returning");
      return dbHelper;
    }
    
    public void createDataBase() throws IOException
    {    	 
    	boolean dbExist = checkDataBase();
    	if(dbExist)
    	{    		
    		//do nothing - database already exist
    	}
    	else
    	{
 
/*    		
*     By calling this method and empty database will be created into the default system path
*     of your application so we are gonna be able to overwrite that database with our database.
*/
        	this.getReadableDatabase().close();
 
        	try {
 
    			copyDataBase();
 
    		} catch (IOException e) {
 
        		throw new Error("Error copying database");
 
        	}
    	}
 
    }
    private boolean checkDataBase()
    {
    	boolean t = false;
    	SQLiteDatabase checkDB = null;
 
    	try{
    		String myPath = DB_PATH + DB_NAME;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
    	}catch(SQLiteException e)
    	{

    		//database does't exist yet.
    		e.printStackTrace();
 
    	}finally{
        	if(checkDB != null){
        		
        		t = true;
        		checkDB.close();
     
        	}
    	}
    	return t;
    }
    private void copyDataBase() throws IOException
    {
    	InputStream databaseInput = null;
        String outFileName = DB_PATH + DB_NAME;
        OutputStream databaseOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;

       // databaseInput = myContext.getResources().openRawResource(R.raw.bloodsampledb);
        while((length = databaseInput.read(buffer)) > 0) 
        {
            databaseOutput.write(buffer, 0, length);
            databaseOutput.flush();
        }
        databaseInput.close();

        databaseOutput.flush();
        databaseOutput.close();

 
    }
    
    public void copyDataBaseToSdcard() throws IOException{
    	
        final Calendar c = Calendar.getInstance();
        int dateYear = c.get(Calendar.YEAR);
        int dateMonth = c.get(Calendar.MONTH)+1;
        int dateDay = c.get(Calendar.DAY_OF_MONTH);
//        long mil = System.currentTimeMillis();
        	
        	InputStream databaseInput = null;
            
        	String inFileName = DB_PATH + DB_NAME;
            
            String dDirName = dateDay+"-"+dateMonth+"-"+dateYear; 

            String outFileName = Environment.getExternalStorageDirectory()
            + "/icddrbDB/"+dDirName+"/"+DB_NAME+"_("+dateDay+"-"+dateMonth+"-"+dateYear+")";
            
            String outDir = Environment.getExternalStorageDirectory()
            + "/icddrbDB/";
            
            String outDatedDir = Environment.getExternalStorageDirectory()
            + "/icddrbDB/"+dDirName;
            
            File dir = new File(outDir);
            File dDir = new File(outDatedDir);
            if(dir.exists()){
            	if(dDir.exists()){
            		File file = new File(outFileName);
            		if(file.exists()){    			
            			if(file.delete()){
            				Log.e("deleting","file");
            			}
            		}	
            	}else{
            		if(dDir.mkdirs()){
        				Log.e("making",outDatedDir+"dir");
        			}
            	}
            }else{
    			if(dir.mkdirs()){
    				Log.e("making","dir");
            		if(dDir.mkdirs()){
        				Log.e("making",outDatedDir+"dir");
        			}
    			}
            }

            OutputStream databaseOutput = new FileOutputStream(outFileName);

            byte[] buffer = new byte[1024];
            int length;
            File f = new File(inFileName);
            if(f.exists()){
                databaseInput = new FileInputStream(f);
                while((length = databaseInput.read(buffer)) > 0) 
                {
                    databaseOutput.write(buffer, 0, length);
                    databaseOutput.flush();
                }
                databaseInput.close();

                databaseOutput.flush();
                databaseOutput.close();
            }            
    }
    public void openDataBase() throws SQLException{
    	 
    	//Open the database
    	/*if(myDataBase==null){
            String myPath = DB_PATH + DB_NAME;           
        	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        	myDataBase=dbHelper.getWritableDatabase();	
        	System.out.println("Its open? "  + myDataBase.isOpen());
    	}*/
    	//Open the database
    	if(myDataBase==null){
            String myPath = DB_PATH + DB_NAME;
        	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        	//myDataBase=dbHelper.getWritableDatabase(); //Blocked by Angsuman	
    	}
    }
    @Override
	public synchronized void close() {
    	    if(myDataBase != null){
    	    	myDataBase.close();
    	    }
    		    
	}

    @Override
	public void onCreate(SQLiteDatabase db) {
 
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public Cursor getQueryCursor(String sql)
	{
		Log.e("sql",sql);

		Cursor mCursor = null;
		
		try {
			mCursor = myDataBase.rawQuery(sql, null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		 
		 return mCursor;		
	}
	
	public boolean executeDMLQuery(String sql)
	{
		Log.e("sql",sql);
		try{
			myDataBase.execSQL(sql);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;		
	}
	public boolean InsertToSample(String sql)
	{
		try{
			myDataBase.execSQL(sql);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;	
		
		
	}
	
	public String GetSingleColumnData(String column, String tableName){	
		String sql = "";	
		
		sql = "Select "+ column +" from "+tableName  +" where dataid='"+CommonStaticClass.dataId+"' and ChildNo='"+ CommonStaticClass.currentChildrenCount +"'";
	
		String data = "";
			Cursor mCursor = null;
			try {
				mCursor = dbHelper.getQueryCursor(sql);
				if(mCursor.getCount()>0){
					if(mCursor.moveToFirst()){
						do{
							data = mCursor.getString(mCursor.getColumnIndex(column));
						}while(mCursor.moveToNext());
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			return data;
	}
}
