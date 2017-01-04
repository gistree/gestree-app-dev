package com.example.gistree.db_con.lib.db_con;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataBaseConnection extends SQLiteOpenHelper {

    private Context myContext;
    private static String DB_PATH;
    private static String DB_NAME;

    public DataBaseConnection(Context context, String DB_NAME, String DB_PATH) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
        this.DB_PATH = DB_PATH;
        this.DB_NAME = DB_NAME;
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
            //do nothing - database already exist
        } else {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + getDatabaseName();
            // DEVELOPMENT CODE
            // TODO
            myPath = this.getDatabaseName();
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            //database does't exist yet.
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null;
    }

    private void copyDataBase() throws IOException {
        // DEVELOPMENT CODE
        // TODO - Uncomment next line
        // InputStream myInput = myContext.getAssets().open(getDatabaseName());
        InputStream myInput = myContext.getAssets().open("testeArvore.db");
        String outFileName = DB_PATH + getDatabaseName();
        // DEVELOPMENT CODE
        // TODO
        outFileName = this.getDatabaseName();
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("GesTree::DataBase", "Database Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("GesTree::DataBase", "Database Updated");
    }
}
