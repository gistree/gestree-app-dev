package com.example.gistree.db_con.application.activities.cortantes;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gistree.db_con.R;
import com.gaurav.cdsrecyclerview.CdsItemTouchCallback;
import com.gaurav.cdsrecyclerview.CdsRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SwipeRecycler extends AppCompatActivity {

    CdsRecyclerView mRecyclerView;
    SwipeRecyclerViewAdapter mSwipeRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_recycler);

        mRecyclerView = new CdsRecyclerView(this);
        mRecyclerView = (CdsRecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //or any other layout manager

        mSwipeRecyclerViewAdapter = new SwipeRecyclerViewAdapter(this, fill_with_data());
        mRecyclerView.setAdapter(mSwipeRecyclerViewAdapter);

        mRecyclerView.setItemClickListener(new CdsRecyclerView.ItemClickListener() {
            @Override
            public void onItemClick(int position) {

                Toast.makeText(SwipeRecycler.this, "Item Clicked:" +
                        mSwipeRecyclerViewAdapter.getItem(position), Toast.LENGTH_SHORT).show();

            }
        });

        mRecyclerView.setItemLongPressListener(new CdsRecyclerView.ItemLongPressListener() {
            @Override
            public void onItemLongClick(int position) {

                Toast.makeText(SwipeRecycler.this, "Item Long Clicked:" +
                        mSwipeRecyclerViewAdapter.getItem(position), Toast.LENGTH_SHORT).show();

            }
        });

        mRecyclerView.enableItemDrag();
        mRecyclerView.setItemDragCompleteListener( new CdsItemTouchCallback.ItemDragCompleteListener() {
            @Override
            public void onItemDragComplete(int fromPosition, int toPosition) {
                Toast.makeText(SwipeRecycler.this, "Item dragged from " + fromPosition +
                        " to " + toPosition, Toast.LENGTH_SHORT).show();
            }
        });

        mRecyclerView.enableItemSwipe();
        mRecyclerView.setItemSwipeCompleteListener(new CdsItemTouchCallback.ItemSwipeCompleteListener() {
            @Override
            public void onItemSwipeComplete(int position) {
                Toast.makeText(SwipeRecycler.this, "Item was swiped:" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    public List<String> fill_with_data() {
        List<String> data = new ArrayList<>();
        data.add("batatas");
        data.add("cebolas");
        data.add("alhos");
        data.add("alface");
        data.add("hortaliça");
        data.add("cenouras");
        data.add("bogalhos");
        data.add("couves");
        data.add("mais couves");
        data.add("chega de couves");
        data.add("batatas");
        data.add("cebolas");
        data.add("alhos");
        data.add("alface");
        data.add("hortaliça");
        data.add("cenouras");
        data.add("bogalhos");
        data.add("couves");
        data.add("mais couves");
        data.add("chega de couves");
        return data;
    }

}
