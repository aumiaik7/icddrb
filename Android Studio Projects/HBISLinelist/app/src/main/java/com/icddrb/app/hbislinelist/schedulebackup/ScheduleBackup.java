package com.icddrb.app.hbislinelist.schedulebackup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.icddrb.app.hbislinelist.CommonStaticClass;
import com.icddrb.app.hbislinelist.datatransfertool.FileRead;
import com.icddrb.app.hbislinelist.datatransfertool.TransData;

import net.sqlcipher.Cursor;
import net.sqlcipher.SQLException;
import net.sqlcipher.database.SQLiteDatabase;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


/**
 * Created by imtiaz.khan on 6/18/2015.
 */
public class ScheduleBackup extends BroadcastReceiver {


    private SharedPreferences app_preferences;
    private boolean mAvailable =  true;

    private static String DB_PATH = "";
    private static String DB_NAME = "";
    private static String DB_PASS = "";
    protected static final int DONE = 1;
    ArrayList<TransData> trans = new ArrayList<TransData>();



    private Cursor mCursor = null;
    private Context ctx;


    private static SQLiteDatabase myDataBase = null,myDataBaseBASE=null;

    @Override
    public void onReceive(Context context, Intent intent) {
        ctx = context;
        SQLiteDatabase.loadLibs(context);
        String success = "";
        try{
            DB_PATH = intent.getStringExtra("dbpath");
            DB_NAME = intent.getStringExtra("dbname");
            DB_PASS = intent.getStringExtra("dbpass");
            openDataBase();
            success = "yes";


            if (isNetworkAvailable()) {

                TransferFile();
                Toast.makeText(context, "Backup", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(context, "No Network available", Toast.LENGTH_SHORT).show();
                new DBCopier().copyDataBaseToSdcard(DB_PATH,DB_NAME);
            }

        }
        catch (Exception e)
        {
            success = "No";
        }





    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void TransferFile() {
        try {
            CommonStaticClass.entryUdpated = 0;
            final FileRead file = new FileRead();
            //ArrayList<TransData> trans = new ArrayList<TransData>();

            trans = file.MakeInsertStringSB(ctx, this, DB_NAME);


            // file.SendRequest(trans);
            if (mAvailable == true) {

                new Thread() {

                    public void run() {
                        try {
                            file.CallWebServiceSB(trans);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                    }

                }.start();

            } else {
                Toast.makeText(ctx, "No Internet Connectivity", Toast.LENGTH_SHORT).show();

            }

        } catch (Exception e) {

            Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_LONG).show();



        }
    }




    public void openDataBase() throws SQLException {

        //Open the database
        if(myDataBase==null){
            String myPath = DB_PATH + DB_NAME+".sqlite";
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

        }catch (Exception e) {
            // TODO: handle exception
            return false;
        }
        return true;
    }
    public Cursor getQueryCursor(String sql)
    {
        Log.e("sql",sql);

        mCursor =myDataBase.rawQuery(sql, null);
        return mCursor;
    }
    public void cursorClose()
    {
        if(!mCursor.isClosed())
            mCursor.close();
    }


}
