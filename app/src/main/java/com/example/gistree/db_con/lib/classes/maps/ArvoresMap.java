package com.example.gistree.db_con.lib.classes.maps;

import com.example.gistree.db_con.lib.classes.records.RecordArvore;
import com.example.gistree.db_con.lib.classes.records.RecordLogArvore;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ArvoresMap extends LinkedHashMap<Long,RecordArvore> {

    public ArvoresMap(){};
    public ArvoresMap(ArrayList<RecordArvore> arvs){
        this.addArvores(arvs);
    }

    public void addArvores(ArrayList<RecordArvore> arvs){
        for(RecordArvore arv : arvs){
            this.put(arv.getId(), arv);
        }
    }
    public void updateTrees(ArrayList<RecordLogArvore> aLogs) {
        for(RecordLogArvore log : aLogs){
            switch (log.getAction()){
                case 'I':
                    if(!this.containsKey(log.getId_tree())){
                        this.put(log.getId_tree(), log);
                    }
                    break;
                case 'U':
                    if(this.containsKey(log.getId_tree())){
                        this.put(log.getId_tree(), log);
                    }
                    break;
                case 'D':
                    this.remove(log.getId_tree());
                    break;
                default:
                    break;
            }
        }
    }
}
