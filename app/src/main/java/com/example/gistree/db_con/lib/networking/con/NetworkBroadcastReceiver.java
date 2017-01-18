package com.example.gistree.db_con.lib.networking.con;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

public class NetworkBroadcastReceiver extends BroadcastReceiver {

    public static IntentFilter getIntentFilter() {
        IntentFilter intFilter = new IntentFilter();
        intFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        return intFilter;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectionManager cm = ConnectionManager.getConnectionManager(context);
        cm.setNetworkInfo();
    }
}
