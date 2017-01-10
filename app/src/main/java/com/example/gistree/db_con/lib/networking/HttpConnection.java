package com.example.gistree.db_con.lib.networking;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import javax.net.ssl.HttpsURLConnection;

public class HttpConnection extends AsyncTask<HttpRequest, Integer, String> {

    public interface HttpResponse{
        void response(String st);
    }
    private HttpResponse delegate = null; //Call back interface

    public HttpConnection(HttpResponse httpResponse) {
        this.delegate = httpResponse; //Assigning call back interface through constructor
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(HttpRequest... params) {
        HttpRequest req = params[0];
        HttpURLConnection myCon;
        StringBuilder total = new StringBuilder();
        try{
            myCon = (HttpURLConnection) req.getURL().openConnection();
            myCon.setRequestMethod(req.getRequestType());
            if(req.getRequestType() == "POST"){
                myCon.setReadTimeout(15000);
                myCon.setConnectTimeout(15000);
                myCon.setDoInput(true);
                myCon.setDoOutput(true);
                myCon.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                myCon.setRequestProperty("Accept", "application/json; charset=UTF-8");
                OutputStreamWriter osw = new OutputStreamWriter(myCon.getOutputStream());
                String dataToSend = req.getData().toString();
                osw.write(dataToSend);
                osw.close();
            }
            if (myCon.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                InputStream is = myCon.getInputStream();
                BufferedReader r = new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line = r.readLine()) != null) {
                    total.append(line);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return total.toString();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String o) {
        delegate.response(o);
        super.onPostExecute(o);
    }
}

