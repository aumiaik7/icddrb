package com.example.edcryption;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import net.sqlcipher.database.SQLiteDatabase;

public class MainActivity extends Activity implements OnClickListener {

    private static final int REQUEST_PICK_FILE = 1;

    private TextView mFilePathTextView;
    private Button mStartActivityButton,encryptButton,decryptButton;
    private File selectedFile;
    private int encordec = 0;
    public static String APP_PATH = "/mnt/sdcard/";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        SQLiteDatabase.loadLibs(this);
        createDirIfNotExists("EDCryption");
        createDirIfNotExists("EDCryption/Encrypted DBs");
        createDirIfNotExists("EDCryption/Decrypted DBs");

        // Set the views
//        mFilePathTextView = (TextView)findViewById(R.id.file_path_text_view);
//        mStartActivityButton = (Button)findViewById(R.id.start_file_picker_button);
//        mStartActivityButton.setOnClickListener(this);      
        encryptButton = (Button)findViewById(R.id.encButton);
        decryptButton = (Button)findViewById(R.id.decButton);
        
        encryptButton.setOnClickListener(this);
        decryptButton.setOnClickListener(this);
        
    }

    Intent intent = null;
    public void onClick(View v) {
        switch(v.getId()) {
        case R.id.encButton:
        	encordec = 1;
        	  // Create a new Intent for the file picker activity
            intent = new Intent(this, FilePickerActivity.class);

            // Set the initial directory to be the sdcard
            intent.putExtra(FilePickerActivity.EXTRA_FILE_PATH,Environment.getExternalStorageDirectory().getAbsolutePath());

            // Show hidden files
            //intent.putExtra(FilePickerActivity.EXTRA_SHOW_HIDDEN_FILES, true);

            //Only make .png files visible
            ArrayList<String> extensions = new ArrayList<String>();
            extensions.add(".sqlite");
            intent.putExtra(FilePickerActivity.EXTRA_ACCEPTED_FILE_EXTENSIONS, extensions);

            // Start the activity
            startActivityForResult(intent, REQUEST_PICK_FILE);
        	
        break;
        case R.id.decButton:
        	
        break;
//        case R.id.start_file_picker_button:
//            // Create a new Intent for the file picker activity
//            intent = new Intent(this, FilePickerActivity.class);
//
//            // Set the initial directory to be the sdcard
//            intent.putExtra(FilePickerActivity.EXTRA_FILE_PATH,"/mnt/sdcard/");
//
//            // Show hidden files
//            //intent.putExtra(FilePickerActivity.EXTRA_SHOW_HIDDEN_FILES, true);
//
//            //Only make .png files visible
//            ArrayList<String> extensions = new ArrayList<String>();
//            extensions.add(".sqlite");
//            intent.putExtra(FilePickerActivity.EXTRA_ACCEPTED_FILE_EXTENSIONS, extensions);
//
//            // Start the activity
//            startActivityForResult(intent, REQUEST_PICK_FILE);
//            break;
//
////        case R.id.You_can_handle_more_onclick_events_from_here:
////            //Do something
////            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            switch(requestCode) {
            case REQUEST_PICK_FILE:
                if(data.hasExtra(FilePickerActivity.EXTRA_FILE_PATH)) {
                    // Get the file path
                    selectedFile = new File(data.getStringExtra(FilePickerActivity.EXTRA_FILE_PATH));
                    // Set the file path text view
                    mFilePathTextView.setText(selectedFile.getPath());  
                    //Now you have your selected file, You can do your additional requirement with file.                
                }
            }
        }
    }
    
    public void createDirIfNotExists(String path) {
        

        File folder = new File(APP_PATH+path);
        //if (!folder.exists()) {
        	folder.mkdirs();
//                Log.e("TravellerLog :: ", "Problem creating Image folder");
//                ret = false;
           
        //}
        
    }
}