package com.dfz.aps;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class TelaDeNovoPedido extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_novo_pedido);
        Intent intent = getIntent();
        String nome = intent.getStringExtra("nome");
        TextView NomeShow = (TextView)findViewById(R.id.nomeusu);
        NomeShow.setText("" + nome);
        SQLiteOpenHelper bs = new APSdao(this);
        SQLiteDatabase bc = bs.getReadableDatabase();
        Cursor busca = bc.rawQuery("SELECT _id,Local,Data FROM Compra WHERE Us_Name = ?", new String[]{nome});
        CursorAdapter lista = new SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_1, busca, new String[]{"Local","Data","_id"}, new int[]{android.R.id.text1},0);
        ListView list =(ListView)findViewById(R.id.spinner);
        list.setAdapter(lista);
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick (AdapterView < ? > parent, View view,int position, long id){
//                setContentView(R.layout.tela_dc);
//                ListView lc =(ListView)findViewById(R.id.li);
//                lc.setAdapter();
//            }
//        });
    }


    public void NovaCompra(View view){
        Intent intent = new Intent(this,NovaCompra.class);
        Intent in = getIntent();
        String nome = in.getStringExtra("nome");
        intent.putExtra("Name",nome);
        startActivity(intent);
    }
}