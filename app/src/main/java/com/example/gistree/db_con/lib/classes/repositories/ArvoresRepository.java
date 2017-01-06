package com.example.gistree.db_con.lib.classes.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.gistree.db_con.lib.classes.records.ArvoreRecord;
import com.example.gistree.db_con.lib.classes.records.RecordInterface;
import com.example.gistree.db_con.lib.classes.records.LogRecord;
import com.example.gistree.db_con.lib.database.DataFactory;

import java.util.ArrayList;

public class ArvoresRepository implements RepositoryInterface {

    private Context myContext;
    private DataFactory db;

    private final String tableName = "dummyTrees";

    protected final String COLUMN_IDTREE = "id_tree";
    protected final String COLUMN_SPECIES = "species";
    protected final String COLUMN_TIMESTAMP = "timestamp";

    public ArvoresRepository(Context c) {
        this.db = new DataFactory(c);
    }

    public DataFactory getDatafactory(){
        return this.db;
    }

    public ArvoreRecord saveArvore(ArvoreRecord a){
        ArvoreRecord arv = null;
        db.startTransaction();
        try {
            arv = (ArvoreRecord) db.insert(this, a);
            LogRepository lgf = new LogRepository(myContext);
            LogRecord logtree = new LogRecord(arv, 'I');
            lgf.saveLogTree(logtree);
            db.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.endTransation();
        return arv;
    }

    @Override
    public ContentValues values(RecordInterface i){
        ArvoreRecord arv = (ArvoreRecord) i;
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
        ArvoreRecord arv = new ArvoreRecord();
        arv.setId_tree(cursor.getLong(cursor.getColumnIndex(this.COLUMN_IDTREE)));
        arv.setTimestamp(cursor.getString(cursor.getColumnIndex(this.COLUMN_TIMESTAMP)));
        arv.setSpecies(cursor.getString(cursor.getColumnIndex(this.COLUMN_SPECIES)));
        return arv;
    }

}
