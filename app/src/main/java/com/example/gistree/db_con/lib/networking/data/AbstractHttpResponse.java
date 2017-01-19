package com.example.gistree.db_con.lib.networking.data;

public abstract class AbstractHttpResponse {
    private String type;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
