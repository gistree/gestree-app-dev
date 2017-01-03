package com.example.gistree.db_con.lib.networking;

import android.content.Context;

import com.example.gistree.db_con.lib.classes.Helper;
import com.example.gistree.db_con.lib.classes.models.Arvore;
import com.example.gistree.db_con.lib.db_con.DataFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Request {

    private Context ct;
    private String requestType;
    private ArrayList<Arvore> arvores;
    private String url;

    public Request(Context ct, String url, String requestType) {
        this.ct = ct;
        this.url = url;
        this.requestType = requestType;
    }

    public Request(Context ct, String url, String requestType, ArrayList<Arvore> arvores) {
        this.ct = ct;
        this.requestType = requestType;
        this.arvores = arvores;
        this.url = url;
    }

    public String getRequestType() {
        return requestType;
    }

    public JSONObject getData() {
        JSONObject json = new JSONObject();
        try {
//            json.put("timestamp", DataFactory.getLastTimestamp());
            for (Arvore arvore: this.arvores) {
                json.accumulate("data", arvore.toJSONObject());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    public void setData(ArrayList<Arvore> arvores) {
        this.arvores = arvores;
    }

    public URL getUrl() throws MalformedURLException {
        URL url = new URL(Helper.getAPIUrl(this.ct)+this.url);
        return url;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public void setUrl(String URL_String) {
        this.url = url;
    }

    public String getDataString() throws Exception {
        //TODO
//        StringBuilder result = new StringBuilder();
//        boolean first = true;
//        Iterator<String> itr = this.data.keys();
//        while (itr.hasNext()) {
//            String key = itr.next();
//            Object value = this.data.get(key);
//            if (first)
//                first = false;
//            else
//                result.append("&");
//            result.append(URLEncoder.encode(key, "UTF-8"));
//            result.append("=");
//            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
//        }
//        return result.toString();
        return "NO";
    }

}