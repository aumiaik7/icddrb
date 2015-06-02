package com.icddrb.datatransfertool;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CommonStaticClass {

	public static String pName = "com.icddrb.datatransfertool";
	public static String userSpecificId;
	public static String AssetID="";	
	public static void showFinalAlert(Context con,CharSequence message) {
        new AlertDialog.Builder(con)
        .setTitle("User Credential Incorrect!!!")
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
    
	public static int SHORT_TOAST = 0;
	public static int LONG_TOAST = 1;

	public static void DisplayToast(Context caller, String toastMsg,
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
	}
	
	public static void SetMobileDataEnabled(Context context, boolean enabled) {
		try {
		   final ConnectivityManager conman = (ConnectivityManager)  context.getSystemService(Context.CONNECTIVITY_SERVICE);
		   Class conmanClass;
		
			conmanClass = Class.forName(conman.getClass().getName());
		
		   final java.lang.reflect.Field iConnectivityManagerField = conmanClass.getDeclaredField("mService");
		   iConnectivityManagerField.setAccessible(true);
		   final Object iConnectivityManager = iConnectivityManagerField.get(conman);
		   final Class iConnectivityManagerClass =  Class.forName(iConnectivityManager.getClass().getName());
		   final Method setMobileDataEnabledMethod = iConnectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
		   setMobileDataEnabledMethod.setAccessible(true);

		   setMobileDataEnabledMethod.invoke(iConnectivityManager, enabled);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
/*	public void SetWifiEnabled(boolean truefalse)
	{
		
		WifiManager wifiManager = (WifiManager) this.getSystemService(this.WIFI_SERVICE);
		wifiManager.setWifiEnabled(true);

		if (!wifiManager.isWifiEnabled()) {
			CommonStaticClass.showMyAlert(con, "Message","Please Wait. Turning internet on to device");
			return;
		}
	}*/
	
}
