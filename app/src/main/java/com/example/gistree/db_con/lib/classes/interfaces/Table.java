package com.example.gistree.db_con.lib.classes.interfaces;

import android.content.ContentValues;
import android.database.Cursor;

public interface Table {
    public ContentValues values(Item i);
    public String getTableName();
    public String[] getAllColumns();
    public String getIdColumn();
    public Item cursorToItem(Cursor cursor);
}
