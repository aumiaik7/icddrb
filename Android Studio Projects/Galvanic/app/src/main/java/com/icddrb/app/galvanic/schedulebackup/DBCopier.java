package com.icddrb.app.galvanic.schedulebackup;

import android.os.Environment;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by imtiaz.khan on 6/24/2015.
 */
public class DBCopier {

    public static final String SD_CARD = "sdCard";
    public static final String EXTERNAL_SD_CARD = "externalSdCard";

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


    public void copyDataBaseToSdcard(String DB_PATH,String DB_NAME) throws IOException
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
}
