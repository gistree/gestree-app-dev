package com.example.gistree.db_con.application.activities.cortantes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gistree.db_con.R;
import com.gaurav.cdsrecyclerview.CdsRecyclerViewAdapter;

import java.util.List;

/**
 * Created by henrique on 1/10/17.
 */

public class SwipeRecyclerViewAdapter extends CdsRecyclerViewAdapter<String, SwipeRecyclerViewAdapter.MyViewHolder> {

    private Context mContext;

    public SwipeRecyclerViewAdapter(Context context, List<String> list) {
        super(context, list);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.swipe_row_layout, parent, false));
    }

    //Cast the viewholder to your custom view holder and then use it
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).mTextView.setText(getList().get(position));
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.textViewSwipe);
        }
    }
}
