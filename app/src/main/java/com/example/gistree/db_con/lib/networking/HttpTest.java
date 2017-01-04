package com.example.gistree.db_con.lib.networking;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import javax.net.ssl.HttpsURLConnection;

public class HttpTest extends AsyncTask<Request, Integer, String> {

    public ServerAsyncResponse delegate = null; //Call back interface

    public HttpTest(ServerAsyncResponse asyncResponse) {
        this.delegate = asyncResponse; //Assigning call back interface through constructor
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Request... params) {
        Request req = params[0];
        HttpURLConnection myCon;
        StringBuilder total = new StringBuilder();
        try{
            myCon = (HttpURLConnection) req.getUrl().openConnection();
            myCon.setRequestMethod(req.getRequestType());
            if(req.getRequestType() == "POST"){
                myCon.setReadTimeout(15000);
                myCon.setConnectTimeout(15000);
                myCon.setDoInput(true);
                myCon.setDoOutput(true);
                myCon.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
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
        Log.d("JOKORD::onProgUpdate", String.valueOf(values[0]));
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String o) {
        delegate.serverResponse(o);
        super.onPostExecute(o);
    }

}

