package com.example.gistree.db_con.lib.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gistree.db_con.lib.classes.repositories.RepositoryInterface;
import com.example.gistree.db_con.lib.classes.records.RecordInterface;

import java.io.IOException;
import java.sql.SQLException;

public class DataFactory {

    private SQLiteDatabase db;
    private DataBaseConnection dbCon;

    public DataFactory(Context context) {
        // DEVELOPMENT CODE
        // TODO:
        //dbCon = new DataBaseConnection(context, Metadata.getMetaData(context, "Database_Name"), Metadata.getMetaData(context, "Database_Path"));
        dbCon = DataBaseConnection.getInstance(context);
        try {
            dbCon.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void open() throws SQLException {
        db = dbCon.getWritableDatabase();
    }

    public void close() {
        dbCon.close();
    }

    public RecordInterface insert (RepositoryInterface t, RecordInterface i) throws Exception {
        long insertId;
        RecordInterface returnRecordInterface = null;
        insertId = db.insertOrThrow(t.getTableName(), null, t.values(i));
        Cursor cursor = db.query(t.getTableName(), t.getAllColumns().toArray(new String[0]), t.getIdColumn() + " = "
                + insertId, null, null, null, null);
        if(cursor != null && cursor.moveToFirst()) {
            returnRecordInterface = t.cursorToItem(cursor);
            cursor.close();
        }
        return returnRecordInterface;
    }

    public RecordInterface get(RepositoryInterface t, long id){
        RecordInterface returnRecordInterface = null;
        Cursor cursor = db.query(t.getTableName(), t.getAllColumns().toArray(new String[0]),t.getIdColumn() + " = " + id, null, null, null, null, null);
        if(cursor != null && cursor.moveToFirst()){
            try {
                returnRecordInterface = t.cursorToItem(cursor);
            } catch (Exception e) {
                e.printStackTrace();
            }
            cursor.close();
        }
        return returnRecordInterface;
    }

    public boolean update (RepositoryInterface t, RecordInterface i) {
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

    public boolean delete (RepositoryInterface t, RecordInterface i){
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

    public void startTransaction(){
        db.beginTransaction();
    }

    public void endTransation(){
        db.endTransaction();
    }

    public void commitTransaction(){
        db.setTransactionSuccessful();
    }

    public RecordInterface getLast(RepositoryInterface t){
        RecordInterface returnRecordInterface = null;
        Cursor cursor = db.query(t.getTableName(), t.getAllColumns().toArray(new String[0]), null, null, null, null, t.getIdColumn() + " DESC", "1");
        if(cursor != null && cursor.moveToFirst()){
            try {
                returnRecordInterface = t.cursorToItem(cursor);
            } catch (Exception e) {
                e.printStackTrace();
            }
            cursor.close();
        }
        return returnRecordInterface;
    }

}
