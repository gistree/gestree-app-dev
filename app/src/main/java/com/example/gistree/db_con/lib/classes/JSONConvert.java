package com.example.gistree.db_con.lib.classes;


import com.example.gistree.db_con.lib.classes.records.RecordLogArvore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONConvert {

    public static ArrayList<RecordLogArvore> logToLogArvoreArray(JSONObject result) throws Exception {
        ArrayList<RecordLogArvore> arrayLogs = new ArrayList<>();
        JSONArray logs = result.getJSONArray("logs");
        for(int i=0; i<logs.length(); i++){
            JSONObject logJSON = logs.getJSONObject(i);
            RecordLogArvore log = new RecordLogArvore();
            log.setId_tree(logJSON.getLong("id_tree"));
            log.setSpecies(logJSON.getString("species"));
            log.setTimestamp(logJSON.getString("insert_date"));
            log.setAction(logJSON.getString("type").charAt(0));
            arrayLogs.add(log);
        }
        return arrayLogs;
    }

}
