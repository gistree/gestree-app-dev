package com.example.gistree.db_con.lib.classes;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

public class Metadata {

    public static String getAPIUrl(Context context){
        return Metadata.getMetaData(context, "API_URL");
    }
    public static String getDatabasePath(Context context){
        return Metadata.getMetaData(context, "Database_Path");
    }
    public static String getDatabaseName(Context context){
        return Metadata.getMetaData(context, "Database_Name");
    }
    public static String getMetaData(Context context, String name){
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            return bundle.getString(name);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("Metadata::getMetaData", "NameNotFoundException" + e.getMessage());
        }
        return null;
    }

}
