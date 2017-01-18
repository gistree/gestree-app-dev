package com.example.gistree.db_con.lib.networking.con;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionManager {

    private static ConnectionManager instance;
    private Context _context;
    private ConnectivityManager _cm;
    private NetworkInfo _ninfo;

    /**
     *
     * @param c - Context
     */
    private ConnectionManager(Context c) {
        this._context = c;
        this._cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        this._ninfo = this._cm.getActiveNetworkInfo();
    }

    public static void registerBroadCastReceiver(Context c){
        c.registerReceiver(new NetworkBroadcastReceiver(),NetworkBroadcastReceiver.getIntentFilter());
    }
    /**
     *
     * @param c - Context
     * @return Connextion Manager
     */
    public static ConnectionManager getConnectionManager(Context c){
        if(instance == null){
            instance = new ConnectionManager(c);
        }
        return instance;
    }
    public void setNetworkInfo() {
        this._ninfo = this._cm.getActiveNetworkInfo();
    }
    public boolean isConnected(){
        return this._ninfo != null && this._ninfo.isConnectedOrConnecting();
    }
    public int getConnectionType(){
        if(this.isConnected()){
            return this._ninfo.getType();
        }
        return 0;
    }
    public boolean isUsingWiFi(){
        if(this._ninfo != null){
            return this._ninfo.getType() == ConnectivityManager.TYPE_WIFI;
        }else{
            return false;
        }
    }
    public boolean isUsingMobileData(){
        if(this._ninfo != null){
            return this._ninfo.getType() == ConnectivityManager.TYPE_MOBILE;
        }else{
            return false;
        }
    }


}
