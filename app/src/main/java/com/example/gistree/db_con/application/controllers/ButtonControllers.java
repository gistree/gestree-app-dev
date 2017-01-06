package com.example.gistree.db_con.application.controllers;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.example.gistree.db_con.R;
import com.example.gistree.db_con.lib.classes.records.ArvoreRecord;
import com.example.gistree.db_con.lib.classes.records.TimestampRecord;
import com.example.gistree.db_con.lib.classes.repositories.TimestampRepository;
import com.example.gistree.db_con.lib.networking.HttpTest;
import com.example.gistree.db_con.lib.networking.Request;
import com.example.gistree.db_con.lib.networking.ServerAsyncResponse;

import java.util.ArrayList;

public class ButtonControllers implements View.OnClickListener {

    private Context context;

    public ButtonControllers(Context c){
        this.context = c;
    }

    @Override
    public void onClick(final View v) {
        Request req;
        switch (v.getId()){
            case R.id.btEcho:
                req = new Request(v.getContext(), "echo/Estou_Vivo", "GET");
                new HttpTest(new ServerAsyncResponse() {
                    @Override
                    public void serverResponse(String st) {
                        Toast.makeText(v.getContext(),st, Toast.LENGTH_SHORT).show();
                    }
                }).execute(req);
                break;
            case R.id.btEnviar:
                ArvoreRecord arv1 = new ArvoreRecord();
                arv1.setId(1);
                arv1.setSpecies("Pinus");
                ArvoreRecord arv2 = new ArvoreRecord();
                arv2.setId(2);
                arv2.setSpecies("Pinus2");
                ArrayList<ArvoreRecord> arvs = new ArrayList<>();
                arvs.add(arv1);
                arvs.add(arv2);
                TimestampRepository tdf = new TimestampRepository(context);
                TimestampRecord tsm = tdf.getLastTimestamp();
                req = new Request(v.getContext(), "post_test", "POST", tsm.getTimestamp(), arvs);
                new HttpTest(new ServerAsyncResponse() {
                    @Override
                    public void serverResponse(String st) {
                        Toast.makeText(v.getContext(), st, Toast.LENGTH_SHORT).show();
                    }
                }).execute(req);
                break;
            default:

                break;
        }
    }
}
