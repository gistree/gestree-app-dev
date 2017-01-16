package com.example.gistree.db_con.lib.classes.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.gistree.db_con.lib.classes.records.RecordTimestamp;
import com.example.gistree.db_con.lib.classes.records.RecordInterface;
import com.example.gistree.db_con.lib.database.DataFactory;

import java.util.ArrayList;

public class RepositoryTimestamp implements RepositoryInterface {

    private Context myContext;
    private DataFactory db = new DataFactory(this.myContext);

    private String tableName = "sync_timestamp";
    private static String COLUMN_ID = "id";
    private static String COLUMN_TIMESTAMP = "timestamp";

    public RepositoryTimestamp(Context c) {
        this.myContext = c;
    }

    public RecordTimestamp saveTimestamp(RecordTimestamp r) throws Exception {
        return (RecordTimestamp) db.insertRecord(this, r);
    }
    public RecordTimestamp getLastTimestamp(){
        return (RecordTimestamp) db.getLastRecord(this);
    }

    @Override
    public ContentValues values(RecordInterface i) {
        RecordTimestamp tsm = (RecordTimestamp) i;
        ContentValues values = new ContentValues();
        values.put(COLUMN_TIMESTAMP, tsm.getTimestamp());
        return values;
    }
    @Override
    public String getTableName() {
        return this.tableName;
    }
    @Override
    public ArrayList<String> getAllColumns() {
        ArrayList<String> allColumns = new ArrayList<String>();
        allColumns.add(this.COLUMN_ID);
        allColumns.add(this.COLUMN_TIMESTAMP);
        return allColumns;
    }
    @Override
    public String getIdColumn() {
        return this.COLUMN_ID;
    }
    @Override
    public RecordInterface cursorToItem(Cursor cursor) {
        RecordTimestamp tsm = new RecordTimestamp();
        tsm.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        tsm.setTimestamp(cursor.getString(cursor.getColumnIndex(COLUMN_TIMESTAMP)));
        return tsm;
    }
}
