package com.example.gistree.db_con.lib.networking.data;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class ResponseError extends AbstractHttpResponse {

    public static final String TIMESTAMP = "Timestamp";
    public static final String DATABASE = "Database";
    public static final String SERVER_ERROR = "Server";
    public static final String CLIENT_ERROR = "Sequence";

    private String name;
    private int errorCode;
    private boolean savedData;
    private String errorMessage;
    private String errorOn;

    public ResponseError(String type, HttpURLConnection con){
        super.setType(type);
        try {
            StringBuilder response = new StringBuilder();
            InputStream is = con.getErrorStream();
            BufferedReader r = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = r.readLine()) != null) {
                response.append(line);
            }
            this._parseError(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ResponseError(String type, String name, int errorCode, boolean savedData, String errorMessage, String errorOn) {
        super.setType(type);
        this.name = name;
        this.errorCode = errorCode;
        this.savedData = savedData;
        this.errorMessage = errorMessage;
        this.errorOn = errorOn;
    }
    private void _parseError(String err){
        try {
            JSONObject errJSON =  new JSONObject(err);
            this.name = errJSON.getString("name");
            this.errorCode = errJSON.getInt("resCode");
            this.savedData = errJSON.getBoolean("savedData");
            this.errorMessage = errJSON.getString("message");
            this.errorOn = errJSON.getJSONObject("errorOn").getString("id_tree");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void raiseError(Context c){
        if(this.name.equals(ResponseError.CLIENT_ERROR)){
        }else{
        }
    }
    public boolean handleError(){
        return this.savedData;
    }
    public String getErrorName(){
        return this.name;
    }
    public String getErrorLocation(){
        return this.errorOn;
    }

    @Override
    public String toString() {
        return this.errorMessage;
    }
}

