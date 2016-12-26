package com.example.gistree.db_con.classes;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.gistree.db_con.interfaces.Item;
import com.example.gistree.db_con.interfaces.Table;

/**
 * Created by henrique on 21-12-2016.
 */

public class TabelaArvores implements Table {

    public static String DB_PATH = "/data/data/com.example.henrique.db_con/databases/";
    public static String DB_NAME = "testeArvore.db";

    private String tableName = "arvores";
    public static String COLUMN_1 = "id_db";
    public static String COLUMN_2 = "id";

    public static String[] allColumns = {
            COLUMN_1,
            COLUMN_2
    };


    @Override
    public ContentValues values(Item i){
        ContentValues values = new ContentValues();
        values.put(COLUMN_2, i.getId());
        return values;
    }

    @Override
    public String getTableName(){
        return this.tableName;
    }

    @Override
    public String[] getAllColumns() {
        return allColumns;
    }

    @Override
    public String getIdColumn() {
        return COLUMN_2;
    }

    @Override
    public Item cursorToItem(Cursor cursor) {
        Item i = new Arvore();
        i.setId(cursor.getLong(1));
        return i;
    }

}
