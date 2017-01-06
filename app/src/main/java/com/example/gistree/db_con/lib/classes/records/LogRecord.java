package com.example.gistree.db_con.lib.classes.records;

public class LogRecord extends ArvoreRecord implements RecordInterface {

    private long id_sync;
    private char action;

    public LogRecord(){}
    public LogRecord(ArvoreRecord arv, char action) throws Exception {
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

}
