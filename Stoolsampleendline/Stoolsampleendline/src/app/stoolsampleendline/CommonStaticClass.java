package app.stoolsampleendline;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class CommonStaticClass {

	public static String pName = "com.app.stoolsampleendline";	
	public static final String ADDMODE = "add";
	public static final String EDITMODE = "edit";
	public static final String SPECIALADD = "specialadd";	
	public static String userSpecificId = "";
	public static String dataId = "";
	
	public static String mode ="";	
	
	public static int currentSLNo = 1;	
	public static boolean langBng = false;	
	public static String participantType = "";
	public static boolean isChecked = false;
	protected static int numberofchildren = 0;
	public static int currentChildrenCount = 1;
	protected static DataItem itemToEdite = null;
	public static String AssetID = "";
	

	

	public static void showFinalAlert(Context con,CharSequence message) {
        new AlertDialog.Builder(con)
        .setTitle("Message")
        .setMessage(message)
        .setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                            int whichButton) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }
    public static void showMyAlert(Context con,String title,CharSequence message) {
        new AlertDialog.Builder(con)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                            int whichButton) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }
	
}
