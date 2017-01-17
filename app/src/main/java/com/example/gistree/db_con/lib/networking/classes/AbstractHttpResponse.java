package com.example.gistree.db_con.lib.networking.classes;

public abstract class AbstractHttpResponse {
    private String type;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
