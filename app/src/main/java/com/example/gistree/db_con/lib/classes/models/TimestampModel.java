package com.example.gistree.db_con.lib.classes.models;

import com.example.gistree.db_con.lib.classes.interfaces.Item;

import org.json.JSONException;
import org.json.JSONObject;

public class TimestampModel implements Item {

    private long id;
    private String timestamp;

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
