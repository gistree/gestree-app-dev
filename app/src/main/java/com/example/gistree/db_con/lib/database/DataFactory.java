package com.example.gistree.db_con.lib.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gistree.db_con.lib.database.repositories.RepositoryInterface;
import com.example.gistree.db_con.lib.database.records.RecordInterface;

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

    public RecordInterface insertRecord(RepositoryInterface repo, RecordInterface i) throws Exception {
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
    public RecordInterface getRecord(RepositoryInterface repo, long id){
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
    public RecordInterface getLastRecord(RepositoryInterface repo){
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
    public void updateRecord(RepositoryInterface repo, RecordInterface i) throws Exception {
        int updatedRows;
        updatedRows = db.update(repo.getTableName(), repo.values(i), repo.getIdColumn() + " = " + i.getId(), null);
        if(updatedRows == 0){
            throw new Exception("Could not updateRecord Arvore Record.");
        }
    }
    public void deleteRecord(RepositoryInterface repo, RecordInterface i) throws Exception {
        int deletedRows;
        deletedRows = db.delete(repo.getTableName(), repo.getIdColumn() + " = " + i.getId(), null);
        if (deletedRows == 0) {
            throw new Exception("Could not Delete Arvore Record.");
        }
    }
    public int truncateTable(RepositoryInterface repo){
        return db.delete(repo.getTableName(), "1", null);
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

}
