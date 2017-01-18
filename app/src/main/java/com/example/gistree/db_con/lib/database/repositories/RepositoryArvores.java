package com.example.gistree.db_con.lib.database.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.example.gistree.db_con.lib.database.records.RecordLogArvore;
import com.example.gistree.db_con.lib.database.records.RecordArvore;
import com.example.gistree.db_con.lib.database.records.RecordInterface;
import com.example.gistree.db_con.lib.database.DataFactory;

import java.util.ArrayList;

public class RepositoryArvores implements RepositoryInterface {

    private Context myContext;
    private DataFactory db;

    private final String tableName = "dummyTrees";

    protected final String COLUMN_IDTREE = "id_tree";
    protected final String COLUMN_SPECIES = "species";
    protected final String COLUMN_TIMESTAMP = "timestamp";

    public RepositoryArvores(Context c) {
        this.db = new DataFactory(c);
        this.myContext = c;
    }

    public DataFactory getDatafactory(){
        return this.db;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<RecordArvore> getAllArvores(){
        return (ArrayList<RecordArvore>) db.getAllRecords(this);
    }
    public RecordArvore saveArvore(RecordArvore a) throws Exception {
        return (RecordArvore) db.insertRecord(this, a);
    }
    public void updateArvore(RecordArvore a) throws Exception {
        db.updateRecord(this, a);
    }
    public void deleteArvore(RecordArvore a) throws Exception {
        db.deleteRecord(this, a);
    }
    public void updateRecords(ArrayList<RecordLogArvore> logs) {
        db.startTransaction();
        try {
            for (RecordLogArvore log : logs) {
                switch (log.getAction()) {
                    case 'I':
                        this.saveArvore(log);
                        break;
                    case 'U':
                        this.updateArvore(log);
                        break;
                    case 'D':
                        this.deleteArvore(log);
                        break;
                    default:
                        break;
                }
            }
            db.commitTransaction();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this.myContext, "ERROR", Toast.LENGTH_LONG).show();
        }finally {
            db.endTransation();
        }
    }

    @Override
    public ContentValues values(RecordInterface i){
        RecordArvore arv = (RecordArvore) i;
        ContentValues values = new ContentValues();
        values.put(this.COLUMN_IDTREE, arv.getId());
        values.put(this.COLUMN_SPECIES, arv.getSpecies());
        values.put(this.COLUMN_TIMESTAMP, arv.getTimestamp());
        return values;
    }
    @Override
    public String getTableName(){
        return this.tableName;
    }
    @Override
    public ArrayList<String> getAllColumns() {
        ArrayList<String> allColumns = new ArrayList<>();
        allColumns.add(this.COLUMN_IDTREE);
        allColumns.add(this.COLUMN_SPECIES);
        allColumns.add(this.COLUMN_TIMESTAMP);
        return allColumns;
    }
    @Override
    public String getIdColumn() {
        return COLUMN_IDTREE;
    }
    @Override
    public RecordInterface cursorToItem(Cursor cursor) throws Exception {
        RecordArvore arv = new RecordArvore();
        arv.setId_tree(cursor.getLong(cursor.getColumnIndex(this.COLUMN_IDTREE)));
        arv.setTimestamp(cursor.getString(cursor.getColumnIndex(this.COLUMN_TIMESTAMP)));
        arv.setSpecies(cursor.getString(cursor.getColumnIndex(this.COLUMN_SPECIES)));
        return arv;
    }

}
