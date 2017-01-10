package com.example.gistree.db_con.application.controllers;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.example.gistree.db_con.R;
import com.example.gistree.db_con.lib.classes.Metadata;
import com.example.gistree.db_con.lib.classes.records.RecordArvore;
import com.example.gistree.db_con.lib.classes.records.RecordLogArvore;
import com.example.gistree.db_con.lib.classes.records.RecordTimestamp;
import com.example.gistree.db_con.lib.classes.repositories.RepositoryLogArvores;
import com.example.gistree.db_con.lib.classes.repositories.RepositoryTimestamp;
import com.example.gistree.db_con.lib.networking.HttpConnection;
import com.example.gistree.db_con.lib.networking.HttpRequest;

import java.util.ArrayList;

public class ButtonControllers implements View.OnClickListener {

    private Context context;

    public ButtonControllers(Context c){
        this.context = c;
    }

    @Override
    public void onClick(final View v) {
        final Context context = this.context;
        final RepositoryTimestamp tdf = new RepositoryTimestamp(this.context);
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
                tsm = tdf.getLastTimestamp();
                records = repoLog.getAllLogs();
                new HttpConnection(new HttpConnection.HttpResponse() {
                    @Override
                    public void response(String st) {
                        Toast.makeText(context, st, Toast.LENGTH_SHORT).show();
                        //repoLog.truncateTableLogs();
                    }
                }).execute(
                        HttpRequest.makePOSTRequest(
                                Metadata.getAPIUrl(context) + "post_test",
                                tsm.getTimestamp(),
                                records
                                )
                );
                break;
            case R.id.btEnviar:
                tsm = tdf.getLastTimestamp();
                records = repoLog.getAllLogs();
                new HttpConnection(new HttpConnection.HttpResponse() {
                    @Override
                    public void response(String st) {
                        Toast.makeText(context, st, Toast.LENGTH_SHORT).show();
                        //repoLog.truncateTableLogs();
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
