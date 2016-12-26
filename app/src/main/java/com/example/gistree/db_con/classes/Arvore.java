package com.example.gistree.db_con.classes;

import com.example.gistree.db_con.interfaces.Item;

/**
 * Created by henrique on 21-12-2016.
 */

public class Arvore implements Item {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
