package com.example.gistree.db_con.lib.classes.maps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gistree.db_con.lib.classes.records.RecordLogArvore;

import java.util.ArrayList;

public class ArvoresAdapter extends BaseAdapter {

    private ArvoresMap _data = new ArvoresMap();
    private Long[] _keys;
    private Context _context;

    public ArvoresAdapter(Context c, ArvoresMap data){
        this._context = c;
        this._data = data;
        this._keys = data.keySet().toArray(new Long[data.size()]);
    }

    public ArvoresMap getMap(){
        return this._data;
    }
    public void updateAdapter(ArrayList<RecordLogArvore> logs){
        this._data.updateTrees(logs);
        this._keys = this._data.keySet().toArray(new Long[this._data.size()]);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this._data.size();
    }
    @Override
    public Object getItem(int position) {
        return this._data.get(this._keys[position]);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        long key = _keys[position];
        String value = this.getItem(position).toString();

        View result;
        if (convertView == null) {
            result = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        } else {
            result = convertView;
        }
        ((TextView) result.findViewById(android.R.id.text1)).setText(value);
        return result;
    }
}
