package com.example.gistree.db_con.application.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.gistree.db_con.MainActivity;
import com.example.gistree.db_con.R;
import com.example.gistree.db_con.application.activities.cortantes.FormActivity;
import com.example.gistree.db_con.application.activities.cortantes.MultiSpinners;

public class CMenuActivity extends AppCompatActivity {

    public static final int FORMACTIVITY = 6;
    public static final int MULTISPINNERACTIVITY = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmenu);
    }


    protected void openFormActivity (View v) {
        Intent intent = new Intent(CMenuActivity.this, FormActivity.class);
        startActivityForResult(intent,FORMACTIVITY);
    }

    protected void openMultiSpinnerActivity (View v) {
        Intent intent = new Intent(CMenuActivity.this, MultiSpinners.class);
        startActivityForResult(intent,MULTISPINNERACTIVITY);
    }
}
