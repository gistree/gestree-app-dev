package com.example.gistree.db_con.application.activities.cortantes;

import android.content.Context;
import android.content.DialogInterface;
import android.database.DataSetObserver;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import com.example.gistree.db_con.R;
import com.toptoche.searchablespinnerlibrary.SearchableListDialog;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

public class SearchableSpinnerYo extends AppCompatActivity {
    SearchableSpinner searchableSpinner;
    String selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable_spinner);

        searchableSpinner = (SearchableSpinner) findViewById(R.id.searchableSpinner);
        searchableSpinner.setTitle("Select Item");
        searchableSpinner.setPositiveButton("OK");

        searchableSpinner.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println(searchableSpinner.getSelectedItem().toString());
            }
        });

        searchableSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = searchableSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                System.out.println("Nothing selected");
            }
        });

    }

}
