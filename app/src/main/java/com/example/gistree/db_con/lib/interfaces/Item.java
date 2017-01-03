package com.example.gistree.db_con.lib.interfaces;

import org.json.JSONException;
import org.json.JSONObject;

public interface Item {
    long getId();
    void setId(long id);
    JSONObject toJSONObject() throws JSONException;
}
