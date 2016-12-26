package com.example.gistree.db_con.db_con;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gistree.db_con.db_con.DataBaseConnection;
import com.example.gistree.db_con.interfaces.Item;
import com.example.gistree.db_con.interfaces.Table;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by henrique on 21-12-2016.
 */

public class DataFactory {

    private SQLiteDatabase database;
    private DataBaseConnection dbHelper;

    public DataFactory(Context context, String dbName, String dbPath) {
        dbHelper = new DataBaseConnection(context, dbName, dbPath);
        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void open() throws SQLException {database = dbHelper.getWritableDatabase();}

    public void close() {dbHelper.close();}

    public Item addItem (Table t, Item i) {
        long insertId = 0;
        Item returnItem = null;
        database.beginTransaction();
        try {
            insertId = database.insertOrThrow(t.getTableName(), null, t.values(i));
            Cursor cursor = database.query(t.getTableName(), t.getAllColumns(), t.getIdColumn() + " = "
                    + insertId, null, null, null, null);
            if(cursor != null && cursor.moveToFirst()){
                returnItem = t.cursorToItem(cursor);
                cursor.close();
            }
            database.setTransactionSuccessful();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            database.endTransaction();
        }
        return returnItem;
    }

    public boolean removeItem (Table t, Item i){
        boolean deleteSuccessful = false;
        long deletedRows = 0;
        database.beginTransaction();
        try {
            deletedRows = database.delete(t.getTableName(), t.getIdColumn() + " = " + i.getId(), null);
            database.setTransactionSuccessful();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            database.endTransaction();
        }
        if (deletedRows > 0) {
            deleteSuccessful = true;
        }
        return deleteSuccessful;
    }

    public boolean updateItem (Table t, Item i) {
        boolean updateSuccessful = false;
        long updatedRows = 0;
        database.beginTransaction();
        try {
            updatedRows = database.update(t.getTableName(), t.values(i), t.getIdColumn() + " = " + i.getId(), null);
            database.setTransactionSuccessful();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            database.endTransaction();
        }
        if (updatedRows > 0) {
            updateSuccessful = true;
        }
        return updateSuccessful;
    }
}
