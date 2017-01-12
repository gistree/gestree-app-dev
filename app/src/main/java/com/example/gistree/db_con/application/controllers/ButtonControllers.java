package com.example.gistree.db_con.application.controllers;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gistree.db_con.R;
import com.example.gistree.db_con.lib.classes.Metadata;
import com.example.gistree.db_con.lib.classes.maps.ArvoresAdapter;
import com.example.gistree.db_con.lib.classes.records.RecordArvore;
import com.example.gistree.db_con.lib.classes.records.RecordLogArvore;
import com.example.gistree.db_con.lib.classes.records.RecordTimestamp;
import com.example.gistree.db_con.lib.classes.repositories.RepositoryLogArvores;
import com.example.gistree.db_con.lib.classes.repositories.RepositoryTimestamp;
import com.example.gistree.db_con.lib.networking.HttpConnection;
import com.example.gistree.db_con.lib.networking.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        ArrayList<RecordLogArvore> records;
        RecordTimestamp tsm;
        switch (v.getId()){
            case R.id.btEcho:
                new HttpConnection(new HttpConnection.HttpResponse() {
                    @Override
                    public void response(String st) {
                        Toast.makeText(context, st, Toast.LENGTH_SHORT).show();
                    }
                }).execute(
                        HttpRequest.makeGETRequest(
                                Metadata.getAPIUrl(context) + "echo/Estou_Vivo")
                    );
                break;
            case R.id.btTeste:
                ListView listView = (ListView) ((Activity)context).findViewById(android.R.id.list);
                ArvoresAdapter adapter = (ArvoresAdapter) listView.getAdapter();
                adapter.updateAdapter(repoLog.getAllLogs());
                break;
            case R.id.btEnviar:
                tsm = repoTime.getLastTimestamp();
                records = repoLog.getAllLogs();
                new HttpConnection(new HttpConnection.HttpResponse() {
                    @Override
                    public void response(String st) {
                        Toast.makeText(context, st, Toast.LENGTH_LONG).show();
                        System.out.println("ButtonControllers.response");
                        // TODO: 11-01-2017 Handle Response
                        JSONObject test = null;
                        try {
                            test = new JSONObject(st);
                            RecordTimestamp time = new RecordTimestamp((String) test.get("newTimestamp"));
                            repoTime.saveTimestamp(time);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        repoLog.truncateTableLogs();
                    }
                }).execute(HttpRequest.makePOSTRequest(
                        Metadata.getAPIUrl(context) + "sync",
                        tsm.getTimestamp(),
                        records
                ));
                break;
            default:

                break;
        }
    }
}
