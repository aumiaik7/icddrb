package com.icddrb.app.hbislinelist.datatransfertool;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkChangeReceiver extends BroadcastReceiver{

	private static final String LOGTAG = "NetworkReceiver";

    @Override
    public void onReceive(Context ctx, Intent intent) {
	Log.i(LOGTAG, "Action: " + intent.getAction());
    	if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
	    NetworkInfo info = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
	    String typeName = info.getTypeName();
	    String subtypeName = info.getSubtypeName();
	    boolean available = info.isAvailable();
	    Log.i(LOGTAG, "Network Type: " + typeName 
			+ ", subtype: " + subtypeName
			+ ", available: " + available);
	}
    }
}