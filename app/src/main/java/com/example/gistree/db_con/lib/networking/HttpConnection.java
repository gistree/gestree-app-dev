package com.example.gistree.db_con.lib.networking;

import android.content.Context;
import android.os.AsyncTask;

import com.example.gistree.db_con.application.components.GestreeAlerts;
import com.example.gistree.db_con.lib.networking.data.AbstractHttpResponse;
import com.example.gistree.db_con.lib.networking.data.Response;
import com.example.gistree.db_con.lib.networking.data.ResponseError;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class HttpConnection extends AsyncTask<HttpRequest, Integer, AbstractHttpResponse> {

    public interface AsyncResponse {
        void onResponse(Response res);
        void onError(ResponseError res);
    }
    private AsyncResponse delegate = null;

    private boolean _hasError = false;
    private SweetAlertDialog sad;
    private Context c;

    public HttpConnection(Context c, AsyncResponse asyncResponse) {
        this.delegate = asyncResponse; //Assigning call back interface through constructor
        this.c = c;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        sad = GestreeAlerts.progressAlert(c, "A Sincronizar...");
        sad.show();
    }
    @Override
    protected AbstractHttpResponse doInBackground(HttpRequest... params) {
        HttpRequest req = params[0];
        HttpURLConnection myCon;
        AbstractHttpResponse response = null;
        try{
            myCon = (HttpURLConnection) req.getURL().openConnection();
            myCon.setRequestMethod(req.getRequestType());
            if(req.getRequestType() == "POST"){
                createPostRequest(myCon, req);
            }
            switch (myCon.getResponseCode()){
                case HttpURLConnection.HTTP_OK:
                    response = this.readResponse(myCon);
                    break;
                case 422:
                case HttpURLConnection.HTTP_INTERNAL_ERROR:
                    this._hasError = true;
                    response = this.readError(myCon);
                    break;
                default:
                    break;
            }
        }catch (IOException e){
            e.printStackTrace();
            this._hasError = true;
            response = new ResponseError("Error", "Response", 500, false, "O servidor não está a responder.", "Cliente");
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
    @Override
    protected void onPostExecute(AbstractHttpResponse res) {
        sad.dismiss();
        if(this._hasError){
            delegate.onError((ResponseError) res);
        }else{
            delegate.onResponse((Response) res);
        }
        super.onPostExecute(res);
    }
    private void createPostRequest(HttpURLConnection con, HttpRequest req) throws Exception {
        con.setReadTimeout(15000);
        con.setConnectTimeout(15000);
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        con.setRequestProperty("Accept", "application/json; charset=UTF-8");
        OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream());
        String dataToSend = req.getData().toString();
        osw.write(dataToSend);
        osw.close();
    }
    private Response readResponse(HttpURLConnection con) throws IOException {
        return new Response("Response", con);
    }
    private ResponseError readError(HttpURLConnection con) throws IOException {
        return new ResponseError("Error", con);
    }
}

