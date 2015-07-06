package com.icddrb.app.ChildIdentificationdb;

//import com.icddrb.app.NHSSchoolTeacher.questions.ParentActivity;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.ViewGroup;

public class CommonStaticClass {

	public static String pName = "com.icddrb.app.Childidentificationendline";
	public static final String ADDMODE = "add";
	public static final String EDITMODE = "edit";
	public static final String SPECIALADD = "specialadd";
	public static String userSpecificId = "";
	public static String dataId = "";
	public static String mode = "";
	public static String VersionNo = "1";

	public static int currentSLNo = 1;
	public static boolean langBng = false;
	public static String participantType = "";
	public static boolean isChecked = false;
	protected static int numberofchildren = 0;
	public static int currentChildrenCount = 1;
	protected static DataItem itemToEdite = null;
	public static boolean DialogResult = false;
	public static ViewGroup sampleCollectorView = null;
	public static String AssetID = "";
	

	public static void showFinalAlert(Context con, CharSequence message) {
		new AlertDialog.Builder(con).setTitle("User Credential Incorrect!!!")
				.setMessage(message)

				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.dismiss();
					}
				}).setCancelable(false).show();
	}

	public static void showMyAlert(Context con, String title,
			CharSequence message) {
		new AlertDialog.Builder(con).setTitle(title).setMessage(message)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.dismiss();
					}
				}).setCancelable(false).show();
	}

	public static void showDialog(String title, CharSequence message,
			final Activity mainActivity) {

		AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
		builder.setTitle(title);
		builder.setMessage(message);

		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				CommonStaticClass.DialogResult = true;
				dialog.dismiss();

			}

		});
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {

				CommonStaticClass.DialogResult = false;
				dialog.dismiss();

			}
		});

		builder.setCancelable(false);

		builder.show();

	}
	protected static boolean futureDateValidator(int year,int month,int day){
		
        final Calendar c = Calendar.getInstance();
        int dateYear = c.get(Calendar.YEAR);
        int dateMonth = c.get(Calendar.MONTH);
        int dateDay = c.get(Calendar.DAY_OF_MONTH);
        
        if(year>dateYear){
            return true;
        }else if(year<=dateYear){
        	if(year<dateYear){
        		return false;
        	}
            if((month+1)>(dateMonth+1)){
                return true;
            }else if((month+1)<=(dateMonth+1)){
            	if((month+1)<(dateMonth+1)){
            		return false;
            	}
                if(day>dateDay){
                    return true;
                }else{
                	return false;
                }
            }
        }
        return false;
	}
}
