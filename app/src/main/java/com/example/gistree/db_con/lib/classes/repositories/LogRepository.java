package com.example.gistree.db_con.lib.classes.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.gistree.db_con.lib.classes.records.RecordInterface;
import com.example.gistree.db_con.lib.classes.records.LogRecord;
import com.example.gistree.db_con.lib.database.DataFactory;

import java.util.ArrayList;

public class LogRepository extends ArvoresRepository {

    private DataFactory db = super.getDatafactory();

    private final String tableName = "log";

    protected final String COLUMN_IDSYNC = "id_sync";
    protected final String COLUMN_ACTION = "action";

    public LogRepository(Context c) {
        super(c);
    }

    public LogRecord saveLogTree(LogRecord logRecord) throws Exception {
        return (LogRecord) db.insert(this, logRecord);
    }

    @Override
    public ContentValues values(RecordInterface i) {
        ContentValues values = super.values(i);
        LogRecord logRecord = (LogRecord) i;
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
        LogRecord logRecord = new LogRecord();
        logRecord.setId_sync(cursor.getLong(cursor.getColumnIndex(this.COLUMN_IDSYNC)));
        logRecord.setId_tree(cursor.getLong(cursor.getColumnIndex(super.COLUMN_IDTREE)));
        logRecord.setTimestamp(cursor.getString(cursor.getColumnIndex(super.COLUMN_TIMESTAMP)));
        logRecord.setSpecies(cursor.getString(cursor.getColumnIndex(super.COLUMN_SPECIES)));
        logRecord.setAction(cursor.getString(cursor.getColumnIndex(this.COLUMN_ACTION)).charAt(0));
        return logRecord;
    }
}
