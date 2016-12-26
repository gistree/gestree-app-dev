package com.example.gistree.db_con.interfaces;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by henrique on 21-12-2016.
 */

public interface Table {
    public ContentValues values(Item i);
    public String getTableName();
    public String[] getAllColumns();
    public String getIdColumn();
    public Item cursorToItem(Cursor cursor);
}
