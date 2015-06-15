package com.example.edcryption;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.icddrb.app.edcryption.db.DatabaseHelper;


import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FilePickerActivity extends ListActivity {

    public final static String EXTRA_FILE_PATH = "file_path";
    public final static String EXTRA_SHOW_HIDDEN_FILES = "show_hidden_files";
    public final static String EXTRA_ACCEPTED_FILE_EXTENSIONS = "accepted_file_extensions";
    private final static String DEFAULT_INITIAL_DIRECTORY =Environment.getExternalStorageDirectory().getAbsolutePath();

    protected File mDirectory;
    protected ArrayList<File> mFiles;
    protected FilePickerListAdapter mAdapter;
    protected boolean mShowHiddenFiles = false;
    protected String[] acceptedFileExtensions;
    private static String DB_PATH = "/mnt/sdcard/";
    DatabaseHelper dbHelper;
	DatabaseHelper dbHelperBase;
	private String password = "";
    private static final int UPDATEDONE = 900;
    private ProgressDialog progressDialog;
	
	File newFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (DatabaseHelper.getInstance() == null) {
			dbHelper = new DatabaseHelper(this);
			dbHelper.openDataBase();
		} else {
			dbHelper = DatabaseHelper.getInstance();
		}
        // Set the view to be shown if the list is empty
        LayoutInflater inflator = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View emptyView = inflator.inflate(R.layout.file_picker_empty_view, null);
        ((ViewGroup)getListView().getParent()).addView(emptyView);
        getListView().setEmptyView(emptyView);

        // Set initial directory
        mDirectory = new File(DEFAULT_INITIAL_DIRECTORY);

        // Initialize the ArrayList
        mFiles = new ArrayList<File>();

        // Set the ListAdapter
        mAdapter = new FilePickerListAdapter(this, mFiles);
        setListAdapter(mAdapter);

        // Initialize the extensions array to allow any file extensions
        acceptedFileExtensions = new String[] {};

        // Get intent extras
        if(getIntent().hasExtra(EXTRA_FILE_PATH)) {
            mDirectory = new File(getIntent().getStringExtra(EXTRA_FILE_PATH));
        }
        if(getIntent().hasExtra(EXTRA_SHOW_HIDDEN_FILES)) {
            mShowHiddenFiles = getIntent().getBooleanExtra(EXTRA_SHOW_HIDDEN_FILES, false);
        }
        if(getIntent().hasExtra(EXTRA_ACCEPTED_FILE_EXTENSIONS)) {
            ArrayList<String> collection = getIntent().getStringArrayListExtra(EXTRA_ACCEPTED_FILE_EXTENSIONS);
            acceptedFileExtensions = (String[]) collection.toArray(new String[collection.size()]);
        }
    }

    @Override
    protected void onResume() {
        refreshFilesList();
        super.onResume();
    }

    /**
     * Updates the list view to the current directory
     */
    protected void refreshFilesList() {
        // Clear the files ArrayList
        mFiles.clear();

        // Set the extension file filter
        ExtensionFilenameFilter filter = new ExtensionFilenameFilter(acceptedFileExtensions);

        // Get the files in the directory
        File[] files = mDirectory.listFiles(filter);
        if(files != null && files.length > 0) {
            for(File f : files) {
            	
                if(f.isHidden() && !mShowHiddenFiles) {
                    // Don't add the file
                    continue;
                    
                }
                // Add the file the ArrayAdapter	
                if(!f.isDirectory())
                	mFiles.add(f);
            }

            Collections.sort(mFiles, new FileComparator());
        }
        mAdapter.notifyDataSetChanged();
    }
    
    /**
     * Updates the list view to the current directory
     */
    protected void refreshFilesListBack() {
        // Clear the files ArrayList
        mFiles.clear();

        // Set the extension file filter
        ExtensionFilenameFilter filter = new ExtensionFilenameFilter(acceptedFileExtensions);

        // Get the files in the directory
        File[] files = mDirectory.listFiles(filter);
        if(files != null && files.length > 0) {
            for(File f : files) {
            	
                if(f.isHidden() && !mShowHiddenFiles) {
                    // Don't add the file
                    continue;
                    
                }
                // Add the file the ArrayAdapter	
              
                	mFiles.add(f);
            }

            Collections.sort(mFiles, new FileComparator());
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
//        if(mDirectory.getParentFile() != null) {
//            // Go to parent directory
//        	
//            mDirectory = mDirectory.getParentFile();
//            refreshFilesListBack();
//            return;
//        }
    	this.finish();
        super.onBackPressed();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        newFile = (File)l.getItemAtPosition(position);

        if(newFile.isFile()) {
        	
        	
      /*      // Set result
            Intent extra = new Intent();
            extra.putExtra(EXTRA_FILE_PATH, newFile.getAbsolutePath());
            setResult(RESULT_OK, extra);
            // Finish the activity
        	Toast.makeText(this, newFile.getAbsolutePath(), 1000).show();
            finish();*/
        	//encrypt(newFile.getAbsolutePath());
        	if(noPassword())
        	{
        		showPasswordDialog();
        	}
        	else
        	{
        		showMyAlert(this, "ALERT!!", "This Database is already Encrypted. Do you want to set a new password?");
        	}
        		
        	
        } else {
            mDirectory = newFile;
            // Update the files list
            refreshFilesList();
        }

        //super.onListItemClick(l, v, position, id);
    }

    

	private class FilePickerListAdapter extends ArrayAdapter<File> {

        private List<File> mObjects;

        public FilePickerListAdapter(Context context, List<File> objects) {
            super(context, R.layout.file_picker_list_item, android.R.id.text1, objects);
            mObjects = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View row = null;

            if(convertView == null) { 
                LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.file_picker_list_item, parent, false);
            } else {
                row = convertView;
            }

            File object = mObjects.get(position);

            ImageView imageView = (ImageView)row.findViewById(R.id.file_picker_image);
            TextView textView = (TextView)row.findViewById(R.id.file_picker_text);
            // Set single line
            textView.setSingleLine(true);

            textView.setText(object.getName());
            if(object.isFile()) {
                // Show the file icon
                imageView.setImageResource(R.drawable.file);
            } else {
                // Show the folder icon
                imageView.setImageResource(R.drawable.folder);
            }

            return row;
        }

    }

    private class FileComparator implements Comparator<File> {
        public int compare(File f1, File f2) {
            if(f1 == f2) {
                return 0;
            }
            if(f1.isDirectory() && f2.isFile()) {
                // Show directories above files
                return -1;
            }
            if(f1.isFile() && f2.isDirectory()) {
                // Show files below directories
                return 1;
            }
            // Sort the directories alphabetically
            return f1.getName().compareToIgnoreCase(f2.getName());
        }
    }

    private class ExtensionFilenameFilter implements FilenameFilter {
        private String[] mExtensions;

        public ExtensionFilenameFilter(String[] extensions) {
            super();
            mExtensions = extensions;
        }

        public boolean accept(File dir, String filename) {
            if(new File(dir, filename).isDirectory()) {
                // Accept all directory names
                return true;
            }
            if(mExtensions != null && mExtensions.length > 0) {
                for(int i = 0; i < mExtensions.length; i++) {
                    if(filename.endsWith(mExtensions[i])) {
                        // The filename ends with the extension
                        return true;
                    }
                }
                // The filename did not match any of the extensions
                return false;
            }
            // No extensions has been set. Accept all file extensions.
            return true;
        }
    }
    
    public boolean noPassword()
    {
    	Cursor cursor = null;
    	String sql = "Select * from tableInfo where dbName='"+ newFile.getName() +"'";
    	boolean noPassword = false;
    	try
    	{
	    	cursor = dbHelper.getQueryCursor(sql);
	    	if(cursor.getCount()>0)
	    	{
	    		noPassword = false;
	    	}
	    	else 
	    		noPassword = true;
    	}
    	 catch (Exception e) {
 			// TODO: handle exception
 			e.printStackTrace();
 		} finally {
 			if (cursor != null)
 				cursor.close();
 		}
    	
    	return noPassword;
    }
    
    public  void showMyAlert(Context con, String title,
			CharSequence message) {
	/*	new AlertDialog.Builder(con).setTitle(title).setMessage(message)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.dismiss();
					}
				}).setCancelable(false).show();*/

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);



        // Set up the buttons
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               showPasswordDialog();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
	}
    public void showPasswordDialog()
    {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle("SET PASSWORD");

    	// Set up the input
    	final EditText input = new EditText(this);
    	// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
    	//input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    	builder.setView(input);

    	// Set up the buttons
    	builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { 
    	    @Override
    	    public void onClick(DialogInterface dialog, int which) {
    	        password = input.getText().toString();
    	        createRow();
    	    }
    	});
    	builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
    	    @Override
    	    public void onClick(DialogInterface dialog, int which) {
    	        dialog.cancel();
    	    }
    	});

    	builder.show();
    }
    
    public void createRow() {
        progressDialog = ProgressDialog.show(this, "Wait",
                "Please wait while encrypting database");

        final boolean[] done = {false};
        new Thread() {

            public void run() {
                Looper.prepare();
                if (EncryptOrDecrypt.encrypt(newFile, password)) {
                    String sql = "insert into tableInfo (dbName,password) values ('" + newFile.getName() + "'" +
                            ",'" + password + "') ";

                    if (dbHelper.executeDMLQuery(sql)) {

                    }
                }

                Message msg = new Message();
                msg.what = UPDATEDONE;
                handler.sendMessage(msg);
                Looper.loop();

            }
        }.start();





    }

    public Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATEDONE:
                    progressDialog.dismiss();
                    /*runOnUiThread(new Runnable() {
                        public void run() {

                            new AlertDialog.Builder(getApplicationContext()).setTitle("Done").setMessage("Encryption Complete")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            dialog.dismiss();
                                        }
                                    }).setCancelable(false).show();

                        }
                    });*/
                    break;
            }

        }
    };
}
