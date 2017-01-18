package com.example.gistree.db_con.application.controllers;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gistree.db_con.R;
import com.example.gistree.db_con.lib.classes.JSONConvert;
import com.example.gistree.db_con.lib.classes.Metadata;
import com.example.gistree.db_con.lib.classes.maps.ArvoresAdapter;
import com.example.gistree.db_con.lib.database.records.RecordLogArvore;
import com.example.gistree.db_con.lib.database.records.RecordTimestamp;
import com.example.gistree.db_con.lib.database.repositories.RepositoryArvores;
import com.example.gistree.db_con.lib.database.repositories.RepositoryLogArvores;
import com.example.gistree.db_con.lib.database.repositories.RepositoryTimestamp;
import com.example.gistree.db_con.lib.networking.HttpConnection;
import com.example.gistree.db_con.lib.networking.HttpRequest;
import com.example.gistree.db_con.lib.networking.con.ConnectionManager;
import com.example.gistree.db_con.lib.networking.data.Response;
import com.example.gistree.db_con.lib.networking.data.ResponseError;

import org.json.JSONException;

import java.util.ArrayList;

public class ButtonControllers implements View.OnClickListener {

    private Context context;

    public ButtonControllers(Context c){
        this.context = c;
    }

    @Override
    public void onClick(final View v) {
        final Context context = this.context;
        final RepositoryTimestamp repoTime = new RepositoryTimestamp(this.context);
        final RepositoryLogArvores repoLog = new RepositoryLogArvores(this.context);
        final RepositoryArvores repoArv = new RepositoryArvores(this.context);
        ArrayList<RecordLogArvore> records;
        RecordTimestamp tsm;
        switch (v.getId()){
            case R.id.btEcho:
                new HttpConnection(new HttpConnection.AsyncResponse() {
                    @Override
                    public void onResponse(Response res) {
                        Toast.makeText(context, res.toString(), Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onError(ResponseError err) {
                        err.raiseError(context);
                    }
                }).execute(
                        HttpRequest.makeGETRequest(
                                Metadata.getAPIUrl(context) + "echo/Estou_Vivo")
                    );
                break;
            case R.id.btTeste:

                break;
            case R.id.btEnviar:
                ConnectionManager cm = ConnectionManager.getConnectionManager(context);
                if(cm.isConnected()){
                    if(cm.isUsingMobileData()){
                        GestreeToasts.mobileDataWarning(context);
                    }else if(cm.isUsingWiFi()){
                        tsm = repoTime.getLastTimestamp();
                        records = repoLog.getAllLogs();
                        new HttpConnection(new HttpConnection.AsyncResponse() {
                            @Override
                            public void onResponse(Response res) {
                                try {
                                    ArrayList<RecordLogArvore> logs = JSONConvert.logToLogArvoreArray(res.getResponseObject());
                                    repoArv.updateRecords(logs);
                                    RecordTimestamp time = new RecordTimestamp((String) res.getResponseObject().get("newTimestamp"));
                                    repoTime.saveTimestamp(time);
                                    ListView listView = (ListView) ((Activity)context).findViewById(android.R.id.list);
                                    ArvoresAdapter adapter = (ArvoresAdapter) listView.getAdapter();
                                    adapter.updateAdapter(logs);
                                    repoLog.truncateTableLogs();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            @Override
                            public void onError(ResponseError err) {
                                if(err.handleError()){
                                    repoLog.truncateTableLogs();
                                }
                                err.raiseError(context);
                            }
                        }).execute(HttpRequest.makePOSTRequest(
                                Metadata.getAPIUrl(context) + "sync",
                                tsm.getTimestamp(),
                                records
                        ));
                    }
                }else{
                    GestreeToasts.wifiWarning(context);
                }
                break;
            default:
                break;
        }
    }
}
