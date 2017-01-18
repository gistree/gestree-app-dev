package com.example.gistree.db_con.lib.database.records;

import org.json.JSONException;
import org.json.JSONObject;

public interface RecordInterface {
    long getId();
    void setId(long id);
    JSONObject toJSONObject() throws JSONException;
}
