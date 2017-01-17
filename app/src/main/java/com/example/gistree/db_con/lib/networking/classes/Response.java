package com.example.gistree.db_con.lib.networking.classes;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class Response extends AbstractHttpResponse{

    private JSONObject resObj;
    private String resString;

    public Response(String type, HttpURLConnection con) {
        this.setType(type);
        try {
            StringBuilder response = new StringBuilder();
            InputStream is = con.getInputStream();
            BufferedReader r = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = r.readLine()) != null) {
                response.append(line);
            }
            this.resString = response.toString();
            this._parseResponse(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getResponseObject(){
        return this.resObj;
    }
    private void _parseResponse(String res) {
        try {
            this.resObj = new JSONObject(res);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.resString;
    }
}
