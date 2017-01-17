package com.example.gistree.db_con.lib.networking;

import com.example.gistree.db_con.lib.classes.records.RecordInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class HttpRequest {

    private String requestType;
    private String url;
    private String timestamp;
    private ArrayList<? extends RecordInterface> records;

    private HttpRequest(String url){
        this.requestType = "GET";
        this.url = url;
    };
    private HttpRequest(String url, String timestamp, ArrayList<? extends RecordInterface> records) {
        this.requestType = "POST";
        this.url = url;
        this.timestamp = timestamp;
        this.records = records;
    }

    public static HttpRequest makeGETRequest(String url){
        return new HttpRequest(url);
    }
    public static HttpRequest makePOSTRequest(String url, String timestamp, ArrayList<? extends RecordInterface> records){
        return new HttpRequest(url,timestamp,records);
    }

    public URL getURL() throws MalformedURLException {
        return new URL(this.url);
    }
    public String getRequestType(){
        return this.requestType;
    }

    public JSONObject getData() throws Exception {
        JSONObject json = new JSONObject();
        try {
            json.put("timestamp", this.timestamp);
            json.put("data", new JSONArray());
            for (RecordInterface record : this.records) {
                json.accumulate("data", record.toJSONObject());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

}