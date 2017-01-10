package com.example.gistree.db_con.lib.classes.records;

import org.json.JSONException;
import org.json.JSONObject;

public class RecordLogArvore extends RecordArvore implements RecordInterface {

    private long id_sync;
    private char action;

    public RecordLogArvore(){}
    public RecordLogArvore(RecordArvore arv, char action) throws Exception {
        super(arv);
        this.setAction(action);
    }

    @Override
    public long getId(){
        return this.id_sync;
    }
    @Override
    public void setId(long id) {
        this.id_sync = id;
    }

    public long getId_sync() {
        return this.id_sync;
    }
    public void setId_sync(long id_sync) {
        this.id_sync = id_sync;
    }

    public char getAction() {
        return action;
    }
    public void setAction(char action) throws Exception {
        if(isValidAction(action)){
            this.action = action;
        }else{
            throw new Exception("Ha Problema");
        }
    }

    private boolean isValidAction(Character action){
        switch (action){
            case 'I':
                return true;
            case 'U':
                return true;
            case 'D':
                return true;
            default:
                return false;
        }
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("id_tree", this.getId_tree());
            json.put("species", this.getSpecies());
            json.put("timestamp", this.getTimestamp());
            json.put("action", String.valueOf(this.action));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
