package com.example.gistree.db_con.application.activities.cortantes.scrollabletabs;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by henrique on 1/11/17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected <V> V findView(int id) {
        //noinspection unchecked
        return (V) findViewById(id);
    }
}
