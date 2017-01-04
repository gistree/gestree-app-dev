package com.example.gistree.db_con.lib.db_con;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gistree.db_con.lib.classes.Helper;
import com.example.gistree.db_con.lib.classes.interfaces.Item;
import com.example.gistree.db_con.lib.classes.interfaces.Table;

import java.io.IOException;
import java.sql.SQLException;

public class DataFactory {

    private SQLiteDatabase db;
    private DataBaseConnection dbCon;

    public DataFactory(Context context) {
        // DEVELOPMENT CODE
        // TODO:
        //dbCon = new DataBaseConnection(context, Helper.getMetaData(context, "Database_Name"), Helper.getMetaData(context, "Database_Path"));
        dbCon = new DataBaseConnection(context, Helper.getMetaData(context, "Database_Path_DEV") + Helper.getMetaData(context,"Database_Name"), Helper.getMetaData(context,"Database_Path_DEV"));
        try {
            dbCon.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void open() throws SQLException {
        db = dbCon.getWritableDatabase();
    }

    public void close() {
        dbCon.close();
    }

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

    public Item getItemByID(Table t, long id){
        Item returnItem = null;
        Cursor cursor = db.query(t.getTableName(),t.getAllColumns(),t.getIdColumn() + " = " + id, null, null, null, null, null);
        if(cursor != null && cursor.moveToFirst()){
            returnItem = t.cursorToItem(cursor);
            cursor.close();
        }
        return returnItem;
    }

    public Item getLastRecord(Table t){
        Item returnItem = null;
        Cursor cursor = db.query(t.getTableName(), t.getAllColumns(), null, null, null, null, t.getIdColumn() + " DESC", "1");
        if(cursor != null && cursor.moveToFirst()){
            returnItem = t.cursorToItem(cursor);
            cursor.close();
        }
        return returnItem;
    }

}
