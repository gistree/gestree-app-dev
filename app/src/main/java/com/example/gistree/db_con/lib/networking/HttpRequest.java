package com.example.gistree.db_con.lib.networking;

import android.content.Context;

import com.example.gistree.db_con.lib.classes.Metadata;
import com.example.gistree.db_con.lib.classes.records.RecordArvore;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;

public class HttpRequest {

    private Context ct;
    private String requestType;
    private String url;

    public HttpRequest(Context ct, String url, String requestType) {
        this.ct = ct;
        this.url = url;
        this.requestType = requestType;
    }

    /*
    public JSONObject getData() throws Exception {
        JSONObject json = new JSONObject();
        try {
            json.put("timestamp", this.timestamp);
            for (RecordArvore recordArvore : this.recordArvores) {
                json.accumulate("data", recordArvore.toJSONObject());
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
    public void setData(ArrayList<RecordArvore> recordArvores, String timestamp) {
        this.timestamp = timestamp;
        this.recordArvores = recordArvores;
    }
    */

}