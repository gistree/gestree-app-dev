package com.example.gistree.db_con.lib.networking;

import android.os.AsyncTask;

import com.example.gistree.db_con.lib.networking.classes.AbstractHttpResponse;
import com.example.gistree.db_con.lib.networking.classes.Response;
import com.example.gistree.db_con.lib.networking.classes.ResponseError;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public class HttpConnection extends AsyncTask<HttpRequest, Integer, AbstractHttpResponse> {

    public interface AsyncResponse {
        void onResponse(Response res);
        void onError(ResponseError res);
    }
    private AsyncResponse delegate = null;

    private boolean _hasError = false;

    public HttpConnection(AsyncResponse asyncResponse) {
        this.delegate = asyncResponse; //Assigning call back interface through constructor
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
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
            response = new ResponseError("Error", "ResponseError", 500, false, "O servidor não está a responder.", "Cliente");
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

