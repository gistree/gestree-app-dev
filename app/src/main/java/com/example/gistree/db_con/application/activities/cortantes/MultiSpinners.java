package com.example.gistree.db_con.application.activities.cortantes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.gistree.db_con.R;
import com.thomashaertel.widget.MultiSpinner;

import java.util.ArrayList;
import java.util.List;

public class MultiSpinners extends AppCompatActivity {

    private MultiSpinner spinner;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_spinner);

        // create spinner list elements
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter.add("Item1");
        adapter.add("Item2");
        adapter.add("Item3");
        adapter.add("Item4");
        adapter.add("Item5");

        // get spinner and set adapter
        spinner = (MultiSpinner) findViewById(R.id.spinnerMulti);
        spinner.setAdapter(adapter, false, onSelectedListener);

        // set initial selection
//        boolean[] selectedItems = new boolean[adapter.getCount()];
//        selectedItems[1] = true; // select second item
//        spinner.setSelected(selectedItems);


    }

    private MultiSpinner.MultiSpinnerListener onSelectedListener = new MultiSpinner.MultiSpinnerListener() {
        public void onItemsSelected(boolean[] selected) {
            // Do something here with the selected items
            List<Integer> selectedItems = new ArrayList<>();
            for(int i=0, x=selected.length; i<x; i++) {
                if(selected[i]){
                    selectedItems.add(i);
                }
            }
            if(selectedItems != null){
                for(int i=0, x=selectedItems.size(); i<x; i++) {
                    System.out.println(adapter.getItem(selectedItems.get(i)));
                }
            }
        }
    };

//    protected void printValuesSelected (View v){
//
//    }
}
