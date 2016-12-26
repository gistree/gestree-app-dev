package com.example.gistree.db_con;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gistree.db_con.classes.TabelaArvores;
import com.example.gistree.db_con.db_con.DataFactory;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    DataFactory db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DataFactory(this, TabelaArvores.DB_NAME, TabelaArvores.DB_PATH);
        try {
            db.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//
//        TabelaArvores t = new TabelaArvores();
//        Item i = new Arvore();
//        i.setId(1);
//        Item i2 = new Arvore();

//        db.addItem(t,i);
//        System.out.println("Inserir");
//        i.setId(2);
//        db.updateItem(t, i);
//        System.out.println("update");
//        db.addItem(t, i2);
//        db.removeItem(t, i2);


    }
}
