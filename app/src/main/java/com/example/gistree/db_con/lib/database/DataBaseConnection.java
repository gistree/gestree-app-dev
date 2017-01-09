package com.example.gistree.db_con.lib.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.gistree.db_con.lib.classes.Metadata;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataBaseConnection extends SQLiteOpenHelper {

    private Context myContext;
    private static DataBaseConnection sInstance;

    public static synchronized DataBaseConnection getInstance(Context context) {
        if (sInstance == null) {
            Log.e("MY_DEBBUG", "CHEGUEI AQUI");
            sInstance = new DataBaseConnection(context);
        }
        return sInstance;
    }

    private DataBaseConnection(Context context) {
        super(context, Metadata.getDatabasePath(context) + Metadata.getDatabaseName(context), null, 1);
        this.myContext = context;
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
            //String myPath = Metadata.getMetaData(myContext,"Database_Path") + getDatabaseName();
            // DEVELOPMENT CODE
            // TODO
            String myPath = this.getDatabaseName();
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
        // DEVELOPMENT CODE
        // TODO
        //String outFileName = Metadata.getMetaData(myContext,"Database_Path_DEV") + getDatabaseName();
        String outFileName = this.getDatabaseName();
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
