package com.example.gistree.db_con;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.gistree.db_con.lib.classes.Helper;
import com.example.gistree.db_con.lib.classes.Metadata;
import com.example.gistree.db_con.lib.classes.records.RecordArvore;

import com.example.gistree.db_con.application.controllers.ButtonControllers;
import com.example.gistree.db_con.lib.classes.records.RecordInterface;
import com.example.gistree.db_con.lib.classes.records.RecordLogArvore;
import com.example.gistree.db_con.lib.classes.records.RecordTimestamp;
import com.example.gistree.db_con.lib.classes.repositories.RepositoryArvores;
import com.example.gistree.db_con.lib.classes.repositories.RepositoryLogArvores;
import com.example.gistree.db_con.lib.classes.repositories.RepositoryTimestamp;
import com.example.gistree.db_con.lib.database.DataFactory;
import com.example.gistree.db_con.lib.networking.HttpConnection;
import com.example.gistree.db_con.lib.networking.HttpRequest;

import java.net.URL;
import java.util.ArrayList;
import java.util.logging.LogRecord;


public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<RecordArvore> adapter;
    private DataFactory db;
    private ArrayList<RecordArvore> values;
    private Bundle bd;
    private RecordArvore tree;
    public static final int UPDATEACTIVITY = 1;
    public static final int RESULT_UPDATE_OK = 2;
    public static final int RESULT_DELETE_OK = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Helper.setupUI(findViewById(R.id.activity_main), MainActivity.this);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        /*
        ButtonControllers btc = new ButtonControllers(getApplicationContext());
        Button btEcho = (Button) findViewById(R.id.btEcho);
        Button btSync = (Button) findViewById(R.id.btEnviar);
        btEcho.setOnClickListener(btc);
        btSync.setOnClickListener(btc);
        */

        RepositoryLogArvores repoLog = new RepositoryLogArvores(getApplicationContext());
        ArrayList<RecordLogArvore> aLogs = repoLog.getAllLogs();

        RepositoryArvores repoArv = new RepositoryArvores(getApplicationContext());
        ArrayList<RecordArvore> aArvores = repoArv.getAllArvores();

        RepositoryTimestamp repoTime = new RepositoryTimestamp(getApplicationContext());
        RecordTimestamp timestamp = repoTime.getLastTimestamp();

        String url = Metadata.getAPIUrl(getApplicationContext());

        //new HttpConnection().execute(new HttpRequest(getApplicationContext(),url+"echo/estou_vivo", "GET"));

        //Log.e("END", "END");


//        ListView listView = (ListView) findViewById(android.R.id.list);
//        values = db.arvoresToShow(db.getAllArvores(), db.getAllLogs());
//        adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, android.R.id.text1, values);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                RecordArvore updateArvoreTemp = values.get(position);
//                Bundle bd = new Bundle();
//                bd.putSerializable("tree", updateArvoreTemp);
//                bd.putInt("position", position);
//                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
//                intent.putExtra("tree", bd);
//                startActivityForResult(intent,UPDATEACTIVITY);
//            }
//        });
//
//        new SendPostRequest().execute();

    }

//    protected void buttonSendClicked (View v){
//        EditText editText_id_tree = (EditText) findViewById(R.id.editText_inserir_id_tree);
//        EditText editText_species = (EditText) findViewById(R.id.editText_inserir_species);
//        RecordLogArvore arvoreTemp = new RecordLogArvore();
//        if(!(editText_id_tree.getText().toString().equals("")) &&
//                !(editText_species.getText().toString().equals(""))){
//            arvoreTemp.setId_tree(Long.valueOf(editText_id_tree.getText().toString()));
//            arvoreTemp.setSpecies(editText_species.getText().toString());
//            arvoreTemp = db.addArvore(arvoreTemp);
//            if(arvoreTemp != null){
//                adapter.add(arvoreTemp);
//                adapter.notifyDataSetChanged();
//            }
//        }
//        editText_id_tree.setText("");
//        editText_species.setText("");
//
//    }
//
//    protected void truncateTableLog (View v) {
//        db.truncateLogs();
//        adapter.clear();
//        adapter.addAll(db.arvoresToShow(db.getAllArvores(), db.getAllLogs()));
//        adapter.notifyDataSetChanged();
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == UPDATEACTIVITY) {
//                switch (resultCode){
//                    case (RESULT_UPDATE_OK):
//                        bd = data.getBundleExtra("Tree");
//                        tree = (RecordArvore) bd.getSerializable("Tree");
//                        adapter.getItem(bd.getInt("position")).replaceTree(tree);
//                        adapter.notifyDataSetChanged();
//                        break;
//                    case (RESULT_DELETE_OK):
//                        bd = data.getBundleExtra("Tree");
//                        tree = (RecordArvore) bd.getSerializable("Tree");
//                        adapter.remove(adapter.getItem(bd.getInt("position")));
//                        adapter.notifyDataSetChanged();
//                        break;
//                    default:
//                        break;
//            }
//        }
//    }
}

