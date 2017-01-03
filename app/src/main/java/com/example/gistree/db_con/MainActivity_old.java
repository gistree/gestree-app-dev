package com.example.gistree.db_con;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.gistree.db_con.lib.classes.Helper;
import com.example.gistree.db_con.lib.networking.HttpTest;
import com.example.gistree.db_con.lib.networking.Request;
import com.example.gistree.db_con.lib.networking.ServerAsyncResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class MainActivity_old extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        final JSONObject json = new JSONObject();
//        try {
//            json.put("timestamp", new Date().toString());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        Button btConnect = (Button)findViewById(R.id.btTestConnect);
//        final ProgressBar pg = (ProgressBar) findViewById(R.id.progressBar);
//        btConnect.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Request req = new Request(Helper.getAPIUrl(MainActivity_old.this)+"post_test/", "POST", json);
//                new HttpTest(new ServerAsyncResponse() {
//                    @Override
//                    public void serverResponse(String output) {
//                        pg.setVisibility(View.INVISIBLE);
//                        pg.setProgress(0);
//                        Toast.makeText(MainActivity_old.this, output, Toast.LENGTH_SHORT).show();
//                    }
//                }).execute(req);
//            }
//        });
    }
}
