package com.example.gistree.db_con.db_con;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gistree.db_con.interfaces.Item;
import com.example.gistree.db_con.interfaces.Table;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by henrique on 21-12-2016.
 */

public class DataFactory {

    private SQLiteDatabase db;
    private DataBaseConnection dbCon;
    public static final String DEFAULT_DB_PATH = "/data/data/com.example.henrique.db_con/databases/";
    public static final String DEFAULT_DB_NAME = "testeArvore.db";

    public DataFactory(Context context, String dbName, String dbPath) {
        dbCon = new DataBaseConnection(context, dbName, dbPath);
        try {
            dbCon.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void open() throws SQLException {db = dbCon.getWritableDatabase();}

    public void close() {dbCon.close();}

    public Item addItem (Table t, Item i) {
        long insertId;
        Item returnItem = null;
        db.beginTransaction();
        try {
            insertId = db.insertOrThrow(t.getTableName(), null, t.values(i));
            Cursor cursor = db.query(t.getTableName(), t.getAllColumns(), t.getIdColumn() + " = "
                    + insertId, null, null, null, null);
            if(cursor != null && cursor.moveToFirst()){
                returnItem = t.cursorToItem(cursor);
                cursor.close();
            }
            db.setTransactionSuccessful();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            db.endTransaction();
        }
        return returnItem;
    }

    public boolean removeItem (Table t, Item i){
        boolean deleteSuccessful = false;
        long deletedRows = 0;
        db.beginTransaction();
        try {
            deletedRows = db.delete(t.getTableName(), t.getIdColumn() + " = " + i.getId(), null);
            db.setTransactionSuccessful();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            db.endTransaction();
        }
        if (deletedRows > 0) {
            deleteSuccessful = true;
        }
        return deleteSuccessful;
    }

    public boolean updateItem (Table t, Item i) {
        boolean updateSuccessful = false;
        long updatedRows = 0;
        db.beginTransaction();
        try {
            updatedRows = db.update(t.getTableName(), t.values(i), t.getIdColumn() + " = " + i.getId(), null);
            db.setTransactionSuccessful();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            db.endTransaction();
        }
        if (updatedRows > 0) {
            updateSuccessful = true;
        }
        return updateSuccessful;
    }
}
