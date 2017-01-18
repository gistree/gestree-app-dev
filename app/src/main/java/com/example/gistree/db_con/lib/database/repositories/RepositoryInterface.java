package com.example.gistree.db_con.lib.database.repositories;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.gistree.db_con.lib.database.records.RecordInterface;

import java.util.ArrayList;

public interface RepositoryInterface {
    ContentValues values(RecordInterface i);
    String getTableName();
    ArrayList<String> getAllColumns();
    String getIdColumn();
    RecordInterface cursorToItem(Cursor cursor) throws Exception;
}
