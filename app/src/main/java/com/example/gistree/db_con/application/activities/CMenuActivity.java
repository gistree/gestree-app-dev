package com.example.gistree.db_con.application.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.gistree.db_con.R;
import com.example.gistree.db_con.application.activities.cortantes.FormActivity;
import com.example.gistree.db_con.application.activities.cortantes.scrollabletabs.MainScrollableTabs;
import com.example.gistree.db_con.application.activities.cortantes.MultiSpinners;
import com.example.gistree.db_con.application.activities.cortantes.MyRecyclerView;
import com.example.gistree.db_con.application.activities.cortantes.SearchableSpinnerYo;
import com.example.gistree.db_con.application.activities.cortantes.SwipeRecycler;
import com.example.gistree.db_con.application.activities.cortantes.SwipeToShowButton;

public class CMenuActivity extends AppCompatActivity {

    public static final int FORMACTIVITY = 6;
    public static final int MULTISPINNERACTIVITY = 7;
    public static final int SEARCHABLESPINNERYO = 8;
    public static final int MYRECYCLERVIEW = 9;
    public static final int MYSWIPERECYCLERVIEW = 10;
    public static final int SWIPETOSHOWBUTTON = 11;
    public static final int SCROLLABLETABS = 12;


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

    protected void openSearchableSpinnerActivity (View v) {
        Intent intent = new Intent(CMenuActivity.this, SearchableSpinnerYo.class);
        startActivityForResult(intent,SEARCHABLESPINNERYO);
    }

    protected void openRecyclerViewActivity (View v) {
        Intent intent = new Intent(CMenuActivity.this, MyRecyclerView.class);
        startActivityForResult(intent,MYRECYCLERVIEW);
    }

    protected void openSwipeRecyclerViewActivity (View v) {
        Intent intent = new Intent(CMenuActivity.this, SwipeRecycler.class);
        startActivityForResult(intent,MYSWIPERECYCLERVIEW);
    }

    protected void openSwipeToShowButtonActivity (View v) {
        Intent intent = new Intent(CMenuActivity.this, SwipeToShowButton.class);
        startActivityForResult(intent,SWIPETOSHOWBUTTON);
    }

    protected void openScrollableTabsActivity (View v) {
        Intent intent = new Intent(CMenuActivity.this, MainScrollableTabs.class);
        startActivityForResult(intent,SCROLLABLETABS);
    }
}
