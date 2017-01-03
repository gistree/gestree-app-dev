package com.example.gistree.db_con;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gistree.db_con.lib.classes.Helper;
import com.example.gistree.db_con.lib.classes.models.Arvore;
import com.example.gistree.db_con.lib.db_con.DataFactory;

import java.sql.SQLException;

public class UpdateActivity extends AppCompatActivity {

//    TextView textView_id_tree;
//    EditText editText_species;
//    DataFactory db;
//    int position;
//    String species;
//    Arvore arvore;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_update);
//        setupUI(findViewById(R.id.activity_update));
//        db = new DataFactory(this);
//        try {
//            db.open();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        arvore = (Arvore) getIntent().getBundleExtra("tree").getSerializable("tree");
//        textView_id_tree = (TextView) findViewById(R.id.textView_updateIdTree);
//        editText_species = (EditText) findViewById(R.id.editText_updateSpecies);
//        long id_tree = arvore.getId();
//        species = arvore.getSpecies();
//        position = getIntent().getBundleExtra("tree").getInt("position");
//        textView_id_tree.setText(String.valueOf(id_tree));
//        editText_species.setText(species);
//    }
//
//    protected void updateArvore(View v) {
//        Arvore arvoreToUpdate = new Arvore();
//        arvoreToUpdate.setId(arvore.getId());
//        arvoreToUpdate.setSpecies(editText_species.getText().toString());
//        arvoreToUpdate.setId_sync(arvore.getId_sync());
//        arvoreToUpdate.setTimestamp(System.currentTimeMillis()/1000);
//        boolean updateSuccessful = db.updateArvore(arvoreToUpdate);
//        if (updateSuccessful){
//            Bundle bd = new Bundle();
//            bd.putSerializable("Tree", arvoreToUpdate);
//            bd.putInt("position", position);
//            Intent intent = new Intent();
//            intent.putExtra("Tree", bd);
//            setResult(MainActivity_old.RESULT_UPDATE_OK, intent);
//            finish();
//        }
//    }
//
//    protected void deleteArvore(View v) {
//        boolean deleteSucessful = db.removeArvore(arvore.getId_tree());
//        if(deleteSucessful){
//            Bundle bd = new Bundle();
//            bd.putSerializable("Tree", arvore);
//            bd.putInt("position", position);
//            Intent intent = new Intent();
//            intent.putExtra("Tree", bd);
//            setResult(MainActivity_old.RESULT_DELETE_OK, intent);
//            finish();
//        }
//    }
//
//    public void setupUI(View view) {
//        if (!(view instanceof EditText)) {
//            view.setOnTouchListener(new View.OnTouchListener() {
//                public boolean onTouch(View v, MotionEvent event) {
//                    Helper.hideSoftKeyboard(UpdateActivity.this);
//                    return false;
//                }
//            });
//        }
//        if (view instanceof ViewGroup) {
//            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
//                View innerView = ((ViewGroup) view).getChildAt(i);
//                setupUI(innerView);
//            }
//        }
//    }
}
