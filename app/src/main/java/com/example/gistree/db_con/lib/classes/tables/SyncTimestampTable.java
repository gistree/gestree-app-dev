package com.example.gistree.db_con.lib.classes.tables;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.gistree.db_con.lib.classes.models.TimestampModel;
import com.example.gistree.db_con.lib.classes.interfaces.Item;
import com.example.gistree.db_con.lib.classes.interfaces.Table;

public class SyncTimestampTable implements Table {

    private String tableName = "sync_timestamp";
    private static String COLUMN_ID = "id";
    private static String COLUMN_TIMESTAMP = "timestamp";

    private String[] allColumns = {
        COLUMN_ID,
        COLUMN_TIMESTAMP
    };

    @Override
    public ContentValues values(Item i) {
        TimestampModel tsm = (TimestampModel) i;
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, tsm.getId());
        values.put(COLUMN_TIMESTAMP, tsm.getTimestamp());
        return values;
    }

    @Override
    public String getTableName() {
        return this.tableName;
    }

    @Override
    public String[] getAllColumns() {
        return allColumns;
    }

    @Override
    public String getIdColumn() {
        return this.COLUMN_ID;
    }

    @Override
    public Item cursorToItem(Cursor cursor) {
        TimestampModel tsm = new TimestampModel();
        tsm.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        tsm.setTimestamp(cursor.getString(cursor.getColumnIndex(COLUMN_TIMESTAMP)));
        return tsm;
    }
}
