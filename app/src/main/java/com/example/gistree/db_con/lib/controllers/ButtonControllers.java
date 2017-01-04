package com.example.gistree.db_con.lib.controllers;

import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Toast;

import com.example.gistree.db_con.R;
import com.example.gistree.db_con.lib.classes.models.Arvore;
import com.example.gistree.db_con.lib.classes.models.TimestampModel;
import com.example.gistree.db_con.lib.classes.tables.SyncTimestampTable;
import com.example.gistree.db_con.lib.db_con.DataFactory;
import com.example.gistree.db_con.lib.networking.HttpTest;
import com.example.gistree.db_con.lib.networking.Request;
import com.example.gistree.db_con.lib.networking.ServerAsyncResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ButtonControllers implements View.OnClickListener {

    private DataFactory db;

    public ButtonControllers(DataFactory db){
        this.db = db;
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
                Arvore arv1 = new Arvore();
                arv1.setId(1);
                arv1.setSpecies("Pinus");
                Arvore arv2 = new Arvore();
                arv2.setId(2);
                arv2.setSpecies("Pinus2");
                ArrayList<Arvore> arvs = new ArrayList<>();
                arvs.add(arv1);
                arvs.add(arv2);
                SyncTimestampTable sync = new SyncTimestampTable();
                TimestampModel tsm = (TimestampModel) db.getLastRecord(sync);
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
