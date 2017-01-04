package com.example.gistree.db_con.lib.classes.models;

import com.example.gistree.db_con.lib.classes.interfaces.Item;

import org.json.JSONException;
import org.json.JSONObject;

public class Arvore implements Item {
    private long id;
    private String species;

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("id", this.id);
            json.put("species", this.species);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
