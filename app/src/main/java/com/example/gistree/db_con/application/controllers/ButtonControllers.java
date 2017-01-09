package com.example.gistree.db_con.application.controllers;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.example.gistree.db_con.R;
import com.example.gistree.db_con.lib.classes.records.RecordArvore;
import com.example.gistree.db_con.lib.classes.records.RecordTimestamp;
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
        HttpRequest req;
        switch (v.getId()){
            case R.id.btEcho:
//                req = new HttpRequest(v.getContext(), "echo/Estou_Vivo", "GET");
//                new HttpConnection(new HttpConnection.HttpResponse() {
//                    @Override
//                    public void serverResponse(String st) {
//                        Toast.makeText(v.getContext(),st, Toast.LENGTH_SHORT).show();
//                    }
//                }).execute(req);
                break;
            case R.id.btEnviar:
                RecordArvore arv1 = new RecordArvore();
                arv1.setId(1);
                arv1.setSpecies("Pinus");
                RecordArvore arv2 = new RecordArvore();
                arv2.setId(2);
                arv2.setSpecies("Pinus2");
                ArrayList<RecordArvore> arvs = new ArrayList<>();
                arvs.add(arv1);
                arvs.add(arv2);
                RepositoryTimestamp tdf = new RepositoryTimestamp(context);
                RecordTimestamp tsm = tdf.getLastTimestamp();
//                req = new HttpRequest(v.getContext(), "post_test", "POST", tsm.getTimestamp(), arvs);
//                new HttpConnection(new ServerAsyncResponse() {
//                    @Override
//                    public void serverResponse(String st) {
//                        Toast.makeText(v.getContext(), st, Toast.LENGTH_SHORT).show();
//                    }
//                }).execute(req);
//                break;
            default:

                break;
        }
    }
}
