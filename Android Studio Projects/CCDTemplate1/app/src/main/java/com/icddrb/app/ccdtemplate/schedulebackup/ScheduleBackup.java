package com.icddrb.app.ccdtemplate.schedulebackup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import com.icddrb.app.ccdtemplate.CommonStaticClass;
import com.icddrb.app.ccdtemplate.datatransfertool.FileRead;
import com.icddrb.app.ccdtemplate.datatransfertool.TransData;
import com.icddrb.app.ccdtemplate.db.DatabaseHelper;

import net.sqlcipher.Cursor;
import net.sqlcipher.SQLException;
import net.sqlcipher.database.SQLiteDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
    private boolean mAvailable =  true;
    /*private static String DB_PATH = "/mnt/sdcard/";
    private static String DB_NAME = "ccdtemplate.sqlite";
    private static String DB_PASS = "ccd@app";*/
    private static String DB_PATH = "";
    private static String DB_NAME = "";
    private static String DB_PASS = "";
    protected static final int DONE = 1;
    ArrayList<TransData> trans = new ArrayList<TransData>();

    public static final String SD_CARD = "sdCard";
    public static final String EXTERNAL_SD_CARD = "externalSdCard";

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

//            SharedPreferences.Editor editor = app_preferences.edit();
//            editor.putString("last_backup", System.currentTimeMillis() + "");
//            editor.commit();

        /*    new Thread() {

                public void run() {

                    Looper.prepare();*/
            if (isNetworkAvailable()) {

                TransferFile();
                Toast.makeText(context, "Backup", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(context, "No Network available", Toast.LENGTH_SHORT).show();
                copyDataBaseToSdcard();
            }
              /*      Looper.loop();
                }

            }.start();
*/


//            String sql = "Insert into tblMainQues(dataid,assetid) values (11,22); ";
//            executeDMLQuery(sql);
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
     /*           if (file.CallWebService(trans)) {
                       *//* if(CommonStaticClass.entryUdpated==0)
                        {
                            Toast.makeText(context, "No New Data Avaible For CCD Data Server", Toast.LENGTH_SHORT).show();
//                            CommonStaticClass.showMyAlert(context,"Message",
//                                    "No New Data Avaible For CCD Data Server");
                        }
                        else
                        {
                            Toast.makeText(context, "Data Successfully Sent To CCD Data Server. Total No. of Sent " +
                                    "Entries: "+CommonStaticClass.entryUdpated, Toast.LENGTH_SHORT).show();
//                            CommonStaticClass.showMyAlert(con,"Message",
//                                    "Data Successfully Sent To CCD Data Server. Total No. of Sent " +
//                                            "Entries: "+CommonStaticClass.entryUdpated);
                        }*//*

                } else {
                    Toast.makeText(ctx, "Could not connect to server. Please try again.", Toast.LENGTH_SHORT).show();
//                        CommonStaticClass.showMyAlert(con,"Message",
//                                "Could not connect to server. Please try again.");


                }*/
            } else {
                Toast.makeText(ctx, "No Internet Connectivity", Toast.LENGTH_SHORT).show();
//                    CommonStaticClass.showMyAlert(con,"Message","No Internet Connectivity");
            }
            // Toast.makeText(con, "Transfered", 1000).show();
            // progressDialog.dismiss();
        } catch (Exception e) {
//                CommonStaticClass.showMyAlert(con,"Message",e.getMessage().toString());
            // progressDialog.dismiss();
            Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_LONG).show();

               /* * Message msg = new Message(); msg.what = COPY_FAILED;
                * handler.sendMessage(msg);*/

        }
    }


    /**
     * @return True if the external storage is available. False otherwise.
     */
    public static boolean isAvailable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public static String getSdCardPath() {
        return Environment.getExternalStorageDirectory().getPath() + "/";
    }

    /**
     * @return True if the external storage is writable. False otherwise.
     */
    public static boolean isWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;

    }

    /**
     * @return A map of all storage locations available
     */
    public static Map<String, File> getAllStorageLocations() {
        Map<String, File> map = new HashMap<String, File>(10);

        List<String> mMounts = new ArrayList<String>(10);
        List<String> mVold = new ArrayList<String>(10);
        mMounts.add("/mnt/sdcard");
        mVold.add("/mnt/sdcard");

        try {
            File mountFile = new File("/proc/mounts");
            if(mountFile.exists()){
                Scanner scanner = new Scanner(mountFile);
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    if (line.startsWith("/dev/block/vold/")) {
                        String[] lineElements = line.split(" ");
                        String element = lineElements[1];

                        // don't add the default mount path
                        // it's already in the list.
                        if (!element.equals("/mnt/sdcard"))
                            mMounts.add(element);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            File voldFile = new File("/system/etc/vold.fstab");
            if(voldFile.exists()){
                Scanner scanner = new Scanner(voldFile);
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    if (line.startsWith("dev_mount")) {
                        String[] lineElements = line.split(" ");
                        String element = lineElements[2];

                        if (element.contains(":"))
                            element = element.substring(0, element.indexOf(":"));
                        if (!element.equals("/mnt/sdcard"))
                            mVold.add(element);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        for (int i = 0; i < mMounts.size(); i++) {
            String mount = mMounts.get(i);
            if (!mVold.contains(mount))
                mMounts.remove(i--);
        }
        mVold.clear();

        List<String> mountHash = new ArrayList<String>(10);

        for(String mount : mMounts){
            File root = new File(mount);
            if (root.exists() && root.isDirectory() && root.canWrite()) {
                File[] list = root.listFiles();
                String hash = "[";
                if(list!=null){
                    for(File f : list){
                        hash += f.getName().hashCode()+":"+f.length()+", ";
                    }
                }
                hash += "]";
                if(!mountHash.contains(hash)){
                    String key = SD_CARD + "_" + map.size();
                    if (map.size() == 0) {
                        key = SD_CARD;
                    } else if (map.size() == 1) {
                        key = EXTERNAL_SD_CARD;
                    }
                    mountHash.add(hash);
                    map.put(key, root);
                }
            }
        }

        mMounts.clear();

        if(map.isEmpty()){
            map.put(SD_CARD, Environment.getExternalStorageDirectory());
        }
        return map;
    }
    private boolean IsTablet() {

        // //****************This Code for find the path of SD
        // Card************************

        String primary_sd = System.getenv("EXTERNAL_STORAGE");
        if (primary_sd != null)
            Log.i("EXTERNAL_STORAGE", primary_sd);
        String secondary_sd = System.getenv("SECONDARY_STORAGE");
        if (secondary_sd != null)
            Log.i("SECONDARY_STORAGE", secondary_sd);
        File path = Environment.getExternalStorageDirectory();
        String s = path.getPath();
        // //////////////////////////////////////////////////////////////////////////

        try {
            DisplayMetrics dm = ctx.getResources().getDisplayMetrics();

            float screenWidth = dm.widthPixels / dm.xdpi;

            float screenHeight = dm.heightPixels / dm.ydpi;

            double size = Math.sqrt(Math.pow(screenWidth, 2) +

                    Math.pow(screenHeight, 2));

            // Tablet devices should have a screen size greater than 6 inches

            return size >= 6;

        } catch (Throwable t) {

           // Log.e("Failed to compute screen size", "Error");
            return false;
        }
    }

    public void copyDataBaseToSdcard() throws IOException
    {
        final Calendar c = Calendar.getInstance();
        int dateYear = c.get(Calendar.YEAR);
        int dateMonth = c.get(Calendar.MONTH) + 1;
        int dateDay = c.get(Calendar.DAY_OF_MONTH);
        long mil = System.currentTimeMillis();

        InputStream databaseInput = null;

        String inFileName = DB_PATH + DB_NAME + ".sqlite";

        String today = dateDay + "-" + dateMonth + "-" + dateYear;
        String outDir = "";
        String outDBDir = "";
        String outDatedMilDir = "";
        String outFileName = "";

        Map<String, File> externalLocations = getAllStorageLocations();
        File sdCard = externalLocations.get(SD_CARD);
        File externalSdCard = externalLocations.get(EXTERNAL_SD_CARD);
//        if (IsTablet() == true) {
        if(externalSdCard != null ) {
                outDir = externalSdCard.getAbsolutePath() + "/" + "icddrbDB/";

                outDBDir = externalSdCard.getAbsolutePath() + "/" + "icddrbDB/" + DB_NAME;

                outDatedMilDir = externalSdCard.getAbsolutePath() + "/" + "icddrbDB/" + DB_NAME
                        + "/" + today + "_" + mil;

                outFileName = outDatedMilDir + "/" + DB_NAME + ".sqlite";
            }
        else
        {
            outDir = sdCard.getAbsolutePath() + "/" + "icddrbDB/";

            outDBDir = sdCard.getAbsolutePath() + "/" + "icddrbDB/" + DB_NAME;

            outDatedMilDir = sdCard.getAbsolutePath() + "/" + "icddrbDB/" + DB_NAME
                    + "/" + today + "_" + mil;

            outFileName = outDatedMilDir + "/" + DB_NAME + ".sqlite";
        }
        /*} else {
            // get to mobile
            outDir = externalSdCard.getAbsolutePath() +"/" + "icddrbDB/";

            outDatedDir = externalSdCard.getAbsolutePath() +"/" + "icddrbDB/" + dDirName;

            outDatedMilDir = externalSdCard.getAbsolutePath() +"/" + "icddrbDB/" + dDirName + "/" + mil;

            outFileName = externalSdCard.getAbsolutePath() +"/" + "icddrbDB/" + dDirName + "/" + mil
                    + "/" + DB_NAME+".sqlite";

        }*/

        File dir = new File(outDir);
        File dDir = new File(outDBDir);
        if (dir.exists()) {
            if (dDir.exists()) {

            } else {
                if (dDir.mkdirs()) {
                    Log.e("making", outDBDir + "dir");
                }
            }
        } else {
            if (dir.mkdirs()) {
                Log.e("making", "dir");
                if (dDir.mkdirs()) {
                    Log.e("making", outDBDir + "dir");
                }
            }
        }
        File dmDir = new File(outDatedMilDir);
        if (dmDir.exists()) {
            File file = new File(outFileName);
            if (file.exists()) {
                if (file.delete()) {
                    Log.e("deleting", "file");
                }
            }
        } else {
            if (dmDir.mkdirs()) {
                Log.e("making", outDatedMilDir + "dir");
            }
        }

        OutputStream databaseOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        File f = new File(inFileName);
        if (f.exists()) {
            databaseInput = new FileInputStream(f);
            while ((length = databaseInput.read(buffer)) > 0) {
                databaseOutput.write(buffer, 0, length);
                databaseOutput.flush();
                Log.e("writing to ", outDatedMilDir + "");
            }
            databaseInput.close();
            databaseOutput.flush();
            databaseOutput.close();
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

   /* private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {

                case DONE:
                   showMessage();
                    break;

            }

        }
    }*/;

    private void showMessage() {
    }
}
