package com.icddrb.app.ccdtemplate.schedulebackup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.icddrb.app.ccdtemplate.CommonStaticClass;
import com.icddrb.app.ccdtemplate.datatransfertool.FileRead;
import com.icddrb.app.ccdtemplate.datatransfertool.TransData;
import com.icddrb.app.ccdtemplate.db.DatabaseHelper;

import net.sqlcipher.SQLException;
import net.sqlcipher.database.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by imtiaz.khan on 6/18/2015.
 */
public class ScheduleBackup extends BroadcastReceiver {

    /*@Override
    public void onReceive(Context arg0, Intent arg1) {
        // For our recurring task, we'll just display a message
        Toast.makeText(arg0, "I'm running", Toast.LENGTH_SHORT).show();

    }*/
    private SharedPreferences app_preferences;
    private boolean mAvailable = false;
    private static String DB_PATH = "";
    private static String DB_NAME = "";
    private static String DB_PASS = "";


    private static SQLiteDatabase myDataBase = null,myDataBaseBASE=null;

    @Override
    public void onReceive(Context context, Intent intent) {
        String success = "";
        try{
            DB_PATH = intent.getStringExtra("dbpath");
            DB_NAME = intent.getStringExtra("dbname");
            DB_PASS = intent.getStringExtra("dbpass");
            openDataBase();

//            String sql = "Insert into tblMainQues(dataid,assetid) values (11,22); ";
//            executeDMLQuery(sql);
        }
        catch (Exception e)
        {
            success = "No";
        }

        Toast.makeText(context, DB_PATH+DB_NAME+DB_PASS, Toast.LENGTH_SHORT).show();


    }
    public void openDataBase() throws SQLException {

        //Open the database
        if(myDataBase==null){
            String myPath = DB_PATH + DB_NAME;
            myDataBase = SQLiteDatabase.openDatabase(myPath, DB_PASS, null, SQLiteDatabase.OPEN_READWRITE);
            //myDataBase = SQLiteDatabase.openOrCreateDatabase(myPath, getpw(), null);
            //myDataBase = SQLiteDatabase.openOrCreateDatabase(myPath, getpw(), null);

        }
    }

    public boolean executeDMLQuery(String sql)
    {
        //SQLiteDatabase SDcardDB = null;
        Log.e("sql", sql);
        try{
            myDataBase.execSQL(sql);
           //Addition
//			String myPath = SDDB_PATH + DB_NAME;
//	   		SDcardDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
//	   		SDcardDB.execSQL(sql);
            //end of Addition
        }catch (Exception e) {
            // TODO: handle exception
            return false;
        }
        return true;
    }
}
