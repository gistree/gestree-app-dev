package com.example.gistree.db_con.lib.networking;

import android.content.Context;

import com.example.gistree.db_con.lib.classes.Helper;
import com.example.gistree.db_con.lib.classes.models.Arvore;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;

public class Request {

    private Context ct;
    private String requestType;
    private ArrayList<Arvore> arvores;
    private String url;
    private String timestamp;

    public Request(Context ct, String url, String requestType) {
        this.ct = ct;
        this.url = url;
        this.requestType = requestType;
    }
    public Request(Context ct, String url, String requestType, String timestamp){
        this.ct = ct;
        this.url = url;
        this.requestType = requestType;
        this.timestamp = timestamp;
    }
    public Request(Context ct, String url, String requestType, String timestamp, ArrayList<Arvore> arvores) {
        this.ct = ct;
        this.url = url;
        this.requestType = requestType;
        this.timestamp = timestamp;
        this.arvores = arvores;
    }

    public JSONObject getData() throws Exception {
        JSONObject json = new JSONObject();
        try {
            json.put("timestamp", this.timestamp);
            for (Arvore arvore: this.arvores) {
                json.accumulate("data", arvore.toJSONObject());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
    private String getDataString(JSONObject data) throws Exception {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        Iterator<String> itr = data.keys();
        while (itr.hasNext()) {
            String key = itr.next();
            Object value = data.get(key);
            if (first)
                first = false;
            else
                result.append("&");
            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
        }
        return result.toString();
    }
    public void setData(ArrayList<Arvore> arvores, String timestamp) {
        this.timestamp = timestamp;
        this.arvores = arvores;
    }
    public String getRequestType() {
        return requestType;
    }
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
    public URL getUrl() throws MalformedURLException {
        URL url = new URL(Helper.getAPIUrl(this.ct)+this.url);
        return url;
    }
    public void setUrl(String URL_String) {
        this.url = url;
    }

}