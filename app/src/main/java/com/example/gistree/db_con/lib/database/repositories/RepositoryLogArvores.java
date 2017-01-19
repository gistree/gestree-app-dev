package com.example.gistree.db_con.lib.database.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.gistree.db_con.lib.database.records.RecordLogArvore;
import com.example.gistree.db_con.lib.database.records.RecordInterface;
import com.example.gistree.db_con.lib.database.DataFactory;

import java.util.ArrayList;

public class RepositoryLogArvores extends RepositoryArvores {

    private DataFactory db = super.getDatafactory();

    private final String tableName = "log";

    protected final String COLUMN_IDSYNC = "id_sync";
    protected final String COLUMN_ACTION = "action";

    public RepositoryLogArvores(Context c) {
        super(c);
    }

    public RecordLogArvore saveLogTree(RecordLogArvore logRecord) throws Exception {
        return (RecordLogArvore) db.insertRecord(this, logRecord);
    }

    @SuppressWarnings("unchecked")
    public ArrayList<RecordLogArvore> getAllLogs() {
        return (ArrayList<RecordLogArvore>) db.getAllRecords(this);
    }
    public int truncateTableLogs(){
        return db.truncateTable(this);
    }

    @Override
    public ContentValues values(RecordInterface i) {
        ContentValues values = super.values(i);
        RecordLogArvore logRecord = (RecordLogArvore) i;
        values.put(this.COLUMN_IDTREE, logRecord.getId_tree());
        values.put(this.COLUMN_ACTION, Character.toString(logRecord.getAction()));
        return values;
    }
    @Override
    public String getTableName() {
        return this.tableName;
    }
    @Override
    public ArrayList<String> getAllColumns() {
        ArrayList<String> allColumns = super.getAllColumns();
        allColumns.add(this.COLUMN_IDSYNC);
        allColumns.add(this.COLUMN_ACTION);
        return allColumns;
    }
    @Override
    public String getIdColumn() {
        return this.COLUMN_IDSYNC;
    }
    @Override
    public RecordInterface cursorToItem(Cursor cursor) throws Exception {
        RecordLogArvore logRecord = new RecordLogArvore();
        logRecord.setId_sync(cursor.getLong(cursor.getColumnIndex(this.COLUMN_IDSYNC)));
        logRecord.setId_tree(cursor.getLong(cursor.getColumnIndex(super.COLUMN_IDTREE)));
        logRecord.setTimestamp(cursor.getString(cursor.getColumnIndex(super.COLUMN_TIMESTAMP)));
        logRecord.setSpecies(cursor.getString(cursor.getColumnIndex(super.COLUMN_SPECIES)));
        logRecord.setAction(cursor.getString(cursor.getColumnIndex(this.COLUMN_ACTION)).charAt(0));
        return logRecord;
    }

}
