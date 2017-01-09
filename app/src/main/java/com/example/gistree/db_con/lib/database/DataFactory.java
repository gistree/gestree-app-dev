package com.example.gistree.db_con.lib.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gistree.db_con.lib.classes.repositories.RepositoryInterface;
import com.example.gistree.db_con.lib.classes.records.RecordInterface;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataFactory {

    private SQLiteDatabase db;
    private final DataBaseConnection dbCon;

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

    private void open() throws SQLException {
        db = dbCon.getWritableDatabase();
    }

    public void close() {
        dbCon.close();
    }

    public RecordInterface insert (RepositoryInterface repo, RecordInterface i) throws Exception {
        long insertId;
        RecordInterface returnRecordInterface = null;
        insertId = db.insertOrThrow(repo.getTableName(), null, repo.values(i));
        Cursor cursor = db.query(repo.getTableName(), repo.getAllColumns().toArray(new String[0]), repo.getIdColumn() + " = "
                + insertId, null, null, null, null);
        if(cursor != null && cursor.moveToFirst()) {
            returnRecordInterface = repo.cursorToItem(cursor);
            cursor.close();
        }
        return returnRecordInterface;
    }

    public ArrayList<? extends RecordInterface> getAllRecords(RepositoryInterface repo) {
        ArrayList<RecordInterface> listRecord = new ArrayList<>();
        Cursor cursor = db.query(repo.getTableName(), repo.getAllColumns().toArray(new String[0]), null, null, null, null, null);
        if(cursor != null && cursor.moveToFirst()){
            try {
                while (!cursor.isAfterLast()){
                    listRecord.add(repo.cursorToItem(cursor));
                    cursor.moveToNext();
                }
                cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listRecord;
    }
    
    public RecordInterface get(RepositoryInterface repo, long id){
        RecordInterface returnRecordInterface = null;
        Cursor cursor = db.query(repo.getTableName(), repo.getAllColumns().toArray(new String[0]),repo.getIdColumn() + " = " + id, null, null, null, null, null);
        if(cursor != null && cursor.moveToFirst()){
            try {
                returnRecordInterface = repo.cursorToItem(cursor);
            } catch (Exception e) {
                e.printStackTrace();
            }
            cursor.close();
        }
        return returnRecordInterface;
    }

    public boolean update (RepositoryInterface repo, RecordInterface i) {
        boolean updateSuccessful = false;
        long updatedRows = 0;
        db.beginTransaction();
        try {
            updatedRows = db.update(repo.getTableName(), repo.values(i), repo.getIdColumn() + " = " + i.getId(), null);
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

    public boolean delete (RepositoryInterface repo, RecordInterface i){
        boolean deleteSuccessful = false;
        long deletedRows = 0;
        db.beginTransaction();
        try {
            deletedRows = db.delete(repo.getTableName(), repo.getIdColumn() + " = " + i.getId(), null);
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

    public RecordInterface getLast(RepositoryInterface repo){
        RecordInterface returnRecordInterface = null;
        Cursor cursor = db.query(repo.getTableName(), repo.getAllColumns().toArray(new String[0]), null, null, null, null, repo.getIdColumn() + " DESC", "1");
        if(cursor != null && cursor.moveToFirst()){
            try {
                returnRecordInterface = repo.cursorToItem(cursor);
            } catch (Exception e) {
                e.printStackTrace();
            }
            cursor.close();
        }
        return returnRecordInterface;
    }
    
}
