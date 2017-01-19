package com.example.gistree.db_con.lib.database.records;

import org.json.JSONException;
import org.json.JSONObject;

public class RecordTimestamp implements RecordInterface {

    private long id;
    private String timestamp;

    public RecordTimestamp(){};
    public RecordTimestamp(String timestamp){
        this.timestamp = timestamp;
    }

    @Override
    public long getId() {
        return this.id;
    }
    @Override
    public void setId(long id) {
        this.id = id;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    @Override
    public String toString() {
        return "id:" + this.id + "|timestamp:" + this.timestamp;
    }
    @Override
    public JSONObject toJSONObject() throws JSONException {
        return null;
    }
}
