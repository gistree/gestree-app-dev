package com.example.gistree.db_con.lib.classes.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.gistree.db_con.lib.classes.records.RecordLogArvore;
import com.example.gistree.db_con.lib.classes.records.RecordArvore;
import com.example.gistree.db_con.lib.classes.records.RecordInterface;
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
        return (RecordArvore) db.insert(this, a);
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
